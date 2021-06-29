function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	 
	////////alert(document.forms[0].sampleName);
	//////////alert(document.forms[0].sampleName.value);
	
 
	 
	if(document.forms[0].laboratoryName &&document.getElementsByName("laboratoryName")[0].value=="")
	{
		alert("Enter laboratory Name   ");
		//document.forms[0].sampleName.focus();
		document.getElementsByName("laboratoryName")[0].focus();
		return false;
	}
	if(document.forms[0].labShortSName && document.getElementsByName("labShortSName")[0].value=="")
	{
		alert("Enter Lab  Short Name   ");
		document.getElementsByName("labShortSName")[0].focus();
		return false;                          
	}
	if(document.forms[0].department && document.getElementsByName("department")[0].value=="-1")
	{
		alert("Select Department   ");
		document.forms[0].department.focus();
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
  
   document.getElementsByName('laboratoryName')[0].value="";

   document.getElementsByName('labShortSName')[0].value="";
   document.getElementsByName('numberofTests')[0].value="";
   document.getElementsByName('headertext')[0].value="";
   document.getElementsByName('footerText')[0].value="";
   document.getElementsByName('remarks')[0].value="";
    
   
  
   
  
 }
  
  
 
 
  
 