//////////////alphabets without initial space//////////////
function validateAlphabetsOnly(e,obj)
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
	//alert(keychar)
	//alert("indexof="+('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ').indexOf(keychar))
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27))
	   return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true;
	// alphas and space
	
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}

function validateNumericOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	var pattern=/\./;
	if( charCode==0 ||
		(!pattern.test(obj.value) && charCode==46) ||
		(obj.value.length==0 && charCode==45) || 
		(charCode>=48 && charCode<= 57) )
		return true;
	else
		return false;
}

function validateAlphaNumericWithSpecialCharacterOnly(e)
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
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

function validateAlphaNumericWithSpecialCharactersOnly(e)
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
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-():").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

///////////////////////getting index of char on keypress////////////////////////////////////////////////

function getCursorIdex(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate();
		r.moveEnd('character', o.value.length);
		if (r.text == '') return o.value.length;
		return o.value.lastIndexOf(r.text);
	} else return o.selectionStart;

}



///////////////////////////////////Validate cr no with size as argument


function validateCRNoCHECK(size)
{
//alert("inside");
var valid=false;
if(validateMinLength(document.getElementsByName('patCrNo')[0],size) &&
validateCheckSumBySize(size))
{
valid=true;
}
else
{	
alert("InValid CR No");
if(document.getElementsByName("patCrNo")[0]){
document.getElementsByName("patCrNo")[0].focus();
}	
valid=false;
}
return valid;
}


function validateCRNoCHECK_forOdisha(size, CrNoObj)
{
	var valid=false;
	if(validateMinLength(CrNoObj,size) && checkSum15_odisha(CrNoObj, size)){
		valid=true;
	}else{	
		alert("InValid CR No");
		valid=false;
	}
	return valid;
}

function submitFormOnValidate(flag,mode)
{
	if(flag)
	{
		submitForm(mode);
	}
	else{
		return false;
	}
	
}

/////////////////////////////////////////////////////function for validating minimum length of a field///////////////////////////////////////////////////////////////////////////////////////////////////////////////


function validateMinLength(elem,minlen) {
	 var isValid = true;
     if(elem)
		value=elem.value;
     else
		value="";
				     
       if ((value.length<minlen))
				               {
				                isValid = false;
				              }
   return isValid;
 } 

///////////////////////////////////////////

function validateCheckSumBySize(size)
{
var valid=true
//alert("validateCheckSumBySize")
if(size==12)
{
valid=checkSumValidation()
}
if(size==13)
{
valid=checkSum();
}
if(size==15)
{
valid=checkSum15();
}
return valid
}


/////////////////////For 13 digit cr no
function checkSum(){
	
		var isValid = true;
		crNo=document.getElementsByName("patCrNo")[0].value;
		
modulo_1=crNo.substring(0,1)*7 + crNo.substring(1,2)*6 + 
		crNo.substring(2,3)*5 + crNo.substring(3,4)*4 +  
		crNo.substring(4,5)*3 + crNo.substring(5,6)*2 +  
		crNo.substring(6,7)*7 + crNo.substring(7,8)*6 +
		crNo.substring(8,9)*5 + crNo.substring(9,10)*4 +  
		crNo.substring(10,11)*3 + crNo.substring(11,12)*2 ;

modulo=modulo_1 % 11;
	checksum = (modulo==0)?1:(11-modulo)%10;
if(checksum==crNo.charAt(crNo.length-1)){
isValid = true;
}
	else
{
//alert("InValid CR No");
isValid = false;
}
return isValid;
}


/////////////////////For 15 digit cr no Added by Singaravelan on 10-Apr-2015
function checkSum15(){
		var isValid = true;
		crNo=document.getElementsByName("patCrNo")[0].value;
		
modulo_1=crNo.substring(0,1)*3 + crNo.substring(1,2)*2 + 
		 crNo.substring(2,3)*7 + crNo.substring(3,4)*6 +  
		 crNo.substring(4,5)*5 + crNo.substring(5,6)*4 +  
		 crNo.substring(6,7)*3 + crNo.substring(7,8)*2 +
		 crNo.substring(8,9)*7 + crNo.substring(9,10)*6 +  
		 crNo.substring(10,11)*5 + crNo.substring(11,12)*4 +
		 crNo.substring(12,13)*3 + crNo.substring(13,14)*2 ;

modulo=modulo_1 % 11;
	checksum = (modulo==0)?1:(11-modulo)%10;
if(checksum==crNo.charAt(crNo.length-1)){
isValid = true;
}
	else
{
//alert("InValid CR No");
isValid = false;
}
return isValid;
}



