package medicalboard.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;


public class ChecklistMasterDAO extends DataAccessObject implements ChecklistMasterDAOi{

	public ChecklistMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	public void create(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT.HMBT_CHECKLIST_MST";
		
		
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
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode()); By Anant Patel
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),"1");				//serial No. 
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode()); By Anant Patel
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	         	populateMAP.put(sq.next(),checklistVO.getChecklist());
	        	populateMAP.put(sq.next(),checklistVO.getRemarks());
	        	populateMAP.put(sq.next(),checklistVO.getIsCompulsory());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("populateMAP::"+e);
	        }
	        try
	        {
	        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        	throw new HisDataAccessException("HisDataAccessException While ADDING");
	        }
	}
	
	//Checking For Duplicate Name
	public String checkDuplicateChecklistBeforeSave(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="CHECK.DUPLICATE_BEFORE_SAVE.HMBT_CHECKLIST_MST";
		String checklist=null;
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
	    	//populateMAP.put(sq.next(), userVO.getHospitalCode())
	    	populateMAP.put(sq.next(),checklistVO.getChecklist().toUpperCase());
	    	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	       	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("CertificateTypeMstDAO.populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	    	if(rs.next())
	    		checklist=rs.getString(1);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}  
		return checklist;
	}
	
	
	public void modifyInsert(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey="MODIFY.INSERT.HMBT_CHECKLIST_MST";
		
		
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
			populateMAP.put(sq.next(),checklistVO.getChecklistID());
			populateMAP.put(sq.next(),checklistVO.getChecklistID());
			//populateMAP.put(sq.next(),userVO.getHospitalCode());
			//populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),checklistVO.getChecklist());
			populateMAP.put(sq.next(),checklistVO.getRemarks());
			populateMAP.put(sq.next(),checklistVO.getIsCompulsory());
			populateMAP.put(sq.next(),checklistVO.getIsValid());
			populateMAP.put(sq.next(),userVO.getSeatId());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}
	
	public void update(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey="UPDATE.HMBT_CHECKLIST_MST";
		
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),userVO.getSeatId());
			//populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),checklistVO.getSerialNo());
			populateMAP.put(sq.next(),checklistVO.getChecklistID());
						
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
	}
	
	
	//Checking For Duplicate Name before Modify
	public String checkDuplicateChecklistBeforeModify(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="CHECK.DUPLICATE_BEFORE_MODIFY.HMBT_CHECKLIST_MST";
		String checklist=null;
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
	    	//populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	    	populateMAP.put(sq.next(),checklistVO.getChecklist().toUpperCase());
	       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);	
	       	populateMAP.put(sq.next(),checklistVO.getChecklistID());
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("CertificateTypeMstDAO.populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	    	if(rs.next())
	    		checklist=rs.getString(1);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}  
		return checklist;
	}
	
	
	public MedicalBoardChecklistVO getChecklistById(MedicalBoardChecklistVO checklistVO,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		Sequence sq=new Sequence();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey="SELECT.HMBT_CHECKLIST_MST";
		ResultSet rs;
		
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
			//populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),checklistVO.getSerialNo());
			populateMAP.put(sq.next(),checklistVO.getChecklistID());
						
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(rs.next()){
				HelperMethods.populateVOfrmRS(checklistVO, rs);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While ADDING");
		}
		return checklistVO;
	}
	
	
}//end class
