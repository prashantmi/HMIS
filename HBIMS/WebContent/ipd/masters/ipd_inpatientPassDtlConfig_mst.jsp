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

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<head><meta charset="utf-8" />
<title>In Patient Configuration/Pass Details</title>

<script type="text/javascript"><!--
			
function validate1()
{	
	retVal = false;
	var hisValidator = new HISValidator("inPatientConfigBean");
	
	hisValidator.addValidation("strNoOfFreePass", "req","No. of Free Pass is a Mandatory Field");
	hisValidator.addValidation("strNoOfFreePass", "numltet=10","No. of Free Pass Cannot be Greater than 10");
	hisValidator.addValidation("strNoOfPaidPass", "req","No. of Paid Pass is a Mandatory Field");
	hisValidator.addValidation("strNoOfPaidPass", "numltet=10","No. of Paid Pass Cannot be Greater than 10");
	hisValidator.addValidation("strNewFreePassValidity", "req","New Free Pass Validity is a Mandatory Field");
	hisValidator.addValidation("strNewPaidPassValidity", "req","New Paid Pass Validity is a Mandatory Field");
	
	hisValidator.addValidation("strPassReprintCharges", "req","Pass Re-Print Charge is a Mandatory Field");
	hisValidator.addValidation("strPassReprintCharges", "amount=5,2","Please Enter Valid Pass Re-Print Charge");
	
	
	
	hisValidator.addValidation("strPaidPassCharge", "req","Paid Pass Charge is a Mandatory Field");
	hisValidator.addValidation("strPaidPassCharge", "amount=5,2","Please Enter Valid Paid Pass Charge");
	
	hisValidator.addValidation("strRenewFreePassValidity", "req","Renew Free Pass Validity is a Mandatory Field");
	hisValidator.addValidation("strRenewPaidPassValidity", "req","Renew Paid Pass Validity is a Mandatory Field");
	hisValidator.addValidation("strPaidPassRenewCharge", "req","Paid Pass Renew Charge is a Mandatory Field");
	hisValidator.addValidation("strPaidPassRenewCharge", "amount=5,2","Please Enter Valid Paid Pass Renew Charge");
	
	
	hisValidator.addValidation("strSummerMorningFromHr", "req","Summer Morning From Hour is a Mandatory Field");
	hisValidator.addValidation("strSummerMorningFromHr", "numltet=23","Hour should be in 00-23 Format");
	hisValidator.addValidation("strSummerMorningFromMin", "req","Summer Morning From Min is a Mandatory Field");
	hisValidator.addValidation("strSummerMorningFromMin", "numltet=59","Minute should be in 00-59 Format");
	
	hisValidator.addValidation("strSummerMorningToHr", "req","Summer Morning To Hour is a Mandatory Field");
	hisValidator.addValidation("strSummerMorningToHr", "numltet=23","Hour should be in 00-23 Format");
	hisValidator.addValidation("strSummerMorningToMin", "req","Summer Morning To Min is a Mandatory Field");
	hisValidator.addValidation("strSummerMorningToMin", "numltet=59","Minute should be in 00-59 Format");
		
	hisValidator.addValidation("strSummerEveningFromHr", "req","Summer Evening From Hour is a Mandatory Field");
	hisValidator.addValidation("strSummerEveningFromHr", "numltet=23","Hour should be in 00-23 Format");
	hisValidator.addValidation("strSummerEveningFromMin", "req","Summer Evening From Min is a Mandatory Field");
	hisValidator.addValidation("strSummerEveningFromMin", "numltet=59","Minute should be in 00-59 Format");
	
	
	hisValidator.addValidation("strSummerEveningToHr", "req","Summer Evening To Hour is a Mandatory Field");
	hisValidator.addValidation("strSummerEveningToHr", "numltet=23","Hour should be in 00-23 Format");
	hisValidator.addValidation("strSummerEveningToMin", "req","Summer Evening To Min is a Mandatory Field");
	hisValidator.addValidation("strSummerEveningToMin", "numltet=59","Minute should be in 00-59 Format");
	
	hisValidator.addValidation("strWinterMorningFromHr", "req","Winter Morning From Hour is a Mandatory Field");
	hisValidator.addValidation("strWinterMorningFromHr", "numltet=23","Hour should be in 00-23 Format");
	hisValidator.addValidation("strWinterMorningFromMin", "req","Winter Morning From Min is a Mandatory Field");
	hisValidator.addValidation("strWinterMorningFromMin", "numltet=59","Minute should be in 00-59 Format");
	
	hisValidator.addValidation("strWinterMorningToHr", "req","Winter Morning To Hour is a Mandatory Field");
	hisValidator.addValidation("strWinterMorningToHr", "numltet=23","Hour should be in 00-23 Format");
	hisValidator.addValidation("strWinterMorningToMin", "req","Winter Morning To Min is a Mandatory Field");
	hisValidator.addValidation("strWinterMorningToMin", "numltet=59","Minute should be in 00-59 Format");
	
	hisValidator.addValidation("strWinterEveningFromHr", "req","Winter Evening From Hour is a Mandatory Field");
	hisValidator.addValidation("strWinterEveningFromHr", "numltet=23","Hour should be in 00-23 Format");
	hisValidator.addValidation("strWinterEveningFromMin", "req","Winter Evening From Min is a Mandatory Field");
	hisValidator.addValidation("strWinterEveningFromMin", "numltet=59","Minute should be in 00-59 Format");
	
	hisValidator.addValidation("strWinterEveningToHr", "req","Winter Evening To Hour is a Mandatory Field");
	hisValidator.addValidation("strWinterEveningToHr", "numltet=23","Hour should be in 00-23 Format");
	hisValidator.addValidation("strWinterEveningToHr", "req","Winter Evening To Min is a Mandatory Field");
	hisValidator.addValidation("strWinterEveningToHr", "numltet=59","Minute should be in 00-59 Format");
	var strTotalFreePassAtAdmTime=document.forms[0].strNoOfFreePassAdmisssionTime.value;
	hisValidator.addValidation("strNoOfFreePass", "numgtet="+strTotalFreePassAtAdmTime,"Total No. of Free Pass At Time Of Admission must be greater than or equal Total No. of Free Pass");		
			
	if(document.forms[0].strPrintRequest.checked == false)
	{		
		document.forms[0].strPrintRequest.checked.value = 0;
	}
		
	retVal = hisValidator.validate(); 
	
		if(retVal)
		{
				hisValidator.clearAllValidations();
									
			/*	var smfrmTime = parseInt(document.forms[0].strSummerMorningFromHr.value , 10)+""+document.forms[0].strSummerMorningFromMin.value;
				var smtoTime = parseInt(document.forms[0].strSummerMorningToHr.value ,10)+""+document.forms[0].strSummerMorningToMin.value;
				
				var summerMorningFromTime = parseInt(smfrmTime,10);
				var summerMorningToTime = parseInt(smtoTime,10);
				
				if(summerMorningFromTime >= summerMorningToTime)
				{
					alert("Summer Morning From Time Cannot be Greater than or Equal to Summer Morning To Time");
					retVal = false;
					document.forms[0].strSummerMorningFromHr.focus();				
					return false;				
				}
				
				var sefrmTime = parseInt(document.forms[0].strSummerEveningFromHr.value, 10)+""+document.forms[0].strSummerEveningFromMin.value;
				
				var summerEveningFromTime = parseInt(sefrmTime , 10);
	
				if(summerMorningToTime >= summerEveningFromTime)
				{
					alert("Summer Evening From Time Cannot be Lesser than or Equal to  Summer Morning To Time");
					retVal = false;
					document.forms[0].strSummerEveningFromHr.focus();				
					return false;
				}
				
				var seToTime = parseInt(document.forms[0].strSummerEveningToHr.value, 10)+""+document.forms[0].strSummerEveningToMin.value;
				
				var summerEveningToTime = parseInt(seToTime , 10);
		
				if(summerEveningFromTime >= summerEveningToTime)
				{
					alert("Summer Evening From Time Cannot be Greater than or Equal to  Summer Evening To Time");
					retVal = false;
					document.forms[0].strSummerEveningFromHr.focus();				
					return false;
				}	
	
			var wmfrmTime = parseInt(document.forms[0].strWinterMorningFromHr.value,10)+""+document.forms[0].strWinterMorningFromMin.value;
			var wmtoTime = parseInt(document.forms[0].strWinterMorningToHr.value,10)+""+document.forms[0].strWinterMorningToMin.value;
	
			var WinterMorningFromTime = parseInt(wmfrmTime , 10);
				var WinterMorningToTime = parseInt(wmtoTime, 10);
		
				if(WinterMorningFromTime >= WinterMorningToTime)
				{
					alert("Winter Morning From Time Cannot be Greater than or Equal to  Winter Morning To Time");
					retVal = false;
					document.forms[0].strWinterMorningFromHr.focus();				
					return false;
				}				
			
				var wefrmTime = parseInt(document.forms[0].strWinterEveningFromHr.value,10)+""+document.forms[0].strWinterEveningFromMin.value;				
				var WinterEveningFromTime = parseInt(wefrmTime);
	
				if(WinterMorningToTime >= WinterEveningFromTime)
				{
					alert("Winter Evening From Time Cannot be Lesser than or Equal to Winter Morning To Time");
					retVal = false;
					document.forms[0].strWinterEveningFromHr.focus();				
					return false;
				}
				
				var wetoTime = parseInt(document.forms[0].strWinterEveningToHr.value,10)+""+document.forms[0].strWinterEveningToMin.value;
				var WinterEveningToTime = parseInt(wetoTime);
		
				if(WinterEveningFromTime >= WinterEveningToTime)
				{
					alert("Winter Evening From Time Cannot be Greater than or Equal to Winter Evening To Time");
					retVal = false;
					document.forms[0].strWinterEveningFromHr.focus();				
					return false;
				}
				if(document.getElementsByName("strNoOfSlipPrintedAtAdmission")[0].value<1 || document.getElementsByName("strNoOfSlipPrintedAtAdmission")[0].value>9)
				{
					alert("Please select value between 1 and 9");
					return false;
				}*/			
				if(retVal)
				{				
					document.forms[0].selectedTab.value = "PASSDTLSAVE";
					document.forms[0].submit();
				}
				else
				{
					return false;
				}
		}
		else
		{
			hisValidator.clearAllValidations();
			return false;
		}
}
function tabTimeTxt(currentTxt,nextTxt)
{
		currentObj = document.getElementsByName(currentTxt);
		nextObj = document.getElementsByName(nextTxt);
		
		if(currentObj[0].value.length == 2) nextObj[0].focus();	
}
function cancelFunction()
{
	document.forms[0].selectedTab.value = "INITIALPAGE";
	document.forms[0].submit();		
}
--></script>

