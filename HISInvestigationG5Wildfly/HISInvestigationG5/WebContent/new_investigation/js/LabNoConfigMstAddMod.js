function submitPage(mode)
{

	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){

	// These All Fields are Mandatory


	if(document.getElementsByName("labCode")[0].value=="-1")
	{
		alert("Select Laboratory ");
		document.getElementsByName("labCode")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("patientType")[0].value=="-1")
	{
		alert("Select Patient Type");
		document.getElementsByName("patientType")[0].focus();
		return false;                          
	}

	if(document.getElementsByName("initializationType")[0].value=="")
	{
		alert("Select Initialization Type");
		document.getElementsByName("initializationType")[0].focus();
		return false;                          
	}


	if(document.getElementsByName("initializationType")[4].checked!=true)
	{

		if(document.getElementsByName("seqDigit")[0].value=="")
		{
			alert("Enter Number of Series Digits");
			document.getElementsByName("seqDigit")[0].focus();
			return false;
		}
		if(document.getElementsByName("labNoFormat")[0].value=="")
		{
			alert("Enter Laboratory Number Format");
			document.getElementsByName("labNoFormat")[0].focus();
			return false;
		}

		if(document.getElementsByName("fromSeries")[0].value=="")
		{
			alert("Enter From Series Value");
			document.getElementsByName("fromSeries")[0].focus();
			return false;
		}

		if(document.getElementsByName("toSeries")[0].value=="")
		{
			alert("Enter To Series Value");
			document.getElementsByName("toSeries")[0].focus();
			return false;
		}

		if(parseInt(document.getElementsByName("toSeries")[0].value)<=parseInt(document.getElementsByName("fromSeries")[0].value))
		{
			alert("Enter Valid To Series Value");
			document.getElementsByName("toSeries")[0].value="";
			document.getElementsByName("toSeries")[0].focus();
			return false;
		}

		if(document.getElementsByName("acceptanceAreawise")[0].value=="")
		{
			alert("Please Choose Is Acceptance Area Wise");
			document.getElementsByName("acceptanceAreawise")[0].focus();
			return false;                          
		}

		
		var initializationType="2";
		for(var i=0;i<document.getElementsByName("acceptanceAreawise").length;i++)
		{
			if(document.getElementsByName("acceptanceAreawise")[i].checked==true)
			{
				initializationType=document.getElementsByName("acceptanceAreawise")[i].value;
			} 
		}

		
		if(initializationType=='2')
		{
			
			
				alert("Please Choose Is Acceptance Area Wise");
				document.getElementsByName("sampleAcceptanceAreaCode")[0].focus();
				return false;                          
			
			
		}
		
		if(initializationType=='1')
		{
			
			if(document.getElementsByName("sampleAcceptanceAreaCode")[0].value=="-1")
			{
				alert("Select Acceptance Area");
				document.getElementsByName("sampleAcceptanceAreaCode")[0].focus();
				return false;                          
			
			}
		}

		
	
		
		
		/*
		if(document.getElementsByName("initDate")[0].value=="")
		{
			alert("Enter Initialization Date");
			document.getElementsByName("initDate")[0].focus();
			return false;
		}	if(document.getElementsByName("reinitDate")[0].value=="")
		{
			alert("Enter Re-Initialization Date");
			document.getElementsByName("reinitDate")[0].focus();
			return false;
		}

		if(!datecheckFromDateToDate('initDate','reinitDate'))
		{
				alert("Enter Valid Re-Initialization Date");
				document.getElementsByName("reinitDate")[0].value="";
				document.getElementsByName("reinitDate")[0].focus();
				return false;
		}
		 */


	}
	else
	{
		/*document.getElementsByName("initDate")[0].value="01-Jan-1900";
		document.getElementsByName("reinitDate")[0].value="01-Jan-1900";*/
		document.getElementsByName("labNoFormat")[0].value="-";
		document.getElementsByName("seqDigit")[0].value="";
		document.getElementsByName("fromSeries")[0].value="";
		document.getElementsByName("toSeries")[0].value="";

	}



	return true;
} 	

