// Desk Episode Diagnosis Tab Javascript

var elemSelText = null;
var popupList =null;
var elemDiagnosisCode = null;
var elemDiagnosisName = null;
var selectIndexVal = -1;
var selectedRowIndex=null;

var elemDiseaseNameCodeList = null;
var elemDiseaseCodeNameList = null;

/**Added by Vasu on 25.Oct.2018*/
var elemDiseaseSiteCodeList = null;
var elemDiseaseSiteNameList = null;
var elemMorphologyCodeList = null;
var elemMorphologyNameList = null;
var elemDiseaseSiteId = null;


window.onload = function()
{
	//alert("unitDiagnosisCode:"+document.getElementsByName("unitDiagnosisCodeType")[0].value);
	if(typeof document.getElementsByName("icdNHospitalFlag")[0] == 'undefined')
	{	
		//alert("undefined");
		if(document.getElementsByName("unitDiagnosisCodeType")[0].value==0)
		{
			elemDiagnosisCode = "icdCode";
			elemDiagnosisName = "dignosisName";
		}
		else if(document.getElementsByName("unitDiagnosisCodeType")[0].value==1)
		{
			elemDiagnosisCode = "hospitalDiagnosisCode";
			elemDiagnosisName = "hospitalDiagnosisName";
		}
		
		else if(document.getElementsByName("unitDiagnosisCodeType")[0].value==4)
				{
			//document.getElementsByName("icdNHospitalFlag")[0].value="3";
			//document.getElementsByName("icdNHospitalFlagValue")[0].value="3";
			//alert('inside snomd');
			load1('2','BODY_STRUCTURE');
			load1('1','DISORDER');
			elemDiagnosisCode = "snomedCTDiagnosisSiteCode";
			elemDiagnosisName = "txt-snomed-ct-search_1";
			document.getElementsByName("snomedCTDiagnosisSiteName")[0].value="";
			document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value="";
			
				}	
		
	}	
	else
	{
		//alert("defined");
		/*alert(document.getElementsByName("icdNHospitalFlag")[0].checked);
		alert(document.getElementsByName("icdNHospitalFlag")[1].checked);
		alert(document.getElementsByName("icdNHospitalFlag")[2].checked);*/
		
		if(document.getElementsByName("icdNHospitalFlag")[0].checked==true)
		{
			elemDiagnosisCode = "icdCode";
			elemDiagnosisName = "dignosisName";
			document.getElementsByName("icdNHospitalFlagValue")[0].value="0";
			//showIdcHospitalDiagnosis();
		}
		else if(document.getElementsByName("icdNHospitalFlag")[1].checked==true)
		{
			/*Modified by Vasu on 24.Oct.2018 for ICD-O Integration
			 * elemDiagnosisCode = "hospitalDiagnosisCode";
			elemDiagnosisName = "hospitalDiagnosisName";
		
			document.getElementsByName("icdNHospitalFlagValue")[0].value="1";*/ 
			//showIdcHospitalDiagnosis();
			
			document.getElementsByName("icdNHospitalFlagValue")[0].value="4";
			//alert('inside snomd');
			elemDiagnosisCode = "snomedCTDiagnosisSiteCode";
			elemDiagnosisName = "txt-snomed-ct-search_1";
			load1('2','BODY_STRUCTURE');
			load1('1','DISORDER');
			document.getElementsByName("snomedCTDiagnosisSiteName")[0].value="";
			document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value="";
			
			document.getElementById('searchICD').style.display= "none";
			
		}
		else if(document.getElementsByName("icdNHospitalFlag")[2].checked==true)
		{
			/*document.getElementsByName("icdNHospitalFlagValue")[0].value="4";
			alert('inside snomd');
			elemDiagnosisCode = "snomedCTDiagnosisSiteCode";
			elemDiagnosisName = "txt-snomed-ct-search_1";
			load1('2','BODY_STRUCTURE');
			load1('1','DISORDER');
			document.getElementsByName("snomedCTDiagnosisSiteName")[0].value="";
			document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value="";*/
			
			document.getElementsByName("icdNHospitalFlagValue")[0].value="3";
			elemDiagnosisCode = "diseaseCode";
			elemDiagnosisName = "diseaseSite";
			elemDiseaseSiteId = "diseaseSiteId";
		}
		
	}	
	//alert(elemDiagnosisCode+"  "+elemDiagnosisName);
	disabledAddDiagnosis();
    //alert("hi");
	setDIagnosticTypeCode1();
	
	
}



function snomd()
{
	clearFormOnSubmit();
    document.getElementsByName("icdNHospitalFlag")[0].value="4";
	document.getElementsByName("icdNHospitalFlagValue")[0].value="4";
	
	document.getElementById('searchICD').style.display= "none";
	
}

function clearFormOnSubmit()
{
	document.getElementsByName("comboOptionString")[0].value="";
	document.getElementsByName("comboDiagnosisSite")[0].value="";
}

