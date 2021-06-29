package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AbortionTypeMasterFB extends ActionForm
{
	private String abortionTypeID;
	private String slNo;
	private String abortionType;
		
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

	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setAbortionType("");
			
	}

	public String getAbortionTypeID() {
		return abortionTypeID;
	}

	public void setAbortionTypeID(String abortionTypeID) {
		this.abortionTypeID = abortionTypeID;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getAbortionType() {
		return abortionType;
	}

	public void setAbortionType(String abortionType) {
		this.abortionType = abortionType;
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

}
