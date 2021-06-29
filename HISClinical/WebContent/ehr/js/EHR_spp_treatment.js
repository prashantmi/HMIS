if (!('contains' in String.prototype)) String.prototype.contains = function (str, startIndex) {
	//alert(str);
	//alert(startIndex);
    return -1 !== String.prototype.indexOf.call(this, str, startIndex);
};



var popupList =null;
var drugDoseList = null;
var selectIndexVal = -1;
var elemSelText = null;
var selectedRowIndex = null;
var selectedDoseRowIndex = null;


function submitAjaxOnValidate()
{
	//if(checkDuplicateDrug())
	//{
	if(validateRows())
	AddRowToTableTreatment();

	//}
}

function checkDuplicateDrug(drugidtocheck)
{
//alert("inside check duplicate");
//alert(drugidtocheck.trim());
var drugIdTrim=drugidtocheck.trim();
//alert(drugIdTrim);
var duplicates = "false";
var tdContent = "";
	
$("#treatmentTable td").each(function() {
tdContent = $(this).text();
//alert($(this).value());
if(tdContent == drugIdTrim)
{
	duplicates = "true";
	
}
}); 


//alert(duplicates);
return duplicates;
}


function AddRowToTableTreatment()
{
	//alert("add");
	if(document.getElementsByName("selDrugId")[0].value!="" &&  document.getElementsByName("selDrugName")[0].value!="")
	{
	
		if(checkDuplicateDrug(document.getElementsByName("selDrugName")[0].value)=="false")
			{
		
	//var resp=document.getElementsByName('comboOptionString')[0].value;
	//var respDiagnosisSite=document.getElementsByName('comboDiagnosisSite')[0].value;
	//alert(resp); alert(respDiagnosisSite);
	var nRow=0;
	var tableObj=document.getElementById('treatmentTable');
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
		tabRow1.id="treatmentRow#"+(parseInt(nRow)+1);
	
		var td1=document.createElement("TD");
		var td2=document.createElement("TD");
		var td3=document.createElement("TD");
		var td4=document.createElement("TD");
		var td5=document.createElement("TD");
		var td6=document.createElement("TD");
		var td7=document.createElement("TD");
		var td8=document.createElement("TD");
		var td9=document.createElement("TD");
		var td10=document.createElement("TD");
		var td11=document.createElement("TD");
	
	
		var tddrugId=document.getElementsByName('selDrugId')[0].value;
		var tddrugItemTypeId=document.getElementsByName('selDrugItemTypeId')[0].value;
		var tddrugName=document.getElementsByName('selDrugName')[0].value;
		var tddoseId=document.getElementsByName('selDoseId')[0].value;
		var tddoseName=document.getElementsByName('selDoseName')[0].value;
		var tdfreqId=document.getElementsByName('selFrequencyId')[0].value;
		var t=document.getElementsByName('selFrequencyId')[0];
		var tdfreqName=t.options[t.selectedIndex].text;
		var tdseldays=document.getElementsByName('selDays')[0].value;
		var tdstartDay=document.getElementsByName('selStartDay')[0].value;
		var tdselInstr=document.getElementsByName('selInstructions')[0].value;
		var tdrxconflg=document.getElementsByName('rxContinueFlag')[0].value;
		var tddrugRouteCombo=document.getElementsByName('selDrugRouteId')[0];
		if(tddrugRouteCombo.selectedIndex=="0")
		{var tddrugRouteName="", tddrugRouteId="";}
		else
		{var tddrugRouteName=tddrugRouteCombo.options[tddrugRouteCombo.selectedIndex].text;
		 var tddrugRouteId=tddrugRouteCombo.options[tddrugRouteCombo.selectedIndex].value;
		}var tdqty=document.getElementsByName('quantity')[0].value;
		var tdadmincombo=document.getElementsByName('selDrugAdminId')[0];
		if(tdadmincombo.selectedIndex=="0")
		{var tdadminName="", tdadminId="";}
		else
		{
		var tdadminName=tdadmincombo.options[tdadmincombo.selectedIndex].text;
		var tdadminId=tdadmincombo.options[tdadmincombo.selectedIndex].value;
		}
		var tdbrandId=document.getElementsByName('selDrugBrandId')[0].value;
		
		
		td1.innerHTML="<div align='center'>"
			+"<input name='selDrugId' value='"+tddrugId+"' type='hidden'></input>"
			+"<input name='selDrugName' value='"+tddrugName+"' type='hidden'></input>"
			+"<input name='selDrugBrandId' value='"+tdbrandId+"' type='hidden'></input>"
			+"<input name='selDrugItemTypeId' value='"+tddrugItemTypeId+"' type='hidden'></input>"
			+"<input name='treatmentRecordStatus' value='2' type='hidden'></input>"
			+tddrugName +"</div>";
																													
		td1.colspan="1";
		
		

		td2.innerHTML="<div align='center'>"
			+"<input name='selDoseId' value='"+tddoseId+"' type='hidden'></input>"
			+"<input name='selDoseName' value='"+tddoseName+"' type='hidden'></input>"
			+tddoseName +"</div>";
																													
		td2.colspan="1";

				
		td3.innerHTML="<div align='center'>"
			+"<input name='selFrequencyId' value='"+tdfreqId+"' type='hidden'></input>"
			+"<input name='selFrequencyName' value='"+tdfreqName+"' type='hidden'></input>"
			+"<input name='rxContinueFlag' value='"+tdrxconflg+"' type='hidden'></input>"
			+tdfreqName +"</div>";
																												
		td3.colspan="1";
		
		td4.innerHTML="<div align='center'>"
			"</div>";
																												
		td4.colspan="1";

		td5.innerHTML="<div align='center'>"
			+"<input name='selDays' value='"+tdseldays+"' type='hidden'></input>"
			+tdseldays +"</div>";
																												
		td5.colspan="1";
		
		
		td6.innerHTML="<div align='center'>"
			+"<input name='selStartDay' value='"+tdstartDay+"' type='hidden'></input>"
			+tdstartDay +"</div>";
																													
		td6.colspan="1";
		
		

		td7.innerHTML="<div align='center'>"
			+"<input name='selDrugAdminId' value='"+tdadminId+"' type='hidden'></input>"
			+"<input name='selDrugAdminName' value='"+tdadminName+"' type='hidden'></input>"
			+tdadminName +"</div>";
																													
		td7.colspan="1";
		
				
		td8.innerHTML="<div align='center'>"
			+"<input name='selDrugRouteId' value='"+tddrugRouteId+"' type='hidden'></input>"
			+"<input name='selDrugRouteName' value='"+tddrugRouteName+"' type='hidden'></input>"
			+tddrugRouteName +"</div>";
																													
		td8.colspan="1";

		td9.innerHTML="<div align='center'>"
		   +"<input name='selInstructions' value='"+tdselInstr+"' type='hidden'></input>"
		   +"<img name='instruction' class='button'  src='/HISClinical/hisglobal/images/icon-vrf.png' onclick ='showDrugInstruction(parseInt(findIndex(this))+1)'/>"
			+"</div>";
																														
		td9.colspan="1";
		

		td10.innerHTML="<div align='center'>"
			+"<input name='quantity' value='"+tdqty+"' type='hidden'></input>"
			+tdqty +"</div>";
																														
		td10.colspan="1";

	
		td11.colspan="1";
		td11.innerHTML="<div align='center'><button type='button' class='btn btn-info btn-sm' style='width:60px;height:28px;' onClick='deleteTreatmentRow("+(parseInt(nRow)+1)+")'>Delete	</div>";
		
		
	   
		tabRow1.appendChild(td1);
		tabRow1.appendChild(td2);
		tabRow1.appendChild(td3);
		tabRow1.appendChild(td4);
		tabRow1.appendChild(td5);
		tabRow1.appendChild(td6);
		tabRow1.appendChild(td7);
		tabRow1.appendChild(td8);
		tabRow1.appendChild(td9);
		tabRow1.appendChild(td10);
		tabRow1.appendChild(td11);
		clearFirstRow();
		
		
	}
		
		else 
		{
			alert("Drug already given");
			clearFirstRow();
		}
	}
}



