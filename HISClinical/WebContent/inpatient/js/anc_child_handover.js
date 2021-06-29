
function noOfDays(a,b)
{
	valid=true;
	var day=0;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	day=(adate-bdate)/86400000;
	return day;
}

function onblurTimeHourCheck(_hObj)
{
	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);
	// Setting Hour
	if(hh<10)	_hObj.value="0"+hh;
	else			_hObj.value=hh;
}

function onblurTimeMinCheck(_mObj)
{
	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);
	// Setting Minutes
	if(mm<10)	_mObj.value="0"+mm;
	else			_mObj.value=mm;
}

function validateSave()
{
	if(document.getElementsByName("handoverDate")[0].value=="")
	{
		alert("Please Enter Handover Date");
		document.getElementsByName("handoverDate")[0].focus();
		return false;
	}
	
	var daysDiff=noOfDays(document.getElementsByName("entryDate")[0].value,document.getElementsByName("handoverDate")[0].value)
	if(daysDiff<0)
	{
		alert("Handover Date can not be greater than System Date");
		document.getElementsByName("handoverDate")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("handoverTimeHr")[0].value=="")
	{
		alert("Please Enter Handover Time Hour");
		document.getElementsByName("handoverTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("handoverTimeHr")[0].value<0 || document.getElementsByName("handoverTimeHr")[0].value>23)
	{
		alert("Hours must be in range 0-23");
		document.getElementsByName("handoverTimeHr")[0].focus();
		return false;
	}
	if(document.getElementsByName("handoverTimeMin")[0].value=="")
	{
		alert("Please Enter Handover Time Minute");
		document.getElementsByName("handoverTimeMin")[0].focus();
		return false;
	}
	if(document.getElementsByName("handoverTimeMin")[0].value<0 || document.getElementsByName("handoverTimeMin")[0].value>59)
	{
		alert("Minute must be in range 0-59");
		document.getElementsByName("handoverTimeMin")[0].focus();
		return false;
	}
	
	if(daysDiff==0)
	{
		if(document.getElementsByName("handoverTimeHr")[0].value>document.getElementsByName("entryTimeHr")[0].value)
		{
			alert("Handover Time can not be greater than System Time");
			document.getElementsByName("handoverTimeHr")[0].focus();
			return false;
		}		
		if(document.getElementsByName("handoverTimeHr")[0].value==document.getElementsByName("entryTimeHr")[0].value)
		{
			if(document.getElementsByName("handoverTimeMin")[0].value>document.getElementsByName("entryTimeMin")[0].value)
			{
				alert("Handover Time can not be greater than System Time");
				document.getElementsByName("handoverTimeMin")[0].focus();
				return false;
			}
		}
	}
	
	document.getElementsByName("handoverDateTime")[0].value = document.getElementsByName("handoverDate")[0].value+" "+
		document.getElementsByName("handoverTimeHr")[0].value+":"+document.getElementsByName("handoverTimeMin")[0].value;
	
	if(document.getElementsByName("relativeName")[0].value=="")
	{
		alert("Please Enter Relative Name");
		document.getElementsByName("relativeName")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("relativeCode")[0].value=="-1")
	{
		alert("Please Select Relationship");
		document.getElementsByName("relativeCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("relativeAddress")[0].value=="")
	{
		alert("Please Enter Relative Address");
		document.getElementsByName("relativeAddress")[0].focus();
		return false;
	}

	return true;
}