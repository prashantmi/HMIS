
<%
try {
%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@page import="registration.*"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/reports/report1/js/dssReports.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
window.onload = function()
{
	onChangeDurMode();
}
</script>


<html:form action="/reports/report1/dssParameterConfigurator">

<bean:define name="DSSParameterConfiguratorFB" property="strFromDate" id="frDate" type="java.lang.String" />
<bean:define name="DSSParameterConfiguratorFB" property="strToDate" id="toDate" type="java.lang.String" />

<his:SubTitleTag name="Report Details">
</his:SubTitleTag>

<logic:equal name="DSSParameterConfiguratorFB" property="strParaFormType" value="<%=RegistrationConfig.DSS_REPORTS_PARAMETER_FORM_TYPE_ALL_LISTS%>">

	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="hospital" />
					</font>
				</div>	
			</td>
			<td class="tdfont" width="25%">
				<div>
					<logic:equal name="DSSParameterConfiguratorFB" property="strIsConsolidated" value="<%=RegistrationConfig.YES%>">
						<html:select name="DSSParameterConfiguratorFB" property="arrayHospitalCode" tabindex="1" multiple="true" value="%">
							<html:option value="%">All Hospital</html:option>
						</html:select>
					</logic:equal>
					<logic:equal name="DSSParameterConfiguratorFB" property="strIsConsolidated" value="<%=RegistrationConfig.NO%>">
						<html:select name="DSSParameterConfiguratorFB" property="arrayHospitalCode" tabindex="1"   multiple="true" value='<%=(String)session.getAttribute("HOSPITAL_CODE")  %>'>
							<html:option value="%">All Hospital</html:option>
							<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
								<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</logic:equal>
				</div>
			</td>	
			<td class="tdfonthead" nowrap width="25%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="department" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<html:select name="DSSParameterConfiguratorFB" property="arrayDepartmentCode" tabindex="1"   multiple="true" value="%">
					<html:option value="%">All Department</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>">
						<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT%>" property="value" labelProperty="label" />
					</logic:present>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="tdfonthead" nowrap width="25%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="category" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<html:select name="DSSParameterConfiguratorFB" property="arrayPatCatCode" tabindex="1"  multiple="true" value="%">
					<html:option value="%">All Category</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>">
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY%>" property="value" labelProperty="label" />
					</logic:present>
				</html:select>
			</td>
			<td class="tdfonthead" nowrap width="25%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="caste" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<html:select name="DSSParameterConfiguratorFB" property="arrayPatientCaste" tabindex="1"  multiple="true" value="%">
					<html:option value="%">All Caste</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_CASTE%>">
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_CASTE%>" property="value" labelProperty="label" />
					</logic:present>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="tdfonthead" nowrap width="25%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="religion" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<html:select name="DSSParameterConfiguratorFB" property="arrayReligion" tabindex="1"  multiple="true" value="%">
					<html:option value="%">All Religion</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION%>">
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_RELIGION%>" property="value" labelProperty="label" />
					</logic:present>
				</html:select>
			</td>
			<td class="tdfonthead" nowrap width="25%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="state" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<html:select name="DSSParameterConfiguratorFB" property="arrayState" tabindex="1"  multiple="true" value="<%=RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE %>">
					<html:option value="%">All State</html:option>
					<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE%>">
						<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_STATE%>" property="value" labelProperty="label" />
					</logic:present>
				</html:select>
			</td>
		</tr>
	 	<tr>
			<td class="tdfonthead" nowrap width="25%" colspan="4">
				<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<html:radio name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_YEARLY%>" onchange="onChangeDurMode()"></html:radio>
						Year-Wise
						&nbsp;&nbsp;&nbsp;<html:radio name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_MONTHLY%>" onchange="onChangeDurMode()"></html:radio>
						Month-Wise
						&nbsp;&nbsp;&nbsp;<html:radio name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_DATEWISE%>" onchange="onChangeDurMode()"></html:radio>
						Date-Wise
					</font>
				</div>
			</td>
		</tr> 
		<tr>
			<td class="tdfonthead" width="25%">
				<div id='divFromYear' style='display: none;' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="frmYear" />
					</font>
				</div>
				<div id='divFromMonth' style='display: none;' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="frmMonth" />
					</font>
				</div>
				<div id='divFromDate' style='' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<div id='divFromYearControl' style='display: none;' align="left">
					<html:select name="DSSParameterConfiguratorFB" property="strFromYear" tabindex="1">
						<logic:present name="<%=RegistrationConfig.DSS_REPORTS_LIST_YEARS%>">
							<html:options collection="<%=RegistrationConfig.DSS_REPORTS_LIST_YEARS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
				<div id='divFromMonthControl' style='display: none;' align="left">
					<html:select name="DSSParameterConfiguratorFB" property="strFromMonth" tabindex="1">
						<logic:present name="<%=RegistrationConfig.DSS_REPORTS_LIST_MONTHS%>">
							<html:options collection="<%=RegistrationConfig.DSS_REPORTS_LIST_MONTHS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
				<div id='divFromDateControl' style='' align="left">
					<his:date name='strFromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" />
				</div>
			</td>
			<td class="tdfonthead" width="25%">
				<div id='divToYear' style='display: none;' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						To Year
					</font>
				</div>
				<div id='divToMonth' style='display: none;' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						To Month
					</font>
				</div>
				<div id='divToDate' style='' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<div id='divToYearControl' style='display: none;' align="left">
					<html:select name="DSSParameterConfiguratorFB" property="strToYear" tabindex="1">
						<logic:present name="<%=RegistrationConfig.DSS_REPORTS_LIST_YEARS%>">
							<html:options collection="<%=RegistrationConfig.DSS_REPORTS_LIST_YEARS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
				<div id='divToMonthControl' style='display: none;' align="left">
					<html:select name="DSSParameterConfiguratorFB" property="strToMonth" tabindex="1">
						<logic:present name="<%=RegistrationConfig.DSS_REPORTS_LIST_MONTHS%>">
							<html:options collection="<%=RegistrationConfig.DSS_REPORTS_LIST_MONTHS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
				<div id='divToDateControl' style='' align="left">
					<his:date name='strToDate' dateFormate="%d-%b-%Y" value="<%=toDate%>" />
				</div>
			</td>
		</tr>
	</table>
		
	</his:ContentTag>
</logic:equal>


<logic:equal name="DSSParameterConfiguratorFB" property="strParaFormType" value="<%=RegistrationConfig.DSS_REPORTS_PARAMETER_FORM_TYPE_EMG_STATS%>">

	<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="hospital" />
					</font>
				</div>	
			</td>
			<td class="tdfont" width="25%">
				<div>
					<logic:equal name="DSSParameterConfiguratorFB" property="strIsConsolidated" value="<%=RegistrationConfig.YES%>">
						<html:select name="DSSParameterConfiguratorFB" property="arrayHospitalCode" tabindex="1" value="%">
							<html:option value="%">All Hospital</html:option>
						</html:select>
					</logic:equal>
					<logic:equal name="DSSParameterConfiguratorFB" property="strIsConsolidated" value="<%=RegistrationConfig.NO%>">
						<html:select name="DSSParameterConfiguratorFB" property="arrayHospitalCode" tabindex="1" value='<%=(String)session.getAttribute("HOSPITAL_CODE")%>'>
							<html:option value="%">All Hospital</html:option>
							<logic:present name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
								<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</logic:equal>
				</div>
			</td>	
			<td class="tdfonthead" nowrap width="25%">
			</td>
			<td class="tdfont" width="25%">
			</td>
		</tr>
	 	<tr>
			<td class="tdfonthead" nowrap width="25%">
				<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="type" />
					</font>
				</div>
			</td>
			<td class="tdfont" nowrap width="25%" colspan="3">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<html:radio name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_YEARLY%>" onchange="onChangeDurMode()"></html:radio>
						Year-Wise
						&nbsp;&nbsp;&nbsp;<html:radio name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_MONTHLY%>" onchange="onChangeDurMode()"></html:radio>
						Month-Wise
						&nbsp;&nbsp;&nbsp;<html:radio name="DSSParameterConfiguratorFB" property="strDurationMode" value="<%=RegistrationConfig.DSS_REPORTS_DURATION_MODE_DATEWISE%>" onchange="onChangeDurMode()"></html:radio>
						Date-Wise
					</font>
				</div>
			</td>
		</tr> 
		<tr>
			<td class="tdfonthead" width="25%">
				<div id='divFromYear' style='display: none;' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="frmYear" />
					</font>
				</div>
				<div id='divFromMonth' style='display: none;' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="frmMonth" />
					</font>
				</div>
				<div id='divFromDate' style='' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="fromDate" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<div id='divFromYearControl' style='display: none;' align="left">
					<html:select name="DSSParameterConfiguratorFB" property="strFromYear" tabindex="1">
						<logic:present name="<%=RegistrationConfig.DSS_REPORTS_LIST_YEARS%>">
							<html:options collection="<%=RegistrationConfig.DSS_REPORTS_LIST_YEARS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
				<div id='divFromMonthControl' style='display: none;' align="left">
					<html:select name="DSSParameterConfiguratorFB" property="strFromMonth" tabindex="1">
						<logic:present name="<%=RegistrationConfig.DSS_REPORTS_LIST_MONTHS%>">
							<html:options collection="<%=RegistrationConfig.DSS_REPORTS_LIST_MONTHS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
				<div id='divFromDateControl' style='' align="left">
					<his:date name='strFromDate' dateFormate="%d-%b-%Y" value="<%=frDate%>" />
				</div>
			</td>
			<td class="tdfonthead" width="25%">
				<div id='divToYear' style='display: none;' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						To Year
					</font>
				</div>
				<div id='divToMonth' style='display: none;' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						To Month
					</font>
				</div>
				<div id='divToDate' style='' align="right">
					<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="toDate" />
					</font>
				</div>
			</td>
			<td class="tdfont" width="25%">
				<div id='divToYearControl' style='display: none;' align="left">
					<html:select name="DSSParameterConfiguratorFB" property="strToYear" tabindex="1">
						<logic:present name="<%=RegistrationConfig.DSS_REPORTS_LIST_YEARS%>">
							<html:options collection="<%=RegistrationConfig.DSS_REPORTS_LIST_YEARS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
				<div id='divToMonthControl' style='display: none;' align="left">
					<html:select name="DSSParameterConfiguratorFB" property="strToMonth" tabindex="1">
						<logic:present name="<%=RegistrationConfig.DSS_REPORTS_LIST_MONTHS%>">
							<html:options collection="<%=RegistrationConfig.DSS_REPORTS_LIST_MONTHS%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</div>
				<div id='divToDateControl' style='' align="left">
					<his:date name='strToDate' dateFormate="%d-%b-%Y" value="<%=toDate%>" />
				</div>
			</td>
		</tr>
	</table>
		
	</his:ContentTag>
</logic:equal>


<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' style="cursor:pointer" tabindex="1" onclick = "submitReport(validateReportConfig());" onkeypress="if(event.keyCode==13) submitReport(validateReportConfig());"/>         
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="cancelReport()" onkeypress="if(event.keyCode==13) cancelReport()"/>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick ="clearReport()" onkeypress="if(event.keyCode==13) clearReport();"/>
</his:ButtonToolBarTag>

<html:hidden name="DSSParameterConfiguratorFB" property="hmode"/>
<html:hidden name="DSSParameterConfiguratorFB" property="strMode"/>
<html:hidden name="DSSParameterConfiguratorFB" property="strIsConsolidated"/>

</html:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

