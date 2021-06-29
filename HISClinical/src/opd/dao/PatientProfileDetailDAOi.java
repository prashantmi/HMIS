package opd.dao;

import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ProfileOTDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface PatientProfileDetailDAOi
{
	/** 
	 * Saving Patient Profile
	 * @param _patProfileDtlVO Patient Detail VO
	 * @param _userVO User VO
	 * @return Generated Profile Id 
	 */
	public String create(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);

	/**
	 * Getting Patient Episode Profiles List
	 * @param _patProfileVO Patient Profile Detail VO
	 * @param _userVO User VO
	 * @return List of Patient Profile VO of Previous Profiles
	 */
	public List<PatientProfileDetailVO> getEpisodePatientProfiles(PatientProfileDetailVO _patProfileVO, UserVO _userVO);

	/**
	 * Modifying Patient Profile 
	 * @param _patProfileDtlVO Patient Profile Detail
	 * @param _userVO
	 */
	public void updateOld(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);

	/**
	 * Removing Patient Profile
	 * @param _patProfileDtlVO Patient Profile Detail 
	 * @param _userVO User Detail
	 */
	public void delete(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);

	/**
	 * Getting Patient Episode Profiles List For Inbox
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<PatientProfileDetailVO> getPatientProfilesForInbox(String _patCrNo, String _unitCode, UserVO _userVO);
	
	public void updateProfileHeaderDetail(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);
	
	public void updateProfileStatus(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);
	
	public ProfileInvestigationVO[] retrieveEpisodeInvestigationForProfile(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);
	
	public String fetchProfileRestrictedCapCount(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);

	public ProfileGroupDtlVO[] fetchGroupIdProfileGroupDtl(ProfileAccessPolicyVO profileAccessPolicy, UserVO _userVO);
	
	public ProfileAccessPolicyVO fetchAccessTypeProfileAccessPolicy(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateAccessType(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);
	
	public void updateIsValid(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);
	
	public ProfileInvestigationVO[] retrieveAdmissionInvestigationForProfile(String _crNo, PatientDetailVO voDP, UserVO _userVO);
	
	public List<PatientProfileDetailVO> getPatientProfilesForAll(String _patCrNo, String _unitCode, UserVO _userVO);
	
	public List<PatientProfileDetailVO> getPatientProfilesForEMR(String _patCrNo, String[] departmentUnitArray, String accessType, UserVO _userVO);
	
	public PatientProfileDetailVO[] getPreviousProfileDetails(String _crNo,UserVO _userVO);
	
	public ProfileOTDetailVO[] getPatientOperationDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);
	
	
	// added by VASU on 06-Nov-2017
	public ProfileInvestigationVO[] retrieveTestIPDInvestigationForProfile(String _crNo, String _admissionNo, UserVO _userVO,String age,String _reqDno);
	public ProfileInvestigationVO[] retrieveTestOPDInvestigationForProfile(String _crNo, String _episodeCode, UserVO _userVO,String age,String _reqDno);

	//added by Dheeraj on 05-Dec-2018
	public void savePatientProfile(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);
	public byte[] fetchPatientProfile(PatientProfileDetailVO _patProfileDtlVO);
	public String fetchHtmlPatientProfile(PatientProfileDetailVO _patProfileDtlVO);
	
	//Added By Shweta on 9-MAY-2019
	public List<DocumentUploadDtlVO> getDocumentUploadEssentials(String patCrNo,String episodeCode, UserVO _userVO);
}

