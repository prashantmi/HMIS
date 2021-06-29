package mrd.transaction.dao;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.RecordMovementDtlVO;
import hisglobal.vo.UserVO;

public class RecordMovementDtlDAO extends DataAccessObject implements RecordMovementDtlDAOi
{
	public  RecordMovementDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	// Inserting Data In Case of ONLINE Certificate Received
	public void create(RecordMovementDtlVO recordMoveDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_RECORD_DISPATCH_DTL";
        
       
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
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordDesc());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordId());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordId());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordType());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordType());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getDeptUnitCode());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordStatus());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
	
	// Updating The Receive From & Record Status of The Certificate  
	public void updateCertificateRecordStatus(String receiveFrom,String recordStatus,String recordId,String slNo,String recordType,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.MOVEMENT.HPMRT_RECORD_DISPATCH_DTL";
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
	    	 populateMAP.put(sq.next(), recordStatus);
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), receiveFrom);
	    	 populateMAP.put(sq.next(), recordId);
	    	 populateMAP.put(sq.next(), slNo);
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 populateMAP.put(sq.next(), recordType);
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
	
	// Inserting Data In Case of OFFLINE Certificate Received
	public void insertOfflineRecord(RecordMovementDtlVO recordMoveDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT_OFFLINE.HPMRT_RECORD_DISPATCH_DTL";
        
       
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
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordDesc());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordId());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordId());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordType());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordType());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getDeptUnitCode());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());
        	populateMAP.put(sq.next(), recordMoveDtlVO.getRecordStatus());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getHospitalCode());      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordMovementDtlDAO.populateMAP::"+e);
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
