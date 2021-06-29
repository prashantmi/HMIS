var ageMaxBound = 125;

function submitForm(mode) {
	document.forms[0].action = mode + "NewAppointment.action";
	document.getElementsByName("afterGo")[0].value="1";
	document.forms[0].submit();
	

}
function getActualParaIdWiseEssensials() {
	
	var action = "/HISRegistration/appointment/transactions/getActualParaIdWiseDetailNewAppointment.action";
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
				//getSlotDetails();
				getSlotDetails_withCapping();
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('getActualParaIdWiseEssensials ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
}


function changePatientType(){
	//alert('m here'+document.getElementsByName("afterGo")[0].value);
	
	
	var val=this.value;
	if(!val){
		if(document.getElementsByName("patientType")[0].checked)
			val=document.getElementsByName("patientType")[0].value;
		else
			val=document.getElementsByName("patientType")[1].value;
	}
	//alert(val);
	if(val==1){
		$('#divpatient_2').hide();
		$('#div_appointment').hide();
		$('#divpatient_1').show('slow');
		$('#div_appointment').hide('slow');
		
		var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
		//alert("HOLIDAY_DATES "+HOLIDAY_DATES);
		$('#appointmentDate').DatePicker({
			format: 'd-M-Y',default_position :'below',
			//disabled_dates:['1 11 2015','11 11 2015'],
			disabled_dates:HOLIDAY_DATES,
			start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		
		$('[name="patFirstName"]').validatebox({
			required:false				
		});
		
		$('[name="patAge"]').validatebox({
			required:false				
		});
		
		$('[name="patAgeUnit"]').validatebox({
			required:false,
			validType: 'selectCombo[-1]'		
		});
		
		$('[name="patGenderCode"]').validatebox({
			required:false				
		});
		$('[name="patGuardianName"]').validatebox({
			required:false				
		});
		$('[name="patGenderCode"]').validatebox({
			required:false				
		});
		$('[name="mobileNo"]').validatebox({
			required:false,
			validType : null
		});	
		
		$('[name="emailId"]').validatebox({
			validType : null
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
				
		$('[name="patFirstName"]').validatebox({
			required:true,
			validType:'alphaWithSpace'
		});
		
		$('[name="patMiddleName"]').validatebox({
			validType:'alphaWithSpace'
		});
		
		$('[name="patLastName"]').validatebox({
			validType:'alphaWithSpace'
		});
		
		$('[name="patAge"]').validatebox({
			required:true,
			validType:['numeric','startZero']
		});
		
		$('[name="patAgeUnit"]').validatebox({
			required:true,
			validType: 'selectCombo[-1]'		
		});
		
		$('[name="patGenderCode"]').validatebox({
			required:true,
			validType: 'selectCombo[-1]'		
		});
		
		$('[name="patGuardianName"]').validatebox({
			required:true,
			validType:'alphaWithSpace'
		});
		
		$('[name="patHusbandName"]').validatebox({
			validType:'alphaWithSpace'
		});
		
		$('[name="mobileNo"]').validatebox({
			required:true,
			validType : [ 'numeric', 'equalLength[10]','mobileNostartwithseven','NotAllZero']
		});		

		$('[name="emailId"]').validatebox({
			validType : 'email'
		});
				
	}
	if(document.getElementsByName("afterGo")[0].value==1){
		$('#div_appointment').show('slow');
		$('#div_patientType').show();
		$('#div_patientType').hide();		
	}
	//By Mukund on 13.04.2017 
	clearFormFields();
}
function validateFields(){
	//patHusbandName
	
    if($('#appointmentDtl').is('select')){	
		$('[name="appointmentDtl"]').validatebox({
			required:true,
			validType: 'selectCombo[-1]'		
		});
    }
	
	$('[name="appointmentDate"]').validatebox({
		required:true				
	});
	
	if($('#appointmentTime').css('display')!="none"){
		$('[name="appointmentTime"]').validatebox({
			required:true				
		});
	}
	else{
		$('[name="appointmentTime"]').validatebox({
			required:false			
		});
	}
	$('[name="appointmentTypeId"]').validatebox({
		required:true,
		validType: 'selectCombo[-1]'		
	});
	$('[name="mobileNo"]').validatebox({
		required:true,
		validType : [ 'numeric', 'equalLength[10]','mobileNostartwithseven','NotAllZero']
	});	
	
	$('[name="emailId"]').validatebox({
		validType : 'email'
	});
}

function clearFormFields () {
  // alert("testing");
	$('#fatherorSpouseError').hide();
	if($('[name="actualParameterId"]')[0]!=undefined)
	$('[name="actualParameterId"]')[0].value = "-1";
	$('[name="appointmentDtl"]')[0].value = "-1";
	$('[name="patFirstName"]')[0].value = "";
	$('[name="patMiddleName"]')[0].value = "";
	$('[name="patLastName"]')[0].value = "";
	$('[name="appointmentTypeId"]')[0].value = "-1";
	$('[name="appointmentMode"]')[0].value = "11";
	$('[name="patAge"]')[0].value = "";
	$('[name="patAgeUnit"]')[0].value = "Yr";
	$('[name="patGenderCode"]')[0].value = "-1";
	$('[name="patGuardianName"]')[0].value = "";
	$('[name="patHusbandName"]')[0].value = "";
	$('[name="mobileNo"]')[0].value = "";
	$('[name="emailId"]')[0].value = "";
	$('[name="remarks"]')[0].value = "";
	var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
	//alert("HOLIDAY_DATES "+HOLIDAY_DATES);
	$('[name="appointmentDate"]')[0].value = "";
	$('[name="appointmentTime"]')[0].value = "";
	//getSlotDetails();
	getSlotDetails_withCapping();
	$('#appointmentDate').DatePicker({
		format: 'd-M-Y',default_position :'below',
		//disabled_dates:['1 11 2015','11 11 2015'],
		disabled_dates:HOLIDAY_DATES,
		start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
	}).val(today);
	
}


function calculateMaxRangeValue(age, ageUnit) {
	var maxAgeRange = 125;

     if (ageUnit == "D") {
		maxAgeRange = (age * 365);
	} else if (ageUnit == "Wk") {
		maxAgeRange = age * 365 / 7;
	} else if (ageUnit == "Mth") {
		maxAgeRange = age * 12;
	} else if (ageUnit == "Yr") {
		if (age < maxAgeRange)
			maxAgeRange = age;
	}
	
	return maxAgeRange;
}
	
function showDivAge() {
		
		var maxAgeRange = calculateMaxRangeValue(ageMaxBound, $('[name="patAgeUnit"]').val());
			var ageRangeValidType = 'range[1,' + maxAgeRange + ']';		
			$('[name="patAge"]').focus();
			
			$("#patAge").validatebox({
				required : true,
				validType : [ 'numeric', ageRangeValidType , 'startZero' ]
			});
	}
	
	
function showMobileNo() {
		$('[name="mobileNo"]').validatebox({
			validType : [ 'numeric', 'equalLength[10]','mobileNostartwithseven','NotAllZero']
		});
	}
	
function showEmailId() {
	$('[name="emailId"]').validatebox({
		validType : 'email'
	});
}
