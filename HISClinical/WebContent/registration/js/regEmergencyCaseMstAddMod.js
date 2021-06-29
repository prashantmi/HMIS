function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	if(document.forms[0].emergencyCase && document.forms[0].emergencyCase.value=="")
	{
		alert("Enter Emergency Case ... ");
		document.forms[0].emergencyCase.focus();
		return false;
	}
	
	if(document.forms[0].caseType && document.forms[0].caseType.value=="-1")
	{
		alert("Select Case Type ... ");
		document.forms[0].caseType.focus();
		return false;                          
	}
	
	if(document.forms[0].isActive && document.forms[0].isActive.value=="-1")
	{
		alert("Select IsActive ... ");
		document.forms[0].isActive.focus();
		return false;                          
	}
	
	
    return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}




function clearForm()
 {
  
   document.getElementsByName('emergencyCase')[0].value="";
   document.getElementsByName('isMlcReq')[0].value="";
   document.getElementsByName('caseType')[0].value="-1";
  
  
 }
  
  
 
 
      
  
 