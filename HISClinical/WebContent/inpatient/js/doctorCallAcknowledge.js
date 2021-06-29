

function clearForm()
 {

   document.getElementsByName('doctorRemarks')[0].value="";
     
 }
 
function togglePeon(chkBox){

	if(chkBox.checked){
	  document.getElementById("showPeon").style.display="block";
	}
	else{
	    document.getElementById("showPeon").style.display="none";
	
	}
}


function togglePhone(chkBox){

	if(chkBox.checked){
	  document.getElementById("showPhone").style.display="block";
	}
	else{
	    document.getElementById("showPhone").style.display="none";
	
	}
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}

 window.onload= function()
 {
 	if(document.getElementsByName("hmode")[0].value=="GETDETAILS"){
 	if(document.getElementsByName("doctorRemarks")[0].value!="")
 	{
 		document.getElementsByName("doctorRemarks")[0].readOnly=true;
 	}
 	}
}

function closeWindow()
{
window.close();
}

 

function validate()
{
if(document.forms[0].doctorRemarks.value=="")
	{
		alert("Please Enter Doctor Remarks");
		document.forms[0].doctorRemarks.focus();
		return false;                          
	}

   return true;
}

function finalSubmit(mode)
{
	if (!validate()) return;
	submitForm21(mode);
	
}
function getRemarks(index)
 {
 document.getElementsByName("index")[0].value=index;
 submitForm21('GETCALLREMARKS');
 } 
 
function addProgressNotes(event)
{
	var path='/HISClinical/inpatient/doctorCallAcknowledge.cnt?hmode=ADDNOTES&processId=8';
	openPopup(createFHashAjaxQuery(path),event,300,600);
}
