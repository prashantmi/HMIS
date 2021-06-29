package mms.reports.controller.fb;

import org.apache.struts.action.ActionForm;

public class ListConsumablesExpiryDateRptFB extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strHospitalCode = "";
	private String strSeatId = "";
	
	private String strErrMsg = "";
	private String strNormalMsg = "";
	private String strWarningMsg = "";
	
	private String strReportId = "";
	private String strUserRemarks = "";
	private String strReportFormat = "";
	private String strIsFooter = "";
	
	private String strItemCatNo = "";
	private String strStoreId = "";
	private String strCurrentDate = "";
	private String strDueDate = "";
    private String strStoreValues = "";
	
	private String strDistrictStoreValues = "";
	private String strDistrictFlag;
	private String strItemCatValues = "";

	public String getStrItemCatValues() {
		return strItemCatValues;
	}
	public void setStrItemCatValues(String strItemCatValues) {
		this.strItemCatValues = strItemCatValues;
	}
	private String whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse="0";
	
	private String strDistrictStoreId;
	
	private String strExpNonExpiryFlag;


	private String strDateDaysFlag;
	private String strFrmExpDate;
	private String strExpiryDays;
	private String strFrmExpiryDays;
	private String strStoreName;
	private String strUserLevel;
	
	public String getStrUserLevel() {
		return strUserLevel;
	}
	public void setStrUserLevel(String strUserLevel) {
		this.strUserLevel = strUserLevel;
	}
	public String getStrStoreName() {
		return strStoreName;
	}
	public void setStrStoreName(String strStoreName) {
		this.strStoreName = strStoreName;
	}
	public String getStrFrmExpiryDays() {
		return strFrmExpiryDays;
	}
	public void setStrFrmExpiryDays(String strFrmExpiryDays) {
		this.strFrmExpiryDays = strFrmExpiryDays;
	}
	public String getStrExpiryDays() {
		return strExpiryDays;
	}
	public void setStrExpiryDays(String strExpiryDays) {
		this.strExpiryDays = strExpiryDays;
	}
	public String getStrFrmExpDate() {
		return strFrmExpDate;
	}
	public void setStrFrmExpDate(String strFrmExpDate) {
		this.strFrmExpDate = strFrmExpDate;
	}
	public String getStrExpNonExpiryFlag() {
		return strExpNonExpiryFlag;
	}
	public void setStrExpNonExpiryFlag(String strExpNonExpiryFlag) {
		this.strExpNonExpiryFlag = strExpNonExpiryFlag;
	}
	public String getStrDateDaysFlag() {
		return strDateDaysFlag;
	}
	public void setStrDateDaysFlag(String strDateDaysFlag) {
		this.strDateDaysFlag = strDateDaysFlag;
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
	 * @return the strItemCatNo
	 */
	public String getStrItemCatNo() {
		return strItemCatNo;
	}
	/**
	 * @param strItemCatNo the strItemCatNo to set
	 */
	public void setStrItemCatNo(String strItemCatNo) {
		this.strItemCatNo = strItemCatNo;
	}
	/**
	 * @return the strStoreId
	 */
	public String getStrStoreId() {
		return strStoreId;
	}
	/**
	 * @param strStoreId the strStoreId to set
	 */
	public void setStrStoreId(String strStoreId) {
		this.strStoreId = strStoreId;
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
	 * @return the strDueDate
	 */
	public String getStrDueDate() {
		return strDueDate;
	}
	/**
	 * @param strDueDate the strDueDate to set
	 */
	public void setStrDueDate(String strDueDate) {
		this.strDueDate = strDueDate;
	}
	/**
	 * @return the strStoreValues
	 */
	public String getStrStoreValues() {
		return strStoreValues;
	}
	/**
	 * @param strStoreValues the strStoreValues to set
	 */
	public void setStrStoreValues(String strStoreValues) {
		this.strStoreValues = strStoreValues;
	}
	public String getStrDistrictStoreValues() {
		return strDistrictStoreValues;
	}
	public void setStrDistrictStoreValues(String strDistrictStoreValues) {
		this.strDistrictStoreValues = strDistrictStoreValues;
	}
	public String getWhetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse() {
		return whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse;
	}
	public void setWhetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse(
			String whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse) {
		this.whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse = whetherConsolidatedStockForAllInstitutseOfDistrictDrugWarehouse;
	}
	public String getStrDistrictStoreId() {
		return strDistrictStoreId;
	}
	public void setStrDistrictStoreId(String strDistrictStoreId) {
		this.strDistrictStoreId = strDistrictStoreId;
	}
	public String getStrDistrictFlag() {
		return strDistrictFlag;
	}
	public void setStrDistrictFlag(String strDistrictFlag) {
		this.strDistrictFlag = strDistrictFlag;
	}

}
