<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: Neha Sharma
 	 Dated			: 17-Feb-2014 -->

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Appointment Configuration Master</title>
<link href="/HISRegistration/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HISRegistration/hisglobal/css/layout.css" rel="stylesheet"	type="text/css">

<link href="/HISRegistration/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/HISRegistration/hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="/HISRegistration/hisglobal/css/easyui.css">
<link rel="stylesheet" href="/HISRegistration/hisglobal/css/basic.css">

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script language="Javascript" src="/HISRegistration/hisglobal/masterutil/js/validation.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type="text/javascript" src="/HISRegistration/appointment/masters/js/AppointmentConfiguration.js"></script>
<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<script>



$(document).ready(function() {
	 
	$('#ADDROW').click(AddRowToTable2);
	
	$("[name='priorApptDays']").validatebox({
		required:true				
	});
	$("[name='opdApptSlots']").validatebox({
		required: false,
		validType : 'integer'
	});
	$("[name='ipdApptSlots']").validatebox({
		required: false,
		validType : 'integer'
	});	
	applyMultirowValidation();

	return false;
});


// save data..
function submitSaveAction(cnt)
{
	var isValid = $('#ApptConfigMst').form('validate');
    if(isValid==false)
      return false;
    
	getSelApptType();
	
	if((document.getElementsByName("apptTypeIdSel")[0].options.length <= 0))
	{
		alert("Kindly select atleast one appointment type.");
		return;
	}
	
	if((document.getElementsByName("priorApptDays")[0].value == 0))
	{
		alert("Kindly enter Prior Appointment Days.");
		return;
	}
	
	if(document.getElementsByName("shiftId").length==0)
	{
		alert("Kindly enter Appointment shift details.");
		return;
	}
	
	for(var i=0;i<document.getElementsByName("shiftId").length;i++)
	{	
		/*
			(				#start of group #1
			 [01]?[0-9]			#  start with 0-9,1-9,00-09,10-19
			 |				#  or
			 2[0-3]				#  start with 20-23
			)				#end of group #1
			 :				#    follow by a semi colon (:)
			  [0-5][0-9]			#      follw by 0..5 and 0..9, which means 00 to 59
		*/
		var TIME24HOURS_PATTERN =new RegExp("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		//alert("reg ex::"+TIME24HOURS_PATTERN.test(document.getElementsByName("startTime")[i].value));
		if(!(TIME24HOURS_PATTERN.test(document.getElementsByName("startTime")[i].value)))
		{
			alert("Start time should be entered in proper 24 hours format.Error at row "+(i+1));
			return;
		}
		
		if(!(TIME24HOURS_PATTERN.test(document.getElementsByName("endTime")[i].value)))
		{
			alert("End time should be entered in proper 24 hours format.Error at row "+(i+1));
			return;
		}
		
		 var startHour = document.getElementsByName("startTime")[i].value.split(":")[0];
		 var startMinute = document.getElementsByName("startTime")[i].value.split(":")[1];
		 var startSecond = 0;

		 var endHour = document.getElementsByName("endTime")[i].value.split(":")[0];
		 var endMinute = document.getElementsByName("endTime")[i].value.split(":")[1];
		 var endSecond = 0;

		 //Create date object and set the time to that
		 var startTimeObject = new Date();
		 startTimeObject.setHours(startHour, startMinute, startSecond);

		 //Create date object and set the time to that
		 var endTimeObject = new Date(startTimeObject);
		 endTimeObject.setHours(endHour, endMinute, endSecond);
			
		 //Now compare both the dates
		 if(startTimeObject > endTimeObject)
		 {
		 alert("End time should be after start time.");
		 return;
		 }
		 
	}
	
	// iterate over each row..'Appointment Shift..'
	for(var i=0;i<document.getElementsByName("shiftId").length;i++)
	{
		var uniqueId=document.getElementsByName("shiftId")[i].id.split("_")[1];
		var shiftSelectedDays="";
		var shiftSelVip="";
		
		// to store selected radio value of each row..
/* 		if(document.getElementsByName("rdo_" + uniqueId)[0].checked)
		{
			shiftSelVip=document.getElementsByName("rdo_" + uniqueId)[0].value;
		}
		else
		{
			shiftSelVip=document.getElementsByName("rdo_" + uniqueId)[1].value;	
		}
		
		if(shiftSelVip!="")
		{
			document.getElementById("SHIFTWISESELECTEDRADIO_"+uniqueId).value= shiftSelVip;
		}
		else
		{
			alert("Kindly select 'VIP Slot'.");
			return;
		} */
		
		for(var j=0;j<document.getElementsByName("chk_" + uniqueId).length;j++)
		{
			if(document.getElementsByName("chk_" + uniqueId)[j].checked)
				shiftSelectedDays+= document.getElementsByName("chk_" + uniqueId)[j].value + "^";			
		}
		if(shiftSelectedDays!="")
		{
			shiftSelectedDays=shiftSelectedDays.substr(0,shiftSelectedDays.length-1);
			document.getElementById("SHIFTWISESELECTEDDAYS_"+uniqueId).value= shiftSelectedDays;
		}
		else
		{
			alert("No days selected for the shift of row "+(i+1));
			return;
		}
		
		var tempObjShiftArr=document.getElementsByName("shiftId");
		
		for(var t=i+1;t<tempObjShiftArr.length;t++)
		{
			if(tempObjShiftArr[i].value==tempObjShiftArr[t].value && document.getElementsByName("weekOfMonth")[0].value=="0")
			{
				alert("Duplicate shifts cannot be added");
				return;
			}
		}
		
		
		//rest fields are non mandatory.
		
	}
	
	//alert("validations passed..");
	document.forms[0].action = "updateApptConfigMst.action";
	document.forms[0].submit();
}


   // to set duration unit in hidden field..
	function setDurationUnit()
    {
    	var tempEle=document.getElementsByName('apptDurationValue')[0];
    	var optVal=tempEle.options[0].text.split('-')[1];
    	var optValTime = '';
    	optValTime = tempEle.options[tempEle.selectedIndex].text.split('-')[0];
    	document.getElementsByName('apptDurationUnit')[0].value=optVal;
    	
    	// set previously selected unit..
    	var tempEle1=document.getElementsByName('apptDurationValue')[0];
    	for(var i=0;i<tempEle1.options.length;i++)
    	{
    		if(tempEle1.options[i].value == document.getElementsByName('apptDurationValue')[0])
    		{
    			tempEle1.options[i].selectedIndex=i;
    			
    			//alert(optValTime);
    		}
    	}
    	document.getElementsByName('apptDurationUnit')[0].value=optVal;
		document.getElementsByName('apptDurationUnitTime')[0].value=optValTime;
		//alert(document.getElementsByName('apptDurationUnitTime')[0].value);
    }
    
	//to set duration unit in hidden field onChange..
	function setDurationUnit_onchange(){
		var tempEleObj=$('[name="apptDurationValue"]')[0];
		//alert(tempEleObj.options[tempEleObj.selectedIndex].text);
		var optVal=tempEleObj.options[tempEleObj.selectedIndex].text.split('-')[1];	
		var optValTime=tempEleObj.options[tempEleObj.selectedIndex].text.split('-')[0];
		document.getElementsByName('apptDurationUnit')[0].value=optVal;
		document.getElementsByName('apptDurationUnitTime')[0].value=optValTime;
		//alert(document.getElementsByName('apptDurationUnitTime')[0].value);
	}
	
    function governCheckBoxSel()
    {
     // alert("called...");
    	var objshiftwiseSelectedDays= document.getElementsByName("shiftwiseSelectedDays");
    	for(var i=0;i< objshiftwiseSelectedDays.length;i++){
    	//	alert("called...1");
    		var arrshiftwiseSelectedDays=objshiftwiseSelectedDays[i].value.split("^");
    		var uniqueId=objshiftwiseSelectedDays[i].id.split("_")[1];
    		//alert("unique id::"+uniqueId);
    		var chkobj=document.getElementsByName("chk_"+ uniqueId);
    		for(var j=0;j<chkobj.length;j++){
    			//alert("called...2"+chkobj[0].value);
    			for(k=0;k<arrshiftwiseSelectedDays.length;k++){
    				//alert("arrshiftwiseSelectedDays:::"+arrshiftwiseSelectedDays[k]);
    				//alert("chkobj::"+chkobj[j].value + " arrshiftwiseSelectedDays[k]--" + arrshiftwiseSelectedDays[k]);
    				//alert("j::"+j+"::k::"+k);
    				if(arrshiftwiseSelectedDays[k]==chkobj[j].value)
    					chkobj[j].checked=true;
    			} // for k ends
    		} // for j ends
    	} // for i ends
    	
    	setShiftId();
    
    }
    
    // to set previously selected index..
    function setShiftId()
    {
    	
    	var objshiftwiseSelIds= document.getElementsByName("shiftIdHidden");
    	var objOrgShiftId=document.getElementsByName("shiftId");
    	var selIndex=0;
    	
    	for(var i=0;i< objshiftwiseSelIds.length;i++)
    	{
    		
    		for(var j=0;j<objOrgShiftId[i].options.length;j++)
    		{
    			
    			if(objshiftwiseSelIds[i].value == objOrgShiftId[i].options[j].value)
        		{
        			
    				selIndex=j;
        			break;
        		}
    		}
    		
    		objOrgShiftId[i].selectedIndex=selIndex;
    		
    	}
    	
    }    
   
</script>
</head>


<body onload="setDurationUnit();governCheckBoxSel();">
<div class="wrapper rounded">
	<s:form action="ApptConfigMst" >
	
			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col  width100 "><s:text name="Apt_appointment"/>&nbsp;<s:text name="config"/>&nbsp;<s:text name="global.master"/></div>
				</div>

				<div class="div-table-row ">
					<div class="div-table-col title width100 ">
						<s:text name="modify"/>&nbsp;<s:text name="config"/>&nbsp;<s:text name="global.details"/> 
					</div>
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="for"/> :
					</div>
					<div class="div-table-col   width45">
						<s:label  name="appointmentForName" value="%{appointmentForName}" cssStyle="color: #000000;font-weight:normal"></s:label>
					</div>
				</div>
				
				<div class="div-table-row " id="actualParaDiv1">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:label  name="labelPara1" value="%{labelPara1}" ></s:label> :
					</div>
					<div class="div-table-col   width45">
						<s:label  name="paraId1" value="%{paraId1}" cssStyle="color: #000000;font-weight:normal"></s:label>
					</div>
				</div>
				
				<s:if test="%{!paraId2.equals('')}">
				
				<div class="div-table-row " id="actualParaDiv2">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:label  name="labelPara2" value="%{labelPara2}" ></s:label> :
					</div>
					<div class="div-table-col   width45">
						<s:label  name="paraId2" value="%{paraId2}" cssStyle="color: #000000;font-weight:normal"></s:label>
					</div>
				</div>
				</s:if>
				<s:if test="%{!paraId3.equals('')}">
				<div class="div-table-row " id="actualParaDiv3">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:label  name="labelPara3" value="%{labelPara3}" ></s:label> :
					</div>
					<div class="div-table-col   width45">
						<s:label  name="paraId3" value="%{paraId3}" cssStyle="color: #000000;font-weight:normal"></s:label>
					</div>
				</div>
				</s:if>
				<s:if test="%{!paraId4.equals('')}">
				<div class="div-table-row " id="actualParaDiv4">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:label  name="labelPara4" value="%{labelPara4}" ></s:label> :
					</div>
					<div class="div-table-col   width45">
						<s:label  name="paraId4" value="%{paraId4}" cssStyle="color: #000000;font-weight:normal"></s:label>
					</div>
				</div>
				</s:if>
				<s:if test="%{!paraId5.equals('')}">
				<div class="div-table-row " id="actualParaDiv5">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:label  name="labelPara5" value="%{labelPara5}" ></s:label> :
					</div>
					<div class="div-table-col   width45">
						<s:label  name="paraId5" value="%{paraId5}" cssStyle="color: #000000;font-weight:normal"></s:label>
					</div>
				</div>
				</s:if>
				<s:if test="%{!paraId6.equals('')}">
				<div class="div-table-row " id="actualParaDiv6">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:label  name="labelPara6" value="%{labelPara6}" ></s:label> :
					</div>
					<div class="div-table-col   width45">
						<s:label  name="paraId6" value="%{paraId6}" cssStyle="color: #000000;font-weight:normal"></s:label>
					</div>
				</div>
				</s:if>
				<s:if test="%{!paraId7.equals('')}">
				<div class="div-table-row " id="actualParaDiv7">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:label  name="labelPara7" value="%{labelPara7}" ></s:label> :
					</div>
					<div class="div-table-col   width45">
						<s:label  name="paraId7" value="%{paraId7}" cssStyle="color: #000000;font-weight:normal"></s:label>
					</div>
				</div>
				</s:if>
				<div class="div-table-row ">
					
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="global.type"/>:
					</div>
					<div class="div-table-col column  width10">
					 <%-- <s:if test="%{#session.LSTAPPOINTMENTMODE}"> --%>
					 <s:if test="%{#session.apptTypeModList.size>0}">
					 <s:select list="%{#session.apptTypeModList}" listKey="value" listValue="label" name="apptTypeId" size="5" > </s:select>
					 
					 </s:if>
					 <s:else>
					 <s:select 
				 		 list="%{{}}"  name="apptTypeId" size="5" > </s:select>
					 </s:else>
					 <%--  <c:if test="%{not empty #session.apptTypeModList}"> 
							<s:select list="%{#session.apptTypeModList}" listKey="value" listValue="label" name="apptTypeId" size="5" > </s:select>--%>
							<%-- </c:if> --%>
							<%-- <c:if test="%{empty #session.apptTypeModList}"> --%>
							<%-- <s:select  list="%{{}}"  listKey="value" listValue="label" name="apptTypeId" size="5" > </s:select> --%>
							<%-- </c:if> --%>
					<%-- </s:if> --%>
					<%-- <s:else >
						<select  name="apptTypeId" size="5" >
	
						</select>
					</s:else>  --%>
					
					
						
					</div>
					<div class="div-table-col column  width5">
						<img src="/HISRegistration/hisglobal/images/forward3.gif" class="link"
							 onClick='moveRightSingle("apptTypeId","apptTypeIdSel")';/>
						<img src="/HISRegistration/hisglobal/images/back3.gif" class="link"
							 onClick='moveLeftSingle("apptTypeIdSel","apptTypeId")';/>
					</div>
					<div class="div-table-col column  width10">
						<s:select 
				 		 list="%{#session.apptTypeSelModList}" listKey="value" listValue="label" name="apptTypeIdSel" size="5" > </s:select>
					</div>
					
					
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:text name="global.default"/>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="global.type"/> :
					</div>
					<div class="div-table-col column  width45">
						<s:select list="%{#session.apptTypeDefModList}" listKey="value" listValue="label" name="defaultApptTypeId" > </s:select>
					</div>
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="duration"/>:
					</div>
					<div class="div-table-col column  width10">
						<s:select 
				 		 list="%{#session.apptDurationList}" listKey="value" listValue="label" name="apptDurationValue" onchange="setDurationUnit_onchange()"> </s:select>
					</div>
										
				</div>
				
				<div class="div-table-row " style="display: none;">
					<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="transferrable"/> :
					</div>
					<div class="div-table-col   width45">
						<s:radio  name="isApptTransferable" list="#{'1':'Yes','2':'No'}"  />

					</div>
				</div>
				
				<div class="div-table-row " style="display: none;">
					<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="accept"/>&nbsp;<s:text name="transferrable"/>&nbsp;<s:text name="Apt_appointment"/> :
					</div>
					<div class="div-table-col   width45">
						<s:radio  name="acceptTransferedAppt" list="#{'1':'Yes','2':'No'}"  />

					</div>
				</div>
				
				<div class="div-table-row " style="display: none;">
					<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="quota"/>&nbsp;<s:text name="shiftwise"/>:
					</div>
					<div class="div-table-col   width45">
						<s:radio  name="isQuotaShiftWise" list="#{'1':'Yes','2':'No'}"  />

					</div>
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="prior"/>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="days"/>:
					</div>
					<div class="div-table-col" style="width: 10%;">
						<s:textfield name="priorApptDays" size='12' maxlength="3"  cssStyle="min-width:4px;" onkeypress="return isNumberKey(event)">
						</s:textfield>
					</div>
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col title width100 "><s:text name="Apt_appointment"/>&nbsp;<s:text name="global.shift"/>:</div>
				</div>
				
				</div>
				
				
				<div class="div-table-listing rounded" id="AppointmentShift">
				
				<s:if test="appointmentForId==1">
				
				<div class='div-table-row listHeader'>
					<div class='div-table-col' style='width: 10%;'>
						<b><s:text name="global.shift"/></b>
					</div>
					<div class='div-table-col' style='width: 15%;'>
						<b><s:text name="time"/></b>
					</div>
					<div class='div-table-col' style='width: 10%;'><s:text name="roster.weekOfMonth"/></div>
					<div class='div-table-col' style='width: 15%;'>
						<b><s:text name="roster.daysOfWeeek"/>  </b>
					</div>
					<div class='div-table-col' style='width: 9%;word-wrap:break-word;'>
						<b><s:text name="current"/>&nbsp;<s:text name="date"/><br><s:text name="aptShort"/></b>
					</div>
					<div class='div-table-col' style='width: 8%;'>
						<b><s:text name="prior"/>&nbsp;<s:text name="aptShort"/></b>
					</div>
					<%-- <div class='div-table-col' style='width: 8%;'>
						<b><s:text name="overbook"/></b>
					</div> --%>
					<div class='div-table-col' style='width: 10%;'>
						<b><s:text name="portal"/>&nbsp;<s:text name="aptShort"/> </b>
					</div>
					<!--  -->
					<div class="div-table-col" style="width: 5%;">
						<b><s:text name="OPD"/></b> 
					</div>
					<div class="div-table-col" style="width: 5%;">
						<b><s:text name="IPD"/></b> 
					</div>
					<div class='div-table-col' style='width: 8%;'>
						<b><s:text name="EMG"/></b>
					</div>
					<!--  -->
					<div class='div-table-col' style='width: 10%;display:none;'>
						<b><s:text name="vip"/>&nbsp;<s:text name="Apt_slot"/>&nbsp;<s:text name="allowed"/></b>
					</div>
				</div>
				</s:if>	
				<s:else>
					<div class="div-table-row listHeader" >
						<div class="div-table-col" style="width: 10%;">
						<b><s:text name="global.shift"/></b>
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b><s:text name="shift.start"/>(24hh:mi)</b> 
						</div>
						<div class="div-table-col" style="width: 10%;">
						<b><s:text name="shift.end"/>(24hh:mi)</b> 
						</div>
						<div class="div-table-col" style="width: 25%;">
						<b><s:text name="roster.daysOfWeeek"/></b> 
						</div>
						<div class="div-table-col" style="width: 9%;">
						<b><s:text name="current"/>&nbsp;<s:text name="date"/> </b> 
						</div>
						<div class="div-table-col" style="width: 8%;word-wrap:break-word;">
						<b><s:text name="number"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="prior"/><br><s:text name="aptShort"/></b> 
						</div>
						<%-- <div class="div-table-col" style="width: 8%;">
						<b><s:text name="overbook"/></b> 
						</div> --%>
						<div class="div-table-col" style="width: 5%;">
						<b><s:text name="portal"/></b> 
						</div>
						<!--  -->
						<div class="div-table-col" style="width: 5%;">
							<b><s:text name="OPD"/></b> 
						</div>
						<div class="div-table-col" style="width: 5%;">
							<b><s:text name="IPD"/></b> 
						</div>
						<div class="div-table-col" style="width: 8%;">
						<b><s:text name="EMG"/></b> 
						</div>
						<!--  -->
						<div class="div-table-col" style="width: 10%;display:none;">
						<b><s:text name="vip"/>&nbsp;<s:text name="Apt_slot"/>&nbsp;<s:text name="allowed"/> </b> 
						</div>
						<div class="div-table-col   width5" >
						<img src="/HISRegistration/hisglobal/images/plus.png" id='ADDROW' >
						</div>
					</div>
				</s:else>
				
				<%int i=0; %>
				<c:forEach items="${multiRowMap}" var="count">
				<div class="div-table-row listData multirow" id="TR_SHIFT_NEW_<%=i%>">
				<s:if test="appointmentForId==1">
				<s:set name="voShift" value="count.value"></s:set>
				<div class='div-table-col' style='width: 10%;'>
				<input type='hidden'  name="shiftId"  id="SHIFTID_<%=i%>"  value="${count.value.shiftId}" >
				<input type='hidden'  name="shiftName"  id="SHIFTNAMEID_<%=i%>"  value="${count.value.shiftName}" >
					${count.value.shiftName}
				</div>
				<div class='div-table-col' style='width: 15%;'>
					${count.value.startTime}-${count.value.endTime}
					<input type='hidden' name='startTime' value="${count.value.startTime}">
					<input	type='hidden' name='endTime' value=  "${count.value.endTime}" >
				</div>
				<div class='div-table-col' style='width: 10%;'>
					<input type='hidden' name='weekOfMonth' value="${count.value.weekOfMonth}">
					${count.value.weekOfMonth}					
				</div>
				<div class='div-table-col' style='width: 15%;'>
					<input type='checkbox' name="chk_<%=i%>"   style="display: none;" value="1"  />
						<input type='checkbox' name="chk_<%=i%>" style="display: none;" value="2"/>
						<input type='checkbox' name="chk_<%=i%>" style="display: none;" value="3"/>
						<input type='checkbox' name="chk_<%=i%>" style="display: none;" value="4" />
						<input type='checkbox' name="chk_<%=i%>" style="display: none;" value="5" />
						<input type='checkbox' name="chk_<%=i%>" style="display: none;" value="6" />
						<input type='checkbox' name="chk_<%=i%>" style="display: none;" value="7" />
					<input type='hidden' name='shiftwiseSelectedDays' id='SHIFTWISESELECTEDDAYS_<%=i%>' value="${count.value.shiftwiseSelectedDays}" />
					${count.value.daystr}					
			  </div>
			  <div class='div-table-col' style='width: 9%;'>
			  	<input type="text" name="currentDateAppt" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.currentDateAppt}" onkeypress="return isNumberKey(event)">
			   </div> 
				<div class='div-table-col' style='width: 8%;'>
					<input type="text" name="priorAppt" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.priorAppt}" onkeypress="return isNumberKey(event)">			
				</div>
				<%-- <div class='div-table-col' style='width: 8%;'>
					<input type="text" name="overBook" size='4' maxlength="4"  style="min-width:4px;" value="${count.value.overBook}" onkeypress="return isNumberKey(event)">
				</div> --%>
				<div class='div-table-col' style='width: 10%;'>
					<input type="text" name="portal" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.portal}" onkeypress="return isNumberKey(event)">
				</div>
				<!--  -->
				<div class="div-table-col" style="width: 5%;">
					<input name="opdApptSlots" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.opdApptSlots}" >
				</div>
				<div class="div-table-col" style="width: 5%;">
					<input name="ipdApptSlots" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.ipdApptSlots}" onkeypress="return isNumberKey(event)">
				</div>
				<div class='div-table-col' style='width: 8%;'>
					<input type="text" name="overBook" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.overBook}" onkeypress="return isNumberKey(event)">
				</div>
				<!--  -->
				<div class='div-table-col ' style='width: 10%;display:none;'>
					<c:if test="${count.value.isVipSlotAllowed == 1}">
		  				<input type="radio" name="rdo_<%=i%>"  value="1" checked="checked" >Yes
		  				<input type="radio" name="rdo_<%=i%>"  value="2" >No
					</c:if>
					<c:if test="${count.value.isVipSlotAllowed == 2}">
		  				<input type="radio" name="rdo_<%=i%>"  value="1"  >Yes
		  				<input type="radio" name="rdo_<%=i%>"  value="2" checked="checked">No
					</c:if>
					<input type='hidden' name="isVipSlotAllowed"  id="SHIFTWISESELECTEDRADIO_<%=i%>" />
				</div>
			</s:if>
			<s:else>
					<div class="div-table-col" style="width: 10%;">
						<input type='hidden' name="shiftIdHidden"  id="SHIFTWISESELECTEDID_<%=i%>" value="${count.value.shiftId}" />
						
						<select   name="shiftId"  id="SHIFTID_<%=i%>" style="width:90%;" > 
						<c:forEach items="${apptShiftList}" var="optCount">
							<option value="${optCount.value}">${optCount.label}</option>
						</c:forEach>
						</select>
						
					</div>
					<div class="div-table-col" style="width: 10%;">
						&nbsp;<input type="text" name="startTime" size='4' maxlength="5"  style="min-width:4px;" value="${count.value.startTime}"  onkeyup="insertColons(this)" id="STARTID_<%=i%>">
					</div>
					<div class="div-table-col" style="width: 10%;">
							<input type="text" name="endTime" size='4' maxlength="5"  style="min-width:4px;" value="${count.value.endTime}" onkeyup="insertColons(this)" id="ENDID_<%=i%>">
					</div>
					<div class="div-table-col" style="width: 25%;">
						<input type='hidden' name='weekOfMonth' value="${count.value.weekOfMonth}">
						<input type='hidden' name="shiftwiseSelectedDays"  id="SHIFTWISESELECTEDDAYS_<%=i%>" value="${count.value.shiftwiseSelectedDays}" />
						 
						<input type='checkbox' name="chk_<%=i%>"  value="1"  />Sun
						<input type='checkbox' name="chk_<%=i%>" value="2"/>Mon
						<input type='checkbox' name="chk_<%=i%>" value="3"/>Tue
						<input type='checkbox' name="chk_<%=i%>" value="4" />Wed
						<input type='checkbox' name="chk_<%=i%>" value="5" />Thu
						<input type='checkbox' name="chk_<%=i%>" value="6" />Fri
						<input type='checkbox' name="chk_<%=i%>" value="7" />Sat
					</div>
					<div class="div-table-col" style="width: 9%;">
							<input type="text" name="currentDateAppt" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.currentDateAppt}" onkeypress="return isNumberKey(event)">
						
					</div>
					<div class="div-table-col" style="width: 8%;">
							<input type="text" name="priorAppt" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.priorAppt}" onkeypress="return isNumberKey(event)">
					</div>
					<%-- <div class="div-table-col" style="width: 8%;">
							<input type="text" name="overBook" size='4' maxlength="4"  style="min-width:4px;" value="${count.value.overBook}" onkeypress="return isNumberKey(event)">
					</div> --%>
					<div class="div-table-col" style="width: 5%;">
							<input type="text" name="portal" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.portal}" onkeypress="return isNumberKey(event)">
					</div>
					<!--  -->
					<div class="div-table-col" style="width: 5%;">
						<input name="opdApptSlots" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.opdApptSlots}" >
					</div>
					<div class="div-table-col" style="width: 5%;">
						<input name="ipdApptSlots" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.ipdApptSlots}" onkeypress="return isNumberKey(event)">
					</div>
					<div class="div-table-col" style="width: 8%;">
							<input type="text" name="overBook" size='3' maxlength="3"  style="min-width:4px;" value="${count.value.overBook}" onkeypress="return isNumberKey(event)">
					</div>
					<!--  -->
					<div class="div-table-col " style="display: none;">
							<c:if test="${count.value.isVipSlotAllowed == 1}">
  								<input type="radio" name="rdo_<%=i%>"  value="1" checked="checked" >Yes
  								<input type="radio" name="rdo_<%=i%>"  value="2" >No
							</c:if>
							<c:if test="${count.value.isVipSlotAllowed == 2}">
  								<input type="radio" name="rdo_<%=i%>"  value="1"  >Yes
  								<input type="radio" name="rdo_<%=i%>"  value="2" checked="checked">No
							</c:if>
						<input type='hidden' name="isVipSlotAllowed"  id="SHIFTWISESELECTEDRADIO_<%=i%>" />
					</div>
					<div class="div-table-col   width5">
						<img src='/HISRegistration/hisglobal/images/Minus.png' onclick="DeleteRows('TR_SHIFT_NEW_<%=i%>')" >
					</div>
			</s:else>
			</div>
				
			<%i++; %>
			 </c:forEach>
			
			</div>	
			 
				<div class="div-table-row " style="display: none">
					<div class="div-table-col column  width10">
						<s:select 
 				 		 list="%{{}}" listKey="value" listValue="label" name="tempShiftId" size="5" ></s:select> 
					</div>
				</div>
						
				<div class="div-table-row ">
					<div class="div-table-col  width100 height20 "></div>
				</div>
			


			<div class="div-table-button">
				<div class="div-table-row" align='center'>
					
						<a href="#" class="button" onclick="return submitSaveAction();"><span
							class="save"><s:text name="save"/></span></a>
						<a href="#" class="button" onclick="return submitCancelAction();"><span
							class="cancel"><s:text name="cancel"/></span></a>
					
				</div>
			</div>

		<s:hidden name="flagToAddMod" value="%{flagToAddMod}"></s:hidden>
		
		<div class="div-table-col" style="width: 10%;display:none;" id="hiddenDivForShifts" >
						<s:select id='hiddenSelForShifts'
				 		 list="%{#session.apptShiftList}" listKey="value" listValue="label"   > </s:select> 
		</div>
		<s:hidden name="apptDurationUnit" ></s:hidden>
		<s:hidden name="apptDurationUnitTime" ></s:hidden>
		<s:hidden name="selApptTypesText" ></s:hidden>
		<s:hidden name="selApptTypesValues" ></s:hidden>
		<s:hidden name="appointmentForId" ></s:hidden>
<%--  		<s:hidden name="apptDurationValue" ></s:hidden>  --%>
		<s:hidden name="paraRefId" ></s:hidden>
		
		<cmbPers:cmbPers></cmbPers:cmbPers>
		<s:token></s:token>
	</s:form></div>
	<h3>
		<s:property value="message" />
	</h3>
	
</body>
</html>