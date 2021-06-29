
/**************** Common Utility Script Functions **********************************/

//*************** Submitting Form *******************
function submitForm(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function submitFormOnValidate(flag,mode)
{
	if(flag)
	{
		document.getElementsByName("hmode")[0].value=mode;
		document.forms[0].submit();
	}
}



//**************** Validating Form *******************
	// Empty Checking
function isEmpty(obj,name)
{
	if(obj!=null && obj!='undefined')
	{
		if(obj.value=="" || obj.value=="-1")
		{
			alert("Please Enter the "+ name);
			obj.focus();
			return false;
		}
		return true;
	}
	return false;
}

	// Validating Date
function compareDateCall(d1,d2,mode,l1,l2) 
{
	var valid=true;
	if(isEmpty(d1,l1) && isEmpty(d2,l2))
	{
 		if(compareDate(d1,d2, mode))
			valid = true;
		else
		{
			alert(l1+" can't be greater than "+l2);
			valid = false;
		}
	}
	else
		valid=false;
	return valid;
}

function compareDate(frDate, toDate, mode) // Is frDate less than toDate
{
	var frValue, toValue, frYear, frMon, frDay,sts = 0;
	if (frDate == null || frDate.value == "")
	{
		frValue = new Date;
		frYear = frValue.getYear();
		frMon = frValue.getMonth();
		frDay = frValue.getDate();
	}
	else
	{
		if (isDate(frDate,mode) == true)
		{
			frYear = intYear;
			frMon = intMon;
			frDay = intDay;
			if (isDate(toDate,mode) == true)
			{
				if (frYear > intYear)	sts = 1;
				else if (frYear == intYear)
				{
					if (frMon > intMon)	sts = 1;
					else if (frMon == intMon && frDay > intDay)	sts = 1;							
				}
			}
			else
			{
				toDate.focus();
				return false;
			}
		}
		else
		{
			frDate.focus();
			return false;
		}
	}
	if (sts == 1)
	{
		if(!(frDate.value == "" || frDate == null) )	frDate.focus();
		return false;
	}
	return true;
}
/************ End Common Utility Script Functions **********************************/	 