<%-- 
 Charge Master Batch Update jsp
 author: Debashis Sardar
  Created on : 06-Sep-2011
  --%>
  <!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>

<head>
<meta charset=utf-8>
<title>Batch Update</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<style type="text/css">@import url(../../hisglobal/css/calendar-tas.css);</style>
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" >
function chooseOption(){
	if(document.clientChargeBean.strSelectOption[0].checked){
		document.getElementById("newChange").style.display = "block";
		document.getElementById("copyTo").style.display = "none";
	} else {
		document.getElementById("newChange").style.display = "none";
		document.getElementById("copyTo").style.display = "block";
		if(document.clientChargeBean.hospitalService[document.clientChargeBean.hospitalService.selectedIndex].value != 2){
		document.getElementsByName("strCopyWardType")[0].disabled = true;
		document.getElementsByName("strCopyWardType")[0].value = "0";
		var objVal = document.getElementById("wardId1");
		objVal.innerHTML = "<select name = 'strCopyWardType'><option value='0'>Select Value</option></select>";
	}else{
		var temp1 = document.getElementsByName("hospitalService")[0].value;
		var mode="WRDCMB";
		var url="CNTClientChargeMst.cnt?hmode="+mode+"&service1="+temp1;
		
		ajaxFunction(url,"3");
	}
	}
}

function groupCombo(){


	var temp = document.getElementsByName("hospitalService")[0].value;
	var mode="GRPCMB";
	var url="CNTClientChargeMst.cnt?hmode="+mode+"&service="+temp;
	
	ajaxFunction(url,"1");
}

function wardCombo(){	
	if(document.clientChargeBean.hospitalService[document.clientChargeBean.hospitalService.selectedIndex].value != 2){
		document.getElementsByName("strWardType")[0].disabled = true;
		document.getElementsByName("strWardType")[0].value = "0";
		var objVal = document.getElementById("wardId");
		objVal.innerHTML = "<select name = 'strWardType'><option value='0'>Select Value</option></select>";
	}else{
		var temp1 = document.getElementsByName("hospitalService")[0].value;
		var mode="WRDCMB";
		var url="CNTClientChargeMst.cnt?hmode="+mode+"&service1="+temp1;
		ajaxFunction(url,"2");
	}
}

function getAjaxResponse(res,mode){
	for(var v = 0; v < 1000; v++){
	}
	if(mode==1){
	   
		var objVal = document.getElementById("grpName");
		objVal.innerHTML = "<select name = 'strGroupName' >" + res + "</select>";
		wardCombo();
	} else if(mode==2){
	  
		var objVal = document.getElementById("wardId");
		objVal.innerHTML = "<select name = 'strWardType'>" + res + "</select>";	
		
		var objVal = document.getElementById("wardId1");
		objVal.innerHTML = "<select name = 'strCopyWardType'>" + res + "</select>";
					
	} else if(mode == 3){
		var objVal = document.getElementById("wardId1");
		objVal.innerHTML = "<select name = 'strCopyWardType'>" + res + "</select>";
	}
}

