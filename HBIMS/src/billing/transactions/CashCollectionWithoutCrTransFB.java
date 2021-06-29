package billing.transactions;

import java.util.LinkedHashMap;

import org.apache.struts.action.ActionForm;

public class CashCollectionWithoutCrTransFB extends ActionForm {

	private static final long serialVersionUID = 02L;
 
	java.util.LinkedHashMap<String, String> lhm = new java.util.LinkedHashMap<String, String>();

	// config parameters 
	private String strPatientMode = "2";
	private String strCounterMode = "0"; 
	private String strIsOnline = "0";
	private String strIsWithoutCrNoRequired = "0";
	private String strPreviousCrNoSearch = "0";
	private String strConfirmationType = "0";
	private String strIsOpdDiscount = "0";
	
	private String strIsPreviousCrNoReq = "0";
	private String strIsRefPhysicanReq = "0";
	private String strPrintMessageLimit = "0";
	// global parameters 
	
	
	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	
	// process specific parameters 
	
	private String strBillUsrFuncName ="";
	

	private String strTempBillNo = "";
	
	private String strTempReceiptNo = "";

	private String strWithoutCrNoOnlineRequestNo = "";
	
	
	private String strWardTypeId = "0";
	private String strGeneralChargeTypeId = "0";
	
	// combo values 
	private String strClientNameContents = "";
	private String strPaymentModeContents = "";
	private String strBillingServiceDetails = "";
	private String strRaisingDepartmentDetails = "";
	private String strOfflineGroupDetails = "";
	private String strOfflineTariffDetails = "";
	private String strOfflineDiscountApprovedByDetails = "";
	private String strOfflineDiscountRemarksDetails = "";
	private String strRequestNoValues = "";
	
	
	private String[] strTariffDiscountConfigDetails = null;
	
	

    private String strCurrentDate = "";
	private String strHospitalCode = "0";
	private String strIpAddress = "0";
	private String strSeatId = "";
	
	// config parameters 
	private String strModuleId = "0";
	private String strUserLevel = "0";
	private String strCounterId = "0";
	private String strPreviousCrNoDtlFlag = "0";
	private String strOffLineRefundPenalty = "0";
	
	
	private String strCrNo = "0";
	private String strPreviousCrNo = "";
	private String strBillNo = "0";
	private String strReceiptNo = "0";
	private String strRefundReceiptNo = "0";
	
	private String strOffLineHospitalService = "";
	private String strOffLineIsPackageGroup = "";
	
		
	private String strOffLineRefundAdvanceAccountNo = "0";
	
	private String strPreviousCrNoPatientDtls = "";
	
	 
	
	private String strOfflineTotalRecAmount = "0";
	private String strOfflineTotalPayAmount = "0";
	private String strOfflineRefundAmount = "0";
	private String strOfflineMaxClientBenefitAmount = "0";
	private String strOfflinePatNetPayAmount = "0";
	private String strOfflineTotalActualTariffAmount = "0";
	
	private String strOfflineTotalServiceTaxAmount = "0";
	private String strOfflineTotalDiscountAmount = "0";
	
	
	private String strOffLineGroup = "0";
	private String strOffLinePakageGroup = "0";
	private String strOfflineTariffName[] = null;
	private String strOfflineTariffDiscountApprovedBy = "";
	private String strOfflineTariffDiscountRemarks = "";

	private String[] strOfflineTariffDetailsHidden = null;
	private String[] strOfflineTariffQty = null;
	private String[] strOfflineTariffUnit = null;
	private String[] strOfflineTariffServiceTax = null;
	private String[] strOfflineTariffDiscount = null;
	private String[] strOfflineTariffDiscountConfigDetails = null;
	private String[] strOfflineTariffNetAmount = null;
	private String[] strOfflineTariffServiceTaxAmtVal = null;
	private String[] strOfflineTariffDiscountAmtVal = null;
	private String[] strOfflineTotalActualTariffAmtVal = null;
	
	private String[] strOfflinePaymentMode = null;
	private String[] strOfflinePaymentDtls = null;
	private String[] strOfflineAmount = null;
	
	private String[] strBillTariffVal = null;
	private String[] strBillTariffPenaltyType = null;
	private String[] strBillTariffPenalty = null;
	private String[] strBillTariffRefund = null;
	private String[] strBillTariffUnit = null;
	
	private String[] strOfflineRefundServiceTaxAmount = null;
	private String[] strOfflineRefundDiscountAmount = null;
	private String[] strOfflineRefundPenaltyAmount = null;
	private String[] strOfflineRefundActualTariffAmount = null;
	private String[] strBillTariffRefundAmount = null;
	private String[] strTariffDetailsId = null;
	private String[] strTariffBilledQty = null;
	private String[] strTariffServiceTaxAmt = null;
	private String[] strTariffDiscountAmt = null;
	private String[] strTariffNetAmt = null;
	
	
	private String strTotalTariffServiceTaxAmount = "0";
	private String strTotalTariffDiscountAmount = "0";
	private String strTotalTariffActualAmount = "0";
	private String strTotalTariffPenaltyAmount = "0";
	
	
	private String strOfflineRefundTotalServiceTaxAmount = "0";
	private String strOfflineRefundTotalDiscountAmount = "0";
	private String strOfflineRefundTotalPenaltyAmount = "0";
	private String strOfflineRefundTotalActualTariffAmount = "0";
	
