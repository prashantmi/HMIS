package appointment.transactions.controller.actionsupport;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class NewAppointmentSUP extends AppointmentGlobalSUP
{
	protected static final long serialVersionUID = 1L;
	
	protected String patCrNo;
	protected String afterGo;
	protected String errorMessage;	
	
	protected String appointmentTime;
	protected String patFirstName;
	protected String patientType;
	protected String patMiddleName;
	protected String patLastName;
	protected String patAgeUnit;
	protected String patGuardianName;
	protected String patHusbandName;
	protected String appointmentTypeId;
	protected String mobileNo;
	protected String emailId;
	
	private String appointmentQueueNo;
	private String remarks;
	private String appointmentMode;
	private String patAge;
	private String patGenderCode;
	private String departmentUnitName;
	
	
	
	public String getAppointmentQueueNo() {
		return appointmentQueueNo;
	}

	public void setAppointmentQueueNo(String appointmentQueueNo) {
		this.appointmentQueueNo = appointmentQueueNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAppointmentMode() {
		return appointmentMode;
	}

	public void setAppointmentMode(String appointmentMode) {
		this.appointmentMode = appointmentMode;
	}

	
	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getPatFirstName() {
		return patFirstName;
	}

	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getPatMiddleName() {
		return patMiddleName;
	}

	public void setPatMiddleName(String patMiddleName) {
		this.patMiddleName = patMiddleName;
	}

	public String getPatLastName() {
		return patLastName;
	}

	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}

	

	public String getPatAge() {
		return patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getPatGenderCode() {
		return patGenderCode;
	}

	public void setPatGenderCode(String patGenderCode) {
		this.patGenderCode = patGenderCode;
	}

	public String getPatGuardianName() {
		return patGuardianName;
	}

	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}

	public String getPatHusbandName() {
		return patHusbandName;
	}

	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}

	public String getAppointmentTypeId() {
		return appointmentTypeId;
	}

	public void setAppointmentTypeId(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPatAgeUnit() {
		return patAgeUnit;
	}

	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPatCrNo() {
		return patCrNo;
	}

	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}

	public String getAfterGo() {
		return afterGo;
	}

	public void setAfterGo(String afterGo) {
		this.afterGo = afterGo;
	}

	public NewAppointmentSUP()
	{	
		
		
	}
	
	public void clear()
	{
		this.patCrNo="";
		this.errorMessage="";
		this.afterGo="0";	
		this.patientType="1";
	}
	
	/*  ## 		Modification Log							
##		Modify Date				:26thFeb'15 
##		Reason	(CR/PRS)		:SMS Integration
##		Modify By				:Sheeldarshi */
	public String getDepartmentUnitName() {
		return departmentUnitName;
	}
	public void setDepartmentUnitName(String departmentUnitName) {
		this.departmentUnitName = departmentUnitName;
	}
	//End:Sheeldarshi
	
}
