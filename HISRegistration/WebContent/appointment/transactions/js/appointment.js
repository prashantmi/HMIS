/*var imported = document.createElement('script');
imported.src = '/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js';
document.head.appendChild(imported);
var imported = document.createElement('script');
imported.src = '/HIS/hisglobal/js/jquery/jquery.easyui.js';
document.head.appendChild(imported);
var imported = document.createElement('script');
imported.src = '/HIS/hisglobal/js/jquery/jquery-ui.js';
document.head.appendChild(imported);
var imported = document.createElement('script');
imported.src = '/HIS/hisglobal/js/jquery/jquery.simplemodal.js';
document.head.appendChild(imported);
var imported = document.createElement('script');
imported.src = '/HISRegistration/hisglobal/js/jquery.plugin.js';
document.head.appendChild(imported);
var imported = document.createElement('script');
imported.src = '/HISRegistration/hisglobal/js/jquery.timeentry.js';
document.head.appendChild(imported);*/
/*
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../appointment/transactions/css/appointment.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/jqueryExtValidationToolTip.css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
<link href="/HISRegistration/hisglobal/css/jquery.timeentry.css" rel="stylesheet">
<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet" type="text/css">


*/
function BookSlot(aptdate,startTime,endTime,shiftId,shiftSt,shiftEt,apptType,slotId)
{	
	//alert("aptdate--"+aptdate+"startTime--"+startTime+"endTime--"+endTime+"shiftId--"+shiftId+"shiftSt--"+shiftSt+"shiftEt--"+shiftEt+"apptType--"+apptType+"slotId--"+slotId);	
	//alert("$('#appointmentTypeId').val() "+parent.$('[name="appointmentTypeId"]').val());
	//var confirm();
	var flagPass = true;
	apptType = parent.$('[name="appointmentTypeId"]').val();
	opdSlotsAll = $('[name="opdSlotsAll"]').val();
	ipdSlotsAll = $('[name="ipdSlotsAll"]').val();
	opdSlotsBooked = $('[name="opdSlotsBooked"]').val();
	ipdSlotsBooked = $('[name="ipdSlotsBooked"]').val();
	//alert(opdSlotsAll+"\n"+ipdSlotsAll+"\n"+opdSlotsBooked+"\n"+ipdSlotsBooked+"\n"+apptType);
	//strResultTag+="<input type='hidden' name='opdSlotsAll' value='"+ws.getString(12)+"'><input type='hidden' name='ipdSlotsAll' value='"+ws.getString(13)+"'><input type='hidden' name='opdSlotsBooked' value='"+ws.getString(14)+"'><input type='hidden' name='ipdSlotsBooked' value='"+ws.getString(15)+"'>";
	//alert(parent.$('[name="isProcessReschedule"]').val());
	if((undefined != parent.$('[name="isProcessReschedule"]').val()) && parent.$('[name="isProcessReschedule"]').val()==1){
		//do nothing
	}else{

		if(apptType==1 && opdSlotsAll!=0 ){//apptType -- 1 = OPD, 2 = IPD, 3 = EMG
			var availOpdSlots = opdSlotsAll - opdSlotsBooked;
			if(availOpdSlots<=0){
				flagPass = false;
				alert("OPD SLots Limit Over");
			}
		}else if(apptType==2 && ipdSlotsAll!=0 ){
			var availIpdSlots = ipdSlotsAll - ipdSlotsBooked;
			if(availIpdSlots<=0){
				flagPass = false;
				alert("IPD SLots Limit Over");
			}
		}else{
			
		}
		
	}

if(flagPass){
	var slotType="Normal";
	if(apptType=="5")//Overbooked
		slotType="OverBooked";
	
	var c=confirm("Appointment Date: "+aptdate+"   Appointment Time:"+startTime+"\nAppointment Type: "+slotType+"\n\n Are You Sure To Save");
	
	if(c)
	{
		var action = "/HISRegistration/appointment/transactions/checkSlotAvailibiltyAppointmentTags.action";
		var appointmentForId="";

		if (typeof $('[name="appointmentForId"]').val() === "undefined")
			appointmentForId= $('[name="aptFor"]').val();
		else
			appointmentForId=  $('[name="appointmentForId"]').val();
	
		//JSON Data
		var data = 
		{
				appointmentForId : appointmentForId,	
				actualParameterReferenceId :$('[name="actualParaRefId"]').val(),
				allActualParameterId:$("#allActualParameterId").val(),
				appointmentDate:$('[name="aptForDate"]').val(),
				//shiftST:$('[name="shiftST"]').val(),shiftET:$('[name="shiftET"]').val(),
				shiftST:shiftSt,
				shiftET:shiftEt,
				slotST:startTime,
				slotET:endTime,
				actualParameterId: $('[name="actualParameterId"]').val()	
		};
		
		$.ajax
		(
				{
					url : action, 
					type : "POST",
					async : true,
					data : data,
					dataType : "html",
					success : function(returnHTML) 
					{
						//alert("slotStatus---"+returnHTML);
						if(returnHTML=='2'||returnHTML=='4')//Slot Is Not Free (2-Already Booked,4-Elapsed)
						{
							alert("The selected Slot Is Already Booked/Hold By Another User Or Slot Elapsed");
							document.getElementById(slotId).className="Booked";
							//document.getElementById(slotId).onclick="";
							return;
						}
						else if(returnHTML=='1' || returnHTML=='5')//Slot Is Free or OverBooked(1-Free,5-OverBooked)
						{
							var all = $(".Hold").map(function() {
							    return this.id;
							}).get();
							
							var _allHold=all.join();							
							if(_allHold.indexOf(',')>0)	_allHold=_allHold.split(",")[0];
							if(_allHold!=""){
								_allHold="#"+_allHold;							
								$( _allHold ).removeClass( "Hold" ).addClass( "Free" );
							}							
							
							holdSlot(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,slotId);
							document.getElementById(slotId).className="Hold";
							//alert(parent.document.getElementById("freeSlotLabel").innerHTML);
							parent.document.getElementById("freeSlotLabel").innerHTML='<font size="2px">'+aptdate+'&nbsp;<span id="freeSlotTime">'+startTime+'</span>&nbsp;Free&nbsp;</font>';
							parent.document.getElementsByName("slotST")[0].value=startTime;
							parent.document.getElementsByName("slotET")[0].value=endTime;
							return;
						}
						else
						{
							document.getElementById(slotId).className="Hold";
						}							
					},
					error : function(errorMsg, textstatus, errorthrown) 
					{
							alert('BookSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
					}
				}
		);		
	}
}//flagPass Ends
	
	//if(datecheckWithMsg("sysdate","schedulDate","Appointment cannot be given for date less than current date !")==false)
	//	  return false;	  
	
	//document.getElementsByName("appointmentDate")[0].value=document.getElementsByName("schedulDate")[0].value;
	//document.getElementsByName("appointmentStartTime")[0].value=startTime;
	//document.getElementById("DIV_APPTDATE").innerHTML=document.getElementsByName("appointmentDate")[0].value;
	//document.getElementById("DIV_APPTSTARTTIME").innerHTML=startTime;	
	//document.getElementsByName("shiftId")[0].value=shiftId;
	
	//Popup.showModal('DIV_APPOINTMENTSLOTBOOK');
	
	/*var  url='/AHIMS/appointment/transactions/AppointmentProcess.cnt?';
	url+='hmode=HOLDAPPOINTMENTSLOT';   
	url+='&divId=DIV_APPOINTMENTSLOTBOOK';
	url+='&appointmentType='+apptType;	 	 
   	url+="&appointmentStartTime="+startTime;
   	url+="&appointmentDate="+document.getElementsByName("appointmentDate")[0].value;
   	url+="&shiftId="+shiftId;
   	url+="&actualParaRefId="+document.getElementsByName("actualParaRefId")[0].value;
   	url+="&inithmode="+document.getElementsByName("inithmode")[0].value;
   	url+="&instanceNo="+document.getElementsByName("instanceNo")[0].value;
   			
   	//HISAlert('info','',"url---" + url); 
 	httpRequest("GET",url,true);*/	 
}

//To Hold the Selected Slot, Added by Singaravelan on 27-Jan-2015
function holdSlot(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,slotId)
{
	//alert("_pSlotDetails in apt js"+_pSlotDetails);
	var action = "/HISRegistration/appointment/transactions/holdSlotAppointmentTags.action";
	
	var data = 
	{
			appointmentForId : appointmentForId,	
			actualParameterReferenceId :$('[name="actualParaRefId"]').val(),
			allActualParameterId:$("#allActualParameterId").val(),
			appointmentDate:$('[name="aptForDate"]').val(),
			shiftST:$('[name="shiftST"]').val(),
			shiftET:$('[name="shiftET"]').val(),
			slotST:startTime,
			slotET:endTime,
			actualParameterId: $('[name="actualParameterId"]').val(),
			shiftId:shiftId,
			slotType:apptType
	};
	
	$.ajax
	(
			{
				url : action, 
				type : "POST",
				async : true,
				data : data,
				dataType : "html",
				success : function(returnHTML) 
				{	
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{
						alert('ConfirmSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
				}
			}
	);	
}

//To Confirm/Save the Selected Slot Added by Singaravelan on 27-Jan-2015
function confirmSlot(appointmentForId,aptdate,startTime,endTime,shiftId,apptType)
{
	var action= "/HISRegistration/appointment/transactions/checkSlotAvailibiltyOnSaveTimeAppointmentTags.action";
	var isConfirm=false;
	//JSON Data
	var data = 
	{
			appointmentForId : appointmentForId,	
			actualParameterReferenceId :$('[name="actualParaRefId"]').val(),
			allActualParameterId:$("#allActualParameterId").val(),
			appointmentDate:$('[name="aptForDate"]').val(),
			shiftST:$('[name="shiftST"]').val(),
			shiftET:$('[name="shiftET"]').val(),
			slotST:startTime,
			slotET:endTime,
			actualParameterId: $('[name="actualParameterId"]').val(),
			shiftId:shiftId,
			slotType:apptType
	};
	
	$.ajax
	(
			{
				url : action, 
				type : "POST",
				async : false,
				data : data,
				dataType : "html",
				success : function(returnHTML) 
				{
					//alert("In Side Confirm Slot "+returnHTML);
					if(returnHTML=='1' || returnHTML=='5')
					{
						_saveSlot(appointmentForId,aptdate,startTime,endTime,shiftId,apptType);
						isConfirm=true;
					}
					else
					{
						alert("The Slot has been Alreay Booked or Elapsed! \n Please Select Another Slot");
						isConfirm=false;			
						
					}				
													
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{
						alert('ConfirmSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
						return false;
				}
			}
	);	
	return isConfirm;
}

//To Confirm/Save the Selected Slot Added by Singaravelan on 27-Jan-2015
function _saveSlot(appointmentForId,aptdate,startTime,endTime,shiftId,apptType)
{
	var action= "/HISRegistration/appointment/transactions/saveAppointmentTags.action";
	
	//JSON Data
	var data = 
	{
			appointmentForId : appointmentForId,	
			actualParameterReferenceId :$('[name="actualParaRefId"]').val(),
			allActualParameterId:$("#allActualParameterId").val(),
			appointmentDate:$('[name="aptForDate"]').val(),
			shiftST:$('[name="shiftST"]').val(),
			shiftET:$('[name="shiftET"]').val(),
			slotST:startTime,
			slotET:endTime,
			actualParameterId: $('[name="actualParameterId"]').val(),
			shiftId:shiftId,
			slotType:apptType
	};
	
	$.ajax
	(
			{
				url : action, 
				type : "POST",
				async : false,
				data : data,
				dataType : "html",
				success : function(returnHTML) 
				{
					var appAndQueueNo=returnHTML;
					document.getElementsByName("appointmentNo")[0].value=appAndQueueNo.split('#')[0];
					document.getElementsByName("appointmentQueueNo")[0].value=appAndQueueNo.split('#')[1];
					return ;											
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{
						alert('ConfirmSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
						return false;
				}
			}
	);	
}

//To Open the AppointmentTag View Popup for the given CRNO,and PARAID String, Added by Singaravelan on 28-Jan-2015
function openApptPopup(obj)
{
	//alert("inside");
	var showAppointmentDateInsidePopup="";
	var crNo=$('[name="patCrNo"]').val();
	var paraId=$('[name="allactualParameterId"]').val();
	var aptDate=$('[name="aptForDate"]').val();
	var aptId=$('[name="aptFor"]').val();
	if(document.getElementsByName("showAppointmentDateInsidePopup")[0]!=undefined)
	var showAppointmentDateInsidePopup=document.getElementsByName("showAppointmentDateInsidePopup")[0].value;
	//alert("--crNo--"+crNo+"--paraId--"+paraId+"--aptDate--"+aptDate+"--aptId--"+aptId);
	//alert("showAppointmentDateInsidePopup"+showAppointmentDateInsidePopup);
	var url = "/HISRegistration/appointment/transactions/makeAppointmentTagHTMLWholeTagAppointmentTags.action?crNo="+crNo+"&paraId="+paraId+"&appointmentDate="+aptDate+"&aptId="+aptId+"&showAppointmentDateInsidePopup="+showAppointmentDateInsidePopup;
	showJQueryDialogPopup(url,"Appointment Slots",350,280);
	
	//var someSessionVariable = '@Session["SomeSessionVariable"]';
}
function openApptPopupNew()
{
	//alert("Inside openApptPopupNew");
	var iframe=  parent.document.getElementById("framePopup");	
	var crNo=$('[name="patCrNo"]').val();
	var paraId=$('[name="allactualParameterId"]').val();
	var aptDate=$('[name="appointmentDatePopup"]').val();
	var aptId=$('[name="aptFor"]').val();
	var today=new Date();//.toLocaleFormat('%d-%b-%Y');
	var _date=$.datepicker.parseDate("dd-M-yy", $('[name="appointmentDatePopup"]').val());
	
	if(!(_date.getTime()>=today.getTime()))
		{
		alert("Date cannot be less than current date");
		var aptDate=$('[name="aptForDate"]').val();
		$('[name="appointmentDatePopup"]').val(aptDate) ; 
			$('#appointmentDate').focus();
		return false;
		}
	
	if(crNo == undefined){
		if(parent.document.getElementsByName("patCrNo")[0]!=undefined)
			crNo= parent.document.getElementsByName("patCrNo")[0].value;
		else
			crNo= parent.document.getElementsByName("crNo")[0].value;
	}
	
	
	if(paraId == undefined)
		paraId= parent.document.getElementsByName("allactualParameterId")[0].value;
	if(aptId == undefined)
		aptId= parent.document.getElementsByName("aptFor")[0].value;
	//alert("--crNo--"+crNo+"--paraId--"+paraId+"--aptDate--"+aptDate+"--aptId--"+aptId);
	var url = "/HISRegistration/appointment/transactions/makeAppointmentTagHTMLWholeTagAppointmentTags.action?crNo="+crNo+"&paraId="+paraId+"&appointmentDate="+aptDate+"&aptId="+aptId;
	parent.document.getElementById('framePopup').setAttribute('src', url);
	//alert($("#framePopup",parent.document).contents().find("body").html());

}

//To Show the Another Shift On Clicking the Next Button Added by Singaravelan on 29-Jan-2015
function DisplayDifftentShift(side,currentShift)
{
	//alert("side "+side+"currentShift "+currentShift);
	
	var nextShift=+currentShift + +side;
	
	var curShiftTabHeader="#TABLESHIFT_"+(currentShift);
	var curShiftTabDetail="#TABLESHIFT_SLOT_"+(currentShift);

	var nextShiftTabHeader="#TABLESHIFT_"+(nextShift);
	var nextShiftTabDetail="#TABLESHIFT_SLOT_"+(nextShift);
	
	if (document.getElementById("TABLESHIFT_"+(nextShift)) === null)
		alert("No Shift Available");
	else
	{
		$(curShiftTabHeader).hide("blind");
		$(curShiftTabDetail).hide("blind");
		
		$(nextShiftTabHeader).show("blind");
		$(nextShiftTabDetail).show("blind");
	}
		
}

//To Show the Src in the Jquery Dialog Popup, Added by Singaravelan on 28-Jan-2015
function showJQueryDialogPopup(src,popUpTitle,height,width) {

	var iframe = $('<iframe style="border: 0px; " id="framePopup" width="100%" height="100%"></iframe>');
	
	//alert("Inside showJQueryDialogPopup");
	var windowWidth = $(window).width() - 100;
	var windowHeight = height;
		var $dialog = $('<div id="_dSlotCtId"></div>').append(iframe).appendTo("body").dialog({
		autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
		title : popUpTitle,	show: { effect: "clip"},resizable: false,
		position: ['top', 100],dialogClass: 'no-close custbtncolor',
		buttons:{
			"Close": function() {	
				var aptDate=$('#framePopup').contents().find('#appointmentDatePopup').val()
				$('[name="appointmentDate"]').val(aptDate) ; 
				$('[name="aptForDate"]').val(aptDate);
				$(this).dialog("close"); 
			}
		},
		open: function() {
			 	$('.ui-widget-overlay').addClass('custoverlay');
			 	$('.ui-dialog-buttonpane').find('button:contains("Cancel")').addClass('custbtncolor');
			 	$('.ui-dialog-buttonpane').find('button:contains("Save As New Patient")').addClass('custbtncolor');
		},
		close: function() {
				var aptDate=$('#framePopup').contents().find('#appointmentDatePopup').val()
				$('[name="appointmentDate"]').val(aptDate) ; 
				$('[name="aptForDate"]').val(aptDate);
				iframe.attr("src", "");
				$(this).dialog("destroy");
				$(this).remove();
				$(".ui-widget-overlay").removeClass('custoverlay');
				
			
	    }   
	});
		$("#_dSlotCtId>#framePopup").attr("src", src);
	$dialog.dialog('open');
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



function addVipApp()
{
	openCustomPopup('#ADDVIPURGENTDIV',680,95);	
	//document.getElementById("ADDVIPURGENTDIV").style.display="";
}
function closeVipApp()
{
	document.getElementById("ADDVIPURGENTDIV").style.display="none";
}

function openApptPopup_old(obj)
{
	var url = "/HISRegistration/appointment/transactions/makeAppointmentTagHTMLWholeTagAppointmentTags.action";
	openURLInPopup(url,350,280);	
}

function checkTime(eve,obj)
{
	if(obj.value.size==2)
		obj.value=obj.value+":";
}
function getTimeFormat(obj)
{
	var len=obj.value.length;
	var tempstr="";
	if(len==1)
	{
		tempstr="0"+obj.value+":"+"00";
		obj.value=tempstr;
		return;
	}
	if(len==2)
	{
		tempstr=obj.value+":"+"00";
		obj.value=tempstr;
		return;
	}
	if(len==3)
	{
		var tempArr=Array();
		tempArr.length=2;
		tempArr=obj.value.split(":");
		var tempstr=tempArr[0]+":00";
		obj.value=tempstr;
		return;
	}
	if(len==4)
	{
		var tempArr=Array();
		tempArr.length=2;
		tempArr=obj.value.split(":");
		var tempstr=tempArr[0]+":0"+tempArr[1];
		obj.value=tempstr;
		return;
	}
	return;
}

function givecolon(eObj,obj)
{
    	 if(eObj.keyCode==8 || eObj.keyCode==46 )
    		 return false;	
    	 if(obj.value.length==2)
		 {
    		 obj.value += ":";
		 }			
}

function IsValidTime(timeStr) 
{

	var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s)?$/;
	var matchArray = timeStr.match(timePat);
	if (matchArray == null) 
	{
		document.getElementsByName("vipUrgentSlotTime").focus();
		return false;
	}
	hour = matchArray[1];
	minute = matchArray[2];
	second = matchArray[4];
	if (second=="") 
	{ 
		second = null;
	}
	if (hour < 0  || hour > 23) 
	{
		alert("Hour must be between 1 and 12. (or 0 and 23 for military time)");
		document.getElementsByName("vipUrgentSlotTime").focus();
		return false;
	}

}
function validateNumeric(e)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}//end of numericOnly

//To Show the Apt Free Slot on the Change of Actual Parameter,Added by Singaravelan on 27-Jan-2015
function getSlotDetails(){
	//alert("$('#appointmentTypeId').val() "+$('[name="appointmentTypeId"]').val());
//alert("slot");
	var i,paraId="",aptDate="",crNo="",aptId="";
	var tagView=1;
	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));
	aptDate=$('[name="appointmentDate"]')[0].value;	
	//alert(document.forms[0].appointmentForId);
	aptId=$('[name="appointmentForId"]')[0].value;	
	var _aptChkdate=$.datepicker.parseDate("dd-M-yy", $('[name="appointmentDate"]').val());
	//alert("-syDate-"+today+"-aptDate-"+aptDate+"-_aptChkdate-"+_aptChkdate+"-Result-"+(_aptChkdate>=today));
	
	for (i=0;i<$('select[name^="actualParameterId"]').length;i++){
		paraId+=$('[name="actualParameterId"]')[i].value+"^";
	}
	for (i=$('select[name^="actualParameterId"]').length;i<7;i++){
		if(i!=6)paraId+="0"+"^";
		else paraId+="0";
	}
	if(undefined!=$('[name="patCrNo"]')[0])
		crNo=$('[name="patCrNo"]')[0].value;

	if(undefined!=$('#aptTagRow'))
		$('#aptTagRow').hide();
	
	if(paraId!="" && (_aptChkdate>=today)){
		
		var action = "/HISRegistration/appointment/transactions/makeTimeSlotTagHTMLAppointmentTags.action";
		//var action = "/HISRegistration/appointment/transactions/makeTimeSlotTagHTML_withCappingAppointmentTags.action";

		var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView,
				appointmentDate:aptDate,
				aptId:aptId
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



function getSlotDetailsForPopup(){
	//alert("popup slot");
	var i,paraId="",aptDate="",crNo="",aptId="";
	var tagView=1;
	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));
	aptDate=$('[name="appointmentDatePopup"]')[0].value;	
	//alert(parent.document.getElementsByName("appointmentForId")[0].value);
	//aptId=$('[name="appointmentForId"]')[0].value;
	aptId=parent.document.getElementsByName("appointmentForId")[0].value
	var _aptChkdate=$.datepicker.parseDate("dd-M-yy", $('[name="appointmentDatePopup"]').val());
	//alert("-syDate-"+today+"-aptDate-"+aptDate+"-_aptChkdate-"+_aptChkdate+"-Result-"+(_aptChkdate>=today));
	
	//alert($('select[name^="actualParameterId"]',parent.document).length);
	//var paraLength= parent.document.getElementsByName("actualParameterId")[0].length;
	for (i=0;i<$('select[name^="actualParameterId"]',parent.document).length;i++){
	//for (i=0;i<paraLength;i++){
		paraId+=$('[name="actualParameterId"]',parent.document)[i].value+"^";
		//paraId+=parent.document.getElementsByName("actualParameterId")[i].value+"^";
	}
	for (i=$('select[name^="actualParameterId"]',parent.document).length;i<7;i++){
		if(i!=6)paraId+="0"+"^";
		else paraId+="0";
	}
	if(undefined!=$('[name="patCrNo"]',parent.document)[0])
		crNo=$('[name="patCrNo"]',parent.document)[0].value;
	//alert(crNo)
	if(undefined!=$('#aptTagRow'))
		$('#aptTagRow').hide();
	if(paraId!="" && (_aptChkdate>=today)){
		
		var action = "/HISRegistration/appointment/transactions/makeTimeSlotTagHTMLAppointmentTags.action";
		var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView,
				appointmentDate:aptDate,
				aptId:aptId
		  };
		
		$.ajax({
			url : action, 
			type : "POST",
			async : true,
			data : data,
			dataType : "html",
			success : function(returnHTML) {
				//alert("returnHTML: "+returnHTML)
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
//To Open the AppointmentTag View Popup for the given CRNO,and PARAID String and divId for Multiple Row Appointment Tag Integration	, Added by Singaravelan on 02-May-2015
function openDivApptPopup(obj,divId)
{
	//alert("inside");
	var crNo=$('[name="patCrNo_'+divId+'"]').val();
	var paraId=$('[name="allactualParameterId_'+divId+'"]').val();
	var aptDate=$('[name="aptForDate_'+divId+'"]').val();
	var aptId=$('[name="aptFor_'+divId+'"]').val();
	
	/*crNo=$('[name="patCrNo_aptTagRow3"]').val();
	paraId=$('[name="allactualParameterId_aptTagRow3"]').val();
	aptDate=$('[name="aptForDate_aptTagRow3"]').val();
	aptId=$('[name="aptFor_aptTagRow3"]').val();*/

	//alert($('[name="allactualParameterId_aptTagRow3"]').val())
	//alert(document.getElementsByName("showAppointmentDateInsidePopup")[0]);
	//alert("--crNo--"+crNo+"--paraId--"+paraId+"--aptDate--"+aptDate+"--aptId--"+aptId);
	//var url = "/HISRegistration/appointment/transactions/makeAppointmentTagHTMLWholeTagAppointmentTags.action?crNo="+crNo+"&paraId="+paraId+"&appointmentDate="+aptDate+"&aptId="+aptId;
	var url = "/HISRegistration/appointment/transactions/makeAppointmentTagHTMLWholeTagDivWiseAppointmentTags.action?crNo="+crNo+"&paraId="+paraId+"&appointmentDate="+aptDate+"&aptId="+aptId+"&divId="+divId+"&tagView=2";
	showJQueryDialogPopup(url,"Appointment Slots",350,280);
	
	//var someSessionVariable = '@Session["SomeSessionVariable"]';
}

//For Multiple Row Appointment Tag Integration	
function BookDivSlot(aptdate,startTime,endTime,shiftId,shiftSt,shiftEt,apptType,slotId,divId)
{	
	//alert("aptdate--"+aptdate+"startTime--"+startTime+"endTime--"+endTime+"shiftId--"+shiftId+"shiftSt--"+shiftSt+"shiftEt--"+shiftEt+"apptType--"+apptType+"slotId--"+slotId+"divId--"+divId);	
	//var confirm();
	var slotType="Normal";
	if(apptType=="5")//Overbooked
		slotType="OverBooked";
	
	var c=confirm("Appointment Date: "+aptdate+"   Appointment Time:"+startTime+"\nAppointment Type: "+slotType+"\n\n Are You Sure To Save");
	
	if(c)
	{
		var action = "/HISRegistration/appointment/transactions/checkSlotAvailibiltyAppointmentTags.action";
		var appointmentForId="";

		if (typeof $('[name="appointmentForId"]').val() === "undefined")
			appointmentForId= $('[name="aptFor"]').val();
		else
			appointmentForId=  $('[name="appointmentForId"]').val();
	
		//JSON Data
		var data = 
		{
				appointmentForId : appointmentForId,	
				actualParameterReferenceId :$('[name="actualParaRefId"]').val(),
				allActualParameterId:$("#allActualParameterId").val(),
				appointmentDate:$('[name="aptForDate"]').val(),
				//shiftST:$('[name="shiftST"]').val(),shiftET:$('[name="shiftET"]').val(),
				shiftST:shiftSt,
				shiftET:shiftEt,
				slotST:startTime,
				slotET:endTime,
				actualParameterId: $('[name="actualParameterId"]').val()	
		};
		
		$.ajax
		(
				{
					url : action, 
					type : "POST",
					async : true,
					data : data,
					dataType : "html",
					success : function(returnHTML) 
					{
						//alert("slotStatus---"+returnHTML);
						if(returnHTML=='2'||returnHTML=='4')//Slot Is Not Free (2-Already Booked,4-Elapsed)
						{
							alert("The selected Slot Is Already Booked/Hold By Another User Or Slot Elapsed");
							document.getElementById(slotId).className="Booked";
							//document.getElementById(slotId).onclick="";
							return;
						}
						else if(returnHTML=='1' || returnHTML=='5')//Slot Is Free or OverBooked(1-Free,5-OverBooked)
						{
							var all = $(".Hold").map(function() {
							    return this.id;
							}).get();
							
							var _allHold=all.join();							
							if(_allHold.indexOf(',')>0)	_allHold=_allHold.split(",")[0];
							if(_allHold!=""){
								_allHold="#"+_allHold;							
								$( _allHold ).removeClass( "Hold" ).addClass( "Free" );
							}							
							
							holdSlot(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,slotId);
							document.getElementById(slotId).className="Hold";
							//alert(parent.document.getElementById("freeSlotLabel").innerHTML);
							parent.document.getElementById("freeSlotLabel_"+divId).innerHTML='<font size="2px">'+aptdate+'&nbsp;<span id="freeSlotTime">'+startTime+'</span>&nbsp;Free&nbsp;</font>';
							parent.document.getElementsByName("slotST")[0].value=startTime;
							parent.document.getElementsByName("slotET")[0].value=endTime;
							return;
						}
						else
						{
							document.getElementById(slotId).className="Hold";
						}							
					},
					error : function(errorMsg, textstatus, errorthrown) 
					{
							alert('BookSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
					}
				}
		);		
	}
}
	

function getAptSlotDetails(crNo,paraId,appointmentDate,divRowId)
{
	var tagView=1;	
	//alert(" Inside getAptSlotDetails");
	//alert("patCRNo--"+crNo+"--paraId--"+paraId+"--appointmentDate--"+appointmentDate+"--divRowId--"+divRowId);

	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));	
	var aptDate=appointmentDate;	
	var _aptChkdate=$.datepicker.parseDate("dd-M-yy",appointmentDate);
	//alert("today--"+today+"--aptDate--"+aptDate+"--_aptChkdate--"+_aptChkdate);

	if(paraId!=-1 && (_aptChkdate>=today)){		
		
		var action = "/HISRegistration/appointment/transactions/makeAppointmentTagHTMLDivWiseAppointmentTags.action";
		//var action = "/AHIMS/appointment/transactions/makeAppointmentTagHTMLAppointmentTags.action";
		var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView,
				appointmentDate:aptDate,
				divId:divRowId
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
				//$('#aptTagRow').html(returnHTML);
				$('#'+divRowId+'').html(returnHTML);
				if(undefined!=$('#'+divRowId+'')&& returnHTML.length>0){
					$('#'+divRowId+'').show('slow');					
				}
				else
				{
					alert("No Slots Available");		
					$('#'+divRowId+'').hide();	
				}
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
	}
}

function createAppointment(divId){
	
	var flag=false;
	var appointmentForId=$('[name="aptFor_'+divId+'"]')[0].value;
	var aptDate=$('[name="aptForDate_'+divId+'"]')[0].value;
	var startTime=$('[name="slotST_'+divId+'"]')[0].value;
	var endTime=$('[name="slotET_'+divId+'"]')[0].value;
	var shiftId=$('[name="shiftId_'+divId+'"]')[0].value;
	var apptType=$('[name="aptType_'+divId+'"]')[0].value;
	
	//alert(appointmentForId +"--"+aptDate  +"--"+startTime  +"--"+endTime  +"--"+shiftId  +"--"+apptType);
	
	flag=confirmSlot(appointmentForId,aptDate,startTime,endTime,shiftId,apptType);
	//alert("Inside createApt"+flag);
	return flag;
}

//To maintain a clean calling from invetigation and other modules of Appointment Tag
//To Show the Apt Free Slot on the Change of Actual Parameter,Added by Singaravelan on 27-Jan-2015
function getSlotDetails_withCapping(){
	//alert("$('#appointmentTypeId').val() "+$('[name="appointmentTypeId"]').val());
//alert("slot");
	var i,paraId="",aptDate="",crNo="",aptId="";
	var tagView=1;
	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));
	aptDate=$('[name="appointmentDate"]')[0].value;	
	//alert(document.forms[0].appointmentForId);
	aptId=$('[name="appointmentForId"]')[0].value;	
	var _aptChkdate=$.datepicker.parseDate("dd-M-yy", $('[name="appointmentDate"]').val());
	//alert("-syDate-"+today+"-aptDate-"+aptDate+"-_aptChkdate-"+_aptChkdate+"-Result-"+(_aptChkdate>=today));
	
	for (i=0;i<$('select[name^="actualParameterId"]').length;i++){
		paraId+=$('[name="actualParameterId"]')[i].value+"^";
	}
	for (i=$('select[name^="actualParameterId"]').length;i<7;i++){
		if(i!=6)paraId+="0"+"^";
		else paraId+="0";
	}
	if(undefined!=$('[name="patCrNo"]')[0])
		crNo=$('[name="patCrNo"]')[0].value;

	if(undefined!=$('#aptTagRow'))
		$('#aptTagRow').hide();

	//alert(parent.$('[name="appointmentTypeId"]').val()+"\n"+$('[name="appointmentTypeId"]').val());
	
	var apptType ='';
	if(undefined!=$('[name="appointmentTypeId"]'))
		apptType = $('[name="appointmentTypeId"]').val();
	//alert("apptType "+apptType);
	if(apptType=='-1'){
		//alert('');
		return false;
	}
	if(paraId!="" && (_aptChkdate>=today)){
		
		//var action = "/HISRegistration/appointment/transactions/makeTimeSlotTagHTMLAppointmentTags.action";
		var action = "/HISRegistration/appointment/transactions/makeTimeSlotTagHTML_withCappingAppointmentTags.action";

		var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView,
				appointmentDate:aptDate,
				aptId:aptId,
				apptType:apptType
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