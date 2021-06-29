<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@page autoFlush="true" %>
<%@ page import="org.apache.struts.tiles.ComponentContext,registration.*"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%try{ %>
<html>
<head>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />	
<title> 
  <tiles:getAsString name='title'  /> </title> 
	
<link href="/HIS/hisglobal/css/avai/Color.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/avai/master.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/avai/hisStyle.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/avai/calendar-blue2.css" rel="stylesheet" type="text/css">

</head>
<script>
window.history.forward()
function submitPage(mode)
{
	document.forms[0].mode.value=mode;
	document.forms[0].submit();
}
/*
function  showOnLoad()
  {
  
 if((document.getElementsByName("mode")[0].value=="EMGNEWREG") && (document.getElementsByName("hmode")[0].value=="unspecified"))
  {
	 showdelhidiv();
  }
  
  else if(document.getElementsByName("mode")[0].value=="EMGPATDTLMOD")
  {
  showdelhidiv();
  }
  
  
  else if((document.getElementsByName("mode")[0].value=="CMOREGISTER")&& (document.getElementsByName("registermode")[0].value=="DTL4STATUS"))
  {
 // alert("else");
  if(document.forms[0].episodeActionCode.value=="8")
    document.getElementsByName("bodyHandOverTo")[1].checked="true";
    document.getElementsByName("crNo1")[0].focus();
  }
  
  //else
      //showTileSpecifications();
      
      
  }
*/

</script>
<body>

<%ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
Object value = compContext.getAttribute("action");
String val=value.toString();

String header=(String)compContext.getAttribute("header");
String body=(String)compContext.getAttribute("body");
String footer=(String)compContext.getAttribute("footer");

%>
	<html:form action="<%=val%>">	
	
	   <jsp:include page="<%=header %>" flush="true"></jsp:include>
	<jsp:include page="<%=body %>" flush="true"></jsp:include>
	    <html:hidden name="commonTransactionFB" property="mode"/>
	    </html:form>
</body>
<%}catch(Exception e){
	e.printStackTrace();	
	} %>
</html>