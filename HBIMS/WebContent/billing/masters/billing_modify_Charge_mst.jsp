<!-- 
 Charge Master Modify jsp
 author: Debashis Sardar
  Created on : 06-Sep-2011
  -->
  <!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8"%>


<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<meta charset=utf-8>
<title>Charge Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<style type="text/css">
@import url(../../hisglobal/css/calendar-tas.css);
</style>
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>

<script language="JavaScript" >
function groupCombo(){
  
   
  if(document.forms[0].strIsPackage.checked == false){
  	 
 
	var temp = document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value ;
	var mode="GRPCMB";
	var url="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	
		
	ajaxFunction(url,"1");
	 }
}

function tariffCombo(){	
  
	var temp = document.forms[0].strGroupName[document.forms[0].strGroupName.selectedIndex].value ;
	var mode="TRFCMB";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	ajaxFunction(url1,"2");
}

function unitCombo(){
 
	var temp = document.forms[0].strTariffName[document.forms[0].strTariffName.selectedIndex].value.split('^') ;
	
	var mode="UNTCMB";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[1];
	
	ajaxFunction(url1,"3");
}

function wardCombo(){


		var temp1 = document.forms[0].hospitalService[document.forms[0].hospitalService.selectedIndex].value ;
		
		var mode="WRDCMB";
		var url="CNTChargeMst.cnt?hmode="+mode+"&service1="+temp1;
		
		ajaxFunction(url,"4");
	
}

function packGroupCombo(){

	var temp = document.chargeBean.strPackHospService[document.chargeBean.strPackHospService.selectedIndex].value;
	
	var mode="GRPCMB1";
	var url="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	ajaxFunction(url,"5");
}

function packTariffCombo(){	
	var temp = document.chargeBean.strPackGroupName[document.chargeBean.strPackGroupName.selectedIndex].value;
	var mode="TRFCMB1";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp;
	ajaxFunction(url1,"6");
}

function packUnitCombo(){
	
	var temp = document.chargeBean.strPackTariffName[document.chargeBean.strPackTariffName.selectedIndex].value.split('^');

	var mode="UNTCMB1";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[1];
	
	ajaxFunction(url1,"7");
}

function packageDetails(){
	var temp = document.chargeBean.strPackTariffName[document.chargeBean.strPackTariffName.selectedIndex].value.split('^');
	var mode="PACKDET";
	var url1="CNTChargeMst.cnt?hmode="+mode+"&service="+temp[0];
	
	ajaxFunction(url1,"9");
}

function packWardCombo(){
	
	
		var temp1 = document.chargeBean.strPackHospService[document.chargeBean.strPackHospService.selectedIndex].value;	
		var mode="WRDCMB";
		var url="CNTChargeMst.cnt?hmode="+mode+"&service1="+temp1;	
		
		ajaxFunction(url,"8");
	
}

function getAjaxResponse(res,mode){	

 
	
		if(mode==1){
	
	 	var objVal = document.getElementById("grpName");
		objVal.innerHTML = "<select onChange='tariffCombo();' class='comboNormal'>" + res + "</select>";		
		wardCombo();
	} else if(mode==2){
	
	  	var objVal = document.getElementById("trfId");
		objVal.innerHTML = "<select  onChange='unitCombo();' class='comboNormal'>" + res + "</select>";
	} else if(mode==3){

	 	var objVal = document.getElementById("unitId");
		objVal.innerHTML = "<select class='comboNormal'>" + res + "</select>";
	} else if(mode==4){
	
	  	var objVal = document.getElementById("wardId");
		objVal.innerHTML = "<select class='comboNormal'>" + res + "</select>";
	} else if(mode==5){
	
		var objVal = document.getElementById("grpName1");
		objVal.innerHTML = "<select name='a3' class='comboNormal'>" + res + "</select>";
		packWardCombo();
	} else if(mode==6){

		var objVal = document.getElementById("trfId1");
		objVal.innerHTML = "<select name='a1' onChange='packUnitCombo();' class='comboNormal'>" + res + "</select>";
	} else if(mode==7){
	
		var objVal = document.getElementById("unitId1");
		objVal.innerHTML = "<select name='a2' class='comboNormal'>" + res + "</select>";
		packageDetails();
	} else if(mode==8){
	
		var objVal = document.getElementById("wardId1");
		objVal.innerHTML = "<select name='a5' class='comboNormal'>" + res + "</select>";
	} else if(mode==9){

		if(res != ""){
		var objVal = document.getElementById("packDetailsId");
		objVal.innerHTML =  res ;
		}
	}
}


