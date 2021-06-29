function sameAsAddress()
{
	if(document.forms[0].strSameAsAdd.checked)
	{
		document.forms[0].strOccCity.value=document.forms[0].strCity.value;
	 	 for (var i=0; i<document.forms[0].strOccState.options.length; i++)
		 	 {
				if ( document.forms[0].strOccState.options[i].value == document.forms[0].strState.value )
      				{
       					document.forms[0].strOccState.options[i].selected = true;
					}
			}
	}
	else
	{
		document.forms[0].strOccCity.value="";
		document.forms[0].strOccState.value ='0';
	}
}
function isDependent()
{
		var temp="";
		if(document.getElementsByName("strOccIsDept").length>=1)
		{
			if(document.getElementsByName("strOccIsDept")[0].checked)//Yes
			{
				//document.getElementById("IsDependendID").style.display="";
				//document.getElementsByName("strOccEmpName")[0].value="";
				if(document.getElementsByName("strOccRelation")[0].disabled)
					document.getElementsByName("strOccRelation")[0].disabled=false;
				//document.getElementsByName("strOccRelation")[0].value="0";
			}
			else//No
			{
				//document.getElementById("IsDependendID").style.display="none";
				temp=document.getElementsByName("strHiddenPatDtl")[0].value.split('^');			
				//document.getElementsByName("strOccEmpName")[0].value=temp[0];
				document.getElementsByName("strOccRelation")[0].value="10";
				if(!document.getElementsByName("strOccRelation")[0].disabled)
					document.getElementsByName("strOccRelation")[0].disabled=true;
			}
		}
}
function isDependentNewBorn()
{
		var temp="";
		document.getElementsByName("strOccIsDept")[0].checked=true;
		//document.getElementById("IsDependendID").style.display="";
		temp=document.getElementsByName("strHiddenPatDtl")[0].value.split('^');			
		document.getElementsByName("strOccEmpName")[0].value=temp[0];
		document.getElementsByName("strOccRelation")[0].value="12";//Mother
		if(document.getElementsByName("strOccRelation")[0].disabled)
				document.getElementsByName("strOccRelation")[0].disabled=false;
}
