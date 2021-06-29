package hisglobal.vo;

public class RequestPurposeMstVO extends ValueObject 
{
	private String reqPurposeId;
	private String slNo;
	private String recordType;
	private String purpose;
	private String priority;
	private String entryDate;
	private String hospitalCode;
	private String lastModifyDate;
	private String lastModifySeatId;
	private String isActive;
	
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getLastModifySeatId() {
		return lastModifySeatId;
	}
	public void setLastModifySeatId(String lastModifySeatId) {
		this.lastModifySeatId = lastModifySeatId;
	}
	public String getReqPurposeId() {
		return reqPurposeId;
	}
	public void setReqPurposeId(String reqPurposeId) {
		this.reqPurposeId = reqPurposeId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
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
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
}
