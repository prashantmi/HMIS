// Parameter Master Javascript Functions ****************************************************

function validate(mode)
{
	
	if(document.getElementsByName('paraName')[0].value=="")
	{
		alert("Enter the Parameter Name ...");
		document.getElementsByName('paraName')[0].focus();
		return false;
	}
	if(document.getElementsByName('paraBound')[0].value=="-1")
	{
		alert("Select Parameter Bound ...");
		document.getElementsByName('parBound')[0].focus();
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
			submitForm(mode);
		}
}
function clearText(){
	document.getElementsByName('paraName')[0].value="";
	document.getElementsByName('paraBound')[0].value="-1";
	document.getElementsByName('paraType')[0].value="-1"
	document.getElementsByName('isActive')[0].value="-1";
}
