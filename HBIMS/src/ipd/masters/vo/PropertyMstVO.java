package ipd.masters.vo;

import hisglobal.masterutil.GenericFormBean;

public class PropertyMstVO extends GenericFormBean {
	private static final long serialVersionUID = 02L;
	private String strPropertyID = "";
	private String strPropertyName = "";
	private String strEffectiveFrom = null;
	private String strRemark = "";
	private String strSeatId = "";
	
	private String strErrorMsg = "";
	private String strIsValid = "";
	private String strCtDate = null;
	
	private String strWarnings = "";
	private String strMsg="";
	
	private String strHospitalCode="";
	private String strLastModifiedSeatId = "";
	private String strChk="";

	/**
	 * @return the strPropertyID
	 */
	public String getStrPropertyID() {
		return strPropertyID;
	}

	/**
	 * @param strPropertyID the strPropertyID to set
	 */
	public void setStrPropertyID(String strPropertyID) {
		this.strPropertyID = strPropertyID;
	}

	/**
	 * @return the strPropertyName
	 */
	public String getStrPropertyName() {
		return strPropertyName;
	}

	/**
	 * @param strPropertyName the strPropertyName to set
	 */
	public void setStrPropertyName(String strPropertyName) {
		this.strPropertyName = strPropertyName;
	}

	/**
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	/**
	 * @param strEffectiveFrom the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * @return the strRemark
	 */
	public String getStrRemark() {
		return strRemark;
	}

	/**
	 * @param strRemark the strRemark to set
	 */
	public void setStrRemark(String strRemark) {
		this.strRemark = strRemark;
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
	 * @return the strErrorMsg
	 */
	public String getStrErrorMsg() {
		return strErrorMsg;
	}

	/**
	 * @param strErrorMsg the strErrorMsg to set
	 */
	public void setStrErrorMsg(String strErrorMsg) {
		this.strErrorMsg = strErrorMsg;
	}

	/**
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}

	/**
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * @return the strCtDate
	 */
	public String getStrCtDate() {
		return strCtDate;
	}

	/**
	 * @param strCtDate the strCtDate to set
	 */
	public void setStrCtDate(String strCtDate) {
		this.strCtDate = strCtDate;
	}

	/**
	 * @return the strWarnings
	 */
	public String getStrWarnings() {
		return strWarnings;
	}

	/**
	 * @param strWarnings the strWarnings to set
	 */
	public void setStrWarnings(String strWarnings) {
		this.strWarnings = strWarnings;
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
	 * @return the strChk
	 */
	public String getStrChk() {
		return strChk;
	}

	/**
	 * @param strChk the strChk to set
	 */
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	/**
	 * @return the strMsg
	 */
	public String getStrMsg() {
		return strMsg;
	}

	/**
	 * @param strMsg the strMsg to set
	 */
	public void setStrMsg(String strMsg) {
		this.strMsg = strMsg;
	}

	/**
	 * @return the strLastModifiedSeatId
	 */
	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}

	/**
	 * @param strLastModifiedSeatId the strLastModifiedSeatId to set
	 */
	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}

	
	
}
