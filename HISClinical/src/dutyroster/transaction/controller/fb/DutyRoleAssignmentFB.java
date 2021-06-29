package dutyroster.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class DutyRoleAssignmentFB extends ActionForm {
	
	private String mode;
	private String hmode;
	private String rosterTypeID;
	private String shiftID;
	private String dutyAreaId;
	private String[] roleID;
	private String year;
	private String month;
	private String fromDate;
	private String toDate;
	private String generatedRosterId;
	private String currentDate;
	private String roleDateRangeId;
	private String fromDateOld;
	private String toDateOld;
	private String shiftName;
	private String rosterName;
	private String areaName;
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		
		this.mode="";
		this.rosterTypeID="-1";
		this.shiftID="";
		this.dutyAreaId="-1";
		this.generatedRosterId="";
		this.shiftName="";
		this.rosterName="";
		this.areaName="";
	}
	
	
	
	public String getRoleDateRangeId() {
		return roleDateRangeId;
	}
	public void setRoleDateRangeId(String roleDateRangeId) {
		this.roleDateRangeId = roleDateRangeId;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getGeneratedRosterId() {
		return generatedRosterId;
	}
	public void setGeneratedRosterId(String generatedRosterId) {
		this.generatedRosterId = generatedRosterId;
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
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getRosterTypeID() {
		return rosterTypeID;
	}
	public void setRosterTypeID(String rosterTypeID) {
		this.rosterTypeID = rosterTypeID;
	}
	public String getShiftID() {
		return shiftID;
	}
	public void setShiftID(String shiftID) {
		this.shiftID = shiftID;
	}
	public String getDutyAreaId() {
		return dutyAreaId;
	}
	public void setDutyAreaId(String dutyAreaId) {
		this.dutyAreaId = dutyAreaId;
	}
	public String[] getRoleID() {
		return roleID;
	}
	public void setRoleID(String[] roleID) {
		this.roleID = roleID;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFromDateOld() {
		return fromDateOld;
	}
	public void setFromDateOld(String fromDateOld) {
		this.fromDateOld = fromDateOld;
	}
	public String getToDateOld() {
		return toDateOld;
	}
	public void setToDateOld(String toDateOld) {
		this.toDateOld = toDateOld;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}



	public String getRosterName() {
		return rosterName;
	}



	public void setRosterName(String rosterName) {
		this.rosterName = rosterName;
	}



	public String getAreaName() {
		return areaName;
	}



	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
