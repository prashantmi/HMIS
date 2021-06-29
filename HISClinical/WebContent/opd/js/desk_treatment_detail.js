if (!('contains' in String.prototype)) String.prototype.contains = function (str, startIndex) {
    return -1 !== String.prototype.indexOf.call(this, str, startIndex);
};


// Desk Episode Diagnosis Tab Javascript

var popupList =null;
var drugDoseList = null;
var selectIndexVal = -1;
var elemSelText = null;
var selectedRowIndex = null;
var selectedDoseRowIndex = null;

// ################ Not in Use
function selectDeselectPrev(chk)
{
	if(chk.checked)
	{
		document.getElementsByName('addDiagnosisCode')[chk.value].disabled = false;
		document.getElementsByName('addDiagnosticTypeCode')[chk.value].disabled = false;
		document.getElementsByName('addDiagnosisRemarks')[chk.value].disabled = false;
		document.getElementsByName('addDiagnosis')[chk.value].disabled = true;
	}
	else
	{
		document.getElementsByName('addDiagnosisCode')[chk.value].disabled = true;
		document.getElementsByName('addDiagnosticTypeCode')[chk.value].disabled = true;
		document.getElementsByName('addDiagnosisRemarks')[chk.value].disabled = true;
		document.getElementsByName('addDiagnosis')[chk.value].disabled = false;
	}
}

//////////////////////////////////////////////////////////////////////////////////////
// ********* Drug Name Functions
// Getting Without AJAX Data
function getDrugDropDownData(eve, obj)
{
	// 13 Enter, 27 Escape, 40 Down Arrow
	if(eve.keyCode!=13 && eve.keyCode!=27 && eve.keyCode!=40)
	{
		// Setting Selected Element
		selectIndexVal = null;
		
		elemSelText=obj;
		selectedRowIndex=findIndex(elemSelText);
		
		if(obj.value!=" " && obj.value!="")
			sendDataForDrugList(obj.value);
			
	}
	if(eve.keyCode==40)
		changeControl();
}

// Move Control To Drop Down on Down Arrow Press
function changeControl()
{
	var elemDropDown=document.getElementById("cboDropDown");	
	if(typeof elemDropDown == 'undefined') return;
	
	var innerValue=elemDropDown.options[0].value;
	if(innerValue=="-1")
	{
		document.getElementById("divDropDown").style.display="none";
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
		selectIndexVal = document.getElementById("cboDropDown").selectedIndex;
		removeValues();
		return;
	}
	var flag = false;
	var arr = popupList;
	if(popupList==null || typeof popupList == 'undefined' || popupList=="" || popupList.length<=0 || selectIndexVal==null)
		removeValues();
	else
		setValues();

	if(document.getElementById("divDropDown") && document.getElementById("cboDropDown"))
	{
		document.getElementById("divDropDown").style.display="none";
	}
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}

function removeValues()
{
	document.getElementsByName("selDrugId")[selectedRowIndex].value = "";
	document.getElementsByName("selDrugItemTypeId")[selectedRowIndex].value = "";
	document.getElementsByName("selDrugName")[selectedRowIndex].value = "";
	document.getElementsByName("selDrugBrandId")[selectedRowIndex].value = "";
}

// Setting Values from Search Popup 
function setSearchValues(id,name)
{
	var index = parseInt(document.getElementsByName("drugDetailRows")[0].value)-1;
	var elemDrugId = document.getElementsByName("selDrugId")[index];
	var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeId")[index];
	var elemDrugName = document.getElementsByName("selDrugName")[index];

	elemDrugId.value=id.split("#")[0];
	elemDrugItemTypeId.value=id.split("#")[1];
	elemDrugName.value=name;
	
	// Drug Dose & Route
	selectedDoseRowIndex = index;	
	//sendDataForDrugRouteList(elemDrugId.value);	
	sendDataForDrugAlerts(elemDrugItemTypeId.value,elemDrugId.value,elemDrugName.value);	
	
}

// Setting Value and Calling for Drug Doses
function setValues()
{

	if(selectIndexVal!=null && selectIndexVal!=-1 && selectedRowIndex!=null)
	{
		
		var elemDrugId = document.getElementsByName("selDrugId")[selectedRowIndex];
		var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeId")[selectedRowIndex];
		var elemDrugName = document.getElementsByName("selDrugName")[selectedRowIndex];
		var elemDrugAdminId = "";
		var elemPatPregCode = "";
		var elemDrugBrandId = document.getElementsByName("selDrugBrandId")[selectedRowIndex];
		
		var elemDropDown = document.getElementById("cboDropDown");
		//alert(elemDropDown.value);
		selectIndexVal = elemDropDown.selectedIndex;
		elemDrugId.value=elemDropDown.value.split("#")[0];
		elemDrugItemTypeId.value=elemDropDown.value.split("#")[1];
		elemDrugAdminId=elemDropDown.value.split("#")[2];
		elemPatPregCode=elemDropDown.value.split("#")[3];
		elemDrugBrandId.value=elemDropDown.value.split("#")[4];
		//alert(elemPatPregCode);
		//alert(elemDrugAdminId);


	//	alert("drugbrandid"+elemDrugBrandId.value);
		//document.getElementsByName("selDrugBrandId")[selectedRowIndex].value=elemDrugBrandId.value;
		elemDrugName.value=elemDropDown.options[selectIndexVal].text.split("[")[0];
		// Drug Dose
		//
		var imgUnAvl=document.getElementById("stk_unavl"+selectedRowIndex);
		var imgAvl=document.getElementById("stk_avl"+selectedRowIndex);
		if(elemDropDown.options[selectIndexVal].text.split("[")[1]=="Qty:0]")
			{
				imgUnAvl.style.display="";
				imgAvl.style.display="none";
				document.getElementsByName("selStock")[selectedRowIndex].value="0";
			}
		else
			{
				imgAvl.style.display="";
				imgUnAvl.style.display="none";
				document.getElementsByName("selStock")[selectedRowIndex].value="1";
			}	
		
		
		selectedDoseRowIndex = selectedRowIndex;
		if(elemPatPregCode != null)
		checkPatPregCategory(elemPatPregCode);
		sendDataForDrugRouteList(elemDrugItemTypeId.value,selectedRowIndex);	
		if(elemDrugAdminId != null)
			selectDataAdminList(elemDrugAdminId,selectedRowIndex);	
		//sendDataForDrugAdminList(elemDrugAdminId);	
		sendDataForDrugAlerts(elemDrugItemTypeId.value,elemDrugId.value, elemDrugName.value);	
			
	}
	else
		removeValues();
}



//added by manisha gangwar date: 22.07.2016  
function calculateQuantity(selectedRowIndex)
{
	
	//vo.getDoseId()+"^"+vo.getIsFrequencyBound()+"^"+vo.getDoseQty() + "$" + vo.getDoseName()
	//alert(selectedRowIndex);
var freq=document.getElementsByName("selFrequencyId")[selectedRowIndex].value;
var freqname= document.getElementsByName("selFrequencyId")[selectedRowIndex].text;
var dos=document.getElementsByName("selDoseId")[selectedRowIndex].value;
var days=document.getElementsByName("selDays")[selectedRowIndex].value;
var calcquan="1";


	
//alert (freqname);  
//alert(freq);
//alert(dos);  alert(days);

	if(freq!= -1 && days!="")
	
		{
  
		if (freq =="11" || freqname =="OD" )
				freq="1";
			
		else if (freq =="12" || freqname =="BD" )
				freq="2";

		else if (freq =="13" || freqname =="TDS" )
				freq="3";

		else if (freq =="15" || freqname =="QID" )
				freq="4";
		else 
			freq="1";

		//alert(freq);
		
		//alert(dos);
		if(dos == "0^0^0" || dos == "0")
		{
		dos=document.getElementsByName("selDoseName")[selectedRowIndex].value;
		
		if(isNaN(dos)) // if dosage is not numeric 
			{
			//alert("hi");
			dos="1";
			document.getElementsByName("quantity")[selectedRowIndex].readOnly=false;	
			}
		calcquan=freq*dos*days;
		}
		
		else
		{
		var tmp= dos.split("^");
		var doseid=tmp[0];
		var freqbound=tmp[1];
		var doseqty=tmp[2];
		var calcquan="1"; 
		
       //  alert("inside dos");
       //  alert(doseid);  alert(freqbound);  alert(doseqty);
			if(freqbound==0)
				calcquan=1;
							
			else
				calcquan=freq*doseqty*days;
			
		}
		
		
	//	alert(calcquan);
			
			document.getElementsByName("quantity")[selectedRowIndex].value=calcquan;
	}
	
	

}


function selectDataAdminList(elemDrugAdminId,index)
{
	
	var drugAdminlist = document.getElementsByName("selDrugAdminId")[index].options;
	
	
	for(var i=0; i<drugAdminlist.length; i++)
	{
		
		if(drugAdminlist[i].value ==elemDrugAdminId)
		{
	         	drugAdminlist.selectedIndex = i;
           break;
        }
    }

}


//Added By Manisha Gangwar Date: 12.4.2016

function sendDataForDrugAdminList(elemDrugAdminId)
{
	//alert("HI");
	var elemAdminCombo = document.getElementsByName("selDrugAdminId")[0];
	elemAdminCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="-1";
	op.innerHTML="Select Value";
	elemAdminCombo.appendChild(op);
	selectIndexVal = -1;
	elemAdminCombo.selectedIndex = selectIndexVal;
	var drugAdminlist = document.getElementsByName("drugAdminList")[0].options;
	//alert(document.getElementsByName("drugPatPregCatList")[0].options);
	for(var i=0; i<drugAdminlist.length; i++)
	{
		//alert(drugAdminlist[i].value.toUpperCase());
		if(drugAdminlist[i].value==elemDrugAdminId)
		{
			op.value=elemDrugAdminId;
			op.innerHTML=drugAdminlist[i].text;
			elemAdminCombo.appendChild(op);
			break;
			
		}
	}

}


//Added by manisha gangwar Date: 12.4.2016 
//**Check Patient Pregnancy Type Code and Raise ALert if Preg Code(A to D and X)  **//
function checkPatPregCategory(patPregCode)
{
	if(document.getElementsByName("isPregnantFlag")[0].value=="1")
	{
		//alert("hi 1");
	if(patPregCode.toUpperCase()!="N")  // Pregnancy Category Code 'N' stands for 'FDA has not classified the drug'. (Other PregCatCode: A to D & X will raise alert)
	{
		//alert("hi 2");
	var pregCatlist = document.getElementsByName("drugPatPregCatList")[0].options;
	//alert(document.getElementsByName("drugPatPregCatList")[0].options);
	for(var i=0; i<pregCatlist.length; i++)
	{
		//alert(pregCatlist[i].text.toUpperCase());
		if(pregCatlist[i].text.toUpperCase()==patPregCode.toUpperCase())
		{
			//alert("hi matched");
			//alert("Patient Has Allergy From "+elemDrugName.value +"\n Please See Allergies");
			alert("Selected Drug is Allergic to Pregnant Patient, (Pregnancy Category Code of Drug:"+pregCatlist[i].text +")");
			break;
		}
	}
	}
	}
}


//******* Drug Data Getting Functions
function sendDataForDrugList(searchText)
{	
	handleDrugList(searchText);
	//var url='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=GETDRUGLIST&text='+searchText;
	//httpDrugListRequest("GET",url,true);
}
/*
function intialDrugListReq(reqType,url,isAsynch)
{
	// Specify the function that will handle the HTTP response 
	request.onreadystatechange=handleDrugList;
	request.open(reqType,url,isAsynch);
	
	// set the Content-Type header for a POST request 
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	request.send(null);
}
function httpDrugListRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		intialDrugListReq(reqType,url,asynch);
	}
	else if(window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
		//	 Unlikely to branch here, as IE uses will be able to use either 
			//one of the constructors
			intialDrugListReq(reqType,url,asynch);
		else
			alert("Your browser does not permit the use of all of this application's features!");
	}
	else
		alert("Your browser does not permit the use of all of this application's features!");
}
*/

Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
       if (this[i] === obj) {
          return true;
        } 
    }
    return false;
}


