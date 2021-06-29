package mms.transactions.controller.fb;

import hisglobal.transactionutil.GenericFormBean;

public class TransferApprovalTransFB extends GenericFormBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String strErr = "";
	private String strWarning = "";
	private String strMsg = "";
	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strCtDate = "";

	private String strRequestNo = "";
	private String strStoreId = "";
	private String strItemBrandId = "";
	private String strRemarks = "";

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

	private String strDwhNames = "";
	private String strDemandRequestDetails = "";
	private String strTransferingDetails = "";

	private String strDemandRequest = "";
	private String[] strTransferingDtls = null;
	private String[] strTransferOrderQty = null;

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

	public String getStrDemandRequest() {
		return strDemandRequest;
	}

	public void setStrDemandRequest(String strDemandRequest) {
		this.strDemandRequest = strDemandRequest;
	}

	public String[] getStrTransferingDtls() {
		return strTransferingDtls;
	}

	public void setStrTransferingDtls(String[] strTransferingDtls) {
		this.strTransferingDtls = strTransferingDtls;
	}

	public String[] getStrTransferOrderQty() {
		return strTransferOrderQty;
	}

	public void setStrTransferOrderQty(String[] strTransferOrderQty) {
		this.strTransferOrderQty = strTransferOrderQty;
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

	public String getStrTransferingDetails() {
		return strTransferingDetails;
	}

	public void setStrTransferingDetails(String strTransferingDetails) {
		this.strTransferingDetails = strTransferingDetails;
	}

	public String getStrItemBrandId() {
		return strItemBrandId;
	}

	public void setStrItemBrandId(String strItemBrandId) {
		this.strItemBrandId = strItemBrandId;
	}

	public String getStrDwhNames() {
		return strDwhNames;
	}

	public void setStrDwhNames(String strDwhNames) {
		this.strDwhNames = strDwhNames;
	}

	public String getStrCtDate() {
		return strCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrDemandRequestDetails() {
		return strDemandRequestDetails;
	}

	public void setStrDemandRequestDetails(String strDemandRequestDetails) {
		this.strDemandRequestDetails = strDemandRequestDetails;
	}

	public String getStrErr() {
		return strErr;
	}

	public void setStrErr(String strErr) {
		this.strErr = strErr;
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

}
