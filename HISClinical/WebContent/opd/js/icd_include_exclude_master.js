function emptyCombo(cbo)
{
	cbo.innerHTML = "";
	var opt = document.createElement("option");
	opt.text = "Select Value";
	opt.value = "-1";
	cbo.appendChild(opt);
}

function setSubGroup(elemGroup)
{
	var elemSubgroup = document.getElementsByName("icdSubgroupCode")[0];
	var elemDisease = document.getElementsByName("diseaseCode")[0];
	if(elemGroup.value=="-1")
	{
		emptyCombo(elemSubgroup);
		emptyCombo(elemDisease);
		return;
	}
	var elemSubGroupLst = document.getElementsByName("icdSubgroupList")[0];
	
	elemSubgroup.innerHTML = "";
	var opt = document.createElement("option");
	opt.text = "Select Value";
	opt.value = "-1";
	elemSubgroup.appendChild(opt);
	
	for(var i=0; i<elemSubGroupLst.options.length; i++)
	{
		var subGroupCode = elemSubGroupLst.options[i].value.split("#")[0];
		var groupCode = elemSubGroupLst.options[i].value.split("#")[1];
		if(trimData(groupCode)==trimData(elemGroup.value))
		{
			opt = document.createElement("option");
			opt.text = elemSubGroupLst.options[i].text;
			opt.value = subGroupCode;
			elemSubgroup.appendChild(opt);
		}
	}	
	emptyCombo(elemDisease);
}

function setDiseaseList(elemSubGroup)
{
	var elemDisease = document.getElementsByName("diseaseCode")[0];
	if(elemSubGroup.value=="-1")
	{
		emptyCombo(elemDisease);
		return;
	}
	var elemDiseaseLst = document.getElementsByName("icdDiseaseList")[0];
	
	elemDisease.innerHTML = "";
	var opt = document.createElement("option");
	opt.text = "Select Value";
	opt.value = "-1";
	elemDisease.appendChild(opt);
	
	for(var i=0; i<elemDiseaseLst.options.length; i++)
	{
		var diseaseCode = elemDiseaseLst.options[i].value.split("#")[0];
		var subGroupCode = elemDiseaseLst.options[i].value.split("#")[1];
		if(trimData(subGroupCode)==trimData(elemSubGroup.value))
		{
			opt = document.createElement("option");
			opt.text = elemDiseaseLst.options[i].text;
			opt.value = diseaseCode;
			elemDisease.appendChild(opt);
		}
	}
}

function submitForm(mode)
{	
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function validate()
{
	if(document.getElementsByName("icdGroupCode")[0].value=="-1")
	{
		alert("Please Select Group");
		document.getElementsByName("icdGroupCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("icdSubgroupCode")[0].value=="-1")
	{
		alert("Please Select Sub Group");
		document.getElementsByName("icdSubgroupCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("diseaseCode")[0].value=="-1")
	{
		alert("Please Select Disease");
		document.getElementsByName("diseaseCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("recordType")[0].value=="-1")
	{
		alert("Please Select Record Type");
		document.getElementsByName("recordType")[0].focus();
		return false;
	}
	if(trimData(document.getElementsByName("disease")[0].value)=="")
	{
		alert("Please Enter " +document.getElementById("boldEntryName").innerHTML);
		document.getElementsByName("disease")[0].focus();
		return false;
	}
	return true;
}

function diseaseSelected(elemDisease)
{
	if(document.getElementsByName("diseaseCode")[0].value=="-1")
	{
		document.getElementById("divDiseaseDetail").innerHTML="";
		document.getElementById("divDiseaseDetail").style.display = "block";		
	}
	/*else
		submitForm("SETDISEASEDTL");*/
}

function clearTextFields()
{
	document.getElementsByName("icdGroupCode")[0].value="-1";
	document.getElementsByName("icdGroupCode")[0].focus();
	document.getElementsByName("icdSubgroupCode")[0].value="-1";
	document.getElementsByName("diseaseCode")[0].value="-1";
	document.getElementById("divDiseaseDetail").innerHTML = "";
	document.getElementsByName("recordType")[0].value="-1";
	document.getElementsByName("disease")[0].value="";
	document.getElementsByName("parentCode")[0].value="-1";
	document.getElementsByName("parentCode")[0].disabled = true;
	document.getElementsByName("diseaseRemark")[0].value="";
}	


function validateModify()
{
	/*if(trimData(document.getElementsByName("attendantReason")[0].value)=="")
	{
		alert("Please Enter Reason");
		document.getElementsByName("attendantReason")[0].focus();
		return false;
	}
	if( trimData(document.getElementsByName("attendantReason")[0].value.toUpperCase())==document.getElementsByName("oldAttendantReason")[0].value.toUpperCase()
		&& document.getElementsByName("isValid")[0].value==document.getElementsByName("oldIsValid")[0].value)
	{
		alert("Please Change at least One Field");
		document.getElementsByName("attendantReason")[0].focus();
		return false;
	}*/
	return true;
}
