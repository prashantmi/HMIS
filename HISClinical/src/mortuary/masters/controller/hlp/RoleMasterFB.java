package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RoleMasterFB extends ActionForm
{
	private String roleID;
	private String slNo;
	private String roleName;
	
	private String chk[];
	private String hmode;
	private String isActive;
	private String tempMode;
	
	public String getTempMode() {
		return tempMode;
	}


	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.roleName="";
				
	}


	public String getRoleID() {
		return roleID;
	}


	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}


	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public String[] getChk() {
		return chk;
	}


	public void setChk(String[] chk) {
		this.chk = chk;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


}
