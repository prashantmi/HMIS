package Ftp;


/*import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.JDBCTransactionContext;
import investigation.vo.HisAppletConfigurator;
import hisglobal.vo.ImageUtilityConfiguratorDetails;
import investigation.InvestigationConfig;
import investigation.InvestigationStaticConfigurator;
import investigation.usefulmethods.BarcodeFileGenerator;
import investigation.vo.PrinterVO;*/

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

//import oracle.jdbc.OracleTypes;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class InvestigationDocumentCacheManager {
	private static InvestigationDocumentCacheManager instance;
	private static int checkedOut = 0;
	private static JCS investigationDocumentCacheVO;
	private static DocumentBuilderFactory builderFactory;
	private static DocumentBuilder builder;
	 XPathFactory factory =null;
	 public static String databaseusername = null;
	 public static String databasepassword = null;
	private InvestigationDocumentCacheManager()
	{
		try
		{
			investigationDocumentCacheVO = JCS.getInstance("investigationDocumentCacheVO");
			builderFactory= DocumentBuilderFactory.newInstance();
			builder=builderFactory.newDocumentBuilder();
			factory = XPathFactory.newInstance();
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
		// Handle cache region initialization failure
		}
	
	
	// Do other initialization that may be necessary, such as getting
	// references to any data access classes we may need to populate value objects later
	}
	/**
	* Singleton access point to the manager.
	*/
	public static InvestigationDocumentCacheManager getInstance()
	{
		synchronized (InvestigationDocumentCacheManager.class)
		{
			if (instance == null)
			{
			instance = new InvestigationDocumentCacheManager();
			}
		}
		synchronized (instance)
		{
			instance.checkedOut++;
		}
		
		return instance;
	}
	
	/**
	* Retrieves a Node element for testtemplate Object. Default to look in the cache.
	*/
