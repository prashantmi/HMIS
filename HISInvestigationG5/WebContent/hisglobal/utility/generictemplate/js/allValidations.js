//------------ /hisglobal/generictemplate/js/validationFunctions.js
// ******************************************************************************
// *** Validation Functions

ValidationFunctions = function(){};


/**
 * Purpose : Just Calling a Functions that does Nothing
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
*/
ValidationFunctions.evtValidateNone = function(evnt)
{
	return true;
};


		/***** Numeric *****/
/**
 * Purpose : To ensure to enter a Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
ValidationFunctions.evtValidateNumericOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==46) || 
			(charCode==45) || 
			(charCode>=48 && charCode<= 57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
/*function validateNumericOnly(obj,e)
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
}*/

/**
 * Purpose : To validate whether a given String a Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
ValidationFunctions.validateNumericValue = function (val)
{
	var pattern=/^-?\d*\.?\d*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
/*function validateNumericValue(val)
{
	var pattern=/^-?\d*\.?\d*$/;
	return pattern.test(val);
}*/



		/***** Positive Numeric *****/
/**
 * Purpose : To ensure to enter a Positive Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46
*/
ValidationFunctions.evtValidatePositiveNumericOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==46) || 
			(charCode>=48 && charCode<= 57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Positive Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46
*/
/*function validatePositiveNumericOnly(obj,e)
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
		(charCode>=48 && charCode<= 57) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Positive Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46
*/
ValidationFunctions.validatePositiveNumericValue = function (val)
{
	var pattern=/^\d*\.?\d*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Positive Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
/*function validatePositiveNumericValue(val)
{
	var pattern=/^\d*\.?\d*$/;
	return pattern.test(val);
}*/



		/***** Integer *****/
/**
 * Purpose : To ensure to enter a Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45 
*/
ValidationFunctions.evtValidateIntegerOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==45) || 
			(charCode >= 48 && charCode <= 57) )
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45 
*/
/*function validateIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		(obj.value.length==0 && charCode==45) || 
		(charCode >= 48 && charCode <= 57) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45
*/
ValidationFunctions.validateIntegerValue = function(val)
{
	var pattern=/^-?[0-9]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45
*/
/*function validateIntegerValue(val)
{
	var pattern=/^-?[0-9]*$/;
	return pattern.test(val);
}*/



		/***** Positive Integer *****/
/**
 * Purpose : To ensure to enter a Positive Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 
*/
ValidationFunctions.evtValidatePositiveIntegerOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			( charCode>=48 && charCode<=57 ) )
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Positive Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 
*/
/*function validatePositiveIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		( charCode>=48 && charCode<=57 ) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Positive Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57
*/
ValidationFunctions.validatePositiveIntegerValue = function(val)
{
	var pattern=/^[0-9]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Positive Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57
*/
/*function validatePositiveIntegerValue(val)
{
	var pattern=/^[0-9]*$/;
	return pattern.test(val);
}*/


		/***** Alphabetic *****/
/**
 * Purpose : To ensure to enter a Alphabetic Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
ValidationFunctions.evtValidateAlphaOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==32) || 
			(charCode==46) || 
			(charCode>=65 && charCode<=90) || 
			(charCode>=97 && charCode<=122) )
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Alphabetic Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
/*function validateAlphaOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==32 || 
		charCode==46 || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}*/

/**
 * Purpose : To validate whether a given String a Alphabetic Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
ValidationFunctions.validateAlphaValue = function(val)
{
	var pattern=/^[a-zA-Z .]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Alphabetic Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
/*function validateAlphaValue(val)
{
	var pattern=/^[a-zA-Z .]*$/;
	return pattern.test(val);
}*/



		/***** Alpha-Numeric *****/
/**
 * Purpose : To ensure to enter a Alphanumeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, comma(,)-44 , .-46 
*/
ValidationFunctions.evtValidateAlphaNumOnly = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			(charCode==44) || 
			(charCode==32) || 
			(charCode==46) || 
			(charCode>=48 && charCode<=57) || 
			(charCode>=65 && charCode<=90) || 
			(charCode>=97 && charCode<=122) )
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure to enter a Alphanumeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, comma(,)-44 , .-46 
*/
/*function validateAlphaNumOnly(obj,e)
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
}*/

