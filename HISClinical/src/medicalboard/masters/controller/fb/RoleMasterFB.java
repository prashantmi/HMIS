package medicalboard.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RoleMasterFB extends ActionForm{

	
	private String roleID;
	private String slNo;
	private String roleName;
	private String hmode;
	private String chk[];
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
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
}
