package opd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import registration.controller.fb.CRNoFB;

public class GenericPatientAlertFB extends CRNoFB
{
	private String patCrNo;
	private String alertName;
	private String patAlertId;
	private String effectiveFrom;
	private String remarks;
	private String durationDate;
	private String effectiveTo;
	private String episodeCode;
	private String episodeVisitNo;
	//private String revokeRemarks;
	private String admissionNo;
	private String hmode;
	private String hiddenAlertName;
	private String hiddenPatAlertId;
	private String revokeAlertId;
	private String[] revokeRemarks;
	private String[] revokeChronics;
	private String index;
	private String year;
	private String month;
	private String day;
	private String calculatedDays;;
	private String dob;
	private String sysDate="";
	private String dayOnAge;
	private String hiddenPatDeadStatus;
	private String showRemarks;
	private String showRevokeRemarks;
	private String snomedCtId;
	private String snomedCIdRemarks[]; // snomed integration
	private String snomedPTRemarks[];
	
	
	public String getHiddenPatDeadStatus() {
		return hiddenPatDeadStatus;
	}
	public void setHiddenPatDeadStatus(String hiddenPatDeadStatus) {
		this.hiddenPatDeadStatus = hiddenPatDeadStatus;
	}
	public String getRevokeAlertId() {
		return revokeAlertId;
	}
	public void setRevokeAlertId(String revokeAlertId) {
		this.revokeAlertId = revokeAlertId;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getAlertName() {
		return alertName;
	}
	public void setAlertName(String alertName) {
		this.alertName = alertName;
	}
	public String getPatAlertId() {
		return patAlertId;
	}
	public void setPatAlertId(String patAlertId) {
		this.patAlertId = patAlertId;
	}
	public String getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDurationDate() {
		return durationDate;
	}
	public void setDurationDate(String durationDate) {
		this.durationDate = durationDate;
	}
	public String getEffectiveTo() {
		return effectiveTo;
	}
	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
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
	/*public String getRevokeRemarks() {
		return revokeRemarks;
	}
	public void setRevokeRemarks(String revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}*/
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getHiddenAlertName() {
		return hiddenAlertName;
	}
	public void setHiddenAlertName(String hiddenAlertName) {
		this.hiddenAlertName = hiddenAlertName;
	}
	public String getHiddenPatAlertId() {
		return hiddenPatAlertId;
	}
	public void setHiddenPatAlertId(String hiddenPatAlertId) {
		this.hiddenPatAlertId = hiddenPatAlertId;
	}
	
	public void reset(ActionMapping mapping,HttpServletRequest request)
	{
		this.setDurationDate("");
		this.setRemarks("");
		this.setPatAlertId("");
		this.setAlertName("");
	}
	public String[] getRevokeRemarks() {
		return revokeRemarks;
	}
	public void setRevokeRemarks(String[] revokeRemarks) {
		this.revokeRemarks = revokeRemarks;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getCalculatedDays() {
		return calculatedDays;
	}
	public void setCalculatedDays(String calculatedDays) {
		this.calculatedDays = calculatedDays;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getDayOnAge() {
		return dayOnAge;
	}
	public void setDayOnAge(String dayOnAge) {
		this.dayOnAge = dayOnAge;
	}
	public String getShowRemarks() {
		return showRemarks;
	}
	public void setShowRemarks(String showRemarks) {
		this.showRemarks = showRemarks;
	}
	public String getShowRevokeRemarks() {
		return showRevokeRemarks;
	}
	public void setShowRevokeRemarks(String showRevokeRemarks) {
		this.showRevokeRemarks = showRevokeRemarks;
	}
	public String[] getRevokeChronics()
	{
		return revokeChronics;
	}
	public void setRevokeChronics(String[] revokeChronics)
	{
		this.revokeChronics = revokeChronics;
	}
	public String getSnomedCtId() {
		return snomedCtId;
	}
	public void setSnomedCtId(String snomedCtId) {
		this.snomedCtId = snomedCtId;
	}
	public String[] getSnomedCIdRemarks() {
		return snomedCIdRemarks;
	}
	public void setSnomedCIdRemarks(String[] snomedCIdRemarks) {
		this.snomedCIdRemarks = snomedCIdRemarks;
	}
	public String[] getSnomedPTRemarks() {
		return snomedPTRemarks;
	}
	public void setSnomedPTRemarks(String[] snomedPTRemarks) {
		this.snomedPTRemarks = snomedPTRemarks;
	}
	
}
