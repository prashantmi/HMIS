<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>Cancelled Patient List</title>
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

function validate(){


	if(document.forms[0].strHospitalCode.value == "0"){
			alert("Please Select Hospital");
			return false;
		} 
	/*if(document.forms[0].strDeptCode.value == "0"){
			alert("Department is a mandatory field");
			return false;
		}*/
	
		var hisValidator = new HISValidator("nonAccPatListRpt");
		hisValidator.addValidation("strEffectiveFrom", "date","From Date is a mandatory field");
	hisValidator.addValidation("strEffectiveTo", "date","To Date is a mandatory field");

	hisValidator.addValidation("strEffectiveTo","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
	hisValidator.addValidation("strEffectiveTo","dtgtet="+document.forms[0].strEffectiveFrom.value,"Please Select To Date Greater Than Or Equal To From Date");
	
	var dateDifference = dateDiff(document.forms[0].strEffectiveFrom.value,document.forms[0].strEffectiveTo.value);
			 	
	 if(parseInt(dateDifference) > 365)
	{
				alert("Date Difference should not be greater than 365 days");
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
			
			//document.forms[0].target = "_blank";
			document.forms[0].target = "_self";
		}

		document.forms[0].submit();
		}else{
		return false;
		}
}
function getDeptCombo(obj)
	{ 
 		var url;
 		url="NonAcceptedPatientListingRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
		ajaxFunction(url,"4");
	}
function getUnitCombo(obj){ 

 		var url ="NonAcceptedPatientListingRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
 		ajaxFunction(url,"1");
		
	}

function getWardCombo(obj){ 

 		var url="NonAcceptedPatientListingRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value;
 		
 		ajaxFunction(url,"2");
		
	}

	function getAjaxResponse(res,mode){
	

	if(mode=="1"){   
		var objVal= document.getElementById("unitDivId");
		objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
		objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";

	}
	
	if(mode=="2"){   
		var objVal= document.getElementById("wardDivId");
		objVal.innerHTML = "<select name ='strWardCode' class='comboNormal'>" + res + "</select>";
	}
	if(mode=="4")
	{   
		var objVal= document.getElementById("deptDivId");
		objVal.innerHTML = "<select name ='strDeptCode' class='comboNormal' onchange='getUnitCombo(this);'>" + res + "</select>";
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

</script>
</head>
<body onload='getDeptCombo(document.forms[0].strHospitalCode)'>
<html:form action="/reports/NonAcceptedPatientListingRptCNT.cnt" method="post">

	
<div class="normalMsg" id="normalMsg"></div>
	
		<tag:tab tabLabel="Cancelled / Not Reported Patient Listing"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">  
	<tr class="HEADER">
			<td width="50%">Cancelled / Not Reported Patient Listing</td>
			<td class="HEADER" align="right" width="25%" >Only Not Reported Patient By Nurse
			<html:checkbox property="isNotReported" name="nonAccPatListRpt"></html:checkbox></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange="getDeptCombo(this);">
			<bean:write name="nonAccPatListRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>
		
		<tr> 
    		<td width="50%" class="LABEL"><font color="red">*</font>Department</td>
    		<td width="50%" class="CONTROL"><div id="deptDivId">
    		<select name="strDeptCode" class="comboNormal" onchange="getUnitCombo(this);" >
        	<option value="0">Select Value</option>
        	 </select>
        	</div></td>
  		</tr>
  		 <tr style="display:none;">
			<td width="50%" class="LABEL">
			Unit
			</td>
			<td width="50%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
			<option value="0">All</option> </select>
			</div>
			</td>
			
		</tr>
  		<tr>
			 <td width="50%" class="LABEL">Ward</td>
			 <td width="50%" class ="CONTROL"><div id ="wardDivId">
			 <select name="strWardCode" class="comboNormal" onchange="">
    		<option value="0">All</option>
          </select> </div>
      </td>
		</tr>
   <tr>
			<td  class="LABEL"><font color="red">*</font> From Date</td>
			<td  class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${nonAccPatListRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td  class="LABEL"><font color="red">*</font> To Date</td>
			<td  class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${nonAccPatListRpt.strCurrentDate}" /></td>
		</tr>
	     <tr style="display:none">
			<td width="50%" class="LABEL">
			Time Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsTimeRequired" name="nonAccPatListRpt" onclick="displayTime(this);"></html:checkbox>
			</td>
			
		</tr>

	</table>
		<div id="timeDiv" Style="display:none">
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> From Time</td>
					<td width="50%" class="CONTROL"><html:text name="nonAccPatListRpt" property="strFromTime" style="width:50px"
					value="00:00" />HH24:MM</td>
				</tr>
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> To Time</td>
					<td width="50%" class="CONTROL"><html:text name="nonAccPatListRpt" property="strToTime" style="width:50px"
					value="${nonAccPatListRpt.strCurrentTime}" />HH24:MM</td>
				</tr>
			</table>
		</div>
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat" class="comboSmall" onchange="">
			<option value="html">HTML</option>
			<option value="pdf">PDF</option>
			<option value="excel">EXCEL</option>
		</select>
			</td>
			
		</tr> 
		
		
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="nonAccPatListRpt" value="1"></html:checkbox>
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
			<!-- <img style="cursor: hand;cursor: pointer"
				src="../../hisglobal/images/btn-generate.png" onClick="return validate();" />
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
<input type="hidden" name="strCurrentDate" value="${nonAccPatListRpt.strCurrentDate}"/>
<input type="hidden" name="strCurrentTime" value="${nonAccPatListRpt.strCurrentTime}"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>