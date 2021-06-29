window.history.forward()
function submitFormOnValidate(mode){
	if(validateInitials()){
		document.getElementsByName("hmode")[0].value=mode
		document.forms[0].submit();
	}	
}
function validateIt(){
//alert("Validate");
	if(
	//isSelected(document.getElementsByName('departmentCode')[0],"Department") &&
	//isSelected(document.getElementsByName('departmentUnitCode')[0],"Unit") &&
	validateInitials()
	)
		return true;
	else
		return false;
}

function validateInitials(){
	
	var initials=document.getElementsByName("daywiseConsultantInitials")
	var valid= false;
	for(var i=0;i<initials.length;i++){
		if(!isEmpty(initials[i],"Consultant Initial for " + document.getElementsByName("day")[i].value))
			return false;
		else
			valid=true	
	}
	return valid

}

function clearForm(){
	var initials=document.getElementsByName("daywiseConsultantInitials")
	for(var i=0;i<initials.length;i++){
		initials[i].value="";
	}
}


function validateAlphaNumericWithSpecialCharacterOnly(e,obj)
{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	
	else if((getCursorIdex(obj)>0) && (key==32))
		return true
	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}	
	

