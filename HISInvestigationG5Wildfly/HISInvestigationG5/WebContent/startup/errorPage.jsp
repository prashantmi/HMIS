
<!-- Copyright ©.-->
<!-- created by dell using Pramati Studio 3.0 -->
 
<HTML>
	<HEAD>
		<TITLE>
			Welcome to error.jsp
		</TITLE>
	</HEAD>
	<BODY>
		<FORM METHOD='POST'>
			<%
		  		String status=(String)request.getAttribute("message");
				if(status!=null && !status.equals(" "))
					out.println("<font color=Red style=verdana size=3><strong> "+status+" </strong</font>");
			%>	
		</FORM>
	</BODY>
</HTML>
