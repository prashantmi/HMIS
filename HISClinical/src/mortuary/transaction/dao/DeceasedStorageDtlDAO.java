package mortuary.transaction.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeceasedStorageDtlVO;
import hisglobal.vo.UserVO;

public class DeceasedStorageDtlDAO extends DataAccessObject implements DeceasedStorageDtlDAOi
{
	public DeceasedStorageDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting The Record
	public void create(DeceasedStorageDtlVO deceasedStorageVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="INSERT.HMRT_DECEASED_STORAGE_DTL";
	    
	   
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
	    	populateMAP.put(sq.next(), deceasedStorageVO.getDeceasedNo());
	    	populateMAP.put(sq.next(), deceasedStorageVO.getDeceasedNo());
	    //	populateMAP.put(sq.next(), deceasedStorageVO.getStorageInTime());
	    	populateMAP.put(sq.next(), deceasedStorageVO.getStorageUpto());
	    	populateMAP.put(sq.next(), deceasedStorageVO.getStorageReason());
	    	populateMAP.put(sq.next(), deceasedStorageVO.getStorageOutTime());
	    	populateMAP.put(sq.next(), deceasedStorageVO.getChamberRackId());
	    	populateMAP.put(sq.next(), deceasedStorageVO.getBodyPutBy());
	    	populateMAP.put(sq.next(), deceasedStorageVO.getOutFor());
	    	populateMAP.put(sq.next(), deceasedStorageVO.getIsStorageExtend());
	    	populateMAP.put(sq.next(), userVO.getSeatId());
	    	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	
	    	      	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("DeceasedStorageDtlDAO.populateMAP::"+e);
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
	
	//Counting The Dead Body in Rack
	public String countdeadBodyInRack(DeceasedStorageDtlVO deceasedStorageVO, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="COUNT_DEAD_BODY_IN_RACK.HMRT_CHAMBER_RACK_MST";
	    
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
			populateMAP.put(sq.next(),deceasedStorageVO.getChamberRackId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			
			
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
	
	public String getRackIdByDeceasedNo(String deceasedNo, UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
	    String queryKey="RACK_ID_BY_DECEASED_NO.HMRT_CHAMBER_RACK_MST";
	    
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
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),deceasedNo);
			
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
	
	public void updateHandoverStorageDtl(String deceasedNo, UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		 Sequence sq=new Sequence();
		String filename= MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey="UPDATE.HMRT_DECEASED_STORAGE_DTL";
		
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
			populateMAP.put(sq.next(),MortuaryConfig.DECEASED_OUT_FOR_HANDOVER);
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),deceasedNo);
		  
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryEssDAO:populateMap::"+e);
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
