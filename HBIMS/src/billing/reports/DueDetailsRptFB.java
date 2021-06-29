package billing.reports;

import org.apache.struts.action.ActionForm;

public class DueDetailsRptFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	
	private String  strUnitCode="0";
    private String strWardCode="0";
	private String strDeptCode = "0";
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strErrMsg = "";
	private String strHospitalCode="";
	
	private String strDeptValues="";
	private String strUnitValues=""	;
	private String strWardValues="";
	
    private String strReportFormat = "0";
	private String strUserRemarks = "";
	private String strHospname="";
	private String strHospNameValues="";

	
	
	public String getStrDeptValues() {
		return strDeptValues;
	}
	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}
	public String getStrUnitValues() {
		return strUnitValues;
	}
	public void setStrUnitValues(String strUnitValues) {
		this.strUnitValues = strUnitValues;
	}
	public String getStrWardValues() {
		return strWardValues;
	}
	public void setStrWardValues(String strWardValues) {
		this.strWardValues = strWardValues;
	}
	public String getStrDeptCode() {
		return strDeptCode;
	}
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	
	public String getStrIsFooter() {
		return strIsFooter;
	}
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	public String getStrReportId() {
		return strReportId;
	}
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	
	public String getStrReportFormat() {
		return strReportFormat;
	}
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	
	
	
	public String getStrUnitCode() {
		return strUnitCode;
	}
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	public String getStrWardCode() {
		return strWardCode;
	}
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	public String getStrHospname() {
		return strHospname;
	}
	public void setStrHospname(String strHospname) {
		this.strHospname = strHospname;
	}
	public String getStrHospNameValues() {
		return strHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		this.strHospNameValues = strHospNameValues;
	}
	
}
