<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%-- 
author 	: Arun V.R
Date of Creation : 20-Jan-11
Process : Expertise Master
Module 	: BMED
TL 		: Mr. Amit Kumar
Description : Add page for Expertise Master  
 --%>
<html>
<head>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

<title>Engineering Item Sub Type Master</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
@import url(../../hisglobal/css/calendar-tas.css);
</style>

<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../bmed/js/expertiseMst.js"></script>
<script language="JavaScript">



	
	
</script>
</head>

<body onload="document.forms[0].strItemSubTypeName.focus()">
<html:form name="expertiseMstBean" action="/masters/ExpertiseMstCNT"
	type="bmed.masters.controller.fb.ExpertiseMstFB">
	<div class="errMsg"><bean:write name="expertiseMstBean"
		property="strErr" /></div>
	<div class="warningMsg"><bean:write name="expertiseMstBean"
		property="strWarning" /></div>
	<div class="normalMsg"><bean:write name="expertiseMstBean"
		property="strMsg" /></div>
	<tag:tab tabLabel="Expertise Master " selectedTab="FIRST"
		align="center" width="TABLEWIDTH">
	</tag:tab>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Expertise Master&gt;&gt;Modify</td>
		</tr>
		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Expertise
			Name</td>
			<td width="50%" class="CONTROL"><html:text
				name="expertiseMstBean" property="strExpertiseName"
				onkeypress="return validateData(event,18);" maxlength="250"></html:text>
			</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> Effective From</td>
			<td class="CONTROL"><date:date name="strEffectiveFrom"
				value="${expertiseMstBean.strEffectiveFrom}"></date:date></td>
		</tr>
		<tr>
			<td class="LABEL">Remarks</td>
			<td width="50%" class="CONTROL"><html:textarea rows="2"
				name="expertiseMstBean" property="strRemarks"></html:textarea>
		</tr>
		<tr>
			<td class="LABEL">Record Status</td>
			<td width="50%" class="CONTROL"><html:radio
				name="expertiseMstBean" property="strIsValid" value="1">Active</html:radio>
			<html:radio name="expertiseMstBean" property="strIsValid" value="2">Inactive</html:radio>
			</td>
		</tr>


	</table>

	<table class="TABLEWIDTH" border="0" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="FOOTER">
			<td colspan="2"><font color="red">* </font> Mandatory Field</td>
		</tr>
	</table>
	<div align="center"><img style="cursor: pointer;"
		src="../../hisglobal/images/btn-sv.png" title="Save Record"
		onClick=" return validateUpdate();" /><img style="cursor: pointer;"
		src="../../hisglobal/images/btn-clr.png" title="Reset Content"
		onClick="document.forms[0].reset(), clearMsg('MODIFY');" /><img
		style="cursor: pointer;" src="../../hisglobal/images/btn-ccl.png"
		title="Cancel Process" onClick="cancel('LIST');" /></div>
	<input type="hidden" name="hmode" value="" />

	<input type="hidden" name="strSkillId"
		value="${expertiseMstBean.strSkillId}" />
	<input type="hidden" name="strHospitalCode"
		value="${expertiseMstBean.strHospitalCode}" />
	<input type="hidden" name="comboValue"
		value="${expertiseMstBean.comboValue}" />
	<input type="hidden" name="strCtDate"
		value="${expertiseMstBean.strCtDate}" />
	<input type="hidden" name="strChk" value="${expertiseMstBean.strChk}" />
	<cmbPers:cmbPers />
</html:form>

</body>
</html>


