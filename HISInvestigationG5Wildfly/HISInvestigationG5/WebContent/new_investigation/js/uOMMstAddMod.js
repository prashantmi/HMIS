function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	 
	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
	 
	if(document.forms[0].uomName &&document.getElementsByName("uomName")[0].value=="")
	{
		alert("Enter UOM Name ... ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("uomName")[0].focus();
		return false;
	}
	/*if(document.forms[0].loincProperty && document.getElementsByName("loincProperty")[0].value=="-1")
	{
		alert("Enter LOINC Property Name ... ");
		document.forms[0].loincProperty.focus();
		return false;                          
	}*/
	 
   
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

function clearAddForm()
 {
  
   document.getElementsByName('uomName')[0].value="";

   document.getElementsByName('loincProperty')[0].value="-1";
   
    
   
  
   
  
 }
  
  
 
 
  
 