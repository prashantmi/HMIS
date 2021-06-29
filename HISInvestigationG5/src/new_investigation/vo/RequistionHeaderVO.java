package new_investigation.vo;

import hisglobal.vo.ValueObject;

public class RequistionHeaderVO extends ValueObject {

	
	private String labCode;
	private String testCode;
	private String labName;
	private String testName;
	
	private String requisitionNumber;
	private String patCrNo;
	private String visitNo;
	private String isConfidential;
	private String reqHeaderStatus;
	
	private String episodeCode;
	private String patName;
	private String reqType;
	private String patDob;
	private String visitDate;
	private String isActualDob;
	private String orderedByDoctor;
	private String genderCode;
	private String requsitionRaisedThrough;
	private String patAge;
	private String patAdmissionNo;
	private String patAddress;
	private String wardCode;
	private String mobileNo;
	private String emailId;
	private String roomCode;
	private String bedCode;
	private String mlcNo;
	private String bedName;
	private String roomName;
	private String wardName;
	private String deptName;
	private String reqDeleteSeatId;
	private String unitName;
	private String reqDeleteDate;
	private String deptCode;
	private String deptUnitCode;
	private String isAutomatedRequest;
	private String orderedByDoctorName;
	private String refHospitalCode;
	private String deptUnitName;
	private String refLabCode;
	private String hospOrLabName;	
	private String extCrNo;
	private String billNo;
	private String deleteReason;
	private String requisitionDate;
	private String patCatCode;
	private String visitReason;
	
	private String isassociatedTest;
	private String isassociatedreqno;
	private String labbasedaptdatetime;
	private String status;
	
	private String followup;

	private String requisitionwiseno;

	
	
	  private String chief_complaints_code[] ;
	  private String chief_complaints_name[] ;
	  private String diagnosis_code[] ;
	  private String diagnosis_name[] ;
	  private String is_pregnant ;
	  private String is_mlc ;
	  private String is_vip ;
	  private String is_dead ;
	  private String is_newborn ;
	  private String instruction_testwise ;
	
	  private String is_unknown ;
	  private String chiefname ;
	  private String diagname ;
	  
	  
    public String getFollowup() {
		return followup;
	}


	public void setFollowup(String followup) {
		this.followup = followup;
	}
	
	public String getPatCatCode() {
		return patCatCode;
	}
	public void setPatCatCode(String patCatCode) {
		this.patCatCode = patCatCode;
	}
	private String advisedByDocName;
	private String advisedByDocNo;
	
