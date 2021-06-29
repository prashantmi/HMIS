<%System.out.println("m here"); %>
<%@page import="hisglobal.hisconfig.Config,hisglobal.presentation.Status"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>

<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link href="../../hisglobal/css/jquery.timeentry.css" rel="stylesheet">
<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>
<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>

<script type="text/javascript" src="./../../appointment/transactions/js/NewAppointment.js"></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>


<script src="../../hisglobal/js/jquery.plugin.js"></script>
<script src="../../hisglobal/js/jquery.timeentry.js"></script>


<title>New Appointment</title>
<script>
$(document).ready(function() {
	$('[name="patientType"]').click(changePatientType);
	$('#appointmentTime').timeEntry({ampmPrefix: ' '});
	var tType="";
	var sysDate=new Date();
	var hr=sysDate.getHours();
	var min=sysDate.getMinutes();
	var _upCurTime,_naptTime="";
	if(hr>12){
		tType="PM";
		if(hr!=12)hr=hr-12;
	}
	else
		tType="AM";
	
	var curTime=hr+":"+min+" "+tType;
	
	$( "#appointmentDate" ).datepicker({
				changeMonth : true,
				changeYear : true,
				dateFormat:"dd-M-yy",
				onSelect: function(d,i){
			          if(d !== i.lastVal){
			              $(this).change();
			          }
			     }
	}).datepicker("setDate", "0");
	$( "#appointmentTime" ).val(curTime);
	$("#appointmentDate").validatebox({required: true, validType: ['dtgtetctdt[\'Appointment Date Must Not be lesser than Today\']']});
	document.getElementsByName("patientType")[0].checked=true;
	changePatientType();
	validateFields();
	$('#submitId').click(function(){
		if(undefined!=document.getElementsByName("parameterId")[0]){
		var parameterId =document.getElementsByName("parameterId")[0].value;
		var concatId= '_'+parameterId;
		var departmentUnit = "#ACTUALPARAMETERID"+concatId;
		var UnitName = $(departmentUnit).find(":selected").text();
		document.getElementsByName("departmentUnitName")[0].value=UnitName;
		//document.getElementsByName("afterGo")[0]
		}
		
		
		if($('#aptTagRow').css('display')!="none")
		{
			$('[name="appointmentTime"]').validatebox({
				required:false			
			});
			$( "#appointmentTime" ).val($('#freeSlotTime').html());
		}
		else
		{
			/* $('[name="appointmentTime"]').validatebox({
				required:true			
			}); 
			_upCurTime=$( "#appointmentTime" ).val().split(":");
			if(_upCurTime[1].split(" ")[1]=="PM"){
				_upCurTime[0]=parseInt(_upCurTime[0])+12;
			}
			_naptTime=_upCurTime[0]+":"+_upCurTime[1].split(" ")[0];
			$( "#appointmentTime" ).val(_naptTime);*/

		}
		
		var isValid = $('#NewAppointment').form('validate');
		if(!$('#aptTagRow').is(':visible')){
			alert("Please select the Slot !");
			isValid=false;
			$("#appointmentDate").focus();
		}
	    if(isValid==false)
	      return false;
	    
	    document.forms[0].action =  "SaveNewAppointment.action";
		document.getElementsByName("afterGo")[0].value="0";
		document.forms[0].submit();
	    
	});
	
	return false;
});

	
</script>

</head>

<body>
<center>
<s:actionerror/>

