<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head><meta charset="utf-8" />
<title>Admitted Patient Listing</title>
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

 function visibleRoomNo(obj)
{
	if(obj.checked)
	{
		document.getElementById("roomNoDivId").style.display = "block";
	}
	else
	{
		document.getElementById("roomNoDivId").style.display = "none";
	}	
}
function validate()
{
	var hisValidator = new HISValidator("admPatRpt");
	hisValidator.addValidation("strHospitalCode", "dontselect=0","Please Select Hospital Name");
	//hisValidator.addValidation("strDeptCode", "dontselect=0","Please Select Department");
	hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	//hisValidator.addValidation("strFromTime","dtltet="+document.forms[0].strFromTime.value,"Please Select To Time Less Than or Equal To Current Date");
	//hisValidator.addValidation("strToTime","dtgtet="+document.forms[0].strToTime.value,"Please Select From Date Greater Than Or Equal To From Date");
	
	
	
	var retVal = hisValidator.validate(); 
	
	var dateDifference = dateDiff(document.forms[0].strEffectiveFrom.value,document.forms[0].strEffectiveTo.value);
	
	
	
			 	
	if(parseInt(dateDifference) > 30)
	{
				alert("Date Difference should not be greater than 30 days");
				return false;
	}
	
	hisValidator.clearAllValidations();
	if(retVal)
	{
		
		
		
		var obj=document.getElementsByName("strIsTimeRequired")[0];
		if(obj.checked)
		{
			var fromDate=document.getElementsByName("strEffectiveFrom")[0].value;
			var toDate=document.getElementsByName("strEffectiveTo")[0].value;
			var currDate=document.getElementsByName("strCurrentDate")[0].value;
			var currTime=document.getElementsByName("strCurrentTime")[0].value;
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
						fromTime.value=currTime;
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
						fromTime.value=currTime;
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
				
				var fromTime_hr=fromTime.value.split(":")[0];
				var fromTime_min=fromTime.value.split(":")[1];
				var toTime_hr=toTime.value.split(":")[0];
				var toTime_min=toTime.value.split(":")[1];
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
		else
		{
			return false;
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
			objVal= document.getElementById("roomDivId");
			objVal.innerHTML = "<select name ='strRoomNo'  class='comboNormal'><option value='0'>All</option></select>";
 		}
 		else
 		{
	 		var url ="AdmittedPatientRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
			ajaxFunction(url,"1");
		}
}
function getWardCombo(obj)
{ 
	 		var url="AdmittedPatientRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value+"&deptCode="+document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].value;
			ajaxFunction(url,"2");
		
}
function getDeptCombo(obj)
{ 
 		var url;
 		url="AdmittedPatientRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
		ajaxFunction(url,"4");
}

function getRoomCombo(obj)
{ 
 		var url;
 		url="AdmittedPatientRptCNT.cnt?hmode=ROOMCMB&wardCode="+obj.value+"&deptunitCode="+document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex].value;
		ajaxFunction(url,"3");
}

function displayTime(obj)
 {
 	if(obj.checked)
 	{
 		document.getElementById("timeDiv").style.display = "block";
 	}
 	else
 		document.getElementById("timeDiv").style.display = "none";
 }
function getAjaxResponse(res,mode)
{
	if(mode=="1")
	{   
		var objVal= document.getElementById("unitDivId");
		objVal.innerHTML = "<select name ='strUnitCode' class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
		//getWardCombo(document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex]);
		objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";
		objVal= document.getElementById("roomDivId");
		objVal.innerHTML = "<select name ='strRoomNo'  class='comboNormal'><option value='0'>All</option></select>";
	}
	if(mode=="2")
	{   
		var objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode' class='comboNormal' onChange ='getRoomCombo(this);'>" + res + "</select>";
		objVal= document.getElementById("roomDivId");
		objVal.innerHTML = "<select name ='strRoomNo'  class='comboNormal'><option value='0'>All</option></select>";
	}
	if(mode=="3")
	{   
		var objVal= document.getElementById("roomDivId");
		objVal.innerHTML = "<select name ='strRoomNo' class='comboNormal'>" + res + "</select>";
	}
	if(mode=="4")
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
function clearForm()
{
        document.forms[0].hmode.value = "CLEAR";
        //document.forms[0].target = "_self";
  	    document.forms[0].submit();
} 
</script>
</head>
<body onload='getDeptCombo(document.forms[0].strHospitalCode)'>

<html:form action="/reports/AdmittedPatientRptCNT.cnt" method="post">	
<div class="normalMsg" id="normalMsg"></div>
	<tag:tab tabLabel="New Admission Detail" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
		<tr class="HEADER">
			<td colspan="2">New Admission Detail</td>
		</tr>
		<!--  <tr>
			<td width="50%" class="LABEL" colspan="2">
			<html:radio name="admPatRpt" property="strModeVal" value="2">New Admission</html:radio>
			<html:radio name="admPatRpt" property="strModeVal" value="1">Accepted</html:radio>
			</td>
		<tr>-->
		<tr>
			<td width="100%" class="LABEL" colspan="2">
			<html:checkbox name="admPatRpt" property="strOnlyAcceptedPatient" value="1">Only Accepted Patient</html:checkbox>			
			</td>
			
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital Name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal"  onchange="getDeptCombo(this);">
			<bean:write name="admPatRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>			
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Department</td>
			<td width="50%" class="CONTROL">
			<div id="deptDivId">
			<select name="strDeptCode" class="comboNormal"  onchange="getUnitCombo(this);">
			<bean:write name="admPatRpt" property="strDeptValues" filter="false"/>
			<option value="0">Select Value</option>
			</select>
			</div>
			</td>			
		</tr>
		<tr>
			<td width="50%" class="LABEL">Unit</td>
			<td width="50%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
			<option value="0">All</option>
			</select>
			</div>
			</td>			
		</tr>
		<tr>
			<td width="50%" class="LABEL">Ward</td>
			<td width="50%" class="CONTROL">
			<div id="wardDivId">
			<select name="strWardCode" class="comboNormal"  onchange="getRoomCombo(this);">
			<option value="0">All</option>
			</select>
			</div>
			</td>			
		</tr>
		<tr style="display: none;">
			<td width="50%" class="LABEL">Whether Room Wise</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsRoomWise" name="admPatRpt" value="1" onclick="visibleRoomNo(this);"></html:checkbox>
			</td>			
		</tr>
		</table>
		<div id="roomNoDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
		<tr>
			<td width="50%" class="LABEL">Room No</td>
			<td width="50%" class="CONTROL">
			<div id="roomDivId">
			<select name="strRoomNo" class="comboNormal" >
			<option value="0">All</option>
			</select>
			</div>
			</td>			
		</tr>
		</table>
		</div>
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
		<tr>
			<td class="LABEL" width="50%" ><font color="red">*</font> From Date</td>
			<td class="CONTROL" width="50%" ><dateTag:date name="strEffectiveFrom"
				value="${admPatRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL" width="50%" ><font color="red">*</font> To Date</td>
			<td class="CONTROL" width="50%" >
			<dateTag:date name="strEffectiveTo"
				value="${admPatRpt.strCurrentDate}" /></td>
		</tr>			
		<tr style="display:none;">
			<td class="LABEL" width="50%" >Time Required</td>
			<td class="CONTROL" width="50%" >
			<html:checkbox property="strIsTimeRequired" name="admPatRpt" value="1"  onclick="displayTime(this);"></html:checkbox>
			</td>
		</tr>
		</table>
		<div id="timeDiv" style="display:none;">
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> From Time</td>
					<td width="50%" class="CONTROL"><html:text name="admPatRpt" property="strFromTime" style="width:50px"
					value="${admPatRpt.strCurrentTime}" /></td>
				</tr>
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> From Time</td>
					<td width="50%" class="CONTROL"><html:text name="admPatRpt" property="strToTime" style="width:50px"
					value="${admPatRpt.strCurrentTime}" /></td>
				</tr>
			</table>
		</div>


			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
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
			<html:checkbox property="strIsFooter" name="admPatRpt" value="1"></html:checkbox>
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
	<table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<!-- <img style="cursor: hand;cursor: pointer"
				src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
				<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="clearForm();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" >
			 -->
			<br><a href="#" class="button" id="" onClick="return validate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onClick="clearForm();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
			</td>			
		</tr>
	</table>
<input type="hidden" name="hmode" value=""/>
<input type="hidden" name="strCurrentDate" value="${admPatRpt.strCurrentDate}"/>
<input type="hidden" name="strCurrentTime" value="${admPatRpt.strCurrentTime}"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>