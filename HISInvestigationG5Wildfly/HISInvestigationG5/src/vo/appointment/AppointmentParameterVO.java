package vo.appointment;

import javax.sql.rowset.WebRowSet;

import hisglobal.vo.ValueObject;

public class AppointmentParameterVO extends ValueObject {

	protected String appointmentForId;
	protected String displayOrder;
	protected String parameterId;
	protected String parameterName;	
	protected String parentParameterId;
	protected String allparameterId;
	protected String allactualParameterName;
	protected String actualParameterReferenceId;	
	protected String masterQuery;
	protected String transactionQuery;
	protected String readOnlyTransactionQuery;
	protected String isParameterForSlotMake;
	protected String optionHTML;
	protected String[] arrActualParaId;
	protected String[] arrActualParaName;
	protected String  patCrno;
	protected String  appointmentRequestNo;
	protected String appointmentForDate;
	protected String slotDuration;
	protected String shiftId;
	protected WebRowSet slotScheduleWS;
	protected String appointmentDatePopup;
	
	
	
	
	
	public String getAppointmentDatePopup() {
		return appointmentDatePopup;
	}
	public void setAppointmentDatePopup(String appointmentDatePopup) {
		this.appointmentDatePopup = appointmentDatePopup;
	}
	public String getOptionHTML() {
		return optionHTML;
	}
	public void setOptionHTML(String optionHTML) {
		this.optionHTML = optionHTML;
	}
	public String getTransactionQuery() {
		return transactionQuery;
	}
	public void setTransactionQuery(String transactionQuery) {
		this.transactionQuery = transactionQuery;
	}
	public String getIsParameterForSlotMake() {
		return isParameterForSlotMake;
	}
	public void setIsParameterForSlotMake(String isParameterForSlotMake) {
		this.isParameterForSlotMake = isParameterForSlotMake;
	}
	public String getMasterQuery() {
		return masterQuery;
	}
	public void setMasterQuery(String masterQuery) {
		this.masterQuery = masterQuery;
	}
	
	public String getReadOnlyTransactionQuery() {
		return readOnlyTransactionQuery;
	}
	public void setReadOnlyTransactionQuery(String readOnlyTransactionQuery) {
		this.readOnlyTransactionQuery = readOnlyTransactionQuery;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getAppointmentForId() {
		return appointmentForId;
	}
	public void setAppointmentForId(String appointmentForId) {
		this.appointmentForId = appointmentForId;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getParameterId() {
		return parameterId;
	}
	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	public String getParentParameterId() {
		return parentParameterId;
	}
	public void setParentParameterId(String parentParameterId) {
		this.parentParameterId = parentParameterId;
	}
	public String getAllparameterId() {
		return allparameterId;
	}
	public void setAllparameterId(String allparameterId) {
		this.allparameterId = allparameterId;
	}
	public String getAllactualParameterName() {
		return allactualParameterName;
	}
	public void setAllactualParameterName(String allactualParameterName) {
		this.allactualParameterName = allactualParameterName;
	}
	public String getActualParameterReferenceId() {
		return actualParameterReferenceId;
	}
	public void setActualParameterReferenceId(String actualParameterReferenceId) {
		this.actualParameterReferenceId = actualParameterReferenceId;
	}
	public String[] getArrActualParaId() {
		return arrActualParaId;
	}
	public void setArrActualParaId(String[] arrActualParaId) {
		this.arrActualParaId = arrActualParaId;
	}
	public String[] getArrActualParaName() {
		return arrActualParaName;
	}
	public void setArrActualParaName(String[] arrActualParaName) {
		this.arrActualParaName = arrActualParaName;
	}
	public String getPatCrno() {
		return patCrno;
	}
	public void setPatCrno(String patCrno) {
		this.patCrno = patCrno;
	}
	public String getAppointmentRequestNo() {
		return appointmentRequestNo;
	}
	public void setAppointmentRequestNo(String appointmentRequestNo) {
		this.appointmentRequestNo = appointmentRequestNo;
	}
	
	public String getAppointmentForDate() {
		return appointmentForDate;
	}
	public void setAppointmentForDate(String appointmentForDate) {
		this.appointmentForDate = appointmentForDate;
	}
	public String getSlotDuration() {
		return slotDuration;
	}
	public void setSlotDuration(String slotDuration) {
		this.slotDuration = slotDuration;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public WebRowSet getSlotScheduleWS() {
		return slotScheduleWS;
	}
	public void setSlotScheduleWS(WebRowSet slotScheduleWS) {
		this.slotScheduleWS = slotScheduleWS;
	}
	
	
}
