
function submitPage(mode)
{
	
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}
function finalSubmit(mode)
{
	//alert("finalSubmit mode"+mode);
	//alert("validateFinalSubmit "+validateFinalSubmit());
	if (!validateFinalSubmit());
	return submitPage(mode);
	
} 


