package dutyroster.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import dutyroster.DutyRosterConfig;

public class ReliverRosterFB extends ActionForm {

	private String year;
	private String month;
	private String rosterMainCatg;
	private String rosterCatg;
	private String areaCode="";
	private String requestedEmpId="";
	private String reliverEmpId="";
	private String reason="";
	private String hmode;
	private String concatedData="";
	private String generatedRosterId;
	private String sysDate="";
	private String fromDate="";
	private String toDate="";
	private String isDutyOff="";
	private String shiftId="";
	private String nextToReliverToDate="";
	private String reasonForReliver="";
	private String dayOffReliverDate="";
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.year="-1";
		this.month="-1";
		this.rosterMainCatg = "-1";	
		this.rosterCatg = "-1";	
		this.concatedData="";
		this.areaCode="-1";
		this.generatedRosterId="";
		this.reason=DutyRosterConfig.RELIVER_REASON_EMPLOYEE;
		this.sysDate="";
		this.fromDate="";
		this.toDate="";
		this.isDutyOff="";
		this.shiftId="-1";
		this.nextToReliverToDate="";
		this.reliverEmpId="-1";
		this.requestedEmpId="-1";
		this.reasonForReliver="";
		this.dayOffReliverDate="";
	
	}





	public String getIsDutyOff() {
		return isDutyOff;
	}





	public void setIsDutyOff(String isDutyOff) {
		this.isDutyOff = isDutyOff;
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


	public String getRosterMainCatg() {
		return rosterMainCatg;
	}


	public void setRosterMainCatg(String rosterMainCatg) {
		this.rosterMainCatg = rosterMainCatg;
	}


	public String getRosterCatg() {
		return rosterCatg;
	}


	public void setRosterCatg(String rosterCatg) {
		this.rosterCatg = rosterCatg;
	}


	public String getAreaCode() {
		return areaCode;
	}


	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}


	public String getRequestedEmpId() {
		return requestedEmpId;
	}

	public void setRequestedEmpId(String requestedEmpId) {
		this.requestedEmpId = requestedEmpId;
	}
	public String getReliverEmpId() {
		return reliverEmpId;
	}


	public void setReliverEmpId(String reliverEmpId) {
		this.reliverEmpId = reliverEmpId;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getHmode() {
		return hmode;
	}


	public void setHmode(String hmode) {
		this.hmode = hmode;
	}


	public String getConcatedData() {
		return concatedData;
	}


	public void setConcatedData(String concatedData) {
		this.concatedData = concatedData;
	}


	public String getGeneratedRosterId() {
		return generatedRosterId;
	}


	public void setGeneratedRosterId(String generatedRosterId) {
		this.generatedRosterId = generatedRosterId;
	}


	public String getSysDate() {
		return sysDate;
	}


	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
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


	public String getShiftId() {
		return shiftId;
	}


	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}


	public String getNextToReliverToDate() {
		return nextToReliverToDate;
	}


	public void setNextToReliverToDate(String nextToReliverToDate) {
		this.nextToReliverToDate = nextToReliverToDate;
	}





	public String getReasonForReliver() {
		return reasonForReliver;
	}





	public void setReasonForReliver(String reasonForReliver) {
		this.reasonForReliver = reasonForReliver;
	}





	public String getDayOffReliverDate() {
		return dayOffReliverDate;
	}





	public void setDayOffReliverDate(String dayOffReliverDate) {
		this.dayOffReliverDate = dayOffReliverDate;
	}



	
	
	

	
		
}
