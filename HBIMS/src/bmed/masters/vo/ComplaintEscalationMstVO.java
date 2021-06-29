package bmed.masters.vo;

import javax.sql.rowset.WebRowSet;

import hisglobal.utility.TransferObject;

public class ComplaintEscalationMstVO implements TransferObject
{
	/** The Constant serialVersionUID. */
  private static final long serialVersionUID = 02L;

	
  /** The str err. */
	private String strErr;
	/** The B exist status. */
	private Boolean BExistStatus = false;

	
	/** The str msg. */
	private String strMsg;
	
	/** The str warning. */
	private String strWarning;
	
	/** The str msg string. */
	private String strMsgString;
	
	/** The str msg type. */
	private String strMsgType="0";
	
	/** The str hospital code. */
	private String strHospitalCode;
	
	
	/** The str remarks. */
	private String strRemarks;
	
	/** The str effective from. */
	private String strEffectiveFrom;
	
	/** The str ct date. */
	private String strCtDate;
	
	/** The str chk. */
	private String strChk;
	
	/** The str is valid. */
	private String strIsValid="1";
	
	private String strSeatId = "";
	
	private String 	strEnggItemTypeId;
	
	private String 	strEnggItemSubTypeId;
		
	private String strEnggItemTypeCmb;
	private String strEnggItemSubTypeCmb;
	
	private WebRowSet wsUnitName;
	private WebRowSet wsEnggNameCmb;
	private WebRowSet wsLevelTypeCmb;
	
	private String strLevelTypeCmb;
	private String strEmpNameCmb;
	private String strUnitIdCmb;
	
	private String strEmpNameId;
	
	private String strUnitId;
	private String strPeriod;
	private String strLevelType;
	private String strEmpNo;
	private String strEmpInfo;
	
	private String strContactNo;
	private String strEmailId;
	private String strUniID;

	
	public String getStrUniID() {
		return strUniID;
	}
	public void setStrUniID(String strUniID) {
		this.strUniID = strUniID;
	}
	public String getStrContactNo() {
		return strContactNo;
	}
	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}
	public String getStrEmailId() {
		return strEmailId;
	}
	public void setStrEmailId(String strEmailId) {
		this.strEmailId = strEmailId;
	}
	public String getStrEmpInfo() {
		return strEmpInfo;
	}
	public void setStrEmpInfo(String strEmpInfo) {
		this.strEmpInfo = strEmpInfo;
	}
	public String getStrEmpNo() {
		return strEmpNo;
	}
	public void setStrEmpNo(String strEmpNo) {
		this.strEmpNo = strEmpNo;
	}
	public String getStrLevelTypeCmb() {
		return strLevelTypeCmb;
	}
	public void setStrLevelTypeCmb(String strLevelTypeCmb) {
		this.strLevelTypeCmb = strLevelTypeCmb;
	}
	public String getStrEmpNameCmb() {
		return strEmpNameCmb;
	}
	public void setStrEmpNameCmb(String strEmpNameCmb) {
		this.strEmpNameCmb = strEmpNameCmb;
	}
	public String getStrUnitIdCmb() {
		return strUnitIdCmb;
	}
	public void setStrUnitIdCmb(String strUnitIdCmb) {
		this.strUnitIdCmb = strUnitIdCmb;
	}
	public WebRowSet getWsUnitName() {
		return wsUnitName;
	}
	public void setWsUnitName(WebRowSet wsUnitName) {
		this.wsUnitName = wsUnitName;
	}
	public String getStrErr() {
		return strErr;
	}
	public void setStrErr(String strErr) {
		this.strErr = strErr;
	}
	public String getStrMsg() {
		return strMsg;
	}
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	public String getStrWarning() {
		return strWarning;
	}
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
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
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrCtDate() {
		return strCtDate;
	}
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	public String getStrChk() {
		return strChk;
	}
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public String getStrSeatId() {
		return strSeatId;
	}
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	public String getStrEnggItemTypeId() {
		return strEnggItemTypeId;
	}
	public void setStrEnggItemTypeId(String strEnggItemTypeId) {
		this.strEnggItemTypeId = strEnggItemTypeId;
	}
	public String getStrEnggItemSubTypeId() {
		return strEnggItemSubTypeId;
	}
	public void setStrEnggItemSubTypeId(String strEnggItemSubTypeId) {
		this.strEnggItemSubTypeId = strEnggItemSubTypeId;
	}
	public String getStrEnggItemTypeCmb() {
		return strEnggItemTypeCmb;
	}
	public void setStrEnggItemTypeCmb(String strEnggItemTypeCmb) {
		this.strEnggItemTypeCmb = strEnggItemTypeCmb;
	}
	public String getStrEnggItemSubTypeCmb() {
		return strEnggItemSubTypeCmb;
	}
	public void setStrEnggItemSubTypeCmb(String strEnggItemSubTypeCmb) {
		this.strEnggItemSubTypeCmb = strEnggItemSubTypeCmb;
	}
	public WebRowSet getWsEnggNameCmb() {
		return wsEnggNameCmb;
	}
	public void setWsEnggNameCmb(WebRowSet wsEnggNameCmb) {
		this.wsEnggNameCmb = wsEnggNameCmb;
	}
	public String getStrEmpNameId() {
		return strEmpNameId;
	}
	public void setStrEmpNameId(String strEmpNameId) {
		this.strEmpNameId = strEmpNameId;
	}
	public String getStrUnitId() {
		return strUnitId;
	}
	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}
	public String getStrPeriod() {
		return strPeriod;
	}
	public void setStrPeriod(String strPeriod) {
		this.strPeriod = strPeriod;
	}
	public String getStrLevelType() {
		return strLevelType;
	}
	public void setStrLevelType(String strLevelType) {
		this.strLevelType = strLevelType;
	}
	public Boolean getBExistStatus() {
		return BExistStatus;
	}
	public void setBExistStatus(Boolean existStatus) {
		BExistStatus = existStatus;
	}
	public WebRowSet getWsLevelTypeCmb() {
		return wsLevelTypeCmb;
	}
	public void setWsLevelTypeCmb(WebRowSet wsLevelTypeCmb) {
		this.wsLevelTypeCmb = wsLevelTypeCmb;
	}

}
