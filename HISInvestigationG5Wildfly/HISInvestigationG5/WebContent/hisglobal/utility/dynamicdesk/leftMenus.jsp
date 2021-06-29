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
	List lstMenus=(List)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL);	
%>

<his:javascript src="/hisglobal/utility/dynamicdesk/js/desk.js" />

<script type="text/javascript">
window.onload = function()
{
	<% 	if(lstMenus == null || lstMenus.size()==0)	{	%>		
		parent.removeLeftMenu();
	<%	}	else	{	%>
		showLeftMenu();	
	<%	}	%>	
}

function hideLeftMenu()
{
	document.getElementById("leftMenu").style.display="none";
	document.getElementById("showButton").style.display="";
	
	parent.hideLeftMenu();
}

function showLeftMenu()
{
	document.getElementById("leftMenu").style.display="";
	document.getElementById("showButton").style.display="none";
	
	parent.showLeftMenu();
}
</script>

<% 	if(lstMenus != null && lstMenus.size()>0)	{	%>
<div id="leftMenu">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center" width="99%" height="100%">
				<table width="100%" cellpadding="0" cellspacing="0">
				<logic:iterate id="menu" name='<%=DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL%>' type="hisglobal.vo.DeskDetailVO">
					<tr>
						<td>
							<his:ContentTag>
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<his:DynamicDeskButtonTag name="menu"> </his:DynamicDeskButtonTag>
									</tr>
								</table>
							</his:ContentTag>
						</td>
					</tr>
				</logic:iterate>
				</table>
			</td>
			<td width="1%" height="100%" valign="top" align="center" class="applicationBackgroundColor">
				<img class="button" src="/HISInvestigationG5/hisglobal/images/trileft.gif" tabindex="1" style="cursor:pointer;" tabindex="1" title="Hide Left Menu" onclick="hideLeftMenu()" >
			</td>
		</tr>
	</table>
</div>
<div id="showButton">
	<table width="100%" height="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" height="100%" valign="top" align="center" class="applicationBackgroundColor">
					<img class="button" src="/HISInvestigationG5/hisglobal/images/tri.gif" tabindex="1" style="cursor:pointer;" tabindex="1" title="Show Left Menu" onclick="showLeftMenu()" >
				</td>
			</tr>
	</table>
</div>
<%	}	%>