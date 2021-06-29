package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AbortionMethodMasterFB extends ActionForm
{
	private String abortionMethodId;
	private String slNo;
	private String abortionTypeId;
	private String abortionMethod;
	private String isValid;
	private String controls[];
	private String abortionTypeName;
	
	private String chk[];
	private String hmode;

	private String tempMode;

	public AbortionMethodMasterFB()
	{
		controls=new String[1];
	}


	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setAbortionMethod("");
			
	}
	
	public String getAbortionMethodId() {
		return abortionMethodId;
	}

	public void setAbortionMethodId(String abortionMethodId) {
		this.abortionMethodId = abortionMethodId;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

	public String getAbortionTypeId() {
		return abortionTypeId;
	}

	public void setAbortionTypeId(String abortionTypeId) {
		this.abortionTypeId = abortionTypeId;
	}

	public String getAbortionMethod() {
		return abortionMethod;
	}

	public void setAbortionMethod(String abortionMethod) {
		this.abortionMethod = abortionMethod;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
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

	public String getAbortionTypeName() {
		return abortionTypeName;
	}

	public void setAbortionTypeName(String abortionTypeName) {
		this.abortionTypeName = abortionTypeName;
	}
	
	
}
