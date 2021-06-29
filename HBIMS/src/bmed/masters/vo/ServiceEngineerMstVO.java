package bmed.masters.vo;


import javax.sql.rowset.WebRowSet;

public class ServiceEngineerMstVO 
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
	
	private String strMesgType;
	
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
	private String strIsValid;
	
	private String strSeatId = "";
	
    private String 	strEnggItemTypeId;
	
	private String 	strEnggItemSubTypeId;
	
    private WebRowSet wsEnggItemSubType = null;

	private WebRowSet wsEnggItemType = null;
	
	private WebRowSet wsEnggNameCmb = null;
	private WebRowSet wsEnggNameHlp = null;
	
    private	String strEnggItemTypeCmb;
	private String strEnggItemSubTypeCmb;
	private String strPkey;
	private String strServiceEnggNameId[] = null;
	private String strEmpId;
	private String arrStrStoredEmp[] = null;
	
	
	private String strServiceEnggNameCmb;


	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	
	public WebRowSet getWsEnggItemSubType() {
		return wsEnggItemSubType;
	}

	public void setWsEnggItemSubType(WebRowSet wsEnggItemSubType) {
		this.wsEnggItemSubType = wsEnggItemSubType;
	}

	public WebRowSet getWsEnggItemType() {
		return wsEnggItemType;
	}

	public void setWsEnggItemType(WebRowSet wsEnggItemType) {
		this.wsEnggItemType = wsEnggItemType;
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

	

	public String getStrServiceEnggNameCmb() {
		return strServiceEnggNameCmb;
	}

	public void setStrServiceEnggNameCmb(String strServiceEnggNameCmb) {
		this.strServiceEnggNameCmb = strServiceEnggNameCmb;
	}

	public String getStrMesgType() {
		return strMesgType;
	}

	public void setStrMesgType(String strMesgType) {
		this.strMesgType = strMesgType;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public Boolean getBExistStatus() {
		return BExistStatus;
	}

	public void setBExistStatus(Boolean existStatus) {
		BExistStatus = existStatus;
	}

	public String getStrPkey() {
		return strPkey;
	}

	public void setStrPkey(String strPkey) {
		this.strPkey = strPkey;
	}

	public String[] getStrServiceEnggNameId() {
		return strServiceEnggNameId;
	}

	public void setStrServiceEnggNameId(String[] strServiceEnggNameId) {
		this.strServiceEnggNameId = strServiceEnggNameId;
	}

	public WebRowSet getWsEnggNameHlp() {
		return wsEnggNameHlp;
	}

	public void setWsEnggNameHlp(WebRowSet wsEnggNameHlp) {
		this.wsEnggNameHlp = wsEnggNameHlp;
	}

	public String getStrEmpId() {
		return strEmpId;
	}

	public void setStrEmpId(String strEmpId) {
		this.strEmpId = strEmpId;
	}

	public String[] getArrStrStoredEmp() {
		return arrStrStoredEmp;
	}

	public void setArrStrStoredEmp(String[] arrStrStoredEmp) {
		this.arrStrStoredEmp = arrStrStoredEmp;
	}
}
