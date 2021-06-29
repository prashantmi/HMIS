package opd.master.dao;

/**
 * @author  CDAC
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hisglobal.persistence.TransactionContext;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import opd.OpdConfig;
import registration.RegistrationConfig;

public class UserDeskMenuTemplateMasterDAO extends DataAccessObject 
{
	Logger log;
	
	public UserDeskMenuTemplateMasterDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	//* Inserting User Desk Menu Template Record
	/*public void create(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_USER_DESKMENU_TEMPLATE.UNIT_WISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getDeptUnitCode());
        	populateMAP.put(sq.next(),_voUDMT.getUserSeatId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
           	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
           	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_voUDMT.getIsDefault());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
          	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}*/

	public  UserDeskMenuTemplateMasterVO getRecords(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMap =new HashMap();
		UserDeskMenuTemplateMasterVO voUDMT = new UserDeskMenuTemplateMasterVO();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey="SELECT.GBLT_USER_DESKMENU_TEMPLATE";

		Sequence sq=new Sequence();
		
		try
		{
			populateMap.put(sq.next(),_voUDMT.getSlNo());
			populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_userVO.getHospitalCode());
			populateMap.put(sq.next(),_voUDMT.getDeskMenuId());
			populateMap.put(sq.next(),_voUDMT.getTemplateId());
			populateMap.put(sq.next(),_voUDMT.getDeskId());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
		}
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		System.out.println("   -------> query :: "+query);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
			rs.beforeFirst();
			while(rs.next())
			{
				voUDMT.setSlNo(rs.getString(1));
				voUDMT.setDeskId(rs.getString(2));
				voUDMT.setDeskMenuId(rs.getString(3));
				voUDMT.setUserSeatId(rs.getString(4));
				voUDMT.setDeptUnitCode(rs.getString(5));
				voUDMT.setIsDefault(rs.getString(6));
				voUDMT.setTemplateId(rs.getString(7));
				voUDMT.setWardCode(rs.getString(8));
				voUDMT.setHospCode(rs.getString(9));
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
		return voUDMT;
	}
	
	public  UserDeskMenuTemplateMasterVO getRecordsForWard(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _userVO)
	{
		ResultSet rs=null;
		String query =  "" ;
		Map populateMap =new HashMap();
		UserDeskMenuTemplateMasterVO voUDMT = new UserDeskMenuTemplateMasterVO();
		
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey="SELECT_WARDUNITMODE.GBLT_USER_DESKMENU_TEMPLATE";

		Sequence sq=new Sequence();
		
		try
		{
			populateMap.put(sq.next(),_voUDMT.getSlNo());
			populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(),_userVO.getHospitalCode());
			populateMap.put(sq.next(),_voUDMT.getDeskMenuId());
			populateMap.put(sq.next(),_voUDMT.getTemplateId());
			populateMap.put(sq.next(),_voUDMT.getDeskId());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("UnitMasterDAO:populateMap::"+e);
		}
		
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
		}
		
		System.out.println("   -------> query :: "+query);
		
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
					
			if (!rs.next())
			{
			throw new HisRecordNotFoundException();
			}
			else
			{
			rs.beforeFirst();
			while(rs.next())
			{
				voUDMT.setSlNo(rs.getString(1));
				voUDMT.setDeskId(rs.getString(2));
				voUDMT.setDeskMenuId(rs.getString(3));
				voUDMT.setWardCode(rs.getString(4));
				voUDMT.setDeptUnitCode(rs.getString(5));
				voUDMT.setIsDefault(rs.getString(6));
				voUDMT.setTemplateId(rs.getString(7));
				voUDMT.setHospCode(rs.getString(8));
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
		return voUDMT;
	}
	
	//* Inserting User Desk Menu Template Record Unit Wise
	public void createUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_USER_DESKMENU_TEMPLATE.UNIT_WISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeptUnitCode());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_voUDMT.getIsDefault());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	//* Inserting User Desk Menu Template Record Unit Wise
	public void createDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_USER_DESKMENU_TEMPLATE.DESK_WISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_voUDMT.getIsDefault());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	//* Inserting User Desk Menu Template Record UnitSeat Wise Wise
	public void createSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_USER_DESKMENU_TEMPLATE.SEAT_WISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeptUnitCode());
        	populateMAP.put(sq.next(),_voUDMT.getUserSeatId());
            populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
           	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_voUDMT.getIsDefault());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}

	public void createWardSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_USER_DESKMENU_TEMPLATE.WARDSEAT_WISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeptUnitCode());
        	populateMAP.put(sq.next(),_voUDMT.getUserSeatId());
        	populateMAP.put(sq.next(),_voUDMT.getWardCode());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
           	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_voUDMT.getIsDefault());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public void createWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_USER_DESKMENU_TEMPLATE.WARD_WISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeptUnitCode());
        	populateMAP.put(sq.next(),_voUDMT.getDeskId());
        	populateMAP.put(sq.next(),_voUDMT.getDeskMenuId());
        	populateMAP.put(sq.next(),_voUDMT.getTemplateId());
        	populateMAP.put(sq.next(),_voUDMT.getWardCode());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_voUDMT.getIsDefault());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        }
        catch(Exception e){
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }
        try{
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}

	
	//* Deleting User Desk Menu Template Record 
	public void delete(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();

        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.GBLT_USER_DESKMENU_TEMPLATE";

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
        	populateMAP.put(sq.next(),_voUDMT.getSlNo());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }

        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}

	//* Deleting User Desk Menu Template Record 
	public void deleteSeatWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();

        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.GBLT_USER_DESKMENU_TEMPLATE_SEATWISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getSlNo());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }

        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public void deleteUnitWardWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();

        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.GBLT_USER_DESKMENU_TEMPLATE_UNITWARDWISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getSlNo());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }

        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}

	//* Deleting User Desk Menu Template Record 
	public void deleteDeskWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();

        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.GBLT_USER_DESKMENU_TEMPLATE_DESKWISE";

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
        	populateMAP.put(sq.next(),_voUDMT.getSlNo());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
        }

        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}

	
