package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods; 
import hisglobal.utility.Sequence;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.RosterAreaCapacityMstVO;
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import registration.dao.RegistrationDaoConfig;

import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.EssentialDAOi;

public class EssentialDAO extends DataAccessObject implements EssentialDAOi{
	
	Logger log;

	public EssentialDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	//--------LIST---OF---AREA--TYPE--LIST--NOT---INCLUDING---BLOCK
	
	
	public List getDutyAreaTypesList(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List dutyAreaTypeList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_LIST.HDRT_DUTY_AREA_MST.DUTY_AREA_EMPLOYEE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());  // by Anant Patel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), DutyRosterConfig.DUTY_AREA_TYPE_CODE_BLOCK);
		//	populateMAP.put(sq.next(), DutyRosterConfig.DUTY_AREA_TYPE_CODE_ESTATE_BLOCK);
			

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("Duty Area Not Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			dutyAreaTypeList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return dutyAreaTypeList;
		
	}
	
	
	//--------LIST---OF---AREA--TYPE--LIST----INCLUDING---BLOCK--AND--ESTATE--BLOCK
	
	
	public List getAllDutyAreaTypesList(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List dutyAreaTypeList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_LIST.HDRT_DUTY_AREA_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());  // by Anant Patel
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("Duty Area Not Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			dutyAreaTypeList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return dutyAreaTypeList;
		
	}
	
