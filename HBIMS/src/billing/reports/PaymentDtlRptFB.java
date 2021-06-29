package billing.reports;

import org.apache.struts.action.ActionForm;

public class PaymentDtlRptFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strReportFormat = "0";
	private String strReportId = "0";
	private String strIsFooter = "0";
	private String strUserRemarks = "0";
	private String strErrMsg = "";
	private String strCurrentDate = "";
	private String strCase = "3";
	private String strHospitalCode = "";
	private String strHospSerValues = "0";
	private String strHospSerName = "0";
	private String strCrNo = "";
	private String strPatName = "";
	private String strHospname="";
	private String strHospNameValues="";
	private String strBillNo="";
	private String strReceiptNo="";
	private String strAllHospCode;
	
	private String strPaymentModeContents= "0"; // added by manisha dt: 23.8.18 for 'payment mode' combo
	private String[] strPaymentMode= null;
	private String strFeeClerk="";
	private String strFeeClerkValues="";
	
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
	public String getStrPatName() {
		return strPatName;
	}
	public void setStrPatName(String strPatName) {
		this.strPatName = strPatName;
	}
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
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrHospSerName() {
		return strHospSerName;
	}
	public void setStrHospSerName(String strHospSerName) {
		this.strHospSerName = strHospSerName;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrBillNo() {
		return strBillNo;
	}
	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}
	public String getStrReceiptNo() {
		return strReceiptNo;
	}
	public void setStrReceiptNo(String strReceiptNo) {
		this.strReceiptNo = strReceiptNo;
	}
	public String getStrAllHospCode() {
		return strAllHospCode;
	}
	public void setStrAllHospCode(String strAllHospCode) {
		this.strAllHospCode = strAllHospCode;
	}
	public String getStrPaymentModeContents() {
		return strPaymentModeContents;
	}
	public void setStrPaymentModeContents(String strPaymentModeContents) {
		this.strPaymentModeContents = strPaymentModeContents;
	}
	public String[] getStrPaymentMode() {
		return strPaymentMode;
	}
	public void setStrPaymentMode(String[] strPaymentMode) {
		this.strPaymentMode = strPaymentMode;
	}
	public String getStrFeeClerk() {
		return strFeeClerk;
	}
	public void setStrFeeClerk(String strFeeClerk) {
		this.strFeeClerk = strFeeClerk;
	}
	public String getStrFeeClerkValues() {
		return strFeeClerkValues;
	}
	public void setStrFeeClerkValues(String strFeeClerkValues) {
		this.strFeeClerkValues = strFeeClerkValues;
	}
	
	
}
