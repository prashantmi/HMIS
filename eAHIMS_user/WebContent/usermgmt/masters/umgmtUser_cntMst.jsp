	<jsp:useBean id="user" class="usermgmt.masters.UmgmtUserMstBean" scope="request">
	
	<% 
	String hospCode= (String)session.getAttribute("HOSPITAL_CODE"); 
	System.out.println("hospital"+hospCode);
	user.setHospitalCode(hospCode);
	user.setPrevHospitalCode(hospCode);
	String seatId= (String)session.getAttribute("SEAT_ID"); 
	System.out.println("seatId"+seatId);
	user.setSeatId(seatId);

	
	%>
	
	<jsp:setProperty name="user" property="*" />
	</jsp:useBean>
	<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" />
	
	<%@ page import="java.util.*"%>
	<%@page import="login.CSRFTokenUtil"%>
	<html><head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
	<%		
	//Declare Variable
	boolean retValue 		= true;
	List AL_List;
	String 	status      	= "";
	String 	list_page 		= "umgmtUser_lstMst.jsp";
	String 	add_page 		= "umgmtUser_addMst.jsp";
	String 	view_page 		= "gbl_view_mst.jsp";
	String 	cnt_page 		= "umgmtUser_cntMst.jsp";
	String 	result_page 	= "result.jsp";
	String 	mod_page 		= "umgmtUser_addMst.jsp";
	String  rpt_page		= "gbl_print_mst.jsp";
	String 	popup_page 		= "addPopUp.jsp";
	
	//View Functionality  
	List	viewHdr			=	new ArrayList();
	Map viewControl 		= 	new HashMap();
	
	//Header
	viewHdr.add("Hospital Name");
	viewHdr.add("Employee Type");
	viewHdr.add("User ID");
	viewHdr.add("User Level");
	viewHdr.add("Group");
	viewHdr.add("Seat Associated");
	viewHdr.add("Effective Date");
	viewHdr.add("Expiry Date");
	viewHdr.add("Designation");
	viewHdr.add("User Name");
	viewHdr.add("Mobile No");
	viewHdr.add("Email Id");
	//viewHdr.add("Smart Card No");
	viewHdr.add("Gender Bound");
	viewHdr.add("Is Portal User");
	viewHdr.add("Is LDAP User");
	
	String 	mode         	= request.getParameter("hmode");
	  System.out.println("mode in controller---->"+mode);
	
	if(mode == null || mode.equals("DEFAULT"))
	{
		%><jsp:forward page="<%=list_page%>" /><%
	}
	
	if(mode.equals("ADD"))
	{
		user.initializeNewMode();%>
		<jsp:forward page="<%=add_page%>">
		<jsp:param name="Status" value="" />
		<jsp:param name="hmode" value="SAVE"/>
		</jsp:forward><%
	}
	
	if(mode.equals("MENU_ADD"))
	{
	    System.out.println("initally in controller---->"+user.getMyService());
	      System.out.println("initally in controller---->"+user.getEmp());
	    if( user.getMyService().equals("on"))
	      {
	     
	    	user.setMyService("1");
	    	 System.out.println("case 1 if checked---->"+user.getMyService());
		   }
		else
		if(user.getMyService().equals("0"))
		   {
		
		user.setMyService("2");
		   System.out.println("case 2 if unchecked--->"+user.getMyService());
		   }
		%>
		<jsp:forward page="<%=add_page%>">
		<jsp:param name="nextpage" value="<%=add_page%>" />
		<jsp:param name="Status" value="" />
		<jsp:param name="hmode" value="SAVE"/>
		</jsp:forward>
		<%
	}
	if(mode.equals("MENU_UPDATE"))
	{
	    
		System.out.println("initally in controller---->"+user.getMyService());
		System.out.println("initally in controller---->"+user.getEmp());
	    if( user.getMyService().equals("on"))
	      {
	     
	    	user.setMyService("1");
	    	 System.out.println("case 1 if checked---->"+user.getMyService());
		   }
		else
		if(user.getMyService().equals("0"))
		   {
		
		user.setMyService("2");
		   System.out.println("case 2 if unchecked--->"+user.getMyService());
		   } 
	     %>
	     <jsp:forward page="<%=mod_page%>">
		<jsp:param name="nextpage" value="<%=add_page%>" />
		<jsp:param name="Status" value="" />
		<jsp:param name="hmode" value="UPDATE"/>
		</jsp:forward>
	     <%
	}
		
	if(mode.equals("SAVE"))
	{
		if(CSRFTokenUtil.isValid(request))
		{
			retValue = user.insertRecord();
			if (retValue)
			{
			user.initializeNewMode();
			%>
			<jsp:forward page="<%=result_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="Name" value="Result Page >>" />
			<jsp:param name="mode" value="DEFAULT" />
			<jsp:param name="status" value="<%=user.getStatus()%>" />
			</jsp:forward><%	
		
			}
			else
			{
			%><jsp:forward page="<%=result_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="Name" value="Error Page >>" />
			<jsp:param name="mode" value="DEFAULT" />
			<jsp:param name="status" value="<%=user.getStatus()%>" />
			</jsp:forward><%	
			}
		}
		else
		{
			%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
			<%
		}
	}
	
	if(mode.equals("EDIT"))
	{
		
		AL_List = user.initializeUpdateMode();
			
		%>
		<jsp:forward page="<%=mod_page%>">
		<jsp:param name="hmode" value="UPDATE" />
		</jsp:forward>
		
		<%
	}
	if(mode.equals("EDIT_TEMP"))
	{
		%>
	    <jsp:forward page="<%=mod_page%>">
		<jsp:param name="hmode" value="UPDATE" />
		</jsp:forward>
		
	
	
		<%	
	}
	
	if(mode.equals("UPDATE"))
	{
		retValue = user.updateRecord(request);
		if (retValue)
		{
			%><jsp:forward page="<%=result_page%>">
			<jsp:param name="nextpage" value="<%=list_page%>" />
			<jsp:param name="Name" value="Result Page >>" />
			<jsp:param name="mode" value="UPDATE" />
			<jsp:param name="status" value="<%=user.getStatus()%>" />
			</jsp:forward><%
			
		}
		else
		{
			%><jsp:forward page="<%=result_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="Name" value="Error Page >>" />
			<jsp:param name="mode" value="DEFAULT" />
			<jsp:param name="status" value="<%=user.getStatus()%>" />
			</jsp:forward><%	
		}
	}
	
	if(mode.equals("DELETE"))
	{
	status = user.deleteRecord();

	%>
	<jsp:forward page="<%=result_page%>">
	<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
	<jsp:param name = "status" value="<%=status%>"/>
	<jsp:param name = "mode" value="DEFAULT"/>
	</jsp:forward>
		<%
	}
	if(mode.equals("REPORT"))
	{	
		List rptHeader	= new ArrayList();
		List rptWidth	= new ArrayList();
		List cmbHeader 	= new ArrayList();
		List cmbValue 	= new ArrayList();
		List colIndex 	= new ArrayList();
		
		rptHeader.add("User Name");
		rptHeader.add("Seat Name");
		rptHeader.add("Expiry Date");
		
		//define corrosponding width(total width should be 95%)
		rptWidth.add("25%");
		rptWidth.add("50%");
		rptWidth.add("20%");
		
			
		//definne column index 
		colIndex.add("1");
		colIndex.add("2");
		colIndex.add("3");
		
					
		//define total column defined in query
		int totColumn = 3;
		String qry = request.getParameter("query");
	//	String[] cmbHdr = request.getParameterValues("header");
		String sFld 	= request.getParameter("cboSearch");
		String sVal 	= request.getParameter("txtSearch");
		String currentStatusValue	= user.getCombo1();
		pageObj.getPrintHtml("User Master",rptHeader, rptWidth,qry,cmbHeader,cmbValue,colIndex,totColumn,currentStatusValue);
			
		%><jsp:forward page="<%=rpt_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
		<jsp:param name="cboSearch" value="<%=sFld%>" />
		<jsp:param name="txtSearch" value="<%=sVal%>" />
		</jsp:forward><%
	}
	
	if(mode.equals("VIEW"))
	{
		
		
		
		//String str=request.getParameter("combo1");
	    AL_List = user.initializeUpdateModeView();
		pageObj.getViewPage(AL_List,viewHdr,viewControl,"User Master");
		
		String cmb1 = request.getParameter("combo1");
		String cmb2 = request.getParameter("combo2");
		String cmb3 = request.getParameter("combo3");
		String sFld = request.getParameter("cboSearch");
		String sVal = request.getParameter("txtSearch");
		
		%><jsp:forward page="<%=view_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
		<jsp:param name="combo1" value="<%=cmb1%>" />
		<jsp:param name="combo2" value="<%=cmb2%>" />
		<jsp:param name="combo3" value="<%=cmb3%>" />
		<jsp:param name="cboSearch" value="<%=sFld%>" />
		<jsp:param name="txtSearch" value="<%=sVal%>" />
		</jsp:forward><%
	}%>
	
	</head>	</html>
