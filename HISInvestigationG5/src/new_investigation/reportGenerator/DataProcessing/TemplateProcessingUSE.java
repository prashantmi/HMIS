 
package new_investigation.reportGenerator.DataProcessing;

 
 
//import hisglobal.Entry;
import new_investigation.reportGenerator.DataHelper.Config;
import new_investigation.reportGenerator.DataHelper.PGDataHelper;
import new_investigation.reportGenerator.DataHelper.PropertiesHelper;
import new_investigation.reportGenerator.Logging.ServiceLogger;
import new_investigation.reportGenerator.TemplateHelper.FourStringObject;
import new_investigation.reportGenerator.TemplateHelper.TriStringObject;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVO;
import new_investigation.reportGenerator.TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;
import new_investigation.reportGenerator.TemplateHelper.vo.TemplateQueryParameterVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.WordUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TemplateProcessingUSE {
	private static List<String> lstEnteredValues=new ArrayList<String>();

    public static List<ResultEntryVOGroupByValidatedBy> groupSelectedWorkOrders(
            List<ResultEntryVOGroupByValidatedBy> selectedWorkOrderList) {
        // TODO Auto-generated method stub
        //	LOGGER_INV.log(Level.INFO,"INVESTIGATION COMMON PROCESS: groupSelectedWorkOrders ");
        List<ResultEntryVOGroupByValidatedBy> newselectedWorkOrderList = new ArrayList<ResultEntryVOGroupByValidatedBy>();
        ResultEntryVOGroupByValidatedBy newResultEntryVOGroupByValidatedBy = null;
        Map<String, Integer> selectedMapDetails = new HashMap<String, Integer>();
        
        Map<String, Integer> selectedDynamicGroupDetail = new HashMap<String, Integer>();		
		Map<String, ResultEntryVO> reqDNoTestMap = new HashMap<String, ResultEntryVO>();
		List<ResultEntryVO> rvoWithDynamic = new ArrayList<ResultEntryVO>();
        
        try {
        	
        	
        	for(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy:selectedWorkOrderList)
    		{
    			for(ResultEntryVO resultEntryVO: resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy())
    			{
    				String s = resultEntryVO.getGroupCode() + resultEntryVO.getDynamicTemplatePrintedGroup();
    				
    				if(selectedDynamicGroupDetail.containsKey(s)) {
    					resultEntryVO.setDoCreateTemplate(false);
    					rvoWithDynamic.add(resultEntryVO);
    					reqDNoTestMap.put(resultEntryVO.getRequisitionNo()+resultEntryVO.getGroupCode()+resultEntryVO.getTestCode(), resultEntryVO);
    					
    				}	
    				else if(resultEntryVO.getDynamicTemplatePrintedGroup().equals("1"))
    				{
    					//resultEntryVO.setTestName(resultEntryVO.getGroupName());
    					selectedDynamicGroupDetail.put(resultEntryVO.getGroupCode() + "1", 1);
    					rvoWithDynamic.add(resultEntryVO);
    					reqDNoTestMap.put(resultEntryVO.getRequisitionNo()+resultEntryVO.getGroupCode()+resultEntryVO.getTestCode(), resultEntryVO);
    				}
    			}
    		}
        	
            //	LOGGER_INV.log(Level.INFO,"INVESTIGATION COMMON PROCESS: groupSelectedWorkOrders step 1");
            for (ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy : selectedWorkOrderList) {
//                if (resultEntryVOGroupByValidatedBy.getGroupCode() != null) {
//                     
//                    int workOrderGroupIndex = newselectedWorkOrderList.size();
//                    newselectedWorkOrderList.add(resultEntryVOGroupByValidatedBy);
//                    int resultEntryVOIndex = 0;
//                    for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
//                        getResultEntryTemplatedocument(resultEntryVO, resultEntryVOIndex, newselectedWorkOrderList.size() - 1);
//                        resultEntryVOIndex++;
//                    }
//
//                    getTestGroupTemplatedocument(resultEntryVOGroupByValidatedBy, workOrderGroupIndex);
//
//                } else {
                    //	LOGGER_INV.log(Level.INFO,"INVESTIGATION COMMON PROCESS: groupSelectedWorkOrders step2");
                    String key = resultEntryVOGroupByValidatedBy.getPatCRNo() + resultEntryVOGroupByValidatedBy.getPatEpisodeCode() + resultEntryVOGroupByValidatedBy.getPatVisitNo() + resultEntryVOGroupByValidatedBy.getRequisitionNo();//+resultEntryVOGroupByValidatedBy.getSampleName();//.getSampleNo();
                    
                //    Log(Level.INFO, "keyy:"+key);
                      

                    if (selectedMapDetails.containsKey(key)) {
                        newResultEntryVOGroupByValidatedBy = newselectedWorkOrderList.get(selectedMapDetails.get(key));
                        for (ResultEntryVO resultEntryVO : resultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
                            // get the template for the workOrder ResultEntry 
                            getResultEntryTemplatedocument(resultEntryVO, newResultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().size(), selectedMapDetails.get(key), reqDNoTestMap);
                            newResultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy().add(resultEntryVO);
                        }

                    } else {
                        newResultEntryVOGroupByValidatedBy = resultEntryVOGroupByValidatedBy;
                        newselectedWorkOrderList.add(newResultEntryVOGroupByValidatedBy);
                        selectedMapDetails.put(key, newselectedWorkOrderList.size() - 1);
                        int resultEntryVOIndex = 0;
                        for (ResultEntryVO resultEntryVO : newResultEntryVOGroupByValidatedBy.getResultEntryVOListValidatedBy()) {
                            getResultEntryTemplatedocument(resultEntryVO, resultEntryVOIndex, newselectedWorkOrderList.size() - 1, reqDNoTestMap);
                            resultEntryVOIndex++;
                        }

                    }
                }

          //  }
        } catch (Exception e) {
            e.printStackTrace();
        }

        selectedWorkOrderList = null;
        return newselectedWorkOrderList;

    }

    public static synchronized void getResultEntryTemplatedocument(ResultEntryVO resultEntryVO, int index, int workOrderGroupIndex, Map<String, ResultEntryVO> reqDNoMap) {

        try {
        	
        	boolean isGroupWithDynamicTemplate = false;
        	
        	String dynamicTemplateID = null;
			String genericTemplateId=PropertiesHelper.getGenericTemplate(); //make config value

        	if(resultEntryVO.getDynamicTemplatePrintedGroup() != null && resultEntryVO.getDynamicTemplatePrintedGroup().equals("1"))
    		{
    			// it is a group and to be printed with dynamic template 
    			isGroupWithDynamicTemplate = true;
    			dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(resultEntryVO.getGroupCode(), resultEntryVO.getHospitalCode(), isGroupWithDynamicTemplate, resultEntryVO.getLaboratoryCode());
    		}
        	else
        	{
        		isGroupWithDynamicTemplate = false;
        		dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(resultEntryVO.getTestCode(), null, isGroupWithDynamicTemplate, resultEntryVO.getLaboratoryCode());
        	}
        	
        	//check if dept entry. get dept id from printing mst
        	if(resultEntryVO.getIsDeptEntry() != null && resultEntryVO.getIsDeptEntry().equals("1"))
    		{
    			// it is a group and to be printed with dynamic template 
    			isGroupWithDynamicTemplate = false;
    			dynamicTemplateID = PGDataHelper.getInstance().getDeptTemplateStatus(resultEntryVO.getTestCode(), resultEntryVO.getHospitalCode(), isGroupWithDynamicTemplate, resultEntryVO.getLaboratoryCode());
    		}
        	
    	  	boolean isPrintWithDynamicTemplate = false;
    	  	String testC = resultEntryVO.getTestCode();
    	  	if(dynamicTemplateID != null && !dynamicTemplateID.isEmpty())
        	{
    	  		isPrintWithDynamicTemplate = true;
    	  		resultEntryVO.setPrintWithDynamicTemplate("1");
    	  		testC = dynamicTemplateID;
        	}
    	  	else
    	  	{
    	  		if(resultEntryVO.getIsDeptEntry() != null && resultEntryVO.getIsDeptEntry().equals("1"))
    	  		{
    	  			isPrintWithDynamicTemplate = true;
        	  		resultEntryVO.setPrintWithDynamicTemplate("1");
        	  		testC = "1";
    	  			
    	  		}
    	  		else
    	  		resultEntryVO.setPrintWithDynamicTemplate("0");
    	  	}
        	
        	
            //LOGGER_INV.log(Level.INFO,"getResultEntryTemplatedocument");
            Transformer transformer = null;
            
            if(!isPrintWithDynamicTemplate)
            {
            	transformer = PGDataHelper.getInstance().getTransformerObject(Config.XSL_RESULTENTRYSTYLESHEET);
            }
            else
            {
            	transformer = PGDataHelper.getInstance().getTransformerObject(Config.XSL_PRINTINGSTYLESHEET_BODY); //tansformerFactory.newTransformer(new StreamSource(path+"ResultEntryStyleSheet.xsl"));
            }
            
                      
            //InvestigationDocumentCacheManager documentCacheManager =InvestigationDocumentCacheManager.getInstance();
            Node testDocumentNode = PGDataHelper.getInstance().getTemplateNodeObj(Config.XML_TESTTEMPLATE, testC, isPrintWithDynamicTemplate);
         
            Node genericTestDocumentNode = PGDataHelper.getInstance().getTemplateNodeObj(Config.XML_TESTTEMPLATE, genericTemplateId, isPrintWithDynamicTemplate);
            
            
            if(resultEntryVO.getIsDeptEntry() != null && resultEntryVO.getIsDeptEntry().equals("1") && testDocumentNode == null)
            {
            	
            	;
            	
            }
            
            else  if (testDocumentNode == null) {
                Log(Level.SEVERE, "Template Not found for Test: " + resultEntryVO.getTestCode());
                Log(Level.INFO, "Template Not found for Test: " + resultEntryVO.getTestCode());
                resultEntryVO.setTemplateDocumentString(null);
                return;
            }
            else
            	;
            String testCode = resultEntryVO.getTestCode();
    	  	String labCode = resultEntryVO.getLaboratoryCode();
    	  	String hospitalCode = resultEntryVO.getHospitalCode();
    		boolean withStandardRanges = PGDataHelper.getInstance().getPrintWithStandardRanges(testCode, labCode, hospitalCode);

    	  	resultEntryVO.setPrintWithStandardRanges(withStandardRanges);
    	  	//chandu
            Document testDocument = PGDataHelper.getInstance().getNewDocument();
            Element rootNode = testDocument.createElement("test");
            rootNode.setAttribute("code", resultEntryVO.getTestCode());
            rootNode.setAttribute("sessionNo", "2");//resultEntryVO.getSessionId());
            rootNode.setAttribute("requisitionDNo", resultEntryVO.getRequisitionDNo());
            String testString = "";

            testString = "Test Name: <strong>" + resultEntryVO.getTestName() + "</strong> Sample Name: <strong>" + WordUtils.capitalize(resultEntryVO.getSampleName()) + ""
                    + "</strong> )" + " Lab No. <strong> ;" + resultEntryVO.getLabNo() + "</strong> ";

            rootNode.setAttribute("testName", resultEntryVO.getTestName());
            rootNode.setAttribute("sampleName", resultEntryVO.getSampleName());
            rootNode.setAttribute("labNo", resultEntryVO.getLabNo());
            //rootNode.setAttribute("sampleName", resultEntryVO.getSampleName());
            // LOGGER_INV.log(Level.INFO,"requisitionDNo::"+resultEntryVO.getRequisitionDNo());
            testDocument.appendChild(rootNode);
            
            if (testDocumentNode != null)
            rootNode.appendChild(testDocument.importNode(testDocumentNode, true));
            
            
            if(resultEntryVO.getIsDeptEntry().equals("1") && genericTestDocumentNode!=null)
            	  rootNode.appendChild(testDocument.importNode(genericTestDocumentNode, true));
            // LOGGER_INV.log(Level.INFO,testDocument.getTextContent());

            resultEntryVO.setTestDocument(testDocument);
            processTheTestDocument(resultEntryVO, testDocument, reqDNoMap);

            // LOGGER_INV.log(Level.INFO,"getResultEntryTemplatedocument + 2");     
            TemplateQueryParameterVO templateQueryParameterVO = new TemplateQueryParameterVO();
            // LOGGER_INV.log(Level.INFO,"getResultEntryTemplatedocument + 2.1");    
            DataHandler.populate(templateQueryParameterVO, resultEntryVO);
	  // LOGGER_INV.log(Level.INFO,"getResultEntryTemplatedocument + 2.2");

            //Commented by Siddharth
            // Date: 13/04/2015
            // Use: It is used to process the queryValue element. So the value of the query is executed and put in the template.
            //InvRequisitionFormDATA.processTheDatabaseElements(testDocument.getFirstChild(), templateQueryParameterVO, null);
            //  LOGGER_INV.log(Level.INFO,"getResultEntryTemplatedocument + 3");   
            templateQueryParameterVO = null;

            
            //code modified till here 
            Source domSource = new DOMSource(testDocument);
            
            
            
            java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
            StreamResult streamResult = new StreamResult(baos);
            
         // Siddharth
            // for printlng xml
            javax.xml.transform.TransformerFactory tfactory = TransformerFactory.newInstance();
            javax.xml.transform.Transformer xform = tfactory.newTransformer();
            xform.setParameter("workOrder", resultEntryVO.getRequisitionDNo());
            xform.setParameter("workOrderIndex", index);
            xform.setParameter("multisessionid", "242424");//resultEntryVO.getSessionId());
            xform.setParameter("workOrderGroupIndex", workOrderGroupIndex);
            java.io.StringWriter writer = new java.io.StringWriter();
            StreamResult result = new javax.xml.transform.stream.StreamResult(writer);
            xform.transform(domSource, result);
          //   Log(Level.INFO, writer.toString());
          
            
         //   Log(Level.INFO, resultEntryVO.getRequisitionDNo() + " " + index + " " + resultEntryVO.getSessionId() + " " + workOrderGroupIndex);
            transformer.setParameter("workOrder", resultEntryVO.getRequisitionDNo());
            transformer.setParameter("workOrderIndex", index);
            transformer.setParameter("multisessionid", "242424");//resultEntryVO.getSessionId());
            transformer.setParameter("workOrderGroupIndex", workOrderGroupIndex);
        //    transformer.transform(domSource, streamResult);
            resultEntryVO.setTemplateDocumentString(baos.toString());

            
          //  resultEntryVO.setTemplateDocumentString(baos.toString());
        } catch (Exception Ex) {
            Ex.printStackTrace();
        }

    }

    public static void processTheTestDocument(ResultEntryVO resultEntryVO, Document testDocument, Map<String, ResultEntryVO> dmap) {
        //	
        //Map resultEntryDataMap= ResultEntryDATA.getResultEntryData(resultEntryVO);
        //List<Entry>  mandatoryInfoDtlList=(List<Entry>)resultEntryDataMap.get("mandatoryInfoDtlList");
    	lstEnteredValues=new ArrayList<String>();
//        List<FourStringObject> resultValidationDataList = (List<FourStringObject>) PGDataHelper.getInstance().getResultEntryValidationList(resultEntryVO);
        //List<HexaStringClass> rangeFromToList=(List<HexaStringClass>)resultEntryDataMap.get("rangeFromToList");
        //List<SiteVO> siteDiagnosisList=(List<SiteVO>)resultEntryDataMap.get("siteDiagnosisDetailList");
    	NodeList tableNodeList = testDocument.getElementsByTagName("table");
        NodeList nodeList = testDocument.getElementsByTagName("element");
        NodeList nodeListCol = testDocument.getElementsByTagName("columnDetails");
        NodeList tableElements = testDocument.getElementsByTagName("table");
        int maxColumns = 0;
        for(int i = 0; i < tableElements.getLength(); i++)
        {
        	Node node = tableElements.item(i);
        	NamedNodeMap namedNodeAttributesMap = node.getAttributes();
        	Node typeNode = namedNodeAttributesMap.getNamedItem("type");
        	if(typeNode != null && typeNode.getNodeValue().equals("2"))
        	{
//        		if(resultEntryVO.getPrintWithStandardRanges() == true)
//        		{
        			String maxCol = node.getAttributes().getNamedItem("maxColumns").getNodeValue();
        			maxColumns = Integer.parseInt(maxCol);
//        			if(Integer.parseInt(maxCol) > 2)
//        			{
//        				maxCol = maxCol +2; //for unit and ref range
//        				node.getAttributes().getNamedItem("maxColumns").setNodeValue(maxCol +2);
//        			}
//        		}
        	}
        }
        
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            
            ((Element)node).setAttribute("displayy", "");
	    	 
            NamedNodeMap namedNodeAttributesMap = node.getAttributes();
            Node idcNode = namedNodeAttributesMap.getNamedItem("idC");
            
            //LOGGER_INV.log(Level.INFO,"name  ="+idcNode.getNodeName());
            if (idcNode.getNodeValue() != null && idcNode.getNodeValue().equals("label") == false && idcNode.getNodeValue().equals("") == false
            		&&  idcNode.getNodeValue().equals("range") == false) {
            	
             //   Node n = node.getAttributes().getNamedItem("name");
                
                if(resultEntryVO.getDynamicTemplatePrintedGroup().equals("1")) {
					String nodeValue = node.getAttributes().getNamedItem("name").getNodeValue();
					String[] splitNodeValue = nodeValue.split("#");
					String stestCode = splitNodeValue[1].substring(0,5);
					
					if(dmap.containsKey(resultEntryVO.getRequisitionNo()+ resultEntryVO.getGroupCode() + stestCode))
					{
						resultEntryVO = dmap.get(resultEntryVO.getRequisitionNo()+ resultEntryVO.getGroupCode() + stestCode);
						
					}
					
				}
                
                Node n = node.getAttributes().getNamedItem("name");
                n.setNodeValue(resultEntryVO.getRequisitionDNo() + "#" + resultEntryVO.getSessionId() + "#" + node.getAttributes().getNamedItem("name").getNodeValue());
                List<FourStringObject> resultValidationDataList =new ArrayList<FourStringObject>();
                if(resultEntryVO.getIsDeptEntry()!=null && resultEntryVO.getIsDeptEntry().equals("1"))
                {
                	 resultValidationDataList = (List<FourStringObject>) PGDataHelper.getInstance().getDeptResultEntryValidationList(resultEntryVO);
                }
                else
                {resultValidationDataList = (List<FourStringObject>) PGDataHelper.getInstance().getResultEntryValidationList(resultEntryVO);
                
                }
                //LOGGER_INV.log(Level.INFO,"Node Name = "+node.getAttributes().getNamedItem("name").getNodeValue());
                for (FourStringObject fourStringObject : resultValidationDataList) {
                    //LOGGER_INV.log(Level.INFO,"triStringObject Name ="+(resultEntryVO.getRequisitionDNo()+"#template#"+triStringObject.getDesc()+triStringObject.getName()));
                    if ((resultEntryVO.getRequisitionDNo() + "#" + resultEntryVO.getSessionId() + "#template#" + fourStringObject.getDesc()).equals(node.getAttributes().getNamedItem("name").getNodeValue())) {
                    	{  //original
                    		//node.getAttributes().getNamedItem("value").setNodeValue(convertValueToHtml(fourStringObject.getCode()));
                    	
                    		
                    		//test code for dept test template
                    		if(node.getAttributes().getNamedItem("value").getNodeValue().equals("") || node.getAttributes().getNamedItem("value").getNodeValue().contains("cnt") || node.getAttributes().getNamedItem("value").getNodeValue().equals("Browse"))
							{
								
								if(!lstEnteredValues.contains(resultEntryVO.getRequisitionDNo()+"#"+resultEntryVO.getSessionId()+"#template#"+fourStringObject.getDesc()+"#"+fourStringObject.getCode()))
								{	node.getAttributes().getNamedItem("value").setNodeValue(convertValueToHtml(fourStringObject.getCode()));
								lstEnteredValues.add(resultEntryVO.getRequisitionDNo()+"#"+resultEntryVO.getSessionId()+"#template#"+fourStringObject.getDesc()+"#"+fourStringObject.getCode());
								}
									
							}
                    		
                    		
                    		
                    		
                    		
                    	
                    	}
                        
                        
                        
                        
                        /////////////////////  START   TEST CODE FOR DEPT ENTRY ///////////////////////////////////
                        
                        
                        
//                        
//                        
//                        boolean tableIdFlag=false;
//            			//check condition for input only to be inserted
//                    	if(idcNode.getNodeValue().equals("input"))
//                    	{
//                    		
//                    		 
//						if(!node.getAttributes().getNamedItem("changeTableId").getNodeValue().equals(""))
//							{
//							
//							for(int k=0;k<tableNodeList.getLength();k++)
//							{
//								Node tableNode= tableNodeList.item(k);
//								NamedNodeMap tableNamedNodeAttributesMap=tableNode.getAttributes();
//								Node tableidNode=tableNamedNodeAttributesMap.getNamedItem("type");
//								
//								if(tableidNode!=null)
//								if(node.getAttributes().getNamedItem("changeTableId").getNodeValue().equals(tableidNode.getNodeValue()))
//								{
//									Log(Level.INFO, tableidNode.getNodeValue());
//									NodeList tableChildNodes=tableNode.getChildNodes();
//									Node mainRowNode=tableChildNodes.item(1);
//									
//									NamedNodeMap mainRowNodeMap=mainRowNode.getAttributes();
//									Node rowidNode=mainRowNodeMap.getNamedItem("rowNo");
//									
//									//add up extra xml nodes accordingly
//									for(int noOfRows=0;noOfRows<( (Integer.parseInt(fourStringObject.getCode())-1)   );noOfRows++)
//									{
//									Node newNode=mainRowNode.cloneNode(true);
//									tableNode.appendChild(newNode);
//									}
//											
//									Log(Level.INFO, "prnt");
//								}
//								
//							
//							}
//							
//							
//							
//							}
//                    }
//                        
//                        
                        
                        /////////////////////  END   TEST CODE FOR DEPT ENTRY ///////////////////////////////////   
                       
                        // no reference range for the patient based test //
                        //Reference Range
                    	
                    	if(resultEntryVO.getIsDeptEntry()!=null && resultEntryVO.getIsDeptEntry().equals("1")==false)
                    	{
                        Element newRangeNodeElement = testDocument.createElement("rangetag");
                        if(resultEntryVO.getPrintWithStandardRanges() == false)  { 
                           
                        } else if (resultEntryVO.getSampleName()==null || resultEntryVO.getSampleName().equals("NA") == true)
                        {// Patient based Test
                        	String refRange = fourStringObject.getRefRange();
                        	newRangeNodeElement.setAttribute("rangefrom"," ");
        					newRangeNodeElement.setAttribute("rangefromunit"," ");
        					newRangeNodeElement.setAttribute("rangeto"," ");
        					newRangeNodeElement.setAttribute("rangetounit"," ");
        					newRangeNodeElement.setAttribute("rangesymbol"," ");
        					node.appendChild(newRangeNodeElement);
                        }
                        else{
                        	String refRange = fourStringObject.getRefRange();
                        	//Log(Level.INFO, "ref range ======================"+refRange);
                        	if(refRange != null)
    						{
    							String[] strValue = refRange.split("\\$");
    							

    							// nneds to comment whn use //<=,>,>= ref range
    						//	newRangeNodeElement.setAttribute("rangetype",strValue[0]);
    							 
    							
    							// needs to uncommnt when ref range <=,>= like
    							if(strValue[0].equals("1")) // from-to range
    							{
    						//	String strRefRange = strValue[2] +"-"+strValue[1]+" "+strValue[4];
    						//	Log(Level.INFO, "rangee1:"+strRefRange);
    							newRangeNodeElement.setAttribute("rangetype",strValue[0]);
    							newRangeNodeElement.setAttribute("rangefrom",strValue[2]);
    							newRangeNodeElement.setAttribute("rangefromunit",strValue[4]);
    							newRangeNodeElement.setAttribute("rangeto",strValue[1]);
    							newRangeNodeElement.setAttribute("rangetounit",strValue[3]);
    							newRangeNodeElement.setAttribute("rangesymbol","");
    							//strReferanceRange=strReferanceRange.replace("#", "$");
    							}//mp.put(paraCode+"refrange", strReferanceRange);
    							else // other range
    							{
    							//	String strRefRange = strValue[0] +" "+strValue[1]+" "+strValue[2];
    							//	Log(Level.INFO, "rangee:"+strRefRange);
    								newRangeNodeElement.setAttribute("rangetype",strValue[0]);
    								String rangesymbol="";
    								if(strValue[0].equals("2"))
    									rangesymbol=">";
    									if(strValue[0].equals("3"))
    										rangesymbol=">=";
    										if(strValue[0].equals("4"))
    											rangesymbol="<";
    											if(strValue[0].equals("5"))		
    												rangesymbol="<=";
    											
    								newRangeNodeElement.setAttribute("rangesymbol1",rangesymbol);		
    								newRangeNodeElement.setAttribute("rangetoounit",strValue[1]);
    								newRangeNodeElement.setAttribute("rangetoo",strValue[2]);
    							}
    							 
    							
    						}
    						else
    						{
    							newRangeNodeElement.setAttribute("rangesymbol1"," ");
    							newRangeNodeElement.setAttribute("rangefrom"," ");
    							newRangeNodeElement.setAttribute("rangefromunit"," ");
    							newRangeNodeElement.setAttribute("rangeto"," ");
    							newRangeNodeElement.setAttribute("rangetounit"," ");
    							newRangeNodeElement.setAttribute("rangesymbol"," ");
    							
    						}
                        	node.appendChild(newRangeNodeElement);
                        }
                        
                    	}
                        
                        
                        /////////////////////  START   TEST CODE FOR DEPT ENTRY ///////////////////////////////////
                        
                        
                        
                        
                        
                        boolean tableIdFlag=false;
            			//check condition for input only to be inserted
                    	if(idcNode.getNodeValue().equals("input"))
                    	{
                    		
                    	if(namedNodeAttributesMap.getNamedItem("changeTableId")!=null)
                    	if(!node.getAttributes().getNamedItem("changeTableId").getNodeValue().equals(""))
							{
							
							for(int k=0;k<tableNodeList.getLength();k++)
							{
								Node tableNode= tableNodeList.item(k);
								NamedNodeMap tableNamedNodeAttributesMap=tableNode.getAttributes();
								Node tableidNode=tableNamedNodeAttributesMap.getNamedItem("type");
								
								if(tableidNode!=null)
								if(node.getAttributes().getNamedItem("changeTableId").getNodeValue().equals(tableidNode.getNodeValue()))
								{
									Log(Level.INFO, tableidNode.getNodeValue());
									NodeList tableChildNodes=tableNode.getChildNodes();
									Node mainRowNode=tableChildNodes.item(1);
									
									NamedNodeMap mainRowNodeMap=mainRowNode.getAttributes();
									Node rowidNode=mainRowNodeMap.getNamedItem("rowNo");
									
									//add up extra xml nodes accordingly
									for(int noOfRows=0;noOfRows<( (Integer.parseInt(fourStringObject.getCode())-1)   );noOfRows++)
									{
									Node newNode=mainRowNode.cloneNode(true);
									tableNode.appendChild(newNode);
									}
											
									//Log(Level.INFO, "prnt");
								}
								
							
							}
							
							
							
							}
                    }
                        
                        
                        
                        /////////////////////  END   TEST CODE FOR DEPT ENTRY ///////////////////////////////////   
                        
                        
                        

                    }
                }  
//                if(dmap != null)
//					dmap.put(resultEntryVO.getRequisitionNo()+ resultEntryVO.getGroupCode() + resultEntryVO.getTestCode(), resultEntryVO);

            } else if (idcNode.getNodeValue() != null && idcNode.getNodeValue().equals("label") == true) {
                if (node.getAttributes().getNamedItem("id") != null) {
                    String id = node.getAttributes().getNamedItem("id").getNodeValue();
                    String[] codes = id.split("#");
                     String testCode = null;
                    String paraCode = null;
                    
                 
					
				
					if(codes != null && codes.length == 2)
					{
						testCode = codes[1].substring(0,5);
						paraCode = codes[1].substring(5);
					
						  
						  
						  
						
						if(resultEntryVO.getDynamicTemplatePrintedGroup().equals("1")) {
						
							if(dmap.containsKey(resultEntryVO.getRequisitionNo()+ resultEntryVO.getGroupCode() + testCode))
							{
								resultEntryVO = dmap.get(resultEntryVO.getRequisitionNo()+ resultEntryVO.getGroupCode() + testCode);
							}
							
						}
					
					}
                    List<FourStringObject> resultValidationDataList = (List<FourStringObject>) PGDataHelper.getInstance().getResultEntryValidationList(resultEntryVO);
                    for (FourStringObject fourStringObject : resultValidationDataList) {
                    	
                    	 
                    	 
                        String tempId = "eleID_template#" + fourStringObject.getName();
                        if (id.equals(tempId)) {
                        	
                        	if( fourStringObject.getCode().equals("") && testCode.equals("13276")) //for prod test by chandan 08122018
                   	     {
                       		 node.getAttributes().getNamedItem("displayy").setNodeValue("none");
                   	    	 
                   	     }
                   	     else
                   	     {
                   	    	// ((Element)node).setAttribute("displayy", "");
                   	     }
                        	
                            //LOGGER_INV.log(Level.INFO,"triStringObject Name ="+(resultEntryVO.getRequisitionDNo()+"#template#"+triStringObject.getDesc()+triStringObject.getName()));
                            //if ((resultEntryVO.getRequisitionDNo() + "#" + resultEntryVO.getSessionId() + "#template#" + fourStringObject.getDesc()).equals(node.getAttributes().getNamedItem("name").getNodeValue())) {
                            if (fourStringObject.getLoincCode() != null && fourStringObject.getLoincCode().length() > 0) {
                                node.getAttributes().getNamedItem("value").setNodeValue(node.getAttributes().getNamedItem("value").getNodeValue() + " (" + fourStringObject.getLoincCode() + ")");
                            } else {
                            	
                            	    
                                // default value during testing
                                // node.getAttributes().getNamedItem("value").setNodeValue(node.getAttributes().getNamedItem("value").getNodeValue() + " (" + "41758-4" + ")");
                            }
                        }
                    }              
                }                
            }
          
            else if(idcNode.getNodeValue() != null && idcNode.getNodeValue().equals("") == true &&  idcNode.getNodeValue().equals("range") == false)
            {
            	Node nodeCol = nodeListCol.item(i);
            	if(nodeCol != null &&  nodeCol.getAttributes().getNamedItem("colNo") != null) {
	            	String colNo = nodeCol.getAttributes().getNamedItem("colNo").getNodeValue();
	            	int col = Integer.parseInt(colNo);
	            	if(col == maxColumns) {
		            	if(resultEntryVO.getPrintWithStandardRanges() == true) {
			            	Element newRangeNodeElement = testDocument.createElement("rangetag");
			            	newRangeNodeElement.setAttribute("rangefrom"," ");
							newRangeNodeElement.setAttribute("rangefromunit"," ");
							newRangeNodeElement.setAttribute("rangeto"," ");
							newRangeNodeElement.setAttribute("rangetounit"," ");
							newRangeNodeElement.setAttribute("rangesymbol"," ");
							node.appendChild(newRangeNodeElement);
	            	}
	            	}
            	}
            }

        }
    }

    public static synchronized void getTestGroupTemplatedocument(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy, int workOrderGroupIndex) {

        try {
        	
        	String testC = resultEntryVOGroupByValidatedBy.getLaboratoryCode() +resultEntryVOGroupByValidatedBy.getGroupCode();
			String dynamicTemplateID = PGDataHelper.getInstance().getDynamicTemplateStatus(testC, resultEntryVOGroupByValidatedBy.getHospitalCode(), true, resultEntryVOGroupByValidatedBy.getLaboratoryCode());
		  	boolean isPrintWithDynamicTemplate = false;
			String genericTemplateId=PropertiesHelper.getGenericTemplate(); //make config value //make config value
		  	if(dynamicTemplateID != null && !dynamicTemplateID.isEmpty())
	    	{
		  		isPrintWithDynamicTemplate = true;
		  		testC = dynamicTemplateID;
		  		
	    	}
		  	Transformer transformer = null;
		  	if(!isPrintWithDynamicTemplate)
		  		transformer = PGDataHelper.getInstance().getTransformerObject(Config.XSL_RESULTENTRYSTYLESHEET); //tansformerFactory.newTransformer(new StreamSource(path+"ResultEntryStyleSheet.xsl"));
		  	else
		  		transformer = PGDataHelper.getInstance().getTransformerObject(Config.XSL_PRINTINGSTYLESHEET_BODY); //tansformerFactory.newTransformer(new StreamSource(path+"ResultEntryStyleSheet.xsl"));
        	
        	
           
            //InvestigationDocumentCacheManager documentCacheManager =InvestigationDocumentCacheManager.getInstance();
            Node testDocumentNode = PGDataHelper.getInstance().getTemplateNodeObj(Config.XML_LABTESTGROUPTEMPLATE, testC, isPrintWithDynamicTemplate);
            //for generic
    	  	Node genericTestDocumentNode= PGDataHelper.getInstance().getTemplateNodeObj(Config.XML_LABTESTGROUPTEMPLATE, genericTemplateId, isPrintWithDynamicTemplate);

            Document testDocument = PGDataHelper.getInstance().getNewDocument();
            if (testDocumentNode != null) {
                Element rootNode = testDocument.createElement("testgroup");
                rootNode.setAttribute("groupcode", resultEntryVOGroupByValidatedBy.getGroupCode());

                rootNode.setAttribute("requisitionNo", resultEntryVOGroupByValidatedBy.getRequisitionNo());
                rootNode.setAttribute("groupname", resultEntryVOGroupByValidatedBy.getGroupName());
                testDocument.appendChild(rootNode);
                rootNode.appendChild(testDocument.importNode(testDocumentNode, true));

                
                if(resultEntryVOGroupByValidatedBy.getIsDeptEntry().equals("1"))
                	rootNode.appendChild(testDocument.importNode(genericTestDocumentNode, true));
                
                
                resultEntryVOGroupByValidatedBy.setGroupTemplateDocument(testDocument);
                processTheTestGroupDocument(resultEntryVOGroupByValidatedBy, testDocument);

                Source domSource = new DOMSource(testDocument);
                java.io.CharArrayWriter baos = new java.io.CharArrayWriter();
                StreamResult streamResult = new StreamResult(baos);
                transformer.setParameter("workOrder", resultEntryVOGroupByValidatedBy.getRequisitionNo());
                transformer.setParameter("workOrderIndex", "NA");
                transformer.setParameter("multisessionid", resultEntryVOGroupByValidatedBy.getGroupCode());
                transformer.setParameter("workOrderGroupIndex", workOrderGroupIndex);
                transformer.transform(domSource, streamResult);
                resultEntryVOGroupByValidatedBy.setGroupTemplateString(baos.toString());
            }

        } catch (Exception Ex) {
            Ex.printStackTrace();
        }

    }

    public static void processTheTestGroupDocument(ResultEntryVOGroupByValidatedBy resultEntryVOGroupByValidatedBy, Document testDocument) {
		//LOGGER_INV.log(Level.INFO,"----------------inisde result entry process of process the test document");

        NodeList nodeList = testDocument.getElementsByTagName("element");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            NamedNodeMap namedNodeAttributesMap = node.getAttributes();
            Node idcNode = namedNodeAttributesMap.getNamedItem("idC");
            //LOGGER_INV.log(Level.INFO,"name  ="+idcNode.getNodeName());
            if (idcNode.getNodeValue() != null && idcNode.getNodeValue().equals("label") == false && idcNode.getNodeValue().equals("") == false) {
                node.getAttributes().getNamedItem("name").setNodeValue(resultEntryVOGroupByValidatedBy.getRequisitionNo() + "#" + resultEntryVOGroupByValidatedBy.getLaboratoryCode() + resultEntryVOGroupByValidatedBy.getGroupCode() + "#group" + node.getAttributes().getNamedItem("name").getNodeValue());              
            }
        }
    }

    public static String convertValueToHtml(String elementValue) {
        return StringEscapeUtils.unescapeXml(elementValue);
    }

    private static void Log(Level level, String msg) {
        ServiceLogger.Log(TemplateProcessingUSE.class.getName(), level, msg);
    }

    private static void Log(Level level, Exception e) {
        ServiceLogger.Log(TemplateProcessingUSE.class.getName(), level, e);
    }
}
