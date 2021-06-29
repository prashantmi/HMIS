<%@page import="hissso.config.HISSSOConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />
<title>Login Application</title>
<s:head/>
<script type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>
<script type="text/javascript" src="/AHIMSG5/startup/transactions/js/login.js"></script>
<script type="text/javascript">
var sessionToken = "<%=session.getAttribute(HISSSOConfig.LOGIN_SESSION_SALT)%>";
</script>
</head>

<body>
<center><br><br><br><br>
<div id="divElementErrorsId"><s:actionerror/></div>
<h1>Application Login</h1>
<s:form action="Login" onsubmit="return validate()" >

<br>
<table>
<tr><td>User Name :</td><td><input name="varUserName"/></td></tr>
<tr><td>Password: </td><td><input type="password" name="varPassword" /></td></tr>

<tr><td colspan="2" align="right">
<input type="button" value="Login" onclick="submitFormOnValidate(validate(),'loginLogin')"/>
</td></tr>
</table>
<s:token/>
</s:form>
</center>
</body>
</html>