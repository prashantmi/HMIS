// Template Parameter Master Javascript Functions ****************************************************

function validateNext()
{
	if(document.getElementsByName('templateName')[0].value=="")
	{
		alert("Enter the Template Name ...");
		document.getElementsByName('templateName')[0].focus();
		return false;
	}
	if(document.getElementsByName('templateType')[0].value=="-1")
	{
		alert("Select Template Type ...");
		document.getElementsByName('templateType')[0].focus();
		return false;
	}
	if(document.getElementsByName('templateCategory')[0].value=="-1")
	{
		alert("Select Template Category ...");
		document.getElementsByName('templateCategory')[0].focus();
		return false;
	}
	if(!validateDates())
		return false;
	return true;
}

function validateDates()
{
	effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	effectiveTo = document.getElementsByName("effectiveTo")[0];
	entryDate = document.getElementsByName("entryDate")[0];

	if(!isEmpty(document.forms[0].effectiveFrom,"Effective From") )
		return false;
	if(!compareDateCall(entryDate,effectiveFrom,2,"Current Date","Effective From") )
		return false;
	if(document.getElementsByName('effectiveTo')[0].value!="")
	{
		if(!compareDateCall(entryDate,effectiveTo,2,"Current Date","Effective To") )
			return false;
		if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
			return false;
	}
	return true;
}

function validateFinalSave()
{	
	if(!window.Pragyas_TemplateDesigner.validateTemplateDone())
		return false;
	window.Pragyas_TemplateDesigner.getTempParaValues();
	
	if(document.getElementsByName('rowCount')[0].value<=0)
	{
		alert("Enter at Least One Row for Template ...");
		return false;
	}
	if(document.getElementsByName('colCount')[0].value<=0)
	{
		alert("Enter at Least One Column for Template ...");
		return false;
	}
	
	if(document.getElementsByName('parameterValuesList')[0].value=="")
	{
		alert("Enter at Least One Parameter for the Template ... ");
		return false;
	}	
	return true;
}

function validateTempModify()
{
	if(document.getElementsByName('templateName')[0].value=="")
	{
		alert("Enter the Template Name ...");
		document.getElementsByName('templateName')[0].focus();
		return false;
	}
	if(document.getElementsByName('templateCategory')[0].value=="-1")
	{
		alert("Select Template Category ...");
		document.getElementsByName('templateCategory')[0].focus();
		return false;
	}
	if(!validateModifyDates())
		return false;

	var chk=document.getElementById('chkModeModify');
	if(chk)
	{
		if(chk.checked)
			document.getElementsByName('modeTempModify')[0].value="1";
		else
			document.getElementsByName('modeTempModify')[0].value="0";
	}
	return true;
}

function validateModifyDates()
{
	effectiveFrom = document.getElementsByName("effectiveFrom")[0];
	effectiveTo = document.getElementsByName("effectiveTo")[0];
	entryDate = document.getElementsByName("entryDate")[0];

	if(!isEmpty(document.forms[0].effectiveFrom,"Effective From") )
		return false;
	if(document.getElementsByName('effectiveTo')[0].value!="")
	{
		if(!compareDateCall(entryDate,effectiveTo,2,"Current Date","Effective To") )
			return false;
		if(!compareDateCall(effectiveFrom,effectiveTo,2,"Effective From Date","Effective To Date"))
			return false;
	}
	return true;
}

function CallThisOnLoad()
{
	if(document.getElementsByName("hmode")[0].value=="CANCEL")
	{
		submitForm('LIST');
	}
}