/**
 * Purpose : To validate whether a given String a Alphanumeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44
*/
ValidationFunctions.validateAlphaNumValue = function(val)
{
	var pattern=/^[a-zA-Z, .0-9]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String a Alphanumeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44
*/
/*function validateAlphaNumValue(val)
{
	var pattern=/^[a-zA-Z, .0-9]*$/;
	return pattern.test(val);
}*/



		/***** Not Some Special Characters *****/
/**
 * Purpose : To ensure that entered Value don't have any Special Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
ValidationFunctions.evtNotSpecChar = function(evnt) 
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==126 || charCode==33 || 
			charCode==64 || charCode==35 || 
			//charCode==38 || charCode==37 || 
			charCode==94 || charCode==36)
			flag=false;
		else
			flag=true;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure that entered Value don't have any Special Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
/*function notSpecChar(obj,e) 
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==126 || charCode==33 || 
		charCode==64 || charCode==35 || 
		charCode==36 || charCode==37 || 
		charCode==94 || charCode== 38 )
		return false;
	else
		return true;
}*/

/**
 * Purpose : To validate whether a given String don't have any Special Character
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
ValidationFunctions.notSpecCharValue = function(val)
{
	var pattern=/^[^~!@#$%^&]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String don't have any Special Character
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
/*function notSpecCharValue(val)
{
	var pattern=/^[^~!@#$%^&]*$/;
	return pattern.test(val);
}*/



		/***** Not Some Special Characters for Regular Expression *****/
/**
 * Purpose : To ensure that entered Value don't have any Special Character for Regular Expression 
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * // Restrict to enter ~!@#$%^& in all fileds  allow ^ and  $ in Regular Expression only
 * ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38 
*/
ValidationFunctions.evtNotSpecCharRegEx = function(evnt) 
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==126 || charCode==33 || 
			charCode==64 || charCode==35 || 
			charCode==37 || charCode== 38)
			flag=false;
		else
			flag=true;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure that entered Value don't have any Special Character for Regular Expression
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Restrict to enter ~!@#%&
 * ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38 
*/
/*function notSpecCharRegEx(obj,e)
{
	try
	{
		//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
		var charCode;
		if(typeof e.charCode != 'undefined')	// Other
			charCode=e.charCode;
		else									// IE
			charCode=e.keyCode;
		//alert(charCode);
		if( charCode==126 || charCode==33 || 
			charCode==64 || charCode==35 || 
			charCode==37 || charCode== 38 )
			return false;
		else
			return true;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
}*/

