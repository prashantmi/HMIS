package dutyroster.transaction.dao;




import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.utility.HelperMethods; 
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterReliverDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;


import org.apache.log4j.LogManager; 
import org.apache.log4j.Logger;
import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.DutyAreaEmployeeMstDAOi;

public class RosterDtlDAO extends DataAccessObject implements RosterDtlDAOi{
	
	Logger log;

	public RosterDtlDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	
	
	
	
	// on Add page for Saving Data
	public void create(RosterDtlVO _rosterDtlVO,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "INSERT.HDRT_ROSTER_DTL";

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
			 
			 			 
			
			
			populateMAP.put(sq.next(), _rosterDtlVO.getRosterId());
			populateMAP.put(sq.next(), _rosterDtlVO.getAreaCode());
			populateMAP.put(sq.next(), _rosterDtlVO.getAreaTypeCode());
			populateMAP.put(sq.next(), _rosterDtlVO.getEmpId());
			populateMAP.put(sq.next(), _rosterDtlVO.getShiftId());
			populateMAP.put(sq.next(), _rosterDtlVO.getStartDateTime());
			populateMAP.put(sq.next(), _rosterDtlVO.getEndDateTime());
			populateMAP.put(sq.next(), _rosterDtlVO.getRoleId());
			populateMAP.put(sq.next(), _rosterDtlVO.getDutyDone());
			populateMAP.put(sq.next(), _rosterDtlVO.getDutyRemarks());
			populateMAP.put(sq.next(), _rosterDtlVO.getCancelRemarks());			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _rosterDtlVO.getGeneratedRosterId());
			populateMAP.put(sq.next(), _rosterDtlVO.getGeneratedRosterId());
			populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
			populateMAP.put(sq.next(), _rosterDtlVO.getToDate());
			populateMAP.put(sq.next(), _rosterDtlVO.getDutyType());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}


	//Fetching the Record for the employees whose roster has been prepared
	
	public List fetch(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT.HDRT_ROSTER_DTL.PIST_EMP_PERSONNEL_DTL.2";
		List empRosterList=new ArrayList();
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		query+=  "   AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4,2) = "+_month
		       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4, 2) = "+_month 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
		       + " ORDER BY empName,concatedata ";
		log.info(query);
		try  
		{
			 
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(),_desigId );
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			if(!rs.next()){
		//		 throw new HisRecordNotFoundException("Details Not Found");
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
			empRosterList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
	return empRosterList;
	}
	
	
	//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
	
	public List fetchAllMappedEmp(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_ALL.HDRT_ROSTER_DTL.PIST_EMP_PERSONNEL_DTL";
		List empRosterList=new ArrayList();
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		query+=  "   AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4,2) = "+_month
		       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4, 2) = "+_month 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
		       + " ORDER BY empName,concatedata ";
		log.info(query);
		try  
		{
			 
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			if(!rs.next()){
		//		 throw new HisRecordNotFoundException("Details Not Found");
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
			empRosterList = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
	return empRosterList;
	}
	
	// for Updating The old Record
	public void update(String _rosterId,String _areaTypeCode,String _areaCode,String empList,String daysList,String _year,String _month,UserVO _userVO)
	{
		
		   
		   
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "UPDATE_ISVALID.HDRT_ROSTER_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
    query+=  "  AND STR_EMP_NO IN  ( "+empList+" ) "
    	   + " AND EXTRACT(day from hdrdt_start_datetime) IN ( "+daysList+" ) " 
    	   + " AND EXTRACT(month from hdrdt_start_datetime) = "+_month
	       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year
	       + " AND EXTRACT(day from hdrdt_start_datetime) IN ( "+daysList+" ) "
	       + " AND EXTRACT(month from hdrdt_start_datetime) = "+_month 
	       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year;
	      
		
		log.info(query);

		try  
		{
			 
			 			 
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _rosterId);
			populateMAP.put(sq.next(), _areaCode);
			populateMAP.put(sq.next(), _areaTypeCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

	}

	
	
	//Fetching the Record for the employees whose roster has been prepared
	//for report generation
		
		public RosterDtlVO[] fetchDesignationWiseRoster(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
			String queryKey = "SELECT_DESG_MAPPED_EMP_ROSTER.HDRT_ROSTER_DTL";
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
			
			
			query+=  "   AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4,2) = "+_month
			       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
			       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4, 2) = "+_month 
			       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
			       + " ORDER BY empname, shiftid DESC, startDateTime";
			log.info(query);
			try  
			{
				 
				
				populateMAP.put(sq.next(),_rosterId);
				populateMAP.put(sq.next(),_areaCode );
				populateMAP.put(sq.next(),_areaTypeCode );
				populateMAP.put(sq.next(),_desigId );
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
					
				
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
		
		
///////////////////////Functions for the Roster Report Generation///////////////////////
	
	
//Fetching the Record for the employees whose roster has been prepared
//for report generation
	
	public RosterDtlVO[] fetchDesignationWiseRosterReport(String _desigId,String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_DESG_MAPPED_EMP_REPORT.HDRT_ROSTER_DTL";
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
		
		
		query+=  "   AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4,2) = "+_month
		       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4, 2) = "+_month 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
		       + " ORDER BY empname, shiftid DESC, startDateTime";
		log.info(query);
		try  
		{
			 
			
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(),_desigId );
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
			
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
	
	
	
	//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
	//For report generation
	
	public RosterDtlVO[] fetchAllMappedDesignationWiseRoster(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_ALL_MAPPED_EMP_ROSTER.HDRT_ROSTER_DTL";
		
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
		
		
		query+=  "   AND EXTRACT(MONTH from hdrdt_start_datetime) = "+_month
		       + " AND EXTRACT(YEAR from hdrdt_start_datetime) = "+_year 
		       + " AND EXTRACT(month from  hdrdt_end_datetime) = "+_month 
		       + " AND EXTRACT(YEAR from  hdrdt_end_datetime) ="+_year
		       + " ORDER BY empname,  startDateTime ";
		log.info(query);
		try  
		{
			 
			
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaCode );
		//	populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
		//	System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			
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
	
	
	
	//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
	//For report generation
	
	public RosterDtlVO[] fetchAllMappedDesignationWiseRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_ALL_MAPPED_EMP_REPORT.HDRT_ROSTER_DTL_NEW";
		
		RosterDtlVO[] _rosterDtlVO=null;
		ValueObject[] _valueObjtsVo=null; 
		
		log.info(queryKey);
		
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		query+=  "   AND EXTRACT(month from hdrdt_start_datetime) = "+_month
		       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
		       + " AND EXTRACT(month from hdrdt_start_datetime) = "+_month 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
		       + " ORDER BY empname,  startDateTime ";
		log.info(query);
		try  
		{
			 
			
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
		//	System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			
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
	

	

///////////////////////Functions for getting the records for  Date wise Emp Roster ///////////////////////


//Fetching the Record for the employees whose roster has been prepared
////Designation wise

public List fetchDesignationWiseDateWiseRoster(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_DESIGNATION_WISE_DISTINCT_DATEWISE_ROSTER.HDRT_ROSTER_DTL";
	List empRosterList=new ArrayList();
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
		 

		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(),_desigId );	
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
		System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		if(!rs.next()){
	//		 throw new HisRecordNotFoundException("Details Not Found");
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
		empRosterList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}

return empRosterList;
}


//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For All mapped designation

public List fetchAllMappedDesignationDateWiseRoster(String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_ALL_DISTINCT_DATEWISE_ROSTER.HDRT_ROSTER_DTL";
	List empRosterList=new ArrayList();
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
		 

		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
		System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		if(!rs.next()){
	//		 throw new HisRecordNotFoundException("Details Not Found");
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
		empRosterList = HelperMethodsDAO.getAlOfEntryObjects(rs);
	}
	catch (Exception e)
	{
		throw new HisDataAccessException("EssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
	}

return empRosterList;
}

//Fetching the Record for the employees whose roster has been prepared
////Designation wise

public RosterDtlVO[] fetchVOofDesignationWiseDateWiseRoster(String _desigId,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_DESG_MAPPED_EMP_REPORT.HDRT_ROSTER_DTL";
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
		 
		
		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(),_desigId );
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		
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


//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For All mapped designation

public RosterDtlVO[] fetchVOofAllMappedDesignationDateWiseRoster(String _generatedRosterId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	//String queryKey = "SELECT_DESG_MAPPED_EMP_REPORT.HDRT_ROSTER_DTL";
	String queryKey = "SELECT_DATE_WISE_EMP_ROSTER_MODIFY.HDRT_ROSTER_DTL";
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
		 
		
		populateMAP.put(sq.next(),_generatedRosterId);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		
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


//---QUERY------FOR---FETCHING----THE--ROSTERS---FOR----ALL---THE----MAPPED----EMPLOYEES
//--FOR THE WHOLE MONTH ---LEAVING THE CURRENT ROSTER AND AREA SELECTED ----
//I.E. THE EMPLOYEE ROSTER IS PREPARED SOMEWHERE ELSE ALSO

public RosterDtlVO[] fetchVOofAllMappeDEmpsForOtherRosterOftheMonth(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_ALL_MAPPEED_EMP_ROSTERS_MONTHLY.HDRT_ROSTER_DTL";
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
		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_areaCode );
		
			
			
		
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




//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For report generation

public RosterDtlVO[] fetchAllMappedAreaWiseRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_ALL_MAPPED_AREA_EMP_REPORT.HDRT_ROSTER_DTL";
	
	RosterDtlVO[] _rosterDtlVO=null;
	ValueObject[] _valueObjtsVo=null; 
	
	log.info(queryKey);
	
	ResultSet rs;
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	
	
	query+=  "   AND EXTRACT(month from hdrdt_start_datetime) = "+_month
	       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
	       + " AND EXTRACT(month from hdrdt_start_datetime) = "+_month 
	       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
	       + " ORDER BY areaCode,empName, startDateTime ";
	  	   //+ " ORDER BY areaCode,empName, shiftId DESC, startDateTime ";
	log.info(query);
	try  
	{
		 
		
		populateMAP.put(sq.next(),_rosterId);
	//area removed in case all the area is selected
		//	populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
	//	System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		
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



//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For report generation

public RosterDtlVO[] fetchAreaWiseEmpWiseRosterReport(String _year,String _month,String _rosterId,String _areaTypeCode,String _areaCode,String _empId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_EMP_AREAWISE.HDRT_ROSTER_DTL";
	
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
		 
		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_areaTypeCode);
		populateMAP.put(sq.next(),_areaCode);	
		populateMAP.put(sq.next(),_empId);	
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(),_year);		
		populateMAP.put(sq.next(),_year);		
		populateMAP.put(sq.next(),_month);		
		populateMAP.put(sq.next(),_month);		
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
	//	System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("No Roster Found ");
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



//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For report generation

public RosterDtlVO[] fetchEmpWiseRosterReport(String _year,String _month,String empId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
	String queryKey = "SELECT_EMP_WISE.HDRT_ROSTER_DTL";
	
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
		
		System.out.println("hieeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+empId);
		System.out.println("hieeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+_userVO.getUserEmpID());
		
		populateMAP.put(sq.next(), empId); 
		
		
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

	
		populateMAP.put(sq.next(),_year);		
		populateMAP.put(sq.next(),_year);		
		populateMAP.put(sq.next(),_month);		
		populateMAP.put(sq.next(),_month);		
		
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
	//	System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		
		if(!rs.next()){
			 throw new HisRecordNotFoundException("No Roster Found ");
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


//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For report generation

public RosterDtlVO[] fetchSeperateAllMappedAreaWiseRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_SEPERATE_ALL_MAPPED_AREA_EMP_REPORT.HDRT_ROSTER_DTL";
	
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
	
	
	query+=  " AND EXTRACT(month from hdrdt_start_datetime) = "+_month
	       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
	       + "AND EXTRACT(month from hdrdt_start_datetime) = "+_month 
	       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
	       + " ORDER BY areaCode,empName, startDateTime ";
	  	   //+ " ORDER BY areaCode,empName, shiftId DESC, startDateTime ";
	log.info(query);
	try  
	{
		 
		
		populateMAP.put(sq.next(),_rosterId);
	//area removed in case all the area is selected
		//	populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
	//	System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		
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


//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For report generation

public RosterDtlVO[] fetchSeperateAllMappedDesignationWiseRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_SEPERATE_ALL_MAPPED_EMP_REPORT.HDRT_ROSTER_DTL";
	
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
	
	
//	query+=  "   AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4,2) = "+_month
//	       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
//	       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 4, 2) = "+_month 
//	       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
//	       + " ORDER BY empname,  startDateTime ";
//	log.info(query);
	
	
	query+=  "   AND EXTRACT(MONTH from hdrdt_start_datetime) = "+_month
		       + " AND EXTRACT(YEAR from hdrdt_start_datetime) = "+_year 
		       + " AND EXTRACT(month from  hdrdt_end_datetime) = "+_month 
		       + " AND EXTRACT(YEAR from  hdrdt_end_datetime) ="+_year
		       + " ORDER BY empname,  startDateTime ";
		log.info(query);
	
	
	
	
	
	
	try  
	{
		 
		
		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
	//	System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		
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


//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For report generation

public RosterDtlVO[] fetchAllRostersCategAreaWiseReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterCatg,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_ALL_ROSTERS_CATG_AREAWISE_REPORT.HDRT_ROSTER_DTL";
	
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
	
	
	query+=  " AND EXTRACT(month from hdrdt_start_datetime) = "+_month
	       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
	       + "AND EXTRACT(month from hdrdt_start_datetime) = "+_month 
	       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
	       + " ORDER BY empname,  startDateTime ";
	log.info(query);
	try  
	{
		 
		
		populateMAP.put(sq.next(),_rosterCatg);
		populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
	//	System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		
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

public String getGeneratedRosterId(UserVO _userVO)
{

	String query = "";
	ResultSet rs;
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence(); 
	
	
	String rosterGenId="";

	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_GENERATE_ROSTER_ID.PKG_DUTY_ROSTER.GEN_ROSTER_ID";

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
			 throw new HisRecordNotFoundException("Duty Area Not Found");
		               }
				else
			rosterGenId=rs.getString(1);
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	

 return rosterGenId;
	

}


//for Updating The old Record
public void updateDutyDoneCancelForReliverDutyAssignment(RosterDtlVO _rosterForReliver,UserVO _userVO)
{
	
	   
	   
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "UPDATE_DUTY_DONE.HDRT_ROSTER_DTL.FOR_RELIVER_ASSIGNMENT";

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
		 
		populateMAP.put(sq.next(), _rosterForReliver.getDutyDone()); 			 
		populateMAP.put(sq.next(), _rosterForReliver.getGeneratedRosterId());
		populateMAP.put(sq.next(), _rosterForReliver.getEmpId());
		populateMAP.put(sq.next(), _rosterForReliver.getStartDateTime());
		populateMAP.put(sq.next(), _rosterForReliver.getEndDateTime());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}

}

//on Add page for Saving Data
public void createForReliverDutyAssignment(RosterDtlVO _rosterDtlVO,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "INSERT_AS_RELIVER.HDRT_ROSTER_DTL.FOR_RELIVER_ASSIGNMENT";

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
		 
		 			 
		
		
		populateMAP.put(sq.next(), _rosterDtlVO.getRosterId());
		populateMAP.put(sq.next(), _rosterDtlVO.getAreaCode());
		populateMAP.put(sq.next(), _rosterDtlVO.getAreaTypeCode());
		populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		
		populateMAP.put(sq.next(), _rosterDtlVO.getRosterId());
		populateMAP.put(sq.next(), _rosterDtlVO.getAreaCode());
		populateMAP.put(sq.next(), _rosterDtlVO.getAreaTypeCode());
		populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), _rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
		
		populateMAP.put(sq.next(), _rosterDtlVO.getRosterId());
		populateMAP.put(sq.next(), _rosterDtlVO.getAreaCode());
		populateMAP.put(sq.next(), _rosterDtlVO.getAreaTypeCode());
		populateMAP.put(sq.next(), _rosterDtlVO.getEmpId());
		populateMAP.put(sq.next(), _rosterDtlVO.getShiftId());
		populateMAP.put(sq.next(), _rosterDtlVO.getStartDateTime());
		populateMAP.put(sq.next(), _rosterDtlVO.getEndDateTime());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _rosterDtlVO.getDutyType());
		populateMAP.put(sq.next(), _userVO.getSeatId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		

	}
	catch (Exception e)
	{
		
		throw new HisApplicationExecutionException("No Generated Roster Found");
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
}






public void updateDutyRole(RosterDtlVO rosterDtlVO, UserVO _uservo) {

	   
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "UPDATE_DUTY_ROLE.HDRT_ROSTER_DTL";

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
		 
		populateMAP.put(sq.next(), rosterDtlVO.getRoleId()); 			 
		populateMAP.put(sq.next(), rosterDtlVO.getGeneratedRosterId());
		populateMAP.put(sq.next(), rosterDtlVO.getEmpId());
		populateMAP.put(sq.next(), rosterDtlVO.getShiftId());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _uservo.getHospitalCode());
		populateMAP.put(sq.next(), rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), rosterDtlVO.getToDate());
		populateMAP.put(sq.next(), rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(), rosterDtlVO.getToDate());
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}

	
}



public int checkDuplicateDateWiseRoster(String _generatedRosterid,RosterDtlVO _rosterDtlVO,UserVO _userVO)
	{

	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_DUPLICATE_CHECK_DATE_WISE_EMP_ROSTER.HDRT_ROSTER_DTL";
	int count=0;
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
		
		populateMAP.put(sq.next(),_generatedRosterid);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(),_rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(),_rosterDtlVO.getToDate());
		populateMAP.put(sq.next(),_rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(),_rosterDtlVO.getToDate());
		populateMAP.put(sq.next(),_rosterDtlVO.getFromDate());
		populateMAP.put(sq.next(),_rosterDtlVO.getToDate());
		
		
		
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
		System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		if(!rs.next()){
			// throw new HisRecordNotFoundException("Details Not Found");
		               }
		else
		{
			count=rs.getInt(1);
		}
			
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class) 
			throw new HisRecordNotFoundException(e.getMessage());
		else
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
	

return count;

	}




//for Updating The old Record
public void updateDateWiseEmpDutyRoster(RosterDtlVO _rosterDtlVo,UserVO _userVO)
{
	
	   
	   
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "UPDATE_DATE_WISE_EMP_ROSTER.HDRT_ROSTER_DTL";

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
		populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _rosterDtlVo.getGeneratedRosterId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}

}


//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For report generation

public RosterDtlVO[] fetchMonthlyEmpRosterReportDateWise(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,String _fromDate,String _toDate,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_MONTHLY_EMP_ROSTER_REPORT_DATE_WISE.HDRT_ROSTER_DTL";
	
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
		 
		
		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(),_year);
		populateMAP.put(sq.next(),_year);
		populateMAP.put(sq.next(),_month);
		populateMAP.put(sq.next(),_month);
		populateMAP.put(sq.next(),_fromDate);
		populateMAP.put(sq.next(),_toDate);
		populateMAP.put(sq.next(),_fromDate);
		populateMAP.put(sq.next(),_toDate);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
	//	System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		
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


public List getRosterDetail(String rosterCatId,String dutyAreaCode,UserVO _userVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT.ROSTER_DETAIL.HDRT_ROSTER_DTL";

	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	
	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	populateMAP.put(sq.next(), rosterCatId);
	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), dutyAreaCode);
	populateMAP.put(sq.next(), DutyRosterConfig.ROSTER_AREA_TYPE_FOR_NURSE);
			

	List rosterDetailVoList = new ArrayList();
	ValueObject vo[] = null;
	try
	{
		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		if (rs.next())
		{
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(RosterDtlVO.class, rs);			
			for (ValueObject v : vo)
				rosterDetailVoList.add((RosterDtlVO)v);
		}
	}
	catch (Exception e)
	{
		if (e.getClass() == HisRecordNotFoundException.class)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		else throw new HisDataAccessException("Application Execution Exception" + e);
	}
	return rosterDetailVoList;
}

//for Updating The old Record
public void updateforExchange(RosterDtlVO _rosterDtlVO,UserVO _userVO)
{
	
	   
	   
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "UPDATE_EXCHANGE.HDRT_ROSTER_DTL";

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
		populateMAP.put(sq.next(), _rosterDtlVO.getDutyDone());
		populateMAP.put(sq.next(), _rosterDtlVO.getGeneratedRosterId());
		populateMAP.put(sq.next(), _rosterDtlVO.getEmpId());
		populateMAP.put(sq.next(), _rosterDtlVO.getStartDateTime());
		populateMAP.put(sq.next(), _rosterDtlVO.getEndDateTime());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}

}


public void checkEmpDutyExists(String _emp1Id,String _emp1StartDateTime,String _emp1EndDateTime,String _emp2Id,String _emp2StartDateTime,String _emp2EndDateTime,UserVO _userVO)
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_CHECK_DUTY_EXSISTS.HDRT_ROSTER_DTL";
	ResultSet rs;
	int count=0;
	
	try
	{
		query = HelperMethodsDAO.getQuery(filename, queryKey);
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException(
				"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
	}
	log.info(query);
			
	try
	{
	populateMAP.put(sq.next(), _emp1Id);
	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	populateMAP.put(sq.next(), _emp1StartDateTime);
	populateMAP.put(sq.next(), _emp1EndDateTime);
	
	
	populateMAP.put(sq.next(), _emp2Id);
	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	populateMAP.put(sq.next(), _userVO.getHospitalCode());
	populateMAP.put(sq.next(), _emp2StartDateTime);
	populateMAP.put(sq.next(), _emp2EndDateTime);
	
	
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
	}
	
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(rs.next()){
			count=rs.getInt(1);
			         }
		
		if(count > 1)
			throw new HisDuplicateRecordException("Duty Cannot be Exchanged,Since Duty Already Exists");
	
	}
	catch (Exception e)
	{
		if (e.getClass() == HisDuplicateRecordException.class) 
			throw new HisDuplicateRecordException(e.getMessage());
		else
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}
	
 
	
}



