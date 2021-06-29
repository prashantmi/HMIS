
/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :LAB TEST GLOBAL MASTER
 ## Purpose						        : 
 ## Date of Creation					:19-Feb-2015 
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
     
	//alert(document.getElementsByName("labCode")[0].value);
	/*if( document.getElementsByName("labCode")[0].value=="")
	{
		alert("Enter laboratory Name   ");
		document.getElementsByName("labCode")[0].focus();
		return false;
	} */
	if( document.getElementsByName("testCode")[0].value=="-1")
	{
		alert("Enter Test Name   ");
		document.getElementsByName("testCode")[0].focus();
		return false;                          
	}
	/*if( document.getElementsByName("instructionPat")[0].value=="")
	{
		alert("Enter Instruction For Patient");
		document.getElementsByName("instructionPat")[0].focus();
		return false;                          
	}
	if( document.getElementsByName("instructionColl")[0].value=="")
	{
		alert("Enter Instruction For Collection");
		document.getElementsByName("instructionColl")[0].focus();
		return false;                          
	}
	if( document.getElementsByName("instructionPost")[0].value=="")
	{
		alert("Enter Instruction For Collection To Patient");
		document.getElementsByName("instructionPost")[0].focus();
		return false;                          
	}
	if( document.getElementsByName("instructionTech")[0].value=="")
	{
		alert("Enter Instruction For Technician ");
		document.getElementsByName("instructionTech")[0].focus();
		return false;                          
	}*/
	/*if(document.getElementsByName("testMethod")[0].value=="-1")
	{
		alert("Select Test Method   ");
		document.forms[0].testMethod.focus();
		return false;                          
	}
*/
	/* if( document.getElementsByName("testDays")[0].checked="true")
=======
	if( document.getElementsByName("testDays")[0].value=="0000000")
>>>>>>> .r3747
	{
		alert("Select Test Days   ");
		document.getElementsByName("testDays")[0].focus();
		return false;                          
<<<<<<< .mine
	} */
	if( document.getElementsByName("noOfTest")[0].value=="")

	{
		alert("Enter No of Test   ");
		document.getElementsByName("noOfTest")[0].focus();
		return false;                          
	}
	if( document.getElementsByName("reportAvailableAfter")[0].value=="-1")
	{
		alert("Select Report Availability   ");
		document.getElementsByName("reportAvailableAfter")[0].focus();
		return false;                          
	}
	if( document.getElementsByName("testPrintingType")[0].value=="-1")
	{
		alert("Select  Result Printing Type  ");
		document.getElementsByName("testPrintingType")[0].focus();
		return false;                          
	}
 
	var ageBound = document.getElementsByName('ageBound');
	var ageBound_value;
	for(var i = 0; i < ageBound.length; i++){
	    if(ageBound[i].checked){
	    	ageBound_value = ageBound[i].value;
	    }
	}
	if(ageBound_value=="1")
		{
		
		if(document.getElementsByName('lowAgeRange')[0].value=="")
			{
			
			alert("Enter Low Age");
			document.getElementsByName("lowAgeRange")[0].focus();
			return false;
			
			}
		
		if(document.getElementsByName('highAgeRange')[0].value=="")
		{
		
		alert("Enter High Age");
		document.getElementsByName("highAgeRange")[0].focus();
		return false;
		
		}
	
		
		if( parseInt(document.getElementsByName('lowAgeRange')[0].value) >= parseInt(document.getElementsByName('highAgeRange')[0].value)    )
		{
			
			alert("Enter Valid Age Range. High Range can't be less than the Low Range.");
			document.getElementsByName("highAgeRange")[0].focus();
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
  
   document.getElementsByName('labCode')[0].value="-1";
   document.getElementsByName('testCode')[0].value="-1";
   document.getElementsByName('testMethod')[0].value="-1";
   document.getElementsByName("isTestAvailable")[0].checked="true";
   document.getElementsByName("isConfidential")[1].checked="true";
   document.getElementsByName("isAppointment")[1].checked="true";
   document.getElementsByName('noOfTest')[0].value="9999";
   document.getElementsByName('reportAvailableAfter')[0].value="2"; 
   document.getElementsByName('testPrintingType')[0].value="1"; 
   document.getElementsByName("isConsent")[1].checked="true"; 
   document.getElementsByName("isMultisession")[1].checked="true";
   document.getElementsByName("isRequisitionFormNeeded")[1].checked="true";
   document.getElementsByName("isMandatoryReq")[1].checked="true";
   document.getElementsByName("isSampleFormNeeded")[1].checked="true";
   document.getElementsByName("genderBound")[0].checked="true";
   document.getElementsByName("ageBound")[1].checked="true";
   document.getElementsByName("isSecurePrinting")[1].checked="true";
   document.getElementsByName("isGrossingReq")[1].checked="true";
   document.getElementsByName("isFilmReq")[1].checked="true";
   document.getElementsByName("instructionPat")[0].value="";
   document.getElementsByName("instructionColl")[0].value="";
   document.getElementsByName("instructionPost")[0].value="";
   document.getElementsByName("instructionTech")[0].value="";
   document.getElementsByName("isNablAccritedTest")[1].checked="true";
   
 }

function modifyClear()
	{
	submitPage("CLEAR");
	}

function validateOnSave()
{
   valid=false;
   
    if( isEmpty(document.forms[0].labCode,"labCode")
    	&&isEmpty(document.forms[0].testName,"testCode")
    	&&isEmpty(document.forms[0].testMethod,"testMethod")
    	&&isEmpty(document.forms[0].testDays,"testDays")
    	&&isEmpty(document.forms[0].noOfTest,"noOfTest")
    	&&isEmpty(document.forms[0].reportAvailableAfter,"reportAvailableAfter")
    	&&isEmpty(document.forms[0].testPrintingType,"testPrintingType"))
     {
         valid=true ;
     }
  return valid;
}
  