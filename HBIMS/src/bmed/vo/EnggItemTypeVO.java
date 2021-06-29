package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class EnggItemTypeVO 
{
	
	/*Variable for Error Management*/
	private String strMsgString;
	private String strMsgType;	
	/*Table Specific Variable's*/
 	private String strEnggItemType;
	private String strHospCode;
	private String strEnggItemTypeName;
	private String strRemarks;
	private String strEntryDate;
	private String strIsValid;
	private WebRowSet strEngineeringItemTypeWS = null;
	
	public String getStrEnggItemType() {
		return strEnggItemType;
	}
	public void setStrEnggItemType(String strEnggItemType) {
		this.strEnggItemType = strEnggItemType;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrEnggItemTypeName() {
		return strEnggItemTypeName;
	}
	public void setStrEnggItemTypeName(String strEnggItemTypeName) {
		this.strEnggItemTypeName = strEnggItemTypeName;
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
	public String getStrMsgString() {
		return strMsgString;
	}
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}
	public String getStrMsgType() {
		return strMsgType;
	}
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}
}
