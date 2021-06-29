package hisglobal.vo;

public class CertificateIssueDtlVO extends ValueObject
{
	private String certificateId;
	private String certificateDesc;
	private String slNo;
	private String recordType;
	private String recordTypeName;
	private String handoverTo;
	private String isDuplicate;
	private String isDuplicateName;
	private String isReceiptTaken;
	private String recordStatus;
	private String remarks;
	private String deptUnitCode;
	private String deptUnitName;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String rackId;
	private String selfName;
	
	private String patCrNo;
	private String patAdmNo;
	private String dispatchId;
	private String returnReason;
	
	
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
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
	public String getSelfName() {
		return selfName;
	}
	public void setSelfName(String selfName) {
		this.selfName = selfName;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
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
	public String getHandoverTo() {
		return handoverTo;
	}
	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}
	public String getIsDuplicate() {
		return isDuplicate;
	}
	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	public String getIsReceiptTaken() {
		return isReceiptTaken;
	}
	public void setIsReceiptTaken(String isReceiptTaken) {
		this.isReceiptTaken = isReceiptTaken;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getCertificateDesc() {
		return certificateDesc;
	}
	public void setCertificateDesc(String certificateDesc) {
		this.certificateDesc = certificateDesc;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String getIsDuplicateName() {
		return isDuplicateName;
	}
	public void setIsDuplicateName(String isDuplicateName) {
		this.isDuplicateName = isDuplicateName;
	}
	public String getDeptUnitName() {
		return deptUnitName;
	}
	public void setDeptUnitName(String deptUnitName) {
		this.deptUnitName = deptUnitName;
	}
	public String getDispatchId() {
		return dispatchId;
	}
	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}
}
