<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>



<html>
<head>
<meta charset=UTF-8">
<title>Item Inventory</title>


<!-- added 20 April 2020 -->
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
	src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script><!-- end -->
<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">


<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" 
type="text/css">

<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>

<script language="JavaScript" src="../js/receivedItemDetailsNEW.js"></script>

<script language="JavaScript" src="../js/item_OtherDtls_util.js"></script>

<script language="Javascript" src="../js/itemparameterdetails_util.js"></script>
<style type="text/css">
.col-sm-1half, .col-sm-8half, .col-sm-half {
    position: relative;
    min-height: 1px;
    padding-right: 15px;
    padding-left: 15px;
}
.custom-radio .custom-control-label::before {
    background-color: white;  /* orange */
}

.col-md-1half, .col-md-8half, .col-md-half {
    position: relative;
    min-height: 1px;
    padding-right: 15px;
    padding-left: 15px;
}
@media (min-width: 768px) {
    .col-sm-1half,.col-md-1half, .col-sm-8half {
        float: left;
    }
    .col-sm-1half ,.col-md-1half{
        width: 12.495%;
    }
    .col-sm-half,.col-md-half {
        width: 4.165%;
    }
    .col-sm-2half,.col-sm-2half {
        width: 20.495%;
    }
}
.table
{
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-weight: 400;
	color: #212529;
}
textarea
{
width:100%;

}
</style>

<style>
 .panel-group .panel {
        border-radius: 0;
        box-shadow: none;
        border-color: #EEEEEE;
    }

    .panel-default > .panel-heading {
        padding: 0;
        border-radius: 0;
        color: #212121;
        background-color: #FAFAFA;
        border-color: #EEEEEE;
    }

    .panel-title {
        font-size: 14px;
    }

    .panel-title > a {
        display: block;
        padding: 15px;
        text-decoration: none;
    }

    .more-less {
        float: right;
        color: #212121;
    }

    .panel-default > .panel-heading + .panel-collapse > .panel-body {
        border-top-color: #EEEEEE;
    }
</style>
<script>
function toggleIcon(e) {
    $(e.target)
        .prev('.panel-heading')
        .find(".more-less")
        .toggleClass('glyphicon-plus glyphicon-minus');
}
$('.panel-group').on('hidden.bs.collapse', toggleIcon);
$('.panel-group').on('shown.bs.collapse', toggleIcon);
</script>
</head>

<body onload="InitialProcess();">
<html:form name="receiveFromThirdPartyTransBean"
	action="/transactions/ReceiveFromThirdPartyTransCNTNEW"
	type="mms.transactions.controller.fb.ReceiveFromThirdPartyTransFB">


<div class="viewport" id="nonPrintable">
			<div class="container-fluid">

				 
		<div class="errMsg" id="errMsg"><bean:write
		name="receiveFromThirdPartyTransBean" property="strErrMsg" /></div>
	<div class="warningMsg" id="warningMsg"><bean:write
		name="receiveFromThirdPartyTransBean" property="strWarningMsg" /></div>
	<div class="normalMsg" id="normalMsg"><bean:write
		name="receiveFromThirdPartyTransBean" property="strNormalMsg"
		filter="false" /></div>

			<div class="row justify-content-center">
					<div class="col-sm-12">
						<br>
						<div class="prescriptionTile">
							<div class="row rowFlex reFlex">
								<div class="legend2" id='nonPrintableLegend2'>
									<button type="button"
										class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn"
										onclick="cancelFunc();">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle"
										onclick="document.forms[0].reset();"
										style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear"
											style="width: 23px; color: #fff;">
									</button>
									<!-- <button  type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" onclick="changeViewMode(this);">
											<i class="fas fa-eye iround"  title="View"></i>
									</button> -->
									<button type="button" id="submitId" onclick=' return validate1();'
										class="float-right btn btn-outline-success mt-1 btn-circle"
										tabindex='2'
										name="patientAdmissionModiTransBean"
										style="background-color: #5cb85c;">
										<i class="fa fa-download iround" title="Save"></i>
									</button>
								</div>
							</div>
