<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<%-- 
/**
 * @author Aritra Kumar Dhawa
 * Date of Creation : 22-Feb-2011
 * Date of Modification :  
 * Version : 
 * Module  : BMED
 */
 --%>
<html>
<head>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> --%>
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
<his:css src="/hisglobal/css/popup.css" />

<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validation.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript  src="/hisglobal/js/time.js" />

<his:javascript
	src="/bmed/transactions/js/bmed_schedule_complaintMaintenanceStatus_trans.js" />
</head>
<body marginheight="0" marginwidth="0">
<html:form name="complaintMaintenanceStatusBean"
	action="/transactions/ComplaintMaintenanceStatusTransACTION"
	type="bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB">
	<his:TransactionContainer>
		<his:TitleTag name="Complaint Schedule">
		</his:TitleTag>

		<%-- ************ Complaint Details Start ***************** --%>
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
		<%-- ============= Complaint Details End ================ --%>


		<%-- *************** External Service Provider Start *************** --%>
		<%--
		This block will be visible when scheduling is done from HEM Desk. 
		--%>
		<span id="externalServiceProvidercheckBox"
			style="display: ${complaintMaintenanceStatusBean.strExternalServiceProvidercheckBoxDisplay}">
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<tr>
					<td width="50%" class="LABEL_TD">Allocate to External Service
					Provider</td>
					<td width="50%" class="CONTROL_TD"><input
						name="strAllocateToExternalServiceProvider" type="checkbox"
						value="1"
						onchange="activateAllocateToExternalServiceProviderMode(this);"
						<bean:write
			name="complaintMaintenanceStatusBean" property="strExternalServiceProvidercheckBoxChecked" />></td>
				</tr>
			</table>
		</his:ContentTag> </span>

		<%-- ================== External Service Provider End ============= --%>



		<%-- ************ Warranty & Maintenance Detail Start *************** --%>
		<span id="warrantyAndMaintenanceContractDiv"
			style="display: ${complaintMaintenanceStatusBean.strWarrantyAndMaintenanceContractDivDisplay}">
		<his:SubTitleTag name="Warranty Details">
		</his:SubTitleTag> <his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strWarrantyDetailsTable" filter="false" />
			</table>
		</his:ContentTag> <his:SubTitleTag name="Maintenance Contract Details">
		</his:SubTitleTag> <his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable">
				<bean:write name="complaintMaintenanceStatusBean"
					property="strMaintenanceContractDetailsTable" filter="false" />
			</table>
		</his:ContentTag></span>
		<%-- =============== Warranty & Maintenance Detail End ================ --%>


		<%-- **************** Service Engineer Data Start ********************* --%>
		<logic:equal value="1" name="complaintMaintenanceStatusBean"
			property="strIsHemDesk">


			<span id="serviceEngineerDataDiv"> <his:SubTitleTag
				name="Service Engineer Details">
			</his:SubTitleTag> <his:ContentTag name="Service Engineer Details">
				<table class="TABLE_STYLE" id="strServiceEngineerDetailsTable">
					<bean:write name="complaintMaintenanceStatusBean"
						property="strServiceEngineerDetailsTable" filter="false" />
				</table>
			</his:ContentTag> <his:SubTitleTag name="Other Service Engineer Details">
			</his:SubTitleTag> <his:ContentTag name="Other Service Engineer Details Filter">
				<table class="TABLE_STYLE"
					id="strOtherServiceEngineerDetailsTableFilter">
					<tr>
						<td class="LABEL_TD" width="25%">Filter By</td>
						<td class="CONTROL_TD" width="25%"><select id="strFilterBy"
							name="strFilterBy" class="COMBO_NORMAL"
							onchange="populateFilterValue(this);">
							<option value="0">Select Value</option>
							<option value="ENGG_ITEM_SUBTYPE">Engineering Item Sub
							Type</option>
							<option value="SKILL">Skills</option>
						</select></td>
						<td class="LABEL_TD" width="25%">Value</td>
						<td class="CONTROL_TD" width="25%"><select
							id="strFilterValue" name="strFilterValue" class="COMBO_NORMAL" onchange="populateOtherServiceEngineer();">
							<option value="0">Select Value</option>
						</select></td>
					</tr>
					<%-- 
					<tr>
						<td colspan="4" class="CONTROL_TD" style="text-align: center;"><img
							class="button" tabindex="1"
							src='<his:path src="/hisglobal/images/GO.png"/>'
							title="Save Record" style="cursor: pointer"
							onclick="populateOtherServiceEngineer();"
							onkeypress="if(event.keyCode==13) populateOtherServiceEngineer();" /></td>
					</tr>
					--%>
				</table>
			</his:ContentTag> <his:ContentTag name="Other Service Engineer Details Table">
				<table class="TABLE_STYLE" id="strOtherServiceEngineerDetailsTable">
					<bean:write name="complaintMaintenanceStatusBean"
						property="strOtherServiceEngineerDetailsTable" filter="false" />
					<tr>
						<td width="5%" class="LABEL_TD" style="text-align: center;">&nbsp;</td>
						<td width="30%" class="LABEL_TD" style="text-align: center;">Service
						Engineer Name</td>
						<td width="30%" class="LABEL_TD" style="text-align: center;">Skills</td>
						<td width="35%" class="LABEL_TD" style="text-align: center;">Number of Jobs</td>
					</tr>
					<tr>
						<td class="CONTROL_TD" colspan="4"
							style="text-align: center; color: red;">No Data Found!</td>
					</tr>

				</table>
			</his:ContentTag> <his:SubTitleTag name="Service Engineer Attend Details">
			</his:SubTitleTag> <his:ContentTag>
				<table class="TABLE_STYLE">
					<tr>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Expected
						Date to Attend</td>
						<td width="25%" class="CONTROL_TD"><his:date
							name="strExpectedDateToAttend" value=""></his:date></td>
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Expected
						Time to Attend</td>
						<td width="25%" class="CONTROL_TD"><input type="text"
							name="strExpectedTimeToAttend" class="TEXT_FIELD_MIN"
							maxlength="5" 							
							onkeypress='return checkTime(event,this);'>&nbsp;[HH:MM]
						24hr</td>
					</tr>
					<tr>
						<td class="LABEL_TD" colspan="2"><font color="red">*</font>Service
						Engineer Remarks</td>
						<td class="CONTROL_TD" colspan="2"><textarea rows="2"
							cols="30" name="strServiceEngnieerRemarks"
							onkeypress="return validateDataWithSpecialChars(event,9,'.');"
							onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
					</tr>
				</table>
			</his:ContentTag> </span>


		</logic:equal>

		<%-- ========================== Service Engineer Data End ==========================  --%>



		<%-- ****************** Previous Schedule and Attend Details Start ****************** --%>

		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show"
						src="/HBIMS/hisglobal/images/plus.gif"
						onclick="tableShow('schedulesDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;">Previous Schedules</td>
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
					<td width="95%" style="text-align: left;">Previous Attender</td>
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
		<%-- =========== Previous Schedule and Attend Details End ========= --%>



		<span id="externalScheduleDetailsDiv"
			style="display: ${complaintMaintenanceStatusBean.strWarrantyAndMaintenanceContractDivDisplay};">
		<%-- ************** External Schedule Details Start ***************** --%>
		<his:ContentTag name="Schedules Details">
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Intimation
					Date</td>
					<td width="25%" class="CONTROL_TD"><his:date
						name="strIntimationDate" value=""></his:date></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Intimation
					Time</td>
					<td width="25%" class="CONTROL_TD"><input type="text"
						name="strIntimationTime" class="TEXT_FIELD_MIN" maxlength="5"
						onchange="return validateDataWithSpecialChars(event,5,':');"
						onkeypress='return checkTime(event,this);'>&nbsp;[HH:MM]
					24hr</td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Contact Person</td>
					<td class="CONTROL_TD"><input type="text"
						name="strContactPerson" class='TEXT_FIELD_MAX' value=""
						maxlength="100" onkeypress="return validateData(event,4);"
						onchange="return validateData(event,4);"></td>
					<td class="LABEL_TD"><font color="red">*</font>Contact No.</td>
					<td class="CONTROL_TD"><input type="text" name="strContactNo"
						class='TEXT_FIELD_MAX' value="" maxlength="25"
						onkeypress="return validateData(event,2);"
						onchange="return validateData(event,2);"></td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Problem
					Description</td>
					<td class="CONTROL_TD"><textarea rows="2" cols="25"
						name="strProblemDescription"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
					<td class="LABEL_TD"><font color="red">*</font>Communication
					Id</td>
					<td class="CONTROL_TD"><input type="text"
						name="strCommunicationId" class="TEXT_FIELD_MAX" maxlength="50"
						onchange="return validateData(event,8);"
						onkeypress="return validateData(event,8);"></td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Expected Visit</td>
					<td class="CONTROL_TD"><input type="text"
						name="strExpectedVisit" class="TEXT_FIELD_MIN" maxlength="3"
						onchange="return validateData(event,5);"
						onkeypress="return validateData(event,5);"></td>
					<td class="LABEL_TD"><font color="red">*</font>Expected Visit
					Unit</td>
					<td class="CONTROL_TD"><select name="strExpectedVisitUnitId"
						class="COMBO_NORMAL">
						<bean:write name="complaintMaintenanceStatusBean"
							property="strExpectedVisitUnitOptions" filter="false" />
					</select></td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Solution
					Provided</td>
					<td class="CONTROL_TD"><textarea rows="2" cols="25"
						name="strSolutionProvided"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
					<td class="LABEL_TD">Remarks</td>
					<td class="CONTROL_TD"><textarea rows="2" cols="25"
						name="strServiceProviderRemarks"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
				</tr>
			</table>
		</his:ContentTag> </span>
		<%--======================== Schedule Details End ========================= --%>


		<his:ContentTag name="Footer">
			<table class="TABLE_STYLE">
				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>
		<his:ButtonToolBarTag>
			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-sv.png"/>'
				title="Save Record" style="cursor: pointer"
				onclick="validateSchedule('saveSchedule');"
				onkeypress="if(event.keyCode==13) validateSchedule('saveSchedule');" />

			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-clr.png"/>' title="Clear"
				style="cursor: pointer" onclick="clearSchedule();"
				onkeypress="if(event.keyCode==13) clearSchedule();" />

			<img class="button" tabindex="1"
				src='<his:path src="/hisglobal/images/btn-ccl.png"/>'
				title="Cancel Record" style="cursor: pointer"
				onclick="cancelProcess();"
				onkeypress="if(event.keyCode==13) cancelPage();" />

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
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strComplaintId" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strComplaintType" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strVendorId" />
		<html:hidden name="complaintMaintenanceStatusBean"
			property="strEngineeringItemTypeId"
			styleId="strEngineeringItemTypeId" />
			
			<html:hidden name="complaintMaintenanceStatusBean"
			property="strIsHemDesk" />
			<html:hidden name="complaintMaintenanceStatusBean"
			property="strUploadFileId" />
			
			<html:hidden name="complaintMaintenanceStatusBean"
			property="strPath" />
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>