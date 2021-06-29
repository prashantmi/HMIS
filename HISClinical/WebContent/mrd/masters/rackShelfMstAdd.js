function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	var valid=false;
	
	if(isEmpty(document.getElementsByName('shelfLabel')[0],"Shelf Name")
	 && isSelected(document.getElementsByName('shelfStatus')[0],"Shelf Status")
	 && isEmpty(document.getElementsByName('shelfCapacity')[0],"Capacity")
	 && validateIsActive(document.getElementsByName('isValid')[0])
	 )
	{
		valid=true;
	}
	
	else 
	{
		valid=false;
	}
	
	return valid;
 } 	
	
function validateIsActive(isActive)
{
	if(isActive){
		if (isSelected(isActive,"Is Active Status")){
			return true;                          
		}
		else{
			return false
		}	
	}
	else{
		return true;
	}
}
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) 
		return;
	submitPage(mode);
	
}
function clearForm(){

   document.getElementsByName('shelfLabel')[0].value="";
   document.getElementsByName('shelfStatus')[0].value="-1";
   document.getElementsByName('shelfCapacity')[0].value="";
   if(document.getElementsByName('isValid')[0])
   {
   		document.forms[0].isValid.value="-1"
   }
  
}
 