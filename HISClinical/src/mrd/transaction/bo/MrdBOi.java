package mrd.transaction.bo;

import java.util.List;
import java.util.Map;

import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.MRDDocumentUploadVO;
import hisglobal.vo.AdmissionAdviceVO;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.AssignmentChangeDtlVO;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.CaseSheetLostFoundVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.InsuranceDetailVO;
import hisglobal.vo.MrdIcdDtlVO;
import hisglobal.vo.MrdRackShelfChangeDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.MrdRecordMovementVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.MrdRecordReturnDtlVO;
import hisglobal.vo.OpdPatientImageDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatFamilyDocDtlVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.SummonDetailVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.UserVO;

public interface MrdBOi 
{
	/**  Getting The List of All The Episodes of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public EpisodeVO[] getAllEpisodeOfThePatient(String crNo,UserVO userVO);
	
	/**  Getting the List of Admission Advice Given to the Patient on a Particular Episode 
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public AdmissionAdviceVO[] getAdmissionAdvice(String crNo,String epiCode,UserVO userVO);
	
	/** Getting the List of Rest Advice Given to the Patient on a Particular Episode 
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public EpisodeRestAdviceVO[] getEpisodeRestAdvice(String crNo,String epiCode,UserVO userVO);
	
	/**  Saving the Medical Certificate Details 
	 * @param patMedicalDtlVO
	 * @param userVO
	 */
	public void saveOnBasisRestAdvice(PatMedicalDtlVO patMedicalDtlVO,String restFlag,String unitCode,String genMode,UserVO userVO);
	
	/** Getting the List of All Visits of the patient on a Particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public EpisodeVO[] getAllVisitOfEpisodePat(String crNo,String epiCode,UserVO userVO);
	
	/**  Getting the List of Medical Certificate Generated for the Patient On a particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] getIssuedMedicalCertificate(String crNo,String epiCode,UserVO userVO);
	
	/**  Saving the Fitness Date of The Patient
	 * @param patFitnessDtlVO
	 * @param userVO
	 */
	public void saveFitnessDate(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO);
	
	/** Saving the Issue Certificate Detail
	 * @param certificateIssueDtlVO
	 * @param userVO
	 */
	public void saveCertificateIssueDtl(RecordDispatchDtlVO dispatchVO, CertificateIssueDtlVO certificateIssueDtlVO,UserVO userVO);
	
	/** Saving the Change Duration Information of the Medical Certificate
	 * @param patMedicalDtlVO
	 * @param userVO
	 */
	public void saveExtendMedicalCertificate(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO);
	

	/** Saving the Certificate Received Details
	 * @param certificateRcvMode
	 * @param mrdRecordDtlVO
	 * @param userVO
	 */
	
	/* not in use
	 public void saveReceiveCertificate(String certificateRcvMode,MrdRecordDtlVO[] mrdRecordDtlVO,UserVO userVO);
	 */
	/** Getting The List of Certificate For Movement By Unit 
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public CertificateIssueDtlVO[] getAllCertificateForMoveBydUnit(String unitCode,UserVO userVO);
	
	/**  Saving Certificate Movement Detail
	 * @param recordMoveDtlVO
	 * @param userVO
	 */
	public void saveCertificateMovement(List<RecordCheckListDtlVO> lstRecordCheckList,RecordDispatchDtlVO[] recordMoveDtlVO,UserVO userVO);
	
