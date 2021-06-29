<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<his:css src="/hisglobal/css/tab.css"/>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<title>Print</title>
<SCRIPT LANGUAGE="JavaScript"> 
</script>
<form>
<%String codebase=request.getContextPath()+ "/hisglobal/utility/print/"; %>

<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td>
		<%-- mayscript attribute allow the applet to run javascript code--%>
		<applet id="printApplet" name="printApplet" code="hisglobal.utility.print.PrintApplet" height="200" width="400"
					archive="print-applet.jar,PDFRenderer.jar,his-html-to-pdf.jar, itext-1.3.1.jar,htmlparser.jar" codebase='<%=codebase%>' mayscript>
			<param name="isCopiesEditable" value="<%=request.getParameter("isCopiesEditable")%>"/>		
			<param name="noOfCopies" value="<%=request.getParameter("copies") %>"/>		
		</applet>
		</td>
	   </tr>
</table>
</form>


