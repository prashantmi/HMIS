package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class RefundApprovalTransVO implements TransferObject
{
	
	private static final long serialVersionUID = 02L;
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqRefundWs = null;
	private WebRowSet strUnitCmb = null;
	private WebRowSet strTrfDtls = null;
	private WebRowSet strBillDtls = null;
	private WebRowSet StrCancelReasonWs = null;
	private WebRowSet strCancelledBy = null;
	
    private String strMsgString = null;
    private String strMsgType = "";   
    private String strChk ="";
    private String strCrNo = "";
    private String strCrNo1 = "";
    private String strBId ="";
    private String strApproval_id="";
    private String strExpnseAmt ="";
    private String strRecFrClnt ="";
    private String lastDis ="";
    private String strBServiceId="";
  //  private String RefundType ="";
    private String strDisAmt ="";
    private String strRecFrPat ="";
    private String strNetAmt ="";
    private String strDetls="";
    private WebRowSet strDisBy =null;
    private WebRowSet strDisRsn =null;
    private String strRsn="";
    private String strRmk="";
    private String strAccPreDis ="";
    private String hspServ="";
    private String strBill = "";
    private String strUnit = "";
    private String strHospitalCode = "";
    private String strUserLevel = "";
    private String strValmode = "";
    private String strPenalty= "";
    private String strHospServ= "";
    private String strUnitCmbVal= "";
    private String strReqNo ="";
    
    private String strChkValues= "";
    private String strSeatId= "";
    private String strRefBy = "";
    private String StrErrMsg = "";
    private String strMsg = "";
    // variables added by anshul
    private String strOtherReason="";
    private String strBillNo = "";
    private String strPatAccNo ="";
    private String strApprovalId ="";
	
	private String refundType[] = null;
	private String trf_penalty[] = null;
	private String refundQty[] = null;
	private String strUnitId[] = null;
	private String refundAmt[] = null;
	private String HidRefundQty[] = null;
	private String strCtDate = "";
	
	 private String strRefundMode = "0";
	
	//
	private String[] chk = null;
    private String[] chkHidd = null;
    
    //credit fields
    private String[] clNo={};
    private String[] clDate={};
    private String[] clPath={};
    private String[] clientNo={};
    private String[] amtByPat={};
    private String[] amtByClient={};
    private String strProcessFeePenalty = "";
    
    
	public String getStrProcessFeePenalty() {
		return strProcessFeePenalty;
	}



	public void setStrProcessFeePenalty(String strProcessFeePenalty) {
		this.strProcessFeePenalty = strProcessFeePenalty;
	}



	public String getStrCtDate() 
	{
		HisUtil util = new HisUtil("Drug Safty Alert Master", "SparePartsMstFB");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("MMS Module", "Spare Parts Master",
					"SparePartsMstFB.getStrCtDate()-->" + e.getMessage());
		}
		return strCtDate;

	}
	
    
    
    public String[] getHidRefundQty() {
		return HidRefundQty;
	}

	public void setHidRefundQty(String[] hidRefundQty) {
		HidRefundQty = hidRefundQty;
	}

	public String getHspServ() {
		return hspServ;
	}

	public void setHspServ(String hspServ) {
		this.hspServ = hspServ;
	}

	public String getStrBId() {
		return strBId;
	}

	public void setStrBId(String strBId) {
		this.strBId = strBId;
	}

	public String getStrExpnseAmt() {
		return strExpnseAmt;
	}

	public void setStrExpnseAmt(String strExpnseAmt) {
		this.strExpnseAmt = strExpnseAmt;
	}

	public String getStrRecFrClnt() {
		return strRecFrClnt;
	}

	public void setStrRecFrClnt(String strRecFrClnt) {
		this.strRecFrClnt = strRecFrClnt;
	}

	

	public String getStrDisAmt() {
		return strDisAmt;
	}

	public void setStrDisAmt(String strDisAmt) {
		this.strDisAmt = strDisAmt;
	}

	public String getStrRecFrPat() {
		return strRecFrPat;
	}

	public void setStrRecFrPat(String strRecFrPat) {
		this.strRecFrPat = strRecFrPat;
	}

	public String getStrNetAmt() {
		return strNetAmt;
	}

	public void setStrNetAmt(String strNetAmt) {
		this.strNetAmt = strNetAmt;
	}

	public String getStrDetls() {
		return strDetls;
	}

	public void setStrDetls(String strDetls) {
		this.strDetls = strDetls;
	}

	

	

	public WebRowSet getStrDisBy() {
		return strDisBy;
	}

	public void setStrDisBy(WebRowSet strDisBy) {
		this.strDisBy = strDisBy;
	}

	public WebRowSet getStrDisRsn() {
		return strDisRsn;
	}

	public void setStrDisRsn(WebRowSet strDisRsn) {
		this.strDisRsn = strDisRsn;
	}

	public String getStrAccPreDis() {
		return strAccPreDis;
	}

	public void setStrAccPreDis(String strAccPreDis) {
		this.strAccPreDis = strAccPreDis;
	}

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
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

	public WebRowSet getStrPatientDetailsWs() {
		return strPatientDetailsWs;
	}

	public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
		this.strPatientDetailsWs = strPatientDetailsWs;
	}

	public WebRowSet getStrOnLineReqRefundWs() {
		return strOnLineReqRefundWs;
	}

	public void setStrOnLineReqRefundWs(WebRowSet strOnLineReqRefundWs) {
		this.strOnLineReqRefundWs = strOnLineReqRefundWs;
	}

	public String getStrCrNo1() {
		return strCrNo1;
	}

	public void setStrCrNo1(String strCrNo1) {
		this.strCrNo1 = strCrNo1;
	}

	public String getStrApproval_id() {
		return strApproval_id;
	}

	public void setStrApproval_id(String strApproval_id) {
		this.strApproval_id = strApproval_id;
	}

	public String getLastDis() {
		return lastDis;
	}

	public void setLastDis(String lastDis) {
		this.lastDis = lastDis;
	}


	public String getStrRsn() {
		return strRsn;
	}

	public void setStrRsn(String strRsn) {
		this.strRsn = strRsn;
	}

	public String getStrRmk() {
		return strRmk;
	}

	public void setStrRmk(String strRmk) {
		this.strRmk = strRmk;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStrOtherReason() {
		return strOtherReason;
	}

	public void setStrOtherReason(String strOtherReason) {
		this.strOtherReason = strOtherReason;
	}

	public String getStrBill() {
		return strBill;
	}

	public void setStrBill(String strBill) {
		this.strBill = strBill;
	}

	

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrValmode() {
		return strValmode;
	}

	public void setStrValmode(String strValmode) {
		this.strValmode = strValmode;
	}

	public WebRowSet getStrUnitCmb() {
		return strUnitCmb;
	}

	public void setStrUnitCmb(WebRowSet strUnitCmb) {
		this.strUnitCmb = strUnitCmb;
	}

	public WebRowSet getStrTrfDtls() {
		return strTrfDtls;
	}

	public void setStrTrfDtls(WebRowSet strTrfDtls) {
		this.strTrfDtls = strTrfDtls;
	}

	public WebRowSet getStrBillDtls() {
		return strBillDtls;
	}

	public void setStrBillDtls(WebRowSet strBillDtls) {
		this.strBillDtls = strBillDtls;
	}

	public String getStrPenalty() {
		return strPenalty;
	}

	public void setStrPenalty(String strPenalty) {
		this.strPenalty = strPenalty;
	}

	public String getStrHospServ() {
		return strHospServ;
	}

	public void setStrHospServ(String strHospServ) {
		this.strHospServ = strHospServ;
	}

	public WebRowSet getStrCancelReasonWs() {
		return StrCancelReasonWs;
	}

	public void setStrCancelReasonWs(WebRowSet strCancelReasonWs) {
		StrCancelReasonWs = strCancelReasonWs;
	}

	public WebRowSet getStrCancelledBy() {
		return strCancelledBy;
	}

	public void setStrCancelledBy(WebRowSet strCancelledBy) {
		this.strCancelledBy = strCancelledBy;
	}

	public String getStrUnitCmbVal() {
		return strUnitCmbVal;
	}

	public void setStrUnitCmbVal(String strUnitCmbVal) {
		this.strUnitCmbVal = strUnitCmbVal;
	}

	public String getStrChkValues() {
		return strChkValues;
	}

	public void setStrChkValues(String strChkValues) {
		this.strChkValues = strChkValues;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrRefBy() {
		return strRefBy;
	}

	public void setStrRefBy(String strRefBy) {
		this.strRefBy = strRefBy;
	}

	public String getStrErrMsg() {
		return StrErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		StrErrMsg = strErrMsg;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrUnit() {
		return strUnit;
	}

	public void setStrUnit(String strUnit) {
		this.strUnit = strUnit;
	}

	public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}

	public String[] getTrf_penalty() {
		return trf_penalty;
	}

	public void setTrf_penalty(String[] trf_penalty) {
		this.trf_penalty = trf_penalty;
	}

	public String[] getRefundQty() {
		return refundQty;
	}

	public void setRefundQty(String[] refundQty) {
		this.refundQty = refundQty;
	}

	public String[] getStrUnitId() {
		return strUnitId;
	}

	public void setStrUnitId(String[] strUnitId) {
		this.strUnitId = strUnitId;
	}

	public String[] getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(String[] refundAmt) {
		this.refundAmt = refundAmt;
	}

	public void setRefundType(String[] refundType) {
		this.refundType = refundType;
	}

	public String[] getRefundType() {
		return refundType;
	}

	public String getStrUserLevel() {
		return strUserLevel;
	}

	public void setStrUserLevel(String strUserLevel) {
		this.strUserLevel = strUserLevel;
	}



	public String getStrReqNo() {
		return strReqNo;
	}



	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}



	public String getStrPatAccNo() {
		return strPatAccNo;
	}



	public void setStrPatAccNo(String strPatAccNo) {
		this.strPatAccNo = strPatAccNo;
	}



	public String getStrApprovalId() {
		return strApprovalId;
	}



	public void setStrApprovalId(String strApprovalId) {
		this.strApprovalId = strApprovalId;
	}



	public String getStrBServiceId() {
		return strBServiceId;
	}



	public void setStrBServiceId(String strBServiceId) {
		this.strBServiceId = strBServiceId;
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



	/**
	 * @return the chkHidd
	 */
	public String[] getChkHidd() {
		return chkHidd;
	}



	/**
	 * @param chkHidd the chkHidd to set
	 */
	public void setChkHidd(String[] chkHidd) {
		this.chkHidd = chkHidd;
	}



	public String getStrRefundMode() {
		return strRefundMode;
	}



	public void setStrRefundMode(String strRefundMode) {
		this.strRefundMode = strRefundMode;
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



	public String[] getClPath() {
		return clPath;
	}



	public void setClPath(String[] clPath) {
		this.clPath = clPath;
	}



	



	public String[] getAmtByPat() {
		return amtByPat;
	}



	public void setAmtByPat(String[] amtByPat) {
		this.amtByPat = amtByPat;
	}



	public String[] getAmtByClient() {
		return amtByClient;
	}



	public void setAmtByClient(String[] amtByClient) {
		this.amtByClient = amtByClient;
	}



	public String[] getClientNo() {
		return clientNo;
	}



	public void setClientNo(String[] clientNo) {
		this.clientNo = clientNo;
	}

	

	

}
