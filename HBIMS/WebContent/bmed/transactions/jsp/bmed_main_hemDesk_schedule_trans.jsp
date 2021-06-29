<%@page import="hisglobal.hisconfig.Config"%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<!-- 
/**
 * @author Saratkumar and P.P. Chattaraj
 * Date of Creation : 27-Aug-2013
 * Date of Modification : 19-Nov-2013 
 * Version : 
 * Module  : HEMMS Product 1.0 [HEMM Desk]
 */
 -->
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<title>Complaint Schedule Desk</title>
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
<his:javascript src="/bmed/transactions/js/bmed_HemDesk_trans.js" />
</head>
<body marginheight="0" marginwidth="0" onload="chkRecordSaved()"
	class="background">
<html:form name="hemDeskFB" action="/transactions/HemDeskACTION"
	type="bmed.transactions.controller.fb.HemDeskFB" styleClass="formbg">
	<div id="strErrMsg" align="center" class="errMsg"><bean:write
		name="hemDeskFB" property="strErrMsg" /></div>
	<div id="strWarningMsg" align="center" class="warningMsg"><bean:write
		name="hemDeskFB" property="strWarningMsg" /></div>
	<div style="display: none;" id="normalMsg" align="center"
		class="normalMsg"><bean:write name="hemDeskFB"
		property="strNormalMsg" /></div>
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr class="HEADER">
			<td colspan="4">Complaint Schedule Details&gt;&gt; Add</td>
		</tr>
	</table>
	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td colspan="4">Request/Complaint Details</td>
		</tr>
	</table>
	</div>



	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr style="display: none;">
			<td width="25%" class="LABEL">Complaint Type</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintType}</td>
			<td width="25%" class="LABEL"></td>
			<td width="25%" class="CONTROL"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Complaint Id</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintId}</td>
			<td width="25%" class="LABEL">Complaint Date</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintDate}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Hospital Name</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strStoreName}</td>
			<td width="25%" class="LABEL">Lab Name</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strLabName}</td>
		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Engineering Item Type</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEnggItemType}</td>
			<td width="25%" class="LABEL">Engineering Item Sub Type</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strEnggItemSubType}</td>
		</tr>
		<tr>
			<td class="LABEL">Equipment Name</td>
			<td class="CONTROL">${hemDeskFB.strItemName}</td>
			<td class="LABEL">Equipment Model</td>
			<!--<td class="CONTROL">${hemDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
			<td class="CONTROL">${hemDeskFB.strItemModel}</td>
		</tr>
		<tr>
			<td class="LABEL">UIN</td>
			<td class="CONTROL">${hemDeskFB.strEquipmentUIDNo}</td>
			<td width="25%" class="LABEL">Item Serial No.</td>
			<td class="CONTROL">${hemDeskFB.strItemBatchNo}</td>
		</tr>
		<tr>
			<td class="LABEL">Manufacturer/Supplier Name</td>
			<td class="CONTROL">${hemDeskFB.strManufacturerName}</td>
			<td class="LABEL">Complaint Description</td>
			<td class="CONTROL" rowspan="3">${hemDeskFB.strComplaintDescription}</td>
		</tr>
	</table>



	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('warrantyDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Guarantee Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="warrantyDetailsTable"
		style="display: none;">
		<bean:write name="hemDeskFB" property="strWarrantyDetailsTable"
			filter="false" />
	</table>


	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('maintenanceContractDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Maintenance Contract
			Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="maintenanceContractDetailsTable"
		style="display: none;">
		<bean:write name="hemDeskFB"
			property="strMaintenanceContractDetailsTable" filter="false" />
	</table>


	<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="5%" style="text-align: center;"><img alt="Show"
				src="../../hisglobal/images/plus.gif"
				style="cursor: pointer;"
				onclick="tableShow('acknowledgeDetailsTable',this);"></td>
			<td width="95%" style="text-align: left;">Complaint
			Acknowledgment and Approval Details</td>
		</tr>
	</table>
	</div>

	<table class="TABLEWIDTH" id="acknowledgeDetailsTable"
		style="display: none;">
		<tr>
			<td width="25%" class="LABEL">Acknowledgment</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIsAcknowledge}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strRemarks}</td>

		</tr>
		<tr style="display: none;">
			<td width="25%" class="LABEL">Complaint Nature</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintNature}</td>
			<td width="25%" class="LABEL">Action Taken</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strActionTaken}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Complaint Nature</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strComplaintNature}</td>
			<td width="25%" class="LABEL">Approved</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIsApproved}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL" colspan="3">${hemDeskFB.strRemarks}</td>
		</tr>
	</table>

	
	<%-- ************** Schedule Details Start ***************** --%>
