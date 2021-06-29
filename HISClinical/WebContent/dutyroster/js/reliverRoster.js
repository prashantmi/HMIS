  function backButton() 
 { 
  history.back(); 
 }
 
function submitFormData()
{
if(document.getElementsByName("hmode")[0].value!="")
	document.forms[0].submit();
}


function submitPage(mode)
{
var flag=true;

if(mode=="GET_EMP_LIST_AND_SHIFTS" && document.getElementsByName("areaCode")[0].value=="-1")
	{
	flag=false;	
	}
	
if(flag==true)
	{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
	}


}

function validateFinalSubmit(){
   

     
 //   These All Fields are Mandatory
  var flag=false;
  
  
  var  valFromDate=document.getElementsByName('fromDate')[0];
  var  valToDate=document.getElementsByName('toDate')[0];
  var  valSysDate=document.getElementsByName('sysDate')[0];   
     
 //alert('fn called----'+compareDateCall(valFromDate,valToDate,2,"From Date","To Date"));
 
 
  if( 
  	
 	comboValidation(document.getElementsByName('rosterMainCatg')[0],"Roster Main Category")
 && comboValidation(document.getElementsByName('rosterCatg')[0],"Roster Category")
 &&	comboValidation(document.getElementsByName('areaCode')[0],"Duty Area")
 &&	comboValidation(document.getElementsByName('reliverEmpId')[0],"Reliver Employee") 
 && comboValidation(document.getElementsByName('shiftId')[0],"Shift Name")
 
 && validateGeneratedRosterId()
 && validateDayOffReliverDate()
 && validateEmp()
 && compareDateCall(valFromDate,valToDate,2,"From Date","To Date") 
 && compareDateCall(valSysDate,valFromDate,2,"System Date","From Date")
   )
   {
	flag=true;
   }
 
 
 
	if(document.getElementsByName("reasonForReliver")[0].value.length>100)
	{
		alert("Characters in the Reason should be less than 100");
		document.getElementsByName("reasonForReliver")[0].focus();
		flag=false;
	}
 


    return flag;
} 	
function validateEmp()
{
var requestedEmpArray=document.getElementsByName("requestedEmpId")[0].value.split("@");
var reliverEmpArray=document.getElementsByName("reliverEmpId")[0].value.split("@");


if(requestedEmpArray[0]==reliverEmpArray[0])
	{
	alert("Employee and Reliver Employee ,Cannot be Same");
	return false;
	}
	else
	return true;
}	

//function for validating the next date day off reliver  
	
function validateDayOffReliverDate()
{

getTheNextDate(document.forms[0].toDate.value,document.forms[0].toDate.value,'dayOffReliverDate',1)

if(document.getElementsByName("isDutyOff")[0].checked==true && document.getElementsByName("dayOffReliverDate")[0].value!=document.getElementsByName("nextToReliverToDate")[0].value)
	{
	alert("Next Date of Reliver To Date and Duty Off Date are Not Same");
	document.getElementsByName("isDutyOff")[0].checked=false;
	document.getElementsByName("nextToReliverToDate")[0].value="";
	return false;
	}
	else
	return true;
	
}	
	
function finalSubmit(mode)
{



if (validateFinalSubmit() ) 
  	{
	 submitPage(mode);
	// alert('save')
       
	}
	
     	
}//function closed




function clearForm()
 {
 
    document.getElementsByName('rosterMainCatg')[0].value="-1";
    document.getElementsByName('rosterCatg')[0].value="-1";
  	document.getElementsByName('areaCode')[0].value="-1";
    document.getElementsByName('requestedEmpId')[0].value="-1";
    document.getElementsByName('reliverEmpId')[0].value="-1";
    document.getElementsByName('shiftId')[0].value="-1";
    document.getElementsByName('isDutyOff')[0].checked=false;
    document.getElementsByName('nextToReliverToDate')[0].value="";
    document.getElementsByName('reason')[0].checked=true;
    document.getElementsByName('reasonForReliver')[0].value="";
    submitPage('NEW'); 
        
 }


 
 function printPage() 
 {
 var frameElement = parent.document.getElementById("f2");  
 var win = frameElement.contentWindow ;
 document.getElementById("noPrint").style.display="none"; 
 win.print(); 
  document.getElementById("noPrint").style.display="block" ; 
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

} 

function validateGeneratedRosterId()
{
var arrayOfData=document.getElementsByName("shiftId")[0].value.split("@");

if(arrayOfData[4]=="-1")
	{
	alert("Roster Id is not Generated")
	return false;
	}
	else
	return true;
}

function validateArea()
{



if(document.getElementsByName("areaCode")[0].value=="-1")
		{
		alert("Please Select Duty Area")
		document.getElementsByName("areaCode")[0].focus();
		return false;
		}
		else
		return true;
	

}





function getTheNextDate(_date,_dateAsString,_dateToSetWhere,_daysToAdd)
{


if(document.getElementsByName("isDutyOff")[0].checked==true)
	 setDateInTextBoxAfterAddingDaysToDate(_date,_dateAsString,_dateToSetWhere,_daysToAdd); 
else
	document.getElementsByName("nextToReliverToDate")[0].value="";

}

function selectReason()
{
if(document.getElementsByName("reason")[1].checked==true)
	{
	document.getElementsByName("requestedEmpId")[0].value="-1";
	document.getElementsByName("requestedEmpId")[0].disabled=true;
	}
	else
if(document.getElementsByName("reason")[0].checked==true)
	{
	document.getElementsByName("requestedEmpId")[0].value="-1";
	document.getElementsByName("requestedEmpId")[0].disabled=false;
	}
	

}

function validateReliverEmp(emp)
{

var requestEmpArray=document.getElementsByName("requestedEmpId")[0].value.split("@");
var reliverEmpArray=document.getElementsByName("reliverEmpId")[0].value.split("@");


if(requestEmpArray[0]==reliverEmpArray[0] && document.getElementsByName("reliverEmpId")[0].value!="-1")
	{
	
	if(emp=="1")
		{
		alert("Request By Employee and Reliver Employee cannot be same");
		document.getElementsByName("requestedEmpId")[0].value="-1";
		
		}
		else
	if(emp=="2")
		{
		alert("Request By Employee and Reliver Employee cannot be same");
		document.getElementsByName("reliverEmpId")[0].value="-1";
		
		}
		
		
		
	}


}