	public List getDutyAreaBasedOnDutyAreaType(String areaTypeCode, UserVO _userVO)
	{
		String query = "";
		ResultSet rs=null;
		String errorMsg = "";
		
		List dutyAreaList=new ArrayList();
	
		try
		{
			Procedure strProc = new Procedure(DutyRosterConfig.PROCEDURE_GET_DUTY_AREA_LIST);

			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, areaTypeCode);			
			strProc.addOutParameter(3, Types.VARCHAR);
			//strProc.addOutParameter(4, OracleTypes.CURSOR);   // by Anant Patel
			strProc.addOutParameter(4, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(3);
			rs = (ResultSet) strProc.getParameterAt(4);
			System.out.println("");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		log.info(query);

	
		
		try
		{
			dutyAreaList = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		
		try
		{
			if (dutyAreaList == null || dutyAreaList.size() == 0)
			{
				throw new HisRecordNotFoundException(" No Duty Area Found  for the Corresponding  Duty Area Type");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	 return dutyAreaList;
		
	}
	
	public List getDutyAreaBasedOnRosterType(String _rosterId, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List dutyAreaList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_AREA_ON_THE_BASIS_OF_ROSTER.HDRT_ROSTERAREA_CAPACITY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _rosterId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Duty Area  Found For the Corresponding Roster Type");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			dutyAreaList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return dutyAreaList;
		
	}
	
	public List getDutyAreaWithCapacityBasedOnRosterType(String _rosterId, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List dutyAreaList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_AREA_WITH_CAPACITY_ON_THE_BASIS_OF_ROSTER.HDRT_ROSTERAREA_CAPACITY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _rosterId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Duty Area  Found For the Corresponding Roster Type");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			dutyAreaList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return dutyAreaList;
		
	}
	
	public List getDutyRoleList( UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List dutyRoleList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDRT_DUTY_ROLE_MST.DUTY_AREA_EMPLOYEE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
			//	 throw new HisRecordNotFoundException("No Duty Role Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			dutyRoleList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return dutyRoleList;
		
	}
	
	
	
	public List getDesignationList( UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List designationList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.GBLT_DESIGNATION_MST.EMPLOYEES";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		System.out.println("check 1 " + _userVO.getHospitalCode());
		try
		{									
			
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);//_userVO.getHospitalCode()); 		
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Designation Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			designationList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return designationList;
		
	}
	
	public List getEmpDetailsUnselected(String _empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List empDetailsList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.EMP_DETAILS.UNSELECTED.PIST_EMP_PERSONNEL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE); 	
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE); 
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE); 				
			populateMAP.put(sq.next(), _dutyAreaEmpVO.getAreaTypeCode());
			populateMAP.put(sq.next(), _dutyAreaEmpVO.getAreaCode());
			populateMAP.put(sq.next(), _empDesg);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Employees Found in Left ListBox");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			empDetailsList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return empDetailsList;
		
	}

	public List getEmpDetailsSelected(String _empDesg,DutyRosterAreaEmployeeVO _dutyAreaEmpVO, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List empDetailsList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.EMP_DETAILS.SELECTED.PIST_EMP_PERSONNEL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), _userVO.getHospitalCode());  
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE); 	
			populateMAP.put(sq.next(), _dutyAreaEmpVO.getAreaTypeCode());
			populateMAP.put(sq.next(), _dutyAreaEmpVO.getAreaCode());
			populateMAP.put(sq.next(), _empDesg);  

			
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Employees Found in Right ListBox");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			empDetailsList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return empDetailsList;
		
	}
	
	public List getRosterIdList(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterIdList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDRT_ROSTER_TYPE_MST.ROSTER_CAPACITY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterIdList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterIdList;
		
	}

	
	public List getDutyBlockList(UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List dutyBlockId=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDTSTR_BLOCK_NAME.HDRT_DUTY_BLOCK_MST";
	
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
	
		try 
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				dutyBlockId=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("No Block Found to be Mapped");
			}
			
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return dutyBlockId;
	}

	
	public List getShiftTypesList(UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List shiftType=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_ALL.HDRT_SHIFT_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());  // by Anant Patel	
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); 	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), DutyRosterConfig.DAY_OFF_SHIFT_TYPE_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Shift Type Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	try
		{
		shiftType = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return shiftType;
		
	}
	
	
	public List getDayOffShiftTypeList(UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List shiftType=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_DAY_OFF.HDRT_SHIFT_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
		    //populateMAP.put(sq.next(), _UserVO.getHospitalCode());  
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);   // Added By Anant Patel
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), DutyRosterConfig.DAY_OFF_SHIFT_TYPE_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
			//	 throw new HisRecordNotFoundException("No Day Off Shift Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	try
		{
		shiftType = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return shiftType;
		
	}

	// Gets Shift Types values while update from the HDRT_SHIFT_TYPE_MST table
	public String getShiftTypeCodeAndTime(String _hCode,String _sTypeCode)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		String shiftTypeCode="";
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_UPDATE.HDRT_SHIFT_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
        
		
		try
		{									
			
			//populateMAP.put(sq.next(),_hCode); 	// by Anant Patel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_sTypeCode); 
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Shift Found");
			               }	
						else
						{
							rs.beforeFirst();
							rs.next();
							shiftTypeCode=rs.getString(1);	
						}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	
	 return shiftTypeCode;
		
	}

	public List getDutyRosterCategory(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterCategList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.DISTINCT.HDRT_ROSTER_CAT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Category Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterCategList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterCategList;
		
	}
	
	
	public List getRosterAndAreaTypeList(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterIdList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDRT_ROSTER_TYPE_MST.HDRT_ROSTER_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterIdList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterIdList;
		
	}
	
	
	public List getEmployeesBasedOnDutyAreaAndDesignation(String _desigId,String _areaTypeCode,String _areaCode,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List empList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_DESIGNATION_WISE.HDRT_DUTY_AREA_EMPLOYEE_MST.PIST_EMP_PERSONNEL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _areaTypeCode); 		
			populateMAP.put(sq.next(), _areaCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _desigId); 

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Employees Found For the Corresponding Duty Area and Designation");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			empList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return empList;
		
	}
	
	public List getEmployeesBasedOnDutyAreaAndAllMappedDesignationsWithRosterType(String _rosterId,String _areaId,String _areaCode,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List empList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_MAPPED_DESIGNATIONS.HDRT_DUTY_AREA_EMPLOYEE_MST.PIST_EMP_PERSONNEL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		System.out.println(" Check " + _areaId);
		try
		{									
			populateMAP.put(sq.next(), _areaId); 		
			populateMAP.put(sq.next(), _areaCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _rosterId); 

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Employees Found For the Corresponding Duty Area and Designation");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			empList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return empList;
		
	}
	
	public List getShiftListBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List shiftList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDRT_SHIFT_MST.HDRT_ROSTTYPE_SHIFT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try 
		{									
						
			populateMAP.put(sq.next(), _rosterId); 	
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Shifts Found for the Corresponding Roster Type");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			shiftList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return shiftList;
		
	}

	
	
	public List getRosterType(UserVO _userVO){	
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List rosterType=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.ROSTER_TYPE.HDRT_ROSTER_TYPE_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
								
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosteRoleMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rosterType=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("No Roster Found");
			}
			
		}
		catch (Exception e)
		{
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return rosterType;
	}

	public List getRosterAndAreaTypeListHavingRosterModeLocation(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterIdList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_LOCATION_DUTY_ROSTER.HDRT_ROSTER_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_MODE_LOCATION_BASED);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Found Having Roster Mode Location");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterIdList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterIdList;
		
	}

	public List getAreaBasedOnBlockId(String _areaCode,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List areaList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDRT_DUTY_BLOCK_AREA_MST.pkg_duty_roster.areaName";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try 
		{									
			populateMAP.put(sq.next(), _areaCode); 	
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Duty Area Found for the Corresponding Block");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			areaList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return areaList;
		
	}
	
	
	public List getDesignationBasedOnRosterType(String _rosterId,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List areaList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDRT_ROSTER_DESIGNATION_MST.GBLT_DESIGNATION_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try 
		{									
			populateMAP.put(sq.next(), _rosterId); 	
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Designation Found for the Corresponding Roster Type");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			areaList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return areaList;
		
	}
	
	public List getShiftTypesRosterWise(String _rosterId,UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List shiftType=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_ROSTERWISE.HDRT_SHIFT_MST.HDRT_ROSTTYPE_SHIFT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _rosterId);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Shift Found For the Corresponding Roster Type");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	try
		{
		shiftType = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return shiftType;
		
	}
	
	
	////////List of Rosters on the Basis of Roster Category////////////////	
	
	public List getDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterIdList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_ROSTER_CATEGORY_WISE.HDRT_ROSTER_TYPE_MST.HDRT_ROSTER_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), rosterCategory);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Found For The Corresponding Roster Category");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterIdList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterIdList;
		
	}
	
