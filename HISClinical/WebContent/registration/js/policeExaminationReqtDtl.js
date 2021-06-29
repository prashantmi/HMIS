function callThisOnload()
{
	if(document.getElementsByName("patCrNo")[0]){
		document.getElementsByName("patCrNo")[0].focus();
	}
	//DutyOfficerDetail();
}

function getPoliceExamReqtPage(objEpChk){
	var arrCrNepCodeNepVisitNo= objEpChk.value.split('^');
	document.getElementsByName("episodeCode")[0].value=arrCrNepCodeNepVisitNo[1];
	document.getElementsByName("episodeVisitNo")[0].value=arrCrNepCodeNepVisitNo[2];
	
	//document.getElementsByName("episodeCode")[0].value=objEpChk.value.split('^')[1];
	//document.getElementsByName("episodeVisitNo")[0].value=objEpChk.value.split('^')[2];
	
	submitForm('GETPOLICEDETAIL');
}


function validateSave()
{
	var valid=true;
	
	if( validateObjects(document.forms[0].policeExaminationType,"Examination Type") &&
		//validateObjects(document.forms[0].caseNo,"FIR Number") &&
		validateObjects(document.forms[0].policeStation,"Police Station") &&
		//validateObjects(document.forms[0].docketNo,"DD(Docket) No") &&
		validateObjects(document.forms[0].letterNo,"Letter No") &&
		validateObjects(document.forms[0].officerIncharge,"Investigating Ofiicer Name") &&
		validateObjects(document.forms[0].ioDesignation,"Investigating Ofiicer Designation") 
		//&& validateObjects(document.forms[0].ioBatchNo,"Investigating Ofiicer Badge No") 
		//validateObjects(document.forms[0].caseRemarks,"Case Remark")
	  )
	{
		valid= true;
	}	
	else
	{
		valid= false;
	}
	return valid;
}



function clearForm()
{
	document.forms[0].policeExaminationType.value="-1";
	document.forms[0].caseNo.value="";
	document.forms[0].policeStation.value="-1";
	document.forms[0].docketNo.value="";
	document.forms[0].letterNo.value="";
	document.forms[0].officerIncharge.value="";
	document.forms[0].ioDesignation.value="";
	document.forms[0].ioBatchNo.value="";
}
