<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head><meta charset="utf-8" />
<title>Patient Transfer Detail Report</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
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
 		var hisValidator = new HISValidator("patTransferDtlRpt");
	    //hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select Effective To Date Less Than or Equal To Effective From Date");
	    //hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	    hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	    hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	    hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	    hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	    var retVal = hisValidator.validate(); 
	    var dateDifference = dateDiff(document.forms[0].strEffectiveFrom.value,document.forms[0].strEffectiveTo.value);
			 	
	    if(parseInt(dateDifference) > 30)
	    {
				alert("Date Difference should not be greater than 30 days");
				return false;
     	}
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
			document.forms[0].target = "_self";
		}
		document.forms[0].submit();
		}
}


function displayTime(obj)
 {
 	if(obj.checked)
 	{
 		obj.value="1";
 		document.getElementById("timeDiv").style.display = "block";
 	}
 	else
 	{
 		obj.value="0";
 		document.getElementById("timeDiv").style.display = "none";
 	}
 }

function hideTime()
 {
 	document.getElementById("timeDiv").style.display = "none";
 }
 
function cancel()
{
	 	document.forms[0].hmode.value = "CANCEL";
	 	document.forms[0].target = "_self";
  	    document.forms[0].submit();
}
function clearForm()
{
	 	document.forms[0].hmode.value = "CLEAR";
	 	document.forms[0].target = "_self";
  	    document.forms[0].submit();
}
function markCheck()
{
document.getElementsByName("strCase")[0].checked="true";
}

function showData(obj)
{	
	if(obj.value=='1' && obj.checked)//Transfer In
	{
		document.getElementById("ward3").innerHTML = "<font style='font-family: Verdana, Arial, Helvetica, sans-serif ; font-size:11px;	color:#333333; text-align:right; font-weight: bold;'>Ward</font>";
		document.getElementById("ward4").innerHTML = "<font style='font-family: Verdana, Arial, Helvetica, sans-serif ; font-size:11px;	color:#333333; text-align:right; font-weight: bold;'>Transfer From Ward</font>";
	}
	if(obj.value=='2' && obj.checked)//Transfer Out
	{
		document.getElementById("ward3").innerHTML = "<font style='font-family: Verdana, Arial, Helvetica, sans-serif ; font-size:11px;	color:#333333; text-align:right; font-weight: bold;'>Ward</font>";
		document.getElementById("ward4").innerHTML = "<font style='font-family: Verdana, Arial, Helvetica, sans-serif ; font-size:11px;	color:#333333; text-align:right; font-weight: bold;'>Transfer To Ward</font>";
	}
}
</script> 
</head>
<body onload="markCheck();">
<html:form action="/reports/PatientTransferDtlRptCNT.cnt" method="post">
<div class="normalMsg" id="normalMsg"></div>
<tag:tab tabLabel="Patient Transfer Details" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="4">Patient Transfer Details</td>
			<td colspan="2">
		    <div align="right">
		    
		    <html:radio property="strCase" name="patTransferDtlRpt" value="1" onclick="changeRadio(this);">Ward</html:radio>
		    </div>
		    </td>
	</tr>
	<tr>
    <td class="LABEL" width="60%" colspan="4"></td>
     <td class="LABEL" width="20%" >
     <div align="left">
    <html:radio property="strTransferType" name="patTransferDtlRpt" value="1" onclick="showData(this);">
    Transfer In</html:radio>
    </div>
    </td>
    <td class="LABEL" width="20%">
     <div align="center">
     <html:radio property="strTransferType" name="patTransferDtlRpt" value="2" onclick="showData(this);">
     Transfer Out</html:radio>
    </div>
    </td>
    </tr>
    
</table>	    
<div id="wardDtId" style="">    
<table class="TABLEWIDTH" align="center">		
	 <tr> 	
			 <td class="LABEL" width="50%"><div id="ward3" align="right">Ward</div></td>
			 <td class ="CONTROL" width="50%">
				 <div id ="wardInDivId">
				 	<select name="strWardCodeAll" class="comboNormal" onchange="">
				 		<bean:write name="patTransferDtlRpt"  filter="false" property="strWardAllValues"/>
	          		</select>
	          	 </div>
      		</td>
	</tr>
	<tr> 
			 <td  class="LABEL" width="50%"><div id="ward4" align="right">Transfer From Ward</div></td>
			 <td  class ="CONTROL" width="50%">
				 <div id ="transferToWardDivId">
				 	<select name="strTransferToWardCodeAll" class="comboNormal">
				 		<bean:write name="patTransferDtlRpt"  filter="false" property="strWardAllValues"/>
	          		</select> 
	          	</div>
      		</td>
	</tr>
</table>	    
</div>  
<div id="dateDivId" style="">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">  
  		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL">
				<dateTag:date name="strEffectiveFrom" value="${patTransferDtlRpt.strCurrentDate}" />
			</td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> To Date</td>
			<td width="50%" class="CONTROL">
				<dateTag:date name="strEffectiveTo" value="${patTransferDtlRpt.strCurrentDate}" />
			</td>
		</tr>		
  </table>
</div>
 

 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
      <tr>
			<td width="50%" class="LABEL">Report Format</td>
			<td width="50%" class="CONTROL">
				<select name="strReportFormat" class="comboSmall" onchange="">
					<option value="html">HTML</option>
					<option value="pdf">PDF</option>
					<option value="excel">EXCEL</option>
					
				</select>
			</td>			
		</tr>    
		<tr>
			<td width="50%" class="LABEL">Footer Required</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="patTransferDtlRpt" value="1"></html:checkbox>
			</td>			
		</tr>
		<tr>
			<td width="50%" class="LABEL">User Remarks</td>
			<td width="50%" class="CONTROL">
			<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>			
		</tr>
		<tr class="FOOTER">
			 <td colspan="2"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center">
			<!--<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();hideTime();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			-->
			<br><a href="#" class="button" id="" onClick="return validate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset();hideTime();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
			</td>			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${patTransferDtlRpt.strCurrentDate}"/>
<input type="hidden" name="strCurrentTime" value="${patTransferDtlRpt.strCurrentTime}"/>
<input type="hidden" name="strHospitalCode" value="${patTransferDtlRpt.strHospitalCode}"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>