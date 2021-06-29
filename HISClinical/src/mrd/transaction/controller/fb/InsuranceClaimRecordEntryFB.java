package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class InsuranceClaimRecordEntryFB extends ActionForm
{
	private String hmode;
	private int currentPage;
	private String selectedInsuranceId;
	private String opinionRemarks;
	private String opinionDate;
	private String opinionBy;
	private String sendingMode;
	private String sysDate;
	private String insuranceId;
	private String receiveDateTime;
	
	
	public String getReceiveDateTime() {
		return receiveDateTime;
	}

	public void setReceiveDateTime(String receiveDateTime) {
		this.receiveDateTime = receiveDateTime;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.opinionRemarks="";
		this.opinionBy="-1";
		this.sendingMode=null;
	}
	
	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public String getSendingMode() {
		return sendingMode;
	}

	public void setSendingMode(String sendingMode) {
		this.sendingMode = sendingMode;
	}

	public String getOpinionRemarks() {
		return opinionRemarks;
	}

	public void setOpinionRemarks(String opinionRemarks) {
		this.opinionRemarks = opinionRemarks;
	}

	public String getOpinionDate() {
		return opinionDate;
	}

	public void setOpinionDate(String opinionDate) {
		this.opinionDate = opinionDate;
	}

	public String getOpinionBy() {
		return opinionBy;
	}

	public void setOpinionBy(String opinionBy) {
		this.opinionBy = opinionBy;
	}

	public String getSelectedInsuranceId() {
		return selectedInsuranceId;
	}

	public void setSelectedInsuranceId(String selectedInsuranceId) {
		this.selectedInsuranceId = selectedInsuranceId;
	}

	public InsuranceClaimRecordEntryFB()
	{
		this.currentPage=1;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
}
