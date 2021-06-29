package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class SparePartStatusMstVO {

	private String strEntryDate;
	private String strHospitalCode;
	private String strIsValid;
	private String strMode;
	private String strStatusId;
	private String strStatusName;
	private WebRowSet wrsData;
	/**
	 * @return the strEntryDate
	 */
	public String getStrEntryDate() {
		return strEntryDate;
	}
	/**
	 * @return the strHospitalCode
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}
	/**
	 * @return the strIsValid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}
	/**
	 * @return the strMode
	 */
	public String getStrMode() {
		return strMode;
	}
	/**
	 * @return the strStatusId
	 */
	public String getStrStatusId() {
		return strStatusId;
	}
	/**
	 * @return the strStatusName
	 */
	public String getStrStatusName() {
		return strStatusName;
	}
	/**
	 * @return the wrsData
	 */
	public WebRowSet getWrsData() {
		return wrsData;
	}
	/**
	 * @param strEntryDate the strEntryDate to set
	 */
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}
	/**
	 * @param strHospitalCode the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}
	/**
	 * @param strIsValid the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}
	/**
	 * @param strMode the strMode to set
	 */
	public void setStrMode(String strMode) {
		this.strMode = strMode;
	}
	/**
	 * @param strStatusId the strStatusId to set
	 */
	public void setStrStatusId(String strStatusId) {
		this.strStatusId = strStatusId;
	}
	/**
	 * @param strStatusName the strStatusName to set
	 */
	public void setStrStatusName(String strStatusName) {
		this.strStatusName = strStatusName;
	}
	/**
	 * @param wrsData the wrsData to set
	 */
	public void setWrsData(WebRowSet wrsData) {
		this.wrsData = wrsData;
	}

}
