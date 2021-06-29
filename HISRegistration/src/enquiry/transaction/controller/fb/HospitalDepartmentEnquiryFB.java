package enquiry.transaction.controller.fb;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HospitalDepartmentEnquiryFB extends ActionForm {

	private String hmode; 
	private String maximumRow;
	private String departmentCode;
	private String departmentTypeCode;
	private String department;
	private String departmentShort;
	private String hod;
	private String location;
	private String departmentUnitCode;
	private String departmentUnit;
	private String ward;
	private String wardCode;
	private String hou;
	private String unitType;
	private String workingDays;
	private String timing;
	private String locationRoom;
	private String locationFloor;
	private String locationBlock;
	private String locationBuilding;
	private String bedProperty;
	private String room;
	private String searchText;
	private String list;
	private String flag="false";
	private String isDirectCall;
	private int strWinResize;
	
		
	public void reset(ActionMapping mapping, ServletRequest request) {
			super.reset(mapping, request);
			this.searchText="";
			this.departmentCode="";
	}
	public String getBedProperty() {
		return bedProperty;
	}
	public void setBedProperty(String bedProperty) {
		this.bedProperty = bedProperty;
	}
	public String getLocationRoom() {
		return locationRoom;
	}
	public void setLocationRoom(String locationRoom) {
		this.locationRoom = locationRoom;
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
	public String getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
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
	public String getHou() {
		return hou;
	}
	public void setHou(String hou) {
		this.hou = hou;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}
	public String getDepartmentTypeCode() {
		return departmentTypeCode;
	}
	public void setDepartmentTypeCode(String departmentTypeCode) {
		this.departmentTypeCode = departmentTypeCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getMaximumRow() {
		return maximumRow;
	}
	public void setMaximumRow(String maximumRow) {
		this.maximumRow = maximumRow;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getWardCode() {
		return wardCode;
	}
	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	public int getStrWinResize() {
		return strWinResize;
	}
	public void setStrWinResize(int strWinResize) {
		this.strWinResize = strWinResize;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIsDirectCall() {
		return isDirectCall;
	}
	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}
	public String getDepartmentShort() {
		return departmentShort;
	}
	public void setDepartmentShort(String departmentShort) {
		this.departmentShort = departmentShort;
	}
}
