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
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class RecordLostFoundDtlDAO extends DataAccessObject implements RecordLostFoundDtlDAOi
{
	public  RecordLostFoundDtlDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void create(RecordLostFoundDtlVO recordLostDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_RECORD_LOSTFOUND_DTL";
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
        	populateMAP.put(sq.next(), recordLostDtlVO.getDispatchId());
        	populateMAP.put(sq.next(), recordLostDtlVO.getDispatchId());
        	populateMAP.put(sq.next(), recordLostDtlVO.getReportedBy());
        	populateMAP.put(sq.next(), recordLostDtlVO.getMrdCode());
        	populateMAP.put(sq.next(), recordLostDtlVO.getLastSeenDateTime());
        	populateMAP.put(sq.next(), recordLostDtlVO.getLostArea());
        	populateMAP.put(sq.next(), recordLostDtlVO.getLostType());
        	populateMAP.put(sq.next(), recordLostDtlVO.getLostRemarks());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), recordLostDtlVO.getFoundDateTime());
        	populateMAP.put(sq.next(), recordLostDtlVO.getFoundRemarks());
        	populateMAP.put(sq.next(), recordLostDtlVO.getFoundBy());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        	
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
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
	
	public RecordLostFoundDtlVO[] getLostRecordList(String recordType,String mrdCode,UserVO userVO)
	{
		RecordLostFoundDtlVO[] arrLostRecordVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "GET_LOST_RECORD_LIST.HPMRT_RECORD_LOSTFOUND_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), mrdCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), recordType);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_LOST);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
				//arrLostRecordVO = null;
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(RecordLostFoundDtlVO.class, rs);
				arrLostRecordVO = new RecordLostFoundDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrLostRecordVO[i] = (RecordLostFoundDtlVO) vo[i];
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
		return arrLostRecordVO;
	}
	
	public void updateFoundDetail(RecordLostFoundDtlVO recordLostDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_FOUND_DETAIL.HPMRT_RECORD_LOSTFOUND_DTL";
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
	    	 populateMAP.put(sq.next(), recordLostDtlVO.getFoundDateTime());
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), recordLostDtlVO.getFoundRemarks());
	    	 populateMAP.put(sq.next(), recordLostDtlVO.getFoundBy());
	    	 populateMAP.put(sq.next(), recordLostDtlVO.getDispatchId());
	    	 populateMAP.put(sq.next(), recordLostDtlVO.getSlNo());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("RecordLostFoundDtlDAO.populateMAP::"+e);
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
