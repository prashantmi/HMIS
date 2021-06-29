package hisglobal.vo;

public class BirthDeathUploadDtlVO extends ValueObject
{
	private String registrtionNo;
	private String remarks;
	private String relativeName;
	private String relativeAddress;
	private String relativeCode;
	private String entryMode;
	private String handoverDateTime;
	private String recordType;
	private String uploadDateTime;
	private String patCrNo;
	private String isHandoverTo;
	
	
	public String getIsHandoverTo() {
		return isHandoverTo;
	}
	public void setIsHandoverTo(String isHandoverTo) {
		this.isHandoverTo = isHandoverTo;
	}
	public String getRegistrtionNo() {
		return registrtionNo;
	}
	public void setRegistrtionNo(String registrtionNo) {
		this.registrtionNo = registrtionNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativeAddress() {
		return relativeAddress;
	}
	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}
	public String getHandoverDateTime() {
		return handoverDateTime;
	}
	public void setHandoverDateTime(String handoverDateTime) {
		this.handoverDateTime = handoverDateTime;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getUploadDateTime() {
		return uploadDateTime;
	}
	public void setUploadDateTime(String uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
}
