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
import hisglobal.vo.ProfileFooterDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ProfileFooterDtlDAO extends DataAccessObject implements ProfileFooterDtlDAOi
{
	
	public ProfileFooterDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileFooterDtlVO _profileFooterDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_FOOTER_DTL";
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
			populateMAP.put(sq.next(), _profileFooterDtlVO.getProfileId());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getProfileSummary());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _profileFooterDtlVO.getReviewDate());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getReviewDays());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getDisclaimerId());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getDisclaimerDesc1());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getDisclaimerDesc2());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getDisclaimerDesc3());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getSnomdCIdRemarks());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getSnomdPTRemarks());
			populateMAP.put(sq.next(), _profileFooterDtlVO.getDischargeAdvisedBy());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileFooterDtlDAO:populateMap::" + e);
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
	
	public ProfileFooterDtlVO[] fetchProfileFooterDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{
		//ProfileAlertsDtlVO[] profileAlertsDtlVO;
		ProfileFooterDtlVO[] profileFooterDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_FOOTER_DETAILS.HPMRT_PROFILE_FOOTER_DTL";

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
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _patientProfileDtlVO.getProfileId());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileFooterDtlDAO.populateMAP::" + e);
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
			vo = HelperMethods.populateVOfrmRS(ProfileFooterDtlVO.class, rs);
			profileFooterDtlVO = new ProfileFooterDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileFooterDtlVO[i] = (ProfileFooterDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileFooterDtlDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileFooterDtlVO;
	}
	
	public void updateIsValidStatus(ProfileFooterDtlVO profileFooterDetailVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_FOOTER_DTL";
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
			populateMAP.put(sq.next(), profileFooterDetailVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileAlertsDtlDAO:populateMap::" + e);
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
