/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LOINC MASTER
 ## Purpose						        : 
 ## Date of Creation					:21-APR-2015 
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
   
	if(document.getElementsByName("testCode")[0].value=="-1")
	{
		alert("Select Test Name   ");
		document.getElementsByName("testCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("testCode")[0].value.split("#")[2]=="")
	{	
		alert("No Mapped Loinc Time Found ")
		return false;
	}
	if(document.getElementsByName("paraCode")[0].value=="-1")
	{
		alert("Select Parameter Name   ");
		document.getElementsByName("paraCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("paraCode")[0].value.split("#")[1]=="")
	{	
		alert("No Mapped Loinc Scale Found ")
		return false;
	}
	if(document.getElementsByName("sampleCode")[0].value=="-1")
	{
		alert("Select Sample Name ");
		document.getElementsByName("sampleCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("sampleCode")[0].value.split("#")[1]=="")
	{	
		alert("No Mapped Loinc System Found ")
		return false;
	}
	if(document.getElementsByName("uomCode")[0].value=="-1")
	{
		alert("Select Unit of Mesurment Name   ");
		document.getElementsByName("uomCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("uomCode")[0].value.split("#")[1]=="")
	{	
		alert("No Mapped Loinc Property Found ")
		return false;
	}
	if(document.getElementsByName("loincValues").selected==false)
	{
		alert("Select Loinc Record ");
		return false;
	}
   return true;
 } 	
	
function validateSave()
	{
	//alert("validateSave");
	var loinc = document.getElementsByName('loincValues');
	var loincCode;
	for(var i = 0; i < loinc.length; i++)
	{
    	if(loinc[i].checked)
    	{
    	return true;
   		}
	}
	alert("select Atleast One Record");
	return false;
	}
function finalSubmit(mode)
{
	//alert("Inside Final Submit");
	if (!validateSave()) return;
	submitPage(mode);
}
function clearForm()
 {
   document.getElementsByName('testCode')[0].value="-1";
   document.getElementsByName('loincTime')[0].value="";
   
   document.getElementsByName('paraCode')[0].value="-1";
   document.getElementsByName('loincScale')[0].value="";
   
   document.getElementsByName('sampleCode')[0].value="-1";
   document.getElementsByName('loincSystem')[0].value="";
   
   document.getElementsByName('uomCode')[0].value="-1";
   document.getElementsByName('loincProperty')[0].value="";
   document.getElementById("tab2").style.display="none";
   document.getElementById("tab1").style.display="none";
   document.getElementById("tab3").style.display="none";
   document.getElementsByName('mode')[0].value="ADD";
   submitPage("ADD");
}

function validateOnSave()
{
   valid=false;
    if( isEmpty(document.forms[0].testCode,"testCode")
    &&isEmpty(document.forms[0].testValues,"testValues"))
     {
         valid=true ;
     }

  return valid;
}
function modifyClear()
{
	submitPage("CLEAR");	
}
function onSelect()
{
 document.getElementsByName('loincCode')[0].value= document.getElementsByName('loincValues');
}
