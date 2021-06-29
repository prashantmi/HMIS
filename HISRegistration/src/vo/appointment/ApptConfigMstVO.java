package vo.appointment;

import appointment.masters.controller.util.ApptConfigMstUTL;
import hisglobal.masterutil.GenericController;

public class ApptConfigMstVO  extends GenericController
{
	private String paraRefId;
	private String hospCode;
	private String appointmentForId;
	private String appointmentForName;//value for the app ID..
	private String apptTypeId;
	private String apptTypeIdSel;// has reference in jsp only..
	private String selApptTypesText;
	private String selApptTypesValues;
	private String defaultApptTypeId;
	private String apptDurationValue;// ex-5mins..value should contain 5 only
	private String apptDurationUnit;// ex-5mins..value should contain min only
	private String apptDurationUnitTime;
	private String isApptTransferable="2";// 2 NO,1 YES
	private String acceptTransferedAppt="2";// 2 NO,1 YES
	private String isQuotaShiftWise="1";// 2 NO,1 YES
	private String[] actualParameterId;
	private String[] currentDateAppt;// is the count of walk in appts in that particular day
	private String[] priorAppt;
	private String[] overBook;
	private String paraId1="0";
	private String paraId2="0";
	private String paraId3="0";
	private String paraId4="0";
	private String paraId5="0";
	private String paraId6="0";
	private String paraId7="0";
	private String labelPara1;//labels for corresponding paraids..
	private String labelPara2;
	private String labelPara3;
	private String labelPara4;
	private String labelPara5;
	private String labelPara6;
	private String labelPara7;
	private String[] isVipSlotAllowed;// 1 yes 2 NO
	private String[] portal;
	private String priorApptDays="30";
	private String[] shiftId;
	private String[] startTime;
	private String[] endTime;
	private String[] shiftwiseSelectedDays;
	private String strMsgType;
	private String strMsgString;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;
	private String finalParaId;
	private String[] weekOfMonth;
	private String depUnitCode;
	//By Mukund for Appt Config Mstr
	private String[] opdApptSlots;
	private String[] ipdApptSlots;
	
	
	public String[] getWeekOfMonth() {
		return weekOfMonth;
	}
	public void setWeekOfMonth(String[] weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}
	public ApptConfigMstVO(){
		
	}
	public ApptConfigMstVO(ApptConfigMstUTL apptConfigMstUTL, String ApptConfigMst,
			String appointment) {
		super(apptConfigMstUTL, ApptConfigMst, appointment);
	}
	public String getFinalParaId() {
		return finalParaId;
	}
	public void setFinalParaId(String finalParaId) {
		this.finalParaId = finalParaId;
	}
	public String getApptTypeId() {
		return apptTypeId;
	}
	public void setApptTypeId(String apptTypeId) {
		this.apptTypeId = apptTypeId;
	}
	public String getDefaultApptTypeId() {
		return defaultApptTypeId;
	}
	public void setDefaultApptTypeId(String defaultApptTypeId) {
		this.defaultApptTypeId = defaultApptTypeId;
	}
	public String getApptDurationValue() {
		return apptDurationValue;
	}
	public void setApptDurationValue(String apptDurationValue) {
		this.apptDurationValue = apptDurationValue;
	}
	public String getApptDurationUnit() {
		return apptDurationUnit;
	}
	public void setApptDurationUnit(String apptDurationUnit) {
		this.apptDurationUnit = apptDurationUnit;
	}
	public String getIsApptTransferable() {
		return isApptTransferable;
	}
	public void setIsApptTransferable(String isApptTransferable) {
		this.isApptTransferable = isApptTransferable;
	}
	public String getAcceptTransferedAppt() {
		return acceptTransferedAppt;
	}
	public void setAcceptTransferedAppt(String acceptTransferedAppt) {
		this.acceptTransferedAppt = acceptTransferedAppt;
	}
	public String getIsQuotaShiftWise() {
		return isQuotaShiftWise;
	}
	public void setIsQuotaShiftWise(String isQuotaShiftWise) {
		this.isQuotaShiftWise = isQuotaShiftWise;
	}
	
	public String[] getShiftId() {
		return shiftId;
	}
	public void setShiftId(String[] shiftId) {
		this.shiftId = shiftId;
	}
	public String[] getStartTime() {
		return startTime;
	}
	public void setStartTime(String[] startTime) {
		this.startTime = startTime;
	}
	public String[] getEndTime() {
		return endTime;
	}
	public void setEndTime(String[] endTime) {
		this.endTime = endTime;
	}
	
