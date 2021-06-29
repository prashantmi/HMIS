package ipd.reports;

import org.apache.struts.action.ActionForm;

public class WardRoomBedRptFB extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strHospitalCode = "";
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strUserRemarks = "";
	private String strErrMsg = "";
	private String strReportId = "0";
	
	private String strDeptValues = "";
	private String strWardTypeValues = "";
	
	public String getStrDeptCode() {
		return strDeptCode;
	}

	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
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

	public String getStrReportFormat() {
		return strReportFormat;
	}

	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
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

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	public String getStrReportId() {
		return strReportId;
	}

	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}

	public String getStrDeptValues() {
		return strDeptValues;
	}

	public void setStrDeptValues(String strDeptValues) {
		this.strDeptValues = strDeptValues;
	}

	public String getStrWardTypeValues() {
		return strWardTypeValues;
	}

	public void setStrWardTypeValues(String strWardTypeValues) {
		this.strWardTypeValues = strWardTypeValues;
	}

}
