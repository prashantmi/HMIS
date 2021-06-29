package mrd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class RecordFoundInMrdFB extends ActionForm
{
	private String mrdRecordId;
	private String slNo;
	private String lostDateTime;
	private String reportedBy;
	private String recordId;
	private String recordDesc;
	private String hiddenRecordId;
	private String lastSeenDateTime;
	private String lastSeenDate;
	private String lastSeenTimeHr;
	private String lastSeenTimeMin;
	private String lostArea;
	private String lostType;
	private String lostRemarks;
	private String foundDate="";
	private String foundTimeHr;
	private String foundTimeMin;
	private String foundRemarks;
	private String foundBy;
	private String hmode;
	private String recAcceptArchivedFlag;
	private String changeArchivedFlag;
	private String putBy;
	private String rackId;
	private String shelfId;
	private String rackInfo;
	private String recordType;
	private String mrdCode;
	private String selectedMrdRecordId;
	private String prevLocation;
	private String prevLocationCode;
	private String isPrevLocationExist;
	
	
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	private String sysDate="";
	
	private int currentPage;
	private String empId;
	
	public RecordFoundInMrdFB()
	{
		this.currentPage=1;
		
	}
	
	
	public String getMrdRecordId() {
		return mrdRecordId;
	}
	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getLostDateTime() {
		return lostDateTime;
	}
	public void setLostDateTime(String lostDateTime) {
		this.lostDateTime = lostDateTime;
	}
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
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
	public String getHiddenRecordId() {
		return hiddenRecordId;
	}
	public void setHiddenRecordId(String hiddenRecordId) {
		this.hiddenRecordId = hiddenRecordId;
	}
	public String getLastSeenDateTime() {
		return lastSeenDateTime;
	}
	public void setLastSeenDateTime(String lastSeenDateTime) {
		this.lastSeenDateTime = lastSeenDateTime;
	}
	public String getLastSeenDate() {
		return lastSeenDate;
	}
	public void setLastSeenDate(String lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getRecAcceptArchivedFlag() {
		return recAcceptArchivedFlag;
	}
	public void setRecAcceptArchivedFlag(String recAcceptArchivedFlag) {
		this.recAcceptArchivedFlag = recAcceptArchivedFlag;
	}
	public String getChangeArchivedFlag() {
		return changeArchivedFlag;
	}
	public void setChangeArchivedFlag(String changeArchivedFlag) {
		this.changeArchivedFlag = changeArchivedFlag;
	}
	public String getPutBy() {
		return putBy;
	}
	public void setPutBy(String putBy) {
		this.putBy = putBy;
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
	public String getRackInfo() {
		return rackInfo;
	}
	public void setRackInfo(String rackInfo) {
		this.rackInfo = rackInfo;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getSelectedMrdRecordId() {
		return selectedMrdRecordId;
	}
	public void setSelectedMrdRecordId(String selectedMrdRecordId) {
		this.selectedMrdRecordId = selectedMrdRecordId;
	}
	public String getPrevLocation() {
		return prevLocation;
	}
	public void setPrevLocation(String prevLocation) {
		this.prevLocation = prevLocation;
	}
	public String getIsPrevLocationExist() {
		return isPrevLocationExist;
	}
	public void setIsPrevLocationExist(String isPrevLocationExist) {
		this.isPrevLocationExist = isPrevLocationExist;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getPrevLocationCode() {
		return prevLocationCode;
	}


	public void setPrevLocationCode(String prevLocationCode) {
		this.prevLocationCode = prevLocationCode;
	}


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}
}
