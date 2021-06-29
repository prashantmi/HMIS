function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Billing category
	
	
	if(listNo=="1")
	{
		source  = document.forms[0].areaCode;
		target = document.forms[0].selectedAreaCode;	
	}

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

function moveRightAll(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].areaCode;
		target = document.forms[0].selectedAreaCode;	
	}
	
	

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

function moveLeftSingle(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].areaCode;
		source = document.forms[0].selectedAreaCode;	
	}
	

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

function moveLeftAll(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].areaCode;
		source = document.forms[0].selectedAreaCode;	
	}
	

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
	// Select All Shift in Selected
	if(document.forms[0].selectedAreaCode)
		for(var i=0;i<document.forms[0].selectedAreaCode.length;i++)
			document.forms[0].selectedAreaCode.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].areaCode)
	{	
		for(var i=0;i<document.forms[0].areaCode.options.length;i++)
			document.forms[0].areaCode.options[i].selected=false;
	}
}


function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(mode)
{
	if(comboValidation(document.getElementsByName('blockId')[0],"Duty Block Name")
	&& validateAreaType(mode))
	{
		return true;
	}
	else{
		return false;
	}
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit(mode)) 
	return;
	submitPage(mode);
	
}
function clearForm(){

	document.getElementsByName('blockId')[0].value="-1";
		document.getElementsByName('selectedAreaCode')[0].length=0;
	document.getElementsByName('areaCode')[0].length=0;
	document.getElementsByName('areaTypeCode')[0].value="-1";
}
  
function moveRightSingleForModify(listNo)
{
	var source;
	var target;

	// 1 -> Parameter
	
	
	if(listNo=="1")
	{
		source  = document.forms[0].areaCode;
		target = document.forms[0].selectedAreaCode;	
	}
	

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

function moveRightAllForModify(listNo)
{
	var source;
	var target;
	
	if(listNo=="1")
	{
		source  = document.forms[0].areaCode;
		target = document.forms[0].selectedAreaCode;	
	}
	

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

function moveLeftSingleForModify(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].areaCode;
		source = document.forms[0].selectedAreaCode;	
	}
	

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

function moveLeftAllForModify(listNo)
{
	var source;
	var target;

	if(listNo=="1")
	{
		target  = document.forms[0].areaCode;
		source = document.forms[0].selectedAreaCode;	
	}
	

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

function getFetchedList(){

	var fetchedList=document.forms[0].selectedAreaCode;
	var selectedData=document.forms[0].fetchedList.value;
	for(var i=0;i<fetchedList.length;i++){
		selectedData=selectedData + fetchedList.options[i].value +"%";
	}
	document.forms[0].fetchedList.value=selectedData;
}

function getDutyAreaCode(){

	var areaTypeCode=document.getElementsByName('areaTypeCode')[0].value;
	if(document.getElementsByName('blockId')[0].value=="-1"){
		alert("Please Select Duty Block Name");
		document.getElementsByName('areaTypeCode')[0].value="-1";
		return;
	}
	else{
		if(areaTypeCode=="-1"){
			submitPage('NEW');
		}	
		else{
			submitPage('GETAREACODE');
		}	
	}
}

function validateAreaType(mode){
	if(mode=="SAVE"){
		if(!comboValidation(document.getElementsByName('areaTypeCode')[0],"Area Type"))
		{
			return false;
		}
		if(document.forms[0].selectedAreaCode.options.length==0)
		{
			alert("Choose at Least One Area Code");
			document.forms[0].selectedAreaCode.focus();
			return false;
		}
	}
	return true;

}

function submitBlockCode(){

if(document.getElementsByName("areaTypeCode")[0].value!="-1")
 {	
	var areaTypeCode=document.getElementsByName('areaTypeCode')[0].value;
	if(document.getElementsByName('blockId')[0].value=="-1"){
		alert("Please Select Duty Block Name");
		document.getElementsByName('areaTypeCode')[0].value="-1";
		return;
	}
	else{
		if(areaTypeCode=="-1"){
			submitPage('NEW');
		}	
		else{
			submitPage('GETAREACODE');
		}	
	 }
  }	
 	
}