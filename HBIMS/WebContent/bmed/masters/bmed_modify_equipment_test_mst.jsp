<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<!-- 
/**
 * @author Vivek Aggarwal
 * Date of Creation : 27-Jul-2012
 * Date of Modification :  27-Jul-2012
 * Version : 
 * Module  : BMED
 */
 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Equipment Test Master Modify Page</title>
<link href="../../hisglobal/css/emms.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tabIndex.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../bmed/js/equipmentTestMst.js"></script>

</head>

<body onload="chkRecordSaved()" class="background">
<html:form name="equipmentTestMstFB" action="/masters/EquipmentTestMstCNT"
	type="bmed.masters.controller.fb.EquipmentTestMstFB" styleClass="formbg">


	<div id="strErrMsg" class="errMsg"><bean:write name="equipmentTestMstFB"
		property="strErrMsg" /></div>
	<div class="warningMsg"><bean:write name="equipmentTestMstFB"
		property="strWarningMsg" /></div>
	<div style="display:none;" id="normalMsg" class="normalMsg"><bean:write name="equipmentTestMstFB"
		property="strNormalMsg" /></div>

	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="2">Equipment Test Master&gt;&gt;Modify</td>
		</tr>

	

		<tr>
			<td class="LABEL"><font color="red">*</font>Test Name</td>
			<td width="50%" class="CONTROL"><input type="text"
				name="strTestName" maxlength="250" value="${equipmentTestMstFB.strTestName}"
				onkeypress="return validateData(event,18);"></td>
		</tr>

		<tr>
			<td class="LABEL" width="50%"><font color="red">*</font>Effective
			From Date</td>
			<td class="CONTROL"><dateTag:date name="strEffectiveFrom"
				value="${equipmentTestMstFB.strEffectiveFrom}"></dateTag:date></td>
		</tr>

		<tr>
			<td class="LABEL">
			<div align="right">Remarks</div>
			</td>
			<td class="CONTROL">
			<div align="left"><textarea name="strRemarks" rows="2"><bean:write
				name="equipmentTestMstFB" property="strRemarks" /></textarea></div>
			</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Record Status</td>
			<td width="45%" class="CONTROL"><html:radio name="equipmentTestMstFB"
				property="strIsValid" value="1">Active</html:radio> <html:radio
				name="equipmentTestMstFB" property="strIsValid" value="2">Inactive</html:radio>

			</td>
		</tr>

	</table>
	
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<!--	<table cellspacing="1px" class="TABLEWIDTH" align="center">-->
			<tr class="FOOTER">
				<td></td>
			</tr>
		</table>
		
		
		<!-- <div class="legends"><font size="2" color="red">*</font>
		Mandatory Fields</div>


	<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div><a href="#" class="button" onClick="return validate2();"><span
					class="save">Save</span></a> <a href="#" class="button"
					onclick="document.forms[0].reset(),clearMsg('MODIFY')" ;><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div> -->
		
		<table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="return validate2();"/> <img
				src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer; cursor: hand;" title="Clear Process"
				onclick="document.forms[0].reset(),clearMsg('MODIFY')" ; />  <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();"/></td>
		</tr>
	</table>
		


	<input type="hidden" name="chk" value="${equipmentTestMstFB.chk[0] }">
	<input type="hidden" name="comboValue" value="${equipmentTestMstFB.strTestName}">
	<input type="hidden" name="hmode">
	<html:hidden name="equipmentTestMstFB" property="strEngineeringItemTypeId"/>
	<html:hidden name="equipmentTestMstFB" property="strEngineeringItemSubTypeId"/>
	<input type="hidden" name="strRetValue" value="${equipmentTestMstFB.strRetValue}" />
	<input type="hidden" name="strNormalMsg" value="${equipmentTestMstFB.strNormalMsg}" />

	<cmbPers:cmbPers />
</html:form>

</body>
</html>

