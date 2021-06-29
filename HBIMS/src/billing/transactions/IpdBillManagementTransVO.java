package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class IpdBillManagementTransVO implements TransferObject {
	private static final long serialVersionUID = 02L;

	
	private int nCompChargeCount = 0;
	
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqDiscountWs = null;
	private WebRowSet strPrimaryKeyWs = null;
	private WebRowSet strDtlWs = null;
	private WebRowSet strPkgBrkUpWs = null;

	private WebRowSet strParticularDtlsListWs = null;
	private WebRowSet strParticularDtlsWs = null;

	private String ChkgValue = "0";
	private String strWardCode = "";
	private String strHospitalCode = "";
	private String strParticulaDtl = "";
	private String strUnitId = "";
	private int nSize = 0;
	private int nChkSize = 0;
	private String strDefaultQtyType = null;
	private String strOffLineDisApprovedBy = null;
	private String strUnProcHiddVal = null;
	private String totalRecAmtDivId1 = "";
	private String modeVal = "";

	private String strTotalExpenseAmt = "0";
	private String strTotalDiscAmt = "0";
	private String strTotalReceAmtByPatient = "";
	private String strTotalBenifitFrmClt = "";
	private String strNetPaymentAmt = "0";
	private String strPukNo = "";
	private String strDeptCode = "";
	private String strIpdThirdPartyBilling = "";
	private String strPatientDetailsView = "";

	private String strUserLevel = "";
	private String strMaxBenifitAmt = "0";
	private String strReceivedAmt = "";
	private String strTariffDiscountAmtConfgDtlBillApproval = "";
	private String strTotalServiceTax = "0";
	private String strNetPaybleAmt = "0";
	private String strIpdRoundOff = "";
	private String strServiceTax = "0";

	private String strRate = "";

	private String strchkvalue = "";
	
	private String strNetServiceTaxAmt = "0";
	private String strNetTariffAmt = "0";
	private String strNetActualTariffAmt = "0";

	private String strPkgBreakUp = "";
	private String strMsgType = "0";
	private String strWarning = "";
	private String strErrMsg = "";
	private String strMsg = "";
	private String strMsgString = null;
	private String strRetValue = null;
	private String strHidden = null;
	private String[] strDiscAmt = { "0" };

	private String strActualTariffNetAmt[] = null;

	private String[] strUnProcessServiceTaxAmt = null;
	private String[] strUnProcessServiceTax = null;
	private String[] strUnProcRefQty = { "0" };
	private String strChargeTypeID = "";
	private String strPatientCatCode = "";
	private String strPtCatCode = "";
	private String strCtDate = null;
	private String strEffectiveFrmDate = "";
	private String strEffectiveToDate = "";
	private String strGroupId = "";
	private String strIsPackaged = "";
	private String strDefaultUnit = "";

	private String strDeptId = "";
	private String strPartPaymentAmt = "";
	private String strChk = "";
	private String strCrNo = "";
	private String strCrNo1 = "";
	private String strPresentAcctStatus = "";
	private String strNewAcctStatus = "";
	private String strApprovalBy = "";
	private String strRemarks = "";
	private String strPrimaryCategory = "";
	private String strTreatmentCategory = "";
	private String strDeptName = "";
	private String strWardName = "";
	private String strAccountNo = "";
	private String strAddmissionNo = "";
	private String strAdmissionDate = "";
	private String strDeptUnitId = "";
	private String strEpisodeNo = "";
	private String strRemarksCombo2 = "";
	private String strApprovedByCombo2 = "";
	private String strSeatId = "";
	private String strApprovedDate = "";
	private String strApprovalId = "";
	private String strReqNo = "";
	private String strErrPrimKeyLog = "";
	private String strPAcctStatus = "";
	private String strIpdChgTypeId = "";
	private String grpid= "";
	
	private String strSearchLetter = "";
	private String finalBillFlag="";
	private String strTotalDiscountApprovalId = "0";
	private String strbcflag="1";
	private String serviceFlag="";
	private String[] sNo = { "0" };
	private WebRowSet strPatAdmWs = null;
	
	
	
	public String getServiceFlag() {
		return serviceFlag;
	}
	public void setServiceFlag(String serviceFlag) {
		this.serviceFlag = serviceFlag;
	}

	public String getGrpid() {
		return grpid;
	}

	public void setGrpid(String grpid) {
		this.grpid = grpid;
	}

	private String strTotalBillApprovalId = "0";

	private WebRowSet strApprovalDtlFrmProc = null;

	// Drop Down Multi Row Variable
	private String[] strOfflinePackageName = null;
	private String[] strOfflinePackageRateUnit = null;
	private String[] strOfflinePackageReqQty = { "0" };
	private String[] strOfflinePackageUnit = null;
	private String[] strOfflinePackageServiceTax = { "0" };
	private String[] strOfflinePackageDiscount = { "0" };
	private String[] strOfflinePackageNetAmount = { "0" };
	private String[] strMultiRowHiddenValue = { "0" };
	private String[] strTariffId = { "0" };
	private String[] strGrpId = { "0" };
	private String[] strTariffRate = { "0" };
	private String[] strBillQty = { "0" };
	private String[] strServTax = { "0" };
	private String[] strTariffActCost = { "0" };
	private String[] strIsPackg = { "0" };
	private String[] strTariffNetAmt = { "0" };
	private String[] strUnitBaseValue = { "0" };
	private String[] strRateBaseValue = { "0" };
	private String[] strTariffActualCost = { "0" };
	private String[] strUnitIDVal = { "0" };
	private String[] strServTaxAmt = { "0" };
	private String[] strIsRefundable = { "0" };
	private String[] strTempVal = { "0" };
	private String[] chkBox1 = { "0" };
	private String[] strServiceType = {"0" };
	
	private String[] strServiceId = { "0" };
	private String[] strGlblTariffId = { "0" };

	private String actual_amt = "";
	private String ser_tax_amt = "";
	private String trf_amt_tot = "";
	private String trf_amt = "";
	private String dis_amt = "";

	private String[] strReqDate = null;
	private String[] strUnProcTarriffName = null;
	private String[] strUnProcessQty = null;
	private String[] strRefundQty = null;
	private String[] strUnitCombo = null;
	private String[] strUnProcNetAmt = null;

	/*--------Un-Process Data------------*/

	private String[] strUnProReqNo = null;
	private String[] strUnProDeptCode = null;
	private String[] strUnProWardCode = null;
	private String[] strUnProTariffId = null;
	private String[] strUnProcBServiceId = null;
	private String[] strUnProcIpdChgTypeId = null;

	private String[] strUnProServiceId = null;
	private String[] strUnProHblDisUnit = null;
	private String[] strUnProHblDisType = null;
	private String[] strUnProGstrTariffId = null;
	private String[] strUnProcGstrReqNo = null;
	private String[] strUnProcHbAppId = null;

	private String[] strUnProTariffRate = null;
	private String[] strUnProRateUnitCode = null;
	private String[] strUnProQtyUnitId = null;
	private String[] strUnProHbReciptNo = null;
	private String[] strUnProcTariffActualRate = null;
	private String[] strUnProcHbIsPkg = null;

	private String[] strUnProRemainQty = null;
	private String[] strUnProUnitBaseVal = null;
	private String[] strUnProcSercTax = null;
	private String[] strUnProcUnitName = null;
	private String[] strUnProcServiceTaxAmt = null;
	private String[] strUnProcGroupId = null;

	private String strOffLineTariffDiscountUnit = "";
	private String strOffLineTariffDiscountType = "";
	private String strOffLineTariffDiscountBy = "";
	private String strOfflineDiscountApprovedByDetails = "";
	private String strOffLineTariffDiscountReason = "";
	private String strOffLineTariffDiscountReasonText = "";
	private String strOfflineDiscountRemarksDetails = "";
	private String strOffLineTariffDiscountDate = "";
	private String strChkRcdOpen = "";

	private WebRowSet OfflineGroupList = null;
	private WebRowSet OfflinePackageGroup = null;
	private WebRowSet OfflineTariffList = null;
	private String strOffLineWard = null;
	private WebRowSet OfflineDiscountRemarks = null;
	private WebRowSet OfflineTariffUnit = null;
	private WebRowSet OfflineDiscountApprovedBy = null;
	private String StrOffLineTariffUnitTempId = "";
	private String strOffLineTreatmentCategory = "";

	private String[] strCompChargeCheck = null;
	private String[] strSpecialChargeCheck = null;
	private String[] strOfflineTariffDetailsHidden = null;
	private String[] strOfflineTariffQty = null;
	private String[] strOfflineTariffUnit = null;
	private String[] strOfflineTariffDate = null;
	private String[] strOfflineTariffServiceTax = null;
	private String[] strOfflineTariffServiceTaxAmtVal = null;
	private String[] strOfflineTariffNetAmount = null;
	private String[] strOfflineActualTariffAmtVal = null;

	private WebRowSet strTariffNameCombo = null;
	private WebRowSet strHospitalServiceCombo = null;
	private WebRowSet strPatientCategoryCombo = null;
	private WebRowSet strWardnameCombo = null;
	private WebRowSet strDropDownWs = null;
	private WebRowSet strPartPaymentWs = null;
	private WebRowSet strRemarksCombo = null;
	private WebRowSet strGroupNameCombo = null;
	private WebRowSet strApprovedByCombo = null;
	private WebRowSet strAccStatusCombo = null;

	private String strClientName = "";
	private String strClientType = "";
	private String strApplNoDate = "";
	private String strAcctNoAcctTyp = "";
	private String strAcctOpngDate = "";
	private String strTreatmentCatg = "";
	private String strCltAppSancAmt = "";

	private String strCltApprBalanceAmt = "";
	private String strAcctDtlBalanceAmt = "";

	private String strSancAmt = "";
	private String strBalanceAmt = "";
	private String strClientPatNo = "";
	private String strPatAcctNo = "";
	private String strBillTypeCombo = "";
	private String[] strComboValue = { "0" };

	private String strNewTreatmentCategory = "";
	private String strSpecialWardType = "";
	private String strStartDate = "";
	private String strEndDate = "";
	private String strWardType = "";
	private String strTariffCode = "";
	private String strDepartment = "";
	
	private String strPreviousDates = "";
	private String[] strPreviousCheck = null;
	
	
	private WebRowSet strDepartmentList = null;
	private WebRowSet strPreviousDatesList = null;
	private WebRowSet strPreviousDtls = null;

	private WebRowSet offlineTreatmentCategoryList = null;
	private WebRowSet wardTypeList = null;
	private String isBillFinal="";
	private String isApproved="";
	private String strPoorFreeWelfareAmt = "0";
	private String strPoorFreeGrantAmt = "0";
	private WebRowSet strAccDtls = null;
	private String[] strPriority= null;
    private String[] strDiscount= null;
    private String[] strDiscountType= null;
    private String[] strDiscountAmt= null;
    private String strOfflineTariffName[] = null;
    private String[] date=null;
    private String[] qty=null;
    private String[] rate=null;
    private String[] amt=null;
    private String[] name=null;
    private WebRowSet allTrfListWS = null;
    private String strAllTrfHLP="";
    private String strNumRows="0";
    private String deleteFlag[] = null;
    private String strPrevReqNo[] = null;
    private WebRowSet prevBedTrf = null;
    private String strTransferDeptCode[]=null;
    private String strTransferWardCode[]=null;
    private String strTransferChargeType[]=null;
    private String strInDate[]=null;
    private String strOutDate[]=null;
    private String strMovNo[]=null;
    private String strSaveFlag[]=null;
    private String adtOnlineFlag[]=null;
    private String strLastStratDate="";
    private String strLastEndDate="";
    private String strPatientAdmndtl="";
    private WebRowSet admissionListWS=null;
    private String printFlag="";
    private String strDischargeDate="";
    private String strBillNo="";
    
    private String strLastDept="";
    private String strLastwardTypr="";
    private String strLastchargetype="";
    private String strAdvanceamt="";
    
    private String[] strUnitCode =null;
	private String[] strConsultant=null;
	private String[] strIsDoubleOc=null;
	private String[] strDblOccDate =null;
	private String[] strDisActAmt=null;
	private String strCurrindex="0";
	private String strAccStatus="";
	private String accType="1";
	private String strNewBabyBed[]=null;
	
	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String[] getStrDisActAmt() {
		return strDisActAmt;
	}

	public void setStrDisActAmt(String[] strDisActAmt) {
		this.strDisActAmt = strDisActAmt;
	}

	public String getStrAdvanceamt() {
		return strAdvanceamt;
	}

	public void setStrAdvanceamt(String strAdvanceamt) {
		this.strAdvanceamt = strAdvanceamt;
	}

	public String getStrLastDept() {
		return strLastDept;
	}

	public void setStrLastDept(String strLastDept) {
		this.strLastDept = strLastDept;
	}

	public String getStrLastwardTypr() {
		return strLastwardTypr;
	}

	public void setStrLastwardTypr(String strLastwardTypr) {
		this.strLastwardTypr = strLastwardTypr;
	}

	public String getStrLastchargetype() {
		return strLastchargetype;
	}

	public void setStrLastchargetype(String strLastchargetype) {
		this.strLastchargetype = strLastchargetype;
	}

	public String[] getDate() {
		return date;
	}

	public String getStrLastStratDate() {
		return strLastStratDate;
	}

	public void setStrLastStratDate(String strLastStratDate) {
		this.strLastStratDate = strLastStratDate;
	}

	public String getStrLastEndDate() {
		return strLastEndDate;
	}

	public void setStrLastEndDate(String strLastEndDate) {
		this.strLastEndDate = strLastEndDate;
	}

	public void setDate(String[] date) {
		this.date = date;
	}

	public String[] getQty() {
		return qty;
	}

	public void setQty(String[] qty) {
		this.qty = qty;
	}

	public String[] getRate() {
		return rate;
	}

	public void setRate(String[] rate) {
		this.rate = rate;
	}

	public String[] getAmt() {
		return amt;
	}

	public void setAmt(String[] amt) {
		this.amt = amt;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public WebRowSet getStrAccDtls() {
		return strAccDtls;
	}

	public void setStrAccDtls(WebRowSet strAccDtls) {
		this.strAccDtls = strAccDtls;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public String getIsBillFinal() {
		return isBillFinal;
	}

	public void setIsBillFinal(String isBillFinal) {
		this.isBillFinal = isBillFinal;
	}

	public WebRowSet getOfflineTreatmentCategoryList() {
		return offlineTreatmentCategoryList;
	}

	public void setOfflineTreatmentCategoryList(
			WebRowSet offlineTreatmentCategoryList) {
		this.offlineTreatmentCategoryList = offlineTreatmentCategoryList;
	}

	public String getStrCtDate() { // function for gettin SYSDATE
		HisUtil util = new HisUtil("IpdBillManagement",
				"IpdBillManagementTransVO");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("IpdBillManagement", "IpdBillManagementTransVO",
					"IpdBillManagementTransVO.getStrCtDate()-->"
							+ e.getMessage());
		}

		return strCtDate;
	}

	public String[] getStrComboValue() {
		return strComboValue;
	}

	public void setStrComboValue(String[] strComboValue) {
		this.strComboValue = strComboValue;
	}

	public void setStrAdmissionDate(String strAdmissionDate) {
		this.strAdmissionDate = strAdmissionDate;
	}

	public String getStrPresentAcctStatus() {
		return strPresentAcctStatus;
	}

	public void setStrPresentAcctStatus(String strPresentAcctStatus) {
		this.strPresentAcctStatus = strPresentAcctStatus;
	}

	public String getStrNewAcctStatus() {
		return strNewAcctStatus;
	}

	public void setStrNewAcctStatus(String strNewAcctStatus) {
		this.strNewAcctStatus = strNewAcctStatus;
	}

	public String getStrApprovalBy() {
		return strApprovalBy;
	}

	public void setStrApprovalBy(String strApprovalBy) {
		this.strApprovalBy = strApprovalBy;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public WebRowSet getStrPatientDetailsWs() {
		return strPatientDetailsWs;
	}

	public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
		this.strPatientDetailsWs = strPatientDetailsWs;
	}

	public WebRowSet getStrOnLineReqDiscountWs() {
		return strOnLineReqDiscountWs;
	}

	public void setStrOnLineReqDiscountWs(WebRowSet strOnLineReqDiscountWs) {
		this.strOnLineReqDiscountWs = strOnLineReqDiscountWs;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public String getStrCrNo1() {
		return strCrNo1;
	}

	public void setStrCrNo1(String strCrNo1) {
		this.strCrNo1 = strCrNo1;
	}

	public String getStrPrimaryCategory() {
		return strPrimaryCategory;
	}

	public void setStrPrimaryCategory(String strPrimaryCategory) {
		this.strPrimaryCategory = strPrimaryCategory;
	}

	public String getStrTreatmentCategory() {
		return strTreatmentCategory;
	}

	public void setStrTreatmentCategory(String strTreatmentCategory) {
		this.strTreatmentCategory = strTreatmentCategory;
	}

	public String getStrAccountNo() {
		return strAccountNo;
	}

	public void setStrAccountNo(String strAccountNo) {
		this.strAccountNo = strAccountNo;
	}

	public String getStrAddmissionNo() {
		return strAddmissionNo;
	}

	public void setStrAddmissionNo(String strAddmissionNo) {
		this.strAddmissionNo = strAddmissionNo;
	}

	public String getStrAdmissionDate() {
		return strAdmissionDate;
	}

	public WebRowSet getStrApprovedByCombo() {
		return strApprovedByCombo;
	}

	public void setStrApprovedByCombo(WebRowSet strApprovedByCombo) {
		this.strApprovedByCombo = strApprovedByCombo;
	}

	public WebRowSet getStrRemarksCombo() {
		return strRemarksCombo;
	}

	public void setStrRemarksCombo(WebRowSet strRemarksCombo) {
		this.strRemarksCombo = strRemarksCombo;
	}

	public String getStrDeptUnitId() {
		return strDeptUnitId;
	}

	public void setStrDeptUnitId(String strDeptUnitId) {
		this.strDeptUnitId = strDeptUnitId;
	}

	public String getStrDeptName() {
		return strDeptName;
	}

	public void setStrDeptName(String strDeptName) {
		this.strDeptName = strDeptName;
	}

	public String getStrWardName() {
		return strWardName;
	}

	public void setStrWardName(String strWardName) {
		this.strWardName = strWardName;
	}

	public WebRowSet getStrGroupNameCombo() {
		return strGroupNameCombo;
	}

	public void setStrGroupNameCombo(WebRowSet strGroupNameCombo) {
		this.strGroupNameCombo = strGroupNameCombo;
	}

	public String getStrDeptId() {
		return strDeptId;
	}

	public void setStrDeptId(String strDeptId) {
		this.strDeptId = strDeptId;
	}

	public String getStrGroupId() {
		return strGroupId;
	}

	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}

	public String getStrEffectiveFrmDate() {
		return strEffectiveFrmDate;
	}

	public void setStrEffectiveFrmDate(String strEffectiveFrmDate) {
		this.strEffectiveFrmDate = strEffectiveFrmDate;
	}

	public String getStrEffectiveToDate() {
		return strEffectiveToDate;
	}

	public void setStrEffectiveToDate(String strEffectiveToDate) {
		this.strEffectiveToDate = strEffectiveToDate;
	}

	public WebRowSet getStrDropDownWs() {
		return strDropDownWs;
	}

	public void setStrDropDownWs(WebRowSet strDropDownWs) {
		this.strDropDownWs = strDropDownWs;
	}

	public WebRowSet getStrTariffNameCombo() {
		return strTariffNameCombo;
	}

	public void setStrTariffNameCombo(WebRowSet strTariffNameCombo) {
		this.strTariffNameCombo = strTariffNameCombo;
	}

	public WebRowSet getStrHospitalServiceCombo() {
		return strHospitalServiceCombo;
	}

	public void setStrHospitalServiceCombo(WebRowSet strHospitalServiceCombo) {
		this.strHospitalServiceCombo = strHospitalServiceCombo;
	}

	public WebRowSet getStrPatientCategoryCombo() {
		return strPatientCategoryCombo;
	}

	public void setStrPatientCategoryCombo(WebRowSet strPatientCategoryCombo) {
		this.strPatientCategoryCombo = strPatientCategoryCombo;
	}

	public WebRowSet getStrWardnameCombo() {
		return strWardnameCombo;
	}

	public void setStrWardnameCombo(WebRowSet strWardnameCombo) {
		this.strWardnameCombo = strWardnameCombo;
	}

	public String getStrWarning() {
		return strWarning;
	}

	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrChargeTypeID() {
		return strChargeTypeID;
	}

	public void setStrChargeTypeID(String strChargeTypeID) {
		this.strChargeTypeID = strChargeTypeID;
	}

	public String getStrPatientCatCode() {
		return strPatientCatCode;
	}

	public void setStrPatientCatCode(String strPatientCatCode) {
		this.strPatientCatCode = strPatientCatCode;
	}

	public WebRowSet getStrDtlWs() {
		return strDtlWs;
	}

	public void setStrDtlWs(WebRowSet strDtlWs) {
		this.strDtlWs = strDtlWs;
	}

	public String getStrWardCode() {
		return strWardCode;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}

	public WebRowSet getStrPartPaymentWs() {
		return strPartPaymentWs;
	}

	public void setStrPartPaymentWs(WebRowSet strPartPaymentWs) {
		this.strPartPaymentWs = strPartPaymentWs;
	}

	public String getStrPtCatCode() {
		return strPtCatCode;
	}

	public void setStrPtCatCode(String strPtCatCode) {
		this.strPtCatCode = strPtCatCode;
	}

	public String getStrPartPaymentAmt() {
		return strPartPaymentAmt;
	}

	public void setStrPartPaymentAmt(String strPartPaymentAmt) {
		this.strPartPaymentAmt = strPartPaymentAmt;
	}

	public String getStrSancAmt() {
		return strSancAmt;
	}

	public void setStrSancAmt(String strSancAmt) {
		this.strSancAmt = strSancAmt;
	}

	public String getStrBalanceAmt() {
		return strBalanceAmt;
	}

	public void setStrBalanceAmt(String strBalanceAmt) {
		this.strBalanceAmt = strBalanceAmt;
	}

	public String getStrClientPatNo() {
		return strClientPatNo;
	}

	public void setStrClientPatNo(String strClientPatNo) {
		this.strClientPatNo = strClientPatNo;
	}

	public String getStrPatAcctNo() {
		return strPatAcctNo;
	}

	public void setStrPatAcctNo(String strPatAcctNo) {
		this.strPatAcctNo = strPatAcctNo;
	}

	public String getStrClientName() {
		return strClientName;
	}

	public void setStrClientName(String strClientName) {
		this.strClientName = strClientName;
	}

	public String getStrClientType() {
		return strClientType;
	}

	public void setStrClientType(String strClientType) {
		this.strClientType = strClientType;
	}

	public String getStrApplNoDate() {
		return strApplNoDate;
	}

	public void setStrApplNoDate(String strApplNoDate) {
		this.strApplNoDate = strApplNoDate;
	}

	public String getStrAcctNoAcctTyp() {
		return strAcctNoAcctTyp;
	}

	public void setStrAcctNoAcctTyp(String strAcctNoAcctTyp) {
		this.strAcctNoAcctTyp = strAcctNoAcctTyp;
	}

	public String getStrAcctOpngDate() {
		return strAcctOpngDate;
	}

	public void setStrAcctOpngDate(String strAcctOpngDate) {
		this.strAcctOpngDate = strAcctOpngDate;
	}

	public String getStrTreatmentCatg() {
		return strTreatmentCatg;
	}

	public void setStrTreatmentCatg(String strTreatmentCatg) {
		this.strTreatmentCatg = strTreatmentCatg;
	}

	public String getStrCltAppSancAmt() {
		return strCltAppSancAmt;
	}

	public void setStrCltAppSancAmt(String strCltAppSancAmt) {
		this.strCltAppSancAmt = strCltAppSancAmt;
	}

	public String getStrCltApprBalanceAmt() {
		return strCltApprBalanceAmt;
	}

	public void setStrCltApprBalanceAmt(String strCltApprBalanceAmt) {
		this.strCltApprBalanceAmt = strCltApprBalanceAmt;
	}

	public String getStrAcctDtlBalanceAmt() {
		return strAcctDtlBalanceAmt;
	}

	public void setStrAcctDtlBalanceAmt(String strAcctDtlBalanceAmt) {
		this.strAcctDtlBalanceAmt = strAcctDtlBalanceAmt;
	}

	public String getStrEpisodeNo() {
		return strEpisodeNo;
	}

	public void setStrEpisodeNo(String strEpisodeNo) {
		this.strEpisodeNo = strEpisodeNo;
	}

	public String getStrRemarksCombo2() {
		return strRemarksCombo2;
	}

	public void setStrRemarksCombo2(String strRemarksCombo2) {
		this.strRemarksCombo2 = strRemarksCombo2;
	}

	public String getStrApprovedByCombo2() {
		return strApprovedByCombo2;
	}

	public void setStrApprovedByCombo2(String strApprovedByCombo2) {
		this.strApprovedByCombo2 = strApprovedByCombo2;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public WebRowSet getStrApprovalDtlFrmProc() {
		return strApprovalDtlFrmProc;
	}

	public void setStrApprovalDtlFrmProc(WebRowSet strApprovalDtlFrmProc) {
		this.strApprovalDtlFrmProc = strApprovalDtlFrmProc;
	}

	public String getStrApprovalId() {
		return strApprovalId;
	}

	public void setStrApprovalId(String strApprovalId) {
		this.strApprovalId = strApprovalId;
	}

	public String getStrReqNo() {
		return strReqNo;
	}

	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}

	public WebRowSet getStrPrimaryKeyWs() {
		return strPrimaryKeyWs;
	}

	public void setStrPrimaryKeyWs(WebRowSet strPrimaryKeyWs) {
		this.strPrimaryKeyWs = strPrimaryKeyWs;
	}

	public String getStrApprovedDate() {
		return strApprovedDate;
	}

	public void setStrApprovedDate(String strApprovedDate) {
		this.strApprovedDate = strApprovedDate;
	}

	public String getStrErrPrimKeyLog() {
		return strErrPrimKeyLog;
	}

	public void setStrErrPrimKeyLog(String strErrPrimKeyLog) {
		this.strErrPrimKeyLog = strErrPrimKeyLog;
	}

	public String getStrPAcctStatus() {
		return strPAcctStatus;
	}

	public void setStrPAcctStatus(String strPAcctStatus) {
		this.strPAcctStatus = strPAcctStatus;
	}

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

	public String getStrIsPackaged() {
		return strIsPackaged;
	}

	public void setStrIsPackaged(String strIsPackaged) {
		this.strIsPackaged = strIsPackaged;
	}

	public WebRowSet getStrPkgBrkUpWs() {
		return strPkgBrkUpWs;
	}

	public void setStrPkgBrkUpWs(WebRowSet strPkgBrkUpWs) {
		this.strPkgBrkUpWs = strPkgBrkUpWs;
	}

	public String getStrPkgBreakUp() {
		return strPkgBreakUp;
	}

	public void setStrPkgBreakUp(String strPkgBreakUp) {
		this.strPkgBreakUp = strPkgBreakUp;
	}

	public String getStrRetValue() {
		return strRetValue;
	}

	public void setStrRetValue(String strRetValue) {
		this.strRetValue = strRetValue;
	}

	public String getStrParticulaDtl() {
		return strParticulaDtl;
	}

	public void setStrParticulaDtl(String strParticulaDtl) {
		this.strParticulaDtl = strParticulaDtl;
	}

	public String getStrTotalExpenseAmt() {
		return strTotalExpenseAmt;
	}

	public void setStrTotalExpenseAmt(String strTotalExpenseAmt) {
		this.strTotalExpenseAmt = strTotalExpenseAmt;
	}

	public String getStrTotalDiscAmt() {
		return strTotalDiscAmt;
	}

	public void setStrTotalDiscAmt(String strTotalDiscAmt) {
		this.strTotalDiscAmt = strTotalDiscAmt;
	}

	public String getStrTotalReceAmtByPatient() {
		return strTotalReceAmtByPatient;
	}

	public void setStrTotalReceAmtByPatient(String strTotalReceAmtByPatient) {
		this.strTotalReceAmtByPatient = strTotalReceAmtByPatient;
	}

	public String getStrTotalBenifitFrmClt() {
		return strTotalBenifitFrmClt;
	}

	public void setStrTotalBenifitFrmClt(String strTotalBenifitFrmClt) {
		this.strTotalBenifitFrmClt = strTotalBenifitFrmClt;
	}

	public String getStrNetPaymentAmt() {
		return strNetPaymentAmt;
	}

	public void setStrNetPaymentAmt(String strNetPaymentAmt) {
		this.strNetPaymentAmt = strNetPaymentAmt;
	}

	public String getStrOffLineTariffDiscountUnit() {
		return strOffLineTariffDiscountUnit;
	}

	public void setStrOffLineTariffDiscountUnit(
			String strOffLineTariffDiscountUnit) {
		this.strOffLineTariffDiscountUnit = strOffLineTariffDiscountUnit;
	}

	public String getStrOffLineTariffDiscountType() {
		return strOffLineTariffDiscountType;
	}

	public void setStrOffLineTariffDiscountType(
			String strOffLineTariffDiscountType) {
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

	public void setStrOffLineTariffDiscountDate(
			String strOffLineTariffDiscountDate) {
		this.strOffLineTariffDiscountDate = strOffLineTariffDiscountDate;
	}

	public WebRowSet getOfflineGroupList() {
		return OfflineGroupList;
	}

	public void setOfflineGroupList(WebRowSet offlineGroupList) {
		OfflineGroupList = offlineGroupList;
	}

	public WebRowSet getOfflinePackageGroup() {
		return OfflinePackageGroup;
	}

	public void setOfflinePackageGroup(WebRowSet offlinePackageGroup) {
		OfflinePackageGroup = offlinePackageGroup;
	}

	public WebRowSet getOfflineTariffList() {
		return OfflineTariffList;
	}

	public void setOfflineTariffList(WebRowSet offlineTariffList) {
		OfflineTariffList = offlineTariffList;
	}

	public String getStrOffLineWard() {
		return strOffLineWard;
	}

	public void setStrOffLineWard(String strOffLineWard) {
		this.strOffLineWard = strOffLineWard;
	}

	public WebRowSet getOfflineDiscountRemarks() {
		return OfflineDiscountRemarks;
	}

	public void setOfflineDiscountRemarks(WebRowSet offlineDiscountRemarks) {
		OfflineDiscountRemarks = offlineDiscountRemarks;
	}

	public WebRowSet getOfflineTariffUnit() {
		return OfflineTariffUnit;
	}

	public void setOfflineTariffUnit(WebRowSet offlineTariffUnit) {
		OfflineTariffUnit = offlineTariffUnit;
	}

	public String getStrOffLineTariffUnitTempId() {
		return StrOffLineTariffUnitTempId;
	}

	public void setStrOffLineTariffUnitTempId(String strOffLineTariffUnitTempId) {
		StrOffLineTariffUnitTempId = strOffLineTariffUnitTempId;
	}

	public WebRowSet getOfflineDiscountApprovedBy() {
		return OfflineDiscountApprovedBy;
	}

	public void setOfflineDiscountApprovedBy(WebRowSet offlineDiscountApprovedBy) {
		OfflineDiscountApprovedBy = offlineDiscountApprovedBy;
	}

	public String getStrOffLineTreatmentCategory() {
		return strOffLineTreatmentCategory;
	}

	public void setStrOffLineTreatmentCategory(
			String strOffLineTreatmentCategory) {
		this.strOffLineTreatmentCategory = strOffLineTreatmentCategory;
	}

	public String getStrHidden() {
		return strHidden;
	}

	public void setStrHidden(String strHidden) {
		this.strHidden = strHidden;
	}

	public String getStrUnitId() {
		return strUnitId;
	}

	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}

	public String[] getStrOfflinePackageName() {
		return strOfflinePackageName;
	}

	public void setStrOfflinePackageName(String[] strOfflinePackageName) {
		this.strOfflinePackageName = strOfflinePackageName;
	}

	public String[] getStrOfflinePackageRateUnit() {
		return strOfflinePackageRateUnit;
	}

	public void setStrOfflinePackageRateUnit(String[] strOfflinePackageRateUnit) {
		this.strOfflinePackageRateUnit = strOfflinePackageRateUnit;
	}

	public String[] getStrOfflinePackageReqQty() {
		return strOfflinePackageReqQty;
	}

	public void setStrOfflinePackageReqQty(String[] strOfflinePackageReqQty) {
		this.strOfflinePackageReqQty = strOfflinePackageReqQty;
	}

	public String[] getStrOfflinePackageUnit() {
		return strOfflinePackageUnit;
	}

	public void setStrOfflinePackageUnit(String[] strOfflinePackageUnit) {
		this.strOfflinePackageUnit = strOfflinePackageUnit;
	}

	public String[] getStrOfflinePackageServiceTax() {
		return strOfflinePackageServiceTax;
	}

	public void setStrOfflinePackageServiceTax(
			String[] strOfflinePackageServiceTax) {
		this.strOfflinePackageServiceTax = strOfflinePackageServiceTax;
	}

	public String[] getStrOfflinePackageDiscount() {
		return strOfflinePackageDiscount;
	}

	public void setStrOfflinePackageDiscount(String[] strOfflinePackageDiscount) {
		this.strOfflinePackageDiscount = strOfflinePackageDiscount;
	}

	public String[] getStrOfflinePackageNetAmount() {
		return strOfflinePackageNetAmount;
	}

	public void setStrOfflinePackageNetAmount(
			String[] strOfflinePackageNetAmount) {
		this.strOfflinePackageNetAmount = strOfflinePackageNetAmount;
	}

	public String getStrPukNo() {
		return strPukNo;
	}

	public void setStrPukNo(String strPukNo) {
		this.strPukNo = strPukNo;
	}

	public String[] getStrMultiRowHiddenValue() {
		return strMultiRowHiddenValue;
	}

	public void setStrMultiRowHiddenValue(String[] strMultiRowHiddenValue) {
		this.strMultiRowHiddenValue = strMultiRowHiddenValue;
	}

	public String[] getStrTariffId() {
		return strTariffId;
	}

	public void setStrTariffId(String[] strTariffId) {
		this.strTariffId = strTariffId;
	}

	public String getStrIpdChgTypeId() {
		return strIpdChgTypeId;
	}

	public void setStrIpdChgTypeId(String strIpdChgTypeId) {
		this.strIpdChgTypeId = strIpdChgTypeId;
	}

	public String[] getStrGrpId() {
		return strGrpId;
	}

	public void setStrGrpId(String[] strGrpId) {
		this.strGrpId = strGrpId;
	}

	public String[] getStrTariffRate() {
		return strTariffRate;
	}

	public void setStrTariffRate(String[] strTariffRate) {
		this.strTariffRate = strTariffRate;
	}

	public String[] getStrBillQty() {
		return strBillQty;
	}

	public void setStrBillQty(String[] strBillQty) {
		this.strBillQty = strBillQty;
	}

	public String[] getStrServTax() {
		return strServTax;
	}

	public void setStrServTax(String[] strServTax) {
		this.strServTax = strServTax;
	}

	public String[] getStrTariffActCost() {
		return strTariffActCost;
	}

	public void setStrTariffActCost(String[] strTariffActCost) {
		this.strTariffActCost = strTariffActCost;
	}

	public String[] getStrIsPackg() {
		return strIsPackg;
	}

	public void setStrIsPackg(String[] strIsPackg) {
		this.strIsPackg = strIsPackg;
	}

	public String[] getStrUnitBaseValue() {
		return strUnitBaseValue;
	}

	public void setStrUnitBaseValue(String[] strUnitBaseValue) {
		this.strUnitBaseValue = strUnitBaseValue;
	}

	public String[] getStrRateBaseValue() {
		return strRateBaseValue;
	}

	public void setStrRateBaseValue(String[] strRateBaseValue) {
		this.strRateBaseValue = strRateBaseValue;
	}

	public String[] getStrTariffActualCost() {
		return strTariffActualCost;
	}

	public void setStrTariffActualCost(String[] strTariffActualCost) {
		this.strTariffActualCost = strTariffActualCost;
	}

	public String[] getStrServTaxAmt() {
		return strServTaxAmt;
	}

	public void setStrServTaxAmt(String[] strServTaxAmt) {
		this.strServTaxAmt = strServTaxAmt;
	}

	public String[] getStrUnitIDVal() {
		return strUnitIDVal;
	}

	public void setStrUnitIDVal(String[] strUnitIDVal) {
		this.strUnitIDVal = strUnitIDVal;
	}

	public String[] getStrTariffNetAmt() {
		return strTariffNetAmt;
	}

	public void setStrTariffNetAmt(String[] strTariffNetAmt) {
		this.strTariffNetAmt = strTariffNetAmt;
	}

	public String[] getStrIsRefundable() {
		return strIsRefundable;
	}

	public void setStrIsRefundable(String[] strIsRefundable) {
		this.strIsRefundable = strIsRefundable;
	}

	public int getNSize() {
		return nSize;
	}

	public void setNSize(int size) {
		nSize = size;
	}

	public String[] getStrTempVal() {
		return strTempVal;
	}

	public void setStrTempVal(String[] strTempVal) {
		this.strTempVal = strTempVal;
	}

	public String getStrMaxBenifitAmt() {
		return strMaxBenifitAmt;
	}

	public void setStrMaxBenifitAmt(String strMaxBenifitAmt) {
		this.strMaxBenifitAmt = strMaxBenifitAmt;
	}

	public String getStrReceivedAmt() {
		return strReceivedAmt;
	}

	public void setStrReceivedAmt(String strReceivedAmt) {
		this.strReceivedAmt = strReceivedAmt;
	}

	public String getStrTariffDiscountAmtConfgDtlBillApproval() {
		return strTariffDiscountAmtConfgDtlBillApproval;
	}

	public void setStrTariffDiscountAmtConfgDtlBillApproval(
			String strTariffDiscountAmtConfgDtlBillApproval) {
		this.strTariffDiscountAmtConfgDtlBillApproval = strTariffDiscountAmtConfgDtlBillApproval;
	}

	public String getStrTotalServiceTax() {
		return strTotalServiceTax;
	}

	public void setStrTotalServiceTax(String strTotalServiceTax) {
		this.strTotalServiceTax = strTotalServiceTax;
	}

	public String getStrNetPaybleAmt() {
		return strNetPaybleAmt;
	}

	public void setStrNetPaybleAmt(String strNetPaybleAmt) {
		this.strNetPaybleAmt = strNetPaybleAmt;
	}

	public String getStrUserLevel() {
		return strUserLevel;
	}

	public void setStrUserLevel(String strUserLevel) {
		this.strUserLevel = strUserLevel;
	}

	public String[] getChkBox1() {
		return chkBox1;
	}

	public void setChkBox1(String[] chkBox1) {
		this.chkBox1 = chkBox1;
	}

	public String[] getStrReqDate() {
		return strReqDate;
	}

	public void setStrReqDate(String[] strReqDate) {
		this.strReqDate = strReqDate;
	}

	public String[] getStrUnProcessQty() {
		return strUnProcessQty;
	}

	public void setStrUnProcessQty(String[] strUnProcessQty) {
		this.strUnProcessQty = strUnProcessQty;
	}

	public String[] getStrRefundQty() {
		return strRefundQty;
	}

	public void setStrRefundQty(String[] strRefundQty) {
		this.strRefundQty = strRefundQty;
	}

	public String[] getStrUnitCombo() {
		return strUnitCombo;
	}

	public void setStrUnitCombo(String[] strUnitCombo) {
		this.strUnitCombo = strUnitCombo;
	}

	public String[] getStrUnProcNetAmt() {
		return strUnProcNetAmt;
	}

	public void setStrUnProcNetAmt(String[] strUnProcNetAmt) {
		this.strUnProcNetAmt = strUnProcNetAmt;
	}

	public String[] getStrUnProcTarriffName() {
		return strUnProcTarriffName;
	}

	public void setStrUnProcTarriffName(String[] strUnProcTarriffName) {
		this.strUnProcTarriffName = strUnProcTarriffName;
	}

	public int getNChkSize() {
		return nChkSize;
	}

	public void setNChkSize(int chkSize) {
		nChkSize = chkSize;
	}

	public String getStrDefaultQtyType() {
		return strDefaultQtyType;
	}

	public void setStrDefaultQtyType(String strDefaultQtyType) {
		this.strDefaultQtyType = strDefaultQtyType;
	}

	public String getStrOffLineDisApprovedBy() {
		return strOffLineDisApprovedBy;
	}

	public void setStrOffLineDisApprovedBy(String strOffLineDisApprovedBy) {
		this.strOffLineDisApprovedBy = strOffLineDisApprovedBy;
	}

	public String getStrUnProcHiddVal() {
		return strUnProcHiddVal;
	}

	public void setStrUnProcHiddVal(String strUnProcHiddVal) {
		this.strUnProcHiddVal = strUnProcHiddVal;
	}

	public String[] getStrUnProReqNo() {
		return strUnProReqNo;
	}

	public void setStrUnProReqNo(String[] strUnProReqNo) {
		this.strUnProReqNo = strUnProReqNo;
	}

	public String[] getStrUnProDeptCode() {
		return strUnProDeptCode;
	}

	public void setStrUnProDeptCode(String[] strUnProDeptCode) {
		this.strUnProDeptCode = strUnProDeptCode;
	}

	public String[] getStrUnProWardCode() {
		return strUnProWardCode;
	}

	public void setStrUnProWardCode(String[] strUnProWardCode) {
		this.strUnProWardCode = strUnProWardCode;
	}

	public String[] getStrUnProTariffId() {
		return strUnProTariffId;
	}

	public void setStrUnProTariffId(String[] strUnProTariffId) {
		this.strUnProTariffId = strUnProTariffId;
	}

	public String[] getStrUnProcBServiceId() {
		return strUnProcBServiceId;
	}

	public void setStrUnProcBServiceId(String[] strUnProcBServiceId) {
		this.strUnProcBServiceId = strUnProcBServiceId;
	}

	public String[] getStrUnProcIpdChgTypeId() {
		return strUnProcIpdChgTypeId;
	}

	public void setStrUnProcIpdChgTypeId(String[] strUnProcIpdChgTypeId) {
		this.strUnProcIpdChgTypeId = strUnProcIpdChgTypeId;
	}

	public String[] getStrUnProServiceId() {
		return strUnProServiceId;
	}

	public void setStrUnProServiceId(String[] strUnProServiceId) {
		this.strUnProServiceId = strUnProServiceId;
	}

	public String[] getStrUnProHblDisUnit() {
		return strUnProHblDisUnit;
	}

	public void setStrUnProHblDisUnit(String[] strUnProHblDisUnit) {
		this.strUnProHblDisUnit = strUnProHblDisUnit;
	}

	public String[] getStrUnProHblDisType() {
		return strUnProHblDisType;
	}

	public void setStrUnProHblDisType(String[] strUnProHblDisType) {
		this.strUnProHblDisType = strUnProHblDisType;
	}

	public String[] getStrUnProGstrTariffId() {
		return strUnProGstrTariffId;
	}

	public void setStrUnProGstrTariffId(String[] strUnProGstrTariffId) {
		this.strUnProGstrTariffId = strUnProGstrTariffId;
	}

	public String[] getStrUnProcGstrReqNo() {
		return strUnProcGstrReqNo;
	}

	public void setStrUnProcGstrReqNo(String[] strUnProcGstrReqNo) {
		this.strUnProcGstrReqNo = strUnProcGstrReqNo;
	}

	public String[] getStrUnProcHbAppId() {
		return strUnProcHbAppId;
	}

	public void setStrUnProcHbAppId(String[] strUnProcHbAppId) {
		this.strUnProcHbAppId = strUnProcHbAppId;
	}

	public String[] getStrUnProTariffRate() {
		return strUnProTariffRate;
	}

	public void setStrUnProTariffRate(String[] strUnProTariffRate) {
		this.strUnProTariffRate = strUnProTariffRate;
	}

	public String[] getStrUnProRateUnitCode() {
		return strUnProRateUnitCode;
	}

	public void setStrUnProRateUnitCode(String[] strUnProRateUnitCode) {
		this.strUnProRateUnitCode = strUnProRateUnitCode;
	}

	public String[] getStrUnProQtyUnitId() {
		return strUnProQtyUnitId;
	}

	public void setStrUnProQtyUnitId(String[] strUnProQtyUnitId) {
		this.strUnProQtyUnitId = strUnProQtyUnitId;
	}

	public String[] getStrUnProHbReciptNo() {
		return strUnProHbReciptNo;
	}

	public void setStrUnProHbReciptNo(String[] strUnProHbReciptNo) {
		this.strUnProHbReciptNo = strUnProHbReciptNo;
	}

	public String[] getStrUnProcTariffActualRate() {
		return strUnProcTariffActualRate;
	}

	public void setStrUnProcTariffActualRate(String[] strUnProcTariffActualRate) {
		this.strUnProcTariffActualRate = strUnProcTariffActualRate;
	}

	public String[] getStrUnProcHbIsPkg() {
		return strUnProcHbIsPkg;
	}

	public void setStrUnProcHbIsPkg(String[] strUnProcHbIsPkg) {
		this.strUnProcHbIsPkg = strUnProcHbIsPkg;
	}

	public String[] getStrUnProRemainQty() {
		return strUnProRemainQty;
	}

	public void setStrUnProRemainQty(String[] strUnProRemainQty) {
		this.strUnProRemainQty = strUnProRemainQty;
	}

	public String[] getStrUnProUnitBaseVal() {
		return strUnProUnitBaseVal;
	}

	public void setStrUnProUnitBaseVal(String[] strUnProUnitBaseVal) {
		this.strUnProUnitBaseVal = strUnProUnitBaseVal;
	}

	public String[] getStrUnProcSercTax() {
		return strUnProcSercTax;
	}

	public void setStrUnProcSercTax(String[] strUnProcSercTax) {
		this.strUnProcSercTax = strUnProcSercTax;
	}

	public String[] getStrUnProcUnitName() {
		return strUnProcUnitName;
	}

	public void setStrUnProcUnitName(String[] strUnProcUnitName) {
		this.strUnProcUnitName = strUnProcUnitName;
	}

	public String[] getStrDiscAmt() {
		return strDiscAmt;
	}

	public void setStrDiscAmt(String[] strDiscAmt) {
		this.strDiscAmt = strDiscAmt;
	}

	public String[] getStrUnProcessServiceTaxAmt() {
		return strUnProcessServiceTaxAmt;
	}

	public void setStrUnProcessServiceTaxAmt(String[] strUnProcessServiceTaxAmt) {
		this.strUnProcessServiceTaxAmt = strUnProcessServiceTaxAmt;
	}

	public String[] getStrUnProcessServiceTax() {
		return strUnProcessServiceTax;
	}

	public void setStrUnProcessServiceTax(String[] strUnProcessServiceTax) {
		this.strUnProcessServiceTax = strUnProcessServiceTax;
	}

	public String[] getStrOfflineTariffQty() {
		return strOfflineTariffQty;
	}

	public void setStrOfflineTariffQty(String[] strOfflineTariffQty) {
		this.strOfflineTariffQty = strOfflineTariffQty;
	}

	public String[] getStrUnProcRefQty() {
		return strUnProcRefQty;
	}

	public void setStrUnProcRefQty(String[] strUnProcRefQty) {
		this.strUnProcRefQty = strUnProcRefQty;
	}

	public String getTotalRecAmtDivId1() {
		return totalRecAmtDivId1;
	}

	public void setTotalRecAmtDivId1(String totalRecAmtDivId1) {
		this.totalRecAmtDivId1 = totalRecAmtDivId1;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrIpdThirdPartyBilling() {
		return strIpdThirdPartyBilling;
	}

	public void setStrIpdThirdPartyBilling(String strIpdThirdPartyBilling) {
		this.strIpdThirdPartyBilling = strIpdThirdPartyBilling;
	}

	public String getStrIpdRoundOff() {
		return strIpdRoundOff;
	}

	public void setStrIpdRoundOff(String strIpdRoundOff) {
		this.strIpdRoundOff = strIpdRoundOff;
	}

	public String getStrPatientDetailsView() {
		return strPatientDetailsView;
	}

	public void setStrPatientDetailsView(String strPatientDetailsView) {
		this.strPatientDetailsView = strPatientDetailsView;
	}

	public String getStrRate() {
		return strRate;
	}

	public void setStrRate(String strRate) {
		this.strRate = strRate;
	}

	public String getStrChkRcdOpen() {
		return strChkRcdOpen;
	}

	public void setStrChkRcdOpen(String strChkRcdOpen) {
		this.strChkRcdOpen = strChkRcdOpen;
	}

	public String getModeVal() {
		return modeVal;
	}

	public void setModeVal(String modeVal) {
		this.modeVal = modeVal;
	}

	public String getActual_amt() {
		return actual_amt;
	}

	public void setActual_amt(String actual_amt) {
		this.actual_amt = actual_amt;
	}

	public String getSer_tax_amt() {
		return ser_tax_amt;
	}

	public void setSer_tax_amt(String ser_tax_amt) {
		this.ser_tax_amt = ser_tax_amt;
	}

	public String getTrf_amt_tot() {
		return trf_amt_tot;
	}

	public void setTrf_amt_tot(String trf_amt_tot) {
		this.trf_amt_tot = trf_amt_tot;
	}

	public String getTrf_amt() {
		return trf_amt;
	}

	public void setTrf_amt(String trf_amt) {
		this.trf_amt = trf_amt;
	}

	public String[] getStrServiceId() {
		return strServiceId;
	}

	public void setStrServiceId(String[] strServiceId) {
		this.strServiceId = strServiceId;
	}

	public String[] getStrGlblTariffId() {
		return strGlblTariffId;
	}

	public void setStrGlblTariffId(String[] strGlblTariffId) {
		this.strGlblTariffId = strGlblTariffId;
	}

	public String getStrDefaultUnit() {
		return strDefaultUnit;
	}

	public void setStrDefaultUnit(String strDefaultUnit) {
		this.strDefaultUnit = strDefaultUnit;
	}

	public String getDis_amt() {
		return dis_amt;
	}

	public void setDis_amt(String dis_amt) {
		this.dis_amt = dis_amt;
	}

	public String getChkgValue() {
		return ChkgValue;
	}

	public void setChkgValue(String chkgValue) {
		ChkgValue = chkgValue;
	}

	public String getStrBillTypeCombo() {
		return strBillTypeCombo;
	}

	public void setStrBillTypeCombo(String strBillTypeCombo) {
		this.strBillTypeCombo = strBillTypeCombo;
	}

	public String getStrNetServiceTaxAmt() {
		return strNetServiceTaxAmt;
	}

	public void setStrNetServiceTaxAmt(String strNetServiceTaxAmt) {
		this.strNetServiceTaxAmt = strNetServiceTaxAmt;
	}

	public String getStrNetTariffAmt() {
		return strNetTariffAmt;
	}

	public void setStrNetTariffAmt(String strNetTariffAmt) {
		this.strNetTariffAmt = strNetTariffAmt;
	}

	public String getStrNetActualTariffAmt() {
		return strNetActualTariffAmt;
	}

	public void setStrNetActualTariffAmt(String strNetActualTariffAmt) {
		this.strNetActualTariffAmt = strNetActualTariffAmt;
	}

	public WebRowSet getStrParticularDtlsListWs() {
		return strParticularDtlsListWs;
	}

	public void setStrParticularDtlsListWs(WebRowSet strParticularDtlsListWs) {
		this.strParticularDtlsListWs = strParticularDtlsListWs;
	}

	public WebRowSet getStrParticularDtlsWs() {
		return strParticularDtlsWs;
	}

	public void setStrParticularDtlsWs(WebRowSet strParticularDtlsWs) {
		this.strParticularDtlsWs = strParticularDtlsWs;
	}

	public String getStrTotalDiscountApprovalId() {
		return strTotalDiscountApprovalId;
	}

	public void setStrTotalDiscountApprovalId(String strTotalDiscountApprovalId) {
		this.strTotalDiscountApprovalId = strTotalDiscountApprovalId;
	}

	public String getStrTotalBillApprovalId() {
		return strTotalBillApprovalId;
	}

	public void setStrTotalBillApprovalId(String strTotalBillApprovalId) {
		this.strTotalBillApprovalId = strTotalBillApprovalId;
	}

	public String getStrServiceTax() {
		return strServiceTax;
	}

	public void setStrServiceTax(String strServiceTax) {
		this.strServiceTax = strServiceTax;
	}

	public String[] getStrUnProcServiceTaxAmt() {
		return strUnProcServiceTaxAmt;
	}

	public void setStrUnProcServiceTaxAmt(String[] strUnProcServiceTaxAmt) {
		this.strUnProcServiceTaxAmt = strUnProcServiceTaxAmt;
	}

	public String[] getStrUnProcGroupId() {
		return strUnProcGroupId;
	}

	public void setStrUnProcGroupId(String[] strUnProcGroupId) {
		this.strUnProcGroupId = strUnProcGroupId;
	}

	public String[] getStrOfflineTariffDetailsHidden() {
		return strOfflineTariffDetailsHidden;
	}

	public void setStrOfflineTariffDetailsHidden(
			String[] strOfflineTariffDetailsHidden) {
		this.strOfflineTariffDetailsHidden = strOfflineTariffDetailsHidden;
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

	public void setStrOfflineTariffServiceTax(
			String[] strOfflineTariffServiceTax) {
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

	public String[] getStrActualTariffNetAmt() {
		return strActualTariffNetAmt;
	}

	public void setStrActualTariffNetAmt(String[] strActualTariffNetAmt) {
		this.strActualTariffNetAmt = strActualTariffNetAmt;
	}

	/**
	 * @return the strOffLineTariffDiscountReasonText
	 */
	public String getStrOffLineTariffDiscountReasonText() {
		return strOffLineTariffDiscountReasonText;
	}

	/**
	 * @param strOffLineTariffDiscountReasonText
	 *            the strOffLineTariffDiscountReasonText to set
	 */
	public void setStrOffLineTariffDiscountReasonText(
			String strOffLineTariffDiscountReasonText) {
		this.strOffLineTariffDiscountReasonText = strOffLineTariffDiscountReasonText;
	}

	public WebRowSet getWardTypeList() {
		return wardTypeList;
	}

	public void setWardTypeList(WebRowSet wardTypeList) {
		this.wardTypeList = wardTypeList;
	}

	public String getStrNewTreatmentCategory() {
		return strNewTreatmentCategory;
	}

	public void setStrNewTreatmentCategory(String strNewTreatmentCategory) {
		this.strNewTreatmentCategory = strNewTreatmentCategory;
	}

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	public String getStrEndDate() {
		return strEndDate;
	}

	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}

	public String getStrWardType() {
		return strWardType;
	}

	public void setStrWardType(String strWardType) {
		this.strWardType = strWardType;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrTariffCode() {
		return strTariffCode;
	}

	public void setStrTariffCode(String strTariffCode) {
		this.strTariffCode = strTariffCode;
	}

	public String[] getStrCompChargeCheck() {
		return strCompChargeCheck;
	}

	public void setStrCompChargeCheck(String[] strCompChargeCheck) {
		this.strCompChargeCheck = strCompChargeCheck;
	}

	public String getStrDepartment() {
		return strDepartment;
	}

	public void setStrDepartment(String strDepartment) {
		this.strDepartment = strDepartment;
	}
 

	public WebRowSet getStrDepartmentList() {
		return strDepartmentList;
	}

	public void setStrDepartmentList(WebRowSet strDepartmentList) {
		this.strDepartmentList = strDepartmentList;
	}

	public String getStrSpecialWardType() {
		return strSpecialWardType;
	}

	public void setStrSpecialWardType(String strSpecialWardType) {
		this.strSpecialWardType = strSpecialWardType;
	}

	public String[] getStrSpecialChargeCheck() {
		return strSpecialChargeCheck;
	}

	public void setStrSpecialChargeCheck(String[] strSpecialChargeCheck) {
		this.strSpecialChargeCheck = strSpecialChargeCheck;
	}

	public int getNCompChargeCount() {
		return nCompChargeCount;
	}

	public void setNCompChargeCount(int compChargeCount) {
		nCompChargeCount = compChargeCount;
	}

	public String getStrPreviousDates() {
		return strPreviousDates;
	}

	public void setStrPreviousDates(String strPreviousDates) {
		this.strPreviousDates = strPreviousDates;
	}

	public WebRowSet getStrPreviousDatesList() {
		return strPreviousDatesList;
	}

	public void setStrPreviousDatesList(WebRowSet strPreviousDatesList) {
		this.strPreviousDatesList = strPreviousDatesList;
	}

	public String[] getStrPreviousCheck() {
		return strPreviousCheck;
	}

	public void setStrPreviousCheck(String[] strPreviousCheck) {
		this.strPreviousCheck = strPreviousCheck;
	}

	public WebRowSet getStrPreviousDtls() {
		return strPreviousDtls;
	}

	public void setStrPreviousDtls(WebRowSet strPreviousDtls) {
		this.strPreviousDtls = strPreviousDtls;
	}

	public String getStrSearchLetter() {
		return strSearchLetter;
	}

	public void setStrSearchLetter(String strSearchLetter) {
		this.strSearchLetter = strSearchLetter;
	}

	public String getStrPoorFreeWelfareAmt() {
		return strPoorFreeWelfareAmt;
	}

	public void setStrPoorFreeWelfareAmt(String strPoorFreeWelfareAmt) {
		this.strPoorFreeWelfareAmt = strPoorFreeWelfareAmt;
	}

	public String getStrPoorFreeGrantAmt() {
		return strPoorFreeGrantAmt;
	}

	public void setStrPoorFreeGrantAmt(String strPoorFreeGrantAmt) {
		this.strPoorFreeGrantAmt = strPoorFreeGrantAmt;
	}

	public String[] getStrServiceType() {
		return strServiceType;
	}

	public void setStrServiceType(String[] strServiceType) {
		this.strServiceType = strServiceType;
	}

	public WebRowSet getStrAccStatusCombo() {
		return strAccStatusCombo;
	}

	public void setStrAccStatusCombo(WebRowSet strAccStatusCombo) {
		this.strAccStatusCombo = strAccStatusCombo;
	}
	public String[] getStrOfflineTariffDate() {
		return strOfflineTariffDate;
	}

	public void setStrOfflineTariffDate(String[] strOfflineTariffDate) {
		this.strOfflineTariffDate = strOfflineTariffDate;
	}

	public String[] getStrPriority() {
		return strPriority;
	}

	public void setStrPriority(String[] strPriority) {
		this.strPriority = strPriority;
	}

	public String[] getStrDiscount() {
		return strDiscount;
	}

	public void setStrDiscount(String[] strDiscount) {
		this.strDiscount = strDiscount;
	}

	public String[] getStrDiscountType() {
		return strDiscountType;
	}

	public void setStrDiscountType(String[] strDiscountType) {
		this.strDiscountType = strDiscountType;
	}

	public String[] getStrDiscountAmt() {
		return strDiscountAmt;
	}

	public void setStrDiscountAmt(String[] strDiscountAmt) {
		this.strDiscountAmt = strDiscountAmt;
	}

	public String[] getStrOfflineTariffName() {
		return strOfflineTariffName;
	}

	public void setStrOfflineTariffName(String[] strOfflineTariffName) {
		this.strOfflineTariffName = strOfflineTariffName;
	}

	public WebRowSet getAllTrfListWS() {
		return allTrfListWS;
	}

	public void setAllTrfListWS(WebRowSet allTrfListWS) {
		this.allTrfListWS = allTrfListWS;
	}

	public String getStrAllTrfHLP() {
		return strAllTrfHLP;
	}

	public void setStrAllTrfHLP(String strAllTrfHLP) {
		this.strAllTrfHLP = strAllTrfHLP;
	}
	public String getStrNumRows() {
		return strNumRows;
	}
	public void setStrNumRows(String strNumRows) {
		this.strNumRows = strNumRows;
	}
	public String[] getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String[] deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String[] getStrPrevReqNo() {
		return strPrevReqNo;
	}
	public void setStrPrevReqNo(String[] strPrevReqNo) {
		this.strPrevReqNo = strPrevReqNo;
	}

	public WebRowSet getPrevBedTrf() {
		return prevBedTrf;
	}

	public void setPrevBedTrf(WebRowSet prevBedTrf) {
		this.prevBedTrf = prevBedTrf;
	}

	public String[] getStrTransferDeptCode() {
		return strTransferDeptCode;
	}

	public void setStrTransferDeptCode(String[] strTransferDeptCode) {
		this.strTransferDeptCode = strTransferDeptCode;
	}

	public String[] getStrTransferWardCode() {
		return strTransferWardCode;
	}

	public void setStrTransferWardCode(String[] strTransferWardCode) {
		this.strTransferWardCode = strTransferWardCode;
	}

	public String[] getStrTransferChargeType() {
		return strTransferChargeType;
	}

	public void setStrTransferChargeType(String[] strTransferChargeType) {
		this.strTransferChargeType = strTransferChargeType;
	}

	public String[] getStrInDate() {
		return strInDate;
	}

	public void setStrInDate(String[] strInDate) {
		this.strInDate = strInDate;
	}

	public String[] getStrOutDate() {
		return strOutDate;
	}

	public void setStrOutDate(String[] strOutDate) {
		this.strOutDate = strOutDate;
	}

	public String getStrPatientAdmndtl() {
		return strPatientAdmndtl;
	}

	public void setStrPatientAdmndtl(String strPatientAdmndtl) {
		this.strPatientAdmndtl = strPatientAdmndtl;
	}

	public WebRowSet getAdmissionListWS() {
		return admissionListWS;
	}

	public void setAdmissionListWS(WebRowSet admissionListWS) {
		this.admissionListWS = admissionListWS;
	}

	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getStrDischargeDate() {
		return strDischargeDate;
	}

	public void setStrDischargeDate(String strDischargeDate) {
		this.strDischargeDate = strDischargeDate;
	}

	public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}

	public String[] getStrMovNo() {
		return strMovNo;
	}

	public void setStrMovNo(String[] strMovNo) {
		this.strMovNo = strMovNo;
	}

	public String[] getStrSaveFlag() {
		return strSaveFlag;
	}

	public void setStrSaveFlag(String[] strSaveFlag) {
		this.strSaveFlag = strSaveFlag;
	}

	public String getStrchkvalue() {
		return strchkvalue;
	}

	public void setStrchkvalue(String strchkvalue) {
		this.strchkvalue = strchkvalue;
	}
	public String[] getStrIsDoubleOc() {
		return strIsDoubleOc;
	}
	public void setStrIsDoubleOc(String[] strIsDoubleOc) {
		this.strIsDoubleOc = strIsDoubleOc;
	}
	public String[] getStrDblOccDate() {
		return strDblOccDate;
	}
	public void setStrDblOccDate(String[] strDblOccDate) {
		this.strDblOccDate = strDblOccDate;
	}

	public String[] getStrUnitCode() {
		return strUnitCode;
	}

	public void setStrUnitCode(String[] strUnitCode) {
		this.strUnitCode = strUnitCode;
	}

	public String[] getStrConsultant() {
		return strConsultant;
	}

	public void setStrConsultant(String[] strConsultant) {
		this.strConsultant = strConsultant;
	}

	public String getStrCurrindex() {
		return strCurrindex;
	}

	public void setStrCurrindex(String strCurrindex) {
		this.strCurrindex = strCurrindex;
	}

	public String getStrAccStatus() {
		return strAccStatus;
	}

	public void setStrAccStatus(String strAccStatus) {
		this.strAccStatus = strAccStatus;
	}

	public String getStrbcflag() {
		return strbcflag;
	}

	public void setStrbcflag(String strbcflag) {
		this.strbcflag = strbcflag;
	}

	public String getFinalBillFlag() {
		return finalBillFlag;
	}

	public void setFinalBillFlag(String finalBillFlag) {
		this.finalBillFlag = finalBillFlag;
	}
	public String[] getsNo() {
		return sNo;
	}
	public void setsNo(String[] sNo) {
		this.sNo = sNo;
	}
	public String[] getAdtOnlineFlag() {
		return adtOnlineFlag;
	}
	public void setAdtOnlineFlag(String[] adtOnlineFlag) {
		this.adtOnlineFlag = adtOnlineFlag;
	}
	public WebRowSet getStrPatAdmWs() {
		return strPatAdmWs;
	}
	public void setStrPatAdmWs(WebRowSet strPatAdmWs) {
		this.strPatAdmWs = strPatAdmWs;
	}
	public String[] getStrNewBabyBed() {
		return strNewBabyBed;
	}
	public void setStrNewBabyBed(String[] strNewBabyBed) {
		this.strNewBabyBed = strNewBabyBed;
	}
	
	
}
