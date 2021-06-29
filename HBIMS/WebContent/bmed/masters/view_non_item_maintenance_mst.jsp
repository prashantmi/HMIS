<%-- 
author 				: Aritra Kumar Dhawa 
Date of Creation 	: 18-Jan-2011
Process 			: Non Item Maintenance Master
Module 				: BMED
TL 					: Mr. A. S. Cheema
Description 		: This Process is used to modify the Non-Item Maintenance Maintenance Details.
 --%>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
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
<title>Modify Non Item Maintenance Master...</title>

<!-- CSS Start  -->
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<!-- CSS End  -->

<!-- JavaScript Start -->
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>

<script language="JavaScript" src="../js/nonItemMaintenanceMaster.js"></script>
<!-- JavaScript End -->
</head>
<body>
<html:form action="/masters/NonItemMaintenanceMstCNT"
	name="nonItemMaintenanceMstFB"
	type="bmed.masters.controller.fb.NonItemMaintenanceMstFB">

	<center>
	<div class="errMsg" id="errMsg">${nonItemMaintenanceMstFB.strErrMsg}</div>
	<div class="warningMsg" id="warningMsg">${nonItemMaintenanceMstFB.strWarningMsg}</div>
	<div class="normalMsg" id="normalMsg">${nonItemMaintenanceMstFB.strNormalMsg}</div>

	<tag:tab tabLabel="Non Item Maintenance Master" selectedTab="FIRST"
		align="center" width="TABLEWIDTH" onlyTabIndexing="1"></tag:tab></center>


	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px" style="border: solid 1px #CC9966;">
		<tr class="HEADER">
			<td colspan="4">&nbsp;Non Item Maintenance Master &gt;&gt; View</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Engineering Item Type</td>
			<td class="CONTROL" width="25%">${nonItemMaintenanceMstFB.strEngineeringItemTypeName}</td>
			<td class="LABEL" width="25%">Engineering Item Sub Type</td>
			<td class="CONTROL" width="25%">${nonItemMaintenanceMstFB.strEngineeringItemSubTypeName}</td>
		</tr>
		<%-- 
		<tr>
			<td class="LABEL">Building Name</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strBuildingName}</td>
			<td class="LABEL">Block Name</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strBlockName}</td>
		</tr>
		<tr>
			<td class="LABEL">Floor Name</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strFloorName}</td>
			<td class="LABEL">Room Number</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strRoomNumber}</td>
		</tr>
		--%>
		<tr>
			<td class="LABEL">Non-Item Name</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strNonItemName}</td>
			<td class="LABEL">Landmark Description</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strLandmarkDesc}</td>
		</tr>
		
		<tr>
			<td class="LABEL">Department Name</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strDeptName}</td>
			<td class="LABEL">Maintenance Name</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strMaintenanceName}</td>
		</tr>
		

	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="0px"
		cellpadding="0px"
		style="border-collapse: collapse; border: solid 1px #CC9966;">
		<tr class="TITLE">
			<td colspan="2">&nbsp;Task Name List</td>
		</tr>
		<logic:iterate id="strTaskName" name="nonItemMaintenanceMstFB"
			indexId="nIndex" property="listStrTaskName">
			<tr>
				<td class="LABEL" style="border: solid 1px #CC9966;" width="25%">${nIndex+1}.&nbsp;</td>
				<td class="CONTROL" style="border: solid 1px #CC9966;">&nbsp;${strTaskName}</td>
			</tr>
		</logic:iterate>
	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px" style="border: solid 1px #CC9966;">
		<tr class="TITLE">
			<td colspan="4">&nbsp;Maintenance Details</td>
		</tr>
		<tr>
			<td class="LABEL" style="width: 25%">Preferred Time From</td>
			<td class="CONTROL" style="width: 25%">${nonItemMaintenanceMstFB.strPreferTimeFrom}</td>
			<td class="LABEL" style="width: 25%">Preferred Time To</td>
			<td class="CONTROL" style="width: 25%;">${nonItemMaintenanceMstFB.strPreferTimeTo}</td>
		</tr>
		<tr>
			<td class="LABEL">Maintenance Period</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strMaintenancePeriod}&nbsp;${nonItemMaintenanceMstFB.strMaintenancePeriodUnitName}</td>
			<td class="LABEL">Duration of alert before the Scheduled Days</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strAlertPeriod}&nbsp;Days</td>
		</tr>

	
		<tr>
			<td class="LABEL">Effective From Date</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strEffectiveFrom}</td>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strRemarks}</td>
		</tr>
		<tr>
			<td class="LABEL"  colspan="2">Record Status</td>
			<td class="CONTROL" colspan="2">${nonItemMaintenanceMstFB.strRecordStatusName}</td>
		</tr>

	</table>

	<table cellspacing="1px" cellpadding="1px" class="TABLEWIDTH"
		align="center" style="border: solid 1px #CC9966;">
		<tr class="FOOTER">
			<td>&nbsp;</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td align="center" colspan="2" width="25%"><img
				src="../../hisglobal/images/close_tab.png" onClick="window.close();"
				style="cursor: pointer;" title="Close Window."></td>
		</tr>
	</table>
</html:form>

</body>
</html>