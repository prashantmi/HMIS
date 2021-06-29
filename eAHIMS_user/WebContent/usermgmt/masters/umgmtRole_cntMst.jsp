
<jsp:useBean id="beanpage" class="usermgmt.masters.UmgmtRoleMstBean"
	scope="request">
</jsp:useBean>
<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" />

<jsp:setProperty name="beanpage" property="*" />
<%@ page import="java.util.*"%>
<%@page import="login.CSRFTokenUtil"%>
<%@ page autoFlush="true" buffer="1094kb"%>

<html>
<head>
<script>
	//window.history.forward() ;
	</script>
</head>
<%
String hospitalCode = (String)session.getAttribute("HOSPITAL_CODE");
String seatId = (String)session.getAttribute("SEAT_ID");	
if(seatId == null)
{		
	%>
		<jsp:forward page="umgmtWarningPage.jsp"/>		
	<%
}

	beanpage.setSeat_id(seatId);
	beanpage.setHospitalCode(hospitalCode);
	
	
boolean retValue 		= true;
List AL_List;

String 	status      	= "";
String 	list_page 		= "umgmtRole_lstMst.jsp";
String 	add_page 		= "umgmtRole_addMst.jsp";
String 	view_page 		= "gbl_view_mst.jsp";
String 	cnt_page 		= "umgmtRole_cntMst.jsp";
String 	result_page 	= "result.jsp";
String 	mod_page 		= "umgmtRole_modMst.jsp";
String 	rpt_page 		="gbl_print_mst.jsp";

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

viewHdr.add("Role Name");
viewHdr.add("Module Name");
//viewHdr.add("Role Type");
viewHdr.add("Effective Date");




String 	mode= request.getParameter("hmode");
System.out.println("mode in role----"+mode);

	if(mode == null || mode.equals("DEFAULT"))
	{
		%>
		<jsp:forward page="<%=list_page%>" />
<%
	}

	if(mode.equals("ADD"))
	{
		beanpage.initializeNewMode();

		%>
		<jsp:forward page="<%=add_page%>">
			<jsp:param name="Status" value=""/>
			<jsp:param name="hmode" value="SAVE"/>
		</jsp:forward>
<%
	}

	if(mode.equals("SAVE"))
	{
		if(CSRFTokenUtil.isValid(request))
		{	
			retValue = beanpage.insertRecord();
			if (retValue)
			{
			beanpage.initializeNewMode();
			status="Record Successfully Inserted !";
			beanpage.setStatus(status);
			%>
			<jsp:forward page="<%=result_page%>">
	     	<jsp:param name="nextpage" value="<%=cnt_page%>"/>
	     	<jsp:param name="Name" value="Result >>" />
			<jsp:param name="mode" value="DEFAULT" />
			<jsp:param name="status" value="<%=beanpage.getStatus()%>" />
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
			<jsp:param name="status" value="<%=beanpage.getStatus()%>" />
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
		System.out.println("inside edit mode"); 
		beanpage.initializeUpdateMode();

		%>
		<jsp:forward page="<%=mod_page%>">
			<jsp:param name="hmode" value="UPDATE" />
		</jsp:forward>
<%
	}


	if(mode.equals("UPDATE"))
	{
		if(CSRFTokenUtil.isValid(request))
		{
		retValue = beanpage.updateRecord(request);
		System.out.println("retValue = "+retValue);
		if (retValue)
		{
			 status = "Record Successfully Updated !";
			 beanpage.setStatus(status);
		%>
		<jsp:forward page="<%=result_page %>">
			     	<jsp:param name="nextpage" value="<%=cnt_page%>"/>
			<jsp:param name="Name" value="Result Page >>" />
			<jsp:param name="mode" value="DEFAULT" />
			<jsp:param name="status" value="<%=status%>" />
			
			</jsp:forward>
		<%	
		}
		else
		{
		%>
		<jsp:forward page="<%=result_page%>">
			<jsp:param name="mode" value="DEFAULT" />
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="Name" value="Error Page >>" />
			<jsp:param name="status" value="<%=beanpage.getStatus()%>" />
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
		status = beanpage.deleteRecord();
		%>
		<jsp:forward page="<%=result_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="status" value="<%=status%>" />
			<jsp:param name="mode" value="DEFAULT" />
		</jsp:forward>
<%
	}

	if(mode.equals("VIEW"))
	{
		AL_List = beanpage.initializeUpdateMode();
		pageObj.getViewPage(AL_List,viewHdr,viewControl,"Role Master");
		
		String sFld = request.getParameter("cboSearch");
		String sVal = request.getParameter("txtSearch");
 
		%>
		<jsp:forward page="<%=view_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="cboSearch" value="<%=sFld%>" />
			<jsp:param name="txtSearch" value="<%=sVal%>" />
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
		
		System.out.println("size of cmbValue--"+cmbValue.size()+"size of colIndex--"+colIndex.size());
		//define report header
		rptHeader.add("Role Name");
		rptHeader.add("Module Name");
		
		//define corrosponding width(total width should be 95%)
		rptWidth.add("45%");
		rptWidth.add("50%");
		
		//definne column index 
		colIndex.add("1");
		colIndex.add("2");
		
		//define total column defined in query
		int totColumn = 2;
		
		//
		String qry = request.getParameter("query");
		//String[] cmbHdr = request.getParameterValues("header");
		String sFld 	= request.getParameter("cboSearch");
		String sVal 	= request.getParameter("txtSearch");
		
		String currentStatusValue	= beanpage.getCombo1();
		
		//calling print function defined in page.java file
		
		pageObj.getPrintHtml("Role Master",rptHeader, rptWidth,qry, 
		cmbHeader,cmbValue,colIndex,totColumn,currentStatusValue);
		
		%>
		<jsp:forward page="<%=rpt_page%>">
			<jsp:param name="nextpage" value="<%=cnt_page%>" />
			<jsp:param name="cboSearch" value="<%=sFld%>" />
			<jsp:param name="txtSearch" value="<%=sVal%>" />
		</jsp:forward>
<%

	}

%>

</html>
