

function getEmployeeList()
{
	if(document.getElementsByName('rosterTypeID')[0].value!='-1' &&
	document.getElementsByName('dutyAreaId')[0].value!='-1'
	//&&	dateCheck()
	  )
	{
		submitPage("GET_EMPLOYEES");
	}
}

function dateCheck()
{

 	
	var valFromDate=document.getElementsByName('fromDate')[0]
	var valToDate=document.getElementsByName('toDate')[0]
	var valSysDate=document.getElementsByName('currentDate')[0]
	var valid=true;
	
if(!(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valSysDate,valFromDate,2,"System Date","From Date")))
	{
	valid=false;
	}
	
	return valid 
}

function getNewRole()
{
if(dateCheck() && checkDateRangeValidation())
	{
	document.getElementsByName('mode')[0].value='NEW';
	//document.forms[0].submit();
	getEmployeeList();
	}
	//else
	//alert('canot create a new ')
}

function modifyOldRoster()
{
var flag=false;



for(var i=0; i< document.getElementsByName("roleDateRangeId").length;i++)
   {	
	if(document.getElementsByName("roleDateRangeId")[i].checked==true)
		{
		flag=true;
		break;		
		}
   }
   
   
   if(flag==true)
   		{
		document.getElementsByName('mode')[0].value='MODIFY';
	//	alert("document.getElementsByName('mode')[0].value="+document.getElementsByName('mode')[0].value)
	//	alert("document.getElementsByName('hmode')[0].value="+document.getElementsByName('hmode')[0].value)
	//	document.forms[0].submit();
		getEmployeeList();
		}
		else
			alert("Please Select an Option");
}

function submitPage(hmode)
{
document.getElementsByName('hmode')[0].value=hmode;
document.forms[0].submit();
}

//**************************************checkDateRangeValidation*********************************//////////

function checkDateRangeValidation()
{
	
	
	var flag=true;
	
	var newShiftId=document.getElementsByName("shiftID")[0].value;

	var fromDateNew=document.getElementsByName("fromDate")[0];
	var toDateNew=document.getElementsByName("toDate")[0];


for(var i=0 ; i < document.getElementsByName("fromDateOld").length; i++)
	{
	
var arrayOfRangData=document.getElementsByName("roleDateRangeId")[i].value.split("@");


var fromDateOld=document.getElementsByName("fromDateOld")[i];
var toDateOld=document.getElementsByName("toDateOld")[i];
	
var oldShiftId=document.getElementsByName("shiftID")[0].value;


	
//	alert("1---"+compareDateCallWithoutAlert(fromDateOld,fromDateNew,2,"From Date Old","From Date New"));
	
//	alert("2---"+compareDateCallWithoutAlert(fromDateNew,toDateOld,2,"From Date New","To Date Old"));
	

	
if(compareDateCallWithoutAlert(fromDateOld,fromDateNew,2,"From Date Old","From Date New") && compareDateCallWithoutAlert(fromDateNew,toDateOld,2,"From Date New","To Date Old") && (arrayOfRangData[3]==newShiftId || newShiftId=='%') )
		{
		alert("Role Assignment for this Date Range and Shift ,Already Exsists");
		flag=false;
		break;
		}
		else
if(compareDateCallWithoutAlert(fromDateOld,toDateNew,2,"From Date Old","To Date New") && compareDateCallWithoutAlert(toDateNew,toDateOld,2,"To Date New","To Date Old") && (arrayOfRangData[3]==newShiftId || newShiftId=='%') )
		{
		alert("Role Assignment for this Date Range and Shift ,Already Exsists");
		flag=false;
		break;
		}
		else
if(compareDateCallWithoutAlert(fromDateNew,fromDateOld,2,"From Date New","From Date Old") && compareDateCallWithoutAlert(fromDateOld,toDateNew,2,"From Date Old","To Date New") && (arrayOfRangData[3]==newShiftId || newShiftId=='%') )
		{
		alert("Role Assignment for this Date Range and Shift ,Already Exsists");
		flag=false;
		break;
		}
		else
if(compareDateCallWithoutAlert(fromDateNew,toDateOld,2,"From Date New","From Date Old") && compareDateCallWithoutAlert(toDateOld,toDateNew,2,"To Date Old","To Date New") && (arrayOfRangData[3]==newShiftId || newShiftId=='%') )
		{
		alert("Role Assignment for this Date Range and Shift ,Already Exsists");
		flag=false;
		break;
		}
		

				
		
		
	
	}//For closed

	

return flag;

}//Fn closed


//**************************************compareDateCallWithoutAlert*********************************//////////

function compareDateCallWithoutAlert(d1,d2,mode,l1,l2){

var valid=true;

 if(compareDate(d1,d2, mode)){

		valid = true;
	}
 else 
 	{

	valid = false;
	}


return valid;
}

function validateSave(mode)
{
var flag=false;

for(var i=0; i < document.getElementsByName("roleID").length ; i++ )
		{
		if(document.getElementsByName("roleID")[i].checked==true)
					{
					flag=true;
					break;
					}
		}


		if(flag==true)
				submitPage(mode);
				else
				alert("Please Select Atleast One Role")



}

function submitFormData()
{
document.forms[0].submit();
}
