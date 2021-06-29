<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.OpdConfig"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value = mode;
	document.forms[0].submit();
}

function closeForm()
{
	self.close();
}

function isFormClose()
{
	var formclose=true;
	<%
		Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
		if(objStatus.contains(Status.NEW))
		{
	%>
		formclose=false;
	<%
		}
	%>
	
	if(formclose)
	{
		if(!window.opener.closed)
		{
			self.close();
		}
	}
}

function addMacro()
{
	var notes="";
	for(var i=0;i<document.getElementsByName("macroHead").length;i++)
	{
		if(document.getElementsByName("macroHead")[i].checked==true)
			notes=document.getElementsByName("macroHead")[i].value;
	}
	if(!window.opener.closed)
		opener.<bean:write name="GenericMacroPopupFB" property="macroTargetFunction" filter="false"/>notes);
	
	closeForm();
}
</script>
</head>

<body onload="isFormClose()">

<html:form action="/genericMacroPopup">
	<his:statusNew>
		<his:TitleTag name="Select Macros">
		</his:TitleTag>
		<%
			List lstMacro=(List)session.getAttribute(OpdConfig.GENERIC_MACRO_POPUP_LIST_BY_PROCESS_ID); 
			if(lstMacro.size()>0)
			{
		%>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="10%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="select"/>
							</font>
						</div>
					</td>
					<td width="90%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;<bean:message key="header"/>
							</font>
						</div>
					</td>
				</tr>
				<%
					for(int i=0;i<lstMacro.size();i++)
					{
						Entry entObj = (Entry) lstMacro.get(i);
				%>
					<tr>
						<td class="tdfont">
							<div align="center">
								<html:radio name="GenericMacroPopupFB" property="macroHead" value="<%=entObj.getLabel() %>" onclick="addMacro()"></html:radio>
							</div>
						</td>
						<td class="tdfont">
							<div align="left">
								<%=entObj.getValue() %>
							</div>
						</td>
					</tr>
				<%	} %>
			</table>
		</his:ContentTag>
		<%	} %>
	</his:statusNew>
	
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
		</his:ButtonToolBarTag>

		<html:hidden name="GenericMacroPopupFB" property="hmode"/>
		<html:hidden name="GenericMacroPopupFB" property="macroProcessId"/>
		<html:hidden name="GenericMacroPopupFB" property="macroTargetFunction"/>
		
<his:status/>

</html:form>
</body>
</html>