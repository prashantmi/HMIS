function submitForm(mode)
{
document.getElementsByName('hmode')[0].value=mode;
document.forms[0].submit();
}

function hideOrganiserDetail(event)
{
document.getElementById("ExternalOrganisation").style.display="none";

}

function showOrganiserDetail(event)
{
document.getElementById("ExternalOrganisation").style.display="";
}



function showSbtcRemarks(event)
{
document.getElementById("sbtcRemarkLabel").style.display="";
document.getElementById("sbtcRemarkValue").style.display="";
}

function hideSbtcRemarks(event)
{
document.getElementById("sbtcRemarkLabel").style.display="none";
document.getElementById("sbtcRemarkValue").style.display="none";
}

function selectSbtcPermission(event)
{
		if(document.getElementsByName("sbtcPermission")[0].value=="-1")
		{
		document.getElementsByName("sbtcRemark")[0].disabled=true;
		}
		
		else if(document.getElementsByName("sbtcPermission")[0].value=="0")
		{
		document.getElementsByName("sbtcRemark")[0].disabled=false;
		}
		
	    else if(document.getElementsByName("sbtcPermission")[0].value=="1")
		{
		document.getElementsByName("sbtcRemark")[0].disabled=false;
		}
		else if(document.getElementsByName("sbtcPermission")[0].value=="2")
		{
		document.getElementsByName("sbtcRemark")[0].disabled=true;
		}
}

	
function onkeyTimeHour(_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+hh);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38) // on Arrow press Down-40 Up-38
	{
		if(k==40)		hh=(hh+1)%24;
		else if(k==38)	hh=(24+hh-1)%24;

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;
	}
	if(k!=0)	return true;
	else		return false;
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


function onkeyTimeMin(_mObj,_hObj,_e) // 24-Hour Format
{
	var c=_e.charCode;
	var k=_e.keyCode;

	var hh=/^0/.test(_hObj.value)?_hObj.value.substr(1,1):_hObj.value;
	if(hh=="" || hh>=24) hh=0;
	hh=parseInt(hh);

	var mm=/^0/.test(_mObj.value)?_mObj.value.substr(1,1):_mObj.value;
	if(mm=="" || mm>=60) mm=0;
	mm=parseInt(mm);

	//alert("Key -> "+k+"  Char -> "+c+"  Value -> "+mm);

	// Only integer check
	if(k==0 && c>=48 && c<=57) return true;

	if(k==40 || k==38)	// on Arrow press Down-40 Up-38
	{
		if(k==40)
		{
			if(mm==59)	hh=(hh+1)%24;
			mm=(mm+1)%60;
		}
		else if(k==38)
		{
			if(mm==0)	hh=(24+hh-1)%24;
			mm=(60+mm-1)%60;
		}

		// Setting Hour
		if(hh<10) _hObj.value="0"+hh;
		else		_hObj.value=hh;

		// Setting Minutes
		if(mm<10) _mObj.value="0"+mm;
		else		_mObj.value=mm;
	}
	if(k!=0)	return true;
	else		return false;
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

function datecheck()
{	

	  var aArray=(document.getElementsByName("campDate")[0].value).split("-");
      var amonth=aArray[1];
      
      var aday=aArray[0];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);
      
      
      var bArray=(document.getElementsByName("systemDate")[0].value).split("-");
      var bmonth=bArray[1];
      var bday=bArray[0];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
      if(adate<bdate)
      {
      alert("Camp Proposed Date Should Be Greater than System Date");
      document.forms[0].campDate.value="";
      document.forms[0].campDate.focus(); 
      return false;
      }
 return true;
}

function noOfDays(a,b){
 
      var aArray=a.split("-");
      var amonth=aArray[1];
      
      var aday=aArray[0];
      var ayear=aArray[2];
      var adate=new Date(amonth +" "+ aday+" "+ayear);// camp date
      
      
      var bArray=b.split("-");
      var bmonth=bArray[1];
      var bday=bArray[0];
      var byear=bArray[2];
      var bdate=new Date(bmonth +" "+ bday+" "+byear);// system date
      var days=(bdate-adate)/ 86400000;
 	  return days;
 
 }