function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);

}

function clearForm()
{

	document.getElementsByName("yearFormat")[0].checked=false;
	document.getElementsByName("yearFormat")[1].checked=false;
	document.getElementsByName("monthFormat")[0].checked=false;
	document.getElementsByName("monthFormat")[1].checked=false;
	document.getElementsByName("dateFormat")[0].checked=false;
	submitPage('CLEAR');
}
function resetType()
{
	
	
	document.getElementById("thirdTable").style.display='';
	

		document.getElementById("secondTable").style.display='';

	var initializationType="m";
	for(var i=0;i<document.getElementsByName("initializationType").length;i++)
	{
		if(document.getElementsByName("initializationType")[i].checked==true)
		{
			initializationType=document.getElementsByName("initializationType")[i].value;
		} 
	}

	if(initializationType=='x')
	{
		document.getElementById("secondTable").style.display='none';
		document.getElementById("thirdTable").style.display='none';

		document.getElementsByName("yearFormat")[0].checked=false;
		document.getElementsByName("yearFormat")[1].checked=false;
		document.getElementsByName("monthFormat")[0].checked=false;
		document.getElementsByName("monthFormat")[1].checked=false;
		document.getElementsByName("dateFormat")[0].checked=false;
		document.getElementsByName("partOne")[0].value="";
		document.getElementsByName("partTwo")[0].value="";
		document.getElementsByName("seriesFormat")[0].value="";
		document.getElementsByName("dateOrder")[0].value="";
		document.getElementsByName("monthOrder")[0].value="";
		document.getElementsByName("yearOrder")[0].value="";
		document.getElementsByName("initDate")[0].value="";
		document.getElementsByName("reinitDate")[0].value="";
		document.getElementsByName("seqDigit")[0].value="";
		document.getElementsByName("labNoFormat")[0].value="";
		document.getElementsByName("fromSeries")[0].value="";
		document.getElementsByName("toSeries")[0].value="";
	}

	document.getElementsByName("initDate")[0].value="";
	document.getElementsByName("reinitDate")[0].value="";

}

function resetInitializationDates()
{
	document.getElementById("secondTable").style.display='';
	document.getElementById("thirdTable").style.display='';

	var initializationType="m";
	for(var i=0;i<document.getElementsByName("initializationType").length;i++)
	{
		if(document.getElementsByName("initializationType")[i].checked==true)
		{
			initializationType=document.getElementsByName("initializationType")[i].value;
		} 
	}

	if(initializationType=='x')
	{
		document.getElementById("secondTable").style.display='none';
		document.getElementById("thirdTable").style.display='none';

		document.getElementsByName("yearFormat")[0].checked=false;
		document.getElementsByName("yearFormat")[1].checked=false;
		document.getElementsByName("monthFormat")[0].checked=false;
		document.getElementsByName("monthFormat")[1].checked=false;
		document.getElementsByName("dateFormat")[0].checked=false;
		document.getElementsByName("partOne")[0].value="";
		document.getElementsByName("partTwo")[0].value="";
		document.getElementsByName("seriesFormat")[0].value="";
		document.getElementsByName("dateOrder")[0].value="";
		document.getElementsByName("monthOrder")[0].value="";
		document.getElementsByName("yearOrder")[0].value="";
		document.getElementsByName("initDate")[0].value="";
		document.getElementsByName("reinitDate")[0].value="";
		document.getElementsByName("seqDigit")[0].value="";
		document.getElementsByName("labNoFormat")[0].value="";
		document.getElementsByName("fromSeries")[0].value="";
		document.getElementsByName("toSeries")[0].value="";
	}

	document.getElementsByName("initDate")[0].value="";
	document.getElementsByName("reinitDate")[0].value="";
}


