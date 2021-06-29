<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function cancel()
{
window.close();
}
function clear()
{
{alert("clear");
    document.getElementsByName('hmode')[0].value="DOCTORCALLBOOKLOGREPORT";  	   	    
	document.forms[0].submit();
    }
}
</script>
           
<his:ButtonToolBarTag>        
    <img class="button" src='<his:path src="/hisglobal/images/btn-view.png"/>'style=cursor:pointer onkeypress="if(event.keyCode==13) submitReport();" tabindex="1" onclick = "submitReport();" />         
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="cancel()" onkeypress="if(event.keyCode==13) cancel()"/>
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick ="clear()" onkeypress="if(event.keyCode==13) clear()"/>
</his:ButtonToolBarTag>