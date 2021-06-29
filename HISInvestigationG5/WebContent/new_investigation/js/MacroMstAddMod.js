function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	if(document.forms[0].macroText &&document.getElementsByName("macroText")[0].value=="")
	{
		alert("Enter Macro Name");
		document.getElementsByName("macroText")[0].focus();
		return false;
	}
	
   
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

 
  
 