////////List of EMPLOYEE TYPE Monthwise Rosters on the Basis of Roster Category////////////////
	
	
	public List getEmpTypeMonthWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterIdList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_EMP_TYPE_MONTHLY_ROSTER_CATEGORY_WISE_ROSTER.HDRT_ROSTER_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), rosterCategory);
			//populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_GENERATION_METHOD_MONTHWISE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Found For The Corresponding Roster Category");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterIdList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterIdList;
		
	}
	
	
////////List of EMPLOYEE TYPE DateWise Rosters on the Basis of Roster Category////////////////
	
	
	public List getEmpTypeDateWiseDutyRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterIdList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_EMP_TYPE_DATEWISE_ROSTER_CATEGORY_WISE_ROSTER.HDRT_ROSTER_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), rosterCategory);
			//populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_GENERATION_METHOD_DATE_RANGE_WISE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Found For The Corresponding Roster Category");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterIdList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterIdList;
		
	}

	//list of roster main category
	
	public List getListOfRosterMainCategory(UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List rosterCatglist=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDRT_ROSTER_MAIN_CAT";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode()); // by Anant Patel 
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); 
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Main Category Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	try
		{
		rosterCatglist = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterCatglist;
		
	}
	
	
	//list of Empwise roster category	
	
	public List getEmpWiseRosterCategory(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterCategList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_EMPWISE.HDRT_ROSTER_CAT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
	
		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_MODE_EMPLOYEE_BASED);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Category Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterCategList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterCategList;
		
	}
	
	
/**
 * list of Empwise roster category together with Max off ,Max Continous Off etc.
 */
	
	public List getRosterCategoryList(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterCategList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_CONCATED_VALUES.HDRT_ROSTER_CAT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_MODE_EMPLOYEE_BASED);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Category Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterCategList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterCategList;
		
	}
	
	//list of Locationwise roster category
	
	public List getLocationWiseRosterCategory(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List rosterCategList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_LOCATIONWISE.HDRT_ROSTER_CAT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_MODE_LOCATION_BASED);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Category Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			rosterCategList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterCategList;
		
	}
	
	
	//list of Locationwise roster category
	
	public Map getMonthlyGazettedHolidays(String _year,String _month,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		Map holidayMap=new HashMap();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_MONTHLY_GAZETTED_HOLIDAYS.PIST_HOLIDAY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _year);
			populateMAP.put(sq.next(), _month);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			//populateMAP.put(sq.next(), DutyRosterConfig.GAZETTED_HOLIDAY_CODE);

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 //throw new HisRecordNotFoundException("No Gazetted Holiday Found in This Month");
			               }
					else
					{
							rs.beforeFirst();
						while(rs.next())
							holidayMap.put(rs.getString(1), rs.getString(2));
					}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	 return holidayMap;
		
	}

	
