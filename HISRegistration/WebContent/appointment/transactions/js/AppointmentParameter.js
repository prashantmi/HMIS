$(document).ready(function() {
	$("input:text").focus(function() { $(this).select();});
	//Added By Raj Kumar on 23 Jan 2019 for Adding All Option in Appointment Report
	var FlagForAllVal="0";
	if($("#flaghidden").val()!=null)
		{
		//alert("flaghidden----"+$("#flaghidden").val());
	FlagForAllVal=$("#flaghidden").val();
		}
		
	if(FlagForAllVal=="1")
		{
		//alert("inside function if flaghiddne value 1");
		$('#appointmentDtl').change(makeParatmeterHTMLwithAll);
		}
	else{
		//alert("inside function else flaghiddne value 0");
		$('#appointmentDtl').change(makeParatmeterHTML);
		}	
	$('[id^="ACTUALPARAMETERID_"]').change(getParameterComboValueHTML);
	addPropertyToParameterCombo();
	
	if($('#tagname').val()=="AppointmentListingTag"){
		$('#btnAppointmentListingGo').click(getAppointmentList);
		$('[name="appointmentDate"]').validatebox({
			required:true				
		})
		.change(emptyAppointmentListing)
		.datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat:"dd-M-yy"
		});
		$('#appointmentDtl').change(emptyAppointmentListing);
	}
	
	return false;
});
function emptyAppointmentListing(){
	$('#DIV_TABLE').html("");	
}
function addPropertyToParameterCombo(){
	$('[id^="ACTUALPARAMETERID_"]').each(function(){
		if($('#' + this.id + " option").length>1){
			//alert($('#' + this.id + " option").length);
			$('#' + this.id ).validatebox({
				required:true,
				validType: 'selectCombo[-1]'		
			});
			
			if($('#tagname').val()=="AppointmentListingTag"){
				$('#' + this.id ).change(emptyAppointmentListing);
			}
		}
		
	});
}
function makeParatmeterHTML() {
	
	$('#'+this.id).validatebox({
		required:true,
		validType: 'selectCombo[-1]'		
	});
	$('#ROW_PARAMETER').html("");
/*	var isValid = $('#'+ $('[name="supportClassName"]').val()).form('validate');
    if(isValid==false)
    	return false;*/	
    	
    
      
    
	var action = "/HISRegistration/appointment/transactions/makeParameterHTMLAppointmentTags.action";
	//alert(this.value);
	var supportClassName=$('[name="supportClassName"]').val() ;
	if(supportClassName!="")
		supportClassName=supportClassName+ ".";
	var arr=this.value.split("#");
	$('[name="'+supportClassName+'appointmentForId"]').val(arr[0]);
	$('[name="'+supportClassName+'appointmentName"]').val($("#"+this.id+"option:selected").text());
	$('[name="'+supportClassName+'multiAppointmentStatus"]').val(arr[1]);
	$('[name="'+supportClassName+'isTimingByAppointment"]').val(arr[2]);
	//alert($('[name="tagView"]').val());
	var data = {
			appointmentForId : arr[0],	
			tagView :$('[name="tagView"]').val(),
			supportClassName:supportClassName
	  };
	$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				//alert(returnHTML);
				if(returnHTML!=""){
					$('#ROW_PARAMETER').html(returnHTML);
					//alert($('[name="'+supportClassName+'.actualParameterId"]'));
					$('[name="'+supportClassName+'actualParameterId"]').change(getParameterComboValueHTML);
					addPropertyToParameterCombo();
				}
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});

}
//Added By Raj Kumar on 23 Jan 2019 for Adding All Option in Appointment Report
function makeParatmeterHTMLwithAll() {
	
	$('#'+this.id).validatebox({
		required:true,
		validType: 'selectCombo[-1]'		
	});
	$('#ROW_PARAMETER').html("");
/*	var isValid = $('#'+ $('[name="supportClassName"]').val()).form('validate');
    if(isValid==false)
    	return false;*/	
    	
    
      
    
	var action = "/HISRegistration/appointment/transactions/makeParameterHTMLAppointmentTags.action";
	//alert(this.value);
	var supportClassName=$('[name="supportClassName"]').val() ;
	if(supportClassName!="")
		supportClassName=supportClassName+ ".";
	var arr=this.value.split("#");
	$('[name="'+supportClassName+'appointmentForId"]').val(arr[0]);
	$('[name="'+supportClassName+'appointmentName"]').val($("#"+this.id+"option:selected").text());
	$('[name="'+supportClassName+'multiAppointmentStatus"]').val(arr[1]);
	$('[name="'+supportClassName+'isTimingByAppointment"]').val(arr[2]);
	
	//alert($('[name="tagView"]').val());
	var data = {
			appointmentForId : arr[0],	
			tagView :$('[name="tagView"]').val(),
			supportClassName:supportClassName
	  };
	$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				//alert(returnHTML);
				if(returnHTML!=""){
					$('#ROW_PARAMETER').html(returnHTML);
					//alert($('[name="'+supportClassName+'.actualParameterId"]'));
					$('[name="'+supportClassName+'actualParameterId"]').change(getParameterComboValueHTML);
					addPropertyToParameterCombo();
					$('select[name="actualParameterId"] option[value="-1"]').remove();
					$('select[name="actualParameterId"]').prepend('<option value="0" selected="selected" >All</option>');
				}
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});

}
function populateActualParaCombo(json){
	//alert("inside populateActualParaCombo");
	
	/*var isValid = $('#'+ $('[name="supportClassName"]').val()).form('validate');
    if(isValid==false)
      return false;*/
	if(json.length){
		for(u=0;u<json.length;u++){
			populateActualParaCombo(json[u]);
		}
	}
	else{
		for (var key in json){
			//alert(key);
			if($('#ACTUALPARAMETERID_'+ key)){				
				$('#ACTUALPARAMETERID_'+ key).html(json[key]);
			}
			if(key=="actualParameterReferenceId" && json[key]!="0"){
				//alert(json[key]);
				$('#actualParameterReferenceId').val(json[key]);					
			}
			if(key=="isMaxParameter" && json[key]=="1"){				
				if($('#scriptCallBackFunctionName').val()!=""){
					//alert($('#scriptCallBackFunctionName').val());
					eval($('#scriptCallBackFunctionName').val() + "()");
				}
			}
		 }
	}
}

