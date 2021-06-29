<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.*" %>
<%@page import="hisglobal.presentation.WebUTIL"%>
<jsp:useBean id="Bean" class="startup.HeaderAlert" scope="request"/>
<jsp:setProperty name="Bean" property="*"/>
<jsp:useBean id="MenuConfig" class="startup.menu.menuConfigure" scope="request" />


<%
String passwChange=(String)session.getAttribute("PASSWORD_CHANGE");

%>
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


<%
	String str = Bean.getAlert();
	
%>
<div class="header" style="height: 55px;">
	<div class="headerImage">
		<img src="<%=MenuConfig.getHeaderLeftImage() %>" >
	</div>
	<div class="logoutTag">
		<img src="/AHIMS/hisglobal/images/logoutIcon.png" alt="logout"
			width="20px" height="20px" title="Log Out" onclick="callLogOut()" onmouseover="showTitle(this)">
	</div>

	<div class="welcomeTag">
		<span> <%=UserFullName%></span>

	</div>
	<div class="dateTag" id="dateTdId"><%=currentDay%>,<%=systemDate1%></div>
</div>
<input type="hidden" name="hmode" value=""/>
	<input type="hidden" name="dateAsString1" value="<%=systemDate1%>"/>
	<input type="hidden" name="dateAsString2" value="<%=systemDate2%>"/>
	<input type="hidden" name="currentDay" value="<%=currentDay%>"/>
