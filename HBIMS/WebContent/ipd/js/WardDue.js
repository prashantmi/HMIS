function getUnitCombo() {
	var hmode = "GETUNITCOMBO"; 
	var url = "WardDueIPDTransCNT.cnt?hmode="+hmode+"&strDept="+document.getElementsByName("strDept")[0].value;
	ajaxFunction(url,"1");
}

function getWardCombo() {
	var hmode = "GETWARDCOMBO"; 
	var url = "WardDueIPDTransCNT.cnt?hmode="+hmode+"&strDeptUnit="+document.getElementsByName("strDeptUnit")[0].value;
	ajaxFunction(url,"2");
}

function getPatientList() {
	var hmode = "GETPATIENTLIST";
	var url = "WardDueIPDTransCNT.cnt?hmode="+hmode+"&strDeptUnit="+document.getElementsByName("strDeptUnit")[0].value+"&strWard="+document.getElementsByName("strWard")[0].value;		
	ajaxFunction(url,"3");
}

function chkPatient()
{
	var hmode = "GETPATIENTDUELIST";
	var url = "WardDueIPDTransCNT.cnt?hmode="+hmode+"&strDeptUnit="+document.getElementsByName("strDeptUnit")[0].value+"&strWard="+document.getElementsByName("strWard")[0].value+"&strPatient="+document.getElementsByName("strPatient")[0].value;		
	ajaxFunction(url,"4");
}

function getAjaxResponse(res,mode){
	if(res.split("####")[0]=="Error"){
		document.getElementById("errMsg").innerHTML = res.split("####")[1];
	}else if(mode == '1'){
		document.getElementById("divUnit").innerHTML = "<select name='strDeptUnit' onchange='getWardCombo()' class='comboNormal'>"+res+"</select>";
		document.getElementById("divWard").innerHTML = getBlankCombo("strWard");
		hideDiv("divPatientDueDtl");
		hideDiv("divPatientDtl");
	}else if(mode == '2'){
		document.getElementById("divWard").innerHTML = "<select name='strWard' onchange='getPatientList()' class='comboNormal'>"+res+"</select>";
		hideDiv("divPatientDueDtl");
		hideDiv("divPatientDtl");
	}else if(mode == '3'){
		showDiv("divPatientDtl");
		hideDiv("divPatientDueDtl");
		document.getElementById("divPatientList").innerHTML = res;
	}else if(mode == '4')
	{
		showDiv("divPatientDueDtl");
		document.getElementById("divPatientDueList").innerHTML = res;
		hideDiv("divPatientList");
		showDiv('divPatientPlusID');
		hideDiv("divPatientMinusID");
	}
}

function getBlankCombo(_ComboName){
	return "<select name='"+_ComboName+"' class='comboNormal'><option value=0>Select Value</option></select>";
}

function buttonClick(_Param){
	document.getElementsByName("hmode")[0].value = _Param;
	document.forms[0].submit();
}

function showDiv(_strDivId){
	document.getElementById(_strDivId).style.display="block";
}

function hideDiv(_strDivId){
	document.getElementById(_strDivId).style.display="none";
}

function saveData(){
	var fErrFlag = true;
	var strArrItem = document.getElementsByName("strChkItem");
	for(var nTmpI = 0; nTmpI<strArrItem.length; nTmpI++){
		if(strArrItem[nTmpI].checked){
			buttonClick("SAVE");
			fErrFlag = false;
			break;
		}
	}
	if(fErrFlag)
		alert("Please select at least one Item.");
}
