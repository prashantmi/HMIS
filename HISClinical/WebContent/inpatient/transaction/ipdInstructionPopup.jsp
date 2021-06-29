<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function closeForm()
{
	self.close();
}

</script>

<html:form action="/nurExtTreatAdministration">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body >
		<his:TitleTag name="INSTRUCTION">
		</his:TitleTag>
		<his:ContentTag>
				
		<table width="100%" border="0" cellspacing="1" cellpadding="0" >
			<logic:iterate id="entryObj" indexId="idx" name="<%=InpatientConfig.PREV_INSTRUNCTION_LIST_FOR_PAT%>" type="hisglobal.utility.Entry">
			<tr>
				<td class="tdfont">
					<bean:write name="entryObj" property="label"/>
				</td>
			</tr>
			</logic:iterate>
		
		</table>
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
    </body>	
    <logic:empty name="<%=InpatientConfig.PREV_INSTRUNCTION_LIST_FOR_PAT%>">
		<table width="100%" cellpadding="0" cellspacing="1" >
					<tr>
						<td>
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>No Instructions</b>
						</font>
						</td>
					</tr>
				</table>
	</logic:empty>
</html:form>