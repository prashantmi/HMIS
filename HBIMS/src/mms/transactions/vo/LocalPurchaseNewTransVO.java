/**
 * 
 */
package mms.transactions.vo;

import javax.sql.rowset.WebRowSet;

import hisglobal.utility.TransferObject;

/**
 * Developer : Tanvi sappal
 * Version : 1.0
 * Date : 18/Jun/09
 */
public class LocalPurchaseNewTransVO implements TransferObject {
	
	private static final long serialVersionUID = 02L;
	
	private String strMsgString = "";
	private String strMsgType = "";
	private String strLpoNo ;
	private String strUcChk ;
	private String strItemCategoryNo = "";
	private String[] strSearchDrug;
	private String strStoreId = "";
	private String strStockStatusCode = "";
	private String strCtDate = "";
	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strIsValid = "";
	private String[] strItemId;
	private String strBatchNo = "";
	private String strReqTypeId = "";
	private String strSupplier;
	private String strSupplierCombo;
	private String strDCNo;
	private String strChallanDate;
	private String strInvoiceNo;
	private String strInvoiceDate;
	private String[] strbatchno;
	private String[] strMfgDate;
	private String[] strExpDate;
	private String[] strPackSize;
	private String[] strPackNo;
	private String[] strRatePack;
	private String[] strGST;
	private String[] strAdmchg;
	private String[] strTotalQty;
	private String[] strPurRate;
	private String[] strPurWidTax;
	private String[] strAdm;
	private String[] strCosttoPat;
	private String strRemarks;
	private String strStoreName;
	private String strItemBrandCombo;
	private WebRowSet strSupplierWs;
	private WebRowSet itemWS;
	private String strLPNo;
	private WebRowSet wsPrint;
	private String[] strMultiRowItemId;
	private String[] strBrandId;
	
	
	public String getStrLpoNo() {
		return strLpoNo;
	}
	public void setStrLpoNo(String strLpoNo) {
		this.strLpoNo = strLpoNo;
	}
	public String getStrUcChk() {
		return strUcChk;
	}
	public void setStrUcChk(String strUcChk) {
		this.strUcChk = strUcChk;
	}
	public String[] getStrBrandId() {
		return strBrandId;
	}
	public void setStrBrandId(String[] strBrandId) {
		this.strBrandId = strBrandId;
	}
	public String[] getStrMultiRowItemId() {
		return strMultiRowItemId;
	}
	public void setStrMultiRowItemId(String[] strMultiRowItemId) {
		this.strMultiRowItemId = strMultiRowItemId;
	}
	
