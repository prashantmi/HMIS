<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html>
<head>
<title> </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../../transactionutil/css/master.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../transactionutil/js/master.js"></script>
</head>
<body bgcolor="#CCCCCC" onLoad = "button1('2');">
<html:form action='<%=request.getParameter("cnt_page")%>' >
<div id="buttonId2">
</div>
<input type="hidden" name="hmode">
<input type="hidden" name="cnt_page" value='<%=request.getParameter("cnt_page")%>'>
</html:form>
</body>
</html>