function getAllActualParameterId(){
	var supportClassName=$('[name="supportClassName"]').val() ;
	if(supportClassName!="")
		supportClassName=supportClassName+ ".";
	var allActualParameterId="";
	
	$('[name="'+supportClassName+'actualParameterId"]').each(function(){
		//alert(this.value);
		if(this.value!=""){
			allActualParameterId+=this.value +"^";
		}	
	});
	
	if(allActualParameterId!="")
		allActualParameterId=allActualParameterId.substr(0,allActualParameterId.length-1);
	
	$("#allActualParameterId").val(allActualParameterId);
	//alert($("#allActualParameterId").val());
}

function getParameterComboValueHTML() { 
		$('#'+this.id).validatebox({
		required:true,
		validType: 'selectCombo[-1]'		
	});
	/*var isValid = $('#'+ $('[name="supportClassName"]').val()).form('validate');
    if(isValid==false)
      return false;*/
    
	var action = "/HISRegistration/appointment/transactions/getParameterComboValueHTMLAppointmentTags.action";
	getAllActualParameterId();
	var supportClassName=$('[name="supportClassName"]').val() ;
	if(supportClassName!="")
		supportClassName=supportClassName+ ".";
	
	var appointmentForId= supportClassName +"appointmentForId";
	
	//alert("appointmentForId--"+$('[name="'+appointmentForId +'"]').val()+"--tagView--"+$('[name="tagView"]').val()+"--parentParameterActualValue--"+this.value+"--parentParameterId--"+this.id.split("_")[1]+"--displayOrder--"+$('#DISPLAYORDER_' + this.id.split("_")[1]).val()+"--allActualParameterId--"+$("#allActualParameterId").val());
	
	var data = {
			appointmentForId : $('[name="'+appointmentForId +'"]').val(),	
			tagView:$('[name="tagView"]').val(),
			parentParameterActualValue:this.value,
			parentParameterId:this.id.split("_")[1],
			displayOrder:$('#DISPLAYORDER_' + this.id.split("_")[1]).val(), 
			allActualParameterId:$("#allActualParameterId").val()
	  };
	
	$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				//alert(returnHTML);		
				var json= eval('(' + returnHTML + ')');
				populateActualParaCombo(json);
				addPropertyToParameterCombo();
				//Added By Raj Kumar on 23 Jan 2019 for All Feature Appointment Report
				if($("#flaghidden").val()=="1" && $("#flaghidden").val()!=null ){
					$('select[id="ACTUALPARAMETERID_22"] option[value="-1"]').remove();
					if($('select[id="ACTUALPARAMETERID_22"]').find('option:eq(0)').val()!=0)
					$('select[id="ACTUALPARAMETERID_22"]').prepend('<option value="0" selected="selected" >All</option>');	
				}
				if($("#flaghidden").val()=="1" && $("#flaghidden").val()!=null ){
					if($("#ACTUALPARAMETERID_31"))
						{
						$('select[id="ACTUALPARAMETERID_32"] option[value="-1"]').remove();
						if($('select[id="ACTUALPARAMETERID_32"]').find('option:eq(0)').val()!=0)
							$('select[id="ACTUALPARAMETERID_32"]').prepend('<option value="0" selected="selected" >All</option>');	
						}
						
				}
				
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
}
function getAppointmentShifts(){
	// shift will be called here
}
function getAppointmentList(){
	//alert(this.id);
	$('#'+this.id).validatebox({
		required:true,
		validType: 'selectCombo[-1]'		
	});
	var isValid = $('#'+ $('[name="controllerName"]').val()).form('validate');
    if(isValid==false)
      return false;
    
   
	var action = "/HISRegistration/appointment/transactions/getAppointmentListHTMLAppointmentTags.action";
	getAllActualParameterId();
	var supportClassName=$('[name="supportClassName"]').val() ;
	if(supportClassName!="")
		supportClassName=supportClassName+ ".";
	
	//alert("appointmentForId--"+$('[name="'+appointmentForId +'"]').val()+"--tagView--"+$('[name="tagView"]').val()+"--allActualParameterId--"+$("#allActualParameterId").val()+"--appointmentDate--"+$("#appointmentDate").val()+"--listingMode--"+$('#listingMode').val());

	var appointmentForId= supportClassName +"appointmentForId";
	var data = {
			appointmentForId : $('[name="'+appointmentForId +'"]').val(),	
			tagView :$('[name="tagView"]').val(),			 
			allActualParameterId:$("#allActualParameterId").val(),
			appointmentDate:$("#appointmentDate").val(),			
			listingMode:$('#listingMode').val()
	  };
	
	$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				//alert(returnHTML);		
				$('#DIV_TABLE').html(returnHTML);
				$('[name="appointmentNo"]').click(setSelectedAppointmentObjectInSession);
				
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
}

function  setSelectedAppointmentObjectInSession(){
	
	//alert(this.checked);
	//alert(this.value);
	var action = "/HISRegistration/appointment/transactions/setSelectedAppointmentObjectInSessionAppointmentTags.action";
	var data = {
			appointmentNo:this.value			
	  };
	
	$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {				
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
}