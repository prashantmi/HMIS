<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.transaction.controller.fb.PostmortemRequestFB"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<script type="text/javascript">

function closeForm()
{
	self.close();
}
</script>

<html:form action="/opinionApproval">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
		<his:ContentTag>
		<%PostmortemRequestFB fb=(PostmortemRequestFB)pageContext.findAttribute("PostmortemRequestFB"); 
			String tempId=fb.getTemplateId();
		%>
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td>
						<his:GenericTemplateTag templateId="<%=tempId %>"></his:GenericTemplateTag>
					</td>
				</tr>		
			</table>	
		</his:ContentTag>
	</body>
	
	<html:hidden name="PostmortemRequestFB" property="templateId"/>
</html:form>	