function resetReInitializationDate()
{
	


	var initializationType="m";
	for(var i=0;i<document.getElementsByName("initializationType").length;i++)
	{
		if(document.getElementsByName("initializationType")[i].checked==true)
		{
			initializationType=document.getElementsByName("initializationType")[i].value;
		} 
	}	

	if(initializationType=='d')
	{
		document.getElementsByName("reinitDate")[0].value=getNextDate(document.getElementsByName("initDate")[0].value,1,'-');
	}
	else if(initializationType=='w')
	{
		document.getElementsByName("reinitDate")[0].value=getNextDate(document.getElementsByName("initDate")[0].value,7,'-');
	}
	else if(initializationType=='m')
	{
		document.getElementsByName("reinitDate")[0].value=getNextDate(document.getElementsByName("initDate")[0].value,daysInMonth(document.getElementsByName("initDate")[0].value,'-'),'-');
	}
	else if(initializationType=='y')
	{
		document.getElementsByName("reinitDate")[0].value=getNextDate(document.getElementsByName("initDate")[0].value,365,'-');
	}
	else
	{
		document.getElementsByName("reinitDate")[0].value="";
	}

	
	

	

}
function daysInMonth(val,delimitter)
{
	var aArray=val.split(delimitter);
	var amonth=aArray[1];

	var aday=aArray[0];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	return 32 - new Date(adate.getFullYear(), adate.getMonth(), 32).getDate();
}


function getNextDate(val,noOfDays,delimitter)
{
	var aArray=val.split(delimitter);
	var amonth=aArray[1];

	var aday=aArray[0];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);

	var newDate=new Date();
	newDate.setDate(adate.getDate()+ noOfDays);

	return ((newDate.getDate()>9)?newDate.getDate():'0'+newDate.getDate())+delimitter+getMonthStr(newDate.getMonth())+delimitter+newDate.getFullYear();

}

function getMonthStr(val)
{
	switch(val){
	case 0:
		return 'Jan';
	case 1:
		return 'Feb';
	case 2:
		return 'Mar';
	case 3:
		return 'Apr';
	case 4:
		return 'May';
	case 5:
		return 'Jun';
	case 6:
		return 'Jul';
	case 7:
		return 'Aug';
	case 8:
		return 'Sep';
	case 9:
		return 'Oct';
	case 10:
		return 'Nov';
	case 11:
		return 'Dec';	

	}
}

function setMaxLength(initiate)
{
	document.getElementsByName("fromSeries")[0].setAttribute('maxlength',document.getElementsByName("seqDigit")[0].value);
	document.getElementsByName("toSeries")[0].setAttribute('maxlength',document.getElementsByName("seqDigit")[0].value);	
}



function datecheckFromDateToDate(from,to)
{
	var aArray=(document.getElementsByName(from)[0].value).split("-");
	var amonth=aArray[1];

	var aday=aArray[0];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);

	var bArray=(document.getElementsByName(to)[0].value).split("-");
	var bmonth=bArray[1];
	var bday=bArray[0];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);

	if(adate>bdate)
		return false;
	else
		return true;

}

function maxlength(){
	
	if(parseInt(document.getElementsByName("seqDigit")[0].value)>6)
		{
		
		alert("Sequence Digit Max Value=6");
		document.getElementsByName("seqDigit")[0].value="";
		document.getElementsByName("seqDigit")[0].focus();
		document.getElementsByName("labNoFormat")[0].value="";
		
		}
	


	else if(document.getElementsByName("seqDigit")[0].value=='')	
	{
		document.getElementsByName("fromSeries")[0].value='';
		document.getElementsByName("toSeries")[0].value='';
		document.getElementsByName("fromSeries")[0].setAttribute('maxlength',0);
		document.getElementsByName("toSeries")[0].setAttribute('maxlength',0);
	}

	else if(document.getElementsByName("seqDigit")[0].value!='')
	{
	document.getElementsByName("partTwo")[0].value=setPartTwo(document.getElementsByName("seqDigit")[0].value);
	document.getElementsByName("labNoFormat")[0].value=setSequence(document.getElementsByName("seqDigit")[0].value);
	document.getElementsByName("fromSeries")[0].setAttribute('maxlength',document.getElementsByName("seqDigit")[0].value);
	document.getElementsByName("toSeries")[0].setAttribute('maxlength',document.getElementsByName("seqDigit")[0].value);	
	}
	
	else
		{document.getElementsByName("fromSeries")[0].value='';
	document.getElementsByName("toSeries")[0].value='';
	document.getElementsByName("partTwo")[0].value=setPartTwo(document.getElementsByName("seqDigit")[0].value);
	document.getElementsByName("labNoFormat")[0].value=setSequence(document.getElementsByName("seqDigit")[0].value);
	document.getElementsByName("fromSeries")[0].setAttribute('maxlength',document.getElementsByName("seqDigit")[0].value);
	document.getElementsByName("toSeries")[0].setAttribute('maxlength',document.getElementsByName("seqDigit")[0].value);	
	}


}
function resetLabNo()
{
	
	document.getElementsByName("partTwo")[0].value=setPartTwo(document.getElementsByName("seqDigit")[0].value);
	document.getElementsByName("labNoFormat")[0].value=setSequence(document.getElementsByName("seqDigit")[0].value);

}

