<%System.out.println("Reschedule Cancel Appointment jsp"); %>
<%@page import="hisglobal.hisconfig.Config,hisglobal.presentation.Status"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/datePickerDefaultSetting.js"></script>

<script language="JavaScript" src="./../../hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript" src="../../hisglobal/js/dateFunctions.js"></script>

<script type="text/javascript" src="./../../appointment/transactions/js/RescheduleCancelAppointment.js"></script>
<script type="text/javascript" src="/HISInvestigationG5/new_investigation/js/appointment.js"></script>

<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>

<script src="../../hisglobal/js/jquery.plugin.js"></script>
<script src="../../hisglobal/js/jquery.timeentry.js"></script>

<title>Reschedule Cancel Appointment</title>
<script>

$(document).ready(function() {
	$('[name="patientType"]').click(changePatientType);
	$('[name="appointmentDate"]').change(getReSchSlotDetails);	
	$('#appointmentTime').timeEntry();
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
	document.getElementsByName("patientType")[0].checked=true;
	changePatientType();
	$('[name="_goappointmentNo"]').val('');
	//validateRescheduleFields();
	$("#appointmentDate").validatebox({validType: 'dtgtetctdt[\'Appointment Date should not be greater than or equal to Current Date\']'});
	//$('[name="emailId"]').validatebox({	required: true,validType: 'email'});
	//$('[name="remarks"]').validatebox({required: true,validType: 'alphaNumericWithSpaces'});
	
	/* if($('[name="afterGo"]').val()==1)
		alert("Inside 1");
	else if($('[name="afterGo"]').val()==2)
		alert("Inside 2");
	else if($('[name="afterGo"]').val()==0)
		alert("Inside 0");
	else
		alert("Inside bla");	 */
	
	$('#submitId').click(function(){
		validateRescheduleFields();
		$('#appointmentTime').val($('[name="slotST"]').val());	
		var isValid = $('#RescheduleCancelAppointment').form('validate');
		if(!$('#aptTagRow').is(':visible')){
			alert("Please select the Slot !");
			isValid=false;
			$("#appointmentDate").focus();
		}
		if(isValid){
			document.forms[0].action =  "saveRescheduleCancelAppointment.action";
			document.forms[0].submit();
		}
		else
			return false;
   
	});
});

function cancel()
{
	    document.getElementById("RescheduleCancelAppointment").action="cancelRescheduleCancelAppointment.action";
		document.getElementById("RescheduleCancelAppointment").submit();
}

function cancelAppointmentAction()
{
	    document.getElementById("RescheduleCancelAppointment").action="cancelAppointmentRescheduleCancelAppointment.action";
		document.getElementById("RescheduleCancelAppointment").submit();
}


function displayReschedule(){
	getReSchSlotDetails();
	document.getElementById('div1').style.display='';
	$("#cancel").hide();
	$("#Reschedule").hide();
	$("#cancelId").hide();
	$('[name="resheduleRemarks"]').validatebox({required: true,validType: 'alphaNumericWithSpaces'});
	$('[name="remarks"]').validatebox({required: false,validType: null});
	$("#cancelFooterId").hide();	


}

function displayCancel(){
	document.getElementById('div2').style.display='';
	$("#cancel").hide();
	$("#Reschedule").hide();
	$('[name="resheduleRemarks"]').validatebox({required: false,validType: null});
	$('[name="remarks"]').validatebox({required: true,validType: 'alphaNumericWithSpaces'});
	$("#cancelId").hide();
	$("#cancelFooterId").hide();	

}

function check(obj){
	//alert(obj.value);
	var _selAptNo=obj.value;
	var _aptRefIdArr=_selAptNo.split("#");
	$("[name='appointmentNo']").val(_aptRefIdArr[0]);
	$("[name='allActualParameterId']").val(_aptRefIdArr[1]);
	//alert("Apt No --"+obj.value+"--all Actual Parameter--"+$("[name='allActualParameterId']").val());
	$("[name='mobileNo']").val(_aptRefIdArr[2]);
	$("[name='emailId']").val(_aptRefIdArr[3]);
	$("[name='appointmentTypeId']").val(_aptRefIdArr[4]);
	if(_aptRefIdArr[4]==1)
		$("#_divappointmentTypeId").html("Normal");
	else if(_aptRefIdArr[4]==2)
		$("#_divappointmentTypeId").html("Urgent");

	$("#cancelFooterId").hide();	
	$("#RescheduleCancel").show();
}

