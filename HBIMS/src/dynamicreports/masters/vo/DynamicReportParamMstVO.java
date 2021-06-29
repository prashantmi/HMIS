/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportParamMstVO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.masters.vo;

import javax.sql.rowset.WebRowSet;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportParamMstVO.
 */
public class DynamicReportParamMstVO {

	/** The str msg string. */
	private String strMsgString = "";

	/** The str msg type. */
	private String strMsgType = "";

	/** The str seat id. */
	private String strSeatId = "";

	/** The str hospital code. */
	private String strHospitalCode = "";

	/** The str report type id. */
	private String strReportTypeId = "";

	/** The str report type name. */
	private String strReportTypeName = "";

	/** The ws report type values. */
	private WebRowSet wsReportTypeValues = null;

	/** The str report template id. */
	private String strReportTemplateId = "";

	/** The str report proc id. */
	private String strReportProcId = "";

	/** The str report name. */
	private String strReportName = "";

	/** The ws report values. */
	private WebRowSet wsReportValues = null;

	/** The str procedure name. */
	private String strProcedureName = "";

	/** The ws procedure values. */
	private WebRowSet wsProcedureValues = null;

	/** The str is combo. */
	private String strIsCombo = "0";

	/** The str is last. */
	private String strIsLast = "0";

	/** The str is column base rpt. */
	private String strIsColumnBaseRpt = "0";

	/** The str report header type id. */
	private String strReportHeaderTypeId = "";

	/** The str report header param req. */
	private String strReportHeaderParamReq = "";

	/** The str report header param type. */
	private String strReportHeaderParamType = "";

	/** The str report header param id. */
	private String strReportHeaderParamId = "";

	/** The ws header mapping param values. */
	private WebRowSet wsHeaderMappingParamValues = null;

	/** The ws in param view. */
	private WebRowSet wsInParamView = null;

	/** The ws out param view. */
	private WebRowSet wsOutParamView = null;

	/** The ws col param view. */
	private WebRowSet wsColParamView = null;

	/**
	 * Gets the ws header mapping param values.
	 * 
	 * @return the ws header mapping param values
	 */
	public WebRowSet getWsHeaderMappingParamValues() {
		return wsHeaderMappingParamValues;
	}

	/**
	 * Sets the ws header mapping param values.
	 * 
	 * @param wsHeaderMappingParamValues
	 *            the new ws header mapping param values
	 */
	public void setWsHeaderMappingParamValues(
			WebRowSet wsHeaderMappingParamValues) {
		this.wsHeaderMappingParamValues = wsHeaderMappingParamValues;
	}

	/**
	 * Gets the ws in param view.
	 * 
	 * @return the ws in param view
	 */
	public WebRowSet getWsInParamView() {
		return wsInParamView;
	}

	/**
	 * Sets the ws in param view.
	 * 
	 * @param wsInParamView
	 *            the new ws in param view
	 */
	public void setWsInParamView(WebRowSet wsInParamView) {
		this.wsInParamView = wsInParamView;
	}

	/**
	 * Gets the ws out param view.
	 * 
	 * @return the ws out param view
	 */
	public WebRowSet getWsOutParamView() {
		return wsOutParamView;
	}

	/**
	 * Sets the ws out param view.
	 * 
	 * @param wsOutParamView
	 *            the new ws out param view
	 */
	public void setWsOutParamView(WebRowSet wsOutParamView) {
		this.wsOutParamView = wsOutParamView;
	}

	/**
	 * Gets the ws col param view.
	 * 
	 * @return the ws col param view
	 */
	public WebRowSet getWsColParamView() {
		return wsColParamView;
	}

	/**
	 * Sets the ws col param view.
	 * 
	 * @param wsColParamView
	 *            the new ws col param view
	 */
	public void setWsColParamView(WebRowSet wsColParamView) {
		this.wsColParamView = wsColParamView;
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

	/** The ws proc in param. */
	private WebRowSet wsProcInParam = null;

	/** The ws proc out param. */
	private WebRowSet wsProcOutParam = null;

	/** The ws pre rpt dtls. */
	private WebRowSet wsPreRptDtls = null;

	/** The ws pre in params. */
	private WebRowSet wsPreInParams = null;

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

	/** The str out column align. */
	private String strOutColumnAlign[] = null;

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

	/** The str report level display name. */
	private String strReportLevelDisplayName = "";

	/** The str param type id. */
	private String strParamTypeId = "";

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
	 * Gets the ws pre in params.
	 * 
	 * @return the ws pre in params
	 */
	public WebRowSet getWsPreInParams() {
		return wsPreInParams;
	}

	/**
	 * Sets the ws pre in params.
	 * 
	 * @param wsPreInParams
	 *            the new ws pre in params
	 */
	public void setWsPreInParams(WebRowSet wsPreInParams) {
		this.wsPreInParams = wsPreInParams;
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
	 * Gets the ws pre rpt dtls.
	 * 
	 * @return the ws pre rpt dtls
	 */
	public WebRowSet getWsPreRptDtls() {
		return wsPreRptDtls;
	}

	/**
	 * Sets the ws pre rpt dtls.
	 * 
	 * @param wsPreRptDtls
	 *            the new ws pre rpt dtls
	 */
	public void setWsPreRptDtls(WebRowSet wsPreRptDtls) {
		this.wsPreRptDtls = wsPreRptDtls;
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
	 * Gets the ws proc out param.
	 * 
	 * @return the ws proc out param
	 */
	public WebRowSet getWsProcOutParam() {
		return wsProcOutParam;
	}

	/**
	 * Sets the ws proc out param.
	 * 
	 * @param wsProcOutParam
	 *            the new ws proc out param
	 */
	public void setWsProcOutParam(WebRowSet wsProcOutParam) {
		this.wsProcOutParam = wsProcOutParam;
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
	 * Gets the ws proc in param.
	 * 
	 * @return the ws proc in param
	 */
	public WebRowSet getWsProcInParam() {
		return wsProcInParam;
	}

	/**
	 * Sets the ws proc in param.
	 * 
	 * @param wsProcInParam
	 *            the new ws proc in param
	 */
	public void setWsProcInParam(WebRowSet wsProcInParam) {
		this.wsProcInParam = wsProcInParam;
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
	 * Gets the ws report type values.
	 * 
	 * @return the ws report type values
	 */
	public WebRowSet getWsReportTypeValues() {
		return wsReportTypeValues;
	}

	/**
	 * Sets the ws report type values.
	 * 
	 * @param wsReportTypeValues
	 *            the new ws report type values
	 */
	public void setWsReportTypeValues(WebRowSet wsReportTypeValues) {
		this.wsReportTypeValues = wsReportTypeValues;
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
	 * Gets the ws report values.
	 * 
	 * @return the ws report values
	 */
	public WebRowSet getWsReportValues() {
		return wsReportValues;
	}

	/**
	 * Sets the ws report values.
	 * 
	 * @param wsReportValues
	 *            the new ws report values
	 */
	public void setWsReportValues(WebRowSet wsReportValues) {
		this.wsReportValues = wsReportValues;
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
	 * Gets the ws procedure values.
	 * 
	 * @return the ws procedure values
	 */
	public WebRowSet getWsProcedureValues() {
		return wsProcedureValues;
	}

	/**
	 * Sets the ws procedure values.
	 * 
	 * @param wsProcedureValues
	 *            the new ws procedure values
	 */
	public void setWsProcedureValues(WebRowSet wsProcedureValues) {
		this.wsProcedureValues = wsProcedureValues;
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

}
