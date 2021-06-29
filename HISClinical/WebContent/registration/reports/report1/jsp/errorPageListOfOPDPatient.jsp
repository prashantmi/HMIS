
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 

<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Report Data Not Found</title>
</head>
<body >


<html:form action="/report1/report.cnt">

<h1>
		 <font color="#FF0000" face="Verdana, Arial, Helvetica, sans-serif">
			Sorry, there is no data available.
		</font>
</h1>
<font face="Verdana, Arial, Helvetica, sans-serif">
	The database does not contains any record corresponding to the parameter provided to generate the report.
	
</font>	


<input type=hidden name="mode" value='<%=session.getAttribute("mode")%>'/>
<input type=hidden name="isSupervisor"/>
<INPUT TYPE="button" align="MIDDLE" value="Back" onClick="document.forms[0].submit();"/> 


</html:form>
</body>
</html>