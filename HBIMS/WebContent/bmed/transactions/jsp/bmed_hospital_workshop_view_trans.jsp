<%@page import="hisglobal.hisconfig.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
 * Date of Creation : 10-Sept-2013
 * Date of Modification :22-Nov-2013  
 * Version : 
 * Module  : HEMMS Product 1.0 [HEMM Desk]
 */
 -->
<html>
<head>
<title>Complaint View Desk</title>
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
<his:javascript	src="/bmed/transactions/js/hospital_workshop_desk_trans.js" />
</head>
<body marginheight="0" marginwidth="0" onload="chkRecordSaved()"
	class="background">
<html:form name="hospitalWorkshopDeskFB"
	action="/transactions/HospitalWorkshopDeskACTION"
	type="bmed.transactions.controller.fb.HospitalWorkshopDeskFB">

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Complaint Status Details&gt;&gt; View</td>
		</tr>
	</table>

	<div id="strErrMsg" align="center" class="errMsg"><bean:write
		name="hospitalWorkshopDeskFB" property="strErrMsg" /></div>
	<div id="strWarningMsg" align="center" class="warningMsg"><bean:write
		name="hospitalWorkshopDeskFB" property="strWarningMsg" /></div>
	<div style="display: none;" id="normalMsg" align="center"
		class="normalMsg"><bean:write name="hospitalWorkshopDeskFB"
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
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strComplaintType}</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Complaint Id</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strComplaintId}</td>
			<td width="25%" class="LABEL">Complaint Date</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strComplaintDate}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Hospital Name</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strStoreName}</td>
			<td width="25%" class="LABEL">Lab Name</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strLabName}</td>
		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Engineering Item Type</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strEnggItemType}</td>
			<td width="25%" class="LABEL">Engineering Item Sub Type</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strEnggItemSubType}</td>
		</tr>
		<tr>
			<td class="LABEL">Equipment Name</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strItemName}</td>
			<td class="LABEL">Equipment Model</td>
			<!--<td class="CONTROL">${hospitalWorkshopDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
			<td class="CONTROL">${hospitalWorkshopDeskFB.strItemModel}</td>
		</tr>
		<tr>
			<td class="LABEL">UIN</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strEquipmentUIDNo}</td>
			<td width="25%" class="LABEL">Equipment Serial No.</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strItemBatchNo}</td>
		</tr>
		<tr>
			<td class="LABEL">Manufacturer/Supplier Name</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strManufacturerName}</td>
			<td class="LABEL">Complaint Description</td>
			<td class="CONTROL" rowspan="3">${hospitalWorkshopDeskFB.strComplaintDescription}</td>
		</tr>
	</table>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('warrantyDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Guarantee Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="warrantyDetailsTable"
		style="display: none;">
		<bean:write name="hospitalWorkshopDeskFB" property="strWarrantyDetailsTable"
			filter="false" />
	</table>

	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('maintenanceContractDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Maintenance Contract
			Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="maintenanceContractDetailsTable"
		style="display: none;">
		<bean:write name="hospitalWorkshopDeskFB"
			property="strMaintenanceContractDetailsTable" filter="false" />
	</table>

	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
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
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIsAcknowledge}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strRemarks}</td>

		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Complaint Nature</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strComplaintNature}</td>
			<td width="25%" class="LABEL">Action Taken</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strActionTaken}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Complaint Nature</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strComplaintNature}</td>
			<td width="25%" class="LABEL">Approved</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIsApproved}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL" colspan="3">${hospitalWorkshopDeskFB.strRemarks}</td>
		</tr>
	</table>

	<table class="TABLEWIDTH" style="display: none;">
		<tr>
			<td width="25%" class="LABEL">Acknowledgment</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIsHEMAcknowledge}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strAckRemarks}</td>
		</tr>

	</table>


	<%-- ************** Schedule Details Start ***************** --%>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
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
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strScheduleDate}</td>
			<td width="25%" class="LABEL">Expected Visit Time</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strScheduleTime}</td>
		</tr>
		<tr>
			<td class="LABEL">Complaint Redressal Details</td>
			<td class="CONTROL" colspan="3">${hospitalWorkshopDeskFB.strServiceEngineerRemarks}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Intimation Date</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIntimationDate}</td>
			<td width="25%" class="LABEL">Intimation Time</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIntimationTime}</td>
		</tr>
		<tr>
			<td class="LABEL">Person Contacted</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strContactPerson}</td>
			<td class="LABEL">Contact No.</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strContactNo}</td>
		</tr>
		<tr>
			<td class="LABEL">Company Name and Address</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strCompanyName}</td>
			<td class="LABEL">Company e-mail Id</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strCompanyAddress}</td>
		</tr>
		<tr style="display: none;">
			<td class="LABEL">Vendor Id</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strVendorId}</td>
			<td class="LABEL">Communication Id</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strCommunicationId}</td>
		</tr>
		<tr>
			<td class="LABEL">Communication Medium</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strCommunicationId}</td>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL">${hospitalWorkshopDeskFB.strServiceProviderRemarks}</td>
		</tr>
	</table>

	<%--======================== Schedule Details End ========================= --%>

	<%-- ************** Attend Details Start ***************** --%>

	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
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
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strEngineerName}
			<input type="hidden" name="strEngineerName"
				value="${hospitalWorkshopDeskFB.strEngineerName}" />
				</td>
			
			<td width="25%" class="LABEL">Service Engineer Address</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strEngineerAddress}
			<input type="hidden" name="strEngineerAddress"
				value="${hospitalWorkshopDeskFB.strEngineerAddress}" /></td>
			
		</tr>
		<tr>
			<td width="25%" class="LABEL">Service Engineer Name</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strEngineerName}</td>
			<td width="25%" class="LABEL">Mobile Number</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strMobileNo}
			<input type="hidden" name="strEngineerAddress"
				value="${hospitalWorkshopDeskFB.strMobileNo}" /></td>
			
		</tr>

		<tr>
			<td width="25%" class="LABEL">Attend Date</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strAttendDate}</td>
			<td width="25%" class="LABEL">Attend Time</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strAttendTime}
			<input type="hidden" name="strEngineerName"
				value="${hospitalWorkshopDeskFB.strEngineerName}" />
				<input type="hidden" name="strHEMAttendTime"
				value="${hospitalWorkshopDeskFB.strAttendTime}" />
			</td>
			
		</tr>
		<tr>
			<td class="LABEL">Actual Problem Description</td>
			<td class="CONTROL" rowspan="2">${hospitalWorkshopDeskFB.strActualProblemDesc}
			<input type="hidden" name="strActualProblemDesc"
				value="${hospitalWorkshopDeskFB.strActualProblemDesc}" />
				</td>
			
			<td class="LABEL">Remarks</td>
			<td class="CONTROL" rowspan="2">${hospitalWorkshopDeskFB.strAttendRemarks}
			<input type="hidden" name="strAttendRemarks"
				value="${hospitalWorkshopDeskFB.strAttendRemarks}" />
				</td>
			
		</tr>
	</table>


	<%--======================== Attend Details End ========================= --%>
	
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
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
			<div id="strVendorServiceEngId">${hospitalWorkshopDeskFB.strVendorServiceEngName}</div>
			</td>

			<!-- Communicate Id/Contact No -->




			<td width="25%" class="LABEL">Communicate Id/Contact No</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strCommunicateIdContactId}</td>


			<!--	Attend Date & Time	-->
		<tr style="display: none;">
			<td width="25%" class="LABEL">Date of Attend</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strServiceAttendDate}</td>

			<td width="25%" class="LABEL">Time of Attend</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strServiceAttendTime}</td>
		</tr>





		<!--	Closing Date & Time	-->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Date of Closing</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strServiceClosingDate}</td>

			<td width="25%" class="LABEL">Time of Closing</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strServiceClosingTime}</td>
		</tr>



		<!-- Is Spare Part Maintenance Involved -->

		<tr>
			<td width="25%" class="LABEL">Spare Parts Used</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIsSparePartsMaintenanceInvolved}</td>

			<td width="25%" class="LABEL"></td>


			<td width="25%" class="CONTROL"></td>
		</tr>

		<logic:equal name='hospitalWorkshopDeskFB'
			property='strIsSparePartsMaintenanceInvolved' value="Yes">
			<!-- Spare Part Multi-row begins here -->
			<tr>
				<td colspan="4" width="100%">

	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('sparePartDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Spare Part Details</td>
		</tr>
	</table>
	</div>
				<table class="TABLEWIDTH" id="sparePartDetailsTable"
					style="display: none;">
					<bean:write name="hospitalWorkshopDeskFB"
						property="strServiceActionSpareDetailsTable" filter="false" />
				</table>
				<hr width="94%">
				</td>
			</tr>
		</logic:equal>

		<!--	Problem Description -->

		<tr>
			<td width="25%" class="LABEL">Problem Description</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strProblemDescription}</td>

			<td width="25%" class="LABEL">Solution Provided</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strSolutionProvided}
			</td>
		</tr>


		<!--	Solution provided -->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Solution Provided</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strSolutionProvided}
			</td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>

		<!--	Remarks -->

		<tr>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strServiceRemarks}</td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>



		<!--	Reason for close -->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Reason for Close</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strReasonForClosing}</td>

			<td width="25%" class="CONTROL"></td>

			<td width="25%" class="CONTROL"></td>
		</tr>

		<!-- Is  Item In working Condition -->

		<tr>
			<td width="25%" class="LABEL">Is Item In Working Condition</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIsItemInWorkingCondition}</td>

			<td width="25%" class="CONTROL"></td>


			<td width="25%" class="CONTROL"></td>
		</tr>


		<!--	From Date & Time	-->
		<logic:equal name="hospitalWorkshopDeskFB" property="strIsItemInWorkingCondition"
			value="No">
			<tr>
				<td width="25%" class="LABEL">From Date</td>
				<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strFromDate}</td>

				<td width="25%" class="LABEL">From Time</td>

				<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strFromTime}</td>
			</tr>
		</logic:equal>
		<!-- Total Cost-->

		<tr>
			<td width="25%" class="LABEL">Other Charges</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strOtherCharges}</td>

			<td width="25%" class="LABEL">Total Cost</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strTotalCost}</td>


		</tr>
		<tr>
			<td width="25%" class="LABEL">Penal Charges</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIsPenality}</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<logic:equal name="hospitalWorkshopDeskFB" property="strIsPenality" value="Yes">
			<tr>
				<td width="25%" class="LABEL">Penal Charge</td>
				<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strPenalityAmount}</td>
				<td width="25%" class="LABEL">Remarks</td>
				<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strPenaltyRemarks}</td>
			</tr>
		</logic:equal>
		<!-- Order No and Order Date-->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Order No.</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strOfficeOrderNo}</td>

			<td width="25%" class="LABEL">Order Date</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strOfficeOrderDt}</td>
		</tr>

		<!-- Net Cost-->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Net Cost</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strNetCost}</td>

			<td width="25%" class="CONTROL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<!--	Contact Person Name	&	Contact No.		-->

		<tr style="display: none;">

			<!--  Vendor/Service Engineer Name -->

			<td width="25%" class="LABEL">VerfiedBy</td>
			<td width="25%" class="CONTROL">
			<div id="strVerifiedById">${hospitalWorkshopDeskFB.strVerifiedBy}</div>
			</td>



			<td width="25%" class="CONTROL"></td>


			<td width="25%" class="CONTROL"></td>
		</tr>


		<!--	Verfied  Date & Time	-->

		<tr style="display: none;">
			<td width="25%" class="LABEL">Date</td>
			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strVerifyDate}</td>

			<td width="25%" class="LABEL">Time</td>

			<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strVerifyTime}</td>
		</tr>



	</table>

	<%--Engineer Service Action Detail Ends--%>
		<%-- **************Complaint Closing Details Start ***************** --%>
		<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('closingDeskDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Complaint Closing Details</td>
		</tr>
	</table>
	</div>
	
			<table class="TABLEWIDTH" id="closingDeskDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL">Closing Date</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strClosingDate}</td>
					<td width="25%" class="LABEL">Closing Time</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strClosingTime}</td>
				</tr>
				<tr>
					<td class="LABEL">Reason For Closing</td>
					<td class="CONTROL" colspan="3">${hospitalWorkshopDeskFB.strHEMReasonForClosing}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL">Complaint Status</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strClosingComplaintStatus}</td>
					<logic:equal name="hospitalWorkshopDeskFB" property="strClosingComplaintStatus" value="Closed">
					<td width="25%" class="LABEL"></td>
					<td width="25%" class="CONTROL"></td>
					</logic:equal>
					<logic:equal name="hospitalWorkshopDeskFB" property="strClosingComplaintStatus" value="Pending">
					<td width="25%" class="LABEL">Reason For Pendency</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strPendencyRemarks}</td>
					</logic:equal>
				</tr>
				<tr>
					<td width="25%" class="LABEL">Total Cost Involved</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strHEMTotalCost}</td>
					<td width="25%" class="LABEL"></td>
					<td width="25%" class="CONTROL"></td>
					
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL">Vendor Invoice No.</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strHEMVendorId}</td>
					<td width="25%" class="LABEL">Total Cost Involved</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strHEMTotalCost}</td>
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL">Is Item In Working Condition
					</td>
					
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strIsHEMItemInWorkingCondition}</td>
					
					<td width="25%" class="CONTROL"></td>
					<td width="25%" class="CONTROL"></td>
				</tr>
					<tr style="display: none;">
						<td width="25%" class="LABEL">Total DownTime From Date</td>
						<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strDownFromDate}</td>
						<td width="25%" class="LABEL">Total DownTime To Date
						</td>
						<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strDownToDate}</td>					
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
				<tr >
						<td width="25%" class="LABEL">Total DownTime</td>
						<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strDownTotalTime}</td>					
					
						<td width="25%" class="LABEL"></td>
						
						<td width="25%" class="CONTROL"></td>					
					</tr>	
				<tr style="display: none;">
			
			<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL">Verfied By</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strHEMVerifiedBy}</td>
					
					<td width="25%" class="LABEL">Remarks</td>
					<td width="25%" class="CONTROL">${hospitalWorkshopDeskFB.strHEMClosingRemarks}</td>
				</tr>
				<tr class="FOOTER">
				<td>
				</td></tr>
			</table>
		<hr width="95%">	
		<%--Hem Close Detail Ends--%>
	<div>
	<div class="control_button">
	<table class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<div><a
				href="#" class="button" onClick="cancelPage();"><span
				class="back">Back</span></a></div>
			</td>
		</tr>
	</table>
	</div>
	</div>
		<br />
		
		<input type="hidden" name="hmode" />
			<input type="hidden" name="strPath" value="${hospitalWorkshopDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${hospitalWorkshopDeskFB.strChk}">
			<input type="hidden" name="strEscDtl" id="strEscDtl" value="">
			<input type="hidden" name="strReqType" id="strReqType" value="${hospitalWorkshopDeskFB.strReqType}">
			<input type="hidden" name="strComplaintStatus" id="strComplaintStatus" value="${hospitalWorkshopDeskFB.strComplaintStatus}">
		
		<html:hidden name="hospitalWorkshopDeskFB" property="strComplaintId" />
		<html:hidden name="hospitalWorkshopDeskFB" property="strHemDesk" />
	<cmbPers:cmbPers />
</html:form>
</body>
</html>