<%@page import="hisglobal.hisconfig.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!-- 
/**
 * @author T. Saratkumar and P. Chattaraj
 * Date of Creation : 26-Aug-2013
 * Date of Modification :  
 * Version : 
 * Module  : HEMMS Product 1.0
 */
 -->
<html>
<head>
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
	src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../bmed/js/BlockMst.js"></script>
<his:javascript
	src="/bmed/transactions/js/bmed_HemComplaintApp_Desk_trans.js" />
<script type="text/javascript">

 function cancelPage()
{
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
}

</script>
</head>

<body onLoad="check();" class="background">
<html:form name="EquipInwardOutwardGatePassFB"
	action="/transactions/EquipInwardOutwardGatePassCNT"
	type="bmed.transactions.controller.fb.EquipInwardOutwardGatePassFB"
	styleClass="formbg">

	<logic:equal name="EquipInwardOutwardGatePassFB"
		property="strComplaintStatus" value="2">
		<table class="TABLEWIDTH" align="center" width="100%">
			<tr class="HEADER">
				<td colspan="4">Complaint Acknowledgment View &gt;&gt;</td>
			</tr>
		</table>
	</logic:equal>
	<logic:equal name="EquipInwardOutwardGatePassFB"
		property="strComplaintStatus" value="3">
		<table class="TABLEWIDTH" align="center" width="100%">
			<tr class="HEADER">
				<td colspan="4">Complaint Approval View &gt;&gt;</td>
			</tr>
		</table>
	</logic:equal>

	<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
		cellpadding='1px' width="100%">
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Type</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strComplaintType}</td>
			<td colspan="1" class="LABEL" style="text-align: right;"></td>
			<td colspan="1" class="CONTROL" style="text-align: left;"></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Id</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strComplaintId}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Date</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strComplaintDate}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Hospital
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strStoreName}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Lab
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strLabName}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strItemName}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Equipment
			Model</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strItemModel}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">UID No</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strEquipmentUIDNo}</td>
			<td colspan="1" class="LABEL" style="text-align: right;">Item
			Serial No.</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strItemSerialNo}</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Manufacturer/Supplier
			Name</td>
			<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strManufacturerName}</td>
			<td colspan="1" class="LABEL" style="text-align: right;"></td>
			<td colspan="1" class="CONTROL" style="text-align: left;"></td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align: right;">Complaint
			Description</td>
			<td colspan="3" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strComplaintDescription}</td>
		</tr>
	</table>
	<logic:equal name="EquipInwardOutwardGatePassFB"
		property="strComplaintStatus" value="2">
		<table class="TABLEWIDTH" align="center" width="100%">
			<tr class="HEADER">
				<td colspan="4">Complaint Acknowledgment &gt;&gt;</td>
			</tr>
		</table>
		<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
			cellpadding='1px' width="100%">
			<tr>
				<td colspan="2" class="LABEL" style="text-align: right;"><font
					color="red">*</font>Acknowledgment</td>
				<td colspan="2" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strIsAcknowledge}
				</td>
			</tr>
			<tr>
				<td colspan="2" class="LABEL" style="text-align: right;"><font
					color="red">*</font>Remarks</td>
				<td colspan="2" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strRemarks}</td>
			</tr>
		</table>
	</logic:equal>
	<logic:equal name="EquipInwardOutwardGatePassFB"
		property="strComplaintStatus" value="3">
		<table class="TABLEWIDTH" align="center" width="100%">
			<tr class="HEADER">
				<td colspan="4">Complaint Approval &gt;&gt;</td>
			</tr>
		</table>
		<table class="TABLEWIDTH" align="center" border="0" cellspacing='1px'
			cellpadding='1px' width="100%">
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;">Complaint
				Nature</td>
				<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strComplaintNature}</td>
				<td colspan="1" class="LABEL" style="text-align: right;">Action
				Taken</td>
				<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strActionTaken}</td>
			</tr>
			<tr>
				<td colspan="1" class="LABEL" style="text-align: right;"><font
					color="red">*</font>Approved</td>
				<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strIsApproved}</td>

				<td colspan="1" class="LABEL" style="text-align: right;"><font
					color="red">*</font>Remarks</td>
				<td colspan="1" class="CONTROL" style="text-align: left;">${EquipInwardOutwardGatePassFB.strRemarks}</td>
			</tr>
		</table>
	</logic:equal>
	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="FOOTER">
			<td colspan="4"></td>
		</tr>
	</table>

	<!--<div>
	<div class="legends"><font size="2" color="red">*</font>
	Mandatory Fields</div>
	<div class="control_button">
	<table class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<div><a href="#" class="button" onClick="cancelPage();"
				onkeypress="if(event.keyCode==13) cancelPage();"><span
				class="back">Back</span></a></div>
			</td>
		</tr>
	</table>
	</div>
	</div>
	
	   --><table border="0" class="TABLEWIDTH" align="center">
		<tr>
			<td align="center"> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();"/></td>
		</tr>
	</table>

	<br />
	<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strErrMsg" /></div>
	<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strWarningMsg" /></div>
	<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
		name="EquipInwardOutwardGatePassFB" property="strNormalMsg" /></div>
	<input type="hidden" name="hmode" />
	<input type="hidden" name="strPath"
		value="${EquipInwardOutwardGatePassFB.strPath}">
	<input type="hidden" name="strChk"
		value="${EquipInwardOutwardGatePassFB.strChk}">
	<input type="hidden" name="strDeptId"
		value="${EquipInwardOutwardGatePassFB.strDeptId}">
	<input type="hidden" name="strComplaintStatus"
		value="${EquipInwardOutwardGatePassFB.strComplaintStatus}">
	<input type="hidden" name="strStatus">
	<html:hidden name="EquipInwardOutwardGatePassFB"
		property="strComplaintId" />
	<cmbPers:cmbPers />
</html:form>
</body>
</html>