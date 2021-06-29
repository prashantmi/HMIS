window.onload = function() 
{
	//alert("ext inv loading...");
	checkForOnSelectDeskListItem(null);
	if(document.getElementsByName("testConductedFrom")[1].checked)
	{
		document.getElementById("extLabInfoId").style.display="block";
	}
}

function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function validateAdd()
{
	var valid=true;
	 
	   
	if(document.getElementsByName("recordDate")[0].value=="")
	{
		alert("Please Enter The Record Date");
		document.getElementsByName("recordDate")[0].focus();
		valid=false;
	}
	else if(selectedDate > now)
	{
		alert("Record Date Cannot be Greater Than Today");
		document.getElementsByName("recordDate")[0].focus();
		valid=false;
	}
	else if(!checkRecordTime())
	{
		valid=false;
	}
	else if(document.getElementsByName("paraId")[0].value == "")
	{
		alert("Please Enter The Parameter");
		document.getElementsByName("paraId")[0].focus();
		valid=false;
	}
	else if(document.getElementsByName("paraValue")[0].value == "")
	{
		alert("Please Enter The Parameter Value");
		document.getElementsByName("paraValue")[0].focus();
		valid=false;
	}
	else
	{
		valid=checkValidateAdd();
	}
	return valid;
}

function deleteRow(obj)
{
	document.getElementsByName("deleteIndex")[0].value=obj;
	submitForm21('DELETEROW')
}

function clearForm()
{
	document.getElementsByName("recordDate")[0].value=document.getElementsByName("sysDate")[0].value;
	document.getElementsByName("recordTimeHr")[0].value=document.getElementsByName("hiddenTimeHr")[0].value;
	document.getElementsByName("recordTimeMin")[0].value=document.getElementsByName("hiddenTimeMin")[0].value;
	document.getElementsByName("paraId")[0].value="";
	document.getElementsByName("paraValue")[0].value="";
	document.getElementsByName("extLabName")[0].value="";
	document.getElementsByName("extLabContactNo")[0].value="";
	document.getElementsByName("extLabAdd")[0].value="";
	
}
function checkRecordDate() {
	var flag=0;
	   var selectedText = document.getElementsByName("recordDate")[0].value;
	   var selectedDate = new Date(selectedText);
	   var now = new Date();
	   if (selectedDate > now) {
		   flag=1;
		   alert("Record Date Cannot be Greater Than Today");
		  // return false;
		  $('#recordDateId').val(setCurrentDate12());  
	   }
	   else if(selectedDate < now){
		   flag=2;
	   }
	   else
		   flag=0;
	   return flag;
	   
	 }
function setCurrentDate12()
{

	
			var today = new Date(); 
			 return today.getFullYear() + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + ('0' + today.getDate()).slice(-2); 		
}
/*function checkRecordDate()
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
}*/

function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function checkRecordTime()
{
	var flag=checkRecordDate();
	var hour=parseInt(trimNum(document.getElementsByName("hiddenTimeHr")[0].value));
	var minute=parseInt(trimNum(document.getElementsByName("hiddenTimeMin")[0].value));
	var valid=true;
	//alert(flag);
	if(flag==0)
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
		if(flag==2)
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
	if(document.getElementsByName("testConductedFrom")[1].checked)
	{
		if(document.getElementsByName("extLabName")[0].value=="")
		{
			alert("Please Enter The Laboratory Name.");
			document.getElementsByName("extLabName")[0].focus();
			valid=false;
		}
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
	}
	return valid;	
}


////////////////////////////////////////////////////////////////////////

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
		setValuesForParameterInExtInv();
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
		setValuesForParameterInExtInv();
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
	setValuesForParameterInExtInv();
	document.getElementById("sid").style.display="none";
	document.getElementById("selid").value="";
	elemSelText.focus();
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}
function setValuesForParameterInExtInv()
{
	if(selectIndexVal!=null && selectIndexVal>=0 && selectedRowIndex!=null)
	{
		var elemDiagCode = document.getElementsByName("paraId")[selectedRowIndex];
		//alert(elemDiagCode);
		
		var elemDropDown = document.getElementById("selid");
		selectIndexVal = elemDropDown.selectedIndex;
		
		elemSelText.value=elemDropDown.options[selectIndexVal].text;
		//alert(elemDropDown.options[selectIndexVal].value);
		document.getElementsByName("paraCode")[0].value = elemDropDown.options[selectIndexVal].value;
	}
}
// Call On Blur of DropDown Sense TextBoxes
function callOnBlurForExtInv()
{
	if(selectIndexVal==-1) 
	{
		selectIndexVal = document.getElementById("selid").selectedIndex;
		return;
	}
	var flag = false;
	var arr = popupList;
	if(popupList==null || typeof popupList == 'undefined' || popupList=="" || popupList.length<=0 || selectIndexVal<0)
		removeValues1();
	else
		setValuesForParameterInExtInv();

	if(document.getElementById("sid") && document.getElementById("selid"))
	{
		document.getElementById("sid").style.display="none";
	}
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}

