package opd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class OpdReportLeftMenuFB extends ActionForm {
	private String hmode;
	private String mode;
	private String deskMenuId;
	private String departmentUnitCode;
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
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
}