<div class="line">
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="100%" style="text-align: left;">Complaint Schedule
			Details</td>
		</tr>
	</table>
	</div>
	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">
		<tr>
			<td width="25%" class="LABEL">Acknowledgment</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strIsHEMAcknowledge}</td>
			<td width="25%" class="LABEL">Remarks</td>
			<td width="25%" class="CONTROL">${hemDeskFB.strAckRemarks}</td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Expected
			Visit Date</td>
			<td width="25%" class="CONTROL"><his:date name="strScheduleDate"
				value=""></his:date></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Expected
			Visit Time</td>
			<td width="25%" class="CONTROL"><input style="display: none;"
				type="text" name="strScheduleTimeOld" class="TEXT_FIELD_MIN"
				maxlength="5"
				onchange="return validateDataWithSpecialChars(event,5,':');"
				onkeypress='return checkTime(event,this);'> <select
				name="strScheduleTime" class="COMBO_MIN">
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
			</select> <select class="COMBO_SMALL" name="strScheduleTimeMeridian">
				<OPTION value="AM">AM</OPTION>
				<OPTION value="PM">PM</OPTION>
			</select></td>
		</tr>
		<tr>
			<td class="LABEL">Complaint Redressal Details<font color="red">*</font></td>
			<td class="CONTROL"><textarea rows="2" cols="25"
				name="strServiceEngineerRemarks"
				onkeypress="return validateDataWithSpecialChars(event,9,'.,,');"
				onchange="return validateDataWithSpecialChars(event,9,'.,,');"></textarea></td>
			<td class="LABEL"></td>
			<td class="CONTROL"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Intimation
			Date</td>
			<td width="25%" class="CONTROL"><his:date
				name="strIntimationDate" value=""></his:date></td>
			<td width="25%" class="LABEL"><font color="red">*</font>Intimation
			Time</td>
			<td width="25%" class="CONTROL"><input type="text"
				style="display: none;" name="strIntimationTimeOld"
				class="TEXT_FIELD_MIN" maxlength="5"
				onchange="return validateDataWithSpecialChars(event,5,':');"
				onkeypress='return checkTime(event,this);'> <select
				name="strIntimationTime" class="COMBO_MIN">
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
			</select> <select class="COMBO_SMALL" name="strIntimationTimeMeridian">
				<OPTION value="AM">AM</OPTION>
				<OPTION value="PM">PM</OPTION>
			</select></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Person contacted</td>
			<td class="CONTROL"><input type="text" name="strContactPerson"
				class='TEXT_FIELD_MAX' value="" maxlength="50"
				onkeypress="return validateData(event,4);"
				onchange="return validateData(event,4);"></td>
			<td class="LABEL"><font color="red">*</font>Contact No.</td>
			<td class="CONTROL"><input type="text" name="strContactNo"
				class='TEXT_FIELD_MAX' value="" maxlength="10"
				onkeypress="return validateData(event,2);"
				onchange="return validateData(event,2);"></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Company Name and
			Address</td>
			<td class="CONTROL"><textarea rows="2" cols="25"
				name="strCompanyName"
				onkeypress="return validateDataWithSpecialChars(event,3,'.');"
				onchange="return validateDataWithSpecialChars(event,3,'.');"></textarea>
			</td>
			<td class="LABEL"><font color="red">*</font>Company E-mail Id</td>
			<td class="CONTROL"><input type="text" name="strCompanyAddress"
				class='TEXT_FIELD_MAX' value="" maxlength="100"
				onkeypress="return validateDataWithSpecialChars(event,1,'@,.');"
				onchange="return validateDataWithSpecialChars(event,1,'@,.');"></td>
		</tr>
		<tr>
			<td class="LABEL"><font color="red">*</font>Communication Medium</td>
			<td class="CONTROL"><select name="strCommunicationId"
				class="COMBO_MAX">
				<bean:write name="hemDeskFB" property="strCommunicationCmb"
					filter="false" />
			</select></td>
			<td class="LABEL"></td>
			<td class="CONTROL"></td>
		</tr>
		<tr style="display: none;">
			<td class="LABEL"><font color="red">*</font>Vendor Id</td>
			<td class="CONTROL"><input type="text" name="strVendorId"
				class="TEXT_FIELD_MAX" maxlength="50"
				onchange="return validateData(event,8);"
				onkeypress="return validateData(event,8);"></td>
			<td class="LABEL"><font color="red">*</font>Communication Id</td>
			<td class="CONTROL"><input type="text"
				name="strCommunicationIdOld" class="TEXT_FIELD_MAX" maxlength="50"
				onchange="return validateData(event,8);"
				onkeypress="return validateData(event,8);"></td>
		</tr>
		<tr>
			<td class="LABEL">Remarks</td>
			<td class="CONTROL"><textarea rows="2" cols="25"
				name="strServiceProviderRemarks"
				onkeypress="return validateDataWithSpecialChars(event,9,'.');"
				onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
			<td class="LABEL"></td>
			<td class="CONTROL"></td>
		</tr>
	</table>

	<%--======================== Schedule Details End ========================= --%>

	<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px"
		cellspacing="1px">

		<tr class="FOOTER">
			<td></td>
		</tr>
	</table>
		<!-- <div>
	<div class="legends"><font size="2" color="red">*</font>
	Mandatory Fields</div>
	<div class="control_button">
	<table class="TABLEWIDTH" align="center">
		<tr>
			<td align="center">
			<div><a href="#" class="button" onClick="return validate1();"><span
				class="save">Save</span></a> <a href="#" class="button"
				onclick="clearPage();"><span class="clear">Clear</span></a> <a
				href="#" class="button" onClick="cancelPage();"><span
				class="back">Back</span></a></div>
			</td>
		</tr>
	</table>
	</div>
	</div> -->
	
	<table border="0" class="TABLEWIDTH" align="center">
		<tr class="FOOTER">
			<td colspan="2"><font size="2" color="red">*</font> Mandatory
			Fields</td>
		</tr>
		<tr>
			<td align="center"><img src="../../hisglobal/images/btn-sv.png"
				style="cursor: pointer; cursor: hand;" title="Save Record"
				onClick="return validate1();"/> <img
				src="../../hisglobal/images/btn-clr.png"
				style="cursor: pointer; cursor: hand;" title="Clear Process"
				onclick="clearPage();"/>  <img
				src="../../hisglobal/images/btn-ccl.png"
				style="cursor: pointer; cursor: hand;" title="Cancel Process"
				onClick="cancelPage();"></td>
		</tr>
	</table>

	
	
	

	<!-- Error Messages 	-->
	<br />

	<input type="hidden" name="hmode" />
	<input type="hidden" name="strPath" value="${hemDeskFB.strPath}">
	<input type="hidden" name="strChk" value="${hemDeskFB.strChk}">
	<input type="hidden" name="strEscDtl" id="strEscDtl" value="">
	<input type="hidden" name="strReqType" id="strReqType"
		value="${hemDeskFB.strReqType}">
	<input type="hidden" name="strComplaintStatus" id="strComplaintStatus"
		value="${hemDeskFB.strComplaintStatus}">
	<input type="hidden" name="strComplaintDate" id="strComplaintDate"
		value="${hemDeskFB.strComplaintDate}">

	<html:hidden name="hemDeskFB" property="strComplaintId" />
	<html:hidden name="hemDeskFB" property="strHemDesk" />
	<input type="hidden" name="strRetValue"
		value="${hemDeskFB.strRetValue}" />
	<input type="hidden" name="strMsgString"
		value="${hemDeskFB.strMsgString}" />
	<cmbPers:cmbPers />
</html:form>
</body>
</html>