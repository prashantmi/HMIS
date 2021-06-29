<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% response.setHeader("Cache-Control", "no-cache"); %>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag" %>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
	
<!--  
 
Developer : PAWAN KUMAR B N
Created On: 19-09-2011
Module : Billing
Process: BILL SETUP Master
JSP URL : billing/masters/billing_general_BillSetup_mst.jsp
Modified On: 02-01-2015
Purpose: Removed Consumables Charges Logic and added credit bill approval flag
Modified By: Amit Kumar Ateria
-->	
	
	
<html>
	
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">	
<link href="/HBIMS/billing/css/master.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="/HBIMS/hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
	
<script language="JavaScript" src="/HBIMS/hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="/HBIMS/hisglobal/js/tab.js"></script>
<script language="JavaScript" src="/HBIMS/hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="/HBIMS/hisglobal/js/util.js"></script>
<script language="Javascript" src="/HBIMS/hisglobal/js/validation.js"></script>

<head><meta charset=utf-8>
<title>Billing Config-General</title>
	
	<script type="text/javascript">
	
	function validate1()
	{		
		//alert('1');
		var hisValidator = new HISValidator("billsetupbean");
		//hisValidator.addValidation("finStartYear", "req","Financial Start Year is a Mandatory Field");
		//hisValidator.addValidation("finStartYear", "date","Financial Start Year should be a valid Date");
		hisValidator.addValidation("inBound", "req","In Bound Purged Days is a Mandatory Field");
		hisValidator.addValidation("inBound", "numgt=0","In Bound Value Should be Greater than 0");
		hisValidator.addValidation("inBound", "numltet=366","In Bound Value Should be Less than or Equal to a Year");
		hisValidator.addValidation("outBound", "req","Out Bound Purged Days is a Mandatory Field");
		hisValidator.addValidation("outBound", "numgt=0","Out Bound Value Should be Greater than 0");
		hisValidator.addValidation("outBound", "numltet=366","Out Bound Value Should be Less than or Equal to a Year");
		hisValidator.addValidation("cancelTime", "req","Cancellation Time is a Mandatory Field");
		hisValidator.addValidation("cancelTime", "minlen=2","Cancellation Time's Should be in 'MM' Format, Starts from 00");
		hisValidator.addValidation("cancelTime", "numltet=59","Cancellation Time Value Should be Less than or Equal to 59");
		//hisValidator.addValidation("defNoRecords", "req","Default Number of Records is a Mandatory Field");
		//hisValidator.addValidation("defNoRecords", "numgt=0","Default Number of Records Should be Greater than 0");
		//hisValidator.addValidation("modRecords", "dontselect=0","Please Select a Default User Level to Modify Records");
		//hisValidator.addValidation("delRecords", "dontselect=0","Please Select a Default User Level to Delete Records");
		hisValidator.addValidation("printCharge", "req","Duplicate Print Charge is a Mandatory Field");
		hisValidator.addValidation("printCharge", "amount=8,2","Duplicate Print Charge Should be in one of the Format [000000.00] or [000000]" );
		hisValidator.addValidation("strUrgTrfSur", "req","Urgent Tariffs Surcharge is a Mandatory Field");
		
		
		//hisValidator.addValidation("strDefaultTariffCode", "dontselect=0","Please Select one  Tariff Value in Combo");
		//hisValidator.addValidation("strConsumableChargesGroupId", "dontselect=0","Please Select a Consumable Charges Group");
		//hisValidator.addValidation("strConsumableChargesTariffCode", "dontselect=0","Please Select one  Consumable Charges Tariff");
		//hisValidator.addValidation("printerName", "req","Default Printer Name is a Mandatory Field");
		//hisValidator.addValidation("tempPath", "req","Default Temp Path is a Mandatory Field");
		var retVal = hisValidator.validate(); 
		
		
	
		if(retVal)
		{	
				/*if(!document.forms[0].strExternalPatient.checked && !document.forms[0].strInternalPatient.checked)
				{
					alert("Please Select At least one check box either internal or external");
					return false ;
				}*/
				
				if(document.getElementsByName("strPaidCategoryValue")[0].value == "0")
				{
				    alert("Please Select Paid Category");	
				    return false;
				}
				/*if( document.getElementsByName("strArogyaShreeCategoryValue")[0].value == "0")
				{
				    alert("Please Select Arogya Shree Category");
				    return false;
				}
				if(document.getElementsByName("strGenCategoryValue")[0].value == "0")
				{
				    alert("Please Select General Credit category");
				    return false;
				}
				if(document.getElementsByName("strCGSHCategoryValue")[0].value == "0")
				{
				    alert("Please Select CGSH category");
				    return false;
				}
				if(document.getElementsByName("strWSHCategoryValue")[0].value == "0")
				{
				    alert("Please Select WSH category");
				    return false;
				}*/
				
				//alert('2');
				if(document.getElementsByName("strPaidCategoryValue")[0].value == document.getElementsByName("strArogyaShreeCategoryValue")[0].value)
				{
					//  alert('7');
				     if(document.getElementsByName("strPaidCategoryValue")[0].value == document.getElementsByName("strCGSHCategoryValue")[0].value)
				     {
				    	 //alert('8');
				          alert("Paid,Arogya Shree and CGSH  Category Can Not Be Same.\nPlease Choose Different Option.");
				          document.getElementsByName("strPaidCategoryValue")[0].selectedIndex = "0";
				          document.getElementsByName("strArogyaShreeCategoryValue")[0].selectedIndex = "0";
				          document.getElementsByName("strGenCategoryValue")[0].selectedIndex = "0";
				          document.getElementsByName("strCGSHCategoryValue")[0].selectedIndex = "0";
				          document.getElementsByName("strWSHCategoryValue")[0].selectedIndex = "0";
				          return false;
				     }
				    // alert('5');
				if(!document.forms[0].strExternalPatient.checked)
				{
					alert("Please Select Check Box External Patient");
					document.forms[0].strExternalPatient.focus();
					return false ;
				}
				//alert('6');
				if(document.forms[0].strExternalPatient.checked)
				{
					
					if(document.forms[0].strChargeTypeExt.value=='0')
					{
						alert("Please Select Charge Type-External Patient");
						document.forms[0].strChargeTypeExt.focus();
						return false ;
					}
					if(document.forms[0].strChargeTypeExt.value=='2' && document.forms[0].strWardTypeExt.value=='0')
					{
						alert("Please Select Ward Type-External Patient");
						document.forms[0].strWardTypeExt.focus();
						return false ;
					}
				}
			}

				if(document.forms[0].headerReq.value=="0")
				{
					var hindirad=document.forms[0].hindiReq;
					var lograd=document.forms[0].logoReq;

					for (var i=0, iLen=hindirad.length; i<iLen; i++)
						hindirad[i].disabled = false;
					for (var i=0, iLen=lograd.length; i<iLen; i++) 
						lograd[i].disabled = false;
				}
				//alert('3');
				//if(document.forms[0].strDayEndTimeBoundFlag.value=="1")//Day End Time Bound Flag Yes
					document.forms[0].strDayEndTime.value =document.forms[0].strDayEndTimeHour.value+":"+document.forms[0].strDayEndTimeMin.value+":"+document.forms[0].strDayEndTimeAMPM.value;
				document.forms[0].selectedTab.value = "GENINSERT";
				document.forms[0].submit();
		
		/* else
		{
			return false;
		} */
	}
	}	
	function initPage()
	{
	 	document.forms[0].selectedTab.value="INITIALPAGE";
		document.forms[0].submit();
	}
	function displayPrevCrSearchInt()
	{	 
		if(document.forms[0].strPreviousCrNoRequiredInt[0].checked)
		{			
			document.getElementById("prevCrNoSearchingDivIdInt").style.display = "block";
		}
		else
		{			
			document.getElementById("prevCrNoSearchingDivIdInt").style.display = "none";
			document.getElementsByName("strPreviousCrNoSearchingInt")[0].checked = false;
		}	 
	 }
	 function displayPrevCrSearchExt()
	 {	 
		if(document.forms[0].strPreviousCrNoRequiredExt[0].checked)
		{			
			document.getElementById("prevCrNoSearchingDivIdExt").style.display = "block";
		}
		else
		{
			document.getElementById("prevCrNoSearchingDivIdExt").style.display = "none";
			document.getElementsByName("strPreviousCrNoSearchingExt")[0].checked = false;
		}	 
	 }
	 
	 if(document.getElementById("logoReq")=="")
		 {
		 document.getElementById("logoReq").checked=true;
		 }
	 
	/**
	 * showDetails
	 * @param {String} divId 
	 */
	 function showDetails(divId) 
	 {
	 	document.getElementById(divId).style.display="block";
	 	document.getElementById('minus'+divId).style.display="block";
		document.getElementById('plus'+divId).style.display="none";
		showDayEndTimeOnPlus(document.getElementsByName("strDayEndTimeBoundFlag"))
	 	
	 }
	 function showDayEndTime(obj)
	 {
		 if(obj.value=="1")//Day End Time Bound Flag Yes-Show Time Row
		 	document.getElementById("TRDAYENDTIME").style.display="";
		 else
			 document.getElementById("TRDAYENDTIME").style.display="none";
	 }
	 function showDayEndTimeOnPlus(obj)
	 {
		 if(obj[0].checked)//Day End Time Bound Flag Yes-Show Time Row
		 	document.getElementById("TRDAYENDTIME").style.display="";
		 else
			 document.getElementById("TRDAYENDTIME").style.display="none";
	 }
	 function showHideDetails(obj,divId) 
	 {
	 	if(obj.checked)
	 	{
	 	document.getElementById(divId).style.display="block";
	 	}
	 	else
	 	{
	 	document.getElementById(divId).style.display="none";	 	
	 	/*document.forms[0].strChargeTypeInt.value=0;
	 	document.forms[0].strChargeTypeExt.value=0;
	 	document.forms[0].strWardTypeInt.value=0;
	 	document.forms[0].strWardTypeExt.value=0;	*/
	 	}
	 }
	
	/**
	 * hideDetails
	 * @param {String} divId 
	 */
	 function hideDetails(divId) {
	 	
	 	document.getElementById(divId).style.display="none";
	 	document.getElementById('minus'+divId).style.display="none";
		document.getElementById('plus'+divId).style.display="block";
	 	
	 }
	 
	 function enableWardType(obj,val)
	 {
	 	if(val==1 && obj.value==2)//Internal
	 	{
	 		document.forms[0].strWardTypeInt.disabled=false;
	 		document.forms[0].strWardTypeInt.value=0;
	 	}
	 	if(val==1 && obj.value!=2)//Internal
	 	{
	 		document.forms[0].strWardTypeInt.disabled=true;
	 		document.forms[0].strWardTypeInt.value=0;
	 	}
	 	if(val==2 && obj.value==2)//External
	 	{
	 		document.forms[0].strWardTypeExt.disabled=false;
	 		document.forms[0].strWardTypeExt.value=0;
	 	}
	 	if(val==2 && obj.value!=2)//External
	 	{
	 		document.forms[0].strWardTypeExt.disabled=true;
	 		document.forms[0].strWardTypeExt.value=0;
	 	}
	 }
