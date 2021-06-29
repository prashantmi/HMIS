<%@page import="hisglobal.hisconfig.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!-- 
/**
 * @author Partha P Chattaraj and T. Saratkumar
 * Date of Creation : 29-July-2013
 * Date of Modification :  
 * Version : 1.0
 * Product  : HEMMS Product
 */
 -->
<html>
<head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:css src="/hisglobal/css/popup.css" />


<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript
	src="/bmed/transactions/js/hemms_main_complaintMaintenanceStatus_trans.js" />
</head>
<body marginheight="0" marginwidth="0">
<html:form name="hemmsComplaintMaintenanceStatusBean"
	action="/transactions/HemmsComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.HemmsComplaintMaintenanceStatusFB">
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Maintenance Status">
		</his:TitleTag>

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<!--<td  width="25%" class="LABEL_TD">Attached</td>

					<td width="25%" class="CONTROL_TD"><input type="checkbox"
						id="strIsAttached" name="strIsAttached" tabindex="1" value="1"
						onchange="getAjaxComplaintRequestData();" /></td>
					-->
					<td  width="25%" class="LABEL_TD"></td>

					<td width="25%" class="CONTROL_TD">
					<input type="checkbox" style="display: none;"
						id="strIsAttached" name="strIsAttached" tabindex="1" value="1"
						onchange="getAjaxComplaintRequestData();" /></td>
					<td width="25%" class="LABEL_TD"><font color="RED">*</font>&nbsp;Hospital</td>

					<td width="25%" class="CONTROL_TD"><select name="strDeptCode"
						id="strDeptCode" class="COMBO_NORMAL" tabindex="1"
						onchange="getAjaxComplaintRequestData();">
						<bean:write name="hemmsComplaintMaintenanceStatusBean"
							property="strDeptOptions" filter="false" />
					</select></td>
				</tr>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Active Complaints">
			<span id="activeComplaintsTablePagination"></span>
		</his:SubTitleTag>

		<his:ContentTag name="Active Complaints">
			<table class="TABLE_STYLE" id="activeComplaintsTable">
				<tr>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Request
					Id / Date</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Request
					Type</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Equipment
					Name</td>
					<td width="20%" class="LABEL_TD" style="text-align: center;">Status</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Actions</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Reminders</td>
					<td width="5%" class="LABEL_TD" style="text-align: center;">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="7" class="CONTROL_TD"
						style="text-align: center; color: red;">No Data Found!</td>
				</tr>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Active Preventive Maintenance">
			<span id="activePreventiveMaintenanceTablePagination"></span>
		</his:SubTitleTag>
		<his:ContentTag name="Active Preventive Maintenance">
			<table class="TABLE_STYLE" id="activePreventiveMaintenanceTable">
				<tr>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Request
					Id / Date</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Request
					Type</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Equipment
					Name</td>
					<td width="20%" class="LABEL_TD" style="text-align: center;">Status</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Actions</td>
					<td width="15%" class="LABEL_TD" style="text-align: center;">Reminders</td>
					<td width="5%" class="LABEL_TD" style="text-align: center;">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="7" class="CONTROL_TD"
						style="text-align: center; color: red;">No Data Found!</td>
				</tr>
			</table>
		</his:ContentTag>
		<his:ContentTag name="Footer">
			<table class="TABLE_STYLE">
				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>
		<his:ButtonToolBarTag>
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/New.png"/>'
				title="New Complaint" style="cursor: pointer"
				onclick="validateNewComplaint('initializeItemComplaintRegister');"
				onkeypress="if(event.keyCode==13) validateNewComplaint('initializeItemComplaintRegister');" />
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-clr.png"/>' title="Clear"
				style="cursor: pointer" onclick="clearComplaintMaintenanceStatus();"
				onkeypress="if(event.keyCode==13) { clearComplaintMaintenanceStatus(); }" />
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>' title="Cancel"
				style="cursor: pointer"
				onclick="cancelComplaintMaintenanceStatus('goToWelcomePage');"
				onkeypress="if(event.keyCode==13) cancelComplaintMaintenanceStatus('goToWelcomePage');" />
		</his:ButtonToolBarTag>
		<div id="" class="popup" style="display: none;">
		<table style="border-collapse: collapse;">
			<!-- Popup Header -->
			<tr class="FOOTER_TR">
				<td colspan="3" style="text-align: left;">Reminder Details</td>
				<td colspan="1" style="text-align: right;">Close</td>
			</tr>
			<!-- Complaint Info -->
			<tr>
				<td width="25%" class="LABEL_TD">Request / Complaint ID</td>
				<td width="25%" class="CONTROL_TD">112356</td>
				<td width="25%" class="LABEL_TD">Request Date</td>
				<td width="25%" class="CONTROL_TD">10-March-2011/ 08.00 A.M.</td>
			</tr>
			<!-- 1st reminder -->
			<tr class="FOOTER_TR">
				<td colspan="4" style="text-align: left;">Reminder 1</td>
			</tr>
			<tr>
				<td class="LABEL_TD">From</td>
				<td class="CONTROL_TD">Mr. PQR</td>
				<td class="LABEL_TD">Date/Time</td>
				<td class="CONTROL_TD">20-March-2011/ 08.00 A.M.</td>
			</tr>
			<tr>
				<td class="LABEL_TD">Contact No.</td>
				<td class="CONTROL_TD">2402551</td>
				<td class="LABEL_TD">Email Id</td>
				<td class="CONTROL_TD">abcatyahoo.com</td>
			</tr>
			<tr>
				<td class="LABEL_TD">Reminder Details</td>
				<td class="CONTROL_TD">No action has been taken yet</td>
				<td class="LABEL_TD"></td>
				<td class="CONTROL_TD"></td>
			</tr>
			<!-- 1st reply -->
			<tr class="FOOTER_TR">
				<td colspan="4" style="text-align: left;">Reply Details</td>
			</tr>
			<tr>
				<td class="LABEL_TD">From</td>
				<td class="CONTROL_TD">Mr. PQR</td>
				<td class="LABEL_TD">Date/Time</td>
				<td class="CONTROL_TD">21-March-2011/ 09.00 A.M.</td>
			</tr>
			<tr>
				<td class="LABEL_TD">Reply</td>
				<td class="CONTROL_TD">Service engineer has been allocated.
				Please view the schedule details.</td>
				<td class="LABEL_TD"></td>
				<td class="CONTROL_TD"></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;"><img class="button"
					tabindex="1" src="../../hisglobal/images/close_tab.png"
					title="Close" style="cursor: pointer"
					onclick="closeReminderPopup();"
					onkeypress="if(event.keyCode==13) { closeReminderPopup(); }" /></td>
			</tr>
		</table>
		</div>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="hemmsComplaintMaintenanceStatusBean" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="hemmsComplaintMaintenanceStatusBean" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="hemmsComplaintMaintenanceStatusBean" property="strNormalMsg" /></div>
		<input type="hidden" name="hmode" />
		<input type="hidden" name="strHiddenComplaintId" />
		<input type="hidden" name="strComplaintId" />
		<input type="hidden" name="strIsHemDesk" value="0" />
		
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>