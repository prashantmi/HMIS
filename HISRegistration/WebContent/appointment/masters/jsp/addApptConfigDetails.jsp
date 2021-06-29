<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Created By 	: Neha Sharma 
Dated			    : 10-Feb-2014 -->

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
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



//to set duration unit in hidden field..
function setDurationUnit()
{
	var tempEle=document.getElementsByName('apptDurationValue')[0];
	var optVal=tempEle.options[0].text.split('-')[1];	
	var optValTime=tempEle.options[0].text.split('-')[0];
	document.getElementsByName('apptDurationUnit')[0].value=optVal;
	document.getElementsByName('apptDurationUnitTime')[0].value=optValTime;
	
}
//to set duration unit in hidden field onChange..
function setDurationUnit_onchange(){
	var tempEleObj=$('[name="apptDurationValue"]')[0];
	//alert(tempEleObj.options[tempEleObj.selectedIndex].text);
	var optVal=tempEleObj.options[tempEleObj.selectedIndex].text.split('-')[1];	
	var optValTime=tempEleObj.options[tempEleObj.selectedIndex].text.split('-')[0];
	document.getElementsByName('apptDurationUnit')[0].value=optVal;
	document.getElementsByName('apptDurationUnitTime')[0].value=optValTime;
}
function submitSaveAction(cnt)
{
	var isValid = $('#ApptConfigMst').form('validate');
    if(isValid==false)
      return false;
	
    // to save selAppType labels and values..
    getSelApptType();
	
	if((document.getElementById("appointmentDtl").value == -1))
	{
		alert("Kindly select Appointment For.");
		return;
	}
	
	for(var k=0;k<document.getElementsByName("actualParameterId").length;k++)
	{
		if(document.getElementsByName("actualParameterId")[k].value == -1)
		{
			alert("Kindly make required selction from the dropdown.");	
			return;
		}
		
		
	}
	
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
		if(document.getElementsByName("rdo_" + uniqueId)[0].checked)
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
		}
		
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
	//alert($('[name="opdApptSlots"]').val()+"\n"+$('[name="ipdApptSlots"]').val());
	//alert("validations passed..");
	document.forms[0].action = "saveApptConfigMst.action";
	document.forms[0].submit();
}

	
</script>
</head>


