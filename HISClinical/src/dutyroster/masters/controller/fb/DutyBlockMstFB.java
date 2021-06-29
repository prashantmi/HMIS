package dutyroster.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DutyBlockMstFB extends ActionForm
{
	private String hmode;
	private String chk;
	private String isActive;
	private String serialNo;
	
	private String dutyBlockId;
	private String dutyBlockName;
	private String dutyBlockDesc;
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.dutyBlockId = "";
		this.dutyBlockName = "";	
		this.dutyBlockDesc = "";	
		
	}
	
	
	public String getDutyBlockId() {
		return dutyBlockId;
	}

	public void setDutyBlockId(String dutyBlockId) {
		this.dutyBlockId = dutyBlockId;
	}

	public String getDutyBlockName() {
		return dutyBlockName;
	}

	public void setDutyBlockName(String dutyBlockName) {
		this.dutyBlockName = dutyBlockName;
	}

	public String getDutyBlockDesc() {
		return dutyBlockDesc;
	}

	public void setDutyBlockDesc(String dutyBlockDesc) {
		this.dutyBlockDesc = dutyBlockDesc;
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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	
}
