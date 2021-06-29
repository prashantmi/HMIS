package medicalboard.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MedicalExamInitiationFB extends ActionForm{

	 private String hmode;
	 private String certificateTypeID; 
	 private String certificateTypeName; 
	 private String patCrNo;
	 private String slNo;
	 private String departmentCode;
	 private String departmentUnitCode;
	 private String referType;
	 private String []selectedCandidate;
	 private String []selectedCheckList;
	 private String []remarks;
	 private String []selectedReferDept;
	 private String referDept;
	 private String isReferred;
	 private String isInvestigationRaised;
	 private String candidateName;
	 private String checkedItem;
	 private String []selectedLabTestCode;
	 private String labTestCode;
	 private String []testSampleCode;
	 private String selectedSampleCode;
	 private int currentPage;
	 private String extReferTo;
	 private String referReason;
	 private String reqID;
	 private String ImageFileNo;
	
	public String getImageFileNo() {
		return ImageFileNo;
	}


	public void setImageFileNo(String imageFileNo) {
		ImageFileNo = imageFileNo;
	}


	public String getExtReferTo() {
		return extReferTo;
	}


	public void setExtReferTo(String extReferTo) {
		this.extReferTo = extReferTo;
	}


	public String getReferReason() {
		return referReason;
	}


	public void setReferReason(String referReason) {
		this.referReason = referReason;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("");
		this.setHmode("");
		this.referDept="";
		this.referType="";
		this.certificateTypeID="-1";
		this.currentPage=1;
		this.checkedItem="";
		this.labTestCode="";
		this.certificateTypeName="";
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


	public String[] getSelectedCandidate() {
		return selectedCandidate;
	}


	public void setSelectedCandidate(String[] selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
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

	public String getDepartmentCode() {
		return departmentCode;
	}


	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}


	public String getReferType() {
		return referType;
	}


	public void setReferType(String referType) {
		this.referType = referType;
	}


	public String[] getSelectedReferDept() {
		return selectedReferDept;
	}


	public void setSelectedReferDept(String[] selectedReferDept) {
		this.selectedReferDept = selectedReferDept;
	}


	public String getReferDept() {
		return referDept;
	}


	public void setReferDept(String referDept) {
		this.referDept = referDept;
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


	public String[] getSelectedLabTestCode() {
		return selectedLabTestCode;
	}


	public void setSelectedLabTestCode(String[] selectedLabTestCode) {
		this.selectedLabTestCode = selectedLabTestCode;
	}


	public String getLabTestCode() {
		return labTestCode;
	}


	public void setLabTestCode(String labTestCode) {
		this.labTestCode = labTestCode;
	}


	public String[] getTestSampleCode() {
		return testSampleCode;
	}


	public void setTestSampleCode(String[] testSampleCode) {
		this.testSampleCode = testSampleCode;
	}


	public String getCertificateTypeName() {
		return certificateTypeName;
	}


	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}


	public String getSelectedSampleCode() {
		return selectedSampleCode;
	}


	public void setSelectedSampleCode(String selectedSampleCode) {
		this.selectedSampleCode = selectedSampleCode;
	}


	public String getReqID() {
		return reqID;
	}


	public void setReqID(String reqID) {
		this.reqID = reqID;
	}

	
}
