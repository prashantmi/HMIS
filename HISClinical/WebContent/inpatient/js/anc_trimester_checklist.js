function popupApgarDetail(event)
{
	var path='/HISClinical/inpatient/ancTrimesterChecklistDetail.cnt?hmode=ALLCHECKLISTSHOW';
	openPopup(path,event,400,800);
}

function setDrugChklstName(obj)
{
	var drugChklstName = "";
	if(obj.value != "-1")
		drugChklstName = obj.options[obj.selectedIndex].text;
	document.getElementsByName("drugChecklistName")[0].value=drugChklstName;
}

function validateDrugAdd()
{
	if(document.getElementsByName("drugChecklistId")[0].value=="-1")
	{
		alert("Please Select Checklist Item for Drug/Immuization");
		document.getElementsByName("drugChecklistId")[0].focus();
		return false;
	}
	if(document.getElementsByName("drugConductDate")[0].value=="")
	{
		alert("Please Enter Date");
		document.getElementsByName("drugConductDate")[0].focus();
		return false;
	}
	// conduct date <sysdate & >lmp
	if(!validateDateEqualNBefore(document.getElementsByName("drugConductDate")[0],"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
	{
		alert("Date can't be greater than Current Date...");
		document.getElementsByName("drugConductDate")[0].focus();
		return false;
	}
	if(!validateDateAfterOnly(document.getElementsByName("drugConductDate")[0],"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
	{
		alert("Date should be greater than Gestation Start Date ("+document.getElementsByName("gestationStartDate")[0].value+")...");
		document.getElementsByName("drugConductDate")[0].focus();
		return false;
	}
	return true;
}

function addDrugRow()
{
	if(validateDrugAdd())
		submitForm('ADDDRUGROW');
}

function minusDrugRow(_val)
{
	document.getElementsByName("delDrugCheckListIndex")[0].value=_val;
	submitForm('DELETEDRUGROW');
}


function setInvstChklstName(obj)
{
	var invstChklstName = "";
	if(obj.value != "-1")
		invstChklstName = obj.options[obj.selectedIndex].text;
	document.getElementsByName("invstChecklistName")[0].value=invstChklstName;
}

function validateInvstAdd()
{
	if(document.getElementsByName("invstChecklistId")[0].value=="-1")
	{
		alert("Please Select Checklist Item for Investigation");
		document.getElementsByName("invstChecklistId")[0].focus();
		return false;
	}
	if(document.getElementsByName("invstConductDate")[0].value=="")
	{
		alert("Please Enter Date");
		document.getElementsByName("invstConductDate")[0].focus();
		return false;
	}
	// conduct date <sysdate & >lmp
	if(!validateDateEqualNBefore(document.getElementsByName("invstConductDate")[0],"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
	{
		alert("Date can't be greater than Current Date...");
		document.getElementsByName("invstConductDate")[0].focus();
		return false;
	}
	if(!validateDateAfterOnly(document.getElementsByName("invstConductDate")[0],"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
	{
		alert("Date should be greater than Gestation Start Date ("+document.getElementsByName("gestationStartDate")[0].value+")...");
		document.getElementsByName("invstConductDate")[0].focus();
		return false;
	}
	if(document.getElementsByName("invstResult")[0].value=="")
	{
		alert("Please Enter Result");
		document.getElementsByName("invstResult")[0].focus();
		return false;
	}
	return true;
}

function addInvstRow()
{
	if(validateInvstAdd())
		submitForm('ADDINVSTROW');
}

function minusInvstRow(_val)
{
	document.getElementsByName("delInvstCheckListIndex")[0].value=_val;
	submitForm('DELETEINVSTROW');
}

function validateSave()
{
	// At least one to enter
	if(document.getElementsByName("drugChecklistId")[0].value=="-1" 
		&& document.getElementsByName("drugConductDate")[0].value=="" 
		&& document.getElementsByName("invstChecklistId")[0].value=="-1" 
		&& document.getElementsByName("invstConductDate")[0].value=="" 
		&& document.getElementsByName("invstResult")[0].value==""
		&& (!document.getElementsByName("addedDrugCount")[0] && !document.getElementsByName("addedInvestigationCount")[0]))
	{
		alert("Please Enter either Drug/Immunization or Investigation Detail to Save");
		document.getElementsByName("drugChecklistId")[0].focus();
		return false;
	}
	
	// If one entereed then Complete it
	if(document.getElementsByName("drugChecklistId")[0].value!="-1" 
		|| document.getElementsByName("drugConductDate")[0].value!="") 
	{
		if(document.getElementsByName("drugChecklistId")[0].value=="-1")
		{
			alert("Please Select Checklist Item for Drug/Immuization");
			document.getElementsByName("drugChecklistId")[0].focus();
			return false;
		}
		if(document.getElementsByName("drugConductDate")[0].value=="")
		{
			alert("Please Enter Date");
			document.getElementsByName("drugConductDate")[0].focus();
			return false;
		}
		// conduct date <sysdate & >lmp
		if(!validateDateEqualNBefore(document.getElementsByName("drugConductDate")[0],"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{
			alert("Date can't be greater than Current Date...");
			document.getElementsByName("drugConductDate")[0].focus();
			return false;
		}
		if(!validateDateAfterOnly(document.getElementsByName("drugConductDate")[0],"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
		{
			alert("Date should be greater than Gestation Start Date ("+document.getElementsByName("gestationStartDate")[0].value+")...");
			document.getElementsByName("drugConductDate")[0].focus();
			return false;
		}
	}
	if(document.getElementsByName("invstChecklistId")[0].value!="-1" 
		|| document.getElementsByName("invstConductDate")[0].value!="" 
		|| document.getElementsByName("invstResult")[0].value!="")
	{
		if(document.getElementsByName("invstChecklistId")[0].value=="-1")
		{
			alert("Please Select Checklist Item for Investigation");
			document.getElementsByName("invstChecklistId")[0].focus();
			return false;
		}
		if(document.getElementsByName("invstConductDate")[0].value=="")
		{
			alert("Please Enter Date");
			document.getElementsByName("invstConductDate")[0].focus();
			return false;
		}
		// conduct date <sysdate & >lmp
		if(!validateDateEqualNBefore(document.getElementsByName("invstConductDate")[0],"dd-Mon-yyyy",document.getElementsByName("entryDate")[0],"dd-Mon-yyyy"))
		{
			alert("Date can't be greater than Current Date...");
			document.getElementsByName("invstConductDate")[0].focus();
			return false;
		}
		if(!validateDateAfterOnly(document.getElementsByName("invstConductDate")[0],"dd-Mon-yyyy",document.getElementsByName("gestationStartDate")[0],"dd-Mon-yyyy"))
		{
			alert("Date should be greater than Gestation Start Date ("+document.getElementsByName("gestationStartDate")[0].value+")...");
			document.getElementsByName("invstConductDate")[0].focus();
			return false;
		}
		if(document.getElementsByName("invstResult")[0].value=="")
		{
			alert("Please Enter Result");
			document.getElementsByName("invstResult")[0].focus();
			return false;
		}
	}
	return true;
}