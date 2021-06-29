/*
 * Js file Used in the Bulk Shift/Slot Cancellation & Reschedule Process
 * Created by Singaravelan on 13-Apr-2015
 * */

function submitForm(mode) {
	document.forms[0].action = mode + "ShiftAndSlotCancellation.action";
	document.forms[0].submit();	

}
function getActualParaIdWiseEssensials() {
	
	var action = "/HISRegistration/appointment/transactions/getActualParaIdWiseDetailShiftAndSlotCancellation.action";
	var data = {
			actualParameterReferenceId:$("#actualParameterReferenceId").val()
	  };
	
	$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			beforeSend: function() {
				$('#modal').show();$('#fade').show();
	        }, 
	        complete: function() {
	       	 	$('#modal').hide();$('#fade').hide();  
	       	 },
			success : function(returnHTML) {
				var json= eval('(' + returnHTML + ')');
				$('#appointmentTypeId').html(json["optionHTML"]);		
				showShiftDetails();
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('getActualParaIdWiseEssensials ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
}

function getShiftRelatedSlotDetails(paraId,aptDate,aptId,shiftId)
{
	var divId="slotData_"+aptDate+"_"+shiftId;
	var cancelImgId="imgCancel_"+aptDate+"_"+shiftId;
	var refreshImgId="imgRefresh_"+aptDate+"_"+shiftId;
	var imgExpnd="imgPlus_"+aptDate+"_"+shiftId;
	document.getElementsByName("selectedSlots")[0].value="";
	var action = "/HISRegistration/appointment/transactions/getSlotsShiftAndSlotCancellation.action";
	var data = {
			paraId	: paraId,
			appointmentDate	: aptDate,
			aptId	: aptId,
			shiftId : shiftId
	  };
	
	if($('#'+divId+'').is(':visible')){
		//$('#'+divId+'').hide('blind');
		$('#'+divId+'').hide();
		$('#'+imgExpnd+'').attr('src', '/HIS/hisglobal/images/plus.gif');
		$('#'+cancelImgId+'').show('blind');
		$('#'+refreshImgId+'').show('blind');
	}
	else
	{
		$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			beforeSend: function() {
				$('#modal').show();$('#fade').show();
	        }, 
	        complete: function() {
	       	 	$('#modal').hide();$('#fade').hide();  
	       	 },
			success : function(returnHTML) {
				$('#'+divId+'').html(returnHTML);//slotData^10-Apr-2015^18
				$('#'+imgExpnd+'').attr('src', '/HIS/hisglobal/images/minus.gif');
				$('#'+cancelImgId+'').hide();
				$('#'+refreshImgId+'').hide();
				$('#'+divId+'').show('blind');
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('getActualParaIdWiseEssensials ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
	}
}

function cancelShift(paraId,aptdate,aptId,shiftId,paraRefId)
{
	var data=paraId+"#"+aptdate+"#"+aptId+"#"+shiftId+"#"+paraRefId;
	showCancelReasonDialogPopup("Shift Cancel",data,150,500,1);

}

function confirmShiftCancellation(data,remarks)
{
	var relData=data.split("#");
	var action = "/HISRegistration/appointment/transactions/cancelShiftShiftAndSlotCancellation.action";
	var data = {
			paraId	: relData[0],
			appointmentDate	: relData[1],
			aptId	: relData[2],
			shiftId : relData[3],
			actualParaRefId : relData[4],
			remarks : remarks
	  };
	$.ajax({
		url : action, 
		type : "POST",
		async : true,
		data : data,
		dataType : "html",
		beforeSend: function() {
			$('#modal').show();$('#fade').show();
        }, 
        complete: function() {
       	 	$('#modal').hide();$('#fade').hide();  
       	 },
		success : function(returnHTML) {			
			$('#statusMessage').html(returnHTML);	
			$('#statusMessage').show();
			showShiftDetails();
		},
		error : function(errorMsg, textstatus, errorthrown) {
			alert('confirmShiftCancellation ' + errorMsg + " textstatus::"
						+ textstatus + " errorthrown::" + errorthrown);
			}
		});
}

function selectSlot(aptdate,startTime,endTime,shiftId,shiftSt,shiftEt,slotType,slotId,cssClass)
{
	var elmId=aptdate+"X"+startTime+"X"+endTime+"X"+shiftId+"X"+shiftSt+"X"+shiftEt+"X"+slotType+"X"+cssClass;
	var _chkelmId=aptdate+"_"+startTime+"_"+endTime+"_"+shiftId+"_"+shiftSt+"_"+shiftEt+"_"+slotType+"_"+cssClass;
	var isremoval=false;
	if($('#'+aptdate+'_'+shiftId+'').is(":checked")){
		$('#'+aptdate+'_'+shiftId+'').attr('checked', false);
		resetColorStatus(elmId);
	}
	if(document.getElementsByName("selectedSlots")[0].value!=""){
		var _newTmpStr="";var _tmpArray=new Array();
			if(document.getElementsByName("selectedSlots")[0].value.indexOf(',')>0)
			{
				_tmpArray=document.getElementsByName("selectedSlots")[0].value.replace(/,/g,'#').split('#');

				var x=0;
				for(var i=0;i<_tmpArray.length;i++){
					if(_tmpArray[i]!=_chkelmId)
					{
						x++;
						if(_newTmpStr!="")_newTmpStr+=","+_tmpArray[i];
						else _newTmpStr=_tmpArray[i];	
						
						if(x==_tmpArray.length){
							_newTmpStr+=","+_chkelmId;
							$("div[id^='"+_chkelmId.replace(/_/g,'X')+"']").css("background-color","#CCCCFF");
						}
					}
					else{
						x--;
						isremoval=true;
						resetColorStatus(elmId);
					}
				}
				
				document.getElementsByName("selectedSlots")[0].value=_newTmpStr;
			}
			else
			{
				if(document.getElementsByName("selectedSlots")[0].value==_chkelmId){
					document.getElementsByName("selectedSlots")[0].value="";
					resetColorStatus(elmId);
				}
				else{
					document.getElementsByName("selectedSlots")[0].value+=","+_chkelmId;
					$("div[id^='"+elmId+"']").css("background-color","#CCCCFF");
				}
			}
		
	}else{
		$("div[id^='"+elmId+"']").css("background-color","#CCCCFF");
		document.getElementsByName("selectedSlots")[0].value=aptdate+"_"+startTime+"_"+endTime+"_"+shiftId+"_"+shiftSt+"_"+shiftEt+"_"+slotType+"_"+cssClass;
	}
	
	showButtonDiv(aptdate+"_"+shiftId,true);
	
	document.getElementsByName("aptId")[0].value=$('#aptId_'+aptdate+'_'+shiftId+'').val();
	document.getElementsByName("allActualParameterId")[0].value=$('#paraId_'+aptdate+'_'+shiftId+'').val();
	document.getElementsByName("paraRefId")[0].value=$('#actualParaRefId_'+aptdate+'_'+shiftId+'').val();
	document.getElementsByName("shiftId")[0].value=$('#shiftId_'+aptdate+'_'+shiftId+'').val();
	document.getElementsByName("aptDate")[0].value=$('#aptDate_'+aptdate+'_'+shiftId+'').val();
}

function showCancelReasonDialogPopup(popUpTitle,data,height,width,type) {

	var windowWidth = width;
	var windowHeight = height;
	var dialogDiv="<div class='div-table' id='shiftCancelReasonDiv'><div class='div-table-row'>"
					+"<div class='div-table-col label' style='width: 50%' ><font color='red'>*</font>Cancel Reason</div>"
					+"<div class='div-table-col control' style='width: 50%;' >"
					+"<textarea rows='2' cols='20' name='cancelRemarks' id='cancelRemarks'></textarea>"
					+"</div></div>"	
					+"</div>";

	var $dialog = $('<div id="_dSlotCtId"></div>').html(dialogDiv).dialog({
		autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
		title : popUpTitle,	//show: { //effect: "clip"},
		resizable: false,
		position: ['top', 100],dialogClass: 'no-close custbtncolor',
		buttons:{
			"Save": function() {
				if($('#cancelRemarks').val()!=""){
					$(this).dialog("close");
					if(type==1)
						confirmShiftCancellation(data,$('#cancelRemarks').val());	
					else if(type==2)
						confirmSlotCancellation(data,$('#cancelRemarks').val());
				}
				else
				{
					alert("Please Enter Remarks");
				}
			},
			"Close": function() {				
				$(this).dialog("close"); 
			}
		},
		open: function() {
			 	$('.ui-widget-overlay').addClass('custoverlay');
			 	$('.ui-dialog-buttonpane').find('button:contains("Cancel")').addClass('custbtncolor');
			 	$('.ui-dialog-buttonpane').find('button:contains("Save")').addClass('custbtncolor');
		},
		close: function() {
			$(".ui-widget-overlay").removeClass('custoverlay');
	    }   
	});
	$dialog.dialog('open');
}

function clickOnCheckbox(obj)
{
	var i,selectedSlots=new Array(),_tmpId=[],_tmpStatus;
	
	for(i=1;i<=$("div[id^='slotRowsDetail_"+obj.id+"']").length;i++){	
		$('#slotRowsDetail_'+obj.id+'_'+i+'').children('div').each(function () {
			if($('#'+obj.id+'').is(":checked")){
				$(this).css("background-color","#CCCCFF");
				selectedSlots.push(this.id.replace(/X/g, '_'));
				showButtonDiv(obj.id,true);
				document.getElementsByName("selectedSlots")[0].value=selectedSlots;
			}
			else{
				_tmpId=this.id.replace("_","#").split("#");
				_tmpStatus=_tmpId[_tmpId.length-1];
							
				resetColorStatus(this.id);
				showButtonDiv(obj.id,false);
				document.getElementsByName("selectedSlots")[0].value=selectedSlots;
			}
		});
	}
	document.getElementsByName("aptId")[0].value=$('#aptId_'+obj.id+'').val();
	document.getElementsByName("allActualParameterId")[0].value=$('#paraId_'+obj.id+'').val();
	document.getElementsByName("paraRefId")[0].value=$('#actualParaRefId_'+obj.id+'').val();
	document.getElementsByName("shiftId")[0].value=$('#shiftId_'+obj.id+'').val();
	document.getElementsByName("aptDate")[0].value=$('#aptDate_'+obj.id+'').val();

	

}

function resetColorStatus(slotDataId)
{
	var _tmpId=[],_tmpStatus;
	_tmpId=slotDataId.replace("X","#").split("#");
	_tmpStatus=_tmpId[_tmpId.length-1];
	if(_tmpStatus=="Free"||_tmpStatus.indexOf("Free")>0)
		$("div[id^='"+slotDataId+"']").css("background-color","#C2E5C15");
	if(_tmpStatus=="Booked"||_tmpStatus.indexOf("Booked")>0)
		$("div[id^='"+slotDataId+"']").css("background-color","#F6A77B");
	if(_tmpStatus=="Elapsed"||_tmpStatus.indexOf("Elapsed")>0)
		$("div[id^='"+slotDataId+"']").css("background-color","#A5A5A5");
	if(_tmpStatus=="OverBooked"||_tmpStatus.indexOf("OverBooked")>0)
		$("div[id^='"+slotDataId+"']").css("background-color","#A985D5");
	if(_tmpStatus=="Hold"||_tmpStatus.indexOf("Hold")>0)
		$("div[id^='"+slotDataId+"']").css("background-color","#CCCCFF");
}

function showButtonDiv(id,flag){
	
	if(flag){		
		$('#submitCancelId').show();
		$('#submitRenewalId').show();
	}
	else{
		$('#submitCancelId').hide();
		$('#submitRenewalId').hide();
	}
		
}

function cancelSelectedSlots()
{
	var selSlots=$('[name="selectedSlots"]').val();
	var paraId=$('[name="allActualParameterId"]').val();
	var aptId=$('[name="aptId"]').val();
	var paraRefId=$('[name="paraRefId"]').val();
	var shiftId=$('[name="shiftId"]').val();
	var aptdate=$('[name="appointmentDate"]').val();

	var data=paraId+"#"+aptdate+"#"+aptId+"#"+shiftId+"#"+paraRefId+"#"+selSlots;
	showCancelReasonDialogPopup("Slot Cancel",data,150,500,2);

	
}

function confirmSlotCancellation(data,remarks){
	var relData=data.split("#");
	var divId="slotData_"+relData[1]+"_"+relData[3];
	var cancelImgId="imgCancel_"+relData[1]+"_"+relData[3];
	var refreshImgId="imgRefresh_"+relData[1]+"_"+relData[3];
	var imgExpnd="imgPlus_"+relData[1]+"_"+relData[3];
	var action = "/HISRegistration/appointment/transactions/cancelSlotsShiftAndSlotCancellation.action";
	var data = {
			paraId	: relData[0],
			aptId	: relData[2],
			shiftId	: relData[3],
			actualParaRefId : relData[4],
			slotsData	: relData[5],
			remarks : remarks
	  };
	$.ajax({
		url : action, 
		type : "POST",
		async : true,
		data : data,
		dataType : "html",
		beforeSend: function() {
			$('#modal').show();$('#fade').show();
        }, 
        complete: function() {
       	 	$('#modal').hide();$('#fade').hide();  
       	 },
		success : function(returnHTML) {
			$('#'+divId+'').hide('blind');
			$('#'+imgExpnd+'').attr('src', '/HIS/hisglobal/images/plus.gif');
			$('#'+cancelImgId+'').show('blind');
			$('#'+refreshImgId+'').show('blind');
			$('#statusMessage').html(returnHTML);	
			$('#statusMessage').show();
			document.getElementsByName("selectedSlots")[0].value="";
			
		},
		error : function(errorMsg, textstatus, errorthrown) {
			alert('confirmSlotCancellation ' + errorMsg + " textstatus::"
						+ textstatus + " errorthrown::" + errorthrown);
			}
		});
}


function showRescheduleDialogPopup(popUpTitle,data,height,width,type) {

	var windowWidth = width;
	var windowHeight = height;
	var dialogDiv="<div class='div-table' id='shiftCancelReasonDiv'>" 
					+"<div class='div-table-row'>"
					+"<div class='div-table-col label' style='width: 50%' ><font color='red'>*</font>Reschedule Date</div>"
					+"<div class='div-table-col control' style='width: 50%;' >"
					+"<input id='rescheduleDateId' tabindex='1' type='text' readonly='readonly' name='rescheduleDate' style='width: 85px;'>"
					+"</div></div>"
					+"<div class='div-table-row'>"
					+"<div class='div-table-col label' style='width: 50%' ><font color='red'>*</font>Cancel Reason</div>"
					+"<div class='div-table-col control' style='width: 50%;' >"
					+"<textarea rows='2' cols='20' name='cancelRemarks' id='cancelRemarks'></textarea>"
					+"</div></div>"	
					+"</div>";

	var $dialog = $('<div id="_dSlotCtId"></div>').html(dialogDiv).dialog({
		autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
		title : popUpTitle,	//show: { //effect: "clip"},
		resizable: false,
		position: ['top', 100],dialogClass: 'no-close custbtncolor',
		buttons:{
			"Save": function() {
				if($('#cancelRemarks').val()!=""){
					$(this).dialog("close");
					if(type==1)
						confirmShiftCancellation(data,$('#cancelRemarks').val(),$('#cancelRemarks').val());	
					else if(type==2)
						confirmSlotCancellation(data,$('#cancelRemarks').val(),$('#cancelRemarks').val());
				}
				else
				{
					alert("Please Enter Remarks");
				}
			},
			"Close": function() {				
				$(this).dialog("close"); 
			}
		},
		open: function() {
			 	$('.ui-widget-overlay').addClass('custoverlay');
			 	$('.ui-dialog-buttonpane').find('button:contains("Cancel")').addClass('custbtncolor');
			 	$('.ui-dialog-buttonpane').find('button:contains("Save")').addClass('custbtncolor');
			 	$( "#rescheduleDateId" ).datepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat:"dd-M-yy",
					onSelect: function(d,i){
				          if(d !== i.lastVal){
				              $(this).change();
				          }
				     }
				}).datepicker("setDate", "0");
		},
		close: function() {
			$(".ui-widget-overlay").removeClass('custoverlay');
	    }   
	});
	$dialog.dialog('open');
}

function rescheduleShift(paraId,aptdate,aptId,shiftId,paraRefId)
{
	var data=paraId+"#"+aptdate+"#"+aptId+"#"+shiftId+"#"+paraRefId;
	showRescheduleDialogPopup("Shift Reschedule",data,200,500,1);

}