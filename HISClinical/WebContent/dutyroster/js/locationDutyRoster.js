  function backButton() 
 { 
  history.back(); 
 }
 
 
function getNewRoster()
{
document.forms[0].modeOfRoster.value='NEW';
document.forms[0].hmode.value='GET_LOCATION_WISE_ROSTER';
document.forms[0].submit();
}
function modifyRoster()
{
var check=false;
var arrayOfValues;
var isGenerated=0;


for(var i=0 ; i <document.getElementsByName("distinctRoster").length ; i++)
	{
	if(document.getElementsByName("distinctRoster")[i].checked==true)
		{
		check=true;
		arrayOfValues=document.getElementsByName("distinctRoster")[i].value.split("@");
		isGenerated=arrayOfValues[2];
		}
	}


if(check==true && isGenerated==0)
	{
	document.forms[0].modeOfRoster.value='MODIFY';
	document.forms[0].hmode.value='MODIFY';
	document.forms[0].submit();
	}
	else
if(check==true && isGenerated==1)	
	alert("Roster is Generated,So it Cannot be Modified")
	else
	alert("Please Select an Option");
}


function generateRoster()
{
var check=false;
var arrayOfValues;
var isGenerated=0;

for(var i=0 ; i <document.getElementsByName("distinctRoster").length ; i++)
	{
	if(document.getElementsByName("distinctRoster")[i].checked==true)
		{
		check=true;
		arrayOfValues=document.getElementsByName("distinctRoster")[i].value.split("@");
		isGenerated=arrayOfValues[2];
		}
	}



if(check==true && isGenerated==0)
	{
	document.forms[0].modeOfRoster.value='GENERATE';
	document.forms[0].hmode.value='GENERATE';
	document.forms[0].submit();
	}
	else
if(check==true && isGenerated==1)	
	alert("Roster is Already Generated")
	else
	alert("Please Select an Option");
}



function generateReport()
{
var check=false;
var arrayOfValues;
var isGenerated=0;

for(var i=0 ; i <document.getElementsByName("distinctRoster").length ; i++)
	{
	if(document.getElementsByName("distinctRoster")[i].checked==true)
		{
		check=true;
		arrayOfValues=document.getElementsByName("distinctRoster")[i].value.split("@");
		isGenerated=arrayOfValues[2];
		}
	}



if(check==true && isGenerated==1)
	{
	document.forms[0].modeOfRoster.value='REPORT';
	document.forms[0].hmode.value='REPORT';
	document.forms[0].submit();
	}
	else
if(check==true && isGenerated==0)	
	alert("Roster is Not Generated,So it's Report Cannot be Generated")
	else
	alert("Please Select an Option");
}


function submitRosterType(mode)
{
	document.forms[0].areaCode.value=-1;
	submitPage(mode);
}
function submitAreaCode(mode)
{
	document.forms[0].modeOfRoster.value="";
	submitPage(mode);
}
 
function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
 //   These All Fields are Mandatory
 var flag=true;
 
   valFromDate=document.getElementsByName('fromDate')[0];
   valToDate=document.getElementsByName('toDate')[0];
   valSysDate=document.getElementsByName('sysDate')[0];   
   valFromDateOld=document.getElementsByName('startDateTimeOld')[0];
   valToDateOld=document.getElementsByName('endDateTimeOld')[0];
   
   
   
   
if(compareDateCallWithoutAlert(valFromDateOld,valFromDate,2,"valFromDateOld "," valFromDate") && compareDateCallWithoutAlert(valFromDate,valToDateOld,2,"valFromDateOld "," valToDateOld"))
       document.getElementsByName("fromDateCheck")[0].value=true;
      
     
if(compareDateCallWithoutAlert(valFromDateOld,valToDate,2,"valFromDateOld "," valToDate") && compareDateCallWithoutAlert(valToDate,valToDateOld,2,"valToDate "," valToDateOld"))
      document.getElementsByName("toDateCheck")[0].value=true;
   
   
 
if(document.forms[0].rosterId.value=="-1")
   {
		alert("Select Roster Type ");
		document.forms[0].rosterId.focus();
		flag=false;
	}
	else
if(document.forms[0].areaCode.value=="-1")
	   {
		alert("Select Duty Area ");
		document.forms[0].areaCode.focus();
		flag=false;
		}
   else
if(document.forms[0].fromDate.value=="")
	   {
		alert("Please Enter From Date");
		document.forms[0].fromDate.focus();
		flag=false;
		}
		else
if(document.forms[0].toDate.value=="")
	   {
		alert("Please Enter To Date");
		document.forms[0].toDate.focus();
		flag=false;
		}
		else
if(!(compareDateCall(valFromDate,valToDate,2,"From Date","To Date") && compareDateCall(valSysDate,valFromDate,2,"System Date","From Date")))
		{
		flag=false
		}
		
	
	

		
    return flag;
} 	
	
function finalSubmit(mode)
{
	if (validateFinalSubmit() && validateShiftCapacity()) 
	{
		  concateDataToBeSaved();
 		  submitPage(mode);
    	
	}
}

function validatePositiveIntegerOnly(obj,e)

      {// Ascii Code 0 - 48 To 9 - 57

       var k=e.charCode;

          //alert(e.keyCode+" " +e.charCode+"\n");

        if( e.keyCode!=0 || ( k>=48 && k<= 57 ) )

          return true;

           else

         return false;

  }
  
  
  
