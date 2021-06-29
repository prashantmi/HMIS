function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	 
	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
	 
	if(document.forms[0].sampleName &&document.getElementsByName("sampleName")[0].value=="")
	{
		alert("Enter Sample Name ... ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("sampleName")[0].focus();
		return false;
	}
	if(document.forms[0].sampleSName && document.getElementsByName("sampleSName")[0].value=="")
	{
		alert("Enter Sample Short Name ... ");
		document.forms[0].sampleSName.focus();
		return false;                          
	}
	/*if(document.forms[0].loincSystem && document.getElementsByName("loincSystem")[0].value=="-1")
	{
		alert("Enter Loinc System ... ");
		document.forms[0].loincSystem.focus();
		return false;                          
	}
	 */
   
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

function clearAddForm()
 {
  
   document.getElementsByName('sampleName')[0].value="";

   document.getElementsByName('sampleSName')[0].value="";
   document.getElementsByName('loincSystem')[0].value="-1";
   document.getElementsByName('remarks')[0].value="";
    
   
  
   
  
 }
  
  
 
 
  
 