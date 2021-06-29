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

<script language="JavaScript" src="../../bmed/transactions/js/hemms_moic_inventory_desk.js"></script>

<script language="JavaScript" src="../js/item_OtherDtls_util.js"></script>

<script language="Javascript" src="../js/itemparameterdetails_util.js"></script>

</head>

<body onload="initializeViewPage();">
<html:form name="ItemInventoryMOICDeskFB"
	action="/transactions/ItemInventoryMOICDeskCNT"
	type="bmed.transactions.controller.fb.ItemInventoryMOICDeskFB" enctype="multipart/form-data">


	<div class="errMsg"><bean:write name="ItemInventoryMOICDeskFB"
		property="strErr" /></div>
	<div class="warningMsg"><bean:write name="ItemInventoryMOICDeskFB"
		property="strWarning" /></div>
	<div style="display:none;" class="normalMsg" id="normalMsg"><bean:write
		name="ItemInventoryMOICDeskFB" property="strMsg" /></div>

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
				name="ItemInventoryMOICDeskFB" property="strDepartmentName" filter="false" />
			</td>
			<td width="25%" class="LABEL">Lab Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryMOICDeskFB" property="strStoreName"
				filter="false" /></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Category</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryMOICDeskFB" property="strItemCategoryName" filter="false" />
			</td>
			<td width="25%" class="LABEL">Group Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryMOICDeskFB" property="strGroupName"
				filter="false" /></td>
		</tr>
		<tr>
			<td width="25%"   class="LABEL">Sub Group Name</td>
			<td width="25%"   class="CONTROL"><bean:write
				name="ItemInventoryMOICDeskFB" property="strSubGroupName" filter="false" />
			</td>
		
			<td width="25%" class="LABEL">Equipment Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryMOICDeskFB" property="strItemName"
				filter="false" /></td>
</tr>
		<tr>

			<td width="25%" class="LABEL">Equipment Model</td>
			<td width="25%" class="CONTROL">
			<!--<bean:write	name="ItemInventoryMOICDeskFB" property="strItemBrandName" filter="false" />-->
			<select
				name="strItemBrandId" class='comboMax' disabled="disabled" onchange="return validateQty('strInHandQuantity','strInHandQuantityUnitID');" >
				<bean:write name="ItemInventoryMOICDeskFB"	property="strEquipModelValues" filter="false" />
			</select>
			</td>
		
			<td width="25%" class="LABEL">Manufacturer Name</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryMOICDeskFB" property="strManufactureName"
				filter="false" />
			</td>

		</tr>
		<tr>
		<td width="25%" class="LABEL">
				<div id="batchNoDivId" ><font color="red">*</font>Serial No.</div>
						<input type="hidden" name="isBatchReq" value="0">
			<!--  
		<logic:empty name="ItemInventoryMOICDeskFB" property="strBatchNo">Serial No.
					<input type="hidden" name="isBatchReq" value="0">
				</logic:empty> 
				
				<logic:notEmpty name="ItemInventoryMOICDeskFB" property="strBatchNo">Serial No.
					<input type="hidden" name="isBatchReq" value="1">
				</logic:notEmpty> -->
		</td>
			<td width="25%" class="CONTROL"><input type="text"
				name="strBatchNo" maxlength="20" class="txtFldNormal" readonly="readonly" 
				value="${ItemInventoryMOICDeskFB.strBatchNo}" onkeypress="return validateData(event,17);" />
			 	<!--<bean:write name="ItemInventoryMOICDeskFB" property="strBatchNo" filter="false" />-->
			 <input type="hidden" name="strOldBatchNo" value="${ItemInventoryMOICDeskFB.strBatchNo}">
		</td>
		<td width="25%" class="LABEL" >Specification
			</td>
			
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryMOICDeskFB" property="strItemSpecification"
				filter="false" />
			</td>		
		</tr>
		<tr style="display: none;">
		<td width="25%" class="LABEL">Specification</td>
			<td width="25%" class="CONTROL"><bean:write
				name="ItemInventoryMOICDeskFB" property="strItemSpecification"
				filter="false" /></td>
		<td style="display: none;" class="LABEL" width="25%">Manufacture Date</td>
			<td style="display: none;" class="CONTROL" width="25%"><dateTag:date
				name="strManufactureDate"
				value="${ItemInventoryMOICDeskFB.strManufactureDate}"></dateTag:date></td>
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
				<bean:write name="ItemInventoryMOICDeskFB" property="strEquipStatusValues" filter="false" />
			</select></div>
			  </td>

			<td class="LABEL" >
		
		<div style="display: none;">
			<logic:empty name="ItemInventoryMOICDeskFB" property="strExpiryDate">
				<input type="hidden" name="isExpirtReq" value="0">Expiry Date
			</logic:empty> <logic:notEmpty name="ItemInventoryMOICDeskFB"
				property="strExpiryDate">
				<input type="hidden" name="isExpirtReq" value="1">Expiry Date
			</logic:notEmpty>
			</div>
			
			</td>

			<td class="CONTROL" width="25%">
				<div style="display: none;">				
					<dateTag:date name="strExpiryDate" value="${ItemInventoryMOICDeskFB.strExpiryDate}"></dateTag:date>
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

				<bean:write name="ItemInventoryMOICDeskFB"
					property="strStockStatusValues" filter="false" />

			</select></td>
		</tr>

		<tr id="inHandRow">
			<td class="LABEL" width="25%">InHand
			Quantity</td>

			<td class="CONTROL" width="25%"><input type="text"
				name="strInHandQuantity" maxlength="8" class="txtFldNormal" readonly="readonly" onkeyup="return validateQty('strInHandQuantity','strInHandQuantityUnitID');"
				value="${ItemInventoryMOICDeskFB.strInHandQuantity}"
				onkeypress="return validateData(event,7);" /></td>

			<td  class="LABEL" width="25%">InHand
			Quantity Unit</td>
			<td class="CONTROL" width="25%">
			<bean:write	name="ItemInventoryMOICDeskFB" property="strInHandQuantityUnitValues" filter="false" />
			</td>