<s:form action="NewAppointment">
	
	<br>
    <div class="wrapper rounded" style="width:95%" >
	    <div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="new"/>&nbsp;<s:text name="Apt_appointment"/>  
				</div>
			</div>
			
			<div class="div-table-row " id="div_patientType">
				<div class="div-table-col label" style="width: 25%;">
					<s:text name="global.patient"/>&nbsp;<s:text name="type"/>   
				</div>
				<div class="div-table-col control" style="width: 75%;">
					<input type="radio"  name="patientType"  value='1' tabindex="1" /><s:text name="registered"/>   
					<input type="radio"  name="patientType"  value='2' tabindex="1" /> <s:text name="unregistered"/>  
				</div>
			</div>
			<%String display="none"; %>
			<div class="div-table-row " id='divpatient_1'>
				<div class="div-table-col label width100 " >
					<s:if test="afterGo==0">
						<his:InputCrNoTag />
					</s:if>	
					<s:if test="afterGo==1">
						<s:action name="patientDetail"  executeResult="true"/>
						<%display=""; %>
					</s:if>	
				</div>
			</div>
			
			<div class="div-table-row " id='divpatient_2' style="display: <%=display%>">
				<div class="div-table-col width100 ">
					<div class="div-table">
						<div class="div-table-row" >
							<div class="div-table-col label" style="width: 25%;"><s:text name="global.patient"/>&nbsp;<s:text name="global.name"/>:</div>
							<div class="div-table-col " style="width: 25%;;">&nbsp;<font color="red">*</font><s:text name="first"/></div>
							<div class="div-table-col " style="width: 25%;">&nbsp;<s:text name="middle"/></div>
							<div class="div-table-col " style="width: 25%;">&nbsp;<s:text name="last"/></div>
						</div>
						<div class="div-table-row">
							<div class="div-table-col" style="width: 25%;"></div>
							<div class="div-table-col control " style="width: 25%;">
								<input name="patFirstName" id="patFirstName"  tabindex="1"  maxlength="33" type="text">
							</div>
							<div class="div-table-col control" style="width: 25%;">
								<input name="patMiddleName"  tabindex="2" maxlength="33" type="text">
							</div>
							<div class="div-table-col control" style="width: 25%;">
								<input name="patLastName"  tabindex="2"  maxlength="33" type="text">
							</div>
						</div>
						<div class="div-table-row">
				    		<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="age"/></div>
							<div class="div-table-col control" style="width: 25%">
							       <input id="patAge" name="patAge" type="text"  tabindex="1"  size="3" maxlength="3" onblur="showDivAge();" >
							       <s:if test="%{#session.LSTAGETYPE}">
										<s:select list="%{#session.LSTAGETYPE}" listKey="value" listValue="label" name="patAgeUnit" cssStyle="width :75px"  > </s:select>
									</s:if>
									<s:else>
										<select  name="patAgeUnit" id="patAgeUnit"  tabindex="1"  style="width :75px">
										<option value="-1">Select</option>
										</select>
									</s:else>
							</div>
							<div class="div-table-col label" style="width: 25%;" ><font color="red">*</font><s:text name="gender"/> </div>
							<div class="div-table-col control" style="width: 25%;">
								<s:if test="%{#session.LSTGENDER}">
									<s:select 	list="%{#session.LSTGENDER}"  tabindex="1"  listKey="value" listValue="label" name="patGenderCode" cssStyle="width:75px;" > </s:select>
								</s:if>
								<s:else >
									<select  name="patGenderCode"  tabindex="1" style="width :75px">
										<option value="-1">Select</option>
									</select>
								</s:else>
							</div>	
						</div>
						<div class="div-table-row">
				    		<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="fathersName"/> </div>
							<div class="div-table-col control" style="width: 25%;">
							       <input name="patGuardianName" id="patGuardianName"  tabindex="1" maxlength="60" type="text">
							</div>
							<div class="div-table-col label" style="width: 25%;" ><s:text name="husbandName"/> </div>
							<div class="div-table-col control" style="width: 25%;">
							   <input name="patHusbandName"  tabindex="2" maxlength="60" type="text">
							</div>	
						</div>					
					</div>				    
				</div>
			</div>
		</div>
		
		<div id='div_appointment'  style="display:none;">
			<div class="div-table">		
			<div class="div-table-row ">
					<div class="width100" align="center"><hr width="95%" color="#115887">
					</div>
				</div>
			</div>
			<appt:AppointmentParameterComboTag  tagView="TRANSACTION" controllerName="NewAppointment"   scriptCallBackFunctionName="getActualParaIdWiseEssensials"/>
			<div class="div-table">
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="Apt_appointment"/>&nbsp;<s:text name="date"/></div>
					<div class="div-table-col control" style="width: 25%;">
					       
					      <input name="appointmentDate"  tabindex="1" id="appointmentDate" maxlength="10" type="text" style="width: 75px;" onchange="getSlotDetails()">   
					      <span id="aptTime1" style="display:none"><input name="appointmentTime"  tabindex="1" id="appointmentTime" maxlength="8" type="text" style="width: 75px;"></span>   
					</div>
					<div class="div-table-col label" style="width: 25%" ><s:text name="Apt_appointment"/>&nbsp;<s:text name="global.type"/></div>
					<div class="div-table-col control" style="width: 25%;">
					   <select name="appointmentTypeId"  tabindex="2" id="appointmentTypeId" style="width :145px">
							<option value="-1">Select Value</option>
						</select>
					</div>	
				</div>
				<div class="div-table-row " id="aptTagRow" style="display:none;" align="left">
					<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="Free Slot"/>&nbsp;<s:text name="date"/>&nbsp;<s:text name="and"/>&nbsp;<s:text name="time"/></div>
					<div id="aptTagId" class="div-table-col control" style="width: 25%;"></div>
					<div class="div-table-col label" style="width: 25%" ></div>
					<div class="div-table-col control" style="width: 25%;"></div>
				</div>
				
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="mobileNo"/></div>
					<div class="div-table-col control" style="width: 25%;">
					   <input name="mobileNo"  tabindex="1" maxlength="12" type="text" onblur="showMobileNo();">    
					</div>
					<div class="div-table-col label" style="width: 25%" ><s:text name="global.email"/></div>
					<div class="div-table-col control" style="width: 25%;">
					   <input name="emailId"  tabindex="2"  maxlength="50" type="text" onblur="showEmailId();">
					</div>	
				</div>			
				<div class="div-table-row ">
					<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="Apt_appointment"/>&nbsp;<s:text name="mode"/> </div>
					<div class="div-table-col control" style="width: 25%;">
					   <s:if test="%{#session.LSTAPPOINTMENTMODE}">
									<s:select 	list="%{#session.LSTAPPOINTMENTMODE}" listKey="value"  tabindex="1" listValue="label" name="appointmentMode" > </s:select>
								</s:if>
								<s:else >
									<select  name="appointmentMode"  tabindex="1" >
										<option value="-1">Select</option>
									</select>
								</s:else> 
					</div>
					<div class="div-table-col label" style="width: 25%" ><s:text name="global.remarks"/></div>
					<div class="div-table-col control" style="width: 25%;">
					   <textarea rows="2"   tabindex="2" cols="20" name="remarks"></textarea>
					</div>	
				</div>						
			</div>	
			<div class="div-table-button">
				<div class="div-table-row footerBar">
						<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row emptyBar">
					<div class="div-table-col"> </div>
				</div>
				<div class="div-table-row" align="center">
					<a href="#"  tabindex="1" class="button" id="submitId"><span class="save"><s:text name="save"/></span></a>
					<a href="#"  tabindex="1" class="button" id="clearId" onclick="clearFormFields();"><span class="clear"><s:text name="clear"/></span></a>
					<a href="#"  tabindex="1" class="button" id="cancelId" onclick="callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a>
				</div>				
			</div>
		</div>
		 
	</div>
	
<s:hidden name="afterGo"  />
<s:hidden name="departmentUnitName"  />
		
</s:form>
</center>
<his:status />
<div id='loadingmessage' style='display:none'>
       <img src='/HISRegistration/hisglobal/images/ajax-loader.gif'/>
</div>

</body>
</html>