
/*
Project			: HIS			
File Name 		: Validation.js
Version	  		: 1.0
Developer 		: HIS Team
*/

/*
Note --> 
1. please name your form as form1;
2. add your validation string in getValidateStr() function
3. Add message in getMsg() function

Warning : NO CHANGE WILL BE REFLECTED IN ORIGINAL VALIDATION FILE WITHOUT AJAY,JITU,LOKESH OR VAIBHAV
PERMISSION	 
*/

/*
	List of functions in this file

 1>	function getValidateStr(index)
 2>	function getMsg(index,conName)
 3>	function validateData(e,index)
 4>	function validAmount(obj)
 5>	function compareDate(frDate,toDate)
 6>	function validateFields(checkArray,mode)
 7>	function isDate(theField)
 8>	function IsAgeValid(theField)
 9>	function submitForm()
10>	function removeStrSpace(str)
11>	function daysInFebruary (year)
12>	function DaysInMonth(mon, year) 
13> function getSeperator(dtStr)	
14>	function getMonthInt(str)
15> function parseDate(dtStr,seprator,mode)
16> function initilizeVar()
17> Showlayer(obj,layerObj)
18> Hidelayer(layerObj)	
*/


/*============================Global Variables declares here=====================================*/
var intDay, intMon, intYear;

/*============================Function Starts from here==========================================*/


/*this function returns the requested string for validations, any validating string
can be appended in this function just before default keyword
*/ 
function getValidateStr(index)
{
	var str = "";
	
	switch(index)
	{
		case 1:		//for validating email
			str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_@.";
			break;
			
		case 2:		//for validating telephone no
			str = "1234567890-+";
			break;
			
		case 3:		//for validating address
			str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-/,.#$()';: ";
			break;
			
		case 4:		//for validating name
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .";
			break;
			
		case 5:		//numeric only
			str = "1234567890";
			break;
			
		case 6:		//numeric with space
			str = "1234567890 ";
			break;
			
		case 7:		//for validating amount
			str = "1234567890.";
			break;
			 
		case 8:		//alphanumeric
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;
			
		case 9:		//alphanumeric with space
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ";
			break;
			
		case 10:	//Character only
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
			
		case 11:	//Character with space
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
			break;
			
		case 12:	//Upper character only
			str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
			
		case 13:	//Upper character with space
			str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
			break;
			
		case 14:	//Lower character
			str = "abcdefghijklmnopqrstuvwxyz";
			break;
			
		case 15:	//Lower character with space
			str = "abcdefghijklmnopqrstuvwxyz ";	 
			break;
			
		case 16:	//Company Names
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .()-";
			break;	
		/*case 17	
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890+-_#$:/";
			break;*/
		case 18:	//for validating time
			str = "1234567890:";
			break;	
		
						
										
	}	//end of Switch statement
	return str;
}//1


//this function will have the user defined messages
function getMsg(index,conName)
{
	var msgStr = "";
	
	switch(index)
	{
		case 1:
			msgStr = "Invalid amount entered [" + conName + "]";
			break;
		case 2:
			msgStr = "Invalid e-mail entered [" + conName + "]";
			break;
		case 3:
			msgStr = "Invalid Date entered [" + conName + "]";
			break;
		case 4:
			msgStr = "From Date is greater than To Date [" + conName + "]";
			break;
		case 5:
			msgStr = "Current Date is greater than " + conName;
			break; 
		case 6:
			msgStr = "[" + conName + "] is blank";
			break;
		case 7:
			msgStr = "[" + conName + "] is not selected";
			break;
		case 8:
			msgStr = "Invalid age entered [" + conName + "]";
	}	//end of switch statement
	
	alert(msgStr); 
	return;
}//2


//this function accepts index, based on index it validates the specified character within the string
//defined in getValidateStr function. it validates single character at a time.
function validateData(e,index)
{
	var key,keychar,str;
		
	if (window.event)
		key = window.event.keyCode;
	else
	{
		if (e)
			key = e.which;
		else
		   return true;
	}
	 
	keychar = String.fromCharCode(key);
		
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
		return true;
	else 
	{
		str = getValidateStr(index)
		if (((str).indexOf(keychar) > -1))
		   return true;
		else
		   return false;
	}

}//3

	 
//this function validates the amount.this function requires the object
//call this function if you have validated each & every digit using validateData function
function validAmount(obj)
{
	var index,len;
	var tempStr;
	var sts = 0;
	
	len = obj.value.length;
	tempStr = obj.value;
	index = tempStr.indexOf(".");
	
	if (index > -1)	//amount is in decimal
	{
		if (len == index+1)	//No digit after decimal point
			sts = 1;
		else
		{
			if (tempStr.indexOf(".",index+1) > -1)	//more than one decimal point
				sts = 1;
		}
	}	//end if
	
	if (sts == 1) 
	{
		getMsg(1,obj.name);
		obj.focus();
		return false;
	}
	
	return true;

}//4


