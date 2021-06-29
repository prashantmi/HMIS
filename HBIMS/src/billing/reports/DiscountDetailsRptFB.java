package billing.reports;

import org.apache.struts.action.ActionForm;

public class DiscountDetailsRptFB extends ActionForm {

private static final long serialVersionUID = 02L;
	
	private String strGroupCode = "0";
	private String strDeptCode = "0";
	private String strTariffCode = "0";
	private String strHospService = "0";
	private String strCategory = "0";
	private String strReason = "0";
	private String strConsult = "0";
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strErrMsg = "";
	private String strCase = "3";
	private String strHospname="";
	private String strHospNameValues="";
	
	private String strGroupValues="";
	private String strHospitalCode = "";
	private String strReportFormat = "0";
	private String strUserRemarks = "";
	private String strHospSerValues = "";
	private String strConsultValues = "";
	private String strTariffValues = "";
	private String strReasonValues = "";
	private String strCategoryValues = "";
	private String strEffectiveFrom =null;
	private String strEffectiveTo =null;
	private String strCurrentDate = "";
	private String strChargeTypeId = "";
	
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrGroupCode() {
		return strGroupCode;
	}
	public void setStrGroupCode(String strGroupCode) {
		this.strGroupCode = strGroupCode;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrCategory() {
		return strCategory;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
	public String getStrReason() {
		return strReason;
	}
	public void setStrReason(String strReason) {
		this.strReason = strReason;
	}
	public String getStrConsult() {
		return strConsult;
	}
	public void setStrConsult(String strConsult) {
		this.strConsult = strConsult;
	}
	public String getStrIsFooter() {
		return strIsFooter;
	}
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
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
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrConsultValues() {
		return strConsultValues;
	}
	public void setStrConsultValues(String strConsultValues) {
		this.strConsultValues = strConsultValues;
	}
	public String getStrReasonValues() {
		return strReasonValues;
	}
	public void setStrReasonValues(String strReasonValues) {
		this.strReasonValues = strReasonValues;
	}
	public String getStrCategoryValues() {
		return strCategoryValues;
	}
	public void setStrCategoryValues(String strCategoryValues) {
		this.strCategoryValues = strCategoryValues;
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
	
	public String getStrGroupValues() {
		return strGroupValues;
	}
	public void setStrGroupValues(String strGroupValues) {
		this.strGroupValues = strGroupValues;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}
	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}
	public String getStrTariffValues() {
		return strTariffValues;
	}
	public void setStrTariffValues(String strTariffValues) {
		this.strTariffValues = strTariffValues;
	}
	public String getStrTariffCode() {
		return strTariffCode;
	}
	public void setStrTariffCode(String strTariffCode) {
		this.strTariffCode = strTariffCode;
	}
	public String getStrHospSerValues() {
		return strHospSerValues;
	}
	public void setStrHospSerValues(String strHospSerValues) {
		this.strHospSerValues = strHospSerValues;
	}
	public String getStrHospService() {
		return strHospService;
	}
	public void setStrHospService(String strHospService) {
		this.strHospService = strHospService;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
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
