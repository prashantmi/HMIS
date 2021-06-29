<% 
/* 
 * Module Name: Global - Common 
 * Name of Process: Common Fields JSP 
 * Name of Developer: Sh.Ashwini Mishra
 * Date of Creation: 29-05-2014
 * Name of Last Modifier: Sh. Ashwini Mishra
 * Last Modification Date: 04-12-2015 
 * Version: 1.0
 */
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%--<%@ taglib uri="/WEB-INF/csrfguard.tld" prefix="csrf" %>--%> 
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>



<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<%@page import="hisglobal.utility.HisUtil"%>
<%@ page import="hr.pis.config.PisConfig"%>
 <head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	
	<meta name="referrer" content="no-referrer" />
	<meta http-equiv="X-Frame-Options" content="deny">
	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<!-- <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" /> -->
	<meta http-equiv="pragma" content="no-cache" />

 	
 </head>
<script>
var gEmpNoLen = <%=PisConfig.EMP_NO_LENGTH%>;
var gEmpNoType = <%=PisConfig.EMP_NO_TYPE%>;

var gNojPermanentId = <%=PisConfig.PIST_NATURE_OF_JOB_MST_PERMANENT%>;
var gNojAdhocId = <%=PisConfig.PIST_NATURE_OF_JOB_MST_ADHOC%>;
var gNojContractualId = <%=PisConfig.PIST_NATURE_OF_JOB_MST_CONTRACTUAL%>;
var gNojDeputationId = <%=PisConfig.PIST_NATURE_OF_JOB_MST_DEPUTATION%>;

var gStatusConfirmedId = "<%=PisConfig.PIST_CONFIGURATION_MST_STATUS_CONFIRMED%>";
var gStatusProbationId = "<%=PisConfig.PIST_CONFIGURATION_MST_STATUS_PROBATION%>";
var gStatusProbationExtensionId = "<%=PisConfig.PIST_CONFIGURATION_MST_STATUS_PROBATION_EXTENSION%>";

var gSalTypeGradeId="<%=PisConfig.SALARY_TYPE_ID_FOR_GRADE%>";
var gSalTypeConsolidatedId="<%=PisConfig.SALARY_TYPE_ID_FOR_CONSOLIDATED%>";

var gPstIabId = "<%=PisConfig.PIST_CONFIGURATION_MST_PST_IAB%>";
var gPstGpbId = "<%=PisConfig.PIST_CONFIGURATION_MST_PST_GPB%>";

var gFlagForBasicWithInRange="<%=PisConfig.FLAG_FOR_BASIC_PAY_WITHIN_RANGE%>";

var gVisibilityModeForAftAvailedAndEmergencyFld= <%=PisConfig.LEAVE_MODULE_VALUE_FOR_FIELDS_VISIBILITY_MODE1%>;
var gVisibilityModeForLeaveOrgPeriodFld= <%=PisConfig.LEAVE_MODULE_VALUE_FOR_FIELDS_VISIBILITY_MODE2%>;
var gVisibilityModeForSubstituteFld= <%=PisConfig.LEAVE_MODULE_VALUE_FOR_FIELDS_VISIBILITY_MODE3%>;
var gVisibilityFlagForPowerDelegatedToFld= <%=PisConfig.LEAVE_MODULE_FLAG_FOR_POWER_DELEGATE_TO_FLD_VISIBILITY%>;

</script>
						
<%
	HospitalMstVO objHospitalMstVO = ControllerUTIL.getHospitalVO(request);	
	HisUtil objHisUtil=new HisUtil("Pis","global");
	String currentDate=objHisUtil.getDSDate("DD-Mon-YYYY");
%>

				
				<input type="hidden" id="gIntEmpNoLength" name="gIntEmpNoLength" value ="<%=PisConfig.EMP_NO_LENGTH%>" />
				<input type="hidden" id="gIntEmpNoType" name="gIntEmpNoType" value ="<%=PisConfig.EMP_NO_TYPE%>" />		
				<input type="hidden" id="hospitalCode" name="hospitalCode" value ="<%=objHospitalMstVO.getHospitalCode()%>" />
				<input type="hidden" id="hospitalShortName" name="hospitalShortName" value ="<%=objHospitalMstVO.getHospitalShortName()%>" />
				<input type="hidden" id="hospitalName" name="hospitalName" value ="<%=objHospitalMstVO.getHospitalName()%>" />
				<input type="hidden" id="address1" name="address1" value ="<%=objHospitalMstVO.getAddress1()%>" />
				<input type="hidden" id="address2" name="address2" value ="<%=objHospitalMstVO.getAddress2()%>" />
				<input type="hidden" id="city" name="city" value ="<%=objHospitalMstVO.getCity()%>" />
				<input type="hidden" id="district" name="district" value ="<%=objHospitalMstVO.getDistrictName()%>" />
				<input type="hidden" id="state" name="state" value ="<%=objHospitalMstVO.getStateName()%>" />
				<input type="hidden" id="phone" name="phone" value ="<%=objHospitalMstVO.getPhone()%>" />
				<input type="hidden" id="fax" name="fax" value ="<%=objHospitalMstVO.getFax()%>" />
				<input type="hidden" id="email" name="email" value ="<%=objHospitalMstVO.getEmail()%>" />
				<input type="hidden" id="contactPerson" name="contactPerson" value ="<%=objHospitalMstVO.getContactPerson()%>" />
				<input type="hidden" id="dtCurrentDate" name="dtCurrentDate" value ="<%=currentDate%>" />
				<s:hidden id="strDateTimeLabel" name="strDateTimeLabel" value="%{getText('global.dateTime')}" />
				<s:hidden id="strRptFooter" name="strRptFooter" value="%{getText('global.reportFooter')}" />
				<s:hidden id="strRptFooterCGF" name="strRptFooterCGF" value="%{getText('global.reportFooter_CGF')}" />
				
		
	

