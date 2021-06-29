<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %> 
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>          
<his:ButtonToolBarTag>        
    <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>'style=cursor:pointer onkeypress="if(event.keyCode==13) submitReport();" tabindex="1" onclick = "submitReport();" />         
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="cancelReport()" onkeypress="if(event.keyCode==13) cancelReport()"/>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="clearReport()" onkeypress="if(event.keyCode==13) clearReport();"/>
</his:ButtonToolBarTag>