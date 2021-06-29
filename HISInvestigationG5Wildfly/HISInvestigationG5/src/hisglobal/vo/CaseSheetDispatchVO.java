package hisglobal.vo;

public class CaseSheetDispatchVO extends ValueObject
{
	private String dispatchId;
	private String recordId;
	private String recordDesc;
	//private String slNo;
	private String recordType;
	private String deptUnitCode;
	private String remarks;
	private String senderSeatId;
	private String senderDate;
	private String recipientSeatId;
	private String recipientDate;
	private String receiveFrom;
	private String recordStatus;
	private String returnReason;
	private String selectType;
	private String patCrNo;
	private String unitCode;
	private String tempMode;
	private String startIdx;
	private String endIdx;
	private String handoverTo;
	private String departmentCode;
	private String department;
	private String departmentUnit;
	
	private String enclosurePages;
	private String pages;
	
	private String unitList;
	private String roomList;
	
	private String departmentUnitCode;
	private String wardCode;
	private String ward;
	private String roomCode;
	
	private String selectCRNo;
	
	private String patAdmNo;
	
	//
	private String selectedAdmnNo;
	private String selectedCheckListId;
	private String selectedEnclosureId;
	
	private String enclosureArray[];
	private String checklistArray[];
	private String remarksArray[];
	private String pagesArray[];
	private String isDuplicate;
	private String verifiedByMrd;
	private String caseSheetId;
	
	

	public String getCaseSheetId() {
		return caseSheetId;
	}

	public void setCaseSheetId(String caseSheetId) {
		this.caseSheetId = caseSheetId;
	}

	public String getIsDuplicate() {
		return isDuplicate;
	}

	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
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

	public String getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
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

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getTempMode() {
		return tempMode;
	}

	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	public String getStartIdx() {
		return startIdx;
	}

	public void setStartIdx(String startIdx) {
		this.startIdx = startIdx;
	}

	public String getEndIdx() {
		return endIdx;
	}

	public void setEndIdx(String endIdx) {
		this.endIdx = endIdx;
	}

	public String getEnclosurePages() {
		return enclosurePages;
	}

	public void setEnclosurePages(String enclosurePages) {
		this.enclosurePages = enclosurePages;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getUnitList() {
		return unitList;
	}

	public void setUnitList(String unitList) {
		this.unitList = unitList;
	}

	public String getRoomList() {
		return roomList;
	}

	public void setRoomList(String roomList) {
		this.roomList = roomList;
	}

	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getSelectCRNo() {
		return selectCRNo;
	}

	public void setSelectCRNo(String selectCRNo) {
		this.selectCRNo = selectCRNo;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getSelectedAdmnNo() {
		return selectedAdmnNo;
	}

	public void setSelectedAdmnNo(String selectedAdmnNo) {
		this.selectedAdmnNo = selectedAdmnNo;
	}

	public String getSelectedCheckListId() {
		return selectedCheckListId;
	}

	public void setSelectedCheckListId(String selectedCheckListId) {
		this.selectedCheckListId = selectedCheckListId;
	}

	public String getSelectedEnclosureId() {
		return selectedEnclosureId;
	}

	public void setSelectedEnclosureId(String selectedEnclosureId) {
		this.selectedEnclosureId = selectedEnclosureId;
	}

	public String[] getEnclosureArray() {
		return enclosureArray;
	}

	public void setEnclosureArray(String[] enclosureArray) {
		this.enclosureArray = enclosureArray;
	}

	public String[] getChecklistArray() {
		return checklistArray;
	}

	public void setChecklistArray(String[] checklistArray) {
		this.checklistArray = checklistArray;
	}

	public String[] getRemarksArray() {
		return remarksArray;
	}

	public void setRemarksArray(String[] remarksArray) {
		this.remarksArray = remarksArray;
	}

	public String[] getPagesArray() {
		return pagesArray;
	}

	public void setPagesArray(String[] pagesArray) {
		this.pagesArray = pagesArray;
	}

	public String getHandoverTo() {
		return handoverTo;
	}

	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentUnit() {
		return departmentUnit;
	}

	public void setDepartmentUnit(String departmentUnit) {
		this.departmentUnit = departmentUnit;
	}

	public String getVerifiedByMrd() {
		return verifiedByMrd;
	}

	public void setVerifiedByMrd(String verifiedByMrd) {
		this.verifiedByMrd = verifiedByMrd;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}


	}
