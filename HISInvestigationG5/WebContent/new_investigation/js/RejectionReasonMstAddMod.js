
function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	 
	if(document.forms[0].rejectionReason &&document.getElementsByName("rejectionReason")[0].value=="")
	{
		alert("Enter Rejection Reason ... ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("rejectionReason")[0].focus();
		return false;
	}
	if(document.forms[0].usedForType && document.getElementsByName("usedForType")[0].value=="1")
	{
		alert("Select Used For Type ... ");
		document.forms[0].usedForType.focus();
		return false;                          
	}

	 
   
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}
