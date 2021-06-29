
<jsp:useBean id="user" class="usermgmt.masters.UmgmtUserSeatMstBean"
	scope="request">
	<jsp:setProperty name="user" property="*" />
</jsp:useBean>
<jsp:useBean id="pageObj" class="usermgmt.masters.Page" scope="request" />
<%@ page import="java.util.*"%><html>
<head>
<%
	String seatId = (String) session.getAttribute("SEATID");
	if (seatId == null) {
%>
<jsp:forward page="umgmtWarningPage.jsp" />
<%
	}
	user.setSeatId(seatId);

	//Declare Variable
	boolean retValue = true;
	List AL_List;
	String status = "";
	String list_page = "umgmtUserSeat_lstMst.jsp";
	String add_page = "umgmtUserSeat_addMst.jsp";
	String view_page = "gbl_view_mst.jsp";
	String cnt_page = "umgmtUserSeat_cntMst.jsp";
	String result_page = "result.jsp";
	String mod_page = "umgmtUserSeat_addMst.jsp";
	String rpt_page = "gbl_print_mst.jsp";

	//View Functionality  
	List viewHdr = new ArrayList();
	Map viewControl = new HashMap();

	//Header
	viewHdr.add("User Name");
	viewHdr.add("Employee Name");
	viewHdr.add("Designation");
	viewHdr.add("Seat Associated");
	viewHdr.add("Effective Date");
	viewHdr.add("Expiry Date");
	String mode = request.getParameter("hmode");

	if (mode == null || mode.equals("DEFAULT")) {
%><jsp:forward page="<%=list_page%>" />
<%
	}

	if (mode.equals("ADD")) {
		user.initializeNewMode();
%>
<jsp:forward page="<%=add_page%>">
	<jsp:param name="Status" value="" />
	<jsp:param name="hmode" value="SAVE" />
</jsp:forward>
<%
	}

	if (mode.equals("SAVE")) {
		retValue = user.insertRecord();
		if (retValue) {
			user.initializeNewMode();
%>
<jsp:forward page="<%=add_page%>" />
<%
} else {
%><jsp:forward page="<%=result_page%>">
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="Name" value="Error Page >>" />
	<jsp:param name="mode" value="DEFAULT" />
	<jsp:param name="status" value="<%=user.getStatus()%>" />
</jsp:forward>
<%
	}
	}

	if (mode.equals("EDIT")) {

		AL_List = user.initializeUpdateMode();

		//String uu=request.getParameter("userName");
%><jsp:forward page="<%=mod_page%>">
	<jsp:param name="hmode" value="UPDATE" />
</jsp:forward>
<%
	}

	if (mode.equals("UPDATE")) {
		retValue = user.updateRecord(request);
		if (retValue) {
%><jsp:forward page="<%=list_page%>" />
<%
} else {
%><jsp:forward page="<%=result_page%>">
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="Name" value="Error Page >>" />
	<jsp:param name="mode" value="DEFAULT" />
	<jsp:param name="status" value="<%=user.getStatus()%>" />
</jsp:forward>
<%
	}
	}

	if (mode.equals("DELETE")) {
		status = user.deleteRecord();
%>
<jsp:forward page="<%=result_page%>">
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="status" value="<%=status%>" />
	<jsp:param name="mode" value="DEFAULT" />
</jsp:forward>
<%
	}
	if (mode.equals("REPORT")) {
		List rptHeader = new ArrayList();
		List rptWidth = new ArrayList();
		List cmbHeader = new ArrayList();
		List cmbValue = new ArrayList();
		List colIndex = new ArrayList();

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
		String sFld = request.getParameter("cboSearch");
		String sVal = request.getParameter("txtSearch");
		String currentStatusValue = user.getCombo1();
		pageObj.getPrintHtml("User-Seat Master", rptHeader, rptWidth,
		qry, cmbHeader, cmbValue, colIndex, totColumn,
		currentStatusValue);
%><jsp:forward page="<%=rpt_page%>">
	<jsp:param name="nextpage" value="<%=cnt_page%>" />
	<jsp:param name="cboSearch" value="<%=sFld%>" />
	<jsp:param name="txtSearch" value="<%=sVal%>" />
</jsp:forward>
<%
	}

	if (mode.equals("VIEW")) {

		//String str=request.getParameter("combo1");
		AL_List = user.initializeUpdateMode();
		pageObj.getViewPage(AL_List, viewHdr, viewControl,
		"User-Seat Master");

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
</jsp:forward>
<%
}
%>

</head>
</html>
