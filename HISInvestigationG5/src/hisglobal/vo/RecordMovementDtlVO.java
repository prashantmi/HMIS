package hisglobal.vo;

public class RecordMovementDtlVO extends ValueObject
{
	private String recordId;
	private String recordDesc;
	private String slNo;
	private String recordType;
	private String recordTypeName;
	private String deptUnitCode;
	private String remarks;
	private String senderSeatId;
	private String senderDate;
	private String recipientSeatId;
	private String recipientDate;
	private String receiveFrom;
	private String recordStatus;
	private String returnReason;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String handoverTo;
	private String isDuplicate;
	private String isDuplicateName;
	
	
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
	public String getIsDuplicateName() {
		return isDuplicateName;
	}
	public void setIsDuplicateName(String isDuplicateName) {
		this.isDuplicateName = isDuplicateName;
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
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getRecordDesc() {
		return recordDesc;
	}
	public void setRecordDesc(String recordDesc) {
		this.recordDesc = recordDesc;
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
	public String getDeptUnitCode() {
		return deptUnitCode;
	}
	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSenderSeatId() {
		return senderSeatId;
	}
	public void setSenderSeatId(String senderSeatId) {
		this.senderSeatId = senderSeatId;
	}
	public String getSenderDate() {
		return senderDate;
	}
	public void setSenderDate(String senderDate) {
		this.senderDate = senderDate;
	}
	public String getRecipientSeatId() {
		return recipientSeatId;
	}
	public void setRecipientSeatId(String recipientSeatId) {
		this.recipientSeatId = recipientSeatId;
	}
	public String getRecipientDate() {
		return recipientDate;
	}
	public void setRecipientDate(String recipientDate) {
		this.recipientDate = recipientDate;
	}
	public String getReceiveFrom() {
		return receiveFrom;
	}
	public void setReceiveFrom(String receiveFrom) {
		this.receiveFrom = receiveFrom;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	
	
}