/**
 * Purpose : To validate whether a given String don't have any Special Character
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#%&
 * ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38 
*/
ValidationFunctions.notSpecCharRegEx = function(val)
{
	var pattern=/^[^~!@#%&]*$/;
	return pattern.test(val);
};

/**
 * Purpose : To validate whether a given String don't have any Special Character for Regular Expression
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#%&
 * ~ - 126, ! - 33, @ - 64, # - 35, % - 37, & - 38 
*/
/*function notSpecCharRegEx(val)
{
	var pattern=/^[^~!@#%&]*$/;
	return pattern.test(val);
}*/



		/***** Allow Some Characters *****/
/**
 * Purpose : To ensure that entered Value have given Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	event 
 * Return Type : boolean
*/
ValidationFunctions.evtValidateWithCharacters = function(evnt) 
{
	try
	{
		var chars = "";//window.Pragyas_ALLOW_CHARS;//---------
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		var char = String.fromCharCode(charCode);
		if ( chars.indexOf(char) > -1)
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

/**
 * Purpose : To ensure that entered Value have given Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	2.	event	3. string of characters allowed 
 * Return Type : boolean
*/
/*function validateWithCharacters(obj,e,chars) 
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	var char = String.fromCharCode(charCode);
	if ( chars.indexOf(char) > -1)
		return true;
	else
		return false;
}*/


		/***** Formula *****/
/**
 * Purpose : To ensure that entered Value is suitable for a formula
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code 0 - 48 To 9 - 57, Space- 32, . 46 , + 43 , - 45 , ( 40 , ) 41 , * 42 , / 47 , % 37
 * U-85, W-87, X-88, Y-89, Z-90
 *  
*/
ValidationFunctions.evtValidateFormulaOnly = function(evnt)
{ 
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			charCode==32 || charCode==37 || 
			charCode==40 || charCode==41 || 
			charCode==42 || charCode==43 || 
			charCode==45 ||	charCode==46 || 
			charCode==47 || 
			charCode==85 || charCode==87 || charCode==88 || charCode==89 || charCode==90 || 
			(charCode>=48 && charCode<=57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

		/***** Corresponding Formula *****/
/**
 * Purpose : To ensure that entered Value is suitable for a formula
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code 0 - 48 To 9 - 57, Space- 32, . 46 , + 43 , - 45 , ( 40 , ) 41 , * 42 , / 47 , % 37
 * V-86
 *  
*/
ValidationFunctions.evtValidateCorrespondingFormulaOnly = function(evnt)
{ 
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			charCode==32 || charCode==37 || 
			charCode==40 || charCode==41 || 
			charCode==42 || charCode==43 || 
			charCode==45 ||	charCode==46 || 
			charCode==47 || charCode==86 || 
			(charCode>=48 && charCode<=57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};


		/***** Date Format *****/
/**
 * Purpose : To ensure that entered Value is suitable for a Date in dd-mm-yyyy format
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code 0 - 48 To 9 - 57 , for - 45 
*/
ValidationFunctions.evtValidateDateFormat = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if( charCode==0 || 
			//(charCode==45) || 
			(charCode>=48 && charCode<= 57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

ValidationFunctions.validateDateFormatValue = function (val)
{// dd-mm-yyyy
	//alert(val);
	var pattern=/^\d{2}-\d{2}-\d{4}$/;
	return pattern.test(val);
};


		/***** Time Format *****/
/**
 * Purpose : To ensure that entered Value is suitable for a Time in hh:mm format
 * Calling On Event : onkeypress
 * Parameters : 	1.	event
 * Return Type : boolean
 
 * Ascii Code 0 - 48 To 9 - 57 , for : 58 
*/
ValidationFunctions.evtValidateTimeFormat = function(evnt)
{
	try
	{
		var flag = true;
		var charCode;
		if(typeof evnt.charCode != 'undefined')	// Other
			charCode=evnt.charCode;
		else									// IE
			charCode=evnt.keyCode;
		if(	charCode==0 || 
			//(charCode==58) || 
			(charCode>=48 && charCode<= 57))
			flag=true;
		else
			flag=false;
		if(!flag && typeof evnt.preventDefault != 'undefined')
		{
			evnt.preventDefault();
		}
		return flag;
	}
	catch(e)
	{
		alert("Error Message -> "+e.message);
	}
};

ValidationFunctions.validateTimeFormatValue = function (val)
{// hh:mm
	//alert(val);
	var pattern=/^\d{2}:\d{2}$/;
	return pattern.test(val);
};


	/****** Validate Regular Expression *****/
ValidationFunctions.validateRegularExpression = function(_val, _re)
{
	if(_re!="")
	{ 
		var rep=new RegExp(_re,"");
		return rep.test(_val);
	}
	return true;
};

	//****** Validate Corresponding Value Related to Function given
ValidationFunctions.validateValueForFunction = function(_value, _fun)
{
	if( /None/i.test(_fun) )
		return true;
	var valFunc =_fun.replace("Only","Value");
	return ValidationFunctions[valFunc](_value);
};


	//****** Validate Minimum Legth of the Field
ValidationFunctions.validateMinLength = function(_value, _minLen)
{
	alert(typeof _minLen);
	if(_value && _value!="" && _minLen && typeof _minLen=='integer' && _value.length>=_minLen)
		return true;
	else
		return false;
};

function comboValidation(obj, str)
{	var valid= true
	if(obj.value==-1)
	{
		alert("Please Select : "+str)
		valid=false
		obj.focus()
	}
	return valid
}
function validatePinNumber(obj)
{	

	var valid=true
	var len=obj.value.length
	var val=obj.value
		
	if (parseInt(len)>0 )
	{
		
		if((val.charAt(0))==0)
		{
		alert("Pin Number Cannot Start with Zero")
		valid=false
		obj.focus()
		}
		else
		{
			valid=true
		}
	}
	else
	{
		valid=true
	}
	
	return valid
	
}

function validateStartingWithDot(obj,name)
{

	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(0))=='.')
		{
			alert(name+" cannot start with special character dot")
			valid=false
			return valid
		}
		
	}
	
	return valid
}
function validateTwoConsecutiveDots(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		for(i=0;i<len;i++)
		{
			if((val.charAt(i))=='.')
			{
				if((val.charAt(i))==(val.charAt(i+1)))
				{
				alert(name+" cannot have two consecutive special character dot")
				valid=false
				
				return valid
				}
			}
		}
	}
	
	return valid	
}
function validateEndingWithDot(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(len-1))=='.')
		{
			alert(name+" cannot end with special character dot")
			valid=false
			return valid
		}
		
	}
	
	return valid
} 
function validateDot(obj,name)
{

	var valid=false
	
	if(validateStartingWithDot(obj,name)
	&& validateTwoConsecutiveDots(obj,name)
	&& validateEndingWithDot(obj,name))
	{
		
		valid=true
	}

	return valid
}
function validateDot(obj,name)
{

	var valid=false
	
	if(validateStartingWithDot(obj,name)
	&& validateTwoConsecutiveDots(obj,name)
	&& validateEndingWithDot(obj,name))
	{
		
		valid=true
	}

	return valid
}
function validateStartingWithSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(0))=='.' ||  (val.charAt(0))=='/' || (val.charAt(0))=='-')
		{
			alert(name+" cannot start with special character ")
			valid=false
			return valid
		}
		
	}
	
	return valid
}
function validateTwoConsecutiveSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		for(i=0;i<len;i++)
		{
			if((val.charAt(i))=='.' || (val.charAt(i))=='/' || (val.charAt(i))=='-')
			{
				if((val.charAt(i))==(val.charAt(i+1)))
				{
				alert(name+" cannot have two consecutive special character ")
				valid=false
				
				return valid
				}
			}
		}
	}
	
	return valid	
}
function validateEndingWithSpecialCharacter(obj,name)
{
	var valid=true
	var len=obj.value.length
	var val=obj.value
	
	if(parseInt(len)>0)
	{
		if((val.charAt(len-1))=='.' || (val.charAt(len-1))=='/' || (val.charAt(len-1))=='-')
		{
			alert(name+" cannot end with special character ")
			valid=false
			return valid
		}
		
	}
	
	return valid
} 
//////////////////////////validate text area with special character//////////////////
function validateSpecialCharacter(obj,name)
{
	var valid=false
	//alert("entry")
	if(validateStartingWithSpecialCharacter(obj,name)
	&& validateTwoConsecutiveSpecialCharacter(obj,name)
	&& validateEndingWithSpecialCharacter(obj,name))
	{
		
		valid=true
	}

	return valid
}
//--------------- /hisglobal/js/validationCommon.js
function validateNullObjects(field,label)
{
 var isValid = true;
   //alert("value"+field.value);
   //alert("validateNullObjects:  "+field);
    if(field==null || field=="undefined")
              { 
               	//alert(label+"is required");
                isValid = false;
              }
            
     return isValid;
        

} 
function validateTextArea(event,obj,maxLength){
//alert("In maxlength");
var valid=true;

if(CheckMaxLength(event,obj,maxLength))
valid=true;
else
valid=false;  

return valid;

}
function validateTextAreaFor(constraint, event,obj,maxLength){
//constraint='A' or 'a' X x 9 ^
var valid=true;

if(CheckMaxLength(event,obj,maxLength)&& validateAlphaNumericOnly(event))
valid=true;
else
valid=false;  

return valid;

}
//--------------- /registration/js/commonFunctions.js
	//onload
 var oldonload = window.onload;
 if (typeof window.onload != 'function') {
   window.onload = function() {
     callThisOnload();
   }
 } else {
   window.onload = function() {
     oldonload();
     callThisOnload();
   }
 } 
 //override this function in the respective tile
 function callThisOnload(){
 
 }
