package mms.transactions.vo;

import javax.sql.rowset.WebRowSet;

public class AcknowledgeTransVO {
	
	private String strHospitalCode = "";
	private String strSeatId ="";
	private String strMsgType = "";
	private String strMsgString = "";
	
	private String strStoreId = "";
	private String strTransNo = "";
	private String strReqTypeId = "";
	private String strRemarks = "";
	
	private WebRowSet strAcknowledgeDtlWs = null;
	private WebRowSet strItemDtlWs = null;
	private WebRowSet strAckDtlWs = null;
	
	private String strItemId = "";
	private String strItemBrandId = "";
	private String strBatchNo = "";
	private String strQty = "";
	private String strQtyUnitId = "";
	private String strItemSlNo = "";
	private String strStockStatusCode = "";
	private String strStrId = "";
	private String strToStrName = "";
	private String strToStrId = "";
	private String strItemCatNo = "";
	private String strAckDate = "";
	
	private String strReservedFlag = "";
	private String strBlockedQtyFlag = "";
	
	 private String[] strHiddenValue  = null;
	 private String[] strBkgQty = null;
	 private String[] strReceivedQty = null;
	 private WebRowSet transferDtlWs  =null;

	public WebRowSet getTransferDtlWs() {
		return transferDtlWs;
	}

	public void setTransferDtlWs(WebRowSet transferDtlWs) {
		this.transferDtlWs = transferDtlWs;
	}

	public String[] getStrBkgQty() {
		return strBkgQty;
	}

	public void setStrBkgQty(String[] strBkgQty) {
		this.strBkgQty = strBkgQty;
	}

	public String[] getStrReceivedQty() {
		return strReceivedQty;
	}

	public void setStrReceivedQty(String[] strReceivedQty) {
		this.strReceivedQty = strReceivedQty;
	}

	public String[] getStrHiddenValue() {
		return strHiddenValue;
	}

	public void setStrHiddenValue(String[] strHiddenValue) {
		this.strHiddenValue = strHiddenValue;
	}

	public String getStrAckDate() {
		return strAckDate;
	}

	public String getStrReservedFlag() {
		return strReservedFlag;
	}

	public void setStrReservedFlag(String strReservedFlag) {
		this.strReservedFlag = strReservedFlag;
	}

	public String getStrBlockedQtyFlag() {
		return strBlockedQtyFlag;
	}

	public void setStrBlockedQtyFlag(String strBlockedQtyFlag) {
		this.strBlockedQtyFlag = strBlockedQtyFlag;
	}

	public void setStrAckDate(String strAckDate) {
		this.strAckDate = strAckDate;
	}

	public String getStrStrId() {
		return strStrId;
	}

	public void setStrStrId(String strStrId) {
		this.strStrId = strStrId;
	}

	public String getStrToStrId() {
		return strToStrId;
	}

	public void setStrToStrId(String strToStrId) {
		this.strToStrId = strToStrId;
	}

	public String getStrItemId() {
		return strItemId;
	}

	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}

	public String getStrItemBrandId() {
		return strItemBrandId;
	}

	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}

	public String getStrBatchNo() {
		return strBatchNo;
	}

	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}

	public String getStrQty() {
		return strQty;
	}

	public void setStrQty(String strQty) {
		this.strQty = strQty;
	}

	public String getStrQtyUnitId() {
		return strQtyUnitId;
	}

	public void setStrQtyUnitId(String strQtyUnitId) {
		this.strQtyUnitId = strQtyUnitId;
	}

	public String getStrItemSlNo() {
		return strItemSlNo;
	}

	public void setStrItemSlNo(String strItemSlNo) {
		this.strItemSlNo = strItemSlNo;
	}

	public String getStrStockStatusCode() {
		return strStockStatusCode;
	}

	public void setStrStockStatusCode(String strStockStatusCode) {
		this.strStockStatusCode = strStockStatusCode;
	}

	public WebRowSet getStrAcknowledgeDtlWs() {
		return strAcknowledgeDtlWs;
	}

	public void setStrAcknowledgeDtlWs(WebRowSet strAcknowledgeDtlWs) {
		this.strAcknowledgeDtlWs = strAcknowledgeDtlWs;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public WebRowSet getStrItemDtlWs() {
		return strItemDtlWs;
	}

	public void setStrItemDtlWs(WebRowSet strItemDtlWs) {
		this.strItemDtlWs = strItemDtlWs;
	}

	public String getStrStoreId() {
		return strStoreId;
	}

	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}

	public String getStrTransNo() {
		return strTransNo;
	}

	public void setStrTransNo(String strTransNo) {
		this.strTransNo = strTransNo;
	}

	public String getStrReqTypeId() {
		return strReqTypeId;
	}

	public void setStrReqTypeId(String strReqTypeId) {
		this.strReqTypeId = strReqTypeId;
	}

	public WebRowSet getStrAckDtlWs() {
		return strAckDtlWs;
	}

	public void setStrAckDtlWs(WebRowSet strAckDtlWs) {
		this.strAckDtlWs = strAckDtlWs;
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

	public String getStrItemCatNo() {
		return strItemCatNo;
	}

	public void setStrItemCatNo(String strItemCatNo) {
		this.strItemCatNo = strItemCatNo;
	}

	public String getStrToStrName() {
		return strToStrName;
	}

	public void setStrToStrName(String strToStrName) {
		this.strToStrName = strToStrName;
	}

	
}
