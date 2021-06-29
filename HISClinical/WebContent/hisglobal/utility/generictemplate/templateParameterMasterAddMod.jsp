<!-- 
/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@ page import = "hisglobal.hisconfig.Config"%>
<%@ page import = "java.util.*,hisglobal.presentation.*" %>
<%@ page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/utility/generictemplate/js/commonUtility.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/registration/js/registration.js" />

<his:javascript src="/hisglobal/utility/generictemplate/js/templateParameterMasterAddMod.js"/>


</head>

<body onload="CallThisOnLoad()">
	<html:form action="/master/TemplateParameterMaster.cnt">
		<%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
		<html:hidden name="TemplateParameterMasterFB" property="hmode"/>		
		<html:hidden name="TemplateParameterMasterFB" property="entryDate" value="<%=sysDate%>" />

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
									<html:text name="TemplateParameterMasterFB" onblur="tempDisplayName()" property="templateName" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)">
									</html:text>
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
									<!--<html:select name="TemplateParameterMasterFB" property="templateCategory">
										<html:option value="-1">Select Value</html:option>
										<html:options collection="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_TEMPLATE_CATEGORY%>" property="templateCategory" labelProperty="templateCategoryType"/> 
									</html:select>-->
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:write name="TemplateParameterMasterFB" property="templateCategoryName"/>
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
									<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
										<html:select name="TemplateParameterMasterFB"  onchange="stopNormalType()" property="templateType">
											<html:option value="-1">Select Value</html:option>
