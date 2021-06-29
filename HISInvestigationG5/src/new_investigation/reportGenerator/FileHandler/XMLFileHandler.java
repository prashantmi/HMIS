 
package new_investigation.reportGenerator.FileHandler;

import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.Ftp.JakartaFtpWrapper;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.MongoHelper.MongoXmlHandler;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

 
public class XMLFileHandler {

	@SuppressWarnings("unchecked")
	public static Map updatePatientInvestigationFile(List<ResultEntryVOGroupByValidatedBy> selectedWorkOrderList) throws IOException, SAXException, ParserConfigurationException, JAXBException {
    	
    	Log(Level.INFO, "XML Building and Apped Starts");
    	
        javax.xml.parsers.DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Map xmlReportDoc= new HashMap();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Log(Level.SEVERE, e);
            e.printStackTrace();
        }

        for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy : selectedWorkOrderList) {
            Document doc = null;
            if (resultEntryVOGroupByValidatedBy.getIsGroupUpdateble().equals("true")) {
              
				             	
            	
            	doc=(Document) xmlReportDoc.get(resultEntryVOGroupByValidatedBy.getPatCRNo());
            	if(doc==null)doc=db.newDocument();
            	//to update appended xml report
            	updatePatientInvestigationFileForGroup(doc, resultEntryVOGroupByValidatedBy);
            }

            for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
                if (resultEntryVO.getIsWorkOrderUpdateble().equals("true")) {
                    if (doc == null) {
                      
						 
                    	
                    	doc=(Document) xmlReportDoc.get(resultEntryVOGroupByValidatedBy.getPatCRNo());
                    	if(doc==null)doc=db.newDocument();
                    }
                    //to update appended xml report
                    updatePatientInvestigationFile(doc, resultEntryVO);
                }
            }

            
            if (doc != null) {
				 
            
            	try {
            		Log(Level.INFO, "XML Building and Apped Ends ");
            	
	           		TransformerFactory tansformerFactory=TransformerFactory.newInstance();
	           		Transformer transformer=null;
	           		BufferedOutputStream bos=null;
	           		StringWriter writer = new StringWriter();
	           		
					transformer = tansformerFactory.newTransformer();
					DOMSource domSource=new DOMSource(doc);
	       	
	       			StreamResult result = new StreamResult(writer);
	       		    transformer.transform(domSource, result);
       		     
	       		    Log(Level.INFO, "XML Output: "+writer.toString());
       		     
           		} catch (TransformerException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
            	xmlReportDoc.put(resultEntryVOGroupByValidatedBy.getPatCRNo(), doc);   	
            }
        }
        return xmlReportDoc;
    }

    public static void saveWorkOrderDocument(Document doc, String patCRNo) {

        Log(Level.INFO, "saveWorkOrderDocumentDirectlyToMongo");
        TransformerFactory tansformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;

        try {
            transformer = tansformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            String key = patCRNo;//(requisitionTypeCode.equals(InvestigationConfig.STATUS_REQUISITIONTYPE_WITHCRNO_OPD) || requisitionTypeCode.equals(InvestigationConfig.STATUS_REQUISITIONTYPE_WITHCRNO_IPD)) ? patCRNo : requisitionNo;
            //bos = createUpdatePatientFileFTP(key);
            StringWriter writer = new StringWriter();

            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(domSource, streamResult);
            writer.flush();
            try {
				MongoXmlHandler.getInstance().DeletefetchFileXML(patCRNo); //delete all xmls for a particular crno before inserting which contain all xmls data in one. chandann by 29 aug-2018
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            MongoXmlHandler.getInstance().saveFile(patCRNo, writer.toString());

        } catch (TransformerConfigurationException e) {
            Log(Level.SEVERE, "Exception in saveWorkOrderDocument : TransformerConfigurationException: " + e.getMessageAndLocation());
            e.printStackTrace();
        } catch (TransformerException e) {
            Log(Level.SEVERE, "Exception in saveWorkOrderDocument : TransformerException: " + e.getMessageAndLocation());
            e.printStackTrace();
        }

    }

    
    public static StringWriter saveWorkOrderDocumentFTP(DocumentBuilder db,Document doc,String hospitalCode,String year,String insideyear ,String count,String patCRNo) {

    	Log(Level.INFO, "saveWorkOrderDocumentDirectlyToFTP");
   		TransformerFactory tansformerFactory=TransformerFactory.newInstance();
   		Transformer transformer=null;
   		BufferedOutputStream bos=null;
   		StringWriter writer = new StringWriter();
   		
   		try {
   			transformer = tansformerFactory.newTransformer();
   			DOMSource domSource=new DOMSource(doc);
   			
   			bos=createUpdatePatientFile(hospitalCode,year,insideyear,count,patCRNo);
   			
   			 
   			
   			Log(Level.INFO, "bos received is "+bos);
   			StreamResult streamResult=new StreamResult(bos);
   			transformer.transform(domSource, streamResult);
   			//StringWriter writer = new StringWriter();
   		     StreamResult result = new StreamResult(writer);
   		     transformer.transform(domSource, result);
   		     Log(Level.INFO, "document saved to ftp is:  "+writer.toString());
   		     Log(Level.INFO, "document saved to ftp is:  "+writer.toString());
   			
   		 // doc = db.parse(writer);
   		} catch (TransformerConfigurationException e) {
   				e.printStackTrace();
   		}
   		catch (TransformerException e) {
   				e.printStackTrace();
   			}
   		finally{
   			try {
   				if(bos!=null)
   					bos.close();
   				
   			} catch (IOException e) {
   				
   				e.printStackTrace();
   			}
   		}
   		
   		return writer;
    }
    
    
    public static void updatePatientInvestigationFile(Document doc, ResultEntryVO resultEntryVO) {
    	
    	Log(Level.INFO, "Append Requisition In XML Start");
    	
        Element patientElement = null;
        patientElement = doc.getDocumentElement();//root Element
        if (patientElement == null) {
            patientElement = doc.createElement("patient");
            doc.appendChild(patientElement);
        }

        NodeList episodeElementNodeList = patientElement.getElementsByTagName("episode");
        Element episodeElement = null;
        if (episodeElementNodeList.getLength() == 0) {

            episodeElement = doc.createElement("episode");
            episodeElement.setAttribute("number", (resultEntryVO.getPatEpisodeCode() == null) ? "0" : resultEntryVO.getPatEpisodeCode());
            episodeElement.setAttribute("visitNo", (resultEntryVO.getPatVisitNo() == null) ? "0" : resultEntryVO.getPatVisitNo());
            episodeElement.setAttribute("deptUnitCode", (resultEntryVO.getDetpUnitCode() == null) ? "0" : resultEntryVO.getDetpUnitCode());
            patientElement.appendChild(episodeElement);
        } else {
            boolean found = false;
            for (int i = 0; i < episodeElementNodeList.getLength(); i++) {
                Element episodeNodeElement = (Element) episodeElementNodeList.item(i);
                if ((episodeNodeElement.getAttributes().getNamedItem("number").getNodeValue().equals(resultEntryVO.getPatEpisodeCode())) && (episodeNodeElement.getAttributes().getNamedItem("visitNo").getNodeValue().equals(resultEntryVO.getPatVisitNo())) && (episodeNodeElement.getAttributes().getNamedItem("deptUnitCode").getNodeValue().equals(resultEntryVO.getDetpUnitCode()))) {
                    episodeElement = episodeNodeElement;
                    found = true;
                }
            }
            if (found == false) {
                episodeElement = doc.createElement("episode");
                episodeElement.setAttribute("number", resultEntryVO.getPatEpisodeCode());
                episodeElement.setAttribute("visitNo", resultEntryVO.getPatVisitNo());
                episodeElement.setAttribute("deptUnitCode", resultEntryVO.getDetpUnitCode());
                patientElement.appendChild(episodeElement);
            }

        }

        NodeList requisitionElementNodeList = episodeElement.getElementsByTagName("requisition");
        Element requisitionElement = null;
        if (requisitionElementNodeList.getLength() == 0) {
            requisitionElement = doc.createElement("requisition");
            requisitionElement.setAttribute("number", resultEntryVO.getRequisitionNo());
            requisitionElement.setAttribute("date", resultEntryVO.getRequisitionDate());
            episodeElement.appendChild(requisitionElement);
        } else {
            boolean found = false;
            for (int i = 0; i < requisitionElementNodeList.getLength(); i++) {
                Element requisitionNodeElement = (Element) requisitionElementNodeList.item(i);
                if (requisitionNodeElement.getAttributes().getNamedItem("number").getNodeValue().equals(resultEntryVO.getRequisitionNo())) {
                    requisitionElement = requisitionNodeElement;
                    found = true;
                }
            }
            if (found == false) {
                requisitionElement = doc.createElement("requisition");
                requisitionElement.setAttribute("number", resultEntryVO.getRequisitionNo());
                requisitionElement.setAttribute("date", resultEntryVO.getRequisitionDate());
                episodeElement.appendChild(requisitionElement);
            }
        }

        // Laboratory Details entry	
        NodeList laboratoryElementNodeList = requisitionElement.getElementsByTagName("laboratory");
        Element laboratoryElement = null;
        if (laboratoryElementNodeList.getLength() == 0) {
            laboratoryElement = doc.createElement("laboratory");
            laboratoryElement.setAttribute("code", resultEntryVO.getLaboratoryCode());
            laboratoryElement.setAttribute("name", resultEntryVO.getLaboratoryName());
            requisitionElement.appendChild(laboratoryElement);
        } else {
            boolean found = false;
            for (int i = 0; i < laboratoryElementNodeList.getLength(); i++) {
                Element laboratoryNodeElement = (Element) laboratoryElementNodeList.item(i);
                if (laboratoryNodeElement.getAttributes().getNamedItem("code").getNodeValue().equals(resultEntryVO.getLaboratoryCode())) {
                    laboratoryElement = laboratoryNodeElement;
                    found = true;
                }
                if (found == false) {
                    laboratoryElement = doc.createElement("laboratory");
                    laboratoryElement.setAttribute("code", resultEntryVO.getLaboratoryCode());
                    laboratoryElement.setAttribute("name", resultEntryVO.getLaboratoryName());
                    requisitionElement.appendChild(laboratoryElement);
                }
            }
        }

        //test Details entry	
        NodeList testElementNodeList = laboratoryElement.getElementsByTagName("test");
        Element testElement = null;
        if (testElementNodeList.getLength() == 0) {
            //testElement=doc.createElement("test");
            //testElement.setAttribute("code", resultEntryVO.getTestCode());
            //testElement.setAttribute("name", resultEntryVO.getTestName());
            //LOGGER_INV.log(Level.INFO,""+resultEntryVO.getTestDocument());
            Document testDocument = resultEntryVO.getTestDocument();
            Node testtemplateDocument = testDocument.getElementsByTagName("test").item(0);

            Node cloneNode = doc.adoptNode(testtemplateDocument);;
            //testElement.appendChild(cloneNode);
            laboratoryElement.appendChild(cloneNode);
        } else {
            boolean found = false;
            for (int i = 0; i < testElementNodeList.getLength(); i++) {
                Element testNodeElement = (Element) testElementNodeList.item(i);
                // LOGGER_INV.log(Level.INFO,testNodeElement.getAttributes().getNamedItem("requisitionDNo").getNodeValue()+"  "+testNodeElement.getAttributes().getNamedItem("code").getNodeValue());
                if (testNodeElement.getAttributes().getNamedItem("requisitionDNo").getNodeValue().equals(resultEntryVO.getRequisitionDNo()) && testNodeElement.getAttributes().getNamedItem("code").getNodeValue().equals(resultEntryVO.getTestCode())) {
                    testElement = testNodeElement;
                    found = true;
                }
            }
            if (found == false) {
                //testElement=doc.createElement("test");
                //testElement.setAttribute("code", resultEntryVO.getTestCode());
                //testElement.setAttribute("name", resultEntryVO.getTestName());
                Document testDocument = resultEntryVO.getTestDocument();
                
                //Log(Level.INFO, "testcode=========="+resultEntryVO.getTestCode());
                
                Node testtemplateDocument = testDocument.getElementsByTagName("test").item(0);

                Node cloneNode = doc.adoptNode(testtemplateDocument);;
                //testElement.appendChild(cloneNode);
                laboratoryElement.appendChild(cloneNode);

            } else {
                laboratoryElement.removeChild(testElement);
                Document testDocument = resultEntryVO.getTestDocument();
                Node testtemplateDocument = testDocument.getElementsByTagName("test").item(0);

                Node cloneNode = doc.adoptNode(testtemplateDocument);;
                //testElement.appendChild(cloneNode);
                //LOGGER_INV.log(Level.INFO,cloneNode+" ---- "+laboratoryElement);
                laboratoryElement.appendChild(cloneNode);
            }

        }
    }

    public static Document getWorkOrderDocument(DocumentBuilder db, Document doc, String patCRNo) throws IOException, SAXException, ParserConfigurationException, JAXBException {

        // doc=getWorkOrderDocumentDirectFromFTP(db, doc, requisitionTypeCode, patCRNo, requisitionNo);
        String file = MongoXmlHandler.getInstance().fetchFile(patCRNo);
        if (file == null) {
            doc = db.newDocument();
           // Element rootElement = doc.createElement("patient");
            // doc.appendChild(rootElement);
        } else {
        	
        	
            // convert XML to Document
           // file = "<patient><episode deptUnitCode=\"0\" number=\"0\" visitNo=\"0\"><requisition date=\"2015-05-15 11:11:41\" number=\"10007150515100002\"><laboratory code=\"10007\" name=\"\"/></requisition></episode><episode deptUnitCode=\"\" number=\"\" visitNo=\"\"><requisition date=\"2015-05-15 11:11:41\" number=\"10007150515100002\"><laboratory code=\"10007\" name=\"\"><test code=\"10001\" requisitionDNo=\"1000715051510000201\" sessionNo=\"0\" testName=\"\"><testtemplate labTestCode=\"10001\"><table type=\"1\" width=\"100%\"/><table type=\"3\" width=\"100%\"/><table maxColumns=\"3\" type=\"2\" width=\"100%\"><rowDetails rowNo=\"1\"><columnDetails align=\"0\" class=\"tdfont\" colNo=\"1\" width=\"33%\"><table><tr><td><element align=\"0\" bold=\"0\" id=\"eleID_template#100011002\" idC=\"label\" name=\"\" type=\"\" underline=\"0\" value=\"blood\"/></td></tr></table></columnDetails><columnDetails align=\"center\" class=\"tdfonthead\" colNo=\"2\" width=\"33%\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td><element id=\"\" idC=\"\" name=\"\" type=\"\" value=\"\"/></td></tr></table></columnDetails><columnDetails align=\"left\" class=\"tdfonthead\" colNo=\"3\" width=\"33%\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td><element align=\"0\" defaultValue=\"-\" eventName=\"0\" eventValidationString=\"0\" functionName=\"\" idC=\"Select\" isPrintable=\"0\" name=\"1000715051510000201#0#template#100011002\" type=\"\" value=\"\"><options><option label=\"para 1\" value=\"4\"/><option label=\"para 2\" value=\"5\"/></options><rangetag rangefrom=\"from\" rangefromunit=\"rangefromunit\" rangesymbol=\"\" rangeto=\"rangeto\" rangetounit=\"tounit\"/></element></td></tr></table></columnDetails></rowDetails><rowDetails rowNo=\"2\"><columnDetails align=\"0\" class=\"tdfont\" colNo=\"1\" width=\"33%\"><table><tr><td><element align=\"0\" bold=\"0\" id=\"eleID_template#100011003\" idC=\"label\" name=\"\" type=\"\" underline=\"0\" value=\"haemoglobin\"/></td></tr></table></columnDetails><columnDetails align=\"center\" class=\"tdfonthead\" colNo=\"2\" width=\"33%\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td><element id=\"\" idC=\"\" name=\"\" type=\"\" value=\"\"/></td></tr></table></columnDetails><columnDetails align=\"left\" class=\"tdfonthead\" colNo=\"3\" width=\"33%\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td><element align=\"0\" defaultValue=\"-\" eventName=\"0\" eventValidationString=\"0\" idC=\"input\" isPrintable=\"0\" maxlength=\"\" name=\"1000715051510000201#0#template#100011003\" size=\"\" type=\"text\" value=\"\"><rangetag rangefrom=\"from\" rangefromunit=\"rangefromunit\" rangesymbol=\"\" rangeto=\"rangeto\" rangetounit=\"tounit\"/></element></td></tr></table></columnDetails></rowDetails><rowDetails rowNo=\"3\"><columnDetails align=\"0\" class=\"tdfont\" colNo=\"1\" width=\"33%\"><table><tr><td><element align=\"0\" bold=\"0\" id=\"eleID_template#100011004\" idC=\"label\" name=\"\" type=\"\" underline=\"0\" value=\"rbc\"/></td></tr></table></columnDetails><columnDetails align=\"center\" class=\"tdfonthead\" colNo=\"2\" width=\"33%\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td><element id=\"\" idC=\"\" name=\"\" type=\"\" value=\"\"/></td></tr></table></columnDetails><columnDetails align=\"left\" class=\"tdfonthead\" colNo=\"3\" width=\"33%\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tr><td><element align=\"0\" defaultValue=\"-\" eventName=\"0\" eventValidationString=\"0\" functionName=\"\" idC=\"Select\" isPrintable=\"0\" name=\"1000715051510000201#0#template#100011004\" type=\"\" value=\"\"><options><option label=\"WHITE\" value=\"1\"/><option label=\"RED\" value=\"2\"/></options><rangetag rangefrom=\"from\" rangefromunit=\"rangefromunit\" rangesymbol=\"\" rangeto=\"rangeto\" rangetounit=\"tounit\"/></element></td></tr></table></columnDetails></rowDetails></table></testtemplate></test></laboratory></requisition></episode></patient>";

            //doc = builder.parse(new InputSource(new StringReader(file)));  
            InputSource is = new InputSource();
           // ByteArrayInputStream is1 = new ByteArrayInputStream(file.getBytes("UTF-8"));
            is.setCharacterStream(new StringReader(file));

            doc = db.parse(is);
            
        }
        //  Log(Level.INFO, file);
        return doc;
    }

    // needs to change chandanFTP
    public static Document getWorkOrderDocumentFTP(DocumentBuilder db, Document doc, String patCRNo,String hosCode) throws IOException, SAXException, ParserConfigurationException, JAXBException {

        // doc=getWorkOrderDocumentDirectFromFTP(db, doc, requisitionTypeCode, patCRNo, requisitionNo);
    	
		String ftpfilepath="";
		InputStream ios=null;
		
		 String crNo=patCRNo;
         //Log(Level.INFO, crNo.substring(5,7));
		 String year=    crNo.substring(5,7);
         
         String insideyear=PGDataHelper.getInsideYear(crNo);
         String count=PGDataHelper.getcount(crNo);
         
         Log(Level.INFO, "crNo : "+crNo+ " insideyear : "+insideyear+ " hoscode :"+hosCode); 
         
     	ftpfilepath+=PropertiesHelper.getFTPConnectionURI()+"/"+hosCode +"/"+"20"+year+"/"+insideyear+"/"+count+"/"+patCRNo+"/"+patCRNo+".xml";
     	URL urlftp=null;
		URLConnection urlc=null;
		
		
		try
		{
			Log(Level.INFO, "ftpfilepath Invest common process :"+ftpfilepath);
			urlftp =new URL(ftpfilepath);
			urlc=	urlftp.openConnection();
			ios=urlc.getInputStream();
			
//			int data = ios.read();  
//            while (data != -1) {  
//                Log(Level.INFO, (char) data);  
//                data = ios.read();  
//            }  
//            Log(Level.INFO, ios.toString());  
//			
			doc = db.parse(ios);
			
			
		}
		catch(Exception ex)
		{
			Log(Level.INFO, "<!-- CreateRequisitionDirectory -->");
			//ex.printStackTrace();
		}
		finally
		{
			if(doc==null)
			{
				doc=db.newDocument();
			}
			
			if(ios!=null)
			{
				try {
					ios.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
		return doc;
		
         
       
    }
    
    public static void updatePatientInvestigationFileForGroup(Document doc,
            ResultEntryVOGroupByValidatedBy voGroupByValidatedBy) {
    	Log(Level.INFO, "Append Group Requisition In XML Start");

    	Element patientElement = null;
        patientElement = doc.getDocumentElement();//root Element
        if (patientElement == null) {
            patientElement = doc.createElement("patient");
            doc.appendChild(patientElement);
        }

        NodeList episodeElementNodeList = patientElement.getElementsByTagName("episode");
        Element episodeElement = null;
        if (episodeElementNodeList.getLength() == 0) {

            episodeElement = doc.createElement("episode");
            episodeElement.setAttribute("number", (voGroupByValidatedBy.getPatEpisodeCode() == null) ? "0" : voGroupByValidatedBy.getPatEpisodeCode());
            episodeElement.setAttribute("visitNo", (voGroupByValidatedBy.getPatVisitNo() == null) ? "0" : voGroupByValidatedBy.getPatVisitNo());
            episodeElement.setAttribute("deptUnitCode", (voGroupByValidatedBy.getDetpUnitCode() == null) ? "0" : voGroupByValidatedBy.getDetpUnitCode());
            patientElement.appendChild(episodeElement);
        } else {
            //Log(Level.INFO, "Update Episode Tag XML");
            boolean found = false;
            for (int i = 0; i < episodeElementNodeList.getLength(); i++) {
                Element episodeNodeElement = (Element) episodeElementNodeList.item(i);
                if ((episodeNodeElement.getAttributes().getNamedItem("number").getNodeValue().equals(voGroupByValidatedBy.getPatEpisodeCode())) && (episodeNodeElement.getAttributes().getNamedItem("visitNo").getNodeValue().equals(voGroupByValidatedBy.getPatVisitNo())) && (episodeNodeElement.getAttributes().getNamedItem("deptUnitCode").getNodeValue().equals(voGroupByValidatedBy.getDetpUnitCode()))) {
                    episodeElement = episodeNodeElement;
                    found = true;
                }
            }
            if (found == false) {
                episodeElement = doc.createElement("episode");
                episodeElement.setAttribute("number", voGroupByValidatedBy.getPatEpisodeCode());
                episodeElement.setAttribute("visitNo", voGroupByValidatedBy.getPatVisitNo());
                episodeElement.setAttribute("deptUnitCode", voGroupByValidatedBy.getDetpUnitCode());
                patientElement.appendChild(episodeElement);
            }

        }

        NodeList requisitionElementNodeList = episodeElement.getElementsByTagName("requisition");
        Element requisitionElement = null;
        if (requisitionElementNodeList.getLength() == 0) {
            requisitionElement = doc.createElement("requisition");
            requisitionElement.setAttribute("number", voGroupByValidatedBy.getRequisitionNo());
            requisitionElement.setAttribute("date", voGroupByValidatedBy.getRequisitionDate());
            episodeElement.appendChild(requisitionElement);
        } else {
            boolean found = false;
            for (int i = 0; i < requisitionElementNodeList.getLength(); i++) {
                Element requisitionNodeElement = (Element) requisitionElementNodeList.item(i);
                if (requisitionNodeElement.getAttributes().getNamedItem("number").getNodeValue().equals(voGroupByValidatedBy.getRequisitionNo())) {
                    requisitionElement = requisitionNodeElement;
                    found = true;
                }
            }
            if (found == false) {
                requisitionElement = doc.createElement("requisition");
                requisitionElement.setAttribute("number", voGroupByValidatedBy.getRequisitionNo());
                requisitionElement.setAttribute("date", voGroupByValidatedBy.getRequisitionDate());
                episodeElement.appendChild(requisitionElement);

            }
        }

        // Laboratory Details entry	
        NodeList laboratoryElementNodeList = requisitionElement.getElementsByTagName("laboratory");
        Element laboratoryElement = null;
        if (laboratoryElementNodeList.getLength() == 0) {
            laboratoryElement = doc.createElement("laboratory");
            laboratoryElement.setAttribute("code", voGroupByValidatedBy.getLaboratoryCode());
            laboratoryElement.setAttribute("name", voGroupByValidatedBy.getLaboratoryName());
            requisitionElement.appendChild(laboratoryElement);
        } else {
            boolean found = false;
            for (int i = 0; i < laboratoryElementNodeList.getLength(); i++) {
                Element laboratoryNodeElement = (Element) laboratoryElementNodeList.item(i);
                if (laboratoryNodeElement.getAttributes().getNamedItem("code").getNodeValue().equals(voGroupByValidatedBy.getLaboratoryCode())) {
                    laboratoryElement = laboratoryNodeElement;
                    found = true;
                }
                if (found == false) {
                    laboratoryElement = doc.createElement("laboratory");
                    laboratoryElement.setAttribute("code", voGroupByValidatedBy.getLaboratoryCode());
                    laboratoryElement.setAttribute("name", voGroupByValidatedBy.getLaboratoryName());
                    requisitionElement.appendChild(laboratoryElement);
                }
            }
        }

        //test Details entry	
        NodeList testElementNodeList = laboratoryElement.getElementsByTagName("testgroup");
        Element testElement = null;
        if (testElementNodeList.getLength() == 0) {
            //LOGGER_INV.log(Level.INFO,""+voGroupByValidatedBy.getGroupTemplateDocument());
            Document testDocument = voGroupByValidatedBy.getGroupTemplateDocument();
            if (testDocument != null && testDocument.getElementsByTagName("testgroup") != null && testDocument.getElementsByTagName("testgroup").getLength() != 0) {
                Node testtemplateDocument = testDocument.getElementsByTagName("testgroup").item(0);

                Node cloneNode = doc.adoptNode(testtemplateDocument);;
                //testElement.appendChild(cloneNode);
                laboratoryElement.appendChild(cloneNode);
            }
        } else {
            boolean found = false;
            for (int i = 0; i < testElementNodeList.getLength(); i++) {
                Element testNodeElement = (Element) testElementNodeList.item(i);
                //LOGGER_INV.log(Level.INFO,testNodeElement.getAttributes().getNamedItem("requisitionNo").getNodeValue()+"  "+testNodeElement.getAttributes().getNamedItem("groupcode").getNodeValue());
                if (testNodeElement.getAttributes().getNamedItem("requisitionNo").getNodeValue().equals(voGroupByValidatedBy.getRequisitionNo()) && testNodeElement.getAttributes().getNamedItem("groupcode").getNodeValue().equals(voGroupByValidatedBy.getGroupCode())) {
                    testElement = testNodeElement;
                    found = true;
                }
            }
            if (found == false) {
                Document testDocument = voGroupByValidatedBy.getGroupTemplateDocument();
                if (testDocument != null && testDocument.getElementsByTagName("testgroup") != null && testDocument.getElementsByTagName("testgroup").getLength() != 0) {
                    Node testtemplateDocument = testDocument.getElementsByTagName("testgroup").item(0);

                    Node cloneNode = doc.adoptNode(testtemplateDocument);;
                    laboratoryElement.appendChild(cloneNode);
                }

            } else {
                laboratoryElement.removeChild(testElement);
                Document testDocument = voGroupByValidatedBy.getGroupTemplateDocument();
                if (testDocument != null && testDocument.getElementsByTagName("testgroup") != null && testDocument.getElementsByTagName("testgroup").getLength() != 1) {
                    Node testtemplateDocument = testDocument.getElementsByTagName("testgroup").item(0);

                    Node cloneNode = doc.adoptNode(testtemplateDocument);;
                    //testElement.appendChild(cloneNode);
                    //LOGGER_INV.log(Level.INFO,cloneNode+" ---- "+laboratoryElement);
                    laboratoryElement.appendChild(cloneNode);
                }
            }

        }

    }

    private static void Log(Level level, String msg) {
        ServiceLogger.Log(XMLFileHandler.class.getName(), level, msg);
    }

    private static void Log(Level level, Exception e) {
        ServiceLogger.Log(XMLFileHandler.class.getName(), level, e);
    }

    
    public static BufferedOutputStream createUpdatePatientFile(String hospitalCode,String year,String insideyear,String count,String directory)
   	{
       	 // Calendar calendar = Calendar.getInstance();
       	  
   		boolean directoryExists=true;
   		//String directoryMethod=InvestigationStaticConfigurator.patientcreatefileftpaddress+"/"+directory;
   		//String directoryMethod=PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+""+"20"+year+"/"+insideyear+ "/"+directory;
   		String directoryMethod=PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+"20"+year+"/"+insideyear+"/"+count+ "/"+directory;
   		
   		BufferedOutputStream bos = null ;
   		URL urlftp=null;
   		URLConnection urlc=null;
   		try
   		{
   		//urlftp =new URL(InvestigationStaticConfigurator.patientretrievefileftpaddress+"/"+directory+"/"+directory+".xml");
   			//urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+""+"20"+year+"/"+insideyear+"/"+directory+"/"+directory+".xml");
   			
   					
   			urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+"20"+year+"/"+insideyear+"/"+count+"/"+directory+"/"+directory+".xml");
   			urlc=	urlftp.openConnection();
   		}
   		catch(Exception ex)
   		{
   			ex.printStackTrace();
   		}
   		
   		 
   		
   		if(bos==null)
   		{
   			Log(Level.INFO, "BOS is null");
   			String[] folder=directoryMethod.replace("/", "#").split("#");
   			
   			
   			if(folder[2]!=null && folder[2].contains("%40") && folder[2].replace("@", "#").split("#").length>1)
   			{
   				String data=folder[2];
   				data=data.replaceAll("%40", "@");
   				createFTPDirectoryStructure(data.replace("@", "#").split("#")[2],folder);
   			}
   			else if(folder[2]!=null && folder[2].replace("@", "#").split("#").length>1)
   			{
   				String data=folder[2];
   				data=data.replaceAll("%40", "@");
   				createFTPDirectoryStructure(data.replace("@", "#").split("#")[1],folder);
   			}
   			else
   			{
   				createFTPDirectoryStructure(folder[2],folder);
   			}
   			Log(Level.INFO, "File Structure created");
   			//createBlankXmlFile(InvestigationStaticConfigurator.patientretrievefileftpaddress+"/"+directory+"/"+directory+".xml");
   			Log(Level.INFO, "File XML created");
   			try
   			{
   			//urlftp =new URL(InvestigationStaticConfigurator.patientretrievefileftpaddress+"/"+directory+"/"+directory+".xml");
   			//urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+ "20"+year+"/"+insideyear+"/"+directory+"/"+directory+".xml");
   		   	//	URLConnection urlc1=null;

   				//urlftp =new URL(PropertiesHelper.getFTPConnectionURI()+"/"+hospitalCode+"/"+ "20"+year+"/"+insideyear+"/"+count+"/"+directory+"/"+directory+".xml");
   			
   		//	urlc1=	urlftp.openConnection();
   			bos=new BufferedOutputStream(urlc.getOutputStream());
   			Log(Level.INFO, "BOS reconstructed XML created");
   			}
   			catch (Exception e) 
   			{
   				e.printStackTrace();
   			}
   		}
   				
   		return bos;
   		
   	}
    
    
    private synchronized static void createFTPDirectoryStructure(String ftpserver, String[] folders) {
		try {
			
			JakartaFtpWrapper ftp = new JakartaFtpWrapper();
			 
			
			String ftpUserName=PropertiesHelper.getFTPConnectionUsername();
			String ftpUserPassword=PropertiesHelper.getFTPConnectionPassword();
			
			if (ftp.connectAndLogin(PropertiesHelper.getFTPConnectionIP(), ftpUserName, ftpUserPassword))
			{
				try 
				{
					//ftp.setPassiveMode(true);
				//	ftp.changeWorkingDirectory(folders[3]);
					for(int i=3;i<folders.length;i++)
					{
						ftp.mkd(folders[i]);
						ftp.changeWorkingDirectory(folders[i]);
					}
					
				} 
				catch (Exception ftpe)
				{
					ftpe.printStackTrace();
				}
				finally 
				{
					ftp.logout();
					ftp.disconnect();
				}
			} 
			else
			{
				Log(Level.INFO, "Unable to connect to" + ftpserver);
			}
			
			
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
	}
    
}