function initializeValues(){
		  
	  if("${chargeBean.strIsPackage}" == '1'){
	  document.chargeBean.strIsPackage.checked = true;
		// TEST////
		
	    document.getElementById("forPackages").style.display="block";
		document.getElementById("forServices").style.display="none";
		
		document.getElementById("packDetailsId").style.display="block";    // packageDetails
		
		var obj = document.getElementsByName("strRefundable");
		for(var i=0;i<obj.length;i++){
			if(obj[i].value=="1")
				obj[i].checked = true;
		}
		}else{
	
	
		document.getElementById("packDetailsId").style.display="none";        // packageDetails
		document.getElementById("forPackages").style.display="none";
		document.getElementById("forServices").style.display="block";
	}
	
}

function submitData(mode){
	var strCheckBox = document.getElementsByName("strCheckBox");
	for(var nTmpI=0; nTmpI<strCheckBox.length; nTmpI++){
		var re = compareDate(strCheckBox[nTmpI].value.split("^")[3],document.getElementsByName("strFromDateArr")[nTmpI].value);
		if(re == 2){
			alert("Effective from date must greater than or equals to previous effective from date "+strCheckBox[nTmpI].value.split("^")[3]);
			return;
		}
	}
	alert(re);
	return;
	
	var hisValidator = new HISValidator("chargeBean");
		
	document.chargeBean.strIsPackage.disabled = false;
	var isPackageVal = document.chargeBean.strIsPackage.checked;
	document.chargeBean.strIsPackage.disabled = true;
	
	
	if(isPackageVal)
	{
		document.chargeBean.strIsPackage.value = "1";
		
							
		if(document.getElementById("packDetailsId").innerHTML != "")
		{						
			hisValidator.addValidation("strPackProcdCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strPackTariffCost","amount=8,2","Please Enter Valid Amount.");
			hisValidator.addValidation("strPackTotalCost","amount=8,2","Please Enter Valid Amount.");
			
			
		}
		
		hisValidator.addValidation("strPackProcdCost","req","Please Enter Procedure cost.");
		hisValidator.addValidation("strPackTariffCost","req","Please Enter Tariff Cost.");
		
	}else
	{
		document.chargeBean.strIsPackage.value = "0";		
			
		
		hisValidator.addValidation("strProductCost","req","Please Enter Procedure Cost.");
		hisValidator.addValidation("strProductCost","amount=8,2","Please Enter Valid Amount.");
		hisValidator.addValidation("strTariffCost","req","Please Enter Tariff Cost.");
		hisValidator.addValidation("strTariffCost","amount=8,2","Please Enter Valid Amount.");
		
	}
	if(document.forms[0].strUpdateMode.value=="1")  
	{
	hisValidator.addValidation("strEffectiveFrm","req","Effective From is a mandatory field"); 
	hisValidator.addValidation("strEffectiveFrm","dtgtet=${chargeBean.strCurrentDate}" ,"Please Select Effective From Date Greater Than Or Equal To Current Date.");
	}
	if(document.chargeBean.strEffectiveTo.value != ""){
		hisValidator.addValidation("strEffectiveTo","dtgtet="+document.chargeBean.strEffectiveFrm.value,"Please Select Effective To Date Greater Than Or Equal To Effective From Date.");
		}
		
	hisValidator.addValidation("strRemarks","maxlen=50","Remarks Should Not Be Greater Than 50 Characters");
	
	var retVal = hisValidator.validate();
	hisValidator.clearAllValidations();
	if(retVal)
	{
	
	
		if(isPackageVal){
		
			if(parseFloat(document.forms[0].strPackTotalCost.value) != parseFloat(document.forms[0].strPackGrandTotalCost.value)){
				
									alert("Total Cost and Grand Total Cost are not Same. Please re-enter the Package Detail Amount");
					
									return false;
								}
			
		}
	
	var advObj = document.getElementsByName("strIsAdvance");
	var refObj = document.getElementsByName("strIsRefundable");
	
	var len = advObj.length ;
	
	if(len > 0){
		
			for(var i=0; i< len ; i++){
			
				if(advObj[i].checked == true){
					advObj[i].value = 1;
				}else{
				advObj[i].checked = true;
				advObj[i].value = 0;
				}
			
			if(refObj[i].checked == true){
					refObj[i].value = 1;
				}else{
				refObj[i].checked = true;
				refObj[i].value = 0;
				}
			
			}
	}
	
	
	var isRefObj = document.getElementsByName("strRefundable");
	 
	var len = isRefObj.length ;
	
	if(len > 0){
		
			for(var i=0; i< len ; i++){
			
							
			if(isRefObj[i].checked == true){
					isRefObj[i].value = 1;
				}else{
				isRefObj[i].checked = true;
				isRefObj[i].value = 0;
				}
			
			}
	}
	
	
		
		document.chargeBean.hmode.value = mode;
		document.chargeBean.submit();
	}
	else{		
		return false;
	}	
}	


