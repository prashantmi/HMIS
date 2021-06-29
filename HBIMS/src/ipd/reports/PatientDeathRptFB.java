package ipd.reports;

import org.apache.struts.action.ActionForm;

public class PatientDeathRptFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	
	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strConsultantName = "0";
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strUserRemarks = "0";
	private String strCase = "1";
	private String strErrMsg = "";
	private String strCurrentDate = "";
	private String strDeptValues = "";
	/*private String strUnitValues = "";
	private String strWardValues = "";*/
	private String strHospitalCode = "";
	private String strPatName= "";
	private String strCrNo = "";
	private String strAgeFrom = "";
	private String strAgeTo = "";
	private String strGenderCode = "0";
	private String strEffectiveFrom ="";
	private String strEffectiveTo = "";
	private String strAgeConditionReq="0";
	private String strGenderConditionReq="0";
	private String strSeatId="";
	private String strUnitReq="";
	
	private String strHospNameValues=""; // Added By Pawan Kumar B N on 16-03-2012
	private String strHospitalName = ""; // Added By Pawan Kumar B N on 16-03-2012

	private String strIsTimeRequired = "0"; // Added By Pawan Kumar B N on 16-03-2012
	private String strCurrentTime = ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strFromTime= ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strToTime = ""; // Added By Pawan Kumar B N on 16-03-2012
	
	
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
	public String getStrConsultantName() {
		return strConsultantName;
	}
	public void setStrConsultantName(String strConsultantName) {
		this.strConsultantName = strConsultantName;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
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
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
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
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
/*	public String getStrUnitValues() {
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
	
	}*/
	public String getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String strPatName) {
		this.strPatName = strPatName;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrAgeFrom() {
		return strAgeFrom;
	}
	public void setStrAgeFrom(String strAgeFrom) {
		this.strAgeFrom = strAgeFrom;
	}
	public String getStrAgeTo() {
		return strAgeTo;
	}
	public void setStrAgeTo(String strAgeTo) {
		this.strAgeTo = strAgeTo;
	}
	public String getStrGenderCode() {
		return strGenderCode;
	}
	public void setStrGenderCode(String strGenderCode) {
		this.strGenderCode = strGenderCode;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrAgeConditionReq() {
		return strAgeConditionReq;
	}
	public void setStrAgeConditionReq(String strAgeConditionReq) {
		this.strAgeConditionReq = strAgeConditionReq;
	}
	public String getStrGenderConditionReq() {
		return strGenderConditionReq;
	}
	public void setStrGenderConditionReq(String strGenderConditionReq) {
		this.strGenderConditionReq = strGenderConditionReq;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrUnitReq() {
		return strUnitReq;
	}
	public void setStrUnitReq(String strUnitReq) {
		this.strUnitReq = strUnitReq;
	}
	public String getStrHospNameValues() {
		return strHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		this.strHospNameValues = strHospNameValues;
	}
	public String getStrHospitalName() {
		return strHospitalName;
	}
	public void setStrHospitalName(String strHospitalName) {
		this.strHospitalName = strHospitalName;
	}
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
	public String getStrFromTime() {
		return strFromTime;
	}
	public void setStrFromTime(String strFromTime) {
		this.strFromTime = strFromTime;
	}
	public String getStrToTime() {
		return strToTime;
	}
	public void setStrToTime(String strToTime) {
		this.strToTime = strToTime;
	}

}
