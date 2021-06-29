<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Centralized Equipment Inventory</title>

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

<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<his:javascript	src="/bmed/transactions/js/item_inventory_trans.js" />
<his:javascript	src="/bmed/transactions/js/item_OtherDtls_util.js"/>
<his:javascript	src="/bmed/transactions/js/itemparameterdetails_util.js"/>






<script type="text/javascript">

</script>

</head>

<body onload="chkRecordSaved()" class="background">
<tag:autoIndex>
	<html:form name="itemInventoryTransBean" styleClass="formbg"
		action="/transactions/ItemInventoryTransCNT"
		type="bmed.transactions.controller.fb.ItemInventoryTransFB"
		enctype="multipart/form-data">


		<div class="errMsg" id="errMsg"><bean:write
			name="itemInventoryTransBean" property="strErr" /></div>
		<div class="warningMsg" id="warningMsg"><bean:write
			name="itemInventoryTransBean" property="strWarning" /></div>
		<div style="display: none;" class="normalMsg" id="normalMsg"><bean:write
			name="itemInventoryTransBean" property="strMsg" filter="false" /></div>

		<table id="itemTable" class="TABLEWIDTH" align="center" border="0"
			cellpadding="1px" cellspacing="1px">
			<tr class="HEADER">
				<td colspan="4">Item Inventory &gt;&gt; Add</td>
			</tr>
			<tr>
				<td width="25%" class="LABEL"><font color="red">*</font>Programme
				Name</td>
				<td width="25%" class="CONTROL"><select name="strProgramId"
					class="comboNormal">
					<bean:write name="itemInventoryTransBean"
						property="strProgramNameCombo" filter="false" />
				</select></td>
				<td class="LABEL" width="25%"></td>
				<td class="CONTROL" width="25%"></td>


			</tr>

			<tr>
				<td width="25%" class="LABEL">Hospital Name</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strDepartmentName"
					filter="false" /></td>
				<td width="25%" class="LABEL">Lab Name</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strStoreName"
					filter="false" /></td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">Category</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strItemCategoryName"
					filter="false" /></td>
				<td width="25%" class="LABEL">Group Name</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strGroupName"
					filter="false" /></td>
			</tr>


			<tr>
				<td width="25%" class="LABEL"><font color="red">*</font>Sub
				Group Name</td>
				<td width="25%" class="CONTROL"><select name="strSubGroupId"
					class="comboNormal"
					onChange="ajaxItemName('ITEMNAME');showOrHideDetails();resetSerialNumberDiv();">
					<bean:write name="itemInventoryTransBean"
						property="strSubGroupCombo" filter="false" />
				</select></td>
				<td class="LABEL" width="25%"><font color="red">*</font>Equipment
				Name</td>
				<td class="CONTROL" width="25%">
				<div id="ItemNameId"><select name="strItemId"
					class='comboNormal' onChange="ajaxItemBrandName('ITEMBRANDNAME');">
					<bean:write name="itemInventoryTransBean"
						property="strItemNameCombo" filter="false" />
				</select></div>
				</td>


			</tr>


			<tr>

				<td class="LABEL" width="25%"><font color="red">*</font>Equipment
				Model</td>
				<td class="CONTROL" width="25%">
				<div id="ItemBrandId"><select name="strItemBrandId"
					class='comboNormal'>
					<%-- onchange="ajaxManufectureName('MANUFECTURENAME');">--%>
					<option value="0">Select Value</option>
				</select></div>
				</td>

				<td class="LABEL" width="25%">
				<div id="manfNotMandatoryDivId" style="display: none;">Manufacture</div>
				<div id="manfMandatoryDivId" style="display: block;"><font
					color="red">*</font>Manufacture</div>
				</td>
				<td class="CONTROL" width="25%">
				<div id="manufDivId"><select class="comboMax"
					name="strManufactureId">
					<option value='0'>Select Value</option>
				</select></div>
				</td>

			</tr>


			<tr>

				<td class="LABEL">
				<div id="batchNoDivId"><font color="red">*</font>Serial No.</div>
				<input type="hidden" name="strAddedSlNumbers" value="0"> <input
					type="hidden" name="isBatchReq" value="0"></td>
				<td width="25%" class="CONTROL">
				<div id="batchNoTextDivId"><input type="text" id="strBatchNo"
					name="strBatchNo" maxlength="20" class="txtFldMax"
					onkeypress="return validateData(event,17);" /> <img
					id="imgStockDetails" title="Add" onclick="ajaxChkDuplicate()"
					src="../../hisglobal/images/plus.gif" style="cursor: pointer;">

				</div>
				</td>



				<td class="LABEL">
				<div id="batchNoDivId"><font color="red">*</font>Equipment
				Status</div>
				</td>

				<td class="CONTROL" width="25%">
				<div id="equipDivId"><select class="comboMax"
					name="strEquipStatusId">
					<bean:write name="itemInventoryTransBean"
						property="strEquipStatusValues" filter="false" />
				</select></div>
				</td>

			</tr>

			<tr id="inHandQtyRow" style="display: none;">

				<td class="LABEL" width="25%"><font color="red">*</font>InHand
				Qty.</td>

				<td class="CONTROL" width="25%"><input type="text"
					name="strInHandQuantity" maxlength="8" class="txtFldMin" value="1"
					onkeyup="return validateQty('strInHandQuantity','strInHandQuantityUnitID');"
					onkeypress="return validateData(event,7);" /></td>

				<td class="LABEL" width="25%"></td>
				<td class="CONTROL" width="25%"></td>
			</tr>
			<tr style="display: none;">
				<td class="LABEL" width="25%"><font color="red">*</font>InHand
				Qty.</td>

				<td class="CONTROL" width="25%"><input type="text"
					name="strInHandQuantityOld" maxlength="8" class="txtFldMin"
					readonly="readonly" value="1"
					onkeyup="return validateQty('strInHandQuantity','strInHandQuantityUnitID');"
					onkeypress="return validateData(event,7);" /></td>

				<td class="LABEL" width="25%"><font color="red">*</font>InHand
				Qty. Unit</td>
				<td class="CONTROL" width="25%">
				<div id="freeItemUnit"><select name="strInHandQuantityUnitID"
					onchange="return validateQty('strInHandQuantity','strInHandQuantityUnitID');"
					class='comboNormal'>
					<!--<option value="0">Select Value</option>-->
				</select></div>
				</td>
			</tr>

			<tr style="display: none;">
				<td class="LABEL" width="25%"><font color="red">*</font>Stock
				Status</td>

				<td class="CONTROL" width="25%"><select name="strStockStatus"
					class="comboNormal">

					<bean:write name="itemInventoryTransBean"
						property="strStockStatusValues" filter="false" />

				</select></td>

				<td class="LABEL" width="25%"></td>
				<td class="CONTROL" width="25%"></td>
			</tr>
			<!--  
		<tr>
			<td class="LABEL" width="25%">Free Item Qty.</td>

			<td class="CONTROL" width="25%"><input type="text"
				name="strFreeItemQuantity" maxlength="8" class="txtFldMin"
				onkeypress="return validateData(event,5);" /></td>

			<td class="LABEL" width="25%"><font color="red">*</font>Free Item Qty. Unit</td>
			<td class="CONTROL" width="25%">
			<div id="freeItemUnitValue"><select name="strFreeItemQuantityUnitID"
				class='comboNormal'>
				<option value="0">Select Value</option>
			</select></div>
			</td>
		</tr>
		-->


			<tr>
				<td colspan="1" class="LABEL">
				<div id="specNotMandatoryDivId" style="display: none;">Specification</div>
				<div id="specMandatoryDivId" style="display: block;"><font
					color="red">*</font>Specification</div>

				</td>
				<td colspan="1" class="CONTROL"><textarea rows="2" cols="40"
					name="strItemSpecification"></textarea></td>
				<td class="LABEL" width="25%">Manufacture Date</td>
				<td class="CONTROL" width="25%"><dateTag:date
					name="strManufactureDate"
					value="${itemInventoryTransBean.strCtDate}"></dateTag:date></td>
			</tr>
			<tr>
				<td colspan="4">
				<div class="line">
				<table id="multiSerialNoTable" class="TABLEWIDTH" align="center"
					border="0" cellpadding="1px" cellspacing="1px">
					<tr id="srlNo">
						<td>Serial No(s).&nbsp;&nbsp;<span id="addedSlNoCount" style="font-style: italic;font-size: medium;"></span></td>
					</tr>
					<tr id="srlNoTR0">
						<td></td>
					</tr>
				</table>

				</div>
				<div id="multiSerialNoDiv" style="float: left; margin-left: 4%;"></div>
				</td>
			</tr>

			<tr>
				<td colspan="4">
				<div class="line">
				<table class="TABLEWIDTH" align="center" border="0"
					cellpadding="1px" cellspacing="1px">
					<tr>
						<td>Rate Details</td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
			<tr style="display: none;">
				<td class="LABEL" width="25%">Currency Code</td>
				<td class="CONTROL" width="25%"><select name="strCurrencyCode"
					class="comboNormal" onchange="isCurrencyMandatory(this);">
					<bean:write name="itemInventoryTransBean"
						property="strCurrencyCodeValues" filter="false" />
				</select></td>

				<td class="LABEL" width="25%">
				<div id="currencyDivId">Currency Value</div>
				<input type="hidden" name="isCurrencyReq" value="0"></td>

				<td class="CONTROL" colspan="1" width="25%"><input type="text"
					name="strCurrencyValue" readonly="readonly" maxlength="8"
					class="txtFldNormal" onkeypress="return validateData(event,7);"
					value="1" disabled="disabled" /></td>

			</tr>
			<tr>
				<td class="LABEL" width="25%"><font color="red">*</font>Purchase
				Rate/Unit</td>

				<td class="CONTROL" colspan="1" width="25%"><input type="text"
					name="strRate" maxlength="14" class="txtFldNormal"
					onkeypress="return validateData(event,7);" />&nbsp;&nbsp;(in
				Rupees)</td>

				<td class="LABEL" width="25%"><font color="red">*</font>Unit
				Name</td>
				<td class="CONTROL" width="25%">
				<div id="UnitRateID"><select name="strUnitRateID"
					class='comboNormal'>
					<option value="0">Select Value</option>
				</select></div>
				</td>

			</tr>
		</table>
		<div id="issueRate" style="display: none">
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td class="LABEL" width="25%"><font color="red">*</font>Issue
				Rate</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strSalePrice" maxlength="14" class="txtFldNormal"
					onkeypress="return validateData(event,7);" /></td>

				<td class="LABEL" width="25%"><font color="red">*</font>Unit
				Name</td>
				<td class="CONTROL" width="25%">

				<div id="UnitRateID1"><select name="strUnitSaleID"
					class='comboNormal'>
					<option value="0">Select Value</option>
				</select></div>

				</td>
			</tr>
		</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<tr>
				<td colspan="4">
				<div class="line">
				<table class="TABLEWIDTH" align="center" border="0"
					cellpadding="1px" cellspacing="1px">
					<tr>
						<td>P.O. Details</td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
			<tr>
				<td class="LABEL" width="25%">Stock Register Page No.</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strStockRegisterPageNo" maxlength="10" class="txtFldMax"
					onkeypress="return validateData(event,7);" /></td>

				<td class="LABEL" width="25%"></td>
				<td class="CONTROL" width="25%"></td>
			</tr>
			<tr>

				<td class="LABEL" width="25%">BID No.</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strTenderNo" maxlength="35" class="txtFldMax" onkeypress="" /></td>

				<td class="LABEL" width="25%">BID Date</td>
				<td class="CONTROL" width="25%"><dateTag:date
					name="strTenderDate" value=""></dateTag:date></td>

			</tr>

			<tr>

				<td class="LABEL" width="25%">P.O. No.</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strPoNo" maxlength="35" class="txtFldMax" onkeypress="" /></td>

				<td class="LABEL" width="25%">P.O. Date</td>
				<td class="CONTROL" width="25%"><dateTag:date name="strPoDate"
					value=""></dateTag:date></td>

			</tr>

			<tr>

				<td class="LABEL" width="25%">Bill No.</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strBillNo" maxlength="35" class="txtFldMax" onkeypress="" /></td>

				<td class="LABEL" width="25%">Bill Date</td>
				<td class="CONTROL" width="25%"><dateTag:date
					name="strBillDate" value=""></dateTag:date></td>

			</tr>


			<tr>

				<td class="LABEL" width="25%"><font color="red">*</font>Received
				Date</td>
				<td class="CONTROL" width="25%"><dateTag:date
					name="strReceivedDate" value="${itemInventoryTransBean.strCtDate}"></dateTag:date></td>

				<td class="LABEL" width="25%"><font color="red">*</font>Supplied
				By</td>
				<td class="CONTROL" width="25%"><select name="strSuppliedBy"
					class="comboMax">
					<bean:write name="itemInventoryTransBean"
						property="strSuppliedByValues" filter="false" />
				</select></td>

			</tr>

		</table>



		<logic:equal name="itemInventoryTransBean" property="strWarrantyFlag"
			value="1">

			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px" style="display: none;">

				<tr>
					<td colspan="4" class="TITLE" width="25%"><html:checkbox
						property="strIsWarrantyDetails" name="itemInventoryTransBean"
						value="1"
						onclick="showOrHideDetails(this,'warrantyItemDtlsDivId');">Whether Guaranty Details Required</html:checkbox>
					</td>
				</tr>
			</table>



			<div id="warrantyItemDtlsDivId" style="display: none">

			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Guaranty
					Date</td>
					<td class="CONTROL" width="25%"><dateTag:date
						name="strWarrantyDate"></dateTag:date></td>
					<td class="LABEL" width="25%"><font color="red">*</font>Manufacture
					</td>
					<td class="CONTROL" width="25%">
					<div id="warrantyManufDivId"><select
						name="strWarantyManufacturer" class="comboMax">
						<option value='0'>Select Value
					</select></div>
					</td>
				</tr>

				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Guaranty
					Upto</td>
					<td class="CONTROL" width="25%"><input type="text"
						name="strWarrantyUpTo" class="txtFldMin" maxlength="3"
						onkeypress="return validateData(event, 5);"></td>
					<td class="LABEL" width="25%"><font color="red">*</font>Unit</td>
					<td class="CONTROL" width="25%"><select
						name="strWarrantyUpToUnit" class="comboMin">
						<option value="0">Select Value</option>
						<option value="1">Day(s)</option>
						<option value="2">Month(s)</option>
						<option value="3">Year(s)</option>
					</select></td>
				</tr>
				<tr>
					<td class="LABEL" colspan="2">Remarks</td>
					<td class="CONTROL" colspan="2"><textarea rows="2" cols="25"
						name="strWarrantyRemarks"></textarea></td>
				</tr>

			</table>
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">

				<tr>
					<td colspan="4" class="TITLE" width="25%"></td>
				</tr>
			</table>

			</div>
		</logic:equal>

		<logic:equal name="itemInventoryTransBean" property="strInstallFlag"
			value="1">

			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">

				<tr>
					<td colspan="4">
					<div class="line">
					<table class="TABLEWIDTH" align="center" border="0"
						cellpadding="1px" cellspacing="1px">

						<tr>
							<td colspan="4"><input type="checkbox"
								name="strIsInstallDetails" value="1" checked="checked"
								onchange="showOrHideDetails(this,'installItemDtlsDivId');"
								onclick="" />Whether Installation Details Required</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>



			<div id="installItemDtlsDivId" style="display: block">

			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Installation
					Start Date</td>
					<td class="CONTROL" width="25%"><dateTag:date
						name="strInstallStartDate"></dateTag:date></td>
					<td class="LABEL" width="25%"><font color="red">*</font>Installation
					End Date</td>
					<td class="CONTROL" width="25%"><dateTag:date
						name="strInstallEndDate"></dateTag:date></td>
				</tr>

				<tr style="display: none;">
					<td class="LABEL" width="25%"><font color="red">*</font>Installation
					Status</td>
					<td class="CONTROL" width="25%"><select
						name="strInstallStatus" class="comboNormal">
						<option value='0'>Select Value</option>
						<option value='1'>Success</option>
						<option value='2'>Failure</option>
					</select></td>
					<td class="LABEL" width="25%"><font color="red">*</font>Installed
					By</td>
					<td class="CONTROL" width="25%"><input type="text"
						class="txtFldMax" name="strInstallBy"
						onkeypress="return validateData(event, 4);" maxlength="100">
					</td>
				</tr>

				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Company
					Contact Detail</td>
					<td class="CONTROL" width="25%"><input type="text"
						name="strInstallerContactNo"
						onkeypress="return validateData(event, 2);" class="txtFldMax"
						maxlength="10"></td>
					<td class="LABEL" width="25%"></td>
					<td class="CONTROL" width="25%"></td>
					<td style="display: none;" class="LABEL" width="25%">Remarks</td>
					<td style="display: none;" class="CONTROL" width="25%"><textarea
						rows="2" cols="25" name="strInstallRemarks"></textarea></td>
				</tr>

			</table>



			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">

				<tr>
					<td colspan="4">
					<div class="line">
					<table class="TABLEWIDTH" align="center" border="0"
						cellpadding="1px" cellspacing="1px">
						<tr>
							<td>Document Details</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>

			</div>



		</logic:equal>



		<%--
	Free Item will not be treated in this way, any more.
	Suggested by: Amit Kumar
	Changed by: Aritra 
	 --%>
		<%-- 
