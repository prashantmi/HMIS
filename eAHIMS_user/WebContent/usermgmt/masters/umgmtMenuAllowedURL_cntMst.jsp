	
	
	<jsp:useBean id="menuAllowed" class="usermgmt.masters.UmgmtMenuAllowedURL_MstBean" scope="request">
	<jsp:setProperty name="menuAllowed" property="*"/>
	</jsp:useBean>
	<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" >
	</jsp:useBean>
	<%@page import="java.util.*"%>
	<%@page import="login.CSRFTokenUtil"%>
<%@ page autoFlush="true" buffer="1094kb"%>
	
	<html>
	<head>
	<script>
	window.history.forward() ;
	</script>
	<%
	boolean retValue 		= true;
	List AL_List;
	
	String 	status      	= "";
	String 	list_page 		= "umgmtMenuAllowedURL_lstMst.jsp";
	String 	add_page 		= "umgmtMenuAllowedURL_addMst.jsp";
	String 	view_page 		= "gbl_view_mst.jsp";
	String 	cnt_page 		= "umgmtMenuAllowedURL_cntMst.jsp";
	String 	result_page 	= "result.jsp";
	String 	mod_page 		= "umgmtMenuAllowedURL_addMst.jsp";
	String 	rpt_page 		= "gbl_print_mst.jsp";
	
	
	String hospitalCode = (String)session.getAttribute("HOSPITAL_CODE");
	String seatId = (String)session.getAttribute("SEAT_ID");	
	
	 menuAllowed.setSeat_id(seatId);
	 menuAllowed.setHospitalCode(hospitalCode);
	
	if(seatId == null)
	{		
		%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
		<%
	}

	
		
	
			String 	mode   	= request.getParameter("hmode");
			System.out.println("mode in menuAllowed master"+mode);
	
			if(mode == null || mode.equals("DEFAULT"))
			{
				%><jsp:forward page = "<%=list_page%>"/><%
			}
	
		 	if(mode.equals("CMB"))
			{	 		
				%><jsp:forward page="<%=add_page%>">
				<jsp:param name = "Status" value=""/>
				<jsp:param name = "hmode" value="SAVE"/>
				</jsp:forward>
				<%
			}
	
			if(mode.equals("ADD"))
			{
	        	
	        	%><jsp:forward page="<%=add_page%>">
				<jsp:param name = "Status" value=""/>
				<jsp:param name = "hmode" value="SAVE"/>
				</jsp:forward>
				<%
			}
	
	  	    if(mode.equals("SAVE"))
			{
	  	    	if(CSRFTokenUtil.isValid(request))
	  			{	
	            retValue = menuAllowed.insertRecord();
				if (retValue)
				{
					menuAllowed.initializeNewMode();
					%>
					<jsp:forward page="<%=result_page%>">
					<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
					<jsp:param name = "Name" value="Result Page >>"/>
					<jsp:param name = "mode" value="DEFAULT"/>
					<jsp:param name = "status" value="<%=menuAllowed.getStatus()%>"/>
					</jsp:forward>
				<%
				}
				else
				{
				%>
					<jsp:forward page="<%=result_page%>">
					<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
					<jsp:param name = "Name" value="Error Page >>"/>
					<jsp:param name = "mode" value="DEFAULT"/>
					<jsp:param name = "status" value="<%=menuAllowed.getStatus()%>"/>
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
	
			if(mode.equals("EDIT"))
			{
				AL_List = menuAllowed.initializeUpdateMode();
	            %><jsp:forward page = "<%=mod_page%>">
				  <jsp:param name = "hmode" value="UPDATE"/>
				  </jsp:forward>
				<%
			}
	
			if(mode.equals("UPDATE"))
			{
				if(CSRFTokenUtil.isValid(request))
				{	
				retValue = menuAllowed.updateRecord(request);
				if (retValue)
				{
				%>
					<jsp:forward page="<%=result_page%>">
					<jsp:param name = "mode" value="DEFAULT"/>
					<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
					<jsp:param name = "Name" value="Result Page >>"/>
					<jsp:param name = "status" value="<%=menuAllowed.getStatus()%>"/>
					</jsp:forward>
				<%
				}
				else
				{
				%>
					<jsp:forward page="<%=result_page%>">
					<jsp:param name = "mode" value="DEFAULT"/>
					<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
					<jsp:param name = "Name" value="Error Page >>"/>
					<jsp:param name = "status" value="<%=menuAllowed.getStatus()%>"/>
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
			status = menuAllowed.deleteRecord();
	
			%>
			<jsp:forward page="<%=result_page%>">
			<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
			<jsp:param name = "status" value="<%=status%>"/>
			<jsp:param name = "mode" value="DEFAULT"/>
			</jsp:forward>
				<%
			}
	
			if(mode.equals("VIEW"))
			{
				//View Functionality
				List	viewHdr			=	new ArrayList();
				Map viewControl 		= 	new HashMap();
	
				//Header
				/* viewHdr.add("Menu Name");
				viewHdr.add("Menu Level");
				viewHdr.add("Display order");
				viewHdr.add("Url");
				viewHdr.add("Menu Class Id");
				viewHdr.add("Parent Name");
				viewHdr.add("HL7 CODE");    
	            viewHdr.add("Module Id");
	            viewHdr.add("Entry Date"); 
	            viewHdr.add("Menu Id");  */
	            
	            viewHdr.add("Menu Id"); 
	            viewHdr.add("Menu Name");
				viewHdr.add("Url");
			    viewHdr.add("Module Id");
				viewHdr.add("Parent Name");
			    viewHdr.add("Entry Date"); 
	         
			
	            //Control Type
				//viewControl.put("Generic Item Description","T");
				//viewControl.put("Composition","T");
	
				AL_List = menuAllowed.initializeUpdateMode();
				
				pageObj.getViewPage(AL_List,viewHdr,viewControl,"Menu Allowed URL Master");
	
				String sFld = request.getParameter("cboSearch");
				String sVal = request.getParameter("txtSearch");
				%>
				<jsp:forward page="<%=view_page%>">
				<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
				<jsp:param name = "cboSearch" value="<%=sFld%>"/>
				<jsp:param name = "txtSearch" value="<%=sVal%>"/>
				</jsp:forward>
				<%
			}
	
		   if(mode.equals("REPORT"))
			{
				List rptHeader	= new ArrayList();
				List rptWidth	= new ArrayList();
				List cmbHeader = new ArrayList();
				List cmbValue = new ArrayList();
				List colIndex = new ArrayList();
	
	
				//define report header
				rptHeader.add("Menu Name");
				//rptHeader.add("Level");
				//rptHeader.add("Parent");
				rptHeader.add("Allowed Url");
				rptHeader.add("Module Name");
	
	
				//define corrosponding width(total width should be 95%)
				rptWidth.add("40%");
				rptWidth.add("10%");
				rptWidth.add("25%");
				rptWidth.add("25%");
				//definne column index
				colIndex.add("1");
				colIndex.add("2");
				colIndex.add("3");
				colIndex.add("4");
	
				//define total column defined in query
				int totColumn = 3;
	
				//
				String qry = request.getParameter("query");
				//String[] cmbHdr = request.getParameterValues("header");
				String sFld 	= request.getParameter("cboSearch");
				String sVal 	= request.getParameter("txtSearch");
				String currentStatusValue	= menuAllowed.getCombo1();
				//calling prit function defined in page.java file
				pageObj.getPrintHtml("Menu Allowed URL Master",rptHeader, rptWidth,qry,
				cmbHeader,cmbValue,colIndex,totColumn,currentStatusValue);
	
				%>
				<jsp:forward page="<%=rpt_page%>">
				<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
				<jsp:param name = "cboSearch" value="<%=sFld%>"/>
				<jsp:param name = "txtSearch" value="<%=sVal%>"/>
				</jsp:forward>
				<%
	
			}
	
		%>
	
		</head>
		<body>
		</body>
	</html>