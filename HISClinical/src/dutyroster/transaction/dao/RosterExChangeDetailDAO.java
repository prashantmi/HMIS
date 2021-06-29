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
import hisglobal.vo.DutyRosterAreaEmployeeVO; 
import hisglobal.vo.RosterDtlVO;
import hisglobal.vo.RosterExChangeDetailVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.log4j.LogManager; 
import org.apache.log4j.Logger;
import dutyroster.DutyRosterConfig;
import dutyroster.masters.dao.DutyAreaEmployeeMstDAOi;

public class RosterExChangeDetailDAO extends DataAccessObject implements RosterExChangeDetailDAOi{
	
	Logger log;

	public RosterExChangeDetailDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	
	
	
	
	// on Add page for Saving Data
	public void create(RosterExChangeDetailVO _exchangeDtlVO,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "INSERT_EXCHANGE.HDRT_ROSTER_EXCHANGE_DTL";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _exchangeDtlVO.getExchangeForEmp());
			populateMAP.put(sq.next(), _exchangeDtlVO.getRequestStatus());
			populateMAP.put(sq.next(), _exchangeDtlVO.getShiftId());
			populateMAP.put(sq.next(), _exchangeDtlVO.getExchangeType());			
			populateMAP.put(sq.next(), _exchangeDtlVO.getStartDateTime());
			populateMAP.put(sq.next(), _exchangeDtlVO.getEndDateTime());
			populateMAP.put(sq.next(), _exchangeDtlVO.getExchangeByEmp());
			populateMAP.put(sq.next(), _exchangeDtlVO.getGeneratedRosterIdExBy());
			populateMAP.put(sq.next(), _exchangeDtlVO.getGeneratedRosterId());
			populateMAP.put(sq.next(), _exchangeDtlVO.getReason());
			populateMAP.put(sq.next(), _exchangeDtlVO.getShiftIdExBy());			
			populateMAP.put(sq.next(), _exchangeDtlVO.getStartDateTimeExBy());
			populateMAP.put(sq.next(), _exchangeDtlVO.getEndDateTimeExBy());
			populateMAP.put(sq.next(), _exchangeDtlVO.getCancelRemarks());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);			
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			
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
	
	public RosterWiseReliversDtlVO[] fetch(String _generatedRosterId,UserVO _userVO) 
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT.HDRT_ROSTERWISE_RELIEVERS_DTL";
		RosterWiseReliversDtlVO[] empReliverVO=null;
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
			rs.last();
			System.out.println("Number of rows=========="+rs.getRow());
			rs.beforeFirst();
			if(!rs.next()){
		//		 throw new HisRecordNotFoundException("Details Not Found");
						}
			else
			  {
         	   rs.beforeFirst();
         	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterWiseReliversDtlVO.class, rs);
    			
         	  empReliverVO=new RosterWiseReliversDtlVO[_valueObjtsVo.length];
    			
    			
    			for(int i=0; i <_valueObjtsVo.length ; i++)
    				empReliverVO[i]=(RosterWiseReliversDtlVO)_valueObjtsVo[i];
    						       			 			            	   
         	   
			 }	   
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	
	return empReliverVO;
	}
	
	
	
	// for Updating The old Record
	public void update(String _generatedRosterId,UserVO _userVO)
	{
		
		   
		   
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "UPDATE.HDRT_ROSTERWISE_RELIEVERS_DTL";

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
			populateMAP.put(sq.next(), _generatedRosterId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
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

	
	public void duplicateCheckOfReliverOfEmp(RosterWiseReliversDtlVO _reliverRosterVO,UserVO _userVO)  
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_DUPLICATE_RELIVER_CHECK.HDRT_ROSTERWISE_RELIEVERS_DTL";
		
		String empName="";
		
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
			 
			populateMAP.put(sq.next(),_reliverRosterVO.getGeneratedRosterId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),_reliverRosterVO.getEmpId());	
			populateMAP.put(sq.next(),_reliverRosterVO.getFromDate());
			populateMAP.put(sq.next(),_reliverRosterVO.getToDate());
			populateMAP.put(sq.next(),_reliverRosterVO.getFromDate());
			populateMAP.put(sq.next(),_reliverRosterVO.getToDate());
			populateMAP.put(sq.next(),_reliverRosterVO.getFromDate());
			populateMAP.put(sq.next(),_reliverRosterVO.getToDate());
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
			else
			  {         	   
				empName=rs.getString(2);
				 throw new HisDuplicateRecordException(empName+" is Already as a Reliver in Some Roster,So it cannot be Reliver in this Roster");
         	  }	   
		}
		catch (Exception e)
		{
			if (e.getClass() == HisDuplicateRecordException.class) 
				throw new HisDuplicateRecordException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	
	}
	

	public RosterWiseReliversDtlVO[] fetchReliverListForDutyAssignment(String _rosterId,String _shiftId,String _selectedDate,UserVO _userVO)  
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_RELIVERS_LIST.HDRT_ROSTERWISE_RELIEVERS_DTL";
		RosterWiseReliversDtlVO[] empReliverVO=null;
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),_shiftId);
			populateMAP.put(sq.next(),_selectedDate);
			populateMAP.put(sq.next(),_selectedDate);
			populateMAP.put(sq.next(),_selectedDate);
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
			else
			  {
         	   rs.beforeFirst();
         	   _valueObjtsVo=HelperMethods.populateVOfrmRS(RosterWiseReliversDtlVO.class, rs);
    			
         	  empReliverVO=new RosterWiseReliversDtlVO[_valueObjtsVo.length];
    			
    			
    			for(int i=0; i <_valueObjtsVo.length ; i++)
    				empReliverVO[i]=(RosterWiseReliversDtlVO)_valueObjtsVo[i];
    						       			 			            	   
         	   
			 }	   
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) 
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	
	return empReliverVO;
		
		
	}
	
}
