package mms.transactions.controller.fb;

import javax.sql.rowset.WebRowSet;

import hisglobal.transactionutil.GenericFormBean;


public class AcknowledgeTransFB extends GenericFormBean{

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
	
	private String strRemarks = "";
	private String strRequestType = "";
	private String strPath = "";
	
	private String strAcknowledgeDetails = "";
	private String strItemDetails = "";
	private String strAckDtls = "";
	private String strAckStatus="0";
	private String strReturnStatus="0";
	private String strTransNo="0";
	private String strStoreId="";
	private String cnt_page="";
	private WebRowSet strAcknowledgeDtlWs = null;
	private WebRowSet strItemDtlWs = null;
	//Hidden Fields
	
	 private String[] strHiddenValue  = null;
	 private String[] strBkgQty = null;
	 private String[] strReceivedQty = null;
	 private String strHidVal = "";
	 private String strViewMode="0";
	 private String strReqTypeId = "";
	 private String strRequestMode ="0";
	 
	public String getStrReqTypeId() {
		return strReqTypeId;
	}
	public void setStrReqTypeId(String strReqTypeId) {
		this.strReqTypeId = strReqTypeId;
	}
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
	public WebRowSet getStrAcknowledgeDtlWs() {
		return strAcknowledgeDtlWs;
	}
	public void setStrAcknowledgeDtlWs(WebRowSet strAcknowledgeDtlWs) {
		this.strAcknowledgeDtlWs = strAcknowledgeDtlWs;
	}
	public WebRowSet getStrItemDtlWs() {
		return strItemDtlWs;
	}
	public void setStrItemDtlWs(WebRowSet strItemDtlWs) {
		this.strItemDtlWs = strItemDtlWs;
	}
	public String getStrRequestType() {
		return strRequestType;
	}
	public void setStrRequestType(String strRequestType) {
		this.strRequestType = strRequestType;
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
	public String getStrViewMode() {
		return strViewMode;
	}
	public void setStrViewMode(String strViewMode) {
		this.strViewMode = strViewMode;
	}
	public String getStrRequestMode() {
		return strRequestMode;
	}
	public void setStrRequestMode(String strRequestMode) {
		this.strRequestMode = strRequestMode;
	}
	public String getStrReturnStatus() {
		return strReturnStatus;
	}
	public void setStrReturnStatus(String strReturnStatus) {
		this.strReturnStatus = strReturnStatus;
	}
	
}
