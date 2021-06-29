<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!-- 
/**
 * @author Saratkumar and P.P. Chattaraj
 * Date of Creation : 09-Sept-2013
 * Date of Modification : 05-Jun-2014  
 * Version : 
 * Module  : HEMMS Product 1.0 [HEMM Desk]
 */
 -->
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
<title>Complaint Closing Desk</title>
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

<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/HBIMS/bmed/transactions/js/bmed_HemDesk_trans.js" />
</head>
<body marginheight="0" marginwidth="0" onload="chkRecordSaved()"
	class="background">
<html:form name="hemDeskFB" action="/transactions/HemDeskACTION"
	type="bmed.transactions.controller.fb.HemDeskFB" styleClass="formbg">

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Complaint Closing Details&gt;&gt; Add</td>
		</tr>
	</table>

	<div id="strErrMsg" align="center" class="errMsg"><bean:write
		name="hemDeskFB" property="strErrMsg" /></div>
	<div id="strWarningMsg" align="center" class="warningMsg"><bean:write
		name="hemDeskFB" property="strWarningMsg" /></div>
	<div style="display: none;" id="normalMsg" align="center"
		class="normalMsg"><bean:write name="hemDeskFB"
		property="strNormalMsg" /></div>

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
			<td width="25%" class="CONTROL">${hemDeskFB.strEngineerName}
			<input type="hidden" name="strEngineerName"
				value="${hemDeskFB.strEngineerName}" />
				</td>
			
			<td width="25%" class="LABEL">Service Engineer Address</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEngineerAddress}
			<input type="hidden" name="strEngineerAddress"
				value="${hemDeskFB.strEngineerAddress}" /></td>
			
		</tr>
		<tr>
			<td width="25%" class="LABEL">Service Engineer Name</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEngineerName}</td>
			<td width="25%" class="LABEL">Mobile Number</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strMobileNo}
			<input type="hidden" name="strEngineerAddress"
				value="${hemDeskFB.strMobileNo}" /></td>
			
		</tr>

		<tr>
			<td width="25%" class="LABEL">Attend Date</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strAttendDate}</td>
			<td width="25%" class="LABEL">Attend Time</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strAttendTime}
			<input type="hidden" name="strEngineerName"
				value="${hemDeskFB.strEngineerName}" />
				<input type="hidden" name="strHEMAttendTime"
				value="${hemDeskFB.strAttendTime}" />
			</td>
			
		</tr>
		<tr>
			<td class="LABEL">Actual Problem Description</td>
			<td class="CONTROL" rowspan="2">${hemDeskFB.strActualProblemDesc}
			<input type="hidden" name="strActualProblemDesc"
				value="${hemDeskFB.strActualProblemDesc}" />
				</td>
			
			<td class="LABEL">Remarks</td>
			<td class="CONTROL" rowspan="2">${hemDeskFB.strAttendRemarks}
			<input type="hidden" name="strAttendRemarks"
				value="${hemDeskFB.strAttendRemarks}" />
				</td>
			
		</tr>
	</table>


	<%--======================== Attend Details End ========================= --%>
	
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('engineerDeskDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Action Taken
			Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="engineerDeskDetailsTable" 
		style="display: none;">
		<tr style="display: none;">

			<!--  Vendor/Service Engineer Name -->

			<td width="25%" class="LABEL">Vendor Name</td>
			<td width="25%" class="CONTROL">
			<div id="strVendorServiceEngId">${hemDeskFB.strVendorServiceEngName}</div>
			</td>

			<!-- Communicate Id/Contact No -->




			<td width="25%" class="LABEL">Communicate Id/Contact No</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strCommunicateIdContactId}</td>


			<!--	Attend Date & Time	-->
		<tr style="display: none;">
			<td width="25%" class="LABEL">Date of Attend</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strServiceAttendDate}</td>

			<td width="25%" class="LABEL">Time of Attend</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strServiceAttendTime}</td>
		</tr>





		<!--	Closing Date & Time	-->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Date of Closing</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strServiceClosingDate}</td>

			<td width="25%" class="LABEL">Time of Closing</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strServiceClosingTime}</td>
		</tr>



		<!-- Is Spare Part Maintenance Involved -->

		<tr>
			<td width="25%" class="LABEL">Spare Parts Used</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strIsSparePartsMaintenanceInvolved}</td>

			<td width="25%" class="LABEL"></td>


			<td width="25%" class="CONTROL"></td>
		</tr>

		<logic:equal name='hemDeskFB'
			property='strIsSparePartsMaintenanceInvolved' value="Yes">
			<!-- Spare Part Multi-row begins here -->
			<tr>
				<td colspan="4" width="100%">

				<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('sparePartDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Spare Part Details</td>
		</tr>
	</table>
	</div>
				<table class="TABLEWIDTH" id="sparePartDetailsTable"
					style="display: none;">
					<bean:write name="hemDeskFB"
						property="strServiceActionSpareDetailsTable" filter="false" />
				</table>
				<hr width="94%">
				</td>
			</tr>
		</logic:equal>

		<!--	Problem Description -->

		<tr>
			<td width="25%" class="LABEL">Problem Description</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strProblemDescription}</td>

			<td width="25%" class="LABEL">Solution Provided</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strSolutionProvided}
			</td>
		</tr>


		<!--	Solution provided -->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Solution Provided</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strSolutionProvided}
			</td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>

		<!--	Remarks -->

		<tr>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strServiceRemarks}</td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>



		<!--	Reason for close -->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Reason for Close</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strReasonForClosing}</td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>

		<!-- Is  Item In working Condition -->

		<tr>
			<td width="25%" class="LABEL">Is Item In Working Condition</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strIsItemInWorkingCondition}</td>

			<td width="25%" class="CONTROL"></td>


			<td width="25%" class="CONTROL"></td>
		</tr>


		<!--	From Date & Time	-->
		<logic:equal name="hemDeskFB" property="strIsItemInWorkingCondition"
			value="No">
			<tr>
				<td width="25%" class="LABEL">From Date</td>
				<td width="25%" class="CONTROL">${hemDeskFB.strFromDate}</td>

				<td width="25%" class="LABEL">From Time</td>

				<td width="25%" class="CONTROL">${hemDeskFB.strFromTime}</td>
			</tr>
		</logic:equal>
		<!-- Total Cost-->

		<tr>
			<td width="25%" class="LABEL">Other Charges</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strOtherCharges}</td>

			<td width="25%" class="LABEL">Total Cost</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strTotalCost}</td>


		</tr>
		<tr>
			<td width="25%" class="LABEL">Penal Charges</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIsPenality}</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<logic:equal name="hemDeskFB" property="strIsPenality" value="Yes">
			<tr>
				<td width="25%" class="LABEL">Penal Charge</td>
				<td width="25%" class="CONTROL">${hemDeskFB.strPenalityAmount}</td>
				<td width="25%" class="LABEL">Remarks</td>
				<td width="25%" class="CONTROL">${hemDeskFB.strPenaltyRemarks}</td>
			</tr>
		</logic:equal>
		<!-- Order No and Order Date-->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Order No.</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strOfficeOrderNo}</td>

			<td width="25%" class="LABEL">Order Date</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strOfficeOrderDt}</td>
		</tr>

		<!-- Net Cost-->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Net Cost</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strNetCost}</td>

			<td width="25%" class="CONTROL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<!--	Contact Person Name	&	Contact No.		-->

		<tr style="display: none;">

			<!--  Vendor/Service Engineer Name -->

			<td width="25%" class="LABEL">VerfiedBy</td>
			<td width="25%" class="CONTROL">
			<div id="strVerifiedById">${hemDeskFB.strVerifiedBy}</div>
			</td>



			<td width="25%" class="CONTROL"></td>


			<td width="25%" class="CONTROL"></td>
		</tr>


		<!--	Verfied  Date & Time	-->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Date</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strVerifyDate}</td>

			<td width="25%" class="LABEL">Time</td>

			<td width="25%" class="CONTROL">${hemDeskFB.strVerifyTime}</td>
		</tr>



	</table>

	<%--Engineer Service Action Detail Ends--%>
	<%--Hem Close Detail Starts--%>
	<%-- ************** Closing Details Start ***************** --%>

	
