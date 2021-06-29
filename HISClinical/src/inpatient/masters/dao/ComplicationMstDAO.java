package inpatient.masters.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;

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
import hisglobal.vo.ComplicationMasterVO;
import hisglobal.vo.ParameterRangeMasterVO;
import hisglobal.vo.UserVO;

public class ComplicationMstDAO extends DataAccessObject implements ComplicationMstDAOi
{
	public ComplicationMstDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	

	public void create(ComplicationMasterVO complicationMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="INSERT.HANCT_COMPLICATION_MST";
		
		
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
	        	
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),"1");	//serial no=1
	        	populateMAP.put(sq.next(),complicationMasterVO.getComplication());
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	populateMAP.put(sq.next(),complicationMasterVO.getComplicationTypeId());
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
	public String checkDuplicateCompName(ComplicationMasterVO complicationMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HANCT_COMPLICATION_MST";
		
		
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
	        populateMAP.put(sq.next(),complicationMasterVO.getComplication());
	        populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	    	//populateMAP.put(sq.next(), userVO.getHospitalCode());
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
		public ComplicationMasterVO getDataForModify(ComplicationMasterVO complicationMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		ComplicationMasterVO vo=new ComplicationMasterVO();

		String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "SELECT.HANCT_COMPLICATION_MST";
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
			populateMAP.put(sq.next(), complicationMasterVO.getComplicationId());
			populateMAP.put(sq.next(), complicationMasterVO.getSlNo());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
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
				/*_ComplicationMasterVO.setAutopsyFor(rs.getString(1));
				_ComplicationMasterVO.setAutopsyType(rs.getString(2));
				_ComplicationMasterVO.setMortuaryType(rs.getString(3));
				_ComplicationMasterVO.setEmailId(rs.getString(4));
				_ComplicationMasterVO.setEmpNo(rs.getString(5));
				_ComplicationMasterVO.setLocationDesc(rs.getString(6));
				_ComplicationMasterVO.setMortuaryName(rs.getString(7));
				_ComplicationMasterVO.setRoomNo(rs.getString(8));
				_ComplicationMasterVO.setMortuaryShortName(rs.getString(9));
				_ComplicationMasterVO.setWebsite(rs.getString(10));
				_ComplicationMasterVO.setBlockId(rs.getString(11));
				_ComplicationMasterVO.setBuildingCode(rs.getString(12));
				_ComplicationMasterVO.setFloorId(rs.getString(13));
				_ComplicationMasterVO.setRoomId(rs.getString(14));
				_ComplicationMasterVO.setDepartmentCode(rs.getString(15));
				_ComplicationMasterVO.setEffectiveFrom(rs.getString(16));
				_ComplicationMasterVO.setEffectiveTo(rs.getString(17));
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
	
	public void updateCompMaster(ComplicationMasterVO complicationMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "UPDATE.HANCT_COMPLICATION_MST";

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
			populateMAP.put(sq.next(), complicationMasterVO.getComplicationId());
			populateMAP.put(sq.next(), complicationMasterVO.getSlNo());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
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
	
	public void modifySaveCompMaster(ComplicationMasterVO complicationMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		  String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
		String queryKey = "MODIFYINSERT.HANCT_COMPLICATION_MST";

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
			populateMAP.put(sq.next(), complicationMasterVO.getComplicationId());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(), complicationMasterVO.getComplicationId());
			populateMAP.put(sq.next(),complicationMasterVO.getComplication());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
           //	populateMAP.put(sq.next(), _UserVO.getHospitalCode());
        	populateMAP.put(sq.next(), complicationMasterVO.getIsValid());
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
        	populateMAP.put(sq.next(),complicationMasterVO.getComplicationTypeId());
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
		
	public String checkDuplicateNameForModify(ComplicationMasterVO complicationMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=InpatientConfig.QUERY_FILE_FOR_INPATIENT_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HANCT_COMPLICATION_MST";
		
		
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
	        populateMAP.put(sq.next(),complicationMasterVO.getComplication());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),complicationMasterVO.getComplicationId());
	       	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	       	//populateMAP.put(sq.next(), userVO.getHospitalCode());
	            	
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
