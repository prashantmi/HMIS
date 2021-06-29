<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ page import ="registration.*,hisglobal.presentation.*" %>


<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="org.apache.struts.tiles.ComponentContext"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>   <tiles:getAsString name='title'  /> </title> 
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>
function submitPage(mode){
	document.forms[0].deskmode.value=mode;
	alert(document.forms[0].deskmode.value);
	document.forms[0].submit();
}
</script>
</head>


<%

Status objStatus=(Status) request.getAttribute(Config.STATUS_OBJECT);
System.out.println("status List"+objStatus);

//System.out.println("status List"+objStatus.getStatusList());

%>


<body>
	<%
		ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
		String header=(String)compContext.getAttribute("header");
		String body=(String)compContext.getAttribute("body");
		String footer=(String)compContext.getAttribute("footer");
	%>
<his:TransactionContainer>
<html:form action="/master/hospitalDiseaseMaster" method = "post">
	<html:hidden  name='HospitalDiseaseMasterFB' property='deskmode'/>
	    
	     <jsp:include page="<%=header%>" flush="true"></jsp:include>   
		<jsp:include page="<%=body%>" flush="true"></jsp:include> 
	 	<jsp:include page="<%=footer%>" flush="true"></jsp:include> 
	    
</html:form> 
</his:TransactionContainer>
</body>
</html>