package billing.transactions;
import javax.sql.rowset.WebRowSet;

public class OnlineRequestCancellationTransVO {

	
	private static final long serialVersionUID = 02L;
	
	private String strHospitalCode = "";
	private String  strSeatId = "";
	private String strIpAddress = "";
	private String strChkValues = "";
	
	private String strCrNo="";
	private String strCancelledBy="";
	private String strCancelReason="";
	private String strOtherReason="";
	private String strOnlineReqDtl="";
	
	private String strRequestNo="";
	private String strPopUpDtls="";
	private String strPopUpData="";
	
	private String strMsg = "";
	private String strErrMsg="";
	private String strMsgString="";
	private String strMsgType="";
		
	private String strPrimaryKeyMsg = "";
	private String strPrimaryKeyLogMsg = "";
	
	private WebRowSet strOnlineReqDtlWs=null;
	private WebRowSet strCancelledByWs=null;
	private WebRowSet strCancelReasonWs=null;
	private WebRowSet strPopUpWs=null;
	
	
	
	
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
	
	public WebRowSet getStrOnlineReqDtlWs() {
		return strOnlineReqDtlWs;
	}
	public void setStrOnlineReqDtlWs(WebRowSet strOnlineReqDtlWs) {
		this.strOnlineReqDtlWs = strOnlineReqDtlWs;
	}
	public void setStrOnlineReqDtl(String strOnlineReqDtl) {
		this.strOnlineReqDtl = strOnlineReqDtl;
	}
	public String getStrOnlineReqDtl() {
		return strOnlineReqDtl;
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
	public String getStrRequestNo() {
		return strRequestNo;
	}
	public void setStrRequestNo(String strRequestNo) {
		this.strRequestNo = strRequestNo;
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
	public String getStrChkValues() {
		return strChkValues;
	}
	public void setStrChkValues(String strChkValues) {
		this.strChkValues = strChkValues;
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
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrIpAddress() {
		return strIpAddress;
	}
	public void setStrIpAddress(String strIpAddress) {
		this.strIpAddress = strIpAddress;
	}
	
}
