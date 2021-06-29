package billing.transactions;

import hisglobal.utility.TransferObject;
import javax.sql.rowset.WebRowSet;

public class BillReconcileTransVO implements TransferObject {

	private static final long serialVersionUID = 02L;
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqRefundWs = null;

	private String strMsgString = null;
	private String strMsgType = "";

	private String strTariffDetailsValue = "";
	
	private String strCrNo = "";
	private String strValmode = "";
	private String strGroup = "0";
	private String strCategoryCode = "0";
	private String strTariffCode = "0";
	private String strTariffTempId = "0";
	
	private String strGblRequestNo = "0";
	private String strGblServiceId = "0";
	
	private String strBillNo = "0";
	private String strChargeTypeId = "0";
	private String strTransactionType = "0";
	private String strBserviceId = "0";
	private String strPatAccountNo = "0";
	private String strRequestNo = "0";
	private String strWardCode = "0";
	private String strReceiptNo = "0";
	private String strIsBill = "0";
	private String strIsOnline = "0";
	private String strAdmissionNo = "0";
	private String strEpisodeCode = "0";
	private String strCatCode = "0";
	private String strDepartmentCode = "0";
	private String strIpdChargeTypeId = "0";
	private String strServiceId = "0";
	
	
	private String[] strTariffId = null;
	private String[] strQtyUnitId = null;
	private String[] strTariffRate = null;
	private String[] strRateUnit = null;
	private String[] strGroupId = null;
	private String[] strActualTariffRate = null;
	private String[] strServiceTax = null;
	private String[] strDiscountType = null;
	private String[] strDiscountUnit = null;
	private String[] strGTariffId = null;
	private String[] strDiscountApprovalId = null;
	private String[] strBaseUnitVal = null;
	private String[] strIsPackage = null;
	private String[] strNetAmount = null;
	private String[] strPenalty = null;
	private String[] strSServiceId = null;
	private String[] strBalanceQty = null;
	private String[] strRemainingQty = null;
	private String[] strTariffReceiptNo = null;
	private String[] strAccReqNo = null;
	private String strTempVal[] = null;

	private String strReconcilationBy = "";
	private String strReconcilationReason = "";
	private String strReconcilationReasonText = "";

	private String strErrPrimaryKeyLog = "";

	private String[] strOfflineTariffDiscountConfigDetails = null;

	private String strHospitalServices = "0";
	private String strHospitalCode = "";

	private WebRowSet strDiscAppByList = null;
	private WebRowSet strReconlByList = null;
	private WebRowSet strDisByRmkList = null;
	private WebRowSet strReconlByRmkList = null;

	private String strOffLinePackageIndex = "0";
	private String strIsPackageWise = "0";
	private String strTariffUnitTempId = "0";
	private String strSeatId = "";
	private String strUserLevel = "";

	private String[] strOfflineTariffDetailsHidden = null;
	private String[] strOfflineTariffDiscount = null;
	private String[] strOfflineTariffNetAmount = null;
	private String[] strOfflineTariffRateUnit = null;
	private String[] strOfflineTariffQty = null;
	private String[] strOfflineTariffUnit = null;
	private String[] strOfflineTariffServiceTax = null;

	
	private String[] strBillTariffVal = null ;
	private String[] strBillTariffRefund = null;
	private String[] strBillTariffUnit = null ;
	private String[] strBillTariffRefundAmount = null;
	
	private String[] strBillTariffRefundDiscountAmount = null;
	private String[] strBillTariffRefundServiceTaxAmount = null;
	
	private String strReconcileTariffAmount = "";
	
	
	
	private WebRowSet strBillWs = null;
	private WebRowSet strBillRecWs = null;
	
	private String[] strOfflineTariffName = null;

	private WebRowSet bServiceDtls = null;
	private WebRowSet fServiceDtls = null;

	private WebRowSet groupList = null;
	private WebRowSet tariffList = null;

	private WebRowSet tariffUnitList = null;
	private WebRowSet billDtlList = null;

	public WebRowSet getBillDtlList() {
		return billDtlList;
	}

