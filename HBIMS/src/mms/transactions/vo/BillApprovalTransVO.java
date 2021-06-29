package mms.transactions.vo;

import javax.sql.rowset.WebRowSet;

import hisglobal.utility.TransferObject;

public class BillApprovalTransVO implements TransferObject {

	private static final long serialVersionUID = 02L;

	private Boolean BExistStatus = false;
	private String strMsgString = "";
	private String strMsgType = "";
	
	private String strHospitalCode = "";
	private String strStoreId = "";
	private String strStoreName = "";
	private String strStatus = "";
	private String strFileName="";
	private String strPONo = "";
	private String strPONoCmb = "";
	private WebRowSet strPONoCmbWS = null;
	private String strPODate = "";
	private String strPOType = "";
	private String strPONetAmount = "";
	private String strAgentName = "";
	private String strCAName = "";
	private String strMaturityDate = "";
	private String strAgendaNo = "";
	private String strAgendaDate = "";
	private String strSupplierName = "";
	private String strSelScheduleNos = "";
	private String strScheduleNo = "";
	private String strReceiptNo = "";
	private String strReceiptDate = "";
	private String strItemName = "";
	private String strBrandName = "";
	private String strCurrencyId = "";
	private String strCurrencyName = "";
	private String strCurrencyValue = "";
	private String strRequiredQty = "0";
	private String strSuppliedQty = "0";
	private String strCurrentDate = "";
	private String strCurrentDateTime = "";
	private String strNetItemCost = "0";
	private String strFinancialStartYear = "";
	private String strFinancialEndYear = "";
	private String strNetPenalty = "0";
	private String []strScheduleNoArrH = null;
	private String []strScheduleCostArrH = {"0"};
	private String strAdvanceTaken = "0";
	private String strApprovedItemCost = "0";
	private String strApprovedPenalty = "0";
	private String strAdvanceAdjusted = "0";
	private String strBalanceItemCost = "0";
	private String strBalancePenalty = "0";
	private String strBalanceAdvance = "0";
	private String strTax = "0";
	private String strItemCost = "0";
	private String strTotalItemCost = "0";
	private String strOverallPOTax = "0";
	private String strTotalTaxAmount = "0";
	private String strBillAmount = "0";
	private String strMisclaneousAmount = "0";
	private String strSanctionedAmount = "0";
	private String strTaxUnit = "";
	private String strItemCategoryNoH = "";
	private String strItemCategoryNameH = "";
	private String strPOTypeId = "";
	private String strSupplierId = "";
	private String strPOStoreId = "";
	private String strPOStoreName = "";
	private String strWaiveOffAmt = "";
	private String strWaiveOffApprovedBy = "";
	private String strWaiveOffApprovedDate = "";
	private String strFileExt="";
	private WebRowSet strWaiveOffApprovedByWS = null;
	private String strSearchListPODtlFromDate = "";
	private String strSearchListPODtlToDate = "";
	private String strSearchListPODetails = "";
	private WebRowSet strPODetailsWs = null;
	private WebRowSet strPOSearchDetailsWs = null;
	private WebRowSet wsPeneltyDtl=null;
	private String strBillNo = "";
	private String strBillDate = "";
	private String strBillType = "";
	private String strRemarks = "";
	private String strSeatId = "";
	private String strDisplayPODetails = "";
	private WebRowSet displayPODetails = null;
	private WebRowSet strScheduleDtlsWS = null;
	private WebRowSet strScheduleItemDtlsWS = null;
	private String strReceiptDetails = "";
	private WebRowSet receiptDetailsWs = null;
	private WebRowSet strOtherDetailsWs = null;
	private String strReceiptItemDetails = "";
	private WebRowSet receiptItemDetailsWs = null;
	
	private WebRowSet itemCostWs = null;
	
