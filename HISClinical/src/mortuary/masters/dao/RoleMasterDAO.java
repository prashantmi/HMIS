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
import hisglobal.vo.MortuaryRoleMasterVO;
import hisglobal.vo.UserVO;

public class RoleMasterDAO extends DataAccessObject implements RoleMasterDAOi
{
	public RoleMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	public void create(MortuaryRoleMasterVO mortuaryRoleMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="INSERT.HMRT_ROLE_MST";
		
		
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
	        	populateMAP.put(sq.next(),mortuaryRoleMasterVO.getRoleName());
	        	//populateMAP.put(sq.next(),userVO.getHospitalCode()); @anantpatel
	        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	        	populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	        	populateMAP.put(sq.next(),userVO.getSeatId());
	        	
	        	
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
	public String checkDuplicateRoleName(MortuaryRoleMasterVO MortuaryRoleMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HMRT_ROLE_MST";
		
		
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
	        populateMAP.put(sq.next(),MortuaryRoleMasterVO.getRoleName());
	        
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
		public MortuaryRoleMasterVO getDataForModify(MortuaryRoleMasterVO MortuaryRoleMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		MortuaryRoleMasterVO vo=new MortuaryRoleMasterVO();

		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "SELECT.HMRT_ROLE_MST";
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
			populateMAP.put(sq.next(), MortuaryRoleMasterVO.getRoleID());
			populateMAP.put(sq.next(), MortuaryRoleMasterVO.getSlNo());
			
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
				/*_MortuaryRoleMasterVO.setAutopsyFor(rs.getString(1));
				_MortuaryRoleMasterVO.setAutopsyType(rs.getString(2));
				_MortuaryRoleMasterVO.setMortuaryType(rs.getString(3));
				_MortuaryRoleMasterVO.setEmailId(rs.getString(4));
				_MortuaryRoleMasterVO.setEmpNo(rs.getString(5));
				_MortuaryRoleMasterVO.setLocationDesc(rs.getString(6));
				_MortuaryRoleMasterVO.setMortuaryName(rs.getString(7));
				_MortuaryRoleMasterVO.setRoomNo(rs.getString(8));
				_MortuaryRoleMasterVO.setMortuaryShortName(rs.getString(9));
				_MortuaryRoleMasterVO.setWebsite(rs.getString(10));
				_MortuaryRoleMasterVO.setBlockId(rs.getString(11));
				_MortuaryRoleMasterVO.setBuildingCode(rs.getString(12));
				_MortuaryRoleMasterVO.setFloorId(rs.getString(13));
				_MortuaryRoleMasterVO.setRoomId(rs.getString(14));
				_MortuaryRoleMasterVO.setDepartmentCode(rs.getString(15));
				_MortuaryRoleMasterVO.setEffectiveFrom(rs.getString(16));
				_MortuaryRoleMasterVO.setEffectiveTo(rs.getString(17));
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
	
	public void updateRoleMaster(MortuaryRoleMasterVO MortuaryRoleMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "UPDATE.HMRT_ROLE_MST";

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
			populateMAP.put(sq.next(), MortuaryRoleMasterVO.getRoleID());
			populateMAP.put(sq.next(), MortuaryRoleMasterVO.getSlNo());
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
	
	public void modifySaveRoleMaster(MortuaryRoleMasterVO MortuaryRoleMasterVO, UserVO _UserVO){
	
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "MODIFYINSERT.HMRT_ROLE_MST";

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
			populateMAP.put(sq.next(), MortuaryRoleMasterVO.getRoleID());
			populateMAP.put(sq.next(), MortuaryRoleMasterVO.getRoleID());
			//populateMAP.put(sq.next(), _UserVO.getHospitalCode()); @anantpatel
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),MortuaryRoleMasterVO.getRoleName());
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
        	//populateMAP.put(sq.next(), _UserVO.getHospitalCode()); @anantpatel
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(), MortuaryRoleMasterVO.getIsActive());
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
		
	public String checkDuplicateNameForModify(MortuaryRoleMasterVO MortuaryRoleMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HMRT_ROLE_MST";
		
		
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
	        populateMAP.put(sq.next(),MortuaryRoleMasterVO.getRoleName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),MortuaryRoleMasterVO.getRoleID());
	      
	       	//populateMAP.put(sq.next(), userVO.getHospitalCode()); @anantpatel
	       	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	       	
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("RoleMasterDAO.populateMAP::"+e);
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
