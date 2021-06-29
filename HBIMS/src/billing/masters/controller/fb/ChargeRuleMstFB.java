/**
 * 
 */
package billing.masters.controller.fb;

import javax.sql.rowset.WebRowSet;

import hisglobal.masterutil.GenericFormBean;

/**
 * Developer : DEEPIKA GABA	
 * Process Name : Charge Rule Master
 * Date : 2 SEP 2011
 * Version : 1.0
 * Modify Date : 
 *
 */
public class ChargeRuleMstFB extends GenericFormBean {
	
	private static final long serialVersionUID = 02L;

	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	private String strMsgType = "";
	
	private String strCtDate = "";
	
	private String strRuleId = "";
	private String strHospitalCode = "";
	private String strRuleName = "";
	private String strNewChargeTypeId = "";
	private String strNewPatientCatcode = "";
	private String strNewIPDChargeTypeId = "";
	
	private String strOldChargeTypeId = "";
	private String strOldPatientCatcode = "";
	private String strOldIPDChargeTypeId = "";
	private String strVariation = "";
	private String strRemarks = "";
	private String strEffectiveFrom = "";
	private String streffectiveTo = "";
	private String strLastModDate = "";
	private String strLastModSeatId = "";
	private String strSeatId = "";
	private String strIsValid = "";
	
	private String strModify = "1";
	private WebRowSet RuleDetails =null;
	private WebRowSet NewChargeTypeWS = null;
	private WebRowSet OldChargeTypeWS = null;
	
	private WebRowSet NewPatCatWS = null;
	private WebRowSet OldPatCatWS = null;
	
	private WebRowSet NewIPDChargeTypeWS = null;
	private WebRowSet OldIPDChargeTypeWS = null;
	
	private String strRuleDetails = "";
	private String strNewChargeTypeCombo = "";
	
	private String strOldChargeTypeCombo = "";
	// Hidden Values
	private String strHidRuleId = "";
	
	private String strHNewIPDChargeTypeId="";
	private String strHOldIPDChargeTypeId="";
	private String strHNewPatientCatcode="";
	private String strHOldPatientCatcode="";
	private String strNewChargeComboValues="";
	
	
	
