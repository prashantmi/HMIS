package bmed.vo;
import javax.sql.rowset.WebRowSet;

public class UnitMstVO 
{
	private String strUnitId;  
	private String strHospitalCode;
	private String strUnitName;
	private String strRemarks;
	private String strPeriodInDays;
	private String strEntryDate;
	private String strIsValid;
	private String strMode;
	private WebRowSet wrsUnitComboOptions;
	
	public String getStrUnitId() {
		return strUnitId;
	}
	public void setStrUnitId(String strUnitId) {
		this.strUnitId = strUnitId;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrUnitName() {
		return strUnitName;
	}
	public void setStrUnitName(String strUnitName) {
		this.strUnitName = strUnitName;
	}
	public String getStrRemarks() {
		return strRemarks;
	}
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	public String getStrPeriodInDays() {
		return strPeriodInDays;
	}
	public void setStrPeriodInDays(String strPeriodInDays) {
		this.strPeriodInDays = strPeriodInDays;
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
	
	public String getStrMode() {
		return strMode;
	}
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	public WebRowSet getWrsUnitComboOptions() {
		return wrsUnitComboOptions;
	}
	public void setWrsUnitComboOptions(WebRowSet wrsUnitComboOptions) {
		this.wrsUnitComboOptions = wrsUnitComboOptions;
	}

}