//MAP OF SHIFT TIMINGS
	
	public Map getShiftTimingsOfShift(String _shiftId,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		Map shiftTimingsMap=new HashMap();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_SHIFT_TIMINGS.HDRT_SHIFTTIMING_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _shiftId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 //throw new HisRecordNotFoundException("No Gazetted Holiday Found in This Month");
			               }
					else
					{
							rs.beforeFirst();
						while(rs.next())
							shiftTimingsMap.put(rs.getString(1), rs.getString(2));
					}
						
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	 return shiftTimingsMap;
		
	}


	//---QUERY------FOR---FETCHING---LEAVE DETAILS---FOR----ALL---THE----MAPPED----EMPLOYEES
	//--FOR THE SELECTED MONTH ---LEAVING THE CURRENT ROSTER AND AREA SELECTED ----
	//for fetching of leave of the month for all the mapped employees

	public RosterDtlVO[] fetchEmpLeaveDetails(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_ALL_MAPPED_EMP_LEAVE.PIST_EMP_LEAVE_DTL";
		RosterDtlVO[] _rosterDtlVO=null;
		ValueObject[] _valueObjtsVo=null; 
		
		
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		
		log.info(query);
		try  
		{
			 
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
			populateMAP.put(sq.next(),DutyRosterConfig.APPROVED_LEAVE );
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),_rosterId);
			
			
	       /* populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),DutyRosterConfig.APPROVED_LEAVE );
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),_rosterId);	
				*/
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			if(!rs.next()){
		//	 throw new HisRecordNotFoundException("No Roster Found ");
			               }else
			             {
			            	   rs.beforeFirst();
			            	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterDtlVO.class, rs);
			       			
			       			_rosterDtlVO=new RosterDtlVO[_valueObjtsVo.length];
			       			
			       			
			       			for(int i=0; i <_valueObjtsVo.length ; i++)
			       			 	_rosterDtlVO[i]=(RosterDtlVO)_valueObjtsVo[i];
			       						       			 			            	   
			            	   
			             }	   
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		


	return _rosterDtlVO;
	}
	
	
	public List getEmployeesMappedWithAllMappedAreasWithRosterType(String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List empList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_AREAS_MAPPED_DESIGNATIONS.HDRT_DUTY_AREA_EMPLOYEE_MST.PIST_EMP_PERSONNEL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _areaTypeCode); 		
		//AREA CODE REMOVED AND INSERTED ROSTERid
			//populateMAP.put(sq.next(), _areaCode);
			
			populateMAP.put(sq.next(), _rosterId); 
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _rosterId); 

			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Employees Found For the Corresponding Duty Area and Designation");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			empList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return empList;
		
	}
	

	
	
	public List getEmpListBasedOnDesignation(String desigId, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List designationList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_EMP_BASED_ON_DESIGNATION.PIST_EMP_PERSONNEL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE); 	
			populateMAP.put(sq.next(), desigId); 	
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Employee Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			designationList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return designationList;
		
	}
	
	
	public List getDutyAreaBasedOnEmployee(String _empId, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List areaList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_EMPWISE_AREA.HDRT_ROSTER_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _empId); 	
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE); 	
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Duty Area Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			areaList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return areaList;
		
	}
	
	public List getRosterListBasedonAreaEmp(String _empId,String _areaCode, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List areaList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_EMP_AREA_WISE_ROSTERS.HDSST_REPORT_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _empId); 	
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE); 	
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), _areaCode); 	
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Duty Area Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			areaList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return areaList;
		
	}
	
	
	public List getEmpListofSupervisior(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List empList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_LOW_LEVEL_EMP_FOR_ROSTER_PRINT.GBLT_USER_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Employee Found");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			empList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return empList;
		
	}
	
	
	
