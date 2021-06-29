package new_investigation.transactions.controller.Helper;

//import static hisglobal.tools.LoggerConfiguration.LOGGER_INV;
import hisglobal.backutil.HisMethods;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;

//import investigation.InvestigationConfig;
//import investigation.InvestigationQueryHandler;
//import investigation.cacheImplementation.cachemanager.InvestigationDocumentCacheManager;
//import investigation.cacheImplementation.cachemanager.InvestigationStyleSheetConfigurationCacheManager;




















import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import new_investigation.InvestigationConfig;
import new_investigation.masters.controller.fb.TestParameterMstFB;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;
import new_investigation.vo.template.ResultEntryVO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ViewTemplate extends HisMethods{
	
	
	
    //static Connection connection = null;
    private static String connectionURL;
    private static String connectionUsername;
    private static String connectionPassword;
    private static TransformerFactory transformerFactory;
    private static DocumentBuilderFactory builderFactory;
    private static DocumentBuilder builder;
    XPathFactory factory = null;
    private static int checkedOut = 0;
    private static HashMap<String, Node> nodeCollection;
    
	
	public void ViewTemplate(HttpServletRequest request, InvestigationViewTemplateActionForm fb)
	{
	//	LOGGER_INV.log(Level.INFO,"Calling viewTemplate"+fb.getSelectedTest());
	Document actualdocumentObj=null;
	DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder=null;
	Node newTestHTML=null;
    Node actualRootElement=null;
    ResultEntryVO resultEntryVO=null;
    String testcode="";
	try
	{
		  //ResultEntryVO resultEntryVO=new ResultEntryVO();//caching implementation
	    try {
			resultEntryVO=new ResultEntryVO(null,fb.getSelectedTest(),null,null);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InvestigationTemplateDataHelper itdh = InvestigationTemplateDataHelper.getInstance();
		InputStream is =null;
		
		//if(fb.getParaType().equals("0"))
	    is = itdh.getClobObjectFromDatabaseForResultEntryFormDocument();
		//else if(fb.getParaType().equals("1"))
		 //   is = itdh.getClobObjectFromDatabaseForResultEntryFormDocument();
			
		
		if(is != null)
			actualdocumentObj=documentBuilder.parse(is);//path+"testtemplate.xml");

		
	}
	catch(FileNotFoundException fileNotFoundException)
	{
		fileNotFoundException.printStackTrace();
	} catch (ParserConfigurationException e) {
		
		e.printStackTrace();
	} catch (SAXException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	if(actualdocumentObj==null)
	{
		actualdocumentObj=documentBuilder.newDocument();
	}
	
	//Element rootHTML=newDocumentObjectHTML.createElement("templates");
	
	
    NodeList templatesList=actualdocumentObj.getElementsByTagName("templates");
//    LOGGER_INV.log(Level.INFO,"tempalte list size "+templatesList.getLength());
    
    if(templatesList.getLength()!=0)
    {
    	actualRootElement=templatesList.item(0);
    	XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		//LOGGER_INV.log(Level.INFO,"testtemplate[@labTestCode='"+resultEntryVO.getTestCode()+"']");
		XPathExpression expr=null;
		try {
			 testcode=resultEntryVO.getTestCode();
			expr = xpath.compile("testtemplate[@labTestCode='"+resultEntryVO.getTestCode()+"']");
			//expr = xpath.compile("testtemplate[@labTestCode='"+1018+"']");
		    Object result=null;
		
			result = expr.evaluate(actualRootElement, XPathConstants.NODESET);
		
		
	    NodeList nodes = (NodeList) result;
    	//LOGGER_INV.log(Level.INFO,""+nodes.getLength());
    	
    if(nodes==null ||  nodes.getLength()==0)
    {
    	 newTestHTML=actualdocumentObj.createElement("testtemplate");	
    }
    else
    {
    	for(int i=0;i<nodes.getLength();i++)
    	{
    		//LOGGER_INV.log(Level.INFO,nodes.getLength()+" existing template"+i);
    		newTestHTML=nodes.item(i);
    	}
     }
	}
    catch (XPathExpressionException e) {
		
		e.printStackTrace();
	}
    }	
     else
    {
    	actualRootElement=actualdocumentObj.createElement("templates");
    	newTestHTML=actualdocumentObj.createElement("testtemplate");	
    	
    }    		  

	Transformer transformer;
	try {
		//transformer = transformerFactory.newTransformer(new StreamSource(//path+"StyleSheet.xsl"));
		//Note: get the XSL stylesheet from the database and convert it to a transformer object
		transformer= InvestigationTemplateDataHelper.getInstance().getTransformerObject(InvestigationConfig.XSL_VIEWTEMPLATE_STYLESHEET);
				
		//InvestigationStyleSheetConfigurationCacheManager.getInstance().getTransformerObj(InvestigationConfig.XSL_STYLESHEET);
		Document newdoc=documentBuilder.newDocument();
		newdoc.appendChild(newdoc.importNode(newTestHTML, true));
		resultEntryVO.setTestDocument(newdoc);
		java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
	 	StreamResult streamResult=new StreamResult(baos) ;
	 	transformer.transform(new DOMSource(newdoc),streamResult);
	 	resultEntryVO.setTemplateDocumentString(baos.toString());
	 	
	 	TransformerFactory tf = TransformerFactory.newInstance();
	 	Transformer transformer1 = tf.newTransformer();
	 	transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	 	StringWriter writer = new StringWriter();
	 	transformer1.transform(new DOMSource(newdoc), new StreamResult(writer));
	 	String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
	 	System.out.println(output);
	 	String outputtestwisesave="<?xml version='1.0' encoding='UTF-8'?><templates>"+output+"</templates>";
	 	if(fb.getParaType().equals("0"))
	 	{
	 		istestemplateexist(testcode,outputtestwisesave);
	 	}
	 	//InvestigationDocumentCacheManager.getInstance().createXMLforCheck("d:/step.xml", newdoc);
		
	} catch (TransformerConfigurationException e) {
			e.printStackTrace();
	} catch (TransformerException e) {
			e.printStackTrace();
	}
	
	request.getSession().setAttribute("RESULTENTRYVO",resultEntryVO);
	
    }


	public void RequisitionFormMasterDataTestSelectionTemplate(HttpServletRequest request, InvestigationViewTemplateActionForm fb) throws XPathExpressionException
	{
	//	LOGGER_INV.log(Level.INFO,"Calling viewTemplate"+fb.getSelectedTest());
	Document actualdocumentObj=null;
	Document actualdocumentObj1=null;
	DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder=null;
	Node newTestHTML=null;
    Node actualRootElement=null;
    ResultEntryVO resultEntryVO=null;
   String testcode="";
	try
	{
		  //ResultEntryVO resultEntryVO=new ResultEntryVO();//caching implementation
	    try {
			resultEntryVO=new ResultEntryVO(null,fb.getMastertestCode(),null,null);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InvestigationTemplateDataHelper itdh = InvestigationTemplateDataHelper.getInstance();
		InputStream is =null;
		
		//if(fb.getParaType().equals("0"))
	    is = itdh.getClobObjectFromDatabaseForRequisitionFormDocument();
		//else if(fb.getParaType().equals("1"))
		 //   is = itdh.getClobObjectFromDatabaseForResultEntryFormDocument();
			
		
		if(is != null)
			actualdocumentObj=documentBuilder.parse(is);//path+"testtemplate.xml");
		//actualdocumentObj1=documentBuilder.parse(is);//path+"testtemplate.xml");
		
	}
	catch(FileNotFoundException fileNotFoundException)
	{
		fileNotFoundException.printStackTrace();
	} catch (ParserConfigurationException e) {
		
		e.printStackTrace();
	} catch (SAXException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
	if(actualdocumentObj==null)
	{
		actualdocumentObj=documentBuilder.newDocument();
	}
	
	//Element rootHTML=newDocumentObjectHTML.createElement("templates");
	actualdocumentObj1=actualdocumentObj;
	
    NodeList templatesList=actualdocumentObj.getElementsByTagName("templates");
//    LOGGER_INV.log(Level.INFO,"tempalte list size "+templatesList.getLength());
    
    if(templatesList.getLength()!=0)
    {
    	actualRootElement=templatesList.item(0);
    	XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		//LOGGER_INV.log(Level.INFO,"testtemplate[@labTestCode='"+resultEntryVO.getTestCode()+"']");
		XPathExpression expr=null;
		try {
			testcode=resultEntryVO.getTestCode();
			expr = xpath.compile("testtemplate[@labTestCode='"+resultEntryVO.getTestCode()+"']");
			//expr = xpath.compile("testtemplate[@labTestCode='"+1018+"']");
		    Object result=null;
		
			result = expr.evaluate(actualRootElement, XPathConstants.NODESET);
		
		
	    NodeList nodes = (NodeList) result;
    	//LOGGER_INV.log(Level.INFO,""+nodes.getLength());
    	
    if(nodes==null ||  nodes.getLength()==0)
    {
    	 newTestHTML=actualdocumentObj.createElement("testtemplate");	
    }
    else
    {
    	for(int i=0;i<nodes.getLength();i++)
    	{
    		//LOGGER_INV.log(Level.INFO,nodes.getLength()+" existing template"+i);
    		newTestHTML=nodes.item(i);
    	}
     }
	}
    catch (XPathExpressionException e) {
		
		e.printStackTrace();
	}
    }	
     else
    {
    	actualRootElement=actualdocumentObj.createElement("templates");
    	newTestHTML=actualdocumentObj.createElement("testtemplate");	
    	
    }    		  

	Transformer transformer;
	try {
		//transformer = transformerFactory.newTransformer(new StreamSource(//path+"StyleSheet.xsl"));
		//Note: get the XSL stylesheet from the database and convert it to a transformer object
		transformer= InvestigationTemplateDataHelper.getInstance().getTransformerObject(InvestigationConfig.XSL_VIEWTEMPLATE_STYLESHEET);
				
		//InvestigationStyleSheetConfigurationCacheManager.getInstance().getTransformerObj(InvestigationConfig.XSL_STYLESHEET);
		Document newdoc=documentBuilder.newDocument();
		newdoc.appendChild(newdoc.importNode(newTestHTML, true));
		
		if(newdoc!=null)
		{
		Node testTemplate = newdoc.getFirstChild();
		Node labTestCode = newdoc.getElementsByTagName("testtemplate").item(0);
		
		NamedNodeMap attr = labTestCode.getAttributes();
		Node nodeAttr = attr.getNamedItem("labTestCode");
		if(nodeAttr!=null)
		nodeAttr.setTextContent(fb.getSelectedTest());
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList)xpath.evaluate("//*[contains(@id, 'eleID_template')]",
		                                          newdoc, XPathConstants.NODESET);
		
		for (int idx = 0; idx < nodes.getLength(); idx++) {
		    Node value = nodes.item(idx).getAttributes().getNamedItem("id");
		    String val = value.getNodeValue();
		    value.setNodeValue(val.replaceAll(fb.getMastertestCode(), fb.getSelectedTest()));
		}
		
		
		XPath xpath1 = XPathFactory.newInstance().newXPath();
		NodeList nodes1 = (NodeList)xpath1.evaluate("//*[contains(@name, 'template')]",
		                                          newdoc, XPathConstants.NODESET);
		
		for (int idx = 0; idx < nodes1.getLength(); idx++) {
		    Node value = nodes1.item(idx).getAttributes().getNamedItem("name");
		    String val = value.getNodeValue();
		    value.setNodeValue(val.replaceAll(fb.getMastertestCode(), fb.getSelectedTest()));
		}
		
		//actualdocumentObj1
		/*Node node = newdoc.getDocumentElement();
		 Element as=newdoc.createElement("chanks");
         as.setAttribute("chh", "chh");
         node.appendChild(as);*/
         
        /* Document newdoc1=newdoc;
         NodeList nList =newdoc1.getElementsByTagName("rowDetails");
 		Node nNode = nList.item(0);
		Element eElement = (Element) nNode;
		System.out.println("Staff id : " + eElement.getAttribute("labTestCode"));
         node.appendChild(eElement);*/
         
		
		
		}
		
		Node ndetournament = actualdocumentObj1.getDocumentElement();
		Element ndetournament1 = (Element) newdoc.getElementsByTagName("testtemplate").item(0);
		Node firstDocImportedNode = actualdocumentObj1.importNode(ndetournament1, true);
		ndetournament.appendChild(firstDocImportedNode);
		
		newdoc=actualdocumentObj1;
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer1 = transformerFactory.newTransformer();
		transformer1.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer1.transform(new DOMSource(actualdocumentObj1), new StreamResult(System.out));
		
		
		
		resultEntryVO.setTestDocument(newdoc);
		java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
	 	StreamResult streamResult=new StreamResult(baos) ;
	 	
	 	TransformerFactory transformerFactory1 = TransformerFactory
				.newInstance();
	 	Source domSource = new DOMSource(newdoc);
		transformerFactory1.newTransformer().transform(domSource,
				streamResult);
				
				
	 	//transformer.transform(new DOMSource(newdoc),streamResult);
	 	resultEntryVO.setTemplateDocumentString(baos.toString());
	 	
	 	HttpSession session=null;
	 	
	 	TemplateProcessingHLP hlp=new TemplateProcessingHLP(1);
		hlp.loadDocumentStringToDatabase(
				InvestigationConfig.XML_TESTREQUISITIONTEMPLATE,
				"testTemplate.xml", "testTemplate.xml",
				baos.toString(), session);
		
	 	
	 	
	 	TransformerFactory tf = TransformerFactory.newInstance();
	 	Transformer transformer11 = tf.newTransformer();
	 	transformer11.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	 	StringWriter writer = new StringWriter();
	 	transformer11.transform(new DOMSource(newdoc), new StreamResult(writer));
	 	String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
	 	System.out.println(output);
	 	String outputtestwisesave="<?xml version='1.0' encoding='UTF-8'?><templates>"+output+"</templates>";
	 	if(fb.getParaType()!=null && fb.getParaType().equals("0"))
	 	{
	 		istestemplateexist(testcode,outputtestwisesave);
	 	}
	 	
	 	//InvestigationDocumentCacheManager.getInstance().createXMLforCheck("d:/step.xml", newdoc);
		
	} catch (TransformerConfigurationException e) {
			e.printStackTrace();
	} catch (TransformerException e) {
			e.printStackTrace();
	}
	
//	request.getSession().setAttribute("RESULTENTRYVO",resultEntryVO);
	
    }
	
	
	
	public void ViewTemplate11(HttpServletRequest request, InvestigationViewTemplateActionForm fb) throws ParserConfigurationException
	{
		
		builderFactory = DocumentBuilderFactory.newInstance();
        builder = builderFactory.newDocumentBuilder();
        factory = XPathFactory.newInstance();
        transformerFactory = TransformerFactory.newInstance();
        nodeCollection = new HashMap<String, Node>();
        
	//	LOGGER_INV.log(Level.INFO,"Calling viewTemplate"+fb.getSelectedTest());
	Document actualdocumentObj=null;
	DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder=null;
	Node newTestHTML=null;
    Node actualRootElement=null;
    ResultEntryVO resultEntryVO=null;
 
	try
	{
		//actualdocumentObj=loadDocumentObj("1"); //need to be uncomment when 1st time data insert in hivt_document_testwise break at initial point
		
		  //ResultEntryVO resultEntryVO=new ResultEntryVO();//caching implementation
	    try {
			resultEntryVO=new ResultEntryVO(null,fb.getSelectedTest(),null,null);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		InvestigationTemplateDataHelper itdh = InvestigationTemplateDataHelper.getInstance();
		InputStream is =null;
		
		//if(fb.getParaType().equals("0"))
	   // is = itdh.getClobObjectFromDatabaseForResultEntryFormDocument();
		//else if(fb.getParaType().equals("1"))
		 //   is = itdh.getClobObjectFromDatabaseForResultEntryFormDocument();
			
		
		//if(is != null)
		//	actualdocumentObj=documentBuilder.parse(is);//path+"testtemplate.xml");
		
	}
	 catch (ParserConfigurationException e) {
		
		e.printStackTrace();
	} 
	
	if(actualdocumentObj==null)
	{
		actualdocumentObj=documentBuilder.newDocument();
	}
	
	//Element rootHTML=newDocumentObjectHTML.createElement("templates");
	
	
    NodeList templatesList=actualdocumentObj.getElementsByTagName("templates");
//    LOGGER_INV.log(Level.INFO,"tempalte list size "+templatesList.getLength());
    
    if(templatesList.getLength()!=0)
    {
    	actualRootElement=templatesList.item(0);
    	XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		//LOGGER_INV.log(Level.INFO,"testtemplate[@labTestCode='"+resultEntryVO.getTestCode()+"']");
		XPathExpression expr=null;
		XPathExpression expr1=null;
		try {
			expr = xpath.compile("/templates/testtemplate");

			/*			Object result1=null;
			expr1 = xpath.compile("testtemplate[@labTestCode]");
			result1 = expr1.evaluate(actualRootElement, XPathConstants.NODESET);
		    NodeList nodes1 = (NodeList) result1;
    		System.out.println("total test contain "+nodes1.getLength());
*/
			
			//expr = xpath.compile("testtemplate[@labTestCode='"+1018+"']");
		    Object result=null;
		
			result = expr.evaluate(actualRootElement, XPathConstants.NODESET);
		
		
	    NodeList nodes = (NodeList) result;
    	//LOGGER_INV.log(Level.INFO,""+nodes.getLength());
    	
    if(nodes==null ||  nodes.getLength()==0)
    {
    	 newTestHTML=actualdocumentObj.createElement("testtemplate");	
    }
    else
    {
    	for(int i=0;i<nodes.getLength();i++)
    	{
    		System.out.println("total test contain "+nodes.getLength());
    		
    		String ss=(String)nodes.item(i).getNodeValue();
    		
    		System.out.println("total test contain "+ss);

    		
    		//LOGGER_INV.log(Level.INFO,nodes.getLength()+" existing template"+i);
    		
    		newTestHTML=nodes.item(i);
    		
    	    String key = newTestHTML.getAttributes().getNamedItem("labTestCode").getNodeValue();
    		
    		System.out.println("test code:::::::::::::"+key);

    		
    		Transformer transformer;
    		try {
    			//transformer = transformerFactory.newTransformer(new StreamSource(//path+"StyleSheet.xsl"));
    			//Note: get the XSL stylesheet from the database and convert it to a transformer object
    			transformer= InvestigationTemplateDataHelper.getInstance().getTransformerObject(InvestigationConfig.XSL_VIEWTEMPLATE_STYLESHEET);
    					
    			//InvestigationStyleSheetConfigurationCacheManager.getInstance().getTransformerObj(InvestigationConfig.XSL_STYLESHEET);
    			Document newdoc=documentBuilder.newDocument();
    			newdoc.appendChild(newdoc.importNode(newTestHTML, true));
    			resultEntryVO.setTestDocument(newdoc);
    			java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
    		 	StreamResult streamResult=new StreamResult(baos) ;
    		 	transformer.transform(new DOMSource(newdoc),streamResult);
    		 	resultEntryVO.setTemplateDocumentString(baos.toString());
    		 	
    		 	TransformerFactory tf = TransformerFactory.newInstance();
    		 	Transformer transformer1 = tf.newTransformer();
    		 	transformer1.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    		 	StringWriter writer = new StringWriter();
    		 	transformer1.transform(new DOMSource(newdoc), new StreamResult(writer));
    		 	String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
    		 	String output1="<?xml version='1.0' encoding='UTF-8'?><templates>"+output+"</templates>";
    		 	//System.out.println("1",key,output);
    		 	loadDocumentStringToDatabase(key,output1);
    		 	//InvestigationDocumentCacheManager.getInstance().createXMLforCheck("d:/step.xml", newdoc);
    			
    		} catch (TransformerConfigurationException e) {
    				e.printStackTrace();
    		} catch (TransformerException e) {
    				e.printStackTrace();
    		}
    		
    	}
     }
	}
    catch (XPathExpressionException e) {
		
		e.printStackTrace();
	}
    }	
     else
    {
    	actualRootElement=actualdocumentObj.createElement("templates");
    	newTestHTML=actualdocumentObj.createElement("testtemplate");	
    	
    }    		  

	
	
	request.getSession().setAttribute("RESULTENTRYVO",resultEntryVO);
	
    }

	
	
	
	public void loadDocumentStringToDatabase(String testcode,String clobdata) {
		System.out.println("Loading .... loadDocumentStringToDatabase");

		/*
		 * try { // Class.forName(InvestigationConfig.databaseDriver); } catch
		 * (ClassNotFoundException e1) { e1.printStackTrace(); }
		 */

		Connection connection = null;
		CallableStatement cstmt = null;
		JDBCTransactionContext tx = null;
		try {
			// connection =
			// DriverManager.getConnection(Conn.getDatabaseConnection(),InvestigationStaticConfigurator.databaseusername,InvestigationStaticConfigurator.databasepassword);
			// connection.setAutoCommit(false);

			tx = new JDBCTransactionContext();
			tx.begin();
			connection = tx.getConnection();
			
			cstmt = connection
					.prepareCall("{call pkg_inv_template.loaddocumenttestwise(?,?,?)}");

			cstmt.setString(1, testcode);
			cstmt.setString(2, clobdata);
			//cstmt.setString(3, xmlTesttemplateRemarks);
		//	cstmt.setString(4, templateFileString);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			// cstmt.setString(6, ""+hospitalCode);
			cstmt.execute();

		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				cstmt.close();
				connection.commit();
				connection.close();
				
			//	tx.close();
				// commented by Siddharth, 27/03/2015
				// InvestigationDocumentCacheManager.getInstance().loadDocumentObj(xmlTesttemplateId,""+hospitalCode);
				///InvestigationTemplateDataHelper.getInstance().loadDocumentObj(
					//	xmlTesttemplateId, session);
				System.out
						.println("document uploading successfull to datadase");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println("success .... loadDocumentStringToDatabase");

	}


	
	public static void istestemplateexist(String testcode,String clobdata) {
		System.out.println("Loading .... loadDocumentStringToDatabase");

		/*
		 * try { // Class.forName(InvestigationConfig.databaseDriver); } catch
		 * (ClassNotFoundException e1) { e1.printStackTrace(); }
		 */

		Connection connection = null;
		CallableStatement cstmt = null;
		JDBCTransactionContext tx = null;
		try {
			// connection =
			// DriverManager.getConnection(Conn.getDatabaseConnection(),InvestigationStaticConfigurator.databaseusername,InvestigationStaticConfigurator.databasepassword);
			// connection.setAutoCommit(false);

			tx = new JDBCTransactionContext();
			tx.begin();
			connection = tx.getConnection();
			
			cstmt = connection
					.prepareCall("{call pkg_inv_template.istesttemplateexist(?,?,?)}");

			cstmt.setString(1, testcode);
			cstmt.setString(2, clobdata);
			//cstmt.setString(3, xmlTesttemplateRemarks);
		//	cstmt.setString(4, templateFileString);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			// cstmt.setString(6, ""+hospitalCode);
			cstmt.execute();

		} catch (HisApplicationExecutionException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		catch (HisDataAccessException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				cstmt.close();
				connection.commit();
				connection.close();
				
			//	tx.close();
				// commented by Siddharth, 27/03/2015
				// InvestigationDocumentCacheManager.getInstance().loadDocumentObj(xmlTesttemplateId,""+hospitalCode);
				///InvestigationTemplateDataHelper.getInstance().loadDocumentObj(
					//	xmlTesttemplateId, session);
				System.out
						.println("document uploading successfull to datadase");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println("success .... loadDocumentStringToDatabase");

	}

	//added  by chandan for use to store xml data in db(split testwise) run only 1 time
	public Document loadDocumentObj(String id) {
	    Document vObj = null;
	    java.io.InputStream ip = null;

	    try {
	        boolean found = false;			
	        switch ((Integer.parseInt(id))) {
	            case 1:
	                ip = null;//getClobObjectFromDatabaseForResultEntryFormDocument();
	                //System.out.println("read template");
	                String filePath = "template";
	                //System.out.println("filePath "+filePath);
	                File fr = new File(filePath,"readTemplate.xml");
	               // File fr = new File("TestTemplate.xml");
	                vObj = builder.parse(fr);
	                //System.out.println("before loading node");
	                loadEachNodeOfDocumentSeparately(vObj, "investigationDocumentCacheVO" + id);
	                System.out.println("loaded nodes");
	                
	                break;                
	        }

	        // set found to true if it was found
	        found = true;//true;
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	        //e.printStackTrace();
	       // Log(Level.SEVERE, e);
	        // Handle failure putting object to cache
	    }

	    return vObj;
	    }


	 public void loadEachNodeOfDocumentSeparately(Document vObj, String str) {
	        //	LOGGER_INV.log(Level.INFO,"loadEachNodeOfDocumentSeparately");
	        XPath xpath = factory.newXPath();
	        try {

	            XPathExpression expr = xpath.compile("/templates/testtemplate");
	            Object result = expr.evaluate(vObj, XPathConstants.NODESET);
	            NodeList nodes = (NodeList) result;
	            //  LOGGER_INV.log(Level.INFO,"loadEachNodeOfDocumentSeparately ->"+nodes.getLength());
	            for (int i = 0; i < nodes.getLength(); i++) {
	                Node node = nodes.item(i);
	                //	investigationDocumentCacheVO.put(str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue(), node);
	                //session.setAttribute(str + "_" + node.getAttributes().getNamedItem("labTestCode").getNodeValue(), node);
	                nodeCollection.put(str + "_" + node.getAttributes().getNamedItem("labTestCode").getNodeValue(), node);
	                //	LOGGER_INV.log(Level.INFO,"Loading key"+str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue());
	            }
	        } catch (Exception ex) {
	            // ex.printStackTrace();
	            //Log(Level.SEVERE, ex);
	        }

	    }
	 
	
}
