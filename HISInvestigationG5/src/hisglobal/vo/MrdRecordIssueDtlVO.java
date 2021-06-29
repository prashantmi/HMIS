package hisglobal.vo;

public class MrdRecordIssueDtlVO extends ValueObject
{
	private String requestId;
	private String mrdRecordId;
	private String recordType;
	private String recordTypeName;
	private String issueDate;
	private String remarks;
	private String mrdCode;
	private String handoverTo;
	private String handoverToName;
	private String expectedReturnDate;
	private String issuedFor;
	private String hospitalCode;
	private String requestBy;
	
	private String patCrNo;
	private String patAdmNo;
	private String patName;
	private String rackId;
	private String shelfName;
	private String shelfId;
	private String rackLabel;
	private String rackLocation;
	private String fileNo;
	
	
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getRackLabel() {
		return rackLabel;
	}
	public void setRackLabel(String rackLabel) {
		this.rackLabel = rackLabel;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
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
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getHandoverTo() {
		return handoverTo;
	}
	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}
	public String getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(String expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssuedFor() {
		return issuedFor;
	}
	public void setIssuedFor(String issuedFor) {
		this.issuedFor = issuedFor;
	}
	public String getHandoverToName() {
		return handoverToName;
	}
	public void setHandoverToName(String handoverToName) {
		this.handoverToName = handoverToName;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String getRackLocation() {
		return rackLocation;
	}
	public void setRackLocation(String rackLocation) {
		this.rackLocation = rackLocation;
	}
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public String getShelfId() {
		return shelfId;
	}
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	
}
