package billing.transactions;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import hisglobal.utility.TransferObject;
import javax.sql.rowset.WebRowSet;

public class CreditBillApprovalTransVO implements TransferObject
{
	
	private static final long serialVersionUID = 02L;
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqDiscountWs = null;
	private WebRowSet strOnLineTariffWs = null;
		
	private String strMsgString = null;
    private String strMsgType = "";   
    private String strChk ="";
    private String strCrNo = "";
    private String strCrNo1 = "";
    private String strhiddTrfFADis  ="";
    private String strTariffId ="";
    private String sumFAdjustment = "";
    private String strPrevDisTypeFA ="";
    private String StrLstDisUnitFA ="";
    private String strCurrDisTypeFA="";
    
    private String[] strhiddTrfAddDis ={"0"};
    
    private String[] strTempCkhBox ={"0"};
   
    private String strRateBaseValue = "";
    private String strBId ="";
    private String[] LstDisService ={"0"}; 
    private String[] CalsumDis ={"0"};
    
    private String tariff_id ="";
    private String strApproval_id="";
    private String strExpnseAmt ="";
    private String strRecFrClnt ="";
    private String lastDis ="";
    private String lastDisType="";
    private int strLength = 0;
    private String hlp_opt_sel ="";
    private String hlpBServiceID = "";
    private String strIpdBilldiscountType = "";
    //HLP Field
    private String strIpdBillCurrDis = null;
    private String[] discountType ={"0"};
    private String[] qtyBaseValue ={"0"};
    private String[] Cost={"0"};
    private String[] qty={"0"};
    private String[] strPrevDisType ={"0"}; 
    private String[] opd_discount={"0"};
    private String[] opd_discountType = {"0"};
    private String[] strAppSlNo ={"0"};
    private String[] strTempDiscountUnit ={"0"};
    private String strIpdBillPrevDisType = "";
    
    private String strDisAmt ="";
    private String strRecFrPat ="";
    private String strNetAmt ="";
    private String strDetls="";
    private WebRowSet strDisBy =null;
    private WebRowSet strDisRsn =null;
    private WebRowSet strPopUpResp =null;
    private String strRsn="";
    private String strRmk="";//waiver by
    private String strAccPreDis ="";
    private String strErrMsg="";
    private String strWarning="";
    private String strMsg="";
    private String strHospitalCode="";
    private String strRetValue="";
    private String strApprovalSlNo="";
    private String strErrPrimKeyLog="";
    private String strPrimaryKeyMsg="";
    private String strSeatId="";
    private String strCtDate="";
    private String strApprovalFlag="";
    private String strRecieptType="";
    private String strBserviceId="";
    private String strChargeTypeId="";
    private String strEmpNo="";
    private String strUserLevel="";
    private String strRemarks="";
    private String strDisUnit="";
    private String strDisType="";
    private String strTrfId="";
    private String strReqNo="";
    private String strDrt="";
    private String strPopUp_id = "";
    private String[] approvalId={"0"};
    private String[] trfDisApproval={"0"};
    private String strHidValOnLineReqDis="";
    private String[] strTariffIdInsert={"0"}; 
    private String strIpdBillFianlDis ="";
    
    private String approval_id="";
    private String strHospitalService="";
    private String strReqType="";
    private String[] hideLstDis={"0"};
    private String disTyp_Dis="";
    private String[] trfAdd_Dis={"0"};
    private String strHospService="";
    private String strValmode = "";
    private String strPopUpId = "";
    private String strClientDtlHlp ="";
    private String strPatientDetailsView = "";
    
    private String[] strCreditLetterNo;
    private String[] strCreditLetterDate;
   

