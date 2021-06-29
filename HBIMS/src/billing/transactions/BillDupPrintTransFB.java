package billing.transactions;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import org.apache.struts.action.ActionForm;

public class BillDupPrintTransFB extends ActionForm {

	private static final long serialVersionUID = 02L;

	private String strWarningMsg = "";
	private String strNormalMsg = "";
	
	private String strPrintMessageLimit = "";
	
	private String strHosCode= "";
	private String strTransNo = "";
//	private String strBillNo = "";
	private String strRcptNo = "";
	
	private String strTempBillNo = "";
	private String strTempRcptNo = "0";
		
	private String strRcptType = "0";
	
	private String strCrNo = "";
//	private String strBillDtl = "";
//	private String strBillDtlWithNo = "";
	private String strFromDate = "";
	private String strToDate = "";
	private String strCtDate = null;
	private String strBillDetl = "";
//	private String strPageNo = "";
//	private String strGuarantorName	= "";
	private String strCase = "1";
	private String strBillUsrFuncName = "";
	private String strPatientDtls = "";
	private String strIpAddr = "";
	private String strSeatId ="";
	
	private String strSearchType = "";
	private String strSearchString = "";
	
	
	private String strPrintFlag = "";
	
	private String strPukNo = "";
	private String strPatientName = "";
	private String strCategoryName = "";
	private String strBillService = "";
	private String strRePrintChg = "";
	private String strBServiceId = "";
	private String strChargeTypeID = "";
	private String strPatientCatCode = "";
	private String strPatActNo = "";
	private String strAdmnNo   = "";	
	private String strDeptCode = "";
	private String strWardCode = "";
	private String strBillTypeCombo ="";
	
	
	
	private String strBillSearchString = "";
	private String strBillFromRow = "";
	private String strBillToRow = "";
	private String strBillRowPerPage = "";
	private String strBillCtBlockSet = "";
	private String strErrMsg ="";
	private String strMsgType ="";
	private String strMsg ="";
	private String strBillNo="";
	
	
	
	
	
	private WebRowSet billSearchList = null;
	private WebRowSet billDetails = null;

	private String filePath="";
	private String isOpenPopUp="";
	
	private String strReceiptType="0";// hospital copy/patient copy etc..
	private String strReceiptTypevalues;
	private WebRowSet receiptTypeWb=null;
	private String strReceiptTypeLabel="Patient Copy";
	
	private String temp_wallet="";
	
	public WebRowSet getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(WebRowSet billDetails) {
		this.billDetails = billDetails;
	}

	public String getStrCtDate() {

		HisUtil util = new HisUtil("billing", "BillRePrintTransFB");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {

			new HisException("billing Trans", "BillRePrintTransFB.getStrCtDate()", e
					.getMessage());
		}

		return strCtDate;
	}

	/*public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}*/

	public String getStrRcptNo() {
		return strRcptNo;
	}

	public void setStrRcptNo(String strRcptNo) {
		this.strRcptNo = strRcptNo;
	}

	/*public String getStrBillDtl() {
		return strBillDtl;
	}

	public void setStrBillDtl(String strBillDtl) {
		this.strBillDtl = strBillDtl;
	}*/

	public String getStrTransNo() {
		return strTransNo;
	}

	public void setStrTransNo(String strTransNo) {
		this.strTransNo = strTransNo;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	/*public String getStrBillDtlWithNo() {
		return strBillDtlWithNo;
	}

	public void setStrBillDtlWithNo(String strBillDtlWithNo) {
		this.strBillDtlWithNo = strBillDtlWithNo;
	}*/

	public String getStrFromDate() {
		return strFromDate;
	}

	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}

