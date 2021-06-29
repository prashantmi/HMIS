<!-- 
/**
 * @author CDAC
 */
--><%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.master.controller.fb.DeskMasterFB"%>
<%@page import="hisglobal.utility.Entry"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/color_picker.css" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/colorPicker.js" />
<his:javascript src="/opd/js/desk_master.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<%@ page import="java.util.*,opd.*"%>

<script>
function GoTo(mode)
{
	var curr_mode=document.getElementsByName("hmode")[0].value;
	document.getElementsByName("currentHmode")[0].value= curr_mode;
	document.getElementsByName("hmode")[0].value=mode;
	//alert(document.getElementsByName("currentHmode")[0].value);
	//alert(document.getElementsByName("hmode")[0].value);
	document.forms[0].submit();
	
}
</script>

<body onload="callonload()">
<html:form action="/master/AddModifyMenuToDeskACTION.cnt">
	<html:hidden name="DeskMasterFB" property="hmode" />
	<html:hidden name="DeskMasterFB" property="deskId" />
	<html:hidden name="DeskMasterFB" property="deskType" />
	<html:hidden name="DeskMasterFB" property="currentHmode" />
	
	<his:TransactionContainer>
		<%
		String varStatus = "";
		%>
		<his:statusNew>
			<%
			varStatus = "New";
			%>
		</his:statusNew>
		<his:statusInProcessWithJsp>
			<%
			varStatus = "InProcess";
			%>
			<logic:equal name="DeskMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Desk Master >> ADD">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="DeskMasterFB" property="hmode" value="ADDDESKDETAIL">
				<his:TitleTag name="Desk Master >> ADD">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="DeskMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Desk Master >> MODIFY">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="DeskMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Desk Master >> VIEW">
				</his:TitleTag>
			</logic:equal>
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td>
						<td width="25%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="deskName" />&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<logic:notEqual name="DeskMasterFB" property="hmode" value="VIEW">
									&nbsp;<html:text name="DeskMasterFB" property="deskName" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event)">
									</html:text>
								</logic:notEqual>
								<logic:equal name="DeskMasterFB" property="hmode" value="VIEW">
									<html:hidden name="DeskMasterFB" property="deskName"/>
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b>&nbsp;<bean:write name="DeskMasterFB" property="deskName" /></b>
									</font>
								</logic:equal>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="isDefault" />&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<logic:notEqual name="DeskMasterFB" property="hmode" value="VIEW">
									<logic:equal name="DeskMasterFB" property="checkForDefault" value="0">
										<input type="checkbox" name="chkIsDefault" />
										<html:hidden name="DeskMasterFB" property="isDefault" />
									</logic:equal>
									<logic:equal name="DeskMasterFB" property="checkForDefault" value="1">
										<input type="checkbox" name="chkIsDefault" disabled="disabled" />
										<html:hidden name="DeskMasterFB" property="isDefault" />
									</logic:equal>
								</logic:notEqual>
								<logic:equal name="DeskMasterFB" property="hmode" value="VIEW">
									<html:hidden name="DeskMasterFB" property="isDefault" />
									<input type="checkbox" name="chkIsDefault" disabled="disabled" />
								</logic:equal>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="deskType" />&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<html:hidden name="DeskMasterFB" property="deskTypeDesc" />
									&nbsp;<b><bean:write name="DeskMasterFB" property="deskTypeDesc" /></b>
								</font>
							</div>
						</td>
						<td width="25%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="isActive"/>&nbsp;</b>
							</font></div>
						</td>
						<td width="25%" class="tdfont">
							<div align="left">								
								 <logic:notEqual name="DeskMasterFB" property="hmode" value="VIEW">
									&nbsp;<html:select name="DeskMasterFB" property="isActive">
										<html:option value="<%=Config.IS_VALID_ACTIVE%>"><%=Config.IS_VALID_ARR[Integer.parseInt(Config.IS_VALID_ACTIVE)]%></html:option>
										<html:option value="<%=Config.IS_VALID_INACTIVE%>"><%=Config.IS_VALID_ARR[Integer.parseInt(Config.IS_VALID_INACTIVE)]%></html:option>
									</html:select>
								</logic:notEqual>
								<logic:equal name="DeskMasterFB" property="hmode" value="VIEW">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										&nbsp;<b>
											<logic:equal name="DeskMasterFB" property="isActive" value="<%=Config.IS_VALID_ACTIVE%>">&nbsp;<%=Config.IS_VALID_ARR[Integer.parseInt(Config.IS_VALID_ACTIVE)]%></logic:equal>
											<logic:equal name="DeskMasterFB" property="isActive" value="<%=Config.IS_VALID_INACTIVE%>">&nbsp;<%=Config.IS_VALID_ARR[Integer.parseInt(Config.IS_VALID_INACTIVE)]%></logic:equal>
										</b>
									</font>
								</logic:equal>
							</div>
						</td>
					</tr>
					<tr>
						<td width="25%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td>
						<td width="25%" class="tdfonthead"></td>
						<td width="25%" class="tdfont"></td>
					</tr>
				</table>
			</his:ContentTag>
		</his:statusInProcessWithJsp>
		<%--<logic:notEqual name="DeskMasterFB" property="hmode" value="VIEW">--%>
			<his:statusInProcessWithJsp>
				<his:SubTitleTag name="Set Menus to Desk">
				</his:SubTitleTag>
				<logic:equal name="DeskMasterFB" property="hmode" value="VIEW">
					<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="100%" class="tdfonthead" colspan="3"></td>
							</tr>
							<tr>
								<td width="100%" class="tdfont" colspan="3">
								<div align="center">
								<%	DeskMasterFB fb = (DeskMasterFB) request .getAttribute("DeskMasterFB");
									ArrayList mnuList = fb.getModTopMenuList();
									for (int i = 0; i < mnuList.size(); i++)
									{
										Entry obj = (Entry) mnuList .get(i);
										String[] split = obj.getValue().split("@");
								%>
								 <input value="<%=split[2]%>" type="button" style="height: 0.8cm; width: 6.0cm; font-weight: bold; font-size: large; font-family: sans-serif; font: Times New Roman; border-color: #CCCCFF; border-width: thin; border-collapse: collapse; elevation: above; background-color: <%= split [ 1 ]%>" />
								<%
								}
								%>
								</div>
								</td>
							</tr>
							<tr>
								<td width="100%" class="tdfont" colspan="3"></td>
							</tr>
							<tr>
								<td width="30%" class="tdfont">
								<div align="center">
								<%	fb = (DeskMasterFB) request .getAttribute("DeskMasterFB");
									mnuList = fb.getModLeftMenuList();
									for (int i = 0; i < mnuList.size(); i++)
									{
										Entry obj = (Entry) mnuList .get(i);
										String[] split = obj.getValue().split("@");
								%> <input value="<%=split[2]%>" type="button"
									style="height: 0.8cm; width: 6.0cm; font-weight: bold; font-size: large; font-family: sans-serif; font: Times New Roman; border-color: #CCCCFF; border-width: thin; border-collapse: collapse; elevation: above; background-color: <%= split [ 1 ]%>" />
								<br>
								<%
								}
								%>
								</div>
								</td>
								<td width="40%" class="tdfont"></td>
								<td width="30%" class="tdfont">
								<div align="center">
								<%
											fb = (opd.master.controller.fb.DeskMasterFB) request
											.getAttribute("DeskMasterFB");
									mnuList = fb.getModRightMenuList();
									for (int i = 0; i < mnuList.size(); i++) {
										hisglobal.utility.Entry obj = (hisglobal.utility.Entry) mnuList
										.get(i);
										String[] split = obj.getValue().split("@");
								%> <input value="<%=split[2]%>" type="button"
									style="height: 0.8cm; width: 6.0cm; font-weight: bold; font-size: large; font-family: sans-serif; font: Times New Roman; border-color: #CCCCFF; border-width: thin; border-collapse: collapse; elevation: above; background-color: <%= split [ 1 ]%>" />
								<br>
								<%
								}
								%>
								</div>
								</td>
							</tr>
							<tr>
								<td width="100%" class="tdfont" colspan="3"></td>
							</tr>
							<tr>
								<td width="100%" class="tdfont" colspan="3">
								<div align="center">
								<%
											fb = (opd.master.controller.fb.DeskMasterFB) request
											.getAttribute("DeskMasterFB");
									mnuList = fb.getModBottomMenuList();
									for (int i = 0; i < mnuList.size(); i++) {
										hisglobal.utility.Entry obj = (hisglobal.utility.Entry) mnuList
										.get(i);
										String[] split = obj.getValue().split("@");
								%> <input value="<%=split[2]%>" type="button"
									style="height: 0.8cm; width: 6.0cm; font-weight: bold; font-size: large; font-family: sans-serif; font: Times New Roman; border-color: #CCCCFF; border-width: thin; border-collapse: collapse; elevation: above; background-color: <%= split [ 1 ]%>" />
								<%
								}
								%>
								</div>
								</td>
							</tr>
							<tr>
								<td width="100%" class="tdfonthead" colspan="3"></td>
							</tr>
						</table>
					</his:ContentTag>
				</logic:equal>
				<logic:notEqual name="DeskMasterFB" property="hmode" value="VIEW">
					<his:ContentTag>
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="20%" class="tdfonthead"></td>
								<td width="10%" class="tdfonthead"></td>
								<td width="25%" class="tdfonthead">
								<div align="center">
								<table>
									<tr>
										<td class="tdfonthead" valign="middle"><html:select name="DeskMasterFB" property="topMenuList" multiple="true"
											size="6" onchange="setPreviewMenu(this)" onfocus="setPreviewMenu(this)">
											<logic:present name="DeskMasterFB" property="modTopMenuList">
												<html:optionsCollection name="DeskMasterFB" property="modTopMenuList" value="value" label="label" />
											</logic:present>
										</html:select></td>
										<td class="tdfonthead" valign="middle">
										<div align="center">&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP("4");' />
										<br>
										<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN("4");' /></div>
										</td>
									</tr>
								</table>
								</div>
								</td>
								<td width="10%" class="tdfonthead"></td>
								<td width="35%" colspan="2" valign="top">
								<div align="left"><his:ContentTag>
									<table width="100%" border="0" cellspacing="1" cellpadding="0">
										<tr height="25px">
											<td class="tdfonthead" width="50%">
											<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="color" /></b> </font></div>
											</td>
											<td class="tdfont" width="50%">
											<div align="left"><input name="mnuColor" id="mnuColor" readonly="readonly" value='#000000' type="text"
												maxlength="7" size="10" onchange="setPreviewMenuColor(this.value)"
												style="width: 80px; font-size: 12px; height: 17px; float: left; border: 0px; padding-top: 2px"> <img
												src="/HISClinical/hisglobal/images/select_arrow.gif" id="mnuColor1"
												style="float: right; padding-right: 1px; padding-top: 1px" title="Color Picker"
												onmouseover="this.src='/HISClinical/hisglobal/images/select_arrow_over.gif'"
												onmouseout="this.src='/HISClinical/hisglobal/images/select_arrow.gif'"> <script language="JavaScript">
																	ColorPicker.setup({ inputField : "mnuColor", button : "mnuColor1", singleClick : true });	
																</script></div>
											</td>
										</tr>
										<tr height="25px">
											<td class="tdfonthead" width="50%">
											<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="menuName" /></b> </font></div>
											</td>
											<td class="tdfont" width="50%">
											<div align="left"><input name="mnuName" maxlength="40" width="120px"
												onchange="setPreviewMenuName(this.value);" /> <input name="mnuCode" type="hidden" /></div>
											</td>
										</tr>
										<tr height="20px">
											<td colspan="2" class="tdfonthead">
											<div align="left"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="preview" /></b> </font></div>
											</td>
										</tr>
										<tr height="40px">
											<td colspan="2" class="tdfont">
											<div align="center"><input name="preview" type="button"
												style="height: 0.8cm; width: 80%; font-weight: bold; font-size: large; font-family: sans-serif; font: Times New Roman; border-color: #CCCCFF; border-width: thin; border-collapse: collapse; elevation: above" />
											</div>
											</td>
										</tr>
										
									</table>
									<bean:define id="beanIdDeskType" name="DeskMasterFB" property="deskType" type="java.lang.String"></bean:define>
									<%
										String showLoginBoundOrNot = "display: none;";
										if (beanIdDeskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_NURSING_DESK))
											showLoginBoundOrNot = "display: block;";
									%>
									<div style="<%=showLoginBoundOrNot%>">
									<table width="100%" border="0" cellspacing="1" cellpadding="0">
										<tr height="25px">
											<td class="tdfonthead" width="50%">
											<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="islogbound" /></b> </font></div>
											</td>
											<td class="tdfont" width="50%">
											<div align="left"><input type="radio" name="isMnuLoginBound" value="<%=OpdConfig.YES%>"
												onclick="onchangeLoginBound();" /><bean:message key="yes" /> &nbsp;&nbsp; <input type="radio"
												name="isMnuLoginBound" value="<%=OpdConfig.NO%>" onclick="onchangeLoginBound();" /><bean:message key="no" /></div>
											</td>
										</tr>
									</table>
									<div id="divDutyRole" style="display: none;">
									<table width="100%" border="0" cellspacing="1" cellpadding="0">
										<tr height="25px">
											<td class="tdfonthead" width="50%">
											<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
												key="dutyRole" /></b> </font></div>
											</td>
											<td class="tdfont" width="50%">
											<div align="left"><select name="mnuDutyRoleId" onchange="setPreviewDutyRoleId(this.value)">
												<option value="-1">Select Value</option>
												<logic:present name="<%=OpdConfig.ESSENTIALBO_DESK_MENU_BASED_DUTY_ROLE_LIST%>">
													<logic:iterate id="entObj" name="<%=OpdConfig.ESSENTIALBO_DESK_MENU_BASED_DUTY_ROLE_LIST%>"
														type="hisglobal.utility.Entry">
														<option value="<%=entObj.getValue()%>"><%=entObj.getLabel()%></option>
													</logic:iterate>
												</logic:present>
											</select></div>
											</td>
										</tr>
									</table>
									</div>
									</div>
								</his:ContentTag></div>
								</td>
							</tr>
							<tr>
								<td width="20%" class="tdfonthead"></td>
								<td width="10%" class="tdfonthead"></td>
								<td width="25%" class="tdfont">
								<div align="center"><img src="/HISClinical/hisglobal/images/arrsgl-up.gif" class="link"
									onClick='clearPreview();moveRightSingle("4");' /> <img src="/HISClinical/hisglobal/images/arrdbl-up.gif" class="link"
									onClick='clearPreview();moveRightAll("4");' /> &nbsp;&nbsp;&nbsp;&nbsp; <img
									src="/HISClinical/hisglobal/images/arrsgl-down.gif" class="link" onClick='clearPreview();moveLeftSingle("4");' /> <img
									src="/HISClinical/hisglobal/images/arrdbl-down.gif" class="link" onClick='clearPreview();moveLeftAll("4");' /></div>
								</td>
								<td width="10%" class="tdfonthead"></td>
								<td width="20%" class="tdfonthead"></td>
								<td width="15%" class="tdfonthead"></td>
							</tr>
							<tr>
								<td width="20%" class="tdfonthead">
								<div align="center">
								<table>
									<tr>
										<td class="tdfonthead" valign="middle"><html:select name="DeskMasterFB" property="leftMenuList"
											multiple="true" size="6" onchange="setPreviewMenu(this)" onfocus="setPreviewMenu(this)">
											<logic:present name="DeskMasterFB" property="modLeftMenuList">
												<html:optionsCollection name="DeskMasterFB" property="modLeftMenuList" value="value" label="label" />
											</logic:present>
										</html:select></td>
										<td class="tdfonthead" valign="middle">
										<div align="center">&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP("2");' />
										<br>
										<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN("2");' /></div>
										</td>
									</tr>
								</table>
								</div>
								</td>
								<td width="10%" class="tdfont">
								<div align="center"><img src="/HIS/hisglobal/images/avai/back3.gif" class="link"
									onClick='clearPreview();moveRightSingle("2");' /> <img src="/HIS/hisglobal/images/avai/backward.gif" class="link"
									onClick='clearPreview();moveRightAll("2");' /> <br>
								<br>
								<img src="/HIS/hisglobal/images/avai/forward3.gif" class="link" onClick='clearPreview();moveLeftSingle("2");' /> <img
									src="/HIS/hisglobal/images/avai/forwardward.gif" class="link" onClick='clearPreview();moveLeftAll("2");' /></div>
								</td>
								<td width="25%" class="tdfonthead">
								<div align="center"><html:select name="DeskMasterFB" property="menusList" multiple="true" size="6">
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_MENUS%>">
										<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_MENUS%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select></div>
								</td>
								<td width="10%" class="tdfont">
								<div align="center"><img src="/HIS/hisglobal/images/avai/forward3.gif" class="link"
									onClick='clearPreview();moveRightSingle("3");' /> <img src="/HIS/hisglobal/images/avai/forwardward.gif" class="link"
									onClick='clearPreview();moveRightAll("3");' /> <br>
								<br>
								<img src="/HIS/hisglobal/images/avai/back3.gif" class="link" onClick='clearPreview();moveLeftSingle("3");' /> <img
									src="/HIS/hisglobal/images/avai/backward.gif" class="link" onClick='clearPreview();moveLeftAll("3");' /></div>
								</td>
								<td width="20%" class="tdfonthead">
								<div align="center">
								<table>
									<tr>
										<td class="tdfonthead" valign="middle"><html:select name="DeskMasterFB" property="rightMenuList"
											multiple="true" size="6" onchange="setPreviewMenu(this)" onfocus="setPreviewMenu(this)">
											<logic:present name="DeskMasterFB" property="modRightMenuList">
												<html:optionsCollection name="DeskMasterFB" property="modRightMenuList" value="value" label="label" />
											</logic:present>
										</html:select></td>
										<td class="tdfonthead" valign="middle">
										<div align="center">&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP("3");' />
										<br>
										<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN("3");' /></div>
										</td>
									</tr>
								</table>
								</div>
								</td>
								<td width="15%" class="tdfonthead"></td>
							</tr>
							<tr>
								<td width="20%" class="tdfonthead"></td>
								<td width="10%" class="tdfonthead"></td>
								<td width="25%" class="tdfont">
								<!-- <div align="center"><img src="/HISClinical/hisglobal/images/arrsgl-down.gif" class="link"
									onClick='clearPreview();moveRightSingle("5");' /> <img src="/HISClinical/hisglobal/images/arrdbl-down.gif" class="link"
									onClick='clearPreview();moveRightAll("5");' /> &nbsp;&nbsp;&nbsp;&nbsp; <img
									src="/HISClinical/hisglobal/images/arrsgl-up.gif" class="link" onClick='clearPreview();moveLeftSingle("5");' /> <img
									src="/HISClinical/hisglobal/images/arrdbl-up.gif" class="link" onClick='clearPreview();moveLeftAll("5");' /></div> -->
								</td>
								<td width="10%" class="tdfonthead"></td>
								<td width="20%" class="tdfonthead"></td>
								<td width="15%" class="tdfonthead"></td>
							</tr>
							<tr>
								<td width="20%" class="tdfonthead"></td>
								<td width="10%" class="tdfonthead"></td>
								<td width="25%" class="tdfonthead">
								<div align="center">
								<table>
									<tr>
										<td class="tdfonthead" valign="middle"><%-- <html:select name="DeskMasterFB" property="bottomMenuList"
											multiple="true" size="6" onchange="setPreviewMenu(this)" onfocus="setPreviewMenu(this)">
											<logic:present name="DeskMasterFB" property="modBottomMenuList">
												<html:optionsCollection name="DeskMasterFB" property="modBottomMenuList" value="value" label="label" />
											</logic:present>
										</html:select> --%></td>
										<td class="tdfonthead" valign="middle">
										<!-- <div align="center">&nbsp;<img src="/HIS/hisglobal/images/avai/arr-up.png" class="link" onClick='moveUP("5");' />
										<br>
										<img src="/HIS/hisglobal/images/avai/arr-dwn.png" class="link" onClick='moveDOWN("5");' /></div> -->
										</td>
									</tr>
								</table>
								</div>
								</td>
								<td width="10%" class="tdfonthead"></td>
								<!-- <td width="20%" class="tdfonthead"></td>
								<td width="15%" class="tdfonthead"></td>
								 -->
								 <td colspan="2" class="tdfonthead" bordercolor="">
											<div align="center"><input name="importfromglobal" type="button" value="Fill from Global-Default" onclick="GoTo('GLOBALMAPPING');"
											style="height: 0.8cm; width: 80%; font-weight: bold; font-size: small; font-family: sans-serif; font: Times New Roman; border-color: #CCCCFF;  border-width: thin; border-collapse: collapse; elevation: above" />
											</div>
											</td>
								
							</tr>
						</table>
					</his:ContentTag>
				</logic:notEqual>
			</his:statusInProcessWithJsp>
		<%--</logic:notEqual>--%>
		<his:ButtonToolBarTag>
			<span id="saveDiv">
				<%if (varStatus.equals("InProcess")) {%> 
				<logic:equal name="DeskMasterFB" property="hmode" value="ADD">
					<%--<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitMyForm('ADDDESKDETAIL')" onclick="submitMyForm('ADDDESKDETAIL')">
				</logic:equal>
				<logic:equal name="DeskMasterFB" property="hmode" value="ADDDESKDETAIL">--%>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer"
						onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				</logic:equal>
				<logic:equal name="DeskMasterFB" property="hmode" value="MODIFY">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer"
						onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				</logic:equal>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer"
					onclick="MoveToSelected(); submitPage('CANCEL');" onkeypress="if(event.keyCode==13){ MoveToSelected(); submitPage('CANCEL');}">
				<logic:equal name="DeskMasterFB" property="hmode" value="ADD">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer"
						onclick="MoveToSelected(); submitPage('ADD');" onkeypress="if(event.keyCode==13){MoveToSelected(); submitPage('ADD');}">
				</logic:equal>
				<%--<logic:equal name="DeskMasterFB" property="hmode" value="ADDDESKDETAIL">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer"
						onclick="submitPage('ADD')" onkeypress="if(event.keyCode==13) submitPage('ADD')">
				</logic:equal>--%> 
				<%	} else {	%>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer"
						onclick="MoveToSelected(); submitPage('CANCEL');" onkeypress="if(event.keyCode==13){ MoveToSelected(); submitPage('CANCEL');}">
					<logic:equal name="DeskMasterFB" property="hmode" value="ADD">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer"
							onclick="MoveToSelected(); submitPage('ADD');" onkeypress="if(event.keyCode==13){ MoveToSelected(); submitPage('ADD');}">
					</logic:equal>
					<logic:equal name="DeskMasterFB" property="hmode" value="SAVE">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer"
							onclick="MoveToSelected(); submitPage('ADD');" onkeypress="if(event.keyCode==13){ MoveToSelected(); submitPage('ADD');}">
					</logic:equal> 
				<%	}	%>
  			</span>
		</his:ButtonToolBarTag>
		<html:hidden name="DeskMasterFB" property="deskType" />
			
		
	</his:TransactionContainer>
</html:form>
<center><b><his:status /></b></center>
</body>
</html>