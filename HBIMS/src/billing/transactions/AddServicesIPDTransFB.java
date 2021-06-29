package billing.transactions;

import org.apache.struts.action.ActionForm;

public class AddServicesIPDTransFB extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	
	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strCtDate ="";
	private String strDateString = "";
	
	private String strCheckOutTime = "";
	private String strFlexAdmissionTime = "";
	private String strFlexDischargeTime = "";
	
	private String strIsPrivateRequired = "0";
	private String strIsGeneralRequired = "1";
	
	private String strCrNo = "";
	private String strDischargeDate = "";
	private String strPatientCategory = "";
	private String strAccountNo = "";
	
	
	private String strGeneralOffLineGroup = "";
	private String strPrivateOffLineGroup = "";
	
	private String strPatientCategoryValues = "";
	private String strOfflineGroupDetails = "";
	private String strPatientDetailsView = "";
	private String strAccountDetailsView = "";
 	
	private String strGeneralTariffDetails = "";
	private String strPrivateTariffDetails = "";
		
	 // Drop Down Data
	 private String strOffLineTariffDiscountUnit = "";
	 private String strOffLineTariffDiscountType ="";
	 private String strOffLineTariffDiscountBy ="";
	 private String strOfflineDiscountApprovedByDetails ="";
	 private String strOffLineTariffDiscountReason = "";
	 private String strOfflineDiscountRemarksDetails="";
	 private String strOffLineTariffDiscountDate="";
	
	 private String[] strCompChargeCheck = null;
	 private String[] strOfflineTariffName = null;
	 private String[] strOfflineTariffDetailsHidden = null;
 	 private String[] strOfflineTariffRateUnit = null;
	 private String[] strOfflineTariffQty = null;
	 private String[] strOfflineTariffUnit = null;
	 private String[] strOfflineTariffServiceTax = null;
	 private String[] strOfflineTariffServiceTaxAmtVal = null;
	 private String[] strOfflineTariffNetAmount = null;
	 private String[] strOfflineActualTariffAmtVal = null;
	
	
	public String getStrGeneralTariffDetails() {
		return strGeneralTariffDetails;
	}
	public void setStrGeneralTariffDetails(String strGeneralTariffDetails) {
		this.strGeneralTariffDetails = strGeneralTariffDetails;
	}
	public String getStrPrivateTariffDetails() {
		return strPrivateTariffDetails;
	}
	public void setStrPrivateTariffDetails(String strPrivateTariffDetails) {
		this.strPrivateTariffDetails = strPrivateTariffDetails;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
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
	public String getStrCtDate() {
		return strCtDate;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrDischargeDate() {
		return strDischargeDate;
	}
	public void setStrDischargeDate(String strDischargeDate) {
		this.strDischargeDate = strDischargeDate;
	}
	public String getStrPatientCategory() {
		return strPatientCategory;
	}
	public void setStrPatientCategory(String strPatientCategory) {
		this.strPatientCategory = strPatientCategory;
	}
	public String getStrPatientCategoryValues() {
		return strPatientCategoryValues;
	}
	public void setStrPatientCategoryValues(String strPatientCategoryValues) {
		this.strPatientCategoryValues = strPatientCategoryValues;
	}
	public String getStrPatientDetailsView() {
		return strPatientDetailsView;
	}
	public void setStrPatientDetailsView(String strPatientDetailsView) {
		this.strPatientDetailsView = strPatientDetailsView;
	}
	public String getStrAccountDetailsView() {
		return strAccountDetailsView;
	}
	public void setStrAccountDetailsView(String strAccountDetailsView) {
		this.strAccountDetailsView = strAccountDetailsView;
	}
	 
	public String getStrOffLineTariffDiscountUnit() {
		return strOffLineTariffDiscountUnit;
	}
	public void setStrOffLineTariffDiscountUnit(String strOffLineTariffDiscountUnit) {
		this.strOffLineTariffDiscountUnit = strOffLineTariffDiscountUnit;
	}
	public String getStrOffLineTariffDiscountType() {
		return strOffLineTariffDiscountType;
	}
	public void setStrOffLineTariffDiscountType(String strOffLineTariffDiscountType) {
		this.strOffLineTariffDiscountType = strOffLineTariffDiscountType;
	}
	public String getStrOffLineTariffDiscountBy() {
		return strOffLineTariffDiscountBy;
	}
	public void setStrOffLineTariffDiscountBy(String strOffLineTariffDiscountBy) {
		this.strOffLineTariffDiscountBy = strOffLineTariffDiscountBy;
	}
	public String getStrOfflineDiscountApprovedByDetails() {
		return strOfflineDiscountApprovedByDetails;
	}
	public void setStrOfflineDiscountApprovedByDetails(
			String strOfflineDiscountApprovedByDetails) {
		this.strOfflineDiscountApprovedByDetails = strOfflineDiscountApprovedByDetails;
	}
	public String getStrOffLineTariffDiscountReason() {
		return strOffLineTariffDiscountReason;
	}
	public void setStrOffLineTariffDiscountReason(
			String strOffLineTariffDiscountReason) {
		this.strOffLineTariffDiscountReason = strOffLineTariffDiscountReason;
	}
	public String getStrOfflineDiscountRemarksDetails() {
		return strOfflineDiscountRemarksDetails;
	}
	public void setStrOfflineDiscountRemarksDetails(
			String strOfflineDiscountRemarksDetails) {
		this.strOfflineDiscountRemarksDetails = strOfflineDiscountRemarksDetails;
	}
	public String getStrOffLineTariffDiscountDate() {
		return strOffLineTariffDiscountDate;
	}
	public void setStrOffLineTariffDiscountDate(String strOffLineTariffDiscountDate) {
		this.strOffLineTariffDiscountDate = strOffLineTariffDiscountDate;
	}
	public String[] getStrOfflineTariffName() {
		return strOfflineTariffName;
	}
	public void setStrOfflineTariffName(String[] strOfflineTariffName) {
		this.strOfflineTariffName = strOfflineTariffName;
	}
	public String[] getStrOfflineTariffDetailsHidden() {
		return strOfflineTariffDetailsHidden;
	}
	public void setStrOfflineTariffDetailsHidden(
			String[] strOfflineTariffDetailsHidden) {
		this.strOfflineTariffDetailsHidden = strOfflineTariffDetailsHidden;
	}
	public String[] getStrOfflineTariffRateUnit() {
		return strOfflineTariffRateUnit;
	}
	public void setStrOfflineTariffRateUnit(String[] strOfflineTariffRateUnit) {
		this.strOfflineTariffRateUnit = strOfflineTariffRateUnit;
	}
	public String[] getStrOfflineTariffQty() {
		return strOfflineTariffQty;
	}
	public void setStrOfflineTariffQty(String[] strOfflineTariffQty) {
		this.strOfflineTariffQty = strOfflineTariffQty;
	}
	public String[] getStrOfflineTariffUnit() {
		return strOfflineTariffUnit;
	}
	public void setStrOfflineTariffUnit(String[] strOfflineTariffUnit) {
		this.strOfflineTariffUnit = strOfflineTariffUnit;
	}
	public String[] getStrOfflineTariffServiceTax() {
		return strOfflineTariffServiceTax;
	}
	public void setStrOfflineTariffServiceTax(String[] strOfflineTariffServiceTax) {
		this.strOfflineTariffServiceTax = strOfflineTariffServiceTax;
	}
	public String[] getStrOfflineTariffServiceTaxAmtVal() {
		return strOfflineTariffServiceTaxAmtVal;
	}
	public void setStrOfflineTariffServiceTaxAmtVal(
			String[] strOfflineTariffServiceTaxAmtVal) {
		this.strOfflineTariffServiceTaxAmtVal = strOfflineTariffServiceTaxAmtVal;
	}
	public String[] getStrOfflineTariffNetAmount() {
		return strOfflineTariffNetAmount;
	}
	public void setStrOfflineTariffNetAmount(String[] strOfflineTariffNetAmount) {
		this.strOfflineTariffNetAmount = strOfflineTariffNetAmount;
	}
	public String[] getStrOfflineActualTariffAmtVal() {
		return strOfflineActualTariffAmtVal;
	}
	public void setStrOfflineActualTariffAmtVal(
			String[] strOfflineActualTariffAmtVal) {
		this.strOfflineActualTariffAmtVal = strOfflineActualTariffAmtVal;
	}
	public String getStrGeneralOffLineGroup() {
		return strGeneralOffLineGroup;
	}
	public void setStrGeneralOffLineGroup(String strGeneralOffLineGroup) {
		this.strGeneralOffLineGroup = strGeneralOffLineGroup;
	}
	public String getStrPrivateOffLineGroup() {
		return strPrivateOffLineGroup;
	}
	public void setStrPrivateOffLineGroup(String strPrivateOffLineGroup) {
		this.strPrivateOffLineGroup = strPrivateOffLineGroup;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrOfflineGroupDetails() {
		return strOfflineGroupDetails;
	}
	public void setStrOfflineGroupDetails(String strOfflineGroupDetails) {
		this.strOfflineGroupDetails = strOfflineGroupDetails;
	}
	public String getStrAccountNo() {
		return strAccountNo;
	}
	public void setStrAccountNo(String strAccountNo) {
		this.strAccountNo = strAccountNo;
	}
	public String getStrIsPrivateRequired() {
		return strIsPrivateRequired;
	}
	public void setStrIsPrivateRequired(String strIsPrivateRequired) {
		this.strIsPrivateRequired = strIsPrivateRequired;
	}
	public String getStrIsGeneralRequired() {
		return strIsGeneralRequired;
	}
	public void setStrIsGeneralRequired(String strIsGeneralRequired) {
		this.strIsGeneralRequired = strIsGeneralRequired;
	}
	public String getStrCheckOutTime() {
		return strCheckOutTime;
	}
	public void setStrCheckOutTime(String strCheckOutTime) {
		this.strCheckOutTime = strCheckOutTime;
	}
	public String getStrFlexAdmissionTime() {
		return strFlexAdmissionTime;
	}
	public void setStrFlexAdmissionTime(String strFlexAdmissionTime) {
		this.strFlexAdmissionTime = strFlexAdmissionTime;
	}
	public String getStrFlexDischargeTime() {
		return strFlexDischargeTime;
	}
	public void setStrFlexDischargeTime(String strFlexDischargeTime) {
		this.strFlexDischargeTime = strFlexDischargeTime;
	}
	public String getStrDateString() {
		return strDateString;
	}
	public void setStrDateString(String strDateString) {
		this.strDateString = strDateString;
	}
	public String[] getStrCompChargeCheck() {
		return strCompChargeCheck;
	}
	public void setStrCompChargeCheck(String[] strCompChargeCheck) {
		this.strCompChargeCheck = strCompChargeCheck;
	}
	
	
}
