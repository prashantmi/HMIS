function openModalForVital(e){
	
		
	
		$("#VitalIDModal").modal()  ;
		$("#strVitalModifyId").val('0');
		var patName = $(e).closest('.patientListBlock').find('.patName').text();
		var patCrNo = $(e).closest('.patientListBlock').find('.patCrNo').text();
		var patEpisodeVisitNo = $(e).closest('.patientListBlock').find('.patEpisodeVisitNo').text();
		var patLastVisitDate = $(e).closest('.patientListBlock').find('.patLastVisitDate').text()==='' ? 'First Visit' : $(e).closest('.patientListBlock').find('.patLastVisitDate').text(); //Changed By Timsi Kataria as suggested by Priyesh Sir
		var patVisitType = $(e).closest('.patientListBlock').find('.patVisitType').text();
		var patGaurdianName = $(e).closest('.patientListBlock').find('.patGaurdianName').text(); 
		var patDeptName = $(e).closest('.patientListBlock').find('.patDeptUnit').text();
		var nextPatName = $(e).closest('.patientListBlock').next().find('.patName').text(); 
		var prevPatName = $(e).closest('.patientListBlock').prev().find('.patName').text();
		var patGenAgeCat = $(e).closest('.patientListBlock').find('.patGenAgeCat').text();
		var episodeCode = $(e).closest('.patientListBlock').find('.patEpisodeCode').text();
		var hospitalCode = $(e).closest('.patientListBlock').find('.patHospitalCode').text();
		var seatId = $(e).closest('.patientListBlock').find('.patSeatId').text(); 
		var patConsultantName = $(e).closest('.patientListBlock').find('.patConsultantName').text();  
		var patCompleteGeneralDtl = $(e).closest('.patientListBlock').find('input[name=patCompleteGeneralDtl]').val();
		$("#patName").text(patName);
		$("#crNo").text(patCrNo);
		$("#patAge").text(patGenAgeCat);
		$("#patGender").text('');
		$("#strPatientDtlId").val(patCompleteGeneralDtl);
		
		console.log('patGenAgeCat::::'+patGenAgeCat.split('/')[0]);
		
		if(patGenAgeCat.split('/')[0] == 'M')
		$("#PregnancyDivId").hide();
		else
		$("#PregnancyDivId").show();
		
		var age = (patGenAgeCat.split('/')[1]).replace('Yr','') ; 
		if(age.trim() <= '18')
		$("#curcumferenceIdDivId").show();
		else
		$("#curcumferenceIdDivId").hide();
		
		if(age.trim() <= '18')
			$("#muacDivId").show();
			else
			$("#muacDivId").hide();
		
		$("#strPatHiddenDtlsId").val(patName.trim()+'^'+patCrNo+'^'+episodeCode+'^'+patEpisodeVisitNo+'^'+hospitalCode+'^'+patLastVisitDate+'^'+patVisitType+'^'+seatId);
		//var str ='<b style="color:#015CB9;"><span id="patName">Patient Name:'+patName+' </span> / <span id="crNo">CR Number:'+patCrNo+'</span> / <span id="patAge">Age:'+patGenAgeCat+'</span> / <span id="patGender">Male</span></b>';
		console.log('patCompleteGeneralDtl=='+patCompleteGeneralDtl);
      	console.log('crNo=='+patCrNo);
      	console.log('patGenAgeCat'+patGenAgeCat);
      	console.log('patEpisodeVisitNo'+patEpisodeVisitNo);
      	
      	
      	var vitalJSON = { 
    			'CR_No' : patCrNo,
    			'episodeCode' : episodeCode,
    			'hospitalCode' : hospitalCode,
    			'visitNo' : patEpisodeVisitNo
    	};
      	var data = JSON.stringify(vitalJSON);
      	$.ajax({url:'/HISDRDESK/services/restful/DrDesk/ModifyVital/',type:'POST',data:{JsonData:data},success:function(result)
        	{
    			console.log(result);
    			console.log(result.status);
    			console.log(result.VitalDtls);
    			
    			if(result.status == 1)
    				{
    				
    				/* $.alert({
    				        title: 'Success!',
    				        content: 'Data Saved successfully!!',
    				    });
    				 */
    				 console.log('Vital Data Saved successfully');
    				 if((result.VitalDtls).length > 0 ){
    				 result.VitalDtls.forEach(function(item){
    					 $("#weightId").val(item.HOPLNUM_WEIGHT_VAL);
    					 $("#heightId").val(item.HOPLNUM_HEIGHT_VAL);
    						$("#bmiId").val(item.HOPLNUM_BMI_VAL);
    						$("#temperatureId").val(item.HOPLNUM_TEMP_VAL);
    						$("#respRateId").val(item.HOPLNUM_RR_VAL);
    						$("#haemoglobinId").val(item.HOPLNUM_HB_VAL);
    						$("#diastolicId").val(item.HOPLNUM_BPDIASY_VAL);
    						$("#systolicId").val(item.HOPLNUM_BPSY_VAL);
    						$("#fastingId").val(item.HOPLNUM_SUGARFAST_VAL);
    						$("#ppRateId").val(item.HOPLNUM_SUGARPP_VAL);
    						$("#hba1cId").val(item.HOPLNUM_HBA1C_VAL);
    						$("#symptomsId").val(item.HOPLSTR_SYMPINFO_VAL.replace(/,/g, ''));
    						 $('#bmiErrmsg').text(item.HOPLSTR_BMI_CLASSNAME);
    						 $('#temperatureErrmsg').text(item.HOPLSTR_TEMPRETURE_CLASSNAME);
    						 $('#bpErrmsg').text(item.HOPLSTR_BP_CLASSNAME);
    						 $('#fastingErrmsg').text(item.HOPLSTR_DIABETIC_CLASSNAME);
    						 $('#ppRateErrmsg').text(item.HOPLSTR_SUGARPP_CLASSNAME);
    						 $('#hba1cErrmsg').text(item.HOPLSTR_HBA1CCLASSNAME);
    						 $('#respRateErrmsg').text(item.HOPLSTR_RR_CLASSNAME);
    						 $("#pulseRateId").val(item.HOPLNUM_PULSE_RATE);
    						 $("#bloodGroupId").val(item.HOPLSTR_BLOOD_GROUP);
    						 $("#curcumferenceId").val(item.HOPLNUM_CURCUMFERENCE_VAL);
    						 $("#muacId").val(item.HOPLNUM_MUAC_VAL);
    						 /*----------------------added for cancer screening---------------*/
    						 $("#cancerScreeningId :selected").val(item.HOPLSTR_CANCER_SCREENING);
    						 
    						 if(item.HOPLSTR_SMOKING != '')
    								$("#SmokingIdvalue").text(item.HOPLSTR_SMOKING);
    							else
    								$("#Smokingid").hide();
    							
    							if(item.HOPLSTR_ANEMIC != '')
    								$("#AnemicIdvalue").text(item.HOPLSTR_ANEMIC);
    							else
    								$("#Anemicid").hide();
    							
    							if(item.HOPLSTR_PREGNANCY != '' && item.HOPLSTR_PREGNANCY != '0')
    								$("#PregnancyIdvalue").text(item.HOPLSTR_PREGNANCY+' Wk');
    							else
    								$("#Pregnancyid").hide();
 							
    						 //$("#strPatHiddenDtlsId").val(''+'^'+CR_No+'^'+episodeCode+'^'+visitNo+'^'+hospitalCode+'^'+''+'^'+''+'^'+'10');
    						// $("#strVitalModifyId").val('1');
    				 });
    				 }else{
    					 console.log('Error!!!');
    					// $("#strVitalModifyId").val('1');
    					 //$("#strPatHiddenDtlsId").val(''+'^'+CR_No+'^'+episodeCode+'^'+visitNo+'^'+hospitalCode+'^'+''+'^'+''+'^'+'10');
    					 /*$.alert({
    					        title: 'No Record!',
    					        content: 'Data No Avilable',
    					    });*/
    					 
    					 $("#weightId").val('');
 						$("#heightId").val('');
 						$("#bmiId").val('');
 						$("#temperatureId").val('');
 						$("#respRateId").val('');
 						$("#haemoglobinId").val('');
 						$("#diastolicId").val('');
 						$("#systolicId").val('');
 						$("#fastingId").val('');
 						$("#ppRateId").val('');
 						$("#hba1cId").val('');
 						$("#symptomsId").val('');
 						 $('#bmiErrmsg').text('');
 						 $('#temperatureErrmsg').text('');
 						 $('#bpErrmsg').text('');
 						 $('#fastingErrmsg').text('');
 						 $('#ppRateErrmsg').text('');
 						 $('#hba1cErrmsg').text('');
 						 $('#respRateErrmsg').text('');
 						 $('#weightErrmsg').text('');
 						 $('#heightErrmsg').text('');
 						 $("#pulseRateId").val('');
						 $("#bloodGroupId").val('0');
						 $("#cancerScreeningId").val('0');/*----------------------added for cancer screening---------------*/
    					 
    				 }
    				 	
    				}else{
    					console.log('Error!!!');
    					 $.alert({
    					        title: 'Error!',
    					        content: 'Unable to Get Prev Data!!',
    					    });
    				}
        	}
    	});
      	
	
}

