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
	if(mode=="GET_EMPLOYEES_CALENDAR")
	{
	document.forms[0].areaCode.value="-1";
	document.forms[0].designationId.value="-1";
	}
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}
function validateRosterType()
{
if(document.getElementsByName('rosterId')[0].value=="-1@-1@-1")
	{
	alert('Please Select : Roster Name');
	return false;
	}
	else
	return true;

}

function validateFinalSubmit(){
     
 //   These All Fields are Mandatory
  var flag=false;
  if( 
 	comboValidation(document.getElementsByName('year')[0],"Year")
 && comboValidation(document.getElementsByName('month')[0],"Month")
 && comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")
 && validateRosterType()
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


//validations +maxcontionous off validation +max off validation + NoOfhrs per week validation
if (validateFinalSubmit() && validateMaxContinousOff() && validateMaxOff() && validateNoOfHrsPerWeek() && validateShiftCapacity() ) 
  {
	 concateDataToBeSaved();
	 getEmpListToBeUpdated();
	// concateEmpToBeRelived();
	
	var flag=checkRosterGenerationDays();
	
	//validating whether any shift is selected for an employeee
	
	
	//if whole roster is generated
	if(flag==true)
		alert("The Whole Roster is Already Generated,Please Drop the Roster if you want to make a Fresh Roster");
		else
	if(document.getElementsByName("concatedValueToBeSaved")[0].value!="")
		{
		
		      submitPage(mode);
    	
    	 }
    	 else
    	alert("Please Select Some Shift For an Employee");
/*    	  else
    	 {	
    	 

		var check=confirm("Do You Want to Drop The Complete Roster");
			
			if(check==true)
				submitPage(mode);
				else
				alert("Please Select Some Shift For an Employee");
    	
    	 }
*/    	 
    	 	
	}//if closed
}//function closed



//Function for checking whether the whole roster is generated or Not
function checkRosterGenerationDays(){

var maxDaysofMonth=document.getElementsByName("maxDaysofMonth")[0].value 
var rosterGenerationDays=document.getElementsByName("rosterGenList")[0].value.split("@");

if(parseInt(maxDaysofMonth)==rosterGenerationDays.length)
	return true;//if whole roster is generated
else
	return false;//if partial roster is generated

}


function clearForm()
 {
  	document.getElementsByName('year')[0].value="-1";
    document.getElementsByName('month')[0].value="-1";
    document.getElementsByName('rosterId')[0].value="-1";
    document.getElementsByName('areaCode')[0].value="-1";
   submitPage('NEW'); 
        
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
		//alert(buttonId);
	   var arrayOfValues=document.getElementById(buttonId).name.split("^");
	 

 //if for checking whether the shift selected is not default 
	 //then only it will be saved
	//alert(arrayOfValues[2])
	   
if(document.getElementById(buttonId).disabled==false && arrayOfValues[2]!=-1 )
	    {
	    valueTobeSaved=document.getElementById(buttonId).name;
	    
	    
		// alert(valueTobeSaved);
	  	  
	   	concatedValueToBeSaved+=valueTobeSaved+"#";
	  // alert(concatedValueToBeSaved);
		   	
		   	
		   	}//if for checking whether the shift selected is not default is closed
		   	
		   	
	   }/////for of days closed
	
	
  }	///if of checkboxId checked closed
	   
	   
	   
}	////for of emplist closed


//alert("Finall valuye concatedValueToBeSaved----------->"+concatedValueToBeSaved);

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
  history.back(); 
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
focusOnLoad();

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

var check=false; 

//alert("selectedYear-----"+selectedYear)
//alert("currentYear-----"+currentYear)
//alert("selectedMonth-----"+selectedMonth)
//alert("currentMonth-----"+currentMonth)

//if year selected is greater than current year

if(parseInt(selectedYear) >  parseInt(currentYear) )
	check=true;
	else  //if year selected is current year && month is greater than equal to current month
if((parseInt(selectedYear) ==  parseInt(currentYear))  && (parseInt(selectedMonth) >= parseInt(currentMonth)))
	check=true;
	
	//alert("check----"+check)
//if the Selected( year && month) is less than than the current (Year && Month)
//then the save button will not be visible
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

function concateEmpToBeRelived()
{

var empListLength=document.getElementsByName("reliverEmp").length;
var reliverEmpList="";

for(var i=0; i< empListLength;i++)
	{
	if(document.getElementsByName("reliverEmp")[i].checked==true)
			{
		var empId=document.getElementsByName("reliverEmp")[i].value;
		reliverEmpList+=empId+"@"
		    }


	}
	//alert("reliverEmpList--------"+reliverEmpList)

document.getElementsByName("reliverEmpList")[0].value=reliverEmpList

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
if(document.forms[0].elements[3].value=="-1@-1@-1")
  	document.forms[0].elements[3].focus();
	else
if(document.forms[0].elements[4].value=="-1")
  	document.forms[0].elements[4].focus();

}

