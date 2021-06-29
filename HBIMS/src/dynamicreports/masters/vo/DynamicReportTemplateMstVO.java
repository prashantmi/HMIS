/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportTemplateMstVO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.vo;

import javax.sql.rowset.WebRowSet;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportTemplateMstVO.
 */
public class DynamicReportTemplateMstVO {

	/** The str msg string. */
	private String strMsgString = "";

	/** The str msg type. */
	private String strMsgType = "";

	/** The b exist status. */
	private boolean bExistStatus = false;

	/** The str seat id. */
	private String strSeatId = "";

	/** The str hospital code. */
	private String strHospitalCode = "";

	/** The str is valid. */
	private String strIsValid = "";

	/** The str report type id. */
	private String strReportTypeId = "";

	/** The str report template id. */
	private String strReportTemplateId = "";

	/** The str report name. */
	private String strReportName = "";

	/** The str display name. */
	private String strDisplayName = "";

	/** The str report width. */
	private String strReportWidth = "";

	/** The str report width unit. */
	private String strReportWidthUnit = "";

	/** The str report header type. */
	private String strReportHeaderType = "";

	/** The ws report header types. */
	private WebRowSet wsReportHeaderTypes = null;

	/** The str is border. */
	private String strIsBorder = "";

	/**
	 * Gets the ws report header types.
	 * 
	 * @return the ws report header types
	 */
	public WebRowSet getWsReportHeaderTypes() {
		return wsReportHeaderTypes;
	}

	/**
	 * Sets the ws report header types.
	 * 
	 * @param wsReportHeaderTypes
	 *            the new ws report header types
	 */
	public void setWsReportHeaderTypes(WebRowSet wsReportHeaderTypes) {
		this.wsReportHeaderTypes = wsReportHeaderTypes;
	}

	/**
	 * Gets the str report header type.
	 * 
	 * @return the str report header type
	 */
	public String getStrReportHeaderType() {
		return strReportHeaderType;
	}

	/**
	 * Sets the str report header type.
	 * 
	 * @param strReportHeaderType
	 *            the new str report header type
	 */
	public void setStrReportHeaderType(String strReportHeaderType) {
		this.strReportHeaderType = strReportHeaderType;
	}

	/**
	 * Gets the str is border.
	 * 
	 * @return the str is border
	 */
	public String getStrIsBorder() {
		return strIsBorder;
	}

	/**
	 * Sets the str is border.
	 * 
	 * @param strIsBorder
	 *            the new str is border
	 */
	public void setStrIsBorder(String strIsBorder) {
		this.strIsBorder = strIsBorder;
	}

	/**
	 * Gets the str report template id.
	 * 
	 * @return the str report template id
	 */
	public String getStrReportTemplateId() {
		return strReportTemplateId;
	}

	/**
	 * Sets the str report template id.
	 * 
	 * @param strReportTemplateId
	 *            the new str report template id
	 */
	public void setStrReportTemplateId(String strReportTemplateId) {
		this.strReportTemplateId = strReportTemplateId;
	}

	/**
	 * Checks if is b exist status.
	 * 
	 * @return true, if is b exist status
	 */
	public boolean isbExistStatus() {
		return bExistStatus;
	}

	/**
	 * Sets the b exist status.
	 * 
	 * @param bExistStatus
	 *            the new b exist status
	 */
	public void setbExistStatus(boolean bExistStatus) {
		this.bExistStatus = bExistStatus;
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
	 * @param strIsValid
	 *            the new str is valid
	 */
	public void setStrIsValid(String strIsValid) {
		this.strIsValid = strIsValid;
	}

	/**
	 * Gets the str msg string.
	 * 
	 * @return the str msg string
	 */
	public String getStrMsgString() {
		return strMsgString;
	}

	/**
	 * Sets the str msg string.
	 * 
	 * @param strMsgString
	 *            the new str msg string
	 */
	public void setStrMsgString(String strMsgString) {
		this.strMsgString = strMsgString;
	}

	/**
	 * Gets the str msg type.
	 * 
	 * @return the str msg type
	 */
	public String getStrMsgType() {
		return strMsgType;
	}

	/**
	 * Sets the str msg type.
	 * 
	 * @param strMsgType
	 *            the new str msg type
	 */
	public void setStrMsgType(String strMsgType) {
		this.strMsgType = strMsgType;
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
	 * @param strSeatId
	 *            the new str seat id
	 */
	public void setStrSeatId(String strSeatId) {
		this.strSeatId = strSeatId;
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
	 * @param strHospitalCode
	 *            the new str hospital code
	 */
	public void setStrHospitalCode(String strHospitalCode) {
		this.strHospitalCode = strHospitalCode;
	}

	/**
	 * Gets the str report type id.
	 * 
	 * @return the str report type id
	 */
	public String getStrReportTypeId() {
		return strReportTypeId;
	}

	/**
	 * Sets the str report type id.
	 * 
	 * @param strReportTypeId
	 *            the new str report type id
	 */
	public void setStrReportTypeId(String strReportTypeId) {
		this.strReportTypeId = strReportTypeId;
	}

	/**
	 * Gets the str report name.
	 * 
	 * @return the str report name
	 */
	public String getStrReportName() {
		return strReportName;
	}

	/**
	 * Sets the str report name.
	 * 
	 * @param strReportName
	 *            the new str report name
	 */
	public void setStrReportName(String strReportName) {
		this.strReportName = strReportName;
	}

	/**
	 * Gets the str display name.
	 * 
	 * @return the str display name
	 */
	public String getStrDisplayName() {
		return strDisplayName;
	}

	/**
	 * Sets the str display name.
	 * 
	 * @param strDisplayName
	 *            the new str display name
	 */
	public void setStrDisplayName(String strDisplayName) {
		this.strDisplayName = strDisplayName;
	}

	/**
	 * Gets the str report width.
	 * 
	 * @return the str report width
	 */
	public String getStrReportWidth() {
		return strReportWidth;
	}

	/**
	 * Sets the str report width.
	 * 
	 * @param strReportWidth
	 *            the new str report width
	 */
	public void setStrReportWidth(String strReportWidth) {
		this.strReportWidth = strReportWidth;
	}

	/**
	 * Gets the str report width unit.
	 * 
	 * @return the str report width unit
	 */
	public String getStrReportWidthUnit() {
		return strReportWidthUnit;
	}

	/**
	 * Sets the str report width unit.
	 * 
	 * @param strReportWidthUnit
	 *            the new str report width unit
	 */
	public void setStrReportWidthUnit(String strReportWidthUnit) {
		this.strReportWidthUnit = strReportWidthUnit;
	}

}
