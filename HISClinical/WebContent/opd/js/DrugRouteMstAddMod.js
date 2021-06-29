function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	
	if(document.getElementsByName('routeClassification')[0].value=="-1")
	{
		alert("Select Drug Route Classification");
		document.getElementsByName('routeClassification')[0].focus();
		return false;
	}
	
	
	if(document.getElementsByName('drugRouteName')[0].value=="")
	{
		alert("Enter Drug Route Name");
		document.getElementsByName('drugRouteName')[0].focus();
		return false;
	}
	
	
	if(document.forms[0].isActive && document.forms[0].isActive.value=="-1")
	{
		alert("Select IsActive Status");
		document.forms[0].isActive.focus();
		return false;                        
	}
	
    return true;
 } 	
	
function finalSubmit(mode)
{
	if (validateFinalSubmit())
	{		
		submitPage(mode);
	}
}


function clearForm()
 {

  
   document.getElementsByName('drugRouteName')[0].value="";
   document.getElementsByName('drugRouteDesc')[0].value="";
   document.getElementsByName('routeClassification')[0].value="-1";
   if(document.forms[0].isActive)
   {
   document.forms[0].isActive.value=="-1"
   }
 
}

  

 
 