//list of roster main category
	
	public List getDutyAreasBasedOnRosterCategory(String rosterCategory,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List areaList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_AREAS_ON_BASIS_OF_ROSTER_CATG.HDRT_ROSTERAREA_CAPACITY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(),rosterCategory);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Duty Area Found For the Corresponding Roster Category ");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	try
		{
		areaList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return areaList;
		
	}
	
	
//list of roster main category
	
	public List getRostersBasedOnDutyAreaAndRosterCatg(String rosterCategory,String _areaTypeCode,String _areaCode,UserVO _userVO) 
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List rosterCatglist=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_ROSTERS_DEPENDENT_ON_CATG_AREA.HDRT_ROSTER_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), rosterCategory); 	
			populateMAP.put(sq.next(), _areaTypeCode); 	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _areaCode); 	
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Rosters Found for the  Corresponding Roster Category and Duty Area ");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	try
		{
		rosterCatglist = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterCatglist;
		
	}
	
	
	public List getDutyAreaBasedOnRosterAndDutyAreaType(String rosterId,String areaTypeCode, UserVO _userVO)
	{
		String query = "";
		ResultSet rs=null;
		String errorMsg = "";
		
		List dutyAreaList=new ArrayList();
	
		try
		{
			Procedure strProc = new Procedure(DutyRosterConfig.PROCEDURE_GET_DUTY_AREA_LIST_ROSTER_WISE);

			strProc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, areaTypeCode);		
			strProc.addInParameter(3, Types.VARCHAR, rosterId);	
			strProc.addOutParameter(4, Types.VARCHAR);
			//strProc.addOutParameter(5, OracleTypes.CURSOR);   // by Anant Patel
			strProc.addOutParameter(5, Types.REF);

			strProc.execute(super.getTransactionContext().getConnection());
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);
			System.out.println("");
		}
		catch (HisException e)
		{	
			e.printStackTrace();
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		log.info(query);

	
		
		try
		{
			dutyAreaList = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{	
			e.printStackTrace();
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
		
		try
		{
			if (dutyAreaList == null || dutyAreaList.size() == 0)
			{
				throw new HisRecordNotFoundException(" No Duty Area Found  for the Corresponding  Duty Area Type");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	 return dutyAreaList;
		
	}


	// fetchForLocationRoster  Showing Data of Selected Record
	public RosterAreaCapacityMstVO getRosterShiftWiseCapacity(String _rosterId,String _areaTypeCode,String _areaCode, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		RosterAreaCapacityMstVO rostAreaCapMstVO=new RosterAreaCapacityMstVO(); 
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_CAPACITY.HDRT_ROSTERAREA_CAPACITY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _rosterId);
			populateMAP.put(sq.next(), _areaCode);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), _areaTypeCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("Details Not Found");
			               }else
			               {
			            	   rs.beforeFirst();
			            	   while(rs.next())
			            	   {
			            		  HelperMethods.populateVOfrmRS(rostAreaCapMstVO,rs);
			            	   }
			            		   
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	 return rostAreaCapMstVO;
		
	}

	public List getRosterForRoleAssignment(UserVO _userVO) {
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List rosterlist=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "ESSENTIAL_ROSTER_TYPE_FOR_ROLE.HDRT_ROSTER_TYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE); 	
			populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_MODE_EMPLOYEE_BASED);
			//populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_GENERATION_METHOD_MONTHWISE);  // Anant Patel on 26th Nov 2014 
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("Duty Roster Essential DAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Rosters Found For Seat ID ");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	try
		{
		rosterlist = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return rosterlist;
	}

	public List getShiftForRoster(String _rosterTypeID, UserVO _userVO) {
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List shiftList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "ESSENTIAL_SHIFT_MAPPED_WITH_ROSTER.HDRT_ROSTTYPE_SHIFT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _rosterTypeID); 
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE); 	
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Shifts Found for Roster");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	try
		{
		shiftList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return shiftList;
	}

	public RosterDtlVO[] getShiftBasedEmployeeList(RosterDtlVO _rosterDtlVO,UserVO _uservo) {
		String query = "";
		ResultSet rs;
		ValueObject[] vo ={};
		RosterDtlVO[] rosterDtlVO;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		List rosterCatglist=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "ESSENTIAL_EMPLOYEE_LIST_SHIFT_WISE.HDRT_ROSTER_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), _rosterDtlVO.getRosterId()); 	
			populateMAP.put(sq.next(), _rosterDtlVO.getAreaCode()); 	
			populateMAP.put(sq.next(), _uservo.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_STATUS_IS_GENERATED); 
			populateMAP.put(sq.next(), _rosterDtlVO.getStartDateTime()); 
			populateMAP.put(sq.next(), _rosterDtlVO.getEndDateTime()); 
			populateMAP.put(sq.next(), _rosterDtlVO.getStartDateTime()); 
			populateMAP.put(sq.next(), _rosterDtlVO.getEndDateTime()); 
			populateMAP.put(sq.next(), _rosterDtlVO.getShiftId()); 	
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Employee found");

			}
			rs.beforeFirst();
			
			vo = HelperMethods.populateVOfrmRS(RosterDtlVO.class, rs);
			
			rosterDtlVO = new RosterDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				System.out.println("before casting");
				rosterDtlVO[i] = (RosterDtlVO) vo[i];
				
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Duty Roster Essential Dao:employee list::shift Details:: " + e);
		}
		return rosterDtlVO;
	}

	public List getRosterWiseDutyRole(String rosterTypeId,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List dutyRoleList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.ROSTER_DUTY_ROLE.HDRT_ROSTER_ROLE_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), rosterTypeId);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosteRoleMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				dutyRoleList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("No Role Mapped With the Roster");
			}
			
		}
		catch (Exception e)
		{
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return dutyRoleList;
	}
	

	public List getShiftListForRosterType(String _rosterId,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		
		List shiftList=new ArrayList();
				
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_SHIFTS_OF_ROSTER.HDRT_ROSTTYPE_SHIFT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try 
		{									
						
			populateMAP.put(sq.next(), _rosterId); 	
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Shifts Found for the Corresponding Roster Type");
			               }
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		try
		{
			shiftList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	 return shiftList;
		
	}

	
	public List getEmpListToBeExchanged(String _rosterCatgId,UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List empList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_EXCHANGE_EMP_LIST.HDRT_ROSTER_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{	
			
		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), _rosterCatgId);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Duty Area  Found For the Corresponding Roster Type");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			empList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return empList;
		
	}
	
	

	public List getCatListForNurse(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List empList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "NURSE_CAT_LIST.HDRT_ROSTER_CAT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{	
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_MAIN_CATEGORY_NURSING);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Nurse Category List Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			empList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return empList;
		
	}
	
	
	public List getAllDutyRoleList( UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List dutyRoleList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.HDRT_DUTY_ROLE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 	
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
			//	 throw new HisRecordNotFoundException("No Duty Role Found");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			dutyRoleList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return dutyRoleList;
		
	}
	
	
	
	

	public List getRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg, UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence(); 
		
		List rosterCatgList=new ArrayList();
		
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT.HDRT_ROSTER_CAT_MST.BASED_ON_ROSTER_MAIN_CATEGORY";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);

		try
		{									
			populateMAP.put(sq.next(), _rosterMainCatg);
			populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if(!rs.next()){
				 throw new HisRecordNotFoundException("No Roster Category  Found For the Corresponding Roster Main Category");
			               }	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
		try
		{
			rosterCatgList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		
	 return rosterCatgList;
		
	}

	//****************************For getting duty list for ExChange**********************///////////

	public RosterDtlVO[] getEmpDutyListForExchange(String _year,String _month,String _rosterCatgId,String empId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_DUTIES_OF_EMP_FOR_EXCHANGE.HDRT_ROSTER_DTL";
		RosterDtlVO[] _rosterDtlVO=null;
		ValueObject[] _valueObjtsVo=null; 
		
		
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		
		log.info(query);
		try  
		{
			 
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(),empId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_rosterCatgId);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			if(!rs.next()){
		//	 throw new HisRecordNotFoundException("No Roster Found ");
			               }else
			             {
			            	   rs.beforeFirst();
			            	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterDtlVO.class, rs);
			       			
			       			_rosterDtlVO=new RosterDtlVO[_valueObjtsVo.length];
			       			
			       			
			       			for(int i=0; i <_valueObjtsVo.length ; i++)
			       			 	_rosterDtlVO[i]=(RosterDtlVO)_valueObjtsVo[i];
			       						       			 			            	   
			            	   
			             }	   
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		


	return _rosterDtlVO;
	}
	
	//****************************For getting duty list for change**********************///////////
	
	public RosterDtlVO[] getEmpDutyListForChange(String _year,String _month,String _day,String _rosterCatgId,String empId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_DUTIES_OF_EMP_FOR_CHANGE.HDRT_ROSTER_DTL";
		RosterDtlVO[] _rosterDtlVO=null;
		ValueObject[] _valueObjtsVo=null; 
		
		
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		
		log.info(query);
		try  
		{
			 
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(),_day);
			populateMAP.put(sq.next(),_day);
			populateMAP.put(sq.next(),empId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_rosterCatgId);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				
			if(!rs.next()){
		//	 throw new HisRecordNotFoundException("No Roster Found ");
			               }else
			             {
			            	   rs.beforeFirst();
			            	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterDtlVO.class, rs);
			       			
			       			_rosterDtlVO=new RosterDtlVO[_valueObjtsVo.length];
			       			
			       			
			       			for(int i=0; i <_valueObjtsVo.length ; i++)
			       			 	_rosterDtlVO[i]=(RosterDtlVO)_valueObjtsVo[i];
			       						       			 			            	   
			            	   
			             }	   
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		


	return _rosterDtlVO;
	}
	
	
	
