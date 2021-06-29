package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.ProfileTypeTabMappingVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

public class ProfileTypeTabMappingDAO extends DataAccessObject
{
	public ProfileTypeTabMappingDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	/**
	 *  get deskmenu based on the profile type
	 */
	public List getDeskMenuByProfileType(String profileType,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.DESK_MENU.HPMRT_PROFILE_TYPE_TABMAPPING";
		List deskMenuList=new ArrayList();;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), profileType);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //changedby:NehaRajgariya Date:8Sept2016
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				deskMenuList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
			}
			
		}
		catch (Exception e)
		{
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return deskMenuList;
	}
	
	
	/**
	 *  get deskmenu not added based on the profile type
	 */
	public List getDeskMenuNotAdded(String profileType,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.DESKMENU_NOT_ADDED.GBLT_DESK_MENU_MST";
		List deskMenuList=new ArrayList();;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), OpdConfig.USABILITY_FLAG_TWO);//usablity flag
			populateMAP.put(sq.next(), profileType);
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //changedby:NehaRajgariya Date:8Sept2016
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());			
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next())
			{
				deskMenuList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
			}
			
		}
		catch (Exception e)
		{
			
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return deskMenuList;
	}
	
	
	/**
	 *  insert into profile type tab mapping
	 */
	public void create(ProfileTypeTabMappingVO profileTypeTabMappingVO,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HPMRT_PROFILE_TYPE_TABMAPPING";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), profileTypeTabMappingVO.getProfileType());
			populateMAP.put(sq.next(), profileTypeTabMappingVO.getDeskMenuId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //changedby:NehaRajgariya Date:8Sept2016
			populateMAP.put(sq.next(), profileTypeTabMappingVO.getProfileType());
			populateMAP.put(sq.next(), profileTypeTabMappingVO.getDeskMenuId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //changedby:NehaRajgariya Date:8Sept2016
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), profileTypeTabMappingVO.getProfileTabOrder());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
			
		}
		catch (Exception e)
		{
			 throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}
	
	/**
	 *  insert into profile type tab mapping
	 */
	public void update(ProfileTypeTabMappingVO profileTypeTabMappingVO,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HPMRT_PROFILE_TYPE_TABMAPPING";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), profileTypeTabMappingVO.getProfileType());
			//populateMAP.put(sq.next(), profileTypeTabMappingVO.getDeskMenuId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); //changedby:NehaRajgariya Date:8Sept2016
			//populateMAP.put(sq.next(), profileTypeTabMappingVO.getProfileType());
			//populateMAP.put(sq.next(), profileTypeTabMappingVO.getDeskMenuId());
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		
	}
	
}//end class
