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
}
