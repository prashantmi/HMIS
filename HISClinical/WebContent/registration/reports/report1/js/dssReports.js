function validateReportConfig()
{
	if(document.getElementsByName("arrayHospitalCode")[0])
	{
		// Validate Hospital
		var elem = document.getElementsByName("arrayHospitalCode")[0];
		var bSelected = false;
		for(var i=0; i<elem.options.length; i++)
			if(elem.options[i].selected)
			{
				bSelected = true;
				break;
			}
		if(!bSelected)
		{
			alert("Select at least One Hospital!");
			elem.focus();
			return false;
		}
	}
	
	if(document.getElementsByName("arrayDepartmentCode")[0])
	{
		// Validate Department
		elem = document.getElementsByName("arrayDepartmentCode")[0];
		bSelected = false;
		for(var i=0; i<elem.options.length; i++)
			if(elem.options[i].selected)
			{
				bSelected = true;
				break;
			}
		if(!bSelected)
		{
			alert("Select at least One Department!");
			elem.focus();
			return false;
		}
	}

	if(document.getElementsByName("arrayPatCatCode")[0])
	{
		// Validate Category
		elem = document.getElementsByName("arrayPatCatCode")[0];
		bSelected = false;
		for(var i=0; i<elem.options.length; i++)
			if(elem.options[i].selected)
			{
				bSelected = true;
				break;
			}
		if(!bSelected)
		{
			alert("Select at least One Category!");
			elem.focus();
			return false;
		}
	}

	if(document.getElementsByName("arrayPatientCaste")[0])
	{
		// Validate Caste
		elem = document.getElementsByName("arrayPatientCaste")[0];
		bSelected = false;
		for(var i=0; i<elem.options.length; i++)
			if(elem.options[i].selected)
			{
				bSelected = true;
				break;
			}
		if(!bSelected)
		{
			alert("Select at least One Caste!");
			elem.focus();
			return false;
		}
	}

	if(document.getElementsByName("arrayReligion")[0])
	{
		// Validate Religion
		elem = document.getElementsByName("arrayReligion")[0];
		bSelected = false;
		for(var i=0; i<elem.options.length; i++)
			if(elem.options[i].selected)
			{
				bSelected = true;
				break;
			}
		if(!bSelected)
		{
			alert("Select at least One Religion!");
			elem.focus();
			return false;
		}
	}

	if(document.getElementsByName("arrayState")[0])
	{
		// Validate State
		elem = document.getElementsByName("arrayState")[0];
		bSelected = false;
		for(var i=0; i<elem.options.length; i++)
			if(elem.options[i].selected)
			{
				bSelected = true;
				break;
			}
		if(!bSelected)
		{
			alert("Select at least One State!");
			elem.focus();
			return false;
		}
	}

	if(document.getElementsByName("strDurationMode"))
	{
		// Validate Duration Mode Wise Dates
		var elemDurMode = document.getElementsByName("strDurationMode");
		if(elemDurMode[0].checked)
		{
			var fromYear = document.getElementsByName("strFromYear")[0].value;
			var toYear = document.getElementsByName("strToYear")[0].value;
			if(parseInt(fromYear)>parseInt(toYear))
			{
				alert("From Year should be less than To Year!");
				document.getElementsByName("strFromYear")[0].focus();
				return false;
			}
		}
		else if(elemDurMode[1].checked)
		{
			var fromYear = document.getElementsByName("strFromYear")[0].value;
			var toYear = document.getElementsByName("strToYear")[0].value;
			var fromMon = document.getElementsByName("strFromMonth")[0].value;
			var toMon = document.getElementsByName("strToMonth")[0].value;
			if(parseInt(fromYear)>parseInt(toYear))
			{
				alert("From Year should be less than To Year!");
				document.getElementsByName("strFromYear")[0].focus();
				return false;
			}
			if(parseInt(fromYear) == parseInt(toYear))
			{
				if(parseInt(fromMon)>parseInt(toMon))
				{
					alert("From Month-Year should be less than To Month-Year!");
					document.getElementsByName("strFromMonth")[0].focus();
					return false;
				}
			}
			if(parseInt(fromMon)<10)	fromMon = "0" + fromMon;
			if(parseInt(toMon)<10)	toMon = "0" + toMon;
			var dtFrom = convertStrToDate('01-' + fromMon + '-' + fromYear, 'dd-MM-yyyy');
			var dtTo = convertStrToDate('01-' + toMon + '-' + toYear, 'dd-MM-yyyy');
			var monthDiff = dateDifference(dtTo, dtFrom, 'M');
			if(monthDiff>=12)	// Max Limit of Months Diffence in Month-Wise Mode
			{
				alert("Differance between Months can't be greater than 12 months!");
				document.getElementsByName("strFromDate")[0].focus();
				return false;
			}
		}
		else if(elemDurMode[2].checked)
		{
			var fromDate = document.getElementsByName("strFromDate")[0].value;
			var toDate = document.getElementsByName("strToDate")[0].value;
			var dtFrom = convertStrToDate(fromDate, 'dd-Mon-yyyy');
			var dtTo = convertStrToDate(toDate, 'dd-Mon-yyyy');
			if(dtFrom>dtTo)
			{
				alert("From Date should be less than To Date!");
				document.getElementsByName("strFromDate")[0].focus();
				return false;
			}
			var dateDiff = dateDifference(dtTo, dtFrom, 'D');
			if(dateDiff>31)	// Max Limit of Days Diffence in Date-Wise Mode
			{
				alert("Differance between dates can't be greater than 31!");
				document.getElementsByName("strFromDate")[0].focus();
				return false;
			}
		}
	}

	return true;
}

