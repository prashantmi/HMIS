<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>



<!--  
 
 										Developer : PAWAN KUMAR B N
 									    Created On: 19-09-2011
 										Module : Billing
 										Process: BILL SETUP Master
 										JSP URL : billing/masters/billing_ipdgeneral_BillSetup_mst.jsp

-->	





<html>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>

<head><meta charset=utf-8>
<title>IPD General</title>
</head>

<script>
	
	function checkClick(idValue){
	
		if(document.forms[0].ipdGenDiscount.checked){
			document.getElementById(idValue).style.display="block";
		}else {
			document.getElementById(idValue).style.display="none";
		}
	}
	
	
	function creditLimitVisible(idValue){
		
		if(document.forms[0].ipdGenCreditBilling[0].checked){
		
			document.getElementById(idValue).style.display="block";
			
		}else{
		
			document.getElementById(idValue).style.display="none";
		}
	
	}
	
	
	function validate1(){	
		 var hisValidator = new HISValidator("billsetupbean");
		
			if(document.billsetupbean.ipdGenPenalty.value !=""){
			    hisValidator.addValidation("ipdGenPenalty", "amount=4,1","Please Enter a Valid Penalty Percentage ");
				hisValidator.addValidation("ipdGenPenalty", "numltet=100","Penalty Percentage Should be Less than or Equal to 100");
			}
			
			if(document.forms[0].ipdGenCreditBilling[0].checked){
			 hisValidator.addValidation("ipdGenExcessCreditLimit", "amount=8,2","Please Enter a Excess Credit Limit ");
		}
			
			
			
			if(document.billsetupbean.ipdGenFlexibleTime.value !=""){
				hisValidator.addValidation("ipdGenFlexibleTime", "numltet=24","Flexible Time Should be Less than or Equal to 24 Hours");
			}
			
			if(document.billsetupbean.ipdGenFlexibleTimeAdmission.value !=""){
				hisValidator.addValidation("ipdGenFlexibleTimeAdmission", "numltet=24","Flexible Time Should be Less than or Equal to 24 Hours");
			}
			
			if(document.billsetupbean.ipdGenPayment.value !=""){
				hisValidator.addValidation("ipdGenPayment", "numltet=30","Part Payment Duration Should be Less than or Equal to 30 Days");
			}
			if(document.billsetupbean.ipdGenCheckOutTimeHr.value !=""){
				hisValidator.addValidation("ipdGenCheckOutTimeHr", "minlen=2","Check Out Time's Hour Should be in 'HH' Format, Starts from 00");
				hisValidator.addValidation("ipdGenCheckOutTimeHr", "numltet=24","Check Out Time's Hour Should be Less than or Equal to 24 Hours");
			}
			if(document.billsetupbean.ipdGenCheckOutTimeMn.value !=""){
				hisValidator.addValidation("ipdGenCheckOutTimeMn", "minlen=2","Check Out Time's Minute Should be in 'MM' Format, Starts from 00");
				hisValidator.addValidation("ipdGenCheckOutTimeMn", "numltet=59","Check Out Time's Minute Should be Less than or Equal to 59 Minutes");
			}
			
			if(document.billsetupbean.ipdGenCheckOutTimePrivateHr.value !=""){
				hisValidator.addValidation("ipdGenCheckOutTimePrivateHr", "minlen=2","Check Out Time's Hour Should be in 'HH' Format, Starts from 00");
				hisValidator.addValidation("ipdGenCheckOutTimePrivateHr", "numltet=23","Check Out Time's Hour Should be Less than or Equal to 24 Hours");
			}
			if(document.billsetupbean.ipdGenCheckOutTimePrivateMn.value !=""){
				hisValidator.addValidation("ipdGenCheckOutTimePrivateMn", "minlen=2","Check Out Time's Minute Should be in 'MM' Format, Starts from 00");
				hisValidator.addValidation("ipdGenCheckOutTimePrivateMn", "numltet=59","Check Out Time's Minute Should be Less than or Equal to 59 Minutes");
			}
			
			
			if(document.billsetupbean.ipdGenServiceTaxOnTotalBill.value !=""){
			    hisValidator.addValidation("ipdGenServiceTaxOnTotalBill", "maxlen=2","Please Enter a Valid Service Tax On Total Bill Value in Percentage ");
				hisValidator.addValidation("ipdGenServiceTaxOnTotalBill", "numltet=100","Service Tax On Total Bill Should be Less than or Equal to 100");
			}
			hisValidator.addValidation("strGeneralWardType", "dontselect=0","Please select the Ward Type Id For General");
			hisValidator.addValidation("strGeneralRoomType", "dontselect=0","Please select the Ward Type Id for Private");
			hisValidator.addValidation("ipdGenServiceFreeTreatmentTime", "req","Please Enter the Time Period for Free Treatment in Emergency");
			
		var retVal = hisValidator.validate(); 
	
		if(retVal){
			document.forms[0].selectedTab.value = "IPDGENERAL";
			document.forms[0].submit();
		}else{
			return false;
		}
		
	}
	
	function initPage(){
	 	document.forms[0].selectedTab.value="INITIALPAGE";
		document.forms[0].submit();
	 }
	
	</script>