<div class="row rowFlex reFlex">
								<div class="col-sm-5">
									<p class="subHeaders">
										<button type="button"
											class="btn btn-outline-success  btn-circle1">
											<i class="fas fa-file-alt iround" title="Cancel"></i>
										</button>
										&nbsp;Received From Third Party Details
									</p>
									
								</div>
			</div>
			
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4">Store Name</div>
				<div class="col-md-2"><label style="color:blue;"><bean:write
				name="receiveFromThirdPartyTransBean" property="strStoreName"
				filter="false" /></label></div>
				<div class="col-md-2">Item Category</div>
				<div class="col-md-2"><label style="color:blue;"><bean:write
				name="receiveFromThirdPartyTransBean" property="strItemCategoryName"
				filter="false" /></label></div>
				<div class="col-md-2">Institute</div>
				<div class="col-md-2"><label style="color:blue;"><bean:write
				name="receiveFromThirdPartyTransBean" property="strInstituteName"
				filter="false" /></label></div>
				
			</div>
			
			
			

			
			<div class="row rowFlex reFlex">
				<div class="col-md-4">
				<div id="plus" style="display: none"><i class="fas fa-plus-circle" onclick="showinfo();"></i>&nbsp;Received Item Information</div>
				<div id="minus" style="display: block"><i class="fas fa-minus-circle" onclick="hideinfo();"></i>&nbsp;Received Item Information</div>
			
			</div></div>
			<div class="row rowFlex reFlex">
			<div class="col-md-12">
			<div id="demographicInfo" style="display:none" align="center">
             <bean:write name="receiveFromThirdPartyTransBean" property="strItemHlp" filter="false" />
        	</div></div></div>
				
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4">Remarks:</div>
				<div class="col-md-2" style="color:blue;word-wrap:break-word;"><bean:write
				name="receiveFromThirdPartyTransBean" property="strRemarks"
				filter="false" /></div>
				<div class="col-md-2"><font color="red">*</font>Stock Status</div>
				<div class="col-md-2"><select name="strStockStatus"
				class="browser-default custom-select">
				<bean:write name="receiveFromThirdPartyTransBean"
					property="strStockStatusValues" filter="false" />
				</select></div>
				<div class="col-md-2"><font color="red">*</font>Group Name</div>
				<div class="col-md-2"><select name="strGroupId"
				onchange="getSubGroupListDtls(this);" class="browser-default custom-select">
				<bean:write name="receiveFromThirdPartyTransBean"
					property="strGroupCombo" filter="false" />
				</select></div>
				</div>
			
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4">Sub Group Name</div>
				<div class="col-md-2"><div id="subGroupComboDivID"><select name="strSubGroupId"
				class="browser-default custom-select" onChange="ajaxItemName('ITEMNAME');">
				<option value="0">All</option>
				</select></div></div>
				
				<div class="col-md-2"><font color="red">*</font>Generic
			Item Name</div>
			<div class="col-md-2"><div id="ItemNameId"><select name="strItemId"
				class='browser-default custom-select' onChange="ajaxItemBrandName('ITEMBRANDNAME')">
				<option value="0">Select Value</option>
			</select></div></div>
			<div class="col-md-2"><font color="red">*</font>Item
			Name</div>
			<div class="col-md-2"><div id="ItemBrandId"><select name="strItemBrandId"
				class='browser-default custom-select'>
				<%-- onchange="ajaxManufectureName('MANUFECTURENAME');">--%>
				<option value="0">Select Value</option>
			</select></div>
			</div>
			</div>
			
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4"><div id="manfNotMandatoryDivId" style="display: none;">Manufacture</div>
					<div id="manfMandatoryDivId" style="display: block;"><font color="red">*</font>Manufacture</div></div>
				<div class="col-md-2"><div id="manufDivId"><select class="browser-default custom-select"
				name="strManufactureId">
				<option value='0'>Select Value</option>
				</select></div></div>
				<div class="col-md-2"><div id="batchNoDivId">Batch No.</div><input type="hidden" name="isBatchReq" value="0"></div>
				<div class="col-md-2"><input type="text"
				name="strBatchNo" maxlength="30" class=" form-control"
				onkeypress="return validateData(event,17);" /></div>
				<div class="col-md-2"><div id="expiryDateDivId">Expiry Date</div><input type="hidden" name="isExpirtReq" value="0"></div>
				<div class="col-md-2"><input  name="strExpiryDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strExpiryDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
			</div>
			
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4">Manufacture Date</div>
				<div class="col-md-2 "><input  name="strManufactureDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strCtDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
				<div class="col-md-2"><font color="red">*</font>Received Quantity</div>
				<div class="col-md-2"><input type="text"
				name="strInHandQuantity" maxlength="8" class=" form-control"
				onkeyup="return validateQty('strInHandQuantity','strInHandQuantityUnitID');"
				onkeypress="return validateData(event,7);" value="1" /></div>
				<div class="col-md-2"><font color="red">*</font>Received Quantity Unit</div>
				<div class="col-md-2"><div id="freeItemUnit"><select name="strInHandQuantityUnitID"
				onchange="return validateQty('strInHandQuantity','strInHandQuantityUnitID');"
				class='browser-default custom-select'>
				<option value="0">Select Value</option>
				</select></div></div>
			</div>
			
			<div class="row rowFlex reFlex">
				
				<div class="col-md-2"><div id="specNotMandatoryDivId" style="display: none;">Specification</div>
					<div id="specMandatoryDivId" style="display: block;"><font color="red">*</font>Specification</div></div>
				<div class="col-md-10"><textarea rows="2" cols="40" name="strItemSpecification" class="form-control"></textarea></div>
			</div>
			<br>
			<div class="row rowFlex reFlex">
				<div class="col-md-12"><i class="fas fa-tags" style="size:4x;"></i>&nbsp;Rate Details</div>
			</div>
			
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4">Currency Code</div>
				<div class="col-md-2"><select name="strCurrencyCode"
				class="browser-default custom-select" onchange="isCurrencyMandatory(this);">
				<bean:write name="receiveFromThirdPartyTransBean"
					property="strCurrencyCodeValues" filter="false" />
				</select></div>
				<div class="col-md-2"><div id="currencyDivId">Currency Value</div>
					<input type="hidden" name="isCurrencyReq" value="0"></div>
				<div class="col-md-2"><input type="text"
				name="strCurrencyValue" maxlength="8" class="form-control"
				onkeypress="return validateData(event,7);" value="1"
				disabled="disabled" /></div>
				<div class="col-md-2"><font color="red">*</font>Rate/Unit</div>
				<div class="col-md-2"><input type="text"
				name="strRate" maxlength="14" class="form-control"
				onkeypress="return validateData(event,7);" /></div>
			</div>
			
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4"><font color="red">*</font>Unit Name</div>
				<div class="col-sm-2"><div id="UnitRateID"><select name="strUnitRateID"
				class='browser-default custom-select'>
				<option value="0">Select Value</option>
				</select></div></div>
				<div class="col-md-2"><font color="red">*</font>Issue Rate/Unit (INR)</div>
				<div class="col-md-2"><input type="text"
				name="strSalePrice" maxlength="14" class="form-control"
				onkeypress="return validateData(event,7);" /></div>
				<div class="col-md-2"><font color="red">*</font>Unit Name</div>
				<div class="col-md-2"><div id="UnitRateID1"><select name="strUnitSaleID"
				class='browser-default custom-select'>
				<option value="0">Select Value</option>
				</select></div></div>
			</div>
			
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4"><font color="red">*</font>Received Date</div>
				<div class="col-md-2"><input  name="strReceivedDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strReceivedDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
				<div class="col-md-2"><font color="red">*</font>Supplied By</div>
				<div class="col-md-2"><select name="strSuppliedBy"
				class="browser-default custom-select">
				<bean:write name="receiveFromThirdPartyTransBean"
					property="strSuppliedByValues" filter="false" />
				</select></div>
			</div>
			
			
			<div class="row rowFlex reFlex">
			
			</div>
			
			<logic:equal name="receiveFromThirdPartyTransBean"
		property="strWarrantyFlag" value="1">
			
			<div class="row rowFlex reFlex">
				<div class="col-md-4 px-4"><html:checkbox
					property="strIsWarrantyDetails" name="receiveFromThirdPartyTransBean"
					value="1"
					onclick="showOrHideDetails(this,'warrantyItemDtlsDivId');">&nbsp;Whether Warranty Details Required</html:checkbox></div>
			</div>
		
			<div id="warrantyItemDtlsDivId" style="display: none">	
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4"><font color="red">*</font>Warranty Date</div>
				<div class="col-md-2"><input  name=strWarrantyDate
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strWarrantyDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
				<div class="col-md-2"><font color="red">*</font>Manufacture</div>
				<div class="col-md-2"><div id="warrantyManufDivId"><select
					name="strWarantyManufacturer" class="browser-default custom-select">
					<option value='0'>Select Value</select></div></div>
				<div class="col-md-2"><font color="red">*</font>Warranty Upto</div>
				<div class="col-md-2"><input type="text"
					name="strWarrantyUpTo" class="form-control" maxlength="3"
					onkeypress="return validateData(event, 5);"></div>
			</div>
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4"><font color="red">*</font>Unit</div>
				<div class="col-md-2"><select
					name="strWarrantyUpToUnit" class="browser-default custom-select">
					<option value="0">Select Value</option>
					<option value="1">Day(s)</option>
					<option value="2">Month(s)</option>
					<option value="3">Year(s)</option>
				</select></div>
				<div class="col-md-2">Remarks</div>
				<div class="col-md-6"><textarea class="form-control" rows="2" cols="25"
					name="strWarrantyRemarks"></textarea></div>
				
			</div>
			
		</div>
	</logic:equal>
	
	<logic:equal name="receiveFromThirdPartyTransBean"
		property="strInstallFlag" value="1">
	<div class="row rowFlex reFlex">
				<div class="col-md-4 px-4"><html:checkbox
					property="strIsInstallDetails" name="receiveFromThirdPartyTransBean"
					value="1" onclick="showOrHideDetails(this,'installItemDtlsDivId');">&nbsp;Whether Install Details Required</html:checkbox></div>
			</div>
		
			<div id="installItemDtlsDivId" style="display: none">	
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4"><font color="red">*</font>Installation Start Date</div>
				<div class="col-md-2"><input  name="strInstallStartDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strInstallStartDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
				<div class="col-md-2"><font color="red">*</font>Installation End Date</div>
				<div class="col-md-2"><input  name="strInstallEndDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strInstallEndDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
				<div class="col-md-2"><font color="red">*</font>Installation Status</div>
				<div class="col-md-2"><select name="strInstallStatus"
					class="browser-default custom-select">
					<option value='0'>Select Value</option>
					<option value='1'>Success</option>
					<option value='2'>Failure</option>
				</select></div>
			</div>
			<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4"><font color="red">*</font>Installed By</div>
				<div class="col-md-2"><input type="text"
					class="form-control" name="strInstallBy"
					onkeypress="return validateData(event, 4);" maxlength="100"></div>
				<div class="col-md-2"><font color="red">*</font>Installer Contact No.</div>
				<div class="col-md-2"><input type="text"
					name="strInstallerContactNo"
					onkeypress="return validateData(event, 2);" class="form-control"
					maxlength="10"></div>
				<div class="col-md-2">Remarks</div>
				<div class="col-md-2"><textarea class="form-control" rows="2" cols="25"
					name="strInstallRemarks"></textarea></div>
			</div>
			
		</div>
	</logic:equal>

		<div class="row rowFlex reFlex">
			<div class="col-md-4 px-3">
			<div id="freeItemDtlsDivIdPlusId" align="left"
				style="display: block;"><i class="fas fa-plus-circle" onClick="showView('freeItemDtlsDivId');" style="cursor: pointer; "></i>&nbsp;Free Items</div>
			<div id="freeItemDtlsDivIdMinusId" style="display: none;"
				align="left"><i class="fas fa-minus-circle" onClick="hideView('freeItemDtlsDivId');" style="cursor: pointer; "></i>&nbsp;Free Items</div>
		</div>
		<div class="col-md-4">
			<div id="partItemDtlsDivIdPlusId" align="left"
				style="display: block;"><i class="fas fa-plus-circle" onClick="showView('partItemDtlsDivId');" style="cursor: pointer; "></i>&nbsp; Part Items</div>
			<div id="partItemDtlsDivIdMinusId" style="display: none;"
				align="left"><i class="fas fa-minus-circle" onClick="hideView('partItemDtlsDivId');" style="cursor: pointer; "></i>&nbsp; Part Items</div>
		</div>
		
		<div class="col-md-4">
		<div id="paramItemDtlsDivIdPlusId" align="left"
				style="display: block;"><i class="fas fa-plus-circle" onClick="showView('paramItemDtlsDivId');" style="cursor: pointer; "></i>&nbsp;Parameter Details</div>
			<div id="paramItemDtlsDivIdMinusId" style="display: none;"
				align="left"><i class="fas fa-minus-circle" onClick="hideView('paramItemDtlsDivId');" style="cursor: pointer; "></i>&nbsp;Parameter Details</div>
		</div>
		
		</div>

