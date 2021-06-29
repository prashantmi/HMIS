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
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.UserVO;

public class BirthDeathUploadDAO extends DataAccessObject implements BirthDeathUploadDAOi
{
	public BirthDeathUploadDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	public void insertUpload(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
	    String queryKey="INSERT_UPLOAD.HPMRT_BIRTHDEATH_UPLOAD_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), birthUploadVO.getRecordType());
	    	populateMAP.put(sq.next(), birthUploadVO.getPatCrNo());
	    	populateMAP.put(sq.next(), birthUploadVO.getRegistrtionNo());
	    	populateMAP.put(sq.next(), birthUploadVO.getRecordType());
	    	populateMAP.put(sq.next(), birthUploadVO.getRemarks());
	    	populateMAP.put(sq.next(), birthUploadVO.getEntryMode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), birthUploadVO.getPatCrNo());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("BirthDeathUploadDAO.populateMAP::"+e);
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
	
	
	public void insertUploadHandover(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
	    String queryKey="INSERT_UPLOAD_N_HANDOVER.HPMRT_BIRTHDEATH_UPLOAD_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), birthUploadVO.getRecordType());
	    	populateMAP.put(sq.next(), birthUploadVO.getPatCrNo());
	    	populateMAP.put(sq.next(), birthUploadVO.getRegistrtionNo());
	    	populateMAP.put(sq.next(), birthUploadVO.getRecordType());
	    	populateMAP.put(sq.next(), birthUploadVO.getRemarks());
	    	populateMAP.put(sq.next(), birthUploadVO.getEntryMode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), birthUploadVO.getPatCrNo());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    	
	    	populateMAP.put(sq.next(), birthUploadVO.getRelativeName());
	    	populateMAP.put(sq.next(), birthUploadVO.getRelativeAddress());
	    	populateMAP.put(sq.next(), birthUploadVO.getRelativeCode());
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("BirthDeathUploadDAO.populateMAP::"+e);
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
	
	public BirthDeathUploadDtlVO getBirthDeathUploadDtl(String recordType,String crNo,UserVO userVO)
	{
		BirthDeathUploadDtlVO birthUploadDtlVO=new BirthDeathUploadDtlVO();
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename= MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_BIRTH_UPLOAD.HPMRT_BIRTHDEATH_UPLOAD_DTL";
		Connection conn=super.getTransactionContext().getConnection();
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), crNo);
						
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(birthUploadDtlVO,rs);
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
		return birthUploadDtlVO;
	}
	
	public void insertHandover(BirthDeathUploadDtlVO birthHandoverVO,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
	    String queryKey="INSERT_HANDOVER.HPMRT_BIRTHDEATH_UPLOAD_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), birthHandoverVO.getRecordType());
	    	populateMAP.put(sq.next(), birthHandoverVO.getPatCrNo());
	    	populateMAP.put(sq.next(), birthHandoverVO.getRegistrtionNo());
	    	populateMAP.put(sq.next(), birthHandoverVO.getRecordType());
	    	populateMAP.put(sq.next(), birthHandoverVO.getUploadDateTime());
	    	populateMAP.put(sq.next(), birthHandoverVO.getRemarks());
	    	populateMAP.put(sq.next(), birthHandoverVO.getEntryMode());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	populateMAP.put(sq.next(), birthHandoverVO.getPatCrNo());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	populateMAP.put(sq.next(), userVO.getIpAddress());
	    	
	    	populateMAP.put(sq.next(), birthHandoverVO.getRelativeName());
	    	populateMAP.put(sq.next(), birthHandoverVO.getRelativeAddress());
	    	populateMAP.put(sq.next(), birthHandoverVO.getRelativeCode());
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("BirthDeathUploadDAO.populateMAP::"+e);
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
}
