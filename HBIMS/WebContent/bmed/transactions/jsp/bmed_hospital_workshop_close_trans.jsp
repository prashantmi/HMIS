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
 * @author Anshuman Singh
 * Date of Creation : 13-May-2014
 * Date of Modification :  
 * Version : 
 * Module  : HEMMS Product 1.0 [Hospital Workshop Desk]
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
<body marginheight="0" marginwidth="0" onload="chkRecordSaved()">
<html:form name="hospitalWorkshopDeskFB"
	action="/transactions/HospitalWorkshopDeskACTION"
	type="bmed.transactions.controller.fb.HospitalWorkshopDeskFB">
	
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Closing">
		</his:TitleTag>
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="hospitalWorkshopDeskFB" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="hospitalWorkshopDeskFB" property="strWarningMsg" /></div>
		<div style="display:none;" id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="hospitalWorkshopDeskFB" property="strNormalMsg" /></div>
		<his:SubTitleTag name="Request/Complaint Details">
		</his:SubTitleTag>

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Complaint Type</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strComplaintType}</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Id</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strComplaintId}</td>
					<td width="25%" class="LABEL_TD">Complaint Date</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strComplaintDate}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Hospital Name</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strStoreName}</td>
					<td width="25%" class="LABEL_TD">Lab Name</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strLabName}</td>
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Engineering Item Type</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strEnggItemType}</td>
					<td width="25%" class="LABEL_TD">Engineering Item Sub Type</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strEnggItemSubType}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Equipment Name</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strItemName}</td>
					<td class="LABEL_TD">Equipment Model</td>
					<!--<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strItemModel}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">UIN</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strEquipmentUIDNo}</td>
					<td width="25%" class="LABEL_TD">Equipment Serial No.</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strItemBatchNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Manufacturer/Supplier Name</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strManufacturerName}</td>
					<td width="25%" class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD" rowspan="2">${hospitalWorkshopDeskFB.strComplaintDescription}</td>
				</tr>
				<tr style="display: none;">
					<td class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD" colspan="3">${hospitalWorkshopDeskFB.strComplaintDescription}</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/DWH/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('warrantyDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Guarantee Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Guarantee Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable" style="display: none;">
				<bean:write name="hospitalWorkshopDeskFB" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/DWH/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('maintenanceContractDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Maintenance Contract Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable"  style="display: none;">
				<bean:write name="hospitalWorkshopDeskFB" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/DWH/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('acknowledgeDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Acknowledgment and Approval Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Complaint Acknowledgment">
			<table class="TABLE_STYLE" id="acknowledgeDetailsTable" style="display: none;" >
			     <tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIsAcknowledge}
			</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strRemarks}</td>
			
		</tr>
		<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Complaint Nature</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strComplaintNature}</td>
					<td width="25%" class="LABEL_TD">Action Taken</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strActionTaken}</td>
			</tr>   
			<tr>
					<td width="25%" class="LABEL_TD">Complaint Nature</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strComplaintNature}</td>
					<td width="25%" class="LABEL_TD">Approved</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIsApproved}</td>
			</tr>   
			     <tr style="display: none;">
			<td width="25%" class="LABEL_TD" >Approved</td>
			<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIsApproved}</td>

					<td width="25%" class="LABEL_TD" >Remarks</td>
					<td width="25%" class="CONTROL_TD" >${hospitalWorkshopDeskFB.strRemarks}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD" >Remarks</td>
					<td width="25%" class="CONTROL_TD" >${hospitalWorkshopDeskFB.strRemarks}</td>
					<td width="25%" class="LABEL_TD" ></td>
					<td width="25%" class="CONTROL_TD"></td>
					
				</tr>
		</table>
		</his:ContentTag>
		<his:ContentTag name="HEM Complaint Acknowledgment">
			<table class="TABLE_STYLE" style="display: none;">
			<tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIsHEMAcknowledge}</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strAckRemarks}</td>
			</tr>
			     
			</table>
		</his:ContentTag>	
		
		
		<%-- **************Complaint Schedule Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/DWH/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('scheduleDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Schedule Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Complaint Schedule Details">
			<table class="TABLE_STYLE" id="scheduleDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL_TD">Expected Visit Date
					</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strScheduleDate}</td>
					<td width="25%" class="LABEL_TD">Expected Visit Time</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strScheduleTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Complaint Redressal Details</td>
					<td class="CONTROL_TD" colspan="3">${hospitalWorkshopDeskFB.strServiceEngineerRemarks}</td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Intimation
					Date</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIntimationDate}</td>
					<td width="25%" class="LABEL_TD">Intimation
					Time</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIntimationTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Person Contacted</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strContactPerson}</td>
					<td class="LABEL_TD">Contact No.</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strContactNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Company Name and Address</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strCompanyName}</td>
					<td class="LABEL_TD">Company e-mail Id</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strCompanyAddress}</td>
				</tr>
				<tr style="display: none;">
					<td class="LABEL_TD">Vendor Id</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strVendorId}</td>
					<td class="LABEL_TD">Communication
					Id</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strCommunicationId}</td>
				</tr>
				<tr>
				<td class="LABEL_TD">Communication Medium</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strCommunicationId}</td>
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strServiceProviderRemarks}</td>
					
				</tr>
			</table>
		</his:ContentTag> 
		<%--======================== Schedule Details End ========================= --%>
		<%-- **************Complaint Attend Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/DWH/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('attendDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Attend Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag  name="Complaint Attend Details">
		
			<table class="TABLE_STYLE" id="attendDetailsTable" style="display: none;">
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Service Engineer Name
					</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strEngineerName}</td>
					<td width="25%" class="LABEL_TD">Service Engineer Address</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strEngineerAddress}</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Service Engineer Name
					</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strEngineerName}</td>
					<td width="25%" class="LABEL_TD">Mobile Number</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strMobileNo}</td>
				</tr>
				<tr style="display: none;">
					<td class="LABEL_TD">Mobile Number</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strMobileNo}</td>
					<td class="CONTROL_TD"></td>
					<td class="CONTROL_TD"></td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Attend
					Date</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strAttendDate}</td>
					<td width="25%" class="LABEL_TD">Attend
					Time</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strAttendTime}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Actual Problem Description</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strActualProblemDesc}</td>
					<td class="LABEL_TD">Remarks</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strAttendRemarks}</td>
				</tr>
				<tr style="display: none;">
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strAttendRemarks}</td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
			</table>
			
		</his:ContentTag> 
		
		<%--======================== Attend Details End ========================= --%>
		
		<%--Action Taken Detail Starts --%>
		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/DWH/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('engineerDeskDetailsTable',this);"></td>
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
					<div id="strVendorServiceEngId">${hospitalWorkshopDeskFB.strVendorServiceEngName}</div>
					</td>
			
