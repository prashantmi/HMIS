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
import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class MrdRecordLostFoundDAO extends DataAccessObject implements MrdRecordLostFoundDAOi 
{
	public  MrdRecordLostFoundDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	public void insertLostDtl(MrdRecordLostFoundDtlVO mrdRecordLostVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT_LOST.HPMRT_MRDRECORD_LOSTFOUND_DTL";
        
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
        	populateMAP.put(sq.next(), mrdRecordLostVO.getMrdRecordId());
        	populateMAP.put(sq.next(), mrdRecordLostVO.getMrdRecordId());
        	populateMAP.put(sq.next(), mrdRecordLostVO.getRecordId());
        	populateMAP.put(sq.next(), mrdRecordLostVO.getReportedBy());
        	populateMAP.put(sq.next(), mrdRecordLostVO.getLastSeenDateTime());
        	populateMAP.put(sq.next(), mrdRecordLostVO.getLostArea());
        	populateMAP.put(sq.next(), mrdRecordLostVO.getLostType());
        	populateMAP.put(sq.next(), mrdRecordLostVO.getLostRemarks());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getIpAddress());
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MrdRecordLostFoundDAO.populateMAP::"+e);
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
	
	public MrdRecordLostFoundDtlVO[] getLostRecordInMrdList(UserVO userVO)
	{
		MrdRecordLostFoundDtlVO[] arrLostRecordVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_LOST_RECORD.HPMRT_MRDRECORD_LOSTFOUND_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		//populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_STATUS_LOST);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//arrLostRecordVO = null;
				throw new HisRecordNotFoundException("No Lost Record Found");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MrdRecordLostFoundDtlVO.class, rs);
				arrLostRecordVO = new MrdRecordLostFoundDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrLostRecordVO[i] = (MrdRecordLostFoundDtlVO) vo[i];
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
	
	public void updateFoundDetail(MrdRecordLostFoundDtlVO mrdRecordLostVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_FOUND_DETAIL.HPMRT_MRDRECORD_LOSTFOUND_DTL";
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
	    	 populateMAP.put(sq.next(), mrdRecordLostVO.getFoundDateTime());
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), mrdRecordLostVO.getFoundRemarks());
	    	 populateMAP.put(sq.next(), mrdRecordLostVO.getFoundBy());
	    	 populateMAP.put(sq.next(), mrdRecordLostVO.getMrdRecordId());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
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
