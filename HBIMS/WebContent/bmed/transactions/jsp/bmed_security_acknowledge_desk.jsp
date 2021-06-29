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
<title>Equipment Gate Pass Request Verification Desk</title>
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
                 	   document.forms[0].hmode.value = "SAVEACKNOWLEDGE";
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
	chkRecordSaved();
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

function chkRecordSaved()
{
	if(document.forms[0].strMsgString.value.length>1)
	{
		alert(document.forms[0].strMsgString.value);
		document.forms[0].hmode.value = "LIST";
		document.forms[0].submit();
	}
}
 
</script>
<!-- 
/**
 * Date of Creation : 09/06/2014
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : Odisha
 */
 -->

</head>
<body onload="hidetables()" class="background">
<html:form action="/transactions/SecurityDeskCNT"
	name="stEquipgatepassrequestDeskName"
	type="bmed.transactions.controller.fb.SecurityDeskFB"
	styleClass="formbg">

	<center>
	<div id="errMsg" class="errMsg"><bean:write name="SecurityDeskFB"
		property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="SecurityDeskFB" property="strWarning" /></div>
	<div style="display: none;" id="normalMsg" class="normalMsg"><bean:write
		name="SecurityDeskFB" property="strMsgString" /></div>
	</center>

	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable1();"
				style="cursor: pointer;" />Equipment Gate Pass Details &gt;&gt;</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" align="center" width="100%" id="table1">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">RequestDate</td>
			<td colspan="1" class="CONTROL">${SecurityDeskFB.strRequestDate}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Request
			Time</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strRequestTime}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">GatePass
			for</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strRequestFor}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Issue
			to</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strGatePassIssueTo}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Receivers
			Address</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strGatePassReceiverAdd}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Purpose</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strGatePassPurpose}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Place
			where item(s) to be Carried:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strToPlaceItemCarried}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Expected
			Date of Return of Item :</td>
			<td colspan="1" class="CONTROL">${SecurityDeskFB.strExpectedReturnDate}</td>
			<td class="CONTROL">
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Remarks:</td>
			<td colspan="3" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strGatePassRemark}</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable2();"
				style="cursor: pointer;" />Equipment Gate Pass Verification Details
			&gt;&gt;</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" width="100%" id="table2">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Verified</td>
			<td class="CONTROL">${SecurityDeskFB.strRequestVerifyStatus}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Date of
			verification</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strVeificationDate}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Remark
			If Any</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strVerificationRemark}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">verified
			By:</td>
			<td colspan="1" class="CONTROL">
			<div id="SupplierDiv" align="left">${SecurityDeskFB.strGatePassVerifier}
			</div>
			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4"><img
				src="../../hisglobal/images/Pl_Green_Sqr.png"
				title="Show/Hide Table" onClick="hidetable3();"
				style="cursor: pointer;" />Equipment Gate Pass Approval &gt;&gt;</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" width="100%" id="table3">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Approved</td>
			<td class="CONTROL">${SecurityDeskFB.strRequestApprovalStatus}</td>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color="red">*</font>Date of Approval</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strApprovalDate}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;"><font
				color="red">*</font>Order No</td>
			<td class="CONTROL">${SecurityDeskFB.strRequestApprovalOrderNo}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Approved
			By:</td>
			<td colspan="1" class="CONTROL">
			<div id="SupplierDiv" align="left">${SecurityDeskFB.strGatePassApprover}
			</div>
			</td>
		</tr>
	</table>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Acknowledge Details &gt;&gt;</td>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Gate
			Pass No:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strGatePassNo}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Checkout
			By</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${SecurityDeskFB.strCheckOutBy}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Date of
			CheckOut:</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><dateTag:date
				name="strDateOfCheckOut" id="strDateOfCheckOut" /></td>
			<td colspan="1" class="LABEL" style="text-align: right;">Remark
			(If Any)</td>
			<td colspan="1" class="CONTROL" style="text-align: left;"><input
				type="text" name="strAckCheckOutRemark" id="strAckCheckOutRemark"></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="6"></td>
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
		value="${SecurityDeskFB.strComplaintId}">
	<input type="hidden" name="strComplaintDate" id="strComplaintDate"
		value="${SecurityDeskFB.strComplaintDate}">
	<input type="hidden" name="strComplaintDescription"
		id="strComplaintDescription"
		value="${SecurityDeskFB.strComplaintDescription}">
	<input type="hidden" name="strStoreID" id="strStoreName"
		value="${SecurityDeskFB.strStoreID}">
	<input type="hidden" name="strLabID" id="strLabName"
		value="${SecurityDeskFB.strLabID}">
	<input type="hidden" name="strBlockID" id="strBlockName"
		value="${SecurityDeskFB.strBlockID}">
	<input type="hidden" name="strBuildingID" id="strBuildingName"
		value="${SecurityDeskFB.strBuildingID}">
	<input type="hidden" name="strFloorID" id="strFloorName"
		value="${SecurityDeskFB.strFloorID}">
	<input type="hidden" name="strRoomID" id="strRoomName"
		value="${SecurityDeskFB.strRoomID}">
	<input type="hidden" name="strEquipmentCategoryNo"
		id="strEquipmentCategory"
		value="${SecurityDeskFB.strEquipmentCategoryNo}">
	<input type="hidden" name="strEquipGroupID" id="strEquipGroup"
		value="${SecurityDeskFB.strEquipGroupID}">
	<input type="hidden" name="strEquipSubGroupID" id="strEquipSubGroup"
		value="${SecurityDeskFB.strEquipSubGroupID}">
	<input type="hidden" name="strItemID" id="strItemName"
		value="${SecurityDeskFB.strItemID}">
	<input type="hidden" name="strItemModelID" id="strItemModel"
		value="${SecurityDeskFB.strItemModelID}">
	<input type="hidden" name="strSupplierID" id="strSupplier"
		value="${SecurityDeskFB.strSupplierID}">
	<input type="hidden" name="strEquipmentUIDNo" id="strEquipmentUIDNo"
		value="${SecurityDeskFB.strEquipmentUIDNo}">
	<input type="hidden" name="strItemSerialNo" id="strItemSerialNo"
		value="${SecurityDeskFB.strItemSerialNo}">
	<input type="hidden" name="strRequestId" id="strRequestId"
		value="${SecurityDeskFB.strRequestId}">
	<input type="hidden" name="strRequestId" id="strRequestId"
		value="${SecurityDeskFB.strRequestId}">
	<input type="hidden" name="strGatePAssId" id="strGatePAssId"
		value="${SecurityDeskFB.strGatePAssId}">
	<input type="hidden" name="strGatePassIssueTo" id="strGatePassIssueTo"
		value="${SecurityDeskFB.strGatePassIssueTo}">
	<input type="hidden" name="strGatePassReceiverAdd"
		id="strGatePassReceiverAdd"
		value="${SecurityDeskFB.strGatePassReceiverAdd}">
	<input type="hidden" name="strIssuerID" id="strIssuerID"
		value="${SecurityDeskFB.strIssuerID}">
	<input type="hidden" name="strCheckOutByID" id="strCheckOutByID"
		value="${SecurityDeskFB.strCheckOutByID}">
	<input type="hidden" name=strGatePassNo id="strGatePassNo"
		value="${SecurityDeskFB.strGatePassNo}" />
	<input type="hidden" name=strDateOfCheckOut id="strGatePassNo"
		value="${SecurityDeskFB.strGatePassNo}" />
	<input type="hidden" name=strGatePassNo id="strGatePassNo"
		value="${SecurityDeskFB.strGatePassNo}" />

	<input type="hidden" name=strRequestDate id="strRequestDate"
		value="${SecurityDeskFB.strRequestDate}" />
	<input type="hidden" name=strGatePassRemark id="strGatePassRemark"
		value="${SecurityDeskFB.strGatePassRemark}" />
	<input type="hidden" name="strMsgString" id="strMsgString"
		value="${SecurityDeskFB.strMsgString}" />

</html:form>

</body>
</html>