function submitData(mode){	
	var hisValidator = new HISValidator("clientclientChargeBean");
	hisValidator.addValidation("hospitalService","dontselect=0","Please Select Hospital Service From The Hospital Service Combo");
	hisValidator.addValidation("strTempPatientCategory","dontselect=0","Please Select Patient Category From The Patient Category Combo");
	
	if(document.clientChargeBean.hospitalService[document.clientChargeBean.hospitalService.selectedIndex].value == 2){
		hisValidator.addValidation("strWardType","dontselect=0","Please Select Ward Type From The Ward Type Combo");
	}
			
	hisValidator.addValidation("strGroupName","dontselect=0","Please Select Group Name From The Group Name Combo");

	
	if(document.clientChargeBean.strSelectOption[0].checked){
		hisValidator.addValidation("strNewProcCost","req","New Procedure Cost is a Mandatory Field");
		hisValidator.addValidation("strNewProcCost","numltet=100","Please Select % Less Than Or Equal To 100");
		hisValidator.addValidation("strNewProcCost","amount=5,2","Please Enter Valid Percentage.");
		hisValidator.addValidation("strNewTrfCost","req","New Tariff Cost is a Mandatory Field");
		hisValidator.addValidation("strNewTrfCost","amount=5,2","Please Enter Valid Percentage.");
		hisValidator.addValidation("strNewTrfCost","numltet=100","Please Select % Less Than Or Equal To 100");
	} else {
		hisValidator.addValidation("strCopyPatCat","dontselect=0","Please Select Patient Category From The Patient Category Combo");
		if(document.clientChargeBean.hospitalService[document.clientChargeBean.hospitalService.selectedIndex].value == 2){
		hisValidator.addValidation("strCopyWardType","dontselect=0","Please Select Ward Type From The Ward Type Combo");
		}
		
		hisValidator.addValidation("strCopyProcCost","req","Copy Procedure Cost is a Mandatory Field");
		hisValidator.addValidation("strCopyProcCost","numltet=100","Please Select % Less Than Or Equal To 100");
		hisValidator.addValidation("strCopyProcCost","amount=5,2","Please Enter Valid Percentage.");
		hisValidator.addValidation("strCopyTrfCost","req","Copy Tariff Cost is a Mandatory Field");
		hisValidator.addValidation("strCopyTrfCost","amount=5,2","Please Enter Valid Percentage.");
		hisValidator.addValidation("strCopyTrfCost","numltet=100","Please Select % Less Than Or Equal To 100");
		if(document.clientChargeBean.strTempPatientCategory.value == document.clientChargeBean.strCopyPatCat.value){
			alert("Please Choose Different Patient Categories");
			return false;
		}
	}
	
	hisValidator.addValidation("strEffectiveFrm","dtgtet="+"${clientChargeBean.strEffectiveFrm}","Please Select Effective From Date Greater Than Or Equal To Current Date.");	
	if(document.clientChargeBean.strEffectiveTo.value != "")
		hisValidator.addValidation("strEffectiveTo","dtgtet="+document.clientChargeBean.strEffectiveFrm.value,"Please Select Effective To Date Greater Than Or Equal To Effective From Date.");
	var retVal = hisValidator.validate();	
	if(retVal){
		var obj = document.getElementsByName("strLevel");
		if(obj[0].value == "+"){
			document.clientChargeBean.strNewProcCost.value = (document.clientChargeBean.strNewProcCost.value/100);
		} else {
			document.clientChargeBean.strNewProcCost.value = -(document.clientChargeBean.strNewProcCost.value/100);
		}
		if(obj[1].value == "+"){
			document.clientChargeBean.strNewTrfCost.value = document.clientChargeBean.strNewTrfCost.value/100;
		} else {
			document.clientChargeBean.strNewTrfCost.value = -(document.clientChargeBean.strNewTrfCost.value/100);
		}
		if(!document.clientChargeBean.strSelectOption[0].checked){
			if(obj[2].value == "+"){
				document.clientChargeBean.strCopyProcCost.value = document.clientChargeBean.strCopyProcCost.value/100;
			} else {
				document.clientChargeBean.strCopyProcCost.value = -(document.clientChargeBean.strCopyProcCost.value/100);
			}
			if(obj[3].value == "+"){
				document.clientChargeBean.strCopyTrfCost.value = document.clientChargeBean.strCopyTrfCost.value/100;
			} else {
			document.clientChargeBean.strCopyTrfCost.value = -(document.clientChargeBean.strCopyTrfCost.value/100);
			}		
		}
		document.clientChargeBean.hmode.value = mode;
		document.clientChargeBean.submit();
	}
	else{		
		return false;
	}
}
</script>
</head>
<body>
<html:form action="/masters/CNTClientChargeMst.cnt"
	name="clientChargeBean" type="billing.masters.controller.fb.ClientChargeMstFB">
	<div class="errMsg" align="center"><bean:write name="clientChargeBean" property="strErrMsg"/> </div>
	<div class="normalMsg" id="normalMsg" align="center"><bean:write name="clientChargeBean" property="strNormalMsg"/></div>
	
	<tag:tab tabLabel="Batch Update Charge" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab>
	
	<table class="TABLEWIDTH" border="0" align="center">
		<tr class="HEADER">
			<td colspan="4">Client Charge Master&gt;&gt; Batch Update</td>
		</tr>
		<tr>
			<td colspan="2" class="CONTROL">&nbsp;</td>
			<td class="LABEL"><font color="red">*</font>Hospital Service</td>
			<td class="CONTROL"><select name="hospitalService" onChange="groupCombo();">
			<bean:write name="clientChargeBean" property="strHospitalServiceCombo" filter="false"/>
			</select></td>
		</tr>
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>Patient
			Category</td>
			<td class="CONTROL" width="30%"><select name="strTempPatientCategory">
				<bean:write name="clientChargeBean" property="patientCategory" filter="false"/>
			</select></td>
			<td class="LABEL" width="20%"><font color="red">*</font>Ward Type</td>
			<td class="CONTROL" width="30%">
			<div id="wardId"><select name="strWardType">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Group Name</td>
			<td class="CONTROL" colspan="3">
			<div id="grpName"><select name="strGroupName">
				<option>Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="CONTROL" colspan="4"><input type="radio"
				name="strSelectOption" value="0" onclick="chooseOption();"
				checked="checked"> Apply New Change</td>
		</tr>
		<tr>
			<td class="CONTROL" colspan="4"><input type="radio"
				name="strSelectOption" value="1" onclick="chooseOption();"> Copy To
			</td>
		</tr>
	</table>
	<div id="newChange" style="display: block;">
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>New Procedure Cost</td>
			<td class="CONTROL" width="80%"><input type="text"
				name="strNewProcCost" maxlength="5" class="txtFldSmall"
				onkeypress="return validateData(event,7);">% <select name="strLevel">
				<option value="+">Hike</option>
				<option value="-">Down</option>
			</select>Of Original Cost</td>
		</tr>
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>New Tariff Cost</td>
			<td class="CONTROL" width="80%"><input type="text"
				name="strNewTrfCost" maxlength="5" class="txtFldSmall"
				onkeypress="return validateData(event,7);">% <select name="strLevel">
				<option value="+">Hike</option>
				<option value="-">Down</option>
			</select>Of Original Cost</td>
		</tr>
	</table>
	</div>
	<div id="copyTo" style="display: none;">
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>Patient
			Category</td>
			<td class="CONTROL" width="80%"><select name="strCopyPatCat">
				
				<bean:write name="clientChargeBean" property="patientCategory" filter="false"/>
				
			</select></td>
		</tr>
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>Ward Type</td>
			<td class="CONTROL" width="80%"><div id="wardId1">
			<select name="strCopyWardType">
				<option>Select Value</option>
			</select></div></td>
		</tr>
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>New Procedure Cost</td>
			<td class="CONTROL" width="80%"><input type="text"
				name="strCopyProcCost" maxlength="5" class="txtFldSmall"
				onkeypress="return validateData(event,7);">% <select name="strLevel">
				<option value="+">Hike</option>
				<option value="-">Down</option>
			</select>Of Actual Cost Of Above Criteria</td>
		</tr>
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>New Tariff Cost</td>
			<td class="CONTROL" width="80%"><input type="text"
				name="strCopyTrfCost" maxlength="5" class="txtFldSmall"
				onkeypress="return validateData(event,7);">% <select name="strLevel">
				<option value="+">Hike</option>
				<option value="-">Down</option>
			</select>Of Actual Cost Of Above Criteria</td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td class="LABEL" width="20%"><font color="red">*</font>Effective
			From</td>
			<td class="CONTROL" width="80%"><dateTag:date name="strEffectiveFrm"
				value="${clientChargeBean.strEffectiveFrm}"></dateTag:date></td>
		</tr>
		<tr>
			<td class="LABEL" width="20%">Effective To</td>
			<td class="CONTROL" width="80%"><dateTag:date name="strEffectiveTo"></dateTag:date>
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<div align="center">
	<!-- <img src="../../hisglobal/images/btn-ok.png" style="cursor:pointer;cursor:hand;" title="Update Batch" 
		onclick="return submitData('SAVEBATCH');" /> 
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer;cursor:hand;" title='Cancel the Process' onClick="cancel('LIST');" >
		-->
		<br><a href="#" class="button" id="" onclick='return submitData('SAVEBATCH');'><span class="save">Batch Update</span></a>
				<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	</div>
	<input type="hidden" name="hmode">
	<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
