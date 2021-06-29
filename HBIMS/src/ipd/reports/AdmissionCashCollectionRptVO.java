package ipd.reports;

import javax.sql.rowset.WebRowSet;

public class AdmissionCashCollectionRptVO {

	private static final long serialVersionUID = 02L;

	private String strMsgString = "";
	private String strMsgType = "";
	private String strCategory = "2";
	
	private String strReportFormat = "0";
	
	private String strHospitalCode = "";
	private WebRowSet strCategoryWs = null;
	private WebRowSet strDeptWs = null;
	private String strCase = "1";

	private String strEffectiveFrom=null;
	private String strEffectiveTo=null;
	private String strModName = "";
	
	
	
	
	public String getStrModName() {
		return strModName;
	}
	public void setStrModName(String strModName) {
		this.strModName = strModName;
	}
	public String getStrEffectiveFrom() {
			
		return strEffectiveFrom;
	}
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	public String getStrEffectiveTo() {

		return strEffectiveTo;
	}
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	public WebRowSet getStrDeptWs() {
		return strDeptWs;
	}
	public void setStrDeptWs(WebRowSet strDeptWs) {
		this.strDeptWs = strDeptWs;
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
	public String getStrCategory() {
		return strCategory;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public WebRowSet getStrCategoryWs() {
		return strCategoryWs;
	}
	public void setStrCategoryWs(WebRowSet strCategoryWs) {
		this.strCategoryWs = strCategoryWs;
	}
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	
}
