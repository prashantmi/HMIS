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
<title>Item Maintenance</title>
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
<script language="Javascript" src="../../bmed/js/itemMaintenance_mst.js"></script>

</head>
<body>
<html:form action="/masters/ItemMaintenanceMstCNT"
	name="itemMaintenanceMstBean"
	type="bmed.masters.controller.fb.ItemMaintenanceMstFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="itemMaintenanceMstBean" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="itemMaintenanceMstBean" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="itemMaintenanceMstBean" property="strMsgString" /></div>


	<tag:tab tabLabel="Item Maintenance" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab></center>

	<table class="TABLEWIDTH" align="center" cellpadding='1px'
		cellspacing='1px' border='0'>
		<tr class="HEADER">
			<td colspan="4">Item Maintenance&gt;&gt;View</td>
		</tr>
		<tr>
		<tr>
			<td colspan="1" class="LABEL">Department/Store Name</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strStoreName" filter="false" />

			</td>
			<td colspan="1" class="CONTROL"></td>
			<td colspan="1" class="CONTROL"></td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL">Engineering Item Type</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strEnggItemTypeCmb"
				filter="false" /></td>
			<td colspan="1" class="LABEL">Engineering Item Sub-Type</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strEnggItemSubTypeCmb"
				filter="false" /></td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL">Item Name</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strItemNameCmb"
				filter="false" /></td>
			<td colspan="1" class="LABEL">Batch No</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strBatchNoCmb"
				filter="false" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL">Item Serial No</td>
			<td colspan="1" class="CONTROL">
			<div id="itemSlNoId"><bean:write name="itemMaintenanceMstBean"
				property="strSerialNoCmb" filter="false" /></div>

			</td>
			<td colspan="1" class="LABEL">Maintenance Name</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strMaintenanceCmb"
				filter="false" /></td>
		</tr>

		<tr>

			<td colspan="2" class="LABEL">Maintenance Desc</td>
			<td colspan="2" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strMaintenanceDesc"
				filter="false" /></td>
		</tr>


	</table>

	<table border="0" class="TABLEWIDTH" align="center" cellpadding='1px'
		cellspacing='1px' border='0'>
		<tr class="TITLE">
			<td colspan="9" align='left'>Task Name List</td>
		</tr>
		<tr>
	</table>


	<bean:write name="itemMaintenanceMstBean" property="strTaskHlp"
		filter="false" />


	<table border="0" class="TABLEWIDTH" align="center" cellpadding='1px'
		cellspacing='1px' border='0'>
		<tr class="TITLE">
			<td colspan="4" align='left'>Maintenance Details</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL">Preferred Time From</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strPreferTimeFrom"
				filter="false" /></td>
			<td colspan="1" class="LABEL">Preferred Time To</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strPreferTimeTo"
				filter="false" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL">Maintenance Period/Unit</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strMaintenancePeriod"
				filter="false" /></td>
			<td colspan="1" class="LABEL">Duration of alert before the
			Scheduled Days/Unit</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strMaintenancePeriodInDays"
				filter="false" /></td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">
			<div align="right">Effective From</div>
			</td>
			<td class="CONTROL"><bean:write name="itemMaintenanceMstBean"
				property="strEffectiveFrom" filter="false" /></td>

			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strRemarks" filter="false" />
			</td>
		</tr>
		<tr>


			<td colspan="2" class="LABEL">Status</td>
			<td colspan="2" class="CONTROL"><bean:write
				name="itemMaintenanceMstBean" property="strIsValid" filter="false" />
			</td>
		</tr>

	</table>

	<table border="0" class="TABLEWIDTH" align="center" cellpadding='1px'
		cellspacing='1px' border='0'>
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="blue">*</font>
			Reserved/Branded Item</td>
		</tr>
	</table>

	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center"><img style="cursor: pointer;"
				src="../../hisglobal/images/btn-ccl.png" onClick="window.close();" />

			</td>
		</tr>
	</table>
	<input type="hidden" name="hmode" />

	<input type="hidden" name="strTempCmbValue"
		value="${itemMaintenanceMstBean.strTempCmbValue}">
	<input type="hidden" name="strChk"
		value="${itemMaintenanceMstBean.strChk}">
	<input type="hidden" name="comboValue"
		value="${itemMaintenanceMstBean.comboValue}">
	<input type="hidden" name="strStoreId"
		value="${itemMaintenanceMstBean.strStoreId}">
	<input type="hidden" name="strStoreIdValue"
		value="${itemMaintenanceMstBean.strStoreIdValue}">

	<cmbPers:cmbPers />
</html:form>

</body>
</html>