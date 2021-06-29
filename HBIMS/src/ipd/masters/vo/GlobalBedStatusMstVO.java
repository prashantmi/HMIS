package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;


public class GlobalBedStatusMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strWarning = "";
	private String strMsg = "";
	private String strBedStatusCode = "";
	private String strBedStatusName = "";
	private String strSeatId = "";
	private String strLastModifiedSeatId = "";
	private String strIsValid = "";
	private String strEffectiveDate = null;
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strErrorMsg = "";
	private String strChk1 = "";
	private String strRemarks = "";
	private String strCtDate = null;
	private String strHospitalCode="";

	public String getStrWarning() {
		return strWarning;
	}

	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
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
/**
 * 
 * @param strRemarks
 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	public String getStrBedStatusCode() {
		return strBedStatusCode;
	}
/**
 * 
 * @param strBedStatusCode
 */
	public void setStrBedStatusCode(String strBedStatusCode) {
		this.strBedStatusCode = strBedStatusCode;
	}

	public String getStrBedStatusName() {
		return strBedStatusName;
	}
/**
 * 
 * @param strBedStatusName
 */
	public void setStrBedStatusName(String strBedStatusName) {
		this.strBedStatusName = strBedStatusName;
	}
/**
 * 
 * @param strEffectiveDate
 */
	public void setStrEffectiveDate(String strEffectiveDate) {
		this.strEffectiveDate = strEffectiveDate;
	}

	public String getStrEffectiveDate() {
		if (this.strEffectiveDate == null) {
			HisUtil util = new HisUtil("Ward Type Master", "VOWardTypeMst");
			try {
				strEffectiveDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return strEffectiveDate;
	}
/**
 * 
 * @param strEffectiveFrom
 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	public String getStrEffectiveFrom() {
		if (this.strEffectiveFrom == null) {
			HisUtil util = new HisUtil("Bed Status Master", "VOBedStatusMst");
			try {
				strEffectiveFrom = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return strEffectiveFrom;
	}
/**
 * 
 * @param strEffectiveTo
 */
	public void setStrEffectiveTo(String strEffectiveTo) {
		this.strEffectiveTo = strEffectiveTo;
	}

	public String getStrEffectiveTo() {
		if (this.strEffectiveTo == null) {
			HisUtil util = new HisUtil("Bed Status Master", "VOBedStatusMst");
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
/**
 * 
 * @param strSeatId
 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrBedCode() {
		return strBedStatusCode;
	}
/**
 * 
 * @param strBedStatusCode
 */
	public void setStrBedCode(String strBedStatusCode) {
		this.strBedStatusCode = strBedStatusCode;
	}

	public String getStrErrorMsg() {
		return strErrorMsg;
	}
/**
 * 
 * @param strErrorMsg
 */
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	public String getStrMsg() {
		return strMsg;
	}
/**
 * 
 * @param strMsg
 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}
	// function for gettin sysdate in date
	// picker
	public String getStrCtDate() { 
		HisUtil util = new HisUtil("Bed Status Master", "VOBed Status Mst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Bed Status Master",
					"VOBedStatusMst.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
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
/**
 * 
 * @param strHospitalCode
 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

}
