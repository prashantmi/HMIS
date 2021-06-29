package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class MaintenanceTypeMstVO 
{
	private String strMainteTypeId;
	private String strHospCode;
	private String strMaintTypeName;
	private String strRemarks;
	private String strEntryDate;
	private String strIsValid;
	private WebRowSet strEngineeringItemTypeWS = null;
	public String getStrMainteTypeId() {
		return strMainteTypeId;
	}
	public void setStrMainteTypeId(String strMainteTypeId) {
		this.strMainteTypeId = strMainteTypeId;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrMaintTypeName() {
		return strMaintTypeName;
	}
	public void setStrMaintTypeName(String strMaintTypeName) {
		this.strMaintTypeName = strMaintTypeName;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrEntryDate() {
		return strEntryDate;
	}
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}
	public String getStrIsValid() {
		return strIsValid;
	}
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	public WebRowSet getStrEngineeringItemTypeWS() {
		return strEngineeringItemTypeWS;
	}
	public void setStrEngineeringItemTypeWS(WebRowSet strEngineeringItemTypeWS) {
		this.strEngineeringItemTypeWS = strEngineeringItemTypeWS;
	}
	
	
}
