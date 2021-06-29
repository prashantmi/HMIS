function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
 
	if(document.getElementsByName("sampleCode")[0].value=="-1")
	{
		alert("Select SAMPLE ");
		document.getElementsByName("sampleCode")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("uomCode")[0].value=="-1")
	{
		alert("Select Unit of Measurement Name ");
		document.getElementsByName("uomCode")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("containerCode")[0].value=="-1")
	{
		alert("Select Container ");
		document.getElementsByName("containerCode")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("defaultSample")[0].value=="")
	{
		alert("Is Default?");
		document.getElementsByName("defaultSample")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("sampleQuantity")[0].value=="")
	{
		alert("Is Default?");
		document.getElementsByName("sampleQuantity")[0].focus();
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
     submitPage('CLEAR');
 }


