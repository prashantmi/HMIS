package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OfflineMrdRecordReqDtlFB extends ActionForm
{
	private String hmode;
	private String reqPurposeId;
	private String mrdCode;
	private String recordType;
	private String remarks;
	private String reqDate;
	private String reqStatus;
	private String reqMode;
	private String forDays;
	private String issueUpto;
	private String requestById;
	private String requestByName;
	private String cancelReason;
	private String approvedBy;
	private String isMrdLengthOne;
	private String isRecordRequested;
	private String reqRemarks[];
	private String userEmpId;
	private String ducRecordId;
	private String[] searchRecordId;
	private String isReqOnlineOffline;
	
	///Search
	private String hrgnum_puk;
	private String hrgstr_fname;
	private String hrgstr_mname;
	private String hrgstr_lname;
	private String hipnum_admno;
	private String hrgnum_mlc_no;
	private String hipdt_disstatus_code;
	private String hrgnum_isunknown;
	private String hrgnum_is_broughtdead;
	private String fromDate="";
	private String toDate="";
	private String deathCase;
	private String gnum_gender_code;
	private String hrgnum_name;
	private String selectedIndex;
	private String[] chkSelectRecord;
	private String concatedIndex;
	
	private String recordIdToRemove;
	private String requestByFlag;
	private String  requestByExternalName;
	private String deptname;
	private String deptunitname;
	private String hodName;
	private String requestByEmpDept;
	private String hiddenDeptName;
	private String sheetDept;
	private String employeeId;//By Sandip on 18.08.2017
	
	public String getConcatedIndex() {
		return concatedIndex;
	}
	public void setConcatedIndex(String concatedIndex) {
		this.concatedIndex = concatedIndex;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getReqPurposeId() {
		return reqPurposeId;
	}
	public void setReqPurposeId(String reqPurposeId) {
		this.reqPurposeId = reqPurposeId;
	}
	public String getMrdCode() {
		return mrdCode;
	}
	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getReqMode() {
		return reqMode;
	}
	public void setReqMode(String reqMode) {
		this.reqMode = reqMode;
	}
	public String getForDays() {
		return forDays;
	}
	public void setForDays(String forDays) {
		this.forDays = forDays;
	}
	public String getIssueUpto() {
		return issueUpto;
	}
	public void setIssueUpto(String issueUpto) {
		this.issueUpto = issueUpto;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getIsMrdLengthOne() {
		return isMrdLengthOne;
	}
	public void setIsMrdLengthOne(String isMrdLengthOne) {
		this.isMrdLengthOne = isMrdLengthOne;
	}
	public String getRequestById() {
		return requestById;
	}
	public void setRequestById(String requestById) {
		this.requestById = requestById;
	}
	public String getRequestByName() {
		return requestByName;
	}
	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}
	public String getHrgnum_puk() {
		return hrgnum_puk;
	}
	public void setHrgnum_puk(String hrgnum_puk) {
		this.hrgnum_puk = hrgnum_puk;
	}
	public String getHrgstr_fname() {
		return hrgstr_fname;
	}
	public void setHrgstr_fname(String hrgstr_fname) {
		this.hrgstr_fname = hrgstr_fname;
	}
	public String getHrgstr_mname() {
		return hrgstr_mname;
	}
	public void setHrgstr_mname(String hrgstr_mname) {
		this.hrgstr_mname = hrgstr_mname;
	}
	public String getHrgstr_lname() {
		return hrgstr_lname;
	}
	public void setHrgstr_lname(String hrgstr_lname) {
		this.hrgstr_lname = hrgstr_lname;
	}
	public String getHipnum_admno() {
		return hipnum_admno;
	}
	public void setHipnum_admno(String hipnum_admno) {
		this.hipnum_admno = hipnum_admno;
	}
	public String getHrgnum_mlc_no() {
		return hrgnum_mlc_no;
	}
	public void setHrgnum_mlc_no(String hrgnum_mlc_no) {
		this.hrgnum_mlc_no = hrgnum_mlc_no;
	}
	public String getHipdt_disstatus_code() {
		return hipdt_disstatus_code;
	}
	public void setHipdt_disstatus_code(String hipdt_disstatus_code) {
		this.hipdt_disstatus_code = hipdt_disstatus_code;
	}
	public String getHrgnum_isunknown() {
		return hrgnum_isunknown;
	}
	public void setHrgnum_isunknown(String hrgnum_isunknown) {
		this.hrgnum_isunknown = hrgnum_isunknown;
	}
	public String getHrgnum_is_broughtdead() {
		return hrgnum_is_broughtdead;
	}
	public void setHrgnum_is_broughtdead(String hrgnum_is_broughtdead) {
		this.hrgnum_is_broughtdead = hrgnum_is_broughtdead;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDeathCase() {
		return deathCase;
	}
	public void setDeathCase(String deathCase) {
		this.deathCase = deathCase;
	}
	public String getGnum_gender_code() {
		return gnum_gender_code;
	}
	public void setGnum_gender_code(String gnum_gender_code) {
		this.gnum_gender_code = gnum_gender_code;
	}
	public String getHrgnum_name() {
		return hrgnum_name;
	}
	public void setHrgnum_name(String hrgnum_name) {
		this.hrgnum_name = hrgnum_name;
	}
	public String getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public String[] getChkSelectRecord() {
		return chkSelectRecord;
	}
	public void setChkSelectRecord(String[] chkSelectRecord) {
		this.chkSelectRecord = chkSelectRecord;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		
		this.setReqPurposeId("");
		this.setForDays("");
		this.setRequestByName("");
		this.setRequestById("");
		this.recordType="-1";
		this.remarks="";
		this.setRequestByExternalName("");
		this.requestByFlag="0";
	}
	public String getIsRecordRequested() {
		return isRecordRequested;
	}
	public void setIsRecordRequested(String isRecordRequested) {
		this.isRecordRequested = isRecordRequested;
	}
	public String getRecordIdToRemove() {
		return recordIdToRemove;
	}
	public void setRecordIdToRemove(String recordIdToRemove) {
		this.recordIdToRemove = recordIdToRemove;
	}
	public String[] getReqRemarks() {
		return reqRemarks;
	}
	public void setReqRemarks(String[] reqRemarks) {
		this.reqRemarks = reqRemarks;
	}
	public String getUserEmpId() {
		return userEmpId;
	}
	public void setUserEmpId(String userEmpId) {
		this.userEmpId = userEmpId;
	}
	public String getDucRecordId() {
		return ducRecordId;
	}
	public void setDucRecordId(String ducRecordId) {
		this.ducRecordId = ducRecordId;
	}
	public String[] getSearchRecordId() {
		return searchRecordId;
	}
	public void setSearchRecordId(String[] searchRecordId) {
		this.searchRecordId = searchRecordId;
	}
	public String getIsReqOnlineOffline() {
		return isReqOnlineOffline;
	}
	public void setIsReqOnlineOffline(String isReqOnlineOffline) {
		this.isReqOnlineOffline = isReqOnlineOffline;
	}
	public String getRequestByFlag() {
		return requestByFlag;
	}
	public void setRequestByFlag(String requestByFlag) {
		this.requestByFlag = requestByFlag;
	}
	public String getRequestByExternalName() {
		return requestByExternalName;
	}
	public void setRequestByExternalName(String requestByExternalName) {
		this.requestByExternalName = requestByExternalName;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getDeptunitname() {
		return deptunitname;
	}
	public void setDeptunitname(String deptunitname) {
		this.deptunitname = deptunitname;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
	}
	public String getRequestByEmpDept() {
		return requestByEmpDept;
	}
	public void setRequestByEmpDept(String requestByEmpDept) {
		this.requestByEmpDept = requestByEmpDept;
	}
	public String getHiddenDeptName() {
		return hiddenDeptName;
	}
	public void setHiddenDeptName(String hiddenDeptName) {
		this.hiddenDeptName = hiddenDeptName;
	}
	public String getSheetDept() {
		return sheetDept;
	}
	public void setSheetDept(String sheetDept) {
		this.sheetDept = sheetDept;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	
	
}
