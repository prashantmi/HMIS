function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	 
	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
	 
	if(document.getElementsByName("containerName")[0].value=="")
	{
		alert("Enter Container Name");
		document.getElementsByName("containerName")[0].focus();
		return false;
	}
	if(document.getElementsByName("containerVolume")[0].value=="")
	{
		alert("Enter Container Volume");
		document.getElementsByName("containerVolume")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("uomCode")[0].value=="-1")
	{
		alert("Select UOM ");
		document.getElementsByName("uomCode")[0].focus();
		return false;                          
	}
	 
   
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

 
  
 