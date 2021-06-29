package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class SemtEscalationLevelTypeMstVO 
{
	  private String strMode;
	  private String strLevelTypeId;
      private String strLevelName;
      private String strHospCode;
      private String strEntryDate;
      private String strIsValid;
      private WebRowSet wrsData;
      
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	public String getStrLevelTypeId() {
		return strLevelTypeId;
	}
	public void setStrLevelTypeId(String strLevelTypeId) {
		this.strLevelTypeId = strLevelTypeId;
	}
	public String getStrLevelName() {
		return strLevelName;
	}
	public void setStrLevelName(String strLevelName) {
		this.strLevelName = strLevelName;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
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
	public WebRowSet getWrsData() {
		return wrsData;
	}
	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}

}
