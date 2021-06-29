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
import hisglobal.vo.IncisionTypeMasterVO;
import hisglobal.vo.UserVO;

public class IncisionTypeMasterDAO extends DataAccessObject implements IncisionTypeMasterDAOi
{
	public IncisionTypeMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	//Inserting Record in Mortuary Master
	public void create(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="INSERT.HMRT_INCISION_TYPE_MST";
		
		
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
	        	populateMAP.put(sq.next(),incisionTypeMasterVO.getIncisionType());
	        	populateMAP.put(sq.next(),incisionTypeMasterVO.getDescription());
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode()); @anantpatel
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	        	
	        }
	        catch(Exception e)
	        {
	        	throw new HisApplicationExecutionException("IncisionTypeMasterDAO.populateMAP::"+e);
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
	public String checkDuplicateName(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HMRT_INCISION_TYPE_MST";
		
		
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
	        populateMAP.put(sq.next(),incisionTypeMasterVO.getIncisionType());
	       
	    	//populateMAP.put(sq.next(), userVO.getHospitalCode()); @anantpatel
	        populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	       	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);	
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("IncisionTypeMasterDAO.populateMAP::"+e);
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
	
	
	public IncisionTypeMasterVO getDataForModify(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		IncisionTypeMasterVO vo=new IncisionTypeMasterVO();

		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "SELECT.HMRT_INCISION_TYPE_MST";
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
			populateMAP.put(sq.next(), incisionTypeMasterVO.getIncisionTypeCode());
			populateMAP.put(sq.next(), incisionTypeMasterVO.getSlNo());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			
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
	
	public void updateIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "UPDATE.HMRT_INCISION_TYPE_MST";

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
			populateMAP.put(sq.next(), incisionTypeMasterVO.getIncisionTypeCode());
			populateMAP.put(sq.next(), incisionTypeMasterVO.getSlNo());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			
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
	
	public void modifySaveIncisionTypeMaster(IncisionTypeMasterVO incisionTypeMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "MODIFYINSERT.HMRT_INCISION_TYPE_MST";

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
			populateMAP.put(sq.next(), incisionTypeMasterVO.getIncisionTypeCode());
			populateMAP.put(sq.next(), incisionTypeMasterVO.getIncisionTypeCode());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),incisionTypeMasterVO.getIncisionType());
        	populateMAP.put(sq.next(),incisionTypeMasterVO.getDescription());
        	populateMAP.put(sq.next(),_UserVO.getSeatId());
        	
        	//populateMAP.put(sq.next(),_UserVO.getHospitalCode()); @anantpatel
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(),incisionTypeMasterVO.getIsActive());
        	
        	
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
		
	public String checkDuplicateNameForModify(IncisionTypeMasterVO incisionTypeMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HMRT_INCISION_TYPE_MST";
		
		
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
	        populateMAP.put(sq.next(),incisionTypeMasterVO.getIncisionType());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),incisionTypeMasterVO.getIncisionTypeCode());
	      
	       //	populateMAP.put(sq.next(), userVO.getHospitalCode()); @anantpatel
	       	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("IncisionTypeMasterDAO.populateMAP::"+e);
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
