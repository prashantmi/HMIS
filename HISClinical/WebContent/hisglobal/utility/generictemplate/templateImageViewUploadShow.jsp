<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html>
<body>
<his:statusDone>
	<img src="<%=ServletsUtilityConfig.SERVLET_DISPLAY_BYTE_ARRAY_URL%>">
</his:statusDone>
<his:statusUnsuccessfull>
	<font color="#FF0000"><b>No Image Found</b></font>
</his:statusUnsuccessfull>
</body>
</html>