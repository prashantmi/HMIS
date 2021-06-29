<jsp:useBean id="meta" class="usermgmt.masters.gblMetaTableMstBean" scope="request">
	<jsp:setProperty name="meta" property="*" />
</jsp:useBean>
<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" />
<%@ page import="java.util.*"%>
<html>
<head>
<script>
	window.history.forward() ;
	</script>
<%
String seatId = (String)session.getAttribute("SEAT_ID");
String hospitalCode = (String)session.getAttribute("HOSPITAL_CODE");	

    meta.setSeatId(seatId);
	meta.setHospitalCode(hospitalCode);


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
String 	list_page 		= "gblMetaTable_lstMst.jsp";
String 	add_page 		= "gblMetaTable_addMst.jsp";
String 	view_page 		= "gbl_view_mst.jsp";
String 	cnt_page 		= "gblMetaTable_cntMst.jsp";
String 	result_page 	= "result.jsp";
String 	mod_page 		= "gblMetaTable_addMst.jsp";
String rpt_page			= "gbl_print_mst.jsp";

//View Functionality
List	viewHdr			=	new ArrayList();
Map viewControl 		= 	new HashMap();

//Header
viewHdr.add("Module Id");
viewHdr.add("Module Name");
viewHdr.add("Seat Id");
//Control Type



String 	mode   = request.getParameter("hmode");

    if(mode == null || mode.equals("DEFAULT"))
    {
        %>
<jsp:forward page="<%=list_page%>" />
<%
    }

	if(mode.equals("ADD"))
	{
	    %>
<jsp:forward page="<%=add_page%>">
	<jsp:param name="Status" value=""/>
		<jsp:param name="hmode" value="SAVE"/>
</jsp:forward>
<%
	}
	
	if(mode.equals("SAVE"))
	{
		retValue = meta.insertRecord();
		if (retValue)
		{
           meta.initializeNewMode();
			%>
<jsp:forward page="<%=result_page%>">
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="Name" value="Result >>" />
	<jsp:param name="mode" value="DEFAULT" />
	<jsp:param name="status" value="<%=meta.getStatus()%>" />
</jsp:forward>
<%	
		}
		else
		{
		%>
			
<jsp:forward page="<%=result_page%>">
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="Name" value="Error Page >>" />
	<jsp:param name="mode" value="DEFAULT" />
	<jsp:param name="status" value="<%=meta.getStatus()%>" />
</jsp:forward>
<%	
		
		}
	}

	if(mode.equals("EDIT"))
	{
		AL_List = meta.initializeUpdateMode();
	    %>
<jsp:forward page="<%=mod_page%>">
	<jsp:param name="hmode" value="UPDATE" />
</jsp:forward>
<%
	}

	 if(mode.equals("UPDATE"))
	{
		retValue = meta.updateRecord(request);
		if (retValue)
		{
		%>
<jsp:forward page="<%=result_page%>">
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="Name" value="Result >>" />
	<jsp:param name="mode" value="DEFAULT" />
	<jsp:param name="status" value="<%=meta.getStatus()%>" />
</jsp:forward>
<%	
		}
		else
		{
		%>
<jsp:forward page="<%=result_page%>">
	<jsp:param name="mode" value="DEFAULT" />
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="Name" value="Error Page >>"/>
	<jsp:param name="status" value="<%=meta.getStatus()%>" />
</jsp:forward>
<%	
		}
	
	
	}
	
	 if(mode.equals("DELETE"))
	{
		String state = meta.deleteRecord();
       
        
        %>
<jsp:forward page="<%=result_page%>">
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="status" value="<%=state%>" />
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
		 rptHeader.add("Module Name");
		rptHeader.add("Module Id");
       
		
			
		
		//define corrosponding width(total width should be 95%)
		rptWidth.add("30%");
		rptWidth.add("30%");
		//definne column index 
		
		colIndex.add("1");
		colIndex.add("2");
		
        //define total column defined in query
		int totColumn = 2;
		String qry = request.getParameter("query");
	//	String[] cmbHdr = request.getParameterValues("header");
		String sFld 	= request.getParameter("cboSearch");
		String sVal 	= request.getParameter("txtSearch");
		String currentStatusValue	= meta.getCombo1();
		//calling prit function defined in page.java file
		pageObj.getPrintHtml("Module Master",rptHeader, rptWidth,qry, 
		cmbHeader,cmbValue,colIndex,totColumn,currentStatusValue);
		
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
		AL_List = meta.initializeUpdateMode();
		pageObj.getViewPage(AL_List,viewHdr,viewControl,"Module Master");
		
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
<body>
</body>
</html>
