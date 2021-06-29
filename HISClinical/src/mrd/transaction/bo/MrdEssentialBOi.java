package mrd.transaction.bo;

import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.DischargePrintingVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.MrdMedicalCampDtlVO;
import hisglobal.vo.MrdMedicalCampTeamDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.MrdRecordIssueDtlVO;
import hisglobal.vo.MrdRecordLostFoundDtlVO;
import hisglobal.vo.MrdRecordRequestDtlVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileCDBurnDtlVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.StaffDetailVO;


import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mrd.transaction.controller.fb.MrdMedicalCampFB;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import mrd.vo.MRDDocumentUploadVO;
import mrd.vo.PatientEmrAuditVO;

public interface MrdEssentialBOi 
{
	/**  Getting the List of Diagnosis Given to the Patient on a Particular Episode 
	 * @param diagCodeType
	 * @param epiRestAdviceVO
	 * @param userVO
	 * @return
	 */
	public List getPatDiagnosisList(String diagCodeType,EpisodeRestAdviceVO epiRestAdviceVO,UserVO userVO);
	
	/** Getting the List of All the Consultant Name
	 * @param userVO
	 * @return
	 */
	public List getAllConsultantForMC(String unitCode,UserVO userVO);
	
	/** Getting the List of Generated Medical Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] getAllGeneratedMCList(String crNo,UserVO userVO);
	
	/** Getting the List of Generated Fitness Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatFitnessDtlVO[] getAllGeneratedFCList(String crNo,UserVO userVO);
	
	/** Getting the List of Duplicate Medical Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] getAllDuplicateMCList(String crNo,UserVO userVO);
	
	/** Getting the List of Duplicate Fitness Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatFitnessDtlVO[] getAllDuplicateFCList(String crNo,UserVO userVO);
	

	/** Getting the List Of Certificate, Rack & Shelf List
	 * @param certificateRcvMode
	 * @param _certificateIssueDtlVO
	 * @param userVO
	 * @return
	 */
	public Map getEssentialForCertificateReceived(String certificateRcvMode,CertificateIssueDtlVO _certificateIssueDtlVO,UserVO userVO);

	public List getAllUnit(UserVO userVO);

	
	public List getSelf(CertificateIssueDtlVO _certificateIssueDtlVO,UserVO userVO);
	
	public String getEmpMaxDaysOnline(UserVO userVO);

	
	public List getDeptUnitList(UserVO userVO);
	