function showIntExtDiv()
{
	if(document.forms[0].strInternalPatient.checked)
	{
		document.getElementById("withoutCrDetailsDivIdInt").style.display = "block";
	}
	else
	{
		document.getElementById("withoutCrDetailsDivIdInt").style.display = "none";
	}
	if(document.forms[0].strExternalPatient.checked)
	{
		document.getElementById("withoutCrDetailsDivIdExt").style.display = "block";
	}
	else
	{
		document.getElementById("withoutCrDetailsDivIdExt").style.display = "none";
	}
	if(document.forms[0].strChargeTypeInt.value==2)//Internal
	{
		document.forms[0].strWardTypeInt.disabled=false;
	}
	if(document.forms[0].strChargeTypeExt.value==2)//External
	{
		document.forms[0].strWardTypeExt.disabled=false;
	}
}
function setConsumableChargesTariffCombo()
{	
		var strConsumableChargesGroupId = document.forms[0].strConsumableChargesGroupId[document.forms[0].strConsumableChargesGroupId.selectedIndex].value ;
		var mode="CONSUMABLECHARGESTARIFFCOMBO";
		var url="CNTBillSetup.cnt?selectedTab="+mode+"&strConsumableChargesGroupId="+strConsumableChargesGroupId;
		//alert("url :"+url);
		ajaxFunction(url,"1");
}
function getAjaxResponse(res,mode)
{	
		if(mode==1)
		{
  			var objVal = document.getElementById("consumableChargeTariffId");
			objVal.innerHTML = "<select name = 'strConsumableChargesTariffCode'>" + res + "</select>";
		}
}

