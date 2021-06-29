	
function callselectedOpd()
{	
	//alert("in");
	var opdVal=document.getElementById("stremgRenewalOpdId").value;
	alert(opdVal);
	if (opdVal == "1")	
			{
			$('[name="strNoOfHrsOpd"]').removeAttribute("disabled");
			}
	
}