<!-- -------------------------------------------------------------------------------------------------- -->
	<div id="freeItemDtlsDivId" style="display: none">

	<table class="table table-striped text-uppercase" align="center" border="0" cellpadding="1px"
		cellspacing="1px" style="font-weight:bold;">
		<tr>

			<td class="" width="20%">Item Name</td>
			<td class="" width="20%">Batch No.</td>
			<td class="" width="20%">Expiry Date</td>
			<td class="" width="20%">Qty.</td>
			<td align="center">
			<button type="button"
										class="float-right btn btn-outline-info mt-1" title="Add"
										onclick="addFreeItems();" style="padding: .175rem .35rem; line-height: 0.8">
										<i class="fas fa-plus-square"></i>
									</button>
			<!-- <a href="#" 	><span class="add">Add</span></a>  -->
			</td>
		</tr>
	</table>
	<div id="id1"></div>


	</div>



	<div id="partItemDtlsDivId" style="display: none">

	<table class="table table-striped text-uppercase" align="center" border="0" cellpadding="1px"
		cellspacing="1px" style="font-weight:bold">
		<tr>

			<td class="" width="20%">Item Name</td>
			<td class="" width="20%">Batch No.</td>
			<td class="" width="20%">Manufacturer</td>
			<td class="" width="15%">Expiry Date</td>
			<td class="" width="20%">Qty.</td>
			<td class="" width="5%">&nbsp;&nbsp;</td>
			<td align="center">
			<button type="button"
										class="float-right btn btn-outline-info mt-1" title="Add"
										onclick="addPartItems();" style="padding: .175rem .35rem; line-height: 0.8">
										<i class="fas fa-plus-square"></i>
									</button>
			<!-- <a href="#" 	><span class="add">Add</span></a>  -->
			</td>
		</tr>
	</table>
	<div id="id2"></div>

	</div>



	<div id="paramItemDtlsDivId" style="display: none">

	<table class="table table-striped text-uppercase" align="center" border="0" cellpadding="1px"
		cellspacing="1px">

		<tr>
			<td colspan="4" class="" width="25%"></td>
		</tr>

		<tr>
		

			<!-- <td align="center"><img
				src="../../hisglobal/images/add_parameter.GIF"
				onclick="checkForPopup(this,document.forms[0].strItemCategoryNo.value , '1')"
				style="cursor: pointer; "></td> -->
				<td align="center">
					<button type="button"
										class="float-right btn btn-outline-info mt-1" title="Add"
										onclick="checkForPopup(this,document.forms[0].strItemCategoryNo.value , '1')" style="padding: .175rem .35rem; line-height: 0.8">
										<i class="fas fa-plus-square"></i>
									</button>
		
			<!-- <a href="#" class="button"	><span class="add">Add</span></a> -->
			</td>
		</tr>

	</table>

	</div>


