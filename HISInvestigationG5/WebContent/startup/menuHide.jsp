<%
try{
%>
<%@page import="java.text.DateFormat"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.*" %>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<jsp:useBean id="MenuConfig" class="startup.menu.menuConfigure" scope="request" />

<%@page import="hisglobal.presentation.WebUTIL"%>
	<%
	String UserFullName="";
	String systemDate1="";
	String systemDate2="";
	String currentDay=""; 
	Date currentDate=new Date();
	String []WeekDays={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	
try{
		
	if(session!=null)	
    	  {	
	 UserFullName="Welcome, "+session.getAttribute("UserFullName");
	
	 //This calling of function is closed inorder to increase the performance 
	 //which is called from the login query 
	 
	// ControllerUTIL.setSysdate(request);
	 systemDate1=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT_LOGIN), "dd-MMM-yyyy HH:mm");
	 systemDate2=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT_LOGIN), "dd-MM-yyyy HH:mm");
	 currentDate=(Date)WebUTIL.getSession(request).getAttribute(Config.SYSDATEOBJECT_LOGIN);
     currentDay=WeekDays[currentDate.getDay()];
	
	  //System.out.println("dayCode-----"+currentDate.getDay()+"-----Day----"+currentDay);
	      }
	else
		UserFullName="";
	  
	}
catch(Exception e)
	{
	UserFullName="";
	e.printStackTrace();
	}
	
	
	%>
		
		<table cellpadding="0" cellspacing="0" width="100%" class="header" style="height: 20px;">
		
<tr>
	<td width="1%">
		</td>
	<td width="30%">
	<div align="left" ><font color="#ffffff" style="position: relative;left:3%;top:0%;" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><%=UserFullName%></b></font></div>
	</td>	
		
	
		<td width="65%">
		<div align="left" id="dateTdId"><font color="#ffffff" style="position: relative;left:60%;top:0%;" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b><%=currentDay%>,<%=systemDate1%></b></font></div>
	</td>
	
	
		<td width="4%" >		
	
	<img src="<%=MenuConfig.getLogoutImage() %>"  onclick="callLogOut()" onmouseover="showTitle(this)" style="position:  relative;right:0%;top:  5%;cursor: pointer;">
	
		</td>	
		
		
	
		
</tr>	
	</table>
	
	<input type="hidden" name="hmode" value=""/>
	<input type="hidden" name="dateAsString1" value="<%=systemDate1%>"/>
	<input type="hidden" name="dateAsString2" value="<%=systemDate2%>"/>
	<input type="hidden" name="currentDay" value="<%=currentDay%>"/>
<%
}catch(Exception e){e.printStackTrace();}
%>