/* Counter Master FB
 * Created By: Pawan Kumar B N
 * Created On: 01-Sep-2011
 */
package billing.masters.controller.fb;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

/**
 * The Class CounterMstFB.
 */
public class CounterMstFB extends GenericFormBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The str hospital code. */
	private String strHospitalCode = "";
	
	/** The module id. */
	private String moduleId = "";
	
	/** The ip address. */
	private String ipAddress = "";
	
	/** The counter name. */
	private String counterName = "";
	
	/** The location id. */
	private String locationId = "";
	
	/** The effective frm. */
	private String effectiveFrm = null;
	
	/** The str crnt date. */
	private String strCrntDate = null;
	
	/** The remarks. */
	private String remarks = "";
	
	/** The mod id. */
	private String modId = "";
	
	/** The lst modify date. */
	private String lstModifyDate = "";
	
	/** The lst seat id. */
	private String lstSeatId = "";
	
	/** The seat id. */
	private String seatId = "";
	
	/** The is valid. */
	private String isValid = "";
	
	/** The str module name. */
	private String strModuleName = "";
	
	/** The str msg. */
	private String strMsg = "";

	/** The str building cmb. */
	private String strBuildingCmb = "";
	
	/** The str block cmb. */
	private String strBlockCmb = "";
	
	/** The str floor cmb. */
	private String strFloorCmb = "";

	/** The str building id. */
	private String strBuildingId = "0";
	
	/** The str block id. */
	private String strBlockId = "0";
	
	/** The str floor id. */
	private String strFloorId = "0";

	/** The errmsg. */
	private String errmsg = "";

	/** The normal msg. */
	private String normalMsg = "";

	/** The warning msg. */
	private String warningMsg = "";

	/** The str chk1. */
	private String strChk1 = "";
	
	/** THe str Location **/
	private String strLocation="";

	
	/**
	 * Gets the warning msg.
	 * 
	 * @return Returns the warningMsg.
	 */
	public String getWarningMsg() {
		return warningMsg;
	}

	/**
	 * Gets the normal msg.
	 * 
	 * @return Returns the normalMsg.
	 */
	public String getNormalMsg() {
		return normalMsg;
	}

	/**
	 * Gets the errmsg.
	 * 
	 * @return Returns the errmsg.
	 */
	public String getErrmsg() {
		return errmsg;
	}

	/**
	 * Gets the is valid.
	 * 
	 * @return Returns the isValid.
	 */
	public String getIsValid() {
		return isValid;
	}

	/**
	 * Gets the seat id.
	 * 
	 * @return Returns the seatId.
	 */
	public String getSeatId() {
		return seatId;
	}

	/**
	 * Gets the lst modify date.
	 * 
	 * @return Returns the lstModifyDate.
	 */
	public String getLstModifyDate() {
		return lstModifyDate;
	}

	/**
	 * Gets the lst seat id.
	 * 
	 * @return Returns the lstSeatId.
	 */
	public String getLstSeatId() {
		return lstSeatId;
	}
	/**
	 * Gets the Location.
	 * 
	 * @return Returns the Location.
	 */

	public String getStrLocation() {
		return strLocation;
	}

	public void setStrLocation(String strLocation) {
		this.strLocation = strLocation;
	}

	/**
	 * Gets the mod id.
	 * 
	 * @return Returns the modId.
	 */
	public String getModId() {
		return modId;
	}

	/**
	 * Gets the remarks.
	 * 
	 * @return Returns the remarks.
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Gets the counter name.
	 * 
	 * @return Returns the counterName.
	 */
	public String getCounterName() {
		return counterName;
	}

	/**
	 * Gets the effective frm.
	 * 
	 * @return Returns the effectiveFrm.
	 * 
	 * @throws Exception the exception
	 */
	public String getEffectiveFrm() throws Exception {

		if (this.effectiveFrm == null) {
			HisUtil util = new HisUtil("billing", "VOCounterMst");
			try {
				effectiveFrm = util.getASDate("dd-MMM-yyyy");
				strCrntDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				throw new Exception("Billing.VOcounterMst.getEffectiveFrm()-->"
						+ e.getMessage());
			}
		}
		return effectiveFrm;
	}

	/**
	 * Gets the ip address.
	 * 
	 * @return Returns the ipAddress.
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Gets the location id.
	 * 
	 * @return Returns the locationId.
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * Gets the module id.
	 * 
	 * @return Returns the moduleId.
	 */
	public String getModuleId() {
		return moduleId;
	}

	// ---------SETTER METHODS----------//

	/**
	 * Sets the normal msg.
	 * 
	 * @param normalMsg The normalMsg to set.
	 */
	public void setNormalMsg(String normalMsg) {
		this.normalMsg = normalMsg;
	}

	/**
	 * Sets the counter name.
	 * 
	 * @param counterName The counterName to set.
	 */
	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	/**
	 * Sets the effective frm.
	 * 
	 * @param effectiveFrm The effectiveFrm to set.
	 */
	public void setEffectiveFrm(String effectiveFrm) {
		this.effectiveFrm = effectiveFrm;
	}

	/**
	 * Sets the ip address.
	 * 
	 * @param ipAddress The ipAddress to set.
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Sets the location id.
	 * 
	 * @param locationId The locationId to set.
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * Sets the module id.
	 * 
	 * @param moduleId The moduleId to set.
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * Sets the remarks.
	 * 
	 * @param remarks The remarks to set.
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * Sets the mod id.
	 * 
	 * @param modId The modId to set.
	 */
	public void setModId(String modId) {
		this.modId = modId;
	}

	/**
	 * Sets the lst modify date.
	 * 
	 * @param lstModifyDate The lstModifyDate to set.
	 */
	public void setLstModifyDate(String lstModifyDate) {
		this.lstModifyDate = lstModifyDate;
	}

	/**
	 * Sets the lst seat id.
	 * 
	 * @param lstSeatId The lstSeatId to set.
	 */
	public void setLstSeatId(String lstSeatId) {
		this.lstSeatId = lstSeatId;
	}

	/**
	 * Sets the seat id.
	 * 
	 * @param seatId The seatId to set.
	 */
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	/**
	 * Sets the is valid.
	 * 
	 * @param isValid The isValid to set.
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	/**
	 * Sets the errmsg.
	 * 
	 * @param errmsg The errmsg to set.
	 */
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	/**
	 * Sets the warning msg.
	 * 
	 * @param warningMsg The warningMsg to set.
	 */
	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	// ---------- combo functions ----------------//

	/*
	 * Module Combo for the modify page.
	 */
	/**
	 * Gets the mod module combo.
	 * 
	 * @return the mod module combo
	 */
	public String getModModuleCombo() {
		String tempStr = "";
		HisUtil hisutil = new HisUtil("Billing", "VOcounterMst");
		try {
			if (this.moduleId == null)
				this.moduleId = "";
			tempStr = hisutil.getOptionValue(billing.qryHandler_billing
					.getQuery(1, "select.counterMst.1"), getModuleId(),
					"0^select value");
			hisutil = null;
		} catch (Exception e) {
			new HisException("billing", "VOcounterMst.getModModuleCombo()", e
					.getMessage());
		}
		return tempStr;
	}

	/*
	 * Module combo for the add page.
	 */
	/**
	 * Gets the module combo.
	 * 
	 * @return the module combo
	 * 
	 * @throws Exception the exception
	 */
	public String getModuleCombo() throws Exception {
		String modStr = "";
		HisUtil hisutil = new HisUtil("Billing", "VOcounterMst");
		String qry = billing.qryHandler_billing.getQuery(1,
				"select.counterMst.1");
		try {
			modStr = hisutil.getOptionValue(qry, "0", "0^select value");
		} catch (Exception e) {
			throw new Exception("billing.VOcounterMst.getModuleCombo()-->"
					+ e.getMessage());
		}
		return modStr;
	}

	/**
	 * Gets the str hospital code.
	 * 
	 * @return the str hospital code
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * Sets the str hospital code.
	 * 
	 * @param strHospitalCode the new str hospital code
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * Gets the str module name.
	 * 
	 * @return the str module name
	 */
	public String getStrModuleName() {
		return strModuleName;
	}

	/**
	 * Sets the str module name.
	 * 
	 * @param strModuleName the new str module name
	 */
	public void setStrModuleName(String strModuleName) {
		this.strModuleName = strModuleName;
	}

	/**
	 * Gets the str building cmb.
	 * 
	 * @return the str building cmb
	 */
	public String getStrBuildingCmb() {
		return strBuildingCmb;
	}

	/**
	 * Sets the str building cmb.
	 * 
	 * @param strBuildingCmb the new str building cmb
	 */
	public void setStrBuildingCmb(String strBuildingCmb) {
		this.strBuildingCmb = strBuildingCmb;
	}

	/**
	 * Gets the str block cmb.
	 * 
	 * @return the str block cmb
	 */
	public String getStrBlockCmb() {
		return strBlockCmb;
	}

	/**
	 * Sets the str block cmb.
	 * 
	 * @param strBlockCmb the new str block cmb
	 */
	public void setStrBlockCmb(String strBlockCmb) {
		this.strBlockCmb = strBlockCmb;
	}

	/**
	 * Gets the str floor cmb.
	 * 
	 * @return the str floor cmb
	 */
	public String getStrFloorCmb() {
		return strFloorCmb;
	}

	/**
	 * Sets the str floor cmb.
	 * 
	 * @param strFloorCmb the new str floor cmb
	 */
	public void setStrFloorCmb(String strFloorCmb) {
		this.strFloorCmb = strFloorCmb;
	}

	/**
	 * Gets the str building id.
	 * 
	 * @return the str building id
	 */
	public String getStrBuildingId() {
		return strBuildingId;
	}

	/**
	 * Sets the str building id.
	 * 
	 * @param strBuildingId the new str building id
	 */
	public void setStrBuildingId(String strBuildingId) {
		this.strBuildingId = strBuildingId;
	}

	/**
	 * Gets the str block id.
	 * 
	 * @return the str block id
	 */
	public String getStrBlockId() {
		return strBlockId;
	}

	/**
	 * Sets the str block id.
	 * 
	 * @param strBlockId the new str block id
	 */
	public void setStrBlockId(String strBlockId) {
		this.strBlockId = strBlockId;
	}

	/**
	 * Gets the str floor id.
	 * 
	 * @return the str floor id
	 */
	public String getStrFloorId() {
		return strFloorId;
	}

	/**
	 * Sets the str floor id.
	 * 
	 * @param strFloorId the new str floor id
	 */
	public void setStrFloorId(String strFloorId) {
		this.strFloorId = strFloorId;
	}

	/**
	 * Gets the str chk1.
	 * 
	 * @return the str chk1
	 */
	public String getStrChk1() {
		return strChk1;
	}

	/**
	 * Sets the str chk1.
	 * 
	 * @param strChk1 the new str chk1
	 */
	public void setStrChk1(String strChk1) {
		this.strChk1 = strChk1;
	}

	/**
	 * Gets the str crnt date.
	 * 
	 * @return the str crnt date
	 * 
	 * @throws Exception the exception
	 */
	public String getStrCrntDate() throws Exception {

		if (this.strCrntDate == null) {
			HisUtil util = new HisUtil("billing", "VOCounterMst");
			try {
				strCrntDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				throw new Exception("Billing.VOcounterMst.getStrCrntDate()-->"
						+ e.getMessage());
			}
		}

		return strCrntDate;
	}

	/**
	 * Sets the str crnt date.
	 * 
	 * @param strCrntDate the new str crnt date
	 */
	public void setStrCrntDate(String strCrntDate) {
		this.strCrntDate = strCrntDate;
	}

	/**
	 * Gets the str msg.
	 * 
	 * @return the str msg
	 */
	public String getStrMsg() {
		return strMsg;
	}

	/**
	 * Sets the str msg.
	 * 
	 * @param strMsg the new str msg
	 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
}
