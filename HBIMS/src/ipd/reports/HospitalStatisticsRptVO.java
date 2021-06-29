package ipd.reports;

import javax.sql.rowset.WebRowSet;

public class HospitalStatisticsRptVO {

	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";

	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strReportFormat = "0";
   
	private String strHospitalCode = "";

	private WebRowSet strDeptWs = null;
	private WebRowSet strUnitWs = null;
	private WebRowSet strWardWs = null;
	private WebRowSet strHospitalWs = null; 

	private String strHospNameValues="";

	private String strHospitalName = ""; 
	private String strSeatId = "";
	
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

	public WebRowSet getStrUnitWs() {
		return strUnitWs;
	}

	public void setStrUnitWs(WebRowSet strUnitWs) {
		this.strUnitWs = strUnitWs;
	}

	public WebRowSet getStrWardWs() {
		return strWardWs;
	}

	public void setStrWardWs(WebRowSet strWardWs) {
		this.strWardWs = strWardWs;
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
}
