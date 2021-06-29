	<!--
 /***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2007-2008 
 ## Client's Name					: PGI
 ## Project Name					: HIMS
 ## Phase						: Development
 ## Name of Developer		 		: Mr. Nitin Vohra
 ## Module Name					: User Managment
 ## Date of creation					: 11/11/2008
 ## Purpose						: Controller Page
 ## Previous Form(Calling)				: Login page
 ## Functions Used					: insertRecord(),initializeNewMode(),deleteRecord(),initializeUpdateMode(),getViewPage(),updateRecord(),getPrintHtml()
 ## Name of Tables used for reference 	            : No
 ## Name of Tables used for data updation/insertion	: Dynamic
 ## Next Form	(Called)				: List Page,Modify page,View Page,Report Page Depending upon hmode
 ## Date of Modification				: No
 ## Unit Tested By	& Date				: 17/11/2008
 ## Comment	after Test				:
     1). All front end formats followed (Y/N)              :
 			if No then Detail   		:
     2). All functions working properly (Y/N)             :
 			if No then Detail   	            :
     3). Is there some Java Script Error (Y/N)              :
 			if YES then Detail   	                        :
     4). Connections/ recordsets used properly (Y/N)  :
 				if No then Detail   	:
     5). All Standard nomenclatures used (Y/N) 	:
 				if No then Detail	:
     6). Internal documentation done (Y/N)                 :
 				if No then Detail   	:
     7). Name of Objects Used		            	:
 		i). Object Name		 	:
 		ii) Purpose				:
 		iii). No. of times called		:
     8). Any suggestion					:
     9) Other Deviation					:
 ## Remark						:
 ## Finalization Date					:
 ## Future Alteration (1)				:
 ## Any major change
 	1) Reason					:
 	2) Time in days (Hour)			:
 	3) Change Raised By				:
 	4) Tested(Y/N)				:
 	5) Remark					:
 
-->   
	
	<jsp:useBean id="beanpage" class="usermgmt.masters.UmgmtGroupRoleMstBean" scope="request">
	</jsp:useBean>
	<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" />
	<jsp:setProperty name="beanpage" property="*" />
	<%@ page import="java.util.*"%>
	<%@page import="login.CSRFTokenUtil"%>
<%@ page autoFlush="true" buffer="1094kb"%>
	<html>

	<%
	String HospitalCode = (String)session.getAttribute("HOSPITAL_CODE");	
	System.out.println("HospitalCode"+HospitalCode);
	beanpage.setHOSPITAL_CODE(HospitalCode);
	
	 String seatId = (String)session.getAttribute("SEAT_ID");	
	
	
	
	if(seatId == null)
	{		
		%>
			<jsp:forward page="umgmtWarningPage.jsp"/>		
		<%
	}
		beanpage.setSeat_id(seatId);
	
	boolean retValue 		= true;
	List AL_List;
	
	String 	status      	= "";
	String 	list_page 		= "umgmtGroupRole_lstMst.jsp";
	String 	add_page 		= "umgmtGroupRole_addMst.jsp";
	String 	view_page 		= "gbl_view_mst.jsp";
	String 	cnt_page 		= "umgmtGroupRole_cntMst.jsp";
	String 	result_page 	= "result.jsp";
	String 	mod_page 		= "umgmtGroupRole_addMst.jsp";
	String 	rpt_page 		="gbl_print_mst.jsp";
	
	
//	View Functionality
	List	viewHdr			=	new ArrayList();
	Map viewControl 		= 	new HashMap();
	
	//Header
	viewHdr.add("Group Name");
	viewHdr.add("Role Name ");
	viewHdr.add("Module Name");
	//viewHdr.add("Effective Date");
	
	
	String 	mode = request.getParameter("hmode");
	
	System.out.println("mode in controller of Group role-----"+mode);
	
	if(mode == null || mode.equals("DEFAULT"))
	{
		%><jsp:forward page="<%=list_page%>" /><%
	}
	if(mode.equals("ADDPAGE"))
	{
		%><jsp:forward page="<%=add_page%>" /><%
	}
	if(mode.equals("ADD"))
	{
		beanpage.initializeNewMode();
		beanpage.setEffect_date(beanpage.getSysDate());
		
		%>
		<jsp:forward page="<%=add_page%>">
		<jsp:param name="Status" value=""/>
		<jsp:param name="hmode" value="SAVE"/>
		</jsp:forward><%
	}
	if(mode.equals("DELETE"))
	{
		boolean del=beanpage.deleteRecord("0");
        if(del)
            status = "Record(s) Successfully Deleted";
        else
            status = "Error while Deleting Record(s)";    
           
        %>
		<jsp:forward page="<%=result_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
		<jsp:param name="status" value="<%=status%>" />
		<jsp:param name="mode" value="DEFAULT" />
		</jsp:forward><%
	}
	if(mode.equals("SAVE"))
	{   
		if(CSRFTokenUtil.isValid(request))
		{		
		status = beanpage.insertRecord();%>
		<jsp:forward page="<%=result_page%>">
		<jsp:param name="nextpage" value="<%=cnt_page%>" />
		<jsp:param name="status" value="<%=status%>" />
		<jsp:param name="mode" value="DEFAULT" />
		</jsp:forward><%
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
		AL_List = beanpage.initializeUpdateMode();
		%><jsp:forward page = "<%=mod_page%>">
		<jsp:param name = "hmode" value="UPDATE"/>
		<jsp:param name="moduleId" value="<%=beanpage.getModule_id()%>"/>
        </jsp:forward><%
      }
	if(mode.equals("UPDATE"))
	{
		if(CSRFTokenUtil.isValid(request))
		{
			retValue = beanpage.updateRecord(request);
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
			%><jsp:forward page="<%=result_page%>">
			<jsp:param name = "mode" value="DEFAULT"/>
			<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
			<jsp:param name = "Name" value="Error Page >>"/>
			<jsp:param name = "status" value="<%=beanpage.getStatus()%>"/>
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
	
	if(mode.equals("VIEW"))
	{
		AL_List = beanpage.initializeUpdateMode();
		pageObj.getViewPage(AL_List,viewHdr,viewControl,"Item Type");
		
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
	}
	
	if(mode.equals("REPORT"))
	{
		List rptHeader	= new ArrayList();
		List rptWidth	= new ArrayList();
		List cmbHeader = new ArrayList();
		List cmbValue = new ArrayList();
		List colIndex = new ArrayList();


		//define report header
		rptHeader.add("Group Name");
		rptHeader.add("Role Name");
		rptHeader.add("Module Name");


		//define corrosponding width(total width should be 95%)
		rptWidth.add("35%");
		rptWidth.add("35%");
		rptWidth.add("30%");
		//definne column index
		colIndex.add("1");
		colIndex.add("2");
		colIndex.add("3");


		//define total column defined in query
		int totColumn = 3;

		//
		String qry = request.getParameter("query");
		//String[] cmbHdr = request.getParameterValues("header");
		String sFld 	= request.getParameter("cboSearch");
		String sVal 	= request.getParameter("txtSearch");
		String currentStatusValue	= beanpage.getCombo1();
		//calling prit function defined in page.java file
		pageObj.getPrintHtml("Group Role Master",rptHeader, rptWidth,qry,
		cmbHeader,cmbValue,colIndex,totColumn,currentStatusValue);

		%>
		<jsp:forward page="<%=rpt_page%>">
		<jsp:param name = "nextpage" value="<%=cnt_page%>"/>
		<jsp:param name = "cboSearch" value="<%=sFld%>"/>
		<jsp:param name = "txtSearch" value="<%=sVal%>"/>
		</jsp:forward>
		<%}%>
</html>
