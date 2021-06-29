package opd.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

import hisglobal.persistence.TransactionContext;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ProfileAccessDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ProfileAccessDetailDAO extends DataAccessObject implements ProfileAccessDetailDAOi
{
	public ProfileAccessDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileAccessDetailVO _profileAccessDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_ACCESS_DTL";
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
			if(_profileAccessDtlVO.getUserId()==null) _profileAccessDtlVO.setUserId("");
			populateMAP.put(sq.next(), _profileAccessDtlVO.getUserId());
			populateMAP.put(sq.next(), _profileAccessDtlVO.getProfileId());
			if(_profileAccessDtlVO.getDepartmentUnitCode()==null) _profileAccessDtlVO.setDepartmentUnitCode("");
			populateMAP.put(sq.next(), _profileAccessDtlVO.getDepartmentUnitCode());
			populateMAP.put(sq.next(), _profileAccessDtlVO.getWardCode());
			populateMAP.put(sq.next(), _profileAccessDtlVO.getProfileId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _profileAccessDtlVO.getAccessType());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileAccessDetailDAO:populateMap::" + e);
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
	 * Removing Patient Profile Access Priviledges
	 * @param _profileId Profile Id 
	 * @param _userVO User Detail
	 */
	public void delete(String _profileId, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "DELETE.HPMRT_PROFILE_ACCESS_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMap.put(sq.next(), Config.IS_VALID_DELETED);
			populateMap.put(sq.next(), _userVO.getSeatId());
			populateMap.put(sq.next(), _profileId);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileAccessDetailDAO:populateMap::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//throw new HisDataAccessException("ProfileAccessDetailDAO:delete:: " + e);
		}
	}
	
	/**
	 * Getting Profile Access Priviledges
	 * @param _profileId Profile Id 
	 * @param _userVO User Detail
	 * @return List Parofile Access Priviledges
	 */
	public List<ProfileAccessDetailVO> get(String _profileId, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PROFILES_ACCESSES.HPMRT_PROFILE_ACCESS_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _profileId);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap::" + e);
		}

		List<ProfileAccessDetailVO> lstProfileAccesses = new ArrayList<ProfileAccessDetailVO>();
		ValueObject[] valueObjects = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(ProfileAccessDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstProfileAccesses.add((ProfileAccessDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileAccessDetailDAO:get::Profile Access Priviledges:: " + e);
		}
		return lstProfileAccesses;
	}
	
	
	public void removeUserPriv(ProfileAccessDetailVO _profileAccessDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "DELETE_USER_PRIV.HPMRT_PROFILE_ACCESS_DTL";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			populateMap.put(sq.next(), Config.IS_VALID_DELETED);
			populateMap.put(sq.next(), _userVO.getSeatId());
			populateMap.put(sq.next(), _profileAccessDetailVO.getProfileId());
			populateMap.put(sq.next(), _profileAccessDetailVO.getUserId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileAccessDetailDAO:populateMap::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("ProfileAccessDetailDAO:delete:: " + e);
		}
	}
}