function sendRequestForDrugList(text)
{
	// Getting Drug DD List from "drugList" select object
	var values = document.getElementsByName("drugList")[0].options;
	//alert(values);
	var result="";
	var txtlen = text.length;
	for(var i=0; i<values.length; i++)
	{
		//alert(values[i]);
		//modifiedby:NehaRajgariya Date:5Sept2016
	//	if(values[i].text.toLowerCase().includes(text.toLowerCase()))
			if(values[i].text.toLowerCase().contains(text.toLowerCase()))
		{
			result+= values[i].value+"$"+values[i].text+"$";
		}
	}	
	if(result.length>0) result = result.substr(0,result.length-1);
	//alert(result);
	return result;
}


function handleDrugList(searchText)
{
	popupList=null;
	// Getting Data
	var list = sendRequestForDrugList(searchText);

	// Setting Drug Drop Down Position
	var elemDropDown=document.getElementById("cboDropDown");
	document.getElementById("divDropDown").style.display="none";
	var o=elemSelText;
	var l=0,t=0;
	while(o.offsetParent)
	{
		l+=o.offsetLeft;
		t+=o.offsetTop;
		o=o.offsetParent;
	}
	document.getElementById("divDropDown").style.left=l;
	document.getElementById("divDropDown").style.top=t+elemSelText.offsetHeight;
	document.getElementById("divDropDown").style.pixelWidth=elemSelText.style.pixelWidth;
			
	// Filling Data in DD
	var optionValueTextArray=list.split('$');
	elemDropDown.innerHTML="";
	popupList = optionValueTextArray;
	if(optionValueTextArray!="")
	{
		document.getElementById("divDropDown").style.display="block";
		for(i=0,j=1;i<optionValueTextArray.length && j<optionValueTextArray.length;i=i+2,j=j+2)
		{
			var op=document.createElement("option");
			op.value=optionValueTextArray[i];
			op.innerHTML=optionValueTextArray[j];
			elemDropDown.appendChild(op);
			//alert(optionValueTextArray[i]);
		}
		selectIndexVal = 0;
		elemDropDown.selectedIndex = selectIndexVal;
	}
	else
	{
		document.getElementById("divDropDown").style.display="none";				
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select Value";
		elemDropDown.appendChild(op);
		selectIndexVal = -1;
	}
}

//************ Drug Dose Data Getting Functions  //old function using ajax
/*function sendDataForDrugAlerts(itemType,drugDoseId,drugName)
{
	handleGetSetDrugDoseList(itemType);
	
	var url='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=GETDRUGDOSELIST&drugItemType='+itemType+'&drugId='+drugDoseId+'&drugName='+drugName+'&patcrNo='+document.getElementsByName('patCrNo')[0].value+'&departmentUnitCode='+document.getElementsByName('departmentUnitCode')[0].value;
	httpDrugDoseListRequest("GET",url,true);
		
}*/


function sendDataForDrugAlerts(itemType,drugDoseId,drugName)
{
	//var path=document.getElementsByName('path')[0].value;
	//alert(path);
	handleGetSetDrugDoseList(itemType);
	//alert("hi");
	var	patcrNo=document.getElementsByName('patCrNo')[0].value;
	var	departmentUnitCode=document.getElementsByName('departmentUnitCode')[0].value;
	//alert(drugDoseId);
	var elemDrugBrandId = document.getElementsByName("selDrugBrandId")[selectedDoseRowIndex].value;
	//alert(elemDrugBrandId);
	
	$.ajax({
		url : createFHashAjaxQuery('/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=GETDRUGDOSELIST&drugItemType='+itemType+'&drugId='+elemDrugBrandId+'&drugName='+drugName+'&patcrNo='+patcrNo+'&departmentUnitCode='+departmentUnitCode),
		type : 'GET',
	
		 datatype: "json",
		 async: false,
		  success : function(data) 
		  {
			//alert(data);
			
			var obj = eval(data);
			
			var elemDrugId = document.getElementsByName("selDrugId")[selectedDoseRowIndex];
			
			var elemDrugName = document.getElementsByName("selDrugName")[selectedDoseRowIndex];
			//alert(elemDrugName);
			var summary=document.getElementById('divSummary');
			//alert(summary);
			var innerHtml="";
			var fontcolor="";
			//alert(obj);
			if(obj != null && obj !='undefined' && obj.length > 0)
			{
				for (var i = 0; i < obj.length; i++) 
				{								
						var searchText=obj[i].msg;
						
						//alert(obj[i].statusDrugAllergy + " " + obj[i].statusDrugDupAssign + " " + obj[i].statusDrugReaction + " " + obj[i].statusDrugContradiction + " " + obj[i].statusPatPregnant + " ");
						if(obj[i].statusDrugAllergy != 'undefined' && obj[i].statusDrugAllergy > 0) //for allergies
						{
							if(obj[i].msg!='')	fontcolor="#FF0000";
						}
						else if(obj[i].statusDrugDupAssign != 'undefined' && obj[i].statusDrugDupAssign > 0) //for prev drug duplicate
						{
							if(obj[i].msg!='')	fontcolor="#0000A0";
						}
						else if(obj[i].statusDrugReaction != 'undefined' && obj[i].statusDrugReaction > 0) // for drug Reaction
						{
							if(obj[i].msg!='')	fontcolor="#0000B0";
						}
						else if(obj[i].statusDrugContradiction != 'undefined' && obj[i].statusDrugContradiction > 0) // for drug contradiction
						{
							if(obj[i].msg!='')	fontcolor="#FF0000";
						}							
						else if(obj[i].statusPatPregnant != 'undefined' && obj[i].statusPatPregnant > 0) // for Pregnancy Category
						{
							if(obj[i].msg!='')	fontcolor="#FF0000";
						}
					
						if(obj[i].msg!='') innerHtml+="*<font color="+fontcolor+" size='2'>"+obj[i].msg+"</font><br>\n";
										
					}
					
				var tr = getCorrespondingTR(elemDrugId);
				//alert(fontcolor)
				for(var j=0;j<tr.childNodes.length;j++)
					if(tr.childNodes[j].tagName=="TD")
					{	
						//alert("bgcolor");
						tr.childNodes[j].style.backgroundColor = fontcolor;
					}
				summary.innerHTML=innerHtml;
														
				}
		
			/*$('[name=bagTypeCode]').val(obj.bagTypeCode);
			$('[name=bagVolume]').val(obj.bagVolume);
			$('[name=bldAboCode]').val(obj.bldAboCode);
			$('[name=bldRhCode]').val(obj.bldRhCode);
			$('[name=tubingNo]').val(obj.tubingNo);
			$('[name=donationDate]').val(obj.donationDate);*/
			
			
				
		},
		error: function(data)
          {
              alert('request failed :');
          }
		
	});

}

function handleGetSetDrugDoseList(_itmeTypeId)
{
	var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeId")[selectedDoseRowIndex];
	var elemDoseCbo = document.getElementsByName("selDoseId")[selectedDoseRowIndex];
	var target = elemDoseCbo.parentNode;

	// Filling Dose Data
	var opts = document.getElementsByName("drugDoseList")[0].options;
	var isDataPresent = false;
	for(var i=0; i<opts.length; i++)
	{
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			isDataPresent = true;
			break;
		}		
	}

	// Getting Data
						
	if(isDataPresent)
	{
		var val = elemDoseCbo.value;
		
		var innerHtml="";			
		innerHtml+="<input type='hidden' name='selDoseName'/>\n";
		innerHtml+="<select name='selDoseId' tabindex='1' value='#' onchange='setDoseName("+selectedDoseRowIndex+")' >\n";
		innerHtml+="<option value='-1'>Select Value</option>\n";
		var haveValueDataFlag = false;				
		for(var i=0; i<opts.length; i++)
		{
			var value = opts[i].value.split("#")[0]
			var itmeTypeId = opts[i].value.split("#")[1];
			if(itmeTypeId==_itmeTypeId)
			{
				if(value==val)	haveValueDataFlag=true;	
				innerHtml+="<option value='"+value+"' selected='selected'>"+opts[i].text+"</option>\n";
			}
		}		
		innerHtml+="</select>";
		if(haveValueDataFlag==false) val="-1";
		innerHtml = innerHtml.replace("#",val);		
		target.innerHTML=innerHtml;
		document.getElementsByName("selDoseId")[selectedDoseRowIndex].value = val;
		setDoseName(selectedDoseRowIndex);
	}
	else
	{			
		if(elemDoseCbo.tagName!="INPUT")
			target.innerHTML="<input type='hidden' name='selDoseId' value='0'/> <input name='selDoseName' tabindex='1' maxlength='20' size='8' onkeypress='return validateAlphaNumOnly(this,event)'/>";					
	}
}

function fillData(index)
{
	//alert("drugRouteId"+ document.getElementsByName("selDrugRouteId")[index].value);
	//alert("doseId "+document.getElementsByName("selDoseId")[index].value);
	var drugRouteId=document.getElementsByName("selDrugRouteId")[index].value;
	var doseId=document.getElementsByName("selDoseId")[index].value;
	//alert("index "+index);
	//alert(document.getElementsByName("selDrugItemTypeId")[index].value);
	_itmeTypeId=document.getElementsByName("selDrugItemTypeId")[index].value;
	//alert("_itmeTypeId "+_itmeTypeId)
	
	
	var elemRouteCombo = document.getElementsByName("selDrugRouteId")[index];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select Value";
	elemRouteCombo.appendChild(op);
	
			
	// Filling Route Data
	var opts = document.getElementsByName("drugRouteList")[0].options;
	for(var i=0; i<opts.length; i++)
	{
		var routeId = opts[i].value.split("#")[0]
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			op=document.createElement("option");
			op.value=routeId;
			op.innerHTML=opts[i].text;
			elemRouteCombo.appendChild(op);
		}		
	}
	//alert("drugRouteId  after"+ document.getElementsByName("selDrugRouteId")[index].value);
	elemRouteCombo.value =drugRouteId;
	
	//handleGetSetDrugRouteList(document.getElementsByName("selDrugItemTypeId")[index].value);
	
	
	
	var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeId")[index];
	var elemDoseCbo = document.getElementsByName("selDoseId")[index];
	var target = elemDoseCbo.parentNode;

	// Filling Dose Data
	var opts = document.getElementsByName("drugDoseList")[0].options;
	var isDataPresent = false;
	for(var i=0; i<opts.length; i++)
	{
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			isDataPresent = true;
			break;
		}		
	}

	// Getting Data
						
	if(isDataPresent)
	{
		var val = elemDoseCbo.value;
		
		var innerHtml="";			
		innerHtml+="<input type='hidden' name='selDoseName'/>\n";
		innerHtml+="<select name='selDoseId' tabindex='1' value='#' onchange='setDoseName("+index+")' >\n";
		innerHtml+="<option value='-1'>Select Value</option>\n";
		var haveValueDataFlag = false;				
		for(var i=0; i<opts.length; i++)
		{
			var value = opts[i].value.split("#")[0]
			var itmeTypeId = opts[i].value.split("#")[1];
			if(itmeTypeId==_itmeTypeId)
			{
				if(value==val)	haveValueDataFlag=true;	
				innerHtml+="<option value='"+value+"' selected='selected'>"+opts[i].text+"</option>\n";
			}
		}		
		innerHtml+="</select>";
		if(haveValueDataFlag==false) val="-1";
		innerHtml = innerHtml.replace("#",val);		
		target.innerHTML=innerHtml;
		document.getElementsByName("selDoseId")[index].value = val;
		setDoseName(index);
	}
	else
	{			
		if(elemDoseCbo.tagName!="INPUT")
			target.innerHTML="<input type='hidden' name='selDoseId' value='0'/> <input name='selDoseName' tabindex='1' maxlength='20' size='8' />";		/*onkeypress='return validateAlphaOnly(this,event)'	*/
	}
}

