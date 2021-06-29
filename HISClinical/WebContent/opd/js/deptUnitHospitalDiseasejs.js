function mappedDiseasePopUp(e)
{
	var selectedDisease=document.getElementsByName("selecteHospitaldDisease")[0].value;
	var child = window.open(createFHashAjaxQuery('/HISClinical/opd/master/DeptUnitIcdMapping.cnt?hmode=MAPPEDDISEASE'),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus();
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}
function moveRightSingle(listNo)
{
	var source;
	var target;
	if(listNo=="1")
	{
		source  = document.forms[0].hosdiseaseCode;
		target = document.forms[0].selecteHospitaldDisease;	
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
		source  = document.forms[0].hosdiseaseCode;
		target = document.forms[0].selecteHospitaldDisease;	
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
		target  = document.forms[0].hosdiseaseCode;
		source = document.forms[0].selecteHospitaldDisease;	
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
		target  = document.forms[0].hosdiseaseCode;
		source = document.forms[0].selecteHospitaldDisease;	
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
	// Select All para in Selected
	if(document.forms[0].hosdiseaseCode)
		for(var i=0;i<document.forms[0].selecteHospitaldDisease.length;i++)
			document.forms[0].selecteHospitaldDisease.options[i].selected=true;
	
	// Unselect Remaining 
	if(document.forms[0].selecteHospitaldDisease)
	{	
		for(var i=0;i<document.forms[0].hosdiseaseCode.options.length;i++)
			document.forms[0].hosdiseaseCode.options[i].selected=false;
	}
}



function validateAdd()
{	
	if(document.forms[0].selecteHospitaldDisease.options.length==0)
	{
		alert("Choose at Least One Disease");
		document.forms[0].selecteHospitaldDisease.focus();
		return false;
	}
	return true;
	
	
}
function validateModify()
{	
	if(document.forms[0].selecteHospitaldDisease.options.length==0)
	{
		alert("Choose at Least One Disease");
		document.forms[0].selecteHospitaldDisease.focus();
		return false;
	}
	return true;
	
	
}
function modifyClear()
{
	moveLeftAll("1");
}
function submitForm(mode)
{
  	  MoveToSelected();
     document.getElementsByName("hmode")[0].value=mode;
	 document.forms[0].submit();
	 
}
function clearForm(){

	document.getElementsByName('hosdiseaseCode')[0].length=0;
	document.getElementsByName('selecteHospitaldDisease')[0].length=0;
}