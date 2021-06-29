package mrd.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SummonTrackingFB extends ActionForm
{
	private String hmode;
	private String selectCriteria;
	private String fromDate="";
	private String toDate="";
	private String employeeId;
	private String displayMode;
	private String noOfRecord="0";
	private String summonTypeId;
	private String sysdate;
	
	private String noOfReceivedSummon;
	private String noOfAssignedSummon;
	private String noOfAttendedSummon;
	private String noOfNotAttendedSummon;
	private String noOfAcceptedSummon;
	
	
	
	public String getSysdate() {
		return sysdate;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}

	public String getSummonTypeId() {
		return summonTypeId;
	}

	public void setSummonTypeId(String summonTypeId) {
		this.summonTypeId = summonTypeId;
	}

	public String getNoOfRecord() {
		return noOfRecord;
	}

	public void setNoOfRecord(String noOfRecord) {
		this.noOfRecord = noOfRecord;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.displayMode="";
		this.selectCriteria="-1";
		this.fromDate="";
		this.toDate="";
		this.employeeId="-1";
	}

	public String getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSelectCriteria() {
		return selectCriteria;
	}

	public void setSelectCriteria(String selectCriteria) {
		this.selectCriteria = selectCriteria;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}

	public String getNoOfReceivedSummon() {
		return noOfReceivedSummon;
	}

	public void setNoOfReceivedSummon(String noOfReceivedSummon) {
		this.noOfReceivedSummon = noOfReceivedSummon;
	}

	public String getNoOfAssignedSummon() {
		return noOfAssignedSummon;
	}

	public void setNoOfAssignedSummon(String noOfAssignedSummon) {
		this.noOfAssignedSummon = noOfAssignedSummon;
	}

	public String getNoOfAttendedSummon() {
		return noOfAttendedSummon;
	}

	public void setNoOfAttendedSummon(String noOfAttendedSummon) {
		this.noOfAttendedSummon = noOfAttendedSummon;
	}

	public String getNoOfNotAttendedSummon() {
		return noOfNotAttendedSummon;
	}

	public void setNoOfNotAttendedSummon(String noOfNotAttendedSummon) {
		this.noOfNotAttendedSummon = noOfNotAttendedSummon;
	}

	public String getNoOfAcceptedSummon() {
		return noOfAcceptedSummon;
	}

	public void setNoOfAcceptedSummon(String noOfAcceptedSummon) {
		this.noOfAcceptedSummon = noOfAcceptedSummon;
	}
}
