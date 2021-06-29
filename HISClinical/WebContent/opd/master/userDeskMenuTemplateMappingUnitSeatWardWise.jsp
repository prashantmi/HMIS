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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>

	<body>
		<html:form action="/master/UserDeskMenuTempMapping.cnt">
			<his:TransactionContainer>
				<his:TitleTag name="User Desk Menu Template Mapping Master">
				</his:TitleTag>
		
			<%boolean disabledFlag=false; %>
			
			<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="MODIFYDESKID">	
				<%disabledFlag=true; %>
			</logic:equal>	
			<logic:equal name="UserDeskMenuTempMapFB" property="hmode" value="ADDBYDESKID">
				<%disabledFlag=true; %>
			</logic:equal>	
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<B>
										<bean:message key="aditionMode"/>
									</B>
								</font>
							</div>
						</td>
						<td width="75%" colspan="3" class="tdfont">
							<div align="left">
								
								<bean:message key="deskWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_DESK_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								<bean:message key="unitWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								<bean:message key="unitSeatWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_SEAT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								<bean:message key="wardWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_UNIT_WARD_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								<bean:message key="unitWardSeatWise"/>
								<html:radio name="UserDeskMenuTempMapFB" property="additionMode" value="<%=OpdConfig.USER_DESK_ADDITION_MODE_WARD_SEAT_WISE %>" onclick="submitPage('CHANGEMODE')" disabled="<%=disabledFlag %>"></html:radio>
								
							</div>
						</td>
					</tr>
				</table>
			</his:ContentTag>	
				
			<his:ContentTag>
				
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
								<html:select name="UserDeskMenuTempMapFB" property="deskType"  disabled="true">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_TYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
								
							</div>
							<html:hidden name="UserDeskMenuTempMapFB" property="deskType"/>
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
								<html:select name="UserDeskMenuTempMapFB" property="deskId"  disabled="true">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BASED_ON_DESKTYPE %>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
								
							</div>
						</td>	
					</tr>
				</table>
			
				<logic:empty name="<%=OpdConfig.LIST_MAPPED_UNITS_FOR_UNITSEATWARD%>" >		
				<his:SubTitleTag name="Wards">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="unitCode" onkeypress="if(event.keyCOde==13)showWards();" onchange="showWards();" styleClass="registrationCmb" >
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS %>">
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					</logic:equal>
					<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="wards"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center"  >
								<html:select name="UserDeskMenuTempMapFB" property="mainWardsList" multiple="true" size="6" >
								
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_WARDS_NOT_ASSIGNED_SEAT%>" property="value" labelProperty="label" />
										</logic:present>
								</html:select>
							</div>
							
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("1");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("1");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("1");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="selectedWards" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%" class="tdfonthead"></td>
					</tr>
					</logic:equal>
					
					<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
						<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<html:hidden name="UserDeskMenuTempMapFB" property="unitCode"/>
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="selectedWards" multiple="true" size="6">
									<logic:present name="UserDeskMenuTempMapFB" property="selectedWardsName" >
									<html:optionsCollection name="UserDeskMenuTempMapFB" property="selectedWardsName" label="label" value="value"/>
									</logic:present>							
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					
					</logic:equal>
					<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
						<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<html:hidden name="UserDeskMenuTempMapFB" property="unitCode"/>
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="selectedWards" multiple="true" size="6">
									<logic:present name="UserDeskMenuTempMapFB" property="selectedWardsName" >
									<html:optionsCollection name="UserDeskMenuTempMapFB" property="selectedWardsName" label="label" value="value"/>
									</logic:present>							
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					
					</logic:equal>
				</table>
			</his:ContentTag>
			</logic:empty>
		</his:ContentTag>
	<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
	<his:SubTitleTag name="Seats">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="group"/>
								</font>	
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="group" onchange="showSeats()">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.ESSENTIALBO_ALL_GROUP_LIST%>" >
									<html:options collection="<%=OpdConfig.ESSENTIALBO_ALL_GROUP_LIST %>" property="value" labelProperty="label"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
				
					
					<tr>
						<td width="15%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="seat"/>
								</font>	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="seatsList" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_DEPT_SEATS%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_DEPT_SEATS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle("2");'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll("2");'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle("2");'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll("2");'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="selectedSeats" multiple="true" size="6">
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="15%"  class="tdfonthead"></td>
						<td width="35%"  class="tdfonthead"></td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					
				</table>
			</his:ContentTag>
	</logic:equal>
	
	
	
	
	
	<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
	<his:SubTitleTag name="Seats">
			</his:SubTitleTag>
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="selectedSeats" multiple="true" size="6">
									<logic:present name="UserDeskMenuTempMapFB" property="selectedSeatsName" >
									<html:optionsCollection name="UserDeskMenuTempMapFB" property="selectedSeatsName" label="label" value="value"/>
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="40%"  class="tdfonthead"></td>
						<td width="20%"  class="tdfont"></td>
						<td width="40%" class="tdfonthead"></td>
					</tr>
					
				</table>
			</his:ContentTag>
	</logic:equal>
	
	
	<div id="viewDisplay" >		
	<his:statusRecordFound>
		<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
			<his:SubTitleTag name="Select Menus">
			</his:SubTitleTag>
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
							<td width="10%" class="tdfonthead"></td>
						</tr>
						<tr>
							<td width="30%" class="tdfont">		
								<div align="center">
									<html:select name="UserDeskMenuTempMapFB"  property="deskMenuId" onchange="getTemplate()">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_USER_DESKMENU_LIST%>" >
										<html:options collection="<%= OpdConfig.EssentialBO_LIST_USER_DESKMENU_LIST%>" property="value" labelProperty="label" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="30%" class="tdfont">		
								<div align="center">
									<html:select name="UserDeskMenuTempMapFB"  property="templateId">
										<html:option value="-1">Select Value</html:option>
										<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_TEMPLATES%>" >
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_TEMPLATES %>" property="templateId" labelProperty="templateName" />
										</logic:present>
									</html:select>
								</div>
							</td>
							<td width="30%" class="tdfont">		
								<div align="center">
									<html:select name="UserDeskMenuTempMapFB"  property="isDefault">
										<html:option value="0" >False</html:option>
										<html:option value="1" >True</html:option>
									</html:select>
								</div>
							</td>
							<td width="10%" class="tdfont">		
								<div align="center">
									<img class="button" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="if(validateAdd()) submitPage('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitPage('ADDROW');">
								</div>
							</td>	
						</tr>
					</table>
				</his:ContentTag>	
				<%if(session.getAttribute(OpdConfig.ARR_USER_DESK_MENU_TEMP_VO)!=null){ %>
				<his:ContentTag>
					<table  width="100%" border="0" cellspacing="1" cellpadding="0" id="tableId">
						<logic:iterate id="arrUserDeskMenuTempVo" name="<%=OpdConfig.ARR_USER_DESK_MENU_TEMP_VO %>" type="hisglobal.vo.UserDeskMenuTemplateMasterVO">
							<tr>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=arrUserDeskMenuTempVo.getDeskMenuName() %>
									</div>
								</td>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=arrUserDeskMenuTempVo.getTemplateName() %>
									</div>
								</td>
								<td class="tdfont" width="30%" >
									<div align="center">
										<%=arrUserDeskMenuTempVo.getIsDefaultName() %>
									</div>
								</td>
								
								<td class="tdfont" width="10%" >
									<div align="center">
										<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=arrUserDeskMenuTempVo.getTemplateName() %>','<%=arrUserDeskMenuTempVo.getTemplateId() %>') ;" onclick=" deleteRow('<%=arrUserDeskMenuTempVo.getTemplateName() %>','<%=arrUserDeskMenuTempVo.getTemplateId() %>')">
										<html:hidden name="UserDeskMenuTempMapFB" property="templateName"/>
										<html:hidden name="UserDeskMenuTempMapFB" property="hiddenTemplateId"/>
									</div>
								</td>
							</tr>
						</logic:iterate>
					</table>	
				</his:ContentTag>
<%} %>

			</logic:equal>
		</his:statusRecordFound>
			</div>
			
	
			<his:ButtonToolBarTag>
			<his:statusTransactionInProcess>	
				<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP2 %>">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(validateAdd()) submitPage('SAVEBYUNITWARDSEATWISE')" onkeypress="if(event.keyCode==13)if(validateAdd()) submitPage('SAVEBYUNITWARDSEATWISE')">
				</logic:equal>	
				
			</his:statusTransactionInProcess>
			
			<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP0 %>">
			<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick ="goSelected()" onkeypress="if(event.keyCode==13) goSelected()">
			</logic:equal>
				<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
			<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick ="getTemplates()" onkeypress="if(event.keyCode==13) getTemplates()">
			</logic:equal>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" submitPage('CANCEL')" onkeypress="if(event.keyCode==13)submitPage('CANCEL')">			
			
			</his:ButtonToolBarTag>
						
			</his:TransactionContainer>
		
		<html:hidden name="UserDeskMenuTempMapFB" property="hmode"/>
		<html:hidden name="UserDeskMenuTempMapFB" property="tempMode"/>
		<html:hidden name="UserDeskMenuTempMapFB" property="additionMode"/>
		<html:hidden name="UserDeskMenuTempMapFB" property="deskId"/>
	
		</html:form>
	</body>
<center><b><his:status/></b></center>
</html>