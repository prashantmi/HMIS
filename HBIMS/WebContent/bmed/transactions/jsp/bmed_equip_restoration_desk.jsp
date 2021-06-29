<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<title>Equipment Restoration Desk</title>
<his:css src="../../../hisglobal/css/Color.css" />
<his:css src="../../../hisglobal/css/master.css" />
<his:css src="../../hisglobal/css/hisStyle.css" />
<his:css src="../../hisglobal/css/hisStyleExt.css" />
<his:css src="../../hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />

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

<script language="JavaScript"
	src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>

<script type="text/javascript">
function validate()
{   
	var retVal = true;
          if(retVal)
          {
                 	   document.forms[0].hmode.value = "SAVERESTORATION";
                       document.forms[0].submit();
          }
          else
          {
             return false;
          }
}

function hidetables()
{
	document.getElementById("table1").style.display = "none";
	document.getElementById("table2").style.display = "none";
	document.getElementById("table3").style.display = "none";
	document.getElementById("table4").style.display = "none";
	document.getElementById("table5").style.display = "none";
	document.getElementById("table6").style.display = "none";
}


function hidetable1()
{
	var table1 = document.getElementById("table1");
	if(table1.style.display == "table")
	table1.style.display="none";
	else
	table1.style.display="table";
}

function hidetable2()
{
	var table2 = document.getElementById("table2");
	if(table2.style.display == "table")
	table2.style.display="none";
	else
	table2.style.display="table";
}

function hidetable3()
{
	var table3 = document.getElementById("table3");
	if(table3.style.display == "table")
	table3.style.display="none";
	else
	table3.style.display="table";
}

function hidetable4()
{
	var table4 = document.getElementById("table4");
	if(table4.style.display == "table")
	table4.style.display="none";
	else
	table4.style.display="table";
}

function hidetable5()
{
	var table5 = document.getElementById("table5");
	if(table5.style.display == "table")
	table5.style.display="none";
	else
	table5.style.display="table";
}

function hidetable6()
{
	var table6 = document.getElementById("table6");
	if(table6.style.display == "table")
	table6.style.display="none";
	else
	table6.style.display="table";
}

</script>
<!-- 
/**
 * Date of Creation : 06/06/2014
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : Odisha
 */
 -->

