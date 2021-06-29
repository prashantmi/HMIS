package mrd.masters.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.RecordBoundingVO;
import hisglobal.vo.UserVO;

public class RecordBoundingDAO extends DataAccessObject implements RecordBoundingDAOi
{
	
	public RecordBoundingDAO(JDBCTransactionContext _tx)
	{
		super(_tx);
	}
	
	public List getBoundedRecordType(String boundingMode,String boundingId,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="GET_BUONDED_RECORD_TYPE.HPMRT_RECORD_BOUNDING";
	    
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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 04-Jul-2012
			populateMAP.put(sq.next(),boundingMode);
			populateMAP.put(sq.next(),boundingId);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}*/

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("RecordBoundingDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public void create(RecordBoundingVO recordBoundingVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
        String queryKey="INSERT.HPMRT_RECORD_BOUNDING";
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
        	populateMAP.put(sq.next(), recordBoundingVO.getBoundingMode());
        	populateMAP.put(sq.next(), recordBoundingVO.getRecordType());
        	populateMAP.put(sq.next(), recordBoundingVO.getBoundingId());
        	populateMAP.put(sq.next(), recordBoundingVO.getBoundingMode());
        	populateMAP.put(sq.next(), recordBoundingVO.getRecordType());
        	populateMAP.put(sq.next(), recordBoundingVO.getBoundingId());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("RecordBoundingDAO.populateMAP::"+e);
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
	
	public void deletePrevRecord(RecordBoundingVO recordBoundingVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "UPDATE_PREV_RECORD.HPMRT_RECORD_BOUNDING";
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
	    	 populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), recordBoundingVO.getBoundingMode());
	    	 populateMAP.put(sq.next(), recordBoundingVO.getBoundingId());
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	     catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("RecordBoundingDAO.populateMAP::"+e);
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