function clearForm()
 {
  	document.getElementsByName('rosterId')[0].value="-1";
    document.getElementsByName('areaCode')[0].value="-1";
    document.getElementsByName('fromDate')[0].value="";
    document.getElementsByName('toDate')[0].value="";
   submitPage('NEW'); 
        
 }

 function concateDataToBeSaved()
{
var maxNoOfAreaMapped=document.getElementById("maxNoOfAreaMapped").value;
var maxNoOfShiftsMapped=document.getElementById("maxNoOfShiftsMapped").value;
var maxNoOfDesignation=document.getElementById("maxNoOfDesignation").value;

var textBoxName="";
var textBoxValue="";
var textBoxNameAndValue="";
var concatedValueToBeSaved="";


//alert("maxNoOfAreaMapped----->"+maxNoOfAreaMapped);
//alert("maxNoOfShiftsMapped----->"+maxNoOfShiftsMapped);
//alert("maxNoOfDesignation----->"+maxNoOfDesignation);

for(i=1;i<=maxNoOfAreaMapped;i++)
{
   //alert(i);
  for(j=1;j<=maxNoOfDesignation;j++)
  	{
  // alert(j);
	for(k=1;k<=maxNoOfShiftsMapped;k++)
	   {
	   var textBoxId=i+"@"+j+"@"+k;
	//	alert("textBoxId----------->"+textBoxId);
	   
	    textBoxName=document.getElementById(textBoxId).name;
	      	// alert("textBoxName------>"+textBoxName);
	    textBoxValue=document.getElementById(textBoxId).value;
		//	 alert("textBoxValue----->"+textBoxValue);	     
			
			if(textBoxValue=="")
				textBoxValue=0;
			
		textBoxNameAndValue=textBoxName+"@"+textBoxValue;
	//	  	 alert("textBoxNameAndValue------>"+textBoxNameAndValue);
			    
	   	concatedValueToBeSaved+=textBoxNameAndValue+"#";
	   //alert("concatedValueToBeSaved-------->"+concatedValueToBeSaved);
		
	//	alert("i----"+i+"----j----"+j+"----k-----"+k);   
	   }
	
	
	}  
}	
	//	alert("i----"+i+"----j----"+j);   
	
document.getElementsByName("concatedValueToBeSaved")[0].value=concatedValueToBeSaved;	   
	   



}  

     
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


       
function focusOnLoad()
{
if(document.forms[0].elements[0].value=="-1^-1")
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

}  

function validateShiftCapacity(){

//getting the max days in the month and max no of employees 
var maxNoOfAreaMapped=document.getElementById("maxNoOfAreaMapped").value
var maxNoOfDesignation=document.getElementById("maxNoOfDesignation").value
var maxNoOfShiftsMapped=document.getElementById("maxNoOfShiftsMapped").value

//getting the area shift capacity array
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


//alert("maxOffAllowed-----"+maxOffAllowed)

//alert("maxDaysofMonth----->"+maxDaysofMonth);
///alert("maxNoOfEmployees----->"+maxNoOfEmployees);

for(var i=1;i<=maxNoOfAreaMapped;i++)
	   {
	   
for(var j=1;j<=maxNoOfDesignation;j++)
	   {


for(var k=1;k<=maxNoOfShiftsMapped;k++)
{

  
  
	
	   var textBoxId=i+"@"+j+"@"+k;
 	   var shiftType=document.getElementById(textBoxId).name.split("@")[6];
	   var capacity=document.getElementById(textBoxId).value;
	
	//alert(textBoxId);  
	//alert("shiftType---"+shiftType);
	//alert("capacity---"+capacity);	
	//alert("morningShiftId---"+morningShiftId);
	//alert("MORNING_MIN_CAPACITY---"+MORNING_MIN_CAPACITY);	
	
	
	
	
	if(shiftType==morningShiftId && capacity < MORNING_MIN_CAPACITY){
			var check=confirm("The  Morning Capacity should not be less than the Minimum Morning Capacity="+MORNING_MIN_CAPACITY+" \n \t\t\t\t Do You still want to Proceed");
			document.getElementById(textBoxId).focus();			
			if(check==false)
				return false;			
			}
			else
	if(shiftType==eveningShiftId && capacity  < EVENING_MIN_CAPACITY){
			var check=confirm("The  Evening Capacity should not be less than the Minimum Evening Capacity="+EVENING_MIN_CAPACITY+" \n \t\t\t\t Do You still want to Proceed");
			document.getElementById(textBoxId).focus();
			if(check==false)
				return false;			
			}
			else
	if(shiftType==nightShiftId && capacity < NIGHT_MIN_CAPACITY){
			var check=confirm("The  Night Capacity should not be less than the Minimum Night Capacity="+NIGHT_MIN_CAPACITY+" \n \t\t\t\t Do You still want to Proceed");
			document.getElementById(textBoxId).focus();
			if(check==false)
				return false;			
			}
			else
	if(shiftType==dayShiftId && capacity < DAY_MIN_CAPACITY){
			var check=confirm("The  Day Capacity should not be less than the Minimum Day Capacity="+DAY_MIN_CAPACITY+" \n \t\t\t\t Do You still want to Proceed");
			document.getElementById(textBoxId).focus();
			if(check==false)
				return false;			
			}
	  
	   			
	   		   
	   
	   
		}	////for of maxNoOfShiftsMapped closed
	
	}// for of maxNoOfDesignation

 }/////for of Total no of  areas  mapped closed

return true;

}  
       