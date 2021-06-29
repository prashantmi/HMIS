
function callComboRptTemplate(form1,mode)
{
	
	var cmbVal = "";

	
	with(form1)
	{
	
		if(combo[0].value == "0" )
		{ 
				alert("Please Select Report Type");
			
						
			document.forms[0].combo[0].focus();
			return false;
		}
		else
		{
			cmbVal = combo[0].options[combo[0].selectedIndex].text;
			 
			comboValue.value = cmbVal;
			 
			 add(mode);
		
		}	
	}
}
   

 