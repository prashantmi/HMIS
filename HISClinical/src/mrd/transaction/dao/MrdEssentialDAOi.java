package mrd.transaction.dao;

import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.DischargePrintingVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.PatAdmissionDtlVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.StaffDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.List;

public interface MrdEssentialDAOi 
{
	
	/**  Getting the List of Diagnosis Given to the Patient on a Particular Episode 
	 * @param diagCodeType
	 * @param epiRestAdviceVO
	 * @param userVO
	 * @return
	 */
	public List getPatDiagnosisListICD(String diagCodeType,EpisodeRestAdviceVO epiRestAdviceVO,UserVO userVO);
	
	public List getPatDiagnosisListHospital(String diagCodeType,EpisodeRestAdviceVO epiRestAdviceVO,UserVO userVO);
	
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
	
	public List getAllUnit(UserVO userVO);
	
	/** Getting The List of Issue Certificate
	 * @param _certificateIssueDtlVO
	 * @param userVO
	 * @return
	 */
	public CertificateIssueDtlVO[] getAllCertificateIssueDtlVOList(CertificateIssueDtlVO _certificateIssueDtlVO,UserVO userVO);
	
	/**  Getting The List of All Rack 
	 * @param userVO
	 * @return
	 */
	public RackMstVO[] getAllRack(UserVO userVO);
	
	/**  Getting The List Of All Shelf
	 * @param userVO
	 * @return
	 */
	public RackShelfMstVO[] getAllRackshelf(UserVO userVO);
	
	/** Getting Patient Info For Medical Certificate
	 * @param mrdRecordDtlVO
	 * @param _userVO
	 * @return
	 */
	public PatMedicalDtlVO getPUKForMedicalCert(MrdRecordDtlVO mrdRecordDtlVO,UserVO _userVO);
	
	/** Getting Patient Info For Fitness Certificate
	 * @param mrdRecordDtlVO
	 * @param _userVO
	 * @return
	 */
	public PatFitnessDtlVO getPUKForFitnessCert(MrdRecordDtlVO mrdRecordDtlVO,UserVO _userVO);
	
	/** Getting The List of Certificate From Movement Table(In Case of ONLINE)
	 * @param userVO
	 * @return
	 */
	public CertificateIssueDtlVO[] getAllMovementCertificateVO(CertificateIssueDtlVO _CertificateIssueDtlVO,UserVO userVO);
	
	public void updateCertificateStatus(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO);
	
	public String getEmpMaxDaysOnline(UserVO userVO);

	
	public List getPhysicianType(UserVO userVO);
	

	public List getDeptUnitList(UserVO userVO);
	
	public List getAllDeptHavingUnits(UserVO userVO);
	
	

