<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ page import="org.apache.struts.tiles.ComponentContext,registration.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	
 </head>
<script>

</script>
<html:form  action="/opd/opdPatientProfile">

<body>
	<%
		ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
		String header=(String)compContext.getAttribute("header");
		String body=(String)compContext.getAttribute("body");
		String footer=(String)compContext.getAttribute("footer");
	%>
<table>

	<tr>
		<jsp:include page="<%=header%>" flush="true"></jsp:include>
	</tr>

	<tr>
		<jsp:include page="<%=body%>" flush="true"></jsp:include>
	</tr>	

	<tr>
		<jsp:include page="<%=footer%>" flush="true"></jsp:include> 
	</tr>
</table>
</body>

<his:status/>
<html:hidden name="OpdPatientProfileFB" property="hmode" />
<html:hidden name="OpdPatientProfileFB" property="patCrNo"  />
</html:form>	
</html>