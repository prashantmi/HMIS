package mms.transactions.controller.fb;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionutil.GenericFormBean;

public class TransferDemandReqTransFB extends GenericFormBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private String chk = "";
	
	
	private String strErrMsg = "";
	private String strWarningMsg = "";
	private String strNormalMsg = "";
	
	private String strComboVal = "";
	
	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strReqDate="";
	
	private String strRemarks = "";
	private String strPath = "";
	private String strGroupNameCombo ="";
	private String strDemandedQty ="";
	private String strApprovedById="";
	private String strApprovedBy ="";
	private String strReqStatus="";
	private String strChk ="";
	private String strModifyChk ="";
	private String strAcknowledgeDetails = "";
	private String strItemDetails = "";
	private String strAckDtls = "";
	private String strAckStatus="0";
	private String strTransNo="0";
	private String strStoreId="";
	private String cnt_page="";
	private String strCtDate="";
	private String strRequestNo="";
	private String strTmpStoreId="";
	private String strEffectiveFrom = "";
	private String strManufactureId = "";
	private String strManufactureName = "";
	private String strManufactureCombo = "";
	
	private String strBillNo="";
	private String strBillDate="";

	private String strSuppliedBy = "0";
	private String strSuppliedByValues = "";
	private String strApprovedByCombo = "";
	private String strApprovedDate="";

	private String strUnitRateID = "";
	private String strUnitRateName = "";
	private String strUnitRateCombo = "";

	private String strUnitSaleID = "";
	private String strUnitSaleName = "";
	private String strUnitSaleCombo = "";
	private String strStoreName = "";
	private String strGroupId = "";
	private String strGroupName = "";
	private String strItemNameCombo = "";

	private String strItemName = "";
	private String strItemId = "";

	private String strBatchNo ;
	private String strExpiryDate = "";
	private String strManufactureDate = "";
	private String strRate = "";
	private String strSalePrice = "";

	private String strPoNo = "";
	private String strPoDate = "";
	private String strReceivedDate = "";

	
	private String strCurrencyCode = "0";
	private String strCurrencyCodeValues = "";
	private String strCurrencyValue = "1";
	private String strDefaultCurrencyCode = "0";
	
	private String strIssueRateConfigFlg;
	
	private String strInHandQuantityUnitValues = "";
	private String strRateUnitValues = "";
	private String strSalesRateUnitValues = "";
	
	private String strStockStatus = "";
	private String strOldStockStatus = "";
	
	private String strStockStatusValues = "";
	private String strUnitId = "0";
	private String strUnitName = "";
	private String strModuleId = "0";
	private String strUnitNameCombo = "";

	private String strUnitCombo = "";
	private String strInHandQuantity = "";
	private String strInHandQuantityUnitID = "";

	private String strItemBrandCombo = "";
	private String strItemBrandName = "";
	private String strItemBrandId = "";
	private String strItemCategoryNo = "";
	private String strSubGroupCombo = "";

	private String strSubGroupCode = "";
	private String strSubGroupName = "";
	private String strUnitIdSale = "0";
	private String strUnitNameSale = "";
	private String strUnitNameComboSale = "";
	private WebRowSet WbTransferOrderDetail = null;
	
	/*View Option
	 * */
	private String strRequestNoView="";
	private String strRequestDateView="";
	private String strItemNameView="";
	private String strGroupNameView="";
	private String strSubGroupNameView="";
	private String strStoreNameView="";
	private String strUnitNameView="";
	private String strReqQtyView="";
	private String strApprovedByView="";
	private String strApprovedDateView="";
	private String strApprovedStatusView="";
	
	private String strReqQtyWithUnitView="";
	private String strApprovedQtyWithUnitView="";
	private String strAckQtyWithUnitView="";
	private String strRaisingAvlQtyWithUnitView="";
	private String strAckAvlQtyWithUnitView="";
	private String strRaisingRemarksView="";
	private String strOrderBySeatIdView="";
	private String strOrderByDateView="";
	private String strOrderRemarksView="";
	private String strStatusView="";
	
	private String strTransferOrderDetails="";
	private String strApprovalRemarksView;
	
	//Hidden Fields
	
	 private String[] strHiddenValue  = null;
	 private String strHidVal = "";
	 
	
	public String getStrHidVal() {
		return strHidVal;
	}
	public void setStrHidVal(String strHidVal) {
		this.strHidVal = strHidVal;
	}
	public String getStrAcknowledgeDetails() {
		return strAcknowledgeDetails;
	}
	public void setStrAcknowledgeDetails(String strAcknowledgeDetails) {
		this.strAcknowledgeDetails = strAcknowledgeDetails;
	}
	public String getStrItemDetails() {
		return strItemDetails;
	}
	public void setStrItemDetails(String strItemDetails) {
		this.strItemDetails = strItemDetails;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrPath() {
		return strPath;
	}
	public void setStrPath(String strPath) {
		this.strPath = strPath;
	}
	public String getStrAckDtls() {
		return strAckDtls;
	}
	public void setStrAckDtls(String strAckDtls) {
		this.strAckDtls = strAckDtls;
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
	
	public String[] getStrHiddenValue() {
		return strHiddenValue;
	}
	public void setStrHiddenValue(String[] strHiddenValue) {
		this.strHiddenValue = strHiddenValue;
	}
	public String getStrComboVal() {
		return strComboVal;
	}
	public void setStrComboVal(String strComboVal) {
		this.strComboVal = strComboVal;
	}
	/**
	 * @return the strAckStatus
	 */
	public String getStrAckStatus() {
		return strAckStatus;
	}
	/**
	 * @param strAckStatus the strAckStatus to set
	 */
	public void setStrAckStatus(String strAckStatus) {
		this.strAckStatus = strAckStatus;
	}
	/**
	 * @return the strTransNo
	 */
	public String getStrTransNo() {
		return strTransNo;
	}
	/**
	 * @param strTransNo the strTransNo to set
	 */
	public void setStrTransNo(String strTransNo) {
		this.strTransNo = strTransNo;
	}
	/**
	 * @return the strStoreId
	 */
	public String getStrStoreId() {
		return strStoreId;
	}
	/**
	 * @param strStoreId the strStoreId to set
	 */
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}
	/**
	 * @return the cnt_page
	 */
	public String getCnt_page() {
		return cnt_page;
	}
	/**
	 * @param cnt_page the cnt_page to set
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrManufactureId() {
		return strManufactureId;
	}
	public void setStrManufactureId(String strManufactureId) {
		this.strManufactureId = strManufactureId;
	}
	public String getStrManufactureName() {
		return strManufactureName;
	}
	public void setStrManufactureName(String strManufactureName) {
		this.strManufactureName = strManufactureName;
	}
	public String getStrManufactureCombo() {
		return strManufactureCombo;
	}
	public void setStrManufactureCombo(String strManufactureCombo) {
		this.strManufactureCombo = strManufactureCombo;
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
	public String getStrSuppliedBy() {
		return strSuppliedBy;
	}
	public void setStrSuppliedBy(String strSuppliedBy) {
		this.strSuppliedBy = strSuppliedBy;
	}
	public String getStrSuppliedByValues() {
		return strSuppliedByValues;
	}
	public void setStrSuppliedByValues(String strSuppliedByValues) {
		this.strSuppliedByValues = strSuppliedByValues;
	}
	public String getStrUnitRateID() {
		return strUnitRateID;
	}
	public void setStrUnitRateID(String strUnitRateID) {
		this.strUnitRateID = strUnitRateID;
	}
	public String getStrUnitRateName() {
		return strUnitRateName;
	}
	public void setStrUnitRateName(String strUnitRateName) {
		this.strUnitRateName = strUnitRateName;
	}
	public String getStrUnitRateCombo() {
		return strUnitRateCombo;
	}
	public void setStrUnitRateCombo(String strUnitRateCombo) {
		this.strUnitRateCombo = strUnitRateCombo;
	}
	public String getStrUnitSaleID() {
		return strUnitSaleID;
	}
	public void setStrUnitSaleID(String strUnitSaleID) {
		this.strUnitSaleID = strUnitSaleID;
	}
	public String getStrUnitSaleName() {
		return strUnitSaleName;
	}
	public void setStrUnitSaleName(String strUnitSaleName) {
		this.strUnitSaleName = strUnitSaleName;
	}
	public String getStrUnitSaleCombo() {
		return strUnitSaleCombo;
	}
	public void setStrUnitSaleCombo(String strUnitSaleCombo) {
		this.strUnitSaleCombo = strUnitSaleCombo;
	}
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	public String getStrGroupId() {
		return strGroupId;
	}
	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}
	public String getStrGroupName() {
		return strGroupName;
	}
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
	}
	public String getStrItemNameCombo() {
		return strItemNameCombo;
	}
	public void setStrItemNameCombo(String strItemNameCombo) {
		this.strItemNameCombo = strItemNameCombo;
	}
	public String getStrItemName() {
		return strItemName;
	}
	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrBatchNo() {
		return strBatchNo;
	}
	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}
	public String getStrExpiryDate() {
		return strExpiryDate;
	}
	public void setStrExpiryDate(String strExpiryDate) {
		this.strExpiryDate = strExpiryDate;
	}
	public String getStrManufactureDate() {
		return strManufactureDate;
	}
	public void setStrManufactureDate(String strManufactureDate) {
		this.strManufactureDate = strManufactureDate;
	}
	public String getStrRate() {
		return strRate;
	}
	public void setStrRate(String strRate) {
		this.strRate = strRate;
	}
	public String getStrSalePrice() {
		return strSalePrice;
	}
	public void setStrSalePrice(String strSalePrice) {
		this.strSalePrice = strSalePrice;
	}
	public String getStrPoNo() {
		return strPoNo;
	}
	public void setStrPoNo(String strPoNo) {
		this.strPoNo = strPoNo;
	}
	public String getStrPoDate() {
		return strPoDate;
	}
	public void setStrPoDate(String strPoDate) {
		this.strPoDate = strPoDate;
	}
	public String getStrReceivedDate() {
		return strReceivedDate;
	}
	public void setStrReceivedDate(String strReceivedDate) {
		this.strReceivedDate = strReceivedDate;
	}
	public String getStrCurrencyCode() {
		return strCurrencyCode;
	}
	public void setStrCurrencyCode(String strCurrencyCode) {
		this.strCurrencyCode = strCurrencyCode;
	}
	public String getStrCurrencyCodeValues() {
		return strCurrencyCodeValues;
	}
	public void setStrCurrencyCodeValues(String strCurrencyCodeValues) {
		this.strCurrencyCodeValues = strCurrencyCodeValues;
	}
	public String getStrCurrencyValue() {
		return strCurrencyValue;
	}
	public void setStrCurrencyValue(String strCurrencyValue) {
		this.strCurrencyValue = strCurrencyValue;
	}
	public String getStrDefaultCurrencyCode() {
		return strDefaultCurrencyCode;
	}
	public void setStrDefaultCurrencyCode(String strDefaultCurrencyCode) {
		this.strDefaultCurrencyCode = strDefaultCurrencyCode;
	}
	public String getStrIssueRateConfigFlg() {
		return strIssueRateConfigFlg;
	}
	public void setStrIssueRateConfigFlg(String strIssueRateConfigFlg) {
		this.strIssueRateConfigFlg = strIssueRateConfigFlg;
	}
	public String getStrInHandQuantityUnitValues() {
		return strInHandQuantityUnitValues;
	}
	public void setStrInHandQuantityUnitValues(String strInHandQuantityUnitValues) {
		this.strInHandQuantityUnitValues = strInHandQuantityUnitValues;
	}
	public String getStrRateUnitValues() {
		return strRateUnitValues;
	}
	public void setStrRateUnitValues(String strRateUnitValues) {
		this.strRateUnitValues = strRateUnitValues;
	}
	public String getStrSalesRateUnitValues() {
		return strSalesRateUnitValues;
	}
	public void setStrSalesRateUnitValues(String strSalesRateUnitValues) {
		this.strSalesRateUnitValues = strSalesRateUnitValues;
	}
	public String getStrStockStatus() {
		return strStockStatus;
	}
	public void setStrStockStatus(String strStockStatus) {
		this.strStockStatus = strStockStatus;
	}
	public String getStrOldStockStatus() {
		return strOldStockStatus;
	}
	public void setStrOldStockStatus(String strOldStockStatus) {
		this.strOldStockStatus = strOldStockStatus;
	}
	public String getStrStockStatusValues() {
		return strStockStatusValues;
	}
	public void setStrStockStatusValues(String strStockStatusValues) {
		this.strStockStatusValues = strStockStatusValues;
	}
	public String getStrUnitId() {
		return strUnitId;
	}
	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}
	public String getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrUnitNameCombo() {
		return strUnitNameCombo;
	}
	public void setStrUnitNameCombo(String strUnitNameCombo) {
		this.strUnitNameCombo = strUnitNameCombo;
	}
	public String getStrUnitCombo() {
		return strUnitCombo;
	}
	public void setStrUnitCombo(String strUnitCombo) {
		this.strUnitCombo = strUnitCombo;
	}
	public String getStrInHandQuantity() {
		return strInHandQuantity;
	}
	public void setStrInHandQuantity(String strInHandQuantity) {
		this.strInHandQuantity = strInHandQuantity;
	}
	public String getStrInHandQuantityUnitID() {
		return strInHandQuantityUnitID;
	}
	public void setStrInHandQuantityUnitID(String strInHandQuantityUnitID) {
		this.strInHandQuantityUnitID = strInHandQuantityUnitID;
	}
	public String getStrItemBrandCombo() {
		return strItemBrandCombo;
	}
	public void setStrItemBrandCombo(String strItemBrandCombo) {
		this.strItemBrandCombo = strItemBrandCombo;
	}
	public String getStrItemBrandName() {
		return strItemBrandName;
	}
	public void setStrItemBrandName(String strItemBrandName) {
		this.strItemBrandName = strItemBrandName;
	}
	public String getStrItemBrandId() {
		return strItemBrandId;
	}
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}
	public String getStrItemCategoryNo() {
		return strItemCategoryNo;
	}
	public void setStrItemCategoryNo(String strItemCategoryNo) {
		this.strItemCategoryNo = strItemCategoryNo;
	}
	public String getStrSubGroupCombo() {
		return strSubGroupCombo;
	}
	public void setStrSubGroupCombo(String strSubGroupCombo) {
		this.strSubGroupCombo = strSubGroupCombo;
	}
	public String getStrSubGroupCode() {
		return strSubGroupCode;
	}
	public void setStrSubGroupCode(String strSubGroupCode) {
		this.strSubGroupCode = strSubGroupCode;
	}
	public String getStrSubGroupName() {
		return strSubGroupName;
	}
	public void setStrSubGroupName(String strSubGroupName) {
		this.strSubGroupName = strSubGroupName;
	}
	public String getStrUnitIdSale() {
		return strUnitIdSale;
	}
	public void setStrUnitIdSale(String strUnitIdSale) {
		this.strUnitIdSale = strUnitIdSale;
	}
	public String getStrUnitNameSale() {
		return strUnitNameSale;
	}
	public void setStrUnitNameSale(String strUnitNameSale) {
		this.strUnitNameSale = strUnitNameSale;
	}
	public String getStrUnitNameComboSale() {
		return strUnitNameComboSale;
	}
	public void setStrUnitNameComboSale(String strUnitNameComboSale) {
		this.strUnitNameComboSale = strUnitNameComboSale;
	}
	public String getStrCtDate() {
		return strCtDate;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String getStrGroupNameCombo() {
		return strGroupNameCombo;
	}
	public void setStrGroupNameCombo(String strGroupNameCombo) {
		this.strGroupNameCombo = strGroupNameCombo;
	}
	public String getStrApprovedByCombo() {
		return strApprovedByCombo;
	}
	public void setStrApprovedByCombo(String strApprovedByCombo) {
		this.strApprovedByCombo = strApprovedByCombo;
	}
	public String getStrDemandedQty() {
		return strDemandedQty;
	}
	public void setStrDemandedQty(String strDemandedQty) {
		this.strDemandedQty = strDemandedQty;
	}
	public String getStrApprovedById() {
		return strApprovedById;
	}
	public void setStrApprovedById(String strApprovedById) {
		this.strApprovedById = strApprovedById;
	}
	public String getStrApprovedBy() {
		return strApprovedBy;
	}
	public void setStrApprovedBy(String strApprovedBy) {
		this.strApprovedBy = strApprovedBy;
	}
	public String getStrApprovedDate() {
		return strApprovedDate;
	}
	public void setStrApprovedDate(String strApprovedDate) {
		this.strApprovedDate = strApprovedDate;
	}
	public String getStrReqDate() {
		return strReqDate;
	}
	public void setStrReqDate(String strReqDate) {
		this.strReqDate = strReqDate;
	}
	public String getStrReqStatus() {
		return strReqStatus;
	}
	public void setStrReqStatus(String strReqStatus) {
		this.strReqStatus = strReqStatus;
	}
	public String getStrChk() {
		return strChk;
	}
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}
	public String getStrRequestNo() {
		return strRequestNo;
	}
	public void setStrRequestNo(String strRequestNo) {
		this.strRequestNo = strRequestNo;
	}
	public String getStrRequestNoView() {
		return strRequestNoView;
	}
	public void setStrRequestNoView(String strRequestNoView) {
		this.strRequestNoView = strRequestNoView;
	}
	public String getStrRequestDateView() {
		return strRequestDateView;
	}
	public void setStrRequestDateView(String strRequestDateView) {
		this.strRequestDateView = strRequestDateView;
	}
	public String getStrItemNameView() {
		return strItemNameView;
	}
	public void setStrItemNameView(String strItemNameView) {
		this.strItemNameView = strItemNameView;
	}
	public String getStrGroupNameView() {
		return strGroupNameView;
	}
	public void setStrGroupNameView(String strGroupNameView) {
		this.strGroupNameView = strGroupNameView;
	}
	public String getStrStoreNameView() {
		return strStoreNameView;
	}
	public void setStrStoreNameView(String strStoreNameView) {
		this.strStoreNameView = strStoreNameView;
	}
	public String getStrUnitNameView() {
		return strUnitNameView;
	}
	public void setStrUnitNameView(String strUnitNameView) {
		this.strUnitNameView = strUnitNameView;
	}
	public String getStrReqQtyView() {
		return strReqQtyView;
	}
	public void setStrReqQtyView(String strReqQtyView) {
		this.strReqQtyView = strReqQtyView;
	}
	public String getStrApprovedByView() {
		return strApprovedByView;
	}
	public void setStrApprovedByView(String strApprovedByView) {
		this.strApprovedByView = strApprovedByView;
	}
	public String getStrApprovedDateView() {
		return strApprovedDateView;
	}
	public void setStrApprovedDateView(String strApprovedDateView) {
		this.strApprovedDateView = strApprovedDateView;
	}
	public String getStrApprovedStatusView() {
		return strApprovedStatusView;
	}
	public void setStrApprovedStatusView(String strApprovedStatusView) {
		this.strApprovedStatusView = strApprovedStatusView;
	}
	public String getStrReqQtyWithUnitView() {
		return strReqQtyWithUnitView;
	}
	public void setStrReqQtyWithUnitView(String strReqQtyWithUnitView) {
		this.strReqQtyWithUnitView = strReqQtyWithUnitView;
	}
	public String getStrApprovedQtyWithUnitView() {
		return strApprovedQtyWithUnitView;
	}
	public void setStrApprovedQtyWithUnitView(String strApprovedQtyWithUnitView) {
		this.strApprovedQtyWithUnitView = strApprovedQtyWithUnitView;
	}
	public String getStrAckQtyWithUnitView() {
		return strAckQtyWithUnitView;
	}
	public void setStrAckQtyWithUnitView(String strAckQtyWithUnitView) {
		this.strAckQtyWithUnitView = strAckQtyWithUnitView;
	}
	public String getStrRaisingAvlQtyWithUnitView() {
		return strRaisingAvlQtyWithUnitView;
	}
	public void setStrRaisingAvlQtyWithUnitView(String strRaisingAvlQtyWithUnitView) {
		this.strRaisingAvlQtyWithUnitView = strRaisingAvlQtyWithUnitView;
	}
	public String getStrAckAvlQtyWithUnitView() {
		return strAckAvlQtyWithUnitView;
	}
	public void setStrAckAvlQtyWithUnitView(String strAckAvlQtyWithUnitView) {
		this.strAckAvlQtyWithUnitView = strAckAvlQtyWithUnitView;
	}
	public String getStrRaisingRemarksView() {
		return strRaisingRemarksView;
	}
	public void setStrRaisingRemarksView(String strRaisingRemarksView) {
		this.strRaisingRemarksView = strRaisingRemarksView;
	}
	public String getStrOrderBySeatIdView() {
		return strOrderBySeatIdView;
	}
	public void setStrOrderBySeatIdView(String strOrderBySeatIdView) {
		this.strOrderBySeatIdView = strOrderBySeatIdView;
	}
	public String getStrOrderByDateView() {
		return strOrderByDateView;
	}
	public void setStrOrderByDateView(String strOrderByDateView) {
		this.strOrderByDateView = strOrderByDateView;
	}
	public String getStrOrderRemarksView() {
		return strOrderRemarksView;
	}
	public void setStrOrderRemarksView(String strOrderRemarksView) {
		this.strOrderRemarksView = strOrderRemarksView;
	}
	public String getStrStatusView() {
		return strStatusView;
	}
	public void setStrStatusView(String strStatusView) {
		this.strStatusView = strStatusView;
	}
	public String getStrModifyChk() {
		return strModifyChk;
	}
	public void setStrModifyChk(String strModifyChk) {
		this.strModifyChk = strModifyChk;
	}
	public String getStrTmpStoreId() {
		return strTmpStoreId;
	}
	public void setStrTmpStoreId(String strTmpStoreId) {
		this.strTmpStoreId = strTmpStoreId;
	}
	public WebRowSet getWbTransferOrderDetail() {
		return WbTransferOrderDetail;
	}
	public void setWbTransferOrderDetail(WebRowSet wbTransferOrderDetail) {
		WbTransferOrderDetail = wbTransferOrderDetail;
	}
	public String getStrTransferOrderDetails() {
		return strTransferOrderDetails;
	}
	public void setStrTransferOrderDetails(String strTransferOrderDetails) {
		this.strTransferOrderDetails = strTransferOrderDetails;
	}
	public String getStrSubGroupNameView() {
		return strSubGroupNameView;
	}
	public void setStrSubGroupNameView(String strSubGroupNameView) {
		this.strSubGroupNameView = strSubGroupNameView;
	}
	public String getStrApprovalRemarksView() {
		return strApprovalRemarksView;
	}
	public void setStrApprovalRemarksView(String strApprovalRemarksView) {
		this.strApprovalRemarksView = strApprovalRemarksView;
	}
	
}

