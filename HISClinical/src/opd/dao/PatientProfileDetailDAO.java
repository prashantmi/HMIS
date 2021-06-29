package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ProfileOTDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import emr.vo.EHR_PatientProfileDtlVO;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

import emr.vo.EHR_PatientProfileDtlVO;
import mrd.MrdConfig;
import mrd.transaction.dao.MRDDocumentUploadDAO;
import mrd.vo.MRDDocumentUploadVO;
import opd.OpdConfig;

public class PatientProfileDetailDAO extends DataAccessObject implements PatientProfileDetailDAOi
{
	public PatientProfileDetailDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	/** 
	 * Saving Patient Profile
	 * @param _patProfileDtlVO Patient Detail VO
	 * @param _userVO User VO
	 * @return Generated Profile Id 
	 */
	public String create(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.CREATE.HPMRT_PAT_PROFILE_DTL";
		String profileId;
		String[] profile = _patProfileDtlVO.getProfileType().split("\\#");
		String profileType = profile[0];
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
			if(_patProfileDtlVO.getProfileId()==null || _patProfileDtlVO.getProfileId().equals(""))
			{
				Procedure proc = new Procedure(OpdDaoConfig.PROCEDURE_GET_PROFILE_ID);
				proc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
				proc.setReturnType(Types.VARCHAR);
				profileId = (String) proc.execute(super.getTransactionContext().getConnection());
				_patProfileDtlVO.setProfileId(profileId);
			}
			else
				profileId = _patProfileDtlVO.getProfileId();
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatProfileDtlD::=call getProfileId" + e);
		}

