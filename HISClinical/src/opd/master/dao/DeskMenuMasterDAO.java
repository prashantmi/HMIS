package opd.master.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.UserVO;

public class DeskMenuMasterDAO extends DataAccessObject
{
	
	Logger log;
	
	public DeskMenuMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log=LogManager.getLogger(this.getClass());
	}
	
	//inserting record in desk menu master
	public void create(DeskMenuMasterVO _DeskMenuMasterVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.GBLT_DESK_MENU_MST";

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
        	
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskMenuName());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskUrl());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getIsTemplateBased());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskType());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getTemplateCategory());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getUsabilityFlag());
        	
        		
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
	
	//* Fetching  Desk Menu Record
	public DeskMenuMasterVO fetchRecord(DeskMenuMasterVO _DeskMenuMasterVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        DeskMenuMasterVO deskMenuMstVo = new DeskMenuMasterVO();
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.FULL_INFO.GBLT_DESK_MENU_MST";
        
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

        	
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskMenuId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	
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
				
				deskMenuMstVo.setDeskMenuName(rs.getString(1));
				deskMenuMstVo.setDeskUrl(rs.getString(2));
				deskMenuMstVo.setIsTemplateBased(rs.getString(3));
				deskMenuMstVo.setDeskType(rs.getString(4));
				deskMenuMstVo.setTemplateCategory(rs.getString(5));
				deskMenuMstVo.setUsabilityFlag(rs.getString(6));
				
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
        
       
        return deskMenuMstVo;   
	}
	
	
	public void update(DeskMenuMasterVO _DeskMenuMasterVO, UserVO _UserVO)
	{
		
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE.GBLT_DESK_MENU_MST";

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
        	
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskMenuName());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskUrl());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getIsTemplateBased());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskType());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getTemplateCategory());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getUsabilityFlag());
        	
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskMenuId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
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
	
	public boolean checkDuplicateDeskMenu(DeskMenuMasterVO _DeskMenuMasterVO, UserVO _UserVO)

	{
		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.DUPLICATE_CHECK.GBLT_DESK_MENU_MST";
	   	
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
        	//populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskType());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskMenuName());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	rs.next();
        	if(rs.getInt(1)==0)
        	{
        		flag=true;
        	}
        	else
        	{
        		flag=false;
        	}
            
            return flag;
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
	} 
	
	public boolean check_Modify_DuplicateDeskMenu(DeskMenuMasterVO _DeskMenuMasterVO, UserVO _UserVO)

	{
		boolean flag=false;
		String query =  "" ;
	   	Map populateMAP =new HashMap();  
	   	ResultSet rs;
	   	Sequence sq=new Sequence();
	   	String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   	String queryKey="SELECT.MODIFY_DUPLICATE_CHECK.GBLT_DESK_MENU_MST";
	   	
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
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskMenuName());
        	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskMenuId());
        	//populateMAP.put(sq.next(),_DeskMenuMasterVO.getDeskType());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ImageMasterDAO.populateMAP::"+e);
        }
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	rs.next();
        	if(rs.getInt(1)==0)
        	{
        		flag=true;
        	}
        	else
        	{
        		flag=false;
        	}
            
            return flag;
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
	} 
	
	public List getMenuList(UserVO _userVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.GBLT_DESK_MENU_MST";

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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DeskMasterDAO.populateMAP::" + e);
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
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("getcOMPONENT:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
}
