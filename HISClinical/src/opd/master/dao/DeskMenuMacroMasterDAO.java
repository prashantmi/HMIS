package opd.master.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeskMenuMacroMstVO;
import hisglobal.vo.UserVO;

public class DeskMenuMacroMasterDAO extends DataAccessObject
{
	Logger log;
	
	public DeskMenuMacroMasterDAO(TransactionContext _tx) 
	{
		super(_tx);	
		log=LogManager.getLogger(this.getClass());
	}
	
	public void create(DeskMenuMacroMstVO _deskMenuMacroVO,UserVO _UserVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HOPT_DESK_MENU_MACRO";
        
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
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getDeskType());
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getDeskMenuID());
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getDeptUnitCode());
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getMacroHead());
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getMacroDesc());
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getIsValid());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DeskMenuMacroMasterDAO.populateMAP::"+e);
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
	
	public void deleteMacroHead(DeskMenuMacroMstVO _deskMenuMacroMstVO,UserVO _UserVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="DELETE.HOPT_DESK_MENU_MACRO";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        	_deskMenuMacroMstVO.setIsValid(Config.IS_VALID_DELETED);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        try
        {
        	Sequence sq=new Sequence();
        	populateMAP.put(sq.next(), _deskMenuMacroMstVO.getIsValid());
        	populateMAP.put(sq.next(), _deskMenuMacroMstVO.getMacroID());
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        }	
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UnknownChangeDAO:populateMap(_unknownChangeVO,populateMAP)::"+e);
        }
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        try
        {
        	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(i==0)
        	{
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset");    		
        	}
        }
        catch(Exception e)
        {
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        } 
	    
	}
	
	public DeskMenuMacroMstVO getMacroHeadForModify(String _macroID,UserVO _UserVO)
	{
		DeskMenuMacroMstVO deskMenuMacroVO= new DeskMenuMacroMstVO();
		String query =  "" ;
		Map populateMap =new HashMap();
		String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey="SELECT.ALL.HOPT_DESK_MENU_MACRO";
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		populateMap.put(sq.next(),_macroID);
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
		Connection conn =super.getTransactionContext().getConnection(); 
		
		try
		{
	 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,populateMap);	 
	 	    if(!rs.next())
	 	    {
	 	    	throw new HisRecordNotFoundException();	 	    
	 	    }
	 	    else
	 	    {
	 	    	rs.beforeFirst();
	 	    	HelperMethods.populateVOfrmRS(deskMenuMacroVO,rs);	 	    	    	
	 	    }
	 	    
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: "+e);			 
		 }
		return deskMenuMacroVO;
	}
	
	public void update(DeskMenuMacroMstVO _deskMenuMacroVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE.HOPT_DESK_MENU_MACRO";
        
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
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getMacroHead());
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getMacroDesc());
        	populateMAP.put(sq.next(),_deskMenuMacroVO.getMacroID());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DeskMenuMacroMasterDAO.populateMAP::"+e);
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
}
