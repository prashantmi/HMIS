package opd.master.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ParameterRangeMasterVO;
import hisglobal.vo.UserVO;

public class ParaRangeMstDAO extends DataAccessObject 
{
	Logger log;
	public ParaRangeMstDAO(TransactionContext _tx)
	{
		super(_tx);
		log=LogManager.getLogger(this.getClass());
	}	

	
	public ParameterRangeMasterVO getparaName(String paraID , UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		ParameterRangeMasterVO parameterRangeMasterVO=new ParameterRangeMasterVO();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECTPARANAME.HGBT_PARA_RANGE_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), paraID);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ParaRangeMstDAO.populateMAP::" + e);
		}
		//String str = new String();
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				parameterRangeMasterVO.setParaName(rs.getString(1));
											
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return parameterRangeMasterVO;
	}
	
	public void saveParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_PARA_RANGE_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), parameterRangeMasterVO.getParaId());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getParaId());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getGenderCode());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getLowAge());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getHighAge());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getLowValue());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getHighValue());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getUnitOfMeasure());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ParaRangeMstDAO.populateMAP::" + e);
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
	
	public void saveModParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{

		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_PARA_RANGE_MST";

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
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), parameterRangeMasterVO.getParaId());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getParaId());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getGenderCode());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getLowAge());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getHighAge());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getLowValue());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getHighValue());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getUnitOfMeasure());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getIsValid());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ParaRangeMstDAO.populateMAP::" + e);
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
	
	public ParameterRangeMasterVO fetchParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		ParameterRangeMasterVO vo=new ParameterRangeMasterVO();
		String filename =OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey ="FETCH.HGBT_PARA_RANGE_MST";

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
			populateMAP.put(sq.next(), parameterRangeMasterVO.getParaId());
			populateMAP.put(sq.next(), parameterRangeMasterVO.getParaRangeId());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ParaRangeMstDAO.populateMAP::" + e);
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
	
	/*public void updateParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_PARA_RANGE_MST";

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
			
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		//	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _drugRouteMstVO.getSerialNo());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ParaRangeMstDAO.populateMAP::" + e);
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
		
	public void saveModParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "MODIFYINSERT.HGBT_PARA_RANGE_MST";

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
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteId());
			populateMAP.put(sq.next(), _drugRouteMstVO.getItemTypeId());
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteName());
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteDesc());
			populateMAP.put(sq.next(), _drugRouteMstVO.getRouteClassification());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _drugRouteMstVO.getIsValid());
			populateMAP.put(sq.next(), _UserVO.getSeatId());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ParaRangeMstDAO.populateMAP::" + e);
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
	
	public String checkDuplicateDrugRouteInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;

		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey ="CHECKDUPLICATEBEFOREINSERT.HGBT_PARA_RANGE_MST";

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
			populateMAP.put(sq.next(), _drugRouteMstVO.getDrugRouteName().trim());
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), _drugRouteMstVO.getItemTypeId());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ParaRangeMstDAO.populateMAP::" + e);
		}
		String record=null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				record=rs.getString(1);
				
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class) throw new HisRecordNotFoundException(e.getMessage());
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return record;
	}
	
*/
	
	public boolean checkRange(ParameterRangeMasterVO parameterRangeMasterVO,UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
		boolean flag=false;
	   Map populateMAP =new HashMap();
	   Sequence sq=new Sequence();
	   String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   String queryKey="SELECT.CHECK_RANGE.HGBT_PARA_RANGE_MST";

	   try{
	   	query =HelperMethodsDAO.getQuery(filename,queryKey);
	   }
	   catch(Exception e){
	   	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   log.info(query);
	   
	   try{   
	   
		populateMAP.put(sq.next(),parameterRangeMasterVO.getLowAge());
	   	populateMAP.put(sq.next(),parameterRangeMasterVO.getHighAge());
	   	populateMAP.put(sq.next(),parameterRangeMasterVO.getLowAge());
	   	populateMAP.put(sq.next(),parameterRangeMasterVO.getHighAge());
	   	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	   	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	  	populateMAP.put(sq.next(),parameterRangeMasterVO.getParaId());
	  	populateMAP.put(sq.next(),parameterRangeMasterVO.getGenderCode());
	  	populateMAP.put(sq.next(),(parameterRangeMasterVO.getParaRangeId()!=null && !parameterRangeMasterVO.getParaRangeId().trim().equalsIgnoreCase(""))?parameterRangeMasterVO.getParaRangeId().trim():"-1");
	  
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.next();
			if (rs.getInt(1)==0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return flag;
	}
	
	
	public boolean checkDuplicateRecord(ParameterRangeMasterVO parameterRangeMasterVO,UserVO _UserVO)
	{
		String query  = "";
		ResultSet rs;
		boolean flag=false;
	   Map populateMAP =new HashMap();
	   Sequence sq=new Sequence();
	   String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
	   String queryKey="SELECT.DUPLICATERECORD.HGBT_PARA_RANGE_MST";

	   try{
	   	query =HelperMethodsDAO.getQuery(filename,queryKey);
	   }
	   catch(Exception e){
	   	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	   }
	   log.info(query);
	   
	   try{   
	   
		populateMAP.put(sq.next(),parameterRangeMasterVO.getGenderCode());
		populateMAP.put(sq.next(),parameterRangeMasterVO.getParaId());
	   	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	   	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	  	populateMAP.put(sq.next(),(parameterRangeMasterVO.getParaRangeId()!=null && !parameterRangeMasterVO.getParaRangeId().trim().equalsIgnoreCase(""))?parameterRangeMasterVO.getParaRangeId().trim():"-1");
	  
	 
	  
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.next();
			if (rs.getInt(1)==0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		catch (HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		
		return flag;
	}
	
	public void updateParaRangeInfo(ParameterRangeMasterVO parameterRangeMasterVO, UserVO _UserVO)
	{
		
		String query  = "";
		Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
        String queryKey="UPDATE.HGBT_PARA_RANGE_MST";

        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        log.info(query);
        
        try
        {
        	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
        	populateMAP.put(sq.next(),parameterRangeMasterVO.getParaId());
        	populateMAP.put(sq.next(),parameterRangeMasterVO.getParaRangeId());
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("ParaRangeMasterDAO.populateMAP::"+e);
        }
        
        try
        {
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e){
        	e.printStackTrace();
        	throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
     
        
	}
	
}