function headerUnCheck(){
	var hindirad=document.forms[0].hindiReq;
	var lograd=document.forms[0].logoReq;
	hindirad.value=0;
	lograd.value=0;
	
	for (var i=0, iLen=hindirad.length; i<iLen; i++) 
		hindirad[i].disabled = true;
	for (var i=0, iLen=lograd.length; i<iLen; i++) 
		lograd[i].disabled = true;
}
function headerCheck(){
	var hindirad=document.forms[0].hindiReq;
	var lograd=document.forms[0].logoReq;

	for (var i=0, iLen=hindirad.length; i<iLen; i++) 
		hindirad[i].disabled = false;
	for (var i=0, iLen=lograd.length; i<iLen; i++) 
		lograd[i].disabled = false;
}

</script>
	
	</head>
	
	
	<body onload='displayPrevCrSearchInt();displayPrevCrSearchExt();showIntExtDiv();'>
	<html:form action="/masters/CNTBillSetup.cnt" method="post" >
	
	<%
		//java.util.LinkedHashMap<String,String> lhm	=	new java.util.LinkedHashMap<String,String>();
		//lhm.put("General","general");
		//lhm.put("Opd","opd");
		//lhm.put("Ipd","ipd");
		//lhm.put("Emergency","emergency");
		//lhm.put("Bill Format","billFormat");
		//lhm.put("Jobs","jobs");
	%>
	<div class="errMsg"><bean:write name="billsetupbean" property="strErr"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="billsetupbean" property="strMsg"/></div>
	
	<tag:tab tabList="${billsetupbean.lhm}" selectedTab="general" align="center" width="TABLEWIDTH"></tag:tab>
	
	   
  <table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
    <tr class="HEADER"> 
      <td colspan="2" height="22">General Screen </td>
    </tr>
    <tr style='display: none;'>
      <td width="50%" class="LABEL"><font color="red">*</font>Financial Start Year</td>
      <td width="50%" class="CONTROL"><dateTag:date name="finStartYear" value="${billsetupbean.finStartYear}" /></td>
    </tr>
    <tr> 
      <td width="50%"  class="LABEL"><font color="red">*</font>Days For Purging Unprocessed Record From In-Bound</td>
      <td width="50%"  class="CONTROL"><input type="text" name="inBound" class="txtFldNormal"  value="${billsetupbean.inBound}" maxlength="3" onkeypress="return validateData(event,5);"> Day(s)</td>
    </tr>
    <tr> 
      <td class="LABEL"><font color="red">*</font>Days For Purging Unprocessed Record From Out-Bound</td>
      <td class="CONTROL"><input type="text" name="outBound" class="txtFldNormal" value="${billsetupbean.outBound}" maxlength="3" onkeypress="return validateData(event,5);"> Day(s)</td>
    </tr>
    <tr> 
      <td class="LABEL"><font color="red">*</font>Cancellation Time</td>
      <td class="CONTROL"><input type="text" name="cancelTime" class="txtFldNormal" onkeypress="return validateData(event,5);" maxlength="2" value="${billsetupbean.cancelTime}" >
        minute</td>
    </tr>	
    <tr>
    <td class="LABEL">Receipt Type
    </td>
    <td class="CONTROL"> <html:radio name="billsetupbean" property="strReceiptType" value="1">Bill Cum Receipt</html:radio>
     <html:radio name="billsetupbean" property="strReceiptType" value="2">Receipt</html:radio>
    </td>
    </tr>
    <tr>
    <td class="LABEL">Duplicate Print
    </td>
    <td class="CONTROL"> 
