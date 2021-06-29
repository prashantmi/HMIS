/* 
	# Project Name : NIMS
	# Process Name : Appointment Desk Calendar
	# Developer    : Singaravelan 
	# Date 		   : 24-Apr-2015
 */

function getActualParaIdWiseEssensials() {
	
	var action = "/HISRegistration/appointment/transactions/getActualParaIdWiseDetailAptDskCalendar.action";
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
				setParaIdDetails();
				$('#calendar').show('puff');
				createCalendar();
			},
			error : function(errorMsg, textstatus, errorthrown) {
				alert('getActualParaIdWiseEssensials ' + errorMsg + " textstatus::"
							+ textstatus + " errorthrown::" + errorthrown);
				}
			});
}

var lastViewName ="";
var fcSources = {
		
		monthView: 
	    {
	    	events: function(start, end,callback) {		
	    		
	    		//alert("inside Month view ---Start Date"+start+"--End Date--"+end+"--parId--"+_paraId+"--aptId--"+$('[name="appointmentForId"]')[0].value);
		        $.ajax({
		            url: '/HISRegistration/appointment/transactions/getDayWiseAptCountDetailAptDskCalendar.action',		        	
		    		async : false,
		            dataType: 'json',
		            data: {
		            	paraId:$('[name="allParaId"]')[0].value,
		    			aptId:$('[name="appointmentForId"]')[0].value,
		    			startDate:start,
		    			endDate:end
		            },
		            beforeSend: function() {
						$('#modal').show();$('#fade').show();
			        }, 
			        complete: function() {
			       	 	$('#modal').hide();$('#fade').hide();  
			       	},
		            success: function(jsondata) {
		                var events =jsondata;	              
		                callback(events);
		            },
		            error : function(errorMsg, textstatus, errorthrown) {
		    			alert('getCalendarDtl ' + errorMsg + " textstatus::"
		    						+ textstatus + " errorthrown::" + errorthrown);
		    			}
		            
		        });
		    },
			cache: true,
	    },
	    dayView: 
	    {
	    	events: function(start, end,callback) {	
	    		
	    		//alert("inside Day view ---Start Date"+start+"--End Date--"+end+"--parId--"+_paraId+"--aptId--"+$('[name="appointmentForId"]')[0].value);
		        $.ajax({
		            url: '/HISRegistration/appointment/transactions/getDayWiseAptDetailAptDskCalendar.action',
		    		async : false,
		            dataType: 'json',
		            data: {
		            	paraId:$('[name="allParaId"]')[0].value,
		    			aptId:$('[name="appointmentForId"]')[0].value,
		    			startDate:start,
		    			endDate:end
		            },
		            beforeSend: function() {
						$('#modal').show();$('#fade').show();
			        }, 
			        complete: function() {
			       	 	$('#modal').hide();$('#fade').hide();  
			       	},
		            success: function(jsondata) {
		                var events =jsondata;	              
		                callback(events);
		            },
		            error : function(errorMsg, textstatus, errorthrown) {
		    			alert('getCalendarDtl ' + errorMsg + " textstatus::"
		    						+ textstatus + " errorthrown::" + errorthrown);
		    			}
		            
		        });
		    },
			cache: true,
	    },
	    weekView: 
	    {
	    	events: function(start, end,callback) {		
	    		
	    		//alert("inside Week view ---Start Date"+start+"--End Date--"+end+"--parId--"+_paraId+"--aptId--"+$('[name="appointmentForId"]')[0].value);
		        $.ajax({
		            url: '/HISRegistration/appointment/transactions/getDayWiseAptDetailAptDskCalendar.action',
		    		async : false,
		            dataType: 'json',
		            data: {
		            	paraId:$('[name="allParaId"]')[0].value,
		    			aptId:$('[name="appointmentForId"]')[0].value,
		    			startDate:start,
		    			endDate:end
		            },
		            beforeSend: function() {
						$('#modal').show();$('#fade').show();
			        }, 
			        complete: function() {
			       	 	$('#modal').hide();$('#fade').hide();  
			       	},
		            success: function(jsondata) {
		                var events =jsondata;	              
		                callback(events);
		            },
		            error : function(errorMsg, textstatus, errorthrown) {
		    			alert('getCalendarDtl ' + errorMsg + " textstatus::"
		    						+ textstatus + " errorthrown::" + errorthrown);
		    			}
		            
		        });
		    },
			cache: true,
	    }
};

