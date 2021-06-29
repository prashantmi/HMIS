package ipd.masters.vo;

import javax.sql.rowset.WebRowSet;

import hisglobal.exceptions.HisException;
import hisglobal.masterutil.GenericFormBean;
import hisglobal.utility.HisUtil;

public class WardTypeMstVO extends GenericFormBean {

	private static final long serialVersionUID = 02L;

	private String strWardType = "";
	private String strSeatId = "";
	private String strLastModifiedSeatId = "";
	private String strIsValid = "";
	private String strCtDate = null;
	private String strEffectiveDate = null;
	private String strerrorMsg = "";
	private String chk1 = "";
	private String msg = "";
	private String warnings = "";
	private String strhospitalCode="";
	private String wardTypeCode="";
	private String strGlobalWardTypeCombo="";
	private String strGlobalWardType="";
	WebRowSet wrsGlobalWardType;
	private String blockhrs="";
	public String getChk1() {
		return chk1;
	}

	public void setChk1(String chk1) {
		this.chk1 = chk1;
	}

	/**
	 * 
	 * @return
	 */
	public String getStrCtDate() { // function for gettin sysdate in date
		// picker
		HisUtil util = new HisUtil("Ward Master", "VOWardMst");
		try {
			strCtDate = util.getASDate("dd-MMM-yyyy");
			util = null;
		} catch (Exception e) {
			new HisException("Ipd Module", "Ward Master",
					"VOWardMst.getStrCtDate()-->" + e.getMessage());
		}

		return strCtDate;
	}

	public void setStrEffectiveDate(String strEffectiveDate) {
		this.strEffectiveDate = strEffectiveDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getStrEffectiveDate() {
		if (this.strEffectiveDate == null) {
			HisUtil util = new HisUtil("Ward Type Master", "VOWardTypeMst");
			try {
				strEffectiveDate = util.getASDate("dd-MMM-yyyy");
				util = null;
			} catch (Exception e) {
				new HisException("IPD", "VOWardTypeMst.getStrEffectiveDate", e
						.getMessage());
			}
		}
		return strEffectiveDate;
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

	public String getStrWardType() {
		return strWardType;
	}

	public void setStrWardType(String strWardType) {
		this.strWardType = strWardType;
	}

	public String getStrerrorMsg() {
		return strerrorMsg;
	}

	public void setStrerrorMsg(String strerrorMsg) {
		this.strerrorMsg = strerrorMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getWarnings() {
		return warnings;
	}

	public void setWarnings(String warnings) {
		this.warnings = warnings;
	}

	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}

	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}

	
	public String getWardTypeCode() {
		return wardTypeCode;
	}

	public void setWardTypeCode(String wardTypeCode) {
		this.wardTypeCode = wardTypeCode;
	}

	public String getStrhospitalCode() {
		return strhospitalCode;
	}

	public void setStrhospitalCode(String strhospitalCode) {
		this.strhospitalCode = strhospitalCode;
	}

	public String getStrGlobalWardTypeCombo() {
		return strGlobalWardTypeCombo;
	}

	public void setStrGlobalWardTypeCombo(String strGlobalWardTypeCombo) {
		this.strGlobalWardTypeCombo = strGlobalWardTypeCombo;
	}

	public WebRowSet getWrsGlobalWardType() {
		return wrsGlobalWardType;
	}

	public void setWrsGlobalWardType(WebRowSet wrsGlobalWardType) {
		this.wrsGlobalWardType = wrsGlobalWardType;
	}

	public String getStrGlobalWardType() {
		return strGlobalWardType;
	}

	public void setStrGlobalWardType(String strGlobalWardType) {
		this.strGlobalWardType = strGlobalWardType;
	}

	public String getBlockhrs() {
		return blockhrs;
	}

	public void setBlockhrs(String blockhrs) {
		this.blockhrs = blockhrs;
	}

	
	
}