public List getDateRangeDutyRoleAssignmentList(String rosterTypeId,String areaCode,UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List dateRangeRoleAssignmentList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_DATE_RANGE_LIST.HDRT_DUTY_ROLE_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.info(query);
		
		try 
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), rosterTypeId);
			populateMAP.put(sq.next(), areaCode);
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosteRoleMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next())
					dateRangeRoleAssignmentList.add(rs.getString(1));
				
			}
			else{
			//	throw new HisRecordNotFoundException("Duty Role Record Not Found");
			}
			
		}
		catch (Exception e)
		{
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		return dateRangeRoleAssignmentList;
	}


public List getAreaListBasedOnRosterCategory(String _rosterCategoryId,UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	List dutyAreaTypeList=new ArrayList();
			
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_ROSTER_CATEGORY_WISE_AREAS.HDRT_ROSTERAREA_CAPACITY_MST";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		
		populateMAP.put(sq.next(), _rosterCategoryId);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("Duty Area Not Found");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		dutyAreaTypeList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
 return dutyAreaTypeList;
	
}



public List getEmpListBasedOnRosterCategory(String _year,String _month,String _rosterCategoryId,UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	List empList=new ArrayList();
			
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_ROSTER_CATEGORY_WISE_EMP.HDRT_ROSTER_DTL";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _year);
		populateMAP.put(sq.next(), _year);
		populateMAP.put(sq.next(), _month);
		populateMAP.put(sq.next(), _month);
		populateMAP.put(sq.next(), _rosterCategoryId);
				
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("Employee Not Found");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		empList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
 return empList;
	
}



