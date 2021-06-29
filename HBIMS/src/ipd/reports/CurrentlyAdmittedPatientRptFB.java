package ipd.reports;

import org.apache.struts.action.ActionForm;


public class CurrentlyAdmittedPatientRptFB extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 02L;

	private String strDeptCode = "0";
	private String strUnitCode = "0";
	private String strWardCode = "0";
	private String strIsRoomWise = "0";
	private String strRoomNo = "0";
	private String strReportFormat = "0";
	private String strIsFooter = "0";
	private String strUserRemarks = "";
	private String strErrMsg = "";
	private String strUnitValues = "";
	private String strWardValues = "";
	private String strRoomValues = "";
	private String strHospitalCode = "";
	
	private String strReportId = "0";
	
	private String strDeptValues = "";
	private String StrHospNameValues="";
	
	
	public String getStrHospNameValues() {
		return StrHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		StrHospNameValues = strHospNameValues;
	}
	/**
	 * @return the strDeptCode
	 */
	public String getStrDeptCode() {
		return strDeptCode;
	}
	/**
	 * @param strDeptCode the strDeptCode to set
	 */
	public void setStrDeptCode(String strDeptCode) {
		this.strDeptCode = strDeptCode;
	}
	/**
	 * @return the strUnitCode
	 */
	public String getStrUnitCode() {
		return strUnitCode;
	}
	/**
	 * @param strUnitCode the strUnitCode to set
	 */
	public void setStrUnitCode(String strUnitCode) {
		this.strUnitCode = strUnitCode;
	}
	/**
	 * @return the strWardCode
	 */
	public String getStrWardCode() {
		return strWardCode;
	}
	/**
	 * @param strWardCode the strWardCode to set
	 */
	public void setStrWardCode(String strWardCode) {
		this.strWardCode = strWardCode;
	}
	/**
	 * @return the strIsRoomWise
	 */
	public String getStrIsRoomWise() {
		return strIsRoomWise;
	}
	/**
	 * @param strIsRoomWise the strIsRoomWise to set
	 */
	public void setStrIsRoomWise(String strIsRoomWise) {
		this.strIsRoomWise = strIsRoomWise;
	}
	/**
	 * @return the strRoomNo
	 */
	public String getStrRoomNo() {
		return strRoomNo;
	}
	/**
	 * @param strRoomNo the strRoomNo to set
	 */
	public void setStrRoomNo(String strRoomNo) {
		this.strRoomNo = strRoomNo;
	}
	/**
	 * @return the strReportFormat
	 */
	public String getStrReportFormat() {
		return strReportFormat;
	}
	/**
	 * @param strReportFormat the strReportFormat to set
	 */
	public void setStrReportFormat(String strReportFormat) {
		this.strReportFormat = strReportFormat;
	}
	
	/**
	 * @return the strDeptValues
	 */
	public String getStrDeptValues() {
		return strDeptValues;
	}
	/**
	 * @param strDeptValues the strDeptValues to set
	 */
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
	public String getStrErrMsg() {
		return strErrMsg;
	}
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
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
	public String getStrRoomValues() {
		return strRoomValues;
	}
	public void setStrRoomValues(String strRoomValues) {
		this.strRoomValues = strRoomValues;
	}
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	
	
}
