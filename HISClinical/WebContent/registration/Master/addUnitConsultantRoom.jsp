<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%System.out.println("inside");%>
<%try{ %>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

    <script>
    function submitMain(mode){
	     alert("inside submit main");
		 document.getElementsByName("transactionMode")[0].value=mode
		 alert("hmode"+document.getElementsByName("transactionMode")[0].value)
		 document.forms[0].submit();
    }
    
    </script>
</head>


<his:TransactionContainer>

<body>

	<html:form action="/registration/master/addUnitConsultantRoom">			   	
	
		<jsp:include page="/registration/master/addUnit.cnt" flush="true"/>	      		   
		 
	 <logic:equal name="AddunitConsultantRoomFB" property="transactionMode" value="CONTINUETOCONSULT">
		 <jsp:include page="/registration/master/addUnitConsultant.cnt" flush="true"/>	   		   		   
	 </logic:equal>
		   
		   
		   <html:hidden name="AddunitConsultantRoomFB" property="transactionMode"/>
	</html:form>
	<%System.out.println("inside");%>
</body>
</his:TransactionContainer>
<%}catch(Exception e){
	e.printStackTrace();	
	} %>
</html>