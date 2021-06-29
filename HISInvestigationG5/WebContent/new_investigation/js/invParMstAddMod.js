function submitPage(mode)
{

	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){

	// These All Fields are Mandatory

	if(document.forms[0].parameterName &&document.getElementById("parameterName").value=="")
	{
		alert("Enter Parameter Name");
		document.getElementById("parameterName").focus();
		return false;
	}
	
	if(document.getElementsByName("parameterName")[0]!=null)
		{
	document.getElementsByName("parameterName")[0].value=document.getElementById("parameterName").value;
		}
	//alert(document.getElementById("parameterName").value);

	return true;
} 	

function finalSubmit(mode)
{
	

	if (!validateFinalSubmit()) 
		
		return;
	
	submitPage(mode);
	

}


