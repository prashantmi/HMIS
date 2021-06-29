package billing.transactions;

import javax.sql.rowset.WebRowSet;

public class BillingCancellationTransVO {
	
	private static final long serialVersionUID = 02L;
	
	private String strHospitalCode = "";
	private String  strSeatId = "";
	private String strIpAddress = "";
	private String strChkValues = "";
	
	private String chk[]= null;
	private String crchk[]= null;
	private String cehk[]= null;
	private String clNo[]= null;
	private String clDate[]= null;
	private String strCriteria = "1";
	private String strCase ="";
	private String strCrNo="";
	private String strGuarantorName = "";
	private String strCancelledBy="";
	private String strCancelReason="";
	private String strOtherReason="";
	private String strBillDtl="";
	
	private String strBillNo="";
	private String strPatAccNo="";
	private String strPopUpDtls="";
	private String strPopUpData="";
	
	private String strTransNo = "";
	private String strChargeTypeId = "";
	private String strReceiptType = "";
	private String strBServiceId = "";
	private String strReqNo = "";
	private String strReceiptNo = "";
	private String strCancelNo = "";
	
		
	private String strMsgString="";
	private String strMsgType="";
	private String strMsg = "";
	private String strErrMsg="";
	
	private String strPrimaryKeyMsg = "";
	private String strPrimaryKeyLogMsg = "";
	private String billcancelflag = "0";
	
	
	private WebRowSet strBillDtlWs=null;
	private WebRowSet strCancelledByWs=null;
	private WebRowSet strCancelReasonWs=null;
	private WebRowSet strPopUpWs=null;
	private WebRowSet strTrfDtls = null;
	private String strService="";
	
	
	
	public String getStrCrNo() {
		return strCrNo;
	}
	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}
	public String getStrCancelledBy() {
		return strCancelledBy;
	}
	public void setStrCancelledBy(String strCancelledBy) {
		this.strCancelledBy = strCancelledBy;
	}
	public String getStrCancelReason() {
		return strCancelReason;
	}
	public void setStrCancelReason(String strCancelReason) {
		this.strCancelReason = strCancelReason;
	}
	public String getStrOtherReason() {
		return strOtherReason;
	}
	public void setStrOtherReason(String strOtherReason) {
		this.strOtherReason = strOtherReason;
	}
	public String getStrBillDtl() {
		return strBillDtl;
	}
	public void setStrBillDtl(String strBillDtl) {
		this.strBillDtl = strBillDtl;
	}
	public String getStrBillNo() {
		return strBillNo;
	}
	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}
	public String getStrPopUpDtls() {
		return strPopUpDtls;
	}
	public void setStrPopUpDtls(String strPopUpDtls) {
		this.strPopUpDtls = strPopUpDtls;
	}
	public String getStrPopUpData() {
		return strPopUpData;
	}
	public void setStrPopUpData(String strPopUpData) {
		this.strPopUpData = strPopUpData;
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
	public WebRowSet getStrBillDtlWs() {
		return strBillDtlWs;
	}
	public void setStrBillDtlWs(WebRowSet strBillDtlWs) {
		this.strBillDtlWs = strBillDtlWs;
	}
	public WebRowSet getStrCancelledByWs() {
		return strCancelledByWs;
	}
	public void setStrCancelledByWs(WebRowSet strCancelledByWs) {
		this.strCancelledByWs = strCancelledByWs;
	}
	public WebRowSet getStrCancelReasonWs() {
		return strCancelReasonWs;
	}
	public void setStrCancelReasonWs(WebRowSet strCancelReasonWs) {
		this.strCancelReasonWs = strCancelReasonWs;
	}
	public WebRowSet getStrPopUpWs() {
		return strPopUpWs;
	}
	public void setStrPopUpWs(WebRowSet strPopUpWs) {
		this.strPopUpWs = strPopUpWs;
	}
	public String getStrPatAccNo() {
		return strPatAccNo;
	}
	public void setStrPatAccNo(String strPatAccNo) {
		this.strPatAccNo = strPatAccNo;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrTransNo() {
		return strTransNo;
	}
	public void setStrTransNo(String strTransNo) {
		this.strTransNo = strTransNo;
	}
	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}
	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}
	public String getStrReceiptType() {
		return strReceiptType;
	}
	public void setStrReceiptType(String strReceiptType) {
		this.strReceiptType = strReceiptType;
	}
	public String getStrBServiceId() {
		return strBServiceId;
	}
	public void setStrBServiceId(String strBServiceId) {
		this.strBServiceId = strBServiceId;
	}
	public String getStrReqNo() {
		return strReqNo;
	}
	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}
	public String getStrReceiptNo() {
		return strReceiptNo;
	}
	public void setStrReceiptNo(String strReceiptNo) {
		this.strReceiptNo = strReceiptNo;
	}
	public String getStrCancelNo() {
		return strCancelNo;
	}
	public void setStrCancelNo(String strCancelNo) {
		this.strCancelNo = strCancelNo;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrChkValues() {
		return strChkValues;
	}
	public void setStrChkValues(String strChkValues) {
		this.strChkValues = strChkValues;
	}
	public String getStrGuarantorName() {
		return strGuarantorName;
	}
	public void setStrGuarantorName(String strGuarantorName) {
		this.strGuarantorName = strGuarantorName;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrCriteria() {
		return strCriteria;
	}
	public void setStrCriteria(String strCriteria) {
		this.strCriteria = strCriteria;
	}
	public String getStrPrimaryKeyMsg() {
		return strPrimaryKeyMsg;
	}
	public void setStrPrimaryKeyMsg(String strPrimaryKeyMsg) {
		this.strPrimaryKeyMsg = strPrimaryKeyMsg;
	}
	public String getStrPrimaryKeyLogMsg() {
		return strPrimaryKeyLogMsg;
	}
	public void setStrPrimaryKeyLogMsg(String strPrimaryKeyLogMsg) {
		this.strPrimaryKeyLogMsg = strPrimaryKeyLogMsg;
	}
	/**
	 * @return the chk
	 */
	public String[] getChk() {
		return chk;
	}
	/**
	 * @param chk the chk to set
	 */
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getStrIpAddress() {
		return strIpAddress;
	}
	public void setStrIpAddress(String strIpAddress) {
		this.strIpAddress = strIpAddress;
	}
	public WebRowSet getStrTrfDtls() {
		return strTrfDtls;
	}
	public void setStrTrfDtls(WebRowSet strTrfDtls) {
		this.strTrfDtls = strTrfDtls;
	}
	public String[] getCrchk() {
		return crchk;
	}
	public void setCrchk(String[] crchk) {
		this.crchk = crchk;
	}
	public String[] getCehk() {
		return cehk;
	}
	public void setCehk(String[] cehk) {
		this.cehk = cehk;
	}
	public String getBillcancelflag() {
		return billcancelflag;
	}
	public void setBillcancelflag(String billcancelflag) {
		this.billcancelflag = billcancelflag;
	}
	public String[] getClNo() {
		return clNo;
	}
	public void setClNo(String[] clNo) {
		this.clNo = clNo;
	}
	public String[] getClDate() {
		return clDate;
	}
	public void setClDate(String[] clDate) {
		this.clDate = clDate;
	}
	public String getStrService() {
		return strService;
	}
	public void setStrService(String strService) {
		this.strService = strService;
	}
	

}
