function submitPage(mode)
{
	//alert(mode);
	document.getElementsByName('hmode')[0].value=mode;
	//document.forms[0].hmode.value=mode;
	//alert("hmode:::::"+document.getElementsByName("hmode")[0].value);
	document.forms[0].submit();
}

function validateFinalSubmit(){
    // alert(document.getElementsByName("testType")[0].value);
	if(document.getElementsByName("bookmarkCode")[0].value=="-1")
	{
		alert("Enter BookMark Name   ");
		document.getElementsByName("sampleCode")[0].focus();
		return false;
	}
	
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
	
}

 function validateOpinionAdd()
{
	var valid=true;
	if(document.getElementsByName("bookmarkCode")[0].value=="-1")
	{
		alert("Please Select BookMark Name");
		document.getElementsByName("bookmarkCode")[0].focus();
		valid=false;
		return valid;	
	}
	if(document.getElementsByName("bookarkType")[0].checked)
	{
		if(document.getElementsByName("userId")[0].value=="-1")
		{
			alert("Please Select User Name");
			document.getElementsByName("userId")[0].focus();
			valid=false;
			return valid;
		}
	}
	if(document.getElementsByName("bookarkType")[1].checked)
	{
		if(document.getElementsByName("deptCode")[0].value=="-1")
		{
			alert("Please Select Department Name");
			document.getElementsByName("deptCode")[0].focus();
			valid=false;
			return valid;
		}
		if(document.getElementsByName("deptUnitCode")[0].value=="-1")
		{
			alert("Please Select Department Unit Name");
			document.getElementsByName("deptUnitCode")[0].focus();
			valid=false;
			return valid;
		}
	}
	if(document.getElementsByName("labCode")[0].value=="-1")
	{
		alert("Please Select Lab Name");
		document.getElementsByName("labCode")[0].focus();
		valid=false;
		return valid;
	}
	if(document.getElementsByName("isTestGroup")[0].checked)
	{
		if(document.getElementsByName("testCode")[0].value=="-1")
		{
			alert("Please Select Test Name");
			document.getElementsByName("testCode")[0].focus();
			valid=false;
			return valid;
		}	
	}
	if(document.getElementsByName("isTestGroup")[1]!=undefined )
	{
		if( document.getElementsByName("isTestGroup")[1].checked)
		if(document.getElementsByName("groupCode")[0].value=="-1")
		{
			alert("Please Select Test Group Name");
			document.getElementsByName("groupCode")[0].focus();
			valid=false;
			return valid;
		}	
	}
	return valid;
} 

/*function deleteOpinionRow(obj1,obj2)
{
	document.getElementsByName("hiddenLabCode")[0].value=obj1;
	document.getElementsByName("hiddenLabName")[0].value=obj2;
	submitForm('DELETEOPINION')
}*/

 function deleteOpinionRow(obj1,obj2)
 {
 	document.getElementsByName("hiddenLabCode")[0].value=obj1;
 	document.getElementsByName("hiddenTestCode")[0].value=obj2;
 	//alert(document.getElementsByName("labCode")[0].value);
 	submitForm('DELETEOPINION')
 }
 
 function clearForm()
{
	document.getElementsByName('bookmarkCode')[0].value="-1";
	document.getElementsByName('bookarkType')[0].checked=false;
	document.getElementsByName('bookarkType')[1].checked=false; 
	document.getElementById("div1").style.display="none";
	document.getElementById("div2").style.display="none";
	document.getElementById("div3").style.display="none";
	document.getElementsByName('labCode')[0].value="-1";
	document.getElementsByName('isTestGroup')[0].checked=true;
	document.getElementsByName('isTestGroup')[1].checked=false;
	document.getElementById("div4").style.display=""; 
	document.getElementById("div5").style.display="none";
	document.getElementsByName('testCode')[0].value="-1";
	document.getElementsByName('groupCode')[0].value="-1";	
}
