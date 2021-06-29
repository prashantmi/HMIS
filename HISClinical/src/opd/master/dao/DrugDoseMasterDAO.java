package opd.master.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DrugDoseMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DrugDoseMasterDAO extends DataAccessObject
{
Logger log;
	
	public DrugDoseMasterDAO(TransactionContext _tx)
	{
		super(_tx);
		log=LogManager.getLogger(this.getClass());
	}
	
	public DrugDoseMstVO itemName(String itemID , UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		DrugDoseMstVO _drugDoseMstVO=new DrugDoseMstVO();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "FETCH.FULL_INFO.HSTT_ITEMTYPE_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), itemID);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisApplicationExecutionException("MstDAO.populateMAP::" + e);
		}
		//String str = new String();
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				_drugDoseMstVO.setItemType(rs.getString(1));
											
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return _drugDoseMstVO;
	}
	
	
	public void create(DrugDoseMstVO drugDoseMstVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT.HGBT_DRUG_DOSE_MST";

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
        	
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),drugDoseMstVO.getSereialNo());
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseName());
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseInstruction());
        	populateMAP.put(sq.next(),drugDoseMstVO.getItemTypeId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getSeatId());  
        	populateMAP.put(sq.next(),drugDoseMstVO.getIsFrequencyBound());
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseQty());
        		
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
	
	public DrugDoseMstVO fetchRecord(DrugDoseMstVO drugDoseMstVO, UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
		//ValueObject[] vo={};
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
       //DrugDoseMstVO[] _drugDoseMstVO= null;
        
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="SELECT.HGBT_DRUG_DOSE_MST";
        
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
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseId());
        	populateMAP.put(sq.next(),drugDoseMstVO.getSereialNo());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	//populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("UserDeskMenuMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if (!rs.next()) {
				throw new HisRecordNotFoundException();
			} else {
				HelperMethods.populateVOfrmRS(drugDoseMstVO, rs);
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
        return drugDoseMstVO;   
	}
	
	/*
	public void update(DrugDoseMstVO drugDoseMstVO, UserVO _UserVO)
	{
		
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE.HGBT_DRUG_DOSE_MST";

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
        	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),drugDoseMstVO.getItemTypeId());
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
	*/
	
	public void modify(DrugDoseMstVO drugDoseMstVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE.HGBT_DRUG_DOSE_MST";

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
        	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
//        	populateMAP.put(sq.next(), _UserVO.getSeatId());
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseId());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(), drugDoseMstVO.getSereialNo());
        	
        	
               	
        }
        catch(Exception e)
        {
        	e.printStackTrace();
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
	public void modifySave(DrugDoseMstVO drugDoseMstVO, UserVO _UserVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="INSERT_MODIFY.HGBT_DRUG_DOSE_MST";

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
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseId());
        	populateMAP.put(sq.next(),drugDoseMstVO.getItemTypeId());
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseName());
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseInstruction());
        	populateMAP.put(sq.next(), drugDoseMstVO.getIsFrequencyBound());
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseQty());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),drugDoseMstVO.getDoseId());
        	populateMAP.put(sq.next(), drugDoseMstVO.getIsValid());
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
        
        }
        catch(Exception e)
        {
        	e.printStackTrace();
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
	
	public boolean checkDuplicate(DrugDoseMstVO drugDoseMstVO,UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
		boolean flag=false;
	   Map populateMAP =new HashMap();
	   Sequence sq=new Sequence();
	   String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   String queryKey="SELECT.CHECK_DUPLICATE.HGBT_DRUG_DOSE_MST";

	   try{
	   	query =HelperMethodsDAO.getQuery(filename,queryKey);
	   }
	   catch(Exception e){
	   	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   log.info(query);
	   
	   try{   
	   
		//populateMAP.put(sq.next(),_UserVO.getHospitalCode()); 
	   	//populateMAP.put(sq.next(),_UserVO.getHospitalCode());
		populateMAP.put(sq.next(),drugDoseMstVO.getItemTypeId());
	   	populateMAP.put(sq.next(),drugDoseMstVO.getDoseName());
	   	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	   	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	  
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.next();
			if (rs.getInt(1)==0)
			{
				//throw new HisRecordNotFoundException("NO Desk Menu Found");
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return flag;
	}
	
	public boolean checkModifyDuplicate(DrugDoseMstVO drugDoseMstVO,UserVO _UserVO)
	 {
	 	String query  = "";
	 	ResultSet rs;
	 	boolean flag=false;
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	    String queryKey="SELECT.CHECK_MOD_DUPLICATE.HGBT_DRUG_DOSE_MST";

	    try{
	    	query =HelperMethodsDAO.getQuery(filename,queryKey);
	    }
	    catch(Exception e){
	    	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    }
	    log.info(query);
	    
	    try{   
	    
	 	
	    	populateMAP.put(sq.next(),drugDoseMstVO.getItemTypeId());
		   	populateMAP.put(sq.next(),drugDoseMstVO.getDoseName());
		   	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
		   	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		   	populateMAP.put(sq.next(),drugDoseMstVO.getDoseId());
	    	
	   
	 		rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	 		rs.next();
	 		if (rs.getInt(1)==0)
	 		{
	 			//throw new HisRecordNotFoundException("NO Desk Menu Found");
	 			flag=true;
	 		}
	 		else
	 		{
	 			flag=false;
	 		}
	 	}
	 	catch (HisRecordNotFoundException e)
	 	{
	 		throw new HisRecordNotFoundException(e.getMessage());
	 	}
	 	catch (Exception e)
	 	{
	 		if (e.getClass() == HisRecordNotFoundException.class)
	 		{
	 			throw new HisRecordNotFoundException();
	 		}
	 		else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
	 	}

	 	
	 	return flag;
	 }
}