function calcTotalAmount(index){
		
			var retVal = false;
		
			var adv = document.getElementById("strProductCost"+index).value;
			var sec = document.getElementById("strTariffCost"+index).value
			var total  = 0;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.getElementById("strTotalCost"+index).value = total;
					retVal = true;
					
			
		
		return retVal;
	}
	
	
	function enterAmount(index){
	
		
			var retVal = false;
		
		
			if(index != '0'){
			var adv = document.getElementById("strPackProcCost"+index).value;	
			var sec = document.getElementById("strPackTrfCost"+index).value;
			var total  = 0;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.getElementById("strPackTotal"+index).value = total;
					retVal = true;
					
					calcGrandTotal(1);
					
			}else{
			
			var adv = document.forms[0].strPackProcdCost.value;	
			var sec = document.forms[0].strPackTariffCost.value;	
			var total  = 0;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.forms[0].strPackTotalCost.value = total;
					retVal = true;
			
			}
		return retVal;
	}
	
	function calcGrandTotal(index){
		
		var totObj = null;
		var totalVal = 0;
		var len = 0;
		
		if(index == 1){
			totObj = document.getElementsByName("strPackTotal");
			len = totObj.length ;
		}else{
		
		totObj = document.getElementsByName("strTotalCost");
			len = totObj.length - 1;
		}
		
		
		if(len > 0){
			for(var i=0; i<len; i++){
				
				var tempTot = totObj[i].value;
				
				if(isNaN(tempTot) || tempTot=="") tempTot = "0";
				
				totalVal = manipulateValue(parseFloat(totalVal),parseFloat(tempTot),0);
			}
				if(index == 1){
			document.forms[0].strPackGrandTotalCost.value = totalVal;
			}
			}
	}
	
	
	
	function checkUpdateMode(radioObj){
	
		if(radioObj.value == 0){
		
			document.getElementById("modifyDateId").style.display = "none";
			document.getElementById("displayDateId").style.display = "block";
			
			document.forms[0].strEffectiveFrm.value = "${chargeBean.strEffectiveFrm}";
			
		}else{
		
			document.getElementById("displayDateId").style.display = "none";
			document.getElementById("modifyDateId").style.display = "block";
		}

	}