//this function compares two date. pass blank to frDate if you want to compare toDate with current Date
//this function accepts the object. date should be dd-mm-yyyy format if mode = 1 otherwise dd-mmm-yyyy
function compareDate(frDate,toDate,mode)
{
	//alert("donation_date"+document.form1.donation_date.value);
	//alert(frDate);
	//alert(toDate);	
	var frValue, toValue,frYear, frMon, frDay,sts = 0;
		
	//validating todate
	if (frDate == "" || frDate == null)
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
				if (frYear > intYear)
					sts = 1;
				else
				{
					if (frYear == intYear)
					{
						if (frMon > intMon)
							sts = 1;
						else
						{
							if (frMon == intMon)
							{
								if (frDay > intDay)
									sts = 1;
							}
						}
					}
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
		if (frDate == "" || frDate == null)		//validating current date with toDate
			getMsg(5,toDate.name); 
		else
			//alert(frDate.name + " is greater than " + toDate.name);
		
		//frDate.focus();                                                       	
		return false;
	}
	document.form1.submit();
	return true;

}//5


/*this function checks those fields that is in checkArray for not null and submits the form 
if mode = 1  
checkArray = new Array("Control Name1","Control Name 2",-----) for calling this function
mode parameter*/ 
function validateFields(checkArray,mode)
{
		
	var i,type;
	var arrLen = checkArray.length;
	var obj;
	
	for ( i = 0 ; i < arrLen ; i++ )
	{   
		obj = document.forms[0].elements[checkArray[i]];
		if(obj!=undefined)
		{	
			type=obj.type;
				
			switch(type)
			{
				case "text":
					if(obj.value == null || obj.value == "")
					{
						getMsg(6,obj.name);	//displays error message
						obj.focus();
						return false;
					}
					break;
				
				case "select-one":
					if(obj.value == "0" || obj.value == "-1")
					{
						getMsg(7,obj.name);	//displays error message
						return false;
					}
					break;
					
				case "textarea":
					if(obj.value == null || obj.value == "")
					{
						getMsg(6,obj.name);	//displays error message
						obj.focus();
						return false;
					}
					break;	
			}		
		}
	}

	if (mode == 1) submitForm();
	return true;
	
}//6



//this function validates the date. the format should be dd-mm-yyyy or dd/mm/yyyy or dd.mm.yyyy
//if mode = 1 otherwise dd/mmm/yyyy or dd-mmm-yyyy or dd.mmm.yyyy
function isDate(theField,mode)
{
	var dtStr = removeStrSpace(theField.value);
	var seprator = "";
	
	if (dtStr == "") return false;		
	seprator = getSeperator(dtStr)		//function that returns seperator
	if (seprator != "")
	{
		if (parseDate(dtStr,seprator,mode) == true)
		{
			theField.value = dtStr;
			return true;
		}
	}
	
	getMsg(3,theField.name);	//display error message
	//theField.focus();
	return false;
}//7


/*this function validates the age*/
function IsAgeValid(theField)
{
	if (theField.value <= 0 || theField.value > 130)
	{
		getMsg(8,theField.name);
		theField.focus();
		return false;
	}
	return true;
}//8
	
	
/*this function submits the page*/
function submitForm()
{
	document.form1.submit();
}//9


/* Function For Removing spaces */
function removeStrSpace(str)
{
	var j;
	var len = str.length;
	var retStr ="";
	
	for(j = 0;j <= len;j++)
	{
		if(str.charAt(j) != " ")
			retStr += str.charAt(j);
	}	
	return retStr;		
}//10	


//returns day in feb month for specified year
function daysInFebruary (year)
{
	return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}//11


