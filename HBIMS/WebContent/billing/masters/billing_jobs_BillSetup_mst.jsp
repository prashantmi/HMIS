<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>

<head><meta charset=utf-8>
<title>Jobs</title>

<script type="text/javascript">
	
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
				document.forms[0].selectedTab.value = "JOBINSERT";
			
		//alert("inside if");
				document.forms[0].submit();
		}else{
		
		return false;
		}
		
		/*
		alert("page submit");
		return false;
		*/
	}
   
    function checkClick(id)
	{
	
		if(document.forms[0].admittedPatient.checked)
		{
			
			document.getElementById(id).style.display="block";
		}
		else 
		{
			document.getElementById(id).style.display="none";
		}
	}
	
	function checkClick1(id)
	{
		if(document.forms[0].generalActivity.checked)
		{
			document.getElementById(id).style.display="block";
		}
		else 
		{
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

<form action="CNTBillSetup.cnt" name="billsetupbean"
	 method="post">


<div class="errMsg"><bean:write name="billsetupbean" property="strErr"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="billsetupbean" property="strMsg"/></div>

<tag:tab tabList="${billsetupbean.lhm}" selectedTab="jobs" align="center"
	width="TABLEWIDTH"></tag:tab>

<table align="center" class="TABLEWIDTH">

	<tr class="HEADER">
		<td colspan="2" height="22">Jobs Screen</td>
	</tr>

	<tr class="TITLE">
		<td colspan="2"><html:checkbox name="billsetupbean" property="admittedPatient" value="1" 
			onclick="checkClick('adPatient')" />Whether Run The Job
		Scheduler For Updating Charges For Admitted Patient</td>

	</tr>

	<tr>
		<td colspan="2">
		<logic:equal name="billsetupbean" property="admittedPatient"  value="1">
		<div id="adPatient" style="display:block">
		<table width="100%" border="0">
		<tr>
				<td class="LABEL" width="60%">Whether Update Bed Charges</td>
				<td class="CONTROL" width="40%"><html:radio name="billsetupbean" property="job1BedCharge" value="1" />Yes
<html:radio name="billsetupbean" property="job1BedCharge" value="0" />No
</td>
			</tr>

			<tr>
				<td class="LABEL" width="60%">Whether Update Compulsory Charges</td>
				<td class="CONTROL" width="40%"><html:radio name="billsetupbean" property="job1CompusoryCharge" value="1" />Yes
<html:radio name="billsetupbean" property="job1CompusoryCharge" value="0" />No
				</td>
			</tr>
           </table>
		</div>
		</logic:equal>
		<logic:notEqual name="billsetupbean" property="admittedPatient"  value="1">
		<div id="adPatient" style="display:none">
		<table width="100%" border="0">
		<tr>
				<td class="LABEL" width="60%">Whether Update Bed Charges</td>
				<td class="CONTROL" width="40%"><html:radio name="billsetupbean" property="job1BedCharge" value="1" />Yes
<html:radio name="billsetupbean" property="job1BedCharge" value="0" />No
</td>
			</tr>

			<tr>
				<td class="LABEL" width="60%"> Whether Update Compulsory Charges</td>
				<td class="CONTROL" width="40%"><html:radio name="billsetupbean" property="job1CompusoryCharge" value="1" />Yes
<html:radio name="billsetupbean" property="job1CompusoryCharge" value="0" />No
				</td>
			</tr>
           </table>
		</div>
		</logic:notEqual>
		</td>
	</tr>

	<tr class="TITLE">
		<td colspan="2"><html:checkbox name="billsetupbean" property="generalActivity" value="1"
			onclick="checkClick1('genActivity')" />Whether Run The Job
		Scheduler For General Activity</td>

	</tr>

	<tr>
		<td colspan="2">
	  	<logic:equal name="billsetupbean" property="generalActivity"  value="1">
		<div id="genActivity" style="display:block"> 
		<table width="100%" border="0">
             <tr>
				<td class="LABEL" width="60%">Whether Update Financial Start Year</td>
			    <td class="CONTROL" width="40%">
			    <html:radio name="billsetupbean" property="job2FinStartYear" value="1"/>Yes
<html:radio name="billsetupbean" property="job2FinStartYear" value="0"/>No </td>
			</tr>

			<tr>
				<td class="LABEL" width="60%">Where Update Compulsary Charges For Emergancy
				Patient</td>
				<td class="CONTROL" width="40%"><html:radio name="billsetupbean" property="job2EmgCompusoryCharge" value="1" />Yes
<html:radio name="billsetupbean" property="job2EmgCompusoryCharge" value="0" />No</td>
			</tr>

		</table>
		</div>
		</logic:equal>
		<logic:notEqual name="billsetupbean" property="generalActivity"  value="1">
		<div id="genActivity" style="display:none"> 
		<table width="100%" border="0">
             <tr>
				<td class="LABEL" width="60%">Whether Update Financial Start Year</td>
				<td class="CONTROL" width="40%"><html:radio name="billsetupbean" property="job2FinStartYear" value="1" />Yes
<html:radio name="billsetupbean" property="job2FinStartYear" value="0" />No</td>
			</tr>

			<tr>
				<td class="LABEL" width="60%">Where Update Compulsary Charges For Emergancy
				Patient</td>
				<td class="CONTROL" width="40%"><html:radio name="billsetupbean" property="job2EmgCompusoryCharge" value="1" />Yes
<html:radio name="billsetupbean" property="job2EmgCompusoryCharge" value="0" />No</td>
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

<table border="0" class="TABLEWIDTH" align="center">
	<tr>

		<!-- <td align="right"><img
			src="../../hisglobal/images/btn-sv.png" style="cursor:pointer;cursor:hand;" title="Save Record" onClick="validate1();" />

		</td>
		<td align="left"><img
			src="../../hisglobal/images/btn-ccl.png" onClick="initPage();" style="cursor:pointer;cursor:hand;" title="Cancel the Process" /></td>
			-->
			<br>
			<td align="right">
				<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>		
			</td>
			<td align="left">
			<a href="#" class="button"	onclick="initPage();"><span class="cancel">Cancel</span></a> 
			</td>
	</tr>
</table>

<input type="hidden" name="selectedTab"></form>
<tag:autoIndex></tag:autoIndex> 
</body>

</html>