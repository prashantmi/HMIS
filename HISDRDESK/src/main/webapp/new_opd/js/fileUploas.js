
$(document).ready(function(){
	
	
	$("#OpenFileUploadModal").on('hide.bs.modal', function () {
		$('#OpenFileUploadModalFrameId').remove();
	 });
});


function save_file(){
	document.forms[0].hmode.value="SAVEFILE";
	document.forms[0].submit();
}

function OpenFileUpload(e)
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
	 
	    
//	var varSSOTicketGrantingTicket=parent.document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
//	console.log('varSSOTicketGrantingTicket'+varSSOTicketGrantingTicket);
		var complainturl	="/HISDRDESK/new_opd/transaction/DoctorDeskAction.cnt?hmode=FILEUPLOAD&vitalJSON="+patGeneralDtl;
		//var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getHistoryTemplate&patCrNo=961012000000872&episodeCode=10312001&episodeVisitNo=1&deptUnitCode=10312&varSSOTicketGrantingTicket="+varSSOTicketGrantingTicket;
	//	var complainturl	="/HISClinical/opd/opdTemplateTab.cnt?hmode=getHistoryTemplate&patCrNo=961012000000872&episodeCode=10312001&episodeVisitNo=1&deptUnitCode=10312&varSSOTicketGrantingTicket="+varSSOTicketGrantingTicket;
		console.log(complainturl);
		//$('#OpenComplaintsTemplateModal .modal-body').append('');
		$('#OpenFileUploadModal .modal-body').prepend('<iframe id="OpenFileUploadModalFrameId" style="width:100%;height: 170px;" src="'+complainturl+'"></iframe>');
	$('#OpenFileUploadModal').modal('show');
	$("#OpenFileUploadModal").find(".modal-backdrop").css({"z-index": "-1"});
}
function getDatasave(){
	//alert($('#strMsgTypeid').val());
	if($('#strMsgTypeid').val() == '1'){
		window.parent.swal('File Uploaded Sucessfully',"","success");
		
	}
	if($('#strMsgTypeid').val() == '2'){
		window.parent.swal('Error !!!');
		
	}
}

function addFiles(){
	//alert($('#uploadBtn').val().split('\\').pop());
	if($('#uploadBtn').val()!=undefined && $('#uploadBtn').val().trim()!=""){

		$("#fileName").html($('#uploadBtn').val().split('\\').pop());
	}
		
}
