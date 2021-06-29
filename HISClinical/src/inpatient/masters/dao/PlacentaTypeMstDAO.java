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
import hisglobal.vo.PlacentaTypeMasterVO;
import hisglobal.vo.UserVO;

public class PlacentaTypeMstDAO extends DataAccessObject implements PlacentaTypeMstDAOi
{
	public PlacentaTypeMstDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	public void create(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HANCT_PLACENTA_TYPE_MST";
		
		
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
	        	populateMAP.put(sq.next(),placentaTypeMasterVO.getPlacentaType());
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	
	        	populateMAP.put(sq.next(),userVO.getSeatId());
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
	public String checkDuplicatePlacentaTypeName(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HANCT_PLACENTA_TYPE_MST";
		
		
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
	        populateMAP.put(sq.next(),placentaTypeMasterVO.getPlacentaType());
	        
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
		public PlacentaTypeMasterVO getDataForModify(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		PlacentaTypeMasterVO vo=new PlacentaTypeMasterVO();

		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.HANCT_PLACENTA_TYPE_MST";
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
			populateMAP.put(sq.next(), placentaTypeMasterVO.getPlacentaTypeId());
			populateMAP.put(sq.next(), placentaTypeMasterVO.getSlNo());
			
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
				
			}
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return vo;
	}
	
	public void updatePlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.HANCT_PLACENTA_TYPE_MST";

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
			populateMAP.put(sq.next(), placentaTypeMasterVO.getPlacentaTypeId());
			populateMAP.put(sq.next(), placentaTypeMasterVO.getSlNo());
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
	
	public void modifySavePlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "MODIFYINSERT.HANCT_PLACENTA_TYPE_MST";

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
			populateMAP.put(sq.next(), placentaTypeMasterVO.getPlacentaTypeId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), placentaTypeMasterVO.getPlacentaTypeId());
			populateMAP.put(sq.next(),placentaTypeMasterVO.getPlacentaType());
           	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(), placentaTypeMasterVO.getIsValid());
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
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
		
	public String checkDuplicateNameForModify(PlacentaTypeMasterVO placentaTypeMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HANCT_PLACENTA_TYPE_MST";
		
		
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
	        populateMAP.put(sq.next(),placentaTypeMasterVO.getPlacentaType());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),placentaTypeMasterVO.getPlacentaTypeId());
	     
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
	
}
