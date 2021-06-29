package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MrdRecordIssueFB extends ActionForm
{
	
	private String hmode;
	private String patAdmNo;
	private String requestId;
	private String requestBy;
	private String reqDate;
	private String approvedDate;
	private String requestByName;
	private String recordType;
	private String recordTypeName;
	private String forDays;
	private String selectedRecord[];
	private String selectedRecordId;
	private String remarks;
	private String issueRemarks;
	private String mrdCode;
	private String handoverTo;
	private String expectedReturnDate;
	private String purpose;
	private String priority;
	private String rejectReason[];
	private String cancelReason;
	private String isAcceptAll;
	private String issueUpto;
	private String maxReturnDate;
	private String requestByFlag;
	private String handoverToName;
	private String rejectedRecord;
	private String rejectRemarks;
	private String isRejected;
	private int currentPage;
	private String recordId;
	private String deptunitname;
	private String approvedBy;


	
	public MrdRecordIssueFB()
	{
		this.currentPage=1;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMrdCode() {
		return mrdCode;
	}

	public void setMrdCode(String mrdCode) {
		this.mrdCode = mrdCode;
	}

	public String getHandoverTo() {
		return handoverTo;
	}

	public void setHandoverTo(String handoverTo) {
		this.handoverTo = handoverTo;
	}

	public String getExpectedReturnDate() {
		return expectedReturnDate;
	}

	public void setExpectedReturnDate(String expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
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

	public String getRecordTypeName() {
		return recordTypeName;
	}

	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}

	public String getIssueUpto() {
		return issueUpto;
	}

	public void setIssueUpto(String issueUpto) {
		this.issueUpto = issueUpto;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getIssueRemarks() {
		return issueRemarks;
	}

	public void setIssueRemarks(String issueRemarks) {
		this.issueRemarks = issueRemarks;
	}

	public String getMaxReturnDate() {
		return maxReturnDate;
	}

	public void setMaxReturnDate(String maxReturnDate) {
		this.maxReturnDate = maxReturnDate;
	}

	public String getRequestByFlag() {
		return requestByFlag;
	}

	public void setRequestByFlag(String requestByFlag) {
		this.requestByFlag = requestByFlag;
	}

	public String getHandoverToName() {
		return handoverToName;
	}

	public void setHandoverToName(String handoverToName) {
		this.handoverToName = handoverToName;
	}

	public String getRejectedRecord() {
		return rejectedRecord;
	}

	public void setRejectedRecord(String rejectedRecord) {
		this.rejectedRecord = rejectedRecord;
	}

	public String getRejectRemarks() {
		return rejectRemarks;
	}

	public void setRejectRemarks(String rejectRemarks) {
		this.rejectRemarks = rejectRemarks;
	}

	public String getIsRejected() {
		return isRejected;
	}

	public void setIsRejected(String isRejected) {
		this.isRejected = isRejected;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	

	
	
	
}
