package billing.transactions;
import org.apache.struts.action.ActionForm;

public class OnlineRequestCancellationTransFB extends ActionForm{
	
	private static final long serialVersionUID = 02L;
	private String strWarningMsg = "";
	private String strNormalMsg = "";
	
	
	private String strCrNo="";
	private String strCancelledBy="";
	private String strCancelReason="";
	private String strOtherReason="";
	private String strHospitalCode ="";
	private String strSeatId = "";
	
	private String strRequestNo="";
	private String strPopUpDtls="";
	
	private String strOnlineReqDtl="";
	
	private String strMsgString="";
	private String strMsgType="";
	private String strErrMsg="";
	private String strMsg = "";
	
	private String strPatientDetailsView = "";
	private String strCRNoSatus="1";
	
	
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
	public String getStrOnlineReqDtl() {
		return strOnlineReqDtl;
	}
	public void setStrOnlineReqDtl(String strOnlineReqDtl) {
		this.strOnlineReqDtl = strOnlineReqDtl;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
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
	
	
	

}
