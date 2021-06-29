function onLoadFunction()
{
	if (document.getElementsByName("parentIndexModifierCode")[0])
	{
		if(document.getElementsByName("modifierLevel")[0].value=="1")
		{
		document.getElementsByName("parentIndexModifierCode")[0].disabled = true;
		}
	}


	if(document.getElementsByName("diseaseCodeChk") && document.getElementsByName("dualDiseaseCodeChk"))
	{
		var chkICD = document.getElementsByName("diseaseCodeChk")[0];
		var chkDualICD = document.getElementsByName("dualDiseaseCodeChk")[0];
		if(chkICD.checked)	showHideICDDis(chkICD);
		if(chkDualICD.checked)	showHideDualICDDis(chkDualICD);
	}
}

// Emptying a Combo
function doEmptyCombo(cbo)
{
	if(cbo)
	{
		cbo.innerHTML="";
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		cbo.appendChild(op);
	}
}



function submitForm(mode)
{
  	document.getElementsByName("hmode")[0].value=mode;

	 document.forms[0].submit();
	 
}

function populateParentModifier()
{

	if(document.getElementsByName("indexCode")[0].value=="-1")
	{
		alert("Please select Index Term to enter Modifier at Level greater than 1");
		document.getElementsByName("modifierLevel")[0].value="1";
		document.getElementsByName("parentIndexModifierCode")[0].disabled = true;
		document.getElementsByName("indexCode")[0].focus();
		return;
	}
	if(document.getElementsByName("modifierLevel")[0].value!="1")
	{
		document.getElementsByName("hmode")[0].value="SETPARENTMODIFIER";
		document.getElementsByName("parentIndexModifierCode")[0].disabled = false;
		document.forms[0].submit();
	}	
	
	if(document.getElementsByName("modifierLevel")[0].value=="1")
	{
	document.getElementsByName("parentIndexModifierCode")[0].disabled = true;
	}
	
}

function populateIcdSubGroup()
{
	if(document.getElementsByName("icdGroupCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETICDSUBGROUP";
		document.forms[0].submit();
	}
	else
	{
		//-------- Empty Sub Group & Disease Combo
		doEmptyCombo(document.getElementsByName("icdSubgroupCode")[0]);
		doEmptyCombo(document.getElementsByName("diseaseCode")[0]);
	}
}

function populateDualIcdSubGroup()
{
	if(document.getElementsByName("dualIcdGroupCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETDUALICDSUBGROUP";
		document.forms[0].submit();
	}	
	else
	{
		//-------- Empty Dual Sub Group & Dual Disease Combo
		doEmptyCombo(document.getElementsByName("dualIcdSubGroupCode")[0]);
		doEmptyCombo(document.getElementsByName("dualDiseaseCode")[0]);
	}
}

function populateIcdDisease()
{
	if(document.getElementsByName("icdSubgroupCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETICDDISEASE";
		document.forms[0].submit();
	}
	else
	{
		doEmptyCombo(document.getElementsByName("diseaseCode")[0]);
	}
	
}

function populateDualIcdDisease()
{
	if(document.getElementsByName("dualIcdSubGroupCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETDUALICDDISEASE";
		document.forms[0].submit();
	}
	else
	{
		doEmptyCombo(document.getElementsByName("dualDiseaseCode")[0]);
	}
}

function showHideICDDis(chk)
{
	var div = document.getElementById("divIcdDiseaseCode");
	if(chk.checked)
	{
		div.style.display="block";
	}
	else
	{
		div.style.display="none";
	}
}

function showHideDualICDDis(chk)
{
	var div = document.getElementById("divDualIcdDiseaseCode");
	if(chk.checked)
	{
		div.style.display="block";
	}
	else
	{
		div.style.display="none";
	}
}

function validateAddModify(mode)
{
	if (!validateForm()) 
		return;
	else
	{
		document.getElementsByName("hmode")[0].value = mode;
		document.forms[0].submit();
	}		
}

function validateForm()
{

if(document.getElementsByName("indexCode")[0].value=="-1")
		{
		
			alert("Please Select Index Term");
			document.getElementsByName("indexCode")[0].focus();
			return false;
		}

if(document.getElementsByName("modifierLevel")[0].value=="")
		{
		
			alert("Please Select Modifier Level");
			document.getElementsByName("modifierLevel")[0].focus();
			return false;
		}
		
if(document.getElementsByName("modifier")[0].value=="")
		{
		
			alert("Please Enter Modifier Term");
			document.getElementsByName("modifier")[0].focus();
			return false;
		}

if( (document.getElementsByName("isWith")[0].checked == "" ) && (document.getElementsByName("isWith")[1].checked == "" ) )
		{
		
			alert("Please Select IsWith");
			document.getElementsByName("isWith")[0].focus();
			return false;
		}

		
		if( (document.getElementsByName("modifierLevel")[0].value!="1") && (document.getElementsByName("parentIndexModifierCode")[0].value=="-1"))  		
			{
				
					alert("Please Select Parent Modifier");
					document.getElementsByName("parentIndexModifierCode")[0].focus();
					return false;
			}

// Icd Disease
if(document.getElementsByName("diseaseCodeChk")[0].value == "0")
{
			if(document.getElementsByName("icdGroupCode")[0].value=="-1")
			{
			
				alert("Please Select Icd Group");
				document.getElementsByName("icdGroupCode")[0].focus();
				return false;
			}
			if(document.getElementsByName("icdSubgroupCode")[0].value=="-1")
			{
			
				alert("Please Select Icd Sub Group");
				document.getElementsByName("icdSubgroupCode")[0].focus();
				return false;
			}
			if(document.getElementsByName("diseaseCode")[0].value=="-1")
			{
				alert("Please Select Icd Disease");
				document.getElementsByName("diseaseCode")[0].focus();
				return false;
			}
}
// Dual Icd Disease
if(document.getElementsByName("dualDiseaseCodeChk")[0].value == "0")
{
	if(document.getElementsByName("dualIcdGroupCode")[0].value=="-1")
	{
	
		alert("Please Select Dual Icd Group");
		document.getElementsByName("dualIcdGroupCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("dualIcdSubGroupCode")[0].value=="-1")
	{
	
		alert("Please Select Dual Icd Sub Group");
		document.getElementsByName("dualIcdSubGroupCode")[0].focus();
		return false;
	}
	if(document.getElementsByName("dualDiseaseCode")[0].value=="-1")
	{
	
		alert("Please Select Dual Icd Disease");
		document.getElementsByName("dualDiseaseCode")[0].focus();
		return false;
	}
}	
	return true;
		
}