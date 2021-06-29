package enquiry.vo;

import hisglobal.vo.ValueObject;

public class HospitalConsultantEnquiryVO extends ValueObject {

	private String consultantName;
	private String empNo;
	private String departmentCode;
	private String department;
	private String departmentUnitCode;
	private String departmentUnit;
	private String hod;
	private String hou;
	private String room;
	private String location;
	private String unitLocation;
	private String day;
	private String week;
	private String startTime;
	private String endTime;
	private String shiftTiming;
	private String designation;
	
	public String getShiftTiming() {
		return shiftTiming;
	}
	public void setShiftTiming(String shiftTiming) {
		this.shiftTiming = shiftTiming;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartmentUnitCode() {
		return departmentUnitCode;
	}
	public void setDepartmentUnitCode(String departmentUnitCode) {
		this.departmentUnitCode = departmentUnitCode;
	}
	public String getDepartmentUnit() {
		return departmentUnit;
	}
	public void setDepartmentUnit(String departmentUnit) {
		this.departmentUnit = departmentUnit;
	}
	public String getHod() {
		return hod;
	}
	public void setHod(String hod) {
		this.hod = hod;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getHou() {
		return hou;
	}
	public void setHou(String hou) {
		this.hou = hou;
	}
	public String getUnitLocation() {
		return unitLocation;
	}
	public void setUnitLocation(String unitLocation) {
		this.unitLocation = unitLocation;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
}
