package opd.dao;

import java.sql.ResultSet;
import java.util.HashMap;
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
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileExtTreatmentDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ProfileExtTreatmentDtlDAO extends DataAccessObject implements ProfileExtTreatmentDtlDAOi
{

	public ProfileExtTreatmentDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileExtTreatmentDtlVO _profileExtTreatmentDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_EXTTREATMENT_DTL";
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
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getProfileId());
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getExtTreatmentId());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getFrequencyId());
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getRemarks());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getExtTreatmentName());
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getStartDay());
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getTreatmentType());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileExtTreatmentDtlDAO:populateMap::" + e);
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
	
	public ProfileExtTreatmentDtlVO[] fetchDischargeExtTreatmentDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{

		//ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO;
		ProfileExtTreatmentDtlVO[] profileExtTreatmentDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_DISCHARGE_EXTERNAL_TEAT_DETAIL.HPMRT_PROFILE_EXTTREATMENT_DTL";

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
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _patientProfileDtlVO.getTreatmentType());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileExtTreatmentDtlDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Diagnosis Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileExtTreatmentDtlVO.class, rs);
			profileExtTreatmentDtlVO = new ProfileExtTreatmentDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileExtTreatmentDtlVO[i] = (ProfileExtTreatmentDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileExtTreatmentDtlDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileExtTreatmentDtlVO;
	}
	
	
	public void updateIsValidStatus(ProfileExtTreatmentDtlVO _profileExtTreatmentDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_EXTTREATMENT_DTL";
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
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _profileExtTreatmentDtlVO.getTreatmentType());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileExtTreatmentDtlDAO:populateMap::" + e);
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
	
}
