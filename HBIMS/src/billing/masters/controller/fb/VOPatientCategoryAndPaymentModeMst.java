package billing.masters.controller.fb;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.transactionmgnt.controller.HisComboOptions;
import hisglobal.utility.HisUtil;

public class VOPatientCategoryAndPaymentModeMst extends GenericFormBean {

	private static final long serialVersionUID = 02L;
	private String strSeatId = "";
	private String strErr = "";
	private String strWarning = "";
	private String strMsg = "";
	private String strEffectiveFromDate = null;
	private String strWardType = null;
	private String strCategory = null;
	private String strAdvanceAmount = null;
	private String strAdvanceSecurity = null;
	private String strPartPayment = null;
	private String strEffectiveToDate = null;
	private String strRemarks = "";
	private String strLastModSeatId = "";
	private String strWardValues = "";
	private String strCategoryValues = "";
	private String strHospitalCode = "";
	private String strHospitalService = "";
	private String strHospitalServiceId = "";
	private String strSpecialWardType = "";
	private String strSpecialWardValues = "";
	
	private String[] strRecieptPaymentMode = null;
	private String[] strRefundPaymentMode = null;
	
	private String strRecieptPaymentModeValues = "";
	private String strRefundPaymentModeValues = "";

	private String strUpdateMode = "0";
	private String strRefQty ="";

	HttpSession httpSession = null;

	private String strCtDate = null;
	private WebRowSet strPayemtnModeMapping=null;
	
	private String strPaymentModeSelected="";

	
	
	public String getStrPaymentModeSelected() {
		return strPaymentModeSelected;
	}

	public void setStrPaymentModeSelected(String strPaymentModeSelected) {
		this.strPaymentModeSelected = strPaymentModeSelected;
	}

	public String getStrCtDate() {

		HisUtil util = new HisUtil("Advance Master", "VOAdvanceMst");

		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {

			new HisException("Advance Master", "VOAdvanceMst.getStrCtDate()", e
					.getMessage());
		}

		return strCtDate;
	}

	/**
	 * retrieves and gets the Ward Option Values.
	 * 
	 * @return Ward Option Value String
	 */
	public String getStrWardValues() {

		String hosCode = this.getStrHospitalCode();

		HisUtil hisUtil = new HisUtil("Advance Master",
				"VOAdvanceMst.getStrWardValues");

		try {

			String strQry = billing.qryHandler_billing.getQuery(1,
					"select.advanceMst.1").replace("?", hosCode);

			if (strWardType == null)
				strWardType = "0";

			strWardValues = hisUtil.getOptionValue(strQry, strWardType,
					"0^Select Value");
			hisUtil = null;

		} catch (Exception e) {

			new HisException("Advance Master", "VOAdvanceMst.getStrWardValues",
					e.getMessage());
		}

		return strWardValues;
	}

	/**
	 * retrieves and gets the Category Option Values.
	 * 
	 * @return Category Option Value String
	 */
	public String getStrCategoryValues() 
	{
		String hosCode = this.getStrHospitalCode();
		//String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;
		HisUtil hisUtil = new HisUtil("Advance Master","VOAdvanceMst.getModuleNames");

		try 
		{
			String strQry = billing.qryHandler_billing.getQuery(1,"select.advanceMst.2").replace("?", hosCode);
			if (strCategory == null)
				strCategory = "0";

			strCategoryValues = hisUtil.getOptionValue(strQry, strCategory,"0^Select Value");
			hisUtil = null;
		} 
		catch (Exception e) 
		{
			new HisException("Advance Master","VOAdvanceMst.getStrCategoryValues", e.getMessage());
		}

		return strCategoryValues;
	}

	public String getStrWardType() {
		return strWardType;
	}

	public void setStrWardType(String strWardType) {
		this.strWardType = strWardType;
	}

