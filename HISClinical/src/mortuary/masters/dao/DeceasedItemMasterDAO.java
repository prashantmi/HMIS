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
import hisglobal.vo.DeceasedItemMasterVO;
import hisglobal.vo.UserVO;

public class DeceasedItemMasterDAO extends DataAccessObject implements DeceasedItemMasterDAOi
{
	public DeceasedItemMasterDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	
	public void create(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="INSERT.HMRT_DECEASED_ITEM_MST";
		
		
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
	        	populateMAP.put(sq.next(),deceasedItemMasterVO.getItemName());
	        	populateMAP.put(sq.next(),deceasedItemMasterVO.getItemType());
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
	public String checkDuplicateItemName(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAME.HMRT_DECEASED_ITEM_MST";
		
		
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
	        populateMAP.put(sq.next(),deceasedItemMasterVO.getItemName());
	        
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
		public DeceasedItemMasterVO getDataForModify(DeceasedItemMasterVO deceasedItemMasterVO, UserVO _UserVO)
	{
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		DeceasedItemMasterVO vo=new DeceasedItemMasterVO();

		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "SELECT.HMRT_DECEASED_ITEM_MST";
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
			populateMAP.put(sq.next(), deceasedItemMasterVO.getItemCode());
			populateMAP.put(sq.next(), deceasedItemMasterVO.getSlNo());
		
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
				/*_DeceasedItemMasterVO.setAutopsyFor(rs.getString(1));
				_DeceasedItemMasterVO.setAutopsyType(rs.getString(2));
				_DeceasedItemMasterVO.setMortuaryType(rs.getString(3));
				_DeceasedItemMasterVO.setEmailId(rs.getString(4));
				_DeceasedItemMasterVO.setEmpNo(rs.getString(5));
				_DeceasedItemMasterVO.setLocationDesc(rs.getString(6));
				_DeceasedItemMasterVO.setMortuaryName(rs.getString(7));
				_DeceasedItemMasterVO.setRoomNo(rs.getString(8));
				_DeceasedItemMasterVO.setMortuaryShortName(rs.getString(9));
				_DeceasedItemMasterVO.setWebsite(rs.getString(10));
				_DeceasedItemMasterVO.setBlockId(rs.getString(11));
				_DeceasedItemMasterVO.setBuildingCode(rs.getString(12));
				_DeceasedItemMasterVO.setFloorId(rs.getString(13));
				_DeceasedItemMasterVO.setRoomId(rs.getString(14));
				_DeceasedItemMasterVO.setDepartmentCode(rs.getString(15));
				_DeceasedItemMasterVO.setEffectiveFrom(rs.getString(16));
				_DeceasedItemMasterVO.setEffectiveTo(rs.getString(17));
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
	
	public void updateDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO,UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "UPDATE.HMRT_DECEASED_ITEM_MST";

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
			populateMAP.put(sq.next(), deceasedItemMasterVO.getItemCode());
			populateMAP.put(sq.next(), deceasedItemMasterVO.getSlNo());
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
	
	public void modifySaveDeceasedItemMaster(DeceasedItemMasterVO deceasedItemMasterVO, UserVO _UserVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
		String queryKey = "MODIFYINSERT.HMRT_DECEASED_ITEM_MST";

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
			populateMAP.put(sq.next(), deceasedItemMasterVO.getItemCode());
			populateMAP.put(sq.next(), deceasedItemMasterVO.getItemCode());
		    //populateMAP.put(sq.next(), _UserVO.getHospitalCode());
			populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(),deceasedItemMasterVO.getItemName());
        	populateMAP.put(sq.next(),deceasedItemMasterVO.getItemType());
        	populateMAP.put(sq.next(), _UserVO.getSeatId());
            //populateMAP.put(sq.next(), _UserVO.getHospitalCode());	
        	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
        	populateMAP.put(sq.next(), deceasedItemMasterVO.getIsActive());
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
		
	public String checkDuplicateNameForModify(DeceasedItemMasterVO deceasedItemMasterVO,UserVO userVO)
	{
		String query  = "";
		ResultSet rs = null;
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MortuaryConfig.QUERY_FILE_FOR_MORTUARY_MASTER_DAO;
        String queryKey="CHECK_DUPLICATE_NAMEFORMODIFY.HMRT_DECEASED_ITEM_MST";
		
		
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
	        populateMAP.put(sq.next(),deceasedItemMasterVO.getItemName());
	    	populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
	       	populateMAP.put(sq.next(),deceasedItemMasterVO.getItemCode());
	       
	       	//populateMAP.put(sq.next(), userVO.getHospitalCode()); @anantpatel
	       	populateMAP.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
	       	
	        	
	    }
	    catch(Exception e)
	    {
	    	throw new HisApplicationExecutionException("DeceaseditemMasterDAO.populateMAP::"+e);
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