	public List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO);
	
	

	
	public List getPhysicianType(UserVO userVO);
	
	public PatientVO getNotUsedCrNo(String crNo,UserVO userVO);
	
	public PatientVO[] searchPatient(HashMap searchMap,UserVO userVO);
	
	public Map getEssentialForOnlineCRNoMerge(UserVO userVO);
	
	/* ************************************Case Sheet Acceptence***************************************/
	
	public Map getEssentialForCaseSheetAcceptence(UserVO userVO);
	
	public Map getAllEnclosureChecklistsByRecordId(String recordId,UserVO userVO);

	public void saveCaseSheetAcceptence(
			CaseSheetDispatchVO caseSheetDispatchVO,
			String isAccept, RecordTypeWiseEnclosureMstVO[] recordTypeEnclosureVOArray, MrdRecordDtlVO mrdRecordDtlVO, RecordTypeCheckListMstVO[] recChecklistDetailVO, UserVO userVO);
	
	//***************************************discharge Printing *************************************/
	
	public List<DischargePrintingVO> getDisPatientListForPrinting(String unitCode,String wardCode,UserVO userVO);
	
	public List<DischargePrintingVO> getDisPatientListForPrintingByCrNo(String patCrnNo,UserVO userVO);
	
	public void saveDischargePrinting(DischargePrintingVO dischargePrintingVO,UserVO userVO);
	

	
	public ANCNeonatalDetailVO[] getListOfBirth(UserVO userVO);
	
	public List getRelationList(UserVO userVO);
	
	public BirthDeathUploadDtlVO getBirthDeathUploadDtl(String recordType,String crNo,UserVO userVO);
	
	public Map getMotherNChildDetail(String motherCrNo, String childCrNo,UserVO userVO);
	
	public PatientVO getBirthSlipDetail(String crNo,UserVO userVO);
	
	public PatientVO[] getListOfDeath(UserVO userVO);
	
	public ANCNeonatalDetailVO[] searchForBirthRegUpload(PatientVO searchFindVO,UserVO userVO);
	
	public ANCNeonatalDetailVO[] searchForBirthRegUploadByMother(PatientVO searchFindVO,UserVO userVO);
	

	/********************************summonDetail*******************************/
	
	public Map getEssentialForSummonDtl(String cidNoFlag,UserVO userVO);
	
	public Map getEssenForSummonAssignDtl(UserVO userVO);
	
	public List searchEmpDetail(String  _fName,String _mName,String _lName,UserVO userVO);
	
	public Map getEssenForPostSummonDtl(UserVO userVO);
	
	public Map getEssenForSummonAssignChange(UserVO userVO);
	
	public Map getEssentialForSummonTracking(UserVO userVO);
	
	public Map searchSummonDetail(String SearchCriteria,String fromDate,String toDate,String empId,String dummonTypeId,UserVO userVO);
	
	public Map searchPatientDtl(String searchType,String _str,UserVO userVO) ;
	
	/**************************insurance claim receive***************************/
	
	public Map getEssenForInsuranceClaimReceive(UserVO userVO);
	
	public List getPatInfoList(String patCrNo,String patAdmNo, String _firstName,String _middleName,String _lastName,UserVO userVO);
	
	public List getPatDeathUploadList(String patCrNo,String deathDate, String _firstName,String _middleName,String _lastName,UserVO userVO);
	
	
	public RecordTypeCheckListMstVO[] getCheckListForMedNFitCertificate(String recordType,UserVO userVO);
	
	public List getRecordType(UserVO userVO);
	
	public RecordDispatchDtlVO[] getRecordListToAcceptByRecordType(String recordType,UserVO userVO);

	public Map getEssentialForSummonInbox(UserVO userVO);

	
	public Map getEssentialForAcceptRecordInMrd(String recordType,String mrdCode,String searchDate,UserVO userVO);
	
	public RackMstVO[] getRackBasedOnMrd(String recordType,String mrdCode,UserVO userVO);
	
	public List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO);
	
	public List getMrdList(UserVO userVO);
	
	public Map getEssentialForRecordArchived(String recordType,String mrdCode,UserVO userVO,String admNo);
	
	public Map getRackDtlForCrAdmNo(String recordType,String mrdCode,UserVO userVO,String admNo);//added by swati sagar on date:`14-may-2019

	
	public Map getEssentialForRecordArchived_AdmNo(String recordType,String mrdCode,UserVO userVO,String admNo);//added by swati sagar on date:`13-may-2019
	
	public Map getEssentialForRecordArchived_CrNo(String recordType,String mrdCode,UserVO userVO,String admNo,String crno);//added by swati sagar on date:`13-may-2019

	
	public Map getEssentialForLostFoundDetail(String recordType,String mrdCode,UserVO userVO);
	
	public List<RecordTypeWiseEnclosureMstVO> getEnclosureDetail(String dispatchId,String recordType,UserVO userVO);
	
	public List getRecordTypeBasedOnMrd(String mrdCode,UserVO userVO);
	
	public Map getCheckListDetail(String dispatchId,String recordType,UserVO userVO);
	
	public Map getEssentialForCertificateReceived(String recordType,String unitCode,UserVO userVO);
	
	public Map getEssentialForOnlineReq(UserVO userVO);
	
	public Map getEssentialForIcdIndexing(UserVO userVO,PatientDetailVO patdtlVO);//added by swati sagar on date:20-02-2019
	
	public List getListDischargeType(UserVO userVO);
	public String getIcdDtls(UserVO userVO);
	
	public CommonCaseSheetEnquiryVO[] searchRecord(CaseSheetEnquiryVO caseSheetEnqVO,UserVO userVO);

	public List getRequestListForApproval(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);

	public List getRequestDetail(RequestRecordDtlVO requestRecordDtlVO,UserVO userVO);

	public void saveApprovalDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,List<RequestRecordDtlVO> requestRecordDtlVOList, UserVO userVO);
	
	//public Map getEssenForInsuranceClaimRecordEntry(UserVO userVO);

	public List getRequestListForIssue(	MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);

	public void saveIssueDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,List<RequestRecordDtlVO> requestRecordDtlVOList, List<MrdRecordDtlVO> mrdRecordDtlVOList, List<MrdRecordIssueDtlVO> mrdRecordIssueDtlVOList, UserVO userVO);

	public Map getMrdRecordStatus(String mrdRecordId,String requestId,UserVO userVO);

	public List getMrdBasedOnRecordType(String recordType, UserVO userVO);
	
	public List<MrdRecordRequestDtlVO> getRequestListForReturn(UserVO userVO);
	
	public List<MrdRecordIssueDtlVO> getReturnedMrdRecordListByRequestId(String requestId,String recordId,UserVO userVO);
	
	public List<RequestRecordDtlVO> getPendingRecordRequestStatus(String requestId,UserVO userVO);

	public Map getMrdNPurposeBasedOnRecordType(String recordType, UserVO userVO); 

	public Map getReturnEssentialForRecordArchived(String recordType,String mrdCode,UserVO userVO);
	
	public MrdRecordDtlVO[] searchLostRecord(CaseSheetEnquiryVO caseSheetEnqVO,UserVO userVO);
	
	public List getLostRecordReportedByList(UserVO userVO);
	
	public Map getEssentialForRecordMovement(String recordType,UserVO userVO);
	
	public MrdRecordLostFoundDtlVO[] getLostRecordInMrdList(UserVO userVO);
	
	public Map getFoundEssentialDtl(String selRecordId,String recordType,String mrdCode,UserVO userVO);

	public List<DischargePrintingVO> getRecordForDupDischargePrinting(String patCrNo, UserVO userVO);

	public Map getEssenForInsuranceEnquiry(UserVO userVO);

	public List searchInsuranceDtl(String patFirstName, String patMiddleName,
			String patLastName, String companyId, String patCrNo,
			String policyNo, UserVO userVO);
	
	public List getRequestedRecordId(String empId,UserVO userVO);
	
	public List getPatientProfileByCrNo(String patCrnNo,UserVO userVO);

	public void saveCDBurnDetail(
			PatientProfileCDBurnDtlVO[] patientProfileCDBurnDtlVO, UserVO userVO);
	
	public Map getEmployeePopUpEssentials(UserVO _uservo);
	
	public StaffDetailVO[] searchStaffDetail(StaffDetailVO staffEnquiryVO,UserVO _userVO);

	public List getAuditTypeWiseList(UserVO userVO);

	public List<PatientEmrAuditVO> getAuditUserList(String strMode_p,
			PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p);

	public List<PatientEmrAuditVO> getPatientEmrAuditDtlByCrNo(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p);

	public List<List<String>> showPatientEmrAuditDiagnosisDialTileByPrimaryKey(
			PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p);

	public boolean savePatientEmrAuditDtl(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p);

	public List<PatientEmrAuditVO> getPreviousPatientEmrAuditDtlByPrimaryKey(
			PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p);
	
	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode, UserVO _userVO);
	public List getRoomBasedOnHospitalDeptUnitWard(String _hosCode,String _deptUnitCode,String _wardCode);
	
	/**
	 * Getting Laboratory List
	 * @param _uservo
	 * @param departmentCode
	 * @return
	 * Created By Adil
	 */
	public List getLaboratoryandTimeMap(UserVO _uservo, String departmentCode);

	
	public Map getRegistrationCensusReportEssentials(UserVO _userVO);
	
	public Map getRegistrationPatientListingReportEssentials(UserVO _userVO);
	
	public Map getOpdStaticReportEssentials(UserVO _userVO);
	
	public void getdeptComboDetails(ReportVO _rptVO);

	/**
	 * Getting All Consultant List
	 * @param _uservo
	 * @return
	 * Created By Adil
	 */
	public Map getAllConsultant(UserVO _userVO);
	
	/**
	 * Getting All MEdical Camp List
	 * @param _uservo
	 * @return
	 * Created By Akash
	 */
	public List getCampListForMedicalCamp(	MrdMedicalCampDtlVO mrdMedicalCampDtlVO, UserVO userVO);

	public List getCampEmpNameForMedicalCamp(UserVO userVO);
	
	public void saveCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, MrdMedicalCampTeamDtlVO[] medicalCampTeamDtlVO, UserVO userVO);

	public void getCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO);

	public List<MrdMedicalCampTeamDtlVO> getCampEmpDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO);

	public MrdRecordRequestDtlVO checkUserIsEmp(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO);
	
	
	public List<MRDDocumentUploadVO> getMRDDocumentEssentials(String patCrNo,String episodeCode, UserVO _userVO);
}