	public void setBillDtlList(WebRowSet ws) {
		billDtlList = ws;
	}

	public WebRowSet getTariffList() {
		return tariffList;
	}

	public void setTariffList(WebRowSet tariffList) {
		this.tariffList = tariffList;
	}

	public WebRowSet getTariffUnitList() {
		return tariffUnitList;
	}

	public void setTariffUnitList(WebRowSet tariffUnitList) {
		this.tariffUnitList = tariffUnitList;
	}

	public WebRowSet getGroupList() {
		return groupList;
	}

	public void setGroupList(WebRowSet groupList) {
		this.groupList = groupList;
	}

	public String getStrIsPackageWise() {
		return strIsPackageWise;
	}

	public void setStrIsPackageWise(String strIsPackageWise) {
		this.strIsPackageWise = strIsPackageWise;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
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

	public WebRowSet getStrPatientDetailsWs() {
		return strPatientDetailsWs;
	}

	public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
		this.strPatientDetailsWs = strPatientDetailsWs;
	}

	public WebRowSet getStrOnLineReqRefundWs() {
		return strOnLineReqRefundWs;
	}

	public void setStrOnLineReqRefundWs(WebRowSet strOnLineReqRefundWs) {
		this.strOnLineReqRefundWs = strOnLineReqRefundWs;
	}

	public String getStrOffLinePackageIndex() {
		return strOffLinePackageIndex;
	}

	public void setStrOffLinePackageIndex(String strOffLinePackageIndex) {
		this.strOffLinePackageIndex = strOffLinePackageIndex;
	}

	public String getStrTariffUnitTempId() {
		return strTariffUnitTempId;
	}

	public void setStrTariffUnitTempId(String strTariffUnitTempId) {
		this.strTariffUnitTempId = strTariffUnitTempId;
	}

	public String getStrHospitalServices() {
		return strHospitalServices;
	}

	public void setStrHospitalServices(String strHospitalServices) {
		this.strHospitalServices = strHospitalServices;
	}

	/**
	 * @return the bServiceDtls
	 */
	public WebRowSet getBServiceDtls() {
		return bServiceDtls;
	}

	/**
	 * @param serviceDtls
	 *            the bServiceDtls to set
	 */
	public void setBServiceDtls(WebRowSet serviceDtls) {
		bServiceDtls = serviceDtls;
	}

	/**
	 * @return the fServiceDtls
	 */
	public WebRowSet getFServiceDtls() {
		return fServiceDtls;
	}

	/**
	 * @param serviceDtls
	 *            the fServiceDtls to set
	 */
	public void setFServiceDtls(WebRowSet serviceDtls) {
		fServiceDtls = serviceDtls;
	}

	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * @param strSeatId
	 *            the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * @return the strUserLevel
	 */
	public String getStrUserLevel() {
		return strUserLevel;
	}

	/**
	 * @param strUserLevel
	 *            the strUserLevel to set
	 */
	public void setStrUserLevel(String strUserLevel) {
		this.strUserLevel = strUserLevel;
	}

	/**
	 * @return the strOfflineTariffRateUnit
	 */
	public String[] getStrOfflineTariffRateUnit() {
		return strOfflineTariffRateUnit;
	}

	/**
	 * @param strOfflineTariffRateUnit
	 *            the strOfflineTariffRateUnit to set
	 */
	public void setStrOfflineTariffRateUnit(String[] strOfflineTariffRateUnit) {
		this.strOfflineTariffRateUnit = strOfflineTariffRateUnit;
	}

	/**
	 * @return the strOfflineTariffQty
	 */
	public String[] getStrOfflineTariffQty() {
		return strOfflineTariffQty;
	}

	/**
	 * @param strOfflineTariffQty
	 *            the strOfflineTariffQty to set
	 */
	public void setStrOfflineTariffQty(String[] strOfflineTariffQty) {
		this.strOfflineTariffQty = strOfflineTariffQty;
	}

