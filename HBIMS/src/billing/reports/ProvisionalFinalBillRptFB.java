package billing.reports;

import org.apache.struts.action.ActionForm;

public class ProvisionalFinalBillRptFB extends ActionForm {
	
private static final long serialVersionUID = 02L;
	
    private String strWardCode="0";
	private String strDeptCode = "0";
	private String strPatAccNo = "";
	private String strBillTypeCombo = "";
	private String strReportId = "0";
	private String strMsgType = "";
	private String strErrMsg = "";
	private String strHospitalCode="108";
	
	private String strDeptValues="";
	private String strWardValues="";
	
    private String strReportFormat = "0";

	public String getStrWardCode() {
		return strWardCode;
	}

	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}

	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}

	public String getStrReportId() {
		return strReportId;
	}

	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrDeptValues() {
		return strDeptValues;
	}

	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}

	public String getStrWardValues() {
		return strWardValues;
	}

	public void setStrWardValues(String strWardValues) {
		this.strWardValues = strWardValues;
	}

	public String getStrReportFormat() {
		return strReportFormat;
	}

	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}

	public String getStrPatAccNo() {
		return strPatAccNo;
	}

	public void setStrPatAccNo(String strPatAccNo) {
		this.strPatAccNo = strPatAccNo;
	}

	public String getStrBillTypeCombo() {
		return strBillTypeCombo;
	}

	public void setStrBillTypeCombo(String strBillTypeCombo) {
		this.strBillTypeCombo = strBillTypeCombo;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

}
