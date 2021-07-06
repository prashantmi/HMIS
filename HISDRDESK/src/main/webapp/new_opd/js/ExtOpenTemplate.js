window.closePopUpIframe = function(){
	$('#OpenComplaintsTemplateModal').modal('hide');$('#OpenComplaintsTemplateModalFrameId').remove();
	$('#OpenQuestionnaireTemplateModal').modal('hide');$('#OpenQuestionnaireTemplateModalFrameId').remove();
	
}
$(document).ready(function(){
	
	$("#OpenComplaintsTemplateModal").on('hide.bs.modal', function () {
		$('#OpenComplaintsTemplateModalFrameId').remove();
	 });
});
$(document).ready(function(){
	
	$("#OpenQuestionnaireTemplateModal").on('hide.bs.modal', function () {
		$('#OpenQuestionnaireTemplateModalFrameId').remove();
	 });
});

$(document).ready(function(){
	
	$("#OpenHistoryTemplateModal").on('hide.bs.modal', function () {
		$('#OpenHistoryTemplateModalFrameId').remove();
	 });
});


$(document).ready(function(){
	
	$("#OpenExaminationTemplateModal").on('hide.bs.modal', function () {
		$('#OpenExaminationTemplateModalFrameId').remove();
	 });
});

$(document).ready(function(){
	
	$("#AdmissionAdviceTemplateModal").on('hide.bs.modal', function () {
		$('#AdmissionAdviceTemplateModalFrameId').remove();
	 });
});

$(document).ready(function(){
	
	$("#PatientReVisitTemplateModal").on('hide.bs.modal', function () {
		$('#PatientReVisitTemplateModalFrameId').remove();
	 });
});



function OpenComplaintTemplate(e)
{
	console.log('Open Template');
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[5];
	console.log(patCompleteGeneralDtlPrescriptionPanel);
	$('#printPrescFrameOnMainDeskId').remove();
	var vitalJSON = { 
			"pat_Name":patGeneralDtl[0],
			"CR_No":patGeneralDtl[1],
			"episodeCode":patGeneralDtl[2],
			"patGender":patGeneralDtl[3].split('/')[0],
			"patAge":patGeneralDtl[3].split('/')[1],
			"patCat":patGeneralDtl[3].split('/')[2],
			"patQueueNo":patGeneralDtl[3].split('/')[3] ,
			 "episodeVisitNo":$('#patEpisodeVisitNoPrescriptionPanel').text(),
			 "Hosp_Code": $('#patHospitalCodePrescriptionPanel').text()
			  }
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	 
//	var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
//	console.log('varSSOTicketGrantingTicket'+varSSOTicketGrantingTicket);
		var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getComplaintsTemplate&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;		
		console.log(complainturl);
		//$('#OpenComplaintsTemplateModal .modal-body').append('');
		$('#OpenComplaintsTemplateModal .modal-body').prepend('<iframe id="OpenComplaintsTemplateModalFrameId" style="width:100%;height:80vh;" src="'+complainturl+'"></iframe>');
	$('#OpenComplaintsTemplateModal').modal('show');
	$("#OpenComplaintsTemplateModal").find(".modal-backdrop").css({"z-index": "-1"});
}