function deleteTreatmentRow(Str)
{	
	//alert(Str);
	//document.getElementsByName('diagnosisCount')[0].value=(parseInt(document.getElementsByName('diagnosisCount')[0].value) - 1).toString();
	var tableObj=document.getElementById('treatmentTable');
	var temp=document.getElementById("treatmentRow#"+Str);
	//alert(temp);
	tableObj.deleteRow(temp.rowIndex);
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	document.getElementsByName('numberOfRow')[0].value=index-1;
	return true;
}

function hideTreatmentRow(idx)
{	
	//alert(idx);
	var tableObj=document.getElementById('treatmentTable');
	var temp=document.getElementById("treatmentRow#"+(parseInt(idx)+2));
	//alert(parseInt(idx)+2);
	//if(document.getElementsByName('selDrugId')[0].value=="")
	document.getElementsByName('treatmentRecordStatus')[parseInt(idx)+1].value="4";
	//else
	//document.getElementsByName('treatmentRecordStatus')[idx].value="4";
	temp.style.display ="none";
	return true;
}


function clearFirstRow()
{
document.getElementsByName('selDrugId')[0].value="";
document.getElementsByName('selDrugItemTypeId')[0].value="";
document.getElementsByName('selDrugItemTypeId')[0].value="";
document.getElementsByName('selDrugName')[0].value="";
document.getElementsByName('selDoseId')[0].value="";
document.getElementsByName('selDoseName')[0].value="";
document.getElementsByName('selFrequencyId')[0].value="-1";
document.getElementsByName('selDays')[0].value="";
document.getElementsByName('selStartDay')[0].value="0";
document.getElementsByName('selInstructions')[0].value="";
document.getElementsByName('rxContinueFlag')[0].value="";
document.getElementsByName('selDrugRouteId')[0].value="-1";
//document.getElementsByName('selDrugRouteName')[0].value="";
document.getElementsByName('quantity')[0].value="";
document.getElementsByName('selDrugAdminId')[0].value="-1";
//document.getElementsByName('selDrugAdminName')[0].value="";
document.getElementsByName('selDrugBrandId')[0].value="";
document.getElementsByName('treatmentRecordStatus')[0].value="2";
}

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
	selectedRowIndex=0;
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
		//alert(elemDrugId.value);
	//	alert("drugbrandid"+elemDrugBrandId.value);
		//document.getElementsByName("selDrugBrandId")[selectedRowIndex].value=elemDrugBrandId.value;
		elemDrugName.value=elemDropDown.options[selectIndexVal].text;
		// Drug Dose
		selectedDoseRowIndex = selectedRowIndex;
		if(elemPatPregCode != null)
		checkPatPregCategory(elemPatPregCode);
		sendDataForDrugRouteList(elemDrugItemTypeId.value,selectedRowIndex);	
		if(elemDrugAdminId != null)
			selectDataAdminList(elemDrugAdminId,selectedRowIndex);	
		//sendDataForDrugAdminList(elemDrugAdminId);	
		//sendDataForDrugAlerts(elemDrugItemTypeId.value,elemDrugId.value, elemDrugName.value);	
			
	}
	else
		removeValues();
}


function setFreqNamecalculateQuantity(selectedRowIndex)
{
	var t=document.getElementsByName('selFrequencyId')[0];
	var tdfreqName=t.options[t.selectedIndex].text;
	document.getElementsByName('selFrequencyName')[0].value=tdfreqName;
	calculateQuantity(selectedRowIndex);
}