<body onload="creditLimitVisible('excessCreditLimitDivId');">
<html:form action="/masters/CNTBillSetup.cnt" method="post" >

<div class="errMsg"><bean:write name="billsetupbean"
	property="strErr" /></div>
<div class="normalMsg" id='normalMsg'><bean:write
	name="billsetupbean" property="strMsg" /></div>

<tag:tab tabList="${billsetupbean.lhm}" selectedTab="ipd" align="center"
	width="TABLEWIDTH"></tag:tab> <tag:tab tabList="${billsetupbean.lhm1}"
	selectedTab="generalIpd" align="center" width="TABLEWIDTH"></tag:tab>


<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr class="HEADER">
		<td colspan="2" height="22">General IPD Screen</td>
	</tr>
	<tr>
		<td width="67%" class="LABEL">Whether Third Party Billing Allowed</td>
		<td width="33%" class="CONTROL"><html:radio name="billsetupbean"
			property="ipdGenThirdPartyBilling" value="1" />Yes <html:radio
			name="billsetupbean" property="ipdGenThirdPartyBilling" value="0" />No
		</td>
	</tr>
	<tr>
		<td colspan="2" class="TITLE"><html:checkbox name="billsetupbean"
			property="ipdGenDiscount" value="1"
			onclick="checkClick('disApplicable')" />Whether Discount is
		applicable</td>
	</tr>

</table>

<logic:equal name="billsetupbean" property="ipdGenDiscount" value="1">
	<div id="disApplicable" style="display: block">
	<table align="center" class="TABLEWIDTH" cellpadding="1px"
		cellspacing="1px">

		<tr>
			<td width="67%" class="LABEL">Whether Provision To Enter
			Discount By The Billing Clerk</td>
			<td width="33%" class="CONTROL"><html:radio name="billsetupbean"
				property="ipdGenClerkDiscount" value="1" />Yes <html:radio
				name="billsetupbean" property="ipdGenClerkDiscount" value="0" />No
			</td>
		</tr>
	</table>
	</div>
</logic:equal> <logic:notEqual name="billsetupbean" property="ipdGenDiscount"
	value="1">

	<div id="disApplicable" style="display: none">
	<table align="center" class="TABLEWIDTH" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="67%" class="LABEL">Whether Provision To Enter
			Discount By The Billing Clerk</td>
			<td width="33%" class="CONTROL"><html:radio name="billsetupbean"
				property="ipdGenClerkDiscount" value="1" />Yes <html:radio
				name="billsetupbean" property="ipdGenClerkDiscount" value="0" />No
			</td>
		</tr>
	</table>
	</div>
