<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Centralized Equipment Inventory</title>

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">


<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">


<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<script language="JavaScript" src="../../bmed/transactions/js/hemms_pmo_inventory_desk.js"></script>

<script language="JavaScript" src="../js/item_OtherDtls_util.js"></script>

<script language="Javascript" src="../js/itemparameterdetails_util.js"></script>

</head>

<body onload="initializeViewPage();">
<html:form name="ItemInventoryPMODeskFB"
	action="/transactions/ItemInventoryPMODeskCNT"
	type="bmed.transactions.controller.fb.ItemInventoryPMODeskFB" enctype="multipart/form-data">


	<div class="errMsg"><bean:write name="ItemInventoryPMODeskFB"
		property="strErr" /></div>
	<div class="warningMsg"><bean:write name="ItemInventoryPMODeskFB"
		property="strWarning" /></div>
	<div style="display:none;" class="normalMsg" id="normalMsg"><bean:write
		name="ItemInventoryPMODeskFB" property="strMsg" /></div>

	<center><tag:tab tabLabel="Centralized Equipment Inventory "
		selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab></center>


	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">

		<tr class="HEADER">
			<td colspan="4"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Hospital Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strDepartmentName" filter="false" />
			</td>
			<td width="25%" class="LABEL">Lab Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strStoreName"
				filter="false" /></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Category</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strItemCategoryName" filter="false" />
			</td>
			<td width="25%" class="LABEL">Group Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strGroupName"
				filter="false" /></td>
		</tr>
		<tr>
			<td width="25%"   class="LABEL">Sub Group Name</td>
			<td width="25%"   class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strSubGroupName" filter="false" />
			</td>
		
			<td width="25%" class="LABEL">Equipment Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strItemName"
				filter="false" /></td>
</tr>
		<tr>

			<td width="25%" class="LABEL">Equipment Model</td>
			<td width="25%" class="CONTROL">
			<!--<bean:write	name="ItemInventoryPMODeskFB" property="strItemBrandName" filter="false" />-->
			<select
				name="strItemBrandId" class='comboMax' disabled="disabled" onchange="return validateQty('strInHandQuantity','strInHandQuantityUnitID');" >
				<bean:write name="ItemInventoryPMODeskFB"	property="strEquipModelValues" filter="false" />
			</select>
			</td>
		
			<td width="25%" class="LABEL">Manufacturer Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strManufactureName"
				filter="false" />
			</td>

		</tr>
		<tr>
		<td width="25%" class="LABEL">
				<div id="batchNoDivId" ><font color="red">*</font>Serial No.</div>
						<input type="hidden" name="isBatchReq" value="0">
			<!--  
		<logic:empty name="ItemInventoryPMODeskFB" property="strBatchNo">Serial No.
					<input type="hidden" name="isBatchReq" value="0">
				</logic:empty> 
				
				<logic:notEmpty name="ItemInventoryPMODeskFB" property="strBatchNo">Serial No.
					<input type="hidden" name="isBatchReq" value="1">
				</logic:notEmpty> -->
		</td>
			<td width="25%" class="CONTROL"><input type="text"
				name="strBatchNo" maxlength="20" class="txtFldNormal" readonly="readonly" 
				value="${ItemInventoryPMODeskFB.strBatchNo}" onkeypress="return validateData(event,17);" />
			 	<!--<bean:write name="ItemInventoryPMODeskFB" property="strBatchNo" filter="false" />-->
			 <input type="hidden" name="strOldBatchNo" value="${ItemInventoryPMODeskFB.strBatchNo}">
		</td>
		<td width="25%" class="LABEL" >Specification
			</td>
			
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strItemSpecification"
				filter="false" />
			</td>		
		</tr>
		<tr style="display: none;">
		<td width="25%" class="LABEL">Specification</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryPMODeskFB" property="strItemSpecification"
				filter="false" /></td>
		<td style="display: none;" class="LABEL" width="25%">Manufacture Date</td>
			<td style="display: none;" class="CONTROL" width="25%"><dateTag:date
				name="strManufactureDate"
				value="${ItemInventoryPMODeskFB.strManufactureDate}"></dateTag:date></td>
		<td class="LABEL" width="25%">Manufacture Date</td>
			<td class="CONTROL" width="25%"></td>		
		</tr>

 		
		<tr>
			<td class="LABEL">
			<div id="equipMandatoryDivId"">Equipment Status</div>				
			
			</td>
			<td width="25%" class="CONTROL">
			<div id="equipDivId"><select class="comboNormal" disabled="disabled"
				name="strEquipStatusId">
				<bean:write name="ItemInventoryPMODeskFB" property="strEquipStatusValues" filter="false" />
			</select></div>
			  </td>

			<td class="LABEL" >
		
		<div style="display: none;">
			<logic:empty name="ItemInventoryPMODeskFB" property="strExpiryDate">
				<input type="hidden" name="isExpirtReq" value="0">Expiry Date
			</logic:empty> <logic:notEmpty name="ItemInventoryPMODeskFB"
				property="strExpiryDate">
				<input type="hidden" name="isExpirtReq" value="1">Expiry Date
			</logic:notEmpty>
			</div>
			
			</td>

			<td class="CONTROL" width="25%">
				<div style="display: none;">				
					<dateTag:date name="strExpiryDate" value="${ItemInventoryPMODeskFB.strExpiryDate}"></dateTag:date>
				</div>	
			</td>
		</tr>

 		
		<tr style="display: none;">
			<td class="LABEL" width="25%"></td>
			<td class="CONTROL" width="25%"></td>
			
			<td class="LABEL" width="25%">Stock
			Status</td>
			<td class="CONTROL" width="25%"><select name="strStockStatus"
				class="comboNormal">

				<bean:write name="ItemInventoryPMODeskFB"
					property="strStockStatusValues" filter="false" />

			</select></td>
		</tr>

		<tr id="inHandRow">
			<td class="LABEL" width="25%">InHand
			Quantity</td>

			<td class="CONTROL" width="25%"><input type="text"
				name="strInHandQuantity" maxlength="8" class="txtFldNormal" readonly="readonly" onkeyup="return validateQty('strInHandQuantity','strInHandQuantityUnitID');"
				value="${ItemInventoryPMODeskFB.strInHandQuantity}"
				onkeypress="return validateData(event,7);" /></td>

			<td  class="LABEL" width="25%">InHand
			Quantity Unit</td>
			<td class="CONTROL" width="25%">
			<bean:write	name="ItemInventoryPMODeskFB" property="strInHandQuantityUnitValues" filter="false" />
			</td>
