package enquiry.vo;

import hisglobal.vo.ValueObject;

public class HospitalLabEnquiryVO extends ValueObject {

	private String departmentCode;
	private String department;
	private String labCode;
	private String labName;
	private String testName;
	private String labType;
	private String labIncharge;
	private String preRequisite;
	private String timeRequired;
	private String testCode;
	private String testCharges;
	private String testDay;
	
	private String locationRoom;
	private String locationFloor;
	private String locationBlock;
	private String locationBuilding;
	
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
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getLabType() {
		return labType;
	}
	public void setLabType(String labType) {
		this.labType = labType;
	}
	public String getLabIncharge() {
		return labIncharge;
	}
	public void setLabIncharge(String labIncharge) {
		this.labIncharge = labIncharge;
	}
	public String getLocationFloor() {
		return locationFloor;
	}
	public void setLocationFloor(String locationFloor) {
		this.locationFloor = locationFloor;
	}
	public String getLocationBlock() {
		return locationBlock;
	}
	public void setLocationBlock(String locationBlock) {
		this.locationBlock = locationBlock;
	}
	public String getLocationBuilding() {
		return locationBuilding;
	}
	public void setLocationBuilding(String locationBuilding) {
		this.locationBuilding = locationBuilding;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getPreRequisite() {
		return preRequisite;
	}
	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}
	public String getTimeRequired() {
		return timeRequired;
	}
	public void setTimeRequired(String timeRequired) {
		this.timeRequired = timeRequired;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTestCharges() {
		return testCharges;
	}
	public void setTestCharges(String testCharges) {
		this.testCharges = testCharges;
	}
	public String getLocationRoom() {
		return locationRoom;
	}
	public void setLocationRoom(String locationRoom) {
		this.locationRoom = locationRoom;
	}
	public String getTestDay() {
		return testDay;
	}
	public void setTestDay(String testDay) {
		this.testDay = testDay;
	}		
	
	
	
}
