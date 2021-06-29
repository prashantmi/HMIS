package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class PatientDischargePrintingFB extends CRNoFB
{
	
	private String patCrNo;
	private String patName;
	private String patAdmNo;
	private String hmode;
	private String wardCode;
	private String departmentCode;
	private String departmentUnitCode;
	private String startIdx;
	private String endIdx;
	private int currentPage=1;
	private String disDateTime;
	private String selection;
	private String isDuplicate;
	private String selectCRNo;
	private String updateType;
	private String remarks;
	private String profileId;
	private String profileStatus;
	private String hiddenDeptUnitCode;
	private String hiddenWardCode;
	
	//handover
	private String handOverTo;
	private String handOverDate;
	private String handOverHr;
	private String handOverMin;
	private String officerName;
	private String officerDesign;
	private String batchNo;
	private String relativeName;
	private String relativeAddress;
	private String relativeCode;
	private String isReceiptTaken;
	private String searchMode;
	private String hideDuplicateLabel;
	private String isMlc;
	private String isDead;
	
	
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
	

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.patCrNo="";
		this.relativeCode="-1";
		this.relativeName="";
		this.relativeAddress="";
		this.officerName="";
		this.officerDesign="";
		this.batchNo="";
		this.isReceiptTaken="";
		this.remarks="";
		this.departmentUnitCode="-1";
		this.wardCode="-1";
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getHandOverTo() {
		return handOverTo;
	}
	public void setHandOverTo(String handOverTo) {
		this.handOverTo = handOverTo;
	}
	public String getHandOverDate() {
		return handOverDate;
	}
	public void setHandOverDate(String handOverDate) {
		this.handOverDate = handOverDate;
	}
	public String getHandOverHr() {
		return handOverHr;
	}
	public void setHandOverHr(String handOverHr) {
		this.handOverHr = handOverHr;
	}
	public String getHandOverMin() {
		return handOverMin;
	}
	public void setHandOverMin(String handOverMin) {
		this.handOverMin = handOverMin;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getOfficerDesign() {
		return officerDesign;
	}
	public void setOfficerDesign(String officerDesign) {
		this.officerDesign = officerDesign;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelativeAddress() {
		return relativeAddress;
	}
	public void setRelativeAddress(String relativeAddress) {
		this.relativeAddress = relativeAddress;
	}
	public String getRelativeCode() {
		return relativeCode;
	}
	public void setRelativeCode(String relativeCode) {
		this.relativeCode = relativeCode;
	}
	public String getIsReceiptTaken() {
		return isReceiptTaken;
	}
	public void setIsReceiptTaken(String isReceiptTaken) {
		this.isReceiptTaken = isReceiptTaken;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getHideDuplicateLabel() {
		return hideDuplicateLabel;
	}
	public void setHideDuplicateLabel(String hideDuplicateLabel) {
		this.hideDuplicateLabel = hideDuplicateLabel;
	}

	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}
	public String getIsMlc() {
		return isMlc;
	}
	public void setIsMlc(String isMlc) {
		this.isMlc = isMlc;
	}
	public String getIsDead() {
		return isDead;
	}
	public void setIsDead(String isDead) {
		this.isDead = isDead;
	}
	public String getHiddenDeptUnitCode() {
		return hiddenDeptUnitCode;
	}
	public void setHiddenDeptUnitCode(String hiddenDeptUnitCode) {
		this.hiddenDeptUnitCode = hiddenDeptUnitCode;
	}
	public String getHiddenWardCode() {
		return hiddenWardCode;
	}
	public void setHiddenWardCode(String hiddenWardCode) {
		this.hiddenWardCode = hiddenWardCode;
	}
	
	
	
	
}
