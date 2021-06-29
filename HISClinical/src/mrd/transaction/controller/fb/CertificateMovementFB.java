package mrd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class CertificateMovementFB extends ActionForm
{
	private String recordId;
	private String recordDesc;
	private String slNo;
	private String recordType;
	private String deptUnitCode;
	private String remarks;
	private String senderSeatId;
	private String senderDate;
	private String recipientSeatId;
	private String recipientDate;
	private String recipientEmpNo;
	private String receiveFrom;
	private String recordStatus;
	private String returnReason;
	private String hmode;
	private String chk[];
	private String selectType;
	private String patCrNo;
	private String unitCode;
	private String tempMode;
	private String startIdx;
	private String endIdx;
	
	private String[] selectedCheckList;
	private String[] isCompulsory;
	private String compCheckListId;
	private String tempChkValue;
	private String acceptRejectFlag;
	private String[] checkListRemarks;
	
	private int currentPage;
	
	public CertificateMovementFB()
	{
		this.currentPage=1;
		this.checkListRemarks=null;
	}
	
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getTempMode() {
		return tempMode;
	}
	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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

	public String[] getSelectedCheckList() {
		return selectedCheckList;
	}

	public void setSelectedCheckList(String[] selectedCheckList) {
		this.selectedCheckList = selectedCheckList;
	}

	public String[] getIsCompulsory() {
		return isCompulsory;
	}

	public void setIsCompulsory(String[] isCompulsory) {
		this.isCompulsory = isCompulsory;
	}

	public String getCompCheckListId() {
		return compCheckListId;
	}

	public void setCompCheckListId(String compCheckListId) {
		this.compCheckListId = compCheckListId;
	}

	public String getTempChkValue() {
		return tempChkValue;
	}

	public void setTempChkValue(String tempChkValue) {
		this.tempChkValue = tempChkValue;
	}

	public String getAcceptRejectFlag() {
		return acceptRejectFlag;
	}

	public void setAcceptRejectFlag(String acceptRejectFlag) {
		this.acceptRejectFlag = acceptRejectFlag;
	}

	public String getRecipientEmpNo() {
		return recipientEmpNo;
	}

	public void setRecipientEmpNo(String recipientEmpNo) {
		this.recipientEmpNo = recipientEmpNo;
	}

	public String[] getCheckListRemarks() {
		return checkListRemarks;
	}

	public void setCheckListRemarks(String[] checkListRemarks) {
		this.checkListRemarks = checkListRemarks;
	}

	
	
	
}
