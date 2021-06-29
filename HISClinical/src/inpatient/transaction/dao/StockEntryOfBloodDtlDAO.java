package inpatient.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;

import java.util.HashMap;
import java.util.Map;

public class StockEntryOfBloodDtlDAO extends DataAccessObject implements StockEntryOfBloodDtlDAOi
{
	public  StockEntryOfBloodDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void creat(PatBloodStockDtlVO patBloodStockDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HBBT_PAT_BLDSTOCK_DTL";
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
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getPatCrNo());
        	///stock Date
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getRequisitionNo());
        	//sereal No
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getPatCrNo());
        	
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getStockStatus());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBloodBagNo());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBloodBagSequenceNo());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBloodBagExpiry());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBagVolume());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getSourceFlag());
        	populateMAP.put(sq.next(),userVO.getSeatId());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getTubingNo());
        	//entry date
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBagBatchNo());
        	//blood group code
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBloodABO());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getRh());
        	populateMAP.put(sq.next(),Config.SUPER_HOSPITAL_CODE);// userVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBloodABO());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getRh());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBloodComponentID());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getReason());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBloodBankName());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getBloodBankAddr());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getContactNo());
        	populateMAP.put(sq.next(),userVO.getHospitalCode());
        	populateMAP.put(sq.next(),patBloodStockDtlVO.getIsReaction());
                	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("DoctorRoundDtlDAO.populateMAP::"+e);
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
	
	public void updateStockStatus(BloodTransfusionDtlVO bloodTrasVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE.STOCK_STATUS.HBBT_PAT_BLDSTOCK_DTL";
		 Sequence sq=new Sequence();
		 
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
			  populateMAP.put(sq.next(), InpatientConfig.TRANSFUSED);
			  populateMAP.put(sq.next(), bloodTrasVO.getPatCrNo());
			  populateMAP.put(sq.next(), bloodTrasVO.getStockDate());
			  populateMAP.put(sq.next(), bloodTrasVO.getStockBagSerialNo());
			  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
	
	public void updateIsReactionStatus(TransfusionReactionDtlVO transReactionDtlVO,UserVO userVO)
	{
		 String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		 String queryKey="UPDATE.IS_REACTION_STATUS.HBBT_PAT_BLDSTOCK_DTL";
		 Sequence sq=new Sequence();
		 
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
			  populateMAP.put(sq.next(), InpatientConfig.IS_REACTION_YES);
			  populateMAP.put(sq.next(), transReactionDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), transReactionDtlVO.getStockDate());
			  populateMAP.put(sq.next(), transReactionDtlVO.getStockBagSerialNo());
			  populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("InpatientDAO::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
	}
}
