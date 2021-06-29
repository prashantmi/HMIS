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
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileClinicalDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;


public class ProfileClinicalDtlDAO extends DataAccessObject implements ProfileClinicalDtlDAOi
{
	public ProfileClinicalDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileClinicalDtlVO _profileClinicalDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.HPMRT_PROFILE_CLINICAL_DTL";
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
			populateMAP.put(sq.next(), _profileClinicalDtlVO.getProfileId());
			populateMAP.put(sq.next(), _profileClinicalDtlVO.getRecordDate());
			populateMAP.put(sq.next(), _profileClinicalDtlVO.getDeskMenuId());
			populateMAP.put(sq.next(), _profileClinicalDtlVO.getTemplateId());
			populateMAP.put(sq.next(), _profileClinicalDtlVO.getParaValue());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _profileClinicalDtlVO.getParaId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _profileClinicalDtlVO.getRecordView());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileClinicalDtlDAO:populateMap::" + e);
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
	
	public ProfileClinicalDtlVO[] fetchProfileComplaintsDetails(PatientProfileDetailVO _patientProfileDtlVO,String _deskType, String recordView, UserVO _userVO)
	{

		//ProfileAllergyDtlVO[] profileAllergyDtlVO;
		ProfileClinicalDtlVO[] profileClinicalDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKeyIPD = "SELECT.HPMRT_PROFILE_CLINICAL_DTL";
		String queryKeyOPD = "SELECT.HRGT_EPISODE_CLINICAL_DTL";

		try
		{
			if(_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK) )
				query = HelperMethodsDAO.getQuery(filename, queryKeyOPD);
			else
				query = HelperMethodsDAO.getQuery(filename, queryKeyIPD);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if(_deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK) || _deskType.equals(DynamicDeskConfig.DESK_TYPE_CASUALITY_DESK) )
			{
				_populateMap.put(sq.next(), _patientProfileDtlVO.getPatCrNo());
				_populateMap.put(sq.next(),_patientProfileDtlVO.getEpisodeCode());
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			else
			{
				_populateMap.put(sq.next(),_patientProfileDtlVO.getProfileId());
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			}
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("ProfileClinicalDtlDAO.populateMAP::" + e);
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
			vo = HelperMethods.populateVOfrmRS(ProfileClinicalDtlVO.class, rs);
			profileClinicalDtlVO = new ProfileClinicalDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileClinicalDtlVO[i] = (ProfileClinicalDtlVO) vo[i];
				//commented by manisha gangwar date: 24.8.2017
				profileClinicalDtlVO[i].setRecordView(recordView);
				//profileClinicalDtlVO[i].setRecordView(OpdConfig.PATIENT_PROFILE_COMPLAINTS_VIEW_REPORT_DATE_WISE);
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
		return profileClinicalDtlVO;
	}
	
	public ProfileClinicalDtlVO[] fetchProfileComplaintsDetailsDateWise(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{

		//ProfileAllergyDtlVO[] profileAllergyDtlVO;
		ProfileClinicalDtlVO[] profileClinicalDtlVO;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_DATE_WISE.HPMRT_PROFILE_CLINICAL_DTL";

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
			throw new HisApplicationExecutionException("ProfileClinicalDtlDAO.populateMAP::" + e);
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
			vo = HelperMethods.populateVOfrmRS(ProfileClinicalDtlVO.class, rs);
			profileClinicalDtlVO = new ProfileClinicalDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileClinicalDtlVO[i] = (ProfileClinicalDtlVO) vo[i];
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
		return profileClinicalDtlVO;
	}
	
	
	public void updateIsValidStatus(String profileId, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_IS_VALID.HPMRT_PROFILE_CLINICAL_DTL";
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
			populateMAP.put(sq.next(), profileId);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileClinicalDtlDAO:populateMap::" + e);
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
	
	public String fetchRecordView(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		ResultSet rs;
		String recordView="";
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_RECORD_VIEW.HPMRT_PROFILE_CLINICAL_DTL";
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
			populateMAP.put(sq.next(), _patientProfileDtlVO.getProfileId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("ProfileClinicalDtlDAO:populateMap::" + e);
		}
		
		try
		{
	
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(!rs.next())
				recordView="";
			else
			{
				rs.first();
				recordView=rs.getString(1);
			}
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
		return recordView;
	}
	
	

}
