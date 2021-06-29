function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	 
	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
	var val='-';
	if(document.getElementsByName("testName")[0].value.indexOf(val)!=-1)
	{
		alert("Please Enter Valid Test Name Without - ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("testName")[0].focus();
		return false;
	} 
	if(document.forms[0].testName &&document.getElementById("testName").value=="")
	{
		alert("Enter Test Name ... ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("testName")[0].focus();
		return false;
	}
	if(document.forms[0].testSName && document.getElementsByName("testSName")[0].value=="")
	{
		alert("Enter Test Short Name ... ");
		document.forms[0].testSName.focus();
		return false;                          
	}
	if(document.forms[0].testType && document.getElementsByName("testType")[0].value=="")
	{
		alert("Enter Test Type  ... ");
		document.forms[0].testType.focus();
		return false;                          
	}
	/*if(document.forms[0].loincTiming && document.getElementsByName("loincTiming")[0].value=="-1")
	{
		alert("Select loinc Timing ... ");
		document.forms[0].loincTiming.focus();
		return false;                          
	}*/
   
	if(document.getElementsByName("resultEntryForm")[1].checked==true)
	{
		if(document.getElementsByName("printingTemplateCode")[0].value=="-1")
		{alert("Select the Printing Template ");
		document.getElementsByName("printingTemplateCode")[0].focus();
		return false;   
		}
	}
	document.getElementsByName("testName")[0].value=document.getElementById("testName").value;
   return true;
 } 	
	
function finalSubmit(mode)
{
	
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

function clearAddForm()
 {
  
   document.getElementsByName('testName')[0].value="";
   document.getElementsByName('testSName')[0].value="";
   document.getElementsByName('testDescription')[0].value="";
   document.getElementsByName('loincTiming')[0].value="Pt";
   document.getElementsByName('testType')[0].checked=true;
   document.getElementsByName("printingTemplateCode")[0].value="-1" ;
   document.getElementsByName("resultEntryForm")[0].checked=true;
   document.getElementsByName("printedWith")[0].checked=true;
   
   document.getElementById("printingTemplate").style.display="none";
   
   document.getElementsByName("departmentResultEntryForm")[0].checked=true;
   document.getElementsByName("departmentprintedWith")[0].checked=true;
   
   document.getElementById("departmentprintingTemplate").style.display="none";
   
   
 }
  
  
 
 
  
 