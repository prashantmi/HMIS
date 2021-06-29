window.onload = function() 
{
	//if(document.getElementsByName("testConductedFrom")[1].checked)
	//{
	//	document.getElementById("extLabInfoId").style.display="block";
	//}
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateAdd()
{
	var valid=true;
	
	//if(document.getElementsByName("recordDate")[0].value=="")
	//{
	//	alert("Please Enter The Record Date");
	//	document.getElementsByName("recordDate")[0].focus();
	//	valid=false;
	//}
	//else if(checkRecordDate()<0)
	//{
	//	alert("Record Date Cannot be Greater Than Today");
	//	document.getElementsByName("recordDate")[0].focus();
	//	valid=false;
	//}
	//else if(!checkRecordTime())
	//{
	//	valid=false;
	//}
	//else
	
	
	if(document.getElementsByName("paraId")[0].value == "")
	{
		alert("Please Enter The Symptoms");
		document.getElementsByName("paraName")[0].focus();
		valid=false;
	}
	//else if(document.getElementsByName("paraValue")[0].value == "")
	//{
	//	alert("Please Enter The Symptoms Value");
	//	document.getElementsByName("paraValue")[0].focus();
	//	valid=false;
	//}
	else
	{
		valid=checkValidateAdd();
	}
	return valid;
}

function deleteRow(obj)
{
	document.getElementsByName("deleteIndex")[0].value=obj;
	submitForm('DELETEROW')
}

function clearForm()
{
	//document.getElementsByName("recordDate")[0].value=document.getElementsByName("sysDate")[0].value;
	//document.getElementsByName("recordTimeHr")[0].value=document.getElementsByName("hiddenTimeHr")[0].value;
	//document.getElementsByName("recordTimeMin")[0].value=document.getElementsByName("hiddenTimeMin")[0].value;
	document.getElementsByName("paraId")[0].value="";
	document.getElementsByName("paraName")[0].value="";
	document.getElementsByName("paraValue")[0].value="";
	document.getElementsByName("remarks")[0].value="";
	//document.getElementsByName("extLabName")[0].value="";
	//document.getElementsByName("extLabContactNo")[0].value="";
	//document.getElementsByName("extLabAdd")[0].value="";
	
}

function setCorrParaId(elemTarget)
{
	var rowIndex = findIndex(elemTarget);

	var elemParaId = document.getElementsByName("paraId")[rowIndex];
	var elemParaName = document.getElementsByName("paraName")[rowIndex];

	// Set Data
	if(elemTarget.SELECTED_VALUE!=null)
		elemParaId.value = elemTarget.SELECTED_VALUE;
	else
	{
		elemParaId.value = "";
		elemParaName.value = "";
	}
}


function checkRecordDate()
{
	var valid=true;
	var days=0;
	var a=document.getElementsByName("recordDate")[0].value;
    var aArray=a.split("-");
      
    var aday=aArray[0];
    var amonth=aArray[1];
    var ayear=aArray[2];
    var adate=new Date(amonth +" "+ aday+" "+ayear);
      
    var b=document.getElementsByName("sysDate")[0].value; 
    var bArray=b.split("-");
     
    var bday=bArray[0];
    var bmonth=bArray[1];
    var byear=bArray[2];
    var bdate=new Date(bmonth +" "+ bday+" "+byear);
      
    
    days=((bdate-adate)/86400000);
   
    return days;
}

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function checkRecordTime()
{
	var days=checkRecordDate();
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var minute=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var valid=true;
	
	if(days==0)
	{
		if(document.getElementsByName("recordTimeHr")[0].value=="")
		{
			alert("Please Enter the Record Hour");
			document.getElementsByName("recordTimeHr")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("recordTimeHr")[0].value)) > hour)
		{
			alert("Hour Cannot be Greater than "+ hour);
			document.getElementsByName("recordTimeHr")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("recordTimeMin")[0].value=="")
		{
			alert("Please Enter the Record Minute");
			document.getElementsByName("recordTimeMin")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("recordTimeHr")[0].value)) >= hour && parseInt(trimNum(document.getElementsByName("recordTimeMin")[0].value)) > minute)
		{
			alert("Minute Cannot be Greater than "+minute);
			document.getElementsByName("recordTimeMin")[0].focus();
			valid=false;
		}
		else if(parseInt(trimNum(document.getElementsByName("recordTimeMin")[0].value)) > parseInt("59"))
		{
			alert("Minute Cannot be Greater than 59");
			document.getElementsByName("recordTimeMin")[0].focus();
			valid=false;
		}
	}
	else
	{
		if(days>0)
		{
			if(document.getElementsByName("recordTimeHr")[0].value=="")
			{
				alert("Please Enter the Record Hour");
				document.getElementsByName("recordTimeHr")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("recordTimeHr")[0].value)) > parseInt("23"))
			{
				alert("Hour Cannot be Greater than 23");
				document.getElementsByName("recordTimeHr")[0].focus();
				valid=false;
			}
			else if(document.getElementsByName("recordTimeMin")[0].value=="")
			{
				alert("Please Enter the Record Minute");
				document.getElementsByName("recordTimeMin")[0].focus();
				valid=false;
			}
			else if(parseInt(trimNum(document.getElementsByName("recordTimeMin")[0].value)) > parseInt("59"))
			{
				alert("Minute Cannot be Greater than 59");
				document.getElementsByName("recordTimeMin")[0].focus();
				valid=false;
			}
		}
		else
		{
			alert("Record Date Cannot be Greater Than Today");
			document.getElementsByName("recordDate")[0].focus();
			valid=false;
		}	
	}
	return valid;
}	

