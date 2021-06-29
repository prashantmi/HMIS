package hisglobal.vo;

public class SummonDetailVO extends ValueObject
{
	private String summonId;
	private String summonTypeId;
	private String recordType;
	private String recordCat;
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String MLCNo;
	private String patAdmNo;
	private String  certificateNo;
	private String deceasedNo;
	private String postmortemId;
	private String summonDateTime;
	private String summonReceiveDateTime;
	private String hearingDateTime;
	private String courtName;
	private String courtAddress;
	private String judgeName;
	private String caseNo;
	private String policeStation;
	private String constableName;
	private String constableDesig;
	private String constableBadgeNo;
	private String isEmpSpecific;
	private String empName;
	private String assignTo;
	private String assignSame;
	private String otherAssignReason;
	private String summonAck;
	private String entryMode;
	private String assignBy;
	private String summonRemarks;
	private String status;
	private String statusDesc;
	private String visitRemarks;
	private String nextHearingDateTime;
	private String instruction;
	private String patName;
	private String fatherName;
	private String spouseName;
	private String motherName;
	private String patGenderCode;
	private String patDOB;
	private String patAddress;
	private String CIDNo;
	private String forwardTo;
	private String isUpload;
	private String scanSummon;
	private String deptCode;
	private String isValid;
	private String entryDate;
	private String seatId;
	private String assignDate;
	private String assignSeatId;
	private String postentryDate;
	private String postSeatId;
	private String hospitalCode;
	private String summonTypeDesc;
	
	private String empId;
	private String empGenderCode;
	private String empGender;
	private String empAge;
	private String empDesignation;
	private String empAddress;
	private String assignToDesigID;
	private String assignEmpDesignation;
	private String assignEmpName;
	private String patAgeType;
	private String patGender;
	private String postMotemReqDate;
	private String mlcDate;
	private  String patAge;
	private String cidNoFlag;
	private String patAdmissionDate;
	
	
	
