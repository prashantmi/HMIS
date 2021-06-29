function submitFormData()
{
if(document.forms[0].hmode.value!="" && document.forms[0].hmode.value!="SAVE" && document.forms[0].hmode.value!="NEW")
		document.forms[0].submit();
	
}
function submitPage(mode)
{
if(mode=="GET_DUTY_AREA")
	{
	document.forms[0].areaCode.value="-1";
	document.forms[0].designationId.value="-1";
	}

if(mode=="GET_REPORT")
	{
		
	if(validateGeneration())
		{	
		document.forms[0].hmode.value=mode;
		document.forms[0].submit();
		}
	}
	else
	{
		
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
	}
	


}

function validateFinalSubmit(){
     
 //   These All Fields are Mandatory
  var flag=false;
 
  if( 
 	comboValidation(document.getElementsByName('year')[0],"Year")
 && comboValidation(document.getElementsByName('month')[0],"Month")
 && comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")
 && comboValidation(document.getElementsByName('rosterId')[0],"Roster Name")
 && comboValidation(document.getElementsByName('areaCode')[0],"Duty Area")
 && comboValidation(document.getElementsByName('designationId')[0],"Designation")
 
	)
	{
	flag=true;
	}
 
 


    return flag;
} 	
	
function finalSubmit(mode)
{



if (validateFinalSubmit()) 
  {
	 concateDataToBeSaved();
	 getEmpListToBeUpdated();
	
	//validating whether any shift is selected for an employeee
	
	if(document.getElementsByName("concatedValueToBeSaved")[0].value!="")
		{
		
		       submitPage(mode);
    	
    	 }
    	 else
    	 {	
    	 

		var check=confirm("Do You Want to Drop The Complete Roster");
			
			if(check==true)
				submitPage(mode);
				else
				alert("Please Select Some Shift For an Employee");
    	
    	 }	
	}//if closed
}//function closed




function clearForm()
 {
  	document.getElementsByName('year')[0].value="-1";
    document.getElementsByName('month')[0].value="-1";
    document.getElementsByName('rosterCategory')[0].value="-1";
    document.getElementsByName('rosterId')[0].value="-1";
    document.getElementsByName('areaCode')[0].value="-1";
    document.getElementsByName('startDate')[0].value="";
    document.getElementsByName('endDate')[0].value="";
 //  submitPage('NEW'); 
        
 }

