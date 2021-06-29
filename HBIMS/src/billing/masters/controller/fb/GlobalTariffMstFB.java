
package billing.masters.controller.fb;

/* Global Tariff Master FB
 * author: Debashis Sardar
 * Created on : 14-Sep-2011
 */
import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

public class GlobalTariffMstFB extends GenericFormBean {

	private static final long serialVersionUID = 02L;


	private String strDataMode = "0";
	private String strtariffId = "0";
	private String strgrpId = "0";
	private String strGroupId = "0";// To persist group id on modify
	private String strtrfPkgName = "";
    private String strDiscountLimit = "0";
	private String strDiscountPrivilege = "0";
	private String strUpdateChargeUnit = "0";
	private String strtariffName = "";
	private String strServiceId = "";
	private String strgroupName = "";
	private String strseatId = "";
    private String strisValid = "1";
	private String errMsg = "";
	private String strtrfId = "";
	private String normalMsg = "";
	private String strHospitalCode = "";
	private String strChK = "";
	private String strtariffCode ="";
	private String strflag ="";
	private String strMsgType="";
	private String strMsgString="";
	private String strsubgroupName= "";

	

	

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

		
	public String getCurrentDate() {
		HisUtil util = new HisUtil("billing", "GlobalTariffMstVO");
		String strCurDate = "";
		try {
			strCurDate = util.getASDate("dd-MMM-yyyy");
		} catch (Exception e) {
			new HisException("Billing", "GlobalTariffMstVO.getCurrentDate -->", e
					.getMessage());
		}
		return strCurDate;
	}


	public String getNormalMsg() {
		return normalMsg;
	}

	
	
	public void setNormalMsg(String normalMsg) {
		this.normalMsg = normalMsg;
	}

	

	public String getStrChK() {
		return strChK;
	}

	public void setStrChK(String strChK) {
		this.strChK = strChK;
	}

	public String getStrGroupId() {
		return strGroupId;
	}

	public void setStrGroupId(String strGroupId) {
		this.strGroupId = strGroupId;
	}

	
	public String getStrDataMode() {
		return strDataMode;
	}

	public void setStrDataMode(String strDataMode) {
		this.strDataMode = strDataMode;
	}

	
	public String getStrDiscountLimit() {
		return strDiscountLimit;
	}

	public void setStrDiscountLimit(String strDiscountLimit) {
		this.strDiscountLimit = strDiscountLimit;
	}

	public String getStrDiscountPrivilege() {
		return strDiscountPrivilege;
	}

	public void setStrDiscountPrivilege(String strDiscountPrivilege) {
		this.strDiscountPrivilege = strDiscountPrivilege;
	}


	public String getStrtariffId() {
		return strtariffId;
	}

	public void setStrtariffId(String strtariffId) {
		this.strtariffId = strtariffId;
	}



	public String getStrtrfPkgName() {
		return strtrfPkgName;
	}

	public void setStrtrfPkgName(String strtrfPkgName) {
		this.strtrfPkgName = strtrfPkgName;
	}

	public String getStrtariffName() {
		return strtariffName;
	}

	public void setStrtariffName(String strtariffName) {
		this.strtariffName = strtariffName;
	}

	public String getStrgroupName() {
		return strgroupName;
	}

	public void setStrgroupName(String strgroupName) {
		this.strgroupName = strgroupName;
	}

	public String getStrseatId() {
		return strseatId;
	}

	public void setStrseatId(String strseatId) {
		this.strseatId = strseatId;
	}

	public String getStrisValid() {
		return strisValid;
	}

	public void setStrisValid(String strisValid) {
		this.strisValid = strisValid;
	}

	
	public String getStrtrfId() {
		return strtrfId;
	}

	public void setStrtrfId(String strtrfId) {
		this.strtrfId = strtrfId;
	}

	public String getStrtariffCode() {
		return strtariffCode;
	}

	public void setStrtariffCode(String strtariffCode) {
		this.strtariffCode = strtariffCode;
	}

	public String getStrflag() {
		return strflag;
	}

	public void setStrflag(String strflag) {
		this.strflag = strflag;
	}

	public String getStrUpdateChargeUnit() {
		return strUpdateChargeUnit;
	}

	public void setStrUpdateChargeUnit(String strUpdateChargeUnit) {
		this.strUpdateChargeUnit = strUpdateChargeUnit;
	}

	public String getStrMsgType() {
		return strMsgType;
	}

	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	public String getStrMsgString() {
		return strMsgString;
	}

	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	public String getStrgrpId() {
		return strgrpId;
	}

	public void setStrgrpId(String strgrpId) {
		this.strgrpId = strgrpId;
	}

	

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}


	public String getStrServiceId() {
		return strServiceId;
	}

	public void setStrServiceId(String strServiceId) {
		this.strServiceId = strServiceId;
	}

	public String getStrsubgroupName() {
		return strsubgroupName;
	}

	public void setStrsubgroupName(String strsubgroupName) {
		this.strsubgroupName = strsubgroupName;
	}
	public String getAddsubgroupCombo()
	{
		StringBuffer trfStr = new StringBuffer();
		String errMsg = "";
		String comboValues = "<option value='0'>Select Value </option>";
		HisUtil hisutil = new HisUtil("Billing", "GlobalTariffMstFB");
		try
		{
			trfStr.append(billing.qryHandler_billing.getQuery(1, "select.global.subgroupMst.1").replace("?", this.getStrgrpId()));
			comboValues = hisutil.getOptionValue(trfStr.toString(), this.getStrsubgroupName(), "0^Select Value");
		} 
		catch (Exception e)
		{
			errMsg = "Billing.LocalTariffMstFB() -->" + e.getMessage();
			new HisException("billing", "GlobalTariffMstFB.getAddsubgroupCombo()", errMsg);
		}
		return comboValues;
	}
	public String getModifysubgroupCombo()
	{
		StringBuffer trfStr = new StringBuffer();
		String errMsg = "";
		String comboValues = "<option value='0'>Select Value </option>";
		HisUtil hisutil = new HisUtil("Billing", "GlobalTariffMstFB");
		try
		{
			trfStr.append(billing.qryHandler_billing.getQuery(1, "select.global.subgroupMst.1").replace("?", this.getStrgrpId()));
			comboValues = hisutil.getOptionValue_DefaultSelected(trfStr.toString(), this.getStrsubgroupName(), "0^Select Value",this.getStrsubgroupName());
		} 
		catch (Exception e)
		{
			errMsg = "Billing.LocalTariffMstFB() -->" + e.getMessage();
			new HisException("billing", "GlobalTariffMstFB.getAddsubgroupCombo()", errMsg);
		}
		return comboValues;
	}

}