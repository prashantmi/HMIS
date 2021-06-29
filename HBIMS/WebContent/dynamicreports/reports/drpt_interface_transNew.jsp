<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>


<html>
<head>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dynamic Reports</title>

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css"
	rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css"
	rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css"
	rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet"
	type="text/css">
<link
	href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>





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
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
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

		var url = "DynamicReportsTransBSCNT.cnt?hmode=" + mode
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
	 
		var url = "DynamicReportsTransBSCNT.cnt?hmode=" + mode
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
	var url = "DynamicReportsTransBSCNT.cnt?hmode=" + mode + "&strReportTemplateId="
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
	<html:form action="/reports/DynamicReportsTransBSCNT"
		name="drptBean"
		type="dynamicreports.reports.controller.fb.DynamicReportsTransFB" styleClass="formbg" >



			<div class="viewport" id="nonPrintable">
				<div class="container-fluid">

					
					<div class="row justify-content-center">
						<div class="col-sm-12">
						<br>
							<div class="prescriptionTile">
							
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
							<div class="row rowFlex reFlex" >
									<div class="legend2" id='nonPrintableLegend2' style="top: 0.5em;">
										<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
											<i class="fas fa-ban iround"  title="Cancel"></i>
										</button>	
										<button type="button" class=" btn btn-secondary btn-circle"  style="background: #b9b9b9; border-color:#b9b9b9;margin-top: 0.25rem !important;">
														 	<img  src="/HIS/hisglobal/images/clear3.png" title="Clear" style="width: 23px; color: #fff;">
														</button>
									    <button  type="button" id="savebutton" class="float-right btn btn-outline-success mt-1 btn-circle" tabindex='2' onClick="return validateInsert();" style="background-color: #5cb85c;">					
											<i class="fa fa-download iround"  title="Generate" ></i>
										</button>
									  </div> 
									</div>

							<div class="row rowFlex reFlex">
								<div class="col-sm-10">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success btn-circle1 ">
											<i class="fas fa-file-alt iround" title="Cancel"></i>
										</button>
										&nbsp;Dynamic Reports
									</p>

								</div>

							</div>

			

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
			cellspacing='1px' cellpadding='1px' style="display: none;">

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
		                        <hr>
		                        <div class="row rowFlex reFlex">
		 							<div class="col-sm-10"></div>
									<div class="col-sm-2" align="right">
										<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>Fields Mandatory
									</div>
								</div>
	 <input type="hidden" name="hmode" />
	 <input type="hidden" name="strReportDisplayName"  value='' />
	 <input type="hidden" name="strReportWidth" value='' />
	 <input type="hidden" name="strReportWidthUnit" value = ''/>
	 <input type="hidden" name="strReportBorderReq"  value = ''/>
	 <input type="hidden" name="strOrderString"  value = ''/>
	  <input type="hidden" name="nCurrentLevel"  value = '0'/>
	  <input type="hidden" name="strReportTypeId"  value = '0'/>
	 
	 <html:hidden property="strReportHiddenId" name="drptBean"/>
	 
	 </div>
	 </div>
	 </div>
	 </div>
	 </div>
	</html:form>
	<tag:autoIndex></tag:autoIndex>
	
	<script type="text/javascript">
	/* 
	$(function() {
		alert("-----------------");
	    $( ".datepicker" ).datepicker(
	    		{ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'}		
	    );
	}); */ 
	
	$(document).ready( function() {
	    var now = new Date();
	    var today = now.getDate()  + '/' + (now.getMonth() + 1) + '/' + now.getFullYear();
	  // alert(today);
	    $('#datePicker').val(today);
	   // $(date).val(today);
	});
	
	//$('#datepicker').datepicker({ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	//$('#datepicker1').datepicker({ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
	var today=new Date();
	var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
	var mmm=arr[today.getMonth()];
	var hrs=today.getHours();
	var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
	$('#datepicker1').val(dd);
	$('#datepicker').val(dd);
	
	
	</script>
	
	
</body>
</html>