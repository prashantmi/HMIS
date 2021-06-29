package mms.transactions.vo;

import javax.sql.rowset.WebRowSet;

public class TransferApprovalTransVO {

	private String strMsgString = "";
	private String strMsgType = "";
	private String strHospitalCode = "";
	private String strSeatId = "";

	private String strRequestNo = "0";
	private String strStoreId = "0";
	private String strItemBrandId = "0";
	private String strRemarks = "";

	private WebRowSet wsDwhList = null;
	private WebRowSet wsDemandRequestDetails = null;
	private WebRowSet wsTransferingDetails = null;

	private String strOrderNo = "";
	private String strOrderDate = "";
	private String strOrderQty = "";
	private String strDemandNo = "";
	private String strDemandDate = "";
	private String strDemandingDDW = "";
	private String strDemandingDDWId = "";
	private String strDrugName = "";
	private String strTransStoreId = "";
	private String strTransRequestNo = "";

	private String strDemandQty = "";
	private String strOrderedQty = "";
	private String strBalanceQty = "";
	private String strBalanceQtyBaseValue = "";

	private String[] strTransferStoreIds = null;
	private String[] strTransferRequestNos = null;
	private String[] strTransferOrderQtys = null;

	public String getStrDemandQty() {
		return strDemandQty;
	}

	public void setStrDemandQty(String strDemandQty) {
		this.strDemandQty = strDemandQty;
	}

	public String getStrOrderedQty() {
		return strOrderedQty;
	}

	public void setStrOrderedQty(String strOrderedQty) {
		this.strOrderedQty = strOrderedQty;
	}

	public String getStrBalanceQty() {
		return strBalanceQty;
	}

	public void setStrBalanceQty(String strBalanceQty) {
		this.strBalanceQty = strBalanceQty;
	}

	public String getStrBalanceQtyBaseValue() {
		return strBalanceQtyBaseValue;
	}

	public void setStrBalanceQtyBaseValue(String strBalanceQtyBaseValue) {
		this.strBalanceQtyBaseValue = strBalanceQtyBaseValue;
	}

	public String[] getStrTransferStoreIds() {
		return strTransferStoreIds;
	}

	public void setStrTransferStoreIds(String[] strTransferStoreIds) {
		this.strTransferStoreIds = strTransferStoreIds;
	}

	public String[] getStrTransferRequestNos() {
		return strTransferRequestNos;
	}

	public void setStrTransferRequestNos(String[] strTransferRequestNos) {
		this.strTransferRequestNos = strTransferRequestNos;
	}

	public String[] getStrTransferOrderQtys() {
		return strTransferOrderQtys;
	}

	public void setStrTransferOrderQtys(String[] strTransferOrderQtys) {
		this.strTransferOrderQtys = strTransferOrderQtys;
	}

	public String getStrOrderNo() {
		return strOrderNo;
	}

	public void setStrOrderNo(String strOrderNo) {
		this.strOrderNo = strOrderNo;
	}

	public String getStrOrderDate() {
		return strOrderDate;
	}

	public void setStrOrderDate(String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}

	public String getStrOrderQty() {
		return strOrderQty;
	}

	public void setStrOrderQty(String strOrderQty) {
		this.strOrderQty = strOrderQty;
	}

	public String getStrDemandNo() {
		return strDemandNo;
	}

	public void setStrDemandNo(String strDemandNo) {
		this.strDemandNo = strDemandNo;
	}

	public String getStrDemandDate() {
		return strDemandDate;
	}

	public void setStrDemandDate(String strDemandDate) {
		this.strDemandDate = strDemandDate;
	}

	public String getStrDemandingDDW() {
		return strDemandingDDW;
	}

	public void setStrDemandingDDW(String strDemandingDDW) {
		this.strDemandingDDW = strDemandingDDW;
	}

	public String getStrDemandingDDWId() {
		return strDemandingDDWId;
	}

	public void setStrDemandingDDWId(String strDemandingDDWId) {
		this.strDemandingDDWId = strDemandingDDWId;
	}

	public String getStrDrugName() {
		return strDrugName;
	}

	public void setStrDrugName(String strDrugName) {
		this.strDrugName = strDrugName;
	}

	public String getStrTransStoreId() {
		return strTransStoreId;
	}

	public void setStrTransStoreId(String strTransStoreId) {
		this.strTransStoreId = strTransStoreId;
	}

	public String getStrTransRequestNo() {
		return strTransRequestNo;
	}

	public void setStrTransRequestNo(String strTransRequestNo) {
		this.strTransRequestNo = strTransRequestNo;
	}

	public String getStrItemBrandId() {
		return strItemBrandId;
	}

	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}

	public WebRowSet getWsDemandRequestDetails() {
		return wsDemandRequestDetails;
	}

	public void setWsDemandRequestDetails(WebRowSet wsDemandRequestDetails) {
		this.wsDemandRequestDetails = wsDemandRequestDetails;
	}

	public WebRowSet getWsTransferingDetails() {
		return wsTransferingDetails;
	}

	public void setWsTransferingDetails(WebRowSet wsTransferingDetails) {
		this.wsTransferingDetails = wsTransferingDetails;
	}

	public WebRowSet getWsDwhList() {
		return wsDwhList;
	}

	public void setWsDwhList(WebRowSet wsDwhList) {
		this.wsDwhList = wsDwhList;
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

	public String getStrRequestNo() {
		return strRequestNo;
	}

	public void setStrRequestNo(String strRequestNo) {
		this.strRequestNo = strRequestNo;
	}

	public String getStrStoreId() {
		return strStoreId;
	}

	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
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
