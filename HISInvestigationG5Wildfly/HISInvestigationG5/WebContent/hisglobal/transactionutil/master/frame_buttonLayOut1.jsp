<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html>
<head>
<title> </title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="../../transactionutil/js/master.js"></script>
<link href="../../transactionutil/css/master.css" rel="stylesheet" type="text/css">
</head>
<body onLoad = "button1('1');" bgcolor="#CCCCCC">
<html:form action='<%=request.getParameter("cnt_page")%>' >
<div id="buttonId">
</div>
<input type="hidden" name="hmode">
<input type="hidden" name="cnt_page" value='<%=request.getParameter("cnt_page")%>'>
</html:form>
</body>
</html>