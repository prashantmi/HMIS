package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class SemtCancelTypeMstVO 
{
	private String strMode;
	private String strCancelTypeId;
	private String strHospCode;
	private String strCancelType;
	private String strProcessType;
	private String strRemarks;
	private String strEntryDate;
	private String strIsValid;
	private WebRowSet wrsCancelTypeInfo;
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	public String getStrCancelTypeId() {
		return strCancelTypeId;
	}
	public void setStrCancelTypeId(String strCancelTypeId) {
		this.strCancelTypeId = strCancelTypeId;
	}
	public String getStrHospCode() {
		return strHospCode;
	}
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}
	public String getStrCancelType() {
		return strCancelType;
	}
	public void setStrCancelType(String strCancelType) {
		this.strCancelType = strCancelType;
	}
	public String getStrProcessType() {
		return strProcessType;
	}
	public void setStrProcessType(String strProcessType) {
		this.strProcessType = strProcessType;
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
	public WebRowSet getWrsCancelTypeInfo() {
		return wrsCancelTypeInfo;
	}
	public void setWrsCancelTypeInfo(WebRowSet wrsCancelTypeInfo) {
		this.wrsCancelTypeInfo = wrsCancelTypeInfo;
	}
}