	private String strRequestDate = "";
	private String strGlobalRequestNo = "";
	private String strService = "0";
	
	private String strOfflineRefundBillDetails = "";
	
	private String strOffLineRefundBy = "0";
	private String strOffLineRefundReason = "0";
	private String strOffLineRefundReasonText = "";
	private String strOfflineRefundDate = "";
	
	private String strOffLineTreatmentCategory = "";
	private String strOffLineWard = "";
 
	private String strTariffCode = "";
	private String strOffLinePackageIndex = "";
	private String strOffLineTariffUnitTempId = "";
	private String strOffLineTariffDetailsHiddenValue = "";
	private String strRefundTariffHiddenValue = "";
	private String strOffLineBillingService = "";
	private String strOffLineRaisingDepartment = "" ;
	private String strOffLineBillNumber = "";
	private String strGuarantorHiddenVal = "";
	private String strGenderList = "";
	private String strBillSearchString = "";
	private String strBillFromRow = "";
	private String strBillToRow = "";
	private String strBillRowPerPage = "";
	private String strBillCtBlockSet = "";
	private String strRequestNo = "";
	private String strChargeTypeId = "";
	
	
	private String strGuarantorName = "";
	private String strGuarantorContactNo = "0";
	private String strGuartorAddress = "";
	
	private String strReferringPhysician = "";
	private String strReferringPhysicianContactNo = "";
	private String strAge = "";
	private String strAgeUnit="0";
	private String strGender = "0";
	
	private String strGroupId = "";
	private String strQtyUnitId = "";
	private String strOffLineRemarks  = "";
	private String strOffLineRequestType = "";
	
	private String strOffLineAccountNo = "";
	private String strPatTypeBoth="";
	private String strPatientType="";
	private String strIntExtPat="";
	
	private String strConsumableCharge="0";
	private String strGroupIdForConsumableConcatenated="";
	private String strOfflineTotalPayAmountWithoutConsumable="0";
	
	private String strConsumableChargesGroupId="";
	private String strConsumableChargesTariffCode="";
	
