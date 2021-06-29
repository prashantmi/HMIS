package bmed.masters.controller.fb;



import hisglobal.masterutil.GenericFormBean;

public class ServiceEngineerExpertiseMstFB extends GenericFormBean {

	/**
	 * The serialization runtime associates with each serializable class a
	 * version number, called a serialVersionUID, which is used during
	 * deserialization to verify that the sender and receiver of a serialized
	 * object have loaded classes for that object that are compatible with
	 * respect to serialization. If the receiver has loaded a class for the
	 * object that has a different serialVersionUID than that of the
	 * corresponding sender's class, then deserialization will result in an
	 * InvalidClassException. A serializable class can declare its own
	 * serialVersionUID explicitly by declaring a field named "serialVersionUID"
	 * that must be static, final, and of type long.
	 */
	private static final long serialVersionUID = -4147546145971143353L;

	/* Process specific instance variables. */

	private String strAvailableExpertiseOptions;
	private String strCurrentDate;
	private String strEffectiveFrom;
	private String strErrMsg;
	private String strExpertiseName;
	private String strHospitalCode;
	private String strIsValid;
	private String strNormalMsg;
	private String strRecordStatusName;;
	private String strRemarks;
	private String[] arrStrSelectedExpertiseId;
	private String strSelectedExpertiseId;
	private String strSelectedExpertiseOptions;
	private String strServiceEngineerEmployeeId;
	private String strServiceEngineerName;
	private String strWarningMsg;

	/**
	 * @return the strAvailableExpertiseOptions
	 */
	public String getStrAvailableExpertiseOptions() {
		return strAvailableExpertiseOptions;
	}

	/**
	 * @param strAvailableExpertiseOptions
	 *            the strAvailableExpertiseOptions to set
	 */
	public void setStrAvailableExpertiseOptions(
			String strAvailableExpertiseOptions) {
		this.strAvailableExpertiseOptions = strAvailableExpertiseOptions;
	}

	/**
	 * @return the strCurrentDate
	 */
	public String getStrCurrentDate() {
		return strCurrentDate;
	}

	/**
	 * @param strCurrentDate
	 *            the strCurrentDate to set
	 */
	public void setStrCurrentDate(String strCurrentDate) {
		this.strCurrentDate = strCurrentDate;
	}

	/**
	 * @return the strEffectiveFrom
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	/**
	 * @param strEffectiveFrom
	 *            the strEffectiveFrom to set
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * @return the strErrMsg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}

	/**
	 * @param strErrMsg
	 *            the strErrMsg to set
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	/**
	 * @param strExpertiseName the strExpertiseName to set
	 */
	public void setStrExpertiseName(String strExpertiseName) {
		this.strExpertiseName = strExpertiseName;
	}

	/**
	 * @return the strExpertiseName
	 */
	public String getStrExpertiseName() {
		return strExpertiseName;
	}

	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * @param strHospitalCode
	 *            the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}

	/**
	 * @param strIsValid
	 *            the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * @return the strNormalMsg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}

	/**
	 * @param strNormalMsg
	 *            the strNormalMsg to set
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}

	/**
	 * @return the strRecordStatusName
	 */
	public String getStrRecordStatusName() {
		return strRecordStatusName;
	}

	/**
	 * @param strRecordStatusName
	 *            the strRecordStatusName to set
	 */
	public void setStrRecordStatusName(String strRecordStatusName) {
		this.strRecordStatusName = strRecordStatusName;
	}

	/**
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * @param strRemarks
	 *            the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	
	/**
	 * @param arrStrSelectedExpertiseId the arrStrSelectedExpertiseId to set
	 */
	public void setArrStrSelectedExpertiseId(String[] arrStrSelectedExpertiseId) {
		this.arrStrSelectedExpertiseId = arrStrSelectedExpertiseId;
	}

	/**
	 * @return the arrStrSelectedExpertiseId
	 */
	public String[] getArrStrSelectedExpertiseId() {
		return arrStrSelectedExpertiseId;
	}

	/**
	 * @param strSelectedExpertiseId the strSelectedExpertiseId to set
	 */
	public void setStrSelectedExpertiseId(String strSelectedExpertiseId) {
		this.strSelectedExpertiseId = strSelectedExpertiseId;
	}

	/**
	 * @return the strSelectedExpertiseId
	 */
	public String getStrSelectedExpertiseId() {
		return strSelectedExpertiseId;
	}

	/**
	 * @return the strSelectedExpertiseOptions
	 */
	public String getStrSelectedExpertiseOptions() {
		return strSelectedExpertiseOptions;
	}

	/**
	 * @param strSelectedExpertiseOptions
	 *            the strSelectedExpertiseOptions to set
	 */
	public void setStrSelectedExpertiseOptions(
			String strSelectedExpertiseOptions) {
		this.strSelectedExpertiseOptions = strSelectedExpertiseOptions;
	}

	/**
	 * @return the strServiceEngineerEmployeeId
	 */
	public String getStrServiceEngineerEmployeeId() {
		return strServiceEngineerEmployeeId;
	}

	/**
	 * @param strServiceEngineerEmployeeId
	 *            the strServiceEngineerEmployeeId to set
	 */
	public void setStrServiceEngineerEmployeeId(
			String strServiceEngineerEmployeeId) {
		this.strServiceEngineerEmployeeId = strServiceEngineerEmployeeId;
	}

	/**
	 * @return the strServiceEngineerName
	 */
	public String getStrServiceEngineerName() {
		return strServiceEngineerName;
	}

	/**
	 * @param strServiceEngineerName
	 *            the strServiceEngineerName to set
	 */
	public void setStrServiceEngineerName(String strServiceEngineerName) {
		this.strServiceEngineerName = strServiceEngineerName;
	}

	/**
	 * @return the strWarningMsg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	/**
	 * @param strWarningMsg
	 *            the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}

}
