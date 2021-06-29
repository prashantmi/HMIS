<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head><meta charset="utf-8" />
<title>Daily Mid Night Census Report</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">

function validate()
{	
		document.forms[0].hmode.value = "SHOWRPT";	
		
			if(document.forms[0].strHospitalCode.value == "0"){
			alert("Hospital name is a mandatory field");
			return false;
		}  
		if(document.forms[0].strDeptCode.value == "0"){
			alert("Department is a mandatory field");
			return false;
		}  
	var hisValidator = new HISValidator("dailyMasterCensusRpt");
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	
	
	
		
		
		var retVal = hisValidator.validate(); 
	hisValidator.clearAllValidations();
	if(retVal)
	{
		document.forms[0].hmode.value = "SHOWRPT";
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
		{
			document.forms[0].target = "_self";
		}
		else
		{
			//document.forms[0].target = "_blank";
			document.forms[0].target = "_self";
		}
		document.forms[0].submit();
		}
		else
		{
			return false;
		}
		
		
		
		
		
		
		
		
		
}

function getCombo(obj){

	for(var i = 0 ; i < obj.length ; i++){
	
		if(obj[i].checked){
			obj = obj[i];
			break;
		}
		
	}

	if(obj.value == 1){
	document.getElementById("datWiseDivId").style.display = "none";
	
	//document.getElementById("wardDivId").style.display = "block";
	//document.getElementById("unitDisDivId").style.display = "none";
	//document.getElementById("deptDivId").style.display = "";
	//document.getElementById("unitDisplayDivId").style.display = "";
	//document.getElementById("catDivId").style.display = "none";
	//document.forms[0].strUnitNameDisplay.value="0";
	}else if(obj.value == 2){
	//document.getElementById("catDivId").style.display = "block";
	//document.getElementById("wardDivId").style.display = "none";
	//document.getElementById("unitDisDivId").style.display = "none";
	//document.getElementById("deptDivId").style.display = "none";
	//document.getElementById("unitDisplayDivId").style.display = "none";
	//document.forms[0].strUnitNameDisplay.value="0";
	document.getElementById("datWiseDivId").style.display = "";
	}
	
}



function getGrandTotal(obj)
{
	if(obj.checked){
	
	document.forms[0].strGrandTotalRequired.value="1";
	}
	else{

	document.forms[0].strGrandTotalRequired.value="0";
	}
	
	
}
function getUnitCombo(obj)
{
 		if(obj.value=='0')
 		{
 			var objVal= document.getElementById("unitDivId");
			objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'><option value='0'>All</option></select>";
			objVal= document.getElementById("wardDivId");
			objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'><option value='0'>All</option></select>";
 		}
 		else
 		{
	 		var url ="DailyMasterCensusRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
			ajaxFunction(url,"1");
 		}
 		
}
function getWardCombo(obj)
{ 
	 		var url="DailyMasterCensusRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value+"&deptCode="+document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].value;
			ajaxFunction(url,"2");
	 		 	
}

function getDeptCombo(obj)
{ 
 		var url;
 		url="DailyMasterCensusRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
		ajaxFunction(url,"3");
}
function getAjaxResponse(res,mode)
{
	if(mode=="1")
	{   
		var objVal= document.getElementById("unitDivId");
			objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
			objVal= document.getElementById("wardDivId");
		    objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";
	}
	if(mode=="2")
	{   
		var objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode' class='comboNormal'>" + res + "</select>";
	}
	if(mode=="3")
	{   
		var objVal= document.getElementById("deptDivId");
		objVal.innerHTML = "<select name ='strDeptCode' class='comboNormal' onchange='getUnitCombo(this);'>" + res + "</select>";
	}
}	
function cancel()
{
	 	document.forms[0].hmode.value = "CANCEL";
	 	document.forms[0].target = "_self";
  	    document.forms[0].submit();
}
function clearPage()
{
		document.forms[0].hmode.value = "CLEAR";
		document.forms[0].target = "_self";
  		document.forms[0].submit();
}
</script>
</head>
<body >
<html:form action="/reports/DailyMasterCensusRptCNT.cnt" method="post">
<div class="normalMsg" id="normalMsg"></div>
	<tag:tab tabLabel="Daily Mid Night Census" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center">   
		<tr class="HEADER">
			<td colspan="2">Daily Mid Night Census.</td>
		</tr>
			<tr >
    			<td class="LABEL" colspan="2">
    			<div align="right">
    				<html:radio property="strCase" name="dailyMasterCensusRpt" value="1" onclick="getCombo(this);" >Consolidated</html:radio>
    				<html:radio property="strCase" name="dailyMasterCensusRpt" value="2" onclick="getCombo(this);" >Date Wise</html:radio>
    			</div>
    			</td>
 		 </tr>
		
		<tr>
		<td width="50%" class="LABEL" >
		</td>
		  <td width="50%" class="CONTROL" >
 	      <html:checkbox property="strGrandTotalRequired" name="dailyMasterCensusRpt" onclick="getGrandTotal(this);">Whether Grand Total Required</html:checkbox>
 	      </td>
		</tr>
		
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange="getDeptCombo(this)">
			<bean:write name="dailyMasterCensusRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>



<tr>
		
		
		
		<tr> 
    				<td width="50%" class="LABEL"><font color="red">*</font>Department</td>
    		<td width="50%" class="CONTROL">
    		<div id="deptDivId">
    		<select name="strDeptCode"  onchange="getUnitCombo(this);" class="comboNormal">
    			
        	<bean:write name="dailyMasterCensusRpt" property="strDeptValues" filter="false"/> 
        	<option value="0">Select Value</option></select>
        	
        	</div> 
        	</td>
  		</tr>
  		<tr>
			<td width="50%" class="LABEL">Unit</td>
			<td width="50%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
			<option value="0">All</option></select>
			</div>
			</td>			
		</tr>
  		<tr>
			 <td width="50%" class="LABEL">Ward</td>
			 <td width="50%" class ="CONTROL">
			 <div id ="wardDivId">
			 <select name="strWardCode" class="comboNormal" >
    		 <option value="0">All</option></select> 
    		 </div>
    	  	 </td>
		</tr>   
		</table>
		<div id="datWiseDivId" style="display:none;">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
   		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${dailyMasterCensusRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${dailyMasterCensusRpt.strCurrentDate}" /></td>
		</tr>
		</table>
		</div>
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
 		<tr>
			<td width="50%" class="LABEL">Report Format</td>
			<td width="50%" class="CONTROL">
			<select name="strReportFormat" class="comboSmall"  onchange="">
			<option value="html">HTML</option>
			<option value="pdf">PDF</option></select>
			</td>			
		</tr> 
		<tr>
			<td width="50%" class="LABEL">Footer Required</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="dailyMasterCensusRpt" value="1"></html:checkbox>
			</td>			
		</tr>
		<tr>
			<td width="50%" class="LABEL">User Remarks</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>			
		</tr>
		<tr class="FOOTER">
			 <td colspan="2"></td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();clearPage();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancel();" >
			</td>			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${dailyMasterCensusRpt.strCurrentDate}"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>