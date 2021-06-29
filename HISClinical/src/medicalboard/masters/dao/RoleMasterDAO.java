package medicalboard.masters.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import medicalboard.MedicalBoardConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.RoleMasterVO;
import hisglobal.vo.UserVO;


public class RoleMasterDAO extends DataAccessObject implements RoleMasterDAOi{

	public RoleMasterDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	
	//Checking For Duplicate Name
	public String checkDuplicateRoleName(RoleMasterVO roleMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="SELECT_CHECKDUPLICATE.HMBT_ROLE_MST";
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
	        populateMAP.put(sq.next(),roleMasterVO.getRoleName());
	    	//populateMAP.put(sq.next(), userVO.getHospitalCode());
	        populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("OrganizationMstDAO.populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	    	rs.first();
		    return rs.getString(1);
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
	
	
	
	
	
	public void create(RoleMasterVO roleMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT.HMBT_ROLE_MST";
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
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode()); //by Anant Patel
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),Config.SL_NO);
	        	populateMAP.put(sq.next(),roleMasterVO.getRoleName());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode()); //by Anant Patel
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
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
	
	
	
	public RoleMasterVO getRoleDtl(RoleMasterVO roleMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		RoleMasterVO vo=new RoleMasterVO();

		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="SELECT.HMBT_ROLE_MST";
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
			populateMAP.put(sq.next(), roleMasterVO.getRoleID());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}
	
	
	
	public void updateRoleDtl(RoleMasterVO roleMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="UPDATE.HMBT_ROLE_MST";
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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), roleMasterVO.getRoleID());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
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
	
	

	
	public void modifySaveRoleDtl(RoleMasterVO roleMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="INSERT_MODIFY.HMBT_ROLE_MST";
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
			populateMAP.put(sq.next(),roleMasterVO.getRoleID());
			populateMAP.put(sq.next(),roleMasterVO.getRoleID());
			//populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),roleMasterVO.getRoleName());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	//populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
         	populateMAP.put(sq.next(),_UserVO.getSeatId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
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
		
	
	
	
	
	public String checkDuplicateRoleNameForModify(RoleMasterVO roleMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="SELECT_CHECKDUPLICATE_MODIFY.HMBT_ROLE_MST";
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
	        populateMAP.put(sq.next(),roleMasterVO.getRoleName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),roleMasterVO.getRoleID());
	       	//populateMAP.put(sq.next(), userVO.getHospitalCode());
	       	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
	    try
		{
	    	rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
	    	rs.first();
		    return rs.getString(1);
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


	
	
}
