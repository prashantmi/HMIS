<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head>
<meta charset=utf-8>
<title>Day End Process</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../billing/js/DayEndTrans.js"></script>
</head>

<body>
<html:form action="transactions/DayEndTransCNT.cnt"
	name="dayEndTransBean" type="billing.transactions.DayEndTransFB"
	method="post">

	<div id="errMsg" class="errMsg"><bean:write name="dayEndTransBean" property="strErrMsg" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="dayEndTransBean" property="strWarning"/></div>
    <div id="normalMsg" class="normalMsg"><bean:write  name="dayEndTransBean" property="strMsg"/></div>
 

	<tag:tab tabLabel="Day End" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
	<table class="TABLEWIDTH" align="center">
		<tr class="HEADER">
			<td colspan="2">Day End Process</td>
		</tr>
		<tr>
			<td class="LABEL">Cashier</td>
			<td width="50%" class="CONTROL"><bean:write
				name="dayEndTransBean" property="strUserName" filter="false" /></td>
		</tr>
		<tr>
			<td class="LABEL">Counter Name</td>
			<td width="50%" class="CONTROL"><bean:write
				name="dayEndTransBean" property="strCounterName" filter="false" /></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Session</td>
			<td class="CONTROL"><select name="strSession">
				<option value="0" selected>Select</option>
				<option value="1">I</option>
				<option value="2">II</option>
				<option value="3">III</option>
				<option value="4">IV</option>
				<option value="5">V</option>
				<option value="6">VI</option>
			</select></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Session Timings
			(HH24:MM)</td>
			<td width="50%" class="CONTROL"><input type="text"
				class="txtFldSmall" maxlength="2" name="strSessionTiming1" value=""
				onkeyup="hour(this,event),focusTiming1(this);"
				onkeypress="return validateData(event,5);">:<input
				type="text" class="txtFldSmall" maxlength="2"
				name="strSessionTiming2" value=""
				onkeyup="minute(this,event),focusTiming2(this);"
				onkeypress="return validateData(event,5);">==<input
				type="text" class="txtFldSmall" maxlength="2"
				name="strSessionTiming3" value=""
				onkeyup="hour(this,event),focusTiming3(this);"
				onkeypress="return validateData(event,5);">:<input
				type="text" class="txtFldSmall" maxlength="2"
				name="strSessionTiming4" value="" onkeyup="minute(this,event);"
				onkeypress="return validateData(event,5);">
		</tr>
		<tr>
			<td width="50%" class="LABEL" align="center">Remarks (If Any)</td>
			<td width="50%" class="CONTROL"><textarea name="strRemarks"></textarea></td>
		</tr>
		
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat" class="comboSmall" onchange=""><option value="html">Html</option><option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="dayEndTransBean" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			User Remarks
			</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>
			
		</tr>
		
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font>Mandatory
			Fields</td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center"><!-- <img
				style="cursor: pointer" src="../../hisglobal/images/btn-sv.png"
				name="save" onclick="return validateFunc();"> <img
				style="cursor: pointer" src="../../hisglobal/images/btn-clr.png"
				name="clearImg" onclick="resetPage();"> <img name="cancel"
				 	src="../../hisglobal/images/btn-ccl.png" onclick="cancel();">
				 	-->
				 	<br>
				<a href="#" class="button" id="" onclick=' return validateFunc();'><span class="save">Save</span></a>
				<a href="#" class="button"	onclick="resetPage();"><span class="clear">Clear</span></a> 
				<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>	
				 	</td>


		</tr>
	</table>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="userId" value="${dayEndTransBean.strUserId}">
	<input type="hidden" name="strCounterId"
		value="${dayEndTransBean.strCounterId}">
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>