
<!-- Copyright ©.-->
<!-- created by cdac using Pramati Studio 3.0 -->

<% String message = (String)request.getAttribute("msg");
if (message==null) message ="";
request.removeAttribute("msg");
%>
<html>
<head>
<title>Welcome... May I Help You...!!</title>
<script language="JavaScript" src="../usermgmt/js/Validation.js" type="text/JavaScript"></script>
<LINK href="../css/master.css" type=TEXT/CSS rel=STYLESHEET>
<link href="../css/Color.css" rel="stylesheet" type="text/css">
<link href="../css/hisStyle.css" rel="stylesheet" type="text/css">


<script language='javascript'>
function formSubmit(e)
{
	if(e.type=="click"||e.keyCode==13)
	{
		document.form1.hmode.value="RESET_PASS";
		document.forms[0].submit();
	}
}

</script>
</head>

<body>
<table width="100%" border="0">
	<tr>
		<td width="68%" colspan=2>
		<div align="center"><img src="../images/e_sushrut_header.gif"
			width="760" height="74"></div>
		</td>
	</tr>
</table>
<br>

<form name='form1' method='post' action="admin">
<table width="75%" border="1" align='center'>
	<tr>
		<td width="68%" class='tdfonthead'>
		<div align="center">May I help you to remember your password....!!</div>
		</td>
	</tr>
	<tr>
		<td width="68%" class='tdfont'>
		<div align="center">User Name : <input type='text' name='user'
			 size='20' onkeypress = "return checkInput(this,3,15,event);"></div>
		</td>
	</tr>
	<tr>
		<td width="68%" class='tdfont'>
		<div align="center">Hint Answer :&nbsp; <input type='text' name='hint'
		 size='20'  onkeypress= "return checkInput(this,3,15,event);" onKeyPress="formSubmit(event);"></div>
		</td>
	</tr>
	<tr>
		<td width="68%" class='tdfont'>
		<div align="center"><img src="../images/verify_tab.gif"
			style="cursor:hand" onKeyPress="formSubmit(event);"
			onClick="formSubmit(event);"></div>
		</td>
	</tr>
	<tr>
		<td width="68%" class='tdfont'>
		<div align="center"><font color="#FF3300"><%=message%></font></div>
		
		</td>
	</tr>


</table>
<br>
<div align="center"><font color="#FF3300"><a href="login.jsp">Back to
Login Form</a></font></div>
 <input type='hidden' name='hmode'></form>


</body>
</html>
