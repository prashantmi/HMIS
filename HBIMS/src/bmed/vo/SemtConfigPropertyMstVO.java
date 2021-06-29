package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class SemtConfigPropertyMstVO 
{
	private String strMode;
	
	private String strConfigPropertyId;
	private String strHospitalCode;
	private String strPropertyName;
	private String strRemarks;
	private String strPropertyValue;
	private String strIsValid;  
	  
	private WebRowSet wrsData;

	/*
	 * Getters and Setters for the Above attributes
	 */
	public String getStrMode() {
		return strMode;
	}

	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

	public String getStrConfigPropertyId() {
		return strConfigPropertyId;
	}

	public void setStrConfigPropertyId(String strConfigPropertyId) {
		this.strConfigPropertyId = strConfigPropertyId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrPropertyName() {
		return strPropertyName;
	}

	public void setStrPropertyName(String strPropertyName) {
		this.strPropertyName = strPropertyName;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrPropertyValue() {
		return strPropertyValue;
	}

	public void setStrPropertyValue(String strPropertyValue) {
		this.strPropertyValue = strPropertyValue;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public WebRowSet getWrsData() {
		return wrsData;
	}

	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}
}
