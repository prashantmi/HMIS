<!--
//alert('inside training script');
/**
	List of functions in this file

 1>	function openPopup()
 2>	function init()
 3>	function alphaOnly(e)
 4>	function numericOnly(e)
 5>	function alphaNumericOnly(e)
 6>	function upperCaseOnly(e,field)
 7>	function validateFun()
 8>	function submitFun()
 9>	function checkCounter(obj)
10>	function submitpage(mode)
11>	function add()
12>	function edit()
13>	function del()
14>	function view()
15>	function cancel(filename)
16>	function initialFocus(field)
17>	function checkAsRadio(field)
18> 	function save()
19> 	function update()
20> 	function saveadd()
21>	function datecheck()
22>	dateDiff(dateform) // dateform is the name of the form containing datePicker

*/

/**
	global variables
*/
var intDay, intMon, intYear;
var messages = new Array();

messages[0] = "Please select record(s) first!";
messages[1] = "Please select a record first!";
messages[2] = "Cannot view multiple records at a time(select only one record)!";
messages[3] = "Cannot edit multiple records at a time(select only one record)!";

myPopup = '';
var oDP = null;


/***************************************-begining of functions-******************************/


/*1*/
	function openPopup()
	{

		var url = "popup_empno.jsp?reqPruCode=" + document.form1.hReqPruCode.value;

		myPopup = window.open(url,'popupWindow','width=800,height=600,scrollbars=yes');
		if (!myPopup.opener)
			 myPopup.opener = self;
	}

/***************************************-end of function-***********************************/

/*2*/
	function init()
	{
	   oDate = new Date();
	   oDP   = new frameDatePicker.DatePicker("divDatePicker", oDate.getFullYear()-60, oDate.getFullYear()+60);

	   // If you want Sunday as first day of the week use this construction call instead:
	   // oDP = new frameDatePicker.DatePicker("divDatePicker", oDate.getFullYear()-2, oDate.getFullYear()+5, true);

	   // Use another init year/month than todays. 0=Jan, 11=Dec
	   //oDP.setInitDate(2003, 0);
	}

/***************************************-end of function-***********************************/

/*3*/
	function alphaOnly(e)
	{
		var key;
		var keychar;

		if (window.event)
		   key = window.event.keyCode;
		else if (e)
		   key = e.which;
		else
		   return true;
		keychar = String.fromCharCode(key);

		keychar = keychar.toUpperCase();

		// control keys
		if ((key==null) || (key==0) || (key==8) ||
			(key==9) || (key==13) || (key==27) )
		   return true;

		// alphas and space
		else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ").indexOf(keychar) > -1))
		   return true;
		else
		   return false;
	}//end of alphaOnly

/***************************************-end of function-***********************************/

/*4*/
	function numericOnly(e)
	{
		var key;
		var keychar;

		if (window.event)
		   key = window.event.keyCode;
		else if (e)
		   key = e.which;
		else
		   return true;
		keychar = String.fromCharCode(key);
		keychar = keychar.toLowerCase();

		// control keys
		if ((key==null) || (key==0) || (key==8) ||
			(key==9) || (key==13) || (key==27) )
		   return true;

		// numbers
		else if ((("0123456789").indexOf(keychar) > -1))
		   return true;
		else
		   return false;
	}//end of numericOnly

/***************************************-end of function-***********************************/

/*5*/
	function alphaNumericOnly(e)
	{
		var key;
		var keychar;

		if (window.event)
		   key = window.event.keyCode;
		else if (e)
		   key = e.which;
		else
		   return true;
		keychar = String.fromCharCode(key);
		keychar = keychar.toLowerCase();

		// control keys
		if ((key==null) || (key==0) || (key==8) ||
			(key==9) || (key==13) || (key==27) )
		   return true;

		// alphas and numbers
		else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789").indexOf(keychar) > -1))
		   return true;
		else
		   return false;
	}//end of alphaNumericOnly

/***************************************-end of function-***********************************/

/*6*/
	function upperCaseOnly(e,field)
	{
		var key;
		var keychar;

		if (window.event)
		   key = window.event.keyCode;
		else if (e)
		   key = e.which;
		else
		   return true;

		keychar = String.fromCharCode(key);


		// control keys
		if ((key==null) || (key==0) || (key==8) ||
		    (key==9) || (key==13) || (key==27) )
		   return true;

		// alphas and numbers
		else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ").indexOf(keychar) > -1))
		{
			field.value += keychar.toUpperCase();
			return false
		}
		else
		   return false;
	}