		try
		{
			populateMAP.put(sq.next(), profileId);
			populateMAP.put(sq.next(), profileId);
			populateMAP.put(sq.next(), _patProfileDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patProfileDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patProfileDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patProfileDtlVO.getDepartmentUnitCode());
			if(_patProfileDtlVO.getAdmissionNo()==null)	_patProfileDtlVO.setAdmissionNo("");
			populateMAP.put(sq.next(), _patProfileDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patProfileDtlVO.getProfileHeader());
			/*populateMAP.put(sq.next(), _patProfileDtlVO.getProfileType());*/
			populateMAP.put(sq.next(), profileType);
			populateMAP.put(sq.next(), _patProfileDtlVO.getAccessType());
			populateMAP.put(sq.next(), _patProfileDtlVO.getUserLevel());
			populateMAP.put(sq.next(), _patProfileDtlVO.getRemarks());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), _patProfileDtlVO.getProfileStatus());
	
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return profileId;
	}

	/**
	 * Getting Patient Episode Profiles List
	 * @param _patProfileVO Patient Profile Detail VO
	 * @param _userVO User VO
	 * @return List of Patient Profile VO of Previous Profiles
	 */
	public List<PatientProfileDetailVO> getEpisodePatientProfiles(PatientProfileDetailVO _patProfileVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_EPISODE_PROFILES.HPMRT_PAT_PROFILE_DTL";
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
			populateMap.put(sq.next(), _patProfileVO.getPatCrNo());
			populateMap.put(sq.next(), _patProfileVO.getEpisodeCode());
			populateMap.put(sq.next(), _userVO.getSeatId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			//Commented by Akash Singh [as per Sir discussed] on date 02-03-2015
			//populateMap.put(sq.next(), OpdConfig.PROFILE_TYPE_DISCHARGE);
			populateMap.put(sq.next(), _patProfileVO.getAdmissionNo());
			populateMap.put(sq.next(), _userVO.getSeatId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			populateMap.put(sq.next(), OpdConfig.PROFILE_TYPE_DISCHARGE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
		}

		List<PatientProfileDetailVO> lstPatProfiles = new ArrayList<PatientProfileDetailVO>();
		ValueObject[] valueObjects = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientProfileDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstPatProfiles.add((PatientProfileDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientProfileDetailDAO:getEpisodePatientProfiles::Patient Episode Profiles:: " + e);
		}
		return lstPatProfiles;
	}

	/**
	 * Modifying Patient Profile 
	 * @param _patProfileDtlVO
	 * @param _userVO
	 */
	public void updateOld(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE.OLD_RECORD.HPMRT_PAT_PROFILE_DTL";
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
			populateMap.put(sq.next(), _patProfileDtlVO.getProfileId());
			populateMap.put(sq.next(), _patProfileDtlVO.getSerialNo());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
	}

	/**
	 * Removing Patient Profile
	 * @param _patProfileDtlVO Patient Profile Detail 
	 * @param _userVO User Detail
	 */
	public void delete(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "DELETE.HPMRT_PAT_PROFILE_DTL";
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
			populateMap.put(sq.next(), _patProfileDtlVO.getProfileId());
			populateMap.put(sq.next(), _patProfileDtlVO.getSerialNo());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
	}

	/**
	 * Getting Patient Episode Profiles List For Inbox
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<PatientProfileDetailVO> getPatientProfilesForInbox(String _patCrNo, String _unitCode, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		ResultSet rs = null;
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.PAT_PROFILES_FOR_INBOX.HPMRT_PAT_PROFILE_DTL";
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
			populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			//populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL);			
			//populateMap.put(sq.next(), _userVO.getUserLevel());
			
			populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			//populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
			//populateMap.put(sq.next(), _userVO.getUserLevel());
			populateMap.put(sq.next(), _unitCode);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			//populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
			populateMap.put(sq.next(), _userVO.getSeatId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		/*	populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OWNING_UNIT);
			populateMap.put(sq.next(), _userVO.getUserLevel());
			populateMap.put(sq.next(), _unitCode);*/
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
		}

		List<PatientProfileDetailVO> lstProfiles = new ArrayList<PatientProfileDetailVO>();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientProfileDetailDAO:getEpisodePatientProfiles::Patient Episode Profiles:: " + e);
		}

		try
		{
			while(rs.next())
			{
				PatientProfileDetailVO vo = new PatientProfileDetailVO();
				vo.setProfileId(rs.getString(1));
				vo.setProfileHeader(rs.getString(2));
				lstProfiles.add(vo);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO::getPatientProfilesForInbox"+e);
		}
		return lstProfiles;
	}
	
	public void updateProfileStatus(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_PROFILE_STATUS.HPMRT_PAT_PROFILE_DTL";
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
			
			populateMap.put(sq.next(), OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED);
			if(_patProfileDtlVO.getAccessType()==null) _patProfileDtlVO.setAccessType("");
			populateMap.put(sq.next(), _patProfileDtlVO.getAccessType());
			if(_patProfileDtlVO.getUserLevel()==null) _patProfileDtlVO.setUserLevel("");
			populateMap.put(sq.next(), _patProfileDtlVO.getUserLevel());
			//populateMap.put(sq.next(), _patProfileDtlVO.getProfileDataPdf());
			populateMap.put(sq.next(), _patProfileDtlVO.getProfileId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
	}
	
	
	public void updateProfileHeaderDetail(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_PROFILE_HEADER.HPMRT_PAT_PROFILE_DTL";
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
			
			populateMap.put(sq.next(), _patProfileDtlVO.getProfileHeader());
			populateMap.put(sq.next(), _patProfileDtlVO.getProfileId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
	}
	
	
	public ProfileInvestigationVO[] retrieveEpisodeInvestigationForProfile(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		ProfileInvestigationVO[] profileInvestigationVOs;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_INVESTIGATION.HIVT_REQUISITION_DTL.HIVT_REQUISITION_HEADER";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			String orderBy = " ORDER BY HGNUM_VISITNO DESC";
			if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED) || profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE))
			{
				query= query+orderBy;
				
			}
			else if (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				query= query+"AND HGNUM_VISITNO=?"+orderBy;
				
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED) || profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE))
			{
				//_populateMap.put(sq.next(), _userVO.getHospitalCode());  // commented on 10.01.2017
				//_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), _crNo);
				//_populateMap.put(sq.next(), voDP.getEpisodeCode());  //commneted on 10.01.2017
			}
			else if (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), _crNo);
				_populateMap.put(sq.next(), voDP.getEpisodeCode());
				_populateMap.put(sq.next(), voDP.getEpisodeVisitNo());
			}			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Investigation Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileInvestigationVO.class, rs);
			profileInvestigationVOs = new ProfileInvestigationVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileInvestigationVOs[i] = (ProfileInvestigationVO) vo[i];
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
		return profileInvestigationVOs;
	}
	
	
	public String fetchProfileRestrictedCapCount(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_COUNT.HPMRT_PROFILE_RESTRICTED_CAT";
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
			
			populateMAP.put(sq.next(), _patientProfileDtlVO.getPatCategoryCode());
			populateMAP.put(sq.next(), _patientProfileDtlVO.getProfileType());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			rs.first();
				return rs.getString(1);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
	}
	
	public ProfileGroupDtlVO[] fetchGroupIdProfileGroupDtl(ProfileAccessPolicyVO profileAccessPolicy, UserVO _userVO)
	{
		//ProfileInvestigationVO[] profileInvestigationVOs;
		ProfileGroupDtlVO[] profileGroupDtlVOs;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_ACCESS_RECORDID.HPMRT_PROFILE_GROUP_DTL";

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
			_populateMap.put(sq.next(), profileAccessPolicy.getProfileGroupId());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(),  Config.IS_VALID_ACTIVE);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				//throw new HisRecordNotFoundException("No Policy Group Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileGroupDtlVO.class, rs);
			profileGroupDtlVOs = new ProfileGroupDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileGroupDtlVOs[i] = (ProfileGroupDtlVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileGroupDtlVOs;
	}
	
	public ProfileAccessPolicyVO fetchAccessTypeProfileAccessPolicy(PatientProfileDetailVO _patientProfileDtlVO,UserVO _userVO){
		{
			//DoctorCallBookVO callBookVO=new DoctorCallBookVO();
			ProfileAccessPolicyVO profileAccessPolicyVO=new ProfileAccessPolicyVO();
			String query = "";
			ResultSet rs;
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT_GROUP_ID.HPMRT_PROFILE_GROUP_DTL";
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
				populateMAP.put(sq.next(), _patientProfileDtlVO.getDepartmentUnitCode());
				populateMAP.put(sq.next(), _patientProfileDtlVO.getProfileType());
				populateMAP.put(sq.next(), _patientProfileDtlVO.getPolicyType());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("PatientProfileDetailDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				rs.beforeFirst();
				if (rs.next())
				{
					HelperMethods.populateVOfrmRS(profileAccessPolicyVO,rs);
					
				}
				
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException("No Group Id Found");	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return profileAccessPolicyVO;
		}
	}
	
	
	public void updateAccessType(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_ACCESS_TYPE.HPMRT_PAT_PROFILE_DTL";
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
			
			populateMap.put(sq.next(), _patProfileDtlVO.getAccessType());
			populateMap.put(sq.next(), _patProfileDtlVO.getUserLevel());
			populateMap.put(sq.next(), _patProfileDtlVO.getProfileId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
	}
	
	public void updateIsValid(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "UPDATE_ISVALID.HPMRT_PAT_PROFILE_DTL";
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
			populateMap.put(sq.next(), _patProfileDtlVO.getProfileId());
			//populateMap.put(sq.next(), _patProfileDtlVO.getSerialNo());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("EpisodeDAO:retrieveByCrNo::Episode Details:: " + e);
		}
	}
	
	public ProfileInvestigationVO[] retrieveAdmissionInvestigationForProfile(String _crNo,PatientDetailVO voDP, UserVO _userVO)
	{
		ProfileInvestigationVO[] profileInvestigationVOs;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_INVESTIGATION_IPD.HIVT_REQUISITION_DTL.HIVT_REQUISITION_HEADER";

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
			/*_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());*/
			_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), voDP.getPatAdmNo());
			
			_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), voDP.getPatAdmNo());
			//_populateMap.put(sq.next(), _episodeCode);  // Commented By Pawan Kumar B N
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Investigation Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileInvestigationVO.class, rs);
			profileInvestigationVOs = new ProfileInvestigationVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileInvestigationVOs[i] = (ProfileInvestigationVO) vo[i];
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
		return profileInvestigationVOs;
	}
	
	/**
	 * Getting Patient All Profiles List For Inbox
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<PatientProfileDetailVO> getPatientProfilesForAll(String _patCrNo, String _unitCode, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		ResultSet rs = null;
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HPMRT_PAT_PROFILE_DTL.PROFILE_VIEW_DETAIL_ALL";
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
			populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			/*populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL);			
			populateMap.put(sq.next(), _userVO.getUserLevel());*/
			
			populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
			populateMap.put(sq.next(), _userVO.getUserLevel());
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			populateMap.put(sq.next(), _unitCode);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
			populateMap.put(sq.next(), _userVO.getHospitalCode());
			populateMap.put(sq.next(), _userVO.getSeatId());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		/*	populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OWNING_UNIT);
			populateMap.put(sq.next(), _userVO.getUserLevel());
			populateMap.put(sq.next(), _unitCode);*/
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
		}

		List<PatientProfileDetailVO> lstProfiles = new ArrayList<PatientProfileDetailVO>();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientProfileDetailDAO:getEpisodePatientProfiles::Patient Episode Profiles:: " + e);
		}

		try
		{
			while(rs.next())
			{
				PatientProfileDetailVO vo = new PatientProfileDetailVO();
				vo.setProfileId(rs.getString(1));
				vo.setProfileHeader(rs.getString(2));
				vo.setPatCrNo(rs.getString(3));
				vo.setDepartmentUnitCode(rs.getString(4));
				vo.setPatientName(rs.getString(5));
				vo.setDepartmentUnit(rs.getString(6));
				
				lstProfiles.add(vo);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO::getPatientProfilesForInbox"+e);
		}
		return lstProfiles;
	}
	
	/**
	 * Getting Patient Episode Profiles List For EMR
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<PatientProfileDetailVO> getPatientProfilesForEMR(String _patCrNo,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		String query = "";
		Map populateMap = new HashMap();
		Sequence sq = new Sequence();
		ResultSet rs = null;
		
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT.HPMRT_PAT_PROFILE_DTL.EMR";
		String orderBy=" ORDER BY a.GDT_ENTRY_DATE desc";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(accessType.equals(MrdConfig.EPR_DISPLAY_ALL_RECORD_NO)){
				String inArg="";
				for(int i=0;i<departmentUnitArray.length;i++){
					inArg+=","+departmentUnitArray[i];
				}
				inArg=inArg.replaceFirst(",","");
				query+="  and (SELECT hgnum_deptunitcode FROM hrgt_episode_dtl WHERE hrgnum_episode_code = a.hrgnum_episode_code "
						+ "and HRGNUM_PUK=a.HRGNUM_PUK and HRGNUM_VISIT_NO=a.HRGNUM_VISIT_NO and gnum_isvalid=a.gnum_isvalid AND "
						+ "gnum_hospital_code = a.gnum_hospital_code) in ("+inArg+") ";
			}
			//query+=orderBy;
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		
		try
		{
			populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), _patCrNo);
			//populateMap.put(sq.next(), _userVO.getHospitalCode());
			
			
		/*	populateMap.put(sq.next(), _patCrNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OWNING_UNIT);
			populateMap.put(sq.next(), _userVO.getUserLevel());
			populateMap.put(sq.next(), _unitCode);*/
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
		}

		List<PatientProfileDetailVO> lstProfiles = new ArrayList<PatientProfileDetailVO>();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			/*if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else*/ throw new HisDataAccessException("PatientProfileDetailDAO:getEpisodePatientProfiles::Patient Episode Profiles:: " + e);
		}

		try
		{
			while(rs.next())
			{
				
				PatientProfileDetailVO vo = new PatientProfileDetailVO();
				vo.setProfileId(rs.getString(3));
				vo.setProfileHeader(rs.getString(4));
				vo.setEntryDate(rs.getString(7));
				vo.setIsSinglePageFlag(rs.getString(12));
				vo.setPatCrNo(_patCrNo);
				vo.setEpisodeCode(rs.getString(5));
				vo.setEpisodeVisitNo(rs.getString(6));
				lstProfiles.add(vo);
			}
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO::getPatientProfilesForInbox"+e);
		}
		return lstProfiles;
	}
	
	
	public PatientProfileDetailVO[] getPreviousProfileDetails(String _crNo,UserVO _userVO)
	{
		PatientProfileDetailVO[] patientProfileDetailVOs;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_PREVIOUS_PROFILE.HPMRT_PAT_PROFILE_DTL";

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
			_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_TO_ALL);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _userVO.getUserLevel());
			_populateMap.put(sq.next(), OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED);
			
			_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED);
			_populateMap.put(sq.next(), _userVO.getUserLevel());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			
			_populateMap.put(sq.next(), _crNo);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), OpdConfig.PATIENT_PROFILE_ACCESS_TYPE_OTHER);
			_populateMap.put(sq.next(), _userVO.getUserLevel());
			_populateMap.put(sq.next(), OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _userVO.getSeatId());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Profile Detail Found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(PatientProfileDetailVO.class, rs);
			patientProfileDetailVOs = new PatientProfileDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				patientProfileDetailVOs[i] = (PatientProfileDetailVO) vo[i];
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
		return patientProfileDetailVOs;
	}
	
	public ProfileOTDetailVO[] getPatientOperationDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		
		ProfileOTDetailVO[] profileOTDetailVOs;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_PATIENT_OPERATION_DETAIL.HOTT_OP_REQUEST_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			String orderBy = " ORDER BY ot.hotdt_operation_date DESC";
			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{
				query= query+orderBy;
				
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{
				query= query+" AND HRGNUM_VISIT_NO=?"+orderBy;
				
			}				
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if((profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_CUSTOMIZED)) || (profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_WHOLE_EPISODE)))
			{						
				_populateMap.put(sq.next(), OpdConfig.OT_NAME_ACTUAL);
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), _crNo);
				_populateMap.put(sq.next(), voDP.getEpisodeCode());
			}
			else if(profileGenerationMode.equals(OpdConfig.PROFILE_TYPE_GENERATION_MODE_AUTOMATICE_AT_CURRENT_VISIT))
			{						
				_populateMap.put(sq.next(), OpdConfig.OT_NAME_ACTUAL);
				_populateMap.put(sq.next(), _userVO.getHospitalCode());
				_populateMap.put(sq.next(), _crNo);
				_populateMap.put(sq.next(), voDP.getEpisodeCode());
				_populateMap.put(sq.next(), voDP.getEpisodeVisitNo());
			}	
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Operation Theatre Detail found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileOTDetailVO.class, rs);
			profileOTDetailVOs = new ProfileOTDetailVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileOTDetailVOs[i] = (ProfileOTDetailVO) vo[i];
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientProfileDetailDAO:retrieveByCrNo::Episode Details:: " + e);
		}
		return profileOTDetailVOs;
	}
	
	// Added by VASU on 06-Nov-2017
	public ProfileInvestigationVO[] retrieveTestIPDInvestigationForProfile(String _crNo, String _admissionNo, UserVO _userVO,String age,String _reqDno)
	{
		ProfileInvestigationVO[] profileInvestigationVOs;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_INVESTIGATION_IPD_TEST.HIVT_RESULTVALIDATION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(_reqDno!="" && _reqDno!= null){
			query=query.replace("$$$"," where x.hivtnum_req_dno in("+_reqDno+") ORDER BY x.hivt_entry_date,testname, hivtnum_order,parametername");
			}
			else
			{
				query=query.replace("$$$","ORDER BY x.hivt_entry_date,testname, hivtnum_order,parametername");
			}
			
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			//_populateMap.put(sq.next(), _reqDno);
			//_populateMap.put(sq.next(), _userVO.getHospitalCode());
			//_populateMap.put(sq.next(), _userVO.getHospitalCode());
//			_populateMap.put(sq.next(), _crNo);
			//_populateMap.put(sq.next(), _admissionNo);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Investigation Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileInvestigationVO.class, rs);
			profileInvestigationVOs = new ProfileInvestigationVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileInvestigationVOs[i] = (ProfileInvestigationVO) vo[i];
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
		return profileInvestigationVOs;
	}
	
	//Added by VASU on 06-Nov-2017
	public ProfileInvestigationVO[] retrieveTestOPDInvestigationForProfile(String _crNo, String _episodeCode, UserVO _userVO,String age,String _reqDno)
	{
		ProfileInvestigationVO[] profileInvestigationVOs;
		String query = "";
		Map _populateMap = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "SELECT_INVESTIGATION_OPD_TEST.HIVT_RESULTVALIDATION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			if(_reqDno!=null && _reqDno!=""){
			query=query.replace("$$$"," where HIVTNUM_REQ_DNO in("+_reqDno+") ORDER BY visitdate");
			}
			else
			{
				query=query.replace("$$$","order by visitdate");
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(),age );
			_populateMap.put(sq.next(),age );
			_populateMap.put(sq.next(),age );
			_populateMap.put(sq.next(),age );
			_populateMap.put(sq.next(),age );
			_populateMap.put(sq.next(),age );
			_populateMap.put(sq.next(),age );
			_populateMap.put(sq.next(),age );
			