function enableBlanket(e)
{
if(validateFinalSubmit() && ( e.type=="click" || e.keyCode==13) )
	{
     
	document.getElementById("blanket").style.display="block";
	document.getElementById("patternDivId").style.display="block";
	document.getElementsByName("patternRepeatAfter")[0].focus();
	}
	
}
function disableBlanket(e)
{


if(e.type=="click" || e.keyCode==13)
	{
	document.getElementById("blanket").style.display="none";
	document.getElementById("patternDivId").style.display="none";
	}
	
}

function repeatPattern(e)
{

if(e.type=="click" || e.keyCode==13) 
	{
	
//enableBlanket();


var maxDaysofMonth=document.getElementById("maxDaysofMonth").value;
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value;


var patternRepeatAfter=document.getElementsByName("patternRepeatAfter")[0].value;

//alert("patternRepeatAfter----"+patternRepeatAfter)

if(patternRepeatAfter=="" || patternRepeatAfter=="0")
	{
	alert("Please Enter Repeat Pattern After");
	document.getElementsByName("patternRepeatAfter")[0].focus();
	}
	else
if(parseInt(patternRepeatAfter) > parseInt(maxDaysofMonth))
	{
	alert("Repeat Pattern After ,Cannot be Greater than "+maxDaysofMonth+" Days");
	document.getElementsByName("patternRepeatAfter")[0].focus();
	document.getElementsByName("patternRepeatAfter")[0].value="";
	}	
	else
   {	
   
  //disabling the blanket 
disableBlanket(e);

//iterating throygh all emp
 
for(var i=1 ; i <= maxNoOfEmployees ; i++)
	{
	var startId=i+"@"+1;
	var endId=i+"@"+patternRepeatAfter;
	
	
//through all months	
		for(var j=1 ; j <= maxDaysofMonth ; j++)
			{
	var checkBoxId="selectEmp_"+i;
	var buttonId=i+"@"+j;
	
	//alert("buttonId---"+buttonId)
	
	//if checkbox is checked 
			if(document.getElementById(checkBoxId).checked==true)
					{
					
			
	//if button is not disabled and button id is greater than pattern repeatAfter		
			if(document.getElementById(buttonId).disabled!=true && j >patternRepeatAfter)
							{
							
							var dayId;
							
							if(j< 10)
								dayId="0"+j;								
								else
								dayId=j;
								
							
							//alert("dayId---"+dayId);
								
							var modulo=j%patternRepeatAfter;
							
							if(modulo==0)
							   modulo=patternRepeatAfter;
							   
							//alert(modulo)
								
							//getting the pattern Id
							var patternId=i+"@"+modulo; //parseInt(j-patternRepeatAfter);
							
							//getting the array values of old button id 
							var buttonNameArray=document.getElementById(patternId).name.split("^");
							
							var buttonOldNameArray=document.getElementById(buttonId).name.split("^");
							
							
								//alert("buttonId-----"+buttonId+"--value---"+document.getElementById(buttonId).value)
							
							
							//alert("patternId----"+patternId+"--value---"+document.getElementById(patternId).name)
							
							
							
							 //Constructing new button name
			//			  1				   2	           3				     4					   5				    	6	       	        	  7   		   	        8 
	//                  EmpID        +    Day      +     ShiftId    +      shiftStartTime  +      shiftEndTime   +      Shift FullName +              ShiftType   + 		  DayCode
var buttonNewName=buttonNameArray[0]+"^"+dayId+"^"+buttonNameArray[2]+"^"+buttonNameArray[3]+"^"+buttonNameArray[4]+"^"+buttonNameArray[5]+"^"+buttonNameArray[6]+"^"+buttonOldNameArray[7];
							
							
						
							
							
							
							
							//putting the patternId  value into the next Button
							document.getElementById(buttonId).value=document.getElementById(patternId).value;
							
							
							//putting the patternId  Name into the next Button
							document.getElementById(buttonId).name=buttonNewName;
							
							
						//	alert("buttonId last-----"+buttonId+"--value---"+document.getElementById(buttonId).value+"--name---"+document.getElementById(buttonId).name)
							
							}//inner if closed for checking of disablity of button
						
						
	
					}//outer if closed for checking of check box checked
	
           }//inner for closed 
	
	
	
	
	}//outer for closed 

  }//else closed 
  
 }//if of click and enter key check 
   
}//fn closed 

/*****************************************New Pattern***************************/

function enableNewBlanket(e)
{
	if(validateFinalSubmit() && ( e.type=="click" || e.keyCode==13) )
	{
		document.getElementById("blanket").style.display="block";
		document.getElementById("newPatternDivId").style.display="block";
		document.getElementsByName("newPatternRepeatFrom")[0].focus();
		document.getElementsByName("newPatternRepeatFrom")[0].value="";
		document.getElementsByName("newPatternRepeatTo")[0].value="";
		document.getElementsByName("newPatternRepeatAfter")[0].value="";
	}
}