var count=0;
	$(document).ready(function(){

		$('.allergiesAddRows').click(function(){
			count++;
			var allergyName=$(this).parent().parent().find('input[name="allergyName"]').val();
			var siteName=$(this).parent().parent().find('select[name="site"] option:selected').text();
			var duration=$(this).parent().parent().find('input[name="noOfDays"]').val();
			var durationCombo=$(this).parent().parent().find('select[name="duration"] option:selected').text();
			var otherInfo=$(this).parent().parent().find('input[name="otherInfo"]').val();
		//alert(allergyName+"  "+siteName+"  "+duration+"   "+durationCombo+"   "+otherInfo);
		if(allergyName.trim()!='' && siteName.trim()!='' && duration.trim()!='' && durationCombo.trim()!='' && otherInfo.trim()!=''){
			str='<div class="row"  id="rowId'+count+'">'+
			'<div class="form-group col-md-3 alignLeftPaddingLeftZero">'+
				'<input type="text" class="form-control" value="'+allergyName+'" name="allergyName" readonly id="allergyNameId'+count+'">'+
			'</div>'+
			'<div class="form-group col-md-2 alignLeftPaddingLeftZero">'+
				'<input type="text" class="form-control" value="'+siteName+'" name="site" readonly id="siteId'+count+'">'+
			'</div>'+
			'<div class="form-group col-md-4 alignLeftPaddingLeftZero">'+
				'<div class="col-md-6">'+
					'<input type="text" class="form-control" value="'+duration+'" name="noOfDays" readonly id="noOfDaysId'+count+'">'+
				'</div>'+
				'<div class="col-md-6 alignLeftPaddingLeftZero">'+
					'<input type="text" class="form-control" value="'+durationCombo+'" name="duration" readonly id="durationId'+count+'">'+
				'</select>'+
				'</div>'+
			'</div>'+
			'<div class="form-group col-md-2 alignLeftPaddingLeftZero">'+
				'<input type="text" class="form-control" value="'+otherInfo+'" name="otherInfo" readonly id="otherInfoId'+count+'">'+
			'</div>'+
			'<div class="form-group col-md-1">'+
				'<button class="btn btn-sm btn-danger allergiesRemoveRow" onclick="$(this).parent().parent().remove();" id="removeBtnId'+count+'">Remove</button>'+
			'</div>'+
			'</div>';
			$(this).closest('.addAllergyNewRowClass').append(str);
			$(this).parent().parent().find('input[name="allergyName"]').val('');
				$(this).parent().parent().find('input[name="noOfDays"]').val('');
				$(this).parent().parent().find('input[name="otherInfo"]').val('');
		}
		else{
			alert("Please fill all the fields.");
		}
		
	});

	/*$('.allergiesRemoveRow').click(function(){
		alert("yruyh");
		$(this).parent().parent().remove();
	});*/

	$("#heightId").keypress(function (e) {
     	var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#heightId").css("border-color","red");
     		$("#heightId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
	        $("#heightErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#heightId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}

		$("#heightId").css("border-color","");
     	$("#heightId").css("box-shadow", "");
     	var weight=$("#weightId").val();
     	var height=$("#heightId").val();
     	if(height == ''){
     		$("#bmiId").val('');
     	}
     	/*if(parseFloat(height) > 250.00){
     		$("#heightErrmsg").html("Height cannot exceed 250cms.").show();
     		$("#heightId").val('');
     	}*/
     	if(parseFloat(height) == 0){
     		$("#heightErrmsg").html("Ht. can't be 0.").show();
     		$("#heightId").val('');
     		$("#bmiId").val('');
     	}
     	else if(parseFloat(height) > 250){
     		$("#heightErrmsg").html("Not Allowed").show();
     		$("#heightId").val('');
     	}
     	else{
     		$("#heightErrmsg").html("");
     	}

     	if(weight!='' && height!='' && weight!='0.00' && height!='0.00'){    // weight!='0.00' && height!='0.00' check added by ashutoshk
     		var temp=parseFloat(parseFloat(height)/100);
     		var bmi=parseFloat(weight)/(temp*temp);

     		if(bmi > 30){
     			$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("Obese").show();
				$('input[name="bmiErrMsgTxt"]').val('Obese');
		        return false;
			}
			else if(bmi < 18.5 ){
				$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("Underweight.").show();
				$('input[name="bmiErrMsgTxt"]').val('Underweight');
		        return false;
			}
			else if(bmi >= 18.5 && bmi <= 24.9 ){
				$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("Normal").show();
				$('input[name="bmiErrMsgTxt"]').val('Normal');
		        return false;
			}
			else if(bmi >= 25.0 && bmi <= 29.9 ){
				$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("Overweight").show();
				$('input[name="bmiErrMsgTxt"]').val('Overweight');
		        return false;
			}
			else{
				$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("");
			}
     	}
	});

	$("#weightId").keypress(function (e) {
     	var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#weightId").css("border-color","red");
     		$("#weightId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
	        $("#weightErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#weightId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		$("#weightId").css("border-color","");
     	$("#weightId").css("box-shadow", "");

     	var weight=$("#weightId").val();
     	var height=$("#heightId").val();
     	//alert('weight:: '+weight);
     	//alert('height ::'+height+' --- '+ height!='0.00');
     	
     	if(weight == ''){
     		$("#bmiId").val('');
     	}
     	/*if(parseFloat(weight) > 200.00){
     		$("#weightErrmsg").html("Weight cannot exceed 200kgs.").show();
     		$("#weightId").val('');
     	}*/
     	if(parseFloat(weight) ==0){
     		$("#weightErrmsg").html("Wt. can't be 0.").show();
     		$("#weightId").val('');
     		$("#bmiId").val('');
     	}
     	else if(parseFloat(weight) > 200){
     		$("#weightErrmsg").html("Not Allowed").show();
     		$("#weightId").val('');
     	}
     	else{
     		$("#weightErrmsg").html("");
     	}

     	if(weight!='' && height!='' && height!='0.00' && weight!='0.00'){ 			// weight!='0.00' && height!='0.00' check added by ashutoshk
     		var temp=parseFloat(parseFloat(height)/100);
     		var bmi=parseFloat(weight)/(temp*temp);

     		if(bmi > 30 ){
     			$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("Obese").show();
				$('input[name="bmiErrMsgTxt"]').val('Obese');
		        return false;
			}
			else if(bmi < 18.5 ){
				$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("Underweight.").show();
				$('input[name="bmiErrMsgTxt"]').val('Underweight');
		        return false;
			}
			else if(bmi >= 18.5 && bmi <= 24.9 ){
				$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("Normal").show();
				$('input[name="bmiErrMsgTxt"]').val('Normal');
		        return false;
			}
			else if(bmi >= 25.0 && bmi <= 29.9 ){
				$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("Overweight").show();
				$('input[name="bmiErrMsgTxt"]').val('Overweight');
		        return false;
			}
			else{
				$("#bmiId").val(bmi.toFixed(2));
				$("#bmiErrmsg").html("");
			}
     	}
	});
	
	$("#respRateId").keypress(function (e) {
     	if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
     		$("#respRateId").css("border-color","red");
     		$("#respRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
	        $("#respRateErrmsg").html("Digits Only").show().fadeOut("slow");
	        return false;
    	}
    	else{
    		$("#respRateId").css("border-color","");
     		$("#respRateId").css("box-shadow", "");
     		return true;
    	}
    });
	$("#respRateId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		var temp=$("#respRateId").val();
		
		if(parseFloat(temp)== 0){
     		$("#respRateErrmsg").html("Respiratory Rate cannot be 0.").show();
     		$("#respRateId").val('');
     	}
		else if(temp > 0 && temp < 9){
			//$("#respRateId").css("border-color","red");
     		//$("#respRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
	        $("#respRateErrmsg").html("Low RR").show();
	        $('input[name="respRateErrMsgTxt"]').val('Low RR');
	        return false;
		}
		else if(temp > 20){
			//$("#respRateId").css("border-color","red");
     		//$("#respRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
	        $("#respRateErrmsg").html("High RR").show();
	        $('input[name="respRateErrMsgTxt"]').val('High RR');
	        return false;
		}
		else{
			$("#respRateId").css("border-color","");
     		$("#respRateId").css("box-shadow", "");
     		$("#respRateErrmsg").html("");
     		$('input[name="respRateErrMsgTxt"]').val('Normal');
     		return true;
		}
	});

	$("#bmiId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57)){
	    	$("#bmiId").css("border-color","red");
     		$("#bmiId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
	        $("#bmiErrmsg").html("Values Not Allowed. Enter weight and height values to calculate BMI.").show().fadeOut("slow");
	        return false;
	   	}
    	else{
    		$("#bmiId").css("border-color","");
     		$("#bmiId").css("box-shadow", "");
     		return true;
    	}
    });
	$("#bmiId").keyup(function(){
		$("#bmiId").css("border-color","");
     	$("#bmiId").css("box-shadow", "");
	});
	
	$("#haemoglobinId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#haemoglobinId").css("border-color","red");
     		$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#haemoglobinErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#haemoglobinId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		window.patGender = $('#patAge').text().charAt(0).toUpperCase().split('/')[0];
		var temp = $("#haemoglobinId").val();

		if(window.patGender == 'F'){
			if(parseFloat(temp)==0){	
		        $("#haemoglobinErrmsg").html("Hgb. cannot be 0.").show();
		        $("#haemoglobinId").val('');
			}
			else if(parseFloat(temp) > 0 && parseFloat(temp) < 12.0){
				//$("#haemoglobinId").css("border-color","red");
	     		//$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#haemoglobinErrmsg").html("Low Hgb").show();
		        $('input[name="haemoglobinErrMsgTxt"]').val('Low Hgb');
		        return false;
			}
			else if(parseFloat(temp) > 15.5){
				//$("#haemoglobinId").css("border-color","red");
	     		//$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#haemoglobinErrmsg").html("High Hgb").show();
		        $('input[name="haemoglobinErrMsgTxt"]').val('High Hgb');
		        return false;
			}
			else{
				$("#haemoglobinId").css("border-color","");
     			$("#haemoglobinId").css("box-shadow", "");
     			$("#haemoglobinErrmsg").html("");
     			$('input[name="haemoglobinErrMsgTxt"]').val('Normal');
     			return true;
			}
		}
		else if(window.patGender == 'M'){
			if(parseFloat(temp)==0){	
		        $("#haemoglobinErrmsg").html("Hgb. cannot be 0.").show();
		        $("#haemoglobinId").val('');
			}
			else if(parseFloat(temp) < 13.5){
				//$("#haemoglobinId").css("border-color","red");
	     		//$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#haemoglobinErrmsg").html("Low Hgb").show();
		        $('input[name="haemoglobinErrMsgTxt"]').val('Low Hgb');
		        return false;
			}
			else if(parseFloat(temp) > 17.5){
				//$("#haemoglobinId").css("border-color","red");
	     		//$("#haemoglobinId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#haemoglobinErrmsg").html("High Hgb").show();
		        $('input[name="haemoglobinErrMsgTxt"]').val('High Hgb');
		        return false;
			}
			else{
				$("#haemoglobinId").css("border-color","");
     			$("#haemoglobinId").css("box-shadow", "");
     			$("#haemoglobinErrmsg").html("");
     			$('input[name="haemoglobinErrMsgTxt"]').val('Normal');
     			return true;
			}
		}
	});
	
	
	$("#curcumferenceId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#curcumferenceId").css("border-color","red");
     		$("#curcumferenceId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#curcumferenceErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	
	
	$("#muacId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#muacId").css("border-color","red");
     		$("#muacId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#muacErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	
	$("#temperatureId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#temperatureId").css("border-color","red");
     		$("#temperatureId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	        
	        $("#temperatureErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }
	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#temperatureId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		if(parseFloat($("#temperatureId").val())==0){	
	        $("#temperatureErrmsg").html("Not Allowed").show();
	        $("#temperatureId").val('');
		}
		else if(parseFloat($("#temperatureId").val())>= 96.0 && parseFloat($("#temperatureId").val())< 99.5 ){	
	        //$("#temperatureErrmsg").html("Normal Temp.").show();
	        $("#temperatureErrmsg").val('');
	        $('input[name="temperatureErrMsgTxt"]').val('Normal Temp.');
		}
		else if(parseFloat($("#temperatureId").val()) >= 99.5 &&  parseFloat($("#temperatureId").val()) < 100.9){
			//$("#temperatureId").css("border-color","red");
 			//$("#temperatureId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
        	$("#temperatureErrmsg").html("Fever").show();
        	$('input[name="temperatureErrMsgTxt"]').val('Fever');
		}
		else if(parseFloat($("#temperatureId").val()) >= 100.9){
			//$("#temperatureId").css("border-color","red");
 			//$("#temperatureId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
        	$("#temperatureErrmsg").html("High Fever").show();
        	$('input[name="temperatureErrMsgTxt"]').val('High Fever');
		}
		else{
			$("#temperatureId").css("border-color","");
	     	$("#temperatureId").css("box-shadow", "");
	     	$("#temperatureErrmsg").html("");
	     	$('input[name="temperatureErrMsgTxt"]').val('Normal');
	     	return true;
	    }
	});
	
	$("#diastolicId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#diastolicId").css("border-color","red");
     		$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#bpErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#diastolicId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		var temp=parseFloat($("#diastolicId").val());
		var x=$("#systolicId").val();
		var temp1;
		if(x == ''){
			temp1 = NaN;
		}
		else{
			temp1=parseFloat(x);
		}
		if(temp != NaN && temp1 != NaN){
			if((temp <= 60) && (temp1 <= 90)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Low BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Low BP');
		        return false;
			}
			if((temp <= 60) && (temp1 > 90)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Ideal BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
		        return false;
			}
			else if((temp > 60 && temp <= 80) && (temp1 > 90 && temp1 <= 120)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Ideal BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
		        return false;
			}
			else if((temp > 60 && temp <= 80) && (temp1 > 120 && temp1 <= 140)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage1").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage1');
		        return false;
			}
			else if((temp > 80 && temp <= 90) && (temp1 > 120 && temp1 <= 140)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage1").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage1');
		        return false;
			}
			else if((temp > 80 && temp <= 90) && temp1 > 140){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage2").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage2');
		        return false;
			}
			else if(temp > 90 && temp1 > 140){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage2").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage2');
		        return false;
			}
			else if(temp == 0){
				$("#bpErrmsg").html("Diastolic cannot be 0.").show();
				$("#diastolicId").val('');
			}
			else{
				$("#diastolicId").css("border-color","");
		     	$("#diastolicId").css("box-shadow", "");
		     	$("#bpErrmsg").html("");
		     	$('input[name="bpErrMsgTxt"]').val('Normal');
		     	return true;
			}
		}				
	});

	
	$("#diastolicId1").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		var temp=parseFloat($("#diastolicId1").val());
		var x=$("#systolicId1").val();
		var temp1;
		if(x == ''){
			temp1 = NaN;
		}
		else{
			temp1=parseFloat(x);
		}
		if(temp != NaN && temp1 != NaN){
			if((temp <= 60) && (temp1 <= 90)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Low BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Low BP');
		        return false;
			}
			if((temp <= 60) && (temp1 > 90)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Ideal BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
		        return false;
			}
			else if((temp > 60 && temp <= 80) && (temp1 > 90 && temp1 <= 120)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Ideal BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
		        return false;
			}
			else if((temp > 60 && temp <= 80) && (temp1 > 120 && temp1 <= 140)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage1").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage1');
		        return false;
			}
			else if((temp > 80 && temp <= 90) && (temp1 > 120 && temp1 <= 140)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage1").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage1');
		        return false;
			}
			else if((temp > 80 && temp <= 90) && temp1 > 140){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage2").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage2');
		        return false;
			}
			else if(temp > 90 && temp1 > 140){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage2").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage2');
		        return false;
			}
			else if(temp == 0){
				$("#bpErrmsg").html("Diastolic cannot be 0.").show();
				$("#diastolicId").val('');
			}
			else{
				$("#diastolicId").css("border-color","");
		     	$("#diastolicId").css("box-shadow", "");
		     	$("#bpErrmsg").html("");
		     	$('input[name="bpErrMsgTxt"]').val('Normal');
		     	return true;
			}
		}				
	});

	
	$("#systolicId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#systolicId").css("border-color","red");
     		$("#systolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#bpErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#systolicId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		var temp1=parseFloat($("#systolicId").val());
		var x=$("#diastolicId").val();
		var temp;
		if(x == ''){
			temp = NaN;
		}
		else{
			temp=parseFloat(x);
		}
		if(temp != NaN && temp1 != NaN){
			if((temp <= 60) && (temp1 <= 90)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Low BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Low BP');
		        return false;
			}
			if((temp <= 60) && (temp1 > 90)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Ideal BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
		        return false;
			}
			else if((temp > 60 && temp <= 80) && (temp1 > 90 && temp1 <= 120)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Ideal BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
		        return false;
			}
			else if((temp > 60 && temp <= 80) && (temp1 > 120 && temp1 <= 140)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage 1").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 1');
		        return false;
			}
			else if((temp > 80 && temp <= 90) && (temp1 > 120 && temp1 <= 140)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage 1").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 1');
		        return false;
			}
			else if((temp > 80 && temp <= 90) && temp1 > 140){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage 2").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 2');
		        return false;
			}
			else if(temp > 90 && temp1 > 140){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage 2").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 2');
		        return false;
			}
			else if(temp1==0){	
		        $("#bpErrmsg").html("Systolic cannot be 0.").show();
		        $("#systolicId").val('');
			}
			else{
				$("#diastolicId").css("border-color","");
		     	$("#diastolicId").css("box-shadow", "");
		     	$("#bpErrmsg").html("");
		     	$('input[name="bpErrMsgTxt"]').val('Normal');
		     	return true;
			}
		}
	});

	$("#systolicId1").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		var temp1=parseFloat($("#systolicId1").val());
		var x=$("#diastolicId1").val();
		var temp;
		if(x == ''){
			temp = NaN;
		}
		else{
			temp=parseFloat(x);
		}
		if(temp != NaN && temp1 != NaN){
			if((temp <= 60) && (temp1 <= 90)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Low BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Low BP');
		        return false;
			}
			if((temp <= 60) && (temp1 > 90)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Ideal BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
		        return false;
			}
			else if((temp > 60 && temp <= 80) && (temp1 > 90 && temp1 <= 120)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Ideal BP").show();
		        $('input[name="bpErrMsgTxt"]').val('Ideal BP');
		        return false;
			}
			else if((temp > 60 && temp <= 80) && (temp1 > 120 && temp1 <= 140)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage 1").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 1');
		        return false;
			}
			else if((temp > 80 && temp <= 90) && (temp1 > 120 && temp1 <= 140)){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage 1").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 1');
		        return false;
			}
			else if((temp > 80 && temp <= 90) && temp1 > 140){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage 2").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 2');
		        return false;
			}
			else if(temp > 90 && temp1 > 140){
				//$("#diastolicId").css("border-color","red");
	     		//$("#diastolicId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
		        $("#bpErrmsg").html("Hypertension Stage 2").show();
		        $('input[name="bpErrMsgTxt"]').val('Hypertension Stage 2');
		        return false;
			}
			else if(temp1==0){	
		        $("#bpErrmsg").html("Systolic cannot be 0.").show();
		        $("#systolicId").val('');
			}
			else{
				$("#diastolicId").css("border-color","");
		     	$("#diastolicId").css("box-shadow", "");
		     	$("#bpErrmsg").html("");
		     	$('input[name="bpErrMsgTxt"]').val('Normal');
		     	return true;
			}
		}
	});

	

	
	$("#fastingId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#fastingId").css("border-color","red");
     		$("#fastingId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#fastingErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#fastingId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		var temp=parseFloat($("#fastingId").val());

		if(temp==0){	
	        $("#fastingErrmsg").html("Blood Sugar Fasting cannot be 0.").show();
	        $("#fastingErrmsg").val('');
	        $("#fastingId").val('');
		}
		else if(temp < 70){
			//$("#fastingId").css("border-color","red");
     		//$("#fastingId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#fastingErrmsg").html("Low Sugar").show();
	        $('input[name="fastingErrMsgTxt"]').val('Low Sugar');
	        return false;
		}
		else if(temp >= 101 && temp < 125){
			//$("#fastingId").css("border-color","red");
     		//$("#fastingId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#fastingErrmsg").html("Pre Diabetes").show();
	        $('input[name="fastingErrMsgTxt"]').val('Pre Diabetes');
	        return false;
		}
		else if(temp >= 125){
			//$("#fastingId").css("border-color","red");
     		//$("#fastingId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#fastingErrmsg").html("HighDiabetes").show();
	        $('input[name="fastingErrMsgTxt"]').val('HighDiabetes');
	        return false;
		} 
		else{
			$("#fastingId").css("border-color","");
     		$("#fastingId").css("box-shadow", "");
     		$("#fastingErrmsg").html("");
     		$('input[name="fastingErrMsgTxt"]').val('Normal');
     		return true;
		}
	});

	$("#ppRateId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#ppRateId").css("border-color","red");
     		$("#ppRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#ppRateErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#ppRateId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		var temp=parseFloat($("#ppRateId").val());

		if(temp==0){	
	        $("#ppRateErrmsg").html("PP Blood Sugar Value cannot be 0.").show();
	        $("#ppRateErrmsg").val('');
	        $("#ppRateId").val('');
		}
		else if(temp < 70){
			//$("#ppRateId").css("border-color","red");
     		//$("#ppRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#ppRateErrmsg").html("Low Sugar").show();
	        $('input[name="ppRateErrMsgTxt"]').val('Low Sugar');
	        return false;
		}
		else if(temp >= 141 && temp < 200){
			//$("#ppRateId").css("border-color","red");
     		//$("#ppRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#ppRateErrmsg").html("PreDiabetes").show();
	        $('input[name="ppRateErrMsgTxt"]').val('PreDiabetes');
	        return false;
		}
		else if(temp >= 200){
			//$("#ppRateId").css("border-color","red");
     		//$("#ppRateId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#ppRateErrmsg").html("HighDiabetes").show();
	        $('input[name="ppRateErrMsgTxt"]').val('HighDiabetes');
	        return false;
		} 
		else{
			$("#ppRateId").css("border-color","");
     		$("#ppRateId").css("box-shadow", "");
     		$("#ppRateErrmsg").html("");
     		$('input[name="ppRateErrMsgTxt"]').val('Normal');
     		return true;
		}
	});

	$("#hba1cId").keypress(function (e) {
		var self = $(this);
	   	self.val(self.val().replace(/[^0-9\.]/g, ''));
	   	if ((e.which != 46 || self.val().indexOf('.') != -1) && (e.which < 48 || e.which > 57) &&
		       (e.which != 0 && e.which != 8)){
     		$("#hba1cId").css("border-color","red");
     		$("#hba1cId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#hba1cErrmsg").html("Numbers Only").show().fadeOut("slow");
	        return false;
    	}
    	var text = $(this).val();
	    if ((e.which == 46) && (text.indexOf('.') == -1)) {
	        setTimeout(function() {
	            if ($this.val().substring($this.val().indexOf('.')).length > 3) {
	                $this.val($this.val().substring(0, $this.val().indexOf('.') + 3));
	            }
	        }, 1);
	    }

	    if ((text.indexOf('.') != -1) &&
	        (text.substring(text.indexOf('.')).length > 2) &&
	        (e.which != 0 && e.which != 8) &&
	        ($(this)[0].selectionStart >= text.length - 2)) {
	            event.preventDefault();
	    }
    });
	$("#hba1cId").keyup(function(){
		if($(this).val().replace(/^(0+)/g, '')){
			$(this).val($(this).val().replace(/^(0+)/g, ''));
		}
		var temp=parseFloat($("#hba1cId").val());

		if(temp==0){	
	        $("#hba1cErrmsg").html("HBA1C Value cannot be 0.").show();
	        $("#hba1cErrmsg").val('');
	        $("#hba1cId").val('');
		}
		else if(temp >= 5.7 && temp <= 6.4){
			//$("#hba1cId").css("border-color","red");
     		//$("#hba1cId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#hba1cErrmsg").html("PreDiabetes").show();
	        $('input[name="hba1cErrMsgTxt"]').val('PreDiabetes');
	        return false;
		}
		else if(temp >= 6.5){
			//$("#hba1cId").css("border-color","red");
     		//$("#hba1cId").css("box-shadow", "0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(255, 0, 0, 0.6)");	
	        $("#hba1cErrmsg").html("HighDiabetes").show();
	        $('input[name="hba1cErrMsgTxt"]').val('HighDiabetes');
	        return false;
		} 
		else{
			$("#hba1cId").css("border-color","");
     		$("#hba1cId").css("box-shadow", "");
     		$("#hba1cErrmsg").html("");
     		$('input[name="hba1cErrMsgTxt"]').val('Normal');
     		return true;
		}
	});

	$('#weightId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});
	$('#heightId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});
	$('#haemoglobinId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});
	$('#diastolicId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});
	$('#systolicId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});
	$('#fastingId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});
	$('#ppRateId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});
	$('#hba1cId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});
	$('#temperatureId').bind("paste", function(e) {
		var text = e.originalEvent.clipboardData.getData('Text');
		if ($.isNumeric(text)) {
		    if ((text.substring(text.indexOf('.')).length > 3) && (text.indexOf('.') > -1)) {
		        e.preventDefault();
		        $(this).val(text.substring(0, text.indexOf('.') + 3));
		   }
		}
		else {
		        e.preventDefault();
		     }
	});

	$('#haemoglobin').tooltip({
        placement:"top"
    });
    $('#respRate').tooltip({
        placement:"top"
    });
    $('#VitalsPrintBtn').tooltip({
        placement:"top"
    });
    $('#bloodPressure').tooltip({
        placement:"top"
    });
    $('#hba1c').tooltip({
        placement:"top"
    });
    $('#ppRate').tooltip({
        placement:"top"
    });
    $('#fasting').tooltip({
        placement:"top"
    });
    $('#bloodSugarId').tooltip({
        placement:"top"
    });

    $("#VitalsPrintBtn").on('click',function(){
		$('.close').hide();
		$('#VitalsPrintBtn').hide();
		$('#saveBtn').hide();
		$('.opdBayHeaderTwo').hide();
		$('[data-toggle="tooltip"]').tooltip("hide");
		window.print();
		$('.close').show();
		$('#VitalsPrintBtn').show();
		$('#saveBtn').show();
		$('.opdBayHeaderTwo').show();
		return false;
	});

    $(window).bind("resize", function () {
	 	if($(window).width() < 992){
		  $('#bloodSugarDivId').removeClass('alignRight');
		}
	}).trigger('resize');

	$(window).bind("resize", function () {
	 	if($(window).width() >= 992){
		  $('#bloodSugarDivId').addClass('alignRight');
		  $('#bloodPressureDivId').addClass('alignRight');
		}
	}).trigger('resize');

	$("input[id='temperatureId']").attr("maxlength", "5");
	$("input[id='diastolicId']").attr("maxlength", "5");
	$("input[id='systolicId']").attr("maxlength", "5");
	$("input[id='respRateId']").attr("maxlength", "2");
	$("input[id='haemoglobinId']").attr("maxlength", "5");
	$("input[id='pulseId']").attr("maxlength", "3");
	$("input[id='fastingId']").attr("maxlength", "5");
	$("input[id='ppRateId']").attr("maxlength", "5");
	$("input[id='hba1cId']").attr("maxlength", "4");

	});



/*
 *  Function Not in Use
 * */

function showvital(e){  
			//alert('showvital');
			var patName = $(e).closest('.patientListBlock').find('.patName').text();
			var patCrNo = $(e).closest('.patientListBlock').find('.patCrNo').text();
			var patEpisodeVisitNo = $(e).closest('.patientListBlock').find('.patEpisodeVisitNo').text();
			var patLastVisitDate = $(e).closest('.patientListBlock').find('.patLastVisitDate').text()==='' ? 'First Visit' : $(e).closest('.patientListBlock').find('.patLastVisitDate').text(); //Changed By Timsi Kataria as suggested by Priyesh Sir
			var patVisitType = $(e).closest('.patientListBlock').find('.patVisitType').text();
			var patGaurdianName = $(e).closest('.patientListBlock').find('.patGaurdianName').text(); 
			var patDeptName = $(e).closest('.patientListBlock').find('.patDeptUnit').text();
			var nextPatName = $(e).closest('.patientListBlock').next().find('.patName').text(); 
			var prevPatName = $(e).closest('.patientListBlock').prev().find('.patName').text();
			var patGenAgeCat = $(e).closest('.patientListBlock').find('.patGenAgeCat').text();
			var episodeCode = $(e).closest('.patientListBlock').find('.patEpisodeCode').text();
			var hospitalCode = $(e).closest('.patientListBlock').find('.patHospitalCode').text();
			var seatId = $(e).closest('.patientListBlock').find('.patSeatId').text(); 
			var patConsultantName = $(e).closest('.patientListBlock').find('.patConsultantName').text();  
			var patCompleteGeneralDtl = $(e).closest('.patientListBlock').find('input[name=patCompleteGeneralDtl]').val();
			console.log('showPrescription::>>> '+patName+patCrNo+patEpisodeVisitNo+patLastVisitDate);
			/* alert('frst::'+episodeCode); */
			var prescMode = $('input[name=prescMode]:checked').val(); 
	        console.log('Presc in showPrescription() : '+prescMode);

	        console.log('Presc in patCrNo() : '+patCrNo);
	        console.log('Presc in patEpisodeVisitNo() : '+patEpisodeVisitNo);
	        
			if(prescMode==1) //Not in use !! Do not comment.
			{	console.log('tile mode presc'+prescMode);
				inTilePresc(e, patName, patCrNo, episodeCode, patGenAgeCat, patCompleteGeneralDtl); //Not in use !! Do not comment.
			}
			else if(prescMode==2)
	        { console.log('page mode presc'+prescMode);
				$('.showPatientDtl').remove();
				$('.patientListBlock').attr('clicked','0');   //Not in use !! Do not comment.
				$('.rightPanel').append(str);
				$('.leftPanelHeader').toggleClass('col-sm-12 hideCls');
				$('.leftPanel').toggleClass('col-sm-12 hideCls');
				$('.prescriptionColShow').hide('slow');  //Not in use !! Do not comment.
				$('.mainTopHeader').hide();
				$('.rightPanel').parent().append('<h2 id="rightPanelLoadMsgId" style="margin-top:20%; margin-left:5%"><i class="fa fa-spinner fa-spin"></i> Loading Patient Prescription....</h3>');
				 $.ajax({
					url: '/HISDRDESK/new_opd/Opdvital.jsp',
					async:true,
					success: function(result){
						//alert(result);
						$('.rightPanel .showPatientDtl').html(result);
						//$('.showPatientDtl').remove();
							$('#patNamePrescriptionPanel').text(patName);
				      		$('#patCrNoPrescriptionPanel').text(patCrNo);
				      		$('#patSummaryTileImg').attr('src',$(e).closest('.patientListBlock').find('.patProfileImg').attr('src'));
				      		$('#patEpisodeVisitNoPrescriptionPanel').text(patEpisodeVisitNo);
				      		$('#patLastVisitDatePrescriptionPanel').text(patLastVisitDate);
				      		$('#patVisitTypePrescriptionPanel').text(patVisitType);
				      		$('#prescriptionPanelPatStatus').text($(e).closest('.patientListBlock').hasClass('isAttended_1')?'':'Attended').css('color',$(e).closest('.patientListBlock').hasClass('isAttended_1')?'#1bbf23':'red');
				      		$('#patGaurdianNamePrescriptionPanel').text(patGaurdianName);
				      		$('#patDeptName').text(patDeptName);
				      		$('#patGenAgeCatPrescriptionPanel').html(patGenAgeCat);
				      		$('#patHospitalCodePrescriptionPanel').html(hospitalCode); 
				      		$('#patSeatIdPrescriptionPanel').html(seatId); 
				      		$('#patConsultantNamePrescriptionPanel').html(patConsultantName); 
				      		if(nextPatName.trim()!='')
				      		$('#nextPatNamePrescriptionPanel').html(nextPatName.split("  ")[0].length>3 ? '('+nextPatName.split("  ")[0]+')' : '('+nextPatName.split("  ")[0]+' '+nextPatName.split("  ")[1]+')'); 
				      	  	if(prevPatName.trim()!='')
				      		$('#prevPatNamePrescriptionPanel').html(prevPatName.split("  ")[0].length>3 ? '('+prevPatName.split("  ")[0]+')' : '('+prevPatName.split("  ")[0]+' '+prevPatName.split("  ")[1]+')'); 
				      		$('#patEpisodeCodePrescriptionPanel').text(episodeCode);
				      		$('input[name=patCompleteGeneralDtlPrescriptionPanel]').val(patCompleteGeneralDtl);
				      		$('input[name=patGeneralDtl]').val(patName+'#'+patCrNo+'#'+episodeCode+'#'+patGenAgeCat);
							$('.rightPanel').show();
							$('body').css('padding-top','0px');
							$('body .container-fluid:first-child').css('margin','0px');
							count=0;
							$('.patientListBlock').each(function(index){
								console.log('patientListBlock::::>>>>> '+count); 
								if($(this).find('.patCrNo').text().trim() === $('#patCrNoPrescriptionPanel').text().trim())
									return false;
									count++;
								}); 	
							$('#rightPanelLoadMsgId').remove();
						}
					}); 
				
			}
			else if(prescMode==3)//Not in use !! Do not comment.
	        { console.log('modal mode presc'+prescMode);
				$('.showPatientDtl').remove();
				$('.patientListBlock').attr('clicked','0'); 
				$('.prescModalBody').append(str);
	      		$('.prescModalBody .showPatientDtl').load('/HISDRDESK/new_opd/prescription.jsp', function(){
			      		$('#patNamePrescriptionPanel').text(patName);
			      		$('#patCrNoPrescriptionPanel').text(patCrNo);
			      		$('#patGenAgeCatPrescriptionPanel').text(patGenAgeCat);
			      		$('#patEpisodeCodePrescriptionPanel').text(episodeCode);
			      		$('input[name=patCompleteGeneralDtlPrescriptionPanel]').val(patCompleteGeneralDtl);
			      		$('input[name=patGeneralDtl]').val(patName+'#'+patCrNo+'#'+episodeCode+'#'+patGenAgeCat);
		      		});   
				$('#prescModalTriggerBtn').click();
			}  
	}