/////////////////////////Check MaxLength in case of fields like TextArea/////////////////////////////////////////////////////////
function CheckMaxLength(e,elem,maxLen){
		//alert("inside: CheckMaxLength");
    	key = e.keyCode;
        //alert(key);
		var valid=true;
		if(key==8 || key==46) //backspace || del
			return true;
		if(key==13){	//return not allowed
		valid=true;
		return valid;
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
		valid=true;
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
					return false
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


///////////////////////////////////////check  min length for PIN..........................

function CheckMinLengthForPIN(elem,minLen){
//alert("pin min");
if(document.getElementsByName("isAddressDelhi")[0].checked){
//alert("delhi");
	if(CheckMinLength(elem,minLen,"PIN"))
	return true;
 	else
   	return false;
}
 else
 return true;

}
///////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////check min length......................................................


function CheckMinLength(elem,minLen,name){
//alert("check min length function");
var valid=true;
val = elem.value; 

if(val.length<minLen){
        
		//alert("if less than true");
 		alert(name+" Requires minimum"+minLen+" Digits");
 		valid=false;
 		elem.focus();
 		}
		else{
		//alert("false less than");
		valid= true;

		}
		
		//alert("min valid......1111"+valid);
		return valid;		
}



/////////////////////////////////////////////////////////////////////////////////////////////................................

///////////////////////////////alphabets only////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


//****** Not Active Overloaded
function validateAlphabetsOnly(e)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}

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
		return true
	// alphas and space
	
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}


