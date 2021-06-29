<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>


<!-- 
/**
 * @author Aritra Kumar Dhawa
 * Date of Creation : 22-Feb-2011
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 -->
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 --%>
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
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript
	src="/bmed/transactions/js/bmed_cancel_complaintMaintenanceStatus_trans.js" />
</head>
<body marginheight="0" marginwidth="0">
<html:form name="complaintMaintenanceStatusBean"
	action="/transactions/ComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB">
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Cancel">
		</his:TitleTag>

		<his:SubTitleTag name="Complaint Details">
		</his:SubTitleTag>

		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Id</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strComplaintId}</td>
					<td width="25%" class="LABEL_TD">Complaint Date</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strComplaintDate}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Item Name</td>
					<td class="CONTROL_TD">${complaintMaintenanceStatusBean.strItemName}</td>
					<td class="LABEL_TD">Batch No.</td>
					<td class="CONTROL_TD">${complaintMaintenanceStatusBean.strItemBatchNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Serial No</td>
					<td class="CONTROL_TD">${complaintMaintenanceStatusBean.strItemSerialNo}</td>
					<td width="25%" class="LABEL_TD">Manufacturer Serial No.</td>
					<td class="CONTROL_TD">${complaintMaintenanceStatusBean.strManufacturerSerialNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD" colspan="3">${complaintMaintenanceStatusBean.strComplaintDescription}</td>
				</tr>
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Warranty Details">
		</his:SubTitleTag>

		<his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strWarrantyDetailsTable" filter="false" />
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="Maintenance Contract Details">
		</his:SubTitleTag>
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strMaintenanceContractDetailsTable" filter="false" />
			</table>
		</his:ContentTag>


		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show"
						src="/AHIMS/hisglobal/images/plus.gif"
						onclick="tableShow('schedulesDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;">Schedules Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Schedules Details">
			<table class="TABLE_STYLE" id="schedulesDetailsTable"
				style="display: none;">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strSchedulesDetailsTable" filter="false" />
			</table>
		</his:ContentTag>

		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show"
						src="/AHIMS/hisglobal/images/plus.gif"
						onclick="tableShow('attenderDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;">Attender Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Attender Details">
			<table class="TABLE_STYLE" id="attenderDetailsTable"
				style="display: none;">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strAttenderDetailsTable" filter="false" />
			</table>
		</his:ContentTag>
		<his:ContentTag name="Cancel Details">
			<table class="TABLE_STYLE" id="CancelDetailsTable">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Cancel
					Type</td>
					<td width="25%" class="CONTROL_TD"><select
						name="strCancelTypeId" class="COMBO_NORMAL">
						<bean:write name="complaintMaintenanceStatusBean"
							property="strCancelTypeOptions" filter="false" />
					</select></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Cancel
					Remarks</td>
					<td width="25%" class="CONTROL_TD"><textarea rows="2"
						cols="20" name="strCancelRemarks"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea>
					</td>
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
				src='<his:path src="/hisglobal/images/btn-sv.png"/>' title="Save Record"
				style="cursor: pointer" onclick="validateCancel('saveCancel');"
				onkeypress="if(event.keyCode==13) validateCancel('saveCancel');" />
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>' title="Cancel"
				style="cursor: pointer" onclick="cancelScheduleCancel();"
				onkeypress="if(event.keyCode==13) cancelScheduleCancel();" />
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write
			name="complaintMaintenanceStatusBean" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write
			name="complaintMaintenanceStatusBean" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write
			name="complaintMaintenanceStatusBean" property="strNormalMsg" /></div>
			<br />
		<input type="hidden" name="hmode" />
		<input type="hidden" name="strUploadFileId" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strComplaintId" />
	</his:TransactionContainer>
</html:form>
</body>
</html>