	public String getApptTypeIdSel() {
		return apptTypeIdSel;
	}
	public void setApptTypeIdSel(String apptTypeIdSel) {
		this.apptTypeIdSel = apptTypeIdSel;
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
	public String getPriorApptDays() {
		return priorApptDays;
	}
	public void setPriorApptDays(String priorApptDays) {
		this.priorApptDays = priorApptDays;
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
	public String getSelApptTypesText() {
		return selApptTypesText;
	}
	public void setSelApptTypesText(String selApptTypesText) {
		this.selApptTypesText = selApptTypesText;
	}
	public String getSelApptTypesValues() {
		return selApptTypesValues;
	}
	public void setSelApptTypesValues(String selApptTypesValues) {
		this.selApptTypesValues = selApptTypesValues;
	}
	public String[] getShiftwiseSelectedDays() {
		return shiftwiseSelectedDays;
	}
	public void setShiftwiseSelectedDays(String[] shiftwiseSelectedDays) {
		this.shiftwiseSelectedDays = shiftwiseSelectedDays;
	}
	public String[] getCurrentDateAppt() {
		return currentDateAppt;
	}
	public void setCurrentDateAppt(String[] currentDateAppt) {
		this.currentDateAppt = currentDateAppt;
	}
	public String[] getPriorAppt() {
		return priorAppt;
	}
	public void setPriorAppt(String[] priorAppt) {
		this.priorAppt = priorAppt;
	}
	public String[] getOverBook() {
		return overBook;
	}
	public void setOverBook(String[] overBook) {
		this.overBook = overBook;
	}
	public String[] getIsVipSlotAllowed() {
		return isVipSlotAllowed;
	}
	public void setIsVipSlotAllowed(String[] isVipSlotAllowed) {
		this.isVipSlotAllowed = isVipSlotAllowed;
	}
	public String[] getPortal() {
		return portal;
	}
	public void setPortal(String[] portal) {
		this.portal = portal;
	}
	public String getAppointmentForId() {
		return appointmentForId;
	}
	public void setAppointmentForId(String appointmentForId) {
		this.appointmentForId = appointmentForId;
	}
	public String[] getActualParameterId() {
		return actualParameterId;
	}
	public void setActualParameterId(String[] actualParameterId) {
		this.actualParameterId = actualParameterId;
	}
	public String getParaRefId() {
		return paraRefId;
	}
	public void setParaRefId(String paraRefId) {
		this.paraRefId = paraRefId;
	}
	public String getHospCode() {
		return hospCode;
	}
	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}
	public String getAppointmentForName() {
		return appointmentForName;
	}
	public void setAppointmentForName(String appointmentForName) {
		this.appointmentForName = appointmentForName;
	}
	public String getLabelPara1() {
		return labelPara1;
	}
	public void setLabelPara1(String labelPara1) {
		this.labelPara1 = labelPara1;
	}
	public String getLabelPara2() {
		return labelPara2;
	}
	public void setLabelPara2(String labelPara2) {
		this.labelPara2 = labelPara2;
	}
	public String getLabelPara3() {
		return labelPara3;
	}
	public void setLabelPara3(String labelPara3) {
		this.labelPara3 = labelPara3;
	}
	public String getLabelPara4() {
		return labelPara4;
	}
	public void setLabelPara4(String labelPara4) {
		this.labelPara4 = labelPara4;
	}
	public String getLabelPara5() {
		return labelPara5;
	}
	public void setLabelPara5(String labelPara5) {
		this.labelPara5 = labelPara5;
	}
	public String getLabelPara6() {
		return labelPara6;
	}
	public void setLabelPara6(String labelPara6) {
		this.labelPara6 = labelPara6;
	}
	public String getLabelPara7() {
		return labelPara7;
	}
	public void setLabelPara7(String labelPara7) {
		this.labelPara7 = labelPara7;
	}
	
	public String getApptDurationUnitTime() {
		return apptDurationUnitTime;
	}
	public void setApptDurationUnitTime(String apptDurationUnitTime) {
		this.apptDurationUnitTime = apptDurationUnitTime;
	}
	
	public String getDepUnitCode() {
		return depUnitCode;
	}
	public void setDepUnitCode(String depUnitCode) {
		this.depUnitCode = depUnitCode;
	}
	public String[] getOpdApptSlots() {
		return opdApptSlots;
	}
	public void setOpdApptSlots(String[] opdApptSlots) {
		this.opdApptSlots = opdApptSlots;
	}
	public String[] getIpdApptSlots() {
		return ipdApptSlots;
	}
	public void setIpdApptSlots(String[] ipdApptSlots) {
		this.ipdApptSlots = ipdApptSlots;
	}
}
