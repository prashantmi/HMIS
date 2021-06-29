package billing.reports;

import org.apache.struts.action.ActionForm;

public class ConsolidatedRefundRptFB extends ActionForm {

	private static final long serialVersionUID = 02L;
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strErrMsg = "";
	private String strModuleId = "0";
	private String strCatType = "1";
	private String strCounterName = "0";
	private String strFeeClerkName = "0";
	private String strTreatCatName = "0";
	private String strDeptName = "0";
	private String strUserRemarks = "0";
	private String strHospitalCode = "";
	private String strReportFormat = "0";
	private String strCounterValues = "0";
	private String strFeeClerkValues = "0";
	private String strTreatCatValues = "0";
	private String strHospSerValues = "0";
	private String strDeptValues = "0";
	private String strChargeId = "0";
	private String strCase = "3";
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strCurrentDate="";
	private String strHospSerName = "0";
	//added by anshul on 3rd April 2012.
	private String strHospname="";
	private String strHospNameValues="";
	private String[] multipleHospitalCode;
	private String strHospitalCodes="";
	
	
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
	public String[] getMultipleHospitalCode() {
		return multipleHospitalCode;
	}
	public void setMultipleHospitalCode(String[] multipleHospitalCode) {
		this.multipleHospitalCode = multipleHospitalCode;
	}
	public String getStrHospitalCodes() {
		return strHospitalCodes;
	}
	public void setStrHospitalCodes(String strHospitalCodes) {
		this.strHospitalCodes = strHospitalCodes;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrCatType() {
		return strCatType;
	}
	public void setStrCatType(String strCatType) {
		this.strCatType = strCatType;
	}
	public String getStrCounterName() {
		return strCounterName;
	}
	public void setStrCounterName(String strCounterName) {
		this.strCounterName = strCounterName;
	}
	public String getStrFeeClerkName() {
		return strFeeClerkName;
	}
	public void setStrFeeClerkName(String strFeeClerkName) {
		this.strFeeClerkName = strFeeClerkName;
	}
	public String getStrTreatCatName() {
		return strTreatCatName;
	}
	public void setStrTreatCatName(String strTreatCatName) {
		this.strTreatCatName = strTreatCatName;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrCounterValues() {
		return strCounterValues;
	}
	public void setStrCounterValues(String strCounterValues) {
		this.strCounterValues = strCounterValues;
	}
	public String getStrFeeClerkValues() {
		return strFeeClerkValues;
	}
	public void setStrFeeClerkValues(String strFeeClerkValues) {
		this.strFeeClerkValues = strFeeClerkValues;
	}
	public String getStrTreatCatValues() {
		return strTreatCatValues;
	}
	public void setStrTreatCatValues(String strTreatCatValues) {
		this.strTreatCatValues = strTreatCatValues;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
	public String getStrChargeId() {
		return strChargeId;
	}
	public void setStrChargeId(String strChargeId) {
		this.strChargeId = strChargeId;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
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
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrHospSerValues() {
		return strHospSerValues;
	}
	public void setStrHospSerValues(String strHospSerValues) {
		this.strHospSerValues = strHospSerValues;
	}
	public String getStrHospSerName() {
		return strHospSerName;
	}
	public void setStrHospSerName(String strHospSerName) {
		this.strHospSerName = strHospSerName;
	}
}
