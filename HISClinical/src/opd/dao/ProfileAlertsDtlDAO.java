package opd.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import opd.OpdConfig;
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
import hisglobal.vo.ProfileAlertsDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ProfileAlertsDtlDAO extends DataAccessObject implements ProfileAlertsDtlDAOi
{
	
	public ProfileAlertsDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileAlertsDtlVO profileAlertsDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_ALERTS_DTL";
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
			populateMAP.put(sq.next(), profileAlertsDtlVO.getProfileId());
			populateMAP.put(sq.next(), profileAlertsDtlVO.getAlertName());
			populateMAP.put(sq.next(), profileAlertsDtlVO.getPatAlertId());
			populateMAP.put(sq.next(), profileAlertsDtlVO.getEffectiveFrom());
			populateMAP.put(sq.next(), profileAlertsDtlVO.getRemarks());
			populateMAP.put(sq.next(), profileAlertsDtlVO.getDurationDate());
			//populateMAP.put(sq.next(), profileAlertsDtlVO.getEffectiveTo());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _userVO.getIpAddress());
			
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
	
	
	public ProfileAlertsDtlVO[] fetchPatAlertsDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{
		ProfileAlertsDtlVO[] profileAlertsDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_ALERTS_DTL.HPMRT_PROFILE_ALERTS_DTL";

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
			throw new HisApplicationExecutionException("ProfileAlertsDtlDAO.populateMAP::" + e);
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
			vo = HelperMethods.populateVOfrmRS(ProfileAlertsDtlVO.class, rs);
			profileAlertsDtlVO = new ProfileAlertsDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileAlertsDtlVO[i] = (ProfileAlertsDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileAlertsDtlDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileAlertsDtlVO;
	}
	
	
	public void updateIsValidStatus(ProfileAlertsDtlVO profileAlertsDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_ALERTS_DTL";
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
			populateMAP.put(sq.next(), profileAlertsDtlVO.getProfileId());
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
