package opd.bo.delegate;

import hisglobal.business.Delegate;
import hisglobal.utility.Entry;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.ChartMasterVO;
import hisglobal.vo.ChartParameterMappingVO;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DoctorRoundDtlVO;
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
import hisglobal.vo.OpdClinicalDetailVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDietAdviceDetailVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatExtTreatmentDetailVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientBelongingVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientFamilyDtlVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ProfileAccessDetailVO;
import hisglobal.vo.ProfileAccessPolicyVO;
import hisglobal.vo.ProfileGroupDtlVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.ProfileOTDetailVO;
import hisglobal.vo.ProfileReviewDtlVO;
import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import opd.bo.OpdPatientBO;
import opd.bo.OpdPatientBOi;

public class OpdPatientDelegate extends Delegate {

	/**
	 * Constructor for Setting Service Provider
	 */
	public OpdPatientDelegate() {
		super(new OpdPatientBO()); // /<<Setting the service provider
	}

	public void saveOpdDiagnosisDetails(
			EpisodeDiagnosisVO[] episodeDiagnosisVO, UserVO userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveOpdDiagnosisDetails(episodeDiagnosisVO, userVO);

	}
	//*
	public void saveOpdPatientEpisode(String patCrNo, String SerialNo, String visitNo, String episodeCode, String isConfirmed, String episodeIsOpen, String nextVisitDate, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.updateOpdPatientEpisode(patCrNo, SerialNo, visitNo, episodeCode, isConfirmed, episodeIsOpen, nextVisitDate, _userVO);
	}

	public EpisodeCloseVO[] getPatientEpisodeDtl(String crNo, UserVO _userVO,
			PatientVO _patientVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientEpisodeDtl(crNo, _userVO, _patientVO);
	}

	public void updatePatientEpisode(String crNo, UserVO _userVO,
			EpisodeCloseVO[] selectedEpisodeCloseVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.updatePatientEpisode(crNo, _userVO, selectedEpisodeCloseVO);
	}

