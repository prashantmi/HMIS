package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MrdRecordMovementDetailFB extends ActionForm
{
	private String hmode;
	private String mrdRecordId;
	private String toMrdCode;
	private String toRackId;
	private String toShelfId;
	private String fromMrdCode;
	private String fromRackId;
	private String fromShelfId;
	private String entryMode;
	private String remarks;
	private String putBy;
	private String[] selectedMrdRecord;
	private String isMrdListOne;
	private String recordType;
	private String recordSelectionFlag;
	private String recordExistFlag;
	private String tempChkValue;
	private String movementOptionFlag;
	private String recordEntryDate;
	
	
	public String getRecordSelectionFlag() {
		return recordSelectionFlag;
	}
	public void setRecordSelectionFlag(String recordSelectionFlag) {
		this.recordSelectionFlag = recordSelectionFlag;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getToMrdCode() {
		return toMrdCode;
	}
	public void setToMrdCode(String toMrdCode) {
		this.toMrdCode = toMrdCode;
	}
	public String getToRackId() {
		return toRackId;
	}
	public void setToRackId(String toRackId) {
		this.toRackId = toRackId;
	}
	public String getToShelfId() {
		return toShelfId;
	}
	public void setToShelfId(String toShelfId) {
		this.toShelfId = toShelfId;
	}
	public String getFromMrdCode() {
		return fromMrdCode;
	}
	public void setFromMrdCode(String fromMrdCode) {
		this.fromMrdCode = fromMrdCode;
	}
	public String getFromRackId() {
		return fromRackId;
	}
	public void setFromRackId(String fromRackId) {
		this.fromRackId = fromRackId;
	}
	public String getFromShelfId() {
		return fromShelfId;
	}
	public void setFromShelfId(String fromShelfId) {
		this.fromShelfId = fromShelfId;
	}
	public String getEntryMode() {
		return entryMode;
	}
	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPutBy() {
		return putBy;
	}
	public void setPutBy(String putBy) {
		this.putBy = putBy;
	}
	public String[] getSelectedMrdRecord() {
		return selectedMrdRecord;
	}
	public void setSelectedMrdRecord(String[] selectedMrdRecord) {
		this.selectedMrdRecord = selectedMrdRecord;
	}
	public String getIsMrdListOne() {
		return isMrdListOne;
	}
	public void setIsMrdListOne(String isMrdListOne) {
		this.isMrdListOne = isMrdListOne;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getRecordExistFlag() {
		return recordExistFlag;
	}
	public void setRecordExistFlag(String recordExistFlag) {
		this.recordExistFlag = recordExistFlag;
	}
	public String getTempChkValue() {
		return tempChkValue;
	}
	public void setTempChkValue(String tempChkValue) {
		this.tempChkValue = tempChkValue;
	}
	public String getMovementOptionFlag() {
		return movementOptionFlag;
	}
	public void setMovementOptionFlag(String movementOptionFlag) {
		this.movementOptionFlag = movementOptionFlag;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setFromMrdCode("");
		this.setFromRackId("");
		this.setFromShelfId("-1");
		this.setToMrdCode("");
		this.setToRackId("");
		this.setToShelfId("");
		this.setTempChkValue("");
		this.setPutBy("");
		this.setRemarks("");
	}
	public String getRecordEntryDate() {
		return recordEntryDate;
	}
	public void setRecordEntryDate(String recordEntryDate) {
		this.recordEntryDate = recordEntryDate;
	}
	
	
}
