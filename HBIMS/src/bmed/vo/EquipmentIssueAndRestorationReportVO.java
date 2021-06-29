package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class EquipmentIssueAndRestorationReportVO {

	private String strHospCode="";
	private String strIsFooter="";
	private String strUserRemarks="";
	
	private String strItemCategNo = "";
	private String strReportFormat = "0";
	private String strReportId = "";
	private String strProcessType="";


	private String strFromDate = "";
	private String strToDate = "";
	private String strDeptId;
	private String strDeptCmb;
	private String strItemId;
	private String strItemCmb;
	
	private String strMode="";
	private WebRowSet wrsDeptComboOptions;
	private WebRowSet wrsDeptItemComboOptions;
	
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
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
	public String getStrDeptId() {
		return strDeptId;
	}
	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}
	public String getStrDeptCmb() {
		return strDeptCmb;
	}
	public void setStrDeptCmb(String strDeptCmb) {
		this.strDeptCmb = strDeptCmb;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrItemCmb() {
		return strItemCmb;
	}
	public void setStrItemCmb(String strItemCmb) {
		this.strItemCmb = strItemCmb;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	public WebRowSet getWrsDeptComboOptions() {
		return wrsDeptComboOptions;
	}
	public void setWrsDeptComboOptions(WebRowSet wrsDeptComboOptions) {
		this.wrsDeptComboOptions = wrsDeptComboOptions;
	}
	public WebRowSet getWrsDeptItemComboOptions() {
		return wrsDeptItemComboOptions;
	}
	public void setWrsDeptItemComboOptions(WebRowSet wrsDeptItemComboOptions) {
		this.wrsDeptItemComboOptions = wrsDeptItemComboOptions;
	}
	public String getStrProcessType() {
		return strProcessType;
	}
	public void setStrProcessType(String strProcessType) {
		this.strProcessType = strProcessType;
	}
	
	
}
