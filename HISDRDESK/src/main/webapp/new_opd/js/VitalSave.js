function VitalSave(e){
	
	console.log('Inside VaitalSave Method');
	console.log($("#weightId").val());
	var patName = $(e).closest('.patientListBlock').find('.patName').text();
   
    var itrremks='';
	console.log('patName'+patName);
	var weightId=$("#weightId").val();
	var heightId=$("#heightId").val();
	var bmiId=$("#bmiId").val();
	var temperatureId=$("#temperatureId").val();
	var respRateId=$("#respRateId").val();
	var haemoglobinId=$("#haemoglobinId").val();
	var diastolicId=$("#diastolicId").val();
	var systolicId=$("#systolicId").val();
	var fastingId=$("#fastingId").val();
	var ppRateId=$("#ppRateId").val();
	var hba1cId=$("#hba1cId").val();
	
	
	var deptId=$("#strPatientDtlId").val();
	var patdtlsId=$("#strPatHiddenDtlsId").val();
	
	var bmiErrmsg=$("#bmiErrmsg").text();
	var temperatureErrmsg=$("#temperatureErrmsg").text();
	var respRateErrmsg=$("#respRateErrmsg").text();
	var haemoglobinErrmsg=$("#haemoglobinErrmsg").text();
	var bpErrmsg=$("#bpErrmsg").text();
	var fastingErrmsg=$("#fastingErrmsg").text();
	var ppRateErrmsg=$("#ppRateErrmsg").text();
	var hba1cErrmsg=$("#hba1cErrmsg").text();
	var pulseRateId=$("#pulseRateId").val() =='' ? '0' : $("#pulseRateId").val();
	var bloodGroupId=$("#bloodGroupId").val()=='' ? '0' : $("#bloodGroupId").val();
	/*##########   added for cancer screening   ##########*/
	//var cancerScreeningId=$("#cancerScreeningId").val()=='' ? '0' : $("#cancerScreeningId").val();
	//var cancerScreeningId = cancerScreeningId.replaceAll("\\[|\\]", "")
	var cancerScreeningId = $("#cancerScreeningId").val()=='' ? '0' :$("#cancerScreeningId").val().toString().replaceAll("[\\[\\]]", "").replaceAll("\"", "");
   			
	
	var Disability=$('input[name="Disability"]:checked').val();
	
	console.log('DisabilityDisabilityDisabilityDisability   '+Disability);
	if(Disability == undefined){
		Disability='';	
	}
	
	console.log('DisabilityDisabilityDisabilityDisability   '+Disability);
	
	var Smoking=$('input[name="Smoking"]:checked').val();
	if(Smoking == undefined){
		Smoking='';	
	}
	var Anemic=$('input[name="Anemic"]:checked').val();
	
	if(Anemic == undefined){
		Anemic='';	
	}
	
	var Pregnancy=$("#PregnancyId").val() =='' ? '' : $("#PregnancyId").val();
	var diastolicId1=$("#diastolicId1").val()=='' ? '0' : $("#diastolicId1").val();
	var systolicId1=$("#systolicId1").val()=='' ? '0' : $("#systolicId1").val();
	console.log("bmiErrmsg::::::::::"+bmiErrmsg);
	var patGenAgeCat=$('#patAge').html();
	var curcumferenceId=$("#curcumferenceId").val()=='' ? '0' : $("#curcumferenceId").val();
	var muacId=$("#muacId").val()=='' ? '0' : $("#muacId").val(); 
	console.log(patGenAgeCat);
	var Pregnancy="";
	if(patGenAgeCat.split('/')[0] == 'M')
	 Pregnancy="";
	else
	 Pregnancy=$("#PregnancyId").val() =='' ? '' : $("#PregnancyId").val();
	
	if(bmiErrmsg != '')
		itrremks +=bmiErrmsg+',' ;
	if(temperatureErrmsg != '')
		itrremks +=temperatureErrmsg+',' ;
	if(respRateErrmsg != '')
		itrremks +=respRateErrmsg+',' ;
	if(haemoglobinErrmsg != '')
		itrremks +=haemoglobinErrmsg+',' ;
	if(fastingErrmsg != '')
		itrremks +=fastingErrmsg+',' ;
	if(ppRateErrmsg != '')
		itrremks +=ppRateErrmsg+',' ;
	if(hba1cErrmsg != '')
		itrremks +=hba1cErrmsg+',' ;
	
	//itrremks=bmiErrmsg+',' +temperatureErrmsg+' ,'+respRateErrmsg+' ,'+haemoglobinErrmsg+' , '+bpErrmsg+' , '+fastingErrmsg+' '+ppRateErrmsg+ ' ,'+hba1cErrmsg ;
	
	//var symptomsId=$("#symptomsId").val().replace(",", " ")+ ',' + itrremks.replace(",", " ") ;
	var symptomsId=$("#symptomsId").val() ; //+ itrremks ;  //.replace(/,(?=[^,]*$)/, '')
	
	console.log('symptomsIdsymptomsIdsymptomsIdsymptomsId'+symptomsId);
	console.log('patdtlsId'+patdtlsId);
	
	var vitalJSON = { 
			'strWeight' : weightId,
			'strHeight' : heightId,
			'strBmid' : bmiId,
			'strTempreature' : temperatureId,
			'strrespRate' : respRateId,
			'strhaemoglobin' : haemoglobinId,
			'strdiastolic' : diastolicId,
			'strsystolic' : systolicId,
			'strfasting' : fastingId,
			'strRateId' : ppRateId,
			'strhba1c' : hba1cId,
			'strsymptoms' : symptomsId,
			'strPatdtls' :patdtlsId,
			'strGeneral' :deptId , 
			'strbmiErrmsg' : bmiErrmsg,
			'strtemperatureErrmsg' : temperatureErrmsg,
			'strrespRateErrmsg' : respRateErrmsg,
			'strhaemoglobinErrmsg' : haemoglobinErrmsg,
			'strbpErrmsg' : bpErrmsg,
			'strfastingErrmsg' : fastingErrmsg,
			'strppRateErrmsg' : ppRateErrmsg,
			'strhba1cErrmsg' : hba1cErrmsg ,
			'strpulseRate' : pulseRateId , 
			'strbloodGroup' : bloodGroupId ,
			'strmuac'	:	muacId,
			'strcurcumference' :curcumferenceId ,
			'strDisability'	   :	Disability,
			'strSmoking'			: Smoking,
			'strAnemic'			:	Anemic ,
			'strPregnancy'			:Pregnancy ,
			'strdiastolic1' : 	diastolicId1,
			'strsystolic1' : 	systolicId1 ,
			'strcancerScreening' : cancerScreeningId /*--- added for cancer screening------*/
	};
	
	var data = JSON.stringify(vitalJSON); 
	console.log(data); 
	$.ajax({url:'/HISDRDESK/services/restful/DrDesk/saveVital/',type:'POST',data:{JsonData:data},success:function(result)
    	{
			console.log(result);
			
			if(result == 1)
				{
				
				 $.alert({
				        title: 'Success!',
				        content: 'Data Saved successfully!!',
				    });
				 console.log('Vital Data Saved successfully');
				
				 
					 if( $("#strVitalModifyId").val() == 1)
						 {
						    $("#weightIdValue").text(weightId +' kgs');
							$("#heightIdValue").text(heightId+' cms');
							$("#bmiIdValue").text(bmiId);
							$("#bloodPressureIdValue").text(systolicId+'/'+diastolicId+' mm/HG'); //bloodPressureIdValue
							//$("#bloodPressureIdValue1").text(systolicId1+'/'+diastolicId1+' mm/HG');
							$("#temperatureIdValue").text(temperatureId+' F');
							$("#respRateIdValue").text(respRateId+' br/m');
							$("#haemoglobinIdValue").text(haemoglobinId+' gm/dL');
							$("#fastingIdValue").text(fastingId+' mg/dL');
							$("#ppRateIdValue").text(ppRateId+' mg/dL');
							$("#hba1cIdValue").text(hba1cId+' %');
							$("#otherInfoIdValue").text(symptomsId.replace(",", " "));
							$("#bmiIdValue").attr('data-original-title', bmiErrmsg);
							
							$("#bloodPressureIdValue").attr('data-original-title', bpErrmsg);
							$("#respRateIdValue").attr('data-original-title', respRateErrmsg);
							$("#fastingIdValue").attr('data-original-title', fastingErrmsg);
							$("#hba1cIdValue").attr('data-original-title', hba1cErrmsg);
							$("#temperatureIdValue").attr('data-original-title', temperatureErrmsg);
							$("#pulserateIdValue").text(pulseRateId+' be/m');
							$("#curcumferenceIdValue").text(curcumferenceId+' cms');
							$("#muacIdvalue").text(muacId+' cms');
							
							/*$("#DisabilityIdvalue").text(Disability);
							$("#SmokingIdvalue").text(Smoking);
							$("#AnemicIdvalue").text(Anemic);
							$("#PregnancyIdvalue").text(Pregnancy);*/
							
							 $('input:radio[name=Disability][value="'+Disability+'"]').click();
							 $('input:radio[name=Smoking][value="'+Smoking+'"]').click();
							 $('input:radio[name=Anemic][value="'+Anemic+'"]').click();
							 //$('input:radio[name=Pregnancy][value="'+Pregnancy+'"]').click();
							 
							//if(bloodGroupId =! '0' && bloodGroupId != '')
							$("#bloodgroupIdValue").text(bloodGroupId);
							/*----added for cancer screening--------*/
							$("#cancerScreeningIdValue").text(cancerScreeningId);
							var strVitalsChart='';
							if(weightId != '' && weightId !='0' && weightId !='0.00' ){
								strVitalsChart+='Weight : '+weightId +'kgs,';
								$("#weightpId").show();
							}
							else
								$("#weightpId").hide();
							
							if(heightId != '' && heightId !='0' && heightId !='0.00' ){
								strVitalsChart+=' Height : ' +heightId+'cms,';
								$("#heightpId").show();
							}
							else
								$("#heightpId").hide();
							
							if(bmiId != '' && bmiId !='0'  && bmiId !='0.0'  && bmiId !='0.00' ){
								strVitalsChart+=' BMI : ' + bmiId+'cms,';
								$("#bmipId").show();
							}
							else
								$("#bmipId").hide();
							
							if(systolicId != '' && diastolicId != '' && systolicId !='0' && diastolicId !='0'  && systolicId !='0.0' && diastolicId !='0.0' ){
								strVitalsChart+=' BP :'+ systolicId+'/'+diastolicId + 'mm/HG,';
								$("#bppid").show();
							}
							else
								$("#bppid").hide();
							
							if(systolicId1 != '' && diastolicId1 != '' && systolicId1 !='0' && diastolicId1 !='0'  && systolicId1 !='0.0' && diastolicId1 !='0.0' ){
								strVitalsChart+=' BP :'+ systolicId1+'/'+diastolicId1 + 'mm/HG,';
								$("#bppid").show();
							}
							else
								$("#bppid").hide();
							
							if(temperatureId != '' && temperatureId !='0' && temperatureId !='0.0' )
							{	
								strVitalsChart+=' Temperature : ' + temperatureId+'F,';
								$("#temppid").show();
							}
							else
								$("#temppid").hide();
							
							if(respRateId != '' && respRateId !='0'  && respRateId !='0.0'   && respRateId !='0.00' ){
								strVitalsChart+=' Respiration Rate : ' + respRateId+'br/m,';
								$("#rrpid").show();		
							}else
								$("#rrpid").hide();	
							
							if(haemoglobinId != '' && haemoglobinId !='0' && haemoglobinId !='0.0' && haemoglobinId !='0.00' ){
								strVitalsChart+=' Hemoglobin : ' + haemoglobinId+'gm/dL,';
								$("#hgbpid").show();
							}else
								$("#hgbpid").hide();
							
							if(fastingId != '' && fastingId !='0' && fastingId !='0.0' && fastingId !='0.00' ){
								strVitalsChart+=' B.S. Fast : ' + fastingId+'mg/dL,';
								$("#bsfastpid").show();
							}else
								$("#bsfastpid").hide();
							
							if(ppRateId != '' && ppRateId !='0'  && ppRateId !='0.0'  && ppRateId !='0.00'){
								strVitalsChart+=' PP : ' + ppRateId+'mg/dL,';
								$("#bspppid").show();
							}else
								$("#bspppid").hide();
							
							if(hba1cId != '' && hba1cId !='0' && hba1cId !='0.0' && hba1cId !='0.00'  ){
								strVitalsChart+=' HBA1C : ' + hba1cId+' %,';
								$("#hba1cpid").show();
							}else
								$("#hba1cpid").hide();
							
							/*if(hba1cId != '' && hba1cId !='0' )
								strVitalsChart+=' HBA1C : ' + hba1cId+'mg/dl,';*/
							
							if(pulseRateId != '' && pulseRateId !='0'  && pulseRateId !='0.00'  && pulseRateId !='0.0' ){
									strVitalsChart+=' Pulse Rate : ' + pulseRateId+'be/m,';
									$("#pulseratepid").show();
							}else
								$("#pulseratepid").hide();
							
							if(curcumferenceId != '' && curcumferenceId !='0'  && curcumferenceId !='0.0'  && curcumferenceId !='0.00' ){
								strVitalsChart+=' Head Circumference : ' + curcumferenceId+'cms,';
								$("#curcumferencepid").show();
							}
							else
								$("#curcumferencepid").hide();
							
							if(muacId != '' && muacId !='0' && muacId !='0.0' && muacId !='0.00' ){
								strVitalsChart+=' MUAC : ' + muacId+'cms,';
								$("#muacpid").show();
							}else
								$("#muacpid").hide();
							
							if(bloodGroupId != '' && bloodGroupId !='0' && bloodGroupId !='0.0'  && bloodGroupId !='0.00'){
								strVitalsChart+=' Blood Group : ' + bloodGroupId+',';
								$("#bloodgrouppid").show();
							}else
								$("#bloodgrouppid").hide();
							
							/*-----------------Added for Cancer Screening---------------------*/
								if(cancerScreeningId != '' && cancerScreeningId !='0' && cancerScreeningId !='0.0'  && cancerScreeningId !='0.00'){
									strVitalsChart+=' Cancer Screening : ' + cancerScreeningId+',';
									$("#cancerscreeninggid").show();
								}else
									$("#cancerscreeninggid").hide();
							
						
							
							
							if(Disability != ''){
								strVitalsChart+=' Disability : ' + Disability+',';
							$("#DisabilityIdvalue").text(Disability);
							}
							else
								$("#Disabilityid").hide();
							
							if(Smoking != ''){
								strVitalsChart+=' Smoking : ' + Smoking+',';	
							$("#SmokingIdvalue").text(Smoking);
							}
							else
								$("#Smokingid").hide();
							
							if(Anemic != ''){
								strVitalsChart+=' Anemic : ' + Anemic+',';
							$("#AnemicIdvalue").text(Anemic);
							}
							else
								$("#Anemicid").hide();
							
							if(Pregnancy != '' && Pregnancy != '0'){
							
							$("#PregnancyIdvalue").text(Pregnancy+' Wk');
							}
							else
								$("#Pregnancyid").hide();
							
							if(Pregnancy != '' && Pregnancy != '0'){
							if(patGenAgeCat.split('/')[0] == 'F')
								strVitalsChart+=' Pregnancy : ' + Pregnancy+' Wk,';
							}
							strVitalsChart =strVitalsChart.replace(/.$/,"")
							if(symptomsId != '' && symptomsId !='--' ){
								strVitalsChart+='  Interpretation Remarks : ' + symptomsId.replace(/,+/g,' ')+',';
								$("#pidremarks").show();
							}else
								$("#pidremarks").hide();
							
							console.log('vitalhiddenval::::'+strVitalsChart);
							//strVitalsChart=strVitalsChart.replace(/,+/g,',');
							//var vitalhiddenval= 'Weight : '+item.HOPLNUM_WEIGHT_VAL +'Kg.,' + ' Height : ' +item.HOPLNUM_HEIGHT_VAL+'CM,'+ ' BMI : ' + item.HOPLNUM_BMI_VAL+'CM,' +' BP :'+ item.HOPLNUM_BPSY_VAL+'/'+item.HOPLNUM_BPDIASY_VAL 'g/dl' +' Temperature : '+ item.HOPLNUM_TEMP_VAL+'F' + item.HOPLNUM_RR_VAL+''  + item.HOPLNUM_HB_VAL + item.HOPLNUM_SUGARFAST_VAL + item.HOPLNUM_SUGARPP_VAL + item.HOPLNUM_HBA1C_VAL
							$("#vitalHiddenValId").val(strVitalsChart);
							//$("#temperatureIdValue").attr('data-original-title', ppRateErrmsg);
							//$("#ClassRangeIdValue").text(''+bmiErrmsg + ' , ' + respRateErrmsg+ ' , '+ bpErrmsg + ' , '+ fastingErrmsg + ' , '+hba1cErrmsg + ' '+ ' , '+temperatureErrmsg + ' , ' +ppRateErrmsg);
						 }

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
 						 $("#curcumferenceId").val('');
 						 $("#muacId").val('');
 						 //$("#bloodGroupId").val('');
					 
				 $("#VitalIDModal").modal('hide');
				 
				 //$("#VitalIDModal").modal();
				}else{
					console.log('Error!!!');
					 $.alert({
					        title: 'Error!',
					        content: 'Unable to Save Data!!',
					    });
				}
    	}
	});
}


