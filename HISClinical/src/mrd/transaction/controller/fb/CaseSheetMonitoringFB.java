package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CaseSheetMonitoringFB extends CRNoFB
{
	
	private String patCrNo;
	private String patName;
	private String patAdmNo;
	private String admDateTime;
	private String admStatusCode;
	private String hmode;
	private String wardCode;
	private String bedCode;
	private String wardName;
	private String bedName;
	private String roomCode;
	private String departmentCode;
	private String departmentUnitCode;
	private String startIdx;
	private String endIdx;
	private int currentPage=1;
	private String disDateTime;
	private String selection;
	private String caseSheetStatus;
	private String caseSheetId;
	private String isDuplicate;
	private String selectCRNo;
	private String updateType;
	private String remarks;
	
	//lostfound
	private String reportedBy;
	private String lostDate;
	private String lostTimeHr;
	private String lostTimeMin;
	private String lostType;
	private String lastSeenDate;
	private String lastSeenTimeHr;
	private String lastSeenTimeMin;
	private String lostArea;
	private String lostRemarks;
	private String foundBy;
	private String foundDate;
	private String foundTimeHr;
	private String foundTimeMin;
	private String foundRemarks;
	private String cancelRemarks;
	
	//
	private String lostLabel;
	private String foundLabel;
	private String duplicateLabel;
	private String removeLabel;
	private String searchMode;
	private String sysDate;
	private String hideDuplicateLabel;
	private String hiddenTimeHr;
	private String hiddenTimeMin;
	
	private String creationDate;
		
	public String getReportedBy() {
		return reportedBy;
	}
	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}
	public String getLostDate() {
		return lostDate;
	}
	public void setLostDate(String lostDate) {
		this.lostDate = lostDate;
	}
	public String getLostType() {
		return lostType;
	}
	public void setLostType(String lostType) {
		this.lostType = lostType;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getAdmDateTime() {
		return admDateTime;
	}
	public void setAdmDateTime(String admDateTime) {
		this.admDateTime = admDateTime;
	}
	public String getAdmStatusCode() {
		return admStatusCode;
	}
	public void setAdmStatusCode(String admStatusCode) {
		this.admStatusCode = admStatusCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getBedCode() {
		return bedCode;
	}
	public void setBedCode(String bedCode) {
		this.bedCode = bedCode;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getBedName() {
		return bedName;
	}
	public void setBedName(String bedName) {
		this.bedName = bedName;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
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
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getDisDateTime() {
		return disDateTime;
	}
	public void setDisDateTime(String disDateTime) {
		this.disDateTime = disDateTime;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public String getCaseSheetStatus() {
		return caseSheetStatus;
	}
	public void setCaseSheetStatus(String caseSheetStatus) {
		this.caseSheetStatus = caseSheetStatus;
	}
	public String getIsDuplicate() {
		return isDuplicate;
	}
	public void setIsDuplicate(String isDuplicate) {
		this.isDuplicate = isDuplicate;
	}
	public String getSelectCRNo() {
		return selectCRNo;
	}
	public void setSelectCRNo(String selectCRNo) {
		this.selectCRNo = selectCRNo;
	}
	public String getUpdateType() {
		return updateType;
	}
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	public String getLostRemarks() {
		return lostRemarks;
	}
	public void setLostRemarks(String lostRemarks) {
		this.lostRemarks = lostRemarks;
	}
	public String getCaseSheetId() {
		return caseSheetId;
	}
	public void setCaseSheetId(String caseSheetId) {
		this.caseSheetId = caseSheetId;
	}
	public String getLostTimeHr() {
		return lostTimeHr;
	}
	public void setLostTimeHr(String lostTimeHr) {
		this.lostTimeHr = lostTimeHr;
	}
	public String getLostTimeMin() {
		return lostTimeMin;
	}
	public void setLostTimeMin(String lostTimeMin) {
		this.lostTimeMin = lostTimeMin;
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
	public String getFoundBy() {
		return foundBy;
	}
	public void setFoundBy(String foundBy) {
		this.foundBy = foundBy;
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
	public String getFoundRemarks() {
		return foundRemarks;
	}
	public void setFoundRemarks(String foundRemarks) {
		this.foundRemarks = foundRemarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLostLabel() {
		return lostLabel;
	}
	public void setLostLabel(String lostLabel) {
		this.lostLabel = lostLabel;
	}
	public String getFoundLabel() {
		return foundLabel;
	}
	public void setFoundLabel(String foundLabel) {
		this.foundLabel = foundLabel;
	}
	public String getDuplicateLabel() {
		return duplicateLabel;
	}
	public void setDuplicateLabel(String duplicateLabel) {
		this.duplicateLabel = duplicateLabel;
	}
	public String getRemoveLabel() {
		return removeLabel;
	}
	public void setRemoveLabel(String removeLabel) {
		this.removeLabel = removeLabel;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getCancelRemarks() {
		return cancelRemarks;
	}
	public void setCancelRemarks(String cancelRemarks) {
		this.cancelRemarks = cancelRemarks;
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
	public String getHideDuplicateLabel() {
		return hideDuplicateLabel;
	}
	public void setHideDuplicateLabel(String hideDuplicateLabel) {
		this.hideDuplicateLabel = hideDuplicateLabel;
	}
	
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.patCrNo="";
	}
	/**
	 * @return the creationDate
	 */
	public String getCreationDate()
	{
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(String creationDate)
	{
		this.creationDate = creationDate;
	}
}
