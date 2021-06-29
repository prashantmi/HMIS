package mrd.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatFamilyDocDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class PatFamilyDocDtlDAO extends DataAccessObject implements PatFamilyDocDtlDAOi
{ 
	public  PatFamilyDocDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public PatFamilyDocDtlVO[] getExistingFamilyDoctorRecord(String crNo,UserVO userVO)
	{
		PatFamilyDocDtlVO[] arrExistPatFamilyDocVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_EXIST_PAT_FAMILY_DOC.HPMRT_PAT_FAMILY_DOC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
	

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatFamilyDocDtlVO.class, rs);
			arrExistPatFamilyDocVO = new PatFamilyDocDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrExistPatFamilyDocVO[i] = (PatFamilyDocDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return arrExistPatFamilyDocVO;
	}
	
	public void savePatientFamilyDoctorDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_PAT_FAMILY_DOC_DTL";
        
       
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
        	
        	populateMAP.put(sq.next(), patFamilyDocVO.getPatCrNo());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), patFamilyDocVO.getPatCrNo());
        	populateMAP.put(sq.next(), patFamilyDocVO.getDoctorName());
        	populateMAP.put(sq.next(), patFamilyDocVO.getPhysicianType());
        	populateMAP.put(sq.next(), patFamilyDocVO.getClinicHosName());
        	populateMAP.put(sq.next(), patFamilyDocVO.getQualification());
        	populateMAP.put(sq.next(), patFamilyDocVO.getDoctorId() );
        	populateMAP.put(sq.next(), patFamilyDocVO.getDocRegNo());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), patFamilyDocVO.getAddress1());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(), patFamilyDocVO.getAddress2());
        	populateMAP.put(sq.next(), patFamilyDocVO.getEmail());
        	populateMAP.put(sq.next(), patFamilyDocVO.getPhNo());
        	populateMAP.put(sq.next(), patFamilyDocVO.getMobileNo());
        	populateMAP.put(sq.next(), patFamilyDocVO.getCity());
        	populateMAP.put(sq.next(), patFamilyDocVO.getPatientId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), patFamilyDocVO.getConsultationFor());
        	populateMAP.put(sq.next(), patFamilyDocVO.getRemarks());
        	populateMAP.put(sq.next(), patFamilyDocVO.getFaxNo());
        	populateMAP.put(sq.next(), patFamilyDocVO.getClinicHosId());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PatFamilyDocDtlDAO.populateMAP::"+e);
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
	
	public PatFamilyDocDtlVO getPatientFamilyDocDetail(String chk,UserVO userVO)
	{
		PatFamilyDocDtlVO patFamilyDocVO=new PatFamilyDocDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="SELECT.HPMRT_PAT_FAMILY_DOC_DTL";
		
		Sequence sq= new Sequence();
		
		String crNo=chk.split("@")[0];
		String hCode=chk.split("@")[1];
		String slNo=chk.split("@")[2];
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), crNo);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), hCode);
		populateMap.put(sq.next(), slNo);
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(patFamilyDocVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return patFamilyDocVO;
	}
	
	public void modifyPatientFamilyDocDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.HPMRT_PAT_FAMILY_DOC_DTL";
		Sequence sq = new Sequence();
		
		
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
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getClinicHosName());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getAddress1());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getAddress2());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getEmail());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getPhNo());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getMobileNo());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getCity());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getPatientId());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getConsultationFor());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getRemarks());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getFaxNo());
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getPhysicianType());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getDoctorName());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getQualification());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getDoctorId());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getDocRegNo());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getClinicHosId());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getSlNo());
	    	 populateMAP.put(sq.next(), patFamilyDocVO.getPatCrNo());
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("PatFamilyDocDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
	
	public void revokePatientFamilyDocDetail(String crNo,String hCode,String slNo,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "REVOKE.HPMRT_PAT_FAMILY_DOC_DTL";
		Sequence sq = new Sequence();
		
		
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
	    	
	    	 populateMAP.put(sq.next(), slNo);
	    	 populateMAP.put(sq.next(), crNo);
	    	 populateMAP.put(sq.next(), hCode);
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("PatFamilyDocDtlDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
}
