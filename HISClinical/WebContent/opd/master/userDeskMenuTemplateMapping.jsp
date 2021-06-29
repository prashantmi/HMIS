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
<html:form action="/master/UserDeskMenuTempMapping.cnt">
	
<his:TransactionContainer>
	<his:TitleTag key="userDskMnuTempMap">
	</his:TitleTag>
	
		<%	boolean disabledFlag=false; %>		
		<logic:equal name="UserDeskMenuTempMapFB" property="isMappingStart" value="<%=OpdConfig.YES%>">	
			<%disabledFlag=true; %>
		</logic:equal>	
		
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
							&nbsp;<bean:message key="deskWise"/>
							<html:radio name="UserDeskMenuTempMapFB" property="additionMode" tabindex="1" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
							<bean:message key="unitWise"/>
							<html:radio name="UserDeskMenuTempMapFB" property="additionMode" tabindex="1" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
							<bean:message key="unitSeatWise"/>
							<html:radio name="UserDeskMenuTempMapFB" property="additionMode" tabindex="1" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
							<bean:message key="wardWise"/>
							<html:radio name="UserDeskMenuTempMapFB" property="additionMode" tabindex="1" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
							<bean:message key="unitWardSeatWise"/>
							<html:radio name="UserDeskMenuTempMapFB" property="additionMode" tabindex="1" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="additionMode"/>	<%	}	%>
						</div>
					</td>
				</tr>
			</table>
		</his:ContentTag>
		
	<his:statusNew>	
		<his:ContentTag>		
			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deskType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskType" tabindex="1" onchange="submitForm21('GETDESK')" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%>
						</td>	
					</tr>
					<tr>
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
						</td>	
					</tr>
				</table>
			</logic:equal>
			
			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deskType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskType" tabindex="1" onchange="submitForm21('GETDESK')" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>							
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%>
						</td>	
					</tr>
					<tr>
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
								<html:select name="UserDeskMenuTempMapFB" property="deskId" tabindex="1" onchange="getUnitInUnitWise()" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskId"/>	<%	}	%>
						</td>	
					</tr>
					<logic:notEmpty name="<%=OpdConfig.LIST_MAPPED_UNITS%>" >			
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="unitCode" tabindex="1" onchange="showTemplateByDesk(2)" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.LIST_MAPPED_UNITS%>" >
										<html:options collection="<%=OpdConfig.LIST_MAPPED_UNITS %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="unitCode"/>	<%	}	%>
						</td>
					</tr>
					</logic:notEmpty>
				</table>
			</logic:equal>
				
			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deskType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskType" tabindex="1" onchange="submitForm21('GETDESK')" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%>
						</td>	
					</tr>
					<tr>
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
								<html:select name="UserDeskMenuTempMapFB" property="deskId" tabindex="1" onchange="getUnitInUnitSeatWise()" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskId"/>	<%	}	%>
						</td>	
					</tr>
					<logic:notEmpty name="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEAT%>" >			
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="unitCode" tabindex="1" onchange="getSeatByUnit()" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEAT%>" >
										<html:options collection="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEAT %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="unitCode"/>	<%	}	%>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="user"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="userSeatId" tabindex="1" onchange="showTemplateByDesk(3)" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_SEAT_BASED_ON_UNIT%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_SEAT_BASED_ON_UNIT %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="userSeatId"/>	<%	}	%>
						</td>
					</tr>
					</logic:notEmpty>
				</table>
			</logic:equal>	
			
			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deskType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskType" tabindex="1" onchange="submitForm21('GETDESK')" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%>
						</td>	
					</tr>
					<tr>
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
								<html:select name="UserDeskMenuTempMapFB" property="deskId" tabindex="1" onchange="getUnitInUnitWardWise()" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskId"/>	<%	}	%>
						</td>	
					</tr>
					<logic:notEmpty name="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_WARD%>" >			
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="unitCode" tabindex="1" onchange="getWardByUnit()" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_WARD%>" >
										<html:options collection="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_WARD %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="unitCode"/>	<%	}	%>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="ward"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="wardCode" tabindex="1" onchange="showTemplateByDesk(4)" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="wardCode"/>	<%	}	%>
						</td>
					</tr>
					</logic:notEmpty>
				</table>
			</logic:equal>
			
			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>">
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="deskType"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="deskType" tabindex="1" onchange="submitForm21('GETDESK')" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>	<%	}	%>
						</td>	
					</tr>
					<tr>
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
								<html:select name="UserDeskMenuTempMapFB" property="deskId" tabindex="1" onchange="getUnitInUnitWardSeatWise()" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>								
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="deskId"/>	<%	}	%>
						</td>	
					</tr>
					<logic:notEmpty name="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEATWARD%>" >			
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="unit"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="unitCode" tabindex="1" onchange="getWardByUnitForUnitWardSeat()" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEATWARD%>" >
										<html:options collection="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEATWARD %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="unitCode"/>	<%	}	%>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="ward"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="wardCode" tabindex="1" onchange="getSeatByUnitNWard()" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE_FOR_UWS%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_WARD_UNIT_WISE_FOR_UWS %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="wardCode"/>	<%	}	%>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="user"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" >
							<div align="left">
								<html:select name="UserDeskMenuTempMapFB" property="userSeatId" tabindex="1" onchange="showTemplateByDesk(5)" disabled="<%=disabledFlag %>">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_SEAT_UNIT_N_WARD_WISE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<%	if(disabledFlag){ %> <html:hidden name="UserDeskMenuTempMapFB" property="userSeatId"/>	<%	}	%>
						</td>
					</tr>
					</logic:notEmpty>
				</table>
			</logic:equal>
		</his:ContentTag>
	</his:statusNew>

	
	<his:statusTransactionInProcess>
	<div id="viewDisplay">		
		<logic:notEqual name="UserDeskMenuTempMapFB" property="length" value="0">
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="menuName"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="tmplname"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="30%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="isdefault"/>
									</b>	
								</font>
							</div>
						</td>
					</tr>
					
					<logic:iterate id="menuTemplateByDesk" name="<%=OpdConfig.MENU_TEMPLATE_LIST_BY_DESKID %>" type="hisglobal.vo.UserDeskMenuTemplateMasterVO">
						<tr>
							<td class="tdfont" width="30%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=menuTemplateByDesk.getDeskMenuName() %>
									</font>
								</div>
							</td>
							<td class="tdfont" width="30%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=menuTemplateByDesk.getTemplateName() %>
									</font>
								</div>
							</td>
							<td class="tdfont" width="30%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=menuTemplateByDesk.getIsDefaultName() %>
									</font>
								</div>
							</td>
						</tr>	
					</logic:iterate>
				</table>
			</his:ContentTag>
		</logic:notEqual>
	</div>
	
	<logic:equal name="UserDeskMenuTempMapFB" property="isMappingStart" value="<%=OpdConfig.YES%>">
		<his:ContentTag>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="menuName"/></b>	
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="tmplname"/></b>
							</font>
						</div>
					</td>
					<td width="30%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="isdefault"/></b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead"></td>
				</tr>
				<tr>
					<td width="30%" class="tdfont">		
						<div align="center">
							<html:select name="UserDeskMenuTempMapFB"  property="deskMenuId" tabindex="1" onchange="getTemplate()">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.ARR_DESK_MENU_N_CATEGORY_VO%>" >
									<html:options collection="<%= OpdConfig.ARR_DESK_MENU_N_CATEGORY_VO%>" property="deskMenuId" labelProperty="deskMenuName" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="30%" class="tdfont">		
						<div align="center">
							<html:select name="UserDeskMenuTempMapFB"  property="templateId" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_TEMPLATES%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_TEMPLATES %>" property="templateId" labelProperty="templateName" />
								</logic:present>
							</html:select>
						</div>
					</td>
					<td width="30%" class="tdfont">		
						<div align="center">
							<html:select name="UserDeskMenuTempMapFB"  property="isDefault" tabindex="1">
								<html:option value="<%=OpdConfig.NO %>" >False</html:option>
								<html:option value="<%=OpdConfig.YES %>" >True</html:option>
							</html:select>
						</div>
					</td>
					<td width="10%" class="tdfont">		
						<div align="center">
							<img class="button" src='<his:path src="/../HIS/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="if(validateAdd()) submitPage('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitPage('ADDROW');">
						</div>
					</td>	
				</tr>
			</table>

			<logic:present name="<%=OpdConfig.ARR_USER_DESK_MENU_TEMP_VO%>">
				<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="tableId">
					<logic:iterate id="userDeskMenuTempVO" name="<%=OpdConfig.ARR_USER_DESK_MENU_TEMP_VO %>" type="hisglobal.vo.UserDeskMenuTemplateMasterVO">
						<tr>
							<td class="tdfont" width="30%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=userDeskMenuTempVO.getDeskMenuName() %>
									</font>
								</div>
							</td>
							<td class="tdfont" width="30%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=userDeskMenuTempVO.getTemplateName() %>
									</font>
								</div>
							</td>
							<td class="tdfont" width="30%" >
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=userDeskMenuTempVO.getIsDefaultName() %>
									</font>
								</div>
							</td>							
							<td class="tdfont" width="10%" >
								<div align="center">
									<img class="button" style="cursor:pointer" src='<his:path src="/../HIS/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) delRow('<%=userDeskMenuTempVO.getTemplateId() %>') ;" onclick="delRow('<%=userDeskMenuTempVO.getTemplateId() %>')">
									<html:hidden name="UserDeskMenuTempMapFB" property="hiddenTemplateId"/>
								</div>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</logic:present>
		</his:ContentTag>
	</logic:equal>