function disableNewBlanket(e)
{


if(e.type=="click" || e.keyCode==13)
	{
	document.getElementById("blanket").style.display="none";
	document.getElementById("newPatternDivId").style.display="none";
	}
	
}


function repeatNewPattern(e)
{
 //alert("2");
if(e.type=="click" || e.keyCode==13) 
	{
	
//enableBlanket();


var maxDaysofMonth=document.getElementById("maxDaysofMonth").value;
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value;


	var patternRepeatFrom=document.getElementsByName("newPatternRepeatFrom")[0].value;
	var patternRepeatTo=document.getElementsByName("newPatternRepeatTo")[0].value;
	var patternRepeatAfter=document.getElementsByName("newPatternRepeatAfter")[0].value;

	

if(patternRepeatFrom=="" || patternRepeatFrom=="0")
{
	alert("Please Enter Repeat Pattern From");
	document.getElementsByName("newPatternRepeatFrom")[0].focus();
	return false;
}

	if(patternRepeatTo=="" || patternRepeatTo=="0")
	{
	alert("Please Enter Repeat Pattern To");
	document.getElementsByName("newPatternRepeatTo")[0].focus();
	return false;
	}
if(patternRepeatAfter=="" || patternRepeatAfter=="0")
	{
	alert("Please Enter Repeat Pattern After");
	document.getElementsByName("newPatternRepeatAfter")[0].focus();
	return false;
	}
if(parseInt(patternRepeatFrom) > parseInt(maxDaysofMonth))
	{
	alert("Repeat Pattern From ,Cannot be Greater than "+maxDaysofMonth+" Days");
	document.getElementsByName("newPatternRepeatFrom")[0].focus();
	document.getElementsByName("newPatternRepeatFrom")[0].value="";
	return false;
	}	
if(parseInt(patternRepeatTo) > parseInt(maxDaysofMonth))
	{
	alert("Repeat Pattern To ,Cannot be Greater than "+maxDaysofMonth+" Days");
	document.getElementsByName("newPatternRepeatTo")[0].focus();
	document.getElementsByName("newPatternRepeatTo")[0].value="";
	return false;
	}	
if(parseInt(patternRepeatFrom) > parseInt(patternRepeatTo))
	{
	alert("Repeat Pattern From Cannot be Greater than Repeat Pattern To");
	document.getElementsByName("newPatternRepeatFrom")[0].focus();
	document.getElementsByName("newPatternRepeatFrom")[0].value="";
	return false;
	}		
if(parseInt(patternRepeatAfter) > parseInt(maxDaysofMonth))
	{
	alert("Repeat Pattern After ,Cannot be Greater than "+maxDaysofMonth+" Days");
	document.getElementsByName("newPatternRepeatAfter")[0].focus();
	document.getElementsByName("newPatternRepeatAfter")[0].value="";
	return false;
	}	
   
  	//disabling the blanket 
	disableNewBlanket(e);
	
	var patternRepeatStartFrom =patternRepeatAfter-1;

	//	iterating throygh all emp

	//alert("patternRepeatStartFrom----"+patternRepeatStartFrom)
	//alert("maxNoOfEmployees----"+maxNoOfEmployees)

	for(var i=1 ; i <= parseInt(maxNoOfEmployees) ; i++)
	{
		var startId=i+"@"+1;
		var endId=i+"@"+patternRepeatStartFrom;

		//alert("patternRepeatFrom----"+patternRepeatFrom)
		//alert("patternRepeatTo----"+patternRepeatTo)
		//through all the days of the  month	
		//for(var j=parseInt(patternRepeatFrom) ; j <= parseInt(patternRepeatTo) ; j++)
		for(var j=parseInt(patternRepeatStartFrom) ; j <= parseInt(maxDaysofMonth) ; j++)
		{
			var checkBoxId="selectEmp_"+i;
			var buttonId=i+"@"+j;

			//alert("checkBoxId----"+checkBoxId)
			//alert("elem(checkBoxId)----"+document.getElementById(checkBoxId).checked)

			//if checkbox is checked 
			if(document.getElementById(checkBoxId).checked==true)
			{
				//alert("buttonId----"+document.getElementById(buttonId).disabled)
				
				//if button is not disabled and button id is greater than pattern repeatAfter		
				if(document.getElementById(buttonId).disabled!=true)// && j >=(parseInt(patternRepeatFrom)+parseInt(patternRepeatStartFrom)) )
				{
					var dayId;
					if(j< 10)
						dayId="0"+j;
					else
						dayId=j;
					var modulo=(j-patternRepeatFrom)%patternRepeatStartFrom+parseInt(patternRepeatFrom);
					//if(modulo==0)
					// modulo=patternRepeatStartFrom;
					//alert("modulo== "+modulo)

					//getting the pattern Id
					var patternId=i+"@"+modulo; //parseInt(j-patternRepeatStartFrom);
					//alert("buttonId-----"+buttonId);
					//alert("patternId-----"+patternId);
					//alert("value-----"+document.getElementById(patternId).value)

					//getting the array values of old button id 
					var buttonNameArray=document.getElementById(patternId).name.split("^");
					var buttonOldNameArray=document.getElementById(buttonId).name.split("^");
					//alert("buttonId-----"+buttonId+"--value---"+document.getElementById(buttonId).value)
					//alert("patternId----"+patternId+"--value---"+document.getElementById(patternId).value)
					//alert("buttonNameArrayValue---"+document.getElementById(patternId).name)
					//alert("buttonNameArray---"+buttonNameArray[7])
					
					//Constructing new button name
					//  1				   2	           3				     4					   5				    	6	       	        	  7   		   	        8 
					// EmpID        +    Day      +     ShiftId    +      shiftStartTime  +      shiftEndTime   +      Shift FullName +              ShiftType   + 		  DayCode
					var buttonNewName=buttonNameArray[0]+"^"+dayId+"^"+buttonNameArray[2]+"^"+buttonNameArray[3]+"^"+buttonNameArray[4]+"^"+buttonNameArray[5]+"^"+buttonNameArray[6]+"^"+buttonOldNameArray[7];
					//alert("buttonNewName----"+buttonNewName)	
					//putting the patternId  value into the next Button
					document.getElementById(buttonId).value=document.getElementById(patternId).value;
					//putting the patternId  Name into the next Button
					document.getElementById(buttonId).name=buttonNewName;
					//alert("buttonId last-----"+buttonId+"--value---"+document.getElementById(buttonId).value+"--name---"+document.getElementById(buttonId).name)
				}//inner if closed for checking of disablity of button
			}//outer if closed for checking of check box checked
		}//inner for closed 
	}//outer for closed 

}//if of click and enter key check 
   
}//fn closed 


