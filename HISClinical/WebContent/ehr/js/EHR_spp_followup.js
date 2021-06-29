
function openVisitNotesMacros(processId, _unitCode, event)
{
	var tarFun = "setMacroToVisitNotes(";
	//var path="/HISClinical/opd/genericMacroPopup.cnt?hmode=ADDUNITMACRO&macroProcessId="+processId+"&unitCode="+_unitCode+"&macroTargetFunction="+tarFun;
	var path="/HISClinical/opd/genericMacroPopup.cnt?hmode=ADDMACRO&macroProcessId="+processId+"&unitCode="+_unitCode+"&macroTargetFunction="+tarFun;
	openPopup(createFHashAjaxQuery(path),event,300,600);
}

function openUnitKeywords(event)
{
	var tarFun = "addEpisodeKeywords(";
	var path="/HISClinical/opd/opdNextVisitDetail.cnt?hmode=ADDKEYWORDS&targetFunction="+tarFun;
	openPopup(path,event,300,600);
}

function showProgressNotesInPopup(event,index)
{	
	var notes = document.getElementsByName("prevProgressNotesForPopup")[index].value;
	var path='/HISClinical/opd/opdNextVisitDetail.cnt?hmode=POPUPNOTES&popupNotes='+notes;
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');
	child.moveTo(250,250);
	child.focus();
	if(!child.opener)	child.opener = self;
	return child;
}

function openDynamicVisitSummary(event,param)
{
	var path="/HISClinical/opd/opdNextVisitDetail.cnt?hmode=POPUPSUMMARY&"+param;
	openPopup(path,event,700,800);
}

function setMacroToVisitNotes(val)
{
	var tarVal = val;
	if(val.length>500)	tarVal=val.substring(0,500);	
	//document.getElementsByName("visitNotes")[0].value = tarVal;
	document.getElementsByName("search_FU2")[0].value = tarVal;
}

function addEpisodeKeywords(val)
{
	var tarVal = val;
	if(document.getElementsByName("episodeKeywords")[0].value!="")
		tarVal = document.getElementsByName("episodeKeywords")[0].value+", "+tarVal;
	if(tarVal.length>500)	tarVal=tarVal.substring(0,100);

	document.getElementsByName("episodeKeywords")[0].value = tarVal;
}

/**
 * name
 * @param {type} param 
 */
 function chiefComplaintsLen() {
 	var keyWordVal=document.getElementsByName("keywordVal")[0].value;
	 var len =keyWordVal.length;
 	if(len>99)
	{
		alert("Can't add more chief Complaints");
		return false;
	}
	return true;
 }

function validateSave()
{
	// alert(document.getElementsByName("keywordVal")[0].value);	
	// var keyWordVal=document.getElementsByName("keywordVal")[0].value;
	// document.getElementsByName("episodeKeywords")[0].value=keyWordVal;
	//alert(document.getElementsByName("episodeKeywords")[0].value);
	 //var val=document.getElementsByName("keywordVal")[0].value;
	 //var str=val.split(",");
	 //var str1=str.join();
	 //alert(str1);
	
	if(document.getElementsByName("episodeKeywords")[0].value != "" && document.getElementsByName("episodeKeywords")[0].value.length>100)
	{
		alert("Please ensure Keyword should not exceed 100 characater!");		
		document.getElementsByName("episodeKeywords")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("visitNotes")[0].value != "" && document.getElementsByName("visitNotes")[0].value.length>1000)
	{
		alert("Please ensure Progress Notes should not exceed 1000 characater!");		
		document.getElementsByName("visitNotes")[0].focus();
		return false;
	}

	if(document.getElementsByName('episodeIsOpen')[0].checked==false && document.getElementsByName('episodeIsOpen')[1].checked==false)
	{
		alert("Please Select either You want to end the Treatment or not");
		document.getElementsByName('episodeIsOpen')[0].focus();
		return false;
	}
	
	if(!validateNextVisitModes())
		return false;
	
	/*if(document.getElementsByName('visitNotes')[0].value=="")
	{
		alert("Please enter Visit Summary");
		document.getElementsByName('visitNotes')[0].focus();
		return false;
	}*/
	
	var radios = document.getElementsByName('episodeIsOpen');
	for(var i=0;i<radios.length;i++)	radios[i].disabled = false;
	//document.getElementsByName("crNoSelected")[0].value="";
	document.getElementsByName("episodeNextVisitDate")[0].disabled = false;
	setfreeText();
	return true;
}

function getSchedule(event)
{	
	var unitCode = document.getElementsByName("departmentUnitCode")[0].value;
	var userId =  document.getElementsByName("loginSeatId")[0].value;
	var date = document.getElementsByName("entryDate")[0].value;
	var tarFun = "setScheduleDate(";
	var path="/HISClinical/opd/opdRosterSchedulePopup.cnt?hmode=SHOWSCHEDULE&departmentUnitCode="+unitCode+"&userId="+userId+"&entryDate="+date+"&targetFunction="+tarFun;	
	openPopup(createFHashAjaxQuery(path),event,400,600);
}

function setScheduleDate(_date)
{
	document.getElementsByName("episodeNextVisitDate")[0].value = _date;
	
}






function validateForm()
{
	document.getElementsByName('episodeNextVisitDate')[0].value=document.getElementsByName('nextVisitDateTag')[0].value;	
	if(!validateDates())
		return false;
	return true;
}

function validateDates()
{
	var nextVisitDt = document.getElementsByName("episodeNextVisitDate")[0];
	var entryDate = document.getElementsByName("entryDate")[0];

	/*if(!isEmpty(document.forms[0].effectiveFrom,"Effective From") )
		return false;*/
	if(nextVisitDt.value!="" && !compareDateCall(entryDate,nextVisitDt,2,"Current Date","Next Visit Date") )
		return false;
	return true;
}

