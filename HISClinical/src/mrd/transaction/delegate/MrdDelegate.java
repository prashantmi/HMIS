package mrd.transaction.delegate;

import hisglobal.business.Delegate;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.AdmissionAdviceVO;
import hisglobal.vo.AssignmentChangeDtlVO;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.CaseSheetDtlVO;
import hisglobal.vo.CaseSheetLostFoundVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.CrNoMergeDtlVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeAllergiesVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeExtInvDtlVO;
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
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.RecordCheckListDtlVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.RecordLostFoundDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import opd.dao.OpdEssentialDAO;
import registration.bo.PatientBOi;
import mrd.transaction.bo.MrdBO;
import mrd.transaction.bo.MrdBOi;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import mrd.vo.MRDDocumentUploadVO;

public class MrdDelegate extends Delegate {

	/**
	 * Constructor for Setting Service Provider
	 */
	public MrdDelegate() {
		super(new MrdBO()); // /<<Setting the service provider
	}

	/** Getting The List of All The Episodes of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public EpisodeVO[] getAllEpisodeOfThePatient(String crNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAllEpisodeOfThePatient(crNo, userVO);
	}
	
	/**  Getting the List of Admission Advice Given to the Patient on a Particular Episode 
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public AdmissionAdviceVO[] getAdmissionAdvice(String crNo,String epiCode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAdmissionAdvice(crNo,epiCode, userVO);
	}
	
	/** Getting the List of Rest Advice Given to the Patient on a Particular Episode 
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public EpisodeRestAdviceVO[] getEpisodeRestAdvice(String crNo,String epiCode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getEpisodeRestAdvice(crNo,epiCode, userVO);
	}
	
	/**  Saving the Medical Certificate Details 
	 * @param patMedicalDtlVO
	 * @param userVO
	 */
	public void saveOnBasisRestAdvice(PatMedicalDtlVO patMedicalDtlVO,String restFlag,String unitCode,String genMode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveOnBasisRestAdvice(patMedicalDtlVO,restFlag, unitCode,genMode, userVO);
	}
	
	/** Getting the List of All Visits of the patient on a Particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public EpisodeVO[] getAllVisitOfEpisodePat(String crNo,String epiCode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAllVisitOfEpisodePat(crNo,epiCode, userVO);
	}
	
	/** Getting the List of Medical Certificate Generated for the Patient On a particular Episode
	 * @param crNo
	 * @param epiCode
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] getIssuedMedicalCertificate(String crNo,String epiCode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getIssuedMedicalCertificate(crNo,epiCode, userVO);
	}
	
	/**  Saving the Fitness Date of The Patient
	 * @param patFitnessDtlVO
	 * @param userVO
	 */
	public void saveFitnessDate(PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveFitnessDate(patFitnessDtlVO,unitCode,genMode, userVO);
	}
	
	/** Saving the Issue Certificate detail
	 * @param certificateIssueDtlVO
	 * @param userVO
	 */
	public void saveCertificateIssueDtl(RecordDispatchDtlVO dispatchVO, CertificateIssueDtlVO certificateIssueDtlVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveCertificateIssueDtl(dispatchVO,certificateIssueDtlVO, userVO);
	}
	
	/** Saving the Change Duration Information of the Medical Certificate
	 * @param patMedicalDtlVO
	 * @param userVO
	 */
	public void saveExtendMedicalCertificate(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveExtendMedicalCertificate(patMedicalDtlVO, userVO);
	}
	

	/** Saving the Certificate Received Details
	 * @param certificateRcvMode
	 * @param mrdRecordDtlVO
	 * @param userVO
	 */
/*	public void saveReceiveCertificate(String certificateRcvMode,MrdRecordDtlVO[] mrdRecordDtlVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveReceiveCertificate(certificateRcvMode,mrdRecordDtlVO, userVO);
	}*/
	

