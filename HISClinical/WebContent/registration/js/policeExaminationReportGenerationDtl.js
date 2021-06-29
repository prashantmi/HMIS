function callThisOnload()
{
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
	//DutyOfficerDetail();
}

function getPoliceExamReportGenerationPage(objEpChk){
	var arrCrNepCodeNepVisitNo= objEpChk.value.split('^');
	document.getElementsByName("episodeCode")[0].value=arrCrNepCodeNepVisitNo[1];
	document.getElementsByName("episodeVisitNo")[0].value=arrCrNepCodeNepVisitNo[2];
	
	//document.getElementsByName("episodeCode")[0].value=objEpChk.value.split('^')[1];
	//document.getElementsByName("episodeVisitNo")[0].value=objEpChk.value.split('^')[2];
	
	submitForm('POLICEEXAMREQTDTL');
}
function getHandOverDtl(objpoliceExamReportGnRadio){
	document.getElementsByName("selectedEpisodeIndex")[0].value=objpoliceExamReportGnRadio.value;
	submitForm('HANDOVERDETAIL');
}


function validateSave()
{
	var valid=true;
	//alert("policeExaminationReportGenerationDtl.js :: validateSave()");
	/*alert("validateObjects(document.getElementsByName("handOverToOffIncharge")[0],"Handover To Officer Name") "+)
	alert(" "+)
	alert(" "+)
	alert(" "+)
	alert(" "+)
	alert(" "+)
	alert(" "+)*/
	if( validateObjects(document.getElementsByName("handOverToOffIncharge")[0],"Handover To Officer Name") &&
		validateObjects(document.getElementsByName("handOverToOffDesignation")[0],"Handover To Officer Designation") &&
		//validateObjects(document.getElementsByName("handOverToOffBatchNo")[0],"Handover To Officer Batch No") &&
		validateObjects(document.getElementsByName("handOverDate")[0],"Handover Date") &&
		validateObjects(document.getElementsByName("isFinal")[0],"Is Final") 
		//&&
		//validateObjects(document.getElementsByName("caseRemarks")[0],"Investigating Ofiicer Name") 
	  )
	{
		valid= true;
		if(document.getElementsByName("isFinal")[0].checked){
			document.getElementsByName("isFinal")[0].value="1";
			if(validateObjects(document.getElementsByName("finalRemarks")[0],"Remark") == false)
				valid= false;
				
		}else{
			document.getElementsByName("isFinal")[0].value="0";
		}
	}	
	else
	{
		valid= false;
	}
	
	return valid;
}

function openPrintPopup(e,templateId){
	var isDirectCall = document.getElementsByName('isDirectCall')[0].value;
	var patCrNo = document.getElementsByName('patCrNo')[0].value;
	var deskType = document.getElementsByName('deskType')[0].value;
	var episodeCode = document.getElementsByName('episodeCode')[0].value;
	var episodeVisitNo = document.getElementsByName('episodeVisitNo')[0].value;
	var deskMenuId = document.getElementsByName('deskMenuId')[0].value;
	var url="/HISClinical/registration/policeExaminationReportGenerationDtl.cnt?hmode=TEMPLATEPOPUP"+
			"&templateId="+templateId+"&isDirectCall="+isDirectCall+"&patCrNo="+patCrNo+"&deskType="+deskType+
			"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&deskMenuId="+258;
	//document.getElementsByName('templateHtmlCode')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML;
	//alert("url :"+url);
    openPopup(url,e,300,600);
}

/*function printPoliceExamRpt(e)
{
	document.getElementsByName('templateHtmlCode')[0].value = document.getElementById('pdfPrintingHTMLData').innerHTML;
	document.getElementsByName('hmode')[0].value='PRINT';
	document.forms[0].submit();
}*/

function clearForm()
{
	document.forms[0].handOverToOffIncharge.value="";
	document.forms[0].handOverToOffDesignation.value="";
	document.forms[0].handOverToOffBatchNo.value="";
	document.forms[0].handOverDate.value="";
	document.forms[0].isFinal.checked=false;
	document.forms[0].caseRemarks="";
}
