package billing.reports;

import javax.sql.rowset.WebRowSet;


public class PaymentDtlRptVO {
	
	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";
	private String strReportFormat = "0";
	private String strHospSerName = "0";
	private String strHospitalCode = "";
	private String strCase = "1";
	private WebRowSet strHospSerWs = null;
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private WebRowSet strHospitalWs = null; 
	private String strHospNameValues=""; 
	private String strHospitalName = "";
	private String StrSeatId="";
	
	private WebRowSet strPaymentModeList = null; // added for 'payment mode' combo,  by: manisha gangwar dt : 23.8.18
	private WebRowSet StrClerkWs=null;
	
	
	public WebRowSet getStrPaymentModeList() {
		return strPaymentModeList;
	}
	public void setStrPaymentModeList(WebRowSet strPaymentModeList) {
		this.strPaymentModeList = strPaymentModeList;
	}
	
	public String getStrSeatId() {
		return StrSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		StrSeatId = strSeatId;
	}
	public WebRowSet getStrHospitalWs() {
		return strHospitalWs;
	}
	public void setStrHospitalWs(WebRowSet strHospitalWs) {
		this.strHospitalWs = strHospitalWs;
	}
	public String getStrHospNameValues() {
		return strHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		this.strHospNameValues = strHospNameValues;
	}
	public String getStrHospitalName() {
		return strHospitalName;
	}
	public void setStrHospitalName(String strHospitalName) {
		this.strHospitalName = strHospitalName;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
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
	public String getStrHospSerName() {
		return strHospSerName;
	}
	public void setStrHospSerName(String strHospSerName) {
		this.strHospSerName = strHospSerName;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public WebRowSet getStrHospSerWs() {
		return strHospSerWs;
	}
	public void setStrHospSerWs(WebRowSet strHospSerWs) {
		this.strHospSerWs = strHospSerWs;
	}
	public WebRowSet getStrClerkWs() {
		return StrClerkWs;
	}
	public void setStrClerkWs(WebRowSet strClerkWs) {
		StrClerkWs = strClerkWs;
	}

}