</his:statusTransactionInProcess>	

<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>	

		<logic:equal name="UserDeskMenuTempMapFB" property="isDeskSelected" value="<%=OpdConfig.YES%>">

			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>">
				<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="NEW">
					<logic:equal name="UserDeskMenuTempMapFB" property="length" value="0">
						<img id="addButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateAddOne()) submitPage('ADDBYDESKID')" onkeypress="if(validateAddOne()) submitPage('ADDBYDESKID')">
					</logic:equal>
					<logic:notEqual name="UserDeskMenuTempMapFB" property="length" value="0">
						<img id="modifyButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('MODIFYDESKID')" onkeypress="if(event.keyCode==13) submitPage('MODIFYDESKID')">
						<img id="deleteButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-del.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(askToDelete()) submitPage('DELBYDESKID')" onkeypress="if(event.keyCode==13) if(askToDelete()) submitPage('DELBYDESKID')">
					</logic:notEqual>
				</logic:equal>
			</logic:equal>

			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>">
				<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="NEW">
					<img id="addButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateAddOne()) submitPage('ADDNONMAPPEDUNITS')" onkeypress="if(validateAddOne()) submitPage('ADDNONMAPPEDUNITS')">
					<logic:notEqual name="UserDeskMenuTempMapFB" property="length" value="0">
						<img id="modifyButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('MODIFYDESKID')" onkeypress="if(event.keyCode==13) submitPage('MODIFYDESKID')">
						<img id="deleteButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-del.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(askToDelete()) submitPage('DELBYDESKID')" onkeypress="if(event.keyCode==13) if(askToDelete()) submitPage('DELBYDESKID')">
					</logic:notEqual>
				</logic:equal>
			</logic:equal>

			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>">
				<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="NEW">
					<img id="addButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateAddOne()) submitPage('ADDNONMAPPEDUNITSFORUNITSEAT')" onkeypress="if(validateAddOne()) submitPage('ADDNONMAPPEDUNITSFORUNITSEAT')">
					<logic:notEqual name="UserDeskMenuTempMapFB" property="length" value="0">
						<img id="modifyButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('MODIFYDESKID')" onkeypress="if(event.keyCode==13) submitPage('MODIFYDESKID')">
						<img id="deleteButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-del.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(askToDelete()) submitPage('DELBYDESKID')" onkeypress="if(event.keyCode==13) if(askToDelete()) submitPage('DELBYDESKID')">
					</logic:notEqual>
				</logic:equal>
			</logic:equal>

			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>">
				<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="NEW">
					<img id="addButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateAddOne()) submitPage('ADDNONMAPPEDUNITSFORWARD')" onkeypress="if(validateAddOne()) submitPage('ADDNONMAPPEDUNITSFORWARD')">
					<logic:notEqual name="UserDeskMenuTempMapFB" property="length" value="0">
						<img id="modifyButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('MODIFYDESKID')" onkeypress="if(event.keyCode==13) submitPage('MODIFYDESKID')">
						<img id="deleteButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-del.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(askToDelete()) submitPage('DELBYDESKID')" onkeypress="if(event.keyCode==13) if(askToDelete()) submitPage('DELBYDESKID')">
					</logic:notEqual>
				</logic:equal>
			</logic:equal>

			<logic:equal name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>">
				<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="NEW">
					<img id="addButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-add.png"/>'  style="cursor:pointer" tabindex="1" onclick =" if(validateAddOne()) submitPage('ADDNONMAPPEDUNITSFORUNITSEATWARD')" onkeypress="if(validateAddOne()) submitPage('ADDNONMAPPEDUNITSFORUNITSEATWARD')">
					<logic:notEqual name="UserDeskMenuTempMapFB" property="length" value="0">
						<img id="modifyButton" class="button" src='<his:path src="/HIS/hisglobal/uttons/btn-mo.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('MODIFYDESKID')" onkeypress="if(event.keyCode==13) submitPage('MODIFYDESKID')">
						<img id="deleteButton" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-del.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(askToDelete()) submitPage('DELBYDESKID')" onkeypress="if(event.keyCode==13) if(askToDelete()) submitPage('DELBYDESKID')">
					</logic:notEqual>
				</logic:equal>
			</logic:equal>
			<logic:equal name="UserDeskMenuTempMapFB" property="isMappingStart" value="<%=OpdConfig.YES%>">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(validateSave()) submitPage('SAVEBYDESKID')" onkeypress="if(event.keyCode==13)if(validateSave()) submitPage('SAVEBYDESKID')">
			</logic:equal>				
		</logic:equal>
	</his:statusTransactionInProcess>
	<his:statusNew>
		<logic:equal name="UserDeskMenuTempMapFB" property="isMappingStart" value="<%=OpdConfig.NO%>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13)cancelFunc()">
		</logic:equal>				
		<logic:equal name="UserDeskMenuTempMapFB" property="isMappingStart" value="<%=OpdConfig.YES%>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('CHANGEMODE')" onkeypress="if(event.keyCode==13)submitPage('CHANGEMODE')">
		</logic:equal>		
	</his:statusNew>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">
	</his:statusUnsuccessfull>			
</his:ButtonToolBarTag>

</his:TransactionContainer>
	
<center><b><his:status/></b></center>

<html:hidden name="UserDeskMenuTempMapFB" property="hmode"/>
<html:hidden name="UserDeskMenuTempMapFB" property="isDeskSelected"/>
<html:hidden name="UserDeskMenuTempMapFB" property="isGoPressed"/>
<html:hidden name="UserDeskMenuTempMapFB" property="isMappingStart"/>
<html:hidden name="UserDeskMenuTempMapFB" property="isModificationStart"/>
<html:hidden name="UserDeskMenuTempMapFB" property="length"/>
<html:hidden name="UserDeskMenuTempMapFB" property="addedLength"/>

</html:form>

</body>

</html>