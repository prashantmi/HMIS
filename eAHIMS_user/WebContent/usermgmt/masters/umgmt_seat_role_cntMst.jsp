
<%	System.out.println("inside controller again");  %>
<%System.out.println(" hmode is "+request.getParameter("hmode")); %>
<jsp:useBean id="newseat" class="usermgmt.masters.UmgmtSeatRoleAddMstBean" scope="request">
<jsp:setProperty name="newseat" property="*"/>
</jsp:useBean>



<%@ page import = "java.util.*" %>
<%@page import="login.CSRFTokenUtil"%>
<%@ page autoFlush="true" buffer="1094kb"%>

<script>
window.history.forward();
</script>
<html>
<head>
<%
System.out.println("inside controller");
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
String 	add_page 		= "umgmt_seat_role_addmst.jsp";
String 	cnt_page 		= "umgmt_seat_role_cntMst.jsp";
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
	<%
	}			
	%>
			
	<%
	if(mode.equals("TEMP"))
	{		
	%>
		<jsp:forward page = "<%=add_page%>"/>
	<%
	}
	if(mode.equals("EDIT"))
	{
		System.out.println("INSIDE MODE EDIT");		
	%>				
				
	<%
	}
	if(mode.equals("UPDATE"))
	{		
		if(CSRFTokenUtil.isValid(request))
		{	
		if (retValue)
		{			
	%>
			<jsp:forward page="<%=add_page%>"/>
	<%	
		}
		else
		{
			
	%>	
			<jsp:forward page="<%=result_page%>">
				<jsp:param name = "mode" value="DEFAULT"/>
				<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
				<jsp:param name = "Name" value="Error Page >>"/>
				<jsp:param name = "status" value="<%=newseat.getStatus()%>"/>			
			</jsp:forward>
	<%	
		}
		}
		else
		{
			%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
			<%
		}
	}
	if(mode.equals("DELETE"))
	{
		
		%>
		<jsp:forward page="<%=result_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="status" value="<%=status%>" />
			<jsp:param name="mode" value="DEFAULT" />
		</jsp:forward>
	<%
	}	

	
	if((mode.equals("SAVE"))||(mode=="SAVE"))
	{
		//System.out.println("inside save mode");
		if(CSRFTokenUtil.isValid(request))
		{	
		retValue=newseat.insertRecord(request);		
		
		if (retValue)
		{
	
	
		
			newseat.initializeNewMode();			
	%>			
			<jsp:forward page="<%=result_page%>">
	     	<jsp:param name="nextpage" value="<%=cnt_page%>"/>
	     	<jsp:param name="Name" value="Result >" />
		<jsp:param name="mode" value="DEFAULT" />
		<jsp:param name="status" value="<%=newseat.getStatus()%>" />
		</jsp:forward>
	<%	
		}
		else
		{
			
	%>	
			<jsp:forward page="<%=result_page%>">
			<jsp:param 	name = "nextpage" 	value="<%=cnt_page%>"/>
			<jsp:param 	name = "Name" 		value="Error Page >>"/>
			<jsp:param 	name = "mode" 		value="DEFAULT"/>
			<jsp:param 	name = "status" 	value="<%=newseat.getStatus()%>"/>
			</jsp:forward>
	<%	
		}
		}
		else
		{
			%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
			<%
		}
	}
			if(mode.equals("CLEAR"))
			{
			
				//newseat.initializeNewMode();			
				%>
							<jsp:forward page="<%=add_page%>"/>
		<%	
			}
			%>
				
</head>
</html>