<!-- Communicate Id/Contact No -->	


	
			
				<td width="25%" class="LABEL_TD">Communicate Id/Contact No</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strCommunicateIdContactId}</td>
			
			
	<!--	Attend Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL_TD">Date of Attend</td>
						<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strServiceAttendDate}</td>					
					
						<td width="25%" class="LABEL_TD">Time of Attend
						</td>
						
						<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strServiceAttendTime}</td>					
					</tr>		
			
			
			
			
			
				<!--	Closing Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL_TD">Date of Closing</td>
						<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strServiceClosingDate}</td>					
					
						<td width="25%" class="LABEL_TD">Time of Closing
						</td>
						
						<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strServiceClosingTime}</td>					
					</tr>		
			
			
			
			<!-- Is Spare Part Maintenance Involved -->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						Spare Parts Used
					</td>
					
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIsSparePartsMaintenanceInvolved}</td>
					
					<td width="25%" class="LABEL_TD">	
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
			
		<logic:equal name='hospitalWorkshopDeskFB' property='strIsSparePartsMaintenanceInvolved' value="Yes"> 
			<!-- Spare Part Multi-row begins here -->
			<tr><td colspan="4" width="100%">
			<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/DWH/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('sparePartDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Spare Part Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Spare Part Details">
			<table class="TABLE_STYLE" id="sparePartDetailsTable" style="display: none;">
				<bean:write name="hospitalWorkshopDeskFB" property="strServiceActionSpareDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
		</td></tr>	
		</logic:equal>	
			
<!--	Problem Description -->

				<tr>
					<td width="25%" class="LABEL_TD">Problem Description</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strProblemDescription}</td>
					
					<td width="25%" class="LABEL_TD">Solution Provided
					</td>
					
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strSolutionProvided}
					</td>
				</tr>			
				

<!--	Solution provided -->

				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Solution Provided</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strSolutionProvided}
					</td>
					
					<td width="25%" class="CONTROL_TD">
					
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>			
				
<!--	Remarks -->

				<tr>
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strServiceRemarks}</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>		
				
				
				
