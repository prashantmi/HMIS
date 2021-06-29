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
<title>Modify Non Item Maintenance Master...</title>

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
			<td colspan="4">&nbsp;Non Item Maintenance Master &gt;&gt;
			Modify</td>
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
		<tr>
			<td class="LABEL">Landmark Description</td>
			<td class="CONTROL" colspan="3"><html:textarea
				property="strLandmarkDesc" name="nonItemMaintenanceMstFB" cols="20"
				rows="2"></html:textarea></td>
		</tr>
		--%>
		<tr>
			<td class="LABEL"><font color="red">*</font>&nbsp;Non-Item Name</td>
			<td class="CONTROL">${nonItemMaintenanceMstFB.strNonItemName}</td>
			<td class="LABEL"><font color="red">*</font>Landmark Description</td>
			<td class="CONTROL"><html:textarea
				property="strLandmarkDesc" name="nonItemMaintenanceMstFB" cols="20"
				rows="2"></html:textarea></td>
		</tr>
		</table>

	<%-- Warranty Details Start--%>
	<%-- 
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<thead>
			<tr class="TITLE">
				<td align="center" style="width: 5%"><img style="cursor: pointer;"
					id="imgWarrantyDetails" src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
					onclick="showOrHideWarrantyDetails('SHOW',this)" title="Show"/></td>
				<td colspan="5" style="width: 95%">Warranty Details</td>
			</tr>
		</thead>
		<tbody id="tbodyWarrantyDetailsHead" style="display: none;">
			<tr>
				<td class="LABEL" style="text-align: center; width: 5%">&nbsp;</td>
				<td class="LABEL" style="text-align: center; width: 19%">Supplier
				Name</td>
				<td class="LABEL" style="text-align: center; width: 19%">Warranty
				Start Date/Upto</td>
				<td class="LABEL" style="text-align: center; width: 19%">Extention
				Upto</td>
				<td class="LABEL" style="text-align: center; width: 19%">Term
				&amp; Condition</td>
				<td class="LABEL" style="text-align: center; width: 19%">File</td>
			</tr>
			</tbody>
			<tbody id="tbodyWarrantyDetailsBody" style="display: none;">
			<tr>
				<td class="LABEL" style="text-align: center;"><input
					type="radio" name="strWarrantyDetails" /></td>
				<td class="CONTROL" style="text-align: center;">ACC</td>
				<td class="CONTROL" style="text-align: center;">20-05-2009/1</td>
				<td class="CONTROL" style="text-align: center;">2 Year</td>
				<td class="CONTROL" style="text-align: center;">It will
				be......</td>
				<td class="CONTROL" style="text-align: center;"><a
					href="http://www.google.co.in/">Document 1 Hyperlink</a></td>
			</tr>
			<tr>
				<td class="LABEL" style="text-align: center;"><input
					type="radio" name="strWarrantyDetails" /></td>
				<td class="CONTROL" style="text-align: center;">Option</td>
				<td class="CONTROL" style="text-align: center;">20-02-2010/1</td>
				<td class="CONTROL" style="text-align: center;">6 Month</td>
				<td class="CONTROL" style="text-align: center;">Machines
				are...........</td>
				<td class="CONTROL" style="text-align: center;"><a
					href="http://www.cdacnoida.in">Document 2 Hyperlink</a></td>
			</tr>
		</tbody>
	</table>
	--%>
	<%-- Warranty Details End--%>


	<%-- Maintenance Contract Details Start--%>
	<%-- 
	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<thead>
			<tr class="TITLE">
				<td align="center" style="width: 5%"><img style="cursor: pointer;"
					id="imgMaintenanceContractDetails"
					src="/HEMMS_ODISHA/hisglobal/images/plus.gif" onclick="showOrHideMaintenanceContractDetails('SHOW',this);" title="Show" /></td>
				<td colspan="5" style="width: 95%">Maintenance Contract Details</td>
			</tr>
		</thead>
		<tbody id="tbodyMaintenanceContractDetailsHead" style="display: none;">
			<tr>
				<td class="LABEL" style="text-align: center; width: 5%">&nbsp;</td>
				<td class="LABEL" style="text-align: center; width: 19%">Supplier
				Name</td>
				<td class="LABEL" style="text-align: center; width: 19%">Start
				Date</td>
				<td class="LABEL" style="text-align: center; width: 19%">End
				Date</td>
				<td class="LABEL" style="text-align: center; width: 19%">Tern
				&amp; Condition</td>
				<td class="LABEL" style="text-align: center; width: 19%">File</td>
			</tr>
		</tbody>
		<tbody id="tbodyMaintenanceContractDetailsBody" style="display: none;">	
			<tr>
				<td class="LABEL" style="text-align: center;"><input
					type="radio" name="strMaintenanceContractDetails" /></td>
				<td class="CONTROL" style="text-align: center;">TATA</td>
				<td class="CONTROL" style="text-align: center;">12-02-2011</td>
				<td class="CONTROL" style="text-align: center;">12-02-2012</td>
				<td class="CONTROL" style="text-align: center;">It will be
				..............</td>
				<td class="CONTROL" style="text-align: center;"><a
					href="http://www.google.co.in/">Document 1 Hyperlink</a></td>
			</tr>
			<tr>
				<td class="LABEL" style="text-align: center;"><input
					type="radio" name="strMaintenanceContractDetails" /></td>
				<td class="CONTROL" style="text-align: center;">Logitech</td>
				<td class="CONTROL" style="text-align: center;">12-05-2010</td>
				<td class="CONTROL" style="text-align: center;">12-05-2011</td>
				<td class="CONTROL" style="text-align: center;">It will be
				..............</td>
				<td class="CONTROL" style="text-align: center;"><a
					href="http://www.cdacnoida.in">Document 2 Hyperlink</a></td>
			</tr>
		</tbody>
	</table>
	--%>
	<%-- Maintenance Contract Details End--%>

	<table class="TABLEWIDTH" align="center" cellspacing="1px"
		cellpadding="1px">
		<tr>
			<td class="LABEL" style="width: 25%"><font color="red">*</font>&nbsp;Department</td>
			<td class="CONTROL" style="width: 25%">${nonItemMaintenanceMstFB.strDeptName}</td>
			<td class="LABEL" style="width: 25%">Maintenance Name</td>
			<td class="CONTROL" style="width: 25%">${nonItemMaintenanceMstFB.strMaintenanceName}</td>
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
			<td class="CONTROL" style="text-align: center;" width="47%"><select
				name="strLeftTaskId" size="8" multiple style="width: 250px">
				<bean:write name="nonItemMaintenanceMstFB"
					property="strLeftTaskIdOptions" filter="false" />
			</select></td>
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
			<td class="CONTROL" style="text-align: center;"><select
				name="strRightTaskId" size="8" multiple style="width: 250px">
				<bean:write name="nonItemMaintenanceMstFB"
					property="strRightTaskIdOptions" filter="false" />
			</select></td>
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
				name="nonItemMaintenanceMstFB" property="strPreferTimeFrom"
				styleClass="txtFldMin" maxlength="5"
				onchange="return validateDataWithSpecialChars(event,5,':');"
				onkeypress="return validateDataWithSpecialChars(event,5,':');"></html:text>&nbsp;[HH:MM] 24hr</td>
			<td class="LABEL" style="width: 25%"><font color="red">*</font>Preferred
			Time To</td>
			<td class="CONTROL" style="width: 25%;"><html:text
				name="nonItemMaintenanceMstFB" property="strPreferTimeTo"
				styleClass="txtFldMin" maxlength="5"
				onchange="return validateDataWithSpecialChars(event,5,':');"
				onkeypress="return validateDataWithSpecialChars(event,5,':');"></html:text>&nbsp;[HH:MM] 24hr</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Maintenance Period</td>
			<td class="CONTROL"><html:text name="nonItemMaintenanceMstFB"
				property="strMaintenancePeriod" styleClass="txtFldMin" maxlength="3"
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
				name="nonItemMaintenanceMstFB" property="strAlertPeriod"
				styleClass="txtFldMin" maxlength="3"
				onchange="return validateData(event,5);"
				onkeypress="return validateData(event,5);"></html:text>&nbsp;Days</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Effective From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${nonItemMaintenanceMstFB.strEffectiveFrom}"></dateTag:date></td>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><html:textarea
				name="nonItemMaintenanceMstFB" property="strRemarks" cols="20"
				rows="2"></html:textarea></td>
		</tr>
		<tr>
			<td class="LABEL">Record Status</td>
			<td class="CONTROL" colspan="3"><html:radio
				name="nonItemMaintenanceMstFB" property="strIsValid" value="1">Active</html:radio>&nbsp;&nbsp;&nbsp;&nbsp;<html:radio
				name="nonItemMaintenanceMstFB" property="strIsValid" value="2">Inactive</html:radio></td>
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
				onClick="return validate('update');" style="cursor: pointer;" /> <img
				src="../../hisglobal/images/btn-clr.png"
				onClick="resetForm('initializeModify');" style="cursor: pointer;"
				title="Clear Content" /> <img
				src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');"
				style="cursor: pointer;" title="Cancel Process"></td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />
	<html:hidden name="nonItemMaintenanceMstFB"
		property="strEngineeringItemTypeId" />
	<html:hidden name="nonItemMaintenanceMstFB"
		property="strEngineeringItemSubTypeId" />
	<html:hidden name="nonItemMaintenanceMstFB" property="strDeptId" />
	<html:hidden name="nonItemMaintenanceMstFB" property="strNonItemId" />
	<html:hidden name="nonItemMaintenanceMstFB" property="strMaintenanceId" />
	<html:hidden name="nonItemMaintenanceMstFB" property="strCurrentDate" />
	<cmbPers:cmbPers />
</html:form>

</body>
</html>