	private String scanDocID;//with what name file is saved..
	 //client no for credit billing
    String strClientNo="";
    String strCatgoryCode="0";
  //credit billing common details..
    String strEmployeeId;
    String strEmployeeName;
    String strRalationId;
    String strRelationWs;
    String strCardValidity;
    String[] tariffCost;//tariff cost for each row/tariff..
    String[] discGivenAmt;
    private String CrNo;
         
    
    public String getStrPatientDetailsView() {
		return strPatientDetailsView;
	}

	public void setStrPatientDetailsView(String strPatientDetailsView) {
		this.strPatientDetailsView = strPatientDetailsView;
	}

	/**
	 * @return the trfAdd_Dis
	 */
	

	public String getStrClientDtlHlp() {
		return strClientDtlHlp;
	}

	public void setStrClientDtlHlp(String strClientDtlHlp) {
		this.strClientDtlHlp = strClientDtlHlp;
	}

	/**
	 * @return the strPopUpId
	 */
	public String getStrPopUpId() {
		return strPopUpId;
	}

	/**
	 * @param strPopUpId the strPopUpId to set
	 */
	public void setStrPopUpId(String strPopUpId) {
		this.strPopUpId = strPopUpId;
	}

	/**
	 * @return the strValmode
	 */
	public String getStrValmode() {
		return strValmode;
	}

	/**
	 * @param strValmode the strValmode to set
	 */
	public void setStrValmode(String strValmode) {
		this.strValmode = strValmode;
	}

	/**
	 * @return the strHospService
	 */
	public String getStrHospService() {
		return strHospService;
	}

	/**
	 * @param strHospService the strHospService to set
	 */
	public void setStrHospService(String strHospService) {
		this.strHospService = strHospService;
	}

	/**
	 * @return the disTyp_Dis
	 */
	public String getDisTyp_Dis() {
		return disTyp_Dis;
	}

	/**
	 * @param disTyp_Dis the disTyp_Dis to set
	 */
	public void setDisTyp_Dis(String disTyp_Dis) {
		this.disTyp_Dis = disTyp_Dis;
	}

	/**
	 * @return the hideLstDis
	 */
	/*public String getHideLstDis() {
		return hideLstDis;
	}

	*//**
	 * @param hideLstDis the hideLstDis to set
	 *//*
	public void setHideLstDis(String hideLstDis) {
		this.hideLstDis = hideLstDis;
	}*/

	/**
	 * @return the strHospitalService
	 */
	public String getStrHospitalService() {
		return strHospitalService;
	}

	/**
	 * @param strHospitalService the strHospitalService to set
	 */
	public void setStrHospitalService(String strHospitalService) {
		this.strHospitalService = strHospitalService;
	}

	/**
	 * @return the approval_id
	 */
	public String getApproval_id() {
		return approval_id;
	}

	/**
	 * @param approval_id the approval_id to set
	 */
	public void setApproval_id(String approval_id) {
		this.approval_id = approval_id;
	}

	/**
	 * @return the strOnLineTariffWs
	 */
	public WebRowSet getStrOnLineTariffWs() {
		return strOnLineTariffWs;
	}

	/**
	 * @param strOnLineTariffWs the strOnLineTariffWs to set
	 */
	public void setStrOnLineTariffWs(WebRowSet strOnLineTariffWs) {
		this.strOnLineTariffWs = strOnLineTariffWs;
	}

	/**
	 * @return the strTrfId
	 */
	public String getStrTrfId() {
		return strTrfId;
	}

	/**
	 * @param strTrfId the strTrfId to set
	 */
	public void setStrTrfId(String strTrfId) {
		this.strTrfId = strTrfId;
	}

	/**
	 * @return the strReqNo
	 */
	public String getStrReqNo() {
		return strReqNo;
	}

	/**
	 * @param strReqNo the strReqNo to set
	 */
	public void setStrReqNo(String strReqNo) {
		this.strReqNo = strReqNo;
	}

	/**
	 * @return the strDisUnit
	 */
	public String getStrDisUnit() {
		return strDisUnit;
	}

