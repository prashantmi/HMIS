var elemSelText = null;
var popupList =null;
var elemDiagnosisCode = null;
var elemDiagnosisName = null;
var selectIndexVal = -1;
var selectedRowIndex=null;

window.onload=function(){
	
	if(document.getElementsByName("unitDiagnosisTypeCode")[0]){
		if(document.getElementsByName("unitDiagnosisTypeCode")[0].value==0)
		{
			elemDiagnosisCode = "icdCode";
			elemDiagnosisName = "disease";
		}
		else if(document.getElementsByName("unitDiagnosisTypeCode")[0].value==1)
		{
			elemDiagnosisCode = "hospitalDiseaseCode";
			elemDiagnosisName = "hospitalDiseaseName";
		}
	}

	if(document.getElementsByName("monitoringMode")[0]){
		if(document.getElementsByName("modificationRequired")[0]){
		if(document.getElementsByName("modificationRequired")[0].value==0){
				document.getElementsByName("modificationRequired")[0].checked=false
		}	
		}
		document.getElementsByName("monitoringMode")[0].focus()
	}
	if(document.getElementsByName("hmode")[0].value=="GETPATYELLOWSLIPDTL"){
		if(document.getElementsByName("modificationRequired")[0]){
			if(!document.getElementsByName("modificationRequired")[0].checked){
				document.getElementsByName("modificationRequired")[0].value=0
			}	
			
		}	
		showEpisodeOpenDiv();
		//alert("onload")
		//alert(document.getElementsByName("monitoringMode")[0].checked)
		if(document.getElementsByName("monitoringMode")[0].value=="1" 
			|| document.getElementsByName("modificationRequired")[0].value=="0" ){
			
			disableForm();
			document.getElementById("monitoringDiv").style.display="block";
		}	
	}
	
	if(document.getElementsByName("diagnosisRemoved")){
		var diagnosisRemoved=document.getElementsByName("diagnosisRemoved")
		var checkedItem=document.getElementsByName("checkedItem")[0].value.split("#")
		for(var i=0;i<diagnosisRemoved.length;i++){
			for(var j=0;j<checkedItem.length;j++){
				if(checkedItem[j]==diagnosisRemoved[i].value){
					diagnosisRemoved[i].checked=true
				}
			}		
		}
	}
	//alert(document.getElementsByName("modificationRequired")[0].value)	
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


function comboValidation(obj, str)
{	var valid= true
	if(obj.value==-1)
	{
		alert("Please Select : "+str)
		obj.focus()
		valid=false
	}
	return valid
}

function showEpisodeOpenDiv()
{
	if(document.getElementsByName("episodeIsOpen")[0].checked)
	{
	//alert("block")
	document.getElementById("nextVisitDatelabel").style.display="";
	document.getElementById("nextVisitDateControl").style.display="";
	}
	else
	{
	document.getElementById("nextVisitDatelabel").style.display="none";
	document.getElementById("nextVisitDateControl").style.display="none";
	}
	return true;
}

//********* Selected TextBox Functions
function gettext(eve, obj, varType)
{
	// 13 Enter, 27 Escape, 40 Down Arrow
	if(eve.keyCode!=13 && eve.keyCode!=27 && eve.keyCode!=40)
	{
		// Setting Selected Element
		selectIndexVal = -2;
		elemSelText=obj;
		selectedRowIndex=findIndex(elemSelText);
		if(obj.value!=" " && obj.value!="")
		//	sendDataForList(obj.value,varType);
		sendDataForCodeList(obj.value,varType);
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
// Move Control To Drop Down on Down Arrow Press
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
	document.getElementsByName(elemDiagnosisName)[selectedRowIndex].value = "";
	document.getElementsByName(elemDiagnosisCode)[selectedRowIndex].value = "";
}

function setValues()
{
	//alert(selectedRowIndex);
	if(selectIndexVal!=null && selectIndexVal>=0 && selectedRowIndex!=null)
	{
		//alert(selectIndexVal)
		var elemDiagCode = document.getElementsByName(elemDiagnosisName)[selectedRowIndex];
		var elemDiagName = document.getElementsByName(elemDiagnosisCode)[selectedRowIndex];
		var elemDropDown = document.getElementById("selid");
		selectIndexVal = elemDropDown.selectedIndex;
		if(elemSelText.name==elemDiagCode.name)
		{
			elemDiagName.value=elemDropDown.value;
		}
		else if(elemSelText.name==elemDiagName.name)
		{
			elemDiagCode.value=elemDropDown.value;
		}
		elemSelText.value=elemDropDown.options[selectIndexVal].text;
	}
}

//********** Drop Down Functions
// OnClick & Double click of Drop Downs
function setClickedValue()
{
	setValues();
	//alert("after setValues")
	document.getElementById("sid").style.display="none";
	document.getElementById("selid").value="";
	elemSelText.focus();
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}

// On Key Down of Drop Down
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

function sendDataForCodeList(searchText, searchType)
{
	handleCodeList(searchText, searchType);
}


function sendRequestForCodeList(text,type)
{
	if(document.getElementsByName("unitDiagnosisTypeCode")[0].value=="0")
	{
		var values = document.getElementsByName("icdCodeList")[0].options;
	
		var result="";
		var txtlen = text.length;
		if(type=="CODE")
		{
			for(var i=0; i<values.length; i++)
			{
				if(values[i].value.substr(0,txtlen).toLowerCase()==text.toLowerCase())
				{
					result+= values[i].text+"$"+values[i].value+"$";
				}
			}
		}
		else
		{
			for(var i=0; i<values.length; i++)
			{
				if(values[i].text.substr(0,txtlen).toLowerCase()==text.toLowerCase())
				{
					result+= values[i].value+"$"+values[i].text+"$";
				}
			}
		}
		
		if(result.length>0) result = result.substr(0,result.length-1);
	}
	else
	{
		var values = document.getElementsByName("hospitalCodeList")[0].options;
	
		var result="";
		var txtlen = text.length;
		if(type=="CODE")
		{
			for(var i=0; i<values.length; i++)
			{
				if(values[i].value.substr(0,txtlen).toLowerCase()==text.toLowerCase())
				{
					result+= values[i].text+"$"+values[i].value+"$";
				}
			}
		}
		else
		{
			for(var i=0; i<values.length; i++)
			{
				if(values[i].text.substr(0,txtlen).toLowerCase()==text.toLowerCase())
				{
					result+= values[i].value+"$"+values[i].text+"$";
				}
			}
		}
		
		if(result.length>0) result = result.substr(0,result.length-1);
	}
	
	return result;
}

function handleCodeList(searchText, searchType)
{
	popupList=null;
	var list = sendRequestForCodeList(searchText, searchType);

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

function checkDuplicateCode()
{	
	var diagnosisCode=document.getElementsByName("icdCode1")
	var previousDiagnosisCode=document.getElementsByName("diagnosisCode")
	if(!document.getElementsByName("diagnosisCode")){
		previousDiagnosisCode[0].value=""
	}
	
	for(var i=0;i<previousDiagnosisCode.length;i++){
		if(previousDiagnosisCode[i].value==document.getElementsByName("icdCode")[0].value && document.getElementsByName("diagnosisRemoved")[i].checked==false){
			alert("Diagnosis Already Added");
			return false;
		}	
	}
	for(var j=0;j<diagnosisCode.length;j++){
		if(diagnosisCode[j].value==document.getElementsByName("icdCode")[0].value){
			alert("Diagnosis Already Added");
			return false;
		}
	}
	
	return true;
}