</script>
<script>
function showLabel(){
	document.getElementById("divGroupId").innerHTML = document.getElementsByName("strGroupCombo")[0].options[document.getElementsByName("strGroupCombo")[0].options.selectedIndex].text;
	document.getElementById("divTariffId").innerHTML = document.getElementsByName("strTariffCombo")[0].options[document.getElementsByName("strTariffCombo")[0].options.selectedIndex].text;
	document.getElementById("divUnitId").innerHTML = document.getElementsByName("strUnitCombo")[0].options[document.getElementsByName("strUnitCombo")[0].options.selectedIndex].text;
	
	document.getElementById("a3").innerHTML = document.getElementsByName("a3")[0].options[document.getElementsByName("a3")[0].options.selectedIndex].text;
	document.getElementById("a1").innerHTML = document.getElementsByName("a1")[0].options[document.getElementsByName("a1")[0].options.selectedIndex].text;
	document.getElementById("a2").innerHTML = document.getElementsByName("a2")[0].options[document.getElementsByName("a2")[0].options.selectedIndex].text;
	document.getElementById("a4").innerHTML = document.getElementsByName("a4")[0].options[document.getElementsByName("a4")[0].options.selectedIndex].text;
	document.getElementById("a5").innerHTML = document.getElementsByName("a5")[0].options[document.getElementsByName("a5")[0].options.selectedIndex].text;
}


function showDiv(_strDivId){
	try{
		document.getElementById(_strDivId).style.display = "block";
	}catch(_Err){
		
	}
}

function hideDiv(_strDivId){
	try{
		document.getElementById(_strDivId).style.display = "none";
	}catch(_Err){
		
	}
}

function getIndex(_these){
	var nTmpI;
	var strSelectedObjName=document.getElementsByName(_these.name)
	for(nTmpI=0;nTmpI<strSelectedObjName.length;nTmpI++)
		if(_these==strSelectedObjName[nTmpI])
			break;
	return nTmpI;
}

function calculateArr(_these){
	calculateArr
	var procCost=0;
	var tarrifCost=0;
	var nProcCost = document.getElementsByName("strProductCostArr")[getIndex(_these)].value;
	var nTariffCost = document.getElementsByName("strTariffCostArr")[getIndex(_these)].value;
	procCost = nProcCost==""?"0":nProcCost;
	tarrifCost = nTariffCost==""?"0":nTariffCost;
	
	document.getElementsByName("strTotalCostArr")[getIndex(_these)].value = eval(tarrifCost)+eval(procCost);
}

function calcAll(_these){
	var strElement="";
	var nHikeAmount = document.getElementsByName("strHike")[getIndex(_these)].value;
	var strPerOrFix = document.getElementsByName("strHikeType")[getIndex(_these)].value;
	var strUpOrDown = document.getElementsByName("strPriceType")[getIndex(_these)].value;
	var strCheckBox = document.getElementsByName("strCheckBox");
	if(document.getElementsByName("strCostType")[getIndex(_these)].value==1)
		strElement = "strProductCostArr";
	else
		strElement = "strTariffCostArr";
		
	if(nHikeAmount==""){
		alert("Please Enter the valid number.");
		return;
	}
		
	var strArr = document.getElementsByName(strElement);
	for(var nTmpI=0; nTmpI<strArr.length; nTmpI++){
		if(document.getElementsByName("strPkChargeTypeArr")[nTmpI].value!=(getIndex(_these)+1))
			continue;
		if(!strCheckBox[nTmpI].checked)
			continue;
		if(strUpOrDown==1){
			if(strPerOrFix==1)
				strArr[nTmpI].value = eval(strArr[nTmpI].value) + (eval(strArr[nTmpI].value)*(nHikeAmount/100));
			else
				strArr[nTmpI].value = eval(strArr[nTmpI].value) + eval(nHikeAmount);
		}else{
			if(strPerOrFix==1)
				strArr[nTmpI].value = eval(strArr[nTmpI].value) - (eval(strArr[nTmpI].value)*(nHikeAmount/100));
			else
				strArr[nTmpI].value = eval(strArr[nTmpI].value) - eval(nHikeAmount);
		}
		calculateArr(strArr[nTmpI]);
	}
}

