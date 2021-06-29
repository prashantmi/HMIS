package medicalboard.masters.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;



public class OrganizationMstDAO extends DataAccessObject implements OrganizationMstDAOi{

	public OrganizationMstDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	//Checking For Duplicate Name
	public String checkDuplicateOrganizationName(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="SELECT_CHECK_DUPLICITY_INSERT.HMBT_ORGANIZATION_MST";
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
	        populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgName());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
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
	
	
	
	
	
	public void create(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="INSERT.HMBT_ORGANIZATION_MST";
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
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),Config.SL_NO);
	        	populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgName());
	        	populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgTypeID());
	        	populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgAddress());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
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
	
	
	
	public MbOrganizationMstVO getOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		MbOrganizationMstVO vo=new MbOrganizationMstVO();

		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="SELECT.HMBT_ORGANIZATION_MST";
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
			populateMAP.put(sq.next(), mbOrganizationMstVO.getOrgID());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), mbOrganizationMstVO.getSlNo());
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
	
	
	public void updateOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="UPDATE.HMBT_ORGANIZATION_MST";
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
			populateMAP.put(sq.next(), mbOrganizationMstVO.getOrgID());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), mbOrganizationMstVO.getSlNo());
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
	
	

	
	public void modifySaveOrganizationDtl(MbOrganizationMstVO mbOrganizationMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
		String queryKey ="INSERT_MODIFY.HMBT_ORGANIZATION_MST";
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
			populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgID());
			populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgID());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgName());
        	populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgTypeID());
        	populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgAddress());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
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
		
	
	
	
	
	public String checkDuplicateOrganizatioNameForModify(MbOrganizationMstVO mbOrganizationMstVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_MASTERSDAO;
        String queryKey="SELECT.CHECK_DUPLICITY_MODIFY.HMBT_ORGANIZATION_MST";
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
	        populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),mbOrganizationMstVO.getOrgID());
	       	populateMAP.put(sq.next(), userVO.getHospitalCode());
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
