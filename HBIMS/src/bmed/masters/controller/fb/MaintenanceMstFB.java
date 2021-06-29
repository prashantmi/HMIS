package bmed.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

/**
 * @author   Vivek Aggarwal
 * Creation Date:- 19-Jan-2011 
 * Modifying Date:- 21-Jan-2011
 * Used For:-
 * Team Lead By:- 
 *  Module:- BMED(HIS Project)
 * 
 */
public class MaintenanceMstFB extends GenericFormBean {
	private static final long serialVersionUID = 02L;

	
	private String strChk = "";
	private String strCtDate = "";

	private String strEffectiveFrom = "";
	private String strEngineeringItemSubCmb = "";
	private String strEngineeringItemSubType = "";
	private String strEngineeringItemSubTypeId = "";
	private String strEngineeringItemSubTypeName = null;
	private String strEngineeringItemType = "";
	private String strEngineeringItemTypeCmb = "";
	private String strEngineeringItemTypeId = "";

	private String strEngineeringItemTypeName = null;
	private String strErrMsg = ""; // only in form bean and not in vo
	private String strHospitalCode = "";
	private String strIsValid = "";
	private String strLstModDate = "";

	private String strLstModSeatId = "";
	private String strMaintenanceId = "";

	private String strMaintenanceName = "";
	private String strNormalMsg = ""; // only in form bean and not in vo

	private String strRemarks = "";

	private String strSeatId = "";

	private String strWarningMsg = ""; // only in form bean and not in vo

	public String getStrChk() {
		return strChk;
	}

	public String getStrCtDate() {
		return strCtDate;
	}

	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	// private String strEffectiveFromDate = "";

	/*
	 * Getters and Setters for the above attributes
	 */

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

	public String getStrMaintenanceId() {
		return strMaintenanceId;
	}

	public String getStrMaintenanceName() {
		return strMaintenanceName;
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

	public void setStrMaintenanceId(String strMaintenanceId) {
		this.strMaintenanceId = strMaintenanceId;
	}

	public void setStrMaintenanceName(String strMaintenanceName) {
		this.strMaintenanceName = strMaintenanceName;
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

	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

}
