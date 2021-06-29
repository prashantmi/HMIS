/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: 
	 ## Module Name					: MRD
	 ## Process/Database Object Name:Estimate Certificate issue after Request
	 ## Purpose						:Certificate Issue Process
	 ## Date of Creation			: 
	 ## Modification Log			:				
     ## Modify Date				    :22-Nov-2014
     ##	Reason	(CR/PRS)			: Certificate Issue Process functionality implementation
 	 ## Modify By				    :Amit Garg 	
 	 */


package mrd.transaction.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.BirthDeathUploadDtlVO;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.DischargePrintingVO;
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
import hisglobal.vo.PatientVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RecordDispatchDtlVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.RequestRecordDtlVO;
import hisglobal.vo.StaffDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.bo.OpdEssentialBOi;
import mrd.transaction.bo.MrdEssentialBO;
import mrd.transaction.bo.MrdEssentialBOi;
import mrd.transaction.controller.fb.MrdMedicalCampFB;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import mrd.vo.EstimateCertificateIssueVO;
import mrd.vo.MRDDocumentUploadVO;
import mrd.vo.PatientEmrAuditVO;

public class MrdEssentialDelegate extends Delegate 
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public MrdEssentialDelegate() 
	{
		super(new MrdEssentialBO()); // /<<Setting the service provider
	}
	
	/**  Getting the List of Diagnosis Given to the Patient on a Particular Episode 
	 * @param diagCodeType
	 * @param epiRestAdviceVO
	 * @param userVO
	 * @return
	 */
	public List getPatDiagnosisList(String diagCodeType,EpisodeRestAdviceVO epiRestAdviceVO,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getPatDiagnosisList(diagCodeType,epiRestAdviceVO,userVO);
		
	}
	
	/** Getting the List of All the Consultant Name
	 * @param userVO
	 * @return
	 */
	public List getAllConsultantForMC(String unitCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllConsultantForMC(unitCode,userVO);
	}
	
	/** Getting the List of Generated Medical Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] getAllGeneratedMCList(String crNo,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllGeneratedMCList(crNo,userVO);	
	}
	
	/** Getting the List of Generated Fitness Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatFitnessDtlVO[] getAllGeneratedFCList(String crNo,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllGeneratedFCList(crNo,userVO);	
	}
	
	/** Getting the List of Duplicate Medical Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatMedicalDtlVO[] getAllDuplicateMCList(String crNo,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllDuplicateMCList(crNo,userVO);	
	}
	
	/** Getting the List of Duplicate Fitness Certificate
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatFitnessDtlVO[] getAllDuplicateFCList(String crNo,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllDuplicateFCList(crNo,userVO);	
	}

	
	/**  Getting the List Of Certificate, Rack & Shelf List
	 * @param certificateRcvMode
	 * @param _certificateIssueDtlVO
	 * @param userVO
	 * @return
	 */
	public Map getEssentialForCertificateReceived(String certificateRcvMode,CertificateIssueDtlVO _certificateIssueDtlVO,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForCertificateReceived(certificateRcvMode,_certificateIssueDtlVO,userVO);	
	}
	
	/** Getting the List Of Shelf On Basis of Rack
	 * @param _certificateIssueDtlVO
	 * @param userVO
	 * @return
	 */
	public List getSelf(CertificateIssueDtlVO _certificateIssueDtlVO,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getSelf(_certificateIssueDtlVO,userVO);	
	}

	
	public List getAllUnit(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllUnit(userVO);
	}
	
	public String getEmpMaxDaysOnline(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEmpMaxDaysOnline(userVO);
	}
	
	public List getPhysicianType(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getPhysicianType(userVO);
	}
	
	public List getDeptUnitList(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDeptUnitList(userVO);
	}

	
	public List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getWardOnBasisOfUnitCode(unitCode,userVO);
	}
	

	
	public PatientVO getNotUsedCrNo(String crNo,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getNotUsedCrNo(crNo,userVO);
	}
	
	public PatientVO[] searchPatient(HashMap searchMap,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchPatient(searchMap,userVO);
	}
	
	public Map getEssentialForOnlineCRNoMerge(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForOnlineCRNoMerge(userVO);
	}

	public Map getEssentialForCaseSheetAcceptence(UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForCaseSheetAcceptence(userVO);
	}

	public Map getAllEnclosureChecklistsByRecordId(String recordId,
			UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllEnclosureChecklistsByRecordId(recordId,userVO);
	}

	public void saveCaseSheetAcceptence(
			CaseSheetDispatchVO caseSheetDispatchVO,
			String isAccept, RecordTypeWiseEnclosureMstVO[] recordTypeEnclosureVOArray, MrdRecordDtlVO mrdRecordDtlVO, RecordTypeCheckListMstVO[] recChecklistDetailVO, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		serviceBO.saveCaseSheetAcceptence(caseSheetDispatchVO,isAccept,recordTypeEnclosureVOArray,mrdRecordDtlVO,recChecklistDetailVO,userVO);
	}

	public List<DischargePrintingVO> getDischargePatientList(String deptUnitCode, String wardCode, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDisPatientListForPrinting(deptUnitCode,wardCode,userVO);
	}

	public List<DischargePrintingVO> getDischargePatientList(String patCrNo,
			UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getDisPatientListForPrintingByCrNo(patCrNo,userVO);
	}

	public void saveDischargePrinting(DischargePrintingVO dischargePrintingVO,
			UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		serviceBO.saveDischargePrinting(dischargePrintingVO,userVO);
		
	}
	

	public ANCNeonatalDetailVO[] getListOfBirth(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getListOfBirth(userVO);
	}
	
	public List getRelationList(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRelationList(userVO);
	}
	
	public BirthDeathUploadDtlVO getBirthDeathUploadDtl(String recordType,String crNo,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getBirthDeathUploadDtl(recordType, crNo, userVO);
	}
	
	public Map getMotherNChildDetail(String motherCrNo, String childCrNo,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMotherNChildDetail(motherCrNo, childCrNo, userVO);
	}
	
	public PatientVO getBirthSlipDetail(String crNo,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getBirthSlipDetail(crNo, userVO);
	}
	
	public PatientVO[] getListOfDeath(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getListOfDeath(userVO);
	}
	
	public ANCNeonatalDetailVO[] searchForBirthRegUpload(PatientVO searchFindVO,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchForBirthRegUpload(searchFindVO, userVO);
	}

	public Map getEssentialForSummonDtl(String cidNoFlag,UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForSummonDtl(cidNoFlag,userVO);
	}
	
	public Map getEssenForSummonAssignDtl(UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssenForSummonAssignDtl(userVO);
	}
	
	public List searchEmpDetail(String _fName,String _mName,String _lName,UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchEmpDetail(_fName,_mName,_lName,userVO);
	}
	
	public Map getEssenForPostSummonDtl(UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssenForPostSummonDtl(userVO);
	}
	
	public Map getEssenForSummonAssignChange(UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssenForSummonAssignChange(userVO);
	}

	
	public Map searchSummonDetail(String SearchCriteria,String fromDate,String toDate,String empId,String summonTypeId,UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchSummonDetail(SearchCriteria, fromDate, toDate,empId,summonTypeId ,userVO);
	}
	
	public Map getEssentialForSummonTracking(UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForSummonTracking(userVO);
	}
	
	public Map searchPatientDtl(String searchType,String _str,UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchPatientDtl(searchType,_str,userVO);
	}
	

	public ANCNeonatalDetailVO[] searchForBirthRegUploadByMother(PatientVO searchFindVO,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchForBirthRegUploadByMother(searchFindVO, userVO);
	}
	
	public Map getEssenForInsuranceClaimReceive(UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssenForInsuranceClaimReceive(userVO);
	}
	public List getPatInfoList(String patCrNo,String patAdmNo, String _firstName,String _middleName,String _lastName,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getPatInfoList(patCrNo, patAdmNo, _firstName, _middleName, _lastName, userVO);
	}
	
	public List getPatDeathUploadList(String patCrNo,String deathDate, String _firstName,String _middleName,String _lastName,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getPatDeathUploadList(patCrNo, deathDate, _firstName, _middleName, _lastName, userVO);
	}
	
	public RecordTypeCheckListMstVO[] getCheckListForMedNFitCertificate(String recordType,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getCheckListForMedNFitCertificate(recordType,userVO);
	}
	
	public List getRecordType(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRecordType(userVO);
	}
	
	public RecordDispatchDtlVO[] getRecordListToAcceptByRecordType(String recordType,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRecordListToAcceptByRecordType(recordType,userVO);
	}

	
	public Map getEssentialForSummonInbox(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForSummonInbox(userVO);
	}

	
	public Map getEssentialForAcceptRecordInMrd(String recordType,String mrdCode,String searchDate,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForAcceptRecordInMrd(recordType,mrdCode,searchDate,userVO);
	}
	
	public RackMstVO[] getRackBasedOnMrd(String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRackBasedOnMrd(recordType,mrdCode,userVO);
	}
	
	public List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getShelfBasedOnRack(recordType,rackId,userVO);
	}
	
	public List getMrdList(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMrdList(userVO);
	}
	
	public Map getEssentialForRecordArchived(String recordType,String mrdCode,UserVO userVO,String admNo)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForRecordArchived(recordType,mrdCode,userVO,admNo);
	}
	
	
	//added by swati sagar on date:14-may-2019
	public Map getRackDtlForCrAdmNo(String recordType,String mrdCode,UserVO userVO,String admNo)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRackDtlForCrAdmNo(recordType,mrdCode,userVO,admNo);
	}
	
	
	//added by swati sagar 
	//date:13-may-2019
	public Map getEssentialForRecordArchived_AdmNo(String recordType,String mrdCode,UserVO userVO,String admNo)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForRecordArchived_AdmNo(recordType,mrdCode,userVO,admNo);
	}
	
	
	//added by swati sagar 
	//date:13-may-2019
	public Map getEssentialForRecordArchived_CrNo(String recordType,String mrdCode,UserVO userVO,String admNo,String crno)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForRecordArchived_CrNo(recordType,mrdCode,userVO,admNo, crno);
	}
	
	
	public Map getEssentialForLostFoundDetail(String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForLostFoundDetail(recordType,mrdCode,userVO);
	}
	
	public List<RecordTypeWiseEnclosureMstVO> getEnclosureDetail(String dispatchId,String recordType,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEnclosureDetail(dispatchId, recordType,userVO);
	}
	
	public List getRecordTypeBasedOnMrd(String mrdCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRecordTypeBasedOnMrd(mrdCode,userVO);
	}
	
	public Map getCheckListDetail(String dispatchId,String recordType,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getCheckListDetail(dispatchId,recordType,userVO);
	}
	
	public Map getEssentialForCertificateReceived(String recordType,String unitCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForCertificateReceived(recordType,unitCode,userVO);
	}
	
	public Map getEssentialForOnlineReq(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForOnlineReq(userVO);
	}
	//added by swati sagar on date:20-02-2019
	public Map getEssentialForIcdIndexing(UserVO userVO,PatientDetailVO patdtlVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForIcdIndexing(userVO, patdtlVO);
	}
	
	public List getListDischargeType(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getListDischargeType(userVO);
	}
	
	public String getIcdDtls(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getIcdDtls(userVO);
	}
	public CommonCaseSheetEnquiryVO[] searchRecord(CaseSheetEnquiryVO caseSheetEnqVO,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchRecord(caseSheetEnqVO,userVO);
	}


	public List getRequestListForApproval(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRequestListForApproval(mrdRecordRequestDtlVO,userVO);
	}

	public List getRequestDetail(RequestRecordDtlVO requestRecordDtlVO,
			UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRequestDetail(requestRecordDtlVO,userVO);
	}

	public void saveApprovalDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,List<RequestRecordDtlVO> requestRecordDtlVOList, UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		serviceBO.saveApprovalDetail(mrdRecordRequestDtlVO,requestRecordDtlVOList,userVO);
	}
	
	//public Map getEssenForInsuranceClaimRecordEntry(UserVO userVO)
	//{
		//MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		//return serviceBO.getEssenForInsuranceClaimRecordEntry(userVO);
	//}

	public List getRequestListForIssue(MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRequestListForIssue(mrdRecordRequestDtlVO,userVO);
	}

	public void saveIssueDetail(MrdRecordRequestDtlVO mrdRecordRequestDtlVO,List<RequestRecordDtlVO> requestRecordDtlVOList, List<MrdRecordDtlVO> mrdRecordDtlVOList, List<MrdRecordIssueDtlVO> mrdRecordIssueDtlVOList, UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		serviceBO.saveIssueDetail(mrdRecordRequestDtlVO,requestRecordDtlVOList,mrdRecordDtlVOList,mrdRecordIssueDtlVOList,userVO);
	}

	public Map getMrdRecordStatus(String mrdRecordId,String requestId,UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMrdRecordStatus(mrdRecordId,requestId,userVO);
	}

	public List getMrdBasedOnRecordType(String recordType, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMrdBasedOnRecordType(recordType,userVO);
	}
	
	public List<MrdRecordRequestDtlVO> getRequestListForReturn(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRequestListForReturn(userVO);
	}
	
	public List<MrdRecordIssueDtlVO> getReturnedMrdRecordListByRequestId(String requestId,String recordId,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getReturnedMrdRecordListByRequestId(requestId,recordId, userVO);
	}
	
	public List<RequestRecordDtlVO> getPendingRecordRequestStatus(String requestId,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getPendingRecordRequestStatus(requestId,userVO);
	}
	
	public Map getMrdNPurposeBasedOnRecordType(String recordType, UserVO userVO) 
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getMrdNPurposeBasedOnRecordType(recordType,userVO);
	}

	public Map getReturnEssentialForRecordArchived(String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getReturnEssentialForRecordArchived(recordType,mrdCode,userVO);
	}
	
	public MrdRecordDtlVO[] searchLostRecord(CaseSheetEnquiryVO caseSheetEnqVO,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchLostRecord(caseSheetEnqVO,userVO);
	}
	
	public List getLostRecordReportedByList(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getLostRecordReportedByList(userVO);
	}
	
	public Map getEssentialForRecordMovement(String recordType,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssentialForRecordMovement(recordType,userVO);
	}
	
	public MrdRecordLostFoundDtlVO[] getLostRecordInMrdList(UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getLostRecordInMrdList(userVO);
	}
	
	public Map getFoundEssentialDtl(String selRecordId,String recordType,String mrdCode,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getFoundEssentialDtl(selRecordId,recordType,mrdCode,userVO);
	}
	
	public List getRequestedRecordId(String empId,UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRequestedRecordId(empId,userVO);
	}

	public Map getEssenForInsuranceEnquiry(UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getEssenForInsuranceEnquiry(userVO);
	}

	public List searchInsuranceDtl(String patFirstName, String patMiddleName,
			String patLastName, String companyId, String patCrNo,
			String policyNo, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.searchInsuranceDtl(patFirstName,patMiddleName,patLastName,companyId,patCrNo,policyNo,userVO);
	}

	public List<DischargePrintingVO> getRecordForDupDischargePrinting(
			String patCrNo, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRecordForDupDischargePrinting(patCrNo,userVO);
	}

	public List getPatientProfileByCrNo(String patCrNo, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getPatientProfileByCrNo(patCrNo,userVO);
		
	}

	public void saveCDBurnDetail(
			PatientProfileCDBurnDtlVO[] patientProfileCDBurnDtlVO, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		serviceBO.saveCDBurnDetail(patientProfileCDBurnDtlVO,userVO);
	}
	
	public Map getEmployeePopUpEssentials(UserVO _uservo) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi)super.getServiceProvider();
		return serviceBO.getEmployeePopUpEssentials(_uservo);
	}
	
	public StaffDetailVO[] searchStaffDetail(StaffDetailVO staffEnquiryVO,UserVO _userVO){
		MrdEssentialBOi serviceBO=(MrdEssentialBOi)super.getServiceProvider();
		return serviceBO.searchStaffDetail(staffEnquiryVO,_userVO);
	}

	public List getAuditTypeWiseList(UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAuditTypeWiseList(userVO);
	}

	public List<PatientEmrAuditVO> getAuditUserList(String strMode_p,
			PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAuditUserList(strMode_p,patientEmrAuditVO_p,userVO_p);
		
	}

	public List<PatientEmrAuditVO> getPatientEmrAuditDtlByCrNo(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
			MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
			return (List<PatientEmrAuditVO>)serviceBO.getPatientEmrAuditDtlByCrNo(patientEmrAuditVO_p,userVO_p);
	}

	public List<List<String>> showPatientEmrAuditDiagnosisDialTileByPrimaryKey(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return (List<List<String>>)serviceBO.showPatientEmrAuditDiagnosisDialTileByPrimaryKey(patientEmrAuditVO_p,userVO_p);
	}

	public Boolean savePatientEmrAuditDtl(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return (Boolean)serviceBO.savePatientEmrAuditDtl(patientEmrAuditVO_p,userVO_p);
	}

	public List<PatientEmrAuditVO> getPreviousPatientEmrAuditDtlByPrimaryKey(PatientEmrAuditVO patientEmrAuditVO_p, UserVO userVO_p) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return (List<PatientEmrAuditVO>)serviceBO.getPreviousPatientEmrAuditDtlByPrimaryKey(patientEmrAuditVO_p,userVO_p);
	}

	
	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,
													 String _deptUnitCode, UserVO _userVO){
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getWardBasedOnHospitalDepartmentUnit(_hosCode, _deptCode, _deptUnitCode, _userVO));
	}
	public List getRoomBasedOnHospitalDeptUnitWard(String _hosCode,String _deptUnitCode,String _wardCode){
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getRoomBasedOnHospitalDeptUnitWard(_hosCode, _deptUnitCode, _wardCode));
	}

	public List getLaboratoryandTimeMap(UserVO _userVO, String departmentCode) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getLaboratoryandTimeMap(_userVO,departmentCode);
	}

	
	public Map getRegistrationCensusReportEssentials(UserVO _userVO) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRegistrationCensusReportEssentials(_userVO);
	}
	public Map getOpdStaticReportEssentials(UserVO _userVO) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getOpdStaticReportEssentials(_userVO);
	}
	public void getdeptComboDetails(ReportVO _rptVO) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		serviceBO.getdeptComboDetails(_rptVO);
	}
	public Map getRegistrationPatientListingReportEssentials(UserVO _userVO) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getRegistrationPatientListingReportEssentials(_userVO);
	}

	public Map getAllConsultant(UserVO _userVO) {
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getAllConsultant(_userVO);
	}

	/**
	 * Getting All MEdical Camp List
	 * @param _uservo
	 * @return
	 * Created By Akash
	 */
	public List getCampListForMedicalCamp(MrdMedicalCampDtlVO mrdMedicalCampDtlVO, UserVO userVO) 
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getCampListForMedicalCamp(mrdMedicalCampDtlVO,userVO);
	}

	public List getCampEmpNameForMedicalCamp(UserVO userVO) 
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getCampEmpNameForMedicalCamp(userVO);
	}


	public void saveCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, MrdMedicalCampTeamDtlVO[] medicalCampTeamDtlVO, UserVO userVO)
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();		
		serviceBO.saveCampDetail(medicalCampDtlVO,medicalCampTeamDtlVO,userVO);
	}

	public void getCampDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO) 
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();		
		serviceBO.getCampDetail(medicalCampDtlVO,userVO);
	}

	public List<MrdMedicalCampTeamDtlVO> getCampEmpDetail(MrdMedicalCampDtlVO medicalCampDtlVO, UserVO userVO) 
	{
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.getCampEmpDetail(medicalCampDtlVO, userVO);
	}
	

	public Map getEstimateCertificateReqDtl(UserVO userVO) 
	{
		MrdEssentialBO serviceBO=(MrdEssentialBO) super.getServiceProvider();	
		return (serviceBO.getEstimateCertificateReqDtl(userVO));
	}
	public Map getEstimateCertificateIssueDtl(EstimateCertificateIssueVO estimateCertificateIssueVO,UserVO userVO) 
	{
		MrdEssentialBO serviceBO=(MrdEssentialBO) super.getServiceProvider();	
		return (serviceBO.getEstimateCertificateIssueDtl(estimateCertificateIssueVO,userVO));
	}
	public void saveEstimateCertificateIssueDtl(EstimateCertificateIssueVO estimateCertificateIssueVO,UserVO userVO)
	{
		MrdEssentialBO serviceBO=(MrdEssentialBO) super.getServiceProvider();
		serviceBO.saveEstimateCertificateIssueDtl(estimateCertificateIssueVO,userVO);
	}
	
	public Map generateEstimateCertificate(EstimateCertificateIssueVO estimateCertificateIssueVO,UserVO userVO) 
	{
		MrdEssentialBO serviceBO=(MrdEssentialBO) super.getServiceProvider();	
		return (serviceBO.generateEstimateCertificate(estimateCertificateIssueVO,userVO));
	}

	public MrdRecordRequestDtlVO checkUserIsEmp(
			MrdRecordRequestDtlVO mrdRecordRequestDtlVO, UserVO userVO) {
		MrdEssentialBOi serviceBO=(MrdEssentialBOi) super.getServiceProvider();
		return serviceBO.checkUserIsEmp(mrdRecordRequestDtlVO,userVO);
	}
	
	public List<MRDDocumentUploadVO>  getMRDDocumentEssentials(String patCrNo,String episodeCode, UserVO _userVO)
	{
		MrdEssentialBOi serviceBO = (MrdEssentialBOi) super.getServiceProvider();
		return (serviceBO.getMRDDocumentEssentials(patCrNo,episodeCode, _userVO));
	}

	
}

