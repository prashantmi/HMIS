function moveRightSingle()
{
	var source;
	var target;

		source  = document.forms[0].district;
		target = document.forms[0].selectedDistrict;	
	
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

function moveRightAll()
{
	var source;
	var target;
	
		source  = document.forms[0].district;
		target = document.forms[0].selectedDistrict;	
	
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

		target  = document.forms[0].district;
		source = document.forms[0].selectedDistrict;	

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

function moveLeftAll()
{
	var source;
	var target;

		target  = document.forms[0].district;
		source = document.forms[0].selectedDistrict;	

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

function MoveToSelected()
{
 //alert("abc");
	// Select All Parameters in Selected
	if(document.forms[0].selectedDistrict){
	// alert("selectedDistrict");
		for(var i=0;i<document.forms[0].selectedDistrict.length;i++)
			document.forms[0].selectedDistrict.options[i].selected=true;}

	// Unselect Remaining Parameters
	if(document.forms[0].district)
	{	
		for(var i=0;i<document.forms[0].district.options.length;i++)
			document.forms[0].district.options[i].selected=false;
	}
}


function submitPage(mode)
{	
   var locationYes=document.getElementsByName('locationBound')[0];
   if(locationYes.checked)
   {
      MoveToSelected();
   }
    //alert("mode"+mode);
    if(validate()){
	document.forms[0].hmode.value=mode;
	document.forms[0].submit(); }
}


function showDistrict()
{
  var locationYes=document.getElementsByName('locationBound')[0];
 
   if(locationYes.checked)
   {
     document.getElementById('showDistrictDetails').style.display="block";
   }else{
    document.getElementById('showDistrictDetails').style.display="none";
   }
}

function showAge()
{
 var ageBound=document.getElementsByName('ageBound')[0];
 
 if(ageBound.checked)
 {
   document.getElementsByName('minAge')[0].disabled=false;
   document.getElementsByName('maxAge')[0].disabled=false;
 }else{
   document.getElementsByName('minAge')[0].disabled=true;
   document.getElementsByName('maxAge')[0].disabled=true;
   document.getElementsByName('minAge')[0].value="";
   document.getElementsByName('maxAge')[0].value="";
 }
}


function showCertNo()
{
	var isCetNoReq = document.getElementsByName('isCertNoRequired')[0];

	if(isCetNoReq.checked)
	{
		document.getElementsByName('certNoStartFrom')[0].disabled=false;
	}
	else
	{
		document.getElementsByName('certNoStartFrom')[0].disabled=true;
		document.getElementsByName('certNoStartFrom')[0].value="";
	}
}

/*
function onload()
{
  alert("onlode");
  if(document.getElementsByName('hmode')[0].value!='ADD' )
     {
       showDistrict();
        showAge();
     }
}
*/



function validate()
{
   //alert("validate");
    var valid=false;
   if(   comboValidation(document.getElementsByName('certificateCatID')[0],"Certificate Category") 
       &&  isEmpty(document.getElementsByName('certificateTypeName')[0],"Certificate Type Name")
	  
       && comboValidation(document.getElementsByName('boardType')[0],"Board Type")
       && isEmpty(document.getElementsByName('minRequest')[0],"Minimum Request") 
       && isEmpty(document.getElementsByName('maxRequest')[0],"Maximum Request") 
       && checkMaxReqValidation()
       && comboValidation(document.getElementsByName('requisitionBy')[0],"Requisition By")
       && comboValidation(document.getElementsByName('issueType')[0],"Issue Type")
       && comboValidation(document.getElementsByName('deptUnitCode')[0],"Department Unit")
       && validateAge()
       && checkMaxAgeValidation()
       && validateDistrict()
       && comboValidation(document.getElementsByName('templateCode')[0],"Template")
       && validateIssueType()
       && validateIsCertNoReq()
       
       
		 	)
		 	 {
			  valid=true;
			 }
			 else { valid=false;}
	return 	valid;	      

  }



function validateAge()
{
  var valid=true;
  var hasFlag;
  var ageBound=document.getElementsByName('ageBound')[0];
 if(ageBound.checked)
 { 
    if( isEmpty(document.getElementsByName('minAge')[0],"Minimum Age")
     && isEmpty(document.getElementsByName('maxAge')[0],"Maximum Age") )
     {
       valid=true;
     }else{ valid=false;  }
 }
  return valid;
}


function validateIsCertNoReq()
{
	var valid=true;
	var hasFlag;
	var isCertNoReq=document.getElementsByName('isCertNoRequired')[0];
	if(isCertNoReq.checked)
	{
		if( isEmpty(document.getElementsByName('certNoStartFrom')[0],"Certificate No Starts From"))
		{
			valid=true;
		}
		else
		{
			valid=false;
		}
	}
	return valid;
}


function checkMaxReqValidation()
{
  var valid=true;
  var maxReq=parseInt(document.getElementsByName('maxRequest')[0].value);
  var minReq=parseInt(document.getElementsByName('minRequest')[0].value);
  
   if(maxReq==0)
   {
    alert("Maximum Requisition cannot be 0");
     valid=false;
     return  valid;
   }

   if(minReq>maxReq)
   {
    alert("Minimum Requisition should be less than or equal to Maximum Requisition");
     valid=false;
   }
  return  valid;
   
}

function checkMaxAgeValidation()
{
  var valid=true;
  var maxAge=parseInt(document.getElementsByName('maxAge')[0].value);
  var minAge=parseInt(document.getElementsByName('minAge')[0].value);
  
   if(maxAge>125)
   {
    alert("Maximum Age should be less than or equal to 125");
     valid=false;
     return valid;
   }

   if(minAge>maxAge)
   {
    alert("Minimum Age should be less than or equal to Maximum Age");
     valid=false;
   }
  return valid; 
}


function validateDistrict()
{

  var locationYes=document.getElementsByName('locationBound')[0];
  var valid=true;
   if(locationYes.checked)
   {
    if(document.forms[0].selectedDistrict.options.length==0)
	 {
		alert("Choose at Least One District");
		document.forms[0].selectedDistrict.focus();
		return false;
	 }
   }
  return valid;
 } 

function validateIssueType()
{

  
  var valid=true;
   
   //alert("requisition by "+document.getElementsByName('requisitionBy')[0].value);
   //alert("issue type "+document.getElementsByName('issueType')[0].value);
   if(document.getElementsByName("requisitionBy")[0].value=="2")
	{
		if(document.getElementsByName("issueType")[0].value!="2")
	    {
	    	alert("For requisition by Individual"+'\n'+"Issue type can only be Individual");
	       	document.getElementsByName('issueType')[0].focus();
	       	return false;
	    }
	 }
 return valid;
} 





