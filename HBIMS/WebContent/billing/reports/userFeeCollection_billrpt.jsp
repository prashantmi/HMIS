<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset=utf-8>
<title>Refund Detail Report</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript">
function multiSelHosp()
{
	
	var tempObj=document.getElementsByName('strHospitalCode')[0];
	var countSel=0;
	
	for(var i=0;i<tempObj.options.length;i++)
	{
			if(tempObj.options[i].selected)
			{
				countSel+=1;
			}		
	}
	
	return 	countSel;
	
}

function validate()
{
	var tempObj=document.getElementsByName('strHospitalCode')[0];
	var countSel=0;
	var allHospCodes='testCode';
	countSel=multiSelHosp();
	var allSelFlag='0'; // 0 all not sel,1 all sel
	
	//alert("sel val count is::"+countSel+"::"+tempObj.value);	
	if(countSel > 1)//multiple hospitals are selected and all is also selected..
	{
		for(var i=0;i<tempObj.options.length;i++)
		{
			if(tempObj.options[0].selected)//All selected
			{
				
				tempObj.options[i].selected=false;	
				if(allHospCodes=='testCode')
				{
					allHospCodes=tempObj.options[i].value;
				}
				else
				{
					allHospCodes=allHospCodes+','+tempObj.options[i].value;
				}
				
				allSelFlag='1';
			}
			else
			{
				if(tempObj.options[i].selected)
				{
					if(allHospCodes=='testCode')
					{
						allHospCodes=tempObj.options[i].value;
					}
					else
					{
						allHospCodes=allHospCodes+','+tempObj.options[i].value;
					}
				}
			}
		}
		
		if(allSelFlag=='1')
		{
			tempObj.options[0].selected=true;
		
			alert("Cannot Proceed.\nEither All or multiple hospitals can be selected.Both the options cannot be exercised together.");
			
			return false;	
		}
		else
		{
			
		}
		
		
			
	}
	
	if(countSel == 1)//either All or single hospital is selected..
	{
		for(var i=0;i<tempObj.options.length;i++)
		{
			
			if(tempObj.options[0].selected)//All selected
			{
				if(allHospCodes=='testCode')
				{
					
					allHospCodes=tempObj.options[i].value;
				}
				else
				{
					
					allHospCodes=allHospCodes+','+tempObj.options[i].value;
				}
			}
		}
				
	}
	
	
		if(allHospCodes=='testCode')
		{
			document.getElementsByName('strAllHospCode')[0].value='testCode';
			
		}
		else
		{
			document.getElementsByName('strAllHospCode')[0].value=allHospCodes;
		}
	
	//alert("strAllHospCode::"+document.getElementsByName('strAllHospCode')[0].value);
	var hisValidator = new HISValidator("userFeeCollRpt");
	/*if(document.forms[0].strHospitalCode.value == "0")
	{
			alert("Hospital Name is a mandatory field");
			return false;
	} */
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
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
		//document.forms[0].strHospitalCode.value = "0";
		document.forms[0].strHospSerName.value = "0";
		document.forms[0].strCounterName.value = "0";
		document.getElementById("counterDivId").style.display = "block";
		document.getElementById("conterDivId2").innerHTML= "<select name ='strCounterName' class='comboNormal'><option value='0'>All</option></select>";
		document.getElementById("feeclerkDivId").style.display = "none";
		document.getElementById("deptDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";	
	}
	else if(obj.value == 2)
	{
		document.forms[0].strHospitalCode.value = "0";
		document.forms[0].strHospSerName.value = "0";
		document.forms[0].strFeeClerkName.value = "0";
		document.getElementById("counterDivId").style.display = "none";
		document.getElementById("feeclerkDivId").style.display = "block";
		document.getElementById("feeclerkDivId2").innerHTML= "<select name ='strFeeClerkName' class='comboNormal'><option value='0'>All</option></select>";
		document.getElementById("deptDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";
	}
	else if(obj.value == 3)
	{
		document.forms[0].strHospitalCode.value = "0";
		document.forms[0].strHospSerName.value = "0";
		document.forms[0].strDeptName.value = "0";
		document.getElementById("counterDivId").style.display = "none";
		document.getElementById("feeclerkDivId").style.display = "none";
		document.getElementById("deptDivId").style.display = "block";
		document.getElementById("deptDivId2").innerHTML= "<select name ='strDeptName' class='comboNormal'><option value='0'>All</optipn></select>";
		document.getElementById("dateDivId").style.display = "block";		
	}
	else if(obj.value == 4)
	{
		document.forms[0].strHospitalCode.value = "0";
		document.forms[0].strHospSerName.value = "0";
		document.getElementById("counterDivId").style.display = "none";
		document.getElementById("feeclerkDivId").style.display = "none";
		document.getElementById("deptDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";
	}
}

function getCombo1(obj1)
{
	var obj=document.getElementsByName("strCase");
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
	//Counter	
		if(document.forms[0].strHospitalCode.value == "0")
		{

			var objVal= document.getElementById("conterDivId2");
			objVal.innerHTML = "<select name ='strCounterName' class='comboNormal'><option value='0'>All</option></select>";
		}
		else
		{
			var url;
	 		url="UserFeeCollectionRptCNT.cnt?hmode=CNTCMB&hospCode="+obj1.value;
			ajaxFunction(url,"1");
		}			
	}
	else if(obj.value == 2)
	{//Cashier
		if(document.forms[0].strHospitalCode.value == "0")
		{
			var objVal= document.getElementById("feeclerkDivId2");
			objVal.innerHTML = "<select name ='strFeeClerkName' class='comboNormal'><option value='0'>All</option></select>";
		}
		else
		{
			var url;
	 		url="UserFeeCollectionRptCNT.cnt?hmode=CASHCMB&hospCode="+obj1.value;
			ajaxFunction(url,"2");
		}			
	}
	else if(obj.value == 3)
	{//Department
		if(document.forms[0].strHospitalCode.value == "0")
		{
			var objVal= document.getElementById("deptDivId2");
			objVal.innerHTML = "<select name ='strDeptName' class='comboNormal'><option value='0'>All</option></select>";
		}
		else
		{
			var url;
	 		url="UserFeeCollectionRptCNT.cnt?hmode=DEEPTCMB&hospCode="+obj1.value;
			ajaxFunction(url,"3");
		}	
	}
	else if(obj.value == 4)
	{//Date	
		document.getElementById("counterDivId").style.display = "none";
		document.getElementById("feeclerkDivId").style.display = "none";
		document.getElementById("deptDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";
		document.getElementById("tariffCheckBoxDivId").style.display = "none";
		document.getElementById("tariffDivId").style.display = "none";	
	}	
}

function getAjaxResponse(res,mode)
{
	if(mode=="1")
	{   
		var objVal= document.getElementById("conterDivId2");
		objVal.innerHTML = "<select name ='strCounterName' class='comboNormal'>" + res + "</select>";
	}
	if(mode=="2")
	{   
		var objVal= document.getElementById("feeclerkDivId2");
		objVal.innerHTML = "<select name ='strFeeClerkName' class='comboNormal'>" + res + "</select>";
	}
	if(mode=="3")
	{   
		var objVal= document.getElementById("deptDivId2");
		objVal.innerHTML = "<select name ='strDeptName' class='comboNormal'>" + res + "</select>";
	}
}
function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].submit();
}