function setParaIdDetails()
{
	var i,paraId="";
	for (i=0;i<$('select[name^="actualParameterId"]').length;i++){
		paraId+=$('[name="actualParameterId"]')[i].value+"^";
	}
	for (i=$('select[name^="actualParameterId"]').length;i<7;i++){
		if(i!=6)paraId+="0"+"^";
		else paraId+="0";
	}
	
	$('[name="allParaId"]')[0].value=paraId;
}


function createCalendar(){
	//alert("Inside the getSlotDuration*********createCalendar()");
	var today=new Date();
	
	$.ajax({
        url: '/HISRegistration/appointment/transactions/getSlotDurationDetailAptDskCalendar.action',
		async : true,
        dataType: 'text',
        data: {
        	paraId:$('[name="allParaId"]')[0].value,
			aptId:$('[name="appointmentForId"]')[0].value,
			startDate:today,
			endDate:today
        },
        beforeSend: function() {
			$('#modal').show();$('#fade').show();
        }, 
        complete: function() {
       	 	$('#modal').hide();$('#fade').hide();  
       	},
        success: function(jsondata) {
        	showCalendarDtl(jsondata);
        },
        error : function(errorMsg, textstatus, errorthrown) {
			alert('createCalendar ' + errorMsg + " textstatus::"
						+ textstatus + " errorthrown::" + errorthrown);
			}
        
    });
}

