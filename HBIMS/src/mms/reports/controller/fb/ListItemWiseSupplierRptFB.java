/**
 * 
 */
package mms.reports.controller.fb;

import org.apache.struts.action.ActionForm;

/**
 * @author user
 *
 */
public class ListItemWiseSupplierRptFB extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String strHospitalCode = "";
	private String strSeatId = "";
	
	private String strIsFooter = "1";
	private String strUserRemarks = "";
	private String strReportFormat = "0";
	private String strReportId = "";
	
	private String strErrMsg = "";
	private String strWarningMsg = "";
	private String strNormalMsg = "";
	
	private String strItemCategoryNo = "";
	private String strGroupId = "";
	private String strSubGroupId = "";
	private String strItemId = "";
	
	private String strItemCategoryCombo = null;
	private String strGroupCombo = null;
	private String strSubGroupCombo = null;
	private String strItemCombo = null;
	private String strhospCmb=null;
	
	private String strSupplierId;
	private String strManufactureCombo;
	
	private String strFrmExpiryDays;
	
	private String strActiveOrNearExpiry;
	
	public String getStrActiveOrNearExpiry() {
		return strActiveOrNearExpiry;
	}
	public void setStrActiveOrNearExpiry(String strActiveOrNearExpiry) {
		this.strActiveOrNearExpiry = strActiveOrNearExpiry;
	}
	public String getStrFrmExpiryDays() {
		return strFrmExpiryDays;
	}
	public void setStrFrmExpiryDays(String strFrmExpiryDays) {
		this.strFrmExpiryDays = strFrmExpiryDays;
	}
	public String getStrManufactureCombo() {
		return strManufactureCombo;
	}
	public void setStrManufactureCombo(String strManufactureCombo) {
		this.strManufactureCombo = strManufactureCombo;
	}
	public String getStrSupplierId() {
		return strSupplierId;
	}
	public void setStrSupplierId(String strSupplierId) {
		this.strSupplierId = strSupplierId;
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
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
	}
	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
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
	 * @return the strWarningMsg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}
	/**
	 * @param strWarningMsg the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}
	/**
	 * @param strNormalMsg the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}
	/**
	 * @return the strItemCategoryNo
	 */
	public String getStrItemCategoryNo() {
		return strItemCategoryNo;
	}
	/**
	 * @param strItemCategoryNo the strItemCategoryNo to set
	 */
	public void setStrItemCategoryNo(String strItemCategoryNo) {
		this.strItemCategoryNo = strItemCategoryNo;
	}
	/**
	 * @return the strGroupId
	 */
	public String getStrGroupId() {
		return strGroupId;
	}
	/**
	 * @param strGroupId the strGroupId to set
	 */
	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}
	/**
	 * @return the strSubGroupId
	 */
	public String getStrSubGroupId() {
		return strSubGroupId;
	}
	/**
	 * @param strSubGroupId the strSubGroupId to set
	 */
	public void setStrSubGroupId(String strSubGroupId) {
		this.strSubGroupId = strSubGroupId;
	}
	/**
	 * @return the strItemId
	 */
	public String getStrItemId() {
		return strItemId;
	}
	/**
	 * @param strItemId the strItemId to set
	 */
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	/**
	 * @return the strItemCategoryCombo
	 */
	public String getStrItemCategoryCombo() {
		return strItemCategoryCombo;
	}
	/**
	 * @param strItemCategoryCombo the strItemCategoryCombo to set
	 */
	public void setStrItemCategoryCombo(String strItemCategoryCombo) {
		this.strItemCategoryCombo = strItemCategoryCombo;
	}
	/**
	 * @return the strGroupCombo
	 */
	public String getStrGroupCombo() {
		return strGroupCombo;
	}
	/**
	 * @param strGroupCombo the strGroupCombo to set
	 */
	public void setStrGroupCombo(String strGroupCombo) {
		this.strGroupCombo = strGroupCombo;
	}
	/**
	 * @return the strSubGroupCombo
	 */
	public String getStrSubGroupCombo() {
		return strSubGroupCombo;
	}
	/**
	 * @param strSubGroupCombo the strSubGroupCombo to set
	 */
	public void setStrSubGroupCombo(String strSubGroupCombo) {
		this.strSubGroupCombo = strSubGroupCombo;
	}
	/**
	 * @return the strItemCombo
	 */
	public String getStrItemCombo() {
		return strItemCombo;
	}
	/**
	 * @param strItemCombo the strItemCombo to set
	 */
	public void setStrItemCombo(String strItemCombo) {
		this.strItemCombo = strItemCombo;
	}
	public String getStrhospCmb() {
		return strhospCmb;
	}
	public void setStrhospCmb(String strhospCmb) {
		this.strhospCmb = strhospCmb;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
