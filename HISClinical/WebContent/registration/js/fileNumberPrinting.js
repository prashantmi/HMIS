window.history.forward()
window.onload=function(){
	if(document.getElementsByName("isPrinted")){
		var isPrinted=document.getElementsByName("isPrintedFlag")[0]
		//alert(isPrinted.value)
		if(isPrinted.value=="0"){
			document.getElementsByName("isPrinted")[0].checked=true
		}
		else{
			document.getElementsByName("isPrinted")[1].checked=true
		}
	
	}


}


function submitFormOnValidate(mode){
	if(validateIt()){
		document.getElementsByName("hmode")[0].value=mode
		document.forms[0].submit();
	}	
}
function validateIt(){
//alert("Validate");
	var selectedCrNo=document.getElementsByName("selectedCrNo")
	//var valid=false;
	var count=0;
	for (var i=0;i<selectedCrNo.length;i++){
		if(selectedCrNo[i].checked){
			count++;
		}
	}
	if(count==0){
		alert("Please Select at least one record");
		return false;
	}	
	else
		return true;
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
	
function setDisplayMode(obj){
	document.getElementsByName("isPrinted")[0].value=obj.value
	document.getElementsByName("hmode")[0].value="GETPATIENTLIST"
	document.forms[0].submit()

}

function selectAllRecord(obj){
	var selectedCrNo=document.getElementsByName("selectedCrNo")
	var count=0;
	for (var i=0;i<selectedCrNo.length;i++){
		if(obj.checked){
			selectedCrNo[i].checked=true;
		}
		else{
			selectedCrNo[i].checked=false;
		}	
	}

}

function submitMode(obj){
	document.getElementsByName("hmode")[0].value="SUBMITMODE"
	document.forms[0].submit();

}