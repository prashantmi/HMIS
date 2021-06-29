package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class SemtMaintTypeMstVO 
{
	private String strMode;
	private String strMaintTypeId;
	private String strHospCode;
	private String strMainTypeName;
	private String strRemarks;
	private String strEntryDate;
	private String strIsValid;
	private WebRowSet wrsMaintTypeComboOptions;
	public String getStrMaintTypeId() {
		return strMaintTypeId;
	}
	public void setStrMaintTypeId(String strMaintTypeId) {
		this.strMaintTypeId = strMaintTypeId;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrMainTypeName() {
		return strMainTypeName;
	}
	public void setStrMainTypeName(String strMainTypeName) {
		this.strMainTypeName = strMainTypeName;
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
	public WebRowSet getWrsMaintTypeComboOptions() {
		return wrsMaintTypeComboOptions;
	}
	public void setWrsMaintTypeComboOptions(WebRowSet wrsMaintTypeComboOptions) {
		this.wrsMaintTypeComboOptions = wrsMaintTypeComboOptions;
	}
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}

}