	/**
	 * @param strDisUnit the strDisUnit to set
	 */
	public void setStrDisUnit(String strDisUnit) {
		this.strDisUnit = strDisUnit;
	}

	/**
	 * @return the strDisType
	 */
	public String getStrDisType() {
		return strDisType;
	}

	/**
	 * @param strDisType the strDisType to set
	 */
	public void setStrDisType(String strDisType) {
		this.strDisType = strDisType;
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
	 * @return the strUserLevel
	 */
	public String getStrUserLevel() {
		return strUserLevel;
	}

	/**
	 * @param strUserLevel the strUserLevel to set
	 */
	public void setStrUserLevel(String strUserLevel) {
		this.strUserLevel = strUserLevel;
	}

	/**
	 * @return the strEmpNo
	 */
	public String getStrEmpNo() {
		return strEmpNo;
	}

	/**
	 * @param strEmpNo the strEmpNo to set
	 */
	public void setStrEmpNo(String strEmpNo) {
		this.strEmpNo = strEmpNo;
	}

	/**
	 * @return the strApprovalFlag
	 */
	public String getStrApprovalFlag() {
		return strApprovalFlag;
	}

	/**
	 * @param strApprovalFlag the strApprovalFlag to set
	 */
	public void setStrApprovalFlag(String strApprovalFlag) {
		this.strApprovalFlag = strApprovalFlag;
	}

	public String getStrCtDate() { // function for gettin SYSDATE
		HisUtil util = new HisUtil("DiscountApproval", "DiscountApprovalTransVO");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("DiscountApproval", "DiscountApprovalTransVO",
					"DiscountApprovalTransVO.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}
    
    /**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * @return the strApprovalSlNo
	 */
	public String getStrApprovalSlNo() {
		return strApprovalSlNo;
	}

	/**
	 * @param strApprovalSlNo the strApprovalSlNo to set
	 */
	public void setStrApprovalSlNo(String strApprovalSlNo) {
		this.strApprovalSlNo = strApprovalSlNo;
	}

	/**
	 * @return the strRetValue
	 */
	public String getStrRetValue() {
		return strRetValue;
	}

	/**
	 * @param strRetValue the strRetValue to set
	 */
	public void setStrRetValue(String strRetValue) {
		this.strRetValue = strRetValue;
	}

	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @return the strWarning
	 */
	public String getStrWarning() {
		return strWarning;
	}

	/**
	 * @param strWarning the strWarning to set
	 */
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}

	/**
	 * @return the strMsg
	 */
	public String getStrMsg() {
		return strMsg;
	}

	/**
	 * @param strMsg the strMsg to set
	 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
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

	public void setStrDisBy(WebRowSet strDisBy) 
	{
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

	public WebRowSet getStrOnLineReqDiscountWs() {
		return strOnLineReqDiscountWs;
	}

	public void setStrOnLineReqDiscountWs(WebRowSet strOnLineReqDiscountWs) {
		this.strOnLineReqDiscountWs = strOnLineReqDiscountWs;
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

	/**
	 * @return the strErrPrimKeyLog
	 */
	public String getStrErrPrimKeyLog() {
		return strErrPrimKeyLog;
	}

	/**
	 * @param strErrPrimKeyLog the strErrPrimKeyLog to set
	 */
	public void setStrErrPrimKeyLog(String strErrPrimKeyLog) {
		this.strErrPrimKeyLog = strErrPrimKeyLog;
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
	 * @return the strRecieptType
	 */
	public String getStrRecieptType() {
		return strRecieptType;
	}

	/**
	 * @param strRecieptType the strRecieptType to set
	 */
	public void setStrRecieptType(String strRecieptType) {
		this.strRecieptType = strRecieptType;
	}

	/**
	 * @return the strBserviceId
	 */
	public String getStrBserviceId() {
		return strBserviceId;
	}

	/**
	 * @param strBserviceId the strBserviceId to set
	 */
	public void setStrBserviceId(String strBserviceId) {
		this.strBserviceId = strBserviceId;
	}

	/**
	 * @return the strChargeTypeId
	 */
	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}

	/**
	 * @param strChargeTypeId the strChargeTypeId to set
	 */
	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}

	/**
	 * @return the lastDisType
	 */
	public String getLastDisType() {
		return lastDisType;
	}

	/**
	 * @param lastDisType the lastDisType to set
	 */
	public void setLastDisType(String lastDisType) {
		this.lastDisType = lastDisType;
	}

	/**
	 * @return the strReqType
	 */
	public String getStrReqType() {
		return strReqType;
	}

	/**
	 * @param strReqType the strReqType to set
	 */
	public void setStrReqType(String strReqType) {
		this.strReqType = strReqType;
	}

	/**
	 * @return the strDrt
	 */
	public String getStrDrt() {
		return strDrt;
	}

	/**
	 * @param strDrt the strDrt to set
	 */
	public void setStrDrt(String strDrt) {
		this.strDrt = strDrt;
	}

	/**
	 * @return the approvalId
	 */
	public String[] getApprovalId() {
		return approvalId;
	}

	/**
	 * @param approvalId the approvalId to set
	 */
	public void setApprovalId(String[] approvalId) {
		this.approvalId = approvalId;
	}

	/**
	 * @return the strChk
	 */
	public String getStrChk() {
		return strChk;
	}

	/**
	 * @param strChk the strChk to set
	 */
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	/**
	 * @return the trfDisApproval
	 */
	public String[] getTrfDisApproval() {
		return trfDisApproval;
	}

	/**
	 * @param trfDisApproval the trfDisApproval to set
	 */
	public void setTrfDisApproval(String[] trfDisApproval) {
		this.trfDisApproval = trfDisApproval;
	}

	/**
	 * @param trfAdd_Dis the trfAdd_Dis to set
	 */
	public void setTrfAdd_Dis(String[] trfAdd_Dis) {
		this.trfAdd_Dis = trfAdd_Dis;
	}

	/**
	 * @return the trfAdd_Dis
	 */
	public String[] getTrfAdd_Dis() {
		return trfAdd_Dis;
	}

	/**
	 * @param strCtDate the strCtDate to set
	 */
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	
	/**
	 * @return the hideLstDis
	 */
	public String[] getHideLstDis() {
		return hideLstDis;
	}

	/**
	 * @param hideLstDis the hideLstDis to set
	 */
	public void setHideLstDis(String[] hideLstDis) {
		this.hideLstDis = hideLstDis;
	}

	/**
	 * @return the strPopUpResp
	 */
	public WebRowSet getStrPopUpResp() {
		return strPopUpResp;
	}

	/**
	 * @param strPopUpResp the strPopUpResp to set
	 */
	public void setStrPopUpResp(WebRowSet strPopUpResp) {
		this.strPopUpResp = strPopUpResp;
	}

	public String getStrPopUp_id() {
		return strPopUp_id;
	}

	public void setStrPopUp_id(String strPopUp_id) {
		this.strPopUp_id = strPopUp_id;
	}

	public String getStrHidValOnLineReqDis() {
		return strHidValOnLineReqDis;
	}

	public void setStrHidValOnLineReqDis(String strHidValOnLineReqDis) {
		this.strHidValOnLineReqDis = strHidValOnLineReqDis;
	}

	public String[] getStrTariffIdInsert() {
		return strTariffIdInsert;
	}

	public void setStrTariffIdInsert(String[] strTariffIdInsert) {
		this.strTariffIdInsert = strTariffIdInsert;
	}

	public String[] getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String[] discountType) {
		this.discountType = discountType;
	}

