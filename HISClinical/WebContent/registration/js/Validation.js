
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
			str = "1234567890-";
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
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .()";
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
	
	//alert(msgStr); 
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
		obj = document.form1.elements[checkArray[i]];
		type = obj.type;
			
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
				if(obj.value == 0)
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
	alert("inside submit")
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


function BeforeSubmit(form1)
{
        var FLAG=false;
	    blankCnditn1 = document.form1.from_date.value=='';
	    blankCnditn2 = document.form1.to_date.value=='';
		var dtResult = compareDate(form1.from_date, form1.to_date, 0);
		
		
		if(dtResult)	// && !slctCnditn1 && !slctCnditn2)
	{
		FLAG=true;
	}
	else if(blankCnditn1 || blankCnditn2)
    {
		alert('Please select date from date fields')
	}
	return FLAG;
    }
function compareDate(from_date,to_date,mode)
{// alert(to_date);
	var frValue, toValue,frYear, frMon, frDay,sts = 0;
	if (from_date == "" || from_date == null)
	{
		frValue = new Date;
		frYear = frValue.getYear();
		frMon = frValue.getMonth();
		frDay = frValue.getDate();
	}
	else
	{
		if (isDate(from_date,mode) == true)
		{
			frYear = intYear;
			frMon = intMon;
			frDay = intDay;
			if (isDate(to_date,mode) == true)
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
				to_date.focus();
				return false;
			}
		}
		else
		{
			from_date.focus();
			return false;
		}
	}

	if (sts == 1)
	{
		if (from_date == "" || from_date == null)		//validating current date with toDate
			getMsg(5,to_date.name);
		else
			//alert(frDate.name + " is greater than " + toDate.name);
			alert("From Date cannot be greater then To Date" );
		    from_date.focus();
		return false;
	}

  return true;

}

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



	//alert(msgStr);

	return;

}

