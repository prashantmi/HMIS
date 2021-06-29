function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	 
	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
	 
	if(document.forms[0].testName &&document.getElementsByName("testName")[0].value=="")
	{
		alert("Enter Test Name ... ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("testName")[0].focus();
		return false;
	}
	if(document.forms[0].testSName && document.getElementsByName("testSName")[0].value=="")
	{
		alert("Enter Test Short Name ... ");
		document.forms[0].testSName.focus();
		return false;                          
	}
	if(document.forms[0].testType && document.getElementsByName("testType")[0].value=="")
	{
		alert("Enter Test Type  ... ");
		document.forms[0].testType.focus();
		return false;                          
	}
	/*if(document.forms[0].loincTiming && document.getElementsByName("loincTiming")[0].value=="-1")
	{
		alert("Select loinc Timing ... ");
		document.forms[0].loincTiming.focus();
		return false;                          
	}*/
   
	if(document.getElementsByName("resultEntryForm")[1].checked==true)
	{
		if(document.getElementsByName("printingTemplateCode")[0].value=="-1")
		{alert("Select the Printing Template ");
		document.getElementsByName("printingTemplateCode")[0].focus();
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

function clearAddForm()
 {
  
	     document.getElementsByName('organicName')[0].value="";
		 document.getElementsByName('organicNameOrder')[0].value="";
		 
 }


  function clearMODIFYForm()
  {
	  submitPage("CLEAR");
  }
  
 function validatesave() {
	
	 if(document.getElementsByName('organicName')[0].value=='')
		 {
		 alert("Please Enter Organism Name");
		 document.getElementsByName("organicName")[0].focus();
		 return false;
		 }
	  if(document.getElementsByName('organicNameOrder')[0].value=='')
	    {
	    alert("Please Enter Display Order");
	    document.getElementsByName('organicNameOrder')[0].focus();
	    return false;
	    }
	 
	  
	 
		 document.forms[0].hmode.value="SAVE";
			document.forms[0].submit();
		 
	 
}
 
 function validatemodify() {
		
	 if(document.getElementsByName('organicName')[0].value=='')
	 {
	 alert("Please Enter Organism Name");
	 document.getElementsByName("organicName")[0].focus();
	 return false;
	 }
  if(document.getElementsByName('organicNameOrder')[0].value=='')
    {
    alert("Please Enter Display Order");
    document.getElementsByName('organicNameOrder')[0].focus();
    return false;
    }
 
 
		 document.forms[0].hmode.value="MODIFYSAVE";
			document.forms[0].submit();
		 
	 
} 
 