function setCheck(_these){
	if(_these.checked && _these.name=="strIsAdvanceArr"){
		document.getElementsByName("strAdvArr")[getIndex(_these)].value=1;
	} else if(_these.name=="strIsAdvanceArr") {
		document.getElementsByName("strAdvArr")[getIndex(_these)].value=0;
	}
	
	if(_these.checked && _these.name=="strIsRefundArr"){
		document.getElementsByName("strRefundArr")[getIndex(_these)].value=1;
	} else if(_these.name=="strIsRefundArr") {
		document.getElementsByName("strRefundArr")[getIndex(_these)].value=0;
	}
}

function saveModifiedData(){
	var strCheckBox = document.getElementsByName("strCheckBox");
	for(var nTmpI=0; nTmpI<strCheckBox.length; nTmpI++){
		var re = compareDate(strCheckBox[nTmpI].value.split("^")[3],document.getElementsByName("strFromDateArr")[nTmpI].value);
		if(re.mode == 2){
			alert("Effective from date must greater than or equals to previous effective from date "+strCheckBox[nTmpI].value.split("^")[3]);
			return;
		}
	}
	document.getElementsByName("hmode")[0].value = "SAVEMODIFY";
	document.forms[0].submit();
}

function clearDate(flag){
	if(flag){
		var strDate = document.getElementsByName("strFromDateArr");
		for(var nTmpI=0; nTmpI<strDate.length; nTmpI++){
			document.getElementsByName("strFromDateArr")[nTmpI].disabled = true;
			hideDiv("divAEffDate"+nTmpI);
			showDiv("divEffDate"+nTmpI);
		}
	}else{
		var strDate = document.getElementsByName("strFromDateArr");
		for(var nTmpI=0; nTmpI<strDate.length; nTmpI++){
			if(document.getElementsByName("strCheckBox")[nTmpI].value.split("^")[4]=="1"){
				document.getElementsByName("strCheckBox")[nTmpI].checked = false;
				clickCheckBox(document.getElementsByName("strCheckBox")[nTmpI]);
			}
			if(document.getElementsByName("strCheckBox")[nTmpI].checked){
				document.getElementsByName("strFromDateArr")[nTmpI].disabled = false;
				showDiv("divAEffDate"+nTmpI);
				hideDiv("divEffDate"+nTmpI);
			}
		}
	}
}

function resetAll(_these){
	var strCheckBox = document.getElementsByName("strCheckBox");
	for(var nTmpI = 0; nTmpI < strCheckBox.length; nTmpI++){
		if(document.getElementsByName("strPkChargeTypeArr")[nTmpI].value!=(getIndex(_these)+1))
			continue;
		document.getElementsByName("strProductCostArr")[nTmpI].value = strCheckBox[nTmpI].value.split("^")[0];
		document.getElementsByName("strTariffCostArr")[nTmpI].value = strCheckBox[nTmpI].value.split("^")[1];
		document.getElementsByName("strTotalCostArr")[nTmpI].value = strCheckBox[nTmpI].value.split("^")[2];
	}
}

