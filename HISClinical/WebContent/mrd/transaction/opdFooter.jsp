<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/registration/js/popup.js" />

<his:javascript src="/opd/opdJs/opd.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function showReportDesk(event)
{
	// alert("Report Desk")
	// alert("screen.availHeight "+screen.availHeight+" screenWidth "+screen.availWidth)
	var screenHieght=screen.availHeight-100;
	

 child=openDependentPopup('<his:path src='opd/gotoOpdReportDesk.cnt'/>',event,screenHieght,screen.availWidth);
 child.moveTo(0,0);
	
}




</script>

<his:ContentTag>
<table width="100%" cellspacing="0" cellpadding="0" height="10%">	
	<tr style="margin:0%">
		<td>
			<a href="/HISClinical/opd/transaction/opdFooter.jsp" onclick="showReportDesk(event)" >Click For Reports</a>
		</td>
	</tr>
	
</table>
</his:ContentTag>