//* Deleting User Desk Menu Template Record (Unit Wise)
public void deleteUnitWise(UserDeskMenuTemplateMasterVO _voUDMT, UserVO _UserVO)
{
	String query  = "";
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();

    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
    String queryKey="DELETE.GBLT_USER_DESKMENU_TEMPLATE_UNITWISE";

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
    	populateMAP.put(sq.next(),_voUDMT.getSlNo());
    	
    }
    catch(Exception e)
    {
    	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
    }

    try
    {
    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }
}

public UserDeskMenuTemplateMasterVO[] getTemplateByDeskType(String deskId,UserVO userVO)
{
	UserDeskMenuTemplateMasterVO[] arrUserDeskMenuTempVO=null;
	ValueObject vo[]= null;
	String query  = "";
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
    String queryKey="SELECT_MENU_TEMPLATE_BY_DESK.GBLT_USER_DESKMENU_TEMPLATE";
    
    try
    {
    	query =HelperMethodsDAO.getQuery(filename,queryKey);
    }
    catch(Exception e)
    {
    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
    }
    
    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
    populateMAP.put(sq.next(), deskId );
    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
    populateMAP.put(sq.next(), userVO.getHospitalCode());
    
    
    try
 	{
 		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
 		/* if(!rs.next())
 		 * {
 		 * throw new HisRecordNotFoundException("No Record Found");
 		 * }*/
	 	   
		if (rs.next())
		{
			rs.beforeFirst();
			vo=HelperMethods.populateVOfrmRS(UserDeskMenuTemplateMasterVO.class, rs);
			arrUserDeskMenuTempVO= new UserDeskMenuTemplateMasterVO[vo.length];
			for(int i=0;i<vo.length;i++)
	 	    	arrUserDeskMenuTempVO[i]= (UserDeskMenuTemplateMasterVO) vo[i];
		}
	}
	catch(Exception e)
	{
		if(e.getClass()==HisRecordNotFoundException.class)
			throw new HisRecordNotFoundException(e.getMessage());
		else
			throw new HisDataAccessException("Application Execution Exception"+e);
	}
	return arrUserDeskMenuTempVO;
}

