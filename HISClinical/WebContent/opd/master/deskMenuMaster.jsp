<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="opd.*"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function submitForm(mode)
{	
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function enabledCombo()
{
	if(document.getElementsByName("isTemplateBased")[0].checked)
	{
		document.getElementById("divTemplateCategoryCombo").style.display="block";
		if(document.getElementsByName("deskUrl")[0].value=="")
			document.getElementsByName("deskUrl")[0].value="<%=OpdConfig.TEMPLATE_BASED_DESK_MENU_DEFAULT_URL%>";
	}
	else
	{
		document.getElementById("divTemplateCategoryCombo").style.display="none";
		if(document.getElementsByName("deskUrl")[0].value=="<%=OpdConfig.TEMPLATE_BASED_DESK_MENU_DEFAULT_URL%>")
			document.getElementsByName("deskUrl")[0].value="";
	}

}

function validateForm(mode)
{
	if(document.getElementsByName("deskMenuName")[0].value=="")
	{
		alert("Please Enter Menu Name");
		document.forms[0].deskMenuName.focus();
		return false;
	}	
	if(document.getElementsByName("deskUrl")[0].value=="")
	{
		alert("Please Enter URL");
		document.forms[0].deskUrl.focus();
		return false;
	}	
	if(document.getElementsByName("isTemplateBased")[0].checked==false && document.getElementsByName("isTemplateBased")[1].checked==false)
	{
		alert("Please Select Is Template Based");
		
		return false;
	}	
	if(document.getElementsByName("isTemplateBased")[0].checked)
	{
		if(document.getElementsByName("templateCategory")[0].value=="-1")
		{
			alert("Please Select Template Category");
			document.forms[0].templateCategory.focus();
			return false;
		}
	}
	if(document.getElementsByName("usabilityFlag")[0].value=="")
	{
		alert("Please Select Usability Flag");
		document.forms[0].usabilityFlag.focus();
		return false;
	}	
	submitForm(mode);
	
}
function clearForm()
{	
	document.getElementsByName("deskMenuName")[0].value="";
	document.getElementsByName("deskUrl")[0].value="";
	document.getElementsByName("templateCategory")[0].value="-1";
	document.getElementsByName("usabilityFlag")[0].value="1";
}		
</script>

<body onload="enabledCombo()">
<html:form action="/master/DeskMenuMaster">
	<his:TransactionContainer>
		<his:TitleTag name="Desk Menu Master">
		</his:TitleTag>
		<his:ContentTag>
			<logic:notEqual value="VIEW" property="hmode" name="deskMenuMasterFB">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="menuName" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="deskMenuMasterFB" property="deskMenuName" tabindex="1" maxlength="50" onkeypress="return validateAlphabetsOnly(event,this)" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="url" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="deskMenuMasterFB" property="deskUrl" tabindex="1" maxlength="120" onkeypress="return validateAlphabetsOnly(event,this)"/>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="isTemplateBased" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont" valign="middle">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
									Yes <html:radio name="deskMenuMasterFB" property="isTemplateBased" value="<%=OpdConfig.IS_TEMPLATE_YES %>" onclick="enabledCombo()" /> 
									No <html:radio name="deskMenuMasterFB" property="isTemplateBased" value="<%=OpdConfig.IS_TEMPLATE_NO %>" onclick="enabledCombo()" />
								</b></font>
							</div>
						</td>
					</tr>
				</table>
				<div id="divTemplateCategoryCombo">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="templateCategory" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="deskMenuMasterFB" property="templateCategory" tabindex="1">
									<html:option value="-1">Select Value</html:option>
									<logic:present name="<%=OpdConfig.ESSENTIALBO_LIST_TEMPLATE_CATEGORY%>">
										<html:options collection="<%=OpdConfig.ESSENTIALBO_LIST_TEMPLATE_CATEGORY%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
				</div>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="usabilityFlag" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:select name="deskMenuMasterFB" tabindex="1" property="usabilityFlag" styleClass="registrationCmb">
									<html:option value="<%=DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_NONE %>"><%=DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_ARR[Integer.parseInt(DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_NONE)]%></html:option>
									<html:option value="<%=DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_PROFILE_BASED %>"><%=DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_ARR[Integer.parseInt(DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_PROFILE_BASED)]%></html:option>
								</html:select>
							</div>
						</td>
					</tr>
				</table>
			</logic:notEqual>
			<logic:equal value="VIEW" name="deskMenuMasterFB" property="hmode">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
									<b><bean:message key="menuName" /></b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="deskMenuMasterFB" property="deskMenuName"/></b>
								</font>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000">*</font> <b><bean:message key="url" /></b> </font></div>
						</td>
						<td width="50%" class="tdfont">
						<div align="left"><html:text name="deskMenuMasterFB" property="deskUrl" readonly="true" /></div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000">*</font> <b><bean:message key="isTemplateBased" /></b> </font></div>
						</td>
						<td width="50%" class="tdfont">YES <html:radio name="deskMenuMasterFB" property="isTemplateBased"
							value="<%=OpdConfig.IS_TEMPLATE_YES %>" onclick="enabledCombo()" disabled="true" /> NO <html:radio
							name="deskMenuMasterFB" property="isTemplateBased" value="<%=OpdConfig.IS_TEMPLATE_NO %>" onclick="enabledCombo()"
							disabled="true" /></td>
					</tr>
				</table>
				<logic:equal value="<%=OpdConfig.IS_TEMPLATE_YES %>" name="deskMenuMasterFB" property="isTemplateBased">
					<div id="divTemplateCategoryCombo">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="50%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000">*</font> <b><bean:message key="templateCategory" /></b> </font></div>
							</td>
							<td width="50%" class="tdfont">
							<div align="left"><html:select name="deskMenuMasterFB" property="templateCategory" disabled="true">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=OpdConfig.ESSENTIALBO_LIST_TEMPLATE_CATEGORY%>">
									<html:options collection="<%=OpdConfig.ESSENTIALBO_LIST_TEMPLATE_CATEGORY%>" property="value" labelProperty="label" />
								</logic:present>
							</html:select></div>
							</td>
						</tr>
					</table>
					</div>
				</logic:equal>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<!--<tr>
	  	<td width="50%"  class="tdfonthead">
				<div align="right">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<font color="#FF0000">*</font>
				<b><bean:message key="deskType"/></b>
				</font>
				</div>
	  		</td>
	  	<td width="50%" class="tdfont" >	  
	  	    <html:select name="deskMenuMasterFB" tabindex="1" property="deskType" styleClass="registrationCmb" disabled="true" >
			<html:option value="">Select Value</html:option>
			<logic:present name="<%=OpdConfig.ESSENTIALBO_LIST_DESK_TYPE%>" >
			<html:options collection="<%=OpdConfig.ESSENTIALBO_LIST_DESK_TYPE%>" property="value" labelProperty="label" />
			</logic:present>
	        </html:select>
	    </td>
	  	</tr>
	  	-->
					<tr>
						<td width="50%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000">*</font> <b><bean:message key="usabilityFlag" /></b> </font></div>
						</td>
						<td width="50%" class="tdfont"><html:select name="deskMenuMasterFB" tabindex="1" property="usabilityFlag"
							styleClass="registrationCmb" disabled="true">
							<html:option value="<%=DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_NONE %>"><%=DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_ARR[Integer.parseInt(DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_NONE)]%></html:option>
							<html:option value="<%=DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_PROFILE_BASED %>"><%=DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_ARR[Integer.parseInt(DynamicDeskConfig.DYNAMIC_DESK_MENU_USABILITY_FLAG_PROFILE_BASED)]%></html:option>
						</html:select></td>
					</tr>
				</table>
			</logic:equal>
		</his:ContentTag>
		<his:ButtonToolBarTag>
			<logic:notEqual value="MODIFY" property="hmode" name="deskMenuMasterFB">
				<logic:notEqual value="VIEW" property="hmode" name="deskMenuMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1"
						onclick=" validateForm('SAVE')" onkeypress="if(event.keyCode==13)  validateForm('SAVE')">
				</logic:notEqual>
			</logic:notEqual>
			<logic:notEqual value="VIEW" property="hmode" name="deskMenuMasterFB">
				<logic:equal value="MODIFY" property="hmode" name="deskMenuMasterFB">
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1"
						onclick=" validateForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13)  validateForm('MODIFYSAVE')">
				</logic:equal>
			</logic:notEqual>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1"
				onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			<logic:notEqual value="VIEW" property="hmode" name="deskMenuMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1"
					onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
			</logic:notEqual>
		</his:ButtonToolBarTag>
		<his:status />
		<html:hidden property="hmode" name="deskMenuMasterFB" />
		<html:hidden property="chk" name="deskMenuMasterFB" />
	</his:TransactionContainer>
</html:form>
</body>
</html>
