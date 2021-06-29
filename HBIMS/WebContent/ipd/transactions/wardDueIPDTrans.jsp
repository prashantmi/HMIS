
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head><meta charset="utf-8" />
<title>Ward Due Details</title>
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../ipd/js/WardDue.js"></script>

</head>
<body>
<html:form action="/transactions/WardDueIPDTransCNT">
	<center>
	<div id="errMsg" class="errMsg"><bean:write name="wardDueIPDTransBean" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write name="wardDueIPDTransBean" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="wardDueIPDTransBean" property="strMsg" /></div>
	</center>
	<tag:tab tabLabel="Ward Due Details" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellspacing="0" id='divHeader'>
		<tr class="HEADER">
			<td colspan="3" width="100%" nowrap="nowrap">Ward Due Details</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Department</td>
			<td width="25%" class="CONTROL"><select name="strDept" onchange="getUnitCombo()" class="comboNormal">
				<bean:write name="wardDueIPDTransBean" property="strDeptValue" filter="false"></bean:write>
			</select></td>
			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL">
			<div id="divUnit"><select name="strUnit" class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Ward</td>
			<td width="25%" class="CONTROL">
			<div id="divWard">
			<select name="strWard" class="comboNormal"><option value="0">Select Value</option>
			</select></div>
			</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
	</table>
	<div id="divPatientDtl" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="TITLE" colspan="5">
			<div id="divPatientPlusID" style="display: none;" align="left">
			<img src="../../hisglobal/images/plus.gif"
				onclick="showDiv('divPatientMinusID'),hideDiv('divPatientPlusID'),showDiv('divPatientList');"
				style="cursor: pointer;">&nbsp;&nbsp;Patient List</div>
			<div id="divPatientMinusID" style="display: block;" align="left">
			<img src="../../hisglobal/images/minus.gif"
				onclick="hideDiv('divPatientMinusID'),hideDiv('divPatientList'),showDiv('divPatientPlusID');"
				style="cursor: pointer;">&nbsp;&nbsp;Patient List</div>
			</td>
		</tr>
	</table>
	<div id="divPatientList"></div>
	</div>	
	<div id="divPatientDueDtl" style="display: none;">
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="TITLE" colspan="5">
			<div id="divPatientDuePlusID" style="display: none;" align="left">
			<img src="../../hisglobal/images/plus.gif"
				onclick="showDiv('divPatientDueMinusID'),hideDiv('divPatientDuePlusID'),showDiv('divPatientDueList');"
				style="cursor: pointer;">&nbsp;&nbsp;Patient Due List</div>
			<div id="divPatientDueMinusID" style="display: block;" align="left">
			<img src="../../hisglobal/images/minus.gif"
				onclick="hideDiv('divPatientDueMinusID'),hideDiv('divPatientDueList'),showDiv('divPatientDuePlusID');"
				style="cursor: pointer;">&nbsp;&nbsp;Patient Due List</div>
			</td>
		</tr>
	</table>
	<div id="divPatientDueList"></div>
	</div>	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr class="FOOTER"><td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td></tr>
	</table>
	<div id="divSaveCancelId">
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer;"
				title="Save Record" onClick="saveData()" /> <img
				src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer;"
				title="Cancel Process" onClick="buttonClick('INITIALPAGE')"></td>
		</tr>
	</table>
	</div>
	<input type="hidden" name="hmode" />
	<cmbPers:cmbPers />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>