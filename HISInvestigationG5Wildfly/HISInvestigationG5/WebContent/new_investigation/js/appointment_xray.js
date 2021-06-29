function BookSlot(aptdate,startTime,endTime,shiftId,shiftSt,shiftEt,apptType,slotId)
{	
	var slotType="Normal";
	if(apptType=="5")//Overbooked
		slotType="OverBooked";
	
 
	var c=confirm("Appointment Date: "+aptdate+"   Appointment Time:"+startTime+"\nAppointment Type: "+slotType+"\n\n Are You Sure To Save");
	
	if(c)
	{
		var action = "/HISInvestigationG5/appointment/transactions/checkSlotAvailibiltyAppointmentTags.action";
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
						if(returnHTML=='2'||returnHTML=='4')//Slot Is Not Free (2-Already Booked,4-Elapsed)
						{
							alert("The selected Slot Is Already Booked/Hold By Another User Or Slot Elapsed");
							document.getElementById(slotId).className="Booked";
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
	
	
}

//To Hold the Selected Slot, Added by Singaravelan on 27-Jan-2015
function holdSlot(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,slotId)
{
	var action = "/HISInvestigationG5/appointment/transactions/holdSlotAppointmentTags.action";	
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
	var action= "/HISInvestigationG5/appointment/transactions/checkSlotAvailibiltyOnSaveTimeAppointmentTags.action";
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
						alert("The Slot has been Already Booked or Elapsed! \n Please Select Another Slot");
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
	var action= "/HISInvestigationG5/appointment/transactions/saveAppointmentTags.action";
	//alert( $('[name="patCrNo"]').val() );
	//alert($("#allactualParameterId").val()+" "+$('[name="allactualParameterId"]').val()+" "+$('[name="shiftST"]').val()+" "+$('[name="shiftET"]').val()+" "+$('[name="actualparameterId"]').val());
	//JSON Data
	var data = 
	{
			appointmentForId : appointmentForId,	
			actualParameterReferenceId :$('[name="actualParaRefId"]').val(),
			allActualParameterId:$('[name="allactualParameterId"]').val(),
			appointmentDate:$('[name="aptForDate"]').val(),
			shiftST:$('[name="shiftST"]').val(),
			shiftET:$('[name="shiftET"]').val(),
			slotST:startTime,
			slotET:endTime,
			actualParameterId: $('[name="actualParameterId"]').val(),
			shiftId:shiftId,
			slotType:apptType,
			patCrNo:$('[name="patCrNo"]').val()
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
					//alert("Appointment Created Successfully");
					return;											
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{
						alert('ConfirmSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
						return false;
				}
			}
	);	
}



function setpatvisitypeforappt()
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	var remarks = "2"; // for ipd
	if(document.getElementsByName('selectedEpisode')[0]!=undefined)
	  {
		var selectedEpisode=document.getElementsByName('selectedEpisode')[0].value;
	  // alert("selectedEpisode"+selectedEpisode);
	var flg = "1";
	
	var _mode = "AJX_SETREQTYPE_FOR_APPOINTMENT";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&selectedEpisode="+selectedEpisode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("data"+data);
			remarks = data;
			remarks="1"; //for all opd
			
			flg = true;
		},
        error: function(error)
        {
            //remarks = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	  }
	return remarks;
}
//To Open the AppointmentTag View Popup for the given CRNO,and PARAID String, Added by Singaravelan on 28-Jan-2015
function openApptPopup(obj)
{
	var crNo=$('[name="patCrNo"]').val();
	var paraId=$('[name="allactualParameterId"]').val();
	var aptDate=$('[name="aptForDate"]').val();
	var aptId=$('[name="aptFor"]').val();
	
	//alert("crNo  "+crNo+"paraId "+paraId+"aptDate" +aptDate+"aptId " +aptId);
	
	var url = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLWholeTagAppointmentTags.action?crNo="+crNo+"&paraId="+paraId+"&appointmentDate="+aptDate+"&aptId="+aptId;
	showJQueryDialogPopup(url,"Appointment Slots",350,280);
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

	var windowWidth = $(window).width() - 100;
	var windowHeight = height;


	var $dialog = $('<div id="_dSlotCtId"></div>').html(
			'<iframe style="border: 0px; " src="' + src
					+ '" width="100%" height="100%"></iframe>').dialog({
		autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
		title : popUpTitle,	show: { effect: "clip"},resizable: false,
		position: ['top', 100],dialogClass: 'no-close custbtncolor',
		buttons:{
			"Close": function() {				
				$(this).dialog("close"); 
			}
		},
		open: function() {
			 	$('.ui-widget-overlay').addClass('custoverlay');
			 	$('.ui-dialog-buttonpane').find('button:contains("Cancel")').addClass('custbtncolor');
			 	$('.ui-dialog-buttonpane').find('button:contains("Save As New Patient")').addClass('custbtncolor');
		},
		close: function() {
			$(".ui-widget-overlay").removeClass('custoverlay');
	    }   
	});
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
    
	var action = "/HISInvestigationG5/appointment/transactions/getAppointmentListHTMLAppointmentTags.action";
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
	var url = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLWholeTagAppointmentTags.action";
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
		return false;
		document.getElementsByName("vipUrgentSlotTime").focus();
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
		return false;
		document.getElementsByName("vipUrgentSlotTime").focus();
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
		
	var i,paraId="",aptDate="",crNo="";
	var tagView=1;
	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));
	aptDate=$('[name="appointmentDate"]')[0].value;	
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
	//var apptType = "1";//Hardcoded for OPD = 1
	
	 var apptType =  "";
	    if(document.getElementsByName('selectedEpisode')[0]!=undefined)  // in case of opd 
		  {
	    	  for(var k=0;k<document.getElementsByName('radioEpisode').length;k++)
		    	{
		    	
		    	             if(document.getElementsByName('radioEpisode')[k].checked==true)
		    	            	 {
		    	          var episode=document.getElementsByName('radioEpisode')[k].value;
		    	          var episode1=document.getElementsByName('selectedEpisodedetails')[k].value;

		    	         // alert("episode3"+episode1.split("#")[1]);
		    	          apptType=episode1.split("#")[1];
		    	            	 }
		    	             
		    	}
		  }
	    else
	    	{
	    	apptType="2";   // in case of ipd

	    	}
	   // alert("apptTypeapptType"+apptType);
	if(paraId!="" && (_aptChkdate>=today)){
		
		//var action = "/HISInvestigationG5/appointment/transactions/makeTimeSlotTagHTMLAppointmentTags.action";
		var action = "/HISInvestigationG5/appointment/transactions/makeTimeSlotTagHTML_withCappingAppointmentTags.action";

		var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView,
				appointmentDate:aptDate,
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

//To Show the Appointment Free Slots in the OPD View Summary, Added by Singaravelan on 19-Mar-2015
function getViewSummaryAptSlotDetails()
{
	//alert("I Came Inside");
	var crNo=$('[name="patCrNo"]')[0].value;
	var paraId=$('[name="departmentUnitCode"]')[0].value+"^0^0^0^0^0^0";
	var tagView=1;	
	//alert("patCRNo--"+crNo+"--paraId--"+paraId);

	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));	
	var aptDate=$('[name="appointmentDate"]')[0].value;	
	var _aptChkdate=$.datepicker.parseDate("dd-M-yy", $('[name="appointmentDate"]').val());
	//alert("today--"+today+"--aptDate--"+aptDate+"--_aptChkdate--"+_aptChkdate);

	if($('[name="departmentUnitCode"]')[0].value!=-1 && (_aptChkdate>=today)){
		
		//alert("Inside the If Loop");
		
		var action = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLAppointmentTags.action";
		//var action = "/HISInvestigationG5/appointment/transactions/makeTimeSlotTagHTMLAppointmentTags.action";
		var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView,
				appointmentDate:aptDate				
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
				$('#aptTagRow').html(returnHTML);
				if(undefined!=$('#aptTagRow')&& returnHTML.length>0){
					$('#aptTagRow').show('slow');					
				}
				else
				{
					alert("No Slots Available");		
					$('#aptTagRow').hide();	
				}
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
	}
}

