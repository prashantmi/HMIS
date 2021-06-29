
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ page autoFlush="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="org.apache.struts.tiles.ComponentContext"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>   <tiles:getAsString name='title'  /> </title> 
<link href="/hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />

<script>
function submitPage(mode)
{
	document.forms[0].deskmode.value=mode;
	document.forms[0].submit();
}
</script>
</head>

<%
	ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
	String header=(String)compContext.getAttribute("header");
	System.out.println("header is:"+header);
	String body=(String)compContext.getAttribute("body");
	System.out.println("Body is:"+body);
	
%>

<body>
	<his:TransactionContainer>
	<html:form styleId="frmDeskMain" action="/receiveCertificateInMrdDesk" method="post">
		
		<jsp:include page="<%=header %>" flush="true"></jsp:include>
		<jsp:include page="<%=body %>" flush="true"></jsp:include>

		<html:hidden name="ReceiveCertificateInMrdDeskFB" property="deskmode"/>
	</html:form>
	<his:status/>
	</his:TransactionContainer>
</body>
</html>