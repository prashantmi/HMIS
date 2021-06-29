package registration.master.controller.fb;

/**
 * Project   : MHIMS
 * Module 	 : Registration
 * Developer : Singaravelan
 * Creation Date : 25-Jul-2014
 * Process Name : Employee Master
 * 
 */


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class EmployeeMasterFB extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jobId;
	private String empNo;
	private String slNo;
	private String salutationId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String genderCode;
	private String birthDate;
	private String countryCode;
	private String hospitalCode;
	private String designationCode;
	private String deptCode;
	private String dateOfJoin;
	private String status;
	private String statusDate;
	private String finalstatus;
	private String finalstatusDate;
	private String oldEmployeeNo;
	private String isValid;
	
	private String contactNo;
	private String transactionMode;
	private String chk;
	private String viewOrModify;
	private String chkFlag;

	public void reset(ActionMapping mapping,HttpServletRequest request) 
	{
		this.salutationId="";
		this.firstName="";
		this.lastName="";
		this.middleName="";
		this.genderCode="";
		this.birthDate="";
		this.countryCode="";
		this.contactNo="";
		this.deptCode="";
		this.designationCode="";
		
	}
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getSlNo() {
		return slNo;
	}
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}
	public String getSalutationId() {
		return salutationId;
	}
	public void setSalutationId(String salutationId) {
		this.salutationId = salutationId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getHospitalCode() {
		return hospitalCode;
	}
	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	public String getDesignationCode() {
		return designationCode;
	}
	public void setDesignationCode(String designationCode) {
		this.designationCode = designationCode;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getFinalstatus() {
		return finalstatus;
	}
	public void setFinalstatus(String finalstatus) {
		this.finalstatus = finalstatus;
	}
	public String getFinalstatusDate() {
		return finalstatusDate;
	}
	public void setFinalstatusDate(String finalstatusDate) {
		this.finalstatusDate = finalstatusDate;
	}
	public String getOldEmployeeNo() {
		return oldEmployeeNo;
	}
	public void setOldEmployeeNo(String oldEmployeeNo) {
		this.oldEmployeeNo = oldEmployeeNo;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getTransactionMode() {
		return transactionMode;
	}
	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
	public String getViewOrModify() {
		return viewOrModify;
	}
	public void setViewOrModify(String viewOrModify) {
		this.viewOrModify = viewOrModify;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getChkFlag() {
		return chkFlag;
	}
	public void setChkFlag(String chkFlag) {
		this.chkFlag = chkFlag;
	}
	
	


}
