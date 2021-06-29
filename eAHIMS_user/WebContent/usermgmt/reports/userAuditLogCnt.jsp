<%	System.out.println("inside controller again");  %>
<%System.out.println(" hmode is "+request.getParameter("hmode")); %>

<jsp:useBean id="newseat" class="usermgmt.reports.userAuditLogReport" scope="request">
<jsp:setProperty name="newseat" property="*" />
</jsp:useBean>



<%@ page import = "java.util.*" %>
<%@ page autoFlush="true" buffer="1094kb"%>
<script>
window.history.forward();
</script>
<html>
<head>
<%
System.out.println("inside seat role log report ");
String seatId = (String)session.getAttribute("SEAT_ID");
String hospital=(String)session.getAttribute("HOSPITAL_CODE");
newseat.setHospitalcode(hospital);
newseat.setSeatId(seatId);

if(seatId == null)
{		
	%>
		<jsp:forward page="umgmtWarningPage.jsp"/>		
	<%
}


//Declare Variable
boolean retValue 		= true;
List AL_List;

String 	status      	= "";
String 	add_page 		= "userAuditLogReport.jsp";
String 	cnt_page 		= "userAuditLogCnt.jsp";
String 	result_page 	= "result.jsp";


/*
TextArea	--> "T"
CheckBox	--> "C"
Option		--> "O"
Default		--> TextBox(No need to specify)
*/

//View Functionality
List	viewHdr			=	new ArrayList();
Map viewControl 		= 	new HashMap();

//Header
viewHdr.add("Group Name");
viewHdr.add("Seat Name");
viewHdr.add("Column Name");
//Control Type



String 	mode         	= request.getParameter("hmode");
System.out.println("mode IS "+mode);


	if(mode == null || mode.equals("DEFAULT"))
	{		
	%>
		<jsp:forward page = "<%=add_page%>"/>
	
	<% }%>	
				
</head>
</html>