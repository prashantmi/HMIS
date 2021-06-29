<%@page import="java.util.*" %>
<%@page import="usermgmt.reports.inv_UserLogUtil"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
		
		<%System.out.println("testing statring");
				String thmode = (String)request.getAttribute("hmode");
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
				
				System.out.println("fromDate----->"+fromDate);
				System.out.println("toDate----->"+toDate);
		%>
		<%
		inv_UserLogUtil myUtil	= new inv_UserLogUtil();
		String HospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
		request.setAttribute("userList",myUtil.usercombo(HospitalCode)); 
		
		
		String 	status      	= "No Record(s) found to Matching Criteria";
		String 	result_page 	= "/usermgmt/masters/result.jsp";
		String 	cnt_page 		= "inv_Userlog_lst.jsp";
		String hmode			= "user_form";
		String Name				= "Status Report";
		
		if(thmode.equals("USER_LIST"))
		{
			
			List rptListf=(ArrayList)request.getAttribute("rptList");
		  if(rptListf.size()== 0)
			{
			%>
				<jsp:forward page="<%=result_page%>">
					<jsp:param name="hmode" value="<%=hmode%>"/>
					<jsp:param name="status" value="<%=status%>"/>
					<jsp:param name="Name" value="<%=Name%>"/>
					<jsp:param name="nextpage" value="<%=cnt_page%>"/>
				</jsp:forward>
			<%
			}
		}
			%>

<HTML>
	<HEAD>
		<TITLE>
			 User Log Report Interface Page
		</TITLE>
	<script>
window.history.forward();
</script>	
<script>
	
	function focusOnLoad()
	{

	document.forms[0].user.focus();	
	}
	
	function dateValidate()
	{
	}//end of function
	
	
	function submitPage(e,mode)
{				
	if(e.type=="click" || e.keyCode==13)
  {		

		if(document.getElementsByName("user")[0].value=="-1")
		{
			alert("Please select User Name");
	    		document.forms[0].user.focus();	
			return false;
		
		}
		
		else if(document.getElementsByName("fromDate")[0].value=="-1"||document.getElementsByName("fromDate")[0].value.length==0)
		{
			alert("Please select From Date");
			document.forms[0].fromDate.focus();
			return false;
		}
		
		else if(document.getElementsByName("toDate")[0].value=="-1"||document.getElementsByName("toDate")[0].value.length==0)
		{
			alert("Please select To Date");
			document.forms[0].toDate.focus();
			return false;
		}
		
		else if(CompareDates()==false)
    	{
    	
     		document.getElementById("fromDate")[0].select();
     		document.forms[0].fromDate.focus();
     		return false;
    	}
		
		else
		{	
			document.forms[0].hmode.value = mode;		
			document.forms[0].submit();
		}
		
	}
}	
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
		
		changeState('id1','hidden');
		if(e.type=="click" || e.keyCode==13)
		{		
			window.print();		
		}
		changeState('id1','visible');
	}
	

</script>
	
	
		
	<script language="JavaScript" src="../js/calendar.js"></script>
	<script language="JavaScript" src="../js/ValidateDate.js"></script>
	<style type="text/css">@import url(../../css/calendar-blue2.css);</style>


	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
	<link href="../../css/Color.css" rel="stylesheet" type="text/css">
	<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
	<link href="../../css/master.css" rel="stylesheet" type="text/css">
	</head>
	
	<body background="../../images/cdac1.psd.gif" onload="focusOnLoad()">
	<html:form action="/usermgmt/reports/user_log"  name="user_form" type="usermgmt.reports.inv_UserLogActionForm">	
	
	
		<table width="100%" border="0">		
			<tr> 
						<td class="ShadedSubTitleTagImage" colspan="8">UserLog  Report</td>
			</tr>		
			<tr>			
						<td class='label' colspan="2" >
						<div align='right'>
						User Name
						</div>						
						</td>
								
						<td class='control' colspan="2">		
						<html:select property="user" name="user_form"  tabindex="1">		 
						<html:options collection="userList" property="optionValue" labelProperty="optionText" />			
						</html:select> 
						</td>
						
						<td class="label" colspan="2"></td>
						<td class="control" colspan="2">