	/** Getting The List of Certificate For Movement By Cr No 
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public CertificateIssueDtlVO[] getAllCertificateForMoveByCrNo(String crNo,UserVO userVO);
	
	public EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO);
	
	public EpisodeRestAdviceVO[] getEpisodeRestAdviceTodayVisited(String crNo,String epiCode,UserVO userVO);

	public void saveFitnessDateNExtendMC(PatMedicalDtlVO patMedicalDtlVO,PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO);
	
	public PatFamilyDocDtlVO[] getExistingFamilyDoctorRecord(String crNo,UserVO userVO);
	
	public void savePatientFamilyDoctorDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO);
	
	public PatFamilyDocDtlVO getPatientFamilyDocDetail(String chk,UserVO userVO);
	
	public void modifyPatientFamilyDocDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO);
	
	public void revokePatientFamilyDocDetail(String crNo,String hCode,String slNo,UserVO userVO);

	
	public List<CaseSheetDtlVO> getCaseSheetListToReady(String unitCode,String wardCode,UserVO userVO);
	
	//added by swati sagar on date:01-may-2019
	public List<CaseSheetDtlVO> getCaseSheetListToReadyADMNOWISE(String admno,UserVO userVO);
	//added by swati sagar on date:02-may-2019
	public List<CaseSheetDtlVO> getCaseSheetListToReadyCRNOWISE(String crno,UserVO userVO);
	
	public void saveCaseSheetDispatch(RecordDispatchDtlVO[] casesheetDispatchVOArray,CaseSheetDtlVO[] caseSheetDtlVOArray,Map enclosureMap,List checklistMstVOList,UserVO userVO);
	
	//public RecordTypeWiseEnclosureMstVO[] getAllEnclosureDetails(UserVO userVO);
	
	public Map getAllEnclosureDetails(CaseSheetDtlVO caseSheetDtlVO,UserVO userVO);
	
	public RecordTypeCheckListMstVO[] getAllChecklistDetails(String checklistMode, UserVO userVO);

	
	public void saveNotUsedCrNo(List<CrNoMergeDtlVO> lstNotUsedCRNo,UserVO userVO);
	
	public List<PatientVO> getMergedCrNo(String crNo,UserVO userVO);
	
	public void revokeMergedCRNo(String reason,String mainCrNo,String crNo,UserVO userVO);
	
	public List getMainCRNumberList(UserVO userVO);
	
	public Map getEpisodesEMR(PatientVO patientVO,UserVO userVO);

	public List getPatientImageDtlByCrNo(String crNo, UserVO userVO);
	
	public byte[] latestFetchImagePostgres (String fileNo);

	public void deletePatientImage(PatientImageDtlVO[] patimageVO,
			UserVO userVO);

	public void savePatientImage(PatientImageDtlVO[] patimageVO, UserVO userVO);

	public void modifyPatientImage(PatientImageDtlVO[] patimageVO, UserVO userVO);
	
	//*********************** Case Sheet Monitoring******************************************
	
	public List<CaseSheetDtlVO> getPatientCaseSheetDtl(String deptUnitCode,String ward,UserVO userVO);

	public void updatePatientCaseSheetStatus(CaseSheetDtlVO caseSheetDtlVO,
			CaseSheetLostFoundVO caseSheetLostFoundVO, String updateType, UserVO userVO);

	public List<CaseSheetDtlVO> getPatientCaseSheetDtlByCrNo(String patCrNo,UserVO userVO);

	public CaseSheetLostFoundVO getPatientCaseSheetLostFoundDtl(
			CaseSheetLostFoundVO caseSheetLostFoundVO, UserVO userVO);

	
	public Map getPersonalProfile(PatientVO patientVO,String departmentUnitCode,String tabType,UserVO userVO);
	
	public PatientVO searchPatientByCrNoEMR(PatientVO _patientVO, UserVO _userVO);
	
	public EpisodeAllergiesVO[] getEpisodeAllergiesVisitWise(EpisodeAllergiesVO _episodeAllergiesVO,UserVO _userVO);
	
	public PatientAlertsDetailVO[] getPatientAlertsEMR(PatientAlertsDetailVO _patientAlertsDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO);
	
	public EpisodeDiagnosisVO[] getDiagnosisDetailsEpisodeVisitWiseEMR(EpisodeDiagnosisVO _episodeDiagnosisVO,UserVO _userVO);
	
	public EpisodeDiagnosisVO[] getAllDiagnosisDetails(EpisodeDiagnosisVO _episodeDiagnosisVO,String[] departmentUnitArray, String accessType, UserVO _userVO);
	
	public EpisodeAllergiesVO[] getEpisodeAllergiesAll(EpisodeAllergiesVO _episodeAllergiesVO,String[] departmentUnitArray, String accessType, UserVO _userVO);

	
	public PatDrugTreatmentDetailVO[] getPateintDrugAdviceAll(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO);
	
	public PatDrugTreatmentDetailVO[] getPateintDrugAdviceAllOffline(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO);

	public ProfileInvestigationVO[] getPatientInvestigationDetails(ProfileInvestigationVO _profInvestigationVO,String [] departmentUnitArray,String accessType,UserVO _userVO);
	
	public Map getOutParaDetailEMR(PatientDetailVO dailyPatVO,String []departmentUnitArray,String accessType,UserVO userVO);
	
	/**
	 * To get patient progress notes by crno
	 * @param doctorRoundDtlVO
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param userVO
	 * @return DoctorRoundDtlVO[]
	 */
	public DoctorRoundDtlVO[] getPatientProgressNotesEMR(DoctorRoundDtlVO doctorRoundDtlVO, String[] departmentUnitArray, String accessType, UserVO userVO);
	
