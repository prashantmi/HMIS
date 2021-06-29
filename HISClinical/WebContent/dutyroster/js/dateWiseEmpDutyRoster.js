function getNewRoster()
{
document.forms[0].modeOfRoster.value='NEW';
document.forms[0].hmode.value='GET_EMPLOYEES_CALENDAR';
document.forms[0].submit();
}

function modifyRoster()
{

var   valFromDateOld=document.getElementsByName('startDateOld')[0];
var   valToDateOld=document.getElementsByName('endDateOld')[0];
var   valSysDate=document.getElementsByName('sysDate')[0];   


var check=false;

for(var i=0 ; i <document.getElementsByName("distinctRoster").length ; i++)
	{
	if(document.getElementsByName("distinctRoster")[i].checked==true)
		check=true;
		
	}

//alert(valFromDateOld.value);
//alert(valToDateOld.value);
//alert(valSysDate.value);

if(check==true)
	{
	
if(document.getElementsByName("isGenerated")[0].value=="1")
		{
		alert("Roster is Generated ,Hence Cannot be Modified");
		}
		else 
if(compareDateCall(valSysDate,valFromDateOld,2,"Cannot be Modified ,because Sysdate "," From Date") && compareDateCall(valSysDate,valToDateOld,2,"Sysdate "," ToDate"))
		{
	document.forms[0].modeOfRoster.value='MODIFY';
	document.forms[0].hmode.value='MODIFY';
	document.forms[0].submit();
		}
	}
	else
	alert("Please Select an Option");
}

function showReport()
{

var   valFromDateOld=document.getElementsByName('startDateOld')[0];
var   valToDateOld=document.getElementsByName('endDateOld')[0];
var   valSysDate=document.getElementsByName('sysDate')[0];   


var check=false;

for(var i=0 ; i <document.getElementsByName("distinctRoster").length ; i++)
	{
	if(document.getElementsByName("distinctRoster")[i].checked==true)
		check=true;
		
	}

//alert(valFromDateOld.value);
//alert(valToDateOld.value);
//alert(valSysDate.value);

if(check==true)
	{
	document.forms[0].modeOfRoster.value='REPORT';
	document.forms[0].hmode.value='REPORT';
	document.forms[0].submit();
	}
	else
	alert("Please Select an Option");
}

function generateRoster()
{


var check=false;

for(var i=0 ; i <document.getElementsByName("distinctRoster").length ; i++)
	{
	if(document.getElementsByName("distinctRoster")[i].checked==true)
		check=true;
	
	}

if(check==true)
	{
	
	if(document.getElementsByName("isGenerated")[0].value=="1")
		{
		alert("Roster is Already Generated");
		}
		else
		{
	document.forms[0].modeOfRoster.value='GENERATE';
	document.forms[0].hmode.value='GENERATE';
	document.forms[0].submit();
		}
		
	}
	else
	alert("Please Select an Option");
}

function submitFormData()
{
if(document.forms[0].hmode.value!="")
	document.forms[0].submit();
}

function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}
function validateDates()
{
var flag=true;

var   valFromDate=document.getElementsByName('startDate')[0];
var   valToDate=document.getElementsByName('endDate')[0];
var   valSysDate=document.getElementsByName('sysDate')[0];   
var   modeOfRoster=document.getElementsByName('modeOfRoster')[0].value;
   
var   valFromDateOld=document.getElementsByName('startDateOld')[0];
var   valToDateOld=document.getElementsByName('endDateOld')[0];
   
//alert(valFromDate.value);
//alert(valToDate.value);
//alert(valSysDate.value);  
   
   if(modeOfRoster=="NEW" && !(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valSysDate,valFromDate,2,"System Date","From Date")))
		{
		//alert('new');
		flag=false
		}
		else
if(modeOfRoster=="MODIFY")
  {			
  /*
  
  alert("valFromDateOld--"+valFromDateOld.value);
  alert("valFromDate--"+valFromDate.value);
  alert("valToDateOld--"+valToDateOld.value);
  alert("valToDate--"+valToDate.value);
  
	if(valFromDateOld.value!=valFromDate.value)	
		{
		flag=false;
		alert("From Date is not Allowed to Change");
		}
		else
	if(valToDateOld.value!=valToDate.value)	
		{
		flag=false;
		alert("To Date is not Allowed to Change");
		}
		*/
  }
  
 //alert(flag);
 
 return flag;
}