function setPartTwo(noOfDigits)
{	
	var varString="";

	
	varString+=document.getElementsByName("partOne")[0].value;

	varString+=document.getElementsByName("seriesFormat")[0].value;

	for(var i=0;i<parseInt(noOfDigits);i++)
	{
		varString+="X";
	}

	return varString;
}

function setSequence(noOfDigits)
{	
	var varString="";
	
	varString+=document.getElementsByName("partOne")[0].value;
	varString+="&";
	varString+=document.getElementsByName("seriesFormat")[0].value;

	for(var i=0;i<parseInt(noOfDigits);i++)
	{
		varString+="X";
	}

	return varString;
}

function setDate(){

	var varString="";
	var rs=document.getElementsByName("partOne")[0].value;
	
	if(	document.getElementsByName("dateOrder")[0].value!="1" && (rs.indexOf("DD")==-1))	
	{
		if(document.getElementsByName("dateFormat")[0].checked==true)
		{varString+="DD"; }

		document.getElementsByName("partOne")[0].value+=varString;
		resetLabNo();
		document.getElementsByName("dateOrder")[0].value="1";
	}


}


function setMonth(){
	var varString="";
	var rs=document.getElementsByName("partOne")[0].value;
	
	if(	document.getElementsByName("monthOrder")[0].value!="1" && (rs.indexOf("MM")==-1) && (rs.indexOf("MON")==-1))
	{
		if(document.getElementsByName("monthFormat")[0].checked==true)
			varString+="MM";
		else if(document.getElementsByName("monthFormat")[1].checked==true)
			varString+="MON";

		document.getElementsByName("partOne")[0].value+=varString;
		resetLabNo();

		document.getElementsByName("monthOrder")[0].value="1";
	}
	
	else
	{
	
	
	
	if(document.getElementsByName("monthFormat")[0].checked==true)
		document.getElementsByName("partOne")[0].value=rs.replace("MON","MM");	
	else
		document.getElementsByName("partOne")[0].value=rs.replace("MM","MON");
	
	
	resetLabNo();
	
	}

}

function setYear(){

	var varString="";
	var rs=document.getElementsByName("partOne")[0].value;

	if(document.getElementsByName("yearOrder")[0].value!="1" && (rs.indexOf("YY")==-1) && (rs.indexOf("YYYY")==-1))
	{
		if(document.getElementsByName("yearFormat")[0].checked==true)
		varString+="YY";
		else if(document.getElementsByName("yearFormat")[1].checked==true)
		varString+="YYYY";
	

		document.getElementsByName("partOne")[0].value+=varString;
		resetLabNo();

		document.getElementsByName("yearOrder")[0].value="1";
	}
	else
		{
		
		
		
		if(document.getElementsByName("yearFormat")[0].checked==true)
			document.getElementsByName("partOne")[0].value=rs.replace("YYYY","YY");	
		else if((rs.indexOf("YYYY")==-1))
			document.getElementsByName("partOne")[0].value=rs.replace("YY","YYYY");
		
		
		resetLabNo();
		
		}

}