function moveRightSingle(listNo)
{
	var source;
	var target;

	// 1 -> Billing category
	
	
	if(listNo=="1")
	{
		source  = document.forms[0].designationId;
		target = document.forms[0].selectedDesignationId;	
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
		source  = document.forms[0].designationId;
		target = document.forms[0].selectedDesignationId;	
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
		target  = document.forms[0].designationId;
		source = document.forms[0].selectedDesignationId;	
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
		target  = document.forms[0].designationId;
		source = document.forms[0].selectedDesignationId;	
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
	if(document.forms[0].selectedDesignationId)
		for(var i=0;i<document.forms[0].selectedDesignationId.length;i++)
			document.forms[0].selectedDesignationId.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].designationId)
	{	
		for(var i=0;i<document.forms[0].designationId.options.length;i++)
			document.forms[0].designationId.options[i].selected=false;
	}
}


function submitPage2(mode)
{
	//alert("inside submitPage");
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(mode)
{
	//alert("inside validateFinalSubmit");
	if(comboValidation(document.getElementsByName('processType')[0],"Process Type"))
	{
		return true;
	}
	else{
		return false;
	}
}

function finalSubmit(mode)
{
	//alert("inside finalSubmit");
	if (!validateFinalSubmit(mode)) 
	return;
	submitPage2(mode);
	
}
function clearForm(){

	document.getElementsByName('processType')[0].value="-1";
	document.getElementsByName('selectedDesignationId')[0].length=0;
	document.getElementsByName('designationId')[0].length=0;
}
 
function getFetchedList(){

	var fetchedList=document.forms[0].selectedDesignationId;
	var selectedData=document.forms[0].fetchedList.value;
	for(var i=0;i<fetchedList.length;i++){
		selectedData=selectedData + fetchedList.options[i].value +"%";
	}
	document.forms[0].fetchedList.value=selectedData;
}

function getDesignation(obj){

	var processTypeCode=obj.value;
	if(processTypeCode=="-1"){
		document.getElementsByName('selectedDesignationId')[0].length=0;
		document.getElementsByName('designationId')[0].length=0;
	}
	else{
		document.getElementsByName("hmode")[0].value="GETDESIG"
		document.forms[0].submit()
	}	
}