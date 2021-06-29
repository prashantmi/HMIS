<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Dynamic Report Template Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">


<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar1.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>


<script language="Javascript" src="../../dynamicreports/js/drpt_rowbased_rptMst.js"></script>

<!-- 
/**
 * @author Balasubramaniam M
 * Date of Creation : 17/4/2012
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : Dynamic Report Generation
 */
 -->
</head>
<body class="background">
	<html:form action="/masters/DynamicReportParamMstCNT"
		name="rptparamBean"
		type="dynamicreports.masters.controller.fb.DynamicReportParamMstFB" styleClass="formbg">

		<center>
			<div id="errMsg" class="errMsg">
				<bean:write name="rptparamBean" property="strErrMsg" />
			</div>
			<div id="warningMsg" class="warningMsg">
				<bean:write name="rptparamBean" property="strWarningMsg" />
			</div>
			<div id="normalMsg" class="normalMsg">
				<bean:write name="rptparamBean" property="strNormalMsg" />
			</div>


		
		</center>

		<table class="TABLEWIDTH" align="center">
			<tr class="HEADER">
				<td colspan="4">Dynamic Reports Template &gt;&gt; Row Based
					Report</td>
			</tr>


		</table>


		<table border="0" class="TABLEWIDTH" align="center" border='0'
			cellspacing='1px' cellpadding='1px'>

			<tr>
				<td class="LABEL" width="25%">Report Type</td>
				<td class="CONTROL" width="25%"><bean:write name="rptparamBean"
						property="strReportTypeName" /></td>

				<td class="LABEL" width="25%">Report Name</td>
				<td class="CONTROL" width="25%"><bean:write name="rptparamBean"
						property="strReportName" /></td>
			</tr>

			<tr>
				<td class="LABEL" width="25%">Display Name</td>
				<td class="CONTROL" width="25%"><bean:write name="rptparamBean"
						property="strReportDisplayName" /></td>

				<td class="LABEL" width="25%">Report Width</td>
				<td class="CONTROL" width="25%"><bean:write name="rptparamBean"
						property="strReportWidth" /></td>
			</tr>

<tr>
				<td class="LABEL" width="25%">Report Header Type</td>
				<td class="CONTROL" width="25%"><bean:write name="rptparamBean"
						property="strReportHeaderTypeName" /></td>

				<td class="LABEL" width="25%">Border Required</td>
				<td class="CONTROL" width="25%"><bean:write name="rptparamBean"
						property="strReportBorderReq" /></td>
			</tr>

			<tr>
				<td class="LABEL" width="25%">Procedure Name</td>
				<td class="CONTROL" width="25%"><bean:write name="rptparamBean"
						property="strProcedureName" /></td>

				<td class="LABEL" width="25%">Is Combo</td>
				<td class="CONTROL" width="25%"><logic:equal value="1"
						name="rptparamBean" property="strIsCombo">Yes</logic:equal> <logic:equal
						value="0" name="rptparamBean" property="strIsCombo">No</logic:equal>
				</td>
			</tr>

		</table>

		<bean:write name="rptparamBean" property="strProcParameterListView"
			filter="false" />

<logic:equal value="1" property="strReportHeaderParamReq"  name="rptparamBean">

	<table border="0" class="TABLEWIDTH" align="center" border='0'
			cellspacing='1px' cellpadding='1px'>
	<tr>
				<td class="LABEL" width="50%"><font color="red">*</font>Header Parameter Mapping</td>
				<td class="CONTROL" width="50%">
				<select name="strReportHeaderParamId" class="comboNormal" >
				<bean:write name="rptparamBean" property="strInParamValues" filter="false"/>
				</select>
				</td>
 
			</tr>

		</table>
		
</logic:equal>

		<table border="0" class="TABLEWIDTH" align="center" border='0'
			cellspacing='1px' cellpadding='1px'>
			<tr class="FOOTER">
				<td colspan="4"></td>
			</tr>
		</table>

		<table border="0" class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
					<div class="legends">
						<font color='red'>*</font>Mandatory Fields
					</div>
					<div class="control_button" align="left">
						<a href="#" class="button" title="Save Record" tabindex="1"
							onClick="return validateInsert();"
					onkeypress="return validateInsert();"><span class="save">Save</span></a>
						<a href="#" class="button" title="Reset Content" tabindex="1"
							onClick="document.forms[0].reset();"><span class="clear">Clear</span></a>
						<a href="#" class="button" tabindex="1" onkeypress="cancel('INIT');" onClick="cancel('INIT');"><span
							class="cancel">Cancel</span></a>
					</div>
				</td>
			</tr>
		</table>
		<input type="hidden" name="hmode" />

		<html:hidden property="strReportTypeId" name="rptparamBean" />
		<html:hidden property="strReportTemplateId" name="rptparamBean" />
		<html:hidden property="strProcedureName" name="rptparamBean" />
		<html:hidden property="strReportName" name="rptparamBean" />
		<html:hidden property="strIsCombo" name="rptparamBean" />

		<html:hidden property="strReportHeaderTypeId" name="rptparamBean" />
		<html:hidden property="strReportHeaderParamReq" name="rptparamBean" />
 

	</html:form>
	<tag:autoIndex></tag:autoIndex>
</body>
</html>