public List getReliverEmpListBasedOnRosterCategory(String _year,String _month,String _rosterCategoryId,UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	List reliverEmpList=new ArrayList();
			
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_ROSTER_CATEGORY_WISE_RELIVERS.HDRT_ROSTERWISE_RELIEVERS_DTL";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		populateMAP.put(sq.next(), _rosterCategoryId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), _year);
		populateMAP.put(sq.next(), _year);
		populateMAP.put(sq.next(), _month);
		populateMAP.put(sq.next(), _month);
				

		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("Reliver Employee Not Found");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		reliverEmpList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
 return reliverEmpList;
	
}


public List getShiftListBasedOnRosterCategory(String _year,String _month,String _areaCode,String _areaTypeCode,String _rosterCategoryId,UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	List shiftList=new ArrayList();
			
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_ROSTER_CATEGORY_WISE_SHIFTS.HDRT_ROSTTYPE_SHIFT_MST";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		populateMAP.put(sq.next(), _areaCode);
		populateMAP.put(sq.next(), _areaTypeCode);
		populateMAP.put(sq.next(), _year);
		populateMAP.put(sq.next(), _year);
		populateMAP.put(sq.next(), _month);
		populateMAP.put(sq.next(), _month);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);		
		populateMAP.put(sq.next(), _rosterCategoryId);
		populateMAP.put(sq.next(), _areaTypeCode);
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("Shift Not Found");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		shiftList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
 return shiftList;
	
}