function showLabInfo()
{
	if(document.getElementsByName("testConductedFrom")[0].checked)
	{
		document.getElementById("extLabInfoId").style.display="none";
		document.getElementsByName("extLabName")[0].value="";
		document.getElementsByName("extLabContactNo")[0].value="";
		document.getElementsByName("extLabAdd")[0].value="";
	}
	else
	{
		document.getElementById("extLabInfoId").style.display="block";
	}
}

function validateExtLabAdd()
{
	var valid=true;
	//if(document.getElementsByName("testConductedFrom")[1].checked)
	//{
	//	if(document.getElementsByName("extLabName")[0].value=="")
	//	{
	//		alert("Please Enter The Laboratory Name.");
	//		document.getElementsByName("extLabName")[0].focus();
	//		valid=false;
	//	}
		/*else if(document.getElementsByName("extLabContactNo")[0].value=="")
		{
			alert("Please Enter The Laboratory Contact No.");
			document.getElementsByName("extLabContactNo")[0].focus();
			valid=false;
		}
		else if(document.getElementsByName("extLabAdd")[0].value=="")
		{
			alert("Please Enter The Laboratory Address");
			document.getElementsByName("extLabAdd")[0].focus();
			valid=false;
		}*/
	//}
	return valid;	
}


////////////////////////////////////////////////////////////////////////
var popupList =null;
var selectIndexVal = -1;
var elemSelText = null;
var selectedRowIndex = null;

function gettext(eve, obj)
{
	// 13 Enter, 27 Escape, 40 Down Arrow
	if(eve.keyCode!=13 && eve.keyCode!=27 && eve.keyCode!=40)
	{
		// Setting Selected Element
		selectIndexVal = -2;
		elemSelText=obj;
		selectedRowIndex=findIndex(elemSelText);
		if(obj.value!=" " && obj.value!="")
			sendDataForList(obj.value);
	}
	if(eve.keyCode==40)
		changeControl();
}
function findIndex(obj)
{
	var ctrlArr = document.getElementsByName(obj.name);
	var index=-1;
	for(var i=0;i<ctrlArr.length;i++)
	{
		if(ctrlArr[i]==obj)
		{
			index=i;
			break;
		}
	}
	return index;
}
function changeControl()
{
	var elemDropDown=document.getElementById("selid");	
	if(typeof elemDropDown == 'undefined') return;
	
	var innerValue=elemDropDown.options[0].value;
	if(innerValue=="-1")
	{
		document.getElementById("sid").style.display="none";
		popupList = null;
		selectIndexVal = -1;
		return;
	}
	selectIndexVal = -1;
	elemDropDown.focus();
}

function sendDataForList(searchText)
{	
	handleList(searchText);
	
}

function sendRequestForExternalParaList(text)
{
	var values = document.getElementsByName("externalParameterList")[0].options;
	var result="";
	var txtlen = text.length;
	for(var i=0; i<values.length; i++)
	{
		if(values[i].text.substr(0,txtlen).toLowerCase()==text.toLowerCase())
		{
			result+= values[i].value+"$"+values[i].text+"$";
		}
	}
	
	if(result.length>0) result = result.substr(0,result.length-1);
	return result;
}

function handleList(searchText)
{
	popupList=null;
	var list = sendRequestForExternalParaList(searchText);

	var elemDropDown=document.getElementById("selid");
	document.getElementById("sid").style.display="none";
	var o=elemSelText;
	var l=0,t=0;
	while(o.offsetParent)
	{
		l+=o.offsetLeft;
		t+=o.offsetTop;
		o=o.offsetParent;
	}
	document.getElementById("sid").style.left=l;
	document.getElementById("sid").style.top=t+elemSelText.offsetHeight;
	document.getElementById("sid").style.pixelWidth=elemSelText.style.pixelWidth;
			
	var optionValueTextArray=list.split('$');
	elemDropDown.innerHTML="";
	popupList = optionValueTextArray;
	if(optionValueTextArray!="")
	{
		document.getElementById("sid").style.display="block";
		for(i=0,j=1;i<optionValueTextArray.length && j<optionValueTextArray.length;i=i+2,j=j+2)
		{
			var op=document.createElement("option");
			op.value=optionValueTextArray[i];
			op.innerHTML=optionValueTextArray[j];
			elemDropDown.appendChild(op);
		}
		selectIndexVal = 0;
		elemDropDown.selectedIndex = selectIndexVal;
	}
	else
	{
		document.getElementById("sid").style.display="none";				
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		elemDropDown.appendChild(op);
		selectIndexVal = -2;
	}
}