<!--	Reason for close -->

				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">Reason for Close</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strReasonForClosing}</td>
					
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
					
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIsItemInWorkingCondition}</td>
					
					<td width="25%" class="CONTROL_TD"></td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				
				
				<!--	From Date & Time	-->
			<logic:equal name="hospitalWorkshopDeskFB" property="strIsItemInWorkingCondition" value="No">
					<tr >
						<td width="25%" class="LABEL_TD">From Date</td>
						<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strFromDate}</td>					
					
						<td width="25%" class="LABEL_TD">From Time
						</td>
						
						<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strFromTime}</td>					
					</tr>					
			</logic:equal>
			<!-- Total Cost-->
			
			<tr>
					<td width="25%" class="LABEL_TD">Other Charges
					</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strOtherCharges}</td>
					
					<td width="25%" class="LABEL_TD">
						Total Cost
					</td>
					
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strTotalCost}</td>
					
					
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Penal Charges</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIsPenality}</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
			<logic:equal name="hospitalWorkshopDeskFB" property="strIsPenality" value="Yes">
				<tr>
					<td width="25%" class="LABEL_TD">Penal Charge</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strPenalityAmount}</td>
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strPenaltyRemarks}</td>
				</tr>
			</logic:equal>		
			<!-- Order No and Order Date-->
			
			<tr style="display: none;">
					<td width="25%" class="LABEL_TD">
						Order No.
					</td>
					
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strOfficeOrderNo}</td>
					
					<td width="25%" class="LABEL_TD">Order Date
					</td>
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strOfficeOrderDt}</td>
				</tr>
				
				<!-- Net Cost-->
			
			<tr style="display: none;">
					<td width="25%" class="LABEL_TD">
						Net Cost
					</td>
					
					<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strNetCost}</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
