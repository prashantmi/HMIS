//The above two functions are commneted 
//since by default timings are already shown hence no need to call it


function setPreviosValue(elem, evt){
		//	prevValue = elem.value;
	}  
	
function moveToRightBox(elem, evt){
	/*
		val=elem.value;
		maxLength =elem.getAttribute('maxlength');
		i=0;
		if(val.length==maxLength){
			for(i=0; i<prevValue.length;i++)
				if(prevValue.charAt(i)!=val.charAt(i))
					break;
		
		}
		if(val.length==maxLength){
			if(elem.name == 'startTimeHrUser'){
				document.getElementsByName("startTimeMinUser")[0].focus();
			}else if(elem.name == 'startTimeMinUser'){
				document.getElementsByName("endTimeHrUser")[0].focus();
			}else if(elem.name == 'endTimeHrUser'){
				document.getElementsByName("endTimeMinUser")[0].focus();
			}
		}	*/
	}

function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}
function validateShiftShortName()
{
if(document.forms[0].shiftShortName.value.length==3)
	return true
	else
	{
	alert("Shift Short Name must be of 3 Characters");
	document.forms[0].shiftShortName.focus();
	return false;
	}
}

function validateFinalSubmit(){
    
    var valid=false; 
 //   These All Fields are Mandatory
 if(document.getElementsByName("isDayWiseShift")[0].value!=1)
{ 
 if(
 		isEmpty(document.getElementsByName('shiftDescription')[0],"Shift Description")
 	&&	isEmpty(document.getElementsByName('shiftShortName')[0],"Shift Short Name")
 	&&  validateShiftShortName()
	&&  comboValidation(document.getElementsByName('shiftTypeCode')[0],"Shift Type")
	&&  comboValidation(document.getElementsByName('isDayWiseShift')[0],"Day Wise Shift Timings")
	&&	isEmpty(document.getElementsByName('startTimeHrUser')[0],"Start Time Hr.")
	&&	isEmpty(document.getElementsByName('startTimeMinUser')[0],"Start Time Min.")
	&&	isEmpty(document.getElementsByName('endTimeHrUser')[0]," End Time Hr.")
	&&	isEmpty(document.getElementsByName('endTimeMinUser')[0],"End Time Min.")
	&&  validateTime()

	)
	{
	valid=true;
	
	}
}
	else
if(document.getElementsByName("isDayWiseShift")[0].value==1)
{ 
 if(
 		isEmpty(document.getElementsByName('shiftDescription')[0],"Shift Description")
 	&&	isEmpty(document.getElementsByName('shiftShortName')[0],"Shift Short Name")
	&&  comboValidation(document.getElementsByName('shiftTypeCode')[0],"Shift Type")
	&&  comboValidation(document.getElementsByName('isDayWiseShift')[0],"Day Wise Shift Timings")
	&&	validateAllTimings()
	&&  validateAllDaysTime()

	)
	{
	valid=true;
	
	}
}	
	
	
	
	 return valid;
 } 	