	private String filePath="";
	private String isOpenPopUp="";
	
	
	public String getStrConsumableChargesGroupId() {
		return strConsumableChargesGroupId;
	}
	public void setStrConsumableChargesGroupId(String strConsumableChargesGroupId) {
		this.strConsumableChargesGroupId = strConsumableChargesGroupId;
	}
	public String getStrConsumableChargesTariffCode() {
		return strConsumableChargesTariffCode;
	}
	public void setStrConsumableChargesTariffCode(
			String strConsumableChargesTariffCode) {
		this.strConsumableChargesTariffCode = strConsumableChargesTariffCode;
	}
	public String getStrConsumableCharge() {
		return strConsumableCharge;
	}
	public void setStrConsumableCharge(String strConsumableCharge) {
		this.strConsumableCharge = strConsumableCharge;
	}
	public String getStrGroupIdForConsumableConcatenated() {
		return strGroupIdForConsumableConcatenated;
	}
	public void setStrGroupIdForConsumableConcatenated(
			String strGroupIdForConsumableConcatenated) {
		this.strGroupIdForConsumableConcatenated = strGroupIdForConsumableConcatenated;
	}
	public String getStrOfflineTotalPayAmountWithoutConsumable() {
		return strOfflineTotalPayAmountWithoutConsumable;
	}
	public void setStrOfflineTotalPayAmountWithoutConsumable(
			String strOfflineTotalPayAmountWithoutConsumable) {
		this.strOfflineTotalPayAmountWithoutConsumable = strOfflineTotalPayAmountWithoutConsumable;
	}
	public String getStrBillUsrFuncName() {
		return strBillUsrFuncName;
	}
	public void setStrBillUsrFuncName(String strBillUsrFuncName) {
		this.strBillUsrFuncName = strBillUsrFuncName;
	}
	public String getStrPatientMode() {
		return strPatientMode;
	}
	public void setStrPatientMode(String strPatientMode) {
		this.strPatientMode = strPatientMode;
	}
	public String getStrIsWithoutCrNoRequired() {
		return strIsWithoutCrNoRequired;
	}
	public void setStrIsWithoutCrNoRequired(String strIsWithoutCrNoRequired) {
		this.strIsWithoutCrNoRequired = strIsWithoutCrNoRequired;
	}
	public String getStrPreviousCrNoSearch() {
		return strPreviousCrNoSearch;
	}
	public void setStrPreviousCrNoSearch(String strPreviousCrNoSearch) {
		this.strPreviousCrNoSearch = strPreviousCrNoSearch;
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
	public String getStrIpAddress() {
		return strIpAddress;
	}
	public void setStrIpAddress(String strIpAddress) {
		this.strIpAddress = strIpAddress;
	}
	public String getStrUserLevel() {
		return strUserLevel;
	}
	public void setStrUserLevel(String strUserLevel) {
		this.strUserLevel = strUserLevel;
	}
	public String getStrConfirmationType() {
		return strConfirmationType;
	}
	public void setStrConfirmationType(String strConfirmationType) {
		this.strConfirmationType = strConfirmationType;
	}
	public String getStrIsOpdDiscount() {
		return strIsOpdDiscount;
	}
	public void setStrIsOpdDiscount(String strIsOpdDiscount) {
		this.strIsOpdDiscount = strIsOpdDiscount;
	}
	public String getStrIsPreviousCrNoReq() {
		return strIsPreviousCrNoReq;
	}
	public void setStrIsPreviousCrNoReq(String strIsPreviousCrNoReq) {
		this.strIsPreviousCrNoReq = strIsPreviousCrNoReq;
	}
	public String getStrIsRefPhysicanReq() {
		return strIsRefPhysicanReq;
	}
	public void setStrIsRefPhysicanReq(String strIsRefPhysicanReq) {
		this.strIsRefPhysicanReq = strIsRefPhysicanReq;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrPreviousCrNo() {
		return strPreviousCrNo;
	}
	public void setStrPreviousCrNo(String strPreviousCrNo) {
		this.strPreviousCrNo = strPreviousCrNo;
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
	public String getStrTempReceiptNo() {
		return strTempReceiptNo;
	}
	public void setStrTempReceiptNo(String strTempReceiptNo) {
		this.strTempReceiptNo = strTempReceiptNo;
	}
	public String getStrRefundReceiptNo() {
		return strRefundReceiptNo;
	}
	public void setStrRefundReceiptNo(String strRefundReceiptNo) {
		this.strRefundReceiptNo = strRefundReceiptNo;
	}
	public String getStrOffLineBillingService() {
		return strOffLineBillingService;
	}
	public void setStrOffLineBillingService(String strOffLineBillingService) {
		this.strOffLineBillingService = strOffLineBillingService;
	}
	public String getStrOfflineTotalRecAmount() {
		return strOfflineTotalRecAmount;
	}
	public void setStrOfflineTotalRecAmount(String strOfflineTotalRecAmount) {
		this.strOfflineTotalRecAmount = strOfflineTotalRecAmount;
	}
	public String getStrWithoutCrNoOnlineRequestNo() {
		return strWithoutCrNoOnlineRequestNo;
	}
	public void setStrWithoutCrNoOnlineRequestNo(
			String strWithoutCrNoOnlineRequestNo) {
		this.strWithoutCrNoOnlineRequestNo = strWithoutCrNoOnlineRequestNo;
	}
	public String getStrOffLineHospitalService() {
		return strOffLineHospitalService;
	}
	public void setStrOffLineHospitalService(String strOffLineHospitalService) {
		this.strOffLineHospitalService = strOffLineHospitalService;
	}
	public String getStrOffLineTreatmentCategory() {
		return strOffLineTreatmentCategory;
	}
	public void setStrOffLineTreatmentCategory(String strOffLineTreatmentCategory) {
		this.strOffLineTreatmentCategory = strOffLineTreatmentCategory;
	}
	public String getStrGuarantorName() {
		return strGuarantorName;
	}
	public void setStrGuarantorName(String strGuarantorName) {
		this.strGuarantorName = strGuarantorName;
	}
	public String getStrGuartorAddress() {
		return strGuartorAddress;
	}
	public void setStrGuartorAddress(String strGuartorAddress) {
		this.strGuartorAddress = strGuartorAddress;
	}
	public String getStrGuarantorContactNo() {
		return strGuarantorContactNo;
	}
	public void setStrGuarantorContactNo(String strGuarantorContactNo) {
		this.strGuarantorContactNo = strGuarantorContactNo;
	}
	public String getStrOffLineRaisingDepartment() {
		return strOffLineRaisingDepartment;
	}
	public void setStrOffLineRaisingDepartment(String strOffLineRaisingDepartment) {
		this.strOffLineRaisingDepartment = strOffLineRaisingDepartment;
	}
	public String getStrService() {
		return strService;
	}
	public void setStrService(String strService) {
		this.strService = strService;
	}
	public String getStrGlobalRequestNo() {
		return strGlobalRequestNo;
	}
	public void setStrGlobalRequestNo(String strGlobalRequestNo) {
		this.strGlobalRequestNo = strGlobalRequestNo;
	}
	public String getStrRequestDate() {
		return strRequestDate;
	}
	public void setStrRequestDate(String strRequestDate) {
		this.strRequestDate = strRequestDate;
	}
	public String getStrAge() {
		return strAge;
	}
	public void setStrAge(String strAge) {
		this.strAge = strAge;
	}
	public String getStrAgeUnit() {
		return strAgeUnit;
	}
	public void setStrAgeUnit(String strAgeUnit) {
		this.strAgeUnit = strAgeUnit;
	}
	public String getStrReferringPhysician() {
		return strReferringPhysician;
	}
	public void setStrReferringPhysician(String strReferringPhysician) {
		this.strReferringPhysician = strReferringPhysician;
	}
	public String getStrGender() {
		return strGender;
	}
	public void setStrGender(String strGender) {
		this.strGender = strGender;
	}
	public String getStrReferringPhysicianContactNo() {
		return strReferringPhysicianContactNo;
	}
	public void setStrReferringPhysicianContactNo(
			String strReferringPhysicianContactNo) {
		this.strReferringPhysicianContactNo = strReferringPhysicianContactNo;
	}
	 
	public String[] getStrTariffDiscountConfigDetails() {
		return strTariffDiscountConfigDetails;
	}
	public void setStrTariffDiscountConfigDetails(
			String[] strTariffDiscountConfigDetails) {
		this.strTariffDiscountConfigDetails = strTariffDiscountConfigDetails;
	}
	public String getStrClientNameContents() {
		return strClientNameContents;
	}
	public void setStrClientNameContents(String strClientNameContents) {
		this.strClientNameContents = strClientNameContents;
	}
	public String getStrPaymentModeContents() {
		return strPaymentModeContents;
	}
	public void setStrPaymentModeContents(String strPaymentModeContents) {
		this.strPaymentModeContents = strPaymentModeContents;
	}
	public String getStrBillingServiceDetails() {
		return strBillingServiceDetails;
	}
	public void setStrBillingServiceDetails(String strBillingServiceDetails) {
		this.strBillingServiceDetails = strBillingServiceDetails;
	}
	public String getStrRaisingDepartmentDetails() {
		return strRaisingDepartmentDetails;
	}
	public void setStrRaisingDepartmentDetails(String strRaisingDepartmentDetails) {
		this.strRaisingDepartmentDetails = strRaisingDepartmentDetails;
	}
	public String getStrOfflineGroupDetails() {
		return strOfflineGroupDetails;
	}
	public void setStrOfflineGroupDetails(String strOfflineGroupDetails) {
		this.strOfflineGroupDetails = strOfflineGroupDetails;
	}
	public String getStrOfflineTariffDetails() {
		return strOfflineTariffDetails;
	}
	public void setStrOfflineTariffDetails(String strOfflineTariffDetails) {
		this.strOfflineTariffDetails = strOfflineTariffDetails;
	}
	public String getStrOfflineDiscountApprovedByDetails() {
		return strOfflineDiscountApprovedByDetails;
	}
	public void setStrOfflineDiscountApprovedByDetails(
			String strOfflineDiscountApprovedByDetails) {
		this.strOfflineDiscountApprovedByDetails = strOfflineDiscountApprovedByDetails;
	}
	public String getStrOfflineDiscountRemarksDetails() {
		return strOfflineDiscountRemarksDetails;
	}
	public void setStrOfflineDiscountRemarksDetails(
			String strOfflineDiscountRemarksDetails) {
		this.strOfflineDiscountRemarksDetails = strOfflineDiscountRemarksDetails;
	}
	public String getStrRequestNoValues() {
		return strRequestNoValues;
	}
	public void setStrRequestNoValues(String strRequestNoValues) {
		this.strRequestNoValues = strRequestNoValues;
	}
	public String getStrGenderList() {
		return strGenderList;
	}
	public void setStrGenderList(String strGenderList) {
		this.strGenderList = strGenderList;
	}
	public String[] getStrOfflineTariffDetailsHidden() {
		return strOfflineTariffDetailsHidden;
	}
	public void setStrOfflineTariffDetailsHidden(
			String[] strOfflineTariffDetailsHidden) {
		this.strOfflineTariffDetailsHidden = strOfflineTariffDetailsHidden;
	}
	public String[] getStrOfflineTariffName() {
		return strOfflineTariffName;
	}
	public void setStrOfflineTariffName(String[] strOfflineTariffName) {
		this.strOfflineTariffName = strOfflineTariffName;
	}
	public String[] getStrOfflineTariffQty() {
		return strOfflineTariffQty;
	}
	public void setStrOfflineTariffQty(String[] strOfflineTariffQty) {
		this.strOfflineTariffQty = strOfflineTariffQty;
	}
	public String[] getStrOfflineTariffDiscount() {
		return strOfflineTariffDiscount;
	}
	public void setStrOfflineTariffDiscount(String[] strOfflineTariffDiscount) {
		this.strOfflineTariffDiscount = strOfflineTariffDiscount;
	}
	public String[] getStrOfflineTariffServiceTax() {
		return strOfflineTariffServiceTax;
	}
	public void setStrOfflineTariffServiceTax(String[] strOfflineTariffServiceTax) {
		this.strOfflineTariffServiceTax = strOfflineTariffServiceTax;
	}
	public String[] getStrOfflineTariffNetAmount() {
		return strOfflineTariffNetAmount;
	}
	public void setStrOfflineTariffNetAmount(String[] strOfflineTariffNetAmount) {
		this.strOfflineTariffNetAmount = strOfflineTariffNetAmount;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrTempBillNo() {
		return strTempBillNo;
	}
	public void setStrTempBillNo(String strTempBillNo) {
		this.strTempBillNo = strTempBillNo;
	}
	public String getStrRequestNo() {
		return strRequestNo;
	}
	public void setStrRequestNo(String strRequestNo) {
		this.strRequestNo = strRequestNo;
	}
	 
	public String[] getStrOfflineTariffDiscountConfigDetails() {
		return strOfflineTariffDiscountConfigDetails;
	}
	public void setStrOfflineTariffDiscountConfigDetails(
			String[] strOfflineTariffDiscountConfigDetails) {
		this.strOfflineTariffDiscountConfigDetails = strOfflineTariffDiscountConfigDetails;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrCounterMode() {
		return strCounterMode;
	}
	public void setStrCounterMode(String strCounterMode) {
		this.strCounterMode = strCounterMode;
	}
	public String[] getStrOfflineTariffUnit() {
		return strOfflineTariffUnit;
	}
	public void setStrOfflineTariffUnit(String[] strOfflineTariffUnit) {
		this.strOfflineTariffUnit = strOfflineTariffUnit;
	}
	public String[] getStrOfflineTariffServiceTaxAmtVal() {
		return strOfflineTariffServiceTaxAmtVal;
	}
	public void setStrOfflineTariffServiceTaxAmtVal(
			String[] strOfflineTariffServiceTaxAmtVal) {
		this.strOfflineTariffServiceTaxAmtVal = strOfflineTariffServiceTaxAmtVal;
	}
	public String[] getStrOfflineTariffDiscountAmtVal() {
		return strOfflineTariffDiscountAmtVal;
	}
	public void setStrOfflineTariffDiscountAmtVal(
			String[] strOfflineTariffDiscountAmtVal) {
		this.strOfflineTariffDiscountAmtVal = strOfflineTariffDiscountAmtVal;
	}
	public String[] getStrOfflineTotalActualTariffAmtVal() {
		return strOfflineTotalActualTariffAmtVal;
	}
	public void setStrOfflineTotalActualTariffAmtVal(
			String[] strOfflineTotalActualTariffAmtVal) {
		this.strOfflineTotalActualTariffAmtVal = strOfflineTotalActualTariffAmtVal;
	}
	public String[] getStrOfflinePaymentMode() {
		return strOfflinePaymentMode;
	}
	public void setStrOfflinePaymentMode(String[] strOfflinePaymentMode) {
		this.strOfflinePaymentMode = strOfflinePaymentMode;
	}
	public String[] getStrOfflinePaymentDtls() {
		return strOfflinePaymentDtls;
	}
	public void setStrOfflinePaymentDtls(String[] strOfflinePaymentDtls) {
		this.strOfflinePaymentDtls = strOfflinePaymentDtls;
	}
	public String[] getStrOfflineAmount() {
		return strOfflineAmount;
	}
	public void setStrOfflineAmount(String[] strOfflineAmount) {
		this.strOfflineAmount = strOfflineAmount;
	}
	public String[] getStrBillTariffVal() {
		return strBillTariffVal;
	}
	public void setStrBillTariffVal(String[] strBillTariffVal) {
		this.strBillTariffVal = strBillTariffVal;
	}
	public String[] getStrBillTariffPenaltyType() {
		return strBillTariffPenaltyType;
	}
	public void setStrBillTariffPenaltyType(String[] strBillTariffPenaltyType) {
		this.strBillTariffPenaltyType = strBillTariffPenaltyType;
	}
	public String[] getStrBillTariffPenalty() {
		return strBillTariffPenalty;
	}
	public void setStrBillTariffPenalty(String[] strBillTariffPenalty) {
		this.strBillTariffPenalty = strBillTariffPenalty;
	}
	public String[] getStrBillTariffRefund() {
		return strBillTariffRefund;
	}
	public void setStrBillTariffRefund(String[] strBillTariffRefund) {
		this.strBillTariffRefund = strBillTariffRefund;
	}
	public String[] getStrBillTariffUnit() {
		return strBillTariffUnit;
	}
	public void setStrBillTariffUnit(String[] strBillTariffUnit) {
		this.strBillTariffUnit = strBillTariffUnit;
	}
	public String[] getStrOfflineRefundServiceTaxAmount() {
		return strOfflineRefundServiceTaxAmount;
	}
	public void setStrOfflineRefundServiceTaxAmount(
			String[] strOfflineRefundServiceTaxAmount) {
		this.strOfflineRefundServiceTaxAmount = strOfflineRefundServiceTaxAmount;
	}
	public String[] getStrOfflineRefundDiscountAmount() {
		return strOfflineRefundDiscountAmount;
	}
	public void setStrOfflineRefundDiscountAmount(
			String[] strOfflineRefundDiscountAmount) {
		this.strOfflineRefundDiscountAmount = strOfflineRefundDiscountAmount;
	}
	public String[] getStrOfflineRefundPenaltyAmount() {
		return strOfflineRefundPenaltyAmount;
	}
	public void setStrOfflineRefundPenaltyAmount(
			String[] strOfflineRefundPenaltyAmount) {
		this.strOfflineRefundPenaltyAmount = strOfflineRefundPenaltyAmount;
	}
	public String[] getStrOfflineRefundActualTariffAmount() {
		return strOfflineRefundActualTariffAmount;
	}
	public void setStrOfflineRefundActualTariffAmount(
			String[] strOfflineRefundActualTariffAmount) {
		this.strOfflineRefundActualTariffAmount = strOfflineRefundActualTariffAmount;
	}
	public String[] getStrBillTariffRefundAmount() {
		return strBillTariffRefundAmount;
	}
	public void setStrBillTariffRefundAmount(String[] strBillTariffRefundAmount) {
		this.strBillTariffRefundAmount = strBillTariffRefundAmount;
	}
	public String[] getStrTariffDetailsId() {
		return strTariffDetailsId;
	}
	public void setStrTariffDetailsId(String[] strTariffDetailsId) {
		this.strTariffDetailsId = strTariffDetailsId;
	}
	public String[] getStrTariffBilledQty() {
		return strTariffBilledQty;
	}
	public void setStrTariffBilledQty(String[] strTariffBilledQty) {
		this.strTariffBilledQty = strTariffBilledQty;
	}
	public String[] getStrTariffServiceTaxAmt() {
		return strTariffServiceTaxAmt;
	}
	public void setStrTariffServiceTaxAmt(String[] strTariffServiceTaxAmt) {
		this.strTariffServiceTaxAmt = strTariffServiceTaxAmt;
	}
	public String[] getStrTariffDiscountAmt() {
		return strTariffDiscountAmt;
	}
	public void setStrTariffDiscountAmt(String[] strTariffDiscountAmt) {
		this.strTariffDiscountAmt = strTariffDiscountAmt;
	}
	public String[] getStrTariffNetAmt() {
		return strTariffNetAmt;
	}
	public void setStrTariffNetAmt(String[] strTariffNetAmt) {
		this.strTariffNetAmt = strTariffNetAmt;
	}
	public String getStrTotalTariffServiceTaxAmount() {
		return strTotalTariffServiceTaxAmount;
	}
	public void setStrTotalTariffServiceTaxAmount(
			String strTotalTariffServiceTaxAmount) {
		this.strTotalTariffServiceTaxAmount = strTotalTariffServiceTaxAmount;
	}
	public String getStrTotalTariffDiscountAmount() {
		return strTotalTariffDiscountAmount;
	}
	public void setStrTotalTariffDiscountAmount(String strTotalTariffDiscountAmount) {
		this.strTotalTariffDiscountAmount = strTotalTariffDiscountAmount;
	}
	public String getStrTotalTariffActualAmount() {
		return strTotalTariffActualAmount;
	}
	public void setStrTotalTariffActualAmount(String strTotalTariffActualAmount) {
		this.strTotalTariffActualAmount = strTotalTariffActualAmount;
	}
	public String getStrTotalTariffPenaltyAmount() {
		return strTotalTariffPenaltyAmount;
	}
	public void setStrTotalTariffPenaltyAmount(String strTotalTariffPenaltyAmount) {
		this.strTotalTariffPenaltyAmount = strTotalTariffPenaltyAmount;
	}
	public String getStrOfflineRefundTotalServiceTaxAmount() {
		return strOfflineRefundTotalServiceTaxAmount;
	}
	public void setStrOfflineRefundTotalServiceTaxAmount(
			String strOfflineRefundTotalServiceTaxAmount) {
		this.strOfflineRefundTotalServiceTaxAmount = strOfflineRefundTotalServiceTaxAmount;
	}
	public String getStrOfflineRefundTotalDiscountAmount() {
		return strOfflineRefundTotalDiscountAmount;
	}
	public void setStrOfflineRefundTotalDiscountAmount(
			String strOfflineRefundTotalDiscountAmount) {
		this.strOfflineRefundTotalDiscountAmount = strOfflineRefundTotalDiscountAmount;
	}
	public String getStrOfflineRefundTotalPenaltyAmount() {
		return strOfflineRefundTotalPenaltyAmount;
	}
	public void setStrOfflineRefundTotalPenaltyAmount(
			String strOfflineRefundTotalPenaltyAmount) {
		this.strOfflineRefundTotalPenaltyAmount = strOfflineRefundTotalPenaltyAmount;
	}
	public String getStrOfflineRefundTotalActualTariffAmount() {
		return strOfflineRefundTotalActualTariffAmount;
	}
	public void setStrOfflineRefundTotalActualTariffAmount(
			String strOfflineRefundTotalActualTariffAmount) {
		this.strOfflineRefundTotalActualTariffAmount = strOfflineRefundTotalActualTariffAmount;
	}
	public String getStrOfflineRefundBillDetails() {
		return strOfflineRefundBillDetails;
	}
	public void setStrOfflineRefundBillDetails(String strOfflineRefundBillDetails) {
		this.strOfflineRefundBillDetails = strOfflineRefundBillDetails;
	}
	public String getStrOffLineRefundBy() {
		return strOffLineRefundBy;
	}
	public void setStrOffLineRefundBy(String strOffLineRefundBy) {
		this.strOffLineRefundBy = strOffLineRefundBy;
	}
	public String getStrOffLineRefundReason() {
		return strOffLineRefundReason;
	}
	public void setStrOffLineRefundReason(String strOffLineRefundReason) {
		this.strOffLineRefundReason = strOffLineRefundReason;
	}
	public String getStrOffLineRefundReasonText() {
		return strOffLineRefundReasonText;
	}
	public void setStrOffLineRefundReasonText(String strOffLineRefundReasonText) {
		this.strOffLineRefundReasonText = strOffLineRefundReasonText;
	}
	public String getStrOfflineRefundDate() {
		return strOfflineRefundDate;
	}
	public void setStrOfflineRefundDate(String strOfflineRefundDate) {
		this.strOfflineRefundDate = strOfflineRefundDate;
	}
	public String getStrOffLineWard() {
		return strOffLineWard;
	}
	public void setStrOffLineWard(String strOffLineWard) {
		this.strOffLineWard = strOffLineWard;
	}
	public String getStrTariffCode() {
		return strTariffCode;
	}
	public void setStrTariffCode(String strTariffCode) {
		this.strTariffCode = strTariffCode;
	}
	public String getStrOffLinePackageIndex() {
		return strOffLinePackageIndex;
	}
	public void setStrOffLinePackageIndex(String strOffLinePackageIndex) {
		this.strOffLinePackageIndex = strOffLinePackageIndex;
	}
	public String getStrOffLineTariffUnitTempId() {
		return strOffLineTariffUnitTempId;
	}
	public void setStrOffLineTariffUnitTempId(String strOffLineTariffUnitTempId) {
		this.strOffLineTariffUnitTempId = strOffLineTariffUnitTempId;
	}
	public String getStrOffLineTariffDetailsHiddenValue() {
		return strOffLineTariffDetailsHiddenValue;
	}
	public void setStrOffLineTariffDetailsHiddenValue(
			String strOffLineTariffDetailsHiddenValue) {
		this.strOffLineTariffDetailsHiddenValue = strOffLineTariffDetailsHiddenValue;
	}
	public String getStrRefundTariffHiddenValue() {
		return strRefundTariffHiddenValue;
	}
	public void setStrRefundTariffHiddenValue(String strRefundTariffHiddenValue) {
		this.strRefundTariffHiddenValue = strRefundTariffHiddenValue;
	}
	public String getStrOffLineBillNumber() {
		return strOffLineBillNumber;
	}
	public void setStrOffLineBillNumber(String strOffLineBillNumber) {
		this.strOffLineBillNumber = strOffLineBillNumber;
	}
	public String getStrGuarantorHiddenVal() {
		return strGuarantorHiddenVal;
	}
	public void setStrGuarantorHiddenVal(String strGuarantorHiddenVal) {
		this.strGuarantorHiddenVal = strGuarantorHiddenVal;
	}
	public String getStrBillSearchString() {
		return strBillSearchString;
	}
	public void setStrBillSearchString(String strBillSearchString) {
		this.strBillSearchString = strBillSearchString;
	}
	public String getStrBillFromRow() {
		return strBillFromRow;
	}
	public void setStrBillFromRow(String strBillFromRow) {
		this.strBillFromRow = strBillFromRow;
	}
	public String getStrBillToRow() {
		return strBillToRow;
	}
	public void setStrBillToRow(String strBillToRow) {
		this.strBillToRow = strBillToRow;
	}
	public String getStrBillRowPerPage() {
		return strBillRowPerPage;
	}
	public void setStrBillRowPerPage(String strBillRowPerPage) {
		this.strBillRowPerPage = strBillRowPerPage;
	}
	public String getStrBillCtBlockSet() {
		return strBillCtBlockSet;
	}
	public void setStrBillCtBlockSet(String strBillCtBlockSet) {
		this.strBillCtBlockSet = strBillCtBlockSet;
	}
	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}
	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}
	public String getStrGroupId() {
		return strGroupId;
	}
	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}
	public String getStrQtyUnitId() {
		return strQtyUnitId;
	}
	public void setStrQtyUnitId(String strQtyUnitId) {
		this.strQtyUnitId = strQtyUnitId;
	}
	public String getStrOffLineRemarks() {
		return strOffLineRemarks;
	}
	public void setStrOffLineRemarks(String strOffLineRemarks) {
		this.strOffLineRemarks = strOffLineRemarks;
	}
	public String getStrOffLineRequestType() {
		return strOffLineRequestType;
	}
	public void setStrOffLineRequestType(String strOffLineRequestType) {
		this.strOffLineRequestType = strOffLineRequestType;
	}
	public String getStrOffLineAccountNo() {
		return strOffLineAccountNo;
	}
	public void setStrOffLineAccountNo(String strOffLineAccountNo) {
		this.strOffLineAccountNo = strOffLineAccountNo;
	}
	public String getStrOfflineTotalPayAmount() {
		return strOfflineTotalPayAmount;
	}
	public void setStrOfflineTotalPayAmount(String strOfflineTotalPayAmount) {
		this.strOfflineTotalPayAmount = strOfflineTotalPayAmount;
	}
	public String getStrOfflineRefundAmount() {
		return strOfflineRefundAmount;
	}
	public void setStrOfflineRefundAmount(String strOfflineRefundAmount) {
		this.strOfflineRefundAmount = strOfflineRefundAmount;
	}
	public String getStrOfflineMaxClientBenefitAmount() {
		return strOfflineMaxClientBenefitAmount;
	}
	public void setStrOfflineMaxClientBenefitAmount(
			String strOfflineMaxClientBenefitAmount) {
		this.strOfflineMaxClientBenefitAmount = strOfflineMaxClientBenefitAmount;
	}
	public String getStrOfflinePatNetPayAmount() {
		return strOfflinePatNetPayAmount;
	}
	public void setStrOfflinePatNetPayAmount(String strOfflinePatNetPayAmount) {
		this.strOfflinePatNetPayAmount = strOfflinePatNetPayAmount;
	}
	public String getStrOfflineTotalActualTariffAmount() {
		return strOfflineTotalActualTariffAmount;
	}
	public void setStrOfflineTotalActualTariffAmount(
			String strOfflineTotalActualTariffAmount) {
		this.strOfflineTotalActualTariffAmount = strOfflineTotalActualTariffAmount;
	}
	public String getStrOfflineTotalServiceTaxAmount() {
		return strOfflineTotalServiceTaxAmount;
	}
	public void setStrOfflineTotalServiceTaxAmount(
			String strOfflineTotalServiceTaxAmount) {
		this.strOfflineTotalServiceTaxAmount = strOfflineTotalServiceTaxAmount;
	}
	public String getStrOfflineTotalDiscountAmount() {
		return strOfflineTotalDiscountAmount;
	}
	public void setStrOfflineTotalDiscountAmount(
			String strOfflineTotalDiscountAmount) {
		this.strOfflineTotalDiscountAmount = strOfflineTotalDiscountAmount;
	}
	public String getStrOffLineGroup() {
		return strOffLineGroup;
	}
	public void setStrOffLineGroup(String strOffLineGroup) {
		this.strOffLineGroup = strOffLineGroup;
	}
	public String getStrOffLinePakageGroup() {
		return strOffLinePakageGroup;
	}
	public void setStrOffLinePakageGroup(String strOffLinePakageGroup) {
		this.strOffLinePakageGroup = strOffLinePakageGroup;
	}
	public String getStrOfflineTariffDiscountApprovedBy() {
		return strOfflineTariffDiscountApprovedBy;
	}
	public void setStrOfflineTariffDiscountApprovedBy(
			String strOfflineTariffDiscountApprovedBy) {
		this.strOfflineTariffDiscountApprovedBy = strOfflineTariffDiscountApprovedBy;
	}
	public String getStrOfflineTariffDiscountRemarks() {
		return strOfflineTariffDiscountRemarks;
	}
	public void setStrOfflineTariffDiscountRemarks(
			String strOfflineTariffDiscountRemarks) {
		this.strOfflineTariffDiscountRemarks = strOfflineTariffDiscountRemarks;
	}
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrCounterId() {
		return strCounterId;
	}
	public void setStrCounterId(String strCounterId) {
		this.strCounterId = strCounterId;
	}
	public String getStrPreviousCrNoDtlFlag() {
		return strPreviousCrNoDtlFlag;
	}
	public void setStrPreviousCrNoDtlFlag(String strPreviousCrNoDtlFlag) {
		this.strPreviousCrNoDtlFlag = strPreviousCrNoDtlFlag;
	}
	public String getStrOffLineRefundPenalty() {
		return strOffLineRefundPenalty;
	}
	public void setStrOffLineRefundPenalty(String strOffLineRefundPenalty) {
		this.strOffLineRefundPenalty = strOffLineRefundPenalty;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrOffLineIsPackageGroup() {
		return strOffLineIsPackageGroup;
	}
	public void setStrOffLineIsPackageGroup(String strOffLineIsPackageGroup) {
		this.strOffLineIsPackageGroup = strOffLineIsPackageGroup;
	}
	public String getStrOffLineRefundAdvanceAccountNo() {
		return strOffLineRefundAdvanceAccountNo;
	}
	public void setStrOffLineRefundAdvanceAccountNo(
			String strOffLineRefundAdvanceAccountNo) {
		this.strOffLineRefundAdvanceAccountNo = strOffLineRefundAdvanceAccountNo;
	}
	public String getStrPreviousCrNoPatientDtls() {
		return strPreviousCrNoPatientDtls;
	}
	public void setStrPreviousCrNoPatientDtls(String strPreviousCrNoPatientDtls) {
		this.strPreviousCrNoPatientDtls = strPreviousCrNoPatientDtls;
	}
	public String getStrIsOnline() {
		return strIsOnline;
	}
	public void setStrIsOnline(String strIsOnline) {
		this.strIsOnline = strIsOnline;
	}
	public String getStrPrintMessageLimit() {
		return strPrintMessageLimit;
	}
	public void setStrPrintMessageLimit(String strPrintMessageLimit) {
		this.strPrintMessageLimit = strPrintMessageLimit;
	}
	public String getStrPatTypeBoth() {
		return strPatTypeBoth;
	}
	public void setStrPatTypeBoth(String strPatTypeBoth) {
		this.strPatTypeBoth = strPatTypeBoth;
	}
	public String getStrPatientType() {
		return strPatientType;
	}
	public void setStrPatientType(String strPatientType) {
		this.strPatientType = strPatientType;
	}
	public String getStrIntExtPat() {
		return strIntExtPat;
	}
	public void setStrIntExtPat(String strIntExtPat) {
		this.strIntExtPat = strIntExtPat;
	}
	public String getStrWardTypeId() {
		return strWardTypeId;
	}
	public void setStrWardTypeId(String strWardTypeId) {
		this.strWardTypeId = strWardTypeId;
	}
	public String getStrGeneralChargeTypeId() {
		return strGeneralChargeTypeId;
	}
	public void setStrGeneralChargeTypeId(String strGeneralChargeTypeId) {
		this.strGeneralChargeTypeId = strGeneralChargeTypeId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getIsOpenPopUp() {
		return isOpenPopUp;
	}
	public void setIsOpenPopUp(String isOpenPopUp) {
		this.isOpenPopUp = isOpenPopUp;
	}
	public LinkedHashMap<String, String> getLhm() 
	{
		lhm.put("Cash Collection Request Based", "ONLINE");
		lhm.put("Cash Collection Direct(Without Request)", "OFFLINE");
		//lhm.put("Cash Collection Without CR", "WITHOUTCRNO");
		//lhm.put("Cash Collection Advanced", "ADVANCED");
		//lhm.put("Cash Collection LF Account", "LF");
		lhm.put("Patient Wallet", "PATWALLET");
		return lhm;
	}
	public void setLhm(java.util.LinkedHashMap<String, String> lhm) 
	{
		this.lhm = lhm;
	}
	 
 
	
}