</head>

<body>
<html:form action="/masters/CNTInPatientConfigMst.cnt" method="post" >

<div class="normalMsg"><bean:write name="inPatientConfigBean" property="strMsg" /></div>

<tag:tab tabLabel="In Patient Configuration" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab> 
<tag:tab tabList="${inPatientConfigBean.lhm}" selectedTab="ipdpassdtls" align="center" width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr class="HEADER">
		<td colspan="3" height="22">In Patient Configuration/Pass Details</td>
	</tr>
	<tr>
		<td class="LABEL" width="50%">Attendant Pass Required 
		<td class="CONTROL" width="50%" colspan="2">
		 	<html:checkbox property="strAttendentPass" name="inPatientConfigBean" value="1"></html:checkbox>
		</td>			
	</tr>
	<tr>
		<td class="LABEL" width="50%">Attendant Pass Generate at Admission Time 
		<td class="CONTROL" width="50%" colspan="2">
		 	<html:checkbox property="strAttendentPassGenerateAtAdmissionTime" name="inPatientConfigBean" value="1"></html:checkbox>
		</td>			
	</tr>
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Total No. of Free Pass At Time Of Admission</td>
		<td class="CONTROL" colspan="2">
		<input type='text' name='strNoOfFreePassAdmisssionTime' class="txtFldMin"
			value="${ inPatientConfigBean.strNoOfFreePassAdmisssionTime}" maxlength="2" onkeypress="return validateData(event,5);"></td>
	</tr>
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Total No. of Free Pass</td>
		<td class="CONTROL" colspan="2">
		<input type='text' name='strNoOfFreePass' class="txtFldMin"
			value="${ inPatientConfigBean.strNoOfFreePass}" maxlength="2" onkeypress="return validateData(event,5);"></td>
	</tr>
	<tr>
		<td class="LABEL"><font color="red">*</font>No. of Paid Pass</td>
		<td class="CONTROL" colspan="2"><input type="text"
			name="strNoOfPaidPass" class="txtFldMin"
			value="${ inPatientConfigBean.strNoOfPaidPass}" maxlength="2"
			onkeypress="return validateData(event,5);"></td>
	</tr>
	<tr>
		<td class="LABEL"><font color="red">*</font>Pass Re-Print Charge(<img src='../../hisglobal/images/INR.png'>)</td>
		<td class="CONTROL" colspan="2"><input type="text"
			name="strPassReprintCharges" class="txtFldMin"
			value="${ inPatientConfigBean.strPassReprintCharges}" maxlength="5"
			onkeypress="return validateData(event,7);"></td>
	</tr>
	<tr>
		<td class="TITLE" colspan="3">New Pass Validity</td>
	</tr>
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Validity for Free Pass</td>
		<td class="CONTROL" colspan="2"><input type='text'
			name='strNewFreePassValidity' class="txtFldMin"
			value="${ inPatientConfigBean.strNewFreePassValidity}" maxlength="2"
			onkeypress="return validateData(event,5);"> Day(s)</td>
	</tr>
	<tr>
		<td class="LABEL"><font color="red">*</font>Validity for Paid Pass</td>
		<td class="CONTROL" colspan="2"><input type="text"
			name="strNewPaidPassValidity" class="txtFldMin"
			value="${ inPatientConfigBean.strNewPaidPassValidity}" maxlength="2"
			onkeypress="return validateData(event,5);"> Day(s)</td>
	</tr>
	<tr>
		<td class="LABEL"><font color="red">*</font>Paid Pass Charge(Rs.)</td>
		<td class="CONTROL" colspan="2"><input type="text"
			name="strPaidPassCharge" class="txtFldMin"
			value="${ inPatientConfigBean.strPaidPassCharge}" maxlength="6"
			onkeypress="return validateData(event,7);"></td>
	</tr>
	<tr>
		<td class="TITLE" colspan="3">Renew Pass Validity</td>
	</tr>
	<tr>
		<td width="50%" class="LABEL"><font color="red">*</font>Validity for Free Pass</td>
		<td class="CONTROL" colspan="2"><input type='text'
			name='strRenewFreePassValidity' class="txtFldMin"
			value="${ inPatientConfigBean.strRenewFreePassValidity}"
			maxlength="2" onkeypress="return validateData(event,5);">
		Day(s)</td>
	</tr>
	<tr>
		<td class="LABEL"><font color="red">*</font>Validity for Paid Pass</td>
		<td class="CONTROL" colspan="2"><input type="text"
			name="strRenewPaidPassValidity" class="txtFldMin"
			value="${ inPatientConfigBean.strRenewPaidPassValidity}"
			maxlength="2" onkeypress="return validateData(event,5);">
		Day(s)</td>
	</tr>
	<tr>
		<td class="LABEL"><font color="red">*</font>Paid Pass Renew Charge(Rs.)</td>
		<td class="CONTROL" colspan="2"><input type="text"
			name="strPaidPassRenewCharge" class="txtFldMin"
			value="${ inPatientConfigBean.strPaidPassRenewCharge}" maxlength="6"
			onkeypress="return validateData(event,7);"></td>
	</tr>
	<tr style="display: none;">
		<td class="TITLE" colspan="3">Timings</td>
	</tr>
	<tr style="display: none;">
		<td width="50%" class="TITLE"><font color="red">*</font>Summer</td>
		<td width="25%" class="multiLabel">From (HH24:MM)</td>
		<td class="multiLabel" width="25%">To (HH24:MM)</td>
	</tr>
	<tr style="display: none;">
		<td width="50%" class="LABEL">Morning</td>
		<td class="multiControl" width="25%"><input type='text'
			name='strSummerMorningFromHr' class="txtFldMin" style="width: 18px"
			maxlength="2" value="${inPatientConfigBean.strSummerMorningFromHr}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strSummerMorningFromHr','strSummerMorningFromMin')">
		: <input type='text' name='strSummerMorningFromMin' class="txtFldMin"
			style="width: 18px" maxlength="2"
			value="${inPatientConfigBean.strSummerMorningFromMin}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strSummerMorningFromMin','strSummerMorningToHr')">
		</td>
		<td class="multiControl" width="25%"><input type='text'
			name='strSummerMorningToHr' class="txtFldMin" style="width: 18px"
			maxlength="2" value="${inPatientConfigBean.strSummerMorningToHr}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strSummerMorningToHr','strSummerMorningToMin')">
		: <input type='text' name='strSummerMorningToMin' class="txtFldMin"
			style="width: 18px" maxlength="2"
			value="${inPatientConfigBean.strSummerMorningToMin}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strSummerMorningToMin','strSummerEveningFromHr')">
		</td>
	</tr>
	<tr style="display: none;">
		<td width="50%" class="LABEL">Evening</td>
		<td class="multiControl" width="25%"><input type='text'
			name='strSummerEveningFromHr' class="txtFldMin" style="width: 18px"
			maxlength="2" value="${inPatientConfigBean.strSummerEveningFromHr}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strSummerEveningFromHr','strSummerEveningFromMin')">
		: <input type='text' name='strSummerEveningFromMin' class="txtFldMin"
			style="width: 18px" maxlength="2"
			value="${inPatientConfigBean.strSummerEveningFromMin}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strSummerEveningFromMin','strSummerEveningToHr')">
		</td>
		<td class="multiControl" width="25%"><input type='text'
			name='strSummerEveningToHr' class="txtFldMin" style="width: 18px"
			maxlength="2" value="${inPatientConfigBean.strSummerEveningToHr}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strSummerEveningToHr','strSummerEveningToMin')">
		: <input type='text' name='strSummerEveningToMin' class="txtFldMin"
			style="width: 18px" maxlength="2"
			value="${inPatientConfigBean.strSummerEveningToMin}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strSummerEveningToMin','strWinterMorningFromHr')">
		</td>
	</tr>
	<tr style="display: none;">
		<td width="50%" class="TITLE"><font color="red">*</font>Winter</td>
		<td class="multiLabel">From (HH24:MM)</td>
		<td class="multiLabel">To (HH24:MM)</td>
	</tr>
	<tr style="display: none;">
		<td width="50%" class="LABEL">Morning</td>
		<td class="multiControl" width='25%'><input type='text'
			name='strWinterMorningFromHr' class="txtFldMin" style="width: 18px"
			maxlength="2" value="${inPatientConfigBean.strWinterMorningFromHr}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strWinterMorningFromHr','strWinterMorningFromMin')">
		: <input type='text' name='strWinterMorningFromMin' class="txtFldMin"
			style="width: 18px" maxlength="2"
			value="${inPatientConfigBean.strWinterMorningFromMin}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strWinterMorningFromMin','strWinterMorningToHr')">
		</td>
		<td class="multiControl" width='25%'><input type='text'
			name='strWinterMorningToHr' class="txtFldMin" style="width: 18px"
			maxlength="2" value="${inPatientConfigBean.strWinterMorningToHr}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strWinterMorningToHr','strWinterMorningToMin')">
		: <input type='text' name='strWinterMorningToMin' class="txtFldMin"
			style="width: 18px" maxlength="2"
			value="${inPatientConfigBean.strWinterMorningToMin}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strWinterMorningToMin','strWinterEveningFromHr')">
		</td>
	</tr>
	<tr style="display: none;">
		<td width="50%" class="LABEL">Evening</td>
		<td class="multiControl" width='25%'><input type='text'
			name='strWinterEveningFromHr' class="txtFldMin" style="width: 18px"
			maxlength="2" value="${inPatientConfigBean.strWinterEveningFromHr}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strWinterEveningFromHr','strWinterEveningFromMin')">
		: <input type='text' name='strWinterEveningFromMin' class="txtFldMin"
			style="width: 18px" maxlength="2"
			value="${inPatientConfigBean.strWinterEveningFromMin}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strWinterEveningFromMin','strWinterEveningToHr')">
		</td>
		<td class="multiControl" width='25%'><input type='text'
			name='strWinterEveningToHr' class="txtFldMin" style="width: 18px"
			maxlength="2" value="${inPatientConfigBean.strWinterEveningToHr}"
			onkeypress="return validateData(event,5);"
			onkeyup="tabTimeTxt('strWinterEveningToHr','strWinterEveningToMin')">
		: <input type='text' name='strWinterEveningToMin' class="txtFldMin"
			style="width: 18px" maxlength="2"
			value="${inPatientConfigBean.strWinterEveningToMin}"
			onkeypress="return validateData(event,5);"></td>
	</tr>
	<tr style="display: none;">
		<td class="LABEL">Print Request</td>
		<td class="CONTROL" colspan="2"><html:checkbox
			name="inPatientConfigBean" property="strPrintRequest" value="1"></html:checkbox>

		</td>
	</tr>
	<tr>
		<td class="LABEL"><font color="red">*</font>No. of Slip Printed at the time of Admission</td>
		<td class="CONTROL" colspan="2">
			<input type="text" name="strNoOfSlipPrintedAtAdmission" class="txtFldMin"
				value="${ inPatientConfigBean.strNoOfSlipPrintedAtAdmission}" maxlength="1"
				onkeypress="return validateData(event,5);"></td>
	</tr>	
	<tr class="FOOTER">
		<td colspan="3"><font size="2" color="red">*</font> Mandatory Fields </tr>
</table>

<table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	<tr>

		<!-- <td align="right">
		<img src="../../hisglobal/images/btn-sv.png" style="cursor: pointer" onClick=" return validate1();" /></td>
		<td align="left">
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor: pointer" onClick="cancelFunc();" /></td>
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