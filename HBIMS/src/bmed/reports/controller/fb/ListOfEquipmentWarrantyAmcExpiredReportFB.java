package bmed.reports.controller.fb;

import org.apache.struts.action.ActionForm;

public class ListOfEquipmentWarrantyAmcExpiredReportFB extends ActionForm{
	
	private static final long serialVersionUID = 02L;
	
	private String strNormalMsg="";
	private String strErrMsg="";
	private String strWarningMsg="";
	private String strHospitalCode="";
	
	
	private String strIsFooter="";
	private String strUserRemarks="";
	
	private String strReportFormat = "0";
	private String strReportId = "";

	private String strFromDate = "";
	private String strToDate = "";
	
	private String strCurrentDate="";

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

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
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

	public String getStrCurrentDate() {
		return strCurrentDate;
	}

	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	
	
}
