function printPage() 
{
	window.print();
}

function submitReport()
{
	if(validateReportOptions())
		submitForm("PRINTREPORT");
}

function clearCombo(cbo)
{
	if(cbo)
	{
		cbo.innerHTML="";
		var op=document.createElement("option");
		op.value="0";
		op.innerHTML="All";
		cbo.appendChild(op);
	}
}

function validateFromToDates()
{
	// Validate dates
	var dtCurrent = convertStrToDate(document.getElementsByName("sysDate")[0].value,'dd-Mon-yyyy');
	var dtFrom = convertStrToDate(document.getElementsByName("fromDate")[0].value,'dd-Mon-yyyy');
	var dtTo = convertStrToDate(document.getElementsByName("toDate")[0].value,'dd-Mon-yyyy');
	if(dtFrom>dtCurrent)
	{
		alert("From Date should less than or equals to Current Date");
		document.getElementsByName("fromDate")[0].focus();
		return false;
	}
	if(dtTo>dtCurrent)
	{
		alert("To Date should less than or equals to Current Date");
		document.getElementsByName("toDate")[0].focus();
		return false;
	}
	if(dtTo<dtFrom)
	{
		alert("To Date should greater than or equals to From Date");
		document.getElementsByName("toDate")[0].focus();
		return false;
	}
	return true;
} 


