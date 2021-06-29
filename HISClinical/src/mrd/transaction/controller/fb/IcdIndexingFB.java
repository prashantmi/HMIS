package mrd.transaction.controller.fb;

import hisglobal.vo.EpisodeDiagnosisVO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


public class IcdIndexingFB extends ActionForm
{
	private String hmode;
	private String reqPurposeId;
	private String mrdCode;
	private String recordType;
	private String remarks;
	private String addmissionNo;
	private String patAge;
	private String  patIsUrban;
	private String  successStatus;
	private String patBloodGroupCode;
	private String	patGender;
	private String patBloodGroup;
	private String patGuardianName;
	private String patPrimaryCat;
	private String patSpouceName;
	private String 	isNewBorn;
	private String patCrNo;
	private String patNationality;
	private String statusMessage="";
	private List<EpisodeDiagnosisVO>  lstMrdDiag;
	private String diagnosisName;
	private String reqDate;
	private String reqStatus;
	private String reqMode;
	private String forDays;
	private String issueUpto;
	private String requestById;
	private String requestByName;
	private String cancelReason;
	private String approvedBy;
	private String isMrdLengthOne;
	private String isRecordRequested;
	private String reqRemarks[];
	private String userEmpId;
	private String ducRecordId;
	private String[] searchRecordId;
	private String isReqOnlineOffline;
	private String episodeCode;
	private String episodeVisitNo;
	private String departmentCode;
	private String departmentUnitCode;
	private String diseaseCode;
	private String icdCodeList;
	private String[] arrIcdCodeList;
	private String[] arrdiagnosisName;
	///Search
	private String hrgnum_puk;
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	private String hipnum_admno;
	private String hrgnum_mlc_no;
	private String hipdt_disstatus_code;
	private String hrgnum_isunknown;
	private String hrgnum_is_broughtdead;
	private String fromDate="";
	private String toDate="";
	private String deathCase;
	private String gnum_gender_code;
	private String hrgnum_name;
	private String selectedIndex;
	private String[] chkSelectRecord;
	private String concatedIndex;
	
	private String recordIdToRemove;
	private String requestByFlag;
	private String  requestByExternalName;
	private String deptname;
	private String deptunitname;
	private String hodName;
	private String requestByEmpDept;
	private String hiddenDeptName;
	private String sheetDept;
	private String employeeId;

	private String departmentName;
	private String departmentUnitName;
	private String mlcNo;

	private String patName;
	private String patCompleteAddress;
	
	private String patAdmNo;
	private String admAdvNo;
	private String admDateTime;
	private String bedAllocDateTime;
	private String disDateTime;
	private String disStatusCode;
	private String admComplain;
	private String dischargeAdvice;
	private String wardCode;
	private String patReligion;
	private String ipdRoomCode;
	private String patDOB;
	private String disease;
	private String bedCode;
	private String wardName;
	private String patReligionCode;
	private String wardTypeCode;
	private String patGenderCode;
	private String patNationalId;
	private String patPrimaryCatCode;
	private String ipdRoomName;
	private String ipdRoomTypeCode;
	private String bedName;
	private String bedTypeCode;
	private String ownDepartmentUnitCode;
	private String ownWardCode;
	private String ownDepartmentCode;
	private String motherAdmNo;
	private String admStatusCode;
	private String charge;
	private String isDead;
	private String consultantID;
	private String consultantName;

	// ADT Details
	private String isIpd;
	private String patAdmittedDays;
	private String isBedSharable;

	// Other Details
	private String patCaste;
	
	private String isActualDateOfMarriage;
	private String patDateOfMarriage;
	private String patAgeOfMarriage;
	private String patAgeOfMenarche;

	private String patCasteName;



	// ICD Entry Form
	private String patDiagnosisDtl;
	
	// Redirect OPD patient
	private String patChangeType;
	private String hospitalCode;	
	private String hospitalName;
	
