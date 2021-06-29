function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
    var valid=false;
     // These All Fields are Mandatory
    if(isEmpty(document.forms[0].patStatus,"Patient Status")){
    	valid=true;
    } 
      
	/*if(document.forms[0].patStatus && document.forms[0].patStatus.value=="")
	{
		alert("Patient Status  ... ");
		document.forms[0].patStatus.focus();
		return false;
	}
	*/
	if(document.forms[0].hmode.value=='MODIFYSAVE'){
		if(comboValidation(document.forms[0].isActive,"Is Active Status")){
		   	valid= true;
		} 
    }
	return valid;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}


function clearForm()
 {
  
   document.getElementsByName('patStatus')[0].value="";
   if(document.forms[0].hmode.value=='MODIFY'){
   		document.getElementsByName('isActive')[0].value="1";
   }	
   document.forms[0].patStatus.focus();
 }
  
  
        
