package billing.transactions;

import javax.sql.rowset.WebRowSet;
import org.apache.struts.action.ActionForm;

public class BillPrintTransFB extends ActionForm 
{
	private static final long serialVersionUID = 1L;
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqWs = null;

	private String strMsgString = null;
	private String strMsgType = "";
	private String strErrMsg = "";
	private String strMsg = "";
	private String strRequestValue = "0";
	private String strCrNo = "";
	private String strHospitalCode = "";
    
	private String strBillType = "0";
	
    private String strOnlineRequestDtls = "";
    private String strPatientDetailsView = "";
    	
    
	public String getStrPatientDetailsView() {
		return strPatientDetailsView;
	}

	public void setStrPatientDetailsView(String strPatientDetailsView) {
		this.strPatientDetailsView = strPatientDetailsView;
	}

	public WebRowSet getStrPatientDetailsWs() {
		return strPatientDetailsWs;
	}

	public void setStrPatientDetailsWs(WebRowSet strPatientDetailsWs) {
		this.strPatientDetailsWs = strPatientDetailsWs;
	}

	public WebRowSet getStrOnLineReqWs() {
		return strOnLineReqWs;
	}

	public void setStrOnLineReqWs(WebRowSet strOnLineReqWs) {
		this.strOnLineReqWs = strOnLineReqWs;
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

	public String getStrCrNo() {
		return strCrNo;
	}

	public void setStrCrNo(String strCrNo) {
		this.strCrNo = strCrNo;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrOnlineRequestDtls() {
		return strOnlineRequestDtls;
	}

	public void setStrOnlineRequestDtls(String strOnlineRequestDtls) {
		this.strOnlineRequestDtls = strOnlineRequestDtls;
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

	public String getStrRequestValue() {
		return strRequestValue;
	}

	public void setStrRequestValue(String strRequestValue) {
		this.strRequestValue = strRequestValue;
	}

	public String getStrBillType() {
		return strBillType;
	}

	public void setStrBillType(String strBillType) {
		this.strBillType = strBillType;
	}
   
	

	
                                                                                                                                  
}
