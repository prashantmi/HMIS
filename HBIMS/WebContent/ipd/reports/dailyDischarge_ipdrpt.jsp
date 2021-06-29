<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>

<html>
<head><meta charset="utf-8" />
<title>Discharge Report</title>
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

function validate()
{
    var obj=document.getElementsByName("dischargeTypeRadio");
    var size=document.getElementsByName("strRadioBeanSize")[0].value;
        
    for(var  i=0;i<obj.length;i++)
    {
       if(obj[i].checked)
       document.getElementsByName("strRadioCheckValue")[0].value=obj[i].value;
    }
    
    
 	var hisValidator = new HISValidator("dailyDischargeRpt");
	
	/*if(document.forms[0].strDeptCode.value == "0"){
	alert("Department is a mandatory field");
	return false;
	} 
	*/
	
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
		
		if(document.getElementsByName("strHospitalCode")[0].value == "0")
		{
		alert("Please Select Hospital Name...");
		return false;
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
 		}
 		else
 		{
	 		var url ="DailyDischargeRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
	  		ajaxFunction(url,"1");
	  	}
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
function getWardCombo(obj)
{ 
 		if(obj.value=='0')
 		{
 			var objVal= document.getElementById("wardDivId");
			objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'><option value='0'>All</option></select>";
 		}
 		else
 		{	
	 		var url="DailyDischargeRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value;
	 		ajaxFunction(url,"2");
	 	}
}

function getDeptCombo(obj)
{ 
        if(obj.value == "0")
        {
        var objVal= document.getElementById("deptDivId");
		objVal.innerHTML = "<select name ='strDeptCode' class='comboNormal' onchange='getUnitCombo(this);'><option value='0'>All</option></select>";
        }
        else
        {
        var url;
 		url="DailyDischargeRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
		ajaxFunction(url,"3");
		}
}

function getAjaxResponse(res,mode)
{
	if(mode=="1")
	{   
		var objVal= document.getElementById("unitDivId");
		objVal.innerHTML = "<select name ='strUnitCode' class='comboNormal'  onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
		//getWardCombo(document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex]);
	//	var objVal1= document.getElementById("wardDivId");
	//	objVal1.innerHTML = "<select name ='strWardCode'><option value='0'>All</option></select>";
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
function clearForm()
{
	 	document.forms[0].hmode.value = "CLEAR";
	 	document.forms[0].target = "_self";
  	    document.forms[0].submit();
}


function makeHospitalCodesList(obj)
{
	 var sdValues = [];
	 var count=0;
     // resetUnitWardRoom();
	
	 // if ALL is selected send all values of hosp codes..
	 if(obj.options[0].selected==true)// ALL selected..
	 {
		  //started form 1 cz ALL is not to be included in the list..
		  for(var i = 1; i < obj.options.length; i++)
		  {
		 		if(obj.options[i].selected==true)
		 		{
		 			alert("No more selection is required if 'All' option is already selected.");
		 			for(var j = 1; j < obj.options.length; j++)
					{
					 		obj.options[j].selected=false;
					}
		 			break;
		 		}
		 }
		 for(var i = 1; i < obj.options.length; i++)
		 {
		 	sdValues.push(obj.options[i].value);
		 }		 
	 }
 	 else
 	 {
		   //make list of selected items..
		   for(var i = 1; i < obj.options.length; i++)
		   {
		      if(obj.options[i].selected == true)
		      {
		      	sdValues.push(obj.options[i].value);
		      	count++;
		      }
		   }		 
 	 }
 		// document.forms[0].strHospitalCodes.value=sdValues;
 		
}
</script>
</head>
<body onload='getDeptCombo(document.forms[0].strHospitalCode)'>
<html:form action="/reports/DailyDischargeRptCNT.cnt" method="post">

	<div class="normalMsg" id="normalMsg"></div>
	<tag:tab tabLabel="Discharge Report" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>

	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
		<tr class="HEADER">
				<td colspan="2">Discharge Report</td>
		</tr>
		
		
		
		
		<tr align="left">
			<td width="0%" class="CONTROL">
			</td>		
			<td width="100%" class="LABEL">
			<DIV align="left"><bean:write name="dailyDischargeRpt" property="strRadioBean" filter="false" /></DIV>
		    </td>			
		</tr>
		
			
		<tr>
			<td width="50%" class="LABEL">Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal"   onchange="getDeptCombo(this);">
			<bean:write name="dailyDischargeRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>
		
		
		
		
		<tr>
			<td width="50%" class="LABEL">Department</td>
			<td width="50%" class="CONTROL">
			<div id="deptDivId">
			<select name="strDeptCode" class="comboNormal"  onchange="getUnitCombo(this);">
			<bean:write name="dailyDischargeRpt" property="strDeptValues" filter="false"/>
			<option value="0">All</option></select>
			
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
			 <td width="50%" class ="CONTROL"><div id ="wardDivId">
			 <select name="strWardCode" class="comboNormal" onchange="">
	    		<option value="0">All</option>
	          </select> </div>
      		 </td>
		</tr>  
		
		
  		<tr>
			<td class="LABEL"><font color="red">*</font> From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${dailyDischargeRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font> To Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${dailyDischargeRpt.strCurrentDate}" /></td>
		</tr>  
	
	        <tr>
			<td width="50%" class="LABEL">
			Time Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsTimeRequired" name="dailyDischargeRpt" value="1"  onclick="displayTime(this);"></html:checkbox>
			</td>
		   </tr>
		   
		</table>
		<div id="timeDiv" Style="display:none">
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> From Time</td>
					<td width="50%" class="CONTROL"><html:text name="dailyDischargeRpt" property="strFromTime" style="width:50px"
					value="${dailyDischargeRpt.strCurrentTime}" /></td>
				</tr>
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> To Time</td>
					<td width="50%" class="CONTROL"><html:text name="dailyDischargeRpt" property="strToTime" style="width:50px"
					value="${dailyDischargeRpt.strCurrentTime}" /></td>
				</tr>
			</table>
		</div>
		
		
		
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
		
		  
  		<tr>
			<td width="50%" class="LABEL">Report Format</td>
			<td width="50%" class="CONTROL">
			<select name="strReportFormat" class="comboSmall" onchange=""><option value="html">HTML</option>
			<option value="pdf">PDF</option>
			<option value="excel">EXCEL</option>
			</select>
			</td>
		</tr> 
		<tr>
			<td width="50%" class="LABEL">Footer Required</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="dailyDischargeRpt" value="1"></html:checkbox>
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
				<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();clearForm();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancelFunc();" > -->
			
			<br><a href="#" class="button" id="" onClick="return validate();" ><span class="generate">Generate</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset();clearForm();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>	
			</td>
			
		</tr>
	</table>
	<script>

	</script>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${dailyDischargeRpt.strCurrentDate}"/>
<input type="hidden" name="strCurrentTime" value="${dailyDischargeRpt.strCurrentTime}"/>
<input type="hidden" name="strRadioBeanSize" value="${dailyDischargeRpt.strRadioBeanSize}"/>
<input type="hidden" name="strRadioCheckValue" />
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>