	/** Getting The List of Certificate For Movement By Unit 
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public CertificateIssueDtlVO[] getAllCertificateForMoveBydUnit(String unitCode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAllCertificateForMoveBydUnit(unitCode,userVO);
	}
	
	/** Getting The List of Certificate For Movement By Cr No 
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public CertificateIssueDtlVO[] getAllCertificateForMoveByCrNo(String crNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAllCertificateForMoveByCrNo(crNo,userVO);
	}
	
	/**  Saving Certificate Movement Detail
	 * @param recordMoveDtlVO
	 * @param userVO
	 */
	public void saveCertificateMovement(List<RecordCheckListDtlVO> lstRecordCheckList,RecordDispatchDtlVO[] recordMoveDtlVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveCertificateMovement(lstRecordCheckList,recordMoveDtlVO, userVO);
	}
	
	public EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAllEpisodeOfThePatientTodayVisited(crNo, userVO);
	}
	
	public EpisodeRestAdviceVO[] getEpisodeRestAdviceTodayVisited(String crNo,String epiCode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getEpisodeRestAdviceTodayVisited(crNo,epiCode, userVO);
	}

	public void saveFitnessDateNExtendMC(PatMedicalDtlVO patMedicalDtlVO,PatFitnessDtlVO patFitnessDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveFitnessDateNExtendMC(patMedicalDtlVO,patFitnessDtlVO,unitCode,genMode, userVO);
	}

	
	public PatFamilyDocDtlVO[] getExistingFamilyDoctorRecord(String crNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getExistingFamilyDoctorRecord(crNo, userVO);
	}
	
	public void savePatientFamilyDoctorDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.savePatientFamilyDoctorDetail(patFamilyDocVO, userVO);
	}
	
	public PatFamilyDocDtlVO getPatientFamilyDocDetail(String chk,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientFamilyDocDetail(chk, userVO);
	}
	
	public void modifyPatientFamilyDocDetail(PatFamilyDocDtlVO patFamilyDocVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.modifyPatientFamilyDocDetail(patFamilyDocVO, userVO);
	}
	

	public List<CaseSheetDtlVO> getCaseSheetListToReady(String unitCode,String wardCode,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getCaseSheetListToReady(unitCode,wardCode,userVO);
	}
	
	public void saveCaseSheet(RecordDispatchDtlVO[] dispatchVO,
			CaseSheetDtlVO[] caseSheetDtlVOArray, Map enclosureMap,
			List checklistMstVOList, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveCaseSheetDispatch(dispatchVO, caseSheetDtlVOArray, enclosureMap, checklistMstVOList,userVO);
	}
	//not in use
	/*public void saveCaseSheet(CaseSheetDispatchVO _CaseSheetDispatchVO,RecordTypeWiseEnclosureMstVO[] _RecordTypeWiseEnclosureMstVOs,RecordTypeCheckListMstVO[] _RecordTypeCheckListMstVOs,CaseSheetDtlVO caseSheetDtlVO, UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveCaseSheetDispatch(_CaseSheetDispatchVO, _RecordTypeWiseEnclosureMstVOs, _RecordTypeCheckListMstVOs, caseSheetDtlVO,userVO);
	}*/
	
	public Map getAllEnclosureDetails(CaseSheetDtlVO caseSheetDtlVO, UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAllEnclosureDetails(caseSheetDtlVO,userVO);
	}
	
	//added by swati
	//date:-01-may-2019
	public List<CaseSheetDtlVO> getCaseSheetListToReadyADMNOWISE(String admno,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getCaseSheetListToReadyADMNOWISE(admno,userVO);
	}
	
	//added by swati
	//date:-02-may-2019
	public List<CaseSheetDtlVO> getCaseSheetListToReadyCRNOWISE(String crno,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getCaseSheetListToReadyCRNOWISE(crno,userVO);
	}
	
	public RecordTypeCheckListMstVO[] getAllChecklistDetails(String checklistMode, UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAllChecklistDetails(checklistMode,userVO);
	}

	public void revokePatientFamilyDocDetail(String crNo,String hCode,String slNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.revokePatientFamilyDocDetail(crNo,hCode,slNo, userVO);
	}
	
	public void saveNotUsedCrNo(List<CrNoMergeDtlVO> lstNotUsedCRNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveNotUsedCrNo(lstNotUsedCRNo, userVO);
	}
	
	public List<PatientVO> getMergedCrNo(String crNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getMergedCrNo(crNo,userVO);
	}

	public void revokeMergedCRNo(String reason,String mainCrNo,String crNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.revokeMergedCRNo(reason,mainCrNo,crNo,userVO);
	}

	
	public List getMainCRNumberList(UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getMainCRNumberList(userVO);
	}
	
	public Map getEprEssentials(PatientVO _patientVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getEpisodesEMR(_patientVO, userVO);
	}

	
	public Map getPersonalProfile(PatientVO _patientVO,String departmentUnitCode, String tabType, UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPersonalProfile(_patientVO,departmentUnitCode,tabType ,userVO);
	}
	
	public PatientVO getPatientDtlByCrNoEMR(PatientVO _patientVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.searchPatientByCrNoEMR(_patientVO, userVO);
	}
	
	public EpisodeAllergiesVO[] getEpisodeAllergiesVisitWise(EpisodeAllergiesVO _episodeAllergiesVO,UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getEpisodeAllergiesVisitWise(_episodeAllergiesVO, _userVO);
	}
	
	public PatientAlertsDetailVO[] getPatientAlertDetails(PatientAlertsDetailVO _patientAlertsDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientAlertsEMR(_patientAlertsDetailVO, departmentUnitArray,accessType,_userVO);
	}
	
	public EpisodeDiagnosisVO[] getDiagnosisDetailsEpisodeVisitWise(EpisodeDiagnosisVO _episodeDiagnosisVO,UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getDiagnosisDetailsEpisodeVisitWiseEMR(_episodeDiagnosisVO, _userVO);
	}
	
	public EpisodeDiagnosisVO[] getAllDiagnosisDetails(EpisodeDiagnosisVO _episodeDiagnosisVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getAllDiagnosisDetails(_episodeDiagnosisVO, departmentUnitArray,accessType,_userVO);
	}
	
	public EpisodeAllergiesVO[] getEpisodeAllergiesAll(EpisodeAllergiesVO _episodeAllergiesVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getEpisodeAllergiesAll(_episodeAllergiesVO,departmentUnitArray,accessType, _userVO);
	}

	
	public List getPatientImageDtlByCrNo(String crNo,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientImageDtlByCrNo(crNo, userVO);
	}
	
	
	public byte[] latestFetchImagePostgres(String fileNo)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.latestFetchImagePostgres(fileNo);
	}

	public void deletePatientImage(PatientImageDtlVO[] patimageVO, UserVO userVO) {

		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.deletePatientImage(patimageVO, userVO);
		
	}

	public void savePatientImage(PatientImageDtlVO[] patimageVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.savePatientImage(patimageVO, userVO);
	}

	public void modifyPatientImage(PatientImageDtlVO[] patimageVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.modifyPatientImage(patimageVO, userVO);
		
	}
	
	public PatDrugTreatmentDetailVO[] getPatientAllDrugAdviceDetails(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPateintDrugAdviceAll(_patDrugTreatmentDetailVO,departmentUnitArray,accessType, _userVO);
	}
	
	public PatDrugTreatmentDetailVO[] getPatientAllDrugAdviceOfflineDetails(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPateintDrugAdviceAllOffline(_patDrugTreatmentDetailVO,departmentUnitArray,accessType, _userVO);
	}

	public ProfileInvestigationVO[] getPatientInvestigationDetails(ProfileInvestigationVO _proInvestigationVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientInvestigationDetails(_proInvestigationVO,departmentUnitArray,accessType, _userVO);
	}

	/* ********************************Case Sheet Monitoring******************************************/
	
	public List<CaseSheetDtlVO> getPatientCaseSheetDtlByCrNo(String patCrNo,
			UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientCaseSheetDtlByCrNo(patCrNo, userVO);
	}

	public List<CaseSheetDtlVO> getPatientCaseSheetDtl(String deptUnitCode,
			String wardCode, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientCaseSheetDtl(deptUnitCode,wardCode, userVO);
	}

	public void updatePatientCaseSheetStatus(CaseSheetDtlVO caseSheetDtlVO,
			CaseSheetLostFoundVO caseSheetLostFoundVO, String updateType, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.updatePatientCaseSheetStatus(caseSheetDtlVO,caseSheetLostFoundVO,updateType, userVO);
		
	}

	public CaseSheetLostFoundVO getPatientCaseSheetLostFoundDtl(
			CaseSheetLostFoundVO caseSheetLostFoundVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientCaseSheetLostFoundDtl(caseSheetLostFoundVO, userVO);
	}

	public Map getOutParaDetailEMR(PatientDetailVO dailyPatVO,String []departmentUnitArray,String accessType,UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getOutParaDetailEMR( dailyPatVO, departmentUnitArray, accessType, userVO);
	}

	/**
	 * To get patient progress notes by crno
	 * @param doctorRoundDtlVO
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param userVO
	 * @return DoctorRoundDtlVO[]
	 */
	public DoctorRoundDtlVO[] getPatientProgressNotesEMR(DoctorRoundDtlVO doctorRoundDtlVO,String[] departmentUnitArray, String accessType, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientProgressNotesEMR(doctorRoundDtlVO,departmentUnitArray,accessType, userVO);
	}
	
	/**
	 * To get patient vitals details by cr no
	 * @param _pateintClinicalDetailVO
	 * @param departmentUnitArray 
	 * @param userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public PatientClinicalDetailVO[] getPatientVitalsDetailsEMR(PatientClinicalDetailVO _pateintClinicalDetailVO,String[] departmentUnitArray,String accessType, UserVO _userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientVitalsEMR(_pateintClinicalDetailVO,departmentUnitArray, accessType, _userVO);
	}
	
	/**
	 * To get patient external investigation details by cr no
	 * @param _episodExtInvDtlVO
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param userVO
	 * @return EpisodeExtInvDtlVO[]
	 */
	public EpisodeExtInvDtlVO[] getPatientExternalInvestigationDetailsEMR(EpisodeExtInvDtlVO _episodExtInvDtlVO,String[] departmentUnitArray, String accessType, UserVO _userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientExternalExaminationEMR(_episodExtInvDtlVO,departmentUnitArray,accessType, _userVO);
	}
	

	/**
	 * To get patient Complaints details EMR
	 * @param _pateintClinicalDetailVO
	 * @param userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public PatientClinicalDetailVO[] getPatientComplaintsDetailsEMR(PatientClinicalDetailVO _pateintClinicalDetailVO,String _templateCategory,UserVO _userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientComplaintsEMR(_pateintClinicalDetailVO,_templateCategory,_userVO);
	}

	/**
	 * To get patient profile details EMR
	 * @param departmentUnitArray 
	 * @param _pateintClinicalDetailVO
	 * @param userVO
	 * @return PatientClinicalDetailVO[]
	 */
	public List<PatientProfileDetailVO> getPatientProfilesForAll(String patCrNo,
			String[] departmentUnitArray, String accessType, UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientProfilesForAll(patCrNo, departmentUnitArray,accessType, userVO);
	}
	
	

	/**
	 * To get patient diagnosis images
	 * @param _opdPatientImageDtlVO
	 * @param _userVO
	 * @return OpdPatientImageDtlVO[]
	 */
	public OpdPatientImageDtlVO[] getPatientDiagnosisImages(OpdPatientImageDtlVO _opdPatientImageDtlVO,UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientDiagnosisImages(_opdPatientImageDtlVO, _userVO);
	}

	
	public void saveSummonDetail(SummonDetailVO summonDetailVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveSummonDetail(summonDetailVO, userVO);
	}
	


	/** To delegate request to BO for operation list
	 * @param crNo
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param userVO
	 * @return List
	 */
	public List getPatientOperationList(String crNo, String[] departmentUnitArray, String accessType, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientOperationList(crNo,departmentUnitArray,accessType, userVO);
	}
	
	/** To delegate request to BO for operation template details
	 * @param crNo
	 * @param userVO
	 * @return Map
	 */
	public Map getPatientOperationTemplateDetails(String crNo,String deptCode,String reqNo, UserVO _userVO)  {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientOperationTemplateParaValueId(crNo, deptCode, reqNo, _userVO);
	}
	


	public void saveSummonAssigmentDetail(SummonDetailVO summonDetailVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveSummonAssigmentDetail(summonDetailVO, userVO);
	}
	
	public void savePostSummonDetail(SummonDetailVO summonDetailVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.savePostSummonDetail(summonDetailVO, userVO);
	}
	

	public void saveForBirthRegUpload(BirthDeathUploadDtlVO birthUploadVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveForBirthRegUpload(birthUploadVO,userVO);
	}
	
	public void saveHandoverForBirthRegUpload(BirthDeathUploadDtlVO birthHandoverVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveHandoverForBirthRegUpload(birthHandoverVO,userVO);
	}

	public void saveChangeAssignDetail(AssignmentChangeDtlVO assignChangeDtlVO,SummonDetailVO summonDetailVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveChangeAssignDetail(assignChangeDtlVO,summonDetailVO, userVO);
	}
	
	public void saveInsuranceDetail(InsuranceDetailVO insuranceDetailVO, UserVO userVO){
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveInsuranceDetail(insuranceDetailVO, userVO);
	}
	

	public ANCDetailVO[] getPatientAncDetails(ANCDetailVO _ancDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientAncDetails(_ancDetailVO,departmentUnitArray,accessType, _userVO);
	}

	public void rejectCertificateHandover(List<RecordDispatchDtlVO> lstRejectRecord,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.rejectCertificateHandover(lstRejectRecord,userVO);
	}

	public Map getCaseSheetEnquiryEssentials(UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getCaseSheetEnquiryEssentials(userVO);
	}
	
	public CommonCaseSheetEnquiryVO[] searchPatientCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO){
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.searchPatientCaseSheet(caseSheetEnquiryVO,_userVO);
	}
	
	public MrdRecordDtlVO[] fetchRecordStorageDetail(CommonCaseSheetEnquiryVO _commonCaseSheetEnquiryVO,UserVO _userVO){
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.fetchRecordStorageDetail(_commonCaseSheetEnquiryVO,_userVO);
	}
	
	public void saveRecordAccepted(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveRecordAccepted(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,userVO);
	}
	
	public void saveRecordArchived(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveRecordArchived(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,userVO);
	}
	
	public void saveRecordLost(List<RecordLostFoundDtlVO> lstLostRecord,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveRecordLost(lstLostRecord,userVO);
	}
	
	public void saveRecordArchivalInMrd(List<MrdRecordDtlVO> lstMrdRecordVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveRecordArchivalInMrd(lstMrdRecordVO,userVO);
	}
	
	public void saveFoundDetailNArchivedInMrd(List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordLostFoundDtlVO> lstFoundRecordVO,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveFoundDetailNArchivedInMrd(lstEnclosure,lstMrdRecordVO,lstFoundRecordVO,lstCheckList,lstRecordEnclosureDtl,userVO);
	}
	
	public void saveForOfflineRequestDetail(MrdRecordRequestDtlVO mrdRecordRequestVO,List<RequestRecordDtlVO> lstRequestRecordVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveForOfflineRequestDetail(mrdRecordRequestVO,lstRequestRecordVO,userVO);
	}
	
	//added by swati on date:27-02-2019
	public void saveIcdIndexDetail(List<MrdIcdDtlVO> lstmrdVo,UserVO userVO,PatientDetailVO patDtlVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveIcdIndexDetail(lstmrdVo,userVO,patDtlVO);
	}
	
	public void saveOnlineRequestDetail(MrdRecordRequestDtlVO mrdRecordRequestVO,List<RequestRecordDtlVO> lstRequestRecordVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveOnlineRequestDetail(mrdRecordRequestVO,lstRequestRecordVO,userVO);
	}


	public RecordDispatchDtlVO[] getRecordDispatchList(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getRecordDispatchList(recordDispatchVO,userVO);
	}
	//ADDED BY SWATI  ON DATE:06-MAY-2019
			//ADM NO WISE DTL
	public RecordDispatchDtlVO[] getCaseSheetListToReadyADMNOWISE2(
			String admno, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getCaseSheetListToReadyADMNOWISE2(admno,userVO);
	}
	
	
	//ADDED BY SWATI  ON DATE:06-MAY-2019
	//CR NO WISE DTL