//////////////////////List of Role Based  Roster Main Category/////////////////////////////////////

public List getListOfRoleBasedRosterMainCategory(UserVO _UserVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	List rosterCatglist=new ArrayList();
	
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_ROLE_BASED.HDRT_ROSTER_MAIN_CAT";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		
		populateMAP.put(sq.next(), _UserVO.getHospitalCode()); 		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("BloodDonorTypeMstDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("No Roster Main Category Found");
		               }	
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) 
			throw new HisRecordNotFoundException(e.getMessage());
		else
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	
try
	{
	rosterCatglist = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("LocationDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
 return rosterCatglist;
	
}


////////List of Role Based Roster  Category  on the Basis of Roster Main Category////////////////



public List getListOfRoleBasedRosterCategoryBasedOnRosterMainCategory(String _rosterMainCatg, UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	List rosterCatgList=new ArrayList();
	
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_ROLE_BASED.HDRT_ROSTER_CAT_MST";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		populateMAP.put(sq.next(), _rosterMainCatg);
		populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("No Roster Category  Found For the Corresponding Roster Main Category");
		               }	
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	
	try
	{
		rosterCatgList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
	
 return rosterCatgList;
	
}


////////List of Role Based Rosters on the Basis of Roster Category////////////////

public List getListOfRoleBasedRostersOnTheBasisOfRosterCategory(String rosterCategory,UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	List rosterIdList=new ArrayList();
			
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_ROLE_BASED.HDRT_ROSTER_TYPE_MST";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		
		populateMAP.put(sq.next(), _userVO.getHospitalCode()); 		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), rosterCategory);

		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("No Roster Found For The Corresponding Roster Category");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		rosterIdList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
 return rosterIdList;
	
}


public List getListOfAllMappedEmployeesHavingUserId(String _rosterId,String _areaTypeCode,String _areaCode,UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	List empList=new ArrayList();
			
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_ALL_MAPPED_USER_EMP.HDRT_DUTY_AREA_EMPLOYEE_MST.PIST_EMP_PERSONNEL_DTL";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		populateMAP.put(sq.next(), _areaTypeCode); 		
		populateMAP.put(sq.next(), _areaCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode()); 	
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _rosterId);
		populateMAP.put(sq.next(), DutyRosterConfig.USER_TYPE_EMPLOYEE);
		

		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("No Employees Found ");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		empList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
 return empList;
	
}

public List getAllHospitalsSeatIDWise(UserVO _userVO)
{
	//_userVO.get seatid to be obtained from userVO

	ResultSet rs = null;
	String query = "";
	Map populateMAP = new HashMap();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "ESSENTIAL.ALL_HOSPTIAL_SEAT_WISE.GBLT_HOSPITAL_MST";

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
	
	Sequence sq = new Sequence();
	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	populateMAP.put(sq.next(), _userVO.getSeatId());
	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	

	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
	}
	List alRecord = new ArrayList();
	try
	{
		if (!rs.next())
		{
			throw new HisRecordNotFoundException("No Hospitals Found");
		}
		else rs.beforeFirst();
		
		alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
	}
	
	return alRecord;
}

public List getRosterCategoryBasedOnHospital(String hospitalCode,UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	List rosterCategList=new ArrayList();
			
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_EMPWISE.HDRT_ROSTER_CAT_MST";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		
		populateMAP.put(sq.next(), hospitalCode); 		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_MODE_EMPLOYEE_BASED);
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("No Roster Category Found");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		rosterCategList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
	return rosterCategList;
}
public List getDesignationBasedOnHospital(String hospitalCode,UserVO _userVO)
{
	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	List rosterCategList=new ArrayList();
			
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_EMPWISE.HDRT_ROSTER_CAT_MST";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);

	try
	{									
		
		populateMAP.put(sq.next(), hospitalCode); 		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_MODE_EMPLOYEE_BASED);
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("No Roster Category Found");
		               }
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	try
	{
		rosterCategList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}
	return rosterCategList;
}


}
