<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@page import="hissso.config.HISSSOServerConfig"%>
<%@page import="hissso.config.HISSSOConfig"%>
<%@page import="hisglobal.config.HISConfig"%>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>

<title>Login Application</title>
<s:head/>
<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet" type="text/css" media="screen" />
<link href="/HIS/hisglobal/css/loginLayout.css" rel="stylesheet" type="text/css" >

<%-- if Directly then include it otherwise not <script src="/HIS/hisglobal/js/jquery.js"></script> --%>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script type="text/javascript" src="/AHIMSG5/hissso/js/ssoLogin.js"></script>
<script>
var IS_CAPTCHA_REQ = "<%=HISConfig.CAPTCHA_IMPLEMENTATION%>";
var sessionToken = "<%=session.getAttribute(HISSSOServerConfig.LOGIN_SESSION_SALT)%>";
 	  $(function() {
// 		  $(document).ready(function() { 
			$("#idForgotPassword").click(function(){
				//openURLInPopup("/AHIMSG5/hislogin/initForgetPasswordLgnFtr.action","600","300");
				//changed by garima for extension change
				openURLInPopup("/AHIMSG5/hislogin/initForgetPasswordLgnFtr","600","300");				
			});
// 		  });
		});
</script>

</head>

<body onload="document.getElementsByName('varUserName')[0].focus();">
<center>
<h3> Login</h3>

<s:form action="Login" onsubmit="return validate()">

<input type="text" tabindex="1" class="loginTextBox" placeholder="Enter User Name"  value="" name="varUserName" maxlength="20" onkeypress="return validateAlphaNumWithUnderscoreOnly(this,event);" autocomplete="off"><br>
<input type="password" tabindex="1" class="loginTextBox" placeholder="Enter Password"  value="" name="varPassword" maxlength="10" onkeypress="if(event.keyCode==13) submitFormOnValidate(validate(),'loginLogin');" autocomplete="off"><br>
<%if(HISConfig.CAPTCHA_IMPLEMENTATION.equals("ON")) {%>
<input type="text" tabindex="1" class="captcha-Text" style="width:34%"  maxlength="6" name="captchaResponse" placeholder="Enter Captcha" autocomplete="off"/>
 <img id="captchaImg" src="/AHIMSG5/hissso/captchaLogin" style="width:30%" alt="Captcha Image" height="30">
<img src="/HIS/hisglobal/images/buttons/refresh.png" alt="Reload"  onclick="document.forms[0].captchaImg.src='/AHIMSG5/hissso/captchaLogin'+'?id='+Math.random();" style="cursor:pointer"/> 
 
  <%}%>
<input type="button" tabindex="1" value="Login" class="login-button" onclick="submitFormOnValidate(validate(),'loginLogin')"> <br>

<a href="#" class="forgetPwd" tabindex="1" id="idForgotPassword"> Forgot Password? </a>
<div id="divElementErrorsId" class="alertMessage"><s:actionerror/></div>

<s:token></s:token>
</s:form>

</center>
</body>
</html>