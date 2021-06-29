package medicalboard.masters.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MBCertificateChecklistVO;
import hisglobal.vo.UserVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import medicalboard.MedicalBoardConfig;
//import oracle.jdbc.driver.OracleTypes;
import registration.RegistrationConfig;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.vo.MbCertificateTypeMstVO; 
import hisglobal.vo.MbInvestigationMappingMstVO;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.ValueObject;


public class MbMasterEssentialDAO extends DataAccessObject implements MbMasterEssentialDAOi{
	
	public MbMasterEssentialDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
		
	}

	public List getCertificateCategory(UserVO userVO)
	{
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.CATEGORY_LIST.HMBT_CERTIFICATE_CAT_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	
	
	 public List getBoardTypeList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.BOARDTYPE_LIST.HMBT_CERTIFICATE_CAT_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), MedicalBoardConfig.TABLE_ID_CERTIFICATE_TYPE_MST);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			}
			catch (Exception e)
			{
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	
	
	 public List getDepartmentUnitList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.DEPARTMENTLIST.HGBT_UNIT_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	 
	
	
	 
	 public List getDistrictList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.DISTRICT_LIST.GBLT_DISTRICT_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	 
	 
	 
	 public List getEmpDoctorList(UserVO _userVO)
		{
			String errorMsg = "";
			ResultSet rs = null;
			Connection conn = super.getTransactionContext().getConnection();
			try
			{
				Procedure strProc = new Procedure(MedicalBoardConfig.PROCEDURE_GET_EMPLIST_PROCESSWISE);
				strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(2, Types.VARCHAR, MedicalBoardConfig.PROCESS_ID_DOCTOR_LIST);
				strProc.addInParameter(3, Types.VARCHAR, null);
				strProc.addOutParameter(4, Types.VARCHAR);
				//strProc.addOutParameter(5, OracleTypes.CURSOR);    // by Anant Patel
				strProc.addOutParameter(5, Types.REF);
				strProc.execute(conn);
				errorMsg = (String) strProc.getParameterAt(4);
				rs = (ResultSet) strProc.getParameterAt(5);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			// log.error(query + "\n");
			/*
			 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
			 */

			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
			catch (Exception e)
			{

				throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}

			finally
			{
				if (rs != null)
				{
					try
					{
						rs.close();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
			}

			return alRecord;
		}

	 
	 
	 
	 public List getEmpEscortList(UserVO _userVO)
		{
			String errorMsg = "";
			ResultSet rs = null;
			Connection conn = super.getTransactionContext().getConnection();
			try
			{
				Procedure strProc = new Procedure(MedicalBoardConfig.PROCEDURE_GET_EMPLIST_PROCESSWISE);
				strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
				strProc.addInParameter(2, Types.VARCHAR, MedicalBoardConfig.PROCESS_ID_ESCORT_LIST);
				strProc.addInParameter(3, Types.VARCHAR, null);
				strProc.addOutParameter(4, Types.VARCHAR);
				//strProc.addOutParameter(5, OracleTypes.CURSOR);    // by Anant Patel
				strProc.addOutParameter(5, Types.REF);
				strProc.execute(conn);
				errorMsg = (String) strProc.getParameterAt(4);
				rs = (ResultSet) strProc.getParameterAt(5);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
			}
			// log.error(query + "\n");
			/*
			 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
			 */

			List alRecord = new ArrayList();

			try
			{
				alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
			}
			catch (Exception e)
			{

				throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}

			finally
			{
				if (rs != null)
				{
					try
					{
						rs.close();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
				}
			}

			return alRecord;
		}
	 
	 
	 

	 
	 
	 public List getRollList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT_ROLL.HMBT_ROLE_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}                     
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	
	 
	 
	 
	 
	 
	 public MedicalBoardMasterVO[] getBoardDetail(UserVO _UserVO)
		{
			ValueObject[] vo =
			{};
			MedicalBoardMasterVO[] mBoardMasterVOs = null;
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT_BOARD_LIST.HMBT_BOARD_MST";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException("No records for this Location ");
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = new ArrayList();
			try
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(MedicalBoardMasterVO.class, rs);
				System.out.println("length" + vo.length);
				mBoardMasterVOs = new MedicalBoardMasterVO[vo.length];
				System.out.println("_patientVO.length:: " + mBoardMasterVOs.length);
				for (int i = 0; i < vo.length; i++)
				{
					System.out.println("before casting");
					mBoardMasterVOs[i] = (MedicalBoardMasterVO) vo[i];
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return mBoardMasterVOs;
		}
	 
	 
	 
	 
	 public MbCertificateTypeMstVO[] getCertificateDetail(UserVO _UserVO)
		{
			ValueObject[] vo ={};
			MbCertificateTypeMstVO[] mbCertificateTypeMstVOs = null;
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT_CERTIFICATETYPEID.HMBT_CERTIFICATE_TYPE_MST";
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			 populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if (!rs.next()) throw new HisRecordNotFoundException("No Certificate Type Found");
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = new ArrayList();
			try
			{
				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(MbCertificateTypeMstVO.class, rs);
				System.out.println("length" + vo.length);
				mbCertificateTypeMstVOs = new MbCertificateTypeMstVO[vo.length];
				System.out.println("_patientVO.length:: " + mbCertificateTypeMstVOs.length);
				for (int i = 0; i < vo.length; i++)
				{
					System.out.println("before casting");
					mbCertificateTypeMstVOs[i] = (MbCertificateTypeMstVO) vo[i];
				}
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return mbCertificateTypeMstVOs;

		}
	 
	 
		
	
	  /* **********************************	Certificate checklist master**********************************/
		
	  public List getCertificateTypeList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
			String queryKey = "SELECT.CERTIFICATE_TYPE.HMBT_CERTIFICATE_TYPE_MST";
			List certificateTypeList=new ArrayList();
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			try
			{
				
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			try
			{
				certificateTypeList= HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			
			return certificateTypeList;
		}

	  
	  public List getAddedChecklistToCertificate(String certificateTypeID,UserVO userVO)
	  {
		  ResultSet rs = null;
		  String query = "";
		  Map populateMAP = new HashMap();
		  String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		  String queryKey = "SELECT.CHECKLIST_ADDED.HMBT_CERTIFICATE_CHECKLIST_MST";
		  List checklist=null;
		  MBCertificateChecklistVO checklistVO=null;
		  try
		  {
			  query = HelperMethodsDAO.getQuery(filename, queryKey);
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException(
					  "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		  }
		  System.out.println("query" + query);
		  Sequence sq = new Sequence();
		    //populateMAP.put(sq.next(), userVO.getHospitalCode());
		  populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		  populateMAP.put(sq.next(), certificateTypeID);
		  try
		  {
			  rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			  
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		  }
		  try
		  {
			  if(rs.next())
				  checklist=new ArrayList();
			  rs.beforeFirst();
			  while(rs.next()){
				  checklistVO=new MBCertificateChecklistVO();
				  HelperMethods.populateVOfrmRS(checklistVO, rs);
				  checklist.add(checklistVO);
			  }
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		  }
		  
		  return checklist;
	  }
	  
	  
	  public List getChecklistNotAddedToCertificate(String certificateTypeID,UserVO userVO)
	  {
		  ResultSet rs = null;
		  String query = "";
		  Map populateMAP = new HashMap();
		  String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		  String queryKey = "SELECT.CHECKLIST_NOT_ADDED.HMBT_CHECKLIST_MST";
		  List checklist=null;
		  
		  try
		  {
			  query = HelperMethodsDAO.getQuery(filename, queryKey);
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException(
					  "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		  }
		  System.out.println("query" + query);
		  Sequence sq = new Sequence();
		 // populateMAP.put(sq.next(), userVO.getHospitalCode());
		  populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		  populateMAP.put(sq.next(), certificateTypeID);
		  try
		  {
			  rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			  
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		  }
		  try
		  {
			  if(rs.next())
				  checklist=new ArrayList();
			  rs.beforeFirst();
			  checklist= HelperMethodsDAO.getAlOfEntryObjects(rs);
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		  }
		  
		  return checklist;
	  }
	  
	  
	  public String getCertificateNameByID(String certificateTypeID,UserVO userVO)
	  {
		  ResultSet rs = null;
		  String query = "";
		  Map populateMAP = new HashMap();
		  String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		  String queryKey = "SELECT.CERTIFICATE_TYPE_NAME.HMBT_CERTIFICATE_TYPE_MST";
		  String certificateType="";
		  try
		  {
			  query = HelperMethodsDAO.getQuery(filename, queryKey);
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException(
					  "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		  }
		  System.out.println("query" + query);
		  Sequence sq = new Sequence();
		  populateMAP.put(sq.next(), certificateTypeID);
		  //populateMAP.put(sq.next(), userVO.getHospitalCode()); // By Anant Patel on 16-10-2014
		  populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		  try
		  {
			  rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			  
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		  }
		  try
		  {	  if(rs.next())
			  	certificateType= rs.getString(1);
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		  }
		  
		  return certificateType;
	  }
	  

	  public List getAllDepartmentList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "ESSENTIAL.ALL_DEPT.GBLT_DEPARTMENT_MST";
			List certificateTypeList=new ArrayList();
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			try
			{
				certificateTypeList= HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			
			return certificateTypeList;
		}

	  
	  
	  public List getAllDeptUnitList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "ESSENTIAL.ALL_UNIT.HGBT_UNIT_MST";
			List certificateTypeList=new ArrayList();
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			try
			{
				certificateTypeList= HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			
			return certificateTypeList;
		}

	  
	  public List getSpecialDeptUnitList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "ESSENTIAL.ALL_SPECIAL_UNIT.HGBT_UNIT_MST";
			List certificateTypeList=new ArrayList();
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), RegistrationConfig.UNIT_TYPE_SPECIALITY);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			try
			{
				certificateTypeList= HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			
			return certificateTypeList;
		}

	  public List getIsCompulsoryList(UserVO userVO)
	{
		//implementation is pending data has to be fetch from flag master table
	  	
		List isCompulsoryList=new ArrayList();
		
		String compulsoryArray[]=MedicalBoardConfig.CHECKLIST_COMPULSORY_FLAG_ARRAY;
		Entry entry=null;
		for(int i=0;i<compulsoryArray.length;i++){
			entry=new Entry();
			entry.setLabel(compulsoryArray[i]);
			entry.setValue(String.valueOf(i));
			isCompulsoryList.add(entry);
		}
		
		return isCompulsoryList;
	}
	
	
	 public List getlabTestList(String certificateTypeId,UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
			String queryKey = "SELECT.LABTEST_LIST.HIVT_LABORATORY_TEST_DTL";
			List labTestList=new ArrayList();
			
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), certificateTypeId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			try
			{
				labTestList= HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			
			return labTestList;
		}
	 
	
	 public List getSelLabTestList(String certificateTypeId,UserVO userVO)
	  {
		  ResultSet rs = null;
		  String query = "";
		  Map populateMAP = new HashMap();
		  String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		  String queryKey = "SELECT.SEL_LABTEST_LIST.HIVT_LABORATORY_TEST_DTL";
		  List investMapVOList=null;
		  MbInvestigationMappingMstVO investMappingVO=null;
		  try
		  {
			  query = HelperMethodsDAO.getQuery(filename, queryKey);
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException(
					  "HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		  }
		  System.out.println("query" + query);
		  Sequence sq = new Sequence();
		  
		  populateMAP.put(sq.next(), userVO.getHospitalCode());
		  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		  populateMAP.put(sq.next(), certificateTypeId);
		  
		  try
		  {
			  rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			  
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		  }
		  try
		  {
			  if(rs.next())
				  investMapVOList=new ArrayList();
			  rs.beforeFirst();
			  while(rs.next()){
				  investMappingVO=new MbInvestigationMappingMstVO();
				  HelperMethods.populateVOfrmRS(investMappingVO, rs);
				  investMapVOList.add(investMappingVO);
			  }
		  }
		  catch (Exception e)
		  {
			  throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		  }
		  
		  return investMapVOList;
	  }  
	


 
	 
	 public List getTemplateList(UserVO userVO)
	  {
			ResultSet rs = null;
			String query = "";
			Map populateMAP = new HashMap();
			String filename = MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
			String queryKey = "SELECT.TEMPLATE_LIST.HGBT_TEMPLATE_MST";
			//first call the getQueryMethod with arguments filename,querykey from prop file
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException(
						"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			System.out.println("query" + query);
			Sequence sq = new Sequence();
			//By Anant Patel
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MedicalBoardConfig.HGNUM_TEMPLATE_CATEGORY);
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			}
			catch (Exception e)
			{
				 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			List alRecord = null;
			try
			{
				alRecord = new ArrayList();
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("CategoryMstDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
			}
			System.out.println("alRecord primary. cat" + alRecord);
			return alRecord;
		}

	 
}