/*	public synchronized Node getDocumentObj(String id,String nodeId,String hospitalCode)
	{
		
		return getTemplateNodeObj(id, true,nodeId,hospitalCode);
	}
	
	public synchronized Document getWholeDocumentObj(String id,boolean fromCache,String hospitalCode)
	{
		Document document = null;
		// First, if requested, attempt to load from cache
		Node node=null;
		if (fromCache)
		{
			System.out.println("loading..."+id);
			document = (Document) investigationDocumentCacheVO.get("investigationDocumentCacheVO"+hospitalCode+ id);
			
		}
		// Either fromCache was false or the object was not found, so
		// call loadBookVObj to create it
		if (document == null)
		{
			document = loadDocumentObj(id,hospitalCode);
			
		}
		
		return document;
	}*/
	
	public Document getNewDocument()
	{
		return builder.newDocument();
	}
	
	public DocumentBuilder getDocumentBuilder()
	{
		return builder;
	}
	
	public XPathFactory getXPathFactory()
	{
		return factory;
	}
	/**
	* Retrieves a CacheLaboratoryTestVO Object. 
	* Second argument decides whether to look * in the cache. Returns a new value object if one can't be
	* loaded from the database. Database cache synchronization is * handled by removing cache elements upon modification.
	* @param id
	*/
	/*public Node getTemplateNodeObj(String id, boolean fromCache,String nodeId,String hospitalCode)
	{
		Document document = null;
		// First, if requested, attempt to load from cache
		Node node=null;
		if (fromCache)
		{
			System.out.println("loading..."+id);
			document = (Document) investigationDocumentCacheVO.get("investigationDocumentCacheVO"+hospitalCode+id);
			
		}
		// Either fromCache was false or the object was not found, so
		// call loadBookVObj to create it
		if (document == null)
		{
			document = loadDocumentObj(id,hospitalCode);
			
		}
		
		if(document != null)
		{
			System.out.println("loading... Node form memory -->"+id);
			node=(Node)investigationDocumentCacheVO.get("investigationDocumentCacheVO"+hospitalCode+id+"_"+nodeId);
			System.out.println("Getting Key ::->"+"investigationDocumentCacheVO"+hospitalCode+id+"_"+nodeId);
		}
		
		
		
		return node;
	}*/
	
	/*loading and reloading of applet Configurator*/
	
	
	
	
	 /**
	* Creates a CacheLaboratoryTestVO based on the id of the BOOK table. Data
	* access could be direct JDBC, some or mapping tool, or an EJB.
	* @param id 
	*/
	/* public Document loadDocumentObj(String id,String hospitalCode)
		{
			Document vObj = null;
			java.io.InputStream ip = null;
			
			try
			{
			boolean found = false;
			//enum  DOCUMENT_DETAILS{testtemplate,testsampletemplate,testrequisitiontemplate,templateQueryFile,printingtemplate,
		//		labtestgrouptemplate,labRegisterConfiguration,laboratoryRequisitiontemplate,imageUtilityUserInformation,appletConfigurator};
			
			System.out.println("id -->"+id);
			switch((Integer.parseInt(id)))
			{
			case 1:
				ip=getClobObjectFromDatabaseForResultEntryFormDocument(hospitalCode);
				
				break;
			case 2:
				ip=getClobObjectFromDatabaseForSampleCollectionFormDocument(hospitalCode);
				
				
				break;
			case 3:
				ip=getClobObjectFromDatabaseForRequisitionFormDocument(hospitalCode);
				
				break;
			case 4:
				ip=getClobObjectFromDatabaseTemplateQueryDocument(hospitalCode);
				
				break;
			case 5:
				ip=getClobObjectFromDatabaseForPrintingFormDocument(hospitalCode);
				
				break;
			case 6:
				ip=getClobObjectFromDatabaseForResultEntryGroupFormDocument(hospitalCode);
				
				break;
			case 7:
				ip=getClobObjectFromDatabaseLabRegisterConfigDocument(hospitalCode);
				
				break;
			case 8:
				ip=getClobObjectFromDatabaseForLabRequisitionFormDocument(hospitalCode);
				
				break;
			case 9:
				ip=getClobObjectFromDatabaseImageUtilityConfDocument(hospitalCode);
				
				break;
			case 10:
				ip=getClobObjectFromDatabaseAppletConfiguratorDocument(hospitalCode);
				
				break;
			
			}
			
			System.out.println("InvestigationDocumentCacheManager-loadDocumentObj "+hospitalCode+id);
			// set found to true if it was found
			found = true;
			// cache the value object if found
				if (found)
				{
					vObj=builder.parse(ip);
					investigationDocumentCacheVO.put("investigationDocumentCacheVO"+hospitalCode+id, vObj);
					if(Integer.parseInt(id)==10)
					{
						loadAppletConfigurator(hospitalCode);					
					}
					if(Integer.parseInt(id)==4)
					{
						loadTemplateQuery(hospitalCode);					
					}
					if(Integer.parseInt(id)==4)
					{
						loadAppletUserInformationXML(hospitalCode);
					}
					else
					{
						loadEachNodeOfDocumentSeparately(vObj,"investigationDocumentCacheVO"+hospitalCode+id);
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			// Handle failure putting object to cache
			}
			
			
			
		return vObj;
		}
	
	 public void loadEachNodeOfDocumentSeparately(Document vObj,String str)
	 {
		 	System.out.println("loadEachNodeOfDocumentSeparately");
			XPath xpath = factory.newXPath();
			try
			{
			
			XPathExpression expr = xpath.compile("/templates/testtemplate");
		    Object result = expr.evaluate(vObj, XPathConstants.NODESET);
		    NodeList nodes = (NodeList) result;
		    System.out.println("loadEachNodeOfDocumentSeparately ->"+nodes.getLength());
		    	for(int i=0;i<nodes.getLength();i++)
		    	{
		    		System.out.println("i=="+i);
		    		Node node=nodes.item(i);
		    		investigationDocumentCacheVO.put(str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue(), node);
		    		
		    		System.out.println(i+" ->Loading key"+str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue());
		    	}
		    	  System.out.println("after for loop ->"+nodes.getLength());
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
	 }
	 
	 public void loadSpecificNodeOfDocumentOnly(String documentId,String str,String id,String hospitalCode)
	 {
		 	System.out.println("loadSpecificNodeOfDocumentOnly");
		 	Document document = (Document) investigationDocumentCacheVO.get(str);
		 	if (document == null)
			{
				document = loadDocumentObj(documentId,hospitalCode);
				
			}
		 	
		 	
		 	
			XPath xpath = factory.newXPath();
			try
			{
			
			XPathExpression expr = xpath.compile("/templates/testtemplate[@labTestCode="+id+"]");
		    Object result = expr.evaluate(document, XPathConstants.NODESET);
		    NodeList nodes = (NodeList) result;
		    System.out.println("loadSpecificNodeOfDocumentOnly ->"+nodes.getLength());
		    for(int i=0;i<nodes.getLength();i++)
		    {
		    		Node node=nodes.item(i);
		    		investigationDocumentCacheVO.put(str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue(), node);
		    		
		    		System.out.println("Loading key"+str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue());
		    }
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
	 }
	
	 public void createXMLforCheck(String string,Document doc) {
			System.out.println("<!--ut.println->");
			 TransformerFactory transformerFactory=TransformerFactory.newInstance();
			    Transformer transformer;
				try {
					transformer = transformerFactory.newTransformer();
					transformer.transform(new DOMSource(doc), new StreamResult(new FileWriter(string)));
				} catch (TransformerConfigurationException e) {
						e.printStackTrace();
				} catch (TransformerException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			 
			
		}
	 public InputStream getClobObjectFromDatabaseForRequisitionFormDocument(String hospitalCode ) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getRequisitionFormDocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseForLabRequisitionFormDocument(String hospitalCode ) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getlabrequformdocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseForSampleCollectionFormDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getSampleCollFormDocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseForResultEntryFormDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getresultEntryFormDocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseForLabTestGroupDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getresultEntryFormDocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseForResultEntryGroupFormDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getGroupFormDocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseForPrintingFormDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getprintingDocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseAppletConfiguratorDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getAppletConfiguratordocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				System.out.println("SUCCESS APPLET CONFIG1");
				cstmt.execute();
				System.out.println("SUCCESS APPLET CONFIG2");
				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
					System.out.println("SUCCESS APPLET CONFIG3");
					ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseTemplateQueryDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getTemplateQuerydocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseImageUtilityConfDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getImageUtilityConfdocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 public InputStream getClobObjectFromDatabaseLabRegisterConfigDocument(String hospitalCode) {

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn.prepareCall("{call inv_configuration.getLabRegisterConfigdocument(?,?,?)}");
				cstmt.registerOutParameter(1, OracleTypes.CLOB);
				cstmt.registerOutParameter(2, OracleTypes.VARCHAR);
				cstmt.setString(3, hospitalCode);
				cstmt.execute();

				Clob requisitionFormClob = cstmt.getClob(1);
				String error = cstmt.getString(2);
				if(error.equals("SUCCESS"))
				{
				ip = requisitionFormClob.getAsciiStream();
				}
				
				

			} catch (HisApplicationExecutionException e) {
				e.printStackTrace();
				tx.rollback();
			
			}

			catch (HisDataAccessException e) {
				e.printStackTrace();
				tx.rollback();
			
			} catch (Exception e) {
				e.printStackTrace();
			
				tx.rollback();
			
			} finally {
				try {
						cstmt.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				tx.close();
			}
			return ip;
		}
	 private static void recursiveRead(Node node, Class referenceClass,HisAppletConfigurator imageUtilityConfiguratorDetails) throws IllegalArgumentException, DOMException, IllegalAccessException {
			if(node.getChildNodes()!=null && node.getChildNodes().getLength()!=0)
			{
				//System.out.println("Lentgh of child  - > "+node.getChildNodes().getLength());
				for(int i=0;i<node.getChildNodes().getLength();i++)
				{
					Node child=node.getChildNodes().item(i);
					//System.out.println(node.getNodeName()+"   ----child Node------ "+child.getNodeName());
					if(child.getNodeType()==Node.TEXT_NODE)
					{
						System.out.println(node.getNodeName());
						Field field=null;
						try
						{
							field=referenceClass.getDeclaredField(node.getNodeName());
						}
						catch(Exception ex)
						{
							
						}
						
						if(field!=null)
							field.set(imageUtilityConfiguratorDetails,node.getTextContent());
					}
					
					if(child.getChildNodes().getLength()!=0)
					{
						try
						{
							recursiveRead(child, referenceClass, imageUtilityConfiguratorDetails);	
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
						
					}
					
				}
				
			}
			
		}
	 public static void recursiveRead(Node node,Class referenceClass,ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails) throws IllegalArgumentException, SecurityException, DOMException, IllegalAccessException, NoSuchFieldException
		{
			//System.out.println(node.getNodeName()+"   ---------- "+node.getNodeType()+"  --------- "+node.getTextContent());
			//System.out.println("setting variable "+node.getNodeName()+"  ---> "+node.getNodeValue());
			if(node.getChildNodes()!=null && node.getChildNodes().getLength()!=0)
			{
				//System.out.println("Lentgh of child  - > "+node.getChildNodes().getLength());
				for(int i=0;i<node.getChildNodes().getLength();i++)
				{
					Node child=node.getChildNodes().item(i);
					//System.out.println(node.getNodeName()+"   ----child Node------ "+child.getNodeName());
					if(child.getNodeType()==Node.TEXT_NODE)
					{
						System.out.println(node.getNodeName());
						Field field=null;
						try
						{
							field=referenceClass.getDeclaredField(node.getNodeName());
						}
						catch(Exception ex)
						{
							
						}
						
						if(field!=null)
							field.set(imageUtilityConfiguratorDetails,node.getTextContent());
					}
					
					if(child.getChildNodes().getLength()!=0)
					{
						try
						{
							recursiveRead(child, referenceClass, imageUtilityConfiguratorDetails);	
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
						
					}
					
				}
				
			}
			
		}
	 
	 public Document loadTemplateQuery(String hospitalCode)
		{
		 	System.out.println("<-loadTemplateQuery->");
			Document vObj = null;
			java.io.InputStream ip = null;
			ip=getClobObjectFromDatabaseTemplateQueryDocument(hospitalCode);
			try {
				vObj=builder.parse(ip);
				String str="investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_TEMPLATEQUERYFILE;
				investigationDocumentCacheVO.put(str, vObj);
				System.out.println("loadEachNodeOfTemplateQuerySeparately");
				try
				{
				System.out.println(" InvestigationDocumentCacheManage://allquery");
				XPath xpath=getXPathFactory().newXPath();
				XPathExpression expr = xpath.compile("//allQuery");///callimagenode[@id="+id+"]"
				Object result = expr.evaluate(vObj, XPathConstants.NODESET);
				NodeList nodes = (NodeList) result;
				Node rootNode=null;
				for (int i = 0; i < nodes.getLength(); i++) 
				{
				  	rootNode=nodes.item(i);
				}
				
				NodeList queryNodes= rootNode.getChildNodes();
				for (int i = 0; i < queryNodes.getLength(); i++) 
				{
				  	Node queryNode=queryNodes.item(i);
				  	investigationDocumentCacheVO.put(str+"_"+queryNode.getAttributes().getNamedItem("templateQueryCode").getNodeValue(), queryNode);
				  	
				}
				
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
				
			} catch (SAXException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (CacheException e) {
				e.printStackTrace();
			}
			
			
			
			return vObj;
		}
	 public String getTemplateQuery(String templateQueryCode,String hospitalCode)
	 {
		 System.out.println("getTemplateQuery->"+templateQueryCode);
		 String query=null;
		 String str="investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_TEMPLATEQUERYFILE;
		 Node queryNode=(Node)investigationDocumentCacheVO.get(str+"_"+templateQueryCode);
		 
		 if(queryNode==null)
		 {
			 loadTemplateQuery(hospitalCode);
			 queryNode=(Node)investigationDocumentCacheVO.get(str+"_"+templateQueryCode);
		 }
		 	
		 query=queryNode.getAttributes().getNamedItem("templateQuery").getNodeValue();
		 
		 return query;
	 }
	 
	 public Document loadAppletUserInformationXML(String hospitalCode)
		{
		 System.out.println("loadAppletUserInformationXML");
			Document vObj = null;
			java.io.InputStream ip = null;
			
			try {
				ip=getClobObjectFromDatabaseImageUtilityConfDocument(hospitalCode);
				vObj=builder.parse(ip);
				String str="investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_IMAGEUTILITYUSERINFORMATION;
				investigationDocumentCacheVO.put(str, vObj);
				System.out.println("loadEachNodeOfTemplateQuerySeparately");
				try
				{
				System.out.println(" ://rootNode");
				XPath xpath=getXPathFactory().newXPath();
				XPathExpression expr = xpath.compile("//rootNode");///callimagenode[@id="+id+"]"
				Object result = expr.evaluate(vObj, XPathConstants.NODESET);
				NodeList nodes = (NodeList) result;
				Node rootNode=null;
				for (int i = 0; i < nodes.getLength(); i++) 
				{
				  	rootNode=nodes.item(i);
				}
				
				NodeList callingImageNodes= rootNode.getChildNodes();
				for (int i = 0; i < callingImageNodes.getLength(); i++) 
				{
				  	Node callingImageNode=callingImageNodes.item(i);
				  	
				  	investigationDocumentCacheVO.put(str+"_"+callingImageNode.getAttributes().getNamedItem("id").getNodeValue(), callingImageNode);
				   	//Node imageUtilityConfiguratorDetailsNode=(Node)investigationDocumentCacheVO.get(str+"_"+callingImageNode.getAttributes().getNamedItem("id").getNodeValue());
				   	ImageUtilityConfiguratorDetails	imageUtilityConfiguratorDetails =new ImageUtilityConfiguratorDetails();
					
					
					Class referenceClass=imageUtilityConfiguratorDetails.getClass();
					imageUtilityConfiguratorDetails.setId(callingImageNode.getAttributes().getNamedItem("id").getNodeValue());
					recursiveRead(callingImageNode, referenceClass, imageUtilityConfiguratorDetails);
					System.out.println(imageUtilityConfiguratorDetails);
					investigationDocumentCacheVO.put(str+"_"+callingImageNode.getAttributes().getNamedItem("id").getNodeValue(),imageUtilityConfiguratorDetails);
				  	
				}
				
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
				
			} catch (SAXException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (CacheException e) {
				e.printStackTrace();
			}
			
			
			
			return vObj;
		}
	 public ImageUtilityConfiguratorDetails getImageUtilityConfiguratorDetails(String id,String hospitalCode)
	 {
		 System.out.println("getImageUtilityConfiguratorDetails->"+id);
		 ImageUtilityConfiguratorDetails imageUtilityConfiguratorDetails=null;
		 String str="investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_IMAGEUTILITYUSERINFORMATION;
		 imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)investigationDocumentCacheVO.get(str+"_"+id);
		 if(imageUtilityConfiguratorDetails==null)
		 {
			 loadAppletUserInformationXML(hospitalCode);
			 imageUtilityConfiguratorDetails=(ImageUtilityConfiguratorDetails)investigationDocumentCacheVO.get(str+"_"+id);
		 }
		 
		 return imageUtilityConfiguratorDetails;
		 
	 }
	 

	 public Document loadAppletConfigurator(String hospitalCode)
		{
			Document vObj = null;
			java.io.InputStream ip = null;
			
			try {
				ip=getClobObjectFromDatabaseAppletConfiguratorDocument(hospitalCode);
				vObj=builder.parse(ip);
				investigationDocumentCacheVO.put("investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_APPLETCONFIGURATOR, vObj);
				System.out.println("loadEachNodeOfloadAppletConfiguratorSeparately");
				try
				{
				System.out.println(" ://rootNode");
				XPath xpath=getXPathFactory().newXPath();
				XPathExpression expr = xpath.compile("//rootNode");///callimagenode[@id="+id+"]"
				Object result = expr.evaluate(vObj, XPathConstants.NODESET);
				NodeList nodes = (NodeList) result;
				Node rootNode=null;
				for (int i = 0; i < nodes.getLength(); i++) 
				{
				  	rootNode=nodes.item(i);
				}
					
				HisAppletConfigurator imageUtilityConfiguratorDetails=null;	
				imageUtilityConfiguratorDetails=(HisAppletConfigurator) investigationDocumentCacheVO.get("investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_APPLETCONFIGURATOR+"_imageUtilityConfiguratorDetails");
				if(imageUtilityConfiguratorDetails==null)
					 imageUtilityConfiguratorDetails=new HisAppletConfigurator();
				
			    Class referenceClass=imageUtilityConfiguratorDetails.getClass();
				recursiveRead(rootNode, referenceClass, imageUtilityConfiguratorDetails);
				System.out.println(imageUtilityConfiguratorDetails);
				investigationDocumentCacheVO.put("investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_APPLETCONFIGURATOR+"_imageUtilityConfiguratorDetails",imageUtilityConfiguratorDetails);
			    
				InvestigationStaticConfigurator.databasepassword=imageUtilityConfiguratorDetails.getDatabasepassword();
				InvestigationStaticConfigurator.databaseusername=imageUtilityConfiguratorDetails.getDatabaseusername();
				InvestigationStaticConfigurator.patientretrievefileftpaddress=imageUtilityConfiguratorDetails.getPatientretrievefileftpaddress();
				InvestigationStaticConfigurator.patientcreatefileftpaddress=imageUtilityConfiguratorDetails.getPatientcreatefileftpaddress();
				InvestigationStaticConfigurator.patientfileftpusername=imageUtilityConfiguratorDetails.getPatientfileftpusername();
				InvestigationStaticConfigurator.patientfileftppassword=imageUtilityConfiguratorDetails.getPatientfileftppassword();
				InvestigationStaticConfigurator.checkingforsecureprinting=imageUtilityConfiguratorDetails.getCheckingforsecureprinting();
				InvestigationStaticConfigurator.sampleCollectionPrinter=imageUtilityConfiguratorDetails.getSampleCollectionPrinter();
				InvestigationStaticConfigurator.sampleCollectionPrinting=imageUtilityConfiguratorDetails.getSampleCollectionPrinting();
				InvestigationStaticConfigurator.sampleCollectionPrinterUserName=imageUtilityConfiguratorDetails.getSampleCollectionPrinterUserName();
				InvestigationStaticConfigurator.sampleCollectionPrinterPassword=imageUtilityConfiguratorDetails.getSampleCollectionPrinterPassword();
				InvestigationStaticConfigurator.sampleCollectionPrinterShareName=imageUtilityConfiguratorDetails.getSampleCollectionPrinterShareName();
				InvestigationStaticConfigurator.noOfCopiesOfBarcodePrint_SampleCollection=imageUtilityConfiguratorDetails.getNoOfCopiesOfBarcodePrint_SampleCollection();
				InvestigationStaticConfigurator.noOfCopiesOfBarcodePrint_duplicateSampleLabel=imageUtilityConfiguratorDetails.getNoOfCopiesOfBarcodePrint_duplicateSampleLabel();
				InvestigationStaticConfigurator.hospitalnameatreport =imageUtilityConfiguratorDetails.getHospitalnameatreport();
				InvestigationStaticConfigurator.hospitaladdressatreport =imageUtilityConfiguratorDetails.getHospitaladdressatreport();
				InvestigationStaticConfigurator.specialstringatreport=imageUtilityConfiguratorDetails.getSpecialstringatreport();
				InvestigationStaticConfigurator.scanningftpurl =imageUtilityConfiguratorDetails.getScanningftpurl();
				InvestigationStaticConfigurator.scanningftpuser=imageUtilityConfiguratorDetails.getScanningftpuser();
				InvestigationStaticConfigurator.scanningftppassword=imageUtilityConfiguratorDetails.getScanningftppassword();
				InvestigationStaticConfigurator.resultprintingftpaddress=imageUtilityConfiguratorDetails.getResultprintingFTPAddress();
				
			    System.out.println("Database user name::"+imageUtilityConfiguratorDetails.getDatabaseusername());
			    System.out.println("Database pasword::"+imageUtilityConfiguratorDetails.getDatabasepassword());
			    System.out.println("InvestigationStaticConfigurator.patientretrievefileftpaddress="+InvestigationStaticConfigurator.patientretrievefileftpaddress);
				System.out.println("InvestigationStaticConfigurator.patientcreatefileftpaddress="+InvestigationStaticConfigurator.patientcreatefileftpaddress);
				System.out.println("InvestigationStaticConfigurator.patientfileftpusername="+InvestigationStaticConfigurator.patientfileftpusername);
				System.out.println("InvestigationStaticConfigurator.patientfileftppassword="+InvestigationStaticConfigurator.patientfileftppassword);
				System.out.println("InvestigationStaticConfigurator.checkingforsecureprinting="+InvestigationStaticConfigurator.checkingforsecureprinting);
				System.out.println("InvestigationStaticConfigurator.sampleCollectionPrinting="+InvestigationStaticConfigurator.sampleCollectionPrinting);
				System.out.println("InvestigationStaticConfigurator.sampleCollectionPrinter="+InvestigationStaticConfigurator.sampleCollectionPrinter);
				System.out.println("InvestigationStaticConfigurator.sampleCollectionPrinterShareName="+InvestigationStaticConfigurator.sampleCollectionPrinterShareName);
				System.out.println("InvestigationStaticConfigurator.sampleCollectionPrinterPassword="+InvestigationStaticConfigurator.sampleCollectionPrinterPassword);
				System.out.println("InvestigationStaticConfigurator.sampleCollectionPrinterUserName="+InvestigationStaticConfigurator.sampleCollectionPrinterUserName);
				System.out.println("InvestigationStaticConfigurator.noOfCopiesOfBarcodePrint_SampleCollection="+InvestigationStaticConfigurator.noOfCopiesOfBarcodePrint_SampleCollection);
				System.out.println("InvestigationStaticConfigurator.noOfCopiesOfBarcodePrint_duplicateSampleLabel="+InvestigationStaticConfigurator.noOfCopiesOfBarcodePrint_duplicateSampleLabel);
				System.out.println("InvestigationStaticConfigurator.hospitalnameatreport="+InvestigationStaticConfigurator.hospitalnameatreport);
				System.out.println("InvestigationStaticConfigurator.hospitaladdressatreport="+InvestigationStaticConfigurator.hospitaladdressatreport);
				System.out.println("InvestigationStaticConfigurator.specialstringatreport="+InvestigationStaticConfigurator.specialstringatreport);
				
				
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
				
			} catch (SAXException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (CacheException e) {
				e.printStackTrace();
			}
			
			
			
			return vObj;
		}
	 
	 public Document loadAppletConfigurator(String ip, String hospitalCode)
		{
		 Document vObj=null;
		try
			{
			InputStream iop=new ByteArrayInputStream(ip.getBytes("UTF-8"));

				vObj=builder.parse(iop);
				System.out.println(" ://rootNode");
				XPath xpath=getXPathFactory().newXPath();
				XPathExpression expr = xpath.compile("//rootNode");///callimagenode[@id="+id+"]"
				Object result = expr.evaluate(vObj, XPathConstants.NODESET);
				NodeList nodes = (NodeList) result;
				Node rootNode=null;
				for (int i = 0; i < nodes.getLength(); i++) 
				{
				  	rootNode=nodes.item(i);
				}
					
				HisAppletConfigurator imageUtilityConfiguratorDetails=null;	
				imageUtilityConfiguratorDetails=(HisAppletConfigurator) investigationDocumentCacheVO.get("investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_APPLETCONFIGURATOR+"_imageUtilityConfiguratorDetails");
				if(imageUtilityConfiguratorDetails==null)
					 imageUtilityConfiguratorDetails=new HisAppletConfigurator();
				
			    Class referenceClass=imageUtilityConfiguratorDetails.getClass();
				recursiveRead(rootNode, referenceClass, imageUtilityConfiguratorDetails);
				System.out.println(imageUtilityConfiguratorDetails);
				investigationDocumentCacheVO.put("investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_APPLETCONFIGURATOR+"_imageUtilityConfiguratorDetails",imageUtilityConfiguratorDetails);
			    
				InvestigationStaticConfigurator.databasepassword=imageUtilityConfiguratorDetails.getDatabasepassword();
				InvestigationStaticConfigurator.databaseusername=imageUtilityConfiguratorDetails.getDatabaseusername();
				InvestigationStaticConfigurator.patientretrievefileftpaddress=imageUtilityConfiguratorDetails.getPatientretrievefileftpaddress();
				InvestigationStaticConfigurator.patientcreatefileftpaddress=imageUtilityConfiguratorDetails.getPatientcreatefileftpaddress();
				InvestigationStaticConfigurator.patientfileftpusername=imageUtilityConfiguratorDetails.getPatientfileftpusername();
				InvestigationStaticConfigurator.patientfileftppassword=imageUtilityConfiguratorDetails.getPatientfileftppassword();
				InvestigationStaticConfigurator.checkingforsecureprinting=imageUtilityConfiguratorDetails.getCheckingforsecureprinting();
				InvestigationStaticConfigurator.sampleCollectionPrinter=imageUtilityConfiguratorDetails.getSampleCollectionPrinter();
				InvestigationStaticConfigurator.sampleCollectionPrinting=imageUtilityConfiguratorDetails.getSampleCollectionPrinting();
				InvestigationStaticConfigurator.sampleCollectionPrinterUserName=imageUtilityConfiguratorDetails.getSampleCollectionPrinterUserName();
				InvestigationStaticConfigurator.sampleCollectionPrinterPassword=imageUtilityConfiguratorDetails.getSampleCollectionPrinterPassword();
				InvestigationStaticConfigurator.sampleCollectionPrinterShareName=imageUtilityConfiguratorDetails.getSampleCollectionPrinterShareName();
				InvestigationStaticConfigurator.noOfCopiesOfBarcodePrint_SampleCollection=imageUtilityConfiguratorDetails.getNoOfCopiesOfBarcodePrint_SampleCollection();
				InvestigationStaticConfigurator.noOfCopiesOfBarcodePrint_duplicateSampleLabel=imageUtilityConfiguratorDetails.getNoOfCopiesOfBarcodePrint_duplicateSampleLabel();
				
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
			
			
			
			return vObj;
		}
		
		public HisAppletConfigurator getAppletConfigurator(String hospitalCode)
		{
			HisAppletConfigurator imageUtilityConfiguratorDetails=null;
			imageUtilityConfiguratorDetails=(HisAppletConfigurator) investigationDocumentCacheVO.get("investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_APPLETCONFIGURATOR+"_imageUtilityConfiguratorDetails");
			if(imageUtilityConfiguratorDetails==null)
			{
				loadAppletConfigurator(hospitalCode);
			}
			imageUtilityConfiguratorDetails=(HisAppletConfigurator) investigationDocumentCacheVO.get("investigationDocumentCacheVO"+hospitalCode+InvestigationConfig.XML_APPLETCONFIGURATOR+"_imageUtilityConfiguratorDetails");
			
			
			return imageUtilityConfiguratorDetails;
		}
		public void reloadAppletConfigurator(String hospitalCode)
		{
			loadAppletConfigurator(hospitalCode);
					
		}
		
	 //create function by Rabindra Nath on 26 Aug 2011 for integrate BarCode functionality to GGSH start
		public static void  callPrinter(String printerName,PrinterVO printerVO )
		{
			BarcodeFileGenerator barcodeFileGeneratorObj=new BarcodeFileGenerator();
			Class classObj=barcodeFileGeneratorObj.getClass();
			Class [] classParameterTypesArray=new Class[1];
			
			classParameterTypesArray[0]=printerVO.getClass();
			Method actualMethod=null;	
				
			try {
				 actualMethod=classObj.getMethod(printerName, classParameterTypesArray);
				 System.out.println("actualMethod-->"+actualMethod);
				 if(actualMethod!=null)
				 {
					 synchronized (barcodeFileGeneratorObj) {
						 actualMethod.invoke(barcodeFileGeneratorObj, printerVO);
						 barcodeFileGeneratorObj.printSlip(printerVO.getPrinterRemoteAddr(), printerVO.getPrinterFileName(),printerVO.getOsType());
					}
					 
				 }
				 
			} catch (SecurityException e) {
				
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				
				e.printStackTrace();
			}
			
			
			
		}*/
		 //create function by Rabindra Nath on 26 Aug 2011 for integrate BarCode functionality to GGSH start	
		
		
		
	 
}
