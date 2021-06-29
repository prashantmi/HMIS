package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CertificateAcceptFB extends ActionForm
{
	private String hmode;
	private String recordType;
	private String recordTypeName;
	private String recordStatus;
	private String mrdCode;
	private String rackId;
	private String shelfId;
	private String recordId;
	private String mrdRecordId;
	private String putBy;
	private String remarks;
	private String[] selectedRecord;
	private String acceptLostFlag;
	private String reportedBy;
	private String lastSeenDate;
	private String lastSeenTimeHr;
	private String lastSeenTimeMin;
	private String lostArea;
	private String lostType;
	private String lostRemarks;
	private String tempSelectedChk;
	private String dispatchId;
	private String index;
	private String[] enclosurePages;
	private String[] hiddenEnclosureName;
	private String[] hiddenEnclosureId;
	private String[] verifiedEnclosure;
	private String[] hiddenRecordId;
	private String[] hiddenRecordDesc;
	private String searchDate="";
	
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	private String sysDate="";
	private String rackInfo;
	private String[] hiddenByPeonPages;
	private String[] chkArchivalCheckList;
	private String[] hiddenIsComp;
	private String archivalChkValue;
	private String tempVerifiedEnclosure;
	
	public String getTempVerifiedEnclosure() {
		return tempVerifiedEnclosure;
	}
	public void setTempVerifiedEnclosure(String tempVerifiedEnclosure) {
		this.tempVerifiedEnclosure = tempVerifiedEnclosure;
	}
	public String getArchivalChkValue() {
		return archivalChkValue;
	}
	public void setArchivalChkValue(String archivalChkValue) {
		this.archivalChkValue = archivalChkValue;
	}
	public String[] getChkArchivalCheckList() {
		return chkArchivalCheckList;
	}
	public void setChkArchivalCheckList(String[] chkArchivalCheckList) {
		this.chkArchivalCheckList = chkArchivalCheckList;
	}
	public String getRackInfo() {
		return rackInfo;
	}
	public void setRackInfo(String rackInfo) {
		this.rackInfo = rackInfo;
	}
	public String getHiddenTimeHr() {
		return hiddenTimeHr;
	}
	public void setHiddenTimeHr(String hiddenTimeHr) {
		this.hiddenTimeHr = hiddenTimeHr;
	}
	public String getHiddenTimeMin() {
		return hiddenTimeMin;
	}
	public void setHiddenTimeMin(String hiddenTimeMin) {
		this.hiddenTimeMin = hiddenTimeMin;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getDispatchId() {
		return dispatchId;
	}
	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getRecordStatus() {
		return recordStatus;
	}
	public void setRecordStatus(String recordStatus) {
		this.recordStatus = recordStatus;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getRackId() {
		return rackId;
	}
	public void setRackId(String rackId) {
		this.rackId = rackId;
	}
	public String getShelfId() {
		return shelfId;
	}
	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getPutBy() {
		return putBy;
	}
	public void setPutBy(String putBy) {
		this.putBy = putBy;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String[] getSelectedRecord() {
		return selectedRecord;
	}
	public void setSelectedRecord(String[] selectedRecord) {
		this.selectedRecord = selectedRecord;
	}
	public String getAcceptLostFlag() {
		return acceptLostFlag;
	}
	public void setAcceptLostFlag(String acceptLostFlag) {
		this.acceptLostFlag = acceptLostFlag;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	public String getLastSeenDate() {
		return lastSeenDate;
	}
	public void setLastSeenDate(String lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}
	public String getLostArea() {
		return lostArea;
	}
	public void setLostArea(String lostArea) {
		this.lostArea = lostArea;
	}
	public String getLostType() {
		return lostType;
	}
	public void setLostType(String lostType) {
		this.lostType = lostType;
	}
	public String getLostRemarks() {
		return lostRemarks;
	}
	public void setLostRemarks(String lostRemarks) {
		this.lostRemarks = lostRemarks;
	}
	public String getLastSeenTimeHr() {
		return lastSeenTimeHr;
	}
	public void setLastSeenTimeHr(String lastSeenTimeHr) {
		this.lastSeenTimeHr = lastSeenTimeHr;
	}
	public String getLastSeenTimeMin() {
		return lastSeenTimeMin;
	}
	public void setLastSeenTimeMin(String lastSeenTimeMin) {
		this.lastSeenTimeMin = lastSeenTimeMin;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setRackId("");
		this.setShelfId("");
		this.setPutBy("");
		this.setReportedBy("");
		this.setLostType("");
		this.setLostArea("");
		this.setLostRemarks("");
		this.setLastSeenDate("");
		this.setLastSeenTimeHr("");
		this.setLastSeenTimeMin("");
	}
	public String getTempSelectedChk() {
		return tempSelectedChk;
	}
	public void setTempSelectedChk(String tempSelectedChk) {
		this.tempSelectedChk = tempSelectedChk;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String[] getEnclosurePages() {
		return enclosurePages;
	}
	public void setEnclosurePages(String[] enclosurePages) {
		this.enclosurePages = enclosurePages;
	}
	public String[] getHiddenEnclosureName() {
		return hiddenEnclosureName;
	}
	public void setHiddenEnclosureName(String[] hiddenEnclosureName) {
		this.hiddenEnclosureName = hiddenEnclosureName;
	}
	public String[] getHiddenEnclosureId() {
		return hiddenEnclosureId;
	}
	public void setHiddenEnclosureId(String[] hiddenEnclosureId) {
		this.hiddenEnclosureId = hiddenEnclosureId;
	}
	public String[] getVerifiedEnclosure() {
		return verifiedEnclosure;
	}
	public void setVerifiedEnclosure(String[] verifiedEnclosure) {
		this.verifiedEnclosure = verifiedEnclosure;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String[] getHiddenRecordId() {
		return hiddenRecordId;
	}
	public void setHiddenRecordId(String[] hiddenRecordId) {
		this.hiddenRecordId = hiddenRecordId;
	}
	public String[] getHiddenByPeonPages() {
		return hiddenByPeonPages;
	}
	public void setHiddenByPeonPages(String[] hiddenByPeonPages) {
		this.hiddenByPeonPages = hiddenByPeonPages;
	}
	public String[] getHiddenIsComp() {
		return hiddenIsComp;
	}
	public void setHiddenIsComp(String[] hiddenIsComp) {
		this.hiddenIsComp = hiddenIsComp;
	}
	public String[] getHiddenRecordDesc() {
		return hiddenRecordDesc;
	}
	public void setHiddenRecordDesc(String[] hiddenRecordDesc) {
		this.hiddenRecordDesc = hiddenRecordDesc;
	}
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
}