//To Create the Appointment while Save the OPD View Summary, Added by Singaravelan on 19-Mar-2015
function createAppointment(){
	
	var flag=false;
	var appointmentForId=$('[name="aptFor"]')[0].value;
	var aptDate=$('[name="aptForDate"]')[0].value;
	var startTime=$('[name="slotST"]')[0].value;
	var endTime=$('[name="slotET"]')[0].value;
	var shiftId=$('[name="shiftId"]')[0].value;
	//var apptType="4";
	var apptType =  "";
    if(document.getElementsByName('selectedEpisode')[0]!=undefined)  // in case of opd 
	  {
    	  for(var k=0;k<document.getElementsByName('radioEpisode').length;k++)
	    	{
	    	
	    	             if(document.getElementsByName('radioEpisode')[k].checked==true)
	    	            	 {
	    	          var episode=document.getElementsByName('radioEpisode')[k].value;
	    	          var episode1=document.getElementsByName('selectedEpisodedetails')[k].value;

	    	        //  alert("episode1"+episode1.split("#")[1]);
	    	          apptType=episode1.split("#")[1];
	    	            	 }
	    	             
	    	}
	  }
    else
    	{
    	apptType="2";   // in case of ipd

    	}
    
	//alert("create app"+apptType);
	//alert(appointmentForId +"--"+aptDate  +"--"+startTime  +"--"+endTime  +"--"+shiftId  +"--"+apptType);
	
	flag=confirmSlot(appointmentForId,aptDate,startTime,endTime,shiftId,apptType);
	//alert("Inside createApt"+flag);
	return flag;
}

