<!-- 
/**
 * @author CDAC
 */
-->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>


<%@page import="hisglobal.hisconfig.Config"%>
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

<script>

function deskTypeSelected(deskType)
{
	if(deskType != "-1")
	{
		//alert(deskType);
		submitPage("SELECTDESK");
	}
}

function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit()
{
	if(document.forms[0].deskType.value=="-1")
	{
		alert("Select Desk Type ... ");
		document.forms[0].deskType.focus();
		return false;
	}
	if(document.forms[0].deskId.value=="-1")
	{
		alert("Select Desk ... ");
		document.forms[0].deskId.focus();
		return false;
	}
	if(document.forms[0].isValid.value=="-1")
	{
		alert("Select Valid State ... ");
		document.forms[0].isValid.focus();
		return false;
	}
	return true;
}

function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}
</script>

<%@ page import ="registration.*,opd.*" %>

	<body >
		<html:form action="/master/ModifyViewUserDeskUnitWardMappingMaster.cnt">
			<html:hidden name="UserDeskUnitWardMappingMasterFB" property="hmode"/>
			<html:hidden name='UserDeskUnitWardMappingMasterFB' property='deskMenuId'/>

			<his:TransactionContainer>
			<%
				String varStatus="InProcess";
			%>
			<his:statusInProcessWithJsp>

			<logic:notEqual name="UserDeskUnitWardMappingMasterFB" property="hmode" value="VIEW">
			<his:TitleTag name="Unit Ward User Wise Desk Mapping Master>>Modify">
			</his:TitleTag>
		
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%"  class="tdfonthead"></td>
						<td width="50%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp; <bean:write name='UserDeskUnitWardMappingMasterFB' property='unitName'/></b>
								</font>
								<html:hidden name='UserDeskUnitWardMappingMasterFB' property='deptUnitCode'/>
								<html:hidden name='UserDeskUnitWardMappingMasterFB' property='unitName'/>
							</div>
						</td>
					</tr>
					<logic:notEqual name="UserDeskUnitWardMappingMasterFB" property="userSeatId" value="">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="user"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp; <bean:write name='UserDeskUnitWardMappingMasterFB' property='seatName'/></b>
								</font>
								<html:hidden name='UserDeskUnitWardMappingMasterFB' property='userSeatId'/>
								<html:hidden name='UserDeskUnitWardMappingMasterFB' property='seatName'/>
								
							</div>
						</td>
					</tr>
					</logic:notEqual>
					<logic:notEqual name="UserDeskUnitWardMappingMasterFB" property="wardCode" value="">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="ward"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp; <bean:write name='UserDeskUnitWardMappingMasterFB' property='wardName'/></b>
								</font>
								<html:hidden name='UserDeskUnitWardMappingMasterFB' property='wardCode'/>
								<html:hidden name='UserDeskUnitWardMappingMasterFB' property='wardName'/>
							</div>
						</td>
					</tr>
					</logic:notEqual>
					
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="deskType"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;
										<bean:write name="UserDeskUnitWardMappingMasterFB" property="deskTypeDesc"/>
									</b>
								</font>
								<html:hidden name="UserDeskUnitWardMappingMasterFB" property="deskType"/>
								<html:hidden name="UserDeskUnitWardMappingMasterFB" property="deskTypeDesc"/>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="seldsk"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="deskId" styleClass="registrationCmb">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>" >
									<html:options collection="<%=OpdConfig.EssentialBO_LIST_ALL_DESK_BY_TYPE%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="isvalid"/> <bean:message key="status"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<html:select name="UserDeskUnitWardMappingMasterFB" property="isValid"> <!-- Validation Only Alphabets -->
									<html:option value="-1">Select Value</html:option>
									<html:option value="1">Active</html:option>
									<html:option value="2">In Active</html:option>
								</html:select>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead"></td>
						<td width="50%"  class="tdfont"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:notEqual>
			
			<logic:equal name="UserDeskUnitWardMappingMasterFB" property="hmode" value="VIEW">
			<his:TitleTag name="Unit Ward User Wise Desk Mapping Master>>View">
			</his:TitleTag>
		
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%"  class="tdfonthead"></td>
						<td width="50%"  class="tdfont"></td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="unit"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;<bean:write name='UserDeskUnitWardMappingMasterFB' property='unitName'/></b>
								</font>
							</div>
						</td>
					</tr>
					
					
					
					
					
					<logic:notEqual name="UserDeskUnitWardMappingMasterFB" property="userSeatId" value="">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="user"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;<bean:write name='UserDeskUnitWardMappingMasterFB' property='seatName'/></b>
								</font>
							</div>
						</td>
					</tr>
					</logic:notEqual>
					
					
					
					<logic:notEqual name="UserDeskUnitWardMappingMasterFB" property="wardCode" value="">
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="ward"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;<bean:write name='UserDeskUnitWardMappingMasterFB' property='wardName'/></b>
								</font>
							</div>
						</td>
					</tr>
					</logic:notEqual>
					
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="deskType"/>&nbsp;</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										&nbsp;<bean:write name="UserDeskUnitWardMappingMasterFB" property="deskTypeDesc"/>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="deskName"/>&nbsp;</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>&nbsp;<bean:write name='UserDeskUnitWardMappingMasterFB' property='deskName'/></b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="isvalid"/> <bean:message key="status"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%"  class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isValid" value="1">&nbsp;Active</logic:equal>
										<logic:equal name="UserDeskUnitWardMappingMasterFB" property="isValid" value="2">&nbsp;In Active</logic:equal>
									</b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%"  class="tdfonthead"></td>
						<td width="50%"  class="tdfont"></td>
					</tr>
				</table>
			</his:ContentTag>
			</logic:equal>

			</his:statusInProcessWithJsp>
			
			
			
			
			
			
			
			<his:ButtonToolBarTag>
				<span id="saveDiv">
					<%if(varStatus.equals("InProcess")){%>
						<logic:notEqual name="UserDeskUnitWardMappingMasterFB" property="hmode" value="VIEW">
							<img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" tabindex="1" onclick="finalSubmit('MODIFYSAVE')">
						</logic:notEqual>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
					<%} else{ %>
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
					<%} %>
				</span>
			</his:ButtonToolBarTag>	

			</his:TransactionContainer>
		</html:form>
	</body>
</html>