	/**
	 * To get patient vitals details for EMR
	 * @param _patientClinicalDetailVO
	 * @param _userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public PatientClinicalDetailVO[] getPatientVitalsEMR(PatientClinicalDetailVO _patientClinicalDetailVO, String [] departmentUnitArray,String accessType,UserVO _userVO);
	
	/**
	 * To get patient external investigation details for EMR
	 * @param _episodExtInvDtlVO
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param _userVO
	 * @return EpisodeExtInvDtlVO[]
	 */
	public EpisodeExtInvDtlVO[] getPatientExternalExaminationEMR(EpisodeExtInvDtlVO _episodExtInvDtlVO, String[] departmentUnitArray, String accessType, UserVO _userVO);
	
	/**
	 * To get patient Complaints details for EMR
	 * @param _patientClinicalDetailVO
	 * @param _userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public PatientClinicalDetailVO[] getPatientComplaintsEMR(PatientClinicalDetailVO _patientClinicalDetailVO,String _templateCategory, UserVO _userVO);

	
	public void saveForBirthRegUpload(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO);
	
	public void saveHandoverForBirthRegUpload(BirthDeathUploadDtlVO birthHandoverVO,UserVO userVO);
	
	
	public List<PatientProfileDetailVO> getPatientProfilesForAll(String patCrNo,
			String[] departmentUnitArray, String accessType, UserVO userVO);
	
	/**
	 * To get patient diagnosis images
	 * @param _opdPatientImageDtlVO
	 * @param _userVO
	 * @return OpdPatientImageDtlVO[]
	 */
	public OpdPatientImageDtlVO[] getPatientDiagnosisImages(OpdPatientImageDtlVO _opdPatientImageDtlVO, UserVO _userVO);

	
	
	public void saveSummonDetail(SummonDetailVO summonDetailVO, UserVO userVO);

	
	/**To get patient operation performed list
	 * @param crNo
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param _userVO
	 * @return List
	 */
	public List getPatientOperationList(String crNo, String[] departmentUnitArray, String accessType, UserVO _userVO);
	
	/**To get template list and para id value map
	 * @param crNo
	 * @param deptCode
	 * @param reqNo
	 * @param _userVO
	 * @return Map
	 */
	public Map getPatientOperationTemplateParaValueId(String crNo,String deptCode,String reqNo, UserVO _userVO);


	
	public void saveSummonAssigmentDetail(SummonDetailVO summonDetailVO, UserVO userVO);
	
	public void savePostSummonDetail(SummonDetailVO summonDetailVO, UserVO userVO);
	
	public void saveChangeAssignDetail(AssignmentChangeDtlVO assignChangeDtlVO,SummonDetailVO summonDetailVO, UserVO userVO);
	
	public void saveInsuranceDetail(InsuranceDetailVO insuranceDetailVO, UserVO userVO);
	
	public void rejectCertificateHandover(List<RecordDispatchDtlVO> lstRejectRecord,UserVO userVO);
	
	public void saveRecordAccepted(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO);
	
	public void saveRecordArchived(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO);
	
	public void saveRecordLost(List<RecordLostFoundDtlVO> lstLostRecord,UserVO userVO);
	
	public void saveRecordArchivalInMrd(List<MrdRecordDtlVO> lstMrdRecordVO,UserVO userVO);
	
	public void saveFoundDetailNArchivedInMrd(List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordLostFoundDtlVO> lstFoundRecordVO,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO);
	
	public void saveForOfflineRequestDetail(MrdRecordRequestDtlVO mrdRecordRequestVO,List<RequestRecordDtlVO> lstRequestRecordVO,UserVO userVO);
	
	public void saveIcdIndexDetail(List<MrdIcdDtlVO> lstmrdVo,UserVO userVO,PatientDetailVO patDtlVO);
	
	public void saveOnlineRequestDetail(MrdRecordRequestDtlVO mrdRecordRequestVO,List<RequestRecordDtlVO> lstRequestRecordVO,UserVO userVO);

	public ANCDetailVO[] getPatientAncDetails(ANCDetailVO _ancDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO);

	
	public Map getCaseSheetEnquiryEssentials(UserVO userVO);
	
