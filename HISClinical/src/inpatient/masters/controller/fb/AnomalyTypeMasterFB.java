package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class AnomalyTypeMasterFB extends ActionForm
{
	private String anomalyTypeID;
	private String slNo;
	private String anomalyType;
		
	private String chk[];
	private String hmode;
	private String isActive;
	private String tempMode;
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setAnomalyType("");
			
	}
	
	public String getAnomalyTypeID() {
		return anomalyTypeID;
	}
	public void setAnomalyTypeID(String anomalyTypeID) {
		this.anomalyTypeID = anomalyTypeID;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getAnomalyType() {
		return anomalyType;
	}
	public void setAnomalyType(String anomalyType) {
		this.anomalyType = anomalyType;
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