	public String getStrCategory() {
		return strCategory;
	}

	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}

	public String getStrAdvanceAmount() {
		return strAdvanceAmount;
	}

	public void setStrAdvanceAmount(String strAdvanceAmount) {
		this.strAdvanceAmount = strAdvanceAmount;
	}

	public String getStrPartPayment() {
		return strPartPayment;
	}

	public void setStrPartPayment(String strPartPayment) {
		this.strPartPayment = strPartPayment;
	}

	public String getStrEffectiveToDate() {

		if (this.strEffectiveToDate == null)
			strEffectiveToDate = "";

		return strEffectiveToDate;
	}

	public void setStrEffectiveToDate(String strEffectiveToDate) {

		this.strEffectiveToDate = strEffectiveToDate;
	}

	public String getStrRemarks() {

		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrErr() {
		return strErr;
	}

	public void setStrErr(String strErr) {

		this.strErr = strErr;
	}

	/**
	 * retrieves Current Date from Application Server
	 * 
	 * @return current Date.
	 */
	public String getStrEffectiveFromDate() {

		if (this.strEffectiveFromDate == null) {

			HisUtil util = new HisUtil("Advance Master", "VOAdvanceMst");

			try {
				strEffectiveFromDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {

				new HisException("Advance Master",
						"VOAdvanceMst.getEffectiveFromDate()", e.getMessage());
			}
		}

		return strEffectiveFromDate;
	}

	public void setStrEffectiveFromDate(String strEffectiveFromDate) {
		this.strEffectiveFromDate = strEffectiveFromDate;
	}

	public String getStrLastModSeatId() {
		return strLastModSeatId;
	}

	public void setStrLastModSeatId(String strLastModSeatId) {
		this.strLastModSeatId = strLastModSeatId;
	}

	public String getStrWarning() {
		return strWarning;
	}

	public void setStrWarning(String strWarning) {

		this.strWarning = strWarning;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {

		this.strMsg = strMsg;
	}

	public String getStrAdvanceSecurity() {
		return strAdvanceSecurity;
	}

	public void setStrAdvanceSecurity(String strAdvanceSecurity) {
		this.strAdvanceSecurity = strAdvanceSecurity;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrHospitalService() {
		return strHospitalService;
	}

	public void setStrHospitalService(String strHospitalService) {
		this.strHospitalService = strHospitalService;
	}

	public String getStrHospitalServiceId() {
		return strHospitalServiceId;
	}

	public void setStrHospitalServiceId(String strHospitalServiceId) {
		this.strHospitalServiceId = strHospitalServiceId;
	}

	public String getStrUpdateMode() {
		return strUpdateMode;
	}

	public void setStrUpdateMode(String strUpdateMode) {
		this.strUpdateMode = strUpdateMode;
	}

	public String getStrSpecialWardType() {
		return strSpecialWardType;
	}

	public void setStrSpecialWardType(String strSpecialWardType) {
		this.strSpecialWardType = strSpecialWardType;
	}
	

	public String getStrRefQty() {
		return strRefQty;
	}

	public void setStrRefQty(String strRefQty) {
		this.strRefQty = strRefQty;
	}

	public String getStrSpecialWardValues() {
		
		 	try {

		 		LinkedHashMap<String, String> mapProcedureParam = new LinkedHashMap<String, String>();
			String qryWardType = billing.qryHandler_billing.getQuery(1,"select.billsetup.8");
			
			mapProcedureParam.put("1", this.getStrHospitalCode());
			
			strSpecialWardValues = HisComboOptions.getOptionsFromQuery(qryWardType, mapProcedureParam, this.getStrSpecialWardType(), "0^Select Value", false);
		
		 	} catch (Exception e) {

				 		new HisException("Billing", "VOAdvanceMst.getStrSpecialWardValues()", e.getMessage());
				 		
				 		strSpecialWardValues = "<option value='0'>Select Value</option>";
				 		
				}
		
		
		return strSpecialWardValues;
	}



	public String[] getStrRecieptPaymentMode() {
		return strRecieptPaymentMode;
	}

	public void setStrRecieptPaymentMode(String[] strRecieptPaymentMode) {
		this.strRecieptPaymentMode = strRecieptPaymentMode;
	}

	public String[] getStrRefundPaymentMode() {
		return strRefundPaymentMode;
	}

	public void setStrRefundPaymentMode(String[] strRefundPaymentMode) {
		this.strRefundPaymentMode = strRefundPaymentMode;
	}

	public String getStrRecieptPaymentModeValues() {
		
		String hosCode = this.getStrHospitalCode();
		//String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;
		HisUtil hisUtil = new HisUtil("PatientCategoryAndPaymentMode Master","VOPatientCategoryAndPaymentModeMst.getModuleNames");

		try 
		{
			String strQry = billing.qryHandler_billing.getQuery(1,"select.patcatandpaymentmodeMst.2").replace("?", hosCode);
		/*	if (strRecieptPaymentMode == null)
				strRecieptPaymentMode = "0";*/

			strRecieptPaymentModeValues = hisUtil.getOptionValue(strQry, strPaymentModeSelected,"0^Select Value");
			hisUtil = null;
		} 
		catch (Exception e) 
		{
			new HisException("Advance Master","VOPatientCategoryAndPaymentModeMst.getStrRecieptPaymentModeValues", e.getMessage());
		}
		return strRecieptPaymentModeValues;
	}

	public void setStrRecieptPaymentModeValues(String strRecieptPaymentModeValues) {
		this.strRecieptPaymentModeValues = strRecieptPaymentModeValues;
	}

	public String getStrRefundPaymentModeValues() {
		String hosCode = this.getStrHospitalCode();
		//String hosCode = BillConfigUtil.SUPER_HOSPITAL_CODE;
		HisUtil hisUtil = new HisUtil("PatientCategoryAndPaymentMode Master","VOPatientCategoryAndPaymentModeMst.getModuleNames");

		try 
		{
			String strQry = billing.qryHandler_billing.getQuery(1,"select.patcatandpaymentmodeMst.2").replace("?", hosCode);
			/*if (strRefundPaymentMode == null)
				strRefundPaymentMode = "0";*/

			strRefundPaymentModeValues = hisUtil.getOptionValue(strQry, strRefundPaymentModeValues,"0^Select Value");
			hisUtil = null;
		} 
		catch (Exception e) 
		{
			new HisException("Advance Master","VOPatientCategoryAndPaymentModeMst.getStrRefundPaymentModeValues", e.getMessage());
		}
		return strRefundPaymentModeValues;
	}

	public void setStrRefundPaymentModeValues(String strRefundPaymentModeValues) {
		this.strRefundPaymentModeValues = strRefundPaymentModeValues;
	}

	public WebRowSet getStrPayemtnModeMapping() {
		return strPayemtnModeMapping;
	}

	public void setStrPayemtnModeMapping(WebRowSet strPayemtnModeMapping) {
		this.strPayemtnModeMapping = strPayemtnModeMapping;
	}
	
	
}
