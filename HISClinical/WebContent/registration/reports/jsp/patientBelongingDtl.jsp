<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>	
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>	
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ page import ="registration.*" %>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<script>
	
	function submitPage(mode)
	{
		elmt=document.getElementsByName("reportMode")[0];  
	    elmt.value=mode;
	    document.forms[0].submit();
	}
	
	function patientBelongingReportHandler()
	{
		document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.PATIENT_BELONGING_DETAIL%>";
		document.getElementsByName("reportMode")[0].value="<%=Config.TEXT%>";
			var patCrNo=document.getElementsByName("patCrNo")[0].value;
			var len=patCrNo.length;
		//	alert("len>>"+len)
			if(len!=<%=Config.CR_NO_FORMAT_SIZE%>)
			{
				alert("Enter a Valid CR. No. ");
				document.getElementsByName("patCrNo")[0].focus();
				return false;
			}
			
			if(validateTextual())
			{
				return true;
			}	
	}
	
	</script>
	
	<his:TitleTag name="Particular Patient Belonging Detail " >
	</his:TitleTag>
	
	<his:SubTitleTag name="Report Details">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="crNo" />
						 </font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						 <%String size=Integer.toString(Integer.parseInt(Config.CR_NO_FORMAT_SIZE)+2); %>
						 <%System.out.println("cr no size>>"+Config.CR_NO_FORMAT_SIZE); %>
							<html:text name="patientBelongingDtlFB" property="patCrNo" maxlength="<%=Config.CR_NO_FORMAT_SIZE %>" size="<%=size %>" value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>" tabindex="1" onkeypress="return validateNumeric(event)">
							</html:text>
					</div>
				</td>
				<td width="25%" class="tdfont"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
		</table>
	</his:ContentTag>
	
	<his:SubTitleTag name="Report Generation Option">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<font color="#FF0000">*</font>
	        		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	          		<bean:message key="pdfOrWord"/>
	        	</font>
	        	</td>
    	    	<td width="25%" class="tdfont">
        			<html:select name="patientBelongingDtlFB" property="reportType" tabindex="1" styleClass="regcbo">
						<html:option value="-1">Select Value</html:option>
						<html:option value="<%=Config.PDF %>">Acrobat Reader</html:option>
						<html:option value="<%=Config.RTF %>">HTML</html:option>
	        		</html:select>
    	    	</td>
    	    	<td width="25%" class="tdfont"></td>
    	    	<td width="25%" class="tdfont"></td>
			</tr>
		</table>
	</his:ContentTag>
	
	
<html:hidden name="patientBelongingDtlFB" property="reportMode"/>
<html:hidden name="patientBelongingDtlFB" property="mode" value="PATIENTBELONGINGDTLREPORT"/>
<html:hidden name="patientBelongingDtlFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH %>"/>
<html:hidden name="patientBelongingDtlFB" property="jrxmlName"/>
<html:hidden name="patientBelongingDtlFB" property="patCrNo"/>
<input type="hidden" name="fromHour" > 
<input type="hidden" name="fromMin" > 
<input type="hidden" name="toHour" > 
<input type="hidden" name="toMin" > 

	