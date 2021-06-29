package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ComplicationMasterFB extends ActionForm
{
	private String complicationId;
	private String slNo;
	private String complicationTypeId;
	private String complication;
	private String isValid;
	private String controls[];
	private String abortionTypeName;
	private String complicationTypeName;
	private String chk[];
	private String hmode;

	private String tempMode;



	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setComplication("");
		//this.setComplicationTypeId("-1");	
	}


	public ComplicationMasterFB()
	{
		this.controls = new String[1];
	}



	public String getComplicationId() {
		return complicationId;
	}

	public void setComplicationId(String complicationId) {
		this.complicationId = complicationId;
	}

	public String getSlNo() {
		return slNo;
	}

	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}



	public String getComplicationTypeId() {
		return complicationTypeId;
	}



	public void setComplicationTypeId(String complicationTypeId) {
		this.complicationTypeId = complicationTypeId;
	}



	public String getComplication() {
		return complication;
	}



	public void setComplication(String complication) {
		this.complication = complication;
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



	public String getAbortionTypeName() {
		return abortionTypeName;
	}



	public void setAbortionTypeName(String abortionTypeName) {
		this.abortionTypeName = abortionTypeName;
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





	public String getComplicationTypeName() {
		return complicationTypeName;
	}





	public void setComplicationTypeName(String complicationTypeName) {
		this.complicationTypeName = complicationTypeName;
	}
	
	
}
