
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Error Page</title>

</head>

<body >
<h1> <font face="Verdana, Arial, Helvetica, sans-serif">
	Sorry, but the document you are requesting cannot be displayed.
</font></h1>

<font face="Verdana, Arial, Helvetica, sans-serif">
	Evidently you are trying to acccess the document by pressing the back button.
    <Br>
    To mOve to Login Page Click <A href='<%=request.getContextPath() + hisglobal.HisGatewayProcessor.LOGIN_PAGE%>'> Login Page </A> 
</font>
</body>
</html>