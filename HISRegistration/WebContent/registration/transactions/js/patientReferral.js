
var departmentList="";
var departmentUnitList="";
var patReferral={
processGeneralNode:function(elementName,elementNode)
  {
	var optionText="<option value='-1'>Select Value</option>";
	if(elementNode!=null){
		for(var i=0;i<elementNode.childNodes.length;i++ )
		{
			var optionNode=elementNode.childNodes[i];
			optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
		}
	}
	if(elementName=="patRefGnctdHospitalDeptUnit"){
		optionText+="<option value='0'>Other</option>";
	}
	
	if(elementName=="patRefGnctdHospitalCode"){
		optionText="<option value='-1'>Select Value</option>"+optionText;
	}
	
	if(document.getElementsByName(elementName).length!=0)
			document.getElementsByName(elementName)[0].innerHTML=optionText;
	
	
	
  },onchange_patRefGnctdHospitalCode:function()
{
	var refHospCode= document.getElementsByName("patRefGnctdHospitalCode")[0].value;
	var flafRefHospOrInst = refHospCode.split("#")[1];
	refHospCode	=	refHospCode.split("#")[0];
	var action 	= "/HISRegistration/registration/transactions/getRefDeptPatientReferral.action?refHospCode="+refHospCode+"&flagRefHospOrInst="+flafRefHospOrInst;
	
	//alert(action);
	$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
		{
				var returnDocument=data;
				var rootNode=returnDocument.getElementsByTagName("root")[0];
				for(var i=0;i<rootNode.childNodes.length;i++ )
				{
					var elementNode=rootNode.childNodes[i];
					var elementName=elementNode.nodeName;
					
					patReferral.processGeneralNode(elementName,elementNode);
				}
					
		},error: function(errorMsg,textstatus,errorthrown) {
          alert('onchange_patRefGnctdHospitalDept '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
          
      }});
},setFormFieldsAfterSave:function()
  {
	  $('.validatebox-text').removeClass('validatebox-invalid');
	  //$('.validatebox-text').removeClass('validatebox-invalid');
	  $('[name="departmentCode"]')[0].value="-1";
	  $('[name="departmentUnitCode"]')[0].value="-1";
	  $('[name="patRefGnctdHospitalCode"]')[0].value="-1";
	  $('[name="patRefGnctdHospitalDeptUnit"]')[0].value="-1";
	  $('[name="remarks"]')[0].value="";
	  $('[name="externalReferRemarks"]')[0].value="";
	  $('[name="patRefHospitalDeptOther"]')[0].value="";
	  $('[name="patRefHospitalName"]')[0].value="";
	  
  }


};// end of patReferral Object Method

$('[name="patRefGnctdHospitalCode"]').change(function(){
	patReferral.onchange_patRefGnctdHospitalCode();
});

$("#patRefGnctdHospitalDeptId").change(function(){
	if($("#patRefGnctdHospitalDeptId").val()=="0"){
		$("#divRefHospitalDeptOtherId").show();
		$('[name="patRefHospitalDeptOther"]').validatebox({required : true, validType: 'alphaWithSpace'});
	}
	else{
		$("#divRefHospitalDeptOtherId").hide();
		$('[name="patRefHospitalDeptOther"]').validatebox({required : false, validType: null});
	}
});


//Submit form if valid
$('#submitId').click(function(e){
	//alert("inside submitClick");
	$('#submitId').hide();
	$("#PatientReferral").attr('action',"/HISRegistration/registration/transactions/SAVEPatientReferral.action");
	if(ValidateBeforeSubmit())
	{
		if($('#PatientReferral').form('validate')){
			if(typeof $('[name="aptFor"]')[0] != "undefined"){
				if(createAppointment())
					sortandEncodebase64($("#PatientReferral"));
					$('#PatientReferral').submit();				
			}
			else
				$('#PatientReferral').submit();
				$('#submitId').show();
		}else{
			$('#submitId').show();
			return false;
		}
   }
   else
	$('#submitId').show();
	//e.preventDefault();
});

