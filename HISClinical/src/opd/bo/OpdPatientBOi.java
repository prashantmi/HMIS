package opd.bo;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import hisglobal.utility.Entry;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
//import hisglobal.vo.Apt_appointmentDtlVO;
//import hisglobal.vo.Apt_slotDtlVO;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeActionDtlVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.EpisodeAttendantDetailVO;
import hisglobal.vo.EpisodeCloseVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeSummaryDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientFamilyDtlVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.ProfileOTDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ProfileAccessDetailVO;
import hisglobal.vo.ProfileReviewDtlVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;


public interface OpdPatientBOi {
	
	 public  void saveOpdDiagnosisDetails(EpisodeDiagnosisVO[] episodeDiagnosisVO,UserVO userVO);
	 //*
	 public  void updateOpdPatientEpisode(String patCrNo,String SerialNo,String visitNo,String episodeCode,String isConfirmed,String episodeIsOpen,String nextVisitDate,UserVO _userVO);
	 
	public EpisodeCloseVO[] getPatientEpisodeDtl(String crNo,UserVO _userVO,PatientVO _patientVO);
	public void updatePatientEpisode(String crNo,UserVO _userVO,EpisodeCloseVO[] selectedEpisodeCloseVO);
	public  void saveOpdPatientReferDetail(EpisodeRefDtlVO[] episodeRefDtlVO,UserVO userVO,String _deskType);

	public  void sendOpdConsultantData(ConsultationDtlVO consultationDtlVO,List<ConsultationProfileDtlVO> consultationProfileDtlVOList, UserVO userVO);
//	public EpisodeAllergiesVO[] getEpisodeAllergiesByPatient(DailyPatientVO _dailyPatientVO,UserVO _userVO);
	
	public PatAllergyDtlVO[] getEpisodeAllergiesByPatient(DailyPatientVO _dailyPatientVO,UserVO _userVO);
	public PatAllergyDtlVO[] getEpisodeAllergiesByPatientNew(DailyPatientVO _dailyPatientVO,UserVO _userVO);

	//* Saving Template Parameter Values in Clinical Table
	public void saveOrReplaceOpdTempParaValues(OpdClinicalDetailVO  clinicalDtlVO, UserVO userVO);

	// * Removing Template Parameter Values in Clinical Table
	public void removeOpdTempParaValues(OpdClinicalDetailVO clinicalDtlVO, List tempIds, UserVO userVO);

	// * Saving Template Parameter Values in Clinical Table from List
	public void saveOpdTempParaValues(List<OpdClinicalDetailVO> lst, UserVO userVO);

	public void saveAllergyDetails(EpisodeAllergiesVO[] _episodeAllergiesVO,UserVO _userVO);
	public void saveAllergyDetailsNew(List<EpisodeAllergiesVO> _episodeAllergiesVO,EpisodeAllergiesVO vo, UserVO _userVO); 

	public  void updateMailAckStatus(ConsultationDtlVO consultationDtlVO,String mailId,String ackStatus,UserVO userVO);
	public  String getNoOfNewMails(String seatId,String ackStatus);
	public  void deleteMails(String[] mailIdArray,UserVO userVO);
	
	
	///////////save opd next visit appointment details/////////////////////
	/*public Apt_appointmentDtlVO SaveNextVisitAppiontemntData(Apt_appointmentDtlVO _appointmentDtlVO,Apt_slotDtlVO _slotDtlVO,PatientVO _patientVO,UserVO userVO);*/	


	public  void saveDocumentDetail(DocumentUploadDtlVO[] docUploadVos,UserVO userVO);
	
	public EpisodeVO[] getOpdProfilePatientEpisodes(String _crNo,String _departmentUnitCode,UserVO _userVO);
	
