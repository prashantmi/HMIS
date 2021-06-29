<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<!-- 
/**
 * @author Vivek Aggarwal
 * Date of Creation : 20-Jan-2011
 * Date of Modification :  25-Jan-2011 
 * Version : 
 * Module  : BMED
 */
 -->
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
<title>Maintenance Master Add Page</title>

<link href="../../hisglobal/css/emms.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tabIndex.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../bmed/js/maintenanceMst.js"></script>
</head>

<body class="background">
<html:form name="maintenanceBean" action="/masters/MaintenanceMstCNT"
	type="bmed.masters.controller.fb.MaintenanceMstFB" styleClass="formbg">

	<div id="strErrMsg" class="errMsg"><bean:write
		name="maintenanceBean" property="strErrMsg" /></div>
	<div class="warningMsg"><bean:write name="maintenanceBean"
		property="strWarningMsg" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="maintenanceBean" property="strNormalMsg" /></div>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Maintenance Master&gt;&gt;Add</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Engineering Item
			Type</td>

			<td class="CONTROL" colspan="2">
			<div id="LeftUserDivId" align="left"><select
				class='comboNormal' name="strEngineeringItemTypeId"
				onChange="getEnggItemSubTypeCmb()">
				<bean:write name="maintenanceBean"
					property="strEngineeringItemTypeCmb" filter="false" />
			</select></div>
			</td>
		</tr>


		<tr>
			<td class="LABEL"><font color="red">*</font>Engineering Item Sub
			Type</td>

			<td class="CONTROL" colspan="2">
			<div id="enggItemSubTypeCmbDivId" align="left"><select
				class='comboNormal' name="strEngineeringItemSubTypeId">
				<bean:write name="maintenanceBean"
					property="strEngineeringItemSubCmb" filter="false" />
			</select></div>
			</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Maintenance Name</td>
			<td width="50%" class="CONTROL"><input type="text"
				name="strMaintenanceName" value="" maxlength="100"
				onkeypress="return validateData(event,18);"></td>
		</tr>


		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Effective
			From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${maintenanceBean.strCtDate}"></dateTag:date></td>
		</tr>

		<tr>
			<td class="LABEL">
			<div align="right">Remarks</div>
			</td>
			<td class="CONTROL">
			<div align="left"><textarea name="strRemarks" rows="2"></textarea>
			</div>
			</td>
		</tr>

	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<!--	<table cellspacing="1px" class="TABLEWIDTH" align="center">-->
			<tr class="FOOTER">
				<td></td>
			</tr>
		</table>
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="return validate1();" /> <img
				src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer; cursor: hand;" title="Reset Content"
				onClick="document.forms[0].reset(),clearMsg('ADD');" /> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();" /></td>
		</tr>
	</table>
		<!--
		
		<div class="legends"><font size="2" color="red">*</font>
		Mandatory Fields</div>
	


	<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div><a href="#" class="button" onClick="validate1();"><span
					class="save">Save</span></a> <a href="#" class="button"
					onClick="document.forms[0].reset(),clearMsg('ADD');"><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div>
		
	
	--><input type="hidden" name="hmode" />



	<cmbPers:cmbPers />
</html:form>

</body>
</html>
