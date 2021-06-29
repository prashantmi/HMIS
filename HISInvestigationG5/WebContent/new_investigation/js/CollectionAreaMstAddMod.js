function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	if(document.getElementsByName("collectionareaName")[0].value=="")
	{
		alert("Enter Collection Area Name");
		document.getElementsByName("collectionareaName")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("locationCode")[0].value=="-1")
	{
		alert("Select LOCATION ");
		document.getElementsByName("locationCode")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("collectionareaType")[0].value=="")
	{
		alert("Select Type");
		document.getElementsByName("collectionareaType")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("wardCode")[0].value=="-1" && document.getElementsByName("collectionareaType")[1].checked==true)
	{
		alert("Select Ward ");
		document.getElementsByName("wardCode")[0].focus();
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


