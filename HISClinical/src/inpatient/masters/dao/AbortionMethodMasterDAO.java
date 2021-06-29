package inpatient.masters.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import inpatient.InpatientConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.AbortionMethodMasterVO;
import hisglobal.vo.UserVO;

public class AbortionMethodMasterDAO extends DataAccessObject implements AbortionMethodMasterDAOi
{
	public AbortionMethodMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	public void create(AbortionMethodMasterVO abortionMethodMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HANCT_ABORTION_METHOD_MST";
		
		
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
	        	
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),"1");	//serial no=1
	        	populateMAP.put(sq.next(),abortionMethodMasterVO.getAbortionMethod());
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	populateMAP.put(sq.next(),abortionMethodMasterVO.getAbortionTypeId());
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("populateMAP::"+e);
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
	
	//Checking For Duplicate Name
	public String checkDuplicateAbortionMethodName(AbortionMethodMasterVO abortionMethodMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HANCT_ABORTION_METHOD_MST";
		
		
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
	        populateMAP.put(sq.next(),abortionMethodMasterVO.getAbortionMethod());
	        
	    	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);	
	        	
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
		public AbortionMethodMasterVO getDataForModify(AbortionMethodMasterVO abortionMethodMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		AbortionMethodMasterVO vo=new AbortionMethodMasterVO();

		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.HANCT_ABORTION_METHOD_MST";
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
			populateMAP.put(sq.next(), abortionMethodMasterVO.getAbortionMethodId());
			populateMAP.put(sq.next(), abortionMethodMasterVO.getSlNo());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
				/*_AbortionMethodMasterVO.setAutopsyFor(rs.getString(1));
				_AbortionMethodMasterVO.setAutopsyType(rs.getString(2));
				_AbortionMethodMasterVO.setMortuaryType(rs.getString(3));
				_AbortionMethodMasterVO.setEmailId(rs.getString(4));
				_AbortionMethodMasterVO.setEmpNo(rs.getString(5));
				_AbortionMethodMasterVO.setLocationDesc(rs.getString(6));
				_AbortionMethodMasterVO.setMortuaryName(rs.getString(7));
				_AbortionMethodMasterVO.setRoomNo(rs.getString(8));
				_AbortionMethodMasterVO.setMortuaryShortName(rs.getString(9));
				_AbortionMethodMasterVO.setWebsite(rs.getString(10));
				_AbortionMethodMasterVO.setBlockId(rs.getString(11));
				_AbortionMethodMasterVO.setBuildingCode(rs.getString(12));
				_AbortionMethodMasterVO.setFloorId(rs.getString(13));
				_AbortionMethodMasterVO.setRoomId(rs.getString(14));
				_AbortionMethodMasterVO.setDepartmentCode(rs.getString(15));
				_AbortionMethodMasterVO.setEffectiveFrom(rs.getString(16));
				_AbortionMethodMasterVO.setEffectiveTo(rs.getString(17));
						*/
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}
	
	public void updateAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.HANCT_ABORTION_METHOD_MST";

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
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), abortionMethodMasterVO.getAbortionMethodId());
			populateMAP.put(sq.next(), abortionMethodMasterVO.getSlNo());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	public void modifySaveAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "MODIFYINSERT.HANCT_ABORTION_METHOD_MST";

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
			populateMAP.put(sq.next(), abortionMethodMasterVO.getAbortionMethodId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), abortionMethodMasterVO.getAbortionMethodId());
			populateMAP.put(sq.next(),abortionMethodMasterVO.getAbortionMethod());
           	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(), abortionMethodMasterVO.getIsValid());
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
        	populateMAP.put(sq.next(),abortionMethodMasterVO.getAbortionTypeId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
		
	public String checkDuplicateNameForModify(AbortionMethodMasterVO abortionMethodMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HANCT_ABORTION_METHOD_MST";
		
		
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
	        populateMAP.put(sq.next(),abortionMethodMasterVO.getAbortionMethod());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),abortionMethodMasterVO.getAbortionMethodId());
	     
	       	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	            	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("populateMAP::"+e);
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
	
	public String getAbortionTypeName(String typeID,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
	    String queryKey="SELECT_ABORTIONTYPENAME.HANCT_ABORTION_TYPE_MST";
	    
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
			populateMAP.put(sq.next(),typeID);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			
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
	
}
