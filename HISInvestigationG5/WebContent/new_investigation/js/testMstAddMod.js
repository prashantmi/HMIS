function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
	//alert(document.getElementById("sugartestcode").value);
	//document.getElementsByName("blddrTxt")[0].value=document.getElementById("blddrTxt").value;
	//document.getElementsByName("turnaround_time")[0].value=document.getElementById("turnaround_time").value;
	 
//alert(document.forms[0].sugartestcode);
//alert(document.forms[0].sugartestcode.value);
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
	
	if(document.getElementsByName("issugartest")[1].checked==true)
	{
		if(document.getElementsByName("sugartestcode")[0].value=="")
		{alert("Please Enter SugarTestCode");
		
		document.getElementsByName("sugartestcode")[0].focus();
		return false;   
		}
	}
	
	if(document.getElementsByName("machinetest")[1].checked==true)
	{
		if(document.getElementsByName("machinetestcode")[0].value =="")
		{alert("Please Enter MachineTestCode");
		
		document.getElementsByName("machinetestcode")[0].focus();
		return false;   
		}
	}
	
	if(document.getElementsByName("anyinstruction")[1].checked==true)
	{
		if(document.getElementsByName("instType")[0].value=="0")
		{alert("Please Enter Instruction Type..");
		
		document.getElementsByName("instType")[0].focus();
		return false;   
		}
		else if(document.getElementsByName("instType")[0].value=="1")
		{
			
		if(document.getElementsByName("fastingtime")[0].value=="0")
			{
			alert("Please select fasting time")
			
		document.getElementsByName("fastingtime")[0].focus();
		return false;   
		}
		else if(document.getElementsByName("fastingtype")[0].value=="0")
		{
		alert("Please select fasting type...")
		
	document.getElementsByName("fastingtype")[0].focus();
	return false;   
		}

		}
		else{
			if(document.getElementsByName("blddrTxt")[0].value=="")
				{
				alert("Please Enter bladder Text Info...")
				
				document.getElementsByName("blddrTxt")[0].focus();
				return false;
				}
		}
	}
	if(document.getElementsByName("turnaround_time")[0].value=="")
	{
		
		document.getElementsByName("turnaround_time")[0].value="72";
		
	}
	
	
	//turnaround_time
	document.getElementsByName("testName")[0].value=document.getElementById("testName").value;
	//document.getElementsByName("turnaround_time")[0].value=document.getElementById("turnaround_time").value;
	//document.getElementsByName("machinetestcode")[0].value=document.getElementById("machinetestcode").value;
	//document.getElementsByName("sugartestcode")[0].value=document.getElementById("sugartestcode").value;
	//document.getElementsByName("blddrTxt")[0].value=document.getElementById("blddrTxt").value;
	
   return true;
 } 	
	
function finalSubmit(mode)
{
	 document.getElementsByName("printingTemplateCode")[0].disabled = false;

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
   //document.getElementsByName("departmentprintedWith")[0].checked=true;
   
 // document.getElementById("departmentprintingTemplate").style.display="none";
   document.getElementsByName("turnaround_time")[0].value ="";
   document.getElementById("instDiv").style.display="none";
   document.getElementById("sugarTestCombo").style.display="none";
   document.getElementById("machineTestCombo").style.display="none"; 
	  document.getElementById("FType").style.display="none";
	  document.getElementById("FTimeId").style.display="none";
	  document.getElementById("bladderId").style.display="none";
	  document.getElementsByName("fastingtype")[0].value ="0";
	  document.getElementsByName("fastingtime")[0].value ="0";   
	  document.getElementsByName("blddrTxt")[0].value ="";
	  document.getElementsByName("instType")[0].value="0";
	  document.getElementsByName("issugartest")[0].checked=true;
	  document.getElementsByName("machinetest")[0].checked =true;
	  document.getElementsByName("isconfidential")[0].checked = true;
	  document.getElementsByName("ishidefromrequisitionraising")[0].checked = true;
	  document.getElementsByName("machinetestcode")[0].value="";
	  document.getElementsByName("sugartestcode")[0].value="";
 }
  
  
 
 
  
 