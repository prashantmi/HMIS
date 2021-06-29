package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class CertificateIssueDtlDAO extends DataAccessObject implements CertificateIssueDtlDAOi
{
	public  CertificateIssueDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	// Saving the Issue Certificate Detail
	public void create(CertificateIssueDtlVO certificateIssueDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_CERTIFICATE_ISSUE_DTL";
        
       
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
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getCertificateId());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getCertificateDesc());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getCertificateId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getRecordType());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getHandoverTo());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getIsDuplicate());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getIsReceiptTaken());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getRecordStatus());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getRemarks());
        	populateMAP.put(sq.next(), certificateIssueDtlVO.getDeptUnitCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("CertificateIssueDtlDAO.populateMAP::"+e);
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
	
	//Updating The Record Status of The Certificate 
	public void updateCertificateRecordStatus(String recordStatus,String recordId,String slNo,String recordType,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.MOVEMENT.HPMRT_CERTIFICATE_ISSUE_DTL";
		//Connection conn = super.getTransactionContext().getConnection();
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
	    	 populateMAP.put(sq.next(), recordId);
	    	 populateMAP.put(sq.next(), slNo);
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 populateMAP.put(sq.next(), recordType);
	    	
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("CertificateIssueDtlDAO.populateMAP::"+e);
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