function checkTime(eObj,obj)
{
	var key;
	if (window.event)
	   key = window.event.keyCode;
	else if (eObj)
	   key = eObj.which;
	else
	   return true;

	//alert(key)
	if(key==32)
		return false;

	if(key>=48 && key<=57)
	{
		//alert("obj.value.length="+obj.value.length)
		if(getCursorIdex(obj)==0 && (key>=48 && key<=50) ){
			//eObj.keyCode = 0;
			return true;
		}
		else if(getCursorIdex(obj)==0 && !(key>=48 && key<=50) ){
			return false;
		}

		if(getCursorIdex(obj)==1 && parseInt(obj.value)>1 ){
			if( (key>=48 && key<=51) ){
			//eObj.keyCode = 0;
				return true;
			}
			else{
			return false;
			}

		}else if(getCursorIdex(obj)==1 && parseInt(obj.value)<=1) {
			if(key>=48 && key<=57){
			   return true;
			}
			else{
				return false;
			}
		}	

		if(getCursorIdex(obj)==2 && key!=58 && obj.value.length==2){
			if( !(key>=48 && key<=53)){
				//eObj.keyCode = 0;
				return false;
			}
			else{
				obj.value += ":";	
				return true;
			}
		}		
		if(getCursorIdex(obj)==2 && key!=58 && obj.value.length >2){
			//alert("Enter :")
			obj.value =obj.value.substring(0,2)+ ":" +obj.value.substring(2);
			return true;
		}
	
		if(getCursorIdex(obj)==3){
			if( (key>=48 && key<=53) ){
			//eObj.keyCode = 0;			
				return true;
		    }
			else{			
				return false
			}
		}
	}
	else
	{
		if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32) || (key==95) || (getCursorIdex(obj)==2 && key==58))
	   return true;
	else
		return false;
		//eObj.keyCode = 0;
	}
}

/////////////////////////Check MaxLength and validate for alphabets,alphanumeric and numeric in case of fields like TextArea/////////////////////////////////////////////////////////

function CheckMaxLength(e,elem,maxLen,constraint){
	//constraint  0- alphabets, 1- alphanumeric, 2- numeric, 3- any character 
	//
	//alert("inside check max length")
	key = e.keyCode;
    //alert(key);
    if (window.event)
   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
   return true;
	var valid=true;
	if(key==8 || key==46) //backspace || del
		return true;
	if(key==13){	//return not allowed
	valid=false;
	return valid;
	}
	if(constraint==0){ 				// Alphabets only 
		if(!validateAlphabetsOnly(e,elem))
			return false;
	}
	else if(constraint==1){					// Alphabets with numbers
		if(!validateAlphaNumericOnly(e,elem))
			return false;
	}
	else if(constraint==2){				// numbers only
		if(!validateNumeric(e))
			return false;
	}
	else if(constraint==3){				// any characters without initial space
		//alert(key)
		 if((getCursorIdex(elem)==0) && (key==32)){
				return false;
			}
	}
	val = elem.value; 
	if(val.length+1>maxLen){

	valid=false;
	
	}
	else{
	
	valid= true;

	}
	
	
	return valid;		
}

function setPrevValue(elem, evt){
	prevValue = elem.value;
}

function callMenu(url)
{
	var targetURL = url;// + "?varSSOTicketGrantingTicket=" + document.getElementsByName("varSSOTicketGrantingTicket")[0].value;
		
	var elemFrame = parent.document.getElementById("frmMain");
	
	if(elemFrame!=null){
		elemFrame.src=targetURL;
		elemFrame.refresh();
	}
	else{
		if(typeof $('#tabframe')!='undefined'){
			var tab = window.parent.$('#tabframe').tabs('getSelected');
			var index = window.parent.$('#tabframe').tabs('getTabIndex',tab);
			window.parent.$('#tabframe').tabs('select',index-1);	
			window.parent.$('#tabframe').tabs('close',index);	

		}
	}
}

$.extend($.fn.validatebox.defaults.rules, {
	 dtNotGtGvnNoDay: {
		 
		 validator: function(value, param){
			 
			 var d1 = $.datepicker.parseDate("dd-M-yy", value);
			 var d2 = $.datepicker.parseDate("dd-M-yy", param[0]); 										
			 return (Math.abs(Math.floor((d2.getTime() - d1.getTime()) / 86400000)) < param[1]);

		 },
	 message: '{2}.'
	}
});


function closeDeskTab()
{
	
	if(typeof $('#tt')!='undefined'){
		var tab = window.parent.$('#tt').tabs('getSelected');
		var index = window.parent.$('#tt').tabs('getTabIndex',tab);
		window.parent.$('#tt').tabs('select',index-1);	
		window.parent.$('#tt').tabs('close',index);	

	}
	
}



/////////////////////For 15 digit cr no Added by Singaravelan on 10-Apr-2015
function checkSum15_odisha(crObj, crSize){
		var isValid = true;
		crNo=crObj.value;
		
modulo_1=crNo.substring(0,1)*3 + crNo.substring(1,2)*2 + 
		 crNo.substring(2,3)*7 + crNo.substring(3,4)*6 +  
		 crNo.substring(4,5)*5 + crNo.substring(5,6)*4 +  
		 crNo.substring(6,7)*3 + crNo.substring(7,8)*2 +
		 crNo.substring(8,9)*7 + crNo.substring(9,10)*6 +  
		 crNo.substring(10,11)*5 + crNo.substring(11,12)*4 +
		 crNo.substring(12,13)*3 + crNo.substring(13,14)*2 ;

modulo=modulo_1 % 11;
	checksum = (modulo==0)?1:(11-modulo)%10;
if(checksum==crNo.charAt(crNo.length-1) && crSize==15){
isValid = true;
}
	else
{
//alert("InValid CR No");
isValid = false;
}
return isValid;
}