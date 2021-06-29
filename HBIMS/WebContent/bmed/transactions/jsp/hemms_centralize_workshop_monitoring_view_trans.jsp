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
 * Date of Modification :  
 * Version : 
 * Module  : HEMMS Product 1.0 [HEMM Desk]
 */
 -->
<html>
<head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/time.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript	src="/bmed/transactions/js/bmed_HemDesk_trans.js" />
</head>
<body marginheight="0" marginwidth="0">
<html:form name="centralizeWorkshopMonitoringDeskFB"
	action="/transactions/CentralizeWorkshopMonitoringDeskACTION"
	type="bmed.transactions.controller.fb.CentralizeWorkshopMonitoringDeskFB">
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Detail View">
		</his:TitleTag>
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="centralizeWorkshopMonitoringDeskFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="centralizeWorkshopMonitoringDeskFB" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="centralizeWorkshopMonitoringDeskFB" property="strNormalMsg" /></div>
		<his:SubTitleTag name="Request/Complaint Details">
		</his:SubTitleTag>

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Complaint Type</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strComplaintType}</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Id</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strComplaintId}</td>
					<td width="25%" class="LABEL_TD">Complaint Date</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strComplaintDate}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Hospital Name</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strStoreName}</td>
					<td width="25%" class="LABEL_TD">Lab Name</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strLabName}</td>
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Engineering Item Type</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strEnggItemType}</td>
					<td width="25%" class="LABEL_TD">Engineering Item Sub Type</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strEnggItemSubType}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Equipment Name</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strItemName}</td>
					<td class="LABEL_TD">Equipment Model</td>
					<!--<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strItemModel}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">UIN</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strEquipmentUIDNo}</td>
					<td width="25%" class="LABEL_TD">Equipment Serial No.</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strItemBatchNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Manufacturer/Supplier Name</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strManufacturerName}</td>
					<td width="25%" class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strComplaintDescription}</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('warrantyDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Guarantee Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Guarantee Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable" style="display: none;">
				<bean:write name="centralizeWorkshopMonitoringDeskFB" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('maintenanceContractDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Maintenance Contract Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable"  style="display: none;">
				<bean:write name="centralizeWorkshopMonitoringDeskFB" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('acknowledgeDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Acknowledgment and Approval Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Complaint Acknowledgment">
			<table class="TABLE_STYLE" id="acknowledgeDetailsTable" style="display: none;" >
			     <tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIsAcknowledge}
			</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strRemarks}</td>
			
		</tr>
		<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Complaint Nature</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strComplaintNature}</td>
					<td width="25%" class="LABEL_TD">Action Taken</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strActionTaken}</td>
			</tr>     
			<tr>
					<td width="25%" class="LABEL_TD">Complaint Nature</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strComplaintNature}</td>
					<td width="25%" class="LABEL_TD">Approved</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIsApproved}</td>
			</tr>
			     <tr>
					<td width="25%" class="LABEL_TD" >Remarks</td>
					<td width="25%" class="CONTROL_TD" >${centralizeWorkshopMonitoringDeskFB.strRemarks}</td>
					<td width="25%" class="LABEL_TD" ></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
		</table>
		</his:ContentTag>
		<his:ContentTag name="HEM Complaint Acknowledgment">
			<table class="TABLE_STYLE" style="display: none;">
			<tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIsHEMAcknowledge}</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strAckRemarks}</td>
			</tr>
			     
			</table>
		</his:ContentTag>	
		
		
		<%-- **************Complaint Schedule Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('scheduleDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Schedule Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Complaint Schedule Details">
			<table class="TABLE_STYLE" id="scheduleDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL_TD">Expected Visit Date
					</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strScheduleDate}</td>
					<td width="25%" class="LABEL_TD">Expected Visit Time</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strScheduleTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Complaint Redressal Details</td>
					<td class="CONTROL_TD" colspan="3">${centralizeWorkshopMonitoringDeskFB.strServiceEngineerRemarks}</td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Intimation
					Date</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIntimationDate}</td>
					<td width="25%" class="LABEL_TD">Intimation
					Time</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIntimationTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Person Contacted</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strContactPerson}</td>
					<td class="LABEL_TD">Contact No.</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strContactNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Company Name and Address</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strCompanyName}</td>
					<td class="LABEL_TD">Company e-mail Id</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strCompanyAddress}</td>
				</tr>
				<tr style="display: none;">
					<td class="LABEL_TD">Vendor Id</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strVendorId}</td>
					<td class="LABEL_TD">Communication
					Id</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strCommunicationId}</td>
				</tr>
				<tr>
				<td class="LABEL_TD">Communication Medium</td>
				<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strCommunicationId}</td>
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strServiceProviderRemarks}</td>
					
				</tr>
			</table>
		</his:ContentTag> 
		<%--======================== Schedule Details End ========================= --%>
		<%-- ************** Attend Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('attendDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Attend Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag  name="Compalint Attend Details">
		
			<table class="TABLE_STYLE" id="attendDetailsTable" style="display: none;">
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Service Engineer Name
					</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strEngineerName}</td>
					<td width="25%" class="LABEL_TD">Service Engineer Address</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strEngineerAddress}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Service Engineer Name
					</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strEngineerName}</td>
					<td width="25%" class="LABEL_TD">Mobile Number</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strMobileNo}</td>
				</tr>
				<tr style="display: none;">
					<td class="LABEL_TD">Mobile Number</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strMobileNo}</td>
					<td class="CONTROL_TD"></td>
					<td class="CONTROL_TD"></td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Attend
					Date</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strAttendDate}</td>
					<td width="25%" class="LABEL_TD">Attend
					Time</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strAttendTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Actual Problem Description</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strActualProblemDesc}</td>
					<td class="LABEL_TD">Remarks</td>
					<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strAttendRemarks}</td>
				</tr>
				<tr style="display: none;">
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strAttendRemarks}</td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
			</table>
			
		</his:ContentTag> 
		
		<%--========================Complaint Attend Details End ========================= --%>
		
		<%--Action Taken Detail Starts --%>
		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('engineerDeskDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Action Taken Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		
		<his:ContentTag  name="Action Taken Details">
			<table class="TABLE_STYLE" id="engineerDeskDetailsTable" style="display: none;">
			<tr style="display: none;">
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD">Vendor Name</td>
					<td width="25%" class="CONTROL_TD">
					<div id="strVendorServiceEngId">${centralizeWorkshopMonitoringDeskFB.strVendorServiceEngName}</div>
					</td>
			
<!-- Communicate Id/Contact No -->	


	
			
				<td width="25%" class="LABEL_TD">Communicate Id/Contact No</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strCommunicateIdContactId}</td>
			
			
	<!--	Attend Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL_TD">Date of Attend</td>
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strServiceAttendDate}</td>					
					
						<td width="25%" class="LABEL_TD">Time of Attend
						</td>
						
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strServiceAttendTime}</td>					
					</tr>		
			
			
			
			
			
				<!--	Closing Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL_TD">Date of Closing</td>
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strServiceClosingDate}</td>					
					
						<td width="25%" class="LABEL_TD">Time of Closing
						</td>
						
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strServiceClosingTime}</td>					
					</tr>		
			
			
			
			<!-- Is Spare Part Maintenance Involved -->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						Spare Parts Used
					</td>
					
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIsSparePartsMaintenanceInvolved}</td>
					
					<td width="25%" class="CONTROL_TD">	
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
			
		<logic:equal name='centralizeWorkshopMonitoringDeskFB' property='strIsSparePartsMaintenanceInvolved' value="Yes"> 
			<!-- Spare Part Multi-row begins here -->
			<tr><td colspan="4" width="100%">
			<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('sparePartDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Spare Part Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Spare Part Details">
			<table class="TABLE_STYLE" id="sparePartDetailsTable" style="display: none;">
				<bean:write name="centralizeWorkshopMonitoringDeskFB" property="strServiceActionSpareDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
		</td></tr>	
		</logic:equal>	
			
<!--	Problem Description -->

				<tr>
					<td width="25%" class="LABEL_TD">Problem Description</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strProblemDescription}</td>
					
					<td width="25%" class="LABEL_TD">Solution Provided
					</td>
					
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strSolutionProvided}
					</td>
				</tr>			
				

