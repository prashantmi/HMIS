package bmed.masters.vo;

import javax.sql.rowset.WebRowSet;

import hisglobal.utility.TransferObject;

/**
 * @author:- Vivek Aggarwal 
 * Creation Date:- 11-April-2011 
 * Modifying Date:- 12-April-2011 
 * Module:- BMED(HIS Project)
 * 
 */
public class NonItemMstVO implements TransferObject {

	private static final long serialVersionUID = 02L;

	
	private boolean bExistStatus;

	private String strChk = "";

	private String strCtDate = "";
	private String strEffectiveFrom = "";
	private String strEffectiveFromDate = "";
	private String strEngineeringItemSubCmb = "";
	private String strEngineeringItemSubType = "";
	private String strEngineeringItemSubTypeId = "";

	private String strEngineeringItemSubTypeName = null;
	private WebRowSet strEngineeringItemSubWS = null;
	private String strEngineeringItemType = "";
	private String strEngineeringItemTypeCmb = "";
	private String strEngineeringItemTypeId = "";
	private String strEngineeringItemTypeName = null;

	private WebRowSet strEngineeringItemTypeWS = null;
	private String strHospitalCode = "";

	private String strIsValid = "";
	private String strMsgString = "";

	private String strMsgType = "";
	private String strRemarks = "";

	private String strSeatId = "";
	private String strNonItemId = "";

	/*
	 * Getters and Setters for the above attributes
	 */

	private String strNonItemName = "";

	public boolean getBExistStatus() {
		return bExistStatus;
	}

	public String getStrChk() {
		return strChk;
	}

	public String getStrCtDate() {
		return strCtDate;
	}

	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	public String getStrEffectiveFromDate() {
		return strEffectiveFromDate;
	}

	public String getStrEngineeringItemSubCmb() {
		return strEngineeringItemSubCmb;
	}

	public String getStrEngineeringItemSubType() {
		return strEngineeringItemSubType;
	}

	public String getStrEngineeringItemSubTypeId() {
		return strEngineeringItemSubTypeId;
	}

	/**
	 * @return the strEngineeringItemSubTypeName
	 */
	public String getStrEngineeringItemSubTypeName() {
		return strEngineeringItemSubTypeName;
	}

	public WebRowSet getStrEngineeringItemSubWS() {
		return strEngineeringItemSubWS;
	}

	public String getStrEngineeringItemType() {
		return strEngineeringItemType;
	}

	public String getStrEngineeringItemTypeCmb() {
		return strEngineeringItemTypeCmb;
	}

	public String getStrEngineeringItemTypeId() {
		return strEngineeringItemTypeId;
	}

	/**
	 * @return the strEngineeringItemTypeName
	 */
	public String getStrEngineeringItemTypeName() {
		return strEngineeringItemTypeName;
	}

	public WebRowSet getStrEngineeringItemTypeWS() {
		return strEngineeringItemTypeWS;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public String getStrNonItemId() {
		return strNonItemId;
	}

	public String getStrNonItemName() {
		return strNonItemName;
	}

	public void setBExistStatus(boolean existStatus) {
		bExistStatus = existStatus;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	public void setStrEffectiveFromDate(String strEffectiveFromDate) {
		this.strEffectiveFromDate = strEffectiveFromDate;
	}

	public void setStrEngineeringItemSubCmb(String strEngineeringItemSubCmb) {
		this.strEngineeringItemSubCmb = strEngineeringItemSubCmb;
	}

	public void setStrEngineeringItemSubType(String strEngineeringItemSubType) {
		this.strEngineeringItemSubType = strEngineeringItemSubType;
	}

	public void setStrEngineeringItemSubTypeId(
			String strEngineeringItemSubTypeId) {
		this.strEngineeringItemSubTypeId = strEngineeringItemSubTypeId;
	}

	/**
	 * @param strEngineeringItemSubTypeName
	 *            the strEngineeringItemSubTypeName to set
	 */
	public void setStrEngineeringItemSubTypeName(
			String strEngineeringItemSubTypeName) {
		this.strEngineeringItemSubTypeName = strEngineeringItemSubTypeName;
	}

	public void setStrEngineeringItemSubWS(WebRowSet strEngineeringItemSubWS) {
		this.strEngineeringItemSubWS = strEngineeringItemSubWS;
	}

	public void setStrEngineeringItemType(String strEngineeringItemType) {
		this.strEngineeringItemType = strEngineeringItemType;
	}

	public void setStrEngineeringItemTypeCmb(String strEngineeringItemTypeCmb) {
		this.strEngineeringItemTypeCmb = strEngineeringItemTypeCmb;
	}

	public void setStrEngineeringItemTypeId(String strEngineeringItemTypeId) {
		this.strEngineeringItemTypeId = strEngineeringItemTypeId;
	}

	/**
	 * @param strEngineeringItemTypeName
	 *            the strEngineeringItemTypeName to set
	 */
	public void setStrEngineeringItemTypeName(String strEngineeringItemTypeName) {
		this.strEngineeringItemTypeName = strEngineeringItemTypeName;
	}

	public void setStrEngineeringItemTypeWS(WebRowSet strEngineeringItemTypeWS) {
		this.strEngineeringItemTypeWS = strEngineeringItemTypeWS;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public void setStrNonItemId(String strNonItemId) {
		this.strNonItemId = strNonItemId;
	}

	public void setStrNonItemName(String strNonItemName) {
		this.strNonItemName = strNonItemName;
	}

}