//for Updating The old Record
public void updateShiftTODayOff(RosterDtlVO _rosterForReliver,UserVO _userVO)
{
	
	   
	   
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "UPDATE_SHIFT_TO_DAY_OFF.HDRT_ROSTER_DTL.FOR_RELIVER_ASSIGNMENT";

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
		 
		populateMAP.put(sq.next(), DutyRosterConfig.DAY_OFF_SHIFT_TYPE_CODE); 			 
		populateMAP.put(sq.next(), _rosterForReliver.getGeneratedRosterId());
		populateMAP.put(sq.next(), _rosterForReliver.getEmpId());
		populateMAP.put(sq.next(), _rosterForReliver.getStartDateTime());
		populateMAP.put(sq.next(), _rosterForReliver.getEndDateTime());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	}

}



//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
//For report generation

public RosterDtlVO[] getEmpDailyWorkReport(String _rosterId,String _areaTypeCode,String _areaCode,String _empId,String _workingDate,UserVO _userVO) 
{
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	String queryKey = "SELECT_DAILY_WORK_REPORT.ROSTER_DETAIL.HDRT_ROSTER_DTL";
	
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

		
		
 
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_areaCode );
		populateMAP.put(sq.next(),_areaTypeCode );
		populateMAP.put(sq.next(),_empId );
		populateMAP.put(sq.next(),_workingDate);
		
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
	}
	try
	{
		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		rs.last();
	//	System.out.println("Number of rows=========="+rs.getRow());
		rs.beforeFirst();
		
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

	//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
	//For report generation for Duty Roster
	
	public RosterDtlVO[] fetchAllMappedDesignationWiseDutyRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_ALL_MAPPED_DUTY_REPORT.HDRT_ROSTER_DTL_NEW";
		
		RosterDtlVO[] _rosterDtlVO=null;
		ValueObject[] _valueObjtsVo=null; 
		
		log.info(queryKey);
		
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		query+=  "   AND EXTRACT(month from hdrdt_start_datetime) = "+_month
		       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
		       + " AND EXTRACT(month from hdrdt_start_datetime) = "+_month 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
		       + " ORDER BY empname,  startDateTime ";
		log.info(query);
		try  
		{
			 
			
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
		//	System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			
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

	//Fetching the Record for All  the Designation Mapped Employees whose Roster has been prepared
	//For report generation

	public RosterDtlVO[] fetchAllMappedAreaWiseDutyRosterReport(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_ALL_MAPPED_AREA_DUTY_REPORT.HDRT_ROSTER_DTL";
		
		RosterDtlVO[] _rosterDtlVO=null;
		ValueObject[] _valueObjtsVo=null; 
		
		log.info(queryKey);
		
		ResultSet rs;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		query+=  "AND EXTRACT(month from hdrdt_start_datetime) = "+_month
		       + " AND SUBSTR (TO_CHAR (hdrdt_start_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7,4) = "+_year 
		       + "AND EXTRACT(month from hdrdt_start_datetime) = "+_month 
		       + " AND SUBSTR (TO_CHAR (hdrdt_end_datetime, 'dd-mm-yyyy hh24:mi:ss'), 7, 4) ="+_year
		       + " ORDER BY areaCode,empName, startDateTime ";
		  	   //+ " ORDER BY areaCode,empName, shiftId DESC, startDateTime ";
		log.info(query);
		try  
		{
			 
			
			populateMAP.put(sq.next(),_rosterId);
		//area removed in case all the area is selected
			//	populateMAP.put(sq.next(),_areaCode );
			populateMAP.put(sq.next(),_areaTypeCode );
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
				
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.last();
		//	System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			
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
	
	
	///////////
	public List getReliverDetail(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT.DUTY_ROSTER_REPORT.HDRT_ROSTERWISE_RELIEVERS_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(),_rosterId);
		populateMAP.put(sq.next(),_userVO.getHospitalCode());
		populateMAP.put(sq.next(),_year);
		populateMAP.put(sq.next(),_year);
		populateMAP.put(sq.next(),_month);
		populateMAP.put(sq.next(),_month);
		
		populateMAP.put(sq.next(),_areaCode);
		populateMAP.put(sq.next(),_areaTypeCode);
		populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_STATUS_IS_GENERATED);
		populateMAP.put(sq.next(),_year);
		populateMAP.put(sq.next(),_year);
		populateMAP.put(sq.next(),_month);
		populateMAP.put(sq.next(),_month);
		
				

		List lstRosterReliverDtlVO = new ArrayList();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(RosterReliverDtlVO.class, rs);			
				for (ValueObject v : vo)
					lstRosterReliverDtlVO.add((RosterReliverDtlVO)v);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return lstRosterReliverDtlVO;
	}
}

