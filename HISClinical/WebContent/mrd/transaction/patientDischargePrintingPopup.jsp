<%try{ %>
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
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function printIt(){
	document.getElementById("printButton").style.display="none"
	window.print();
	self.close();
	/*var isSuccessfull=opener.window.confirm("Is printing Successfull")
	if(isSuccessfull){
		window.document.getElementsByName("hmode")[0].value="SAVE"
		window.document.forms[0].submit();
	}*/
}
</script>
<body >
<form>
	<table width="100%" cellspacing="1">
	<tr>
		<td width="100%">
		<div align="right" id="printButton">
		<img  class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  style="cursor:pointer" tabindex="1" onclick =" printIt()" onkeypress="if(event.keyCode==13)printIt()">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="self.close()" onkeypress="if(event.keyCode==13)self.close()">
		</div>
		</td>
	</tr>
	</table>		
	<%String array=(String)session.getAttribute(MrdConfig.PATIENT_PROFILE_BYTE_ARRAY);%>
	<%=array %>
<his:status/>
</form>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();}%>