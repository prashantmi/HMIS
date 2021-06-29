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
	
	function showMlcNo()
	{
		submitPage('GO');
	}
	
	function mlcPoliceInfoProformaReportHandler()
	{
		document.getElementsByName('jrxmlName')[0].value="<%=RegistrationConfig.MLC_POLICE_INFO_PROFORMA%>";
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
			var mlc=document.getElementsByName("mlcNo")[0].value;
			if(mlc==null || mlc=="")
			{
				alert("Select The MLC No. \n To Get The MLC No Press The GO Button.");
				return false;
			}
			
			if(validateTextual())
			{
				return true;
			}	
	}
	
	function validateCrNo(){
		if(validateCRNoCHECK('<%=Config.CR_NO_FORMAT_SIZE%>')){ 
			showMlcNo()
		}
		else{
			return false;
		}	
	
	
	}
	</script>
	
	<his:TitleTag name="MLC Police Information Proforma" >
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
						<logic:notEqual name="mlcPoliceInfoFB" property="reportMode" value="GO">
						 <%String size=Integer.toString(Integer.parseInt(Config.CR_NO_FORMAT_SIZE)+2); %>
						 <%System.out.println("cr no size>>"+Config.CR_NO_FORMAT_SIZE); %>
							<html:text name="mlcPoliceInfoFB" property="patCrNo" tabindex="1" maxlength="<%=Config.CR_NO_FORMAT_SIZE %>" size="<%=size %>" value="<%=(String)session.getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT)%>" onkeypress="if(event.keyCode==13) return validateCrNo();  else return validateNumeric(event);">
							</html:text>
							
						</logic:notEqual>
						<logic:equal name="mlcPoliceInfoFB" property="reportMode"  value="GO">
							<bean:write name="mlcPoliceInfoFB" property="patCrNo"/>
						</logic:equal>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<logic:notEqual name="mlcPoliceInfoFB" property="reportMode" value="GO">
							<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateCRNoCHECK('<%=Config.CR_NO_FORMAT_SIZE%>')) showMlcNo()" onkeypress="if(event.keyCode==13){if(validateCRNoCHECK('<%=Config.CR_NO_FORMAT_SIZE%>')) showMlcNo()}">
						</logic:notEqual>
					</div>
				</td>
				<td width="25%" class="tdfont"></td>
			</tr>
<his:statusTransactionInProcess>
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="mlcNo" />
						 </font>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="left">
						<html:select name="mlcPoliceInfoFB" property="mlcNo" tabindex="1">
							<html:options collection ="<%=RegistrationConfig.ESSENTIALBO_OPTION_MLC_NO_BASED_ON_CR_NO %>" property = "value" labelProperty = "label"/>
						</html:select>
					</div>
				</td>
				<td width="25%" class="tdfont"></td>
				<td width="25%" class="tdfont"></td>
			</tr>
			
</his:statusTransactionInProcess>
			
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
        			<html:select name="mlcPoliceInfoFB" property="reportType" tabindex="1" styleClass="regcbo">
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
	
	
<html:hidden name="mlcPoliceInfoFB" property="reportMode"/>
<html:hidden name="mlcPoliceInfoFB" property="mode" value="MLCPOLICEINFOPROFORMAREPORT"/>
<html:hidden name="mlcPoliceInfoFB" property="jrxmlPath" value="<%=RegistrationConfig.REGISTRATION_JRXML_PATH %>"/>
<html:hidden name="mlcPoliceInfoFB" property="jrxmlName"/>
<html:hidden name="mlcPoliceInfoFB" property="patCrNo"/>
<html:hidden name="mlcPoliceInfoFB" property="mlcNo"/>
<input type="hidden" name="fromHour" > 
<input type="hidden" name="fromMin" > 
<input type="hidden" name="toHour" > 
<input type="hidden" name="toMin" > 
	