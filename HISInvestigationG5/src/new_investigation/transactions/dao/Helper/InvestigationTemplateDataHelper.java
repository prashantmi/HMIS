package new_investigation.transactions.dao.Helper;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import new_investigation.InvestigationConfig;
import new_investigation.vo.InvCriteriaCodeVO;
import new_investigation.vo.InvResultValidationRespVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.template.Entry;
import new_investigation.vo.template.HexaStringClass;
import new_investigation.vo.template.LaboratoryGroupVO;
import new_investigation.vo.template.LaboratoryTestVO;
import new_investigation.vo.template.LaboratoryVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.Test;
import new_investigation.vo.template.TestGroupTreeListVO;
import new_investigation.vo.template.TriStringObject;

import org.apache.http.HttpRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class InvestigationTemplateDataHelper {
	private static InvestigationTemplateDataHelper instance;
	private static int checkedOut = 0;
	private static DocumentBuilderFactory builderFactory;
	private static DocumentBuilder builder;
	XPathFactory factory = null;
	public static String databaseusername = null;
	public static String databasepassword = null;
	private static TransformerFactory transformerFactory;

	private InvestigationTemplateDataHelper() {
		try {
			builderFactory = DocumentBuilderFactory.newInstance();
			builder = builderFactory.newDocumentBuilder();
			factory = XPathFactory.newInstance();
			transformerFactory = TransformerFactory.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
			// Handle cache region initialization failure
		}

		// Do other initialization that may be necessary, such as getting
		// references to any data access classes we may need to populate value
		// objects later
	}

	public static InvestigationTemplateDataHelper getInstance() {
		synchronized (InvestigationTemplateDataHelper.class) {
			if (instance == null) {
				instance = new InvestigationTemplateDataHelper();
			}
		}
		synchronized (instance) {
			instance.checkedOut++;
		}

		return instance;
	}

	public Transformer getNewTransformer()
			throws TransformerConfigurationException {
		return transformerFactory.newTransformer();
	}

	public InputStream getClobObjectFromDatabaseForResultEntryFormDocument() {
		// Siddharth Srivastava, 09/03/2015
		// Check all the conditions before implementing to store InputStream to
		// session
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		java.io.InputStream ip = null;
		CallableStatement cstmt = null;
		try {
			cstmt = conn
					.prepareCall("{call pkg_inv_template.getresultEntryFormDocument(?,?)}");
			// cstmt.registerOutParameter(1, Types.CLOB);
			// Siddharth Srivastava, 30/02/2015
			// Changed after consulting the mhmis workspace
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.execute();

			// Clob requisitionFormClob = cstmt.getClob(1);
			String requisitionFormClob = cstmt.getString(1);
			String error = cstmt.getString(2);
			if (error.equals("SUCCESS")) {
				// ip = requisitionFormClob.getAsciiStream();
				if (requisitionFormClob != null)
					ip = new ByteArrayInputStream(
							requisitionFormClob.getBytes());
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
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			// tx.close();
		}

		// LOGGER_INV.log(Level.INFO,"input stream =="+ip);
		return ip;
	}
	
	
	
	public InputStream getClobObjectFromDatabaseForRequisitionFormDocument() {
		// Siddharth Srivastava, 09/03/2015
		// Check all the conditions before implementing to store InputStream to
		// session
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		java.io.InputStream ip = null;
		CallableStatement cstmt = null;
		try {
			cstmt = conn
					.prepareCall("{call pkg_inv_template.getrequisitionformdocument(?,?)}");
			// cstmt.registerOutParameter(1, Types.CLOB);
			// Siddharth Srivastava, 30/02/2015
			// Changed after consulting the mhmis workspace
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.execute();

			// Clob requisitionFormClob = cstmt.getClob(1);
			String requisitionFormClob = cstmt.getString(1);
			String error = cstmt.getString(2);
			if (error.equals("SUCCESS")) {
				// ip = requisitionFormClob.getAsciiStream();
				if (requisitionFormClob != null)
					ip = new ByteArrayInputStream(
							requisitionFormClob.getBytes());
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
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			// tx.close();
		}

		// LOGGER_INV.log(Level.INFO,"input stream =="+ip);
		return ip;
	}

	public InputStream getClobObjectFromDatabaseForDocument(String id) {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		java.io.InputStream ip = null;
		CallableStatement cstmt = null;
		try {
			// LOGGER_INV.log(Level.INFO,"getClobObjectFromDatabaseForDocument  "+id);
			cstmt = conn
					.prepareCall("{call pkg_inv_template.getStyleSheet(?,?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.execute();

			String requisitionFormClob = cstmt.getString(2);
			String error = cstmt.getString(3);
			// LOGGER_INV.log(Level.INFO,error);
			if (error.equals("SUCCESS")) {
				ip = new ByteArrayInputStream(requisitionFormClob.getBytes());
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
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			// tx.close();
		}
		return ip;
	}

	public Transformer getTransformerObject(String id) {
		Transformer transformer = null;
		try {
			java.io.InputStream ip = getClobObjectFromDatabaseForDocument(id);
			if( ip != null)
			transformer = transformerFactory.newTransformer(new StreamSource(ip
					));
			// investigationStylesheetConfigurationCacheVO.put("investigationStylesheetConfigurationCacheVO"
			// + id, transformer);
		} catch (Exception e) {
			e.printStackTrace();
			// LOGGER_INV.log(Level.INFO,"id->"+id);
			// LOGGER_INV.log(Level.INFO,"id->"+id+e.getCause());

			// Handle failure putting object to cache
		}
		return transformer;

	}

	/**
	 * Retrieves a CacheLaboratoryTestVO Object. Second argument decides whether
	 * to look * in the cache. Returns a new value object if one can't be loaded
	 * from the database. Database cache synchronization is * handled by
	 * removing cache elements upon modification.
	 * 
	 * @param id
	 */
//	public Node getTemplateNodeObj(String id, String nodeId, HttpSession session, boolean isDynamicTemplate, boolean isPrintWithDynamicTemplate) {
//		Document document = null;
//
//		Node node = null;
//
//		// Either fromCache was false or the object was not found, so
//		// call loadBookVObj to create it
//
//		document = (Document) session
//				.getAttribute("investigationDocumentCacheVO" + id);
////		if (document == null)
////			document = loadDocumentObj(id, session);
//		
//		String dynamicTemplateID = null;
//        boolean isDynamicTemplate = false;
//        if (document == null) {
//        	dynamicTemplateID = getDynamicTemplateStatus(nodeId);
//        	if(dynamicTemplateID != null && !dynamicTemplateID.isEmpty())
//        	{
//        		nodeId = dynamicTemplateID;
//        		document = loadDynamicDocumentObj(dynamicTemplateID, session);
//        		isDynamicTemplate = true;
//        	}
//        	else
//        	{
//        		document = loadDocumentObj(id, session);
//        	}
//        		
//        }
//
////		if (document != null) {			
////			node = (Node) session.getAttribute("investigationDocumentCacheVO"
////					+ id + "_" + nodeId);			
////		}
//        
//        if (document != null) {
//            //LOGGER_INV.log(Level.INFO,"loading... Node form memory -->"+id);
//        	String cacheString = "investigationDocumentCacheVO" + id;
//        	if(isDynamicTemplate)
//        		cacheString = "dynamicInvestigationDocumentCacheVO";
//            node = (Node) session.getAttribute(cacheString + "_" + nodeId);
//            //LOGGER_INV.log(Level.INFO,"Getting Key ::->"+"investigationDocumentCacheVO"+id+"_"+nodeId);
//        }               
//        
//		return node;
//	}
	
	public Node getTemplateNodeObj(String id, String nodeId, HttpSession session, boolean isPrintWithDynamicTemplate) {
		Document document = null;

		Node node = null;

		// Either fromCache was false or the object was not found, so
		// call loadBookVObj to create it
		if(!isPrintWithDynamicTemplate) {
			document = (Document) session
					.getAttribute("investigationDocumentCacheVO" + id);
		}
//		if (document == null)
//			document = loadDocumentObj(id, session);
		
		String dynamicTemplateID = nodeId;;
       boolean isDynamicTemplate = isPrintWithDynamicTemplate;
        if (document == null) {
        	
        	if(isPrintWithDynamicTemplate)
        	{
        		//nodeId = dynamicTemplateID;
        		document = loadDynamicDocumentObj(dynamicTemplateID, session);
        		isDynamicTemplate = true;
        	}
        	else
        	{
        		document = loadDocumentObj(id, session);        		
        	}
        		
        }

//		if (document != null) {			
//			node = (Node) session.getAttribute("investigationDocumentCacheVO"
//					+ id + "_" + nodeId);			
//		}
        
        if (document != null) {
            //LOGGER_INV.log(Level.INFO,"loading... Node form memory -->"+id);
        	String cacheString = "investigationDocumentCacheVO" + id;
        	if(isDynamicTemplate)
        		cacheString = "dynamicInvestigationDocumentCacheVO";
            node = (Node) session.getAttribute(cacheString + "_" + nodeId);
            //LOGGER_INV.log(Level.INFO,"Getting Key ::->"+"investigationDocumentCacheVO"+id+"_"+nodeId);
        }               
        
		return node;
	}
	
	
	/*public Node getTemplateNodeObj(String id, String nodeId, HttpSession session, boolean isPrintWithDynamicTemplate,String testformtype,UserVO userVo) {
		Document document = null;

		Node node = null;

		// Either fromCache was false or the object was not found, so
		// call loadBookVObj to create it
		if(!isPrintWithDynamicTemplate) {
			document = (Document) session
					.getAttribute("investigationDocumentCacheVO" + id);
		}
//		if (document == null)
//			document = loadDocumentObj(id, session);
		
		String dynamicTemplateID = nodeId;;
       boolean isDynamicTemplate = isPrintWithDynamicTemplate;
        if (document == null) {
        	
        	if(isPrintWithDynamicTemplate)
        	{
        		//nodeId = dynamicTemplateID;
        		document = loadDynamicDocumentObj(dynamicTemplateID, session,testformtype,true,userVo);
        		isDynamicTemplate = true;
        	}
        	else
        	{
        		document = loadDocumentObj(id, session);        		
        	}
        		
        }

//		if (document != null) {			
//			node = (Node) session.getAttribute("investigationDocumentCacheVO"
//					+ id + "_" + nodeId);			
//		}
        
        if (document != null) {
            //LOGGER_INV.log(Level.INFO,"loading... Node form memory -->"+id);
        	String cacheString = "investigationDocumentCacheVO" + id;
        	if(isDynamicTemplate)
        		cacheString = "dynamicInvestigationDocumentCacheVO";
            node = (Node) session.getAttribute(cacheString + "_" + nodeId);
            //LOGGER_INV.log(Level.INFO,"Getting Key ::->"+"investigationDocumentCacheVO"+id+"_"+nodeId);
        }               
        
		return node;
	}*/
	
	 private Document loadDynamicDocumentObj(String dynamicTemplateID, HttpSession session)
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
	                   System.out.println("Input Stream is null");
	                    return null;
	                }
	                
	                loadEachNodeOfDocumentSeparately(vObj, "dynamicInvestigationDocumentCacheVO", session);                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	           
	            // Handle failure putting object to cache
	        }

	        return vObj;
	    }
	
//getting template data from the printing template
	 private InputStream getClobObjectForDynamicTemplate(String dynamicTemplateID)
	    {
		 	Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey = "DYNAMIC.GET.TESTTEMPLATE";

		 	JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();			
			ResultSet rs = null;
	        java.io.InputStream ip = null;
	        //CallableStatement cstmt = null;
	 
	        try {        
	        	
	        	String query = HelperMethodsDAO.getQuery(filename, queryKey);

				System.out.println("Query ---------> " + query);
				Sequence sq = new Sequence();

				populateMAP.put(sq.next(), dynamicTemplateID);
				
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);				      
	        		       	          
	            if (rs.next()) {
	                String requisitionFormClob = rs.getString(1);
	                if (requisitionFormClob != null) {
	                    ip = new ByteArrayInputStream(requisitionFormClob.getBytes());
	                }

	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            //Log(Level.SEVERE, e);
	           

	        } finally {
	            try {
	               // pstmt.close();
	                if (conn != null) {
	                   // conn.commit();
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();	                
	            }
	        }
	        return ip;
	        }

	  public String getDynamicTemplateStatus(String testCode, String hospitalCode, boolean isGroupWithDynamicTemplate, String labCode,String paratype)
	    {
		  // testCode is Group Code if isGroupWithDynamicTemplate = true
		  Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey = null;
			if(isGroupWithDynamicTemplate)
			{
				queryKey = "DYNAMIC.GROUPTEMPLATE.STATUS";
			}
			else
			{
				if(paratype!=null && paratype.equals("2"))
					queryKey = "DYNAMIC.TESTTEMPLATE.STATUS.REQUISITION_FORM";
					else
				queryKey = "DYNAMIC.TESTTEMPLATE.STATUS";
			}
			
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();			
			ResultSet rs = null;
			String dynamicTemplateID = null;
			boolean isDynamic = false;
			String strIsDynamic = null;
			 
			try {
				String query = HelperMethodsDAO.getQuery(filename, queryKey);

				System.out.println("Query ---------> " + query);
				Sequence sq = new Sequence();

				populateMAP.put(sq.next(), testCode);
				if(isGroupWithDynamicTemplate)
				{
					populateMAP.put(sq.next(), hospitalCode);
					populateMAP.put(sq.next(), labCode);
				}
				
				if(paratype!=null && paratype.equals("2"))
					populateMAP.put(sq.next(), labCode);
				
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				while (rs.next()) {
					strIsDynamic  = rs.getString(1);
	                  dynamicTemplateID = rs.getString(2);
				}
				if(strIsDynamic != null && strIsDynamic.equals("1"))
	            	  isDynamic = true;		  		  	    		    	
	              
	              
	          } catch (Exception e) {
	                e.printStackTrace();
	             // Log(Level.SEVERE, e);	             
	          } finally {
	              try {
	            	  if(rs != null)
	            		  rs.close();
	                  if (conn != null) {
	                     // conn.commit();
	                      conn.close();
	                  }
	              } catch (SQLException e) {

	                  e.printStackTrace();
	                  //Log(Level.SEVERE, e);
	              }
	          }
	          
	          return dynamicTemplateID;

	    }
	
	public Document loadDocumentObj(String id, HttpSession session) {
		Document vObj = null;
		java.io.InputStream ip = null;

		try {
			boolean found = false;
			// enum
			// DOCUMENT_DETAILS{testtemplate,testsampletemplate,testrequisitiontemplate,templateQueryFile,printingtemplate,
			// labtestgrouptemplate,labRegisterConfiguration,laboratoryRequisitiontemplate,imageUtilityUserInformation,appletConfigurator};

			// LOGGER_INV.log(Level.INFO,"id -->"+id);
			switch ((Integer.parseInt(id))) {
			case 1:
				ip = getClobObjectFromDatabaseForResultEntryFormDocument();
				break;
			case 3:
				ip = getClobObjectFromDatabaseForRequisitionFormDocument();

				break;
			/*
			 * case 2:
			 * ip=getClobObjectFromDatabaseForSampleCollectionFormDocument();
			 * 
			 * 
			 * break; case 3:
			 * ip=getClobObjectFromDatabaseForRequisitionFormDocument();
			 * 
			 * break; case 4:
			 * ip=getClobObjectFromDatabaseTemplateQueryDocument();
			 * 
			 * break; case 5:
			 * ip=getClobObjectFromDatabaseForPrintingFormDocument();
			 * 
			 * break; case 6:
			 * ip=getClobObjectFromDatabaseForResultEntryGroupFormDocument();
			 * 
			 * break; case 7:
			 * ip=getClobObjectFromDatabaseLabRegisterConfigDocument();
			 * 
			 * break; case 8:
			 * ip=getClobObjectFromDatabaseForLabRequisitionFormDocument();
			 * 
			 * break; case 9:
			 * ip=getClobObjectFromDatabaseImageUtilityConfDocument();
			 * 
			 * break; case 10:
			 * ip=getClobObjectFromDatabaseAppletConfiguratorDocument();
			 * 
			 * break;
			 */

			}

			// set found to true if it was found
			found = true;
			// cache the value object if found
			if (found) {
				vObj = builder.parse(ip);
				// investigationDocumentCacheVO.put("investigationDocumentCacheVO"+id,
				// vObj);
				/*
				 * if(Integer.parseInt(id)==10) { loadAppletConfigurator(); }
				 * if(Integer.parseInt(id)==4) { loadTemplateQuery(); }
				 * if(Integer.parseInt(id)==4) { loadAppletUserInformationXML();
				 * } else {
				 */

				loadEachNodeOfDocumentSeparately(vObj,
						"investigationDocumentCacheVO" + id, session);
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Handle failure putting object to cache
		}

		return vObj;
	}

	public void loadEachNodeOfDocumentSeparately(Document vObj, String str,
			HttpSession session) {
		// LOGGER_INV.log(Level.INFO,"loadEachNodeOfDocumentSeparately");
		XPath xpath = factory.newXPath();
		try {

			XPathExpression expr = xpath.compile("/templates/testtemplate");
			Object result = expr.evaluate(vObj, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			// LOGGER_INV.log(Level.INFO,"loadEachNodeOfDocumentSeparately ->"+nodes.getLength());
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				// investigationDocumentCacheVO.put(str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue(),
				// node);
				String r = node.getAttributes().getNamedItem("labTestCode")
						.getNodeValue();
				session.setAttribute(str
						+ "_"
						+ node.getAttributes().getNamedItem("labTestCode")
								.getNodeValue(), node);

				// LOGGER_INV.log(Level.INFO,"Loading key"+str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public Document getNewDocument() {
		// TODO Auto-generated method stub
		return builder.newDocument();
	}

	public LaboratoryGroupVO getLaboratoryGroupVObj(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public LaboratoryTestVO getLaboratoryTestVObj(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public LaboratoryVO getLaboratoryVObj(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<HexaStringClass> getRangeFromToList(
			ResultEntryVO resultEntryVO, String flag) {
		// TODO Auto-generated method stub
		List<HexaStringClass> rangeFromToList = new ArrayList<HexaStringClass>();

		return rangeFromToList;
	}

	public String getLoincCode(String testCode, String paraCode,
			String sampleCode, String uomCode) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.TEMPLATE_PROCESSING.LOINC_CODE";
		System.out.println("test code: " + testCode + " paraCode: " + paraCode);
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		String loincCode = null;
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();

			populateMAP.put(sq.next(), testCode);
			populateMAP.put(sq.next(), paraCode);
			populateMAP.put(sq.next(), sampleCode);
			populateMAP.put(sq.next(), uomCode);

			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			while (rs.next()) {
				loincCode = rs.getString(1);
				System.out.println("loincCode found: " + loincCode);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else if (e.getClass() == HisDataAccessException.class) {
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			} else if (e.getClass() == HisApplicationExecutionException.class) {
				throw new HisApplicationExecutionException(
						"DailyPatientDAO.populateMAP::" + e);
			} else
				throw new HisDataAccessException("HisDataAccessException:: "
						+ e);
		} finally {
			// tx.close();
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return loincCode;
		// return "41758-4";
	}

	public List<TestMandRefMasterVO> getReferanceRange(String hospitalcode) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.hivt_test_mand_ref_val_mst";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		List<TestMandRefMasterVO> objList = new ArrayList<TestMandRefMasterVO>();

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();

			populateMAP.put(sq.next(), hospitalcode);
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			rs.beforeFirst();
			while (rs.next()) {
				TestMandRefMasterVO objTestMandRefVo = new TestMandRefMasterVO();
				HelperMethods.populateVOfrmRS(objTestMandRefVo, rs);
				objList.add(objTestMandRefVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else if (e.getClass() == HisDataAccessException.class) {
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			} else if (e.getClass() == HisApplicationExecutionException.class) {
				throw new HisApplicationExecutionException(
						"DailyPatientDAO.populateMAP::" + e);
			} else
				throw new HisDataAccessException("HisDataAccessException:: "
						+ e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// tx.close();
		}
		return objList;
	}

	public List<InvCriteriaCodeVO> getCriteriaCode() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.CRITERIA_CODE";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		List<InvCriteriaCodeVO> objList = new ArrayList<InvCriteriaCodeVO>();

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query ---------> " + query);
			Sequence sq = new Sequence();

			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			rs.beforeFirst();
			while (rs.next()) {
				InvCriteriaCodeVO objTestMandRefVo = new InvCriteriaCodeVO();
				HelperMethods.populateVOfrmRS(objTestMandRefVo, rs);
				objList.add(objTestMandRefVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException();
			} else if (e.getClass() == HisDataAccessException.class) {
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			} else if (e.getClass() == HisApplicationExecutionException.class) {
				throw new HisApplicationExecutionException(
						"DailyPatientDAO.populateMAP::" + e);
			} else
				throw new HisDataAccessException("HisDataAccessException:: "
						+ e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// tx.close();
		}
		return objList;
	}

	public static boolean getPrintWithStandardRanges(String testCode,
			String labCode, String hospitalCode) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.HIVT_LABORATORY_TEST_MST.PRINT_WITH_STANDARD_RANGES";

		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		String printWithStandardRanges = null;
		boolean returnVal = false;
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), testCode);
			populateMAP.put(sq.next(), labCode);
			populateMAP.put(sq.next(), hospitalCode);

			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			while (rs.next()) {
				printWithStandardRanges = rs.getString(1);
			}

			if(printWithStandardRanges!=null)
			{
			if (printWithStandardRanges.equals("1") == true) {
				returnVal = true;
			} else
				returnVal = false;
			}
			else
				returnVal = false;
			} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (conn != null)
				try {
					if (rs != null) {
						rs.close();
					}
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// tx.close();
		}
		return returnVal;
	}

	public static List<TriStringObject> getDefaultValues(
			ResultEntryVO resultEntryVO) {
		// TODO Auto-generated method stub

		String query = null;
		ResultSet rs = null;

		Sequence sq = new Sequence();
		Map populateMap = new HashMap();

		List<TriStringObject> resultValidationList = new ArrayList<TriStringObject>();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey = "SELECT.HIVT_PARAMETER_DTL.DEFAULTVALUES";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();

		populateMap.put(sq.next(), resultEntryVO.getTestCode());
		// populateMap.put(sq.next(), resultEntryVO.getHospitalcode());

		// populateMap.put(sq.next(), hosCode);

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query :WorkOrder List For Result Validation "
					+ query);
		} catch (Exception e) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}
		try {

			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			while (rs.next()) {

				String parentCode = "";

				if (rs.getString(1).equals(rs.getString(3)))
					parentCode = rs.getString(3);
				else
					parentCode = rs.getString(1) + rs.getString(3);

				TriStringObject triStringObject = new TriStringObject(
						rs.getString(1), rs.getString(2), parentCode);
				if (resultValidationList == null)
					resultValidationList = new ArrayList<TriStringObject>();

				resultValidationList.add(triStringObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new HisDataAccessException(
					"RESULT Validation :getResultValidfationDataList  :" + e);
		} finally {
			if (conn != null)
				try {
					if (rs != null) {
						rs.close();
					}
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// tx.close();
		}

		return resultValidationList;

	}
	
	//added by prashant for getting seq_id of default hgstr_value
	public static String getDefaultValues2( String testcode, String testparam)
	{
			String query = null;
			ResultSet rs = null;
			Sequence sq = new Sequence();
			Map populateMap = new HashMap();
			String seq_idOfdefaultvalue = null;
			
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.HIVT_PARAMETER_DTL.HGSTR_VALUES";
			
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
		
			populateMap.put(sq.next(), testcode);
			populateMap.put(sq.next(), testparam);

			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			} catch (Exception e) {
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}
			try {
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				while (rs.next()) {
					seq_idOfdefaultvalue=rs.getString(1);
					}
			} catch (Exception e) {
				e.printStackTrace();
				throw new HisDataAccessException(
						"RESULT Validation :getResultValidfationDataList  :" + e);
			} finally {
				if (conn != null)
					try {
						if (rs != null) {
							rs.close();
						}
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
			return seq_idOfdefaultvalue;
		}
		

	/*****************************************
	 * Functions for Dynamic Template Printing
	 *****************************************/

	/*
	 * Function to populate the combo on dynamic template printing page
	 */
	public List getTemplateListCombo() {
		List<Entry> comboList = new ArrayList<Entry>();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DYNAMIC.SELECT.TEST.LIST";
		ResultSet rs = null;
		try {
			String query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query :Dynamic Template- List of tests "
					+ query);

			Map populateMap = new HashMap();
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			while (rs.next()) {
				comboList.add(new Entry(rs.getString(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					if (rs != null) {
						rs.close();
					}
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// tx.close();
		}

		return comboList;
	}

	
	public List getGroupTemplateListCombo() {
		List<Entry> comboList = new ArrayList<Entry>();

		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DYNAMIC.SELECT.GROUP.TEST.LIST";
		ResultSet rs = null;
		try {
			String query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query :Dynamic Template- List of tests "
					+ query);

			Map populateMap = new HashMap();
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			while (rs.next()) {
				comboList.add(new Entry(rs.getString(1) + "-" + rs.getString(4), rs.getString(2)+ "-" + rs.getString(3)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					if (rs != null) {
						rs.close();
					}
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			// tx.close();
		}

		return comboList;
	}

	
	public List getTemplateList() {

		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DYNAMIC.SELECT.EXISTING.LIST";
		ResultSet rs = null;
		List<Entry> tpList = new ArrayList<Entry>();
		try {
			String query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("query=" + query);
			Sequence seq = new Sequence();
			Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
			// populateMap.put(seq.next(), userVO.getHospitalCode());

			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			while (rs.next()) {
				tpList.add(new Entry(rs.getString(2), rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return tpList;

	}
	
	public Element getResultEntryFormParameters(Document ownerDocument, Element resultEntryFormParamterElement, String testCode)
	{
		
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "SELECT.TEMPLATE_DATA.TEST_PARAMETER_DETAIL";
		ResultSet rs = null;
		
		
		
		try
		{
			
			String query = HelperMethodsDAO.getQuery(filename, queryKey);
			//LOGGER_INV.log(Level.INFO,"query="+query);
			Sequence seq = new Sequence();
			Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
			 populateMap.put(seq.next(), testCode);
			
			
			 rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			while(rs.next())
			{
				Element element=ownerDocument.createElement("element");
				element.setAttribute("levelNo",rs.getString(1));
				element.setAttribute("parameterCode",rs.getString(4));// adding labcode only to the testparameterCode whenever processing it remove labcode
				element.setAttribute("parameterName",rs.getString(3));
				element.setAttribute("parentParameterCode",rs.getString(5));
				element.setAttribute("CriteriaCode",rs.getString(6));
				element.setAttribute("valueType",rs.getString(7));// represents the type of template(simple,horizontal,vertical)
				element.setAttribute("objectId",rs.getString(8));
				element.setAttribute("removeStatus","0");
				element.setAttribute("functionName",rs.getString(9));
				element.setAttribute("eventName",rs.getString(10));
				element.setAttribute("eventValidationString",rs.getString(9));
				element.setAttribute("property",rs.getString(11));
				element.setAttribute("isparameternamedisplayed",rs.getString(12));
				element.setAttribute("labelelementdisplaytype","false");
				element.setAttribute("labelAlignment",rs.getString(13));
				element.setAttribute("elementAlignment",rs.getString(14));
				
				String defaultValue = "-";
				if(rs.getString(19) != null && rs.getString(19).length() > 0 )
					defaultValue = rs.getString(19);
				
				element.setAttribute("defaultValue",defaultValue);
				element.setAttribute("isPrintable",rs.getString(15)); 
				element.setAttribute("queryCode",rs.getString(16));
				element.setAttribute("buttonName",rs.getString(17)); 
				element.setAttribute("callUrl",rs.getString(18)); 
				//element.setAttribute("parentparametername",rs.getString(22));  //check this
				element.setAttribute("parentparametername","Parent Label");  //Hardcoded for new version as of now
				element.setAttribute("isTestGroup","NO");
				element.setAttribute("typeOfElement","RT");//RT for resultEntry
				element.setAttribute("templateType","resultentrytemplate");//
				element.setAttribute("labTestCode",testCode);
				element.setAttribute("originalname","template#"+((rs.getString(5)==null || rs.getString(5).equals(rs.getString(4))==false)?"":rs.getString(5)) +rs.getString(4));
				//LOGGER_INV.log(Level.INFO,"previous lab test code ="+testCode);
				element.setAttribute("previousLabTestCode", testCode);
				
				resultEntryFormParamterElement.appendChild(element);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return resultEntryFormParamterElement;
	}
	
	public String getDynamicTemplateSeqID()
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DYNAMIC.SELECT.SEQID";
		ResultSet rs = null;
		String seqId = null;
		try {
			String query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("query=" + query);
		
			Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
			// populateMap.put(seq.next(), userVO.getHospitalCode());

			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			while (rs.next()) {
				seqId = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return seqId;
	}

	public Document loadDynamicDocumentObj(String id,
			HttpSession session, String templateSequenceId) {
		Document vObj = null;
		java.io.InputStream ip = null;

		try {
			boolean found = false;
								

				JDBCTransactionContext tx = new JDBCTransactionContext();
				tx.begin();
				Connection conn = tx.getConnection();				
				String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
				String queryKey = "DYNAMIC.SELECT.TEMPLATE";
				ResultSet rs = null;
				
				try {								
					String query = HelperMethodsDAO.getQuery(filename, queryKey);
					System.out.println("query=" + query);
				
					Sequence seq = new Sequence();
					Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
					 populateMap.put(seq.next(), templateSequenceId);

					rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

					while (rs.next()) {
					
						String requisitionFormClob = rs.getString(1);
																	
							if (requisitionFormClob != null)
							{
								ip = new ByteArrayInputStream(
										requisitionFormClob.getBytes());
								
							}
					}
					
					found = true;		
					if (found) {
						vObj = builder.parse(ip);				

						loadEachNodeOfDocumentSeparately(vObj,
								"investigationDocumentCacheVO" + id, session);
						
					}
					
						
				} catch (HisApplicationExecutionException e) {
					e.printStackTrace();
				//	tx.rollback();
				}

				catch (HisDataAccessException e) {
					e.printStackTrace();
				//	tx.rollback();

				} catch (Exception e) {
					e.printStackTrace();

				//	tx.rollback();

				} finally {
					try {
						if(conn != null)
							conn.close();
					} catch (SQLException e) {

						e.printStackTrace();
					}
					
				}
		} catch (Exception e) {
			e.printStackTrace();			
		}

		return vObj;
	}

	
	 private Document loadDynamicDocumentObj(String dynamicTemplateID, HttpSession session,String testformtype,boolean flag,UserVO userVO)
	    {
	    	Document vObj = null;
	        java.io.InputStream ip = null;

	        try {
	            boolean found = false;			
	            
	            if( (testformtype.equals("2")) && (session.getAttribute("TESTCODESONTEMPLATEDF")!=null))
	            {
	            HashSet<String> testCodeListt = (HashSet<String>) session.getAttribute("TESTCODESONTEMPLATEDF");
				HashSet<String> testCodeList = (HashSet<String>) session.getAttribute("TESTCODESONTEMPLATE");
				String oldtest=""; 
				for (String obj : testCodeList) {
				        
					oldtest= obj;
				      } 
				
				String newtest="";
				
				for (String obj : testCodeListt) {
			        
					newtest= obj;
					if(newtest.equals(oldtest))
					{}
					else
					break;
				      }
				
				
				ip = getClobObjectForDynamicTemplateDF(dynamicTemplateID,testformtype,oldtest,newtest);
	            }
	            else
	            {
	            	ip = getClobObjectForDynamicTemplate(dynamicTemplateID);
	            	/*if(session.getAttribute("requiredXMLRF")!=null)
	            	{
	            		String xml=(String)session.getAttribute("requiredXMLRF");
	                    ip = new ByteArrayInputStream(xml.getBytes());
	            	}else*/
	            	
	            		
	            		//ip=null;
	            }
	            // set found to true if it was found
	            found = true;
	            // cache the value object if found
	            if (found) {
	                if (ip != null) {
	                    vObj = builder.parse(ip);

	                } else {
	                   System.out.println("Input Stream is null");
	                    return null;
	                }
	                
	                loadEachNodeOfDocumentSeparately(vObj, "dynamicInvestigationDocumentCacheVO", session);                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	           
	            // Handle failure putting object to cache
	        }

	        return vObj;
	    }
	 
	public boolean insertDynamicTemplate(String templateSequenceId,
			boolean isNewTemplate, String templateName, String headerHeight,
			String headerWidth, String footerHeight, String footerWidth,
			String pageHeight, String pageWidth, String templateString, HashSet<String> testCodeList, String isGroup, Entry entry) {
		// TODO Auto-generated method stub
		
		boolean returnVal = false;
		String insertUpdateFlag = "1";
		if(!isNewTemplate)
		{
			insertUpdateFlag = "2";
		}
			returnVal = insertNewDynamicTemplate(templateSequenceId,
					 templateName,  headerHeight,
					 headerWidth,  footerHeight,  footerWidth,
					 pageHeight,  pageWidth,  templateString, testCodeList, isGroup, insertUpdateFlag, entry);
		
		
		return returnVal;
	}
	
	
	
	public boolean insertNewDynamicTemplate(String templateSequenceId,
			String templateName, String headerHeight,
			String headerWidth, String footerHeight, String footerWidth,
			String pageHeight, String pageWidth, String templateString, HashSet<String> testCodeList, String isGroup, 
			String insertUpdateFlag, Entry entry)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CallableStatement cstmt = null;
		tx.begin();
		Connection conn = tx.getConnection();				
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DYNAMIC.INSERT.NEWTEMPLATE";
		ResultSet rs = null;	
		try {
		//String query = HelperMethodsDAO.getQuery(filename, queryKey);
		//System.out.println("query=" + query);
		
		cstmt = conn
				.prepareCall("{call pkg_inv_template.storeDynamicTemplate(?,?,?,?,?,?,?,?,?,?)}");

		cstmt.setString(1, templateSequenceId);
		cstmt.setString(2, templateName);
		cstmt.setString(3, insertUpdateFlag);
		cstmt.setString(4, templateString);
		cstmt.setString(5, isGroup);
		cstmt.setString(6, entry.getHospitalCode());
		cstmt.setString(7,entry.getLabCode());
		cstmt.setString(8, entry.getTestCode());
		cstmt.setString(9, entry.getGroupCode());
		cstmt.registerOutParameter(10, Types.VARCHAR);
		// cstmt.setString(6, ""+hospitalCode);
		cstmt.execute();
//		queryKey = "DYNAMIC.UPDATE.TESTMST";
//		String query = HelperMethodsDAO.getQuery(filename, queryKey);

	//	System.out.println("Query ---------> " + query);
//		Iterator<String> itr=testCodeList.iterator();
		 
//		while(itr.hasNext())
//		{
//			Sequence sq = new Sequence();
//			Map populateMap = new HashMap();;
//			populateMap.put(sq.next(), templateSequenceId);
//			populateMap.put(sq.next(), itr.next());			
//			HelperMethodsDAO.excecuteUpdate(conn, query, populateMap);
//		}		
		}
		catch(Exception e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
			return false;
		}
		finally
		{
			try {
				cstmt.close();
				conn.commit();
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return false;
			}
		}
		return true;
		
	}
	
	public TestGroupTreeListVO fetchTestForDynamicTemplate(String templateSeqId, TestGroupTreeListVO testGroupTreeListVO,
			Entry entry)
	{		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CallableStatement cstmt = null;
		tx.begin();
		Connection conn = tx.getConnection();				
		String fileName = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = null; 
		ResultSet rs = null;
		
		String isGroup = entry.getIsGroupTest();
		String hospitalCode = entry.getHospitalCode();
		String labCode = entry.getLabCode();
		String groupCode = entry.getGroupCode();
		String testCode = entry.getTestCode();
				
		boolean isGroupTemplate = false;
		if(isGroup.equals("1"))
		{
			isGroupTemplate = true;
			queryKey = "DYNAMIC.SELECT.TEMPLATE.GROUPDETAILS";
		}
		else
		{
			queryKey = "DYNAMIC.SELECT.TEMPLATE.TESTDETAILS";
		}
		try {
			String query = HelperMethodsDAO.getQuery(fileName, queryKey);
			Sequence seq = new Sequence();
			Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
			 populateMap.put(seq.next(), templateSeqId);
			 if(isGroupTemplate)
			 {
				 populateMap.put(seq.next(), hospitalCode);
			 }
			// populateMap.put(seq.next(), isGroup);
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			while (rs.next()) {
				if(rs.getString(1)!=null)
				{
					Test obj=new Test();
					obj.setTestCode(rs.getString(1));
					obj.setTestName(rs.getString(2));
					
					
					if(testGroupTreeListVO.getTestModifyList()==null)
					testGroupTreeListVO.setTestModifyList(new ArrayList<Test>());
					
					
					testGroupTreeListVO.getTestModifyList().add(obj);
					if(isGroupTemplate) {
						testGroupTreeListVO.setGroupName(rs.getString(3));
						testGroupTreeListVO.setHospitalName(rs.getString(4));
					}
				}	
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			try{
				if(rs != null)
					rs.close();
				if(conn != null )
					conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return testGroupTreeListVO;
			

	}
	
	public List<Test> getTestsInGroup(String groupCode, String hospitalCode)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CallableStatement cstmt = null;
		tx.begin();
		Connection conn = tx.getConnection();				
		String fileName = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "DYNAMIC.SELECT.TESTS.FOR.GROUP";
		List<Test> testList = new ArrayList<Test>();
		ResultSet rs = null;
		try {
			String query = HelperMethodsDAO.getQuery(fileName, queryKey);
			Sequence seq = new Sequence();
			Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
			 populateMap.put(seq.next(), groupCode);
			 populateMap.put(seq.next(), hospitalCode);
			// populateMap.put(seq.next(), isGroup);
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			
			while (rs.next()) {
				if(rs.getString(1)!=null)
				{
					Test obj=new Test();
					obj.setTestCode(rs.getString(1));
					obj.setTestName(rs.getString(2));
					
					testList.add(obj);					
					
				}	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try{
				if(rs != null)
					rs.close();
				if(conn != null )
					conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return testList;
	}
	
	
	//to get dept parameter is. puneet
	public InputStream getClobObjectFromDatabaseForDepartmentResultEntryFormDocument() {
		// Siddharth Srivastava, 09/03/2015
		// Check all the conditions before implementing to store InputStream to
		// session
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		java.io.InputStream ip = null;
		CallableStatement cstmt = null;
		try {
			cstmt = conn
					.prepareCall("{call pkg_inv_template.getdepartmentresultEntryFormDocument(?,?)}");
			// cstmt.registerOutParameter(1, Types.CLOB);
			// Siddharth Srivastava, 30/02/2015
			// Changed after consulting the mhmis workspace
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.execute();

			// Clob requisitionFormClob = cstmt.getClob(1);
			String requisitionFormClob = cstmt.getString(1);
			String error = cstmt.getString(2);
			if (error.equals("SUCCESS")) {
				// ip = requisitionFormClob.getAsciiStream();
				if (requisitionFormClob != null)
					ip = new ByteArrayInputStream(
							requisitionFormClob.getBytes());
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
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			// tx.close();
		}

		// LOGGER_INV.log(Level.INFO,"input stream =="+ip);
		return ip;
	}


	
	//to get requisition parameter is. chandan
		public InputStream getClobObjectFromDatabaseForRequisitionFormResultEntryFormDocument() {
			// Siddharth Srivastava, 09/03/2015
			// Check all the conditions before implementing to store InputStream to
			// session
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn
						.prepareCall("{call pkg_inv_template.getrequisitionformresultentryformdocument(?,?)}");
				// cstmt.registerOutParameter(1, Types.CLOB);
				// Siddharth Srivastava, 30/02/2015
				// Changed after consulting the mhmis workspace
				cstmt.registerOutParameter(1, Types.VARCHAR);
				cstmt.registerOutParameter(2, Types.VARCHAR);
				cstmt.execute();

				// Clob requisitionFormClob = cstmt.getClob(1);
				String requisitionFormClob = cstmt.getString(1);
				String error = cstmt.getString(2);
				if (error.equals("SUCCESS")) {
					// ip = requisitionFormClob.getAsciiStream();
					if (requisitionFormClob != null)
						ip = new ByteArrayInputStream(
								requisitionFormClob.getBytes());
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
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
				// tx.close();
			}

			// LOGGER_INV.log(Level.INFO,"input stream =="+ip);
			return ip;
		}
		
	
	//for department specific loading
	public TestGroupTreeListVO fetchTestForDepartmentTemplate(String templateSeqId, TestGroupTreeListVO testGroupTreeListVO,
			Entry entry)
	{		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		CallableStatement cstmt = null;
		tx.begin();
		Connection conn = tx.getConnection();				
		String fileName = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = null; 
		ResultSet rs = null;
		
		String isGroup = entry.getIsGroupTest();
		String hospitalCode = entry.getHospitalCode();
		String labCode = entry.getLabCode();
		String groupCode = entry.getGroupCode();
		String testCode = entry.getTestCode();
				
		boolean isGroupTemplate = false;
		if(isGroup.equals("1"))
		{
			isGroupTemplate = true;
			queryKey = "DEPARTMENT.SELECT.TEMPLATE.GROUPDETAILS";
		}
		else
		{
			queryKey = "DEPARTMENT.SELECT.TEMPLATE.TESTDETAILS";
		}
		try {
			String query = HelperMethodsDAO.getQuery(fileName, queryKey);
			Sequence seq = new Sequence();
			Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
			 populateMap.put(seq.next(), templateSeqId);
			 if(isGroupTemplate)
			 {
				 populateMap.put(seq.next(), hospitalCode);
			 }
			// populateMap.put(seq.next(), isGroup);
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

			while (rs.next()) {
				if(rs.getString(1)!=null)
				{
					Test obj=new Test();
					obj.setTestCode(rs.getString(1));
					obj.setTestName(rs.getString(2));
					
					
					if(testGroupTreeListVO.getTestModifyList()==null)
					testGroupTreeListVO.setTestModifyList(new ArrayList<Test>());
					
					
					testGroupTreeListVO.getTestModifyList().add(obj);
					if(isGroupTemplate) {
						testGroupTreeListVO.setGroupName(rs.getString(3));
						testGroupTreeListVO.setHospitalName(rs.getString(4));
					}
				}	
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally {
			try{
				if(rs != null)
					rs.close();
				if(conn != null )
					conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return testGroupTreeListVO;
			

	}
	
	
	
	//for dept test parameters data - puneet
	public Element getDepartmentResultEntryFormParameters(Document ownerDocument, Element resultEntryFormParamterElement, String testCode)
	{
		
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		tx.begin();
		Connection conn = tx.getConnection();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		
		
		String queryKey = "SELECT.TEMPLATE_DATA.DEPTTEST_PARAMETER_DETAIL";
		//String queryKey = "SELECT.TEMPLATE_DATA.TEST_PARAMETER_DETAIL";
		ResultSet rs = null;
		
		
		
		try
		{
			
			String query = HelperMethodsDAO.getQuery(filename, queryKey);
			//LOGGER_INV.log(Level.INFO,"query="+query);
			Sequence seq = new Sequence();
			Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
			 populateMap.put(seq.next(), testCode);
			
			
			 rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			while(rs.next())
			{
				Element element=ownerDocument.createElement("element");
				element.setAttribute("levelNo",rs.getString(1));
				element.setAttribute("parameterCode",rs.getString(4));// adding labcode only to the testparameterCode whenever processing it remove labcode
				element.setAttribute("parameterName",rs.getString(3));
				element.setAttribute("parentParameterCode",rs.getString(5));
				element.setAttribute("CriteriaCode",rs.getString(6));
				element.setAttribute("valueType",rs.getString(7));// represents the type of template(simple,horizontal,vertical)
				element.setAttribute("objectId",rs.getString(8));
				element.setAttribute("removeStatus","0");
				element.setAttribute("functionName",rs.getString(9));
				element.setAttribute("eventName",rs.getString(10));
				element.setAttribute("eventValidationString",rs.getString(9));
				element.setAttribute("property",rs.getString(11));
				element.setAttribute("isparameternamedisplayed",rs.getString(12));
				element.setAttribute("labelelementdisplaytype","false");
				element.setAttribute("labelAlignment",rs.getString(13));
				element.setAttribute("elementAlignment",rs.getString(14));
				
				String defaultValue = "-";
				if(rs.getString(19) != null && rs.getString(19).length() > 0 )
					defaultValue = rs.getString(19);
				
				element.setAttribute("defaultValue",defaultValue);
				element.setAttribute("isPrintable",rs.getString(15)); 
				element.setAttribute("queryCode",rs.getString(16));
				element.setAttribute("buttonName",rs.getString(17)); 
				element.setAttribute("callUrl",rs.getString(18)); 
				//element.setAttribute("parentparametername",rs.getString(22));  //check this
				element.setAttribute("parentparametername","Parent Label");  //Hardcoded for new version as of now
				element.setAttribute("isTestGroup","NO");
				element.setAttribute("typeOfElement","RT");//RT for resultEntry
				element.setAttribute("templateType","resultentrytemplate");//
				element.setAttribute("labTestCode",testCode);
				element.setAttribute("originalname","template#"+((rs.getString(5)==null || rs.getString(5).equals(rs.getString(4))==false)?"":rs.getString(5)) +rs.getString(4));
				//LOGGER_INV.log(Level.INFO,"previous lab test code ="+testCode);
				element.setAttribute("previousLabTestCode", testCode);
				
				resultEntryFormParamterElement.appendChild(element);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return resultEntryFormParamterElement;
	}
	
	
	
	
	//dynamic template id for the test dept template - puneet
	  public String getDynamicDeptTemplateStatus(String testCode, String hospitalCode, boolean isGroupWithDynamicTemplate, String labCode)
	    {
		  // testCode is Group Code if isGroupWithDynamicTemplate = true
		  Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey = null;
		/*	if(isGroupWithDynamicTemplate)
			{
				queryKey = "DYNAMIC.GROUPTEMPLATE.STATUS";
			}
			else
			{*/
				queryKey = "DYNAMIC.DEPTTESTTEMPLATE.STATUS";
			/*}*/
			
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();			
			ResultSet rs = null;
			String dynamicTemplateID = null;
			boolean isDynamic = false;
			String strIsDynamic = null;
			 
			try {
				String query = HelperMethodsDAO.getQuery(filename, queryKey);

				System.out.println("Query ---------> " + query);
				Sequence sq = new Sequence();

				populateMAP.put(sq.next(), testCode);
			/*	if(isGroupWithDynamicTemplate)
				{
					populateMAP.put(sq.next(), hospitalCode);
					populateMAP.put(sq.next(), labCode);
				}*/
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				while (rs.next()) {
					strIsDynamic  = rs.getString(1);
	                  dynamicTemplateID = rs.getString(2);
				}
				if(strIsDynamic != null && strIsDynamic.equals("1"))
	            	  isDynamic = true;		  		  	    		    	
	              
	              
	          } catch (Exception e) {
	                e.printStackTrace();
	             // Log(Level.SEVERE, e);	             
	          } finally {
	              try {
	            	  if(rs != null)
	            		  rs.close();
	                  if (conn != null) {
	                     // conn.commit();
	                      conn.close();
	                  }
	              } catch (SQLException e) {

	                  e.printStackTrace();
	                  //Log(Level.SEVERE, e);
	              }
	          }
	          
	          return dynamicTemplateID;

	    }
	  
	  
	  
	//for dept test parameters data - puneet
		public Element getRequisitionFormParameters(Document ownerDocument, Element resultEntryFormParamterElement, String testCode)
		{
			
			
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			
			
			String queryKey = "SELECT.TEMPLATE_DATA.REQTEST_PARAMETER_DETAIL";
			//String queryKey = "SELECT.TEMPLATE_DATA.TEST_PARAMETER_DETAIL";
			ResultSet rs = null;
			
			
			
			try
			{
				
				String query = HelperMethodsDAO.getQuery(filename, queryKey);
				//LOGGER_INV.log(Level.INFO,"query="+query);
				Sequence seq = new Sequence();
				Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
				 populateMap.put(seq.next(), testCode);
				
				
				 rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				
				while(rs.next())
				{
					Element element=ownerDocument.createElement("element");
					element.setAttribute("levelNo",rs.getString(1));
					element.setAttribute("parameterCode",rs.getString(4));// adding labcode only to the testparameterCode whenever processing it remove labcode
					element.setAttribute("parameterName",rs.getString(3));
					element.setAttribute("parentParameterCode",rs.getString(5));
					element.setAttribute("CriteriaCode",rs.getString(6));
					element.setAttribute("valueType",rs.getString(7));// represents the type of template(simple,horizontal,vertical)
					element.setAttribute("objectId",rs.getString(8));
					element.setAttribute("removeStatus","0");
					element.setAttribute("functionName",rs.getString(9));
					element.setAttribute("eventName",rs.getString(10));
					element.setAttribute("eventValidationString",rs.getString(9));
					element.setAttribute("property",rs.getString(11));
					element.setAttribute("isparameternamedisplayed",rs.getString(12));
					element.setAttribute("labelelementdisplaytype","false");
					element.setAttribute("labelAlignment",rs.getString(13));
					element.setAttribute("elementAlignment",rs.getString(14));
					
					String defaultValue = "-";
					if(rs.getString(19) != null && rs.getString(19).length() > 0 )
						defaultValue = rs.getString(19);
					
					element.setAttribute("defaultValue",defaultValue);
					element.setAttribute("isPrintable",rs.getString(15)); 
					element.setAttribute("queryCode",rs.getString(16));
					element.setAttribute("buttonName",rs.getString(17)); 
					element.setAttribute("callUrl",rs.getString(18)); 
					//element.setAttribute("parentparametername",rs.getString(22));  //check this
					element.setAttribute("parentparametername","Parent Label");  //Hardcoded for new version as of now
					element.setAttribute("isTestGroup","NO");
					element.setAttribute("typeOfElement","RT");//RT for resultEntry
					element.setAttribute("templateType","resultentrytemplate");//
					element.setAttribute("labTestCode",testCode);
					element.setAttribute("originalname","template#"+((rs.getString(5)==null || rs.getString(5).equals(rs.getString(4))==false)?"":rs.getString(5)) +rs.getString(4));
					//LOGGER_INV.log(Level.INFO,"previous lab test code ="+testCode);
					element.setAttribute("previousLabTestCode", testCode);
					
					resultEntryFormParamterElement.appendChild(element);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally {
				try {
					if(rs != null)
						rs.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			return resultEntryFormParamterElement;
		}
		
		
		public TestGroupTreeListVO fetchTestForDynamicRequisitionTemplate(String templateSeqId, TestGroupTreeListVO testGroupTreeListVO,
				Entry entry)
		{		
			JDBCTransactionContext tx = new JDBCTransactionContext();
			CallableStatement cstmt = null;
			tx.begin();
			Connection conn = tx.getConnection();				
			String fileName = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey = null; 
			ResultSet rs = null;
			
			String isGroup = entry.getIsGroupTest();
			String hospitalCode = entry.getHospitalCode();
			String labCode = entry.getLabCode();
			String groupCode = entry.getGroupCode();
			String testCode = entry.getTestCode();
					
			boolean isGroupTemplate = false;
			if(isGroup.equals("1"))
			{
				isGroupTemplate = true;
				queryKey = "DYNAMIC.SELECT.TEMPLATE.GROUPDETAILS";
			}
			else
			{
				queryKey = "DYNAMIC.SELECT.TEMPLATE.TESTDETAILS_REQUISITIONFORM";
			}
			try {
				String query = HelperMethodsDAO.getQuery(fileName, queryKey);
				Sequence seq = new Sequence();
				Map<Integer, String> populateMap = new HashMap<Integer, String>(1);
				 populateMap.put(seq.next(), templateSeqId);
				 if(isGroupTemplate)
				 {
					 populateMap.put(seq.next(), hospitalCode);
				 }
				// populateMap.put(seq.next(), isGroup);
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

				while (rs.next()) {
					if(rs.getString(1)!=null)
					{
						Test obj=new Test();
						obj.setTestCode(rs.getString(1));
						obj.setTestName(rs.getString(2));
						
						
						if(testGroupTreeListVO.getTestModifyList()==null)
						testGroupTreeListVO.setTestModifyList(new ArrayList<Test>());
						
						
						testGroupTreeListVO.getTestModifyList().add(obj);
						if(isGroupTemplate) {
							testGroupTreeListVO.setGroupName(rs.getString(3));
							testGroupTreeListVO.setHospitalName(rs.getString(4));
						}
					}	
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			finally {
				try{
					if(rs != null)
						rs.close();
					if(conn != null )
						conn.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			return testGroupTreeListVO;
				

		}
		
		
		
		private InputStream getClobObjectForDynamicTemplateDF(String dynamicTemplateID,String testformtype,String oldtest,String newtest)
	    {
		 	Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
			String queryKey = "DYNAMIC.GET.TESTTEMPLATE";

		 	JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();			
			ResultSet rs = null;
	        java.io.InputStream ip = null;
	        //CallableStatement cstmt = null;
	 
	        try {        
	        	
	        	String query = HelperMethodsDAO.getQuery(filename, queryKey);

				System.out.println("Query ---------> " + query);
				Sequence sq = new Sequence();

				populateMAP.put(sq.next(), dynamicTemplateID);
				
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);				      
	        		       	          
	            if (rs.next()) {
	                String requisitionFormClob = rs.getString(1);
	                
	                if(testformtype.equals("0"))//chandan 
	                {
	                	requisitionFormClob=requisitionFormClob.replaceAll(oldtest, newtest);
	                	
	                }
	                
	                if (requisitionFormClob != null) {
	                    ip = new ByteArrayInputStream(requisitionFormClob.getBytes());
	                }

	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            //Log(Level.SEVERE, e);
	           

	        } finally {
	            try {
	               // pstmt.close();
	                if (conn != null) {
	                   // conn.commit();
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();	                
	            }
	        }
	        return ip;
	        }
		
		
		public Node getTemplateNodeObjtestwise(String id, String nodeId, HttpSession session, boolean isPrintWithDynamicTemplate,String toview,String testcode) {
			Document document = null;

			Node node = null;

			// Either fromCache was false or the object was not found, so
			// call loadBookVObj to create it
			if(!isPrintWithDynamicTemplate) {
				document = (Document) session
						.getAttribute("investigationDocumentCacheVO" + id);
			}
//			if (document == null)
//				document = loadDocumentObj(id, session);
			
			String dynamicTemplateID = nodeId;;
	       boolean isDynamicTemplate = isPrintWithDynamicTemplate;
	        if (document == null) {
	        	
	        	if(isPrintWithDynamicTemplate)
	        	{
	        		//nodeId = dynamicTemplateID;
	        		document = loadDynamicDocumentObj(dynamicTemplateID, session);
	        		isDynamicTemplate = true;
	        	}
	        	else
	        	{
	        		if(id.equals("1"))
	        			document = loadDocumentObjtestwise(id, session,nodeId);	
	        		else
	        		document = loadDocumentObj(id, session);        		
	        	}
	        		
	        }

//			if (document != null) {			
//				node = (Node) session.getAttribute("investigationDocumentCacheVO"
//						+ id + "_" + nodeId);			
//			}
	        
	        if (document != null) {
	            //LOGGER_INV.log(Level.INFO,"loading... Node form memory -->"+id);
	        	String cacheString = "investigationDocumentCacheVO" + id;
	        	if(isDynamicTemplate)
	        		cacheString = "dynamicInvestigationDocumentCacheVO";
	            node = (Node) session.getAttribute(cacheString + "_" + nodeId);
	            //LOGGER_INV.log(Level.INFO,"Getting Key ::->"+"investigationDocumentCacheVO"+id+"_"+nodeId);
	        }               
	        
			return node;
		}
		

		public Document loadDocumentObjtestwise(String id, HttpSession session,String testcode) {
			Document vObj = null;
			java.io.InputStream ip = null;

			try {
				boolean found = false;
				// enum
				// DOCUMENT_DETAILS{testtemplate,testsampletemplate,testrequisitiontemplate,templateQueryFile,printingtemplate,
				// labtestgrouptemplate,labRegisterConfiguration,laboratoryRequisitiontemplate,imageUtilityUserInformation,appletConfigurator};

				// LOGGER_INV.log(Level.INFO,"id -->"+id);
				switch ((Integer.parseInt(id))) {
				case 1:
					ip = getClobObjectFromDatabaseForResultEntryFormDocumenttestwise(testcode);
					break;
				

					
				/*
				 * case 2:
				 * ip=getClobObjectFromDatabaseForSampleCollectionFormDocument();
				 * 
				 * 
				 * break; case 3:
				 * ip=getClobObjectFromDatabaseForRequisitionFormDocument();
				 * 
				 * break; case 4:
				 * ip=getClobObjectFromDatabaseTemplateQueryDocument();
				 * 
				 * break; case 5:
				 * ip=getClobObjectFromDatabaseForPrintingFormDocument();
				 * 
				 * break; case 6:
				 * ip=getClobObjectFromDatabaseForResultEntryGroupFormDocument();
				 * 
				 * break; case 7:
				 * ip=getClobObjectFromDatabaseLabRegisterConfigDocument();
				 * 
				 * break; case 8:
				 * ip=getClobObjectFromDatabaseForLabRequisitionFormDocument();
				 * 
				 * break; case 9:
				 * ip=getClobObjectFromDatabaseImageUtilityConfDocument();
				 * 
				 * break; case 10:
				 * ip=getClobObjectFromDatabaseAppletConfiguratorDocument();
				 * 
				 * break;
				 */

				}

				// set found to true if it was found
				if(ip!=null)
				found = true;
				// cache the value object if found
				if (found) {
					vObj = builder.parse(ip);
					// investigationDocumentCacheVO.put("investigationDocumentCacheVO"+id,
					// vObj);
					/*
					 * if(Integer.parseInt(id)==10) { loadAppletConfigurator(); }
					 * if(Integer.parseInt(id)==4) { loadTemplateQuery(); }
					 * if(Integer.parseInt(id)==4) { loadAppletUserInformationXML();
					 * } else {
					 */

					loadEachNodeOfDocumentSeparately(vObj,
							"investigationDocumentCacheVO" + id, session);
					// }
				}
			} catch (Exception e) {
				e.printStackTrace();
				// Handle failure putting object to cache
			}

			return vObj;
		}

		
		public InputStream getClobObjectFromDatabaseForResultEntryFormDocumenttestwise(String testcode) {
			// Siddharth Srivastava, 09/03/2015
			// Check all the conditions before implementing to store InputStream to
			// session
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			java.io.InputStream ip = null;
			CallableStatement cstmt = null;
			try {
				cstmt = conn
						.prepareCall("{call pkg_inv_template.getresultentryformdocumenttestwise(?,?,?)}");
				// cstmt.registerOutParameter(1, Types.CLOB);
				// Siddharth Srivastava, 30/02/2015
				// Changed after consulting the mhmis workspace
				cstmt.setString(1,testcode);
				cstmt.registerOutParameter(2, Types.VARCHAR);
				cstmt.registerOutParameter(3, Types.VARCHAR);
				cstmt.execute();

				// Clob requisitionFormClob = cstmt.getClob(1);
				String requisitionFormClob = cstmt.getString(2);
				String error = cstmt.getString(3);
				if (error.equals("SUCCESS")) {
					// ip = requisitionFormClob.getAsciiStream();
					if (requisitionFormClob != null)
						ip = new ByteArrayInputStream(
								requisitionFormClob.getBytes());
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
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
				// tx.close();
			}

			// LOGGER_INV.log(Level.INFO,"input stream =="+ip);
			return ip;
		}

		
		public static boolean istemplateshowtestwiseornot(String hospitalCode) {
			// TODO Auto-generated method stub
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.FETCH.ISTEMPLATESHOWTESTWISEORNOT";

			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();
			String printWithStandardRanges = null;
			boolean returnVal = false;
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				Sequence sq = new Sequence();
				
				populateMAP.put(sq.next(), hospitalCode);

				rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
				while (rs.next()) {
					printWithStandardRanges = rs.getString(1);
				}

				if(printWithStandardRanges!=null)
				{
				
					returnVal = true;
				}
				else
					returnVal = false;
				} catch (Exception ex) {
				ex.printStackTrace();
			} finally {

				if (conn != null)
					try {
						if (rs != null) {
							rs.close();
						}
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				// tx.close();
			}
			return returnVal;
		}

		
		
		public static List<TriStringObject> getDefaultValues_textediot_val_machine(
				ResultEntryVO resultEntryVO) {
			// TODO Auto-generated method stub

			String query = null;
			ResultSet rs = null;

			Sequence sq = new Sequence();
			Map populateMap = new HashMap();

			List<TriStringObject> resultValidationList = new ArrayList<TriStringObject>();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.HIVT_PARAMETER_DTL.DEFAULTVALUES_VALI_TEXTEDIOTR";
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();

			populateMap.put(sq.next(), resultEntryVO.getTestCode());
			// populateMap.put(sq.next(), resultEntryVO.getHospitalcode());

			// populateMap.put(sq.next(), hosCode);

			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				System.out.println("Query :WorkOrder List For Result Validation "
						+ query);
			} catch (Exception e) {
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}
			try {

				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

				while (rs.next()) {

					String parentCode = "";

					if (rs.getString(1).equals(rs.getString(3)))
						parentCode = rs.getString(3);
					else
						parentCode = rs.getString(1) + rs.getString(3);

					TriStringObject triStringObject = new TriStringObject(
							rs.getString(1), rs.getString(2), parentCode);
					if (resultValidationList == null)
						resultValidationList = new ArrayList<TriStringObject>();

					resultValidationList.add(triStringObject);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new HisDataAccessException(
						"RESULT Validation :getResultValidfationDataList  :" + e);
			} finally {
				if (conn != null)
					try {
						if (rs != null) {
							rs.close();
						}
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				// tx.close();
			}

			return resultValidationList;

		}

		
		
		public static List<TriStringObject> getDefaultValues_textediot_val_machine( InvResultValidationRespVO invResultValidationRespVO) {
			// TODO Auto-generated method stub

			String query = null;
			ResultSet rs = null;

			Sequence sq = new Sequence();
			Map populateMap = new HashMap();

			List<TriStringObject> resultValidationList = new ArrayList<TriStringObject>();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.HIVT_PARAMETER_DTL.DEFAULTVALUES_VALI_TEXTEDIOTR";
			JDBCTransactionContext tx = new JDBCTransactionContext();
			tx.begin();
			Connection conn = tx.getConnection();

			populateMap.put(sq.next(), invResultValidationRespVO.getTestCode());
			// populateMap.put(sq.next(), resultEntryVO.getHospitalcode());

			// populateMap.put(sq.next(), hosCode);

			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				System.out.println("Query :WorkOrder List For Result Validation "
						+ query);
			} catch (Exception e) {
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}
			try {

				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);

				while (rs.next()) {

					String parentCode = "";

					if (rs.getString(1).equals(rs.getString(3)))
						parentCode = rs.getString(3);
					else
						parentCode = rs.getString(1) + rs.getString(3);

					TriStringObject triStringObject = new TriStringObject(
							rs.getString(1), rs.getString(2), parentCode);
					if (resultValidationList == null)
						resultValidationList = new ArrayList<TriStringObject>();

					resultValidationList.add(triStringObject);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new HisDataAccessException(
						"RESULT Validation :getResultValidfationDataList  :" + e);
			} finally {
				if (conn != null)
					try {
						if (rs != null) {
							rs.close();
						}
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				// tx.close();
			}

			return resultValidationList;

		}

		
}
