<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<%--
/**
 * Developer Name : Vivek Aggarwal
 * Process Name : Complaint Reminder
 * Date : 26/May/2011
 * Modify By/Date : 
 */ 
 --%>

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


<his:javascript	src="/bmed/transactions/js/bmed_complaintReminder_trans.js" />

</head>

<body marginheight="0" marginwidth="0" >
<html:form name="complaintMaintenanceStatusBean" action="/transactions/ComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB">
	
	<his:TransactionContainer>
	
		<his:TitleTag name="Complaint Reminder">
		</his:TitleTag>
	
	<%-- Request/Complaint Details --%>

		<his:ContentTag>		
		<his:SubTitleTag name="Request/Complaint Details">
		</his:SubTitleTag>
				<table class="TABLE_STYLE">	
				 	 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							Complaint Id</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strComplaintId" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							Complaint Date</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strComplaintDate" />						
						</td>
					</tr>
					
					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							Item Name</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strItemName" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							Batch No.</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strItemBatchNo" />						
						</td>
					</tr>
		

					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							Serial No.</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strItemSerialNo" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							Manufacturer Serial No.</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strManufacturerSerialNo" />						
						</td>
					</tr>


					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							Complaint Description</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strComplaintDescription" />						
						</td>
					
						<td width="25%" class="CONTROL_TD"></td>
						<td width="25%" class="CONTROL_TD"></td>
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
	
 			
<%-- Internal Reminder Form --%>		
		<his:ContentTag>	
			
		<his:SubTitleTag name="Internal Reminder/Updates Form">
		</his:SubTitleTag>
		
				<table class="TABLE_STYLE">	
				 	 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							Type</td>
						<td width="25%" class="CONTROL_TD">
							<input type='radio' name='remindertype' value="1" checked="checked">Reminder							
							<input type='radio' name='remindertype' value="2">Updates
						</td>					
						<td width="25%" class="LABEL_TD"></td>
						<td width="25%" class="CONTROL_TD"></td>
					</tr>
					<tr align="center" >
						<td width="25%" class="LABEL_TD">
							Reminder/Updates against Complaint</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strComplaintId" />							
						</td>
					
						<td width="25%" class="LABEL_TD">
							Reminder/Updates number</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strNoOfReminders" />						
						</td>
					</tr>
					
					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							From</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strContactPerson" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							Department</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strDeptName" />						
						</td>
					</tr>
		

					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							Contact No.</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strContactNo" />						
						</td>
					
						<td width="25%" class="LABEL_TD">
							Email Id</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strEmailId" />						
						</td>
					</tr>


					 <tr align="center" >
						<td width="25%" class="LABEL_TD">
							Date/Time</td>
						<td width="25%" class="CONTROL_TD">
							<bean:write name="complaintMaintenanceStatusBean" property="strCtDate" />						
						</td>
					
						<td width="25%" class="CONTROL_TD"></td>
						<td width="25%" class="CONTROL_TD"></td>
					</tr>
					
					
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Reminder/Updates Details</td>
						<td width="75%" class="CONTROL_TD" colspan='3'>
							<html:textarea name="complaintMaintenanceStatusBean" property="strReminderDetails" cols="70" rows="2" onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');" />
						</td>
						
						<td width="25%" class="CONTROL_TD">
						</td>
						
						<td width="25%" class="CONTROL_TD">
						</td>
					</tr>		
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
			<img style="cursor: pointer;" src="/HBIMS/hisglobal/images/btn-sv.png" onClick="return validate1();" onkeypress="if(event.keyCode==13) validate1();"/>
			<img style="cursor: pointer;" src="/HBIMS/hisglobal/images/btn-clr.png" onClick="clearPage();" onkeypress="if(event.keyCode==13) clearPage();" />
			<img style="cursor: pointer;" src="/HBIMS/hisglobal/images/btn-ccl.png" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();" />
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write name="complaintMaintenanceStatusBean" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write name="complaintMaintenanceStatusBean" property="strWarningMsg" /></div>
		<div class="NORMAL_DIV" id="normalMsg"><bean:write name="complaintMaintenanceStatusBean" property="strNormalMsg" /></div>
<br /><br />
		<input type="hidden" name="hmode" />
		<input type="hidden" name="strComplaintId" value="${complaintMaintenanceStatusBean.strHiddenComplaintId}"  />
		<input type="hidden" name="strHiddenComplaintId" value="${complaintMaintenanceStatusBean.strHiddenComplaintId}" />		
		<input type="hidden" name="strComplaintType" value="${complaintMaintenanceStatusBean.strComplaintType}" />
		<input type="hidden" name="strEmpId" value="${complaintMaintenanceStatusBean.strEmpId}" />
		<input type="hidden" name="strUploadFileId" value="" />
	
	
		</his:TransactionContainer>
		
	<cmbPers:cmbPers />
		
</html:form>
</body>
</html>
