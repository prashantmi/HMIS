function validate()
{
	//alert('loginLogin');
	document.getElementById("divElementErrorsId").innerHTML = "";
	//var specialChars = "#,+,~,\`,=,\,,.,@,!,~,*,^,\`,&,$,(,),[,],{,},:,;,>,<,%,?,<,>,\",\'";
	if (document.getElementsByName("varUserName")[0].value == "" || document.getElementsByName("varPassword")[0].value == "")
	{
		document.getElementById("divElementErrorsId").innerHTML = "User Name / Password is empty!";
		return false;
	}
	if (IS_CAPTCHA_REQ == "ON" && document.getElementsByName("captchaResponse")[0].value == "")
	{
		document.getElementById("divElementErrorsId").innerHTML = "Captcha is empty!";
		return false;
	}
	if (!validateAlphaNumWithUnderscoreValue(document.getElementsByName("varUserName")[0].value))
		//	|| (!isValidAlphaNumericInput(document.getElementById("passwd").value, ""))) //specialChars ) ) )
	{
		document.getElementById("divElementErrorsId").innerHTML = "User Name should be Alphabnumeric with Underscore only...";
		//document.getElementById("divElementErrorsId").innerHTML = "User Name / Password is not an Alpha Numeric...";
		return false;
	}
	
	if(!securePassword())
	{
		document.getElementById("divElementErrorsId").innerHTML = "Faced Some Unknown Problem. Please try Again!";
		document.getElementsByName("varUserName")[0].value = "";
		document.getElementsByName("varPassword")[0].value = "";
		return false;
	}
	// Setting window.name property
	
	window.name = sessionToken;
	//alert(window.name);
	return true;
}

function securePassword()
{
	var hashValue = "";
	var objPassHash = new jsSHA(document.getElementsByName("varPassword")[0].value+document.getElementsByName("varUserName")[0].value, "ASCII");
	//---var objPassHash = new jsSHA(document.getElementsByName("varUserName")[0].value+document.getElementsByName("varUserName")[0].value, "ASCII");
	try 
	{
		hashValue = objPassHash.getHash("SHA-1", "HEX");
	} 
	catch(e)
	{
		return false;
	}

	objPassHash = new jsSHA(hashValue + sessionToken, "ASCII");
	try
	{
		hashValue = objPassHash.getHash("SHA-1", "HEX");
	}
	catch(e)
	{
		return false;
	}

	document.getElementsByName("varPassword")[0].value = hashValue;
	return true;
}

function submitFormOnValidate(flg, actionURL)
{
	if(flg)
	{
		submitForm(actionURL);
	}
}

function submitForm(actionURL)
{
	//document.forms[0].action = actionURL + ".action";
	//changed by garima for extension change
	document.forms[0].action = actionURL;
	document.forms[0].submit();
}



/**
 * Purpose : To ensure to enter a Alphanumeric Value with Underscore Only
 * Calling On Event : onkeypress
 * Parameters : 	1.	this	&	2.	event
 * Return Type : boolean
 
 * Ascii Code allowed A-65, Z-90, a-97, z-122, Underscore - 95
*/
function validateAlphaNumWithUnderscoreOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==95 || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) ||
		(charCode>=48 && charCode<=57) )
		return true;
	else
		return false;
}


/**
 * Purpose : To validate whether a given String is Alphanumeric having Underscore Only
 * Calling On Event : onchange, user-defined way
 * Parameters : 	1.	val/string to validate
 * Return Type : boolean
*/
function validateAlphaNumWithUnderscoreValue(val)
{
	var pattern=/^[a-zA-Z0-9_]*$/;
	return pattern.test(val);
}