function  showOrganisationName()
{
			if(document.forms[0].organisationName.value=="")
				 {
				 alert("Enter Organisation Name");
				 document.forms[0].organisationName.focus();
				 return false;                          
				 }
				  else if(document.forms[0].venue.value=="")
				 {
				 alert("Enter Venue Name");
				 document.forms[0].venue.focus();
				 return false;                          
				 }
				// else if(document.forms[0].venueAddress.value=="")
				// {
				// alert("Enter Venue Address");
				// document.forms[0].venueAddress.focus();
				// return false;                          
				 //}
	 return true;
				 
}


function trimNum(n)

{

while(n.substr(0,1)=='0') n=n.substr(1);

return n;

}

function validateSave(mode)
{
		var sampStartHr=0;
		var sampEndHr=0;
		var sampStartMin=0;
		var sampEndMin=0;
		var valid=false;
		if(document.getElementsByName("sampStartHr")[0].value=="00")
		{
			sampStartHr=0;
		}
		else
		{
		campStartHr=parseInt(trimNum(document.forms[0].campStartHr.value))
		}
		
		if(document.getElementsByName("sampEndHr")[0].value=="00")
		{
			sampEndHr=0;
		}
		else
		{
		campEndHr=parseInt(trimNum(document.forms[0].campEndHr.value))
		}
		if(document.getElementsByName("sampStartMin")[0].value=="00")
		{
			sampStartMin=0;
		}
		else
		{
		campStartMin=parseInt(trimNum(document.forms[0].campStartMin.value))
		}
		if(document.getElementsByName("sampEndMin")[0].value=="00")
		{
			sampEndMin=0;
		}
		else
		{
		campEndMin=parseInt(trimNum(document.forms[0].campEndMin.value))
		}
		
		 if(document.getElementsByName("campDate")[0].value=="")
		 {
			alert("Select Camp Proposed Date");
			document.getElementsByName("campDate")[0].focus();
			return false;    
		 }
		 if(document.getElementsByName("campDate")[0].value!="" && !datecheck())
		 {
			return false;
		 }
		 
	
	
		if(document.getElementsByName("campStartHr")[0].value=="" && document.getElementsByName("campStartMin")[0].value=="" && document.getElementsByName("campEndHr")[0].value=="" && document.getElementsByName("campEndMin")[0].value=="")	
		{
			valid=true;
		}
		else
		//alert("1"+document.getElementsByName("campStartHr")[0].value+"=="+document.getElementsByName("campStartMin")[0].value+"=="+document.getElementsByName("campEndHr")[0].value+"=="+document.getElementsByName("campEndMin")[0].value+"2")
		
		{
			if(document.getElementsByName("campStartHr")[0].value=="")
			{
				alert("Enter Camp Start Time Hr");
				document.getElementsByName("campStartHr")[0].focus();
				return false;
			}
			if(document.getElementsByName("campStartMin")[0].value=="")
			{
				alert("Enter Camp Start Time Minute");
				document.getElementsByName("campStartMin")[0].focus();
				return false;
			}
			if(document.getElementsByName("campEndHr")[0].value=="")
			{
				alert("Enter Camp End Time Hr");
				document.getElementsByName("campEndHr")[0].focus();
				return false;
			}
			if(document.getElementsByName("campEndMin")[0].value=="")
			{
				alert("Enter Camp End Time Minute");
				document.getElementsByName("campEndMin")[0].focus();
				return false;
			}		
		}
		
		var day=0;
		day=noOfDays(document.getElementsByName("campDate")[0].value,document.getElementsByName("systemDate")[0].value);
		if(day==0)
		{
			if(document.getElementsByName("campStartHr")[0].value!="")
			{
				if(document.getElementsByName("campStartHr")[0].value < document.getElementsByName("systemTimeInHr")[0].value)
				{
					alert("Camp Start Time Should be Greater than System Time");
					document.getElementsByName("campStartHr")[0].focus();
					return false;
				}
				else
				{
					if(document.getElementsByName("campStartHr")[0].value == document.getElementsByName("systemTimeInHr")[0].value)
					{
						if(document.getElementsByName("campStartMin")[0].value <= document.getElementsByName("systemTimeInMin")[0].value)
						{
							alert("Camp Start Time Should be Greater than System Time");
							document.getElementsByName("campStartMin")[0].focus();
							return false;
						}
					}
				}
				
				//Checking for End Time
				if(document.getElementsByName("campEndHr")[0].value < document.getElementsByName("campStartHr")[0].value)
				{
					alert("Camp End Time Should be Greater than Camp Start Time");
					document.getElementsByName("campEndHr")[0].focus();
					return false;
				}
				else
				{
					if(document.getElementsByName("campEndHr")[0].value == document.getElementsByName("campStartHr")[0].value)
					{
						if(document.getElementsByName("campEndMin")[0].value <= document.getElementsByName("campStartMin")[0].value)
						{
							alert("Camp End Time Should be Greater than System Time");
							document.getElementsByName("campEndMin")[0].focus();
							return false;
						}
					}
				}
				
			}
		}
		if(day<0)
		{
			if(document.getElementsByName("campStartHr")[0].value!="")
			{
				//Checking for End Time
				if(document.getElementsByName("campEndHr")[0].value < document.getElementsByName("campStartHr")[0].value)
				{
					alert("Camp End Time Should be Greater than Camp Start Time");
					document.getElementsByName("campEndHr")[0].focus();
					return false;
				}
				else
				{
					if(document.getElementsByName("campEndHr")[0].value == document.getElementsByName("campStartHr")[0].value)
					{
						if(document.getElementsByName("campEndMin")[0].value <= document.getElementsByName("campStartMin")[0].value)
						{
							alert("Camp End Time Should be Greater than System Time");
							document.getElementsByName("campEndMin")[0].focus();
							return false;
						}
					}
				}
			}	
		}	
		
		//alert("3")
		
		 if(campStartHr>campEndHr)
		 {
		  alert("Camp End Time should be greater than Camp Start Time:::: ") 
		   document.forms[0].campStartHr.focus();
		   return false;
		 }
		  else if(campStartHr==campEndHr&&campStartMin==campEndMin)
			 {
			 		alert("Camp End Time Should Be Greater Than Camp Start Time ") 
			   		document.forms[0].campEndMin.focus();
			   		return false;
			 }
		 else if(campStartHr==campEndHr&&campStartMin>campEndMin)
		 {
		 		alert("Camp End Time Should Be Greater Than Camp Start Time ") 
		   		document.forms[0].campEndMin.focus();
		   		return false;
		 }
		  
		 
		// else if(document.forms[0].refreshmentBy.value=="-1")
		// {
		// alert("Select Refreshment By");
		// document.forms[0].refreshmentBy.focus();
		// return false;                          
		// }
		 
		//  else if(document.forms[0].donationType.value=="-1")
		// {
		// alert("Select Donation Type");
		// document.forms[0].donationType.focus();
		// return false;                          
		// }
		 
		 else if(document.getElementsByName("selfOrganiser")[1].checked==true && document.getElementsByName("societyId")[0].value=="-1")
		 {
		 	 alert("Select Society/Organiser");
			 document.forms[0].societyId.focus();
			 return false;                          
		  }
		  
		 // else if(document.getElementsByName("campType")[1].checked==true && document.forms[0].organisationName.value=="")
		//		 {
		//		 alert("Enter Organisation Name");
		//		 document.forms[0].organisationName.focus();
		//		 return false;                          
		//		 }
		 else if(document.getElementsByName("campType")[1].checked==true && document.forms[0].venue.value=="")
				 {
				 alert("Enter Venue Name");
				 document.forms[0].venue.focus();
				 return false;                          
				 }
		else if(document.getElementsByName("campType")[1].checked==true && document.forms[0].venueAddress.value=="")
				 {
				 alert("Enter Venue Address");
				 document.forms[0].venueAddress.focus();
				 return false;                          
				 }
		  
		  
		 
		 
					 
		 
		/* else if(document.getElementsByName("sbtcPermission")[0].value=="-1")
		 {
		 alert("Select SBTC Permission");
		 document.forms[0].sbtcPermission.focus();
		 return false;                          
		 }*/
		 
		  else submitForm(mode);

}

 
