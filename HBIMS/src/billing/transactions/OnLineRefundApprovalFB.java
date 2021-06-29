package billing.transactions;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;

public class OnLineRefundApprovalFB extends ActionForm {

	private static final long serialVersionUID = 02L;

	private String strWarningMsg = "";
	private String strNormalMsg = "";
		
	private String strHosCode= "";
	private String strTransNo = "";

	
	private String strRemarks = "";
	private String strTempRcptNo = "0";
		
	private String strRcptType = "0";
	private String strReqNo="0";
	
	private String strCrNoVal = "";
	private String strCrNo = "";
	private String strIpAddr = "";
	private String strSeatId ="";
	private String strErrMsg ="";
	private String strMsgType ="";
	private String strMsg ="";
	private String strTodayApprovalListDtls;
	private String strRefundRequestDtls;
	private String strSearchValue;
	private String strRefendedBy;
	private String strRefundedByComboVal;	
	private String[] strHiddenVal;
	private String[] strApprovedQty;
	private String[] strRefundCost;
	private String[] tariffId ;
	private String[] strRequestQty;
	
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
	public String getStrSearchValue() {
		return strSearchValue;
	}
	public void setStrSearchValue(String strSearchValue) {
		this.strSearchValue = strSearchValue;
	}
	public String getStrTodayApprovalListDtls() {
		return strTodayApprovalListDtls;
	}
	public void setStrTodayApprovalListDtls(String strTodayApprovalListDtls) {
		this.strTodayApprovalListDtls = strTodayApprovalListDtls;
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
	public String getStrHosCode() {
		return strHosCode;
	}
	public void setStrHosCode(String strHosCode) {
		this.strHosCode = strHosCode;
	}
	public String getStrTransNo() {
		return strTransNo;
	}
	public void setStrTransNo(String strTransNo) {
		this.strTransNo = strTransNo;
	}
	
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrTempRcptNo() {
		return strTempRcptNo;
	}
	public void setStrTempRcptNo(String strTempRcptNo) {
		this.strTempRcptNo = strTempRcptNo;
	}
	public String getStrRcptType() {
		return strRcptType;
	}
	public void setStrRcptType(String strRcptType) {
		this.strRcptType = strRcptType;
	}
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrIpAddr() {
		return strIpAddr;
	}
	public void setStrIpAddr(String strIpAddr) {
		this.strIpAddr = strIpAddr;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
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
	public String getStrRefundRequestDtls() {
		return strRefundRequestDtls;
	}
	public void setStrRefundRequestDtls(String strRefundRequestDtls) {
		this.strRefundRequestDtls = strRefundRequestDtls;
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
	public String getStrReqNo() {
		return strReqNo;
	}
	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
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