	/**
	 * @return the strOfflineTariffUnit
	 */
	public String[] getStrOfflineTariffUnit() {
		return strOfflineTariffUnit;
	}

	/**
	 * @param strOfflineTariffUnit
	 *            the strOfflineTariffUnit to set
	 */
	public void setStrOfflineTariffUnit(String[] strOfflineTariffUnit) {
		this.strOfflineTariffUnit = strOfflineTariffUnit;
	}

	/**
	 * @return the strOfflineTariffServiceTax
	 */
	public String[] getStrOfflineTariffServiceTax() {
		return strOfflineTariffServiceTax;
	}

	/**
	 * @param strOfflineTariffServiceTax
	 *            the strOfflineTariffServiceTax to set
	 */
	public void setStrOfflineTariffServiceTax(
			String[] strOfflineTariffServiceTax) {
		this.strOfflineTariffServiceTax = strOfflineTariffServiceTax;
	}

	/**
	 * @return the strErrPrimaryKeyLog
	 */
	public String getStrErrPrimaryKeyLog() {
		return strErrPrimaryKeyLog;
	}

	/**
	 * @param strErrPrimaryKeyLog
	 *            the strErrPrimaryKeyLog to set
	 */
	public void setStrErrPrimaryKeyLog(String strErrPrimaryKeyLog) {
		this.strErrPrimaryKeyLog = strErrPrimaryKeyLog;
	}

	public String getStrReconcilationBy() {
		return strReconcilationBy;
	}

	public void setStrReconcilationBy(String strReconcilationBy) {
		this.strReconcilationBy = strReconcilationBy;
	}

	public String getStrReconcilationReason() {
		return strReconcilationReason;
	}

	public void setStrReconcilationReason(String strReconcilationReason) {
		this.strReconcilationReason = strReconcilationReason;
	}

	public String getStrReconcilationReasonText() {
		return strReconcilationReasonText;
	}

	public void setStrReconcilationReasonText(String strReconcilationReasonText) {
		this.strReconcilationReasonText = strReconcilationReasonText;
	}

	/**
	 * @return the strBillWs
	 */
	public WebRowSet getStrBillWs() {
		return strBillWs;
	}

	/**
	 * @param strBillWs
	 *            the strBillWs to set
	 */
	public void setStrBillWs(WebRowSet strBillWs) {
		this.strBillWs = strBillWs;
	}

	/**
	 * @return the strBillRecWs
	 */
	public WebRowSet getStrBillRecWs() {
		return strBillRecWs;
	}

	/**
	 * @param strBillRecWs
	 *            the strBillRecWs to set
	 */
	public void setStrBillRecWs(WebRowSet strBillRecWs) {
		this.strBillRecWs = strBillRecWs;
	}

	
	/**
	 * @return the strOfflineTariffDiscount
	 */
	public String[] getStrOfflineTariffDiscount() {
		return strOfflineTariffDiscount;
	}

	/**
	 * @param strOfflineTariffDiscount
	 *            the strOfflineTariffDiscount to set
	 */
	public void setStrOfflineTariffDiscount(String[] strOfflineTariffDiscount) {
		this.strOfflineTariffDiscount = strOfflineTariffDiscount;
	}

	/**
	 * @return the strOfflineTariffName
	 */
	public String[] getStrOfflineTariffName() {
		return strOfflineTariffName;
	}

	/**
	 * @param strOfflineTariffName
	 *            the strOfflineTariffName to set
	 */
	public void setStrOfflineTariffName(String[] strOfflineTariffName) {
		this.strOfflineTariffName = strOfflineTariffName;
	}

	// /**
	// * @return the strTariffId
	// */
	// public String[] getStrTariffId() {
	// return strTariffId;
	// }
	// /**
	// * @param strTariffId the strTariffId to set
	// */
	// public void setStrTariffId(String[] strTariffId) {
	// this.strTariffId = strTariffId;
	// }
	/**
	 * @return the strOfflineTariffDetailsHidden
	 */
	public String[] getStrOfflineTariffDetailsHidden() {
		return strOfflineTariffDetailsHidden;
	}

