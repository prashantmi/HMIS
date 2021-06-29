<%@page import="new_investigation.vo.template.invStatusDashboardVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/dataTables/ColumnFilterWidgets.css" rel="stylesheet" type="text/css" />
        
       	<script src="scripts/ColumnFilterWidgets.js" type="text/javascript"></script>
 		<script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
 		
 		<script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>  
    	<link href="media/dataTables/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
    	<link href="media/dataTables/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
   
    	<link href="media/dataTables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
    	<link href="media/themes/AdvancedView.css" rel="stylesheet" type="text/css" />
    	<script src="scripts/CustomizeDatatable.js" type="text/javascript"></script>
		<script type="text/javascript" src="/HISInvestigationG5/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HIS Investigation Desk</title>
</head>
<script>
function updateDateTime(){
	var date = new Date();
	var n = date.toDateString();
	var time = date.toLocaleTimeString();
	document.getElementById("dateTime").innerHTML = "Last updated on : " + n + " "+ time;
}

function getPendingSampleList(value){
	//1 : pending for sample collection,    //2:pending for packing list generation
	//3 : pending for sample acceptance,    //4:pending for result entry
	//5 : pending for machine result entry, //6:pending for result validation
	//7 : pending for result revalidation
/* 
	 document.getElementsByName('requestedType')[0].value=value;
	 document.getElementsByName('hmode')[0].value='GETSAMPLELIST';
	 document.forms[0].submit();
	 */
	 
	 var hmode="GETSAMPLELIST";
	 var url1="/HISInvestigationG5/new_investigation/invStatusDashboard.cnt";
	 mywindow=window.open (url1+"?requestedType="+value+"&hmode="+hmode,"_blank","scrollbars=1,directories=no, status=no,width=1000, height=500,top=200,left=500");


	 
	 
	 
}

