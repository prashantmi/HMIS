package ipd.reports;

import org.apache.struts.action.ActionForm;

public class PatientDiagnosisRptFB extends ActionForm {

	private static final long serialVersionUID = 02L;

	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strIsRoomWise = "0";
	private String strRoomNo = "0";
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strUserRemarks = "";
	private String strCase ="";
	private String strErrMsg = "";
	private String strReportId = "0";
	private String strEffectiveFrom = "";
	private String strEffectiveTo = "";
	private String strCurrentDate = "";
	private String strHospCode = "";
	
	private String strDeptValues = "";

	/**
	 * @return the strHospCode
	 */
	public String getStrHospCode() {
		return strHospCode;
	}

	/**
	 * @param strHospCode the strHospCode to set
	 */
	public void setStrHospCode(String strHospCode) {
		this.strHospCode = strHospCode;
	}

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

	public String getStrIsRoomWise() {
		return strIsRoomWise;
	}

	public void setStrIsRoomWise(String strIsRoomWise) {
		this.strIsRoomWise = strIsRoomWise;
	}

	public String getStrRoomNo() {
		return strRoomNo;
	}

	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
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

	public String getStrCase() {
		return strCase;
	}

	public void setStrCase(String strCase) {
		this.strCase = strCase;
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

	public String getStrErrMsg() {
		return strErrMsg;
	}

	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
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

	public String getStrCurrentDate() {
		return strCurrentDate;
	}

	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	
}