<!--  		<td class="CONTROL" width="25%"><select
				name="strInHandQuantityUnitID" class='comboNormal' onchange="return validateQty('strInHandQuantity','strInHandQuantityUnitID');" >
				<bean:write name="ItemInventoryMOICDeskFB"
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
				value="${ItemInventoryMOICDeskFB.strFreeItemQuantity}" onkeypress="return validateData(event,5);" /></td>

			<td class="LABEL" width="25%">Free Item Qty. Unit</td>
			<td class="CONTROL" width="25%">
			<select name="strFreeItemQuantityUnitID"
				class='comboNormal'>
				<bean:write name="ItemInventoryMOICDeskFB"
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
				<bean:write name="ItemInventoryMOICDeskFB"
					property="strCurrencyCodeValues" filter="false" />
			</select></td>

			<td class="LABEL" width="25%"><logic:equal
				name="ItemInventoryMOICDeskFB" property="strCurrencyCode"
				value="${ItemInventoryMOICDeskFB.strDefaultCurrencyCode}">
				<div id="currencyDivId">Currency Value</div>
				<input type="hidden" name="isCurrencyReq" value="0">
			</logic:equal> <logic:notEqual name="ItemInventoryMOICDeskFB"
				property="strCurrencyCode"
				value="${ItemInventoryMOICDeskFB.strDefaultCurrencyCode}">
				<div id="currencyDivId">Currency
				Value</div>
				<input type="hidden" name="isCurrencyReq" value="1">
			</logic:notEqual></td>

			<td class="CONTROL" width="25%"><logic:equal
				name="ItemInventoryMOICDeskFB" property="strCurrencyCode"
				value="${ItemInventoryMOICDeskFB.strDefaultCurrencyCode}">
				<input type="text" name="strCurrencyValue" maxlength="8"
					class="txtFldNormal" onkeypress="return validateData(event,7);"
					value="${ItemInventoryMOICDeskFB.strCurrencyValue}" />
			</logic:equal> <logic:notEqual name="ItemInventoryMOICDeskFB"
				property="strCurrencyCode"
				value="${ItemInventoryMOICDeskFB.strDefaultCurrencyCode}">
				<input type="text" name="strCurrencyValue" maxlength="8"
					class="txtFldNormal" onkeypress="return validateData(event,7);"
					value="${ItemInventoryMOICDeskFB.strCurrencyValue}" />
			</logic:notEqual></td>

		</tr>

		<tr>
			<td class="LABEL" width="25%">Rate/Unit
			</td>

			<td class="CONTROL" colspan="1" width="25%"><input type="text"
				name="strRate" maxlength="14" class="txtFldNormal" readonly="readonly"
				value="${ItemInventoryMOICDeskFB.strRate}"
				onkeypress="return validateData(event,7);" /></td>

			<td class="LABEL" width="25%">Unit
			Name</td>
			<td class="CONTROL" width="25%"><select name="strUnitRateID" disabled="disabled"
				class='comboNormal'>
				<bean:write name="ItemInventoryMOICDeskFB"
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
				value="${ItemInventoryMOICDeskFB.strSalePrice}"
				onkeypress="return validateData(event,7);" /></td>

			<td class="LABEL" width="25%">Unit
			Name</td>
			<td class="CONTROL" width="25%"><select name="strUnitSaleID" disabled="disabled"
				class='comboNormal'>
				<bean:write name="ItemInventoryMOICDeskFB" 
					property="strSalesRateUnitValues" filter="false" />
			</select></td>


		</tr>
		</table>
		</div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"		cellspacing="1px">

		<tr>
			<td colspan="4" class="TITLE" width="25%">P.O. Details</td>
		</tr>
		<logic:notEmpty name="ItemInventoryMOICDeskFB" property="strStockRegisterPageNo">
		<tr>

			<td class="LABEL" width="25%">Stock Register Page No.</td>
			<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strStockRegisterPageNo}</td>

			<td class="LABEL" width="25%"></td>
			<td class="CONTROL" width="25%"></td>

		</tr>
		</logic:notEmpty>
		<logic:notEmpty name="ItemInventoryMOICDeskFB" property="strTenderNo">
         <tr>

			<td class="LABEL" width="25%">BID No.</td>
			<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strTenderNo}
			</td>

			<td class="LABEL" width="25%">BID Date</td>
			<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strTenderDate}
			</td>

		</tr>
		</logic:notEmpty>
        <logic:notEmpty name="ItemInventoryMOICDeskFB" property="strPoNo">
		<tr>

			<td class="LABEL" width="25%">P.O. No.</td>
			<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strPoNo}
			</td>

			<td class="LABEL" width="25%">P.O.
			Date</td>
			<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strPoDate}
			</td>

		</tr>
		</logic:notEmpty>
	<logic:notEmpty name="ItemInventoryMOICDeskFB" property="strBillNo">	
	<tr>

			<td class="LABEL" width="25%">Bill No.</td>
			<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strBillNo}
			</td>

			<td class="LABEL" width="25%">Bill Date</td>
			<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strBillDate}
			</td>

		</tr>
	</logic:notEmpty>
		<tr>

			<td class="LABEL" width="25%">Received
			Date</td>
			<td class="CONTROL" width="25%">
			<bean:write name="ItemInventoryMOICDeskFB" 
					property="strReceivedDate" filter="false" />
			<!--<dateTag:date
				name="strReceivedDate"
				value="${ItemInventoryMOICDeskFB.strReceivedDate}"></dateTag:date>-->
				</td>

			<td class="LABEL" width="25%">Supplied
			By</td>
			<td class="CONTROL" width="25%"><select name="strSuppliedBy" disabled="disabled"
				class="comboMax">
				<bean:write name="ItemInventoryMOICDeskFB"
					property="strSuppliedByValues" filter="false" />
			</select></td>

		</tr>

	</table>

	<logic:equal name="ItemInventoryMOICDeskFB" property="strWarrantyFlag"
		value="1">

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px" style="display:none;">

			<tr>
				<td colspan="4" class="TITLE" width="25%"><html:checkbox
					property="strIsWarrantyDetails" name="ItemInventoryMOICDeskFB"
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
					name="strWarrantyDate" value="${ItemInventoryMOICDeskFB.strWarrantyDate}"></dateTag:date></td>
				<td class="LABEL" width="25%">Manufacturer</td>
				<td class="CONTROL" width="25%">
				<div id="warrantyManufDivId"><select
					name="strWarantyManufacturer" class="comboMax">
				<bean:write name="ItemInventoryMOICDeskFB" property="strManufactureCombo" filter="false"/>
				</select></div>
				</td>
			</tr>

			<tr>
				<td class="LABEL" width="25%">Warranty
				Upto</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strWarrantyUpTo" class="txtFldMin" value="${ItemInventoryMOICDeskFB.strWarrantyUpTo}" maxlength="3"
					onkeypress="return validateData(event, 5);"></td>
				<td class="LABEL" width="25%">Unit</td>
				<td class="CONTROL" width="25%"><html:select
					name="ItemInventoryMOICDeskFB" styleClass="comboMin"
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
					name="strWarrantyRemarks"><bean:write name="ItemInventoryMOICDeskFB" property="strWarrantyRemarks"/> </textarea></td>
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


	<logic:equal name="ItemInventoryMOICDeskFB" property="strInstallFlag"
		value="1">

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<tr>
				<td colspan="4" class="TITLE" width="25%">
				<html:checkbox property="strIsInstallDetails" style="border:red;" name="ItemInventoryMOICDeskFB" disabled="true" value="1" onclick="showOrHideDetails(this,'installItemDtlsDivId');">Whether Install Details Required</html:checkbox> 
				</td>
			</tr>
		</table>



		<div id="installItemDtlsDivId" style="display: block">

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td class="LABEL" width="25%">Installation Start Date</td>
				<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strInstallStartDate}</td>
				<td class="LABEL" width="25%">Installation End Date</td>
				<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strInstallEndDate}</td>
			</tr>

			<tr style="display: none;">
				<td class="LABEL" width="25%">Installation Status</td>
				<td class="CONTROL" width="25%"> <html:select property="strInstallStatus" name="ItemInventoryMOICDeskFB" disabled="true" styleClass="comboNormal">
				<html:option value='0'>Select Value</html:option>
				<html:option value='1'>Success</html:option>
				<html:option value='2'>Failure</html:option>
				</html:select> </td>
				<td class="LABEL" width="25%">Installed By</td>
				<td class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strInstallBy}</td>
			</tr>
			
			<tr>
				<td class="LABEL" width="25%">Installer Contact No.</td>
				<td class="CONTROL" width="25%"> ${ItemInventoryMOICDeskFB.strInstallerContactNo}</td>
				<td class="LABEL" width="25%"></td>
				<td class="CONTROL" width="25%"></td>
				<td style="display: none;" class="LABEL" width="25%">Remarks</td>
				<td style="display: none;" class="CONTROL" width="25%">${ItemInventoryMOICDeskFB.strInstallRemarks}</td>
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
				src="../../hisglobal/images/Verify.gif"
				onClick="verifyInventory('VERIFY');" title="Click to Verify Inventory"/></td>

			<td width="15%" align="center"><img
				style="cursor: pointer; "
				src="../../hisglobal/images/back_tab.png"
				onClick="cancelPage('LIST');" title="Click to Return On Desk"/></td>

			<td width="15%" align="left"><img
				style="cursor: pointer; "
				src="../../hisglobal/images/btn-mo.png"
				onClick="modifyRequest('MODIFYREQUEST');" title="Click for Modify Request send to CM&HO/PMO"/></td>
			<td width="28%"></td>

		</tr>
	</table>
    
	<input type="hidden" name="strStoreId"
		value="${ItemInventoryMOICDeskFB.combo[0]}" />
	<input type="hidden" name="strStoreName"
		value="${ItemInventoryMOICDeskFB.strStoreName}" />
	<input type="hidden" name="strGroupId"
		value="${ItemInventoryMOICDeskFB.combo[1]}" />
	<input type="hidden" name="strGroupName"
		value="${ItemInventoryMOICDeskFB.strGroupName}" />
	<input type="hidden" name="strCtDate"
		value="${ItemInventoryMOICDeskFB.strCtDate}">

<input type="hidden" name="strSerialNo"
		value="${ItemInventoryMOICDeskFB.strSerialNo}">

	<input type="hidden" name="strItemCategoryNo"
		value="${ItemInventoryMOICDeskFB.strItemCategoryNo}" />
	<input type="hidden" name="strItemCategoryName"
		value="${ItemInventoryMOICDeskFB.strItemCategoryName}" />

	<input type="hidden" name="strDefaultCurrencyCode"
		value="${ItemInventoryMOICDeskFB.strDefaultCurrencyCode}">

	<input type="hidden" name="strChk"
		value="${ItemInventoryMOICDeskFB.strChk }">
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strItemSpecification"
		value="${ItemInventoryMOICDeskFB.strItemSpecification }">
	<input type="hidden" name="strManufactureId"
		value="${ItemInventoryMOICDeskFB.strManufactureId }">
	<input type="hidden" name="isConsumable" value="${ItemInventoryMOICDeskFB.isConsumable}" />
	<input type="hidden" name="strRetValue" value="${ItemInventoryMOICDeskFB.strRetValue }">
	<input type="hidden" name="strMsg" value="${ItemInventoryMOICDeskFB.strMsg }">
		
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