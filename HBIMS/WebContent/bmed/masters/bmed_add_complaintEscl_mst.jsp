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
<title>Complaint Escalation Master</title>
<link href="../../hisglobal/css/emms.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript"
	src="../../bmed/js/complaintEscalation_mst.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>


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
<body  class="background">
<html:form action="/masters/ComplaintEscalationMstCNT"
	name="complaintMstBean"
	type="bmed.masters.controller.fb.ComplaintEscalationMstFB" styleClass="formbg">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="complaintMstBean" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="complaintMstBean" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="complaintMstBean" property="strMsgString" /></div>


	<table class="TABLEWIDTH" align="center">
		<tr class="HEADER">
			<td colspan="4">Complaint Escalation Master&gt;&gt; Add</td>
		</tr>


	</table>


	<table border="0" class="TABLEWIDTH" align="center" border='0'
		cellspacing='1px' cellpadding='1px'>

		<tr>
			<td colspan="1" class="LABEL">Engineering Item Type</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="complaintMstBean" property="strEnggItemTypeCmb" filter="false" />

			</td>
			<td colspan="1" class="LABEL">Engineering Item Sub-Type</td>
			<td colspan="1" class="CONTROL"><bean:write
				name="complaintMstBean" property="strEnggItemSubTypeCmb"
				filter="false" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL"><font color="red">*</font>Level
			Type</td>
			<td colspan="1" class="CONTROL"><select name="strLevelType"
				class='comboMax' onChange="">
				<bean:write name="complaintMstBean" property="strLevelTypeCmb"
					filter="false" />
			</select></td>
			<td colspan="1" class="LABEL"><font color="red">*</font>Employee
			Name</td>
			<td colspan="1" class="CONTROL"><select name="strEmpNameId"
				class='comboMax' onChange="getEmpInformation();">
				<bean:write name="complaintMstBean" property="strEmpNameCmb"
					filter="false" />
			</select></td>
		</tr>
		<!-- Commented On 29-oct-2015 -->
		<!-- <tr>
			<td colspan="1" class="LABEL">Contact No</td>
			<td colspan="1" class="CONTROL">

			<div id='contactNoDiv'></div>

			</td>
			<td colspan="1" class="LABEL">E-Mail ID</td>
			<td colspan="1" class="CONTROL">
			<div id='emailIdDiv'></div>
			</td>
		</tr> -->


		<tr>
			<td colspan="1" class="LABEL"><font color="red">*</font>Period</td>
			<td colspan="1" class="CONTROL"><input type="text"
				class="txtFldNormal" name="strPeriod" maxlength="3" value=""
				onkeypress="return validateData(event,5);"></td>
			<td colspan="1" class="LABEL"><font color="red">*</font>Unit
			Name</td>
			<td colspan="1" class="CONTROL"><select name="strUnitId"
				class='comboNormal'>
				<bean:write name="complaintMstBean" property="strUnitIdCmb"
					filter="false" />
			</select></td>
		</tr>

		<tr>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL"><textarea name="strRemarks"
				cols="25" rows="2" onkeypress="return validateData(event,9);"></textarea></td>

			<td width="25%" class="CONTROL"></td>
			<td width="25%" class="CONTROL"></td>
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
				onClick="document.forms[0].reset();" /> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancel('LIST');" /></td>
		</tr>
	</table>
		
		<!--
		
		<div class="legends"><font size="2" color="red">*</font>
		Mandatory Fields</div>
		
		<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div><a href="#" class="button" onClick="return validate1();"><span
					class="save">Save</span></a> <a href="#" class="button"
					onClick="ResetPage();"><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancel('LIST');"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div>

	
	--><input type="hidden" name="hmode" />
	<input type="hidden" name="comboValue"
		value="${complaintMstBean.comboValue}">
	<input type="hidden" name="strChk" value="${complaintMstBean.strChk}">
	<input type="hidden" name="strContactNo"
		value="${complaintMstBean.strContactNo}">
	<input type="hidden" name="strEmailId"
		value="${complaintMstBean.strEmailId}">

	<cmbPers:cmbPers />
</html:form>

</body>
</html>