function hideta(eve)
{
	// 9 Tab
	if(eve.keyCode==13 || eve.keyCode==9)
	{
		setValues();
		document.getElementById("sid").style.display="none";
		document.getElementById("selid").value="";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
	if(eve.keyCode==27)
	{
		setValues();
		document.getElementById("sid").style.display="none";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
}

function onChangeDrop()
{
	var elemDropDown = document.getElementById("selid");
	selectIndexVal = elemDropDown.selectedIndex;
	elemSelText.value=elemDropDown.options[selectIndexVal].text;
}
// OnClick & Double click of Drop Downs
function setClickedValue()
{
	setValues();
	document.getElementById("sid").style.display="none";
	document.getElementById("selid").value="";
	elemSelText.focus();
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}
function setValues()
{
	if(selectIndexVal!=null && selectIndexVal>=0 && selectedRowIndex!=null)
	{
		var elemDiagCode = document.getElementsByName("paraId")[selectedRowIndex];
		
		var elemDropDown = document.getElementById("selid");
		selectIndexVal = elemDropDown.selectedIndex;
		
		elemSelText.value=elemDropDown.options[selectIndexVal].text;
		elemDiagCode.value=elemDropDown.options[selectIndexVal].value;
	}
}
// Call On Blur of DropDown Sense TextBoxes
function callOnBlur()
{
	if(selectIndexVal==-1) 
	{
		selectIndexVal = document.getElementById("selid").selectedIndex;
		return;
	}
	var flag = false;
	var arr = popupList;
	if(popupList==null || typeof popupList == 'undefined' || popupList=="" || popupList.length<=0 || selectIndexVal<0)
		removeValues();
	else
		setValues();

	if(document.getElementById("sid") && document.getElementById("selid"))
	{
		document.getElementById("sid").style.display="none";
	}
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}

function removeValues()
{
	document.getElementsByName("paraId")[selectedRowIndex].value = "";
	
}

function checkValidateAdd()
{
	var valid=true;
	
	var addedLength=document.getElementsByName("addedRecordDate").length
	if(addedLength>0)
	{
		for(var i=0;i<addedLength;i++)
		{
			var addedStr=document.getElementsByName("addedRecordDate")[i].value+document.getElementsByName("addedRecordTime")[i].value+document.getElementsByName("addedParaId")[i].value;
			var currentStr=document.getElementsByName("recordDate")[0].value+document.getElementsByName("recordTimeHr")[0].value+":"+document.getElementsByName("recordTimeMin")[0].value+document.getElementsByName("paraId")[0].value;
			//alert(addedStr+"======"+currentStr);
			if(addedStr==currentStr)
			{
				alert(document.getElementsByName("paraId")[0].value+" is Already Added On "+document.getElementsByName("recordDate")[0].value+" "+document.getElementsByName("recordTimeHr")[0].value+":"+document.getElementsByName("recordTimeMin")[0].value);
				valid=false;
				break;
			}
		}
	}	
	
	var currentLength=document.getElementsByName("currentAddedRecordDate").length
	if(currentLength>0)
	{
		for(var i=0;i<currentLength;i++)
		{
			var addedStr=document.getElementsByName("currentAddedRecordDate")[i].value+document.getElementsByName("currentAddedRecordTime")[i].value+document.getElementsByName("currentAddedParaId")[i].value;
			var currentStr=document.getElementsByName("recordDate")[0].value+document.getElementsByName("recordTimeHr")[0].value+":"+document.getElementsByName("recordTimeMin")[0].value+document.getElementsByName("paraId")[0].value;
			if(addedStr==currentStr)
			{
				alert(document.getElementsByName("paraId")[0].value+" is Already Added On "+document.getElementsByName("recordDate")[0].value+" "+document.getElementsByName("recordTimeHr")[0].value+":"+document.getElementsByName("recordTimeMin")[0].value);
				valid=false;
				break;
			}
		}
	}	
	return valid;
} 





function setCorrParaID(elemTarget)
{
	var rowIndex = findIndex(elemTarget);

	var elemParaID = document.getElementsByName("paraId")[rowIndex];
	var elemParaName = document.getElementsByName("paraName")[rowIndex];

	// Set Data
	if(elemTarget.SELECTED_VALUE!=null)
		elemParaID.value = elemTarget.SELECTED_VALUE;
	else
	{
		elemParaID.value = "";
		elemParaName.value = "";
	}
}