	public Boolean getBExistStatus() {
		return BExistStatus;
	}
	public void setBExistStatus(Boolean existStatus) {
		BExistStatus = existStatus;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrStoreId() {
		return strStoreId;
	}
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getStrPONo() {
		return strPONo;
	}
	public void setStrPONo(String strPONo) {
		this.strPONo = strPONo;
	}
	public String getStrPODate() {
		return strPODate;
	}
	public void setStrPODate(String strPODate) {
		this.strPODate = strPODate;
	}
	public String getStrPOType() {
		return strPOType;
	}
	public void setStrPOType(String strPOType) {
		this.strPOType = strPOType;
	}
	public String getStrMaturityDate() {
		return strMaturityDate;
	}
	public void setStrMaturityDate(String strMaturityDate) {
		this.strMaturityDate = strMaturityDate;
	}
	public String getStrAgendaNo() {
		return strAgendaNo;
	}
	public void setStrAgendaNo(String strAgendaNo) {
		this.strAgendaNo = strAgendaNo;
	}
	public String getStrAgendaDate() {
		return strAgendaDate;
	}
	public void setStrAgendaDate(String strAgendaDate) {
		this.strAgendaDate = strAgendaDate;
	}
	public String getStrSupplierName() {
		return strSupplierName;
	}
	public void setStrSupplierName(String strSupplierName) {
		this.strSupplierName = strSupplierName;
	}
	public String getStrReceiptNo() {
		return strReceiptNo;
	}
	public void setStrReceiptNo(String strReceiptNo) {
		this.strReceiptNo = strReceiptNo;
	}
	public String getStrReceiptDate() {
		return strReceiptDate;
	}
	public void setStrReceiptDate(String strReceiptDate) {
		this.strReceiptDate = strReceiptDate;
	}
	public String getStrItemName() {
		return strItemName;
	}
	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}
	public String getStrBrandName() {
		return strBrandName;
	}
	public void setStrBrandName(String strBrandName) {
		this.strBrandName = strBrandName;
	}
	public String getStrRequiredQty() {
		return strRequiredQty;
	}
	public void setStrRequiredQty(String strRequiredQty) {
		this.strRequiredQty = strRequiredQty;
	}
	public String getStrSuppliedQty() {
		return strSuppliedQty;
	}
	public void setStrSuppliedQty(String strSuppliedQty) {
		this.strSuppliedQty = strSuppliedQty;
	}
	public String getStrNetItemCost() {
		return strNetItemCost;
	}
	public void setStrNetItemCost(String strNetItemCost) {
		this.strNetItemCost = strNetItemCost;
	}
	public String getStrNetPenalty() {
		return strNetPenalty;
	}
	public void setStrNetPenalty(String strNetPenalty) {
		this.strNetPenalty = strNetPenalty;
	}
	public String getStrAdvanceTaken() {
		return strAdvanceTaken;
	}
	public void setStrAdvanceTaken(String strAdvanceTaken) {
		this.strAdvanceTaken = strAdvanceTaken;
	}
	public String getStrApprovedItemCost() {
		return strApprovedItemCost;
	}
	public void setStrApprovedItemCost(String strApprovedItemCost) {
		this.strApprovedItemCost = strApprovedItemCost;
	}
	public String getStrApprovedPenalty() {
		return strApprovedPenalty;
	}
	public void setStrApprovedPenalty(String strApprovedPenalty) {
		this.strApprovedPenalty = strApprovedPenalty;
	}
	public String getStrAdvanceAdjusted() {
		return strAdvanceAdjusted;
	}
	public void setStrAdvanceAdjusted(String strAdvanceAdjusted) {
		this.strAdvanceAdjusted = strAdvanceAdjusted;
	}
	public String getStrBalanceItemCost() {
		return strBalanceItemCost;
	}
	public void setStrBalanceItemCost(String strBalanceItemCost) {
		this.strBalanceItemCost = strBalanceItemCost;
	}

