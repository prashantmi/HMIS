$(document).ready(function(){
	/*$("#visitDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
	var today=moment().format('DD-MMM-YYYY');//new Date().toLocaleFormat('%d-%b-%Y');
	$('#visitDateId').DatePicker({
		format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
	}).val(today);
	$("#entryDateId").val($("#visitDateId").val());
	if($("#afterGoId").val()=="1"){
		if($('[name="flagMlcOnlineOffline"]')[0].value !="1"){
			$('[name="selectMlcEpisode"]').validatebox({
				validType: 'requireRadio[\'input[name=selectMlcEpisode]\', \'Please Select Alteast One Department Visit aginst which MLC needs to be created.\']'
			});
		}
		/*$("#mlcDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());
		$("#handoverDateTimeId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
		$('#mlcDateId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		$('#handoverDateTimeId').DatePicker({
			format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
		}).val(today);
		var visitRadio=$('[name="visitRadio"]');
		if(typeof visitRadio!=undefined && visitRadio.length>0){
			if(visitRadio[0].checked){
				emgMlcDtlJsObj.setNVisitNoIndex(visitRadio[0], 0);
			}
		}
	}else{
		$('[name="visitRadio"]').validatebox({
			validType: 'requireRadio[\'input[name=visitRadio]\', \'Please Select Alteast One Department Visit aginst which MLC needs to be created.\']'
		});
	}
	
	/*$("#divPatDtlTileHeaderId").click(function(){
		$("#divPatDtlTileContentId").slideToggle();
	});*/
	
	$('#submitId').click(function(e){
		//alert("1");
		if($('#EmgMlcDtl').form('validate')){
			//alert("validation true");
			$('.switchDisabledClass').attr("disabled",false);
			sortandEncodebase64($("#EmgMlcDtl"));
			submitForm('SAVE')
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
		$('.validatebox-text').removeClass('validatebox-invalid');
		if($("#afterGoId").val()=="1")
		{
			$("#mlcTimeHrId").validatebox({required: true, validType : 'valueltet[23]'});
			$("#mlcTimeMinId").validatebox({required: true, validType : 'valueltet[59]'});
			
			var visitRadio=$('[name="visitRadio"]');
			var today=new Date().toLocaleFormat('%d-%b-%Y');

			if(typeof visitRadio!=undefined && visitRadio.length>0 && visitRadio[0].checked){
				emgMlcDtlJsObj.setNVisitNoIndex(visitRadio[0], 0);
			}else{
				/*$("#mlcDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
				$('#mlcDateId').DatePicker({
					format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
				}).val(today);
			}
			/*$("#handoverDateTimeId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date());*/
			$('#handoverDateTimeId').DatePicker({
				format: 'd-M-Y',default_position :'below',start_date:today,	onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}}
			}).val(today);
			if($('[name="isBroughtBy"]')[0].checked)
				emgMlcDtlJsObj.broughtBy($('[name="isBroughtBy"]')[0]);
		}
	});
	
	$("#patMlcNoId").validatebox({required: true});
	$("#pageNoId").validatebox({validType : 'numeric'});
	
	$("#mlcDateId").validatebox({required: true});
	
	
});

