package vo.registration;

import hisglobal.exceptions.HisException;
import hisglobal.utility.HisUtil;
import hisglobal.vo.ValueObject;

public class DeathMannerMstVO extends ValueObject {

	private static final long serialVersionUID = 02L;

	private String strDeathMannerCode = "";
	private String strDeathManner = "";
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

	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrCtDate() { // function for gettin SYSDATE
		HisUtil util = new HisUtil("DeathMannerMaster", "VODeathMannerMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "DeathMannerMaster",
					"VODeathMannerMst.getStrCtDate()-->" + e.getMessage());
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

	public String getStrDeathMannerCode() {
		return strDeathMannerCode;
	}

	public void setStrDeathMannerCode(String strDeathMannerCode) {
		this.strDeathMannerCode = strDeathMannerCode;
	}

	public String getStrDeathManner() {
		return strDeathManner;
	}

	public void setStrDeathManner(String strDeathManner) {
		this.strDeathManner = strDeathManner;
	}

	public void setStrEffectiveDate(String strEffectiveDate) {
		this.strEffectiveDate = strEffectiveDate;
	}

	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrEffectiveDate() {
		if (this.strEffectiveDate == null) {
			HisUtil util = new HisUtil("Death Manner Master",
					"VODeathMannerMst");
			try {
				strEffectiveDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return strEffectiveDate;
	}

	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Death Manner Master",
					"VODeathMannerMst");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return strEffectiveFrom;
	}

	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	/**
	 * retrieves Current Date From Application Server and returns the Same.
	 * 
	 * @return - Current Date in String Format
	 */
	public String getStrEffectiveTo() {
		if (this.strEffectiveTo == null) {
			HisUtil util = new HisUtil("Death Manner Master",
					"VODeathMannerMst");
			try {
				strEffectiveTo = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				//e.printStackTrace();
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

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
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