</script>
</head>
<body onload="getCombo(document.forms[0].strCase);getCombo1(document.forms[0].strHospitalCode);">
<html:form action="/reports/UserFeeCollectionRptCNT.cnt" method="post" >
			
		<div class="errMsg" id="errMsg"><bean:write name="userFeeCollRpt" property="strErrMsg"/></div>
		<div class="normalMsg" id="normalMsg"></div>

	<tag:tab tabLabel="Refund Detail Report"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="2">Refund Detail Report</td>
		</tr>
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right">
    <html:radio property="strCase" name="userFeeCollRpt" value="1" onclick="getCombo(this);" >Counter</html:radio>
    <html:radio property="strCase" name="userFeeCollRpt" value="2" onclick="getCombo(this);" >Cashier</html:radio>
    <html:radio property="strCase" name="userFeeCollRpt" value="3" onclick="getCombo(this);" >Department</html:radio>
    <html:radio property="strCase" name="userFeeCollRpt" value="4" onclick="getCombo(this);" >Date</html:radio>
    </div>
    </td>
 	 </tr>
 	 <tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange="getCombo1(this);" >
			<bean:write name="userFeeCollRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>
 	  <tr> 
    <td width="50%" class="LABEL">Hospital Service</td>
    <td width="50%" class="CONTROL"><select name="strHospSerName" class="comboNormal" onchange="getGroupCombo(this);" >
        <bean:write name="userFeeCollRpt" property="strHospSerValues" filter="false"/></select> </td>
  </tr>
 	 
 	 </table>
 	 <div id="counterDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
 	  <tr> 
    <td width="50%" class="LABEL">Counter Name</td>
    <td width="50%" class="CONTROL">
        <div id="conterDivId2">
    <select name="strCounterName" class="comboNormal" >
        <bean:write name="userFeeCollRpt" property="strCounterValues" filter="false"/>
        <option value="0">All</option></select></div> </td>
  </tr>
  </table>
  </div>
  
  <div id="feeclerkDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
 	  <tr> 
    <td width="50%" class="LABEL">Fee Clerk Name</td>
    <td width="50%" class="CONTROL">
     <div id="feeclerkDivId2">
    <select name="strFeeClerkName" class="comboNormal" >
        <bean:write name="userFeeCollRpt" property="strFeeClerkValues" filter="false"/>
        <option value="0">All</option></select></div> </td>
  </tr>
  </table>
  </div>
     
  <div id="deptDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
 	  <tr> 
    <td width="50%" class="LABEL">Department </td>
    <td width="50%" class="CONTROL">
      <div id="deptDivId2">
    <select name="strDeptName" class="comboNormal" >
        <bean:write name="userFeeCollRpt" property="strDeptValues" filter="false"/>
        <option value="0">All</option></select></div> </td>
  </tr>
  </table>
  </div>
  
  
 <div id="dateDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
  
  <tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${userFeeCollRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${userFeeCollRpt.strCurrentDate}" /></td>
		</tr>
  </table>
  </div>
		<table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="1px">   
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat"  onchange="">
		<option value="html">Html</option>
		<option value="pdf">Pdf</option>
		<option value="excel">Excel</option></select>
			</td>
			
		</tr> 
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="userFeeCollRpt" value="1"></html:checkbox>
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
			<td colspan="1"><div align="left"><font size="2" color="red">#</font>Default Hospital Selected-Looged In Hospital  </div></td>
			<td colspan="1"><font size="2" color="red">*</font> Mandatory Fields  </td>
		</tr>
	</table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<!--<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			-->
			<br><a href="#" class="button" id="" onClick="return validate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${userFeeCollRpt.strCurrentDate}"/>
<input type="hidden" name="strAllHospCode" value="${paymentDtlRpt.strAllHospCode}"/>
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>