// cims intergartion drug route code (working)  commented on 22.7.2016 by manisha gangwar 
/*function fillData(index)
{
	//alert("drugRouteId"+ document.getElementsByName("selDrugRouteId")[index].value);
	//alert("doseId "+document.getElementsByName("selDoseId")[index].value);
	var drugRouteId=document.getElementsByName("selDrugRouteId")[index].value;
	var doseId=document.getElementsByName("selDoseId")[index].value;
	//alert("index "+index);
	//alert(document.getElementsByName("selDrugItemTypeId")[index].value);
	_itmeTypeId=document.getElementsByName("selDrugItemTypeId")[index].value;
	//alert("_itmeTypeId "+_itmeTypeId)
	
	
	var elemRouteCombo = document.getElementsByName("selDrugRouteId")[index];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select Value";
	elemRouteCombo.appendChild(op);
	
			
	// Filling Route Data
	var opts = document.getElementsByName("drugRouteList")[0].options;
	for(var i=0; i<opts.length; i++)
	{
		var routeId = opts[i].value.split("#")[0]
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			op=document.createElement("option");
			op.value=routeId;
			op.innerHTML=opts[i].text;
			elemRouteCombo.appendChild(op);
		}		
	}
	//alert("drugRouteId  after"+ document.getElementsByName("selDrugRouteId")[index].value);
	elemRouteCombo.value =drugRouteId;
	//handleGetSetDrugRouteList(document.getElementsByName("selDrugItemTypeId")[index].value);
	
	
	
	var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeId")[index];
	var elemDoseCbo = document.getElementsByName("selDoseId")[index];
	var target = elemDoseCbo.parentNode;

	// Filling Dose Data
	var opts = document.getElementsByName("drugDoseList")[0].options;
	var isDataPresent = false;
	for(var i=0; i<opts.length; i++)
	{
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			isDataPresent = true;
			break;
		}		
	}

	// Getting Data
						
	if(isDataPresent)
	{
		var val = elemDoseCbo.value;
		
		var innerHtml="";			
		innerHtml+="<input type='hidden' name='selDoseName'/>\n";
		innerHtml+="<select name='selDoseId' tabindex='1' value='#' onchange='setDoseName("+index+")' >\n";
		innerHtml+="<option value='-1'>Select Value</option>\n";
		var haveValueDataFlag = false;				
		for(var i=0; i<opts.length; i++)
		{
			var value = opts[i].value.split("#")[0]
			var itmeTypeId = opts[i].value.split("#")[1];
			if(itmeTypeId==_itmeTypeId)
			{
				if(value==val)	haveValueDataFlag=true;	
				innerHtml+="<option value='"+value+"' selected='selected'>"+opts[i].text+"</option>\n";
			}
		}		
		innerHtml+="</select>";
		if(haveValueDataFlag==false) val="-1";
		innerHtml = innerHtml.replace("#",val);		
		target.innerHTML=innerHtml;
		document.getElementsByName("selDoseId")[index].value = val;
		setDoseName(index);
	}
	else
	{			
		if(elemDoseCbo.tagName!="INPUT")
			target.innerHTML="<input type='hidden' name='selDoseId' value='0'/> <input name='selDoseName' tabindex='1' maxlength='20' size='8' onkeypress='return validateAlphaOnly(this,event)'/>";					
	}
}
*/
function intialDrugDoseListReq(reqType,url,isAsynch)
{
	/* Specify the function that will handle the HTTP response */
	request.onreadystatechange=handleDrugDoseList;
	request.open(reqType,url,isAsynch);
	
	/* set the Content-Type header for a POST request */
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	request.send(null);
}
function httpDrugDoseListRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		intialDrugDoseListReq(reqType,url,asynch);
	}
	else if(window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
			/* Unlikely to branch here, as IE uses will be able to use either 
			one of the constructors*/
			intialDrugDoseListReq(reqType,url,asynch);
		else
			alert("Your browser does not permit the use of all of this application's features!");
	}
	else
		alert("Your browser does not permit the use of all of this application's features!");
}


function handleDrugDoseList()
{
	if(request.readyState == 4)
	{
		popupList=null;
		if(request.status == 200)
		{
			var elemDrugId = document.getElementsByName("selDrugId")[selectedDoseRowIndex];
			//var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeId")[selectedDoseRowIndex];
			var elemDrugName = document.getElementsByName("selDrugName")[selectedDoseRowIndex];
			
			//var elemDoseCbo = document.getElementsByName("selDoseId")[selectedDoseRowIndex];
			//var target = elemDoseCbo.parentNode;
			
				
			var temp = request.responseText.split('#');
			var alertCount = temp[0];
			var prevDrugStatus = temp[1];
			var drugReactionStatus = temp[2];
			
			var summary=document.getElementById('divSummary');
			//alert("summary "+ summary);
			var innerHtml="";	
			
			
			var tr = getCorrespondingTR(elemDrugId);
			for(var i=0;i<tr.childNodes.length;i++)
				if(tr.childNodes[i].tagName=="TD")
				{	
					if(alertCount!=0)
					{
						//alert("inside If");
						tr.childNodes[i].style.backgroundColor = "#FF0000";
					}
					else
					{
						tr.childNodes[i].style.backgroundColor = "";
					}
					if(prevDrugStatus!="prevNoStatus")
					{
						tr.childNodes[i].style.backgroundColor = "#0000A0";
					}
					if(drugReactionStatus!=0)
					{
						tr.childNodes[i].style.backgroundColor = "#0000B0";
					}
				}
				if(alertCount!=0)
				{
					alert("Patient Has Allergy From "+elemDrugName.value +"\n Please See Allergies");
					
					var searchText="Patient Has Allergy From "+elemDrugName.value +" Please See Allergies";
					if (document.getElementsByName("summary")[0].value.indexOf(searchText)==-1)
					{
						innerHtml+=document.getElementsByName("summary")[0].value+"*<font color='#FF0000' size='2'> Patient Has Allergy From "+elemDrugName.value +" Please See Allergies</font><br>";
						summary.innerHTML=innerHtml;
						document.getElementsByName("summary")[0].value=document.getElementsByName("summary")[0].value+"*<font color='#FF0000' size='2'> Patient Has Allergy From "+elemDrugName.value +" Please See Allergies</font><br>";
					}
					//alert("summary "+ summary);
				}
				if(prevDrugStatus!="prevNoStatus")
				{
					alert(elemDrugName.value+" Is Already Advice In "+ prevDrugStatus);
					var searchText=elemDrugName.value+" Is Already Advice In "+ prevDrugStatus;
					if (document.getElementsByName("summary")[0].value.indexOf(searchText)==-1)
					{
						innerHtml+=document.getElementsByName("summary")[0].value+"*<font color='#0000A0' size='2'>"+elemDrugName.value+" Is Already Advice In "+ prevDrugStatus+"</font><br>";
						summary.innerHTML=innerHtml;
						document.getElementsByName("summary")[0].value=document.getElementsByName("summary")[0].value+"*<font color='#0000A0' size='2'>"+elemDrugName.value+" Is Already Advice In "+ prevDrugStatus+"</font></br>";
					}
					
				}
				if(drugReactionStatus!=0)
				{
					alert(elemDrugName.value+" is reacted to patient ");
					var searchText=elemDrugName.value+" is reacted to patient ";
					if (document.getElementsByName("summary")[0].value.indexOf(searchText)==-1)
					{
						innerHtml+=document.getElementsByName("summary")[0].value+"*<font color='#0000B0' size='2'>"+elemDrugName.value+" is reacted to patient </font><br>";
						summary.innerHTML=innerHtml;
						document.getElementsByName("summary")[0].value=document.getElementsByName("summary")[0].value+"*<font color='#0000B0' size='2'>"+elemDrugName.value+" is reacted to patient </font><br>";
					}
					
				}
					
			/*var optionValueTextArray=temp[0].split('$');
			drugDoseList = optionValueTextArray;
					
			if(optionValueTextArray!="")
			{
				var innerHtml="";
				var val= elemDoseCbo.value;
				
				innerHtml+="<input type='hidden' name='selDoseName' />\n";
				innerHtml+="<select name='selDoseId' tabindex='1' value='#' onchange='setDoseName("+selectedDoseRowIndex+")' >\n";
				innerHtml+="<option value='-1'>Select Value</option>\n";
				var flag = false;				
				for(i=0,j=1;i<optionValueTextArray.length && j<optionValueTextArray.length;i=i+2,j=j+2)
				{
					if(optionValueTextArray[i]==val)
					{	
						flag=true;
						innerHtml+="<option value='"+optionValueTextArray[i]+"' selected='selected'>"
								+optionValueTextArray[j]+"</option>\n";
					}
					else
						innerHtml+="<option value='"+optionValueTextArray[i]+"'>"
								+optionValueTextArray[j]+"</option>\n";
					if(optionValueTextArray[i]==val)	flag=true;
				}
				innerHtml+="</select>";
				if(!flag) val="-1";
				innerHtml = innerHtml.replace("#",val);
				target.innerHTML=innerHtml;
				setDoseName(selectedDoseRowIndex);
			}
			else
			{			
				if(elemDoseCbo.tagName!="INPUT")
					target.innerHTML="<input type='hidden' name='selDoseId' value='0'/> <input name='selDoseName' tabindex='1' maxlength='20' size='8' onkeypress='return validateAlphaOnly(this,event)'/>";					
			}*/
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}//end outer if
}

//************ Drug Route Data Getting Functions
function sendDataForDrugRouteList(itemType,index)
{
	handleGetSetDrugRouteList(itemType,index);
	//var url='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=GETDRUGROUTELIST&drugItemType='+itemType;
	//httpDrugRouteListRequest("GET",url,true);
}
function handleGetSetDrugRouteList(_itmeTypeId,_index)
{
	var idx = selectedDoseRowIndex;
	if(_index) idx = _index;
	var elemRouteCombo = document.getElementsByName("selDrugRouteId")[idx];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select Value";
	elemRouteCombo.appendChild(op);

	// Filling Route Data
	var opts = document.getElementsByName("drugRouteList")[0].options;
	for(var i=0; i<opts.length; i++)
	{
		var routeId = opts[i].value.split("#")[0]
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			op=document.createElement("option");
			op.value=routeId;
			op.innerHTML=opts[i].text;
			elemRouteCombo.appendChild(op);
		}		
	}
	
		
	if(elemRouteCombo.length > 1)
	{
	elemRouteCombo.options.selectedIndex="1";
	var drugRouteName=document.getElementsByName('selDrugRouteName')[idx];
	drugRouteName.value=elemRouteCombo.options[1].text;
		//alert(drugRouteName.value);
	}

	
	
}

function checkPatPregFlag(_itmeTypeId,_index)
{
	var idx = selectedDoseRowIndex;
	if(_index) idx = _index;
	var elemRouteCombo = document.getElementsByName("selDrugRouteId")[idx];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select Value";
	elemRouteCombo.appendChild(op);

	// Filling Route Data
	var opts = document.getElementsByName("drugRouteList")[0].options;
	for(var i=0; i<opts.length; i++)
	{
		var routeId = opts[i].value.split("#")[0]
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			op=document.createElement("option");
			op.value=routeId;
			op.innerHTML=opts[i].text;
			elemRouteCombo.appendChild(op);
		}		
	}
}



/*function intialDrugRouteListReq(reqType,url,isAsynch)
{
	// Specify the function that will handle the HTTP response 
	routeRequest.onreadystatechange=handleDrugRouteList;
	routeRequest.open(reqType,url,isAsynch);
	
	// set the Content-Type header for a POST request 
	routeRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	routeRequest.send(null);
}
function httpDrugRouteListRequest(reqType,url,asynch)
{	
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		routeRequest = new XMLHttpRequest();
		intialDrugRouteListReq(reqType,url,asynch);
	}
	else if(window.ActiveXObject)
	{
		routeRequest=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			routeRequest=new ActiveXObject("Microsoft.XMLHTTP");
		if(routeRequest)
			// Unlikely to branch here, as IE uses will be able to use either 
			//one of the constructors
			intialDrugRouteListReq(reqType,url,asynch);
		else
			alert("Your browser does not permit the use of all of this application's features!");
	}
	else
		alert("Your browser does not permit the use of all of this application's features!");
}
function handleDrugRouteList()
{
	if(routeRequest.readyState == 4)
	{
		popupList=null;
		if(routeRequest.status == 200)
		{	
			var comboTextArray=routeRequest.responseText.split('^');
			var routeCombo=document.getElementsByName("selDrugRouteId")[selectedDoseRowIndex];
			routeCombo.innerHTML="";
				var op=document.createElement("option");
				op.value="-1";
				op.innerHTML="Select Value";
				routeCombo.appendChild(op);
			if(comboTextArray!="")
			{
				for(i=0,j=1;i<comboTextArray.length && j<comboTextArray.length;i=i+2,j=j+2)
				{
					var op=document.createElement("option");
					op.value=comboTextArray[i];
					op.innerHTML=comboTextArray[j];
					routeCombo.appendChild(op);
				}
			}
			else
			{
				var op=document.createElement("option");
				op.value="-1";
				op.innerHTML="Select Value";
				routeCombo.appendChild(op);
				selectIndexVal = -1;
			}
		}	
	}//end outer if
}*/




