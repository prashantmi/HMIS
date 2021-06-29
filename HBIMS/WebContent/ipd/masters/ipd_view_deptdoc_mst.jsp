<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<html>
<head>
<meta charset=utf-8>
<title>Department Document Master View Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
</head>
<body >
<html:form action="/masters/CNTDeptDocumentMst">
<div class="normalMsg" id="normalMsg"></div>  	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td height="25" colspan="4">Department Document Details&gt;&gt; View</td>
		</tr>
		<tr>			
			<td colspan="1" width="25%" class="LABEL">Department Name</td>
			<td colspan="1" width="25%" class="CONTROL">${deptdocBean.strDeptName}</td>
			<td colspan="1" width="25%" class="LABEL">Document Name</td>
			<td colspan="1" width="25%" class="CONTROL">${deptdocBean.strDocumentName}</td>
		</tr>
		<tr>
			<td width="25%" class="multiLabel" colspan="1">Component Name</td>
		    <td width="25%" class="multiLabel" colspan="1">File Name</td>
			<td width="25%" class="multiLabel" colspan="1">Status</td>
		    <td width="25%" class="multiLabel" colspan="1">Remarks</td>
		</tr>
		<tr>
			<td class="multiControl" colspan="1">${deptdocBean.strComponentName}</td>
			<td class="multiControl" colspan="1">${deptdocBean.strComponentFile}</td>
			<td class="multiControl" colspan="1">${deptdocBean.strIsValid}</td>
			<td class="multiControl" colspan="1">${deptdocBean.strRemarks}</td>
		</tr>
		<tr>
		    <td colspan="1" width="25%" class="LABEL">Effective From</td>
			<td colspan="1" width="25%" class="CONTROL">
			<div id="frDt1">${deptdocBean.strEffectiveFrom}</div>	
		    <td colspan="1" width="25%" class="LABEL">Is Default</td>
			<td colspan="1" width="25%" class="CONTROL">
			${deptdocBean.strIsDefault}
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4">&nbsp;</td>		
		</tr>
	</table>
	<center><img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick="window.close();"/></center>
	<input type="hidden" name="chk" value="${deptdocBean.strDeptCode}">
    <input type="hidden" name="hmode">	
	
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>