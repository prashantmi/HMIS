<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<script type="text/javascript">

</script>
<body>
<his:TransactionContainer>
<html:form action="/cdBurn">
	<his:TitleTag name="CD Burn">
	</his:TitleTag>
	<%String htmlCode=(String)session.getAttribute(MrdConfig.PATIENT_PROFILE_HTML_STRING); %>
	<%if(htmlCode!=null && !htmlCode.equals("")){ %>
		<%=htmlCode %>
	<%} else{%>
		File Not Found
	<%} %>
</html:form>
<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick =" self.close()" onkeypress="if(event.keyCode==13) self.close()">
</his:ButtonToolBarTag>
<his:status/>
</his:TransactionContainer>
</body>
</html>