// End Drug Functions /////////////////////////////////////////////////////////////////////////////
//********** Drop Down Functions
// OnClick & Double click of Drop Downs

function setClickedValue()
{
	setValues();
	document.getElementById("divDropDown").style.display="none";
	document.getElementById("cboDropDown").value="";
	elemSelText.focus();
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}


// On Key Down of Drop Down
function onKeyDownDrop(eve)
{
	// 9 Tab
	if(eve.keyCode==13 || eve.keyCode==9)
	{
		setValues();
		document.getElementById("divDropDown").style.display="none";
		document.getElementById("cboDropDown").value="";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
	if(eve.keyCode==27)
	{
		document.getElementById("divDropDown").style.display="none";
		elemSelText.focus();
		popupList = null;
		selectIndexVal = -1;
		elemSelText = null;
		selectedRowIndex = null;
	}
}

function onChangeDrop()
{
	var elemDropDown = document.getElementById("cboDropDown");
	selectIndexVal = elemDropDown.selectedIndex;
	elemSelText.value=elemDropDown.options[selectIndexVal].text;
}

function setDoseName(index)
{
	//alert("index " + index);
	
	var doseId=document.getElementsByName('selDoseId')[index];
	//alert("doseid"+ doseId.value );
	//alert("doseId.split("^")[1] "+doseId.value.split("^")[1]);
	if(doseId.value.split("^")[1]==0)
	{
		//alert("quantity "+doseId.value.split("^")[2]);
		if(doseId.value.split("^")[2]=="null")
		{
			document.getElementsByName("quantity")[index].value="";
		}
		else
		{
			document.getElementsByName("quantity")[index].value=doseId.value.split("^")[2];
		}
		
		document.getElementsByName("quantity")[index].value="";
		//alert(document.getElementsByName("quantity")[index].readonly);
		document.getElementsByName("quantity")[index].readOnly=false;	
		//alert(document.getElementsByName("quantity")[index].readonly);
	}
	/*else
	{
		document.getElementsByName("quantity")[index].value="";
		document.getElementsByName("quantity")[index].readOnly=true;
	}*/
	var doseName=document.getElementsByName('selDoseName')[index];
	
	doseName.value=doseId.options[doseId.selectedIndex].text;
}

function setDrugRouteName(index)
{
	
	var drugRouteId=document.getElementsByName('selDrugRouteId')[index];
	var drugRouteName=document.getElementsByName('selDrugRouteName')[index];
	drugRouteName.value=drugRouteId.options[drugRouteId.selectedIndex].text;
	//alert(drugRouteName.value);
}


function setDrugAdminName(index)
{
	//alert(document.getElementsByName('selDrugAdminId')[index]);
	//alert(document.getElementsByName('selDrugAdminName')[index]);
	var drugadminId=document.getElementsByName('selDrugAdminId')[index];
	var drugadminame=document.getElementsByName('selDrugAdminName')[index];
	drugadminName.value=drugRouteId.options[drugadminId.selectedIndex].text;
}


//******* Row * Validation
// Focusing On Add Button
function goToNextIndex(eve)
{
	if(eve.keyCode==9)
	{
		document.getElementById("addButton").focus();
	}
}

// get no of days between two date
function noOfDays(a,b)
{
	valid=true;
	var day=0;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	day=(adate-bdate)/86400000;
	return day;
}

// Validating Rows
function validateRows()
{
	var rowCount = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	for(var i=0;i<rowCount;i++)
	{
		if(document.getElementsByName("selDrugName")[i].value=="" || document.getElementsByName("selDrugId")[i].value==0)
		{
			alert("Please Enter Drug Name");
			setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDrugName")[i].focus();
			return false;
		}
		
		if(document.getElementsByName("selDoseId")[i].value=="-1")
		{
			alert("Please Enter Dose");
			setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDoseId")[i].focus();
			return false;
		}
			
		if(document.getElementsByName("selDoseName")[i].value=="")
		{
			alert("Please Enter Dose");
			setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDoseName")[i].focus();
			return false;
		}	
			
		if(document.getElementsByName("selFrequencyId")[i].value=="-1")
		{
			alert("Please Enter Frequecy");
			setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selFrequencyId")[i].focus();
			return false;
		}
		if(document.getElementsByName("selDays")[i].value=="" || document.getElementsByName("selDays")[i].value=="0")
		{
			alert("Please Enter Days");
			setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDays")[i].focus();
			return false;
		}
		if(document.getElementsByName("selStartDay")[i].value=="")
		{
			alert("Please Enter Start Day");
			setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selStartDay")[i].focus();
			return false;
		}
	}
	
	for(var i=0;i<rowCount-1;i++)
	{
		var drugId = document.getElementsByName("selDrugId")[i].value;
		var doseId=document.getElementsByName("selDoseId")[i].value;
		var fequencyId=document.getElementsByName("selFrequencyId")[i].value;
		
		for(var j=i+1;j<rowCount;j++)
		{
			if(document.getElementsByName("selDrugId")[j].value==drugId )
			{
				
				var systemDate = convertStrToDate(document.forms[0].sysDate.value,"dd-MM-yyyy");
								
				systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDay")[i].value));
				startDate=new Date(systemDate);
				
				newStartDate=new Date(startDate);
				
				startDate.setDate(startDate.getDate()+parseInt(document.getElementsByName("selDays")[i].value));
				endDate=startDate
				
				
				var systemDate = convertStrToDate(document.forms[0].sysDate.value,"dd-MM-yyyy");
				systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDay")[j].value));
				startDateOfNext=new Date(systemDate);
				
				newNextStartDate=new Date(startDateOfNext);
				
				startDateOfNext.setDate(startDateOfNext.getDate()+parseInt(document.getElementsByName("selDays")[j].value));
				endDateOfNext=startDateOfNext
				
				var startDateOfNext = convertDateToStr(newNextStartDate,"dd-Mon-yyyy");
				var endDateOfNext = convertDateToStr(endDateOfNext,"dd-Mon-yyyy");
				var startDate = convertDateToStr(newStartDate,"dd-Mon-yyyy");
				var endDate = convertDateToStr(endDate,"dd-Mon-yyyy");
				
								
				var startday=noOfDays(startDateOfNext,startDate);
				var endDay=noOfDays(endDate,startDateOfNext);
				
				
								
				if(startday>=0 && endDay>0)
				{
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				
				var nextStartday=noOfDays(endDateOfNext,startDate);
				var nextEndDay=noOfDays(endDate,endDateOfNext);
				
				
				
				if(nextStartday>0 && nextEndDay>0)
				{
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				
				
				var startday=noOfDays(startDate,startDateOfNext);
				var endDay=noOfDays(endDateOfNext,startDate);
								
				if(startday>=0 && endDay>0)
				{
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				
				var nextStartday=noOfDays(endDate,startDateOfNext);
				var nextEndDay=noOfDays(endDateOfNext,endDate);
				
				
				
				
				if(nextStartday>0 && nextEndDay>0)
				{
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				
			}
			
			if(document.getElementsByName("selDrugId")[j].value==drugId && document.getElementsByName("selDoseId")[j].value==doseId && document.getElementsByName("selFrequencyId")[j].value==fequencyId )
			{
				alert(document.getElementsByName("selDrugName")[j].value+" is Already Added");	
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDrugName")[j].focus();
				return false;
			}
			
		}
	}
	
	
	
	//cheking drug from prev drug
	
	var len=document.getElementsByName("prevDrugName").length;
	var prevDrugName=document.getElementsByName("prevDrugName");
	
	
	var len2=document.getElementsByName("selDrugName").length;
	var drugName=document.getElementsByName("selDrugName");
	
			for(i=0;i<len2;i++)
			{	
						
				for(j=0;j<len;j++)
				{
					if(drugName[i].value==prevDrugName[j].value)
					{
													
							var systemDateStr = document.getElementsByName("sysDate")[0].value;
							var prevEndDate = document.getElementsByName("selEndDate")[j].value;
							//alert("prevEndDate "+prevEndDate);
							
							var validateEndDate=noOfDays(prevEndDate,systemDateStr);
							//alert("validateEndDate "+validateEndDate);
							
							if(validateEndDate>=0)
							{
								
								var changeStartDate=document.getElementsByName("changeStartDate")[i].value;
								//alert("changeStartDate "+changeStartDate);
								
								var rxContinueFlag=document.getElementsByName("rxContinueFlag")[i].value;
								//alert("rxContinueFlag "+rxContinueFlag);
								
								
								//var revoke=document.getElementsByName("revoke")[i].value;
								//var revokeChecked=document.getElementsByName("revoke")[i].checked;
								//alert("revokeArray "+revoke);
								//alert("revokeChecked "+revokeChecked);
								
								
								
								var systemDate = convertStrToDate(document.forms[0].sysDate.value,"dd-MM-yyyy");
								
								systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDay")[i].value));
								startDate=new Date(systemDate);
								//alert("startDate "+startDate);
								newStartDate=new Date(startDate);
							
								/*
								//alert("document.forms[0].sysDate " + document.forms[0].sysDate);
								//alert("document.forms[0] " + document.forms[0]);
								if(rxContinueFlag=="modify")
								{
									alert("before changeStartDate Obj "+document.forms[0].changeStartDate[i].value);
									var changeStartDateObj=convertStrToDate(document.forms[0].changeStartDate[i].value,"dd-MM-yyyy");
									alert("changeStartDate obj "+changeStartDateObj);
									
					 				changeStartDateObj.setDate(changeStartDateObj.getDate()-parseInt(1));
									endDate=changeStartDateObj
									alert("modify end date "+endDate);
								}
								else
								{
									startDate.setDate(startDate.getDate()+(parseInt(document.getElementsByName("selDays")[i].value)-1));
									endDate=startDate	
									alert("normal End date "+endDate);						
								}
								*/
								
								startDate.setDate(startDate.getDate()+(parseInt(document.getElementsByName("selDays")[i].value)-1));
								endDate=startDate	
						
								//var prevEndDate = document.getElementsByName("selEndDate")[j].value;
								var prevStartDate = document.getElementsByName("selStartDate")[j].value;
								var startDate = convertDateToStr(newStartDate,"dd-Mon-yyyy");
								var endDate = convertDateToStr(endDate,"dd-Mon-yyyy");
						
								//alert("prevStartDate "+prevStartDate);
								//alert("prevEndDate "+prevEndDate);
								//alert("startDate "+startDate);
								//alert("endDate "+endDate);
								
								var startday=noOfDays(startDate,prevStartDate);
								var endDay=noOfDays(prevEndDate,startDate);
							
								//	alert("startday "+startday);
								//alert("endDay "+endDay);
							
								if(startday>=0 && endDay>=0)
								{
									//alert("1");
									//alert("j "+j);
									alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									setToDesiredTab("tabDrugAdvice");
									document.getElementsByName("selDrugName")[i].focus();
									return false;
								}
							
								var nextStartday=noOfDays(endDate,prevStartDate);
								var nextEndDay=noOfDays(prevEndDate,endDate);
							
								//alert("nextStartday "+nextStartday);
								//alert("nextEndDay "+nextEndDay);
				
								if(nextStartday>=0 && nextEndDay>=0)
								{
									//alert("2");
									alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									setToDesiredTab("tabDrugAdvice");
									document.getElementsByName("selDrugName")[i].focus();
									return false;
								}
							
								var startday=noOfDays(prevStartDate,startDate);
								var endDay=noOfDays(endDate,prevStartDate);
							
								if(startday>=0 && endDay>=0)
								{
									//alert("3");
									alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									setToDesiredTab("tabDrugAdvice");
									document.getElementsByName("selDrugName")[i].focus();
									return false;
								}
							
									var nextStartday=noOfDays(prevEndDate,startDate);
									var nextEndDay=noOfDays(endDate,prevEndDate);
									
									if(nextStartday>=0 && nextEndDay>=0)
									{
										//alert("4");
										alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
										setToDesiredTab("tabDrugAdvice");
										document.getElementsByName("selDrugName")[i].focus();
										return false;
									}
								
								//alert("drug name "+ drugName[i].value +"prev drug name "+prevDrugName[j].value );
								//alert("dose Id "+document.getElementsByName("selDoseId")[i].value.split("^")[0] +" prev dose id "+document.getElementsByName("previousDoseId")[j].value );
								//alert("Freq Id "+document.getElementsByName("selFrequencyId")[i].value +"prev freq Id "+document.getElementsByName("previousFrequencyId")[j].value);
								
								
								if(drugName[i].value==prevDrugName[j].value && document.getElementsByName("selDoseId")[i].value.split("^")[0]==document.getElementsByName("previousDoseId")[j].value && document.getElementsByName("selFrequencyId")[i].value==document.getElementsByName("previousFrequencyId")[j].value )
								{
									alert(drugName[i].value+" Is Already Taking By Patient ");
									setToDesiredTab("tabDrugAdvice");
									document.getElementsByName("selDrugName")[i].focus();
									return false;
								}
								
								
								
							}				
							
							
						
					}
				}
			}
	
	
	
	
	//For isEmptyStomach field in drug Advice
	var len=document.getElementsByName("selIsEmptyStomach").length;
	//alert("len  "+len);
	var emptyStomachIndex="";
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("selIsEmptyStomach")[i].checked)
		{	
			emptyStomachIndex=emptyStomachIndex+"1"+"#";
		}
		else
		{
			emptyStomachIndex=emptyStomachIndex+"0"+"#";
		}
	}
	document.getElementsByName("emptyStomachIndexArray")[0].value=emptyStomachIndex;
	//alert("empty stomach Array "+document.getElementsByName("emptyStomachIndexArray")[0].value);
		
	
	return true;
}
//validate Ext Treatment Rows
function validateExtTreatmentRows()
{
	var rowCount = parseInt(document.getElementsByName("extDrugDetailRows")[0].value);
	for(var i=0;i<rowCount;i++)
	{
		
		if(document.getElementsByName("selExtTreatmentId")[i].value=="-1" && document.getElementsByName("selExtTreatmentName")[i].value=="")
		{
			alert("Please Enter Ext Treatment Name");
			setToDesiredTab("tabLAAdvice");
			document.getElementsByName("selExtTreatmentName")[i].focus();
			return false;
		}	
		
		if(document.getElementsByName("selExtFrequencyId")[i].value=="-1")
		{
			alert("Please Select Frequency");
			setToDesiredTab("tabLAAdvice");
			document.getElementsByName("selExtFrequencyId")[i].focus();
			return false;
		}
			
		//alert("ext frequency id"+document.getElementsByName("selExtFrequencyId")[i].value);		
		if(document.getElementsByName("selExtDays")[i].value=="")
		{
			alert("Please Enter Days");
			setToDesiredTab("tabLAAdvice");
			document.getElementsByName("selExtDays")[i].focus();
			return false;
		}
		if(document.getElementsByName("selExtStartDay")[i].value=="")
		{
			alert("Please Enter Start Day");
			setToDesiredTab("tabLAAdvice");
			document.getElementsByName("selExtStartDay")[i].focus();
			return false;
		}
	}
	
	//checking duplicate External Treatment Name
	for(var i=0;i<rowCount-1;i++)
	{
		//var drugId = document.getElementsByName("selExtTreatmentId")[i].value;
		var extTreatName = document.getElementsByName("selExtTreatmentName")[i].value;
		
		for(var j=i+1;j<rowCount;j++)
		{
			//if(document.getElementsByName("selExtTreatmentId")[j].value==drugId)
			if(document.getElementsByName("selExtTreatmentName")[j].value==extTreatName)
			{
				alert(document.getElementsByName("selExtTreatmentName")[j].value+" is Already Added");
				setToDesiredTab("tabLAAdvice");
				document.getElementsByName("selExtTreatmentName")[j].focus();
				return false;
			}
	     }
	}
	
	
	//For isEmptyStomach field in drug Advice
	var len=document.getElementsByName("selIsEmptyStomach").length;
	//alert("len  "+len);
	var emptyStomachIndex="";
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("selIsEmptyStomach")[i].checked)
		{	
			emptyStomachIndex=emptyStomachIndex+"1"+"#";
		}
		else
		{
			emptyStomachIndex=emptyStomachIndex+"0"+"#";
		}
	}
	document.getElementsByName("emptyStomachIndexArray")[0].value=emptyStomachIndex;
	//alert("empty stomach Array "+document.getElementsByName("emptyStomachIndexArray")[0].value);
	
	document.getElementsByName("activeTab")[0].value="tabLAAdvice";
	return true;
}

// Delete Row 
function deleteRow(row)
{
	var index=parseInt(document.getElementsByName('drugDetailRows')[0].value);
	var elemTable=document.getElementById('tblDrugDetailId');
	elemTable.deleteRow(row.rowIndex);
	document.getElementsByName('drugDetailRows')[0].value = index-1;
	return true;
}
//delete extTretment Table Row
function deleteExtTreatmentRow(row)
{
	var index=parseInt(document.getElementsByName('extDrugDetailRows')[0].value);
	var elemTable=document.getElementById('tblExtTreatMentDetailId');
	elemTable.deleteRow(row.rowIndex);
	document.getElementsByName('extDrugDetailRows')[0].value = index-1;
	return true;
}

// ########
function AddRowToTable()
{
	var resp=document.getElementsByName('comboOptionString')[0].value;
	var nRow=0;
	var elemTable=document.getElementById('tblDrugDetail');
	var numRows=elemTable.rows.length;
	
	if(numRows>2)
	{
		nRow=elemTable.rows[numRows-1].id;
	}
	else
	{
		nRow=numRows;
	}
	
	var tabRow=elemTable.insertRow(numRows);
	tabRow.id=parseInt(nRow)+1;

	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");
	var td5=document.createElement("TD");

	td1.innerHTML="<div id='divIcdCode' align='center'>"+"<input type='text' name='"+ elemDiagnosisCode 
	+ "' maxlength='6' size='6' tabindex='1' onkeypress='return validateAlphaNumOnly(this,event)' onkeyup=\"gettext(event,this,'CODE');\" " 
	+ "onblur='callOnBlur()' ></div>";
	td1.className='tdfont';																													
	td1.colspan="1";
	
	td2.innerHTML="<div align='center'>"+"<input type='text' name='"+ elemDiagnosisName 
	+ "' maxlength='50' size='30' tabindex='1' onkeypress='return validateAlphaNumOnly(this,event)' onkeyup=\"gettext(event,this,'LABLE');\" "
	+ "onblur='callOnBlur()' ></div>";
	td2.className='tdfont';
	td2.colspan="1";
	
	td3.innerHTML="<div align='center'>"
		+ "<select name='diagonisticTypeCode' tabindex='1' onfocus='hideListOnBlur(event)' ><option value='-1'>Select Value</option>"
		+ resp + "</select></div>";
	td3.className='tdfont';
	td3.colspan="1";
	
	td4.innerHTML="<div align='center'>"
		+ "<input type='text' name='remarks' maxlength='50' size='30' tabindex='1' ></div>";
	td4.className='tdfont';
	td4.colspan="1";

	
	td5.className='tdfont';
	td5.colspan="1";
	td5.innerHTML="<div align='center'><img src='/HIS/hisglobal/images/avai/minus.gif' onClick='deleteRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
   
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	tabRow.appendChild(td4);
	tabRow.appendChild(td5);
	document.getElementsByName('numberOfRow')[0].value=numRows;
}

//############
function validateIt()
{	
	if(typeof document.getElementsByName("chkPrev")[0] == 'undefined' || document.getElementsByName('numberOfRow')[0].value>1)
	{	
		for(var i=0;i<document.getElementsByName('numberOfRow')[0].value;i++)
		{
			if(!validateRow(i))
				return false;
		}
	}
	else
	{	
		var row=0;
		var Code,name;
		Code = document.getElementsByName(elemDiagnosisCode)[row];
		name = document.getElementsByName(elemDiagnosisName)[row];

		diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[row];
		remark = document.getElementsByName("remarks")[row];
		
		if(Code.value!="" || name.value!="" ||diaTypeCode.value!="-1")
		{
			if(!validateRow(row))
				return false;
		}
		
		var chks = document.getElementsByName("chkPrev");
		if(typeof document.getElementsByName("chkPrev")[0] != 'undefined')
		{
			for( var i=0;i<chks.length;i++)
			{
				if(chks[i].checked && document.getElementsByName('addDiagnosisRemarks')[i].value =="")
				{
					alert("Enter the Remark ");
					document.getElementsByName('addDiagnosisRemarks')[i].focus();
					return false;
				}
			}
		}
	}
	return true;
}


//############
function getPrevDiagnosisDetail(event,path)
{
	openPopup(path,event,300,600);
}




/*
function handleDrugList()
{
	if(request.readyState == 4)
	{
		popupList=null;
		if(request.status == 200)
		{
			var elemDropDown=document.getElementById("cboDropDown");
			// Setting Position
			document.getElementById("divDropDown").style.display="none";
			var o=elemSelText;
			var l=0,t=0;
			while(o.offsetParent)
			{
				l+=o.offsetLeft;
				t+=o.offsetTop;
				o=o.offsetParent;
			}
			document.getElementById("divDropDown").style.left=l;
			document.getElementById("divDropDown").style.top=t+elemSelText.offsetHeight;
			document.getElementById("divDropDown").style.pixelWidth=elemSelText.style.pixelWidth;
			
			var optionValueTextArray=request.responseText.split('$');
			//alert("optionValueTextArray-->"+optionValueTextArray);
			elemDropDown.innerHTML="";
			popupList = optionValueTextArray;
			if(optionValueTextArray!="")
			{
				document.getElementById("divDropDown").style.display="block";
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
				document.getElementById("divDropDown").style.display="none";				
				var op=document.createElement("option");
				op.value="-1";
				op.innerHTML="Select Value";
				elemDropDown.appendChild(op);
				selectIndexVal = -1;
			}
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}//end outer if
}
*/

function getCorrespondingTR(elem)
{
	var elemTR = elem;
	while(elemTR.tagName!="TR")
		elemTR=elemTR.parentNode;
	return elemTR;
}



///////////////////////////////////////////////////////////////////////////////////////////////

function DelRowFromTableForRxContinue(mode,obj)
{

	var rxLen=document.getElementsByName("rxContinue").length;
	//alert(document.getElementsByName("rxContinue")[0].value);
	/*
	//this is for Rx Extion
	var extLen= document.getElementsByName("extension").length;
	if(extLen==rxLen)
	{
		if(document.getElementsByName("extension")[obj].checked || document.getElementsByName("revoke")[obj].checked)
		{
			alert("You can select only one check box in a row");
			document.getElementsByName("rxContinue")[obj].checked=false;
			return false;
		}
	}
	*/
	//alert("index"+obj);
	var prevdrugbrand=document.getElementsByName("prevDrugBrandId")[obj];
	//alert(prevdrugbrand.value);
	document.getElementsByName("selDrugBrandId")[0].value=prevdrugbrand.value;
	//alert(document.getElementsByName("selDrugBrandId")[0].value);
	
	
	if(document.getElementsByName("revoke")[obj].checked)
	{
		alert("You can select only one check box in a row");
		document.getElementsByName("rxContinue")[obj].checked=false;
		return false;
	}
	
//For isEmptyStomach field in drug Advice
	var len=document.getElementsByName("selIsEmptyStomach").length;
	//alert("len  "+len);
	var emptyStomachIndex="";
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("selIsEmptyStomach")[i].checked)
		{	
			emptyStomachIndex=emptyStomachIndex+"1"+"#";
		}
		else
		{
			emptyStomachIndex=emptyStomachIndex+"0"+"#";
		}
	}
	document.getElementsByName("emptyStomachIndexArray")[0].value=emptyStomachIndex;


