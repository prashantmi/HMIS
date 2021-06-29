<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/HISInvestigationTools.tld" prefix="inv"%>
<%@taglib uri="/WEB-INF/HISOperationtheaterTools.tld" prefix="ot"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
<%@page import="mrd.MrdConfig"%>
<%@page import="java.util.List"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitToclose()
{
	// alert("window")
	window.self.close();
}
</script>

 
<body bgcolor="#FFFFFF" >
<his:TitleTag key="operationResult" >
<%=request.getAttribute("OT_NAME")==null?" ": request.getAttribute("OT_NAME")%>
</his:TitleTag>
<his:ContentTag>




<%

		List templateId = (List) request.getSession().getAttribute(MrdConfig.OPERATION_TEMPLATE_LIST);
		
		if (templateId != null && templateId.size() > 0) {
		
		for (int i = 0; i < templateId.size(); i++) {
		
		%>
		
		<table width="100%" cellspacing="1" cellpadding="0">
		
			<tr>
		
			<td width="100%"><ot:OTTemplateTag		
				templateId="<%=(String)templateId.get(i)%>" mode="<%=GenericTemplateConfig.GENERIC_TEMPLATE_MODE_REPORT%>"></ot:OTTemplateTag>
		
			</td>
		
			</tr>
		
		</table>
		
		 
		
		<%

}

}

%>

</his:ContentTag>
<his:ButtonToolBarTag>
 <img class="button" src='<his:path src="/hisglobal/images/close.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitToclose();" onkeypress="if(event.keyCode==13) submitToclose();">
</his:ButtonToolBarTag>
</body>
</html>