<!--						<img style='cursor:hand' src='../../images/GoNew.png' 	tabindex=1 	onclick="submitPage(event,'USER_LIST');" onKeyPress="submitPage(event,'USER_LIST');" > -->
						<a style="cursor: pointer;" class="button"  tabindex='0' onClick="submitPage(event,'USER_LIST');" onKeyPress="submitPage(event,'USER_LIST');"><span>Go</span></a>
						
						</td>
						
												
			</tr>		
			<tr>
						<td class="label" colspan="2"><div align="right">From Date</div></td>
						<td class="control" colspan="2"><otTags:date  name='fromDate' value="<%=fromDate%>"/></td>
						<td class="label" colspan="2"><div align="right">To Date</div></td>
						<td class="control" colspan="2"><otTags:date name='toDate' value="<%=toDate%>" /></td>
		
			</tr>	
			<tr> 
						<td class="ShadedSubTitleTagImage" colspan="8">&nbsp;</td>
			</tr>		
		</table>

	<%	
		if(!thmode.equals("USER_LIST"))
		{
		
	
	}//end of if loop	
	

		if(thmode.equals("USER_LIST"))
		{
			List rptList=(ArrayList)request.getAttribute("rptList");
			System.out.println("size of rptList              "+rptList.size());
			
			
			if(rptList.size()!=0)
			{				
			
				
		
	%>
		<table width='100%' border='0' cellspacing='0' cellpadding='0' height='159'>      	
				<tr>
						<td class='control' colspan='5' nowrap><div align='left'><font face = Verdana size=2><b>Seat Name:-</b> &nbsp;&nbsp;&nbsp;<%=rptList.get(6)%></font></div>
						</td>			
					
						<td  class='control' colspan='7'>
						</td>			
				</tr>				
		    	
		    	<tr> 
		     			 <td class='control'  height="22" colspan=12> <hr> </td>
	    		</tr>
		   		 <tr> 
					      <td class='control' colspan='2'><div align="left"><font face = Verdana size="2"><b>S.No.</b></font></div>&nbsp;&nbsp;&nbsp;</td>				      
					      <td class='control' colspan='2'  nowrap><div align="right"><font face = Verdana size="2"><b>Log In Time</b></font></div>&nbsp;&nbsp;&nbsp;</td>
					      <td class='control' colspan='2'  nowrap><div align="right"><font face = Verdana size="2"><b>Log Out Time</b></font></div>&nbsp;&nbsp;&nbsp;</td>
					      <td class='control' colspan='6'  nowrap><div align="center"><font face = Verdana size="2"><b>IP Address</b></font></div>&nbsp;&nbsp;&nbsp;</td>
		      
			    </tr>
				<tr> 
		      			<td class='control' colspan=12> <hr></td>
				</tr>
	    	<%

				int counter = 0;
				int sNo	=	1;
				String login  = "";
				String logout = "";
				
				String loginDate  = "";
				String loginTime = "";
				String logoutDate = "" ;
				String logoutTime = "" ;
				String ipAddress = "";
				String loginDate_tmp = "";
				
				String logoutDate_tmp = "";
				String logoutTime_tmp = "";
				
				
				
				try{
				for(int index=0;index<rptList.size();index+=7)
				{					
					 login =  rptList.get(index+2).toString();//login date and time
					 logout =  rptList.get(index+3).toString();//logout date and time
					 ipAddress  = rptList.get(index+4).toString(); // IP Address					 
					 int a  = login.indexOf(" ");
					 loginDate =  login.substring(0,a);
					 loginTime = login.substring(a+1);
					
					 if ( !loginDate_tmp.equals(loginDate) )
							loginDate_tmp     = loginDate ; 
					else
							 loginDate_tmp  ="-";								 
					 if ( !logout.equals("*") )
					 {							 
						 int b  = logout.indexOf(" ");
						 logoutDate =  logout.substring(0,b);
						 logoutTime = logout.substring(b+1);
						 logoutTime_tmp = logoutTime;							
							
							if ( !logoutDate_tmp.equals(logoutDate) )
								  logoutDate_tmp  =  logoutDate ; 
							else
								  logoutDate_tmp  = "-";				
					 }
					 else
						{						 
						 logoutTime_tmp	=	"";
						 logoutDate_tmp="*";
						}					
			%>
					<tr> 
					      <td class='control' colspan='2'><font face = Verdana size="2"><%=sNo%>.</font></td>			      
					     <td class='control' colspan='2' nowrap><div align="right"><font face = Verdana size="2"><%=loginDate_tmp+" "+loginTime%></font></div></td>
					      <td class='control' colspan='2'  nowrap><div align="right"><font face = Verdana size="2"><%=logoutDate_tmp+" "+logoutTime_tmp%></font></div></td>
					      <td class='control' colspan='6' nowrap><div align="center"><font face = Verdana size="2"><%=ipAddress%></font></div></td>	  
		      
			    	</tr>
   			 <% 
   			 		loginDate_tmp     = loginDate ;
		   			logoutDate_tmp  =  logoutDate ;   			 
					sNo++;
					counter++;			
					}//end of for loop
				}
					catch(Exception e ) { System.out.println(" my exception = "+e);}
			  %>			
			
    	<tr> 
      			<td class='control' colspan=12> <hr> </td>
   		 </tr> 
 
	</table>		
	<%			
				}
				

		
	}//end of if loop having  USER_LIST	
	%>		

		
		<html:hidden property="hmode" name="user_form"/>
		<html:hidden property="userid" name="user_form"/>        
		
		</html:form>
	
	</body>
	</html>		
		
		
		
		