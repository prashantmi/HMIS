<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
 <%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>

<script language="JavaScript">
	
	function validate1(){	
		
		//alert("inside validate1");
		//var hisValidator = new HISValidator("remarkBean");
		//alert("Objec :"+hisValidator);
		//hisValidator.addValidation("finStartYear", "req","First Start Year is a Madatory Field");
		//hisValidator.addValidation("finStartYear", "date","First Start Year should be a valid Date");
		//hisValidator.addValidation("inBound", "req","Record From In Bound is a Madatory Field");
		//hisValidator.addValidation("inBound", "maxlen=50", "Remarks Description should have less than or equal to 50 Characters" );
		//hisValidator.addValidation("remarksType", "dontselect=0","Please select a value from Remarks For Combo");
		
		//var retVal = hisValidator.validate(); 
		//	alert(retVal);
		retVal = true;
		if(retVal){
			document.billsetupbean.selectedTab.value = "IPDBEDCALC";
			//alert("inside if");
			document.billsetupbean.submit();
		}else{
			return false;
		}
		
	}
	
	
	function radioClick(id){
	
		
		if(document.billsetupbean.radiobutton[1].checked)
		{
		
		document.billsetupbean.ipdBedCalcWard.selectedIndex = 0;
		document.billsetupbean.ipdBedCalcWard.disabled = true;
		document.billsetupbean.wardText.value = "";
		
			document.getElementById(id).style.display="block";
			
		}
		else 
		{
		document.billsetupbean.ipdBedCalcWard.selectedIndex = 0;
		document.billsetupbean.ipdBedCalcWard.disabled = false;
	
			document.getElementById(id).style.display="none";
		}
	}



function wardSetter(objVal){
		
	if(objVal.value == "0"){
		document.billsetupbean.wardText.value = "";
		document.billsetupbean.radiobutton[1].checked = true;
	}else {
		document.billsetupbean.wardText.value = objVal.options[objVal.selectedIndex].text; 
		document.billsetupbean.radiobutton[0].checked = true;
	}	
}

function initPage(){
	 	document.forms[0].selectedTab.value="INITIALPAGE";
		document.forms[0].submit();
	 }



	</script>

<head><meta charset=utf-8>
<title>IPD Bed Calculation</title>
</head>

<body onLoad="wardSetter(document.forms[0].ipdBedCalcWard);">

<html:form action="/masters/CNTBillSetup.cnt" method="post" >

<div class="errMsg"><bean:write name="billsetupbean" property="strErr"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="billsetupbean" property="strMsg"/></div>
	
	
<tag:tab tabList="${billsetupbean.lhm}" selectedTab="ipd" align="center" width="TABLEWIDTH"></tag:tab>
<tag:tab tabList="${billsetupbean.lhm1}" selectedTab="bedCalculationRule" align="center" width="TABLEWIDTH"></tag:tab>

