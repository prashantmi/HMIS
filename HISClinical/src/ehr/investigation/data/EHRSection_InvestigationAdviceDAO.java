/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.investigation.data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ehr.EHRConfig;
import ehr.investigation.vo.EHRSection_InvestigationAdviceVO;


import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDetailVO;


import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;


import hisglobal.backutil.HisDAO;

import java.sql.Types;

import opd.OpdConfig;
import investigationDesk.InvestigationConfig;
import ehr.investigation.vo.*;



public class EHRSection_InvestigationAdviceDAO extends DataAccessObject 
{

	
		public EHRSection_InvestigationAdviceDAO(JDBCTransactionContext _tx)
		{
			super(_tx);
		}
		
			
			public EHRSection_InvestigationAdviceVO[]  getPatientInvestigationDetail(String _pmode, EHR_AccessPermissionVO _voEHRAccess, EHR_PatientEncounterVO _voEHREncounter, EHRSection_InvestigationAdviceVO _voEHRInvestigation)
			{
				int nProcedureIndex;
				HisDAO hisDAO_p = new HisDAO("EHR", "EHRSection_InvestigationAdviceDAO");
				String strDBErr;
				ResultSet rs;
				EHRSection_InvestigationAdviceVO[] EHRSection_InvestigationAdviceVOs;
				
				try
				{
					nProcedureIndex = hisDAO_p.setProcedure(EHRConfig.GET_EHR_PATIENT_INVESTIGATION);
					// Setting and Registering In and Out Parameters 
					hisDAO_p.setProcInValue(nProcedureIndex, "p_mode", _pmode,1);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_crno", _voEHRInvestigation.getPatCRNo(),2);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_episodecode", (_voEHRInvestigation.getEpisodeCode()==null) ? "": _voEHRInvestigation.getEpisodeCode(),3);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_hospcode", _voEHREncounter.getHospitalCode(),4);
					hisDAO_p.setProcInValue(nProcedureIndex, "p_visitno", _voEHREncounter.getEpisodeVisitNo(),5);
					hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,6); // varchar
					hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,7); // Cursor
			
					// Executing Procedure 
					hisDAO_p.executeProcedureByPosition(nProcedureIndex);
					// Getting out parameters 
					strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
					rs = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");
			
					// If Database Error Occurs, No further processing is required. 
					if (strDBErr != null && !strDBErr.equals(""))
					{
						throw new Exception("Data Base Error:" + strDBErr);
					}
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("EHRSection_InvestigationAdviceDAO.getPatientInvestigationDetail::hisDAO_p.execute" + EHRConfig.GET_EHR_PATIENT_INVESTIGATION
							+ ") -> " + e.getMessage());
				}
				
				ValueObject[] vo = {};
				try
				{
				//	ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
					if (!rs.next())
					{
						throw new HisRecordNotFoundException("");
					}
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(EHRSection_InvestigationAdviceVO.class, rs);
					EHRSection_InvestigationAdviceVOs = new EHRSection_InvestigationAdviceVO[vo.length];
					for (int i = 0; i < vo.length; i++)
					{
						EHRSection_InvestigationAdviceVOs[i] = (EHRSection_InvestigationAdviceVO) vo[i];
					}
				}
				catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
					else throw new HisDataAccessException("EHRSection_InvestigationAdviceDAO::getPatient Investigation Detail:: " + e);
				}
				return EHRSection_InvestigationAdviceVOs;
			}
			
			
			
			
			public  EHRSection_InvestigationAdviceVO getInvRaisingPatDetails(EHRSection_InvestigationAdviceVO patVO,UserVO _UserVO)
			{
				String query = "";		
				Map populateMAP = new HashMap();
				ResultSet rs = null;
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.PATIENT_DTLS.HRGT_PATIENT_DTL";
				Sequence sq = new Sequence();

				EHRSection_InvestigationAdviceVO invReqRaisingVO;
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), patVO.getPatCRNo());
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				try
				{
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
							populateMAP);
					rs.beforeFirst();
					invReqRaisingVO=new EHRSection_InvestigationAdviceVO();
					if(!rs.next())
					{
					}
					else HelperMethods.populateVOfrmRS(invReqRaisingVO,rs);
					
				}
				catch (HisRecordNotFoundException e)
				{

					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("" + e);
				}
				return invReqRaisingVO;
			}
			
			public List getLabNames(UserVO _UserVO)
			{
				String query="";
				Map populateMap= new HashMap();
				ResultSet rs=null;
				List<String> lstLabNames=new ArrayList<String>();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey="SELECT.LAB_COMBO_NAMES.HIVT_LABORATORY_MST";
				
				Sequence sq= new Sequence();
				Connection conn=super.getTransactionContext().getConnection();
				
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
				populateMap.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION); //99
				populateMap.put(sq.next(), _UserVO.getUserSeatId());
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
					//	throw new HisRecordNotFoundException();
					}
					else
					{
						rs.beforeFirst();
						if(rs.next())
						{
							lstLabNames=HelperMethodsDAO.getAlOfEntryObjects(rs);
	 					}
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
				return lstLabNames;
			}
			
			
			public List getTestNames(UserVO _UserVO)
			{
				String query="";
				Map populateMap= new HashMap();
				ResultSet rs=null;
				List<String> lstTestNames=new ArrayList<String>();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey="SELECT.TEST_COMBO_NAMES.HIVT_TEST_MST";
				
				Sequence sq= new Sequence();
				Connection conn=super.getTransactionContext().getConnection();
				
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
					//	throw new HisRecordNotFoundException();
					}
					else
					{
						rs.beforeFirst();
						if(rs.next())
						{
							lstTestNames=HelperMethodsDAO.getAlOfEntryObjects(rs);
	 					}
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
				return lstTestNames;
			}
			
			
			public List getTestGroupNames(UserVO _UserVO)
			{
				String query="";
				Map populateMap= new HashMap();
				ResultSet rs=null;
				List<String> lstTestNames=new ArrayList<String>();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey="SELECT.TEST_GROUP_NAMES.HIVT_TEST_GROUP_MST";
				
				Sequence sq= new Sequence();
				Connection conn=super.getTransactionContext().getConnection();
				
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
					//	throw new HisRecordNotFoundException();
					}
					else
					{
						rs.beforeFirst();
						if(rs.next())
						{
							lstTestNames=HelperMethodsDAO.getAlOfEntryObjects(rs);
	 					}
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
				return lstTestNames;
			}
			
			public List getAdvisedByNames(UserVO _UserVO)
			{
				String query="";
				Map populateMap= new HashMap();
				ResultSet rs=null;
				List<String> lstTestNames=new ArrayList<String>();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey="SELECT.ADVISEDBY_DOCTOR_NAMES.PIST_EMP_REGISTRATION_DTL";
				
				Sequence sq= new Sequence();
				Connection conn=super.getTransactionContext().getConnection();
				
				populateMap.put(sq.next(), InvestigationConfig.IS_VALID_ACTIVE);
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
					//	throw new HisRecordNotFoundException();
					}
					else
					{
						rs.beforeFirst();
						if(rs.next())
						{
							lstTestNames=HelperMethodsDAO.getAlOfEntryObjects(rs);
	 					}
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
				return lstTestNames;
			}
			
			
			public List getTestCodeNames(UserVO _UserVO)
			{
				String query="";
				Map populateMap= new HashMap();
				ResultSet rs=null;
				List<String> lstTestNames=new ArrayList<String>();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey="SELECT.TEST_CODE_WISE.HIVT_LABORATORY_TEST_MST";
				
				Sequence sq= new Sequence();
				Connection conn=super.getTransactionContext().getConnection();
				
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
					//	throw new HisRecordNotFoundException();
					}
					else
					{
						rs.beforeFirst();
						if(rs.next())
						{
							lstTestNames=HelperMethodsDAO.getAlOfEntryObjects(rs);
	 					}
					}
				}
				catch(Exception e)
				{
					if(e.getClass()==HisRecordNotFoundException.class)
					{
						return lstTestNames;	
					}
					else			 		
						return lstTestNames;			 
				 }	
				return lstTestNames;
			}
			
			
			
			public List<Inv_PatientAdmissionDtlVO> getPatientAdmission(EHRSection_InvestigationAdviceVO patVO, UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();
				String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.PATIENT_ADMISSION_DTLS.HIPT_PATADMISSION_DTL";

				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
			

				System.out.println("Query ---------> " + query);
				Sequence sq = new Sequence();
				try
				{
					
					populateMAP.put(sq.next(), _userVO.getHospitalCode());
					
					populateMAP.put(sq.next(), patVO.getPatCRNo());
					 
				}
				catch (Exception e)
				{
					throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
				}
				
				Inv_PatientAdmissionDtlVO[] dailyPatientVOs = null;
				List<Inv_PatientAdmissionDtlVO> lstPatientAdmissionDetailVO = new ArrayList<Inv_PatientAdmissionDtlVO>();
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
						valueObjects = HelperMethods.populateVOfrmRS(Inv_PatientAdmissionDtlVO.class, rs);
						//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
						for (int i = 0; i < valueObjects.length; i++)
						{
							//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
							lstPatientAdmissionDetailVO.add((Inv_PatientAdmissionDtlVO)valueObjects[i]);
						}
					}
				}
				catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
					//else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
				}
				return lstPatientAdmissionDetailVO;
			}	
			

			public List<Inv_EpisodeVO> getPatientEpisode(EHRSection_InvestigationAdviceVO patVO, UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();
				String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.PATIENT_EPISODE_DTLS.HRGT_EPISODE_DTL";

				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}

				System.out.println("Query ---------> " + query);
				Sequence sq = new Sequence();
				try
				{
					populateMAP.put(sq.next(), _userVO.getHospitalCode());
					 
					populateMAP.put(sq.next(), patVO.getPatCRNo());
					 
				}
				catch (Exception e)
				{
					throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
				}
				
				PatientDetailVO[] dailyPatientVOs = null;
				List<Inv_EpisodeVO> lstPatientDetailVO = new ArrayList<Inv_EpisodeVO>();
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
						valueObjects = HelperMethods.populateVOfrmRS(Inv_EpisodeVO.class, rs);
						//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
						for (int i = 0; i < valueObjects.length; i++)
						{
							//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
							lstPatientDetailVO.add((Inv_EpisodeVO)valueObjects[i]);
						}
					}
				}
				catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
					else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
				}
				return lstPatientDetailVO;
			}

			
			
			public List<BookMarkVO> getBookMarkList( UserVO _userVO)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();
				String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.BOOK_MARK_DTLS.HIVT_BOOKMARK_MST";

				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}

				System.out.println("Query ---------> " + query);
				Sequence sq = new Sequence();
				try
				{
					populateMAP.put(sq.next(), _userVO.getHospitalCode());
					
					populateMAP.put(sq.next(), _userVO.getSeatId());
					
					populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION); //99
					
					//populateMAP.put(sq.next(), _userVO.getUserSeatId());
					
					populateMAP.put(sq.next(), _userVO.getHospitalCode());
					 
					 
				}
				catch (Exception e)
				{
					throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
				}
				
				BookMarkVO[] bookMarkVOs = null;
				List<BookMarkVO> lstBookMarkVO = new ArrayList<BookMarkVO>();
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
						valueObjects = HelperMethods.populateVOfrmRS(BookMarkVO.class, rs);
						//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
						for (int i = 0; i < valueObjects.length; i++)
						{
							//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
							lstBookMarkVO.add((BookMarkVO)valueObjects[i]);
						}
					}
				}
				catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
					else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
				}
				return lstBookMarkVO;
			}
			
			
			public String getAccountNo(EHRSection_InvestigationAdviceVO patVO,UserVO userVO)
			{
				String accountNo=""; 
				try
				{
					Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_GET_ACOOUNT_NO);
					strProc.addInParameter(1,Types.VARCHAR,userVO.getHospitalCode());
					strProc.addInParameter(2,Types.VARCHAR,InvestigationConfig.CERTIFICATE_TYPE_ID_IPD);
					strProc.addInParameter(3,Types.VARCHAR,patVO.getPatCRNo());
					
					strProc.setReturnType(Types.NUMERIC);
					return accountNo =strProc.execute(super.getTransactionContext().getConnection()).toString();
					
				}
				catch (HisRecordNotFoundException e)
				{
					throw new HisRecordNotFoundException("No Record Found");
				}
				
			}
			
			
			public List<BookMarkVO> getBookMarkListDeptUnitWise( UserVO _userVO,String deptUnitCode)
			{
				ResultSet rs = null;
				String query = "";
				Map populateMAP = new HashMap();
				String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.BOOK_MARK_DTLS_DEPT_UNIT_WISE.HIVT_BOOKMARK_MST";

				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}

				System.out.println("Query ---------> " + query);
				Sequence sq = new Sequence();
				try
				{
					populateMAP.put(sq.next(), _userVO.getHospitalCode());
					
					populateMAP.put(sq.next(), deptUnitCode);
					
					populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION); //99
					
				//	populateMAP.put(sq.next(), _userVO.getUserSeatId());
					
					populateMAP.put(sq.next(), _userVO.getHospitalCode());
					 
					 
				}
				catch (Exception e)
				{
					throw new HisApplicationExecutionException("DailyPatientDAO.populateMAP::" + e);
				}
				
				BookMarkVO[] bookMarkVOs = null;
				List<BookMarkVO> lstBookMarkVO = new ArrayList<BookMarkVO>();
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
						valueObjects = HelperMethods.populateVOfrmRS(BookMarkVO.class, rs);
						//dailyPatientVOs = new PatientDetailVO[valueObjects.length];
						for (int i = 0; i < valueObjects.length; i++)
						{
							//dailyPatientVOs[i] = (PatientDetailVO) valueObjects[i];
							lstBookMarkVO.add((BookMarkVO)valueObjects[i]);
						}
					}
				}
				catch (Exception e)
				{
					if (e.getClass() == HisRecordNotFoundException.class)
					{
						throw new HisRecordNotFoundException(e.getMessage());
					}
					else throw new HisDataAccessException("DailyPatientDAO:getPatientsForPoliceExaminationReqt:HelperMethods :: " + e);
				}
				return lstBookMarkVO;
			}
			
			
			
			
			public  List<LabTestVO> searchLabWiseTestDtls(InvestigationSearchVO searchVO ,UserVO _UserVO)
			{
				String query = "";		
				Map populateMAP = new HashMap();
				ResultSet rs = null;
				List<LabTestVO> lstLabVO=null;
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST";
				
				//String queryKey1 = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST";
				Sequence sq = new Sequence();
				
				//String condition1="and EXISTS (SELECT 1 FROM HIVT_LABORATORY_MST where GNUM_LAB_CODE =  a.GNUM_LABCODE and gnum_hospital_Code=a.gnum_hospital_Code and UPPER(GSTR_LAB_NAME)  LIKE '%'||UPPER(TRIM('"+searchVO.getSearchLabName()+"'))||'%' )";
				//String condition2="and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GSTR_TEST_NAME) LIKE '%'||UPPER(TRIM('"+searchVO.getSearchTestName()+"'))||'%')";
				
				String condition1=" and a.GNUM_LABCODE ="+searchVO.getSearchLabName();
				String condition2=" and a.GNUM_TEST_CODE ="+searchVO.getSearchTestName();
				String condition3="and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code ="+searchVO.getSearchTestGroupName()+" AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code)";
				String orderBy=" ORDER BY labname, testname";

				EHRSection_InvestigationAdviceVO invReqRaisingVO;
				 
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
					populateMAP.put(sq.next(), _UserVO.getUserSeatId());
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				 
				
				try
				{
					 
						query = HelperMethodsDAO.getQuery(filename, queryKey);
					 
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				try
				{
					
					if(searchVO.getSearchLabName()!=null&&!searchVO.getSearchLabName().equals(""))
						query=query+condition1;
					if(searchVO.getSearchTestName()!=null&&!searchVO.getSearchTestName().equals(""))
						query=query+condition2;
					if(searchVO.getSearchTestGroupName()!=null&&!searchVO.getSearchTestGroupName().equals(""))
						query=query+condition3;
					
					
					 
						query+=orderBy;
					 
					 
					 
					
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
							populateMAP);
			           
			            if (!rs.next())
			            {
			                throw new HisRecordNotFoundException("No Test / Lab  Found");
			            }
			            else
			            {
			                rs.beforeFirst();
			                lstLabVO=new ArrayList<LabTestVO>();
			                LabTestVO voLabTest=null;
			                while (rs.next()) {
			                	voLabTest=new LabTestVO();
			                    HelperMethods.populateVOfrmRS(voLabTest, rs);
			                    lstLabVO.add(voLabTest);
			                }
			               
			            }
					
				}
				catch (HisRecordNotFoundException e)
				{

					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("" + e);
				}
				return lstLabVO;

			}
			
			
			public List getSampleCombo(String labCode,String testCode,UserVO _UserVO)
			{
				String query="";
				Map populateMap= new HashMap();
				ResultSet rs=null;
				List lstSample=new ArrayList();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey="SELECT.LABTESTSAMPLECOMBO.HIVT_LABTEST_SAMPLE_MST";
				
				Sequence sq= new Sequence();
				Connection conn=super.getTransactionContext().getConnection();
				
				populateMap.put(sq.next(), labCode);
				populateMap.put(sq.next(), testCode);
				populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
					//	throw new HisRecordNotFoundException();
					}
					else
					{
						rs.beforeFirst();
						lstSample=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
				return lstSample;
			}
			
			
			public List getUOMCombo(UserVO _UserVO)
			{
				String query="";
				Map populateMap= new HashMap();
				ResultSet rs=null;
				List uomCombo=new ArrayList();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey="SELECT.UOM_COMBO.HIVT_UOM_MST";
				
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
						uomCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
				return uomCombo;
			}
			
			
			public List getContainerCombo(UserVO _UserVO)
			{
				String query="";
				Map populateMap= new HashMap();
				ResultSet rs=null;
				List containerCombo=new ArrayList();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey="SELECT.CONTAINER_COMBO.HIVT_CONTAINER_MST";
				
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
						containerCombo=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
				return containerCombo;
			}
			
			
			public String generateRequisitionNoSequence(String labCode,UserVO userVO)
			{
				String reqNo=""; 
				String errorMsg="";
				ResultSet rs=null;
				try
				{
					Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_GENERATE_REQUISITIONNO);
					strProc.addInParameter(1,Types.VARCHAR,userVO.getHospitalCode());
					strProc.addInParameter(2,Types.VARCHAR,labCode);
					strProc.setReturnType(Types.VARCHAR);
					return	reqNo = (String)strProc.execute(super.getTransactionContext().getConnection());
					
				}
				catch (HisRecordNotFoundException e)
				{
					throw new HisRecordNotFoundException("No Record Found");
				}
				
			}
			
		
			// insert in requisition header table
			
			public void insertRequisitionHeaderDtl(RequistionHeaderVO voReqHeader, UserVO _userVO)
			{
				try
				{ 
					Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_UPDATE_REQUSITION_HEADER);
					strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.INSERT_REQ_HEADER);
					strProc.addInParameter(2,Types.VARCHAR,voReqHeader.getRequisitionNumber());
					strProc.addInParameter(3,Types.VARCHAR,voReqHeader.getPatCrNo());
					strProc.addInParameter(4,Types.VARCHAR,_userVO.getHospitalCode());
					strProc.addInParameter(5,Types.VARCHAR,voReqHeader.getVisitNo());
					strProc.addInParameter(6,Types.VARCHAR,voReqHeader.getLabCode());
					strProc.addInParameter(7,Types.VARCHAR,voReqHeader.getReqHeaderStatus());
					strProc.addInParameter(8,Types.VARCHAR,voReqHeader.getReqHeaderStatus());
					strProc.addInParameter(9,Types.VARCHAR,_userVO.getSeatId());
					strProc.addInParameter(10,Types.VARCHAR,voReqHeader.getEpisodeCode());
					strProc.addInParameter(11,Types.VARCHAR,voReqHeader.getPatName());
					strProc.addInParameter(12,Types.VARCHAR,voReqHeader.getReqType());
					strProc.addInParameter(13,Types.VARCHAR,voReqHeader.getPatDob());
					strProc.addInParameter(14,Types.VARCHAR,voReqHeader.getVisitDate());
					strProc.addInParameter(15,Types.VARCHAR,voReqHeader.getIsActualDob());
					strProc.addInParameter(16,Types.VARCHAR,voReqHeader.getAdvisedByDocNo());
					strProc.addInParameter(17,Types.VARCHAR,voReqHeader.getGenderCode());
					strProc.addInParameter(18,Types.VARCHAR,voReqHeader.getRequsitionRaisedThrough());
					strProc.addInParameter(19,Types.VARCHAR,voReqHeader.getPatAge());
					strProc.addInParameter(20,Types.VARCHAR,voReqHeader.getPatAdmissionNo());
					strProc.addInParameter(21,Types.VARCHAR,voReqHeader.getPatAddress());
					strProc.addInParameter(22,Types.VARCHAR,voReqHeader.getWardCode());
					strProc.addInParameter(23,Types.VARCHAR,voReqHeader.getMobileNo());
					strProc.addInParameter(24,Types.VARCHAR,voReqHeader.getEmailId());
					strProc.addInParameter(25,Types.VARCHAR,voReqHeader.getRoomCode());
					strProc.addInParameter(26,Types.VARCHAR,voReqHeader.getBedCode());
					strProc.addInParameter(27,Types.VARCHAR,voReqHeader.getMlcNo());
					strProc.addInParameter(28,Types.VARCHAR,voReqHeader.getBedName());
					strProc.addInParameter(29,Types.VARCHAR,voReqHeader.getRoomName());
					strProc.addInParameter(30,Types.VARCHAR,voReqHeader.getWardName());
					strProc.addInParameter(31,Types.VARCHAR,voReqHeader.getDeptName());
					strProc.addInParameter(32,Types.VARCHAR,null);
					strProc.addInParameter(33,Types.VARCHAR,voReqHeader.getDeptUnitName());
					strProc.addInParameter(34,Types.VARCHAR,voReqHeader.getDeptCode());
					strProc.addInParameter(35,Types.VARCHAR,voReqHeader.getDeptUnitCode());
					strProc.addInParameter(36,Types.VARCHAR,voReqHeader.getIsAutomatedRequest());
					strProc.addInParameter(37,Types.VARCHAR,voReqHeader.getAdvisedByDocName());
					strProc.addInParameter(38,Types.VARCHAR,voReqHeader.getRefHospitalCode());
					strProc.addInParameter(39,Types.VARCHAR,"");
					strProc.addInParameter(40,Types.VARCHAR,voReqHeader.getRefLabCode());
					strProc.addInParameter(41,Types.VARCHAR,voReqHeader.getHospOrLabName());
					strProc.addInParameter(42,Types.VARCHAR,voReqHeader.getExtCrNo());
					strProc.addInParameter(43,Types.VARCHAR,voReqHeader.getBillNo());
					strProc.addInParameter(44,Types.VARCHAR,voReqHeader.getDeleteReason());
					strProc.addInParameter(45,Types.VARCHAR,Config.IS_VALID_ACTIVE);
					
					strProc.addInParameter(46,Types.VARCHAR,null);
					strProc.addInParameter(47, Types.VARCHAR,voReqHeader.getPatCatCode());
			strProc.addInParameter(48, Types.VARCHAR,"");
					
					strProc.addInOutParameter(49,Types.VARCHAR,"");
					strProc.execute(super.getTransactionContext().getConnection());
				}
				catch (HisDataAccessException e)
				{
					throw new HisDataAccessException();
				}
				
				
				
			}

			
			public void insertHivtRequsitionTestMandatoryDtl(InvestigationRequistionVO voLabTest, UserVO _userVO)
			{
				
				try
				{ 
					Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_REQ_TEST_MAND_DTL);
					strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.INSERT_REQ_TESTMAND);
					strProc.addInParameter(2,Types.VARCHAR,voLabTest.getStrRequsitionDNo());
					strProc.addInParameter(3,Types.VARCHAR,voLabTest.getStrTestCode());
					strProc.addInParameter(4,Types.VARCHAR,voLabTest.getMandCode());
					strProc.addInParameter(5,Types.VARCHAR,voLabTest.getFinalMandValues());
					strProc.addInParameter(6,Types.VARCHAR,_userVO.getSeatId());
					strProc.addInParameter(7,Types.VARCHAR,_userVO.getHospitalCode());
					strProc.addInParameter(8,Types.VARCHAR,InvestigationConfig.IS_VALID_ACTIVE);
					strProc.addInOutParameter(9,Types.VARCHAR,"");
					strProc.execute(super.getTransactionContext().getConnection());
				}
				catch (HisDataAccessException e)
				{
					throw new HisDataAccessException();
				}
				
				
				
				
			}
			
			
			public InvestigationRequistionVO  ConfirmAppointment(InvestigationRequistionVO searchVO, UserVO _userVO){
				  //PROCEDURE proc_Confirm_appt_save(appointmentno character varying, hospitalcode character varying, seatId character varying, appointmentstatus character varying,OUT err character varying) IS
				  
				  	HisDAO daoObj = null;
					String strProcName = "{call pkg_appointment_Transaction.proc_confirm_appt_save(?::character varying,?::character varying,?::character varying,?::character varying,?)}";
					int nProcIndex = 0;
					int sq=0;
					String strErr = "";
					
					//System.out.println("AppointmentQueueNo="+InvestigationConfig.APPOINTMENT_STATUS_CONFIRMED );
					//System.out.println("apt no="+ searchVO.getAppointmentNo());
					
					try 
					{
					HelperMethods.setNullToEmpty(searchVO);	
					daoObj = new HisDAO("Appointment","AppointmentDAO");
					nProcIndex = daoObj.setProcedure(strProcName);
					daoObj.setProcInValue(nProcIndex, "appointmentno", searchVO.getAppointmentNo()  ,++sq);
					daoObj.setProcInValue(nProcIndex, "hospitalcode", _userVO.getHospitalCode(),++sq);
					daoObj.setProcInValue(nProcIndex, "seatId", _userVO.getSeatId() ,++sq);
					daoObj.setProcInValue(nProcIndex, "appointmentstatus", InvestigationConfig.APPOINTMENT_STATUS_CONFIRMED  ,++sq);
					daoObj.setProcOutValue(nProcIndex, "err", 1 ,++sq);
					daoObj.executeProcedureByPosition(nProcIndex);
					searchVO.setErrorMessage(daoObj.getString(nProcIndex, "err"));
					
					} catch (Exception e) {
						throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e + strErr);
					}
					finally 
					{
						if (daoObj != null) 
						{
							daoObj.free();
							daoObj = null;
						}
					}
			    	return searchVO;
			    } 
			
			
			public String makeBillingTestWise(InvestigationRequisitionBillDtlVO voBillingDtl,EHRSection_InvestigationAdviceVO patVO,String tariffdetails,String tariffQty,String testGroupVal,UserVO userVO)
			{
				String errorMsg = "";
				String billNo="";
				HisDAO hisDAO_p=new HisDAO("New Investigation", "InvestigationBillingDAO");
				final String strProcName = InvestigationConfig.PROCEDURE_UPDATE_BILLING;
				final int nProcedureIndex;
				final String strDbErr;
				String ageUnit="";
				String patAge = "";
				
				Connection conn = super.getTransactionContext().getConnection();
				try
				{
					
					
					patVO.setPatAddress(patVO.getPatAddress().replaceAll("\\s+",""));
					patVO.setPatAge(patVO.getPatAge().replaceAll("\\s+",""));
					//patVO.setPatAge(patVO.getPatAge().replace("Yr",""));

					if(patVO.getPatAddress().equals(""))
						patVO.setPatAddress("0  ");
					if(patVO.getPatAge().equals(""))
						patVO.setPatAge("0  ");
					
					
					if(patVO.getPatAge() != null && !patVO.getPatAge().equals("")){
						
						patAge = patVO.getPatAge().split("\\s+")[0];
						
						//System.out.println("patAge : "+patAge);
						
						if(patAge.contains("Yr")){
							ageUnit = "1";
							patAge = patAge.substring(0, (patAge.length()-2));
						}else if(patAge.contains("Mth")){
							ageUnit = "2";
							patAge = patAge.substring(0, (patAge.length()-3));
						}else if(patAge.contains("Wk")){
							ageUnit = "3";
							patAge = patAge.substring(0, (patAge.length()-2));
						}else if(patAge.contains("D")){
							ageUnit = "4";
							patAge = patAge.substring(0, (patAge.length()-1));
						}
							
					}
					
					//System.out.println("patAge : "+patAge+ " : ageUnit : "+ageUnit);
					//System.out.println("department code................"+voBillingDtl.getDeptCode());
					
					nProcedureIndex = hisDAO_p.setProcedure(strProcName);

					hisDAO_p.setProcInValue(nProcedureIndex, "modval","1",1);
					hisDAO_p.setProcInValue(nProcedureIndex, "gnum_dept_code",voBillingDtl.getDeptCode(),2);
					hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_chargetype_id",voBillingDtl.getRequisitionType(),3);
					hisDAO_p.setProcInValue(nProcedureIndex, "sblnum_service_id",testGroupVal,4);
					hisDAO_p.setProcInValue(nProcedureIndex, "gstr_req_no",voBillingDtl.getRequisitionNos(),5);
					hisDAO_p.setProcInValue(nProcedureIndex, "gnum_treatment_cat",patVO.getPatCategoryCode(),6);
					hisDAO_p.setProcInValue(nProcedureIndex, "hrgnum_episode_code", patVO.getPatepisodecode(),7);
					hisDAO_p.setProcInValue(nProcedureIndex, "hrgnum_puk",patVO.getPatCRNo(),8);
					hisDAO_p.setProcInValue(nProcedureIndex, "gstr_tariff",tariffdetails,9);
					hisDAO_p.setProcInValue(nProcedureIndex, "gstr_reqqty",tariffQty,10);			
					hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_patient_name",patVO.getPatFirstName()==null?"":patVO.getPatFirstName()+" "+patVO.getPatMiddleName()==null?"":patVO.getPatMiddleName(),11);
					hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_pat_address",patVO.getPatAddress()==null?"":patVO.getPatAddress().split("\\s+")[0],12);  // For Temp Purpose.. need to change after
					hisDAO_p.setProcInValue(nProcedureIndex, "hblstr_contact_no",patVO.getPatMobileNo()==null?"0":patVO.getPatMobileNo(),13);
					//hisDAO_p.setProcInValue(nProcedureIndex, "age",patVO.getPatAge()==null?"0":patVO.getPatAge().split("\\s+")[0],14);
					//hisDAO_p.setProcInValue(nProcedureIndex, "ageunit","",15);
					
					hisDAO_p.setProcInValue(nProcedureIndex, "age",patVO.getPatAge()==null?"0":patAge,14);
					hisDAO_p.setProcInValue(nProcedureIndex, "ageunit",ageUnit,15);
					hisDAO_p.setProcInValue(nProcedureIndex, "gender",patVO.getPatGenderCode(),16);
					hisDAO_p.setProcInValue(nProcedureIndex, "refdoctor",(patVO.getConsultantName()==null?"":patVO.getConsultantName()),17);
					hisDAO_p.setProcInValue(nProcedureIndex, "refdoccontactno","",18);
					hisDAO_p.setProcInValue(nProcedureIndex, "gnum_seatid",userVO.getSeatId(),19);
					hisDAO_p.setProcInValue(nProcedureIndex, "hosp_code",userVO.getHospitalCode(),20);
					hisDAO_p.setProcInValue(nProcedureIndex, "ward_code",(patVO.getPatwardcode()==null?"":patVO.getPatwardcode()),21);
					hisDAO_p.setProcInValue(nProcedureIndex, "admno",(patVO.getPatadmissionno()==null?"":patVO.getPatadmissionno()),22);
					hisDAO_p.setProcInValue(nProcedureIndex, "visitno",(patVO.getPatVisitNo()==null?"":patVO.getPatVisitNo()),23);
					

					hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,24); 

					hisDAO_p.executeProcedureByPosition(nProcedureIndex);

					/* Getting out parameters */
					strDbErr = hisDAO_p.getString(nProcedureIndex, "err");
					//System.out.println("Billing Done Successussfully"+strDbErr);

					/* If Database Error Occurs, No farther processing is required. */
					if (strDbErr != null && !strDbErr.equals("")) {
						throw new Exception("Data Base Error:" + strDbErr);
					}

				}

				catch (Exception e)
				{
					e.printStackTrace();
					//System.out.println(e.getMessage());
					throw new HisDataAccessException(e.getMessage());
				}
				return billNo;
			}
			
			// insert in requisition dtl table
			
			public void insertRequisitionDtl(InvestigationRequistionVO voLabTest, UserVO _userVO)
			{
				String sampleNo=""; 
				String errorMsg="";
				ResultSet rs=null;
				try
				{
					Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_REQUSITION_DTL);
					strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.INSERT_REQ_DTL);
					strProc.addInParameter(2,Types.VARCHAR,_userVO.getHospitalCode());
					strProc.addInParameter(3,Types.VARCHAR,voLabTest.getStrRequsitionDNo());
					strProc.addInParameter(4,Types.VARCHAR,voLabTest.getStrIsAppointment()==null?"0":voLabTest.getStrIsAppointment());
					strProc.addInParameter(5,Types.VARCHAR,voLabTest.getStrLabCode());
					strProc.addInParameter(6,Types.VARCHAR,voLabTest.getStrTestCode());
					strProc.addInParameter(7,Types.VARCHAR,voLabTest.getStrIsConfidential()==null?"0":voLabTest.getStrIsConfidential());
					strProc.addInParameter(8,Types.VARCHAR,voLabTest.getStrPriority());
					strProc.addInParameter(9,Types.VARCHAR,null);
					strProc.addInParameter(10,Types.VARCHAR,voLabTest.getStrRequisitionDtlStatus());
					strProc.addInParameter(11,Types.VARCHAR,_userVO.getSeatId());
					strProc.addInParameter(12,Types.VARCHAR,voLabTest.getAppointmentRefNo());
					strProc.addInParameter(13,Types.VARCHAR,null);
					strProc.addInParameter(14,Types.VARCHAR,voLabTest.getStrTestGroupCode());
					strProc.addInParameter(15,Types.VARCHAR,voLabTest.getStrTestGroupType());
					strProc.addInParameter(16,Types.VARCHAR,null);
					strProc.addInParameter(17,Types.VARCHAR,null);
					strProc.addInParameter(18,Types.VARCHAR,null);
					strProc.addInParameter(19,Types.VARCHAR,null);
					strProc.addInParameter(20,Types.VARCHAR,voLabTest.getStrSampleCode());
					strProc.addInParameter(21,Types.VARCHAR,null);
					strProc.addInParameter(22,Types.VARCHAR,null);
					strProc.addInParameter(23,Types.VARCHAR,null);
					strProc.addInParameter(24,Types.VARCHAR,null);
					strProc.addInParameter(25,Types.VARCHAR,null);
					strProc.addInParameter(26,Types.VARCHAR,null);
					strProc.addInParameter(27,Types.VARCHAR,voLabTest.getStrWorkOrderSequence());
					strProc.addInParameter(28,Types.VARCHAR,null);
					strProc.addInParameter(29,Types.VARCHAR,null);
					strProc.addInParameter(30,Types.VARCHAR,null);
					strProc.addInParameter(31,Types.VARCHAR,voLabTest.getStrTypeOfComponent());
					strProc.addInParameter(32,Types.VARCHAR,voLabTest.getStrReqNo());
					strProc.addInParameter(33,Types.VARCHAR,null);
					strProc.addInParameter(34,Types.VARCHAR,null);
					strProc.addInParameter(35,Types.VARCHAR,null);
					strProc.addInParameter(36,Types.VARCHAR,null);
					strProc.addInParameter(37,Types.VARCHAR,null);
					strProc.addInParameter(38,Types.VARCHAR,null);
					strProc.addInParameter(39,Types.VARCHAR,voLabTest.getStrAppointmentTime());
					strProc.addInParameter(40,Types.VARCHAR,null);
					strProc.addInParameter(41,Types.VARCHAR,null);
					strProc.addInParameter(42,Types.VARCHAR,null);
					strProc.addInParameter(43,Types.VARCHAR,null);
					strProc.addInParameter(44,Types.VARCHAR,null);
					strProc.addInParameter(45,Types.VARCHAR,null);
					strProc.addInParameter(46,Types.VARCHAR,voLabTest.getStrAppointmentDate());
					strProc.addInParameter(47,Types.VARCHAR,"1"); //priority all
					strProc.addInOutParameter(48,Types.VARCHAR ,"");
					strProc.execute(super.getTransactionContext().getConnection());
					
				}
				catch (HisRecordNotFoundException e)
				{
					throw new HisRecordNotFoundException("No Record Found");
				}
				
				
				
			}
			

			
			public  List<LabTestVO> searchTestGroup(InvestigationSearchVO searchVO ,UserVO _UserVO)
			{
				String query = "";		
				Map populateMAP = new HashMap();
				ResultSet rs = null;
				List<LabTestVO> lstLabVO=null;
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST";
				Sequence sq = new Sequence();
				
				String condition1="and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code ="+searchVO.getSearchTestGroupName()+" AND gnum_lab_code="+searchVO.getSearchLabName()+" AND gnum_hospital_Code=a.gnum_hospital_Code)";
				String condition2="and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code ="+searchVO.getSearchTestGroupName()+" AND gnum_hospital_Code=a.gnum_hospital_Code)";
				//String condition2="and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GSTR_TEST_NAME) LIKE '%'||UPPER(TRIM('"+searchVO.getSearchTestName()+"'))||'%')";
				String orderBy=" ORDER BY labname, testname";

				EHRSection_InvestigationAdviceVO invReqRaisingVO;
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
				populateMAP.put(sq.next(), _UserVO.getUserSeatId());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				try
				{
					
					if(searchVO.getSearchTestGroupName()!=null&&!searchVO.getSearchTestGroupName().equals("") && searchVO.getSearchLabName()!=null&&!searchVO.getSearchLabName().equals(""))
						query=query+condition1;
					if(searchVO.getSearchTestGroupName()!=null&&!searchVO.getSearchTestGroupName().equals(""))
						query=query+condition2;
					query+=orderBy;
					
						rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
							populateMAP);
			           
			            if (!rs.next())
			            {
			                throw new HisRecordNotFoundException("No Test / Lab  Found");
			            }
			            else
			            {
			                rs.beforeFirst();
			                lstLabVO=new ArrayList<LabTestVO>();
			                LabTestVO voLabTest=null;
			                while (rs.next()) {
			                	voLabTest=new LabTestVO();
			                    HelperMethods.populateVOfrmRS(voLabTest, rs);
			                    lstLabVO.add(voLabTest);
			                }
			               
			            }
					
				}
				catch (HisRecordNotFoundException e)
				{

					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("" + e);
				}
				return lstLabVO;

			}
			
	
			
			public  List<LabTestVO> getTestsUsingGroup(InvestigationSearchVO searchVO ,UserVO _UserVO)
			{
				String query = "";		
				Map populateMAP = new HashMap();
				ResultSet rs = null;
				List<LabTestVO> lstLabVO=null;
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST";
				Sequence sq = new Sequence();
				
				String condition1="and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code ="+searchVO.getSearchTestGroupName()+" AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code)";
				//String condition2="and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GSTR_TEST_NAME) LIKE '%'||UPPER(TRIM('"+searchVO.getSearchTestName()+"'))||'%')";
				String orderBy=" ORDER BY labname, testname";

				EHRSection_InvestigationAdviceVO invReqRaisingVO;
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
				populateMAP.put(sq.next(), _UserVO.getUserSeatId());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				try
				{
					
						query=query+condition1+orderBy;
						rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
							populateMAP);
			           
			            if (!rs.next())
			            {
			                throw new HisRecordNotFoundException("No Test / Lab  Found");
			            }
			            else
			            {
			                rs.beforeFirst();
			                lstLabVO=new ArrayList<LabTestVO>();
			                LabTestVO voLabTest=null;
			                while (rs.next()) {
			                	voLabTest=new LabTestVO();
			                    HelperMethods.populateVOfrmRS(voLabTest, rs);
			                    lstLabVO.add(voLabTest);
			                }
			               
			            }
					
				}
				catch (HisRecordNotFoundException e)
				{

					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("" + e);
				}
				return lstLabVO;

			}
			
			public  List<LabTestVO> searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO ,UserVO _UserVO)
			{
				String query = "";		
				Map populateMAP = new HashMap();
				ResultSet rs = null;
				List<LabTestVO> lstLabVO=null;
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST";
				
				//String queryKey1 = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST";
				Sequence sq = new Sequence();
				
				//String condition1="and EXISTS (SELECT 1 FROM HIVT_LABORATORY_MST where GNUM_LAB_CODE =  a.GNUM_LABCODE and gnum_hospital_Code=a.gnum_hospital_Code and UPPER(GSTR_LAB_NAME)  LIKE '%'||UPPER(TRIM('"+searchVO.getSearchLabName()+"'))||'%' )";
				//String condition2="and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GSTR_TEST_NAME) LIKE '%'||UPPER(TRIM('"+searchVO.getSearchTestName()+"'))||'%')";
				
				 

				EHRSection_InvestigationAdviceVO invReqRaisingVO;
				 
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
					populateMAP.put(sq.next(), _UserVO.getUserSeatId());
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				 
				
				try
				{
					 
						query = HelperMethodsDAO.getQuery(filename, queryKey);
					 
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				try
				{
					
					 
					
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
							populateMAP);
			           
			            if (!rs.next())
			            {
			                throw new HisRecordNotFoundException("No Test / Lab  Found");
			            }
			            else
			            {
			                rs.beforeFirst();
			                lstLabVO=new ArrayList<LabTestVO>();
			                LabTestVO voLabTest=null;
			                while (rs.next()) {
			                	voLabTest=new LabTestVO();
			                    HelperMethods.populateVOfrmRS(voLabTest, rs);
			                    lstLabVO.add(voLabTest);
			                }
			               
			            }
					
				}
				catch (HisRecordNotFoundException e)
				{

					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("" + e);
				}
				return lstLabVO;

			}
			
			public String getBookMarkType( String bookCode,UserVO _UserVO)
			{
				String query = "";
				ResultSet rs;
				Map populateMAP = new HashMap();
				Sequence sq = new Sequence();
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey ="SELECT.BOOKMARK_TYPE.BookMark_mst";


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
					populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
					populateMAP.put(sq.next(),bookCode);
					populateMAP.put(sq.next(),_UserVO.getHospitalCode());
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
			
			
			
			public  List<LabTestVO> searchBookMark(InvestigationSearchVO searchVO ,UserVO _UserVO,String bookType)
			{
				String query = "";		
				Map populateMAP = new HashMap();
				ResultSet rs = null;
				List<LabTestVO> lstLabVO=null;
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST";
				Sequence sq = new Sequence();
				
				String condition1="and EXISTS (SELECT 1 FROM HIVT_BOOKMARK_MST where ";// gnum_bookmark_code ="+searchVO.getBookMarkCode() AND;
				String condition3="gnum_isvalid=1 and gnum_bookmark_code ="+searchVO.getBookMarkCode()+" AND";
				String condition2="";
				if(bookType!=null && bookType.equals("2"))
				 condition2="  hivtnum_lab_code=a.gnum_labcode AND hivtnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code  and gnum_dept_unit_code="+searchVO.getDeptUnitCode()+")";
				else
				condition2="  hivtnum_lab_code=a.gnum_labcode AND hivtnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code  and gnum_user_id="+_UserVO.getSeatId()+")";
				
				String condition4="and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GNUM_TEST_CODE) LIKE '%'||UPPER(TRIM('"+searchVO.getSearchTestName()+"'))||'%')";
				String condition5="   AND EXISTS (SELECT 1 FROM hivt_test_mst WHERE gnum_test_code = a.gnum_test_code AND  gnum_test_code in("+searchVO.getTestCode()+"0))";

				String orderBy=" ORDER BY labname, testname";

				EHRSection_InvestigationAdviceVO invReqRaisingVO;
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
				populateMAP.put(sq.next(), _UserVO.getUserSeatId());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				try
				{
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				try
				{
					if(searchVO.getBookMarkCode()!=null&&!searchVO.getBookMarkCode().equals(""))
					{
						query=query+condition1+condition3+condition2+orderBy;
					}
					
					  if(searchVO.getSearchTestName()!=null&&!searchVO.getSearchTestName().equals(""))
					{
						  query=query+condition4+orderBy;
					}
					
					 if(searchVO.getTestCode()!=null&&!searchVO.getTestCode().equals(""))
					{
						query=query+condition5+orderBy;
					}
					 
						rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
							populateMAP);
			           
			            if (!rs.next())
			            {
			              //  throw new HisRecordNotFoundException("No Test / Lab  Found");
			            }
			            else
			            {
			                rs.beforeFirst();
			                lstLabVO=new ArrayList<LabTestVO>();
			                LabTestVO voLabTest=null;
			                while (rs.next()) {
			                	voLabTest=new LabTestVO();
			                    HelperMethods.populateVOfrmRS(voLabTest, rs);
			                    lstLabVO.add(voLabTest);
			                }
			               
			            }
					
				}
				catch (HisRecordNotFoundException e)
				{

					//throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("" + e);
				}
				return lstLabVO;

			}
			
			
			public  List<LabTestVO> searchLaboratoryWiseTestGroupOnClickCumColl(InvestigationSearchVO searchVO ,UserVO _UserVO)
			{
				String query = "";		
				Map populateMAP = new HashMap();
				ResultSet rs = null;
				List<LabTestVO> lstLabVO=null;
				String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
				String queryKey = "SELECT.LAB_WISE_TEST_GROUP_TEST_CUM_COLL.HIVT_TEST_MST";
				
				//String queryKey1 = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST";
				Sequence sq = new Sequence();
				
				//String condition1="and EXISTS (SELECT 1 FROM HIVT_LABORATORY_MST where GNUM_LAB_CODE =  a.GNUM_LABCODE and gnum_hospital_Code=a.gnum_hospital_Code and UPPER(GSTR_LAB_NAME)  LIKE '%'||UPPER(TRIM('"+searchVO.getSearchLabName()+"'))||'%' )";
				//String condition2="and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GSTR_TEST_NAME) LIKE '%'||UPPER(TRIM('"+searchVO.getSearchTestName()+"'))||'%')";
				
				 

				EHRSection_InvestigationAdviceVO invReqRaisingVO;
				 
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
					populateMAP.put(sq.next(), _UserVO.getUserSeatId());
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
					populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				 
				
				try
				{
					 
						query = HelperMethodsDAO.getQuery(filename, queryKey);
					 
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
				}
				try
				{
					
					 
					
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
							populateMAP);
			           
			            if (!rs.next())
			            {
			                throw new HisRecordNotFoundException("No Test / Lab  Found");
			            }
			            else
			            {
			                rs.beforeFirst();
			                lstLabVO=new ArrayList<LabTestVO>();
			                LabTestVO voLabTest=null;
			                while (rs.next()) {
			                	voLabTest=new LabTestVO();
			                    HelperMethods.populateVOfrmRS(voLabTest, rs);
			                    lstLabVO.add(voLabTest);
			                }
			               
			            }
					
				}
				catch (HisRecordNotFoundException e)
				{

					throw new HisRecordNotFoundException(e.getMessage());
				}
				catch (Exception e)
				{
					throw new HisDataAccessException("" + e);
				}
				return lstLabVO;

			}


	


	/*public String generateRequisitionNoSequence(String labCode,UserVO userVO)
	{
		String sequence_Hash_yyymmdd=""; 
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey="SELECT.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";
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
			populateMap.put(sq.next(), labCode);
			populateMap.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodBankEssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}
			else
			{
				sequence_Hash_yyymmdd=rs.getString(1)+"#"+rs.getString(2);
				
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
		return sequence_Hash_yyymmdd;
	}
	*/
	
	public void insertRequisitionSequenceInMaintainer(String labCode,String sequence,String yymmdd, UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="INSERT.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";

		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);

		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		try {
			
			populateMAP.put(sq.next(),labCode);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),sequence);
			populateMAP.put(sq.next(),yymmdd);
			
        	
            	
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"IcdGroupMasterDAO.populateMAP::" + e);
		}
		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			throw new HisDataAccessException("Exception While insertion in SYS_REQUISITION_MAINTAINER Table");
		}

	}
	
	public void updateRequisitionSequenceInMaintainer(String sequence,String labcCode,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
		String queryKey ="UPDATE.REQUISTIONNO.SYS_REQUISITION_MAINTAINER";
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
			populateMAP.put(sq.next(),sequence);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), labcCode);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			//System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	// insert in requisition header table
	
	
	/*	public void insertRequisitionDtl(InvestigationRequistionVO voLabTest, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.HIVT_REQUISITION_DTL";

			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				populateMAP.put(sq.next(),_userVO.getHospitalCode()); //1
				
				populateMAP.put(sq.next(),voLabTest.getStrRequsitionDNo()); //2
				
				populateMAP.put(sq.next(),(voLabTest.getStrIsAppointment()==null?"0":voLabTest.getStrIsAppointment())); //3
				
				populateMAP.put(sq.next(),voLabTest.getStrLabCode());  //4
				
				populateMAP.put(sq.next(),voLabTest.getStrTestCode());  //5
				
				populateMAP.put(sq.next(),(voLabTest.getStrIsConfidential()==null?"0":voLabTest.getStrIsConfidential()));//6
				
				//populateMAP.put(sq.next(),voLabTest.getStrResultDate());//7
				
				populateMAP.put(sq.next(),voLabTest.getStrSampleCode());//7-- should be 8
				
			//	populateMAP.put(sq.next(),voLabTest.getStrResultDate());//9
				
				//populateMAP.put(sq.next(),voLabTest.getStrSampleNo());//9
				
				populateMAP.put(sq.next(),_userVO.getSeatId());//8
				
				populateMAP.put(sq.next(),voLabTest.getStrReqNo());//9
				
				populateMAP.put(sq.next(),voLabTest.getStrWorkOrderSequence());//10
				
				populateMAP.put(sq.next(),voLabTest.getStrTypeOfComponent());//11
				
				populateMAP.put(sq.next(),voLabTest.getStrRequisitionDtlStatus());//12
				
				populateMAP.put(sq.next(),voLabTest.getStrPriority());//13
				
				populateMAP.put(sq.next(),voLabTest.getStrTestGroupCode());//14
				
				populateMAP.put(sq.next(),voLabTest.getStrTestGroupType());//15
				
				populateMAP.put(sq.next(),voLabTest.getStrAppointmentDate());//16
				
				populateMAP.put(sq.next(),voLabTest.getStrAppointmentTime());//17
				
				 
				
	            	
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in HIVT_REQUISITION_DTL Table");
			}

		}*/

		public List getTestNamesUsingAJAX(String labCode,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> lstTestNames=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.TEST_COMBO_NAMES_AJAX.HIVT_TEST_MST";
 			
			String condition1="and gnum_labcode ="+labCode+" order by testName ";
		 

			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
			//populateMap.put(sq.next(), labCode);
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
				if(!labCode.equals(""))
					query+=condition1;
				
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
				//	throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					if(rs.next())
					{
						lstTestNames=HelperMethodsDAO.getAlOfEntryObjects(rs);
 					}
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
			return lstTestNames;
		}

		public List getTestGroupUsingAJAX(String labCode,UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> lstTestNames=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.TEST_GROUP_NAMES_AJAX.HIVT_TEST_GROUP_MST";
			
			String condition1="AND gnum_lab_code="+labCode+" ";
			String condition2=") ORDER BY testgroupname";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
			//populateMap.put(sq.next(), labCode);
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
			if(!labCode.equals(""))
			{
				
			
				query+=condition1+condition2;
			}
			else
			{

				query+=condition2;
			}
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				if (!rs.next())
				{
				//	throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					if(rs.next())
					{
						lstTestNames=HelperMethodsDAO.getAlOfEntryObjects(rs);
 					}
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
			return lstTestNames;
		}
		
		//Appointment Date will update in requisition header
		
		public void updateRequisitionHeader(String requisitionNumber, String appoitmentDate, UserVO _userVO)
		{
			try
			{ 
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_UPDATE_REQUSITION_HEADER);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.UPDATE_APPOINTMENT_DATE);
				strProc.addInParameter(2,Types.VARCHAR,requisitionNumber);
				strProc.addInParameter(3,Types.VARCHAR,null);
				strProc.addInParameter(4,Types.VARCHAR,null);
				strProc.addInParameter(5,Types.VARCHAR,null);
				strProc.addInParameter(6,Types.VARCHAR,null);
				strProc.addInParameter(7,Types.VARCHAR,null);
				strProc.addInParameter(8,Types.VARCHAR,null);
				strProc.addInParameter(9,Types.VARCHAR,null);
				strProc.addInParameter(10,Types.VARCHAR,null);
				strProc.addInParameter(11,Types.VARCHAR,null);
				strProc.addInParameter(12,Types.VARCHAR,null);
				strProc.addInParameter(13,Types.VARCHAR,null);
				strProc.addInParameter(14,Types.VARCHAR,null);
				strProc.addInParameter(15,Types.VARCHAR,null);
				strProc.addInParameter(16,Types.VARCHAR,null);
				strProc.addInParameter(17,Types.VARCHAR,null);
				strProc.addInParameter(18,Types.VARCHAR,null);
				strProc.addInParameter(19,Types.VARCHAR,null);
				strProc.addInParameter(20,Types.VARCHAR,null);
				strProc.addInParameter(21,Types.VARCHAR,null);
				strProc.addInParameter(22,Types.VARCHAR,null);
				strProc.addInParameter(23,Types.VARCHAR,null);
				strProc.addInParameter(24,Types.VARCHAR,null);
				strProc.addInParameter(25,Types.VARCHAR,null);
				strProc.addInParameter(26,Types.VARCHAR,null);
				strProc.addInParameter(27,Types.VARCHAR,null);
				strProc.addInParameter(28,Types.VARCHAR,null);
				strProc.addInParameter(29,Types.VARCHAR,null);
				strProc.addInParameter(30,Types.VARCHAR,null);
				strProc.addInParameter(31,Types.VARCHAR,null);
				strProc.addInParameter(32,Types.VARCHAR,null);
				strProc.addInParameter(33,Types.VARCHAR,null);
				strProc.addInParameter(34,Types.VARCHAR,null);
				strProc.addInParameter(35,Types.VARCHAR,null);
				strProc.addInParameter(36,Types.VARCHAR,null);
				strProc.addInParameter(37,Types.VARCHAR,null);
				strProc.addInParameter(38,Types.VARCHAR,null);
				strProc.addInParameter(39,Types.VARCHAR,null);
				strProc.addInParameter(40,Types.VARCHAR,null);
				strProc.addInParameter(41,Types.VARCHAR,null);
				strProc.addInParameter(42,Types.VARCHAR,null);
				strProc.addInParameter(43,Types.VARCHAR,null);
				strProc.addInParameter(44,Types.VARCHAR,null);
				strProc.addInParameter(45,Types.VARCHAR,null);
				strProc.addInParameter(46,Types.VARCHAR,appoitmentDate);
				strProc.addInParameter(47, Types.VARCHAR,null);
				strProc.addInParameter(48,Types.VARCHAR,null);
				strProc.addInOutParameter(49,Types.VARCHAR,"");
				strProc.execute(super.getTransactionContext().getConnection());
			}
			catch (HisDataAccessException e)
			{
				throw new HisDataAccessException();
			}
			
			
			
			
			
		}
		
		/*public void updateRequisitionHeader(String requisitionNumber, String appoitmentDate, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.APPOITMENT.IN.REQUISITIONHEADER";
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
				populateMAP.put(sq.next(),appoitmentDate);
				 populateMAP.put(sq.next(), requisitionNumber);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				//System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		*/
		
	 
	
		
		
		public  List<LabTestVO>  getPrvTestDtlUsingAJAX(String CrNo,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			List<LabTestVO> lstLabVO=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.PRV_TEST_DETAIL";
			Sequence sq = new Sequence();
			
			 

			EHRSection_InvestigationAdviceVO invReqRaisingVO;
			populateMAP.put(sq.next(),CrNo);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(),CrNo);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				
				 
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
						populateMAP);
		           
		            if (!rs.next())
		            {
		                throw new HisRecordNotFoundException("No Test / Lab  Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		                lstLabVO=new ArrayList<LabTestVO>();
		                LabTestVO voLabTest=null;
		                while (rs.next()) {
		                	voLabTest=new LabTestVO();
		                    HelperMethods.populateVOfrmRS(voLabTest, rs);
		                    lstLabVO.add(voLabTest);
		                }
		               
		            }
				
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstLabVO;

		}
		
		
		
		public  List<LabTestVO> getAptBasedTest(InvestigationSearchVO searchVO ,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			List<LabTestVO> lstLabVO=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.APT_BASED_DETAIL.HAPT_APPOINTMENT_DTL";
			Sequence sq = new Sequence();
			
			 
			EHRSection_InvestigationAdviceVO invReqRaisingVO;
			populateMAP.put(sq.next(), InvestigationConfig.APTID_FOR_INVESTIGATION);
			//populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				 
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,populateMAP);
		           
		           // if (!rs.next())
		           // {
		               // throw new HisRecordNotFoundException("No Appoitment Based Details Found");
		           // }
		           // else
		            //{
		                rs.beforeFirst();
		                lstLabVO=new ArrayList<LabTestVO>();
		                LabTestVO voLabTest=null;
		                while (rs.next()) {
		                	voLabTest=new LabTestVO();
		                    HelperMethods.populateVOfrmRS(voLabTest, rs);
		                    lstLabVO.add(voLabTest);
		              //  }
		               
		            }
				
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstLabVO;

		}
			
		
		
			/*	public void insertHivtRequsitionTestMandatoryDtl(InvestigationRequistionVO voLabTest, UserVO _userVO) {

			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="INSERT.HIVT_REQUSITION_TEST_MANDATORY_DTL";
			 
			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);

			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
								+ e);
			}

			try {
				
				
				 

				
			 
				populateMAP.put(sq.next(),voLabTest.getStrRequsitionDNo());
				
				populateMAP.put(sq.next(),voLabTest.getStrTestCode()); 
				
				populateMAP.put(sq.next(),voLabTest.getMandCode()); 
				
				populateMAP.put(sq.next(),voLabTest.getFinalMandValues());//2IS_VALID_ACTIVE
				
				populateMAP.put(sq.next(),_userVO.getSeatId());//8
				
				populateMAP.put(sq.next(),_userVO.getHospitalCode()); //1
				
				populateMAP.put(sq.next(),InvestigationConfig.IS_VALID_ACTIVE);
				 
				
	            	
			} catch (Exception e) {
				throw new HisApplicationExecutionException(
						"IcdGroupMasterDAO.populateMAP::" + e);
			}
			try {

				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
						.getConnection(), query, populateMAP);
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println(e.getMessage());
				throw new HisDataAccessException("Exception While insertion in HIVT_REQUSITION_TEST_MANDATORY_DTL Table");
			}

		}*/
		
		//req details for raising cum sample collection
		
		
		public void insertRaisingCumCollectionDetails(LabTestVO voLabTest, UserVO _userVO)
		{
			String sampleNo=""; 
			String errorMsg="";
			ResultSet rs=null;
			try
			{
				Procedure strProc=new Procedure(InvestigationConfig.PROCEDURE_INSERT_REQUSITION_DTL);
				strProc.addInParameter(1,Types.VARCHAR,InvestigationConfig.RAISING_CUM_COLLECTION); //MODE VALUE-4
				strProc.addInParameter(2,Types.VARCHAR,_userVO.getHospitalCode());
				strProc.addInParameter(3,Types.VARCHAR,voLabTest.getReqDno());
				strProc.addInParameter(4,Types.VARCHAR,voLabTest.getIsAppointment()==null?"0":voLabTest.getIsAppointment());
				strProc.addInParameter(5,Types.VARCHAR,voLabTest.getLabCode());
				strProc.addInParameter(6,Types.VARCHAR,voLabTest.getTestCode());
				strProc.addInParameter(7,Types.VARCHAR,voLabTest.getIsConfidential()==null?"0":voLabTest.getIsConfidential());
				strProc.addInParameter(8,Types.VARCHAR,voLabTest.getPriority());
				strProc.addInParameter(9,Types.VARCHAR,null);
				strProc.addInParameter(10,Types.VARCHAR,voLabTest.getRequisitionDtlStatus());
				strProc.addInParameter(11,Types.VARCHAR,_userVO.getSeatId());
				strProc.addInParameter(12,Types.VARCHAR,voLabTest.getAppointmentRefNo());
				strProc.addInParameter(13,Types.VARCHAR,voLabTest.getTempSampleNo());
				strProc.addInParameter(14,Types.VARCHAR,voLabTest.getStrTestGroupCode());
				strProc.addInParameter(15,Types.VARCHAR,voLabTest.getTestGroupType());
				strProc.addInParameter(16,Types.VARCHAR,null);
				strProc.addInParameter(17,Types.VARCHAR,voLabTest.getBillNo());																		//bill no
				strProc.addInParameter(18,Types.VARCHAR,null);
				strProc.addInParameter(19,Types.VARCHAR,null);
				strProc.addInParameter(20,Types.VARCHAR,voLabTest.getSampleCode());
				strProc.addInParameter(21,Types.VARCHAR,null);
				strProc.addInParameter(22,Types.VARCHAR,null);
				strProc.addInParameter(23,Types.VARCHAR,null);
				strProc.addInParameter(24,Types.VARCHAR,null);
				strProc.addInParameter(25,Types.VARCHAR,null);
				strProc.addInParameter(26,Types.VARCHAR,null);
				strProc.addInParameter(27,Types.VARCHAR,voLabTest.getWorkingOrderSequence());
				strProc.addInParameter(28,Types.VARCHAR,null);
				strProc.addInParameter(29,Types.VARCHAR,null);
				strProc.addInParameter(30,Types.VARCHAR,null);
				strProc.addInParameter(31,Types.VARCHAR,voLabTest.getTypeOfComponent());
				strProc.addInParameter(32,Types.VARCHAR,voLabTest.getReqNo());
				strProc.addInParameter(33,Types.VARCHAR,InvestigationConfig.SAMPLE_ACCEPTED);
				strProc.addInParameter(34,Types.VARCHAR,null);
				strProc.addInParameter(35,Types.VARCHAR,null);
				strProc.addInParameter(36,Types.VARCHAR,_userVO.getSeatId());
				strProc.addInParameter(37,Types.VARCHAR,_userVO.getSeatId());
				strProc.addInParameter(38,Types.VARCHAR,voLabTest.getSampleAreaCode());
				strProc.addInParameter(39,Types.VARCHAR,voLabTest.getAppointmentTime().substring(0, 4));
				strProc.addInParameter(40,Types.VARCHAR,null);
				strProc.addInParameter(41,Types.VARCHAR,InvestigationConfig.SAMPLE_RECEIVED);
				strProc.addInParameter(42,Types.VARCHAR,voLabTest.getSampleNo());//sample no
				strProc.addInParameter(43,Types.VARCHAR,voLabTest.getUomCode());
				strProc.addInParameter(44,Types.VARCHAR,voLabTest.getContainerVolume());
				strProc.addInParameter(45,Types.VARCHAR,voLabTest.getContainerCode());
				strProc.addInParameter(46,Types.VARCHAR,voLabTest.getAppointmentDate());
				strProc.addInParameter(47,Types.VARCHAR,null);
				strProc.addInOutParameter(48,Types.VARCHAR ,"");
				strProc.execute(super.getTransactionContext().getConnection());
				
			}
			catch (HisRecordNotFoundException e)
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			
			
			
		}
		
		
		
		
	/*	public  LabTestVO getSampleRelatedValues(LabTestVO labVO ,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			LabTestVO voValues=new LabTestVO();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.SAMPLE_RELATED.HIVT_LAB_TEST_MST";
			
			
			Sequence sq = new Sequence();
			
					
				populateMAP.put(sq.next(), );
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				
			 
			
			try
			{
				 
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				 
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
			
				 
				 
				
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
						populateMAP);
		           
		            if (!rs.next())
		            {
		                throw new HisRecordNotFoundException("No Test / Lab  Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		               
		               
		                while (rs.next()) {
		                	
		                    HelperMethods.populateVOfrmRS(voValues, rs);
		                   
		                }
		               
		            }
				
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return voValues;

		}*/

		public  List<LabTestVO> getTestsCodeWiseDtl(InvestigationSearchVO searchVO ,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			List<LabTestVO> lstLabVO=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.LAB_WISE_TEST.HIVT_TEST_MST";
			Sequence sq = new Sequence();
			
			String condition1=" AND UPPER(gnum_user_test_code)=UPPER(TRIM('"+searchVO.getTestCodeWise()+"'))";
			//String condition2="and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GSTR_TEST_NAME) LIKE '%'||UPPER(TRIM('"+searchVO.getSearchTestName()+"'))||'%')";
			String orderBy=" ORDER BY labname, testname";

			EHRSection_InvestigationAdviceVO invReqRaisingVO;
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
			populateMAP.put(sq.next(), _UserVO.getUserSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				
					query=query+condition1+orderBy;
					rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
						populateMAP);
		           
		            if (!rs.next())
		            {
		                throw new HisRecordNotFoundException("No Test / Lab  Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		                lstLabVO=new ArrayList<LabTestVO>();
		                LabTestVO voLabTest=null;
		                while (rs.next()) {
		                	voLabTest=new LabTestVO();
		                    HelperMethods.populateVOfrmRS(voLabTest, rs);
		                    lstLabVO.add(voLabTest);
		                }
		               
		            }
				
			}
			catch (HisRecordNotFoundException e)
			{

				return lstLabVO;
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstLabVO;

		}
		
		
		
		public void deleteReqDtl(InvestigationSearchVO searchVO ,UserVO _UserVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey ="UPDATE.REQUISITIONSTATUS.HIVT_REQUISITION_DTL";
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
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_AFETR_DELETE);
				populateMAP.put(sq.next(),_UserVO.getUserSeatId());
				
				//Where Condiotion
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_WHERE_CONDITION);
				populateMAP.put(sq.next(),InvestigationConfig.UPDATE_REQ_STATUS_FOR_PATIENT_BASED_WHERE_CONDITION);
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), searchVO.getDelLabCode());
				populateMAP.put(sq.next(),searchVO.getDelTestCode());
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("CellGroupingValidationDAO.populateMAP::" + e);
			}
			try
			{
				HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				//System.out.println(e.getMessage());
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		
		public List getreqStatusAJAX(InvestigationSearchVO searchVO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> lstReqStatus=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.TEST.REQSTATUS.HIVT_REQUISITION_DTL";
			
			 
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
			//populateMap.put(sq.next(),_UserVO.getUserSeatId());
			populateMap.put(sq.next(), searchVO.getDupLabCode());
			populateMap.put(sq.next(),searchVO.getDupTestCode());
			
			//populateMap.put(sq.next(), labCode);
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
				//	throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					if(rs.next())
					{
						lstReqStatus=HelperMethodsDAO.getAlOfEntryObjects(rs);
 					}
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
			return lstReqStatus;
		}
			
		
		
		
		
		public boolean checkRequisitionPending(InvestigationSearchVO searchVO, UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			int count=0;
			boolean isTempReqPresent=false;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.REQUISITON.PENDING.DTL.HIVT_REQUISITION_DTL";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), searchVO.getDupLabCode());
			
			populateMap.put(sq.next(), searchVO.getDupTestCode());
			
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
			//populateMap.put(sq.next(), _UserVO.getUserSeatId());
			
			populateMap.put(sq.next(), searchVO.getPatientCrNo());
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
			
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
					//rs.beforeFirst();
					count=rs.getInt(1);
					if(count>0)
						isTempReqPresent=true;
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
			return isTempReqPresent;
		}
		

		
		
			//lab test for raising cum collection
		public  List<LabTestVO> searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO searchVO ,UserVO _UserVO)
		{
			String query = "";		
			Map populateMAP = new HashMap();
			ResultSet rs = null;
			List<LabTestVO> lstLabVO=null;
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey = "SELECT.LAB_WISE_TEST.RAISING_CUM_SELECTION.HIVT_TEST_MST";
			
			//String queryKey1 = "SELECT.LAB_WISE_TEST_GROUP_TEST.HIVT_TEST_MST";
			Sequence sq = new Sequence();
			
			//String condition1="and EXISTS (SELECT 1 FROM HIVT_LABORATORY_MST where GNUM_LAB_CODE =  a.GNUM_LABCODE and gnum_hospital_Code=a.gnum_hospital_Code and UPPER(GSTR_LAB_NAME)  LIKE '%'||UPPER(TRIM('"+searchVO.getSearchLabName()+"'))||'%' )";
			//String condition2="and EXISTS (SELECT 1 from hivt_test_mst where GNUM_TEST_CODE =  a.GNUM_TEST_CODE AND UPPER(GSTR_TEST_NAME) LIKE '%'||UPPER(TRIM('"+searchVO.getSearchTestName()+"'))||'%')";
			
			String condition1=" and a.GNUM_LABCODE ="+searchVO.getSearchLabName();
			String condition2=" and a.GNUM_TEST_CODE ="+searchVO.getSearchTestName();
			String condition3="and EXISTS (SELECT 1 FROM HIVT_TEST_GROUP_INFO_MST where hgnum_group_code ="+searchVO.getSearchTestGroupName()+" AND gnum_lab_code=a.gnum_labcode AND gnum_test_code=a.gnum_test_code and gnum_hospital_Code=a.gnum_hospital_Code)";
			String orderBy=" ORDER BY labname, testname";

			EHRSection_InvestigationAdviceVO invReqRaisingVO;
			 
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), InvestigationConfig.MODULE_ID_INVESTIGATION);
				populateMAP.put(sq.next(), _UserVO.getUserSeatId());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
				populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			 
			
			try
			{
				 
					query = HelperMethodsDAO.getQuery(filename, queryKey);
				 
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			try
			{
				
				if(searchVO.getSearchLabName()!=null&&!searchVO.getSearchLabName().equals(""))
					query=query+condition1;
				if(searchVO.getSearchTestName()!=null&&!searchVO.getSearchTestName().equals(""))
					query=query+condition2;
				if(searchVO.getSearchTestGroupName()!=null&&!searchVO.getSearchTestGroupName().equals(""))
					query=query+condition3;
				
				
				 
					query+=orderBy;
				 
				 
				 
				
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,
						populateMAP);
		           
		            if (!rs.next())
		            {
		                throw new HisRecordNotFoundException("No Test / Lab  Found");
		            }
		            else
		            {
		                rs.beforeFirst();
		                lstLabVO=new ArrayList<LabTestVO>();
		                LabTestVO voLabTest=null;
		                while (rs.next()) {
		                	voLabTest=new LabTestVO();
		                    HelperMethods.populateVOfrmRS(voLabTest, rs);
		                    lstLabVO.add(voLabTest);
		                }
		               
		            }
				
			}
			catch (HisRecordNotFoundException e)
			{

				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("" + e);
			}
			return lstLabVO;

		}
		
		
		
		
		public List getTestNamesCumColl(UserVO _UserVO)
		{
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			List<String> lstTestNames=new ArrayList<String>();
			String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_TRANSACTIONDAO;
			String queryKey="SELECT.TEST_COMBO_NAMES.CUM_COLL.HIVT_TEST_MST";
			
			Sequence sq= new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
			
			populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
				//	throw new HisRecordNotFoundException();
				}
				else
				{
					rs.beforeFirst();
					if(rs.next())
					{
						lstTestNames=HelperMethodsDAO.getAlOfEntryObjects(rs);
 					}
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
			return lstTestNames;
		}
		//Added by Vasu on 03.May.2019
		public List<EHRSection_InvestigationAdviceVO> getInvestigationsList(UserVO userVO, PatientDetailVO selectedPatientVO)
		{
		
			String query = "";
			Map _populateMap = new HashMap();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT_INVESTIGATION.HIVT_REQUISITION_DTL.HIVT_REQUISITION_HEADER_SINGLE_PAGE";
			String admno= "";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			
				if(selectedPatientVO.getAddmissionNo()!=null || !selectedPatientVO.getAddmissionNo().isEmpty() || !selectedPatientVO.getAddmissionNo().equalsIgnoreCase(" "))
				{
			     admno= " and b.hipnum_admno= "+ selectedPatientVO.getAddmissionNo() ;
			     query=query+admno;
				}
			   
				
				String orderBy = " ORDER BY HGNUM_VISITNO DESC";
			
				query= query+orderBy;
				//System.out.println("All VISIT"+"  QUERY= "+query);		
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
			}
			Sequence sq = new Sequence();
			try
			{
				//_populateMap.put(sq.next(), _userVO.getHospitalCode());  // commented on 10.01.2017
				//_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), selectedPatientVO.getPatCrNo());
				//_populateMap.put(sq.next(), voDP.getEpisodeCode());
			}
			catch(Exception e)
			{
				throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
			}
			List<EHRSection_InvestigationAdviceVO> prescriptionDtl = new ArrayList<EHRSection_InvestigationAdviceVO>();
			ValueObject[] valueObjects = null;
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
				/*if (!rs.next())
				{
					throw new HisRecordNotFoundException("No Operation Theatre Detail found");
				}*/
				if(rs.next())
				{
					rs.beforeFirst();
					valueObjects = HelperMethods.populateVOfrmRS(EHRSection_InvestigationAdviceVO.class, rs);
					for (int i = 0; i < valueObjects.length; i++)
						prescriptionDtl.add((EHRSection_InvestigationAdviceVO) valueObjects[i]);
				}
				/*else
				{
					throw new HisRecordNotFoundException("");
				}*/
			}
			catch (Exception e)
			{
				/*if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("PatientProfileDetailDAO:retrieveByCrNo::Episode Details:: " + e);*/
				e.printStackTrace();
			}
			return prescriptionDtl;
		}

		
		
}