	/**
	 * @return the strHidRuleId
	 */
	public String getStrHidRuleId() {
		return strHidRuleId;
	}
	/**
	 * @param strHidRuleId the strHidRuleId to set
	 */
	public void setStrHidRuleId(String strHidRuleId) {
		this.strHidRuleId = strHidRuleId;
	}
	/**
	 * @return the strRuleDetails
	 */
	public String getStrRuleDetails() {
		return strRuleDetails;
	}
	/**
	 * @param strRuleDetails the strRuleDetails to set
	 */
	public void setStrRuleDetails(String strRuleDetails) {
		this.strRuleDetails = strRuleDetails;
	}
	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}
	/**
	 * @param strErrMsg the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	/**
	 * @param strNormalMsg the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	/**
	 * @return the strWarningMsg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	/**
	 * @param strWarningMsg the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	/**
	 * @return the strRuleId
	 */
	public String getStrRuleId() {
		return strRuleId;
	}
	/**
	 * @param strRuleId the strRuleId to set
	 */
	public void setStrRuleId(String strRuleId) {
		this.strRuleId = strRuleId;
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
	 * @return the strRuleName
	 */
	public String getStrRuleName() {
		return strRuleName;
	}
	/**
	 * @param strRuleName the strRuleName to set
	 */
	public void setStrRuleName(String strRuleName) {
		this.strRuleName = strRuleName;
	}
	/**
	 * @return the strNewChargeTypeId
	 */
	public String getStrNewChargeTypeId() {
		return strNewChargeTypeId;
	}
	/**
	 * @param strNewChargeTypeId the strNewChargeTypeId to set
	 */
	public void setStrNewChargeTypeId(String strNewChargeTypeId) {
		this.strNewChargeTypeId = strNewChargeTypeId;
	}
	/**
	 * @return the strNewPatientCatcode
	 */
	public String getStrNewPatientCatcode() {
		return strNewPatientCatcode;
	}
	/**
	 * @param strNewPatientCatcode the strNewPatientCatcode to set
	 */
	public void setStrNewPatientCatcode(String strNewPatientCatcode) {
		this.strNewPatientCatcode = strNewPatientCatcode;
	}
	/**
	 * @return the strNewIPDChargeTypeId
	 */
	public String getStrNewIPDChargeTypeId() {
		return strNewIPDChargeTypeId;
	}
	/**
	 * @param strNewIPDChargeTypeId the strNewIPDChargeTypeId to set
	 */
	public void setStrNewIPDChargeTypeId(String strNewIPDChargeTypeId) {
		this.strNewIPDChargeTypeId = strNewIPDChargeTypeId;
	}
	/**
	 * @return the strOldChargeTypeId
	 */
	public String getStrOldChargeTypeId() {
		return strOldChargeTypeId;
	}
	/**
	 * @param strOldChargeTypeId the strOldChargeTypeId to set
	 */
	public void setStrOldChargeTypeId(String strOldChargeTypeId) {
		this.strOldChargeTypeId = strOldChargeTypeId;
	}
	/**
	 * @return the strOldPatientCatcode
	 */
	public String getStrOldPatientCatcode() {
		return strOldPatientCatcode;
	}
	/**
	 * @param strOldPatientCatcode the strOldPatientCatcode to set
	 */
	public void setStrOldPatientCatcode(String strOldPatientCatcode) {
		this.strOldPatientCatcode = strOldPatientCatcode;
	}
	/**
	 * @return the strOldIPDChargeTypeId
	 */
	public String getStrOldIPDChargeTypeId() {
		return strOldIPDChargeTypeId;
	}
	/**
	 * @param strOldIPDChargeTypeId the strOldIPDChargeTypeId to set
	 */
	public void setStrOldIPDChargeTypeId(String strOldIPDChargeTypeId) {
		this.strOldIPDChargeTypeId = strOldIPDChargeTypeId;
	}
	/**
	 * @return the strVariation
	 */
	public String getStrVariation() {
		return strVariation;
	}
	/**
	 * @param strVariation the strVariation to set
	 */
	public void setStrVariation(String strVariation) {
		this.strVariation = strVariation;
	}
	/**
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}
	/**
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	/**
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	/**
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	/**
	 * @return the streffectiveTo
	 */
	public String getStreffectiveTo() {
		return streffectiveTo;
	}
	/**
	 * @param streffectiveTo the streffectiveTo to set
	 */
	public void setStreffectiveTo(String streffectiveTo) {
		this.streffectiveTo = streffectiveTo;
	}
	/**
	 * @return the strLastModDate
	 */
	public String getStrLastModDate() {
		return strLastModDate;
	}
	/**
	 * @param strLastModDate the strLastModDate to set
	 */
	public void setStrLastModDate(String strLastModDate) {
		this.strLastModDate = strLastModDate;
	}
	/**
	 * @return the strLastModSeatId
	 */
	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}
	/**
	 * @param strLastModSeatId the strLastModSeatId to set
	 */
	public void setStrLastModSeatId(String strLastModSeatId) {
		this.strLastModSeatId = strLastModSeatId;
	}
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}
	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	/**
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}
	/**
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	/**
	 * @return the ruleDetails
	 */
	public WebRowSet getRuleDetails() {
		return RuleDetails;
	}
	/**
	 * @param ruleDetails the ruleDetails to set
	 */
	public void setRuleDetails(WebRowSet ruleDetails) {
		RuleDetails = ruleDetails;
	}
	/**
	 * @return the strNewChargeTypeCombo
	 */
	public String getStrNewChargeTypeCombo() {
		return strNewChargeTypeCombo;
	}
	/**
	 * @param strNewChargeTypeCombo the strNewChargeTypeCombo to set
	 */
	public void setStrNewChargeTypeCombo(String strNewChargeTypeCombo) {
		this.strNewChargeTypeCombo = strNewChargeTypeCombo;
	}
	/**
	 * @return the strNewPatientCatcodeCombo
	 *//*
	public String getStrNewPatientCatcodeCombo() {
		return strNewPatientCatcodeCombo;
	}
	*//**
	 * @param strNewPatientCatcodeCombo the strNewPatientCatcodeCombo to set
	 *//*
	public void setStrNewPatientCatcodeCombo(String strNewPatientCatcodeCombo) {
		this.strNewPatientCatcodeCombo = strNewPatientCatcodeCombo;
	}
	*//**
	 * @return the strNewIPDChargeTypeCombo
	 *//*
	public String getStrNewIPDChargeTypeCombo() {
		return strNewIPDChargeTypeCombo;
	}
	*//**
	 * @param strNewIPDChargeTypeCombo the strNewIPDChargeTypeCombo to set
	 *//*
	public void setStrNewIPDChargeTypeCombo(String strNewIPDChargeTypeCombo) {
		this.strNewIPDChargeTypeCombo = strNewIPDChargeTypeCombo;
	}*/
	/**
	 * @return the strOldChargeTypeCombo
	 */
	public String getStrOldChargeTypeCombo() {
		return strOldChargeTypeCombo;
	}
	/**
	 * @param strOldChargeTypeCombo the strOldChargeTypeCombo to set
	 */
	public void setStrOldChargeTypeCombo(String strOldChargeTypeCombo) {
		this.strOldChargeTypeCombo = strOldChargeTypeCombo;
	}
	/**
	 * @return the strOldPatientCatcodeCombo
	 *//*
	public String getStrOldPatientCatcodeCombo() {
		return strOldPatientCatcodeCombo;
	}
	*//**
	 * @param strOldPatientCatcodeCombo the strOldPatientCatcodeCombo to set
	 *//*
	public void setStrOldPatientCatcodeCombo(String strOldPatientCatcodeCombo) {
		this.strOldPatientCatcodeCombo = strOldPatientCatcodeCombo;
	}
	*//**
	 * @return the strOldIPDChargeTypeCombo
	 *//*
	public String getStrOldIPDChargeTypeCombo() {
		return strOldIPDChargeTypeCombo;
	}
	*//**
	 * @param strOldIPDChargeTypeCombo the strOldIPDChargeTypeCombo to set
	 *//*
	public void setStrOldIPDChargeTypeCombo(String strOldIPDChargeTypeCombo) {
		this.strOldIPDChargeTypeCombo = strOldIPDChargeTypeCombo;
	}*/
	/**
	 * @return the newChargeTypeWS
	 */
	public WebRowSet getNewChargeTypeWS() {
		return NewChargeTypeWS;
	}
	/**
	 * @param newChargeTypeWS the newChargeTypeWS to set
	 */
	public void setNewChargeTypeWS(WebRowSet newChargeTypeWS) {
		NewChargeTypeWS = newChargeTypeWS;
	}
	/**
	 * @return the oldChargeTypeWS
	 */
	public WebRowSet getOldChargeTypeWS() {
		return OldChargeTypeWS;
	}
	/**
	 * @param oldChargeTypeWS the oldChargeTypeWS to set
	 */
	public void setOldChargeTypeWS(WebRowSet oldChargeTypeWS) {
		OldChargeTypeWS = oldChargeTypeWS;
	}
	/**
	 * @return the newIPDChargeTypeWS
	 */
	public WebRowSet getNewIPDChargeTypeWS() {
		return NewIPDChargeTypeWS;
	}
	/**
	 * @param newIPDChargeTypeWS the newIPDChargeTypeWS to set
	 */
	public void setNewIPDChargeTypeWS(WebRowSet newIPDChargeTypeWS) {
		NewIPDChargeTypeWS = newIPDChargeTypeWS;
	}
	/**
	 * @return the oldIPDChargeTypeWS
	 */
	public WebRowSet getOldIPDChargeTypeWS() {
		return OldIPDChargeTypeWS;
	}
	/**
	 * @param oldIPDChargeTypeWS the oldIPDChargeTypeWS to set
	 */
	public void setOldIPDChargeTypeWS(WebRowSet oldIPDChargeTypeWS) {
		OldIPDChargeTypeWS = oldIPDChargeTypeWS;
	}
	/**
	 * @return the newPatCatWS
	 */
	public WebRowSet getNewPatCatWS() {
		return NewPatCatWS;
	}
	/**
	 * @param newPatCatWS the newPatCatWS to set
	 */
	public void setNewPatCatWS(WebRowSet newPatCatWS) {
		NewPatCatWS = newPatCatWS;
	}
	/**
	 * @return the oldPatCatWS
	 */
	public WebRowSet getOldPatCatWS() {
		return OldPatCatWS;
	}
	/**
	 * @param oldPatCatWS the oldPatCatWS to set
	 */
	public void setOldPatCatWS(WebRowSet oldPatCatWS) {
		OldPatCatWS = oldPatCatWS;
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
	 * @return the strCtDate
	 */
	public String getStrCtDate() {
		return strCtDate;
	}
	/**
	 * @param strCtDate the strCtDate to set
	 */
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
	/**
	 * @return the strModify
	 */
	public String getStrModify() {
		return strModify;
	}
	/**
	 * @param strModify the strModify to set
	 */
	public void setStrModify(String strModify) {
		this.strModify = strModify;
	}
	/**
	 * @return the strNewChargeType
	 *//*
	public String getStrNewChargeType() {
		return strNewChargeType;
	}
	*//**
	 * @param strNewChargeType the strNewChargeType to set
	 *//*
	public void setStrNewChargeType(String strNewChargeType) {
		this.strNewChargeType = strNewChargeType;
	}
	*//**
	 * @return the strOldChargeType
	 *//*
	public String getStrOldChargeType() {
		return strOldChargeType;
	}
	*//**
	 * @param strOldChargeType the strOldChargeType to set
	 *//*
	public void setStrOldChargeType(String strOldChargeType) {
		this.strOldChargeType = strOldChargeType;
	}
	*//**
	 * @return the strNewIPDChargeType
	 *//*
	public String getStrNewIPDChargeType() {
		return strNewIPDChargeType;
	}
	*//**
	 * @param strNewIPDChargeType the strNewIPDChargeType to set
	 *//*
	public void setStrNewIPDChargeType(String strNewIPDChargeType) {
		this.strNewIPDChargeType = strNewIPDChargeType;
	}
	*//**
	 * @return the strNewOldChargeType
	 *//*
	public String getStrNewOldChargeType() {
		return strNewOldChargeType;
	}
	*//**
	 * @param strNewOldChargeType the strNewOldChargeType to set
	 *//*
	public void setStrNewOldChargeType(String strNewOldChargeType) {
		this.strNewOldChargeType = strNewOldChargeType;
	}
	*//**
	 * @return the strNewPatientCat
	 *//*
	public String getStrNewPatientCat() {
		return strNewPatientCat;
	}
	*//**
	 * @param strNewPatientCat the strNewPatientCat to set
	 *//*
	public void setStrNewPatientCat(String strNewPatientCat) {
		this.strNewPatientCat = strNewPatientCat;
	}
	*//**
	 * @return the strOldPatientCat
	 *//*
	public String getStrOldPatientCat() {
		return strOldPatientCat;
	}
	*//**
	 * @param strOldPatientCat the strOldPatientCat to set
	 *//*
	public void setStrOldPatientCat(String strOldPatientCat) {
		this.strOldPatientCat = strOldPatientCat;
	}*/
	/**
	 * @return the strHNewIPDChargeTypeId
	 */
	public String getStrHNewIPDChargeTypeId() {
		return strHNewIPDChargeTypeId;
	}
	/**
	 * @param strHNewIPDChargeTypeId the strHNewIPDChargeTypeId to set
	 */
	public void setStrHNewIPDChargeTypeId(String strHNewIPDChargeTypeId) {
		this.strHNewIPDChargeTypeId = strHNewIPDChargeTypeId;
	}
	/**
	 * @return the strHOldIPDChargeTypeId
	 */
	public String getStrHOldIPDChargeTypeId() {
		return strHOldIPDChargeTypeId;
	}
	/**
	 * @param strHOldIPDChargeTypeId the strHOldIPDChargeTypeId to set
	 */
	public void setStrHOldIPDChargeTypeId(String strHOldIPDChargeTypeId) {
		this.strHOldIPDChargeTypeId = strHOldIPDChargeTypeId;
	}
	/**
	 * @return the strHNewPatientCatcode
	 */
	public String getStrHNewPatientCatcode() {
		return strHNewPatientCatcode;
	}
	/**
	 * @param strHNewPatientCatcode the strHNewPatientCatcode to set
	 */
	public void setStrHNewPatientCatcode(String strHNewPatientCatcode) {
		this.strHNewPatientCatcode = strHNewPatientCatcode;
	}
	/**
	 * @return the strHOldPatientCatcode
	 */
	public String getStrHOldPatientCatcode() {
		return strHOldPatientCatcode;
	}
	/**
	 * @param strHOldPatientCatcode the strHOldPatientCatcode to set
	 */
	public void setStrHOldPatientCatcode(String strHOldPatientCatcode) {
		this.strHOldPatientCatcode = strHOldPatientCatcode;
	}
	public String getStrNewChargeComboValues() {
		return strNewChargeComboValues;
	}
	public void setStrNewChargeComboValues(String strNewChargeComboValues) {
		this.strNewChargeComboValues = strNewChargeComboValues;
	}
	
	
	
					


}
