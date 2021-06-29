
function validate()
{

		var hisValidator = new HISValidator("applicationErrorLogDetailRptFB");


		hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
		hisValidator.addValidation("strToDate", "date","To Date is a mandatory field");
		hisValidator.addValidation("strToDate","dtltet="+document.forms[0].strCurrentDate.value,"Please Select To Date Less Than or Equal To Current Date");
		hisValidator.addValidation("strToDate","dtgtet="+document.forms[0].strFromDate.value,"Please Select To Date Greater Than Or Equal To From Date");
		
	
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	
	if(retVal)
	{
		document.forms[0].hmode.value = "SHOWRPT";
		
		if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
		{
			document.forms[0].target = "_self";
		}
		else
		{
			document.forms[0].target = "_blank";
		}
			document.forms[0].submit();
		}
		else
		{
			return false;
		}
	}

	function onLoadPage()
	{
		
	}
	
	function onClearPage()
	{
		document.forms[0].strFromDate.value = document.forms[0].strCurrentDate.value ;
		document.forms[0].strToDate.value = document.forms[0].strCurrentDate.value ;
	}

function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].target = "_self";
	document.forms[0].submit();
}