//* Inserting User Desk Menu Template Record Desk Wise
public void saveForDeskId(UserDeskMenuTemplateMasterVO userDeskMenuDeskVO, UserVO userVO)
{
	
	String query  = "";
    Map populateMAP =new HashMap();
    Sequence sq=new Sequence();
    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
    String queryKey="INSERT_BY_DESK_GBLT_USER_DESKMENU_TEMPLATE";

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
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeskId());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeskMenuId());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getTemplateId());
    	populateMAP.put(sq.next(),userVO.getHospitalCode());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeskId());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeskMenuId());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getTemplateId());
    	populateMAP.put(sq.next(),userVO.getHospitalCode());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getUserSeatId());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getUnitCode());
    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
    	populateMAP.put(sq.next(),userVO.getSeatId());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getIsDefault());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getWardCode());
    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDisplayOrder());
    }
    catch(Exception e)
    {
    	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
    }
    
    try
    {
    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
    }
}

	public void deleteForDeskId(String deskId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="DELETE_BY_DESK_GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), deskId);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	}
	
	//* Inserting User Desk Menu Template Record Unit Wise
	public void saveForUnit(UserDeskMenuTemplateMasterVO userDeskMenuDeskVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="INSERT_BY_UNIT_GBLT_USER_DESKMENU_TEMPLATE";

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
	    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeskId());
	    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeskMenuId());
	    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getTemplateId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeskMenuId());
	    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getTemplateId());
	    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeskId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(),userDeskMenuDeskVO.getDeptUnitCode());
	    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), userDeskMenuDeskVO.getIsDefault());
	    	
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
	    }
	    try{
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	}
	
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskNUnit(String unitCode,String deskId,UserVO userVO)
	{
		UserDeskMenuTemplateMasterVO[] arrUserDeskMenuTempVO=null;
		ValueObject vo[]= null;
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="SELECT_MENU_TEMPLATE_BY_DESKNUNIT.GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);	    
	    populateMAP.put(sq.next(), deskId);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), userVO.getHospitalCode());
	    populateMAP.put(sq.next(), unitCode);
	    
	    try
	 	{
	 		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
			/* if(!rs.next())
		 	    {
		 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
		 	    }*/
		
			if (rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(UserDeskMenuTemplateMasterVO.class, rs);
				arrUserDeskMenuTempVO= new UserDeskMenuTemplateMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
					arrUserDeskMenuTempVO[i]= (UserDeskMenuTemplateMasterVO) vo[i];
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("Application Execution Exception"+e);
		}
		return arrUserDeskMenuTempVO;
	}
	
	public void deleteForDeskNUnit(String unitCode,String deskId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="DELETE_BY_DESKNUNIT_GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), deskId);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), unitCode);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	}
	
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNSeat(String seatId,String unitCode,String deskId,UserVO userVO)
	{
		UserDeskMenuTemplateMasterVO[] arrUserDeskMenuTempVO=null;
		ValueObject vo[]= null;
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="SELECT_MENU_TEMPLATE_BY_DESKUNIT_N_SEAT.GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), deskId);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), userVO.getHospitalCode());
	    populateMAP.put(sq.next(), unitCode);
	    populateMAP.put(sq.next(), seatId);
	    
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	 		/* if(!rs.next())
		 	    {
		 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
		 	    }*/

			if (rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(UserDeskMenuTemplateMasterVO.class, rs);
				arrUserDeskMenuTempVO= new UserDeskMenuTemplateMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
					arrUserDeskMenuTempVO[i]= (UserDeskMenuTemplateMasterVO) vo[i];
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("Application Execution Exception"+e);
		}
		return arrUserDeskMenuTempVO;
	}
	
	public void deleteForDeskUnitNSeat(String seatId,String unitCode,String deskId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="DELETE_BY_DESK_UNIT_N_SEAT_GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), deskId);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), unitCode);
	    	populateMAP.put(sq.next(), seatId);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	}
	
	
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWard(String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		UserDeskMenuTemplateMasterVO[] arrUserDeskMenuTempVO=null;
		ValueObject vo[]= null;
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="SELECT_MENU_TEMPLATE_BY_DESKUNIT_N_WARD.GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), deskId);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), userVO.getHospitalCode());
	    populateMAP.put(sq.next(), unitCode);
	    populateMAP.put(sq.next(), wardCode);
	    
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
			/* if(!rs.next())
				{
					throw new HisRecordNotFoundException("No Record Found");	 	    
				}*/
			if (rs.next())
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(UserDeskMenuTemplateMasterVO.class, rs);
				arrUserDeskMenuTempVO= new UserDeskMenuTemplateMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
					arrUserDeskMenuTempVO[i]= (UserDeskMenuTemplateMasterVO) vo[i];
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("Application Execution Exception"+e);
		}
		return arrUserDeskMenuTempVO;
	}
	
	public void deleteForDeskUnitNWard(String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="DELETE_BY_DESK_UNIT_N_WARD_GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), deskId);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), unitCode);
	    	populateMAP.put(sq.next(), wardCode);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	}
	
	public UserDeskMenuTemplateMasterVO[] getTemplateByDeskUnitNWardNSeat(String seatId,String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		UserDeskMenuTemplateMasterVO[] arrUserDeskMenuTempVO=null;
		ValueObject vo[]= null;
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="SELECT_MENU_TEMPLATE_BY_DESKUNIT_N_WARD_N_SEAT.GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), deskId);
	    populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    populateMAP.put(sq.next(), userVO.getHospitalCode());
	    populateMAP.put(sq.next(), unitCode);
	    populateMAP.put(sq.next(), wardCode);
	    populateMAP.put(sq.next(), seatId);
	    
	    try
	 	{
	 		ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	 
	 		/* if(!rs.next())
		 	    {
		 	    	throw new HisRecordNotFoundException("No Record Found");	 	    
		 	    }*/
	 		if(rs.next())
	 		{
	 			rs.beforeFirst();
	 			vo=HelperMethods.populateVOfrmRS(UserDeskMenuTemplateMasterVO.class, rs);
	 			arrUserDeskMenuTempVO = new UserDeskMenuTemplateMasterVO[vo.length];
				for(int i=0;i<vo.length;i++)
					arrUserDeskMenuTempVO[i]= (UserDeskMenuTemplateMasterVO) vo[i];
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("Application Execution Exception"+e);
		}
		return arrUserDeskMenuTempVO;
	}
	
	public void deleteForDeskUnitNWardNSeat(String seatId,String wardCode,String unitCode,String deskId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="DELETE_BY_DESK_UNIT_N_WARD_N_SEAT_GBLT_USER_DESKMENU_TEMPLATE";
	    
	    try
	    {
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    
	    try
	    {
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	populateMAP.put(sq.next(), deskId);
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), unitCode);
	    	populateMAP.put(sq.next(), wardCode);
	    	populateMAP.put(sq.next(), seatId);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("UserDeskMenuTemplateMasterDAO.populateMAP::"+e);
	    }
	    try
	    {
	    	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
	    }
	}
	
	// Getting All Non-Added Clinical Units List in Dept (Unit) form for Unit-Wise Mode
	public List getNotAddedClinicalUnits(String _deskId, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "ESSENTIAL.ALL_CLINICAL_UNIT_NOT_ADDED.GBLT_USER_DESKMENU_TEMPLATE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
			populateMAP.put(sq.next(), _deskId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	// Getting All Non-Added Seats List for Unit Seat-Wise Mode
	public List getNotAddedSeatsUnitSeatWise(String _deskId, List<Entry> _units, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		//String queryKey = "ESSENTIAL.ALL_SEATS_NOT_ADDED_UNITSEATWISE.GBLT_USER_DESKMENU_TEMPLATE";
		String queryKey = "ESSENTIAL.ALL_USERS_NOT_ADDED_UNITUSERWISE.GBLT_USER_DESKMENU_TEMPLATE";		

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			String units = "";
			for(Entry entUnit : _units)
				units += entUnit.getValue() + ",";
			if(!units.equals(""))	units = units.substring(0,units.length()-1);
			
			query = query.replace("#", units);
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _deskId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}

	// Getting All Non-Added Seats List for Unit Ward Seat-Wise Mode
	public List getNotAddedSeatsUnitWardSeatWise(String _deskId, List<Entry> _units, List<Entry> _wards, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		//String queryKey = "ESSENTIAL.ALL_SEATS_NOT_ADDED_UNITWARDSEATWISE.GBLT_USER_DESKMENU_TEMPLATE";
		String queryKey = "ESSENTIAL.ALL_USERS_NOT_ADDED_UNITWARDUSERWISE.GBLT_USER_DESKMENU_TEMPLATE";
		

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		log.error(query + "\n");

		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();

		try
		{
			String units = "";
			for(Entry entUnit : _units)
				units += entUnit.getValue() + ",";
			if(!units.equals(""))	units = units.substring(0,units.length()-1);

			String wards = "";
			for(Entry entWard : _wards)
				wards += entWard.getValue() + ",";
			if(!wards.equals(""))	wards = wards.substring(0,wards.length()-1);
			
			query = query.replace("#", units);
			
			query = query.replace("%", wards);
			
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _deskId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getAllUnitList" + e);
		}
		return alRecord;
	}
}