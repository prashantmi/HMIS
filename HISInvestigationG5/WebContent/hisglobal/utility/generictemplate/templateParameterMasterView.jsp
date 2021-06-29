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
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/validationFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/date_validator.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/time_validator.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonUtility.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/templateValidations.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/templateParameterMasterAddMod.js"/>
</head>

<body>
	<html:form action="/master/TemplateParameterMaster.cnt">		
		<html:hidden name="TemplateParameterMasterFB" property="hmode"/>		
		<html:hidden name="TemplateParameterMasterFB" property="templateGroupID"/>

		<his:TransactionContainer>
			<his:TitleTag name="Template Parameter Master">
			</his:TitleTag>

			<his:statusNew>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
							<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
						</tr>
						<tr>
							<td width="25%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="tmplname"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="templateName"/></b>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="tmplcat"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<div align="left">
									<html:hidden name="TemplateParameterMasterFB" property="templateCategory"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">										
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="templateCategoryType"/></b>
									</font>
								</div>
							</td>
						</tr>
						<tr>
							<td width="25%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="tmpl"/> <bean:message key="type"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<div align="left">
									<bean:define id="tempType" name="TemplateParameterMasterFB" property="templateType" type="java.lang.String"></bean:define>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<%=GenericTemplateConfig.TEMPLATE_TYPES[Integer.parseInt(tempType)]%></b>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
						</tr>
						<tr>
					   		<td width="25%" class="tdfonthead">
			           			<div align="right">	           
			              			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			              				<bean:message key="effectiveFrom"/>
			              			</font>
			            		</div>
							</td>
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="effectiveFrom"/></b>
									</font>
		   						</div>
		   					</td>
		   					<td width="25%" class="tdfonthead">
		   						<div align="right">
		   							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		   								<bean:message key="effectiveTo"/>
		   							</font>
		   						</div>
		   					</td>
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="effectiveTo"/></b>
									</font>
		   						</div>
		   					</td>
		   				</tr>
						<tr>
							<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
							<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
						</tr>
					</table>
				</his:ContentTag>
				
				<his:SubTitleTag name="Template Designer">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" align="center" cellpadding="0" cellspacing="0" border="1">
						<tr>
							<td width="100%" align="center">
								<bean:define id="tempId" name="TemplateParameterMasterFB" property="templateId" type="java.lang.String"></bean:define>
								<his:GenericTemplateTag templateId="<%=tempId%>"></his:GenericTemplateTag>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</his:statusNew>

			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor:pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			</his:ButtonToolBarTag>
			<his:status/>
		</his:TransactionContainer>
	</html:form>	
</body>
</html>