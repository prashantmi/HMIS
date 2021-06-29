package bmed.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

/**
 * @author Vivek 
 * Creation Date:- 27-Jul-2012 
 * Modifying Date:- 27-Jul-2012
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project)
 * 
 */
public class EquipmentParameterMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	
	private String strChk = "";
	private String strCtDate = "";

	private String strEffectiveFrom = "";
	private String strEffectiveFromDate = "";
	
	private String strErrMsg = "";
	private String strHospitalCode = "";
	private String strIsValid = "";
	private String strLstModDate = "";

	private String strLstModSeatId = "";
	private String strNormalMsg = "";

	private String strRemarks = "";
	private String strSeatId = "";

	private String strParameterId = "";
	private String strParameterName = "";

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

	

	/**
	 * @return the strEngineeringItemSubTypeName
	 */



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

	

	/**
	 * @param strEngineeringItemSubTypeName
	 *            the strEngineeringItemSubTypeName to set
	 */
	



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

	

	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	public String getStrParameterName() {
		return strParameterName;
	}

	public void setStrParameterName(String strParameterName) {
		this.strParameterName = strParameterName;
	}

	public String getStrParameterId() {
		return strParameterId;
	}

	public void setStrParameterId(String strParameterId) {
		this.strParameterId = strParameterId;
	}

}
