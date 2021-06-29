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
import hisglobal.vo.AnomalyTypeMasterVO;
import hisglobal.vo.IntakeOutputParaMasterVO;
import hisglobal.vo.UserVO;

public class IntakeOutputParaMstDAO extends DataAccessObject implements IntakeOutputParaMstDAOi
{
	public IntakeOutputParaMstDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	


	@Override
	public void saveIntakeOutputParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HIPD_INTAKEOUT_PARA_MST";
		
		
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
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),"1");	//serial no=1
	        	populateMAP.put(sq.next(),inoutparaMasterVO.getParaName());
	        	populateMAP.put(sq.next(),inoutparaMasterVO.getParaType());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	populateMAP.put(sq.next(),inoutparaMasterVO.getConceptId());
	        	populateMAP.put(sq.next(),inoutparaMasterVO.getPrefferedTerm());
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


	@Override
	public String checkDuplicateParaName(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HIPD_INTAKEOUT_PARA_MST";
		
		
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
	        populateMAP.put(sq.next(),inoutparaMasterVO.getParaName());
	        
	    	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
	       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);	
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("AnomalyTypeMasterDAO.populateMAP::"+e);
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



	@Override
	public IntakeOutputParaMasterVO getDataForModify(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		IntakeOutputParaMasterVO vo=new IntakeOutputParaMasterVO();

		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.hipd_intakeout_para_mst";
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
			populateMAP.put(sq.next(), inoutparaMasterVO.getInTakeOutParaId());
			populateMAP.put(sq.next(), inoutparaMasterVO.getSlNo());
			
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



	@Override
	public String checkDuplicateNameForModify(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.hipd_intakeout_para_mst";
		
		
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
	        populateMAP.put(sq.next(),inoutparaMasterVO.getParaName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),inoutparaMasterVO.getInTakeOutParaId());
	      
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


	@Override
	public void updateInoutParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.hipd_intakeout_para_mst";

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
			populateMAP.put(sq.next(), userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), inoutparaMasterVO.getInTakeOutParaId());
			populateMAP.put(sq.next(), inoutparaMasterVO.getSlNo());
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





	@Override
	public void modifySaveInoutParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "MODIFYINSERT.hipd_intakeout_para_mst";

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
			populateMAP.put(sq.next(), inoutparaMasterVO.getInTakeOutParaId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), inoutparaMasterVO.getInTakeOutParaId());
			populateMAP.put(sq.next(),inoutparaMasterVO.getParaName());
           	populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
           	populateMAP.put(sq.next(), inoutparaMasterVO.getIsActive());//isvalid
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(),inoutparaMasterVO.getParaType());
        	populateMAP.put(sq.next(),inoutparaMasterVO.getConceptId());
        	populateMAP.put(sq.next(),inoutparaMasterVO.getPrefferedTerm());
        	
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
	
}
