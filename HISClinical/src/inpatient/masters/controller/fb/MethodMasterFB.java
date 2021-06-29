package inpatient.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class MethodMasterFB extends ActionForm
{
	private String methodID;
	private String method;
	private String methodTypeName;
	private String slNo;
	private String methodType;
	
	private String chk[];
	private String hmode;
	private String isActive;
	private String tempMode;
	private String controls[];
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setMethod("");
		
	}
	
	public MethodMasterFB()
	{
		controls=new String[1];
	}
	
	public String getMethodID() {
		return methodID;
	}
	public void setMethodID(String methodID) {
		this.methodID = methodID;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMethodTypeName() {
		return methodTypeName;
	}
	public void setMethodTypeName(String methodTypeName) {
		this.methodTypeName = methodTypeName;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
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

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

}