//Function for Changing Shift Types 
function changeShiftType(these)
{
//var buttonColorArray=new Array{"white"}; 

var flag=false;
var buttonId=these.id;
var  buttonIdArray=buttonId.split("@");
var buttonDate=buttonIdArray[1];
var currentDate=document.getElementsByName("currentDate")[0].value;
var currentMonth=document.getElementsByName("currentMonth")[0].value;
var currentYear=document.getElementsByName("currentYear")[0].value;
var selectedMonth=document.getElementsByName("month")[0].value;
var selectedYear=document.getElementsByName("year")[0].value;
//alert("button Id--->"+buttonId);


//Checking the Button's Date Should be greater than or equal to the current date
//Bco'z you cannot change or assign an  old shift  


if(currentDate <= 9 )
   currentDate=0+currentDate;
   
if(currentMonth <= 9 )
   currentMonth=0+currentMonth;

if(buttonDate <= 9 )
   buttonDate=0+buttonDate;

if(selectedMonth <= 9 )
   selectedMonth=0+selectedMonth;
   
   
   
   
/*alert("buttonDate-->"+buttonDate);
alert("selectedMonth-->"+selectedMonth);
alert("selectedYear-->"+selectedYear);

alert("currentDate-->"+currentDate);
alert("currentMonth-->"+currentMonth);
alert("currentYear-->"+currentYear);
*/
   
var currentDateCheck=currentYear+currentMonth+currentDate;
	//alert("currentDateCheck----"+currentDateCheck);

var selectedDateCheck=selectedYear+selectedMonth+buttonDate;
	//alert("selectedDateCheck----"+selectedDateCheck);

if(selectedDateCheck >= currentDateCheck)
	flag=true;
	
//	alert(flag);

//alert("button Name--->"+these.name);
//alert("button Value--->"+these.value);

	
		
if(flag==true)
	{		

var shiftFlag=false;



	 			//			  1			 2			  3						4					 5						6						  7				   8
//buttonNameArray----->		Empid   +   Date   +    shiftId     +		startTime        +     EndTime          +   ShifTFull Name   +         ShiftType    +    DayCode



var buttonNameArray=these.name.split("^");


var currentShiftId=buttonNameArray[2];


var currentShiftType=buttonNameArray[6];

//getting the current  day code Sunday,Monday, etc....

var currentDayCode=buttonNameArray[7];

var index=-1;

//alert("currentShiftId--->"+currentShiftId);


var shiftIdArray=document.getElementById("shiftIdArray").value.split("^")
var shiftNameArray=document.getElementById("shiftNameArray").value.split("^");
var shiftStartTimeArray=document.getElementById("shiftStartTimeArray").value.split("^")
var shiftEndTimeArray=document.getElementById("shiftEndTimeArray").value.split("^");
var shiftFullNameArray=document.getElementById("shiftFullNameArray").value.split("^");
var shiftTypeArray=document.getElementById("shiftTypeArray").value.split("^");
var shiftTimingsArray=document.getElementById("shiftTimingsArray").value.split("^")

//getting the current index of shift type from the array of shifts available for the current Roster Type  
for(i=0 ; i < shiftIdArray.length ; i++)
 {
// alert("shiftIdArray---->"+i+"----->"+shiftIdArray[i]);
 if(shiftIdArray[i]==currentShiftId)
 	{
 //	alert("equal");
 	index=i;
 	}
 
 }
//alert("index after iteration---->"+index);
 

 var lengthCheck=shiftIdArray.length-2;
 
//getting the new shift type index from the array of shifts available for the current Roster Type
//	alert("length----"+length);
//alert("lengthCheck--"+lengthCheck);
if(index < lengthCheck)	
	{
//	alert("less than max length");
	
	index=index+1;
	}
	else
if(index=lengthCheck)
	{
//	alert("equal to max length");
	index=0;
	}


	//alert("new value of index after comparison---->"+index);
/*
Earlier Used Code for getting value based on index when default was none
if(index!=-1)
{
	var nextShiftId=shiftIdArray[index];
	var nextShiftName=shiftNameArray[index];
	var nextShiftStartTime=shiftStartTimeArray[index];
	var nextShiftEndTime=shiftEndTimeArray[index];
 }
 else
 if(index==-1)
 {
 	var nextShiftId=-1;
	var nextShiftName=" ";
	var nextShiftStartTime="-1";
	var nextShiftEndTime="-1";
	
 }*/
 
 
 //Getting the next value of shift based on next index.
 
 	var nextShiftId=shiftIdArray[index];
	var nextShiftName=shiftNameArray[index];
	var nextShiftStartTime=shiftStartTimeArray[index];
	var nextShiftEndTime=shiftEndTimeArray[index];
	var nextFullName=shiftFullNameArray[index];
	var nextShiftType=shiftTypeArray[index];
	
	
	
	shiftFlag=validateShift(buttonId,nextShiftType);
	
	
	if(nextShiftStartTime=="NA" || nextShiftEndTime=="NA")
		{
		var shiftTimings=shiftTimingsArray[currentDayCode-1].split("@");
		
		
		nextShiftStartTime=shiftTimings[0]
		nextShiftEndTime=shiftTimings[1]
		}
	
	
 //Constructing new button name
		//			  1						 2					     3				     4					 5					6				  7				   8 
//                  EmpID           +        Day            +     ShiftId    +      shiftStartTime  +    shiftEndTime   +  nextFullName +    nextShiftType   + currentDayCode
var buttonNewName=buttonNameArray[0]+"^"+buttonNameArray[1]+"^"+nextShiftId+"^"+nextShiftStartTime+"^"+nextShiftEndTime+"^"+nextFullName+"^"+nextShiftType+"^"+currentDayCode;

//alert("buttonNewName------->"+buttonNewName);
//alert("nextShiftName------->"+nextShiftName);

//alert("shiftFlag-----"+shiftFlag);
if(shiftFlag==true)
			{
 
		document.getElementById(buttonId).name=buttonNewName;
		document.getElementById(buttonId).value=nextShiftName;
		    }
		    else
		    {
		document.getElementById(buttonId).name=buttonNewName;
		document.getElementById(buttonId).value=nextShiftName;
		
		    var elem=document.getElementById(buttonId);
		    changeShiftType(elem); 	    
		    }
		    
		    
	}	 
		else
		alert("You Cannot Change it's Shift,Because the Shift's Date is Older than the Today's Date ");

}


   function concateDataToBeSaved()
{



//getting the current year , month and date
var currentYear=document.getElementsByName("currentYear")[0].value;
var currentMonth=document.getElementsByName("currentMonth")[0].value;
var currentDate=document.getElementsByName("currentDate")[0].value;

//getting the selected year and month 
var selectedYear=document.getElementsByName("year")[0].value;
var selectedMonth=document.getElementsByName("month")[0].value;
//alert("button Id--->"+buttonId);


//getting the max days in the month and max no of employees 
var maxDaysofMonth=document.getElementById("maxDaysofMonth").value
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value
  


//starting date of the  month
var startingDate=1;


//Now checking whether the selected year and month are  equal then the vo list to save will start from the current date 
//onwards bcoz ,you cannot change/update the shift for an old date 

if(selectedYear == currentYear && selectedMonth==currentMonth)
{
startingDate=parseInt(currentDate);

}
  
  

//alert("startingDate----"+startingDate);




var valueTobeSaved="";
var concatedValueToBeSaved="";



//alert("maxDaysofMonth----->"+maxDaysofMonth);
///alert("maxNoOfEmployees----->"+maxNoOfEmployees);


for(var i=1;i<=maxNoOfEmployees;i++)
{
  // alert(i);
  var checkBoxId="selectEmp_"+i; 
  
//  alert(checkBoxId);
  
  ///now checking whether the particular checkbox is 
  ////clicked or not and hence whether to save the data or not
 //alert(document.getElementById(checkBoxId).checked)
  
if(document.getElementById(checkBoxId).checked==true)
  {  
	for(var j=startingDate;j<=maxDaysofMonth;j++)
	   {
	   var buttonId=i+"@"+j;
	//	alert(buttonId);
	   var arrayOfValues=document.getElementById(buttonId).name.split("^");
	 

 //if for checking whether the shift selected is not default 
	 //then only it will be saved
	//alert(arrayOfValues[2])
	   
if(document.getElementById(buttonId).disabled==false && arrayOfValues[2]!=-1 )
	    {
	    valueTobeSaved=document.getElementById(buttonId).name;
	    
	    
	//  	 alert(valueTobeSaved);
	  	  
	   	concatedValueToBeSaved+=valueTobeSaved+"#";
	//   alert(concatedValueToBeSaved);
		   	
		   	
		   	}//if for checking whether the shift selected is not default is closed
		   	
		   	
	   }/////for of days closed
	
	
  }	///if of checkboxId checked closed
	   
	   
	   
}	////for of emplist closed


//alert("concatedValueToBeSaved----------->"+concatedValueToBeSaved);

	//	alert("i----"+i+"----j----"+j);   
document.getElementsByName("concatedValueToBeSaved")[0].value=concatedValueToBeSaved;	   
	   
}  
      
       
 function showShiftName(these)
 {
var arrayOfData=these.name.split("^");
 these.title=arrayOfData[5];
 } 
 
 function printPage() 
 {
 var frameElement = parent.document.getElementById("f2");  
 var win = frameElement.contentWindow ;
 document.getElementById("noPrint").style.display="none"; 
 win.print(); 
  document.getElementById("noPrint").style.display="block" ; 
 } 
   function backButton() 
 { 
  //history.back();
   submitPage('NEW');
 }
  
 function selectEmployee(these,leaveList)
 {
	
//alert("fn called-----leaveList---"+leaveList)
 
var rosterGenList=document.getElementById("rosterGenList").value;
var rosterGenArray=rosterGenList.split("@"); 
 
 //getting the max days in the month and max no of employees 
var maxDaysofMonth=document.getElementById("maxDaysofMonth").value


 var checkBoxId=these.id
 
 //alert(checkBoxId)
 
 //getting the roster mode
 var rosterMode=document.getElementsByName("dutyType")[0].value;
 
 //creating a holiday array
 var holidayArray=document.getElementById("holidayList").value.split("@");
 
 //creating the Leave Array
 var leaveArray=leaveList.split("@");
 
 
  //alert("rosterMode----"+rosterMode)
   //alert("holidayList----"+holidayList)
   
var idArray=checkBoxId.split("_");



if(document.getElementById(checkBoxId).checked==true)
	{
//	alert("chekced")
	
for(var i=1 ; i <= maxDaysofMonth ; i++)
		{	
var buttonId=idArray[1]+"@"+i;

//alert("buttonId----"+buttonId)

var holidayFlag=false;

for(var j=0; j < holidayArray.length ; j++)
{
//if the date(i) is equal to any holiday(holidayArray[j]) ,then it is holiday
if(i==holidayArray[j])
	{
	holidayFlag=true;
	break;
	}

}



var leaveFlag=false;

for(var j=0; j < leaveArray.length ; j++)
{
//if the date(i) is equal to any Leave(leaveArray[j]) ,then it is Leave
if(i==leaveArray[j])
	{
	leaveFlag=true;
	break;
	}

}


var generatedFlag=false;

for(var k=0; k < rosterGenArray.length ; k++)
{

if(i==rosterGenArray[k])
	{
	generatedFlag=true;
	break;
	}

}




//alert("color---"+buttonId+"----"+document.getElementById(buttonId).style.backgroundColor)
//alert("leaveFlag-----"+leaveFlag);


//in case of Offical Roster-1 only offical days will be enabled && the person should not be on Leave

if(rosterMode=="1" && holidayFlag==false && leaveFlag==false && document.getElementById(buttonId).style.backgroundColor!="Pink" && generatedFlag==false)
	document.getElementById(buttonId).disabled=false;
	else

//in case of Holiday Roster-3 only holidays days will be enabled && the person should not be on Leave

if(rosterMode=="3" && holidayFlag==true && leaveFlag==false && document.getElementById(buttonId).style.backgroundColor!="Pink" && generatedFlag==false)
	document.getElementById(buttonId).disabled=false;	
	else
	
//in case of 24x7 Roster-2 All  days will be enabled && the person should not be on Leave

if(rosterMode=="2" && leaveFlag==false && document.getElementById(buttonId).style.backgroundColor!="Pink" && generatedFlag==false)
	document.getElementById(buttonId).disabled=false;	

//in case the leave flag is true i.e. the person is on leave ,it's color willl change to pink
if(leaveFlag==true)
		document.getElementById(buttonId).style.backgroundColor="pink";
	

		}
	
	
	}
else
	{
//	alert("unchekced")
	
	for(var i=1 ; i <= maxDaysofMonth ; i++)
		{	
var buttonId=idArray[1]+"@"+i;
//alert("buttonId----"+buttonId)

document.getElementById(buttonId).disabled=true;

		}
	
	
	}
	
 
 }      
 
