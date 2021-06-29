<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset=utf-8>
<title>Consolidated Fee Refund Report</title>
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
	var hisValidator = new HISValidator("totalRefundRpt");
	if(document.forms[0].strHospitalCode.value == "0")
	{	
		Multiple();
	}
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Date : "+document.forms[0].strCurrentDate.value);
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
			document.forms[0].target = "_blank";
		}
			document.forms[0].submit();
		}
		else
		{
			return false;
		}
}

function getCombo(obj)
{	
	for(var i = 0 ; i < obj.length ; i++)
	{	
		if(obj[i].checked)
		{
			obj = obj[i];
			break;
		}		
	}	
	if(obj.value == 1)
	{	
		//document.getElementById("counterDivId").style.display = "block";
		//document.getElementById("feeclerkDivId").style.display = "none";
		document.forms[0].strHospitalCode.value = "0";
		document.forms[0].strHospSerName.value = "0";
		document.getElementById("treatcatDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";	
	}
	else if(obj.value == 2)
	{	
		document.forms[0].strHospitalCode.value = "0";
		document.forms[0].strHospSerName.value = "0";
		//document.getElementById("counterDivId").style.display = "none";
		//document.getElementById("feeclerkDivId").style.display = "block";
		document.getElementById("treatcatDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";	
	}
	else if(obj.value == 3)
	{	
		document.forms[0].strHospitalCode.value = "0";
		document.forms[0].strHospSerName.value = "0";
		//document.getElementById("counterDivId").style.display = "none";
		//document.getElementById("feeclerkDivId").style.display = "none";
		document.getElementById("treatcatDivId").style.display = "block";
		document.getElementById("dateDivId").style.display = "block";	
	}
	else if(obj.value == 4)
	{	
		document.forms[0].strHospitalCode.value = "0";
		document.forms[0].strHospSerName.value = "0";
		//document.getElementById("counterDivId").style.display = "none";
		//document.getElementById("feeclerkDivId").style.display = "none";
		document.getElementById("treatcatDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";	
	}	
}
function Multiple()
{
	 var sdValues = [];
	 for(var i = 1; i < document.forms[0].strHospitalCode.options.length; i++)
	 {
	 	// obj.options[i].selected = true;
	  	sdValues.push(document.forms[0].strHospitalCode.options[i].value);
	 }	
	  	document.forms[0].strHospitalCodes.value=sdValues;
  		// alert(sdValues);
}
function getCombo1(obj)
{    
	 var sdValues = [];
	 if(obj.options[0].selected==true)
	 {	
		  for(var i = 1; i < obj.options.length; i++)
		  {
		 	// obj.options[i].selected = true;
		  	sdValues.push(obj.options[i].value);
		  }	 
	 }
	 else
	 {
	   for(var i = 1; i < obj.options.length; i++)
	   {
	      if(obj.options[i].selected == true)
	      {
	      	sdValues.push(obj.options[i].value);	      
	      }
	   }
	 }
	 document.forms[0].strHospitalCodes.value=sdValues;
	 //  alert(sdValues);

}
function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}
function clearForm()
{
	document.forms[0].hmode.value = "CLEAR";
	document.forms[0].submit();
}
</script>
</head>
<body onload="getCombo(document.forms[0].strCase);">
<html:form action="/reports/ConsolidatedRefundRptCNT.cnt" method="post">
	
<div class="errMsg"><bean:write name="totalRefundRpt" property="strErrMsg"/></div>

	<tag:tab tabLabel="Consolidated Refund Report"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>
                  
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="2">Consolidated Refund Report</td>
		</tr>
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right">
    <!--<html:radio property="strCase" name="totalRefundRpt" value="1" onclick="getCombo(this);" >Counter</html:radio>
    <html:radio property="strCase" name="totalRefundRpt" value="2" onclick="getCombo(this);" >Cashier</html:radio>
   	--><html:radio property="strCase" name="totalRefundRpt" value="3" onclick="getCombo(this);" >Patient Category</html:radio>
    <html:radio property="strCase" name="totalRefundRpt" value="4" onclick="getCombo(this);" >Date</html:radio>
  
    </div>
    </td>
 	 </tr>
 	 <tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" class="CONTROL">
			<div id='selectedResult'>
			
		<select name="strHospitalCode"   multiple="multiple"  size="5" onchange="getCombo1(this);" >
			<bean:write name="totalRefundRpt" property="strHospNameValues" filter="false"/>
			</select>
			</div>
			</td>			
		</tr>
 	 <tr> 
    <td width="50%" class="LABEL">Hospital Service</td>
    <td width="50%" class="CONTROL"><select name="strHospSerName" class="comboNormal" >
        <bean:write name="totalRefundRpt" property="strHospSerValues" filter="false"/></select> </td>
  </tr>
 	 </table>
 	 
 	  <!--<div id="counterDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
 	  <tr> 
    <td width="50%" class="LABEL">Counter Name</td>
    <td width="50%" class="CONTROL"><select name="strCounterName" class="comboNormal" >
        <bean:write name="totalRefundRpt" property="strCounterValues" filter="false"/></select> </td>
  </tr>
  </table>
  </div>
  
  <div id="feeclerkDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
 	  <tr> 
    <td width="50%" class="LABEL">Fee Clerk Name</td>
    <td width="50%" class="CONTROL"><select name="strFeeClerkName" class="comboNormal" >
        <bean:write name="totalRefundRpt" property="strFeeClerkValues" filter="false"/></select> </td>
  </tr>
  </table>
  </div>
  
  --><div id="treatcatDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
 	  <tr> 
    <td width="50%" class="LABEL">Patient Category</td>
    <td width="50%" class="CONTROL"><select name="strTreatCatName" class="comboNormal" >
        <bean:write name="totalRefundRpt" property="strTreatCatValues" filter="false"/></select> </td>
  </tr>
  </table>
  </div>
  
 	 <div id="dateDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
  
  <tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${totalRefundRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${totalRefundRpt.strCurrentDate}" /></td>
		</tr>
  </table>
  </div>
 	 <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat"  onchange=""><option value="html">Html</option><option value="pdf">Pdf</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="totalRefundRpt" value="1"></html:checkbox>
			</td>
			
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			User Remarks
			</td>
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
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();clearForm();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelPage();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${totalRefundRpt.strCurrentDate}"/>
<input type="hidden" name="strHospitalCodes" value="${consolidatedExpDtlRpt.strHospitalCodes}" />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>