/***************************************-end of function-***********************************/

/*7*/
	function validateFun(mode)
	{

		var len = document.form1.elements.length ;
		var i,type ;

		for ( i = 0 ; i < len ; i++ )
		{

			type = document.form1.elements[i].type;

			switch(type)
			{
				case "text":
						if(document.form1.elements[i].value == null || document.form1.elements[i].value == "")
						{

							alert(document.form1.elements[i].name + " is empty!");
							return;
						}
						break;


				case "select-one":
						if(document.form1.elements[i].value == 0)
						{
							alert(document.form1.elements[i].name + " is not selected");
							return;
						}
						break;
			}
		}

		submitpage(mode);

	return;
	}//end of validateFun

/***************************************-end of function-***********************************/

/*8*/
	function submitFun()
	{
			document.form1.submit();
	}//end of validateFun

/***************************************-end of function-***********************************/

/*9*/
	function checkCounter(obj)
	{

		if(obj.checked)
			document.form1.hcounter.value++;
		else
			document.form1.hcounter.value--;

		document.form1.hprimary.value=obj.value;

	}//end of checkCounter

/***************************************-end of function-***********************************/

/*10*/
	function submitpage(mode)
	{
		document.form1.hmode.value=mode;
		document.form1.submit();

	}//end of submitpage

/***************************************-end of function-***********************************/

/*11*/
	function add()
	{
		submitpage("ADD");

	}//end of add

/***************************************-end of function-***********************************/

/*12*/
	function edit()
	{

		len=document.form1.hcounter.value;

		if(len!=0)
			if(len>1)
				alert(messages[3]);
			else
				submitpage("EDIT");
		else
			alert(messages[1]);

	}//end of edit

/***************************************-end of function-***********************************/

/*13*/
	function del()
	{

		var len=document.form1.hcounter.value;

		if(len!=0)
			submitpage("DELETE");
		else
			alert(messages[0]);

	}//end of del

/***************************************-end of function-***********************************/

/*14*/
	function view()
	{

		len=document.form1.hcounter.value;

		if(len!=0)
			if(len>1)
				alert(messages[2]);
			else
				submitpage("VIEW");
		else
			alert(messages[1]);

	}//end of view

/***************************************-end of function-***********************************/

/*15*/
	function cancel(filename)
	{
		document.form1.action=filename;
		document.form1.submit();

	}//end of cancel

/***************************************-end of function-***********************************/

/*16*/
	function initialFocus(field)
	{
		field.focus();
	}

/***************************************-end of function-***********************************/

/*17*/
	function checkAsRadio(obj,checkArray,j)
	{

		theArray	= 	document.all.tags("td");
		fontArray	= 	document.all.tags("font");

		if(obj.checked)
		{
			for(i=0;i<checkArray.length;i++)
			{
				if(checkArray[i].checked && obj!=checkArray[i])
				{

					checkArray[i].checked = false;

					if(i%2==0)
						bgColor="#ffffff";
					else
						bgColor="#eeeeee";

					theArray[i*3+4+8].bgColor=bgColor;
					theArray[i*3+5+8].bgColor=bgColor;

					fontArray[i*2+4].color	=	"black";
					fontArray[i*2+1+4].color	=	"black";



					break;
				}
			}
		}
		else
			obj.checked=true;

		theArray[j+1+8].bgColor="#004492";
		theArray[j+2+8].bgColor="#004492";

		fontArray[((j/3)*2)-1+4].color	=	"white";
		fontArray[((j/3)*2)-2+4].color	=	"white";

	}

/***************************************-end of function-***********************************/

/*18*/
	function save()
	{
		submitpage("SAVE");

	}//end of edit

/***************************************-end of function-***********************************/

/*19*/
	function update()
	{
		submitpage("UPDATE");

	}//end of edit

/***************************************-end of function-***********************************/

/*20*/
	function saveadd()
	{
		submitpage("SAVEADD");

	}//end of edit



/**********************************************************************************************/

/*****************************************************************************************/
/*21*/

function submitForm(dateform)
{
	dateform.submit(dateform);
}

/*****************************************************************************************/
/*22*/
function initilizeVar()
{
	intDay = "";
	intMon = "";
	intYear = "";
}

/*******************************************************************************************************************/
/*23*/


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
}

/*24*/