function clickCheckBox(_these){
	if(_these.checked && _these.value.split("^")[4]=="1" && document.getElementsByName("strUpdateMode")[1].checked){
		alert("This Data has already been updated so it cannot be selected for updation.");
		_these.checked=false;
		return;
	}
	if(_these.checked){
		enField("strPatCatArr",_these);
		enField("strPKTariffArr",_these);
		enField("strPKPatCatArr",_these);
		enField("strPkChargeTypeArr",_these);
		enField("strPKIpdChargeTypeArr",_these);
		enField("strPKChargeSlNoArr",_these);
		enField("strUnitIdArr",_these);
		enField("strWardTypeArr",_these);
		enField("strProductCostArr",_these);
		enField("strTariffCostArr",_these);
		enField("strTotalCostArr",_these);
		enField("strIsAdvanceArr",_these);
		enField("strAdvArr",_these);
		enField("strIsRefundArr",_these);
		enField("strRefundArr",_these);
		effEn("strFromDateArr",_these);
	} else {
		disField("strPatCatArr",_these);
		disField("strPKTariffArr",_these);
		disField("strPKPatCatArr",_these);
		disField("strPkChargeTypeArr",_these);
		disField("strPKIpdChargeTypeArr",_these);
		disField("strPKChargeSlNoArr",_these);
		disField("strUnitIdArr",_these);
		disField("strWardTypeArr",_these);
		disField("strProductCostArr",_these);
		disField("strTariffCostArr",_these);
		disField("strTotalCostArr",_these);
		disField("strIsAdvanceArr",_these);
		disField("strAdvArr",_these);
		disField("strIsRefundArr",_these);
		disField("strRefundArr",_these);
		effDis("strFromDateArr",_these);
	}	
}

