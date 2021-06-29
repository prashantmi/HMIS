package bmed.masters.vo;

import java.io.Serializable;

import javax.sql.rowset.WebRowSet;

public class ServiceEngineerExpertiseMstVO implements Serializable {

	
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
	
	private static final long serialVersionUID = -5294462226999914611L;
	
	
	/* Process specific instance variables. */

	private boolean fExistStatus;
	private boolean fSaveSuccessfull;
	private String strCurrentDate;
	private String strEffectiveFrom;
	private String strErrMsg;
	private String strExpertiseName;
	private String strHospitalCode;
	private String strIsValid;
	private String strNormalMsg;
	private String strRecordStatusName;;
	private String strRemarks;
	private String strSeatId;
	private String[] arrStrSelectedExpertiseId;
	private String strSelectedExpertiseId;
	private String strServiceEngineerEmployeeId;
	private String strServiceEngineerName;
	private String strWarningMsg;
	private WebRowSet wrsAvailableExpertiseOptions;
	private WebRowSet wrsSelectedExpertiseOptions;
	
	
	/**
	 * @return the fExistStatus
	 */
	public boolean isfExistStatus() {
		return fExistStatus;
	}
	/**
	 * @param fExistStatus the fExistStatus to set
	 */
	public void setfExistStatus(boolean fExistStatus) {
		this.fExistStatus = fExistStatus;
	}
	/**
	 * @param fSaveSuccessfull the fSaveSuccessfull to set
	 */
	public void setfSaveSuccessfull(boolean fSaveSuccessfull) {
		this.fSaveSuccessfull = fSaveSuccessfull;
	}
	/**
	 * @return the fSaveSuccessfull
	 */
	public boolean isfSaveSuccessfull() {
		return fSaveSuccessfull;
	}
	/**
	 * @return the strCurrentDate
	 */
	public String getStrCurrentDate() {
		return strCurrentDate;
	}
	/**
	 * @param strCurrentDate the strCurrentDate to set
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
	 * @param strEffectiveFrom the strEffectiveFrom to set
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
	 * @param strErrMsg the strErrMsg to set
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
	 * @param strHospitalCode the strHospitalCode to set
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
	 * @param strIsValid the strIsValid to set
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
	 * @param strNormalMsg the strNormalMsg to set
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
	 * @param strRecordStatusName the strRecordStatusName to set
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
	 * @param strRemarks the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}
	/**
	 * @param strSeatId the strSeatId to set
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}
	/**
	 * @return the strSeatId
	 */
	public String getStrSeatId() {
		return strSeatId;
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
	public String getStrServiceEngineerEmployeeId() {
		return strServiceEngineerEmployeeId;
	}
	/**
	 * @param strServiceEngineerEmployeeId the strServiceEngineerEmployeeId to set
	 */
	public void setStrServiceEngineerEmployeeId(String strServiceEngineerEmployeeId) {
		this.strServiceEngineerEmployeeId = strServiceEngineerEmployeeId;
	}
	/**
	 * @return the strServiceEngineerName
	 */
	public String getStrServiceEngineerName() {
		return strServiceEngineerName;
	}
	/**
	 * @param strServiceEngineerName the strServiceEngineerName to set
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
	 * @param strWarningMsg the strWarningMsg to set
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
	}
	/**
	 * @return the strAvailableExpertiseOptions
	 */
	public WebRowSet getWrsAvailableExpertiseOptions() {
		return wrsAvailableExpertiseOptions;
	}
	/**
	 * @param strAvailableExpertiseOptions the strAvailableExpertiseOptions to set
	 */
	public void setWrsAvailableExpertiseOptions(
			WebRowSet wrsAvailableExpertiseOptions) {
		this.wrsAvailableExpertiseOptions = wrsAvailableExpertiseOptions;
	}
	/**
	 * @return the strSelectedExpertiseOptions
	 */
	public WebRowSet getWrsSelectedExpertiseOptions() {
		return wrsSelectedExpertiseOptions;
	}
	/**
	 * @param strSelectedExpertiseOptions the strSelectedExpertiseOptions to set
	 */
	public void setWrsSelectedExpertiseOptions(WebRowSet strSelectedExpertiseOptions) {
		this.wrsSelectedExpertiseOptions = strSelectedExpertiseOptions;
	}




}
