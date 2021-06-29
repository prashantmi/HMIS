	<jsp:useBean id="group" class="usermgmt.masters.UmgmtGroupMstBean" scope="request"/>
	<jsp:setProperty name="group" property="*"/>
	<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request"/>
	<%@page import="java.util.*"%>
		
	<%@page import="login.CSRFTokenUtil"%>
<%@ page autoFlush="true" buffer="1094kb"%>
<html>
	<head>
	<%
	String strHospitalCode = (String)session.getAttribute("HOSPITAL_CODE");	
group.setHospitalcode(strHospitalCode);
	
	//Declare Variable
	
	boolean retValue 		= true;
	List AL_List;
	String 	status      	= "";
	String 	list_page 		= "umgmtGroup_lstMst.jsp";
	String 	add_page 		= "umgmtGroup_addMst.jsp";
	String 	view_page 		= "gbl_view_mst.jsp";
	String 	cnt_page 		= "umgmtGroup_cntMst.jsp";
	String 	result_page 	= "result.jsp";
	String 	mod_page 		= "umgmtGroup_addMst.jsp";
	String rpt_page			= "gbl_print_mst.jsp";
	
	
String seatId = (String)session.getAttribute("SEAT_ID");	
	
	if(seatId == null)
	{		
		%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
		<%
	}
	group.setSeatId(seatId);
	
	//View Functionality
	List	viewHdr			=	new ArrayList();
	Map viewControl 		= 	new HashMap();
	
	//Header
	viewHdr.add("Group Name");
	viewHdr.add("Effective Date ");
	
	String 	mode   = request.getParameter("hmode");
	
	if(mode == null || mode.equals("DEFAULT"))
	{
		%><jsp:forward page="<%=list_page%>"/>
		
		<%
	}
	if(mode.equals("ADD"))
	{
		%><jsp:forward page="<%=add_page%>">
		<jsp:param name="hmode" value="SAVE" />
		</jsp:forward>
		
		<%
	}
	if(mode.equals("SAVE"))
	{
		if(CSRFTokenUtil.isValid(request))
		{
			retValue = group.insertRecord(request);	
			
			if (retValue)
			{
	    	
			group.initializeNewMode();
			System.out.println(group.getStatus());
			status="Record Successfully Inserted !";
			group.setStatus(status);
	     	%>	     	
	     	<jsp:forward page="<%=result_page%>">
	     	<jsp:param name="nextpage" value="<%=cnt_page%>"/>
	     	<jsp:param name="Name" value="Result >>" />
			<jsp:param name="mode" value="DEFAULT" />
			<jsp:param name="status" value="<%=group.getStatus()%>" />
			</jsp:forward>
		
	     	<%	
			}
		
			else
			{
			%><jsp:forward page="<%=result_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>"/>
			<jsp:param name="Name" value="Error Page >" />
			<jsp:param name="mode" value="DEFAULT" />
			<jsp:param name="status" value="<%=group.getStatus()%>" />
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
		AL_List = group.initializeUpdateMode();
		%><jsp:forward page="<%=mod_page%>">
		<jsp:param name="hmode" value="UPDATE" />
		</jsp:forward>
	<%
		}
	
		
	    if(mode.equals("UPDATE"))
		{
	    	if(CSRFTokenUtil.isValid(request))
			{
				retValue = group.updateRecord(request);
				if(retValue)
				{
		            status = "Record Successfully Updated !";
		            group.setStatus(status);
			     	%><jsp:forward page="<%=result_page %>">
			     	<jsp:param name="nextpage" value="<%=cnt_page%>"/>
				<jsp:param name="Name" value="Result Page >>" />
				<jsp:param name="mode" value="DEFAULT" />
				<jsp:param name="status" value="<%=status%>" />
			
				</jsp:forward><%	
				}
			  	else
			  	{
					  status=group.getStatus();
		      	     	
				%>
				<jsp:forward page="<%=result_page %>">

				<jsp:param name="nextpage" value="<%=cnt_page%>"/>
				<jsp:param name="Name" value="Result Page >>" />
				<jsp:param name="mode" value="DEFAULT" />
				<jsp:param name="status" value="<%=status%>" />
			
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

	    	boolean del=group.deleteRecord("0");
	        if(del)
	            status = "Record(s) Successfully Deleted";
	        else
	            status = "Error while Deleting Record(s)";    
	           
	        %>
	<jsp:forward page="<%=result_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
		<jsp:param name="status" value="<%=status%>" />
		<jsp:param name="mode" value="DEFAULT" />
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
			
			//define report header
			
			rptHeader.add("Group Name");
			rptHeader.add("Effective Date");
				
			
			//define corrosponding width(total width should be 95%)
			rptWidth.add("30%");
			rptWidth.add("30%");
			//definne column index 
			
			colIndex.add("1");
			colIndex.add("2");
			
	        //define total column defined in query
			int totColumn = 2;
			String qry = request.getParameter("query");
			
			
			//String[] cmbHdr = request.getParameterValues("header");
			String sFld 	= request.getParameter("cboSearch");
			String sVal 	= request.getParameter("txtSearch");
			String currentStatusValue	= group.getCombo1();
			
			//calling prit function defined in page.java file
			pageObj.getPrintHtml("Group Master",rptHeader, rptWidth,qry,cmbHeader,cmbValue,colIndex,totColumn,currentStatusValue);
			%>
		<jsp:forward page="<%=rpt_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
		<jsp:param name="cboSearch" value="<%=sFld%>" />
		<jsp:param name="txtSearch" value="<%=sVal%>" />
		</jsp:forward>
	
	<%
		}
		
		
	    if(mode.equals("VIEW"))
		{
	        AL_List = group.initializeUpdateMode();
			pageObj.getViewPage(AL_List,viewHdr,viewControl,"Group Master");
			
			String cmb1 = request.getParameter("combo1");
			String cmb2 = request.getParameter("combo2");
			String cmb3 = request.getParameter("combo3");
			String sFld = request.getParameter("cboSearch");
			String sVal = request.getParameter("txtSearch");
	 
		%>
	<jsp:forward page="<%=view_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
		<jsp:param name="combo1" value="<%=cmb1%>" />
		<jsp:param name="combo2" value="<%=cmb2%>" />
		<jsp:param name="combo3" value="<%=cmb3%>" />
		<jsp:param name="cboSearch" value="<%=sFld%>" />
		<jsp:param name="txtSearch" value="<%=sVal%>" />
	</jsp:forward>
	<%
		}
	
	%>
	
	</head>
	</html>