	public String[] getStrPrevDisType() {
		return strPrevDisType;
	}

	public void setStrPrevDisType(String[] strPrevDisType) {
		this.strPrevDisType = strPrevDisType;
	}

	public String[] getOpd_discountType() {
		return opd_discountType;
	}

	public void setOpd_discountType(String[] opd_discountType) {
		this.opd_discountType = opd_discountType;
	}

	public int getStrLength() {
		return strLength;
	}

	public void setStrLength(int strLength) {
		this.strLength = strLength;
	}

	public String[] getStrAppSlNo() {
		return strAppSlNo;
	}

	public void setStrAppSlNo(String[] strAppSlNo) {
		this.strAppSlNo = strAppSlNo;
	}

	public String getHlpBServiceID() {
		return hlpBServiceID;
	}

	public void setHlpBServiceID(String hlpBServiceID) {
		this.hlpBServiceID = hlpBServiceID;
	}

	public String getStrIpdBilldiscountType() {
		return strIpdBilldiscountType;
	}

	public void setStrIpdBilldiscountType(String strIpdBilldiscountType) {
		this.strIpdBilldiscountType = strIpdBilldiscountType;
	}

	public String getStrIpdBillCurrDis() {
		return strIpdBillCurrDis;
	}

	public void setStrIpdBillCurrDis(String strIpdBillCurrDis) {
		this.strIpdBillCurrDis = strIpdBillCurrDis;
	}

