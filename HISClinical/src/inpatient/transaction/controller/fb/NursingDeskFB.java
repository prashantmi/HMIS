package inpatient.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class NursingDeskFB extends ActionForm
{
	private String mode;
	private String deskMenuId;
	private String departmentUnitCode;
	private String crNoSelected;
	protected String patCrNo;
	private String enableBlanket; // Needed Login Screen or Not
	private String roleId;
	
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDeskMenuId() {
		return deskMenuId;
	}
	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getCrNoSelected() {
		return crNoSelected;
	}
	public void setCrNoSelected(String crNoSelected) {
		this.crNoSelected = crNoSelected;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEnableBlanket() {
		return enableBlanket;
	}
	public void setEnableBlanket(String enableBlanket) {
		this.enableBlanket = enableBlanket;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
