package medicalboard.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class MedicalBoardRequisitionFB extends CRNoFB{

	 private String hmode;
	 private String certificateTypeID; 
	 private String patCrNo = "";
	 private String patPrimaryCat;
	 private String patPrimaryCatCode;
	 private String patFirstName;
	 private String patMiddleName;
	 private String patLastName;
	 private String patGender;
     private String patGenderCode;
	 private String patAge;
	 private String selectedPatCrNo;
	 private String reqBy;
	 private String isBillingDone;
	 private String selectedSchedule;
	 private String checkedItem;
	  
	 private String reqID;
	 private String slNo;
	 private String requestFrom;
	 private String designation;
	 private String orgID;
	 private String orgType;
	 private String orgTypeID;
	 private String orgName;
	 private String otherOrgName;
	 private String orgAddress;
	 private String reqStatus;
	 private String examDate;
	 private String approvedDate;
	 private String approvedBy;
	 //private String boardID;
	 private String hospitalCode;
	 private String isValid;
	 private String seatID;
	 private String CIDNo;

	 private String departmentCode;
	 private String departmentUnit;
	 private String departmentUnitCode;
	 private String[] selectedCheckListId;
	 private String isCompulsoryArray;
	 private String[] checkListIdArray;
	 private String[] checkByIdArray;
	 private String[] remarks;
	
	 private String boardTypeID;
	 private String minRequest;
	 private String maxRequest;
	 private String isDataCorrect;
	 private String billNo;
	 private int currentPage=1;
	 private int index;
 
	 
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
	public String getPatPrimaryCat() {
		return patPrimaryCat;
	}
	public void setPatPrimaryCat(String patPrimaryCat) {
		this.patPrimaryCat = patPrimaryCat;
	}
	public String getPatPrimaryCatCode() {
		return patPrimaryCatCode;
	}
	public void setPatPrimaryCatCode(String patPrimaryCatCode) {
		this.patPrimaryCatCode = patPrimaryCatCode;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	public String getPatMiddleName() {
		return patMiddleName;
	}
	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}
	public String getPatLastName() {
		return patLastName;
	}
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	public String getPatGender() {
		return patGender;
	}
	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}
	public String getPatGenderCode() {
		return patGenderCode;
	}
	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}
	public String getPatAge() {
		return patAge;
	}
	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
	public String getRequestFrom() {
		return requestFrom;
	}
	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSeatID() {
		return seatID;
	}
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}


	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentUnit() {
		return departmentUnit;
	}
	public void setDepartmentUnit(String departmentUnit) {
		this.departmentUnit = departmentUnit;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getOrgTypeID() {
		return orgTypeID;
	}
	public void setOrgTypeID(String orgTypeID) {
		this.orgTypeID = orgTypeID;
	}



	public String getIsCompulsoryArray() {
		return isCompulsoryArray;
	}

	public void setIsCompulsoryArray(String isCompulsoryArray) {
		this.isCompulsoryArray = isCompulsoryArray;
	}

	 
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setPatCrNo("            ");
		this.setPatPrimaryCatCode("");
		
		this.setPatFirstName("");
		this.setPatMiddleName("");
		this.setPatLastName("");
		this.setPatGenderCode("");
		this.setPatAge("");
		this.setHmode("");
		this.setDepartmentCode("");
		this.isDataCorrect="";
		this.currentPage=1;
		this.requestFrom="1";
		this.checkedItem="";
		this.isBillingDone="0";
		this.billNo="";
		this.examDate="";
		this.orgID="-1";
	}
	
	public String[] getCheckListIdArray() {
		return checkListIdArray;
	}

	public void setCheckListIdArray(String[] checkListIdArray) {
		this.checkListIdArray = checkListIdArray;
	}

	public String[] getCheckByIdArray() {
		return checkByIdArray;
	}

	public void setCheckByIdArray(String[] checkByIdArray) {
		this.checkByIdArray = checkByIdArray;
	}

	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public String getBoardTypeID() {
		return boardTypeID;
	}

	public void setBoardTypeID(String boardTypeID) {
		this.boardTypeID = boardTypeID;
	}

	public String getMinRequest() {
		return minRequest;
	}

	public void setMinRequest(String minRequest) {
		this.minRequest = minRequest;
	}

	public String getMaxRequest() {
		return maxRequest;
	}

	public void setMaxRequest(String maxRequest) {
		this.maxRequest = maxRequest;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String[] getSelectedCheckListId() {
		return selectedCheckListId;
	}
	public void setSelectedCheckListId(String[] selectedCheckListId) {
		this.selectedCheckListId = selectedCheckListId;
	}
	public String getSelectedPatCrNo() {
		return selectedPatCrNo;
	}
	public void setSelectedPatCrNo(String selectedPatCrNo) {
		this.selectedPatCrNo = selectedPatCrNo;
	}
	public String getReqBy() {
		return reqBy;
	}
	public void setReqBy(String reqBy) {
		this.reqBy = reqBy;
	}
	public String getIsBillingDone() {
		return isBillingDone;
	}
	public void setIsBillingDone(String isBillingDone) {
		this.isBillingDone = isBillingDone;
	}
	public String getSelectedSchedule() {
		return selectedSchedule;
	}
	public void setSelectedSchedule(String selectedSchedule) {
		this.selectedSchedule = selectedSchedule;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getIsDataCorrect() {
		return isDataCorrect;
	}
	public void setIsDataCorrect(String isDataCorrect) {
		this.isDataCorrect = isDataCorrect;
	}
	public String getOtherOrgName() {
		return otherOrgName;
	}
	public void setOtherOrgName(String otherOrgName) {
		this.otherOrgName = otherOrgName;
	}
	public String getCheckedItem() {
		return checkedItem;
	}
	public void setCheckedItem(String checkedItem) {
		this.checkedItem = checkedItem;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getCIDNo()
	{
		return CIDNo;
	}
	public void setCIDNo(String no)
	{
		CIDNo = no;
	}

	
}