function disField(_name,_these){
	document.getElementsByName(_name)[getIndex(_these)].disabled = true;
}
function enField(_name,_these){
	document.getElementsByName(_name)[getIndex(_these)].disabled = false;
}
function effDis(_name,_these){
	document.getElementsByName(_name)[getIndex(_these)].disabled = true;
	if(document.getElementsByName("strUpdateMode")[1].checked){
		hideDiv("divAEffDate"+getIndex(_these));
		showDiv("divEffDate"+getIndex(_these));
	}
}
function effEn(_name,_these){
	document.getElementsByName(_name)[getIndex(_these)].disabled = false;
	if(document.getElementsByName("strUpdateMode")[1].checked){
		showDiv("divAEffDate"+getIndex(_these));
		hideDiv("divEffDate"+getIndex(_these));
	}
}
</script>
</head>
<body onload="initializeValues();">
<html:form action="/masters/CNTChargeMst.cnt" name="chargeBean"
	type="billing.masters.controller.fb.ChargeMstFB">
	<div class="errMsg" align="center"><bean:write name="chargeBean"
		property="strErrMsg" /></div>
	<div class="normalMsg" id='normalMsg' align="center"><bean:write
		name="chargeBean" property="strNormalMsg" /></div>
	<div class="warningMsg" align="center"><bean:write
		name="chargeBean" property="strWarningMsg" /></div>


	<tag:tab tabLabel="Modify Charge" selectedTab="FIRST" align="center"
		width="TABLEWIDTH">
	</tag:tab>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="HEADER">
			<td colspan="4">
			<table width="100%" border="0" align="center">
				<tr class="HEADER">
					<td width="50%">
					<div align="left">Charge Master&gt;&gt;Modify</div>
					</td>
					<td width="50%">
					<div align="right"><input type=checkbox 
						value='${chargeBean.strIsPackage}' disabled="disabled" name="strIsPackage">
					Package</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td class="LABEL" colspan="4"><b> <input type="radio" checked
				name="strUpdateMode" value="0"
				onclick="clearDate(true)">Correction <input  type="radio"  <%=((request.getParameterValues("chk")[0].replace("$","#").replace("@","#").split("#")[6]).equals("0")?"disabled":"" )%>
				
				name="strUpdateMode" value="1"
				onclick="clearDate(false)">Update </b> &nbsp;&nbsp;</td>
		</tr>

	</table>
	<div id="forServices" style="display: block">
	<table class="TABLEWIDTH" border="0" align="center">

		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Group Name</td>
			<td class="CONTROL" width="75%" colspan="3">
			<div style="display: none" id="grpName">
			<select name="strGroupCombo" onChange="tariffCombo();" disabled="disabled" class='comboNormal'>
				<bean:write name="chargeBean" property="groupCombo" filter="false" />
			</select></div>
			<div id="divGroupId"></div>
			</td>
		</tr>

		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Tariff/Package Name</td>
			<td class="CONTROL" width="25%">
			<div style="display: none" id="trfId"><select
				name="strTariffCombo" disabled="disabled" class='comboNormal'>
				<bean:write name="chargeBean" property="tariffCombo" filter="false" />
			</select></div>
			<div id="divTariffId"></div>
			</td>
			<td class="LABEL" width="25%"><font color="red">*</font>Unit</td>
			<td class="CONTROL" width="25%">
			<div style="display: none" id="unitId"><select
				name="strUnitCombo" disabled="disabled">
				<bean:write name="chargeBean" property="unitCombo" filter="false" />
			</select></div>
			<div id="divUnitId"></div>
			</td>
		</tr>
	</table>
	<bean:write name="chargeBean" property="strModifyDtl" filter="false" />
	</div>
	<div id="forPackages" style="display:none">
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td class="CONTROL" >&nbsp;</td>
			<td class="LABEL" colspan="2"><font color="red">*</font>Hospital Service</td>
			<td class="CONTROL">
			  <select   name='a4'  disabled="disabled" class='comboNormal'>  <!-- style="display:none"  onclick="packGroupCombo();" -->
				<bean:write name="chargeBean" property="strHospitalServiceCombo" filter="false" /> 
			 </select> 
			<div id="a4" style="display: none;"></div></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Group Name</td>
			<td class="CONTROL" colspan="3">
			<div id="grpName1" style="display:none"><select name='a3' onChange="packTariffCombo();"
				disabled="disabled" class='comboNormal'>
				<bean:write name="chargeBean" property="groupCombo1" filter="false" />
			</select></div><div id="a3"></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>Tariff/Package
			Name</td>
			<td class="CONTROL" width="48%">
			<div id="trfId1" style="display:none"><select name='a1' onChange="packUnitCombo();"
				disabled="disabled" class='comboNormal'>
				<bean:write name="chargeBean" property="tariffCombo1" filter="false" />
			</select></div><div id="a1"></div>
			</td>
			<td class="LABEL" width="16%"><font color="red">*</font>Unit</td>
			<td class="CONTROL" width="16%">
			<div id="unitId1" style="display:none"><select name='a2' disabled="disabled" class='comboNormal'>
				<bean:write name="chargeBean" property="unitCombo" filter="false" />
			</select></div><div id="a2"></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL">Ward Type</td>
			<td class="CONTROL" colspan="3">
			<div id="wardId1" style="display:none"><select name='a5' disabled="disabled" class='comboNormal'>
				<bean:write name="chargeBean" property="wardCombo" filter="false" />
			</select></div><div id="a5"></div>
			</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="TITLE"><td colspan="7">Current Charges</td></tr>
		<tr>
			<td class="multiLabel" width="20%"><font color="red">*</font>Patient Category</td>
			<td class="multiLabel" width="16%" style="display: none"><font color="red">*</font>Procedure Cost</td>
			<td class="multiLabel" width="16%"><font color="red">*</font>Tariff Cost</td>
			<td class="multiLabel" width="16%">Total Cost</td>
			<!-- <td class="multiLabel" width="16%">Is-Advance</td> -->
			<td class="multiLabel" width="16%">Is-Refundable</td>
			<td class="multiLabel" width="16%">Is-Advance</td>
		</tr>
		<tr>
			<td class="multiControl"><bean:write name="chargeBean" property="strPatCatName" filter="false" />
					<input type=hidden name=strPatientCategoryMod value="${chargeBean.strPatientCategoryMod }">
				</td>
			<td class="multiControl" style="display: none"><input type="text" class="txtFldSmall" name="strPackProcdCost" maxlength="9" value="${chargeBean.strPackProcdCost}"
				onkeypress="return validateData(event,7);" onkeyup="enterAmount('0');"></td>
			<td class="multiControl"><input type="text" class="txtFldSmall" name="strPackTariffCost" maxlength="9" value="${chargeBean.strPackTariffCost}"
				onkeypress="return validateData(event,7);" onkeyup="enterAmount('0');"></td>
			<td class="multiControl"><input type="text" class="txtFldSmall" readonly="readonly" name="strPackTotalCost" maxlength="9"
				value="${chargeBean.strPackProcdCost + chargeBean.strPackTariffCost}" onkeypress="return validateData(event,7);"></td>
			<!-- <td class="multiControl"><input type="checkbox" name="strPackIsAdvance"></td> -->
			<td class="multiControl"><html:checkbox name="chargeBean" property="strPackIsRefundable" value="1"></html:checkbox></td>
			<td class="multiControl"><html:checkbox name="chargeBean" property="strPackIsAdvance" value="1"></html:checkbox></td>
		</tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="HEADER">
			<td colspan="8">Package Details</td>
		</tr>
		<tr>

			<td class="multiLabel" width="15%">Tariff Name</td>
			<td class="multiLabel" width="15%">Quantity</td>
			<td class="multiLabel" width="15%">Unit</td>
			<td class="multiLabel" width="15%">Procedure Cost/unit(<img src='../../hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="15%">Tariff Cost/unit(<img src='../../hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="15%">Total Cost/unit(<img src='../../hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="10%">Refundable</td>
		</tr>
	</table>
	<div id="packDetailsId"><bean:write name="chargeBean"
		property="strPackageDetails" filter="false" /></div>
	</div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL" colspan="3"><textarea rows="2"
				name="strRemarks"><bean:write name="chargeBean"
				property="strRemarks"></bean:write></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<div align="center"><!-- <img name="save"
		src="../../hisglobal/images/btn-sv.png"
		style="cursor: pointer; cursor: hand;" title="Save Record"
		onClick="saveModifiedData()" /> <img
		src="../../hisglobal/images/btn-ccl.png"
		style="cursor: pointer; cursor: hand;" title="Cancel the Process"
		onclick="cancel('LIST');">
		 -->
		<br><a href="#" class="button" id="" onclick='saveModifiedData();'><span class="save">Save</span></a>
				<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
		</div>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="chk" value="${chargeBean.chk[0]}">
	<input type="hidden" name="strGroupNameHidden"
		value="${chargeBean.strGroupName}">
	<input type="hidden" name="strUnitHidden" value="${chargeBean.strUnit}">
	<input type="hidden" name="strTempCategory"
		value="${chargeBean.strPatientCategory[0]}">
	<input type="hidden" name="hospitalService"
		value="${chargeBean.hospitalService }">
	<input type="hidden" name="strPackHospService"
		value="${chargeBean.strPackHospService }">
	<input type="hidden" name="strGroupName"
		value="${chargeBean.strGroupName }">
	<input type="hidden" name="strPackGroupName"
		value="${chargeBean.strPackGroupName }">
	<input type="hidden" name="strTariffName"
		value="${chargeBean.strTariffName }">
	<input type="hidden" name="strPackTariffName"
		value="${chargeBean.strPackTariffName }">
	<input type="hidden" name="strUnit" value="${chargeBean.strUnit }">
	<input type="hidden" name="strPackUnit"
		value="${chargeBean.strPackUnit }">
	<input type="hidden" name="strWardType"
		value="${chargeBean.strWardType}">
	<input type="hidden" name="strPackWardType"
		value="${chargeBean.strPackWardType}">
	<input type="hidden" name="strPatientCategoryMod"
		value="${chargeBean.strPatientCategoryMod}">
	<input type="hidden" name="strPackPatientCategory"
		value="${chargeBean.strPackPatientCategory}">
		

		
	<cmbPers:cmbPers />
</html:form>
<script>
showLabel();
if("${chargeBean.strIsPackage}"=="1")
	document.getElementsByName("chargeBean")[0].checked = true;

</script>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
