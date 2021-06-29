package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OnlineMrdRecordReqDtlFB extends ActionForm
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
	private String requestBy;
	private String cancelReason;
	private String approvedBy;
	private String isMrdLengthOne;
	private String isRecordRequested;
	private String concatedIndex;
	private String recordIdToRemove;
	private String reqRemarks[];
	private String requestId;
	private String userEmpId;
	private String isReqOnlineOffline;
	
	private String rejectReason;
	
	private int currentPage;
	private String DESIGNATED_APPROVED_BY;
	private String reqByIsHod;
	private String extendDays;
	private String extendReason;
	private String isUserEmp;
	
	public OnlineMrdRecordReqDtlFB()
	{
		this.currentPage=1;
	}
	
	
	
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getIsRecordRequested() {
		return isRecordRequested;
	}
	public void setIsRecordRequested(String isRecordRequested) {
		this.isRecordRequested = isRecordRequested;
	}
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
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
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
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setReqPurposeId("");
		this.setForDays("");
		this.recordType="-1";
		this.remarks="";
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
	public void setReqRemarks(String [] reqRemarks) {
		this.reqRemarks = reqRemarks;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}



	public int getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public String getUserEmpId() {
		return userEmpId;
	}



	public void setUserEmpId(String userEmpId) {
		this.userEmpId = userEmpId;
	}



	public String getIsReqOnlineOffline() {
		return isReqOnlineOffline;
	}



	public void setIsReqOnlineOffline(String isReqOnlineOffline) {
		this.isReqOnlineOffline = isReqOnlineOffline;
	}



	public String getDESIGNATED_APPROVED_BY() {
		return DESIGNATED_APPROVED_BY;
	}



	public void setDESIGNATED_APPROVED_BY(String dESIGNATED_APPROVED_BY) {
		DESIGNATED_APPROVED_BY = dESIGNATED_APPROVED_BY;
	}



	public String getReqByIsHod() {
		return reqByIsHod;
	}



	public void setReqByIsHod(String reqByIsHod) {
		this.reqByIsHod = reqByIsHod;
	}



	public String getExtendDays() {
		return extendDays;
	}



	public void setExtendDays(String extendDays) {
		this.extendDays = extendDays;
	}



	public String getExtendReason() {
		return extendReason;
	}



	public void setExtendReason(String extendReason) {
		this.extendReason = extendReason;
	}



	public String getIsUserEmp() {
		return isUserEmp;
	}



	public void setIsUserEmp(String isUserEmp) {
		this.isUserEmp = isUserEmp;
	}
	
}