function validateFinalSubmit(){
     
 //   These All Fields are Mandatory
 var flag=false;
 
  if( 
 	comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")
 && comboValidation(document.getElementsByName('rosterId')[0],"Roster Name")
 && comboValidation(document.getElementsByName('areaCode')[0],"Duty Area")
 && comboValidation(document.getElementsByName('designationId')[0],"Designation")
 && isEmpty(document.getElementsByName('startDate')[0],"From Date")
 && isEmpty(document.getElementsByName('endDate')[0],"To Date")
 && validateDates()
	)
	{
	flag=true;
	}
	  
   
    return flag;
} 	
	
function finalSubmit(mode)
{
var flag=true;


	if (validateFinalSubmit() && validateShiftCapacity()) 
	{
		  concateDataToBeSaved();
 	     
//validating whether any shift is selected for an employeee
	
	if(document.getElementsByName("concatedValueToBeSaved")[0].value!="")
		{
		//alert("hii");
     submitPage(mode);
    	
    	 }
    	 else
    	 	   alert("Please Select Some Shift For an Employee");
    	
	}
}




function clearForm()
 {
  	document.getElementsByName('startDate')[0].value="";
    document.getElementsByName('endDate')[0].value="";
    document.getElementsByName('rosterCategory')[0].value="-1";    
    document.getElementsByName('rosterId')[0].value="-1";
    document.getElementsByName('areaCode')[0].value="-1";
    document.getElementsByName('designationId')[0].value="-1"; 
    submitPage('NEW'); 
        
 }

//Function for Changing Shift Types 
function changeShiftType(these)
{

var flag=false;
var buttonId=these.id;

   
var buttonNameArray=these.name.split("@");
var currentShiftId=buttonNameArray[2];
var index=-1;
//alert("currentShiftId--->"+currentShiftId);


var shiftIdArray=document.getElementById("shiftIdArray").value.split("^")
var shiftNameArray=document.getElementById("shiftNameArray").value.split("^");
var shiftStartTimeArray=document.getElementById("shiftStartTimeArray").value.split("^")
var shiftEndTimeArray=document.getElementById("shiftEndTimeArray").value.split("^");
var shiftFullNameArray=document.getElementById("shiftFullNameArray").value.split("^");
var shiftTypeArray=document.getElementById("shiftTypeArray").value.split("^");


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



 
 
 //Getting the next value of shift based on next index.
 
 	var nextShiftId=shiftIdArray[index];
	var nextShiftName=shiftNameArray[index];
	var nextShiftStartTime=shiftStartTimeArray[index];
	var nextShiftEndTime=shiftEndTimeArray[index];
	var nextFullName=shiftFullNameArray[index];
	var nextShiftType=shiftTypeArray[index];
	
	
 //Constructing new button name
//                  EmpID           +        EmpName            +  ShiftId    +   shiftStartTime  +    shiftEndTime   +     nextFullName  +  nextShiftType
var buttonNewName=buttonNameArray[0]+"@"+buttonNameArray[1]+"@"+nextShiftId+"@"+nextShiftStartTime+"@"+nextShiftEndTime+"@"+nextFullName+"@"+nextShiftType;

//alert("buttonNewName------->"+buttonNewName);
//alert("nextShiftName------->"+nextShiftName);
 
document.getElementById(buttonId).name=buttonNewName;
document.getElementById(buttonId).value=nextShiftName;
		  
	
}


//Function for concatinating the data to be saved 
function concateDataToBeSaved()
{

var maxNoOfEmployees=document.getElementById("maxNoOfEmployees").value

var valueTobeSaved="";
var concatedValueToBeSaved="";

//alert("maxNoOfEmployees----->"+maxNoOfEmployees);


for(i=1;i<=maxNoOfEmployees;i++)
{

  // alert(i);
  var checkBoxId="selectEmp_"+i; 
  
 //    alert(checkBoxId);
  
  ///now checking whether the particular checkbox is 
  ////clicked or not and hence whether to save the data or not
  
  
if(document.getElementById(checkBoxId).checked==true)
  { 


 	   var buttonId=i;
		//alert(buttonId);
	   var arrayOfValues=document.getElementById(buttonId).name.split("@");
	   
	   if(arrayOfValues[2]!="-1")
	   {
	    valueTobeSaved=document.getElementById(buttonId).name;
	//  	 alert(valueTobeSaved); 
	   	concatedValueToBeSaved+=valueTobeSaved+"#";
	//   alert(concatedValueToBeSaved);
  	   }	
  
  }
	   
	
	   
}	
   
 //alert("concatedValueToBeSaved-----"+concatedValueToBeSaved);
   
document.getElementsByName("concatedValueToBeSaved")[0].value=concatedValueToBeSaved;	   
	   



}  
      
       
 function showShiftName(these)
 {
var arrayOfData=these.name.split("@");
 these.title=arrayOfData[5];
 } 
  
 function selectEmployee(these)
 {
 

 var checkBoxId=these.id
 
 //alert(checkBoxId)
 
var idArray=checkBoxId.split("_");



if(document.getElementById(checkBoxId).checked==true)
	{
	//alert("chekced")
	
	
var buttonId=idArray[1];

//alert("buttonId----"+buttonId)
document.getElementById(buttonId).disabled=false;

	
	}
else
	{
//	alert("unchekced")
	
	
var buttonId=idArray[1];

//alert("buttonId----"+buttonId)
document.getElementById(buttonId).disabled=true;
	
	
	}

	
 
 }      


