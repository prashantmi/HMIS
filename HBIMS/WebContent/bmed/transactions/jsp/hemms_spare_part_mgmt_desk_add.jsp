<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<%--
/**
 * Developer Name : T. Saratkumar
 * Process Name : Centralized Spare Parts Management Desk Add
 * Date : 30/Aug/2013
 * Modify By/Date : 28/May/2014
 */ 
 --%>

<html>
<head>

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



<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<!-- <script language="Javascript"src="../../bmed/js/complaintHierarchyMst.js"></script> -->
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/commonFunctions.js"></script>

<his:javascript
	src="/bmed/transactions/js/hemms_spare_part_mgmt_desk_trans.js" />

</head>

<body marginheight="0" marginwidth="0" onload="chkRecordSaved()"
	class="background">

<html:form name="hemmsSparePartMgmtDeskFB"
	action="/transactions/HemmsSparePartMgmtDeskACTION" styleClass="formbg"
	type="bmed.transactions.controller.fb.HemmsSparePartMgmtDeskFB">
	<div id="strErrMsg" align="center" class="errMsg"><bean:write
		name="hemmsSparePartMgmtDeskFB" property="strErrMsg" /></div>
	<div id="strWarningMsg" align="center" class="warningMsg"><bean:write
		name="hemmsSparePartMgmtDeskFB" property="strWarningMsg" /></div>
	<div id="normalMsg" align="center" class="normalMsg"><bean:write
		name="hemmsSparePartMgmtDeskFB" property="strNormalMsg" /></div>
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Centralized Spare Parts &gt;&gt; Add</td>
		</tr>
	</table>

	<%-- Spare Part Stock Details --%>
	<div class="line">
	<table class="TABLEWIDTH" id="sparePartStockDetailsId" align="center"
		border="0" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="4">Spare Parts Stock Details</td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">

		<%-- Spare-Part Name & Spare-Part Serial No --%>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Store
			Name</td>
			<td width="25%" class="CONTROL"><select name="strDeptCode"
				class='comboMax' onchange="">
				<bean:write name="hemmsSparePartMgmtDeskFB"
					property="strDeptNameCombo" filter="false" />
			</select></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Group
			Name</td>
			<td width="25%" class="CONTROL">
			<div id="GroupNameDiv"><select name="strGroupName"
				id="strGroupName" class='comboMax' onchange="getSubGroupName();">
				<bean:write name="hemmsSparePartMgmtDeskFB"
					property="strGroupNameCmb" filter="false" />
			</select></div>
			</td>

		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Sub-Group
			Name</td>
			<td width="25%" class="CONTROL">
			<div id="SubGroupNameDiv"><select name="strSubGroupName"
				id="strSubGroupName" class='comboMax'
				onchange="showOrHideSrlNo();getEquipName();">
				<bean:write name="hemmsSparePartMgmtDeskFB"
					property="strSubGroupNameCmb" filter="false" />
			</select></div>
			</td>
			<td width="25%" class="LABEL"><font color="red">*</font>Equipment
			Name</td>
			<td width="25%" class="CONTROL">
			<div id="EquipNameDivId"><select name="strEquipName"
				id="strEquipName" class='comboMax' onChange='getSparePartName();'>
				<bean:write name="hemmsSparePartMgmtDeskFB"
					property="strEquipNameCmb" filter="false" />
			</select></div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Spare-Part
			Name</td>
			<td width="25%" class="CONTROL">
			<div id="SparePartDivId"><select name="strSparePartName"
				id="strSparePartName" " class='comboMax'>
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td id="batchNoDivId" width="25%" class="LABEL"><font
				color="red">*</font>Serial No.</td>
			<td width="25%" class="CONTROL"><input id="strSparePartSerialNo"
				type="text" class="TEXT_FIELD_MAX" maxlength="20"
				name=strSparePartSerialNo onkeypress="return validateData(event,8);">
			<input style="display: none" id="strReceivedQty" type="text"
				class="TEXT_FIELD_MAX" maxlength="9" name=strReceivedQty
				onkeypress="return validateData(event,5);"> <img
				id="addSparePartsSrlNo" title="Hide" onclick="addSerialNo()"
				src="../../hisglobal/images//plus.gif" style="cursor: pointer;">

			</td>
		</tr>

		<%-- Manufacturer Name & Manufacturer Serial No --%>

		<tr>
			<td width="25%" class="LABEL">Manufacturer Name</td>
			<td width="25%" class="CONTROL">
			<div id="strManufacturerDiv"><select name="strManufacturerName"
				id="strManufacturerName" class='comboMax'>
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td width="25%" class="LABEL">Warranty From Date</td>
			<td width="25%" class="CONTROL"><dateTag:date
				name="strWarrantyFromDate"></dateTag:date></td>
			<td style="display: none;" width="25%" class="LABEL">Manufacturer
			Serial No</td>

			<td style="display: none;" width="25%" class="CONTROL"><input
				type="text" class="TEXT_FIELD_MAX" maxlength="20"
				name="strManufacturerSerialNo"
				onkeypress="return validateData(event,8);"></td>
		</tr>


		<%-- Warranty Upto &  Unit --%>

		<tr>
			<td width="25%" class="LABEL">Warranty Upto</td>

			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MAX" maxlength="3" name="strWarrantyUpto"
				onkeypress="return validateData(event,5);"></td>

			<td width="25%" class="LABEL">Unit</td>
			<td width="25%" class="CONTROL"><select
				name="strWarrantyUptoUnit" class='comboNormal'>
				<bean:write name="hemmsSparePartMgmtDeskFB"
					property="strUnitNameCombo" filter="false" />
			</select></td>
		</tr>

		<%-- Specification --%>

		<tr>
			<td width="25%" class="LABEL">Specification</td>
			<td width="25%" class="CONTROL"><html:textarea
				name="hemmsSparePartMgmtDeskFB" property="strSpecification"
				cols="25" rows="2" /></td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>

		<%-- Performed Date  --%>
		<tr style="display: none;">
			<td width="25%" class="LABEL"><font color="red">*</font>Performed
			Date</td>
			<td width="25%" class="CONTROL"><dateTag:date
				name="strPerformedDate" value=""></dateTag:date></td>

			<td width="25%" class="LABEL"></td>
			<td width="25%" class="LABEL"></td>
		</tr>

	</table>
	<div class="line">
	<table class="TABLEWIDTH">
		<tr>
			<td>Added Serial Numbers
			<div id="addedSrlNoDiv">

			<table id="sparePartSrlNoTable" class="TABLEWIDTH"></table>

			</div>
			</td>
		</tr>
	</table>
	</div>

	<div class="line">
	<table class="TABLEWIDTH">
		<tr>
			<td>Rate Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH">
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Purchase
			Rate/Unit</td>

			<td class="CONTROL" colspan="1" width="25%"><input type="text"
				name="strRate" maxlength="14" class="txtFldNormal"
				onkeypress="return validateData(event,7);" /></td>

			<td class="LABEL" width="25%"><font color="red">*</font>Unit</td>
			<td class="CONTROL" width="25%">
			<div id="UnitRateID"><select name="strUnitRateID"
				class='comboNormal'>
				<option value="390001">No.</option>
			</select></div>
			</td>

		</tr>
	</table>


	<div class="line">
	<table class="TABLEWIDTH">
		<tr>
			<td>P.O. Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH">
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Received
			Date</td>
			<td class="CONTROL" width="25%"><dateTag:date
				name="strReceivedDate" value=""></dateTag:date></td>
			<td class="LABEL" width="25%"><font color="red">*</font>Supplied
			By</td>
			<td class="CONTROL" width="25%">
			<div id="SupplierDiv"><select name="strSuppliedName"
				id="strSuppliedName" class="comboMax">
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">Batch No.</td>
			<td class="CONTROL" width="25%"><input type="text"
				name="strBatchNo" maxlength="20" class="txtFldMax"
				onkeypress="return validateData(event,21);" /></td>
			<td class="LABEL" width="25%">Stock Register Page No.</td>
			<td class="CONTROL" width="25%"><input type="text"
				name="strStockRegisterPageNo" maxlength="10" class="txtFldMax"
				onkeypress="return validateData(event,5);" /></td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">BID No.</td>
			<td class="CONTROL" width="25%"><input type="text"
				name="strTenderNo" maxlength="30" class="txtFldMax"
				onkeypress="return validateData(event,21);" /></td>

			<td class="LABEL" width="25%">BID Date</td>
			<td class="CONTROL" width="25%"><dateTag:date
				name="strTenderDate" value=""></dateTag:date></td>
		</tr>
		<tr>
			<td class="LABEL" width="25%">P.O. No.</td>
			<td class="CONTROL" width="25%"><input type="text"
				name="strPoNo" maxlength="30" class="txtFldMax"
				onkeypress="return validateData(event,21);" /></td>
			<td class="LABEL" width="25%">P.O. Date</td>
			<td class="CONTROL" width="25%"><dateTag:date name="strPoDate"
				value=""></dateTag:date></td>

		</tr>
		<tr>
			<td class="LABEL" width="25%">Bill No.</td>
			<td class="CONTROL" width="25%"><input type="text"
				name="strBillNo" maxlength="35" class="txtFldMax"
				onkeypress="return validateData(event,21);" /></td>
			<td class="LABEL" width="25%">Bill Date</td>
			<td class="CONTROL" width="25%"><dateTag:date name="strBillDate"
				value=""></dateTag:date></td>
		</tr>
	</table>




	<table class="TABLEWIDTH">

		<tr class="FOOTER">
			<td></td>
		</tr>
	</table>
	
<!--<div>
	<div class="legends"><font size="2" color="red">*</font>
	Mandatory Fields</div>
	<div class="control_button">
	<table class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<div><a href="#" class="button" onClick="return validate1();"><span
				class="save">Save</span></a> <a href="#" class="button"
				onclick="clearPage();"><span class="clear">Clear</span></a> <a
				href="#" class="button" onClick="cancelPage();"><span
				class="back">Back</span></a></div>
			</td>
		</tr>
	</table>
	</div>
	</div>  -->

	<table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="return validate1();" /><img
				src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onclick="clearPage();"/>  <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();" /></td>
		</tr>
	</table>

	<input type="hidden" name="strSparePartSrlNoVal" value="" />

	<input type="hidden" name="hmode" />
	<input type="hidden" name="strPath"
		value="${hemmsSparePartMgmtDeskFB.strPath}">
	<input type="hidden" name="strChk"
		value="${hemmsSparePartMgmtDeskFB.strChk}">
	<input type="hidden" name="strRetValue"
		value="${hemmsSparePartMgmtDeskFB.strRetValue}" />
	<input type="hidden" name="strMsgString"
		value="${hemmsSparePartMgmtDeskFB.strMsgString}" />

</html:form>
</body>
</html>