	public CommonCaseSheetEnquiryVO[] searchPatientCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO);
	
	public MrdRecordDtlVO[] fetchRecordStorageDetail(CommonCaseSheetEnquiryVO _commonCaseSheetEnquiryVO,UserVO _userVO);


	public RecordDispatchDtlVO[] getRecordDispatchList(RecordDispatchDtlVO recordDispatchVO,
			UserVO userVO);
	
	//ADDED BY SWATI  ON DATE:06-MAY-2019
		//ADM NO WISE DTL
	public RecordDispatchDtlVO[] getCaseSheetListToReadyADMNOWISE2(String admno,
			UserVO userVO);
	
	
	//ADDED BY SWATI  ON DATE:07-MAY-2019
		//CR NO WISE DTL
		public RecordDispatchDtlVO[] getCaseSheetListToReadyCRNOWISE2(String crno,
				UserVO userVO);

	public Map getEnclosureSummary(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO);

public void saveCaseSheetHandoverDetail(List<RecordDispatchDtlVO> recordDispatchVOList,
			List checklistVOList,Map enclosureMap, String isAccepted, UserVO userVO);

	//public void saveCaseSheetHandoverDetail(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO);
	
	public RecordTypeCheckListMstVO[] getChecklistForHandOver(String recordType,
			String checklistMode, UserVO userVO);

	public RecordDispatchDtlVO[] getRecordListByPatCrNo(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO);

	public Map getEssentialForCaseSheetHandOver(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO);
	
	public void saveCaseSheetApprovalDetail(List recordDispatchVOList, List<CaseSheetDtlVO> caseSheetDtlVOList, UserVO userVO);
	
	public void saveSummonAcceptenceDetail(String  summonId, UserVO userVO);
	
	public void saveInsuranceClaimRecordEntry(InsuranceDetailVO insuranceDetailVO, UserVO userVO);
	
	public void saveMrdRecordReturnDetail(List<MrdRecordReturnDtlVO> mrdRecordReturnDtlList,List<MrdRecordDtlVO> mrdRecordDtlVOList,String isRequestReturn,List<MrdRackShelfChangeDtlVO> mrdRackShelfChangeList,UserVO userVO);
	
	public void saveMrdRecordLostDetail(MrdRecordLostFoundDtlVO mrdRecordLostVO,UserVO userVO);
	
	public MrdRecordDtlVO[] getMrdRecordBasedOnShelfList(String recordType,String shelfId,UserVO userVO);
	
	public void saveRecordMovementDetail(List<MrdRackShelfChangeDtlVO> lstMrdRackShelfChangeVO,UserVO userVO);
	
	public void saveRecordFoundNArchivalDetail(MrdRecordLostFoundDtlVO mrdFoundVO,MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO);
	
	public  void deleteRejectedRecordDetail(String requestId,UserVO userVO);


	//EMR
	public List getDepartmentUnitListForEMR(UserVO userVO);

	public Map getListOfOpdFilesToMove(String _mrdCode, UserVO _userVO);

	public void saveOPDFileMovementDetail(MrdRecordDtlVO[] _mrdRecordVO,MrdRecordMovementVO[] _movementVO,UserVO _userVO);

	public Map getListOfOpdFilesToReturn(String mrdCode,UserVO userVO);

	public Map getPatientVisitSummaryForEMR(EpisodeVO episodeVO,String[] departmentUnitArray, String accessType,UserVO userVO);
	
	public List getPatientCRNoMergeList(CrNoMergeDtlVO voCRMerge, String[] departmentUnitArray, String accessType, UserVO userVO);

	public void saveExtendDays(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO);

	public MrdRecordRequestDtlVO getEssentials(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO);

	
	public void updateSL_NO(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO);
	
	public void savePatientMRDDocumentdetails(MRDDocumentUploadVO[] docUploadVos,MRDDocumentUploadVO[] documentUploadRevokeDtlVOs, UserVO userVO);
	
	//Added by Dheeraj
	public byte[] fetchImageFromPostgres(MRDDocumentUploadVO mrdDocumentUploadVO);
	
	//Added By Shweta on 09-MAY-2019
	public List<DocumentUploadDtlVO> getDocumentArchivalEssentials(String patCrNo,String episodeCode, UserVO _userVO);
	
	// Added By Shweta for fetching External Treatment Detail on 15-May-2019
		public PatDrugTreatmentDetailVO[] getPateintExtTreatmentDetails(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO);
		public RecordDispatchDtlVO[] getRecordListByPatAdmNo(RecordDispatchDtlVO recordDispatchVO, UserVO userVO);	
		
		
}
