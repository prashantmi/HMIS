/*
DATE:	8-APR-2004


function goHome()
function submitForm(selectObj)

function validateNameItems(objArray)
function isAnyOneItem(objArray)
function validateItem(obj)
function inElements(objArray,obj)

function dateValidator(obj,seperator)
function isMonth(val)
function getDays(mon,yyy)
function sysBefore(obj,seperator)
function sysAfter(obj,seperator)
function ageInserter(e,obj)
function validaeItemWithOutAlert(item)
*/


function goHome()
{
	document.forms[0].action="index.jsp";
	document.forms[0].submit();
}

function submitForm(selectObj)
{
	selectObj.form.submit();
}//End of submitForm

function setMode(mode)
{
	if(validateNameItems(nameArray))
	{
		document.form1.hmode.value=mode;
		document.form1.submit();
	}
}

function validateNameItems(objArray)
{
	var flag=true;

	for(var i=0;i<document.forms[0].elements.length;i++)
	{
		if(inElements(objArray,document.forms[0].elements[i]))
		{
			flag=validateItem(document.forms[0].elements[i]);
			if(flag==false)
				break;
		}
	}

	//alert("Validation "+flag);

	return flag;
}
//End of validateNameItems

function isAnyOneItem(objArray)
{
	var flag=true;
	var alertMsg="";

	for(var i=0;i<document.forms[0].elements.length;i++)
	{
		if(inElements(objArray,document.forms[0].elements[i]))
		{
			alertMsg+=document.forms[0].elements[i].name+" ";
			flag=validaeItemWithOutAlert(document.forms[0].elements[i]);
			if(flag)
				break;
		}
	}

	if(!flag)
		alert("Please Insert the Into Any of "+alertMsg);

	return flag;
}//End of isAnyOneItem(objArray)


function validateItem(obj)
{
	var flag=true;

	switch(obj.type)
	{
		case "checkbox"		:if(obj.value=="-1" || obj.value=="" || obj.value==null)
							 {
								 alert(obj.name+" Should Not Left Blank!");
								 flag=false;
							 }
							 break;

		case "radio"		:if(obj.value=="-1" || obj.value=="" || obj.value==null)
							 {
								 alert(obj.name+" Should Not Left Blank!");
								 flag=false;
							 }
							 break;

		case "text"			:if(obj.value=="-1" || obj.value.replace(/\s*/,"")=="" || obj.value==null)
							 {
								 alert(obj.name+" Should Not Left Blank!");
								 flag=false;
							 }
							 break;


		case "textarea"		:if(obj.value=="-1" || obj.value.replace(/\s*/,"")=="" || obj.value==null)
							 {
								 alert(obj.name+" Should Not Left Blank!");
								 flag=false;
							 }
							 break;

		case "select-one"	:if(obj.options[obj.selectedIndex].value=="-1" || obj.options[obj.selectedIndex].value=="" || obj.options[obj.selectedIndex].value==null)
							 {
								if (obj.name=="mstTable"){obj.name="master table";}
								if (obj.name=="colName"){obj.name="Column Name";}
								if (obj.name=="dispCol"){obj.name="Display Column";}
								 alert( "First Select the value of "+obj.name);
								 flag=false;
							 }
							 break;

		case "hidden"		:if(obj.value=="-1" || obj.value=="" || obj.value==null)
							 {
								 alert(obj.name+" Should Not Left Blank!");
								 flag=false;
							 }
							 break;

	}
	return flag;
}
//End of validaeItem(item)

function inElements(objArray,obj)
{
	var flag=false;

	for(var i=0;i<objArray.length;i++)
	{
		if(objArray[i]==obj.name)
		{
			flag=true;
			break;
		}
	}
	return flag;
}
//End of inElements(objArray,obj)







function dateValidator(obj,seperator)
{
	var dateSplit=obj.value.split(seperator);
	var flag=true;

	if(dateSplit.length!=3)
	{
		alert("Use format DD"+seperator +"MM"+seperator+"YYYY");
		flag=false;
	}
	else if(isNaN(dateSplit[0]) || isNaN(dateSplit[2]))
	{
		alert("Check Either Date or Year is not Correctly Entered");
		flag=false;
	}
	else if(!isMonth(dateSplit[1]))
	{
		alert("Month Is Not Correctly Entered!");
		flag=false;
	}
	else if(dateSplit[2].length!=4)
	{
		alert("Enter Full Year");
		flag=false;
	}
	else
	{
		if(dateSplit[0]>=0 && dateSplit[0]<=getDays(dateSplit[1],dateSplit[2]))
				flag=true;
		else
		{
			alert("Date Is Not Correctly Entered");
			flag=false;
		}

	}

	return flag;

}//End od dateValidator


function isMonth(val)
{
	switch(val.toUpperCase())
	{
		case "JAN":return true;
					break;

		case "FEB":return true;
					break;

		case "MAR":return true;
					break;

		case "APR":return true;
					break;

		case "MAY":return true;
					break;

		case "JUN":return true;
					break;

		case "JUL":return true;
					break;

		case "AUG":return true;
					break;

		case "SEP":return true;
					break;

		case "OCT":return true;
					break;

		case "NOV":return true;
					break;

		case "DEC":return true;
					break;
	}
	return false;
}//End of isMonth(val)