	/**
	 * Getting Episode Diagnosis Detail for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO
	 * @param profileGenerationMode 
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public EpisodeDiagnosisVO[] getOpdDiagnosisDetailProfilePatientEpisodes(String _crNo, PatientDetailVO voDp,String _deskType, UserVO _userVO, String profileGenerationMode);
	
	/**
	 * Saving Patient Profile
	 * @param _patProfileDtlVO Patient Profile Detail
	 * @param _userVO User VO
	 */
	public String savePatientProfile(PatientProfileDetailVO _patProfileDtlVO,Map inAllMap,Map inAllPreviousMap,UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO, UserVO _userVO);
	
	/*Not in Use*/
	//public PatProfileDtlVO[] getPreviousProfileDtlByCrNo(String _crNo,String _departmentUnitCode,UserVO _userVO);
	
	/**
	 * Modifying Patient Profile
	 * @param _lstPatProfileDtlVO List of Patient Profile Detail 
	 * @param _userVO User Detail
	 */
	public void modifyPatientProfile(List<PatientProfileDetailVO> _lstPatProfileDtlVO, UserVO _userVO);
	
	/**
	 * Removing Patient Profile
	 * @param _lstProfileDtlVO List of Patient Profile Detail 
	 * @param _userVO User Detail
	 */
	public void removePatientProfile(List<PatientProfileDetailVO> _lstProfileDtlVO, UserVO _userVO);

	//////////////opd service requisition details ///////////
	
	//public Service_Req_dtlVO SaveServiceRequisition(Service_Req_dtlVO _Service_Req_dtlVO,UserVO _userVO);
	
	
	 public void saveBelongingDetails(PatientBelongingVO[] _patBelongingVO,UserVO _userVO);

	 public void saveBelongingHandoverDetails(PatientBelongingVO[] _patBelongingVO,UserVO _userVO);
	 
	 public  PatientBelongingVO[] modifyBelongingDetails(PatientBelongingVO _patBelongingVO,String _oldItemCode,String _collectionDate,UserVO _userVO);

