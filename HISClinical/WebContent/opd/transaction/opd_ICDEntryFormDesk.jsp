
<%@ page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@ page import="org.apache.struts.tiles.ComponentContext"%>

<html>
<head>
<meta http-equiv="pragma" content="no-cache">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>   <tiles:getAsString name='title'  /> </title> 
<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script>
function submitPage(mode)
{
	document.getElementById("frmDeskMain").deskMode.value=mode;
	document.getElementById("frmDeskMain").submit();
}
</script>
</head>

 
<body>

<html:form styleId="frmDeskMain" action="/gotoIcdEntryFormDesk" method="post">

<%
	ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
	String header=(String)compContext.getAttribute("header");
	String body=(String)compContext.getAttribute("body");
	String footer=(String)compContext.getAttribute("footer");
%>

<jsp:include page="<%=header %>" flush="true"></jsp:include>
<jsp:include page="<%=body %>" flush="true"></jsp:include>
<jsp:include page="<%=footer %>" flush="true"></jsp:include>
    
<html:hidden  name='ICDEntryFormDeskFB' property='deskMode'/>



 
</html:form> 
</body>
</html>