package dutyroster.transaction.dao;




import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods; 
import hisglobal.utility.Sequence;
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterGenerationDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager; 
import org.apache.log4j.Logger;
import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.DutyAreaEmployeeMstDAOi;

public class RosterGenerationDtlDAO extends DataAccessObject implements RosterGenerationDtlDAOi{
	
	Logger log;

	public RosterGenerationDtlDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	
	
	
	
	// on Add page for Saving Data
	public void create(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "INSERT.HDRT_ROSTER_GENERATION_DTL";

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
			 
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getRosterId());
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getAreaCode());
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getAreaTypeCode());
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getEmpId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());			
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getStartDate());
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getEndDate());
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getRosterStatus());
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getGenerationRemarks());
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getCancelRemarks());			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getGeneratedRosterId());	
			populateMAP.put(sq.next(), _rosterGenerationDtlVO.getGeneratedRosterId());
			
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
	
	public int checkDateRange(RosterGenerationDtlVO _rosterGenerationDtlVO,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_CHECK_RANGE.HDRT_ROSTER_GENERATION_DTL";
		int flag=0;
		
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
			 
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getStartDate());
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getEndDate());
			
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getStartDate());
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getEndDate());
			
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getStartDate());
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getEndDate());
			
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getStartDate());
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getEndDate());
			
			
			
			
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getRosterId());
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getAreaCode() );
			populateMAP.put(sq.next(),_rosterGenerationDtlVO.getAreaTypeCode() );
			populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_STATUS_IS_GENERATED );
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
			rs.beforeFirst();
			if(!rs.next()){
		//		 throw new HisRecordNotFoundException("Details Not Found");
			               }
				else	
					flag=rs.getInt(1);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	
	return flag;
	}
	
	
	//Fetching the Record for the Range of dates for which the  roster has been Generated
	
	public RosterGenerationDtlVO[] fetch(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_RANGE.HDRT_ROSTER_GENERATION_DTL";
		RosterGenerationDtlVO[] _rangeOfRosterGenerationDtlVO=null;
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
			populateMAP.put(sq.next(),_areaCode);
			populateMAP.put(sq.next(),_areaTypeCode);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_STATUS_IS_GENERATED);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				
			if(!rs.next()){
		//	 throw new HisRecordNotFoundException("No Roster Found ");
			               }else
			             {
			            	   rs.beforeFirst();
			            	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterGenerationDtlVO.class, rs);
			       			
			            	   _rangeOfRosterGenerationDtlVO=new RosterGenerationDtlVO[_valueObjtsVo.length];
			       			
			       			
			       			for(int i=0; i <_valueObjtsVo.length ; i++)
			       				_rangeOfRosterGenerationDtlVO[i]=(RosterGenerationDtlVO)_valueObjtsVo[i];
			       						       			 			            	   
			            	   
			             }	   
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	
	
	return _rangeOfRosterGenerationDtlVO;
	}

	

	//Fetching the Record for the Range of dates for which the  roster has been Generated
	
	public RosterGenerationDtlVO[] fetchForAllMappedAreas(String _year,String _month,String _areaTypeCode,String _rosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_RANGE_ALL_MAPPED_AREAS.HDRT_ROSTER_GENERATION_DTL";
		RosterGenerationDtlVO[] _rangeOfRosterGenerationDtlVO=null;
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
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_STATUS_IS_GENERATED);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				
			if(!rs.next()){
		//	 throw new HisRecordNotFoundException("No Roster Found ");
			               }else
			             {
			            	   rs.beforeFirst();
			            	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterGenerationDtlVO.class, rs);
			       			
			            	   _rangeOfRosterGenerationDtlVO=new RosterGenerationDtlVO[_valueObjtsVo.length];
			       			
			       			
			       			for(int i=0; i <_valueObjtsVo.length ; i++)
			       				_rangeOfRosterGenerationDtlVO[i]=(RosterGenerationDtlVO)_valueObjtsVo[i];
			       						       			 			            	   
			            	   
			             }	   
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	
	
	return _rangeOfRosterGenerationDtlVO;
	}
	
//Fetching the Record for the Range of dates for which the  roster has been Generated
	
	public RosterGenerationDtlVO[] fetchMonthlyForAllRosters(String _year,String _month,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_ESSENTIALDAO;
		String queryKey = "SELECT_MONTHLY.HDRT_ROSTER_GENERATION_DTL";
		RosterGenerationDtlVO[] _rangeOfRosterGenerationDtlVO=null;
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
			 
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_month);
			populateMAP.put(sq.next(),_year);
			populateMAP.put(sq.next(),_month);			
			populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_STATUS_IS_GENERATED);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
				
			
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
			            	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterGenerationDtlVO.class, rs);
			       			
			            	   _rangeOfRosterGenerationDtlVO=new RosterGenerationDtlVO[_valueObjtsVo.length];
			       			
			       			
			       			for(int i=0; i <_valueObjtsVo.length ; i++)
			       				_rangeOfRosterGenerationDtlVO[i]=(RosterGenerationDtlVO)_valueObjtsVo[i];
			       						       			 			            	   
			            	   
			             }	   
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	
	
	return _rangeOfRosterGenerationDtlVO;
	}
	
	// for Updating The old Record
	public void update(String _year,String _month,String _areaTypeCode,String _areaCode,String _rosterId,UserVO _userVO)
	{
		
		   
		   
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "UPDATE_DROP_ROSTER.HDRT_ROSTER_GENERATION_DTL";

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
			 
			populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_STATUS_IS_CANCELLED);
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),_rosterId);
			populateMAP.put(sq.next(),_areaCode);
			populateMAP.put(sq.next(),_areaTypeCode);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_STATUS_IS_GENERATED);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

	}

//Fetching the Record for the Range of dates for which the  roster has been Generated
	
	public RosterGenerationDtlVO[] fetchForAllRostersOfaCategoryAreaWise(String _year,String _month,String _areaCode,String _areaTypeCode,String _rosterCatg,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_RANGE_ALL_ROSTERS_ROSTER_CATEG_AREAWISE.HDRT_ROSTER_GENERATION_DTL";
		RosterGenerationDtlVO[] _rangeOfRosterGenerationDtlVO=null;
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
			 
			populateMAP.put(sq.next(),_rosterCatg);
			populateMAP.put(sq.next(),_areaCode);
			populateMAP.put(sq.next(),_areaTypeCode);			
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),DutyRosterConfig.ROSTER_STATUS_IS_GENERATED);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
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
				
			if(!rs.next()){
		//	 throw new HisRecordNotFoundException("No Roster Found ");
			               }else
			             {
			            	   rs.beforeFirst();
			            	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterGenerationDtlVO.class, rs);
			       			
			            	   _rangeOfRosterGenerationDtlVO=new RosterGenerationDtlVO[_valueObjtsVo.length];
			       			
			       			
			       			for(int i=0; i <_valueObjtsVo.length ; i++)
			       				_rangeOfRosterGenerationDtlVO[i]=(RosterGenerationDtlVO)_valueObjtsVo[i];
			       						       			 			            	   
			            	   
			             }	   
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	
	
	return _rangeOfRosterGenerationDtlVO;
	}
		



}