	public String getStrLPNo() {
		return strLPNo;
	}
	public void setStrLPNo(String strLPNo) {
		this.strLPNo = strLPNo;
	}
	public WebRowSet getWsPrint() {
		return wsPrint;
	}
	public void setWsPrint(WebRowSet wsPrint) {
		this.wsPrint = wsPrint;
	}
	public WebRowSet getStrSupplierWs() {
		return strSupplierWs;
	}
	public void setStrSupplierWs(WebRowSet strSupplierWs) {
		this.strSupplierWs = strSupplierWs;
	}
	public WebRowSet getItemWS() {
		return itemWS;
	}
	public void setItemWS(WebRowSet itemWS) {
		this.itemWS = itemWS;
	}
	public String getStrSupplierCombo() {
		return strSupplierCombo;
	}
	public void setStrSupplierCombo(String strSupplierCombo) {
		this.strSupplierCombo = strSupplierCombo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	
	public String getStrSupplier() {
		return strSupplier;
	}
	public void setStrSupplier(String strSupplier) {
		this.strSupplier = strSupplier;
	}
	 
	
	public String getStrItemBrandCombo() {
		return strItemBrandCombo;
	}
	public void setStrItemBrandCombo(String strItemBrandCombo) {
		this.strItemBrandCombo = strItemBrandCombo;
	}
	/**
	 * @return the strItemCategoryNo
	 */
	public String getStrItemCategoryNo() {
		return strItemCategoryNo;
	}
	/**
	 * @param strItemCategoryNo the strItemCategoryNo to set
	 */
	public void setStrItemCategoryNo(String strItemCategoryNo) {
		this.strItemCategoryNo = strItemCategoryNo;
	}
	
	/**
	 * @return the strItemId
	 */
	public String[] getStrItemId() {
		return strItemId;
	}
	/**
	 * @param strItemId the strItemId to set
	 */
	public void setStrItemId(String[] strItemId) {
		this.strItemId = strItemId;
	}
	
	
	/**
	 * @return the strCtDate
	 */
	public String getStrCtDate() {
		return strCtDate;
	}
	/**
	 * @param strCtDate the strCtDate to set
	 */
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
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
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}
	/**
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	
	
	/**
	 * @return the strBatchNo
	 */
	public String getStrBatchNo() {
		return strBatchNo;
	}
	/**
	 * @param strBatchNo the strBatchNo to set
	 */
	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}
	
	/**
	 * @return the strStockStatusCode
	 */
	public String getStrStockStatusCode() {
		return strStockStatusCode;
	}
	/**
	 * @param strStockStatusCode the strStockStatusCode to set
	 */
	public void setStrStockStatusCode(String strStockStatusCode) {
		this.strStockStatusCode = strStockStatusCode;
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
	 * @return the strReqTypeId
	 */
	public String getStrReqTypeId() {
		return strReqTypeId;
	}
	/**
	 * @param strReqTypeId the strReqTypeId to set
	 */
	public void setStrReqTypeId(String strReqTypeId) {
		this.strReqTypeId = strReqTypeId;
	}
	public String[] getStrSearchDrug() {
		return strSearchDrug;
	}
	public void setStrSearchDrug(String[] strSearchDrug) {
		this.strSearchDrug = strSearchDrug;
	}
	public String getStrDCNo() {
		return strDCNo;
	}
	public void setStrDCNo(String strDCNo) {
		this.strDCNo = strDCNo;
	}
	public String getStrChallanDate() {
		return strChallanDate;
	}
	public void setStrChallanDate(String strChallanDate) {
		this.strChallanDate = strChallanDate;
	}
	public String getStrInvoiceNo() {
		return strInvoiceNo;
	}
	public void setStrInvoiceNo(String strInvoiceNo) {
		this.strInvoiceNo = strInvoiceNo;
	}
	public String getStrInvoiceDate() {
		return strInvoiceDate;
	}
	public void setStrInvoiceDate(String strInvoiceDate) {
		this.strInvoiceDate = strInvoiceDate;
	}
	public String[] getStrbatchno() {
		return strbatchno;
	}
	public void setStrbatchno(String[] strbatchno) {
		this.strbatchno = strbatchno;
	}
	public String[] getStrMfgDate() {
		return strMfgDate;
	}
	public void setStrMfgDate(String[] strMfgDate) {
		this.strMfgDate = strMfgDate;
	}
	public String[] getStrExpDate() {
		return strExpDate;
	}
	public void setStrExpDate(String[] strExpDate) {
		this.strExpDate = strExpDate;
	}
	public String[] getStrPackSize() {
		return strPackSize;
	}
	public void setStrPackSize(String[] strPackSize) {
		this.strPackSize = strPackSize;
	}
	public String[] getStrPackNo() {
		return strPackNo;
	}
	public void setStrPackNo(String[] strPackNo) {
		this.strPackNo = strPackNo;
	}
	public String[] getStrRatePack() {
		return strRatePack;
	}
	public void setStrRatePack(String[] strRatePack) {
		this.strRatePack = strRatePack;
	}
	public String[] getStrGST() {
		return strGST;
	}
	public void setStrGST(String[] strGST) {
		this.strGST = strGST;
	}
	public String[] getStrAdmchg() {
		return strAdmchg;
	}
	public void setStrAdmchg(String[] strAdmchg) {
		this.strAdmchg = strAdmchg;
	}
	public String[] getStrTotalQty() {
		return strTotalQty;
	}
	public void setStrTotalQty(String[] strTotalQty) {
		this.strTotalQty = strTotalQty;
	}
	public String[] getStrPurRate() {
		return strPurRate;
	}
	public void setStrPurRate(String[] strPurRate) {
		this.strPurRate = strPurRate;
	}
	public String[] getStrPurWidTax() {
		return strPurWidTax;
	}
	public void setStrPurWidTax(String[] strPurWidTax) {
		this.strPurWidTax = strPurWidTax;
	}
	public String[] getStrAdm() {
		return strAdm;
	}
	public void setStrAdm(String[] strAdm) {
		this.strAdm = strAdm;
	}
	public String[] getStrCosttoPat() {
		return strCosttoPat;
	}
	public void setStrCosttoPat(String[] strCosttoPat) {
		this.strCosttoPat = strCosttoPat;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
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
	

}