function createAppointment(){
	var appointmentForId=$('[name="aptFor"]')[0].value;
	var aptDate=$('[name="aptForDate"]')[0].value;
	var startTime=$('[name="slotST"]')[0].value;
	var endTime=$('[name="slotET"]')[0].value;
	var shiftId=$('[name="shiftId"]')[0].value;
	var apptType=$('[name="aptType"]')[0].value;
	var url=$('#PatientReferral');	
	
	var flag=confirmSlot(appointmentForId,aptDate,startTime,endTime,shiftId,apptType);
	//alert(document.getElementsByName("appointmentNo")[0].value);
	//alert(document.getElementsByName("appointmentQueueNo")[0].value);
	//alert("Inside createApt"+flag);
	return flag;
}

//Clear Form Data
$('#clearId').click(function(e){
	patReferral.setFormFieldsAfterSave();
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId").html("");
	//$("#divInternalReferId").hide();
	//$("#divExternalReferId").hide();
	//$("#divReferredInstitute").hide();
	$("#divRefHospitalDeptOtherId").hide();
	
	
});

//Clear Initial Data
$('#initialClearId').click(function(e){
	
	
	$("#PatientReferral").attr('action',"/HISRegistration/registration/transactions/PatientReferral.action");
	$('#PatientReferral').submit();
	
});

//To Show the Apt Free Slot on the Change of Dept Unit Combo,Added by Singaravelan on 27-Jan-2015
$('[name="departmentUnitCode"]').change(function(e){
	
	var crNo=$('[name="patCrNo"]')[0].value;
	var paraId=$('[name="departmentUnitCode"]')[0].value+"^0^0^0^0^0^0";
	var tagView=1;
	//if(document.getElementsByName("showAppointmentDateInsidePopup")[0]!=undefined)
		//var showAppointmentDateInsidePopup=document.getElementsByName("showAppointmentDateInsidePopup")[0].value;
	//alert("_pSlotDetails in pR js"+_pSlotDetails);

	if($('[name="departmentUnitCode"]')[0].value!=-1){
		
		var action = "/HISRegistration/appointment/transactions/makeAppointmentTagHTMLAppointmentTags.action";
		var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView
				//showAppointmentDateInsidePopup:showAppointmentDateInsidePopup
				
		  };
		
		$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				if(returnHTML.indexOf('^')>0)
					returnHTML=returnHTML.split('@')[1];
				else
					returnHTML="";
				$('#aptTagId').html(returnHTML);
				if(returnHTML=="")
				{
					document.getElementById("divNoSlot").style.display="";
				}
				else
				{
				document.getElementById("divNoSlot").style.display="none";
				}
				
				},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
	}
	

	
	
	
});

function makeTimeTag()
{	
	var action = "/HISRegistration/appointment/transactions/makeTimeTagAppointmentTags.action";
	var data = {
			//paraId : paraId,	
			//crNo :crNo,
			//tagView :tagView
			
	  };
	
	$.ajax({
		url : action, 
		type : "POST",
		async : true,
		data : data,
		dataType : "html",
		success : function(returnHTML) {
			if(returnHTML.indexOf('^')>0)
				returnHTML=returnHTML.split('@')[1];
			else
				returnHTML="";
			$('#aptTagId').html(returnHTML);
		},
		error : function(errorMsg, textstatus, errorthrown) {
			alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
						+ textstatus + " errorthrown::" + errorthrown);
			}
		});
}

function ValidateBeforeSubmit(){
	//var flagYes=false;
		
	if($('[name="isRefferInOut"]')[1].checked)
	{
		$('[name="isRefferInOut"]')[1].value=0;
		if($('[name="referringInstType"]')[1].checked)
			{
			  $('[name="isAssociated"]')[0].value=0;
			}
		else if($('[name="referringInstType"]')[0].checked)
			{
			 $('[name="isAssociated"]')[0].value=1;
			}
		else
			{
			  alert("Please Select Referring Istitute Type");
			  return false;
			}
		if($('[name="referringInstType"]')[0].checked){
			$("#patRefGnctdHospitalCodeId").validatebox({validType: ['selectCombo[-1]']});
		}
		else
			{
			$("#patRefGnctdHospitalCodeId").validatebox({validType: null});
			$('[name="patRefHospitalName"]').validatebox({required : true, validType: 'allowAlphaSpaceBracketDot'});
			}
	}
	else if($('[name="isRefferInOut"]')[0].checked)
		{
		$('[name="isRefferInOut"]')[0].value=1;
		if($('[name="choice"]')[0].checked){
			$('[name="departmentCode"]').validatebox({validType: ['selectCombo[-1]']});
		}
		if($('[name="choice"]')[1].checked){
			$('[name="departmentUnitCode"]').validatebox({validType: ['selectCombo[-1]']});
		}
		}
	else
	{
			alert("Please Select Referring Type");
			return false;
	}
	
	return true;
	
}

