/**
 * Developer : Aadil
 * Date		 : May-2014 
 */


$(document).ready(function(){
	if($("#afterGoId").val()=="1"){
		
		if($('[name="patGenderCode"]')[0].value=="F"){
			$('.female').show();
			if($('[name="isPregnant"]')[0].checked)
				$('.pregnant').show();
		}
		var chks=$('[name="chks"]');
		if(typeof chks!=undefined && chks.length>0){
			chks[0].checked=true;
			patDeathDtlJsObj.onclickEpisode(chks[0], '0');
		}
		/*$("#deathDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
		$("#verificationDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
		$("#handoverDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
		var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
		$('#deathDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		$('#verificationDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		$('#handoverDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		
		$("#sysdateId").val($("#deathDateId").val());
		var hiddenTimeHr = $("#hiddenTimeHrId").val();
		var hiddenTimeMin = $("#hiddenTimeMinId").val();
		
		var validTypeHiddenTimeHr="range[0,"+hiddenTimeHr+"]";
		var validTypeHiddenTimeMin="range[0,"+hiddenTimeMin+"]";
		
		patDeathDtlJsObj.setDeathDateValidation(0);
		$("#deathTimeHrId").validatebox({required: true, validType : validTypeHiddenTimeHr});
		$("#deathTimeMinId").validatebox({required: true, validType : validTypeHiddenTimeMin});
		
		patDeathDtlJsObj.setVerifyDtValidation();
		
		$("#deathMannerCodeId").validatebox({validType: ['selectCombo[-1]']});
		$("#deathMannerCodeId").validatebox({validType: ['unitConsultantCodeId[-1]']});
		
		$("#immediateCause1Id").validatebox({required: true, validType : 'maxLengthFixed[500,\'immediateCause1\']'});
		$("#immediateCause2Id").validatebox({validType : 'maxLengthFixed[500,\'immediateCause2\']'});
		$("#immediateCause3Id").validatebox({validType : 'maxLengthFixed[500,\'immediateCause3\']'});
		$("#otherCauseId").validatebox({validType : 'maxLengthFixed[500,\'otherCause\']'});
	}
});

var patDeathDtlJsObj={
	showHandoverDetail : function()
	{
		var bodyHandoverTo = $("#bodyHandoverToId").val();
		/*if(bodyHandoverTo=="1")
			$("#isBodyHandoverToMortuaryId").val("1");
		else
			$("#isBodyHandoverToMortuaryId").val("0");*/
		if(bodyHandoverTo=="1" || bodyHandoverTo=="-1"){
			if($("#divPoliceId").is(":visible")==true){
				patDeathDtlJsObj.showHideHandoverToPoliceDiv(false);
			}
			if($("#divRelativeId").is(":visible")==true){
				patDeathDtlJsObj.showHideHandoverToRelativeDiv(false);
			}
		}else if(bodyHandoverTo=="2"){
			patDeathDtlJsObj.showHideHandoverToRelativeDiv(false);
			patDeathDtlJsObj.showHideHandoverToPoliceDiv(true);
			
		}else if(bodyHandoverTo=="3"){
			patDeathDtlJsObj.showHideHandoverToPoliceDiv(false);
			patDeathDtlJsObj.showHideHandoverToRelativeDiv(true);
		}
	},
	showHandoverToDiv :	function (obj)
	{
		if(obj.checked){
			obj.value="1";
			$("#divHandoverDtlDataId").slideDown(500);
			$("#handoverDateId").val($("#sysdateId").val());
			$("#handoverTimeHrId").val($("#hiddenTimeHrId").val());
			$("#handoverTimeMinId").val($("#hiddenTimeMinId").val());
			
			patDeathDtlJsObj.setHandoverDtValidation(true);
			$("#bodyHandoverToId").validatebox({validType: ['selectCombo[-1]']});
		}
		else{
			obj.value="0";
			
			$("#bodyHandoverToId").validatebox({validType: null});
			if($("#divPoliceId").is(":visible")==true)
				patDeathDtlJsObj.showHideHandoverToPoliceDiv(false);
			if($("#divRelativeId").is(":visible")==true)
				patDeathDtlJsObj.showHideHandoverToRelativeDiv(false);
			
			patDeathDtlJsObj.setHandoverDtValidation(false);
			$("#divHandoverDtlDataId").slideUp(500);
		}
	},
	showInjuryDiv :	function ()
	{
		var injuryCodeWithAccFlag=$("#deathMannerCodeId").val();	
		var accFlag=injuryCodeWithAccFlag.split("@")[1];
		
		if(accFlag=="1"){
			$("#divInjuryDtlId").slideDown(500);
			$("#isDeathAccidentalId").val("1");
			
			$("#injuryRemarksId").validatebox({required: true});
		}else{
			$("#injuryRemarksId").validatebox({required: false});
			$("#divInjuryDtlId").slideUp(500);
			$("#isDeathAccidentalId").val("0");
		}	
	},
	onclickEpisode : function (obj,index){
		//var patGenderCode = $('[name="patGenderCode"]')[0].value;
		
		$('[name="episodeCode"]')[0].value=$('[name="selEpisodeCode"]')[index].value;
		$('[name="episodeVisitNo"]')[0].value=$('[name="selEpisodeVisitNo"]')[index].value;
		$('[name="departmentCode"]')[0].value=$('[name="selDeptCode"]')[index].value;
		$('[name="departmentUnitCode"]')[0].value=$('[name="selDeptUnitCode"]')[index].value;
		$('[name="wardCode"]')[0].value=$('[name="selWardCode"]')[index].value;
		$('[name="roomCode"]')[0].value=$('[name="selRoomCode"]')[index].value;
		var mlcFlag= $('[name="selMlcFlag"]')[index].value;
		if(mlcFlag=="1"){
			$("#bodyHandoverToId option[value='3']").remove();
		}else{
			if( $("#bodyHandoverToId option[value='3']").length == 0){
				$('#bodyHandoverToId').append($('<option>', {
					value: 3,
					text: 'Relatives'
				}));
			}
		}
		patDeathDtlJsObj.setDeathDateValidation(index);
	},
	showHideHandoverToPoliceDiv : function (flag){
		if(flag){
			$("#divPoliceId").show();
			$("#officerNameId").validatebox({required: true, validType : ['alphaNumericWithSpaces','maxLengthFixed[60,\'officerName\']']});
			$("#officerDesignationId").validatebox({required: true, validType : ['alphaNumericWithSpaces','maxLengthFixed[60,\'officerDesignation\']']});
			$("#officerBadgeNoId").validatebox({required: true, validType : ['alphaNumericWithSpaces','maxLengthFixed[60,\'officerBadgeNo\']']});
		}else{
			$("#officerNameId").validatebox({required: false});
			$("#officerDesignationId").validatebox({required: false});
			$("#officerBadgeNoId").validatebox({required: false});
			
			$("#officerNameId").val("");
			$("#officerDesignationId").val("");
			$("#officerBadgeNoId").val("");
			$("#divPoliceId").hide();
		}
	},
	showHideHandoverToRelativeDiv : function (flag){
		if(flag){
			$("#divRelativeId").show();
			$("#relativeNameId").validatebox({required: true, validType : ['alphaNumericWithSpaces','maxLengthFixed[80,\'relativeName\']']});
			$("#relativeCodeId").validatebox({validType: ['selectCombo[-1]']});
			$("#relativeAddressId").validatebox({required: true, validType : ['alphaNumericWithSpaces','maxLengthFixed[200,\'relativeAddress\']']});
		}else{
			$("#relativeNameId").validatebox({required: false});
			$("#relativeCodeId").validatebox({validType: null});
			$("#relativeAddressId").validatebox({required: false});
			
			$("#relativeNameId").val("");
			$("#relativeCodeId").val("-1");
			$("#relativeAddressId").val("");
			
			$("#divRelativeId").hide();
		}
	},
	showHidePregnantDtl : function(){
		if($('[name="isPregnant"]')[0].checked)
			$('.pregnant').show();
		else
			$('.pregnant').hide();
	},
	setDeathDateValidation : function(index){
		var visitDate= $('[name="selVisitDate"]')[index].value;
		
		var dateValidType1="dtgtetdt['"+visitDate+"','dd-Mon-yyyy',0,'It should not be less than Visit Date.']";
		var dateValidType2="dtltetctdt['It should not greater than Current Date']";
		
		$("#deathDateId").validatebox({
			required: true,	
			validType: [dateValidType1,dateValidType2]
		});
		
		
		
	},
	setVerifyDtValidation : function(){
		var verifyDateValidType1= "myValidateRule[\"verifyDateValidFn(1)\",'msg']";
		
		// Verification Date Validation
		$("#verificationDateId").validatebox({
			required: true,	
			validType: [verifyDateValidType1]
		});
		$("#verificationTimeHrId").validatebox({required: true, validType : verifyDateValidType1});
		$("#verificationTimeMinId").validatebox({required: true, validType : verifyDateValidType1});
		
	},
	setHandoverDtValidation : function(flag){
		if(flag){
			var hiddenTimeHr = $("#hiddenTimeHrId").val();
			var hiddenTimeMin = $("#hiddenTimeMinId").val();
			var validTypeHiddenTimeHr="range[0,"+hiddenTimeHr+"]";
			var validTypeHiddenTimeMin="range[0,"+hiddenTimeMin+"]";
			var validTypeCurrentDate="dtltetctdt['It should not greater than Current Date']";
			
			$("#handoverDateId").validatebox({
				required: true,	
				validType: [validTypeCurrentDate]
			});
			$("#handoverTimeHrId").validatebox({required: true, validType : validTypeHiddenTimeHr});
			$("#handoverTimeMinId").validatebox({required: true, validType : validTypeHiddenTimeMin});
		}else{
			//alert("false");
			$("#handoverDateId").val($("#sysdateId").val());
			$("#handoverDateId").validatebox({
				required: false
			});
			$("#handoverTimeHrId").validatebox({required: false});
			$("#handoverTimeMinId").validatebox({required: false});
		}
		
	}
	
};

$("#deathMannerCodeId").click(function(){
	patDeathDtlJsObj.showInjuryDiv();
});

$('#submitId').click(function(e){
	if($('#PatientDeathDetail').form('validate')){
		//alert("validation true");
		//return;
		if($('#txt-snomed-ct-search_1').val()==""){
			alert("Kindly enter Immediate Cause 1");
			return false;
		}
		$("#isHandoverToId").prop('checked',true);
		sortandEncodebase64($("#PatientDeathDetail"));
		submitForm('SAVE');
	}else{
		//alert("validation false");
		return false;
	}
	
});

$("#cancelId").click(function(){
	//alert("inside cancelId");
	if($("#afterGoId").val()=="1")
		submitForm('CANCEL');
	else
		callMenu('/AHIMSG5/hislogin/transactions/jsp/st_desk_background.jsp');
	if($("#flagHasActionErrorId").val())
		submitForm('CANCEL');
	
});
/* ## 	Modification Log							
	##		Modify Date				:12thDec'14 
	##		Reason	(CR/PRS)		:Bug fix 7727
	##		Modify By				:Sheeldarshi 
*/
$("#clearId").click(function(){
	submitForm('CLEAR');
});
//End:Sheeldarshi

function moveToRightBox(elem, evt,targetFldName){
	val=elem.value;
	if(val<=23){
		maxLength =elem.getAttribute('maxlength');
		if(val.length==maxLength){
			document.getElementsByName(targetFldName)[0].select();
		}
	}
}
function submitForm(mode) 
{
	document.forms[0].action = mode + "PatientDeathDetail.action";
	document.forms[0].submit();
}

function verifyDateValidFn(mode)
{
	var deathDate= $("#deathDateId").val();
	var deathTimeHr= $("#deathTimeHrId").val();
	var deathTimeMin= $("#deathTimeMinId").val();
	var deathTimeSec = "00";
	if(deathTimeHr.trim()=="")
		deathTimeHr="00";
	if(deathTimeMin.trim()=="")
		deathTimeMin="00";
			
	var deathDateHrMin= deathDate+" "+deathTimeHr+":"+deathTimeMin+":"+deathTimeSec;
	

	var verificationDate= $("#verificationDateId").val();
	var verificationTimeHr= $("#verificationTimeHrId").val();
	var verificationTimeMin= $('[name="verificationTimeMin"]')[0].value;
	var verificationTimeSec = "00";
	if(verificationTimeHr.trim()=="")
		verificationTimeHr="00";
	if(verificationTimeMin.trim()=="")
		verificationTimeMin="00";
	var verificationDateHrMin= verificationDate+" "+verificationTimeHr+":"+verificationTimeMin+":"+verificationTimeSec;
	
	if(deathTimeHr=="")
		return "Please Select Death Time Hour First.";
	
	if(deathTimeMin=="")
		return "Please Select Death Time Min First.";
	
	
	var d1 = convertStrToDate(deathDateHrMin, 'dd-Mon-yyyy hh:mm:ss');
	var d2 = convertStrToDate(verificationDateHrMin, 'dd-Mon-yyyy hh:mm:ss');
	var ctdt = new Date();
	
	
	//alert("d1 :"+d1+",\nd2 :"+d2);
	if(d2<d1){
		return "Verification Date(including Date,Hour & Min) should not be less than Death Date.";
	}
	//d2.setDate(d2.getDate() + 1);
	
	if(d2>ctdt){
		return "Verification Date Should not Greater than current Date.";
	}
	
	return "";
	
}

window.onload = function() 
{
	//alert("Inside WINDOWS oNLOAD");
	if(document.getElementsByName("printFlag")[0].value=="1")
	{
		openDeathPrintPopup();
	}	
}

function openDeathPrintPopup()
{
	//alert("popup");
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var noOfCopies=document.getElementsByName("noOfCopies")[0].value;
	
	//alert(patCrNo +""+noOfCopies );
	//var path='/HISRegistration/registration/transactions/PatientDeathDetail.action?hmode=PRINTCERT&patCrNo='+patCrNo;patCRNo
	var path='/HISRegistration/registration/transactions/PRINTCERTPatientDeathDetail.action?patCRNo='+patCrNo+'&noOfCopies='+noOfCopies;
	openPrintPopup(path,700,800);
	document.getElementsByName("printFlag")[0].value="0";
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
		child.moveTo(250,250);
		child.focus(); 
}