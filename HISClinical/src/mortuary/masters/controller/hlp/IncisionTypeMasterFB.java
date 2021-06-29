package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class IncisionTypeMasterFB extends ActionForm
{
	private String incisionType;
	private String incisionTypeCode;
	private String description;
	private String slNo;
	
	private String chk[];
	private String hmode;
	private String tempMode;
	private String isActive;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.incisionType="";
		this.description="";
	}

	public String getIncisionType() {
		return incisionType;
	}


	public void setIncisionType(String incisionType) {
		this.incisionType = incisionType;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
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

	public String getIncisionTypeCode() {
		return incisionTypeCode;
	}

	public void setIncisionTypeCode(String incisionTypeCode) {
		this.incisionTypeCode = incisionTypeCode;
	}

	
}
