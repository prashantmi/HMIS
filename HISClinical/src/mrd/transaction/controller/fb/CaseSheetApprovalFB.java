package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CaseSheetApprovalFB extends CRNoFB
{
	
	private String patAdmNo;
	private String dispatchId;
	private String hmode;
	private String wardCode;
	private String departmentName;
	private String departmentUnitName;
	private String departmentCode;
	private String departmentUnitCode;
	private String recordId;
	private String recordType;
	private String approvalRemarks;
	private String selectedRecord[];
	private int currentPage=1;
	private String searchMode;
	private String selection;
	private String  strCheckMode;//added by swati sagar on date:30-04-2019
	private String  strCrNo;//added by swati sagar on date:30-04-2019
	private String isAccept;

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.departmentUnitCode="-1";
		this.wardCode="-1";
		this.approvalRemarks="";
	}
	
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
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
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentUnitName() {
		return departmentUnitName;
	}
	public void setDepartmentUnitName(String departmentUnitName) {
		this.departmentUnitName = departmentUnitName;
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
	public String getApprovalRemarks() {
		return approvalRemarks;
	}
	public void setApprovalRemarks(String approvalRemarks) {
		this.approvalRemarks = approvalRemarks;
	}
	public String[] getSelectedRecord() {
		return selectedRecord;
	}
	public void setSelectedRecord(String[] selectedRecord) {
		this.selectedRecord = selectedRecord;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getSearchMode() {
		return searchMode;
	}
	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getStrCheckMode() {
		return strCheckMode;
	}

	public void setStrCheckMode(String strCheckMode) {
		this.strCheckMode = strCheckMode;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public String getIsAccept() {
		return isAccept;
	}

	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}

	
	
	
	
}