	public String getStrIpdBillPrevDisType() {
		return strIpdBillPrevDisType;
	}

	public void setStrIpdBillPrevDisType(String strIpdBillPrevDisType) {
		this.strIpdBillPrevDisType = strIpdBillPrevDisType;
	}

	public String getStrIpdBillFianlDis() {
		return strIpdBillFianlDis;
	}

	public void setStrIpdBillFianlDis(String strIpdBillFianlDis) {
		this.strIpdBillFianlDis = strIpdBillFianlDis;
	}

	public String getStrRateBaseValue() {
		return strRateBaseValue;
	}

	public void setStrRateBaseValue(String strRateBaseValue) {
		this.strRateBaseValue = strRateBaseValue;
	}

	public String getHlp_opt_sel() {
		return hlp_opt_sel;
	}

	public void setHlp_opt_sel(String hlp_opt_sel) {
		this.hlp_opt_sel = hlp_opt_sel;
	}

	public String getTariff_id() {
		return tariff_id;
	}

	public void setTariff_id(String tariff_id) {
		this.tariff_id = tariff_id;
	}

	public String[] getLstDisService() {
		return LstDisService;
	}

	public void setLstDisService(String[] lstDisService) {
		LstDisService = lstDisService;
	}

	public String getStrhiddTrfFADis() {
		return strhiddTrfFADis;
	}

	public void setStrhiddTrfFADis(String strhiddTrfFADis) {
		this.strhiddTrfFADis = strhiddTrfFADis;
	}

	public String[] getQtyBaseValue() {
		return qtyBaseValue;
	}

	public void setQtyBaseValue(String[] qtyBaseValue) {
		this.qtyBaseValue = qtyBaseValue;
	}

	public String[] getCost() {
		return Cost;
	}

	public void setCost(String[] cost) {
		Cost = cost;
	}

	public String[] getQty() {
		return qty;
	}

	public void setQty(String[] qty) {
		this.qty = qty;
	}

	public String[] getOpd_discount() {
		return opd_discount;
	}

	public void setOpd_discount(String[] opd_discount) {
		this.opd_discount = opd_discount;
	}

	public String[] getStrhiddTrfAddDis() {
		return strhiddTrfAddDis;
	}

	public void setStrhiddTrfAddDis(String[] strhiddTrfAddDis) {
		this.strhiddTrfAddDis = strhiddTrfAddDis;
	}

	public String getStrTariffId() {
		return strTariffId;
	}

	public void setStrTariffId(String strTariffId) {
		this.strTariffId = strTariffId;
	}

	public String[] getStrTempDiscountUnit() {
		return strTempDiscountUnit;
	}

