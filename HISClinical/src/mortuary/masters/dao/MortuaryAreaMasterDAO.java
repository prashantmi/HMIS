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
import hisglobal.vo.MortuaryAreaMasterVO;
import hisglobal.vo.UserVO;

public class MortuaryAreaMasterDAO extends DataAccessObject implements MortuaryAreaMasterDAOi
{
	public MortuaryAreaMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record in Mortuary Master
	public void create(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="INSERT.HMRT_MORTUARY_AREA_MST";
		
		
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
	        	
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	populateMAP.put(sq.next(),"1");	//serial no=1
	        	populateMAP.put(sq.next(),mortuaryMstVO.getAreaName());
	        	populateMAP.put(sq.next(),mortuaryMstVO.getAreaDesc());
	        	populateMAP.put(sq.next(),mortuaryMstVO.getMortuaryCode());
	        	
	        	populateMAP.put(sq.next(),userVO.getHospitalCode());
	        	
	        	populateMAP.put(sq.next(),mortuaryMstVO.getEmpNo());
	        	populateMAP.put(sq.next(),mortuaryMstVO.getRoomNo());
	        	
	        	populateMAP.put(sq.next(),mortuaryMstVO.getBuildingCode());
	        	populateMAP.put(sq.next(),mortuaryMstVO.getBlockId());
	        	
	        	populateMAP.put(sq.next(),mortuaryMstVO.getFloorId());
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	
	        	populateMAP.put(sq.next(),mortuaryMstVO.getRoomId());
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	populateMAP.put(sq.next(),mortuaryMstVO.getAreaTypeCode());
	        	
	        	
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("MortuaryAreaMasterDAO.populateMAP::"+e);
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
	public String checkDuplicateName(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HMRT_MORTUARY_AREA_MST";
		
		
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
	        populateMAP.put(sq.next(),mortuaryMstVO.getAreaName());
	    	populateMAP.put(sq.next(), userVO.getHospitalCode());
	       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);	
	       	populateMAP.put(sq.next(),mortuaryMstVO.getMortuaryCode());	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("MortuaryAreaMasterDAO.populateMAP::"+e);
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
	
	
	public MortuaryAreaMasterVO getDataForModify(MortuaryAreaMasterVO _MortuaryMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		MortuaryAreaMasterVO vo=new MortuaryAreaMasterVO();

		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "SELECT.HMRT_MORTUARY_AREA_MST";
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
			populateMAP.put(sq.next(), _MortuaryMasterVO.getAreaCode());
			populateMAP.put(sq.next(), _MortuaryMasterVO.getSlNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
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
				/*_MortuaryMasterVO.setAutopsyFor(rs.getString(1));
				_MortuaryMasterVO.setAutopsyType(rs.getString(2));
				_MortuaryMasterVO.setMortuaryType(rs.getString(3));
				_MortuaryMasterVO.setEmailId(rs.getString(4));
				_MortuaryMasterVO.setEmpNo(rs.getString(5));
				_MortuaryMasterVO.setLocationDesc(rs.getString(6));
				_MortuaryMasterVO.setMortuaryName(rs.getString(7));
				_MortuaryMasterVO.setRoomNo(rs.getString(8));
				_MortuaryMasterVO.setMortuaryShortName(rs.getString(9));
				_MortuaryMasterVO.setWebsite(rs.getString(10));
				_MortuaryMasterVO.setBlockId(rs.getString(11));
				_MortuaryMasterVO.setBuildingCode(rs.getString(12));
				_MortuaryMasterVO.setFloorId(rs.getString(13));
				_MortuaryMasterVO.setRoomId(rs.getString(14));
				_MortuaryMasterVO.setDepartmentCode(rs.getString(15));
				_MortuaryMasterVO.setEffectiveFrom(rs.getString(16));
				_MortuaryMasterVO.setEffectiveTo(rs.getString(17));
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
	
	public void updateMortuaryAreaMaster(MortuaryAreaMasterVO _MortuaryMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "UPDATE.HMRT_MORTUARY_AREA_MST";

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
			populateMAP.put(sq.next(), _MortuaryMasterVO.getAreaCode());
			populateMAP.put(sq.next(), _MortuaryMasterVO.getSlNo());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			
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
	
	public void modifySaveMortuaryAreaMaster(MortuaryAreaMasterVO _MortuaryMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "MODIFYINSERT.HMRT_MORTUARY_AREA_MST";

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
			populateMAP.put(sq.next(), _MortuaryMasterVO.getAreaCode());
			populateMAP.put(sq.next(), _MortuaryMasterVO.getAreaCode());
			populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(),_MortuaryMasterVO.getAreaName());
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getAreaDesc());
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getMortuaryCode());
        	
        	populateMAP.put(sq.next(),_UserVO.getHospitalCode());
        	
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getEmpNo());
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getRoomNo());
        	
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getBuildingCode());
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getBlockId());
        	
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getFloorId());
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getIsActive());
        	
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getRoomId());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	populateMAP.put(sq.next(),_MortuaryMasterVO.getAreaTypeCode());
        
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
		
	public String checkDuplicateNameForModify(MortuaryAreaMasterVO mortuaryMstVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HMRT_MORTUARY_AREA_MST";
		
		
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
	        populateMAP.put(sq.next(),mortuaryMstVO.getAreaName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),mortuaryMstVO.getAreaCode());
	       	populateMAP.put(sq.next(), userVO.getHospitalCode());
	       	populateMAP.put(sq.next(),mortuaryMstVO.getMortuaryCode());	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("MortuaryAreaMasterDAO.populateMAP::"+e);
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
