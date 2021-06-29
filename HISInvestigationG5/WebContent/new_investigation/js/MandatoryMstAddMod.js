function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	
	if(document.forms[0].mandatoryName &&document.getElementsByName("mandatoryName")[0].value=="")
	{
		alert("Enter Mandatory Name");
		document.getElementsByName("mandatoryName")[0].focus();
		return false;
	}
   
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}


 
 
  
 