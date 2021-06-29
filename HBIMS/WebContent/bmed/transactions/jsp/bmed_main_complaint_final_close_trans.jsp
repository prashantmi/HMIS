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
<his:javascript
	src="/bmed/transactions/js/bmed_HemComplaintApp_Desk_trans.js" />
</head>
<body marginheight="0" marginwidth="0" onload="chkRecordSaved()" class="background">
<html:form name="hemComplaintAppDeskFB"
	action="/transactions/HemComplaintApprovalDeskACTION"
	type="bmed.transactions.controller.fb.HemComplaintApprovalDeskFB" styleClass="formbg">
	
		<div style="display: none;"><his:TitleTag  name="Complaint Final Close">
		</his:TitleTag></div>
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="hemComplaintAppDeskFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="hemComplaintAppDeskFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV" style="display: none;"><bean:write
			name="hemComplaintAppDeskFB" property="strNormalMsg" /></div>
		
		<table class="TABLEWIDTH" align="center" cellpadding="1px"
				cellspacing="1px">
				<tr class="HEADER">
					<td colspan="4">Complaint Final Closure Approval</td>
				</tr>
			</table>
			
			<table class="TABLEWIDTH" align="center" cellpadding="1px"
				cellspacing="1px">
				<tr class="HEADER">
					<td colspan="4">The following Complaint Closure details are forwarded for your kind approval.</td>
				</tr>
			<tr>
			<td width="25%" class="LABEL" ><font color="red">*</font>Approved</td>
			<td width="25%" class="CONTROL">
			   <input type="radio" name=strIsApproved onClick="" value="1" />Yes
			</td>

					<td width="25%" class="LABEL" >Remarks</td>
					<td width="25%" class="CONTROL" ><textarea rows="2" cols="20" name="strRemarks" onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea> </td>
			</tr>
			     
			</table>
		<table class="TABLEWIDTH" align="center" cellpadding="1px"
				cellspacing="1px">
				<tr class="HEADER">
					<td colspan="4">Complaint Details</td>
				</tr>
			</table>
			<table class="TABLEWIDTH" align="center" cellpadding="1px"
				cellspacing="1px">
				<tr style="display: none;">
					<td width="25%" class="LABEL">Complaint Type</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strComplaintType}</td>
					<td width="25%" class="LABEL"></td>
					<td width="25%" class="CONTROL"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL">Complaint Id</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strComplaintId}</td>
					<td width="25%" class="LABEL">Complaint Date</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strComplaintDate}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL">Hospital Name</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strStoreName}</td>
					<td width="25%" class="LABEL">Lab Name</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strLabName}</td>
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL">Engineering Item Type</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strEnggItemType}</td>
					<td width="25%" class="LABEL">Engineering Item Sub Type</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strEnggItemSubType}</td>
				</tr>
				<tr>
					<td class="LABEL">Equipment Name</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strItemName}</td>
					<td class="LABEL">Equipment Model</td>
					<!--<td class="CONTROL">${hemComplaintAppDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
					<td class="CONTROL">${hemComplaintAppDeskFB.strItemModel}</td>
				</tr>
				<tr>
					<td class="LABEL">UIN</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strEquipmentUIDNo}</td>
					<td width="25%" class="LABEL">Equipment Serial No.</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strItemBatchNo}</td>
				</tr>
				<tr>
					<td class="LABEL">Manufacturer/Supplier Name</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strManufacturerName}</td>
					<td width="25%" class="LABEL">Complaint Description</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strComplaintDescription}</td>
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
			<td width="95%" style="text-align: left;">Guaranty Details</td>
		</tr>
	</table>
	</div>
			<table class="TABLEWIDTH" id="warrantyDetailsTable" style="display: none;">
				<bean:write name="hemComplaintAppDeskFB" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		
		<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('maintenanceContractDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Maintenance Contract Details</td>
		</tr>
	</table>
	</div>
	
		
			<table class="TABLEWIDTH" id="maintenanceContractDetailsTable"  style="display: none;">
				<bean:write name="hemComplaintAppDeskFB" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('acknowledgeDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Complaint Acknowledgment and Approval Details</td>
		</tr>
	</table>
	</div>
		
			<table class="TABLEWIDTH" id="acknowledgeDetailsTable" style="display: none;" >
			     <tr>
			<td width="25%" class="LABEL">Acknowledgment</td>
			<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIsAcknowledge}
			</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strRemarks}</td>
			
		</tr>
		<tr style="display: none;">
					<td width="25%" class="LABEL">Complaint Nature</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strComplaintNature}</td>
					<td width="25%" class="LABEL">Action Taken</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strActionTaken}</td>
			</tr>     
			<tr>
					<td width="25%" class="LABEL">Complaint Nature</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strComplaintNature}</td>
					<td width="25%" class="LABEL">Approved</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIsApproved}</td>
			</tr>
			     <tr>
					<td width="25%" class="LABEL" >Remarks</td>
					<td width="25%" class="CONTROL" >${hemComplaintAppDeskFB.strRemarks}</td>
					<td width="25%" class="LABEL" ></td>
					<td width="25%" class="CONTROL"></td>
				</tr>
		</table>
		
		
			<table class="TABLEWIDTH" style="display: none;">
			<tr>
			<td width="25%" class="LABEL">Acknowledgment</td>
			<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIsHEMAcknowledge}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strAckRemarks}</td>
			</tr>
			     
			</table>
		
		
		<%-- **************Complaint Schedule Details Start ***************** --%>
		<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('scheduleDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Complaint Schedule Details</td>
		</tr>
	</table>
	</div>
	
			<table class="TABLEWIDTH" id="scheduleDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL">Expected Visit Date
					</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strScheduleDate}</td>
					<td width="25%" class="LABEL">Expected Visit Time</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strScheduleTime}</td>
				</tr>
				<tr>
					<td class="LABEL">Complaint Redressal Details</td>
					<td class="CONTROL" colspan="3">${hemComplaintAppDeskFB.strServiceEngineerRemarks}</td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL">Intimation
					Date</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIntimationDate}</td>
					<td width="25%" class="LABEL">Intimation
					Time</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIntimationTime}</td>
				</tr>
				<tr>
					<td class="LABEL">Person Contacted</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strContactPerson}</td>
					<td class="LABEL">Contact No.</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strContactNo}</td>
				</tr>
				<tr>
					<td class="LABEL">Company Name and Address</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strCompanyName}</td>
					<td class="LABEL">Company e-mail Id</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strCompanyAddress}</td>
				</tr>
				<tr style="display: none;">
					<td class="LABEL">Vendor Id</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strVendorId}</td>
					<td class="LABEL">Communication
					Id</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strCommunicationId}</td>
				</tr>
				<tr>
				<td class="LABEL">Communication Medium</td>
				<td class="CONTROL">${hemComplaintAppDeskFB.strCommunicationId}</td>
				<td class="LABEL">Remarks</td>
				<td class="CONTROL">${hemComplaintAppDeskFB.strServiceProviderRemarks}</td>
					
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
			<td width="95%" style="text-align: left;">Complaint Attend Details</td>
		</tr>
	</table>
	</div>
	
			<table class="TABLEWIDTH" id="attendDetailsTable" style="display: none;">
				<tr style="display: none;">
					<td width="25%" class="LABEL">Service Engineer Name
					</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strEngineerName}</td>
					<td width="25%" class="LABEL">Service Engineer Address</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strEngineerAddress}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL">Service Engineer Name
					</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strEngineerName}</td>
					<td width="25%" class="LABEL">Mobile Number</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strMobileNo}</td>
				</tr>
				<tr style="display: none;">
					<td class="LABEL">Mobile Number</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strMobileNo}</td>
					<td class="CONTROL"></td>
					<td class="CONTROL"></td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL">Attend
					Date</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strAttendDate}</td>
					<td width="25%" class="LABEL">Attend
					Time</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strAttendTime}</td>
				</tr>
				<tr>
					<td class="LABEL">Actual Problem Description</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strActualProblemDesc}</td>
					<td class="LABEL">Remarks</td>
					<td class="CONTROL">${hemComplaintAppDeskFB.strAttendRemarks}</td>
				</tr>
				<tr style="display: none;">
				<td class="LABEL">Remarks</td>
				<td class="CONTROL">${hemComplaintAppDeskFB.strAttendRemarks}</td>
					<td class="LABEL"></td>
					<td class="CONTROL"></td>
				</tr>
			</table>
			
		
		
		<%--========================Complaint Attend Details End ========================= --%>
		
		<%--Action Taken Detail Starts --%>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="/HEMMS_ODISHA/hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('engineerDeskDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Complaint Action Taken Details</td>
		</tr>
	</table>
	</div>
			<table class="TABLEWIDTH" id="engineerDeskDetailsTable" style="display: none;">
			<tr style="display: none;">
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL">Vendor Name</td>
					<td width="25%" class="CONTROL">
					<div id="strVendorServiceEngId">${hemComplaintAppDeskFB.strVendorServiceEngName}</div>
					</td>
			
