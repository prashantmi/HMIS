package bmed.masters.controller.fb;

import hisglobal.transactionutil.GenericFormBean;

/**
 * @author Vivek 
 * Creation Date:- 27-Jul-2012 
 * Modifying Date:- 27-Jul-2012
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project)
 * 
 */
public class EquipmentTestMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	
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
	private String strErrMsg = "";
	private String strHospitalCode = "";
	private String strIsValid = "";
	private String strLstModDate = "";

	private String strLstModSeatId = "";
	private String strNormalMsg = "";

	private String strRemarks = "";
	private String strSeatId = "";

	private String strTestId = "";
	private String strTestName = "";
	private String strRetValue;
	
	private String strReportFormat = "0";
	private String strReportId = "";
	
	private String strIsFooter="";

	/*
	 * Getters and Setters for the above attributes
	 */

	private String strWarningMsg = "";

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

	public String getStrNormalMsg() {
		return strNormalMsg;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public String getStrTestId() {
		return strTestId;
	}

	public String getStrTestName() {
		return strTestName;
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

	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public void setStrTestId(String strTestId) {
		this.strTestId = strTestId;
	}

	public void setStrTestName(String strTestName) {
		this.strTestName = strTestName;
	}

	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	public String getStrRetValue() {
		return strRetValue;
	}

	public void setStrRetValue(String strRetValue) {
		this.strRetValue = strRetValue;
	}

	/**
	 * @return the strReportFormat
	 */
	public String getStrReportFormat() {
		return strReportFormat;
	}

	/**
	 * @param strReportFormat the strReportFormat to set
	 */
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}

	/**
	 * @return the strReportId
	 */
	public String getStrReportId() {
		return strReportId;
	}

	/**
	 * @param strReportId the strReportId to set
	 */
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}

	/**
	 * @return the strIsFooter
	 */
	public String getStrIsFooter() {
		return strIsFooter;
	}

	/**
	 * @param strIsFooter the strIsFooter to set
	 */
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}

}