//this function returns no of days in a month for the specified year
function DaysInMonth(mon, year) 
{
	var retVal;
	
	retVal = 31;
	if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {retVal = 30;}
	if (mon == 2) {retVal = daysInFebruary(year);}
   	return retVal;
}//12


//this function finds the seperator used for seperating the date
function getSeperator(dtStr)
{
	var seprator = "";
	
	if (dtStr.indexOf("-") > -1)
		seprator = "-";
	else
	{
		if (dtStr.indexOf("/") > -1)
			seprator = "/";
		else
		{
			if (dtStr.indexOf(".") > -1)
				seprator = ".";
		}
	}	//endif
	return seprator;
}//13


//this function converts the month(in string) into month(in integer)
function getMonthInt(str)
{
	var month = -1;
	
	switch(str.toUpperCase())
	{
		case "JAN":
			month = 0;
			break;
		case "FEB":
			month = 1;
			break;
		case "MAR":
			month = 2;
			break;
		case "APR":
			month = 3;
			break;
		case "MAY":
			month = 4;
			break;
		case "JUN":
			month = 5;
			break;
		case "JUL":
			month = 6;
			break;
		case "AUG":
			month = 7;
			break;
		case "SEP":
			month = 8;
			break;
		case "OCT":
			month = 9;
			break;
		case "NOV":
			month = 10;
			break;
		case "DEC":
			month = 11;
			break;
	}
	return month;
}//14


//this function parses the date inti day, month & year
function parseDate(dtStr,seprator,mode)
{
	var pos1,pos2;
	var len = dtStr.length;
	
	initilizeVar();		//initilizes the variables
	pos1 = dtStr.indexOf(seprator);
	pos2 = dtStr.indexOf(seprator,pos1+1);
	
	if (len > 8 && len <= 11)
	{			
		if (pos1 > 0 && pos1 <= 2)	//validations for day
		{
			if (pos2 > pos1 + 1 && len == pos2 + 5)	//validation for month & year
			{
				//getting value seperately and convert it into int
				intDay = parseInt(dtStr.substring(0,pos1),'10');
				if (mode == 1)
					intMon = parseInt(dtStr.substring(pos1+1,pos2),'10');
				else
					intMon = getMonthInt(dtStr.substring(pos1+1,pos2));
						
				intYear = parseInt(dtStr.substring(pos2+1),'10');
				
				if (intMon >= 0 && intMon <= 12)
				{
					if (intYear >= 1900 && intYear <= 2100)
					{
					
					
		
						if (intDay > 0 && intDay <= DaysInMonth(intMon+1, intYear))
						{
							return true;
						}
					}//end if
				}//endif
			}//endif
		}//endif
	}//endif
	return false;
}//15


function checkmail(obj)
{
	var eindex;
	var mailstr = obj.value;
	var retVal = false;
	
	if(mailstr != "")
	{
		eindex = mailstr.indexOf('@');
		if(eindex!=-1)
		{
			var newstr = mailstr.substring(eindex+1);
			if(newstr.indexOf('@') == -1)
			{
				if(mailstr.indexOf('.') != -1 && (mailstr.indexOf('.') > (eindex+1)) )
					retVal = true;
			}
		}
	}
	else
		retVal = true;
		
	if(retVal == false)
		getMsg(2,obj.name);
		
	return retVal;
}//16

//this function initilizes the global variable 
function initilizeVar()
{
	intDay = "";
	intMon = "";
	intYear = "";
}//17

//this function displays the layer and shifts the other layer after the 
//displayed layer
//Note--> the layer's position property should be relative and top & left
//value should be zero 
function Showlayer(obj,layerObj)
{
	if(obj.checked)
		layerObj.style.display = "BLOCK";
	else
		layerObj.style.display = "NONE";
}//18

//this function displays the layer and shifts the other layer after the 
//displayed layer
//Note--> the layer's position property should be relative and top & left
//value should be zero 
function Hidelayer(layerObj)
{
	layerObj.style.display = "NONE";
}//19