function validateAllTimings()
{
//alert("validateAllTimings");

var valid=false;

var arrayOfDays=new Array("Sunday","Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday")

for(var i=1 ; i < 8 ; i++ )
{
//alert("i----"+i);
var day=arrayOfDays[i-1]

	if(
		isEmpty(document.getElementsByName('startTimeHrUser')[i],"Start Time Hr. of "+day)
	&&	isEmpty(document.getElementsByName('startTimeMinUser')[i],"Start Time Min. of "+day)
	&&	isEmpty(document.getElementsByName('endTimeHrUser')[i]," End Time Hr. of "+day)
	&&	isEmpty(document.getElementsByName('endTimeMinUser')[i],"End Time Min. of "+day)
	  )
	  { 
	  valid=true;
	  }
      else
      {
      valid=false;
	  break;
	  } 
}
//alert("valid-----"+valid);
	  	
return valid;

}	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

 function clearForm()
 {
     document.getElementsByName('shiftShortName')[0].value="";
     document.getElementsByName('shiftDescription')[0].value="";
     
if(document.getElementsByName("hmode")[0].value!="MODIFY")   
		{
       document.getElementsByName('shiftTypeCode')[0].value="-1";
       document.getElementsByName('isDayWiseShift')[0].value="-1";     
       document.getElementById("startTime").innerHTML="";
 	   document.getElementById("endTime").innerHTML="";
 		
     	}
     	
     	
if(document.getElementsByName("hmode")[0].value=="MODIFY" && document.getElementsByName("isDayWiseShift")[0].disabled==false)   
		{
      
       document.getElementsByName('isDayWiseShift')[0].value="-1";     
      
     	}     	
     	
     	
     document.getElementsByName('startTimeHrUser')[0].value="";
     document.getElementsByName('startTimeMinUser')[0].value="";
     document.getElementsByName('endTimeHrUser')[0].value="";
     document.getElementsByName('endTimeMinUser')[0].value="";
     
 	
 	 document.getElementById("sameTimingsTable").style.display="block";
   	 document.getElementById("differentTimingsTable").style.display="none";	
 	 
  
if(document.getElementsByName("hmode")[0].value=="MODIFY")
     document.getElementsByName('isValid')[0].value="-1";
 } 
  
 function getShiftTypeAndTime(these)
 {
 if(these.value!="-1")
   {
   var concateValues=these.value.split("^")
   
//Now checking whether the selected Shift Type is Day Shift    

if(concateValues[0]=="4")
   {		
   document.getElementsByName("isDayWiseShift")[0].disabled=false;
   
   }
else
   {	
   	  document.getElementsByName("isDayWiseShift")[0].value="0";	
      document.getElementsByName("isDayWiseShift")[0].disabled=true;
   	  document.getElementById("sameTimingsTable").style.display="block";
   	  document.getElementById("differentTimingsTable").style.display="none";		
   }
   
  //Now getting the split values into the hidden text box
  //and the div element 
   
   var divId1=document.getElementById("startTime");
   var divId2=document.getElementById("endTime");
   
   var divId3=document.getElementById("startTimeDifferent");
   var divId4=document.getElementById("endTimeDifferent");
  
  
  
  
   divId1.innerHTML="Greater Than Equal To "+concateValues[1]+" (HH:MM 24 Hrs)";
   divId2.innerHTML="Less Than Equal To "+concateValues[2]+" (HH:MM 24 Hrs)";
  
  
   divId3.innerHTML="Greater Than Equal To "+concateValues[1]+" (HH:MM 24 Hrs)";
   divId4.innerHTML="Less Than Equal To "+concateValues[2]+" (HH:MM 24 Hrs)";
  
   
   document.getElementsByName('startTimeTable')[0].value=concateValues[1];
   document.getElementsByName('endTimeTable')[0].value=concateValues[2];
   
   
   
   var startTimeArray=concateValues[1].split(":");
   var endTimeArray=concateValues[2].split(":");
   
   
  putDefaultTimings(startTimeArray,endTimeArray);
   
 	}
 	else
 if(these.value=="-1")
 	{
 	document.getElementById("startTime").innerHTML="";
 	document.getElementById("endTime").innerHTML="";
 	
 	document.getElementById("startTimeDifferent").innerHTML="";
 	document.getElementById("endTimeDifferent").innerHTML=""; 	
 	
 	document.getElementsByName('startTimeTable')[0].value="";
   	document.getElementsByName('endTimeTable')[0].value="";
   	
   	
   var startTimeArray=new Array("","");
   var endTimeArray=new Array("","");
   	
     putDefaultTimings(startTimeArray,endTimeArray);
 	}
 	
}	
function putDefaultTimings(startTimeArray,endTimeArray)
{

for(var i=0; i < document.getElementsByName("startTimeHrUser").length ; i++)
	document.getElementsByName("startTimeHrUser")[i].value=startTimeArray[0];


for(var i=0;i < document.getElementsByName("startTimeMinUser").length ; i++)
	document.getElementsByName("startTimeMinUser")[i].value=startTimeArray[1];
	
for(var i=0; i < document.getElementsByName("endTimeHrUser").length ; i++)
	document.getElementsByName("endTimeHrUser")[i].value=endTimeArray[0];
	
	
for(var i=0; i < document.getElementsByName("endTimeMinUser").length ; i++)
	document.getElementsByName("endTimeMinUser")[i].value=endTimeArray[1];		



}



