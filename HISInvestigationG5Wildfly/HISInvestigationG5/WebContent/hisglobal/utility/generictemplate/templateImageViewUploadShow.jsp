<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>

<html>
<body>
<his:statusDone>
	<img src="<%=ServletsUtilityConfig.SERVLET_DISPLAY_BYTE_ARRAY_URL%>">
</his:statusDone>
<his:statusUnsuccessfull>
	<font color="#FF0000"><b>No Image Found</b></font>
</his:statusUnsuccessfull>
</body>
</html>