<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<title>Equipment Identification for Condemnation</title>
<script type="text/javascript" src="../../bmed/js/jquery.js"></script>
<link href="../../hisglobal/css/emms.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/control.css" rel="stylesheet"
	type="text/css">
<link href="../../bmed/css/select2.css" rel="stylesheet"
	type="text/css">
<his:css src="../../../hisglobal/css/Color.css" />
<his:css src="../../hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">

<link href="../../hisglobal/css/popup.css" rel="stylesheet"
	type="text/css">
<script language="JavaScript"
	src="../../bmed/js/select2.js"></script>
<script language="JavaScript"
	src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<!-- <script 

language="Javascript"src="../../bmed/js/complaintHierarchyMst.js"></script

> -->
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript"
	src="../../bmed/transactions/js/bmed_equip_condemnation_auction_desk.js"></script>

<!-- 
/**
 * BY shefali
 * Date of Creation : 07/11/2013
 * Date of Modification :  04/03/2014 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : Rajasthan
 */
 -->
<script type="text/javascript">
<!--
	function validate() {

		var hisValidator = new HISValidator("stEquipCondemnAndAuctionDeskName");
		//      var hisValidator = new HISValidator("stEquipgatepassrequestDeskName");
		//                  hisValidator.addValidation("strRequestDate","date","Please Enter Request Date");
		//            hisValidator.addValidation("strRequestTime","req","Please Enter Request Time");

		//          hisValidator.addValidation("strGatePassRequestFor","req","Please Enter Request for");
		//        hisValidator.addValidation("strGatePassIssueTo","req","Please Enter Issue to Name");
		//      hisValidator.addValidation("strGatePassReceiverAdd","req","Please Enter Receiver Address ");
		//    hisValidator.addValidation("strGatePassPurpose","req","Please Enter Purpose");
		//  hisValidator.addValidation("strToPlaceItemCarried","req","Please Enter place where Item is being carried");
		//
		//            hisValidator.addValidation("strGatePassRequester","dontselect=0","Please Select requester Name");

		//	  var retVal = hisValidator.validate(); 
		var retVal = true;
		if (retVal) {
			document.forms[0].hmode.value = "SAVE";
			document.forms[0].submit();
		} else {
			return false;
		}
	}
	-->
	$(document).ready(function() { $("#strIndentifiedByID").select2(); });
</script>


</head>
<body onload="chkRecordSaved()" class="background">
	<html:form
		action="/transactions/EquipmentAuctionAndCondemnationDeskCNT"
		styleClass="formbg" name="stEquipCondemnAndAuctionDeskName"
		type="bmed.transactions.controller.fb.EquipmentAuctionAndCondemnationDeskFB">

		<center>
			<div id="errMsg" class="errMsg">
				<bean:write name="EquipmentAuctionAndCondemnationDeskFB"
					property="strErr" />
			</div>
			<div id="warningMsg" class="warningMsg">
				<bean:write name="EquipmentAuctionAndCondemnationDeskFB"
					property="strWarning" />
			</div>
			<div id="normalMsg" class="normalMsg" style="display: none;">
				<bean:write name="EquipmentAuctionAndCondemnationDeskFB"
					property="strMsgString" />
			</div>
		</center>
		<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Equipment Identification for Condemnation</td>
		</tr>
	</table>
			
	
		<div class="line">
	<table class="TABLEWIDTH" align="center"
		border="0" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="4">Equipment Detail</td>
		</tr>
	</table>
	</div>
		<!-- <table class="TABLEWIDTH" align="center" width="100%">
			<tr>
				<td colspan="7">
					<div style="width: 90%; border-color: aqua; table-layout: auto;">
						<span style="text-align: center;">Equipment Name</span> <span
							style="text-align: center;">Equipment Model</span> <span
							style="text-align: center;">Group / Sub-Group</span> <span
							style="text-align: center;">Serial No.</span> <span
							style="text-align: center;">UIN</span> <span
							style="text-align: center;">Purchase Date</span> <span
							style="text-align: center;">Status</span>
					</div>
				</td>
			</tr>
		</table> -->
		<table class="TABLEWIDTH" align="center" width="100%">
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Hospital
					Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strHospital}
				</td>
				<td colspan="1" class="LABEL" style="text-align: right;">Lab
					Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strLabName}
				</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Equipment
					Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipName}
				</td>
				<td colspan="" class="LABEL" style="text-align: right;">Equipment
					Model</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Group
					Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipGroup}
				</td>
				<td colspan="1" class="LABEL" style="text-align: right;">Sub
					Group Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strEquipSubGroup}</td>
			</tr>
			</table>
			<div class="line">
	<table class="TABLEWIDTH"  align="center"
		border="0" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="4">Inventory Detail</td>
		</tr>
	</table>
	</div>