public RecordDispatchDtlVO[] getCaseSheetListToReadyCRNOWISE2(
	String crno, UserVO userVO) {
MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
return serviceBO.getCaseSheetListToReadyCRNOWISE2(crno,userVO);
}



	public Map getEnclosureSummary(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getEnclosureSummary(recordDispatchVO,userVO);
	}

	public void saveCaseSheetHandoverDetail(List<RecordDispatchDtlVO> recordDispatchVOList,
			List checklistVOList, Map enclosureMap, String isAccepted, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveCaseSheetHandoverDetail(recordDispatchVOList,checklistVOList,enclosureMap,isAccepted,userVO);
	}
	
	/*//prachi
	public void saveCaseSheetHandoverDetail(List<MrdRecordDtlVO> lstMrdRecordVO,List<RecordTypeWiseEnclosureMstVO> lstEnclosure,List<RecordCheckListDtlVO> lstCheckList,List<RecordEnclosureDtlVO> lstRecordEnclosureDtl,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveRecordAccepted(lstMrdRecordVO,lstEnclosure,lstCheckList,lstRecordEnclosureDtl,userVO);
	}*/

	public RecordTypeCheckListMstVO[] getChecklistForHandOver(String recordType,
			String checklistMode, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getChecklistForHandOver(recordType,checklistMode,userVO);
	}

	public RecordDispatchDtlVO[] getRecordListByPatCrNo(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getRecordListByPatCrNo(recordDispatchVO,userVO);
	}

	public Map getEssentialForCaseSheetHandOver(
			RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getEssentialForCaseSheetHandOver(recordDispatchVO,userVO);
	}

	public void saveCaseSheetApprovalDetail(List recordDispatchVOList,
			List<CaseSheetDtlVO> caseSheetDtlVOList, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveCaseSheetApprovalDetail(recordDispatchVOList,caseSheetDtlVOList,userVO);
	}
	
	public void saveSummonAcceptedDetail(String summonId, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveSummonAcceptenceDetail(summonId, userVO);
	}
	
	public void saveInsuranceClaimRecordEntry(InsuranceDetailVO insuranceDetailVO, UserVO userVO){
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveInsuranceClaimRecordEntry(insuranceDetailVO, userVO);
	}
	
	public void saveMrdRecordReturnDetail(List<MrdRecordReturnDtlVO> mrdRecordReturnDtlList,List<MrdRecordDtlVO> mrdRecordDtlVOList,String isRequestReturn,List<MrdRackShelfChangeDtlVO> mrdRackShelfChangeList,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveMrdRecordReturnDetail(mrdRecordReturnDtlList,mrdRecordDtlVOList,isRequestReturn,mrdRackShelfChangeList, userVO);
	}
	
	public void saveMrdRecordLostDetail(MrdRecordLostFoundDtlVO mrdRecordLostVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveMrdRecordLostDetail(mrdRecordLostVO, userVO);
	}
	
	public MrdRecordDtlVO[] getMrdRecordBasedOnShelfList(String recordType,String shelfId,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getMrdRecordBasedOnShelfList(recordType,shelfId, userVO);
	}
	
	public void saveRecordMovementDetail(List<MrdRackShelfChangeDtlVO> lstMrdRackShelfChangeVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveRecordMovementDetail(lstMrdRackShelfChangeVO, userVO);
	}
	
	public void saveRecordFoundNArchivalDetail(MrdRecordLostFoundDtlVO mrdFoundVO,MrdRecordDtlVO mrdRecordDtlVO,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveRecordFoundNArchivalDetail(mrdFoundVO,mrdRecordDtlVO, userVO);
	}
	
	public void deleteRejectedRecordDetail(String requestId,UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.deleteRejectedRecordDetail(requestId, userVO);
	}

	public List getDepartmentUnitListForEMR(UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getDepartmentUnitListForEMR(userVO);
	}

	public Map getListOfOpdFilesToMove(String _mrdCode, UserVO _userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getListOfOpdFilesToMove(_mrdCode, _userVO);
	}

	public void saveOPDFileMovementDetail(MrdRecordDtlVO[] _mrdRecordVO,MrdRecordMovementVO[] _movementVO,UserVO _userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveOPDFileMovementDetail(_mrdRecordVO,_movementVO, _userVO);
	}

	public Map getListOfOpdFilesToReturn(String mrdCode,UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getListOfOpdFilesToReturn(mrdCode, userVO);
	}

	public Map getPatientVisitSummaryForEMR(EpisodeVO episodeVO,
			String[] departmentUnitArray, String accessType, UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientVisitSummaryForEMR(episodeVO,departmentUnitArray, accessType,userVO);
	}

	public List getPatientCRNoMergeList(CrNoMergeDtlVO voCRMerge, String[] departmentUnitArray, String accessType, UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getPatientCRNoMergeList(voCRMerge, departmentUnitArray, accessType, userVO);
	}

	public void saveExtendDays(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.saveExtendDays(mrdRecordRequestVO, userVO);
		
	}

	public MrdRecordRequestDtlVO getEssentials(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return(serviceBO.getEssentials(mrdRecordRequestVO, userVO));
		
	}

	public void updateSL_NO(MrdRecordRequestDtlVO mrdRecordRequestVO,
			UserVO userVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.updateSL_NO(mrdRecordRequestVO, userVO);
		
	}
	
	public void savePatientMRDDocumentdetails(MRDDocumentUploadVO[] docUploadVos,MRDDocumentUploadVO[] documentUploadRevokeDtlVO, UserVO userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		serviceBO.savePatientMRDDocumentdetails(docUploadVos,documentUploadRevokeDtlVO, userVO);
	}
	
	
	//Added by Dheeraj
	public byte[] fetchImageFromPostgres(MRDDocumentUploadVO mrdDocumentUploadVO) {
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		byte[] getdoc = serviceBO.fetchImageFromPostgres(mrdDocumentUploadVO); 
		return getdoc;
	}

	//Added By Shweta
	public List<DocumentUploadDtlVO> getDocumentArchivalEssentials(String patCrNo,String episodeCode, UserVO _userVO)
	{
		MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
		return serviceBO.getDocumentArchivalEssentials(patCrNo, episodeCode, _userVO);
	}
	
	// Added By Shweta for fetching External Treatment Detail on 15-May-2019
		public PatDrugTreatmentDetailVO[] getPateintExtTreatmentDetails(PatDrugTreatmentDetailVO _patDrugTreatmentDetailVO,String[] departmentUnitArray, String accessType, UserVO _userVO)
		{
			MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
			return serviceBO.getPateintExtTreatmentDetails(_patDrugTreatmentDetailVO,departmentUnitArray,accessType, _userVO);
		}
	
		public RecordDispatchDtlVO[] getRecordListByPatAdmNo(
				RecordDispatchDtlVO recordDispatchVO, UserVO userVO) {
			MrdBOi serviceBO = (MrdBOi) super.getServiceProvider();
			return serviceBO.getRecordListByPatAdmNo(recordDispatchVO,userVO);
		}
		
		

}
