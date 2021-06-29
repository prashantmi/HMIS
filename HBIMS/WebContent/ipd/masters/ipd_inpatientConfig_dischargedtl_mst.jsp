<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<head><meta charset="utf-8" />
<title>In Patient Configuration/Discharge Details</title>

<script type="text/javascript"><!--

function validate1()
{	
	retVal = false;
	var hisValidator = new HISValidator("inPatientConfigBean");
	
	hisValidator.addValidation("strDischargeCancellationTime", "req","Discharge Cancellation Time Validity  is a Mandatory Field");
	hisValidator.addValidation("strDischargeReprintCharge","req","Discharge Re-Print Charge is a Mandatory Field");
	hisValidator.addValidation("strDischargeReprintCharge","amount=5,2","Please Enter a Valid Discharge Re-Print Charge");
	/* hisValidator.addValidation("strDischargeTypeAbsconded","dontselect=0","Discharge Type Absconded is a Mandatory Field");
	hisValidator.addValidation("strDischargeTypeReferral","dontselect=0","Discharge Type Referral is a Mandatory Field");
	hisValidator.addValidation("strDischargeTypeTransfer","dontselect=0","Discharge Type Transfer is a Mandatory Field");
	hisValidator.addValidation("strDischargeTypeDeath","dontselect=0","Discharge Type Death is a Mandatory Field");
	*/
	
	
	/***For Unique Discharge Type Selection***/
	var normalDisType=document.getElementsByName("strNormalDischargeType")[0].value;
	var disTypeLAMA=document.getElementsByName("strDischargeTypeLAMA")[0].value;
	var disTypeAbsc=document.getElementsByName("strDischargeTypeAbsconded")[0].value;
	var disTypeReferal=document.getElementsByName("strDischargeTypeReferral")[0].value;
	var disTypeTransfer=document.getElementsByName("strDischargeTypeTransfer")[0].value;
	var disTypeDeath=document.getElementsByName("strDischargeTypeDeath")[0].value;
	
	var arr1=[normalDisType, disTypeLAMA, disTypeAbsc,disTypeReferal,disTypeTransfer,disTypeDeath];
	for(var i=0;i<arr1.length;i++)
	{
		if(i == arr1.length)
		break;	

		for(var j=0;j<arr1.length;j++)
	    	{
	    	    if((arr1[i] == arr1[j]) && (i!=j))
	    	    {
	    	        alert('Discharge Type Value Can Not Be Same.\nPlease Choose The Unique Option.');
	    	        return false;
	    	    }
	    	}
	}


	
		
	retVal = hisValidator.validate(); 
	if(retVal)
	{
		hisValidator.clearAllValidations();
		document.forms[0].selectedTab.value = "DISCHARGESAVE";
		document.forms[0].submit();
	}
	else
	{
		hisValidator.clearAllValidations();
		return false;
	}
}
function showName(obj)
{
	var val = obj.options[obj.selectedIndex].text;
	val = val.split('-')[1];
	if(obj.value != 0)
	{
		document.getElementById("nameDiv").innerHTML = val;
	}
	else
	{
		document.getElementById("nameDiv").innerHTML = "";
	}
}
function cancelFunction()
{
	document.forms[0].selectedTab.value = "INITIALPAGE";
	document.forms[0].submit();		
}
	--></script>

</head>
<div class="normalMsg" id="normalMsg"></div>
<body onLoad="">
<html:form action="/masters/CNTInPatientConfigMst.cnt" method="post" >

<div class="normalMsg"><bean:write name="inPatientConfigBean" property="strMsg" /></div>

<tag:tab tabLabel="In Patient Configuration" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab> 
<tag:tab tabList="${inPatientConfigBean.lhm}" selectedTab="ipddischargedtls" align="center" width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr class="HEADER">
		<td colspan="2" height="22">In Patient Configuration</td>	
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Discharge Process</td>
		<td width="50%" class="CONTROL">
		<html:radio name="inPatientConfigBean" property="strDischargeProcessType" value="1">Online</html:radio>
		<html:radio name="inPatientConfigBean" property="strDischargeProcessType" value="2">Offline</html:radio>
		</td>
	</tr>	
</table>
	
<div id="genWardApproverId" style="display: none"></div>

	<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Permissible Discharge Time For Not Reported Patient After Leave(Hours)</td>
		<td class="CONTROL" width="50%"><input type="text" name="strNotReportedLeavehour" class="txtFldMin" maxlength="2" onkeypress="return validateData(event,5);" value="${inPatientConfigBean.strNotReportedLeavehour }"> 
		</td>			
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Discharge Cancellation Time</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strDischargeCancellationTime" value="${inPatientConfigBean.strDischargeCancellationTime }"
			class="txtFldMin" maxlength="3" onkeypress="return validateData(event,5);"> (in minutes)</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%"><font color="red">*</font>Discharge Re-Print Charge(<img src='../../hisglobal/images/INR.png'>)</td>
		<td class="CONTROL" width="50%">
		<input type="text" name="strDischargeReprintCharge" value="${inPatientConfigBean.strDischargeReprintCharge }"
			class="txtFldMin" maxlength="6" onkeypress="return validateData(event,7);"></td>
	</tr>
	
	<tr>
		<td class="LABEL" width="50%">Discharge Type Normal</td>
		<td class="CONTROL" width="50%">
		<select name="strNormalDischargeType"> 
		<bean:write name="inPatientConfigBean" property="strNormalDischargeTypeValues" filter="false" />
		</select></td>
	</tr>	
	
	<tr>
		<td class="LABEL" width="50%">Discharge Type LAMA</td>
		<td class="CONTROL" width="50%">
		<select name="strDischargeTypeLAMA"> 
		<bean:write name="inPatientConfigBean" property="strDischargeTypeLAMAValues" filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%">Discharge Type Absconded</td>
		<td class="CONTROL" width="50%">
		<select name="strDischargeTypeAbsconded">
			<bean:write name="inPatientConfigBean" property="strDischargeTypeAbscondedValues" filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%">Discharge Type Referral</td>
		<td class="CONTROL" width="50%">
		<select name="strDischargeTypeReferral">
			<bean:write name="inPatientConfigBean" property="strDischargeTypeReferralValues" filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%">Discharge Type Transfer</td>
		<td class="CONTROL" width="50%">
		<select name="strDischargeTypeTransfer">
			<bean:write name="inPatientConfigBean" property="strDischargeTypeTransferValues" filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%">Discharge Type Death</td>
		<td class="CONTROL" width="50%">
		<select name="strDischargeTypeDeath">
			<bean:write name="inPatientConfigBean" property="strDischargeTypeDeathValues" filter="false" />
		</select></td>
	</tr>
	<tr class="FOOTER">
		<td colspan="2"><font size="2" color="red">*</font> Mandatory Fields</td>
	</tr>
</table>

<table border="0" class="TABLEWIDTH" align="center">
	<tr>
		<!-- <td align="right">
		<img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer" onClick=" return validate1();" /></td>
		<td align="left">
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer" onClick="cancelFunc();" /></td>
		
		 -->	<td align="right">
		<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
		</td>
		<td align="left">
		<br><a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
		</td>
	</tr>
</table>

<input type="hidden" name="selectedTab"> 
<input type="hidden" name="hmode"></html:form>

<tag:autoIndex></tag:autoIndex></body>

</html>