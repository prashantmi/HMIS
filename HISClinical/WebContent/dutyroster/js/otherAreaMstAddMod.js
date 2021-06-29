function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     var valid=false;
     // These All Fields are Mandatory
if(
	isEmpty(document.getElementsByName('otherAreaName')[0],"Area Name")
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
  
    document.getElementsByName('otherAreaName')[0].value="";
    
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

      
       