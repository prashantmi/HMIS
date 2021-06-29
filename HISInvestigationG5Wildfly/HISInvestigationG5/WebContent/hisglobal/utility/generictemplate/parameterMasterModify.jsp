<!-- 
/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */
-->

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@ page import = "hisglobal.hisconfig.Config"%>
<%@ page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<html>
<head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonUtility.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/parameterMasterAdd.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/>
</head>

<body>
	<html:form action="/master/ParameterMaster.cnt">
	
		<html:hidden name="ParameterMasterFB" property="hmode"/>		
	<his:TransactionContainer>	
		<his:TitleTag name="Parameter Master>>Modify">
		</his:TitleTag>
		<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%"  class="tdfonthead" >
								<div align="right">
									
									<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#ff0000">*</font>
										<b><bean:message key="paraName"/></b>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<html:text name="ParameterMasterFB" property="paraName" maxlength="50" onkeypress="return validateAlphabetsOnly(event,this)">
								</html:text>
							</td>
						</tr>
						<tr>	
							<td width="25%"  class="tdfonthead" align="right">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraBound"/></b>
								</font>
								</div>
							</td>
							<td width="25%"  class="tdfont" >
								<html:select name="ParameterMasterFB" property="paraBound">
									<html:option value="-1">Select Value</html:option>
									<html:option value="<%=GenericTemplateConfig.PARAMETER_BOUND_NON_PATIENT_CENTRIC %>">Non Patient Centric</html:option>
									<html:option value="<%=GenericTemplateConfig.PARAMETER_BOUND_PATIENT_CENTRIC %>">Patient Centric</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="25%"  class="tdfonthead" align="right">
								<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraType"/></b> 
								</font>
								</div>
							</td>
							<td width="25%"  class="tdfont" align="right">
								<html:select name="ParameterMasterFB" property="paraType">
									<html:option value="-1">Select Value</html:option>
								 	<html:options collection="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_TEMPLATE_GROUP%>" property="templateGroupID" labelProperty="templateGroupName"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead"  width="25%" align="right">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<font color="#ff0000">*</font>
										<b><bean:message key="isActive"/></b> 
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<html:select name="ParameterMasterFB" property="isActive">
										<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option> 
										<html:option value="<%=Config.IS_VALID_INACTIVE %>">InActive</html:option> 
								</html:select>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor:pointer" onclick="validate('MODIFYSAVE')" onkeypress="if(event.keyCode==13) validate('MODIFYSAVE')">
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor:pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
				<img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>' style="cursor:pointer" onclick="clearText()" onkeypress="if(event.keyCode==13) clearText()">
			</his:ButtonToolBarTag>
			<center><b><his:status/></b></center>
		</his:TransactionContainer>		
		<html:hidden name="ParameterMasterFB" property="paraId"/>
	</html:form>	
</body>
</html>