function removeValues1()
{
	document.getElementsByName("paraId")[selectedRowIndex].value = "";
	
}

function checkValidateAdd()
{
	//alert("validate called");
	var valid=true;
	
	/*var addedLength=document.getElementsByName("addedRecordDate").length
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
	}	*/
	
	var addedLength=document.getElementsByName("currentAddedRecordDate").length
	if(addedLength>0)
	{
		for(var i=0;i<addedLength;i++)
		{
			var addedStr=document.getElementsByName("currentAddedTest")[i].value+document.getElementsByName("currentAddedParaId")[i].value;
			var currentStr=document.getElementsByName("testId")[0].value+document.getElementsByName("paraId")[0].value;
			//alert(addedStr+"======"+currentStr);
			if(addedStr==currentStr)
			{
				alert(document.getElementsByName("testId")[0].value+" is Already Added On "+document.getElementsByName("recordDate")[0].value+" "+document.getElementsByName("recordTimeHr")[0].value+":"+document.getElementsByName("recordTimeMin")[0].value);
				clearFirstRowForExtInv();
				valid=false;
				break;
			}
		}
	}	
	
	var currentLength=document.getElementsByName("selRecordDate").length
	if(currentLength>0)
	{
		for(var i=0;i<currentLength;i++)
		{
			var addedStr=document.getElementsByName("selTestName")[i].value+document.getElementsByName("selParaName")[i].value;
			var currentStr=document.getElementsByName("testId")[0].value+document.getElementsByName("paraId")[0].value;
			//alert(addedStr+"======"+currentStr);
			if(addedStr==currentStr)
			{
				alert(document.getElementsByName("testId")[0].value+" is Already Added On "+document.getElementsByName("recordDate")[0].value+" "+document.getElementsByName("recordTimeHr")[0].value+":"+document.getElementsByName("recordTimeMin")[0].value);
				clearFirstRowForExtInv();
				valid=false;
				break;
			}
		}
	}	
	return valid;
} 


//Added by Vasu on 05.August.2019

function gettextForTestName(eve, obj)
{
	// 13 Enter, 27 Escape, 40 Down Arrow
	if(eve.keyCode!=13 && eve.keyCode!=27 && eve.keyCode!=40)
	{
		// Setting Selected Element
		selectIndexVal = -2;
		elemSelText=obj;
		selectedRowIndex=findIndex(elemSelText);
		if(obj.value!=" " && obj.value!="")
			sendDataForListForTestName(obj.value);
	}
	if(eve.keyCode==40)
		changeControl();
}

function sendDataForListForTestName(searchText)
{	
	handleListForTestName(searchText);
	
}

function handleListForTestName(searchText)
{
	popupList=null;
	var list = sendRequestForExternalTestNameList(searchText);

	var elemDropDown=document.getElementById("selExtTestId");
	document.getElementById("extTestId").style.display="none";
	var o=elemSelText;
	var l=0,t=0;
	while(o.offsetParent)
	{
		l+=o.offsetLeft;
		t+=o.offsetTop;
		o=o.offsetParent;
	}
	document.getElementById("extTestId").style.left=l;
	document.getElementById("extTestId").style.top=t+elemSelText.offsetHeight;
	document.getElementById("extTestId").style.pixelWidth=elemSelText.style.pixelWidth;
			
	var optionValueTextArray=list.split('$');
	elemDropDown.innerHTML="";
	popupList = optionValueTextArray;
	if(optionValueTextArray!="")
	{
		document.getElementById("extTestId").style.display="block";
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
		document.getElementById("extTestId").style.display="none";				
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		elemDropDown.appendChild(op);
		selectIndexVal = -2;
	}
}



