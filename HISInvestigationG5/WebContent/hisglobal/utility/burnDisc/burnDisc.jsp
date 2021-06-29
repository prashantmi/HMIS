<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<his:css src="/hisglobal/css/tab.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<title>Burn Disc</title>
<SCRIPT LANGUAGE="JavaScript"> 
function closeWindow(){
	opener.updateStatus();
	self.close();
}
</script>
<%String codebase=request.getContextPath()+ "/hisglobal/utility/burnDisc/"; %>
<%//String path="C:\\HISInvestigationG5\\cdburnFile";%>
<%String winPath=request.getParameter("winPath");%>
<%String linuxPath=request.getParameter("linuxPath");%>
<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td>
		<%-- mayscript attribute allow the applet to run javascript code--%>
		<applet id="burnDiscApplet" name="burnDiscApplet" code="hisglobal.utility.burnDisc.BurnDiscApplet" height="200" width="400"
					archive="JStarBurn.jar,burnDisc.jar" codebase='<%=codebase%>' mayscript>
			<param name="winPath" value="<%=winPath%>"/>		
			<param name="linuxPath" value="<%=linuxPath%>"/>		
		</applet>
		</td>
	   </tr>
</table>