	/**
	 * @param strOfflineTariffDetailsHidden
	 *            the strOfflineTariffDetailsHidden to set
	 */
	public void setStrOfflineTariffDetailsHidden(
			String[] strOfflineTariffDetailsHidden) {
		this.strOfflineTariffDetailsHidden = strOfflineTariffDetailsHidden;
	}

	public WebRowSet getStrDiscAppByList() {
		return strDiscAppByList;
	}

	public void setStrDiscAppByList(WebRowSet strDiscAppByList) {
		this.strDiscAppByList = strDiscAppByList;
	}

	public WebRowSet getStrReconlByList() {
		return strReconlByList;
	}

	public void setStrReconlByList(WebRowSet strReconlByList) {
		this.strReconlByList = strReconlByList;
	}

	public WebRowSet getStrDisByRmkList() {
		return strDisByRmkList;
	}

	public void setStrDisByRmkList(WebRowSet strDisByRmkList) {
		this.strDisByRmkList = strDisByRmkList;
	}

	public WebRowSet getStrReconlByRmkList() {
		return strReconlByRmkList;
	}

	public void setStrReconlByRmkList(WebRowSet strReconlByRmkList) {
		this.strReconlByRmkList = strReconlByRmkList;
	}

	
	/**
	 * @return the strOfflineTariffDiscountConfigDetails
	 */
	public String[] getStrOfflineTariffDiscountConfigDetails() {
		return strOfflineTariffDiscountConfigDetails;
	}

	/**
	 * @param strOfflineTariffDiscountConfigDetails
	 *            the strOfflineTariffDiscountConfigDetails to set
	 */
	public void setStrOfflineTariffDiscountConfigDetails(
			String[] strOfflineTariffDiscountConfigDetails) {
		this.strOfflineTariffDiscountConfigDetails = strOfflineTariffDiscountConfigDetails;
	}

	public String getStrValmode() {
		return strValmode;
	}

	public void setStrValmode(String strValmode) {
		this.strValmode = strValmode;
	}

	public String getStrGroup() {
		return strGroup;
	}

	public void setStrGroup(String strGroup) {
		this.strGroup = strGroup;
	}

	public String getStrCategoryCode() {
		return strCategoryCode;
	}

	public void setStrCategoryCode(String strCategoryCode) {
		this.strCategoryCode = strCategoryCode;
	}

	public String getStrWardCode() {
		return strWardCode;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}

	public String getStrTariffTempId() {
		return strTariffTempId;
	}

	public void setStrTariffTempId(String strTariffTempId) {
		this.strTariffTempId = strTariffTempId;
	}

	public String[] getStrTempVal() {
		return strTempVal;
	}

	public void setStrTempVal(String[] strTempVal) {
		this.strTempVal = strTempVal;
	}

	public String getStrTariffDetailsValue() {
		return strTariffDetailsValue;
	}

	public void setStrTariffDetailsValue(String strTariffDetailsValue) {
		this.strTariffDetailsValue = strTariffDetailsValue;
	}


