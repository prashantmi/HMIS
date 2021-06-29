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
<title>Centralized Spare Part Management Desk</title>

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

<script language="JavaScript" src="../js/item_inventory_trans.js"></script>

<script language="JavaScript" src="../js/item_OtherDtls_util.js"></script>

<script language="Javascript" src="../js/itemparameterdetails_util.js"></script>

<script type="text/javascript">

</script>

</head>

<body onload="chkRecordSaved()">
<tag:autoIndex>
<html:form name="CentralizeWorkshopMonitoringDeskFB"
	action="/transactions/CentralizeWorkshopMonitoringDeskACTION"
	type="bmed.transactions.controller.fb.CentralizeWorkshopMonitoringDeskFB" enctype="multipart/form-data">


	<div class="errMsg" id="errMsg"><bean:write
		name="CentralizeWorkshopMonitoringDeskFB" property="strErr" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="CentralizeWorkshopMonitoringDeskFB" property="strWarning" /></div>
	<div style="display:none;" class="normalMsg" id="normalMsg"><bean:write
		name="CentralizeWorkshopMonitoringDeskFB" property="strMsg" filter="false" /></div>

	<center><tag:tab tabLabel="Centralized Equipment Inventory "
		selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab></center>

	<table id="itemTable" class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4"></td>
		</tr>


		<tr>
			<td width="25%" class="LABEL">Workshop Name</td>
			<td width="25%" class="CONTROL"></td>
			<td width="25%" class="LABEL">Lab Name</td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Category</td>
			<td width="25%" class="CONTROL"></td>
			<td width="25%" class="LABEL">Group Name</td>
			<td width="25%" class="CONTROL"></td>
		</tr>


		<tr>
			<td width="25%" class="LABEL">Sub Group Name</td>
			<td width="25%" class="CONTROL"><select name="strSubGroupId"
				class="comboNormal" onChange="ajaxItemName('ITEMNAME');showOrHideDetails();">
				
			</select>
			</td>
			<td class="LABEL" width="25%"><font color="red">*</font>Equipment Name</td>
			<td class="CONTROL" width="25%"><div id="ItemNameId"><select name="strItemId"
				class='comboNormal' onChange="ajaxItemBrandName('ITEMBRANDNAME');">
				
			</select></div></td>
		</tr>
		<tr>
			<td class="LABEL" width="25%"><font color="red">*</font>Equipment Model</td>
			<td class="CONTROL" width="25%">
			<div id="ItemBrandId"><select name="strItemBrandId"
				class='comboNormal'>
				<option value="0">Select Value</option>
			</select></div>
			</td>
			<td class="LABEL" width="25%"><div id="manfNotMandatoryDivId" style="display: none;">Manufacture</div>
			<div id="manfMandatoryDivId" style="display: block;"><font color="red">*</font>Manufacture</div>
			</td>
			<td class="CONTROL" width="25%">
			<div id="manufDivId"><select class="comboMax" name="strManufactureId">
				<option value='0'>Select Value</option>
			</select></div>
			</td>
		</tr>
		<tr >
			<td class="LABEL">
			<div id="batchNoDivId" ><font color="red">*</font>Serial No.</div>
			<input type="hidden" name="isBatchReq" value="0">
			</td>
			<td width="25%" class="CONTROL">
			<div id="batchNoTextDivId" >
			<input type="text" id="strBatchNo" name="strBatchNo" maxlength="20" class="txtFldMax"
				onkeypress="return validateData(event,17);" />
						<img id="imgStockDetails" title="Hide" onclick="ajaxChkDuplicate()" 
						src="/HEMMS_ODISHA/hisglobal/images//plus.gif" style="cursor: pointer;">			
			</div>
			</td>
			<td class="LABEL">
			<div id="batchNoDivId" ><div id="equipMandatoryDivId""><font color="red">*</font>Equipment Status</div></td>

			<td class="CONTROL" width="25%">
			<div id="equipDivId"><select class="comboMax"
				name="strEquipStatusId">
			</select></div>
				</td>
		</tr>


	</table>
	<input type="hidden" name="strDepartmentId"
		value="" />
	<input type="hidden" name="strDepartmentName"
		value="" />
	<input type="hidden" name="strStoreId"
		value="$" />
	<input type="hidden" name="strStoreName"
		value="" />
	<input type="hidden" name="strGroupId"
		value="" />
	<input type="hidden" name="strGroupName"
		value="" />

	<input type="hidden" name="strItemCategoryNo"
		value="" />
	<input type="hidden" name="strItemCategoryName"
		value="" />

	<input type="hidden" name="strCtDate"
		value="">

	<input type="hidden" name="strDefaultCurrencyCode"
		value="">
		
		<input type="hidden"  name="strConfigIssueRate"  value="">
		
        <input type="hidden"  name="strIssueRateConfigFlg"  value="">
	<input type="hidden" name="strRetValue"
		value="" />
	<input type="hidden" name="strMsg"
		value="" />
		

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

<div class="popUpDiv" id="popUpDivId" style="display:none;">
<table bgcolor="white">
<tr>
<td>
<div id="itemParameterDtlDivId" style="display:block;"></div>
</td>
</tr>
</table>
</div>

	<cmbPers:cmbPers />
</html:form>

<jsp:include page="itemInventory_multirow_mmstrans.jsp"></jsp:include>
	</tag:autoIndex>   
</body>
</html>








