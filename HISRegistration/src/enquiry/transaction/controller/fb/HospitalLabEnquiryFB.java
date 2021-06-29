package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HospitalLabEnquiryFB extends ActionForm {

	private String hmode; 
	private String departmentCode;
	private String department;
	private String labCode;
	private String labName;
	private String labType;
	private String labIncharge;
	private String testName;
	private String locationRoom;
	private String locationFloor;
	private String locationBlock;
	private String locationBuilding;
	private String isDirectCall;
	private int currentPage;
	
	private String hospitalCode;
	private String hospitalName;
	private String isHospitalComboShown;
	private String isFinalCancelReqd="1";
	
	public HospitalLabEnquiryFB() {
		
		this.currentPage = 1;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.currentPage = 1;
		this.isFinalCancelReqd="1";
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

	public String getIsDirectCall() {
		return isDirectCall;
	}
	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
	}


	public String getLocationRoom() {
		return locationRoom;
	}


	public void setLocationRoom(String locationRoom) {
		this.locationRoom = locationRoom;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
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


	public String getIsHospitalComboShown() {
		return isHospitalComboShown;
	}


	public void setIsHospitalComboShown(String isHospitalComboShown) {
		this.isHospitalComboShown = isHospitalComboShown;
	}


	public String getIsFinalCancelReqd() {
		return isFinalCancelReqd;
	}


	public void setIsFinalCancelReqd(String isFinalCancelReqd) {
		this.isFinalCancelReqd = isFinalCancelReqd;
	}
}