</head>
<body onload="hidetables()" class="background">
<html:form action="/transactions/EquipmentIssueAndRestorationDeskCNT"
	name="stEquipgatepassrequestDeskName"
	type="bmed.transactions.controller.fb.EquipmentIssueAndRestorationDeskFB"
	styleClass="formbg">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="EquipmentIssueAndRestorationDeskFB" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="EquipmentIssueAndRestorationDeskFB" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="EquipmentIssueAndRestorationDeskFB" property="strMsgString" /></div>
	</center>

	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable1();"
				style="cursor: pointer;" />Request/Complaint Detail &gt;&gt;</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%" id="table1">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Id</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strComplaintId}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Date</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strComplaintDate}
			</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Description</td>
			<td colspan="3" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strComplaintDescription}</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable2();"
				style="cursor: pointer;" />Equipment Installation Details &gt;&gt;</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%" id="table2">

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Hospital
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strStoreName}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Lab
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strLabName}</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Block
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strBlockName}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Building
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strBuildingName}
			</td>

		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Floor</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strFloorName}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Room</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strRoomName}
			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable3();"
				style="cursor: pointer;" />Equipment Inventory Details &gt;&gt;</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%" id="table3">

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Category</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strEquipmentCategory}</td>
			<td colspan="2" class="CONTROL" style="text-align: left;"></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Group</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strEquipGroup}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			SubGroup</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strEquipSubGroup}</td>
		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strItemName}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Model Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strItemModel}
			</td>

		</tr>

		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Supplier</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strSupplier}
			</td>
			<td colspan="1" class="LABEL" style="text-align: right;">UID No.</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strEquipmentUIDNo}
			</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Serial
			No.</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strItemSerialNo}
			</td>
			<td colspan="2" class="CONTROL" style="text-align: left;"></td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable4();"
				style="cursor: pointer;" />Equipment Gate Pass Details &gt;&gt;</td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%" id="table4">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">RequestDate</td>
			<td class="CONTROL">${EquipmentIssueAndRestorationDeskFB.strRequestDate}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Request
			Time</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strRequestTime}</td>

		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">GatePass
			for</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strRequestFor}</td>

			<td colspan="1" class="LABEL" style="text-align: right;">Issue
			to</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strGatePassIssueTo}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Receivers
			Address</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strGatePassReceiverAdd}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Purpose</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strGatePassPurpose}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Place
			where item(s) to be Carried:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strToPlaceItemCarried}</td>

			<td colspan="1" class="LABEL" style="text-align: right;">Expected
			Date of Return of Item :</td>
			<td colspan="1" class="CONTROL">${EquipmentIssueAndRestorationDeskFB.strExpectedReturnDate}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Remarks:</td>
			<td colspan="3" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strGatePassRemark}</td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">

		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable5();"
				style="cursor: pointer;" />Equipment Gate Pass Verification Details
			&gt;&gt;</td>
		</tr>
	</table>
	
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%" id="table5">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Verified</td>
			<td class="CONTROL">${EquipmentIssueAndRestorationDeskFB.strRequestVerifyStatus}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Date of
			verification</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strVeificationDate}</td>

		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Remark
			If Any</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strVerificationRemark}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">verified
			By:</td>
			<td colspan="1" class="CONTROL">
			<div id="SupplierDiv" align="left">${EquipmentIssueAndRestorationDeskFB.strGatePassVerifier}
			</div>
			</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable6();"
				style="cursor: pointer;" />Equipment Gate Pass Approval &gt;&gt;</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%" id="table6">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Approved</td>
			<td class="CONTROL">${EquipmentIssueAndRestorationDeskFB.strRequestApprovalStatus}</td>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color="red">*</font>Date of Approval</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strApprovalDate}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color="red">*</font>Order No</td>
			<td class="CONTROL">${EquipmentIssueAndRestorationDeskFB.strRequestApprovalOrderNo}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Approved
			By:</td>
			<td colspan="1" class="CONTROL">
			<div id="SupplierDiv" align="left">${EquipmentIssueAndRestorationDeskFB.strGatePassApprover}
			</div>
			</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable6();"
				style="cursor: pointer;" />Equipment Issue Details &gt;&gt;</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Status:</td>
			<td colspan="3" class="CONTROL" style="text-align: left;">Issued</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Gate
			Pass No:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strIssueGatePassNo}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Date of
			Issue:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strDateOfIssue}</td>
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Remark
			(If Any)</td>
			<td colspan="2" class="CONTROL" style="text-align: left;">${EquipmentIssueAndRestorationDeskFB.strEquipIssueRemark}</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr class="HEADER">
			<td colspan="4">Equipment Restoration &gt;&gt;</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Restored By:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" name="strEquipRestoredBy" id="strEquipRestoredBy" /></td>

			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Returned From:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" name="strEquipRestoredFrom" id="strEquipRestoredFrom" /></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Status:</td>
			<td colspan="3" class="LABEL" style="text-align: left;">RESTORED</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Gate
			Pass No:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" name="strRestoreGatePassNo" id="strRestoreGatePassNo" /></td>
			<td colspan="1" class="LABEL" style="text-align: right;">Date of
			Receipt:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><dateTag:date
				name="strDateOfRestoration" id="strDateOfRestoration" /></td>
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Remark
			(If Any)</td>
			<td colspan="2" class="CONTROL" style="text-align: left;"><input
				type="text" name="strRestorationRemark" id="strRestorationRemark"></td>
		</tr>
		<tr>
			<td colspan="2" class="LABEL" style="text-align: right;">Equipment
			Received By:</td>
			<td colspan="2" class="CONTROL" style="text-align: left;"><select
				name="strEquipReceivedBy" id="strEquipReceivedBy">
				<bean:write name="EquipmentIssueAndRestorationDeskFB"
					property="strReceivedByCmb" filter="false" />
			</select></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="4"></td>
		</tr>
	</table>

	<div>
		<div class="legends"><font size="2" color="red">*</font>
		Mandatory Fields</div>
			<div class="control_button">
			<table class="TABLEWIDTH" align="center">
				<tr>
					<td align="center">
					<div><a href="#" class="button" onClick="validate();"><span
						class="save">Save</span></a> <a href="#" class="button"
						onClick="cancel('LIST');"><span class="back">Back</span></a></div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<html:hidden property="hmode" styleId="hmode" />

	<input type="hidden" name="strComplaintId" id="strComplaintId"
		value="${EquipmentIssueAndRestorationDeskFB.strComplaintId}">
	<input type="hidden" name="strComplaintDate" id="strComplaintDate"
		value="${EquipmentIssueAndRestorationDeskFB.strComplaintDate}">
	<input type="hidden" name="strComplaintDescription"
		id="strComplaintDescription"
		value="${EquipmentIssueAndRestorationDeskFB.strComplaintDescription}">
	<input type="hidden" name="strStoreID" id="strStoreName"
		value="${EquipmentIssueAndRestorationDeskFB.strStoreID}">
	<input type="hidden" name="strLabID" id="strLabName"
		value="${EquipmentIssueAndRestorationDeskFB.strLabID}">
	<input type="hidden" name="strBlockID" id="strBlockName"
		value="${EquipmentIssueAndRestorationDeskFB.strBlockID}">
	<input type="hidden" name="strBuildingID" id="strBuildingName"
		value="${EquipmentIssueAndRestorationDeskFB.strBuildingID}">
	<input type="hidden" name="strFloorID" id="strFloorName"
		value="${EquipmentIssueAndRestorationDeskFB.strFloorID}">
	<input type="hidden" name="strRoomID" id="strRoomName"
		value="${EquipmentIssueAndRestorationDeskFB.strRoomID}">
	<input type="hidden" name="strEquipmentCategoryNo"
		id="strEquipmentCategory"
		value="${EquipmentIssueAndRestorationDeskFB.strEquipmentCategoryNo}">
	<input type="hidden" name="strEquipGroupID" id="strEquipGroup"
		value="${EquipmentIssueAndRestorationDeskFB.strEquipGroupID}">
	<input type="hidden" name="strEquipSubGroupID" id="strEquipSubGroup"
		value="${EquipmentIssueAndRestorationDeskFB.strEquipSubGroupID}">
	<input type="hidden" name="strItemID" id="strItemName"
		value="${EquipmentIssueAndRestorationDeskFB.strItemID}">
	<input type="hidden" name="strItemModelID" id="strItemModel"
		value="${EquipmentIssueAndRestorationDeskFB.strItemModelID}">
	<input type="hidden" name="strSupplierID" id="strSupplier"
		value="${EquipmentIssueAndRestorationDeskFB.strSupplierID}">
	<input type="hidden" name="strEquipmentUIDNo" id="strEquipmentUIDNo"
		value="${EquipmentIssueAndRestorationDeskFB.strEquipmentUIDNo}">
	<input type="hidden" name="strItemSerialNo" id="strItemSerialNo"
		value="${EquipmentIssueAndRestorationDeskFB.strItemSerialNo}">
	<input type="hidden" name="strRequestId" id="strRequestId"
		value="${EquipmentIssueAndRestorationDeskFB.strRequestId}">
	<input type="hidden" name="strRequestId" id="strRequestId"
		value="${EquipmentIssueAndRestorationDeskFB.strRequestId}">
	<input type="hidden" name="strGatePAssId" id="strGatePAssId"
		value="${EquipmentIssueAndRestorationDeskFB.strGatePAssId}">
	<input type="hidden" name="strGatePassIssueTo" id="strGatePassIssueTo"
		value="${EquipmentIssueAndRestorationDeskFB.strGatePassIssueTo}">
	<input type="hidden" name="strGatePassReceiverAdd"
		id="strGatePassReceiverAdd"
		value="${EquipmentIssueAndRestorationDeskFB.strGatePassReceiverAdd}">
	<input type="hidden" name="strIssuerID" id="strIssuerID"
		value="${EquipmentIssueAndRestorationDeskFB.strIssuerID}">


</html:form>

</body>
</html>