function load1(elmtId,semantictag)
{

	
	//alert("inside load");
	//alert(elmtId+','+semantictag);
	if(elmtId=="4")
	{
	var callbck_index4 =function(ret_OUT){setValue4(ret_OUT);};
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);
	}
	else
		{
	if(elmtId=="1")
		{
		var callbck_index =function(ret_OUT){setValue1(ret_OUT);};
		}
	if(elmtId=="2")
		{
		var callbck_index =function(ret_OUT){setValue2(ret_OUT);};
		}
	
	
	
	var semantictag_IN;
	var acceptability_IN;
	var state_IN ;
	var returnlimit_IN;
	var retterm_value ={};
	if(elmtId==null || elmtId==undefined)
		{
		elmtId="1"; semantictag="DISORDER";
	
		}
	//elmtId="2";
	//alert(elmtId);
	
	selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null', elmtId,callbck_index);

		}
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	$("#conceptdiv_4").hide();
	$("#norecorddiv_4").hide();
	

	
}
	
	


//setting diagnostic type "Provisional" as default selected date : 18.7.2016 by Manisha gangwar
function  setDIagnosticTypeCode1()
{
	
	var elmt=document.getElementsByName('diagonisticTypeCode')[0];
	//alert(elmt);
    for(var i=0;i<elmt.length;i++)
    	{
    	if(elmt.options[i].text=="Provisional")
    		{
    		elmt.selectedIndex=i;
    	break;
    		}
           
    	}
    
}
function setSerachDropDownLists()
{
	//alert("inside setSerachDropDownLists()");
	var deskFrame=parent.document.getElementById("frmDynamicDeskFooter");
	//alert(deskFrame);
	var doc="";
	if(window.XMLHttpRequest)
		doc=deskFrame.contentDocument;
	else if (window.ActiveXObject)
		doc=deskFrame.Document;
	
	elemDiseaseNameCodeList = doc.getElementsByName('diseaseNameCodeList')[0];
	elemDiseaseCodeNameList = doc.getElementsByName('diseaseCodeNameList')[0];
	//alert(elemDiseaseNameCodeList.options.length);
	
	//alert(elemDiseaseCodeNameList.options.length);
	/**Added by Vasu on 25.Oct.2018 for ICD-O Integration*/
	elemDiseaseSiteCodeList = doc.getElementsByName('diagnosisSiteNameCodeList')[0];
	//alert(elemDiseaseSiteCodeList);
	elemDiseaseSiteNameList = doc.getElementsByName('dignosisSiteCodeNameList')[0];
	//alert(elemDiseaseSiteNameList);
	elemMorphologyCodeList = doc.getElementsByName('morphologyNameCodeList')[0];
	//alert(elemMorphologyCodeList);
	elemMorphologyNameList = doc.getElementsByName('morphologyCodeNameList')[0];
	//alert(elemMorphologyNameList);
	
	
}

function setHosSerachDropDownLists()
{
	elemDiseaseNameCodeList = document.getElementsByName("diseaseNameCodeList")[0];
	elemDiseaseCodeNameList = document.getElementsByName("diseaseCodeNameList")[0];
}

function disabledAddDiagnosis()
{
	var len=document.getElementsByName('addDiagnosis').length;
	for(var i=0;i<len;i++)
	{
		document.getElementsByName('addDiagnosis')[i].disabled = true;
	}
}

function selectDeselectPrev(chk)
{
	var elemRem = document.getElementsByName('addDiagnosisRemarks')[chk.value]; 
	var elemCanRem = document.getElementsByName('addDiagnosisCancelRemarks')[chk.value];
	if(chk.checked)
	{
		document.getElementsByName('addDiagnosisCode')[chk.value].disabled = false;
		document.getElementsByName('addDiagnosticTypeCode')[chk.value].disabled = false;
		//Enable Modify Remarks
		elemRem.disabled = false;
		elemRem.parentNode.style.display = "";
		//Disable Cancel Remarks
		elemCanRem.disabled = true;
		elemCanRem.parentNode.style.display = "none";
		/*var flg = false;
		for(var i=0;i<document.getElementsByName('chkPrev').length;i++)
			if(!document.getElementsByName('chkPrev')[i].checked)
			{
				flg = true;
				break;
			}
		if(!flg)	document.getElementById("fntStar").innerHTML="";*/
		
		document.getElementsByName('addDiagnosisSite')[chk.value].disabled = false;
		document.getElementsByName('addDiagnosisCodeType')[chk.value].disabled = false;
		document.getElementsByName('addDiagnosis')[chk.value].disabled = true;
	}
	else
	{
		document.getElementsByName('addDiagnosisCode')[chk.value].disabled = true;
		document.getElementsByName('addDiagnosticTypeCode')[chk.value].disabled = true;
		//Enable Modify Remarks
		elemRem.disabled = true;
		elemRem.parentNode.style.display = "none";
		//Disable Cancel Remarks
		elemCanRem.disabled = false;
		elemCanRem.parentNode.style.display = "";
		//document.getElementById("fntStar").innerHTML="*";
		document.getElementsByName('addDiagnosisSite')[chk.value].disabled = true;
		document.getElementsByName('addDiagnosisCodeType')[chk.value].disabled = true;
		document.getElementsByName('addDiagnosis')[chk.value].disabled = false;
	}
}

//********* Selected TextBox Functions
// Getting AJAX Data
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

