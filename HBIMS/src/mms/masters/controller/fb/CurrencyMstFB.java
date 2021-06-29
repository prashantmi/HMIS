package mms.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

// TODO: Auto-generated Javadoc
/**
 * The Class CurrencyMstFB.
 */
public class CurrencyMstFB extends GenericFormBean {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The str currency id. */
	private String strCurrencyId = "";
	
	/** The str currency name. */
	private String strCurrencyName = "";
	
	/** The str currency short name. */
	private String strCurrencyShortName = "";
	
	/** The str currency value. */
	private String strCurrencyValue = "";
	
	/** The str currency sl no. */
	private String strCurrencySlNo = "";

	/** The str effective from. */
	private String strEffectiveFrom = "";
	
	/** The str effective to. */
	private String strEffectiveTo = "";
	
	/** The str remarks. */
	private String strRemarks = "";
	
	/** The str entry date. */
	private String strEntryDate = "";
	
	/** The str last modified date. */
	private String strLastModifiedDate = "";
	
	/** The str seat id. */
	private String strSeatId = "";
	
	/** The str last modified seat id. */
	private String strLastModifiedSeatId = "";
	
	/** The str is valid. */
	private String strIsValid = "1";
	
	/** The str hospital code. */
	private String strHospitalCode = "";
	
	/** The str chk. */
	private String strChk = "";
	
	/** The str warning. */
	private String strWarning = "";
	
	/** The str msg. */
	private String strMsg = "";
	
	/** The str error msg. */
	private String strErrorMsg = "";
	
	/** The str ct date. */
	private String strCtDate = null;
	
	/** The b exist status. */
	private Boolean bExistStatus = null;
	
	private String strDefault="0";

	/**
	 * @return the strDefault
	 */
	public String getStrDefault() {
		return strDefault;
	}

	/**
	 * @param strDefault the strDefault to set
	 */
	public void setStrDefault(String strDefault) {
		this.strDefault = strDefault;
	}

	/**
	 * Gets the str currency id.
	 * 
	 * @return the str currency id
	 */
	public String getStrCurrencyId() {
		return strCurrencyId;
	}

	/**
	 * Sets the str currency id.
	 * 
	 * @param strCurrencyId the new str currency id
	 */
	public void setStrCurrencyId(String strCurrencyId) {
		this.strCurrencyId = strCurrencyId;
	}

	/**
	 * Gets the str currency name.
	 * 
	 * @return the str currency name
	 */
	public String getStrCurrencyName() {
		return strCurrencyName;
	}

	/**
	 * Sets the str currency name.
	 * 
	 * @param strCurrencyName the new str currency name
	 */
	public void setStrCurrencyName(String strCurrencyName) {
		this.strCurrencyName = strCurrencyName;
	}

	/**
	 * Gets the str currency short name.
	 * 
	 * @return the str currency short name
	 */
	public String getStrCurrencyShortName() {
		return strCurrencyShortName;
	}

	/**
	 * Sets the str currency short name.
	 * 
	 * @param strCurrencyShortName the new str currency short name
	 */
	public void setStrCurrencyShortName(String strCurrencyShortName) {
		this.strCurrencyShortName = strCurrencyShortName;
	}

	/**
	 * Gets the str currency value.
	 * 
	 * @return the str currency value
	 */
	public String getStrCurrencyValue() {
		return strCurrencyValue;
	}

	/**
	 * Sets the str currency value.
	 * 
	 * @param strCurrencyValue the new str currency value
	 */
	public void setStrCurrencyValue(String strCurrencyValue) {
		this.strCurrencyValue = strCurrencyValue;
	}

	/**
	 * Gets the str currency sl no.
	 * 
	 * @return the str currency sl no
	 */
	public String getStrCurrencySlNo() {
		return strCurrencySlNo;
	}

	/**
	 * Sets the str currency sl no.
	 * 
	 * @param strCurrencySlNo the new str currency sl no
	 */
	public void setStrCurrencySlNo(String strCurrencySlNo) {
		this.strCurrencySlNo = strCurrencySlNo;
	}

	/**
	 * Gets the str effective from.
	 * 
	 * @return the str effective from
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	/**
	 * Sets the str effective from.
	 * 
	 * @param strEffectiveFrom the new str effective from
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * Gets the str effective to.
	 * 
	 * @return the str effective to
	 */
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}

	/**
	 * Sets the str effective to.
	 * 
	 * @param strEffectiveTo the new str effective to
	 */
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	/**
	 * Gets the str remarks.
	 * 
	 * @return the str remarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * Sets the str remarks.
	 * 
	 * @param strRemarks the new str remarks
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * Gets the str entry date.
	 * 
	 * @return the str entry date
	 */
	public String getStrEntryDate() {
		return strEntryDate;
	}

	/**
	 * Sets the str entry date.
	 * 
	 * @param strEntryDate the new str entry date
	 */
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	/**
	 * Gets the str last modified date.
	 * 
	 * @return the str last modified date
	 */
	public String getStrLastModifiedDate() {
		return strLastModifiedDate;
	}

	/**
	 * Sets the str last modified date.
	 * 
	 * @param strLastModifiedDate the new str last modified date
	 */
	public void setStrLastModifiedDate(String strLastModifiedDate) {
		this.strLastModifiedDate = strLastModifiedDate;
	}

	/**
	 * Gets the str seat id.
	 * 
	 * @return the str seat id
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * Sets the str seat id.
	 * 
	 * @param strSeatId the new str seat id
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * Gets the str last modified seat id.
	 * 
	 * @return the str last modified seat id
	 */
	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}

	/**
	 * Sets the str last modified seat id.
	 * 
	 * @param strLastModifiedSeatId the new str last modified seat id
	 */
	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}

	/**
	 * Gets the str is valid.
	 * 
	 * @return the str is valid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}

	/**
	 * Sets the str is valid.
	 * 
	 * @param strIsValid the new str is valid
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * Gets the str hospital code.
	 * 
	 * @return the str hospital code
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * Sets the str hospital code.
	 * 
	 * @param strHospitalCode the new str hospital code
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * Gets the str chk.
	 * 
	 * @return the str chk
	 */
	public String getStrChk() {
		return strChk;
	}

	/**
	 * Sets the str chk.
	 * 
	 * @param strChk the new str chk
	 */
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	/**
	 * Gets the str warning.
	 * 
	 * @return the str warning
	 */
	public String getStrWarning() {
		return strWarning;
	}

	/**
	 * Sets the str warning.
	 * 
	 * @param strWarning the new str warning
	 */
	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}

	/**
	 * Gets the str msg.
	 * 
	 * @return the str msg
	 */
	public String getStrMsg() {
		return strMsg;
	}

	/**
	 * Sets the str msg.
	 * 
	 * @param strMsg the new str msg
	 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	/**
	 * Gets the str error msg.
	 * 
	 * @return the str error msg
	 */
	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	/**
	 * Sets the str error msg.
	 * 
	 * @param strErrorMsg the new str error msg
	 */
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	/**
	 * Gets the str ct date.
	 * 
	 * @return the str ct date
	 */
	public String getStrCtDate() {
		return strCtDate;
	}

	/**
	 * Gets the b exist status.
	 * 
	 * @return the b exist status
	 */
	public Boolean getBExistStatus() {
		return bExistStatus;
	}

	/**
	 * Sets the b exist status.
	 * 
	 * @param existStatus the new b exist status
	 */
	public void setBExistStatus(Boolean existStatus) {
		bExistStatus = existStatus;
	}

	/**
	 * Sets the str ct date.
	 * 
	 * @param strCtDate the new str ct date
	 */
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

}
