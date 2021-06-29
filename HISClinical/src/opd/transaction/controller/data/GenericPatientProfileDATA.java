/**
## 		Creation Log		: PatIntakeOutDtlVO
##		Created Date			: 16-01-2015
##		Reason	(CR/PRS)		: CR
##		created By				: Akash Singh
*/
package opd.transaction.controller.data;

import java.util.List;
import java.util.Map;

import opd.bo.OpdPatientBO;
import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.utility.dynamicdesk.delegate.DynamicDeskDelegate;
import hisglobal.vo.DeskMenuMasterVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.DisclaimerMstVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.ProfileOTDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileAccessDetailVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public class GenericPatientProfileDATA extends ControllerDATA
{
	// Getting Patient Episode Profiles List
	public static Map<String,Object> getPatientProfilesEssentials(PatientProfileDetailVO _patProfileVO, String _deskType,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientProfilesEssentials(_patProfileVO, _deskType, _userVO);
	}

	// Getting Patient Episode Essentials
	public static Map<String, Object> getPatientProfilesAccessEssentials(PatientProfileDetailVO _patProfileVO, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getPatientProfilesEssentials(_patProfileVO, _userVO);
	}

	// Getting Patient Episode Profiles List
	public static List<DeskMenuMasterVO> getProfileBasedDeskMenus(String profileType,String _deskId,UserVO _userVO)
	{
		DynamicDeskDelegate delegate = new DynamicDeskDelegate();
		return delegate.getDeskProfileBasedMenus(profileType,_deskId, _userVO);
	}
	
	// Getting Patient Episode Diagnosis Detail
	public static EpisodeDiagnosisVO[] getEpisodeDiagnosis(String _crNo, PatientDetailVO voDp, String _deskType, UserVO _userVO, String profileGenerationMode)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.getPatientProfileDiagnosisDetail(_crNo, voDp,_deskType, _userVO,  profileGenerationMode);
	}
	
	// Getting Patient Episode Allergies Detail
	public static PatAllergyDtlVO[] getPatientAllergiesDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		return opdPatDelegate.getPatientAllergiesDetail(_crNo,_userVO, voDP, profileGenerationMode);
	}
	
	// Getting Patient Episode Image Examination Detail
	public static OpdPatientImageDtlVO[] getEpisodeExamImages(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.getPatientProfileEpisodeExamImages(_crNo, _userVO, voDP, profileGenerationMode);
	}

	// Getting Patient Next Visit Detail
	public static EpisodeVO getPatientNextVisitDetail(String _crNo, String _episodeCode, String _visitNo, UserVO _userVO)
	{
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		EpisodeVO vo = new EpisodeVO();
		vo.setPatCrNo(_crNo);
		vo.setEpisodeCode(_episodeCode);
		vo.setEpisodeVisitNo(_visitNo);
		return opdPatDelegate.retrieveEpisodeDetail(vo, _userVO);
	}

	// Getting Patient Episode External Investigation Detail
	public static EpisodeExtInvDtlVO[] getEpisodeExtInvestigation(String _crNo, PatientDetailVO voDP, String profileGenerationMode, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.getPatientProfileEpisodeExtInvestigation(_crNo, voDP, profileGenerationMode, _userVO);
	}

	// Saving Patient Profile
	public static String savePatientProfile(PatientProfileDetailVO _patProfileDtlVO,Map inAllMap,Map inAllPreviousMap,UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.savePatientProfile(_patProfileDtlVO,inAllMap,inAllPreviousMap,userDeskMenuTemplateMasterVO, _userVO);
	}

	// Modifying Patient Profile
	public static void modifyPatientProfile(List<PatientProfileDetailVO> _lstPatProfileDtlVO, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.modifyPatientProfile(_lstPatProfileDtlVO, _userVO);
	}
	
	// Removing Patient Profile
	public static void removePatientProfile(List<PatientProfileDetailVO> _lstProfileDtlVO, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.removePatientProfile(_lstProfileDtlVO, _userVO);
	}

	// Saving Patient Profile Access Priviledges
	public static void saveProfileAccessPriviledges(List<ProfileAccessDetailVO> _lstProfileAccessDtlVO,PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.saveProfileAccessPriviledges(_lstProfileAccessDtlVO,_patientProfileDtlVO, _userVO);
	}

	// Getting Serach Result of Users for the Profile Access Priviledges
	public static List<UserVO> getSearchUsersForProfileAccessPrivil(String _mode, String _str, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.getSearchUsersForProfileAccessPrivil(_mode, _str, _userVO);
	}
	
	public static Map fetchProfileDetails(PatientProfileDetailVO _patientProfileDtlVO,String genderCode,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.fetchProfileDetails(_patientProfileDtlVO,genderCode, _userVO);
	}
	
	// Saving Profile header detail
	public static void updateProfileHeaderDetail(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.updateProfileHeaderDetail(_patProfileDtlVO, _userVO);
	}
	
	public static void profileGeneration(PatientProfileDetailVO _patProfileDtlVO,ProfileGroupDtlVO[] profileGroupDtlVO,ProfileAccessPolicyVO profileAccessPolicy, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.profileGeneration(_patProfileDtlVO,profileGroupDtlVO,profileAccessPolicy, _userVO);
	}
	
	// Getting Patient Episode Investigation Detail
	public static ProfileInvestigationVO[] getEpisodeInvestigation(String _crNo, String _deskType, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.getPatientProfileInvestigationDetail(_crNo, _deskType, _userVO, voDP, profileGenerationMode);
	}
	
	public static Map fetchDetailsForGenerate(PatientProfileDetailVO _patientProfileDtlVO,String _deskType,UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.fetchDetailsForGenerate(_patientProfileDtlVO,_deskType, _userVO);
	}
	
	public static void removeUserPriv(ProfileAccessDetailVO _profileAccessDetailVO, UserVO _userVO)
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		opdPatientDelegate.removeUserPriv(_profileAccessDetailVO, _userVO);
	}
	
	public static PatDrugTreatmentDetailVO[] getPatientTreatmentDetail(String _crNo, PatientDetailVO voDP, String profileGenerationMode,String _deskType,UserVO _userVO)
	{
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		return opdPatDelegate.getPatientTreatmentDetail(_crNo, voDP, profileGenerationMode,_deskType,_userVO);
	}
	
	public static PatientAlertsDetailVO[] getPatientAlertsDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		return opdPatDelegate.getPatientAlertsDetail(_crNo,_userVO, voDP, profileGenerationMode);
	}
	
	public static DisclaimerMstVO fetchDisclaimerDetails(String _deptUnitCode,String profileType, UserVO _userVO)
	{
		OpdEssentialDelegate delegate = new OpdEssentialDelegate();
		return delegate.fetchDisclaimerDetails(_deptUnitCode, profileType,_userVO);
	}
	
	// Getting Profile Detail
	public static Map getDischargeProfileEssentials(String _deptUnitCode, String patCrNo, UserVO _userVO) 
	{
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		return opdPatDelegate.getDischargeProfileEssentials(_deptUnitCode, patCrNo, _userVO);
	}

	// Getting Patient Progress Notes
	public static DoctorRoundDtlVO[] getPatientProgressNotes(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		return opdPatDelegate.getPatientProgressNotes(_crNo,_userVO, voDP, profileGenerationMode);
	}
	
	public static ProfileOTDetailVO[] getPatientOperationDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		return opdPatDelegate.getPatientOperationDetail(_crNo,_userVO, voDP, profileGenerationMode);
	}
	
	public static Map getPatientProfileOperationTemplateDetails(List profileOTList,String patCrNo, UserVO _userVO) 
	{
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		return opdPatDelegate.getPatientProfileOperationTemplateDetails(profileOTList,patCrNo, _userVO);
	}

	public static EpisodeSummaryDetailVO[] getPatientOPDProgressNotes(
			EpisodeVO episodeVO, UserVO userVO) {
		OpdPatientDelegate opdPatDelegate = new OpdPatientDelegate();
		return opdPatDelegate.getPatientOPDProgressNotes(episodeVO,userVO);
	}

	public static PatIntakeOutDtlVO[] getPatientOutTakeDetail(String patCrNo,PatientDetailVO voDp, String profileGenerationMode,UserVO userVO) 
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.getPatientOutTakeDetail(patCrNo, voDp, profileGenerationMode, userVO);
	}
	
	//Added by VASU on 06.11.17 for Getting Patient Episode Investigation Test Detail
	public static ProfileInvestigationVO[] getEpisodeTestInvestigation(String _crNo, String _episodeCode,String _admissionNo,String _deskType, UserVO _userVO,String age,String _reqDno )
	{
		OpdPatientDelegate opdPatientDelegate = new OpdPatientDelegate();
		return opdPatientDelegate.getPatientProfileTestInvestigationDetail(_crNo, _episodeCode,_admissionNo,_deskType, _userVO,age,_reqDno);
	}
	
	// Getting Profile Footer Details:Added by Vasu on 16.April.2019
		public static Map getPatientProfileFooterEssentials(PatientDetailVO voDP, UserVO _userVO) 
		{
			OpdPatientBO opdPatDelegate = new OpdPatientBO();
			return opdPatDelegate.getPatientProfileFooterEssentials(voDP, _userVO);
		}
}
