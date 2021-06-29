package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ValidateUserFB extends ActionForm {
	
	private String hmode;
	private String transactionMode;
	private String processId;
	private String mode;
	private String targetMode;
	private String roleId;
	private String menuRoleId;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.hmode="";
		this.transactionMode="";
		this.processId="";
		this.mode="";
		this.targetMode="";
		this.roleId="";
		//this.menuRoleId="";
		
	}
	public String getTargetMode() {
		return targetMode;
	}

	public void setTargetMode(String targetMode) {
		this.targetMode = targetMode;
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

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuRoleId() {
		return menuRoleId;
	}

	public void setMenuRoleId(String menuRoleId) {
		this.menuRoleId = menuRoleId;
	}

	
}
