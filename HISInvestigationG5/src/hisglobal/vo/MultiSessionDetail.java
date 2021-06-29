package hisglobal.vo;

public class MultiSessionDetail extends ValueObject {
	private String  sessionNo;//auto generating for start
	private String  proposedTestDate;
	private String  appointmentSlotReferenceId;
	private String  appointmentSlotStartTime;
	private String  appointmentSlotEndTime;
	private String  isBilling;
	private String  testStatus;
	public String getSessionNo() {
		return sessionNo;
	}
	public void setSessionNo(String sessionNo) {
		this.sessionNo = sessionNo;
	}
	public String getProposedTestDate() {
		return proposedTestDate;
	}
	public void setProposedTestDate(String proposedTestDate) {
		this.proposedTestDate = proposedTestDate;
	}
	public String getAppointmentSlotReferenceId() {
		return appointmentSlotReferenceId;
	}
	public void setAppointmentSlotReferenceId(String appointmentSlotReferenceId) {
		this.appointmentSlotReferenceId = appointmentSlotReferenceId;
	}
	public String getAppointmentSlotStartTime() {
		return appointmentSlotStartTime;
	}
	public void setAppointmentSlotStartTime(String appointmentSlotStartTime) {
		this.appointmentSlotStartTime = appointmentSlotStartTime;
	}
	public String getAppointmentSlotEndTime() {
		return appointmentSlotEndTime;
	}
	public void setAppointmentSlotEndTime(String appointmentSlotEndTime) {
		this.appointmentSlotEndTime = appointmentSlotEndTime;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public String getIsBilling() {
		return isBilling;
	}
	public void setIsBilling(String isBilling) {
		this.isBilling = isBilling;
	}
		
}
