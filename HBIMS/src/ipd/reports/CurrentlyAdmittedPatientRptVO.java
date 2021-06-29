package ipd.reports;

import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class CurrentlyAdmittedPatientRptVO implements TransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";
	
	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strIsRoomWise = "0";
	private String strRoomNo = "0";
	private String strReportFormat = "0";
	private String strSeatId = "";
	private String strHospitalCode = "";
	private String strHospitalName = "";
	
	
	private WebRowSet strDeptWs = null;
	private WebRowSet strUnitWs = null;
	private WebRowSet strWardWs = null;
	private WebRowSet strRoomWs = null;
	private WebRowSet strHospitalWs = null;
	private String strHospNameValues="";
	
 
	
	
	
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
	 * @return the strWardCode
	 */
	public String getStrWardCode() {
		return strWardCode;
	}
	/**
	 * @param strWardCode the strWardCode to set
	 */
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	/**
	 * @return the strIsRoomWise
	 */
	public String getStrIsRoomWise() {
		return strIsRoomWise;
	}
	/**
	 * @param strIsRoomWise the strIsRoomWise to set
	 */
	public void setStrIsRoomWise(String strIsRoomWise) {
		this.strIsRoomWise = strIsRoomWise;
	}
	/**
	 * @return the strRoomNo
	 */
	public String getStrRoomNo() {
		return strRoomNo;
	}
	/**
	 * @param strRoomNo the strRoomNo to set
	 */
	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
	}
	/**
	 * @return the strReportFormat
	 */
	public String getStrReportFormat() {
		return strReportFormat;
	}
	/**
	 * @param strReportFormat the strReportFormat to set
	 */
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	
	/**
	 * @return the strDeptCode
	 */
	public String getStrDeptCode() {
		return strDeptCode;
	}
	/**
	 * @param strDeptCode the strDeptCode to set
	 */
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	/**
	 * @return the strUnitCode
	 */
	public String getStrUnitCode() {
		return strUnitCode;
	}
	/**
	 * @param strUnitCode the strUnitCode to set
	 */
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	/**
	 * @return the strDeptWs
	 */
	public WebRowSet getStrDeptWs() {
		return strDeptWs;
	}
	/**
	 * @param strDeptWs the strDeptWs to set
	 */
	public void setStrDeptWs(WebRowSet strDeptWs) {
		this.strDeptWs = strDeptWs;
	}
	/**
	 * @return the strUnitWs
	 */
	public WebRowSet getStrUnitWs() {
		return strUnitWs;
	}
	/**
	 * @param strUnitWs the strUnitWs to set
	 */
	public void setStrUnitWs(WebRowSet strUnitWs) {
		this.strUnitWs = strUnitWs;
	}
	/**
	 * @return the strWardWs
	 */
	public WebRowSet getStrWardWs() {
		return strWardWs;
	}
	/**
	 * @param strWardWs the strWardWs to set
	 */
	public void setStrWardWs(WebRowSet strWardWs) {
		this.strWardWs = strWardWs;
	}
	/**
	 * @return the strRoomWs
	 */
	public WebRowSet getStrRoomWs() {
		return strRoomWs;
	}
	/**
	 * @param strRoomWs the strRoomWs to set
	 */
	public void setStrRoomWs(WebRowSet strRoomWs) {
		this.strRoomWs = strRoomWs;
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
	public String getStrHospitalName() {
		return strHospitalName;
	}
	public void setStrHospitalName(String strHospitalName) {
		this.strHospitalName = strHospitalName;
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
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	
	
}