document.getElementsByName('hmode')[0].value=mode;
document.getElementsByName('index')[0].value=obj;
document.forms[0].submit();

}

function DelRowFromTableForRxContinueExt(mode,obj)
{
document.getElementsByName('hmode')[0].value=mode
document.getElementsByName('extIndex')[0].value=obj
document.getElementsByName("activeTab")[0].value="tabLAAdvice";
document.forms[0].submit();

}


function validateNumberWithoutZeroOnly(e)
{	
	/*
	var rowCount = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	for(var i=0;i<rowCount;i++)
	{
		if(document.getElementsByName("selDays")[i].value=="0")
		{
			alert("You can not enter zero for days");
			document.getElementsByName("selDays")[i].value="";
			setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDays")[i].focus();
			return false;
		}
	}
	*/
	
	var key;
	var keychar;
	
	

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space (give here which character u want to allow 
	else if ((("123456789").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
	  
}

function validateNumberic(e)
{	
	var key;
	var keychar;
	
	

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space (give here which character u want to allow 
	else if ((("0123456789").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
	  
}




function validateRowsSave()
{
	
	//added by manisha gangwar for alert on zero balance on 03.Nov.2015
	if(document.getElementsByName("patBalance") && document.getElementsByName("patBalance")[0] && document.getElementsByName("patBalance")[0].value!= null)
	{
		if(document.getElementsByName("patBalance")[0].value<=0.0)
		{
			//alert("Patient Account Balance is not sufficient..");		
		}
	}
	
	
		
	/*
	//this is for extension
	var extendedLen=document.getElementsByName("extension").length;
	for(i=0;i<extendedLen;i++)
	{
		
		if(document.getElementsByName("extension")[i].checked)
		{
			if(document.getElementsByName("extensionDays")[i].value=="")
			{
				alert("Please Enter Extended Days");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("extensionDays")[i].focus();
				return false;
			}
		}
		
	}
	*/
	
	var rowCount = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	
	for(var i=0;i<rowCount;i++)
	{
		if(i==(rowCount-1))
		{
			//for checking the last record of drug treatment detail
			if(document.getElementsByName("selDrugName")[i].value!="" ||  document.getElementsByName("selDoseName")[i].value!="" || document.getElementsByName("selFrequencyId")[i].value!="-1" || document.getElementsByName("selDays")[i].value!="" || document.getElementsByName("selInstructions")[i].value!="")
			{
								
				if(document.getElementsByName("selDrugName")[i].value=="" || document.getElementsByName("selDrugId")[i].value==0)
				{
					alert("Please Enter Drug Name");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[i].focus();
					
					return false;
				}
				if(document.getElementsByName("selDoseId")[i].value=="-1")
				{
					alert("Please Enter Dose");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDoseId")[i].focus();
					
					return false;
				}		
				if(document.getElementsByName("selDoseName")[i].value=="")
				{
					alert("Please Enter Dose");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDoseName")[i].focus();
					
					return false;
				}		
				if(document.getElementsByName("selFrequencyId")[i].value=="-1")
				{
					alert("Please Enter Frequecy");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selFrequencyId")[i].focus();
					
					return false;
				}
				if(document.getElementsByName("selDays")[i].value=="" || document.getElementsByName("selDays")[i].value=="0")
				{
					alert("Please Enter Days");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDays")[i].focus();
					
					return false;
				}
				if(document.getElementsByName("selStartDay")[i].value=="")
				{
					alert("Please Enter Start Day");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selStartDay")[i].focus();
					
					return false;
				}
				/*
				if(document.getElementsByName("selDrugRouteId")[i].value=="-1")
				{
					alert("Please Select Drug Route");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugRouteId")[i].focus();
					return false;
				}
				*/
				/*
				if(document.getElementsByName("deskType")[0].value==3 || document.getElementsByName("deskType")[0].value==4)
				{
			
					if(document.getElementsByName("requirmentTimeHrs")[i].value=="")
					{
						alert("Please Enter Hrs");
						setToDesiredTab("tabDrugAdvice");
						document.getElementsByName("requirmentTimeHrs")[i].focus();
						return false;
					}
					
					if(document.getElementsByName("requirmentTimeHrs")[i].value>24)
					{
						alert("Hours can be in 0-23 ");
						setToDesiredTab("tabDrugAdvice");
						document.getElementsByName("requirmentTimeHrs")[i].focus();
						return false;
					}
			
					if(document.getElementsByName("requirmentTimeMin")[i].value=="")
					{
						alert("Please Enter Min");
						setToDesiredTab("tabDrugAdvice");
						document.getElementsByName("requirmentTimeMin")[i].focus();
						return false;
					}
					if(document.getElementsByName("requirmentTimeMin")[i].value>59)
					{
						alert("Minut can be in 0-59");
						setToDesiredTab("tabDrugAdvice");
						document.getElementsByName("requirmentTimeMin")[i].focus();
						return false;
					}
				}
				*/
				
			}
		}
		else
		{
			if(document.getElementsByName("selDrugName")[i].value=="" || document.getElementsByName("selDrugId")[i].value==0)
			{
				alert("Please Enter Drug Name");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDrugName")[i].focus();
				
				return false;
			}
			if(document.getElementsByName("selDoseId")[i].value=="-1")
			{
				alert("Please Enter Dose");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDoseId")[i].focus();
				
				return false;
			}		
			if(document.getElementsByName("selDoseName")[i].value=="")
			{
				alert("Please Enter Dose");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDoseName")[i].focus();
				
				return false;
			}		
			if(document.getElementsByName("selFrequencyId")[i].value=="-1")
			{
				alert("Please Enter Frequecy");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selFrequencyId")[i].focus();
				
				return false;
			}
			if(document.getElementsByName("selDays")[i].value=="" || document.getElementsByName("selDays")[i].value=="0")
			{
				alert("Please Enter Days");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDays")[i].focus();
				
				return false;
			}
			if(document.getElementsByName("selStartDay")[i].value=="-1")
			{
				alert("Please Enter Start Day");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selStartDay")[i].focus();
				return false;
			}
			
			
			/*
			if(document.getElementsByName("selDrugRouteId")[i].value=="-1")
			{
				alert("Please Select Drug Route");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDrugRouteId")[i].focus();
				return false;
			}
			*/
			/*
			if(document.getElementsByName("deskType")[0].value==3 || document.getElementsByName("deskType")[0].value==4)
			{
			
				if(document.getElementsByName("requirmentTimeHrs")[i].value=="")
				{
					alert("Please Enter Hrs");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("requirmentTimeHrs")[i].focus();
					return false;
				}
				if(document.getElementsByName("requirmentTimeHrs")[i].value>24)
				{
					alert("Hours can be in 0-24 ");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("requirmentTimeHrs")[i].focus();
					return false;
				}
			
				if(document.getElementsByName("requirmentTimeMin")[i].value=="")
				{
					alert("Please Enter Min");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("requirmentTimeMin")[i].focus();
					return false;
				}
				if(document.getElementsByName("requirmentTimeMin")[i].value>24)
				{
					alert("Minut can be in 0-59 ");
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("requirmentTimeMin")[i].focus();
					return false;
				}
				
			}
			*/
		}
		
	}
	/*
	//checking duplicate value for Drug Name
	for(var i=0;i<rowCount-1;i++)
	{
		var drugId = document.getElementsByName("selDrugId")[i].value;
		
		for(var j=i+1;j<rowCount;j++)
		{
			if(document.getElementsByName("selDrugId")[j].value==drugId)
			{
				alert(document.getElementsByName("selDrugName")[j].value+" is Already Added");
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDrugName")[j].focus();
				return false;
			}
	     }
	}
	*/
	//checking duplicate value for Drug Name
	//alert(rowCount);
	for(var i=0;i<rowCount-1;i++)
	{
		//alert('33');
		var drugId = document.getElementsByName("selDrugId")[i].value;
		var doseId=document.getElementsByName("selDoseId")[i].value;
		var fequencyId=document.getElementsByName("selFrequencyId")[i].value;
		
		for(var j=i+1;j<rowCount;j++)
		{
			if(document.getElementsByName("selDrugId")[j].value==drugId )
			{
				
				//&& document.getElementsByName("selStartDay")[j].value<document.getElementsByName("selDays")[i].value
				var systemDate = convertStrToDate(document.forms[0].sysDate.value,"dd-MM-yyyy");
				//alert("systemDate "+systemDate);
								
				
				systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDay")[i].value));
				startDate=new Date(systemDate);
				//alert("startDate "+startDate);
				newStartDate=new Date(startDate);
				
				startDate.setDate(startDate.getDate()+parseInt(document.getElementsByName("selDays")[i].value));
				endDate=startDate
				//alert("endDate "+startDate);
				
				var systemDate = convertStrToDate(document.forms[0].sysDate.value,"dd-MM-yyyy");
				//alert("systemDate "+systemDate);
				
				systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDay")[j].value));
				startDateOfNext=new Date(systemDate);
				//alert("startDateOfNext "+startDateOfNext);
				newNextStartDate=new Date(startDateOfNext);
				
				startDateOfNext.setDate(startDateOfNext.getDate()+parseInt(document.getElementsByName("selDays")[j].value));
				endDateOfNext=startDateOfNext
				
				var startDateOfNext = convertDateToStr(newNextStartDate,"dd-Mon-yyyy");
				var endDateOfNext = convertDateToStr(endDateOfNext,"dd-Mon-yyyy");
				var startDate = convertDateToStr(newStartDate,"dd-Mon-yyyy");
				var endDate = convertDateToStr(endDate,"dd-Mon-yyyy");
				
				
				//alert("startDate "+startDate);
				//alert("endDate "+endDate);
				//alert("startDateOfNext "+startDateOfNext);
				//alert("endDateOfNext "+endDateOfNext);
				
				var startday=noOfDays(startDateOfNext,startDate);
				var endDay=noOfDays(endDate,startDateOfNext);
								
				if(startday>0 && endDay>0)
				{
					//alert("1");
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				
				var nextStartday=noOfDays(endDateOfNext,startDate);
				var nextEndDay=noOfDays(endDate,endDateOfNext);
				
				//alert("nextStartday "+nextStartday);
				//alert("nextEndDay "+nextEndDay);
				
				if(nextStartday>0 && nextEndDay>0)
				{
					//alert("2");
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				
				
				var startday=noOfDays(startDate,startDateOfNext);
				var endDay=noOfDays(endDateOfNext,startDate);
								
				if(startday>=0 && endDay>0)
				{	
					//alert("3");
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				
				var nextStartday=noOfDays(endDate,startDateOfNext);
				var nextEndDay=noOfDays(endDateOfNext,endDate);
				
				
				
				if(nextStartday>0 && nextEndDay>0)
				{
					//alert("4");
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				/*
				
				alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDrugName")[j].focus();
				return false;
				*/
			}
			
			if(document.getElementsByName("selDrugId")[j].value==drugId && document.getElementsByName("selDoseId")[j].value==doseId && document.getElementsByName("selFrequencyId")[j].value==fequencyId )
			{
				alert(document.getElementsByName("selDrugName")[j].value+" is Already Added");	
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDrugName")[j].focus();
				return false;
			}
			
		}
	}
	
	//for checking the last record of Diet advice
	if(document.getElementsByName("dietTypeId")[0].value!="-1" || document.getElementsByName("dietRemark")[0].value!="" || document.getElementsByName("dietDays")[0].value!="")
	{
		if(document.getElementsByName("dietTypeId")[0].value=="-1")
		{
			alert("Please Select Diet Type");
			setToDesiredTab("tabDietAdvice");
			document.getElementsByName("dietTypeId")[0].focus();
			return false;
		}
		if(document.getElementsByName("dietDays")[0].value=="")
		{
			alert("Please Enter Days");
			setToDesiredTab("tabDietAdvice");
			document.getElementsByName("dietDays")[0].focus();
			return false;
		}
		if(parseInt(document.getElementsByName("dietDays")[0].value)<=0)
		{
			alert("Please Enter Days (>0)");
			setToDesiredTab("tabDietAdvice");
			document.getElementsByName("dietDays")[0].focus();
			return false;
		}
	}
	
	//for checking the last record of Rest advice
	if(document.getElementsByName("restDays")[0].value!="" || document.getElementsByName("restReason")[0].value!="")
	{
		if(document.getElementsByName("restDays")[0].value=="")
		{
			alert("Please Enter Days");
			setToDesiredTab("tabRestAdvice");
			document.getElementsByName("restDays")[0].focus();
			return false;
		}
		if(parseInt(document.getElementsByName("restDays")[0].value)<=0)
		{
			alert("Please Enter Days (>0)");
			setToDesiredTab("tabRestAdvice");
			document.getElementsByName("restDays")[0].focus();
			return false;
		}
		if(document.getElementsByName("restReason")[0].value=="")
		{
			alert("Please Enter Rest Reason");
			setToDesiredTab("tabRestAdvice");
			document.getElementsByName("restReason")[0].focus();
			return false;
		}
	}
	
	/*
	//validation so that user must select rxContinue or Revoke in case of drug detail
	var len=document.getElementsByName("revoke").length;
	
	for(var i=0;i<len;i++)
	{
		//alert(document.getElementsByName("revoke")[i].checked);
		if(document.getElementsByName("revoke")[i].checked==false)
		{
			//alert("Please Revoke or RxContinue For "+document.getElementsByName("prevDrugName")[i].value);
			alert("Please Select Revoke or RxContinue");
			setToDesiredTab("tabDrugAdvice");
			return false;
		}
	}
	*/
	
	//validation for external treatment Detail
	//alert('validation');
	var rowCountExt = parseInt(document.getElementsByName("extDrugDetailRows")[0].value);
	
	
	for(var i=0;i<rowCountExt;i++)
	{
		if(i==(rowCountExt-1))
		{
			//for checking the last record of External treatment detail
			if(document.getElementsByName("selExtTreatmentName")[i].value!="" ||  document.getElementsByName("selExtFrequencyId")[i].value!="-1" || document.getElementsByName("selExtDays")[i].value!="" || document.getElementsByName("selExtInstructions")[i].value!="")
			{
								
				if(document.getElementsByName("selExtTreatmentId")[i].value=="-1" && document.getElementsByName("selExtTreatmentName")[i].value=="")
				{
					alert("Please Enter Ext Treatment Name");
					setToDesiredTab("tabLAAdvice");
					document.getElementsByName("selExtTreatmentName")[i].focus();
					return false;
				}
				
				if(document.getElementsByName("selExtFrequencyId")[i].value=="-1")
				{
					alert("Please Select Frequency");
					setToDesiredTab("tabLAAdvice");
					document.getElementsByName("selExtFrequencyId")[i].focus();
					return false;
				}	
						
				if(document.getElementsByName("selExtDays")[i].value=="")
				{
					alert("Please Enter Days");
					setToDesiredTab("tabLAAdvice");
					document.getElementsByName("selExtDays")[i].focus();
					
					return false;
				}
				if(parseInt(document.getElementsByName("selExtDays")[i].value)<=0)
				{
					alert("Please Enter Days (>0)");
					setToDesiredTab("tabLAAdvice");
					document.getElementsByName("selExtDays")[i].focus();
					
					return false;
				}
				if(document.getElementsByName("selExtStartDay")[i].value=="")
				{
					alert("Please Enter Start Day");
					setToDesiredTab("tabLAAdvice");
					document.getElementsByName("selExtStartDay")[i].focus();
					return false;
				}
			}
		}
		else
		{
			if(document.getElementsByName("selExtTreatmentId")[i].value=="-1" && document.getElementsByName("selExtTreatmentName")[i].value=="")
			{
				alert("Please Enter Ext Treatment Name");
				setToDesiredTab("tabLAAdvice");
				document.getElementsByName("selExtTreatmentName")[i].focus();
				return false;
			}	
						
			if(document.getElementsByName("selExtFrequencyId")[i].value=="-1")
			{
				alert("Please Enter Frequecy");
				setToDesiredTab("tabLAAdvice");
				document.getElementsByName("selExtFrequencyId")[i].focus();
				return false;
			}
			if(document.getElementsByName("selExtDays")[i].value=="")
			{
				alert("Please Enter Days");
				setToDesiredTab("tabLAAdvice");
				document.getElementsByName("selExtDays")[i].focus();
				return false;
			}
			if(document.getElementsByName("selExtStartDay")[i].value=="")
			{
				alert("Please Enter Start Day");
				setToDesiredTab("tabLAAdvice");
				document.getElementsByName("selExtStartDay")[i].focus();
				return false;
			}
		}
		
	}
	
	/*
	//validation so that user must select rxContinue or Revoke in case of External Treatment detail
	var len=document.getElementsByName("extRevoke").length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("extRevoke")[i].checked==false)
		{
			alert("Please Select Revoke or RxContinue");
			setToDesiredTab("tabLAAdvice");
			return false;
		}
	}
	*/
	
	
	/*
	//cheking drug from prev drug
	
	var selPrevDrugLen=document.getElementsByName("prevDrugName").length;
	var prevDrugName=document.getElementsByName("prevDrugName");
	//alert("prevDrug len"+len);
	
	var selDrugArrayLen=document.getElementsByName("selDrugName").length;
	var drugName=document.getElementsByName("selDrugName");
	//alert(" sel drug len"+len2);
	//alert("prev len-->"+len);
			for(i=0;i<selDrugArrayLen;i++)
			{	
				//alert("prevDrugName-->"+prevDrugName[i].value);
				//alert("selDrugName---> "+drugName);
			
				for(j=0;j<selPrevDrugLen;j++)
				{
					if(drugName[i].value==prevDrugName[j].value)
					{
						alert(drugName[j].value+" Is Alredy Taking By Patient");
						setToDesiredTab("tabDrugAdvice");
						document.getElementsByName("selDrugName")[i].focus();
						return false;
					}
				}
			}
		*/
		
		//cheking drug from prev drug
	
	var len=document.getElementsByName("prevDrugName").length;
	var prevDrugName=document.getElementsByName("prevDrugName");
	//alert("prevDrug len"+len);
	
	var len2=document.getElementsByName("selDrugName").length;
	var drugName=document.getElementsByName("selDrugName");
	//alert("drug len"+len2);
	//alert("len-->"+len);
			for(i=0;i<len2;i++)
			{	
				//alert("prevDrugName-->"+prevDrugName[i].value);
				//alert("selDrugName---> "+drugName);
			
				for(j=0;j<len;j++)
				{
					if(drugName[i].value==prevDrugName[j].value)
					{
							//alert(document.getElementsByName("selStartDate")[j].value);
							//alert(document.getElementsByName("selEndDate")[j].value);
							//alert(document.getElementsByName("sysDate")[0].value);
						
							var systemDateStr = document.getElementsByName("sysDate")[0].value;
							//alert("systemDate "+systemDateStr); 
							
							var prevEndDate = document.getElementsByName("selEndDate")[j].value;
							//alert("prevEndDate "+prevEndDate);
							
							var validateEndDate=noOfDays(prevEndDate,systemDateStr);
							//alert("validateEndDate "+validateEndDate);
							
							if(validateEndDate>=0)
							{
								var systemDate = convertStrToDate(document.forms[0].sysDate.value,"dd-MM-yyyy");
								
								systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDay")[i].value));
								startDate=new Date(systemDate);
								//alert("startDate "+startDate);
								newStartDate=new Date(startDate);
							
								startDate.setDate(startDate.getDate()+(parseInt(document.getElementsByName("selDays")[i].value)-1));
								endDate=startDate
						
								//var prevEndDate = document.getElementsByName("selEndDate")[j].value;
								var prevStartDate = document.getElementsByName("selStartDate")[j].value;
								var startDate = convertDateToStr(newStartDate,"dd-Mon-yyyy");
								var endDate = convertDateToStr(endDate,"dd-Mon-yyyy");
						
								//alert("prevStartDate "+prevStartDate);
								//alert("prevEndDate "+prevEndDate);
								//alert("startDate "+startDate);
								//alert("endDate "+endDate);
								
								
								
								var startday=noOfDays(startDate,prevStartDate);
								var endDay=noOfDays(prevEndDate,startDate);
							
								//	alert("startday "+startday);
								//alert("endDay "+endDay);
							
								if(startday>=0 && endDay>=0)
								{
									//alert("1");
									//alert("j "+j);
									alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									setToDesiredTab("tabDrugAdvice");
									document.getElementsByName("selDrugName")[i].focus();
									return false;
								}
							
								var nextStartday=noOfDays(endDate,prevStartDate);
								var nextEndDay=noOfDays(prevEndDate,endDate);
							
								//alert("nextStartday "+nextStartday);
								//alert("nextEndDay "+nextEndDay);
				
								if(nextStartday>=0 && nextEndDay>=0)
								{
									//alert("2");
									alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									setToDesiredTab("tabDrugAdvice");
									document.getElementsByName("selDrugName")[i].focus();
									return false;
								}
							
								var startday=noOfDays(prevStartDate,startDate);
								var endDay=noOfDays(endDate,prevStartDate);
							
								if(startday>=0 && endDay>=0)
								{
									//alert("3");
									alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									setToDesiredTab("tabDrugAdvice");
									document.getElementsByName("selDrugName")[i].focus();
									return false;
								}
							
									var nextStartday=noOfDays(prevEndDate,startDate);
									var nextEndDay=noOfDays(endDate,prevEndDate);
									
									if(nextStartday>=0 && nextEndDay>=0)
									{
										//alert("4");
										alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
										setToDesiredTab("tabDrugAdvice");
										document.getElementsByName("selDrugName")[i].focus();
										return false;
									}
								
								//alert("drug name "+ drugName[i].value +"prev drug name "+prevDrugName[j].value );
								//alert("dose Id "+document.getElementsByName("selDoseId")[i].value.split("^")[0] +" prev dose id "+document.getElementsByName("previousDoseId")[j].value );
								//alert("Freq Id "+document.getElementsByName("selFrequencyId")[i].value +"prev freq Id "+document.getElementsByName("previousFrequencyId")[j].value);
								
								
								if(drugName[i].value==prevDrugName[j].value && document.getElementsByName("selDoseId")[i].value.split("^")[0]==document.getElementsByName("previousDoseId")[j].value && document.getElementsByName("selFrequencyId")[i].value==document.getElementsByName("previousFrequencyId")[j].value )
								{
									alert(drugName[i].value+" Is Already Taking By Patient ");
									setToDesiredTab("tabDrugAdvice");
									document.getElementsByName("selDrugName")[i].focus();
									return false;
								}
							}				
							
							
						
					}
				}
			}
		
		
	
	//checking Ext Treatment  from prev Treatment
	
	var len=document.getElementsByName("prevSelExtTreatmentName").length;
	var prevExtTreatName=document.getElementsByName("prevSelExtTreatmentName");
	
	var len2=document.getElementsByName("selExtTreatmentName").length;
	var extTreatName=document.getElementsByName("selExtTreatmentName");
	
	//alert("len-->"+len);
		
			for(i=0;i<len2;i++)
			{	
			//alert("prevDrugName-->"+prevDrugName[i].value);
			
				for(j=0;j<len;j++)
				{
					if(extTreatName[i].value==prevExtTreatName[j].value)
					{
						alert(extTreatName[i].value+" Is Alredy Added");
						setToDesiredTab("tabLAAdvice");
						document.getElementsByName("extTreatName")[j].focus();
						return false;
					}
				}
			
			}
		
		
		
	//This is for that if there is no change in any advice for today advice than it will not save
	var saveFlag=false;
	var rowCounter = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	var revoke=document.getElementsByName("revoke"); 
	var len=revoke.length;
	var revokeFlag=false;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("revoke")[i].checked==true)
		{
			revokeFlag=true;
		}
	}
		
	if(rowCounter==1)
	{
		if(document.getElementsByName("selDrugName")[0].value=="" &&  document.getElementsByName("selDoseName")[0].value=="" || document.getElementsByName("selFrequencyId")[0].value=="-1" || document.getElementsByName("selDays")[0].value=="" || document.getElementsByName("selInstructions")[0].valu=="")
		{
			if(revokeFlag!=true)
			{
				saveFlag=true;
			}
		}
	}
	//alert("SaveFlag "+saveFlag);
	
	var extSaveFlag=false;
	var rowCounterExt = parseInt(document.getElementsByName("extDrugDetailRows")[0].value);
	var len=document.getElementsByName("extRevoke").length;
	var extRevokeFlag=false;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("extRevoke")[i].checked==true)
		{
			extRevokeFlag=true;
		}
	}
	if(rowCounterExt==1)
	{
		if(document.getElementsByName("selExtTreatmentName")[0].value=="" &&  document.getElementsByName("selExtFrequencyId")[0].value=="-1" && document.getElementsByName("selExtDays")[0].value=="" && document.getElementsByName("selExtInstructions")[0].value=="")
		{
			if(extRevokeFlag!=true)
			{
				extSaveFlag=true;
			}
		}
	}
	
	//alert("extSaveFlag "+extSaveFlag);
		
	var dietSaveFlag=false;
	if(document.getElementsByName("dietTypeId")[0].value=="-1" && document.getElementsByName("dietRemark")[0].value=="" && document.getElementsByName("dietDays")[0].value=="")
	{
		dietSaveFlag=true;
	}
	//alert("dietSaveFlag "+dietSaveFlag);
	
	
	var restSaveFlag=false;
	if(document.getElementsByName("restDays")[0].value=="" && document.getElementsByName("restReason")[0].value=="")
	{
		restSaveFlag=true;
	}
	//alert("restSaveFlag "+restSaveFlag);
	
	
	var sysDate = document.getElementsByName("sysDateForValidation")[0].value;
	var prescriptionDate = document.getElementsByName("prescriptionDate")[0].value;
	
	// alert("sysDate "+sysDate);
	// alert("prescriptionDate "+prescriptionDate);
	
	if(saveFlag==true && extSaveFlag==true && dietSaveFlag==true && restSaveFlag==true)
	{
		if(prescriptionDate=="")
		{
			alert("Please add some advice");
			return false;
		}
		else
		{
			if(sysDate==prescriptionDate)
			{
				alert("Please do some change in advice");
				return false;
			}
		}
	}
	
	//return false;
		
	//For isEmptyStomach field in drug Advice
	var len=document.getElementsByName("selIsEmptyStomach").length;
	//alert("len  "+len);
	var emptyStomachIndex="";
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("selIsEmptyStomach")[i].checked)
		{	
			emptyStomachIndex=emptyStomachIndex+"1"+"#";
		}
		else
		{
			emptyStomachIndex=emptyStomachIndex+"0"+"#";
		}
	}
	document.getElementsByName("emptyStomachIndexArray")[0].value=emptyStomachIndex;
	//alert("empty stomach Array "+document.getElementsByName("emptyStomachIndexArray")[0].value);
	
	//return false;	
	return true;
}

function submitFormOnValidate(flag,mode)
{

 // alert("cr no  "+document.getElementsByName("patCrNo")[0]);
  //alert("submitFormOnValidate")
 	if(flag)
	{
	 
		submitForm21(mode);
	}
	else{
// 	alert("elesee")
		return false;
	}
	
}

function setToDesiredTab(tabName)
{
	document.getElementsByName("activeTab")[0].value=tabName;
	activateTab(document.getElementById(tabName));	
}



///////////////////////////////////////////////////////////////////
//********************** External treatment functions
var popupListExt =null;
var drugDoseListExt = null;
var selectIndexValExt = -1;
var elemSelTextExt = null;
var selectedRowIndexExt = null;
var selectedDoseRowIndexExt = null;

function getEXTTreatmentDropDownData(eve, obj)
{
	
	//alert("obj "+obj);
	// 13 Enter, 27 Escape, 40 Down Arrow
	if(eve.keyCode!=13 && eve.keyCode!=27 && eve.keyCode!=40)
	{
		// Setting Selected Element
		selectIndexValExt = null;
		
		elemSelTextExt=obj;
		selectedRowIndexExt=findIndex(elemSelTextExt);
		//alert("elemSelTextExt "+elemSelTextExt);
		
		if(obj.value!=" " && obj.value!="")
			sendDataForEXTTreatmentList(obj.value);
			
	}
	if(eve.keyCode==40)
		changeControlExt();
}
		
// Ajax Function 
function sendDataForEXTTreatmentList(searchText)
{	
	//alert("elemSelTextExt "+elemSelTextExt);
	handleExtList(searchText);
	//var url='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=GETEXTTREATMENTLIST&text='+searchText;
	//httpExtListRequest("GET",url,true);
}
/*
function intialExtListReq(reqType,url,isAsynch)
{
	// Specify the function that will handle the HTTP response 
	request.onreadystatechange=handleExtList;
	request.open(reqType,url,isAsynch);
	
	// set the Content-Type header for a POST request 
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	request.send(null);
}

function httpExtListRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		intialExtListReq(reqType,url,asynch);
	}
	else if(window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
			// Unlikely to branch here, as IE uses will be able to use either 
			//one of the constructors
			intialExtListReq(reqType,url,asynch);
		else
			alert("Your browser does not permit the use of all of this application's features!");
	}
	else
		alert("Your browser does not permit the use of all of this application's features!");
}
*/
function sendRequestForExtTreatList(text)
{
	var values = document.getElementsByName("extTreatList")[0].options;
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

function handleExtList(searchText)
{
		//alert("elemSelTextExt "+elemSelTextExt);
		//popupListExt=null;
		//alert("inside handleExtList");
		
		var list = sendRequestForExtTreatList(searchText);
		//alert("list "+list);
		var elemDropDownExt=document.getElementById("cboDropDownExt");
			// Setting Position
			document.getElementById("divDropDownExt").style.display="none";
			//alert("elemSelTextExt "+elemSelTextExt);
			var o=elemSelTextExt;
			//alert("0"+ o);
			var l=0,t=0;
			while(o.offsetParent)
			{
				l+=o.offsetLeft;
				t+=o.offsetTop;
				o=o.offsetParent;
			}
			document.getElementById("divDropDownExt").style.left=l;
			document.getElementById("divDropDownExt").style.top=t+elemSelTextExt.offsetHeight;
			document.getElementById("divDropDownExt").style.pixelWidth=elemSelTextExt.style.pixelWidth;
			
			var optionValueTextArray=list.split('$');
			elemDropDownExt.innerHTML="";
			popupListExt = optionValueTextArray;
			if(optionValueTextArray!="")
			{
				document.getElementById("divDropDownExt").style.display="block";
				for(i=0,j=1;i<optionValueTextArray.length && j<optionValueTextArray.length;i=i+2,j=j+2)
				{
					var op=document.createElement("option");
					op.value=optionValueTextArray[i];
					op.innerHTML=optionValueTextArray[j];
					elemDropDownExt.appendChild(op);
				}
				selectIndexValExt = 0;
				elemDropDownExt.selectedIndex = selectIndexValExt;
			}
			else
			{
				document.getElementById("divDropDownExt").style.display="none";				
				var op=document.createElement("option");
				op.value="-1";
				op.innerHTML="Select Value";
				elemDropDownExt.appendChild(op);
				selectIndexValExt = -1;
			}
}
/*

function handleExtList()
{
	if(request.readyState == 4)
	{
		popupListExt=null;
		if(request.status == 200)
		{
			var elemDropDownExt=document.getElementById("cboDropDownExt");
			// Setting Position
			document.getElementById("divDropDownExt").style.display="none";
			var o=elemSelTextExt;
			var l=0,t=0;
			while(o.offsetParent)
			{
				l+=o.offsetLeft;
				t+=o.offsetTop;
				o=o.offsetParent;
			}
			document.getElementById("divDropDownExt").style.left=l;
			document.getElementById("divDropDownExt").style.top=t+elemSelTextExt.offsetHeight;
			document.getElementById("divDropDownExt").style.pixelWidth=elemSelTextExt.style.pixelWidth;
			
			var optionValueTextArray=request.responseText.split('$');
			elemDropDownExt.innerHTML="";
			popupListExt = optionValueTextArray;
			if(optionValueTextArray!="")
			{
				document.getElementById("divDropDownExt").style.display="block";
				for(i=0,j=1;i<optionValueTextArray.length && j<optionValueTextArray.length;i=i+2,j=j+2)
				{
					var op=document.createElement("option");
					op.value=optionValueTextArray[i];
					op.innerHTML=optionValueTextArray[j];
					elemDropDownExt.appendChild(op);
				}
				selectIndexValExt = 0;
				elemDropDownExt.selectedIndex = selectIndexValExt;
			}
			else
			{
				document.getElementById("divDropDownExt").style.display="none";				
				var op=document.createElement("option");
				op.value="-1";
				op.innerHTML="Select Value";
				elemDropDownExt.appendChild(op);
				selectIndexValExt = -1;
			}
		}
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}//end outer if
}
*/
// Move Control To Drop Down on Down Arrow Press
function changeControlExt()
{
	var elemDropDownExt=document.getElementById("cboDropDownExt");	
	if(typeof elemDropDownExt == 'undefined') return;
	
	var innerValue=elemDropDownExt.options[0].value;
	if(innerValue=="-1")
	{
		document.getElementById("divDropDownExt").style.display="none";
		popupListExt = null;
		selectIndexValExt = -1;
		return;
	}
	selectIndexValExt = -1;
	elemDropDownExt.focus();
}

// Call On Blur of DropDown Sense TextBoxes
function callOnBlurExt()
{
	if(selectIndexValExt==-1) 
	{
		selectIndexValExt = document.getElementById("cboDropDownExt").selectedIndex;
		return;
	}
	var flag = false;
	var arr = popupListExt;
//	if(popupListExt==null || typeof popupListExt == 'undefined' || popupListExt=="" || popupListExt.length<=0 || selectIndexValExt==null)
	//	removeValuesExt();
	//else
		setValuesExt();

	if(document.getElementById("divDropDownExt") && document.getElementById("cboDropDownExt"))
	{
		document.getElementById("divDropDownExt").style.display="none";
	}
	popupListExt = null;
	selectIndexValExt = -1;
	elemSelTextExt = null;
	selectedRowIndexExt = null;
}

function removeValuesExt()
{
	document.getElementsByName("selExtTreatmentId")[selectedRowIndexExt].value = "";
	document.getElementsByName("selExtTreatmentName")[selectedRowIndexExt].value = "";
}


// Setting Value 
function setValuesExt()
{
	if(selectIndexValExt!=null && selectIndexValExt!=-1 && selectedRowIndexExt!=null)
	{
		//alert("inside setvalueext");
		var elemExtTretId = document.getElementsByName("selExtTreatmentId")[selectedRowIndexExt];
		var elemExtTretName = document.getElementsByName("selExtTreatmentName")[selectedRowIndexExt];
		var elemDropDownExt = document.getElementById("cboDropDownExt");
		
		selectIndexValExt = elemDropDownExt.selectedIndex;
		elemExtTretId.value=elemDropDownExt.options[selectIndexValExt].value;
		elemExtTretName.value=elemDropDownExt.options[selectIndexValExt].text;
		//alert("elemExtTretId.value "+ elemExtTretId.value);
		//alert("elemExtTretName.value "+ elemExtTretName.value);
		
		// Drug Dose
		//selectedDoseRowIndexExt = selectedRowIndexExt;			
	}
	else
	{
		var elemExtTretId = document.getElementsByName("selExtTreatmentId")[selectedRowIndexExt];
		var elemExtTretName = document.getElementsByName("selExtTreatmentName")[selectedRowIndexExt];
		var elemDropDownExt = document.getElementById("cboDropDownExt");
		
		if(elemExtTretName.value != "")
		{
			selectIndexValExt = "-1";
			elemExtTretId.value = "";
		}
		//selectedDoseRowIndexExt = selectedRowIndexExt;
	}
}

// On Key Down of Drop Down
function onKeyDownDropExt(eve)
{
	// 9 Tab
	if(eve.keyCode==13 || eve.keyCode==9)
	{
		//alert("insie Key");
		setValuesExt();
		document.getElementById("divDropDownExt").style.display="none";
		document.getElementById("cboDropDownExt").value="";
		elemSelTextExt.focus();
		popupListExt = null;
		selectIndexValExt = -1;
		elemSelTextExt = null;
		selectedRowIndexExt = null;
	}
	if(eve.keyCode==27)
	{
		document.getElementById("divDropDownExt").style.display="none";
		elemSelTextExt.focus();
		popupListExt = null;
		selectIndexValExt = -1;
		elemSelTextExt = null;
		selectedRowIndexExt = null;
	}
}

function onChangeDropExt()
{
	//alert("onChangeDropExt");
	var elemDropDownExt = document.getElementById("cboDropDownExt");
	selectIndexValExt = elemDropDownExt.selectedIndex;
	//alert("selectIndexValExt "+selectIndexValExt);
	elemSelTextExt.value=elemDropDownExt.options[selectIndexValExt].text;
}

function setClickedValueExt()
{
	setValuesExt();
	document.getElementById("divDropDownExt").style.display="none";
	document.getElementById("cboDropDownExt").value="";
	elemSelTextExt.focus();
	popupListExt = null;
	selectIndexValExt = -1;
	elemSelTextExt = null;
	selectedRowIndexExt = null;
}

//**************************************************************
// Common Function
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
