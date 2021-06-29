function submitFormData()
{
if(document.forms[0].hmode.value!="" && document.forms[0].hmode.value!="SAVE" && document.forms[0].hmode.value!="NEW")
		document.forms[0].submit();
	
}
function submitPage(mode)
{


if(mode=="GET_DUTY_AREA" )
	{
	
	document.forms[0].areaCode.value="-1";
	}

if(mode=="GET_REPORT")
	{
		
	if(validateReport())
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




function clearForm()
 {
 
 	document.getElementsByName('rosterMainCategory')[0].value="-1";
    document.getElementsByName('rosterCategory')[0].value="-1";
    document.getElementsByName('rosterId')[0].value="-1";
    document.getElementsByName('areaCode')[0].value="-1";
    document.getElementsByName('empId')[0].value="-1";
    submitPage('NEW'); 
        
 }


      
       
 
   function backButton() 
 { 
  //history.back();
   submitPage('NEW');
 }
  
 
	
function validateReport()
{
 //   These All Fields are Mandatory
  var flag=false;
 
  if( 
  	
  	isEmpty(document.getElementsByName('workingDate')[0],"Working Date")  
 &&	comboValidation(document.getElementsByName('rosterMainCategory')[0],"Roster Main Category")
 && comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")
 &&	comboValidation(document.getElementsByName('areaCode')[0],"Duty Area")
 && comboValidation(document.getElementsByName('empId')[0],"Employee")
	)
	{
	flag=true;
	}
 
 


    return flag;
	
	
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
  	
  	
}