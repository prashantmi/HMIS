<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/opd/js/desk_temp_mapping.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<body>
<html:form action="/master/ClinicalSectionCompositionMapping.cnt">
	
<his:TransactionContainer>
	<his:TitleTag key="clinicalSectionInterface">
	</his:TitleTag>
	
		<%-- <%	boolean disabledFlag=false; %>		
		<logic:equal name="UserDeskMenuTempMapFB" property="isMappingStart" value="<%=OpdConfig.YES%>">	
			<%disabledFlag=true; %>
		</logic:equal>	
		 --%>
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="15%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<B>
									<bean:message key="aditionMode"/>&nbsp;
								</B>
							</font>
						</div>
					</td>
					<td width="85%" colspan="3" class="tdfont" valign="middle">
						<div align="left">							
							&nbsp;
							<label>Hospital Default</label>
							<html:radio name="ClinicalSectionCompMapFB" property="hospitalCode" tabindex="1" value=""></html:radio>
							&nbsp;
							<label>Department Wise</label>
							<html:radio name="ClinicalSectionCompMapFB" property="hospitalCode" tabindex="1" value="" ></html:radio>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
		
		
		<his:ContentTag>		
		<%-- <logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>"> --%>
		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="compType"/>
									</b>	
								</font>
							</div>
						</td>
					 	<td width="50%" class="tdfont" >
							<div align="left">
							<html:select name="ClinicalSectionCompMapFB" property="compositionType" tabindex="1" onchange="submitForm21('GETCOMPOSITION')">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.COMPOSITION_TYPE%>" >
									<html:options collection="<%=OpdConfig.COMPOSITION_TYPE %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
							</div>
							<%-- <%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%> --%>
						</td>	
					</tr>
					
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="clinicalSection"/>
									</b>	
								</font>
							</div>
						</td>
					 	<td width="50%" class="tdfont" >
							<div align="left">
							<html:select name="ClinicalSectionCompMapFB" property="sectionCode" tabindex="1" onchange="submitForm21('GETCOMPOSITION')">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.COMPOSITION_TYPE%>" >
									<html:options collection="<%=OpdConfig.COMPOSITION_TYPE %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
							</div>
							<%-- <%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%> --%>
						</td>	
					</tr>
					
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="subSection"/>
									</b>	
								</font>
							</div>
						</td>
					 	<td width="50%" class="tdfont" >
							<div align="left">
							<html:select name="ClinicalSectionCompMapFB" property="sectionCode" tabindex="1" onchange="submitForm21('GETCOMPOSITION')">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.COMPOSITION_TYPE%>" >
									<html:options collection="<%=OpdConfig.COMPOSITION_TYPE %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
							</div>
							<%-- <%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%> --%>
						</td>	
					</tr>
					
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="interfaceType"/>
									</b>	
								</font>
							</div>
						</td>
					 	<td width="50%" class="tdfont" >
							<div align="left">
							<html:select name="ClinicalSectionCompMapFB" property="interfaceType" tabindex="1" onchange="submitForm21('GETCOMPOSITION')">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.COMPOSITION_TYPE%>" >
									<html:options collection="<%=OpdConfig.COMPOSITION_TYPE %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
							</div>
							<%-- <%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%> --%>
						</td>	
					</tr>
					
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="sectionInterface"/>
									</b>	
								</font>
							</div>
						</td>
					 	<td width="50%" class="tdfont" >
							<div align="left">
							<html:select name="ClinicalSectionCompMapFB" property="sectionInterface" tabindex="1" onchange="submitForm21('GETCOMPOSITION')">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.COMPOSITION_TYPE%>" >
									<html:options collection="<%=OpdConfig.COMPOSITION_TYPE %>" property="value" labelProperty="label" />
								</logic:present>
							</html:select>
							</div>
							<%-- <%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%> --%>
						</td>	
					</tr>
					
					
					<%-- <tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deskName"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskId" tabindex="1" onchange="showTemplateByDesk(1)" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskId"/>	<%	}	%>
						</td>	 --%>
					</tr>
				</table>
			<%-- </logic:equal> --%>
			</his:ContentTag>
		
		
		
		
		
		
		
		
</his:TransactionContainer>
	
<center><b><his:status/></b></center>

<html:hidden name="ClinicalSectionCompMapFB" property="hmode"/>

</html:form>

</body>

</html>