<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>Statistical Report of Discharge Patient</title>
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

function validate(){
	
	var hisValidator = new HISValidator("statDischargePatRpt");
	if(document.forms[0].strHospitalCode.value == "0"){
			alert("Please Select Hospital");
			document.forms[0].strHospitalCode.focus();
			return false;
		} 
	if(document.forms[0].strDeptCode.value == "0" && document.getElementsByName("strCase")[0].checked){
			alert("Department is a mandatory field");
			document.forms[0].strDeptCode.focus();
			return false;
	} 

	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	
	
	var dateDifference = dateDiff(document.forms[0].strEffectiveFrom.value,document.forms[0].strEffectiveTo.value);
			 	
	 if(parseInt(dateDifference) > 30)
	{
				alert("Date Difference should not be greater than 30 days");
				return false;
	} 
	
	
	
	var retVal = hisValidator.validate(); 
		hisValidator.clearAllValidations();
	if(retVal){
	
		var obj=document.getElementsByName("strIsTimeRequired")[0];
		if(obj.checked)
		{
			var fromDate=document.getElementsByName("strEffectiveFrom")[0].value;
			var toDate=document.getElementsByName("strEffectiveTo")[0].value;
			var currDate=document.getElementsByName("strCurrentDate")[0].value;
			var currTime=document.getElementsByName("strCurrentTime")[0].value;
			var currentTime=document.getElementsByName("strCurrentTime")[0];
			if((fromDate==currDate)&&(toDate==currDate))
			{
				var fromTime=document.getElementsByName("strFromTime")[0];
				var toTime=document.getElementsByName("strToTime")[0];
				var matchString = /^[0-9":"]+$/;
				var checkFromTime=fromTime.value.match(matchString);
				var checkToTime=toTime.value.match(matchString);
				if(!checkFromTime)
				{
						alert("Please Enter Valid From Time");
						fromTime.value="00:00";
						fromTime.focus();
						return false;
				}
				if(!checkToTime)
				{
						alert("Please Enter Valid To Time");
						toTime.value=currTime;
						toTime.focus();
						return false;
				}
				
				if(!(fromTime.value.indexOf(":")==2))
				{
						alert("Please Enter Valid From Time");
						fromTime.value="00:00";
						fromTime.focus();
						return false;
				}
				if(!(toTime.value.indexOf(":")==2))
				{
						alert("Please Enter Valid To Time");
						toTime.value=currTime;
						toTime.focus();
						return false;
				}
				
				var fromTime_hr=parseInt(fromTime.value.split(":")[0]);
				var fromTime_min=parseInt(fromTime.value.split(":")[1]);
				var toTime_hr=parseInt(toTime.value.split(":")[0]);
				var toTime_min=parseInt(toTime.value.split(":")[1]);
				var currTime_hr=parseInt(currentTime.value.split(":")[0]);
				var currTime_min=parseInt(currentTime.value.split(":")[1]);
				if(fromTime_hr>=currTime_hr||fromTime_hr>23)
				{
					if(fromTime_hr==currTime_hr)
					{
						if(fromTime_min>currTime_min || fromTime_min>59)
						{
							alert("From Time should Less than Current Time");
							fromTime.value="00:00";
							fromTime.focus();
							return false;
						}
					}
					else 
					{
						alert("From Time should Less than Current Time");
						fromTime.value="00:00";
						fromTime.focus();
						return false;
					}
				}
				if(toTime_hr>=currTime_hr || toTime_hr>23)
				{
					if(toTime_hr==currTime_hr)
					{
						if(toTime_min>currTime_min || toTime_min>59)
						{
							alert("To Time should Less than Current Time");
							toTime.value=currentTime.value;
							toTime.focus();
							return false;
						}
					}
					else
					{
						alert("To Time should Less than Current Time");
						toTime.value=currentTime.value;
						toTime.focus();
						return false;
					}
				}
				if(fromTime_hr>=toTime_hr)
				{
					if(fromTime_hr==toTime_hr)
					{
						if(fromTime_min>=toTime_min)
						{
							if(fromTime_min==toTime_min)
							{}
							else
							{
								alert("From Time should not be greater than To Time");
								fromTime.focus();
								return false;
							}
						}
					}
					else
					{
						alert("From Time should not be greater than To Time");
						fromTime.focus();
						return false;
					}
				}
			}
			else
			{
						alert("From Date and To Date should be Current Date for entering Time ");
						fromDate.focus();
						return false;	
			}
		}
	}

	
	if(retVal){
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

function getCombo(obj){
	document.getElementsByName("strHospitalCode")[0].value="0";
	for(var i = 0 ; i < obj.length ; i++){
	
		if(obj[i].checked){
			obj = obj[i];
			break;
		}
		
	}

	if(obj.value == 1){
	document.getElementById("wardDivId").style.display = "block";
	document.getElementById("unitDisDivId").style.display = "none";
	document.getElementById("deptDivId").style.display = "";
	document.getElementById("unitDisplayDivId").style.display = "";
	document.getElementById("catDivId").style.display = "none";
	document.forms[0].strUnitNameDisplay.value="0";
	}else if(obj.value == 2){
	document.getElementById("catDivId").style.display = "block";
	document.getElementById("wardDivId").style.display = "none";
	document.getElementById("unitDisDivId").style.display = "none";
	document.getElementById("deptDivId").style.display = "none";
	document.getElementById("unitDisplayDivId").style.display = "none";
	document.forms[0].strUnitNameDisplay.value="0";
	}
	
}

function getUnitComboDisplay(obj)
{
	if(obj.checked){
	document.getElementById("unitDisDivId").style.display = "";
	 
	}
	else{
	document.getElementById("unitDisDivId").style.display = "none";
	 
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
function getDeptCombo(obj)
	{ 
 		var url;
 		url="StatisticalRptDischargePatRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
		ajaxFunction(url,"4");
	}


function getUnitCombo(obj){ 

 		var url ="StatisticalRptDischargePatRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
 		ajaxFunction(url,"1");
		
	}
function getWardCombo(obj){ 

 		var url="StatisticalRptDischargePatRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value;
 			ajaxFunction(url,"2");;
		
		}


function getAjaxResponse(res,mode){
	
	
	
	if(mode=="1"){ 

		var objVal= document.getElementById("unitDivId");
		objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
		objVal= document.getElementById("wardId");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";
	
	}
	if(mode=="2"){  
	
		var objVal= document.getElementById("warDivId");
		objVal.innerHTML = "<select name ='strWardName' class='comboNormal'>" + res + "</select>";
	}
	if(mode=="4")
	{   
		var objVal= document.getElementById("deptDivId2");
		objVal.innerHTML = "<select name ='strDeptCode' class='comboNormal' onchange='getUnitCombo(this);'>" + res + "</select>";
	}
	
	
	}

function cancel()
 {
	 	document.forms[0].hmode.value = "CANCEL";
	 	document.forms[0].target = "_self";
  	    document.forms[0].submit();
 }

</script>
</head>
<body onload="getCombo(document.forms[0].strCase);">
<html:form action="/reports/StatisticalRptDischargePatRptCNT.cnt" method="post">

	<div class="normalMsg" id="normalMsg"></div>

		<tag:tab tabLabel="Statistical Report For Discharge Patient"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="2">Statistical Report For Discharge Patient</td>
		</tr>
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right">
    <html:radio property="strCase" name="statDischargePatRpt" value="1" onclick="getCombo(this);" >Ward</html:radio>
    <html:radio property="strCase" name="statDischargePatRpt" value="2" onclick="getCombo(this);" >Category</html:radio>
    </div>
    </td>
 	 </tr>
	 <tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange="getDeptCombo(this);">
			<bean:write name="statDischargePatRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
	 </tr>
 	 <tr>
 	  <td class="LABEL" >
 	  <div id="unitDisplayDivId">
 	  <html:checkbox value="1" property="strUnitNameDisplay" name="statDischargePatRpt" onclick="getUnitComboDisplay(this);"  >Unit Name Display</html:checkbox>
 	  </div>
 	  </td>
 	  <td class="LABEL" >
 	  <html:checkbox property="strGrandTotalRequired" name="statDischargePatRpt" onclick="getGrandTotal(this);">Grand Total Required</html:checkbox>
 	  </td>
 	 </tr>		
	<tr id="deptDivId">
			<td width="50%" class="LABEL" colspan="1">
			<div align="right"><font color="red">*</font>Department</div>
			</td>
			<td width="50%" class="CONTROL" colspan="1">
			<div align="left"><div id="deptDivId2">
    		<select name="strDeptCode" class="comboNormal" onchange="getUnitCombo(this);" >
        	<option value="0">Select Value</option>
        	 </select>
        	</div></div>
			</td>
			
		</tr>

		<tr id="unitDisDivId" style="display:none" >
			<td width="50%" class="LABEL">
			Unit
			</td>
			<td width="50%" class="CONTROL">
			<div id="unitDivId" >
			<select name="strUnitCode" class="comboNormal" onChange = "getWardCombo(this);"  >
			<option value="0">All</option></select>
			</div>
			</td>
		
		</tr>
       
  </table>
 <div id="wardDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
  
  <tr> 
    <td width="50%" class="LABEL">Ward </td>
    <td width="50%" class="CONTROL">
    <div id="wardId">
    <select name="strWardName" class="comboNormal" >
        <option value="0">All</option>
        </select>
        </div>
        
        </td>
  </tr>
</table>
</div>
  <div id="catDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">  
  <tr> 
    <td width="50%" class="LABEL">Category</td>
    <td width="50%" class="CONTROL"><select name="strCategory"  class="comboNormal" >
        <bean:write name="statDischargePatRpt" property="strCategoryValues" filter="false"/>
       </select></td>
  </tr>
  </table>
  </div>
  <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
   		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${statDischargePatRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${statDischargePatRpt.strCurrentDate}" /></td>
		</tr>
	<tr>
			<td width="50%" class="LABEL">
			Time Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsTimeRequired" name="statDischargePatRpt" onclick="displayTime(this);"></html:checkbox>
			</td>
			
	</tr>
   </table>
		<div id="timeDiv" Style="display:none">
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> From Time</td>
					<td width="50%" class="CONTROL"><html:text name="statDischargePatRpt" property="strFromTime" style="width:50px"
					value="00:00" />HH24:MM</td>
				</tr>
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> To Time</td>
					<td width="50%" class="CONTROL"><html:text name="statDischargePatRpt" property="strToTime" style="width:50px"
					value="${statDischargePatRpt.strCurrentTime}" />HH24:MM</td>
				</tr>
			</table>
		</div>
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
      <tr>
			<td width="50%" class="LABEL">
			<font color="red">*</font>Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat" class="comboSmall" onchange=""><option value="html">HTML</option><option value="pdf">PDF</option></select>
			</td>
			
		</tr> 
   
      <tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="statDischargePatRpt" value="1"></html:checkbox>
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
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center">
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();hideTime();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancel();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strChkRadio"/>
<input type="hidden" name="strCurrentDate" value="${statDischargePatRpt.strCurrentDate}"/>
<input type="hidden" name="strCurrentTime" value="${statDischargePatRpt.strCurrentTime}"/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>