///////////////////////validate alphabets with dots//////////////////////////////


function validateAlphabetsWithDotsOnly(e,obj)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;
	   
	else if((getCursorIdex(obj)>0) && (key==32))
		return true
	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}




///////////////////////////////////validate numeric//////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


function validateNumeric(e)
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
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}//end of numericOnly

//////////////////////////////////////////////validate alpha numeric  without any special characters.................

function validateAlphaNumericOnly(e)
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
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

//////////////////////////////////////////////validate alpha numeric  without any special characters and without initial spaces.................

function validateAlphaNumericOnly(e,obj)
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
		(key==9) || (key==13) || (key==27))
	   return true;
	else if((getCursorIdex(obj)>0) && (key==32))
		return true

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

/////////////////////////////////////validate alphanumeric with dots only//////////////////

function validateAlphaNumericWithDotsOnly(e)
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
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}

/////////////////////////////////////validate alphanumeric with dots only without initial space//////////////////

function validateAlphaNumericWithDotsOnly(e,obj)
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
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;
}


/////////////////////////////////////validate alphanumeric with special character (.,/,-) only//////////////////

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

/////////////////////////////////////validate alphanumeric with special character (.,/,-) only without initial space//////////////////

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


///////////////////////////////////validate alpha numeric field on Blur..........................


function IsAlphaNumeric(elem,fieldName)
{

var pari=parseInt(elem.value);
//alert(pari);
/*
if(parseInt(elem.value))
{
alert("Can't put Numeric Value in "+fieldName);
elem.focus();
return false;
}
else
return true;*/
}

////////////////////////////////////validate currency fields.......................................

/*
function validateCurrency(e,elem)
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
	keychar = keychar.toLowerCase();

	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789.").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
  }
  */
  /////////////////////////////////////validate amount collected////////////////
  
function currencyFormat(fld, milSep, decSep, e)
{
	var sep = 0;
	var key = '';
	var milSep="";
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? e.which : e.keyCode;
	//if (whichCode == 13) return true;  // Enter
	if (whichCode == 0)	return true; //tab-index
	//alert(whichCode);
	if (whichCode == 8) return true;  // Back-Space 
	key = String.fromCharCode(whichCode);  // Get key value from key code
		if (strCheck.indexOf(key) == -1) return false;  // Not a valid key
		//len = fld.value.length;
		len=11;
		for(i = 0; i < len; i++)
		if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep)) break;
		aux = '';
	for(; i < len; i++)
	if (strCheck.indexOf(fld.value.charAt(i))!=-1) aux += fld.value.charAt(i);
	aux += key;
	len = aux.length;
	if (len == 0) fld.value = '';
	if (len == 1) fld.value = ''+ decSep + '' + aux;
	if (len == 2) fld.value = ''+ decSep + aux;
	if (len > 2) {
	aux2 = '';
	for (j = 0, i = len - 3; i >= 0; i--) {
	if (j == 3) {
	aux2 += milSep;
	j = 0;
}
aux2 += aux.charAt(i);
j++;
}
fld.value = " ";
len2 = aux2.length;
for (i = len2 - 1; i >= 0; i--)
fld.value += aux2.charAt(i);
fld.value += decSep + aux.substr(len - 2, len);
}
return false;
}
//////////////////////////////////////////////////// doHomeWork empty........///////////////////////////////////////////////////////////////////