</script>
<style>
#dateTime{
	color: green;
}
#marq{
	color: red;
}
</style>
<body onload="updateDateTime();">
<html:form action="/invStatusDashboard" enctype="multipart/form-data">
<html:hidden name="invStatusDashboardFB" property="hmode" />
<html:hidden name="invStatusDashboardFB" property="requestedType" />
		<div class="container">
		<table class="table table-bordered">
		<tr style="background-color: #809fff">
		<td align="center">Biochemistry Stats For Today</td>
		</tr>
		</table>
		<p id="dateTime">Last updated on : </p>	
		<table id = "statusRecord" class="table table-bordered">
		<%
			List<invStatusDashboardVO> lst1=(List<invStatusDashboardVO>)session.getAttribute(InvestigationConfig.LIST_STATUS_DASHBOARD_RECORD);
			System.out.println("-=====-"+lst1);
			int i1,size1=0;
			if(lst1!=null && lst1.size()>0 )
				size1=lst1.size();
				String grpCode1="";
				for(int j=0;j<lst1.size();j++)
				{
					invStatusDashboardVO voPat=lst1.get(j);
		%>
		<tbody>
		<tr style="background-color: #008000">
			<td width="15%" align="center">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">No. of Requisition Raised</font>
			</td>
			
			<td width="15%" align="center">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">No. of Test Raised</font>
			</td>
			
			<td width="10%" align="center" >
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">No. of Group Test Raised</font>
			</td>
		</tr>		
		<tr style="background-color: #00FFFF">	
			<td width="15%" align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getNo_of_req_raised()%></font>
			</td>
			
			<td width="15%" align="center">
			<font color="#000000"size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getNo_of_test_raised()%></font>
			</td>
			
			<td width="10%" align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> <%=voPat.getNo_of_group_test_raised()%></font>
			</td>
		</tr>
		
		<tr style="background-color: #008000">	
			<td width="15%" align="center" >
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Process Name</font>
			</td>
			
			<td width="15%" align="center" >
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Done</font>
			</td>
			
			<td width="15%" align="center" >
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Pending</font>
			</td>				
		</tr>
		<tr style="background-color: #d6ff99">
			<td width="15%" align="center" style="background-color: #008000">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Sample Collection</font>
			</td>
							
			<td width="12%" align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_done_with_sam_col()%> / Test : <%=voPat.getTest_done_with_sam_col()%></font>
			</td>
			
			<td width="11%" align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_pending_for_sam_col()%> / Test : <%=voPat.getTest_pending_for_sam_col()%></font>
			</td>
		</tr>
		
		<tr style="background-color: #d6ff99">
			<td width="15%" align="center" style="background-color: #008000">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Packing List generation</font>
			</td>
							
			<td width="12%" align="center" style="background-color: #ebffcc">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Test : <%=voPat.getTest_done_with_pkg_list_gen()%></font>
			</td>
			
			<td width="11%" align="center" style="background-color: #ebffcc">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<% if(!voPat.getTest_pending_for_pkg_list_gen().equals("0"))
			{ 
			%>
				<a style="color: black;"   href="javascript:getPendingSampleList(2);"> 
					Test : <%=voPat.getTest_pending_for_pkg_list_gen()%>
				</a>
			<%
			}else{
			%>
				Test : <%=voPat.getTest_pending_for_pkg_list_gen()%>
			<%
				}
			%>
			</font>
			</td>
		</tr>
		<tr style="background-color: #d6ff99">
			<td width="15%" align="center" style="background-color: #008000">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Sample Acceptance</font>
			</td>
							
			<td width="12%" align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_done_with_sam_acc()%> / Test : <%=voPat.getTest_done_with_sam_acc()%></font>
			</td>
			
			<td width="11%" align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_pending_for_sam_acc()%> / 
			<% if(!voPat.getTest_pending_for_sam_acc().equals("0"))
			   { 
			%>
				<a style="color: black;"   href="javascript:getPendingSampleList(3);">
					Test : <%=voPat.getTest_pending_for_sam_acc()%>
				</a>
			<%
			   }else{
			%>	
				Test : <%=voPat.getTest_pending_for_sam_acc()%>
			<% 
			   }
			%>
			</font>
			</td>
		</tr>
		
		<tr style="background-color: #d6ff99">
			<td width="15%" align="center" style="background-color: #008000">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Machine Result Entry</font>
			</td>
							
			<td width="12%" align="center" style="background-color: #ebffcc">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-</font>
			</td>
			
			<td width="11%" align="center" style="background-color: #ebffcc">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<%
				if(!voPat.getTest_pending_for_mach_prcs().equals("0"))
				{
			%>
			
			<a style="color: black;"   href="javascript:getPendingSampleList(5);">
				Test : <%=voPat.getTest_pending_for_mach_prcs()%>
			</a>
			<%
				}else
				{
			%>
				Test : <%=voPat.getTest_pending_for_mach_prcs()%>
			<%
				}
			%>
			</font>
			</td>
		</tr>
		
		<tr style="background-color: #d6ff99">
			<td width="15%" align="center" style="background-color: #008000">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Result Entry</font>
			</td>
							
			<td width="12%" align="center" style="background-color: #ebffcc">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_done_with_res_entry() %> / Test : <%=voPat.getTest_done_with_res_entry()%></font>
			</td>
			
			<td width="11%" align="center" style="background-color: #ebffcc">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_pending_for_res_entry()%> / 
			<% if(!voPat.getTest_pending_for_res_entry().equals("0")) 
			   {
			%>
				<a style="color: black;"   href="javascript:getPendingSampleList(4);">
					Test : <%=voPat.getTest_pending_for_res_entry()%>
				</a>
			<%
			   }else{
			%>
				Test : <%=voPat.getTest_pending_for_res_entry()%>
			<%
			   }
			%>
			</font>
			</td>
		</tr>
		
		<tr style="background-color: #d6ff99">
			<td width="15%" align="center" style="background-color: #008000">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Result Validation</font>
			</td>
							
			<td width="12%" align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_done_with_validation()%> / Test : <%=voPat.getTest_done_with_validation()%></font>
			</td>
			
			<td width="11%" align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_pending_for_validation()%> / 
			<%if(!voPat.getTest_pending_for_validation().equals("0"))
			  { 
			%>
				<a  style="color: black;"  href="javascript:getPendingSampleList(6);">
					Test : <%=voPat.getTest_pending_for_validation()%>
				</a>
			<%
			  }else{
			%>
				Test : <%=voPat.getTest_pending_for_validation()%>
			<%
			  }
			%>
			</font>
			</td>
		</tr>
		
		<tr style="background-color: #d6ff99">
			<td width="15%" align="center" style="background-color: #008000">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif">Result Re-Validation</font>
			</td>
							
			<td width="12%" align="center" style="background-color: #ebffcc">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_done_with_revalidation()%> / Test : <%=voPat.getTest_done_with_revalidation()%></font>
			</td>
			
			<td width="11%" align="center" style="background-color: #ebffcc">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Samples : <%=voPat.getReq_pending_for_revalidation()%> / 
			<%if(!voPat.getTest_pending_for_revalidation().equals("0"))
			  {
			%>
				<a style="color: black;"  href="javascript:getPendingSampleList(7);">
					Test : <%=voPat.getTest_pending_for_revalidation()%>
				</a>
			<%
			  }else{
			%>
				Test : <%=voPat.getTest_pending_for_revalidation()%>
			<%
			  }
			%>
			</font>
			</td>
		</tr>
			<%
				}  
			%>
	</table>
	<%-- 
	<logic:present name="<%=InvestigationListingConfig.LIST_SAMPLE_DRILLDOWN_STATS%>">

	<table id = "sampleList" class="table table-bordered">
	<thead>
		<th>Requisition Dno.</th>
		<th>Cr Number</th>
		<th>Test Name</th>
		<th>Sample No.</th>
		<th>Sample Type</th>		
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
		%>
		<tr style="background-color: #008000">
			<td width="15%" align="center">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getReqdno()%></font>
			</td>
			<td width="15%" align="center">
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getCrno()%></font>
			</td>
			<td width="10%" align="center" >
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getTestname()%></font>
			</td>
			<td width="10%" align="center" >
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getSampleNo()%></font>
			</td>
			<td width="10%" align="center" >
			<font color="#FFFFFF" size="2" face="Verdana, Arial, Helvetica, sans-serif"><%=voPat2.getSampleType()%></font>
			</td>
		</tr>
		<%
			}  
		%>
		</tbody>
	</table>
	</logic:present> --%>
	<marquee><p id="marq">This page is still under construction.</p></marquee>
		</div>
</html:form>
</body>
</html>