</script>
</head>
<body >
<center>
<s:actionerror/>
<s:form action="RescheduleCancelAppointment">
	<br>
    <div class="wrapper rounded" style="width:98%" >
	    <div class="div-table">
			<div class="div-table-row ">
				<div class="div-table-col title width100 ">
						<s:text name="reschedule"/>&nbsp;<s:text name="and"/>&nbsp;<s:text name="cancellation"/>&nbsp;<s:text name="Apt_appointment"/>     
				</div>
			</div>
			<div class="div-table-row " id="div_patientType">
				<div class="div-table-col label" style="width: 25%;">
					<s:text name="global.patient"/>&nbsp;<s:text name="type"/></div>
				<div class="div-table-col control" style="width: 75%;">
					<input type="radio"  name="patientType"  value='1' /> <s:text name="crNO"/>&nbsp;<s:text name="wise"/>    
					<input type="radio"  name="patientType"  value='2' /><s:text name="Apt_appointment"/>&nbsp;<s:text name="number"/> &nbsp;<s:text name="wise"/>
				</div>
			</div>
			<%String display="none"; %>
			<div class="div-table-row " id='divpatient_1'>
				<div class="div-table-col label width100 " >
					<s:if test="afterGo==0">
						<his:InputCrNoTag />
					</s:if>	
					<s:if test="afterGo==1">
<%-- 					<s:action name="patientDetail"  executeResult="true"/> --%>
						<his:PatientTileTag crNo="${patCrNo }" ></his:PatientTileTag>
						<%display=""; %>
					</s:if>	
				</div>

			 	<s:if test="%{#session.MAP_PREV_APPT}">
					<div class="div-table-col width100 ">
						<div class="div-table-listing">
					        <div class="div-table-row listHeader ">
						        <div class="div-table-col title width100 "><s:text name="privious"/>&nbsp;<s:text name="Apt_appointment"/> </div>	
						        <div class="div-table-col " style="width: 10%" align="center"> <s:text name="select"/></div>				
								<div class="div-table-col " style="width: 20%" align="center"> <s:text name="Apt_appointment"/>&nbsp;<s:text name="number"/> </div>
						        <div class="div-table-col " style="width: 20%" align="center">  <s:text name="Apt_appointment"/>&nbsp; <s:text name="date"/>&nbsp;<s:text name="and"/><s:text name="time"/></div>
								<div class="div-table-col " style="width: 20%" align="center"><s:text name="status"/></div>
								<div class="div-table-col " style="width: 30%" align="center"><s:text name="global.remarks"/>  </div>
								
							</div>
							<c:forEach items="${MAP_PREV_APPT}" var="count">
							 <div class="div-table-row listData  ">
								    <div class="div-table-col " style="width: 10%" align="center">
								   		 <c:set var="counter" value="${count.value.appointmentStatus}"/>
								    		 <c:if test="${counter==1 || counter==2}"> 
											<input type="radio"  name="_rowappointmentNo"  value='${count.value.appointmentNo}#${count.value.allActualParameterId}#${count.value.mobileNo}#${count.value.emailId}#${count.value.appointmentTypeId}' id="SelappointmentNo" onchange="check(this);"/>
			       							</c:if>
							
											<c:if test="${counter==3 ||counter==0}"> 
												<input type="radio"  name="_rowappointmentNo"  value='${count.value.appointmentNo}'  id="SelappointmentNo" disabled="disabled"/>
											</c:if>
									</div>
								
							        <div class="div-table-col " style="width: 20%" align="center">${count.value.appointmentNo}</div>
						            <div class="div-table-col " style="width: 20%" align="center">${count.value.appointmentDate}  &   ${count.value.appointmentTime}</div>
								    <div class="div-table-col " style="width: 20%" align="center">
												<c:set var="counter" value="${count.value.appointmentStatus}"/>
						        				<c:if test="${counter==1}"> New</c:if>
						        				<c:if test="${counter==2}"> Reschedule</c:if>
						        				<c:if test="${counter==3}"> Confirmed</c:if>
						        				<c:if test="${counter==0}"> Cancelled</c:if>
						            </div>
						        			<c:if test="${counter==3 ||counter==0 ||counter==2}"> 
							        			<div class="div-table-col " align="center" id='ReasonForCancellation' style="width: 30%"> 
							        		      ${count.value.statusRemarks}
							     			    </div>
						     			    </c:if>
						     		   
						     		        <div class="div-table-col label" style="width: 10%" style="display:none;">
						     			    <div id="appointmentQueueN">
						     			    <c:set var="counter" value="${count.value.appointmentQueueNo}"/>
						     			    <input type="hidden" name="appointmentQueueNo" id="appointmentQueueNo" value="${count.value.appointmentQueueNo}" type="text" style="width: 75px;">   
						     			    </div>	
						     			    </div> 	
							 </div>
							</c:forEach>
						</div>
					</div>
							<div class="div-table-button" id="RescheduleCancel" style="display:none;">
									<div class="div-table-row footerBar">
											<div class="div-table-col"> </div>
									</div>
											<div class="div-table-row emptyBar">
												<div class="div-table-col"> </div>
											</div>
						            <div class="div-table-row" align="center" >
							          	<a href="#" class="largebutton" id="Reschedule" onclick="displayReschedule();" ><span class="save"><s:text name="reschedule"/></span></a>
							        	<a href="#" class="largebutton" id="cancel" onclick="displayCancel();"><span class="cancel"><s:text name="Apt Cancel"/></span></a>
							        	<a href="#" class="button" id="cancelId" onclick="cancel();"><span class="cancel"><s:text name="cancel"/></span></a>							        	
						        	</div>
				        	</div>
		    	</s:if>
			</div>