function selectAllEmp(these)
 {

 if(these.checked==true)
 	{
 	for(var i=0; i< document.getElementsByName("selectEmp").length;i++)
 			{
 			var obj=document.getElementsByName("selectEmp")[i]; 			
 			document.getElementsByName("selectEmp")[i].checked=true;
 			selectEmployee(obj);
 			
 			}
 	}
 	else
 if(these.checked==false)
 	{
 	for(var i=0; i< document.getElementsByName("selectEmp").length;i++)
 			{
 			var obj=document.getElementsByName("selectEmp")[i]; 			
 			document.getElementsByName("selectEmp")[i].checked=false;
 			selectEmployee(obj);
 			
 			}
 	}
 	
 
 
 }
 function setGeneratedRosterId(these)
 {
 var arrayData=these.value.split("@");
 
 document.getElementsByName("startDateOld")[0].value=arrayData[0]
 document.getElementsByName("endDateOld")[0].value=arrayData[1]
 document.getElementsByName("generatedRosterId")[0].value=arrayData[2]
 document.getElementsByName("isGenerated")[0].value=arrayData[3]
 
 
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

function validateShiftCapacity(){

//getting the max days in the month and max no of employees 

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


///alert("maxNoOfEmployees----->"+maxNoOfEmployees);


//Declaring the various counters for the different shifts  
	   
var morningShiftCapacityCounter=0;
var eveningShiftCapacityCounter=0;
var nightShiftCapacityCounter=0;
var dayShiftCapacityCounter=0;

for(var i=1;i<=maxNoOfEmployees;i++)
{

  
  
	
	   var buttonId=i;
	//	alert(buttonId);
	   
	   var shiftType=document.getElementById(buttonId).name.split("@")[6];
	   
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
	  
	   			
	   		   
	   
	   
	}	////for of emplist closed
	
	
	//alert("morningShiftCapacityCounter----"+morningShiftCapacityCounter);
	//alert("MORNING_MIN_CAPACITY----"+MORNING_MIN_CAPACITY);
	//alert("eveningShiftCapacityCounter----"+eveningShiftCapacityCounter);
	//alert("EVENING_MIN_CAPACITY----"+EVENING_MIN_CAPACITY);
	//alert("nightShiftCapacityCounter----"+nightShiftCapacityCounter);
	//alert("NIGHT_MIN_CAPACITY----"+NIGHT_MIN_CAPACITY);
	//alert("dayShiftCapacityCounter----"+dayShiftCapacityCounter);
	//alert("DAY_MIN_CAPACITY----"+DAY_MIN_CAPACITY);
	
	if(morningShiftCapacityCounter < MORNING_MIN_CAPACITY){
			var check=confirm("The Minimum Morning Capacity  should not be less than the Minimum Capacity="+MORNING_MIN_CAPACITY+" \n \t\t\t\t\t Do You still want to Proceed");
			if(check==false)
				return false;			
			}
			
	if(eveningShiftCapacityCounter < EVENING_MIN_CAPACITY){
			var check=confirm("The Minimum Evening Capacity  should not be less than the Minimum Capacity="+EVENING_MIN_CAPACITY+" \n \t\t\t\t\t Do You still want to Proceed");
			if(check==false)
				return false;			
			}
			
	if(nightShiftCapacityCounter < NIGHT_MIN_CAPACITY){
			var check=confirm("The Minimum Night Capacity  should not be less than the Minimum Capacity="+NIGHT_MIN_CAPACITY+" \n \t\t\t\t\t Do You still want to Proceed");
			if(check==false)
				return false;			
			}
			
	if(dayShiftCapacityCounter < DAY_MIN_CAPACITY){
			var check=confirm("The Minimum Day Capacity  should not be less than the Minimum Capacity="+DAY_MIN_CAPACITY+" \n \t\t\t\t\t Do You still want to Proceed");
			if(check==false)
				return false;			
			}
	
			
			



return true;

}