	/**
	 * Getting Episode Allergies Detail for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @param episodeCode 
	 * @param episodeVisitNo 
	 * @return Array of Episode Allery Detail VOs
	 */
	public PatAllergyDtlVO[] getPatientAllergiesDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);

	/**
	 * Getting Episode Exam Images for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @return Array of Patient Image Detail VOs
	 */
	public OpdPatientImageDtlVO[] getPatientProfileEpisodeExamImages(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);

	/**
	 * Getting Episode External Investigation for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @return Array of Patient External Investigation Detail VOs
	 */
	public EpisodeExtInvDtlVO[] getPatientProfileEpisodeExtInvestigation(String _crNo, PatientDetailVO voDP, String profileGenerationMode, UserVO _userVO);

	 public EpisodeAllergiesVO[] getAllergyDtlEpisodeWise(String selAllergyID,String selAllergyCode,DailyPatientVO _dailyPatientVO,UserVO _userVO);
	 public EpisodeAllergiesVO[] getAllergyDtlEpisodeWiseNew(String selAllergyID,DailyPatientVO _dailyPatientVO,UserVO _userVO);

	 /** Saving Next Visit Detail
	 * @param _episodeVO Episode VO
	 * @param _userVO UserVO
	 */
	public void saveNextVisitDetail(EpisodeVO _episodeVO, UserVO _userVO,EpisodeActionDtlVO _episodeActDtlVO,String _deskType);

	/** 
	 * Retrieving Episode Detail 
	 * @param _episodeVO 
	 * @param _userVO
	 * @return Episode VO 
	 */
	public EpisodeVO retrieveEpisodeDetail(EpisodeVO _episodeVO, UserVO _userVO);
	
	//update consent status
	public void updateStatus(List consentRequestVO,UserVO _userVO);
	
	//get no of new request
	public String getNoOfNewConsentRequest(ConsentRequestVO _consentVO,String hospitalCode);

	/**
	 * Getting Patient-Centric & Episode-Centric Clinical Template Data
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by List of Entry Object with paraId/Value at 0 Patient-Centric Data
	 */
	public Map<String, List<Entry>> getPatientClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, UserVO _userVO);

	/**
	 * Getting Patien Clinical Template Data
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by Map of  paraId/Value 
	 */
	public Map<String, Map<String, String>> getPatientFinalClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, UserVO _userVO);

	/**
	 * Saving Patient Clinical Data
	 * @param _deskType Desk Type
	 * @param _lstPatCliDtl List of PatientClinicalDetailVO to save
	 * @param _userVO User VO
	 */
	public void savePatientClinicalDetail(String _deskType, List<PatientClinicalDetailVO> _lstPatCliDtl, UserVO _userVO);

	/**
	 * Get All Template Details
	 * @param _lstTemps list of Entry object in tempId/tempName
	 * @param _userVO User VO
	 * @return Map of tempId by tempMst VO
	 */
	public Map<String, TemplateMasterVO> getAllTemplateDetails(List<Entry> _lstTemps, UserVO _userVO);

	/**
	 * Get All Template Parameters Detail
	 * @param _lstTemps list of Entry object in tempId/tempName
	 * @param _userVO User VO
	 * @return List of all Template Parameters Detail
	 */
	public List<TemplateParameterMasterVO> getAllTemplateParametersDetail(List<Entry> _lstTemps, UserVO _userVO);

	/**
	 * Getting Report Date List
	 * @param _deskType Desk Type
	 * @param _patCliDtlVO Patient Detail Data
	 * @param _userVO User VO
	 * @return list of Entry object as Visit Date by Detail to Display
	 */
	public List<Entry> getReportDateList(String _deskType, PatientClinicalDetailVO _patCliDtlVO, UserVO _userVO);

	/**
	 * Getting Patient Chart Clinical Template Data
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstReportDates
	 * @param _userVO User VO
	 * @return Map of VisitDate/Map of temp Id/Map of  paraId/Value 
	 */
	public Map<String, Map<String, Map<String, String>>> getPatientChartClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, UserVO _userVO);

	/**
	 * Getting Patient Chart Clinical Template Data
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstReportDates
	 * @param _lstTemps 
	 * @param _userVO User VO
	 * @return Map of VisitDate/Map of temp Id/Map of  paraId/GenericTemplateUtility.TempParameter Object 
	 */
	public Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> getPatientChartClinicalDataTempWiseWithParaDtl(String _deskType, 
			PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO);

	/**
	 * Getting Patient Chart Clinical Template Data
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstReportDates
	 * @param _lstTemps 
	 * @param _userVO User VO
	 * @return Map of VisitDate/Map of temp Id/Map of  paraId/Value 
	 */
	public Map<String, Map<String, Map<String, String>>> getPatientChartClinicalDataTempWise(String _deskType, 
			PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO);

	/**
	 * Getting Patient Episode Profiles List
	 * @param _patProfileVO Patient Profile Detail VO
	 * @param _userVO User VO
	 * @return List of Patient Profile VO of Previous Profiles
	 */
	public Map<String,Object> getPatientProfileEssentails(PatientProfileDetailVO _patProfileVO, String _deskType,UserVO _userVO);
	
	
	/**
	 * Saving Patient Treatment Detail
	 * @param _lstDrugDtl List of Patient Drug Treatment Detail 
	 * @param _userVO User VO
	 */

	public void savePatTreatmentDetail(List<PatDrugTreatmentDetailVO> _lstDrugDtl,List<PatExtTreatmentDetailVO> _lstExtDtl,PatDietAdviceDetailVO _patDietDetailVO,Map drugScheduleMap,RestAdviceDtlVO restAdviceDtlVO,List drugAdminList,List extAdminVoList ,UserVO _userVO);
	
	public String getDrugAllergyAllerts(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	
	public String getPrevDrugStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	
	public String getdrugReactionStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	
	public String getdrugAdviceAlerts(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	

	public void revokeDiagnosis(String revokeCode,EpisodeDiagnosisVO episodeDiaVO,UserVO userVO);
	
	/**  Getting All The Assigned Alerts of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public List<PatientAlertsDetailVO> getPatientAssignedAlert(String crNo,UserVO userVO);
	
	/**  Saving the Patient Alerts & Revoking
	 * @param patAlertDetailVO
	 * @param userVO
	 */
	public void savePatientAlerts(List<PatientAlertsDetailVO> lstAddedPatAlerts,List<PatientAlertsDetailVO> lstRevokedPatAlerts,UserVO userVO);
	
	/** Revoking the Alert of the Patient
	 * @param patAlertDetailVO
	 * @param userVO
	 */
	public void revokeAlerts(PatientAlertsDetailVO patAlertDetailVO,UserVO userVO);
	
	/** Getting All The Alert of The patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatientAlertsDetailVO[] getAllPatientAlert(String crNo,UserVO userVO);
	
	/**
	 * Saving Patient Profile Access Priviledges
	 * @param _lstProfileAccessDtlVO List of Patient Profile Access Detail
	 * @param _userVO User VO
	 */
	public void saveProfileAccessPriviledges(List<ProfileAccessDetailVO> _lstProfileAccessDtlVO,PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);

	/**
	 * Getting Patient Episode Profiles List For Inbox
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<ConsultationProfileDtlVO> getPatientProfilesForInbox(String _mailRequestId, UserVO _userVO);
	
	public void saveExtInvestigationDtl(List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO,UserVO userVO);
	
	public EpisodeExtInvDtlVO[] getAddedExternalInvDetail(String patCrNo,UserVO userVO);

	public ConsultationDtlVO getMailDetailByMailId(UserVO _userVO,String _mailRequestId);

	
	public Map fetchProfileDetails(PatientProfileDetailVO _patientProfileDtlVO,String genderCode, UserVO _userVO);
	
	public void updateProfileHeaderDetail(PatientProfileDetailVO _patProfileDtlVO, UserVO _userVO);
	
	/**
	 * generate the profile,
	 * the profile is created on the disk,
	 * the profile dtl table is updated with the profile status flag as generated,
	 * The default access policy is saved if found in the previous method
	 */
	public void profileGeneration(PatientProfileDetailVO _patProfileDtlVO,ProfileGroupDtlVO[] profileGroupDtlVO,ProfileAccessPolicyVO profileAccessPolicy, UserVO _userVO);
	
	/**
	 * Getting Episode Investigation Detail for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public ProfileInvestigationVO[] getOpdInvestigationDetailProfilePatientEpisodes(String _crNo,String _deskType, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);
	
	public Map fetchDetailsForGenerate(PatientProfileDetailVO _patientProfileDtlVO,String _deskType, UserVO _userVO);
	
	public void removeUserPriv(ProfileAccessDetailVO _profileAccessDetailVO, UserVO _userVO);
	
	public PatDrugTreatmentDetailVO[] getPatientTreatmentDetail(String _crNo, PatientDetailVO voDP, String profileGenerationMode,String _deskType,UserVO _userVO);
	
	public void revokeAllergy(EpisodeAllergiesVO epiAllergyVO, UserVO userVO );
	public void revokeAllergyNew(EpisodeAllergiesVO epiAllergyVO, UserVO userVO );
	

	public PatientAlertsDetailVO[] getPatientAlertsDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);
	

	public  Map getDrugProperties(String itemId,UserVO _userVO);
	
	/**
	 * Getting Patient All Profiles List For Inbox
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<PatientProfileDetailVO> getPatientProfilesForAll(String _patCrNo, String _unitCode, UserVO _userVO);


	public DoctorRoundDtlVO[] getPatientProgressNotes(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);

	// Saving Image to Patient in Image Examination
	//public void savePatientExaminationImage(OpdPatientImageDtlVO _vo, BufferedImage _image, UserVO _userVO);
	
	public void savePatientExaminationImage(OpdPatientImageDtlVO patimageVO, UserVO userVO); // Modified by VASU on 21-AUG-2017

	// Modifying Image to Patient in Image Examination
	public void modifyPatientExaminationImage(OpdPatientImageDtlVO _vo, BufferedImage _image, UserVO _userVO);

	public PatientProfileDetailVO[] getPreviousProfileDetails(String _crNo,UserVO _userVO);
	
	public  void savePatientProfileReviewDetail(ProfileReviewDtlVO profileReviewDtlVO,UserVO userVO);
	
	public ProfileReviewDtlVO[] fetchProfileReviewDetails(String _crNo,String profileID,UserVO _userVO);
	
	public ProfileOTDetailVO[] getPatientOperationDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);
	
	public  Map getPatientProfileOperationTemplateDetails(List profileOTList,String patCrNo,UserVO _userVO);

	/**
	 * Saving Visit Summary Detail
	 * @param _episodeSummaryVO Episode Summary Detail
	 * @param _userVO User DEtail
	 * @param _episodeActDtlVO Episode Action Detail for CMO Desk
	 * @param _deskType Desk Type
	 */
	public void saveVisitSummaryDetail(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO, String _deskType, EpisodeCloseVO _episodeCloseVO);

	/**
	 * Modifying Visit Summary Detail
	 * @param _episodeSummaryVO Episode Summary Detail
	 * @param _userVO User DEtail
	 * @param _episodeActDtlVO Episode Action Detail for CMO Desk
	 * @param _deskType Desk Type
	 */
	public void modifyVisitSummaryDetail(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO, String _deskType, EpisodeCloseVO _episodeCloseVO);
	
	public void savePatientAttendantDtl(String isExisting,PatientFamilyDtlVO patFamilyVO,EpisodeAttendantDetailVO epiAttendantVO,UserVO userVO);

	/**
	 * Getting Generic Chart Detail
	 * 
	 * @param _deskType Desk Type
	 * @param _patDtlVO Patient Detail 
	 * @param _chartVO Chart Detail
	 * @param _lstChartParas List Chart Parameters
	 * @param _criteria Criteria
	 * @param _fromDate From Date
	 * @param _toDate To Date
	 * @param _userVO User Detail
	 * @return Map of Data
	 */
	public Map<String, Object> getChartReportingData(String _deskType, PatientDetailVO _patDtlVO, ChartMasterVO _chartVO, List<ChartParameterMappingVO> _lstChartParas, String _criteria, String _fromDate, String _toDate, UserVO _userVO);

	/**
	 * Getting Column Chart Paramter List
	 * 
	 * @param _chartVO Chart Detail
	 * @param _userVO User Detail
	 * @return List Chart Paras
	 */
	public List<ChartParameterMappingVO> getChartParamtersList(ChartMasterVO _chartVO, UserVO _userVO);
	
	public EpisodeSummaryDetailVO[] getPatientOPDProgressNotes(EpisodeVO episodeVO, UserVO userVO);
	
	public Map getDischargeProfileEssentials(String _deptUnitCode, String patCrNo, UserVO _userVO);
	
	/*Functions Added By Pawan Kumar B N*/
	public void savePatientComplaintsDtl(List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO, UserVO userVO);
	
	public EpisodeExtInvDtlVO[] getAddedPatientComplaintDetail(String patCrNo, UserVO userVO);
	 public   List getEssentialDiagnosisDetail(EpisodeDiagnosisVO previousDiagVO,UserVO userVO);
/**
## 		Modification Log		: added getPatientOutTakeDetail				
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/
	public PatIntakeOutDtlVO[] getPatientOutTakeDetail(String patCrNo,PatientDetailVO voDp, String profileGenerationMode, UserVO userVO);
	
//	AddedBy: NehaRajgariya Date:28July2016
	public String getIcdHospCode(String deptUnitCode,UserVO userVO);
	
	// added by VASU on 06-Nov-2017
		public ProfileInvestigationVO[] getTestInvestigationDetailProfilePatientEpisodes(String _crNo, String _episodeCode,String _admissionNo,String _deskType, UserVO _userVO,String age,String _reqDno);
		public byte[] fetchImageFromPostgres(String imgCode);
		public byte[] viewImageFromPostgres(OpdPatientImageDtlVO vo);
}
