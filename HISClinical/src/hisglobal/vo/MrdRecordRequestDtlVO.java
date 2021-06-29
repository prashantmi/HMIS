package hisglobal.vo;

public class MrdRecordRequestDtlVO extends ValueObject
{
	private String requestId;
	private String reqDate;
	private String reqPurposeId;
	private String priority;
	private String purpose;
	private String mrdCode;
	private String recordType;
	private String recordTypeName;
	private String recordTypeCode;
	private String remarks;
	private String reqStatus;
	private String reqRecordStatus;//added by sandip naik on 20/7/17
	private String reqRecordUnit;
	private String deptunitname;
	private String admno;
	private String recordId;//end by sandip naik on 20/7/17
	private String reqMode;
	private String forDays;
	private String issueUpto;
	private String requestBy;
	private String requestByName;
	private String cancelReason;
	private String approvedBy;
	private String approvedSeatId;
	private String approvedDate; 
	private String requestByFlag;
	private String requestByNameDept;
	private String requestByNameDesig;
	private String loginRequestByDept;
	private String loginRequestByDeptHod;
	private String loginRequestByName;
	private String loginRequestByDesig;
	private String DESIGNATED_APPROVED_BY;
	private String isUserEmp;
	private String reqByIsHod;
	private String rejectedRecord;
	private String rejectRemarks;
	private String isRejected;
	private String extendDays;
	private String extendReason;
	private String profileId;
	private String HPMRNUM_UNDER_EXTENTION_SNO;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
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
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
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
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
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
	public String getApprovedSeatId() {
		return approvedSeatId;
	}
	public void setApprovedSeatId(String approvedSeatId) {
		this.approvedSeatId = approvedSeatId;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getRequestByName() {
		return requestByName;
	}
	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String getRecordTypeCode() {
		return recordTypeCode;
	}
	public void setRecordTypeCode(String recordTypeCode) {
		this.recordTypeCode = recordTypeCode;
	}
	public String getRequestByFlag() {
		return requestByFlag;
	}
	public void setRequestByFlag(String requestByFlag) {
		this.requestByFlag = requestByFlag;
	}
	public String getRequestByNameDept() {
		return requestByNameDept;
	}
	public void setRequestByNameDept(String requestByNameDept) {
		this.requestByNameDept = requestByNameDept;
	}
	public String getLoginRequestByDept() {
		return loginRequestByDept;
	}
	public void setLoginRequestByDept(String loginRequestByDept) {
		this.loginRequestByDept = loginRequestByDept;
	}
	public String getLoginRequestByDeptHod() {
		return loginRequestByDeptHod;
	}
	public void setLoginRequestByDeptHod(String loginRequestByDeptHod) {
		this.loginRequestByDeptHod = loginRequestByDeptHod;
	}
	public String getLoginRequestByName() {
		return loginRequestByName;
	}
	public void setLoginRequestByName(String loginRequestByName) {
		this.loginRequestByName = loginRequestByName;
	}
	public String getLoginRequestByDesig() {
		return loginRequestByDesig;
	}
	public void setLoginRequestByDesig(String loginRequestByDesig) {
		this.loginRequestByDesig = loginRequestByDesig;
	}
	public String getDESIGNATED_APPROVED_BY() {
		return DESIGNATED_APPROVED_BY;
	}
	public void setDESIGNATED_APPROVED_BY(String dESIGNATED_APPROVED_BY) {
		DESIGNATED_APPROVED_BY = dESIGNATED_APPROVED_BY;
	}
	public String getReqByIsHod() {
		return reqByIsHod;
	}
	public void setReqByIsHod(String reqByIsHod) {
		this.reqByIsHod = reqByIsHod;
	}
	public String getIsUserEmp() {
		return isUserEmp;
	}
	public void setIsUserEmp(String isUserEmp) {
		this.isUserEmp = isUserEmp;
	}
	public String getRejectedRecord() {
		return rejectedRecord;
	}
	public void setRejectedRecord(String rejectedRecord) {
		this.rejectedRecord = rejectedRecord;
	}
	public String getRejectRemarks() {
		return rejectRemarks;
	}
	public void setRejectRemarks(String rejectRemarks) {
		this.rejectRemarks = rejectRemarks;
	}
	public String getIsRejected() {
		return isRejected;
	}
	public void setIsRejected(String isRejected) {
		this.isRejected = isRejected;
	}
	public String getExtendDays() {
		return extendDays;
	}
	public void setExtendDays(String extendDays) {
		this.extendDays = extendDays;
	}
	public String getExtendReason() {
		return extendReason;
	}
	public void setExtendReason(String extendReason) {
		this.extendReason = extendReason;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getHPMRNUM_UNDER_EXTENTION_SNO() {
		return HPMRNUM_UNDER_EXTENTION_SNO;
	}
	public void setHPMRNUM_UNDER_EXTENTION_SNO(
			String hPMRNUM_UNDER_EXTENTION_SNO) {
		HPMRNUM_UNDER_EXTENTION_SNO = hPMRNUM_UNDER_EXTENTION_SNO;
	}
	
	public String getRequestByNameDesig() {
		return requestByNameDesig;
	}
	public void setRequestByNameDesig(String requestByNameDesig) {
		this.requestByNameDesig = requestByNameDesig;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getReqRecordUnit() {
		return reqRecordUnit;
	}
	public void setReqRecordUnit(String reqRecordUnit) {
		this.reqRecordUnit = reqRecordUnit;
	}
	public String getReqRecordStatus() {
		return reqRecordStatus;
	}
	public void setReqRecordStatus(String reqRecordStatus) {
		this.reqRecordStatus = reqRecordStatus;
	}
	public String getDeptunitname() {
		return deptunitname;
	}
	public void setDeptunitname(String deptunitname) {
		this.deptunitname = deptunitname;
	}
	public String getAdmno() {
		return admno;
	}
	public void setAdmno(String admno) {
		this.admno = admno;
	}
}
