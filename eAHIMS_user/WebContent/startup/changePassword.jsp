
<% String message = (String)request.getAttribute("msg");
if (message == null) message ="";
request.removeAttribute("msg");
%>
<html>
<head>
<title>Welcome... you can change your Password here...!!</title>
<script language="JavaScript" src="../usermgmt/js/Validation.js" type="text/JavaScript"></script>
<LINK href="../css/master.css" type=TEXT/CSS rel=STYLESHEET>
<link href="../css/Color.css" rel="stylesheet" type="text/css">
<link href="../css/hisStyle.css" rel="stylesheet" type="text/css">


<script language='javascript'>
function formSubmit(e)
{
	if(e.type=="click"||e.keyCode==13)
	{
		with( document.form1)
		{
			
			if ( user.value =="" || oldPass.value =="" || newPass.value=="" || confirmPass.value=="")
			{	alert("some field(s) are left blank..."); return false ; }
			
			if (  newPass.value != confirmPass.value )
			{	  
				 alert("check Your Confirm Password please........!!");
				 document.form1.reset();
				 return false ;
			}
			
				   			
			hmode.value="CHANGE_PASSWORD";
			submit();
		}
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
<br>

<form name='form1' method='post' action="admin">
<table width="75%" border="1" align='center'>
	<tr>
		<td width="68%" colspan='2' class='tdfonthead'>
		<div align="center">You can change your Password here...!!</div>
		</td>
	</tr>
	<tr>
		<td class='tdfont'>
		<div align="center">User Name
		:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
			type='text' name='user'size='20' onkeypress = "return checkInput(this,3,15,event);"></div>
		</td>
	</tr>
	<tr>
		<td class='tdfont'>
		<div align="center">Old Password
		:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type='password'
			name='oldPass' size='20' onkeypress = "return checkInput(this,3,15,event);"></div>
		</td>
	</tr>
	<tr>
		<td class='tdfont'>
		<div align="center">New Password :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
			type='password' name='newPass' size='20' onkeypress = "return checkInput(this,3,15,event);"></div>
		</td>
	</tr>
	<tr>
		<td class='tdfont'>
		<div align="center">Confirm Password :&nbsp; <input type='password'
			name='confirmPass' size='20' onkeypress = "return checkInput(this,3,15,event);"></div>
		</td>
	</tr>

	<tr>
		<td class='tdfont'>
		<div align="center"><img src="../images/Save.gif" style="cursor:hand"
			onKeyPress="formSubmit(event);" onClick="formSubmit(event);"></div>
		</td>
	</tr>
	<tr>
		<td class='tdfont'>
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
