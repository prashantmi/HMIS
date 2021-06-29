function onLoadCall() // Add
{
	if(document.getElementsByName("strRefModifier1")[0])
		setStrIndexTermPlusModifier(document.getElementsByName("strRefModifier1")[0]);
	else
		setStrIndexTerm(document.getElementsByName("refIndexCode")[0]);
	
}

function doEmptyCombo(cbo)
{
	if(cbo)
	{
		cbo.innerHTML="";
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		cbo.appendChild(op);
	}
}

function enableIndexValues()
{
	if(document.getElementsByName("indexCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETINDEXVALUES";
		document.forms[0].submit();
	}
	else 
	{
		document.getElementsByName("strCheck")[0].value="0";
		document.getElementsByName("hmode")[0].value="SAME";
		document.getElementsByName("seeTermsFlag")[0].value="0";
		document.forms[0].submit();
	}
}

function enableRefIndexValues()
{
	if(document.getElementsByName("refIndexCode")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETREFMODIFIER";
		document.forms[0].submit();
	}
	else
	{
		document.getElementsByName("strCheckSee")[0].value="0";
		document.getElementsByName("hmode")[0].value="SAME";
		document.forms[0].submit();
	}	
}

function enableModifier(obj,level)
{
	  if(obj.value!=-1)
	  {	  	
	  	switch(level)
	  	{
			case 1:
			document.getElementsByName("hmode")[0].value="SETMODIFIERTWO";
			break;
			
			case 2:
		 	document.getElementsByName("hmode")[0].value="SETMODIFIERTHREE";
		 	break;
		 		
			case 3:
			document.getElementsByName("hmode")[0].value="SETMODIFIERFOUR";
			break;
			
			case 4:
			document.getElementsByName("hmode")[0].value="SETMODIFIERFIVE";
			break;
			
			case 5:
			document.getElementsByName("hmode")[0].value="SETMODIFIERSIX";
			break;
			
			case 6:
			document.getElementsByName("hmode")[0].value="SETMODIFIERSEVEN";
			break;
			
			case 7:
			document.getElementsByName("hmode")[0].value="SETMODIFIEREIGHT";
			break;
			
			case 8:
			document.getElementsByName("hmode")[0].value="SETMODIFIERNINE";
			break;	  	
	  	}	    
	   document.forms[0].submit();
	  }	
	else
	{
	  	switch(level)
	  	{
			case 1:
			document.getElementsByName("hmode")[0].value="SETINDEXVALUES";
			break;
			
			case 2:
		 	document.getElementsByName("hmode")[0].value="SETMODIFIERTWO";
		 	break;
		 		
			case 3:
			document.getElementsByName("hmode")[0].value="SETMODIFIERTHREE";
			break;
			
			case 4:
			document.getElementsByName("hmode")[0].value="SETMODIFIERFOUR";
			break;
			
			case 5:
			document.getElementsByName("hmode")[0].value="SETMODIFIERFIVE";
			break;
			
			case 6:
			document.getElementsByName("hmode")[0].value="SETMODIFIERSIX";
			break;
			
			case 7:
			document.getElementsByName("hmode")[0].value="SETMODIFIERSEVEN";
			break;
			
			case 8:
			document.getElementsByName("hmode")[0].value="SETMODIFIEREIGHT";
			break;	  	
	  	}	    
		document.getElementsByName("strCheck")[0].value=level-1;
		document.forms[0].submit();
	}	
}


function enableSeeTermsForModNine()
{
	if(document.getElementsByName("strModifier9")[0].value!="-1")
	{
		document.getElementsByName("hmode")[0].value="SETSEETERMS";
		document.forms[0].submit();
	}
	else
	{
		document.getElementsByName("hmode")[0].value="SETMODIFIERNINE";
		document.getElementsByName("strCheck")[0].value=8;
		document.forms[0].submit();
	}
}


function validate(mode)
{
   if(document.getElementsByName("indexCode")[0].value=="-1")
	{
		alert("Please Select the Index Term");
		document.getElementsByName("indexCode")[0].focus();
		return false;
	}		
	else
	{
		document.getElementsByName("hmode")[0].value = mode;
		document.forms[0].submit();
	}	
	
	//return true;	
}


function setValue(obj)
{  
    if(obj.value!=-1)
    {
       var strIndex=document.getElementsByName("strIndexTerm")[0];
       strIndex.value=obj.options[obj.selectedIndex].text;
       return true;
	}
	return false;
}

function setStrIndexTerm(obj)
{  
	var strIndexTermPlus=document.getElementsByName("strIndexTermPlus")[0];
	if(obj.value!=-1)
		strIndexTermPlus.value=obj.options[obj.selectedIndex].text;
	else
		strIndexTermPlus.value="";
	
	document.getElementById("idRefSeeTerm").innerHTML = strIndexTermPlus.value;
}

function setStrIndexTermPlusModifier(objMod)
{
	var strIndexTermPlus=document.getElementsByName("strIndexTermPlus")[0];
	var refIndex=document.getElementsByName("refIndexCode")[0];
	
	if(objMod.value!=-1)
		strIndexTermPlus.value = refIndex.options[refIndex.selectedIndex].text + "," + objMod.options[objMod.selectedIndex].text;
	else
		strIndexTermPlus.value = refIndex.options[refIndex.selectedIndex].text;

	document.getElementById("idRefSeeTerm").innerHTML = strIndexTermPlus.value;
}

function setModifierValue(obj,level)
{
	    if(obj.value!=-1 && level==1)
	    {
		     var strModOne=document.getElementsByName("strModTerm1")[0];
		     strModOne.value=obj.options[obj.selectedIndex].text;
			 return true;  
		}
	    if(obj.value!=-1 && level==2)
		{
			    var strModTwo=document.getElementsByName("strModTerm2")[0];
			    strModTwo.value=obj.options[obj.selectedIndex].text;
			    return true;
		}
	    if(obj.value!=-1 && level==3)
		{
			    var strModThree= document.getElementsByName("strModTerm3")[0];
			    strModThree.value=obj.options[obj.selectedIndex].text;
			    return true;
		}
	    if(obj.value!=-1 && level==4)
		{
			    var strModFour=document.getElementsByName("strModTerm4")[0];
			    strModFour.value=obj.options[obj.selectedIndex].text;
			    return true;
		}
	    if(obj.value!=-1 && level==5)
		{
			    var strModFive=document.getElementsByName("strModTerm5")[0];
			    strModFive.value=obj.options[obj.selectedIndex].text;
			    return true;
			    
		}
	    if(obj.value!=-1 && level==6)
		{
			    var strModSix=document.getElementsByName("strModTerm6")[0];
			    strModSix.value=obj.options[obj.selectedIndex].text;
			    return true;
			    
		}
	    if(obj.value!=-1 && level==7)
		{
			    var strModSeven=document.getElementsByName("strModTerm7")[0];
			    strModSeven.value=obj.options[obj.selectedIndex].text;
				return true;			   
		}
	    if(obj.value!=-1 && level==8)
		{
			    var strModEight=document.getElementsByName("strModTerm8")[0];
			    strModEight.value=obj.options[obj.selectedIndex].text;
			    return true;
		}
        if(obj.value!=-1 && level==9)
		{
			    var strModNine=document.getElementsByName("strModTerm9")[0];
			    strModNine.value=obj.options[obj.selectedIndex].text;
			    return true;
		}
		return false;
}


function validateAddModify(mode)
{
	if (!validateForm()) 
		return;
	else
	{
		document.getElementsByName("hmode")[0].value = mode;
		document.forms[0].submit();
	}		
}

function validateForm()
{
	if( (document.getElementsByName("seeTermRadio")[0].checked == "" ) && (document.getElementsByName("seeTermRadio")[1].checked == "" ) )
		{
		
			alert("Please Select See/See Also  ");
			document.getElementsByName("seeTermRadio")[0].focus();
			return false;
		}

		if(document.getElementsByName("refIndexCode")[0].value=="-1")
		{
		
			alert("Please Select Referenced Index Term");
			document.getElementsByName("refIndexCode")[0].focus();
			return false;
		}
		//
		
		if(document.getElementsByName("strCheck")[0].value==0)
		{
			if(document.getElementsByName("indexCode")[0].value == document.getElementsByName("refIndexCode")[0].options[document.getElementsByName("refIndexCode")[0].selectedIndex].value)
				{
				alert("IndexCode and Reference Index Code cannot be same.")
				return false;
				}
		}	
		
	return true;	
}

function setSeeTermRadioValue()
{
	document.getElementsByName("seeTermRadio")[0].value == "0"; 
	document.getElementsByName("hmode")[0].value="MODIFY";
	document.forms[0].submit();
	
}
function setSeeAlsoTermRadioValue()
{	
	document.getElementsByName("seeTermRadio")[0].value == "1";
	document.getElementsByName("hmode")[0].value="MODIFY";
	document.forms[0].submit();
}