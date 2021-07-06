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
	
	if(document.getElementsByName("chkIsDefault")[0].checked==true)
			document.getElementsByName("isDefault")[0].value = 1;
	else
	{
		document.getElementsByName("isDefault")[0].value = 0;
		document.getElementsByName("defChk")[0].value=0;
	}
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
	
	if(document.getElementsByName("chkIsDefault")[0].checked==true)
		document.getElementsByName("isDefault")[0].value = 1;
	else
	{
		document.getElementsByName("isDefault")[0].value = 0;
		document.getElementsByName("defChk")[0].value=0;
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
	if(document.getElementsByName("isDefault")[0].value == 1)
	{
		document.getElementsByName("chkIsDefault")[0].disabled =true;
	}
	else if(document.getElementsByName("isDefault")[0].value==0)
		document.getElementsByName("chkIsDefault")[0].disabled=false;
	
	if(document.getElementsByName("defChk")[0].value == 1)
	{
		document.getElementsByName("chkIsDefault")[0].checked=true;
		document.getElementsByName("chkIsDefault")[0].disabled=false;
	}
	else if(document.getElementsByName("defChk")[0].value==0)
		document.getElementsByName("chkIsDefault")[0].checked=false;
	
	
	
}

function load1(elmtId,semantictag)
{

	
	//alert("inside load");
	//alert(elmtId+','+semantictag);
	
		
		var callbck_index =function(ret_OUT){setValue1(ret_OUT);};
		
	
	
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	if(elmtId==null || elmtId==undefined)
		{
		elmtId="1"; semantictag="";
	
		}
	//elmtId="2";
	//alert(elmtId);
	
	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null',elmtId,callbck_index);

	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	
}


function setValue1(selectedSNOMEDTerm)
{
	var target="1";
	if(document.getElementsByName('targetId')[0].value!=null)
	 target=document.getElementsByName('targetId')[0].value;
//	alert(target);
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
//	var arr=selectedSNOMEDTerm.split(",");
//	var str=arr[0];
//	var str1=arr[1];
	
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	//alert(str[0]); alert(str1);
	
	//document.getElementsByName("snomedCTDiagnosisName")[0].value=str;
	//document.getElementsByName("snomedCTDiagnosisCode")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_"+target)[0].value=str;
	document.getElementById("optionText#"+target).value =str;
	document.getElementById("optionValue#"+target).value =str1;
//	document.getElementById("somectIddata").innerHTML =str1;
	//alert(document.getElementById("optionValue#"+target).value);
	
		}
	else
		{
	//	document.getElementsByName("snomedCTDiagnosisName")[0].value="";
		//document.getElementsByName("snomedCTDiagnosisCode")[0].value="";
		 //document.getElementById("somectIddata").innerHTML ="";
		}
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
}