<!-- Communicate Id/Contact No -->	


	
			
				<td width="25%" class="LABEL">Communicate Id/Contact No</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strCommunicateIdContactId}</td>
			
			
	<!--	Attend Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL">Date of Attend</td>
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strServiceAttendDate}</td>					
					
						<td width="25%" class="LABEL">Time of Attend
						</td>
						
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strServiceAttendTime}</td>					
					</tr>		
			
			
			
			
			
				<!--	Closing Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL">Date of Closing</td>
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strServiceClosingDate}</td>					
					
						<td width="25%" class="LABEL">Time of Closing
						</td>
						
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strServiceClosingTime}</td>					
					</tr>		
			
			
			
			<!-- Is Spare Part Maintenance Involved -->
			
			<tr>
					<td width="25%" class="LABEL">
						Spare Parts Used
					</td>
					
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIsSparePartsMaintenanceInvolved}</td>
					
					<td width="25%" class="CONTROL">	
						
					</td>
					
					
					<td width="25%" class="CONTROL"></td>
				</tr>
			
		<logic:equal name='hemComplaintAppDeskFB' property='strIsSparePartsMaintenanceInvolved' value="Yes"> 
			<!-- Spare Part Multi-row begins here -->
			<tr><td colspan="4" width="100%">
			
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('sparePartDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Spare Part Details</td>
				</tr>
			</table>
		
			<table class="TABLEWIDTH" id="sparePartDetailsTable" style="display: none;">
				<bean:write name="hemComplaintAppDeskFB" property="strServiceActionSpareDetailsTable" filter="false"/>
			</table>
		
		</td></tr>	
		</logic:equal>	
			
<!--	Problem Description -->

				<tr>
					<td width="25%" class="LABEL">Problem Description</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strProblemDescription}</td>
					
					<td width="25%" class="LABEL">Solution Provided
					</td>
					
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strSolutionProvided}
					</td>
				</tr>			
				

