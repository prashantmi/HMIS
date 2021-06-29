<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Dynamic Reports</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">	

<script language="JavaScript"
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newpopup.js"></script>

<script type="text/javascript">


function validateInsert(){
	
	var hisValidator = new HISValidator("drptBean");
	
	hisValidator.addValidation("strReportTemplateId", "dontselect=0",
	"Please Select a Report Name");
	
	
	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

if(retVal){
	
	
	var strTempParamIds = new Array();

	var strInConstantValueXObj = document
			.getElementsByName("strInConstantValueX");

	var inParamComboVal =   document.getElementsByName("strInParamComboVal");
	var inCompType =    document.getElementsByName("strInCompType");
	
	var strInParamDisplayValue =    document.getElementsByName("strInParamDisplayValue");
	
	var vCount = parseInt("0");
	
	
	if (strInConstantValueXObj.length > 0) {

		var strInConstantValueObj = document
				.getElementsByName("strInConstantValue");

		for ( var x = 0; x <= strInConstantValueXObj.length - 1; x++) {

			for ( var y = 0; y <= strInConstantValueObj.length - 1; y++) {
				
								
				if ((strInConstantValueObj[y].type != "text") && ( strInConstantValueObj[y].value == ""
						|| strInConstantValueObj[y].value.length == 0)) {

					strInConstantValueObj[y].value = strInConstantValueXObj[x].value;
										
					strTempParamIds[x] = strInConstantValueObj[y].id;
										
					break;
				}
								
			}

			
		}

				
	}
	
	if (strInConstantValueXObj.length > 0) {
	for ( var y = 0; y <= strInConstantValueObj.length - 1; y++) {
		
		if(inCompType[y].value != '0'){
			 
			strInParamDisplayValue[vCount].value = strInConstantValueObj[y].value;
			
			vCount = vCount + 1;
			
		}
		
	}
		
	}
	
	
	var strInConstantValueObjArr = document.getElementsByName("strInConstantValue");
	var strInValueObjArr =   document.getElementsByName("strInParamComboVal");
	var strInCompType =    document.getElementsByName("strInCompType");
	
	var count = parseInt("0");

	for ( var i = 0; i <= strInConstantValueObjArr.length - 1; i++) {

		if (strInCompType[i].value != '0') {
			if (strInCompType[i].value == '2') {

				if (strInConstantValueObjArr[i].value == '0') {

					strInValueObjArr[count].value = "All";

				} else {
					strInValueObjArr[count].value = strInConstantValueObjArr[i][strInConstantValueObjArr[i].selectedIndex].text;

				}

				count = count + 1;
				
			}  

			
		}
	}

	var orderString = "1";

	var objOrder = document.getElementsByName("strOutColIsOrderBy");
	var objIndex = document.getElementsByName("strOutColActualIndex");
	 
	
	var orderFlag = false;
	for ( var i = 0; i < objOrder.length; i++) {

		if (objOrder[i].value == '1') {

			if (!orderFlag) {

				orderString = objIndex[i].value;

				orderFlag = true;
			}

			orderString = orderString + " # " + objIndex[i].value;

		}

	}

	
	document.forms[0].strOrderString.value = orderString;
	
	
	
	
	
	
	document.forms[0].target = "_blank";
	document.forms[0].hmode.value = "GENERATEREPORT";
	document.forms[0].submit();

	for ( var z = 0; z <= strTempParamIds.length - 1; z++) {

		document.getElementById(strTempParamIds[z]).value = "";

	}

} else {
	return false;
}
	
		
	
}


function cancelPage(){
	

	document.forms[0].target = "_self";
	document.forms[0].hmode.value = "CANCELPAGE";
	document.forms[0].submit();
	
}


/*
function getReportList(obj) {
	
	document.getElementById("rptInParamDtlsDivId").style.display="none";
	
	if (obj.value != '0') {
		
		setExportType(obj.value);
		
		var mode = 'GETREPORTLIST';
		var strReportTypeId = obj.value;

		var url = "DynamicReportsTransCNT.cnt?hmode=" + mode
				+ "&strReportTypeId=" + strReportTypeId;
		ajaxFunction(url, "1");

	} else {

		document.getElementById("reportNameDivId").innerHTML = "<select name='strReportTemplateId' class='comboMax'><option value='0'>Select Value</option></select>";
				
	}
	  
	
}
*/

