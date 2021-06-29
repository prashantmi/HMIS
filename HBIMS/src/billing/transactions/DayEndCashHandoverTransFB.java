package billing.transactions;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;

public class DayEndCashHandoverTransFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	
	private String strUserVal = "";
	private String strCounterVal = "";
	private String strSessionVal = "";
	private String strUserMode = "";
	private String strCountId = "";
	private String strUsrId = "";
	private String strSessionFromTime = "";
	private String strSessionToTime = "";
	private String strBillModuleId = "";
	private String strCtDate = "";
	private String strDayEndDate = "";
	
	private String strProcessType = "";
	private String strDateType = "";

	private String strPrintCount = "0";
	
	private String strIsDetailsRequired = "0";
	private String strIsPayModeRequired = "0";
	private String strIsPayDetailRequired = "0";
	private String strRetValue="";
	private String strHospitalCode="";
	private String strUserName="";
	private String strUserId="";
	private String strCounterName="";
	private String strCounterId="0";
	private String strSessionId="";
	
	private String strIpAddress="";
	private String strMsgString="";
	private String strMsgType="";
	private String strErrMsg="";
	private String strSeatId="";
	private String strRemarks="";
	private String strSessValue="";
	private String strPrimaryKeyMsg = "";
	private String strSummNo="";
	
	private String strMsg = "";
	private String msgStr = "";
	private String msgType = "";
	private String MsgString = "";
	private String strErr = "";
	private String strValid = null;
	private String strWarning = "";
	
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strReportFormat = "0";
	private String strUserRemarks = "0";
	
	private String strPendingDayEndDetailsTable="";
	
	private String strDayEndProcess="";
	private WebRowSet strDayEndReportProcess=null;
	private WebRowSet strDayEndPaymentReportProcess=null;
	
	//denomination details..currency note and coins count
	private String strTwoThousandNotes="0";
	private String strThousandNotes="0";
	private String strFiveHundNotes="0";
	private String strTwoHundNotes="0";
	private String strHundNotes="0";
	private String strFiftyNotes="0";
	private String strTwentyNotes="0";
	private String strTenNotes="0";
	private String strFiveNotes="0";
	private String strTwoNotes="0";
	private String strOneNotes="0";
	private String strOneCoins="0";
	private String strTwoCoins="0";
	private String strFiveCoins="0";
	private String strtenCoins="0";
	private String strDayEndAmount="0";
	private String strDayEndUserName="0";
	private String strDayEndCounterName="0";
	private String strDayEndMode="1"; //1 for counter + user wise, 2 for counter wise, 3 for user wise
	private String strDayEndCreditCol="0";
	private String strDayEndTimeBoundFlag="";
	private String strDayEndAllowedFlag="";
	private String strDayEndAllowedTime="";
	private String strCurrentTime="";	
	private String strRadioButton;
	private String strDayEndInstAmt="0";
	private String[] strCashHandoverTo=null;
    private String strCashHandoverToValues="";

	private String[] strCashHandOverAmount=null;
	private String[] strChk=null;
	private String[] strRefNo=null;
	private String[] strHandOverToName=null;
	
	public String getStrDayEndInstAmt() {
		return strDayEndInstAmt;
	}
	public void setStrDayEndInstAmt(String strDayEndInstAmt) {
		this.strDayEndInstAmt = strDayEndInstAmt;
	}
	public String getStrRadioButton() {
		return strRadioButton;
	}
	public void setStrRadioButton(String strRadioButton) {
		this.strRadioButton = strRadioButton;
	}
	public WebRowSet getStrDayEndReportProcess() {
		return strDayEndReportProcess;
	}
	public void setStrDayEndReportProcess(WebRowSet strDayEndReportProcess) {
		this.strDayEndReportProcess = strDayEndReportProcess;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrUserName() {
		return strUserName;
	}
	public void setStrUserName(String strUserName) {
		this.strUserName = strUserName;
	}
	public String getStrCounterName() {
		return strCounterName;
	}
	public void setStrCounterName(String strCounterName) {
		this.strCounterName = strCounterName;
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
	 
	public String getStrUserId() {
		return strUserId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	public String getStrIpAddress() {
		return strIpAddress;
	}
	public void setStrIpAddress(String strIpAddress) {
		this.strIpAddress = strIpAddress;
	}
	
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrDayEndProcess() {
		return strDayEndProcess;
	}
	public void setStrDayEndProcess(String strDayEndProcess) {
		this.strDayEndProcess = strDayEndProcess;
	}
	public String getStrRetValue() {
		return strRetValue;
	}
	public void setStrRetValue(String strRetValue) {
		this.strRetValue = strRetValue;
	}
	public String getStrCounterId() {
		return strCounterId;
	}
	public void setStrCounterId(String strCounterId) {
		this.strCounterId = strCounterId;
	}
	public WebRowSet getStrDayEndPaymentReportProcess() {
		return strDayEndPaymentReportProcess;
	}
	public void setStrDayEndPaymentReportProcess(
			WebRowSet strDayEndPaymentReportProcess) {
		this.strDayEndPaymentReportProcess = strDayEndPaymentReportProcess;
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
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}
	/**
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	/**
	 * @return the strsessValue
	 */
	public String getStrSessValue() {
		return strSessValue;
	}
	/**
	 * @param strsessValue the strsessValue to set
	 */
	public void setStrSessValue(String strSessValue) {
		this.strSessValue = strSessValue;
	}
	/**
	 * @return the strPrimaryKeyMsg
	 */
	public String getStrPrimaryKeyMsg() {
		return strPrimaryKeyMsg;
	}
	/**
	 * @param strPrimaryKeyMsg the strPrimaryKeyMsg to set
	 */
	public void setStrPrimaryKeyMsg(String strPrimaryKeyMsg) {
		this.strPrimaryKeyMsg = strPrimaryKeyMsg;
	}
	/**
	 * @return the strSummNo
	 */
	public String getStrSummNo() {
		return strSummNo;
	}
	/**
	 * @param strSummNo the strSummNo to set
	 */
	public void setStrSummNo(String strSummNo) {
		this.strSummNo = strSummNo;
	}
	public String getMsgStr() {
		return msgStr;
	}
	public void setMsgStr(String msgStr) {
		this.msgStr = msgStr;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgString() {
		return MsgString;
	}
	public void setMsgString(String msgString) {
		MsgString = msgString;
	}
	public String getStrErr() {
		return strErr;
	}
	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}
	public String getStrValid() {
		return strValid;
	}
	public void setStrValid(String strValid) {
		this.strValid = strValid;
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
	public String getStrIsFooter() {
		return strIsFooter;
	}
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	/**
	 * @return the strUserVal
	 */
	public String getStrUserVal() {
		return strUserVal;
	}
	/**
	 * @param strUserVal the strUserVal to set
	 */
	public void setStrUserVal(String strUserVal) {
		this.strUserVal = strUserVal;
	}
	/**
	 * @return the strCounterVal
	 */
	public String getStrCounterVal() {
		return strCounterVal;
	}
	/**
	 * @param strCounterVal the strCounterVal to set
	 */
	public void setStrCounterVal(String strCounterVal) {
		this.strCounterVal = strCounterVal;
	}
	/**
	 * @return the strSessionVal
	 */
	public String getStrSessionVal() {
		return strSessionVal;
	}
	/**
	 * @param strSessionVal the strSessionVal to set
	 */
	public void setStrSessionVal(String strSessionVal) {
		this.strSessionVal = strSessionVal;
	}
	/**
	 * @return the strSessionId
	 */
	public String getStrSessionId() {
		return strSessionId;
	}
	/**
	 * @param strSessionId the strSessionId to set
	 */
	public void setStrSessionId(String strSessionId) {
		this.strSessionId = strSessionId;
	}
	/**
	 * @return the strUserMode
	 */
	public String getStrUserMode() {
		return strUserMode;
	}
	/**
	 * @param strUserMode the strUserMode to set
	 */
	public void setStrUserMode(String strUserMode) {
		this.strUserMode = strUserMode;
	}
	/**
	 * @return the strCountId
	 */
	public String getStrCountId() {
		return strCountId;
	}
	/**
	 * @param strCountId the strCountId to set
	 */
	public void setStrCountId(String strCountId) {
		this.strCountId = strCountId;
	}
	/**
	 * @return the strUsrId
	 */
	public String getStrUsrId() {
		return strUsrId;
	}
	/**
	 * @param strUsrId the strUsrId to set
	 */
	public void setStrUsrId(String strUsrId) {
		this.strUsrId = strUsrId;
	}
	/**
	 * @return the strSessionFromTime
	 */
	public String getStrSessionFromTime() {
		return strSessionFromTime;
	}
	/**
	 * @param strSessionFromTime the strSessionFromTime to set
	 */
	public void setStrSessionFromTime(String strSessionFromTime) {
		this.strSessionFromTime = strSessionFromTime;
	}
	/**
	 * @return the strSessionToTime
	 */
	public String getStrSessionToTime() {
		return strSessionToTime;
	}
	/**
	 * @param strSessionToTime the strSessionToTime to set
	 */
	public void setStrSessionToTime(String strSessionToTime) {
		this.strSessionToTime = strSessionToTime;
	}
	/**
	 * @return the strBillModuleId
	 */
	public String getStrBillModuleId() {
		return strBillModuleId;
	}
	/**
	 * @param strBillModuleId the strBillModuleId to set
	 */
	public void setStrBillModuleId(String strBillModuleId) {
		this.strBillModuleId = strBillModuleId;
	}
	 
	public String getStrDayEndDate() {
		return strDayEndDate;
	}
	public void setStrDayEndDate(String strDayEndDate) {
		this.strDayEndDate = strDayEndDate;
	}
	public String getStrCtDate() {
		return strCtDate;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String getStrIsDetailsRequired() {
		return strIsDetailsRequired;
	}
	public void setStrIsDetailsRequired(String strIsDetailsRequired) {
		this.strIsDetailsRequired = strIsDetailsRequired;
	}
	public String getStrProcessType() {
		return strProcessType;
	}
	public void setStrProcessType(String strProcessType) {
		this.strProcessType = strProcessType;
	}
	public String getStrDateType() {
		return strDateType;
	}
	public void setStrDateType(String strDateType) {
		this.strDateType = strDateType;
	}
	public String getStrIsPayModeRequired() {
		return strIsPayModeRequired;
	}
	public void setStrIsPayModeRequired(String strIsPayModeRequired) {
		this.strIsPayModeRequired = strIsPayModeRequired;
	}
	public String getStrIsPayDetailRequired() {
		return strIsPayDetailRequired;
	}
	public void setStrIsPayDetailRequired(String strIsPayDetailRequired) {
		this.strIsPayDetailRequired = strIsPayDetailRequired;
	}
	 
	public String getStrPrintCount() {
		return strPrintCount;
	}
	public void setStrPrintCount(String strPrintCount) {
		this.strPrintCount = strPrintCount;
	}
	public String getStrPendingDayEndDetailsTable() {
		return strPendingDayEndDetailsTable;
	}
	public void setStrPendingDayEndDetailsTable(String strPendingDayEndDetailsTable) {
		this.strPendingDayEndDetailsTable = strPendingDayEndDetailsTable;
	}
	public String getStrTwoThousandNotes() {
		return strTwoThousandNotes;
	}
	public void setStrTwoThousandNotes(String strTwoThousandNotes) {
		this.strTwoThousandNotes = strTwoThousandNotes;
	}
	public String getStrThousandNotes() {
		return strThousandNotes;
	}
	public void setStrThousandNotes(String strThousandNotes) {
		this.strThousandNotes = strThousandNotes;
	}
	public String getStrFiveHundNotes() {
		return strFiveHundNotes;
	}
	public void setStrFiveHundNotes(String strFiveHundNotes) {
		this.strFiveHundNotes = strFiveHundNotes;
	}
	
	public String getStrTwoHundNotes() {
		return strTwoHundNotes;
	}
	public void setStrTwoHundNotes(String strTwoHundNotes) {
		this.strTwoHundNotes = strTwoHundNotes;
	}
	public String getStrHundNotes() {
		return strHundNotes;
	}
	public void setStrHundNotes(String strHundNotes) {
		this.strHundNotes = strHundNotes;
	}
	public String getStrFiftyNotes() {
		return strFiftyNotes;
	}
	public void setStrFiftyNotes(String strFiftyNotes) {
		this.strFiftyNotes = strFiftyNotes;
	}
	public String getStrTwentyNotes() {
		return strTwentyNotes;
	}
	public void setStrTwentyNotes(String strTwentyNotes) {
		this.strTwentyNotes = strTwentyNotes;
	}
	public String getStrTenNotes() {
		return strTenNotes;
	}
	public void setStrTenNotes(String strTenNotes) {
		this.strTenNotes = strTenNotes;
	}
	public String getStrFiveNotes() {
		return strFiveNotes;
	}
	public void setStrFiveNotes(String strFiveNotes) {
		this.strFiveNotes = strFiveNotes;
	}
	public String getStrTwoNotes() {
		return strTwoNotes;
	}
	public void setStrTwoNotes(String strTwoNotes) {
		this.strTwoNotes = strTwoNotes;
	}
	public String getStrOneNotes() {
		return strOneNotes;
	}
	public void setStrOneNotes(String strOneNotes) {
		this.strOneNotes = strOneNotes;
	}
	
	public String getStrDayEndAmount() {
		return strDayEndAmount;
	}
	public void setStrDayEndAmount(String strDayEndAmount) {
		this.strDayEndAmount = strDayEndAmount;
	}
	public String getStrOneCoins() {
		return strOneCoins;
	}
	public void setStrOneCoins(String strOneCoins) {
		this.strOneCoins = strOneCoins;
	}
	public String getStrTwoCoins() {
		return strTwoCoins;
	}
	public void setStrTwoCoins(String strTwoCoins) {
		this.strTwoCoins = strTwoCoins;
	}
	public String getStrFiveCoins() {
		return strFiveCoins;
	}
	public void setStrFiveCoins(String strFiveCoins) {
		this.strFiveCoins = strFiveCoins;
	}
	public String getStrtenCoins() {
		return strtenCoins;
	}
	public void setStrtenCoins(String strtenCoins) {
		this.strtenCoins = strtenCoins;
	}
	public String getStrDayEndUserName() {
		return strDayEndUserName;
	}
	public void setStrDayEndUserName(String strDayEndUserName) {
		this.strDayEndUserName = strDayEndUserName;
	}
	public String getStrDayEndCounterName() {
		return strDayEndCounterName;
	}
	public void setStrDayEndCounterName(String strDayEndCounterName) {
		this.strDayEndCounterName = strDayEndCounterName;
	}
	public String getStrDayEndMode() {
		return strDayEndMode;
	}
	public void setStrDayEndMode(String strDayEndMode) {
		this.strDayEndMode = strDayEndMode;
	}
	public String getStrDayEndCreditCol() {
		return strDayEndCreditCol;
	}
	public void setStrDayEndCreditCol(String strDayEndCreditCol) {
		this.strDayEndCreditCol = strDayEndCreditCol;
	}
	public String getStrDayEndTimeBoundFlag() {
		return strDayEndTimeBoundFlag;
	}
	public void setStrDayEndTimeBoundFlag(String strDayEndTimeBoundFlag) {
		this.strDayEndTimeBoundFlag = strDayEndTimeBoundFlag;
	}
	public String getStrDayEndAllowedFlag() {
		return strDayEndAllowedFlag;
	}
	public void setStrDayEndAllowedFlag(String strDayEndAllowedFlag) {
		this.strDayEndAllowedFlag = strDayEndAllowedFlag;
	}
	public String getStrDayEndAllowedTime() {
		return strDayEndAllowedTime;
	}
	public void setStrDayEndAllowedTime(String strDayEndAllowedTime) {
		this.strDayEndAllowedTime = strDayEndAllowedTime;
	}
	public String getStrCurrentTime() {
		return strCurrentTime;
	}
	public void setStrCurrentTime(String strCurrentTime) {
		this.strCurrentTime = strCurrentTime;
	}
	public String[] getStrCashHandoverTo() {
		return strCashHandoverTo;
	}
	public void setStrCashHandoverTo(String[] strCashHandoverTo) {
		this.strCashHandoverTo = strCashHandoverTo;
	}
	
	public String getStrCashHandoverToValues() {
		return strCashHandoverToValues;
	}
	public void setStrCashHandoverToValues(String strCashHandoverToValues) {
		this.strCashHandoverToValues = strCashHandoverToValues;
	}
	public String[] getStrCashHandOverAmount() {
		return strCashHandOverAmount;
	}
	public void setStrCashHandOverAmount(String[] strCashHandOverAmount) {
		this.strCashHandOverAmount = strCashHandOverAmount;
	}
	public String[] getStrChk() {
		return strChk;
	}
	public void setStrChk(String[] strChk) {
		this.strChk = strChk;
	}
	public String[] getStrRefNo() {
		return strRefNo;
	}
	public void setStrRefNo(String[] strRefNo) {
		this.strRefNo = strRefNo;
	}
	public String[] getStrHandOverToName() {
		return strHandOverToName;
	}
	public void setStrHandOverToName(String[] strHandOverToName) {
		this.strHandOverToName = strHandOverToName;
	}
	
	
	
}