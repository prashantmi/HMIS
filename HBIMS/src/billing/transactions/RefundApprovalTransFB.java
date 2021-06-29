package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;

import javax.sql.rowset.WebRowSet;

import org.apache.struts.action.ActionForm;

public class RefundApprovalTransFB extends ActionForm {

	private static final long serialVersionUID = 02L;
	
	private String strWarningMsg = "";
	private String strNormalMsg = "";
	

	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqRefundWs = null;
	private String strMsgString = null;
	
	private String strSeatId = "";
	
	private String strGlbErrMsgTLD = "";
	private String strMsgType = "";
	private String strChk = "";
	private String tmpMsg = "";
	private String strCrNo = "";
	private String strCrNo1 = "";
	private String strBId = "";
	private String strExpnseAmt = "";
	private String strRecFrClnt = "";
	private String strLstDisAmt = "";
	private String strDisAmt = "";
	private String strRecFrPat = "";
	private String strNetAmt = "";
	private String strDetls = "";
	private WebRowSet strDisBy = null;
	private WebRowSet strDisRsn = null;
	private String strRsn = "";
	private String strRmk = "";
	private String strAccPreDis = "";
	private String strApproval_id = "";
	private String strBill = "";
	//private String strUnitId = "";
	private String strHospitalCode = "";
	private String strHospServ = "";
	private String StrErrMsg = "";
	private String strMsg = "";
	private String strPatientDtls = "";
	private String strOtherReason = "";
	private String strRefBy = "";
	private String strReqNo ="";
	private String strBServiceId="";
	
	private String strIpdRefundPenaltyAmt = "0";
	private String strOpdRefundPenaltyAmt = "0";
	private String strEmergencyRefundPenaltyAmt = "0";
	
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
    private String strOnlineRefundRequestAllowed = "";
        
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
    private String filePath="";
	private String isOpenPopUp="";
	private String printMode="0";
	private String strBillDate = "";
	private String trf_grossRefund= "";
    
    
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

	public String getStrErrMsg() {
		return StrErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		StrErrMsg = strErrMsg;
	}

	public String getStrBill() {
		return strBill;
	}

	public void setStrBill(String strBill) {
		this.strBill = strBill;
	}

	public String getStrExpnseAmt() {
		return strExpnseAmt;
	}

	public void setStrExpnseAmt(String strExpnseAmt) {
		this.strExpnseAmt = strExpnseAmt;
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

	public String getStrBId() {
		return strBId;
	}

	public void setStrBId(String strBId) {
		this.strBId = strBId;
	}

	public String getStrRecFrClnt() {
		return strRecFrClnt;
	}

	public void setStrRecFrClnt(String strRecFrClnt) {
		this.strRecFrClnt = strRecFrClnt;
	}

	public String getStrLstDisAmt() {
		return strLstDisAmt;
	}

	public void setStrLstDisAmt(String strLstDisAmt) {
		this.strLstDisAmt = strLstDisAmt;
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

	public String getStrAccPreDis() {
		return strAccPreDis;
	}

	public void setStrAccPreDis(String strAccPreDis) {
		this.strAccPreDis = strAccPreDis;
	}

	public String getStrApproval_id() {
		return strApproval_id;
	}

	public void setStrApproval_id(String strApproval_id) {
		this.strApproval_id = strApproval_id;
	}

	public WebRowSet getStrDisRsn() {
		return strDisRsn;
	}

	public void setStrDisRsn(WebRowSet strDisRsn) {
		this.strDisRsn = strDisRsn;
	}

	public String getTmpMsg() {
		return tmpMsg;
	}

	public void setTmpMsg(String tmpMsg) {
		this.tmpMsg = tmpMsg;
	}

	public String getStrGlbErrMsgTLD() {
		return strGlbErrMsgTLD;
	}

	public void setStrGlbErrMsgTLD(String strGlbErrMsgTLD) {
		this.strGlbErrMsgTLD = strGlbErrMsgTLD;
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

	/*public String getStrUnitId() {
		return strUnitId;
	}

	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}*/

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrHospServ() {
		return strHospServ;
	}

	public void setStrHospServ(String strHospServ) {
		this.strHospServ = strHospServ;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrPatientDtls() {
		return strPatientDtls;
	}

	public void setStrPatientDtls(String strPatientDtls) {
		this.strPatientDtls = strPatientDtls;
	}

	public String getStrOtherReason() {
		return strOtherReason;
	}

	public void setStrOtherReason(String strOtherReason) {
		this.strOtherReason = strOtherReason;
	}

	public String getStrRefBy() {
		return strRefBy;
	}

	public void setStrRefBy(String strRefBy) {
		this.strRefBy = strRefBy;
	}

	public String getStrBillNo() {
		return strBillNo;
	}

	public void setStrBillNo(String strBillNo) {
		this.strBillNo = strBillNo;
	}

	public String[] getRefundType() {
		return refundType;
	}

	public void setRefundType(String[] refundType) {
		this.refundType = refundType;
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


	public String getStrIpdRefundPenaltyAmt() {
		return strIpdRefundPenaltyAmt;
	}


	public void setStrIpdRefundPenaltyAmt(String strIpdRefundPenaltyAmt) {
		this.strIpdRefundPenaltyAmt = strIpdRefundPenaltyAmt;
	}


	public String getStrOpdRefundPenaltyAmt() {
		return strOpdRefundPenaltyAmt;
	}


	public void setStrOpdRefundPenaltyAmt(String strOpdRefundPenaltyAmt) {
		this.strOpdRefundPenaltyAmt = strOpdRefundPenaltyAmt;
	}


	public String getStrEmergencyRefundPenaltyAmt() {
		return strEmergencyRefundPenaltyAmt;
	}


	public void setStrEmergencyRefundPenaltyAmt(String strEmergencyRefundPenaltyAmt) {
		this.strEmergencyRefundPenaltyAmt = strEmergencyRefundPenaltyAmt;
	}


	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
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


	public String getStrRefundMode() {
		return strRefundMode;
	}


	public void setStrRefundMode(String strRefundMode) {
		this.strRefundMode = strRefundMode;
	}


	public String getStrOnlineRefundRequestAllowed() {
		return strOnlineRefundRequestAllowed;
	}


	public void setStrOnlineRefundRequestAllowed(
			String strOnlineRefundRequestAllowed) {
		this.strOnlineRefundRequestAllowed = strOnlineRefundRequestAllowed;
	}


	public String getStrSeatId() {
		return strSeatId;
	}


	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
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


	public String[] getClientNo() {
		return clientNo;
	}


	public void setClientNo(String[] clientNo) {
		this.clientNo = clientNo;
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


	public String getPrintMode() {
		return printMode;
	}


	public void setPrintMode(String printMode) {
		this.printMode = printMode;
	}


	public String getStrBillDate() {
		return strBillDate;
	}


	public void setStrBillDate(String strBillDate) {
		this.strBillDate = strBillDate;
	}


	public String getTrf_grossRefund() {
		return trf_grossRefund;
	}


	public void setTrf_grossRefund(String trf_grossRefund) {
		this.trf_grossRefund = trf_grossRefund;
	}
    

}