	public String getAdvisedByDocName() {
		return advisedByDocName;
	}
	public void setAdvisedByDocName(String advisedByDocName) {
		this.advisedByDocName = advisedByDocName;
	}
	public String getAdvisedByDocNo() {
		return advisedByDocNo;
	}
	public void setAdvisedByDocNo(String advisedByDocNo) {
		this.advisedByDocNo = advisedByDocNo;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getRequisitionNumber() {
		return requisitionNumber;
	}
	public void setRequisitionNumber(String requisitionNumber) {
		this.requisitionNumber = requisitionNumber;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getVisitNo() {
		return visitNo;
	}
	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}
	public String getIsConfidential() {
		return isConfidential;
	}
	public void setIsConfidential(String isConfidential) {
		this.isConfidential = isConfidential;
	}
	public String getReqHeaderStatus() {
		return reqHeaderStatus;
	}
	public void setReqHeaderStatus(String reqHeaderStatus) {
		this.reqHeaderStatus = reqHeaderStatus;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public String getPatDob() {
		return patDob;
	}
	public void setPatDob(String patDob) {
		this.patDob = patDob;
	}
	public String getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	public String getIsActualDob() {
		return isActualDob;
	}
	public void setIsActualDob(String isActualDob) {
		this.isActualDob = isActualDob;
	}
	public String getOrderedByDoctor() {
		return orderedByDoctor;
	}
	public void setOrderedByDoctor(String orderedByDoctor) {
		this.orderedByDoctor = orderedByDoctor;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getRequsitionRaisedThrough() {
		return requsitionRaisedThrough;
	}
	public void setRequsitionRaisedThrough(String requsitionRaisedThrough) {
		this.requsitionRaisedThrough = requsitionRaisedThrough;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getPatAdmissionNo() {
		return patAdmissionNo;
	}
	public void setPatAdmissionNo(String patAdmissionNo) {
		this.patAdmissionNo = patAdmissionNo;
	}
	public String getPatAddress() {
		return patAddress;
	}
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getBedCode() {
		return bedCode;
	}
	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}
	public String getMlcNo() {
		return mlcNo;
	}
	public void setMlcNo(String mlcNo) {
		this.mlcNo = mlcNo;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getReqDeleteSeatId() {
		return reqDeleteSeatId;
	}
	public void setReqDeleteSeatId(String reqDeleteSeatId) {
		this.reqDeleteSeatId = reqDeleteSeatId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getReqDeleteDate() {
		return reqDeleteDate;
	}
	public void setReqDeleteDate(String reqDeleteDate) {
		this.reqDeleteDate = reqDeleteDate;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getIsAutomatedRequest() {
		return isAutomatedRequest;
	}
	public void setIsAutomatedRequest(String isAutomatedRequest) {
		this.isAutomatedRequest = isAutomatedRequest;
	}
	public String getOrderedByDoctorName() {
		return orderedByDoctorName;
	}
	public void setOrderedByDoctorName(String orderedByDoctorName) {
		this.orderedByDoctorName = orderedByDoctorName;
	}
	public String getRefHospitalCode() {
		return refHospitalCode;
	}
	public void setRefHospitalCode(String refHospitalCode) {
		this.refHospitalCode = refHospitalCode;
	}
	public String getDeptUnitName() {
		return deptUnitName;
	}
	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}
	public String getRefLabCode() {
		return refLabCode;
	}
	public void setRefLabCode(String refLabCode) {
		this.refLabCode = refLabCode;
	}
	public String getHospOrLabName() {
		return hospOrLabName;
	}
	public void setHospOrLabName(String hospOrLabName) {
		this.hospOrLabName = hospOrLabName;
	}
	public String getExtCrNo() {
		return extCrNo;
	}
	public void setExtCrNo(String extCrNo) {
		this.extCrNo = extCrNo;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getRequisitionDate() {
		return requisitionDate;
	}
	public void setRequisitionDate(String requisitionDate) {
		this.requisitionDate = requisitionDate;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	public String getIsassociatedTest() {
		return isassociatedTest;
	}
	public void setIsassociatedTest(String isassociatedTest) {
		this.isassociatedTest = isassociatedTest;
	}
	public String getIsassociatedreqno() {
		return isassociatedreqno;
	}
	public void setIsassociatedreqno(String isassociatedreqno) {
		this.isassociatedreqno = isassociatedreqno;
	}
	public String getLabbasedaptdatetime() {
		return labbasedaptdatetime;
	}
	public void setLabbasedaptdatetime(String labbasedaptdatetime) {
		this.labbasedaptdatetime = labbasedaptdatetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	public String getRequisitionwiseno() {
		return requisitionwiseno;
	}


	public void setRequisitionwiseno(String requisitionwiseno) {
		this.requisitionwiseno = requisitionwiseno;
	}


	public String[] getChief_complaints_code() {
		return chief_complaints_code;
	}


	public void setChief_complaints_code(String[] chief_complaints_code) {
		this.chief_complaints_code = chief_complaints_code;
	}


	public String[] getChief_complaints_name() {
		return chief_complaints_name;
	}


	public void setChief_complaints_name(String[] chief_complaints_name) {
		this.chief_complaints_name = chief_complaints_name;
	}


	public String[] getDiagnosis_code() {
		return diagnosis_code;
	}


	public void setDiagnosis_code(String[] diagnosis_code) {
		this.diagnosis_code = diagnosis_code;
	}


	public String[] getDiagnosis_name() {
		return diagnosis_name;
	}


	public void setDiagnosis_name(String[] diagnosis_name) {
		this.diagnosis_name = diagnosis_name;
	}


	public String getIs_pregnant() {
		return is_pregnant;
	}


	public void setIs_pregnant(String is_pregnant) {
		this.is_pregnant = is_pregnant;
	}


	public String getIs_mlc() {
		return is_mlc;
	}


	public void setIs_mlc(String is_mlc) {
		this.is_mlc = is_mlc;
	}


	public String getIs_vip() {
		return is_vip;
	}


	public void setIs_vip(String is_vip) {
		this.is_vip = is_vip;
	}


	public String getIs_dead() {
		return is_dead;
	}


	public void setIs_dead(String is_dead) {
		this.is_dead = is_dead;
	}


	public String getIs_newborn() {
		return is_newborn;
	}


	public void setIs_newborn(String is_newborn) {
		this.is_newborn = is_newborn;
	}


	public String getInstruction_testwise() {
		return instruction_testwise;
	}


	public void setInstruction_testwise(String instruction_testwise) {
		this.instruction_testwise = instruction_testwise;
	}


	public String getIs_unknown() {
		return is_unknown;
	}


	public void setIs_unknown(String is_unknown) {
		this.is_unknown = is_unknown;
	}


	public String getChiefname() {
		return chiefname;
	}


	public void setChiefname(String chiefname) {
		this.chiefname = chiefname;
	}


	public String getDiagname() {
		return diagname;
	}


	public void setDiagname(String diagname) {
		this.diagname = diagname;
	}

	
	
	
}