//added by manisha gangwar date: 22.07.2016  
function calculateQuantity(selectedRowIndex)
{
	
	//vo.getDoseId()+"^"+vo.getIsFrequencyBound()+"^"+vo.getDoseQty() + "$" + vo.getDoseName()
	//alert("rowindex"+selectedRowIndex);
var freq=document.getElementsByName("selFrequencyId")[selectedRowIndex].value;
var freqname= document.getElementsByName("selFrequencyId")[selectedRowIndex].text;
var dos=document.getElementsByName("selDoseId")[selectedRowIndex].value;
var days=document.getElementsByName("selDays")[selectedRowIndex].value;
var calcquan="1";


	
//alert (freqname);  
//alert(freq);
//alert("dos"+dos);  alert(days);

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
		
	//	alert(dos);
		if(dos == "0^0^0" || dos == "0" || dos=="undefined")
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
		
        // alert("inside dos");
        //alert("dose"+doseid);  alert("freqbound"+freqbound);  alert("doseqty"+doseqty);
        try
        {
			//if(freqbound==0 || freqbound=='undefined' || doseqty=='undefined')
				//calcquan=1;
							
			//else
				calcquan=freq*doseqty*days;
        }
        catch(e)
        {
			//if(calcquan='undefined')
				//calcquan='1';
        	calcquan=1;
        }
        
			
		}
		
		
		//alert(calcquan);
			
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
	document.getElementById("divDropDown").style.top=t-elemSelText.offsetHeight; //for unipage
	//document.getElementById("divDropDown").style.top=t+elemSelText.offsetHeight;
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
		}
		selectIndexVal = 0;
		elemDropDown.selectedIndex = selectIndexVal;
	}
	else
	{
		document.getElementById("divDropDown").style.display="none";				
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select";
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
	var	patcrNo=document.getElementsByName('patCrNo')[0].value;
	var	departmentUnitCode=document.getElementsByName('departmentUnitCode')[0].value;

	$.ajax({
		//url : '/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=GETDRUGADVICEALERTS',
		url : createFHashAjaxQuery('/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=GETDRUGADVICEALERTS'),
		type : 'GET',
		data : {
			drugItemType : itemType,
			drugId : drugDoseId,
			drugName : drugName,
			patcrNo : patcrNo,
			departmentUnitCode : departmentUnitCode
			
		},
		 datatype: "json",
		 async: false,
		  success : function(data) {
					var obj = eval(data);
		
			var elemDrugId = document.getElementsByName("selDrugId")[selectedDoseRowIndex];
			
			var elemDrugName = document.getElementsByName("selDrugName")[selectedDoseRowIndex];
			
			if(obj != null && obj !='undefined'){
						if (obj.length > 0) {
							for (var i = 0; i < obj.length; i++) {
						
								var summary=document.getElementById('divSummary');
							
								var innerHtml="";	
																
								var tr = getCorrespondingTR(elemDrugId);
								for(var j=0;j<tr.childNodes.length;j++)
									if(tr.childNodes[j].tagName=="TD")
									{	
										
										if(obj[i].statusDrugAllergy > 0)
																					{
											
											tr.childNodes[j].style.backgroundColor = "#FF0000";
											
										}
										else
										{
											tr.childNodes[j].style.backgroundColor = "";
										}
										if(obj[i].statusDrugDupAssign > 0)
										{
											tr.childNodes[j].style.backgroundColor = "#0000A0";
										}
										if(obj[i].statusDrugReaction > 0)
										{
											tr.childNodes[j].style.backgroundColor = "#0000B0";
										}
										
										if(obj[i].statusDrugContradiction > 0)
										{
											tr.childNodes[j].style.backgroundColor = "#0000C0";
										}
									}
								         
										
										var searchText=obj[i].msg;
										var fontcolor="";
										if(i==0 && obj[i].msg!='' )  fontcolor="#FF0000";    //for allergies
										if(i==1 && obj[i].msg!='' )  fontcolor="#0000A0";    //for prev drug duplicate
										if(i==2 && obj[i].msg!='' )  fontcolor="#0000B0";    // for drug Reaction
										if(i==3 && obj[i].msg!='' )  fontcolor="#FF0000";     // for drug contradiction
										
								
										if (document.getElementsByName("summary")[0].value.indexOf(searchText)==-1)
										{																		
											innerHtml+=document.getElementsByName("summary")[0].value+"*<font color="+fontcolor+" size='2'>"+obj[i].msg+"</font><br>";
											summary.innerHTML=innerHtml;
											document.getElementsByName("summary")[0].value=document.getElementsByName("summary")[0].value+"*<font color="+fontcolor+" size='2'>"+obj[i].msg+"</font><br>";
										}
										
									}
								
														
							}
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
	//alert("handleGetSetDrugDoseList");
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
		innerHtml+="<select name='selDoseId' class='form-control' tabindex='1' value='#' onchange='setDoseName("+selectedDoseRowIndex+");calculateQuantity("+selectedDoseRowIndex+");' >\n";
		innerHtml+="<option value='-1'>Select</option>\n";
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
			target.innerHTML="<input type='hidden' name='selDoseId' class='form-control' value='0'/> <input name='selDoseName' tabindex='1' maxlength='20' size='8' onkeypress='return validateAlphaNumOnly(this,event)'/>";					
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
	op.value="-1";
	op.innerHTML="Select";
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
		innerHtml+="<select name='selDoseId' class='form-control'  tabindex='1' value='#' onchange='setDoseName("+index+");calculateQuantity("+index+");' >\n";
		innerHtml+="<option value='-1'>Select</option>\n";
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
			target.innerHTML="<input type='hidden' name='selDoseId' class='form-control' value='0'/> <input name='selDoseName' class='form-control' tabindex='1' maxlength='20' size='8' />";		/*onkeypress='return validateAlphaOnly(this,event)'	*/
	}
}


function setDoseName(index)
{
	//alert("index " + index);
	
	var doseId=document.getElementsByName('selDoseId')[index];
	//alert("doseid"+ doseId.value );
	//alert("doseId.split("^")[1] "+doseId.value.split("^")[1]);
	/*if(doseId.value.split("^")[1]==0)
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
	else
	{
		document.getElementsByName("quantity")[index].value="";
		document.getElementsByName("quantity")[index].readOnly=true;
	}*/
	var doseName=document.getElementsByName('selDoseName')[index];
	
	doseName.value=doseId.options[doseId.selectedIndex].text;
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
	op.value="-1";
	op.innerHTML="Select";
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
	op.innerHTML="Select";
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
function setDrugRouteName(index)
{
	
	var drugRouteId=document.getElementsByName('selDrugRouteId')[index];
	//var drugRouteName=document.getElementsByName('selDrugRouteName')[index];
	document.getElementsByName('selDrugRouteName')[index].value=drugRouteId.options[drugRouteId.selectedIndex].text;
	//alert(drugRouteName.value);
}


function setDrugAdminName(index)
{
	var drugadminId=document.getElementsByName('selDrugAdminId')[index];
	//var drugadminame=document.getElementsByName('selDrugAdminName')[index];
	document.getElementsByName('selDrugAdminName')[index].value=drugadminId.options[drugadminId.selectedIndex].text;
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
	/*var  theForms = document.getElementById("drugAdvice").name; 
	alert(theForms);
	//var drugname = theForms.elements["selDrugName"].value;
	var inputs = document.getElementById("drugAdvice").elements;
	var drugname = inputs["selDrugName"].value;
	var drugid = inputs["selDrugId"].value;
	var doseid = inputs["selDoseId"].value;
	var doseName = inputs["selDoseName"].value;
	var frequencyId = inputs["selFrequencyId"].value;
	var days = inputs["selDays"].value;
	var startday = inputs["selFrequencyId"].value;	
    alert(drugname);*/
	var rowCount = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	for(var i=0;i<rowCount;i++)
	{
		if(document.getElementsByName("selDrugName")[i].value=="" || document.getElementsByName("selDrugId")[i].value==0)
		{
			alert("Please Enter Drug Name");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDrugName")[i].focus();
			return false;
		}
		
		if(document.getElementsByName("selDoseId")[i].value=="-1")
		{
			alert("Please Enter Dose");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDoseId")[i].focus();
			return false;
		}
			
		if(document.getElementsByName("selDoseName")[i].value=="")
		{
			alert("Please Enter Dose");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDoseName")[i].focus();
			return false;
		}	
			
		if(document.getElementsByName("selFrequencyId")[i].value=="-1")
		{
			alert("Please Enter Frequecy");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selFrequencyId")[i].focus();
			return false;
		}
		if(document.getElementsByName("selDays")[i].value=="" || document.getElementsByName("selDays")[i].value=="0")
		{
			alert("Please Enter Days");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDays")[i].focus();
			return false;
		}
		if(document.getElementsByName("selStartDay")[i].value=="")
		{
			alert("Please Enter Start Day");
			//setToDesiredTab("tabDrugAdvice");
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
			
			alert(document.getElementsByName("selDrugId")[j].value);
			alert(document.getElementsByName("selDoseId")[j].value);
			alert(document.getElementsByName("selFrequencyId")[j].value);
			
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
					//setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
				
				var nextStartday=noOfDays(endDateOfNext,startDate);
				var nextEndDay=noOfDays(endDate,endDateOfNext);
				
				
				
				if(nextStartday>0 && nextEndDay>0)
				{
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
				//	setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
					return false;
				}
				
				
				var startday=noOfDays(startDate,startDateOfNext);
				var endDay=noOfDays(endDateOfNext,startDate);
								
				if(startday>=0 && endDay>0)
				{
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
				//	setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
					return false;
				}
				
				var nextStartday=noOfDays(endDate,startDateOfNext);
				var nextEndDay=noOfDays(endDateOfNext,endDate);
				
				
				
				
				if(nextStartday>0 && nextEndDay>0)
				{
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					//setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
					return false;
				}
				
			}
			
			if(document.getElementsByName("selDrugId")[j].value==drugId && document.getElementsByName("selDoseId")[j].value==doseId && document.getElementsByName("selFrequencyId")[j].value==fequencyId )
			{
				alert(document.getElementsByName("selDrugName")[j].value+" is Already Added");	
				//setToDesiredTab("tabDrugAdvice");
				//document.getElementsByName("selDrugName")[j].focus();
				clearFirstRow();
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
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();
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
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();					
									return false;
								}
							
								var startday=noOfDays(prevStartDate,startDate);
								var endDay=noOfDays(endDate,prevStartDate);
							
								if(startday>=0 && endDay>=0)
								{
									//alert("3");
									alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();
									return false;
								}
							
									var nextStartday=noOfDays(prevEndDate,startDate);
									var nextEndDay=noOfDays(endDate,prevEndDate);
									
									if(nextStartday>=0 && nextEndDay>=0)
									{
										//alert("4");
										alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									//	setToDesiredTab("tabDrugAdvice");
										//document.getElementsByName("selDrugName")[i].focus();
										clearFirstRow();
										return false;
									}
								
								//alert("drug name "+ drugName[i].value +"prev drug name "+prevDrugName[j].value );
								//alert("dose Id "+document.getElementsByName("selDoseId")[i].value.split("^")[0] +" prev dose id "+document.getElementsByName("previousDoseId")[j].value );
								//alert("Freq Id "+document.getElementsByName("selFrequencyId")[i].value +"prev freq Id "+document.getElementsByName("previousFrequencyId")[j].value);
								
								
								if(drugName[i].value==prevDrugName[j].value && document.getElementsByName("selDoseId")[i].value.split("^")[0]==document.getElementsByName("previousDoseId")[j].value && document.getElementsByName("selFrequencyId")[i].value==document.getElementsByName("previousFrequencyId")[j].value )
								{
									alert(drugName[i].value+" Is Already Taking By Patient ");
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();
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

// Delete Row 
function deleteRow(row)
{
	var index=parseInt(document.getElementsByName('drugDetailRows')[0].value);
	var elemTable=document.getElementById('tblDrugDetailId');
	elemTable.deleteRow(row.rowIndex);
	document.getElementsByName('drugDetailRows')[0].value = index-1;
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
																													
	td1.colspan="1";
	
	td2.innerHTML="<div align='center'>"+"<input type='text' name='"+ elemDiagnosisName 
	+ "' maxlength='50' size='30' tabindex='1' onkeypress='return validateAlphaNumOnly(this,event)' onkeyup=\"gettext(event,this,'LABLE');\" "
	+ "onblur='callOnBlur()' ></div>";

	td2.colspan="1";
	
	td3.innerHTML="<div align='center'>"
		+ "<select name='diagonisticTypeCode' tabindex='1' onfocus='hideListOnBlur(event)' ><option value='-1'>Select</option>"
		+ resp + "</select></div>";
	
	td3.colspan="1";
	
	td4.innerHTML="<div align='center'>"
		+ "<input type='text' name='remarks' maxlength='50' size='30' tabindex='1' ></div>";
	
	td4.colspan="1";

	

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






function getCorrespondingTR(elem)
{
	var elemTR = elem;
	while(elemTR.tagName!="TR")
		elemTR=elemTR.parentNode;
	return elemTR;
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
					//setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[i].focus();
					
					return false;
				}
				if(document.getElementsByName("selDoseId")[i].value=="-1")
				{
					alert("Please Enter Dose");
				//	setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDoseId")[i].focus();
					
					return false;
				}		
				if(document.getElementsByName("selDoseName")[i].value=="")
				{
					alert("Please Enter Dose");
				//	setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDoseName")[i].focus();
					
					return false;
				}		
				if(document.getElementsByName("selFrequencyId")[i].value=="-1")
				{
					alert("Please Enter Frequecy");
					//setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selFrequencyId")[i].focus();
					
					return false;
				}
				if(document.getElementsByName("selDays")[i].value=="" || document.getElementsByName("selDays")[i].value=="0")
				{
					alert("Please Enter Days");
					//setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDays")[i].focus();
					
					return false;
				}
				if(document.getElementsByName("selStartDay")[i].value=="")
				{
					alert("Please Enter Start Day");
					//setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selStartDay")[i].focus();
					
					return false;
				}
			
				
			}
		}
		else
		{
			if(document.getElementsByName("selDrugName")[i].value=="" || document.getElementsByName("selDrugId")[i].value==0)
			{
				alert("Please Enter Drug Name");
			//	setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDrugName")[i].focus();
				
				return false;
			}
			if(document.getElementsByName("selDoseId")[i].value=="-1")
			{
				alert("Please Enter Dose");
			//	setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDoseId")[i].focus();
				
				return false;
			}		
			if(document.getElementsByName("selDoseName")[i].value=="")
			{
				alert("Please Enter Dose");
			//	setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDoseName")[i].focus();
				
				return false;
			}		
			if(document.getElementsByName("selFrequencyId")[i].value=="-1")
			{
				alert("Please Enter Frequecy");
			//	setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selFrequencyId")[i].focus();
				
				return false;
			}
			if(document.getElementsByName("selDays")[i].value=="" || document.getElementsByName("selDays")[i].value=="0")
			{
				alert("Please Enter Days");
			//	setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDays")[i].focus();
				
				return false;
			}
			if(document.getElementsByName("selStartDay")[i].value=="-1")
			{
				alert("Please Enter Start Day");
			//	setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selStartDay")[i].focus();
				return false;
			}
			
			
			}
		
	}
	
	//checking duplicate value for Drug Name
	for(var i=0;i<rowCount-1;i++)
	{
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
				//	setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
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
					//setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
					return false;
				}
				
				
				var startday=noOfDays(startDate,startDateOfNext);
				var endDay=noOfDays(endDateOfNext,startDate);
								
				if(startday>=0 && endDay>0)
				{	
					//alert("3");
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
				//	setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
					return false;
				}
				
				var nextStartday=noOfDays(endDate,startDateOfNext);
				var nextEndDay=noOfDays(endDateOfNext,endDate);
				
				
				
				if(nextStartday>0 && nextEndDay>0)
				{
					//alert("4");
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added Between Given Time");	
					//setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
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
			//	setToDesiredTab("tabDrugAdvice");
				//document.getElementsByName("selDrugName")[j].focus();
				clearFirstRow();
				return false;
			}
			
		}
	}
	
	
	
	
	
	
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
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();
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
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();
									return false;
								}
							
								var startday=noOfDays(prevStartDate,startDate);
								var endDay=noOfDays(endDate,prevStartDate);
							
								if(startday>=0 && endDay>=0)
								{
									//alert("3");
									alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
								//	setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();
									return false;
								}
							
									var nextStartday=noOfDays(prevEndDate,startDate);
									var nextEndDay=noOfDays(endDate,prevEndDate);
									
									if(nextStartday>=0 && nextEndDay>=0)
									{
										//alert("4");
										alert(document.getElementsByName("selDrugName")[i].value+" is Already Added Between Given Time");	
									//	setToDesiredTab("tabDrugAdvice");
									//	document.getElementsByName("selDrugName")[i].focus();
										
										clearFirstRow();
										return false;
									}
								
								//alert("drug name "+ drugName[i].value +"prev drug name "+prevDrugName[j].value );
								//alert("dose Id "+document.getElementsByName("selDoseId")[i].value.split("^")[0] +" prev dose id "+document.getElementsByName("previousDoseId")[j].value );
								//alert("Freq Id "+document.getElementsByName("selFrequencyId")[i].value +"prev freq Id "+document.getElementsByName("previousFrequencyId")[j].value);
								
								
								if(drugName[i].value==prevDrugName[j].value && document.getElementsByName("selDoseId")[i].value.split("^")[0]==document.getElementsByName("previousDoseId")[j].value && document.getElementsByName("selFrequencyId")[i].value==document.getElementsByName("previousFrequencyId")[j].value )
								{
									alert(drugName[i].value+" Is Already Taking By Patient ");
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();
									return false;
								}
							}				
							
							
						
					}
				}
			}
		
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


