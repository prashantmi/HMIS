/* author Pfiza Nooreen
   Javascript File for Icd Disease Code Master For Vol -3
*/

function onLoadFunction()
{     

        if(document.getElementsByName("diseaseCode")[0].value!="-1")
		{
		  document.forms[0].strDiseaseCodeChk.checked=true;
		}
		if(document.getElementsByName("strDualDiseaseCode")[0].value!="-1")
		{
		document.forms[0].strDualDiseaseCodeChk.checked=true;
		}	
	
	if(document.getElementsByName("strDiseaseCodeChk") && document.getElementsByName("strDualDiseaseCodeChk"))
	{
		var chkICD = document.getElementsByName("strDiseaseCodeChk")[0];
		var chkDualICD = document.getElementsByName("strDualDiseaseCodeChk")[0];
		if(chkICD.checked)	showHideICDDiseaseCode(chkICD);
		if(chkDualICD.checked)	showHideDualICDDisease(chkDualICD);
	}
	
}

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

// to enable Icd Sub Group on change of Group Code
function enableSubGroup()
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

function enableDiseaseCode()
{

	if(document.getElementsByName("icdSubgroupCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETICDDISEASE";
		document.forms[0].submit();
	}
	else
	{
		//-------- Empty Sub Group & Disease Combo
		doEmptyCombo(document.getElementsByName("diseaseCode")[0]);
	}
}


//for dual sub group and dual disease 

function enableDualSubGroup()
{
if(document.getElementsByName("strDualGroupCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETDUALICDSUBGROUP";
		document.forms[0].submit();
	}	
	else
	{
		//-------- Empty Dual Sub Group & Dual Disease Combo
		doEmptyCombo(document.getElementsByName("strDualSubGroupCode")[0]);
		doEmptyCombo(document.getElementsByName("strDualDiseaseCode")[0]);
	}
}

function enableDualDisease()
{
  if(document.getElementsByName("strDualSubGroupCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETDUALICDDISEASE";
		document.forms[0].submit();
	}	
	else
	{
		//-------- Empty Dual Sub Group & Dual Disease Combo
		doEmptyCombo(document.getElementsByName("strDualDiseaseCode")[0]);
	}

}

function showHideICDDiseaseCode(disChk)
{
    var divDisease=document.getElementById("DIVIcdDiseaseCode");
	if(disChk.checked)
	divDisease.style.display='block';		
	else
	divDisease.style.display='none';
}

function showHideDualICDDisease(dualDisChk)
{

	var divDualDisease=document.getElementById("DIVDualIcdDiseaseCode");
	if(dualDisChk.checked)
	divDualDisease.style.display='block';		
	else
	divDualDisease.style.display='none';

}




function validateSave()
{
	if(document.getElementsByName("diagnosticTerm")[0].value=="")
    {
		alert("Please enter the Diagnostic Term");
        document.forms[0].diagnosticTerm.focus();
        return false;
    }
    if(document.forms[0].strDiseaseCodeChk.checked==true)
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
      else
       {
       		doEmptyCombo(document.getElementsByName("icdGroupCode")[0])	
       		doEmptyCombo(document.getElementsByName("icdSubgroupCode")[0]);
		    doEmptyCombo(document.getElementsByName("diseaseCode")[0]);
       
       }
       
       if(document.forms[0].strDualDiseaseCodeChk.checked==true)
       {
  		
	  		   if(document.getElementsByName("strDualGroupCode")[0].value=="-1")
				{
				
					alert("Please Select Dual Icd Group");
					document.getElementsByName("strDualGroupCode")[0].focus();
					return false;
				}
				if(document.getElementsByName("strDualSubGroupCode")[0].value=="-1")
				{
				
					alert("Please Select Dual Icd Sub Group");
					document.getElementsByName("strDualSubGroupCode")[0].focus();
					return false;
				}
				if(document.getElementsByName("strDualDiseaseCode")[0].value=="-1")
				{
					alert("Please Select Dual Icd Disease");
					document.getElementsByName("strDualDiseaseCode")[0].focus();
					return false;
				}
       }
       else
       {
              
              doEmptyCombo(document.getElementsByName("strDualGroupCode")[0])	
              doEmptyCombo(document.getElementsByName("strDualSubGroupCode")[0]);
		      doEmptyCombo(document.getElementsByName("strDualDiseaseCode")[0]);
       
       }
       
       submitButton("SAVE");
}


function submitButton(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function clearTextFields()
{
	document.getElementsByName("diagnosticTerm")[0].value="";
	document.getElementsByName("hospitalSynonym")[0].value="";
	document.getElementsByName("icdGroupCode")[0].value="-1";
	document.getElementsByName("icdSubgroupCode")[0].value="-1";
	document.getElementsByName("diseaseCode")[0].value="-1";
	document.getElementsByName("strDualGroupCode")[0].value="-1";
	document.getElementsByName("strDualSubGroupCode")[0].value="-1";
	document.getElementsByName("strDualDiseaseCode")[0].value="-1";
	document.getElementsByName("strRemark")[0].value="";
	
	document.forms[0].strDiseaseCodeChk.checked=false;
	document.forms[0].strDualDiseaseCodeChk.checked=false;
	
	if(document.forms[0].strDiseaseCodeChk.checked==false)
	{
		document.getElementById("DIVIcdDiseaseCode").style.display="none";
	}
	if(document.forms[0].strDualDiseaseCodeChk.checked==false)
	{
		document.getElementById("DIVDualIcdDiseaseCode").style.display="none";
	}
}	

function validateModify()
{
    if(document.getElementsByName("diagnosticTerm")[0].value=="")
    {
		alert("Please enter the Diagnostic Term");
        document.forms[0].diagnosticTerm.focus();
        return false;
    }
    if(document.forms[0].strDiseaseCodeChk.checked==true)
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
      if(document.forms[0].strDualDiseaseCodeChk.checked==true)
       {
  		
  		if(document.getElementsByName("strDualGroupCode")[0].value=="-1")
			{
			
				alert("Please Select Dual Icd Group");
				document.getElementsByName("strDualGroupCode")[0].focus();
				return false;
			}
			if(document.getElementsByName("strDualSubGroupCode")[0].value=="-1")
			{
			
				alert("Please Select Dual Icd Sub Group");
				document.getElementsByName("strDualSubGroupCode")[0].focus();
				return false;
			}
			if(document.getElementsByName("strDualDiseaseCode")[0].value=="-1")
			{
				alert("Please Select Dual Icd Disease");
				document.getElementsByName("strDualDiseaseCode")[0].focus();
				return false;
			}
       }
       
             
	submitButton("MODIFYSAVE");	
     
}

function validateIndexTerm(e)
{

     var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
	//alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32)|| (key==47) || (key==45) || (key==95) || (key==44))
	   return true;
	

	// alphas and numeric
	else if ((("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,(-)").indexOf(keychar) > -1))
	   return true;
		   
	   else if ((("0123456789").indexOf(keychar) > -1))
	   return true;
	   
	   
	   
	else
	   return false;

}