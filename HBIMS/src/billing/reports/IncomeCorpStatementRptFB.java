package billing.reports;

import org.apache.struts.action.ActionForm;

public class IncomeCorpStatementRptFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	
	private String strFromMonth = "0";
	private String strToMonth = "0";
	private String strFromYear = null;
	private String strToYear = null;
	
	private String strFromMonthYear = null;
	private String strToMonthYear = null;
	
	
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strUserRemarks = "0";
	private String strErrMsg = "";
	private String strCurrentDate = "";
	private String strCase = "1";
	private String strHospitalCode = "";
	private String strHospSerValues = "0";
	private String strHospSerName = "0";
	private String strGraph="0";
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
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrHospSerValues() {
		return strHospSerValues;
	}
	public void setStrHospSerValues(String strHospSerValues) {
		this.strHospSerValues = strHospSerValues;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrHospSerName() {
		return strHospSerName;
	}
	public void setStrHospSerName(String strHospSerName) {
		this.strHospSerName = strHospSerName;
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