//Added by Vasu on 23.July.2019 for treatment given section
function submitAjaxOnValidateForTreatmentGiven()
{
	//if(checkDuplicateDrug())
	//{
	//alert(validateRowsForTreatmentGiven());
	if(validateRowsForTreatmentGiven())
	AddRowToTableTreatmentGiven();

	//}
}


function AddRowToTableTreatmentGiven()
{
	//alert("add");
	if(document.getElementsByName("selDrugIdForTreatmentGiven")[0].value!="" &&  document.getElementsByName("selDrugNameForTreatmentGiven")[0].value!="")
	{
	
		if(checkDuplicateDrugForTreatmentGiven(document.getElementsByName("selDrugNameForTreatmentGiven")[0].value)=="false")
			{
		
	//var resp=document.getElementsByName('comboOptionString')[0].value;
	//var respDiagnosisSite=document.getElementsByName('comboDiagnosisSite')[0].value;
	//alert(resp); alert(respDiagnosisSite);
	var nRow=0;
	var tableObj=document.getElementById('treatmentGivenTable');
	var numRows=tableObj.rows.length;
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id.split("#")[1];
	}
	else
	{
		nRow=numRows;
	}
	
	    //alert(nRow);
	    var tabRow1=tableObj.insertRow(numRows);
		tabRow1.id="treatmentGivenRow#"+(parseInt(nRow)+1);
	
		var td1=document.createElement("TD");
		var td2=document.createElement("TD");
		var td3=document.createElement("TD");
		var td4=document.createElement("TD");
		var td5=document.createElement("TD");
		var td6=document.createElement("TD");
		var td7=document.createElement("TD");
		var td8=document.createElement("TD");
		var td9=document.createElement("TD");
		var td10=document.createElement("TD");
		var td11=document.createElement("TD");
	
	
		var tddrugId=document.getElementsByName('selDrugIdForTreatmentGiven')[0].value;
		var tddrugItemTypeId=document.getElementsByName('selDrugItemTypeIdForTreatmentGiven')[0].value;
		var tddrugName=document.getElementsByName('selDrugNameForTreatmentGiven')[0].value;
		var tddoseId=document.getElementsByName('selDoseIdForTreatmentGiven')[0].value;
		var tddoseName=document.getElementsByName('selDoseNameForTreatmentGiven')[0].value;
		var tdfreqId=document.getElementsByName('selFrequencyIdForTreatmentGiven')[0].value;
		var t=document.getElementsByName('selFrequencyIdForTreatmentGiven')[0];
		var tdfreqName=t.options[t.selectedIndex].text;
		var tdseldays=document.getElementsByName('selDaysForTreatmentGiven')[0].value;
		//var tdstartDay=document.getElementsByName('selStartDay')[0].value;
		var tdstartDay=document.getElementsByName("treatmentStartDate")[0].value;
		//alert(tdstartDay);
		document.getElementsByName('selStartDayForTreatmentGiven')[0].value = tdstartDay;
		var tdselInstr=document.getElementsByName('selInstructions')[0].value;
		var tdrxconflg=document.getElementsByName('rxContinueFlag')[0].value;
		var tddrugRouteCombo=document.getElementsByName('selDrugRouteIdForTreatmentGiven')[0];
		if(tddrugRouteCombo.selectedIndex=="0")
		{var tddrugRouteName="", tddrugRouteId="";}
		else
		{var tddrugRouteName=tddrugRouteCombo.options[tddrugRouteCombo.selectedIndex].text;
		 var tddrugRouteId=tddrugRouteCombo.options[tddrugRouteCombo.selectedIndex].value;
		}var tdqty=document.getElementsByName('quantity')[0].value;
		var tdadmincombo=document.getElementsByName('selDrugAdminIdForTreatmentGiven')[0];
		if(tdadmincombo.selectedIndex=="0")
		{var tdadminName="", tdadminId="";}
		else
		{
		var tdadminName=tdadmincombo.options[tdadmincombo.selectedIndex].text;
		var tdadminId=tdadmincombo.options[tdadmincombo.selectedIndex].value;
		}
		var tdbrandId=document.getElementsByName('selDrugBrandIdForTreatmentGiven')[0].value;
		
		
		td1.innerHTML="<div align='center'>"
			+"<input name='selDrugIdForTreatmentGiven' value='"+tddrugId+"' type='hidden'></input>"
			+"<input name='selDrugNameForTreatmentGiven' value='"+tddrugName+"' type='hidden'></input>"
			+"<input name='selDrugBrandIdForTreatmentGiven' value='"+tdbrandId+"' type='hidden'></input>"
			+"<input name='selDrugItemTypeIdForTreatmentGiven' value='"+tddrugItemTypeId+"' type='hidden'></input>"
			+"<input name='treatmentRecordStatusForTreatmentGiven' value='2' type='hidden'></input>"
			+tddrugName +"</div>";
																													
		td1.colspan="1";
		
		

		td2.innerHTML="<div align='center'>"
			+"<input name='selDoseIdForTreatmentGiven' value='"+tddoseId+"' type='hidden'></input>"
			+"<input name='selDoseNameForTreatmentGiven' value='"+tddoseName+"' type='hidden'></input>"
			+tddoseName +"</div>";
																													
		td2.colspan="1";

				
		td3.innerHTML="<div align='center'>"
			+"<input name='selFrequencyIdForTreatmentGiven' value='"+tdfreqId+"' type='hidden'></input>"
			+"<input name='selFrequencyNameForTreatmentGiven' value='"+tdfreqName+"' type='hidden'></input>"
			+"<input name='rxContinueFlag' value='"+tdrxconflg+"' type='hidden'></input>"
			+tdfreqName +"</div>";
																												
		td3.colspan="1";
		
		td4.innerHTML="<div align='center'>"
			"</div>";
																												
		td4.colspan="1";

		td5.innerHTML="<div align='center'>"
			+"<input name='selDaysForTreatmentGiven' value='"+tdseldays+"' type='hidden'></input>"
			+tdseldays +"</div>";
																												
		td5.colspan="1";
		
		
		td6.innerHTML="<div align='center'>"
			+"<input name='selStartDayForTreatmentGiven' value='"+tdstartDay+"' type='hidden'></input>"
			+tdstartDay +"</div>";
																													
		td6.colspan="1";
		
		

		td7.innerHTML="<div align='center'>"
			+"<input name='selDrugAdminIdForTreatmentGiven' value='"+tdadminId+"' type='hidden'></input>"
			+"<input name='selDrugAdminNameForTreatmentGiven' value='"+tdadminName+"' type='hidden'></input>"
			+tdadminName +"</div>";
																													
		td7.colspan="1";
		
				
		td8.innerHTML="<div align='center'>"
			+"<input name='selDrugRouteIdForTreatmentGiven' value='"+tddrugRouteId+"' type='hidden'></input>"
			+"<input name='selDrugRouteNameForTreatmentGiven' value='"+tddrugRouteName+"' type='hidden'></input>"
			+tddrugRouteName +"</div>";
																													
		td8.colspan="1";

		td9.innerHTML="<div align='center'>"
		   +"<input name='selInstructions' value='"+tdselInstr+"' type='hidden'></input>"
		   +"<img name='instruction' class='button'  src='/HISClinical/hisglobal/images/icon-vrf.png' onclick ='showDrugInstruction(parseInt(findIndex(this))+1)'/>"
			+"</div>";
																														
		td9.colspan="1";
		

		td10.innerHTML="<div align='center'>"
			+"<input name='quantity' value='"+tdqty+"' type='hidden'></input>"
			+tdqty +"</div>";
																														
		td10.colspan="1";

	
		td11.colspan="1";
		td11.innerHTML="<div align='center'><button type='button' class='btn btn-info btn-sm' style='width:60px;height:28px;' onClick='deleteTreatmentRowForTreatmentGiven("+(parseInt(nRow)+1)+")'>Delete	</div>";
		
		
		tabRow1.appendChild(td6);
		tabRow1.appendChild(td5);
		tabRow1.appendChild(td1);
		tabRow1.appendChild(td2);
		tabRow1.appendChild(td3);
		tabRow1.appendChild(td4);
		//tabRow1.appendChild(td5);
		//tabRow1.appendChild(td6);
		tabRow1.appendChild(td7);
		tabRow1.appendChild(td8);
		//tabRow1.appendChild(td9);
		//tabRow1.appendChild(td10);
		tabRow1.appendChild(td11);
		clearFirstRowForTreatmentGiven();
		
		
	}
		
		else 
		{
			alert("Drug already given");
			clearFirstRowForTreatmentGiven();
		}
	}
}


