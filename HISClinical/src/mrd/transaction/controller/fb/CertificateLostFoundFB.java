package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class CertificateLostFoundFB extends ActionForm
{
	private String hmode;
	private String foundDate="";
	private String foundTimeHr;
	private String foundTimeMin;
	private String foundRemarks;
	private String foundBy;
	private String mrdCode;
	private String rackId;
	private String shelfId;
	private String recordId;
	private String recordType;
	private String recordTypeName;
	private String[] selectedRecord;
	private String tempSelectedChk;
	private String tempVerifiedEnclosure;
	private String sysDate="";
	private String putBy;
	
	
	private String[] verifiedEnclosure;
	private String index;
	private String[] enclosurePages;
	private String[] hiddenEnclosureName;
	private String[] hiddenEnclosureId;
	private String dispatchId;
	private String[] hiddenRecordId;
	private String[] hiddenRecordDesc;
	
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	private String rackInfo;
	
	
	public String getRackInfo() {
		return rackInfo;
	}
	public void setRackInfo(String rackInfo) {
		this.rackInfo = rackInfo;
	}
	public String[] getHiddenRecordId() {
		return hiddenRecordId;
	}
	public void setHiddenRecordId(String[] hiddenRecordId) {
		this.hiddenRecordId = hiddenRecordId;
	}
	public String getDispatchId() {
		return dispatchId;
	}
	public void setDispatchId(String dispatchId) {
		this.dispatchId = dispatchId;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getFoundRemarks() {
		return foundRemarks;
	}
	public void setFoundRemarks(String foundRemarks) {
		this.foundRemarks = foundRemarks;
	}
	public String getFoundBy() {
		return foundBy;
	}
	public void setFoundBy(String foundBy) {
		this.foundBy = foundBy;
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
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getFoundDate() {
		return foundDate;
	}
	public void setFoundDate(String foundDate) {
		this.foundDate = foundDate;
	}
	public String getFoundTimeHr() {
		return foundTimeHr;
	}
	public void setFoundTimeHr(String foundTimeHr) {
		this.foundTimeHr = foundTimeHr;
	}
	public String getFoundTimeMin() {
		return foundTimeMin;
	}
	public void setFoundTimeMin(String foundTimeMin) {
		this.foundTimeMin = foundTimeMin;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setFoundBy("");
		this.setFoundDate("");
		this.setFoundRemarks("");
		this.setFoundTimeHr("");
		this.setFoundTimeMin("");
		this.setRackId("");
		this.setShelfId("");
	}
	public String[] getSelectedRecord() {
		return selectedRecord;
	}
	public void setSelectedRecord(String[] selectedRecord) {
		this.selectedRecord = selectedRecord;
	}
	public String getTempSelectedChk() {
		return tempSelectedChk;
	}
	public void setTempSelectedChk(String tempSelectedChk) {
		this.tempSelectedChk = tempSelectedChk;
	}
	public String getRecordTypeName() {
		return recordTypeName;
	}
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}
	public String getTempVerifiedEnclosure() {
		return tempVerifiedEnclosure;
	}
	public void setTempVerifiedEnclosure(String tempVerifiedEnclosure) {
		this.tempVerifiedEnclosure = tempVerifiedEnclosure;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
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
	public String getPutBy() {
		return putBy;
	}
	public void setPutBy(String putBy) {
		this.putBy = putBy;
	}
	public String[] getHiddenRecordDesc() {
		return hiddenRecordDesc;
	}
	public void setHiddenRecordDesc(String[] hiddenRecordDesc) {
		this.hiddenRecordDesc = hiddenRecordDesc;
	}
}
