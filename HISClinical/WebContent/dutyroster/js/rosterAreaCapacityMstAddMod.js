function submitDutyAreaType(these)
{


var concateValues=these.value.split("^");

document.forms[0].areaTypeCode.value=concateValues[1];
document.forms[0].areaTypeName.value=concateValues[2];

	submitPage('GET_DUTY_AREA');
	
}

function submitDutyArea(these)
{
	
	document.forms[0].areaName.value=these.value;
	submitPage('ADD');
	
}

function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(mode){
     
 //   These All Fields are Mandatory
 var flag=false;
 var flagCheck=false;
  
 //alert(document.getElementById("textBoxNameConcate").value);
 
 
 if(
   comboValidation(document.getElementsByName('rosterId')[0],"Roster Name")
&& comboValidation(document.getElementsByName('areaCode')[0],"Duty Area")	 
  )
	{
		flagCheck=true;
		flag=true;
	}
	
		
if(flagCheck==true && document.getElementById("textBoxNameConcate")!=null && document.getElementById("shiftNameAlertConcate")!=null)
 {
 
 var arrayOfTextBoxNames=document.getElementById("textBoxNameConcate").value.split("@");

 var arrayOfShiftNameAlerts=document.getElementById("shiftNameAlertConcate").value.split("@");
 
 
 
 for(var i=0 ; i < arrayOfTextBoxNames.length-1 ; i++ )
	{
	
	var alertName=arrayOfShiftNameAlerts[i];
	///alert(alertName);
	var textBoxName=arrayOfTextBoxNames[i];
	//alert(textBoxName);
	
	var textBoxValue=document.getElementsByName(textBoxName)[0].value;
//	alert(textBoxValue);
	
	
if(textBoxValue=="" )
	   {
		alert("Please Enter :"+alertName);
		document.getElementsByName(textBoxName)[0].focus();
		flag=false;
		break;
		}
		
	 }	
}		 


//alert("flag2---"+flag)
    return flag;
} 	
	
function finalSubmit(mode)
{
var flag=true;

	if (validateFinalSubmit(mode)) 
	{
	   
    	submitPage(mode);
    	
	}
}




function clearForm()
 {
 
 
 if(document.getElementById("textBoxNameConcate")!=null)
 {	
 	var arrayOfTextBoxNames=document.getElementById("textBoxNameConcate").value.split("@");
 
  for(var i=0 ; i < arrayOfTextBoxNames.length-1 ; i++ )
	{
	var textBoxName=arrayOfTextBoxNames[i];
	document.getElementsByName(textBoxName)[0].value="";
	 }	
 }	
 
 
 if(document.getElementsByName('hmode')[0].value=="ADD")
 	{
  	document.getElementsByName('rosterId')[0].value="-1";
  	document.getElementsByName('areaCode')[0].value="-1";    
   	submitPage('ADD');
  	}
  	else
 if(document.getElementsByName('hmode')[0].value=="MODIFY")
 	{
    document.getElementsByName('areaCode')[0].value="-1";    
    } 
     
  
      
 }
  
  
 
  

       