//Added by Vasu on 24.July.2019

function getDrugDropDownDataForTreatmentGiven(eve, obj)
{
	//alert("21");
	// 13 Enter, 27 Escape, 40 Down Arrow
	if(eve.keyCode!=13 && eve.keyCode!=27 && eve.keyCode!=40)
	{
		// Setting Selected Element
		selectIndexVal = null;
		
		elemSelText=obj;
		selectedRowIndex=findIndex(elemSelText);
		
		if(obj.value!=" " && obj.value!="")
			sendDataForDrugListForTreatmentGiven(obj.value);
			
	}
	if(eve.keyCode==40)
		changeControl();
}


function sendDataForDrugListForTreatmentGiven(searchText)
{	
	handleDrugListForTreatmentGiven(searchText);
	//var url='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=GETDRUGLIST&text='+searchText;
	//httpDrugListRequest("GET",url,true);
}


function handleDrugListForTreatmentGiven(searchText)
{
	popupList=null;
	// Getting Data
	var list = sendRequestForDrugListForTreatmentGiven(searchText);

	// Setting Drug Drop Down Position
	var elemDropDown=document.getElementById("cboDropDownForTreatmentGiven");
	document.getElementById("divDropDownForTreatmentGiven").style.display="none";
	var o=elemSelText;
	var l=0,t=0;
	while(o.offsetParent)
	{
		l+=o.offsetLeft;
		t+=o.offsetTop;
		o=o.offsetParent;
	}
	document.getElementById("divDropDownForTreatmentGiven").style.left=l;
	document.getElementById("divDropDownForTreatmentGiven").style.top=t-elemSelText.offsetHeight; //for unipage
	//document.getElementById("divDropDown").style.top=t+elemSelText.offsetHeight;
	document.getElementById("divDropDownForTreatmentGiven").style.pixelWidth=elemSelText.style.pixelWidth;
			
	// Filling Data in DD
	var optionValueTextArray=list.split('$');
	elemDropDown.innerHTML="";
	popupList = optionValueTextArray;
	if(optionValueTextArray!="")
	{
		document.getElementById("divDropDownForTreatmentGiven").style.display="block";
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
		document.getElementById("divDropDownForTreatmentGiven").style.display="none";				
		var op=document.createElement("option");
		op.value="-1";
		op.innerHTML="Select";
		elemDropDown.appendChild(op);
		selectIndexVal = -1;
	}
}


