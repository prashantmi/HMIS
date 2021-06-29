package inpatient.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DischargeRequestFB extends ActionForm
{
	private String patCrNo;
	private String episodeCode;
	private String episodeVisitNo;
	private String slNo;
	private String patAdmNo;
	private String dischargeRemarks;
	private String dischargeRemarksDisabled;
	private String revokeRemarks;
	private String nextVisitDate;
	private String requestType;
	private String empNo;
	private String hmode;
	private String patStatus;
	private String dischargeStatus;
	private String nextVisitDateFlag;
	private String nextVisitDays;
	private String sysDate="";
	private String profileStatus;
	private String dischargeStatusValue;//added vby swati sagr on date:11-06-2019

	
	public String getDischargeStatus() {
		return dischargeStatus;
	}
	public void setDischargeStatus(String dischargeStatus) {
		this.dischargeStatus = dischargeStatus;
	}
	public String getNextVisitDateFlag() {
		return nextVisitDateFlag;
	}
	public void setNextVisitDateFlag(String nextVisitDateFlag) {
		this.nextVisitDateFlag = nextVisitDateFlag;
	}
	public String getNextVisitDays() {
		return nextVisitDays;
	}
	public void setNextVisitDays(String nextVisitDays) {
		this.nextVisitDays = nextVisitDays;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getEpisodeVisitNo() {
		return episodeVisitNo;
	}
	public void setEpisodeVisitNo(String episodeVisitNo) {
		this.episodeVisitNo = episodeVisitNo;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getPatAdmNo() {
		return patAdmNo;
	}
	public void setPatAdmNo(String patAdmNo) {
		this.patAdmNo = patAdmNo;
	}
	public String getNextVisitDate() {
		return nextVisitDate;
	}
	public void setNextVisitDate(String nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getDischargeRemarks() {
		return dischargeRemarks;
	}
	public void setDischargeRemarks(String dischargeRemarks) {
		this.dischargeRemarks = dischargeRemarks;
	}
	public String getRevokeRemarks() {
		return revokeRemarks;
	}
	public void setRevokeRemarks(String revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}
	public String getPatStatus() {
		return patStatus;
	}
	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}
	public String getDischargeRemarksDisabled() {
		return dischargeRemarksDisabled;
	}
	public void setDischargeRemarksDisabled(String dischargeRemarksDisabled) {
		this.dischargeRemarksDisabled = dischargeRemarksDisabled;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDischargeRemarks("");
		this.setRevokeRemarks("");
		
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getProfileStatus() {
		return profileStatus;
	}
	public void setProfileStatus(String profileStatus) {
		this.profileStatus = profileStatus;
	}
	public String getDischargeStatusValue() {
		return dischargeStatusValue;
	}
	public void setDischargeStatusValue(String dischargeStatusValue) {
		this.dischargeStatusValue = dischargeStatusValue;
	}
	
	
	
}