	//Added by Vasu on 7.Mar.2019
	private String icdCodeToBeRemoved;
	private String icdRecordStatus[];
	
	
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentUnitName() {
		return departmentUnitName;
	}
	public void setDepartmentUnitName(String departmentUnitName) {
		this.departmentUnitName = departmentUnitName;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatCompleteAddress() {
		return patCompleteAddress;
	}
	public void setPatCompleteAddress(String patCompleteAddress) {
		this.patCompleteAddress = patCompleteAddress;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getAdmAdvNo() {
		return admAdvNo;
	}
	public void setAdmAdvNo(String admAdvNo) {
		this.admAdvNo = admAdvNo;
	}
	public String getAdmDateTime() {
		return admDateTime;
	}
	public void setAdmDateTime(String admDateTime) {
		this.admDateTime = admDateTime;
	}
	public String getBedAllocDateTime() {
		return bedAllocDateTime;
	}
	public void setBedAllocDateTime(String bedAllocDateTime) {
		this.bedAllocDateTime = bedAllocDateTime;
	}
	public String getDisDateTime() {
		return disDateTime;
	}
	public void setDisDateTime(String disDateTime) {
		this.disDateTime = disDateTime;
	}
	public String getDisStatusCode() {
		return disStatusCode;
	}
	public void setDisStatusCode(String disStatusCode) {
		this.disStatusCode = disStatusCode;
	}
	public String getAdmComplain() {
		return admComplain;
	}
	public void setAdmComplain(String admComplain) {
		this.admComplain = admComplain;
	}
	public String getDischargeAdvice() {
		return dischargeAdvice;
	}
	public void setDischargeAdvice(String dischargeAdvice) {
		this.dischargeAdvice = dischargeAdvice;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getIpdRoomCode() {
		return ipdRoomCode;
	}
	public void setIpdRoomCode(String ipdRoomCode) {
		this.ipdRoomCode = ipdRoomCode;
	}
	public String getBedCode() {
		return bedCode;
	}
	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getWardTypeCode() {
		return wardTypeCode;
	}
	public void setWardTypeCode(String wardTypeCode) {
		this.wardTypeCode = wardTypeCode;
	}
	public String getIpdRoomName() {
		return ipdRoomName;
	}
	public void setIpdRoomName(String ipdRoomName) {
		this.ipdRoomName = ipdRoomName;
	}
	public String getIpdRoomTypeCode() {
		return ipdRoomTypeCode;
	}
	public void setIpdRoomTypeCode(String ipdRoomTypeCode) {
		this.ipdRoomTypeCode = ipdRoomTypeCode;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
	public String getBedTypeCode() {
		return bedTypeCode;
	}
	public void setBedTypeCode(String bedTypeCode) {
		this.bedTypeCode = bedTypeCode;
	}
	public String getOwnDepartmentUnitCode() {
		return ownDepartmentUnitCode;
	}
	public void setOwnDepartmentUnitCode(String ownDepartmentUnitCode) {
		this.ownDepartmentUnitCode = ownDepartmentUnitCode;
	}
	public String getOwnWardCode() {
		return ownWardCode;
	}
	public void setOwnWardCode(String ownWardCode) {
		this.ownWardCode = ownWardCode;
	}
	public String getOwnDepartmentCode() {
		return ownDepartmentCode;
	}
	public void setOwnDepartmentCode(String ownDepartmentCode) {
		this.ownDepartmentCode = ownDepartmentCode;
	}
	public String getMotherAdmNo() {
		return motherAdmNo;
	}
	public void setMotherAdmNo(String motherAdmNo) {
		this.motherAdmNo = motherAdmNo;
	}
	public String getAdmStatusCode() {
		return admStatusCode;
	}
	public void setAdmStatusCode(String admStatusCode) {
		this.admStatusCode = admStatusCode;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public String getIsDead() {
		return isDead;
	}
	public void setIsDead(String isDead) {
		this.isDead = isDead;
	}
	public String getConsultantID() {
		return consultantID;
	}
	public void setConsultantID(String consultantID) {
		this.consultantID = consultantID;
	}
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	public String getIsIpd() {
		return isIpd;
	}
	public void setIsIpd(String isIpd) {
		this.isIpd = isIpd;
	}
	public String getPatAdmittedDays() {
		return patAdmittedDays;
	}
	public void setPatAdmittedDays(String patAdmittedDays) {
		this.patAdmittedDays = patAdmittedDays;
	}
	public String getIsBedSharable() {
		return isBedSharable;
	}
	public void setIsBedSharable(String isBedSharable) {
		this.isBedSharable = isBedSharable;
	}
	public String getPatCaste() {
		return patCaste;
	}
	public void setPatCaste(String patCaste) {
		this.patCaste = patCaste;
	}
	public String getIsActualDateOfMarriage() {
		return isActualDateOfMarriage;
	}
	public void setIsActualDateOfMarriage(String isActualDateOfMarriage) {
		this.isActualDateOfMarriage = isActualDateOfMarriage;
	}
	public String getPatDateOfMarriage() {
		return patDateOfMarriage;
	}
	public void setPatDateOfMarriage(String patDateOfMarriage) {
		this.patDateOfMarriage = patDateOfMarriage;
	}
	public String getPatAgeOfMarriage() {
		return patAgeOfMarriage;
	}
	public void setPatAgeOfMarriage(String patAgeOfMarriage) {
		this.patAgeOfMarriage = patAgeOfMarriage;
	}
	public String getPatAgeOfMenarche() {
		return patAgeOfMenarche;
	}
	public void setPatAgeOfMenarche(String patAgeOfMenarche) {
		this.patAgeOfMenarche = patAgeOfMenarche;
	}
	public String getPatCasteName() {
		return patCasteName;
	}
	public void setPatCasteName(String patCasteName) {
		this.patCasteName = patCasteName;
	}
	public String getPatDiagnosisDtl() {
		return patDiagnosisDtl;
	}
	public void setPatDiagnosisDtl(String patDiagnosisDtl) {
		this.patDiagnosisDtl = patDiagnosisDtl;
	}
	public String getPatChangeType() {
		return patChangeType;
	}
	public void setPatChangeType(String patChangeType) {
		this.patChangeType = patChangeType;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	
	public String getConcatedIndex() {
		return concatedIndex;
	}
	public void setConcatedIndex(String concatedIndex) {
		this.concatedIndex = concatedIndex;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getReqPurposeId() {
		return reqPurposeId;
	}
	public void setReqPurposeId(String reqPurposeId) {
		this.reqPurposeId = reqPurposeId;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	
	public String getAddmissionNo() {
		return addmissionNo;
	}
	public void setAddmissionNo(String addmissionNo) {
		this.addmissionNo = addmissionNo;
	}
	public String getReqMode() {
		return reqMode;
	}
	public void setReqMode(String reqMode) {
		this.reqMode = reqMode;
	}
	public String getForDays() {
		return forDays;
	}
	public void setForDays(String forDays) {
		this.forDays = forDays;
	}
	public String getIssueUpto() {
		return issueUpto;
	}
	public void setIssueUpto(String issueUpto) {
		this.issueUpto = issueUpto;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getIsMrdLengthOne() {
		return isMrdLengthOne;
	}
	public void setIsMrdLengthOne(String isMrdLengthOne) {
		this.isMrdLengthOne = isMrdLengthOne;
	}
	public String getRequestById() {
		return requestById;
	}
	public void setRequestById(String requestById) {
		this.requestById = requestById;
	}
	public String getRequestByName() {
		return requestByName;
	}
	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}
	public String getHrgnum_puk() {
		return hrgnum_puk;
	}
	public void setHrgnum_puk(String hrgnum_puk) {
		this.hrgnum_puk = hrgnum_puk;
	}
	public String getHrgstr_fname() {
		return hrgstr_fname;
	}
	public void setHrgstr_fname(String hrgstr_fname) {
		this.hrgstr_fname = hrgstr_fname;
	}
	public String getHrgstr_mname() {
		return hrgstr_mname;
	}
	public void setHrgstr_mname(String hrgstr_mname) {
		this.hrgstr_mname = hrgstr_mname;
	}
	public String getHrgstr_lname() {
		return hrgstr_lname;
	}
	public void setHrgstr_lname(String hrgstr_lname) {
		this.hrgstr_lname = hrgstr_lname;
	}
	public String getHipnum_admno() {
		return hipnum_admno;
	}
	public void setHipnum_admno(String hipnum_admno) {
		this.hipnum_admno = hipnum_admno;
	}
	public String getHrgnum_mlc_no() {
		return hrgnum_mlc_no;
	}
	public void setHrgnum_mlc_no(String hrgnum_mlc_no) {
		this.hrgnum_mlc_no = hrgnum_mlc_no;
	}
	public String getHipdt_disstatus_code() {
		return hipdt_disstatus_code;
	}
	public void setHipdt_disstatus_code(String hipdt_disstatus_code) {
		this.hipdt_disstatus_code = hipdt_disstatus_code;
	}
	public String getHrgnum_isunknown() {
		return hrgnum_isunknown;
	}
	public void setHrgnum_isunknown(String hrgnum_isunknown) {
		this.hrgnum_isunknown = hrgnum_isunknown;
	}
	public String getHrgnum_is_broughtdead() {
		return hrgnum_is_broughtdead;
	}
	public void setHrgnum_is_broughtdead(String hrgnum_is_broughtdead) {
		this.hrgnum_is_broughtdead = hrgnum_is_broughtdead;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDeathCase() {
		return deathCase;
	}
	public void setDeathCase(String deathCase) {
		this.deathCase = deathCase;
	}
	public String getGnum_gender_code() {
		return gnum_gender_code;
	}
	public void setGnum_gender_code(String gnum_gender_code) {
		this.gnum_gender_code = gnum_gender_code;
	}
	public String getHrgnum_name() {
		return hrgnum_name;
	}
	public void setHrgnum_name(String hrgnum_name) {
		this.hrgnum_name = hrgnum_name;
	}
	public String getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public String[] getChkSelectRecord() {
		return chkSelectRecord;
	}
	public void setChkSelectRecord(String[] chkSelectRecord) {
		this.chkSelectRecord = chkSelectRecord;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		
		this.setReqPurposeId("");
		this.setForDays("");
		this.setRequestByName("");
		this.setRequestById("");
		this.recordType="-1";
		this.remarks="";
		this.setRequestByExternalName("");
		this.requestByFlag="0";
	}
	public String getIsRecordRequested() {
		return isRecordRequested;
	}
	public void setIsRecordRequested(String isRecordRequested) {
		this.isRecordRequested = isRecordRequested;
	}
	public String getRecordIdToRemove() {
		return recordIdToRemove;
	}
	public void setRecordIdToRemove(String recordIdToRemove) {
		this.recordIdToRemove = recordIdToRemove;
	}
	public String[] getReqRemarks() {
		return reqRemarks;
	}
	public void setReqRemarks(String[] reqRemarks) {
		this.reqRemarks = reqRemarks;
	}
	public String getUserEmpId() {
		return userEmpId;
	}
	public void setUserEmpId(String userEmpId) {
		this.userEmpId = userEmpId;
	}
	public String getDucRecordId() {
		return ducRecordId;
	}
	public void setDucRecordId(String ducRecordId) {
		this.ducRecordId = ducRecordId;
	}
	public String[] getSearchRecordId() {
		return searchRecordId;
	}
	public void setSearchRecordId(String[] searchRecordId) {
		this.searchRecordId = searchRecordId;
	}
	public String getIsReqOnlineOffline() {
		return isReqOnlineOffline;
	}
	public void setIsReqOnlineOffline(String isReqOnlineOffline) {
		this.isReqOnlineOffline = isReqOnlineOffline;
	}
	public String getRequestByFlag() {
		return requestByFlag;
	}
	public void setRequestByFlag(String requestByFlag) {
		this.requestByFlag = requestByFlag;
	}
	public String getRequestByExternalName() {
		return requestByExternalName;
	}
	public void setRequestByExternalName(String requestByExternalName) {
		this.requestByExternalName = requestByExternalName;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptunitname() {
		return deptunitname;
	}
	public void setDeptunitname(String deptunitname) {
		this.deptunitname = deptunitname;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
	}
	public String getRequestByEmpDept() {
		return requestByEmpDept;
	}
	public void setRequestByEmpDept(String requestByEmpDept) {
		this.requestByEmpDept = requestByEmpDept;
	}
	public String getHiddenDeptName() {
		return hiddenDeptName;
	}
	public void setHiddenDeptName(String hiddenDeptName) {
		this.hiddenDeptName = hiddenDeptName;
	}
	public String getSheetDept() {
		return sheetDept;
	}
	public void setSheetDept(String sheetDept) {
		this.sheetDept = sheetDept;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPatSpouceName() {
		return patSpouceName;
	}
	public void setPatSpouceName(String patSpouceName) {
		this.patSpouceName = patSpouceName;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public List<EpisodeDiagnosisVO> getLstMrdDiag() {
		return lstMrdDiag;
	}
	public void setLstMrdDiag(List<EpisodeDiagnosisVO> lstMrdDiag) {
		this.lstMrdDiag = lstMrdDiag;
	}
	
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getIcdCodeList() {
		return icdCodeList;
	}
	public void setIcdCodeList(String icdCodeList) {
		this.icdCodeList = icdCodeList;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatNationalId() {
		return patNationalId;
	}
	public void setPatNationalId(String patNationalId) {
		this.patNationalId = patNationalId;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getPatReligionCode() {
		return patReligionCode;
	}
	public void setPatReligionCode(String patReligionCode) {
		this.patReligionCode = patReligionCode;
	}
	public String getPatReligion() {
		return patReligion;
	}
	public void setPatReligion(String patReligion) {
		this.patReligion = patReligion;
	}
	public String getPatBloodGroup() {
		return patBloodGroup;
	}
	public void setPatBloodGroup(String patBloodGroup) {
		this.patBloodGroup = patBloodGroup;
	}
	public String getPatBloodGroupCode() {
		return patBloodGroupCode;
	}
	public void setPatBloodGroupCode(String patBloodGroupCode) {
		this.patBloodGroupCode = patBloodGroupCode;
	}
	public String getIsNewBorn() {
		return isNewBorn;
	}
	public void setIsNewBorn(String isNewBorn) {
		this.isNewBorn = isNewBorn;
	}
	public String getPatNationality() {
		return patNationality;
	}
	public void setPatNationality(String patNationality) {
		this.patNationality = patNationality;
	}
	public String getPatIsUrban() {
		return patIsUrban;
	}
	public void setPatIsUrban(String patIsUrban) {
		this.patIsUrban = patIsUrban;
	}
	public String getSuccessStatus() {
		return successStatus;
	}
	public void setSuccessStatus(String successStatus) {
		this.successStatus = successStatus;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String[] getArrIcdCodeList() {
		return arrIcdCodeList;
	}
	public void setArrIcdCodeList(String[] arrIcdCodeList) {
		this.arrIcdCodeList = arrIcdCodeList;
	}
	public String[] getArrdiagnosisName() {
		return arrdiagnosisName;
	}
	public void setArrdiagnosisName(String[] arrdiagnosisName) {
		this.arrdiagnosisName = arrdiagnosisName;
	}
	public String getIcdCodeToBeRemoved() {
		return icdCodeToBeRemoved;
	}
	public void setIcdCodeToBeRemoved(String icdCodeToBeRemoved) {
		this.icdCodeToBeRemoved = icdCodeToBeRemoved;
	}
	public String[] getIcdRecordStatus() {
		return icdRecordStatus;
	}
	public void setIcdRecordStatus(String[] icdRecordStatus) {
		this.icdRecordStatus = icdRecordStatus;
	}
	

	
}
