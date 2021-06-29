/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :BOOKMARK MASTER
 ## Purpose						        : 
 ## Date of Creation					:30-MAR-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
   
	if(document.getElementsByName("bookmarkName")[0].value=="")
	{
		alert("Enter BookMark Name   ");
		document.getElementsByName("bookmarkName")[0].focus();
		return false;
	}
	
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}
function clearForm()
 {
   document.getElementsByName('bookmarkName')[0].value="";
   document.getElementsByName('bookmarkType')[0].checked="true";
}

function modifyClear()
{
	submitPage("CLEAR");	
}
function validateOnSave()
{
   valid=false;
    if( isEmpty(document.forms[0].bookmarkName,"bookmarkName")
    &&isEmpty(document.forms[0].bookmarType,"bookmarType"))
     {
         valid=true ;
     }

  return valid;
}