	public void saveOpdPatientReferDetail(EpisodeRefDtlVO[] episodeRefDtlVO,
			UserVO userVO,String _deskType) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveOpdPatientReferDetail(episodeRefDtlVO, userVO,_deskType);
	}

	/*public EpisodeAllergiesVO[] getEpisodeAllergiesByPatient(
			DailyPatientVO _dailyPatientVO, UserVO _userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO
				.getEpisodeAllergiesByPatient(_dailyPatientVO, _userVO));
	}*/
	
	public PatAllergyDtlVO[] getEpisodeAllergiesByPatient(DailyPatientVO _dailyPatientVO, UserVO _userVO) 
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getEpisodeAllergiesByPatient(_dailyPatientVO, _userVO));
	}

	public void sendOpdConsultantData(ConsultationDtlVO consultationDtlVO,
			List<ConsultationProfileDtlVO> consultationProfileDtlVOList, UserVO userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.sendOpdConsultantData(consultationDtlVO,consultationProfileDtlVOList, userVO);
	}

	//* Saving Template Parameter Values in Clinical Table
	public void saveOrReplaceOpdTempParaValues(OpdClinicalDetailVO clinicalDtlVO, UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveOrReplaceOpdTempParaValues(clinicalDtlVO, userVO);
	}

	// * Removing Template Parameter Values in Clinical Table
	public void removeOpdTempParaValues(OpdClinicalDetailVO clinicalDtlVO, List tempIds, UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.removeOpdTempParaValues(clinicalDtlVO, tempIds, userVO);
	}

	// * Saving Template Parameter Values in Clinical Table from List
	public void saveOpdTempParaValues(List<OpdClinicalDetailVO> lst, UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveOpdTempParaValues(lst, userVO);
	}

	public void saveAllergyDetails(EpisodeAllergiesVO[] _episodeAllergiesVO,
			UserVO _userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveAllergyDetails(_episodeAllergiesVO, _userVO);
	}

	public void updateMailAckStatus(ConsultationDtlVO consultationDtlVO,
			String mailId, String ackStatus, UserVO userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.updateMailAckStatus(consultationDtlVO, mailId, ackStatus,
				userVO);
	}

	public String getNoOfNewMails(String seatId, String ackStatus) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getNoOfNewMails(seatId, ackStatus);
	}

	public void deleteMails(String[] mailIdArray, UserVO userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.deleteMails(mailIdArray, userVO);
	}

	public void saveDocument(DocumentUploadDtlVO[] docUploadVos, UserVO userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveDocumentDetail(docUploadVos, userVO);
	}

	/**
	 * Not In Use
	 * @deprecated
	 */
	public EpisodeVO[] getPatientProfileEpisodes(String _crNo,
			String _departmentUnitCode, UserVO _userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getOpdProfilePatientEpisodes(_crNo,
				_departmentUnitCode, _userVO);
	}

	/**
	 * Getting Episode Diagnosis Detail for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO User VO
	 * @param episodeVisitNo 
	 * @param profileGenerationMode 
	 * @return Array of Episode Diagnosis Detail VOs
	 */
	public EpisodeDiagnosisVO[] getPatientProfileDiagnosisDetail(String _crNo, PatientDetailVO voDp, String _deskType, UserVO _userVO, String profileGenerationMode)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getOpdDiagnosisDetailProfilePatientEpisodes(_crNo, voDp,_deskType, _userVO, profileGenerationMode);
	}

	/**
	 * Saving Patient Profile
	 * @param _patProfileDtlVO Patient Profile Detail
	 * @param _userVO User VO
	 */
	public String savePatientProfile(PatientProfileDetailVO _patProfileDtlVO,Map inAllMap,Map inAllPreviousMap,UserDeskMenuTemplateMasterVO userDeskMenuTemplateMasterVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.savePatientProfile(_patProfileDtlVO,inAllMap,inAllPreviousMap,userDeskMenuTemplateMasterVO, _userVO);
	}

	/* Not in Use*/
	/*public PatProfileDtlVO[] getPreviousPatientProfile(String _crNo,
			String _departmentUnitCode, UserVO _userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPreviousProfileDtlByCrNo(_crNo,
				_departmentUnitCode, _userVO);
	}*/

	/**
	 * Modifying Patient Profile
	 * @param _lstPatProfileDtlVO List of Patient Profile Detail 
	 * @param _userVO User Detail
	 */
	public void modifyPatientProfile(List<PatientProfileDetailVO> _lstPatProfileDtlVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.modifyPatientProfile(_lstPatProfileDtlVO, _userVO);
	}

	/**
	 * Removing Patient Profile
	 * @param _lstProfileDtlVO List of Patient Profile Detail 
	 * @param _userVO User Detail
	 */
	public void removePatientProfile(List<PatientProfileDetailVO> _lstProfileDtlVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.removePatientProfile(_lstProfileDtlVO, _userVO);
	}

	// ////////////////patient belonging//////////////////////

	public void saveBelongingDetails(PatientBelongingVO[] _patBelongingVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveBelongingDetails(_patBelongingVO, _userVO);
	}

	public void saveBelongingHandoverDetails(PatientBelongingVO[] _patBelongingVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveBelongingHandoverDetails(_patBelongingVO, _userVO);
	}

	public PatientBelongingVO[] modifyBelongingDetails(PatientBelongingVO _patBelongingVO, String _oldItemCode, String _collectionDate, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.modifyBelongingDetails(_patBelongingVO, _oldItemCode, _collectionDate, _userVO);
	}

	/**
	 * Getting Episode Allergies Detail for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @param episodeCode 
	 * @param episodeVisitNo 
	 * @return Array of Episode Allery Detail VOs
	 */
	public PatAllergyDtlVO[] getPatientAllergiesDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientAllergiesDetail(_crNo, _userVO, voDP, profileGenerationMode));
	}
	
	/**
	 * Getting Episode Exam Images for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @return Array of Patient Image Detail VOs
	 */
	public OpdPatientImageDtlVO[] getPatientProfileEpisodeExamImages(String _crNo, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientProfileEpisodeExamImages(_crNo, _userVO, voDP, profileGenerationMode));
	}
	
	/**
	 * Getting Episode External Investigation for Patient Profile
	 * @param _crNo Patient CR No.
	 * @param _episodeCode Episode Code
	 * @param _userVO user VO
	 * @return Array of Patient External Investigation Detail VOs
	 */
	public EpisodeExtInvDtlVO[] getPatientProfileEpisodeExtInvestigation(String _crNo, PatientDetailVO voDP, String profileGenerationMode, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientProfileEpisodeExtInvestigation(_crNo, voDP, profileGenerationMode, _userVO));
	}

	public EpisodeAllergiesVO[] getAllergyDtlEpisodeWise(String selAllergyID,String selAllergyCode,DailyPatientVO _dailyPatientVO,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getAllergyDtlEpisodeWise(selAllergyID, selAllergyCode,_dailyPatientVO,_userVO));
		
	}

	/** Saving Next Visit Detail
	 * @param _episodeVO Episode VO
	 * @param _userVO UserVO
	 */
	public void saveNextVisitDetail(EpisodeVO _episodeVO, UserVO _userVO,EpisodeActionDtlVO _episodeActDtlVO,String _deskType)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveNextVisitDetail(_episodeVO, _userVO,_episodeActDtlVO,_deskType);
	}

	/** 
	 * Retrieving Episode Detail 
	 * @param _episodeVO 
	 * @param _userVO
	 * @return Episode VO 
	 */
	public EpisodeVO retrieveEpisodeDetail(EpisodeVO _episodeVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.retrieveEpisodeDetail(_episodeVO, _userVO);
	}

	/**
	 * Getting Patient-Centric & Episode-Centric Clinical Template Data
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by List of Entry Object with paraId/Value at 0 Patient-Centric Data
	 */
	public Map<String, List<Entry>> getPatientClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientClinicalData(_deskType, _patClinicalVO, _userVO);
	}

	/**
	 * Getting Patien Clinical Template Data
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _userVO User VO
	 * @return Map of temp Id by Map of  paraId/Value 
	 */
	public Map<String, Map<String, String>> getPatientFinalClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientFinalClinicalData(_deskType, _patClinicalVO, _userVO);
	}

	/**
	 * Saving Patient Clinical Data
	 * @param _deskType Desk Type
	 * @param _lstPatCliDtl List of PatientClinicalDetailVO to save
	 * @param _userVO User VO
	 */
	public void savePatientClinicalDetail(String _deskType, List<PatientClinicalDetailVO> _lstPatCliDtl, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.savePatientClinicalDetail(_deskType, _lstPatCliDtl, _userVO);
	}
	
	public PatAllergyDtlVO[] getEpisodeAllergiesByPatientNew(DailyPatientVO _dailyPatientVO, UserVO _userVO) 
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getEpisodeAllergiesByPatientNew(_dailyPatientVO, _userVO));
	}

	public void saveAllergyDetailsNew(List<EpisodeAllergiesVO> _episodeAllergiesVO, EpisodeAllergiesVO vo,
			UserVO _userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveAllergyDetailsNew(_episodeAllergiesVO,vo, _userVO);
	}
	public EpisodeAllergiesVO[] getAllergyDtlEpisodeWiseNew(String selAllergyID,DailyPatientVO _dailyPatientVO,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getAllergyDtlEpisodeWiseNew(selAllergyID, _dailyPatientVO,_userVO));
		
	}
	public void revokeAllergyNew(EpisodeAllergiesVO epiAllergyVO, UserVO userVO )
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.revokeAllergyNew(epiAllergyVO, userVO);
	}
	

	/**
	 * Get All Template Details
	 * @param _lstTemps list of Entry object in tempId/tempName
	 * @param _userVO User VO
	 * @return Map of tempId by tempMst VO
	 */
	public Map<String, TemplateMasterVO> getAllTemplateDetails(List<Entry> _lstTemps, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getAllTemplateDetails(_lstTemps, _userVO);
	}

	/**
	 * Get All Template Parameters Detail
	 * @param _lstTemps list of Entry object in tempId/tempName
	 * @param _userVO User VO
	 * @return List of all Template Parameters Detail
	 */
	public List<TemplateParameterMasterVO> getAllTemplateParametersDetail(List<Entry> _lstTemps, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getAllTemplateParametersDetail(_lstTemps, _userVO);
	}

	/**
	 * Getting Report Date List
	 * @param _deskType Desk Type
	 * @param _patCliDtlVO Patient Detail Data
	 * @param _userVO User VO
	 * @return list of Entry object as Visit Date by Detail to Display
	 */
	public List<Entry> getReportDateList(String _deskType, PatientClinicalDetailVO _patCliDtlVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getReportDateList(_deskType, _patCliDtlVO, _userVO);
	}

	/**
	 * Getting Patient Chart Clinical Template Data
	 * @param _deskType Desk Type
	 * @param _patClinicalVO Patient Clinical Detail VO
	 * @param _lstReportDates
	 * @param _userVO User VO
	 * @return Map of VisitDate/Map of temp Id/Map of  paraId/Value 
	 */
	public Map<String, Map<String, Map<String, String>>> getPatientChartClinicalData(String _deskType, PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientChartClinicalData(_deskType, _patClinicalVO, _lstReportDates, _userVO);
	}

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
			PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientChartClinicalDataTempWise(_deskType, _patClinicalVO, _lstReportDates, _lstTemps, _userVO);
	}
	
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
			PatientClinicalDetailVO _patClinicalVO, List<Entry> _lstReportDates, List<Entry> _lstTemps, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientChartClinicalDataTempWiseWithParaDtl(_deskType, _patClinicalVO, _lstReportDates, _lstTemps, _userVO);
	}

	//update the consent status
	public void updateStatus(List consentRequestVO,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.updateStatus(consentRequestVO,_userVO);
		
	}
	//get no of new consent request
	
	public String getNoOfNewConsentRequest(ConsentRequestVO consentVO, String hospitalCode) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getNoOfNewConsentRequest(consentVO,hospitalCode);
	}
	
	/**
	 * Getting Patient Episode Profiles List
	 * @param _patProfileVO Patient Profile Detail VO
	 * @param _userVO User VO
	 * @return List of Patient Profile VO of Previous Profiles
	 */
	public Map<String,Object> getPatientProfilesEssentials(PatientProfileDetailVO _patProfileVO,String _deskType ,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientProfileEssentails(_patProfileVO, _deskType, _userVO);
	}

	/**
	 * Saving Patient Treatment Detail
	 * @param _lstDrugDtl List of Patient Drug Treatment Detail 
	 * @param _userVO User VO
	 */
	public void savePatTreatmentDetail(List<PatDrugTreatmentDetailVO> _lstDrugDtl,List<PatExtTreatmentDetailVO> _lstExtDtl,PatDietAdviceDetailVO _patDietDetailVO,Map drugScheduleMap,RestAdviceDtlVO restAdviceDtlVO,List drugAdminList,List extAdminVoList ,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.savePatTreatmentDetail(_lstDrugDtl,_lstExtDtl,_patDietDetailVO,drugScheduleMap,restAdviceDtlVO,drugAdminList,extAdminVoList,_userVO);
	}
	
	public String getDrugAllergyAllerts(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getDrugAllergyAllerts(patDrugDtlVO, _userVO);
	}
	
	public String getPrevDrugStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPrevDrugStatus(patDrugDtlVO, _userVO);
	}
	
	public String getdrugReactionStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getdrugReactionStatus(patDrugDtlVO, _userVO);
	}
	
	public String getdrugAdviceAlerts(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getdrugAdviceAlerts(patDrugDtlVO, _userVO);
	}
	
	
	public  Map getDrugProperties(String itemId,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getDrugProperties(itemId, _userVO);
	}
	
	public void revokeDiagnosis(String revokeCode,EpisodeDiagnosisVO episodeDiaVO,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.revokeDiagnosis(revokeCode,episodeDiaVO, userVO);
	}
	
	/** Getting All The Assigned Alerts of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public List<PatientAlertsDetailVO> getPatientAssignedAlert(String crNo,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientAssignedAlert(crNo, userVO);
	}
	
	/** Saving the Patient Alerts & Revoking
	 * @param patAlertDetailVO
	 * @param userVO
	 */
	public void savePatientAlerts(List<PatientAlertsDetailVO> lstAddedPatAlerts,List<PatientAlertsDetailVO> lstRevokedPatAlerts,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.savePatientAlerts(lstAddedPatAlerts,lstRevokedPatAlerts,userVO);
	}
	
	/** Revoking the Alert of the Patient
	 * @param patAlertDetailVO
	 * @param userVO
	 */
	public void revokeAlerts(PatientAlertsDetailVO patAlertDetailVO,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.revokeAlerts(patAlertDetailVO, userVO);
	}
	
	/**  Getting All The Alert of The patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatientAlertsDetailVO[] getAllPatientAlert(String crNo,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getAllPatientAlert(crNo, userVO);
	}

	/**
	 * Saving Patient Profile Access Priviledges
	 * @param _lstProfileAccessDtlVO List of Patient Profile Access Detail
	 * @param _userVO User VO
	 */
	public void saveProfileAccessPriviledges(List<ProfileAccessDetailVO> _lstProfileAccessDtlVO,PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveProfileAccessPriviledges(_lstProfileAccessDtlVO,_patientProfileDtlVO, _userVO);
	}

	/**
	 * Getting Patient Episode Profiles List For Inbox
	 * @param _patCrNo Patient CR No.
	 * @param _unitCode Unit Code
	 * @param _userVO User Detail
	 * @return
	 */
	public List<ConsultationProfileDtlVO> getPatientProfilesForInbox(String _mailRequestId, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientProfilesForInbox(_mailRequestId, _userVO);
	}
	
	public void saveExtInvestigationDtl(List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveExtInvestigationDtl(lstEpiExtInvDtlVO,userVO );
	}
	
	public EpisodeExtInvDtlVO[] getAddedExternalInvDetail(String patCrNo,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getAddedExternalInvDetail(patCrNo,userVO );
	}
	

	public ConsultationDtlVO getMailDetailsByMailId(UserVO _userVO,String _mailId)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getMailDetailByMailId(_userVO, _mailId);
	}
	
	

	public Map fetchProfileDetails(PatientProfileDetailVO _patientProfileDtlVO,String genderCode, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.fetchProfileDetails(_patientProfileDtlVO,genderCode, _userVO);
	}
	
	public void updateProfileHeaderDetail(PatientProfileDetailVO _patProfileDtlVO,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.updateProfileHeaderDetail(_patProfileDtlVO,_userVO);
	}
	
	public void profileGeneration(PatientProfileDetailVO _patProfileDtlVO,ProfileGroupDtlVO[] profileGroupDtlVO,ProfileAccessPolicyVO profileAccessPolicy, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.profileGeneration(_patProfileDtlVO,profileGroupDtlVO,profileAccessPolicy,_userVO);
	}
	
	public ProfileInvestigationVO[] getPatientProfileInvestigationDetail(String _crNo, String _deskType, UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getOpdInvestigationDetailProfilePatientEpisodes(_crNo,_deskType, _userVO, voDP, profileGenerationMode);
	}
	
	public Map fetchDetailsForGenerate(PatientProfileDetailVO _patientProfileDtlVO,String _deskType, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.fetchDetailsForGenerate(_patientProfileDtlVO,_deskType, _userVO);
	}
	
	public void removeUserPriv(ProfileAccessDetailVO _profileAccessDetailVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.removeUserPriv(_profileAccessDetailVO, _userVO);
	}
	
	public PatDrugTreatmentDetailVO[] getPatientTreatmentDetail(String _crNo, PatientDetailVO voDP, String profileGenerationMode,String _deskType,UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientTreatmentDetail(_crNo, voDP, profileGenerationMode,_deskType, _userVO));
	}

	
	public void revokeAllergy(EpisodeAllergiesVO epiAllergyVO, UserVO userVO )
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.revokeAllergy(epiAllergyVO, userVO);
	}
	
	public PatientAlertsDetailVO[] getPatientAlertsDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientAlertsDetail(_crNo, _userVO, voDP, profileGenerationMode));
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
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientProfilesForAll(_patCrNo, _unitCode, _userVO);
	}
	

	public DoctorRoundDtlVO[] getPatientProgressNotes(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientProgressNotes(_crNo, _userVO, voDP, profileGenerationMode));
	}
	

	// Saving Image to Patient in Image Examination
	/*public void savePatientExaminationImage(OpdPatientImageDtlVO _vo, BufferedImage _image, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi)super.getServiceProvider();
		serviceBO.savePatientExaminationImage(_vo, _image, _userVO);
	}*/
	
	//Added by VASU on 21-AUG-2017
	public void savePatientExaminationImage(OpdPatientImageDtlVO patimageVO, UserVO userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi)super.getServiceProvider();
		serviceBO.savePatientExaminationImage(patimageVO, userVO);
	}
	
	//End VASU
	
	// Modifying Image to Patient in Image Examination
	public void modifyPatientExaminationImage(OpdPatientImageDtlVO _vo, BufferedImage _image, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi)super.getServiceProvider();
		serviceBO.modifyPatientExaminationImage(_vo, _image, _userVO);
	}

	//Profile Review Detail	
	public PatientProfileDetailVO[] getPreviousProfileDetails(String _crNo, UserVO _userVO) 
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPreviousProfileDetails(_crNo, _userVO));
	}
	
	public void savePatientProfileReviewDetail(ProfileReviewDtlVO profileReviewDtlVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.savePatientProfileReviewDetail(profileReviewDtlVO, _userVO);
	}

	public ProfileReviewDtlVO[] fetchProfileReviewDetails(String _crNo,String profileID, UserVO _userVO) 
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.fetchProfileReviewDetails(_crNo,profileID, _userVO));
	}
	
	public ProfileOTDetailVO[] getPatientOperationDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientOperationDetail(_crNo, _userVO, voDP, profileGenerationMode));
	}
	
	public Map getPatientProfileOperationTemplateDetails(List profileOTList,String patCrNo, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getPatientProfileOperationTemplateDetails(profileOTList,patCrNo, _userVO);
	}
	
	/**
	 * Saving Visit Summary Detail
	 * @param _episodeSummaryVO Episode Summary Detail
	 * @param _userVO User DEtail
	 * @param _episodeActDtlVO Episode Action Detail for CMO Desk
	 * @param _deskType Desk Type
	 */
	public void saveVisitSummaryDetail(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO, String _deskType, EpisodeCloseVO _episodeCloseVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.saveVisitSummaryDetail(_episodeSummaryVO, _userVO, _episodeActDtlVO, _deskType, _episodeCloseVO);
	}

	/**
	 * Modifying Visit Summary Detail
	 * @param _episodeSummaryVO Episode Summary Detail
	 * @param _userVO User DEtail
	 * @param _episodeActDtlVO Episode Action Detail for CMO Desk
	 * @param _deskType Desk Type
	 */
	public void modifyVisitSummaryDetail(EpisodeSummaryDetailVO _episodeSummaryVO, UserVO _userVO, EpisodeActionDtlVO _episodeActDtlVO, String _deskType, EpisodeCloseVO _episodeCloseVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.modifyVisitSummaryDetail(_episodeSummaryVO, _userVO, _episodeActDtlVO, _deskType, _episodeCloseVO);
	}
	
	public void savePatientAttendantDtl(String isExisting,PatientFamilyDtlVO patFamilyVO,EpisodeAttendantDetailVO epiAttendantVO,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.savePatientAttendantDtl(isExisting,patFamilyVO,epiAttendantVO,userVO);
	}

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
	public Map<String, Object> getChartReportingData(String _deskType, PatientDetailVO _patDtlVO, ChartMasterVO _chartVO, List<ChartParameterMappingVO> _lstChartParas, String _criteria, String _fromDate, String _toDate, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getChartReportingData(_deskType, _patDtlVO, _chartVO, _lstChartParas, _criteria, _fromDate, _toDate, _userVO);
	}

	/**
	 * Getting Column Chart Paramter List
	 * 
	 * @param _chartVO Chart Detail
	 * @param _userVO User Detail
	 * @return List Chart Paras
	 */
	public List<ChartParameterMappingVO> getChartParamtersList(ChartMasterVO _chartVO, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getChartParamtersList(_chartVO, _userVO);
	}
	public EpisodeSummaryDetailVO[] getPatientOPDProgressNotes(
			EpisodeVO episodeVO, UserVO userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientOPDProgressNotes(episodeVO, userVO));
	}
	
	public Map getDischargeProfileEssentials(String _deptUnitCode, String patCrNo, UserVO _userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getDischargeProfileEssentials(_deptUnitCode, patCrNo, _userVO);
	}
	
	/*Functions Added By Pawan Kumar B N*/
	
	public void savePatientComplaintsDtl(List<EpisodeExtInvDtlVO> lstEpiExtInvDtlVO,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		serviceBO.savePatientComplaintsDtl(lstEpiExtInvDtlVO,userVO );
	}
	
	public EpisodeExtInvDtlVO[] getAddedPatientComplaintDetail(String patCrNo,UserVO userVO)
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getAddedPatientComplaintDetail(patCrNo,userVO );
	}
	public  List getEssentialDiagnosisDetail(EpisodeDiagnosisVO previousDiagVO, UserVO userVO) {
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return serviceBO.getEssentialDiagnosisDetail(previousDiagVO, userVO);

	}