	public String getStrBalanceAdvance() {
		return strBalanceAdvance;
	}
	public void setStrBalanceAdvance(String strBalanceAdvance) {
		this.strBalanceAdvance = strBalanceAdvance;
	}
	public String getStrBillNo() {
		return strBillNo;
	}
	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}
	public String getStrBillDate() {
		return strBillDate;
	}
	public void setStrBillDate(String strBillDate) {
		this.strBillDate = strBillDate;
	}
	public String getStrBillType() {
		return strBillType;
	}
	public void setStrBillType(String strBillType) {
		this.strBillType = strBillType;
	}
	public String getStrTotalItemCost() {
		return strTotalItemCost;
	}
	public void setStrTotalItemCost(String strTotalItemCost) {
		this.strTotalItemCost = strTotalItemCost;
	}
	
	public String getStrTax() {
		return strTax;
	}
	public void setStrTax(String strTax) {
		this.strTax = strTax;
	}
	public String getStrTaxUnit() {
		return strTaxUnit;
	}
	public void setStrTaxUnit(String strTaxUnit) {
		this.strTaxUnit = strTaxUnit;
	}
	public String getStrTotalTaxAmount() {
		return strTotalTaxAmount;
	}
	public void setStrTotalTaxAmount(String strTotalTaxAmount) {
		this.strTotalTaxAmount = strTotalTaxAmount;
	}
	public String getStrBillAmount() {
		return strBillAmount;
	}
	public void setStrBillAmount(String strBillAmount) {
		this.strBillAmount = strBillAmount;
	}
	public String getStrMisclaneousAmount() {
		return strMisclaneousAmount;
	}
	public void setStrMisclaneousAmount(String strMisclaneousAmount) {
		this.strMisclaneousAmount = strMisclaneousAmount;
	}
	public String getStrSanctionedAmount() {
		return strSanctionedAmount;
	}
	public void setStrSanctionedAmount(String strSanctionedAmount) {
		this.strSanctionedAmount = strSanctionedAmount;
	}
	
	public String getStrReceiptDetails() {
		return strReceiptDetails;
	}
	public void setStrReceiptDetails(String strReceiptDetails) {
		this.strReceiptDetails = strReceiptDetails;
	}
	public String getStrItemCost() {
		return strItemCost;
	}
	public void setStrItemCost(String strItemCost) {
		this.strItemCost = strItemCost;
	}
	public WebRowSet getItemCostWs() {
		return itemCostWs;
	}
	public void setItemCostWs(WebRowSet itemCostWs) {
		this.itemCostWs = itemCostWs;
	}
	public WebRowSet getReceiptDetailsWs() {
		return receiptDetailsWs;
	}
	public void setReceiptDetailsWs(WebRowSet receiptDetailsWs) {
		this.receiptDetailsWs = receiptDetailsWs;
	}
	public String getStrDisplayPODetails() {
		return strDisplayPODetails;
	}
	public void setStrDisplayPODetails(String strDisplayPODetails) {
		this.strDisplayPODetails = strDisplayPODetails;
	}
	public WebRowSet getDisplayPODetails() {
		return displayPODetails;
	}
	public void setDisplayPODetails(WebRowSet displayPODetails) {
		this.displayPODetails = displayPODetails;
	}
	public String getStrReceiptItemDetails() {
		return strReceiptItemDetails;
	}
	public void setStrReceiptItemDetails(String strReceiptItemDetails) {
		this.strReceiptItemDetails = strReceiptItemDetails;
	}
	public WebRowSet getReceiptItemDetailsWs() {
		return receiptItemDetailsWs;
	}
	public void setReceiptItemDetailsWs(WebRowSet receiptItemDetailsWs) {
		this.receiptItemDetailsWs = receiptItemDetailsWs;
	}
	public String getStrSelScheduleNos() {
		return strSelScheduleNos;
	}
	public void setStrSelScheduleNos(String strSelScheduleNos) {
		this.strSelScheduleNos = strSelScheduleNos;
	}
	public WebRowSet getStrScheduleItemDtlsWS() {
		return strScheduleItemDtlsWS;
	}
	public void setStrScheduleItemDtlsWS(WebRowSet strScheduleItemDtlsWS) {
		this.strScheduleItemDtlsWS = strScheduleItemDtlsWS;
	}
	public WebRowSet getStrOtherDetailsWs() {
		return strOtherDetailsWs;
	}
	public void setStrOtherDetailsWs(WebRowSet strOtherDetailsWs) {
		this.strOtherDetailsWs = strOtherDetailsWs;
	}
	public WebRowSet getStrScheduleDtlsWS() {
		return strScheduleDtlsWS;
	}
	public void setStrScheduleDtlsWS(WebRowSet strScheduleDtlsWS) {
		this.strScheduleDtlsWS = strScheduleDtlsWS;
	}
	public String getStrOverallPOTax() {
		return strOverallPOTax;
	}
	public void setStrOverallPOTax(String strOverallPOTax) {
		this.strOverallPOTax = strOverallPOTax;
	}
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getStrItemCategoryNoH() {
		return strItemCategoryNoH;
	}
	public String getStrItemCategoryNameH() {
		return strItemCategoryNameH;
	}
	public String getStrPOTypeId() {
		return strPOTypeId;
	}
	public String getStrSupplierId() {
		return strSupplierId;
	}
	public String getStrPOStoreId() {
		return strPOStoreId;
	}
	public String getStrPOStoreName() {
		return strPOStoreName;
	}
	public String getStrSearchListPODtlFromDate() {
		return strSearchListPODtlFromDate;
	}
	public String getStrSearchListPODtlToDate() {
		return strSearchListPODtlToDate;
	}
	public String getStrSearchListPODetails() {
		return strSearchListPODetails;
	}
	public WebRowSet getStrPODetailsWs() {
		return strPODetailsWs;
	}
	public void setStrItemCategoryNoH(String strItemCategoryNoH) {
		this.strItemCategoryNoH = strItemCategoryNoH;
	}
	public void setStrItemCategoryNameH(String strItemCategoryNameH) {
		this.strItemCategoryNameH = strItemCategoryNameH;
	}
	public void setStrPOTypeId(String strPOTypeId) {
		this.strPOTypeId = strPOTypeId;
	}
	public void setStrSupplierId(String strSupplierId) {
		this.strSupplierId = strSupplierId;
	}
	public void setStrPOStoreId(String strPOStoreId) {
		this.strPOStoreId = strPOStoreId;
	}
	public void setStrPOStoreName(String strPOStoreName) {
		this.strPOStoreName = strPOStoreName;
	}
	public void setStrSearchListPODtlFromDate(String strSearchListPODtlFromDate) {
		this.strSearchListPODtlFromDate = strSearchListPODtlFromDate;
	}
	public void setStrSearchListPODtlToDate(String strSearchListPODtlToDate) {
		this.strSearchListPODtlToDate = strSearchListPODtlToDate;
	}
	public void setStrSearchListPODetails(String strSearchListPODetails) {
		this.strSearchListPODetails = strSearchListPODetails;
	}
	public void setStrPODetailsWs(WebRowSet strPODetailsWs) {
		this.strPODetailsWs = strPODetailsWs;
	}
	public String getStrBalancePenalty() {
		return strBalancePenalty;
	}
	public void setStrBalancePenalty(String strBalancePenalty) {
		this.strBalancePenalty = strBalancePenalty;
	}
	public String getStrCurrencyId() {
		return strCurrencyId;
	}
	public String getStrCurrencyName() {
		return strCurrencyName;
	}
	public String getStrCurrencyValue() {
		return strCurrencyValue;
	}
	public void setStrCurrencyId(String strCurrencyId) {
		this.strCurrencyId = strCurrencyId;
	}
	public void setStrCurrencyName(String strCurrencyName) {
		this.strCurrencyName = strCurrencyName;
	}
	public void setStrCurrencyValue(String strCurrencyValue) {
		this.strCurrencyValue = strCurrencyValue;
	}
	public WebRowSet getStrPOSearchDetailsWs() {
		return strPOSearchDetailsWs;
	}
	public void setStrPOSearchDetailsWs(WebRowSet strPOSearchDetailsWs) {
		this.strPOSearchDetailsWs = strPOSearchDetailsWs;
	}
	public String getStrScheduleNo() {
		return strScheduleNo;
	}
	public void setStrScheduleNo(String strScheduleNo) {
		this.strScheduleNo = strScheduleNo;
	}
	public String[] getStrScheduleNoArrH() {
		return strScheduleNoArrH;
	}
	public String[] getStrScheduleCostArrH() {
		return strScheduleCostArrH;
	}
	public void setStrScheduleNoArrH(String[] strScheduleNoArrH) {
		this.strScheduleNoArrH = strScheduleNoArrH;
	}
	public void setStrScheduleCostArrH(String[] strScheduleCostArrH) {
		this.strScheduleCostArrH = strScheduleCostArrH;
	}
	public String getStrFinancialStartYear() {
		return strFinancialStartYear;
	}
	public String getStrFinancialEndYear() {
		return strFinancialEndYear;
	}
	public void setStrFinancialStartYear(String strFinancialStartYear) {
		this.strFinancialStartYear = strFinancialStartYear;
	}
	public void setStrFinancialEndYear(String strFinancialEndYear) {
		this.strFinancialEndYear = strFinancialEndYear;
	}
	public String getStrWaiveOffAmt() {
		return strWaiveOffAmt;
	}
	public String getStrWaiveOffApprovedBy() {
		return strWaiveOffApprovedBy;
	}
	public void setStrWaiveOffAmt(String strWaiveOffAmt) {
		this.strWaiveOffAmt = strWaiveOffAmt;
	}
	public void setStrWaiveOffApprovedBy(String strWaiveOffApprovedBy) {
		this.strWaiveOffApprovedBy = strWaiveOffApprovedBy;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrWaiveOffApprovedDate() {
		return strWaiveOffApprovedDate;
	}
	public WebRowSet getStrWaiveOffApprovedByWS() {
		return strWaiveOffApprovedByWS;
	}
	public void setStrWaiveOffApprovedDate(String strWaiveOffApprovedDate) {
		this.strWaiveOffApprovedDate = strWaiveOffApprovedDate;
	}
	public void setStrWaiveOffApprovedByWS(WebRowSet strWaiveOffApprovedByWS) {
		this.strWaiveOffApprovedByWS = strWaiveOffApprovedByWS;
	}
	public String getStrPONoCmb() {
		return strPONoCmb;
	}
	public void setStrPONoCmb(String strPONoCmb) {
		this.strPONoCmb = strPONoCmb;
	}
	public WebRowSet getStrPONoCmbWS() {
		return strPONoCmbWS;
	}
	public void setStrPONoCmbWS(WebRowSet strPONoCmbWS) {
		this.strPONoCmbWS = strPONoCmbWS;
	}
	public String getStrPONetAmount() {
		return strPONetAmount;
	}
	public String getStrAgentName() {
		return strAgentName;
	}
	public String getStrCAName() {
		return strCAName;
	}
	public void setStrPONetAmount(String strPONetAmount) {
		this.strPONetAmount = strPONetAmount;
	}
	public void setStrAgentName(String strAgentName) {
		this.strAgentName = strAgentName;
	}
	public void setStrCAName(String strCAName) {
		this.strCAName = strCAName;
	}
	public WebRowSet getWsPeneltyDtl() {
		return wsPeneltyDtl;
	}
	public void setWsPeneltyDtl(WebRowSet wsPeneltyDtl) {
		this.wsPeneltyDtl = wsPeneltyDtl;
	}
	/**
	 * @return the strFileExt
	 */
	public String getStrFileExt() {
		return strFileExt;
	}
	/**
	 * @param strFileExt the strFileExt to set
	 */
	public void setStrFileExt(String strFileExt) {
		this.strFileExt = strFileExt;
	}
	/**
	 * @return the strCurrentDateTime
	 */
	public String getStrCurrentDateTime() {
		return strCurrentDateTime;
	}
	/**
	 * @param strCurrentDateTime the strCurrentDateTime to set
	 */
	public void setStrCurrentDateTime(String strCurrentDateTime) {
		this.strCurrentDateTime = strCurrentDateTime;
	}
	/**
	 * @return the strFileName
	 */
	public String getStrFileName() {
		return strFileName;
	}
	/**
	 * @param strFileName the strFileName to set
	 */
	public void setStrFileName(String strFileName) {
		this.strFileName = strFileName;
	}

}
