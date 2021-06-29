
function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

/*function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	 
	//alert(document.forms[0].rejectionReason);
	//alert(document.forms[0].rejectionReason.value);
	 
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
	if(document.forms[0].loincSystem && document.getElementsByName("loincSystem")[0].value=="-1")
	{
		alert("Enter Loinc System ... ");
		document.forms[0].loincSystem.focus();
		return false;                          
	}
	 
   
   return true;
 } 	
	*/
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

/*function clearForm()
 {
  
   document.getElementsByName('rejectionReason')[0].value="";

   document.getElementsByName('usedForType')[0].value="";
   
    document.getElementsByName('loincSystem')[0].value="";
   document.getElementsByName('remarks')[0].value="";
    
   
 }*/