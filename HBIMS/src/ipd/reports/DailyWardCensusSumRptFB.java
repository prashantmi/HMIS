package ipd.reports;

import org.apache.struts.action.ActionForm;

public class DailyWardCensusSumRptFB extends ActionForm{

	private static final long serialVersionUID = 02L;
	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strGrandTotalRequired="0";
	private String strDeptCheckRequired="0";
	private String strCase ="2";
			
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strUserRemarks = "";
	private String strErrMsg = "";
	private String strHospitalCode ="";
	private String strReportId = "0";
	private String strCurrentDate="";
	private String strReportDate="";
	private String StrSeatId="";
	private String strEffectiveTo;
	private String strEffectiveFrom;
	private String strWardCodeAll = "0";
	private String strWardAllValues = "";
	private String strDeptValues = "";
	
	private String strWardTypeVal="";
	private String strNewBornOnly="";
	private String strDateMonthWiseCase="1";
	private String strYearList="";
	private String strMonthValue="";
	private String strYear="";
	
	
	
	
	
	public String getStrWardTypeVal() {
		return strWardTypeVal;
	}
	public void setStrWardTypeVal(String strWardTypeVal) {
		this.strWardTypeVal = strWardTypeVal;
	}
	/*private String strUnitValues = "";
	private String strWardValues = "";*/
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
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
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
	/*public String getStrUnitValues() {
		return strUnitValues;
	}
	public void setStrUnitValues(String strUnitValues) {
		this.strUnitValues = strUnitValues;
	}
	public String getStrWardValues() {
		return strWardValues;
	}
	public void setStrWardValues(String strWardValues) {
		this.strWardValues = strWardValues;
	}*/
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrReportDate() {
		return strReportDate;
	}
	public void setStrReportDate(String strReportDate) {
		this.strReportDate = strReportDate;
	}
	public String getStrSeatId() {
		return StrSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		StrSeatId = strSeatId;
	}
	public String getStrGrandTotalRequired() {
		return strGrandTotalRequired;
	}
	public void setStrGrandTotalRequired(String strGrandTotalRequired) {
		this.strGrandTotalRequired = strGrandTotalRequired;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrDeptCheckRequired() {
		return strDeptCheckRequired;
	}
	public void setStrDeptCheckRequired(String strDeptCheckRequired) {
		this.strDeptCheckRequired = strDeptCheckRequired;
	}
	public String getStrWardCodeAll() {
		return strWardCodeAll;
	}
	public void setStrWardCodeAll(String strWardCodeAll) {
		this.strWardCodeAll = strWardCodeAll;
	}
	public String getStrWardAllValues() {
		return strWardAllValues;
	}
	public void setStrWardAllValues(String strWardAllValues) {
		this.strWardAllValues = strWardAllValues;
	}
	public String getStrNewBornOnly() {
		return strNewBornOnly;
	}
	public void setStrNewBornOnly(String strNewBornOnly) {
		this.strNewBornOnly = strNewBornOnly;
	}
	public String getStrDateMonthWiseCase() {
		return strDateMonthWiseCase;
	}
	public void setStrDateMonthWiseCase(String strDateMonthWiseCase) {
		this.strDateMonthWiseCase = strDateMonthWiseCase;
	}
	public String getStrYearList() {
		return strYearList;
	}
	public void setStrYearList(String strYearList) {
		this.strYearList = strYearList;
	}
	public String getStrYear() {
		return strYear;
	}
	public void setStrYear(String strYear) {
		this.strYear = strYear;
	}
	public String getStrMonthValue() {
		return strMonthValue;
	}
	public void setStrMonthValue(String strMonthValue) {
		this.strMonthValue = strMonthValue;
	}
}