function OpenHistoryTemplate(e)
{
	console.log('Open Template');
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[6];
	console.log(patCompleteGeneralDtlPrescriptionPanel);
	$('#printPrescFrameOnMainDeskId').remove();
	var vitalJSON = { 
			"pat_Name":patGeneralDtl[0],
			"CR_No":patGeneralDtl[1],
			"episodeCode":patGeneralDtl[2],
			"patGender":patGeneralDtl[3].split('/')[0],
			"patAge":patGeneralDtl[3].split('/')[1],
			"patCat":patGeneralDtl[3].split('/')[2],
			"patQueueNo":patGeneralDtl[3].split('/')[3] ,
			 "episodeVisitNo":$('#patEpisodeVisitNoPrescriptionPanel').text(),
			 "Hosp_Code": $('#patHospitalCodePrescriptionPanel').text()
			  }
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	 
//	var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
//	console.log('varSSOTicketGrantingTicket'+varSSOTicketGrantingTicket);
		var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getHistoryTemplate&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
		//var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getHistoryTemplate&patCrNo=961012000000872&episodeCode=10312001&episodeVisitNo=1&deptUnitCode=10312&varSSOTicketGrantingTicket="+varSSOTicketGrantingTicket;
	//	var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getHistoryTemplate&patCrNo=961012000000872&episodeCode=10312001&episodeVisitNo=1&deptUnitCode=10312&varSSOTicketGrantingTicket="+varSSOTicketGrantingTicket;
		console.log(complainturl);
		//$('#OpenComplaintsTemplateModal .modal-body').append('');
		$('#OpenHistoryTemplateModal .modal-body').prepend('<iframe id="OpenHistoryTemplateModalFrameId" style="width:100%;height:80vh;" src="'+complainturl+'"></iframe>');
	$('#OpenHistoryTemplateModal').modal('show');
	$("#OpenHistoryTemplateModal").find(".modal-backdrop").css({"z-index": "-1"});
}
function OpenExaminationTemplate(e)
{
	console.log('Open Template');
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[6];
	console.log(patCompleteGeneralDtlPrescriptionPanel);
	$('#printPrescFrameOnMainDeskId').remove();
	var vitalJSON = { 
			"pat_Name":patGeneralDtl[0],
			"CR_No":patGeneralDtl[1],
			"episodeCode":patGeneralDtl[2],
			"patGender":patGeneralDtl[3].split('/')[0],
			"patAge":patGeneralDtl[3].split('/')[1],
			"patCat":patGeneralDtl[3].split('/')[2],
			"patQueueNo":patGeneralDtl[3].split('/')[3] ,
			 "episodeVisitNo":$('#patEpisodeVisitNoPrescriptionPanel').text(),
			 "Hosp_Code": $('#patHospitalCodePrescriptionPanel').text()
			  }
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	 
//	var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
//	console.log('varSSOTicketGrantingTicket'+varSSOTicketGrantingTicket);
		var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getExaminationTemplate&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
		//var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getExaminationTemplate&patCrNo=961012000000872&episodeCode=10312001&episodeVisitNo=1&deptUnitCode=10312&varSSOTicketGrantingTicket="+varSSOTicketGrantingTicket;
		console.log(complainturl);
		//$('#OpenComplaintsTemplateModal .modal-body').append('');
		$('#OpenExaminationTemplateModal .modal-body').prepend('<iframe id="OpenExaminationTemplateModalFrameId" style="width:100%;height:80vh;" src="'+complainturl+'"></iframe>');
	$('#OpenExaminationTemplateModal').modal('show');
	$("#OpenExaminationTemplateModal").find(".modal-backdrop").css({"z-index": "-1"});
}


function OpenQuestionnaireTemplate(e)
{
	console.log('Open Template');
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[6];
	console.log(patCompleteGeneralDtlPrescriptionPanel);
	$('#printPrescFrameOnMainDeskId').remove();
	var vitalJSON = { 
			"pat_Name":patGeneralDtl[0],
			"CR_No":patGeneralDtl[1],
			"episodeCode":patGeneralDtl[2],
			"patGender":patGeneralDtl[3].split('/')[0],
			"patAge":patGeneralDtl[3].split('/')[1],
			"patCat":patGeneralDtl[3].split('/')[2],
			"patQueueNo":patGeneralDtl[3].split('/')[3] ,
			 "episodeVisitNo":$('#patEpisodeVisitNoPrescriptionPanel').text(),
			 "Hosp_Code": $('#patHospitalCodePrescriptionPanel').text()
			  }
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	 
//	var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
//	console.log('varSSOTicketGrantingTicket'+varSSOTicketGrantingTicket);
		var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getQuestionnaireTemplate&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
		//var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getQuestionnaireTemplate&patCrNo=961012000000872&episodeCode=10312001&episodeVisitNo=1&deptUnitCode=10312&varSSOTicketGrantingTicket="+varSSOTicketGrantingTicket;
		console.log(complainturl);
		//$('#OpenComplaintsTemplateModal .modal-body').append('');
		$('#OpenQuestionnaireTemplateModal .modal-body').prepend('<iframe id="OpenQuestionnaireTemplateModalFrameId" style="width:100%;height:80vh;" src="'+complainturl+'"></iframe>');
	$('#OpenQuestionnaireTemplateModal').modal('show');
	$("#OpenQuestionnaireTemplateModal").find(".modal-backdrop").css({"z-index": "-1"});
}



