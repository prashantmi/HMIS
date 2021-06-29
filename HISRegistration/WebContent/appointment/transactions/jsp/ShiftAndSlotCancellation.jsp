<%System.out.println("Reschedule Cancel Appointment jsp"); %>
<%@page import="hisglobal.hisconfig.Config,hisglobal.presentation.Status"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%> 
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">
<link rel="stylesheet" href="/HIS/appointment/css/appointment.css">


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

<script type="text/javascript" src="./../../appointment/transactions/js/ShiftAndSlotCancellation.js"></script>
<script type="text/javascript" src="./../../appointment/transactions/js/appointment.js"></script>
<script type="text/javascript" src="./../../registration/masters/js/registration.js" /></script>
<script type="text/javascript" src="./../../registration/transactions/js/popup.js"></script>

<script src="../../hisglobal/js/jquery.plugin.js"></script>
<script src="../../hisglobal/js/jquery.timeentry.js"></script>

<script language="JavaScript" type="text/javascript" src="/HISRegistration/hisglobal/masterutil/js/jquery/security.js" ></script>
<title>Shift/Slot Cancellation</title>

<style type="text/css">
a { cursor: pointer; cursor: hand; }
img { cursor: pointer; cursor: hand; }
.custbtncolor .ui-button-text{
		background: #086FA6;
		color: #FFFFFF;
		font-weight: bold
}
.custoverlay{opacity: 0.5;}
.no-close .ui-dialog-titlebar-close {display: none }
</style>
<script>

$(document).ready(function() {
	$( "#appointmentDateId" ).datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat:"dd-M-yy",
		onSelect: function(d,i){
	          if(d !== i.lastVal){
	              $(this).change();
	          }
	     }
	}).datepicker("setDate", "0");
	
	$('#appointmentDateId').change(function(){
		showShiftDetails();
		
	});
	
	/* $("img").hover(function(){
		alert("Hover Image");
	}
	); */


});

function showShiftDetails()
{
	var i,paraId="",aptDate="",aptId="";
	var tagView=1;
	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));
	aptDate=$('[name="appointmentDate"]')[0].value;	
	aptId=$('[name="appointmentForId"]')[0].value;
	var _aptChkdate=$.datepicker.parseDate("dd-M-yy", $('[name="appointmentDate"]').val());
	//alert("-syDate-"+today+"-aptDate-"+aptDate+"-_aptChkdate-"+_aptChkdate+"-Result-"+(_aptChkdate>=today));
	document.getElementsByName("selectedSlots")[0].value="";
	for (i=0;i<$('select[name^="actualParameterId"]').length;i++){
		paraId+=$('[name="actualParameterId"]')[i].value+"^";
	}
	for (i=$('select[name^="actualParameterId"]').length;i<7;i++){
		if(i!=6)paraId+="0"+"^";
		else paraId+="0";
	}
	
	if(paraId!="" && (_aptChkdate>=today)){
		
		var action = "/HISRegistration/appointment/transactions/getAvailShiftShiftAndSlotCancellation.action";
		var data = {
				aptId : aptId,
				paraId : paraId,	
				appointmentDate:aptDate
		  };
		
		$.ajax({
			url : action, 
			type : "POST",
			async : false,
			data : data,
			dataType : "html",
			beforeSend: function() {
				$('#modal').show();$('#fade').show();
	        }, 
	        complete: function() {
	       	 	$('#modal').hide();$('#fade').hide();  
	       	 },
			success : function(returnHTML) {				
				//$('#shiftDiv').hide('blind');	
				$('#shiftDiv').hide();
				$('#shiftDiv').html(returnHTML);
				if(returnHTML.length>0){
					//$('#shiftDiv').show('blind');
					$('#shiftDiv').show();
				}
				else
				{
					alert("No Shifts Available");
					
				}
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
	}
	else
	{
		if(!(_aptChkdate>=today))
			$('#appointmentDate').focus();
			
	}
}

function cancel()
{
	    document.getElementById("ShiftSlotCancellation").action="cancelShiftAndSlotCancellation.action";
		document.getElementById("ShiftSlotCancellation").submit();
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
<s:form action="ShiftSlotCancellation">
	<br>
    <div class="wrapper rounded" style="width:98%" >
	 <div class="div-table">
		<div class="div-table-row ">
			<div class="div-table-col title width100 ">
					<s:text name="shift"/>&nbsp;<s:text name="/"/>&nbsp;<s:text name="slot"/>&nbsp;<s:text name="cancellation"/>     
			</div>
		</div>
			
			
		<s:hidden name="selectedSlots"  />	
		<s:hidden name="aptId"  />
		<s:hidden name="paraRefId"  />
		<s:hidden name="shiftId"  />
		<s:hidden name="aptDate"  />
		<div class="div-table " id="bulkCancellationDiv">
			<div class="div-table-row ">
						<appt:AppointmentParameterComboTag  tagView="TRANSACTION" controllerName="NewAppointment"   scriptCallBackFunctionName="getActualParaIdWiseEssensials"/>
			</div>
			<div class="div-table-row ">
					<div class="div-table-col label" style="width: 25%;"><font color="#FF0000">*</font>&nbsp;<s:text name="date"/> </div>	
					<div class="div-table-col control" id="divFromDate" style="width: 25%;">
						       <input id="appointmentDateId" tabindex="1" type="text" readonly="readonly" name="appointmentDate" style="width: 85px;">&nbsp;
					</div>	
			
			</div>
			<div class="div-table-row " id="shiftDiv" style="display:none"> 
			</div>
			
			<%-- <div class="div-table-row " id="shiftCancelReasonDiv" style="display:none">
						<div class="div-table-col label" style="width: 50%" ><font color="red">*</font><s:text name="reason"/>&nbsp;<s:text name="for"/>&nbsp;<s:text name="reschedule"/></div>
						<div class="div-table-col control" style="width: 50%;" >
						   <textarea rows="2" cols="20" name="resheduleRemarks" id="resheduleRemarks"></textarea>
						</div>	
			</div>		
			 --%>
			
		</div>	
			
			<div class="div-table-button" id="cancelFooterId"> 	
						<div class="div-table-row footerBar">
								<div class="div-table-col"> </div>
						</div>			
						<div class="div-table-row emptyBar">
								<div class="div-table-col"> </div>
						</div>	
						<div class="div-table-row" align="center">
 							<a href="#" class="button" id="submitCancelId" onclick="cancelSelectedSlots();" style="display: none"><span class="save"><s:text name="Slot Cncl"/></span></a>
<%--  							<a href="#" class="button" id="submitRenewalId" onclick="cancel();" style="display: none"><span class="cancel"><s:text name="Save"/></span></a> 							 --%>
							<a href="#" class="button" id="cancelId" onclick="window.parent.closeTab('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');"><span class="cancel"><s:text name="cancel"/></span></a> 
							
						</div>				
			</div>	
		</div>	
		
		
	</div>
	<div id="statusMessage" style="color: red;display: none"/>
			<s:hidden name="afterGo" />
			<s:hidden name="appointmentNo"  />			
			<s:hidden name="allActualParameterId"  />
			<s:hidden name="appointmentTypeId"  />			
				
				
<!-- </div>-->
<cmbPers:cmbPers></cmbPers:cmbPers>
<s:token></s:token>
</s:form>
</center>
<his:status />

<div id='loadingmessage' style='display:none'>
       <img src='/HISRegistration/hisglobal/images/ajax-loader.gif'/>
</div>
<div id="fade"></div>
 <div id="modal">
     <img id="loader" src="/HIS/hisglobal/images/loading.gif" />
 </div>

</body>
<script>

</script>

</html>