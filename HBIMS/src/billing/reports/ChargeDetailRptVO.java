package billing.reports;

import javax.sql.rowset.WebRowSet;

public class ChargeDetailRptVO {
	
	private String strHospitalCode = "";
	private String strChargeTypeId = "1";
	private String strCategory = "";
	private String strGroupNameId = "";
	private String strCurrentRate = "1";
	private String strReportFormat = "0";
	private String strMonth = "";
	private String strYear = "";
	private String StrSeatId="";
	private WebRowSet strHospServiceWs = null;
	private WebRowSet strCategoryWs = null;
	private WebRowSet strGroupDetailWs = null;
	private WebRowSet strHospitalWs = null; 
	private String strHospNameValues=""; 
	private String strHospitalName = "";
	private String strMsgString = "";
	private String strMsgType = "";
	private WebRowSet StrHospSerWs=null;
	private WebRowSet StrReportWs=null;
	public WebRowSet getStrHospSerWs() {
		return StrHospSerWs;
	}
	public void setStrHospSerWs(WebRowSet strHospSerWs) {
		StrHospSerWs = strHospSerWs;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public WebRowSet getStrHospServiceWs() {
		return strHospServiceWs;
	}
	public void setStrHospServiceWs(WebRowSet strHospServiceWs) {
		this.strHospServiceWs = strHospServiceWs;
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
	public WebRowSet getStrCategoryWs() {
		return strCategoryWs;
	}
	public void setStrCategoryWs(WebRowSet strCategoryWs) {
		this.strCategoryWs = strCategoryWs;
	}
	public String getStrChargeTypeId() {
		return strChargeTypeId;
	}
	public void setStrChargeTypeId(String strChargeTypeId) {
		this.strChargeTypeId = strChargeTypeId;
	}
	public WebRowSet getStrGroupDetailWs() {
		return strGroupDetailWs;
	}
	public void setStrGroupDetailWs(WebRowSet strGroupDetailWs) {
		this.strGroupDetailWs = strGroupDetailWs;
	}
	public String getStrCategory() {
		return strCategory;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
	 
	public String getStrGroupNameId() {
		return strGroupNameId;
	}
	public void setStrGroupNameId(String strGroupNameId) {
		this.strGroupNameId = strGroupNameId;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrCurrentRate() {
		return strCurrentRate;
	}
	public void setStrCurrentRate(String strCurrentRate) {
		this.strCurrentRate = strCurrentRate;
	}
	public String getStrMonth() {
		return strMonth;
	}
	public void setStrMonth(String strMonth) {
		this.strMonth = strMonth;
	}
	public String getStrYear() {
		return strYear;
	}
	public void setStrYear(String strYear) {
		this.strYear = strYear;
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
	public WebRowSet getStrReportWs() {
		return StrReportWs;
	}
	public void setStrReportWs(WebRowSet strReportWs) {
		StrReportWs = strReportWs;
	}
	
	
	

}
