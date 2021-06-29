/**********************************************************
 Project:	   DWH_ANDHRA	
 File:         DynamicReportsTransVO.java
 Created:      Jul 8, 2014
 Last Changed: Jul 8, 2014
 Author:       manish

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package dynamicreports.reports.vo;

import javax.sql.rowset.WebRowSet;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicReportsTransVO.
 */
public class DynamicReportsTransVO {

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
	// private WebRowSet wsReportTypeValues = null;
	/** The str report template id. */
	private String strReportTemplateId = "";

	/** The str proc id. */
	private String strProcId = "";

	/** The str report is col based. */
	private String strReportIsColBased = "0";

	/**
	 * Gets the str report is col based.
	 * 
	 * @return the str report is col based
	 */
	public String getStrReportIsColBased() {
		return strReportIsColBased;
	}

	/**
	 * Sets the str report is col based.
	 * 
	 * @param strReportIsColBased
	 *            the new str report is col based
	 */
	public void setStrReportIsColBased(String strReportIsColBased) {
		this.strReportIsColBased = strReportIsColBased;
	}

	/** The ws report template values. */
	private WebRowSet wsReportTemplateValues = null;

	/** The ws report proc values. */
	private WebRowSet wsReportProcValues = null;

	/** The ws report in param values. */
	private WebRowSet wsReportInParamValues = null;

	/** The ws report out param values. */
	private WebRowSet wsReportOutParamValues = null;

	/** The ws report col param values. */
	private WebRowSet wsReportColParamValues = null;

	/** The ws generated datas. */
	private WebRowSet wsGeneratedDatas = null;

	/** The str template proc display name. */
	private String strTemplateProcDisplayName = "";

	/** The str template proc is combo depend. */
	private String strTemplateProcIsComboDepend = "";

	/** The str template proc name. */
	private String strTemplateProcName = "";

	/** The str template proc is last. */
	private String strTemplateProcIsLast = "";

	/** The str template proc is col base. */
	private String strTemplateProcIsColBase = "";

	/** The str report header type id. */
	private String strReportHeaderTypeId = "";

	/** The str report header param req. */
	private String strReportHeaderParamReq = "";

	/** The str report header param type. */
	private String strReportHeaderParamType = "";

	/** The str report header param id. */
	private String strReportHeaderParamId = "";

	/** The str report header param value. */
	private String strReportHeaderParamValue = "";

	/** The str report header html content. */
	private String strReportHeaderHtmlContent = "";

	/** The str header. */
	private String[] strHeader = null;

	/** The str in param name. */
	private String[] strInParamName = null;

	/** The str in constant value. */
	private String[] strInConstantValue = null;

	/** The str in comp type. */
	private String[] strInCompType = null;

	/** The str in value details. */
	private String[] strInValueDetails = null;

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

	/** The str call procedure. */
	private String strCallProcedure = "";

	/** The str order string. */
	private String strOrderString = "";

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
	 * Gets the str report header param value.
	 * 
	 * @return the str report header param value
	 */
	public String getStrReportHeaderParamValue() {
		return strReportHeaderParamValue;
	}