var emgOfflineMlcDtlJsObj={
	showHideMlcEpisodeDtl : function(objDeptUnit,similarDeptIndex)
	{
		var id="divMlcEpisodeDtlId"+objDeptUnit.id;
		
		
		if(objDeptUnit.className=="list-plus"){
			objDeptUnit.className="list-minus";
		}
		else{
			objDeptUnit.className="list-plus";
			eval("$('.classVisitRadio"+similarDeptIndex+"').prop('checked', false)");
			
			if(!isVisitRadioSelected()){
				$('[name="selectMlcEpisode"]')[0].checked=false;
				$('[name="selectMlcEpisode"]')[0].value="0";
			}
		}
			
		eval('$("#'+id+'").slideToggle(500)');
	},
	setMlcEpisodeChkBox : function(objVisitRadio,index){
		var selectMlcEpisodeFlag=$('[name="selectMlcEpisode"]')[0].checked;
		if(objVisitRadio.checked){
			if(!selectMlcEpisodeFlag){
				$('[name="selectMlcEpisode"]')[0].checked=true;
				$('[name="selectMlcEpisode"]')[0].value="1";
			}
			emgMlcDtlJsObj.setNVisitNoIndex(objVisitRadio,index);
		}
	},
	
	onClickSelectMlcEpisode : function(objSelectMlcEpisode){
		//alert("inside onClickSelectMlcEpisode");
		if(objSelectMlcEpisode.value=="1")
			objSelectMlcEpisode.checked=true;
		else
			objSelectMlcEpisode.checked=false;
	}
};
var emgMlcDtlJsObj={	
	setNVisitNoIndex: function(objVisitRadio,index){
		//alert("inside setNVisitNoIndex");
		$("#nVisitNoIndexId").val(objVisitRadio.value);
		$("#mlcDateId").val($('[name="epiDate"]')[index].value);
		//$("#mlcTimeHrId").val("00");
		//$("#mlcTimeMinId").val("00");
		
		var strEpisodeDt= $('[name="epiDate"]')[index].value;
		var strEpisodeDtWithTime= $('[name="episodeDate"]')[index].value;
		
		/*alert("episodeDate :"+episodeDate);
		alert("episodeDateWithTime :"+episodeDateWithTime);
		alert("episodeDate+1 :"+(episodeDate+1));
		alert("episodeDate+2 :"+new Date(episodeDate+2));*/
		
		
		var mlcDateValidType0="dtltetdt['"+strEpisodeDt+"','dd-Mon-yyyy',1,'MLC Date should not be greater than 24 hour more than of Episode Date.']";
		var mlcDateValidType1="dtgtetdt['"+strEpisodeDt+"','dd-Mon-yyyy',0,'MLC Date should not be less than Episode Date.']";
		var mlcDateValidType2="dtltetctdt['MLC Date should not greater than Current Date']";

		//alert("mlcDateValidType :"+mlcDateValidType);
		$("#mlcDateId").validatebox({
			required: true,	
			validType: [mlcDateValidType0,mlcDateValidType2,mlcDateValidType1]
		});
		
		var mlcDateTimeValidType= "myValidateRule[\"mlcTimeValidateFn('mlcDateId','mlcTimeHrId','mlcTimeMinId', '"+strEpisodeDtWithTime+"','dd-Mon-yyyy hh:mm:ss')\",'msg']";
		$("#mlcTimeHrId").validatebox({required: true, validType : [mlcDateTimeValidType,'valueltet[23]']});
		$("#mlcTimeMinId").validatebox({required: true, validType : [mlcDateTimeValidType,'valueltet[59]']});
		
		
		var action 	= "/HISRegistration/registration/transactions/getBroughtByDtl_AJAXEmgMlcDtl.action?nVisitNoIndex="+(objVisitRadio.value);
		//alert(action);
	  	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data){
			//alert(data);
			emgMlcDtlJsObj.populateBroughtByDtlFromJsonVOObj(data);
			
		},error: function(errorMsg,textstatus,errorthrown) {
	        //alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	        
	    }
		});
	},
	populateBroughtByDtlFromJsonVOObj:function(broughtByJSONObject)
	  {
		//$("#divBroughtById").show().children().show();
		
		for (var broughtByKey in broughtByJSONObject) {
			 if (broughtByJSONObject.hasOwnProperty(broughtByKey)) {
			    var broughtByVal = broughtByJSONObject[broughtByKey];
			    //alert("broughtByKey :"+broughtByKey+", broughtByVal :"+broughtByVal);
			    if(document.getElementsByName(broughtByKey) && document.getElementsByName(broughtByKey)[0]!=undefined ){
			    	eval("var "+broughtByKey+"='"+broughtByVal+"'");
			    	document.getElementsByName(broughtByKey)[0].value=eval(broughtByKey);
			    }
			    
			 }
		 }
		var objIsRelative=$('[name="isRelative"]')[0];
		emgMlcDtlJsObj.enableRelation(objIsRelative);
		
	  },
	broughtBy : function (obj)
	{
		//alert("inside broughtBy()");
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
		emgMlcDtlJsObj.enableRelation($('[name="isRelative"]')[0]);

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
				if (obj.value == "-1")///////relative
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

function isVisitRadioSelected()
{
	var objVisitRadio= $('[name="visitRadio"]');
	var flagVisitRadioSelected=false;
	if(typeof objVisitRadio !=undefined && objVisitRadio.length > 0){
		for(var i=0; i<objVisitRadio.length; i++){
			if(objVisitRadio[i].checked){
				flagVisitRadioSelected=true;
				break;
			}
		}
	}
	return flagVisitRadioSelected;
}
function revertCheckThis(obj){
	if(obj.value=="1")
		obj.checked=true;
	else
		obj.checked=false;
}



function submitForm(mode) 
{
	document.forms[0].action = mode + "EmgMlcDtl.action";
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
	//alert("inside mlcTimeValidateFn");
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
	
	
	//console.log("d1 :"+d1+",\nd2 :"+d2+"\nctdt :"+ctdt);
	if(d1<d2){
		return "MLC time should not less than episode Date.";
	}
	
	d2.setDate(d2.getDate() + 1);
	if(d1>d2){
		return "MLC time should not greater than 24 hour more than episode date.";
	}
	 d1.setHours(0); 
	 d1.setMinutes(0);
	 d1.setSeconds(0); 
	if(d1>ctdt){
		return "MLC Date Should not Greater than current Date.";
	}
	
	return "";
	
}

/*Start : Surabhi
	 * Reason : for printing the certificate
	 * date : 7th oct 2016 */
window.onload = function() 
{
	//alert("Inside WINDOWS oNLOAD");
	if(document.getElementsByName("printFlag")[0].value=="1")
	{
		openMlcPrintPopup();
	}	
}

function openMlcPrintPopup()
{
	//alert("popup");
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	//alert(patCrNo);
	//var noOfCopies=document.getElementsByName("noOfCopies")[0].value;
	
	//alert(patCrNo +""+noOfCopies );
	//var path='/HISRegistration/registration/transactions/PatientDeathDetail.action?hmode=PRINTCERT&patCrNo='+patCrNo;patCRNo
	var path='/HISRegistration/registration/transactions/PRINTCERTEmgMlcDtl.action?patCRNo='+patCrNo;
	openPrintPopup(path,700,800);
	document.getElementsByName("printFlag")[0].value="0";
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
		child.moveTo(250,250);
		child.focus(); 
}
// end