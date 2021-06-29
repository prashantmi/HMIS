<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<head><meta charset="utf-8" />
<title>In Patient Configuration/Print Details</title>

<script type="text/javascript">
			
function validate1()
{	
	retVal = false;
	var hisValidator = new HISValidator("inPatientConfigBean");
	hisValidator.addValidation("strNoOfLineInAdmissionSlip","req","No. Of Lines in Admission Slip is a Mandatory Field");
	hisValidator.addValidation("strNoOfLineInVisitorPassSlip","req","No. Of Lines in Visitor Pass Slip is a Mandatory Field");
	retVal = hisValidator.validate(); 
	if(retVal)
	{
				document.forms[0].selectedTab.value = "PRINTDTLSAVE";
				document.forms[0].submit();
	}
}	
function openDiv()
{
	if(document.forms[0].strIntegrationBilling[0].checked)
	{
		document.getElementById("billId").style.display="block";
	}
	else
	{
		document.getElementById("billId").style.display="none";
	}
}	
function cancelFunction()
{
	document.forms[0].selectedTab.value = "INITIALPAGE";
	document.forms[0].submit();		
}
</script>

</head>

<body onload="">
<html:form action="/masters/CNTInPatientConfigMst.cnt" method="post" >

<div class="normalMsg"><bean:write name="inPatientConfigBean"
	property="strMsg" /></div>

<tag:tab tabLabel="In Patient Configuration" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab> <tag:tab
	tabList="${inPatientConfigBean.lhm}" selectedTab="ipdprintdtls"
	align="center" width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">

	<tr class="HEADER">
		<td colspan="4" height="22">In Patient Configuration/Print Details</td>
	</tr>
	</table>
	<div id="billId">
	<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>No Of Lines in Admission Slip
		<td class="CONTROL" width="50%">
		<input type="text" name="strNoOfLineInAdmissionSlip" value="${inPatientConfigBean.strNoOfLineInAdmissionSlip}"
			class="txtFldMin" maxlength="2" onkeypress="return validateData(event,5);"> </td>			
	</tr>
	<tr style="display: none;">
		<td class="LABEL" width="50%"><font color="red">*</font>No Of Lines in New Born Baby Admission Slip
		<td class="CONTROL" width="50%">
		<input type="text" name="strNoOfLineInNewBornBabyAdmissionSlip" value="${inPatientConfigBean.strNoOfLineInNewBornBabyAdmissionSlip}"
			class="txtFldMin" maxlength="2" onkeypress="return validateData(event,5);"> </td>			
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>No Of Lines in Visitor Pass Slip
		<td class="CONTROL" width="50%">
		<input type="text" name="strNoOfLineInVisitorPassSlip" value="${inPatientConfigBean.strNoOfLineInVisitorPassSlip }"
			class="txtFldMin" maxlength="2" onkeypress="return validateData(event,5);"> </td>
	</tr>
	</table>
	</div>
		
<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr class="FOOTER">
		<td colspan="4"><font size="2" color="red">*</font> Mandatory Fields</td></tr>
</table>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>

		<!-- <td align="right">
		<img src="../../hisglobal/images/btn-sv.png" style="cursor:pointer" onClick=" return validate1();" /></td>
		<td align="left">
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick="cancelFunc();" /></td>
		 -->
		<td align="right">
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
		</td>
		<td align="left">
		<br><a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
		</td>
	</tr>
</table>

<input type="hidden" name="selectedTab"></html:form>
<tag:autoIndex></tag:autoIndex>
</body>

</html>