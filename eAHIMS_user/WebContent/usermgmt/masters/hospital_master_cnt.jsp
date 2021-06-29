<jsp:useBean id="hospitalMst" class="usermgmt.masters.HospitalConfigMasterBEAN" scope="request">
<jsp:setProperty name="hospitalMst" property="*"/>
</jsp:useBean>

<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request"/>

<%@ page import = "java.util.*" %>
<%@page import="login.CSRFTokenUtil"%>

<script>
	window.history.forward() ;
</script>
	
<html>
<head>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
String seatId = (String)session.getAttribute("SEAT_ID");	
if(seatId == null)
{		
	%>
		<jsp:forward page="umgmtWarningPage.jsp"/>		
	<%
}
hospitalMst.setSeatId(seatId);

//Declare Variable
boolean retValue 		= true;
List AL_List = new ArrayList();

String 	status      	= "";
String 	list_page 		= "hospital_master_list.jsp";
String 	add_page 		= "hospital_master_add.jsp";
String 	view_page 		= "gbl_view_mst.jsp";
String 	cnt_page 		= "hospital_master_cnt.jsp";
String 	result_page 	= "result.jsp";
String 	mod_page 		= "hospital_master_add.jsp";
String rpt_page			= "gbl_print_mst.jsp";

//View Functionality
List	viewHdr			=	new ArrayList();
Map viewControl 		= 	new HashMap();

//Header
viewHdr.add("Hospital Name");
viewHdr.add("Hospital Short Name");
viewHdr.add("User ID");
viewHdr.add("Hospital Address1");
viewHdr.add("Hospital Address2");
//viewHdr.add("City");
viewHdr.add("PIN Code");
viewHdr.add("District");
viewHdr.add("State");
viewHdr.add("Phone No");
viewHdr.add("Fax No");
viewHdr.add("Email Id");
viewHdr.add("Contact Person");
viewHdr.add("Hospital Type ");
viewHdr.add("Bus Route No.");
viewHdr.add("Bed Capacity");
viewHdr.add("WeekDay Timings");
viewHdr.add("Saturday Timings");
viewHdr.add("Lunch Break Timings");
//viewHdr.add("User Licenses Allowed");

//Control Type

 	

String 	mode   = request.getParameter("hmode");

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
	
	if(mode.equals("CMB"))
	{	 		
		%><jsp:forward page="<%=add_page%>">
		<jsp:param name = "hmode" value="SAVE"/>
		</jsp:forward>
		<%
	}
	
	if(mode.equals("CMBU"))
	{	 		
		%><jsp:forward page = "<%=mod_page%>">
		<jsp:param name = "hmode" value="UPDATE"/>
		</jsp:forward>
		<%
	}
	
	
	
	if(mode.equals("SAVE"))
	{
		
		// String hospcode=session.getAttribute("HOSPITAL_CODE").toString();
		
		if(CSRFTokenUtil.isValid(request))
		{
		retValue = hospitalMst.insertRecord();
		if (retValue)
		{
			hospitalMst.initializeNewMode();
			status="Record  Successfuly Inserted!";
			
	%>
			<jsp:forward page="<%=result_page%>">
			<jsp:param 	name = "nextpage" 	value="<%=cnt_page%>"/>
			<jsp:param 	name = "Name" 		value="Result Page >>"/>
			<jsp:param 	name = "mode" 		value="DEFAULT"/>
			<jsp:param 	name = "status" 	value="<%=status%>"/>
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
			<jsp:param 	name = "status" 	value="<%=hospitalMst.getStatus()%>"/>
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
		AL_List = hospitalMst.initializeUpdateMode();
		System.out.println("AL_List = "+AL_List);
		System.out.println("retValue = "+hospitalMst.getHospitalcode());
		hospitalMst.setHospitalcode(hospitalMst.getHospitalcode());
		//hospitalMst.setState(AL_List.get(9).toString());
	%>
			<jsp:forward page = "<%=mod_page%>">
			<jsp:param name = "hmode" value="UPDATE"/>
			</jsp:forward>			
	<%
	}

	if(mode.equals("UPDATE"))
	{
		if(CSRFTokenUtil.isValid(request))
		{
		retValue = hospitalMst.updateRecord(request);
		//System.out.println("retValue = "+hospitalMst.);
		if (retValue)
		{
			
	%>
			<jsp:forward page="<%=result_page%>">
			<jsp:param 	name = "nextpage" 	value="<%=cnt_page%>"/>
			<jsp:param 	name = "Name" 		value="Result Page >>"/>
			<jsp:param 	name = "mode" 		value="DEFAULT"/>
			<jsp:param 	name = "status" 	value="Record  Successfuly Updated !"/>
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
			<jsp:param name = "status" value="<%=hospitalMst.getStatus()%>"/>
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
		
		status = hospitalMst.deleteRecord();
		
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
		//System.out.println("VIEW MODE");
		AL_List = hospitalMst.initializeviewMode();
		pageObj.getViewPage(AL_List,viewHdr,viewControl,"Hospital Configuration");
		String cmb1 = request.getParameter("combo1");
		String cmb2 = request.getParameter("combo2");
		String cmb3 = request.getParameter("combo3");
		String sFld = request.getParameter("cboSearch");
		String sVal = request.getParameter("txtSearch");
 
	%>
			<jsp:forward page="<%=view_page%>">
			<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
			<jsp:param name = "combo1" value="<%=cmb1%>"/>
			<jsp:param name = "combo2" value="<%=cmb2%>"/>
			<jsp:param name = "combo3" value="<%=cmb3%>"/>
			<jsp:param name = "cboSearch" value="<%=sFld%>"/>
			<jsp:param name = "txtSearch" value="<%=sVal%>"/>
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
		rptHeader.add("Hospital Name");
		rptHeader.add("User Id");		
		//rptHeader.add("Effective Date");
			
		
		//define corrosponding width(total width should be 95%)
		rptWidth.add("50%");
		rptWidth.add("30%");
		
		
		
				
		
		//definne column index 
		
		colIndex.add("1");
		colIndex.add("2");
		
				
		
		//define total column defined in query
		int totColumn = 2;
		String qry = request.getParameter("query");
		String sFld 	= request.getParameter("cboSearch");
		String sVal 	= request.getParameter("txtSearch");
		String currentStatusValue	= hospitalMst.getCombo1();
			
			pageObj.getPrintHtml("Hospital Configuration",rptHeader, rptWidth,qry,cmbHeader,cmbValue,colIndex,totColumn,currentStatusValue);
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