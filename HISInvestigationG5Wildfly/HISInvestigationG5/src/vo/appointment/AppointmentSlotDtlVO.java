package vo.appointment;

import hisglobal.vo.ValueObject;

public class AppointmentSlotDtlVO  extends ValueObject
{
	private String actualParaRefId;
	private String hospCode;
	private String appointmentForId;
	private String appointmentForName;//value for the app ID..
	private String apptTypeId;
	private String slotDuration;
	private String slotDurationUnit;// ex-5mins..value should contain min only
	private String[] actualParameterId;
	private String paraId1="0";
	private String paraId2="0";
	private String paraId3="0";
	private String paraId4="0";
	private String paraId5="0";
	private String paraId6="0";
	private String paraId7="0";
	private String shiftId;
	private String slotStartTime;
	private String slotEndTime;
	private String strMsgType;
	private String strMsgString;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	private String appointmentDate;
	private String shiftStartTime;
	private String shiftEndTime;
	
	
	public String getActualParaRefId() {
		return actualParaRefId;
	}
	public void setActualParaRefId(String actualParaRefId) {
		this.actualParaRefId = actualParaRefId;
	}
	public String getHospCode() {
		return hospCode;
	}
	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}
	public String getAppointmentForId() {
		return appointmentForId;
	}
	public void setAppointmentForId(String appointmentForId) {
		this.appointmentForId = appointmentForId;
	}
	public String getAppointmentForName() {
		return appointmentForName;
	}
	public void setAppointmentForName(String appointmentForName) {
		this.appointmentForName = appointmentForName;
	}
	public String getApptTypeId() {
		return apptTypeId;
	}
	public void setApptTypeId(String apptTypeId) {
		this.apptTypeId = apptTypeId;
	}
	public String getSlotDuration() {
		return slotDuration;
	}
	public void setSlotDuration(String slotDuration) {
		this.slotDuration = slotDuration;
	}
	public String getSlotDurationUnit() {
		return slotDurationUnit;
	}
	public void setSlotDurationUnit(String slotDurationUnit) {
		this.slotDurationUnit = slotDurationUnit;
	}
	public String[] getActualParameterId() {
		return actualParameterId;
	}
	public void setActualParameterId(String[] actualParameterId) {
		this.actualParameterId = actualParameterId;
	}
	public String getParaId1() {
		return paraId1;
	}
	public void setParaId1(String paraId1) {
		this.paraId1 = paraId1;
	}
	public String getParaId2() {
		return paraId2;
	}
	public void setParaId2(String paraId2) {
		this.paraId2 = paraId2;
	}
	public String getParaId3() {
		return paraId3;
	}
	public void setParaId3(String paraId3) {
		this.paraId3 = paraId3;
	}
	public String getParaId4() {
		return paraId4;
	}
	public void setParaId4(String paraId4) {
		this.paraId4 = paraId4;
	}
	public String getParaId5() {
		return paraId5;
	}
	public void setParaId5(String paraId5) {
		this.paraId5 = paraId5;
	}
	public String getParaId6() {
		return paraId6;
	}
	public void setParaId6(String paraId6) {
		this.paraId6 = paraId6;
	}
	public String getParaId7() {
		return paraId7;
	}
	public void setParaId7(String paraId7) {
		this.paraId7 = paraId7;
	}
	public String getShiftId() {
		return shiftId;
	}
	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}
	public String getSlotStartTime() {
		return slotStartTime;
	}
	public void setSlotStartTime(String slotStartTime) {
		this.slotStartTime = slotStartTime;
	}
	public String getSlotEndTime() {
		return slotEndTime;
	}
	public void setSlotEndTime(String slotEndTime) {
		this.slotEndTime = slotEndTime;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
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
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getShiftStartTime() {
		return shiftStartTime;
	}
	public void setShiftStartTime(String shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}
	public String getShiftEndTime() {
		return shiftEndTime;
	}
	public void setShiftEndTime(String shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}	
}