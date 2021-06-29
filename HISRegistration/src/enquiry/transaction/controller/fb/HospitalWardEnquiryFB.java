package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HospitalWardEnquiryFB extends ActionForm {

	private String hmode; 
	private String maximumRow;
	private String departmentCode;
	private String department;
	private String departmentUnitCode;
	private String departmentUnit;
	private String ward;
	private String wardCode;
	private String wardType;
	private String locationFloor;
	private String locationBlock;
	private String locationBuilding;
	private String bedProperty;
	private String isDirectCall;
	private int strWinResize;	
	
	private String hospitalCode;
	private String hospitalName;
	private String isPageList;
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
	}
	
	public String getBedProperty() {
		return bedProperty;
	}
	public void setBedProperty(String bedProperty) {
		this.bedProperty = bedProperty;
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
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
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
	public String getWardType() {
		return wardType;
	}
	public void setWardType(String wardType) {
		this.wardType = wardType;
	}
	public String getIsDirectCall() {
		return isDirectCall;
	}
	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getIsPageList() {
		return isPageList;
	}

	public void setIsPageList(String isPageList) {
		this.isPageList = isPageList;
	}
}