function sendRequestForDrugListForTreatmentGiven(text)
{
	// Getting Drug DD List from "drugList" select object
	var values = document.getElementsByName("drugListForTreatmentGiven")[0].options;
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

function callOnBlurForTreatmentGiven()
{
	if(selectIndexVal==-1) 
	{
		selectIndexVal = document.getElementById("cboDropDownForTreatmentGiven").selectedIndex;
		removeValuesForTreatmentGiven();
		return;
	}
	var flag = false;
	var arr = popupList;
	if(popupList==null || typeof popupList == 'undefined' || popupList=="" || popupList.length<=0 || selectIndexVal==null)
		removeValuesForTreatmentGiven();
	else
		setValuesForTreatmentGiven();

	if(document.getElementById("divDropDownForTreatmentGiven") && document.getElementById("cboDropDownForTreatmentGiven"))
	{
		document.getElementById("divDropDownForTreatmentGiven").style.display="none";
	}
	popupList = null;
	selectIndexVal = -1;
	elemSelText = null;
	selectedRowIndex = null;
}



function removeValuesForTreatmentGiven()
{
	
	document.getElementsByName("selDrugIdForTreatmentGiven")[selectedRowIndex].value = "";
	document.getElementsByName("selDrugItemTypeIdForTreatmentGiven")[selectedRowIndex].value = "";
	document.getElementsByName("selDrugNameForTreatmentGiven")[selectedRowIndex].value = "";
	document.getElementsByName("selDrugBrandIdForTreatmentGiven")[selectedRowIndex].value = "";
}


function setValuesForTreatmentGiven()
{
	selectedRowIndex=0;
	if(selectIndexVal!=null && selectIndexVal!=-1 && selectedRowIndex!=null)
	{
		var elemDrugId = document.getElementsByName("selDrugIdForTreatmentGiven")[selectedRowIndex];
		var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeIdForTreatmentGiven")[selectedRowIndex];
		var elemDrugName = document.getElementsByName("selDrugNameForTreatmentGiven")[selectedRowIndex];
		//alert(elemDrugName);
		var elemDrugAdminId = "";
		var elemPatPregCode = "";
		var elemDrugBrandId = document.getElementsByName("selDrugBrandIdForTreatmentGiven")[selectedRowIndex];
		
		var elemDropDown = document.getElementById("cboDropDownForTreatmentGiven");
		//alert(elemDropDown.value);
		selectIndexVal = elemDropDown.selectedIndex;
		elemDrugId.value=elemDropDown.value.split("#")[0];
		//alert(elemDrugId.value);
		elemDrugItemTypeId.value=elemDropDown.value.split("#")[1];
		elemDrugAdminId=elemDropDown.value.split("#")[2];
		elemPatPregCode=elemDropDown.value.split("#")[3];
		elemDrugBrandId.value=elemDropDown.value.split("#")[4];
		//alert(elemPatPregCode);
		//alert(elemDrugAdminId);
		//alert(elemDrugId.value);
	//	alert("drugbrandid"+elemDrugBrandId.value);
		//document.getElementsByName("selDrugBrandId")[selectedRowIndex].value=elemDrugBrandId.value;
		elemDrugName.value=elemDropDown.options[selectIndexVal].text;
		// Drug Dose
		selectedDoseRowIndex = selectedRowIndex;
		if(elemPatPregCode != null)
		checkPatPregCategory(elemPatPregCode);
		sendDataForDrugRouteListForTreatmentGiven(elemDrugItemTypeId.value,selectedRowIndex);
		if(elemDrugAdminId != null)
			selectDataAdminListForTreatmentGiven(elemDrugAdminId,selectedRowIndex);	
		//sendDataForDrugAdminList(elemDrugAdminId);	
		//sendDataForDrugAlerts(elemDrugItemTypeId.value,elemDrugId.value, elemDrugName.value);	
			
	}
	else
		removeValuesForTreatmentGiven();
}

function validateRowsForTreatmentGiven()
{
	var currDate = document.getElementsByName("entryDate")[0].value;	
	//alert(currDate);
	
	var selectedDate =document.getElementsByName("treatmentStartDate")[0].value;
	//alert(selectedDate);
	var d = new Date(selectedDate);
	//alert(d);
	
	
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear();

if (month.length < 2) month = '0' + month;
if (day.length < 2) day = '0' + day;

const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
                  ];
var mon = monthNames[d.getMonth()];

var treatmentStartDate = [day, mon, year].join('-');

//alert([day, mon, year].join('-'));

//return [year, month, day].join('-');
	
	
	//var selected = convertStrToDate(myDate, 'dd-Mon-yyyy');
	//alert(selected);
	
	var d1 = Date.parse(currDate);
	var d2 = Date.parse(treatmentStartDate);
	
	//alert("d1=="+d1+"==d2=="+d2);
	
	if (d1 < d2) {
        alert ("Treatment Given Date Must be less than or equals Today Date");
 		//document.getElementById("nextVisitDate").focus(); 
 	
 	 return false;
 	}
	
	/*var  theForms = document.getElementById("drugAdvice").name; 
	alert(theForms);*/
	//var drugname = theForms.elements["selDrugName"].value;
	/*var inputs = document.getElementById("drugTreatmentGiven").elements;
	var drugname = inputs["selDrugName"].value;
	var drugid = inputs["selDrugId"].value;
	var doseid = inputs["selDoseId"].value;
	var doseName = inputs["selDoseName"].value;
	var frequencyId = inputs["selFrequencyId"].value;
	var days = inputs["selDays"].value;
	var startday = inputs["selFrequencyId"].value;	
    alert(drugname);*/
	var rowCount = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	for(var i=0;i<rowCount;i++)
	{
		if(document.getElementsByName("selDrugNameForTreatmentGiven")[i].value=="" || document.getElementsByName("selDrugIdForTreatmentGiven")[i].value==0)
		{
			alert("Please Enter Drug Name");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDrugNameForTreatmentGiven")[i].focus();
			return false;
		}
		
		if(document.getElementsByName("selDoseIdForTreatmentGiven")[i].value=="-1")
		{
			alert("Please Enter Dose");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDoseIdForTreatmentGiven")[i].focus();
			return false;
		}
			
		if(document.getElementsByName("selDoseNameForTreatmentGiven")[i].value=="")
		{
			alert("Please Enter Dose");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDoseNameForTreatmentGiven")[i].focus();
			return false;
		}	
			
		if(document.getElementsByName("selFrequencyIdForTreatmentGiven")[i].value=="-1")
		{
			alert("Please Enter Frequecy");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selFrequencyIdForTreatmentGiven")[i].focus();
			return false;
		}
		if(document.getElementsByName("selDaysForTreatmentGiven")[i].value=="" || document.getElementsByName("selDaysForTreatmentGiven")[i].value=="0")
		{
			alert("Please Enter Days");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDaysForTreatmentGiven")[i].focus();
			return false;
		}
		if(document.getElementsByName("treatmentStartDate")[i].value=="")
		{
			alert("Please Enter Start Date");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("treatmentStartDate")[i].focus();
			return false;
		}
	}
	
	for(var i=0;i<rowCount-1;i++)
	{
		var drugId = document.getElementsByName("selDrugIdForTreatmentGiven")[i].value;
		var doseId=document.getElementsByName("selDoseIdForTreatmentGiven")[i].value;
		var fequencyId=document.getElementsByName("selFrequencyIdForTreatmentGiven")[i].value;
		
		for(var j=i+1;j<rowCount;j++)
		{
			
			alert(document.getElementsByName("selDrugIdForTreatmentGiven")[j].value);
			alert(document.getElementsByName("selDoseIdForTreatmentGiven")[j].value);
			alert(document.getElementsByName("selFrequencyIdForTreatmentGiven")[j].value);
			
			if(document.getElementsByName("selDrugIdForTreatmentGiven")[j].value==drugId )
			{
				
				var systemDate = convertStrToDate(document.forms[0].sysDate.value,"dd-MM-yyyy");
								
				systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDayForTreatmentGiven")[i].value));
				startDate=new Date(systemDate);
				
				newStartDate=new Date(startDate);
				
				startDate.setDate(startDate.getDate()+parseInt(document.getElementsByName("selDaysForTreatmentGiven")[i].value));
				endDate=startDate
				
				
				var systemDate = convertStrToDate(document.forms[0].sysDate.value,"dd-MM-yyyy");
				systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDayForTreatmentGiven")[j].value));
				startDateOfNext=new Date(systemDate);
				
				newNextStartDate=new Date(startDateOfNext);
				
				startDateOfNext.setDate(startDateOfNext.getDate()+parseInt(document.getElementsByName("selDaysForTreatmentGiven")[j].value));
				endDateOfNext=startDateOfNext
				
				var startDateOfNext = convertDateToStr(newNextStartDate,"dd-Mon-yyyy");
				var endDateOfNext = convertDateToStr(endDateOfNext,"dd-Mon-yyyy");
				var startDate = convertDateToStr(newStartDate,"dd-Mon-yyyy");
				var endDate = convertDateToStr(endDate,"dd-Mon-yyyy");
				
								
				var startday=noOfDays(startDateOfNext,startDate);
				var endDay=noOfDays(endDate,startDateOfNext);
				
				
								
				if(startday>=0 && endDay>0)
				{
					alert(document.getElementsByName("selDrugNameForTreatmentGiven")[j].value+" is Already Added Between Given Time");	
					//setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugNameForTreatmentGiven")[j].focus();
					return false;
				}
				
				var nextStartday=noOfDays(endDateOfNext,startDate);
				var nextEndDay=noOfDays(endDate,endDateOfNext);
				
				
				
				if(nextStartday>0 && nextEndDay>0)
				{
					alert(document.getElementsByName("selDrugNameForTreatmentGiven")[j].value+" is Already Added Between Given Time");	
				//	setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
					return false;
				}
				
				
				var startday=noOfDays(startDate,startDateOfNext);
				var endDay=noOfDays(endDateOfNext,startDate);
								
				if(startday>=0 && endDay>0)
				{
					alert(document.getElementsByName("selDrugNameForTreatmentGiven")[j].value+" is Already Added Between Given Time");	
				//	setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
					return false;
				}
				
				var nextStartday=noOfDays(endDate,startDateOfNext);
				var nextEndDay=noOfDays(endDateOfNext,endDate);
				
				
				
				
				if(nextStartday>0 && nextEndDay>0)
				{
					alert(document.getElementsByName("selDrugNameForTreatmentGiven")[j].value+" is Already Added Between Given Time");	
					//setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[j].focus();
					clearFirstRow();
					return false;
				}
				
			}
			
			if(document.getElementsByName("selDrugIdForTreatmentGiven")[j].value==drugId && document.getElementsByName("selDoseIdForTreatmentGiven")[j].value==doseId && document.getElementsByName("selFrequencyIdForTreatmentGiven")[j].value==fequencyId )
			{
				alert(document.getElementsByName("selDrugNameForTreatmentGiven")[j].value+" is Already Added");	
				//setToDesiredTab("tabDrugAdvice");
				//document.getElementsByName("selDrugName")[j].focus();
				clearFirstRow();
				return false;
			}
			
		}
	}
	
	
	
	//cheking drug from prev drug
	
	var len=document.getElementsByName("prevDrugNameForTreatmentGiven").length;
	var prevDrugName=document.getElementsByName("prevDrugNameForTreatmentGiven");
	
	
	var len2=document.getElementsByName("selDrugNameForTreatmentGiven").length;
	var drugName=document.getElementsByName("selDrugNameForTreatmentGiven");
	
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
								
								systemDate.setDate(systemDate.getDate()+parseInt(document.getElementsByName("selStartDayForTreatmentGiven")[i].value));
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
								
								startDate.setDate(startDate.getDate()+(parseInt(document.getElementsByName("selDaysForTreatmentGiven")[i].value)-1));
								endDate=startDate	
						
								//var prevEndDate = document.getElementsByName("selEndDate")[j].value;
								//var prevStartDate = document.getElementsByName("selStartDayForTreatmentGiven")[j].value;
								var prevStartDate = "";
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
									alert(document.getElementsByName("selDrugNameForTreatmentGiven")[i].value+" is Already Added Between Given Time");	
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRowForTreatmentGiven();
									return false;
								}
							
								var nextStartday=noOfDays(endDate,prevStartDate);
								var nextEndDay=noOfDays(prevEndDate,endDate);
							
								//alert("nextStartday "+nextStartday);
								//alert("nextEndDay "+nextEndDay);
				
								if(nextStartday>=0 && nextEndDay>=0)
								{
									//alert("2");
									alert(document.getElementsByName("selDrugNameForTreatmentGiven")[i].value+" is Already Added Between Given Time");	
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRowForTreatmentGiven();					
									return false;
								}
							
								var startday=noOfDays(prevStartDate,startDate);
								var endDay=noOfDays(endDate,prevStartDate);
							
								if(startday>=0 && endDay>=0)
								{
									//alert("3");
									alert(document.getElementsByName("selDrugNameForTreatmentGiven")[i].value+" is Already Added Between Given Time");	
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRowForTreatmentGiven();
									return false;
								}
							
									var nextStartday=noOfDays(prevEndDate,startDate);
									var nextEndDay=noOfDays(endDate,prevEndDate);
									
									if(nextStartday>=0 && nextEndDay>=0)
									{
										//alert("4");
										alert(document.getElementsByName("selDrugNameForTreatmentGiven")[i].value+" is Already Added Between Given Time");	
									//	setToDesiredTab("tabDrugAdvice");
										//document.getElementsByName("selDrugName")[i].focus();
										clearFirstRowForTreatmentGiven();
										return false;
									}
								
								//alert("drug name "+ drugName[i].value +"prev drug name "+prevDrugName[j].value );
								//alert("dose Id "+document.getElementsByName("selDoseId")[i].value.split("^")[0] +" prev dose id "+document.getElementsByName("previousDoseId")[j].value );
								//alert("Freq Id "+document.getElementsByName("selFrequencyId")[i].value +"prev freq Id "+document.getElementsByName("previousFrequencyId")[j].value);
								
								
								/*if(drugName[i].value==prevDrugName[j].value && document.getElementsByName("selDoseIdForTreatmentGiven")[i].value.split("^")[0]==document.getElementsByName("previousDoseId")[j].value && document.getElementsByName("selFrequencyIdForTreatmentGiven")[i].value==document.getElementsByName("previousFrequencyId")[j].value )
								{
									alert(drugName[i].value+" Is Already Taking By Patient ");
									//setToDesiredTab("tabDrugAdvice");
									//document.getElementsByName("selDrugName")[i].focus();
									clearFirstRow();
									return false;
								}*/
								
									if(drugName[i].value==prevDrugName[j].value)
									{
										alert(drugName[i].value+" Is Already Taking By Patient ");
										//setToDesiredTab("tabDrugAdvice");
										//document.getElementsByName("selDrugName")[i].focus();
										clearFirstRowForTreatmentGiven();
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


function calculateQuantityForTreatmentGiven(selectedRowIndex)
{
	//alert("in calculateQuantityForTreatmentGiven");
	//vo.getDoseId()+"^"+vo.getIsFrequencyBound()+"^"+vo.getDoseQty() + "$" + vo.getDoseName()
	//alert("rowindex"+selectedRowIndex);
var freq=document.getElementsByName("selFrequencyIdForTreatmentGiven")[selectedRowIndex].value;
var freqname= document.getElementsByName("selFrequencyIdForTreatmentGiven")[selectedRowIndex].text;
var dos=document.getElementsByName("selDoseIdForTreatmentGiven")[selectedRowIndex].value;
var days=document.getElementsByName("selDaysForTreatmentGiven")[selectedRowIndex].value;
var calcquan="1";


	
//alert (freqname);  
//alert(freq);
//alert("dos"+dos); 
//alert(days);

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
		
	//	alert(dos);
		if(dos == "0^0^0" || dos == "0" || dos=="undefined")
		{
		dos=document.getElementsByName("selDoseNameForTreatmentGiven")[selectedRowIndex].value;
		
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
		
        // alert("inside dos");
      //  alert("dose"+doseid);  alert("freqbound"+freqbound);  alert("doseqty"+doseqty);
			if(freqbound==0 || freqbound=='undefined' || doseqty=='undefined')
				calcquan=1;
							
			else
				calcquan=freq*doseqty*days;
			
			if(calcquan='undefined')
				calcquan='1';
		}
		
		
		//alert(calcquan);
			
			document.getElementsByName("quantity")[selectedRowIndex].value=calcquan;
	}
	
	

}


