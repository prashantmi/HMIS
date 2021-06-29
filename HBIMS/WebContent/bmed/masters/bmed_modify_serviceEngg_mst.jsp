<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
 <%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<title>Service Engineering Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../bmed/js/serviceEngg_mst.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<!-- 
/**
 * @author Amit Kumar
 * Date of Creation : 17/1/2011
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : BMED
 */
 -->
</head>
<body
	onLoad="addRows(new Array('strServiceEnggName'),new Array('t'),'1','1','R');">
<html:form action="/masters/ServiceEngineerMstCNT"
	name="serviceEnggMstBean"
	type="bmed.masters.controller.fb.ServiceEngineerMstFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="serviceEnggMstBean" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="serviceEnggMstBean" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="serviceEnggMstBean" property="strMsgString" /></div>


	</center>

	<table class="TABLEWIDTH" align="center">
		<tr class="HEADER">
			<td colspan="4">Service Engineer &gt;&gt; Modify</td>
		</tr>


	</table>


	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1">

		<tr>
			<td class="LABEL" width="25%" colspan="2">Engineering Item Type</td>
			<td width="25%" class="CONTROL"><bean:write
				name="serviceEnggMstBean" property="strEnggItemTypeCmb"
				filter="false" /></td>

			<td class="LABEL" width="25%" colspan="2">Engineering Item
			Sub-Type</td>
			<td width="25%" class="CONTROL"><bean:write
				name="serviceEnggMstBean" property="strEnggItemSubTypeCmb"
				filter="false" /></td>

		</tr>
	</table>

	<bean:write name="serviceEnggMstBean" property="strServiceEnggNameList"
		filter="false" />

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr class="TITLE">

			<td colspan="4"></td>

		</tr>
		<tr>
			<td class="LABEL" width="50%" colspan="2">
			<div align="right"><font color="red">*</font> Effective From</div>
			</td>
			<td class="CONTROL" colspan="2"><dateTag:date
				name="strEffectiveFrom" value="${serviceEnggMstBean.strCtDate}"></dateTag:date></td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL" valign="top">Remarks
			if Any</td>
			<td width="50%" colspan="2" class="CONTROL"><textarea
				name="strRemarks" cols="25" rows="2"
				onkeypress="return validateData(event,9);"><bean:write
				name="serviceEnggMstBean" property="strRemarks" /></textarea></td>
		</tr>
		<tr>
			<td width="50%" colspan="2" class="LABEL">Record Status</td>
			<td width="50%" colspan="2" class="CONTROL"><html:radio
				name="serviceEnggMstBean" property="strIsValid" value="1">Active</html:radio>
			<html:radio name="serviceEnggMstBean" property="strIsValid" value="2">Inactive</html:radio>

			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center">
		<tr>

			<td align="center"><img style="cursor: pointer;"
				src="../../hisglobal/images/btn-sv.png"
				onClick="return validateUpdate();" /> <img style="cursor: pointer;"
				src="../../hisglobal/images/btn-clr.png"
				onClick="document.forms[0].reset();"> <img
				style="cursor: pointer;" src="../../hisglobal/images/btn-ccl.png"
				onClick="cancel('LIST');"></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="comboValue"
		value="${serviceEnggMstBean.comboValue}">
	<input type="hidden" name="strPkey"
		value="${serviceEnggMstBean.strPkey}">
	<input type="hidden" name="strChk" value="${serviceEnggMstBean.strChk}">

	<cmbPers:cmbPers />
</html:form>
<jsp:include page="bmed_serviceEngg_multiRow.jsp"></jsp:include>

</body>
</html>