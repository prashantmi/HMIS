package mortuary.masters.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.OpinionMasterVO;
import hisglobal.vo.UserVO;

public class OpinionMasterDAO extends DataAccessObject implements OpinionMasterDAOi
{
	public OpinionMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	public void create(OpinionMasterVO opinionMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="INSERT.HMRT_OPINION_MST";
		
		
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
	        	
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode()); @anantpatel
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),"1");	//serial no=1
	        	populateMAP.put(sq.next(),opinionMasterVO.getOpinionName());
	        	populateMAP.put(sq.next(),opinionMasterVO.getOpinionDesc());
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode()); @anantpatel
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("MortuaryMasterDAO.populateMAP::"+e);
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
	public String checkDuplicateOpinionName(OpinionMasterVO opinionMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HMRT_OPINION_MST";
		
		
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
	        populateMAP.put(sq.next(),opinionMasterVO.getOpinionName());
	        
	    	//populateMAP.put(sq.next(), userVO.getHospitalCode()); @anantpatel
	        populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
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
		public OpinionMasterVO getDataForModify(OpinionMasterVO opinionMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		OpinionMasterVO vo=new OpinionMasterVO();

		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "SELECT.HMRT_OPINION_MST";
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
			populateMAP.put(sq.next(), opinionMasterVO.getOpinionCode());
			populateMAP.put(sq.next(), opinionMasterVO.getSlNo());
			
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BloodStorageMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		
			if (rs.next())
			{
				HelperMethods.populateVOfrmRS(vo, rs);
				/*_OpinionMasterVO.setAutopsyFor(rs.getString(1));
				_OpinionMasterVO.setAutopsyType(rs.getString(2));
				_OpinionMasterVO.setMortuaryType(rs.getString(3));
				_OpinionMasterVO.setEmailId(rs.getString(4));
				_OpinionMasterVO.setEmpNo(rs.getString(5));
				_OpinionMasterVO.setLocationDesc(rs.getString(6));
				_OpinionMasterVO.setMortuaryName(rs.getString(7));
				_OpinionMasterVO.setRoomNo(rs.getString(8));
				_OpinionMasterVO.setMortuaryShortName(rs.getString(9));
				_OpinionMasterVO.setWebsite(rs.getString(10));
				_OpinionMasterVO.setBlockId(rs.getString(11));
				_OpinionMasterVO.setBuildingCode(rs.getString(12));
				_OpinionMasterVO.setFloorId(rs.getString(13));
				_OpinionMasterVO.setRoomId(rs.getString(14));
				_OpinionMasterVO.setDepartmentCode(rs.getString(15));
				_OpinionMasterVO.setEffectiveFrom(rs.getString(16));
				_OpinionMasterVO.setEffectiveTo(rs.getString(17));
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
	
	public void updateOpinionMaster(OpinionMasterVO opinionMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "UPDATE.HMRT_OPINION_MST";

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
			populateMAP.put(sq.next(), opinionMasterVO.getOpinionCode());
			populateMAP.put(sq.next(), opinionMasterVO.getSlNo());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldDonCompMstDAO.populateMAP::" + e);
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
	
	public void modifySaveOpinionMaster(OpinionMasterVO opinionMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "MODIFYINSERT.HMRT_OPINION_MST";

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
			populateMAP.put(sq.next(), opinionMasterVO.getOpinionCode());
			populateMAP.put(sq.next(), opinionMasterVO.getOpinionCode());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),opinionMasterVO.getOpinionName());
        	populateMAP.put(sq.next(),opinionMasterVO.getOpinionDesc());
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
        	//populateMAP.put(sq.next(), _UserVO.getHospitalCode()); @anantpatel
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(), opinionMasterVO.getIsActive());
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
		
	public String checkDuplicateNameForModify(OpinionMasterVO opinionMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HMRT_OPINION_MST";
		
		
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
	        populateMAP.put(sq.next(),opinionMasterVO.getOpinionName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),opinionMasterVO.getOpinionCode());
	   
	       	//populateMAP.put(sq.next(), userVO.getHospitalCode()); @anantpatel
	       	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	       	
	        	
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
