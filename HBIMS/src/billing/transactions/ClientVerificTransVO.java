package billing.transactions;

import hisglobal.utility.HisUtil;
import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class ClientVerificTransVO implements TransferObject
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;
	private String strClientName  = "";
	private String strTotBill = "";
	private String strCopy = "";
	private String strTotHidd = "";
	private String strTotAmt = "";
	private String strClientAdd  = "";
	private String strApprovedFor = "";
	private String strClientType  = "";
	private String strPaymentType = "";
	private String strRegNo       = "";
	
	private String strContactPerson = "";
	private String strContacNo      = "";
	private String strClientID   = "";
	private String strCrNo = "";
	private String strPk = "";
	private String strCrComboDtl = "";
	private String strBalanceAmt = "";
	
	/************Added Data**************/
	private String strName = "";
	private String strAddress = "";
	private String strPhoneNo = "";
	private String strBookNo = "";
	private String strInvoice = "";
	private String strTransNo = "";
	private String strDLNo = "";
	private String strTINNo = "";
	private String strGrName = "";
	private String strNoc = "";
	private String strTax ="";
	private String strTotRs ="";
	private String strTotHiddRs ="";
	private String strFinalMul= "";
	private String strGRDate = "";
	
	private String[]  strCat ={"0"};
	private String[]  strPkg ={"0"};
	private String[]  strBatch ={"0"};
	private String[]  strExpiry ={"0"};
	private String[]  strQty ={"0"};
	private String[]  strQtyFinal ={"0"};
	private String[]  strPTW ={"0"};
	private String[]  strPTR={"0"};
	private String[]  strMrp ={"0"};
	private String[]  strAmt ={"0"};
	/****************************************/
	
	
	
	
	
	
	
	private String strIsOPD = "";
	private String strIsIPD = "";
	private String strIsEME = "";
	private String strDepositAmt = "";
	private String strPaymentMode ="";
	private String strPaymentModeDetl = "";
	
	private String strReOrderLevel = "";
	private String strCrBilling = "";
	private String strBgAmount ="";
	private String strRenewalDtl ="";
	private String strBankName ="";
	private String strBankAddress = "";
	private String strRemarks = null;
	private String strCtDate = null;
	private String strEffectiveFrmDate = null;
	private String strExpiryDate = null;
	private String strChk ="";
	private String strRenewalDate = "";
	private String strBranchName ="";
	private String strInvoiceComboDtl = "";
	
	
	private String strDepositAmount="";
	private String strPaymentModeDtl="";
	
	private String strOPDDiscount= "";
	private String strIPDDiscount="";
	private String strEMEDiscount="";
	
	private String strBillNo="";
	private String strBillDate="";	
	private String strService="";
	private String strBillAmt="";
	private String strOldExpiryDate = "";
	private String strBgReNewalDate = "";
	
	
	private String msgStr = "";
	private String msgType = "";
	private String MsgString = "";
	
	private WebRowSet ws = null;
	
	private String strInvoiceNo = "";
	private String strInvoiceDate = "";
	private String strInvoiceAmt = "";
	private String strCRNO = "";
	private String strCardNo = "";
	private String strCardHolderName = "";
	
	private String strAutNo = "";
	private String strAuthDate = "";
	private String strSancAmt = "";
	private String strRecAmt = "";
	private String strExpenseAmt = "";
	
	private String strPatientName = "";
	private String strErr = "";
	
	private String strTotalDepositAmt = "";
	private String strTotalExpense = "";
	private String strRemainedAmt = "";
	private String strPymentModeDetails = "";
	
	private String strMsgString = "";
	private String strMsgType = "0";
	private String strValid = null;
	
	private WebRowSet strClientDetailWs = null;
	private WebRowSet strInvoiceDetailsWs = null;
	
	
	

	public WebRowSet getStrInvoiceDetailsWs() {
		return strInvoiceDetailsWs;
	}

	public void setStrInvoiceDetailsWs(WebRowSet strInvoiceDetailsWs) {
		this.strInvoiceDetailsWs = strInvoiceDetailsWs;
	}

	public String getStrCtDate()
	{ //function to Get SYSDATE
		HisUtil util = new HisUtil("Client Type Master", "VOClientTypeMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			
		}
		
		return strCtDate;
	}
	
	public String getStrCopy() {
		return strCopy;
	}

	public void setStrCopy(String strCopy) {
		this.strCopy = strCopy;
	}
	public String getStrTotHidd() {
		return strTotHidd;
	}

	public void setStrTotHidd(String strTotHidd) {
		this.strTotHidd = strTotHidd;
	}
	public String getStrTotBill() {
		return strTotBill;
	}

	public void setStrTotBill(String strTotBill) {
		this.strTotBill = strTotBill;
	}
	
	public String getStrTotAmt() {
		return strTotAmt;
	}

	public void setStrTotAmt(String strTotAmt) {
		this.strTotAmt = strTotAmt;
	}
	public void setStrEffectiveFrmDate(String strEffectiveFrmDate)
	{
		this.strEffectiveFrmDate = strEffectiveFrmDate;
	}
	public String getStrEffectiveFrmDate() 
	{
	    if(this.strEffectiveFrmDate == null){
	    HisUtil util = new HisUtil("Client Verification Transaction","ClientVerificationTransFB");
	      try {
	    	  strEffectiveFrmDate = util.getASDate("dd-MMM-yyyy");
	       util = null;
	      } catch (Exception e) {
	      e.printStackTrace();
	      }
	    }
	     return strEffectiveFrmDate;
	   }

	public String getStrClientName() {
		return strClientName;
	}

	public void setStrClientName(String strClientName) {
		this.strClientName = strClientName;
	}

	public String getStrClientType() {
		return strClientType;
	}

	public void setStrClientType(String strClientType) {
		this.strClientType = strClientType;
	}

	public String getStrPaymentType() {
		return strPaymentType;
	}

	public void setStrPaymentType(String strPaymentType) {
		this.strPaymentType = strPaymentType;
	}

	public String getStrRegNo() {
		return strRegNo;
	}

	public void setStrRegNo(String strRegNo) {
		this.strRegNo = strRegNo;
	}

	public String getStrAddress() {
		return strAddress;
	}

	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}

	public String getStrContactPerson() {
		return strContactPerson;
	}

	public void setStrContactPerson(String strContactPerson) {
		this.strContactPerson = strContactPerson;
	}

	public String getStrContacNo() {
		return strContacNo;
	}

	public void setStrContacNo(String strContacNo) {
		this.strContacNo = strContacNo;
	}

	

	public String getStrIsOPD() {
		return strIsOPD;
	}

	public void setStrIsOPD(String strIsOPD) {
		this.strIsOPD = strIsOPD;
	}

	public String getStrIsIPD() {
		return strIsIPD;
	}

	public void setStrIsIPD(String strIsIPD) {
		this.strIsIPD = strIsIPD;
	}

	public String getStrIsEME() {
		return strIsEME;
	}

	public void setStrIsEME(String strIsEME) {
		this.strIsEME = strIsEME;
	}

	public String getStrDepositAmt() {
		return strDepositAmt;
	}

	public void setStrDepositAmt(String strDepositAmt) {
		this.strDepositAmt = strDepositAmt;
	}

	public String getStrPaymentMode() {
		return strPaymentMode;
	}

	public void setStrPaymentMode(String strPaymentMode) {
		this.strPaymentMode = strPaymentMode;
	}

	public String getStrPaymentModeDetl() {
		return strPaymentModeDetl;
	}

	public void setStrPaymentModeDetl(String strPaymentModeDetl) {
		this.strPaymentModeDetl = strPaymentModeDetl;
	}

	public String getStrReOrderLevel() {
		return strReOrderLevel;
	}

	public void setStrReOrderLevel(String strReOrderLevel) {
		this.strReOrderLevel = strReOrderLevel;
	}

	public String getStrCrBilling() {
		return strCrBilling;
	}

	public void setStrCrBilling(String strCrBilling) {
		this.strCrBilling = strCrBilling;
	}

	public String getStrBgAmount() {
		return strBgAmount;
	}

	public void setStrBgAmount(String strBgAmount) {
		this.strBgAmount = strBgAmount;
	}

	public String getStrRenewalDtl() {
		return strRenewalDtl;
	}

	public void setStrRenewalDtl(String strRenewalDtl) {
		this.strRenewalDtl = strRenewalDtl;
	}

	public String getStrBankName() {
		return strBankName;
	}

	public void setStrBankName(String strBankName) {
		this.strBankName = strBankName;
	}

	public String getStrBankAddress() {
		return strBankAddress;
	}

	public void setStrBankAddress(String strBankAddress) {
		this.strBankAddress = strBankAddress;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrExpiryDate() {
		return strExpiryDate;
	}

	public void setStrExpiryDate(String strExpiryDate) {
		this.strExpiryDate = strExpiryDate;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
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

	public String getStrValid() {
		return strValid;
	}

	public void setStrValid(String strValid) {
		this.strValid = strValid;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getStrInvoiceNo() {
		return strInvoiceNo;
	}

	public void setStrInvoiceNo(String strInvoiceNo) {
		this.strInvoiceNo = strInvoiceNo;
	}

	public String getStrInvoiceDate() {
		return strInvoiceDate;
	}

	public void setStrInvoiceDate(String strInvoiceDate) {
		this.strInvoiceDate = strInvoiceDate;
	}

	public String getStrInvoiceAmt() {
		return strInvoiceAmt;
	}

	public void setStrInvoiceAmt(String strInvoiceAmt) {
		this.strInvoiceAmt = strInvoiceAmt;
	}

	public String getStrCRNO() {
		return strCRNO;
	}

	public void setStrCRNO(String strCRNO) {
		this.strCRNO = strCRNO;
	}

	public String getStrPatientName() {
		return strPatientName;
	}

	public void setStrPatientName(String strPatientName) {
		this.strPatientName = strPatientName;
	}

	public String getStrTotalDepositAmt() {
		return strTotalDepositAmt;
	}

	public void setStrTotalDepositAmt(String strTotalDepositAmt) {
		this.strTotalDepositAmt = strTotalDepositAmt;
	}

	public String getStrTotalExpense() {
		return strTotalExpense;
	}

	public void setStrTotalExpense(String strTotalExpense) {
		this.strTotalExpense = strTotalExpense;
	}

	public String getStrRemainedAmt() {
		return strRemainedAmt;
	}

	public void setStrRemainedAmt(String strRemainedAmt) {
		this.strRemainedAmt = strRemainedAmt;
	}

	public String getStrDepositAmount() {
		return strDepositAmount;
	}

	public void setStrDepositAmount(String strDepositAmount) {
		this.strDepositAmount = strDepositAmount;
	}

	public String getStrPymentModeDetails() {
		return strPymentModeDetails;
	}

	public void setStrPymentModeDetails(String strPymentModeDetails) {
		this.strPymentModeDetails = strPymentModeDetails;
	}

	public WebRowSet getStrClientDetailWs() {
		return strClientDetailWs;
	}

	public void setStrClientDetailWs(WebRowSet strClientDetailWs) {
		this.strClientDetailWs = strClientDetailWs;
	}

	public String getStrClientID() {
		return strClientID;
	}

	public void setStrClientID(String strClientID) {
		this.strClientID = strClientID;
	}

	public String getStrPaymentModeDtl() {
		return strPaymentModeDtl;
	}

	public void setStrPaymentModeDtl(String strPaymentModeDtl) {
		this.strPaymentModeDtl = strPaymentModeDtl;
	}

	public String getStrRenewalDate() {
		return strRenewalDate;
	}

	public void setStrRenewalDate(String strRenewalDate) {
		this.strRenewalDate = strRenewalDate;
	}

	public String getStrOPDDiscount() {
		return strOPDDiscount;
	}

	public void setStrOPDDiscount(String strOPDDiscount) {
		this.strOPDDiscount = strOPDDiscount;
	}

	public String getStrIPDDiscount() {
		return strIPDDiscount;
	}

	public void setStrIPDDiscount(String strIPDDiscount) {
		this.strIPDDiscount = strIPDDiscount;
	}

	public String getStrEMEDiscount() {
		return strEMEDiscount;
	}

	public void setStrEMEDiscount(String strEMEDiscount) {
		this.strEMEDiscount = strEMEDiscount;
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

	public WebRowSet getWs() {
		return ws;
	}

	public void setWs(WebRowSet ws) {
		this.ws = ws;
	}

	public String getStrBranchName() {
		return strBranchName;
	}

	public void setStrBranchName(String strBranchName) {
		this.strBranchName = strBranchName;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public String getStrPk() {
		return strPk;
	}

	public void setStrPk(String strPk) {
		this.strPk = strPk;
	}

	public String getStrCrComboDtl() {
		return strCrComboDtl;
	}

	public void setStrCrComboDtl(String strCrComboDtl) {
		this.strCrComboDtl = strCrComboDtl;
	}

	public String getStrCardNo() {
		return strCardNo;
	}

	public void setStrCardNo(String strCardNo) {
		this.strCardNo = strCardNo;
	}

	public String getStrCardHolderName() {
		return strCardHolderName;
	}

	public void setStrCardHolderName(String strCardHolderName) {
		this.strCardHolderName = strCardHolderName;
	}

	

	public String getStrAutNo() {
		return strAutNo;
	}

	public void setStrAutNo(String strAutNo) {
		this.strAutNo = strAutNo;
	}

	public String getStrAuthDate() {
		return strAuthDate;
	}

	public void setStrAuthDate(String strAuthDate) {
		this.strAuthDate = strAuthDate;
	}

	public String getStrSancAmt() {
		return strSancAmt;
	}

	public void setStrSancAmt(String strSancAmt) {
		this.strSancAmt = strSancAmt;
	}

	public String getStrRecAmt() {
		return strRecAmt;
	}

	public void setStrRecAmt(String strRecAmt) {
		this.strRecAmt = strRecAmt;
	}

	public String getStrExpenseAmt() {
		return strExpenseAmt;
	}

	public void setStrExpenseAmt(String strExpenseAmt) {
		this.strExpenseAmt = strExpenseAmt;
	}

	public String getStrClientAdd() {
		return strClientAdd;
	}

	public void setStrClientAdd(String strClientAdd) {
		this.strClientAdd = strClientAdd;
	}

	public String getStrApprovedFor() {
		return strApprovedFor;
	}

	public void setStrApprovedFor(String strApprovedFor) {
		this.strApprovedFor = strApprovedFor;
	}

	public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}

	public String getStrBillDate() {
		return strBillDate;
	}

	public void setStrBillDate(String strBillDate) {
		this.strBillDate = strBillDate;
	}

	public String getStrService() {
		return strService;
	}

	public void setStrService(String strService) {
		this.strService = strService;
	}

	public String getStrBillAmt() {
		return strBillAmt;
	}

	public void setStrBillAmt(String strBillAmt) {
		this.strBillAmt = strBillAmt;
	}

	public String getStrErr() {
		return strErr;
	}

	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}

	public String getStrInvoiceComboDtl() {
		return strInvoiceComboDtl;
	}

	public void setStrInvoiceComboDtl(String strInvoiceComboDtl) {
		this.strInvoiceComboDtl = strInvoiceComboDtl;
	}

	public String getStrBalanceAmt() {
		return strBalanceAmt;
	}

	public void setStrBalanceAmt(String strBalanceAmt) {
		this.strBalanceAmt = strBalanceAmt;
	}

	public String getStrOldExpiryDate() {
		return strOldExpiryDate;
	}

	public void setStrOldExpiryDate(String strOldExpiryDate) {
		this.strOldExpiryDate = strOldExpiryDate;
	}

	public String getStrBgReNewalDate() {
		return strBgReNewalDate;
	}

	public void setStrBgReNewalDate(String strBgReNewalDate) {
		this.strBgReNewalDate = strBgReNewalDate;
	}

	public String getStrTransNo() {
		return strTransNo;
	}

	public void setStrTransNo(String strTransNo) {
		this.strTransNo = strTransNo;
	}

	public String getStrDLNo() {
		return strDLNo;
	}

	public void setStrDLNo(String strDLNo) {
		this.strDLNo = strDLNo;
	}

	public String getStrTINNo() {
		return strTINNo;
	}

	public void setStrTINNo(String strTINNo) {
		this.strTINNo = strTINNo;
	}

	public String getStrGrName() {
		return strGrName;
	}

	public void setStrGrName(String strGrName) {
		this.strGrName = strGrName;
	}

	public String getStrNoc() {
		return strNoc;
	}

	public void setStrNoc(String strNoc) {
		this.strNoc = strNoc;
	}

	public String getStrTax() {
		return strTax;
	}

	public void setStrTax(String strTax) {
		this.strTax = strTax;
	}

	public String[] getStrCat() {
		return strCat;
	}

	public void setStrCat(String[] strCat) {
		this.strCat = strCat;
	}

	public String[] getStrPkg() {
		return strPkg;
	}

	public void setStrPkg(String[] strPkg) {
		this.strPkg = strPkg;
	}

	public String[] getStrExpiry() {
		return strExpiry;
	}

	public void setStrExpiry(String[] strExpiry) {
		this.strExpiry = strExpiry;
	}

	public String[] getStrMrp() {
		return strMrp;
	}

	public void setStrMrp(String[] strMrp) {
		this.strMrp = strMrp;
	}

	public String[] getStrAmt() {
		return strAmt;
	}

	public void setStrAmt(String[] strAmt) {
		this.strAmt = strAmt;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrPhoneNo() {
		return strPhoneNo;
	}

	public void setStrPhoneNo(String strPhoneNo) {
		this.strPhoneNo = strPhoneNo;
	}

	public String getStrBookNo() {
		return strBookNo;
	}

	public void setStrBookNo(String strBookNo) {
		this.strBookNo = strBookNo;
	}

	public String getStrInvoice() {
		return strInvoice;
	}

	public void setStrInvoice(String strInvoice) {
		this.strInvoice = strInvoice;
	}

	public String[] getStrBatch() {
		return strBatch;
	}

	public void setStrBatch(String[] strBatch) {
		this.strBatch = strBatch;
	}

	public String[] getStrQty() {
		return strQty;
	}

	public void setStrQty(String[] strQty) {
		this.strQty = strQty;
	}

	public String[] getStrQtyFinal() {
		return strQtyFinal;
	}

	public void setStrQtyFinal(String[] strQtyFinal) {
		this.strQtyFinal = strQtyFinal;
	}

	public String[] getStrPTW() {
		return strPTW;
	}

	public void setStrPTW(String[] strPTW) {
		this.strPTW = strPTW;
	}

	public String[] getStrPTR() {
		return strPTR;
	}

	public void setStrPTR(String[] strPTR) {
		this.strPTR = strPTR;
	}

	public String getStrTotRs() {
		return strTotRs;
	}

	public void setStrTotRs(String strTotRs) {
		this.strTotRs = strTotRs;
	}

	public String getStrTotHiddRs() {
		return strTotHiddRs;
	}

	public void setStrTotHiddRs(String strTotHiddRs) {
		this.strTotHiddRs = strTotHiddRs;
	}

	public String getStrFinalMul() {
		return strFinalMul;
	}

	public void setStrFinalMul(String strFinalMul) {
		this.strFinalMul = strFinalMul;
	}

	public String getStrGRDate() {
		return strGRDate;
	}

	public void setStrGRDate(String strGRDate) {
		this.strGRDate = strGRDate;
	}

	
}