function setExportType(obj){
	
	var exportTypeValues = "";
	
	if(obj.value == '0'){
		
		exportTypeValues = "<select name='strExportType' class='comboNormal'> <option value='html'>Html</option> </select>";
		
		document.forms[0].strReportTypeId.value = "0";
		
	}else{
		
		var rptType = obj.value.split('^')[5];
		
		document.forms[0].strReportTypeId.value = rptType;
		
		if(rptType == '1' || rptType == '2'){
			
			exportTypeValues = "<select name='strExportType' class='comboNormal'> <option value='html'>Html</option> </select>";
			
		}else if(rptType == '3'){
			
			exportTypeValues = "<select name='strExportType' class='comboNormal'> <option value='html'>Html</option> </select>"; 
			
		}else{
			
			exportTypeValues = "<select name='strExportType' class='comboNormal'> <option value='html'>Html</option>  </select>"; 
			
		}
		
		
		
		
	}
	

	document.getElementById("exportTypeDivId").innerHTML = exportTypeValues;
	
}


function getInParamDetails(obj){
	
		
	if (obj.value != '0') {
		
		var tempVal = obj.value.split('^');
		
		document.forms[0].strReportDisplayName.value = tempVal[1];
		document.forms[0].strReportWidth.value = tempVal[2];
		document.forms[0].strReportWidthUnit.value = tempVal[3];
		document.forms[0].strReportBorderReq.value = tempVal[4];
		
		var strReportTypeId = document.forms[0].strReportTypeId.value
		
		var mode = 'GETPARAMINDTL';
	 
		var url = "DynamicReportsTransCNT.cnt?hmode=" + mode
				+ "&strReportTemplateId=" + tempVal[0]+"&strReportTypeId="+strReportTypeId;
		
			ajaxFunction(url, "2");

	} else {
	
		document.forms[0].strReportDisplayName.value = "";
		document.forms[0].strReportWidth.value = "";
		document.forms[0].strReportWidthUnit.value = "";
		document.forms[0].strReportBorderReq.value = "";
		
		document.getElementById("rptInParamDtlsDivId").style.display = "none";
				
	}
		
}


function fetchCombos(ctCombo) {

	var rptTypeId = document.forms[0].strReportTypeId.value;
	var templateId = document.forms[0].strReportTemplateId[document.forms[0].strReportTemplateId.selectedIndex].value
			.split("^")[0];
	var isComboDept = document.forms[0].strTemplateProcIsComboDepend.value;
	var paramId = "";
	var comboValues = "";

	if (isComboDept == '0')
		return false;

	var paramObj = document.getElementsByName("strInParamId");

	// for last combo execution is not required.
	if (ctCombo == paramObj.length - 1)
		return false;

	for ( var i = 0; i <= paramObj.length - 1; i++) {

		if (i == 0) {

			paramId = paramObj[i].value;

		} else {

			paramId = paramId + "@" + paramObj[i].value;

		}

	}

	var strInConstantValueObjArr = document
			.getElementsByName("strInConstantValue");
	var strInCompType = document.getElementsByName("strInCompType"); // 1 - Text Box , 2 - Combo box , 3 - Date Picker  
	var flag = 0;

	for ( var i = 0; i <= strInConstantValueObjArr.length - 1; i++) {

		if (strInCompType[i].value == '2') {

			if (flag == 0) {

				comboValues = strInConstantValueObjArr[i].value;
				flag = 1;
			} else {

				comboValues = comboValues + "@"
						+ strInConstantValueObjArr[i].value;

			}

		}

	}

	var mode = "GETCOMBOS";
	var url = "DynamicReportsTransCNT.cnt?hmode=" + mode + "&strReportTemplateId="
			+ templateId +"&strReportTypeId="+rptTypeId+ "&strParamId=" + paramId + "&strComboValues="
			+ comboValues + "&strCtCombo=" + ctCombo;

	  
	ajaxFunction(url, "3");

}


 
function getAjaxResponse(res, mode) {
	var objVal1;
	var err = document.getElementById("errMsg");
	var temp1 = res.split("####");

	if (temp1[0] == "ERROR") {
		err.innerHTML = temp1[1];
	} else {

		 
	/*	if (mode == "1") {

			
			document.getElementById("reportNameDivId").innerHTML = "<select name='strReportTemplateId' class='comboMax' onchange='getInParamDetails(this);' >"
					+ res + "</select>";
					
		}
*/		
		
		if (mode == "2") {
						
			document.getElementById("rptInParamDtlsDivId").innerHTML = res;
			document.getElementById("rptInParamDtlsDivId").style.display = "block";
			
		}
		
		
		if (mode == "3") {

					
			var resVals = res.split("@@");

			var ctCombo = parseInt(resVals[0]) + 1;
			var totCombo = parseInt(resVals[1]);

			for ( var x = ctCombo, y = 2; x < totCombo; x++, y++) {

				var cmbObj = document.getElementById("comboDiv" + x);

				cmbObj.innerHTML = resVals[y];

			}

		}
		
		 
}

}

