package billing.reports;

import javax.sql.rowset.WebRowSet;

public class DayEndDuplicateRptVO {
	
	private String strMsgType = "";
	private String strMsgString = "";
	private String strHospitalCode = "";
	private String strFromDate = "";
	private String strToDate = "";
	private String strBillModuleId = "";
	private WebRowSet strHospitalWs = null; 
	private String strHospNameValues=""; 
	private String strHospitalName = "";
	private String strUserId = "";
	private String strSeatId="";
	
	private WebRowSet strSummaryDtlWs = null;
	/**
	 * @return the strSummaryDtlWs
	 */
	public WebRowSet getStrSummaryDtlWs() {
		return strSummaryDtlWs;
	}
	/**
	 * @param strSummaryDtlWs the strSummaryDtlWs to set
	 */
	public void setStrSummaryDtlWs(WebRowSet strSummaryDtlWs) {
		this.strSummaryDtlWs = strSummaryDtlWs;
	}
	/**
	 * @return the strMsgType
	 */
	public String getStrMsgType() {
		return strMsgType;
	}
	/**
	 * @param strMsgType the strMsgType to set
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
	/**
	 * @return the strMsgString
	 */
	public String getStrMsgString() {
		return strMsgString;
	}
	/**
	 * @param strMsgString the strMsgString to set
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
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
	 * @return the strFromDate
	 */
	public String getStrFromDate() {
		return strFromDate;
	}
	/**
	 * @param strFromDate the strFromDate to set
	 */
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	/**
	 * @return the strToDate
	 */
	public String getStrToDate() {
		return strToDate;
	}
	/**
	 * @param strToDate the strToDate to set
	 */
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	/**
	 * @return the strBillModuleId
	 */
	public String getStrBillModuleId() {
		return strBillModuleId;
	}
	/**
	 * @param strBillModuleId the strBillModuleId to set
	 */
	public void setStrBillModuleId(String strBillModuleId) {
		this.strBillModuleId = strBillModuleId;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
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
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	  

}
