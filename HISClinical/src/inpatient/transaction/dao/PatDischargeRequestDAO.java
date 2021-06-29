package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import ehr.EHRConfig;

public class PatDischargeRequestDAO extends DataAccessObject implements PatDischargeRequestDAOi
{
	public  PatDischargeRequestDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void create(PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HIPT_PATDISCHARGE_REQ_DTL";
        
       
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
        	
        	System.out.println("value::::::::"+patDischargeReqVO.getNextVisitDate());
        	populateMAP.put(sq.next(), patDischargeReqVO.getPatCrNo());
        	populateMAP.put(sq.next(), patDischargeReqVO.getEpisodeCode());
        	populateMAP.put(sq.next(), patDischargeReqVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), patDischargeReqVO.getPatAdmNo());
        	populateMAP.put(sq.next(), patDischargeReqVO.getPatAdmNo());
        	populateMAP.put(sq.next(), patDischargeReqVO.getRemarks());
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), patDischargeReqVO.getNextVisitDate());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(), patDischargeReqVO.getRequestType());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),userVO.getIpAddress());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("PatDischargeRequestDAO.populateMAP::"+e);
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
	

	public void crossmatched_bag_revert(PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		int nProcedureIndex;
		HisDAO hisDAO_p = new HisDAO("Inpatient", "PatDischargeRequestDAO");
		String pmode="1";
		String strDBErr;
		ResultSet objResSet;
		
            
        
        try
        {
        	nProcedureIndex = hisDAO_p.setProcedure(InpatientConfig.PROC_CROSSMATCHED_BAG_REVERT);
    		// Setting and Registering In and Out Parameters 
    		hisDAO_p.setProcInValue(nProcedureIndex, "pmode", pmode,1);
    		hisDAO_p.setProcInValue(nProcedureIndex, "hcode", (userVO.getHospitalCode()==null) ? "": userVO.getHospitalCode(),2);
    		hisDAO_p.setProcInValue(nProcedureIndex, "puk_no", (patDischargeReqVO.getPatCrNo()==null) ? "":patDischargeReqVO.getPatCrNo(),3);
    		hisDAO_p.setProcInValue(nProcedureIndex, "adm_no", (patDischargeReqVO.getPatAdmNo()==null) ? "":patDischargeReqVO.getPatAdmNo(),4);
    		hisDAO_p.setProcInValue(nProcedureIndex, "gnum_seatid", (userVO.getSeatId()==null) ? "":userVO.getSeatId(),5);
    		hisDAO_p.setProcOutValue(nProcedureIndex,"err", 1,6); // varchar
    		// Executing Procedure 
    		hisDAO_p.executeProcedureByPosition(nProcedureIndex);
    		// Getting out parameters 
    		strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
    		
        	
       
     // If Database Error Occurs, No further processing is required. 
     		if (strDBErr != null && !strDBErr.equals(""))
     		{
     			throw new Exception("Data Base Error:" + strDBErr);
     		}
     		}
     		catch (Exception e)
     		{
     			throw new HisDataAccessException("PatDischargeRequestDAO::hisDAO_p.execute: " + e.getMessage());
     		}
     			
    	finally{
			if (hisDAO_p != null) 
				hisDAO_p.free();
			hisDAO_p = null;
			}
	}
	
	
	
	public PatDischargeReqDtlVO getDischargeRemarks(String admNo,UserVO userVO)
	{
		PatDischargeReqDtlVO patDischargeReqVO=new PatDischargeReqDtlVO();
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT.HIPT_PATDISCHARGE_REQ_DTL";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), admNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), InpatientConfig.DISCHARGE_REQUEST_TYPE_REQUEST);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Remarks Found");
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(patDischargeReqVO,rs);
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
		return patDischargeReqVO;
	}
	
	//adde by swati on date:11-06-2019
	
	public String getDischargeStatus(String admNo,UserVO userVO)
	{
		PatDischargeReqDtlVO patDischargeReqVO=new PatDischargeReqDtlVO();
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="SELECT.HIPT_PATADMDISC_DTL";
        String dietST="";
        
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        populateMAP.put(sq.next(), admNo);
        populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        populateMAP.put(sq.next(), userVO.getHospitalCode());
        
        try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			
				//rs.beforeFirst();
				while(rs.next())
				{
					//HelperMethods.populateVOfrmRS(patDischargeReqVO,rs);
					 dietST=rs.getString(1);
					 System.out.println("dietSTdao"+dietST);
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
		return dietST;
	}
	
	
	
	
}
