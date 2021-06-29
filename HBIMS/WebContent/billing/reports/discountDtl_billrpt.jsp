<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset=utf-8>
<title>Discount Detail Report</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
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



function validate()
{
	var hisValidator = new HISValidator("discountDtlRpt");

	hisValidator.addValidation("strHospitalCode", "dontselect=0","Hospital Name is a mandatory field");
	//hisValidator.addValidation("strHospService", "dontselect=0","Hospital Service is a mandatory field");
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	if(retVal)
	{
	
		document.forms[0].hmode.value = "SHOWRPT";
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html"){
			
			document.forms[0].target = "_self";
		}else{
			document.forms[0].target = "_blank";
		}

		document.forms[0].submit();
		}else{
		return false;
		}
}
function getConsultantCombo(obj){ 

 		var url="DiscountDetailsRptCNT.cnt?hmode=CONSULTANTCMB&hospCode="+obj.value;
 		ajaxFunction(url,"1");
		
	}
function getHospitalServiceCombo(obj)
{ 
 		var url;
 		url="DiscountDetailsRptCNT.cnt?hmode=HOSPSERVICECMB&hospCode="+obj.value;
		ajaxFunction(url,"2");
}


function getAjaxResponse(res,mode)
{

if(mode=="1")
	{   
		var objVal= document.getElementById("consultDivId2");
		objVal.innerHTML = "<select name ='strConsult' class='comboBig'>" + res + "</select>";
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
		//document.forms[0].strHospitalCode.value = "0"
		document.getElementById("consultDivId").style.display = "block";
		//document.getElementById("consultDivId2").innerHTML= "<select name ='strConsult' class='comboNormal'><option value='0'>All</option></select>";
		document.getElementById("reasonDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";	
	}
	else if(obj.value == 2)
	{	
		document.forms[0].strHospitalCode.value = "0"
		document.getElementById("consultDivId").style.display = "none";
		document.getElementById("reasonDivId").style.display = "block";
		document.getElementById("dateDivId").style.display = "block";
	}
	else if(obj.value == 3)
	{	
		//document.forms[0].strHospitalCode.value = "0"
		document.getElementById("consultDivId").style.display = "none";
		document.getElementById("reasonDivId").style.display = "none";
		document.getElementById("dateDivId").style.display = "block";	
	}
}
function cancelPage(){
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();

}
</script>
</head>
<body onload="getCombo(document.forms[0].strCase);getConsultantCombo(document.forms[0].strHospitalCode);" >
<html:form action="/reports/DiscountDetailsRptCNT.cnt" method="post">
	
<div class="errMsg" id="errMsg"><bean:write name="discountDtlRpt" property="strErrMsg"/></div>
<div class="normalMsg" id="normalMsg"></div>

	<tag:tab tabLabel="Discount Detail Report" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >   
	<tr class="HEADER">
			<td colspan="2">Discount Detail Report</td>
		</tr>
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right">
    <html:radio property="strCase" name="discountDtlRpt" value="1" onclick="getCombo(this);" >Consultant</html:radio>
    <!--<html:radio property="strCase" name="discountDtlRpt" value="2" onclick="getCombo(this);" >Reason</html:radio>-->
    <html:radio property="strCase" name="discountDtlRpt" value="3" onclick="getCombo(this);" >Date</html:radio> 
    </div>
    </td>
    </tr>
    <tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange ="getConsultantCombo(this);" >
			<bean:write name="discountDtlRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>
 	 	
    
    <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Hospital Service</td>
    <td width="50%" class="CONTROL"><select name="strHospService" class="comboNormal" >
        <bean:write name="discountDtlRpt" property="strHospSerValues" filter="false"/></select> </td>        
  </tr>
    </table>
    
    	<div id="consultDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >
		<tr> 
    		<td  width="50%" class="LABEL">Consultant Name</td>
       		<td  width="50%" class="CONTROL">
    		<div id="consultDivId2" >
    		<select name="strConsult"  class="comboBig" >
          	<option value="0">Select Value</option>
          	<bean:write name="discountDtlRpt" property="strConsultValues" filter="false"/>
          	</select></div></td>
  		</tr>
		</table>
		</div>
		
		<div id="reasonDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >
		<tr> 
    		<td  width="50%" class="LABEL">Reason</td>
    		<td  width="50%" class="CONTROL">
    		<select name="strReason"  class="comboNormal">
        	<bean:write name="discountDtlRpt" property="strReasonValues" filter="false"/></select></td>
  		</tr>
		</table>
		</div>
		 <div id="dateDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
  
  <tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${discountDtlRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${discountDtlRpt.strCurrentDate}" /></td>
		</tr>
  </table>
  </div>
  <table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="1px" >
  	<tr>
  		<td width="50%" class="LABEL" colspan="1">Report Format</td>
		<td width="50%" class="CONTROL" colspan="1">
			<select name="strReportFormat"  onchange="">
				<option value="html">HTML</option>
				<option value="pdf">PDF</option>
				<option value="excel">Excel</option></select>
		</td>
	</tr> 
	<tr>
		<td width="50%" class="LABEL" colspan="1">Footer Required</td>
		<td width="50%" class="CONTROL" colspan="1">
			<html:checkbox property="strIsFooter" name="discountDtlRpt" value="1"></html:checkbox>
		</td>
	</tr>
	<tr>
			<td width="50%" class="LABEL" colspan="1">User Remarks</td>
			<td width="50%" class="CONTROL" colspan="1">
				<input class="txtFldMax" type="text" name="strUserRemarks" >
			</td>			
	</tr>
  </table>
  <table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="1px" >
	<tr class="FOOTER">
			 <td colspan="1" width='90%'><div align='left'><font size="2" color="red">#</font>In This Report Data Will Be Shown After providing Discount and Generating The Bill Using Cash Collection Online</div></td>
			 <td colspan="1" width='10%'><font size="2" color="red">*</font> Mandatory Fields  </td>
	</tr>
  </table>
	<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<!-- -<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" > -->
			
			<br><a href="#" class="button" id="" onClick="return validate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onclick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${discountDtlRpt.strCurrentDate}"/>
</html:form>
<tag:autoIndex></tag:autoIndex> 
</body>
</html>