<%-- 											<html:option value="<%=GenericTemplateConfig.TEMPLATE_TYPE_NORMAL%>"><%=GenericTemplateConfig.TEMPLATE_TYPES[Integer.parseInt(GenericTemplateConfig.TEMPLATE_TYPE_NORMAL)]%></html:option> --%>
											<html:option value="<%=GenericTemplateConfig.TEMPLATE_TYPE_MATRIX%>"><%=GenericTemplateConfig.TEMPLATE_TYPES[Integer.parseInt(GenericTemplateConfig.TEMPLATE_TYPE_MATRIX)]%></html:option>
											<html:option value="<%=GenericTemplateConfig.TEMPLATE_TYPE_CONSENT%>"><%=GenericTemplateConfig.TEMPLATE_TYPES[Integer.parseInt(GenericTemplateConfig.TEMPLATE_TYPE_CONSENT)]%></html:option>
											<html:option value="<%=GenericTemplateConfig.TEMPLATE_TYPE_GUIDELINE%>"><%=GenericTemplateConfig.TEMPLATE_TYPES[Integer.parseInt(GenericTemplateConfig.TEMPLATE_TYPE_GUIDELINE)]%></html:option>
										</html:select>
									</logic:notEqual>
									<logic:equal name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
										<html:hidden name="TemplateParameterMasterFB" property="templateId"/>
										<html:hidden name="TemplateParameterMasterFB" property="tempSerialNo"/>
										<html:hidden name="TemplateParameterMasterFB" property="oldTemplateName"/>
										<html:hidden name="TemplateParameterMasterFB" property="oldTemplateCategory"/>
										<html:hidden name="TemplateParameterMasterFB" property="oldEffectiveTo"/>										
										<html:hidden name="TemplateParameterMasterFB" property="templateType"/>
										<html:hidden name="TemplateParameterMasterFB" property="oldIsDef"/>
										<bean:define id="tempType" name="TemplateParameterMasterFB" property="templateType" type="java.lang.String"></bean:define>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>&nbsp;<%=GenericTemplateConfig.TEMPLATE_TYPES[Integer.parseInt(tempType)]%></b>
										</font>
									</logic:equal>
								</div>
							</td>
							<td width="25%"  class="tdfonthead">
							<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="isDefault"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont"><input type="checkbox" name="chkIsDefault"  />
							</td>
						</tr>
						<tr style="display:none">
					   		<td width="25%" class="tdfonthead">
			           			<div align="right">	           
			              			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			              				<bean:message key="effectiveFrom"/>
			              			</font>
			            		</div>
							</td>
							
							<bean:define name="TemplateParameterMasterFB" property="effectiveFrom"  id="effFrom" type="java.lang.String"/>
		   					<%	if(effFrom==null||effFrom.equalsIgnoreCase(""))	{	effFrom =sysDate;	}	%>
	
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
		   								<his:date name='<%="effectiveFrom"%>' dateFormate="%d-%b-%Y" value="<%=effFrom %>" />
		   							</logic:notEqual>
									<logic:equal name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
										<html:hidden name="TemplateParameterMasterFB" property="effectiveFrom"/>
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b>&nbsp;<bean:write name="TemplateParameterMasterFB" property="effectiveFrom"/></b>
										</font>
									</logic:equal>
		   						</div>
		   					</td>
		   					<td width="25%" class="tdfonthead">
		   						<div align="right">
		   							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		   								<bean:message key="effectiveTo"/>
		   							</font>
		   						</div>
		   					</td>
		   					
		   					<bean:define name="TemplateParameterMasterFB" property="effectiveTo" id="effTo" type="java.lang.String" />		   					
		   					
		   					<td width="25%" class="tdfont">
		   						<div align="left">
									<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
		   								<his:date name='<%="effectiveTo"%>' dateFormate="%d-%b-%Y" />
		   							</logic:notEqual>
									<logic:equal name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
			   							<his:date name='<%="effectiveTo"%>' dateFormate="%d-%b-%Y" value="<%=effTo%>" />
									</logic:equal>
		   						</div>
		   					</td>
		   				</tr>
		   				<tr>
		   				
		   				<td width="25%" class="tdfonthead">
					 Age
					</td>
					<td width="25%" class="tdfont">
					
					<div align="left">
						<html:select styleId="" name="TemplateParameterMasterFB"  property="ageRangeId" styleClass="comboNormal">
								<html:option value="0">No Age Bound</html:option>
								<logic:notEmpty name="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_AGE_RANGE%>"> 
									<html:options collection="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_AGE_RANGE%>" property="value" labelProperty="label" /> 
								</logic:notEmpty>	 
							</html:select>
								
					</div>
					</td>
					
					
					<td width="25%" class="tdfonthead">
					 Gender
					</td>
					<td width="25%" class="tdfont">
					
					<div align="left">
						<html:select styleId="" name="TemplateParameterMasterFB"  property="genderCode" styleClass="comboNormal">
								<html:option value="0">No Gender Bound</html:option>
								<logic:notEmpty name="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_GENDER%>"> 
									<html:options collection="<%=GenericTemplateConfig.ESSENTIAL_LIST_ALL_GENDER%>" property="value" labelProperty="label" /> 
								</logic:notEmpty>	 
							</html:select>	
					</div>
					</td>
					
					
		   				</tr>
		   				
		   				
		   				<tr>
							<td width="25%"  class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="displayname"/>
									</font>
								</div>
							</td>
							<td width="25%"  class="tdfont">
								<div align="left">
									<html:text name="TemplateParameterMasterFB" property="strTemplateDispName" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)">
									</html:text>
								</div>
							</td>
							<td width="25%"  class="tdfonthead"></td>
							<td width="25%"  class="tdfont"></td>
						</tr>
		   				
						<logic:equal name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
						<tr>
							<td class="tdfont" colspan="4" width="100%">
								<html:hidden name="TemplateParameterMasterFB" property="modeTempModify" />
								<input type="checkbox" id="chkModeModify"/>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									 &nbsp; Want to modify Template
								</font>
							</td>
						</tr>
						
						
						
						</logic:equal>
						
					</table>
				</his:ContentTag>
			</his:statusNew>

			<his:ButtonToolBarTag>
				<his:statusNew>
					<logic:notEqual name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
						<img class="button" src='<his:path src="/hisglobal/images/btn-next.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateNext(),'NEXT')" onclick="submitFormOnValidate(validateNext(),'NEXT')">	
					</logic:notEqual>				
					<logic:equal name="TemplateParameterMasterFB" property="hmode" value="MODIFY">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateTempModify(),'MODIFYTEMPLATE')" onclick="submitFormOnValidate(validateTempModify(),'MODIFYTEMPLATE')">
					</logic:equal>					
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" onclick="submitForm21('ADD')" onkeypress="if(event.keyCode==13) submitForm21('ADD')">
				</his:statusNew>
					
				<his:statusDone>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
				</his:statusDone>
			</his:ButtonToolBarTag>
			<html:hidden name="TemplateParameterMasterFB" property="templateCategory"/>
			<html:hidden name="TemplateParameterMasterFB" property="templateCategoryName"/>
			<html:hidden name="TemplateParameterMasterFB" property="templateGroupID"/>
			<html:hidden name="TemplateParameterMasterFB" property="isDefault"  />
			<html:hidden name="TemplateParameterMasterFB" property="defChk"  />
			<html:hidden name="TemplateParameterMasterFB" property="lockChk"  />
			<html:hidden name="TemplateParameterMasterFB" property="ageRangeName"/>
			<html:hidden name="TemplateParameterMasterFB" property="genderCode"/>
			<html:hidden name="TemplateParameterMasterFB" property="strGenderName"/>
			<html:hidden name="TemplateParameterMasterFB" property="strTemplateDispName"/>
			
			
			
			<center><b><his:status/></b></center>
		</his:TransactionContainer>
	</html:form>	
</body>
</html>