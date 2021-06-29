package ipd.reports;

import javax.sql.rowset.WebRowSet;

public class PatientDischargeRptVO {

	private static final long serialVersionUID = 02L;
	private String strMsgString = "";
	private String strMsgType = "";
	
	
	private String strDeptCode = "0";
	private String strWardCode = "0";
	private String strUnitCode = null;
	private String strConsultantName = "0";
	private String strReportFormat = "0";
	
	private String strHospitalCode = "";
	private WebRowSet strDeptWs = null;
	private WebRowSet strWardWs = null;
	private WebRowSet strUnitWs = null;
	private WebRowSet strConsultWs = null;
	
	private String strPatName= "";
	private String strCrNo = "";
	private String strAgeFrom = "";
	private String strAgeTo = "";
	private String strGenderCode = "0";
	
	private WebRowSet strHospitalWs = null; // Added By Pawan Kumar B N on 16-03-2012
	private String strHospNameValues=""; // Added By Pawan Kumar B N on 16-03-2012
	private String strHospitalName = ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strSeatId = ""; // Added By Pawan Kumar B N on 16-03-2012

	private String strIsTimeRequired = "0"; // Added By Pawan Kumar B N on 16-03-2012
	private String strCurrentTime = ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strFromTime= ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strToTime = ""; // Added By Pawan Kumar B N on 16-03-2012
	
	
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
	public WebRowSet getStrConsultWs() {
		return strConsultWs;
	}
	public void setStrConsultWs(WebRowSet strConsultWs) {
		this.strConsultWs = strConsultWs;
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
	
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public WebRowSet getStrDeptWs() {
		return strDeptWs;
	}
	public void setStrDeptWs(WebRowSet strDeptWs) {
		this.strDeptWs = strDeptWs;
	}
	public WebRowSet getStrWardWs() {
		return strWardWs;
	}
	public void setStrWardWs(WebRowSet strWardWs) {
		this.strWardWs = strWardWs;
	}
	public WebRowSet getStrUnitWs() {
		return strUnitWs;
	}
	public void setStrUnitWs(WebRowSet strUnitWs) {
		this.strUnitWs = strUnitWs;
	}
	public String getStrConsultantName() {
		return strConsultantName;
	}
	public void setStrConsultantName(String strConsultantName) {
		this.strConsultantName = strConsultantName;
	}
	public WebRowSet getStrHospitalWs() {
		return strHospitalWs;
	}
	public void setStrHospitalWs(WebRowSet strHospitalWs) {
		this.strHospitalWs = strHospitalWs;
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
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
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
