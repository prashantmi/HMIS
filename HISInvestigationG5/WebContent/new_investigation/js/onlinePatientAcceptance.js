function submitPage(mode)
{
	alert(mode);
	 
	 var hmode="GETDETAILS"; // create function in action file, UTIL,...... with query and populate the list in session and rediredt to NEW in ACTION
	 document.getElementsByName('hmode')[0].value=hmode;
		document.forms[0].submit(); 	 
 
	 
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	 
	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
	 alert("aaa");
	// alert(document.getElementsByName("patCRNo")[0].value);
	if(document.getElementsByName("patCRNo")[0].value=="")
	{
		alert("Enter CR no Name ... ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("patCRNo")[0].focus();
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
  
   document.getElementsByName('sampleName')[0].value="";

   
    
   
  
   
  
 }
  
  
 
 
  
 