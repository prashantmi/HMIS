package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CertificateReceivedFB extends ActionForm
{
	private String hmode;
	private String certificateId;
	private String certificateDesc;
	private String slNo;
	private String recordType;
	private String handoverTo;
	private String isDuplicate;
	private String isReceiptTaken;
	private String recordStatus;
	private String remarks;
	private String deptUnitCode;
	private String entryDate;
	private String seatId;
	private String isValid;
	private String certificateReceivedMode;
	
	private String[] rackId;
	private String[] shelfId;
	private String[] chk;
	private String index;
	private String receiveFrom;
	

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

	public String[] getRackId() {
		return rackId;
	}

	public void setRackId(String[] rackId) {
		this.rackId = rackId;
	}

	public String[] getShelfId() {
		return shelfId;
	}

	public void setShelfId(String[] shelfId) {
		this.shelfId = shelfId;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public String getCertificateDesc() {
		return certificateDesc;
	}

	public void setCertificateDesc(String certificateDesc) {
		this.certificateDesc = certificateDesc;
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

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getCertificateReceivedMode() {
		return certificateReceivedMode;
	}

	public void setCertificateReceivedMode(String certificateReceivedMode) {
		this.certificateReceivedMode = certificateReceivedMode;
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
}