//			_populateMap.put(sq.next(), _crNo);
			//_populateMap.put(sq.next(), _episodeCode);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("PatientDAO.populateMAP::" + e);
		}

		ValueObject[] vo = {};
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, _populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Investigation Record found");
			}
			rs.beforeFirst();
			vo = HelperMethods.populateVOfrmRS(ProfileInvestigationVO.class, rs);
			profileInvestigationVOs = new ProfileInvestigationVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				profileInvestigationVOs[i] = (ProfileInvestigationVO) vo[i];
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
		return profileInvestigationVOs;
	}
	
	//Added by Vasu on 05.Nov.2018
	public String savePatientProfileDtl(EHR_PatientProfileDtlVO _patProfileDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
		String queryKey = "INSERT.CREATE.HPMRT_PAT_PROFILE_DTL_FOR_SINGLE_PAGE_DISCHARGE";
		String profileId;
		//String[] profile = _patProfileDtlVO.getProfileType().split("\\#");
		//String profileType = profile[0];
		//String profileType = "16";
		
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
			if(_patProfileDtlVO.getProfileId()==null || _patProfileDtlVO.getProfileId().equals(""))
			{
				Procedure proc = new Procedure(OpdDaoConfig.PROCEDURE_GET_PROFILE_ID);
				proc.addInParameter(1, Types.VARCHAR, _userVO.getHospitalCode());
				proc.setReturnType(Types.VARCHAR);
				profileId = (String) proc.execute(super.getTransactionContext().getConnection());
				_patProfileDtlVO.setProfileId(profileId);
			}
			else
				profileId = _patProfileDtlVO.getProfileId();
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatProfileDtlD::=call getProfileId" + e);
		}

		try
		{
			populateMAP.put(sq.next(), profileId);
			populateMAP.put(sq.next(), profileId);
			populateMAP.put(sq.next(), _patProfileDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), _patProfileDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), _patProfileDtlVO.getEpisodeVisitNo());
			populateMAP.put(sq.next(), _patProfileDtlVO.getDepartmentUnitCode());
			if(_patProfileDtlVO.getAdmissionNo()==null)	_patProfileDtlVO.setAdmissionNo("");
			populateMAP.put(sq.next(), _patProfileDtlVO.getAdmissionNo());
			populateMAP.put(sq.next(), _patProfileDtlVO.getProfileHeader());
			/*populateMAP.put(sq.next(), _patProfileDtlVO.getProfileType());*/
			populateMAP.put(sq.next(), _patProfileDtlVO.getProfileType());
			populateMAP.put(sq.next(), _patProfileDtlVO.getAccessType());
			populateMAP.put(sq.next(), _patProfileDtlVO.getUserLevel());
			populateMAP.put(sq.next(), _patProfileDtlVO.getRemarks());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(),OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED);
			populateMAP.put(sq.next(), _patProfileDtlVO.getHtmlPreview());
			populateMAP.put(sq.next(), _patProfileDtlVO.getIsSinglePageFlag());	
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return profileId;
	}
    
	
	  //Added by Vasu on 13.Nov.2018
		public String getExistingPatientProfileId(EHR_PatientProfileDtlVO _patProfileDtlVO, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "SELECT.HPMRT_PAT_PROFILE_DTL_FOR_SINGLE_PAGE_DISCHARGE";
			String profileId = "null";
			//String profileType = "17";
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
			
				populateMAP.put(sq.next(), _patProfileDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patProfileDtlVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patProfileDtlVO.getEpisodeVisitNo());
				//populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
				//populateMAP.put(sq.next(), _userVO.getSeatId());
				populateMAP.put(sq.next(), _userVO.getHospitalCode());
				populateMAP.put(sq.next(), _patProfileDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patProfileDtlVO.getEpisodeCode());
				populateMAP.put(sq.next(), _patProfileDtlVO.getEpisodeVisitNo());
				
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
			}
			ValueObject[] vo = {};
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				//while(rs.next()){
				//profileId = Integer.toString(rs.getInt(1));
				
				//}
				
				if (rs.next())
					HelperMethods.populateVOfrmRS(_patProfileDtlVO, rs);
