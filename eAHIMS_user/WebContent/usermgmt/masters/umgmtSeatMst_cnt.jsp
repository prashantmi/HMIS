<jsp:useBean id="seat" class="usermgmt.masters.umgmtSeatMasterBean" scope="request">
<jsp:setProperty name="seat" property="*"/>
</jsp:useBean>

<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request"/>

<%@ page import = "java.util.*" %>
<%@page import="login.CSRFTokenUtil"%>
<%@ page autoFlush="true" buffer="1094kb"%>

	
<html>
<head>


<%
String strHospitalCode = (String)session.getAttribute("HOSPITAL_CODE");	
seat.setHospCode(strHospitalCode);

System.out.println("strHospitalCode   is "+strHospitalCode );
String seatId = (String)session.getAttribute("SEAT_ID");	
if(seatId == null)
{		
	%>
		<jsp:forward page="umgmtWarningPage.jsp"/>		
	<%
}
	seat.setSeatId(seatId);

//Declare Variable
boolean retValue 		= true;
List AL_List = new ArrayList();

String 	status      	= "";
String 	list_page 		= "umgmtSeatMst_lst.jsp";
String 	add_page 		= "umgmtSeatMst_add.jsp";
String 	view_page 		= "umgmtSeat_viewMst.jsp";
String 	cnt_page 		= "umgmtSeatMst_cnt.jsp";
String 	result_page 	= "result.jsp";
String 	mod_page 		= "umgmtSeatMst_add.jsp";
String rpt_page			= "gbl_print_mst.jsp";

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
viewHdr.add("Seat Name");
viewHdr.add("Seat Description");
viewHdr.add("Effective Date");
viewHdr.add("Group Associate");
viewHdr.add("HL-7 Code");
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
	
	if(mode.equals("SAVE"))
	{
		if(CSRFTokenUtil.isValid(request))
		{
		
		String hospcode=session.getAttribute("HOSPITAL_CODE").toString();
		seat.setHospCode(hospcode);
		
    			
			retValue = seat.insertRecord();
			if (retValue)
			{
			seat.initializeNewMode();
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
			<jsp:param 	name = "status" 	value="<%=seat.getStatus()%>"/>
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
		
		String hospcode=session.getAttribute("HOSPITAL_CODE").toString();
		seat.setHospCode(hospcode);
		AL_List = seat.initializeUpdateMode();
	%>
			<jsp:forward page = "<%=mod_page%>">
			<jsp:param name = "hmode" value="UPDATE"/>
			</jsp:forward>			
	<%
	}

	if(mode.equals("UPDATE"))
	{
		String hospcode=session.getAttribute("HOSPITAL_CODE").toString();
		seat.setHospCode(hospcode);
		if(CSRFTokenUtil.isValid(request))
		{
			retValue = seat.updateRecord(request);
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
			<jsp:param name = "status" value="<%=seat.getStatus()%>"/>
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
		
		status = seat.deleteRecord();
		
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
		AL_List = seat.initializeUpdateModeView();
		pageObj.getViewPage(AL_List,viewHdr,viewControl,"Seat Master");
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
		rptHeader.add("Seat Name");
		rptHeader.add("Group Associated");		
		rptHeader.add("Effective Date");
			
		
		//define corrosponding width(total width should be 95%)
		rptWidth.add("30%");
		rptWidth.add("30%");
		rptWidth.add("40%");
		
		
		
				
		
		//definne column index 
		
		colIndex.add("1");
		colIndex.add("2");
		colIndex.add("3");
		
				
		
		//define total column defined in query
		int totColumn = 3;
		String qry = request.getParameter("query");
		//System.out.println("query = " + qry);
		//String[] cmbHdr = request.getParameterValues("header");
		String sFld 	= request.getParameter("cboSearch");
		String sVal 	= request.getParameter("txtSearch");
		String currentStatusValue	= seat.getCombo1();
		//System.out.println("query = " + qry);
		//calling prit function defined in page.java file
		pageObj.getPrintHtml("Seat Master",rptHeader, rptWidth,qry, 
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