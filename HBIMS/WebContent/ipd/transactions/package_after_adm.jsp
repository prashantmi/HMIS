<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<html>
<head>
<meta charset=utf-8>
<title>Package App(Adm)</title>
<link href="../../ipd/css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/popup.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newMultiRow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>

</script>
<script type='text/javascript'>
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
//,document.forms[0].nNoOfRow.focus()  on body onload(deleted due to error in IE.)

function validate()
{
	
	var hisValidator = new HISValidator("PackAdmFB");  
	hisValidator.addValidation("strPackageApply", "req", "Package Application is a Mandatory Field" );
	hisValidator.addValidation("strPackAppDate", "req", "Package Application Date is a Mandatory Field"  );
	var retVal = hisValidator.validate();
	if(retVal)
	{
	document.forms[0].hmode.value="INSERT";
	document.forms[0].submit();
    }
    else
		return false;
}
function goFuncNA(){
	
	var hisValidator = new HISValidator("PackAdmFB");  
	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
	hisValidator.addValidation("strCrNo","minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
	var retVal = hisValidator.validate();
	//document.forms[0].strSaveFlag.value="1";
	//document.forms[0].strCrNo.value="";
	if(retVal)
	{
		document.forms[0].hmode.value="GOPatient";
		document.forms[0].submit();
	}
	else
	{		
		return false;
	}		
}
function clearRecord()
{
	/*document.forms[0].strCrNo.value="";
	document.getElementById("errID").innerHTML="";
	document.getElementById("normalMsg").innerHTML="";
	document.getElementById("wrnID").innerHTML="";
	viewA();*/
	document.forms[0].hmode.value="CLEAR";
	/*document.forms[0].strSaveFlag.value="";
	document.forms[0].strPatientCrNo.value="";
	document.forms[0].strCaseSheetNo.value="";*/
	document.forms[0].submit();
}
</script>
</head>

<body onload="SetCursorToTextEnd('strCrNoId');">
<html:form action="/transactions/PackAdmTransCNT" method="post">
	<div class="errMsg" id="errID"><bean:write
		name="PackAdmFB" property="strMsgString" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="PackAdmFB" property="strMsg" filter="false" /></div>
	<div class="warningMsg" id="wrnID"><bean:write
		name="PackAdmFB" property="strWarningMsg" /></div>
	<table class="TABLEWIDTH" align='center' cellspacing='1px'>
		<tr class="HEADER">
			<td colspan="6">Package Application After Admission</td>
		</tr>
	</table>
	<div id="crNoId">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL"><font color="red" id="mandCRId">*</font>CR
			No.</td>
			<td class="CONTROL" nowrap="nowrap">
			<div id="patientCrEdId"><crNo:crNo id="strCrNoId"
				value="${PackAdmFB.strCrNo}"
				js="	
				onkeypress='return goRetFuncNA(event);return validateData(event,5);'"></crNo:crNo>
			<!--  <img style="cursor: pointer; cursor: hand;" id="searhPatientImageId"
				src="../../hisglobal/images/viewDetails.gif"
				title="Click here for Patient Search" align="middle"
				name='searchPatient'
				onclick="showPatientListingWindow('4',document.forms[0].strCrNo,'setSelectedCrNo');" />-->
			<img src="../../hisglobal/images/Go.png"
				onClick="return goFuncNA();" align="top" style="cursor: pointer;">
			</div>
			</td>
		</tr>
	</table>
	</div>
	<div id="admitdetail" style='display: block'><bean:write
		name="PackAdmFB" property="strAdmitDetailProperty"
		filter='false' /></div>
	<logic:notEqual name="PackAdmFB"  property="strCrNo" value="">
	<div id="apppack">
	<table class="TABLEWIDTH" align="center" cellspacing="1px">
	<tr>
	 <td width="50%" class="LABEL"><font color="red" id="mandCRId">*</font>Apply OT Package</td>
    	   <td width="50%" class="LABEL"><div align="left">
	    	   <select name="strPackageApply" class="comboNormal">
			   	<bean:write name="PackAdmFB" property="strPackageComboValues" filter="false"/>
			   </select></div>
    	   </td>
    	   </tr>
    	   <tr>
    	   <td width="50%" class="LABEL"><font color="red" id="mandCRId">*</font>Date of Applying Package</td>
    	   <td width="50%" class="CONTROL">
    	   <date:date name="strPackAppDate" value="${PackAdmFB.strCurrentDate}"></date:date>
    	   </td>
    </table>
    </div>
    </logic:notEqual>
	
	<table border="0" class="TABLEWIDTH" align="center">
	    <tr class="FOOTER">
			<td colspan="4"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
		    <td align="center">
		    <!-- <img
				src="../../hisglobal/images/btn-sv.png" style='cursor: pointer;'
				title='Save Record' onClick="validateNr();" /> <img
				src="../../hisglobal/images/btn-ccl.png" style='cursor: pointer;'
				title='Return to main page' onclick="cancelFunc();" /> -->
				
				<br>
				<a href="#" class="button"	onClick="validate();"><span class="Save">Save</span></a> 
				<a href="#" class="button"	onClick="clearRecord();"><span class="clear">Clear</span></a> 
				<a href="#" class="button" onclick="cancelFunc();"><span class="cancel">Cancel</span></a>
				</td>
		 
		</tr>
	</table>

	<input type="hidden" name="hmode">
	<input type='hidden' name='hiddencrno'>
	<input type='hidden' name='hiddenadmno'>
	<input type='hidden' name='hiddenbed'>
	<input type='hidden' name='hiddennursecheck'>
	<input type='hidden' name='hiddenchkremark'>
	<input type='hidden' name='hiddenflag'>
	<input type='hidden' name='hiddenbelonging'>
	<input type='hidden' name='strhiddendepartment'>
	<input type='hidden' name='strhiddendunit'>
	<input type='hidden' name='strhiddenward'>
	<input type='hidden' name='strhiddenRoom' value="${PackAdmFB.strRoom}">
	<input type='hidden' name='stroldradio'>
	<input type='hidden' name='strhtransinflag'>
	<input type='hidden' name='strhmoveno' value="">
	<input type='hidden' name='delRowsNo' id="delRowsNo" value="0">
	<input type="hidden" name="rowIndex1" value="0">
	<input type="hidden" name="rowLength1" value="0">
	<input type="hidden" name="strDeptCode" value='${PackAdmFB.strDeptCode }'>
	<input type="hidden" name="strWardCode" value='${PackAdmFB.strWardCode }'>
	<input type="hidden" name="strSaveFlag" value="${PackAdmFB.strSaveFlag}">
	<input type="hidden" name="strTempCtDate"
		value="${PackAdmFB.strCurrentDate}" />
	<input type="hidden" name="index">
	
	<cmbPers:cmbPers />
</html:form>
</body>
</html>
