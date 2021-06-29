<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>


<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ page import="java.util.*,registration.*,opd.*,hisglobal.presentation.*"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/reportsValidation.js" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
	<!-- 
/**
 * @author Singaravelan
 * Date of Creation : 09-Sep-2013
 * Version : 
 * Module  : OPD
 */
 -->

function opdPatientAttendedReportHandler()
{
	var jrxmlName="";
	jrxmlName="<%=OpdConfig.OPD_PATIENT_ATTENDED_WAITING%>";	
	document.getElementsByName('jrxmlName')[0].value=jrxmlName;
	var success=true;
	
    if(validateGraphOrText())
    { 
    	if(document.getElementsByName('graphOrText')[0].selectedIndex==1)
		{
			document.getElementsByName('reportMode')[0].value="<%=Config.TEXT%>";
	        
        }//end of selection is for text based report	   		
		  
    }  
    if(document.getElementsByName('userCode')[0].value==-1)
    {
    	alert("Please select the User");
    	success=false;
    	document.getElementsByName('userCode')[0].focus();
    }
        
    return success;
}
	
function userCombo()
{
document.forms[0].reportMode.value="USERLIST";
document.forms[0].submit();
}	
</script>

<body  >
<his:TitleTag name="OPD Patient Attended/Waiting">
</his:TitleTag>
<his:SubTitleTag name="Report Details">
</his:SubTitleTag>
<%String sysdate= WebUTIL.getCustomisedSysDate((Date) session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");%>
<his:ContentTag>
	<table width="100% cellspacing=" 1" cellpadding="0">
		<tr>
			<td class="tdfonthead" width="25%">
			<div id='divfromDate' style='' align="right"><font
				color="#FF0000" size="1"
				face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="fromDate" /> </font></div>
			</td>

			<td class="tdfont" width="25%">
			<div id='divfromDateControl' style='' align="left"><his:date
				name='fromDate' dateFormate="%d-%b-%Y" value="<%=sysdate%>" /></div>
			</td>

			<td class="tdfonthead" width="25%">
			<div id='divtoDate' style='' align="right"><font
				color="#FF0000" size="1"
				face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="toDate" /> </font></div>
			</td>

			<td class="tdfont" width="25%">
			<div id='divtoDateControl' style='' align="left"><his:date
				name='toDate' dateFormate="%d-%b-%Y" value="<%=sysdate%>" /></div>
			</td>
		</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
      <tr>   		
		<td class="tdfonthead" nowrap width="25%">
			<div align="right" ><font
				color="#FF0000" size="1"
				face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="user" /> </font></div>				
			</td>
			<td class="tdfont" width="25%">
			<div  >
			<html:select
				name="OPDPatientAttendedReportFB" property="userCode" tabindex="1"
				styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIALBO_OPTION_USER%>">
					<html:options collection="<%=RegistrationConfig.ESSENTIALBO_OPTION_USER%>"
						property="value" labelProperty="label" />
				</logic:present>
			</html:select></div>			
			</td>   
      		<td class="tdfonthead" nowrap width="25%">
			<div align="right" ><font
				color="#FF0000" size="1"
				face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
				color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="department" /> </font></div>		
			</td>
			<td class="tdfont" width="25%">
			<div  >
			<html:select
				name="OPDPatientAttendedReportFB" property="departmentCode" tabindex="1"
				styleClass="regcbo">
				<html:option value="%">All</html:option>
				<logic:present name="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>">
					<html:options collection="<%=RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT%>"
						property="value" labelProperty="label" />
				</logic:present>
			</html:select></div>			
			</td>
			<td class="tdfonthead" nowrap width="25%">
			</td>
			<td class="tdfont" width="25%">
			</td>
      </tr>     
	</table>
</his:ContentTag>
<his:SubTitleTag name="Report Generation Option">
</his:SubTitleTag>
<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead"><font color="#FF0000">*</font>
			<font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="reportType" /> </font></td>
			<td width="25%" class="tdfont"><html:select
				name="OPDPatientAttendedReportFB" property="graphOrText"
				tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<html:option value="<%=Config.TEXT%>">Textual</html:option>
			</html:select></td>
			<td width="25%" class="tdfonthead"><font color="#FF0000">*</font>
			<font color="#000000" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
				key="pdfOrWord" /> </font></td>
			<td width="25%" class="tdfont"><html:select
				name="OPDPatientAttendedReportFB" property="reportType"
				tabindex="1" styleClass="regcbo">
				<html:option value="-1">Select Value</html:option>
				<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
				<html:option value="<%=Config.RTF %>">HTML</html:option>
			</html:select></td>
		</tr>
	</table>
</his:ContentTag>
<input type="hidden" name="reportMode">
<input type="hidden" name="hmode">
<input type="hidden" name="mode" value="OPDPATIENTATTENDED">
<html:hidden name="OPDPatientAttendedReportFB"
	property="jrxmlPath"
	value="<%=OpdConfig.OPD_JRXML_PATH %>" />
<html:hidden name="OPDPatientAttendedReportFB"
	property="jrxmlName" />
<html:hidden name="OPDPatientAttendedReportFB" property="sysDate"
	value="<%=sysdate%>" />