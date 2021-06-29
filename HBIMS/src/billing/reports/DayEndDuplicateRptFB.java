package billing.reports;

import org.apache.struts.action.ActionForm;

public class DayEndDuplicateRptFB extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	
	private String strCurrentDate = "";
	private String strSummaryNo = "";
	private String strHospitalCode = "";
	private String strFromDate = "";
	private String strToDate = "";
	private String strIsFooter = "";
	private String strUserRemarks = "";
	private String strReportFormat = "";
	private String strBillModuleId = "";
	private String strUserMode = "";
	private String strReportId = "";
	private String strHospname="";
	private String strHospNameValues="";

	private String strPrintCount = "0";
	
	private String strIsDetailsRequired = "0"; 
	private String strIsPayModeRequired = "0";
	private String strIsPayDetailRequired = "0";
	private String strDayEndDate = "";
	
	public String getStrIsPayModeRequired() {
		return strIsPayModeRequired;
	}
	public void setStrIsPayModeRequired(String strIsPayModeRequired) {
		this.strIsPayModeRequired = strIsPayModeRequired;
	}
	public String getStrIsPayDetailRequired() {
		return strIsPayDetailRequired;
	}
	public void setStrIsPayDetailRequired(String strIsPayDetailRequired) {
		this.strIsPayDetailRequired = strIsPayDetailRequired;
	}
	public String getStrIsDetailsRequired() {
		return strIsDetailsRequired;
	}
	public void setStrIsDetailsRequired(String strIsDetailsRequired) {
		this.strIsDetailsRequired = strIsDetailsRequired;
	}
	/**
	 * @return the strIsFooter
	 */
	public String getStrIsFooter() {
		return strIsFooter;
	}
	/**
	 * @param strIsFooter the strIsFooter to set
	 */
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	/**
	 * @return the strUserRemarks
	 */
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	/**
	 * @param strUserRemarks the strUserRemarks to set
	 */
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	/**
	 * @return the strReportFormat
	 */
	public String getStrReportFormat() {
		return strReportFormat;
	}
	/**
	 * @param strReportFormat the strReportFormat to set
	 */
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	/**
	 * @return the strBillModuleId
	 */
	public String getStrBillModuleId() {
		return strBillModuleId;
	}
	/**
	 * @param strBillModuleId the strBillModuleId to set
	 */
	public void setStrBillModuleId(String strBillModuleId) {
		this.strBillModuleId = strBillModuleId;
	}
	/**
	 * @return the strSummaryNo
	 */
	public String getStrSummaryNo() {
		return strSummaryNo;
	}
	/**
	 * @param strSummaryNo the strSummaryNo to set
	 */
	public void setStrSummaryNo(String strSummaryNo) {
		this.strSummaryNo = strSummaryNo;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}
	/**
	 * @param strErrMsg the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	/**
	 * @param strNormalMsg the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	/**
	 * @return the strWarningMsg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	/**
	 * @param strWarningMsg the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	/**
	 * @return the strFromDate
	 */
	public String getStrFromDate() {
		return strFromDate;
	}
	/**
	 * @param strFromDate the strFromDate to set
	 */
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	/**
	 * @return the strToDate
	 */
	public String getStrToDate() {
		return strToDate;
	}
	/**
	 * @param strToDate the strToDate to set
	 */
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	/**
	 * @return the strCurrentDate
	 */
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	/**
	 * @param strCurrentDate the strCurrentDate to set
	 */
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	/**
	 * @return the strReportId
	 */
	public String getStrReportId() {
		return strReportId;
	}
	/**
	 * @param strReportId the strReportId to set
	 */
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrUserMode() {
		return strUserMode;
	}
	public void setStrUserMode(String strUserMode) {
		this.strUserMode = strUserMode;
	}
	public String getStrPrintCount() {
		return strPrintCount;
	}
	public void setStrPrintCount(String strPrintCount) {
		this.strPrintCount = strPrintCount;
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
	public String getStrDayEndDate() {
		return strDayEndDate;
	}
	public void setStrDayEndDate(String strDayEndDate) {
		this.strDayEndDate = strDayEndDate;
	}
 

}