function fillDataForTreatmentGiven(index)
{
	//alert("drugRouteId"+ document.getElementsByName("selDrugRouteId")[index].value);
	//alert("doseId "+document.getElementsByName("selDoseId")[index].value);
	var drugRouteId=document.getElementsByName("selDrugRouteIdForTreatmentGiven")[index].value;
	var doseId=document.getElementsByName("selDoseIdForTreatmentGiven")[index].value;
	//alert("index "+index);
	//alert(document.getElementsByName("selDrugItemTypeId")[index].value);
	_itmeTypeId=document.getElementsByName("selDrugItemTypeIdForTreatmentGiven")[index].value;
	//alert("_itmeTypeId "+_itmeTypeId)
	
	
	var elemRouteCombo = document.getElementsByName("selDrugRouteIdForTreatmentGiven")[index];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="-1";
	op.innerHTML="Select";
	elemRouteCombo.appendChild(op);
	
			
	// Filling Route Data
	var opts = document.getElementsByName("drugRouteListForTreatmentGiven")[0].options;
	//alert(opts);
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
	
	
	
	var elemDrugItemTypeId = document.getElementsByName("selDrugItemTypeIdForTreatmentGiven")[index];
	var elemDoseCbo = document.getElementsByName("selDoseIdForTreatmentGiven")[index];
	var target = elemDoseCbo.parentNode;

	// Filling Dose Data
	var opts = document.getElementsByName("drugDoseListForTreatmentGiven")[0].options;
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
		innerHtml+="<input type='hidden' name='selDoseNameForTreatmentGiven'/>\n";
		innerHtml+="<select name='selDoseIdForTreatmentGiven' class='form-control'  tabindex='1' value='#' onchange='setDoseNameForTreatmentGiven("+index+")' >\n";
		innerHtml+="<option value='-1'>Select</option>\n";
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
		document.getElementsByName("selDoseIdForTreatmentGiven")[index].value = val;
		setDoseNameForTreatmentGiven(index);
	}
	else
	{			
		if(elemDoseCbo.tagName!="INPUT")
			target.innerHTML="<input type='hidden' name='selDoseIdForTreatmentGiven' class='form-control' value='0'/> <input name='selDoseNameForTreatmentGiven' class='form-control' tabindex='1' maxlength='20' size='8' />";		/*onkeypress='return validateAlphaOnly(this,event)'	*/
	}
}


