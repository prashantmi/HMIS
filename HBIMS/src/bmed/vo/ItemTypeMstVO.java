package bmed.vo;

import javax.sql.rowset.WebRowSet;

public class ItemTypeMstVO {
	private String strEnggItemTypeId;
	private String strEnggItemTypeName;
	private String strEntryDate;
	private String strHospitalCode;
	private String strIsValid;
	private String strRemarks;
	private WebRowSet wrsItemTypeComboOptions;

	/**
	 * @return the strEnggItemTypeId
	 */
	public String getStrEnggItemTypeId() {
		return strEnggItemTypeId;
	}

	/**
	 * @return the strEnggItemTypeName
	 */
	public String getStrEnggItemTypeName() {
		return strEnggItemTypeName;
	}

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
	 * @return the strRemarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * @return the wrsItemTypeComboOptions
	 */
	public WebRowSet getWrsItemTypeComboOptions() {
		return wrsItemTypeComboOptions;
	}

	/**
	 * @param strEnggItemTypeId
	 *            the strEnggItemTypeId to set
	 */
	public void setStrEnggItemTypeId(String strEnggItemTypeId) {
		this.strEnggItemTypeId = strEnggItemTypeId;
	}

	/**
	 * @param strEnggItemTypeName
	 *            the strEnggItemTypeName to set
	 */
	public void setStrEnggItemTypeName(String strEnggItemTypeName) {
		this.strEnggItemTypeName = strEnggItemTypeName;
	}

	/**
	 * @param strEntryDate
	 *            the strEntryDate to set
	 */
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	/**
	 * @param strHospitalCode
	 *            the strHospitalCode to set
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * @param strIsValid
	 *            the strIsValid to set
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * @param strRemarks
	 *            the strRemarks to set
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * @param wrsItemTypeComboOptions
	 *            the wrsItemTypeComboOptions to set
	 */
	public void setWrsItemTypeComboOptions(WebRowSet wrsItemTypeComboOptions) {
		this.wrsItemTypeComboOptions = wrsItemTypeComboOptions;
	}
}
