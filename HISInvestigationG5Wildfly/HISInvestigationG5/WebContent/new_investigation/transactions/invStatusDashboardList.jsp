<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@page import="java.awt.BorderLayout"%>
<%@page import="java.awt.TextArea"%>
<%@page import="java.awt.Frame"%>
<%@page import="java.awt.Color"%>
<%@page import="javax.swing.JTextArea"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@page import="java.util.*"%>
<meta http-equiv="pragma" content="no-cache">
<!-- <meta http-equiv="refresh" content="30"/> -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@page import="new_investigation.vo.template.invStatusDashboardVO"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="new_investigation.InvestigationListingConfig"%>

<script src="scripts/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="scripts/js/jquery.dataTables.min.js" type="text/javascript"></script>
<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script>
$(document).ready( function () {
    $('#statusRecord2').DataTable();
} );
</script>
<body>
<table id = "statusRecord2" class="display">
<thead>
<tr>
	<th>Requisition No</th>
	<th>Cr Number</th>
	<th>Test Name</th>
	<th>Group Name</th>
	<th>Sample No</th>
	<th>Sample Type</th>
	<th>Machine Name</th>
	<th>Acceptance Date</th>
	<th>Validation Date</th>
	<th>Revalidation Date</th>
	
</tr>
</thead>
<tbody>
	<%
		List<invStatusDashboardVO> lst2=(List<invStatusDashboardVO>)session.getAttribute(InvestigationListingConfig.LIST_SAMPLE_DRILLDOWN_STATS);
		System.out.println("-=====Listing-"+lst2);
		int i2,size2=0;
		if(lst2!=null && lst2.size()>0 )
			size2=lst2.size();
			
			for(int j=0;j<lst2.size();j++)
			{
				invStatusDashboardVO voPat2=lst2.get(j);
				
				System.out.println("<%=voPat2.getCrno()"+voPat2.getCrno());
				
		%>
		
		<tr>
		
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getReqdno()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getCrno()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getTestname()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getGroupCode()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getSampleNo()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getSampleType()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getMachineName()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getAcceptDate()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getValDate()%></font>
			</td>
			<td width="15%" align="center">
				<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getReValDate()%></font>
			</td>
		</tr>	
		<%
			}	
		%>	
</tbody>	
</table>
</body>
</html>