<!-- display when click on cancel button -->			
			<div id='div2' style="display:none;">
					 <div class="div-table">
						<div class="div-table-row ">
							<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="reason"/>&nbsp;<s:text name="for"/>&nbsp;<s:text name="cancellation"/> </div>
								<div class="div-table-col control"  style="width: 25%;" >
							   		<textarea rows="2" cols="20" name="remarks" id="Showremarks"></textarea>
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
										<a href="#" class="button" id="cancelAppointment" onclick="cancelAppointmentAction();"><span class="save"><s:text name="apt"/>&nbsp<s:text name="cancel"/></span></a>
										<a href="#" class="button" id="clearCancelId" ><span class="clear"><s:text name="clear"/></span></a>
										<a href="#" class="button" id="cancelId" onclick="cancel();"><span class="cancel"><s:text name="cancel"/></span></a>
				
									</div>				
					   </div>
		    </div>
			
			<!-- appointment no wise   -->
				<div class="div-table-row " id='divpatient_2' style="display: <%=display%>">
					<div class="div-table">
						<div class="div-table-row ">
							<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="Apt_appointment"/>&nbsp;<s:text name="number"/> </div>
								<div class="div-table-col control" style="width: 25%;">
<!--                						  <input name="SelectappointmentNo" id="SelectappointmentNO"  type="text" style="width: 75px;"> -->
               						   <input name="_goappointmentNo" id="SelectappointmentNO"  type="text" style="width: 75px;">
									   <img id="go" src='/HISRegistration/hisglobal/images/GO.png' style="cursor:pointer" onclick="SearchAppointmentNoWiseAction();">
								</div>
								<div>
								<%-- <s:if test="%{#session.MAP_PREV_APPT}">
								<c:forEach items="${MAP_PREV_APPT}" var="count">
								<div class="div-table-col label" id='Reasonn' style="width: 30%"> 
							        		     <s:text name="Apt_appointment"/>&nbsp;<s:text name="number"/>&nbsp;<s:text name="wise"/> 
							     			    </div>
								</c:forEach>
								</s:if> --%>
								</div>
					   </div>
				</div>
			</div>
				
		<!--Display when click on Reschedule Button  -->
		<%-- <s:if test="%{#session.MAP_PREV_APPT}">
		<c:forEach items="${MAP_PREV_APPT}" var="count"> --%>
			<div id='div1' style="display:none;">
				 <div class="div-table">
					<div class="div-table-row ">
						<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="Apt_appointment"/>&nbsp;<s:text name="date"/></div>
						<div class="div-table-col control" style="width: 25%;">
								  <input name="appointmentDate"  tabindex="1" id="appointmentDate" maxlength="10" type="text" style="width: 75px;">   
							      <input name="appointmentTime" id="appointmentTime"  type="text" style="width: 75px;display: none">   
						</div>
						<div class="div-table-col label" style="width: 25%" >Appointment Type</div>
						<div id="_divappointmentTypeId" class="div-table-col control" style="width: 25%;">
									<%-- <c:set var="appType" value="${count.value.appointmentTypeId}"/>
			        				<c:if test="${appType==1}"> Normal</c:if>
							        <c:if test="${appType==2}"> Urgent</c:if> --%>
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
						   <input name="mobileNo" maxlength="12" type="text" value="${count.value.mobileNo}">   
						</div>
						<div class="div-table-col label" style="width: 25%" ><s:text name="global.email"/>&nbsp;<s:text name="global.id"/></div>
						<div class="div-table-col control" style="width: 25%;">
						   <input name="emailId" maxlength="50" type="text" value="${count.value.emailId}">
						</div>	
					</div>			
					<div class="div-table-row ">
						<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="Apt_appointment"/>&nbsp;<s:text name="mode"/> </div>
						<div class="div-table-col control" style="width: 25%;">
										<s:select 	list="%{#session.LSTAPPOINTMENTMODE}" listKey="value" listValue="label" name="appointmentMode" > </s:select>
									<s:else >
										<select  name="appointmentMode" >
											<option value="-1">Select</option>
										</select>
									</s:else> 
						</div>
						<div class="div-table-col label" style="width: 25%" ><font color="red">*</font><s:text name="reason"/>&nbsp;<s:text name="for"/>&nbsp;<s:text name="reschedule"/></div>
						<div class="div-table-col control" style="width: 25%;" >
						   <textarea rows="2" cols="20" name="resheduleRemarks" id="resheduleRemarks"></textarea>
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
						<a href="#" class="button" id="submitId" ><span class="save"><s:text name="save"/></span></a>
						<a href="#" class="button" id="clearId" ><span class="clear"><s:text name="clear"/></span></a>
						<a href="#" class="button" id="cancelId" onclick="cancel();"><span class="cancel"><s:text name="cancel"/></span></a>
					</div>				
				</div>
			</div>
				<div class="div-table-button" id="cancelFooterId"> 	
					<div class="div-table-row footerBar">
							<div class="div-table-col"> </div>
					</div>			
					<div class="div-table-row emptyBar">
							<div class="div-table-col"> </div>
					</div>	
					<div class="div-table-row" align="center">
						<a href="#" class="button" id="cancelId" onclick="cancel();"><span class="cancel"><s:text name="cancel"/></span></a>
					</div>				
				</div>			
		<%-- </c:forEach>
		</s:if> --%>
	</div>		
			<s:hidden name="afterGo" />
			<s:hidden name="appointmentNo"  />			
			<s:hidden name="allActualParameterId"  />
			<s:hidden name="appointmentTypeId"  />
				
				
</div>
</s:form>
</center>
<his:status />

<div id='loadingmessage' style='display:none'>
       <img src='/HISRegistration/hisglobal/images/ajax-loader.gif'/>
</div>

</body>
<script>

$('#clearId').click(function(e){	
	
	$('#appointmentDate').val('');	
	$('#appointmentTime').val('');
	$('#resheduleRemarks').val('');
	$("[name='mobileNo']").val('');
	$("[name='emailId']").val('');

	
});

$('#clearCancelId').click(function(e){	
	
	$('#Showremarks').val('');	
});


 function SearchAppointmentNoWiseAction()
{
	 if ($('#RescheduleCancelAppointment').form('validate')) {
	 	$('[name="appointmentNo"]').val($('[name="_goappointmentNo"]').val());
		document.getElementById("RescheduleCancelAppointment").action="SearchAppointmentNoWiseRescheduleCancelAppointment.action";
		document.getElementById("RescheduleCancelAppointment").submit();
	 }
}


</script>

</html>