<!--  		<td class="CONTROL" width="25%"><select
				name="strInHandQuantityUnitID" class='comboNormal' onchange="return validateQty('strInHandQuantity','strInHandQuantityUnitID');" >
				<bean:write name="ItemInventoryPMODeskFB"
					property="strInHandQuantityUnitValues" filter="false" />
			</select></td> -->	
		</tr>
		</table>
		<!--  
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Free Item Qty.</td>

			<td class="CONTROL" width="25%"><input type="text"
				name="strFreeItemQuantity" maxlength="8" class="txtFldMin"
				value="${ItemInventoryPMODeskFB.strFreeItemQuantity}" onkeypress="return validateData(event,5);" /></td>

			<td class="LABEL" width="25%">Free Item Qty. Unit</td>
			<td class="CONTROL" width="25%">
			<select name="strFreeItemQuantityUnitID"
				class='comboNormal'>
				<bean:write name="ItemInventoryPMODeskFB"
					property="strInHandQuantityUnitValues" filter="false" />
			</select>
			</td>
		</tr>
		</table>
		-->
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"		cellspacing="1px">
		
		<tr>
			<td colspan="4" class="TITLE" width="25%">Rate Details</td>
		</tr>
		<tr style="display: none;">
			<td class="LABEL" width="25%">Currency Code</td>
			<td class="CONTROL" width="25%"><select name="strCurrencyCode" 
				class="comboNormal" onchange="isCurrencyMandatory(this);">
				<bean:write name="ItemInventoryPMODeskFB"
					property="strCurrencyCodeValues" filter="false" />
			</select></td>

			<td class="LABEL" width="25%"><logic:equal
				name="ItemInventoryPMODeskFB" property="strCurrencyCode"
				value="${ItemInventoryPMODeskFB.strDefaultCurrencyCode}">
				<div id="currencyDivId">Currency Value</div>
				<input type="hidden" name="isCurrencyReq" value="0">
			</logic:equal> <logic:notEqual name="ItemInventoryPMODeskFB"
				property="strCurrencyCode"
				value="${ItemInventoryPMODeskFB.strDefaultCurrencyCode}">
				<div id="currencyDivId">Currency
				Value</div>
				<input type="hidden" name="isCurrencyReq" value="1">
			</logic:notEqual></td>

			<td class="CONTROL" width="25%"><logic:equal
				name="ItemInventoryPMODeskFB" property="strCurrencyCode"
				value="${ItemInventoryPMODeskFB.strDefaultCurrencyCode}">
				<input type="text" name="strCurrencyValue" maxlength="8"
					class="txtFldNormal" onkeypress="return validateData(event,7);"
					value="${ItemInventoryPMODeskFB.strCurrencyValue}" />
			</logic:equal> <logic:notEqual name="ItemInventoryPMODeskFB"
				property="strCurrencyCode"
				value="${ItemInventoryPMODeskFB.strDefaultCurrencyCode}">
				<input type="text" name="strCurrencyValue" maxlength="8"
					class="txtFldNormal" onkeypress="return validateData(event,7);"
					value="${ItemInventoryPMODeskFB.strCurrencyValue}" />
			</logic:notEqual></td>

		</tr>

		<tr>
			<td class="LABEL" width="25%">Rate/Unit
			</td>

			<td class="CONTROL" colspan="1" width="25%"><input type="text"
				name="strRate" maxlength="14" class="txtFldNormal" readonly="readonly"
				value="${ItemInventoryPMODeskFB.strRate}"
				onkeypress="return validateData(event,7);" /></td>

			<td class="LABEL" width="25%">Unit
			Name</td>
			<td class="CONTROL" width="25%"><select name="strUnitRateID" disabled="disabled"
				class='comboNormal'>
				<bean:write name="ItemInventoryPMODeskFB"
					property="strRateUnitValues" filter="false" />
			</select></td>

		</tr>
		</table>
		<div id="issueRate" style="display:none">
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"		cellspacing="1px">
		<tr>
			<td class="LABEL" width="25%">Issue Rate/Unit</td>

			<td class="CONTROL" width="25%"><input type="text"
				name="strSalePrice" maxlength="14" class="txtFldNormal" 
				value="${ItemInventoryPMODeskFB.strSalePrice}"
				onkeypress="return validateData(event,7);" /></td>

			<td class="LABEL" width="25%">Unit
			Name</td>
			<td class="CONTROL" width="25%"><select name="strUnitSaleID" disabled="disabled"
				class='comboNormal'>
				<bean:write name="ItemInventoryPMODeskFB" 
					property="strSalesRateUnitValues" filter="false" />
			</select></td>


		</tr>
		</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"		cellspacing="1px">

		<tr>
			<td colspan="4" class="TITLE" width="25%">P.O. Details</td>
		</tr>
		<logic:notEmpty name="ItemInventoryPMODeskFB" property="strStockRegisterPageNo">
		<tr>

			<td class="LABEL" width="25%">Stock Register Page No.</td>
			<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strStockRegisterPageNo}</td>

			<td class="LABEL" width="25%"></td>
			<td class="CONTROL" width="25%"></td>

		</tr>
		</logic:notEmpty>
		<logic:notEmpty name="ItemInventoryPMODeskFB" property="strTenderNo">
         <tr>

			<td class="LABEL" width="25%">BID No.</td>
			<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strTenderNo}
			</td>

			<td class="LABEL" width="25%">BID Date</td>
			<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strTenderDate}
			</td>

		</tr>
		</logic:notEmpty>
        <logic:notEmpty name="ItemInventoryPMODeskFB" property="strPoNo">
		<tr>

			<td class="LABEL" width="25%">P.O. No.</td>
			<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strPoNo}
			</td>

			<td class="LABEL" width="25%">P.O.
			Date</td>
			<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strPoDate}
			</td>

		</tr>
		</logic:notEmpty>
	<logic:notEmpty name="ItemInventoryPMODeskFB" property="strBillNo">	
	<tr>

			<td class="LABEL" width="25%">Bill No.</td>
			<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strBillNo}
			</td>

			<td class="LABEL" width="25%">Bill Date</td>
			<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strBillDate}
			</td>

		</tr>
	</logic:notEmpty>
		<tr>

			<td class="LABEL" width="25%">Received
			Date</td>
			<td class="CONTROL" width="25%">
			<bean:write name="ItemInventoryPMODeskFB" 
					property="strReceivedDate" filter="false" />
			<!--<dateTag:date
				name="strReceivedDate"
				value="${ItemInventoryPMODeskFB.strReceivedDate}"></dateTag:date>-->
				</td>

			<td class="LABEL" width="25%">Supplied
			By</td>
			<td class="CONTROL" width="25%"><select name="strSuppliedBy" disabled="disabled"
				class="comboMax">
				<bean:write name="ItemInventoryPMODeskFB"
					property="strSuppliedByValues" filter="false" />
			</select></td>

		</tr>

	</table>

	<logic:equal name="ItemInventoryPMODeskFB" property="strWarrantyFlag"
		value="1">

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px" style="display:none;">

			<tr>
				<td colspan="4" class="TITLE" width="25%"><html:checkbox
					property="strIsWarrantyDetails" name="ItemInventoryPMODeskFB"
					value="1" onclick="showOrHideDetails(this,'warrantyItemDtlsDivId');">Whether Warranty Details Required</html:checkbox>
				</td>
			</tr>
		</table>