<!--	Solution provided -->

				<tr style="display: none;">
					<td width="25%" class="LABEL">Solution Provided</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strSolutionProvided}
					</td>
					
					<td width="25%" class="CONTROL">
					
					</td>
					
					<td width="25%" class="CONTROL">
					</td>
				</tr>			
				
<!--	Remarks -->

				<tr>
					<td width="25%" class="LABEL">Remarks</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strServiceRemarks}</td>
					
					<td width="25%" class="CONTROL">
					</td>
					
					<td width="25%" class="CONTROL">
					</td>
				</tr>		
				
				
				
<!--	Reason for close -->

				<tr style="display: none;">
					<td width="25%" class="LABEL">Reason for Close</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strReasonForClosing}</td>
					
					<td width="25%" class="CONTROL">
					</td>
					
					<td width="25%" class="CONTROL">
					</td>
				</tr>		
				
	<!-- Is  Item In working Condition -->
			
			<tr>
					<td width="25%" class="LABEL">
						Is Item In Working Condition
					</td>
					
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIsItemInWorkingCondition}</td>
					
					<td width="25%" class="CONTROL"></td>
					
					
					<td width="25%" class="CONTROL"></td>
				</tr>
				
				
				<!--	From Date & Time	-->
			<logic:equal name='hemComplaintAppDeskFB' property='strIsItemInWorkingCondition' value="No">
					<tr >
						<td width="25%" class="LABEL">From Date</td>
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strFromDate}</td>					
					
						<td width="25%" class="LABEL">From Time
						</td>
						
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strFromTime}</td>					
					</tr>					
			</logic:equal>
			<!-- Total Cost-->
			
			<tr>
					<td width="25%" class="LABEL">
						Total Cost
					</td>
					
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strTotalCost}</td>
					
					<td width="25%" class="CONTROL">
					</td>
					<td width="25%" class="CONTROL"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL">Is Penalty</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIsPenality}</td>
					<td width="25%" class="LABEL"></td>
					<td width="25%" class="CONTROL"></td>
				</tr>
				<logic:equal name="hemComplaintAppDeskFB" property="strIsPenality" value="Yes">
				<tr>
					<td width="25%" class="LABEL">Penalty Charge</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strPenalityAmount}</td>
					<td width="25%" class="LABEL">Penalty Remarks</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strPenaltyRemarks}</td>
				</tr>
			</logic:equal>			
				<!-- Order No and Order Date-->
			
			<tr style="display: none;">
					<td width="25%" class="LABEL">
						Order No.
					</td>
					
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strOfficeOrderNo}</td>
					
					<td width="25%" class="LABEL">Order Date
					</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strOfficeOrderDt}</td>
				</tr>
				
				<!-- Net Cost-->
			
			<tr>
					<td width="25%" class="LABEL">
						Net Cost
					</td>
					
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strNetCost}</td>
					
					<td width="25%" class="CONTROL">
					</td>
					<td width="25%" class="CONTROL"></td>
				</tr>