function showCalendarDtl(timeSnap)
{
	$('#calendar').html("");
	$('#calendar').fullCalendar({
		theme: true,
		editable: false,
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		//allDayDefault: false,
		eventSources: [		
		
		     fcSources.monthView
		],
		  axisFormat: 'H:mm',
		    timeFormat: {
		        agenda: 'H:mm'
		    },
		eventRender: function(event, element) {
	        /*element.qtip({
	            content:
	            {
	            	text: event.description,
	            },
	            style: {  	
	            	classes: 'tipCustomStyle'
	            }
	        });*/
            //$("#currenttime").html("Start: "+event.start.format("YYYY-MM-DD hh:mma")+"<br> End: "+event.end.format("YYYY-MM-DD hh:mma"));

			var dragTip="Start: "+event.start+"<br> End: "+event.end;
			/*if(event.changing!="")
			alert(event.changing);*/
	        if(event.changing){ // If this event is being changed, grab its render date
	        	element.qtip({
		            content:
		            {
		            	text: dragTip
		            },
		            style: {  	
		            	classes: 'tipCustomStyle'
		            }
		        }).show();
	        }
	        else
	        {
	        	element.qtip({
		            content:
		            {
		            	text: event.description
		            },
		            style: {  	
		            	classes: 'tipCustomStyle'
		            },
		            position: {
		                my: 'bottom center',  // Position my top left...
		                at: 'top center', // at the bottom right of...
		            }
		        });
	        }
	    },
		firstHour: 8,
	    slotMinutes:5,
	    snapMinutes:parseInt(timeSnap),
	    slotEventOverlap :false,
	    eventDurationEditable :false,
	    selectable: {
	        agenda: true
	    },
	    select : function( startDate, endDate, allDay, jsEvent, view ){
	    	
	    	var today=$.fullCalendar.formatDate(new Date(),'dd-MMM-yyyy HH:mm TT');
	    	var selDate=$.fullCalendar.formatDate(startDate,'dd-MMM-yyyy HH:mm TT');
	    	var endTime=$.fullCalendar.formatDate(endDate,'HH:mm TT');

	    	if(selDate>=today){
					if(confirm("Do you want to Book Appointment on this Slot "+selDate+"-"+endTime+" ?"))
						{
							//alert("Create Appointment");
						
							$.ajax({
							    url: '/HISRegistration/appointment/transactions/getShiftDetailAptDskCalendar.action',
							    async : false,
							    dataType: 'text',
							    data: {
							    	paraId:$('[name="allParaId"]')[0].value,
									aptId:$('[name="appointmentForId"]')[0].value,
									startDate:startDate,
									endDate:endDate
							    },
								beforeSend: function() {
									$('#modal').show();$('#fade').show();
								}, 
								complete: function() {
								 	$('#modal').hide();$('#fade').hide();  
								},
								success: function(jsondata) {
								    //alert(jsondata);	 
								    if(!jsondata.length>0)
								    	alert("Sorry!! This Slot is not Configured..Please Try Some another Slot !");
								    else
								    	openDskNewAptPopup(startDate,endDate,jsondata);
									//$('#calendar').fullCalendar( 'refetchEvents' );
								},
								error : function(errorMsg, textstatus, errorthrown) {
									alert('getShiftDetail ' + errorMsg + " textstatus::"
										+ textstatus + " errorthrown::" + errorthrown);
										revertFunc();
										}
							    
							});
							//openDskNewAptPopup(startDate,endDate);
						}
	    	}
	    	
	    },
	    viewRender	: function(view,element) {	    	
	    	
	    	if(view.name === 'month' && lastViewName != view.name) {
	    		if(lastViewName=== 'agendaWeek')
	    			$('#calendar').fullCalendar( 'removeEventSource', fcSources.weekView);
	    		if(lastViewName=== 'agendaDay')
	    			$('#calendar').fullCalendar( 'removeEventSource', fcSources.dayView);	            
	         }
	    	
	    	
	    	if(view.name === 'agendaWeek' && lastViewName != view.name) {
	    		if(lastViewName=== 'agendaDay')
	    			$('#calendar').fullCalendar( 'removeEventSource', fcSources.dayView);
	    		
	    		$('#calendar').fullCalendar( 'addEventSource', fcSources.weekView );
	         }
	    	
	    	 if(view.name === 'agendaDay' && lastViewName != view.name) {
		    	if(lastViewName=== 'agendaWeek')
	    			$('#calendar').fullCalendar( 'removeEventSource', fcSources.weekView);
	    			    		
	    		$('#calendar').fullCalendar( 'addEventSource', fcSources.dayView );

	         }	    	 

	         lastViewName = view.name;

	    	
	    },
	    eventClick: function(calEvent,jsEvent, view)
        {
	    	var selDate=$.fullCalendar.formatDate(calEvent.start,'dd-MMM-yyyy HH:mm TT');
	    	var today=$.fullCalendar.formatDate(new Date(),'dd-MMM-yyyy HH:mm TT');
	    	
	        if(view.name==='month'){
	        	$('#calendar').fullCalendar( 'changeView', 'agendaDay' );
	        	$('#calendar').fullCalendar('gotoDate', calEvent.start);
	        }	
	        //alert(calEvent.color);
	        if((view.name==='agendaWeek'||view.name==='agendaDay') && !(calEvent.allDay) && selDate>=today 
	        		//&& !calEvent.color.equals("")
	        		){
	        	
	        	var choice=confirm("Do You want to cancel the Appointment at "+selDate+" ?");
	        	if(choice)
	        		var remarks = prompt("Please Enter your Cancel Remarks");
	        	
	        	if (choice && remarks!=null) {
	    
	        		$.ajax({
					    url: '/HISRegistration/appointment/transactions/aptCancelAptDskCalendar.action',
					    async : false,
					    dataType: 'text',
					    data: {
							//appointmentNo:calEvent.title,
							appointmentNo:calEvent.id,
							paraId:$('[name="allParaId"]')[0].value,
							aptId:$('[name="appointmentForId"]')[0].value,
							sloST:calEvent.start,
							slotET:calEvent.end,
							remarks:remarks
					    },
						beforeSend: function() {
							$('#modal').show();$('#fade').show();
						}, 
						complete: function() {
						 	$('#modal').hide();$('#fade').hide();  
						},
						success: function(jsondata) {
						    //alert(jsondata);	 
							$('#calendar').fullCalendar( 'refetchEvents' );
						},
						error : function(errorMsg, textstatus, errorthrown) {
							alert('aptCancel ' + errorMsg + " textstatus::"
								+ textstatus + " errorthrown::" + errorthrown);
								revertFunc();
								}
					    
					});
                }
	        }	

        },
        eventDragStart: function(event, jsEvent, ui, view ){
            event.changing = true;
           // $('#'+event.title+'').qtip({
            	 $('#'+event.id+'').qtip({
	            content:
	            {
	            	text: "Start: "+event.start+"<br> End: "+event.end
	            },
	            style: {  	
	            	classes: 'tipCustomStyle'
	            }
	        }).show();
        },
        eventDragStop: function(event, jsEvent, ui, view ){
            event.changing = false;
        },
        eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc){
        	
        	var today = $.fullCalendar.formatDate(new Date(),'yyyy-MM-dd HH:mm');
    		var targetday = $.fullCalendar.formatDate(event.start,'yyyy-MM-dd HH:mm');
    		var startTime = $.fullCalendar.formatDate(event.start,'dd-MMM-yyyy HH:mm TT');
        	
    		if(targetday>=today)
    		{    		
	        	if(isOverlapping(event)){
	        		alert("Unable to Reschedule Since the Slot is Not Free !");
	        		revertFunc();
	        	}
	        	else
	        	{	        		
		        		if (!confirm("Do You want to Reschedule the Appointment at "+startTime+" ?")) {
		                    revertFunc();
		                }
		        		else
		        		{
		        			$.ajax({
							    url: '/HISRegistration/appointment/transactions/aptRescheduleAptDskCalendar.action',
							    async : false,
							    dataType: 'text',
							    data: {
									//appointmentNo:event.title,
									appointmentNo:event.id,
									paraId:$('[name="allParaId"]')[0].value,
									aptId:$('[name="appointmentForId"]')[0].value,
									startDate:event.start,
									endDate:event.end
							    },
								beforeSend: function() {
									$('#modal').show();$('#fade').show();
								}, 
								complete: function() {
								 	$('#modal').hide();$('#fade').hide();  
								},
								success: function(jsondata) {
								    //alert(jsondata);	 
									$('#calendar').fullCalendar( 'refetchEvents' );
								},
								error : function(errorMsg, textstatus, errorthrown) {
									alert('aptReschedule ' + errorMsg + " textstatus::"
										+ textstatus + " errorthrown::" + errorthrown);
										revertFunc();
										}
							    
							});
		        			
		        		}
	        		}       		
        		
        	}    
    		else
    		{
    			alert("Appointment Can be Rescheduled only to Present or Future Dates !");
            	revertFunc();
    		}
        }
	});
}