/*				rs.beforeFirst();
				vo = HelperMethods.populateVOfrmRS(EHR_PatientProfileDtlVO.class, rs);
*/			}catch (Exception e){
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
			
			return _patProfileDtlVO.getProfileId();//profileId;		
			
		}
		
		
		//Added by Dheeraj on 05-Dec-2018 to save Patient Profile to Postgres
		
		public void savePatientProfile(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
		{
			
			PreparedStatement ps = null;
			Connection conn	=	super.getTransactionContext().getConnection();
			try
			{
				if(_patProfileDtlVO.getProfileDataPdf()!=null)
				{
				 String query = "update hpmrt_pat_profile_dtl SET gbyte_profile_pdf_data=?, hrgstr_profile_html_data=? where HRGNUM_PUK=? and hpmrnum_profile_id = ?";
			   	 ps = conn.prepareStatement(query);
			   	 InputStream iss = new ByteArrayInputStream(_patProfileDtlVO.getProfileDataPdf());	
			   	 ps.setBinaryStream(1,iss,_patProfileDtlVO.getProfileDataPdf().length);
			   	 ps.setString(2,_patProfileDtlVO.getProfileData());
			   	 ps.setString(3,_patProfileDtlVO.getPatCrNo());
			   	 ps.setString(4,_patProfileDtlVO.getProfileId() );
			   	 ps.executeUpdate();
				}
			}
			catch(Exception e)
			   	{
			   		
			   		try 
			   		{  ps.close(); ps =null;  
			   		//throw new Exception("error in saving Patient profile to postgres...terminated unsuccesfully");
			   		} 
			   		catch( SQLException se ) { }
			   	}
				
		}
		
		
		//added by Dheeraj on 05-Dec-2018 to fetch Patient Profile from Postgres
		public byte[] fetchPatientProfile(PatientProfileDetailVO _patProfileDtlVO) 
		{
			PreparedStatement ps = null;
			Connection conn	=	super.getTransactionContext().getConnection();
			byte[] imgBytes = null;
			InputStream fis=null;
			try
			{
				
				
				String query = "select gbyte_profile_pdf_data from hpmrt_pat_profile_dtl where hrgnum_puk = ? and hpmrnum_profile_id = ? and gnum_isvalid=1";
				 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			   	 ps.setString(1,_patProfileDtlVO.getPatCrNo());
			   	ps.setString(2,_patProfileDtlVO.getProfileId());
				 ResultSet resultSet = ps.executeQuery();
				 
				 if(resultSet!=null && resultSet.next())
					{
					 Base64 codec = new Base64();
					 imgBytes = Base64.decodeBase64(resultSet.getBytes(1));
					  imgBytes = resultSet.getBytes(1);
					  if(imgBytes!=null)
					  fis = new ByteArrayInputStream(imgBytes); 
						
					  
					}
			}
			catch(Exception e)
			   	{
			   		
			   		try 
			   		{  ps.close(); ps =null; 
			   		//throw new Exception("error in saving image to postgres...terminated unsuccesfully");
			   		} 
			   		catch( SQLException se ) {  }
			   	}
			return imgBytes;
		}
		
		
		public String fetchHtmlPatientProfile(PatientProfileDetailVO _patProfileDtlVO) 
		{
			PreparedStatement ps = null;
			Connection conn	=	super.getTransactionContext().getConnection();
			String htmlData = "";
			InputStream fis=null;
			try
			{
				
				
				String query = "select hrgstr_profile_html_data from hpmrt_pat_profile_dtl where hrgnum_puk = ? and hpmrnum_profile_id = ? and gnum_isvalid=1";
				 ps	= conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			   	 ps.setString(1,_patProfileDtlVO.getPatCrNo());
			   	ps.setString(2,_patProfileDtlVO.getProfileId());
				 ResultSet resultSet = ps.executeQuery();
				 
				 if(resultSet!=null && resultSet.next())
					{
					 //Base64 codec = new Base64();
					 //htmlData = new String(DatatypeConverter.parseBase64Binary(resultSet.getString(0)));
					 htmlData = resultSet.getString(1);
					}
			}
			catch(Exception e)
			   	{
			   		
			   		try 
			   		{  ps.close(); ps =null; 
			   		//throw new Exception("error in saving image to postgres...terminated unsuccesfully");
			   		} 
			   		catch( SQLException se ) {  }
			   	}
			return htmlData;
		}
		
		//Added by Vasu on 17.Jan.2019
		public void updateProfileDetail(EHR_PatientProfileDtlVO _patProfileDtlVO, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = OpdConfig.QUERY_FILE_FOR_OPD_DAO;
			String queryKey = "UPDATE.HPMRT_PAT_PROFILE_DTL_FOR_SINGLE_PAGE_DISCHARGE";
			//String profileType = "16";
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
				populateMAP.put(sq.next(),  _patProfileDtlVO.getProfileId());
				populateMAP.put(sq.next(), _patProfileDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patProfileDtlVO.getProfileType());
				populateMAP.put(sq.next(),  _patProfileDtlVO.getProfileId());
				populateMAP.put(sq.next(), _patProfileDtlVO.getPatCrNo());
				populateMAP.put(sq.next(), _patProfileDtlVO.getProfileType());
			}
			catch (Exception e)
			{
				throw new HisDataAccessException("PatientProfileDetailDAO:populateMap(_patientVO,populateMAP)::" + e);
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
		
		//Added By Shweta on 09-MAY-2019
		public List<DocumentUploadDtlVO> getDocumentUploadEssentials(String patCrNo,String episodeCode, UserVO _userVO)
		{
			DocumentUploadDtlVO[] documentUploadDtlVOs=null;
			
			ValueObject vo[]= null;
			String query  = "";
	        Map populateMAP =new HashMap();
	        Sequence sq=new Sequence();
	        String filename=OpdConfig.QUERY_FILE_FOR_OPD_ESSENTIALDAO;
	        String queryKey="SELECT.DOCMENT_DETAIL_BY_CRNO.HOPT_DOC_UPLOAD_DTL";
	        
	        
	        try{
	        	query =HelperMethodsDAO.getQuery(filename,queryKey);
	        	
	        }
	        catch(Exception e){
	        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	        }
	         populateMAP.put(sq.next(),patCrNo);
	         //populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	         //populateMAP.put(sq.next(),episodeCode);
	         //added for no episode--shruti shail
	         //populateMAP.put(sq.next(),patCrNo);
	         //populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
	         
	         List<DocumentUploadDtlVO> lstuploadeddoc = new ArrayList<DocumentUploadDtlVO>();
	     	try{
	     		
	 			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);	
	 			while(rs.next())
				{
	 				DocumentUploadDtlVO docvo = new DocumentUploadDtlVO();
	 				docvo.setDocumentTitle(rs.getString(3));//1));
	 				docvo.setEntryDate(rs.getString(6));//2));
	 				docvo.setDocumentCode(rs.getString(2));//3));
					//vo.setDepartmentUnitCode(rs.getString(4));
					//vo.setDocumentTypeName(rs.getString(5));
					docvo.setFileType(rs.getString(12));//6));
					
					lstuploadeddoc.add(docvo);
				}
	 	 	    /*if(!rs.next()){
	 	 	    	throw new HisRecordNotFoundException("No Previous Document");	 	    
	 	 	    }*/
	 	 	    rs.beforeFirst();
	 	 	   
	 	 	  vo=HelperMethods.populateVOfrmRS(DocumentUploadDtlVO.class, rs);
	 	 	  
	 	 	documentUploadDtlVOs= new DocumentUploadDtlVO[vo.length];
	 	 	for(int i=0;i<vo.length;i++)
	 	 	{
	 	 		documentUploadDtlVOs[i]= (DocumentUploadDtlVO) vo[i];
	 	 	}
	 	 	   
	 	 	  			
	 	 	  }
	 		catch(Exception e){
	 			if(e.getClass()==HisRecordNotFoundException.class){
	 				throw new HisRecordNotFoundException(e.getMessage());	
	 			}
	 			else			 		
	 			 throw new HisDataAccessException("Application Execution Exception"+e);			 
	 		 }			 
	    
			return lstuploadeddoc;
		}

}
