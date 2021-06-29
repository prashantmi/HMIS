
<%
String menuMode=request.getParameter("loginMode");
startup.login	loginObj	=new startup.login();
//System.out.println("login Mode "+menuMode);

if(menuMode==null)//FROM LOGIN PAGE
{	
	String	uid	=request.getParameter("uid");
	String	pwd	=request.getParameter("pwd");
	
 
	
	loginObj.setUid(uid);
	loginObj.setPwd(pwd);
	
	loginObj.validateUid(request.getRemoteAddr());
	
	String seatid	=	"";
	String empId	=	"";
	String userId   =   "";
	String userName =   "";
	

	//System.out.println(loginObj.getSeatId());
	
	if(!loginObj.getSeatId().equals("NONE"))
	{
		seatid	=loginObj.getSeatId();
		empId	=loginObj.getEmpId();
		userId   =  loginObj.getUserId();
	    userName =  loginObj.getUserName();
		
		session.setAttribute("SEATID",seatid);
		session.setAttribute("EMPID",empId);
		session.setAttribute("userId",userId);
		session.setAttribute("userName",userName);
		
		%>
			<jsp:forward page="mainPage.jsp">
				<jsp:param name="seatId" value="<%=seatid%>"/>
				<jsp:param name="empId" value="<%=empId%>"/>
				<jsp:param name="userId" value="<%=userId%>"/>
				<jsp:param name="userName" value="<%=userName%>"/>

			</jsp:forward>
		<%
	}
	else
	{
		%>
			<jsp:forward page="index.jsp">
				<jsp:param name="status" value="Login Failed"/>
			</jsp:forward>
		<%
	}
}
else if(menuMode.equals("LOGOUT"))
{
	loginObj.logoutUser((String)session.getAttribute("SEATID"),request.getRemoteAddr());
	session.invalidate();
	%>
		<jsp:forward page="index.jsp"/>
	<%
}
	
%>