function submitReport(flag)
{
	if(flag)
	{
		document.getElementsByName("hmode")[0].value=document.getElementsByName("strMode")[0].value
		document.forms[0].submit();
	}
}

function onChangeDurMode()
{
	var radios = document.getElementsByName("strDurationMode");
	if(radios[0].checked)		// Year Wise
	{
		document.getElementById("divFromYear").style.display = "block";
		document.getElementById("divFromYearControl").style.display = "block";
		document.getElementById("divToYear").style.display = "block";
		document.getElementById("divToYearControl").style.display = "block";

		document.getElementById("divFromMonth").style.display = "none";
		document.getElementById("divFromMonthControl").style.display = "none";
		document.getElementById("divToMonth").style.display = "none";
		document.getElementById("divToMonthControl").style.display = "none";

		document.getElementById("divFromDate").style.display = "none";
		document.getElementById("divFromDateControl").style.display = "none";
		document.getElementById("divToDate").style.display = "none";
		document.getElementById("divToDateControl").style.display = "none";
	}
	else if(radios[1].checked)	// Month Wise
	{
		document.getElementById("divFromYear").style.display = "none";
		document.getElementById("divFromYearControl").style.display = "block";
		document.getElementById("divToYear").style.display = "none";
		document.getElementById("divToYearControl").style.display = "block";

		document.getElementById("divFromMonth").style.display = "block";
		document.getElementById("divFromMonthControl").style.display = "block";
		document.getElementById("divToMonth").style.display = "block";
		document.getElementById("divToMonthControl").style.display = "block";

		document.getElementById("divFromDate").style.display = "none";
		document.getElementById("divFromDateControl").style.display = "none";
		document.getElementById("divToDate").style.display = "none";
		document.getElementById("divToDateControl").style.display = "none";
	}
	else if(radios[2].checked)	// Date Wise
	{
		document.getElementById("divFromYear").style.display = "none";
		document.getElementById("divFromYearControl").style.display = "none";
		document.getElementById("divToYear").style.display = "none";
		document.getElementById("divToYearControl").style.display = "none";

		document.getElementById("divFromMonth").style.display = "none";
		document.getElementById("divFromMonthControl").style.display = "none";
		document.getElementById("divToMonth").style.display = "none";
		document.getElementById("divToMonthControl").style.display = "none";

		document.getElementById("divFromDate").style.display = "block";
		document.getElementById("divFromDateControl").style.display = "block";
		document.getElementById("divToDate").style.display = "block";
		document.getElementById("divToDateControl").style.display = "block";
	}
}



/*validations

Maximum days months etc.
*/ 


