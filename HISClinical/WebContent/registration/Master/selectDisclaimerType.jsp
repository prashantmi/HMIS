<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="registration.RegistrationConfig"%>
<%@page import="java.util.Date"%>
<%@page import="registration.RegistrationConfig;"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/util.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/registration.js" />
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
	function submitTile(mode)
{
	if(mode=="DISCLAIMER_TYPE")
	 {
	//alert( document.getElementsByName('disclaimerType')[0].value)
	 
	document.getElementsByName('transactionMode')[0].value=document.getElementsByName('disclaimerType')[0].value;
	document.forms[0].submit();
	 
	 }
	 else
	 {
	document.getElementsByName('transactionMode')[0].value=mode;
	document.forms[0].submit();
	 }
}
</script>
	<body>
		<html:form action="/master/disclaimerMaster">
		
		<his:TransactionContainer>
			
		<table cellpadding="0" cellspacing="1" width="80%">

			<tr>
				<td class="MasterXMLHeader">
				
					<b>Disclaimer Master&gt;&gt;</b>
				</td>
			</tr>
			<tr>
						
			</tr>
			</table>
			
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
   
					<tr>
						<td width="100%" height="25" class="ShadedSubTitleTagImage">
						<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="disclaimerType"/>
						</font>
						</div>
						</td>
						<td>
						<div align="left">
                         <html:select name="AddDisclaimerFB" property="disclaimerType" onchange="submitTile('DISCLAIMER_TYPE') " >
							<html:option value="<%=RegistrationConfig.DISCLAIMER_MAPPING_DEFAULT %>" >Default Disclaimer</html:option>
							<html:option value="<%=RegistrationConfig.DISCLAIMER_MAPPING_DEPARTMENT_WISE %>">Department Wise Disclaimer</html:option>
							<html:option value="<%=RegistrationConfig.DISCLAIMER_MAPPING_UNIT_WISE %>">Department Unit Wise Disclaimer</html:option>
						</html:select>
						</div>
						</td>
					</tr>
			 <html:hidden name="AddDisclaimerFB" property="transactionMode"/>
				</table>
				
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="25%" height="25" class="MasterXMLDataLabel">
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="defaultDisclaimerType"/>
						</font>
						</div>
					</td>
						
					<td width="25%" height="25" class="MasterXMLDataLabel">
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="disclaimer1"/>
						</font>
						</div>
					</td>
						
					<td width="25%" height="25" class="MasterXMLDataLabel" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="disclaimer2"/>
						</font>
						</div>
					</td>
						
						
					<td width="25%" height="25" class="MasterXMLDataLabel" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="disclaimer3"/>
						</font>
						</div>
					</td>
			</tr>
			<logic:present name="<%=RegistrationConfig.ESSENTIAL_DISCLAIMER_MASTER_VO %>" >
			<tr>
					
				<td width="25%" class="MasterXMLDataValue" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="normal" />
						
						</font>
					</div>
				</td>
						
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="generalDisclaimerDesc1"/>
						</font>
						</div>
				</td>
						
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="generalDisclaimerDesc2" />
						</font>
						</div>
				</td>
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="generalDisclaimerDesc3" />
						</font>
						</div>
				</td>
		  </tr>
		  <tr>
					
				<td width="25%" class="MasterXMLDataValue" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="special" />
						
						</font>
					</div>
				</td>
						
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="specialDisclaimerDesc1" />
						</font>
						</div>
				</td>
						
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="specialDisclaimerDesc2" />
						</font>
						</div>
				</td>
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="specialDisclaimerDesc3" />
						</font>
						</div>
				</td>
			</tr>
			<tr>
				<td width="25%" class="MasterXMLDataValue" >
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:message key="casuality" />
						
						</font>
					</div>
				</td>
						
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="casualityDisclaimerDesc1"/>
						</font>
						</div>
				</td>
						
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="casualityDisclaimerDesc2" />
						</font>
						</div>
				</td>
				<td width="25%" class="MasterXMLDataValue" >
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="AddDisclaimerFB" property="casualityDisclaimerDesc3" />
						</font>
						</div>
				</td>
			</tr>	
		
			</logic:present>	
			</table>
			
				<his:ButtonToolBarTag>
				<logic:notPresent name="<%=RegistrationConfig.ESSENTIAL_DISCLAIMER_MASTER_VO %>">
				
				    <div align="center">		 
			           <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13) submitTile('ADD')" onclick ="submitTile('ADD');" tabindex="1"/>
	                   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitTile('CANCEL_LIST');" tabindex="1" onclick ="submitTile('CANCEL_LIST');">
	         
                    </div>
				</logic:notPresent>
				 <logic:present name="<%=RegistrationConfig.ESSENTIAL_DISCLAIMER_MASTER_VO %>">
				 <div align="center" >
				      	   
			              <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13) submitTile('EDIT')" onclick ="submitTile('EDIT');" tabindex="1"/>
			              <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitTile('CANCEL_LIST');" tabindex="1" onclick ="submitTile('CANCEL_LIST');">
	                      <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) submitTile('VIEW');" tabindex="1" onclick ="submitTile('VIEW');">
	                      <img class="button" src='<his:path src="/hisglobal/images/btn-rpt.png"/>' tabindex="1"  style=cursor:pointer onclick ="submitTile('REPORT')" onkeypress="if(event.keyCode==13) submitTile('REPORT');">
                 </div>
				</logic:present>
		      </his:ButtonToolBarTag>
		</his:TransactionContainer>
	</html:form>
	
	</body>

</html>

						