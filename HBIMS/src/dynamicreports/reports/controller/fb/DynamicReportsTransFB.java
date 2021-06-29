/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportsTransFB.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.reports.controller.fb;

import org.apache.struts.action.ActionForm;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportsTransFB.
 */
public class DynamicReportsTransFB extends ActionForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The str normal msg. */
	private String strNormalMsg = "";

	/** The str err msg. */
	private String strErrMsg = "";

	/** The str warning msg. */
	private String strWarningMsg = "";

	/** The str seat id. */
	private String strSeatId = "";

	/** The str hospital code. */
	private String strHospitalCode = "";

	/** The str report hidden id. */
	private String strReportHiddenId = "";

	/** The str report type id. */
	private String strReportTypeId = "0";

	/** The str report type name. */
	private String strReportTypeName = "";

	/** The str report template values. */
	private String strReportTemplateValues = "";

	/** The str report template id. */
	private String strReportTemplateId = "0";

	/** The str report display name. */
	private String strReportDisplayName = "";

	/** The str report width. */
	private String strReportWidth = "";

	/** The str report width unit. */
	private String strReportWidthUnit = "";

	/** The str report border req. */
	private String strReportBorderReq = "";

	/** The str ct combo. */
	private String strCtCombo = "";

	/** The str param id. */
	private String strParamId = "";

	/** The str combo values. */
	private String strComboValues = "";

	/** The str template proc display name. */
	private String[] strTemplateProcDisplayName = null;

	/** The str template proc is combo depend. */
	private String[] strTemplateProcIsComboDepend = null;

	/** The str template proc name. */
	private String[] strTemplateProcName = null;

	/** The str template proc is last. */
	private String[] strTemplateProcIsLast = null;

	/** The str template proc is col base. */
	private String[] strTemplateProcIsColBase = null;

	/** The str template proc id. */
	private String[] strTemplateProcId = null;

	/** The str report header type id. */
	private String[] strReportHeaderTypeId = null;

	/** The str report header param req. */
	private String[] strReportHeaderParamReq = null;

	/** The str report header param type. */
	private String[] strReportHeaderParamType = null;

	/** The str report header param id. */
	private String[] strReportHeaderParamId = null;

	/** The str in param name. */
	private String[] strInParamName = null;

	/** The str in param display name. */
	private String[] strInParamDisplayName = null;

	/** The str in param display value. */
	private String[] strInParamDisplayValue = null;

	/** The str in param comp type. */
	private String[] strInParamCompType = null;

	/** The str in constant value. */
	private String[] strInConstantValue = null;

	/** The str in comp type. */
	private String[] strInCompType = null;

	/** The str in value details. */
	private String[] strInValueDetails = null;

	/** The str in param combo val. */
	private String[] strInParamComboVal = null;

	/** The str out col display name. */
	private String[] strOutColDisplayName = null;

	/** The str out col width. */
	private String[] strOutColWidth = null;

	/** The str out col is group by. */
	private String[] strOutColIsGroupBy = null;

	/** The str out col is order by. */
	private String[] strOutColIsOrderBy = null;

	/** The str out col is grand total. */
	private String[] strOutColIsGrandTotal = null;

	/** The str out col index. */
	private String[] strOutColIndex = null;

	/** The str out col actual index. */
	private String[] strOutColActualIndex = null;

	/** The str out col is hyper link. */
	private String[] strOutColIsHyperLink = null;

	/** The str out col is hyper value. */
	private String[] strOutColIsHyperValue = null;

	/** The str out column align. */
	private String[] strOutColumnAlign = null;

	/** The str column display name. */
	private String[] strColumnDisplayName = null;

	/** The str column prifix. */
	private String[] strColumnPrifix = null;

	/** The str column formula. */
	private String[] strColumnFormula = null;

	/** The str column suffix. */
	private String[] strColumnSuffix = null;

	/** The str export type. */
	private String strExportType = "";

	/** The str is footer. */
	private String strIsFooter = "0";

	/** The str user remarks. */
	private String strUserRemarks = "";

	/** The str order string. */
	private String strOrderString = "";

	/** The str level. */
	private String strLevel = "0";

	/** The n current level. */
	private int nCurrentLevel = 0;

	/** The str report contents. */
	private String strReportContents = "";

	/** The str out pram values hidden. */
	private String strOutPramValuesHidden = "";

	/** The str in param hidden values. */
	private String strInParamHiddenValues = "";

	/** The str in param id hidden values. */
	private String strInParamIdHiddenValues = "";

	/** The str report header html content. */
	private String strReportHeaderHtmlContent = "";

	/** The str header. */
	private String[] strHeader = null;

	/**
	 * Gets the str report hidden id.
	 * 
	 * @return the str report hidden id
	 */
	public String getStrReportHiddenId() {
		return strReportHiddenId;
	}

	/**
	 * Sets the str report hidden id.
	 * 
	 * @param strReportHiddenId
	 *            the new str report hidden id
	 */
	public void setStrReportHiddenId(String strReportHiddenId) {
		this.strReportHiddenId = strReportHiddenId;
	}

	/**
	 * Gets the str header.
	 * 
	 * @return the str header
	 */
	public String[] getStrHeader() {
		return strHeader;
	}

	/**
	 * Sets the str header.
	 * 
	 * @param strHeader
	 *            the new str header
	 */
	public void setStrHeader(String[] strHeader) {
		this.strHeader = strHeader;
	}

	/**
	 * Gets the str report header html content.
	 * 
	 * @return the str report header html content
	 */
	public String getStrReportHeaderHtmlContent() {
		return strReportHeaderHtmlContent;
	}

	/**
	 * Sets the str report header html content.
	 * 
	 * @param strReportHeaderHtmlContent
	 *            the new str report header html content
	 */
	public void setStrReportHeaderHtmlContent(String strReportHeaderHtmlContent) {
		this.strReportHeaderHtmlContent = strReportHeaderHtmlContent;
	}

	/**
	 * Gets the str report header type id.
	 * 
	 * @return the str report header type id
	 */
	public String[] getStrReportHeaderTypeId() {
		return strReportHeaderTypeId;
	}

	/**
	 * Sets the str report header type id.
	 * 
	 * @param strReportHeaderTypeId
	 *            the new str report header type id
	 */
	public void setStrReportHeaderTypeId(String[] strReportHeaderTypeId) {
		this.strReportHeaderTypeId = strReportHeaderTypeId;
	}

	/**
	 * Gets the str report header param req.
	 * 
	 * @return the str report header param req
	 */
	public String[] getStrReportHeaderParamReq() {
		return strReportHeaderParamReq;
	}

	/**
	 * Sets the str report header param req.
	 * 
	 * @param strReportHeaderParamReq
	 *            the new str report header param req
	 */
	public void setStrReportHeaderParamReq(String[] strReportHeaderParamReq) {
		this.strReportHeaderParamReq = strReportHeaderParamReq;
	}

	/**
	 * Gets the str report header param type.
	 * 
	 * @return the str report header param type
	 */
	public String[] getStrReportHeaderParamType() {
		return strReportHeaderParamType;
	}

	/**
	 * Sets the str report header param type.
	 * 
	 * @param strReportHeaderParamType
	 *            the new str report header param type
	 */
	public void setStrReportHeaderParamType(String[] strReportHeaderParamType) {
		this.strReportHeaderParamType = strReportHeaderParamType;
	}

	/**
	 * Gets the str report header param id.
	 * 
	 * @return the str report header param id
	 */
	public String[] getStrReportHeaderParamId() {
		return strReportHeaderParamId;
	}

	/**
	 * Sets the str report header param id.
	 * 
	 * @param strReportHeaderParamId
	 *            the new str report header param id
	 */
	public void setStrReportHeaderParamId(String[] strReportHeaderParamId) {
		this.strReportHeaderParamId = strReportHeaderParamId;
	}

	/**
	 * Gets the str out column align.
	 * 
	 * @return the str out column align
	 */
	public String[] getStrOutColumnAlign() {
		return strOutColumnAlign;
	}

	/**
	 * Sets the str out column align.
	 * 
	 * @param strOutColumnAlign
	 *            the new str out column align
	 */
	public void setStrOutColumnAlign(String[] strOutColumnAlign) {
		this.strOutColumnAlign = strOutColumnAlign;
	}

	/**
	 * Gets the str in param id hidden values.
	 * 
	 * @return the str in param id hidden values
	 */
	public String getStrInParamIdHiddenValues() {
		return strInParamIdHiddenValues;
	}

	/**
	 * Sets the str in param id hidden values.
	 * 
	 * @param strInParamIdHiddenValues
	 *            the new str in param id hidden values
	 */
	public void setStrInParamIdHiddenValues(String strInParamIdHiddenValues) {
		this.strInParamIdHiddenValues = strInParamIdHiddenValues;
	}

	/**
	 * Gets the str in param hidden values.
	 * 
	 * @return the str in param hidden values
	 */
	public String getStrInParamHiddenValues() {
		return strInParamHiddenValues;
	}

	/**
	 * Sets the str in param hidden values.
	 * 
	 * @param strInParamHiddenValues
	 *            the new str in param hidden values
	 */
	public void setStrInParamHiddenValues(String strInParamHiddenValues) {
		this.strInParamHiddenValues = strInParamHiddenValues;
	}

	/**
	 * Gets the str level.
	 * 
	 * @return the str level
	 */
	public String getStrLevel() {
		return strLevel;
	}

	/**
	 * Sets the str level.
	 * 
	 * @param strLevel
	 *            the new str level
	 */
	public void setStrLevel(String strLevel) {
		this.strLevel = strLevel;
	}

	/**
	 * Gets the str out pram values hidden.
	 * 
	 * @return the str out pram values hidden
	 */
	public String getStrOutPramValuesHidden() {
		return strOutPramValuesHidden;
	}

	/**
	 * Sets the str out pram values hidden.
	 * 
	 * @param strOutPramValuesHidden
	 *            the new str out pram values hidden
	 */
	public void setStrOutPramValuesHidden(String strOutPramValuesHidden) {
		this.strOutPramValuesHidden = strOutPramValuesHidden;
	}

	/**
	 * Gets the str out col is hyper value.
	 * 
	 * @return the str out col is hyper value
	 */
	public String[] getStrOutColIsHyperValue() {
		return strOutColIsHyperValue;
	}

	/**
	 * Sets the str out col is hyper value.
	 * 
	 * @param strOutColIsHyperValue
	 *            the new str out col is hyper value
	 */
	public void setStrOutColIsHyperValue(String[] strOutColIsHyperValue) {
		this.strOutColIsHyperValue = strOutColIsHyperValue;
	}

	/**
	 * Gets the str report template values.
	 * 
	 * @return the str report template values
	 */
	public String getStrReportTemplateValues() {
		return strReportTemplateValues;
	}

	/**
	 * Sets the str report template values.
	 * 
	 * @param strReportTemplateValues
	 *            the new str report template values
	 */
	public void setStrReportTemplateValues(String strReportTemplateValues) {
		this.strReportTemplateValues = strReportTemplateValues;
	}

	/**
	 * Gets the str report contents.
	 * 
	 * @return the str report contents
	 */
	public String getStrReportContents() {
		return strReportContents;
	}

	/**
	 * Sets the str report contents.
	 * 
	 * @param strReportContents
	 *            the new str report contents
	 */
	public void setStrReportContents(String strReportContents) {
		this.strReportContents = strReportContents;
	}

	/**
	 * Gets the str template proc id.
	 * 
	 * @return the str template proc id
	 */
	public String[] getStrTemplateProcId() {
		return strTemplateProcId;
	}

	/**
	 * Sets the str template proc id.
	 * 
	 * @param strTemplateProcId
	 *            the new str template proc id
	 */
	public void setStrTemplateProcId(String[] strTemplateProcId) {
		this.strTemplateProcId = strTemplateProcId;
	}

	/**
	 * Gets the n current level.
	 * 
	 * @return the n current level
	 */
	public int getnCurrentLevel() {
		return nCurrentLevel;
	}

	/**
	 * Sets the n current level.
	 * 
	 * @param nCurrentLevel
	 *            the new n current level
	 */
	public void setnCurrentLevel(int nCurrentLevel) {
		this.nCurrentLevel = nCurrentLevel;
	}

	/**
	 * Gets the str template proc display name.
	 * 
	 * @return the str template proc display name
	 */
	public String[] getStrTemplateProcDisplayName() {
		return strTemplateProcDisplayName;
	}

	/**
	 * Sets the str template proc display name.
	 * 
	 * @param strTemplateProcDisplayName
	 *            the new str template proc display name
	 */
	public void setStrTemplateProcDisplayName(
			String[] strTemplateProcDisplayName) {
		this.strTemplateProcDisplayName = strTemplateProcDisplayName;
	}

	/**
	 * Gets the str template proc is combo depend.
	 * 
	 * @return the str template proc is combo depend
	 */
	public String[] getStrTemplateProcIsComboDepend() {
		return strTemplateProcIsComboDepend;
	}

	/**
	 * Sets the str template proc is combo depend.
	 * 
	 * @param strTemplateProcIsComboDepend
	 *            the new str template proc is combo depend
	 */
	public void setStrTemplateProcIsComboDepend(
			String[] strTemplateProcIsComboDepend) {
		this.strTemplateProcIsComboDepend = strTemplateProcIsComboDepend;
	}

	/**
	 * Gets the str template proc name.
	 * 
	 * @return the str template proc name
	 */
	public String[] getStrTemplateProcName() {
		return strTemplateProcName;
	}

	/**
	 * Sets the str template proc name.
	 * 
	 * @param strTemplateProcName
	 *            the new str template proc name
	 */
	public void setStrTemplateProcName(String[] strTemplateProcName) {
		this.strTemplateProcName = strTemplateProcName;
	}

	/**
	 * Gets the str template proc is last.
	 * 
	 * @return the str template proc is last
	 */
	public String[] getStrTemplateProcIsLast() {
		return strTemplateProcIsLast;
	}

	/**
	 * Sets the str template proc is last.
	 * 
	 * @param strTemplateProcIsLast
	 *            the new str template proc is last
	 */
	public void setStrTemplateProcIsLast(String[] strTemplateProcIsLast) {
		this.strTemplateProcIsLast = strTemplateProcIsLast;
	}

	/**
	 * Gets the str template proc is col base.
	 * 
	 * @return the str template proc is col base
	 */
	public String[] getStrTemplateProcIsColBase() {
		return strTemplateProcIsColBase;
	}

	/**
	 * Sets the str template proc is col base.
	 * 
	 * @param strTemplateProcIsColBase
	 *            the new str template proc is col base
	 */
	public void setStrTemplateProcIsColBase(String[] strTemplateProcIsColBase) {
		this.strTemplateProcIsColBase = strTemplateProcIsColBase;
	}

	/**
	 * Gets the str column display name.
	 * 
	 * @return the str column display name
	 */
	public String[] getStrColumnDisplayName() {
		return strColumnDisplayName;
	}

	/**
	 * Sets the str column display name.
	 * 
	 * @param strColumnDisplayName
	 *            the new str column display name
	 */
	public void setStrColumnDisplayName(String[] strColumnDisplayName) {
		this.strColumnDisplayName = strColumnDisplayName;
	}

	/**
	 * Gets the str column prifix.
	 * 
	 * @return the str column prifix
	 */
	public String[] getStrColumnPrifix() {
		return strColumnPrifix;
	}

	/**
	 * Sets the str column prifix.
	 * 
	 * @param strColumnPrifix
	 *            the new str column prifix
	 */
	public void setStrColumnPrifix(String[] strColumnPrifix) {
		this.strColumnPrifix = strColumnPrifix;
	}

	/**
	 * Gets the str column formula.
	 * 
	 * @return the str column formula
	 */
	public String[] getStrColumnFormula() {
		return strColumnFormula;
	}

	/**
	 * Sets the str column formula.
	 * 
	 * @param strColumnFormula
	 *            the new str column formula
	 */
	public void setStrColumnFormula(String[] strColumnFormula) {
		this.strColumnFormula = strColumnFormula;
	}

	/**
	 * Gets the str column suffix.
	 * 
	 * @return the str column suffix
	 */
	public String[] getStrColumnSuffix() {
		return strColumnSuffix;
	}

	/**
	 * Sets the str column suffix.
	 * 
	 * @param strColumnSuffix
	 *            the new str column suffix
	 */
	public void setStrColumnSuffix(String[] strColumnSuffix) {
		this.strColumnSuffix = strColumnSuffix;
	}

	/**
	 * Gets the str report display name.
	 * 
	 * @return the str report display name
	 */
	public String getStrReportDisplayName() {
		return strReportDisplayName;
	}

	/**
	 * Sets the str report display name.
	 * 
	 * @param strReportDisplayName
	 *            the new str report display name
	 */
	public void setStrReportDisplayName(String strReportDisplayName) {
		this.strReportDisplayName = strReportDisplayName;
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

	/**
	 * Gets the str report border req.
	 * 
	 * @return the str report border req
	 */
	public String getStrReportBorderReq() {
		return strReportBorderReq;
	}

	/**
	 * Sets the str report border req.
	 * 
	 * @param strReportBorderReq
	 *            the new str report border req
	 */
	public void setStrReportBorderReq(String strReportBorderReq) {
		this.strReportBorderReq = strReportBorderReq;
	}

	/**
	 * Gets the str in param comp type.
	 * 
	 * @return the str in param comp type
	 */
	public String[] getStrInParamCompType() {
		return strInParamCompType;
	}

	/**
	 * Sets the str in param comp type.
	 * 
	 * @param strInParamCompType
	 *            the new str in param comp type
	 */
	public void setStrInParamCompType(String[] strInParamCompType) {
		this.strInParamCompType = strInParamCompType;
	}

	/**
	 * Gets the str in param display value.
	 * 
	 * @return the str in param display value
	 */
	public String[] getStrInParamDisplayValue() {
		return strInParamDisplayValue;
	}

	/**
	 * Sets the str in param display value.
	 * 
	 * @param strInParamDisplayValue
	 *            the new str in param display value
	 */
	public void setStrInParamDisplayValue(String[] strInParamDisplayValue) {
		this.strInParamDisplayValue = strInParamDisplayValue;
	}

	/**
	 * Gets the str in param combo val.
	 * 
	 * @return the str in param combo val
	 */
	public String[] getStrInParamComboVal() {
		return strInParamComboVal;
	}

	/**
	 * Sets the str in param combo val.
	 * 
	 * @param strInParamComboVal
	 *            the new str in param combo val
	 */
	public void setStrInParamComboVal(String[] strInParamComboVal) {
		this.strInParamComboVal = strInParamComboVal;
	}

	/**
	 * Gets the str in param display name.
	 * 
	 * @return the str in param display name
	 */
	public String[] getStrInParamDisplayName() {
		return strInParamDisplayName;
	}

	/**
	 * Sets the str in param display name.
	 * 
	 * @param strInParamDisplayName
	 *            the new str in param display name
	 */
	public void setStrInParamDisplayName(String[] strInParamDisplayName) {
		this.strInParamDisplayName = strInParamDisplayName;
	}

	/**
	 * Gets the str order string.
	 * 
	 * @return the str order string
	 */
	public String getStrOrderString() {
		return strOrderString;
	}

	/**
	 * Sets the str order string.
	 * 
	 * @param strOrderString
	 *            the new str order string
	 */
	public void setStrOrderString(String strOrderString) {
		this.strOrderString = strOrderString;
	}

	/**
	 * Gets the str export type.
	 * 
	 * @return the str export type
	 */
	public String getStrExportType() {
		return strExportType;
	}

	/**
	 * Sets the str export type.
	 * 
	 * @param strExportType
	 *            the new str export type
	 */
	public void setStrExportType(String strExportType) {
		this.strExportType = strExportType;
	}

	/**
	 * Gets the str is footer.
	 * 
	 * @return the str is footer
	 */
	public String getStrIsFooter() {
		return strIsFooter;
	}

	/**
	 * Sets the str is footer.
	 * 
	 * @param strIsFooter
	 *            the new str is footer
	 */
	public void setStrIsFooter(String strIsFooter) {
		this.strIsFooter = strIsFooter;
	}

	/**
	 * Gets the str user remarks.
	 * 
	 * @return the str user remarks
	 */
	public String getStrUserRemarks() {
		return strUserRemarks;
	}

	/**
	 * Sets the str user remarks.
	 * 
	 * @param strUserRemarks
	 *            the new str user remarks
	 */
	public void setStrUserRemarks(String strUserRemarks) {
		this.strUserRemarks = strUserRemarks;
	}

	/**
	 * Gets the str in param name.
	 * 
	 * @return the str in param name
	 */
	public String[] getStrInParamName() {
		return strInParamName;
	}

	/**
	 * Sets the str in param name.
	 * 
	 * @param strInParamName
	 *            the new str in param name
	 */
	public void setStrInParamName(String[] strInParamName) {
		this.strInParamName = strInParamName;
	}

	/**
	 * Gets the str in constant value.
	 * 
	 * @return the str in constant value
	 */
	public String[] getStrInConstantValue() {
		return strInConstantValue;
	}

	/**
	 * Sets the str in constant value.
	 * 
	 * @param strInConstantValue
	 *            the new str in constant value
	 */
	public void setStrInConstantValue(String[] strInConstantValue) {
		this.strInConstantValue = strInConstantValue;
	}

	/**
	 * Gets the str in comp type.
	 * 
	 * @return the str in comp type
	 */
	public String[] getStrInCompType() {
		return strInCompType;
	}

	/**
	 * Sets the str in comp type.
	 * 
	 * @param strInCompType
	 *            the new str in comp type
	 */
	public void setStrInCompType(String[] strInCompType) {
		this.strInCompType = strInCompType;
	}

	/**
	 * Gets the str in value details.
	 * 
	 * @return the str in value details
	 */
	public String[] getStrInValueDetails() {
		return strInValueDetails;
	}

	/**
	 * Sets the str in value details.
	 * 
	 * @param strInValueDetails
	 *            the new str in value details
	 */
	public void setStrInValueDetails(String[] strInValueDetails) {
		this.strInValueDetails = strInValueDetails;
	}

	/**
	 * Gets the str out col display name.
	 * 
	 * @return the str out col display name
	 */
	public String[] getStrOutColDisplayName() {
		return strOutColDisplayName;
	}

	/**
	 * Sets the str out col display name.
	 * 
	 * @param strOutColDisplayName
	 *            the new str out col display name
	 */
	public void setStrOutColDisplayName(String[] strOutColDisplayName) {
		this.strOutColDisplayName = strOutColDisplayName;
	}

	/**
	 * Gets the str out col width.
	 * 
	 * @return the str out col width
	 */
	public String[] getStrOutColWidth() {
		return strOutColWidth;
	}

	/**
	 * Sets the str out col width.
	 * 
	 * @param strOutColWidth
	 *            the new str out col width
	 */
	public void setStrOutColWidth(String[] strOutColWidth) {
		this.strOutColWidth = strOutColWidth;
	}

	/**
	 * Gets the str out col is group by.
	 * 
	 * @return the str out col is group by
	 */
	public String[] getStrOutColIsGroupBy() {
		return strOutColIsGroupBy;
	}

	/**
	 * Sets the str out col is group by.
	 * 
	 * @param strOutColIsGroupBy
	 *            the new str out col is group by
	 */
	public void setStrOutColIsGroupBy(String[] strOutColIsGroupBy) {
		this.strOutColIsGroupBy = strOutColIsGroupBy;
	}

	/**
	 * Gets the str out col is order by.
	 * 
	 * @return the str out col is order by
	 */
	public String[] getStrOutColIsOrderBy() {
		return strOutColIsOrderBy;
	}

	/**
	 * Sets the str out col is order by.
	 * 
	 * @param strOutColIsOrderBy
	 *            the new str out col is order by
	 */
	public void setStrOutColIsOrderBy(String[] strOutColIsOrderBy) {
		this.strOutColIsOrderBy = strOutColIsOrderBy;
	}

	/**
	 * Gets the str out col is grand total.
	 * 
	 * @return the str out col is grand total
	 */
	public String[] getStrOutColIsGrandTotal() {
		return strOutColIsGrandTotal;
	}

	/**
	 * Sets the str out col is grand total.
	 * 
	 * @param strOutColIsGrandTotal
	 *            the new str out col is grand total
	 */
	public void setStrOutColIsGrandTotal(String[] strOutColIsGrandTotal) {
		this.strOutColIsGrandTotal = strOutColIsGrandTotal;
	}

	/**
	 * Gets the str out col index.
	 * 
	 * @return the str out col index
	 */
	public String[] getStrOutColIndex() {
		return strOutColIndex;
	}

	/**
	 * Sets the str out col index.
	 * 
	 * @param strOutColIndex
	 *            the new str out col index
	 */
	public void setStrOutColIndex(String[] strOutColIndex) {
		this.strOutColIndex = strOutColIndex;
	}

	/**
	 * Gets the str out col actual index.
	 * 
	 * @return the str out col actual index
	 */
	public String[] getStrOutColActualIndex() {
		return strOutColActualIndex;
	}

	/**
	 * Sets the str out col actual index.
	 * 
	 * @param strOutColActualIndex
	 *            the new str out col actual index
	 */
	public void setStrOutColActualIndex(String[] strOutColActualIndex) {
		this.strOutColActualIndex = strOutColActualIndex;
	}

	/**
	 * Gets the str out col is hyper link.
	 * 
	 * @return the str out col is hyper link
	 */
	public String[] getStrOutColIsHyperLink() {
		return strOutColIsHyperLink;
	}

	/**
	 * Sets the str out col is hyper link.
	 * 
	 * @param strOutColIsHyperLink
	 *            the new str out col is hyper link
	 */
	public void setStrOutColIsHyperLink(String[] strOutColIsHyperLink) {
		this.strOutColIsHyperLink = strOutColIsHyperLink;
	}

	/**
	 * Gets the str param id.
	 * 
	 * @return the str param id
	 */
	public String getStrParamId() {
		return strParamId;
	}

	/**
	 * Sets the str param id.
	 * 
	 * @param strParamId
	 *            the new str param id
	 */
	public void setStrParamId(String strParamId) {
		this.strParamId = strParamId;
	}

	/**
	 * Gets the str combo values.
	 * 
	 * @return the str combo values
	 */
	public String getStrComboValues() {
		return strComboValues;
	}

	/**
	 * Sets the str combo values.
	 * 
	 * @param strComboValues
	 *            the new str combo values
	 */
	public void setStrComboValues(String strComboValues) {
		this.strComboValues = strComboValues;
	}

	/**
	 * Gets the str ct combo.
	 * 
	 * @return the str ct combo
	 */
	public String getStrCtCombo() {
		return strCtCombo;
	}

	/**
	 * Sets the str ct combo.
	 * 
	 * @param strCtCombo
	 *            the new str ct combo
	 */
	public void setStrCtCombo(String strCtCombo) {
		this.strCtCombo = strCtCombo;
	}

	/**
	 * Gets the str normal msg.
	 * 
	 * @return the str normal msg
	 */
	public String getStrNormalMsg() {
		return strNormalMsg;
	}

	/**
	 * Sets the str normal msg.
	 * 
	 * @param strNormalMsg
	 *            the new str normal msg
	 */
	public void setStrNormalMsg(String strNormalMsg) {
		this.strNormalMsg = strNormalMsg;
	}

	/**
	 * Gets the str err msg.
	 * 
	 * @return the str err msg
	 */
	public String getStrErrMsg() {
		return strErrMsg;
	}

	/**
	 * Sets the str err msg.
	 * 
	 * @param strErrMsg
	 *            the new str err msg
	 */
	public void setStrErrMsg(String strErrMsg) {
		this.strErrMsg = strErrMsg;
	}

	/**
	 * Gets the str warning msg.
	 * 
	 * @return the str warning msg
	 */
	public String getStrWarningMsg() {
		return strWarningMsg;
	}

	/**
	 * Sets the str warning msg.
	 * 
	 * @param strWarningMsg
	 *            the new str warning msg
	 */
	public void setStrWarningMsg(String strWarningMsg) {
		this.strWarningMsg = strWarningMsg;
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
	 * Gets the str report type name.
	 * 
	 * @return the str report type name
	 */
	public String getStrReportTypeName() {
		return strReportTypeName;
	}

	/**
	 * Sets the str report type name.
	 * 
	 * @param strReportTypeName
	 *            the new str report type name
	 */
	public void setStrReportTypeName(String strReportTypeName) {
		this.strReportTypeName = strReportTypeName;
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

}
