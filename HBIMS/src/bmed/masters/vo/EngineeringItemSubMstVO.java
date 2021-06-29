package bmed.masters.vo;

import javax.sql.rowset.WebRowSet;

/**
 * @author   
 * Creation Date:- 11-jan-2011 
 * Modifying Date:- 
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class EngineeringItemSubMstVO   {
	
	/** The str seat id. */
	private String strSeatId = "";
	
	/** The str is valid. */
	private String strIsValid = "1";
	
	/** The str hospital code. */
	private String strHospitalCode = "";
	
	/** The str eng item type. */
	
	private String strEngItemType ="";
	
	/** the str eng item type id */
	
	private String strEngItemTypeId ="";
	
	/** the str eng item sub type id */
	private String strEngItemSubTypeId ="";
	
	/** The str eng item sub type name */
	
	private String strEngItemSubTypeName = "";
	
	/** The str effective date from */
	
	
	
	private String strEffectiveFrom = "";

	/** The str remarks */
	
	private String strRemarks ="";
	
	
	/** The str ct date. */
	private String strCtDate = null;
	
	/** The str err. */
	private String strErr = "";
	
	/** The str msg. */
	private String strMsg = "";
	

	/** The str msg type. */
	private String strMsgType = "0";

	/** The str msg string. */
	private String strMsgString = "";
	
	/** The str warning. */
	private String strWarning = "";

	/** The is visible. */
	private String isVisible = "";
	
	/** The str is child. */
	private String strIsChild = "0";
	
	/** The strparent cmb ws. */
	private WebRowSet enggItemTypeCmbWS = null;
	
	/** The strparent name combo. */
	private String strparentNameCombo = "";

	/** The str error msg. */
	private String strErrorMsg = "";
	
	/** The str chk. */
	private String strChk = "";
	
	/** The str error. */
	private String strError = "";

	/** The get str msg. */
	private String getStrMsg = "";

	/** The B exist status. */
	private Boolean BExistStatus = false;
	
	private String strEnggItemTypeCmb;
	
	private String strEnggItemTypeId;
	
	/** The str entry date. */
	private String strEntryDate = "";
	
	/** The seat Id last modified */
	private String strSeatIdLastModified ="";
	
	/* The seat Id last modified */
	private String strLastMofifiedDate ="";
	
	public String getStrEnggItemTypeCmb() {
		return strEnggItemTypeCmb;
	}

	public void setStrEnggItemTypeCmb(String strEnggItemTypeCmb) {
		this.strEnggItemTypeCmb = strEnggItemTypeCmb;
	}

	public String getStrEnggItemTypeId() {
		return strEnggItemTypeId;
	}

	public void setStrEnggItemTypeId(String strEnggItemTypeId) {
		this.strEnggItemTypeId = strEnggItemTypeId;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrEngItemType() {
		return strEngItemType;
	}

	public void setStrEngItemType(String strEngItemType) {
		this.strEngItemType = strEngItemType;
	}

	public String getStrEngItemSubTypeName() {
		return strEngItemSubTypeName;
	}

	public void setStrEngItemSubTypeName(String strEngItemSubTypeName) {
		this.strEngItemSubTypeName = strEngItemSubTypeName;
	}


	public String getStrCtDate() {
		return strCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
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

	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}

	public String getStrIsChild() {
		return strIsChild;
	}

	public void setStrIsChild(String strIsChild) {
		this.strIsChild = strIsChild;
	}

	

	public String getStrparentNameCombo() {
		return strparentNameCombo;
	}

	public void setStrparentNameCombo(String strparentNameCombo) {
		this.strparentNameCombo = strparentNameCombo;
	}

	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	public String getStrChk() {
		return strChk;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	public String getStrError() {
		return strError;
	}

	public void setStrError(String strError) {
		this.strError = strError;
	}

	public String getGetStrMsg() {
		return getStrMsg;
	}

	public void setGetStrMsg(String getStrMsg) {
		this.getStrMsg = getStrMsg;
	}

	public String getStrEngItemTypeId() {
		return strEngItemTypeId;
	}

	public void setStrEngItemTypeId(String strEngItemTypeId) {
		this.strEngItemTypeId = strEngItemTypeId;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrEngItemSubTypeId() {
		return strEngItemSubTypeId;
	}

	public void setStrEngItemSubTypeId(String strEngItemSubTypeId) {
		this.strEngItemSubTypeId = strEngItemSubTypeId;
	}


	public WebRowSet getEnggItemTypeCmbWS() {
		return enggItemTypeCmbWS;
	}

	public void setEnggItemTypeCmbWS(WebRowSet enggItemTypeCmbWS) {
		this.enggItemTypeCmbWS = enggItemTypeCmbWS;
	}

	public String getStrEntryDate() {
		return strEntryDate;
	}

	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	public String getstrSeatIdLastModified() {
		return strSeatIdLastModified;
	}

	public void setstrSeatIdLastModified(String strSeatIdLastModified) {
		this.strSeatIdLastModified = strSeatIdLastModified;
	}

	public String getstrLastMofifiedDate() {
		return strLastMofifiedDate;
	}

	public void setstrLastMofifiedDate(String strLastMofifiedDate) {
		this.strLastMofifiedDate = strLastMofifiedDate;
	}

	public String getStrSeatIdLastModified() {
		return strSeatIdLastModified;
	}

	public void setStrSeatIdLastModified(String strSeatIdLastModified) {
		this.strSeatIdLastModified = strSeatIdLastModified;
	}

	public String getStrLastMofifiedDate() {
		return strLastMofifiedDate;
	}

	public void setStrLastMofifiedDate(String strLastMofifiedDate) {
		this.strLastMofifiedDate = strLastMofifiedDate;
	}

	public Boolean getBExistStatus() {
		return BExistStatus;
	}

	public void setBExistStatus(Boolean existStatus) {
		BExistStatus = existStatus;
	}

	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	
}
