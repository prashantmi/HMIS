package enquiry.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class OperationTheaterEnquiryFB extends ActionForm {

	private String hmode; 
	private String departmentCode;
	private String department;
	private String theaterCode;
	private String theaterName;
	private String operationType;
	private String operationTypeCode;
	
	
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
	public String getTheaterCode() {
		return theaterCode;
	}
	public void setTheaterCode(String theaterCode) {
		this.theaterCode = theaterCode;
	}
	public String getTheaterName() {
		return theaterName;
	}
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperationTypeCode() {
		return operationTypeCode;
	}
	public void setOperationTypeCode(String operationTypeCode) {
		this.operationTypeCode = operationTypeCode;
	}
	
}
