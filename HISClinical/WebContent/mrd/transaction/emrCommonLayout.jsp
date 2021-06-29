
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page autoFlush="true" %>
<%@ page import="org.apache.struts.tiles.ComponentContext"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%System.out.println("inside coomon layout");%>
<%try{ %>
<%@page import="mrd.MrdConfig"%>
<%@page import="ehr.EHRConfig" %>
<html> 
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 
  <tiles:getAsString name='title'  /> </title> 
	
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>
    
</head>
<script>
//alert("history clear")
window.history.forward()
function submitPage(mode){





	document.forms[0].mode.value=mode;
//	alert("action "+document.forms[0].action)
	document.forms[0].submit();
}

function showEMRDetails(id)
{
	var deskFrame=parent.document.getElementById("treeNodeFrameId");
	var doc='';
	
	if(window.XMLHttpRequest)
		doc=deskFrame.contentDocument;
	else if (window.ActiveXObject)
		doc=deskFrame.Document;
	
	//var frm=doc.forms[0];
	var emrDoc='';
	var deskFrameEMRDetails=parent.document.getElementById("emrDetailsId");
	if(window.XMLHttpRequest)
		emrDoc=deskFrameEMRDetails.contentDocument;
	else if (window.ActiveXObject)
		emrDoc=deskFrameEMRDetails.Document;
		
		var emrFrm=emrDoc.forms[0];
		//alert("hmode emr "+emrFrm.hmode.value)
		
		emrFrm.hmode.value="COMMON";
		emrFrm.submit();
	
}

</script>

<body>

<%ComponentContext compContext = (ComponentContext)pageContext.getAttribute("org.apache.struts.taglib.tiles.CompContext", 2);
Object value = compContext.getAttribute("action");
String val=value.toString();

String header=(String)compContext.getAttribute("header");
String body=(String)compContext.getAttribute("body");
String footer=(String)compContext.getAttribute("footer");
System.out.println("header"+header);
System.out.println("body"+body);
System.out.println("footer"+footer);
System.out.println("action: "+val);
%>
	<html:form action="/emrDesk">	
	<%
		System.out.println("inside layout");
	%>
	   
	   
	   <his:ContentTag>
		<jsp:include page="<%=body %>" flush="true"></jsp:include>
		</his:ContentTag>
	    <html:hidden name="EmrCommonDeskFB" property="hmode"/>
	    </html:form>
	    
</body>
	
<%}catch(Exception e){
	e.printStackTrace();	
	} %>
</html>