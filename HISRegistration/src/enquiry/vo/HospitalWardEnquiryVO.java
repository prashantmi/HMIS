package enquiry.vo;

import hisglobal.vo.ValueObject;

public class HospitalWardEnquiryVO extends ValueObject {

	private String departmentCode;
	private String department;
	private String departmentUnitCode;
	private String departmentUnit;
	private String wardCode;
	private String ward;
	private String wardType;
	private String room;
	private String bed;
	private String bedStatus;
	private String locationFloor;
	private String locationBlock;
	private String locationBuilding;
	
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
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
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

	public String getWardType() {
		return wardType;
	}
	public void setWardType(String wardType) {
		this.wardType = wardType;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getBed() {
		return bed;
	}
	public void setBed(String bed) {
		this.bed = bed;
	}
	public String getBedStatus() {
		return bedStatus;
	}
	public void setBedStatus(String bedStatus) {
		this.bedStatus = bedStatus;
	}
	
}
