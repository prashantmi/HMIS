<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<!--
/**
 * Developer Name : Vivek Aggarwal
 * Process Name : Complaint Details View
 * Date : 18/May/2011
 * Modify By/Date : 
 */ 
 -->

<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />

<his:javascript  src="/hisglobal/masterutil/js/master.js" />
<his:javascript  src="/hisglobal/js/util.js" />
<his:javascript  src="/hisglobal/js/tab.js" />
<his:javascript  src="/hisglobal/js/validation.js" />
<his:javascript  src="/hisglobal/js/commonFunctions.js" />


<his:javascript	src="/bmed/transactions/js/bmed_complaintDetailsView_trans.js" />

</head>

<body marginheight="0" marginwidth="0"  onload="onLoadFunction();">
<html:form name="complaintMaintenanceStatusBean" action="/transactions/ComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB">
	
	<his:TransactionContainer>
	
		<his:TitleTag name="Complaint Details View">
		</his:TitleTag>
		
			<his:ContentTag>		
		<his:SubTitleTag name="Request/Complaint Details">
		</his:SubTitleTag>
				<table class="TABLE_STYLE">	
				 	 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Complaint Id</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strComplaintId" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Complaint Date</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strComplaintDate" />						
						</td>
					</tr>
					
				<tr>
					<td width="25%" class="LABEL_TD">Department Name</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strDeptName}</td>
					<td width="25%" class="LABEL_TD">Store Name</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strStoreName}</td>
				</tr>
					
					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Item Name</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strItemName" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Batch No.</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strItemBatchNo" />						
						</td>
					</tr>
		

					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Serial No.</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strItemSerialNo" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Manufacturer Serial No.</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strManufacturerSerialNo" />						
						</td>
					</tr>


					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							<font color="red">*</font>Complaint Description</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strComplaintDescription" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							<!-- <font color="red">*</font>Department</td> -->
						<td width="25%" class="CONTROL_TD">
							<%-- <bean:write name="complaintMaintenanceStatusBean" property="strDeptName" />	 --%>					
						</td>
					</tr>
				</table>
		</his:ContentTag>		
	
<%-- Warranty Details --%>
		<his:SubTitleTag name="Warranty Details">
		</his:SubTitleTag>
	
		<his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean" property="strWarrantyDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

<%-- Maintenance Contract Details --%>

		<his:SubTitleTag name="Maintenance Contract Details">
		</his:SubTitleTag>
		
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean" property="strMaintenanceContractDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
	
	
<%-- Approval Details --%>		

<his:SubTitleTag name="Approval Details">
		</his:SubTitleTag>
		
		<his:ContentTag name="Approval Details">
			<table class="TABLE_STYLE" id="approvalDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean" property="strApprovalDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>

	
<%-- Schedules Details --%>	
		
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HBIMS/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('schedulesDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Schedules Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Schedules Details">
			<table class="TABLE_STYLE" id="schedulesDetailsTable" style="display: none;" >
				<bean:write name="complaintMaintenanceStatusBean" property="strSchedulesDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
	
<%-- Attender Details --%>
		
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HBIMS/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('attenderDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Attender Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Attender Details">
			<table class="TABLE_STYLE" id="attenderDetailsTable" style="display: none;" >
				<bean:write name="complaintMaintenanceStatusBean" property="strAttenderDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>



<%-- Reminders --%>
		
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HBIMS/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('remindersDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Reminders</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Reminders">
			<table class="TABLE_STYLE" id="remindersDetailsTable" style="display: none;" >
				<bean:write name="complaintMaintenanceStatusBean" property="strRemindersDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
        

<%-- Grievances  Details --%>
		
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HBIMS/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('grievancesDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Grievances Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Grievances Details">
			<table class="TABLE_STYLE" id="grievancesDetailsTable" style="display: none;" >
				<bean:write name="complaintMaintenanceStatusBean" property="strGrievancesDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
  
  <%--  Spare Part  Details --%>
		
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HBIMS/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('sparePartDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Spare Part Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Grievances Details">
			<table class="TABLE_STYLE" id="sparePartDetailsTable" style="display: none;" >
				<bean:write name="complaintMaintenanceStatusBean" property="strSparePartDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>
        
         
 <%-- Close Details --%>		

		<his:SubTitleTag name="Close Details">
		</his:SubTitleTag>
		
		<his:ContentTag name="Close Details">
			<table class="TABLE_STYLE" id="closeDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean" property="strCloseDetailsTable" filter="false"/>
			</table>
		</his:ContentTag>    
        
        
        <his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>
     		
		<his:ButtonToolBarTag>
			<img style="cursor: pointer;" src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();" />
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write name="complaintMaintenanceStatusBean" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write name="complaintMaintenanceStatusBean" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write name="complaintMaintenanceStatusBean" property="strNormalMsg" /></div>
<br /><br />
		<input type="hidden" name="hmode" />
		<input type="hidden" name="strComplaintId" value"" />
		<input type="hidden" name="strHiddenComplaintId" value="${complaintMaintenanceStatusBean.strHiddenComplaintId}" />		
		<input type="hidden" name="strComplaintType" value="${complaintMaintenanceStatusBean.strComplaintType}" />
		<input type="hidden" name="strEmpId" value="${complaintMaintenanceStatusBean.strEmpId}" />
		<input type="hidden" name="strUploadFileId" value="" />		
		<html:hidden name="complaintMaintenanceStatusBean" property="strIsHemDesk" />
		</his:TransactionContainer>
		
		<cmbPers:cmbPers />
		
</html:form>
</body>
</html>