function compareDatecmpsep(mode)
{

	var frValue, toValue,frYear, frMon, frDay,sts = 0;
		
	frDate=	document.form1.donation_date.value;
	toDate=document.form1.cdt.value;
		
	//validating todate
	if (frDate == "" || frDate == null)
	{
		frValue = new Date;
		frYear = frValue.getYear();
		frMon = frValue.getMonth();
		frDay = frValue.getDate();
	}
	else
	{
		if (isDate(document.form1.donation_date,mode) == true)
		{
			frYear = intYear;
			frMon = intMon;
			frDay = intDay;
			
			if (isDate(document.form1.cdt,mode) == true)
			{
				if (frYear > intYear)
					sts = 1;
				else
				{
					if (frYear == intYear)
					{
						if (frMon > intMon)
							sts = 1;
						else
						{
							if (frMon == intMon)
							{
								if (frDay > intDay)
									sts = 1;
							}
						}
					}
				}
			}
			else
			{
				//toDate.focus();
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
		if (frDate == "" || frDate == null)		//validating current date with toDate
			getMsg(5,toDate.name); 
		else
			alert("Donation Date is greater than  Current Date");
		
		//frDate.focus();                                                       	
		return false;
	}
	
	return true;

}//5

function compareDate_threemonths(mode)
{

	var frValue, toValue,frYear, frMon, frDay,sts = 0;
	//alert("document.form1.previousdonation_date.value"+document.form1.previousdonation_date.value);
	//alert("document.form1.predate.value"+document.form1.predate.value);	
	frDate=	document.form1.previousdonation_date.value;
	toDate=document.form1.predate.value;
		
	//validating todate
	if (frDate == "" || frDate == null)
	{
		frValue = new Date;
		frYear = frValue.getYear();
		frMon = frValue.getMonth();
		frDay = frValue.getDate();
	}
	else
	{
		if (isDate(document.form1.previousdonation_date,mode) == true)
		{
			frYear = intYear;
			frMon = intMon;
			frDay = intDay;
			
			if (isDate(document.form1.predate,mode) == true)
			{
				if (frYear > intYear)
					sts = 1;
				else
				{
					if (frYear == intYear)
					{
						if (frMon > intMon)
							sts = 1;
						else
						{
							if (frMon == intMon)
							{
								if (frDay > intDay)
									sts = 1;
							}
						}
					}
				}
			}
			else
			{
				//toDate.focus();
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
		if (frDate == "" || frDate == null)	//validating current date with toDate
			getMsg(5,toDate.name); 
		else
		{
			alert("Donor not Eligible for Donation.\nThere should be gap of 3 months b/w previous and current donation date.");
			return (confirm("Do You Want To Continue"));
			
		}	
		//frDate.focus();                                                       	
		return true;
	}
	
	return true;

}//5


function CompareDatesWithVal(obj1,obj2) 
{ 
  //alert ("Inside compare date_transactions");
  //var str1 = obj1.value;
  //var str2 = obj2.value;
  var str1 = obj1;
  var str2 = obj2;
  var dt1 = str1.substring(0,2); 
  var mon1 = (str1.substring(3,6)).toUpperCase();
  var yr1 = str1.substring(7,11); 
  var dt2 = str2.substring(0,2); 
  var mon2 = (str2.substring(3,6)).toUpperCase();
  var yr2 = str2.substring(7,11); 
  //for FromDate
  var month1=monthvalue(mon1);
  //for ToDate
  var month2=monthvalue(mon2);
  var date1 = new Date(yr1, month1, dt1); 
  var date2 = new Date(yr2, month2, dt2); 
  /*var diffDate=new Date();
  diffDate = (date2 - date1);
  diffDate = ((((diffDate / 1000) / 60) / 60) / 24);
  if(diffDate<0)
  {
  alert("FromDate cannot be greater than ToDate");
  return false;
  }*/
  
  if(date2 < date1)
  {
     return false; 
  } 
  else
  {
        return true;
  }
} 


function CompareDates(obj1,obj2) 
{ 
  //alert ("Inside compare date_transactions");
  var str1 = obj1.value;
  var str2 = obj2.value;
  var dt1 = str1.substring(0,2); 
  var mon1 = (str1.substring(3,6)).toUpperCase();
  var yr1 = str1.substring(7,11); 
  var dt2 = str2.substring(0,2); 
  var mon2 = (str2.substring(3,6)).toUpperCase();
  var yr2 = str2.substring(7,11); 
  //for FromDate
  var month1=monthvalue(mon1);
  //for ToDate
  var month2=monthvalue(mon2);
  var date1 = new Date(yr1, month1, dt1); 
  var date2 = new Date(yr2, month2, dt2); 
  /*var diffDate=new Date();
  diffDate = (date2 - date1);
  diffDate = ((((diffDate / 1000) / 60) / 60) / 24);
  if(diffDate<0)
  {
  alert("FromDate cannot be greater than ToDate");
  return false;
  }*/
  
  if(date2 < date1)
  {
     return false; 
  } 
  else
  {
        return true;
  }
} 

function monthvalue(mon11) 
{
switch(mon11)
   {
   case "JAN" :
      return 00;
      
   case "FEB" :
      return 01;
      
   case "MAR" :
      return 02;
      
   case "APR" :
      return 03;
      
   case "MAY" :
      return 04;
      
   case "JUN" :
      return 05;
   
   case "JUL" :
      return 06;
      
   case "AUG" :
      return 07;
      
   case "SEP" :
      return 08;
    
   case "OCT" :
      return 09;
   
   case "NOV" :
      return 10;
   
   case "DEC" :
      return 11;

   default :
                  //alert("You have entered an invalid month");
   
   }

}


function monthValueInVar(monInNum) 
{
switch(monInNum)
   {
   case 00 :
      return "Jan";
      
   case 01 :
      return "Feb";
      
   case 02 :
      return "Mar";
      
   case 03 :
      return "Apr";
      
   case 04 :
      return "May";
      
   case 05 :
      return "Jun";
   
   case 06 :
      return "Jul";
      
   case 07 :
      return "Aug";
      
   case 08 :
      return "Sep";
    
   case 09 :
      return "Oct";
   
   case 10 :
      return "Nov";
   
   case 11 :
      return "Dec";

   default :
                  //alert("You have entered an invalid month");
   
   }

}

function checkLength(control,maxLength)
	{
	 var msgValue=control.value,msgLength=0,lf=0;
	 for(var i=0; i<msgValue.length;i++){if(msgValue[i].charCodeAt(0)==10){msgLength+=2;lf++;}else{msgLength++}}
 		if (msgLength > maxLength){
 		   //alert('Please limit your message to ' + maxLength + ' characters. There are currently ' + msg_length + '.');
 		   control.value = control.value.substring(0,(maxLength-lf));return false;}
	 return true;
	}

function checkEmployeeNumber(mode, empNoObj, hCodeObj)	
	{
		//alert("mode:  "+mode+"  empNoObj.value:  "+empNoObj.value+"  hCodeObj.value:  "+hCodeObj.value);
				
		if(empNoObj.value.length != 14)
		{
			alert("Employee Number should be of 14 digits.");
			return false;
		}
		
		if(hCodeObj.value==101)
		{	
		   if(!isNaN(empNoObj.value.substr(0,1)) && !isNaN(empNoObj.value.substr(1,1)) && !isNaN(empNoObj.value.substr(2,1)) && !isNaN(empNoObj.value.substr(3,1)) && !isNaN(empNoObj.value.substr(4,1)) && !isNaN(empNoObj.value.substr(5,1)) && isNaN(empNoObj.value.substr(6,8)) )
			{
			  alert("Employee Number format is not correct.");
			  return false;
			}
		}
				
		if(hCodeObj.value==108)
		{	
		   if(!isNaN(empNoObj.value.substr(0,1)) && !isNaN(empNoObj.value.substr(1,1)) && !isNaN(empNoObj.value.substr(2,1)) && !isNaN(empNoObj.value.substr(3,1)) && !isNaN(empNoObj.value.substr(4,1)) && !isNaN(empNoObj.value.substr(5,1)) && isNaN(empNoObj.value.substr(6,8)) )
			{
			  alert("Employee Number format is not correct.");
			  return false;
			}
		}
		
		if(hCodeObj.value==109)
		{	
		   /*
		   if(!isNaN(empNoObj.value.substr(0,1)) && isNaN(empNoObj.value.substr(1,13)) )
			{
			  alert("Employee Number format is not correct.");
			  return false;
			}
		   */
		}
		
	 return true;
	}
		
	

		
	function NameinCap(frmObj)
	{
		var index;
		var tmpStr;
		var tmpChar;
		var preString;
		var postString;
		var strlen;
		
		tmpStr = frmObj.value.toLowerCase();
		strLen = tmpStr.length;
		if (strLen > 0) {
		for (index = 0; index < strLen; index++) {
		if (index == 0) {
		tmpChar = tmpStr.substring(0,1).toUpperCase();
		postString = tmpStr.substring(1,strLen);
		tmpStr = tmpChar + postString;
		}
		else {
		tmpChar = tmpStr.substring(index, index+1);
		if (tmpChar == " " && index < (strLen-1)) {
		tmpChar = tmpStr.substring(index+1, index+2).toUpperCase();
		preString = tmpStr.substring(0, index+1);
		postString = tmpStr.substring(index+2,strLen);
		tmpStr = preString + tmpChar + postString;
		}
		}
		}
		}
		frmObj.value = tmpStr;
	}


	function initialCapital(frmObj)
	{
		var tempstr=frmObj.value;
		//alert(tempstr);
		var str = tempstr.substring(0,1).toUpperCase() +tempstr.substring(1,tempstr.length).toLowerCase();
		//document.getElementById('strHallName').value=str;
		frmObj.value=str;
		//return str;
	}

	
	function AllinCap(frmObj)
	{
		var temp=frmObj.value;
		temp=temp.toUpperCase();
		//alert(temp);
		frmObj.value=temp;
	}

	function trimValue(frmObj)
	{
		var temp=frmObj.value;
		temp=temp.trim();
		//alert(temp);
		frmObj.value=temp;
	}

function ltrim(frmObj,trimChar)
{var j=0;
for(var i=0;i<frmObj.value.length;i++){
	if(frmObj.value.charAt(i)==trimChar) j++;
	else break;	}
frmObj.value=frmObj.value.substring(j);
}

function rtrim(frmObj,trimChar)
{var j=0;
for(var i=0;i<frmObj.value.length;i++){
	if(frmObj.value.charAt((frmObj.value.length-1)-i)==trimChar) j++;
	else break;	}
frmObj.value=frmObj.value.substring(0,(frmObj.value.length)-j);
}


	function extractNumber(obj, decimalPlaces, allowNegative)
	{
		var temp = obj.value;
		
		// avoid changing things if already formatted correctly
		var reg0Str = '[0-9]*';
		if (decimalPlaces > 0) {
			reg0Str += '\\.?[0-9]{0,' + decimalPlaces + '}';
		} else if (decimalPlaces < 0) {
			reg0Str += '\\.?[0-9]*';
		}
		reg0Str = allowNegative ? '^-?' + reg0Str : '^' + reg0Str;
		reg0Str = reg0Str + '$';
		var reg0 = new RegExp(reg0Str);
		if (reg0.test(temp)) return true;
	
		// first replace all non numbers
		var reg1Str = '[^0-9' + (decimalPlaces != 0 ? '.' : '') + (allowNegative ? '-' : '') + ']';
		var reg1 = new RegExp(reg1Str, 'g');
		temp = temp.replace(reg1, '');
	
		if (allowNegative) {
			// replace extra negative
			var hasNegative = temp.length > 0 && temp.charAt(0) == '-';
			var reg2 = /-/g;
			temp = temp.replace(reg2, '');
			if (hasNegative) temp = '-' + temp;
		}
		
		if (decimalPlaces != 0) {
			var reg3 = /\./g;
			var reg3Array = reg3.exec(temp);
			if (reg3Array != null) {
				// keep only first occurrence of .
				//  and the number of places specified by decimalPlaces or the entire string if decimalPlaces < 0
				var reg3Right = temp.substring(reg3Array.index + reg3Array[0].length);
				reg3Right = reg3Right.replace(reg3, '');
				reg3Right = decimalPlaces > 0 ? reg3Right.substring(0, decimalPlaces) : reg3Right;
				temp = temp.substring(0,reg3Array.index) + '.' + reg3Right;
			}
		}
		
		obj.value = temp;
	}
	
		
function decimalCheckInAmt(obj,mantissa,exp)
 { 
	  var Text=obj.value;
	  var finaltext = new String("a");
	  finaltext = Text + '.';
	  var arr = finaltext.split("."); 
	  if (arr.length>3)
	  {
		 alert("Please enter data in correct format. Eg. 10.12");
		 obj.value = ''; 
		 obj.focus();
		 return false;
	  } 
	  if (arr[0].length>mantissa)
	  {
		 alert("Please enter data in correct format. \nOnly " + mantissa + " characters for Mantissa.\nEg. 10.12 -- 10 is Mantissa and .12 is Precision.");
		 obj.value = ''; 
		 obj.focus();
		 return false;
	  }
	  if (arr[1].length>exp)
	  {
		  alert("Please enter data in correct format. \nPrecision only " + exp + " decimal places.\nEg. 10.12 -- 10 is Mantissa and .12 is Precision.");
		  obj.value = ''; 
		  obj.focus();
		  return false;
	  }
	  return true; 
 } 
 
 function checkTime(obj,hours,mins,secs,format,rowNum)
 { 
	if(format=="HH:MM")
	{	  
		  var Text=obj.value;
		  var finaltext = new String("a");
		  finaltext = Text + ':';
		  var arr = finaltext.split(":"); 
		  if (arr.length>3)
		  {
			 alert("Please enter time in correct format. Eg. 10:12");
			 obj.value = ''; 
			 obj.focus();
			 return false;
		  } 
		  if (arr[0].length>hours)
		  {
			 alert("Please enter time in correct format. \nOnly " + hours + " characters for Hours.\nEg. 10:12 -- 10 is Hours and :12 is Minutes.");
			 obj.value = ''; 
			 obj.focus();
			 return false;
		  }		  
		  if (arr[1].length>mins)
		  {
			  alert("Please enter time in correct format. \nOnly " + mins + " characters for Minutes.\nEg. 10:12 -- 10 is Hours and :12 is Minutes.");
			  obj.value = ''; 
			  obj.focus();
			  return false;
		  }
		  if (parseInt(arr[1])>59)
		  {
			 alert("Please enter time in correct format. \n Minutes cannot be greater then 59.");
			 obj.value = ''; 
			 obj.focus();
			 return false;
		  }
	  }	 
	  else if(format=="HH:MM:SS")
	  {	  
		  var Text=obj.value;
		  var finaltext = new String("a");
		  finaltext = Text + ':';
		  var arr = finaltext.split(":"); 
		  if (arr.length>3)
		  {
			 alert("Please enter time in correct format. Eg. 10:12");
			 obj.value = ''; 
			 obj.focus();
			 return false;
		  } 
		  if (arr[0].length>hours)
		  {
			 alert("Please enter time in correct format. \nOnly " + hours + " characters for Hours.\nEg. 10:12:14 -- 10 is Hours and :12 is Minutes and :14 is Seconds.");
			 obj.value = ''; 
			 obj.focus();
			 return false;
		  }
		  if (arr[1].length>mins)
		  {
			  alert("Please enter time in correct format. \nOnly " + mins + " characters for Minutes.\nEg. 10:12:14 -- 10 is Hours and :12 is Minutes and :14 is Seconds.");
			  obj.value = ''; 
			  obj.focus();
			  return false;
		  }
		  if (parseInt(arr[1])>59)
		  {
			 alert("Please enter time in correct format. \n Minutes cannot be greater then 59.");
			 obj.value = ''; 
			 obj.focus();
			 return false;
		  }
		  if (arr[2].length>secs)
		  {
			  alert("Please enter time in correct format. \nOnly " + secs + " characters for Seconds.\nEg. 10:12:14 -- 10 is Hours and :12 is Minutes and :14 is Seconds.");
			  obj.value = ''; 
			  obj.focus();
			  return false;
		  }
		  if (parseInt(arr[2])>59)
		  {
			 alert("Please enter time in correct format. \n Seconds cannot be greater then 59.");
			 obj.value = ''; 
			 obj.focus();
			 return false;
		  }
	   }	   
	  return true; 
 } 

//Added on 10-12-2010 by AM	
function chkDisplayJs(fldName)
{ var _i=1;	
//alert("inside chkDisplayJs")	
	if(document.getElementById(fldName).checked==true) { while(1==1) {
			var _divName=fldName+"Div"+_i; if(document.getElementById(_divName)==null) break;
			else document.getElementById(_divName).style.display="inline";			
			_i++; }
	}
	else { while(1==1)	{
			var _divName=fldName+"Div"+_i; if(document.getElementById(_divName)==null) break;
			else document.getElementById(_divName).style.display="none";
			_i++;} }
}

//Added on 10-12-2010 by AM
function chkDisplayMultirowJs(fldName,rowNum)
{ var _i=1;
//alert("inside chkDisplayMultirowJs")	
	if(document.getElementsByName(fldName)[rowNum].checked==true) {	while(1==1) {
			var _divName=fldName+"Div"+_i; if(document.getElementsByName(_divName)[rowNum]==null) break;
			else document.getElementsByName(_divName)[rowNum].style.display="inline";			
			_i++; }
	}
	else { while(1==1) {
			var _divName=fldName+"Div"+_i; if(document.getElementsByName(_divName)[rowNum]==null) break;
			else document.getElementsByName(_divName)[rowNum].style.display="none"; 
			_i++; } }
}	

//Added on 10-12-2010 by AM
function toggleHideOrShowInMultirow(transMode, trId, displayFlagId, tPlImageId, tMiImageId, rowNum, noOfRows, funcMode, callMode)
{ //alert("inside toggleHideOrShowInMultirow");
	//var elemName=trId;
	//var curStatus=document.getElementById((trId+1)).style.display;
	//alert("curStatus- "+curStatus);
	var displayFlag=document.getElementsByName(displayFlagId)[rowNum].value;
	var newstatus="",plImage="",miImage="",disFlagVal="";
	//if(callMode!="99") {if(curStatus=="none") {newstatus="";plImage="none";miImage="inline";}	else {newstatus="none";plImage="inline";miImage="none";}}
	//else {if(curStatus=="none") {newstatus="none";plImage="inline";miImage="none";}	else {newstatus="";plImage="none";miImage="inline";}}
	if(callMode!="99") {if(displayFlag=="1") {newstatus="";plImage="none";miImage="inline";disFlagVal="2";}	else {newstatus="none";plImage="inline";miImage="none";disFlagVal="1";}}
	else {if(displayFlag=="1") {newstatus="none";plImage="inline";miImage="none";disFlagVal="1";}	else {newstatus="";plImage="none";miImage="inline";disFlagVal="2";}}
	for(var i=1;i<=noOfRows;i++){ if(funcMode==1){if(i==1){}else{ document.getElementById((trId+i)).style.display=newstatus;}} else { document.getElementById((trId+i)).style.display=newstatus;} }
	document.getElementsByName(displayFlagId)[rowNum].value=disFlagVal;
	document.getElementById(tPlImageId).style.display=plImage; document.getElementById(tMiImageId).style.display=miImage;									
}

function dayOnDate(obj1) 
{
	var str1 = obj1.value;
  	var dt1 = str1.substring(0,2); 
  	var mon1 = (str1.substring(3,6)).toUpperCase();
  	var yr1 = str1.substring(7,11); 
  	var month1=monthvalue(mon1);
  	var date1 = new Date(yr1, month1, dt1); 
  	var day= date1.getDay()
	return day;
}

function getValidateStrRE(index,retF)
{
	var str="", msg1="";
	
	switch(index)
	{
		case 1:		//for validating email
			str = /^(\w+[\-\.])*\w+@(\w+\.)+[A-Za-z]+$/;
			break;
			
		case 2:	//for validating amount without decimal
			str = /^[1-9]/;
			break;	
			
		case 3:	//for validating percentage
			//str = /^(100(\.0{1,2})? | [1-9]?\d(\.\d{1,2})?)$/;
			str = /^(100(\.0?0?)?|[1-9]?\d(\.\d?\d?)?)$/;
			
			//str = /^100(\.(0){0,2})?$|^([1-9]?[0-9])(\.(\d{0,2}))?\%$/;
			msg1 = "Maximum Value Allowed is 100.00";
			break;
						
	}	//end of Switch statement
	if(retF==0) return str; else return msg1;	
}

function validateDataRE(e,index,msgF,msg)
{
  var str=e.value;	
  var re=getValidateStrRE(index,0);
  var msg1=getValidateStrRE(index,1);
  if ((re).test(str)){ return true;}	   
  else {e.value=str.substring(0,str.length-1); if(msgF=='1'){alert(msg1);}else if(msgF=='2'){alert(msg);} return false;}
}

function validateDataRecRE(e,index,msgF,msg)
{
	var retValF="1";	
	var re=getValidateStrRE(index,0);
	var msg1=getValidateStrRE(index,1);
	if(e.value.length>0){
	for(var i=1;i<=e.value.length;i++)
	{var tStr=e.value.substring(0,i);
	if ((re).test(tStr)){retValF=1;}	   
	else {e.value=tStr.substring(0,tStr.length-1); retValF=2; break;
	}}}
	if(retValF=="1") return true;
	else {if(msgF=='1'){alert(msg1);}else if(msgF=='2'){alert(msg);} return false;}
	
}


