
/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST SAMPLE MASTER
 ## Purpose						        : 
 ## Date of Creation					:23-Feb-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
    */


function submitPage(mode)
{
	//alert(mode);
	document.getElementsByName('hmode')[0].value=mode;
	//document.forms[0].hmode.value=mode;
	//alert("hmode:::::"+document.getElementsByName("hmode")[0].value);
	document.forms[0].submit();
}

function validateFinalSubmit(){
    // alert(document.getElementsByName("testType")[0].value);
	if(document.getElementsByName("sampleCode")[0].value=="-1")
	{
		alert("Enter Sample Name   ");
		document.getElementsByName("sampleCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("testType")[0].value!="P")
	{
	if(document.getElementsByName("containerCode")[0].value=="-1")
	{
		alert("Enter Container Name   ");
		document.getElementsByName("containerCode")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("uomCode")[0].value=="-1")
	{
		alert("Enter UOM Name   ");
		document.getElementsByName("uomCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("sampleQty")[0].value=="")
	{
		alert("Enter Sample Quantity");
		document.getElementsByName("sampleQty")[0].focus();
		return false;
	}
	
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
  if(document.getElementsByName("testType")[0].value!="P")
  {
   document.getElementsByName('sampleCode')[0].value="-1";
   document.getElementsByName('containerCode')[0].value="-1";
   document.getElementsByName('uomCode')[0].value="-1";
   document.getElementsByName("sampleQty")[0].value="";
   document.getElementsByName("isDefaultSample")[1].checked="true";
   document.getElementsByName("sampleCollRemarks")[0].value="";
 }
 if(document.getElementsByName("testType")[0].value=="P")
 {
 	document.getElementsByName('sampleCode')[0].value="-1";
 	document.getElementsByName("sampleCollRemarks")[0].value="";
 }
}
function modifyClear()
{
	submitPage("CLEAR");	
}

function validateOnSave()
{
   valid=false;
   if(document.getElementsByName("testType")[0].value=="P")
   {
    if( isEmpty(document.forms[0].sampleCode,"sampleCode"))
     {
         valid=true ;
     }
   }
   if(document.getElementsByName("testType")[0].value!="P")
   {
   	if( isEmpty(document.forms[0].sampleCode,"sampleCode")
   	&&isEmpty(document.forms[0].containerCode,"containerCode")
   	&&isEmpty(document.forms[0].uomCode,"uomCode")
   	&&isEmpty(document.forms[0].sampleQty,"sampleQty")
   	)
     {
         valid=true ;
     }
   }
  return valid;
}
  