<div id="warrantyItemDtlsDivId" style="display: none">
<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td class="LABEL" width="25%">Warranty
				Date</td>
				<td class="CONTROL" width="25%"><dateTag:date
					name="strWarrantyDate" value="${ItemInventoryPMODeskFB.strWarrantyDate}"></dateTag:date></td>
				<td class="LABEL" width="25%">Manufacturer</td>
				<td class="CONTROL" width="25%">
				<div id="warrantyManufDivId"><select
					name="strWarantyManufacturer" class="comboMax">
				<bean:write name="ItemInventoryPMODeskFB" property="strManufactureCombo" filter="false"/>
				</select></div>
				</td>
			</tr>

			<tr>
				<td class="LABEL" width="25%">Warranty
				Upto</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strWarrantyUpTo" class="txtFldMin" value="${ItemInventoryPMODeskFB.strWarrantyUpTo}" maxlength="3"
					onkeypress="return validateData(event, 5);"></td>
				<td class="LABEL" width="25%">Unit</td>
				<td class="CONTROL" width="25%"><html:select
					name="ItemInventoryPMODeskFB" styleClass="comboMin"
					property="strWarrantyUpToUnit">
					<html:option value="0">Select Value</html:option>
					<html:option value="1">Day(s)</html:option>
					<html:option value="2">Month(s)</html:option>
					<html:option value="3">Year(s)</html:option>
				</html:select></td>
			</tr>
			<tr>
				<td class="LABEL" colspan="2">Remarks</td>
				<td class="CONTROL" colspan="2"><textarea rows="2" cols="25"
					name="strWarrantyRemarks"><bean:write name="ItemInventoryPMODeskFB" property="strWarrantyRemarks"/> </textarea></td>
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


	<logic:equal name="ItemInventoryPMODeskFB" property="strInstallFlag"
		value="1">

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<tr>
				<td colspan="4" class="TITLE" width="25%">
				<html:checkbox property="strIsInstallDetails" style="border:red;" name="ItemInventoryPMODeskFB" disabled="true" value="1" onclick="showOrHideDetails(this,'installItemDtlsDivId');">Whether Install Details Required</html:checkbox> 
				</td>
			</tr>
		</table>



		<div id="installItemDtlsDivId" style="display: block">

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td class="LABEL" width="25%">Installation Start Date</td>
				<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strInstallStartDate}</td>
				<td class="LABEL" width="25%">Installation End Date</td>
				<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strInstallEndDate}</td>
			</tr>

			<tr style="display: none;">
				<td class="LABEL" width="25%">Installation Status</td>
				<td class="CONTROL" width="25%"> <html:select property="strInstallStatus" name="ItemInventoryPMODeskFB" disabled="true" styleClass="comboNormal">
				<html:option value='0'>Select Value</html:option>
				<html:option value='1'>Success</html:option>
				<html:option value='2'>Failure</html:option>
				</html:select> </td>
				<td class="LABEL" width="25%">Installed By</td>
				<td class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strInstallBy}</td>
			</tr>
			
			<tr>
				<td class="LABEL" width="25%">Installer Contact No.</td>
				<td class="CONTROL" width="25%"> ${ItemInventoryPMODeskFB.strInstallerContactNo}</td>
				<td class="LABEL" width="25%"></td>
				<td class="CONTROL" width="25%"></td>
				<td style="display: none;" class="LABEL" width="25%">Remarks</td>
				<td style="display: none;" class="CONTROL" width="25%">${ItemInventoryPMODeskFB.strInstallRemarks}</td>
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

	<%--
	Free Item will not be treated in this way, any more.
	Suggested by: Amit Kumar
	Changed by: Aritra 
	--%>
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px" style="display: none;" >

		<tr>
			<td colspan="4" class="TITLE" width="25%">
			<div id="freeItemDtlsDivIdPlusId" align="left"
				style="display: block;"><img
				src="../../hisglobal/images/plus.gif"
				onClick="getOtherItemsModifyView('1'),showView('freeItemDtlsDivId');"
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
			<td class="multiLabel" width="20%">Serial No.</td>
			<td class="multiLabel" width="20%">Expiry Date</td>
			<td class="multiLabel" width="20%">Qty.</td>
			<td class="multiLabel" width="5%">&nbsp;&nbsp;</td>
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
				onClick="getOtherItemsModifyView('2'),showView('partItemDtlsDivId');"
				style="cursor: pointer; " /> Component Items</div>
			<div id="partItemDtlsDivIdMinusId" style="display: none;"
				align="left"><img src="../../hisglobal/images/minus.gif"
				onClick="hideView('partItemDtlsDivId');"
				style="cursor: pointer; " /> Component Items <br/> </div>
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

			<td align="center"><img style="cursor: pointer; "
				src="../../hisglobal/images/btn-add.png" onClick="addPartItems();" />
			</td>
		</tr>

	</table>

	</div>

