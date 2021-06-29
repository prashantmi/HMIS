<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
<jsp:useBean id="profileBean" class="usermgmt.reports.UmgmtUserProfileBean"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK href="../../css/master.css" type=TEXT/CSS rel=STYLESHEET>
<link href="../../css/Color.css" rel="stylesheet" type="text/css">
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<script>
window.history.forward();
</script>
<script language="javascript">
function onMouseScroll(ev)
{
	var str=ev.innerHTML;
	window.status=' ';
}


function subForm(e)
{
	if(e.keyCode==13)
	{
		document.forms[0].submit();
	}
}
</script>
</head>
<body>
<form action="umgmtUserProfile_ListPageMst.jsp">
<%
System.out.println("------------list page user profile --------------");
String hCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
System.out.println("The value of HospitalCode:-"+hCode);
String searchString=request.getParameter("search");
if(searchString==null)
	searchString="";
String html=profileBean.getUserList(searchString,hCode);
%>
<table class="formbg" width="97%" align="center" cellspacing='1' cellpadding='0'>
	<tr class="label">
		<td>
			<b><font size='2' face='verdana'>&nbsp;&nbsp;&nbsp;Total Users  : <%= profileBean.getCountUsers()%></font></b>
		</td>
	</tr>
	<tr class="label">
		<td>
			<b><font size='2' face='verdana'>&nbsp;&nbsp;&nbsp;Logged In  : <%= profileBean.getCountLoggedIn()%></font></b>
		</td>
	</tr>
	
	<tr class="ShadedSubTitleTagImage"  height='15'>
		<td><b><font size='2' face='verdana'>&nbsp;&nbsp;&nbsp;User's List</font></b></td>
	</tr>
	<tr>
		<td>
			<input type='text' name='search' onclick="document.forms[0].search.value=''" onfocus="document.forms[0].search.value=''" onkeypress='subForm(event)'>
			<a style="float: right;margin-right: 20%;width: 50px;" class="button"  tabindex='0'onClick='document.forms[0].submit()' ><span>Go</span></a>
		</td>
	</tr>
	<tr>
		<td>
		
		
			<%=html %>
		</td>
	</tr>
	
	<tr class="ShadedSubTitleTagImage"  height='10'>
		<td><font size='3' face='verdana'>&nbsp;</font></td>
	</tr>
	
</table>
</form>
</body>
</html>