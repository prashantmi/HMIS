package ipd.masters.vo;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;


public class GlobalBedTypeMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strBedTypeCode = "";
	private String strBedType = "";
	private String strSeatId = "";
	private String strLastModifiedSeatId="";
	private String strIsValid = "";
	private String strEffectiveDate = null;
	private String strEffectiveFrom = null;
	private String strEffectiveTo = null;
	private String strHospital_id ="";
	private String strErrorMsg = "";
	private String strChk1 = "";
	private String strRemarks = "";
	private String strMsg = "";
	private String strWarning = "";

	private String strCtDate = null;

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

	public String getStrBedTypeCode() {
		return strBedTypeCode;
	}

	public void setStrBedTypeCode(String strBedTypeCode) {
		this.strBedTypeCode = strBedTypeCode;
	}

	public String getStrBedType() {
		return strBedType;
	}

	public void setStrBedType(String strBedType) {
		this.strBedType = strBedType;
	}

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

	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	public String getStrCtDate() { // function for gettin sysdate in date
									// picker
		HisUtil util = new HisUtil("Bed Type Master", "VOBedTypeMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "BedTypeMaster",
					"VOBedTypeMst.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}

	public String getStrMsg() {
		return strMsg;
	}

	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	public String getStrWarning() {
		return strWarning;
	}

	public void setStrWarning(String strWarning) {
		this.strWarning = strWarning;
	}

	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}

	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}

	public String getStrHospital_id() {
		return strHospital_id;
	}

	public void setStrHospital_id(String strHospital_id) {
		this.strHospital_id = strHospital_id;
	}

	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}
}
