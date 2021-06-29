package mms.masters.vo;

import javax.sql.rowset.WebRowSet;
import hisglobal.utility.TransferObject;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthorizationMstVO.
 */
public class AuthorizationMstVO implements TransferObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The str store id. */
	private String strStoreId = "0";

	/** The str user id. */
	private String strUserId = "";
	
	/** The str user name. */
	private String strUserName = "";
	
	/** The str authorization type. */
	private String strAuthorizationType = "";
	
	/** The str authorization type id. */
	private String strAuthorizationTypeId = "";
	
	/** The str authorization level. */
	private String strAuthorizationLevel = "";
	
	/** The str authorization level id. */
	private String strAuthorizationLevelId = "";
	
	/** The str authorization sl no. */
	private String strAuthorizationSlNo = "";
	
	/** The str cost form. */
	private String strCostForm = "";
	
	/** The str cost. */
	private String strCost = "";
	
	/** The str chk. */
	private String strChk = "";

	/** The str effective from. */
	private String strEffectiveFrom = "";
	
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

	/** The str msg string. */
	private String strMsgString = "";
	
	/** The str msg type. */
	private String strMsgType = "";

	/** The str store type cmb ws. */
	private WebRowSet strStoreTypeCmbWs = null;
	
	/** The str store type combo. */
	private String strStoreTypeCombo = "";

	/** The str store name cmb ws. */
	private WebRowSet strStoreNameCmbWs = null;
	
	/** The str store name combo. */
	private String strStoreNameCombo = "";
	
	/** The str user name combo ws. */
	private WebRowSet strUserNameComboWS = null;
	
	/** The str user name combo. */
	private String strUserNameCombo = "";
	
	/** The b exist status. */
	private Boolean bExistStatus = null;

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
	 * Gets the str effective from.
	 * 
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	/**
	 * Sets the str effective from.
	 * 
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * Gets the str remarks.
	 * 
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * Sets the str remarks.
	 * 
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * Gets the str entry date.
	 * 
	 * @return the strEntryDate
	 */
	public String getStrEntryDate() {
		return strEntryDate;
	}

	/**
	 * Sets the str entry date.
	 * 
	 * @param strEntryDate the strEntryDate to set
	 */
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	/**
	 * Gets the str last modified date.
	 * 
	 * @return the strLastModifiedDate
	 */
	public String getStrLastModifiedDate() {
		return strLastModifiedDate;
	}

	/**
	 * Sets the str last modified date.
	 * 
	 * @param strLastModifiedDate the strLastModifiedDate to set
	 */
	public void setStrLastModifiedDate(String strLastModifiedDate) {
		this.strLastModifiedDate = strLastModifiedDate;
	}

	/**
	 * Gets the str seat id.
	 * 
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * Sets the str seat id.
	 * 
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * Gets the str last modified seat id.
	 * 
	 * @return the strLastModifiedSeatId
	 */
	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}

	/**
	 * Sets the str last modified seat id.
	 * 
	 * @param strLastModifiedSeatId the strLastModifiedSeatId to set
	 */
	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}

	/**
	 * Gets the str is valid.
	 * 
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}

	/**
	 * Sets the str is valid.
	 * 
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * Gets the str hospital code.
	 * 
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * Sets the str hospital code.
	 * 
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * Gets the str msg string.
	 * 
	 * @return the strMsgString
	 */
	public String getStrMsgString() {
		return strMsgString;
	}

	/**
	 * Sets the str msg string.
	 * 
	 * @param strMsgString the strMsgString to set
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	/**
	 * Gets the str msg type.
	 * 
	 * @return the strMsgType
	 */
	public String getStrMsgType() {
		return strMsgType;
	}

	/**
	 * Sets the str msg type.
	 * 
	 * @param strMsgType the strMsgType to set
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	/**
	 * Gets the str store type combo.
	 * 
	 * @return the strStoreTypeCombo
	 */
	public String getStrStoreTypeCombo() {
		return strStoreTypeCombo;
	}

	/**
	 * Sets the str store type combo.
	 * 
	 * @param strStoreTypeCombo the strStoreTypeCombo to set
	 */
	public void setStrStoreTypeCombo(String strStoreTypeCombo) {
		this.strStoreTypeCombo = strStoreTypeCombo;
	}

	/**
	 * Gets the str store type cmb ws.
	 * 
	 * @return the strStoreTypeCmbWs
	 */
	public WebRowSet getStrStoreTypeCmbWs() {
		return strStoreTypeCmbWs;
	}

	/**
	 * Sets the str store type cmb ws.
	 * 
	 * @param strStoreTypeCmbWs the strStoreTypeCmbWs to set
	 */
	public void setStrStoreTypeCmbWs(WebRowSet strStoreTypeCmbWs) {
		this.strStoreTypeCmbWs = strStoreTypeCmbWs;
	}

	/**
	 * Gets the str store name cmb ws.
	 * 
	 * @return the strStoreNameCmbWs
	 */
	public WebRowSet getStrStoreNameCmbWs() {
		return strStoreNameCmbWs;
	}

	/**
	 * Sets the str store name cmb ws.
	 * 
	 * @param strStoreNameCmbWs the strStoreNameCmbWs to set
	 */
	public void setStrStoreNameCmbWs(WebRowSet strStoreNameCmbWs) {
		this.strStoreNameCmbWs = strStoreNameCmbWs;
	}

	/**
	 * Gets the str store name combo.
	 * 
	 * @return the strStoreNameCombo
	 */
	public String getStrStoreNameCombo() {
		return strStoreNameCombo;
	}

	/**
	 * Sets the str store name combo.
	 * 
	 * @param strStoreNameCombo the strStoreNameCombo to set
	 */
	public void setStrStoreNameCombo(String strStoreNameCombo) {
		this.strStoreNameCombo = strStoreNameCombo;
	}

	/**
	 * Gets the str user id.
	 * 
	 * @return the str user id
	 */
	public String getStrUserId() {
		return strUserId;
	}

	/**
	 * Sets the str user id.
	 * 
	 * @param strUserId the new str user id
	 */
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}

	/**
	 * Gets the str authorization type.
	 * 
	 * @return the str authorization type
	 */
	public String getStrAuthorizationType() {
		return strAuthorizationType;
	}

	/**
	 * Sets the str authorization type.
	 * 
	 * @param strAuthorizationType the new str authorization type
	 */
	public void setStrAuthorizationType(String strAuthorizationType) {
		this.strAuthorizationType = strAuthorizationType;
	}

	/**
	 * Gets the str authorization level.
	 * 
	 * @return the str authorization level
	 */
	public String getStrAuthorizationLevel() {
		return strAuthorizationLevel;
	}

	/**
	 * Sets the str authorization level.
	 * 
	 * @param strAuthorizationLevel the new str authorization level
	 */
	public void setStrAuthorizationLevel(String strAuthorizationLevel) {
		this.strAuthorizationLevel = strAuthorizationLevel;
	}

	/**
	 * Gets the str cost form.
	 * 
	 * @return the str cost form
	 */
	public String getStrCostForm() {
		return strCostForm;
	}

	/**
	 * Sets the str cost form.
	 * 
	 * @param strCostForm the new str cost form
	 */
	public void setStrCostForm(String strCostForm) {
		this.strCostForm = strCostForm;
	}

	/**
	 * Gets the str cost.
	 * 
	 * @return the str cost
	 */
	public String getStrCost() {
		return strCost;
	}

	/**
	 * Sets the str cost.
	 * 
	 * @param strCost the new str cost
	 */
	public void setStrCost(String strCost) {
		this.strCost = strCost;
	}

	/**
	 * Gets the str user name.
	 * 
	 * @return the str user name
	 */
	public String getStrUserName() {
		return strUserName;
	}

	/**
	 * Sets the str user name.
	 * 
	 * @param strUserName the new str user name
	 */
	public void setStrUserName(String strUserName) {
		this.strUserName = strUserName;
	}

	/**
	 * Gets the str user name combo ws.
	 * 
	 * @return the str user name combo ws
	 */
	public WebRowSet getStrUserNameComboWS() {
		return strUserNameComboWS;
	}

	/**
	 * Sets the str user name combo ws.
	 * 
	 * @param strUserNameComboWS the new str user name combo ws
	 */
	public void setStrUserNameComboWS(WebRowSet strUserNameComboWS) {
		this.strUserNameComboWS = strUserNameComboWS;
	}

	/**
	 * Gets the str user name combo.
	 * 
	 * @return the str user name combo
	 */
	public String getStrUserNameCombo() {
		return strUserNameCombo;
	}

	/**
	 * Sets the str user name combo.
	 * 
	 * @param strUserNameCombo the new str user name combo
	 */
	public void setStrUserNameCombo(String strUserNameCombo) {
		this.strUserNameCombo = strUserNameCombo;
	}

	/**
	 * Gets the str authorization sl no.
	 * 
	 * @return the str authorization sl no
	 */
	public String getStrAuthorizationSlNo() {
		return strAuthorizationSlNo;
	}

	/**
	 * Sets the str authorization sl no.
	 * 
	 * @param strAuthorizationSlNo the new str authorization sl no
	 */
	public void setStrAuthorizationSlNo(String strAuthorizationSlNo) {
		this.strAuthorizationSlNo = strAuthorizationSlNo;
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
	 * Gets the str authorization type id.
	 * 
	 * @return the str authorization type id
	 */
	public String getStrAuthorizationTypeId() {
		return strAuthorizationTypeId;
	}

	/**
	 * Sets the str authorization type id.
	 * 
	 * @param strAuthorizationTypeId the new str authorization type id
	 */
	public void setStrAuthorizationTypeId(String strAuthorizationTypeId) {
		this.strAuthorizationTypeId = strAuthorizationTypeId;
	}

	/**
	 * Gets the str authorization level id.
	 * 
	 * @return the str authorization level id
	 */
	public String getStrAuthorizationLevelId() {
		return strAuthorizationLevelId;
	}

	/**
	 * Sets the str authorization level id.
	 * 
	 * @param strAuthorizationLevelId the new str authorization level id
	 */
	public void setStrAuthorizationLevelId(String strAuthorizationLevelId) {
		this.strAuthorizationLevelId = strAuthorizationLevelId;
	}

	/**
	 * Gets the str store id.
	 * 
	 * @return the strStoreId
	 */
	public String getStrStoreId() {
		return strStoreId;
	}

	/**
	 * Sets the str store id.
	 * 
	 * @param strStoreId the strStoreId to set
	 */
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
	}

	/**
	 * @return the strStoreNameCmbWs
	 */

	/**
	 * @return the strStoreNameCombo
	 */

	/**
	 * @return the strStoreNameCmbWs
	 */

}