	public void setStrTempDiscountUnit(String[] strTempDiscountUnit) {
		this.strTempDiscountUnit = strTempDiscountUnit;
	}

	public String[] getCalsumDis() {
		return CalsumDis;
	}

	public void setCalsumDis(String[] calsumDis) {
		CalsumDis = calsumDis;
	}

	public String[] getStrTempCkhBox() {
		return strTempCkhBox;
	}

	public void setStrTempCkhBox(String[] strTempCkhBox) {
		this.strTempCkhBox = strTempCkhBox;
	}

	public String getSumFAdjustment() {
		return sumFAdjustment;
	}

	public void setSumFAdjustment(String sumFAdjustment) {
		this.sumFAdjustment = sumFAdjustment;
	}

	public String getStrPrevDisTypeFA() {
		return strPrevDisTypeFA;
	}

	public void setStrPrevDisTypeFA(String strPrevDisTypeFA) {
		this.strPrevDisTypeFA = strPrevDisTypeFA;
	}

	public String getStrLstDisUnitFA() {
		return StrLstDisUnitFA;
	}

	public void setStrLstDisUnitFA(String strLstDisUnitFA) {
		StrLstDisUnitFA = strLstDisUnitFA;
	}

	public String getStrCurrDisTypeFA() {
		return strCurrDisTypeFA;
	}

	public void setStrCurrDisTypeFA(String strCurrDisTypeFA) {
		this.strCurrDisTypeFA = strCurrDisTypeFA;
	}

	

	

	public String[] getStrCreditLetterNo() {
		return strCreditLetterNo;
	}

	public void setStrCreditLetterNo(String[] strCreditLetterNo) {
		this.strCreditLetterNo = strCreditLetterNo;
	}

	public String[] getStrCreditLetterDate() {
		return strCreditLetterDate;
	}

	public void setStrCreditLetterDate(String[] strCreditLetterDate) {
		this.strCreditLetterDate = strCreditLetterDate;
	}

	public String getScanDocID() {
		return scanDocID;
	}

	public void setScanDocID(String scanDocID) {
		this.scanDocID = scanDocID;
	}

	public String getStrClientNo() {
		return strClientNo;
	}

	public void setStrClientNo(String strClientNo) {
		this.strClientNo = strClientNo;
	}

	public String getStrCatgoryCode() {
		return strCatgoryCode;
	}

	public void setStrCatgoryCode(String strCatgoryCode) {
		this.strCatgoryCode = strCatgoryCode;
	}

	public String getStrEmployeeId() {
		return strEmployeeId;
	}

	public void setStrEmployeeId(String strEmployeeId) {
		this.strEmployeeId = strEmployeeId;
	}

	public String getStrEmployeeName() {
		return strEmployeeName;
	}

	public void setStrEmployeeName(String strEmployeeName) {
		this.strEmployeeName = strEmployeeName;
	}

	public String getStrRalationId() {
		return strRalationId;
	}

	public void setStrRalationId(String strRalationId) {
		this.strRalationId = strRalationId;
	}

	
	public String getStrCardValidity() {
		return strCardValidity;
	}

	public void setStrCardValidity(String strCardValidity) {
		this.strCardValidity = strCardValidity;
	}

	public void setStrRelationWs(String strRelationWs) {
		this.strRelationWs = strRelationWs;
	}

	public String getStrRelationWs() {
		return strRelationWs;
	}

	

	public String[] getDiscGivenAmt() {
		return discGivenAmt;
	}

	public void setDiscGivenAmt(String[] discGivenAmt) {
		this.discGivenAmt = discGivenAmt;
	}

	public String[] getTariffCost() {
		return tariffCost;
	}

	public void setTariffCost(String[] tariffCost) {
		this.tariffCost = tariffCost;
	}

	public String getCrNo() {
		return CrNo;
	}

	public void setCrNo(String crNo) {
		this.CrNo = crNo;
	}

	

	

	
}