function setDoseNameForTreatmentGiven(index)
{
	//alert("index setDoseNameForTreatmentGiven" + index);
	
	var doseId=document.getElementsByName('selDoseIdForTreatmentGiven')[index];
	
	var doseName=document.getElementsByName('selDoseNameForTreatmentGiven')[index];
	
	doseName.value=doseId.options[doseId.selectedIndex].text;
}

function setFreqNamecalculateQuantityForTreatmentGiven(selectedRowIndex)
{
	var t=document.getElementsByName('selFrequencyIdForTreatmentGiven')[0];
	var tdfreqName=t.options[t.selectedIndex].text;
	document.getElementsByName('selFrequencyNameForTreatmentGiven')[0].value=tdfreqName;
	calculateQuantityForTreatmentGiven(selectedRowIndex);
}


function sendDataForDrugRouteListForTreatmentGiven(itemType,index)
{
	handleGetSetDrugRouteListForTreatmentGiven(itemType,index);
	
}
function handleGetSetDrugRouteListForTreatmentGiven(_itmeTypeId,_index)
{
	var idx = selectedDoseRowIndex;
	if(_index) idx = _index;
	var elemRouteCombo = document.getElementsByName("selDrugRouteIdForTreatmentGiven")[idx];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="-1";
	op.innerHTML="Select";
	elemRouteCombo.appendChild(op);

	// Filling Route Data
	var opts = document.getElementsByName("drugRouteListForTreatmentGiven")[0].options;
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
	var drugRouteName=document.getElementsByName('selDrugRouteNameForTreatmentGiven')[idx];
	drugRouteName.value=elemRouteCombo.options[1].text;
		//alert(drugRouteName.value);
	}

	
	
}



function selectDataAdminListForTreatmentGiven(elemDrugAdminId,index)
{
	var drugAdminlist = document.getElementsByName("selDrugAdminIdForTreatmentGiven")[index].options;
	
	
	for(var i=0; i<drugAdminlist.length; i++)
	{
		
		if(drugAdminlist[i].value ==elemDrugAdminId)
		{
	         	drugAdminlist.selectedIndex = i;
           break;
        }
    }
}


function clearFirstRowForTreatmentGiven	()
{
document.getElementsByName('selDrugIdForTreatmentGiven')[0].value="";
document.getElementsByName('selDrugItemTypeIdForTreatmentGiven')[0].value="";
document.getElementsByName('selDrugItemTypeIdForTreatmentGiven')[0].value="";
document.getElementsByName('selDrugNameForTreatmentGiven')[0].value="";
document.getElementsByName('selDoseIdForTreatmentGiven')[0].value="";
document.getElementsByName('selDoseNameForTreatmentGiven')[0].value="";
document.getElementsByName('selFrequencyIdForTreatmentGiven')[0].value="-1";
document.getElementsByName('selDaysForTreatmentGiven')[0].value="";
document.getElementsByName('selStartDayForTreatmentGiven')[0].value="0";
document.getElementsByName('selInstructions')[0].value="";
document.getElementsByName('rxContinueFlag')[0].value="";
document.getElementsByName('selDrugRouteIdForTreatmentGiven')[0].value="-1";
//document.getElementsByName('selDrugRouteName')[0].value="";
document.getElementsByName('quantity')[0].value="";
document.getElementsByName('selDrugAdminIdForTreatmentGiven')[0].value="-1";
//document.getElementsByName('selDrugAdminName')[0].value="";
document.getElementsByName('selDrugBrandIdForTreatmentGiven')[0].value="";
document.getElementsByName('treatmentRecordStatusForTreatmentGiven')[0].value="2";
}

function deleteTreatmentRowForTreatmentGiven(Str)
{	
	//alert(Str);
	//document.getElementsByName('diagnosisCount')[0].value=(parseInt(document.getElementsByName('diagnosisCount')[0].value) - 1).toString();
	var tableObj=document.getElementById('treatmentGivenTable');
	var temp=document.getElementById("treatmentGivenRow#"+Str);
	//alert(temp);
	tableObj.deleteRow(temp.rowIndex);
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	document.getElementsByName('numberOfRow')[0].value=index-1;
	return true;
}

function hideTreatmentRowForTreatmentGiven(idx)
{	
	//alert(idx);
	var tableObj=document.getElementById('treatmentGivenTable');
	var temp=document.getElementById("treatmentGivenRow#"+(parseInt(idx)+2));
	//alert(parseInt(idx)+2);
	//if(document.getElementsByName('selDrugId')[0].value=="")
	document.getElementsByName('treatmentRecordStatusForTreatmentGiven')[parseInt(idx)+1].value="4";
	//else
	//document.getElementsByName('treatmentRecordStatus')[idx].value="4";
	temp.style.display ="none";
	return true;
}

function checkDuplicateDrugForTreatmentGiven(drugidtocheck)
{
//alert("inside check duplicate");
//alert(drugidtocheck.trim());
var drugIdTrim=drugidtocheck.trim();
//alert(drugIdTrim);
var duplicates = "false";
var tdContent = "";
	
$("#treatmentGivenTable td").each(function() {
tdContent = $(this).text();
//alert($(this).value());
if(tdContent == drugIdTrim)
{
	duplicates = "true";
	
}
}); 


//alert(duplicates);
return duplicates;
}


function validate_ENC_MED_ADV_DRUG()
{
  //alert("validate treatment Given");

  var drugName = document.getElementsByName("selDrugNameForTreatmentGiven")[0].value;

  if(drugName!="")
	  {
         alert("Please click add button to add Treatment Given");
         document.getElementById("addButtonForTreatmentGiven").focus();
         return false;
	  }
  else
	 {
		 
          return true;
      }
	}
