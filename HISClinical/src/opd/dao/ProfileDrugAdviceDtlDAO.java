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
import hisglobal.vo.ProfileDrugAdviceDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class ProfileDrugAdviceDtlDAO extends DataAccessObject implements ProfileDrugAdviceDtlDAOi
{
	
	public ProfileDrugAdviceDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileDrugAdviceDtlVO _profileDrugAdviceDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_DRUG_ADVICE_DTL";
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
			String startDay=_profileDrugAdviceDtlVO.getStartDay().trim().equals("")?"0":_profileDrugAdviceDtlVO.getStartDay();
			String days=_profileDrugAdviceDtlVO.getDays().equals("")?"0":_profileDrugAdviceDtlVO.getDays();
			int noOfDays=Integer.parseInt(startDay)+
				Integer.parseInt(days) -1 ;
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getProfileId());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getDoseName());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getDrugRouteId());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getDrugId());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getDoseId());
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getFrequencyId());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getDays());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getStartDate());
			//populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getEndDate());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getStartDate()); //start date
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getEndDate()); //end date
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getRemarks());
			//populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getDrugName());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getMedicationName());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getIsEmptyStomach());
			populateMAP.put(sq.next(), _userVO.getUserEmpID());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getIsManagementPlan());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getRxContinue());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getDoseQty());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getIssueQty());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getRequiredQty());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getEntryMode());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getStartDay());
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getProfileId());//For serial no 
			populateMAP.put(sq.next(), _userVO.getHospitalCode());//for serial no
			populateMAP.put(sq.next(),_profileDrugAdviceDtlVO.getMedicationType() );
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDrugAdviceDtlDAO:populateMap::" + e);
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
	
	
	public ProfileDrugAdviceDtlVO[] fetchTreatProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{

		ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_TREAT_DTL.HPMRT_PROFILE_DRUG_ADVICE_DTL";

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
			_populateMap.put(sq.next(), OpdConfig.PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DRUG_DETAIL);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileDrugAdviceDtlDAO.populateMAP::" + e);
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
			vo = HelperMethods.populateVOfrmRS(ProfileDrugAdviceDtlVO.class, rs);
			profileDrugAdviceDtlVO = new ProfileDrugAdviceDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileDrugAdviceDtlVO[i] = (ProfileDrugAdviceDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileDrugAdviceDtlDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileDrugAdviceDtlVO;
	}
	
	
	public void updateIsValidStatus(ProfileDrugAdviceDtlVO _profileDrugAdviceDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_DRUG_ADVICE_DTL";
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
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), OpdConfig.PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DRUG_DETAIL);
			
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDrugAdviceDtlDAO:populateMap::" + e);
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
	
	public void updateIsValidStatusDischarge(ProfileDrugAdviceDtlVO _profileDrugAdviceDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_DRUG_ADVICE_DTL";
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
			populateMAP.put(sq.next(), _profileDrugAdviceDtlVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), OpdConfig.PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DISCHARGE);
			
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileDrugAdviceDtlDAO:populateMap::" + e);
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
	
	public ProfileDrugAdviceDtlVO[] fetchDischargeTreatProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{

		ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_TREAT_DTL.HPMRT_PROFILE_DRUG_ADVICE_DTL";

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
			_populateMap.put(sq.next(), OpdConfig.PROFILE_DRUG_ADVICE_DTL_ENTRY_MODE_DISCHARGE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileDrugAdviceDtlDAO.populateMAP::" + e);
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
			vo = HelperMethods.populateVOfrmRS(ProfileDrugAdviceDtlVO.class, rs);
			profileDrugAdviceDtlVO = new ProfileDrugAdviceDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileDrugAdviceDtlVO[i] = (ProfileDrugAdviceDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileDrugAdviceDtlDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileDrugAdviceDtlVO;
	}
	
	
	
	public String getMaxSerialNo(ProfileDrugAdviceDtlVO profileDrugAdviceDtlVO, UserVO _userVO)
	{
		
		//ProfileDrugAdviceDtlVO[] profileDrugAdviceDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.MAX_SERIAL_NO.HPMRT_PROFILE_DRUG_ADVICE_DTL";
		String maxSerialNo="";
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
			_populateMap.put(sq.next(), profileDrugAdviceDtlVO.getProfileId());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileDrugAdviceDtlDAO.populateMAP::" + e);
		}
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			rs.next();
			maxSerialNo=rs.getString(1);
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("ProfileDrugAdviceDtlDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return maxSerialNo;
	}

}
