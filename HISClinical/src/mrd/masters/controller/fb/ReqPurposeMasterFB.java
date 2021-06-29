package mrd.masters.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ReqPurposeMasterFB extends ActionForm
{
	private String hmode;
	private String reqPurpose;
	private String priority;
	private String controls[];
	private String recordType;
	private String chk[];
	private String reqPurposeId;
	private String slNo;
	private String isActive;
	private String recordTypeDesc;
	
	public String getRecordTypeDesc() {
		return recordTypeDesc;
	}

	public void setRecordTypeDesc(String recordTypeDesc) {
		this.recordTypeDesc = recordTypeDesc;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		this.reqPurpose="";
		this.priority="-1";
	}
	
	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public ReqPurposeMasterFB()
	{
		this.controls=new String[1];
	}

	public String getReqPurpose() {
		return reqPurpose;
	}

	public void setReqPurpose(String reqPurpose) {
		this.reqPurpose = reqPurpose;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String[] getControls() {
		return controls;
	}

	public void setControls(String[] controls) {
		this.controls = controls;
	}

	public String getReqPurposeId() {
		return reqPurposeId;
	}

	public void setReqPurposeId(String reqPurposeId) {
		this.reqPurposeId = reqPurposeId;
	}
}