function  validateMaxContinousOff(){

//getting the max days in the month and max no of employees 
var maxDaysofMonth=document.getElementById("maxDaysofMonth").value
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value
var maxContinousOffAllowed=document.getElementsByName("rosterCategory")[0].value.split("@")[2];


//maxDaysofMonth=10;

//alert("maxContinousOffAllowed-----"+maxContinousOffAllowed)

//alert("maxDaysofMonth----->"+maxDaysofMonth);
///alert("maxNoOfEmployees----->"+maxNoOfEmployees);

if(maxContinousOffAllowed=="-1")
	return true;

for(var i=1;i<=maxNoOfEmployees;i++)
{
  // alert(i);
  var checkBoxId="selectEmp_"+i; 
  
   var empNameDivId="empNameDivId_"+i;
  
  //alert(document.getElementById(empNameDivId));
  
  var empName=document.getElementById(empNameDivId).title;
  
if(document.getElementById(checkBoxId).checked==true)
  {  
 
  var maxConitnousOffCounter=1;
  
	for(var j=1;j<=maxDaysofMonth;j++)
	   {
	   
	   var buttonId=i+"@"+j;	   	   
	   var shiftType=document.getElementById(buttonId).name.split("^")[6];
	
		//alert("buttonId--------"+buttonId);
	 
	   
  //i.e. Day Off Shift-->0
if(parseInt(shiftType)==0){
	   
	   //checking for the Second Last Date of the month
	   if(j+1<=maxDaysofMonth){
	   
	   var buttonIdNext=i+"@"+(j+1);	   	   
	   var shiftTypeNext=document.getElementById(buttonIdNext).name.split("^")[6];

		//alert("shiftType----"+shiftType);
		//alert("shiftTypeNext----"+shiftTypeNext);
	
			//if the current and the next day shifts are equal then increase the counter
			if(parseInt(shiftType)==parseInt(shiftTypeNext)){
			//	alert("equal")
				maxConitnousOffCounter++;
											}//if of equality checking closed
											
						        }//if for 2nd last day of month closed
					}//if of day off shift closed					           
					else
					{
					//alert("no day off shift");
					maxConitnousOffCounter=1;//else if the shifttype is  not day off then resetting the counter		           
	    			}
	    
	   // alert("maxConitnousOffCounter-----"+maxConitnousOffCounter);
	  //  alert("maxContinousOffAllowed-----"+maxContinousOffAllowed);	
	    		
	    	if(maxConitnousOffCounter > maxContinousOffAllowed){
	    			alert("The Maximum Continous Off's allowed to any Employee[ "+empName+"] are "+maxContinousOffAllowed);
	    			return false;
	    		
	    		}//if for checking maxContinousOffAllowed closed
	        	
		   	
	   }/////for of days closed
	
	
  }	///if of checkboxId checked closed
	   
	   
	   
}	////for of emplist closed


return true;

} 