function validateHr(these)
 {

if(these.value.length!=2 && these.value!="")
 		{
		alert("Enter the Hours in 00 format");
		document.getElementsByName(these.name)[0].focus();
		these.value="";
		}
if(these.value!="" && these.value!="00")
  {

 if(these.value < 24 && these.value >0 )
   {
        return true;
    
    } 
  else
   {
      alert("Hours Should be Less than 24");
      these.value="";
      return false;
     }
   }
 }
 function validateMin(these)
 {

if(these.value.length!=2 && these.value!="")
		{
		alert("Enter the Minutes in 00 format");
		document.getElementsByName(these.name)[0].focus();
		these.value="";
		}
if(these.value!="" && these.value!="00")
  {
  
 if(these.value < 60 && these.value >0 )
     {
     return true;
      }
   else
   	 {
    alert("Minutes Should be Less than 60");
    these.value="";
    return false;
     }
   } 
 }
function validateAllDaysTime()
 {
 //alert("validate all days timgs");
 var flag=true;
 
 var arrayOfDays=new Array("Sunday","Monday","Tuesday","Wednesday","Thrusday","Friday","Saturday")
 
for(var i=1 ; i < 8 ; i++)
  {

var day=arrayOfDays[i-1];
//alert("day---"+day)

 var userStartTime=document.getElementsByName('startTimeHrUser')[i].value+document.getElementsByName('startTimeMinUser')[i].value;
 var userEndTime=document.getElementsByName('endTimeHrUser')[i].value+document.getElementsByName('endTimeMinUser')[i].value;
 var tableStartTimeConcate=document.getElementsByName('startTimeTable')[0].value.split(":");
 var tableEndTimeConcate=document.getElementsByName('endTimeTable')[0].value.split(":");
var tableStartTime=tableStartTimeConcate[0]+tableStartTimeConcate[1];
var tableEndTime=tableEndTimeConcate[0]+tableEndTimeConcate[1];

 
 
 //alert(userStartTime);
 // alert(userEndTime);
 //  alert(tableStartTime);
 //   alert(tableEndTime);
if(tableEndTime-tableStartTime> 0)
   {
   //alert("hi");
   if(!(userStartTime >= tableStartTime && userStartTime < tableEndTime))
       {
      alert("Enter the Start Time of "+day+" Within the given Range");
       flag=false;
       break;
       }
       else
   if(!(userEndTime > tableStartTime && userEndTime <= tableEndTime))
       {
       alert("Enter the End Time of "+day+" Within the given Range");
       flag=false;
        break;
       }
        
   }
else
if(tableEndTime-tableStartTime < 0)
	{
//	alert("bye");
	
//alert("userStartTime--->"+userStartTime);
//alert("userEndTime-->"+userEndTime);
//alert("tableStartTime--->"+tableStartTime);
//alert("tableEndTime-->"+tableEndTime);
	
	if(userEndTime <= tableEndTime )
	 {
//	 alert("inside");
	userEndTime=eval(userEndTime)+2400;
	 }
//	alert("userEndTime after---->"+userEndTime);
	
	
	tableEndTime=eval(tableEndTime)+2400;
//	alert("tableEndTime after--->"+tableEndTime);
	
	
if(!(userStartTime >= tableStartTime && userStartTime < tableEndTime))
		{ 
       alert("Enter the Start Time of "+day+" Within the given Range");
       flag=false;  
        break;
        }
        else
   if(!(userEndTime > tableStartTime && userEndTime <= tableEndTime))
   	   {
       alert("Enter the End Time of "+day+" Within the given Range");
       flag=false;
        break;
       } 
   }
   
}//for closed   
   
 
 return flag;
  
 }
   
   
 function validateTime()
 {
 var flag=true;
 var userStartTime=document.getElementsByName('startTimeHrUser')[0].value+document.getElementsByName('startTimeMinUser')[0].value;
 var userEndTime=document.getElementsByName('endTimeHrUser')[0].value+document.getElementsByName('endTimeMinUser')[0].value;
 var tableStartTimeConcate=document.getElementsByName('startTimeTable')[0].value.split(":");
 var tableEndTimeConcate=document.getElementsByName('endTimeTable')[0].value.split(":");
var tableStartTime=tableStartTimeConcate[0]+tableStartTimeConcate[1];
var tableEndTime=tableEndTimeConcate[0]+tableEndTimeConcate[1];

 
 
 //alert(userStartTime);
 // alert(userEndTime);
 //  alert(tableStartTime);
 //   alert(tableEndTime);
if(tableEndTime-tableStartTime> 0)
   {
   //alert("hi");
   if(!(userStartTime >= tableStartTime && userStartTime < tableEndTime))
       {
      alert("Enter the Start Time Within the given Range");
       flag=false;
       }
       else
   if(!(userEndTime > tableStartTime && userEndTime <= tableEndTime))
       {
       alert("Enter the End Time Within the given Range");
       flag=false;
       }
        
   }
else
if(tableEndTime-tableStartTime < 0)
	{
//	alert("bye");
	
//alert("userStartTime--->"+userStartTime);
//alert("userEndTime-->"+userEndTime);
//alert("tableStartTime--->"+tableStartTime);
//alert("tableEndTime-->"+tableEndTime);
	
	if(userEndTime <= tableEndTime )
	 {
//	 alert("inside");
	userEndTime=eval(userEndTime)+2400;
	 }
//	alert("userEndTime after---->"+userEndTime);
	
	
	tableEndTime=eval(tableEndTime)+2400;
//	alert("tableEndTime after--->"+tableEndTime);
	
	
if(!(userStartTime >= tableStartTime && userStartTime < tableEndTime))
		{ 
       alert("Enter the Start Time Within the given Range");
       flag=false;  
        }
        else
   if(!(userEndTime > tableStartTime && userEndTime <= tableEndTime))
   	   {
       alert("Enter the End Time Within the given Range");
       flag=false;
       } 
   }
 
 return flag;
  
 }
 
      
function validatePositiveIntegerOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		( charCode>=48 && charCode<= 57 ) )
		return true;
	else
		return false;
}  
 
function showDayWiseShift(these)
{

//alert("showDayWiseShift")

if(these.value=="1")
	{
document.getElementById("sameTimingsTable").style.display="none";
document.getElementById("differentTimingsTable").style.display="block";
	}
	else
	{
document.getElementById("sameTimingsTable").style.display="block";
document.getElementById("differentTimingsTable").style.display="none";
	}
	
}

function checkDayWiseOnLoad()
{
focusFirstElementOnLoad();

  var startTimeTable=document.getElementsByName("startTimeTable")[0].value 
   var endTimeTable=document.getElementsByName("endTimeTable")[0].value
  



   
 if(document.getElementsByName("shiftTypeCode")[0].value!=-1)
 	{   
    
   var  shiftTypeCodeArray=document.getElementsByName("shiftTypeCode")[0].value.split("^");
  
if(shiftTypeCodeArray[0]=="4" && document.getElementsByName("hmode")[0].value=="MODIFY" )  
  document.getElementsByName("isDayWiseShift")[0].disabled=false;
    
    }
    

//IN CASE THE DAYWISE SHIFT CODE IS 1(YES)

if(document.getElementsByName("isDayWiseShift")[0].value=="1")
	{

	document.getElementById("differentTimingsTable").style.display="block";
	document.getElementById("sameTimingsTable").style.display="none";


   var divId1=document.getElementById("startTime");
   var divId2=document.getElementById("endTime");
  
   divId1.innerHTML="Greater Than Equal To "+startTimeTable+" (HH:MM 24 Hrs)";
   divId2.innerHTML="Less Than Equal To "+endTimeTable+" (HH:MM 24 Hrs)";
  	
	
   var divId3=document.getElementById("startTimeDifferent");
   var divId4=document.getElementById("endTimeDifferent");
  
   divId3.innerHTML="Greater Than Equal To "+startTimeTable+" (HH:MM 24 Hrs)";
   divId4.innerHTML="Less Than Equal To "+endTimeTable+" (HH:MM 24 Hrs)";
   
   
	}
	else //IN CASE THE DAYWISE SHIFT CODE IS 0(NO)
	{
document.getElementById("sameTimingsTable").style.display="block";

   var divId1=document.getElementById("startTime");
   var divId2=document.getElementById("endTime");
  
   divId1.innerHTML="Greater Than Equal To "+startTimeTable+" (HH:MM 24 Hrs)";
   divId2.innerHTML="Less Than Equal To "+endTimeTable+" (HH:MM 24 Hrs)";

    }

 
   
   if(document.getElementsByName("shiftTypeCode")[0].value==-1)
   		{
   		
   		 var divId1=document.getElementById("startTime");
  		 var divId2=document.getElementById("endTime");
  
   		  divId1.innerHTML="";
 		  divId2.innerHTML="";
   		   		
   		}
   
  
}      
       