package billing.reports;

import javax.sql.rowset.WebRowSet;

public class DayEndCashHandoverRptVO {

	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";
	private String strModuleId = "0";
	private String strReportFormat = "0";
	private String strCounterName = "0";
	private String strHospSerName = "0";
	private String strGroupName = "";
	private String strHospitalCode = "";
	private String strCase = "1";
	private String strChargeId = "0";
	private WebRowSet strCounterWs = null;
	private WebRowSet strClerkWs = null;
	private WebRowSet strGroupWs = null;
	private WebRowSet strTariffWs = null;
	private WebRowSet strDeptWs = null;
	private WebRowSet strHospSerWs = null;
	private WebRowSet strHospitalWs = null; 
	private String strHospNameValues=""; 
	private String strHospitalName = "";
	private String StrSeatId = "";
	private String strTariffType="";
	
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strCostType= ""; 
	private String strCostTypeValue="";
	private WebRowSet strCostTypeWS=null;
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
	public String getStrModuleId() {
		return strModuleId;
	}
	public void setStrModuleId(String strModuleId) {
		this.strModuleId = strModuleId;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public WebRowSet getStrCounterWs() {
		return strCounterWs;
	}
	public void setStrCounterWs(WebRowSet strCounterWs) {
		this.strCounterWs = strCounterWs;
	}
	public String getStrCounterName() {
		return strCounterName;
	}
	public void setStrCounterName(String strCounterName) {
		this.strCounterName = strCounterName;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public WebRowSet getStrClerkWs() {
		return strClerkWs;
	}
	public void setStrClerkWs(WebRowSet strClerkWs) {
		this.strClerkWs = strClerkWs;
	}
	public WebRowSet getStrGroupWs() {
		return strGroupWs;
	}
	public void setStrGroupWs(WebRowSet strGroupWs) {
		this.strGroupWs = strGroupWs;
	}
	public WebRowSet getStrTariffWs() {
		return strTariffWs;
	}
	public void setStrTariffWs(WebRowSet strTariffWs) {
		this.strTariffWs = strTariffWs;
	}
	public WebRowSet getStrDeptWs() {
		return strDeptWs;
	}
	public void setStrDeptWs(WebRowSet strDeptWs) {
		this.strDeptWs = strDeptWs;
	}
	public String getStrChargeId() {
		return strChargeId;
	}
	public void setStrChargeId(String strChargeId) {
		this.strChargeId = strChargeId;
	}
	public WebRowSet getStrHospSerWs() {
		return strHospSerWs;
	}
	public void setStrHospSerWs(WebRowSet strHospSerWs) {
		this.strHospSerWs = strHospSerWs;
	}
	public String getStrHospSerName() {
		return strHospSerName;
	}
	public void setStrHospSerName(String strHospSerName) {
		this.strHospSerName = strHospSerName;
	}
	public String getStrGroupName() {
		return strGroupName;
	}
	public void setStrGroupName(String strGroupName) {
		this.strGroupName = strGroupName;
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
	
	public String getStrSeatId() {
		return StrSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		StrSeatId = strSeatId;
	}
	public String getStrTariffType() {
		return strTariffType;
	}
	public void setStrTariffType(String strTariffType) {
		this.strTariffType = strTariffType;
	}
	public String getStrCostType() {
		return strCostType;
	}
	public void setStrCostType(String strCostType) {
		this.strCostType = strCostType;
	}
	public String getStrCostTypeValue() {
		return strCostTypeValue;
	}
	public void setStrCostTypeValue(String strCostTypeValue) {
		this.strCostTypeValue = strCostTypeValue;
	}
	public WebRowSet getStrCostTypeWS() {
		return strCostTypeWS;
	}
	public void setStrCostTypeWS(WebRowSet strCostTypeWS) {
		this.strCostTypeWS = strCostTypeWS;
	}

	
}