function validateMaxOff(){

//getting the max days in the month and max no of employees 
var maxDaysofMonth=document.getElementById("maxDaysofMonth").value
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value
var maxOffAllowed=document.getElementsByName("rosterCategory")[0].value.split("@")[1];

//alert("maxOffAllowed-----"+maxOffAllowed)

//alert("maxDaysofMonth----->"+maxDaysofMonth);
///alert("maxNoOfEmployees----->"+maxNoOfEmployees);

if(maxOffAllowed=="-1")
	return true;

for(var i=1;i<=maxNoOfEmployees;i++)
{
  // alert(i);
  var checkBoxId="selectEmp_"+i; 
  
   var empNameDivId="empNameDivId_"+i;
  
  //alert(document.getElementById(empNameDivId));
  
  var empName=document.getElementById(empNameDivId).title;
  

if(document.getElementById(checkBoxId).checked==true)
  {  
  var maxOffCounter=0;
  
	for(var j=1;j<=maxDaysofMonth;j++)
	   {
	   var buttonId=i+"@"+j;
	//	alert(buttonId);
	   
	   var shiftType=document.getElementById(buttonId).name.split("^")[6];
	   
	    if(shiftType=="0")//i.e. Day Off Shift
	   		maxOffCounter++;
	   		
	   //alert("maxOffCounter-----"+maxOffCounter)
	   		
	   if(maxOffCounter > maxOffAllowed){
	   	
	   	alert("The Maximum Off's allowed to any Employee["+empName+"] are "+maxOffAllowed);
	   	return false;
	   
	   		}//if maxOffCounter > maxOffAllowed closed 		   	
		   	
	   }/////for of days closed
	
	
  }	///if of checkboxId checked closed
	   
	   
	   
}	////for of emplist closed


return true;

}


function  validateNoOfHrsPerWeek() {

if(validateNoOfHrsOfWeek(1) &&
   validateNoOfHrsOfWeek(2) && 
   validateNoOfHrsOfWeek(3) && 
   validateNoOfHrsOfWeek(4) && 
   validateNoOfHrsOfWeek(5))
    return true;
   else
	return false;
}

function validateWeek(week){

//getting the max days in the month and max no of employees 
var maxDaysofMonth=document.getElementById("maxDaysofMonth").value
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value

//Calculating the start & end date of the particular week to validate

var startDate=7*week-6;
var endDate=7*week;

//Checking for the Last Week and then putting the maxDaysofMonth into the enddate
if(week==5)
	endDate=maxDaysofMonth;
	

//if it is a leap year and month is feb then only 4 weeks should be validated
//Since there is no 5th week 
//in that case startDate=29 and endDate=28 ,Hence should return true
//Particular Case for a Leap Year ,Feb=28 Days
if(startDate > maxDaysofMonth)
	return true;


//alert("maxDaysofMonth----->"+maxDaysofMonth);
///alert("maxNoOfEmployees----->"+maxNoOfEmployees);

//alert("startDate----"+startDate);
//alert("endDate----"+endDate);



//traversing through the total no. of employees
for(var i=1;i<=maxNoOfEmployees;i++)
{
  // alert(i);
  var checkBoxId="selectEmp_"+i; 
  
 //if the emp checkbox is checked
if(document.getElementById(checkBoxId).checked==true)
  {  
 
//traversing through the no. of days  
	for(var j=startDate;j<=endDate;j++)
	   {
	   
	   var buttonId=i+"@"+j;	   	   
	 //  alert(buttonId)
	   
	   var shiftType=document.getElementById(buttonId).name.split("^")[6];
		
	 //  alert("shiftType---"+shiftType)	

//i.e. if some Shift/Duty is Assigned to any emp i.e. Shift!='NA'
//then return true so that we need to validate that particular week
	if(shiftType!="-1")
		return true;	
	     	
		   	
	   }/////for of days closed
	   
				
  }	///if of checkboxId checked closed
	   
	  
	   
	   
}	////for of emplist closed

//if no shift is assigned to any employee for the particular date range
//then return false so that there is no need for checking that particular week 
return false;	


}

