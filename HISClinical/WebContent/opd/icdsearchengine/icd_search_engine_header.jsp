<!-- 
/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */
--><%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.icdsearchengine.ICDSearchEngineConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>ICD Search Engine</title>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/opd/icdsearchengine/css/icdSearchEngine.css"/>

<his:javascript src="/hisglobal/utility/generictemplate/js/commonDesigner.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngineformbean.js"/>
<his:javascript src="/opd/icdsearchengine/js/icdSearchEngine.js"/>

<script>

window.onload = function()
{
	// Setting Engine Object 
	var objEngine = ICDSearchEngine.getICDSearchEngine();
	if(!objEngine)
	{
		parent.callOnLoad();
		objEngine = ICDSearchEngine.getICDSearchEngine();
	}
	if(objEngine)	objEngine.populate();
}
</script>

</head>

<body>

<html:form action="/icdsearchengine/icdEngineHeader">


<table cellpadding="0" cellspacing="0" width="100%">
	<tr><td style="background-color: rgb(255, 255, 255);"></td></tr>
	<tr><td class="ShadedTitleTagImage">
		<table cellpadding="5" cellspacing="0" width="100%">
			<tr><td class="engineHeader">
				<div align="center">
					ICD Search Engine
				</div>
			</td></tr>
		</table>
	</td></tr>
</table>

<table id="idOptions" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<bean:define id="CurOption" name="ICDSearchEngineFB" property="strCurOption" type="java.lang.String"></bean:define>
	<%
		for(int i=0;i<ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_ARR.length;i++)
		{
			if(i==Integer.parseInt(CurOption))
			{
	%>
		<td>
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="activeTabLeft"></td>
					<td class="activeTabMid">
						<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_ARR[i]%>
					</td>
					<td class="activeTabRight"></td>
				</tr>
			</table>
		</td>
	<%		}	else	{	 %>
		<td id="tabDrugAdvice" onclick="activateTab(<%=i%>)">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="inactiveTabLeft"></td>
					<td class="inactiveTabMid">
						<%=ICDSearchEngineConfig.ICD_ENGINE_SEARCH_OPTION_ARR[i]%>
					</td>
					<td class="inactiveTabRight"></td>
				</tr>
			</table>
		</td>
	<%	}	} %>
	</tr>
</table>

<jsp:include page="/opd/icdsearchengine/icd_search_engine_hidden_fields.jsp" flush="true"></jsp:include>

</html:form>
</body>
</html>