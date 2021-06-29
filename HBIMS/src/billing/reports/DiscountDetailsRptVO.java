package billing.reports;

import javax.sql.rowset.WebRowSet;

public class DiscountDetailsRptVO {
	
	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";
	private String strChargeTypeId = "0";
	private String strCategory = "2";
	private String strReason = "0";
	private String strReportFormat = "0";
	private String strHospitalCode = "108";
	private WebRowSet strGroupWs = null;
	private WebRowSet strTariffWs = null;
	private WebRowSet strHospServiceWs = null;
	private WebRowSet strConsultWs = null;
	private WebRowSet strCategoryWs = null;
	private WebRowSet strReasonWs = null;
	private String strCase = "";
	private WebRowSet strHospitalWs = null; 
	private String strHospNameValues=""; 
	private String strHospitalName = "";
	private String strHospService = "1";
	private String strGroupCode = "0";
	private String strDeptCode = "0";
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String StrSeatId="";
	public String getStrSeatId() {
		return StrSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		StrSeatId = strSeatId;
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
	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}
	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
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
	public WebRowSet getStrGroupWs() {
		return strGroupWs;
	}
	public void setStrGroupWs(WebRowSet strGroupWs) {
		this.strGroupWs = strGroupWs;
	}
	public WebRowSet getStrHospServiceWs() {
		return strHospServiceWs;
	}
	public void setStrHospServiceWs(WebRowSet strHospServiceWs) {
		this.strHospServiceWs = strHospServiceWs;
	}
	public WebRowSet getStrConsultWs() {
		return strConsultWs;
	}
	public void setStrConsultWs(WebRowSet strConsultWs) {
		this.strConsultWs = strConsultWs;
	}
	public WebRowSet getStrCategoryWs() {
		return strCategoryWs;
	}
	public void setStrCategoryWs(WebRowSet strCategoryWs) {
		this.strCategoryWs = strCategoryWs;
	}
	public WebRowSet getStrReasonWs() {
		return strReasonWs;
	}
	public void setStrReasonWs(WebRowSet strReasonWs) {
		this.strReasonWs = strReasonWs;
	}
	public String getStrHospService() {
		return strHospService;
	}
	public void setStrHospService(String strHospService) {
		this.strHospService = strHospService;
	}
	public String getStrGroupCode() {
		return strGroupCode;
	}
	public void setStrGroupCode(String strGroupCode) {
		this.strGroupCode = strGroupCode;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
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
	public String getStrCategory() {
		return strCategory;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
	public String getStrReason() {
		return strReason;
	}
	public void setStrReason(String strReason) {
		this.strReason = strReason;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public WebRowSet getStrTariffWs() {
		return strTariffWs;
	}
	public void setStrTariffWs(WebRowSet strTariffWs) {
		this.strTariffWs = strTariffWs;
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
	
}
