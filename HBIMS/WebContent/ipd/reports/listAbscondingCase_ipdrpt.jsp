<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>List of Absconding Cases</title>
<link href="../css/report.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script type="text/javascript"><!--

function validate()
{
	
	var hisValidator = new HISValidator("listAbsCaseRpt");
	hisValidator.addValidation("strHospName", "dontselect=0","Please Select Hospital Name");
	if((document.getElementsByName("strCase")[0].value == "1") && document.getElementsByName("strCase")[0].checked){
	if(document.forms[0].strDeptCode.value == "0"){
	alert("Department is a mandatory field");
	return false;
	} 
	}
	
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
		
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html"){
			
				document.forms[0].target = "_self";
		}else{
			
			document.forms[0].target = "_blank";
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
	document.getElementById("deptwardDivId").style.display = "block";
	}else{
	document.getElementById("deptwardDivId").style.display = "none";
	}
	
}
function getUnitCombo(obj){ 
		if(obj.value!=0){
 			var url ="ListAbscondingCaseRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
 			ajaxFunction(url,"1");
 		}else{
 		
 			document.forms[0].strUnitCode.value = "0";
 			document.forms[0].strWardCode.value = "0";
 		}	
 		//getWardCombo(document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex]);
		
	}
function getDeptCombo(obj)
{ 
 		var url;
 		url="ListAbscondingCaseRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
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
function getWardCombo(obj){ 
		if(obj.value!=0){
	 		var url="ListAbscondingCaseRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value;
	 		ajaxFunction(url,"2");
		}else{
 		
 			document.forms[0].strWardCode.value = "0";
 			
 		}	
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
				objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res + "</select>";
			
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

--></script>
</head>
<body onload="getCombo(document.forms[0].strCase);">
<html:form action="/reports/ListAbscondingCaseRptCNT.cnt" method="post">

	<div class="normalMsg" id="normalMsg"></div>

	<tag:tab tabLabel="List of Absconding Cases"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>

	<table class="TABLEWIDTH" align="center">   
	<tr class="HEADER">
			<td colspan="2">List of Absconding Cases</td>
		</tr>
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right">
    <html:radio property="strCase" name="listAbsCaseRpt" value="1" onclick="getCombo(this);" >Ward Wise</html:radio>
    <html:radio property="strCase" name="listAbsCaseRpt" value="2" onclick="getCombo(this);" >Date Wise</html:radio>
    </div>
    </td>
    </tr>
    </table>
    <table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
    <tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange="getDeptCombo(this)" >
			<bean:write name="listAbsCaseRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
		</tr>	
		</table>
    
		 <div id="deptwardDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  		
  		  		
		
	 <tr >
    		<td width="50%" class="LABEL"><font color="red">*</font>Department</td>
    		<td width="50%" class="CONTROL">
    		<div id="deptDivId">
    		<select name="strDeptCode"  onchange="getUnitCombo(this);" class="comboNormal">
    			
        	<bean:write name="listAbsCaseRpt" property="strDeptValues" filter="false"/> 
        	<option value="0">Select Value</option>
        	</select>
        	</div> 
        	</td>
  		</tr>
  		
  		
  		 <tr>
			<td width="50%" class="LABEL">
			Unit
			</td>
			<td width="50%" class="CONTROL">
			<div id="unitDivId">
			<select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
			<option value="0">All</option></select>
			</div>
			</td>
			
		</tr>
  		<tr>
			 <td width="50%" class="LABEL">Ward </td>
			 <td width="50%" class ="CONTROL"><div id ="wardDivId">
			 <select name="strWardCode" class="comboNormal" onchange="">
    		<option value="0">All</option>
          </select> </div>
      </td>
		</tr>
		</table>
		</div>
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
   <tr>
			<td  class="LABEL"><font color="red">*</font> From Date</td>
			<td  class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${listAbsCaseRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td  class="LABEL"><font color="red">*</font> To Date</td>
			<td  class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${listAbsCaseRpt.strCurrentDate}" /></td>
		</tr>
		
			<tr>
			<td width="50%" class="LABEL">
			Time Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsTimeRequired" name="listAbsCaseRpt" value="1"  onclick="displayTime(this);"></html:checkbox>
			</td>
		</tr>
		</table>
		<div id="timeDiv" Style="display:none">
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> From Time</td>
					<td width="50%" class="CONTROL"><html:text name="listAbsCaseRpt" property="strFromTime" style="width:50px"
					value="${listAbsCaseRpt.strCurrentTime}" /></td>
				</tr>
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> To Time</td>
					<td width="50%" class="CONTROL"><html:text name="listAbsCaseRpt" property="strToTime" style="width:50px"
					value="${listAbsCaseRpt.strCurrentTime}" /></td>
				</tr>
			</table>
		</div>
		
		
		
		
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
			
		
		<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat" class="comboSmall"  onchange=""><option value="html">HTML</option><option value="pdf">PDF</option></select>
			</td>
			
		</tr> 
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="listAbsCaseRpt" value="1"></html:checkbox>
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
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancel();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${listAbsCaseRpt.strCurrentDate}"/>
<input type="hidden" name="strCurrentTime" value="${listAbsCaseRpt.strCurrentTime}"/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>