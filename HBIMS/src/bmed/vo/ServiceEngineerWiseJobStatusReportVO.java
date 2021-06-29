package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class ServiceEngineerWiseJobStatusReportVO {

	private String strNormalMsg="";
	private String strErrMsg="";
	private String strWarningMsg="";
	private String strHospitalCode="";
	private String strSeatId="";
	
	
	private String strIsFooter="";
	private String strUserRemarks="";
	
	private String strItemCategNo = "";
	private String strReportFormat = "0";
	private String strReportId = "";

	private String strFromDate = "";
	private String strToDate = "";
	
	private String strServiceEngineerId="";
	private String strServiceEngineerCmb="";
	private String strMode="";
	
	private WebRowSet wrsServiceEngineerComboOptions;

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

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
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

	public String getStrItemCategNo() {
		return strItemCategNo;
	}

	public void setStrItemCategNo(String strItemCategNo) {
		this.strItemCategNo = strItemCategNo;
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

	public String getStrServiceEngineerId() {
		return strServiceEngineerId;
	}

	public void setStrServiceEngineerId(String strServiceEngineerId) {
		this.strServiceEngineerId = strServiceEngineerId;
	}

	public String getStrServiceEngineerCmb() {
		return strServiceEngineerCmb;
	}

	public void setStrServiceEngineerCmb(String strServiceEngineerCmb) {
		this.strServiceEngineerCmb = strServiceEngineerCmb;
	}

	public String getStrMode() {
		return strMode;
	}

	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	public WebRowSet getWrsServiceEngineerComboOptions() {
		return wrsServiceEngineerComboOptions;
	}

	public void setWrsServiceEngineerComboOptions(
			WebRowSet wrsServiceEngineerComboOptions) {
		this.wrsServiceEngineerComboOptions = wrsServiceEngineerComboOptions;
	}

}
