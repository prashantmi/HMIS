<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8;" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head>

<meta charset=utf-8>
<title>Charge Master Add Page</title>

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
<script language="JavaScript" src="../js/ChargeMstAdd.js"></script>
<script language="JavaScript" >

</script>
</head>
<body
	onload="resetServices(),addRows(new Array('strPatientCategory','strProductCost','strTariffCost','strIsAdvance','strIsRefundable'),new Array('s','t','t','c','c'),'1','1','I');">
<html:form action="/masters/CNTChargeMst.cnt" name="chargeBean"
	type="billing.masters.controller.fb.ChargeMstFB">
	<div class="errMsg" id="errMsg"><bean:write name="chargeBean"
		property="strErrMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="chargeBean" property="strWarningMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="chargeBean" property="strNormalMsg" /></div>

	<tag:tab tabLabel="Add Charge" selectedTab="FIRST" align="center" onlyTabIndexing="0"
		width="TABLEWIDTH">
	</tag:tab>

	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">
			<table width="100%" border="0" align="center" cellspacing="1px">
				<tr class="HEADER">
					<td width="50%">
					<div align="left">Charge Master&gt;&gt;Add</div>
					</td>
					<td width="50%">
					<div align="right"><input type="checkbox" name="strIsPackage"
						onClick="servicePackage();">Package</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<div id="forServices" style="display: block">
	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr>
			<td class="CONTROL" colspan="4"></td>
			<td class="LABEL"><font color="red">*</font>Hospital Service</td>
			<td class="CONTROL" colspan="2">
			<select name="hospitalService" class="comboNormal" onChange="groupCombo();">
				<bean:write name="chargeBean" property="strHospitalServiceCombo" filter="false" />
			</select></td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Group Name</td>
			<td class="CONTROL" colspan="6">
			<div id="grpName">
			<select name="strGroupName" class="comboNormal" onChange="tariffCombo();">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>

		<tr>
			<td class="LABEL"><font color="red">*</font>Tariff/Package Name</td>
			<td class="CONTROL" colspan="3">
			<div id="trfId"><select name="strTariffName" class="comboNormal" onchange="changeCatCombo(this)">
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td class="LABEL"><font color="red">*</font>Unit</td>
			<td class="CONTROL" colspan="2">
			<div id="unitId"><select name="strUnit" class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL">Ward Type</td>
			<td class="CONTROL" colspan="6">
			<div id="wardId"><select name="strWardType" class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
			</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td class="TITLE" colspan="5">
			<div id="divPreviousDataPlusID" align="left"><img
				src="../../hisglobal/images/plus.gif"
				title='Show Previous Data Details'
				onclick="callDtl();"
				style="cursor: pointer;"> Previous Data</div>
			<div id="divPreviousDataMinusID" style="display: none;" style="display: block;"
				align="left"><img src="../../hisglobal/images/minus.gif"
				title='Hide Previous Data Details'
				onclick="hideDiv('divPreviousDataMinusID'),hideDiv('previousDataDetailsDivId'),showDiv('divPreviousDataPlusID');"
				style="cursor: pointer;"> Previous Data</div>
			</td>
		</tr>
	</table>
	<div id="previousDataDetailsDivId" style="display:none"></div>
	
	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr class="TITLE">
			<td colspan="7">Current Charges</td>
		</tr>
		<tr>
			<td class="multiLabel" width="20%"><font color="red">*</font>Patient Category .<input type="checkbox" name="strIsAllCategory" value="1" onclick="chkAllCategory();">All</td>
			<td class="multiLabel" width="14%" style="display: none"><font color="red">*</font>Procedure Cost(<img src='../../hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="14%"><font color="red">*</font>Tariff Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="14%"><font color="red">*</font>Total Cost(<img src='/HBIMS/hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="14%">IsAdvance</td>
			<td class="multiLabel" width="14%">IsRefundable</td>
			<td class="multiLabel" width="10%"><div id="plusDiv" style="display: block;cursor:pointer;"><img src="../../hisglobal/images/plus.gif" onClick="addMoreRows();"></div></td>
		</tr>
	</table>
	<div id="id1"></div>
	<div id="allCatDivId" style="display: none">

	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr>
			<td class="multiControl" width="20%"><select class="comboNormal" name="strAllPatientCategory">
				<option value="1">All</option>
			</select></td>
			  <td style="display: none" class="multiControl" width="14%"><input class="txtFldSmall" autocomplete='off'
				type="text" name="strAllProductCost" value="0" maxlength="9"
				onkeypress="return validateData(event,7);"
				onkeyup="calcAllTotalAmount();"></td>
			<td class="multiControl" width="14%"><input class="txtFldSmall" autocomplete='off'
				type="text" name="strAllTariffCost" value="" maxlength="9"
				onkeypress="return validateData(event,7);"
				onkeyup="calcAllTotalAmount();"></td>
			<td class="multiControl" width="14%"><input class="txtFldSmall" autocomplete='off'
				type="text" name="strAllTotalCost" value="" maxlength="9"
				onkeypress="return validateData(event,7);" readonly="readonly"></td>
			<td class="multiControl" width="14%"><input type="checkbox"
				name="strAllIsAdvance" value="1" checked='checked'></td>
			<td class="multiControl" width="14%"><input type="checkbox"
				name="strAllIsRefundable" value="1" checked='checked'></td>
			<td class="multiControl" width="10%"></td>
		</tr>
	</table>

	</div>
	</div>

	<div id="forPackages" style="display: none">
	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr>
			<td class="CONTROL" width="50%" colspan="2">&nbsp;</td>
			<td class="LABEL" width="25%"><font color="red">*</font>Hospital Service</td>
			<td class="CONTROL" width="25%"><select name="strPackHospService" class="comboNormal" onChange="packGroupCombo();">
				<bean:write name="chargeBean" property="strHospitalServiceCombo" filter="false" />
			</select></td>
		</tr>
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Group Name</td>
			<td class="CONTROL" width="25%" colspan=3>
			<div id="grpName1"><select name="strPackGroupName" class="comboNormal" onChange="packTariffCombo();">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Tariff/Package Name</td>
			<td class="CONTROL" width="25%">
			<div id="trfId1"><select name="strPackTariffName" class="comboNormal" onChange="packUnitCombo();">
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td class="LABEL" width="25%"><font color="red">*</font>Unit</td>
			<td class="CONTROL" width="25%">
			<div id="unitId1"><select name="strPackUnit" class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Ward Type</td>
			<td class="CONTROL" width="75%" colspan=3>
			<div id="wardId1"><select name="strPackWardType" class="comboNormal">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="TITLE" colspan="5">
			<div id="divPreviousDataPPlusID" align="left"><img
				src="../../hisglobal/images/plus.gif"
				title='Show Previous Data Details'
				onclick="callDtl1();"
				style="cursor: pointer;"> Previous Data</div>
			<div id="divPreviousDataPMinusID" style="display: none;" style="display: block;"
				align="left"><img src="../../hisglobal/images/minus.gif"
				title='Hide Previous Data Details'
				onclick="hideDiv('divPreviousDataPMinusID'),hideDiv('previousPackDataDetailsDivId'),showDiv('divPreviousDataPPlusID');"
				style="cursor: pointer;"> Previous Data</div>
			</td>
		</tr>
	</table>
	<div id="previousPackDataDetailsDivId"></div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr><td class="TITLE" colspan="6"></td></tr>
		<tr>
			<td class="multiLabel" width="20%"><font color="red">*</font>Patient Category</td>
			<td class="multiLabel" width="16%" style="display: none;"><font color="red">*</font>Procedure Cost</td>
			<td class="multiLabel" width="16%"><font color="red">*</font>Tariff Cost</td>
			<td class="multiLabel" width="16%">Total Cost</td>
			<!-- <td class="multiLabel" width="16%">Is-Advance</td> -->
			<td class="multiLabel" width="16%">Is-Refundable</td>
			<td class="multiLabel" width="16%">Is-Advance</td>
		</tr>

		<tr>
			<td class="multiControl">
				<div id="strPackPatientCategorydiv">
					<select name="strPackPatientCategory" class="comboNormal">
						<bean:write name="chargeBean" property="patientCategory" filter="false" />
					</select>
				</div>
			</td>
			<td class="multiControl" style="display: none;">
				<input type="text" class="txtFldNormal" autocomplete='off' name="strPackProcdCost" id="strPackProcdCost" maxlength="9" value="0" onkeypress="return validateData(event,7);"
				onkeyup="enterAmount('0');">
			</td>
			<td class="multiControl">
				<input type="text" class="txtFldSmall" autocomplete='off' name="strPackTariffCost" id="strPackTariffCost" maxlength="9" value="0" onkeypress="return validateData(event,7);"
				onkeyup="enterAmount('0');">
			</td>
			<td class="multiControl">
				<input type="text" class="txtFldSmall" autocomplete='off' name="strPackTotalCost" id="strPackTotalCost" maxlength="9" value="0" readonly="readonly"
				onkeypress="return validateData(event,7);">
			</td>
			<!-- <td class="multiControl"><input type="checkbox"
				name="strPackIsAdvance"></td> -->
			<td class="multiControl"><input type="checkbox" name="strPackIsRefundable" value='0' checked='checked'></td>
			<td class="multiControl"><input type="checkbox" name="strPackIsAdvance" value='0' checked='checked'></td>
		</tr>
	</table>
	<table class="TABLEWIDTH" border="0" align="center" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="8">Package Details</td>
		</tr>
		<tr>

			<td class="multiLabel" width="15%">Tariff Name</td>
			<td class="multiLabel" width="15%">Quantity</td>
			<td class="multiLabel" width="15%">Unit</td>
			<td class="multiLabel" width="15%" style="display: none;">Procedure Cost/Unit(<img src='../../hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="15%">Tariff Cost/Unit(<img src='../../hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="15%">Total Cost/Unit(<img src='../../hisglobal/images/INR.png'>)</td>
			<td class="multiLabel" width="10%">Refundable</td>
		</tr>
	</table>
	<div id="packDetailsId"></div>
	</div>
	<table class="TABLEWIDTH" border="0" align="center">
		<tr>
			<td width="50%" class="LABEL"><font color="red">*</font>Effective From</td>
			<td width="50%" class="CONTROL"><dateTag:date name="strEffectiveFrm" value="${chargeBean.strEffectiveFrm}"></dateTag:date></td>
		</tr>
		<tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><textarea rows="2" name="strRemarks"></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="2"><font color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	<div align="center"><!-- <img name="save"
		src="../../hisglobal/images/btn-sv.png"
		style="cursor: pointer; cursor: hand;" title="Save Record"
		onClick="return submitData('SAVEADD');"> <img
		src="../../hisglobal/images/btn-clr.png"
		style="cursor: pointer; cursor: hand;" title="Reset Content"
		onClick="resetChargeMst();" /> <img
		src="../../hisglobal/images/btn-ccl.png"
		style="cursor: pointer; cursor: hand;" title="Cancel Process"
		onclick="cancel('LIST');">
		-->
		<br><a href="#" class="button" id="" onclick="return submitData('SAVEADD');"><span class="save">Save</span></a>
							<a href="#" class="button"	onclick="resetChargeMst();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
		
		
		</div>
	<input type="hidden" name="hmode" value="" />
	<input type="hidden" name="strCurrentDate" value="${chargeBean.strCurrentDate}" />
	<input type="hidden" name="previousDataFlag" value="0" />
	<input type="hidden" name="packPreviousDataFlag" value="0" />
	<cmbPers:cmbPers />
</html:form>
<jsp:include page="addmultirow_chargeMst_bill.jsp"></jsp:include>
<tag:autoIndex></tag:autoIndex>
</body>
</html>
