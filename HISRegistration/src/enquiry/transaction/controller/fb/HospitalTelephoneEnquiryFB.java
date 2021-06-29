package enquiry.transaction.controller.fb;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class HospitalTelephoneEnquiryFB extends ActionForm {
	

	private String isDirectCall;
	
	private String departmentCode;
	private String department;
	private String telephoneNo;
	private String employeeName;
	private String searchBy;
	private String hmode;
	private String hospitalCode;
	private String hospitalName;
	private String isFinalCancelReqd="1";

	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		super.reset(mapping, request);
		this.isFinalCancelReqd="1";
		this.employeeName="";
	}
	
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	
	public String getIsDirectCall() {
		return isDirectCall;
	}

	public void setIsDirectCall(String isDirectCall) {
		this.isDirectCall = isDirectCall;
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

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
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

	public String getIsFinalCancelReqd() {
		return isFinalCancelReqd;
	}

	public void setIsFinalCancelReqd(String isFinalCancelReqd) {
		this.isFinalCancelReqd = isFinalCancelReqd;
	}

	

	
}
