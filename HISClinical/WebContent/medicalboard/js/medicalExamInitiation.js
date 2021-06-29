window.onload=function(){
	var winl = (screen.width - 500) / 2;
	var wint = (screen.height - 300) / 2;
	
	if(document.getElementsByName("selectedCandidate")){
		var selectedCandidate=document.getElementsByName("selectedCandidate")
		var checkedItem=document.getElementsByName("checkedItem")[0].value.split("#")
		for(var i=0;i<selectedCandidate.length;i++){
			for(var j=0;j<checkedItem.length;j++){
				if(checkedItem[j]==selectedCandidate[i].value){
					selectedCandidate[i].checked=true;
				}
			}
		}
	}
	
	if(document.getElementsByName('hmode')[0].value=="GETREFERMAPPINGIST"){
		popup=window.open("/HISClinical/medicalboard/medicalExamInitiation.cnt?hmode=POPUP" , "Select Refer","height=300,width=600 ,top=" + wint + ",left=" + winl);
		if(!popup.opener){
	     popup.opener = self;
	 	}
	}
	if(document.getElementsByName('hmode')[0].value=="GETINVESTIGATIONMAPPINGIST"){
		popup=window.open("/HISClinical/medicalboard/medicalExamInitiation.cnt?hmode=INVMAPPING" , "Select Refer","height=300,width=600 ,top=" + wint + ",left=" + winl);
		if(!popup.opener){
	     popup.opener = self;
	 	}
	}

}

function getCandidateList(obj){
	submitForm('GETCANDIDATELIST');

}

function getReferMapping(){
	if(validateSelection() && validateIsRefer()){
		var winl = (screen.width - 500) / 2;
		var wint = (screen.height - 300) / 2;
		//var certificateTypeID=document.getElementsByName('certificateTypeID')[0].value
		//var selectedCandidate=document.getElementsByName('selectedCandidate')
		/*		
		popup=window.open("/HISClinical/medicalboard/medicalExamInitiation.cnt?hmode=GETREFERMAPPINGIST&certificateTypeID=" + certificateTypeID +"&selectedCandidate=" + selectedCandidate, "Select Refer","height=300,width=500 ,top=" + wint + ",left=" + winl);
		if(!popup.opener){
	     popup.opener = self;
	 	}*/
	 	var checkedItem="";
	 	
	 	var selectedCandidate=document.getElementsByName("selectedCandidate")
	 	for(var i=0;i<selectedCandidate.length;i++){
			if(selectedCandidate[i].checked){
				checkedItem=checkedItem +"#"+selectedCandidate[i].value
			}
		}
		checkedItem=checkedItem.substring(1)
		document.getElementsByName("checkedItem")[0].value=checkedItem
		//alert(document.getElementsByName("checkedItem")[0].value)
	 	document.getElementsByName('hmode')[0].value="GETREFERMAPPINGIST"
	 	document.forms[0].submit()
	}
	else{
		return false;
	}
}
function setSceduleDate(obj){
	if(obj.checked){
		document.getElementById("scheduleDateDiv").style.display="block";
	}
	else{
		document.getElementById("scheduleDateDiv").style.display="none";
		document.getElementsByName("examDate")[0].value="";
	}
}

function selectAllCandidate(obj){
	var selectedCandidate=document.getElementsByName("selectedCandidate")
	if(obj.checked){
		for(var i=0;i<selectedCandidate.length;i++){
			selectedCandidate[i].checked=true;
		}
	}
	else{
		for(var i=0;i<selectedCandidate.length;i++){
			selectedCandidate[i].checked=false;
		}
	}
}

function validateSelection(){
	var selectedCandidate=document.getElementsByName("selectedCandidate")
	var valid=false;
	for(var i=0;i<selectedCandidate.length;i++){
		if(selectedCandidate[i].checked){
			valid=true;
			
			break;
		}
	}
	if(!valid){
		alert("Please Select at least One Candidate")
	}
	return valid
}


function saveReferDetail(){
	//if(validateIsRefer()){
		submitForm('SAVEREFERDETAIL');
	///}

}
function saveRaisedInvestigation(){
	//if(validateIsRefer()){
		submitForm('SAVEINVESTIGATIONDETAIL');
	///}

}

function getCandidateReferDetail(patCrNo){
	var winl = (screen.width - 500) / 2;
	var wint = (screen.height - 300) / 2;
	var path="/HISClinical/medicalboard/medicalExamInitiation.cnt?hmode=GETCANDIDATEREFERDTL&patCrNo=" + patCrNo ;
	popup=window.open(path, "Select Refer","height=300,width=600 ,top=" + wint + ",left=" + winl);
	if(!popup.opener){
     popup.opener = self;
 	}
}

function getCandidateInvDetail(patCrNo,episodeCode,episodeVisitNo){
	var winl = (screen.width - 500) / 2;
	var wint = (screen.height - 300) / 2;
	var path="/HISClinical/medicalboard/medicalExamInitiation.cnt?hmode=GETCANDIDATEINVDTL&patCrNo=" + patCrNo + "&episodeCode=" + episodeCode + "&episodeVisitNo= "+ episodeVisitNo;
	popup=window.open(path, "Select Refer","height=300,width=600 ,top=" + wint + ",left=" + winl);
	if(!popup.opener){
     popup.opener = self;
 	}
}

function getInvestigationMapping(){
	if(validateSelection() && validateIsInvRaised()){
		var winl = (screen.width - 500) / 2;
		var wint = (screen.height - 300) / 2;
		var checkedItem="";
	 	var selectedCandidate=document.getElementsByName("selectedCandidate")
	 	for(var i=0;i<selectedCandidate.length;i++){
			if(selectedCandidate[i].checked){
				checkedItem=checkedItem +"#"+selectedCandidate[i].value
			}
		}
		checkedItem=checkedItem.substring(1)
		document.getElementsByName("checkedItem")[0].value=checkedItem
		document.getElementsByName('hmode')[0].value="GETINVESTIGATIONMAPPINGIST"
	 	document.forms[0].submit()
	}
	else{
		return false;
	}
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

function referExternal()
{
	if(validateSelection())
	{
	var winl = (screen.width - 500) / 2;
	var wint = (screen.height - 300) / 2;
	window.open("/HISClinical/medicalboard/medicalExamInitiation.cnt?hmode=SHOWEXTERNAL" , "External Refer","height=300,width=600 ,top=" + wint + ",left=" + winl);
		if(!popup.opener){
	     popup.opener = self;
	 	}
	 }
}

function getCandidateExtReferDetail(requsitionId,e){
	var winl = (screen.width - 500) / 2;
	var wint = (screen.height - 300) / 2;
	var path="/HISClinical/medicalboard/medicalExamInitiation.cnt?hmode=GETEXTREFERDTL&reqID=" + requsitionId;
	/*popup=window.open(path, "Select Refer","height=300,width=600 ,top=" + wint + ",left=" + winl);
	if(!popup.opener){
     popup.opener = self;
 	}*/
 	//alert('openDependentPopup')
 	openDependentPopup(path,e,300,600,true)
}