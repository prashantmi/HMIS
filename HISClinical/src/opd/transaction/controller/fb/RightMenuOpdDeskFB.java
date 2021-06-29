package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RightMenuOpdDeskFB extends ActionForm {
	private String hmode;
	private String deskMenuId;
	private String mode;
	private String departmentUnitCode;
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}

	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}

	public String getDeskMenuId() {
		return deskMenuId;
	}

	public void setDeskMenuId(String deskMenuId) {
		this.deskMenuId = deskMenuId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		this.setHmode("");
		this.setMode("");
		this.setDepartmentUnitCode("");
		super.reset(arg0, arg1);
	}
}
