$(document).ready(function(){
	/*$(document).ajaxStart(function(){
	    $("#divWaitId").css("display","block");
	});
	$(document).ajaxComplete(function(){
		$("#divWaitId").css("display","none");
	});*/
	  
	/*$("#visitDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
	var today=new Date().toLocaleFormat('%d-%b-%Y');
	$('#visitDateId').DatePicker({
		format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
	}).val(today);
	$("#entryDateId").val($("#visitDateId").val());
	
	if($("#afterGoId").val()=="1")
	{
		/*$("#mlcDateId").datepicker({dateFormat: 'dd-M-yy',
			onSelect: function(d,i){
		          
		          if(d < i.lastVal){
	            	  var mlcDateValidType0="dtgtetdt['"+i.lastVal+"','dd-Mon-yyyy',0,'MLC Date should not be less than Episode Date.']";
	            	  $("#mlcDateId").validatebox({
          				required: true,	
          				validType: [mlcDateValidType0]
          			});
		          }
		     }
		}).datepicker("setDate", new Date());*/		
		
		/*$("#handoverDateTimeId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
		
		/*for(var i=0; i<10; i++)
			emgMlcTypeSpecJsObj.createMultiDIv();*/
	}
																					
	$("#divPatDtlTileHeaderId").click(function(){
		$("#divPatDtlTileContentId").slideToggle();
	});
	
	$('#submitId').click(function(e){
		if($('#EmgMlcModificationDtl').form('validate')){
			//alert("validation true");
			//return;
			$('.tmpClassDisabled').attr("disabled", false);
			sortandEncodebase64($("#EmgMlcModificationDtl"));
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
	});
	
	$("#clearId").click(function(){
		document.forms[0].reset();
		if($("#afterGoId").val()=="1"){
			/*$("#mlcDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
			$("#handoverDateTimeId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
			var today=new Date().toLocaleFormat('%d-%b-%Y');
			$('#mlcDateId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(today);
			$('#handoverDateTimeId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(today);
			if($('[name="isBroughtBy"]')[0].checked)
				emgMlcModificationDtlJsObj.broughtBy($('[name="isBroughtBy"]')[0]);
		}
	});
	
	$("#patMlcNoId").validatebox({required: true});
	$("#pageNoId").validatebox({validType : 'numeric'});
	
	$("#mlcDateId").validatebox({required: true});
	
	
	//$("#mlcTimeHrId").validatebox({required: true, validType : 'valueltet[23]'});
	//$("#mlcTimeMinId").validatebox({required: true, validType : 'valueltet[59]'});
		
	var mlcDateTimeValidType= "myValidateRule[\"mlcTimeValidateModificationFn('oldMlcTimeHrId','oldMlcTimeMinId','mlcTimeHrId', 'mlcTimeMinId')\",'msg']";
	$("#mlcTimeHrId").validatebox({required: true, validType : [mlcDateTimeValidType,'valueltet[23]']});
	$("#mlcTimeMinId").validatebox({required: true, validType : [mlcDateTimeValidType,'valueltet[59]']});
	
});

$( document ).ajaxComplete(function() {
	$('#mlcDateId').DatePicker({
		format: 'd-M-Y',default_position :'below',start_date:today,	
		onSelect: function(d,i){
			if(d < i.lastVal){
            	  var mlcDateValidType0="dtgtetdt['"+i.lastVal+"','dd-Mon-yyyy',0,'MLC Date should not be less than Episode Date.']";
            	  $("#mlcDateId").validatebox({
    				required: true,	
    				validType: [mlcDateValidType0]
    			});
	          }
			}
	}).val(today);
	
	$('#handoverDateTimeId').DatePicker({
		format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
	}).val(today);
});

var flagMlcDtl="0";
var flagMlcParamDtl="0";
var flagBroughtByDtl="0";

var emgMlcModificationDtlJsObj={	
		
	populateFormFields: function(objMlc){
		
		flagMlcDtl="0";
		flagMlcParamDtl="0";
		flagBroughtByDtl="0";
			
		var index=objMlc.value.split('@')[0];
		var patMlcNo=objMlc.value.split('@')[1];
		var patCrNo=$('[name="patCrNo"]')[0].value;
		var episodeCode=$('[name="epiCode"]')[index].value;
		var episodeVisitNo=$('[name="epiVisitNo"]')[index].value;
		var params="patCrNo="+patCrNo+"&patMlcNo="+patMlcNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo;
		
		document.forms[0].reset();
		objMlc.checked=true;
		
		$("#submitId").hide();
		showHideWaitLoadDiv("show");
	  	
	  	
	  	var actionMlcParam 	= "/HISRegistration/registration/transactions/getMlcParameterDtl_AJAXEmgMlcModificationDtl.action?"+params;
		//alert("actionMlcParam :"+actionMlcParam);
	  	$.ajax({url: actionMlcParam,type:"POST",async:false,dataType:"json" ,success:function(dataMlcParam){
			var objFirstTmp=$('.tmpClass')[0];
			if(typeof dataMlcParam!=undefined && dataMlcParam.length>0)
			{
				DeleteRows(objFirstTmp.id);
				for( var i=0; i<dataMlcParam.length; i++){
					emgMlcTypeSpecJsObj.createMultiDIv();
				}	
				
				//alert("Completed");
				var mlcAction 	= "/HISRegistration/registration/transactions/getMlcDtl_AJAXEmgMlcModificationDtl.action?"+params;
				//alert(action);
			  	var objMlcDtl =$.ajax({url: mlcAction,type:"POST",async:true,dataType:"json" ,success:function(dataMlcDtl){
					//alert("dataMlcDtl :"+dataMlcDtl);
					emgMlcModificationDtlJsObj.populateMlcDtlFromJsonVOObj(dataMlcDtl);
					flagMlcDtl="1";
					emgMlcModificationDtlJsObj.finalizePopulateWork();
					
				},error: function(errorMsg,textstatus,errorthrown) {
					flagMlcDtl="2";
			        alert('getMlcDtl_AJAX :: '+errorMsg+", textstatus::"+textstatus+", errorthrown::"+errorthrown);
			        emgMlcModificationDtlJsObj.finalizePopulateWork();
			        
			    }
				});
			  	
		  		var actionBroughtBy 	= "/HISRegistration/registration/transactions/getBroughtByDtl_AJAXEmgMlcModificationDtl.action?"+params;
				//alert(actionBroughtBy);
			  	var objBroughtBy = $.ajax({url: actionBroughtBy,type:"POST",async:false,dataType:"json" ,success:function(dataBroughtBy){
					//alert("dataBroughtBy :"+dataBroughtBy);
					emgMlcModificationDtlJsObj.populateBroughtByDtlFromJsonVOObj(dataBroughtBy);
					flagBroughtByDtl="1";
					emgMlcModificationDtlJsObj.finalizePopulateWork();
				},error: function(errorMsg,textstatus,errorthrown) {
					flagBroughtByDtl="2";
			        alert('getBroughtByDtl_AJAX :: '+errorMsg+", textstatus::"+textstatus+", errorthrown::"+errorthrown);
			        emgMlcModificationDtlJsObj.finalizePopulateWork();
			    }
				});
			  	
			  	
				emgMlcModificationDtlJsObj.populateMlcParameterDtlFromJsonVOObj(dataMlcParam);
			  	//emgMlcModificationDtlJsObj.populateMlcParameterDtlFromJsonVOObj(dataMlcParam[i],i);
				flagMlcParamDtl="1";
				emgMlcModificationDtlJsObj.finalizePopulateWork();
			}
			
		},error: function(errorMsg,textstatus,errorthrown) {
			flagMlcParamDtl="2";
	        alert('getMlcParameterDtl_AJAX ::'+errorMsg+", textstatus::"+textstatus+", errorthrown::"+errorthrown);
	        emgMlcModificationDtlJsObj.finalizePopulateWork();
	        
	    }
		
		});
	},
	populateMlcDtlFromJsonVOObj:function(mlcJSONObject)
	{
		//$("#divBroughtById").show().children().show();
		
		for (var mlcKey in mlcJSONObject) {
			 if (mlcJSONObject.hasOwnProperty(mlcKey)) {
			    var mlcVal = mlcJSONObject[mlcKey];
			    //console.log("mlcKey :"+mlcKey+", mlcVal :"+mlcVal);
			    
			    if(document.getElementsByName(mlcKey) && document.getElementsByName(mlcKey)[0]!=undefined ){
			    	eval("var "+mlcKey+"='"+mlcVal+"'");
			    	document.getElementsByName(mlcKey)[0].value=eval(mlcKey);
			    	var objMlcFld = document.getElementsByName(mlcKey)[0];
			    	var type= objMlcFld.type;
			    	
			    	if(type=="text" || type=="textarea" )
			    		objMlcFld.className= objMlcFld.className +" tmpClassReadOnly";
			    	else if(type=="select-one" && mlcVal!="-1")
			    		objMlcFld.className= objMlcFld.className +" tmpClassDisabled";
			    	
			    	if(mlcKey=="fitnessStatus"){
			    		if(mlcVal==0){
			    			document.getElementsByName(mlcKey)[0].checked=false;
			    			document.getElementsByName(mlcKey)[1].checked=true;
			    		}
			    		else{
		    				document.getElementsByName(mlcKey)[0].checked=true;
			    			document.getElementsByName(mlcKey)[1].checked=false;
			    		}
			    	}
			    	
			    }else{
			    	if(mlcKey=="mlcTime"){
			    		var objMlcTimeHr=document.getElementsByName("mlcTimeHr");
			    		var objMlcTimeMin=document.getElementsByName("mlcTimeMin");
			    		
			    		objMlcTimeHr[0].value=mlcVal.split(':')[0];
			    		objMlcTimeMin[0].value=mlcVal.split(':')[1];
			    		objMlcTimeHr.className = objMlcTimeHr.className +" tmpClassReadOnly";
			    		objMlcTimeMin.className= objMlcTimeMin.className +" tmpClassReadOnly";
			    		 document.getElementsByName('oldMlcTimeHr')[0].value=objMlcTimeHr[0].value;
					  	 document.getElementsByName('oldMlcTimeMin')[0].value=objMlcTimeMin[0].value;
			    	}
			    }
			    
			 }
		 }
		var objIsRelative=$('[name="isRelative"]')[0];
		emgMlcModificationDtlJsObj.enableRelation(objIsRelative);
		
	},
	populateMlcParameterDtlFromJsonVOObj:function(arrMlcJSONObject)
	{
		//alert("arrMlcJSONObject.length :"+arrMlcJSONObject.length);
		for( var i=0; i<arrMlcJSONObject.length; i++)
		{
			mlcParamJSONObject=arrMlcJSONObject[i];
			for (var mlcParamKey in mlcParamJSONObject) {
				 if (mlcParamJSONObject.hasOwnProperty(mlcParamKey)) {
				    var mlcParamVal = mlcParamJSONObject[mlcParamKey];
				    //console.log("mlcParamKey :"+mlcParamKey+", mlcParamVal :"+mlcParamVal);
				    if(document.getElementsByName(mlcParamKey) && document.getElementsByName(mlcParamKey)[i]!=undefined ){
				    	eval("var "+mlcParamKey+"='"+mlcParamVal+"'");
				    	document.getElementsByName(mlcParamKey)[i].value=eval(mlcParamKey);
				    	var objMlcParamFld = document.getElementsByName(mlcParamKey)[i];
				    	var type= objMlcParamFld.type;
				    	if(type=="text" || type=="textarea" )
				    		objMlcParamFld.className= objMlcParamFld.className +" tmpClassReadOnly";
				    	else if(type=="select-one" && mlcParamVal!="-1")
				    		objMlcParamFld.className= objMlcParamFld.className +" tmpClassDisabled";
				    }
				    
				 }
			 }
		}
		
	},
	populateBroughtByDtlFromJsonVOObj:function(broughtByJSONObject)
	 {
		//$("#divBroughtById").show().children().show();
		for (var broughtByKey in broughtByJSONObject) {
			if(broughtByKey=="serialNo")
				continue;
			 if (broughtByJSONObject.hasOwnProperty(broughtByKey)) {
			    var broughtByVal = broughtByJSONObject[broughtByKey];
			    //alert("broughtByKey :"+broughtByKey+", broughtByVal :"+broughtByVal);
			    if(document.getElementsByName(broughtByKey) && document.getElementsByName(broughtByKey)[0]!=undefined ){
			    	eval("var "+broughtByKey+"='"+broughtByVal+"'");
			    	document.getElementsByName(broughtByKey)[0].value=eval(broughtByKey);
			    	var objBroughtByFld = document.getElementsByName(broughtByKey)[0];
			    	var type= objBroughtByFld.type;
			    	if(type=="text" || type=="textarea" )
			    		objBroughtByFld.className= objBroughtByFld.className +" tmpClassReadOnly";
			    	else if(type=="select-one" && broughtByVal!="-1")
			    		objBroughtByFld.className= objBroughtByFld.className +" tmpClassDisabled";
			    }
			    
			 }
		 }
		var objIsRelative=$('[name="isRelative"]')[0];
		emgMlcModificationDtlJsObj.enableRelation(objIsRelative);
		
	},
	finalizePopulateWork : function(){
		if(flagMlcDtl!="0" && flagMlcParamDtl!="0" && flagBroughtByDtl!="0"){
			if(flagMlcDtl=="1" && flagMlcParamDtl=="1" && flagBroughtByDtl=="1"){
				//alert("populate Successfull");
				$("#divMlcDtlId").hide();
				$("#divMlcDtlHeaderId").hide();
				$("#divRestMlcDtlId").show();
				$('.tmpClassReadOnly').prop('readOnly', true);
				$('.tmpClassDisabled').attr("disabled", true);
				
				if($("#finalOpinionId").prop("readonly") == false)
					$("#submitId").show();
				showHideWaitLoadDiv("hide");
				//alert("mlcTypeCode.length :"+$('[name="mlcTypeCode"]').length);
				
			}else if(flagMlcDtl=="2" && (flagMlcParamDtl=="1" || flagMlcParamDtl=="2")){
				$("#divRestMlcDtlId").hide();
				$("#divMlcDtlId").show();
				$("#divMlcDtlHeaderId").show();
				$('.tmpClassReadOnly').prop('readOnly', false);
				$('.tmpClassDisabled').attr("disabled", false);
				document.forms[0].reset();
				$("#submitId").show();
				/*var objMlcRadio= $('[name="mlcRadio"]');
				for(var i=0; i<objMlcRadio.length; i++)
					objMlcRadio[i].checked=false;*/
			}else{
				$('.tmpClassReadOnly').prop('readOnly', false);
				$('.tmpClassDisabled').attr("disabled", false);
			}
		}
	},
	broughtBy : function (obj)
	{
		//alert("inside broughtBy() -->> obj.checked :"+obj.checked);
		if(obj.checked){
			$('[name="isRelative"]').validatebox({validType: ['selectCombo[-1]']});
	 		$('[name="broughtByName"]').validatebox({required: true, validType: 'alpha'});
	 		$('[name="broughtByLocation"]').validatebox({required: true,validType: 'alphaNumeric'});
	 	
			obj.value="1";
			$("#divBroughtById2").slideDown("slow");
		}else{
			//alert("fsfsdf");
			$('[name="broughtByRelationCode"]').validatebox({validType:null});
			$('[name="isRelative"]').validatebox({validType: null});
			$('[name="broughtByName"]').validatebox({required: false});
	 		$('[name="broughtByLocation"]').validatebox({required: false});
	 		$('[name="broughtByPhone"]').validatebox({required: false});
	 		$('[name="broughtByAddress"]').validatebox({required:false});
	 
			obj.value="0";
			$("#divBroughtById2").slideUp("slow");
			$('["isRelative"]')[0].value="-1";
			$('["broughtByRelationCode"]')[0].value="-1";
			$('["broughtByName"]')[0].value="";
			$('["broughtByLocation"]')[0].value="";
			$('["constableDesig"]')[0].value="";
			$('["constableBadgeNo"]')[0].value="";
			$('["broughtByPhone"]')[0].value="";
			$('["broughtByAddress"]')[0].value="";
		}
		emgMlcModificationDtlJsObj.enableRelation($('[name="isRelative"]')[0]);

	},
	enableRelation : function(obj)
	{	
		//alert("enableRelation() -->  obj.value :"+obj.value);
		var objIsBroughtBy= $('[name="isBroughtBy"]')[0];
		if(objIsBroughtBy.checked){
			$('[name="broughtByName"]').validatebox({required: true, validType: 'alpha'});
	 		$('[name="broughtByLocation"]').validatebox({required: true,validType: 'alphaNumeric'});
		}
		if(obj.value=="1")///////relative
		{
			if(objIsBroughtBy.checked){
				$('[name="broughtByAddress"]').validatebox({required: true, validType: 'alphaNumeric'});
				$('[name="broughtByPhone"]').validatebox({required: true, validType: 'mobile'});
			}
			
	 		$("#divNameBroughtById").html("<font color='#FF0000'>*</font>Relative Name");
	 		$('[name="broughtByRelationCode"]')[0].disabled=false;
	 		$("#divPhoneBroughtById").show();
			
	 		if(objIsBroughtBy.checked){
	 			$('[name="broughtByRelationCode"]').validatebox({required: true,validType: ['selectCombo[0]']});
	 			$('[name="constableDesig"]').validatebox({required : false, validType : 'alphaNumeric'});
	 			$('[name="constableBadgeNo"]').validatebox({required : false, validType : 'alphaNumeric'});
	 		}
			
	 		$('[name="constableDesig"]')[0].value = "";
	 		$('[name="constableBadgeNo"]')[0].value = "";
			$("#divPoliceDetailId").hide();
			
			$('[name="policeStation"]')[0].value = "";
			$("#divPoliceStnId").hide();
			
		}
		else 
		{
			$('[name="broughtByPhone"]').validatebox({required: false});
	 		$('[name="broughtByAddress"]').validatebox({required:false});
			$("#broughtByRelationCodeId").removeClass('validatebox-invalid');
			$('[name="broughtByRelationCode"]')[0].disabled=true;
			
			/////////
			if(obj.value=="2")/////////POlICE Detail
			{
				//alert("2");
				if(objIsBroughtBy.checked){
					$('[name="broughtByPhone"]').validatebox({required: false});
					$('[name="broughtByAddress"]').validatebox({required:false});
				}
		 		
		 		$("#divNameBroughtById").html("<font color='#FF0000'>*</font>Officer Name");
		 		$("#divPoliceDetailId").show();
		 		$("#divPoliceStnId").show();
		 		$("#divPhoneBroughtById").hide();
				
		 		if(objIsBroughtBy.checked){
		 			$('[name="constableDesig"]').validatebox({required: true,validType: 'alphaNumeric'});
		 			$('[name="constableBadgeNo"]').validatebox({required: true,validType: 'alphaNumeric'});
		 			$('[name="broughtByRelationCode"]').validatebox({validType: null});
		 		}
		 		$('[name="broughtByRelationCode"]')[0].value = "-1";
				
				$('[name="broughtByPhone"]')[0].value = "";
				$('[name="broughtByAddress"]')[0].value = "";
			}
			else 
			{
				if(objIsBroughtBy.checked){
					$('[name="broughtByAddress"]').validatebox({required: true,validType: 'alphaNumeric'});
					$('[name="broughtByPhone"]').validatebox({required: true,validType: 'mobile'});
				}
		 		
		 		$("#divPoliceDetailId").hide();
		 		$("#divPoliceStnId").show();
				
		 		if(objIsBroughtBy.checked){
		 			$('[name="constableDesig"]').validatebox({required: false});
		 			$('[name="constableBadgeNo"]').validatebox({required: false});
		 		}
		 		
		 		/////////////
		 		// in order to change the label "name" of brought by to EMT name & to diplay the combo for vehicle in case of choice by 108
				if(obj.value=="3")
				{
					$("#divPhoneBroughtById").hide();
					$("#divNameBroughtById").html("<font color='#FF0000'>*</font>Name");
					
					if(objIsBroughtBy.checked){
						$('[name="broughtByAddress"]').validatebox({required : true, validType : 'alphaNumeric' });
						$('[name="broughtByPhone"]').validatebox({required : true, validType : 'mobile'});
						$('[name="broughtByRelationCode"]').validatebox({validType: null});
					}
					
					$("#divPoliceDetailId").hide();
					$("#divPoliceStnId").hide();
					
					if(objIsBroughtBy.checked){
						$('[name="constableDesig"]').validatebox({required : false });
						$('[name="constableBadgeNo"]').validatebox({required : false });
					}

					$('[name="broughtByRelationCode"]')[0].value = "-1";
					$('[name="constableDesig"]')[0].value = "";
					$('[name="constableBadgeNo"]')[0].value = "";
					$('[name="policeStation"]')[0].value = "";
				}
				if (obj.value == "-1")///////Select Value
				{
					$('[name="broughtByRelationCode"]').validatebox({validType: null});
					$("#divNameBroughtById").html("<font color='#FF0000'>*</font> Name");
					$("#divPhoneBroughtById").show();
					$("#divPoliceDetailId").hide();
					$("#divPoliceStnId").hide();
					
					if(objIsBroughtBy.checked){
						$('[name="broughtByAddress"]').validatebox({required : false});
						$('[name="broughtByPhone"]').validatebox({required : false});
						$('[name="broughtByRelationCode"]').validatebox({validType : null });
						$('[name="isRelative"]').validatebox({validType : null});
						$('[name="constableDesig"]').validatebox({required : false});
						$('[name="constableBadgeNo"]').validatebox({required : false});
						$('[name="broughtByLocation"]').validatebox({required : false});
						$('[name="broughtByName"]').validatebox({required : false});
					}

					$('[name="isRelative"]')[0].value = "-1";
					$('[name="broughtByRelationCode"]')[0].value = "-1";
					$('[name="broughtByName"]')[0].value = "";
					$('[name="broughtByLocation"]')[0].value = "";
					$('[name="constableDesig"]')[0].value = "";
					$('[name="constableBadgeNo"]')[0].value = "";
					$('[name="broughtByPhone"]')[0].value = "";
					$('[name="broughtByAddress"]')[0].value = "";
					$('[name="isRelative"]')[0].value = "-1";
					$('[name="policeStation"]')[0].value = "";

				}
			}
		}
		
		if(!objIsBroughtBy.checked){
			
			$('[name="broughtByPhone"]').validatebox({required: false});
	 		$('[name="broughtByAddress"]').validatebox({required: false});
	 		$('[name="constableDesig"]').validatebox({required: false});
	 		$('[name="constableBadgeNo"]').validatebox({required: false});
		}
		
	}
};

var emgMlcDtlJsObj = {
	broughtBy : function (obj){
		emgMlcModificationDtlJsObj.broughtBy(obj);
	},
	enableRelation : function(obj){
		emgMlcModificationDtlJsObj.enableRelation(obj);
	}
};


function showHideWaitLoadDiv(mode){
	if(mode=="show"){
		//$('.overlay2').show();
		if(parent.frameElement !=null && parent.frameElement!=undefined && parent.frameElement.id=="frmMain"){
			parent.parent.ajaxStart();
		}else{
			parent.ajaxStart();
		}
	}else if(mode=="hide"){
		//$('.overlay2').hide();
		if(parent.frameElement !=null && parent.frameElement!=undefined && parent.frameElement.id=="frmMain"){
			parent.parent.ajaxComplete();
		}else{
			parent.ajaxComplete();
		}
	}
	
}

function submitForm(mode) 
{
	document.forms[0].action = mode + "EmgMlcModificationDtl.action";
	document.forms[0].submit();
}

//////////////////////

function setPreviosValue(elem, evt){
	prevValue = elem.value;
}  

function moveToRightBox(elem, evt){
	val=elem.value;
	if(val<=23){
		maxLength =elem.getAttribute('maxlength');
		i=0;
		if(val.length==maxLength){
			for(i=0; i<prevValue.length;i++)
				if(prevValue.charAt(i)!=val.charAt(i))
					break;
		}
		if(i==maxLength-1){
			if(elem.name == 'mlcTimeHr'){
				document.getElementsByName("mlcTimeMin")[0].focus();
				
			}	
		}
	}
}

function mlcTimeValidateFn(mlcDateId_p,mlcHrId_p,mlcMinId_p, strEpisodeDtWithTime_p,format){
	var mlcDate = $("#"+mlcDateId_p).val();
	var mlcHr = $("#"+mlcHrId_p).val();
	var mlcMin = $("#"+mlcMinId_p).val();
	var mlcSec = "00";
	var episodeDate =strEpisodeDtWithTime_p;	// $('[name="'+episodeDate_p+'"]')[index];
	if(mlcHr=="")
		mlcHr="00";
			
	if(mlcMin=="")
		mlcMin="00";
	
	var mlcDateHrMin= mlcDate+" "+mlcHr+":"+mlcMin+":"+mlcSec;
	
	var d1 = convertStrToDate(mlcDateHrMin, format);
	var d2 = convertStrToDate(episodeDate, format);
	var ctdt = new Date();
	
	
	//alert("d1 :"+d1+",\nd2 :"+d2);
	if(d1<d2){
		return "MLC time should not less than episode Date.";
	}
	
	d2.setDate(d2.getDate() + 1);
	if(d1>d2){
		return "MLC time should not greater than 24 hour more than episode date.";
	}
	if(d1>ctdt){
		return "MLC Date Should not Greater than current Date.";
	}
	
	return "";	
}

function mlcTimeValidateModificationFn(oldMlcTimeHrId,oldMlcTimeMinId,mlcTimeHrId, mlcTimeMinId){
	
	
	var oldmlcHr = $("#"+oldMlcTimeHrId).val();
	var oldmlcMin = $("#"+oldMlcTimeMinId).val();
	
	var mlcHr = $("#"+mlcTimeHrId).val();
	var mlcMin = $("#"+mlcTimeMinId).val();
/*	alert(oldmlcHr);
	alert(oldmlcMin);
	alert(mlcHr);
	alert(mlcMin);*/
	if(mlcHr<oldmlcHr)
		return "MLC time should not less than episode Date.";
	if(mlcHr==oldmlcHr)
		{
		if(mlcMin<oldmlcMin)
			return "MLC time should not less than episode Date.";
		}
	
	
	return "";	
}