<!--	Solution provided -->

				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Solution Provided</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strSolutionProvided}
					</td>
					
					<td width="25%" class="CONTROL_TD">
					
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>			
				
<!--	Remarks -->

				<tr>
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strServiceRemarks}</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>		
				
				
				
<!--	Reason for close -->

				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Reason for Close</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strReasonForClosing}</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>		
				
	<!-- Is  Item In working Condition -->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						Is Item In Working Condition
					</td>
					
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIsItemInWorkingCondition}</td>
					
					<td width="25%" class="CONTROL_TD"></td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				
				
				<!--	From Date & Time	-->
			<logic:equal name='centralizeWorkshopMonitoringDeskFB' property='strIsItemInWorkingCondition' value="No">
					<tr >
						<td width="25%" class="LABEL_TD">From Date</td>
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strFromDate}</td>					
					
						<td width="25%" class="LABEL_TD">From Time
						</td>
						
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strFromTime}</td>					
					</tr>					
			</logic:equal>
			<!-- Total Cost-->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						Total Cost
					</td>
					
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strTotalCost}</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Penal Charges</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIsPenality}</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				<logic:equal name="centralizeWorkshopMonitoringDeskFB" property="strIsPenality" value="Yes">
				<tr>
					<td width="25%" class="LABEL_TD">Penal Charge</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strPenalityAmount}</td>
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strPenaltyRemarks}</td>
				</tr>
			</logic:equal>			
				<!-- Order No and Order Date-->
			
			<tr style="display: none;">
					<td width="25%" class="LABEL_TD">
						Order No.
					</td>
					
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strOfficeOrderNo}</td>
					
					<td width="25%" class="LABEL_TD">Order Date
					</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strOfficeOrderDt}</td>
				</tr>
				
				<!-- Net Cost-->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						Net Cost
					</td>
					
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strNetCost}</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
<!--	Contact Person Name	&	Contact No.		-->

					<tr style="display: none;">
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD">VerfiedBy</td>
					<td width="25%" class="CONTROL_TD">
					<div id="strVerifiedById">${centralizeWorkshopMonitoringDeskFB.strVerifiedBy}</div>
					</td>
					
					
					
					<td width="25%" class="CONTROL_TD">	
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
	
	
		<!--	Verfied  Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL_TD">Date</td>
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strVerifyDate}</td>					
					
						<td width="25%" class="LABEL_TD">Time 
						</td>
						
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strVerifyTime}</td>					
					</tr>					
				
		

			</table>
		</his:ContentTag>
		<%--Action Taken Detail Ends--%>
		<%--Complaint Closing Detail Starts--%>
		<%-- **************Complaint Closing Details Start ***************** --%>
		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('closingDeskDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Closing Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		
		<his:ContentTag  name="Complaint Closing Details">
			<table class="TABLE_STYLE" id="closingDeskDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL_TD">Closing Date</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strClosingDate}</td>
					<td width="25%" class="LABEL_TD">Closing Time</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strClosingTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Reason For Closing</td>
					<td class="CONTROL_TD" colspan="3">${centralizeWorkshopMonitoringDeskFB.strHEMReasonForClosing}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Status</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strClosingComplaintStatus}</td>
					<logic:equal name="centralizeWorkshopMonitoringDeskFB" property="strClosingComplaintStatus" value="Closed">
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
					</logic:equal>
					<logic:equal name="centralizeWorkshopMonitoringDeskFB" property="strClosingComplaintStatus" value="Pending">
					<td width="25%" class="LABEL_TD">Reason For Pendency</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strPendencyRemarks}</td>
					</logic:equal>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Total Cost Involved</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strHEMTotalCost}</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
					
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Vendor Invoice No.</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strHEMVendorId}</td>
					<td width="25%" class="LABEL_TD">Total Cost Involved</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strHEMTotalCost}</td>
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Is Item In Working Condition
					</td>
					
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strIsHEMItemInWorkingCondition}</td>
					
					<td width="25%" class="CONTROL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
					<tr style="display: none;">
						<td width="25%" class="LABEL_TD">Total DownTime From Date</td>
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strDownFromDate}</td>
						<td width="25%" class="LABEL_TD">Total DownTime To Date
						</td>
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strDownToDate}</td>					
					</tr>	
					
				<!--<tr>
					<td class="LABEL_TD"><font color="red">*</font>Actual Problem Description</td>
					<td class="CONTROL_TD"><textarea rows="2" cols="25"
						name="strHEMActualProblemDesc"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
				-->
				<tr >
						<td width="25%" class="LABEL_TD">Total DownTime</td>
						<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strDownTotalTime}</td>					
					
						<td width="25%" class="LABEL_TD"></td>
						
						<td width="25%" class="CONTROL_TD"></td>					
					</tr>	
				<tr style="display: none;">
			
			<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD">Verfied By</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strHEMVerifiedBy}</td>
					
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">${centralizeWorkshopMonitoringDeskFB.strHEMClosingRemarks}</td>
				</tr>
			</table>
			
		</his:ContentTag> 
		<%--Hem Close Detail Ends--%>
		<his:ButtonToolBarTag>
			<img style="cursor: pointer;"
				src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();">
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		
		<input type="hidden" name="hmode" />
			<input type="hidden" name="strPath" value="${centralizeWorkshopMonitoringDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${centralizeWorkshopMonitoringDeskFB.strChk}">
			<input type="hidden" name="strEscDtl" id="strEscDtl" value="">
			<input type="hidden" name="strReqType" id="strReqType" value="${centralizeWorkshopMonitoringDeskFB.strReqType}">
			<input type="hidden" name="strComplaintStatus" id="strComplaintStatus" value="${centralizeWorkshopMonitoringDeskFB.strComplaintStatus}">
		
		<html:hidden name="centralizeWorkshopMonitoringDeskFB" property="strComplaintId" />
		<html:hidden name="centralizeWorkshopMonitoringDeskFB" property="strCentralizeWorkshopMonitoringDesk" />
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>