package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.MrdConfig;

public class CrNoMergeDAO extends DataAccessObject implements CrNoMergeDAOi
{
	public CrNoMergeDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void saveNotUsedCrNo(CrNoMergeDtlVO crNoMergeDtlVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HPMRT_CRNO_MERGE_DTL";
        
       
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
        	
        	populateMAP.put(sq.next(), crNoMergeDtlVO.getPatMainCrNo());
        	populateMAP.put(sq.next(), crNoMergeDtlVO.getPatNotUsedCrNo());
        	populateMAP.put(sq.next(), crNoMergeDtlVO.getIsMerged());
        	populateMAP.put(sq.next(), crNoMergeDtlVO.getReason());
        	populateMAP.put(sq.next(), crNoMergeDtlVO.getRemarks());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), userVO.getUserSeatId());
        	
        	
        	
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
	
	public List getMergedCrNo(String crNo,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_MERGED_CR_NO.HPMRT_CRNO_MERGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), MrdConfig.CR_NUMBER_IS_MERGED_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Found");
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public void revokeMergedCRNo(String reason,String mainCrNo,String crNo,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "REVOKE.HPMRT_CRNO_MERGE_DTL";
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
	    	 populateMAP.put(sq.next(), MrdConfig.CR_NUMBER_IS_MERGED_REVOKED);
	    	 populateMAP.put(sq.next(), userVO.getUserSeatId());
	    	 populateMAP.put(sq.next(), reason);
	    	 populateMAP.put(sq.next(), mainCrNo);
	    	 populateMAP.put(sq.next(), crNo);
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), MrdConfig.CR_NUMBER_IS_MERGED_YES);
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("CrNoMergeDAO.populateMAP::"+e);
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
	
	public List getMainCRNumberList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_MAIN_CR_NO.HPMRT_CRNO_MERGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), MrdConfig.CR_NUMBER_IS_MERGED_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Found");
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
	
		return alRecord;
	}
	
	public String countMergedCrNo(String crNo,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "COUNT_MERGED_CR_NO.HPMRT_CRNO_MERGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), MrdConfig.CR_NUMBER_IS_MERGED_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), crNo);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Found");
			}*/
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
	
	public String countMainCrNo(String crNo,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "COUNT_MAIN_CR_NO.HPMRT_CRNO_MERGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), MrdConfig.CR_NUMBER_IS_MERGED_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), crNo);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Found");
			}*/
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
	

	public List<CrNoMergeDtlVO> getMergedCrNoForEMR(String crNo,UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.MERGED_CRNO_FOR_EMR.HPMRT_CRNO_MERGE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), MrdConfig.CR_NUMBER_IS_MERGED_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), MrdConfig.CR_NUMBER_IS_MERGED_YES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Found");
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

		List<CrNoMergeDtlVO> lst = new ArrayList<CrNoMergeDtlVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (rs.next())
			{
				rs.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(CrNoMergeDtlVO.class, rs);
				for (ValueObject obj : arrVOs)
					lst.add((CrNoMergeDtlVO) obj);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("CrNoMergeDAO.getDocuments()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;

	}

}
