package billing.transactions;

import javax.sql.rowset.WebRowSet;

public class OnLineRefundApprovalVO {

	private static final long serialVersionUID = 02L;

	
	private String strCase= "1";
	private String strHosCode= "";
	    
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strMsg ="";
	private String strSeatId ="";
	private String strCrNoVal = "";
	private String strRefendedBy;
	private String strRefundedByComboVal;	
	private String strSearchValue;
	private String strSearchMode;
	private String strReqNo;
	private String strRemarks;
	private String strIpAddress;
	private String[] strHiddenVal;
	private String[] strApprovedQty;
	private String[] strRefundCost;
	private String[] tariffId ;
	private String[] strRequestQty;
	
	private WebRowSet RefundApprovalDetails = null;
	private WebRowSet todayRefundApprovalDetails = null;
	private WebRowSet RefundRequestDetails = null;
	private WebRowSet RefundTariffDetails = null;
	private WebRowSet ApprovedBy = null;
	
	public WebRowSet getApprovedBy() {
		return ApprovedBy;
	}
	public void setApprovedBy(WebRowSet approvedBy) {
		ApprovedBy = approvedBy;
	}
	public String getStrCase()
	{
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrHosCode() {
		return strHosCode;
	}
	public void setStrHosCode(String strHosCode) {
		this.strHosCode = strHosCode;
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
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public WebRowSet getRefundApprovalDetails() {
		return RefundApprovalDetails;
	}
	public void setRefundApprovalDetails(WebRowSet refundApprovalDetails) {
		RefundApprovalDetails = refundApprovalDetails;
	}
	public WebRowSet getTodayRefundApprovalDetails() {
		return todayRefundApprovalDetails;
	}
	public void setTodayRefundApprovalDetails(WebRowSet todayRefundApprovalDetails) {
		this.todayRefundApprovalDetails = todayRefundApprovalDetails;
	}
	public WebRowSet getRefundRequestDetails() {
		return RefundRequestDetails;
	}
	public void setRefundRequestDetails(WebRowSet refundRequestDetails) {
		RefundRequestDetails = refundRequestDetails;
	}
	public WebRowSet getRefundTariffDetails() {
		return RefundTariffDetails;
	}
	public void setRefundTariffDetails(WebRowSet refundTariffDetails) {
		RefundTariffDetails = refundTariffDetails;
	}
	public String getStrRefendedBy() {
		return strRefendedBy;
	}
	public void setStrRefendedBy(String strRefendedBy) {
		this.strRefendedBy = strRefendedBy;
	}
	public String getStrRefundedByComboVal() {
		return strRefundedByComboVal;
	}
	public void setStrRefundedByComboVal(String strRefundedByComboVal) {
		this.strRefundedByComboVal = strRefundedByComboVal;
	}
	public String getStrSearchValue() {
		return strSearchValue;
	}
	public void setStrSearchValue(String strSearchValue) {
		this.strSearchValue = strSearchValue;
	}
	public String getStrSearchMode() {
		return strSearchMode;
	}
	public void setStrSearchMode(String strSearchMode) {
		this.strSearchMode = strSearchMode;
	}
	public String getStrReqNo() {
		return strReqNo;
	}
	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}
	public String getStrIpAddress() {
		return strIpAddress;
	}
	public void setStrIpAddress(String strIpAddress) {
		this.strIpAddress = strIpAddress;
	}
	public String[] getStrHiddenVal() {
		return strHiddenVal;
	}
	public void setStrHiddenVal(String[] strHiddenVal) {
		this.strHiddenVal = strHiddenVal;
	}
	public String[] getStrApprovedQty() {
		return strApprovedQty;
	}
	public void setStrApprovedQty(String[] strApprovedQty) {
		this.strApprovedQty = strApprovedQty;
	}
	public String[] getStrRefundCost() {
		return strRefundCost;
	}
	public void setStrRefundCost(String[] strRefundCost) {
		this.strRefundCost = strRefundCost;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String[] getTariffId() {
		return tariffId;
	}
	public void setTariffId(String[] tariffId) {
		this.tariffId = tariffId;
	}
	public String[] getStrRequestQty() {
		return strRequestQty;
	}
	public void setStrRequestQty(String[] strRequestQty) {
		this.strRequestQty = strRequestQty;
	}
	public String getStrCrNoVal() {
		return strCrNoVal;
	}
	public void setStrCrNoVal(String strCrNoVal) {
		this.strCrNoVal = strCrNoVal;
	}
	
}