//this function converts the month(in string) into month(in integer)
function getMonthInt(str)
{
	var month = -1;

	switch(str.toUpperCase())
	{
		case "JAN":
			month = 1;
			break;
		case "FEB":
			month = 2;
			break;
		case "MAR":
			month = 3;
			break;
		case "APR":
			month = 4;
			break;
		case "MAY":
			month = 5;
			break;
		case "JUN":
			month = 6;
			break;
		case "JUL":
			month = 7;
			break;
		case "AUG":
			month = 8;
			break;
		case "SEP":
			month = 9;
			break;
		case "OCT":
			month = 10;
			break;
		case "NOV":
			month = 11;
			break;
		case "DEC":
			month = 12;
			break;
	}
	return month;
}
/*25*/

//this function returns no of days in a month for the specified year
function DaysInMonth(mon, year)
{
	var retVal;

	retVal = 31;
	if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {retVal = 30;}
	if (mon == 2) {retVal = daysInFebruary(year);}
   	return retVal;
}


/***************************************-end of function-***********************************/
/**************/
/*26*/

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
				//alert(intMon);

				if (intMon >= 0 && intMon <= 12)
				{
					if (intYear >= 1900 && intYear <= 2100)
					{
						if (intDay > 0 && intDay <= DaysInMonth(intMon, intYear))
							return true;
					}//end if
				}//endif
			}//endif
		}//endif
	}//endif
	return false;
}

/**/
/***************************************-end of function-***********************************/


/*27*/
/* Function For Removing spaces */
function removeStrSpace(str)
{
	//alert('inside remove')
	var j;
	var len = str.length;
	var retStr ="";

	for(j = 0;j <= len;j++)
	{
		if(str.charAt(j) != " ")
			retStr += str.charAt(j);
	}
	return retStr;
}
/***************************************-end of function-***********************************/
/*28*/

//this function finds the seperator used for seperating the date
function getSeperator(dtStr)
{
	//alert('inside seperator')
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
}
/***************************************-end of function-***********************************/
/*29*/

function isDate(theField,mode)
{

	var dtStr = removeStrSpace(theField.value);
	var seprator = "";

	if (dtStr == "") return false;
	seprator = getSeperator(dtStr)		//function that returns seperator
	if (seprator != "")
	{

	//alert('one 1 ');
	var x = parseDate(dtStr,seprator,mode)
	//alert('x= ' + x);
		if (x == true)
		{
			//alert('inside 2')
			theField.value = dtStr;
			return true;
		}
	}
	//alert('inside idate')
	getMsg(3,theField.name);	//display error message
	theField.focus();
	return false;
}
/*******************************************************************************************************************/
/*30*/
//this function compares two date. pass blank to frDate if you want to compare toDate with current Date
//this function accepts the object. date should be dd-mm-yyyy format if mode = 1 otherwise dd-mmm-yyyy

function compareDate(frDate,toDate,mode)
{
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
			alert("From Date is greater than To Date...!" );

		frDate.focus();
		return false;
	}

	return true;

}

/***************************************-end of function-***********************************/
/*31*/
function submitForm()
{
	//alert('inside submitForm')
	document.SearchForm.submit();
}//end of validateFun

/***************************************-end of function-***********************************/
/*32*/

	function BeforeSubmit(dateform)
{


	

	blankCnditn1=dateform.Start_Range.value == '';
	blankCnditn2=dateform.End_Range.value == '';

	//indx1 = document.ReportForm.dept.selectedIndex;
	//slctCnditn1 = document.ReportForm.dept.options[indx1].text=="Select Department";
	
	//var choice=0;
	
	//choice=dateform.r1.value;
		
	var dtResult = compareDate(dateform.Start_Range, dateform.End_Range, 0);
	
return dtResult;


	/*if(dtResult )   //&& (!slctCnditn1) )  
	{
		
		         document.SearchForm.action=".jsp";
				 document.SearchForm.submit();
		
	}*/
		/*if(datecheck()==true )
		{
			
			if(document.ReportForm.r1[0].checked==true) 
			{
				if(check()==true)  
			      submitForm();
			}	  
			
			if(document.ReportForm.r1[1].checked==true) 
			{
				if(checkmonth()==true)  
			      */
			      //submitForm();
			//}	  
			
			
			/*if(document.ReportForm.r1[2].checked==true) 
			{
				if(checkyear()==true)  
			      submitForm();
			}	  
			*/
		//}	
		
					
					  
	//}	
		
	/*else if(blankCnditn1 || blankCnditn2)
		alert('One or more date fields are blank...!')

	else if(slctCnditn1)
		alert('One or more selections are not made...!');
	  	
	*/
		
}

function datecheck()