function setCorrICDCodeName(elemTarget)
{
	var rowIndex = findIndex(elemTarget);

	var elemDiagCode = document.getElementsByName(elemDiagnosisCode)[rowIndex];
	var elemDiagName = document.getElementsByName(elemDiagnosisName)[rowIndex];


	// Set Data
	if(elemTarget.name==elemDiagCode.name)
	{
		if(elemTarget.SELECTED_VALUE!=null)
			elemDiagName.value = elemTarget.SELECTED_VALUE;
		else
		{
			elemDiagCode.value = "";
			elemDiagName.value = "";
		}
	}
	else if(elemTarget.name==elemDiagName.name)
	{
		if(elemTarget.SELECTED_VALUE!=null)
			elemDiagCode.value = elemTarget.SELECTED_VALUE;
		else
		{
			elemDiagCode.value = "";
			elemDiagName.value = "";
		}
	}
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


//*********** Other
// Not in Use
function hideListOnBlur(eve)
{
	document.getElementById("sid").style.display="none";
}
// Not in use
function chekpos(event)
{
	var x=eve.pageX;
	var y=eve.pageY;	
	//alert("x "+x+" y "+y);	
}
// Not In Use
function selectOption()
{
	var selObj=document.getElementById("selid");
	//selObj.options[0].selected='selected';
}

	

//******* Row * Validation
// Focusing On Add Button
function goToNextIndex(eve)
{
	if(eve.keyCode==9)
	{
		document.getElementById("addButton").focus();
	}
	return true;
}

function validateAdd()
{
	
	var rownumber=document.getElementsByName('numberOfRow')[0].value;	
	var valid=true;

	//alert(document.getElementsByName("icdNHospitalFlagValue")[0].value);
	if(document.getElementsByName("icdNHospitalFlagValue")[0].value !="4")
	{
	Code = document.getElementsByName(elemDiagnosisCode)[rownumber-1];
	name = document.getElementsByName(elemDiagnosisName)[rownumber-1];
	//alert(name);
	diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[rownumber-1]
	diagnosisSite	= document.getElementsByName("diagnosisSite")[rownumber-1]
	remark = document.getElementsByName("remarks")[rownumber-1]
	
	
	if(isEmpty(Code,"Diagnosis Code") &&
		isEmpty(name,"Diagnosis Name") &&
		comboValidation(diaTypeCode,"Diagnosis Type") ) 
	//	&& isEmpty(remark,"Remarks")
		
	{
	//	alert("hi");
		preIcdCodeLen=document.getElementsByName("addDiagnosticTypeCode").length;
		//alert(preIcdCodeLen);
		valid=true;
		for(var i=0;i<preIcdCodeLen;i++)
		{
			if(trimData(document.getElementsByName("addDiagnosisCode")[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[rownumber-1].value))
			{
				alert(trimData(document.getElementsByName(elemDiagnosisName)[rownumber-1].value)+" Already Added");
				valid=false;
				break;
			}
		}
		if(valid && rownumber>1)
		{
			for(var i=0;i<rownumber-1;i++)
			{
				if(trimData(document.getElementsByName(elemDiagnosisCode)[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[rownumber-1].value))
				{
					alert(trimData(document.getElementsByName(elemDiagnosisName)[rownumber-1].value)+" Already Added");
					valid=false;
					break;
				}
			}
		}
	}
	else
	{
		valid=false;
	}
	}
	
	if(document.getElementsByName("icdNHospitalFlagValue")[0].value==4)
	{
		{
		if(!validateRow(0))
			return false;
		}
	}
	//alert(valid);
	return valid;
}

function validateRow(row)
{
	var Code,name;
	if(document.getElementsByName("icdNHospitalFlagValue")[0].value==4)
	{
		var diagname=document.getElementsByName('snomedCTDiagnosisName')[0].value;
		//alert(diagname);
		if(diagname=="")
			{
			  alert("Please Select Diagnosis");
			  return false;
			}
		elemDiagnosisName = "txt-snomed-ct-search_1";
		name = document.getElementsByName(elemDiagnosisName)[0];
		elemDiagnosisCode="snomedCTDiagnosisCode";
		
		diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[row]
		
		if( isEmpty(name,"Diagnosis Name") && comboValidation(diaTypeCode,"Diagnosis Type") )
		{
			preIcdCodeLen=document.getElementsByName("addDiagnosticTypeCode").length;
			for(var i=0;i<preIcdCodeLen;i++)
			{	
				if(trimData(document.getElementsByName("addDiagnosisCode")[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value))
				{
					alert(trimData(document.getElementsByName(elemDiagnosisName)[row].value)+" Already Added");
					return false;
				}
			}
			if(row>0)
			{
				for(var i=0;i<row;i++)
				{
					if(trimData(document.getElementsByName(elemDiagnosisCode)[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value))
					{
						alert(trimData(document.getElementsByName(elemDiagnosisName)[row].value)+" Already Added");
						return false;
					}
				}
			}

		}
		else
			return false;
		setfreeText();
		return true;
		
	}
	
	else
		{
	Code = document.getElementsByName(elemDiagnosisCode)[row];
	name = document.getElementsByName(elemDiagnosisName)[row];
		
	//alert(Code.value+','+name.value);
	diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[row]
	diagnosisSite	= document.getElementsByName("diagnosisSite")[row]
	remark = document.getElementsByName("remarks")[row]
	
	if(isEmpty(Code,"Diagnosis Code") && isEmpty(name,"Diagnosis Name") &&
		comboValidation(diaTypeCode,"Diagnosis Type") )
	{
		preIcdCodeLen=document.getElementsByName("addDiagnosticTypeCode").length;
		for(var i=0;i<preIcdCodeLen;i++)
		{	
			if(trimData(document.getElementsByName("addDiagnosisCode")[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value))
			{
				alert(trimData(document.getElementsByName(elemDiagnosisName)[row].value)+" Already Added");
				return false;
			}
		}
		if(row>0)
		{
			for(var i=0;i<row;i++)
			{
				if(trimData(document.getElementsByName(elemDiagnosisCode)[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value))
				{
					alert(trimData(document.getElementsByName(elemDiagnosisName)[row].value)+" Already Added");
					return false;
				}
			}
		}
		

	}
	else
		return false;
	return true;
}
}

function deleteDiagRow(Str)
{	
	var tableObj=document.getElementById('diagnosisTable');
	var temp=Str;
	tableObj.deleteRow(temp.rowIndex);
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	document.getElementsByName('numberOfRow')[0].value=index-1;
	return true;
}




function AddRowToTable()
{
	//alert(document.getElementsByName("unitDiagnosisCodeType")[0].value)
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	
	var resp=document.getElementsByName('comboOptionString')[0].value;
	var respDiagnosisSite=document.getElementsByName('comboDiagnosisSite')[0].value;
	//alert(resp); alert(respDiagnosisSite);
	var nRow=0;
	var tableObj=document.getElementById('diagnosisTable');
	var numRows=tableObj.rows.length;
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
	}
	else
	{
		nRow=numRows;
	}
	
	
	
	
	
	if(document.getElementsByName("icdNHospitalFlagValue")[0].value=="4")  //  icdNHospitalFlag
		
		 
	{

		var tabRow1=tableObj.insertRow(numRows);
		tabRow1.id=parseInt(nRow)+1;
	
		var td7=document.createElement("TD");
		var td8=document.createElement("TD");
		var td9=document.createElement("TD");
		var td10=document.createElement("TD");
		var td11=document.createElement("TD");
		var td12=document.createElement("TD");
		  
		//alert("inside snomdct");
		//load1(1,'DISORDER');
		var diagcode=document.getElementsByName('snomedCTDiagnosisCode')[0].value;
		var diagname=document.getElementsByName('snomedCTDiagnosisName')[0].value;
		var t=document.getElementById('diagnostictypeId');
		var diagtype = t.options[t.selectedIndex].text;
		var diagtypecode=document.getElementsByName('diagonisticTypeCode')[0].value;
		
	//	var r=document.getElementById('diagnosisSiteId');
		//var diagsite = r.options[r.selectedIndex].text;
	//	var diagsiteId = document.getElementsByName('diagnosisSite')[0].value;
		
		var diagsitecode=document.getElementsByName('snomedCTDiagnosisSiteCode')[0].value;
		var diagsitename=document.getElementsByName('snomedCTDiagnosisSiteName')[0].value;
		
		
		var diagremarks=document.getElementsByName('remarks')[0].value;
		//alert(diagcode+','+diagname+','+diagtype+','+diagsite+','+diagremarks);
		//load2();
		
		/* var x = document.createElement("LABEL");
		 var t = document.createTextNode(diagcode);
		 x.appendChild(t);
		*/
		
		/*
		td7.innerHTML="<div align='center'>"
			+"<input name='snomedCTDiagnosisCode' value='"+diagcode+"' type='hidden'></input>"
			+diagcode +"</div>";
		td7.className='tdfont';																													
		td7.colspan="1";*/
		
		

		td8.innerHTML="<div align='center'>"
			+"<input name='snomedCTDiagnosisCode' value='"+diagcode+"' type='hidden'></input>"
			+"<input name='snomedCTDiagnosisName' value='"+diagname+"' type='hidden'></input>"
			+diagname +"</div>";
		td8.className='tdfont';																													
		td8.colspan="1";
		
		

		td9.innerHTML="<div align='center'>"
			+"<input name='diagonisticTypeCode' value='"+diagtypecode+"' type='hidden'></input>"
			+diagtype +"</div>";
		td9.className='tdfont';																													
		td9.colspan="1";

		/*td10.innerHTML="<div align='center'>"
			+"<input name='diagnosisSite' value='"+diagsiteId+"' type='hidden'></input>"
			+diagsite +"</div>";
		td10.className='tdfont';																													
		td10.colspan="1";*/
		
		td10.innerHTML="<div align='center'>"
			+"<input name='snomedCTDiagnosisSiteCode' value='"+diagsitecode+"' type='hidden'></input>"
			+"<input name='snomedCTDiagnosisSiteName' value='"+diagsitename+"' type='hidden'></input>"
			+diagsitename +"</div>";
		td10.className='tdfont';																													
		td10.colspan="1";

		td11.innerHTML="<div align='center'>"
			+"<input name='remarks' value='"+diagremarks+"' type='hidden'></input>"
			+diagremarks +"</div>";
		td11.className='tdfont';																													
		td11.colspan="1";
		
		
		td12.className='tdfont';
		td12.colspan="1";
		td12.innerHTML="<div align='center'><img src='/HISClinical/hisglobal/images/minus.gif' onClick='deleteDiagRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
	   
		
		//tabRow1.appendChild(td7);
		tabRow1.appendChild(td8);
		tabRow1.appendChild(td9);
		tabRow1.appendChild(td10);
		tabRow1.appendChild(td11);
		tabRow1.appendChild(td12);
		document.getElementById('somectIddata').innerHTML="";
		document.getElementsByName('txt-snomed-ct-search_1')[0].value="";
		document.getElementsByName('snomedCTDiagnosisCode')[0].value="";
		document.getElementsByName('snomedCTDiagnosisName')[0].value="";
		
		var elmt=document.getElementsByName('diagonisticTypeCode')[0];
//		alert(elmt);
	    for(var i=0;i<elmt.length;i++)
	    	{
	    	if(elmt.options[i].text=="Provisional")
	    		{
	    		elmt.selectedIndex=i;
	    	break;
	    		}
	           
	    	}
//		var tt=document.getElementById('diagnostictypeId');
//		tt.selectedIndex = "-1";
//		t.options[t.selectedIndex].text;
//		
	    
	/*    var elmtt=document.getElementsByName('diagnosisSite')[0];
//		alert(elmt);
	    for(var i=0;i<elmtt.length;i++)
    	{
    	if(elmtt.options[i].text=="Select Value")
    		{
    		elmtt.selectedIndex=i;
    	break;
    		}
           
    	}*/
	   
	    
	    document.getElementsByName('snomedCTDiagnosisSiteCode')[0].value="";
		document.getElementsByName('snomedCTDiagnosisSiteName')[0].value="";
		document.getElementsByName('txt-snomed-ct-search_2')[0].value="";
	    
		//document.getElementById("seldiagnosticSiteId").value="";
		document.getElementById("divfindingsitesnomed").style.display="none";
		document.getElementById("divfindingsite").style.display="none";
	
		
	    document.getElementsByName('snomedPTRemarks')[0].value="";
		document.getElementsByName('snomedCIdRemarks')[0].value="";
		document.getElementsByName('txt-snomed-ct-search_4')[0].value="";
	
		//$("#seldiagnosticSiteId").empty();
		//var tr=document.getElementById('diagnosisSiteId');
		//tr.selectedIndex = "-1";
				
		document.getElementsByName('remarks')[0].value="";
		
			
		//deleteDiagRow(document.getElementById((parseInt(nRow)-1)));
		
		/*var x=document.getElementById('diagnosisTable').tBodies[0]; 
		var node=tableObj.rows[1].cloneNode(true);    //clone the previous node or row
	   
		
		    var parentNode = node.parentNode;
		    parentNode.removeChild(node);
		    parentNode.innerHTML('<div>' + node.value + '</div>');
	  */  
	   
			
		//var tableObj1=document.getElementById('diagnosisTable');
		//tableObj1.deleteRow(1);
		
	
	/*td1.innerHTML="<div align='center'>"
		+ "<input type='text' name= 'snomedCTDiagnosisCode'></div>"
	td1.className='tdfont';																													
	td1.colspan="1";

	td2.innerHTML="<div align='center'>"
		+ " <div  id='dialog-form'> <div id='snomed-ct-search'> <span class='ui-helper-hidden-accessible' aria-live='polite' role='status'></span>"
		+ "<input autocomplete='off' placeholder='Enter 3 characters to search...' id='txt-snomed-ct-search' class='searchText ui-autocomplete-input' name='txt-snomed-ct-search' style='width:95%;color:#000000;' type='text'>"
		+"</div> <div id='norecorddiv'>	<label style='display: inline;' id='reccnt'>No. of records : </label>	<span style='display: inline;' id='reccount' ></span>"
		+"<label style='display: none;' id='nosuggestion'>No suggestions found</label>	<label style='display: none;' id='norec'>No results found</label>  <label style='display: none;' id='msg3chars'>Please enter atleast 3 characters</label>"
		+" </div>  <div class='concept' id='conceptdiv'>  </div> </div></div> ";

	td2.className='tdfont';																													
	td2.colspan="1";
	*/
		load1('2','BODY_STRUCTURE');
		load1('1','DISORDER');
	}
	else{
		
		var tabRow=tableObj.insertRow(numRows);
		tabRow.id=parseInt(nRow)+1;
		var td1=document.createElement("TD");
		var td2=document.createElement("TD"); 
		var td3=document.createElement("TD");
		var td4=document.createElement("TD");
		var td5=document.createElement("TD");
		var td6=document.createElement("TD");
		
	
		
		
		
	//alert("inside icd");
		td1.innerHTML="<div align='center'>"
			+ "<input type='text' name='"+ elemDiagnosisCode +"' maxlength='6' size='6' tabindex='1' "
			+ "onchange='setCorrICDCodeName(this)' ></div>";
	/*		+ "onkeypress='return validateAlphaNumOnly(this,event)' "
			+ "onkeyup=\"gettext(event,this,'CODE');\" " 
			+ "onblur='callOnBlur()' ></div>";*/
		td1.className='tdfont';																													
		td1.colspan="1";
		
		td2.innerHTML="<div align='center'>"
			+"<input type='text' name='"+ elemDiagnosisName + "' maxlength='100' size='30' tabindex='1' "
			+ "onchange='setCorrICDCodeName(this)' ></div>";
	/*		+ "onkeypress='return validateAlphaNumOnly(this,event)' "
			+ "onkeyup=\"gettext(event,this,'LABLE');\" "
			+ "onblur='callOnBlur()' ></div>";*/
		td2.className='tdfont';
		td2.colspan="1";
		
	
	
	
	td3.innerHTML="<div align='center' id='divdiagnostictype'>"
		+ "<select name='diagonisticTypeCode' tabindex='1' value='-1'> "
		+ "<option value='-1'>Select Value</option>"
		+ resp + "</select></div>";
	td3.className='tdfont';
	td3.colspan="1";
	
	td4.innerHTML="<div align='center' id='divdiagnosticsite'>"
		+ "<select name='diagnosisSite' style='width:100px;' tabindex='1' value='-1'> "
		+ "<option value='-1'>Select Value</option>"
		+ respDiagnosisSite + "</select></div>";
	td4.className='tdfont';
	td4.colspan="1";
	
	td5.innerHTML="<div align='center' id='divremarks'>"
		+ "<textarea name='remarks' rows='1' cols='40' tabindex='1' "
		+" onkeypress='return (validateAlphaNumOnly(this,event) && goToNextIndex(event))'> "
		+" </textarea></div>";
	td5.className='tdfont';
	td5.colspan="1";

	td6.className='tdfont';
	td6.colspan="1";
	/*td6.innerHTML="<div align='center'><img class='button' id='addButton' style='cursor: pointer' src='/hisglobal/images/plus.gif' tabindex='1'  onkeypress='if(event.keyCode==13)if(validateAdd()) load1(); AddRowToTable() ;' onclick='if(validateAdd()) load1();AddRowToTable()'></div>";
	*/
	
	td6.innerHTML="<div align='center'><img src='/HISClinical/hisglobal/images/minus.gif' onClick='deleteDiagRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
   
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	tabRow.appendChild(td4);
	tabRow.appendChild(td5);
	tabRow.appendChild(td6);
	document.getElementsByName('numberOfRow')[0].value=numRows;
	
	var txts = tabRow.getElementsByTagName("INPUT");
	for(var i=0;i<txts.length;i++)
	{
		var elemTxt = txts[i];
		if(elemTxt.name == elemDiagnosisCode)
			NewDropDownSearch.setup(elemTxt,elemDiseaseNameCodeList,NewDropDownSearch.SEARCH_TYPE["START"],true,true);			
		else if(elemTxt.name == elemDiagnosisName)
			NewDropDownSearch.setup(elemTxt,elemDiseaseCodeNameList,NewDropDownSearch.SEARCH_TYPE["START"],true,false);
	}
	}
	
	// load1(1,'DISORDER');
	
	
	
	
}


 function load2()
 {
	//alert("inside plus");
	 
	 $("#divdiagnostictype :input").each(function () {
     
     this.outerHTML = "<div>" + this.options[this.selectedIndex].text+ "</div>";
      } ); 
	 
	 $("#divdiagnosticsite :input").each(function () {

	     this.outerHTML = "<div>" + this.options[this.selectedIndex].text+ "</div>";
	 } ); 
		 
	 $("#divremarks :input").each(function () {

	     this.outerHTML = "<div>" + this.value+ "</div>";
	 } ); 
	 
	 $("#dialog-form :input").each(function () {

	     this.outerHTML = "<div>" + this.value+ "</div>";
	 } ); 
	
 }
 
 /**Added by Vasu on 22.Oct.2018 for ICD-O Integration*/
 function validate()
 {
 	//alert("Inside Validate");
 	if(typeof document.getElementsByName("icdNHospitalFlag")[0] == 'undefined')
 	{
 		if(document.getElementsByName("unitDiagnosisCodeType")[0].value==0||document.getElementsByName("unitDiagnosisCodeType")[0].value==1)
 		{
 			submitFormOnValidate(validateIt(),'SAVE');
 		}
 		else
 		submitFormOnValidate(validateICDO(),'SAVE');
 	}
 	else
 	{
 		if(document.getElementsByName("icdNHospitalFlag")[2].checked==true)
 		{
 			document.getElementsByName("icdNHospitalFlagValue")[0].value = 3;
 			document.getElementsByName("diagnosisCodeType")[0].value = 4;
 			//alert(document.getElementsByName("icdNHospitalFlagValue")[0].value);
 			//submitFormOnValidate(validateICDO(),'SAVE');
 			submitFormOnValidate(true,'SAVE');
 		}
 		else
 		{
 			submitFormOnValidate(validateIt(),'SAVE');
 		}
 	}
 }   
function validateIt()
{	
	 //alert("inside validateIt");
	//clearForm();
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
		
		//alert(Code.value+".."+name.value+".."+diaTypeCode!="-1");
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
				if(!chks[i].checked && document.getElementsByName('addDiagnosisCancelRemarks')[i].value =="")
				{
					alert("Enter the Cancel Remark ");
					document.getElementsByName('addDiagnosisCancelRemarks')[i].focus();
					return false;
				}
			}
		}
	}
	if(document.getElementsByName("icdNHospitalFlagValue")[0].value==4)
		setfreeText();

	return true;
}

/**Added by Vasu on 22.Oct.2018 for ICD-O Integration*/
function validateICDO()
{	
	//alert("Inside Validate ICDO");
	if(typeof document.getElementsByName("chkPrev")[0] == 'undefined' || document.getElementsByName('numberOfRow')[0].value>1)
	{	
		for(var i=0;i<document.getElementsByName('numberOfRow')[0].value;i++)
		{
			if(!validateRowICDO(i))
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
		morphCode = document.getElementsByName("morphCode")[row];
		morphTitle = document.getElementsByName("morphTitle")[row]; 
		remark = document.getElementsByName("remarks")[row];
		
		//alert(Code.value+".."+name.value+".."+diaTypeCode!="-1");
		if(Code.value!="" || name.value!="" ||morphCode.value!="" || morphTitle.value!=""||diaTypeCode.value!="-1")
		{
			if(!validateRowICDO(row))
				return false;
		}
		var chks = document.getElementsByName("chkPrev");
		if(typeof document.getElementsByName("chkPrev")[0] != 'undefined')
		{
			for( var i=0;i<chks.length;i++)
			{
				if(!chks[i].checked && document.getElementsByName('addDiagnosisCancelRemarks')[i].value =="")
				{
					alert("Enter the Cancel Remark ");
					document.getElementsByName('addDiagnosisCancelRemarks')[i].focus();
					return false;
				}
			}
		}
	}
	return true;
}

//Added by Vasu on 23.Oct.2018 for ICD-O Integration
function validateRowICDO(row)
{
	//alert("Inside validateRowICDO");
	var Code,name;
	Code = document.getElementsByName(elemDiagnosisCode)[row];
	name = document.getElementsByName(elemDiagnosisName)[row];
	diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[row]
	morphCode = document.getElementsByName("morphCode")[row];
	morphTitle = document.getElementsByName("morphTitle")[row]; 
	
	remark = document.getElementsByName("remarks")[row]
	
	if(isEmpty(Code,"Site Code") && isEmpty(name,"Site Name") && isEmpty(morphCode,"Morphology Code")  && isEmpty(morphTitle,"Morphology") 
		&& comboValidation(diaTypeCode,"Diagnosis Type"))
	{
		preIcdCodeLen=document.getElementsByName("addDiagnosticTypeCode").length;
		for(var i=0;i<preIcdCodeLen;i++)
		{	
			if(trimData(document.getElementsByName("addDiagnosisCode")[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value))
			{
				alert(trimData(document.getElementsByName(elemDiagnosisCode)[row].value)+" Already Added");
				return false;
			}
		}
		if(row>0)
		{
			alert("1");
			for(var i=0;i<row;i++)
			{
				if(trimData(document.getElementsByName(elemDiagnosisCode)[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value))
				{
					alert(trimData(document.getElementsByName(elemDiagnosisCode)[row].value)+" Already Added");
					return false;
				}
			}
		}

	}
	else
		{
		alert("False");
		return false;
		}
	alert("True");
	return true;
}

function getPrevDiagnosisDetail(event,path)
{
	openPopup(createFHashAjaxQuery(path),event,300,900);
}



//******* AJAX Functions
/*function sendDataForList(searchText, searchType)
{
	var choice=document.getElementsByName('unitDiagnosisCodeType')[0].value;
	var url='/AHIMS/opd/opdDiagnosis.cnt?hmode=GETTEXTLIST&WORD='+searchText+'&SEARCHTYPE='+searchType+'&unitDiagnosisCodeType='+choice;
	httpListRequest("GET",url,true);
}*/

/*function intialListReq(reqType,url,isAsynch)
{
//	Specify the function that will handle the HTTP response 
	request.onreadystatechange=handleList;
	request.open(reqType,url,isAsynch);
	
//	set the Content-Type header for a POST request 
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	request.send(null);
}*/

/*function httpListRequest(reqType,url,asynch)
{
	//Mozilla-based browsers
	if(window.XMLHttpRequest)
	{
		request = new XMLHttpRequest();
		intialListReq(reqType,url,asynch);
	}
	else if(window.ActiveXObject)
	{
		request=new ActiveXObject("Msxml2.XMLHTTP");
		if (! request)
			request=new ActiveXObject("Microsoft.XMLHTTP");
		if(request)
		//	Unlikely to branch here, as IE uses will be able to use either 
		//	one of the constructors
			intialListReq(reqType,url,asynch);
		else
			alert("Your browser does not permit the use of all of this application's features!");
	}
	else
		alert("Your browser does not permit the use of all of this application's features!");
}*/

/*function handleList()
{
	if(request.readyState == 4)
	{
		popupList=null;
		if(request.status == 200)
		{
			var elemDropDown=document.getElementById("selid");
			// Setting Position
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
			
			var optionValueTextArray=request.responseText.split('$');
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
		else
		{
			alert("A problem occurred with communicating between "+"the XMLHttpRequest object and the server program.");
		}
	}//end outer if
}*/

function revokeDiagnosis(obj1,obj2)
{
	var diagCode=obj1;
	document.getElementsByName("revokeDiagCode")[0].value=diagCode;
	//alert(document.getElementsByName("revokeDiagCode")[0].value);
	document.getElementsByName("revokeDiagicdNHospitalFlag")[0].value=obj2;
	submitForm21('REVOKE');
}


function submitForm21(hmode)
{
	
	document.getElementsByName("hmode")[0].value=hmode;
	document.forms[0].submit();
	}



function sendDataForCodeList(searchText, searchType)
{
	handleCodeList(searchText, searchType);
}


function sendRequestForCodeList(text,type)
{
	
	//if(document.getElementsByName("unitDiagnosisCodeType")[0].value=="0")
	if(document.getElementsByName("icdNHospitalFlagValue")[0].value=="0")
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




/*function showIdcHospitalDiagnosis()
{
	document.getElementsByName("comboOptionString")[0].value="";
	document.getElementsByName("comboDiagnosisSite")[0].value="";
	

if(document.getElementsByName("icdNHospitalFlag")[0].checked==true)
	document.getElementsByName("icdNHospitalFlagValue")[0].value="0";

//if(document.getElementsByName("icdNHospitalFlag")[1].checked==true)
	
else
	document.getElementsByName("icdNHospitalFlagValue")[0].value="1";	

if(document.getElementsByName("icdNHospitalFlag")[0].value=="4")
	{
	document.getElementsByName("icdNHospitalFlagValue")[0].value="4";
	load1('2','BODY_STRUCTURE');
	load1('1','DISORDER');

	} 

//alert(document.getElementsByName("icdNHospitalFlagValue")[0].value);

}*/

function showIdcHospitalDiagnosis()
{
	//alert("Inside showIdcHospitalDiagnosis");
	//alert(document.getElementsByName("icdNHospitalFlagValue")[0].value);
	//alert(document.getElementsByName("unitDiagnosisCodeType")[0].value);
	if(typeof document.getElementsByName("icdNHospitalFlag")[0] == 'undefined')
	{
		//alert("show U1");
		if(document.getElementsByName("unitDiagnosisCodeType")[0].value==0)
		{
			//alert("U1 0");
			document.getElementsByName("icdNHospitalFlagValue")[0].value=0;
		}
		else if(document.getElementsByName("unitDiagnosisCodeType")[0].value==1)
		{
			//alert("U1 1");
			document.getElementsByName("icdNHospitalFlagValue")[0].value=1;
		}
		else
		{
			//alert("U1 2")
			document.getElementsByName("icdNHospitalFlagValue")[0].value=2;
		}
	}
	else
	{
		if(document.getElementsByName("unitDiagnosisCodeType")[0].value==2||document.getElementsByName("unitDiagnosisCodeType")[0].value==3)
		{
			if(document.getElementsByName("icdNHospitalFlag")[0].checked==true)
			{
				//alert("[icd].checked");
				document.getElementsByName("icdNHospitalFlagValue")[0].value=0;
			}
			else if(document.getElementsByName("icdNHospitalFlag")[1].checked==true)
			{
				//alert("[hos].checked");
				document.getElementsByName("icdNHospitalFlagValue")[0].value=1;
			}			
		}
		if(document.getElementsByName("unitDiagnosisCodeType")[0].value==5||document.getElementsByName("unitDiagnosisCodeType")[0].value==6)
		{
			//alert("unitDiagnosisCode = 6");
			if(document.getElementsByName("icdNHospitalFlag")[0].checked==true)
			{
				//alert("[icd].checked");
				document.getElementsByName("icdNHospitalFlagValue")[0].value=0;
			}
			else if(document.getElementsByName("icdNHospitalFlag")[2].checked==true) //Modified By Vasu on 23.Oct.2018 as per ICD-O Code
			{
				//alert("[icd0].checked");
				document.getElementsByName("icdNHospitalFlagValue")[0].value=9;
				document.getElementById('searchICD').style.display= "none";
			}			
		}
	}
	//submitForm('NEWCHANGE');
}


//Added by Vasu for ICD-O Disease SiteId auto dropdown on 24.Oct.2018
function setCorrSiteName(elemTarget)
{
	//alert("Inside setCorrSiteName");
	var rowIndex = findIndex(elemTarget);
	var elemDiagSId = document.getElementsByName(elemDiseaseSiteId)[rowIndex];
	var elemDiagCode = document.getElementsByName(elemDiagnosisCode)[rowIndex];
	var elemDiagName = document.getElementsByName(elemDiagnosisName)[rowIndex];
	// Set Data
	if(elemTarget.name==elemDiagCode.name)
	{
		if(elemTarget.SELECTED_VALUE!=null)
		{
			elemDiagSId.value = elemTarget.SELECTED_VALUE.substr(0,elemTarget.SELECTED_VALUE.indexOf('-'));
			elemDiagName.value = elemTarget.SELECTED_VALUE.substr(elemTarget.SELECTED_VALUE.indexOf('-')+1);
		}
		else
		{
			elemDiagCode.value = "";elemDiagName.value = "";
		}
	}
	else if(elemTarget.name==elemDiagName.name)
	{
		if(elemTarget.SELECTED_VALUE!=null)
		{
			elemDiagSId.value = elemTarget.SELECTED_VALUE.substr(0,elemTarget.SELECTED_VALUE.indexOf('-'));
			elemDiagCode.value = elemTarget.SELECTED_VALUE.substr(elemTarget.SELECTED_VALUE.indexOf('-')+1);
		}
		else
		{
			elemDiagCode.value = "";elemDiagName.value = "";
		}
	}
}
//Added by Vasu for ICD-O Morphology auto dropdown on 24.Oct.2018
function setCorrMorphCodeName(elemTarget)
{
	//alert("setCorrMorphCodeName");
	var rowIndex = findIndex(elemTarget);
	var elemDiagCode = document.getElementsByName("morphCode")[rowIndex];
	var elemDiagName = document.getElementsByName("morphTitle")[rowIndex];
	// Set Data
	if(elemTarget.name==elemDiagCode.name)
	{
		if(elemTarget.SELECTED_VALUE!=null)
			elemDiagName.value = elemTarget.SELECTED_VALUE;
		else
		{
			elemDiagCode.value = "";elemDiagName.value = "";
		}
	}
	else if(elemTarget.name==elemDiagName.name)
	{
		if(elemTarget.SELECTED_VALUE!=null)
			elemDiagCode.value = elemTarget.SELECTED_VALUE;
		else
		{
			elemDiagCode.value = "";elemDiagName.value = "";
		}
	}
}

function setDiseaseSiteSerachDropDownLists()
{
	elemDiseaseSiteNameList = document.getElementsByName("diseaseSiteNameList")[0];
	elemDiseaseSiteCodeList = document.getElementsByName("diseaseSiteCodeList")[0];
}


