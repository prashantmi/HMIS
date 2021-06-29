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
			charCode!=86 ||	(charCode>=65 && charCode<=90) || 
			// || charCode==85 || charCode==87 || charCode==88 || charCode==89 || charCode==90 || 
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





// *** End Validation Functions
// ******************************************************************************