<%--
	Component Item will not be treated in this way, any more.
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
				onClick="showView('paramItemDtlsDivId');"
				style="cursor: pointer; " /> Parameter Details</div>
			<div id="paramItemDtlsDivIdMinusId" style="display: none;"
				align="left"><img src="../../hisglobal/images/minus.gif"
				onClick="hideView('paramItemDtlsDivId');"
				style="cursor: pointer; " /> Parameter Details</div>
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

			<td align="center"><img src="../../hisglobal/images/add_parameter.GIF" onclick="checkForPopup(this,document.forms[0].strItemCategoryNo.value , '2')" style="cursor: pointer; ">
			</td>
		</tr>

	</table>

	</div>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr class="FOOTER">
			<td><font size="2" color="red">*</font>
			Mandatory Fields  , ['*'] Reserved/Branded Item</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">

		<tr>
			<td width="27%"></td>
			<td width="15%" align="right"><img
				style="cursor: pointer; "
				src="../../hisglobal/images/Approve.gif"
				onClick="approveModifyRequest('APPROVE');" title="Click to Approve Modification Request."/></td>

			<td width="15%" align="center"><img
				style="cursor: pointer; "
				src="../../hisglobal/images/reject_tab.gif"
				onClick="rejectModifyRequest('REJECT');" title="Click to Reject Modification Request."/></td>

			<td width="15%" align="left"><img
				style="cursor: pointer; "
				src="../../hisglobal/images/back_tab.png"
				onClick="cancelPage('LIST');" title="Click to Return On Desk"/></td>
			<td width="28%"></td>

		</tr>
	</table>
    
	<input type="hidden" name="strStoreId"
		value="${ItemInventoryPMODeskFB.combo[0]}" />
	<input type="hidden" name="strStoreName"
		value="${ItemInventoryPMODeskFB.strStoreName}" />
	<input type="hidden" name="strGroupId"
		value="${ItemInventoryPMODeskFB.combo[1]}" />
	<input type="hidden" name="strGroupName"
		value="${ItemInventoryPMODeskFB.strGroupName}" />
	<input type="hidden" name="strCtDate"
		value="${ItemInventoryPMODeskFB.strCtDate}">