function  validateNoOfHrsOfWeek(week) {

//if of validate week started 
//if the week is validated i.e. if any duty is assigned to any emp for that particular week 
//then only will the hours be validated
if(validateWeek(week)){

//getting the max days in the month and max no of employees 
var maxDaysofMonth=document.getElementById("maxDaysofMonth").value
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value
var noOfHrsPerWeek=(document.getElementsByName("rosterCategory")[0].value.split("@")[3])*100;
var dayShiftTimingsArray=document.getElementById("shiftTimingsArray").value.split("^");


//Calculating the start & end date of the particular week to validate
var startDate=7*week-6;
var endDate=7*week;
var noOfDaysInWeek=0;

//Checking for the Last Week and then putting the maxDaysofMonth into the enddate
if(week==5)
	endDate=maxDaysofMonth;
	
//Calculating the No. of days in the week
noOfDaysInWeek=endDate-startDate+1;	

//if it is a leap year and month is feb then only 4 weeks should be validated
//Since there is no 5th week 
//in that case startDate=29 and endDate=28 ,Hence should return
if(startDate > maxDaysofMonth)
	return true;


//alert("startDate----"+startDate);
//alert("endDate----"+endDate);
//alert("noOfHrsPerWeek-----"+noOfHrsPerWeek);

//alert("maxDaysofMonth----->"+maxDaysofMonth);
///alert("maxNoOfEmployees----->"+maxNoOfEmployees);

if(noOfHrsPerWeek=="-1")
	return true;

for(var i=1;i<=maxNoOfEmployees;i++)
{
  // alert(i);
  var checkBoxId="selectEmp_"+i; 
  
  var empNameDivId="empNameDivId_"+i;
  
  //alert(document.getElementById(empNameDivId));
  
  var empName=document.getElementById(empNameDivId).title;
  
  var totalWeekTime=0;
  var countForDayOff_Leave=0;

if(document.getElementById(checkBoxId).checked==true)
  {  
 
  
	for(var j=startDate;j<=endDate;j++)
	   {
	   
	   var buttonId=i+"@"+j;	   	   
	 //  alert(buttonId)
	   
	   var shiftType=document.getElementById(buttonId).name.split("^")[6];
		
	 //  alert("shiftType---"+shiftType)	

//ie if shift is Dayoff ---> shiftType=="0"
//or
//if the person is on Leave or if it is a Gazetted Holiday -->document.getElementById(buttonId).disabled==false
//or the roster is offical or holiday roster then again checking-->document.getElementById(buttonId).disabled==false


//if(shiftType=="0" || document.getElementById(buttonId).disabled==true)

if(document.getElementById(buttonId).disabled==true)	
	countForDayOff_Leave++;

//ie Dayoff shift or Not Assigned Shift
	   
if(shiftType!="0" && shiftType!="-1"){
	   
	   var startTimeArr=document.getElementById(buttonId).name.split("^")[3].split(":");
	   var endTimeArr=document.getElementById(buttonId).name.split("^")[4].split(":");
	   
	   //i.e. if the shift type is day shift then it's timings will be different for each day
 if(shiftType=="4"){
	   
	   var dayCode=document.getElementById(buttonId).name.split("^")[7];
	   
	//  alert("dayCode---"+dayCode);
	   
	   var dayShiftStartEndTimeArray=dayShiftTimingsArray[dayCode-1].split("@");
	   
	 //  alert("dayShiftStartEndTimeArray---"+dayShiftStartEndTimeArray);
	   
	   startTimeArr=dayShiftStartEndTimeArray[0].split(":");
	   endTimeArr=dayShiftStartEndTimeArray[1].split(":");
	   }
	   
	   
	 //  alert(startTimeArr)
	//   alert(endTimeArr)
	   
	 //  var startTimeStr=startTimeArr[0]+startTimeArr[1];
	 //  var endTimeStr=endTimeArr[0]+endTimeArr[1];
	   
	 //  alert(startTimeStr);
	 //  alert(endTimeStr);
	    
	   var startTime=eval(startTimeArr[0]+startTimeArr[1]);
	   var endTime=eval(endTimeArr[0]+endTimeArr[1]);
	
		//alert("startTime--"+startTime);
		//alert("endTime--"+endTime);
	     
	    var timeDiff
	        	
	    if(startTime > endTime)
	   		 {
	   		 timeDiff=(2400-startTime)+endTime;
	   		 }
	   		 else
	   		 {
	   		 timeDiff=endTime-startTime;
	   		 }
	        
	      //  alert("timeDiff----"+timeDiff);	
	        
	        totalWeekTime+=timeDiff;
	        	
		 
		   		}
		   	
		   	
	   }/////for of days closed
	   
	//   alert("countForDayOff_Leave----"+countForDayOff_Leave);
	  
	  var noOfDaysToLessInWeek=countForDayOff_Leave + (7-noOfDaysInWeek);
	
	//  alert("noOfDaysToLessInWeek----"+noOfDaysToLessInWeek);
	
	 var cumulativeTime=parseInt(noOfHrsPerWeek*(1-noOfDaysToLessInWeek/7));
	   
	  // alert("cumulativeTime---"+cumulativeTime)
	  // alert("totalWeekTime----"+totalWeekTime)
	   
if(cumulativeTime > totalWeekTime){
	   var check=confirm("No of Hours of "+empName+" for Week"+week+" is less than "+(noOfHrsPerWeek/100)+" Hours/Week  \n \t\t\t Do You still want to Proceed")
	   
	   if(check==false)
	   		return false;
	   }
	   
	
				
    	}	///if of checkboxId checked closed
	   
  }	////for of emplist closed
	
}//if of validateweek closed 
//else
//alert("Could not validate the week as no duty is assigned to any emp for week"+week)

return true;	


}

