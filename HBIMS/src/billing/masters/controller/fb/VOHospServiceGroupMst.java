package billing.masters.controller.fb;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.WebRowSet;

import billing.BillConfigUtil;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HisUtil;

public class VOHospServiceGroupMst extends GenericFormBean {

	private static final long serialVersionUID = 02L;
	private String LastSeatId = "";
	private String lgroupName = "";
	private String[] rgroupName = null;
	private String hserviceName = "";
	private String effectiveFrom = null;
	private String chk1 = "";
	private String remark = "";
	private String isValid = "";
	private String seatId = "";
	private String strErrorMsg = "";
	private String msg = "";
	private String warnings = "";
	private String strServiceCode = "";
	private String strGroupCode = "";
	private String strHospitalCode = "";
	private String strCtDate = "";
	private String effectiveTo = null;
	private String chargetypeid = "";

	HttpSession httpSession = null;

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	public String getStrGroupCode() {
		return strGroupCode;
	}

	public void setStrGroupCode(String strGroupCode) {
		this.strGroupCode = strGroupCode;
	}

	public String getStrServiceCode() {
		return strServiceCode;
	}

	public void setStrServiceCode(String strServiceCode) {
		this.strServiceCode = strServiceCode;
	}

	public String getChk1() {
		return chk1;
	}

	public void setChk1(String chk1) {
		this.chk1 = chk1;
	}

	/*
	 * public void setEffectiveDate(String effectiveFrom) { this.effectiveFrom =
	 * effectiveFrom; }
	 */

	public String getLgroupName() {
		return lgroupName;
	}

	public void setLgroupName(String lgroupName) {
		this.lgroupName = lgroupName;
	}

	public String[] getRgroupName() {
		return rgroupName;
	}

	public void setRgroupName(String[] rgroupName) {
		this.rgroupName = rgroupName;
	}

	public String getHserviceName() {
		return hserviceName;
	}

	public void setHserviceName(String hserviceName) {
		this.hserviceName = hserviceName;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCmbval() 
	{
		String errMsg = "";
		String comboValues = "<option value='0'>Select Value</option>";
		HisUtil hisutil = new HisUtil("Billing", "VOHospServiceGroupMst");

		try 
		{
			String hosCode = this.getStrHospitalCode();
			String qry = billing.qryHandler_billing.getQuery(1,"select.hservicegroup.1");
			comboValues = hisutil.getOptionValue(qry.replace("?", hosCode),"0", "0^Select Value");

			int qryIndex;
			HisDAO dao = null;
			dao = new HisDAO("Billing", "DAOHospServiceGroupMst");
			qryIndex = dao.setQuery(qry);

			//dao.setQryValue(qryIndex, 1, this.getStrHospitalCode());
			dao.setQryValue(qryIndex, 1, BillConfigUtil.SUPER_HOSPITAL_CODE);
			WebRowSet wb = dao.executeQry(qryIndex);

			if (hosCode == null)
				hosCode = "0";
			comboValues = hisutil.getOptionValue(wb, "0", "0^Select Value",false);
			hisutil = null;
		} 
		catch (Exception e) 
		{
			errMsg = "Billing.VOHospServiceGroupMst() -->" + e.getMessage();
			new HisException("Billing", "VOHospServiceGroupMst.getCmbval",errMsg);
		}

		return comboValues;
	}

	public String getListValues() {

		String val = this.getHserviceName();
		// System.out.println("val--->" + val);

		String listValues = "<option value='0'> Select Value </option>";
		HisUtil hisUtil = new HisUtil("Hospital Service Group Master",
				"CNTHospServiceGroupMst");
		try {
			String qry = billing.qryHandler_billing.getQuery(1,
					"select.hservicegroup.3");
			// System.out.println("inside first
			// try"+billing.qryHandler_billing.getQuery(1,"select.hservicegroup.3"));
			int qryIndex;
			HisDAO dao = null;

			dao = new HisDAO("Billing", "DAOHospServiceGroupMst");
			qryIndex = dao.setQuery(qry);

			// System.out.println("hservice val--->"+val);
			// System.out.println("hosp code--->"+this.getStrHospitalCode());
			// System.out.println("hservice val--->"+val);
			// System.out.println("hosp code--->"+this.getStrHospitalCode());
			// System.out.println("hosp code--->"+this.getStrHospitalCode());

			dao.setQryValue(qryIndex, 1, val);
			dao.setQryValue(qryIndex, 2, this.getStrHospitalCode());
			dao.setQryValue(qryIndex, 3, this.getStrHospitalCode());
			/*
			 * dao.setQryValue(qryIndex,3,val);
			 * dao.setQryValue(qryIndex,4,this.getStrHospitalCode());
			 * dao.setQryValue(qryIndex,5,this.getStrHospitalCode());
			 */

			WebRowSet wb = dao.executeQry(qryIndex);

			// System.out.println("list query"+
			// billing.qryHandler_billing.getQuery(1,"select.hservicegroup.3"));
			if (val == null)
				val = "0";
			listValues = hisUtil.getOptionValue(wb, "0", "", false);
			// if (val1 == null)val1 = "0";

			// listValues = hisUtil.getOptionValue(qry.replace("?", val), "0",
			// "0^Select Value");

			// //System.out.println("listQuery :" + qry.replace("?", val));

			// System.out.println(listValues);
			hisUtil = null;

		} catch (Exception e) {
			strErrorMsg = "billing.VOHospServiceGroupMst() -->"
					+ e.getMessage();
			new HisException("Billing", "VOHospServiceGroupMst.getListValues",
					strErrorMsg);
		}

		return listValues;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getLastSeatId() {
		return LastSeatId;
	}

	public void setLastSeatId(String lastSeatId) {
		LastSeatId = lastSeatId;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getStrCtDate() {
		HisUtil util = new HisUtil("Hospital Service Group Master",
				"VOGroupMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Billing Module", "Hospital Service Group Master",
					"VOHospServiceGroupMst.getStrCtDate()-->" + e.getMessage());
		}
		return strCtDate;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public String getChargetypeid() {
		return chargetypeid;
	}

	public void setChargetypeid(String chargetypeid) {
		this.chargetypeid = chargetypeid;
	}

}