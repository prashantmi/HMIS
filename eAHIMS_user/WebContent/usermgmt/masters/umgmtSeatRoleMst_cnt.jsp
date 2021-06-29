<jsp:useBean id="beanObj" class="usermgmt.masters.UmgmtSeatRoleMstBean" scope="request">
<jsp:setProperty name="beanObj" property="*"/>
</jsp:useBean>

<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request"/>

<%@ page import = "java.util.*" %>
<%@page import="usermgmt.umgmtGlobal"%>
<html>
<head>



<%

String seatId = (String)request.getSession().getAttribute("SEAT_ID");	
String hospCode = (String)request.getSession().getAttribute("HOSPITAL_CODE");

if(seatId == null && hospCode == null)
{		
	%>
		<jsp:forward page="umgmtWarningPage.jsp"/>		
	<%
}
	beanObj.setSeatId(seatId);
	beanObj.setHospitalCode(hospCode);
	
	//Declare Variable
	boolean retValue 		= true;
	List AL_List;
	
	String 	status      	= "";
	String 	result_page 	= "result.jsp";
	String  menuPage		= "../../startup/content.jsp";
	String  cntPage			= "umgmtSeatRoleMst_cnt.jsp";
	String MainFlatNew = "umgmtSeatRoleMappingMst_main.jsp";
	
	/*
	TextArea	--> "T"
	CheckBox	--> "C"
	Option		--> "O"
	Default		--> TextBox(No need to specify)
	*/
	
	String 	mode         	= request.getParameter("hmode");
	System.out.println("mode in controller IS "+mode);


		if(mode == null || mode.equals("DEFAULT"))
		{		
		%>
			<jsp:forward page = "<%=MainFlatNew%>"/>
		<%
		}
	
		if(mode.equals("TEMP"))
		{
			System.out.println("Module in controller is :"+request.getParameter("module"));
			
		%>
			<jsp:forward page = "<%=MainFlatNew%>"/>
		<%
		}
		
		///////////////////////
		if(mode.equals("SAVE"))
	{
		retValue = beanObj.insertRecord();
		if (retValue)
		{
           beanObj.initializeNewMode();
			%>
			<jsp:forward page="<%=result_page%>">
				<jsp:param name="nextpage" value="<%=cntPage%>" />
				<jsp:param name="Name" value="Result >>" />
				<jsp:param name="mode" value="DEFAULT" />
				<jsp:param name="status" value="<%=beanObj.getStatus()%>" />
			</jsp:forward>
			<%	
			}
			else
			{
			%>
			
			<jsp:forward page="<%=result_page%>">
				<jsp:param name="nextpage" value="<%=cntPage%>" />
				<jsp:param name="Name" value="Error Page >>" />
				<jsp:param name="mode" value="DEFAULT" />
				<jsp:param name="status" value="<%=beanObj.getStatus()%>" />
			</jsp:forward>
			<%	
		
			}
		}

		///////////////////////
		
		
		
		if(mode.equals("CLEAR"))
		{
			retValue = beanObj.reset();
			%>
			<jsp:forward page="<%=MainFlatNew%>"/>
			<%
		}
		
		if(mode.equals("CANCEL"))
		{
			
			%>
			<jsp:forward page="<%=menuPage%>"/>
			<%
		}
		
%>