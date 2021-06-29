package vo.appointment;

import hisglobal.vo.ValueObject;

public class RescheduleCancelAppointmentVO extends ValueObject{
	
	private String appointmentNo;
	private String appointmentQueueNo;
	private String appointmentForId;
	private String patCrNo;
	private String episodeCode;
	private String patFirstName;	
	private String patMiddleName;
	private String patLastName;
	private String patGuardianName;
	private String patDOB;
	private String appointmentDate;
	private String emailId;
	private String mobileNo;	
	private String appointmentTime;
	private String appointmentStatus;
	private String slotType;
	private String remarks;
	private String appointmentTypeId;
	private String moduleSpecificCode;
	private String appointmentMode;
	private String moduleSpecificKeyName;
	private String patAgeUnit;
	private String patHusbandName;
	private String errorMessage;
	private String[] actualParameterId;
	private String patAge;
	private String patGenderCode;
	private String allActualParameterId;
	private String shiftId;
	private String statusRemarks;
	private String resheduleRemarks;
	private String slotST;
	private String slotET;
	private String paraRefId;
	private String sNo;

	public String getResheduleRemarks() {
		return resheduleRemarks;
	}
	public void setResheduleRemarks(String resheduleRemarks) {
		this.resheduleRemarks = resheduleRemarks;
	}
	
	public String getStatusRemarks() {
		return statusRemarks;
	}
	public void setStatusRemarks(String statusRemarks) {
		this.statusRemarks = statusRemarks;
	}
	public String getAllActualParameterId() {
		return allActualParameterId;
	}
	public void setAllActualParameterId(String allActualParameterId) {
		this.allActualParameterId = allActualParameterId;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getAppointmentNo() {
		System.out.println("VO::AppointmentNo"+appointmentNo);
		return appointmentNo;
	}
	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public String getAppointmentQueueNo() {
		return appointmentQueueNo;
	}
	public void setAppointmentQueueNo(String appointmentQueueNo) {
		this.appointmentQueueNo = appointmentQueueNo;
	}
	public String getAppointmentForId() {
		return appointmentForId;
	}
	public void setAppointmentForId(String appointmentForId) {
		this.appointmentForId = appointmentForId;
	}
	public String getPatCrNo() {
		return patCrNo;
	}
	public void setPatCrNo(String patCrNo) {
		this.patCrNo = patCrNo;
	}
	public String getEpisodeCode() {
		return episodeCode;
	}
	public void setEpisodeCode(String episodeCode) {
		this.episodeCode = episodeCode;
	}
	public String getPatFirstName() {
		return patFirstName;
	}
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
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
	public String getPatGuardianName() {
		return patGuardianName;
	}
	public void setPatGuardianName(String patGuardianName) {
		this.patGuardianName = patGuardianName;
	}
	
	public String getPatDOB() {
		return patDOB;
	}
	public void setPatDOB(String patDOB) {
		this.patDOB = patDOB;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public String getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public String getSlotType() {
		return slotType;
	}
	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAppointmentTypeId() {
		return appointmentTypeId;
	}
	public void setAppointmentTypeId(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}
	public String getModuleSpecificCode() {
		return moduleSpecificCode;
	}
	public void setModuleSpecificCode(String moduleSpecificCode) {
		this.moduleSpecificCode = moduleSpecificCode;
	}
	public String getAppointmentMode() {
		return appointmentMode;
	}
	public void setAppointmentMode(String appointmentMode) {
		this.appointmentMode = appointmentMode;
	}
	public String getModuleSpecificKeyName() {
		return moduleSpecificKeyName;
	}
	public void setModuleSpecificKeyName(String moduleSpecificKeyName) {
		this.moduleSpecificKeyName = moduleSpecificKeyName;
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
	public String getPatAgeUnit() {
		return patAgeUnit;
	}
	public void setPatAgeUnit(String patAgeUnit) {
		this.patAgeUnit = patAgeUnit;
	}
	public String getPatHusbandName() {
		return patHusbandName;
	}
	public void setPatHusbandName(String patHusbandName) {
		this.patHusbandName = patHusbandName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String[] getActualParameterId() {
		return actualParameterId;
	}
	public void setActualParameterId(String[] actualParameterId) {
		this.actualParameterId = actualParameterId;
	}
	public String getSlotST() {
		return slotST;
	}
	public void setSlotST(String slotST) {
		this.slotST = slotST;
	}
	public String getSlotET() {
		return slotET;
	}
	public void setSlotET(String slotET) {
		this.slotET = slotET;
	}
	public String getsNo() {
		return sNo;
	}
	public void setsNo(String sNo) {
		this.sNo = sNo;
	}
	public String getParaRefId() {
		return paraRefId;
	}
	public void setParaRefId(String paaraRefId) {
		this.paraRefId = paaraRefId;
	}
	
	}
