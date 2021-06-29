package vo.appointment;

import appointment.reports.controller.actionsupport.AppointmentReportSUP;

public class AppontmentListingReportVO extends AppointmentReportSUP

{
	private static final long serialVersionUID = 1L;
	private String strChoice;
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	private String status;
	private String appointmentDtl;
	private String paraId;
	
	public void reset()
	{
		strChoice="0";
		appointmentDtl="0";
	
	}
	
	public String getStrChoice() {
		return strChoice;
	}
	public void setStrChoice(String strChoice) {
		this.strChoice = strChoice;
	}

	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrErrorMsg() {
		return strErrorMsg;
	}
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}	
	public void setStrReportType(String strReportType) {
		super.setReportType(strReportType);
	}
	public String getStrReportType() {
		return super.getReportType();
	}	
	public void setStrGraphOrText(String strGraphOrText) {
		super.setGraphOrText(strGraphOrText);
	}
	public String getStrGraphOrText() {
		return super.getGraphOrText();
	}	
	public String getTitle() {
		return super.getTitle();
	}
	public String getReportPath() {
		return super.getRptDesignPath();
	}	
	public String getHospitalCode() {
		return super.getHospitalCode();
	}
	public String getStrFromDate() {
		return super.getFromDate();
	}
	public String getStrToDate() {
		return super.getToDate();
	}
	public String getStrFromHour() {
		return super.getFromHour();
	}
	public String getStrToHour() {
		return super.getToHour();
	}
	public String getStrFromMin() {
		return super.getFromMin();
	}
	public String getStrToMin() {
		return super.getToMin();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppointmentDtl() {
		return appointmentDtl;
	}

	public void setAppointmentDtl(String appointmentDtl) {
		this.appointmentDtl = appointmentDtl;
	}

	

	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	
}
