package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CaseSheetReceivedFB extends CRNoFB
{
	private String hmode;
	private String recordId;
	private String slNo;
	private String recordType;
	private String handoverTo;
	private String isDuplicate;
	private String isReceiptTaken;
	private String recordStatus;
	private String checkListremarks[];
	private String deptUnitCode;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String rackId;
	private String shelfId;
	private String[] chk;
	private String index;
	private String receiveFrom;
	private String selectCrNo;
	private String patAdmNo;
	private String enclosureNameArray;
	private String compulsoryEnclosureArray;
	private String receivedPages[];
	private String selectedCheckListId[];
	private String selectedEnclosureId[];
	private String pages;
	private String patCrNo;
	private String isAccept;
	private String returnReason;
	private int currentPage=1;
	
	

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
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

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getReceiveFrom() {
		return receiveFrom;
	}

	public void setReceiveFrom(String receiveFrom) {
		this.receiveFrom = receiveFrom;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.receiveFrom="";
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getSelectCrNo() {
		return selectCrNo;
	}

	public void setSelectCrNo(String selectCrNo) {
		this.selectCrNo = selectCrNo;
	}

	public String getPatAdmNo() {
		return patAdmNo;
	}

	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}

	public String getEnclosureNameArray() {
		return enclosureNameArray;
	}

	public void setEnclosureNameArray(String enclosureNameArray) {
		this.enclosureNameArray = enclosureNameArray;
	}

	public String getCompulsoryEnclosureArray() {
		return compulsoryEnclosureArray;
	}

	public void setCompulsoryEnclosureArray(String compulsoryEnclosureArray) {
		this.compulsoryEnclosureArray = compulsoryEnclosureArray;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	public void setRackId(String rackId) {
		this.rackId = rackId;
	}

	public String getRackId() {
		return rackId;
	}

	public String getShelfId() {
		return shelfId;
	}

	public String[] getReceivedPages() {
		return receivedPages;
	}

	public void setReceivedPages(String[] receivedPages) {
		this.receivedPages = receivedPages;
	}

	public String[] getSelectedCheckListId() {
		return selectedCheckListId;
	}

	public void setSelectedCheckListId(String[] selectedCheckListId) {
		this.selectedCheckListId = selectedCheckListId;
	}

	public String[] getSelectedEnclosureId() {
		return selectedEnclosureId;
	}

	public void setSelectedEnclosureId(String[] selectedEnclosureId) {
		this.selectedEnclosureId = selectedEnclosureId;
	}

	public String[] getCheckListremarks() {
		return checkListremarks;
	}

	public void setCheckListremarks(String[] checkListremarks) {
		this.checkListremarks = checkListremarks;
	}
}