<!--	Contact Person Name	&	Contact No.		-->

					<tr style="display: none;">
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL">VerfiedBy</td>
					<td width="25%" class="CONTROL">
					<div id="strVerifiedById">${hemComplaintAppDeskFB.strVerifiedBy}</div>
					</td>
					
					
					
					<td width="25%" class="CONTROL">	
						
					</td>
					
					
					<td width="25%" class="CONTROL"></td>
				</tr>
	
	
		<!--	Verfied  Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL">Date</td>
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strVerifyDate}</td>					
					
						<td width="25%" class="LABEL">Time 
						</td>
						
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strVerifyTime}</td>					
					</tr>					
				
		

			</table>
		
		<%--Action Taken Detail Ends--%>
		<%--Complaint Closing Detail Starts--%>
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
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strClosingDate}</td>
					<td width="25%" class="LABEL">Closing Time</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strClosingTime}</td>
				</tr>
				<tr>
					<td class="LABEL">Reason For Closing</td>
					<td class="CONTROL" colspan="3">${hemComplaintAppDeskFB.strHEMReasonForClosing}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL">Complaint Status</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strClosingComplaintStatus}</td>
					<logic:equal name="hemComplaintAppDeskFB" property="strClosingComplaintStatus" value="Closed">
					<td width="25%" class="LABEL"></td>
					<td width="25%" class="CONTROL"></td>
					</logic:equal>
					<logic:equal name="hemComplaintAppDeskFB" property="strClosingComplaintStatus" value="Pending">
					<td width="25%" class="LABEL">Reason For Pendency</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strPendencyRemarks}</td>
					</logic:equal>
				</tr>
				<tr>
					<td width="25%" class="LABEL">Total Cost Involved</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strHEMTotalCost}</td>
					<td width="25%" class="LABEL"></td>
					<td width="25%" class="CONTROL"></td>
					
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL">Vendor Invoice No.</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strHEMVendorId}</td>
					<td width="25%" class="LABEL">Total Cost Involved</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strHEMTotalCost}</td>
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL">Is Item In Working Condition
					</td>
					
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strIsHEMItemInWorkingCondition}</td>
					
					<td width="25%" class="CONTROL"></td>
					<td width="25%" class="CONTROL"></td>
				</tr>
					<tr style="display: none;">
						<td width="25%" class="LABEL">Total DownTime From Date</td>
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strDownFromDate}</td>
						<td width="25%" class="LABEL">Total DownTime To Date
						</td>
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strDownToDate}</td>					
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
						<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strDownTotalTime}</td>					
					
						<td width="25%" class="LABEL"></td>
						
						<td width="25%" class="CONTROL"></td>					
					</tr>	
				<tr style="display: none;">
			
			<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL">Verfied By</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strHEMVerifiedBy}</td>
					
					<td width="25%" class="LABEL">Remarks</td>
					<td width="25%" class="CONTROL">${hemComplaintAppDeskFB.strHEMClosingRemarks}</td>
				</tr>
			</table>
			
		
		
		<%--Hem Close Detail Ends--%>
		<!--<div class="control_button">
		<table class="TABLEWIDTH" align="center">
			<tr>
				<td align="center">
				<div><a href="#" class="button" onClick="return validate3();"><span
					class="save">Save</span></a> <a href="#" class="button"
					onclick="ClearPage();"><span class="clear">Clear</span></a>
				<a href="#" class="button" onClick="cancelPage();"><span
					class="back">Back</span></a></div>
				</td>
			</tr>
		</table>
		</div>  -->
		
		
		<table border="0" class="TABLEWIDTH" align="center">
		
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="return validate3();" />  <img
				src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer; cursor: hand;" title="Clear Process"
				onclick="clearPage();" /> <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();" /></td>
		</tr>
	</table>
		
		
		<!-- Error Messages 	-->
		<br />
		
		<input type="hidden" name="hmode" />
			<input type="hidden" name="strPath" value="${hemComplaintAppDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${hemComplaintAppDeskFB.strChk}">
			<input type="hidden" name="strDeptId" value="${hemComplaintAppDeskFB.strDeptId}">
			<input type="hidden" name="strComplaintStatus" value="${hemComplaintAppDeskFB.strComplaintStatus}">
			
			<input type="hidden" name="strStatus">
		<input type="hidden" name="strRetValue" value="${hemComplaintAppDeskFB.strRetValue}" />
			<input type="hidden" name=strMsgString value="${hemComplaintAppDeskFB.strMsgString}" />
		<html:hidden name="hemComplaintAppDeskFB" property="strComplaintId" />
	<cmbPers:cmbPers />
</html:form>
</body>
</html>