package mms.reports.controller.fb;

import org.apache.struts.action.ActionForm;

public class StockLedgerForSubStoresRptFB extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String strHospitalCode = "";
	private String strSeatId = "";
	
	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	
	private String strReportId = "";
	private String strUserRemarks = "";
	private String strReportFormat = "";
	private String strIsFooter = "";
	
	private String strItemCatNo = "";
	private String strStoreId = "";
	private String strItemId = "";
	private String strGroupId = "";
	private String strStoreName = "";
	private String strDrugName = "";
	private String strCurrentDate = "";
	private String strFromDate = "";
	private String strToDate = "";
	
	private String strItemValues = "";
	private String strStoreValues = "";
	private String strWhetherBatchWise;
	private String strStockLedgerPopUp;
	
	private String strStockLedgerDetails;
	private String strItemTypeValues;
	
	private String[] strLeftItemIds=null;
	private String strLeftItemList="";
	private String[] strRightItemIds=null;
	private String strRightItemList="";
	
	private String strItemType;
	
	private String strItemBrandId="0";
	
	private String strGroupCombo;
	private String strHtmlCode="";
	
	public String getStrHtmlCode() {
		return strHtmlCode;
	}
	public void setStrHtmlCode(String strHtmlCode) {
		this.strHtmlCode = strHtmlCode;
	}
	public String getStrGroupCombo() {
		return strGroupCombo;
	}
	public void setStrGroupCombo(String strGroupCombo) {
		this.strGroupCombo = strGroupCombo;
	}
	public String getStrStockLedgerDetails() {
		return strStockLedgerDetails;
	}
	public void setStrStockLedgerDetails(String strStockLedgerDetails) {
		this.strStockLedgerDetails = strStockLedgerDetails;
	}
	public String getStrStockLedgerPopUp() {
		return strStockLedgerPopUp;
	}
	public void setStrStockLedgerPopUp(String strStockLedgerPopUp) {
		this.strStockLedgerPopUp = strStockLedgerPopUp;
	}
	/**
	 * @return the strStoreValues
	 */
	public String getStrStoreValues() {
		return strStoreValues;
	}
	/**
	 * @param strStoreValues the strStoreValues to set
	 */
	public void setStrStoreValues(String strStoreValues) {
		this.strStoreValues = strStoreValues;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}
	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}
	/**
	 * @param strErrMsg the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	/**
	 * @param strNormalMsg the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	/**
	 * @return the strWarningMsg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	/**
	 * @param strWarningMsg the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	/**
	 * @return the strReportId
	 */
	public String getStrReportId() {
		return strReportId;
	}
	/**
	 * @param strReportId the strReportId to set
	 */
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	/**
	 * @return the strUserRemarks
	 */
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	/**
	 * @param strUserRemarks the strUserRemarks to set
	 */
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	/**
	 * @return the strReportFormat
	 */
	public String getStrReportFormat() {
		return strReportFormat;
	}
	/**
	 * @param strReportFormat the strReportFormat to set
	 */
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	/**
	 * @return the strIsFooter
	 */
	public String getStrIsFooter() {
		return strIsFooter;
	}
	/**
	 * @param strIsFooter the strIsFooter to set
	 */
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	/**
	 * @return the strItemCatNo
	 */
	public String getStrItemCatNo() {
		return strItemCatNo;
	}
	/**
	 * @param strItemCatNo the strItemCatNo to set
	 */
	public void setStrItemCatNo(String strItemCatNo) {
		this.strItemCatNo = strItemCatNo;
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
	 * @return the strStoreName
	 */
	public String getStrStoreName() {
		return strStoreName;
	}
	/**
	 * @param strStoreName the strStoreName to set
	 */
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	/**
	 * @return the strCurrentDate
	 */
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	/**
	 * @param strCurrentDate the strCurrentDate to set
	 */
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	/**
	 * @return the strFromDate
	 */
	public String getStrFromDate() {
		return strFromDate;
	}
	/**
	 * @param strFromDate the strFromDate to set
	 */
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	/**
	 * @return the strToDate
	 */
	public String getStrToDate() {
		return strToDate;
	}
	/**
	 * @param strToDate the strToDate to set
	 */
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrGroupId() {
		return strGroupId;
	}
	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}
	public String getStrItemValues() {
		return strItemValues;
	}
	public void setStrItemValues(String strItemValues) {
		this.strItemValues = strItemValues;
	}
	public String getStrWhetherBatchWise() {
		return strWhetherBatchWise;
	}
	public void setStrWhetherBatchWise(String strWhetherBatchWise) {
		this.strWhetherBatchWise = strWhetherBatchWise;
	}
	public String getStrDrugName() {
		return strDrugName;
	}
	public void setStrDrugName(String strDrugName) {
		this.strDrugName = strDrugName;
	}
	public String getStrItemTypeValues() {
		return strItemTypeValues;
	}
	public void setStrItemTypeValues(String strItemTypeValues) {
		this.strItemTypeValues = strItemTypeValues;
	}
	public String[] getStrLeftItemIds() {
		return strLeftItemIds;
	}
	public void setStrLeftItemIds(String[] strLeftItemIds) {
		this.strLeftItemIds = strLeftItemIds;
	}
	public String getStrLeftItemList() {
		return strLeftItemList;
	}
	public void setStrLeftItemList(String strLeftItemList) {
		this.strLeftItemList = strLeftItemList;
	}
	public String[] getStrRightItemIds() {
		return strRightItemIds;
	}
	public void setStrRightItemIds(String[] strRightItemIds) {
		this.strRightItemIds = strRightItemIds;
	}
	public String getStrRightItemList() {
		return strRightItemList;
	}
	public void setStrRightItemList(String strRightItemList) {
		this.strRightItemList = strRightItemList;
	}
	public String getStrItemType() {
		return strItemType;
	}
	public void setStrItemType(String strItemType) {
		this.strItemType = strItemType;
	}
	public String getStrItemBrandId() {
		return strItemBrandId;
	}
	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}

}
