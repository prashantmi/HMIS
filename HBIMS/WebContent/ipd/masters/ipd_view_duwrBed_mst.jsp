<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>

<html>
<head>
<meta charset=utf-8>
<title>DUWR Bed Master view Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

</head>
<body >
<html:form action="/masters/CNTDUWRBedMst">
	<div class="normalMsg" id="normalMsg"></div>  
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td height="25" colspan="2">DUWR Bed Master &gt;&gt; View</td>
		</tr>		
		<tr>			
			<td width="25%" class="LABEL" nowrap>Department/Unit Name</td>
			<td  class="CONTROL" >${duwrbedBean.strDeptUnitName}</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Ward Name</td>
			<td  class="CONTROL">${duwrbedBean.strWardName}</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Room Name</td>
			<td  class="CONTROL">${duwrbedBean.strRoomName}</td>
		</tr>		
		<tr>
			<td class="multiLabel" width="25%" colspan="2">Bed Name</td>
		</tr>
			<tr>
			<td  colspan="2">${duwrbedBean.strBedView}</td>
		</tr>		
		<tr>
		<td class="LABEL">Effective From</td>
			<td class="CONTROL" width ="25%">
			<div id="frDt1">${duwrbedBean.strEffectiveFrom}</div>	
		</tr>
		<tr>
			<td width="25%" class="LABEL">Record Status</td>
			<td class="CONTROL" >${duwrbedBean.strIsValid}</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2">&nbsp;</td>		
		</tr>		
	</table>
	<center>
		<!-- <img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick="window.close();" /> -->
		<br><a href="#" class="button" onClick="window.close();"><span class="cancel">Cancel</span></a>
	</center>		
	<input type="hidden" name="chk" value="${duwrbedBean.strDeptUnitCode}">
    <input type="hidden" name="hmode">	
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>