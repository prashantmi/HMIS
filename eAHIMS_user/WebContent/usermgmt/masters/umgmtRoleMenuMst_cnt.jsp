<jsp:useBean id="beanObj" class="usermgmt.masters.umgmtRoleMenuBean_Mst" scope="request">
<jsp:setProperty name="beanObj" property="*"/>
</jsp:useBean>

<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request"/>

<%@ page import = "java.util.*" %>
<%@ page autoFlush="true" buffer="1094kb"%>
<html>
<head>



<%
//String strSeatId=(String)session.getAttribute("SEATID");
String strSeatId="10001";
String strHospitalCode = (String)session.getAttribute("HOSPITALCODE");	
System.out.println("strHospitalCode---->"+strHospitalCode);
if(strHospitalCode == null)
{		
	%>
		<jsp:forward page="umgmtWarningPage.jsp"/>		
	<%
}
beanObj.setHospital_code(strHospitalCode);
beanObj.setSeatId(strSeatId);

//Declare Variable
boolean retValue 		= true;
List AL_List;

String 	status      	= "";
String 	list_page 		= "umgmtRoleMenuMst_lst.jsp";
String 	add_page 		= "umgmtRoleMenuMst_add.jsp";
String 	view_page 		= "umgmtRoleMenuMst_view.jsp";
String 	cnt_page 		= "umgmtRoleMenuMst_cnt.jsp";
String 	result_page 	= "result.jsp";
String 	mod_page 		= "umgmtRoleMenuMst_mod.jsp";
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
viewHdr.add("Group Name");
viewHdr.add("Group Associated");
viewHdr.add("Effective Date");
//Control Type



String 	mode         	= request.getParameter("hmode");
System.out.println("mode in controller IS "+mode);


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
		
		retValue = beanObj.insertRecord();		
		
		
		if (retValue)
		{
			beanObj.initializeNewMode();
			
	%>
			<jsp:forward page="<%=result_page%>">
			<jsp:param 	name = "nextpage" 	value="<%=cnt_page%>"/>
			<jsp:param 	name = "Name" 		value="Result >>"/>
			<jsp:param 	name = "mode" 		value="DEFAULT"/>
			<jsp:param 	name = "status" 	value="<%=beanObj.getStatus()%>"/>
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
			<jsp:param 	name = "status" 	value="<%=beanObj.getStatus()%>"/>
			</jsp:forward>
	<%	
		}
	}
	if(mode.equals("TEMP"))
	{
		System.out.println("Module in controller is :"+request.getParameter("module"));
		
	%>
		<jsp:forward page = "<%=add_page%>"/>
	<%
	}
	if(mode.equals("TEMP_PERSIST"))
	{		
	%>
		<jsp:forward page = "<%=add_page%>"/>
	<%
	}
	if(mode.equals("TEMP_MODIFY"))
	{
		
	%>
		<jsp:forward page = "<%=mod_page%>"/>
	<%
	}
	if(mode.equals("EDIT"))
	{

		
		
		
		AL_List = beanObj.initializeUpdateMode();
	%>
			<jsp:forward page = "<%=mod_page%>">
			<jsp:param name = "hmode" value="UPDATE"/>
			</jsp:forward>			
	<%
	}
	if(mode.equals("UPDATE"))
	{
		retValue = beanObj.updateRecord(request);
		if (retValue)
		{
			
	%>
			<jsp:forward page="<%=result_page%>">
			<jsp:param 	name = "nextpage" 	value="<%=cnt_page%>"/>
			<jsp:param 	name = "Name" 		value="Result >>"/>
			<jsp:param 	name = "mode" 		value="DEFAULT"/>
			<jsp:param 	name = "status" 	value="<%=beanObj.getStatus()%>"/>
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
			<jsp:param name = "status" value="<%=beanObj.getStatus()%>"/>
			
			</jsp:forward>
	<%	
		}
	}
	if(mode.equals("DELETE"))
	{
		status = beanObj.deleteRecord();
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
		AL_List = beanObj.initializeUpdateMode();
		%>
		<jsp:forward page="<%=view_page%>"/>		

		<%		
	}
	if(mode.equals("TEMP_VIEW"))
	{
		//AL_List = beanObj.initializeUpdateMode();
		%>
		<jsp:forward page="<%=view_page%>"/>		

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
			rptHeader.add("Role Date");
			rptHeader.add("Menu Name");
				
			
			//define corrosponding width(total width should be 95%)
			rptWidth.add("30%");
			rptWidth.add("30%");
			rptWidth.add("30%");
			//definne column index 
			
			colIndex.add("1");
			colIndex.add("2");
			colIndex.add("3");
			
	        //define total column defined in query
			int totColumn = 3;
			String qry = request.getParameter("query");
			System.out.println("qry_report"+qry);
			
			//String[] cmbHdr = request.getParameterValues("header");
			String sFld 	= request.getParameter("cboSearch");
			String sVal 	= request.getParameter("txtSearch");
			String currentStatusValue	="1";// group.getCombo1();
			
			//calling prit function defined in page.java file
			pageObj.getPrintHtml("Role Menu Master",rptHeader, rptWidth,qry,cmbHeader,cmbValue,colIndex,totColumn,currentStatusValue);
			%>
		<jsp:forward page="<%=rpt_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
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