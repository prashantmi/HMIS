package ipd.reports;

import org.apache.struts.action.ActionForm;

public class AdmittedPatientRptFB extends ActionForm{
	
	private static final long serialVersionUID = 02L;

	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strIsRoomWise = "0";
	private String strRoomNo = "0";
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strUserRemarks = "0";
	private String strErrMsg = "";
	private String strHospitalCode ="";
	private String strSeatId ="";
	private String strCurrentDate = "";
	private String strCategory = null;
	private String strModeVal = "0";

	private String strEffectiveFrom = "";
	private String strEffectiveTo = "";
	private String strDeptValues = "";
//	private String strUnitValues = "";
//	private String strWardValues = "";
//	private String strRoomValues = "";
	private String strOnlyAcceptedPatient="";
	private String strHospname="";
	private String strHospNameValues="";
	private String strFromHrVal="";
	private String strToHrVal="";
	private String strFromMinVal="";
	private String strToMinVal="";
	private String strCurrentTime="";
	private String strIsTimeRequired="0";
	private String StrFromTime="";
	private String StrToTime="";	
	
	public String getStrIsTimeRequired() {
		return strIsTimeRequired;
	}
	public void setStrIsTimeRequired(String strIsTimeRequired) {
		this.strIsTimeRequired = strIsTimeRequired;
	}
	public String getStrCurrentTime() {
		return strCurrentTime;
	}
	public void setStrCurrentTime(String strCurrentTime) {
		this.strCurrentTime = strCurrentTime;
	}
	public String getStrOnlyAcceptedPatient() {
		return strOnlyAcceptedPatient;
	}
	public void setStrOnlyAcceptedPatient(String strOnlyAcceptedPatient) {
		this.strOnlyAcceptedPatient = strOnlyAcceptedPatient;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	
	
	
	
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrIsRoomWise() {
		return strIsRoomWise;
	}
	public void setStrIsRoomWise(String strIsRoomWise) {
		this.strIsRoomWise = strIsRoomWise;
	}
	public String getStrRoomNo() {
		return strRoomNo;
	}
	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
	/*public String getStrUnitValues() {
		return strUnitValues;
	}
	public void setStrUnitValues(String strUnitValues) {
		this.strUnitValues = strUnitValues;
	}
	public String getStrWardValues() {
		return strWardValues;
	}
	public void setStrWardValues(String strWardValues) {
		this.strWardValues = strWardValues;
	}
	public String getStrRoomValues() {
		return strRoomValues;
	}
	public void setStrRoomValues(String strRoomValues) {
		this.strRoomValues = strRoomValues;
	}*/
	
	public String getStrIsFooter() {
		return strIsFooter;
	}
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public String getStrCategory() {
		return strCategory;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrModeVal() {
		return strModeVal;
	}
	public void setStrModeVal(String strModeVal) {
		this.strModeVal = strModeVal;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrHospname() {
		return strHospname;
	}
	public void setStrHospname(String strHospname) {
		this.strHospname = strHospname;
	}
	public String getStrHospNameValues() {
		return strHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		this.strHospNameValues = strHospNameValues;
	}
	public String getStrFromHrVal() {
		return strFromHrVal;
	}
	public void setStrFromHrVal(String strFromHrVal) {
		this.strFromHrVal = strFromHrVal;
	}
	public String getStrToHrVal() {
		return strToHrVal;
	}
	public void setStrToHrVal(String strToHrVal) {
		this.strToHrVal = strToHrVal;
	}
	public String getStrFromMinVal() {
		return strFromMinVal;
	}
	public void setStrFromMinVal(String strFromMinVal) {
		this.strFromMinVal = strFromMinVal;
	}
	public String getStrToMinVal() {
		return strToMinVal;
	}
	public void setStrToMinVal(String strToMinVal) {
		this.strToMinVal = strToMinVal;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getStrFromTime() {
		return StrFromTime;
	}
	public void setStrFromTime(String strFromTime) {
		StrFromTime = strFromTime;
	}
	public String getStrToTime() {
		return StrToTime;
	}
	public void setStrToTime(String strToTime) {
		StrToTime = strToTime;
	}

}
