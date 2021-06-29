package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RecordRequestApprovalFB extends ActionForm
{
	
	private String hmode;
	private String patAdmNo;
	private String requestId;
	private String requestBy;
	private String reqDate;
	private String requestByName;
	private String recordType;
	private String recordTypeName;
	private String forDays;
	private String approvalRemarks;
	private String selectedRecord[];
	private String selectedRecordId;
	private String purpose;
	private String priority;
	private String rejectReason[];
	private String cancelReason;
	private String isAcceptAll;
	private String acceptForDays;
	private String mrdRecordId;
	private String remarks;
	private String requestByNameDept;
	private String isUserEmp;
	private String recordId;//added by sandip naik on 20/7/17
	private String deptunitname;//end by sandip naik on 20/7/17
	
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.approvalRemarks="";
		this.selectedRecord=new String[]{};
		this.requestId="";
		this.isAcceptAll="";
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

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getForDays() {
		return forDays;
	}

	public void setForDays(String forDays) {
		this.forDays = forDays;
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

	public String getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRequestByName() {
		return requestByName;
	}

	public void setRequestByName(String requestByName) {
		this.requestByName = requestByName;
	}

	public String[] getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String[] rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getSelectedRecordId() {
		return selectedRecordId;
	}

	public void setSelectedRecordId(String selectedRecordId) {
		this.selectedRecordId = selectedRecordId;
	}

	public String getIsAcceptAll() {
		return isAcceptAll;
	}

	public void setIsAcceptAll(String isAcceptAll) {
		this.isAcceptAll = isAcceptAll;
	}

	public String getAcceptForDays() {
		return acceptForDays;
	}

	public void setAcceptForDays(String acceptForDays) {
		this.acceptForDays = acceptForDays;
	}

	public String getMrdRecordId() {
		return mrdRecordId;
	}

	public void setMrdRecordId(String mrdRecordId) {
		this.mrdRecordId = mrdRecordId;
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

	public String getRecordTypeName() {
		return recordTypeName;
	}

	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}

	public String getRequestByNameDept() {
		return requestByNameDept;
	}

	public void setRequestByNameDept(String requestByNameDept) {
		this.requestByNameDept = requestByNameDept;
	}

	public String getIsUserEmp() {
		return isUserEmp;
	}

	public void setIsUserEmp(String isUserEmp) {
		this.isUserEmp = isUserEmp;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getDeptunitname() {
		return deptunitname;
	}

	public void setDeptunitname(String deptunitname) {
		this.deptunitname = deptunitname;
	}

	
	
	
}
