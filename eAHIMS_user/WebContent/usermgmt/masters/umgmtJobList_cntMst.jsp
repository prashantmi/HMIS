	<jsp:useBean id="group" class="usermgmt.masters.UmgmtJobMstBean" scope="request"/>
	<jsp:setProperty name="group" property="*"/>
	<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request"/>
	<%@page import="java.util.*"%>
		
<html>
	<head>
	<%
	String strHospitalCode = (String)session.getAttribute("HOSPITAL_CODE");	
group.setHospitalcode(strHospitalCode);
	
	//Declare Variable
	
	boolean retValue 		= true;
	List AL_List;
	String 	status      	= "";
	
	String 	list_page 		= "umgmtJob_lstMst.jsp";
	String 	cnt_page 		= "umgmtJobList_cntMst.jsp";
	String 	result_page 	= "result.jsp";
	String rpt_page			= "gbl_print_mst.jsp";
	
	
String seatId = (String)session.getAttribute("SEAT_ID");	
	
	if(seatId == null)
	{		
		%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
		<%
	}
	//group.setSeatId(seatId);
	
	//View Functionality
	List	viewHdr			=	new ArrayList();
	Map viewControl 		= 	new HashMap();
	
	//Header
	viewHdr.add("Job Name");
	viewHdr.add("Last Run Date ");
	viewHdr.add("Next Run Date ");
	viewHdr.add("Status ");

	
	String 	mode   = request.getParameter("hmode");
	
	if(mode == null || mode.equals("DEFAULT"))
	{
		%><jsp:forward page="<%=list_page%>"/>
		
		<%
	}	
	%>
	
	</head>
	</html>
