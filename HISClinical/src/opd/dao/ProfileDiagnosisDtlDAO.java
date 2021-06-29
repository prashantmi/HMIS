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
import hisglobal.vo.ProfileDiagnosisDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

/**
 * @author user
 *
 */
public class ProfileDiagnosisDtlDAO extends DataAccessObject implements ProfileDiagnosisDtlDAOi
{
	
	public ProfileDiagnosisDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */

	public void create(ProfileDiagnosisDtlVO _profileDiagnosisTypeVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_DIAGNOSIS_DTL";
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
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getProfileId());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getDiagnosticTypeName());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getDignosisName());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getDiagnosticTypeCode());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getDiagnosticCode());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getRemarks());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getDiagnosisCodeType());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getEpisodeDate());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getRevokeDate());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getEpisodeCode());
			
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDiagnosisDtlDAO:populateMap::" + e);
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
	
	public ProfileDiagnosisDtlVO[] fetchDiagProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{

		ProfileDiagnosisDtlVO[] profileDiagnosisDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_DIAG_DTL.HPMRT_PROFILE_DIAGNOSIS_DTL";

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
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileDiagnosisDtlDAO.populateMAP::" + e);
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
			vo = HelperMethods.populateVOfrmRS(ProfileDiagnosisDtlVO.class, rs);
			profileDiagnosisDtlVO = new ProfileDiagnosisDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileDiagnosisDtlVO[i] = (ProfileDiagnosisDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileDiagnosisDtlVO;
	}
	
	public void updateIsValidStatus(ProfileDiagnosisDtlVO _profileDiagnosisTypeVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_DIAGNOSIS_DTL";
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
			populateMAP.put(sq.next(), _profileDiagnosisTypeVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDiagnosisDtlDAO:populateMap::" + e);
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
