function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory

var valid=false;


if(isEmpty(document.getElementsByName('rosterCategoryName')[0],"Roster Category")
	&& comboValidation(document.getElementsByName('rosterMainCategoryCode')[0],"Roster Main Category")
	&& comboValidation(document.getElementsByName('rosterMode')[0],"Roster Mode")
	&& comboValidation(document.getElementsByName('dutyOffFlag')[0],"Duty Off Flag")
	&& validateDutyOff()
	&& validateIsActive()
	)
	{
	 valid=true;
	}

    return valid;


 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}




function clearForm()
 {
  
     	document.getElementsByName('rosterCategoryName')[0].value="";     	
     	document.getElementsByName('rosterMainCategoryCode')[0].value="-1";
     	document.getElementsByName('rosterMode')[0].value="-1";
     	document.getElementsByName('maxOff')[0].value="";
     	document.getElementsByName('maxContinuousOff')[0].value="";
     	document.getElementsByName('dutyOffFlag')[0].value="-1";
     	document.getElementsByName('dutyOffAccount')[0].value="";
     	document.getElementsByName('noOfHours')[0].value="";
     	document.getElementById("acc").style.display="none";
    
if(document.getElementsByName("hmode")[0].value=="MODIFY")
	    document.getElementsByName('isValid')[0].value="-1";
  
 }
  
  
 
 function validateAlphaOnly(obj,e)
    {// Ascii Code 0 - 48 To 9 - 57,A-65, Z-90, a-97, z-122, Space- 32, . -46, for , - 44

     var k=e.charCode;

     //alert(k);

     if( e.keyCode!=0 || k==32 || (k>=65 && k<=90) || (k>=97 && k<=122) )

     return true;

     else

     return false;

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

function validateAlphaNumOnly(obj,e)
{
	//alert("Char Code = "+e.charCode+"   Key Code = "+e.keyCode);
	var charCode;
	if(typeof e.charCode != 'undefined')	// Other
		charCode=e.charCode;
	else									// IE
		charCode=e.keyCode;
	//alert(charCode);
	if( charCode==0 || 
		charCode==44 || 
		charCode==32 || 
		charCode==46 || 
		(charCode>=48 && charCode<=57) || 
		(charCode>=65 && charCode<=90) || 
		(charCode>=97 && charCode<=122) )
		return true;
	else
		return false;
}

  function showDutyOffAccount()

 { 
 focusFirstElementOnLoad();
 
 
    if(document.getElementsByName('dutyOffFlag')[0].value=="1"){

    	document.getElementById("acc").style.display="block";

    }

    else{

    	document.getElementById("acc").style.display="none";
    	

    }

 }

function validateDutyOff(){


 	var valid=true;
 	if(document.getElementsByName('dutyOffFlag')[0].value=="1"){	
		valid=isEmpty(document.getElementsByName('dutyOffAccount')[0],"Duty Of Account")
		
		
	}

	return valid
 }      
 
 function validateIsActive(){
 

 
 	var valid=true;
 	if( document.getElementsByName('hmode')[0].value=="MODIFY"){	
		valid=comboValidation(document.forms[0].isValid,"Is Active")
		
	}		

	return valid
 }      