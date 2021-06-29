
function submitForm(mode) {
	document.forms[0].action = mode + "RescheduleCancelAppointment.action";
	//document.getElementsByName("afterGo")[0].value="1";
	document.forms[0].submit();
	

}
function getActualParaIdWiseEssensials() {
	
	var action = "/HISRegistration/appointment/transactions/getActualParaIdWiseDetailRescheduleCancelAppointment.action";
	var data = {
			actualParameterReferenceId:$("#actualParameterReferenceId").val()
	  };
	
	$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				var json= eval('(' + returnHTML + ')');
				$('#appointmentTypeId').html(json["optionHTML"]);				
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('getActualParaIdWiseEssensials ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
}

/*
 function Added By Raj kumar for Disable holiday dates
 * */
 
function changePatientType(){
	//alert('m here');
	var val=this.value;
	if(!val){
		if(document.getElementsByName("patientType")[0].checked)
			val=document.getElementsByName("patientType")[0].value;
		else
			val=document.getElementsByName("patientType")[1].value;
	}
	//alert("Inside Pat type ::val--"+val);
	if(val==1){
		$('#divpatient_2').hide();
		//$('#div_appointment').hide('slow');
		$('#div_appointment').hide();
		$('#divpatient_1').show('slow');
		
		var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
		//alert("HOLIDAY_DATES "+HOLIDAY_DATES);
		$('#appointmentDate').DatePicker({
			format: 'd-M-Y',default_position :'below',
			//disabled_dates:['1 11 2015','11 11 2015'],
			disabled_dates:HOLIDAY_DATES,
			start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		
		$('[name="_goappointmentNo"]').validatebox({
			required:false,
			validType: null		
		});	
	}	
	else{
		
		$('#divpatient_1').hide();
		$('#divpatient_2').show('slow');
		$('#div_appointment').show('slow');
		
		var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
		//alert("HOLIDAY_DATES "+HOLIDAY_DATES);
		$('#appointmentDate').DatePicker({
			format: 'd-M-Y',default_position :'below',
			//disabled_dates:['1 11 2015','11 11 2015'],
			disabled_dates:HOLIDAY_DATES,
			start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		
		$('[name="_goappointmentNo"]').validatebox({
			required:true,
			validType: 'numeric'		
		});		
	
	}
	//alert("after Go"+document.getElementsByName("afterGo")[0].value);

	if(document.getElementsByName("afterGo")[0].value==1){
		$('#div_appointment').show('slow');
		//$('#div_patientType').show();
		$('#div_patientType').hide();		
	}
}
function validateRescheduleFields(){
	
	$('[name="appointmentDate"]').validatebox({
		required:true				
	});
	$('[name="emailId"]').validatebox({
		validType : 'email'
	});
	/*$('[name="appointmentTime"]').validatebox({
		required:true				
	});*/
	/*$('[name="appointmentTypeId"]').validatebox({
		required:true,
		validType: 'selectCombo[-1]'		
	});*/
	$('[name="mobileNo"]').validatebox({
		required:true,	
		validType : 'mobile'
	});	
}
//To Show the Apt Free Slot on the Change of Apt Date,Added by Singaravelan on 11-Mar-2015
function getReSchSlotDetails(){
		
	var i,paraId="",aptDate="",crNo="";
	var tagView=1;
	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));
	aptDate=$('[name="appointmentDate"]')[0].value;	
	var _aptChkdate=$.datepicker.parseDate("dd-M-yy", $('[name="appointmentDate"]').val());
	paraId=$('[name="allActualParameterId"]').val();
	aptId=$('[name="appointmentForId"]').val();
	//alert("value for aptid in js "+aptId);
	//alert("-syDate-"+today+"-aptDate-"+aptDate+"-_aptChkdate-"+_aptChkdate+"-Result-"+(_aptChkdate>=today)+"-ParaId-"+paraId);
	
	/*for (i=0;i<$('select[name^="actualParameterId"]').length;i++){
		paraId+=$('[name="actualParameterId"]')[i].value+"^";
	}
	for (i=$('select[name^="actualParameterId"]').length;i<7;i++){
		if(i!=6)paraId+="0"+"^";
		else paraId+="0";
	}*/
	var apptType='';
	if(undefined!=$('[name="apptType"]').val())
		apptType = $('[name="apptType"]').val();
	//alert(apptType);return false;
	
	if(undefined!=$('[name="patCrNo"]')[0])
		crNo=$('[name="patCrNo"]')[0].value;

	if(undefined!=$('#aptTagRow'))
		$('#aptTagRow').hide();
	
	$('[name="isProcessReschedule"]').val("0");
	var isProcessRescheduleAndSameDate  = $('[name="isProcessReschedule"]').val();//if 0 works as it is if 1 then works to get timeslot for the day with previous appointment date
	var previousApptDateSelected = $.datepicker.parseDate("dd-M-yy", $('[name="prevAptDate"]').val());
	//alert(previousApptDateSelected.getTime()+"\n"+_aptChkdate.getTime());
	if(previousApptDateSelected.getTime() === _aptChkdate.getTime()){//previous appt date is equal to date selected than allow to create time slots else not
		isProcessRescheduleAndSameDate = 1;
	}
	$('[name="isProcessReschedule"]').val(isProcessRescheduleAndSameDate);
	/* alert(_aptChkdate+"\n"+today.getTime()+"\n"+$('[name="isProcessReschedule"]').val()+"\n"+(_aptChkdate.getTime()===today.getTime()));return false;*/
	if(paraId!="" && (_aptChkdate>=today)){
		
		var action ="";
			action = "/HISRegistration/appointment/transactions/makeTimeSlotTagHTML_withCappingAppointmentTags.action";

		var data = {
				paraId : paraId,
				aptId : aptId,
				crNo : crNo,
				tagView : tagView,
				appointmentDate : aptDate,
				apptType : apptType,
				isProcessReschedule : isProcessRescheduleAndSameDate
		  };
		
		$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				if(returnHTML.indexOf('@')>0)
					returnHTML=returnHTML.split('@')[1];
				else
					returnHTML="";
				$('#aptTagId').html(returnHTML);
				if(undefined!=$('#aptTagRow')&& returnHTML.length>0){
					$('#aptTagRow').show('slow');
					if(undefined!=$('#aptTime'))
						$('#aptTime').hide();
				}
				else
				{
					alert("No Slots Available");
					if(undefined!=$('#aptTime'))
						$('#aptTime').show('slow');
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