package new_investigation.reports.controller.fb;

import org.apache.struts.action.ActionForm;

public class InvestigationStatisticalReportFB extends ActionForm {
	
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
	private String deptCode="";
	private String hmode;
	private String fromDate="";
	private String toDate="";
	private String fromDate1="";
	private String toDate1="";
	private String sysDate="";
	private String reportMode="";
	private String fromHrTime="";
	private String fromMinTime="";
	private String toHrTime="";
	private String toMinTime="";
	private String today="";
	private String datewise="";
	private String datewise1="";
	private String dateTypeCheck;
	private String reportType;// rtf/pdf
 private String frmHr="";
 private String frmMin="";
 private String toHr="";
 private String toMin="";
 private String pat_type="";
 private String status="";
 private String sampleCode="";
 private String mode="";
	private String datetype="";
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
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
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrSample() {
		return strSample;
	}
	public void setStrSample(String strSample) {
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
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getHmode() {
		return hmode;
	}
	public void setHmode(String hmode) {
		this.hmode = hmode;
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
	public String getFromDate1() {
		return fromDate1;
	}
	public void setFromDate1(String fromDate1) {
		this.fromDate1 = fromDate1;
	}
	public String getToDate1() {
		return toDate1;
	}
	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
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
	public String getDatewise1() {
		return datewise1;
	}
	public void setDatewise1(String datewise1) {
		this.datewise1 = datewise1;
	}
	public String getDateTypeCheck() {
		return dateTypeCheck;
	}
	public void setDateTypeCheck(String dateTypeCheck) {
		this.dateTypeCheck = dateTypeCheck;
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
	public String getPat_type() {
		return pat_type;
	}
	public void setPat_type(String pat_type) {
		this.pat_type = pat_type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSampleCode() {
		return sampleCode;
	}
	public void setSampleCode(String sampleCode) {
		this.sampleCode = sampleCode;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getDatetype() {
		return datetype;
	}
	public void setDatetype(String datetype) {
		this.datetype = datetype;
	}
	
	
	
	
}