function initViewMode(){
	
	var mode = document.forms[0].strReportHiddenId.value;
	
	if(mode != '0'){
		
		document.getElementById("reportTemplateComboDivId").style.display = "none";
		
		setExportType(document.forms[0].strReportTemplateId);
		getInParamDetails(document.forms[0].strReportTemplateId);
			
	}else{
		
		document.getElementById("reportTemplateComboDivId").style.display = "block";
		
	}
	
	
	
	
}

	
</script>

<!-- 
/**
 * @author Balasubramaniam M
 * Date of Creation : 17/4/2012
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : Dynamic Report Generation
 */
 -->
</head>
<body class="background" onload="initViewMode();">
	<html:form action="/reports/DynamicReportsTransCNT"
		name="drptBean"
		type="dynamicreports.reports.controller.fb.DynamicReportsTransFB" styleClass="formbg">

		<center>
			<div id="errMsg" class="errMsg">
				<bean:write name="drptBean" property="strErrMsg" />
			</div>
			<div id="warningMsg" class="warningMsg">
				<bean:write name="drptBean" property="strWarningMsg" />
			</div>
			<div id="normalMsg" class="normalMsg">
				<bean:write name="drptBean" property="strNormalMsg" />
			</div>
		</center>

		<table class="TABLEWIDTH" align="center">
			<tr class="HEADER">
				<td colspan="4" class='all-four-rounded-corners'>Dynamic Reports</td>
			</tr>


		</table>

<div id="reportTemplateComboDivId">
		<table border="0" class="TABLEWIDTH" align="center" border='0'
			cellspacing='1px' cellpadding='1px'>

			<tr>
				<td class="LABEL" width="50%"><font color="red">*</font>Report Name</td>
				<td class="CONTROL" width="50%"><div id="reportNameDivId">
						<select name="strReportTemplateId" class="comboMax" onchange="setExportType(this),getInParamDetails(this);">
						<bean:write name="drptBean" property="strReportTemplateValues" filter="false"/>
						</select>
					</div></td>
			</tr>
		</table>
 
 </div>
 
 <div id="rptInParamDtlsDivId" style="display: none;" ></div>
 
 
 	<table border="0" class="TABLEWIDTH" align="center" border='0'
			cellspacing='1px' cellpadding='1px'>

			<tr>
				<td class="LABEL" width="50%">Export Type</td>
				<td class="CONTROL" width="50%"><div id='exportTypeDivId'><select name="strExportType"
					class="comboNormal" > 
					<option value='html'>Html</option>
					<!-- <option value='pdf'>Pdf</option>
					<option value='xls'>Excel</option> -->
				</select></div></td>

			</tr>
			
			<tr>
			<td class="LABEL" width="50%">
			Footer Required
			</td>
			<td class="CONTROL" width="50%">
			<input tabindex="1" name="strIsFooter" value="1" type="checkbox">
			</td>
			
		</tr>
		<tr>
			<td class="LABEL" width="50%">
			User Remarks
			</td>
			<td class="CONTROL" width="50%">
			<input class="txtFldMax" name="strUserRemarks" type="text">
			</td>
			
		</tr>
			
		</table>
 
 
 	<table border="0" class="TABLEWIDTH" align="center" border='0'
			cellspacing='1px' cellpadding='1px'>
			<tr class="FOOTER">
				<td colspan="4" class='all-four-rounded-corners'></td>
			</tr>
		</table>
 

		
		<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center">
			<div class="legends">
						<font color='red'>*</font>Mandatory Fields
					</div>
			<div class="control_button" align="left">
				<a href="#" class="button" style="width:170px" onClick="return validateInsert();"><span class="generate">Generate & Print</span></a>
				<a href="#" class="button" onClick="document.forms[0].reset();"><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancelPage();"><span class="cancel">Cancel</span></a>				 
				</div>			
			</td>			
		</tr>
	</table>
	
		<input type="hidden" name="hmode" />
	 
	 	<input type="hidden" name="strReportDisplayName"  value='' />
	 <input type="hidden" name="strReportWidth" value='' />
	 <input type="hidden" name="strReportWidthUnit" value = ''/>
	 <input type="hidden" name="strReportBorderReq"  value = ''/>
	 <input type="hidden" name="strOrderString"  value = ''/>
	  <input type="hidden" name="nCurrentLevel"  value = '0'/>
	  <input type="hidden" name="strReportTypeId"  value = '0'/>
	 
	 <html:hidden property="strReportHiddenId" name="drptBean"/>
	 
	 
	</html:form>
	<tag:autoIndex></tag:autoIndex>
</body>
</html>