<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/hisglobal/js/validationCommon.js" />

<%@page import="hisglobal.hisconfig.Config"%>
<HTML>
<body>
<%
String message="";
try{

if(session!=null)
	 message=(String)session.getAttribute(Config.MARQUE_MESSAGE);  
  }
  catch(Exception e)
  {
	  e.printStackTrace();
  }
  if(message==null)
	  message="";
  
%>
<marquee scrolldelay="150" ><font color="#000000" ><b><%=message%></b></font></marquee>



</body>
</HTML>