/**
## 		Modification Log		: added getPatientOutTakeDetail				
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/

	public PatIntakeOutDtlVO[] getPatientOutTakeDetail(String patCrNo,PatientDetailVO voDp, String profileGenerationMode, UserVO userVO) 
	{
		OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientOutTakeDetail(patCrNo, voDp, profileGenerationMode, userVO));
	}

	/*AddedBy: NehaRajgariya Date:28July2016*/
	public String getIcdHospCode(String deptUnitCode,UserVO userVO) {
	// TODO Auto-generated method stub
	OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
	return (serviceBO.getIcdHospCode(deptUnitCode,userVO));
}
	
	// added by VASU on 06.11.17
		public ProfileInvestigationVO[] getPatientProfileTestInvestigationDetail(String _crNo, String _episodeCode,String _admissionNo,String _deskType, UserVO _userVO,String age,String _reqDno)
		{
			OpdPatientBOi serviceBO = (OpdPatientBOi) super.getServiceProvider();
			return serviceBO.getTestInvestigationDetailProfilePatientEpisodes(_crNo, _episodeCode,_admissionNo,_deskType, _userVO,age,_reqDno);
		}
		
		
		public  byte[] fetchImageFromPostgres(String imgCode) {
			OpdPatientBOi serviceBO = (OpdPatientBOi)super.getServiceProvider();
			byte[] getdoc=serviceBO.fetchImageFromPostgres(imgCode);
			return getdoc;
		}
		
		public  byte[] viewImageFromPostgres(OpdPatientImageDtlVO vo) {
			OpdPatientBOi serviceBO = (OpdPatientBOi)super.getServiceProvider();
			byte[] getdoc=serviceBO.viewImageFromPostgres(vo);
			return getdoc;
		}
}
