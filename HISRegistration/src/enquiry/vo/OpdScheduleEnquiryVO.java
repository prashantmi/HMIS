package enquiry.vo;

import hisglobal.vo.ValueObject;

public class OpdScheduleEnquiryVO extends ValueObject {

	private String departmentCode;
	private String department;
	private String departmentUnitCode;
	private String departmentUnit;
	private String deptUnitIsonduty;
	private String room;
	private String unitWokingDays;
	private String unitWokingTiming;
	private String unitDays;
	private String today;
	private String hod;
	
	
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

	public String getDeptUnitIsonduty() {
		return deptUnitIsonduty;
	}
	public void setDeptUnitIsonduty(String deptUnitIsonduty) {
		this.deptUnitIsonduty = deptUnitIsonduty;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getUnitWokingDays() {
		return unitWokingDays;
	}
	public void setUnitWokingDays(String unitWokingDays) {
		this.unitWokingDays = unitWokingDays;
	}
	public String getUnitWokingTiming() {
		return unitWokingTiming;
	}
	public void setUnitWokingTiming(String unitWokingTiming) {
		this.unitWokingTiming = unitWokingTiming;
	}
	public String getUnitDays() {
		return unitDays;
	}
	public void setUnitDays(String unitDays) {
		this.unitDays = unitDays;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getHod() {
		return hod;
	}
	public void setHod(String hod) {
		this.hod = hod;
	}
	
}
