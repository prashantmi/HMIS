<%-- 
author 				: Aritra Kumar Dhawa 
Date of Creation 	: 13-Jan-2011
Process 			: Non Item Maintenance Master
Module 				: BMED
TL 					: Mr. A. S. Cheema
Description 		: This Process is used to add the Non-Item Maintenance Maintenance Details.
 --%>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

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

<title>Add Non Item Maintenance Master...</title>

<!-- CSS Start  -->
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<!-- CSS End  -->

<!-- JavaScript Start -->
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../js/nonItemMaintenanceMaster.js"></script>
<script language="JavaScript" src="../js/maintenance_warranty_contract.js"></script>
<!-- JavaScript Start -->
</head>
<body>
<html:form action="/masters/NonItemMaintenanceMstCNT"
	name="nonItemMaintenanceMstFB"
	type="bmed.masters.controller.fb.NonItemMaintenanceMstFB">

	<center>
	<div class="errMsg" id="errMsg"><bean:write
		name="nonItemMaintenanceMstFB" property="strErrMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="nonItemMaintenanceMstFB" property="strWarningMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="nonItemMaintenanceMstFB" property="strNormalMsg" /></div>

	</center>


	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="HEADER">
			<td colspan="4">&nbsp;Non Item Maintenance Contract Master
			&gt;&gt; Add</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Engineering Item Type</td>
			<td class="CONTROL" width="25%">${nonItemMaintenanceMstFB.strEngineeringItemTypeName}</td>
			<td class="LABEL" width="25%">Engineering Item Sub Type</td>
			<td class="CONTROL" width="25%">${nonItemMaintenanceMstFB.strEngineeringItemSubTypeName}</td>
		</tr>
		<%--
		Inactivated according to LLD Version 2.0 
		--%>
		<%-- 
		<tr>
			<td class="LABEL">Building Name</td>
			<td class="CONTROL"><select name="strBuildingCode"
				class="comboNormal" onchange="setBlockOptions();">
				<bean:write name="nonItemMaintenanceMstFB"
					property="strBuildingCodeOptions" filter="false" />
			</select></td>
			<td class="LABEL">Block Name</td>
			<td class="CONTROL" id="BlockDivId"><select name="strBlockCode"
				class="comboNormal">
				<option value="0">All</option>
			</select></td>
		</tr>
		<tr>
			<td class="LABEL">Floor Name</td>
			<td class="CONTROL" id="FloorDivId"><select name="strFloorId"
				class="comboNormal">
				<option value="0">All</option>
			</select></td>
			<td class="LABEL">Room Number</td>
			<td class="CONTROL" id="RoomDivId"><select name="strRoomId"
				class="comboNormal">
				<option value="0">All</option>
			</select></td>
		</tr>
		--%>
		<tr>
			<td class="LABEL"><font color="red">*</font>&nbsp;Non-Item Name</td>
			<td class="CONTROL"><select name="strNonItemId"
				class="comboNormal" onchange="ajaxCallGetData('strDisplayMode', 'strProcessMode', this.value);">
				<bean:write name="nonItemMaintenanceMstFB"
					property="strNonItemOptions" filter="false" />
			</select></td>
			<td class="LABEL"><font color="red">*</font>Landmark Description</td>
			<td class="CONTROL"><html:textarea property="strLandmarkDesc"
				cols="20" rows="2"></html:textarea></td>
		</tr>
	</table>

	<%-- Warranty Details Start--%>
	<jsp:include page="/bmed/global/warranty_details.jsp"></jsp:include>
	<%-- Warranty Details End--%>


	<%-- Maintenance Contract Details Start--%>
	<jsp:include page="/bmed/global/maintenance_contract_details.jsp"></jsp:include>
	<%-- Maintenance Contract Details End--%>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="LABEL" style="width: 25%"><font color="red">*</font>&nbsp;Department Name</td>
			<td class="CONTROL" style="width: 25%"><select name="strDeptId"
				class="comboMax" onchange="setMaintenanceOptions()">
				<bean:write name="nonItemMaintenanceMstFB"
					property="strDeptIdOptions" filter="false" />
			</select></td>
			<td class="LABEL" style="width: 25%"><font color="red">*</font>Maintenance
			Name</td>
			<td class="CONTROL" id="MaintenanceDivId" style="width: 25%"><select
				name="strMaintenanceId" class="comboNormal"
				onchange="setTaskOptions();">
				<option value="0">Select Value</option>
			</select></td>
		</tr>

	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="TITLE">
			<td colspan="3">&nbsp;<font color="red">*</font>Task Name</td>
		</tr>
		<tr>
			<td class="LABEL" style="text-align: center;" width="47%">Available
			Task</td>
			<td class="LABEL" width="6%">&nbsp;</td>
			<td class="LABEL" style="text-align: center;" width="47%">Selected
			Task</td>
		</tr>
		<tr>
			<td class="CONTROL">
			<div id="LeftTaskDivId" align="center"><select
				name="strLeftTaskId" size="8" multiple style="width: 250px"></select></div>
			</td>
			<td class="LABEL">
			<center><img src="../../hisglobal/images/forward3.gif"
				onclick='LeftListTransfer	(									);'></center>
			<center><img src="../../hisglobal/images/forwardward.gif"
				onClick='shiftAllToRight	("strLeftTaskId","strRightTaskId",1	);'></center>
			<center><img src="../../hisglobal/images/backward.gif"
				onClick='shiftAllToLeft		("strLeftTaskId","strRightTaskId",1	);'></center>
			<center><img src="../../hisglobal/images/back3.gif"
				onClick='shiftToLeft		("strLeftTaskId","strRightTaskId",1	);'></center>
			</td>
			<td class="CONTROL">
			<div id="RightTaskDivId" align="center"><select
				name="strRightTaskId" size="8" multiple style="width: 250px"></select></div>
			</td>
		</tr>


	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr class="TITLE">
			<td colspan="4">&nbsp;Maintenance Details</td>
		</tr>
		<tr>
			<td class="LABEL" style="width: 25%"><font color="red">*</font>Preferred
			Time From</td>
			<td class="CONTROL" style="width: 25%"><html:text
				property="strPreferTimeFrom" styleClass="txtFldMin" maxlength="5"
				onchange="return validateDataWithSpecialChars(event,5,':');"
				onkeypress="return validateDataWithSpecialChars(event,5,':');"></html:text>&nbsp;[HH:MM]
			24hr</td>
			<td class="LABEL" style="width: 25%"><font color="red">*</font>Preferred
			Time To</td>
			<td class="CONTROL" style="width: 25%;"><html:text
				property="strPreferTimeTo" styleClass="txtFldMin" maxlength="5"
				onchange="return validateDataWithSpecialChars(event,5,':');"
				onkeypress="return validateDataWithSpecialChars(event,5,':');"></html:text>&nbsp;[HH:MM]
			24hr</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Maintenance Period</td>
			<td class="CONTROL"><html:text property="strMaintenancePeriod"
				styleClass="txtFldMin" maxlength="3"
				onchange="return validateData(event,5);"
				onkeypress="return validateData(event,5);"></html:text></td>
			<td class="LABEL"><font color="red">*</font>Unit</td>
			<td class="CONTROL"><select name="strMaintenancePeriodUnitId"
				class="comboNormal">
				<bean:write name="nonItemMaintenanceMstFB"
					property="strMaintenancePeriodUnitIdOptions" filter="false" />
			</select></td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Duration of alert
			before the Scheduled Days</td>
			<td class="CONTROL" colspan="3"><html:text
				property="strAlertPeriod" styleClass="txtFldMin" maxlength="3"
				onchange="return validateData(event,5);"
				onkeypress="return validateData(event,5);"></html:text>&nbsp;Days</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Effective From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${nonItemMaintenanceMstFB.strCurrentDate}"></dateTag:date></td>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><html:textarea property="strRemarks"
				cols="20" rows="2"></html:textarea></td>
		</tr>

	</table>

	<table cellspacing="1px" cellpadding="1px" class="TABLEWIDTH"
		align="center">
		<tr class="FOOTER">
			<td><font size="2" color="red">*</font> Mandatory Fields</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td align="center" colspan="2" width="25%"><img
				src="../../hisglobal/images/btn-sv.png" title="Save Record"
				onClick="return validate('insert');" style="cursor: pointer;" /> <img
				src="../../hisglobal/images/btn-clr.png"
				onClick="resetForm('initializeAdd');" style="cursor: pointer;"
				title="Clear Content" /> <img
				src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');"
				style="cursor: pointer;" title="Cancel Process"></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<input type="hidden" value="${nonItemMaintenanceMstFB.strCurrentDate}"
		name="strCurrentDate" />
	<input type="hidden"
		value="${nonItemMaintenanceMstFB.strEngineeringItemTypeId}"
		name="strEngineeringItemTypeId" />
	<input type="hidden"
		value="${nonItemMaintenanceMstFB.strEngineeringItemSubTypeId}"
		name="strEngineeringItemSubTypeId" />

	<input type="hidden" name="strUploadFileId" value="" />		
		
	<cmbPers:cmbPers />

</html:form>

</body>
</html>