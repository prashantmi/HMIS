<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 

<html>
<script type="text/javascript">
function fullScreen()
{
	 var url='/HISClinical/GenerateGraphACTION';
   	var graphFullScrPopup=window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+screen.availHeight+',width='+screen.availWidth+',left=10,top=10,dependent=yes,resizable=yes');
   graphFullScrPopup.document.body.innerHTML=
   "<img  style=cursor:pointer src='HISClinical/GenerateGraphACTION' height='100%' width='100%' alt='No image Loaded' ismap='ismap'>";
  
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<title>CHART</title>
</head>
<body>
<html:form action="/report.cnt">
 <img  id="imageId" style=cursor:pointer; onclick="fullScreen()" src="<his:path src='/GenerateGraphACTION'/>" height="80%" width="80%" alt="No image Loaded" ismap="ismap"  >
 <input type=hidden name="mode" value='<%=session.getAttribute("mode")%>'/>
 <p>&nbsp;</p>
 <a style=cursor:pointer;font-style:oblique;font-weight:bolder;    onclick="document.forms[0].submit();"><u>Back</u></a>
</html:form>
</body>
</html>