/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportParamMstFB.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.controller.fb;

import org.apache.struts.action.ActionForm;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportParamMstFB.
 */
public class DynamicReportParamMstFB extends ActionForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 02L;

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

	/** The str report type id. */
	private String strReportTypeId = "";

	/** The str report type name. */
	private String strReportTypeName = "";

	/** The str report type values. */
	private String strReportTypeValues = "";

	/** The str report template id. */
	private String strReportTemplateId = "";

	/** The str report proc id. */
	private String strReportProcId = "";

	/** The str report name. */
	private String strReportName = "";

	/** The str report values. */
	private String strReportValues = "";

	/** The str report header type id. */
	private String strReportHeaderTypeId = "10";

	/** The str report header param req. */
	private String strReportHeaderParamReq = "0";

	/** The str report display name. */
	private String strReportDisplayName = "";

	/** The str report width. */
	private String strReportWidth = "";

	/** The str report header type name. */
	private String strReportHeaderTypeName = "";

	/** The str report border req. */
	private String strReportBorderReq = "";

	/** The str report level display name. */
	private String strReportLevelDisplayName = "";

	/** The str procedure name. */
	private String strProcedureName = "";

	/** The str procedure values. */
	private String strProcedureValues = "";

	/** The str is combo. */
	private String strIsCombo = "0";

	/** The str is last. */
	private String strIsLast = "0";

	/** The str param type id. */
	private String strParamTypeId = "";

	/** The str proc in column dtls. */
	private String strProcInColumnDtls[] = null;

	/** The str in constant value. */
	private String strInConstantValue[] = null;

	/** The str param comp type. */
	private String strParamCompType[] = null;

	/** The str combo query. */
	private String strComboQuery[] = null;

	/** The str in display name. */
	private String strInDisplayName[] = null;

	/** The str pre in param values. */
	private String strPreInParamValues[] = null;

	/** The str in param type. */
	private String strInParamType[] = null;

	/** The str proc parameter list view. */
	private String strProcParameterListView = "";

	/** The str column required. */
	private String strColumnRequired[] = null;

	/** The str out column name. */
	private String strOutColumnName[] = null;

	/** The str out column display name. */
	private String strOutColumnDisplayName[] = null;

	/** The str out column actual index. */
	private String strOutColumnActualIndex[] = null;

	/** The str out column index. */
	private String strOutColumnIndex[] = null;

	/** The str out column width. */
	private String strOutColumnWidth[] = null;

	/** The str total required. */
	private String strTotalRequired[] = null;

	/** The str group by required. */
	private String strGroupByRequired[] = null;

	/** The str order by required. */
	private String strOrderByRequired[] = null;

	/** The str hyperlink required. */
	private String strHyperlinkRequired[] = null;

	/** The str total required value. */
	private String strTotalRequiredValue[] = null;

	/** The str group by required value. */
	private String strGroupByRequiredValue[] = null;

	/** The str order by required value. */
	private String strOrderByRequiredValue[] = null;

	/** The str hyperlink required value. */
	private String strHyperlinkRequiredValue[] = null;

	/** The str hyperlink mapping value. */
	private String strHyperlinkMappingValue[] = null;

	/** The str column display name. */
	private String strColumnDisplayName[] = null;

	/** The str column prefix. */
	private String strColumnPrefix[] = null;

	/** The str column formula. */
	private String strColumnFormula[] = null;

	/** The str column suffix. */
	private String strColumnSuffix[] = null;

	/** The str out column align. */
	private String strOutColumnAlign[] = null;

	/** The str is merge intermediate. */
	private String strIsMergeIntermediate = "0";

	/** The str is column base rpt. */
	private String strIsColumnBaseRpt = "0";

	/** The str in param values. */
	private String strInParamValues = "";

	/** The str out param values. */
	private String strOutParamValues = "";

	/** The str report header param type. */
	private String strReportHeaderParamType = "1";

	/** The str report header param id. */
	private String strReportHeaderParamId = "0";

	/**
	 * Gets the str out param values.
	 * 
	 * @return the str out param values
	 */
	public String getStrOutParamValues() {
		return strOutParamValues;
	}

	/**
	 * Sets the str out param values.
	 * 
	 * @param strOutParamValues
	 *            the new str out param values
	 */
	public void setStrOutParamValues(String strOutParamValues) {
		this.strOutParamValues = strOutParamValues;
	}

	/**
	 * Gets the str report header param type.
	 * 
	 * @return the str report header param type
	 */
	public String getStrReportHeaderParamType() {
		return strReportHeaderParamType;
	}

	/**
	 * Sets the str report header param type.
	 * 
	 * @param strReportHeaderParamType
	 *            the new str report header param type
	 */
	public void setStrReportHeaderParamType(String strReportHeaderParamType) {
		this.strReportHeaderParamType = strReportHeaderParamType;
	}

	/**
	 * Gets the str report header param id.
	 * 
	 * @return the str report header param id
	 */
	public String getStrReportHeaderParamId() {
		return strReportHeaderParamId;
	}

	/**
	 * Sets the str report header param id.
	 * 
	 * @param strReportHeaderParamId
	 *            the new str report header param id
	 */
	public void setStrReportHeaderParamId(String strReportHeaderParamId) {
		this.strReportHeaderParamId = strReportHeaderParamId;
	}

	/**
	 * Gets the str in param values.
	 * 
	 * @return the str in param values
	 */
	public String getStrInParamValues() {
		return strInParamValues;
	}

	/**
	 * Sets the str in param values.
	 * 
	 * @param strInParamValues
	 *            the new str in param values
	 */
	public void setStrInParamValues(String strInParamValues) {
		this.strInParamValues = strInParamValues;
	}

	/**
	 * Gets the str report header type id.
	 * 
	 * @return the str report header type id
	 */
	public String getStrReportHeaderTypeId() {
		return strReportHeaderTypeId;
	}

	/**
	 * Sets the str report header type id.
	 * 
	 * @param strReportHeaderTypeId
	 *            the new str report header type id
	 */
	public void setStrReportHeaderTypeId(String strReportHeaderTypeId) {
		this.strReportHeaderTypeId = strReportHeaderTypeId;
	}

	/**
	 * Gets the str report header param req.
	 * 
	 * @return the str report header param req
	 */
	public String getStrReportHeaderParamReq() {
		return strReportHeaderParamReq;
	}

	/**
	 * Sets the str report header param req.
	 * 
	 * @param strReportHeaderParamReq
	 *            the new str report header param req
	 */
	public void setStrReportHeaderParamReq(String strReportHeaderParamReq) {
		this.strReportHeaderParamReq = strReportHeaderParamReq;
	}

	/**
	 * Gets the str report header type name.
	 * 
	 * @return the str report header type name
	 */
	public String getStrReportHeaderTypeName() {
		return strReportHeaderTypeName;
	}

	/**
	 * Sets the str report header type name.
	 * 
	 * @param strReportHeaderTypeName
	 *            the new str report header type name
	 */
	public void setStrReportHeaderTypeName(String strReportHeaderTypeName) {
		this.strReportHeaderTypeName = strReportHeaderTypeName;
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
	 * Gets the str hyperlink mapping value.
	 * 
	 * @return the str hyperlink mapping value
	 */
	public String[] getStrHyperlinkMappingValue() {
		return strHyperlinkMappingValue;
	}

	/**
	 * Sets the str hyperlink mapping value.
	 * 
	 * @param strHyperlinkMappingValue
	 *            the new str hyperlink mapping value
	 */
	public void setStrHyperlinkMappingValue(String[] strHyperlinkMappingValue) {
		this.strHyperlinkMappingValue = strHyperlinkMappingValue;
	}

	/**
	 * Gets the str report proc id.
	 * 
	 * @return the str report proc id
	 */
	public String getStrReportProcId() {
		return strReportProcId;
	}

	/**
	 * Sets the str report proc id.
	 * 
	 * @param strReportProcId
	 *            the new str report proc id
	 */
	public void setStrReportProcId(String strReportProcId) {
		this.strReportProcId = strReportProcId;
	}

	/**
	 * Gets the str in param type.
	 * 
	 * @return the str in param type
	 */
	public String[] getStrInParamType() {
		return strInParamType;
	}

	/**
	 * Sets the str in param type.
	 * 
	 * @param strInParamType
	 *            the new str in param type
	 */
	public void setStrInParamType(String[] strInParamType) {
		this.strInParamType = strInParamType;
	}

	/**
	 * Gets the str param type id.
	 * 
	 * @return the str param type id
	 */
	public String getStrParamTypeId() {
		return strParamTypeId;
	}

	/**
	 * Sets the str param type id.
	 * 
	 * @param strParamTypeId
	 *            the new str param type id
	 */
	public void setStrParamTypeId(String strParamTypeId) {
		this.strParamTypeId = strParamTypeId;
	}

	/**
	 * Gets the str hyperlink required.
	 * 
	 * @return the str hyperlink required
	 */
	public String[] getStrHyperlinkRequired() {
		return strHyperlinkRequired;
	}

	/**
	 * Sets the str hyperlink required.
	 * 
	 * @param strHyperlinkRequired
	 *            the new str hyperlink required
	 */
	public void setStrHyperlinkRequired(String[] strHyperlinkRequired) {
		this.strHyperlinkRequired = strHyperlinkRequired;
	}

	/**
	 * Gets the str hyperlink required value.
	 * 
	 * @return the str hyperlink required value
	 */
	public String[] getStrHyperlinkRequiredValue() {
		return strHyperlinkRequiredValue;
	}

	/**
	 * Sets the str hyperlink required value.
	 * 
	 * @param strHyperlinkRequiredValue
	 *            the new str hyperlink required value
	 */
	public void setStrHyperlinkRequiredValue(String[] strHyperlinkRequiredValue) {
		this.strHyperlinkRequiredValue = strHyperlinkRequiredValue;
	}

	/**
	 * Gets the str is column base rpt.
	 * 
	 * @return the str is column base rpt
	 */
	public String getStrIsColumnBaseRpt() {
		return strIsColumnBaseRpt;
	}

	/**
	 * Sets the str is column base rpt.
	 * 
	 * @param strIsColumnBaseRpt
	 *            the new str is column base rpt
	 */
	public void setStrIsColumnBaseRpt(String strIsColumnBaseRpt) {
		this.strIsColumnBaseRpt = strIsColumnBaseRpt;
	}

	/**
	 * Gets the str pre in param values.
	 * 
	 * @return the str pre in param values
	 */
	public String[] getStrPreInParamValues() {
		return strPreInParamValues;
	}

	/**
	 * Sets the str pre in param values.
	 * 
	 * @param strPreInParamValues
	 *            the new str pre in param values
	 */
	public void setStrPreInParamValues(String[] strPreInParamValues) {
		this.strPreInParamValues = strPreInParamValues;
	}

	/**
	 * Gets the str is merge intermediate.
	 * 
	 * @return the str is merge intermediate
	 */
	public String getStrIsMergeIntermediate() {
		return strIsMergeIntermediate;
	}

	/**
	 * Sets the str is merge intermediate.
	 * 
	 * @param strIsMergeIntermediate
	 *            the new str is merge intermediate
	 */
	public void setStrIsMergeIntermediate(String strIsMergeIntermediate) {
		this.strIsMergeIntermediate = strIsMergeIntermediate;
	}

	/**
	 * Gets the str report level display name.
	 * 
	 * @return the str report level display name
	 */
	public String getStrReportLevelDisplayName() {
		return strReportLevelDisplayName;
	}

	/**
	 * Sets the str report level display name.
	 * 
	 * @param strReportLevelDisplayName
	 *            the new str report level display name
	 */
	public void setStrReportLevelDisplayName(String strReportLevelDisplayName) {
		this.strReportLevelDisplayName = strReportLevelDisplayName;
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
	 * Gets the str column prefix.
	 * 
	 * @return the str column prefix
	 */
	public String[] getStrColumnPrefix() {
		return strColumnPrefix;
	}

	/**
	 * Sets the str column prefix.
	 * 
	 * @param strColumnPrefix
	 *            the new str column prefix
	 */
	public void setStrColumnPrefix(String[] strColumnPrefix) {
		this.strColumnPrefix = strColumnPrefix;
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
	 * Gets the str total required value.
	 * 
	 * @return the str total required value
	 */
	public String[] getStrTotalRequiredValue() {
		return strTotalRequiredValue;
	}

	/**
	 * Sets the str total required value.
	 * 
	 * @param strTotalRequiredValue
	 *            the new str total required value
	 */
	public void setStrTotalRequiredValue(String[] strTotalRequiredValue) {
		this.strTotalRequiredValue = strTotalRequiredValue;
	}

	/**
	 * Gets the str group by required value.
	 * 
	 * @return the str group by required value
	 */
	public String[] getStrGroupByRequiredValue() {
		return strGroupByRequiredValue;
	}

	/**
	 * Sets the str group by required value.
	 * 
	 * @param strGroupByRequiredValue
	 *            the new str group by required value
	 */
	public void setStrGroupByRequiredValue(String[] strGroupByRequiredValue) {
		this.strGroupByRequiredValue = strGroupByRequiredValue;
	}

	/**
	 * Gets the str order by required value.
	 * 
	 * @return the str order by required value
	 */
	public String[] getStrOrderByRequiredValue() {
		return strOrderByRequiredValue;
	}

	/**
	 * Sets the str order by required value.
	 * 
	 * @param strOrderByRequiredValue
	 *            the new str order by required value
	 */
	public void setStrOrderByRequiredValue(String[] strOrderByRequiredValue) {
		this.strOrderByRequiredValue = strOrderByRequiredValue;
	}

	/**
	 * Gets the str column required.
	 * 
	 * @return the str column required
	 */
	public String[] getStrColumnRequired() {
		return strColumnRequired;
	}

	/**
	 * Sets the str column required.
	 * 
	 * @param strColumnRequired
	 *            the new str column required
	 */
	public void setStrColumnRequired(String[] strColumnRequired) {
		this.strColumnRequired = strColumnRequired;
	}

	/**
	 * Gets the str out column name.
	 * 
	 * @return the str out column name
	 */
	public String[] getStrOutColumnName() {
		return strOutColumnName;
	}

	/**
	 * Sets the str out column name.
	 * 
	 * @param strOutColumnName
	 *            the new str out column name
	 */
	public void setStrOutColumnName(String[] strOutColumnName) {
		this.strOutColumnName = strOutColumnName;
	}

	/**
	 * Gets the str out column display name.
	 * 
	 * @return the str out column display name
	 */
	public String[] getStrOutColumnDisplayName() {
		return strOutColumnDisplayName;
	}

	/**
	 * Sets the str out column display name.
	 * 
	 * @param strOutColumnDisplayName
	 *            the new str out column display name
	 */
	public void setStrOutColumnDisplayName(String[] strOutColumnDisplayName) {
		this.strOutColumnDisplayName = strOutColumnDisplayName;
	}

	/**
	 * Gets the str out column actual index.
	 * 
	 * @return the str out column actual index
	 */
	public String[] getStrOutColumnActualIndex() {
		return strOutColumnActualIndex;
	}

	/**
	 * Sets the str out column actual index.
	 * 
	 * @param strOutColumnActualIndex
	 *            the new str out column actual index
	 */
	public void setStrOutColumnActualIndex(String[] strOutColumnActualIndex) {
		this.strOutColumnActualIndex = strOutColumnActualIndex;
	}

	/**
	 * Gets the str out column index.
	 * 
	 * @return the str out column index
	 */
	public String[] getStrOutColumnIndex() {
		return strOutColumnIndex;
	}

	/**
	 * Sets the str out column index.
	 * 
	 * @param strOutColumnIndex
	 *            the new str out column index
	 */
	public void setStrOutColumnIndex(String[] strOutColumnIndex) {
		this.strOutColumnIndex = strOutColumnIndex;
	}

	/**
	 * Gets the str out column width.
	 * 
	 * @return the str out column width
	 */
	public String[] getStrOutColumnWidth() {
		return strOutColumnWidth;
	}

	/**
	 * Sets the str out column width.
	 * 
	 * @param strOutColumnWidth
	 *            the new str out column width
	 */
	public void setStrOutColumnWidth(String[] strOutColumnWidth) {
		this.strOutColumnWidth = strOutColumnWidth;
	}

	/**
	 * Gets the str total required.
	 * 
	 * @return the str total required
	 */
	public String[] getStrTotalRequired() {
		return strTotalRequired;
	}

	/**
	 * Sets the str total required.
	 * 
	 * @param strTotalRequired
	 *            the new str total required
	 */
	public void setStrTotalRequired(String[] strTotalRequired) {
		this.strTotalRequired = strTotalRequired;
	}

	/**
	 * Gets the str group by required.
	 * 
	 * @return the str group by required
	 */
	public String[] getStrGroupByRequired() {
		return strGroupByRequired;
	}

	/**
	 * Sets the str group by required.
	 * 
	 * @param strGroupByRequired
	 *            the new str group by required
	 */
	public void setStrGroupByRequired(String[] strGroupByRequired) {
		this.strGroupByRequired = strGroupByRequired;
	}

	/**
	 * Gets the str order by required.
	 * 
	 * @return the str order by required
	 */
	public String[] getStrOrderByRequired() {
		return strOrderByRequired;
	}

	/**
	 * Sets the str order by required.
	 * 
	 * @param strOrderByRequired
	 *            the new str order by required
	 */
	public void setStrOrderByRequired(String[] strOrderByRequired) {
		this.strOrderByRequired = strOrderByRequired;
	}

	/**
	 * Gets the str proc parameter list view.
	 * 
	 * @return the str proc parameter list view
	 */
	public String getStrProcParameterListView() {
		return strProcParameterListView;
	}

	/**
	 * Sets the str proc parameter list view.
	 * 
	 * @param strProcParameterListView
	 *            the new str proc parameter list view
	 */
	public void setStrProcParameterListView(String strProcParameterListView) {
		this.strProcParameterListView = strProcParameterListView;
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
	 * Gets the str in display name.
	 * 
	 * @return the str in display name
	 */
	public String[] getStrInDisplayName() {
		return strInDisplayName;
	}

	/**
	 * Sets the str in display name.
	 * 
	 * @param strInDisplayName
	 *            the new str in display name
	 */
	public void setStrInDisplayName(String[] strInDisplayName) {
		this.strInDisplayName = strInDisplayName;
	}

	/**
	 * Gets the str proc in column dtls.
	 * 
	 * @return the str proc in column dtls
	 */
	public String[] getStrProcInColumnDtls() {
		return strProcInColumnDtls;
	}

	/**
	 * Sets the str proc in column dtls.
	 * 
	 * @param strProcInColumnDtls
	 *            the new str proc in column dtls
	 */
	public void setStrProcInColumnDtls(String[] strProcInColumnDtls) {
		this.strProcInColumnDtls = strProcInColumnDtls;
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
	 * Gets the str param comp type.
	 * 
	 * @return the str param comp type
	 */
	public String[] getStrParamCompType() {
		return strParamCompType;
	}

	/**
	 * Sets the str param comp type.
	 * 
	 * @param strParamCompType
	 *            the new str param comp type
	 */
	public void setStrParamCompType(String[] strParamCompType) {
		this.strParamCompType = strParamCompType;
	}

	/**
	 * Gets the str combo query.
	 * 
	 * @return the str combo query
	 */
	public String[] getStrComboQuery() {
		return strComboQuery;
	}

	/**
	 * Sets the str combo query.
	 * 
	 * @param strComboQuery
	 *            the new str combo query
	 */
	public void setStrComboQuery(String[] strComboQuery) {
		this.strComboQuery = strComboQuery;
	}

	/**
	 * Gets the str report type values.
	 * 
	 * @return the str report type values
	 */
	public String getStrReportTypeValues() {
		return strReportTypeValues;
	}

	/**
	 * Sets the str report type values.
	 * 
	 * @param strReportTypeValues
	 *            the new str report type values
	 */
	public void setStrReportTypeValues(String strReportTypeValues) {
		this.strReportTypeValues = strReportTypeValues;
	}

	/**
	 * Gets the str report values.
	 * 
	 * @return the str report values
	 */
	public String getStrReportValues() {
		return strReportValues;
	}

	/**
	 * Sets the str report values.
	 * 
	 * @param strReportValues
	 *            the new str report values
	 */
	public void setStrReportValues(String strReportValues) {
		this.strReportValues = strReportValues;
	}

	/**
	 * Gets the str procedure name.
	 * 
	 * @return the str procedure name
	 */
	public String getStrProcedureName() {
		return strProcedureName;
	}

	/**
	 * Sets the str procedure name.
	 * 
	 * @param strProcedureName
	 *            the new str procedure name
	 */
	public void setStrProcedureName(String strProcedureName) {
		this.strProcedureName = strProcedureName;
	}

	/**
	 * Gets the str procedure values.
	 * 
	 * @return the str procedure values
	 */
	public String getStrProcedureValues() {
		return strProcedureValues;
	}

	/**
	 * Sets the str procedure values.
	 * 
	 * @param strProcedureValues
	 *            the new str procedure values
	 */
	public void setStrProcedureValues(String strProcedureValues) {
		this.strProcedureValues = strProcedureValues;
	}

	/**
	 * Gets the str is combo.
	 * 
	 * @return the str is combo
	 */
	public String getStrIsCombo() {
		return strIsCombo;
	}

	/**
	 * Sets the str is combo.
	 * 
	 * @param strIsCombo
	 *            the new str is combo
	 */
	public void setStrIsCombo(String strIsCombo) {
		this.strIsCombo = strIsCombo;
	}

	/**
	 * Gets the str is last.
	 * 
	 * @return the str is last
	 */
	public String getStrIsLast() {
		return strIsLast;
	}

	/**
	 * Sets the str is last.
	 * 
	 * @param strIsLast
	 *            the new str is last
	 */
	public void setStrIsLast(String strIsLast) {
		this.strIsLast = strIsLast;
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

}