	public List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO);
	
	public PatientVO getPatDtlByCrNo(String crNo,UserVO userVO);
	
	public void updatePatientValidStatus(String validStr,String crNo,UserVO userVO);
	
	public void updatePatientAllEpisodeValidStatus(String validStr, String crNo, UserVO userVO);
	
	public int countSearchPatient(HashMap _searchMap, UserVO _userVO);
	public PatientVO[] searchPatient(HashMap _searchMap,UserVO _userVO);

	public void updatePatientMergeStatus(String validStr, String crNo, UserVO userVO);
	public List<CaseSheetDispatchVO> getAllDispatchedCaseSheet(UserVO userVO);

	public List<RecordTypeWiseEnclosureMstVO> getEnclosureByRecordId(String recordId,UserVO userVO);
	
	public List<RecordTypeCheckListMstVO> getChecklistByRecordId(String recordId,UserVO userVO);
	
	//******************************Discharge Printing****************************************************/
	
	public List<DischargePrintingVO> getDisPatientListForPrinting(String unitCode,String wardCode,UserVO userVO);
	
	public List<DischargePrintingVO> getDisPatientListForPrintingByCrNo(String patCrnNo,UserVO userVO);

	
	/** Getting List of Child For Registration Upload
	 * @param userVO
	 * @return
	 */
	public ANCNeonatalDetailVO[] getListOfBirth(UserVO userVO);
	
	public List getRelationList(UserVO userVO);
	
	public PatientVO getMotherDetail(String motherCrNo,UserVO userVO);
	
	public PatientVO getChildDetail(String childCrNo,UserVO userVO);
	
	public PatientVO getBirthSlipDetail(String crNo,UserVO userVO);
	
	public PatientVO[] getListOfDeath(UserVO userVO);
	
	
	public List getGender(UserVO _userVO);
	
	public List getSummonTypeList(UserVO _userVO);
	
	public List getSummonReceivedList(UserVO userVO);
	
	
	public List getSearchUsersByEmpName(String _fName,String _mName,String _lName, UserVO _userVO);
	
	public List getPostSummonList(UserVO userVO);
	
	public List getSummonAssignChangeList(UserVO userVO);
	
	public List getAllSummonDtlVOList(String fromDate,String toDate,UserVO userVO);
	
	public List getAllAttendedSummonDtlVOList(String fromDate,String toDate,UserVO userVO);
	
	public List getAllNotAttendedSummonDtlVOList(String fromDate,String toDate,UserVO userVO);
	
	public List getAllPostDtlSummonDtlVOList(String fromDate,String toDate,UserVO userVO);
	
	public List getNextHearingSummonDtlVOList(String fromDate,String toDate,UserVO userVO);
	
	public List getAllEmployeeList(UserVO userVO) ;
	
	public List getParticularEmpSummonDtlVOList(String fromDate,String toDate,String empId,UserVO userVO);
	
	public List getPatDetailByMLCNo(String mlcNo,UserVO userVO);
	
	public List getPatDetailByPostMortemId(String postMortemId,UserVO userVO);
	
	public AddressVO getPatAddress(String crNo,UserVO userVO);
	
	public List getPatPostMortemDtlByCRNo(String crNo,UserVO userVO);
	
	public List getPatMLCDtlByCRNo(String mlcNo,UserVO userVO);
	
	public List getPatMLCDtlByName(String _firstName,String _middleName,String _lastName, UserVO _userVO);
	
	public List getPatPostMortemDtlByName(String _firstName,String _middleName,String _lastName, UserVO _userVO);
	
	public ANCNeonatalDetailVO[] searchForBirthRegUpload(PatientVO searchFindVO,UserVO userVO);
	
	public ANCNeonatalDetailVO[] searchForBirthRegUploadByMother(PatientVO searchFindVO,UserVO userVO);

	public List getCIDNoInfoList(UserVO userVO);
	
	public List getCIDNoInfoListForInsurance(UserVO userVO);
	
	public List getAllInsuranceCompanyList(UserVO userVO);
	
	public List getPatInfoList(String patCrNo,String patAdmNo, String _firstName,String _middleName,String _lastName,UserVO userVO);
	
	public List getPatDeathUploadList(String patCrNo,String deathDate, String _firstName,String _middleName,String _lastName,UserVO userVO);
	
	public List getExternalLabList(UserVO userVO);
	
	public List getExternalLabTestList(UserVO userVO);

	public List getRecordType(UserVO userVO);
	
	public List getMrdList(String recordType,UserVO userVO);
	
	public RackMstVO[] getRackBasedOnMrd(String recordType,String mrdCode,UserVO userVO);
	
	public RackMstVO[] getRackDtlForCrAdmNo(String recordType,String mrdCode,UserVO userVO);//added by swati sagar on date:14-may-2019

	
	public List getShelfBasedOnRack(String recordType,String rackId,UserVO userVO);
	
	public List getShelfBasedOnRackForCrAdmNo(String recordType,String rackId,UserVO userVO);//added by swati sagar on date:14-may-2019
	
	public List getMrdListUserBased(UserVO userVO);
	
	public List getRecordTypeBasedOnMrd(String mrdCode,UserVO userVO);
	
	public List getRecordPutByList(String deptCode,UserVO userVO);
	
	public List getCheckListByRecordType(String recordType,UserVO userVO);
	
	public List getMrdListBasedOnRecordType(String recordType,UserVO userVO);
	

	public List getPatAdmissionDtlByName(String _firstName,String _middleName,String _lastName, UserVO _userVO);

	
	public List getPatAdmissionDtlByCRNo(String crNo,UserVO userVO);
	
	public List getPatAdmissionDtlByAdmNo(String admNo,UserVO userVO);
	
	public List getPatPostMortemDtlByAdmNo(String admNO,UserVO userVO);
	
	public List getPatMLCDtlByAdmNo(String admNo,UserVO userVO);
	
	public List getSummonAssignDtlList(UserVO userVO);
	
	public List getInsuranceReceivedDtlList(UserVO userVO);

	public List getRecordTypeBasedOnMovableType(UserVO userVO);

	public List getPatientRecord(UserVO userVO);
	
	public List getDiseaseType(UserVO userVO);

	
	public String getRecordPreviousLocation(String selRecordId,UserVO userVO);
	
	public List getAllCheckListList(UserVO userVO);
	
	public List<DischargePrintingVO> getRecordForDuplicateDischargePrinting(String patCrnNo,UserVO userVO);

	
	public List getRequestedRecordId(String empId,UserVO userVO);
	
	public List getParticularSummonTypeSummonDtlVOList(String fromDate,String toDate,String summonTypeId,UserVO userVO);
	
	public List searchInsuranceDetail(String _firstName,String _middleName,String _lastName,String companyId,String crNo,String policyNo, UserVO _userVO);

	public List getPatientProfileByCrNo(String patCrnNo,UserVO userVO);
	
	//EMR	
	public List getDepartmentUnitForEmr(UserVO userVO);
	
	/**
	 * return greater than 1 if patient category is restricted other wise 0
	 * @param patCategory
	 * @param userVO
	 * @return int
	 */
	public int getEmrRestrictedCategory(String patCategory,UserVO userVO);
	
	/**
	 * get all department unit list
	 * @param userVO
	 * @return
	 */
	public List getAllDeptUnitList(UserVO userVO);
	
	public List getEprTabList(UserVO userVO);
	
	public List getUserIdList(UserVO userVO);
	
	public List<PatAdmissionDtlVO> getPatientAdmissionDetailByCrNo(String patCrNo,UserVO userVO);
	
	public List getAllDesignations(UserVO _uservo);
	
	public StaffDetailVO[] searchStaffDetail(HashMap staffDetailMap,UserVO _userVO);

	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode, UserVO _userVO);
	public List getRoomBasedOnHospitalDeptUnitWard(String _hosCode,String _deptUnitCode,String _wardCode);
	public List getLaboratoryAsPerDepartment(UserVO _userVO,String departmentCode); 

	/**
	 * Retrieves all consultants of a multiple hospital.
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getAllConsultant(UserVO _userVO);
	public void getdeptComboDetails(ReportVO voObj);
	
	public List getStaffMembers(String recordType,String rackId,UserVO userVO);
}
