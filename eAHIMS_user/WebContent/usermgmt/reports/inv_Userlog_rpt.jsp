
<%@ page  import="java.sql.*,java.io.*,java.util.*,HisGlobal.*"%>
<jsp:useBean id="myUtil" class= "usermgmt.reports.inv_UserLogUtil" scope="request"/>
<jsp:useBean id="myForm" class= "usermgmt.reports.inv_UserLogActionForm" scope="request"/>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>



<script>

function cancelPage(e,mode)
{
	if(e.type=="click"||e.keyCode==13)
	{
		
		document.forms[0].hmode.value = mode;		
		document.forms[0].submit();
	}
}
function callMe(e)
{	
	if(e.type=="click"||e.keyCode==13)
	{
	window.print();
	}			
}


</script> 
 

		<%
		System.out.println("before rptList will be");
		List rptList=(ArrayList)request.getAttribute("rptList");
		System.out.println("rptList will be" +rptList);
		List lst = (List)request.getAttribute("systemDate");
		String date1 = (String) lst.get(0);
		String time1 = (String) lst.get(1);
		String fromDate=(String)request.getParameter("fromDate");
		String toDate=(String)request.getParameter("toDate");	

	
					
		
%>
	<html>
	<head>
	<title>UserLog Report Page </title> 

	<script language="JavaScript" src="../js/printPDF.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	</head>
	<body>
	
	
	<html:form action="/usermgmt/reports/user_log"  name="user_form" type="usermgmt.reports.inv_UserLogActionForm">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="69">
  <tr>
	<td colspan=3>
		<div id="id1" align="right">
			<img style="cursor:hand" src='../../images/print_tab.gif'   tabindex=1 onClick="callMe(event);" onKeyPress="callMe(event);">
			<img style="cursor:hand" src='../../images/cancel_tab.gif'  tabindex=1 onClick="cancelPage(event,'CANCEL');" >				
		</div>
	</td>
  </tr>
 </table>

	
	
	<table width='100%' border='0' cellspacing='0' cellpadding='0' height='159'>
    <thead style='DISPLAY:TABLE-HEADER-GRAP'> 
    
   </table>
   <table width='100%' border='0' cellspacing='0' cellpadding='0' height='159'>
   		
    	<tr> 
	      		<td colspan='12'> <div align='center'> <b>User Log Report </b> </div></td>
    	</tr>
    	
    	<tr>
		    	<td>
		    	</td>    	
    	</tr>
    	
		<tr>
				<td colspan='12' nowrap><div align='left'><b>Seat Name:-</b> &nbsp;&nbsp;&nbsp;<%=rptList.get(6)%></div></td>
		</tr>
		<tr>
			    <td width='15%' nowrap><b>User Name :-</b> &nbsp;&nbsp;&nbsp;<%=rptList.get(1)%></td>      	
		</tr>
		<tr>	 
			    <td></td>			     
			    <td  align='right'  class='adddatavalue' nowrap><b> &nbsp;&nbsp;&nbsp;From Date:</b>
			   	<%=fromDate%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>				
				<td nowrap class='adddatavalue'><b>To Date:</b>
				<%=toDate%>
				</td>
		</tr>

    	<tr> 
     			 <td colspan=12> <hr> </td>
    	</tr>
    	<tr> 
     			 <td height="22" colspan=12> <hr> </td>
    	</tr>
   		 <tr> 
			      <td width='10%'><font size="3"><b>S.No.</b></font>&nbsp;&nbsp;&nbsp;</td><!--
			      <td width='15%' nowrap><b><font size="2">Seat Name</font></b>&nbsp;&nbsp;&nbsp;</td>
			     
			      <td width='15%' nowrap><b><font size="3">UserID</font></b>&nbsp;&nbsp;&nbsp;</td>
			      <td width='15%' nowrap><b><font size="2">User Name</font></b>&nbsp;&nbsp;&nbsp;</td>      
			      -->
			      <td width='30%' align="center" nowrap><b><font size="3">LogIn Time</font></b>&nbsp;&nbsp;&nbsp;</td>
			      <td width='25%' align="center" nowrap><b><font size="3">Log Out Time</font></b>&nbsp;&nbsp;&nbsp;</td>
			      <td width='20%' align="center" nowrap><b><font size="3">IP Address</font></b>&nbsp;&nbsp;&nbsp;</td>
      
	    </tr>
		<tr> 
	      			<td colspan=12> <hr></td>
		</tr>
    <%
			
				int counter = 0;
				int sNo	=	1;
				System.out.println("rptList IS "+rptList);
				for(int index=0;index<rptList.size();index+=7)
				{	
					System.out.println("INSIDE FOR LOOP index :"+index);			
					/*String str1 = "";
					String str2 = "";
					String str	= rptList.get(index+2).toString();
					int length	= str.length();	
					
					System.out.println("str IS "+str);
					System.out.println("length IS "+length);	
					
					int a	= str.indexOf("^");				
					
					//str1= str.substring(0,a);
					//str2=str.substring(a+1,length);
					
					System.out.println("before html");*/
					
				 
			%>
		<tr> 
		      <td width='10%' ><%=sNo%></td>
		      <!--<td width='15%' nowrap><b><%=rptList.get(index+6)%></b></td>      
		      
		      <td width='15%' nowrap><%=rptList.get(index+0)%></td>     
		      <td width='15%' nowrap><b><%=rptList.get(index+1)%></b></td>
		      -->
		      ===============<%=rptList.get(index+2)%>
		      ++++++++++++++<%=rptList.get(index+3)%>
		      <td width='30%' align="center" nowrap><%=rptList.get(index+2)%></td>
		      <td width='30%' align="center" nowrap><%=rptList.get(index+3)%></td>
		      <td width='30%' align="center" nowrap><%=rptList.get(index+4)%></td>	  
      
	    </tr>
    <%		sNo++;
			counter++;
			}
			%>
			<tr> 
      		<td colspan=12> <hr> </td>
   		 </tr>
	
  
 
</table>	
<html:hidden property="hmode" name="user_form"/>
<html:hidden property="userid" name="user_form"/>  	

</html:form>
</body>				 
</html>

	
	
	
	
	
	