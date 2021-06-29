
<jsp:useBean id="conpool" class= "HisGlobal.HisMethods" scope="request"/>
<jsp:useBean id="myUtil" class= "usermgmt.reports.inv_Adminactivity_Util" scope="request"/>
<%@page import="java.util.*" %>



<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>

<%System.out.println("testing statring");
               
				String thmode = (String)request.getAttribute("hmode");
				System.out.println("thmode"+thmode);
				String fromDate = (String)request.getAttribute("fromDate");				
				if(fromDate==null || fromDate.equals(""))
				{
					fromDate = new usermgmt.FuncLib().getSysdate();		
				
				}				
				String toDate = (String)request.getAttribute("toDate");	
				if(toDate==null || toDate.equals(""))
				{
					toDate = new usermgmt.FuncLib().getSysdate();	
				}
						
				if(thmode==null)
				thmode="";	
		%>

<HTML>
	<HEAD>
		<TITLE>
			 Audit Log Report
		</TITLE>
		
<script>
function submitPage(e,mode)
	{
	
	   				
		if(document.getElementsByName("mod")[0].value=="-1")
		{
			alert("Please select Module Name");
			return false;
		}
		else if(document.getElementsByName("fromDate")[0].value=="-1"||document.getElementsByName("fromDate")[0].value.length==0)

            {
                  alert("Please select From Date");
                  return false;
             }
        else if(CompareDates()==false)
             {
               document.getElementById("fromDate")[0].select();
                return false;
           	}
		
	  else
		{	
			document.forms[0].hmode.value = mode;		
			document.forms[0].submit();
		}
		
	}
	



function submitMode(mode)
{		
	document.forms[0].hmode.value = mode;
	document.forms[0].submit();
}

function search(path, k)
		{	
			/*alert("inside search");	
			alert("path"+path);
			alert("k"+k);
			alert("value"+document.forms[0].filename[k].value);*/
			
          var policy_no_value=document.forms[0].filename[k].value;
         // alert("policy_no_value"+policy_no_value);
			var url = "inv_adminactivityrpt_cpy.jsp";
			 url += "?fullpath="+path+'*'+policy_no_value;
			//alert("url"+url);		
			child = window.open(url,'popupWindow',"status=yes,scrollbars=yes,height=450,width=780,left=10,top=10");			
		}



	
	</script>
<script language="JavaScript" src="../js/calendar.js"></script>
<script language="JavaScript" src="../js/ValidateDate.js"></script>
		
	


	<style type="text/css">@import url(../../css/calendar-blue2.css);</style>


	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
	<link href="../../css/Color.css" rel="stylesheet" type="text/css">
	<link href="../../css/master.css" rel="stylesheet" type="text/css">
	</head>
	<body bgcolor="#FFFFFF" text="#000000">
	<html:form action="/usermgmt/reports/admin_action"  name="admin_new" type="usermgmt.reports.inv_Adminactivity_ActionForm">	
	
	<table width="100%" border="0">
		
		
		<tr> 
		<td class="header" colspan="4">AuditLog Report</td>
		</tr>		
		
		
		<tr>
			<td class='tdfonthead' width='25%' >
			<div align='right'>
			Module Name
			</div>
			</td>		
			<td class='tdfont' width='25%'>		
			<html:select property="mod"  name="admin_new"  >		 
			<html:options collection="modList" property="optionValue" labelProperty="optionText" />			
			</html:select> 
			</td>
			<td class="tdfonthead" width='25%'></td>
						<td class="tdfont" width='25%'>
						<img style='cursor:hand' src='../../images/go.gif' 	tabindex=1 	onClick="submitPage(event,'MENU');" onKeyPress="submitPage(event,'MENU');" > 
				
		</td>						
		</tr>
		<tr>
				<td class="tdfonthead" width="25%"><div align="right">From Date</div></td>
				<td class="tdfont" width="25%"><otTags:date name='fromDate' value="<%=fromDate%>"/></td>
				<td class="tdfonthead" width="25%"><div align="right">To Date</div></td>
				<td class="tdfont" width="25%"><otTags:date name='toDate' value="<%=toDate%>"/></td>
				
		     	 
		</tr>
		
		<%String mode = (String)request.getAttribute("hmode");
		  System.out.println("mode"+mode);
		
		 %>
		<logic:equal name="hmode" value="MENU">
		<tr>
		<td  class="tdfonthead"><b>S.No<b></td>
		<td  class="tdfonthead"><b>Date</b></td>		
		<td  class="tdfonthead"><b>Audit Log FileName</b></td>
		<td  class="tdfonthead"><b>View</b></td>
		</tr>
		
		<%
		int sNo = 1;
System.out.println("Inside list1");
String path = (String)request.getAttribute("fullpath");
System.out.println("path in jsp"+path);
%>
<logic:iterate id="record" name="AuditLogDetails" indexId='k'>
						<tr>
						<td  class="tdfont" width="25%"><%=sNo++%>.</td>
						<td  class="tdfont" width="25%"><bean:write name="record" property="dtDate"/></td>
						<td  class="tdfont" width="25%"><html:text name="record" property="filename"/></td>
						<td  class="tdfont" width="25%">
						<img  src="../../images/report_tab.gif" align="center"  onclick="search('<%=(String)request.getAttribute("fullpath")%>',<%=k%>)"/>
						</td>
						</tr>
				
</logic:iterate>
	<logic:empty name="AuditLogDetails">						
						<tr>
						<td width="100%" class="tdfont" colspan="5" ><center><font color="red"><b>No Record(s) Found</b></font></center></td>
						
						</tr>
	</logic:empty>			
		</logic:equal>
	<tr> 
		<td class="header" colspan="4">&nbsp;</td>
		</tr>
		
		
</table>



	
	
 
  <html:hidden property="hmode" name="admin_new"/>   
  <html:hidden property="menuid" name="admin_new"/>
  <html:hidden property="userList" name="admin_new"/>  
  <html:hidden property="moduleid" name="admin_new"/>
  
  
</html:form>
</body>
</html>

	
		
		
		
		
	
	
	