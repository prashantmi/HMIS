<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/HISInvestigationTools.tld" prefix="inv"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitToclose()
{
	// alert("window")
	window.self.close();
}
</script>

 
<body bgcolor="#FFFFFF" >

<his:ContentTag>
<his:TitleTag key="testResult" >
<table>
<tr>
		<td width="40%"  >
					<%=request.getParameter("testName")%>
		</td>
	</tr>
	</table>
</his:TitleTag>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	
	<tr>
		<td width="100%" class="tdfont" >
					<font color="#000000" >	<inv:viewInvestigation workOrderNo='<%=request.getParameter("reqDNo")%>' patCRNo='<%=request.getParameter("patCrNo")%>'></inv:viewInvestigation></font>
		</td>
	</tr>
</table>
</his:ContentTag>
<his:ButtonToolBarTag>
 <img class="button" src='<his:path src="/hisglobal/images/close.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToclose();" onkeypress="if(event.keyCode==13) submitToclose();">
</his:ButtonToolBarTag>
</body>
</html>