<logic:equal name="itemInventoryTransBean" property="strFreeItemFlag"	value="1">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">

		<tr>
			<td colspan="4" class="TITLE" width="25%">
			<div id="freeItemDtlsDivIdPlusId" align="left"
				style="display: block;"><img
				src="../../hisglobal/images/plus.gif"
				onClick="showView('freeItemDtlsDivId');"
				style="cursor: pointer; " /> Free Items</div>
			<div id="freeItemDtlsDivIdMinusId" style="display: none;"
				align="left"><img src="../../hisglobal/images/minus.gif"
				onClick="hideView('freeItemDtlsDivId');"
				style="cursor: pointer; " /> Free Items</div>
			</td>
		</tr>
	</table>



	<div id="freeItemDtlsDivId" style="display: none">

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td class="multiLabel" width="20%">Item Name</td>
			<td class="multiLabel" width="20%">Batch No.</td>
			<td class="multiLabel" width="20%">Expiry Date</td>
			<td class="multiLabel" width="20%">Qty.</td>
			<td class="multiLabel" width="5%"></td>
		</tr>
	</table>
	<div id="id1"></div>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">

		<tr>
			<td colspan="4" class="TITLE" width="25%"></td>
		</tr>

		<tr>

			<td align="center"><img style="cursor: pointer; "
				src="../../hisglobal/images/btn-add.png" onClick="addFreeItems();" />
			</td>
		</tr>

	</table>

	</div>
	</logic:equal>
	--%>

		<%--
	Component Item will not be treated in this way, any more.
	Suggested by: Amit Kumar
	Changed by: Aritra 
	--%>

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px" style="display: none;">

			<tr>
				<td colspan="4" class="TITLE" width="25%">
				<div id="partItemDtlsDivIdPlusId" align="left"
					style="display: block;"><img
					src="../../hisglobal/images/plus.gif"
					onClick="showView('partItemDtlsDivId');" style="cursor: pointer;" />
				Component Items</div>
				<div id="partItemDtlsDivIdMinusId" style="display: none;"
					align="left"><img src="../../hisglobal/images/minus.gif"
					onClick="hideView('partItemDtlsDivId');" style="cursor: pointer;" />
				Component Items</div>
				</td>
			</tr>
		</table>



		<div id="partItemDtlsDivId" style="display: none">

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>

				<td class="multiLabel" width="15%">Component Type</td>
				<td class="multiLabel" width="15%">Item Name</td>
				<td class="multiLabel" width="15%">Serial No.</td>
				<td class="multiLabel" width="15%">Manufacturer</td>
				<td class="multiLabel" width="15%">Expiry Date</td>
				<td class="multiLabel" width="20%">Qty.</td>
				<td class="multiLabel" width="5%">&nbsp;&nbsp;</td>
			</tr>
		</table>
		<div id="id2"></div>

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<tr>
				<td colspan="4" class="TITLE" width="25%"></td>
			</tr>

			<tr>

				<td align="center"><img style="cursor: pointer;"
					src="../../hisglobal/images/btn-add.png" onClick="addPartItems();" />
				</td>
			</tr>

		</table>

		</div>


		<%--
	Item Parameter will not be treated in this way, any more.
	Suggested by: Amit Kumar
	Changed by: Aritra 
	--%>



		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px" style="display: none;">

			<tr>
				<td colspan="4" class="TITLE" width="25%">
				<div id="paramItemDtlsDivIdPlusId" align="left"
					style="display: block;"><img
					src="../../hisglobal/images/plus.gif"
					onclick="showView('paramItemDtlsDivId');" style="cursor: pointer;">
				Parameter Details</div>
				<div id="paramItemDtlsDivIdMinusId" style="display: none;"
					align="left"><img src="../../hisglobal/images/minus.gif"
					onclick="hideView('paramItemDtlsDivId');" style="cursor: pointer;">
				Parameter Details</div>
				</td>
			</tr>
		</table>

		<div id="paramItemDtlsDivId" style="display: none">

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<tr>
				<td colspan="4" class="TITLE" width="25%"></td>
			</tr>

			<tr>

				<td align="center"><img
					src="../../hisglobal/images/add_parameter.GIF"
					onclick="checkForPopup(this,document.forms[0].strItemCategoryNo.value , '1')"
					style="cursor: pointer;"></td>
			</tr>

		</table>

		</div>

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td class="LABEL" width="25%"><!--  <font color="red">*</font>    -->
				Ref. No./Ref Date</td>
				<td class="CONTROL" width="25%"><input type="text"
					class="txtFldMax" name="strDocRefNo"
					onkeypress="return validateDataWithSpecialChars(event,9 ,'\')"
					maxlength="30">/<dateTag:date name="strDocRefDate" value="${itemInventoryTransBean.strDocRefDate}"></dateTag:date></td>

			</tr>

			<tr>
				<td class="LABEL" width="25%">Installation Report/P.O.Copy</td>
				<td class="CONTROL" width="25%"><html:file
					property="strLocation" /></td>

			</tr>


		</table>

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<!--	<table cellspacing="1px" class="TABLEWIDTH" align="center">-->
			<tr class="FOOTER">
				<td></td>
			</tr>
		</table>
		<!-- <div>
		
		<div class="legends"><font size="2" color="red">*</font>
		Mandatory Fields</div>
		<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div><a href="#" class="button" onClick="validate1();"><span
					class="save">Save</span></a> <a href="#" class="button"
					onclick="document.forms[0].reset();"><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancelPage('LIST');"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div>
		</div>   -->
		
		
		  <table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="validate1();" />  <img
				src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer; cursor: hand;" title="Clear Process"
				onclick="document.forms[0].reset();" />  <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				 onClick="cancelPage('LIST');" /></td>
		</tr>
	</table>
		
		
		
		<input type="hidden" name="strDepartmentId"
			value="${itemInventoryTransBean.combo[0]}" />
		<input type="hidden" name="strDepartmentName"
			value="${itemInventoryTransBean.strDepartmentName}" />
		<input type="hidden" name="strStoreId"
			value="${itemInventoryTransBean.combo[1]}" />
		<input type="hidden" name="strStoreName"
			value="${itemInventoryTransBean.strStoreName}" />
		<input type="hidden" name="strGroupId"
			value="${itemInventoryTransBean.combo[2]}" />
		<input type="hidden" name="strGroupName"
			value="${itemInventoryTransBean.strGroupName}" />

		<input type="hidden" name="strItemCategoryNo"
			value="${itemInventoryTransBean.strItemCategoryNo}" />
		<input type="hidden" name="strItemCategoryName"
			value="${itemInventoryTransBean.strItemCategoryName}" />

		<input type="hidden" name="strCtDate"
			value="${itemInventoryTransBean.strCtDate}">

		<input type="hidden" name="strDefaultCurrencyCode"
			value="${itemInventoryTransBean.strDefaultCurrencyCode}">

		<input type="hidden" name="strConfigIssueRate"
			value="${itemInventoryTransBean.strConfigIssueRate}">

		<input type="hidden" name="strIssueRateConfigFlg"
			value="${itemInventoryTransBean.strIssueRateConfigFlg}">
		<input type="hidden" name="strRetValue"
			value="${itemInventoryTransBean.strRetValue}" />
		<input type="hidden" name="strMsg"
			value="${itemInventoryTransBean.strMsg}" />


		<input type="hidden" name="strRegFlag" value="" />

		<input type="hidden" name="hmode" />
		<input type="hidden" name="isConsumable" />
		<input type="hidden" name="subGroupId" />

		<div id="blanket" style="display: none;"></div>
		<div class="popUpDiv" id="otherDtlspopUpDiv" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>
				<div id="itemsOtherDtlsDivId" style="display: block;"></div>
				</td>
			</tr>
		</table>
		</div>

		<div class="popUpDiv" id="popUpDivId" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>
				<div id="itemParameterDtlDivId" style="display: block;"></div>
				</td>
			</tr>
		</table>
		</div>
		<cmbPers:cmbPers />
	</html:form>
	<%-- <jsp:include page="itemInventory_multirow_mmstrans.jsp"></jsp:include>--%>
</tag:autoIndex>
</body>
</html>