function compareDateCall(d1,d2,mode,l1,l2) 
{
	var valid=true;
	if(isEmpty(d1,l1) && isEmpty(d2,l2))
	{
 		if(compareDate(d1,d2, mode))
			valid = true;
		else
		{
			alert(l1+" can't be greater than "+l2);
			valid = false;
		}
	}
	else
		valid=false;
	return valid;
}


function callOnChangeNextDate()
{
	//document.getElementsByName('episodeNextVisitDate')[0].value=document.getElementsByName('nextVisitDateTag')[0].value;
	document.getElementsByName('nextVisitDateText')[0].value=document.getElementsByName('nextVisitDateTag')[0].value;
}


//To Save the Visit Summary Details along with Appointment Details, Added by Singaravelan on 23-Mar-2015
function saveVisitSummarywithAppointment()
{
	var valid=false; 
	//alert("Inside the Summary Save");
	valid=validateSave();
	if(valid){
		if(undefined!=$('#aptTagRow') && ($("#aptTagRow").is(":visible")) ){
			//alert("Inside Appointment Save");
			//alert($('#aptTagRow').html());
			if(createAppointmentWithoutDiv()){
				if(document.getElementsByName("appointmentNo")[0].value!=null && document.getElementsByName("appointmentNo")[0].value!='')
					{
					document.getElementsByName("patNextAptNo")[0].value=document.getElementsByName("appointmentNo")[0].value;
					if(document.getElementsByName("appointmentQueueNo")[0].value!='undefined')
					document.getElementsByName("patNextAptQueueNo")[0].value=document.getElementsByName("appointmentQueueNo")[0].value;
					else
					document.getElementsByName("patNextAptQueueNo")[0].value="";
					document.getElementsByName("patNextAptSlot")[0].value=document.getElementsByName("slotST")[0].value;
					alert(document.getElementsByName("patNextAptNo")[0].value);
					alert(document.getElementsByName("patNextAptSlot")[0].value);
					alert(document.getElementsByName("patNextAptQueueNo")[0].value);
					}
				submitFormOnValidate(true,'SAVE');
			}
			else
					alert("Problem in creating the Appointment");
			
		}
		else
		{
			//alert("Inside Non Appointment Save");
			submitFormOnValidate(true,'SAVE');
			
		}
			
	}	
}

//To Modify the Visit Summary Details along with Appointment Details, Added by Singaravelan on 23-Mar-2015
function modifyVisitSummarywithAppointment()
{
	var valid=false; 
	//alert("Inside the Summary Save");
	valid=validateSave();
	if(valid){
		if(undefined!=$('#aptTagRow') && ($("#aptTagRow").is(":visible")) ){
			//alert("Inside Appointment Save");
			//alert($('#aptTagRow').html());
			if(createAppointmentWithoutDiv()){
				if(document.getElementsByName("appointmentNo")[0].value!=null && document.getElementsByName("appointmentNo")[0].value!='')
					{
					document.getElementsByName("patNextAptNo")[0].value=document.getElementsByName("appointmentNo")[0].value;
					if(document.getElementsByName("appointmentQueueNo")[0].value!='undefined')
					document.getElementsByName("patNextAptQueueNo")[0].value=document.getElementsByName("appointmentQueueNo")[0].value;
					else
					document.getElementsByName("patNextAptQueueNo")[0].value="";
					document.getElementsByName("patNextAptSlot")[0].value=document.getElementsByName("slotST")[0].value;
					alert(document.getElementsByName("patNextAptNo")[0].value);
					alert(document.getElementsByName("patNextAptSlot")[0].value);
					alert(document.getElementsByName("patNextAptQueueNo")[0].value);
					}
				submitFormOnValidate(true,'MODIFYSAVE');
			}
			else
					alert("Problem in creating the Appointment");
			
		}
		else
		{
			//alert("Inside Non Appointment Save");
			submitFormOnValidate(true,'MODIFYSAVE');
			
		}
			
		
		
		
}}


function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    //alert("hi");
    clear(id);
    
    //  document.getElementById(ClientsID.txtantcedentcausecptid).value = "";
    //  document.getElementById(ClientsID.txtccptid).value = "";


});


function clear(id)
{
	//alert(id); 
	var elmtid=id.split('_');
	var trgt=elmtid[1];
	var targetPrefferedTerm="";  
	 var targetConceptId="";
	 document.getElementById(id).value="";
	 if(trgt=="1")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTVisitReason")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdVisitReason")[0];   //concept Id
		 document.getElementsByName("visitReason")[0].value="";
		
		}
	 if(trgt=="2")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTKeywords")[0];  //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdKeywords")[0];   //concept Id
		 document.getElementsByName("keywords")[0].value="";
		}
	 if(trgt=="3")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTVisitNotes")[0]; //preffered term
		  targetConceptId=document.getElementsByName("snomdCIdVisitNotes")[0];  //concept Id
		  document.getElementsByName("visitNotes")[0].value="";
		
		}
	 if(trgt=="4")
		{
		
		 targetPrefferedTerm=document.getElementsByName("snomdPTEpisodeSummary")[0]; //preffered term
		 targetConceptId=document.getElementsByName("snomdCIdEpisodeSummary")[0];  //concept Id
		 document.getElementsByName("episodeSummary")[0].value="";
		}

		    targetPrefferedTerm.value = "";
		    targetConceptId.value = "";
	 
}

function setAppointmentTag()
{
	if(document.getElementsByName("isUnitAppointmentBased")[0] && document.getElementsByName("isUnitAppointmentBased")[0].value==1)
	{
		getViewSummaryAptSlotDetails();
	}
}