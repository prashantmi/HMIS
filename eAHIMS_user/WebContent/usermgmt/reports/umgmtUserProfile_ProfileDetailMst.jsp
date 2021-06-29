<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="profileBean"
	class="usermgmt.reports.UmgmtUserProfileBean" />
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
</head>
<body>
<%
System.out.println("------------ page user profile Detail  --------------");
String uid=request.getParameter("uid"); 
System.out.println("The value of uid:-"+uid);
String userSid=request.getParameter("userSid"); 
System.out.println("The value of userSid:-"+userSid);
String userName=request.getParameter("userName");
System.out.println("The value of userName:-"+userName); 
String roleId = request.getParameter("roleId");
System.out.println("The value of roleId:-"+roleId);
String hCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
System.out.println("The value of HospitalCode:-"+hCode);

String ipAddress=request.getParameter("ipAddress");
System.out.println("The value of ipAddress--:-"+ipAddress);

if(roleId==null)
	roleId = "";

if(userName==null)
{
System.out.println("inside userName equals to Null");
	userName="No User Selected";
	}
%>
<form action="" class="formbg" >
<table class="topmargin" width="100%" cellspacing='1' cellpadding='0'>

	<tr>
		<td><b><font size='2' face='verdana'>&nbsp;&nbsp;&nbsp;User
		:&nbsp;</font></b><font
			size='2' face='verdana'><%=profileBean.getUserInfo(uid,hCode) %></font>
			
		
	
	
	      <font size='1' face='verdana'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	           <b>Last Login Time :</b>&nbsp;<%= profileBean.getLastLoginTime(uid,hCode,ipAddress) %>
	       </font>		
			
	</td>
		
	</tr>
	<tr>
		<td><b><font size='2' face='verdana'>&nbsp;&nbsp;&nbsp;Seat
		:</font></b><font size='2' face='verdana'> <%=profileBean.getSeatName(userSid,hCode) %>
		</font></td>
	</tr>
	<tr>
		<td class="ShadedSubTitleTagImage" height='15' align='center'><b><font size='2' face='verdana'>&nbsp;&nbsp;&nbsp;User's
		Profile</font></b></td>
	</tr>
</table>
<%= profileBean.getUserRoles(userSid,userName,uid,hCode,ipAddress) %>
<%=profileBean.getMenuDetail(userSid,roleId,hCode)%>
<table width="100%" cellspacing='1' cellpadding='0'>
	<tr class="ShadedSubTitleTagImage" height='10'>
		<td><font size='2' face='verdana'>&nbsp;</font></td>
	</tr>
</table>
</body>
</html>
