/**
 * 
 */
package billing.reports;

import org.apache.struts.action.ActionForm;

/**
 * @author Administrator
 *
 */
public class DuplicatePrintLogDetailRptFB extends ActionForm {
	
	private static final long serialVersionUID = 02L;
	private String strIsFooter = "0";
	private String strReportId = "0";
	private String strErrMsg = "";
	
	private String strUserRemarks = "0";
	private String strHospitalCode = "";
	private String strReportFormat = "0";
	
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strCurrentDate="";
	
	private String strCase = "1";
	private String strHospNameValues="";
	
	/**
	 * @return the strIsFooter
	 */
	public String getStrIsFooter() {
		return strIsFooter;
	}
	/**
	 * @param strIsFooter the strIsFooter to set
	 */
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}
	/**
	 * @return the strReportId
	 */
	public String getStrReportId() {
		return strReportId;
	}
	/**
	 * @param strReportId the strReportId to set
	 */
	public void setStrReportId(String strReportId) {
		this.strReportId = strReportId;
	}
	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}
	/**
	 * @param strErrMsg the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}
	/**
	 * @return the strUserRemarks
	 */
	public String getStrUserRemarks() {
		return strUserRemarks;
	}
	/**
	 * @param strUserRemarks the strUserRemarks to set
	 */
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
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
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}
	/**
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}
	/**
	 * @return the strEffectiveTo
	 */
	public String getStrEffectiveTo() {
		return strEffectiveTo;
	}
	/**
	 * @param strEffectiveTo the strEffectiveTo to set
	 */
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}
	/**
	 * @return the strCurrentDate
	 */
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	/**
	 * @param strCurrentDate the strCurrentDate to set
	 */
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}
	/**
	 * @return the strCase
	 */
	public String getStrCase() {
		return strCase;
	}
	/**
	 * @param strCase the strCase to set
	 */
	public void setStrCase(String strCase) {
		this.strCase = strCase;
	}
	public String getStrHospNameValues() {
		return strHospNameValues;
	}
	public void setStrHospNameValues(String strHospNameValues) {
		this.strHospNameValues = strHospNameValues;
	}

}
