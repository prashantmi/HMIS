//************  Popup ******************************************************************************

/**
 * Purpose : To ensure to enter a Alphanumeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, comma(,)-44 , .-46 
*/
function validateAlphaNumericOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==44 || 
		charCode==32 || 
		charCode==46 || 
		(charCode>=48 && charCode<=57) || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}

/**
 * Purpose : To ensure to enter a Alphanumeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122 
*/
function validateAlphaNumOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		(charCode>=48 && charCode<=57) || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}

/**
 * Purpose : To ensure to enter a Positive Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 
*/
function validatePositiveIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		( charCode>=48 && charCode<= 57 ) )
		return true;
	else
		return false;
}







	// On Entry Apply   	Enter:13
function onEnterApply(obj,e,apply,param)
{
	if(e.keyCode==13)
	{
		//alert(apply+"("+param+")");
		eval(apply+"("+param+")");
	}
}

	// Validate ICD Code
function validateICDCode(_val)
{
	var main=null, sub=null;
	var rex = new RegExp("/./","");
	if(!rex.test(_val))
	{
		main = new String(_val);
	}
	else
	{
		var arr = _val.split(".");
		main = arr[0];
		sub = arr[1];
	}
	
	if(main!=null)
	{
		if(main.length!=3)
			return false;
		 
		if(!((main.charCodeAt(0)>=65 && main.charCodeAt(0)<=90) || (main.charCodeAt(0)>=97 && main.charCodeAt(0)<=122)))
			return false;
		if(!(main.charCodeAt(1)>=48 && main.charCodeAt(1)<=57))
			return false;
		if(!(main.charCodeAt(2)>=48 && main.charCodeAt(2)<=57))
			return false;
	}

	if(sub!=null)
	{
		if(sub.length!=1)
			return false;
		if(!(sub.charCodeAt(0)>=48 && sub.charCodeAt(0)<=57))
			return false;
	}
	return true;
}

//************  End Popup *************************************************************************