<div class="row rowFlex reFlex">
	<div class="col-md-12" align="right"><font size="2" color="red">*</font>
			Mandatory Fields</div>
</div>
	

	<input type="hidden" name="strStoreId"
		value="${receiveFromThirdPartyTransBean.strStoreId}" />


	<input type="hidden" name="strItemCategoryNo"
		value="${receiveFromThirdPartyTransBean.strItemCategoryNo}" />



<input type="hidden" name="strInstituteId"
		value="${receiveFromThirdPartyTransBean.strInstituteId}" />

	<input type="hidden" name="strRemarks"
		value="${receiveFromThirdPartyTransBean.strRemarks}" />


	<input type="hidden" name="strCtDate"
		value="${receiveFromThirdPartyTransBean.strCtDate}">

	<input type="hidden" name="strDefaultCurrencyCode"
		value="${receiveFromThirdPartyTransBean.strDefaultCurrencyCode}">

<input type="hidden" name="strRegFlag" value="" />

<input type="hidden"  name="strIssueRateConfigFlg"  value="${receiveFromThirdPartyTransBean.strIssueRateConfigFlg}">

	<input type="hidden" name="hmode" />



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

<jsp:include page="itemInventory_multirow_mmstransNEW.jsp"></jsp:include>
	<tag:autoIndex></tag:autoIndex>   
	<script type="text/javascript">

$('.datepicker').each(function(){
    $(this).datepicker({ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
});
var today=new Date();
var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
var mmm=arr[today.getMonth()];
var hrs=today.getHours();
var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
$('.datepicker').val(dd);

</script>
</body>
</html>

	






