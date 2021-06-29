package mrd.transaction.controller.fb;

import org.apache.struts.action.ActionForm;

public class MrdMedicalCampFB extends ActionForm{
	
	private String hmode;
	private String strCampId;
	private String strCampName;
	private String strCampVenue;
	private String strCampRequisitionDate;
	private String strCampStartDate;
	private String strCampStartTime;
	private String strCampEndDate;
	private String strCampEndTime;
	private String strCampStartDateTime;
	private String strCampEndDateTime;
	private String strCampDescription;
	private String strIsCampClosed="1";
	private String strTotalNoofPatientAttended="0";
	private String strTotalMalePatientAttended="0";
	private String strTotalFemalePatientAttended="0";
	private String strTotalMaleChildPatientAttended="0";
	private String strTotalFemaleChildPatientAttended="0";
	private String strEmployeeName;
	private String strRole;
	private String strDutyRemarks;
	private String StrNormalMsg;
	private String campEndHr;
	private String campEndMin;
	private String campStartHr;
	private String campStartMin;
	
	private String[] strArrEmployeeName;
	private String[] strArrRole;
	private String[] strArrDutyRemarks;	
	private String[] strArrEmployeeId;
	
	private String chk;
	
	public String getStrCampId() {
		return strCampId;
	}
	public void setStrCampId(String strCampId) {
		this.strCampId = strCampId;
	}
	public String getStrCampName() {
		return strCampName;
	}
	public void setStrCampName(String strCampName) {
		this.strCampName = strCampName;
	}
	public String getStrCampVenue() {
		return strCampVenue;
	}
	public void setStrCampVenue(String strCampVenue) {
		this.strCampVenue = strCampVenue;
	}
	public String getStrCampRequisitionDate() {
		return strCampRequisitionDate;
	}
	public void setStrCampRequisitionDate(String strCampRequisitionDate) {
		this.strCampRequisitionDate = strCampRequisitionDate;
	}
	public String getStrCampStartDate() {
		return strCampStartDate;
	}
	public void setStrCampStartDate(String strCampStartDate) {
		this.strCampStartDate = strCampStartDate;
	}
	public String getStrCampStartTime() {
		return strCampStartTime;
	}
	public void setStrCampStartTime(String strCampStartTime) {
		this.strCampStartTime = strCampStartTime;
	}
	public String getStrCampEndDate() {
		return strCampEndDate;
	}
	public void setStrCampEndDate(String strCampEndDate) {
		this.strCampEndDate = strCampEndDate;
	}
	public String getStrCampEndTime() {
		return strCampEndTime;
	}
	public void setStrCampEndTime(String strCampEndTime) {
		this.strCampEndTime = strCampEndTime;
	}
	public String getStrCampDescription() {
		return strCampDescription;
	}
	public void setStrCampDescription(String strCampDescription) {
		this.strCampDescription = strCampDescription;
	}
	public String getStrIsCampClosed() {
		return strIsCampClosed;
	}
	public void setStrIsCampClosed(String strIsCampClosed) {
		this.strIsCampClosed = strIsCampClosed;
	}
	public String getStrTotalNoofPatientAttended() {
		return strTotalNoofPatientAttended;
	}
	public void setStrTotalNoofPatientAttended(String strTotalNoofPatientAttended) {
		this.strTotalNoofPatientAttended = strTotalNoofPatientAttended;
	}
	public String getStrTotalMalePatientAttended() {
		return strTotalMalePatientAttended;
	}
	public void setStrTotalMalePatientAttended(String strTotalMalePatientAttended) {
		this.strTotalMalePatientAttended = strTotalMalePatientAttended;
	}
	public String getStrTotalFemalePatientAttended() {
		return strTotalFemalePatientAttended;
	}
	public void setStrTotalFemalePatientAttended(
			String strTotalFemalePatientAttended) {
		this.strTotalFemalePatientAttended = strTotalFemalePatientAttended;
	}
	public String getStrTotalMaleChildPatientAttended() {
		return strTotalMaleChildPatientAttended;
	}
	public void setStrTotalMaleChildPatientAttended(
			String strTotalMaleChildPatientAttended) {
		this.strTotalMaleChildPatientAttended = strTotalMaleChildPatientAttended;
	}
	public String getStrTotalFemaleChildPatientAttended() {
		return strTotalFemaleChildPatientAttended;
	}
	public void setStrTotalFemaleChildPatientAttended(
			String strTotalFemaleChildPatientAttended) {
		this.strTotalFemaleChildPatientAttended = strTotalFemaleChildPatientAttended;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getStrCampStartDateTime() {
		return strCampStartDateTime;
	}
	public void setStrCampStartDateTime(String strCampStartDateTime) {
		this.strCampStartDateTime = strCampStartDateTime;
	}
	public String getStrCampEndDateTime() {
		return strCampEndDateTime;
	}
	public void setStrCampEndDateTime(String strCampEndDateTime) {
		this.strCampEndDateTime = strCampEndDateTime;
	}
	public String getStrEmployeeName() {
		return strEmployeeName;
	}
	public void setStrEmployeeName(String strEmployeeName) {
		this.strEmployeeName = strEmployeeName;
	}
	public String getStrRole() {
		return strRole;
	}
	public void setStrRole(String strRole) {
		this.strRole = strRole;
	}
	public String getStrDutyRemarks() {
		return strDutyRemarks;
	}
	public void setStrDutyRemarks(String strDutyRemarks) {
		this.strDutyRemarks = strDutyRemarks;
	}
	public String getStrNormalMsg() {
		return StrNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		StrNormalMsg = strNormalMsg;
	}
	public String[] getStrArrEmployeeName() {
		return strArrEmployeeName;
	}
	public void setStrArrEmployeeName(String[] strArrEmployeeName) {
		this.strArrEmployeeName = strArrEmployeeName;
	}
	public String[] getStrArrRole() {
		return strArrRole;
	}
	public void setStrArrRole(String[] strArrRole) {
		this.strArrRole = strArrRole;
	}
	public String[] getStrArrDutyRemarks() {
		return strArrDutyRemarks;
	}
	public void setStrArrDutyRemarks(String[] strArrDutyRemarks) {
		this.strArrDutyRemarks = strArrDutyRemarks;
	}
	public String[] getStrArrEmployeeId() {
		return strArrEmployeeId;
	}
	public void setStrArrEmployeeId(String[] strArrEmployeeId) {
		this.strArrEmployeeId = strArrEmployeeId;
	}
	public String getCampEndHr() {
		return campEndHr;
	}
	public void setCampEndHr(String campEndHr) {
		this.campEndHr = campEndHr;
	}
	public String getCampEndMin() {
		return campEndMin;
	}
	public void setCampEndMin(String campEndMin) {
		this.campEndMin = campEndMin;
	}
	public String getCampStartHr() {
		return campStartHr;
	}
	public void setCampStartHr(String campStartHr) {
		this.campStartHr = campStartHr;
	}
	public String getCampStartMin() {
		return campStartMin;
	}
	public void setCampStartMin(String campStartMin) {
		this.campStartMin = campStartMin;
	}
	public String getChk() {
		return chk;
	}
	public void setChk(String chk) {
		this.chk = chk;
	}
}
