<%@page import="hisglobal.hisconfig.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<his:TitleTag name="Complaint Schedule Details">
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
					<td width="25%" class="LABEL_TD">Item Serial No.</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strItemBatchNo}</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Manufacturer/Supplier Name</td>
					<td class="CONTROL_TD">${hospitalWorkshopDeskFB.strManufacturerName}</td>
					<td class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD" rowspan="3">${hospitalWorkshopDeskFB.strComplaintDescription}</td>
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
			     <tr>
					<td width="25%" class="LABEL_TD" >Remarks</td>
					<td width="25%" class="CONTROL_TD" colspan="3">${hospitalWorkshopDeskFB.strRemarks}</td>
				</tr>
		</table>
		</his:ContentTag>
		<his:ContentTag name="HEMM Acknowledgment">
			<table class="TABLE_STYLE">
			<tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strIsHEMAcknowledge}</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${hospitalWorkshopDeskFB.strAckRemarks}</td>
			</tr>
			     
			</table>
		</his:ContentTag>	
		
		
		<%-- ************** Schedule Details Start ***************** --%>
		<his:ContentTag name="Schedule Details">
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Expected Visit Date
					</td>
					<td width="25%" class="CONTROL_TD"><his:date
						name="strScheduleDate" value=""></his:date></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Expected Visit Time</td>
					<td width="25%" class="CONTROL_TD">
					<input style="display: none;" type="text"
						name="strScheduleTimeOld" class="TEXT_FIELD_MIN" maxlength="5"
						onchange="return validateDataWithSpecialChars(event,5,':');"
						onkeypress='return checkTime(event,this);'>
					<select name="strScheduleTime" class="COMBO_MIN">
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
					  	<select class="COMBO_SMALL" name="strScheduleTimeMeridian"><OPTION value="AM">AM</OPTION><OPTION value="PM">PM</OPTION></select>
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Complaint Redressal Details<font color="red">*</font></td>
					<td class="CONTROL_TD"><textarea rows="2" cols="25"
						name="strServiceEngineerRemarks"
						onkeypress="return validateDataWithSpecialChars(event,9,'.,,');"
						onchange="return validateDataWithSpecialChars(event,9,'.,,');"></textarea></td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Intimation
					Date</td>
					<td width="25%" class="CONTROL_TD"><his:date
						name="strIntimationDate" value=""></his:date></td>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Intimation
					Time</td>
					<td width="25%" class="CONTROL_TD"><input type="text" style="display: none;"
						name="strIntimationTimeOld" class="TEXT_FIELD_MIN" maxlength="5"
						onchange="return validateDataWithSpecialChars(event,5,':');"
						onkeypress='return checkTime(event,this);'>
					<select name="strIntimationTime" class="COMBO_MIN">
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
					  	<select class="COMBO_SMALL" name="strIntimationTimeMeridian"><OPTION value="AM">AM</OPTION><OPTION value="PM">PM</OPTION></select>
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Person contacted</td>
					<td class="CONTROL_TD"><input type="text"
						name="strContactPerson" class='TEXT_FIELD_MAX' value=""
						maxlength="100" onkeypress="return validateData(event,4);"
						onchange="return validateData(event,4);"></td>
					<td class="LABEL_TD"><font color="red">*</font>Contact No.</td>
					<td class="CONTROL_TD"><input type="text" name="strContactNo"
						class='TEXT_FIELD_MAX' value="" maxlength="10"
						onkeypress="return validateData(event,2);"
						onchange="return validateData(event,2);"></td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Company Name and Address</td>
					<td class="CONTROL_TD">
					<textarea rows="2" cols="25"
						name="strCompanyName" 
						onkeypress="return validateDataWithSpecialChars(event,3,'.');"
						onchange="return validateDataWithSpecialChars(event,3,'.');"></textarea>
					</td>
					<td class="LABEL_TD"><font color="red">*</font>Company E-mail Id</td>
					<td class="CONTROL_TD"><input type="text" name="strCompanyAddress"
						class='TEXT_FIELD_MAX' value="" maxlength="100"
						onkeypress="return validateDataWithSpecialChars(event,1,'@,.');"
						onchange="return validateDataWithSpecialChars(event,1,'@,.');"></td>
				</tr>
				<tr>
					<td class="LABEL_TD"><font color="red">*</font>Communication Medium</td>
					<td class="CONTROL_TD">
					<select name="strCommunicationId" class="COMBO_MAX">
					<bean:write name="hospitalWorkshopDeskFB"
							property="strCommunicationCmb" filter="false" />
					</select>
					</td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
				<tr style="display: none;">
					<td class="LABEL_TD"><font color="red">*</font>Vendor Id</td>
					<td class="CONTROL_TD"><input type="text"
						name="strVendorId" class="TEXT_FIELD_MAX" maxlength="50"
						onchange="return validateData(event,8);"
						onkeypress="return validateData(event,8);"></td>
					<td class="LABEL_TD"><font color="red">*</font>Communication
					Id</td>
					<td class="CONTROL_TD"><input type="text"
						name="strCommunicationIdOld" class="TEXT_FIELD_MAX" maxlength="50"
						onchange="return validateData(event,8);"
						onkeypress="return validateData(event,8);"></td>
				</tr>
				<tr>
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD"><textarea rows="2" cols="25"
						name="strServiceProviderRemarks"
						onkeypress="return validateDataWithSpecialChars(event,9,'.');"
						onchange="return validateDataWithSpecialChars(event,9,'.');"></textarea></td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
			</table>
		</his:ContentTag> 
		<%--======================== Schedule Details End ========================= --%>
		
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
			<input type="hidden" name="strComplaintDate" id="strComplaintDate" value="${hospitalWorkshopDeskFB.strComplaintDate}">
		
		<html:hidden name="hospitalWorkshopDeskFB" property="strComplaintId" />
		<html:hidden name="hospitalWorkshopDeskFB" property="strHemDesk" />
		<input type="hidden" name="strRetValue" value="${hospitalWorkshopDeskFB.strRetValue}" />
		<input type="hidden" name="strMsgString" value="${hospitalWorkshopDeskFB.strMsgString}" />
	</his:TransactionContainer>
	<cmbPers:cmbPers />
</html:form>
</body>
</html>