function getDays(mon,yyy)
{
	switch(mon.toUpperCase())
	{
		case "JAN":return 31;
					break;

		case "FEB":if(yyy%400==0 || (yyy%400!=0 && yyy%4==0))
						return 29;
					else
						return 28;
					break;

		case "MAR":return 31;
					break;

		case "APR":return 30;
					break;

		case "MAY":return 31;
					break;

		case "JUN":return 30;
					break;

		case "JUL":return 31;
					break;

		case "AUG":return 31;
					break;

		case "SEP":return 30;
					break;

		case "OCT":return 31;
					break;

		case "NOV":return 30;
					break;

		case "DEC":return 31;
					break;
	}
	return false;
}//End of isMonth(val)


function sysBefore(obj,seperator)
{
	var flag=true;
	var mon=new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");

	if(obj.value.replace(/\s*/,"")=="")
		flag=true;
	else if(dateValidator(obj,seperator) && validateItem(obj))
	{
		var curD=new Date();
		var splits=obj.value.split(seperator);

		if(curD.getYear()<splits[2])//entered year is greater
		{
			alert("Entered Year is Bigger");
			flag=false;
		}
		else if(curD.getYear()==splits[2])//entered year equals current year
		{
			var i=0;
			for(;i<mon.length;i++)
				if(splits[1].toUpperCase()==mon[i])
					break;

			if(curD.getMonth()<i)//entered month is bigger
			{
				alert("Entered Month is Bigger");
				flag=false;
			}
			else if(curD.getMonth()==i)//entered month equals current month
			{
				if(curD.getDate()<splits[0])
				{
					alert("Entered Date Is bigger");
					flag=false;
				}
				else
					flag=true;
			}
			else
				flag=true;
		}
	}
	else
		flag=false;


	if(!flag)
	{
		obj.focus();
	}
}//end of sysBefore(obj.seperator)

function sysAfter(obj,seperator)
{
	var flag=true;
	var mon=new Array("JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC");

	if(obj.value.replace(/\s*/,"")=="")
		flag=true;
	else if(dateValidator(obj,seperator))
	{
		var curD=new Date();
		var splits=obj.value.split(seperator);

		if(curD.getYear()>splits[2])//for entered year is small
		{
			alert("Entered Year is Smaller");
			flag=false;
		}
		else if(curD.getYear()==splits[2])//for entered year equals current year
		{
			var i=0;
			for(;i<mon.length;i++)
				if(splits[1].toUpperCase()==mon[i])
					break;

			if(curD.getMonth()>i)//entered month is small
			{
				alert("Entered Month is Smaller");
				flag=false;
			}
			else if(curD.getMonth()==i)//entered month is equals current month
			{
				if(curD.getDate()>splits[0])
				{
					alert("Entered Date Is Smaller");
					flag=false;
				}
				else
					flag=true;
			}
			else
				flag=true;
		}
	}
	else
		flag=false;

	if(!flag)
	{
		obj.focus();
	}
}//end of sysAfter(obj.seperator)

function ageInserter(e,obj)
{
	if(e.keyCode>=48 && e.keyCode<=57)
	{
		if(obj.value.length==2)
		{
			/*FOR starting with 1*/
			if(obj.value.charCodeAt(0)==49 && obj.value.charCodeAt(1)>51)
				e.keyCode=0;
			else if(obj.value.charCodeAt(0)==49 && obj.value.charCodeAt(1)==51)
			{
				if(!(e.keyCode>=48 && e.keyCode<=53))
					e.keyCode=0;
			}
			else if(obj.value.charCodeAt(0)>=50)
				e.keyCode=0;


		}
	}
	else
		e.keyCode=0;
}//End of ageInserter(e,obj)

function validaeItemWithOutAlert(obj)
{
	var flag=true;

	switch(obj.type)
	{
		case "checkbox"		:if(obj.value=="-1" || obj.value=="" || obj.value==null)
								 flag=false;
							 break;

		case "radio"		:if(obj.value=="-1" || obj.value=="" || obj.value==null)
								 flag=false;
							 break;

		case "text"			:if(obj.value=="-1" || obj.value.replace(/\s*/,"")=="" || obj.value==null)
								 flag=false;
							 break;


		case "textarea"		:if(obj.value=="-1" || obj.value.replace(/\s*/,"")=="" || obj.value==null)
								 flag=false;
							 break;

		case "select-one"	:if(obj.options[obj.selectedIndex].value=="-1" || obj.options[obj.selectedIndex].value=="" || obj.options[obj.selectedIndex].value==null)
								 flag=false;
							 break;

		case "hidden"		:if(obj.value=="-1" || obj.value=="" || obj.value==null)
								 flag=false;
							 break;

	}
	return flag;
}
//End of validaeItemWithOutAlert(item)
