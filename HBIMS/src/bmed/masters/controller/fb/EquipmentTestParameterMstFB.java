package bmed.masters.controller.fb;

import hisglobal.masterutil.GenericFormBean;

/**
 * @author Arun VR 
 * Creation Date:- 7-Aug-2012 
 * Modifying Date:- 7-Aug-2012
 * Used For:-
 * Team Lead By:- 
 * Module:- BMED(HIS Project)
 * 
 */

public class EquipmentTestParameterMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	
	private String strChk = "";
	private String strCtDate = "";
	
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
	
	private String strTestNameCmb="";

	private String[] arrStrSelectedTestId;
	
	private String strAvailableTestOptions;
	private String strSelectedTestOptions;
	
	private String strEffectiveFrom="";
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


	public String getStrTestId() {
		return strTestId;
	}

	public void setStrTestId(String strTestId) {
		this.strTestId = strTestId;
	}

	public String getStrTestName() {
		return strTestName;
	}

	public void setStrTestName(String strTestName) {
		this.strTestName = strTestName;
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

	
	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
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

	

	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

	public String getStrTestNameCmb() {
		return strTestNameCmb;
	}

	public void setStrTestNameCmb(String strTestNameCmb) {
		this.strTestNameCmb = strTestNameCmb;
	}

	public String[] getArrStrSelectedTestId() {
		return arrStrSelectedTestId;
	}

	public void setArrStrSelectedTestId(String[] arrStrSelectedTestId) {
		this.arrStrSelectedTestId = arrStrSelectedTestId;
	}

	public String getStrAvailableTestOptions() {
		return strAvailableTestOptions;
	}

	public void setStrAvailableTestOptions(String strAvailableTestOptions) {
		this.strAvailableTestOptions = strAvailableTestOptions;
	}

	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	public String getStrSelectedTestOptions() {
		return strSelectedTestOptions;
	}

	public void setStrSelectedTestOptions(String strSelectedTestOptions) {
		this.strSelectedTestOptions = strSelectedTestOptions;
	}

	

}