<!--	Contact Person Name	&	Contact No.		-->

					<tr style="display: none;">
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD">VerfiedBy</td>
					<td width="25%" class="CONTROL_TD">
					<div id="strVerifiedById">${hospitalWorkshopDeskFB.strVerifiedBy}</div>
					</td>
					
					
					
					<td width="25%" class="CONTROL_TD">	
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
	
	
		<!--	Verfied  Date & Time	-->

					<tr style="display: none;">
						<td width="25%" class="LABEL_TD">Date</td>
						<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strVerifyDate}</td>					
					
						<td width="25%" class="LABEL_TD">Time 
						</td>
						
						<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strVerifyTime}</td>					
					</tr>					
				
		

			</table>
		</his:ContentTag>
		<%--Engineer Service Action Detail Ends--%>
		<%--Hem Close Detail Starts--%>
		<%-- ************** Attend Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="100%" style="text-align: left;" >Complaint Closing Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag>
		
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Closing Date</td>
					<td width="25%" class="CONTROL_TD"><his:date
						name="strClosingDate" value=""></his:date></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Closing Time</td>
					<td width="25%" class="CONTROL_TD"><input type="text" style="display: none;"
						name="strClosingTimeOld" class="TEXT_FIELD_MIN" maxlength="5"
						onchange="return validateDataWithSpecialChars(event,5,':');"
						onkeypress='return checkTime(event,this);'">
					<select name="strClosingTime" id="strClosingTime" class="COMBO_MIN" onchange="calculateTotalDownTime();">
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
					  	</select>
					  	<select class="COMBO_SMALL" name="strScheduleTimeMeridian" id="strScheduleTimeMeridian" onchange="calculateTotalDownTime();"><OPTION value="AM">AM</OPTION><OPTION value="PM">PM</OPTION></select>
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Reason For Closing</td>
					<td class="CONTROL_TD"><textarea rows="2" cols="25" onblur="calculateTotalDownTime();"
						name="strHEMReasonForClosing"
						onkeypress="return validateDataWithSpecialChars(event,9,'.,,');"
						onchange="return validateDataWithSpecialChars(event,9,'.,,');"></textarea>
					</td>
					<td class="CONTROL_TD"></td>	
					<td class="CONTROL_TD">
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Complaint Status</td>	
					<td class="CONTROL_TD">
					<select name="strClosingComplaintStatus" class="COMBO_MIN" onchange="showHidePendencyRemarks();">
					<OPTION value="C">Close</OPTION><OPTION value="P">Pending</OPTION>
					</select>
					</td>
					<td class="LABEL_TD"><div id="PendencyRemarksRowId" class="LABEL_TD" style="display: none;"><font color="red">*</font>Reason For Pendency
					</div></td>
					<td class="CONTROL_TD"><div id="PendencyRemarksRowIdDefault" style="display: none;"><textarea rows="1" cols="25"
						name="strPendencyRemarks"
						onkeypress="return validateDataWithSpecialChars(event,9,'.,,');"
						onchange="return validateDataWithSpecialChars(event,9,'.,,');"></textarea>
					</div></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Vendor Invoice No.</td>
					<td width="25%" class="CONTROL_TD">
					<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strHEMVendorId" onkeypress="return validateData(event,2);">
					</td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Total Cost Involved</td>
					<td width="25%" class="CONTROL_TD">
					<input type="text" class="TEXT_FIELD_MAX" maxlength="10" value="${hospitalWorkshopDeskFB.strTotalCost}" name="strHEMTotalCost" onkeypress="return validateData(event,7);">
					</td>
				</tr>
				<tr style="display: none;">
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Is Item In Working Condition
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<html:radio name="hospitalWorkshopDeskFB" property="strIsHEMItemInWorkingCondition" value="0" onclick=""/> Yes
					</td>
					
					<td width="25%" class="CONTROL_TD">	
						<html:radio name="hospitalWorkshopDeskFB" property="strIsHEMItemInWorkingCondition" value="1" onclick="" /> NO
					</td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
					<tr style="display: none;">
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Total DownTime From Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strDownFromDate" value="${hospitalWorkshopDeskFB.strDownFromDate}"></dateTag:date>
						</td>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Total DownTime To Date
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strDownToDate" value="${hospitalWorkshopDeskFB.strDownToDate}"></dateTag:date>					
						</td>					
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
				--><tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Total DownTime</td>
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" readonly="readonly" maxlength="5" name="strDownTotalTime" onkeyup="calculateTotalDownTime();" onkeypress='calculateTotalDownTime();' />
						</td>					
					
						<td width="25%" class="LABEL_TD"></td>
						
						<td width="25%" class="CONTROL_TD"></td>					
					</tr>	
				<tr style="display: none;">
			
			<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD"><font color="red">*</font>Verfied By</td>
					<td width="25%" class="CONTROL_TD">
					<div id="strHEMVerifiedById">
						<select name="strHEMVerifiedBy" class='COMBO_NORMAL' onchange=""  >
									<bean:write name="hospitalWorkshopDeskFB" property="strVerifiedByOptions" filter="false" />
								</select>
								</div>
								<html:hidden property="strVerifiedByOptions" value="${hospitalWorkshopDeskFB.strVerifiedByOptions}"/>
					</td>
					
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">
					<html:textarea name="hospitalWorkshopDeskFB" property="strHEMClosingRemarks" cols="25" rows="2"/></td>
				</tr>
			</table>
			
		</his:ContentTag> 
		<%--Hem Close Detail Ends--%>
		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			<img style="cursor: pointer;"
				src="/DWH/hisglobal/images/btn-sv.png"
				onClick="return validate2();" />
			<img style="cursor: pointer;"
				src="/DWH/hisglobal/images/btn-clr.png" onClick="clearPage();">
			<img style="cursor: pointer;"
				src="/DWH/hisglobal/images/btn-ccl.png" onClick="cancelPage();">
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		
		<input type="hidden" name="hmode" />
			<input type="hidden" name="strPath" value="${hospitalWorkshopDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${hospitalWorkshopDeskFB.strChk}">
			<input type="hidden" name="strEscDtl" id="strEscDtl" value="">
			<input type="hidden" name="strReqType" id="strReqType" value="${hospitalWorkshopDeskFB.strReqType}">
			<input type="hidden" name="strComplaintStatus" id="strComplaintStatus" value="${hospitalWorkshopDeskFB.strComplaintStatus}">
			<input type="hidden" name="strAttendDate" id="strAttendDate" value="${hospitalWorkshopDeskFB.strAttendDate}">
			<input type="hidden" name="strComplaintDate" id="strComplaintDate" value="${hospitalWorkshopDeskFB.strComplaintDate}">
		<input type="hidden" name="strRetValue" value="${hospitalWorkshopDeskFB.strRetValue}" />
		<input type="hidden" name="strMsgString" value="${hospitalWorkshopDeskFB.strMsgString}" />
		
		<html:hidden name="hospitalWorkshopDeskFB" property="strComplaintId" />
		<html:hidden name="hospitalWorkshopDeskFB" property="strHemDesk" />
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>