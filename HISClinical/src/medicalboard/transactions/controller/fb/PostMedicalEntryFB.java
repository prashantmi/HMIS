package medicalboard.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import registration.controller.fb.CRNoFB;

public class PostMedicalEntryFB extends CRNoFB{

	 private String hmode;
	 private String certificateTypeID; 
	 private String certificateTypeName; 
	 private String boardNo; 
	 private String boardName; 
	 private String patCrNo;
	 private String reqID;
	 private String slNo;
	 private String departmentCode;
	 private String departmentUnitCode;
	 private String selectedCandidate;
	 private String medicalPerformed;
	 private String isReferred;
	 private String isInvestigationRaised;
	 private String candidateName;
	 private String checkedItem;
	 private String reason;
	 private String[]remarks;
	 private String[]selectedCheckList;
	 private String result;
	 private String opinion;
	 private String isVerified;
	 private String isApproved;
	 private String approvedBy;
	 private String approvedDate;
	 private String examDate;
	 private String fromDate;
	 private String toDate;
	 private String lastVisitDate;
	 private String lastCertNo;
	 
	 private int currentPage;
	 
	 private String[] opinionCodeArray;
	 private String[] opinionRemarkArray; 
	 private String[] empIdArray;
	 
	 private String finalRemark;
	 private String opinionCode;
	 private String certificateResult;
	
	 private String onlineOfflineFlag;

	 private String patIsDocPresent;
	 
	 private String sysDate;
 


	public String getPatIsDocPresent() {
		return patIsDocPresent;
	}


	public void setPatIsDocPresent(String patIsDocPresent) {
		this.patIsDocPresent = patIsDocPresent;
	}


	public String getOnlineOfflineFlag() {
		return onlineOfflineFlag;
	}


	public void setOnlineOfflineFlag(String onlineOfflineFlag) {
		this.onlineOfflineFlag = onlineOfflineFlag;
	}


	public String getFinalRemark() {
		return finalRemark;
	}


	public void setFinalRemark(String finalRemark) {
		this.finalRemark = finalRemark;
	}


	public String[] getEmpIdArray() {
		return empIdArray;
	}


	public void setEmpIdArray(String[] empIdArray) {
		this.empIdArray = empIdArray;
	}


	public String[] getOpinionCodeArray() {
		return opinionCodeArray;
	}


	public void setOpinionCodeArray(String[] opinionCodeArray) {
		this.opinionCodeArray = opinionCodeArray;
	}


	public String[] getOpinionRemarkArray() {
		return opinionRemarkArray;
	}


	public void setOpinionRemarkArray(String[] opinionRemarkArray) {
		this.opinionRemarkArray = opinionRemarkArray;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("");
		this.setHmode("");
		this.certificateTypeID="-1";
		this.currentPage=1;
		this.checkedItem="";
		this.certificateTypeName="";
		this.isVerified="";
		this.isApproved="";
		this.approvedBy="-1";
		this.approvedDate="";
		this.examDate="";
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


	public String getCertificateTypeID() {
		return certificateTypeID;
	}


	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}


	public String getPatCrNo() {
		return patCrNo;
	}


	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}


	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}


	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}


	public String getSelectedCandidate() {
		return selectedCandidate;
	}


	public void setSelectedCandidate(String selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
	}


	public String getDepartmentCode() {
		return departmentCode;
	}


	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}


	public String getIsReferred() {
		return isReferred;
	}


	public void setIsReferred(String isReferred) {
		this.isReferred = isReferred;
	}


	public String getIsInvestigationRaised() {
		return isInvestigationRaised;
	}


	public void setIsInvestigationRaised(String isInvestigationRaised) {
		this.isInvestigationRaised = isInvestigationRaised;
	}


	public String getCandidateName() {
		return candidateName;
	}


	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}


	public String getCheckedItem() {
		return checkedItem;
	}


	public void setCheckedItem(String checkedItem) {
		this.checkedItem = checkedItem;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getCertificateTypeName() {
		return certificateTypeName;
	}


	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}


	public String[] getRemarks() {
		return remarks;
	}


	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}


	public String getMedicalPerformed() {
		return medicalPerformed;
	}


	public void setMedicalPerformed(String medicalPerformed) {
		this.medicalPerformed = medicalPerformed;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public String getOpinion() {
		return opinion;
	}


	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}


	public String getIsVerified() {
		return isVerified;
	}


	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}


	public String getIsApproved() {
		return isApproved;
	}


	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}


	public String getApprovedBy() {
		return approvedBy;
	}


	public void setApproveBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}


	public String getApprovedDate() {
		return approvedDate;
	}


	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}


	public String getReqID() {
		return reqID;
	}


	public void setReqID(String reqID) {
		this.reqID = reqID;
	}


	public String getExamDate() {
		return examDate;
	}


	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}


	public String[] getSelectedCheckList() {
		return selectedCheckList;
	}


	public void setSelectedCheckList(String[] selectedCheckList) {
		this.selectedCheckList = selectedCheckList;
	}


	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}


	public String getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}


	public String getBoardName() {
		return boardName;
	}


	public void setBoardName(String boardName) {
		this.boardName = boardName;
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


	public String getLastVisitDate() {
		return lastVisitDate;
	}


	public void setLastVisitDate(String lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}


	public String getOpinionCode() {
		return opinionCode;
	}


	public void setOpinionCode(String opinionCode) {
		this.opinionCode = opinionCode;
	}


	public String getCertificateResult() {
		return certificateResult;
	}


	public void setCertificateResult(String certificateResult) {
		this.certificateResult = certificateResult;
	}


	public String getSysDate() {
		return sysDate;
	}


	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}


	public String getLastCertNo()
	{
		return lastCertNo;
	}


	public void setLastCertNo(String lastCertNo)
	{
		this.lastCertNo = lastCertNo;
	}

	
}
