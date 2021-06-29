function moveRightSingle()
{
	var source;
	var target;


	source  = document.forms[0].boardID;
	target = document.forms[0].selectedBoardId;	
	

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
	
	
		source  = document.forms[0].boardID;
		target = document.forms[0].selectedBoardId;	
	
	
	

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

	
		target  = document.forms[0].boardID;
		source = document.forms[0].selectedBoardId;	
	
	

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

	
		target  = document.forms[0].boardID;
		source = document.forms[0].selectedBoardId;	
	
	

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
	if(document.forms[0].selectedBoardId)
		for(var i=0;i<document.forms[0].selectedBoardId.length;i++)
			document.forms[0].selectedBoardId.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].boardID)
	{	
		for(var i=0;i<document.forms[0].boardID.options.length;i++)
			document.forms[0].boardID.options[i].selected=false;
	}
}


function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}



function getBoard(obj){

	var processTypeCode=obj.value;
	if(processTypeCode=="-1"){
		document.getElementsByName('selectedBoardId')[0].length=0;
		document.getElementsByName('boardID')[0].length=0;
	}
	else{
		document.getElementsByName("hmode")[0].value="GETBOARD"
		document.forms[0].submit()
	}	
}



function clearForm(){

	document.getElementsByName('certificateTypeID')[0].value="-1";
	document.getElementsByName('selectedBoardId')[0].length=0;
	document.getElementsByName('boardID')[0].length=0;
}
 
 function validateSelectedBoard()
 {
   var valid=false;
   if(document.forms[0].selectedBoardId.options.length==0)
	 {
		alert("Choose at Least One Board");
		document.forms[0].selectedBoardId.focus();
		valid=false;
	 }else{
	    valid=true;
	 }
   return valid;
 }
 
 
function validateFinalSubmit()
{
	if(comboValidation(document.getElementsByName('certificateTypeID')[0],"Certificate Type")
	  && validateSelectedBoard())
	{
		return true;
	}
	else{
		return false;
	}
}

function finalSubmit(mode)
{
	if (validateFinalSubmit()) 
      {	submitPage(mode); }
	
}

/*

function getFetchedList(){

	var fetchedList=document.forms[0].selectedBoardId;
	var selectedData=document.forms[0].fetchedList.value;
	for(var i=0;i<fetchedList.length;i++){
		selectedData=selectedData + fetchedList.options[i].value +"%";
	}
	document.forms[0].fetchedList.value=selectedData;
}
*/