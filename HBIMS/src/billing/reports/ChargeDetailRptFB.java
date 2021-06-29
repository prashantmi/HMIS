package billing.reports;

import org.apache.struts.action.ActionForm;

public class ChargeDetailRptFB extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;
	
	private String strHospitalCode = "";
	private String strHospSerValues = "";
	private String strCategoryValues = "";
	
	private String strIsFooter = "";
	private String strUserRemarks = "";
	private String strReportFormat = "0";
	private String strReportId = "";
	private String strCatCode = "";
	
	private String strCurrentDate = "";
	private String strChargeTypeId = "";
	private String strCategory = "";
	private String strGroupNameId = "";
	private String strCurrentRate = "1";
	private String strMonth = "";
	private String strYear = "";
	private String strHospname="";
	private String strHospNameValues="";
	private String strMonthVal = "";
	
	private String strRateVal = "1";
	
	private String strErrMsg = "";
	
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
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrCategoryValues() {
		return strCategoryValues;
	}
	public void setStrCategoryValues(String strCategoryValues) {
		this.strCategoryValues = strCategoryValues;
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
	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}
	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}
	public String getStrCategory() {
		return strCategory;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
	 
	public String getStrGroupNameId() {
		return strGroupNameId;
	}
	public void setStrGroupNameId(String strGroupNameId) {
		this.strGroupNameId = strGroupNameId;
	}
	public String getStrCurrentRate() {
		return strCurrentRate;
	}
	public void setStrCurrentRate(String strCurrentRate) {
		this.strCurrentRate = strCurrentRate;
	}
	public String getStrMonth() {
		return strMonth;
	}
	public void setStrMonth(String strMonth) {
		this.strMonth = strMonth;
	}
	public String getStrYear() {
		return strYear;
	}
	public void setStrYear(String strYear) {
		this.strYear = strYear;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrCatCode() {
		return strCatCode;
	}
	public void setStrCatCode(String strCatCode) {
		this.strCatCode = strCatCode;
	}
	public String getStrMonthVal() {
		return strMonthVal;
	}
	public void setStrMonthVal(String strMonthVal) {
		this.strMonthVal = strMonthVal;
	}
	public String getStrRateVal() {
		return strRateVal;
	}
	public void setStrRateVal(String strRateVal) {
		this.strRateVal = strRateVal;
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
}
