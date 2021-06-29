function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	if(!isEmpty(document.forms[0].injuryDescription,"Injury Description" ))
	{
		return false;
	}
	if(document.forms[0].hmode.value=="MODIFYSAVE"){
		if(document.forms[0].isActive && document.forms[0].isActive.value=="-1")
		{
			alert("Is Active Status  ... ");
			document.forms[0].isActive.focus();
			return false;
		}
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
  
   document.getElementsByName('injuryDescription')[0].value="";
   //document.getElementsByName('isActive')[0].value="1";
   document.forms[0].injuryDescription.focus();
  
  
 }
  
  
  
      
      
  
 
      
