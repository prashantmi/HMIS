package mrd.transaction.dao;

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
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class RecordDispatchDtlDAO extends DataAccessObject implements RecordDispatchDtlDAOi
{
	public  RecordDispatchDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	// Inserting Data In Case of ONLINE Certificate Received
	public void create(RecordDispatchDtlVO recordDispatchDtlVO,UserVO userVO)
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
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRecordDesc());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getDispatchId());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRecordId());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRecordType());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getDeptUnitCode());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getDeptCode());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRemarks());
        	populateMAP.put(sq.next(), userVO.getSeatId());	//Sender Seat Id
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getWardCode());
        	//sysdate		///Sender Date
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRecipientSeatId());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRecipientDate());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getDelayReason());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getReceiveFrom());
        	populateMAP.put(sq.next(), userVO.getUserEmpID());	//Sender Employee No
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRecordPages());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getApprovedEmpNo());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getHandoverTo());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getApprovedDate());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getApprovedSeatId());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRecipentEmpNo());
        	//sysdate
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getVerifyEmpNo());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getRecordStatus());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getVerifyDate());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getVerifySeatId());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getReturnReason());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getEntryMode());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), recordDispatchDtlVO.getPatAdmNo());
        	
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordDispatchDtlDAO.populateMAP::"+e);
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
	       	throw new HisApplicationExecutionException("RecordDispatchDtlDAO.populateMAP::"+e);
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
	public void insertOfflineRecord(RecordDispatchDtlVO recordMoveDtlVO,UserVO userVO)
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
        	throw new HisApplicationExecutionException("RecordDispatchDtlDAO.populateMAP::"+e);
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
	
	public String generateDispatchId(UserVO userVO,String recordType)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="GENERATE_ID.HPMRT_RECORD_DISPATCH_DTL";
	    
        try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),recordType);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
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
	
	public void updateRecipentDtl(RecordDispatchDtlVO dispatchVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.RECIPENT.HPMRT_RECORD_DISPATCH_DTL";
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
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), userVO.getUserEmpID());
	    	 populateMAP.put(sq.next(), dispatchVO.getRecordStatus());//MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_IN_MRD);
	    	 populateMAP.put(sq.next(), dispatchVO.getDispatchId());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("RecordDispatchDtlDAO.populateMAP::"+e);
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
	
	public void updateRejectRecord(RecordDispatchDtlVO dispatchVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.REJECT_RECORD.HPMRT_RECORD_DISPATCH_DTL";
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
	    	 populateMAP.put(sq.next(), dispatchVO.getRecordStatus());
	    	 populateMAP.put(sq.next(), dispatchVO.getReturnReason());
	    	 populateMAP.put(sq.next(), dispatchVO.getDispatchId());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("RecordDispatchDtlDAO.populateMAP::"+e);
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
	
	public RecordDispatchDtlVO[] getRecordListToAcceptByRecordType(String recordType,UserVO userVO)
	{
		RecordDispatchDtlVO[] arrDispatchVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_RECORD_TOBE_ACCEPT_IN_MRD.HPMRT_RECORD_DISPATCH_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
				//arrDispatchVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(RecordDispatchDtlVO.class, rs);
				arrDispatchVO = new RecordDispatchDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDispatchVO[i] = (RecordDispatchDtlVO) vo[i];
				}
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
		return arrDispatchVO;
	}
	
	
	public void updateRecordStatus(String dispatchId,String status,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_RECORD_STATUS.HPMRT_RECORD_DISPATCH_DTL";
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
	    	 populateMAP.put(sq.next(), status);
	    	 populateMAP.put(sq.next(), dispatchId);
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("RecordDispatchDtlDAO.populateMAP::"+e);
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
	
	
	public void updateRecordForCasesheetHandover(RecordDispatchDtlVO dispatchVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_FOR_CASESHEET_HANDOVER.HPMRT_RECORD_DISPATCH_DTL";
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
			populateMAP.put(sq.next(), dispatchVO.getRecordStatus());
			populateMAP.put(sq.next(), dispatchVO.getSenderEmpNo());
			populateMAP.put(sq.next(), dispatchVO.getRecipientSeatId());
			populateMAP.put(sq.next(), dispatchVO.getRecipentEmpNo());
			populateMAP.put(sq.next(), dispatchVO.getDispatchId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("RecordDispatchDtlDAO.populateMAP::"+e);
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
	
	
	public void updateApprovalDetail(RecordDispatchDtlVO dispatchVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE.APPROVAL_DETAIL.HPMRT_RECORD_DISPATCH_DTL";
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
			populateMAP.put(sq.next(), dispatchVO.getRecordStatus());
			populateMAP.put(sq.next(), dispatchVO.getApprovedEmpNo());
			populateMAP.put(sq.next(), dispatchVO.getApprovedSeatId());
			populateMAP.put(sq.next(), dispatchVO.getApprovedRemarks());
			populateMAP.put(sq.next(), dispatchVO.getDispatchId());
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("RecordDispatchDtlDAO.populateMAP::"+e);
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
	
	public RecordDispatchDtlVO[] getRecordListToAcceptForOPDFile(String recordType,String searchDate,UserVO userVO)
	{
		RecordDispatchDtlVO[] arrDispatchVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_OPD_FILE_TOBE_ACCEPT_IN_MRD.HRGT_EPISODE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), userVO.getSeatId());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), searchDate);
		

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
				//arrDispatchVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(RecordDispatchDtlVO.class, rs);
				arrDispatchVO = new RecordDispatchDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDispatchVO[i] = (RecordDispatchDtlVO) vo[i];
				}
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
		return arrDispatchVO;
	}
	
}
