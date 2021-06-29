package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.RosterShiftMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dutyroster.DutyRosterConfig;


public class RosterShiftMstDAO extends DataAccessObject implements RosterShiftMstDAOi {

	Logger log;

	public RosterShiftMstDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data
	public List getRosterType(UserVO userVO) {
	
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List rosterTypeList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
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
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterShiftMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rosterTypeList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("All Rosters have been Mapped Please use Modify");
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

		return rosterTypeList;
	}
	
	public List getShiftsBasedOnRoster(String rosterId,String shiftType,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List shiftEssentialList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.SHIFT_ESSENTIAL.HDRT_SHIFT_MST";
		
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
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), shiftType);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
					
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterShiftMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				shiftEssentialList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
				throw new HisRecordNotFoundException("All Shifts Have Been Mapped");
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
		
		return shiftEssentialList;
	}
	
	
	public void saveRosterShift(RosterShiftMstVO rosterShiftVO ,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_ROSTTYPE_SHIFT_MST";
		
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
			populateMAP.put(sq.next(), rosterShiftVO.getRosterTypeId());
			populateMAP.put(sq.next(), rosterShiftVO.getShiftId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), rosterShiftVO.getRosterTypeId());
			populateMAP.put(sq.next(), rosterShiftVO.getShiftId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterShiftMstDAO.populateMAP::" + e);
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
	
	public List getRosterShift(RosterShiftMstVO rosterShiftVO ,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_ROSTTYPE_SHIFT_MST";
		List rosterShift=new ArrayList();
		
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
			populateMAP.put(sq.next(), rosterShiftVO.getRosterTypeId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			//populateMAP.put(sq.next(), rosterShiftVO.getSerialNo());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterShiftMstDAO.populateMAP::" + e);
		}
		try
		{
			int i=0;
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rosterShift=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return rosterShift;
		
	}
	
	public List getShiftsBasedOnRosterModify(String rosterId,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		List shiftEssentialList=new ArrayList();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.SHIFT_ESSENTIAL.HDRT_SHIFT_MST.FOR_MODIFY";
		
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
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), rosterId);
			
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		//	populateMAP.put(sq.next(), shiftType);
			
			//in case we have 24x7 roster ,day shift type will not be there
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
			
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMAP.put(sq.next(), rosterId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterShiftMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				shiftEssentialList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			else{
			//	throw new HisRecordNotFoundException("Shift Record Not Found");
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
		
		return shiftEssentialList;
	}
	
	
	public void modifyRosterShift(RosterShiftMstVO rosterShiftVO ,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE.HDRT_ROSTTYPE_SHIFT_MST";
		
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
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), rosterShiftVO.getRosterTypeId());
			populateMAP.put(sq.next(), rosterShiftVO.getSelectedShiftId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), rosterShiftVO.getRosterTypeId());
			populateMAP.put(sq.next(), rosterShiftVO.getSelectedShiftId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterShiftMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate" + e);
		}
		
	}
	
	
	public void modifyInsertRosterShift(RosterShiftMstVO rosterShiftVO ,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.MODIFY.HDRT_ROSTTYPE_SHIFT_MST";
		
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
			populateMAP.put(sq.next(), rosterShiftVO.getRosterTypeId());
			populateMAP.put(sq.next(), rosterShiftVO.getSelectedShiftId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), rosterShiftVO.getRosterTypeId());
			populateMAP.put(sq.next(), rosterShiftVO.getSelectedShiftId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), rosterShiftVO.getIsActive());
			populateMAP.put(sq.next(), userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterShiftMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate" + e);
		}
		
	}
	
	public String getRosterTypeNameById(String rosterTypeId ,UserVO userVO) {
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.ROSTER_NAME_BY_ID.HDRT_ROSTER_TYPE_MST";
		String rosterTypeName="";
		
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
			populateMAP.put(sq.next(), rosterTypeId);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("RosterShiftMstDAO.populateMAP::" + e);
		}
		try
		{
			int i=0;
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rosterTypeName=rs.getString(1);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return rosterTypeName;
		
	}
	
}
