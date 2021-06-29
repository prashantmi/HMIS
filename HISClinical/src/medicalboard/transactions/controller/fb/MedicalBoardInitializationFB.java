package medicalboard.transactions.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class MedicalBoardInitializationFB extends CRNoFB
{
	private String hmode;
	private String certificateTypeID;
	private String scheduleDate;
	private String deptUnitCode;
	private String[] selectedSchedule;
	private String[] minReqArray;
	private String[] maxReqArray;
	private String[] registeredReqArray; 
	private String minReq;
	private String maxReq;
	private String registeredReq; 
	private int currentPage=1;
	private String docRoleIndex;
	private String empID;
	private String roleID;
	private String escortedEmpID;
	private String escortedRowIndex;
	private String location;
	
	private String selectedBoard;
	private String[] selectedBoardArray;
	private String[] assignBoardNoArray; 
	private String selectedAssignBoardNo;
	private String selectedAssignBoardId;
	private String docRoleAssignIndex;
	private String escortedAssignRowIndex;
	private String[] locationArray;
	private String selectedLocation;
	//private String selCertificateTypeID;
	private String[] docRoleArray;
	private String[] escortedArray;
	private String[] addNewBoardDocArray;
	private String[] addNewBoardEscortArray;
	private String[] modifyDocArray;
	private String[] modifyEscortedArray;
	private String divMessage;
	private String modifyCheck;
	
	private String index;
	
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String[] getAddNewBoardDocArray() {
		return addNewBoardDocArray;
	}
	public void setAddNewBoardDocArray(String[] addNewBoardDocArray) {
		this.addNewBoardDocArray = addNewBoardDocArray;
	}
	public String[] getAddNewBoardEscortArray() {
		return addNewBoardEscortArray;
	}
	public void setAddNewBoardEscortArray(String[] addNewBoardEscortArray) {
		this.addNewBoardEscortArray = addNewBoardEscortArray;
	}
	public String[] getDocRoleArray() {
		return docRoleArray;
	}
	public void setDocRoleArray(String[] docRoleArray) {
		this.docRoleArray = docRoleArray;
	}
	public String[] getEscortedArray() {
		return escortedArray;
	}
	public void setEscortedArray(String[] escortedArray) {
		this.escortedArray = escortedArray;
	}
	public String getSelectedLocation() {
		return selectedLocation;
	}
	public void setSelectedLocation(String selectedLocation) {
		this.selectedLocation = selectedLocation;
	}
	public String getDocRoleAssignIndex() {
		return docRoleAssignIndex;
	}
	public void setDocRoleAssignIndex(String docRoleAssignIndex) {
		this.docRoleAssignIndex = docRoleAssignIndex;
	}
	public String getEscortedAssignRowIndex() {
		return escortedAssignRowIndex;
	}
	public void setEscortedAssignRowIndex(String escortedAssignRowIndex) {
		this.escortedAssignRowIndex = escortedAssignRowIndex;
	}
	public String getSelectedAssignBoardNo() {
		return selectedAssignBoardNo;
	}
	public void setSelectedAssignBoardNo(String selectedAssignBoardNo) {
		this.selectedAssignBoardNo = selectedAssignBoardNo;
	}
	public String[] getAssignBoardNoArray() {
		return assignBoardNoArray;
	}
	public void setAssignBoardNoArray(String[] assignBoardNoArray) {
		this.assignBoardNoArray = assignBoardNoArray;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.roleID="-1";
		this.escortedEmpID="-1";
		this.empID="-1";
		this.scheduleDate="";
		this.minReq="";
		this.maxReq="";
		this.registeredReq="";
		this.certificateTypeID="-1";
		this.divMessage="";
	}
	public String getDeptUnitCode() {
		return deptUnitCode;
	}

	public void setDeptUnitCode(String deptUnitCode) {
		this.deptUnitCode = deptUnitCode;
	}

	

	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getCertificateTypeID() {
		return certificateTypeID;
	}

	public void setCertificateTypeID(String certificateTypeID) {
		this.certificateTypeID = certificateTypeID;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	

	public String[] getSelectedSchedule() {
		return selectedSchedule;
	}

	public void setSelectedSchedule(String[] selectedSchedule) {
		this.selectedSchedule = selectedSchedule;
	}

	public String[] getMinReqArray() {
		return minReqArray;
	}

	public void setMinReqArray(String[] minReqArray) {
		this.minReqArray = minReqArray;
	}

	public String[] getMaxReqArray() {
		return maxReqArray;
	}

	public void setMaxReqArray(String[] maxReqArray) {
		this.maxReqArray = maxReqArray;
	}

	public String[] getRegisteredReqArray() {
		return registeredReqArray;
	}

	public void setRegisteredReqArray(String[] registeredReqArray) {
		this.registeredReqArray = registeredReqArray;
	}

	public String getMinReq() {
		return minReq;
	}

	public void setMinReq(String minReq) {
		this.minReq = minReq;
	}

	public String getMaxReq() {
		return maxReq;
	}

	public void setMaxReq(String maxReq) {
		this.maxReq = maxReq;
	}

	public String getRegisteredReq() {
		return registeredReq;
	}

	public void setRegisteredReq(String registeredReq) {
		this.registeredReq = registeredReq;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getEscortedEmpID() {
		return escortedEmpID;
	}

	public void setEscortedEmpID(String escortedEmpID) {
		this.escortedEmpID = escortedEmpID;
	}

	public String getEscortedRowIndex() {
		return escortedRowIndex;
	}

	public void setEscortedRowIndex(String escortedRowIndex) {
		this.escortedRowIndex = escortedRowIndex;
	}

	public String getDocRoleIndex() {
		return docRoleIndex;
	}

	public void setDocRoleIndex(String docRoleIndex) {
		this.docRoleIndex = docRoleIndex;
	}
	public String getSelectedBoard() {
		return selectedBoard;
	}
	public void setSelectedBoard(String selectedBoard) {
		this.selectedBoard = selectedBoard;
	}
	public String[] getSelectedBoardArray() {
		return selectedBoardArray;
	}
	public void setSelectedBoardArray(String[] selectedBoardArray) {
		this.selectedBoardArray = selectedBoardArray;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String[] getLocationArray() {
		return locationArray;
	}
	public void setLocationArray(String[] locationArray) {
		this.locationArray = locationArray;
	}
	public String getSelectedAssignBoardId() {
		return selectedAssignBoardId;
	}
	public void setSelectedAssignBoardId(String selectedAssignBoardId) {
		this.selectedAssignBoardId = selectedAssignBoardId;
	}
	public String[] getModifyDocArray() {
		return modifyDocArray;
	}
	public void setModifyDocArray(String[] modifyDocArray) {
		this.modifyDocArray = modifyDocArray;
	}
	public String[] getModifyEscortedArray() {
		return modifyEscortedArray;
	}
	public void setModifyEscortedArray(String[] modifyEscortedArray) {
		this.modifyEscortedArray = modifyEscortedArray;
	}
	public String getDivMessage()
	{
		return divMessage;
	}
	public void setDivMessage(String divMessage)
	{
		this.divMessage = divMessage;
	}
	public String getModifyCheck() {
		return modifyCheck;
	}
	public void setModifyCheck(String modifyCheck) {
		this.modifyCheck = modifyCheck;
	}
	
}