</logic:notEqual>




<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="LABEL">Penalty(in %)if patient demands for refund
		without Approval</td>
		<td class="CONTROL"><input type="text" name="ipdGenPenalty"
			class="txtFldNormal" value="${billsetupbean.ipdGenPenalty}"
			maxlength="4" onkeypress="return validateData(event,7);"> %</td>
	</tr>
	<tr>
		<td class="LABEL">Whether Credit(Negative) Billing Allowed</td>
		<td class="CONTROL"><html:radio name="billsetupbean"
			property="ipdGenCreditBilling" value="1"
			onclick="creditLimitVisible('excessCreditLimitDivId');" />Yes <html:radio
			name="billsetupbean" property="ipdGenCreditBilling" value="0"
			onclick="creditLimitVisible('excessCreditLimitDivId');" />No</td>
	</tr>
</table>
<div id="excessCreditLimitDivId" style="display: none">
<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="LABEL">Excess Credit Limit(Deficit Amount)</td>
		<td class="CONTROL"><input type="text" class="txtFldNormal"
			name="ipdGenExcessCreditLimit" maxlength="11"
			value="${billsetupbean.ipdGenExcessCreditLimit }"></td>
	</tr>
</table>
</div>

<script>
function copyText(_these){
	document.getElementsByName("ipdGenCheckOutTimePrivateHr")[0].value=_these.value;
}
function copyText1(_these){
	document.getElementsByName("ipdGenCheckOutTimePrivateMn")[0].value=_these.value;
}
</script>
<table align="center" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr>
		<td class="LABEL">CheckOut Time (General/Private)</td>
		<td class="CONTROL"><input type="text" onkeyup="copyText(this)"
			name="ipdGenCheckOutTimeHr" class="txtFldNormal"
			value="${billsetupbean.ipdGenCheckOutTimeHr}" maxlength="2"
			style="width: 35px"> : <input type="text"
			onkeyup="copyText1(this)" name="ipdGenCheckOutTimeMn"
			class="txtFldNormal" value="${billsetupbean.ipdGenCheckOutTimeMn}"
			maxlength="2" style="width: 39px"> (HH24:MM) <input
			type="hidden" name="ipdGenCheckOutTimePrivateHr" class="txtFldNormal"
			value="${billsetupbean.ipdGenCheckOutTimePrivateHr}" maxlength="2"
			style="width: 35px"><input type="hidden"
			name="ipdGenCheckOutTimePrivateMn" class="txtFldNormal"
			value="${billsetupbean.ipdGenCheckOutTimePrivateMn}" maxlength="2"
			style="width: 39px"></td>
	</tr>
	<!--  tr>
		<td class="LABEL">CheckOut Time (Private)</td>
		<td class="CONTROL"><input type="text"
			name="ipdGenCheckOutTimePrivateHr" class="txtFldNormal"
			value="${billsetupbean.ipdGenCheckOutTimePrivateHr}" maxlength="2"
			style="width: 35px"> : <input type="text"
			name="ipdGenCheckOutTimePrivateMn" class="txtFldNormal"
			value="${billsetupbean.ipdGenCheckOutTimePrivateMn}" maxlength="2"
			style="width: 39px"> (HH24:MM)</td>
	</tr>-->
	<tr>
		<td class="LABEL">Flexible Time (Discharge)</td>
		<td class="CONTROL"><input type="text" name="ipdGenFlexibleTime"
			class="txtFldNormal" value="${billsetupbean.ipdGenFlexibleTime}"
			maxlength="2" onkeypress="return validateData(event,5);">
		Hour(s)</td>
	</tr>
	<tr>
		<td class="LABEL">Flexible Time (Admission)</td>
		<td class="CONTROL"><input type="text"
			name="ipdGenFlexibleTimeAdmission" class="txtFldNormal"
			value="${billsetupbean.ipdGenFlexibleTimeAdmission}" maxlength="2"
			onkeypress="return validateData(event,5);"> Hour(s)</td>
	</tr>
	<tr>
		<td class="LABEL">Part Payment Duration</td>
		<td class="CONTROL"><input type="text" name="ipdGenPayment"
			class="txtFldNormal" value="${billsetupbean.ipdGenPayment}"
			maxlength="2" onkeypress="return validateData(event,5);">
		Day(s)</td>
	</tr>
	<tr>
		<td class="LABEL">Required Round Off</td>
		<td class="CONTROL"><html:checkbox name="billsetupbean"
			property="ipdGenRoundOff" value="1" /></td>
	</tr>
	<tr>
		<td class="LABEL">Service Tax On Total Bill</td>
		<td class="CONTROL"><input type="text"
			name="ipdGenServiceTaxOnTotalBill" class="txtFldNormal"
			value="${billsetupbean.ipdGenServiceTaxOnTotalBill}" maxlength="2"
			onkeypress="return validateData(event,5);"> %</td>
	</tr>
	<tr>
		<td class="LABEL">Ward Type Id For General</td>
		<td class="CONTROL"><select name="strGeneralWardType">
			<bean:write property="strGeneralWardTypeValue" name="billsetupbean"
				filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL">Ward Type Id for Private</td>
		<td class="CONTROL"><select name="strGeneralRoomType">
			<bean:write property="strGeneralRoomTypeValue" name="billsetupbean"
				filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL">Time Period for Free Treatment in Emergency</td>
		<td class="CONTROL"><input type="text"
			name="ipdGenServiceFreeTreatmentTime" class="txtFldNormal"
			value="${billsetupbean.ipdGenServiceFreeTreatmentTime}" maxlength="3"
			onkeypress="return validateData(event,5);"> Hour(s)</td>
	</tr>
	<tr>
		<td class="LABEL">ADT Process</td>
		<td class="CONTROL">
		
		<html:radio name="billsetupbean" property="ipdGenAdtProcessType" value="1">Online</html:radio>
			<html:radio name="billsetupbean" property="ipdGenAdtProcessType" value="2">Only Admission</html:radio>
		 </td>
	</tr>
	<tr>
		<td class="LABEL">Service Tax</td>
		<td class="CONTROL">
		
		<html:radio name="billsetupbean" property="ipdServiceTax" value="0">No</html:radio>
			<html:radio name="billsetupbean" property="ipdServiceTax" value="1">Yes</html:radio>
		 </td>
	</tr>
	<tr>
		<td class="LABEL">Free Category</td>
		<td class="CONTROL"><select name="ipdFreeCategory">
			<bean:write property="strIpdFreeCategoryValue" name="billsetupbean"
				filter="false" />
		</select></td>
	</tr>
	<tr>
		<td class="LABEL">Whether Advance Is Required</td>
		<td class="CONTROL"><html:checkbox name="billsetupbean"
			property="ipdGenAdvanceReq" value="1" /></td>
	</tr>
	<tr>
		<td class="LABEL">Re-Open Validity </td>
		<td class="CONTROL"><input type="text"
			name="ipdGenReOpenValidity" class="txtFldNormal"
			value="${billsetupbean.ipdGenReOpenValidity}" maxlength="3"
			onkeypress="return validateData(event,5);"></td>
	</tr>
	<tr class="FOOTER">
		<td colspan="2" height="22"></td>
	</tr>
</table>

<table border="0" class="TABLEWIDTH" align="center">
	<tr>

		<td align="right"><img src="../../hisglobal/images/btn-sv.png"
			style="cursor: pointer; cursor: hand;" title="Save Record"
			onClick="return validate1();" /></td>
		<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
			onClick="cancelFunc();" style="cursor: pointer; cursor: hand;"
			title="Cancel the Process" /></td>
	</tr>
</table>

<input type="hidden" name="selectedTab"></html:form>
<tag:autoIndex></tag:autoIndex>
</body>

</html>