<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td>Complaint Closing Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH">
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Closing
			Date</td>
			<td width="25%" class="CONTROL"><his:date name="strClosingDate"
				value="" onchange="calculateTotalDownTime();"></his:date></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Closing
			Time</td>
			<td width="25%" class="CONTROL"><input type="text"
				style="display: none;" name="strClosingTimeOld"
				class="TEXT_FIELD_MIN" maxlength="5"
				onchange="return validateDataWithSpecialChars(event,5,':');"
				onkeypress='return checkTime(event,this);'"> <select
				name="strClosingTime" id="strClosingTime" class="COMBO_MIN"
				onchange="calculateTotalDownTime();">
				<option value="0">Select Value</option>
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
			</select> <select class="COMBO_SMALL" name="strScheduleTimeMeridian"
				id="strScheduleTimeMeridian" onchange="calculateTotalDownTime();">
				<OPTION value="AM">AM</OPTION>
				<OPTION value="PM">PM</OPTION>
			</select></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Reason For Closing</td>
			<td class="CONTROL"><textarea rows="2" cols="25"
				onblur="calculateTotalDownTime();" name="strHEMReasonForClosing"
				onkeypress="return validateDataWithSpecialChars(event,9,'.,,');"
				onchange="return validateDataWithSpecialChars(event,9,'.,,');"></textarea>
			</td>
			<td class="CONTROL"></td>
			<td class="CONTROL"></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Complaint Status</td>
			<td class="CONTROL"><select name="strClosingComplaintStatus"
				class="COMBO_MIN" onchange="showHidePendencyRemarks();">
				<OPTION value="C">Close</OPTION>
				<OPTION value="P">Pending</OPTION>
			</select></td>
			<td class="LABEL">
			<div id="PendencyRemarksRowId" class="LABEL" style="display: none;"><font
				color="red">*</font>Reason For Pendency</div>
			</td>
			<td class="CONTROL">
			<div id="PendencyRemarksRowIdDefault" style="display: none;"><textarea
				rows="1" cols="25" name="strPendencyRemarks"
				onkeypress="return validateDataWithSpecialChars(event,9,'.,,');"
				onchange="return validateDataWithSpecialChars(event,9,'.,,');"></textarea>
			</div>
			</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Vendor
			Invoice No.</td>
			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MAX" maxlength="25" name="strHEMVendorId"
				onkeypress="return validateData(event,2);" value="NA"></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Total
			Cost Involved</td>
			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MAX" maxlength="10"
				value="${hemDeskFB.strTotalCost}" name="strHEMTotalCost"
				onkeypress="return validateData(event,7);"></td>
		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL"><font color="red">*</font>Is Item
			In Working Condition</td>

			<td width="25%" class="CONTROL"><html:radio name="hemDeskFB"
				property="strIsHEMItemInWorkingCondition" value="0" onclick="" />
			Yes</td>

			<td width="25%" class="CONTROL"><html:radio name="hemDeskFB"
				property="strIsHEMItemInWorkingCondition" value="1" onclick="" />
			NO</td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL"><font color="red">*</font>Total
			DownTime From Date</td>
			<td width="25%" class="CONTROL"><dateTag:date
				name="strDownFromDate" value="${hemDeskFB.strDownFromDate}"></dateTag:date>
			</td>
			<td width="25%" class="LABEL"><font color="red">*</font>Total
			DownTime To Date</td>

			<td width="25%" class="CONTROL"><dateTag:date
				name="strDownToDate" value="${hemDeskFB.strDownToDate}"></dateTag:date>
			</td>
		</tr>

		<!--<tr>
					<td class="LABEL"><font color="red">*</font>Actual Problem Description</td>
					<td class="CONTROL"><textarea rows="2" cols="25"
						name="strHEMActualProblemDesc"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
					<td class="LABEL"></td>
					<td class="CONTROL"></td>
				</tr>
				-->
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Total
			DownTime</td>
			<td width="25%" class="CONTROL"><input type="text"
				class="TEXT_FIELD_MIN" readonly="readonly" maxlength="5"
				name="strDownTotalTime" onkeyup="calculateTotalDownTime();"
				onkeypress='calculateTotalDownTime();' onfocus="calculateTotalDownTime();"/></td>

			<td width="25%" class="LABEL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>
		<tr style="display: none;">

			<!--  Vendor/Service Engineer Name -->

			<td width="25%" class="LABEL"><font color="red">*</font>Verfied
			By</td>
			<td width="25%" class="CONTROL">
			<div id="strHEMVerifiedById"><select name="strHEMVerifiedBy"
				class='COMBO_NORMAL' onchange="">
				<bean:write name="hemDeskFB" property="strVerifiedByOptions"
					filter="false" />
			</select></div>
			<html:hidden property="strVerifiedByOptions"
				value="${hemDeskFB.strVerifiedByOptions}" /></td>

			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL"><html:textarea name="hemDeskFB"
				property="strHEMClosingRemarks" cols="25" rows="2" /></td>
		</tr>
	</table>


	<%--Hem Close Detail Ends--%>

	<table class="TABLEWIDTH">

		<tr class="FOOTER">
			<td></td>
		</tr>
	</table>
	
	<!-- <div>
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
	</div> -->
	
	<table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="return validate1();"/> <img
				src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer; cursor: hand;" title="Clear Process"
				onclick="clearPage();"/>  <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();" /></td>
		</tr>
	</table>

	


	<!-- Error Messages 	-->
	<br />

	<input type="hidden" name="hmode" />
	<input type="hidden" name="strPath" value="${hemDeskFB.strPath}">
	<input type="hidden" name="strChk" value="${hemDeskFB.strChk}">
	<input type="hidden" name="strEscDtl" id="strEscDtl" value="">
	<input type="hidden" name="strReqType" id="strReqType"
		value="${hemDeskFB.strReqType}">
	<input type="hidden" name="strComplaintStatus" id="strComplaintStatus"
		value="${hemDeskFB.strComplaintStatus}">
	<input type="hidden" name="strAttendDate" id="strAttendDate"
		value="${hemDeskFB.strAttendDate}">
	<input type="hidden" name="strComplaintDate" id="strComplaintDate"
		value="${hemDeskFB.strComplaintDate}">
	<input type="hidden" name="strRetValue"
		value="${hemDeskFB.strRetValue}" />
	<input type="hidden" name="strMsgString"
		value="${hemDeskFB.strMsgString}" />

	<html:hidden name="hemDeskFB" property="strComplaintId" />
	<html:hidden name="hemDeskFB" property="strHemDesk" />
	<cmbPers:cmbPers />
</html:form>
</body>
</html>