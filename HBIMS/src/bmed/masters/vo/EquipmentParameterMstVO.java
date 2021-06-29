package bmed.masters.vo;

import javax.sql.rowset.WebRowSet;

import hisglobal.utility.TransferObject;

/**
 * @author:- Arun VR
 * Creation Date:- 06-Aug-2012
 * Modifying Date:-
 * Used For:- 
 * Team Lead By:- 
 * Module:- BMED(HIS Project)
 * 
 */
public class EquipmentParameterMstVO implements TransferObject {

	private static final long serialVersionUID = 02L;

	
	private boolean bExistStatus;

	private String strChk = "";

	private String strCtDate = "";
	private String strEffectiveFrom = "";
	private String strEffectiveFromDate = "";
	
	private String strHospitalCode = "";

	private String strIsValid = "";
	private String strMsgString = "";

	private String strMsgType = "";
	private String strRemarks = "";

	private String strSeatId = "";
	private String strParameterId = "";

	/*
	 * Getters and Setters for the above attributes
	 */

	private String strParameterName = "";

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