{

	var frValue, toValue,frYear, frMon, frDay, fromYear, index, sts = 0;

		frValue = new Date;  //system date
		frYear = frValue.getYear();
		frMon = frValue.getMonth();
		frMon=frMon+1;
		frDay = frValue.getDate();
		
		var a=document.ReportForm.End_Range.value;
		var str= ""+frDay+"/"+frMon+"/"+frYear+"";
		var sysdate=new Date(str);
		
		
		

var x= a.substring(0,2);			
var y= a.substring(3,6);
var z= a.substring(7,11);

switch(y)
	{
		case "Jan":
			index=01;
			break;

		case "Feb":
			index=02;
    		break;

		case "Mar":
			index=03;
			break;

		case "Apr":
			index=04;
			break;

		case "May":
			index=05;
			break; 

		case "Jun":
			index=06;
			break;

		case "Jul":
			index=07;
			break;

		case "Aug":
			index=08;
			break;
			
		case "Sep":
			index=09;
			break;
			
		case "Oct":
			index=10;
			break;
			
		case "Nov":
			index=11;
			break;
			
		case "Dec":
			index=12;
			break;



	}	
	
	var str1= ""+x+"/"+index+"/"+z+"";
	var mydate=new Date(str1);
	var result=sysdate.valueOf()-mydate.valueOf();
	//alert("result"+result);
	var final=true;
	
	if(x>frDay && index==frMon && result <0 )  
	{
		 alert(" Date should be less than the current date");
		 final=false;
	}	 
	
	 if(x<=frDay && index> frMon && result <0  )  
	 {
		alert("  Date should be less than the current date");
	    final=false;
	 }
	 
	  
	 if(x<=frDay && index> frMon && result >0 )
	 {
		alert("  Date should be less than the current date");
	    final=false;
	 }
	 
	 if(x>frDay && index> frMon && result <0 )  
	 {
	  alert("  Date should be less than the current date");
      final=false;	
	 } 	
	 
return final;
}


function check()

{
var final=true;
var a = document.ReportForm.Start_Range.value;
var b = document.ReportForm.End_Range.value;


var x= a.substring(0,2);				
var y= a.substring(3,6);
var s=number(y);
var z= a.substring(7,11);

var x1= b.substring(0,2);
var y1= b.substring(3,6);
var s1=number(y1);
var z1= b.substring(7,11);

var i,j,k,m,total,diff,sum=0;

if(z<z1)  //for the case of 01-Dec-2003  to 01-Feb-2004 for different years
  {
  var w=s1;
 
  for(i=1;i<=s1+1;i++)
	{ 
	total=DaysInMonth(w,z1);
	sum=sum+total;
	w=w-1;
	}

 j=sum-x;
 m=DaysInMonth(y1,z);
 diff=m-x1;

 k=j-diff;

  if(k>30)
   {
	alert("Date Greater than 30 days");
	final=false;
   }
 

} // end of if


if(!(z<z1))
{	
	for(i=s1;i>=s;i--)
	{ 
	total=DaysInMonth(i,z);
	sum=sum+total;
	}
	
j=sum-x;
m=DaysInMonth(s1,z);
diff=m-x1;
k=j-diff;
//alert("k===== "+k);
	if(k>30)
	{
	alert("Date Greater than 30 days");
	final=false;
	}
	
	
}
return final;	
}		




function checkmonth()
{

var final=true;
var a = document.ReportForm.Start_Range.value;
var b = document.ReportForm.End_Range.value;

var x= a.substring(0,2);				
var y= a.substring(3,6);
var s=number(y);
var z= a.substring(7,11);



var x1= b.substring(0,2);
var y1= b.substring(3,6);
var s1=number(y1);
var z1= b.substring(7,11);

var i,j,k,m,n,total,diff,sum=0;
var m1,me=0;

if(z<z1)  //for the case of 01-Dec-2003  to 01-Feb-2004 for different years

  {

  var w=s1;
  var count=s1-s;	
  for(i=s1;i>=1;i--) 
	{ 
	total=DaysInMonth(i,z1);
	sum=sum+total;
	w=w-1;
	}

 for(i=s;i<=12;i++)
{
n=DaysInMonth(i,z);
me=me+n;
}
sum=sum+me;
m=DaysInMonth(s,z);
j=m-x+1;

m1=DaysInMonth(s1,z1);
diff=m1-x1;
k=(sum-x+1)-diff;

var l=2;
var days,days1=0;
days=DaysInMonth(l,z);
days1=DaysInMonth(l,z1);

if(days==29 || days1==29 )
{
  if(k>366)

   {

	alert("Date exceeds 12 months");

	final=false;

   }

} 

else if(k>365)
  {
    
	alert("Date exceeds 12 months");

	final=false;

  }

}

if(!(z<z1))

{	

	for(i=s1;i>=s;i--)

	{ 

	total=DaysInMonth(i,z);

	sum=sum+total;

	}

	

m=DaysInMonth(s,z);
j=m-x+1;

m1=DaysInMonth(s1,z1);
diff=m1-x1;
k=(sum-x+1)-diff;
var l=2;
var days=0;
days=DaysInMonth(l,z);
if(days==29)
{
  if(k>366)

   {

	alert("Date exceeds 12 months");

	final=false;

   }

} 

else if(k>365)
  {
    
	alert("Date exceeds 12 months");

	final=false;

  }

	

}

return final;	

}		



