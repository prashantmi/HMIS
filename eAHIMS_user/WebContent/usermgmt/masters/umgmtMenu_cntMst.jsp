	
	
	<jsp:useBean id="menu" class="usermgmt.masters.UmgmtMenuMstBean" scope="request">
	<jsp:setProperty name="menu" property="*"/>
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
	String 	list_page 		= "umgmtMenu_lstMst.jsp";
	String 	add_page 		= "umgmtMenu_addMst.jsp";
	String 	view_page 		= "gbl_view_mst.jsp";
	String 	cnt_page 		= "umgmtMenu_cntMst.jsp";
	String 	result_page 	= "result.jsp";
	String 	mod_page 		= "umgmtMenu_addMst.jsp";
	String 	rpt_page 		= "gbl_print_mst.jsp";
	
	
	String hospitalCode = (String)session.getAttribute("HOSPITAL_CODE");
	String seatId = (String)session.getAttribute("SEAT_ID");	
	
	 menu.setSeat_id(seatId);
	 menu.setHospitalCode(hospitalCode);
	
	if(seatId == null)
	{		
		%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
		<%
	}

	
		
	
			String 	mode   	= request.getParameter("hmode");
			System.out.println("mode in menu master"+mode);
	
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
	            retValue = menu.insertRecord();
				if (retValue)
				{
					menu.initializeNewMode();
					%>
					<jsp:forward page="<%=result_page%>">
					<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
					<jsp:param name = "Name" value="Result Page >>"/>
					<jsp:param name = "mode" value="DEFAULT"/>
					<jsp:param name = "status" value="<%=menu.getStatus()%>"/>
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
					<jsp:param name = "status" value="<%=menu.getStatus()%>"/>
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
				AL_List = menu.initializeUpdateMode();
	            %><jsp:forward page = "<%=mod_page%>">
				  <jsp:param name = "hmode" value="UPDATE"/>
				  </jsp:forward>
				<%
			}
	
			if(mode.equals("UPDATE"))
			{
				if(CSRFTokenUtil.isValid(request))
				{	
				retValue = menu.updateRecord(request);
				if (retValue)
				{
				%>
					<jsp:forward page="<%=result_page%>">
					<jsp:param name = "mode" value="DEFAULT"/>
					<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
					<jsp:param name = "Name" value="Result Page >>"/>
					<jsp:param name = "status" value="<%=menu.getStatus()%>"/>
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
					<jsp:param name = "status" value="<%=menu.getStatus()%>"/>
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
				String strSelectedMenuId="";
				String strSelectedMenuLevel="";
				boolean deleteFlag=false;
				System.out.println("chk length value is"+menu.getChk().length);
				for(int i=0;i<menu.getChk().length;i++)
				{
					String[] arraymenu=menu.getChk()[i].replace("^", "#").split("#");
					strSelectedMenuId = arraymenu[0].substring(0,arraymenu[0].length());
				    strSelectedMenuLevel = arraymenu[1];
					System.out.println("after splitting menui id is "+strSelectedMenuId);
					System.out.println("after splitting menui level is "+strSelectedMenuLevel);
					if(strSelectedMenuLevel.equalsIgnoreCase("1"))
							{
							deleteFlag=true;
							break;
							}
					
							
				}
				
				if(deleteFlag)
					status="Delete action is prohibited for Level 1 Menus";
				else
			    status = menu.deleteRecord();
			
	    
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
				viewHdr.add("Menu Name");
				viewHdr.add("Menu Level");
				viewHdr.add("Display order");
				viewHdr.add("Url");
				viewHdr.add("Menu Class Id");
				viewHdr.add("Parent Name");
				viewHdr.add("HL7 CODE");    
	            viewHdr.add("Module Id");
	            viewHdr.add("Entry Date"); 
	            viewHdr.add("Menu Id"); 
				
	            //Control Type
				//viewControl.put("Generic Item Description","T");
				//viewControl.put("Composition","T");
	
				AL_List = menu.initializeUpdateMode();
				
				pageObj.getViewPage(AL_List,viewHdr,viewControl,"Menu Master");
	
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
				rptHeader.add("Level");
				rptHeader.add("Parent");
				rptHeader.add("Url");
	
	
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
				int totColumn = 4;
	
				//
				String qry = request.getParameter("query");
				//String[] cmbHdr = request.getParameterValues("header");
				String sFld 	= request.getParameter("cboSearch");
				String sVal 	= request.getParameter("txtSearch");
				String currentStatusValue	= menu.getCombo1();
				//calling prit function defined in page.java file
				pageObj.getPrintHtml("Menu Master",rptHeader, rptWidth,qry,
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