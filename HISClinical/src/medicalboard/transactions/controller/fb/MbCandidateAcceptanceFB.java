package medicalboard.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import registration.controller.fb.CRNoFB;

public class MbCandidateAcceptanceFB extends CRNoFB{

	 private String hmode;
	 private String certificateTypeID; 
	 private String patCrNo;
	 private String selectedSchedule;
	 private String checkedItem;
	 private String reqID;
	 private String slNo;
	 private String requestBy;
	 private String orgID;
	 private String examDate;
	 private String boardNo;
	 private String boardName;
	 private String visitNo;
	 private String departmentUnitCode;
	 private String []selectedCandidate;
	 private String []selectedCheckList;
	 private String []isCompulsory;
	 private String []remarks;
	 
	 private String flag;
	 private String strPreviousDate;
	 private String chkPreviousDate;

	public String getStrPreviousDate() {
		return strPreviousDate;
	}


	public void setStrPreviousDate(String strPreviousDate) {
		this.strPreviousDate = strPreviousDate;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("");
		this.setHmode("");
		this.requestBy="";
		this.checkedItem="";
		this.orgID="-1";
		this.requestBy="1";
		this.flag="";
		this.boardNo="-1";
		this.reqID="";
		this.certificateTypeID="%";
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

	public String getSelectedSchedule() {
		return selectedSchedule;
	}


	public void setSelectedSchedule(String selectedSchedule) {
		this.selectedSchedule = selectedSchedule;
	}


	public String getCheckedItem() {
		return checkedItem;
	}


	public void setCheckedItem(String checkedItem) {
		this.checkedItem = checkedItem;
	}


	public String getReqID() {
		return reqID;
	}


	public void setReqID(String reqID) {
		this.reqID = reqID;
	}


	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}


	public String getRequestBy() {
		return requestBy;
	}


	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}


	public String getOrgID() {
		return orgID;
	}


	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}


	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}


	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}


	public String[] getSelectedCandidate() {
		return selectedCandidate;
	}


	public void setSelectedCandidate(String[] selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
	}


	public String[] getIsCompulsory() {
		return isCompulsory;
	}


	public void setIsCompulsory(String[] isCompulsory) {
		this.isCompulsory = isCompulsory;
	}


	public String[] getRemarks() {
		return remarks;
	}


	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}


	public String[] getSelectedCheckList() {
		return selectedCheckList;
	}


	public void setSelectedCheckList(String[] selectedCheckList) {
		this.selectedCheckList = selectedCheckList;
	}


	public String getExamDate() {
		return examDate;
	}


	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}


	public String getBoardNo() {
		return boardNo;
	}


	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getVisitNo() {
		return visitNo;
	}


	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}


	public String getBoardName() {
		return boardName;
	}


	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}


	public String getChkPreviousDate() {
		return chkPreviousDate;
	}


	public void setChkPreviousDate(String chkPreviousDate) {
		this.chkPreviousDate = chkPreviousDate;
	}

	
}