	public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}

	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}

	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}

	public String getStrTransactionType() {
		return strTransactionType;
	}

	public void setStrTransactionType(String strTransactionType) {
		this.strTransactionType = strTransactionType;
	}

	public String getStrBserviceId() {
		return strBserviceId;
	}

	public void setStrBserviceId(String strBserviceId) {
		this.strBserviceId = strBserviceId;
	}

	public String getStrPatAccountNo() {
		return strPatAccountNo;
	}

	public void setStrPatAccountNo(String strPatAccountNo) {
		this.strPatAccountNo = strPatAccountNo;
	}

	public String getStrRequestNo() {
		return strRequestNo;
	}

	public void setStrRequestNo(String strRequestNo) {
		this.strRequestNo = strRequestNo;
	}

	public String getStrReceiptNo() {
		return strReceiptNo;
	}

	public void setStrReceiptNo(String strReceiptNo) {
		this.strReceiptNo = strReceiptNo;
	}

	public String getStrIsBill() {
		return strIsBill;
	}

	public void setStrIsBill(String strIsBill) {
		this.strIsBill = strIsBill;
	}

	public String getStrIsOnline() {
		return strIsOnline;
	}

	public void setStrIsOnline(String strIsOnline) {
		this.strIsOnline = strIsOnline;
	}

	public String getStrAdmissionNo() {
		return strAdmissionNo;
	}

	public void setStrAdmissionNo(String strAdmissionNo) {
		this.strAdmissionNo = strAdmissionNo;
	}

	public String getStrEpisodeCode() {
		return strEpisodeCode;
	}

	public void setStrEpisodeCode(String strEpisodeCode) {
		this.strEpisodeCode = strEpisodeCode;
	}

	public String getStrCatCode() {
		return strCatCode;
	}

	public void setStrCatCode(String strCatCode) {
		this.strCatCode = strCatCode;
	}

	public String getStrDepartmentCode() {
		return strDepartmentCode;
	}

	public void setStrDepartmentCode(String strDepartmentCode) {
		this.strDepartmentCode = strDepartmentCode;
	}

	public String getStrIpdChargeTypeId() {
		return strIpdChargeTypeId;
	}

	public void setStrIpdChargeTypeId(String strIpdChargeTypeId) {
		this.strIpdChargeTypeId = strIpdChargeTypeId;
	}

	public String getStrServiceId() {
		return strServiceId;
	}

	public void setStrServiceId(String strServiceId) {
		this.strServiceId = strServiceId;
	}

	public String[] getStrTariffId() {
		return strTariffId;
	}

	public void setStrTariffId(String[] strTariffId) {
		this.strTariffId = strTariffId;
	}

	public String[] getStrQtyUnitId() {
		return strQtyUnitId;
	}

	public void setStrQtyUnitId(String[] strQtyUnitId) {
		this.strQtyUnitId = strQtyUnitId;
	}

	public String[] getStrTariffRate() {
		return strTariffRate;
	}

	public void setStrTariffRate(String[] strTariffRate) {
		this.strTariffRate = strTariffRate;
	}

	
	public String[] getStrGroupId() {
		return strGroupId;
	}

	public void setStrGroupId(String[] strGroupId) {
		this.strGroupId = strGroupId;
	}

	public String[] getStrActualTariffRate() {
		return strActualTariffRate;
	}

	public void setStrActualTariffRate(String[] strActualTariffRate) {
		this.strActualTariffRate = strActualTariffRate;
	}

	public String[] getStrServiceTax() {
		return strServiceTax;
	}

	public void setStrServiceTax(String[] strServiceTax) {
		this.strServiceTax = strServiceTax;
	}

	public String[] getStrDiscountType() {
		return strDiscountType;
	}

	public void setStrDiscountType(String[] strDiscountType) {
		this.strDiscountType = strDiscountType;
	}

	public String[] getStrDiscountUnit() {
		return strDiscountUnit;
	}

	public void setStrDiscountUnit(String[] strDiscountUnit) {
		this.strDiscountUnit = strDiscountUnit;
	}

	public String[] getStrGTariffId() {
		return strGTariffId;
	}

	public void setStrGTariffId(String[] strGTariffId) {
		this.strGTariffId = strGTariffId;
	}

	public String[] getStrDiscountApprovalId() {
		return strDiscountApprovalId;
	}

	public void setStrDiscountApprovalId(String[] strDiscountApprovalId) {
		this.strDiscountApprovalId = strDiscountApprovalId;
	}

	public String[] getStrBaseUnitVal() {
		return strBaseUnitVal;
	}

	public void setStrBaseUnitVal(String[] strBaseUnitVal) {
		this.strBaseUnitVal = strBaseUnitVal;
	}

	public String[] getStrIsPackage() {
		return strIsPackage;
	}

	public void setStrIsPackage(String[] strIsPackage) {
		this.strIsPackage = strIsPackage;
	}

	public String[] getStrNetAmount() {
		return strNetAmount;
	}

	public void setStrNetAmount(String[] strNetAmount) {
		this.strNetAmount = strNetAmount;
	}

	public String[] getStrPenalty() {
		return strPenalty;
	}

	public void setStrPenalty(String[] strPenalty) {
		this.strPenalty = strPenalty;
	}

	public String[] getStrSServiceId() {
		return strSServiceId;
	}

	public void setStrSServiceId(String[] strSServiceId) {
		this.strSServiceId = strSServiceId;
	}

	public String[] getStrOfflineTariffNetAmount() {
		return strOfflineTariffNetAmount;
	}

	public void setStrOfflineTariffNetAmount(String[] strOfflineTariffNetAmount) {
		this.strOfflineTariffNetAmount = strOfflineTariffNetAmount;
	}

	public String[] getStrBalanceQty() {
		return strBalanceQty;
	}

	public void setStrBalanceQty(String[] strBalanceQty) {
		this.strBalanceQty = strBalanceQty;
	}

	public String[] getStrRemainingQty() {
		return strRemainingQty;
	}

	public void setStrRemainingQty(String[] strRemainingQty) {
		this.strRemainingQty = strRemainingQty;
	}

	public String[] getStrRateUnit() {
		return strRateUnit;
	}

	public void setStrRateUnit(String[] strRateUnit) {
		this.strRateUnit = strRateUnit;
	}

	public String[] getStrBillTariffVal() {
		return strBillTariffVal;
	}

	public void setStrBillTariffVal(String[] strBillTariffVal) {
		this.strBillTariffVal = strBillTariffVal;
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

	public String[] getStrBillTariffRefundAmount() {
		return strBillTariffRefundAmount;
	}

	public void setStrBillTariffRefundAmount(String[] strBillTariffRefundAmount) {
		this.strBillTariffRefundAmount = strBillTariffRefundAmount;
	}

	public String getStrReconcileTariffAmount() {
		return strReconcileTariffAmount;
	}

	public void setStrReconcileTariffAmount(String strReconcileTariffAmount) {
		this.strReconcileTariffAmount = strReconcileTariffAmount;
	}

	public String[] getStrTariffReceiptNo() {
		return strTariffReceiptNo;
	}

	public void setStrTariffReceiptNo(String[] strTariffReceiptNo) {
		this.strTariffReceiptNo = strTariffReceiptNo;
	}

	public String getStrGblRequestNo() {
		return strGblRequestNo;
	}

	public void setStrGblRequestNo(String strGblRequestNo) {
		this.strGblRequestNo = strGblRequestNo;
	}

	public String getStrGblServiceId() {
		return strGblServiceId;
	}

	public void setStrGblServiceId(String strGblServiceId) {
		this.strGblServiceId = strGblServiceId;
	}

	public String[] getStrBillTariffRefundDiscountAmount() {
		return strBillTariffRefundDiscountAmount;
	}

	public void setStrBillTariffRefundDiscountAmount(
			String[] strBillTariffRefundDiscountAmount) {
		this.strBillTariffRefundDiscountAmount = strBillTariffRefundDiscountAmount;
	}

	public String[] getStrBillTariffRefundServiceTaxAmount() {
		return strBillTariffRefundServiceTaxAmount;
	}

	public void setStrBillTariffRefundServiceTaxAmount(
			String[] strBillTariffRefundServiceTaxAmount) {
		this.strBillTariffRefundServiceTaxAmount = strBillTariffRefundServiceTaxAmount;
	}

	/**
	 * @return the strAccReqNo
	 */
	public String[] getStrAccReqNo() {
		return strAccReqNo;
	}

	/**
	 * @param strAccReqNo the strAccReqNo to set
	 */
	public void setStrAccReqNo(String[] strAccReqNo) {
		this.strAccReqNo = strAccReqNo;
	}

	/**
	 * @return the strTariffCode
	 */
	public String getStrTariffCode() {
		return strTariffCode;
	}

	/**
	 * @param strTariffCode the strTariffCode to set
	 */
	public void setStrTariffCode(String strTariffCode) {
		this.strTariffCode = strTariffCode;
	}

}