function checkDateOnLoad()
{
document.getElementById("legendId").style.display="none";

//alert("fn called---"+document.getElementsByName("hmode")[0].value)

//checking in the case the hmode is GET_ROSTERS
//IF THE SELECTED YEAR/MONTH IS LESS THAN THE CURRENT YEAR/MONTH THEN THE SAVE
//BUTTON WILL BE DISABLED

if(document.getElementsByName("hmode")[0].value=="GET_DUTY_AREA"  || document.getElementsByName("hmode")[0].value=="GET_EMPLOYEES_CALENDAR" )
{
//alert("inside");



//getting the current year , month and date
var currentYear=document.getElementsByName("currentYear")[0].value;
var currentMonth=document.getElementsByName("currentMonth")[0].value;


//getting the selected year and month 
var selectedYear=document.getElementsByName("year")[0].value;
var selectedMonth=document.getElementsByName("month")[0].value;

var check=true; 


if(selectedYear <  currentYear)
	check=false;
	else
if(selectedMonth < currentMonth)
	check=false;
	
//	alert("check----"+check)

if(check==false)
	document.getElementById("saveButton").style.display="none";
}

	

} 
function changeShiftTypeOfUnMappedEmp()
{
alert("You Cannot assign a Shift To This Employee ,Since This Employee Has been Removed From the Mapping")
}
function getEmpListToBeUpdated()
{
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value;
var empListToBeUpdated="";
 
	for(var i=1 ; i<=maxNoOfEmployees ; i++)
	{
	var checkBoxId="selectEmp_"+i;
		
	if(i < maxNoOfEmployees)
		empListToBeUpdated+="'"+document.getElementById(checkBoxId).value+"'"+",";
		else
	if(i== maxNoOfEmployees)
		empListToBeUpdated+="'"+document.getElementById(checkBoxId).value+"'";
		
		
	
	}
	
	//alert("empListToBeUpdated-----------"+empListToBeUpdated);
	
	document.getElementsByName("empListToBeUpdated")[0].value=empListToBeUpdated;

}
function selectAllEmployees(these)
{

var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value;

var Id=these.id;



for(var i=1 ; i<=maxNoOfEmployees ; i++)
	{
	var checkBoxId="selectEmp_"+i;

    var elem=document.getElementById(checkBoxId);
    
//checking wheteher the select ALL checkbox is checked or not
 
 
if(document.getElementById(Id).checked==true)		
	{

if(document.getElementById(checkBoxId).disabled==false)	
	document.getElementById(checkBoxId).checked=true
	
	selectEmployee(elem,'NA')
	}
else
if(document.getElementById(Id).checked==false)		
	 {
	document.getElementById(checkBoxId).checked=false
    selectEmployee(elem,'NA')
	 }//2nd if closed 
   
   
   
   }//for closed



}