function ModifyVital(e)
{
	console.log('Inside ModifyVital');
	var CR_No = $('#patCrNoPrescriptionPanel').text();  
	var episodeCode = $('#patEpisodeCodePrescriptionPanel').text();
	var hospitalCode = $('#patHospitalCodePrescriptionPanel').text();
	var lastVisitDate = $('#patLastVisitDatePrescriptionPanel').text();
	var visitNo=$('#patEpisodeVisitNoPrescriptionPanel').text();
	var patname=$('#patNamePrescriptionPanel').text();
	var patGenAgeCat=$('#patGenAgeCatPrescriptionPanel').html();
	var seatId = $('#patSeatIdPrescriptionPanel').text();
	
	var vitalJSON = { 
			'CR_No' : CR_No,
			'episodeCode' : episodeCode,
			'hospitalCode' : hospitalCode,
			'visitNo' : visitNo,
			
	};
	var data = JSON.stringify(vitalJSON);
	$("#VitalIDModal").modal();
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
			
	console.log(CR_No+'=='+episodeCode+'=='+hospitalCode+'=='+lastVisitDate+'=='+visitNo+'=='+patname+'=='+seatId);
	$("#patName").text(patname);
	$("#crNo").text(CR_No);
	$("#patAge").text(patGenAgeCat);
	$("#patGender").text('');
	//$('input[name=patGeneralDtl]').val(patname+'#'+CR_No+'#'+episodeCode+'#'+patGenAgeCat);
	$.ajax({url:'/HISDRDESK/services/restful/DrDesk/ModifyVital/',type:'POST',data:{JsonData:data},success:function(result)
    	{ 
			console.log('result '+result);
			console.log('result.status '+result.status);
			console.log('result.VitalDtls '+result.VitalDtls);
			
			if(result.status == 1  )
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
					// console.log(' item.HOPLSTR_SYMPINFO_VAL '+item.HOPLSTR_SYMPINFO_VAL);
						$("#bmiId").val(item.HOPLNUM_BMI_VAL);
						$("#temperatureId").val(item.HOPLNUM_TEMP_VAL);
						$("#respRateId").val(item.HOPLNUM_RR_VAL);
						$("#haemoglobinId").val(item.HOPLNUM_HB_VAL);
						$("#diastolicId").val(item.HOPLNUM_BPDIASY_VAL);
						$("#systolicId").val(item.HOPLNUM_BPSY_VAL);
						$("#fastingId").val(item.HOPLNUM_SUGARFAST_VAL);
						$("#ppRateId").val(item.HOPLNUM_SUGARPP_VAL);
						$("#hba1cId").val(item.HOPLNUM_HBA1C_VAL);
						//$("#symptomsId").val(item.HOPLSTR_SYMPINFO_VAL);
						$("#symptomsId").val(item.HOPLSTR_SYMPINFO_VAL.replace(/,/g, '')); // uncommented for remarks check by ashutoshk
						 $('#bmiErrmsg').text(item.HOPLSTR_BMI_CLASSNAME);
						 $('#temperatureErrmsg').text(item.HOPLSTR_TEMPRETURE_CLASSNAME);
						 $('#bpErrmsg').text(item.HOPLSTR_BP_CLASSNAME);
						 $('#fastingErrmsg').text(item.HOPLSTR_DIABETIC_CLASSNAME);
						 $('#ppRateErrmsg').text(item.HOPLSTR_SUGARPP_CLASSNAME);
						 $('#hba1cErrmsg').text(item.HOPLSTR_HBA1CCLASSNAME);
						 $('#respRateErrmsg').text(item.HOPLSTR_RR_CLASSNAME);
						 $("#pulseRateId").val(item.HOPLNUM_PULSE_RATE);
						 $("#bloodGroupId").val(item.HOPLSTR_BLOOD_GROUP);
						 /*--------------added for cancer screening--------------*/
						 $("#cancerScreeningId :selected").val(item.HOPLSTR_CANCER_SCREENING);
						 	
						 $("#curcumferenceId").val(item.HOPLNUM_CURCUMFERENCE_VAL);
						 $("#muacId").val(item.HOPLNUM_MUAC_VAL);
						 
						 $('input:radio[name=Disability][value="'+item.HOPLSTR_DISABILITY+'"]').click();
						 $('input:radio[name=Smoking][value="'+item.HOPLSTR_SMOKING+'"]').click();
						 $('input:radio[name=Anemic][value="'+item.HOPLSTR_ANEMIC+'"]').click();
						 //$('input:radio[name=Pregnancy][value="'+item.HOPLSTR_PREGNANCY+'"]').click();
						 $("#diastolicId1").val(item.HOPLNUM_BPDIASY_VAL1);
						 $("#systolicId1").val(item.HOPLNUM_BPSY_VAL1);
						 $("#PregnancyId").val(item.HOPLSTR_PREGNANCY);
						 /*	$("#DisabilityIdvalue").val(item.HOPLSTR_DISABILITY);
							$("#SmokingIdvalue").val(item.HOPLSTR_SMOKING);
							$("#AnemicIdvalue").val(item.HOPLSTR_ANEMIC);
							$("#PregnancyIdvalue").val(item.HOPLSTR_PREGNANCY);*/
							
						/* $("#Disability").val(item.HOPLSTR_DISABILITY);
						 $("#muacId").val(item.HOPLSTR_SMOKING);
						 $("#muacId").val(item.HOPLSTR_ANEMIC);
						 $("#muacId").val(item.HOPLSTR_PREGNANCY);*/
						 $("#strPatHiddenDtlsId").val(''+'^'+CR_No+'^'+episodeCode+'^'+visitNo+'^'+hospitalCode+'^'+''+'^'+''+'^'+seatId);
						 
						 var strVitalsChart='';
							if(item.HOPLNUM_WEIGHT_VAL != '' && item.HOPLNUM_WEIGHT_VAL !='0' )
								strVitalsChart+='Weight : '+item.HOPLNUM_WEIGHT_VAL +'kgs,';
							
							if(item.HOPLNUM_HEIGHT_VAL != '' && item.HOPLNUM_HEIGHT_VAL !='0' )
								strVitalsChart+=' Height : ' +item.HOPLNUM_HEIGHT_VAL+'cms,';
							
							if(item.HOPLNUM_BMI_VAL != '' && item.HOPLNUM_BMI_VAL !='0' )
								strVitalsChart+=' BMI : ' + item.HOPLNUM_BMI_VAL+'cms,';
							
							if(item.HOPLNUM_BPSY_VAL != '' && item.HOPLNUM_BPDIASY_VAL != '' && item.HOPLNUM_BPSY_VAL !='0' && item.HOPLNUM_BPDIASY_VAL !='0' )
								strVitalsChart+=' BP :'+ item.HOPLNUM_BPSY_VAL+'/'+item.HOPLNUM_BPDIASY_VAL + 'mm/HG,';
							
							if(item.HOPLNUM_TEMP_VAL != '' && item.HOPLNUM_TEMP_VAL !='0' )
								strVitalsChart+=' Temperature : ' + item.HOPLNUM_TEMP_VAL+'F,';
							
							if(item.HOPLNUM_RR_VAL != '' && item.HOPLNUM_RR_VAL !='0' )
								strVitalsChart+=' Respiration Rate : ' + item.HOPLNUM_RR_VAL+'br/m,';
							
							if(item.HOPLNUM_HB_VAL != '' && item.HOPLNUM_HB_VAL !='0' )
								strVitalsChart+=' Haemoglobin : ' + item.HOPLNUM_HB_VAL+'gm/dL,';
							
							if(item.HOPLNUM_SUGARFAST_VAL != '' && item.HOPLNUM_SUGARFAST_VAL !='0' )
								strVitalsChart+=' B.S. Fast : ' + item.HOPLNUM_SUGARFAST_VAL+'mg/dL,';
							
							if(item.HOPLNUM_SUGARPP_VAL != '' && item.HOPLNUM_SUGARPP_VAL !='0' )
								strVitalsChart+=' PP : ' + item.HOPLNUM_SUGARPP_VAL+'mg/dL,';
							
							if(item.HOPLNUM_HBA1C_VAL != '' && item.HOPLNUM_HBA1C_VAL !='0' )
								strVitalsChart+=' HBA1C : ' + item.HOPLNUM_HBA1C_VAL+'mg/dL,';
							
							if(item.HOPLNUM_PULSE_RATE != '' && item.HOPLNUM_PULSE_RATE !='0' )
								strVitalsChart+=' Pulse Rate : ' + item.HOPLNUM_PULSE_RATE+' be/m,';
							
							
							if(item.HOPLSTR_BLOOD_GROUP != '' && item.HOPLSTR_BLOOD_GROUP !='0' )
								strVitalsChart+=' Blood Group : ' + item.HOPLSTR_BLOOD_GROUP+',';	
							/*-----------------added for cancer screening------------*/
								if(item.HOPLSTR_CANCER_SCREENING != '' && item.HOPLSTR_CANCER_SCREENING !='0' )
									strVitalsChart+='Cancer Screening : ' + item.HOPLSTR_CANCER_SCREENING+',';
								
							if(item.HOPLNUM_CURCUMFERENCE_VAL != '' && item.HOPLNUM_CURCUMFERENCE_VAL !='0' )
								strVitalsChart+=' Head Circumference : ' + item.HOPLNUM_CURCUMFERENCE_VAL+' cms,';
							
							if(item.HOPLNUM_MUAC_VAL != '' && item.HOPLNUM_MUAC_VAL !='0' )
								strVitalsChart+=' MUAC : ' + item.HOPLNUM_MUAC_VAL+' cms,';
							
							
							if(item.HOPLSTR_DISABILITY != '' && item.HOPLSTR_DISABILITY !='0' )
								strVitalsChart+=' Disability : ' + item.HOPLSTR_DISABILITY+' ,';
							
							
							if(item.HOPLSTR_SMOKING != '' && item.HOPLSTR_SMOKING !='0' )
								strVitalsChart+=' Smoking : ' + item.HOPLSTR_SMOKING+' ,';
							
							
							if(item.HOPLSTR_ANEMIC != '' && item.HOPLSTR_ANEMIC !='0' )
								strVitalsChart+=' Anemic : ' + item.HOPLSTR_ANEMIC+' ,';
							
							if(patGenAgeCat.split('/')[0] == 'F'){
							if(item.HOPLSTR_PREGNANCY != '' && item.HOPLSTR_PREGNANCY !='0'  )
								strVitalsChart+=' Pregnancy : ' + item.HOPLSTR_PREGNANCY+' Wk ,';
							}
							
							
							console.log('vitalhiddenval::::'+strVitalsChart);
							//var vitalhiddenval= 'Weight : '+item.HOPLNUM_WEIGHT_VAL +'Kg.,' + ' Height : ' +item.HOPLNUM_HEIGHT_VAL+'CM,'+ ' BMI : ' + item.HOPLNUM_BMI_VAL+'CM,' +' BP :'+ item.HOPLNUM_BPSY_VAL+'/'+item.HOPLNUM_BPDIASY_VAL 'g/dl' +' Temperature : '+ item.HOPLNUM_TEMP_VAL+'F' + item.HOPLNUM_RR_VAL+''  + item.HOPLNUM_HB_VAL + item.HOPLNUM_SUGARFAST_VAL + item.HOPLNUM_SUGARPP_VAL + item.HOPLNUM_HBA1C_VAL
							$("#vitalHiddenValId").val(strVitalsChart.replace(/,{2,}/g, ','));
							
						 $("#strVitalModifyId").val('1');
				 });
				 }else{
					 console.log('Error!!!');
					 $("#strVitalModifyId").val('1');
					 $("#strPatHiddenDtlsId").val(''+'^'+CR_No+'^'+episodeCode+'^'+visitNo+'^'+hospitalCode+'^'+''+'^'+''+'^'+seatId);
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
 						 $("#curcumferenceId").val('');
						 $("#MUAC").val('');
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