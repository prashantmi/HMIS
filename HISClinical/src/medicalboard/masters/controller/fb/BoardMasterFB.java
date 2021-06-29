package medicalboard.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BoardMasterFB extends ActionForm
{
	private String hmode;
	private String chk[];
	private String isActive;
	private String controls[];
	private String boardID;
	private String hospitalCode;
	private String slNo;
	private String boardName;
	private String boardTypeID;
	private String boardTypeName;
	private String empID;
	private String boardTeamSlNo;
	private String roleID;
	private String seatId;
	private String entryDate;
	private String[] empIDArray;
	private String[] roleIDArray;
	private String comboValue;
	private String escortedEmpID;
	private String[] escortedEmpIDArray;
	
	
	
	private String empIDValue;
	private String roleIDValue;
	private String escortedEmpIDValue;
	private String empName;
	private String roleName;
	private String escortedEmpName;
	
	private String numberOfRow;
	private String numberOfRowEscorted;
	
	public BoardMasterFB()
	{
	  this.controls= new String[2];
	}
		 
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.controls= new String[2];
		this.boardName="";
	}
	
	
	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String[] getChk() {
		return chk;
	}

	public void setChk(String[] chk) {
		this.chk = chk;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String getBoardID() {
		return boardID;
	}

	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getBoardTypeID() {
		return boardTypeID;
	}

	public void setBoardTypeID(String boardTypeID) {
		this.boardTypeID = boardTypeID;
	}

	public String getBoardTypeName() {
		return boardTypeName;
	}

	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getBoardTeamSlNo() {
		return boardTeamSlNo;
	}

	public void setBoardTeamSlNo(String boardTeamSlNo) {
		this.boardTeamSlNo = boardTeamSlNo;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getComboValue() {
		return comboValue;
	}

	public void setComboValue(String comboValue) {
		this.comboValue = comboValue;
	}

	public String getEscortedEmpID() {
		return escortedEmpID;
	}

	public void setEscortedEmpID(String escortedEmpID) {
		this.escortedEmpID = escortedEmpID;
	}

	public String[] getEmpIDArray() {
		return empIDArray;
	}

	public void setEmpIDArray(String[] empIDArray) {
		this.empIDArray = empIDArray;
	}

	public String[] getRoleIDArray() {
		return roleIDArray;
	}

	public void setRoleIDArray(String[] roleIDArray) {
		this.roleIDArray = roleIDArray;
	}

	public String[] getEscortedEmpIDArray() {
		return escortedEmpIDArray;
	}

	public void setEscortedEmpIDArray(String[] escortedEmpIDArray) {
		this.escortedEmpIDArray = escortedEmpIDArray;
	}

	public String getEmpIDValue() {
		return empIDValue;
	}

	public void setEmpIDValue(String empIDValue) {
		this.empIDValue = empIDValue;
	}

	public String getRoleIDValue() {
		return roleIDValue;
	}

	public void setRoleIDValue(String roleIDValue) {
		this.roleIDValue = roleIDValue;
	}

	public String getEscortedEmpIDValue() {
		return escortedEmpIDValue;
	}

	public void setEscortedEmpIDValue(String escortedEmpIDValue) {
		this.escortedEmpIDValue = escortedEmpIDValue;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEscortedEmpName() {
		return escortedEmpName;
	}

	public void setEscortedEmpName(String escortedEmpName) {
		this.escortedEmpName = escortedEmpName;
	}

	public String getNumberOfRow() {
		return numberOfRow;
	}

	public void setNumberOfRow(String numberOfRow) {
		this.numberOfRow = numberOfRow;
	}

	public String getNumberOfRowEscorted() {
		return numberOfRowEscorted;
	}

	public void setNumberOfRowEscorted(String numberOfRowEscorted) {
		this.numberOfRowEscorted = numberOfRowEscorted;
	}

	

	
}