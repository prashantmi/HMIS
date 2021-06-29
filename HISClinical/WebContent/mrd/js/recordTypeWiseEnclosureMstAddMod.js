function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit()
{
	var valid=true;
	if(document.getElementsByName('enclosure')[0].value=="")
	{
		alert("Enter Enclosure...");
		document.getElementsByName('enclosure')[0].focus();
		valid=false;
	}
	if(document.forms[0].isActive && document.forms[0].isActive.value=="-1")
	{
		alert("Select IsActive Status ... ");
		document.forms[0].isActive.focus();
		valid=false;                          
	}
	
    return valid;
 } 	
	
function finalSubmit(mode)
{
	if (validateFinalSubmit())
	{		
		submitPage(mode);
	}
}

function clearForm()
{
   document.getElementsByName('enclosure')[0].value="";
   document.getElementsByName('remarks')[0].value="";
   document.getElementsByName('recordTypeId')[0].value="-1";
   if(document.forms[0].isActive)
 	   document.forms[0].isActive.value=="-1"
}

function getEnclosure(e)
{
	var recordTypeId=document.getElementsByName("recordTypeId")[0].value;
	var path="/HISClinical/mrd/master/recordTypeWiseEnclosureMst.cnt?hmode=GETENCLOSURE&recordTypeId="+recordTypeId;
	//openPopup(path,e, "300", "600"); 
	openPopup(createFHashAjaxQuery(path,e, "300", "600")); 
}


function moveRightSingle()
{
	var source;
	var target;
	
	source  = document.forms[0].enclosureID;
	target = document.forms[0].selectedEnclosureID;	
	
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


function moveLeftSingle()
{
	var source;
	var target;

		target  = document.forms[0].enclosureID;
		source = document.forms[0].selectedEnclosureID;	

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

		target  = document.forms[0].enclosureID;
		source = document.forms[0].selectedEnclosureID;	

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


function moveRightAll()
{
	var source;
	var target;
	
		source  = document.forms[0].enclosureID;
		target = document.forms[0].selectedEnclosureID;	

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

function MoveToSelected()
{
	// Unselect Remaining User
	for(var i=0;i<document.getElementsByName("enclosureID")[0].length;i++)
		document.getElementsByName("enclosureID")[0].options[i].selected=false;
	
	// Select All User in Selected
	for(var i=0;i<document.getElementsByName("selectedEnclosureID")[0].length;i++)
		document.getElementsByName("selectedEnclosureID")[0].options[i].selected=true;
}
  

function validateOk()
{
	var valid=false;
	var selectedIndex="";
	if(document.getElementsByName("selectedEnclosureID")[0].options.length==0)
	{
		alert("Choose at Least One Enclosure");
		return false;
	}
	else
	{
		for(var i=0;i<document.getElementsByName("selectedEnclosureID")[0].options.length;i++)
		{
			selectedIndex=selectedIndex+document.getElementsByName("selectedEnclosureID")[0].options[i].value+"@";
		}
		
		if(selectedIndex!="") selectedIndex=selectedIndex.substring(0,selectedIndex.length-1);
		opener.document.getElementsByName('hmode')[0].value='POPULATE'
		opener.document.getElementsByName('concatedIndex')[0].value=selectedIndex;
		opener.document.forms[0].submit();
		self.close();
	}
		
}  

function validateSave()
{
	for(var i=0;i<document.getElementsByName("isCompulsory").length;i++)
	{
		if(document.getElementsByName("isCompulsory")[i].value=="-1")
		{
			alert("Please Select the Compulsory for '" +document.getElementsByName("hiddenEnclosure")[i].value+"'");
			document.getElementsByName("isCompulsory")[i].focus();
			return false;
		}
	}
	submitPage('SAVE');
}

function closeForm()
{
	self.close();
}

 