package new_investigation.reports.controller.fb;

import org.apache.struts.action.ActionForm;

public class PatientAcceptanceReportFB extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strReportFormat = "0";
	private String strReportId = "";
	private String strIsFooter = "";
	private String strUserRemarks = "";
	private String strNormalMsg="";
	private String strErrMsg="";
	private String strWarningMsg="";
	private String strCurrentDate = "";
	private String strFromDate = "";
	private String strToDate = "";
	private String strHospitalCode = "";
	private String strSample="";
	private String labCode;
	private String testCode="";
	private String hmode;
	private String fromDate="";
	private String toDate="";
	private String sysDate="";
	private String reportMode="";
	private String fromHrTime="";
	private String fromMinTime="";
	private String toHrTime="";
	private String toMinTime="";
	private String today="";
	private String datewise="";
   private String dateTypeCheck;
	private String reportType;// rtf/pdf
 private String frmHr="";
 private String frmMin="";
 private String toHr="";
 private String toMin="";
 private String datetype="";
 
 
 
 
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
	
	
	
	
	
	
	
	
	
	public String getStrSample() {
		return strSample;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrstrSample(String strSample) {
		this.strSample = strSample;
	}
	public String getLabCode() {
		return labCode;
	}
	public void setLabCode(String labCode) {
		this.labCode = labCode;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setStrSample(String strSample) {
		this.strSample = strSample;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	public String getReportMode() {
		return reportMode;
	}
	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}
	public String getFromHrTime() {
		return fromHrTime;
	}
	public void setFromHrTime(String fromHrTime) {
		this.fromHrTime = fromHrTime;
	}
	public String getFromMinTime() {
		return fromMinTime;
	}
	public void setFromMinTime(String fromMinTime) {
		this.fromMinTime = fromMinTime;
	}
	public String getToHrTime() {
		return toHrTime;
	}
	public void setToHrTime(String toHrTime) {
		this.toHrTime = toHrTime;
	}
	public String getToMinTime() {
		return toMinTime;
	}
	public void setToMinTime(String toMinTime) {
		this.toMinTime = toMinTime;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getDatewise() {
		return datewise;
	}
	public void setDatewise(String datewise) {
		this.datewise = datewise;
	}
	public String getDateTypeCheck() {
		return dateTypeCheck;
	}
	public void setDateTypeCheck(String dateTypeCheck) {
		this.dateTypeCheck = dateTypeCheck;
	}
	
	
	public void setStrReportType(String reportType) {
		this.reportType=reportType;
	}
	public String getStrReportType() {
		return reportType;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getFrmHr() {
		return frmHr;
	}
	public void setFrmHr(String frmHr) {
		this.frmHr = frmHr;
	}
	public String getFrmMin() {
		return frmMin;
	}
	public void setFrmMin(String frmMin) {
		this.frmMin = frmMin;
	}
	public String getToHr() {
		return toHr;
	}
	public void setToHr(String toHr) {
		this.toHr = toHr;
	}
	public String getToMin() {
		return toMin;
	}
	public void setToMin(String toMin) {
		this.toMin = toMin;
	}
	public String getDatetype() {
		return datetype;
	}
	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}	
	
	
	
	
	
	
	
	

}