<!--    <html:radio name="billsetupbean" property="strDuplicatePrint" value="1">Consolidated</html:radio>-->
     <html:radio name="billsetupbean" property="strDuplicatePrint" value="2">Replica of Original</html:radio>
    </td>
    </tr>
    <tr>
    <td class="LABEL">Refund Receipt Type
    </td>
    <td class="CONTROL"> <html:radio name="billsetupbean" property="strRefundReceiptType" value="1">Refund Receipt</html:radio>
     <html:radio name="billsetupbean" property="strRefundReceiptType" value="2">Revised Bill</html:radio>
    </td>
    </tr>
     <tr>
    <td class="LABEL">Whether Without CR. No. Screen Required
    </td>
    <td class="CONTROL"> <html:checkbox name="billsetupbean" property="strIsWithoutCrNoRequired" value="1" disabled="true"></html:checkbox>
   
    </td>
    </tr>
 	<tr style="display:none">
    <td class="LABEL">Cash Collection Confirmation Type
    </td>
    <td class="CONTROL"> <html:radio name="billsetupbean" property="strCcConfirmationType" value="1">Ok</html:radio>
     <html:radio name="billsetupbean" property="strCcConfirmationType" value="0">Cancel</html:radio>
    </td>
    </tr>
    <tr>
    <td class="LABEL">Online Refund Request Allowed
    </td>
    <td class="CONTROL"> <html:radio name="billsetupbean" property="strOnlineRefundRequestAllowed" value="1">Yes</html:radio>
     <html:radio name="billsetupbean" property="strOnlineRefundRequestAllowed" value="0">No</html:radio>
    </td>
    </tr>
     <tr>
    <td class="LABEL">CashCollection (Offline) Refund Required
    </td>
    <td class="CONTROL"> <html:radio name="billsetupbean" property="strCashCollectionOfflineRefundRequired" value="1">Yes</html:radio>
     <html:radio name="billsetupbean" property="strCashCollectionOfflineRefundRequired" value="0">No</html:radio>
    </td>
    </tr>
     <tr> 
      <td class="LABEL">Print Message Limit</td>
      <td class="CONTROL"><input type="text" name="strPrintMessageLimit" class="txtFldMin" onkeypress="return validateData(event,5);" maxlength="1" value="${billsetupbean.strPrintMessageLimit}" >
        </td>
    </tr>
    <tr>
    <td class="LABEL">Refund against Refund Request 
    </td>
    <td class="CONTROL"> <html:radio name="billsetupbean" property="strRefundAgainstRefundRequest" value="1">Same User</html:radio>
     <html:radio name="billsetupbean" property="strRefundAgainstRefundRequest" value="0">All</html:radio>
    </td>
    </tr>
    <tr>
    	<td class="LABEL">Whether Approval By Required (Advance & Part Payment)</td>
    	<td class="CONTROL"> <html:checkbox name="billsetupbean" property="strIsApprovalByRequired" value="1"></html:checkbox></td>
    </tr>
    <tr>
    	<td class="LABEL">Credit/Cashless Bill Approval Required</td>
    	<td class="CONTROL"> 
    		<html:radio name="billsetupbean" property="strCreditCashlessAppRequired" value="1">Yes</html:radio>
     		<html:radio name="billsetupbean" property="strCreditCashlessAppRequired" value="0">No</html:radio>
    </td>
    </tr>
    
    
    <tr>
    <td class="LABEL"><font color="red">*</font>Paid category</td>
    	<td class="CONTROL">
    	<html:select name="billsetupbean" property="strPaidCategoryValue" >
			<bean:write name="billsetupbean"  property="strPaidCategoryValue"  filter="false" />
	    </html:select>
    </td>
    </tr>
    
    <tr style="display:none">
    	<td class="LABEL"><font color="red">*</font>Arogya Shree AP Category(Dr. NTR VaidyaSevaTruct (DrNTRVST) AP)</td>
    	<td class="CONTROL">
      	<html:select name="billsetupbean" property="strArogyaShreeCategoryValue" >
			<bean:write name="billsetupbean" property="strArogyaShreeCategoryValue"   filter="false" />
	    </html:select>
    </td>
    </tr>    
    <tr>
    	<td class="LABEL">OSTF category</td>
    	<td class="CONTROL">
      	<html:select name="billsetupbean" property="strOSTFCategoryvalue" >
			<bean:write name="billsetupbean" property="strOSTFCategoryvalue" filter="false" />
	    </html:select>
    </td>
    </tr>
    <tr>
    <td class="LABEL"> Niramaya category
    </td>
    <td class="CONTROL">
    <html:select name="billsetupbean" property="niramayaValue">
    		<bean:write name="billsetupbean" property="niramayaValue" filter="false"/>
    </html:select>
    </td>
    </tr>
        <tr style="display: none">
    	<td class="LABEL">Arogya Shree TS Category</td>
    	<td class="CONTROL">
      	<html:select name="billsetupbean" property="strArogyaShreeTSCategoryValue" >
			<bean:write name="billsetupbean" property="strArogyaShreeTSCategoryValue"   filter="false" />
	    </html:select>
    </td>
    </tr>
    
    <tr style="display: none">
    	<td class="LABEL">Arogya Shree TS Category Credit Limit</td>
    	<td class="CONTROL">
      	<input type="text" name="strArogyaTSCreditLimit" class="txtFldMin" onkeypress="return validateData(event,5);" maxlength="4" value="${billsetupbean.strArogyaTSCreditLimit}" >
    </td>
    </tr>
    
    <tr>
    	<td class="LABEL">General Credit Category</td>
    	<td class="CONTROL">
    	<html:select name="billsetupbean" property="strGenCategoryValue" >
			<bean:write name="billsetupbean" property="strGenCategoryValue"   filter="false" />
	    </html:select>
    </td>
    </tr>
    
    <tr>
    	<td class="LABEL">CGSH Credit Category</td>
    	<td class="CONTROL">
    	<html:select name="billsetupbean" property="strCGSHCategoryValue" >
			<bean:write name="billsetupbean" property="strCGSHCategoryValue"   filter="false" />
	    </html:select>
    </td>
    </tr>
    
    
    
    <tr>
    	<td class="LABEL"><font color="red">*</font>WCH Category</td>
    	<td class="CONTROL">
    	<html:select name="billsetupbean" property="strWSHCategoryValue" >
			<bean:write name="billsetupbean" property="strWSHCategoryValue"   filter="false" />
	    </html:select>
    </td>
    </tr>
    
    <tr>
    	<td class="LABEL"><font color="red">*</font>Urgent Tariff Surcharge</td>
    	<td class="CONTROL">
    	<input type="text" name="strUrgTrfSur" value="0" class="txtFldMin" onkeypress="return validateData(event,7);" maxlength="6,2" value="${billsetupbean.strUrgTrfSur}" >%
        </td>
    </tr>
    <tr>
    	<td class="LABEL"><font color="red">*</font>Header Required</td>
    	<td class="CONTROL"> 
    			<html:radio name="billsetupbean" property="headerReq" onclick="headerCheck();" value="1">Yes</html:radio>
     			<html:radio name="billsetupbean" property="headerReq" onclick="headerUnCheck();" value="0">No</html:radio>
    	</td>
    </tr>
    
    <tr>
    	<td class="LABEL"><font color="red">*</font>Is Logo Required</td>
    	<td class="CONTROL"> 
    			<html:radio name="billsetupbean" property="logoReq" value="1">Yes</html:radio>
     			<html:radio name="billsetupbean" property="logoReq" value="0">No</html:radio>
    	</td>
    </tr>
    <tr>
    	<td class="LABEL"><font color="red">*</font>Hindi Header Required</td>
    	<td class="CONTROL"> 
    			<html:radio name="billsetupbean" property="hindiReq" value="1">Yes</html:radio>
     			<html:radio name="billsetupbean" property="hindiReq" value="0">No</html:radio>
    	</td>
    </tr>  
    
    <tr style="display: none;">
      <td class="LABEL"><font color="red">*</font>Default Tariff Code(Consumables)</td>
      <td class="CONTROL"><html:select name="billsetupbean" property="strDefaultTariffCode" >
  	 <bean:write property="strGeneralTariffValues" name="billsetupbean"
				filter="false" />
    </html:select></td>
    </tr>
    <tr style="display: none">
      <td class="LABEL"><font color="red">*</font>Consumable Charges Group</td>
      <td class="CONTROL">
      	
      	<html:select name="billsetupbean" property="strConsumableChargesGroupId"  onchange="setConsumableChargesTariffCombo()">
      			<bean:write property="strConsumableChargesGroupIdValues" name="billsetupbean" filter="false" />
   		</html:select>
   		
   	  </td>
    </tr>
	<tr style="display: none">
		<td class="LABEL"><font color="red">*</font>Consumable Charges Tariff</td>
		<td class="CONTROL">
		<div id="consumableChargeTariffId" style="display: block"><html:select
			name="billsetupbean" property="strConsumableChargesTariffCode" >
			<bean:write property="strConsumableChargesTariffValues"
				name="billsetupbean" filter="false" />
		</html:select></div>
		</td>
	</tr>
