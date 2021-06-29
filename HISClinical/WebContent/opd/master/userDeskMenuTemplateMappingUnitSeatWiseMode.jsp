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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<body>
<html:form action="/master/UserDeskMenuTempMapping.cnt">
	
<his:TransactionContainer>
	<his:TitleTag key="userDskMnuTempMap">
	</his:TitleTag>
	
		<%	boolean disabledFlag=true; %>		
		
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
			</table>
		</his:ContentTag>
		
	<his:statusNew>	
		<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP0%>">
			<his:SubTitleTag name="Select Units">
			</his:SubTitleTag>
			
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deptName"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="deptId" tabindex="1" onchange="deptSelected(this);">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DEPT%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center" style="display: none;" >
								<html:select name="UserDeskMenuTempMapFB" property="mainUnitsList" multiple="true" size="6" disabled="true">
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_UNITS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="unitsList" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif" class="link" onClick='moveSingleInLists(document.getElementsByName("unitsList")[0],document.getElementsByName("selectedUnits")[0]);'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveAllInLists(document.getElementsByName("unitsList")[0],document.getElementsByName("selectedUnits")[0]);'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif" class="link" onClick='moveSingleInLists(document.getElementsByName("selectedUnits")[0],document.getElementsByName("unitsList")[0]);'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif" class="link" onClick='moveAllInLists(document.getElementsByName("selectedUnits")[0],document.getElementsByName("unitsList")[0]);'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="selectedUnits" multiple="true" size="6">
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
				</table>
			</his:ContentTag>
		</logic:equal>

		<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP1%>">
			<his:SubTitleTag name="Selected Units">
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
								<html:select name="UserDeskMenuTempMapFB" property="selectedUnits" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS%>" >
										<html:options collection="<%=OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS%>" property="value" labelProperty="label" />
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

			<his:SubTitleTag name="Select Users">
			</his:SubTitleTag>
			
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="group"/>
								</font>
							</div>
						</td>
						<td width="35%" class="tdfonthead">
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="group" tabindex="1" onchange="groupSelected(this)">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.ESSENTIALBO_ALL_GROUP_LIST %>">
										<html:options collection="<%=OpdConfig.ESSENTIALBO_ALL_GROUP_LIST%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont"></td>
						<td width="35%"  class="tdfonthead"></td>
					</tr>
					<tr>
						<td width="15%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="user"/>
								</font>
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center" style="display: none;" >
								<html:select name="UserDeskMenuTempMapFB" property="mainSeatsList" multiple="true" size="6" disabled="true">
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_SEATS %>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_SEATS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
							<div align="center">
								<html:select name="UserDeskMenuTempMapFB" property="seatsList" multiple="true" size="6">
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif" class="link" onClick='moveSingleInLists(document.getElementsByName("seatsList")[0],document.getElementsByName("selectedSeats")[0]);'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='moveAllInLists(document.getElementsByName("seatsList")[0],document.getElementsByName("selectedSeats")[0]);'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif" class="link" onClick='moveSingleInLists(document.getElementsByName("selectedSeats")[0],document.getElementsByName("seatsList")[0]);'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif" class="link" onClick='moveAllInLists(document.getElementsByName("selectedSeats")[0],document.getElementsByName("seatsList")[0]);'/> 	
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
						<td width="35%" class="tdfonthead"></td>
					</tr>
				</table>
			</his:ContentTag>
		</logic:equal>

		<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP2%>">
			<his:SubTitleTag name="Selected Units">
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
								<html:select name="UserDeskMenuTempMapFB" property="selectedUnits" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS%>" >
										<html:options collection="<%=OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_UNITS%>" property="value" labelProperty="label" />
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

			<his:SubTitleTag name="Selected Users">
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
									<logic:present name="<%=OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_SEATS%>" >
										<html:options collection="<%=OpdConfig.LIST_OF_ENTRY_OBJECTS_OF_SELECTED_SEATS%>" property="value" labelProperty="label" />
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
	</his:statusNew>

	
	<his:statusTransactionInProcess>
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
							<img class="button" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' style='cursor:pointer' onclick ="if(validateAdd()) submitPage('ADDROW')" onkeypress="if(event.keyCode==13) if(validateAdd()) submitPage('ADDROW');">
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
									<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/minus.gif"/>'  onkeypress="if(event.keyCode==13) deleteRow('<%=userDeskMenuTempVO.getTemplateId() %>') ;" onclick="deleteRow('<%=userDeskMenuTempVO.getTemplateId() %>')">
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
		<logic:equal name="UserDeskMenuTempMapFB" property="isMappingStart" value="<%=OpdConfig.YES%>">
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style="cursor:pointer" tabindex="1" onclick ="if(validateSave()) submitPage('SAVEBYUNITSEAT')" onkeypress="if(event.keyCode==13)if(validateSave()) submitPage('SAVEBYUNITSEAT')">
		</logic:equal>				
	</his:statusTransactionInProcess>
	<his:statusNew>
		<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP0%>">
			<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick="if(validateUnitSelection()) submitPage('SELECTSEATS')" onkeypress="if(event.keyCode==13) if(validateUnitSelection()) submitPage('SELECTSEATS')">
		</logic:equal>				
		<logic:equal name="UserDeskMenuTempMapFB" property="isGoPressed" value="<%=OpdConfig.STEP1 %>">
			<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onclick="if(validateSeatSelection()) submitPage('GETMENUSTEMPLATEFORUNITSEAT')" onkeypress="if(event.keyCode==13) if(validateSeatSelection()) submitPage('GETMENUSTEMPLATEFORUNITSEAT')">
		</logic:equal>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="MoveToSelected(); submitPage('CHANGEMODE');" onkeypress="if(event.keyCode==13){MoveToSelected(); submitPage('CHANGEMODE');}">
	</his:statusNew>
	<his:statusUnsuccessfull>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="MoveToSelected(); submitPage('CHANGEMODE');" onkeypress="if(event.keyCode==13){MoveToSelected(); submitPage('CHANGEMODE');}">
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