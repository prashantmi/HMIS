package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 




import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;
import new_investigation.vo.InvSampleMasterVO;
import new_investigation.vo.SampleAcceptanceVO;
import new_investigation.vo.UOMMasterVO;
 
import new_investigation.vo.TestNewMasterVO;
import hisglobal.vo.LabTestMasterVO;
import new_investigation.vo.TestParameterMasterVO;

public class InvTestParameterMstDAO extends DataAccessObject implements InvTestParameterMstDAOi
{

	public InvTestParameterMstDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	 
		
	
				
				//START   TEST Parameter MASTER  Added By Pathan Basha //
				public TestParameterMasterVO[] getTestParameterDetail(UserVO userVO)
				{
					TestParameterMasterVO[] TestParameterMasterVOs=null;
					ValueObject[] vo=null;
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT_TEST_PARAMETER.HIVT_TEST_PARAMETER_MST";
					Sequence sq = new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						populateMap.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
					 
						 
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("BldCheckListMstDAO.populateMAP::" + e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						
						if (!rs.next())
						{
							throw new HisRecordNotFoundException("No Lab Found");
						}
						else
						{
							rs.beforeFirst();
							vo = HelperMethods.populateVOfrmRS(TestParameterMasterVO.class, rs);
							TestParameterMasterVOs = new TestParameterMasterVO[vo.length];
							for (int i = 0; i < vo.length; i++) {
								TestParameterMasterVOs[i] = (TestParameterMasterVO) vo[i];
							}
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException(e.getMessage());	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }
					return TestParameterMasterVOs;
				}


				
				
				public void createTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
				{
					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey = "INSERT.HIVT_TEST_PARAMETER_MST";
					String queryKey1 = "INSERT.HIVT_DEPTTEST_PARAMETER_MST";
					String queryKey2 = "INSERT.HIVT_REQFORM_TEST_PARAMETER_MST";
					try
					{
						if(testParameterMasterVO.getParaType().equals("0"))
						query = HelperMethodsDAO.getQuery(filename, queryKey);
						else if(testParameterMasterVO.getParaType().equals("1"))
							query = HelperMethodsDAO.getQuery(filename, queryKey1);
						else if(testParameterMasterVO.getParaType().equals("2")) // requisition form
							query = HelperMethodsDAO.getQuery(filename, queryKey2);
						else 
							;//requisition form
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
					}
					try
					{
						   populateMAP.put(sq.next(), testParameterMasterVO.getTestCode());
						   populateMAP.put(sq.next(), testParameterMasterVO.getElementType());
						   populateMAP.put(sq.next(), testParameterMasterVO.getParameterCode());
						   populateMAP.put(sq.next(), testParameterMasterVO.getArrangeAs());
						   populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
						   populateMAP.put(sq.next(),_UserVO.getSeatId() );
						   populateMAP.put(sq.next(), testParameterMasterVO.getTestCode()+testParameterMasterVO.getParameterCode());
						   populateMAP.put(sq.next(), testParameterMasterVO.getRemarks());
						   populateMAP.put(sq.next(), testParameterMasterVO.getCriteriaDesk());
						   populateMAP.put(sq.next(), testParameterMasterVO.getFunctionName());
						   populateMAP.put(sq.next(), testParameterMasterVO.getEvent());
						   populateMAP.put(sq.next(), testParameterMasterVO.getEventFunction());							
						   populateMAP.put(sq.next(), testParameterMasterVO.getElementProperty());
						   populateMAP.put(sq.next(), testParameterMasterVO.getShowParameterNameasLabel());
						   populateMAP.put(sq.next(), testParameterMasterVO.getLabelAlignment());
						   populateMAP.put(sq.next(), testParameterMasterVO.getElementAlign());
						   populateMAP.put(sq.next(), testParameterMasterVO.getElementPosition());
						   populateMAP.put(sq.next(), testParameterMasterVO.getIsPrintRequired());
						   populateMAP.put(sq.next(), testParameterMasterVO.getQuery());
						   populateMAP.put(sq.next(), testParameterMasterVO.getButtonName());
						   populateMAP.put(sq.next(), testParameterMasterVO.getUrlCode());
						  /* populateMAP.put(sq.next(), testParameterMasterVO.getCallingURL());*/
						   populateMAP.put(sq.next(), testParameterMasterVO.getLoincScale());
						   populateMAP.put(sq.next(), testParameterMasterVO.getTextEditorValue());
						   populateMAP.put(sq.next(), testParameterMasterVO.getHyperName());
						   populateMAP.put(sq.next(), testParameterMasterVO.getParaType());
						   
						   if(testParameterMasterVO.getParaType().equals("2")) // requisition form
						   {
						   populateMAP.put(sq.next(), testParameterMasterVO.getReqMasterFormType()==null?"0":testParameterMasterVO.getReqMasterFormType());
						   populateMAP.put(sq.next(), testParameterMasterVO.getMastertestCode().equals("-1")?"0":testParameterMasterVO.getMastertestCode());
						   }
						 
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
					}
					try
					{
						HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
						throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
					}
				}
				
				
				
				public void fetchTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
				{
					
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List testEditorValue=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="";
					if(testParameterMasterVO.getParaType().equals("2")) // for requisition form
					{
						 queryKey="SELECT.HIVT_REQFORM_TEST_PARAMETER_MST";
					}
					else if(testParameterMasterVO.getParaType().equals("1")) // for dept form
					{
						
						queryKey="SELECT.HIVT_DEPT_TEST_PARAMETER_MST";
					}
					else
					{
					 queryKey="SELECT.HIVT_TEST_PARAMETER_MST";
					}
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
					populateMap.put(sq.next(), testParameterMasterVO.getTestCode());
					populateMap.put(sq.next(), testParameterMasterVO.getParameterCode());
					 
					 
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							HelperMethods.populateVOfrmRS(testParameterMasterVO, rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					 
				}
				
				
				// for Updating The old Record
				public void updateTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
				{
					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey ="UPDATE.HIVT_TEST_PARAMETER_MST";
					String queryKey1 ="UPDATE.HIVT_DEPTTEST_PARAMETER_MST";
					String queryKey2 ="UPDATE.HIVT_REQFORM_TEST_PARAMETER_MST";
					try
					{
						if(testParameterMasterVO.getParaType().equals("0"))
						query = HelperMethodsDAO.getQuery(filename, queryKey);
						else if(testParameterMasterVO.getParaType().equals("1"))
							query = HelperMethodsDAO.getQuery(filename, queryKey1);
						else if(testParameterMasterVO.getParaType().equals("2"))
							query = HelperMethodsDAO.getQuery(filename, queryKey2);
						else
							;//requisition form
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
					}
					try
					{
				              if(testParameterMasterVO.getParaType().equals("2"))
							  populateMAP.put(sq.next(), testParameterMasterVO.getMappedTest()==null?"0":testParameterMasterVO.getMappedTest());
						
						
						   populateMAP.put(sq.next(), testParameterMasterVO.getElementType());
						   populateMAP.put(sq.next(), testParameterMasterVO.getArrangeAs());
						   populateMAP.put(sq.next(), testParameterMasterVO.getTestCode()+testParameterMasterVO.getParentParameter());
						   populateMAP.put(sq.next(),_UserVO.getSeatId() );
						   populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
						   populateMAP.put(sq.next(), testParameterMasterVO.getRemarks());
						   populateMAP.put(sq.next(), testParameterMasterVO.getCriteriaDesk());
						   populateMAP.put(sq.next(), testParameterMasterVO.getFunctionName());
						   populateMAP.put(sq.next(), testParameterMasterVO.getEvent());
						   populateMAP.put(sq.next(), testParameterMasterVO.getEventFunction());							
						   populateMAP.put(sq.next(), testParameterMasterVO.getElementProperty());
						   populateMAP.put(sq.next(), testParameterMasterVO.getShowParameterNameasLabel());
						   populateMAP.put(sq.next(), testParameterMasterVO.getLabelAlignment());
						   populateMAP.put(sq.next(), testParameterMasterVO.getElementAlign());
						   populateMAP.put(sq.next(), testParameterMasterVO.getElementPosition());
						   populateMAP.put(sq.next(), testParameterMasterVO.getIsPrintRequired());
						   populateMAP.put(sq.next(), testParameterMasterVO.getQuery());
						   populateMAP.put(sq.next(), testParameterMasterVO.getButtonName());
						   populateMAP.put(sq.next(), testParameterMasterVO.getUrlCode());
						   /*populateMAP.put(sq.next(), testParameterMasterVO.getCallingURL());*/
						   populateMAP.put(sq.next(), testParameterMasterVO.getLoincScale());
						   populateMAP.put(sq.next(), testParameterMasterVO.getTextEditorValue());
						   populateMAP.put(sq.next(), testParameterMasterVO.getHyperName());
						   
						   populateMAP.put(sq.next(), testParameterMasterVO.getTestCode());
						   populateMAP.put(sq.next(), testParameterMasterVO.getParameterCode());
						  
						
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
					}
					try
					{
						HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
						throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
					}
				}

				public void savemodifyTestParameter(TestParameterMasterVO Test, UserVO _UserVO)
				{
					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey = "INSERT_MODIFY.HIVT_TEST_PARAMETER_MST";

					try
					{
						query = HelperMethodsDAO.getQuery(filename, queryKey);
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
					}
					try
					{
						 	
						populateMAP.put(sq.next(), InvestigationConfig.HOSPITAL_CODE);
						 		        					  
						  populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
   			   		        	populateMAP.put(sq.next(), _UserVO.getSeatId());
                         
						
					 }
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
					}
					try
					{
						HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
						throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
					}
				}

				
				 /// check for duplicate ParameterName
				public String checkDuplicateTestParameter(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
				{
					String query = "";
					ResultSet rs;
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey  ="CHECKDUPLICATE.HIVT_TEST_PARAMETER_MST";
					String queryKey1 ="CHECKDUPLICATE.HIVT_DEPTTEST_PARAMETER_MST";
					String queryKey2 ="CHECKDUPLICATE.HIVT_REQFORM_TEST_PARAMETER_MST";
					try
					{ 
						if(testParameterMasterVO.getParaType().equals("0"))
						query = HelperMethodsDAO.getQuery(filename, queryKey);
						else if(testParameterMasterVO.getParaType().equals("1"))
							query = HelperMethodsDAO.getQuery(filename, queryKey1);
						else if(testParameterMasterVO.getParaType().equals("2")) // requisition form
							query = HelperMethodsDAO.getQuery(filename, queryKey2);
						else
							;//for resource form
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
					}
					try
					{
						populateMAP.put(sq.next(), testParameterMasterVO.getTestCode());
						populateMAP.put(sq.next(), testParameterMasterVO.getParameterCode());
						 
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
					}
					String record=null;
					try
					{
						rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
						while (rs.next())
						{
							record=rs.getString(1);
						}
					}
					catch (Exception e)
					{
						if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
						else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
					}
					return record;
				}
				
				
				/// check for duplicate Parameter Name on Modify
				public String checkDuplicateTestParameterModify(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
				{
					String query = "";
					ResultSet rs;
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey ="CHECKDUPLICITY_MODIFY.HIVT_TEST_PARAMETER_MST";
					try
					{
						query = HelperMethodsDAO.getQuery(filename, queryKey);
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
					}
					try
			 		{
						 
						populateMAP.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);         
					 
						 
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
					}
					String record=null;
					try
					{
						rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
						while (rs.next())
					 	{
							record=rs.getString(1);
						}
					}
					catch (Exception e)
					{
						if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
						else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
					}
					return record;
				}

				
				public List getTestCombo(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List departcombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.TEST_PARAMETER_COMBO.HIVT_TEST_PARAMETER_MST";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					//populateMap.put(sq.next(), _UserVO.getHospitalCode());
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return departcombo;
				}
				
				
				public List getParameterCombo(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List departcombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.PARAMETER_COMBO.HIVT_TEST_PARAMETER_MST";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					 
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return departcombo;
				}
				
				public List getCriteriaCombo(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List departcombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.CRITERIA_COMBO.HIVT_REF_CRITERIA_MST";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					 
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return departcombo;
				}
				
				
				public List getElementTypeCombo(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List departcombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.ELEMENT_TYPE_COMBO.HIVT_TESTPARA_ELEMENTTYPE_MST";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
				 
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return departcombo;
				}
				
				
				 
				
				
				public List getTestParameterCombo(TestParameterMasterVO testParameterMasterVO,UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List parameterCombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT_SET_PARAMETER.HIVT_TEST_PARAMETER_MST";
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					//populateMap.put(sq.next(), testParameterMasterVO.getTestName());
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							parameterCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return parameterCombo;
				}
				
				
	
				
				 
				
				public List<TestParameterMasterVO> CheckTestParameterCode(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
				{
					ResultSet rs = null;
					String query = "";
					Map populateMAP = new HashMap();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey = "SELECT_CHECKTESTPARAMETERCODE.HIVT_TEST_PARAMETER_MST";
					String queryKey1 = "SELECT_CHECKTESTPARAMETERCODE.HIVT_DEPTTEST_PARAMETER_MST";
					String queryKey2 = "SELECT_CHECKTESTPARAMETERCODE.HIVT_REQFORM_TEST_PARAMETER_MST";
					 
					try
					{
						if(testParameterMasterVO.getParaType().equals("0"))
						query = HelperMethodsDAO.getQuery(filename, queryKey);
						else if(testParameterMasterVO.getParaType().equals("1"))
							query = HelperMethodsDAO.getQuery(filename, queryKey1);
						else if(testParameterMasterVO.getParaType().equals("2"))  // requisition form
							query = HelperMethodsDAO.getQuery(filename, queryKey2);
						else
							;//requisition form
					}
					catch (Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
					}

					System.out.println("Query ---------> " + query);
					Sequence sq = new Sequence();
					try
					{
					 
						 
					 	 
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
					}
					 
					List<TestParameterMasterVO> listTestParameterMasterVO = new ArrayList<TestParameterMasterVO>();
					ValueObject[] valueObjects = null;
					
					try
					{
						 
						rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
						if (!rs.next())
						{
							//throw new HisRecordNotFoundException("No Patient Record");
						}
						else
						{
							rs.beforeFirst();
							valueObjects = HelperMethods.populateVOfrmRS(TestParameterMasterVO.class, rs);
							//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
							for (int i = 0; i < valueObjects.length; i++)
							{
								//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
								listTestParameterMasterVO.add((TestParameterMasterVO)valueObjects[i]);
							}
						}
					}
					catch (Exception e)
					{
						if (e.getClass() == HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException(e.getMessage());
						}
						else throw new HisDataAccessException("OnlinePatientAcceptanceDAO:setPatientEssentials:HelperMethods :: " + e);
					}
					return listTestParameterMasterVO;
				}		
				
				public List getLoincScale(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List loincScale=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.LOINC_SCALE.TEST_PARAMETER_MST";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					 
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							loincScale=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return loincScale;
				}
				
				
				
				
				public List ajaxUrlCombo(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List lsttest=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TESTNAME;
					String queryKey="SELECT.URL_COMBO.HIVT_PARAMETER_URL_MST";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					 
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							//throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							lsttest=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							//throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return lsttest;
				}
				//END  TEST PARAMETER MASTER  Added By Pathan Basha //
								

				
				public List getmastertypeTestCombo(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List departcombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.MASTERTYPE_TEST_PARAMETER_COMBO.HIVT_TEST_PARAMETER_MST";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					//populateMap.put(sq.next(), _UserVO.getHospitalCode());
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return departcombo;
				}
				

				public List<TestParameterMasterVO> fetchTestParameterForMaster(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
				{
					
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List<TestParameterMasterVO> returnList=new ArrayList<TestParameterMasterVO>();
					List testEditorValue=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="";
					List<TestParameterMasterVO> voo = new ArrayList<TestParameterMasterVO>();
					queryKey="SELECT.HIVT_REQFORM_TEST_PARAMETER_MST_FOR_MASTER";
					ValueObject[] valueObjects = null;
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					
					populateMap.put(sq.next(), testParameterMasterVO.getMastertestCode());
					populateMap.put(sq.next(),"2"); // req form para type
					populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
					 
					 
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							valueObjects = HelperMethods.populateVOfrmRS(TestParameterMasterVO.class, rs);
							for (int i = 0; i < valueObjects.length; i++)
							{
								//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
								voo.add((TestParameterMasterVO)valueObjects[i]);
								
								returnList.add(voo.get(i)); //add all other values
								}
							
							//HelperMethods.populateVOfrmRS(testParameterMasterVO, rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return returnList;
				}
				
				
				
				public List getTestComboReqform(UserVO _UserVO)
				{
					String query="";
					Map populateMap= new HashMap();
					ResultSet rs=null;
					List departcombo=new ArrayList();
					String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey="SELECT.TEST_PARAMETER_COMBO.HIVT_TEST_PARAMETER_MST_REQ_FORM";
					
					Sequence sq= new Sequence();
					Connection conn=super.getTransactionContext().getConnection();
					
					//populateMap.put(sq.next(), _UserVO.getHospitalCode());
					try
					{
						query = HelperMethodsDAO.getQuery(filename,queryKey);
					}
					catch(Exception e)
					{
						throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
					}
					try
					{
						rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
						if (!rs.next())
						{
							//throw new HisRecordNotFoundException();
						}
						else
						{
							rs.beforeFirst();
							departcombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
						}
					}
					catch(Exception e)
					{
						if(e.getClass()==HisRecordNotFoundException.class)
						{
							throw new HisRecordNotFoundException();	
						}
						else			 		
						 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
					 }	
					return departcombo;
				}

				
				
				public void updateisreqformmapped(TestParameterMasterVO testParameterMasterVO, UserVO _UserVO)
				{
					String query = "";
					Map populateMAP = new HashMap();
					Sequence sq = new Sequence();
					String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
					String queryKey ="UPDATE.HIVT_TEST_PARAMETER_MST_ISREQFORM_MAPPED";
				
					try
					{
				
				
				           query = HelperMethodsDAO.getQuery(filename, queryKey);
						
						
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
					}
					try
					{
				           
						   
						   populateMAP.put(sq.next(), testParameterMasterVO.getTestCodee());
						   
						  
						
					}
					catch (Exception e)
					{
						throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
					}
					try
					{
						HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
						throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
					}
				}
				
}