</table>
 <table class="TABLEWIDTH" align="center" cellspacing ="1px" style="display: none">
  <tr> 
    <td width="5%" class="TITLE" align="center">
    	<html:checkbox name="billsetupbean" property="strInternalPatient" value="1" onclick="showHideDetails(this,'withoutCrDetailsDivIdInt');"></html:checkbox></td>
    <td colspan="3" class="TITLE"><b>Internal Patient(CR No not generated using Registration)</b></td> 
   </tr>
 </table>
 <div id="withoutCrDetailsDivIdInt" style="display: none">
    <table class="TABLEWIDTH" align="center" cellspacing ="1px" style="display: none">
      <tr>
    		<td class="LABEL" width="50%">Is Referring Physician Required</td>
    		<td class="CONTROL" width="50%"> 
    			<html:radio name="billsetupbean" property="strReferringPhysicianRequiredInt" value="1">Yes</html:radio>
     			<html:radio name="billsetupbean" property="strReferringPhysicianRequiredInt" value="0" >No</html:radio>
    		</td>
    </tr>    
    <tr>
    		<td class="LABEL">Is Previous CR No. Required</td>
    		<td class="CONTROL"> 
    			<html:radio name="billsetupbean" property="strPreviousCrNoRequiredInt" value="1" onclick="displayPrevCrSearchInt();">Yes</html:radio>
     			<html:radio name="billsetupbean" property="strPreviousCrNoRequiredInt" value="0" onclick="displayPrevCrSearchInt();" >No</html:radio>
    		</td>
    </tr>
    </table>
    <div id="prevCrNoSearchingDivIdInt" style="display: none;">
	    <table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px" style="display: none">
		     <tr>
		    		<td class="LABEL" width="50%" >Whether Searching Required On Previous CR No.</td>
		    		<td class="CONTROL" width="50%">
		    			<html:checkbox property="strPreviousCrNoSearchingInt" name="billsetupbean" value="1" ></html:checkbox> 
		    		</td>
		    </tr>
	    </table>
    </div>
     
    <table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px" style="display: none">
    <tr>
    	<td class="LABEL" width="50%" >Charge Type</td>
    	<td class="CONTROL" width="50%">
    		<html:select  property="strChargeTypeInt" name="billsetupbean"  onchange="enableWardType(this,1)"  >
    			<bean:write property="strChargeTypeValueInt" name="billsetupbean" filter="false" />		
    		</html:select>
    </td>
    </tr>
    <tr>
	    <td class="LABEL" width="50%" >Ward Type</td>
	    <td class="CONTROL" width="50%">
		    <html:select property="strWardTypeInt" name="billsetupbean"  disabled="true">    
		     	<bean:write property="strWardTypeValueInt" name="billsetupbean" filter="false" /> 
		    </html:select> 
	    </td>
    </tr>
    </table>
   </div> 
   <table class="TABLEWIDTH" align="center" cellspacing ="1px">
   <tr> 
    <td width="5%" class="TITLE" align="center">
   		<html:checkbox name="billsetupbean" property="strExternalPatient" value="1" onclick="showHideDetails(this,'withoutCrDetailsDivIdExt');"></html:checkbox>
    <td colspan="3" class="TITLE"><b>External Patient</b></td> 
   </tr>
 </table>
      
   <div id="withoutCrDetailsDivIdExt" style="display: none">
    <table class="TABLEWIDTH" align="center" cellspacing ="1px">    
	    <tr>
		    <td class="LABEL" width="50%">Is Referring Physician Required</td>
		    <td class="CONTROL" width="50%"> 
		    	<html:radio name="billsetupbean" property="strReferringPhysicianRequiredExt" value="1">Yes</html:radio>
		     	<html:radio name="billsetupbean" property="strReferringPhysicianRequiredExt" value="0">No</html:radio>
		    </td>
	    </tr>	    
	    <tr>
		    <td class="LABEL">Is Previous CR No. Required</td>
		    <td class="CONTROL"> 
		    	<html:radio name="billsetupbean" property="strPreviousCrNoRequiredExt" value="1" onclick="displayPrevCrSearchExt();">Yes</html:radio>
		     	<html:radio name="billsetupbean" property="strPreviousCrNoRequiredExt" value="0" onclick="displayPrevCrSearchExt();" >No</html:radio>
		    </td>
	    </tr>
	    </table>
	    <div id="prevCrNoSearchingDivIdExt" style="display: none;">
	    <table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
	     <tr>
	    	<td class="LABEL" width="50%" >Whether Searching Required On Previous CR No.</td>
	    	<td class="CONTROL" width="50%">
	    		<html:checkbox property="strPreviousCrNoSearchingExt" name="billsetupbean" value="1" ></html:checkbox> 
	    	</td>
	    </tr>
    </table>
    </div> 
    <table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
    <tr>
	    <td class="LABEL" width="50%" ><font color="red">*</font>Charge Type</td>
	    <td class="CONTROL" width="50%">
	    <html:select property="strChargeTypeExt" name="billsetupbean"  onchange="enableWardType(this,2)" >
	      <bean:write property="strChargeTypeValueExt" name="billsetupbean" filter="false" /> 
	    </html:select> 
	    </td>
    </tr>
    <tr>
	    <td class="LABEL" width="50%" ><font color="red">*</font>Ward Type</td>
	    <td class="CONTROL" width="50%">
	    	<html:select property="strWardTypeExt" name="billsetupbean"  disabled="true"  >
	     		<bean:write property="strWardTypeValueExt" name="billsetupbean" filter="false" />			
	    	</html:select> 
	    </td>
    </tr>
    </table>
   </div> 
    
    
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
  <tr> 
    <td width="5%" class="TITLE" align="center">
    	<img src="/HBIMS/hisglobal/images/plus.gif"  width="15" height="15" id="plusdayEndDetailsDivId" style="display:block;cursor:pointer;cursor:hand" onClick="showDetails('dayEndDetailsDivId');">
    	<img src="/HBIMS/hisglobal/images/minus.gif" height="15" width="15" id="minusdayEndDetailsDivId" style="display:none;cursor:pointer;cursor:hand" onClick="hideDetails('dayEndDetailsDivId');"></td>
    <td colspan="3" class="TITLE"><b>Day End Process</b></td> 
  </tr>
 </table>
 <div id="dayEndDetailsDivId" style="display: none">
 
 <table class="TABLEWIDTH" align="center" cellspacing ="1px">
    <tr>
    	<td colspan="2" class="TITLE"> Day End Process (Billing)</td>
    </tr>
    <tr> 
      <td class="LABEL"><font color="red">*</font>Day End Process Time Bound</td>
      <td class="CONTROL">
      	<html:radio name="billsetupbean" property="strDayEndTimeBoundFlag" value="1" onchange='showDayEndTime(this)'>Yes</html:radio>
      	<html:radio name="billsetupbean" property="strDayEndTimeBoundFlag" value="0" onchange='showDayEndTime(this)'>No</html:radio></td>
    </tr>
    <tr id='TRDAYENDTIME'> 
      <td class="LABEL"><font color="red">*</font>Day End Time</td>
      <td class="CONTROL">
      	<html:select name="billsetupbean" property="strDayEndTimeHour" value="${billsetupbean.strDayEndTimeHour}">
			<html:option value="01">01</html:option>
			<html:option value="02">02</html:option>
			<html:option value="03">03</html:option>
			<html:option value="04">04</html:option>
			<html:option value="05">05</html:option>
			<html:option value="06">06</html:option>
			<html:option value="07">07</html:option>
			<html:option value="08">08</html:option>
			<html:option value="09">09</html:option>
			<html:option value="10">10</html:option>
			<html:option value="11">11</html:option>
			<html:option value="12">12</html:option>			
	    </html:select>
	    <html:select name="billsetupbean" property="strDayEndTimeMin" value="${billsetupbean.strDayEndTimeMin}">
			<html:option value="00">00</html:option>
			<html:option value="01">01</html:option>
			<html:option value="02">02</html:option>
			<html:option value="03">03</html:option>
			<html:option value="04">04</html:option>
			<html:option value="05">05</html:option>
			<html:option value="06">06</html:option>
			<html:option value="07">07</html:option>
			<html:option value="08">08</html:option>
			<html:option value="09">09</html:option>
			<html:option value="10">10</html:option>
			<html:option value="11">11</html:option>
			<html:option value="12">12</html:option>
			<html:option value="13">13</html:option>
			<html:option value="14">14</html:option>
			<html:option value="15">15</html:option>
			<html:option value="16">16</html:option>
			<html:option value="17">17</html:option>
			<html:option value="18">18</html:option>
			<html:option value="19">19</html:option>
			<html:option value="20">20</html:option>
			<html:option value="21">21</html:option>
			<html:option value="22">22</html:option>
			<html:option value="23">23</html:option>
			<html:option value="24">24</html:option>
			<html:option value="25">25</html:option>
			<html:option value="26">26</html:option>
			<html:option value="27">27</html:option>
			<html:option value="28">28</html:option>
			<html:option value="29">29</html:option>
			<html:option value="30">30</html:option>
			<html:option value="31">31</html:option>
			<html:option value="32">32</html:option>
			<html:option value="33">33</html:option>
			<html:option value="34">34</html:option>
			<html:option value="35">35</html:option>
			<html:option value="36">36</html:option>
			<html:option value="37">37</html:option>
			<html:option value="38">38</html:option>
			<html:option value="39">39</html:option>
			<html:option value="40">40</html:option>
			<html:option value="41">41</html:option>
			<html:option value="42">42</html:option>
			<html:option value="43">43</html:option>
			<html:option value="44">44</html:option>
			<html:option value="45">45</html:option>
			<html:option value="46">46</html:option>
			<html:option value="47">47</html:option>
			<html:option value="48">48</html:option>
			<html:option value="49">49</html:option>
			<html:option value="50">50</html:option>
			<html:option value="51">51</html:option>
			<html:option value="52">52</html:option>
			<html:option value="53">53</html:option>
			<html:option value="54">54</html:option>
			<html:option value="55">55</html:option>
			<html:option value="56">56</html:option>
			<html:option value="57">57</html:option>
			<html:option value="58">58</html:option>
			<html:option value="59">59</html:option>
	    </html:select>
	    <html:select name="billsetupbean" property="strDayEndTimeAMPM" value="${billsetupbean.strDayEndTimeAMPM}">
			<html:option value="AM">AM</html:option>
			<html:option value="PM">PM</html:option>
	    </html:select>
	    <input 	type="hidden" name="strDayEndTime" >
      </td>
    </tr> 
    <tr>
	    <td class="LABEL" width="50%">Process Type</td>
	    <td class="CONTROL" width="50%"> 
	    	<html:radio name="billsetupbean" property="strDayEndProcessType" value="1">User Wise</html:radio>
	     	<!--<html:radio name="billsetupbean" property="strDayEndProcessType" value="2">User + Counter Wise</html:radio>-->
	    </td>
    </tr>
    <tr>
    	<td class="LABEL">Date Type</td>
    	<td class="CONTROL"> 
    		<html:radio name="billsetupbean" property="strDayEndDateType" value="1">Current Date Only</html:radio>
     		<html:radio name="billsetupbean" property="strDayEndDateType" value="2">Back Date Allowed</html:radio>
    	</td>
    </tr>
    <tr>
    	<td colspan="2" class="TITLE"> Day End Process (Except Billing)</td>
    </tr>
    <tr>
    	<td class="LABEL" width="50%">Process Type</td>
    	<td class="CONTROL" width="50%"> 
    		<html:radio name="billsetupbean" property="strDayEndNonBillingProcessType" value="1">User Wise</html:radio>
     		<!--<html:radio name="billsetupbean" property="strDayEndNonBillingProcessType" value="2">User + Counter Wise</html:radio>-->
    	</td>
    </tr>
     <tr>
    <td class="LABEL">Date Type</td>
    <td class="CONTROL"> 
    	<html:radio name="billsetupbean" property="strDayEndNonBillingDateType" value="1">Current Date Only</html:radio>
     	<html:radio name="billsetupbean" property="strDayEndNonBillingDateType" value="2">Back Date Allowed</html:radio>
    </td>
    </tr>
    </table>
 
 </div>
    
  <table class="TABLEWIDTH" align="center" cellspacing ="1px" cellpadding="1px">
  <tr> 
    <td width="5%" class="TITLE" align="center">
    <img src="/HBIMS/hisglobal/images/plus.gif"  width="15" height="15" id="plusreceiptDetailsDivId" style="display:block;cursor:pointer;cursor:hand" onClick="showDetails('receiptDetailsDivId');">
    <img src="/HBIMS/hisglobal/images/minus.gif" height="15" width="15" id="minusreceiptDetailsDivId" style="display:none;cursor:pointer;cursor:hand" onClick="hideDetails('receiptDetailsDivId');"></td>
    <td colspan="3" class="TITLE"><b>Receipt</b></td> 
   </tr>
 </table>
 <div id="receiptDetailsDivId" style="display: none">
 
  <table class="TABLEWIDTH" align="center" cellspacing ="1px" cellpadding="1px">
  <tr>
      <td class="LABEL" width="50%"><font color="red">*</font>Duplicate Print Charge</td>
      <td class="CONTROL" width="50%"><input type="text" name="printCharge" class="txtFldNormal" value="${billsetupbean.printCharge}" maxlength="9" onkeypress="return validateData(event,7);"> (in <img src='/HBIMS/hisglobal/images/INR.png'>)</td>
  </tr>
  <tr style="display:none">
    		<td class="LABEL">OPD Receipt</td>
    		<td class="CONTROL"> <html:radio name="billsetupbean" property="strOpdReceiptType" value="1">Group Wise</html:radio>
     			<html:radio name="billsetupbean" property="strOpdReceiptType" value="2">Tariff Wise</html:radio>
    		</td>
  </tr>
  <tr style="display:none">
    	<td class="LABEL">IPD Receipt</td>
    	<td class="CONTROL" > <html:radio name="billsetupbean" property="strIpdReceiptType" value="1">Group Wise</html:radio>
     		<html:radio name="billsetupbean" property="strIpdReceiptType" value="2">Tariff Wise</html:radio>
    	</td>
  </tr>
    
  </table>
 
 </div>
    
    <table align="center" class="TABLEWIDTH" cellpadding="1px" cellspacing="1px">
    	<tr class="FOOTER"> 
      		<td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields </tr>
  	</table>
	
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
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
		
	<input 	type="hidden" name="selectedTab" >
	
	</html:form>
	<tag:autoIndex></tag:autoIndex>
	</body>
	
</html>