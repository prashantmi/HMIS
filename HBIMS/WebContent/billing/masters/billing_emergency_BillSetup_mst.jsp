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
 										JSP URL : billing/masters/billing_emergency_BillSetup_mst.jsp

-->	






<html>
<head><meta charset=utf-8>
<title>Emergency</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
	<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>

<script type="text/javascript">
	var delIndex;
	
	function myFunc(mode, comObj, delIdx){

		delIndex = delIdx;
		var hmode = "EMGTARIFFVALUES";
		var url = "CNTBillSetup.cnt?selectedTab="+hmode+"&grpName="+comObj.value;
		ajaxFunction(url,"1");
		
	}
	
	function getAjaxResponse(res,mode){
	
		var objEle = document.getElementById("emgTariffId" + delIndex);
		objEle.innerHTML = "<select name='emgTariffName' id='emgTariffName"+delIndex + "'> " +res+ "</select>";
		
		}
	
	function validate1(){	
	
			var hisValidator = new HISValidator("billsetupbean");
		
			//	hisValidator.addValidation("emgGroupName", "dontselect=0","Please Select A Group Name");
			//	hisValidator.addValidation("emgTariffName", "dontselect=0","Please Select A Tariff Name");
			
			
			if(document.billsetupbean.emgRefundPenalty.value !=""){
			  	 hisValidator.addValidation("emgRefundPenalty", "amount=4,1","Please Enter a Valid Penalty Percentage ");
				hisValidator.addValidation("emgRefundPenalty", "numltet=100","Penalty Percentage Should be Less than or Equal to 100");
			}
			var retVal = hisValidator.validate();
			if(retVal){
				//destroyMultiRow("1");
				document.forms[0].selectedTab.value = "EMGINSERT";
				document.forms[0].submit();
			}else{
				return false;
			}
		
	}
	
	function checkClick(id){
	
		if(document.forms[0].emgDiscount.checked){
			document.getElementById(id).style.display="block";
		}else {
			document.getElementById(id).style.display="none";
		}
	}
	
	function initPage(){
	 	document.forms[0].selectedTab.value="INITIALPAGE";
		document.forms[0].submit();
	 }
	
	</script>

</head>

<body>
<html:form action="/masters/CNTBillSetup.cnt" method="post" >

<div class="errMsg"><bean:write name="billsetupbean"
	property="strErr" /></div>
<div class="normalMsg" id='normalMsg'><bean:write
	name="billsetupbean" property="strMsg" /></div>
<tag:tab tabList="${billsetupbean.lhm}" selectedTab="emergency"
	align="center" width="TABLEWIDTH"></tag:tab>


<table align="center" border="0" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">
	<tr class="HEADER">
		<td colspan="4" height="22">Emergency Screen</td>
	</tr>

	<tr>
		<td width="50%" class="LABEL">Whether Third Party Billing Allowed</td>
		<td width="50%" colspan="3" class="CONTROL"><html:radio
			name="billsetupbean" property="emgThirdPartyBilling" value="1" />Yes
		<html:radio name="billsetupbean" property="emgThirdPartyBilling"
			value="0" />No</td>
	</tr>
	<tr>
		<td colspan="4" class="TITLE"><html:checkbox name="billsetupbean"
			property="emgDiscount" value="1"
			onclick="checkClick('disApplicable')" /> Whether Discount Is
		Applicable</td>
	</tr>
</table>

<logic:equal name="billsetupbean" property="emgDiscount" value="1">
	<div id="disApplicable" style="display: block">
	<table align="center" border="0" class="TABLEWIDTH" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="50%" class="LABEL">Whether Provision To Enter
			Discount By The Billing Clerk</td>
			<td width="50%" colspan="2" class="CONTROL"><html:radio
				name="billsetupbean" property="emgDiscountClerk" value="1" />Yes <html:radio
				name="billsetupbean" property="emgDiscountClerk" value="0" />No</td>
		</tr>
	</table>
	</div>

</logic:equal> <logic:notEqual name="billsetupbean" property="emgDiscount" value="1">
	<div id="disApplicable" style="display: none">
	<table align="center" border="0" class="TABLEWIDTH" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="50%" class="LABEL">Whether Provision To Enter
			Discount By The Billing Clerk</td>
			<td width="50%" colspan="2" class="CONTROL"><html:radio
				name="billsetupbean" property="emgDiscountClerk" value="1" />Yes <html:radio
				name="billsetupbean" property="emgDiscountClerk" value="0" />No</td>
		</tr>
	</table>
	</div>
</logic:notEqual>

<table align="center" border="0" class="TABLEWIDTH" cellpadding="1px"
	cellspacing="1px">

	<tr>
		<td class="LABEL" width="50%">Penalty(in %)if patient demands for refund
		without Approval</td>
		<td class="CONTROL" width="50%"><input type="text"
			name="emgRefundPenalty" value="${billsetupbean.emgRefundPenalty}"
			class="txtFldNormal" maxlength="4"
			onkeypress="return validateData(event,7);"></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%">Required Round Off</td>
		<td class="CONTROL" width="50%"><html:checkbox
			name="billsetupbean" property="emgRoundOff" value="1" /></td>
	</tr>
	<tr>
		<td class="LABEL" width="50%">Service Tax</td>
		<td class="CONTROL" width="50%">
		
		<html:radio name="billsetupbean" property="emgServiceTax" value="0">No</html:radio>
			<html:radio name="billsetupbean" property="emgServiceTax" value="1">Yes</html:radio>
		 </td>
	</tr>
	<tr>
		<td class="LABEL">Free Category</td>
		<td class="CONTROL"><select name="emgFreeCategory">
			<bean:write property="strEmgFreeCategoryValue" name="billsetupbean"
				filter="false" />
		</select></td>
	</tr>
	<tr class="FOOTER">
		<td colspan="4"><font size="2" color="red">*</font> Mandatory
		Fields</td>
	</tr>
</table>

<table border="0" class="TABLEWIDTH" align="center">
	<tr>

	<!-- 	<td align="right"><img src="../../hisglobal/images/btn-sv.png"
			style="cursor: pointer; cursor: hand;" title="Save Record"
			onClick="return validate1();" /></td>
		<td align="left"><img src="../../hisglobal/images/btn-ccl.png"
			style="cursor: pointer; cursor: hand;" title="Cancel the Process"
			onClick="cancelFunc();" /></td>
		 -->
		 <br>	
			<td align="right">
				<!-- <img src="/HBIMS/hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record" onClick="return validate1();" /> -->	
				<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>		
			</td>
			<td align="left">
				<!-- <img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel the Process" onClick="cancelFunc();" /> -->
				<a href="#" class="button"	onclick="cancelFunc();"><span class="cancel">Cancel</span></a> 
			</td>
	</tr>
</table>

<input type="hidden" name="selectedTab"></html:form>
<tag:autoIndex></tag:autoIndex>
</body>

</html>