function sendRequestForExternalTestNameList(text)
{
	//$("input[name=TypeList]")
	var values = document.getElementsByName("externalTestList")[0].options;
	//var values=$("input[name=externalTestList]").val();
//alert(values);
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

function hidetaForExtTestName(eve)
{
	// 9 Tab
	if(eve.keyCode==13 || eve.keyCode==9)
	{
		setValuesForExtTest();
		document.getElementById("extTestId").style.display="none";
		document.getElementById("selExtTestId").value="";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
	if(eve.keyCode==27)
	{
		setValuesForExtTest();
		document.getElementById("extTestId").style.display="none";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
}

function onChangeDropForExtTest()
{
	var elemDropDown = document.getElementById("selExtTestId");
	selectIndexVal = elemDropDown.selectedIndex;
	elemSelText.value=elemDropDown.options[selectIndexVal].text;
}


function setClickedValueForExtTest()
{
	setValuesForExtTest();
	document.getElementById("extTestId").style.display="none";
	document.getElementById("selExtTestId").value="";
	elemSelText.focus();
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}
function setValuesForExtTest()
{
	if(selectIndexVal!=null && selectIndexVal>=0 && selectedRowIndex!=null)
	{
		var elemDiagCode = document.getElementsByName("testId")[selectedRowIndex];
		
		var elemDropDown = document.getElementById("selExtTestId");
		selectIndexVal = elemDropDown.selectedIndex;
		
		elemSelText.value=elemDropDown.options[selectIndexVal].text;
		document.getElementsByName("testCode")[0].value = elemDropDown.options[selectIndexVal].value;
	}
}

function callOnBlurForExtTest()
{
	if(selectIndexVal==-1) 
	{
		selectIndexVal = document.getElementById("selExtTestId").selectedIndex;
		return;
	}
	var flag = false;
	var arr = popupList;
	if(popupList==null || typeof popupList == 'undefined' || popupList=="" || popupList.length<=0 || selectIndexVal<0)
		removeValuesForExtTest();
	else
		setValuesForExtTest();

	if(document.getElementById("extTestId") && document.getElementById("selExtTestId"))
	{
		document.getElementById("extTestId").style.display="none";
	}
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}

function removeValuesForExtTest()
{
	document.getElementsByName("testId")[selectedRowIndex].value = "";
	
}

function addRowToTableExtInv()
{
	
	if(validateRowsForExtInv())
	AddRowToTableExtInvDtlTable();

	
}


function validateRowsForExtInv()
{
var valid=true;
var selectedText = document.getElementsByName("recordDate")[0].value;
var selectedDate = new Date(selectedText);
var now = new Date();
	if(document.getElementsByName("recordDate")[0].value=="")
	{
		alert("Please Enter The Record Date");
		document.getElementsByName("recordDate")[0].focus();
		valid=false;
	}
	else if(selectedDate > now)
	{
		alert("Record Date Cannot be Greater Than Today");
		document.getElementsByName("recordDate")[0].focus();
		valid=false;
	}
	else if(!checkRecordTime())
	{
		valid=false;
	}
	else if(document.getElementsByName("testId")[0].value == "")
	{
		alert("Please Enter The Test");
		document.getElementsByName("testId")[0].focus();
		valid=false;
	}
	/*else if(document.getElementsByName("paraId")[0].value == "")
	{
		alert("Please Enter The Parameter");
		document.getElementsByName("paraId")[0].focus();
		valid=false;
	}*/
	else if(document.getElementsByName("paraValue")[0].value == "")
	{
		alert("Please Enter The Parameter Value");
		document.getElementsByName("paraValue")[0].focus();
		valid=false;
	}
	else
	{
		valid=checkValidateAdd();
	}
	return valid;

}
function AddRowToTableExtInvDtlTable()
{
	//alert("add");	
	var recordDate = document.getElementsByName("recordDate")[0].value;
	var dateRecoredDate=new Date(recordDate);
	var month_names =["Jan","Feb","Mar",
                      "Apr","May","Jun",
                      "Jul","Aug","Sep",
                      "Oct","Nov","Dec"];
	 var day = dateRecoredDate.getDate();
	    var month_index = dateRecoredDate.getMonth();
	    var year = dateRecoredDate.getFullYear();
	    
	    var selectedDate= "" + day + "-" + month_names[month_index] + "-" + year;
	//alert(recordDate);
	var recordTimeHr = document.getElementsByName("recordTimeHr")[0].value;
	var recordTimeMin = document.getElementsByName("recordTimeMin")[0].value;
	var paraName = document.getElementsByName("paraId")[0].value;
	//alert(paraName);
	var paraCode = document.getElementsByName("paraCode")[0].value;
	//alert(paraCode);
	
	var testName = document.getElementsByName("testId")[0].value;
	//alert(testName);
	var testCode = document.getElementsByName("testCode")[0].value;
	//alert(testCode);
	
	var paraValue = document.getElementsByName("paraValue")[0].value;
	//alert(paraValue);
	
	var nRow=0;
	var tableObj=document.getElementById('extInvDtlTable');
	var numRows=tableObj.rows.length;
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id.split("#")[1];
	}
	else
	{
		nRow=numRows;
	}
	
	    var tabRow1=tableObj.insertRow(numRows);
		tabRow1.id="ExtInvRow1#"+(parseInt(nRow)+1);
	
		var td1=document.createElement("TD");
		var td2=document.createElement("TD");
		var td3=document.createElement("TD");
		var td4=document.createElement("TD");
		var td5=document.createElement("TD");
		var td6=document.createElement("TD");
		
	
	
		
		
		td1.innerHTML="<div align='center'>"
			+"<input name='selTestName' value='"+testName+"' type='hidden'></input>"
			+"<input name='selTestCode' value='"+testCode+"' type='hidden'></input>"
			+testName +"</div>";
																													
		td1.colspan="1";
		
		td2.innerHTML="<div align='center'>"
			+"<input name='selParaName' value='"+paraName+"' type='hidden'></input>"
			+"<input name='selParaCode' value='"+paraCode+"' type='hidden'></input>"
			+paraName +"</div>";
																													
		td2.colspan="1";
		
		

		td3.innerHTML="<div align='center'>"
			+"<input name='selParaValue' value='"+paraValue+"' type='hidden'></input>"
			+paraValue +"</div>";
																													
		td3.colspan="1";
		
		td4.innerHTML="<div align='center'>"
			+"<input name='selRecordDate' value='"+selectedDate+"' type='hidden'></input>"
			+selectedDate +"</div>";
																													
		td4.colspan="1";
		
		td5.innerHTML="<div align='center'>"
			+"<input name='selRecordTimeHr' value='"+recordTimeHr+"' type='hidden'></input>"
			+"<input name='selRecordTimeMin' value='"+recordTimeMin+"' type='hidden'></input>"
			+recordTimeHr +":"+ recordTimeMin +"</div>";
																													
		td5.colspan="1";
		
		td6.colspan="1";
		td6.innerHTML="<div align='center'><button type='button' class='btn btn-info btn-sm' style='width:60px;height:28px;' onClick='hideExtInvRow("+(parseInt(nRow)+1)+")'>Delete	</div>";
		
		tabRow1.appendChild(td1);
		tabRow1.appendChild(td2);
		tabRow1.appendChild(td3);
		tabRow1.appendChild(td4);
		tabRow1.appendChild(td5);
		tabRow1.appendChild(td6);
		
		clearFirstRowForExtInv();
}

function hideExtInvRow(Str)
{	
	//alert(Str);
	var tableObj=document.getElementById('extInvDtlTable');
	var temp=document.getElementById("ExtInvRow1#"+Str);
	tableObj.deleteRow(temp.rowIndex);
	//var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	//document.getElementsByName('numberOfRow')[0].value=index-1;
	return true;
}

function clearFirstRowForExtInv()
{
	document.getElementsByName("paraId")[0].value = "";
	document.getElementsByName("testId")[0].value = "";
	document.getElementsByName("paraValue")[0].value = "";
}


function deleteExtInvRow(slno,idx)
{
	//alert(slno);
	
	var answer = window.confirm("Please confirm to delete External Investigations Details")
	if (answer) {
		$.ajax({
			url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_EXT_INV.cnt?hmode=DELETE_PREV_EXT_INV_AJAX&slno="+slno+""),  
		    type : 'GET',
		    datatype: "json",
		    async : false,
			success: function(data)
			{
				hideDeletedExtInvRow(idx);
				
		      },
			
		      error: function(data)
		      {
		    	    alert('request failed :');
		    	}

			});
	}
	else {
	    return false;
	}
	
}


function hideDeletedExtInvRow(idx)
{
	//alert(idx);
	var tableObj=document.getElementById('extInvDtlTable');
	var temp=document.getElementById("ExtInvRow#"+(parseInt(idx)+2));
	
	temp.style.display ="none";
	return true;
}

function validate_EXT_INV()
{
	if(document.getElementsByName("testId")[0].value!= "")
		{
		  alert("Please click add button to add External Investigation");
		  document.getElementById('addButtonForExternalInvestigation').focus();
		  return false
		}
	else
		{
		  return true;
		}
}

