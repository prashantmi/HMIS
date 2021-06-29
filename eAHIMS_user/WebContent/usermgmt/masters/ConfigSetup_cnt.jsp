	<jsp:useBean id="configSetup" class="usermgmt.masters.ConfigSetup_Bean" scope="request"><jsp:setProperty name="configSetup" property="*" /></jsp:useBean>

	
	<%@page import="java.util.*"%>
	<%@page import="login.CSRFTokenUtil"%>

<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">

</head>
<body>
<form method="post" action="" name="form1">
<% 

String 	mode   	= request.getParameter("hmode");
boolean retValue 		= true;
if(mode.equals("SAVE"))
{
  	if(CSRFTokenUtil.isValid(request))
	{	
    	retValue = configSetup.insertRecord();
		if (retValue)
		{
			out.println(configSetup.getStatus());
		}
	}
}

%>
</form>
</body>
</html>
