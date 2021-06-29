package billing.transactions;

import org.apache.struts.action.ActionForm;

public class BillingCancellationTransFB extends ActionForm{
	
	private static final long serialVersionUID = 02L;
	
	private String strWarningMsg = "";
	private String strNormalMsg = "";
	
	
	private String chk[]= null;
	private String crchk[]= null;
	private String cehk[]= null;
	private String clNo[]= null;
	private String clDate[]= null;
	private String strHospitalCode = "";
	private String strSeatId = "";
		
	private String strCrNo="";
	private String strCancelledBy="";
	private String strCancelReason="";
	private String strOtherReason="";
	
	private String strBillNo="";
	private String strTransNo="";
	private String strRcptNo="";
		
	private String strPopUpDtls="";
	
	private String strBillDtl="";
	
	private String strMsgString="";
	private String strMsgType="";
	private String strMsg = "";
	private String strErrMsg="";
	
	private String strCriteria = "1";
	private String strCase ="1";
	private String strGuarantorName = "";
	private String strBillUsrFuncName ="";
	
	private String strPatientDetailsView = "";
	private String strCRNoSatus="1";
	private String crbill = "0";
	private String billcancelflag = "0";
	private String filePath="";
	private String isOpenPopUp="";
	private String printMode="0";
	private String strService="0";
	
	
	/**
	 * @return the strPatientDetailsView
	 */
	public String getStrPatientDetailsView() {
		return strPatientDetailsView;
	}
	/**
	 * @param strPatientDetailsView the strPatientDetailsView to set
	 */
	public void setStrPatientDetailsView(String strPatientDetailsView) {
		this.strPatientDetailsView = strPatientDetailsView;
	}
	/**
	 * @return the strCRNoSatus
	 */
	public String getStrCRNoSatus() {
		return strCRNoSatus;
	}
	/**
	 * @param strCRNoSatus the strCRNoSatus to set
	 */
	public void setStrCRNoSatus(String strCRNoSatus) {
		this.strCRNoSatus = strCRNoSatus;
	}
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
	public String getStrBillDtl() {
		return strBillDtl;
	}
	public void setStrBillDtl(String strBillDtl) {
		this.strBillDtl = strBillDtl;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String[] getChk() {
		return chk;
	}
	public void setChk(String[] chk) {
		this.chk = chk;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrGuarantorName() {
		return strGuarantorName;
	}
	public void setStrGuarantorName(String strGuarantorName) {
		this.strGuarantorName = strGuarantorName;
	}
	public String getStrBillUsrFuncName() {
		return strBillUsrFuncName;
	}
	public void setStrBillUsrFuncName(String strBillUsrFuncName) {
		this.strBillUsrFuncName = strBillUsrFuncName;
	}
	public String getStrCriteria() {
		return strCriteria;
	}
	public void setStrCriteria(String strCriteria) {
		this.strCriteria = strCriteria;
	}
	public String getStrTransNo() {
		return strTransNo;
	}
	public void setStrTransNo(String strTransNo) {
		this.strTransNo = strTransNo;
	}
	public String getStrRcptNo() {
		return strRcptNo;
	}
	public void setStrRcptNo(String strRcptNo) {
		this.strRcptNo = strRcptNo;
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
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getCrbill() {
		return crbill;
	}
	public void setCrbill(String crbill) {
		this.crbill = crbill;
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
	public String getStrService() {
		return strService;
	}
	public void setStrService(String strService) {
		this.strService = strService;
	}

	 
	

}
