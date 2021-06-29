
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="java.util.Date"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/hisglobal/js/validationCommon.js" />
<his:javascript src="/hisglobal/js/validationCalls.js" />
<his:javascript src="/hisglobal/js/dateFunctions.js" />
<his:javascript src="/bloodbank/js/bloodDonation.js" />
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/masterXml.css" />
<his:javascript src="/bloodbank/js/bloodBankMstAddMod.js" />

<script>
function submitForm(transactionMode)
{

document.forms[0].transactionMode.value=transactionMode;
document.forms[0].submit();
}

</script>
<body>
		<html:form action="/master/disclaimerMaster">
		
		<html:hidden name="AddDisclaimerFB" property="transactionMode" />
		




<table cellpadding="0" cellspacing="1" width="100%">
		<thead style='DISPLAY: TABLE-HEADER-GROUP'>
			<tr>

				<td>
					<div align="right" id="noPrint">
						<a>
							<img src='/HIS/hisglobal/images/buttons/btn-pnt.png' onKeyPress="" onClick=''>
						</a>
						<a>
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('CANCEL_REPORT');" tabindex="1" onclick ="submitForm('CANCEL_REPORT');">
						</a>
					</div>

				</td>
			</tr>
			<tr>
				<td colspan='alcol.size()'>
					<div align="center">
						<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>
							<b>
							
							Report for Default Disclaimer Master
							</b>

						</font>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan='alcol.size()'>
					<div align="right">
						<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>
							 <b> <bean:message key="date" /> 
			     <bean:message key="and" /> 
			     <bean:message key="time" /> 
			     <bean:write name="<%=Config.SYSDATE%>" /> 
			   </b>
					</div>
				</td>
			</tr>
			
			
			<tr>
				
				<td colspan='alcol.size()'>
					<font size='2' face='Verdana, Arial, Helvetica, sans-serif'>Record Status::<b>ACTIVE</b></font>
				</td>
				
			</tr>

		</thead>
	</table>
	
	<table width="100%">
		<tr>
		
			<td colspan='alcol.size()'>
				<font size='2' face='Verdana, Arial, Helvetica, sans-serif'><b><bean:message key="defaultDisclaimerType"/></b></font>
			</td>
		
			<td colspan='alcol.size()'>

				<font size='2' face='Verdana, Arial, Helvetica, sans-serif'><b><bean:message key="disclaimer1"/></b></font>
			</td>
		
			<td colspan='alcol.size()'>
				<font size='2' face='Verdana, Arial, Helvetica, sans-serif'><b><bean:message key="disclaimer2"/></b></font>
			</td>
		
			<td colspan='alcol.size()'>
				<font size='2' face='Verdana, Arial, Helvetica, sans-serif'><b><bean:message key="disclaimer3"/></b></font>

			</td>
		
		</tr>
		
		<tr>
					
					<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="normal"/>
						</font>
						</div>
						</td>
						
					   <td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="generalDisclaimerDesc1"/>
						
						</font>
						</div>
						</td>
						
						<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="generalDisclaimerDesc2"/>
						</font>
						</div>
						</td>
						
						<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="generalDisclaimerDesc3" />
						</font>
						</div>
						</td>
						</tr>
		
		<tr>
					
					<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="special"/>
						</font>
						</div>
						</td>
						
					   <td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="specialDisclaimerDesc1"/>
						
						</font>
						</div>
						</td>
						
						<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="specialDisclaimerDesc2"/>
						</font>
						</div>
						</td>
						
						<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="specialDisclaimerDesc3" />
						</font>
						</div>
						</td>
						</tr>
		
		<tr>
					
					<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:message key="casuality"/>
						</font>
						</div>
						</td>
						
					   <td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="casualityDisclaimerDesc1"/>
						
						</font>
						</div>
						</td>
						
						<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="casualityDisclaimerDesc2"/>
						</font>
						</div>
						</td>
						
						<td colspan='alcol.size()'>
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="casualityDisclaimerDesc3" />
						</font>
						</div>
						</td>
						</tr>
		
	<table>	
	
	
	
	
</html:form>

</body>
</html>