	public String getPatAdmissionDate() {
		return patAdmissionDate;
	}
	public void setPatAdmissionDate(String patAdmissionDate) {
		this.patAdmissionDate = patAdmissionDate;
	}
	public String getCidNoFlag() {
		return cidNoFlag;
	}
	public void setCidNoFlag(String cidNoFlag) {
		this.cidNoFlag = cidNoFlag;
	}
	public String getMlcDate() {
		return mlcDate;
	}
	public void setMlcDate(String mlcDate) {
		this.mlcDate = mlcDate;
	}
	public String getPostMotemReqDate() {
		return postMotemReqDate;
	}
	public void setPostMotemReqDate(String postMotemReqDate) {
		this.postMotemReqDate = postMotemReqDate;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatAgeType() {
		return patAgeType;
	}
	public void setPatAgeType(String patAgeType) {
		this.patAgeType = patAgeType;
	}
	public String getAssignEmpName() {
		return assignEmpName;
	}
	public void setAssignEmpName(String assignEmpName) {
		this.assignEmpName = assignEmpName;
	}
	public String getAssignToDesigID() {
		return assignToDesigID;
	}
	public void setAssignToDesigID(String assignToDesigID) {
		this.assignToDesigID = assignToDesigID;
	}
	public String getAssignEmpDesignation() {
		return assignEmpDesignation;
	}
	public void setAssignEmpDesignation(String assignEmpDesignation) {
		this.assignEmpDesignation = assignEmpDesignation;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpGenderCode() {
		return empGenderCode;
	}
	public void setEmpGenderCode(String empGenderCode) {
		this.empGenderCode = empGenderCode;
	}
	public String getEmpAge() {
		return empAge;
	}
	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	public String getSummonTypeDesc() {
		return summonTypeDesc;
	}
	public void setSummonTypeDesc(String summonTypeDesc) {
		this.summonTypeDesc = summonTypeDesc;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getSummonId() {
		return summonId;
	}
	public void setSummonId(String summonId) {
		this.summonId = summonId;
	}
	public String getSummonTypeId() {
		return summonTypeId;
	}
	public void setSummonTypeId(String summonTypeId) {
		this.summonTypeId = summonTypeId;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getRecordCat() {
		return recordCat;
	}
	public void setRecordCat(String recordCat) {
		this.recordCat = recordCat;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
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
	public String getMLCNo() {
		return MLCNo;
	}
	public void setMLCNo(String no) {
		MLCNo = no;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getDeceasedNo() {
		return deceasedNo;
	}
	public void setDeceasedNo(String deceasedNo) {
		this.deceasedNo = deceasedNo;
	}
	public String getPostmortemId() {
		return postmortemId;
	}
	public void setPostmortemId(String postmortemId) {
		this.postmortemId = postmortemId;
	}
	public String getSummonDateTime() {
		return summonDateTime;
	}
	public void setSummonDateTime(String summonDateTime) {
		this.summonDateTime = summonDateTime;
	}
	public String getSummonReceiveDateTime() {
		return summonReceiveDateTime;
	}
	public void setSummonReceiveDateTime(String summonReceiveDateTime) {
		this.summonReceiveDateTime = summonReceiveDateTime;
	}
	public String getHearingDateTime() {
		return hearingDateTime;
	}
	public void setHearingDateTime(String hearingDateTime) {
		this.hearingDateTime = hearingDateTime;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getCourtAddress() {
		return courtAddress;
	}
	public void setCourtAddress(String courtAddress) {
		this.courtAddress = courtAddress;
	}
	public String getJudgeName() {
		return judgeName;
	}
	public void setJudgeName(String judgeName) {
		this.judgeName = judgeName;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getPoliceStation() {
		return policeStation;
	}
	public void setPoliceStation(String policeStation) {
		this.policeStation = policeStation;
	}
	public String getConstableName() {
		return constableName;
	}
	public void setConstableName(String constableName) {
		this.constableName = constableName;
	}
	public String getConstableDesig() {
		return constableDesig;
	}
	public void setConstableDesig(String constableDesig) {
		this.constableDesig = constableDesig;
	}
	public String getConstableBadgeNo() {
		return constableBadgeNo;
	}
	public void setConstableBadgeNo(String constableBadgeNo) {
		this.constableBadgeNo = constableBadgeNo;
	}
	
	public String getIsEmpSpecific() {
		return isEmpSpecific;
	}
	public void setIsEmpSpecific(String isEmpSpecific) {
		this.isEmpSpecific = isEmpSpecific;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAssignTo() {
		return assignTo;
	}
	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	
	
	public String getAssignSame() {
		return assignSame;
	}
	public void setAssignSame(String assignSame) {
		this.assignSame = assignSame;
	}
	public String getOtherAssignReason() {
		return otherAssignReason;
	}
	public void setOtherAssignReason(String otherAssignReason) {
		this.otherAssignReason = otherAssignReason;
	}
	public String getSummonAck() {
		return summonAck;
	}
	public void setSummonAck(String summonAck) {
		this.summonAck = summonAck;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}
	public String getAssignBy() {
		return assignBy;
	}
	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}
	public String getSummonRemarks() {
		return summonRemarks;
	}
	public void setSummonRemarks(String summonRemarks) {
		this.summonRemarks = summonRemarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVisitRemarks() {
		return visitRemarks;
	}
	public void setVisitRemarks(String visitRemarks) {
		this.visitRemarks = visitRemarks;
	}
	public String getNextHearingDateTime() {
		return nextHearingDateTime;
	}
	public void setNextHearingDateTime(String nextHearingDateTime) {
		this.nextHearingDateTime = nextHearingDateTime;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getPatAddress() {
		return patAddress;
	}
	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}
	public String getCIDNo() {
		return CIDNo;
	}
	public void setCIDNo(String no) {
		CIDNo = no;
	}
	public String getForwardTo() {
		return forwardTo;
	}
	public void setForwardTo(String forwardTo) {
		this.forwardTo = forwardTo;
	}
	public String getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}
	public String getScanSummon() {
		return scanSummon;
	}
	public void setScanSummon(String scanSummon) {
		this.scanSummon = scanSummon;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getSeatId() {
		return seatId;
	}
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	public String getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	public String getAssignSeatId() {
		return assignSeatId;
	}
	public void setAssignSeatId(String assignSeatId) {
		this.assignSeatId = assignSeatId;
	}
	public String getPostentryDate() {
		return postentryDate;
	}
	public void setPostentryDate(String postentryDate) {
		this.postentryDate = postentryDate;
	}
	public String getPostSeatId() {
		return postSeatId;
	}
	public void setPostSeatId(String postSeatId) {
		this.postSeatId = postSeatId;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getEmpGender() {
		return empGender;
	}
	public void setEmpGender(String empGender) {
		this.empGender = empGender;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	
}
