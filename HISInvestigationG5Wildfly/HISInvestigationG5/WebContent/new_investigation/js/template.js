

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
   
	if(document.getElementsByName("templateName")[0].value=="")
	{
		alert("Enter template Name   ");
		document.getElementsByName("templateName")[0].focus();
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
   document.getElementsByName('templateName')[0].value="";
   document.getElementsByName('templateType')[0].checked="true";
}

function modifyClear()
{
	submitPage("CLEAR");	
}
function validateOnSave()
{
   valid=false;
    if( isEmpty(document.forms[0].bookmarkName,"templateName")
    &&isEmpty(document.forms[0].bookmarType,"templateType"))
     {
         valid=true ;
     }

  return valid;
}
