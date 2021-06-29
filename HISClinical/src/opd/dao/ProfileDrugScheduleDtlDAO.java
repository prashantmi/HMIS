package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileDrugScheduleDtlVO;
import hisglobal.vo.UserVO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

public class ProfileDrugScheduleDtlDAO extends DataAccessObject implements ProfileDrugScheduleDtlDAOi
{
	
	public ProfileDrugScheduleDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/**
	 * Saving Patient Profile Drug Schedule Detail
	 */
	public void create(ProfileDrugScheduleDtlVO drugScheduleDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_DRUG_SCH_DTL";
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
			populateMAP.put(sq.next(), drugScheduleDtlVO.getProfileId());
			populateMAP.put(sq.next(), drugScheduleDtlVO.getSerialNo());
			populateMAP.put(sq.next(), drugScheduleDtlVO.getItemId());
			populateMAP.put(sq.next(), drugScheduleDtlVO.getDoseId());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), drugScheduleDtlVO.getDoseName());
			populateMAP.put(sq.next(), drugScheduleDtlVO.getDoseTime());
			populateMAP.put(sq.next(), drugScheduleDtlVO.getIsEmptyStomach());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), drugScheduleDtlVO.getDoseShift());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDrugScheduleDtlDAO:populateMap::" + e);
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
	
	
	public void updateIsValidStatus(ProfileDrugScheduleDtlVO drugScheduleDtlVO,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_DRUG_SCH_DTL";
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
			populateMAP.put(sq.next(), drugScheduleDtlVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDrugScheduleDtlDAO:populateMap::" + e);
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
	
	
	public List<ProfileDrugScheduleDtlVO> fetchDrugSchedule(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{

		ProfileDrugScheduleDtlVO profileDrugScheduleDtlVO=null;
		List <ProfileDrugScheduleDtlVO> profileDrugScheduleDtlVOList=null;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HPMRT_PROFILE_DRUG_SCH_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			_populateMap.put(sq.next(), _patientProfileDtlVO.getProfileId());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
		
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileDrugAdviceDtlDAO.populateMAP::" + e);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			profileDrugScheduleDtlVOList=new ArrayList<ProfileDrugScheduleDtlVO>();
			rs.beforeFirst();
			while(rs.next()){
				profileDrugScheduleDtlVO=new ProfileDrugScheduleDtlVO();
				HelperMethods.populateVOfrmRS(profileDrugScheduleDtlVO, rs);
				profileDrugScheduleDtlVOList.add(profileDrugScheduleDtlVO);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileDrugScheduleDtlDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileDrugScheduleDtlVOList;
	}

}