function isOverlapping(event){
    var array = $('#calendar').fullCalendar('clientEvents');
    for(i in array){
    	if(array[i].id != event.id && array[i].allDay==false){
            if(!((array[i].start) >= (event.end) || (array[i].end) <= (event.start))){
                return true;
            }
        }
    }
    return false;
}

function showJQueryNewAptDialogPopup(src,popUpTitle,height,width) {

	var windowWidth = $(window).width() - 100;
	var windowHeight = height;
	var isValid=""


	var $dialog = $('<div id="_dPatDtlId"></div>').html(
			'<iframe name="patFrame" style="border: 0px; " src="' + src
					+ '" width="100%" height="100%"></iframe>').dialog({
		autoOpen : false,modal : true,height : windowHeight,width : windowWidth,
		title : popUpTitle,	show: { effect: "clip"},resizable: false,
		position: ['top', 100],dialogClass: 'no-close custbtncolor',
		buttons:{
			"Create Appointment": function() {
				
				 var iframe = window.frames['patFrame'].document;
				 var value = iframe.getElementById('patFirstName').value;
								 
				 var _FirstName=iframe.getElementById('patFirstName').value;
				 var _MiddleName=iframe.getElementById('patMiddleName').value;
				 var _LastName=iframe.getElementById('patLastName').value;
				 var _Age=iframe.getElementById('patAge').value;
				 var _AgeUnit=iframe.getElementById('patAgeUnit').value;

				 var _Gender=iframe.getElementById('patGenderCode').value;
				 var _FatherName=iframe.getElementById('patGuardianName').value;
				 var _startDate=iframe.getElementsByName('startDate')[0].value;
				 var _endDate=iframe.getElementsByName('endDate')[0].value;
				 var _paraId=iframe.getElementsByName('paraId')[0].value;
				 var _aptId=iframe.getElementsByName('aptId')[0].value;
				 var _shiftId=iframe.getElementsByName('shiftId')[0].value;

				 var _mobileNo=iframe.getElementsByName('mobileNo')[0].value;
				 var _emailId=iframe.getElementsByName('emailId')[0].value;

				 
				 var isValid=true;
				 
				 //alert("_FirstName"+_FirstName+"_MiddleName"+_MiddleName+"_LastName"+_LastName+"_Age"+_Age+"_AgeUnit"+_AgeUnit+"_Gender"+_Gender+"_FatherName"+_FatherName+"_startDate"+_startDate+"_endDate"+_endDate);
				 
				 //alert(iframe.getElementsByName('patientType')[1].checked);
				 /*if(iframe.getElementsByName('patientType')[1].checked){
					 //alert("Inside the Unreg");
					 var docVars=["_FirstName","_Age","_Gender","_FatherName"];
					 var docValVars=["patFirstName","patAge","patGuardianName"];
					 for(i=0;i<docVars.length;i++)
						 {
						    alert(docVars[i] + ' value'+ docVars[i].value);
						 	if(docVars[i].value==""){
						 		alert("Please Enter "+docVars[i]);
						 		isValid=false;
						 	}
						 	if(docVars[i].value=="-1"){
						 		alert("Please Select "+docVars[i]);
						 		isValid=false;
						 	}

						 }
				 }*/

				
				  //isValid = $('#newAptCreationNewAppointmentAptDskCalendar').form('validate');
				  //alert(isValid);
				  //createDskAppointment();
				  //if(isValid)
					  //$(this).dialog("close"); 
				 
				 //alert(iframe.getElementsByName('patientType')[0].checked + "  "+ iframe.getElementsByName('patientType')[1].checked);
				 
				 if((iframe.getElementsByName('patientType')[0].checked) || (_FirstName!="" && _Age!="" && _FatherName!="" && _Gender!="-1" && _mobileNo!="")){
				 
				  $.ajax({
					    url: '/HISRegistration/appointment/transactions/createNewAppointmentAptDskCalendar.action',
					    async : false,
					    dataType: 'text',
					    data: {
							patFirstName:_FirstName,
							patMiddleName:_MiddleName,
							patLastName:_LastName,
							patAge:_Age,
							patAgeUnit:_Age+" "+_AgeUnit,
							patGenderCode:_Gender,
							patGaurdianName:_FatherName,
							mobileNo:_mobileNo,
							emailId:_emailId,
							
							paraId:_paraId,
							aptId:_aptId,
							shiftId:_shiftId,
							startDate:_startDate,
							endDate:_endDate
					    },
						beforeSend: function() {
							$('#modal').show();$('#fade').show();
						}, 
						complete: function() {
						 	$('#modal').hide();$('#fade').hide();  
						},
						success: function(jsondata) {
						    //alert(jsondata);	 
							$('#calendar').fullCalendar( 'refetchEvents' );							
						},
						error : function(errorMsg, textstatus, errorthrown) {
							alert('aptReschedule ' + errorMsg + " textstatus::"
								+ textstatus + " errorthrown::" + errorthrown);
								revertFunc();
								}
					    
					});
					$(this).dialog("close"); 

				 }
      			


			},
			"Close": function() {				
				$(this).dialog("close"); 
			}
		},
		open: function() {
			 	$('.ui-widget-overlay').addClass('custoverlay');
			 	$('.ui-dialog-buttonpane').find('button:contains("Cancel")').addClass('custbtncolor');
			 	$('.ui-dialog-buttonpane').find('button:contains("New Appointment")').addClass('custbtncolor');
		},
		close: function() {
			$(".ui-widget-overlay").removeClass('custoverlay');
	    }   
	});
	$dialog.dialog('open');
}


