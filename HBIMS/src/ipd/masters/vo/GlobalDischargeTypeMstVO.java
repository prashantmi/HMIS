package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

public class GlobalDischargeTypeMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strDischargeTypeCode = "";
	private String strDischargeTypeName = "";
	private String strSeatId = "";
	private String strLastModifiedSeatId = "";
	private String strIsValid = "";
	private String strEffectiveDate = null;
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strErrorMsg = "";
	private String strChk1 = "";
	private String strRemarks = "";
	private String strMsg = "";
	private String strCtDate = null;
	private String strWarning = "";
	private String strHospitalCode="";

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
	/**
	 * This function is used to return current date of system
	 * @return
	 */

	public String getStrCtDate() { // function for gettin SYSDATE
		HisUtil util = new HisUtil("DischargeType", "VODischargeTypeMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "DischargeTypeMaster",
					"VODischargeTypeMst.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}

	public String getStrChk1() {
		return strChk1;
	}

	public void setStrChk1(String strChk1) {
		this.strChk1 = strChk1;
	}

	public String getStrRemarks() {
		return strRemarks;
	}

	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrDischargeTypeCode() {
		return strDischargeTypeCode;
	}

	public void setStrDischargeTypeCode(String strDischargeTypeCode) {
		this.strDischargeTypeCode = strDischargeTypeCode;
	}

	public String getStrDischargeTypeName() {
		return strDischargeTypeName;
	}

	public void setStrDischargeTypeName(String strDischargeTypeName) {
		this.strDischargeTypeName = strDischargeTypeName;
	}

	public void setStrEffectiveDate(String strEffectiveDate) {
		this.strEffectiveDate = strEffectiveDate;
	}

	public String getStrEffectiveDate() {
		if (this.strEffectiveDate == null) {
			HisUtil util = new HisUtil("Discharge Type Master",
					"VODischargeTypeMst");
			try {
				strEffectiveDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ipd Module", "DischargeTypeMaster",
						"VODischargeTypeMst.getStrEffectiveDate()-->" + e.getMessage());
			}
		}
		return strEffectiveDate;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Discharge Type Master",
					"VODischargeTypeMst");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ipd Module", "DischargeTypeMaster",
						"VODischargeTypeMst.setStrEffectiveFrom()-->" + e.getMessage());
			}
		}
		return strEffectiveFrom;
	}

	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	public String getStrEffectiveTo() {
		if (this.strEffectiveTo == null) {
			HisUtil util = new HisUtil("Discharge Type Master",
					"VODischargeTypeMst");
			try {
				strEffectiveTo = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("Ipd Module", "DischargeTypeMaster",
						"VODischargeTypeMst.getStrEffectiveTo()-->" + e.getMessage());
			
			}
		}
		return strEffectiveTo;
	}

	public String getStrIsValid() {
		return strIsValid;
	}

	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	public String getStrSeatId() {
		return strSeatId;
	}

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}

	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}

	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
}
