/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataHelper;

import DataProcessing.PGTemplateProcessing;
import Logging.ServiceLogger;
import MongoHelper.MongoXmlHandler;
import TemplateHelper.FourStringObject;
import TemplateHelper.TriStringObject;
import TemplateHelper.vo.ResultEntryVO;
import TemplateHelper.vo.ResultEntryVOGroupByValidatedBy;
import TemplateHelper.vo.UserVO;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import mongoapp.QScheduler;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class PGDataHelper {

    //static Connection connection = null;
    private static String connectionURL;
    private static String connectionUsername;
    private static String connectionPassword;
    private static TransformerFactory transformerFactory;
    private static DocumentBuilderFactory builderFactory;
    private static DocumentBuilder builder;
    XPathFactory factory = null;
    public static PGDataHelper instance;
    private static int checkedOut = 0;
    private static HashMap<String, Node> nodeCollection;
    private static ComboPooledDataSource cpds;

    private PGDataHelper() {
        try {
            builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();
            factory = XPathFactory.newInstance();
            transformerFactory = TransformerFactory.newInstance();
            nodeCollection = new HashMap<String, Node>();

        } catch (Exception e) {
            e.printStackTrace();
            // Handle cache region initialization failure
        }

        // Do other initialization that may be necessary, such as getting
        // references to any data access classes we may need to populate value objects later
    }

    public static PGDataHelper getInstance() {
        synchronized (PGDataHelper.class) {


      	/*  Connection conn=PGDataHelper.getInstance().getConnection();
      	   
      	  try {
			if(conn==null || conn.isClosed())
			  {
				instance=null;  
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	*/
        	
        	
        	//return new PGDataHelper();
            if (instance == null) {
                instance = new PGDataHelper();
            }
        }
        synchronized (instance) {
            instance.checkedOut++;
        }


        //instance = new PGDataHelper();
        return instance;

    }

    
  //by chandann
    
    public static Connection getConnection() {
         Connection connection = null;
         try {
             
                 connection = createPostgresConnection();//(connectionURL, connectionUsername, connectionPassword);
             }
         catch(Exception e)
         {
         	e.printStackTrace();
         }
         
         
         return connection;
     }
	 
    
    /*public static Connection getConnection() {
        Connection connection = null;
        try {
          
            if (cpds != null) {

                connection = cpds.getConnection();

                connection.setAutoCommit(false);

                if(connection != null && !connection.isClosed())
                	return connection;
                else
                	throw new SQLException();
            }
         
        } catch (SQLException ex) {
            Log(Level.SEVERE, ex);
            if (cpds != null) {
                cpds.close();
                connection = createPostgresConnection();//(connectionURL, connectionUsername, connectionPassword);
            }
        }
        return connection;
    }
*/
    public static void closeConnection()
    {
    	if(cpds != null)
    	{
    		cpds.close();
    	}
    }
    public static Connection createPostgresConnection() {

        connectionURL = PropertiesHelper.getPGConnectionURI();
        connectionUsername = PropertiesHelper.getPGUserName();
        connectionPassword = PropertiesHelper.getPGPassword();
        return createPostgresConnection(connectionURL, connectionUsername, connectionPassword);
    }

    /**
     * creates connection to a postgres database
     *
     * @param connectionURL
     * @param username
     * @param password
     */
  //  private static int k = 0;
  /*  private static Connection createPostgresConnection(String url, String username, String password) {
           if (connection != null) {
         return connection;
         }
         
        connectionURL = url;
        connectionUsername = username;
        connectionPassword = password;
        Connection connection = null;
        try {
   //     	System.out.println("pg called " + ++k);
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass("com.edb.Driver"); //loads the jdbc driver
            cpds.setJdbcUrl(connectionURL);
            cpds.setUser(connectionUsername);
            cpds.setPassword(connectionPassword);

// the settings below are optional -- c3p0 can work with defaults
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            cpds.setCheckoutTimeout(2000);
            //Class.forName("org.postgresql.Driver");
            // Class.forName();
            //  connection = DriverManager.getConnection(connectionURL, connectionUsername, connectionPassword);
            connection = cpds.getConnection();
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Log(Level.INFO, "Connection Created to " + connectionURL);
        return connection;
    }
*/    
    // by chandannnnnn
    private static Connection createPostgresConnection(final String url, final String username, final String password) {
        PGDataHelper.connectionURL = url;
        PGDataHelper.connectionUsername = username;
        PGDataHelper.connectionPassword = password;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(PGDataHelper.connectionURL, PGDataHelper.connectionUsername, PGDataHelper.connectionPassword);
            connection.setAutoCommit(false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        Log(Level.INFO, "Connection Created to " + PGDataHelper.connectionURL);
        return connection;
    }
    
    public Document getNewDocument() {

        return builder.newDocument();
    }

    public Transformer getTransformerObject(String id) {
        Transformer transformer = null;
        try {
            InputStream ip = getClobObjectFromDatabaseForDocument(id);
            transformer = transformerFactory.newTransformer(new StreamSource(ip));
            //investigationStylesheetConfigurationCacheVO.put("investigationStylesheetConfigurationCacheVO" + id, transformer);			
        } catch (Exception e) {
            e.printStackTrace();
			//LOGGER_INV.log(Level.INFO,"id->"+id);
            //LOGGER_INV.log(Level.INFO,"id->"+id+e.getCause());

            // Handle failure putting object to cache
        }
        return transformer;

    }
    
    

//    public Node getTemplateNodeObj(String id, String nodeId) {
//        Document document = null;
//
//        Node node = null;
//        String dynamicTemplateID = null;
//        boolean isDynamicTemplate = false;
//        if (document == null) {
//        	dynamicTemplateID = getDynamicTemplateStatus(nodeId);
//        	if(dynamicTemplateID != null && !dynamicTemplateID.isEmpty())
//        	{
//        		nodeId = dynamicTemplateID;
//        		document = loadDynamicDocumentObj(dynamicTemplateID);
//        		isDynamicTemplate = true;
//        	}
//        	else
//        	{
//        		document = loadDocumentObj(id);
//        	}
//        		
//        }
//
//        if (document != null) {
//            //LOGGER_INV.log(Level.INFO,"loading... Node form memory -->"+id);
//        	String cacheString = "investigationDocumentCacheVO" + id;
//        	if(isDynamicTemplate)
//        		cacheString = "dynamicInvestigationDocumentCacheVO";
//            node = nodeCollection.get(cacheString + "_" + nodeId);
//            //LOGGER_INV.log(Level.INFO,"Getting Key ::->"+"investigationDocumentCacheVO"+id+"_"+nodeId);
//        }
//        return node;
//    }

    public Node getTemplateNodeObj(String id, String nodeId, boolean isPrintWithDynamicTemplate) {
        Document document = null;

        Node node = null;
        String dynamicTemplateID = nodeId;
        boolean isDynamicTemplate = isPrintWithDynamicTemplate;
    	     	
             if (document == null) {
             	
             	if(isPrintWithDynamicTemplate)
             	{
             		//nodeId = dynamicTemplateID;

             		document = loadDynamicDocumentObj(dynamicTemplateID);
             		isDynamicTemplate = true;
             	}
             	else
             	{
             		  if(PropertiesHelper.getfetchnoramltemplatetestwise())
             			{
             		document = loadDocumentObj_testwise(id,nodeId);        		
             			}
             		  else
             		  {
             			 document = loadDocumentObj(id);
             		  }
             		  //changed by chandan to fetch template from document_store_mst_testwise inplace of document_store_mst
             		//document = loadDocumentObj(id);        		
             	}
             		
             }

        if (document != null) {
            //LOGGER_INV.log(Level.INFO,"loading... Node form memory -->"+id);
        	String cacheString = "investigationDocumentCacheVO" + id;
        	if(isDynamicTemplate)
        		cacheString = "dynamicInvestigationDocumentCacheVO";
            node = nodeCollection.get(cacheString + "_" + nodeId);
            //LOGGER_INV.log(Level.INFO,"Getting Key ::->"+"investigationDocumentCacheVO"+id+"_"+nodeId);
        }
        return node;
    }

    
    
    public String getDynamicTemplateStatus(String code, String hospitalCode, boolean isGroupWithDynamicTemplate, String labCode)
    {
    	 // testCode is Group Code if isGroupWithDynamicTemplate = true
    	String dynamicTemplateID = null;
    	  Connection conn = PGDataHelper.getInstance().getConnection();
          java.io.InputStream ip = null;
          //CallableStatement cstmt = null;
          PreparedStatement pstmt = null;
          boolean isDynamic = false;
          String query = QueryConfig.Q_GET_DYNAMIC_TESTTEMPLATE_STATUS;
        		  
          try {
             if(isGroupWithDynamicTemplate)
            	 query = QueryConfig.Q_GET_DYNAMIC_GROUPTEMPLATE_STATUS;
            
             
             pstmt = conn.prepareStatement(query);
              pstmt.setString(1, code);
              
              if(isGroupWithDynamicTemplate)
              {
            	  pstmt.setString(2, hospitalCode);
            	  pstmt.setString(3, labCode);
              }
              
              
              
       //       System.out.println("Group Template Info: GCode " + code + "HCode " + hospitalCode + "LCode " + labCode);
              
              
              
              if(conn==null || conn.isClosed())
              {
            	  
            	
              }
              else
            	  
              {   ResultSet rs = pstmt.executeQuery();
              String strIsDynamic = null;
              if (rs.next()) {
            	  strIsDynamic  = rs.getString(1);
                  dynamicTemplateID = rs.getString(2);
              }

              if(strIsDynamic != null && strIsDynamic.equals("1"))
            	  isDynamic = true;
              }
              
          } catch (Exception e) {
              //  e.printStackTrace();
              Log(Level.SEVERE, e);
             

          } finally {
              try {
                  pstmt.close();
                  if (conn != null && !conn.isClosed()) {
                      conn.commit();
                      conn.close();
                  }
              } catch (SQLException e) {

                  //e.printStackTrace();
                  Log(Level.SEVERE, e);
              }
          }
          
          return dynamicTemplateID;

    }
    
    private Document loadDynamicDocumentObj(String dynamicTemplateID)
    {
    	Document vObj = null;
        java.io.InputStream ip = null;

        try {
            boolean found = false;			
           
            ip = getClobObjectForDynamicTemplate(dynamicTemplateID);

            // set found to true if it was found
            found = true;
            // cache the value object if found
            if (found) {
                if (ip != null) {
                    vObj = builder.parse(ip);

                } else {
                    Log(Level.SEVERE, "Input Stream is null");
                    return null;
                }
                
                loadEachNodeOfDocumentSeparately(vObj, "dynamicInvestigationDocumentCacheVO");                
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log(Level.SEVERE, e);
            // Handle failure putting object to cache
        }

        return vObj;
    }
    
    //commented by Ashutosh 20/07/2018
    
    public Document loadDocumentObj(String id) {
        Document vObj = null;
        java.io.InputStream ip = null;

        try {
            boolean found = false;			
            switch ((Integer.parseInt(id))) {
                case 1:
                    ip = getClobObjectFromDatabaseForResultEntryFormDocument();

                    break;                
            }

            // set found to true if it was found
            found = true;
            // cache the value object if found
            if (found) {
                if (ip != null) {
                    vObj = builder.parse(ip);

                } else {
                    Log(Level.SEVERE, "Input Stream is null");
                    return null;
                }
                
                loadEachNodeOfDocumentSeparately(vObj, "investigationDocumentCacheVO" + id);                
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log(Level.SEVERE, e);
            // Handle failure putting object to cache
        }

        return vObj;
    }
    
   /* public Document loadDocumentObj(String id) {
    Document vObj = null;
    java.io.InputStream ip = null;

    try {
        boolean found = false;			
        switch ((Integer.parseInt(id))) {
            case 1:
                ip = null;//getClobObjectFromDatabaseForResultEntryFormDocument();
                //System.out.println("read template");
                String filePath = PropertiesHelper.getTemplateXMLPath();
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
        //e.printStackTrace();
        Log(Level.SEVERE, e);
        // Handle failure putting object to cache
    }

    return vObj;
    }*/


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
            Log(Level.SEVERE, ex);
        }

    }

    private InputStream getClobObjectForDynamicTemplate(String dynamicTemplateID)
    {
    	Connection conn = PGDataHelper.getInstance().getConnection();
        java.io.InputStream ip = null;
        //CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        try {           
            pstmt = conn.prepareStatement(QueryConfig.Q_GET_DYNAMIC_TESTTEMPLATE);
            pstmt.setString(1, dynamicTemplateID);
            
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String requisitionFormClob = rs.getString(1);
                if (requisitionFormClob != null) {
                    ip = new ByteArrayInputStream(requisitionFormClob.getBytes());
                }

            }
            }
        } catch (Exception e) {
            //  e.printStackTrace();
            Log(Level.SEVERE, e);
           

        } finally {
            try {
                pstmt.close();
                if (conn != null && !conn.isClosed()) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {

                //e.printStackTrace();
                Log(Level.SEVERE, e);
            }

        }

        //LOGGER_INV.log(Level.INFO,"input stream =="+ip);
        return ip;
    }
    
    //commented by Ashutosh 15/03/2018
    
    public InputStream getClobObjectFromDatabaseForResultEntryFormDocument() {
        //  Siddharth Srivastava, 09/03/2015	
        //	Check all the conditions before implementing to store InputStream to session 

        Connection conn = PGDataHelper.getInstance().getConnection();
        java.io.InputStream ip = null;
        //CallableStatement cstmt = null;
        PreparedStatement pstmt = null;
        try {
           

            pstmt = conn.prepareStatement(QueryConfig.Q_GET_TESTTEMPLATE_XML);
            
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String requisitionFormClob = rs.getString(1);
                if (requisitionFormClob != null) {
                    ip = new ByteArrayInputStream(requisitionFormClob.getBytes());
                }

            }
            }

        } catch (Exception e) {
            //  e.printStackTrace();
            Log(Level.SEVERE, e);
           

        } finally {
            try {
                pstmt.close();
                if (conn != null && !conn.isClosed()) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {

                //e.printStackTrace();
                Log(Level.SEVERE, e);
            }

        }

        //LOGGER_INV.log(Level.INFO,"input stream =="+ip);
        return ip;
    }

    
    public InputStream getClobObjectFromDatabaseForDocument(String id) {

        Connection conn = PGDataHelper.getInstance().getConnection();
        java.io.InputStream ip = null;
        PreparedStatement pstmt = null;
        // CallableStatement cstmt = null;
        try {           

            pstmt = conn.prepareStatement(QueryConfig.Q_GET_STYLESHEET);
            pstmt.setString(1, id);
            
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String requisitionFormClob = rs.getString(1);
              //  System.out.println("the xsl printed is for id: "+id+" is : "+rs.getString(1));
                if (requisitionFormClob != null) {
                    ip = new ByteArrayInputStream(requisitionFormClob.getBytes());
                }
            } else {
                // System.out.println("Result Entry Stylesheet not found");
                Log(Level.SEVERE, "Result Entry StyleSheet not found.");
            }
            }

        } catch (SQLException ex) {
            try {
            	if(conn!=null && !conn.isClosed())
                conn.rollback();
            } catch (SQLException ex1) {
                Log(Level.SEVERE, ex1);
            }
            ex.printStackTrace();
            Log(Level.SEVERE, ex);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }
        return ip;
    }

    /**
     * This function returns the list of results that needs to be set to
     * corresponding value tag in the patient XML
     *
     * @param resultEntryVO
     * @return
     */
    public List<FourStringObject> getResultEntryValidationList(ResultEntryVO resultEntryVO) {
        String query = null;
        ResultSet rs = null;
        List<FourStringObject> resultValidationList = new ArrayList<FourStringObject>();

        Connection conn = PGDataHelper.getInstance().getConnection();

        System.out.println("reference range call::::::::dnoooooo:::"+resultEntryVO.getRequisitionDNo()+"testcode====="+resultEntryVO.getTestCode());
        query = QueryConfig.Q_GET_RESULT_FORXML;
        //Log(Level.INFO, "Query :WorkOrder List For Result Validation " + query);

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, resultEntryVO.getRequisitionDNo());
            pstmt.setString(2, resultEntryVO.getHospitalCode());
            // Commented because multisession test not supported yet
            // 14/05/2015
           /* if (resultEntryVO.getIsTestMultiSession().equals("1") && resultEntryVO.getIsMultiSessionRegrouped().equals("0")) {
             pstmt.setString(2, resultEntryVO.getSessionId());
             } else {
             pstmt.setString(2, "0");
             }*/
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	
            	
                String parameterValue = "";
                
                if(rs.getString(2).equals("--"))
                {
                	  parameterValue = "";
                }
                else
                {
                	  parameterValue = rs.getString(2);
                }
                
                String parentCode="";
				
				if(rs.getString(1).equals(rs.getString(3)))
					parentCode=rs.getString(3);
				else
					parentCode=rs.getString(3)+rs.getString(1);
                
             //   Log(Level.INFO, "--------parameterValue =" + parameterValue);
                FourStringObject fourStringObject = new FourStringObject(rs.getString(1), parameterValue, parentCode, rs.getString(4), rs.getString(5));

                if (resultValidationList == null) {
                    resultValidationList = new ArrayList<FourStringObject>();
                }

                resultValidationList.add(fourStringObject);
            }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.commit();
                    conn.close();
                }

            } catch (SQLException sqex) {
                sqex.printStackTrace();
            }
        }
        return resultValidationList;
    }

    public static void processingToPutTheJobs(List<ResultEntryVOGroupByValidatedBy> resultEntryVOGroupByValidatedBy) {

        Map<String, String> reportingCRNo = new HashMap<String, String>(5);
        PreparedStatement pstmt = null;
        Connection conn = PGDataHelper.getInstance().getConnection();

        String query = QueryConfig.Q_PUT_PDF_JOB;

        for (int i = 0; i < resultEntryVOGroupByValidatedBy.size(); i++) {
            ResultEntryVOGroupByValidatedBy voGroupByValidatedBy = resultEntryVOGroupByValidatedBy.get(i);

            if (!reportingCRNo.containsKey(voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode())) {
                reportingCRNo.put(voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode(), voGroupByValidatedBy.getPatCRNo() + voGroupByValidatedBy.getLaboratoryCode());
                try {
                    // conn.commit();
                    //conn = PGDataHelper.getInstance().getConnection();
                    pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, voGroupByValidatedBy.getLaboratoryCode());//labCode
                    pstmt.setString(2, voGroupByValidatedBy.getPatCRNo());//crNo
                    pstmt.setString(3, "1");
                    pstmt.setString(4, "1");
                    pstmt.setString(5, voGroupByValidatedBy.getHospitalCode());
                    pstmt.setString(6, "1");
                    pstmt.setString(7, voGroupByValidatedBy.getIsDeptEntry());
                
                    if(conn==null || conn.isClosed())
                    {
                  	  
                  	
                    }
                    else
                    {
                    pstmt.executeUpdate();
                    }
                    
                    } catch (Exception ex) {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.rollback();
                        }
                    } catch (Exception e) {
                        // e.printStackTrace();
                        Log(Level.SEVERE, e);
                    }
                    Log(Level.SEVERE, ex);
                    //ex.printStackTrace();
                } finally {
                    try {
                        if (conn != null) {
                           // conn.commit();
                            //conn.close();
                        }
                    } catch (Exception e) {
                        Log(Level.SEVERE, e);
                        //e.printStackTrace();
                    }
                }

            }

        }
        
            try {
                conn.commit();
                conn.close();
            } catch (SQLException ex) {
                Log(Level.SEVERE,ex);
            }
            
        
    }

    public static void processingToPutTheJobs(UserVO userVO, Map<String, String> reportingCRNoMap_, Map<String, String> reportingRequisitionNoMap_, int labCodeLength, int crNoLength) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {

            //conn = DriverManager.getConnection(connection.getDatabaseConnection(),InvestigationStaticConfigurator.databaseusername,InvestigationStaticConfigurator.databasepassword);
            // ArrayDescriptor workOrderAD =ArrayDescriptor.createDescriptor("ARRAY_STRING", conn);
           /* String[] requisitions = null;

             if ((reportingRequisitionNoMap_ != null) && reportingRequisitionNoMap_.size() != 0) {
             requisitions = new String[reportingRequisitionNoMap_.size()];
             }*/
            String[] crNos = null;
            if (reportingCRNoMap_ != null) {
                crNos = new String[reportingCRNoMap_.size()];
            }

            if (reportingCRNoMap_ != null) {
                int i = 0;
                for (String key : reportingCRNoMap_.keySet()) {
                    crNos[i++] = reportingCRNoMap_.get(key);
                }
            }

            /*if (reportingRequisitionNoMap_ != null) {
             int i = 0;

             for (String key : reportingRequisitionNoMap_.keySet()) {
             crNos[i++] = reportingRequisitionNoMap_.get(key);
             }
             }*/
            int nProcessed = crNos.length;
            PreparedStatement pstmt = null;
            conn = PGDataHelper.getInstance().getConnection();
            while (nProcessed >= 0) {

                String query = QueryConfig.Q_PUT_PDF_JOB;
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, crNos[nProcessed].substring(crNoLength));//labCode
                pstmt.setString(2, crNos[nProcessed].substring(0, crNoLength));//crNo
            }

            /*  conn.setAutoCommit(false);
             cstmt = conn.prepareCall("{call INV_REPORTENGINE.AddForProceesingforjob(?,?,?,?,?,?)}");
             cstmt.setString(1, labCode);
             cstmt.setObject(2, new oracle.sql.ARRAY(workOrderAD, conn, requisitions));

             cstmt.setObject(3, new oracle.sql.ARRAY(workOrderAD, conn, crNos));
             cstmt.setString(4, InvestigationStaticConfigurator.servernumber);
             cstmt.setString(5, userVO.getHospitalCode());
             cstmt.registerOutParameter(6, OracleTypes.VARCHAR);
             cstmt.execute();*/
            //LOGGER_INV.log(Level.INFO,"INV_REPORTENGINE.AddForProceesingforjob"+cstmt.getString(6));
        } catch (Exception ex) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.commit();
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
    
    
    public static boolean getPrintWithStandardRanges(String testCode,
			String labCode, String hospitalCode) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		String query = QueryConfig.Q_GET_PRINTED_WITHSTANDARD_RANGES;
		
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String printWithStandardRanges = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			 pstmt = conn.prepareStatement(query);
             pstmt.setString(1, testCode);
             pstmt.setString(2, labCode);
             pstmt.setString(3, hospitalCode);
             if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				printWithStandardRanges = rs.getString(1);
			}
			
			
			if(printWithStandardRanges.equals("1") == true)
			{
				returnVal = true;
			}
			else
				returnVal = false;
             }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnVal;
	}
    

    private static void Log(Level level, String msg) {
        ServiceLogger.Log(PGDataHelper.class
                .getName(), level, msg);
    }

    private static void Log(Level level, Exception e) {
        ServiceLogger.Log(PGDataHelper.class
                .getName(), level, e);
    }

	
    
    //function to fetch dept template id for the dept template- puneet
    
    public String getDeptTemplateStatus(String code, String hospitalCode, boolean isGroupWithDynamicTemplate, String labCode)
    {
    	 // testCode is Group Code if isGroupWithDynamicTemplate = true
    	String dynamicTemplateID = null;
    	  Connection conn = PGDataHelper.getInstance().getConnection();
          java.io.InputStream ip = null;
          //CallableStatement cstmt = null;
          PreparedStatement pstmt = null;
          boolean isDynamic = false;
          String query = QueryConfig.Q_GET_DEPT_TESTTEMPLATE_STATUS;
        		  
          try {
             if(isGroupWithDynamicTemplate)
            	 query = QueryConfig.Q_GET_DYNAMIC_GROUPTEMPLATE_STATUS;//to be changed
              pstmt = conn.prepareStatement(query);
              pstmt.setString(1, code);
              
              if(isGroupWithDynamicTemplate)
              {
            	  pstmt.setString(2, hospitalCode);
            	  pstmt.setString(3, labCode);
              }
            //  System.out.println("Group Template Info: GCode " + code + "HCode " + hospitalCode + "LCode " + labCode);
              if(conn==null || conn.isClosed())
              {
            	  
            	
              }
              else
              {
              ResultSet rs = pstmt.executeQuery();
              String strIsDynamic = null;
              if (rs.next()) {
            	  strIsDynamic  = rs.getString(1);
                  dynamicTemplateID = rs.getString(2);
              }

              if(strIsDynamic != null && strIsDynamic.equals("1"))
            	  isDynamic = true;
              }  
          } catch (Exception e) {
              //  e.printStackTrace();
              Log(Level.SEVERE, e);
             

          } finally {
              try {
                  pstmt.close();
                  if (conn != null && !conn.isClosed()) {
                      conn.commit();
                      conn.close();
                  }
              } catch (SQLException e) {

                  //e.printStackTrace();
                  Log(Level.SEVERE, e);
              }
          }
          
          return dynamicTemplateID;

    }
    
    
    //get dept parameter result entry values
    public List<FourStringObject> getDeptResultEntryValidationList(ResultEntryVO resultEntryVO) {
        String query = null;
        ResultSet rs = null;
        List<FourStringObject> resultValidationList = new ArrayList<FourStringObject>();

        Connection conn = PGDataHelper.getInstance().getConnection();

        query = QueryConfig.Q_GET_DEPT_RESULT_FORXML;
        Log(Level.INFO, "Query :WorkOrder List For Result Validation " + query);

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, resultEntryVO.getRequisitionDNo());
            pstmt.setString(2, resultEntryVO.getHospitalCode());
            // Commented because multisession test not supported yet
            // 14/05/2015
           /* if (resultEntryVO.getIsTestMultiSession().equals("1") && resultEntryVO.getIsMultiSessionRegrouped().equals("0")) {
             pstmt.setString(2, resultEntryVO.getSessionId());
             } else {
             pstmt.setString(2, "0");
             }*/
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String parameterValue = rs.getString(2);
                
                String parentCode="";
				
				if(rs.getString(1).equals(rs.getString(3)))
					parentCode=rs.getString(3);
				else
					parentCode=rs.getString(3)+rs.getString(1);
                
                Log(Level.INFO, "--------parameterValue =" + parameterValue);
                FourStringObject fourStringObject = new FourStringObject(rs.getString(1), parameterValue, parentCode, rs.getString(4), rs.getString(5));

                if (resultValidationList == null) {
                    resultValidationList = new ArrayList<FourStringObject>();
                }

                resultValidationList.add(fourStringObject);
            }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.commit();
                    conn.close();
                }

            } catch (SQLException sqex) {
                sqex.printStackTrace();
            }
        }
        return resultValidationList;
    }

    
    public static String getFetchIdForReportHistory() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		String query = QueryConfig.FETCH_SERVICE_ID_REPORT_HISTORY;
		
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			 pstmt = conn.prepareStatement(query);
           
			 if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				ServiceId = rs.getString(1);
			}
			
             }
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ServiceId;
	}
    
    
    public static void insertReportHistory(String serviceId) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String query = QueryConfig.INSERT_HIVT_REPORT_SERVICE_DTL;
		int historySaveFrequency =PropertiesHelper.getReportHistorySaveFrequency();
		String path = QScheduler.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	String decodedPath = URLDecoder.decode(path, "UTF-8");
    	System.out.println("Service Path:"+decodedPath);
		
    	String mongoDbName="";
		String mongoUri="";
		if(PropertiesHelper.getISFtporMongo())
		{
    	mongoDbName=PropertiesHelper.getMongoDBName();
		mongoUri=PropertiesHelper.getMongoConnectionURI();
		}
		else
		{
			mongoDbName="-";
			mongoUri=PropertiesHelper.getFTPConnectionURI();
				
		}
		
		String pgConnectionUri=PropertiesHelper.getPGConnectionURI();
		String reportLogoPath=PropertiesHelper.getReportLogoPath();
		String pgusername=PropertiesHelper.getPGUserName();
		
	     String frequencyString=Integer.toString(historySaveFrequency);
		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			  pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, serviceId);
	            pstmt.setString(2, frequencyString);
	            pstmt.setString(3, mongoDbName);
	            pstmt.setString(4, mongoUri);
	            pstmt.setString(5, pgConnectionUri);
	            pstmt.setString(6, pgusername);
	            pstmt.setString(7, reportLogoPath);
	            pstmt.setString(8, decodedPath);
	            if(conn==null || conn.isClosed())
	              {
	            	  
	            	
	              }
	              else
	              {
	            pstmt.executeUpdate();
	              }
			 if (pstmt != null) {
                 pstmt.close();
             }
			 if(conn!=null && !conn.isClosed())
             conn.commit();
			
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
    
    
    public static void updateReportHistory(String serviceId) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		String query = QueryConfig.UPDATE_HIVT_REPORT_SERVICE_DTL;
		
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			  pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, serviceId);
	        
	            if(conn==null || conn.isClosed())
	              {
	            	  
	            	
	              }
	              else
	              {
	            pstmt.executeUpdate();
	            if (pstmt != null) {
	                 pstmt.close();
	             }
	             conn.commit();
	              }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
			if(conn!=null && !conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
    
    
    public static void deleteReportHistory(String serviceId) {
		// TODO Auto-generated method stub
		String query = QueryConfig.DELETE_HIVT_REPORT_SERVICE_DTL;
		
	
		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			  pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, serviceId);
	          
	            if(conn==null || conn.isClosed())
	              {
	            	  
	            	
	              }
	              else
	              {
			 pstmt.executeUpdate();
			
			 if (pstmt != null) {
                 pstmt.close();
             }
             conn.commit();
	              }
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				if(conn!=null && !conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
    
    
    
    public String getTestNameAccrebited(String testcodes)
    {
    	 // testCode is Group Code if isGroupWithDynamicTemplate = true
    	String dynamicTemplateID = null;
    	  Connection conn = PGDataHelper.getInstance().getConnection();
          java.io.InputStream ip = null;
          //CallableStatement cstmt = null;
          PreparedStatement pstmt = null;
         String TestNames = null;
          String query = QueryConfig.FETCH_TEST_NAMES_ACCREDITED;
        
          String condition1=" AND gnum_test_code in ("+testcodes+")";
          try {
              
        	  query+=condition1;
            
              pstmt = conn.prepareStatement(query);
              
              
              
           
              if(conn==null || conn.isClosed())
              {
            	  
            	
              }
              else
              {
              ResultSet rs = pstmt.executeQuery();
              
              if (rs.next()) {
            	  TestNames  = rs.getString(1);
                  
              }
              }
              
              
          } catch (Exception e) {
              //  e.printStackTrace();
              Log(Level.SEVERE, e);
             

          } finally {
              try {
                  pstmt.close();
                  if (conn != null && !conn.isClosed()) {
                      conn.commit();
                      conn.close();
                  }
              } catch (SQLException e) {

                  //e.printStackTrace();
                  Log(Level.SEVERE, e);
              }
          }
          
          return TestNames;

    }
    
    
    public String getTestCodesAccrebited(String hospitalcode)
    {
    	 // testCode is Group Code if isGroupWithDynamicTemplate = true
    	String dynamicTemplateID = null;
    	  Connection conn = PGDataHelper.getInstance().getConnection();
          java.io.InputStream ip = null;
          //CallableStatement cstmt = null;
          PreparedStatement pstmt = null;
         String TestNames = null;
          String query = QueryConfig.FETCH_TEST_CODES_ACCREDITED;
        		  
          try {
             
            
              pstmt = conn.prepareStatement(query);
              pstmt.setString(1, hospitalcode);
              
              
              if(conn==null || conn.isClosed())
              {
            	  
            	
              }
              else
              {
              ResultSet rs = pstmt.executeQuery();
              
              if (rs.next()) {
            	  TestNames  = rs.getString(1);
                  
              }

              }
              
          } catch (Exception e) {
              //  e.printStackTrace();
              Log(Level.SEVERE, e);
             

          } finally {
              try {
                  pstmt.close();
                  if (conn != null && !conn.isClosed()) {
                      conn.commit();
                      conn.close();
                  }
              } catch (SQLException e) {

                  //e.printStackTrace();
                  Log(Level.SEVERE, e);
              }
          }
          
          return TestNames;

    }
    
    
    
    public String getPortalVal(String hospitalcode)
    {
    	  Connection conn = PGDataHelper.getInstance().getConnection();
          java.io.InputStream ip = null;
          //CallableStatement cstmt = null;
          PreparedStatement pstmt = null;
         String isPortal = null;
          String query = QueryConfig.FETCH_IS_PORTAL;
        		  
          try {
             
            
              pstmt = conn.prepareStatement(query);
              pstmt.setString(1, hospitalcode);
              
              if(conn==null || conn.isClosed())
              {
            	  
            	
              }
              else
              {
           
              ResultSet rs = pstmt.executeQuery();
              
              if (rs.next()) {
            	  isPortal  = rs.getString(1);
                  
              }
              
              }
              
              
          } catch (Exception e) {
              //  e.printStackTrace();
              Log(Level.SEVERE, e);
             

          } finally {
              try {
                  pstmt.close();
                  if (conn != null && !conn.isClosed()) {
                      conn.commit();
                      conn.close();
                  }
              } catch (SQLException e) {

                  //e.printStackTrace();
                  Log(Level.SEVERE, e);
              }
          }
          
          return isPortal;

    }
    
    
    
    public static List getPdfsrequisitiondtl() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List finall=new ArrayList();
         List lsttest=new ArrayList();
         List lsttest1=new ArrayList();
		String query = QueryConfig.FETCH_PDFS_HIVT_REQUISITION_HEADER;
		
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String filename = null;
		String crno = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			 pstmt = conn.prepareStatement(query);
			 int i=1;
			 
			 if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				
				filename = rs.getString(1);
				lsttest.add(filename);
				
				crno = rs.getString(2);
				lsttest1.add(crno);
				
				i++;
			}
			
			if(lsttest.size()>0)
			{
				finall.addAll(lsttest);
				
			}
			
			if(lsttest1.size()>0)
			{
				finall.addAll(lsttest1);
				
			}
			
             }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				
				if(conn!=null && !conn.isClosed())
				   {
					rs.close();
					conn.close();
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return finall;
	}
    
    
    public static void insertInReportDeleteLog(String serviceId) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String query = QueryConfig.INSERT_HIVT_SCHEDULER_CONFIG_DTL;
		//int historySaveFrequency =PropertiesHelper.getReportHistorySaveFrequency();
	//	String path = QScheduler.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	//String decodedPath = URLDecoder.decode(path, "UTF-8");
    //	System.out.println("Service Path:"+decodedPath);
		String hour=Integer.toString(PropertiesHelper.getDeletepdffrequencyHour());
		String min=Integer.toString(PropertiesHelper.getDeletepdffrequencyMin());
		
		
		String pgConnectionUri=PropertiesHelper.getPGConnectionURI();
		String reportLogoPath=PropertiesHelper.getReportLogoPath();
		String pgusername=PropertiesHelper.getPGUserName();
		
	//     String frequencyString=Integer.toString(historySaveFrequency);
		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			  pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, serviceId);
	            pstmt.setString(2, hour);
	            pstmt.setString(3, min);
			
	            if(conn==null || conn.isClosed())
	              {
	            	  
	            	
	              }
	              else
	              {
	            pstmt.executeUpdate();
			
			 if (pstmt != null) {
                 pstmt.close();
             }
             conn.commit();
	              }
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
				if(conn!=null && !conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

    
    public List<String> getPatientDetails(String patCRNo) {
        String query = null;
        ResultSet rs = null;
        List<String> patDetailList = new ArrayList<String>();

        Connection conn = PGDataHelper.getInstance().getConnection();

        query = QueryConfig.FETCH_PATIENT_DTL;
     //   Log(Level.INFO, "Query : Get Patient Details " + query);

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, patCRNo);
            if(conn==null || conn.isClosed())
            {
          	  
          	
            }
            else
            {
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	
                patDetailList.add(rs.getString(1));
                patDetailList.add(rs.getString(2));
                patDetailList.add(rs.getString(3));
                patDetailList.add(rs.getString(4));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.commit();
                    conn.close();
                }

            } catch (SQLException sqex) {
                sqex.printStackTrace();
            }
        }
        return patDetailList;
    }

    
    
    public static String getInsideYear(String crNo) {
		
		 int insideYear=1000000;
		 
		 //String last6Digits = getLastnCharacters(crNo,6);
		 
		 String compareYearWith = crNo.substring(7,14);
		 
		 //System.out.println("for year "+compareYearWith);
		 
	   if (Integer.parseInt(compareYearWith) <= 100000)
      {
    	  insideYear=100000;
      }
      
      else  if ((Integer.parseInt(compareYearWith)) > 100000 && (Integer.parseInt(compareYearWith)) <= 200000)
      {
    	  insideYear=200000;
      }
      
      else  if ((Integer.parseInt(compareYearWith)) > 200000 && (Integer.parseInt(compareYearWith)) <= 300000)
      {
    	  insideYear=300000;
      }
      else  if ((Integer.parseInt(compareYearWith)) > 300000 && (Integer.parseInt(compareYearWith)) <= 400000)
      {
    	  insideYear=400000;
      }
      else  if ((Integer.parseInt(compareYearWith)) > 400000 && (Integer.parseInt(compareYearWith)) <= 500000)
      {
    	  insideYear=500000;
      }
      
      else  if ((Integer.parseInt(compareYearWith)) > 500000 && (Integer.parseInt(compareYearWith)) <= 600000)
      {
    	  insideYear=600000;
      }
      
      else  if ((Integer.parseInt(compareYearWith)) > 600000 && (Integer.parseInt(compareYearWith)) <= 700000)
      {
    	  insideYear=700000;
      }
      
      else  if ((Integer.parseInt(compareYearWith)) > 700000 && (Integer.parseInt(compareYearWith)) <= 800000)
      {
    	  insideYear=800000;
      }
      
      else  if ((Integer.parseInt(compareYearWith)) > 800000 && (Integer.parseInt(compareYearWith)) <= 900000)
      {
    	  insideYear=900000;
      }
      
      else  if ((Integer.parseInt(compareYearWith)) > 900000 && (Integer.parseInt(compareYearWith)) <= 1000000)
      {
    	  insideYear=1000000;
      }
	   
	   //System.out.println(insideYear);
	   return Integer.toString(insideYear);    
	}
   
   
   
   public static String getcount(String crNo) {
   	
  	  int count=100000;
  	 
  	  String last5Digits = crNo.substring(9,14);
	  int digit=Integer.parseInt(last5Digits);
		 
	  if (digit <= 10000)
     {
	   count=10000;
     }
     
     else  if ((digit) > 10000 && (digit) <= 20000)
     {
  	   count=20000;
     }
     
     else  if ((digit) > 20000 && (digit) <= 30000)
     {
  	   count=30000;
     }
     else  if ((digit) > 30000 && (digit) <= 40000)
     {
  	   count=40000;
     }
     else  if ((digit) > 40000 && (digit) <= 50000)
     {
  	   count=50000;
     }
     
     else  if ((digit) > 50000 && (digit) <= 60000)
     {
  	   count=60000;
     }
     
     else  if ((digit) > 60000 && (digit) <= 70000)
     {
  	   count=70000;
     }
     
     else  if ((digit) > 70000 && (digit) <= 80000)
     {
  	   count=80000;
     }
     
     else  if ((digit) > 80000 && (digit) <= 90000)
     {
  	   count=90000;
     }
     
     else  if ((digit) > 90000 && (digit) <= 99999)
     {
  	   count=100000;
     }
  
		System.out.println(count);
  	 return Integer.toString(count);
  	 
  
  }

   
   public static List<String> getCrNos(String fromDate,String toDate) {
       String query = null;
       String orderBy = null;
       ResultSet rs = null;
       List<String> crNoList = new ArrayList<String>();

       Connection conn = PGDataHelper.getInstance().getConnection();

       if (conn == null) {
			conn = PGDataHelper.getInstance().createPostgresConnection();  

		}
       
      // System.out.println(conn);
       query = QueryConfig.FETCH_CR_NOS;
       orderBy = " ORDER BY MAX(hivdt_entry_date) DESC";
       query += orderBy;
     //  Log(Level.INFO, "Query : Get CR NOs. : " + query);

       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(query);
           pstmt.setString(1, toDate);
           pstmt.setString(2, fromDate);
           if(conn==null || conn.isClosed())
           {
         	  
         	
           }
           else
           {
           rs = pstmt.executeQuery();
           while (rs.next()) {
           	
           	crNoList.add(rs.getString(1));
           } }

       } catch (Exception e) {
           e.printStackTrace();

       } finally {
           try {
               if (rs != null) {
                   rs.close();
               }

               if (pstmt != null) {
                   pstmt.close();
               }
               if (conn != null && !conn.isClosed()) {
                   conn.commit();
                   conn.close();
               }

           } catch (SQLException sqex) {
               sqex.printStackTrace();
           }
       }
       return crNoList;
   }
   

   
   public Document loadDocumentObj_testwise(String id,String testcode) {
       Document vObj = null;
       java.io.InputStream ip = null;

       try {
           boolean found = false;			
           switch ((Integer.parseInt(id))) {
               case 1:
                   ip = getClobObjectFromDatabaseForResultEntryFormDocument_testwise(testcode);

                   break;                
           }

           // set found to true if it was found
           found = true;
           // cache the value object if found
           if (found) {
               if (ip != null) {
                   vObj = builder.parse(ip);

               } else {
                   Log(Level.SEVERE, "Input Stream is null");
                   return null;
               }
               
               loadEachNodeOfDocumentSeparately(vObj, "investigationDocumentCacheVO" + id);                
           }
       } catch (Exception e) {
           //e.printStackTrace();
           Log(Level.SEVERE, e);
           // Handle failure putting object to cache
       }

       return vObj;
   }
   

   
   public InputStream getClobObjectFromDatabaseForResultEntryFormDocument_testwise(String testcode) {
       //  Siddharth Srivastava, 09/03/2015	
       //	Check all the conditions before implementing to store InputStream to session 

       Connection conn = PGDataHelper.getInstance().getConnection();
       java.io.InputStream ip = null;
       //CallableStatement cstmt = null;
       PreparedStatement pstmt = null;
       try {
          

           pstmt = conn.prepareStatement(QueryConfig.Q_GET_TESTTEMPLATE_XML_TESTWISE);
           pstmt.setString(1, testcode);
           if(conn==null || conn.isClosed())
           {
         	  
         	
           }
           else
           {
           ResultSet rs = pstmt.executeQuery();
           if (rs.next()) {
               String requisitionFormClob = rs.getString(1);
               if (requisitionFormClob != null) {
                   ip = new ByteArrayInputStream(requisitionFormClob.getBytes());
               }

           }
           }
       } catch (Exception e) {
           //  e.printStackTrace();
           Log(Level.SEVERE, e);
          

       } finally {
           try {
               pstmt.close();
               if (conn != null && !conn.isClosed()) {
                   conn.commit();
                   conn.close();
               }
           } catch (SQLException e) {

               //e.printStackTrace();
               Log(Level.SEVERE, e);
           }

       }

       //LOGGER_INV.log(Level.INFO,"input stream =="+ip);
       return ip;
   }

   
   
   public Map<Integer,String> getBilldetailsdata(String hospitalcode)
   {
   	 // testCode is Group Code if isGroupWithDynamicTemplate = true
   	String dynamicTemplateID = null;
   	  Connection conn = PGDataHelper.getInstance().getConnection();
         java.io.InputStream ip = null;
         //CallableStatement cstmt = null;
         PreparedStatement pstmt = null;
        String TestNames = null;
         String query = QueryConfig.FETCH_BILL_DETAILS;
       Map<Integer,String> mp=new HashMap<Integer,String>();
         try {
            
           
             pstmt = conn.prepareStatement(query);
           //  pstmt.setString(1, hospitalcode);
             
             
             if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
             ResultSet rs = pstmt.executeQuery();
             int count=0;
             while (rs.next()) {
            	 count=count+1;
           	String  data  = rs.getString(1);
                 mp.put(count, data);
             }

             }
             
         } catch (Exception e) {
             //  e.printStackTrace();
             Log(Level.SEVERE, e);
            

         } finally {
             try {
                 pstmt.close();
                 if (conn != null && !conn.isClosed()) {
                     conn.commit();
                     conn.close();
                 }
             } catch (SQLException e) {

                 //e.printStackTrace();
                 Log(Level.SEVERE, e);
             }
         }
         
         return mp;

   }
   
   
   public String getpatdetailsonbasisofbilldetails(String data,String newreqno)
   {
   	 // testCode is Group Code if isGroupWithDynamicTemplate = true
   	String dynamicTemplateID = null;
   	  Connection conn = PGDataHelper.getInstance().getConnection();
         java.io.InputStream ip = null;
         //CallableStatement cstmt = null;
         PreparedStatement pstmt = null;
        String TestNames = null;
         String query = QueryConfig.PAT_DETAILS_ON_BASIS_OF_FETCH_BILL_DETAILS;
       Map<Integer,String> mp=new HashMap<Integer,String>();
       String val="";
         try {
            
           
        	 
			 String[] dataval=data.split("\\*\\*\\*");
		
			 
			 String hospitalcode=dataval[0];
			 String billno=dataval[1];
			 String billdate=dataval[2];
			 
			 String reqno="";
			 
			 if(dataval[3].contains("|"))
			  reqno=dataval[3].split("\\|")[0];
			 
			 String testorgrpcode=dataval[4];
			 
			 
             pstmt = conn.prepareStatement(query);
             pstmt.setString(1, newreqno);
             pstmt.setString(2, testorgrpcode);
             pstmt.setString(3, hospitalcode);
             pstmt.setString(4, newreqno);
             pstmt.setString(5, testorgrpcode);
             pstmt.setString(6, hospitalcode);
             pstmt.setString(7, newreqno);
             pstmt.setString(8, testorgrpcode);
             pstmt.setString(9, hospitalcode);
             
             pstmt.setString(10, newreqno);
             pstmt.setString(11, hospitalcode);

             
             if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
            	 
            	 System.out.println("newreqno"+newreqno);
             ResultSet rs = pstmt.executeQuery();
             int count=0;
             if (rs.next()) {
            	 count=count+1;
           	  val  = rs.getString(1);
                
           	System.out.println("==================================");
           	
           	
             }
             }
             
             
         } catch (Exception e) {
             //  e.printStackTrace();
             Log(Level.SEVERE, e);
            

         } finally {
             try {
                 pstmt.close();
                 if (conn != null && !conn.isClosed()) {
                     conn.commit();
                     conn.close();
                 }
             } catch (SQLException e) {

                 //e.printStackTrace();
                 Log(Level.SEVERE, e);
             }
         }
         
         return val;

   }
 
   
   public String istestexist(String reqno,String test,String hospitalcode)
   {
   	 // testCode is Group Code if isGroupWithDynamicTemplate = true
   	String dynamicTemplateID = null;
   	  Connection conn = PGDataHelper.getInstance().getConnection();
         java.io.InputStream ip = null;
         //CallableStatement cstmt = null;
         PreparedStatement pstmt = null;
        String TestNames = null;
         String query = QueryConfig.IS_TEST_EXIST;
       Map<Integer,String> mp=new HashMap<Integer,String>();
       String val="0";
         try {
            
          	 
             pstmt = conn.prepareStatement(query);
             pstmt.setString(1, reqno);
             pstmt.setString(2, test);
             pstmt.setString(3, hospitalcode);

             if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
          if(!reqno.equals("") && !test.equals(""))
             {ResultSet rs = pstmt.executeQuery();
             int count=0;
             if (rs.next()) {
            	 count=count+1;
           	  val  = rs.getString(1);
             }
           	System.out.println("==================================");
           	
           	
             }

             } 
             
         } catch (Exception e) {
             //  e.printStackTrace();
             Log(Level.SEVERE, e);
            

         } finally {
             try {
                 pstmt.close();
                 if (conn != null && !conn.isClosed()) {
                     conn.commit();
                     conn.close();
                 }
             } catch (SQLException e) {

                 //e.printStackTrace();
                 Log(Level.SEVERE, e);
             }
         }
         
         return val;

   }
   
   
   public String isgroupexist(String reqno,String group,String hospitalcode)
   {
   	 // testCode is Group Code if isGroupWithDynamicTemplate = true
   	String dynamicTemplateID = null;
    String  groupcode   ="0";
   	if(group.length()>4)
   	 groupcode=group.substring(5);
   	     //  String  labcode=group.substring(0,5);
   	  Connection conn = PGDataHelper.getInstance().getConnection();
         java.io.InputStream ip = null;
         //CallableStatement cstmt = null;
         PreparedStatement pstmt = null;
        String TestNames = null;
         String query = QueryConfig.IS_GROUP_EXIST;
       Map<Integer,String> mp=new HashMap<Integer,String>();
       String val="0";
         try {
            
          	 
             pstmt = conn.prepareStatement(query);
             pstmt.setString(1, reqno);
             pstmt.setString(2, groupcode);
             pstmt.setString(3, hospitalcode);
             pstmt.setString(3, hospitalcode);

             if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
             if(!groupcode.equals(""))
             { ResultSet rs = pstmt.executeQuery();
             int count=0;
             if (rs.next()) {
            	 count=count+1;
           	  val  = rs.getString(1);
             }  
           	System.out.println("==================================");
             
           	
             }

             }  
             
         } catch (Exception e) {
               e.printStackTrace();
            // Log(Level.SEVERE, e);
            

         } finally {
             try {
                 pstmt.close();
                 if (conn != null && !conn.isClosed()) {
                     conn.commit();
                     conn.close();
                 }
             } catch (SQLException e) {
 
                 //e.printStackTrace();
                 Log(Level.SEVERE, e);
             }
         }
         
         return val;

   }
   
   
   public String getpatdetailsonbasisofbilldetailsgroup(String data,String newreqno)
   {
   	 // testCode is Group Code if isGroupWithDynamicTemplate = true
   	String dynamicTemplateID = null;
   	  Connection conn = PGDataHelper.getInstance().getConnection();
         java.io.InputStream ip = null;
         //CallableStatement cstmt = null;
         PreparedStatement pstmt = null;
        String TestNames = null;
         String query = QueryConfig.GROUP_DETAILS;
       Map<Integer,String> mp=new HashMap<Integer,String>();
       String val="";
         try {
            
           
        	 
			 String[] dataval=data.split("\\*\\*\\*");
		
			 
			 String hospitalcode=dataval[0];
			 String billno=dataval[1];
			 String billdate=dataval[2];
			 
			 String reqno="";
			 
			 if(dataval[3].contains("|"))
			  reqno=dataval[3].split("\\|")[0];
			 
			 String testorgrpcode=dataval[4];
			   testorgrpcode=testorgrpcode.substring(5);
			 
             pstmt = conn.prepareStatement(query);
             pstmt.setString(1, newreqno);
             pstmt.setString(2, testorgrpcode);
             pstmt.setString(3, hospitalcode);
             pstmt.setString(4, newreqno);
             pstmt.setString(5, testorgrpcode);
             pstmt.setString(6, hospitalcode);
           //  pstmt.setString(7, reqno);
             pstmt.setString(7, newreqno);
             pstmt.setString(8, testorgrpcode);
             pstmt.setString(9, hospitalcode);
             pstmt.setString(10, testorgrpcode);
             pstmt.setString(11, hospitalcode);
             pstmt.setString(12, newreqno);
             pstmt.setString(13, hospitalcode);

             if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
          
             ResultSet rs = pstmt.executeQuery();
             int count=0;
             if (rs.next()) {
            	 count=count+1;
           	  val  = rs.getString(1);
                
           	System.out.println("==================================");
           	
           	
             }
             }
             
             
         } catch (Exception e) {
             //  e.printStackTrace();
             Log(Level.SEVERE, e);
            

         } finally {
             try {
                 pstmt.close();
                 if (conn != null && !conn.isClosed()) {
                     conn.commit();
                     conn.close();
                 }
             } catch (SQLException e) {

                 //e.printStackTrace();
                 Log(Level.SEVERE, e);
             }
         }
         
         return val;

   }

   
   public static boolean inserttest(String data,String billno,String billdate,String istestorgroup) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
	   
	   String query="";
	   boolean flg=false;
	   if(istestorgroup.equals("0"))
		 query = QueryConfig.INSERT_TESTABLE;
	   else
		query = QueryConfig.INSERT_TESTABLE_GROUP;

		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		System.out.println("query:"+query);
		String val[]=data.split("\\*\\*\\*");
		try
		{
			  pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, val[0]);
	            pstmt.setString(2, val[1].trim());
	            pstmt.setString(3, val[2].trim());
	            pstmt.setString(4, val[3]);
	            pstmt.setString(5, val[4]);
	            pstmt.setString(6, val[5].equals("")?null:val[5]);
	            pstmt.setString(7, val[6].equals("")?null:val[6]); 
	            pstmt.setString(8, val[7].equals("")?null:val[7]);
	            pstmt.setString(9, val[8]);
	            pstmt.setString(10, val[9]);
	            pstmt.setString(11, val[10]);
	            pstmt.setString(12, billno);
	            pstmt.setString(13, billdate.equals("")?null:billdate);
	            pstmt.setString(14, val[11]);
	            if(istestorgroup.equals("0"))
	            pstmt.setString(15, "0");
	            else
	             pstmt.setString(15, "1");	
	            
	            pstmt.setString(16, val[12]);

	            
	            if(!istestorgroup.equals("0"))
		        pstmt.setString(17, val[13]);
	            
	            if(conn==null || conn.isClosed())
	              {
	            	  
	            	
	              }
	              else
	              {
			 pstmt.executeUpdate();
			
			 flg=true;
			 
			 if (pstmt != null) {
                pstmt.close();
            }
            conn.commit();
	              }
		
		}
		catch(Exception ex)
		{
			flg=false;
			ex.printStackTrace();
		}
		finally {
			
			try {
				if(conn!=null && !conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		  return flg;
	}
   
   
   public static void updatebilltableaftersave(String reqno,String testorgroupcode) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		String query = QueryConfig.UPDATE_BILL_TBL;
		
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			  pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, reqno);
	            pstmt.setString(2, testorgroupcode);

	            if(conn==null || conn.isClosed())
	              {
	            	  
	            	
	              }
	              else
	              {
	            pstmt.executeUpdate();
	            if (pstmt != null) {
	                 pstmt.close();
	             }
	             conn.commit();
	              }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
			if(conn!=null && !conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
   
   
   public static void deleteduplicaterequisition(String reqdno) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
 		String query = QueryConfig.ISEXIST_TESTABLE;
		
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			  pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, reqdno);
	            
	            if(conn==null || conn.isClosed())
	              {
	            	  
	            	
	              }
	              else
	              {
	            pstmt.executeUpdate();
	            if (pstmt != null) {
	                 pstmt.close();
	             }
	             conn.commit();
	              }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
			if(conn!=null && !conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

   
   
   public String istestexistintestmst(String reqdno,String test,String hospitalcode)
   {
   	 // testCode is Group Code if isGroupWithDynamicTemplate = true
   	String dynamicTemplateID = null;
   	  Connection conn = PGDataHelper.getInstance().getConnection();
         java.io.InputStream ip = null;
         //CallableStatement cstmt = null;
         PreparedStatement pstmt = null;
        String TestNames = null;
         String query = QueryConfig.IS_TEST_EXIST_TEST_MST;
       Map<Integer,String> mp=new HashMap<Integer,String>();
       String val="0";
         try {
            
          	 
             pstmt = conn.prepareStatement(query);
             pstmt.setString(1, reqdno);
          
             if(conn==null || conn.isClosed())
             {
           	  
           	
             }
             else
             {
          if(!reqdno.equals("") && !test.equals(""))
             {ResultSet rs = pstmt.executeQuery();
             int count=0;
             if (rs.next()) {
            	 count=count+1;
           	  val  = rs.getString(1);
             }
           	System.out.println("==================================");
           	
           	
             }

             }   
             
         } catch (Exception e) {
             //  e.printStackTrace();
             Log(Level.SEVERE, e);
            

         } finally {
             try {
                 pstmt.close();
                 if (conn != null && !conn.isClosed()) {
                     conn.commit();
                     conn.close();
                 }
             } catch (SQLException e) {

                 //e.printStackTrace();
                 Log(Level.SEVERE, e);
             }
         }
         
         return val;

   }
   

   
   
   public static void updatebillnoindtltbl(String reqdno,String billno) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		
		String query = QueryConfig.UPDATE_BILL_NO_DTLTBL;
		
		
		Connection conn = PGDataHelper.getInstance().getConnection();
		String ServiceId = null;
		boolean returnVal = false;
		PreparedStatement pstmt = null;
		try
		{
			
			query=query+" where hivtnum_req_dno in ("+reqdno+")";
			  pstmt = conn.prepareStatement(query);
			  
			  
	            pstmt.setString(1, billno);
	           // pstmt.setString(2, reqdno);

	            if(conn==null || conn.isClosed())
	              {
	            	  
	            	
	              }
	              else
	              {
	            pstmt.executeUpdate();
	            if (pstmt != null) {
	                 pstmt.close();
	             }
	             conn.commit();
	              }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			
			try {
			if(conn!=null && !conn.isClosed())
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
   

   
   
   public String checkisparmeterexist(ResultEntryVO resultEntryVO,String testcode,String parametercode) {
       String query = null;
       ResultSet rs = null;
       List<FourStringObject> resultValidationList = new ArrayList<FourStringObject>();

       Connection conn = PGDataHelper.getInstance().getConnection();

       System.out.println("reference range call::::::::dnoooooo:::"+resultEntryVO.getRequisitionDNo()+"testcode====="+resultEntryVO.getTestCode());
       query = QueryConfig.SELECT_COUNT_RESULT_ENTRY_DETAIL_HIVT_PARAMETER_DTL;
       //Log(Level.INFO, "Query :WorkOrder List For Result Validation " + query);
       String record = null;
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(query);
           pstmt.setString(1, resultEntryVO.getRequisitionDNo());
           pstmt.setString(2, testcode);
           pstmt.setString(3, parametercode);
           pstmt.setString(4, parametercode+""+parametercode);
           
           pstmt.setString(5, resultEntryVO.getHospitalCode());
           // Commented because multisession test not supported yet
           // 14/05/2015
          /* if (resultEntryVO.getIsTestMultiSession().equals("1") && resultEntryVO.getIsMultiSessionRegrouped().equals("0")) {
            pstmt.setString(2, resultEntryVO.getSessionId());
            } else {
            pstmt.setString(2, "0");
            }*/
           if(conn==null || conn.isClosed())
           {
         	  
         	
           }
           else
           {
           rs = pstmt.executeQuery();
           while (rs.next()) {
           	
        	   record = rs.getString(1);
           }
           }
       } catch (Exception e) {
           e.printStackTrace();

       } finally {
           try {
               if (rs != null) {
                   rs.close();
               }

               if (pstmt != null) {
                   pstmt.close();
               }
               if (conn != null && !conn.isClosed()) {
                   conn.commit();
                   conn.close();
               }

           } catch (SQLException sqex) {
               sqex.printStackTrace();
           }
       }
       return record;
   }
   
   
   
   public String checkisparmeterexistTextarea(ResultEntryVO resultEntryVO,String testcode,String parametercode) {
       String query = null;
       ResultSet rs = null;
       List<FourStringObject> resultValidationList = new ArrayList<FourStringObject>();

       Connection conn = PGDataHelper.getInstance().getConnection();

       System.out.println("reference range call::::::::dnoooooo:::"+resultEntryVO.getRequisitionDNo()+"testcode====="+resultEntryVO.getTestCode());
       query = QueryConfig.SELECT_COUNT_PARAMETER_DTL_TEXTAREA;
       //Log(Level.INFO, "Query :WorkOrder List For Result Validation " + query);
       String record = null;
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(query);
       //    pstmt.setString(1, resultEntryVO.getRequisitionDNo());
           pstmt.setString(1, testcode);
           pstmt.setString(2, parametercode);
        //   pstmt.setString(4, parametercode+""+parametercode);
           
        //   pstmt.setString(5, resultEntryVO.getHospitalCode());
           // Commented because multisession test not supported yet
           // 14/05/2015
          /* if (resultEntryVO.getIsTestMultiSession().equals("1") && resultEntryVO.getIsMultiSessionRegrouped().equals("0")) {
            pstmt.setString(2, resultEntryVO.getSessionId());
            } else {
            pstmt.setString(2, "0");
            }*/
           if(conn==null || conn.isClosed())
           {
         	  
         	
           }
           else
           {
           rs = pstmt.executeQuery();
           while (rs.next()) {
           	
        	   record = rs.getString(1);
           }
           }
       } catch (Exception e) {
           e.printStackTrace();

       } finally {
           try {
               if (rs != null) {
                   rs.close();
               }

               if (pstmt != null) {
                   pstmt.close();
               }
               if (conn != null && !conn.isClosed()) {
                   conn.commit();
                   conn.close();
               }

           } catch (SQLException sqex) {
               sqex.printStackTrace();
           }
       }
       return record;
   }
   
   
   public String gettextareadefaultvvalue(ResultEntryVO resultEntryVO,String testcode,String parametercode) {
       String query = null;
       ResultSet rs = null;
       List<FourStringObject> resultValidationList = new ArrayList<FourStringObject>();

       Connection conn = PGDataHelper.getInstance().getConnection();

       System.out.println("reference range call::::::::dnoooooo:::"+resultEntryVO.getRequisitionDNo()+"testcode====="+resultEntryVO.getTestCode());
       query = QueryConfig.SELECT_DEFAULT_PARAMETER_DTL_TEXTAREA;
       //Log(Level.INFO, "Query :WorkOrder List For Result Validation " + query);
       String record = "--";
       PreparedStatement pstmt = null;
       try {
           pstmt = conn.prepareStatement(query);
       //    pstmt.setString(1, resultEntryVO.getRequisitionDNo());
           pstmt.setString(1, testcode);
           pstmt.setString(2, parametercode);
        //   pstmt.setString(4, parametercode+""+parametercode);
           
        //   pstmt.setString(5, resultEntryVO.getHospitalCode());
           // Commented because multisession test not supported yet
           // 14/05/2015
          /* if (resultEntryVO.getIsTestMultiSession().equals("1") && resultEntryVO.getIsMultiSessionRegrouped().equals("0")) {
            pstmt.setString(2, resultEntryVO.getSessionId());
            } else {
            pstmt.setString(2, "0");
            }*/
           if(conn==null || conn.isClosed())
           {
         	  
         	
           }
           else
           {
           rs = pstmt.executeQuery();
           while (rs.next()) {
           	
        	   record = rs.getString(1);
           }
           }
       } catch (Exception e) {
           e.printStackTrace();

       } finally {
           try {
               if (rs != null) {
                   rs.close();
               }

               if (pstmt != null) {
                   pstmt.close();
               }
               if (conn != null && !conn.isClosed()) {
                   conn.commit();
                   conn.close();
               }

           } catch (SQLException sqex) {
               sqex.printStackTrace();
           }
       }
       return record;
   }
   
   
}
