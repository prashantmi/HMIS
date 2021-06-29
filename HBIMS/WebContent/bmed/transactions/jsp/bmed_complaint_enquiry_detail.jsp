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
<title>Equipment Complaint Detail Desk</title>
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

<script language="JavaScript"src="../../hisglobal/transactionutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<!-- <script language="Javascript"src="../../bmed/js/complaintHierarchyMst.js"></script> -->
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../bmed/js/CentralEquipEnquiryDesk.js"></script>

<!-- 
/**
 * BY shefali
 * Date of Creation : 16/08/2013
 * Date of Modification :  /  / 
 * Version : 1.0
 * Module  : HEMM Product
 * Product For : Rajasthan
 */
 -->
</head>
<body>
<html:form action="/transactions/CentralEquipEnquiryDeskCNT"
	name="stEquipInstallDeskName"
	type="bmed.transactions.controller.fb.CentralEquipEnquiryDeskFB">

	<center>
	<div id="errMsg" class="errMsg"><bean:write
		name="CentralEquipEnquiryDeskFB" property="strErr" /></div>
	<div id="warningMsg" class="warningMsg"><bean:write
		name="CentralEquipEnquiryDeskFB" property="strWarning" /></div>
	<div id="normalMsg" class="normalMsg"><bean:write
		name="CentralEquipEnquiryDeskFB" property="strMsgString" /></div>


	<tag:tab tabLabel="Equipment Complaint detail Enquiry Desk" selectedTab="FIRST"
		align="center"  width="TABLEWIDTH">
	</tag:tab></center>

	<table class="TABLEWIDTH" align="center" width="100%">
		<tr class="HEADER">
			<td colspan="4">Request/Complaint Detail &gt;&gt;</td></tr>

	</table>
		<table border="0"  align="center" border='0'
		cellspacing='1px' cellpadding='1px' width="100%">

		
		<tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Complaint Type</td>
			<td colspan="1" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplainttype}</td>
			<td colspan="1" class="LABEL" style="text-align:right;"></td>
			<td colspan="1" class="CONTROL"></td>
			
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Complaint Id</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintId}
			</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Complaint Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintDate}
			</td>
		</tr>
		<tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Complaint Description</td>
			<td colspan="3" class="CONTROL" style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintDescript}
			</td>
		</tr>

		<tr class="HEADER">
			<td colspan="4">Complaint Acknowledgment and Approval Details &gt;&gt;</td></tr>

		<tr>	
			<td colspan="1" class="LABEL" style="text-align:right;">Acknowledgment</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintAcknow}</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Remarks</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintAcknowRemarks}</td>
		</tr>
	
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Complaint Nature</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintNature}
			</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Action Taken</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strcomplaintAction}
			</td>
					
		</tr>
		
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Approved</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintApproved}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Remarks</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintAppRemarks}
			</td>	
		</tr>
	
				<tr class="HEADER">
			<td colspan="4">Complaint Schedule Details &gt;&gt;</td></tr>

		<tr>	
			<td colspan="1" class="LABEL" style="text-align:right;">Expected Date Scheduled</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintScheduledate}</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Schedule Time</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintScheduleTime}</td>
		</tr>
	
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Service Engineer Remark</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strServiceEngineerRemark}
			</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Intimation Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strcomplaintIntimationdate}
			</td>
					
		</tr>
		
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Contact Person</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintContactPerson}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Contact No.</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintContactNo}
			</td>	
		</tr>
		  <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Company Name</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strcompanyName}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Company Address</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strCompanyAddress}
			</td>	
		</tr>
	  <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Vendor Id</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strVendorId}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Communication Id</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strCommunicationId}
			</td>	
		</tr>
		<tr>
		<td colspan="1" class="LABEL" style="text-align:right;">Remarks</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintScheduleRemarks}
			</td>	
			<td colspan="1" class="LABEL" style="text-align:right;"></td>
			<td colspan="1" class="CONTROL"  style="text-align:left;"></td>	
	</tr>
		<tr class="HEADER">
			<td colspan="4">Complaint HEMM Attend Details &gt;&gt;</td></tr>

		<tr>	
			<td colspan="1" class="LABEL" style="text-align:right;">Service Engineer Name </td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintScheduledate}</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Service Engineer Address</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintScheduleTime}</td>
		</tr>
	
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Mobile Number</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strServiceEngineerRemark}
			</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Intimation Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strcomplaintIntimationdate}
			</td>
					
		</tr>
		
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Contact Person</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintContactPerson}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Contact No.</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintContactNo}
			</td>	
		</tr>
		  <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Company Name</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strcompanyName}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Company Address</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strCompanyAddress}
			</td>	
		</tr>
	  <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Vendor Id</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strVendorId}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Communication Id</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strCommunicationId}
			</td>	
		</tr>
		<tr>
		<td colspan="1" class="LABEL" style="text-align:right;">Remarks</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strComplaintScheduleRemarks}
			</td>	
			<td colspan="1" class="LABEL" style="text-align:right;"></td>
			<td colspan="1" class="CONTROL"  style="text-align:left;"></td>	
	</tr>
	
	<tr class="HEADER">
			<td colspan="4">Engineer Desk Details &gt;&gt;</td></tr>

		<tr>	
			<td colspan="1" class="LABEL" style="text-align:right;">Vendor Name </td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strVendorName}</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Communicate Id/Contact No 	</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strCommunicateId}</td>
		</tr>
	
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Date of Attend 	</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strDateofAttend}
			</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Time of Attend</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strTimeofAttend}
			</td>
					
		</tr>
		
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Date of Closing</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strDateofClosing}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Time of Closing </td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strTimeofClosing}
			</td>	
		</tr>
		  <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Is Spare Part Maintenance Involved 	</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strIsspareInvolved}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Problem Description</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strProblemDescription}
			</td>	
		</tr>
	  <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Solution Provided</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strSolutionProvided}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Remarks</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strEngineerRemark}
			</td>	
		</tr>
		<tr>
		<td colspan="1" class="LABEL" style="text-align:right;">Reason for Close</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strReasonforClose}
			</td>	
			<td colspan="1" class="LABEL" style="text-align:right;"> Is Item In Working Condition </td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strIsWorking}</td>	
	</tr>
	<tr>
		<td colspan="1" class="LABEL" style="text-align:right;">From Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strFromDate}
			</td>	
			<td colspan="1" class="LABEL" style="text-align:right;">From Time</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strFromTime}</td>	
	</tr><tr>
		<td colspan="1" class="LABEL" style="text-align:right;">Total Cost</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strTotalCost}
			</td>	
			<td colspan="1" class="LABEL" style="text-align:right;">Is Penalty</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strIspenalty}</td>	
	</tr><tr>
		<td colspan="1" class="LABEL" style="text-align:right;">Penalty Amount</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strPenaltyAmount}
			</td>	
			<td colspan="1" class="LABEL" style="text-align:right;">Order No.</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strOrderNo}</td>	
	</tr>
	<tr>
		<td colspan="1" class="LABEL" style="text-align:right;">Order Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strOrderDate}
			</td>	
			<td colspan="1" class="LABEL" style="text-align:right;">Net Cost</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strNetCost}</td>	
	</tr>
	<tr>
		<td colspan="1" class="LABEL" style="text-align:right;">VerifiedBy</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strVerifiedBy}
			</td>	
			<td colspan="1" class="LABEL" style="text-align:right;">Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strAttendDate}</td>	
	</tr>
	<tr>
	<td colspan="1" class="LABEL" style="text-align:right;">Time</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strAttendTime}</td>	

	</tr>
	
	<tr class="HEADER">
			<td colspan="4">HEMM Closing Details &gt;&gt;</td></tr>

		<tr>	
			<td colspan="1" class="LABEL" style="text-align:right;">Closing Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strDateofClosing}</td>
			<td colspan="1" class="LABEL" style="text-align:right;">Closing Time</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strTimeofClosing}</td>
		</tr>
	
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Reason For Closing</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strReasonforClose}
			</td>
								
		</tr>
		
	    <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Vendor Invoice No</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strVendorInvoiceNo}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Total Cost Involved</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strTotalCostInvolved}
			</td>	
		</tr>
		  <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Is Item In Working Condition</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strIsWorking}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Total DownTime From Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strDownTimeFromDate}
			</td>	
		</tr>
	  <tr>
			<td colspan="1" class="LABEL" style="text-align:right;">Total DownTime To Date</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strDownTimeToDate}
			</td>
				<td colspan="1" class="LABEL" style="text-align:right;">Total DownTime</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strTotalDownTime}
			</td>	
		</tr>
		<tr>
			
			<td colspan="1" class="LABEL" style="text-align:right;"> Is Verfied By</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strVerifiedBy}</td>	

		<td colspan="1" class="LABEL" style="text-align:right;">Remarks</td>
			<td colspan="1" class="CONTROL"  style="text-align:left;">${CentralEquipEnquiryDeskFB.strCloseRemarks}
			</td>	
				
	</tr>
	<tr>
	<td colspan="4" style="text-align:center;"><img src="../../../hisglobal/images/back_tab.png" onClick="javascript:window.history.back();" ></td>
	</tr>	
		</table>
	<html:hidden property="hmode" styleId="hmode"/>
		</html:form>

</body>
</html>