function validateShift(shiftId,shiftTypeNew)
{
var hiddenId=shiftId+"@"+"1";
var hiddenValues=document.getElementById(hiddenId).value;
var flag=true;

//alert("hiddenValues---"+hiddenValues);
if(hiddenValues!="")
{
if(hiddenValues.indexOf("#")!="-1")
	{
	var arrayOfShifts=hiddenValues.split("#");
	
	for(var i=0; i < arrayOfShifts.length ; i++)
		{
		var arrayOfShiftDetails=arrayOfShifts[i].split("@")
		var shiftType=arrayOfShiftDetails[0];
		var shiftName=arrayOfShiftDetails[1];
	
	
		
		if(shiftTypeNew==shiftType)
			{
		   flag=false;	
		//	alert(shiftName+" Shift is Already Assigned to this Employee,so it Cannot be Assigned");
	    
			break;
			}
						
		
		}
	
	}
	else//if it does not contain # ,then it will be split by @ 
	{
	
	var arrayOfShiftDetails=hiddenValues.split("@");
		var shiftType=arrayOfShiftDetails[0];
		var shiftName=arrayOfShiftDetails[1];
		
		//alert("shiftType---"+shiftType);
		
	
		
		if(shiftTypeNew==shiftType)
			{
			flag=false;	
		//alert(shiftName+" Shift is Already Assigned to this Employee,so it Cannot be Assigned");
		
			}
	
	}


}//if hidden closed


return flag;



}