function getFilteredDeptUnit(obj)
{
	var unitTypeGeneral=1;
	var unitTypeSpecial=2;
	var index=obj.title;
	var indexInt=parseInt(index);
	var selectedUnitType=document.getElementsByName("selectedDepartmentUnitType")[indexInt].value;
	var selectedDepartmentCode=document.getElementsByName("selectedDepartmentCode")[indexInt].value;
	var selectedDepartmentUnitCode=document.getElementsByName("selectedDepartmentUnitCode")[indexInt].value;
	document.getElementsByName("departmentCode")[0].innerHTML=departmentList;
	document.getElementsByName("departmentUnitCode")[0].innerHTML=departmentUnitList;
	var departmentCombo=document.getElementsByName("departmentCode")[0];
	var departmentUnitCombo=document.getElementsByName("departmentUnitCode")[0];
	// alert("selectedDepartmentCode "+selectedDepartmentCode)
	//alert("selectedUnitType......"+selectedUnitType+"General..........."+unitTypeGeneral);
	
	if(selectedUnitType==unitTypeGeneral)
	{
		var options=departmentCombo.options;
		// alert("options "+options)
		var i=0;
		for(;i<options.length;i++)
		{
			 //alert(options[i].value)
			if(options[i].value==selectedDepartmentCode)
			{
				departmentCombo.remove(i);
				break;
			}
		}
	}
	if(selectedUnitType==unitTypeSpecial)
	{
		var options=departmentUnitCombo.options;
		// alert("options "+options)
		var i=0;
		for(;i<options.length;i++)
		{
			 //alert(options[i].value)
			if(options[i].value==selectedDepartmentUnitCode)
			{
				departmentUnitCombo.remove(i);
				break;
			}
		}
	}
}

function showdivhoscode(obj)
{
    //alert('inside showdivhoscode'+obj.value);
    if(obj.value=='O')
    	{
	document.getElementById("divRefHosCode").style.display="none";  
	document.getElementById("divRefHosname").style.display=""; 
	document.getElementsByName("patRefHospitalName")[0].value="";
	if(document.getElementsByName("divRefDeptforAssociatedInstitutes")[0]!=undefined)
	document.getElementsByName("divRefDeptforAssociatedInstitutes")[0].value="none";
	if(document.getElementsByName("divRefDeptforAssociatedInstitutes")[0]!=undefined)
	document.getElementsByName("divRefDeptforOtherInstitutes")[0].value="";
	
	
    	}
    else if(obj.value=='G')
    	{
    	document.getElementById("divRefHosCode").style.display="";  
    	document.getElementById("divRefHosname").style.display="none"; 
    	document.getElementsByName("patRefHospitalName")[0].value="";
    	if(document.getElementsByName("divRefDeptforAssociatedInstitutes")[0]!=undefined)
    		document.getElementsByName("divRefDeptforAssociatedInstitutes")[0].value="";
		if(document.getElementsByName("divRefDeptforOtherInstitutes")[0]!=undefined)
    		document.getElementsByName("divRefDeptforOtherInstitutes")[0].value="";
    	}
	
}

function selectDept(obj)
{
    //alert('inside selectDept'+obj.value);
    if(obj.value=='0')
    	{
	document.getElementById("divReferDeptUnitId").style.display="none";  
	document.getElementById("divReferDeptId").style.display=""; 
	
    	}
    else if(obj.value=='1')
    	{
    	document.getElementById("divReferDeptUnitId").style.display="";  
    	document.getElementById("divReferDeptId").style.display="none"; 
    	
    	}
	
}

function showInternal(obj)
{
	document.getElementById("divExternalReferId").style.display="none";  
	document.getElementById("divInternalReferId").style.display=""; 
	document.getElementById("divReferred").style.display="none"; 
	document.getElementById("divReferredInstitute").style.display="none"; 
}

function showExternal(obj)
{
	document.getElementById("divExternalReferId").style.display="";  
	document.getElementById("divInternalReferId").style.display="none"; 
	document.getElementById("divReferred").style.display=""; 
	document.getElementById("divReferredInstitute").style.display="";  
	
}



