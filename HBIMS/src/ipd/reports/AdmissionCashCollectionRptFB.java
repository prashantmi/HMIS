package ipd.reports;

import org.apache.struts.action.ActionForm;

public class AdmissionCashCollectionRptFB extends ActionForm {
	
	
	private static final long serialVersionUID = 02L;
	
	private String strCategory = "1";
	private String strReportFormat = "0";
	private String strRoomNo = "0";
	private String strWardCode = "0";
	private String strUserRemarks = "0";
	private String strReportId = "0";
	private String strIsFooter = "0";
	private String strDeptValues = "";
	private String strCategoryValues = "";
	private String strHospitalCode = "";
	private String strErrMsg ="";
	private String strCase = "1";
	private String strEffectiveFrom= "";
	private String strEffectiveTo= "";
	private String strChkRadio = "";
	private String strCurrentDate = "";
	
	
	
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
	
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	public String getStrCategoryValues() {
		return strCategoryValues;
	}
	public void setStrCategoryValues(String strCategoryValues) {
		this.strCategoryValues = strCategoryValues;
	}
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
	public String getStrIsFooter() {
		return strIsFooter;
	}
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrRoomNo() {
		return strRoomNo;
	}
	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
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
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	
	public String getStrCase() {
		return strCase;
	}
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrChkRadio() {
		return strChkRadio;
	}
	public void setStrChkRadio(String strChkRadio) {
		this.strChkRadio = strChkRadio;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	
	
}
