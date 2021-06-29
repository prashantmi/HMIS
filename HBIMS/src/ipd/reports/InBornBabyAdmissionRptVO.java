package ipd.reports;

import javax.sql.rowset.WebRowSet;

public class InBornBabyAdmissionRptVO {
	
private static final long serialVersionUID = 02L;
	
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strUserRemarks = "";
	
	private String strMsgString = "";
	private String strMsgType = "";
	
	private String strReportId = "0";
	private String strCase = "1";
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	
	private String strHospitalCode = ""; // Added By Pawan Kumar B N on 16-03-2012
	
	private WebRowSet strHospitalWs = null; // Added By Pawan Kumar B N on 16-03-2012
	private String strHospNameValues=""; // Added By Pawan Kumar B N on 16-03-2012
	private String strHospitalName = ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strSeatId = ""; // Added By Pawan Kumar B N on 16-03-2012

	private String strIsTimeRequired = "0"; // Added By Pawan Kumar B N on 16-03-2012
	private String strCurrentTime = ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strFromTime= ""; // Added By Pawan Kumar B N on 16-03-2012
	private String strToTime = ""; // Added By Pawan Kumar B N on 16-03-2012
	
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
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

}
