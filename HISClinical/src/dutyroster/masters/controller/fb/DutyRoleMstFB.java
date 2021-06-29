package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DutyRoleMstFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isActive;
	private String hospitalcode;
	private String dutyRoleId;
	private String dutyRoleDesc;
	private String roleShortName;
	private String serialNo;
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.dutyRoleId = "";
		this.isActive = "-1";
		this.dutyRoleDesc = "";	
		this.hospitalcode = "";
		this.chk="";
		this.roleShortName="";
		//this.hmode="";
	}

	
	
	public String getRoleShortName() {
		return roleShortName;
	}



	public void setRoleShortName(String roleShortName) {
		this.roleShortName = roleShortName;
	}



	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getHospitalcode() {
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}

	public String getDutyRoleId() {
		return dutyRoleId;
	}

	public void setDutyRoleId(String dutyRoleId) {
		this.dutyRoleId = dutyRoleId;
	}

	public String getDutyRoleDesc() {
		return dutyRoleDesc;
	}

	public void setDutyRoleDesc(String dutyRoleDesc) {
		this.dutyRoleDesc = dutyRoleDesc;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	
}
