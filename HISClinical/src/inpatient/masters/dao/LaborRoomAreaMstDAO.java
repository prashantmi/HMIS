package inpatient.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.LaborRoomAreaMasterVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LaborRoomAreaMstDAO extends DataAccessObject {
	
	Logger log;
	
	public LaborRoomAreaMstDAO(TransactionContext _tx)
	{
		super(_tx);
		log=LogManager.getLogger(this.getClass());
	}
	
		
	public void create(LaborRoomAreaMasterVO _LaborRoomAreaMstVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
        String queryKey="INSERT.HANCT_LABORROOM_AREA_MST";

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
        	
        	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	
        	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomAreaType());
        	//populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getSlNo());
        	
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	//populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getAreaId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());        	    	
        	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomAreaDescription());
        
        	        		
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserLaborRoomAreaMstDAO.populateMAP::"+e);
        }
        
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);}
    }
        
        
        public boolean checkDuplicateAreaType(LaborRoomAreaMasterVO _LaborRoomAreaMstVO, UserVO _UserVO)

    	{
    		boolean flag=false;
    		String query =  "" ;
    	   	Map populateMAP =new HashMap();  
    	   	ResultSet rs;
    	   	Sequence sq=new Sequence();
    	   	String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
    	   	String queryKey="SELECT.DUPLICATE_CHECK.HANCT_LABORROOM_AREA_MST";
    	   	
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
            	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomId());
            	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomAreaType());
            	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomAreaDescription());
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
        
        
        public LaborRoomAreaMasterVO fetchRecord(LaborRoomAreaMasterVO _LaborRoomAreaMstVO, UserVO _UserVO)
    	{
    		String query  = "";
    		ResultSet rs;
            Map populateMAP =new HashMap();
            Sequence sq=new Sequence();
            LaborRoomAreaMasterVO laborRoomAreaMstVo = new LaborRoomAreaMasterVO();
            
            String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
            String queryKey="SELECT.FULL_INFO.HANCT_LABORROOM_AREA_MST";
            
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

            	
            	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomAreaId());
            	//populateMAP.put(sq.next(), _LaborRoomAreaMstVO.getLaborRoomAreaId());
            	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
            	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
            	
            	
            }
            catch(Exception e)
            {
            	throw new HisApplicationExecutionException("LaborRoomAreaMstDAO.populateMAP::"+e);
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
    				
    				laborRoomAreaMstVo.setLaborRoomId(rs.getString(1));
    				laborRoomAreaMstVo.setLaborRoomAreaType(rs.getString(2));
    				laborRoomAreaMstVo.setLaborRoomAreaDescription(rs.getString(3));
    				
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
            
           
            return laborRoomAreaMstVo;   
    	}
    	
        
        public boolean check_Modify_Duplicate(LaborRoomAreaMasterVO _LaborRoomAreaMstVO, UserVO _UserVO)

    	{
    		boolean flag=false;
    		String query =  "" ;
    	   	Map populateMAP =new HashMap();  
    	   	ResultSet rs;
    	   	Sequence sq=new Sequence();
            String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
    	   	String queryKey="SELECT.MODIFY_DUPLICATE_CHECK.HANCT_LABORROOM_AREA_MST";
    	   	
    	   	System.out.println("in check modify duplicate");
    	   	
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
            	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomId());
            	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
            	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
            	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomAreaType());
            	//populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getDeskType());
            	
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
    	
        
        public void update(LaborRoomAreaMasterVO _LaborRoomAreaMstVO, UserVO _UserVO)
    	{
    		
    		String query  = "";
    		Map populateMAP =new HashMap();
            Sequence sq=new Sequence();
            String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
            String queryKey="UPDATE.HANCT_LABORROOM_AREA_MST";

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
            	
            	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
            	populateMAP.put(sq.next(),_UserVO.getUserSeatId());
            	populateMAP.put(sq.next(),_LaborRoomAreaMstVO.getLaborRoomAreaId());
            	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
            }
            catch(Exception e)
            {
            	throw new HisApplicationExecutionException("LaborRoomAreaMstDAO.populateMAP::"+e);
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
        
        
        public void modifySave(LaborRoomAreaMasterVO laborRoomAreaMstVO,UserVO _userVO)
    	{
    		String query  = "";
    		Map populateMAP =new HashMap();
            Sequence sq=new Sequence();
            String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_ESSENTIALDAO;
            String queryKey="INSERT.MODIFY.HANCT_LABORROOM_AREA_MST";

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
            	populateMAP.put(sq.next(),laborRoomAreaMstVO.getLaborRoomId());
            	populateMAP.put(sq.next(),laborRoomAreaMstVO.getLaborRoomId());
            	populateMAP.put(sq.next(),_userVO.getHospitalCode());
               	populateMAP.put(sq.next(),laborRoomAreaMstVO.getLaborRoomAreaType());
            	populateMAP.put(sq.next(),_userVO.getHospitalCode());            	         	
            	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
            	populateMAP.put(sq.next(),_userVO.getUserSeatId());
            	populateMAP.put(sq.next(),laborRoomAreaMstVO.getLaborRoomAreaDescription());
            	
            		
            }
            catch(Exception e)
            {
            	throw new HisApplicationExecutionException("LaborRoomAreaMstDAO.populateMAP::"+e);
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
        
        
        
	}
