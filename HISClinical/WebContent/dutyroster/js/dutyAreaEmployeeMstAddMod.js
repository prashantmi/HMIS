

function submitDutyArea(these)
{
	
	document.forms[0].areaName.value=these.value;
	submitPage('ADD');
	
}

function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(mode){
     
 //   These All Fields are Mandatory
 var flag=false;
 
 if( 
 	comboValidation(document.getElementsByName('areaTypeCode')[0],"Duty Area Type")
 && comboValidation(document.getElementsByName('areaCode')[0],"Duty Area")
 && comboValidation(document.getElementsByName('empDesg')[0],"Designation")
	)
	{
	flag=true;
	} 



    return flag;
} 	
	
function finalSubmit(mode)
{
var flag=true;

	if (validateFinalSubmit(mode)) 
	{
	   if(mode=="SAVE")
  	       {
  	  var flag=	concatinateEmployeesDuringSave();
  	  	   }	
       if(flag)
    	submitPage(mode);
    	
	}
}




function clearForm()
 {
  	document.getElementsByName('areaTypeCode')[0].value="-1";
    document.getElementsByName('areaCode')[0].value="-1";
    document.getElementsByName('empDesg')[0].value="-1";
   submitPage('NEW'); 
    
      
 }
  
  
 
  
function moveLeftSingle()
{
	
	
		firstMenuObj  = document.forms[0].empCollectionLeft;
		secondMenuObj = document.forms[0].empCollectionRight;	
	
	
	
	var totalElement  = secondMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
						
	for(var i=0;i<totalElement;i++)
	{
		if(secondMenuObj.options[i].selected)
		{
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			
		
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		}
	}
	deleteThis(firstMenuObj,secondMenuObj);
}

function moveLeftAll()
{
	
		firstMenuObj  = document.forms[0].empCollectionLeft;
		secondMenuObj = document.forms[0].empCollectionRight;	
	
	var totalElement  = secondMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
						
	for(var i=0;i<totalElement;i++)
	{
		
			val1 = secondMenuObj.options[i].value;
			val2 = secondMenuObj.options[i].text;
			
		
			t1 = firstMenuObj.length;							
			firstMenuObj.length = (t1+1);				
			
			firstMenuObj.options[t1].value = val1;
			firstMenuObj.options[t1].text  = val2;													
		
	}
	deleteThis(firstMenuObj,secondMenuObj);
}


function moveRightSingle()
{
	
	
	
	var firstMenuObj;
	var secondMenuObj;
	
	
		firstMenuObj  = document.forms[0].empCollectionLeft;
		secondMenuObj = document.forms[0].empCollectionRight;	
	
	
	
	
	var totalElement  = firstMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
	
						
	for(var i=0;i<totalElement;i++)
	{
		if(firstMenuObj.options[i].selected)
		{
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
		
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;													
		}
	}
	
	deleteThis(secondMenuObj,firstMenuObj)
}
function moveRightAll()
{
	var firstMenuObj;
	var secondMenuObj;
	
	
		firstMenuObj  = document.forms[0].empCollectionLeft;
		secondMenuObj = document.forms[0].empCollectionRight;	
	
	
	var totalElement  = firstMenuObj.length
	var val1 = "";
	var val2 = "";
	
	var t1 = 0;
	
						
	for(var i=0;i<totalElement;i++)
	{
		
			val1 = firstMenuObj.options[i].value;
			val2 = firstMenuObj.options[i].text;			
		
			t1 = secondMenuObj.length;							
			secondMenuObj.length = (t1+1);				
			
			secondMenuObj.options[t1].value = val1;
			secondMenuObj.options[t1].text  = val2;													
		
	}
	
	deleteThis(secondMenuObj,firstMenuObj)
}

function deleteThis(srcMenuObj,targetMenuObj)
{	
	
	
	var t =0;
	var val1 = "";
	var val2 = "";	
	
	var len  = targetMenuObj.length;

	var len2 = srcMenuObj.length;
	
		
	var a1 = new Array(len);
	var a2 = new Array(len);
	
	var a3 = new Array(len2);
	
	
	
	for(var i=0;i<len;i++)
	{		
		a1[i]= targetMenuObj.options[i].value;
		a2[i]= targetMenuObj.options[i].text;	
		
	}
	
	
	for( i=0;i<len2;i++)
	{		
		a3[i]= srcMenuObj.options[i].value;
	}
	
	
	
	targetMenuObj.length = 0;
	
	var counter = 0;
	
	
	var k = 0;
	
	var flag = 0;
		
	for(i=0;i<len;i++)
	{		
		flag = 0;
		for(k=0;k<len2;k++)
		{		
			if(a1[i]==a3[k])
			{	
				flag = 1;					
			}					
		}		
		if(flag==0)
		{
			targetMenuObj.length = (counter+1);
			targetMenuObj.options[counter].value = a1[i];
			targetMenuObj.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
	
}
  function concatinateEmployeesDuringSave()
  {
        var flag=true;
        
		var i = 0;
		var str1 = "";
		var str2 = "";
		
		
		var menuObj1 = document.forms[0].empCollectionLeft;
		var len1 = menuObj1.length;	
		
		var menuObj2 = document.forms[0].empCollectionRight;
		var len2 = menuObj2.length;
		
		
		for(i=0;i<len1;i++)
			str1 += menuObj1.options[i].value+"^";		
				
		for(i=0;i<len2;i++)
			str2 += menuObj2.options[i].value+"^";
		
		
	
	if(str2.length==0 || str2=="")
	     {
	       alert("Please Select : Employee") 	;
	       document.forms[0].empCollectionRight.focus(); 
	       flag=false;
	     }   	
		else
			{						
				document.forms[0].newEmpSelectedLeft.value = str1;
				document.forms[0].newEmpSelectedRight.value = str2;
								
				
			}
	
		return flag;
  
  }    
  
 function concatinateEmployeesOnLoad()
  {
       focusOnLoad();
        
		var i = 0;
		var str1 = "";
		var str2 = "";
		
		
		var menuObj1 = document.forms[0].empCollectionLeft;
		var len1 = menuObj1.length;	
		
		var menuObj2 = document.forms[0].empCollectionRight;
		var len2 = menuObj2.length;
		
		
		for(i=0;i<len1;i++)
			str1 += menuObj1.options[i].value+"^";		
				
		for(i=0;i<len2;i++)
			str2 += menuObj2.options[i].value+"^";
		
		
	
	 	
				document.forms[0].oldEmpSelectedLeft.value = str1;
				document.forms[0].oldEmpSelectedRight.value = str2;
								
				
	
	
	
  
  }     

function focusOnLoad()
{

if(document.forms[0].elements[0].value=="-1")
  	document.forms[0].elements[0].focus();
  	else
if(document.forms[0].elements[1].value=="-1")
  	document.forms[0].elements[1].focus();
  	else
if(document.forms[0].elements[2].value=="-1")
  	document.forms[0].elements[2].focus();
  	else
  	document.forms[0].elements[4].focus();
  	
	
}
       