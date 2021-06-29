
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<head><meta charset="utf-8" />
<title>In Patient Configuration</title>

<script type="text/javascript">
			
function validate1(){	
		if(document.forms[0].strIntegrationBilling[1].checked){
			
			document.forms[0].strAdvanceRequestAdmissionAdvice[1].checked=true;
			document.forms[0].strAdvanceAmountAdmission[1].checked=true;
			//document.forms[0].strPatientAdjustedFinalDischargeBill[1].checked=true;
			document.forms[0].strRefundRequestAdmissionCancellation[1].checked=true;
		}
		document.forms[0].selectedTab.value = "BILLDTLSAVE";
		document.forms[0].submit();
	}
	
function openDiv()
{
	if(document.forms[0].strIntegrationBilling[0].checked)
	{
		document.getElementById("billId").style.display="block";
		
		document.forms[0].strAdvanceRequestAdmissionAdvice[0].checked=true;
		document.forms[0].strAdvanceAmountAdmission[0].checked=true;
		//document.forms[0].strPatientAdjustedFinalDischargeBill[1].checked=true;
		document.forms[0].strRefundRequestAdmissionCancellation[0].checked=true;
		
		document.forms[0].strAdvanceRequestAdmissionAdvice[1].disabled=true;
		document.forms[0].strAdvanceAmountAdmission[1].disabled=true;
		//document.forms[0].strPatientAdjustedFinalDischargeBill[1].checked=true;
		document.forms[0].strRefundRequestAdmissionCancellation[1].disabled=true;
	}
	else
	{
		document.getElementById("billId").style.display="none";
		
		document.forms[0].strAdvanceRequestAdmissionAdvice[1].disabled=false;
		document.forms[0].strAdvanceAmountAdmission[1].disabled=false;
		//document.forms[0].strPatientAdjustedFinalDischargeBill[1].checked=true;
		document.forms[0].strRefundRequestAdmissionCancellation[1].disabled=false;
	}
}	
	function cancelFunction(){
	document.forms[0].selectedTab.value = "INITIALPAGE";
				document.forms[0].submit();		
		
		}
	</script>

</head>

<body onload="openDiv();">
<html:form action="/masters/CNTInPatientConfigMst.cnt" method="post">

<div class="normalMsg"><bean:write name="inPatientConfigBean"
	property="strMsg" /></div>

<tag:tab tabLabel="In Patient Configuration" selectedTab="FIRST"
	align="center" width="TABLEWIDTH"></tag:tab> <tag:tab
	tabList="${inPatientConfigBean.lhm}" selectedTab="ipdbilldtls"
	align="center" width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">

   
	<tr class="HEADER">
		<td colspan="4" height="22">In Patient Configuration</td>
	</tr>
	
	<tr>
		<td width="50%" class="LABEL" colspan="2">
		Admission Charge Taken At Admission Counter
		</td>
		<td width="50%" class="CONTROL" colspan="2">
		<html:radio	name="inPatientConfigBean" property="strAdmissionCharge" value="1" >Yes</html:radio>
		<html:radio name="inPatientConfigBean" property="strAdmissionCharge" value="0" >No</html:radio>
		</td>
	</tr>
	<tr>
		<td width="50%" class="LABEL" colspan="2">
			Integration With Billing
			
		</td>
		<td width="50%" class="CONTROL" colspan="2">
		<html:radio	name="inPatientConfigBean" property="strIntegrationBilling" value="1" onclick="openDiv();">Yes</html:radio>
		<html:radio name="inPatientConfigBean" property="strIntegrationBilling" value="0" onclick="openDiv();">No</html:radio>
		</td>
	</tr>
	</table>
	<div id="billId">
	<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td width="50%" class="LABEL" colspan="2">
			Advance Request at Admission Advice
		</td>
		<td width="50%" class="CONTROL" colspan="2"><html:radio
			name="inPatientConfigBean" property="strAdvanceRequestAdmissionAdvice" value="1">Yes</html:radio>
		<html:radio name="inPatientConfigBean" property="strAdvanceRequestAdmissionAdvice" value="0">No</html:radio>
		</td>
	</tr>
	<tr>
		<td width="50%" class="LABEL" colspan="2">
			Whether check Advance amount at the time Of Admission 
		</td>
		<td width="50%" class="CONTROL" colspan="2"><html:radio
			name="inPatientConfigBean" property="strAdvanceAmountAdmission" value="1">Yes</html:radio>
		<html:radio name="inPatientConfigBean" property="strAdvanceAmountAdmission" value="0">No</html:radio>
		</td>
	</tr>
	
	<tr>
		<td width="50%" class="LABEL" colspan="2">
		Advance Refund Request At Time Of Admission Cancellation 
		</td>
		<td width="50%" class="CONTROL" colspan="2">
		<html:radio	name="inPatientConfigBean" property="strRefundRequestAdmissionCancellation" value="1">Yes</html:radio>
		<html:radio name="inPatientConfigBean" property="strRefundRequestAdmissionCancellation" value="0">No</html:radio>
		</td>
		</tr>
		</table>
		</div>
		
<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr class="FOOTER">
	    <td align="left"><font size="1" color="red">*</font>In Case Of Integration With Billing All Subsequent Options Should be Checked YES
		<td align="right"><font size="2" color="red">*</font> Mandatory	Fields
	</tr>
</table>

<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>

		<!-- <td align="right"><img src="../../hisglobal/images/btn-sv.png"
			style="cursor:pointer" onClick=" return validate1();" /></td>
		<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
			style="cursor:pointer" onClick="cancelFunc();" /></td>
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