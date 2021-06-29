package opd.master.dao;

/**
 * @author  CDAC
 */

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserDeskUnitWardMappingMasterVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserDeskUnitWardMappingMasterDAO extends DataAccessObject
{
	Logger log;
	
	public UserDeskUnitWardMappingMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log=LogManager.getLogger(this.getClass());
	}
	
	//* Inserting UserDeskMenu Record
	public void create(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT_UNIT_WARD_MAPPING_MASTER.GBLT_USER_DESKMENU_MST";

        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getDeptUnitCode());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getDeskId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getIsValid());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getDeskType());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}
	
	
	//* Inserting UserDeskMenu Record both type(Unit Ward and Unit Ward Seat Wise)
	public void createUnitWardWise(UserDeskUnitWardMappingMasterVO _UserDeskUnitWardMappingVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT_UNIT_WARD_MAPPING_MASTER_UNIT_WARD_WISE.GBLT_USER_DESKMENU_MST";

        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getUserSeatId());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getWardCode());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getDeptUnitCode());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getDeskId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getIsValid());
        	populateMAP.put(sq.next(),_UserDeskUnitWardMappingVO.getDeskType());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}


	
	

	//* Fetching User Desk Unit Ward Mapping Record
	public UserDeskUnitWardMappingMasterVO fetchRecordForUnitWise(UserDeskUnitWardMappingMasterVO _UserDeskVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        UserDeskUnitWardMappingMasterVO _voUsrDeskUnitWardMappingMaster=new UserDeskUnitWardMappingMasterVO();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.UNIT_WISE_FULL_INFO.GBLT_USER_DESKMENU_MST";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskVO.getUserDeskMenuId());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
			rs.beforeFirst();
			while(rs.next())
			{
				_voUsrDeskUnitWardMappingMaster.setUserDeskMenuId(rs.getString(1));
			//	_voUsrDeskUnitWardMappingMaster.setSeatName(rs.getString(2));
				_voUsrDeskUnitWardMappingMaster.setUnitName(rs.getString(2));
				_voUsrDeskUnitWardMappingMaster.setDeskName(rs.getString(3));
			//	_voUsrDeskUnitWardMappingMaster.setWardName(rs.getString(4));
				_voUsrDeskUnitWardMappingMaster.setDeskType(rs.getString(5));
				
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
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
        return _voUsrDeskUnitWardMappingMaster;   
	}
	
	
	//* Fetching User Desk Unit Ward Mapping Record
	public UserDeskUnitWardMappingMasterVO fetchRecordForUnitWardWise(UserDeskUnitWardMappingMasterVO _UserDeskVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        UserDeskUnitWardMappingMasterVO _voUsrDeskUnitWardMappingMaster=new UserDeskUnitWardMappingMasterVO();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.FULL_INFO_UNIT_WARD_WISE.GBLT_USER_DESKMENU_MST";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
          	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_UserDeskVO.getUserDeskMenuId());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
			rs.beforeFirst();
			while(rs.next())
			{
				_voUsrDeskUnitWardMappingMaster.setUserDeskMenuId(rs.getString(1));
			//	_voUsrDeskUnitWardMappingMaster.setSeatName(rs.getString(2));
				_voUsrDeskUnitWardMappingMaster.setUnitName(rs.getString(2));
				_voUsrDeskUnitWardMappingMaster.setDeskName(rs.getString(3));
				_voUsrDeskUnitWardMappingMaster.setWardName(rs.getString(4));
				_voUsrDeskUnitWardMappingMaster.setDeskType(rs.getString(5));
				
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
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
       
        return _voUsrDeskUnitWardMappingMaster;   
	}
	
	//* Fetching User Desk Unit Ward Mapping Record
	public UserDeskUnitWardMappingMasterVO fetchRecordForUnitWardSeatWise(UserDeskUnitWardMappingMasterVO _UserDeskVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        UserDeskUnitWardMappingMasterVO _voUsrDeskUnitWardMappingMaster=new UserDeskUnitWardMappingMasterVO();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.FULL_INFO_UNIT_WARD_SEAT_WISE.GBLT_USER_DESKMENU_MST";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),_UserDeskVO.getUserDeskMenuId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserDeskVO.getIsValid());
        
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
			rs.beforeFirst();
			while(rs.next())
			{
				_voUsrDeskUnitWardMappingMaster.setUserDeskMenuId(rs.getString(1));
				_voUsrDeskUnitWardMappingMaster.setDeptUnitCode(rs.getString(2));
				_voUsrDeskUnitWardMappingMaster.setDeskId(rs.getString(3));
				_voUsrDeskUnitWardMappingMaster.setUserSeatId(rs.getString(4));
				_voUsrDeskUnitWardMappingMaster.setIsValid(rs.getString(5));
				_voUsrDeskUnitWardMappingMaster.setDeskType(rs.getString(6));
				_voUsrDeskUnitWardMappingMaster.setWardCode(rs.getString(7));
				
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
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
       
        
        return _voUsrDeskUnitWardMappingMaster;   
	}
	
	
	//* Updating User Desk Unit Ward Mapping Master Record
	public void update(UserDeskUnitWardMappingMasterVO _UserDeskMenuVO, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE_UNIT_WARD_SEAT_MAPPING_MASTER.GBLT_USER_DESKMENU_MST";

        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeskType());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getIsValid());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getUserSeatId());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getDeptUnitCode());
        	populateMAP.put(sq.next(),_UserDeskMenuVO.getWardCode());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        
	}

	
}