function validateShiftCapacity(){

//getting the max days in the month and max no of employees 
var maxDaysofMonth=document.getElementById("maxDaysofMonth").value
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value
var areaShiftCapacityArray=document.getElementsByName("areaCode")[0].value.split("@");

var morningShiftId=document.getElementsByName("morningShiftId")[0].value;
var eveningShiftId=document.getElementsByName("eveningShiftId")[0].value;
var nightShiftId=document.getElementsByName("nightShiftId")[0].value;
var dayShiftId=document.getElementsByName("dayShiftId")[0].value;


//Getting the different capacities
var MORNING_MIN_CAPACITY=areaShiftCapacityArray[morningShiftId];
var EVENING_MIN_CAPACITY=areaShiftCapacityArray[eveningShiftId];
var NIGHT_MIN_CAPACITY=areaShiftCapacityArray[nightShiftId];
var DAY_MIN_CAPACITY=areaShiftCapacityArray[dayShiftId];

//Flags for checking wheteher the capacities for the particular week have been 
//validated atleast once

var morningCapacityFlag=true;
var eveningCapacityFlag=true;
var nightCapacityFlag=true;
var dayCapacityFlag=true;





//alert("maxDaysofMonth----->"+maxDaysofMonth);
///alert("maxNoOfEmployees----->"+maxNoOfEmployees);

//iterating through the total no. of days
for(var j=1;j<=maxDaysofMonth;j++)
	   {
	   
//Declaring the various counters for the different shifts  
	   
var morningShiftCapacityCounter=0;
var eveningShiftCapacityCounter=0;
var nightShiftCapacityCounter=0;
var dayShiftCapacityCounter=0;

//iterating through the total no of employees
for(var i=1;i<=maxNoOfEmployees;i++)
{

 
  var checkBoxId="selectEmp_"+i; 
  
 //if the employee  is selected
if(document.getElementById(checkBoxId).checked==true)
   {    
  
	
	   var buttonId=i+"@"+j;
	//	alert(buttonId);
	   
	   var shiftType=document.getElementById(buttonId).name.split("^")[6];
	   
	   if(shiftType==morningShiftId) 
	   			morningShiftCapacityCounter++;
	   			else
	   if(shiftType==eveningShiftId) 
	   			eveningShiftCapacityCounter++;
	   			else
	   if(shiftType==nightShiftId) 
	   			nightShiftCapacityCounter++;
	   			else
	   if(shiftType==dayShiftId) 
	   			dayShiftCapacityCounter++;
	  
	  }//if of emp selected closed   			
	   		   
	   
	   
 }	////for of emplist closed
	
	
	//alert("morningShiftCapacityCounter----"+morningShiftCapacityCounter);
	//alert("MORNING_MIN_CAPACITY----"+MORNING_MIN_CAPACITY);
	//alert("eveningShiftCapacityCounter----"+eveningShiftCapacityCounter);
	//alert("EVENING_MIN_CAPACITY----"+EVENING_MIN_CAPACITY);
	//alert("nightShiftCapacityCounter----"+nightShiftCapacityCounter);
	//alert("NIGHT_MIN_CAPACITY----"+NIGHT_MIN_CAPACITY);
	//alert("dayShiftCapacityCounter----"+dayShiftCapacityCounter);
	//alert("DAY_MIN_CAPACITY----"+DAY_MIN_CAPACITY);
	
	if(morningCapacityFlag==true && ( morningShiftCapacityCounter < MORNING_MIN_CAPACITY) ){
			morningCapacityFlag=false;
			var check=confirm("The Morning Capacity  should not be less than the Minimum Morning Capacity="+MORNING_MIN_CAPACITY+" \n \t\t\t\t\t Do You still want to Proceed");
			if(check==false)
				return false;			
			}
			
	if(eveningCapacityFlag==true && ( eveningShiftCapacityCounter < EVENING_MIN_CAPACITY) ){
			eveningCapacityFlag=false;
			var check=confirm("The  Evening Capacity should not be less than the Minimum Evening Capacity="+EVENING_MIN_CAPACITY+" \n \t\t\t\t\t Do You still want to Proceed");
			if(check==false)
				return false;			
			}
			
	if(nightCapacityFlag==true && (nightShiftCapacityCounter < NIGHT_MIN_CAPACITY) ){
			nightCapacityFlag=false;
			var check=confirm("The  Night Capacity  should not be less than the Minimum Night Capacity="+NIGHT_MIN_CAPACITY+" \n \t\t\t\t\t Do You still want to Proceed");
			if(check==false)
				return false;			
			}
			
	if(dayCapacityFlag==true && (dayShiftCapacityCounter < DAY_MIN_CAPACITY) ){
			dayCapacityFlag=false;
			var check=confirm("The  Day Capacity  should not be less than the Minimum Day Capacity="+DAY_MIN_CAPACITY+" \n \t\t\t\t\t Do You still want to Proceed");
			if(check==false)
				return false;			
			}
	
//if all the Minimum Shift Capacities have been found to be invalidated atleast onnce then don't need to check further
		if(morningCapacityFlag==false && eveningCapacityFlag==false && nightCapacityFlag==false && dayCapacityFlag==false)	
				return true;
			

 }/////for of days closed

return true;

}

