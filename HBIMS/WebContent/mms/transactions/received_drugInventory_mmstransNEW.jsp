<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>



<html>
<head>
<<meta charset=UTF-8">
<title>Drug Inventory</title>


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
	src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>
	<!-- end -->


<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">


<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">

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
										onclick="cancelViewPage();">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle"
										onclick="pageResetMethod();"
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
										name=""
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
							
	<%-- 	<div class="row rowFlex reFlex">
			<div class="col-md-4">
				<div id="plus" style="display: none"><i class="fas fa-plus-circle" onclick="showinfo();"></i>&nbsp;Received Drug Information</div>
				<div id="minus" style="display: block"><i class="fas fa-minus-circle" onclick="hideinfo();"></i>&nbsp;Received Drug Information</div>	
			</div>
		</div>

	<div class="row rowFlex reFlex">
			<div class="col-md-12">
			<div id="demographicInfo" style="display:none" align="center">
             <bean:write name="receiveFromThirdPartyTransBean" property="strItemHlp" filter="false" />
        	</div></div></div>
 --%>	  
         
       <div class="row rowFlex reFlex">
       <div class="col-md-2 px-4">Remarks:</div>
				<div class="col-md-2" style="color:blue;word-wrap:break-word;"><bean:write
				name="receiveFromThirdPartyTransBean" property="strRemarks"
				filter="false" /></div>
				
				<div class="col-md-2"><font color='red'>*</font>Group Name</div>
				<div class="col-md-2"><select name="strGroupId" onchange="getSubGroupListDtls(this);" class="browser-default custom-select">
					<bean:write name="receiveFromThirdPartyTransBean" property="strGroupCombo" filter="false" />
				</select></div>
				<div class="col-md-2">Sub Group Name</div>
				<div class="col-md-2"><select name="strSubGroupId"
				class="browser-default custom-select" onChange="ajaxItemName('ITEMNAME');">
				<option value="0">All</option>
			</select><!-- <div id="subGroupComboDivID"></div> --></div>
				
				</div>
		
<div class="row rowFlex reFlex">
       <div class="col-md-2 px-4"><font color="red">*</font>Generic Drug Name</div>
				<div class="col-md-2"><div id="ItemNameId"><select name="strItemId"
				class='browser-default custom-select' onChange="ajaxItemBrandName('ITEMBRANDNAME')">
				<option value="0">Select Value</option>
					</select></div></div>
				
				<div class="col-md-2"><font color="red">*</font>Drug Name</div>
				<div class="col-md-2"><div id="ItemBrandId">	
				<select name="strItemBrandId" class='browser-default custom-select'>
					<option value="0">Select Value</option>
				</select>
			</div></div>
				<div class="col-md-2"><font color="red">*</font>Stock Status</div>
				<div class="col-md-2"><div id="stockStatusComboDivId">
		             <select name="strStockStatus" class="browser-default custom-select">
		           		<bean:write name="receiveFromThirdPartyTransBean" property="strStockStatusValues" filter="false"/>
		             </select>
		         </div></div>
				
				</div>


<div class="row rowFlex reFlex">
       <div class="col-md-2 px-4"><div id="manfNotMandatoryDivId" style="display: none;">Manufacture</div>
       <div id="manfMandatoryDivId" style="display: block;"><font color="red">*</font>Manufacture</div>
				</div>
				<div class="col-md-2"><div id="manufDivId"><select class="browser-default custom-select"
				name="strManufactureId">
				<option value='0'>Select Value</option>
			</select></div></div>
				
				<div class="col-md-2"><div id="batchNoDivId"><font color='red'>*</font>Batch No.</div>
			<input type="hidden" name="isBatchReq" value="1"></div>
				<div class="col-md-2"><input type="text"
				name="strBatchNo" maxlength="30" class="form-control"
				onkeypress="return validateData(event,17);" /></div>
				<div class="col-md-2"><div id="expiryDateDivId"><font color='red'>*</font>Expiry Date</div>
			<input type="hidden" name="isExpirtReq" value="1"></div>
				<div class="col-md-2"><input  name="strExpiryDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strExpiryDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
				
				</div>

			


<div class="row rowFlex reFlex">
       <div class="col-md-2 px-4">Manufacture Date</div>
				
				<div class="col-md-2"><input  name="strManufactureDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strCtDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
				
				<div class="col-md-2"><font color="red">*</font>Received Quantity</div>
				<div class="col-md-2"><input type="text"
				name="strInHandQuantity" maxlength="8" class="form-control"
				onkeypress="return validateData(event,5);" value="1" />
				</div>
				<div class="col-md-2"><font color="red">*</font>Received Quantity Unit</div>
				<div class="col-md-2"><div id="freeItemUnit"><select name="strInHandQuantityUnitID"
				class='browser-default custom-select'>
				<option value="0">Select Value</option>
			</select></div></div>
				
				</div>

