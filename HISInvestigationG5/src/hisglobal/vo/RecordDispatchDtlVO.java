package hisglobal.vo;

public class RecordDispatchDtlVO extends ValueObject
{
	private String recordId;
	private String dispatchId;
	private String recordDesc;
	private String slNo;
	private String recordType;
	private String recordTypeName;
	private String deptUnitCode;
	private String deptCode;
	private String remarks;
	private String senderSeatId;
	private String wardCode;
	private String ward;
	private String senderDate;
	private String recipientSeatId;
	private String recipientDate;
	private String delayReason;
	private String receiveFrom;
	private String senderEmpNo;
	private String isValid;
	private String seatId;
	private String recordPages;
	private String approvedEmpNo;
	private String handoverTo;
	private String approvedDate;
	private String approvedSeatId;
	private String approvedRemarks;
	private String recipentEmpNo;
	private String verifyEmpNo;
	private String recordStatus;
	private String verifyDate;
	private String verifySeatId;
	private String returnReason;
	private String entryDate;
	private String entryMode;
	private String isDuplicate;
	private String isDuplicateName;
	private String departmentName;
	private String departmentUnitName;
	private String wardName;
	private String senderName;
	private String recipentName;
	
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String patAdmNo;
	private String patName;

	
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
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
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
	public String getDispatchId() {
		return dispatchId;
	}
	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getDelayReason() {
		return delayReason;
	}
	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}
	public String getSenderEmpNo() {
		return senderEmpNo;
	}
	public void setSenderEmpNo(String senderEmpNo) {
		this.senderEmpNo = senderEmpNo;
	}
	public String getRecordPages() {
		return recordPages;
	}
	public void setRecordPages(String recordPages) {
		this.recordPages = recordPages;
	}
	public String getApprovedEmpNo() {
		return approvedEmpNo;
	}
	public void setApprovedEmpNo(String approvedEmpNo) {
		this.approvedEmpNo = approvedEmpNo;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApprovedSeatId() {
		return approvedSeatId;
	}
	public void setApprovedSeatId(String approvedSeatId) {
		this.approvedSeatId = approvedSeatId;
	}
	public String getRecipentEmpNo() {
		return recipentEmpNo;
	}
	public void setRecipentEmpNo(String recipentEmpNo) {
		this.recipentEmpNo = recipentEmpNo;
	}
	public String getVerifyEmpNo() {
		return verifyEmpNo;
	}
	public void setVerifyEmpNo(String verifyEmpNo) {
		this.verifyEmpNo = verifyEmpNo;
	}
	public String getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getVerifySeatId() {
		return verifySeatId;
	}
	public void setVerifySeatId(String verifySeatId) {
		this.verifySeatId = verifySeatId;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
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
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getRecipentName() {
		return recipentName;
	}
	public void setRecipentName(String recipentName) {
		this.recipentName = recipentName;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getApprovedRemarks() {
		return approvedRemarks;
	}
	public void setApprovedRemarks(String approvedRemarks) {
		this.approvedRemarks = approvedRemarks;
	}
	
	
}
