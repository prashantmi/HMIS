package billing.transactions;

import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class BillPrintTransVO implements TransferObject {

	private static final long serialVersionUID = 02L;
	private WebRowSet strPatientDetailsWs = null;
	private WebRowSet strOnLineReqWs = null;

	private String strMsgString = null;
	private String strMsgType = "";

	private String strCrNo = "";

	private String strHospitalCode = "";

	
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

}