////Function showing the title for showing or hiding the generated roster///

function showTitle(obj){

var expandRoster=document.getElementById("expandRoster").value;

if(expandRoster=="1")
	obj.title="Expand All Generated Roster"
	else
if(expandRoster=="0")	
	obj.title="Contract All Generated Roster"
}

////Function for hiding & showing the complete generated roster//////

function expandCollapseRoster(obj){
	

var expandRoster=document.getElementById("expandRoster").value;

var rosterGenList=document.getElementById("rosterGenList").value.split("@");
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value;

var displayType="";

//alert("expandRoster1 ="+expandRoster)


//in case we are expanding the generated roster  
//if expandRoster==1 --->then we are expanding else
//if expandRoster==0 --->then we are contracting

if(expandRoster=="1"){
	document.getElementById("expandCollapseRosterId").src="/HIS/hisglobal/images/avai/mi_small.gif";
	displayType="";
	document.getElementById("expandRoster").value="0";
	}
	else
if(expandRoster=="0"){//in case we are collapsing the generated roster
	
	document.getElementById("expandCollapseRosterId").src="/HIS/hisglobal/images/avai/pl_small.gif";
	displayType="none";
	document.getElementById("expandRoster").value="1";
	}
	
//alert(displayType);	
//alert("expandRoster2 ="+expandRoster)
	
//setting the display to block for all the td of the date	
for(var i=0;i< rosterGenList.length;i++){
	var rosterDay=rosterGenList[i];
	var dateTdId="dateTdId"+rosterDay;
	//alert("dateTdId---"+dateTdId);	
	document.getElementById(dateTdId).style.display=displayType;	
	}

//setting the display to block for all the td of the buttons
for(var i=1; i <=maxNoOfEmployees;i++){

for(var j=0;j< rosterGenList.length;j++){
	
	var rosterDay=rosterGenList[j];
	var buttonTdId="buttonTdId"+i+"@"+rosterDay;	
	document.getElementById(buttonTdId).style.display=displayType;	
	}


}


}

/////Function for showing title for  range of generated roster buttons

function showTitleRange(obj){
var arrayOfRange=obj.id.split("_")[1].split("@");
var range=arrayOfRange[0]+"-"+arrayOfRange[1];
var hiddenBoxId="hiddenBoxId_"+arrayOfRange[0]+"@"+arrayOfRange[1];
var expandRoster=document.getElementById(hiddenBoxId).value;

if(expandRoster=="1")
	obj.title="Expand Roster for "+range+" Days";
else
	obj.title="Contract Roster for "+range+" Days";
}


/////Function for hiding/contracting  individual range of generated roster buttons


function expandCollapseRosterRange(obj){

var arrayOfRange=obj.id.split("_")[1].split("@");
var startDate=parseInt(arrayOfRange[0]);
var endDate=parseInt(arrayOfRange[1]);

var hiddenBoxId="hiddenBoxId_"+startDate+"@"+endDate;

var expandRoster=document.getElementById(hiddenBoxId).value;
var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value;

//alert("hiddenBoxId ="+hiddenBoxId)
//alert("expandRoster ="+expandRoster)
//alert("startDate ="+startDate)
//alert("endDate ="+endDate)


//in case we are expanding the generated roster  
//if expandRoster==1 --->then we are expanding else
//if expandRoster==0 --->then we are contracting

if(expandRoster=="1"){
	document.getElementById(obj.id).src="/HIS/hisglobal/images/avai/mi_small.gif";
	displayType="";
	document.getElementById(hiddenBoxId).value="0";
	}
	else
if(expandRoster=="0"){//in case we are collapsing the generated roster
	
	document.getElementById(obj.id).src="/HIS/hisglobal/images/avai/pl_small.gif";
	displayType="none";
	document.getElementById(hiddenBoxId).value="1";
	}
	
	


//setting the display to block for all the td of the date	
for(var i=startDate;i<=endDate;i++){
	
	var dateTdId="dateTdId"+i;
	//alert("dateTdId---"+dateTdId);	
	document.getElementById(dateTdId).style.display=displayType;	
	}

//alert("hiii   "+i);
//alert("maxNoOfEmployees   "+maxNoOfEmployees);

//setting the display to block for all the td of the buttons
for(var i=1;i<=maxNoOfEmployees;i++){

//alert("i--"+i);
for(var j=startDate;j<=endDate;j++){

//alert("j--"+j);
	
	var buttonTdId="buttonTdId"+i+"@"+j;	
//	alert("buttonTdId---"+buttonTdId);	
	
	document.getElementById(buttonTdId).style.display=displayType;	
								}//inner for closed

						}//outer for closed 

	//alert("byee");
}//function expandCollapseRosterRange closed