function doHomeWork(){
}



//  ******************************************************************************
// *** Validation Functions

/**
 * Purpose : To ensure to enter a Numeric Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
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

/**
 * Purpose : To ensure to enter a Integer Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45 
*/
function validateIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		(obj.value.length==0 && charCode==45) || 
		(charCode >= 48 && charCode <= 57) )
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


/**
 * Purpose : To ensure to enter a Alphabetic Value
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
function validateAlphaOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==32 || 
		charCode==46 || 
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
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44 
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
 * Purpose : To ensure that entered Value don't have any Special Character
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
function notSpecChar(obj,e) 
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==126 || charCode==33 || 
		charCode==64 || charCode==35 || 
		charCode==36 || charCode==37 || 
		charCode==94 || charCode== 38 )
		return false;
	else
		return true;
}

/**
 * Purpose : To validate whether a given String a Numeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , for . - 46, Minus - 45
*/
function validateNumericValue(val)
{
	var pattern=/^-?\d*\.?\d*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String a Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57 , Minus - 45
*/
function validateIntegerValue(val)
{
	var pattern=/^-?[0-9]*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String a Positive Integer Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57
*/
function validatePositiveIntegerValue(val)
{
	var pattern=/^[0-9]*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String a Alphabetic Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Space- 32, . -46
*/
function validateAlphaValue(val)
{
	var pattern=/^[a-zA-Z .]*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String a Alphanumeric Value
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Ascii Code allowed 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, for , - 44
*/
function validateAlphaNumValue(val)
{
	var pattern=/^[a-zA-Z, .0-9]*$/;
	return pattern.test(val);
}

/**
 * Purpose : To validate whether a given String don't have any Special Character
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
 
 * Restrict to enter ~!@#$%^&
 * ~ - 126, ! - 33, @ - 64, # - 35, $ - 36, % - 37,^ - 94, & - 38 
*/
function notSpecCharValue(val)
{
	var pattern=/^[^~!@#$%^&]*$/;
	return pattern.test(val);
}	
///////////////////////validate alphabets with dots//////////////////////////////


function validateAlphabetsWithDotsAndForwardSlashWithHypen(e,obj)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
	   return true;
	   
	else if((getCursorIdex(obj)>0) && (key==32))
		return true
	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ./-").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}


//-----------------  /hisglobal/js/commonfunctions.js
function getCursorIdex(o) {
	if (o.createTextRange) {
		var r = document.selection.createRange().duplicate()
		r.moveEnd('character', o.value.length)
		if (r.text == '') return o.value.length
		return o.value.lastIndexOf(r.text)
	} else return o.selectionStart
}



///////////////////////////////alphabets  with brackets only////////>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



function validateAlphabetsWithBrackets(e)
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
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ()").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}


 ///////////Function for focusing on the First element of a form ////////
 ////Note : You have to call this function onload of your form and ////
 ////take care of that the element should be the first element ,  ////
 ////on which you want to focus no hidden elements should be in between it
 
 function focusFirstElementOnLoad()
 {
 document.forms[0].elements[0].focus();
 }
 
 
 
 //////////////////////////////email validation//////////////////////////////////
function validateEmail(obj){
var testresults;
if(trimSpec(obj.value)!="")
{
 var str=obj.value
 var filter=/^.+@.+\..{2,3}$/

 if (filter.test(str))
    testresults=true
 else {
    alert("Please enter valid email address!")
    testresults=false
}
}
else
{
	testresults=true;
}
 return (testresults)
}
 
 
function trimSpec(strValue)
{
	var j;
	var retStr = "";
	var len = strValue.length;
	
	for(j = 0;j < len;j++)
	{
		if(strValue.charAt(j) != " ") retStr += strValue.charAt(j);
	}	
	
	return retStr;		
}	




// *** End Validation Functions
// ******************************************************************************