//To Show the Appointment Free Slots , Added by Singaravelan on 19-Mar-2015
function getAptSlotDetails(crNo,paraId,appointmentDate)
{
	var tagView=1;	
	//alert("patCRNo--"+crNo+"--paraId--"+paraId);

	var today = $.datepicker.parseDate("dd-M-yy",$.datepicker.formatDate('dd-M-yy', new Date()));	
	var aptDate=appointmentDate;	
	var _aptChkdate=$.datepicker.parseDate("dd-M-yy",appointmentDate);
	//alert("today--"+today+"--aptDate--"+aptDate+"--_aptChkdate--"+_aptChkdate);

	if(paraId!=-1 && (_aptChkdate>=today)){		
		
		var action = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLAppointmentTags.action";
			var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView,
				appointmentDate:aptDate				
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
				$('#aptTagRow').html(returnHTML);
				if(undefined!=$('#aptTagRow')&& returnHTML.length>0){
					$('#aptTagRow').show('slow');					
				}
				else
				{
					alert("No Slots Available");		
					$('#aptTagRow').hide();	
				}
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('makeParatmeterHTML ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
	}
}


//To Open the AppointmentTag View Popup for the given CRNO,and PARAID String and divId for Multiple Row Appointment Tag Integration	, Added by Singaravelan on 02-May-2015
function openDivApptPopup(obj,divId)
{
	var crNo=$('[name="patCrNo_'+divId+'"]').val();
	var paraId=$('[name="allactualParameterId_'+divId+'"]').val();
	var aptDate=$('[name="aptForDate_'+divId+'"]').val();
	var aptId=$('[name="aptFor_'+divId+'"]').val();
	//alert("aptIdaptIdaptIdaptIdaptId"+aptId);
	/*crNo=$('[name="patCrNo_aptTagRow3"]').val();
	paraId=$('[name="allactualParameterId_aptTagRow3"]').val();
	aptDate=$('[name="aptForDate_aptTagRow3"]').val();
	aptId=$('[name="aptFor_aptTagRow3"]').val();*/

	//alert($('[name="allactualParameterId_aptTagRow3"]').val())
	//alert("--crNo--"+crNo+"--paraId--"+paraId+"--aptDate--"+aptDate+"--aptId--"+aptId);
	//var url = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLWholeTagAppointmentTags.action?crNo="+crNo+"&paraId="+paraId+"&appointmentDate="+aptDate+"&aptId="+aptId;
	//original commented 
	//var url = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLWholeTagDivWiseAppointmentTags.action?crNo="+crNo+"&paraId="+paraId+"&appointmentDate="+aptDate+"&aptId="+aptId+"&divId="+divId+"&tagView=2";
	//call for ipd opd capping feature
	
	 var apptType =  "";
	    if(document.getElementsByName('selectedEpisode')[0]!=undefined)  // in case of opd 
		  {
	    	  for(var k=0;k<document.getElementsByName('radioEpisode').length;k++)
		    	{
		    	
		    	             if(document.getElementsByName('radioEpisode')[k].checked==true)
		    	            	 {
		    	          var episode=document.getElementsByName('radioEpisode')[k].value;
		    	          var episode1=document.getElementsByName('selectedEpisodedetails')[k].value;

		    	     //     alert("episode4"+episode1.split("#")[1]);
		    	          apptType=episode1.split("#")[1];
		    	            	 }
		    	             
		    	}
		  }
	    else
	    	{
	    	apptType="2";   // in case of ipd

	    	}
	    
	var url = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLWholeTagDivWise_withCappingAppointmentTags.action?crNo="+crNo+"&paraId="+paraId+"&appointmentDate="+aptDate+"&aptId="+aptId+"&divId="+divId+"&tagView=2&apptType="+apptType+"";//apptType Hardcoded 1 fro OPD

	//
	showJQueryDialogPopup(url,"Appointment Slots",350,280);
	//var someSessionVariable = '@Session["SomeSessionVariable"]';
}

//For Multiple Row Appointment Tag Integration	
function BookDivSlot(aptdate,startTime,endTime,shiftId,shiftSt,shiftEt,apptType,slotId,divId)
{	
	
	var flagPass = true;
	//apptType = "1";//parent.$('[name="appointmentTypeId"]').val();//apptType -- 1 = OPD, 2 = IPD, 3 = EMG
	
	   apptType =  "";
	    if(document.getElementsByName('selectedepisodeee')[0]!=undefined)  // in case of opd 
		  {
	    	  for(var k=0;k<document.getElementsByName('selectedepisodee').length;k++)
		    	{
		    	
		    	             if(document.getElementsByName('selectedepisodee')[k].checked==true)
		    	            	 {
		    	          //var episode=document.getElementsByName('selectedepisodee')[k].value;
		    	          var episode1=document.getElementsByName('selectedepisodee')[k].value;

		    	        //  alert("episode5"+episode1.split("#")[1]);
		    	          apptType=episode1.split("#")[1];
		    	            	 }
		    	             
		    	}
		  }
	    else
	    	{
	    	apptType="2";   // in case of ipd

	    	}
	    
	   // alert("apptType"+apptType);
	    
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
	
	
	//var confirm();
	//alert("BookDivSlot");
	var slotType="Normal";
	if(apptType=="5")//Overbooked
		slotType="OverBooked";
	
	var c=confirm("Appointment Date: "+aptdate+"   Appointment Time:"+startTime+"\nAppointment Type: "+slotType+"\n\n Are You Sure To Save");
	if(c)
	{
		var action = "/HISInvestigationG5/appointment/transactions/checkSlotAvailibiltyAppointmentTags.action";
		var appointmentForId="";
		 

		if (typeof $('[name="appointmentForId"]').val() === "undefined")
			appointmentForId= $('[name="aptFor_'+divId+'"]').val();
		else
			appointmentForId=  $('[name="appointmentForId"]').val();
	
		//alert("aptdate--"+aptdate+"startTime--"+startTime+"endTime--"+endTime+"shiftId--"+shiftId+"shiftSt--"+shiftSt+"shiftEt--"+shiftEt+"appointmentForId--"+appointmentForId+"slotId--"+slotId+"divId--"+divId+"actualParaRefId--"+$('[name="actualParaRefId"]').val()+" allActualParameterId--"+$("#allActualParameterId").val());	

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
							
							holdSlotOnDivId(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,slotId,divId);
							document.getElementById(slotId).className="Hold";
							//alert(parent.document.getElementById("freeSlotLabel_"+divId).innerHTML);
							
							
							
							
							parent.document.getElementById("freeSlotLabel_"+divId).innerHTML='<font size="2px">'+aptdate+'&nbsp;<span id="freeSlotTime">'+startTime+'</span>&nbsp;Free&nbsp;</font>';
							//alert(parent.document.getElementById("freeSlotLabel_"+divId).innerHTML);
							parent.document.getElementsByName("slotST_"+divId)[0].value=startTime;
							parent.document.getElementsByName("slotET_"+divId)[0].value=endTime;
							
							for(var ss=0;ss<parent.document.getElementsByName(divId).length;ss++)
								{
								
								parent.document.getElementsByName(divId)[ss].innerHTML=' '+aptdate+ ' '+startTime+' ';
								}
							
							//alert(parent.document.getElementsByName(divId).length);
							
							return;
						}
						else
						{
							document.getElementById(slotId).className="Hold";
						}							
					},
					error : function(errorMsg, textstatus, errorthrown) 
					{
							alert('BookDivSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
					}
				}
		);		
	}
}
	

