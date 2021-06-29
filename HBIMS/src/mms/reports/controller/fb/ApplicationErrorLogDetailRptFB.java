package mms.reports.controller.fb;

import org.apache.struts.action.ActionForm;

public class ApplicationErrorLogDetailRptFB extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	
	private String strCurrentDate = "";
	private String strHospitalCode = "";
	private String strSeatId = "";
	
	private String strReportFormat = "";
	private String strIsFooter = "";
	
	private String strCase = "1";
	
	private String strPreferredFromTime;
	private String strPreferredToTime;
	
	private String strFromDate="";
	private String strToDate="";
	
	private String strReportId = "";
	private String strUserRemarks = "";
	
	private String strWhetherConsolidated;
	
	private String strErrorId;

	public String getStrErrorId() {
		return strErrorId;
	}
	public void setStrErrorId(String strErrorId) {
		this.strErrorId = strErrorId;
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
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrFromDate() {
		return strFromDate;
	}
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	public String getStrToDate() {
		return strToDate;
	}
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
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
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrWhetherConsolidated() {
		return strWhetherConsolidated;
	}
	public void setStrWhetherConsolidated(String strWhetherConsolidated) {
		this.strWhetherConsolidated = strWhetherConsolidated;
	}
	public String getStrPreferredFromTime() {
		return strPreferredFromTime;
	}
	public void setStrPreferredFromTime(String strPreferredFromTime) {
		this.strPreferredFromTime = strPreferredFromTime;
	}
	public String getStrPreferredToTime() {
		return strPreferredToTime;
	}
	public void setStrPreferredToTime(String strPreferredToTime) {
		this.strPreferredToTime = strPreferredToTime;
	}

}
