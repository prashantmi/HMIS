<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<%--
/**
 * Developer Name : Saratkumar Thokchom
 * Process Name : Service Action Desk
 * Date : 20-Nov-2013
 * Modify By/Date : 15-May-2014 by Anshuman Singh
 */ 
 --%>

<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
 <%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
<title>Complaint Service Action Detail Desk</title>
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

<his:javascript src="/hisglobal/masterutil/js/master.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/tab.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/bmed/js/maintenance_warranty_contract.js" />
<his:javascript src="/hisglobal/js/multirow.js" />
<his:javascript src="/bmed/transactions/js/bmed_HemDesk_trans.js" />
<style>
.TEXT_FIELD_NEW {
	color: #000000;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-style: normal;
	font-weight: normal;
	height: 16px;
	width: 60px;
}

.CONTROL_TD_NEW {
	background-color: #F1ECE2;
	color: #333333;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: normal;
	height: 16px;
	text-align: center;
}
</style>

</head>

<body marginheight="0" marginwidth="0" onload="chkRecordSaved()"
	class="background">
<html:form name="hemDeskFB" action="/transactions/HemDeskACTION"
	type="bmed.transactions.controller.fb.HemDeskFB" styleClass="formbg">



	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Complaint Action Taken Details&gt;&gt; Add</td>
		</tr>
	</table>

	<div id="strErrMsg" align="center" class="errMsg"><bean:write
		name="hemDeskFB" property="strErrMsg" /></div>
	<div id="strWarningMsg" align="center" class="warningMsg"><bean:write
		name="hemDeskFB" property="strWarningMsg" /></div>
	<div style="display: none;" id="normalMsg" align="center"
		class="normalMsg"><bean:write name="hemDeskFB"
		property="strNormalMsg" /></div>


	<table class="TABLEWIDTH" style="display: none;">
		<tr>
			<td colspan="4" class="LABEL"><html:radio name="hemDeskFB"
				property="strItemOrNonItemMode" value="1"
				onclick="changeViewMode();" /> Item <!-- html:radio name="hemDeskFB" property="strItemOrNonItemMode" value="0" onclick="changeViewMode();" / -->
			<!-- Non-Item --></td>
		</tr>
	</table>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan="4">Request/Complaint Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr style="display: none;">
			<td width="25%" class="LABEL">Complaint Type</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintType}</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Complaint Id</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintId}</td>
			<td width="25%" class="LABEL">Complaint Date</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintDate}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Hospital Name</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strStoreName}</td>
			<td width="25%" class="LABEL">Lab Name</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strLabName}</td>
		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Engineering Item Type</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEnggItemType}</td>
			<td width="25%" class="LABEL">Engineering Item Sub Type</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEnggItemSubType}</td>
		</tr>
		<tr>
			<td class="LABEL">Equipment Name</td>
			<td class="CONTROL">${hemDeskFB.strItemName}</td>
			<td class="LABEL">Equipment Model</td>
			<!--<td class="CONTROL">${hemDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
			<td class="CONTROL">${hemDeskFB.strItemModel}</td>
		</tr>
		<tr>
			<td class="LABEL">UIN</td>
			<td class="CONTROL">${hemDeskFB.strEquipmentUIDNo}</td>
			<td width="25%" class="LABEL">Equipment Serial No.</td>
			<td class="CONTROL">${hemDeskFB.strItemBatchNo}</td>
		</tr>
		<tr>
			<td class="LABEL">Manufacturer/Supplier Name</td>
			<td class="CONTROL">${hemDeskFB.strManufacturerName}</td>
			<td class="LABEL">Complaint Description</td>
			<td class="CONTROL" rowspan="3">${hemDeskFB.strComplaintDescription}</td>
		</tr>
	</table>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('warrantyDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Guarantee Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="warrantyDetailsTable"
		style="display: none;">
		<bean:write name="hemDeskFB" property="strWarrantyDetailsTable"
			filter="false" />
	</table>

	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('maintenanceContractDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Maintenance Contract
			Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="maintenanceContractDetailsTable"
		style="display: none;">
		<bean:write name="hemDeskFB"
			property="strMaintenanceContractDetailsTable" filter="false" />
	</table>

	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('acknowledgeDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Complaint
			Acknowledgment and Approval Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="acknowledgeDetailsTable"
		style="display: none;">
		<tr>
			<td width="25%" class="LABEL">Acknowledgment</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIsAcknowledge}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strRemarks}</td>

		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Complaint Nature</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintNature}</td>
			<td width="25%" class="LABEL">Action Taken</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strActionTaken}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Complaint Nature</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintNature}</td>
			<td width="25%" class="LABEL">Approved</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIsApproved}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL" colspan="3">${hemDeskFB.strRemarks}</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" style="display: none;">
		<tr>
			<td width="25%" class="LABEL">Acknowledgment</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIsHEMAcknowledge}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strAckRemarks}</td>
		</tr>

	</table>


	<%-- ************** Schedule Details Start ***************** --%>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('scheduleDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Complaint Schedule
			Details</td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" id="scheduleDetailsTable"
		style="display: none;">
		<tr>
			<td width="25%" class="LABEL">Expected Visit Date</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strScheduleDate}</td>
			<td width="25%" class="LABEL">Expected Visit Time</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strScheduleTime}</td>
		</tr>
		<tr>
			<td class="LABEL">Complaint Redressal Details</td>
			<td class="CONTROL" colspan="3">${hemDeskFB.strServiceEngineerRemarks}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Intimation Date</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIntimationDate}</td>
			<td width="25%" class="LABEL">Intimation Time</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIntimationTime}</td>
		</tr>
		<tr>
			<td class="LABEL">Person Contacted</td>
			<td class="CONTROL">${hemDeskFB.strContactPerson}</td>
			<td class="LABEL">Contact No.</td>
			<td class="CONTROL">${hemDeskFB.strContactNo}</td>
		</tr>
		<tr>
			<td class="LABEL">Company Name and Address</td>
			<td class="CONTROL">${hemDeskFB.strCompanyName}</td>
			<td class="LABEL">Company e-mail Id</td>
			<td class="CONTROL">${hemDeskFB.strCompanyAddress}</td>
		</tr>
		<tr style="display: none;">
			<td class="LABEL">Vendor Id</td>
			<td class="CONTROL">${hemDeskFB.strVendorId}</td>
			<td class="LABEL">Communication Id</td>
			<td class="CONTROL">${hemDeskFB.strCommunicationId}</td>
		</tr>
		<tr>
			<td class="LABEL">Communication Medium</td>
			<td class="CONTROL">${hemDeskFB.strCommunicationId}</td>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL">${hemDeskFB.strServiceProviderRemarks}</td>
		</tr>
	</table>

	<%--======================== Schedule Details End ========================= --%>

	<%-- ************** Attend Details Start ***************** --%>

	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('attendDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Complaint Attend
			Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="attendDetailsTable"
		style="display: none;">
		<tr style="display: none;">
			<td width="25%" class="LABEL">Service Engineer Name</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEngineerName}</td>
			<input type="hidden" name="strEngineerName"
				value="${hemDeskFB.strEngineerName}" />
			<td width="25%" class="LABEL">Service Engineer Address</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEngineerAddress}</td>
			<input type="hidden" name="strEngineerAddress"
				value="${hemDeskFB.strEngineerAddress}" />
		</tr>
		<tr>
			<td width="25%" class="LABEL">Service Engineer Name</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEngineerName}</td>
			<input type="hidden" name="strEngineerName"
				value="${hemDeskFB.strEngineerName}" />
			<td width="25%" class="LABEL">Mobile Number</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strMobileNo}</td>
			<input type="hidden" name="strEngineerAddress"
				value="${hemDeskFB.strMobileNo}" />
		</tr>

		<tr>
			<td width="25%" class="LABEL">Attend Date</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strAttendDate}</td>
			<input type="hidden" name="strAttendDate"
				value="${hemDeskFB.strAttendDate}" />
			<td width="25%" class="LABEL">Attend Time</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strAttendTime}</td>
			<input type="hidden" name="strHEMAttendTime"
				value="${hemDeskFB.strAttendTime}" />
		</tr>
		<tr>
			<td class="LABEL">Actual Problem Description</td>
			<td class="CONTROL" rowspan="2">${hemDeskFB.strActualProblemDesc}</td>
			<input type="hidden" name="strActualProblemDesc"
				value="${hemDeskFB.strActualProblemDesc}" />
			<td class="LABEL">Remarks</td>
			<td class="CONTROL" rowspan="2">${hemDeskFB.strAttendRemarks}</td>
			<input type="hidden" name="strAttendRemarks"
				value="${hemDeskFB.strAttendRemarks}" />
		</tr>
	</table>


	<%--======================== Attend Details End ========================= --%>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td>Action Taken Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH">
		<tr style="display: none;">

			<!--  Vendor/Service Engineer Name -->

			<td width="25%" class="LABEL">Vendor Name</td>
			<td width="25%" class="CONTROL">
			<div id="strVendorServiceEngId"><select
				name="strVendorServiceEngName" class='COMBO_NORMAL'
				onchange="getAttendName();">
				<bean:write name="hemDeskFB" property="strServiceEngNameCombo"
					filter="false" />
			</select> <html:hidden property="strServiceEngNameCombo"
				value="${hemDeskFB.strServiceEngNameCombo}" /></div>
			</td>

			<!-- Communicate Id/Contact No -->




			<td width="25%" class="LABEL">Communicate Id/Contact No</td>
			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MAX" maxlength="25"
				name="strCommunicateIdContactId"
				value="${hemDeskFB.strCommunicateIdContactId}"
				onkeypress="return validateData(event,2);"></td>


			<!--	Attend Date & Time	-->
		<tr style="display: none;">
			<td width="25%" class="LABEL"><font color="red">*</font>Date of
			Attend</td>
			<td width="25%" class="CONTROL"><dateTag:date
				name="strAttendDate" value="${hemDeskFB.strAttendDate}"></dateTag:date>
			</td>

			<td width="25%" class="LABEL"><font color="red">*</font>Time of
			Attend</td>

			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MIN" maxlength="5" name="strAttendTime"
				value="${hemDeskFB.strAttendTime}"
				onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)</td>
		</tr>





		<!--	Closing Date & Time	-->

		<tr style="display: none;">
			<td width="25%" class="LABEL"><font color="red">*</font>Date of
			Closing</td>
			<td width="25%" class="CONTROL"><dateTag:date
				name="strClosingDate" value="${hemDeskFB.strClosingDate}"></dateTag:date>
			</td>

			<td width="25%" class="LABEL"><font color="red">*</font>Time of
			Closing</td>

			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MIN" maxlength="5" name="strClosingTime"
				value="${hemDeskFB.strClosingTime}"
				onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)</td>
		</tr>



		<!-- Is Spare Part Maintenance Involved -->

		<tr>
			<td width="25%" class="LABEL">Spare Part used</td>

			<td width="25%" class="CONTROL"><select
				name="strIsSparePartsMaintenanceInvolved"
				id="strIsSparePartsMaintenanceInvolved" class="COMBO_MIN"
				onchange="isSparePartsUsed();">
				<option value="1">Yes</option>
				<option value="0">No</option>
			</select></td>

			<td width="25%" class="CONTROL"></td>


			<td width="25%" class="CONTROL"></td>
		</tr>

	</table>
	<!-- Spare Part Multi-row begins here -->




	<table class="TABLEWIDTH" id="SparePartMultiRowId">
		<tr>
			<td width="19%" class="LABEL">
			<div align="center"><font color="red">*</font>Store Name</div>
			</td>
			<td width="19" class="LABEL">
			<div align="center"><font color="red">*</font>Group Name</div>
			</td>
			<td width="19%" class="LABEL">
			<div align="center"><font color="red">*</font>Equipment Name</div>
			</td>
			<td width="19%" class="LABEL">
			<div align="center"><font color="red">*</font>Spare-Part Name</div>
			</td>
			<td width="19%" class="LABEL">
			<div id="srlNoLabel" align="center"><font color="red">*</font>Serial
			No.</div>
			</td>

			<td width="5%" class="LABEL"></td>
		</tr>
		<tr>
			<td width="19%" class="CONTROL_TD_NEW">${hemDeskFB.strStoreNameCombo}
			<input type="hidden" id="strWorkshopName" name="strWorkshopName"
				value="${hemDeskFB.strWorkshopName}" /></td>

			<td width="19%" class="LABEL">
			<div id="GroupNameDiv" align="center"><select
				name="strGroupName" id="strGroupName" OnChange='getEquipNameCmb();'
				title="Select Group Name">
				<bean:write name="hemDeskFB" property="strGroupNameCombo"
					filter="false" />
			</select></div>
			</td>
			<td width="19%" class="LABEL">
			<div id="EquipmentNameDiv" align="center"><select
				name="strEquipName" id="strEquipName" title="Select Equipment Name"
				onchange="getSparePartCmb();">
				<bean:write name="hemDeskFB" property="strEquipNameCombo"
					filter="false" />
			</select></div>
			</td>
			<td width="19%" class="LABEL">
			<div align="center" id="SparePartNameDiv"><select
				name="strSparePartName" id="strSparePartName"
				title="Select Spare Part Name" onchange="getSparePartSrlNoCmb();">
				<bean:write name="hemDeskFB" property="strSparePartNameOptions"
					filter="false" />
			</select></div>
			</td>

			<td width="19%" class="LABEL">
			<div id="SrlNoDiv" align="center"><select
				name="strSpareSreialNo" id="strSpareSreialNo" onchange=""
				title="Select Spare Part Serial No.">
				<option value="0">Select Value</option>
			</select></div>
			<div style="display: none;" id="SpareQtyDiv" align="center"><input
				type="text" style="border: 1px solid; text-align: center;"
				class="TEXT_FIELD_NEW" id="spareQty" readonly /> <!-- 		<label style="border:1px solid;" class="TEXT_FIELD_NEW" id="spareQty"></label>  -->
			<input id="strIssuedQty" class="TEXT_FIELD_NEW" type="text"
				onkeypress="return validateData(event,2);" onkeyup="" value="0"
				onclick="" onblur="" name="strIssuedQty" maxlength="5"></div>
			</td>
			<td style="display: none;" width="15%" class="LABEL">
			<div id="statusDiv" align="center"><select name="strStatus"
				id="strStatus" onchange=""
				title="Select Spare Part Replacement Status">
				<bean:write name="hemDeskFB" property="strSparePartStatusOptions"
					filter="false" />
			</select></div>
			</td>

			<td width="5%" class="LABEL"><img
				Src="../../hisglobal/images/plus.gif" style="cursor: pointer;"
				title="Add New Detail Row" OnClick="addSparePart();"></td>

		</tr>
	</table>





	<div id="id1">
	<div class="line">
	<table class="	" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td>Spare Part Details</td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" id="SparePartTable">
		<tr id="row">
			<td width="18" class="LABEL">
			<div align="center">Group Name</div>
			</td>
			<td width="18%" class="LABEL">
			<div align="center">Equipment Name</div>
			</td>
			<td width="18%" class="LABEL">
			<div align="center">Spare-Part Name</div>
			</td>
			<td width="18%" class="LABEL">
			<div align="center">Serial No./Quantity</div>
			</td>
			<td width="15%" class="LABEL">
			<div align="center">Cost(In Rs.)</div>
			</td>
			<td width="10%" class="LABEL">
			<div align="center">Status</div>
			</td>
			<td width="3%" class="LABEL"></td>
		</tr>

	</table>

	</div>
	<table class="TABLEWIDTH">

		<!--	Problem Description -->

		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Problem
			Description</td>
			<td width="25%" class="CONTROL"><html:textarea name="hemDeskFB"
				property="strProblemDescription" cols="25" rows="2"
				onkeypress="return validateDataWithSpecialChars(event,9,'.,,');" />
			</td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>


		<!--	Solution provided -->

		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Solution
			Provided</td>
			<td width="25%" class="CONTROL"><html:textarea name="hemDeskFB"
				property="strSolutionProvided" cols="25" rows="2"
				onkeypress="return validateDataWithSpecialChars(event,9,'.,,');" />
			</td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>

		<!--	Remarks -->

		<tr>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL"><html:textarea name="hemDeskFB"
				property="strServiceRemarks" cols="25" rows="2" /></td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>



		<!--	Reason for close -->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Reason for Close</td>
			<td width="25%" class="CONTROL"><html:textarea name="hemDeskFB"
				property="strReasonForClosing" cols="25" rows="2" /></td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>

		<!-- Is  Item In working Condition -->

		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Is Item
			In Working Condition</td>

			<td width="25%" class="CONTROL"><input type="radio"
				name="strIsItemInWorkingCondition" value="1" checked="true"
				onclick="isItemInWorkingConditionchangeMode();">Yes</td>

			<td width="25%" class="CONTROL"><input type="radio"
				name="strIsItemInWorkingCondition" value="0"
				onclick="isItemInWorkingConditionchangeMode();">No</td>


			<td width="25%" class="CONTROL"></td>
		</tr>


		<!--	From Date & Time	-->

		<tr id="dateAndTimeId" style="display: none;">
			<td width="25%" class="LABEL"><font color="red">*</font>From
			Date</td>
			<td width="25%" class="CONTROL"><dateTag:date name="strFromDate"
				value="${hemDeskFB.strFromDate}"></dateTag:date></td>

			<td width="25%" class="LABEL"><font color="red">*</font>From
			Time</td>

			<td width="25%" class="CONTROL"><input style="display: none;"
				type="text" class="TEXT_FIELD_MIN" maxlength="5"
				name="strFromTimeOld" onkeypress='return checkTime(event,this);' />
			<select name="strFromTime" class="COMBO_MIN">
				<option value="01:00">01:00</option>
				<option value="01:30">01:30</option>
				<option value="02:00">02:00</option>
				<option value="02:30">02:30</option>
				<option value="03:00">03:00</option>
				<option value="03:30">03:30</option>
				<option value="04:00">04:00</option>
				<option value="04:30">04:30</option>
				<option value="05:00">05:00</option>
				<option value="05:30">05:30</option>
				<option value="06:00">06:00</option>
				<option value="06:30">06:30</option>
				<option value="07:00">07:00</option>
				<option value="07:30">07:30</option>
				<option value="08:00">08:00</option>
				<option value="08:30">08:30</option>
				<option value="09:00">09:00</option>
				<option value="09:30">09:30</option>
				<option value="10:00">10:00</option>
				<option value="10:30">10:30</option>
				<option value="11:00">11:00</option>
				<option value="11:30">11:30</option>
				<option value="12:00">12:00</option>
				<option value="12:30">12:30</option>
			</select> <select class="COMBO_SMALL" name="strScheduleTimeMeridian">
				<OPTION value="AM">AM</OPTION>
				<OPTION value="PM">PM</OPTION>
			</select></td>
		</tr>

		<!-- Total Cost-->

		<tr>
			<td width="25%" class="LABEL">Other Charges</td>
			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MAX" maxlength="10" name="strOtherCharges"
				id="strOtherCharges" onblur="calculateTotal();" onclick="select();"
				value="0" onkeyup="calculateTotal();"
				onkeypress="return validateData(event,2);"></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Total
			Cost</td>

			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MAX" maxlength="10" name="strTotalCost"
				id="strTotalCost" onclick="select();"
				onkeypress="return validateData(event,2);"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Penal Charges</td>
			<td width="25%" class="CONTROL"><input type="radio"
				name="strIsPenality" value="1"
				onclick="isPenaltyConditionchangeMode();">Yes</td>

			<td width="25%" class="CONTROL"><input type="radio"
				name="strIsPenality" value="0" checked="checked"
				onclick="isPenaltyConditionchangeMode();">No</td>
			<td width="25%" class="CONTROL"></td>
		</tr>
	</table>
	<table class="TABLEWIDTH" id="penaltyAmtId" style="display: none;">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Penal
			Charge</td>
			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MAX" maxlength="10" name="strPenalityAmount"
				onblur="setNetAmount(this);"
				onkeypress="return validateData(event,2);"></td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL"><html:textarea name="hemDeskFB"
				property="strPenaltyRemarks" cols="25" rows="1" /></td>

		</tr>
	</table>


	<!-- Order No and Order Date-->


	<table class="TABLEWIDTH">

		<!-- Net Cost-->

		<tr style="display: none;">
			<td width="25%" class="LABEL"><font color="red">*</font>Net Cost
			</td>

			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MAX" maxlength="25" name="strNetCost"
				id="strNetCost" value="0" onkeypress="return validateData(event,2);">
			</td>

			<td width="25%" class="CONTROL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<!--	Contact Person Name	&	Contact No.		-->

		<tr style="display: none;">

			<!--  Vendor/Service Engineer Name -->

			<td width="25%" class="LABEL">VerfiedBy</td>
			<td width="25%" class="CONTROL">
			<div id="strVerifiedById"><select name="strVerifiedBy"
				class='COMBO_NORMAL' onchange="getAttendName();">
				<bean:write name="hemDeskFB" property="strVerifiedByOptions"
					filter="false" />
			</select></div>
			<html:hidden property="strVerifiedByOptions"
				value="${hemDeskFB.strVerifiedByOptions}" /></td>



			<td width="25%" class="CONTROL"></td>


			<td width="25%" class="CONTROL"></td>
		</tr>


		<!--	Verfied  Date & Time	-->

		<tr style="display: none;">
			<td width="25%" class="LABEL"><font color="red">*</font>Date</td>
			<td width="25%" class="CONTROL"><dateTag:date
				name="strVerifyDate" value="${hemDeskFB.strVerifyDate}"></dateTag:date>
			</td>

			<td width="25%" class="LABEL"><font color="red">*</font>Time</td>

			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MIN" maxlength="5" name="strVerifyTime"
				onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)</td>
		</tr>



	</table>


	<table class="TABLEWIDTH">

		<tr class="FOOTER">
			<td></td>
		</tr>
	</table>


	<div>
	<div class="legends"><font size="2" color="red">*</font>
	Mandatory Fields</div>
	<div class="control_button">
	<table class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<div><a href="#" class="button" onClick="return validate1();"><span
				class="save">Save</span></a> <a href="#" class="button"
				onclick="clearPage();"><span class="clear">Clear</span></a> <a
				href="#" class="button" onClick="cancelPage();"><span
				class="back">Back</span></a></div>
			</td>
		</tr>
	</table>
	</div>
	</div>

	<input type="hidden" name="hmode" />

	<input type="hidden" name="strStoreName" value="" />
	<input type="hidden" name="strStockInfoVal" value="" />
	<input type="hidden" name="strConfigPropertyValue" value="0" />
	<input type="hidden" name="strEmpName" value="${hemDeskFB.strEmpName}" />
	<input type="hidden" name="strItemId" value="${hemDeskFB.strItemId}" />
	<input type="hidden" name="strStoreId" value="${hemDeskFB.strStoreId}" />
	<input type="hidden" name="strEnggItemTypeId"
		value="${hemDeskFB.strEnggItemTypeId}" />
	<input type="hidden" name="strEnggItemSubTypeId"
		value="${hemDeskFB.strEnggItemSubTypeId}" />
	<input type="hidden" name="strComplaintId"
		value="${hemDeskFB.strComplaintId}" />
	<input type="hidden" name="strPageFlag"
		value="${hemDeskFB.strPageFlag}" />
	<input type="hidden" name="strDesignation" value="" />
	<input type="hidden" name="strOldDepartmentCode"
		value="${hemDeskFB.strDeptCode}" />
	<input type="hidden" name="strPath" value="${hemDeskFB.strPath}">
	<input type="hidden" name="strComplaintStatus" id="strComplaintStatus"
		value="${hemDeskFB.strComplaintStatus}">
	<html:hidden name="hemDeskFB" property="strHemDesk" />
	<input type="hidden" name="strChk" value="${hemDeskFB.strChk}">
	<html:hidden property="strSparePartNameOptions"
		value="${hemDeskFB.strSparePartNameOptions}" />
	<html:hidden property="strManufactureNameOptions"
		value="${hemDeskFB.strManufactureNameOptions}" />
	<html:hidden property="strSparePartStatusOptions"
		value="${hemDeskFB.strSparePartStatusOptions}" />
	<input type="hidden" name="strRetValue"
		value="${hemDeskFB.strRetValue}" />
	<input type="hidden" name="strMsgString"
		value="${hemDeskFB.strMsgString}" />

	<input type="hidden" name="strUploadFileId" value="" />
	<input type="hidden" name="strAttendedContactPerson" value="" />
	<html:hidden name="hemDeskFB" property="strSerialNo" value="0" />
	<html:hidden name="hemDeskFB" property="strSparePartsFlag" value="0" />
	<input type="hidden" name="strSparePartInfoVal" value="" />
	<input type="hidden" name="strSpareCost" value="" />
	<input type="hidden" name="strReceivedNo" value="" />

	<div id="blanket" style="display: none;"></div>
	<div class="popUpDiv" id="popUpDiv" style="display: none;">
	<table bgcolor="white">
		<tr>
			<td>

			<div id="stockDtlsDivId" style="display: block;"></div>
			</td>
		</tr>
	</table>
	</div>
	<cmbPers:cmbPers />
</html:form>
<jsp:include page="bmed_service_action_multirow.jsp"></jsp:include>
</body>
</html>