	<jsp:useBean id="bean" class="usermgmt.masters.UmgmtSeatMenuMstBean" scope="request"/>
	<jsp:setProperty name="bean" property="*" />
	
	<%
	String menulist=request.getParameter("mList1");
	String seatId = (String)session.getAttribute("SEATID");	
	
	boolean retValue 		= true;
	String 	add_page 		= "umgmtSeatMenu_addMst.jsp";
	String 	cnt_page 		= "umgmtSeatMenu_cntMst.jsp";
	String 	result_page 	= "result.jsp";
	String rpt_page			= "gbl_print_mst.jsp";
	
	String 	mode   = request.getParameter("hmode");
	
	if(mode == null || mode.equals("DEFAULT"))
	{
		%><jsp:forward page="<%=add_page%>" /><%
	}
	else if(mode.equals("ADDPAGE"))
	{
		%><jsp:forward page="<%=add_page%>" /><%
	}
	else if(mode.equals("SAVE"))
	{
		bean.setMenuList(menulist);
		retValue = bean.insertRecord();
		if (retValue)
		{
			bean.initializeNewMode();
			%><jsp:forward page="<%=add_page%>"/><%	
		}
	}%>
		
	
	