function getComplaintsTemplateHTMLString()
{
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[6];
	
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	
//alert("hello");
 	//var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	//var Ftlvalue=document.getElementsByName("ftlValueForExamination")[0].value; 
 	//var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;

	 var action = "/HISClinical/emr/ehrComposition_ENC_COMPLAINTS.cnt?hmode=getComplaintsTemplateHTMLStringData&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
 
	$.ajax({
	url: action,  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{

		 //alert(data);
       //document.getElementsByName('printComplaintModalDivId')[0].value  = data;
       document.getElementById("printComplaintModalDivId").innerHTML = data; 
       $('#printComplaintModal').modal('show');
	   //document.getElementById('examination').innerHTML = data;	
		
      },
	
      error: function(data)
      {
    	    swal('request failed :');
    	}

	});
	
	}


function getHistoryTemplateHTMLString()
{
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[6];
	
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	
//alert("hello");
 	//var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	//var Ftlvalue=document.getElementsByName("ftlValueForExamination")[0].value; 
 	//var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;

	 var action = "/HISClinical/emr/ehrComposition_PAT_HISTORY.cnt?hmode=getHistoryTemplateHTMLStringData&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
 
	$.ajax({
	url: action,  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{

		// alert(data);
		  document.getElementById("printHistoryModalDivId").innerHTML = data; 
	       $('#printHistoryModal').modal('show');
      // document.getElementsByName('ftlValueForExamination')[0].value  = data;

	   //document.getElementById('examination').innerHTML = data;	
		
      },
	
      error: function(data)
      {
    	    swal('request failed :');
    	}

	});
	
	}



function getExaminationTemplateHTMLString()
{
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[6];
	
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	
//alert("hello");
 	//var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	//var Ftlvalue=document.getElementsByName("ftlValueForExamination")[0].value; 
 	//var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;

	 var action = "/HISClinical/emr/ehrComposition_ENC_EXAM.cnt?hmode=getExaminationTemplateHTMLStringData&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
 
	$.ajax({
	url: action,  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{

		// alert(data);
		document.getElementById("printExaminationModalDivId").innerHTML = data; 
	       $('#printExaminationModal').modal('show');
      // document.getElementsByName('ftlValueForExamination')[0].value  = data;

	   //document.getElementById('examination').innerHTML = data;	
		
      },
	
      error: function(data)
      {
    	    swal('request failed :');
    	}

	});
	
	}




function getExaminationTemplateHTMLString()
{
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[6];
	
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	
//alert("hello");
 	//var PatCrNo=document.getElementsByName("patCrNo")[0].value; 
 	//var Ftlvalue=document.getElementsByName("ftlValueForExamination")[0].value; 
 	//var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;

	 var action = "/HISClinical/emr/ehrComposition_QUESTIONNAIRE.cnt?hmode=getQuestionnaireTemplateHTMLStringData&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
 
	$.ajax({
	url: action,  
    type : 'GET',
    datatype: "json",
    async : false,
	success: function(data)
	{

		// alert(data);
		 document.getElementById("printQuestionareModalDivId").innerHTML = data; 
	       $('#printQuestionareModal').modal('show');
      // document.getElementsByName('printComplaintModalDivId')[0].value  = data;

	   //document.getElementById('examination').innerHTML = data;	
		
      },
	
      error: function(data)
      {
    	    swal('request failed :');
    	}

	});
	
	}


