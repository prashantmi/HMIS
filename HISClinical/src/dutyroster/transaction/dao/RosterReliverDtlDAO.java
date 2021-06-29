package dutyroster.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.RosterReliverDtlVO;
import hisglobal.vo.RosterWiseReliversDtlVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import dutyroster.DutyRosterConfig;

public class RosterReliverDtlDAO extends DataAccessObject implements RosterReliverDtlDAOi{
	


	public RosterReliverDtlDAO(TransactionContext _tx) {
		super(_tx);
		
	}
	
	public void create(RosterReliverDtlVO _rosterReliverDtlVO,UserVO _userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename=DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
	    String queryKey="INSERT.HDRT_ROSTER_RELIVER_DTL"; 
	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	   
	    try{
	    	Sequence sq=new Sequence();
			
		
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getSerialNo());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getReliverEmp());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getRequestStatus());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getShiftId());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getReliverMode());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getStartDateTime());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getEndDateTime());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getReliverId());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getGeneratedRosterId());
			populateMAP.put(sq.next(), _rosterReliverDtlVO.getReason());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
	    }
	    catch(Exception e){
	    	throw new HisDataAccessException("DutyRoleDetailDAO:populateMap(_dutyRoleDetailVO,populateMAP)::"+e);
	    }
	    try{
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e){
	    	throw new HisDataAccessException("INSERT FAILED:::create DutyRoleDetailDAO.getResultset"+e);
	    }
	}
	
	
	public int getMaxSNo(RosterReliverDtlVO _rosterReliverDtlVO,UserVO _userVO)  
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_TRANSACTIONDAO;
		String queryKey = "SELECT_MAX_SNO.HDRT_ROSTER_RELIVER_DTL";
		
		int serialNo=0;
		
		ResultSet rs;
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
			 
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterDtlDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
			
			
		if(rs.next()){
			
			serialNo=Integer.parseInt(rs.getString(1));
			
			System.out.println("serialNo in dao-----"+serialNo);
		
		
					}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisDuplicateRecordException.class) 
				throw new HisDuplicateRecordException(e.getMessage());
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
		
	return serialNo;
	}
				
		


}
