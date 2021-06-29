package billing.reports;

import javax.sql.rowset.WebRowSet;

public class IncomeCorpStatementRptVO {
	
	private static final long serialVersionUID = 02L;

	private String strFromMonth = null;
	private String strToMonth = null;
	private String strFromYear = null;
	private String strToYear = null;
	private String strFromMonthYear = null;
	private String strToMonthYear = null;
	private String strMsgString = "";
	private String strMsgType = "";
	private String strReportFormat = "0";
	private String strHospSerName = "0";
	private String strHospitalCode = "";
	private String strCase = "1";
	private WebRowSet strHospSerWs = null;
	private String strGraph="";
	
	public String getStrGraph() {
		return strGraph;
	}
	public void setStrGraph(String strGraph) {
		this.strGraph = strGraph;
	}
	public String getStrFromMonth() {
		return strFromMonth;
	}
	public void setStrFromMonth(String strFromMonth) {
		this.strFromMonth = strFromMonth;
	}
	public String getStrToMonth() {
		return strToMonth;
	}
	public void setStrToMonth(String strToMonth) {
		this.strToMonth = strToMonth;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrFromYear() {
		return strFromYear;
	}
	public void setStrFromYear(String strFromYear) {
		this.strFromYear = strFromYear;
	}
	public String getStrToYear() {
		return strToYear;
	}
	public void setStrToYear(String strToYear) {
		this.strToYear = strToYear;
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
	public String getStrHospSerName() {
		return strHospSerName;
	}
	public void setStrHospSerName(String strHospSerName) {
		this.strHospSerName = strHospSerName;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public WebRowSet getStrHospSerWs() {
		return strHospSerWs;
	}
	public void setStrHospSerWs(WebRowSet strHospSerWs) {
		this.strHospSerWs = strHospSerWs;
	}
	public String getStrFromMonthYear() {
		return strFromMonthYear;
	}
	public void setStrFromMonthYear(String strFromMonthYear) {
		this.strFromMonthYear = strFromMonthYear;
	}
	public String getStrToMonthYear() {
		return strToMonthYear;
	}
	public void setStrToMonthYear(String strToMonthYear) {
		this.strToMonthYear = strToMonthYear;
	}
	

}
