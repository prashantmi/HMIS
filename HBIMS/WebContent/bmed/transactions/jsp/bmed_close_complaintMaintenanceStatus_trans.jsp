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
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/bmed/transactions/js/bmed_close_complaintMaintenanceStatus_trans.js" />
</head>
<body marginheight="0" marginwidth="0" onload="setInitValues();">
<html:form name="complaintMaintenanceStatusBean"
	action="/transactions/ComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB">
	<his:TransactionContainer>
		<his:TitleTag name="Close Complaint">
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
					<td width="25%" class="LABEL_TD">Department Name</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strDeptName}</td>
					<td width="25%" class="LABEL_TD">Store Name</td>
					<td width="25%" class="CONTROL_TD">${complaintMaintenanceStatusBean.strStoreName}</td>
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
						src="/HBIMS/hisglobal/images/plus.gif"
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
						src="/HBIMS/hisglobal/images/plus.gif"
						onclick="tableShow('attenderDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;">Attender Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		
		<his:ContentTag name="Attender Details">
			<table class="TABLE_STYLE" id="attenderDetailsTable" style="display: none;">
				<bean:write name="complaintMaintenanceStatusBean" property="strAttenderDetailsTable" filter="false" />
			</table>			
		</his:ContentTag>
		
		<his:SubTitleTag name="Close Schedule">

		</his:SubTitleTag>

		<his:ContentTag>
			<table class="TABLE_STYLE" id="repaiedByVendorHeaderTable">
				<tr>
					<td width="50%" class="LABEL_TD">Repaired By Vendor</td>
					<td width="50%" class="CONTROL_TD"><input type="checkbox"
						name="strRepaiedByVendor" value="1" onchange="showOrHideVendorDetails(this)"></td>
				</tr>
			</table>
			<table class="TABLE_STYLE" id="repaiedByVendorDetailTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL_TD">Vendor Name</td>
					<td width="25%" class="CONTROL_TD"><select
						name="strVendorId" class="COMBO_NORMAL">
						<bean:write name="complaintMaintenanceStatusBean"
							property="strManufactureNameOptions" filter="false" />
					</select></td>
					<td width="25%" class="LABEL_TD">Contact No.</td>
					<td width="25%" class="CONTROL_TD"><span id="contactNoLebel"></span></td>
				</tr>
				<tr>
					<td class="LABEL_TD">Address</td>
					<td class="CONTROL_TD"><span id="addressLebel"></span></td>
					<td class="LABEL_TD">Invoice No.</td>
					<td class="CONTROL_TD"><input type="text"
						name="strInvoiceNo" value="" class="TEXT_FIELD_MAX"></td>
				</tr>
			</table>
		</his:ContentTag>
		<his:ContentTag>
			<table class="TABLE_STYLE" id="closingDetailTable">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Closing Date</td>
					<td width="25%" class="CONTROL_TD"><his:date name="strClosingDate" value=""></his:date> </td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Closing Time</td>
					<td width="25%" class="CONTROL_TD"><input type="text"
						name="strClosingTime" class="TEXT_FIELD_MIN" maxlength="5"
						onchange="return validateDataWithSpecialChars(event,5,':');"
						onkeypress="return validateDataWithSpecialChars(event,5,':');">&nbsp;[HH:MM]
					24hr</td>
				</tr>
				<tr>
					<td colspan="2" class="LABEL_TD"><font color="red">*</font>Total Cost Involved</td>
					<td colspan="2" class="CONTROL_TD"><input type="text"
						name="strTotalCostInvolved" class="TEXT_FIELD_MAX" maxlength="12"
						onchange="return validateData(event,5);"
						onkeypress="return validateData(event,5);">&nbsp;INR</td>
				</tr>
				<tr>
					<td colspan="2" class="LABEL_TD"><font color="red">*</font>Reason for Closing</td>
					<td colspan="2" class="CONTROL_TD"><textarea rows="2"
						cols="30" name="strReasonForClosing"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Item is in Working
					Condition</td>
					<td class="CONTROL_TD">
					<input type="radio" name="strWorkingCondition" value="1" onchange="showOrHideTotalDownTime(this);">Yes<input
						type="radio" name="strWorkingCondition" value="0" onchange="showOrHideTotalDownTime(this);">No</td>
					<td class="LABEL_TD"><span id="strTotalDownTimeLebel" >
						<font color="red">*</font>Total Down Time</span>
					</td>
					<td class="CONTROL_TD">
						<span id="strTotalDownTimeControl" >
							<input type="text" name="strTotalDownTime" class="TEXT_FIELD_MIN" maxlength="5"
						onchange="return validateData(event,5);"
						onkeypress="return validateData(event,5);">&nbsp;[Hours Only]</span>
					</td>
				</tr>
				
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Verified By</td>
					<td class="CONTROL_TD"><select
						name="strVerifiedBy" class="COMBO_NORMAL">
						<bean:write name="complaintMaintenanceStatusBean"
							property="strVerifiedByOptions" filter="false" />
					</select></td>
					<td class="LABEL_TD">Remarks</td>
					<td class="CONTROL_TD"><textarea rows="2"
						cols="25" name="strRemarks"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
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
				style="cursor: pointer" onclick="validateSave('saveClose');"
				onkeypress="if(event.keyCode==13) validateSave('saveClose');" />
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-clr.png"/>' title="Clear"
				style="cursor: pointer" onclick="clearClose();"
				onkeypress="if(event.keyCode==13) clearClose();" />
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>' title="Cancel"
				style="cursor: pointer" onclick="cancelClose();"
				onkeypress="if(event.keyCode==13) cancelClose();" />
		</his:ButtonToolBarTag>

		<!-- Error Messages 	-->
		<br />
		<div id="strErrMsg" align="center" class="ERR_DIV"><bean:write	name="complaintMaintenanceStatusBean" property="strErrMsg" /></div>
		<div id="strWarningMsg" align="center" class="WARNING_DIV"><bean:write	name="complaintMaintenanceStatusBean" property="strWarningMsg" /></div>
		<div id="normalMsg" align="center" class="NORMAL_DIV"><bean:write name="complaintMaintenanceStatusBean" property="strNormalMsg" /></div>
		<br /><br />
		<input type="hidden" name="hmode" />
		<input type="hidden" name="strUploadFileId" />
			<html:hidden name="complaintMaintenanceStatusBean" property="strComplaintId" />
			<input type="hidden" name="strHiddenComplaintId" value="${complaintMaintenanceStatusBean.strHiddenComplaintId}" />
			<html:hidden name="complaintMaintenanceStatusBean" property="strIsHemDesk" />
		</his:TransactionContainer>
</html:form>
</body>
</html>