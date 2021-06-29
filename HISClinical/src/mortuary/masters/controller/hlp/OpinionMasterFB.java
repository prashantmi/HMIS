package mortuary.masters.controller.hlp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class OpinionMasterFB extends ActionForm
{
	private String opinionCode;
	private String slNo;
	private String opinionName;
	private String opinionDesc;
	private String tempMode;
	
	private String chk[];
	private String hmode;
	private String isActive;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setOpinionName("");
		this.setOpinionDesc("");
			
	}


	public String getOpinionCode() {
		return opinionCode;
	}


	public void setOpinionCode(String opinionCode) {
		this.opinionCode = opinionCode;
	}


	public String getSlNo() {
		return slNo;
	}


	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}


	public String getOpinionName() {
		return opinionName;
	}


	public void setOpinionName(String opinionName) {
		this.opinionName = opinionName;
	}


	public String getOpinionDesc() {
		return opinionDesc;
	}


	public void setOpinionDesc(String opinionDesc) {
		this.opinionDesc = opinionDesc;
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


	public String getIsActive() {
		return isActive;
	}


	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}


	public String getTempMode() {
		return tempMode;
	}


	public void setTempMode(String tempMode) {
		this.tempMode = tempMode;
	}

}