<input type="hidden" name="strSerialNo"
		value="${ItemInventoryPMODeskFB.strSerialNo}">

	<input type="hidden" name="strItemCategoryNo"
		value="${ItemInventoryPMODeskFB.strItemCategoryNo}" />
	<input type="hidden" name="strItemCategoryName"
		value="${ItemInventoryPMODeskFB.strItemCategoryName}" />

	<input type="hidden" name="strDefaultCurrencyCode"
		value="${ItemInventoryPMODeskFB.strDefaultCurrencyCode}">

	<input type="hidden" name="strChk"
		value="${ItemInventoryPMODeskFB.strChk }">
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strItemSpecification"
		value="${ItemInventoryPMODeskFB.strItemSpecification }">
	<input type="hidden" name="strManufactureId"
		value="${ItemInventoryPMODeskFB.strManufactureId }">
	<input type="hidden" name="isConsumable" value="${ItemInventoryPMODeskFB.isConsumable}" />
	<input type="hidden" name="strRetValue" value="${ItemInventoryPMODeskFB.strRetValue }">
	<input type="hidden" name="strMsg" value="${ItemInventoryPMODeskFB.strMsg }">
		
	<cmbPers:cmbPers />


	
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

<div class="popUpDiv" id="popUpDivId" style="display:none;">
<table bgcolor="white">
<tr>
<td>
<div id="itemParameterDtlDivId" style="display:block;"></div>
</td>
</tr>
</table>
</div>


</html:form>

<jsp:include page="itemInventory_multirow_mmstrans.jsp"></jsp:include>
	<tag:autoIndex></tag:autoIndex>   
</body>
</html>