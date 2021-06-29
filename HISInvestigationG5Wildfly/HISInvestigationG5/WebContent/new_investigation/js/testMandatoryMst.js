/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :TEST MANDATORY GLOBAL MASTER
 ## Purpose						        : This master is used for mapping test with mandatory globally i.e. on hospital code 100
 ## Date of Creation					:16-Mar-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	
	if(document.getElementsByName("testCode")[0].value=="-1")
	{
		alert("Select Test Name ");
		document.getElementsByName("testCode")[0].focus();
		validateSelectedMandatory();
		return false;                          
	}
	

if(document.getElementsByName("mappedList")[0].length==0)
      {
		alert("Select Atleast One Mandatory");
		document.getElementsByName("mappedList")[0].focus();
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
     submitPage('CLEAR');
 }

function moveRightSingle()
{
	
	
	var source  = document.forms[0].unmappedList;
	var target = document.forms[0].mappedList;	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function moveLeftSingle()
{
	var source;
	var target;

	
	
		source  = document.forms[0].mappedList;
		target = document.forms[0].unmappedList;	
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function MoveToSelected()
{
	// Select All Shift in Selected
	if(document.forms[0].mappedList)
	{	
		for(var i=0;i<document.getElementsByName("mappedList")[0].length;i++)
		{
			document.getElementsByName("mappedList")[0].options[i].selected=true;
		}
		document.getElementsByName("templab")[0].value=document.getElementsByName("mappedList")[0].length;
		}
	// Unselect Remaining 
	if(document.forms[0].unmappedList)
	{	
		for(var i=0;i<document.forms[0].unmappedList.options.length;i++)
			document.forms[0].unmappedList.options[i].selected=false;
	}
	

}


function moveRightAll()
{
	
	
	var source  = document.forms[0].unmappedList;
	var target = document.forms[0].mappedList;	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		
	}
	deleteThis(target,source);
}


function moveLeftAll()
{
	var source;
	var target;

	
	
		source  = document.forms[0].mappedList;
		target = document.forms[0].unmappedList;	
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		
	}
	deleteThis(target,source);
}