	public String getStrToDate() {
		return strToDate;
	}

	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}

	public String getStrHosCode() {
		return strHosCode;
	}

	public void setStrHosCode(String strHosCode) {
		this.strHosCode = strHosCode;
	}

	/*public String getStrPageNo() {
		return strPageNo;
	}

	public void setStrPageNo(String strPageNo) {
		this.strPageNo = strPageNo;
	}

	public String getStrGuarantorName() {
		return strGuarantorName;
	}

	public void setStrGuarantorName(String strGuarantorName) {
		this.strGuarantorName = strGuarantorName;
	}
*/
	public String getStrCase() {
		return strCase;
	}

	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}

	public String getStrBillUsrFuncName() {
		return strBillUsrFuncName;
	}

	public void setStrBillUsrFuncName(String strBillUsrFuncName) {
		this.strBillUsrFuncName = strBillUsrFuncName;
	}

	public String getStrBillSearchString() {
		return strBillSearchString;
	}

	public void setStrBillSearchString(String strBillSearchString) {
		this.strBillSearchString = strBillSearchString;
	}

	public String getStrBillFromRow() {
		return strBillFromRow;
	}

	public void setStrBillFromRow(String strBillFromRow) {
		this.strBillFromRow = strBillFromRow;
	}

	public String getStrBillToRow() {
		return strBillToRow;
	}

	public void setStrBillToRow(String strBillToRow) {
		this.strBillToRow = strBillToRow;
	}

	public String getStrBillRowPerPage() {
		return strBillRowPerPage;
	}

	public void setStrBillRowPerPage(String strBillRowPerPage) {
		this.strBillRowPerPage = strBillRowPerPage;
	}

	public String getStrBillCtBlockSet() {
		return strBillCtBlockSet;
	}

	public void setStrBillCtBlockSet(String strBillCtBlockSet) {
		this.strBillCtBlockSet = strBillCtBlockSet;
	}

	public WebRowSet getBillSearchList() {
		return billSearchList;
	}

	public void setBillSearchList(WebRowSet billSearchList) {
		this.billSearchList = billSearchList;
	}

	public String getStrPatientDtls() {
		return strPatientDtls;
	}

	public void setStrPatientDtls(String strPatientDtls) {
		this.strPatientDtls = strPatientDtls;
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

	public String getStrBillDetl() {
		return strBillDetl;
	}

	public void setStrBillDetl(String strBillDetl) {
		this.strBillDetl = strBillDetl;
	}

	public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}

	public String getStrPukNo() {
		return strPukNo;
	}

	public void setStrPukNo(String strPukNo) {
		this.strPukNo = strPukNo;
	}

	public String getStrPatientName() {
		return strPatientName;
	}

	public void setStrPatientName(String strPatientName) {
		this.strPatientName = strPatientName;
	}

	public String getStrCategoryName() {
		return strCategoryName;
	}

	public void setStrCategoryName(String strCategoryName) {
		this.strCategoryName = strCategoryName;
	}

	public String getStrBillService() {
		return strBillService;
	}

	public void setStrBillService(String strBillService) {
		this.strBillService = strBillService;
	}

	public String getStrRePrintChg() {
		return strRePrintChg;
	}

	public void setStrRePrintChg(String strRePrintChg) {
		this.strRePrintChg = strRePrintChg;
	}

	public String getStrBServiceId() {
		return strBServiceId;
	}

	public void setStrBServiceId(String strBServiceId) {
		this.strBServiceId = strBServiceId;
	}

	public String getStrChargeTypeID() {
		return strChargeTypeID;
	}

	public void setStrChargeTypeID(String strChargeTypeID) {
		this.strChargeTypeID = strChargeTypeID;
	}

	public String getStrPatientCatCode() {
		return strPatientCatCode;
	}

	public void setStrPatientCatCode(String strPatientCatCode) {
		this.strPatientCatCode = strPatientCatCode;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
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

	public String getStrPatActNo() {
		return strPatActNo;
	}

	public void setStrPatActNo(String strPatActNo) {
		this.strPatActNo = strPatActNo;
	}

	public String getStrAdmnNo() {
		return strAdmnNo;
	}

	public void setStrAdmnNo(String strAdmnNo) {
		this.strAdmnNo = strAdmnNo;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrWardCode() {
		return strWardCode;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}

	public String getStrBillTypeCombo() {
		return strBillTypeCombo;
	}

	public void setStrBillTypeCombo(String strBillTypeCombo) {
		this.strBillTypeCombo = strBillTypeCombo;
	}

	/**
	 * @return the strTempBillNo
	 */
	public String getStrTempBillNo() {
		return strTempBillNo;
	}

	/**
	 * @param strTempBillNo the strTempBillNo to set
	 */
	public void setStrTempBillNo(String strTempBillNo) {
		this.strTempBillNo = strTempBillNo;
	}

	/**
	 * @return the strTempRcptNo
	 */
	public String getStrTempRcptNo() {
		return strTempRcptNo;
	}

	/**
	 * @param strTempRcptNo the strTempRcptNo to set
	 */
	public void setStrTempRcptNo(String strTempRcptNo) {
		this.strTempRcptNo = strTempRcptNo;
	}

	/**
	 * @return the strWarningMsg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	/**
	 * @param strWarningMsg the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}

	/**
	 * @param strNormalMsg the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	public String getStrPrintFlag() {
		return strPrintFlag;
	}

	public void setStrPrintFlag(String strPrintFlag) {
		this.strPrintFlag = strPrintFlag;
	}

	public String getStrRcptType() {
		return strRcptType;
	}

	public void setStrRcptType(String strRcptType) {
		this.strRcptType = strRcptType;
	}

	public String getStrSearchType() {
		return strSearchType;
	}

	public void setStrSearchType(String strSearchType) {
		this.strSearchType = strSearchType;
	}

	public String getStrSearchString() {
		return strSearchString;
	}

	public void setStrSearchString(String strSearchString) {
		this.strSearchString = strSearchString;
	}

	public String getStrPrintMessageLimit() {
		return strPrintMessageLimit;
	}

	public void setStrPrintMessageLimit(String strPrintMessageLimit) {
		this.strPrintMessageLimit = strPrintMessageLimit;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getIsOpenPopUp() {
		return isOpenPopUp;
	}

	public void setIsOpenPopUp(String isOpenPopUp) {
		this.isOpenPopUp = isOpenPopUp;
	}

	public String getStrReceiptType() {
		return strReceiptType;
	}

	public void setStrReceiptType(String strReceiptType) {
		this.strReceiptType = strReceiptType;
	}

	public String getStrReceiptTypevalues() {
		return strReceiptTypevalues;
	}

	public void setStrReceiptTypevalues(String strReceiptTypevalues) {
		this.strReceiptTypevalues = strReceiptTypevalues;
	}

	public WebRowSet getReceiptTypeWb() {
		return receiptTypeWb;
	}

	public void setReceiptTypeWb(WebRowSet receiptTypeWb) {
		this.receiptTypeWb = receiptTypeWb;
	}

	public String getStrReceiptTypeLabel() {
		return strReceiptTypeLabel;
	}

	public void setStrReceiptTypeLabel(String strReceiptTypeLabel) {
		this.strReceiptTypeLabel = strReceiptTypeLabel;
	}

	public String getTemp_wallet() {
		return temp_wallet;
	}

	public void setTemp_wallet(String temp_wallet) {
		this.temp_wallet = temp_wallet;
	}

}
