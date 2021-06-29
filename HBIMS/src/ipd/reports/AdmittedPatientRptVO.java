package ipd.reports;

import hisglobal.utility.TransferObject;

import javax.sql.rowset.WebRowSet;

public class AdmittedPatientRptVO implements TransferObject{

	
	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";
	
	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strIsRoomWise = "0";
	private String strRoomNo = "0";
	private String strReportFormat = "0";
	
	private String strHospitalCode = "";
	private String strSeatId = "";
	private String strHospitalName = "";
	private WebRowSet strDeptWs = null;
	private WebRowSet strUnitWs = null;
	private WebRowSet strWardWs = null;
	private WebRowSet strRoomWs = null;
	private WebRowSet strHospitalWs = null;
	private String strHospNameValues="";
	
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
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrIsRoomWise() {
		return strIsRoomWise;
	}
	public void setStrIsRoomWise(String strIsRoomWise) {
		this.strIsRoomWise = strIsRoomWise;
	}
	public String getStrRoomNo() {
		return strRoomNo;
	}
	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
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
	public WebRowSet getStrDeptWs() {
		return strDeptWs;
	}
	public void setStrDeptWs(WebRowSet strDeptWs) {
		this.strDeptWs = strDeptWs;
	}
	public WebRowSet getStrUnitWs() {
		return strUnitWs;
	}
	public void setStrUnitWs(WebRowSet strUnitWs) {
		this.strUnitWs = strUnitWs;
	}
	public WebRowSet getStrWardWs() {
		return strWardWs;
	}
	public void setStrWardWs(WebRowSet strWardWs) {
		this.strWardWs = strWardWs;
	}
	public WebRowSet getStrRoomWs() {
		return strRoomWs;
	}
	public void setStrRoomWs(WebRowSet strRoomWs) {
		this.strRoomWs = strRoomWs;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrHospName() {
		return strHospitalName;
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
	
}