function openDskNewAptPopup(startDate,endDate,shiftId)
{
		
	var paraId=$('[name="allParaId"]')[0].value;
	var aptId=$('[name="appointmentForId"]')[0].value;
	startDate = $.fullCalendar.formatDate(startDate,'dd-MMM-yyyy HH:mm');
	endDate = $.fullCalendar.formatDate(endDate,'dd-MMM-yyyy HH:mm');

	//alert("paraId--"+paraId+"--aptId--"+aptId);
	
	var url = "/HISRegistration/appointment/transactions/newAptCreationAptDskCalendar.action?paraId="+paraId+"&aptId="+aptId+"&startDate="+startDate+"&endDate="+endDate+"&shiftId="+shiftId;
	showJQueryNewAptDialogPopup(url,"Appointment Creation",350,280);
}

function changePatientType(){
	//alert('m here'+document.getElementsByName("afterGo")[0].value);
	
	
	var val=this.value;
	if(!val){
		if(document.getElementsByName("patientType")[0].checked)
			val=document.getElementsByName("patientType")[0].value;
		else
			val=document.getElementsByName("patientType")[1].value;
	}
	//alert(val);
	if(val==1){
		$('#divpatient_2').hide();
		$('#div_appointment').hide();
		$('#divpatient_1').show('slow');
		$('#div_appointment').hide('slow');
		
		
		$('[name="patFirstName"]').validatebox({
			required:false				
		});
		
		$('[name="patAge"]').validatebox({
			required:false				
		});
		
		$('[name="patAgeUnit"]').validatebox({
			required:false,
			validType: 'selectCombo[-1]'		
		});
		
		$('[name="patGenderCode"]').validatebox({
			required:false				
		});
		$('[name="patGuardianName"]').validatebox({
			required:false				
		});
		$('[name="patGenderCode"]').validatebox({
			required:false				
		});
		$('[name="mobileNo"]').validatebox({
			required:false				
		});	
	}	
	else{
		
		$('#divpatient_1').hide();
		$('#divpatient_2').show('slow');
		$('#div_appointment').show('slow');
				
		$('[name="patFirstName"]').validatebox({
			required:true				
		});
		
		$('[name="patAge"]').validatebox({
			required:true				
		});
		
		$('[name="patAgeUnit"]').validatebox({
			required:true,
			validType: 'selectCombo[-1]'		
		});
		
		$('[name="patGenderCode"]').validatebox({
			required:true,
			validType: 'selectCombo[-1]'		
		});
		$('[name="patGuardianName"]').validatebox({
			required:true				
		});
		
		$('[name="mobileNo"]').validatebox({
			required:true				
		});	
				
	}
	if(document.getElementsByName("afterGo")[0].value==1){
		$('#div_appointment').show('slow');
		$('#div_patientType').show();
		$('#div_patientType').hide();		
	}
	
}

function submitForm(mode) {
	document.forms[0].action = mode + "AptDskCalendar.action";
	document.getElementsByName("afterGo")[0].value="1";
	document.forms[0].submit();
	

}
