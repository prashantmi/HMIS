package medicalboard.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import registration.controller.fb.CRNoFB;

public class MedicalBoardRequisitionChangeFB extends CRNoFB{

	 private String hmode;
	 private String certificateTypeID; 
	 private String patCrNo;
	 private String selectedSchedule;
	 private String checkedItem;
	 private String reqID;
	 private String slNo;
	 private String requestBy;
	 private String orgID;
	 private String oldExamDate[];
	 private String [] examDate;
	 private String []changeReason;
	 private String departmentUnitCode;
	 private String []selectedRequisition;
	 private String otherOrgName;
	 private String scheduleDate;
	 
  
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("");
		this.setHmode("");
		this.requestBy="";
		this.checkedItem="";
		this.orgID="-1";
		this.otherOrgName="";
		this.requestBy="1";
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


	public String []getOldExamDate() {
		return oldExamDate;
	}


	public void setOldExamDate(String []oldExamDate) {
		this.oldExamDate = oldExamDate;
	}


	public String[] getExamDate() {
		return examDate;
	}


	public void setExamDate(String []examDate) {
		this.examDate = examDate;
	}


	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}


	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String[] getSelectedRequisition() {
		return selectedRequisition;
	}


	public void setSelectedRequisition(String []selectedRequisition) {
		this.selectedRequisition = selectedRequisition;
	}


	public String[] getChangeReason() {
		return changeReason;
	}


	public void setChangeReason(String[] changeReason) {
		this.changeReason = changeReason;
	}


	public String getOtherOrgName() {
		return otherOrgName;
	}


	public void setOtherOrgName(String otherOrgName) {
		this.otherOrgName = otherOrgName;
	}


	public String getScheduleDate() {
		return scheduleDate;
	}


	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}


	
	
}
