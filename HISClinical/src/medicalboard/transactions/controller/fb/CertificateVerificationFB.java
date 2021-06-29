package medicalboard.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class CertificateVerificationFB extends CRNoFB
{
	private String hmode;
	private String boardNo;
	private String certificateTypeID;
	private String fromDate;
	private String toDate;
	private String certificateTypeName;
	private String patCrNo;
	private String selectedCandidate;
	private String candidateName;
	private String selReqId;
	
	private String finalRemark;
	private String opinionCode;
	private String approvedDate;
	private String opinionCodeArray;
	private String certificateNo;
	
	private int currentPage;
	private String sysDate;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("");
		this.setHmode("");
		this.certificateTypeID="-1";
		this.currentPage=1;
		this.certificateTypeName="";
		this.boardNo="%";
		this.fromDate="";
		this.toDate="";
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getCertificateTypeID() {
		return certificateTypeID;
	}

	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
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

	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getSelectedCandidate() {
		return selectedCandidate;
	}

	public void setSelectedCandidate(String selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getFinalRemark() {
		return finalRemark;
	}

	public void setFinalRemark(String finalRemark) {
		this.finalRemark = finalRemark;
	}

	public String getOpinionCode() {
		return opinionCode;
	}

	public void setOpinionCode(String opinionCode) {
		this.opinionCode = opinionCode;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getOpinionCodeArray() {
		return opinionCodeArray;
	}

	public void setOpinionCodeArray(String opinionCodeArray) {
		this.opinionCodeArray = opinionCodeArray;
	}

	public String getSelReqId() {
		return selReqId;
	}

	public void setSelReqId(String selReqId) {
		this.selReqId = selReqId;
	}

	public String getSysDate()
	{
		return sysDate;
	}

	public void setSysDate(String sysDate)
	{
		this.sysDate = sysDate;
	}

	public String getCertificateNo()
	{
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo)
	{
		this.certificateNo = certificateNo;
	}
}
