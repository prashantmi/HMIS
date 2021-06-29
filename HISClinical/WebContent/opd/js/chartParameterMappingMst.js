


function getPara(obj)
{
	if(obj.value!="-1")
		submitPage("GETPARA");
}

function clearForm()
{
	moveAll(document.getElementsByName("selectedPara")[0],document.getElementsByName("para")[0]);
	moveAll(document.getElementsByName("arrSelectedInvPara")[0],document.getElementsByName("arrInvPara")[0]);
	moveAll(document.getElementsByName("arrSelectedInOutPara")[0],document.getElementsByName("arrInOutPara")[0]);
}

function finalSubmit(mode)
{
	if(validate())
		submitPage2(mode);
}

function validate()
{
	//alert("validating...");
	if(document.getElementsByName("chartId")[0].value=="-1")
	{
	 	alert("Please select Chart");
	 	document.forms[0].chartId.focus();
	 	return false;
	}
	//alert(document.getElementsByName("selectedPara")[0].value);
	if(document.getElementsByName('selectedPara')[0].length==0 &&
	document.getElementsByName('arrSelectedInvPara')[0].length==0 &&
	document.getElementsByName('arrSelectedInOutPara')[0].length==0)
	{
		alert("Please map at least one Parameter!");
	 	document.forms[0].selectedPara.focus();
	 	return false;
	}
	
	var totalMapped = document.getElementsByName('selectedPara')[0].length + document.getElementsByName('arrSelectedInvPara')[0].length 
		+ document.getElementsByName('arrSelectedInOutPara')[0].length;
	if(totalMapped>50)
	{
		alert("Please Ensure Mapped Parameters Count should not exceed 50!");
	 	document.forms[0].selectedPara.focus();
	 	return false;
	}

	return true;
}

function submitPage2(mode)
{
	MoveToSelected();
	submitForm21(mode);
}

//Added by 
/*submitPageOnValidate(mode)
{
	MoveToSelected();
	submitForm21(mode);
}*/

function submitForm21(mode)
{
    
  //  alert("submitform...."+mode);
   //  document.getElementsByName("hmode")[0].value=
   //  alert(document.getElementsByName("hmode")[0].value);
     document.getElementsByName("hmode")[0].value=mode;
    // doHomeWork();  
  //   alert("submit form action is"+document.forms[0].action);  
     //alert("hmode "+document.getElementsByName("hmode")[0].value);  
    
	 document.forms[0].submit();
	 
}



function MoveToSelected()
{
	// Select All para in Selected
	if(document.forms[0].selectedPara)
		for(var i=0;i<document.forms[0].selectedPara.length;i++)
			document.forms[0].selectedPara.options[i].selected=true;
	
	if(document.forms[0].arrSelectedInvPara)
		for(var i=0;i<document.forms[0].arrSelectedInvPara.length;i++)
			document.forms[0].arrSelectedInvPara.options[i].selected=true;

	if(document.forms[0].arrSelectedInOutPara)
		for(var i=0;i<document.forms[0].arrSelectedInOutPara.length;i++)
			document.forms[0].arrSelectedInOutPara.options[i].selected=true;

	// Unselect Remaining 
	if(document.forms[0].para)
		for(var i=0;i<document.forms[0].para.options.length;i++)
			document.forms[0].para.options[i].selected=false;

	if(document.forms[0].arrInvPara)
		for(var i=0;i<document.forms[0].arrInvPara.options.length;i++)
			document.forms[0].arrInvPara.options[i].selected=false;

	if(document.forms[0].arrInOutPara)
		for(var i=0;i<document.forms[0].arrInOutPara.options.length;i++)
			document.forms[0].arrInOutPara.options[i].selected=false;
}
