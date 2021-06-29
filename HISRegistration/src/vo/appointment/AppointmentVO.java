package vo.appointment;

import hisglobal.vo.ValueObject;

public class AppointmentVO extends ValueObject {

	private String appointmentForId;
	private String appointmentName;
	private String multiAppointmentStatus;
	private String isTimingByAppointment;
	private String patAptNo;
	private String remarks;
	
	public String getAppointmentForId() {
		return appointmentForId;
	}
	public void setAppointmentForId(String appointmentForId) {
		this.appointmentForId = appointmentForId;
	}
	public String getAppointmentName() {
		return appointmentName;
	}
	public void setAppointmentName(String appointmentName) {
		this.appointmentName = appointmentName;
	}
	public String getMultiAppointmentStatus() {
		return multiAppointmentStatus;
	}
	public void setMultiAppointmentStatus(String multiAppointmentStatus) {
		this.multiAppointmentStatus = multiAppointmentStatus;
	}
	public String getIsTimingByAppointment() {
		return isTimingByAppointment;
	}
	public void setIsTimingByAppointment(String isTimingByAppointment) {
		this.isTimingByAppointment = isTimingByAppointment;
	}
	public String getPatAptNo() {
		return patAptNo;
	}
	public void setPatAptNo(String patAptNo) {
		this.patAptNo = patAptNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
}