<body onload="setDurationUnit();">
<div class="wrapper rounded">
	<s:form action="ApptConfigMst">	
			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col  width100 "><s:text name="Apt_appointment"/>&nbsp;<s:text name="config"/>&nbsp;<s:text name="global.master"/></div>
				</div>

				<div class="div-table-row ">
					<div class="div-table-col title width100 ">
							<s:text name="global.add"/>&nbsp;<s:text name="config"/>&nbsp;<s:text name="global.details"/>  
					</div>
				</div>
			 	<appt:AppointmentParameterComboTag tagView="MASTER" controllerName="ApptConfigMst" scriptCallBackFunctionName="getOPDRosterSchedule"  />
			  	<div class="div-table-row ">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="type"/>:
					</div>
					<div class="div-table-col column  width10">
						<s:select 
				 		 list="%{#session.apptTypeList}" listKey="value" listValue="label" name="apptTypeId" size="5" > </s:select>
					</div>
					<div class="div-table-col column  width5">
						<img src="/HISRegistration/hisglobal/images/forward3.gif" class="link"
							 onClick='moveRightSingle("apptTypeId","apptTypeIdSel")';/>
						<img src="/HISRegistration/hisglobal/images/back3.gif" class="link"
							 onClick='moveLeftSingle("apptTypeIdSel","apptTypeId")';/>
					</div>
					<div class="div-table-col column  width10">
						<s:select 
				 		 list="%{{}}"  name="apptTypeIdSel" size="5" > </s:select>
					</div>
					
					
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label  width50">
						<font color="#FF0000">*</font>&nbsp;<s:text name="global.default"/>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="type"/>:
					</div>
					<div class="div-table-col column  width45">
						<s:select list="%{{}}" name="defaultApptTypeId" > </s:select>
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
					<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="global.is"/>&nbsp;<s:text name="Apt_appointment"/>&nbsp;<s:text name="transferrable"/>:
					</div>
					<div class="div-table-col   width45">
						<s:radio  name="isApptTransferable" list="#{'1':'Yes','2':'No'}"  />

					</div>
				</div>
				
				<div class="div-table-row " style="display: none;">
					<div class="div-table-col label  width50"><font color="#FF0000">*</font>&nbsp;<s:text name="accept"/>&nbsp;<s:text name="transferrable"/>&nbsp;<s:text name="Apt_appointment"/>:
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
				
				
								
				
				<div class="div-table-row " id="AppointmentShiftHeader" style='display:none;'>
					<div class="div-table-col title width100 "><s:text name="Apt_appointment"/>&nbsp;<s:text name="global.shift"/>:</div>
				</div>
				
				</div>
				<div class="div-table-listing rounded" id="AppointmentShift" style='display:none;'>
				<div class="div-table-row listHeader" >
					<div class="div-table-col" style="width: 10%;">
					<b><s:text name="global.shift"/></b>
					</div>
					<div class="div-table-col" style="width: 10%;">
					<b><s:text name="shift.start"/>(24hh:mi)</b> 
					</div>
					<div class="div-table-col" style="width: 10%;">
					<b><s:text name="shift.end"/> (24hh:mi)</b> 
					</div>
					<div class="div-table-col" style="width: 25%;">
					<b><s:text name="roster.daysOfWeeek"/></b> 
					</div>
					<div class="div-table-col" style="width: 9%;">
					<b><s:text name="current"/>&nbsp;<s:text name="date"/> </b> 
					</div>
					<div class="div-table-col" style="width: 8%;">
					<b><s:text name="number"/>&nbsp;<s:text name="of"/>&nbsp;<s:text name="prior"/>&nbsp;<s:text name="Apt_appointment"/></b> 
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
					<div class="div-table-col" style="width: 5%;display: none;">
					<b><s:text name="vip"/>&nbsp;<s:text name="Apt_slot"/>&nbsp;<s:text name="allowed"/> </b> 
					</div>
					<div class="div-table-col   width5" >
					<img src="/HISRegistration/hisglobal/images/plus.png" id='ADDROW' >
					</div>
				</div>
				
				<div class="div-table-row listData multirow" id="TR_SHIFT_NEW_1">				
					<div class="div-table-col" style="width: 10%;">
						<input type='hidden' name='weekOfMonth'  value='0' >
						<s:select 
				 		 list="%{#session.apptShiftList}" listKey="value" listValue="label"  name="shiftId"  id="SHIFTID_1" cssStyle="width:15px;max-width:75px;"> </s:select>
					</div>
					<div class="div-table-col" style="width: 10%;">
						&nbsp;<s:textfield name="startTime" size='4' maxlength="5"  cssStyle="min-width:4px;"  onkeyup="insertColons(this)">
						</s:textfield>
					</div>
					<div class="div-table-col" style="width: 10%;">
						<s:textfield name="endTime" size='4' maxlength="5"  cssStyle="min-width:4px;" onkeyup="insertColons(this)">
						</s:textfield>
					</div>
					<div class="div-table-col" style="width: 25%;">
						<s:hidden name="shiftwiseSelectedDays"  id="SHIFTWISESELECTEDDAYS_1"></s:hidden>
						<input type='checkbox' name="chk_1"  value="1"  checked/>Sun
						<input type='checkbox' name="chk_1" value="2" checked/>Mon
						<input type='checkbox' name="chk_1" value="3" checked/>Tue
						<input type='checkbox' name="chk_1" value="4" checked/>Wed
						<input type='checkbox' name="chk_1" value="5" checked/>Thu
						<input type='checkbox' name="chk_1" value="6"  checked/>Fri
						<input type='checkbox' name="chk_1" value="7"  checked/>Sat
					</div>
					<div class="div-table-col" style="width: 9%;">
						 <s:textfield name="currentDateAppt" size="3" maxlength="3" cssStyle="min-width:4px;" onkeypress="return isNumberKey(event)">
						</s:textfield> 
					</div> 
					<div class="div-table-col" style="width: 8%;">
						<s:textfield name="priorAppt" size="3" maxlength="3"  cssStyle="min-width:4px;" onkeypress="return isNumberKey(event)">
						</s:textfield>
					</div>
					<%-- <div class="div-table-col" style="width: 8%;">
						<s:textfield name="overBook" size='4' maxlength="4"  cssStyle="min-width:4px;" onkeypress="return isNumberKey(event)">
						</s:textfield>
					</div> --%>
					<div class="div-table-col" style="width: 5%;">
						<s:textfield name="portal" size='3' maxlength="3"  cssStyle="min-width:4px;" onkeypress="return isNumberKey(event)">
						</s:textfield>
					</div>
					<!--  -->
					<div class="div-table-col" style="width: 5%;">
						<s:textfield name="opdApptSlots" size='3' maxlength="3"  cssStyle="min-width:4px;" >
						</s:textfield>
					</div>
					<div class="div-table-col" style="width: 5%;">
						<s:textfield name="ipdApptSlots" size='3' maxlength="3"  cssStyle="min-width:4px;" >
						</s:textfield>
					</div>
					<div class="div-table-col" style="width: 8%;">
						<s:textfield name="overBook" size='3' maxlength="3"  cssStyle="min-width:4px;" onkeypress="return isNumberKey(event)">
						</s:textfield>
					</div>
					<!--  -->
					<div class="div-table-col " style="width: 10%;display: none;">
						<s:radio  name="rdo_1" list="#{'1':'Yes','2':'No'}"  value="1"/>
						<s:hidden name="isVipSlotAllowed"  id="SHIFTWISESELECTEDRADIO_1"></s:hidden>
					</div>
					<div class="div-table-col   width5" align="center">
					<img src='/HISRegistration/hisglobal/images/Minus.png' onclick="DeleteRows('TR_SHIFT_NEW_1')" >
					</div>
				</div>
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
		<s:hidden name="selApptTypesText" ></s:hidden>
		<s:hidden name="selApptTypesValues" ></s:hidden>
		<s:hidden name="apptDurationUnitTime" ></s:hidden>
		<cmbPers:cmbPers></cmbPers:cmbPers>
	<s:token></s:token>	
	</s:form></div>
	<h3>
		<s:property value="message" />
	</h3>
	
</body>
</html>