<div class="row rowFlex reFlex">
       <div class="col-md-2 px-4">Rack No</div>
				
				<div class="col-md-2"><input type="text"
				name="strRackNumber" maxlength="8" class="form-control"
				onkeypress="return validateData(event,3);" /></div>
				
				<div class="col-md-2"><div id="specNotMandatoryDivId" style="display: none;">Specification</div>
			<div id="specMandatoryDivId" style="display: block;"><font color="red">*</font>Specification</div></div>
				<div class="col-md-6"><textarea class="form-control" rows="2" cols="40" name="strItemSpecification"></textarea>
				</div>
				
				
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
				name="strCurrencyValue" maxlength="8" class="form-control" value="1"
				disabled="disabled" onkeypress="return validateData(event,7);" />
				</div>
		
			</div>
		
		
<div class="row rowFlex reFlex">
		 <div class="col-md-2 px-4"><font color="red">*</font>Rate/Unit</div>
				
				<div class="col-md-2"><input type="text"
				name="strRate" maxlength="14" class="form-control"
				onkeypress="return(numericWithTwoDecimalPlaces(this,',','.',event));" onBlur="calcIssueRate();" /></div>
				 <div class="col-md-2"><font color="red">*</font>Unit Name</div>
				
				<div class="col-md-2"><div id="UnitRateID"><select name="strUnitRateID" disabled="disabled"
				class='browser-default custom-select'>
				<option value="0">Select Value</option>
			</select></div></div>
				<div class="col-md-2"><font color="red">*</font>Received Date</div>
				<div class="col-md-2"><input  name="strReceivedDate"
											class="form-control datepicker"
											value="${receiveFromThirdPartyTransBean.strReceivedDate}"
											style="color: rgba(113, 111, 111, 0.87);">
				</div>
				
			</div>			
		
		
<div class="row rowFlex reFlex">
				<div class="col-md-2 px-4"><font color="red">*</font>Issue Rate</div>
				<div class="col-md-2"><input type="text"
				name="strSalePrice" maxlength="14" class="form-control"
				onkeypress="return validateData(event,7);" />
				</div>
				 <div class="col-md-2"><font color="red">*</font>Unit Name</div>
				
				<div class="col-md-2"><div id="UnitRateID1"><select name="strUnitSaleID" disabled="disabled"
				class='browser-default custom-select'>
				<option value="0">Select Value</option>
			</select></div></div> 
				 <div class="col-md-2"><font color="red">*</font>Supplied By</div>
				
				<div class="col-md-2"><select name="strSuppliedBy"
				class="browser-default custom-select">
				<bean:write name="receiveFromThirdPartyTransBean"
					property="strSuppliedByValues" filter="false" />
			</select></div> 
			
						</div>
		

		<!-- 	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px" style="display:none;">

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
	</table> -->


<!-- 
	<div id="freeItemDtlsDivId" style="display: none">

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td class="multiLabel" width="20%">Drug Name</td>
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

		<tr align="center">
			<td align="center"><img style="cursor: pointer; " src="../../hisglobal/images/btn-add.png" onClick="addFreeItems();" />
			<a href="#" class="button"  onclick='addFreeItems();'><span class="add">Add</span></a>
			</td>
		</tr>

	</table>

	</div>
 -->

	<div class="row">
		<div class="col-md-12" align="right"><font size="2" color="red">*</font>Mandatory Fields</div>
	</div>
	<!-- 
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">

		<tr class="FOOTER">
			<td colspan="4" width="25%"></td>
		</tr>
	</table>
 -->	
<!-- 	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>

			<td align="center"><img style="cursor: pointer; "
				src="../../hisglobal/images/btn-sv.png" onClick="validate1();" />
			<img style="cursor: pointer; "
				src="../../hisglobal/images/btn-clr.png"
				onClick="pageResetMethod();" /> <img
				style="cursor: pointer; "
				src="../../hisglobal/images/btn-ccl.png"
				 onClick="cancelViewPage();" /></td>
		</tr>



	</table>
	 -->
	 
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

<input type="hidden"  name="strConfigIssueRate"  value="${receiveFromThirdPartyTransBean.strConfigIssueRate}">
<input type="hidden"  name="strIssueRateConfigFlg"  value="${receiveFromThirdPartyTransBean.strIssueRateConfigFlg}">
<input type="hidden"  name="strReceivedFromThirdPartyName"  value="${receiveFromThirdPartyTransBean.strInstituteName}">


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

</div>
</div>
</div>
</div>
</div>

	<cmbPers:cmbPers />
</html:form>

<jsp:include page="drugInventory_multirow_mmstrans.jsp"></jsp:include>
	<tag:autoIndex></tag:autoIndex>   
</body>
</html>