function getMappedAreas(these)
{
alert("This Employee is Currently Mapped to "+these.name+" Areas");
}


function selectAllEmpTitle(these)
{
these.title="Select or UnSelect All"
}


function showLegends()
{
document.getElementById("legendId").style.display="block";
}


function hideLegends()
{
document.getElementById("legendId").style.display="none";
}

function openEmpTotalRoster(these)
{
document.forms[0].hmode.value="GET_TOTAL_EMPLOYEE_ROSTER";
window.opener.document.forms[0].empId.value=these.name;
document.forms[0].submit();

}

function reportValidation(){
     
 //   These All Fields are Mandatory
  var flag=false;
 
  if( 
 	 	comboValidation(document.getElementsByName('year')[0],"Year")
	&&  comboValidation(document.getElementsByName('month')[0],"Month")
	&&	comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")
	&&	comboValidation(document.getElementsByName('rosterId')[0],"Roster Name")
	&&	comboValidation(document.getElementsByName('areaCode')[0]," Duty Area")
	&&	comboValidation(document.getElementsByName('designationId')[0],"Designation")
 
	)
	{
	flag=true;
	}
 
 


    return flag;
} 	
function validateGeneration()
{

	
		var year=document.getElementsByName("year")[0].value;
		var month=document.getElementsByName("month")[0].value;
		
		var monthArray=new Array("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec");
		
		var valid=false; 

	
	if(reportValidation()==false)
		return false;
		else
    if(document.getElementsByName('startDate')[0].value!="" &&  document.getElementsByName('endDate')[0].value!="")
    	{
    	var flag=true;
    		
    	var  valFromDate=document.getElementsByName('startDate')[0];
   		var  valToDate=document.getElementsByName('endDate')[0];
    		
    		
    	var startDateArray=document.getElementById("startDate").value.split("-");		
		var endDateArray=document.getElementById("endDate").value.split("-");
		
		if(year!=startDateArray[2])
			{
			flag=false;
			alert("Please Select The  Year in Report Printing From Date,Same as the Year in Combo");
			document.getElementsByName('startDate')[0].focus();
			}
			else
		if(year!=endDateArray[2])
			{
			flag=false;
			alert("Please Select The  Year in Report Printing To Date,Same as the Year in Combo");
			document.getElementsByName('endDate')[0].focus();
			}
			else
		if(monthArray[month-1]!=startDateArray[1])
			{
			flag=false;
			alert("Please Select The  Month in Report Printing From Date,Same as the Month in Combo");
			document.getElementsByName('startDate')[0].focus();
			}
			else
		if(monthArray[month-1]!=endDateArray[1])
			{
			flag=false;
			alert("Please Select The  Month in Report Printing To Date,Same as the Month in Combo");
			document.getElementsByName('endDate')[0].focus();
			}
			else
		if(!(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") ))
			{
			flag=false
			}		
		
		
			return flag;
    	
    	}
    	else
    		return true;
}


function focusOnLoad()
{
if(document.forms[0].elements[0].value=="-1")
  	document.forms[0].elements[0].focus();
  	else
if(document.forms[0].elements[1].value=="-1")
  	document.forms[0].elements[1].focus();
  	else
if(document.forms[0].elements[2].value=="-1")
  	document.forms[0].elements[2].focus();
  	else
if(document.forms[0].elements[3].value=="-1")
  	document.forms[0].elements[3].focus();
	else
if(document.forms[0].elements[4].value=="-1")
  	document.forms[0].elements[4].focus();
		else
if(document.forms[0].elements[5].value=="-1")
  	document.forms[0].elements[5].focus();
  		else
if(document.forms[0].elements[6].value=="-1")
  	document.forms[0].elements[6].focus();
  		else
if(document.forms[0].elements[7].value=="-1")
  	document.forms[0].elements[7].focus();
  	
}