<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">

	<tr class="HEADER">
		<td colspan="2" height="22" width="50%">Bed Calculation Screen </td>
	</tr>

	<tr class="TITLE">

		<td colspan="2" width="50%">
		<logic:equal name="billsetupbean" property="ipdBedCalcWard" value="0">
		<input type="radio"
			name="radiobutton" value="radiobutton" checked='checked'
			onClick="radioClick('transWard')" >
		</logic:equal>
		<logic:notEqual name="billsetupbean" property="ipdBedCalcWard" value="0">
		<input type="radio"
			name="radiobutton" value="radiobutton" 
			onClick="radioClick('transWard')" >
		</logic:notEqual>
		 Whether
		All The Charges (Service Charge + Bed Charge) from day 1 of 
		<logic:equal name="billsetupbean" property="ipdBedCalcWard" value="0">
		<select name="ipdBedCalcWard" class="comboMax" disabled='disabled' onChange="wardSetter(this);">
		<bean:write name="billsetupbean" property="ipdBedCalcWardValues" filter="false"/>
		</select> 
		</logic:equal>
		<logic:notEqual name="billsetupbean" property="ipdBedCalcWard" value="0">
		<select name="ipdBedCalcWard" class="comboMax" onChange="wardSetter(this);">
		<bean:write name="billsetupbean" property="ipdBedCalcWardValues" filter="false"/>
		</select> 
		</logic:notEqual>
		Ward Will be imposed if Has Availed 
		<logic:equal name="billsetupbean" property="ipdBedCalcWard" value="0">
		<input type="text" class="txtFldMax" disabled='disabled'
			name="wardText"> 
			 </logic:equal>
			 <logic:notEqual name="billsetupbean" property="ipdBedCalcWard" value="0">
			 <input type="text" class="txtFldMax" value=""
			name="wardText"> 
			 </logic:notEqual>
			 Ward at any time during
		his stay.</td>
	</tr>

	<tr class="TITLE">
		<td colspan="2" height="22">
		<logic:equal name="billsetupbean" property="ipdBedCalcWard" value="0">
		<input type="radio"
			name="radiobutton" value="radiobutton" checked='checked'
			onClick="radioClick('transWard')" >
		</logic:equal>
		<logic:notEqual name="billsetupbean" property="ipdBedCalcWard" value="0">
		<input type="radio"
			name="radiobutton" value="radiobutton" 
			onClick="radioClick('transWard')" >
		</logic:notEqual> Transferred in more than
		One Ward</td>
	</tr>
	<tr>
		<td colspan="2">
		<logic:equal name="billsetupbean" property="ipdBedCalcWard" value="0">
		<div id="transWard" style="display:none">
		<table width="100%">
			<tr>
				<td class="LABEL" width="50%">Bed Charges Ward that has</td>
				<td class="CONTROL" width="50%">
				
				<html:radio name="billsetupbean" property="ipdBedCalcWardCharg" value="1">Max Charge</html:radio>
				<html:radio name="billsetupbean" property="ipdBedCalcWardCharg" value="0">Min Charge</html:radio>
				<html:radio name="billsetupbean" property="ipdBedCalcWardCharg" value="2">Ward Wise</html:radio>
				</td>
			</tr>

			<tr>
				<td class="LABEL" width="50%">Charges For Services</td>
				<td class="CONTROL" width="50%">
				
				<html:radio name="billsetupbean" property="ipdBedCalcServiceCharge" value="1">Raising Ward</html:radio>
				<html:radio name="billsetupbean" property="ipdBedCalcServiceCharge" value="0">Based on above rule</html:radio>
			</td>
			</tr>
		</table>
		</div>
		</logic:equal>
		<logic:notEqual name="billsetupbean" property="ipdBedCalcWard" value="0">
		<div id="transWard" style="display:none">
		<table width="100%" cellpadding="1px" cellspacing="1px">
			<tr>
				<td class="LABEL" width="50%">Bed Charges Ward that has</td>
				<td class="CONTROL" width="50%">
				
				<html:radio name="billsetupbean" property="ipdBedCalcWardCharg" value="1">Max Charge</html:radio>
				<html:radio name="billsetupbean" property="ipdBedCalcWardCharg" value="0">Min Charge</html:radio>
				<html:radio name="billsetupbean" property="ipdBedCalcWardCharg" value="2">Ward Wise</html:radio>
				</td>
			</tr>

			<tr>
				<td class="LABEL" width="50%">Charges For Services</td>
				<td class="CONTROL" width="50%">
				
				<html:radio name="billsetupbean" property="ipdBedCalcServiceCharge" value="1">Raising Ward</html:radio>
				<html:radio name="billsetupbean" property="ipdBedCalcServiceCharge" value="0">Based on above rule</html:radio>
			</td>
			</tr>
		</table>
		</div>
		</logic:notEqual>
		</td>
	</tr>

	<tr class="FOOTER">
		<td colspan="2" height="22"></td>
	</tr>

</table>

<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
             
			<td align="right">
			<img
				src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save the Record" onClick="validate1();" />
			
			</td>
			<td align="left">
			<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title="Cancel the Process"
				onClick="initPage();" />
			</td>
		</tr>
	</table>


<input type="hidden" name="selectedTab"></html:form>
<tag:autoIndex></tag:autoIndex>
</body>
<cmbPers:cmbPers/>
</html>