function validate()
{
	document.getElementById("divElementErrorsId").innerHTML = "";
	//var specialChars = "#,+,~,\`,=,\,,.,@,!,~,*,^,\`,&,$,(,),[,],{,},:,;,>,<,%,?,<,>,\",\'";
	if (document.getElementsByName("varUserName")[0].value == "" || document.getElementsByName("varPassword")[0].value == "")
	{
		document.getElementById("divElementErrorsId").innerHTML = "Login / Password is empty!";
		return false;
	}
	/*if ((!isValidAlphaNumericInput(document.getElementById("uName").value,""))
			|| (!isValidAlphaNumericInput(document.getElementById("passwd").value, ""))) //specialChars ) ) )
	{
		document.getElementById("errors").innerHTML = "Login / Password is not an Alpha Numeric...";
		return false;
	}*/
	
	/* validation for blankspace of userid
	 else if( isBlankSpace( trim(document.getElementById("uName").value) ) )
	 {
	 document.getElementById( "errors" ).innerHTML = "User Name is not be a blank space..";
	 return false;
	 }*/

	if(!securePassword())	return false;
	return true;
}

function securePassword()
{
	var hashValue = "";
	var objPassHash = new jsSHA(document.getElementsByName("varPassword")[0].value, "ASCII");

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
