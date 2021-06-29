<jsp:useBean id="MenuGeneratorBean" class="startup.menu.MenuGenerator" scope="request" />
<jsp:setProperty name="MenuGeneratorBean" property="*" />

<%
String menuLevel	=	request.getParameter("level");
try
{
if(menuLevel==null)
{
	System.out.println("inside if ");
	MenuGeneratorBean.setRoleIDBySeatId(request.getParameter("seatId"));
	%>
		<jsp:forward page="Menu.jsp" >
			<jsp:param name="level" value="1" />
			<jsp:param name="hyper" value="" />
		</jsp:forward>
	<%
}

else
{
	System.out.println("inside else");
	%>
		<jsp:forward page="Menu.jsp" />
	<%
}


}
catch(Exception e)
{
   System.out.println("Exception at Controller Menu "+e);
}

%>

