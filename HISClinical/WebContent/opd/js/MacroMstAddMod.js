function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	var valid=true;
	if(document.getElementsByName('macroHeader')[0].value=="")
	{
		alert("Enter Macro Header...");
		document.getElementsByName('macroHeader')[0].focus();
		valid=false;
		//alert('valid'+valid);
	}

	if(document.getElementsByName('macroDesc')[0].value=="")
	{
		alert("Enter Macro Description...");
		document.getElementsByName('macroDesc')[0].focus();
		valid=false;
		//alert('valid'+valid);
	}
	
	
	if(document.forms[0].isActive && document.forms[0].isActive.value=="-1")
	{
		alert("Select IsActive Status ... ");
		document.forms[0].isActive.focus();
		valid=false;                          
	}
	//alert('valid again'+valid);
    return valid;
 } 	
	
function finalSubmit(mode)
{
	
	if ((validateFinalSubmit()) && (checkLength()))
	 {//alert('inside');
		
	submitPage(mode);
	}
	
}


function clearForm()
 {

  
   document.getElementsByName('macroHeader')[0].value="";
   document.getElementsByName('macroDesc')[0].value="";
   if(document.forms[0].isActive)
   {
   document.forms[0].isActive.value=="-1"
   }
  
  
 }

function checkLength()
{

if(document.getElementsByName('macroDesc')[0].value.length>document.getElementsByName('length')[0].value)
{
	alert("Enter Description not more than "+document.getElementsByName('length')[0].value+" characters");
	return false;
}
	else
	{
	return true;
	}

}  
  

 
 