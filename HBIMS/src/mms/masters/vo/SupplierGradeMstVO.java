package mms.masters.vo;

import hisglobal.utility.TransferObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SupplierGradeMstVO.
 */
public class SupplierGradeMstVO implements TransferObject {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

	/** The str supplier grade id. */
	private String strSupplierGradeId = "0";
	
	/** The str grade name. */
	private String strGradeName = "";
	
	/** The str grade criteria. */
	private String strGradeCriteria = "";
	
	/** The str effective from. */
	private String strEffectiveFrom = "";
	
	/** The str remarks. */
	private String strRemarks = "";
	
	/** The str entry date. */
	private String strEntryDate = "";
	
	/** The str last modified date. */
	private String strLastModifiedDate = "";
	
	/** The str seat id. */
	private String strSeatId = "";
	
	/** The str last modified seat id. */
	private String strLastModifiedSeatId = "";
	
	/** The str is valid. */
	private String strIsValid = "1";
	
	/** The str hospital code. */
	private String strHospitalCode = "";
	
	/** The str chk. */
	private String strChk = "";

	/** The str msg type. */
	private String strMsgType = "";
	
	/** The str msg string. */
	private String strMsgString = "";

	/** The b exist status. */
	private Boolean bExistStatus = null;
	
	private String strSlNo;

	public String getStrSlNo() {
		return strSlNo;
	}

	public void setStrSlNo(String strSlNo) {
		this.strSlNo = strSlNo;
	}

	/**
	 * Gets the str supplier grade id.
	 * 
	 * @return the str supplier grade id
	 */
	public String getStrSupplierGradeId() {
		return strSupplierGradeId;
	}

	/**
	 * Sets the str supplier grade id.
	 * 
	 * @param strSupplierGradeId the new str supplier grade id
	 */
	public void setStrSupplierGradeId(String strSupplierGradeId) {
		this.strSupplierGradeId = strSupplierGradeId;
	}

	/**
	 * Gets the str grade name.
	 * 
	 * @return the str grade name
	 */
	public String getStrGradeName() {
		return strGradeName;
	}

	/**
	 * Sets the str grade name.
	 * 
	 * @param strGradeName the new str grade name
	 */
	public void setStrGradeName(String strGradeName) {
		this.strGradeName = strGradeName;
	}

	/**
	 * Gets the str grade criteria.
	 * 
	 * @return the str grade criteria
	 */
	public String getStrGradeCriteria() {
		return strGradeCriteria;
	}

	/**
	 * Sets the str grade criteria.
	 * 
	 * @param strGradeCriteria the new str grade criteria
	 */
	public void setStrGradeCriteria(String strGradeCriteria) {
		this.strGradeCriteria = strGradeCriteria;
	}

	/**
	 * Gets the str effective from.
	 * 
	 * @return the str effective from
	 */
	public String getStrEffectiveFrom() {
		return strEffectiveFrom;
	}

	/**
	 * Sets the str effective from.
	 * 
	 * @param strEffectiveFrom the new str effective from
	 */
	public void setStrEffectiveFrom(String strEffectiveFrom) {
		this.strEffectiveFrom = strEffectiveFrom;
	}

	/**
	 * Gets the str remarks.
	 * 
	 * @return the str remarks
	 */
	public String getStrRemarks() {
		return strRemarks;
	}

	/**
	 * Sets the str remarks.
	 * 
	 * @param strRemarks the new str remarks
	 */
	public void setStrRemarks(String strRemarks) {
		this.strRemarks = strRemarks;
	}

	/**
	 * Gets the str entry date.
	 * 
	 * @return the str entry date
	 */
	public String getStrEntryDate() {
		return strEntryDate;
	}

	/**
	 * Sets the str entry date.
	 * 
	 * @param strEntryDate the new str entry date
	 */
	public void setStrEntryDate(String strEntryDate) {
		this.strEntryDate = strEntryDate;
	}

	/**
	 * Gets the str last modified date.
	 * 
	 * @return the str last modified date
	 */
	public String getStrLastModifiedDate() {
		return strLastModifiedDate;
	}

	/**
	 * Sets the str last modified date.
	 * 
	 * @param strLastModifiedDate the new str last modified date
	 */
	public void setStrLastModifiedDate(String strLastModifiedDate) {
		this.strLastModifiedDate = strLastModifiedDate;
	}

	/**
	 * Gets the str seat id.
	 * 
	 * @return the str seat id
	 */
	public String getStrSeatId() {
		return strSeatId;
	}

	/**
	 * Sets the str seat id.
	 * 
	 * @param strSeatId the new str seat id
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
	}

	/**
	 * Gets the str last modified seat id.
	 * 
	 * @return the str last modified seat id
	 */
	public String getStrLastModifiedSeatId() {
		return strLastModifiedSeatId;
	}

	/**
	 * Sets the str last modified seat id.
	 * 
	 * @param strLastModifiedSeatId the new str last modified seat id
	 */
	public void setStrLastModifiedSeatId(String strLastModifiedSeatId) {
		this.strLastModifiedSeatId = strLastModifiedSeatId;
	}

	/**
	 * Gets the str is valid.
	 * 
	 * @return the str is valid
	 */
	public String getStrIsValid() {
		return strIsValid;
	}

	/**
	 * Sets the str is valid.
	 * 
	 * @param strIsValid the new str is valid
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * Gets the str hospital code.
	 * 
	 * @return the str hospital code
	 */
	public String getStrHospitalCode() {
		return strHospitalCode;
	}

	/**
	 * Sets the str hospital code.
	 * 
	 * @param strHospitalCode the new str hospital code
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/* (non-Javadoc)
	 * @see hisglobal.utility.TransferObject#getStrMsgString()
	 */
	public String getStrMsgString() {
		return strMsgString;
	}

	/* (non-Javadoc)
	 * @see hisglobal.utility.TransferObject#setStrMsgString(java.lang.String)
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	/* (non-Javadoc)
	 * @see hisglobal.utility.TransferObject#getStrMsgType()
	 */
	public String getStrMsgType() {
		return strMsgType;
	}

	/* (non-Javadoc)
	 * @see hisglobal.utility.TransferObject#setStrMsgType(java.lang.String)
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
	}

	/**
	 * Gets the str chk.
	 * 
	 * @return the str chk
	 */
	public String getStrChk() {
		return strChk;
	}

	/**
	 * Sets the str chk.
	 * 
	 * @param strChk the new str chk
	 */
	public void setStrChk(String strChk) {
		this.strChk = strChk;
	}

	/**
	 * Gets the b exist status.
	 * 
	 * @return the b exist status
	 */
	public Boolean getBExistStatus() {
		return bExistStatus;
	}

	/**
	 * Sets the b exist status.
	 * 
	 * @param existStatus the new b exist status
	 */
	public void setBExistStatus(Boolean existStatus) {
		bExistStatus = existStatus;
	}

}
