/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataProcessing;

import DataHelper.Config;
import DataHelper.PGDataHelper;
import FileHandler.XMLFileHandler;
import Logging.ServiceLogger;
import TemplateHelper.vo.ResultEntryVO;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * *************************Start of program***************************** ##
 * Copyright Information	: C-DAC, Noida ## Project Name	: CCD SDK ## Name of
 * Developer	: Siddharth Srivastava ## Module Name	: Health Standards ##
 * Process/Database Object Name	: ## Purpose : ## Date of Creation	: ##
 * Modification Log	: ##	Modify Date	: ##	Reason	(CR/PRS)	: ##	Modify By	:
 */
/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class PrintingHelper {

    public static synchronized void populatePrintingDocumentWithWorkOrderDocument(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        Log(Level.INFO, "populatePrintingDocumentWithWorkOrderDocument");
        Document printingGroupDocument = getPrintingTemplate(resultEntryVOGroupByValidatedBy);
        boolean isContainableInGroupSpecialTemplate = false;

        /*if printing document is required */
        if (printingGroupDocument != null) {

            Log(Level.INFO, "resultEntryVOGroupByValidatedBy--GroupMap()-" + resultEntryVOGroupByValidatedBy.getGroupMap());
            if (resultEntryVOGroupByValidatedBy.getGroupMap() != null) {
                Set<String> keySet = resultEntryVOGroupByValidatedBy.getGroupMap().keySet();
                for (String key : keySet) {
                    String reqNo_groupCode = resultEntryVOGroupByValidatedBy.getGroupMap().get(key);
                    Document testResultEntryGroupDocument = getGroupResultTemplatedocument(resultEntryVOGroupByValidatedBy, reqNo_groupCode);
                    printingGroupDocument = transferResultEntryGroupValueToPrintingTemplate(testResultEntryGroupDocument, printingGroupDocument);
                }
            }

            printingGroupDocument = populatePrintingTemplateWithResultEntryVOGroupByValidatedBy(resultEntryVOGroupByValidatedBy, printingGroupDocument);
            isContainableInGroupSpecialTemplate = true;
        }

        for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
            Document testResultEntryDocument = getResultTemplatedocument(resultEntryVO);
            printingGroupDocument = transferResultEntryValueToPrintingTemplate(testResultEntryDocument, printingGroupDocument);
        }

        resultEntryVOGroupByValidatedBy.setGroupTemplateDocument(printingGroupDocument);

    }

    private static Document transferResultEntryValueToPrintingTemplate(Document testResultEntryDocument, Document printingGroupDocument) {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        String xquery = "/test/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@idC!='']";
        try {

            XPathExpression expr = xpath.compile(xquery);
            Object result = expr.evaluate(testResultEntryDocument, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;

            for (int i = 0; i < nodes.getLength(); i++) {
                Node testDocumentNode = nodes.item(i);
                String elementName = testDocumentNode.getAttributes().getNamedItem("name").getNodeValue();

                if (elementName != null && elementName.length() > 23) {
                    String comparisonElementName = elementName.substring(23);
                    xquery = "/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@originalname='" + comparisonElementName + "'][@labCode='" + elementName.substring(3, 8) + "'][@typeOfElement='RT']";

                    expr = xpath.compile(xquery);
                    Object result1 = expr.evaluate(printingGroupDocument, XPathConstants.NODESET);
                    NodeList nodesList = (NodeList) result1;

                    if (nodesList.getLength() != 0) {

                        if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("input") || nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("select")) {
                            nodesList.item(0).getAttributes().getNamedItem("value").setNodeValue(testDocumentNode.getAttributes().getNamedItem("value").getNodeValue());
                            NodeList elementChildList = testDocumentNode.getChildNodes();

                            for (int k = 0; k < elementChildList.getLength(); k++) {
                                nodesList.item(0).appendChild((nodesList.item(0)).getOwnerDocument().importNode(elementChildList.item(k), true));
                            }
                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("textarea")) {
                            Log(Level.INFO, "textArea");
                            nodesList.item(0).getAttributes().getNamedItem("value").setNodeValue(testDocumentNode.getAttributes().getNamedItem("value").getNodeValue());
                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("button")) {
                            Log(Level.INFO, "button");
                            nodesList.item(0).appendChild(nodesList.item(0).getOwnerDocument().importNode(testDocumentNode.getFirstChild(), true));
                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("queryValue")) {
                            Log(Level.INFO, "queryValue");
                            nodesList.item(0).appendChild(nodesList.item(0).getOwnerDocument().importNode(testDocumentNode.getFirstChild(), true));
                        } else {

                        }
                    }
                }

            }

            xquery = "/test/requisitionform/testtemplate/table/rowDetails/columnDetails/table/tr/td/element";

            expr = xpath.compile(xquery);
            result = expr.evaluate(testResultEntryDocument, XPathConstants.NODESET);
            nodes = (NodeList) result;

            for (int i = 0; i < nodes.getLength(); i++) {
                Node testDocumentNode = nodes.item(i);
                String elementName = testDocumentNode.getAttributes().getNamedItem("name").getNodeValue();
                String comparisonElementName = elementName;
                xquery = "/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@originalname='" + comparisonElementName + "'][@labCode='" + elementName.substring(3, 8) + "'][@typeOfElement='RF']";

                expr = xpath.compile(xquery);
                Object result1 = expr.evaluate(printingGroupDocument, XPathConstants.NODESET);
                NodeList nodesList = (NodeList) result1;

                if (nodesList.getLength() != 0) {
                    if (nodesList.item(0).getAttributes().getNamedItem("idC") != null) {
                        if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("input") || nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("select")) {
                            nodesList.item(0).getAttributes().getNamedItem("value").setNodeValue(testDocumentNode.getAttributes().getNamedItem("value").getNodeValue());
                            NodeList elementChildList = testDocumentNode.getChildNodes();

                            for (int k = 0; k < elementChildList.getLength(); k++) {
                                nodesList.item(0).appendChild((nodesList.item(0)).getOwnerDocument().importNode(elementChildList.item(k), true));
                            }

                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("textarea")) {
                            nodesList.item(0).getAttributes().getNamedItem("value").setNodeValue(testDocumentNode.getAttributes().getNamedItem("value").getNodeValue());
                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("button")) {
                            nodesList.item(0).appendChild(nodesList.item(0).getOwnerDocument().importNode(testDocumentNode.getFirstChild(), true));
                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("queryValue")) {
                            nodesList.item(0).appendChild(nodesList.item(0).getOwnerDocument().importNode(testDocumentNode.getFirstChild(), true));
                        } else {

                        }
                    }
                }

            }

            xquery = "/test/sampleform/testtemplate/table/rowDetails/columnDetails/table/tr/td/element";

            expr = xpath.compile(xquery);
            result = expr.evaluate(testResultEntryDocument, XPathConstants.NODESET);
            nodes = (NodeList) result;

            for (int i = 0; i < nodes.getLength(); i++) {
                Node testDocumentNode = nodes.item(i);
                String elementName = testDocumentNode.getAttributes().getNamedItem("name").getNodeValue();
                String comparisonElementName = elementName;
                xquery = "/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@originalname='" + comparisonElementName + "'][@labCode='" + elementName.substring(3, 8) + "'][@typeOfElement='SF']";

                expr = xpath.compile(xquery);

                Object result1 = expr.evaluate(printingGroupDocument, XPathConstants.NODESET);
                NodeList nodesList = (NodeList) result1;

                if (nodesList.getLength() != 0) {
                    if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("input") || nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("select")) {
                        nodesList.item(0).getAttributes().getNamedItem("value").setNodeValue(testDocumentNode.getAttributes().getNamedItem("value").getNodeValue());
                        NodeList elementChildList = testDocumentNode.getChildNodes();

                        for (int k = 0; k < elementChildList.getLength(); k++) {
                            nodesList.item(0).appendChild((nodesList.item(0)).getOwnerDocument().importNode(elementChildList.item(k), true));
                        }
                    } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("textarea")) {
                        nodesList.item(0).getAttributes().getNamedItem("value").setNodeValue(testDocumentNode.getAttributes().getNamedItem("value").getNodeValue());
                    } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("button")) {
                        nodesList.item(0).appendChild(nodesList.item(0).getOwnerDocument().getOwnerDocument().importNode(testDocumentNode.getFirstChild(), true));
                    } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("queryValue")) {
                        nodesList.item(0).appendChild(nodesList.item(0).getOwnerDocument().getOwnerDocument().importNode(testDocumentNode.getFirstChild(), true));
                    } else {

                    }
                }

            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return printingGroupDocument;

    }

    public static synchronized Document getResultTemplatedocument(ResultEntryVO resultEntryVO) {
        javax.xml.parsers.DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document testDocument = null;
        String xquery = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document testTemplateDocument = null;
            testTemplateDocument = XMLFileHandler.getWorkOrderDocument(db, testTemplateDocument, resultEntryVO.getPatCRNo());

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            xquery = "//patient/episode[@number='" + ((resultEntryVO.getPatEpisodeCode() == null) ? "0" : resultEntryVO.getPatEpisodeCode()) + "']" + "[@visitNo='" + ((resultEntryVO.getPatVisitNo() == null) ? "0" : resultEntryVO.getPatVisitNo()) + "' ]"
                    + "/requisition[@number='" + resultEntryVO.getRequisitionNo() + "']/laboratory[@code='" + resultEntryVO.getLaboratoryCode() + "']/test[@code='" + resultEntryVO.getTestCode() + "'][@requisitionDNo='" + resultEntryVO.getRequisitionDNo() + "']";
            XPathExpression expr = xpath.compile(xquery);
            Object result = expr.evaluate(testTemplateDocument, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Node testDocumentNode = nodes.item(i);
                testDocument = db.newDocument();
                testDocument.appendChild(testDocument.importNode(testDocumentNode, true));

            }

        } catch (Exception Ex) {
            Ex.printStackTrace();
        }
        return testDocument;

    }

    private static Document getGroupResultTemplatedocument(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy, String reqNoGroupCode) {
        javax.xml.parsers.DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document testDocument = null;
        String xquery = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document testTemplateDocument = null;
            //testTemplateDocument = XMLFileHandler.getWorkOrderDocument(db, testTemplateDocument, resultEntryVOGroupByValidatedBy.getRequisitionTypeCode(), resultEntryVOGroupByValidatedBy.getPatCRNo(), resultEntryVOGroupByValidatedBy.resultEntryVO.getRequisitionNo());
            testTemplateDocument = XMLFileHandler.getWorkOrderDocument(db, testTemplateDocument, resultEntryVOGroupByValidatedBy.getPatCRNo());

            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            xquery = "//patient/episode[@number='" + ((resultEntryVOGroupByValidatedBy.getPatEpisodeCode() == null) ? "0" : resultEntryVOGroupByValidatedBy.getPatEpisodeCode()) + "']" + "[@visitNo='" + ((resultEntryVOGroupByValidatedBy.getPatVisitNo() == null) ? "0" : resultEntryVOGroupByValidatedBy.getPatVisitNo()) + "' ]"
                    + "/requisition[@number='" + resultEntryVOGroupByValidatedBy.getRequisitionNo() + "']/laboratory[@code='" + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + "']/testgroup[@groupcode='" + resultEntryVOGroupByValidatedBy.getGroupCode() + "'][@requisitionNo='" + resultEntryVOGroupByValidatedBy.getRequisitionNo() + "']";
            XPathExpression expr = xpath.compile(xquery);
            Object result = expr.evaluate(testTemplateDocument, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            Log(Level.INFO, "getGroupResultTemplatedocument::group template found ::" + nodes.getLength());
            for (int i = 0; i < nodes.getLength(); i++) {
                Log(Level.INFO, "inside template creation show");
                Node testDocumentNode = nodes.item(i);
                testDocument = db.newDocument();
                testDocument.appendChild(testDocument.importNode(testDocumentNode, true));

                // resultEntryVO.setTestDocument(testDocument);
            }
            Log(Level.INFO, "getGroupResultTemplatedocument  :: testDocument " + testDocument);
        } catch (Exception Ex) {
            Ex.getMessage();
        }
        return testDocument;
    }

    public static Document getPrintingTemplate(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy) {
        Log(Level.INFO, "getPrintingTemplate");
        Document groupDocumentNode = null;
        try {

            //InvestigationDocumentCacheManager documentCacheManager=InvestigationDocumentCacheManager.getInstance();
            Node testDocumentNode = PGDataHelper.getInstance().getTemplateNodeObj(Config.XML_PRINTINGTEMPLATE, resultEntryVOGroupByValidatedBy.getPrintedWithTemplateID(), false);
            groupDocumentNode = PGDataHelper.getInstance().getNewDocument();
            groupDocumentNode.appendChild(groupDocumentNode.importNode(testDocumentNode, true));
            resultEntryVOGroupByValidatedBy.setGroupTemplateDocument(groupDocumentNode);

            resultEntryVOGroupByValidatedBy.setPageSize(Config.pagewidthheight);
            resultEntryVOGroupByValidatedBy.setHeaderHeight("182");
            resultEntryVOGroupByValidatedBy.setHeaderHeight(Config.pagewidthprinting);
            resultEntryVOGroupByValidatedBy.setFooterHeight("42");
            resultEntryVOGroupByValidatedBy.setFooterWidth(Config.pagewidthprinting);

            if (testDocumentNode.getAttributes().getNamedItem("pageheight") != null) {
                resultEntryVOGroupByValidatedBy.setPageSize(testDocumentNode.getAttributes().getNamedItem("pageheight").getNodeValue());

                if (resultEntryVOGroupByValidatedBy.getPageSize().equals("100%")) {
                    resultEntryVOGroupByValidatedBy.setPageSize(Config.pagewidthheight);
                }
            }

            if (testDocumentNode.getAttributes().getNamedItem("headerheight") != null) {
                resultEntryVOGroupByValidatedBy.setHeaderHeight(testDocumentNode.getAttributes().getNamedItem("headerheight").getNodeValue());
                if (resultEntryVOGroupByValidatedBy.getHeaderHeight().equals("100%")) {
                    resultEntryVOGroupByValidatedBy.setHeaderHeight("182");
                }
            }

            if (testDocumentNode.getAttributes().getNamedItem("headerwidth") != null) {
                resultEntryVOGroupByValidatedBy.setHeaderWidth(testDocumentNode.getAttributes().getNamedItem("headerwidth").getNodeValue());
                if (resultEntryVOGroupByValidatedBy.getHeaderWidth().equals("100%")) {
                    resultEntryVOGroupByValidatedBy.setHeaderWidth(Config.pagewidthprinting);
                }
            }

            if (testDocumentNode.getAttributes().getNamedItem("footerheight") != null) {
                resultEntryVOGroupByValidatedBy.setFooterHeight(testDocumentNode.getAttributes().getNamedItem("footerheight").getNodeValue());
                if (resultEntryVOGroupByValidatedBy.getFooterHeight().equals("100%")) {
                    resultEntryVOGroupByValidatedBy.setFooterHeight("42");
                }

            }

            if (testDocumentNode.getAttributes().getNamedItem("footerwidth") != null) {
                resultEntryVOGroupByValidatedBy.setFooterWidth(testDocumentNode.getAttributes().getNamedItem("footerwidth").getNodeValue());
                if (resultEntryVOGroupByValidatedBy.getFooterWidth().equals("100%")) {
                    resultEntryVOGroupByValidatedBy.setFooterWidth(Config.pagewidthprinting);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return groupDocumentNode;
    }

    private static Document transferResultEntryGroupValueToPrintingTemplate(
            Document testResultEntryGroupDocument,
            Document printingGroupDocument) {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        String xquery = "/testgroup/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@idC!='']";

        Log(Level.INFO, "tranferResultEntryGroupValueToPrintingTemplate::xquery ::" + xquery);
        try {

            //	InvestigationDocumentCacheManager.getInstance().createXMLforCheck("d:/s3.xml", testResultEntryGroupDocument);
            //InvestigationDocumentCacheManager.getInstance().createXMLforCheck("d:/s4.xml", printingGroupDocument);
            XPathExpression expr = xpath.compile(xquery);
            Object result = expr.evaluate(testResultEntryGroupDocument, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            Log(Level.INFO, "tranferResultEntryGroupValueToPrintingTemplate :: 2 " + nodes.getLength());
            for (int i = 0; i < nodes.getLength(); i++) {
                Node testDocumentNode = nodes.item(i);
                String elementName = testDocumentNode.getAttributes().getNamedItem("name").getNodeValue();
                //Log(Level.INFO,"elementName  "+elementName);
                if (elementName != null && elementName.length() > 34) {
                    String comparisonElementName = elementName.substring(34);
                    xquery = "/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@name='" + comparisonElementName + "']";
                    Log(Level.INFO, "tranferResultEntryGroupValueToPrintingTemplate::xquery ::" + xquery);
                    expr = xpath.compile(xquery);
                    Object result1 = expr.evaluate(printingGroupDocument, XPathConstants.NODESET);
                    NodeList nodesList = (NodeList) result1;
                    Log(Level.INFO, "tranferResultEntryGroupValueToPrintingTemplate Nodes :: 1 " + nodesList.getLength());
                    if (nodesList.getLength() != 0) {

                        if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("input") || nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("select")) {
                            nodesList.item(0).getAttributes().getNamedItem("value").setNodeValue(testDocumentNode.getAttributes().getNamedItem("value").getNodeValue());
                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("textarea")) {
                            nodesList.item(0).getAttributes().getNamedItem("value").setNodeValue(testDocumentNode.getAttributes().getNamedItem("value").getNodeValue());
                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("button")) {
                            testDocumentNode.appendChild(testDocumentNode.getOwnerDocument().importNode(nodesList.item(0).getFirstChild(), true));
                        } else if (nodesList.item(0).getAttributes().getNamedItem("idC").getNodeValue().equalsIgnoreCase("queryValue")) {
                            testDocumentNode.appendChild(testDocumentNode.getOwnerDocument().importNode(nodesList.item(0).getFirstChild(), true));
                        } else {

                        }
                    }
                }

            }

        } catch (XPathExpressionException e) {
            Log(Level.INFO, e.getMessage());
        } catch (Exception ex) {
            Log(Level.INFO, ex.getMessage());
        }
        return printingGroupDocument;
    }

    public static Document populatePrintingTemplateWithResultEntryVOGroupByValidatedBy(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy,
            Document printingGroupDocument) {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        String xquery = "/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@idC='voValue']";
        Class resultEntryVOGroupByValidatedByClassObj = resultEntryVOGroupByValidatedBy.getClass();
        Map<String, Method> getterMethodMap = new HashMap<String, Method>();
        {
            Method[] methods = resultEntryVOGroupByValidatedByClassObj.getMethods();
            for (int j = 0; j < methods.length; j++) {
                Method method = methods[j];
                if (method.getName().startsWith("get")) {
                    getterMethodMap.put(method.getName(), method);
                }
            }
        }

        try {
            XPathExpression expr = xpath.compile(xquery);
            Object result = expr.evaluate(printingGroupDocument, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Node voValueNode = nodes.item(i);
                String elementName = voValueNode.getAttributes().getNamedItem("name").getNodeValue();
                String voVariable = elementName.split("#")[1];
                Character c = voVariable.charAt(0);
                voVariable = Character.toUpperCase(c) + voVariable.substring(1);
                //invoking getter method 
                Method getterMethod = getterMethodMap.get("get" + voVariable);
                voValueNode.getAttributes().getNamedItem("value").setNodeValue((String) getterMethod.invoke(resultEntryVOGroupByValidatedBy, null));

            }
        } catch (Exception ex) {
            Log(Level.INFO, ex.getMessage());
        }

        return printingGroupDocument;
    }

    public static void createHTMLReportForListOfWorkOrders(List<ResultEntryVOGroupByValidatedBy> groupValidatedByList, boolean compulsory) {
        int groupIndex = 0;
        // InvestigationStyleSheetConfigurationCacheManager PGDataHelper.getInstance() = InvestigationStyleSheetConfigurationCacheManager.getInstance();
        javax.xml.parsers.DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Transformer transformerGroupPrintingTemplate_body = PGDataHelper.getInstance().getTransformerObject(Config.XSL_PRINTINGSTYLESHEET_BODY);//tansformerFactory.newTransformer(new StreamSource(path+"printingStyleSheet_body.xsl"));
            Transformer transformerGroupPrintingTemplate_header = null;//PGDataHelper.getInstance().getTransformerObject(Config.XSL_PRINTINGSTYLESHEET_HEADER);//tansformerFactory.newTransformer(new StreamSource(path+"printingStyleSheet_header.xsl"));
            Transformer transformerGroupPrintingTemplate_footer = null;//PGDataHelper.getInstance().getTransformerObject(Config.XSL_PRINTINGSTYLESHEET_FOOTER);//tansformerFactory.newTransformer(new StreamSource(path+"printingStyleSheet_footer.xsl"));
            Transformer transformerStandardRangesPrintingTemplate = PGDataHelper.getInstance().getTransformerObject(Config.XSL_REPORTWITHNORMALVALUES);//tansformerFactory.newTransformer(new StreamSource(path+"reportWithNormalValues.xsl"));
            Transformer transformerNoRangesPrintingTemplate = null;//PGDataHelper.getInstance().getTransformerObject(Config.XSL_REPORTWITHOUTNORMALVALUES);//tansformerFactory.newTransformer(new StreamSource(path+"reportWithoutNormalValues.xsl"));
            Map<String, Integer> selectedDynamicGroupDetail = new HashMap<String, Integer>();
            Map<String, ResultEntryVO> reqDNoTestMap = new HashMap<String, ResultEntryVO>();
    		List<ResultEntryVO> rvoWithDynamic = new ArrayList<ResultEntryVO>();
            for(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy:groupValidatedByList)
    		{
    			for(ResultEntryVO resultEntryVO: resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy())
    			{
    				String s = resultEntryVO.getRequisitionNo() + resultEntryVO.getGroupCode() + resultEntryVO.getDynamicTemplatePrintedGroup();
    				
    				if(selectedDynamicGroupDetail.containsKey(s)) {
    					resultEntryVO.setDoCreateTemplate(false);
    					rvoWithDynamic.add(resultEntryVO);
    					reqDNoTestMap.put(resultEntryVO.getRequisitionNo()+resultEntryVO.getGroupCode()+resultEntryVO.getTestCode(), resultEntryVO);
    					
    				}	
    				else if(resultEntryVO.getDynamicTemplatePrintedGroup().equals("1"))
    				{
    					//resultEntryVO.setTestName(resultEntryVO.getGroupName());
    					selectedDynamicGroupDetail.put(resultEntryVO.getRequisitionNo()+resultEntryVO.getGroupCode() + "1", 1);
    					rvoWithDynamic.add(resultEntryVO);
    					reqDNoTestMap.put(resultEntryVO.getRequisitionNo()+resultEntryVO.getGroupCode()+resultEntryVO.getTestCode(), resultEntryVO);
    				}
    			}    			    			    	
    		}
            
            for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy : groupValidatedByList) {
                Log(Level.INFO, "resultEntryVOGroupByValidatedBy.getGroupTemplateDocument() is " + resultEntryVOGroupByValidatedBy.getGroupTemplateDocument());
                if (resultEntryVOGroupByValidatedBy.getGroupTemplateDocument() != null) {
                    printingWorkOrderDocumentString(resultEntryVOGroupByValidatedBy, transformerGroupPrintingTemplate_body, transformerGroupPrintingTemplate_header, transformerGroupPrintingTemplate_footer);
                } else {
                    Document testTemplateDocument = null;

                    // This function regenerates the XML of workorders for whom it was not generatede
                  //  reupdatePatientXML(db, resultEntryVOGroupByValidatedBy, resultEntryVOGroupByValidatedBy.getRequisitionTypeCode(), resultEntryVOGroupByValidatedBy.getPatCRNo(), resultEntryVOGroupByValidatedBy.resultEntryVO.getRequisitionNo(), compulsory);
                    testTemplateDocument = XMLFileHandler.getWorkOrderDocument(db, testTemplateDocument, resultEntryVOGroupByValidatedBy.getPatCRNo());

                    /*setting the header*/
                    // if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals(Config.STATUS_REQUISITIONTYPE_WITHCRNO_OPD) || resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals(Config.STATUS_REQUISITIONTYPE_WITHCRNO_IPD)) {
                    PDFPrintingProcesses.getDefaultHeaderRegisteredPatient(resultEntryVOGroupByValidatedBy);
                   // } else {
                    //     PDFPrintingProcesses.getDefaultHeaderUnRegisteredPatient(resultEntryVOGroupByValidatedBy);

                    // }

                    /*setting the body*/
                    if (resultEntryVOGroupByValidatedBy.getGroupTemplateString() == null) {
                        //remove after testing
                      //  resultEntryVOGroupByValidatedBy.setGroupTemplateString("<table width=100%><tr><td width='100%'></td></td></table>");
                    }

                    for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
                        //Log(Level.INFO,"Reporting Type ="+resultEntryVOGroupByValidatedBy.getPrintingType());
                        Log(Level.INFO, "create html for testcode:" + resultEntryVO.getTestCode() + " isfullcolspanrequired: " + resultEntryVO.getIsFullColspanRequired());
                        Transformer transformer = transformerStandardRangesPrintingTemplate;
                        
                        
                        //select transformer object for department entry 21
                        if(resultEntryVO.getIsDeptEntry().equals("1"))
                        	transformer = transformerGroupPrintingTemplate_body;
                        
                        
                        String dynamicTemplateID = null;
                        boolean isGroupWithDynamicTemplate = false;
                        if(resultEntryVO.getDynamicTemplatePrintedGroup() != null && resultEntryVO.getDynamicTemplatePrintedGroup().equals("1"))
                        {
                        	isGroupWithDynamicTemplate = true;
                			dynamicTemplateID =PGDataHelper.getInstance().getDynamicTemplateStatus(resultEntryVO.getGroupCode(), resultEntryVO.getHospitalCode(), isGroupWithDynamicTemplate, resultEntryVO.getLaboratoryCode());
                        }
                        else
                        {
                        	isGroupWithDynamicTemplate = false;
                        	dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(resultEntryVO.getTestCode(), null, isGroupWithDynamicTemplate, resultEntryVO.getLaboratoryCode());
                        }
//                        if(resultEntryVO.getPrintWithDynamicTemplate().equals("1") == true)
//                        {
//                        	transformer = transformerGroupPrintingTemplate_body;
//                        }
                        if(dynamicTemplateID != null && !dynamicTemplateID.isEmpty())
                        {
                        	transformer = transformerGroupPrintingTemplate_body;
                        }	
                        if (resultEntryVO.getIsFullColspanRequired().equals("1")) {
                            getResultTemplatedocumentString(db, resultEntryVO, resultEntryVOGroupByValidatedBy, transformer, testTemplateDocument);
                        } else {
                            if (resultEntryVOGroupByValidatedBy.getPrintingType() == null || resultEntryVOGroupByValidatedBy.getPrintingType().equals("0")) {
                                getResultTemplatedocumentString(db, resultEntryVO, resultEntryVOGroupByValidatedBy, transformer, testTemplateDocument);
                            } else {
                                getResultTemplatedocumentString(db, resultEntryVO, resultEntryVOGroupByValidatedBy, transformer, testTemplateDocument);
                            }

                        }
                        
                       
                        	
                    }
/*final remark at the end of report for the patient*/
                    if(resultEntryVOGroupByValidatedBy.getFinalRemarkReqd().equals("1"))
                    { String finalRemark=" \n <table style='margin-top:15px'><tr><td><font color='black' size='3'><b><u>Comments: </u></b></font></td></tr><tr><td>"+resultEntryVOGroupByValidatedBy.getFinalRemark()+"</td></tr></table>";
                    resultEntryVOGroupByValidatedBy.setGroupTemplateString(resultEntryVOGroupByValidatedBy.getGroupTemplateString()+finalRemark);
                    }
                    
                    
/*report adddendum remarks*/                    
                 
                    if(resultEntryVOGroupByValidatedBy.getAddendumRemark()!=null && (resultEntryVOGroupByValidatedBy.getAddendumRemark().equals("")==false))
                    { String addendum="<table><tr><td><font color='black' size='3'><b><u>Addendum: </u></b></font></td></tr><tr><td>"+(resultEntryVOGroupByValidatedBy.getAddendumRemark()==null?"":resultEntryVOGroupByValidatedBy.getAddendumRemark())+"</td></tr></table>";
                    resultEntryVOGroupByValidatedBy.setGroupTemplateString(resultEntryVOGroupByValidatedBy.getGroupTemplateString()+addendum);
                    }
                   // resultEntryVOGroupByValidatedBy.setGroupTemplateString(resultEntryVOGroupByValidatedBy.getGroupTemplateString());//+"</td></tr></table>");

                    //Changed by ashu
                    
                    String endOfReport =" \n <table cellspacing='0' cellpadding='0' border='0' width='100%'><tr><td align='center' width='100%'><font color='black' size='2'><b>********** END OF THE REPORT **********</b></font></td></tr></table>";
                    resultEntryVOGroupByValidatedBy.setGroupTemplateString(resultEntryVOGroupByValidatedBy.getGroupTemplateString()+endOfReport);
                    
                    
                    /*setting the footer*/
                    if (resultEntryVOGroupByValidatedBy.getFooter() == null || resultEntryVOGroupByValidatedBy.getFooter().equals("")) {
                        //if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals(Config.STATUS_REQUISITIONTYPE_WITHCRNO_OPD) || resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals(Config.STATUS_REQUISITIONTYPE_WITHCRNO_IPD)) {
                        PDFPrintingProcesses.getDefaultFooter(resultEntryVOGroupByValidatedBy);

                        /*} else {
                         PDFPrintingProcesses.getDefaultFooter(resultEntryVOGroupByValidatedBy);
                         }*/
                    }

                }

                groupIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static synchronized void getResultTemplatedocumentString(DocumentBuilder db, ResultEntryVO resultEntryVO, ResultEntryVOGroupByValidatedBy validatedBy, Transformer tranformer, Document testTemplateDocument) {

        Document testDocument = null;
        String xquery = null;
        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            DOMSource domSource1 = new DOMSource(testTemplateDocument);
            StringWriter writer = new StringWriter();
            StreamResult result1 = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource1, result1);

            System.out.println("XML IN String format is: \n" + writer.toString());
           /* xquery = "//patient/episode[@number='" + ((resultEntryVO.getPatEpisodeCode() == null) ? "0" : resultEntryVO.getPatEpisodeCode()) + "']" + "[@visitNo='" + ((resultEntryVO.getPatVisitNo() == null) ? "0" : resultEntryVO.getPatVisitNo()) + "' ]"
                    + "/requisition[@number='" + resultEntryVO.getRequisitionNo() + "']/laboratory[@code='" + resultEntryVO.getLaboratoryCode() + "']/test[@code='" + resultEntryVO.getTestCode() + "'][@requisitionDNo='" + resultEntryVO.getRequisitionDNo() + "']";*/
            
             xquery = "//patient/episode[@number='" + ((resultEntryVO.getPatEpisodeCode() == null) ? "0" : resultEntryVO.getPatEpisodeCode()) + "']" + "[@visitNo='" + ((resultEntryVO.getPatVisitNo() == null) ? "" : resultEntryVO.getPatVisitNo()) + "' ]"
                    + "/requisition[@number='" + resultEntryVO.getRequisitionNo() + "']/laboratory[@code='" + resultEntryVO.getLaboratoryCode() + "']/test[@code='" + resultEntryVO.getTestCode() + "'][@requisitionDNo='" + resultEntryVO.getRequisitionDNo() + "']";
            
            XPathExpression expr = xpath.compile(xquery);
            Object result = expr.evaluate(testTemplateDocument, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                Node testDocumentNode = nodes.item(i);
                testDocument = db.newDocument();
                testDocument.appendChild(testDocument.importNode(testDocumentNode, true));

                Source domSource = new DOMSource(testDocument);
                java.io.CharArrayWriter baos = new java.io.CharArrayWriter();

                StringWriter w = new StringWriter();
                StreamResult streamResult = new StreamResult(w);
                tranformer.clearParameters();
                tranformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tranformer.setParameter("workOrder", resultEntryVO.getRequisitionDNo());
                tranformer.setParameter("sessionNo", "2");//resultEntryVO.getSessionId());
                tranformer.transform(domSource, streamResult);

                StringWriter writer1 = new StringWriter();
                StreamResult result2 = new StreamResult(writer1);
                writer.flush();
                transformer.transform(domSource, result2);
                System.out.println(writer1.toString());
                Log(Level.INFO, "xml for particular reqdno:"+resultEntryVO.getRequisitionDNo()+":" + writer1.toString());  
               // System.out.println("baos String: " + w.toString());
                if(resultEntryVO.isDoCreateTemplate() == true)
                	validatedBy.setGroupTemplateString(((validatedBy.getGroupTemplateString() != null) ? validatedBy.getGroupTemplateString() : "") + w.toString());
                System.out.println("baos String: " + validatedBy.getGroupTemplateString());
            }
            
            
            
         
        } catch (Exception Ex) {
            Ex.printStackTrace();
        }

    }

    private static boolean reupdatePatientXML(DocumentBuilder db, ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy, String requisitionTypeCode, String patCRNo, String requisitionNo, boolean compulsory) throws IOException, SAXException, ParserConfigurationException, JAXBException {
        Document patientXMLDocument = null;
        boolean workOrderTemplateFound = true;
        try {
            patientXMLDocument = XMLFileHandler.getWorkOrderDocument(db, patientXMLDocument, patCRNo);
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
                String xquery = "//patient/episode[@number='" + ((resultEntryVO.getPatEpisodeCode() == null) ? "0" : resultEntryVO.getPatEpisodeCode()) + "']" + "[@visitNo='" + ((resultEntryVO.getPatVisitNo() == null) ? "0" : resultEntryVO.getPatVisitNo()) + "' ]"
                        + "/requisition[@number='" + resultEntryVO.getRequisitionNo() + "']/laboratory[@code='" + resultEntryVO.getLaboratoryCode() + "']/test[@code='" + resultEntryVO.getTestCode() + "'][@requisitionDNo='" + resultEntryVO.getRequisitionDNo() + "']";
                XPathExpression expr = xpath.compile(xquery);
                Object result = expr.evaluate(patientXMLDocument, XPathConstants.NODESET);
                NodeList nodes = (NodeList) result;
                if (nodes.getLength() == 0) {

                    workOrderTemplateFound = false;
                    break;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (compulsory || workOrderTemplateFound == false) {
            List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedBies = new ArrayList<ResultEntryVOGroupByValidatedBy>();
            resultEntryVOGroupByValidatedBies.add(resultEntryVOGroupByValidatedBy);
            TemplateProcessingUSE.groupSelectedWorkOrders(resultEntryVOGroupByValidatedBies);
            if (resultEntryVOGroupByValidatedBy.getGroupTemplateDocument() != null) {
                resultEntryVOGroupByValidatedBy.setIsGroupUpdateble("true");
            }

            for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {

                resultEntryVO.setIsWorkOrderUpdateble("true");
            }

            XMLFileHandler.updatePatientInvestigationFile(resultEntryVOGroupByValidatedBies);
            workOrderTemplateFound = true;

        } else {
            System.out.println("Patient XML OKAY in xml file");
        }

        return workOrderTemplateFound;

    }

    public static void printingWorkOrderDocumentString(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy, Transformer transformerGroupPrintingTemplateBody, Transformer transformerGroupPrintingTemplateHeader, Transformer transformerGroupPrintingTemplateFooter) {
        Source domSource = new DOMSource(resultEntryVOGroupByValidatedBy.getGroupTemplateDocument());
        java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
        try {

            //	InvestigationDocumentCacheManager.getInstance().createXMLforCheck("d:/s2", resultEntryVOGroupByValidatedBy.getGroupTemplateDocument());
            //header
            StreamResult streamResult = new StreamResult(baos);
            transformerGroupPrintingTemplateHeader.transform(domSource, streamResult);
            resultEntryVOGroupByValidatedBy.setHeader((baos != null) ? baos.toString() : "");
            //LOGGER_INV.log(Level.INFO,"Header ::"+resultEntryVOGroupByValidatedBy.getHeader());

            if (resultEntryVOGroupByValidatedBy.getHeader() == null || resultEntryVOGroupByValidatedBy.getHeader().trim().equals("")) {

                if (resultEntryVOGroupByValidatedBy.getIsHeaderRequired().equals("Y")) {

                    /* if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3") && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
                     PDFPrintingProcesses.getDefaultHeaderUnRegisteredPatient(resultEntryVOGroupByValidatedBy);
                     } else {*/
                    PDFPrintingProcesses.getDefaultHeaderRegisteredPatient(resultEntryVOGroupByValidatedBy);
                    //}
                }
            }

            //body
            baos = new java.io.CharArrayWriter();
            streamResult = new StreamResult(baos);
            transformerGroupPrintingTemplateBody.transform(domSource, streamResult);
            resultEntryVOGroupByValidatedBy.setGroupTemplateString(baos.toString());
            //footer
            baos = new java.io.CharArrayWriter();
            streamResult = new StreamResult(baos);
            transformerGroupPrintingTemplateFooter.transform(domSource, streamResult);
            resultEntryVOGroupByValidatedBy.setFooter((baos != null) ? baos.toString() : "");

            //LOGGER_INV.log(Level.INFO,"Footer 1 ::"+resultEntryVOGroupByValidatedBy.getFooter());
            if (resultEntryVOGroupByValidatedBy.getFooter() == null || resultEntryVOGroupByValidatedBy.getFooter().trim().equals("")) {
                if (resultEntryVOGroupByValidatedBy.getIsFooterRequired().equals("Y")) {

                    /*if (resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("3") && resultEntryVOGroupByValidatedBy.getRequisitionTypeCode().equals("4")) {
                     PDFPrintingProcesses.getDefaultFooter(resultEntryVOGroupByValidatedBy);
                     } else {*/
                    PDFPrintingProcesses.getDefaultFooter(resultEntryVOGroupByValidatedBy);
                    //}
                }
            }

            //LOGGER_INV.log(Level.INFO,"Footer 2 ::"+resultEntryVOGroupByValidatedBy.getFooter());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void Log(Level level, String msg) {
        ServiceLogger.Log(PrintingHelper.class.getName(), level, msg);
    }

    private static void Log(Level level, Exception e) {
        ServiceLogger.Log(PrintingHelper.class.getName(), level, e);
    }
}
