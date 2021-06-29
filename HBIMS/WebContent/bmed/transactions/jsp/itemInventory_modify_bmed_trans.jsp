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

<his:javascript src="/bmed/transactions/js/item_inventory_trans.js" />
<his:javascript src="/bmed/transactions/js/item_OtherDtls_util.js" />
<his:javascript src="/bmed/transactions/js/itemparameterdetails_util.js" />


</head>

<body onload="initializeModifyPage();" class="background">
<tag:autoIndex>
	<html:form name="itemInventoryTransBean"
		action="/transactions/ItemInventoryTransCNT"
		type="bmed.transactions.controller.fb.ItemInventoryTransFB"
		enctype="multipart/form-data" styleClass="formbg">


		<div class="errMsg"><bean:write name="itemInventoryTransBean"
			property="strErr" /></div>
		<div class="warningMsg"><bean:write
			name="itemInventoryTransBean" property="strWarning" /></div>
		<div style="display: none;" class="normalMsg" id="normalMsg"><bean:write
			name="itemInventoryTransBean" property="strMsg" /></div>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">

			<tr class="HEADER">
				<td colspan="4">Item Inventory &gt;&gt; Modify</td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">Program Name</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strProgramNameCombo"
					filter="false" /></td>
				<td width="25%" class="LABEL">Category</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strItemCategoryName"
					filter="false" /></td>
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
				<td width="25%" class="LABEL">Group Name</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strGroupName"
					filter="false" /></td>
				<td width="25%" class="LABEL">Sub Group Name</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strSubGroupName"
					filter="false" /></td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">Equipment Name</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strItemName" filter="false" />
				</td>
				<td width="25%" class="LABEL">Equipment Model</td>
				<td width="25%" class="CONTROL"><bean:write
					name="itemInventoryTransBean" property="strItemBrandName"
					filter="false" /></td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">Manufacturer Name</td>
				<td width="25%" class="CONTROL">${itemInventoryTransBean.strManufactureName}</td>
				<td width="25%" class="LABEL">Manufacture Date</td>
				<td width="25%" class="CONTROL"><dateTag:date
					name="strManufactureDate"
					value="${itemInventoryTransBean.strManufactureDate}"></dateTag:date></td>
			</tr>
			<tr>
				<td width="25%" class="LABEL">
				<div id="batchNoDivId">Serial No.</div>
				<input type="hidden" name="isBatchReq" value="0"></td>
				<td width="25%" class="CONTROL"><!--<input type="text" name="strBatchNo"  maxlength="20" class="txtFldNormal"  
				value="${itemInventoryTransBean.strBatchNo}" onkeypress="return validateData(event,17);" />-->
				<bean:write name="itemInventoryTransBean" property="strBatchNo"
					filter="false" /> <input type="hidden" name="strOldBatchNo"
					value="${itemInventoryTransBean.strBatchNo}"></td>
				<td class="LABEL">
				<div id="equipMandatoryDivId"">Equipment Status</div>

				</td>
				<td width="25%" class="CONTROL">
				<div id="equipDivId"><select class="comboMax"
					name="strEquipStatusId">
					<bean:write name="itemInventoryTransBean"
						property="strEquipStatusValues" filter="false" />
				</select></div>
				</td>
			</tr>

			<tr id="inHandRow">
				<td class="LABEL" width="25%"><font color="red">*</font>InHand
				Quantity</td>

				<td class="CONTROL" width="25%"><input type="text"
					name="strInHandQuantity" maxlength="8" class="txtFldNormal"
					readonly="readonly"
					onkeyup="return validateQty('strInHandQuantity','strInHandQuantityUnitID');"
					value="${itemInventoryTransBean.strInHandQuantity}"
					onkeypress="return validateData(event,7);" /></td>
				<td class="LABEL" width="25%"><font color="red">*</font>InHand
				Quantity Unit</td>
				<td class="CONTROL" width="25%"><bean:write
					name="itemInventoryTransBean"
					property="strInHandQuantityUnitValues" filter="false" /></td>
			</tr>

			<tr>
				<td width="25%" class="LABEL">Specification</td>
				<td width="25%" class="CONTROL">${itemInventoryTransBean.strItemSpecification}
				</td>
				<input type="hidden" name="strItemSpecification"
					value="${itemInventoryTransBean.strItemSpecification}" />
				<td class="LABEL" width="25%"></td>
				<td class="CONTROL" width="25%"></td>
			</tr>

		</table>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr class="HEADER">
				<td colspan="4" width="25%">Rate Details</td>
			</tr>

			<tr>
				<td class="LABEL" width="25%"><font color="red">*</font>Purchase
				Rate/Unit</td>
				<td class="CONTROL" colspan="1" width="25%"><input type="text"
					name="strRate" maxlength="14" class="txtFldNormal"
					value="${itemInventoryTransBean.strRate}"
					onkeypress="return validateData(event,7);" /></td>
				<td class="LABEL" width="25%"><font color="red">*</font>Unit
				Name</td>
				<td class="CONTROL" width="25%"><select name="strUnitRateID"
					class='comboNormal'>
					<bean:write name="itemInventoryTransBean"
						property="strRateUnitValues" filter="false" />
				</select></td>
			</tr>
		</table>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr class="HEADER">
				<td colspan="4" width="25%">P.O. Details</td>
			</tr>
			<tr>
				<td class="LABEL" width="25%">Stock Register Page No.</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strStockRegisterPageNo" maxlength="10"
					value="${itemInventoryTransBean.strStockRegisterPageNo}"
					class="txtFldMax" onkeypress="return validateData(event,7);" /></td>
				<td class="LABEL" width="25%"></td>
				<td class="CONTROL" width="25%"></td>

			</tr>
			<tr>
				<td class="LABEL" width="25%">BID No.</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strTenderNo" maxlength="35"
					value="${itemInventoryTransBean.strTenderNo}" class="txtFldMax"
					onkeypress="return validateData(event,21);" /></td>

				<td class="LABEL" width="25%">BID Date</td>
				<td class="CONTROL" width="25%"><dateTag:date
					name="strTenderDate"
					value="${itemInventoryTransBean.strTenderDate}"></dateTag:date></td>
			</tr>
			<tr>

				<td class="LABEL" width="25%">P.O. No.</td>
				<td class="CONTROL" width="25%"><input type="text"
					value="${itemInventoryTransBean.strPoNo}" name="strPoNo"
					maxlength="35" class="txtFldMax"
					onkeypress="return validateData(event,21);" /></td>
				<td class="LABEL" width="25%">P.O. Date</td>
				<td class="CONTROL" width="25%"><dateTag:date name="strPoDate"
					value="${itemInventoryTransBean.strPoDate}"></dateTag:date></td>
			</tr>
			<tr>
				<td class="LABEL" width="25%">Bill No.</td>
				<td class="CONTROL" width="25%"><input type="text"
					name="strBillNo" maxlength="35" class="txtFldMax"
					onkeypress="return validateData(event,21);"
					value="${itemInventoryTransBean. strBillNo}" /></td>
				<td class="LABEL" width="25%">Bill Date</td>
				<td class="CONTROL" width="25%"><dateTag:date
					name="strBillDate" value="${itemInventoryTransBean. strBillDate}"></dateTag:date>
				</td>
			</tr>
			<tr>
				<td class="LABEL" width="25%"><font color="red">*</font>Received
				Date</td>
				<td class="CONTROL" width="25%"><dateTag:date
					name="strReceivedDate"
					value="${itemInventoryTransBean.strReceivedDate}"></dateTag:date></td>
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
					<td colspan="4" width="25%"><html:checkbox
						property="strIsWarrantyDetails" name="itemInventoryTransBean"
						value="1"
						onclick="showOrHideDetails(this,'warrantyItemDtlsDivId');">Whether Warranty Details Required</html:checkbox>
					</td>
				</tr>
			</table>

			<div id="warrantyItemDtlsDivId" style="display: none">
			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Warranty
					Date</td>
					<td class="CONTROL" width="25%"><dateTag:date
						name="strWarrantyDate"
						value="${itemInventoryTransBean.strWarrantyDate}"></dateTag:date></td>
					<td class="LABEL" width="25%"><font color="red">*</font>Manufacturer</td>
					<td class="CONTROL" width="25%">
					<div id="warrantyManufDivId"><select
						name="strWarantyManufacturer" class="comboMax">
						<bean:write name="itemInventoryTransBean"
							property="strManufactureCombo" filter="false" />
					</select></div>
					</td>
				</tr>

				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Warranty
					Upto</td>
					<td class="CONTROL" width="25%"><input type="text"
						name="strWarrantyUpTo" class="txtFldMin"
						value="${itemInventoryTransBean.strWarrantyUpTo}" maxlength="3"
						onkeypress="return validateData(event, 5);"></td>
					<td class="LABEL" width="25%"><font color="red">*</font>Unit</td>
					<td class="CONTROL" width="25%"><html:select
						name="itemInventoryTransBean" styleClass="comboMin"
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
						name="strWarrantyRemarks"><bean:write
						name="itemInventoryTransBean" property="strWarrantyRemarks" /> </textarea></td>
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

				<tr class="HEADER">
					<td colspan="4" width="25%"><html:checkbox
						property="strIsInstallDetails" name="itemInventoryTransBean"
						value="1" onchange="">Whether Installation Details Required</html:checkbox>
					</td>
				</tr>
			</table>



			<div id="installItemDtlsDivId" style="display: none">

			<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
				cellspacing="1px">
				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Installation
					Start Date</td>
					<td class="CONTROL" width="25%"><dateTag:date
						name="strInstallStartDate"
						value="${itemInventoryTransBean.strInstallStartDate}"></dateTag:date>
					</td>
					<td class="LABEL" width="25%"><font color="red">*</font>Installation
					End Date</td>
					<td class="CONTROL" width="25%"><dateTag:date
						name="strInstallEndDate"
						value="${itemInventoryTransBean.strInstallEndDate}"></dateTag:date>
					</td>
				</tr>

				<tr style="display: none;">
					<td class="LABEL" width="25%"><font color="red">*</font>Installation
					Status</td>
					<td class="CONTROL" width="25%"><html:select
						property="strInstallStatus" name="itemInventoryTransBean"
						styleClass="comboNormal">
						<html:option value='0'>Select Value</html:option>
						<html:option value='1'>Success</html:option>
						<html:option value='2'>Failure</html:option>
					</html:select></td>
					<td class="LABEL" width="25%"><font color="red">*</font>Installed
					By</td>
					<td class="CONTROL" width="25%"><input type="text"
						class="txtFldMax" name="strInstallBy" maxlength="100"
						value="${itemInventoryTransBean.strInstallBy}"></td>
				</tr>

				<tr>
					<td class="LABEL" width="25%"><font color="red">*</font>Company
					Contact Detail</td>
					<td class="CONTROL" width="25%"><input type="text"
						name="strInstallerContactNo" class="txtFldNormal" maxlength="10"
						value="${itemInventoryTransBean.strInstallerContactNo}"></td>
					<td class="LABEL" width="25%"></td>
					<td class="CONTROL" width="25%"></td>
					<td style="display: none;" class="LABEL" width="25%">Remarks</td>
					<td style="display: none;" class="CONTROL" width="25%"><textarea
						rows="2" cols="25" name="strInstallRemarks"><bean:write
						property="strInstallRemarks" name="itemInventoryTransBean"
						filter="false" /></textarea></td>
				</tr>

			</table>

			</div>

		</logic:equal>




		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
			<tr>
				<td class="LABEL" width="25%">Ref. No./Ref Date</td>
				<td class="CONTROL" width="25%"><input type="text"
					class="txtFldMax" name="strDocRefNo"
					value="${itemInventoryTransBean.strDocRefNo}"
					onkeypress="return validateDataWithSpecialChars(event,9 ,'\')"
					maxlength="30">/ <dateTag:date name="strDocRefDate"
					value="${itemInventoryTransBean.strDocRefDate}"></dateTag:date></td>
			</tr>
			<tr id="DocDownloadRow">
				<td class="LABEL">Old Installation Report/P.O.Copy</td>
				<td class="CONTROL" colspan="2">
				<div>
				<div id="IconDiv" style="position: relative; left: 3px; top: 16px;"><img
					src="../../hisglobal/images/global-icon.png"></div>
				<div
					style="color: blue; left: 30px; position: relative; top: -10px;">${itemInventoryTransBean.strUploadFileName}
				<input style="position: relative;" type="button" class="btn"
					value="View / Download" onclick="getUploadFile();"></div>
				</div>
				</td>
			</tr>
			<tr>
				<td class="LABEL" width="25%">Installation Report/P.O.Copy</td>
				<td class="CONTROL" width="25%"><html:file
					property="strLocation" /></td>
			</tr>
		</table>

		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
			cellspacing="1px">
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
				<div><a href="#" class="button" onClick="validatemodify();"  title="Click to Save Record"><span
					class="save">Update</span></a> 
				<a href="#" class="button" onClick="cancelPage('LIST');" title="Click to Return on Desk"><span
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
					onClick="validatemodify();" /> <img
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
		<input type="hidden" name="strGroupName"
			value="${itemInventoryTransBean.strGroupName}" />
		<input type="hidden" name="strCtDate"
			value="${itemInventoryTransBean.strCtDate}">
		<input type="hidden" name="strInHandQuantityUnitID"
			value="${itemInventoryTransBean.strInHandQuantityUnitID}">

		<input type="hidden" name="strSerialNo"
			value="${itemInventoryTransBean.strSerialNo}">
		<input type="hidden" name="strUIDNo"
			value="${itemInventoryTransBean.strUIDNo}">
		<input type="hidden" name="strItemCategoryNo"
			value="${itemInventoryTransBean.strItemCategoryNo}" />
		<input type="hidden" name="strItemCategoryName"
			value="${itemInventoryTransBean.strItemCategoryName}" />

		<input type="hidden" name="strDefaultCurrencyCode"
			value="${itemInventoryTransBean.strDefaultCurrencyCode}">

		<input type="hidden" name="strGroupId"
			value="${itemInventoryTransBean.strGroupId}">

		<input type="hidden" name="strSubGroupId"
			value="${itemInventoryTransBean.strSubGroupId}">
		<input type="hidden" name="strChk"
			value="${itemInventoryTransBean.strChk }">
		<input type="hidden" name="hmode" />

		<input type="hidden" name="strManufacturId"
			value="${itemInventoryTransBean.strManufacturId}">

		<input type="hidden" name="strManufactureName"
			value="${itemInventoryTransBean.strManufactureName}">

		<input type="hidden" name="strInHandQuantity"
			value="${itemInventoryTransBean.strInHandQuantity}">
		<input type="hidden" name="strUploadFileId"
			value="${itemInventoryTransBean.strUploadFileId}">
		<input type="hidden" name="strChildFolder"
			value="${itemInventoryTransBean.strChildFolder}">

		<cmbPers:cmbPers />

		<input type="hidden" name="strRegFlag" value="" />
		<input type="hidden" name="strConfigIssueRate"
			value="${itemInventoryTransBean.strConfigIssueRate}">
		<input type="hidden" name="isConsumable"
			value="${itemInventoryTransBean.isConsumable}" />
		<input type="hidden" name="strInstallationId"
			value="${itemInventoryTransBean.strInstallationId}" />

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
		<input type="hidden" name="strRetValue"
			value="${itemInventoryTransBean.strRetValue}" />
		<input type="hidden" name="strMsg"
			value="${itemInventoryTransBean.strMsg}" />

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
</tag:autoIndex>
<%-- <jsp:include page="itemInventory_multirow_mmstrans.jsp"></jsp:include> --%>
</body>
</html>