	/**
	 * Sets the str report header param value.
	 * 
	 * @param strReportHeaderParamValue
	 *            the new str report header param value
	 */
	public void setStrReportHeaderParamValue(String strReportHeaderParamValue) {
		this.strReportHeaderParamValue = strReportHeaderParamValue;
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
	 * Gets the str proc id.
	 * 
	 * @return the str proc id
	 */
	public String getStrProcId() {
		return strProcId;
	}

	/**
	 * Sets the str proc id.
	 * 
	 * @param strProcId
	 *            the new str proc id
	 */
	public void setStrProcId(String strProcId) {
		this.strProcId = strProcId;
	}

	/**
	 * Gets the ws report col param values.
	 * 
	 * @return the ws report col param values
	 */
	public WebRowSet getWsReportColParamValues() {
		return wsReportColParamValues;
	}

	/**
	 * Sets the ws report col param values.
	 * 
	 * @param wsReportColParamValues
	 *            the new ws report col param values
	 */
	public void setWsReportColParamValues(WebRowSet wsReportColParamValues) {
		this.wsReportColParamValues = wsReportColParamValues;
	}

	/**
	 * Gets the ws generated datas.
	 * 
	 * @return the ws generated datas
	 */
	public WebRowSet getWsGeneratedDatas() {
		return wsGeneratedDatas;
	}

	/**
	 * Sets the ws generated datas.
	 * 
	 * @param wsGeneratedDatas
	 *            the new ws generated datas
	 */
	public void setWsGeneratedDatas(WebRowSet wsGeneratedDatas) {
		this.wsGeneratedDatas = wsGeneratedDatas;
	}

	/**
	 * Gets the str call procedure.
	 * 
	 * @return the str call procedure
	 */
	public String getStrCallProcedure() {
		return strCallProcedure;
	}

	/**
	 * Sets the str call procedure.
	 * 
	 * @param strCallProcedure
	 *            the new str call procedure
	 */
	public void setStrCallProcedure(String strCallProcedure) {
		this.strCallProcedure = strCallProcedure;
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
	 * Gets the str template proc display name.
	 * 
	 * @return the str template proc display name
	 */
	public String getStrTemplateProcDisplayName() {
		return strTemplateProcDisplayName;
	}

	/**
	 * Sets the str template proc display name.
	 * 
	 * @param strTemplateProcDisplayName
	 *            the new str template proc display name
	 */
	public void setStrTemplateProcDisplayName(String strTemplateProcDisplayName) {
		this.strTemplateProcDisplayName = strTemplateProcDisplayName;
	}

	/**
	 * Gets the str template proc is combo depend.
	 * 
	 * @return the str template proc is combo depend
	 */
	public String getStrTemplateProcIsComboDepend() {
		return strTemplateProcIsComboDepend;
	}

	/**
	 * Sets the str template proc is combo depend.
	 * 
	 * @param strTemplateProcIsComboDepend
	 *            the new str template proc is combo depend
	 */
	public void setStrTemplateProcIsComboDepend(
			String strTemplateProcIsComboDepend) {
		this.strTemplateProcIsComboDepend = strTemplateProcIsComboDepend;
	}

	/**
	 * Gets the str template proc name.
	 * 
	 * @return the str template proc name
	 */
	public String getStrTemplateProcName() {
		return strTemplateProcName;
	}

	/**
	 * Sets the str template proc name.
	 * 
	 * @param strTemplateProcName
	 *            the new str template proc name
	 */
	public void setStrTemplateProcName(String strTemplateProcName) {
		this.strTemplateProcName = strTemplateProcName;
	}

	/**
	 * Gets the str template proc is last.
	 * 
	 * @return the str template proc is last
	 */
	public String getStrTemplateProcIsLast() {
		return strTemplateProcIsLast;
	}

	/**
	 * Sets the str template proc is last.
	 * 
	 * @param strTemplateProcIsLast
	 *            the new str template proc is last
	 */
	public void setStrTemplateProcIsLast(String strTemplateProcIsLast) {
		this.strTemplateProcIsLast = strTemplateProcIsLast;
	}

	/**
	 * Gets the str template proc is col base.
	 * 
	 * @return the str template proc is col base
	 */
	public String getStrTemplateProcIsColBase() {
		return strTemplateProcIsColBase;
	}

	/**
	 * Sets the str template proc is col base.
	 * 
	 * @param strTemplateProcIsColBase
	 *            the new str template proc is col base
	 */
	public void setStrTemplateProcIsColBase(String strTemplateProcIsColBase) {
		this.strTemplateProcIsColBase = strTemplateProcIsColBase;
	}

	/**
	 * Gets the ws report out param values.
	 * 
	 * @return the ws report out param values
	 */
	public WebRowSet getWsReportOutParamValues() {
		return wsReportOutParamValues;
	}

	/**
	 * Sets the ws report out param values.
	 * 
	 * @param wsReportOutParamValues
	 *            the new ws report out param values
	 */
	public void setWsReportOutParamValues(WebRowSet wsReportOutParamValues) {
		this.wsReportOutParamValues = wsReportOutParamValues;
	}

	/**
	 * Gets the ws report in param values.
	 * 
	 * @return the ws report in param values
	 */
	public WebRowSet getWsReportInParamValues() {
		return wsReportInParamValues;
	}

	/**
	 * Sets the ws report in param values.
	 * 
	 * @param wsReportInParamValues
	 *            the new ws report in param values
	 */
	public void setWsReportInParamValues(WebRowSet wsReportInParamValues) {
		this.wsReportInParamValues = wsReportInParamValues;
	}

	/**
	 * Gets the ws report proc values.
	 * 
	 * @return the ws report proc values
	 */
	public WebRowSet getWsReportProcValues() {
		return wsReportProcValues;
	}

	/**
	 * Sets the ws report proc values.
	 * 
	 * @param wsReportProcValues
	 *            the new ws report proc values
	 */
	public void setWsReportProcValues(WebRowSet wsReportProcValues) {
		this.wsReportProcValues = wsReportProcValues;
	}

	/**
	 * Gets the ws report template values.
	 * 
	 * @return the ws report template values
	 */
	public WebRowSet getWsReportTemplateValues() {
		return wsReportTemplateValues;
	}

	/**
	 * Sets the ws report template values.
	 * 
	 * @param wsReportTemplateValues
	 *            the new ws report template values
	 */
	public void setWsReportTemplateValues(WebRowSet wsReportTemplateValues) {
		this.wsReportTemplateValues = wsReportTemplateValues;
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
