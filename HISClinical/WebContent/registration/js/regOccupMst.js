function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	if(document.forms[0].occupName && document.forms[0].occupName.value=="")
	{
		alert("Plz Fill The Field Occupation Name ... ");
		document.forms[0].occupName.focus();
		return false;
	}
	if(document.forms[0].hmode.value="MODIFYSAVE"){
		if(document.forms[0].isActive && document.forms[0].isActive.value=="-1")
		{
			alert("Is Active Status  ... ");
			document.forms[0].isActive.focus();
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




function clearForm()
 {
  
   document.getElementsByName('occupName')[0].value="";
   //alert(document.getElementsByName('hmode')[0].value)
   if(document.getElementsByName('hmode')[0].value=="MODIFY"){
   		document.getElementsByName('isActive')[0].value="1";
   }	
   document.forms[0].occupName.focus();
  
  
 }
  
  