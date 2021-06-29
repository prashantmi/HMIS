package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class PostSummonDetailFB extends ActionForm
{
	private String hmode;
	private int currentPage;
	private String selectedSummonId;
	private String notAttendedReason;
	private String visitRemarks;
	private String nextVisitDate;
	private String hearingAttendedFlag;
	private String nextHearingTimeHr;
	private String nextHearingTimeMin;
	private String sysDate;
	
	
	public String getSysDate() {
		return sysDate;
	}

	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.selectedSummonId="";
		this.hearingAttendedFlag="";
	}
	
	public String getNextHearingTimeHr() {
		return nextHearingTimeHr;
	}

	public void setNextHearingTimeHr(String nextHearingTimeHr) {
		this.nextHearingTimeHr = nextHearingTimeHr;
	}

	public String getNextHearingTimeMin() {
		return nextHearingTimeMin;
	}

	public void setNextHearingTimeMin(String nextHearingTimeMin) {
		this.nextHearingTimeMin = nextHearingTimeMin;
	}

	public String getHearingAttendedFlag() {
		return hearingAttendedFlag;
	}

	public void setHearingAttendedFlag(String hearingAttendedFlag) {
		this.hearingAttendedFlag = hearingAttendedFlag;
	}

	public String getNotAttendedReason() {
		return notAttendedReason;
	}

	public void setNotAttendedReason(String notAttendedReason) {
		this.notAttendedReason = notAttendedReason;
	}

	public String getVisitRemarks() {
		return visitRemarks;
	}

	public void setVisitRemarks(String visitRemarks) {
		this.visitRemarks = visitRemarks;
	}

	public String getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(String nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}

	public String getSelectedSummonId() {
		return selectedSummonId;
	}

	public void setSelectedSummonId(String selectedSummonId) {
		this.selectedSummonId = selectedSummonId;
	}

	public PostSummonDetailFB()
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
}