function getAptSlotDetails(crNo,paraId,appointmentDate,divRowId,aptId)
{
	//alert("getAptSlotDetails========================="+aptId+"vvvvvvvv"+divRowId);
	var monthNames = [ "Jan", "Feb", "Mar", "Apr", "May", "Jun",
	                   "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" ];
	
	//alert(crNo+","+paraId+","+appointmentDate+","+divRowId+","+aptId);
	var tagView=1;	
 
	var today = $.datepicker.formatDate('dd-M-yy', new Date());
	var aptDate=appointmentDate;
 
	if(appointmentDate=='')
		aptDate=today;
	else
		aptDate=appointmentDate;
 
	
	var aptdatee=aptDate.split("-");
	
	    var date1=aptdatee[0];
		var month1=aptdatee[1];
	    var year1=aptdatee[2];
	    var newDate = new Date(aptDate);
	    month1=(newDate.getMonth())+1;
	    
	    aptdatee=year1+"-"+month1+"-"+date1;
	    
	    
		var aptdatee1=today.split("-");
		
	    var date11=aptdatee1[0];
		var month11=aptdatee1[1];
	    var year11=aptdatee1[2];
	    var newDate1 = new Date(today);
	    month11=(newDate1.getMonth())+1;
	    
	    aptdatee1=year11+"-"+month11+"-"+date11;
	    
	  //  alert("data....."+document.getElementsByName('radioEpisode').length);
	    

	    
	    
	    //var apptType = "1";//hardcoded for OPD
	    var apptType =  "";
	    if(document.getElementsByName('selectedEpisode')[0]!=undefined)  // in case of opd 
		  {
	    	
		    for(var k=0;k<document.getElementsByName('radioEpisode').length;k++)
	    	{
	    	
	    	             if(document.getElementsByName('radioEpisode')[k].checked==true)
	    	            	 {
	    	          var episode=document.getElementsByName('radioEpisode')[k].value;
	    	          var episode1=document.getElementsByName('selectedEpisodedetails')[k].value;

	    	          //alert("episode2"+episode1.split("#")[1]);
	    	          apptType=episode1.split("#")[1];
	    	            	 }
	    	             
	    	}
		    
	    //	apptType="1";
		  }
	    else
	    	{
	    	apptType="2";   // in case of ipd
 
	    	}
	    
	//    alert("apptType"+apptType);
	//    alert("apptType"+paraId);
	/*alert(aptdatee);
	alert(aptdatee1);
	
	if () {
	    alert("vvv");
	}
	else
		{
		alert("false");
		}*/
	
	if(paraId!=-1 && (new Date(aptdatee) >= new Date(aptdatee1) )){			
 
		var action = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLDivWiseAppointmentTags.action";//
		var action = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLDivWise_withCappingAppointmentTags.action";
		//var action = "/HISInvestigationG5/appointment/transactions/makeAppointmentTagHTMLAppointmentTags.action";
		var data = {
				paraId : paraId,	
				crNo :crNo,
				tagView :tagView,
				appointmentDate:aptDate,
				divId:divRowId,
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
				if(returnHTML.indexOf('^')>0)
					returnHTML=returnHTML.split('@')[1];
				else
					returnHTML="";
				//$('#aptTagRow').html(returnHTML);
				//alert(returnHTML);
				$('#'+divRowId+'').html(returnHTML);
				
				if(undefined!=$('#'+divRowId+'')&& returnHTML.length>0){
					$('#'+divRowId+'').show('slow');	
			//		alert("divRowIddivRowId"+divRowId);
				var val=document.getElementById("freeSlotLabel_"+divRowId).innerHTML;
			//	alert(val);
				
				for(var ss=0;ss<document.getElementsByName(divRowId).length;ss++)
				{
				
				document.getElementsByName(divRowId)[ss].innerHTML=' '+val+' ';
				}
				
				
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
	
  // alert("Create Appoitment divId is"+divId);
	
	var flag=false;
	var appointmentForId=$('[name="aptFor_'+divId+'"]')[0].value;
	var aptDate=$('[name="aptForDate_'+divId+'"]')[0].value;
	var startTime=$('[name="slotST_'+divId+'"]')[0].value;
	var endTime=$('[name="slotET_'+divId+'"]')[0].value;
	var shiftId=$('[name="shiftId_'+divId+'"]')[0].value;
	//var apptType="4";
	var apptType =  "";
    if(document.getElementsByName('selectedEpisode')[0]!=undefined)  // in case of opd 
	  {
    	  for(var k=0;k<document.getElementsByName('radioEpisode').length;k++)
	    	{
	    	
	    	             if(document.getElementsByName('radioEpisode')[k].checked==true)
	    	            	 {
	    	          var episode=document.getElementsByName('radioEpisode')[k].value;
	    	          var episode1=document.getElementsByName('selectedEpisodedetails')[k].value;

	    	       //   alert("episode4"+episode1.split("#")[1]);
	    	          apptType=episode1.split("#")[1];
	    	            	 }
	    	             
	    	}
	  }
    else
    	{
    	apptType="2";   // in case of ipd

    	}
    
     //alert("create app"+apptType);
	//alert(appointmentForId +"--"+aptDate  +"--"+startTime  +"--"+endTime  +"--"+shiftId  +"--"+apptType);
	
	flag=confirmSlotOnDivId(appointmentForId,aptDate,startTime,endTime,shiftId,apptType,divId);
	//alert("Inside createApt"+flag);
	return flag;
}


//To Confirm/Save the Selected Slot Added by Singaravelan on 27-Jan-2015
function confirmSlotOnDivId(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,divId)
{
	
	//alert("confirmSlotOnDivId apptType"+apptType); 
	var action= "/HISInvestigationG5/appointment/transactions/checkSlotAvailibiltyOnSaveTimeAppointmentTags.action";
	var isConfirm=false;
	//JSON Data
	//alert("appointmentForId--"+appointmentForId+"actualParameterReferenceId--"+$('[name="actualParaRefId_'+divId+'"]').val()+"allActualParameterId--"+$("#allActualParameterId").val()+"appointmentDate--"+$('[name="aptForDate_'+divId+'"]').val()+"shiftST--"+$('[name="shiftST_'+divId+'"]').val()+"shiftEt--"+$('[name="shiftET_'+divId+'"]').val()+"startTime--"+startTime+"endTime--"+endTime+"actualParameterId--"+$('[name="actualParameterId_'+divId+'"]').val()+"actualParaRefId--"+$('[name="actualParaRefId"]').val()+" allActualParameterId--"+$("#allActualParameterId").val()+"shiftId--"+shiftId+"slotType--"+apptType);	

	var data = 
	{
			appointmentForId : appointmentForId,	
			actualParameterReferenceId :$('[name="actualParaRefId_'+divId+'"]').val(),
			allActualParameterId:$('[name="actualParameterId_'+divId+'"]').val(),//$("#allActualParameterId").val(),
			appointmentDate:$('[name="aptForDate_'+divId+'"]').val(),
			shiftST:$('[name="shiftST_'+divId+'"]').val(),
			shiftET:$('[name="shiftET_'+divId+'"]').val(),
			slotST:startTime,
			slotET:endTime,
			actualParameterId: $('[name="actualParameterId_'+divId+'"]').val(),
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
					//alert("In Side confirmSlotOnDivId Slot "+returnHTML);
					if(returnHTML=='1' || returnHTML=='5')
					{
						 
						_saveSlotdivId(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,divId);
						isConfirm=true;
						//alert(isConfirm);
						
					}
					else
					{
						alert("The Slot has been Already Booked or Elapsed! \n Please Select Another Slot");
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

//To Hold the Selected Slot, Added by Singaravelan on 27-Jan-2015
function holdSlotOnDivId(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,slotId,divId)
{
	//alert("Inside hold Div Slot");
	var action = "/HISInvestigationG5/appointment/transactions/holdSlotAppointmentTags.action";	
	//alert("appointmentForId--"+appointmentForId+"actualParameterReferenceId--"+$('[name="actualParaRefId_'+divId+'"]').val()+"allActualParameterId--"+$('[name="actualParameterId_'+divId+'"]').val()+"appointmentDate--"+$('[name="aptForDate_'+divId+'"]').val()+"shiftST--"+$('[name="shiftST_'+divId+'"]').val()+"shiftEt--"+$('[name="shiftET_'+divId+'"]').val()+"startTime--"+startTime+"endTime--"+endTime+"actualParameterId--"+$('[name="actualParameterId_'+divId+'"]').val()+"actualParaRefId--"+$('[name="actualParaRefId"]').val()+" allActualParameterId--"+$("#allActualParameterId").val()+"shiftId--"+shiftId+"slotType--"+apptType);
	var data = 
	{
			appointmentForId : appointmentForId,	
			actualParameterReferenceId :$('[name="actualParaRefId"]').val(),
			allActualParameterId:$('[name="actualParameterId"]').val(),
			appointmentDate:$('[name="aptForDate_'+divId+'"]').val(),
			shiftST:$('[name="shiftST_'+divId+'"]').val(),
			shiftET:$('[name="shiftET_'+divId+'"]').val(),
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
					//alert("Inside hold Div Slot"+returnHTML)
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{
						alert('ConfirmSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
				}
			}
	);	
}





function _saveSlotdivId(appointmentForId,aptdate,startTime,endTime,shiftId,apptType,divId)
{
	
	//alert("_saveSlotdivId");
	var action= "/HISInvestigationG5/appointment/transactions/saveAppointmentTags.action";
	//alert( $('[name="patCrNo"]').val() );
	//alert($("#allactualParameterId").val()+" "+$('[name="allactualParameterId"]').val()+" "+$('[name="shiftST"]').val()+" "+$('[name="shiftET"]').val()+" "+$('[name="actualparameterId"]').val());
	//JSON Data
	
	    if(document.getElementsByName('selectedEpisode')[0]!=undefined)  // in case of opd 
		  {
	    	  for(var k=0;k<document.getElementsByName('radioEpisode').length;k++)
		    	{
		    	
		    	             if(document.getElementsByName('radioEpisode')[k].checked==true)
		    	            	 {
		    	          var episode=document.getElementsByName('radioEpisode')[k].value;
		    	          var episode1=document.getElementsByName('selectedEpisodedetails')[k].value;

		    	        //  alert("episode1q"+episode1.split("#")[1]);
		    	          apptType=episode1.split("#")[1];
		    	            	 }
		    	             
		    	}
		  }
	    else
	    	{
	    	apptType="2";   // in case of ipd

	    	}
	   // alert(apptType);
	//alert("appointmentForId--"+appointmentForId+"actualParameterReferenceId--"+$('[name="actualParaRefId_'+divId+'"]').val()+"actualParameterId_--"+$('[name="actualParameterId_'+divId+'"]').val()+"appointmentDate--"+$('[name="aptForDate_'+divId+'"]').val()+"shiftST--"+$('[name="shiftST_'+divId+'"]').val()+"shiftEt--"+$('[name="shiftET_'+divId+'"]').val()+"startTime--"+startTime+"endTime--"+endTime+"actualParameterId--"+$('[name="actualParameterId_'+divId+'"]').val()+"actualParaRefId--"+$('[name="actualParaRefId"]').val()+" allActualParameterId--"+$("#allActualParameterId").val()+"shiftId--"+shiftId+"slotType--"+apptType);	

	var data = 
	{
			appointmentForId : appointmentForId,	
			actualParameterReferenceId :$('[name="actualParaRefId_'+divId+'"]').val(),
			allActualParameterId:$('[name="actualParameterId_'+divId+'"]').val(),
			appointmentDate:$('[name="aptForDate_'+divId+'"]').val(),
			shiftST:$('[name="shiftST_'+divId+'"]').val(),
			shiftET:$('[name="shiftET_'+divId+'"]').val(),
			slotST:startTime,
			slotET:endTime,
			actualParameterId: $('[name="actualParameterId_'+divId+'"]').val(),
			shiftId:shiftId,
			slotType:apptType,
			patCrNo:$('[name="patCrNo"]').val()
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
					//alert("Appointment Created Successfully");
					//alert("Appoint Saved"+returnHTML);
					document.getElementsByName('appointmentRefNo')[0].value=returnHTML;
					return;											
				},
				error : function(errorMsg, textstatus, errorthrown) 
				{
						alert('ConfirmSlot ' + errorMsg + " textstatus::"+ textstatus + " errorthrown::" + errorthrown);
						return false;
				}
			}
	);	
}



function setpatvisitypeforappt()
{
	
	//alert("setAptNoUsingAjax"+appoitmentNo+"labcode"+tmpLabCode+"testCode"+tmpTestCode);
	var remarks = "2"; // for ipd
	if(document.getElementsByName('selectedEpisode')[0]!=undefined)
	  {
		var selectedEpisode=document.getElementsByName('selectedEpisode')[0].value;
	  // alert("selectedEpisode"+selectedEpisode);
	var flg = "1";
	
	var _mode = "AJX_SETREQTYPE_FOR_APPOINTMENT";
	var objXHR = {url: "/HISInvestigationG5/new_investigation/investigationReqRaising.cnt?hmode="+_mode+"&selectedEpisode="+selectedEpisode, sync:true, postData: "", handleAs: "text",
		load: function(data) 
		{
			//alert("data"+data);
			remarks = data;
			remarks="1"; //for all opd
			
			flg = true;
		},
        error: function(error)
        {
            //remarks = tmpLabTestCodeArray;
            flg = false;
        }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	  }
	return remarks;
}