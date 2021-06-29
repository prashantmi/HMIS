package billing.reports;

import org.apache.struts.action.ActionForm;

public class DayEndCashHandoverRptFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strErrMsg = "";
	private String strModuleId = "0";
	private String strCounterName = "0";
	private String strFeeClerkName = "0";
	private String strGroupName = "0";
	private String strTariffName = "0";
	private String strDeptName = "0";
	private String strHospSerName = "0";
	private String strUserRemarks = "0";
	private String strHospitalCode = "";
	private String strReportFormat = "0";
	private String strCounterValues = "0";
	private String strFeeClerkValues = "0";
	private String strGroupValues = "0";
	private String strTariffValues = "0";
	private String strHospSerValues = "0";
	private String strDeptValues = "0";
	private String strCase = "1";
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strCurrentDate="";
	private String strTariffWise="";
	private String strHospname="";
	private String strHospNameValues="";
	private String strAllHospCode;
	private String strTariffType="0";
	private String  strCostType="";
	private String strCostTypeValue="";
	
	
	public String getStrTariffType() {
		return strTariffType;
	}
	public void setStrTariffType(String strTariffType) {
		this.strTariffType = strTariffType;
	}
	public String getStrTariffSelection() {
		return strTariffSelection;
	}
	public void setStrTariffSelection(String strTariffSelection) {
		this.strTariffSelection = strTariffSelection;
	}
	public String getStrLeftMenuList() {
		return strLeftMenuList;
	}
	public void setStrLeftMenuList(String strLeftMenuList) {
		this.strLeftMenuList = strLeftMenuList;
	}
	public String getStrRightMenuList() {
		return strRightMenuList;
	}
	public void setStrRightMenuList(String strRightMenuList) {
		this.strRightMenuList = strRightMenuList;
	}
	public String getStrLeftMenuNames() {
		return strLeftMenuNames;
	}
	public void setStrLeftMenuNames(String strLeftMenuNames) {
		this.strLeftMenuNames = strLeftMenuNames;
	}
	public String[] getStrRightMenuNames() {
		return strRightMenuNames;
	}
	public void setStrRightMenuNames(String[] strRightMenuNames) {
		this.strRightMenuNames = strRightMenuNames;
	}
	public String getStrDeptNameNew() {
		return strDeptNameNew;
	}
	public void setStrDeptNameNew(String strDeptNameNew) {
		this.strDeptNameNew = strDeptNameNew;
	}
	public String getStrTariffTypeNew() {
		return strTariffTypeNew;
	}
	public void setStrTariffTypeNew(String strTariffTypeNew) {
		this.strTariffTypeNew = strTariffTypeNew;
	}
	public String getStrSelectedTariffIds() {
		return strSelectedTariffIds;
	}
	public void setStrSelectedTariffIds(String strSelectedTariffIds) {
		this.strSelectedTariffIds = strSelectedTariffIds;
	}
	private String strTariffSelection="1";
	private String strLeftMenuList = "";
	private String strRightMenuList = "";
	private String strLeftMenuNames = "";
	private String strRightMenuNames[] = null;
	private String strDeptNameNew="";
	private String strTariffTypeNew="";
	private String strSelectedTariffIds="";
	
	
	
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
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
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrCounterName() {
		return strCounterName;
	}
	public void setStrCounterName(String strCounterName) {
		this.strCounterName = strCounterName;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrCounterValues() {
		return strCounterValues;
	}
	public void setStrCounterValues(String strCounterValues) {
		this.strCounterValues = strCounterValues;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrFeeClerkName() {
		return strFeeClerkName;
	}
	public void setStrFeeClerkName(String strFeeClerkName) {
		this.strFeeClerkName = strFeeClerkName;
	}
	public String getStrGroupName() {
		return strGroupName;
	}
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}
	public String getStrTariffName() {
		return strTariffName;
	}
	public void setStrTariffName(String strTariffName) {
		this.strTariffName = strTariffName;
	}
	public String getStrDeptName() {
		return strDeptName;
	}
	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}
	public String getStrFeeClerkValues() {
		return strFeeClerkValues;
	}
	public void setStrFeeClerkValues(String strFeeClerkValues) {
		this.strFeeClerkValues = strFeeClerkValues;
	}
	public String getStrGroupValues() {
		return strGroupValues;
	}
	public void setStrGroupValues(String strGroupValues) {
		this.strGroupValues = strGroupValues;
	}
	public String getStrTariffValues() {
		return strTariffValues;
	}
	public void setStrTariffValues(String strTariffValues) {
		this.strTariffValues = strTariffValues;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
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
	public String getStrTariffWise() {
		return strTariffWise;
	}
	public void setStrTariffWise(String strTariffWise) {
		this.strTariffWise = strTariffWise;
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
	public String getStrAllHospCode() {
		return strAllHospCode;
	}
	public void setStrAllHospCode(String strAllHospCode) {
		this.strAllHospCode = strAllHospCode;
	}
	public String getStrCostType() {
		return strCostType;
	}
	public void setStrCostType(String strCostType) {
		this.strCostType = strCostType;
	}
	public String getStrCostTypeValue() {
		return strCostTypeValue;
	}
	public void setStrCostTypeValue(String strCostTypeValue) {
		this.strCostTypeValue = strCostTypeValue;
	}
	
	
}