function checkyear()
{

var final=true;
var a = document.ReportForm.Start_Range.value;
var b = document.ReportForm.End_Range.value;

var x= a.substring(0,2);				
var y= a.substring(3,6);
var s=number(y);
var z= a.substring(7,11);



var x1= b.substring(0,2);
var y1= b.substring(3,6);
var s1=number(y1);
var z1= b.substring(7,11);

var i,j,k,m,n,total,diff,sum=0;
var m1,yr,me=0;
var tot_yrs=1;
var count_feb=0;
var digit=1825;
var n1,me1=0;

if(z<z1)  //for the case of 01-Dec-2003  to 01-Feb-2004 for different years

 {

  var w=s1;
  var count=s1-s;	
  
  for(i=s1;i>=1;i--) //for  to date
	{ 
	total=DaysInMonth(i,z1);
	sum=sum+total;
	w=w-1;
	}

 for(i=s;i<=12;i++)  //for from date
   {
	n=DaysInMonth(i,yr);
	me=me+n;
   }

var new_z=0;
new_z=parseInt(z);
new_z=new_z+1;

 for(yr=new_z;yr<z1;yr++)  //for in-between years
   {

     for(i=1;i<=12;i++)  
      {
		n1=DaysInMonth(i,yr);
		me1=me1+n1;

      }

tot_yrs++;

   }
sum=sum+me+me1;
m=DaysInMonth(s,z);    
j=m-x+1;
m1=DaysInMonth(s1,z1);
diff=m1-x1;
k=(sum-x+1)-diff;

var l=2;
var d,days=0;
yr=z;
for(d=yr;d<=z1;d++)  
{
days=DaysInMonth(l,d);
 if(days==29)
 {
  count_feb++;
 }
} 

digit=digit+count_feb;
 
  if(k>digit)
   {
	alert("Date exceeds 5 years");
	final=false;

   }


 if(k>1825)
  {
    alert("Date exceeds 5 years");
	final=false;

  }

}


return final;	

}		




	




	
function number(y)
{
var index="";
switch(y)
	{
		case "Jan":
			index=1;
			break;

		case "Feb":
			index=2;
    		break;

		case "Mar":
			index=3;
			break;

		case "Apr":
			index=4;
			break;

		case "May":
			index=5;
			break; 

		case "Jun":
			index=6;
			break;

		case "Jul":
			index=7;
			break;

		case "Aug":
			index=8;
			break;
			
		case "Sep":
			index=9;
			break;
			
		case "Oct":
			index=10;
			break;
			
		case "Nov":
			index=11;
			break;
			
		case "Dec":
			index=12;
			break;

	}	//end of switch statement
return index;
}










//returns day in feb month for specified year
function daysInFebruary (year)
{
	return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}



function changemode()
{
	//alert("inside changemode")
	//alert("1>"+document.ReportForm.vhmode.value )

	if(document.ReportForm.vhmode.value == "OLD")
		document.ReportForm.vhmode.value = "NEW"
	

	//alert("2>"+document.ReportForm.vhmode.value )
	
}

function submitAgeRptForm()
{
	//alert('inside submitForm')
	document.form1.submit();
}

function BeforeAgeRptSubmit(form1)
{
	//alert('inside before submit')
	
	blankCnditn1=form1.Start_Range.value == '';
	blankCnditn2=form1.End_Range.value == '';
	
	
	var dtResult = compareDate(form1.Start_Range, form1.End_Range, 0);
	
	if(dtResult && (!slctCnditn1) && (!slctCnditn2))
		submitAgeRptForm();
	else if(blankCnditn1 || blankCnditn2)
		alert('One or more date fields are blank...!')
}





/***************************************-end of function-***********************************/


/***************************************-end of file-***************************************/

//-->