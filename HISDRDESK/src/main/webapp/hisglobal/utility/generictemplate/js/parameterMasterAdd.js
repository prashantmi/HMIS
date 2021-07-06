

function load1(elmtId,semantictag)
{

	
	//alert("inside load");
	//alert(elmtId+','+semantictag);
	if(elmtId=="1")
		{
		var callbck_index =function(ret_OUT){setValue(ret_OUT);};
		}
	
	
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

function showSnomed()
{
	
	if(document.getElementsByName("isSnomedConcept")[0].checked)
		{
		document.getElementById("divSnomed").style.display="";
		document.getElementsByName("isSnomedFlagValue")[0].value="<%=GenericTemplateConfig.SNOMED_CT_YES %>";
		}
	if(document.getElementsByName("isSnomedConcept")[1].checked)
			{
		
		document.getElementById("divSnomed").style.display="none";
		document.getElementsByName("isSnomedFlagValue")[0].value="<%=GenericTemplateConfig.SNOMED_CT_NO %>";
			
		}
		
	
}


function validate(mode)
{
	//alert("hi");
	
	if(document.getElementsByName('paraName')[0].value=="")
	{
		alert("Enter the Parameter Name ...");
		document.getElementsByName('paraName')[0].focus();
		return false;
	}
	if(document.getElementsByName('paraBound')[0].value=="-1")
	{
		alert("Select Parameter Bound ...");
		document.getElementsByName('paraBound')[0].focus();
		return false;
	}
	if(document.getElementsByName('paraType')[0].value=="-1")
	{
		alert("Select Parameter Type ...");
		document.getElementsByName('paraType')[0].focus();
		return false;
	}
	if(document.getElementsByName('isActive')[0].value=="-1")
	{
		alert("Select is Active ...");
		document.getElementsByName('isActive')[0].focus();
		return false;
	}
	
	else
		{
			if(document.getElementsByName("hmode")[0].value =="ADD")
			{
			if(document.getElementsByName("isSnomedFlagValue")[0].value=="<%=GenericTemplateConfig.SNOMED_CT_YES %>")
			{
			if(document.getElementsByName('prefferedTerm')[0].value=="" || document.getElementsByName('conceptId')[0].value=="" )
			{
				alert("Select Snomed");
				document.getElementsByName('txt-snomed-ct-search_1')[0].focus();
				return false;
			}
			}
			}
			
			if(document.getElementsByName("hmode")[0].value =="MODIFY")
			{
				/*if(document.getElementsByName('prefferedTerm')[0].value != document.getElementsByName('paraName')[0].value)
					{
					document.getElementsByName('prefferedTerm')[0].value="";
					document.getElementsByName('conceptId')[0].value="";
					
					}*/
				if(document.getElementsByName('txt-snomed-ct-search_1')[0].value=="")			
			     {
				 document.getElementsByName('prefferedTerm')[0].value="";
			     document.getElementsByName('conceptId')[0].value="";
			      
			     }
			
			     
		    }
			submitForm21(mode);
		}
	
}
function clearText(){
	document.getElementsByName('paraName')[0].value="";
	document.getElementsByName('paraBound')[0].value="-1";
	document.getElementsByName('paraType')[0].value="-1";
	//document.getElementsByName('isActive')[0].value="-1";
	if(document.getElementsByName("hmode")[0].value =="ADD")
	{
	document.getElementsByName('prefferedTerm')[0].value="";
	document.getElementsByName('txt-snomed-ct-search_1')[0].value="";
	document.getElementsByName('conceptId')[0].value="";
	}
	
}


//Parameter Master Javascript Functions ****************************************************

var callbck_index =function(ret_OUT){setValue(ret_OUT);};

var semantictag_IN;
var acceptability_IN;
var state_IN ;
var returnlimit_IN;
var retterm_value ={};


function setValue(selectedSNOMEDTerm)
{
	//alert(selectedSNOMEDTerm);
	//alert(selectedSNOMEDTerm.split(","));
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	/*var arr=selectedSNOMEDTerm.split(",");
	var str=arr[0];
	var str1=arr[1];
	*///alert(str[0]); alert(str1);
		var str=selectedSNOMEDTerm.term;
		var str1=selectedSNOMEDTerm.conceptId;

	document.getElementsByName("prefferedTerm")[0].value=str;
	document.getElementsByName("conceptId")[0].value=str1;
	document.getElementsByName("paraName")[0].value=str;
		}
	else
		{
		document.getElementsByName("prefferedTerm")[0].value="";
		document.getElementsByName("conceptId")[0].value="";
		}
}




window.onload = function()
{
	if(document.getElementsByName("hmode")[0].value =="ADD")
	{
	document.getElementsByName("isSnomedConcept")[0].checked=true;
	document.getElementsByName("isSnomedFlagValue")[0].value="<%=GenericTemplateConfig.SNOMED_CT_YES %>";
	}
	load1('1','');
	
	if(document.getElementsByName("hmode")[0].value =="MODIFY")
	{
		document.getElementsByName("txt-snomed-ct-search_1")[0].value=document.getElementsByName("prefferedTerm")[0].value;
	}
	
}

