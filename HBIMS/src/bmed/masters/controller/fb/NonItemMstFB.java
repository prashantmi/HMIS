package bmed.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

/**
 * @author Vivek Aggarwal
 * Creation Date:- 11/April/2011 
 * Modifying Date:- 13/April/2011 
 * Module:- BMED(HIS Project)
 * 
 */
public class NonItemMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	// Error, warning or Normal Messages
	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";

	
	private String strChk = "";
	private String strCtDate = "";

	private String strEffectiveFrom = "";
	private String strEffectiveFromDate = "";
	private String strEngineeringItemSubCmb = "";
	private String strEngineeringItemSubType = "";
	private String strEngineeringItemSubTypeId = "";
	private String strEngineeringItemSubTypeName = "";
	private String strEngineeringItemType = "";
	private String strEngineeringItemTypeCmb = "";

	private String strEngineeringItemTypeId = "";
	private String strEngineeringItemTypeName = "";
	
	private String strHospitalCode = "";
	private String strIsValid = "";
	private String strLstModDate = "";

	private String strLstModSeatId = "";
	

	private String strRemarks = "";
	private String strSeatId = "";

	private String strNonItemId = "";
	private String strNonItemName = "";

	/*
	 * Getters and Setters for the above attributes
	 */
	
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

	public String getStrEngineeringItemSubTypeName() {
		return strEngineeringItemSubTypeName;
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

	public String getStrEngineeringItemTypeName() {
		return strEngineeringItemTypeName;
	}

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public String getStrLstModDate() {
		return strLstModDate;
	}

	public String getStrLstModSeatId() {
		return strLstModSeatId;
	}

	public String getStrNormalMsg() {
		return strNormalMsg;
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

	public String getStrWarningMsg() {
		return strWarningMsg;
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

	public void setStrEngineeringItemSubTypeName(
			String strEngineeringItemSubTypeName) {
		this.strEngineeringItemSubTypeName = strEngineeringItemSubTypeName;
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

	public void setStrEngineeringItemTypeName(String strEngineeringItemTypeName) {
		this.strEngineeringItemTypeName = strEngineeringItemTypeName;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public void setStrLstModDate(String strLstModDate) {
		this.strLstModDate = strLstModDate;
	}

	public void setStrLstModSeatId(String strLstModSeatId) {
		this.strLstModSeatId = strLstModSeatId;
	}

	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
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

	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

}
