package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HospitalConsultantEnquiryFB extends ActionForm {
	
	private String consultantName;
	private String department;
	private String departmentCode;
	private String unit;
	private String room;
	private String location;
	private String empNo;
	private String unitRoomDays;
	private String departmentUnitCode;
	private String departmentUnit;
	private String designation;
	private String hmode;
	private String flag;
	private String workingDays;
	private String workingTimings;
	private String unitWorkingRoom;
	private String isDirectCall;
	private int currentPage=1;
	
	private String hospitalCode;
	private String hospitalName;
	private String isPageList;
	private String isFinalCancelReqd="1";
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	
		super.reset(mapping, request);
		this.consultantName="";
		this.departmentUnitCode="";
		this.departmentCode="";
		this.empNo="";
		this.currentPage=1;
		this.isFinalCancelReqd="1";
		
	}
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getUnitRoomDays() {
		return unitRoomDays;
	}
	public void setUnitRoomDays(String unitRoomDays) {
		this.unitRoomDays = unitRoomDays;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartmentCode() {
		return departmentCode;
	}
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
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
	public String getWorkingDays() {
		return workingDays;
	}
	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}
	public String getWorkingTimings() {
		return workingTimings;
	}
	public void setWorkingTimings(String workingTimings) {
		this.workingTimings = workingTimings;
	}
	public String getUnitWorkingRoom() {
		return unitWorkingRoom;
	}
	public void setUnitWorkingRoom(String unitWorkingRoom) {
		this.unitWorkingRoom = unitWorkingRoom;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	public String getIsFinalCancelReqd() {
		return isFinalCancelReqd;
	}
	public void setIsFinalCancelReqd(String isFinalCancelReqd) {
		this.isFinalCancelReqd = isFinalCancelReqd;
	}
}
