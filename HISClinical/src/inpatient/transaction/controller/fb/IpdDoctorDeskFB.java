package inpatient.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class IpdDoctorDeskFB extends ActionForm
{
	private String mode;
	private String deskMenuId;
	private String departmentUnitCode;
	private String crNoSelected;
	private String patCrNo;
	
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
}
