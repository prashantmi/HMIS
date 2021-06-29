<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html:base/>
</head>
<body>
<html:form action="/registration/master/TestBaseTag">
<html:text property="name" />
<html:link href="addDeptHistMstACTION.cnt">click</html:link>
<br>
<html:link href="errorPageReportDataNotFound.jsp">click New</html:link>
</html:form>
</body>
</html:html>
