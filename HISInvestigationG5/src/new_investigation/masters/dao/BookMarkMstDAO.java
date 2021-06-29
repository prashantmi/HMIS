/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :BOOKMARK MASTER
 ## Purpose						        : 
 ## Date of Creation					:30-MAR-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/
package new_investigation.masters.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.BookMarkMstVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

public class BookMarkMstDAO extends DataAccessObject {

	public BookMarkMstDAO(TransactionContext _tx) {
		super(_tx);
		
	}
	public String checkDuplicateRecord(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_BOOKMARK_NAME.Bookmark_Mst";
		
		
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
	    	populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkName().toLowerCase());
	    	/*populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkType());*/
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::"+e);
	    }
	    try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
	public String checkDuplicateRecordSave(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_BOOKMARK_NAME.Bookmark_Mst_Save";
		
		
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
	    	populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkName());
	    	populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::"+e);
	    }
	    try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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

	public String checkDuplicateRecordModify(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_BOOKMARK_NAME_MODIFY.Bookmark_Mst";
		
		
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
	    	populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkName());
	    	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkType());
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::"+e);
	    }
	    try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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

	
	public void saveBookMark(BookMarkMstVO bookMarkMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "SAVE.BookMarkMst";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{	
			
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkType());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkName().toLowerCase());
				
		}
		
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	
	}
	public void fetchModifyBOokMark(BookMarkMstVO bookMarkMstVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List testMethod=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="SELECT.MODIFY.BooMark_Mst";
		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),bookMarkMstVO.getBookmarkCode());
		populateMap.put(sq.next(),_UserVO.getHospitalCode());
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(bookMarkMstVO, rs);
					
				}
				
				
				
				testMethod=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		
	}
	
	public void saveModifyBookMark(BookMarkMstVO bookMarkMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "SAVE.MODIFY.BookMark_Mst";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{	
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkName());
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkType());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkCode());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				
		}
		
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	
	}
	public void saveDelBookMark(BookMarkMstVO bookMarkMstVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey = "UPDATE.DUP.Deleted.BookMark_Mst";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{	
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkName());
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkType());
			populateMAP.put(sq.next(),_UserVO.getSeatId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkName());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
				
		}
		
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("AntibodyMethodMstDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	
	}

	public String checkDuplicateRecordd(BookMarkMstVO bookMarkMstVO,UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_MASTERSDAO;
		String queryKey="CHECK_DUPLICATE_BOOKMARK_NAME.Bookmark_Mst_Modify";
		
		
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
	    	populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkName().toLowerCase());
	    	populateMAP.put(sq.next(),bookMarkMstVO.getBookmarkType());
			populateMAP.put(sq.next(),_UserVO.getHospitalCode());
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::"+e);
	    }
	    try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
	
	
}