<table class="TABLEWIDTH" align="center" width="100%">

			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Serial
					No</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strBatchNo}
				</td>
				<td colspan="1" class="LABEL" style="text-align: right;">UIN</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strUidNo}</td>
			</tr>

			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Manufacturer
					Name</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strSupplierName}
				</td>

				<td colspan="1" class="LABEL" style="text-align: right;">Status</td>
				<td colspan="1" class="CONTROL">${EquipmentAuctionAndCondemnationDeskFB.strStatus}
				</td>
			</tr>
			</table>
				<div class="line">
	<table class="TABLEWIDTH" align="center"
		border="0" cellpadding="1px" cellspacing="1px">
		<tr>
			<td colspan="4">Identify Equipment for Condemnation</td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" align="center"
		border="0" cellpadding="1px" cellspacing="1px">
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Date</td>
				<td colspan="1" class="CONTROL"><dateTag:date
						name="strIndentifyEquipDate"></dateTag:date></td>
				<td colspan="1" class="LABEL" style="text-align: right;">Identified
					By</td>
				<td colspan="1" class="CONTROL"><select
					name="strIndentifiedByID" id="strIndentifiedByID">
						<bean:write name="EquipmentAuctionAndCondemnationDeskFB"
							property="strIndentifiedByIDCmb" filter="false" />
				</select></td>
			</tr>
			<tr>
				<td colspan="2" class="LABEL" style="text-align: right;">Remarks/Reason
					for Condemnation</td>
				<td colspan="2" class="CONTROL">
				<input type="textarea"
					name="strIdentificationRemarks"></td>
			</tr>
		</table>
		<table class="TABLEWIDTH" align="center">

		<tr class="FOOTER">
			<td></td>
		</tr>
	</table>
		<div>
			<div class="legends">
				<font size="2" color="red">*</font> Mandatory Fields
			</div>
			<div class="control_button">
				<table class="TABLEWIDTH" align="center">
					<tr>
						<td align="center">
							<div>
								<a href="#" class="button" onClick="return validate();"><span
									class="save">Save</span></a> <a href="#" class="button"
									onClick="cancel('LIST');"><span class="back">Back</span></a>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<html:hidden property="hmode" styleId="hmode" />
		<input type="hidden" name="strEquipModel" id="strEquipModel"
			value="${EquipmentAuctionAndCondemnationDeskFB.strEquipModel}">
		<input type="hidden" name="strEquipGroup" id="strEquipGroup"
			value="${EquipmentAuctionAndCondemnationDeskFB.strEquipGroup}">
		<input type="hidden" name="strEquipSubGroup" id="strEquipSubGroup"
			value="${EquipmentAuctionAndCondemnationDeskFB.strEquipSubGroup}">
		<input type="hidden" name="strBatchNo" id="strBatchNo"
			value="${EquipmentAuctionAndCondemnationDeskFB.strBatchNo}">
			<input type="hidden" name="strEquipmentSerialNo" id="strEquipmentSerialNo"
			value="${EquipmentAuctionAndCondemnationDeskFB.strEquipmentSerialNo}">
		<input type="hidden" name="strUidNo" id="strUidNo"
			value="${EquipmentAuctionAndCondemnationDeskFB.strUidNo}">
		
		<input type="hidden" name="strItemId" id="strItemId"
			value="${EquipmentAuctionAndCondemnationDeskFB.strItemId}">

		<input type="hidden" name="strItemBrandId" id="strItemBrandId"
			value="${EquipmentAuctionAndCondemnationDeskFB.strItemBrandId }">

		<input type="hidden" name="strItemGroupId" id="strItemGroupId"
			value="${EquipmentAuctionAndCondemnationDeskFB.strItemGroupId }">

		<input type="hidden" name="strItemSubGroupId" id="strItemSubGroupId"
			value="${EquipmentAuctionAndCondemnationDeskFB.strItemSubGroupId}">

		<input type="hidden" name="strsupplierId" id="strsupplierId"
			value="${EquipmentAuctionAndCondemnationDeskFB.strsupplierId}">
		<input type="hidden" name="strHospitalId" id="strHospitalId"
			value="${EquipmentAuctionAndCondemnationDeskFB.strHospitalId}">
		<input type="hidden" name="strLabID" id="strLabID"
			value="${EquipmentAuctionAndCondemnationDeskFB.strLabID}">
		<input type="hidden" name="strMsgString" id="strMsgString"
			value="${EquipmentAuctionAndCondemnationDeskFB.strMsgString}">


		<cmbPers:cmbPers />

	</html:form>
</body>
</html>

