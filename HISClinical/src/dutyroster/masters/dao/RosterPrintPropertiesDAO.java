package dutyroster.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.BlockAreaMstVO;
import hisglobal.vo.DutyRosterPrintPropertiesVO;
import hisglobal.vo.RosterShiftMstVO;
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


public class RosterPrintPropertiesDAO extends DataAccessObject implements RosterPrintPropertiesDAOi {

	Logger log;

	public RosterPrintPropertiesDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}

	// on Add page for Saving Data
	
	
	public void create(DutyRosterPrintPropertiesVO _rosterPrintMstVO, UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "INSERT.HDRT_ROSTER_PRINT_PROPERTIES";
		
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
			populateMAP.put(sq.next(),_rosterPrintMstVO.getRosterType());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),_rosterPrintMstVO.getRosterType());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),_rosterPrintMstVO.getPrintSequence());
			populateMAP.put(sq.next(),_rosterPrintMstVO.getDisplayValue());
			populateMAP.put(sq.next(),_rosterPrintMstVO.getPropertyType());
			populateMAP.put(sq.next(),_rosterPrintMstVO.getDisplayOrder());
			populateMAP.put(sq.next(),_rosterPrintMstVO.getAlign());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query, populateMAP);
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


		
	}

	
	
	
	public DutyRosterPrintPropertiesVO[] fetch(String rosterType, UserVO _userVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "SELECT.HDRT_ROSTER_PRINT_PROPERTIES";
		DutyRosterPrintPropertiesVO[]  _rosterPrintVO=null;
		ValueObject[] _valueObjects=null;
		
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
			populateMAP.put(sq.next(), rosterType);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
		if(!rs.next()){
			//	throw new HisRecordNotFoundException("No Instructions / Roster By / Copy To Found for the Roster Type Selected");
			}
			else{
				rs.beforeFirst();
				_valueObjects=HelperMethods.populateVOfrmRS(DutyRosterPrintPropertiesVO.class,rs);
				
				_rosterPrintVO=new DutyRosterPrintPropertiesVO[_valueObjects.length];
				
				for(int i= 0 ;i < _valueObjects.length ; i++)
					_rosterPrintVO[i]=(DutyRosterPrintPropertiesVO)_valueObjects[i];
				
				
				
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
		return _rosterPrintVO;
	}
	
	
	
	public void update(String rosterType,UserVO _UserVO){
		
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
		String queryKey = "UPDATE_ISVALID.HDRT_ROSTER_PRINT_PROPERTIES";
		
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
			populateMAP.put(sq.next(), rosterType);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
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
	
	
public void changeDisplayOrder(DutyRosterPrintPropertiesVO _rosterPrintMstVO,UserVO _userVO){
		
	ResultSet rs;
	String query = "";
	Map populateMAP = new HashMap();
	Sequence sq = new Sequence();
	String filename = DutyRosterConfig.QUERY_FILE_FOR_DUTY_ROSTER_MASTERSDAO;
	String queryKey = "UPDATE_HDRNUM_DISPLAY_ORDER.HDRT_ROSTER_PRINT_PROPERTIES";
	
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
		populateMAP.put(sq.next(),_rosterPrintMstVO.getDisplayOrder());
		populateMAP.put(sq.next(),_rosterPrintMstVO.getRosterType());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());		
		populateMAP.put(sq.next(),_rosterPrintMstVO.getSerialNo());
		populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
				
	}
	catch (Exception e)
	{
		throw new HisApplicationExecutionException("BlockAreaMstDAO.populateMAP::" + e);
	}
	
	
	try
	{
		HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query, populateMAP);
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
 }


}
