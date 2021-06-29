package mrd.vo;

import mrd.reports.controller.actionsupport.ReportSUP;



public class MRDDiagnosticStatisticsReportVO extends ReportSUP{

	private static final long serialVersionUID = 1L;
	private String strChoice;
	private String mode;
	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}


	private String strOrder;
	private String strOrderBy;
	private String strGroupBy;
	
	private String fromMonth;
	private String toMonth;
	private String fromMonthYear;
	private String toMonthYear;
	private String fromYear;
	private String toYear;
	
	private String strSeatId;
	private String strIsValid;
	private String strMsgString;
	private String strMsgType;
	private String strWarning;
	private String strMsg;
	private String strErrorMsg;

	
	
	public void reset()
	{
		strChoice="0";
		
		
		strOrder="";
	}

	
	public String getStrChoice() {
		return strChoice;
	}


	public void setStrChoice(String strChoice) {
		this.strChoice = strChoice;
	}

	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
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
	public void setStrReportType(String strReportType) {
		super.setReportType(strReportType);
	}
	public String getStrReportType() {
		return super.getReportType();
	}	
	public void setStrGraphOrText(String strGraphOrText) {
		super.setGraphOrText(strGraphOrText);
	}
	public String getStrGraphOrText() {
		return super.getGraphOrText();
	}	
	public String getTitle() {
		return super.getTitle();
	}
	public String getReportPath() {
		return super.getRptDesignPath();
	}	
	public String getHospitalCode() {
		return super.getHospitalCode();
	}
	public String getStrFromDate() {
		return super.getFromDate();
	}
	public String getStrToDate() {
		return super.getToDate();
	}
	public String getStrFromHour() {
		return super.getFromHour();
	}
	public String getStrToHour() {
		return super.getToHour();
	}
	public String getStrFromMin() {
		return super.getFromMin();
	}
	public String getStrToMin() {
		return super.getToMin();
	}


	public String getStrOrderBy() {
		return strOrderBy;
	}


	public void setStrOrderBy(String strOrderBy) {
		this.strOrderBy = strOrderBy;
	}


	public String getStrGroupBy() {
		return strGroupBy;
	}


	public void setStrGroupBy(String strGroupBy) {
		this.strGroupBy = strGroupBy;
	}


	public String getFromMonthYear() {
		return fromMonthYear;
	}


	public void setFromMonthYear(String fromMonthYear) {
		this.fromMonthYear = fromMonthYear;
	}


	public String getToMonthYear() {
		return toMonthYear;
	}


	public void setToMonthYear(String toMonthYear) {
		this.toMonthYear = toMonthYear;
	}


	public String getFromYear() {
		return fromYear;
	}


	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}


	public String getToYear() {
		return toYear;
	}


	public void setToYear(String toYear) {
		this.toYear = toYear;
	}


	public String getFromMonth() {
		return fromMonth;
	}


	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}


	public String getToMonth() {
		return toMonth;
	}


	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}


	public String getStrOrder() {
		return strOrder;
	}


	public void setStrOrder(String strOrder) {
		this.strOrder = strOrder;
	}
	

}


