<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<his:css src="/hisglobal/css/tab.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<title>finger print verification</title>
<SCRIPT LANGUAGE="JavaScript"> 

</script>
<form>
<%String codebase=request.getContextPath()+ "/hisglobal/utility/biometric/"; %>

<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td>
		<%-- mayscript attribute allow the applet to run javascript code--%>
		<applet id="fingerPrint" name="fingerPrint" code="hisglobal.utility.biometric.FingerPrintVeriApplet" height="400" width="500"
					archive="fpv-applet.jar, Nffv.jar" codebase='<%=codebase%>' mayscript>
		</applet>
		</td>
	   </tr>
</table>
<form>
