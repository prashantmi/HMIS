<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="java.util.List"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/utility/dynamicdesk/css/dynamic_desk_style.css"/>

<%
	List lstMenus=(List)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TOP_MENU_DTL);	
%>

<his:javascript src="/hisglobal/utility/dynamicdesk/js/desk.js" />

<script type="text/javascript">
window.onload = function()
{
	<% 	if(lstMenus == null || lstMenus.size()==0)	{	%>
		parent.removeTopMenu();
	<%	}	else	{	%>
		showTopMenu();	
	<%	}	%>	
}

function hideTopMenu()
{
	document.getElementById("topMenu").style.display="none";
	document.getElementById("showButton").style.display="";
	
	parent.hideTopMenu();
}

function showTopMenu()
{
	document.getElementById("topMenu").style.display="";
	document.getElementById("showButton").style.display="none";
	
	parent.showTopMenu();
}
</script>

<% 	if(lstMenus != null && lstMenus.size()>0)	{	
	String colwidth=(100/lstMenus.size())+"%";%>
<div id="topMenu">
	<table width="100%" height="100%" cellpadding="0" cellspacing="0">
		<tr height="99%">
			<td align="center" width="100%">
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
					<logic:iterate id="menu" name='<%=DynamicDeskConfig.DYNAMIC_DESK_TOP_MENU_DTL%>' type="hisglobal.vo.DeskDetailVO">
						<td width="<%=colwidth%>" >
							<his:ContentTag>
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<his:DynamicDeskButtonTag name="menu"> </his:DynamicDeskButtonTag>
									</tr>
								</table>
							</his:ContentTag>
						</td>
					</logic:iterate>
					</tr>
				</table>
			</td>
		</tr>
		<tr height="1%">
			<td width="100%" align="center" valign="middle" class="applicationBackgroundColor">
				<img class="button" src="/HISInvestigationG5/hisglobal/images/triup.gif" tabindex="1" style="cursor:pointer;" tabindex="1" title="Hide Top Menu" onclick="hideTopMenu()" >
			</td>
		</tr>
	</table>
</div>
<div id="showButton">
	<table width="100%" height="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%" height="100%" align="center" valign="middle" class="applicationBackgroundColor">
				<img class="button" src="/HISInvestigationG5/hisglobal/images/tridown.gif" tabindex="1" style="cursor:pointer;" tabindex="1" title="Show Top Menu" onclick="showTopMenu()" >
			</td>
		</tr>
	</table>
</div>
<%	}	%>