function OpenAdmissionadviceTemplate(e)
{
	//alert('1');
	console.log('Open Admission advice');
	var patGenAgeCat = $('#patGenAgeCatPrescriptionPanel').text();
	var patGeneralDtl = $('input[name=patGeneralDtl]').val().split('#');
	var CR_No = $('#patCrNoPrescriptionPanel').text();
	var hospCode = $('#patHospitalCodePrescriptionPanel').text();
	var patCompleteGeneralDtlPrescriptionPanel = $('input[name=patCompleteGeneralDtlPrescriptionPanel]').val()
	var fromdeptCode= patCompleteGeneralDtlPrescriptionPanel.split('#')[5];
	console.log(patCompleteGeneralDtlPrescriptionPanel);
	$('#printPrescFrameOnMainDeskId').remove();
	var vitalJSON = { 
			"pat_Name":patGeneralDtl[0],
			"CR_No":patGeneralDtl[1],
			"episodeCode":patGeneralDtl[2],
			"patGender":patGeneralDtl[3].split('/')[0],
			"patAge":patGeneralDtl[3].split('/')[1],
			"patCat":patGeneralDtl[3].split('/')[2],
			"patQueueNo":patGeneralDtl[3].split('/')[3] ,
			 "episodeVisitNo":$('#patEpisodeVisitNoPrescriptionPanel').text(),
			 "Hosp_Code": $('#patHospitalCodePrescriptionPanel').text()
			  }
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	 
//	var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
//	console.log('varSSOTicketGrantingTicket'+varSSOTicketGrantingTicket);
		//var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getComplaintsTemplate&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
		var complainturl	="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode=NEW&varSSOTicketGrantingTicket="+tkt+"&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&departmentUnitCode="+fromdeptCode+"&roomCode=0&isDesk=1";
		console.log(complainturl);
		//$('#OpenComplaintsTemplateModal .modal-body').append('');
		$('#AdmissionAdviceTemplateModal .modal-body').prepend('<iframe id="AdmissionAdviceTemplateModalFrameId" style="width:100%;height:80vh;" src="'+complainturl+'"></iframe>');
	$('#AdmissionAdviceTemplateModal').modal('show');
	$("#AdmissionAdviceTemplateModal").find(".modal-backdrop").css({"z-index": "-1"});
}



function OPENREveistProcess(e)
{
	e.preventDefault;
	//alert('1');
	
	var tkt;
	 if(localStorage.getItem("varSSOTicketGrantingTicketStoreage"))
		 tkt = localStorage.getItem("varSSOTicketGrantingTicketStoreage");
	 
//	var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
//	console.log('varSSOTicketGrantingTicket'+varSSOTicketGrantingTicket);
		//var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getComplaintsTemplate&patCrNo="+patGeneralDtl[1]+"&episodeCode="+patGeneralDtl[2]+"&episodeVisitNo="+$('#patEpisodeVisitNoPrescriptionPanel').text()+"&deptUnitCode="+fromdeptCode+"&varSSOTicketGrantingTicket="+tkt;
		var complainturl	="/HISRegistration/registration/transactions/PatientVisit.action?varSSOTicketGrantingTicket="+tkt+"&iSopdDeskRefral=1";
		console.log(complainturl);
		//alert('1');
		//$('#OpenComplaintsTemplateModal .modal-body').append('');
		$('#PatientReVisitTemplateModal .modal-body').prepend('<iframe id="PatientReVisitTemplateModalFrameId" style="width:100%;height:80vh;" src="'+complainturl+'"></iframe>');
	$('#PatientReVisitTemplateModal').modal('show');
	//$("#PatientReVisitTemplateModal").find(".modal-backdrop").css({"z-index": "-1"});
	//alert('1');
}
