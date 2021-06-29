<jsp:useBean id="beanObj" class="usermgmt.masters.umgmtRoleSeatMenuMstBean" scope="request">
<jsp:setProperty name="beanObj" property="*"/>
</jsp:useBean>

<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request"/>

<%@ page import = "java.util.*" %>
<%@ page autoFlush="true" buffer="1094kb"%>
<html>
<head>



<%

String seatId = (String)session.getAttribute("SEATID");	
if(seatId == null)
{		
	%>
		<jsp:forward page="umgmtWarningPage.jsp"/>		
	<%
}
	beanObj.setSeatId(seatId);

//Declare Variable
boolean retValue 		= true;
List AL_List;

String 	status      	= "";
String 	list_page 		= "umgmtRoleSeatMenuMst_lst.jsp";
String 	add_page 		= "umgmtRoleSeatMenuMst_add.jsp";
String 	view_page 		= "umgmtRoleSeatMenuMst_view.jsp";
String 	cnt_page 		= "umgmtRoleSeatMenuMst_cnt.jsp";
String 	result_page 	= "result.jsp";
String 	mod_page 		= "umgmtRoleSeatMenuMst_mod.jsp";
String rpt_page			= "umgmtRoleSeatMenuMst_rpt.jsp";

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
viewHdr.add("Group Associated");
viewHdr.add("Effective Date");
//Control Type



String 	mode         	= request.getParameter("hmode");
System.out.println("mode in controller IS "+mode);


	if(mode == null || mode.equals("DEFAULT"))
	{		
	%>
		<jsp:forward page = "<%=list_page%>"/>
	<%
	}

	
	if(mode.equals("ADD"))
	{		
	%>
		<jsp:forward page="<%=add_page%>">
		<jsp:param name = "hmode" value="SAVE"/>
		</jsp:forward>
	<%
	}
	
	if(mode.equals("SAVE"))
	{
		
		retValue = beanObj.insertRecord();		
		
		
		if (retValue)
		{
			beanObj.initializeNewMode();
			
	%>
			<jsp:forward page="<%=add_page%>"/>
	<%	
		}
		else
		{
			
	%>	
			<jsp:forward page="<%=result_page%>">
			<jsp:param 	name = "nextpage" 	value="<%=cnt_page%>"/>
			<jsp:param 	name = "Name" 		value="Error Page >>"/>
			<jsp:param 	name = "mode" 		value="DEFAULT"/>
			<jsp:param 	name = "status" 	value="<%=beanObj.getStatus()%>"/>
			</jsp:forward>
	<%	
		}
	}
	if(mode.equals("TEMP"))
	{
		System.out.println("Module in controller is :"+request.getParameter("module"));
		
	%>
		<jsp:forward page = "<%=add_page%>"/>
	<%
	}
	if(mode.equals("TEMP_PERSIST"))
	{		
	%>
		<jsp:forward page = "<%=add_page%>"/>
	<%
	}
	if(mode.equals("TEMP_MODIFY"))
	{
		
	%>
		<jsp:forward page = "<%=mod_page%>"/>
	<%
	}
	if(mode.equals("EDIT"))
	{

		
		
		
		AL_List = beanObj.initializeUpdateMode();
	%>
			<jsp:forward page = "<%=mod_page%>">
			<jsp:param name = "hmode" value="UPDATE"/>
			</jsp:forward>			
	<%
	}
	if(mode.equals("UPDATE"))
	{
		retValue = beanObj.updateRecord(request);
		if (retValue)
		{
			
	%>
			<jsp:forward page="<%=list_page%>"/>
	<%	
		}
		else
		{
			
	%>	
			<jsp:forward page="<%=result_page%>">
			<jsp:param name = "mode" value="DEFAULT"/>
			<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
			<jsp:param name = "Name" value="Error Page >>"/>
			<jsp:param name = "status" value="<%=beanObj.getStatus()%>"/>
			
			</jsp:forward>
	<%	
		}
	}
	if(mode.equals("DELETE"))
	{
		status = beanObj.deleteRecord();
		%>
		<jsp:forward page="<%=result_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="status" value="<%=status%>" />
			<jsp:param name="mode" value="DEFAULT" />
		</jsp:forward>
<%
	}
	if(mode.equals("VIEW"))
	{
		AL_List = beanObj.initializeUpdateMode();
		%>
		<jsp:forward page="<%=view_page%>"/>		

		<%		
	}
	if(mode.equals("TEMP_VIEW"))
	{
		//AL_List = beanObj.initializeUpdateMode();
		%>
		<jsp:forward page="<%=view_page%>"/>		

		<%		
	}
	if(mode.equals("REPORT"))
	{		
		%>
		<jsp:forward page="<%=rpt_page%>"/>

		
		<%
	}
%>

</head>
<body>
</body>
</html>