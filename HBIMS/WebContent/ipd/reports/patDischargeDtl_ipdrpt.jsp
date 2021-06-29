<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head><meta charset="utf-8" />
<title>Patient Discharge Detail Report</title>
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
	var hisValidator = new HISValidator("patDisDtlRpt");
		
	
	if(document.forms[0].strHospitalCode.value == "0"){
			alert("Please Select Hospital");
			return false;
		} 	
	
	if(document.forms[0].strDeptCode.value == "0"){
		alert("Department is a mandatory field");
		return false;
	} 	
		
	if(document.forms[0].strCase[3].checked == true){
	
	hisValidator.addValidation("strCrNo", 

			"minlen="+document.forms[0].strCrNo.maxLength, 

			"Cr No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );


	
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
	
	document.getElementById("crnoDivId").style.display = "none";
	document.getElementById("deptDivId").style.display = "block";
	document.getElementById("unitDivId").style.display = "block";
	document.getElementById("wardDivId").style.display = "block";
	document.getElementById("roomNoDivId").style.display = "none";
	document.getElementById("bedNoDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	
	}else if(obj.value == 2){
	
	document.getElementById("crnoDivId").style.display = "none";
	document.getElementById("deptDivId").style.display = "block";
	document.getElementById("unitDivId").style.display = "block";
	document.getElementById("wardDivId").style.display = "block";
	document.getElementById("roomNoDivId").style.display = "block";
	document.getElementById("bedNoDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	
	}else if(obj.value == 3){
	
	document.getElementById("crnoDivId").style.display = "none";
	document.getElementById("deptDivId").style.display = "block";
	document.getElementById("unitDivId").style.display = "block";
	document.getElementById("wardDivId").style.display = "block";
	document.getElementById("roomNoDivId").style.display = "block";
	document.getElementById("bedNoDivId").style.display = "block";
	document.getElementById("dateDivId").style.display = "block";
	
	}else if(obj.value == 4){
	
	document.getElementById("crnoDivId").style.display = "block";
	document.getElementById("deptDivId").style.display = "none";
	document.getElementById("unitDivId").style.display = "none";
	document.getElementById("wardDivId").style.display = "none";
	document.getElementById("roomNoDivId").style.display = "none";
	document.getElementById("bedNoDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "none";
	
	}else if(obj.value == 5){
	
	document.getElementById("crnoDivId").style.display = "none";
	document.getElementById("deptDivId").style.display = "none";
	document.getElementById("unitDivId").style.display = "none";
	document.getElementById("wardDivId").style.display = "none";
	document.getElementById("roomNoDivId").style.display = "none";
	document.getElementById("bedNoDivId").style.display = "none";
	document.getElementById("dateDivId").style.display = "block";
	
	}
}

	function getDeptCombo(obj)
	{ 
 		var url;
 		url="PatDischargeDtlRptCNT.cnt?hmode=DEPTCMB&hospCode="+obj.value;
		ajaxFunction(url,"5");
	}

function getUnitCombo(obj){ 

 		var url ="PatDischargeDtlRptCNT.cnt?hmode=UNITCMB&deptCode="+obj.value;
 		 		
 			ajaxFunction(url,"1");
		
		}

function getWardCombo(obj){ 

 		var url="PatDischargeDtlRptCNT.cnt?hmode=WARDCMB&deptunitCode="+obj.value+"&deptCode="+document.forms[0].strDeptCode[document.forms[0].strDeptCode.selectedIndex].value;
 	
 			ajaxFunction(url,"2");
		
		}
		function getRoomCombo(obj){ 

 		var url;
 		url="PatDischargeDtlRptCNT.cnt?hmode=ROOMCMB&wardCode="+obj.value+"&deptunitCode="+document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex].value;
 			
 			ajaxFunction(url,"3");
		
	}
		function getBedCombo(obj){ 

 		var url;
 		url="PatDischargeDtlRptCNT.cnt?hmode=BEDCMB&wardCode="+document.forms[0].strWardCode.value+"&deptunitCode="+document.forms[0].strUnitCode[document.forms[0].strUnitCode.selectedIndex].value+"&roomCode="+document.forms[0].strRoomNo.value;
 			ajaxFunction(url,"4");
		
	}
	function getAjaxResponse(res,mode){
	
		
	if(mode=="1"){   
			var objVal= document.getElementById("unitId");
			objVal.innerHTML = "<select name ='strUnitCode'  class='comboNormal' onChange ='getWardCombo(this);'>" + res.split('^')[0] + "</select>";
			objVal= document.getElementById("wardId");
			objVal.innerHTML = "<select name ='strWardCode'  class='comboNormal'>" + res.split('^')[1] + "</select>";
	}
	
	if(mode=="2"){   
			var objVal= document.getElementById("wardId");
		objVal.innerHTML = "<select name ='strWardCode' class='comboNormal' onchange='getRoomCombo(this)'>" + res + "</select>";
	}
	if(mode=="3"){
			var objVal= document.getElementById("roomDivId");
		objVal.innerHTML = "<select name ='strRoomNo' class='comboNormal' onchange='getBedCombo(this)' >" + res + "</select>";
	}
	if(mode=="4"){
			var objVal= document.getElementById("bedDivId");
		objVal.innerHTML = "<select name ='strBedCode' class='comboNormal' >" + res + "</select>";
	}
	if(mode=="5")
	{   
		var objVal= document.getElementById("deptDivId2");
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
<body onload="getCombo(document.forms[0].strCase);" >
<html:form action="/reports/PatDischargeDtlRptCNT.cnt" method="post">
	<div class="normalMsg" id="normalMsg"></div>

		
		<tag:tab tabLabel="Patient Discharge Detail"
                        selectedTab="FIRST" align="center" width="TABLEWIDTH">
                  </tag:tab>


	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">   
	<tr class="HEADER">
			<td colspan="2">Patient Discharge Detail</td>
		</tr>
		<tr >
    <td class="LABEL" colspan="2">
    <div align="right" style="display:none">
    <html:radio property="strCase" name="patDisDtlRpt" value="1" onclick="getCombo(this);" >Ward</html:radio>
    <html:radio property="strCase" name="patDisDtlRpt" value="2" onclick="getCombo(this);" >Room</html:radio>
    <html:radio property="strCase" name="patDisDtlRpt" value="3" onclick="getCombo(this);" >Bed</html:radio>
    <html:radio property="strCase" name="patDisDtlRpt" value="4" onclick="getCombo(this);" >CR No</html:radio>
    <html:radio property="strCase" name="patDisDtlRpt" value="5" onclick="getCombo(this);" >Date</html:radio>
    </div>
    </td>
    </tr>
    <tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Hospital name</td>
			<td width="50%" class="CONTROL">
			<select name="strHospitalCode" class="comboNormal" onchange="getDeptCombo(this);">
			<bean:write name="patDisDtlRpt" property="strHospNameValues" filter="false"/>
			</select>
			</td>			
	</tr>
    
    </table>
    
   
     <div id="crnoDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
   <tr>
    	<td colspan="1" width="50%" class="LABEL">CR No.</td>
       <td colspan="1" width="50%" class="CONTROL">
       		<crNo:crNo value="${patDisDtlRpt.strCrNo}" js="onkeypress='return validateData(event,5)';"></crNo:crNo>
       </td>
  </tr>
  </table>
  </div>
  
    
    <div id="deptDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  <tr> 
    <td width="50%" class="LABEL"><font color="red">*</font>Department</td>
    <td width="50%" class="CONTROL">
   			<div id="deptDivId2">
    		<select name="strDeptCode" class="comboNormal" onchange="getUnitCombo(this);" >
        	<option value="0">Select Value</option>
        	 </select>
        	</div>
        </td>
  </tr>
  </table>
  </div>
    
    
    
    
	
	<div id="unitDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  <tr> 
    <td width="50%" class="LABEL">Unit</td>
    <td width="50%" class="CONTROL">
    <div Id = "unitId">
    <select name="strUnitCode" class="comboNormal" onchange="getWardCombo(this);">
    <option value="0">All</option>
	</select>
	</div>
	</td>
  </tr>
  </table>
  </div>
  
  <div id="wardDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" >  
  
  	<tr> 
    <td width="50%" class="LABEL">Ward</td>
    <td width="50%" class="CONTROL"><div Id = "wardId">
    <select name="strWardCode" class="comboNormal" onchange="getRoomCombo(this);">
   <option value="0">All</option>
     </select></div></td>
  	</tr>
	</table>
	</div>
	<div id="roomNoDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
		<tr>
			<td width="50%" class="LABEL">
			Room No
			</td>
			<td width="50%" class="CONTROL">
			<div id="roomDivId">
			<select name="strRoomNo" class="comboNormal"  onchange="getBedCombo(this);">
			<option value="0">All</option>
			</select>
			</div>
			</td>
			
		</tr>
		</table>
		</div>
  
  <div id="bedNoDivId" style="display:none">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
		<tr>
			<td width="50%" class="LABEL">
			Bed No
			</td>
			<td width="50%" class="CONTROL">
			<div id="bedDivId">
			<select name="strBedCode" class="comboNormal" >
			<option value="0">All</option>
			</select>
			</div>
			</td>
			
		</tr>
		</table>
		</div>
  
  
   <div id="dateDivId" style="display:block">
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
  
  <tr>
			<td width="50%" class="LABEL"><font color="red">*</font> From Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${patDisDtlRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font> To Date</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveTo"
				value="${patDisDtlRpt.strCurrentDate}" /></td>
		</tr>
		<tr>
			<td width="50%" class="LABEL">
			Time Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsTimeRequired" name="patDisDtlRpt" onclick="displayTime(this);"></html:checkbox>
			</td>
			
		</tr>
  </table>
  </div>
	
	<div id="timeDiv" Style="display:none">
			<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px" > 
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> From Time</td>
					<td width="50%" class="CONTROL"><html:text name="patDisDtlRpt" property="strFromTime" style="width:50px"
					value="00:00" />HH24:MM</td>
				</tr>
				<tr>
					<td width="50%" class="LABEL"><font color="red">*</font> To Time</td>
					<td width="50%" class="CONTROL"><html:text name="patDisDtlRpt" property="strToTime" style="width:50px"
					value="${patDisDtlRpt.strCurrentTime}" />HH24:MM</td>
				</tr>
			</table>
	</div>
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px"> 
	<tr>
			<td width="50%" class="LABEL">
			Report Format
			</td>
			<td width="50%" class="CONTROL">
		<select name="strReportFormat" class="comboSmall" >
		<option value="html">HTML</option>
		<option value="pdf">PDF</option>
		</select>
			</td>
			
		</tr> 
	 
		<tr>
			<td width="50%" class="LABEL">
			Footer Required
			</td>
			<td width="50%" class="CONTROL">
			<html:checkbox property="strIsFooter" name="patDisDtlRpt" value="1"></html:checkbox>
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
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-clr.png" onClick="document.forms[0].reset();hideTime();" >
			<img style="cursor: hand;cursor: pointer" src="../../hisglobal/images/btn-ccl.png" onClick="cancel();" >
			</td>
			
		</tr>
	</table>
<input type="hidden" name="hmode"/>
<input type="hidden" name="strCurrentDate" value="${patDisDtlRpt.strCurrentDate}"/>
<input type="hidden" name="strCurrentTime" value="${patDisDtlRpt.strCurrentTime}"/>

</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>